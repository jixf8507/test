package com.qx.eakay.service.car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qx.common.base.BaseExport;
import com.qx.common.base.PageResults;
import com.qx.eakay.dao.car.CarTypeDao;
import com.qx.eakay.dao.car.CarTypeDetailDao;
import com.qx.eakay.export.car.CarTypeExport;
import com.qx.eakay.model.car.CarPO.CarStatus;
import com.qx.eakay.model.car.CarTypeDetailPO;
import com.qx.eakay.model.car.CarTypePO;
import com.qx.eakay.service.BaseService;

/**
 * 车辆型号
 * 
 * @author Administrator
 * 
 */
@Service
public class CarTypeService extends BaseService {

	@Autowired
	private CarTypeDao carTypeDao;
	@Autowired
	private CarTypeDetailDao carTypeDetailDao;

	/**
	 * 分页查询车辆型号
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findCarTypePage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return carTypeDao.findCarTypePage(jsonObject, pageSize, iDisplayStart);
	}

	/**
	 * 导出车辆供应商
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportCarType(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new CarTypeExport();
		List<Map<String, Object>> list = findCarTypes(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取商家的车辆型号列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findCarTypes(JSONObject jsonObject) {
		return carTypeDao.findList(jsonObject);
	}

	/**
	 * 新增车辆型号
	 * 
	 * @param typePO
	 * @return
	 */
	@Transactional
	public boolean createCarType(CarTypePO typePO) {
		int typeId = carTypeDao.create(typePO);
		carTypeDetailDao.batchCreate(typePO.getDetailPOs(), typeId);
		return typeId > 0;
	}

	/**
	 * 更改车辆型号
	 * 
	 * @param typePO
	 * @return
	 */
	@Transactional
	public boolean updateCarType(CarTypePO typePO) {
		carTypeDetailDao.delete(typePO.getId());
		carTypeDetailDao.batchCreate(typePO.getDetailPOs(), typePO.getId());
		return carTypeDao.updateCarType(typePO);
	}

	/**
	 * 删除车辆型号
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public boolean deleteCarType(Integer id) {
		carTypeDetailDao.delete(id);
		return carTypeDao.delete(id);
	}

	/**
	 * 获取车辆型号
	 * 
	 * @param id
	 * @return
	 */
	public CarTypePO getCarType(Integer id) {
		CarTypePO carTypePO = carTypeDao.get(id);
		List<Map<String, Object>> list = carTypeDetailDao.findDetails(id);
		for (Map<String, Object> map : list) {
			CarTypeDetailPO detailPO = new CarTypeDetailPO();
			detailPO.setComponent(map.get("component") + "");
			detailPO.setStatus(map.get("status") + "");
			detailPO.setDescribe(map.get("describe") + "");
			carTypePO.getDetailPOs().add(detailPO);
		}
		return carTypePO;
	}

	/**
	 * 得到车辆型号下的颜色列表
	 * 
	 * @param typeId
	 * @return
	 */
	public List<Map<String, Object>> findTypeColors(Integer typeId) {
		List<Map<String, Object>> colors = new ArrayList<>();
		if (typeId == null) {
			return colors;
		}
		CarTypePO typePO = carTypeDao.get(typeId);
		String color = typePO.getColor();
		String[] arr = color.split(",");
		for (String s : arr) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("value", s);
			colors.add(map);
		}
		return colors;
	}
	
	/**
	 * 得到车辆下的指定类型，这里写死了，以后可以扩展。
	 * @param typeId
	 * @return
	 */
	public List<Map<String, Object>> findTypeStatus(Integer typeId){
		List<Map<String, Object>> status = new ArrayList<>();
		Map<String, Object> map1 =new HashMap<String,Object>();
		Map<String, Object> map2 =new HashMap<String,Object>();
		map1.put("value", CarStatus.其他);
		map2.put("value",  CarStatus.空闲);
		status.add(map1);
		status.add(map2);
		return status;
	}

}
