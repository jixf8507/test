package com.qx.eakay.service.car;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qx.eakay.dao.car.CarDetailDao;
import com.qx.eakay.service.BaseService;

/**
 * 
 * @author jixf
 * @date 2015年7月14日
 */
@Service
public class CarDetailService extends BaseService {

	@Autowired
	private CarDetailDao carDetailDao;

	/**
	 * 查询车辆详细信息
	 * 
	 * @param carId
	 * @return
	 */
	public List<Map<String, Object>> findDetails(Integer carId) {
		return carDetailDao.findDetails(carId);
	}

}
