package com.qx.eakay.service.stake;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qx.common.base.BaseExport;
import com.qx.common.base.PageResults;
import com.qx.eakay.dao.stake.StakeDeviceNameDao;
import com.qx.eakay.dao.stake.StakeDevicePortDao;
import com.qx.eakay.dao.stake.StakeDevicePortTypeDao;
import com.qx.eakay.dao.stake.StakeSiteAreaDao;
import com.qx.eakay.export.stake.StakeDeviceExport;
import com.qx.eakay.model.stake.StakeDeviceNamePO;
import com.qx.eakay.model.stake.StakeDevicePortPO;
import com.qx.eakay.service.BaseService;

/**
 * 
 * @author jixf
 * @date 2015年7月15日
 */
@Service
public class DeviceService extends BaseService {

	@Autowired
	private StakeDeviceNameDao deviceNameDao;
	@Autowired
	private StakeDevicePortDao devicePortDao;
	@Autowired
	private StakeDevicePortTypeDao devicePortTypeDao;
	@Autowired
	private StakeSiteAreaDao areaDao;

	/**
	 * 分页查找充电设备
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findDevicesPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return deviceNameDao.findDevicesPage(jsonObject, pageSize,
				iDisplayStart);
	}

	/**
	 * 导出充电设备列表
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportDevices(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new StakeDeviceExport();
		List<Map<String, Object>> list = findDevices(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 条件搜索充电设备
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findDevices(JSONObject jsonObject) {
		return deviceNameDao.findDevices(jsonObject);
	}

	/**
	 * 创建充电设备
	 * 
	 * "1托30" 增加31个充电桩， 1个主充电桩(DeviceTypeNo:"2") ，30个子充电桩(DeviceTypeNo:"0");
	 * 其他(DeviceTypeNo:"1")
	 * 
	 * @param deviceNamePO
	 * @return
	 */
	@Transactional
	public boolean createDevice(StakeDeviceNamePO deviceNamePO) {

		StakeDeviceNamePO deviceName = getDeviceNameType(deviceNamePO);

		deviceNamePO.setDeviceType(deviceName.getDeviceType());
		deviceNamePO.setParkType(deviceName.getParkType());

		// 其他（原来创建方式）
		if (!"旗翔1托30".equals(deviceNamePO.getChargePort())) {

			Integer id = deviceNameDao.create(deviceNamePO);
			// 创建充电设备端口
			createDevicePorts(deviceNamePO);

			return id != null;

			// 1托30
		} else {
			// 创建主充电桩
			deviceNamePO.setDeviceTypeNo("2");
			deviceNameDao.create(deviceNamePO);

			// 创建子充电桩
			deviceNamePO.setDeviceTypeNo("0");
			Integer[] arr = new Integer[deviceNamePO.getStakeNum()];
			deviceNameDao.batchCreate(deviceNamePO, arr);

			// 创建充电设备端口
			deviceNamePO.setGroupNo(deviceNamePO.getFactoryNo());
			createNewDevicePorts(deviceNamePO, arr);

			return true;
		}
	}

	/**
	 * 获取充电设备
	 * 
	 * @param id
	 * @return
	 */
	public StakeDeviceNamePO getDevice(Integer id) {
		return deviceNameDao.get(id);
	}

	/**
	 * 获取充电设备(子充电桩页面)
	 * 
	 * @param id
	 * @return
	 */
	public StakeDeviceNamePO getDeviceChild(Integer id) {
		return deviceNameDao.getChild(id);
	}

	/**
	 * 修改充电设备
	 * 
	 * @param deviceNamePO
	 * @return
	 */
	@Transactional
	public boolean updateDevice(StakeDeviceNamePO deviceNamePO) {
		StakeDeviceNamePO deviceName = getDeviceNameType(deviceNamePO);

		deviceNamePO.setDeviceType(deviceName.getDeviceType());
		deviceNamePO.setParkType(deviceName.getParkType());

		StakeDeviceNamePO devicePO = deviceNameDao.get(deviceNamePO.getId());

		// 其他（原来创建方式）
		if ("1边1车".equals(deviceNamePO.getParkType())) {
			// 删除原有的端口
			devicePortDao.delete(deviceNamePO.getId());
			// 创建充电设备端口
			createDevicePorts(deviceNamePO);
			return deviceNameDao.update(deviceNamePO);

			// 1托30(1口1车)
		} else {

			// "0"子桩子；"2"总桩子；"1"其他
			String deviceTypeNo = devicePO.getDeviceTypeNo();

			if ("0".equals(deviceTypeNo)) {
				// 单独修改子充电桩
				return deviceNameDao.updateChild(deviceNamePO);

				// 批量修改主、子充电桩
			} else {

				String groupNo = devicePO.getGroupNo();
				String oldFactoryNo = devicePO.getFactoryNo();

				String factoryNo = deviceNamePO.getFactoryNo();
				Integer stakeNum = deviceNamePO.getStakeNum();

				// 查找子充电桩id
				List<Map<String, Object>> list = deviceNameDao
						.getByGroupNo(groupNo);

				Integer[] stakeIds = new Integer[list.size()];
				Integer i = 0;

				for (Map<String, Object> map : list) {
					stakeIds[i] = Integer.parseInt(map.get("id") + "");
					i++;
				}

				// 1托30或充电桩编号不变 （不需要修改充电桩端口表也不需要增加或删除子充电桩数量）
				if (oldFactoryNo.equals(factoryNo)
						&& stakeNum.equals(deviceNamePO.getDeviceType())) {
					// 批量修改主、子充电桩(根据groupNo)
					return deviceNameDao.batchUpdate(deviceNamePO);

				} else {

					Integer[] arr = new Integer[stakeNum];

					// 删除原有的端口
					devicePortDao.deleteOther(groupNo + "-%");
					// 创建充电设备端口
					deviceNamePO.setGroupNo(deviceNamePO.getFactoryNo());
					createNewDevicePorts(deviceNamePO, arr);

					// 端口数量是否变化
					if (stakeNum.equals(deviceNamePO.getDeviceType())) {

						// 修改主充电桩
						deviceNameDao.update(deviceNamePO);

						// 批量修改子充电桩
						return deviceNameDao.batchUpdateChild(deviceNamePO,
								stakeIds);
					} else {

						// 删除子充电桩
						deviceNameDao.deleteChild(groupNo);

						// 创建子充电桩
						deviceNamePO.setDeviceTypeNo("0");
						return deviceNameDao.batchCreate(deviceNamePO, arr);

					}

				}

			}
		}
	}

	/**
	 * 创建设备充电端口(其他)
	 * 
	 * @param deviceNamePO
	 */
	@Transactional
	private void createDevicePorts(StakeDeviceNamePO deviceNamePO) {
		List<Map<String, Object>> ports = devicePortTypeDao
				.findPorts(deviceNamePO.getChargePort());
		List<StakeDevicePortPO> devicePorts = new ArrayList<>();
		for (Map<String, Object> port : ports) {
			StakeDevicePortPO devicePortPO = new StakeDevicePortPO();
			devicePortPO.setCurrents(port.get("spec") + "");
			devicePortPO.setFactoryNo(deviceNamePO.getFactoryNo());
			devicePortPO.setGrooveNo(port.get("grooveNo") + "");
			devicePortPO.setMemo(port.get("memo") + "");
			devicePortPO.setName(port.get("portname") + "");
			devicePortPO.setPortorder(port.get("portorder") + "");
			devicePortPO.setPos(port.get("pos") + "");
			devicePorts.add(devicePortPO);
		}
		devicePortDao.batchCreate(devicePorts);
	}

	/**
	 * 创建设备充电端口(1托30)
	 * 
	 * @param deviceNamePO
	 */
	@Transactional
	private void createNewDevicePorts(StakeDeviceNamePO deviceNamePO,
			Integer[] arr) {
		Integer i = 0;
		List<Map<String, Object>> ports = devicePortTypeDao
				.findPorts(deviceNamePO.getChargePort());
		List<StakeDevicePortPO> devicePorts = new ArrayList<>();

		for (Map<String, Object> port : ports) {
			if (i < arr.length) {
				StakeDevicePortPO devicePortPO = new StakeDevicePortPO();
				devicePortPO.setCurrents(port.get("spec") + "");
				devicePortPO.setFactoryNo(deviceNamePO.getGroupNo() + "-"
						+ (i + 1));
				devicePortPO.setGrooveNo(port.get("grooveNo") + "");
				devicePortPO.setMemo(port.get("memo") + "");
				devicePortPO.setName(port.get("portname") + "");
				devicePortPO.setPortorder(port.get("portorder") + "");
				devicePortPO.setPos(port.get("pos") + "");
				devicePorts.add(devicePortPO);
				i++;
			} else {
				break;
			}
		}
		devicePortDao.batchCreate(devicePorts);
	}

	/**
	 * 查找充电设备类型参数
	 * 
	 * @param deviceNamePO
	 */
	private StakeDeviceNamePO getDeviceNameType(StakeDeviceNamePO deviceNamePO) {
		return devicePortTypeDao.getDeviceNameType(deviceNamePO);

	}

	/**
	 * 删除充电设备
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public boolean deleteDevice(Integer id) {

		StakeDeviceNamePO devicePO = deviceNameDao.get(id);

		if ("2".equals(devicePO.getDeviceTypeNo())) {
			// 删除所有的端口(1托30)
			devicePortDao.deleteOther(devicePO.getGroupNo() + "-%");
			return deviceNameDao.deleteOther(devicePO.getGroupNo());
		} else {
			// 删除充电设备充电端口(其他)
			devicePortDao.delete(id);
			return deviceNameDao.delete(id);
		}
	}

	/**
	 * 检测充电设备编号是否已存在
	 * 
	 * @param factoryNo
	 * @param id
	 * @return
	 */
	public boolean cheakFactoryNo(String factoryNo, Integer id) {
		StakeDeviceNamePO deviceNamePO = deviceNameDao
				.findByFactoryNo(factoryNo);
		if (deviceNamePO == null) {
			return true;
		}
		if (deviceNamePO.getId().equals(id)) {
			return true;
		}
		return false;
	}

	/**
	 * 检测充电设备铭牌是否已存在
	 * 
	 * @param nameplate
	 * @param id
	 * @return
	 */
	public boolean checkNameplate(String nameplate, Integer id) {
		StakeDeviceNamePO deviceNamePO = deviceNameDao
				.findByNameplate(nameplate);
		if (deviceNamePO == null) {
			return true;
		}
		if (deviceNamePO.getId().equals(id)) {
			return true;
		}
		return false;
	}

	/**
	 * 检测充电设备名称是否已存在
	 * 
	 * @param deviceNo
	 * @param id
	 * @return
	 */
	public boolean checkDeviceNo(String deviceNo, Integer id) {
		StakeDeviceNamePO deviceNamePO = deviceNameDao
				.findByDeviceNo(deviceNo);
		if (deviceNamePO == null) {
			return true;
		}
		if (deviceNamePO.getId().equals(id)) {
			return true;
		}
		return false;
	}

	/**
	 * 查找充电口分类
	 * 
	 * @return
	 */
	public List<Map<String, Object>> findPortTypes(JSONObject jsonObject) {
		return devicePortTypeDao.findPortTypes(jsonObject);
	}

	/**
	 * 根据充电站查找充电分区
	 * 
	 * @param siteId
	 * @return
	 */
	public List<Map<String, Object>> findAreas(String siteCode) {
		if (StringUtils.isBlank(siteCode)) {
			return new ArrayList<>();
		}
		return areaDao.findAreas(siteCode);
	}

	/**
	 * 条件搜索设备端口
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findDevicePorts(JSONObject jsonObject) {
		return devicePortDao.findDevicePorts(jsonObject);
	}

}
