package com.qx.eakay.service.parking;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qx.common.base.BaseExport;
import com.qx.common.base.PageResults;
import com.qx.eakay.dao.parking.ParkingSpaceDao;
import com.qx.eakay.export.parking.ParkingSpaceExport;
import com.qx.eakay.model.parking.ParkingSpacePO;
import com.qx.eakay.service.BaseService;
import com.qx.eakay.util.MSG;

/**
 * 
 * @author jixf
 * @date 2015年7月16日
 */
@Service
public class ParkingSpaceService extends BaseService {

	@Autowired
	private ParkingSpaceDao spaceDao;

	/**
	 * 分页查找充电设备
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findSpacePage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return spaceDao.findSpacePage(jsonObject, pageSize, iDisplayStart);
	}

	/**
	 * 导出充电设备列表
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportSpace(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new ParkingSpaceExport();
		List<Map<String, Object>> list = findSpace(jsonObject);
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
	public List<Map<String, Object>> findSpace(JSONObject jsonObject) {
		return spaceDao.findSpace(jsonObject);
	}

	/**
	 * 保存新增停车位
	 * 
	 * @param spacePO
	 * @param jsonObject
	 * @return
	 */
	@Transactional
	public MSG createSpace(ParkingSpacePO spacePO, JSONObject jsonObject) {
		MSG msg = new MSG();
		// 检查停车位是否已存在
		boolean flag = checkOnly(jsonObject);
		if (flag) {
			if("1".equals(spacePO.getDeviceTypeNo())){
				spacePO.setGrooveNo("-1");
			}else{
				String no = spacePO.getFactoryNo().split("-")[1];
				spacePO.setGrooveNo((Integer.parseInt(no)-1)+"");
			}
			// 保存新增停车位
			msg.setSuccess(spaceDao.create(spacePO) > 0);
		} else {
			return new MSG("停车位已重复", 1);
		}
		return msg;
	}

	/**
	 * 获取停车位信息
	 * 
	 * @param spacePO
	 * @return
	 */
	public ParkingSpacePO getSpace(Integer id) {
		return spaceDao.get(id);
	}

	/**
	 * 保存停车位修改
	 * 
	 * @param spacePO
	 * @return
	 */
	public MSG updateSpace(ParkingSpacePO spacePO,JSONObject jsonObject) {
		MSG msg = new MSG();
		// 检查停车位是否已存在
		boolean flag = checkOnly(jsonObject);
		if (flag) {
			if("1".equals(spacePO.getDeviceTypeNo())){
				spacePO.setGrooveNo("-1");
			}else{
				String no = spacePO.getFactoryNo().split("-")[1];
				spacePO.setGrooveNo((Integer.parseInt(no)-1)+"");
			}
			// 保存新增停车位
			msg.setSuccess(spaceDao.update(spacePO));
		} else {
			return new MSG("停车位已重复", 1);
		}
		return msg;
	}

	/**
	 * 删除停车位信息
	 * 
	 * @param spacePO
	 * @return
	 */
	public boolean deleteSpace(Integer id) {
		return spaceDao.delete(id);
	}

	/**
	 * 检查停车位是否已存在
	 * 
	 * @param paraData
	 * @return
	 */
	public boolean checkOnly(JSONObject jsonObject) {
		List<Map<String, Object>> list = spaceDao.findSpace(jsonObject);
		if (list == null || list.size() <= 0) {
			return true;
		}
		if (list.get(0).get("id").equals(jsonObject.getString("id"))) {
			return true;
		}
		return false;
	}
}
