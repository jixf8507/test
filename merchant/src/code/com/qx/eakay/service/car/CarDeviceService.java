package com.qx.eakay.service.car;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qx.common.base.BaseExport;
import com.qx.common.base.PageResults;
import com.qx.eakay.dao.car.CarDeviceDao;
import com.qx.eakay.export.car.CarDeviceExport;
import com.qx.eakay.export.car.CarDeviceSimExport;
import com.qx.eakay.model.car.CarDevice;
import com.qx.eakay.model.car.SimPO;
import com.qx.eakay.service.BaseService;

/**
 * 
 * @author jixf
 * @date 2015年7月6日
 */
@Service
public class CarDeviceService extends BaseService {

	@Autowired
	private CarDeviceDao carDeviceDao;

	/**
	 * 分页查询车辆信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findDevicesPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return carDeviceDao
				.findDevicesPage(jsonObject, pageSize, iDisplayStart);
	}

	/**
	 * 条件搜索车辆信息
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findDevices(JSONObject jsonObject) {
		return carDeviceDao.findDevices(jsonObject);
	}

	/**
	 * 导出车载设备
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportDevices(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new CarDeviceExport();
		List<Map<String, Object>> list = findDevices(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改车辆的ID
	 * 
	 * @param carId
	 * @param id
	 * @return
	 */
	public boolean updateCarId(Integer carId, String carTableName, Integer id) {
		return carDeviceDao.updateCarId(carId, carTableName, id);
	}

	/**
	 * 查找设备
	 * 
	 * @param carId
	 * @return
	 */
	public CarDevice carDeviceByCarId(Integer carId) {
		return carDeviceDao.carDeviceByCarId(carId);
	}

	/**
	 * 分页查询车载设备卡信息
	 * 
	 * @param jsonObject
	 * @param getiDisplayLength
	 * @param getiDisplayStart
	 * @return
	 */
	public PageResults findSimPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return carDeviceDao.findSimPage(jsonObject, pageSize, iDisplayStart);
	}

	/**
	 * 保存新增车载设备卡
	 * 
	 * @param simPO
	 * @return
	 */
	public boolean saveAdd(SimPO simPO) {
		return carDeviceDao.saveAdd(simPO);
	}

	/**
	 * 动态加载车载设备
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findCarDevice(JSONObject jsonObject) {
		return carDeviceDao.findDevices(jsonObject);
	}

	/**
	 * 条件搜索车辆信息
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findSims(JSONObject jsonObject) {
		return carDeviceDao.findSims(jsonObject);
	}

	/**
	 * 导出车载设备卡信息到Excel表格
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportSim(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new CarDeviceSimExport();
		List<Map<String, Object>> list = findSims(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 检查车载设备卡号是否存在
	 * 
	 * @param id
	 * @param simCard
	 * @return
	 */
	public boolean checkSimCard(Integer id, String simCard) {
		SimPO simPO = carDeviceDao.findBySimCard(simCard);
		if (simPO == null) {
			return true;
		}
		if (simPO.getId().equals(id)) {
			return true;
		}
		return false;
	}

	/**
	 * 删除车载设备卡
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteSim(Integer id) {
		return carDeviceDao.deleteSim(id);
	}

	/**
	 * 保存修改车载设备卡
	 * 
	 * @param simPO
	 * @return
	 */
	public boolean saveEdite(SimPO simPO) {
		return carDeviceDao.saveEdite(simPO);
	}

	/**
	 * 查找车载设备卡
	 * 
	 * @param id
	 * @return
	 */
	public SimPO getSim(Integer id) {
		return carDeviceDao.findSim(id);
	}

	/**
	 * 修改设备的安装设备卡
	 * 
	 * @param id
	 * @param deviceId
	 * @return
	 */
	public boolean updateSim(Integer id, Integer deviceId) {
		// 重置设备卡设备id
		carDeviceDao.updateDeviceId(deviceId);
		// 修改设备的安装设备卡
		return carDeviceDao.updateSim(id, deviceId);
	}

	/**
	 * 计算车载设备续航里程（100%==160km）
	 * 
	 * @param returnCarDevices
	 */
	@Transactional
	public void countSurplusKms(List<Map<String, Object>> returnCarDevices) {

		if (returnCarDevices == null || returnCarDevices.isEmpty()) {
			return;
		}

		List<Integer> deviceIds = new ArrayList<>();
		List<Integer> surplusKmss = new ArrayList<>();
		List<BigDecimal> socs = new ArrayList<>();
		for (Map<String, Object> CarDevice : returnCarDevices) {

			Integer deviceId = Integer.parseInt(CarDevice.get("id") + "");
			BigDecimal soc = new BigDecimal(CarDevice.get("soc") + "");
			// Integer surplusKms = (int) Math.ceil(soc * 160 / 100);
			Integer surplusKms = (int) Math.ceil(soc.multiply(
					new BigDecimal(1.6)).doubleValue());

			deviceIds.add(deviceId);
			surplusKmss.add(surplusKms);
			socs.add(soc);
		}

		// 批量计算车载设备续航里程
		logger.info("------>批量计算车载设备: deviceId: " + deviceIds + "SOC: "
				+ socs + "续航里程: " + surplusKmss);
		carDeviceDao.batchCountSurplusKms(surplusKmss, deviceIds);

	}

	/**
	 * 修改车辆续航里程,soc
	 * 
	 * @param normalCarDevices
	 */
	@Transactional
	public void updateCar(List<Map<String, Object>> normalCarDevices) {

		if (normalCarDevices == null || normalCarDevices.isEmpty()) {
			return;
		}

		List<Integer> carIds = new ArrayList<>();
		List<Float> surplusKmss = new ArrayList<>();
		List<Float> socs = new ArrayList<>();
		for (Map<String, Object> CarDevice : normalCarDevices) {

			Integer carId = Integer.parseInt(CarDevice.get("carId") + "");
			Float surplusKms = Float.parseFloat(CarDevice.get("surplusKms")
					+ "");
			Float soc = Float.parseFloat(CarDevice.get("soc") + "");

			carIds.add(carId);
			surplusKmss.add(surplusKms);
			socs.add(soc);
		}

		// 批量修改车辆续航里程,soc
		logger.info("------>修改车辆: carIds: " + carIds + "续航里程: surplusKms: "
				+ surplusKmss + "soc: " + socs);
		carDeviceDao.batchUpdateCar(carIds, surplusKmss, socs);

	}

}
