package com.qx.eakay.service.car;

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
import com.qx.eakay.dao.car.CarDao;
import com.qx.eakay.dao.car.CarDetailDao;
import com.qx.eakay.dao.car.CarTypeDetailDao;
import com.qx.eakay.dao.customer.CustomerCarsDao;
import com.qx.eakay.export.car.CarExport;
import com.qx.eakay.model.car.CarDetailPO;
import com.qx.eakay.model.car.CarPO;
import com.qx.eakay.model.car.CarPO.CarStatus;
import com.qx.eakay.model.customer.CustomerCarsPO;
import com.qx.eakay.service.BaseService;

/**
 * 
 * @author Administrator
 * 
 */
@Service
public class CarService extends BaseService {

	@Autowired
	private CarDao carDao;
	@Autowired
	private CarTypeDetailDao carTypeDetailDao;
	@Autowired
	private CarDetailDao carDetailDao;
	@Autowired
	private CustomerCarsDao carsDao;

	/**
	 * 分页查询车辆信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findCarsPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return carDao.findCarsPage(jsonObject, pageSize, iDisplayStart);
	}

	/**
	 * 导出车辆信息列表
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportCars(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new CarExport();
		List<Map<String, Object>> list = findCars(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 条件搜索车辆信息
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findCars(JSONObject jsonObject) {
		return carDao.findCars(jsonObject);
	}

	/**
	 * 新增车辆
	 * 
	 * @param carPO
	 * @return
	 */
	@Transactional
	public boolean createCar(CarPO carPO) {
		// 添加车辆
		Integer carId = carDao.create(carPO);
		List<Map<String, Object>> typeDetails = carTypeDetailDao
				.findDetails(carPO.getCarTypeId());
		List<CarDetailPO> carDetails = new ArrayList<>();
		for (Map<String, Object> typeDetail : typeDetails) {
			CarDetailPO carDetailPO = new CarDetailPO();
			carDetailPO.setCarId(carId);
			carDetailPO.setComponent(typeDetail.get("component") + "");
			carDetailPO.setStatus(typeDetail.get("status") + "");
			carDetailPO.setDescribe(typeDetail.get("describe") + "");
			carDetails.add(carDetailPO);
		}
		// 创建车辆的审核项目
		return carDetailDao.batchCreate(carDetails);
	}

	/**
	 * 删除车辆
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteCar(Integer id) {
		return carDao.delete(id);
	}

	/**
	 * 批量发布车辆
	 * 
	 * @param flag
	 * @param carIds
	 * @return
	 */
	public boolean batchPublish(List<Integer> carIds) {
		return carDao.batchPublish("正常", carIds);
	}

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 */
	public CarPO getCar(Integer id) {
		return carDao.get(id);
	}

	public boolean updateCar(CarPO carPO) {
		return carDao.update(carPO);
	}

	/**
	 * 检测车牌号码是否已存在
	 * 
	 * @param carNumber
	 * @return
	 */
	public boolean checkCarNumber(String carNumber, Integer id) {
		CarPO carPO = carDao.getByNumber(carNumber);
		if (carPO == null) {
			return checkCustomerCarsName(carNumber, id);
		}
		if (carPO.getId().equals(id)) {
			return true;
		}

		return false;
	}

	public boolean checkCarVin(String vin, Integer id) {
		CarPO carPO = carDao.getByVin(vin);
		if (carPO == null) {
			return checkCustomerCarsVin(vin, id);
		}
		if (carPO.getId().equals(id)) {
			return true;
		}

		return false;
	}

	/**
	 * 检测用户车辆的车牌号码
	 * 
	 * @param carNumber
	 * @param id
	 * @return
	 */
	private boolean checkCustomerCarsName(String carNumber, Integer id) {
		CustomerCarsPO customerCarsPO = carsDao.getByNumber(carNumber);
		if (customerCarsPO == null) {
			return true;
		}
		if (customerCarsPO.getId().equals(id)) {
			return true;
		}
		return false;
	}

	/**
	 * 检测用户车辆的车牌号码
	 * 
	 * @param carNumber
	 * @param id
	 * @return
	 */
	private boolean checkCustomerCarsVin(String vin, Integer id) {
		CustomerCarsPO customerCarsPO = carsDao.getByVin(vin);
		if (customerCarsPO == null) {
			return true;
		}
		if (customerCarsPO.getId().equals(id)) {
			return true;
		}
		return false;
	}

	/**
	 * 修改车辆的当前状态
	 * 
	 * @param status
	 * @param id
	 * @return
	 */
	public boolean updateStatus(CarStatus status, Integer id) {
		return carDao.updateStatus(status, id);
	}
	
	/**
	 * 修改车辆的当前状态和描述
	 * 
	 * @param status
	 * @param id
	 * @return
	 */
	public boolean updateStatusAndDescribe(CarPO carPO, Integer id) {
		return carDao.updateStatusAndDescribe(carPO, id);
	}

	/**
	 * 查找没有安装车载设备的车辆
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findNoDeviceCars(JSONObject jsonObject) {
		return carDao.findNoDeviceCars(jsonObject);
	}

	/**
	 * 查找租赁套餐的车辆
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findPriceTypeCars(JSONObject jsonObject) {
		return carDao.findPriceTypeCars(jsonObject);
	}

	/**
	 * 条件搜索车辆当前位置
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findCarPoints(JSONObject jsonObject) {
		return carDao.findCarPoints(jsonObject);
	}

	/**
	 * 分页查询车辆状态信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findOnlyCarsPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return carDao.findOnlyCarsPage(jsonObject, pageSize, iDisplayStart);
	}


}
