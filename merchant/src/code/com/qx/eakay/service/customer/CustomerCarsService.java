package com.qx.eakay.service.customer;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qx.common.base.BaseExport;
import com.qx.common.base.PageResults;
import com.qx.eakay.dao.customer.CustomerCarsDao;
import com.qx.eakay.export.customer.CustomerCarsExport;
import com.qx.eakay.model.customer.CustomerCarsPO;
import com.qx.eakay.service.BaseService;

/**
 * 
 * @author jixf
 * @date 2015年7月20日
 */
@Service
public class CustomerCarsService extends BaseService {

	@Autowired
	private CustomerCarsDao carsDao;

	/**
	 * 分页查询用户信息列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findCustomerCarsPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return carsDao
				.findCustomerCarsPage(jsonObject, pageSize, iDisplayStart);
	}

	/**
	 * 条件搜索用户信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public List<Map<String, Object>> findCustomerCars(JSONObject jsonObject) {
		return carsDao.findCustomerCars(jsonObject);
	}

	/**
	 * 导出用户车辆信息
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportCustomerCars(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new CustomerCarsExport();
		List<Map<String, Object>> list = findCustomerCars(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 新增用户车辆
	 * 
	 * @param customerCarsPO
	 * @return
	 */
	public boolean createCustomerCars(CustomerCarsPO customerCarsPO) {
		return carsDao.create(customerCarsPO) > 0;
	}

	/**
	 * 根据ID得到用户车辆
	 * 
	 * @param id
	 * @return
	 */
	public CustomerCarsPO getCustomerCars(Integer id, Integer merchantId) {
		return carsDao.get(id, merchantId);
	}

	/**
	 * 修改用户车辆信息
	 * 
	 * @param customerCarsPO
	 * @return
	 */
	public boolean updateCustomerCars(CustomerCarsPO customerCarsPO) {
		return carsDao.update(customerCarsPO);
	}

	/**
	 * 删除用户车辆信息
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteCustomerCars(Integer id) {
		return carsDao.delete(id);
	}

}
