package com.qx.eakay.service.car;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qx.common.base.BaseExport;
import com.qx.common.base.PageResults;
import com.qx.eakay.dao.car.CarManufacturerDao;
import com.qx.eakay.export.car.CarManufacturerExport;
import com.qx.eakay.model.car.CarManufacturerPO;
import com.qx.eakay.service.BaseService;

/**
 * 
 * @author jixf
 * @date 2015年6月30日
 */
@Service
public class CarManufacturerService extends BaseService {

	@Autowired
	private CarManufacturerDao manufacturerDao;

	/**
	 * 分页查询车辆供应商
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findManufacturerPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return manufacturerDao.findManufacturerPage(jsonObject, pageSize,
				iDisplayStart);
	}

	/**
	 * 导出车辆供应商
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportManufacturer(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new CarManufacturerExport();
		List<Map<String, Object>> list = findManufacturers(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 条件查找车辆列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findManufacturers(JSONObject jsonObject) {
		return manufacturerDao.findManufaturers(jsonObject);
	}

	/**
	 * 保存车辆生产厂商
	 * 
	 * @param carManufacturerPO
	 * @return
	 */
	public boolean saveManfacturer(CarManufacturerPO carManufacturerPO) {
		return manufacturerDao.create(carManufacturerPO) > 0;
	}

	/**
	 * 删除供应商
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteManfacturer(Integer id) {
		return manufacturerDao.delete(id);
	}

	/**
	 * 根据ID得到供应商
	 * 
	 * @param id
	 * @return
	 */
	public CarManufacturerPO getManufacturerPO(Integer id) {
		return manufacturerDao.get(id);
	}

	/**
	 * 修改车辆供应商信息
	 * 
	 * @param manufacturerPO
	 * @return
	 */
	public boolean updateManufacturer(CarManufacturerPO manufacturerPO) {
		return manufacturerDao.updateManufacturer(manufacturerPO);
	}

	/**
	 * 检测供应商名称是否重复
	 * 
	 * @param manufacturerName
	 * @param merchantId
	 * @param id
	 * @return
	 */
	public boolean checkManfacturerName(String manufacturerName,
			Integer merchantId, Integer id) {
		CarManufacturerPO manufacturerPO = manufacturerDao.findByName(
				manufacturerName, merchantId);
		if (manufacturerPO == null) {
			return true;
		}
		if (manufacturerPO.getId().equals(id)) {
			return true;
		}
		return false;
	}
}
