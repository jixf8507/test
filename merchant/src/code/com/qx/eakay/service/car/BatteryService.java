package com.qx.eakay.service.car;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qx.common.base.BaseExport;
import com.qx.common.base.PageResults;
import com.qx.eakay.dao.car.CarBatteryDao;
import com.qx.eakay.export.car.CarBatteryExport;
import com.qx.eakay.model.car.CarBatteryPO;
import com.qx.eakay.service.BaseService;

/**
 * 
 * @author jixf
 * @date 2015年7月15日
 */
@Service
public class BatteryService extends BaseService {

	@Autowired
	private CarBatteryDao batteryDao;

	/**
	 * 分页查找电池信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findBatteryPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return batteryDao.findBatteryPage(jsonObject, pageSize, iDisplayStart);
	}

	/**
	 * 导出车辆电池信息列表
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportBatterys(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new CarBatteryExport();
		List<Map<String, Object>> list = findBatterys(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 条件搜索车辆电池信息
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findBatterys(JSONObject jsonObject) {
		return batteryDao.findBatterys(jsonObject);
	}

	/**
	 * 得到电池信息
	 * 
	 * @param id
	 * @return
	 */
	public CarBatteryPO getBattery(Integer id) {
		return batteryDao.get(id);
	}

	/**
	 * 创建电池
	 * 
	 * @param batteryPO
	 * @return
	 */
	public boolean createBattery(CarBatteryPO batteryPO) {
		return batteryDao.create(batteryPO) > 0;
	}

	/**
	 * 修改电池信息
	 * 
	 * @param batteryPO
	 * @return
	 */
	public boolean updateBattery(CarBatteryPO batteryPO) {
		return batteryDao.update(batteryPO);
	}

	/**
	 * 删除电池
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteBattery(Integer id) {
		return batteryDao.delete(id);
	}

	/**
	 * 检测电池VIN码是否重复
	 * 
	 * @param vin
	 * @param id
	 * @return
	 */
	public boolean checkVin(String vin, Integer id) {
		CarBatteryPO batteryPO = batteryDao.findByVIN(vin);
		if (batteryPO == null) {
			return true;
		}
		if (batteryPO.getId().equals(id)) {
			return true;
		}
		return false;
	}

}
