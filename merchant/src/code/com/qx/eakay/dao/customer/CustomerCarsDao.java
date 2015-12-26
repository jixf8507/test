package com.qx.eakay.dao.customer;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import net.sf.json.JSONObject;

import com.qx.common.base.PageResults;
import com.qx.common.dao.BaseDao;
import com.qx.common.sql.JSONSqlMapping;
import com.qx.eakay.db.creator.CustomerCarsCreator;
import com.qx.eakay.db.extractor.CustomerCarsExtractor;
import com.qx.eakay.model.customer.CustomerCarsPO;

@Repository
public class CustomerCarsDao extends BaseDao {

	@Resource(name = "customer_cars_select_sql")
	private JSONSqlMapping customerCarsSelectSQL;

	/**
	 * 分页查询客户车辆信息列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findCustomerCarsPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return this.findPageByJSONSqlMapping(customerCarsSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 条件搜索客户车辆信息列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findCustomerCars(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(customerCarsSelectSQL, jsonObject);
	}

	/**
	 * 新增保存用户
	 * 
	 * @param customerPO
	 * @return
	 */
	public Integer create(CustomerCarsPO customerCarsPO) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(
				new CustomerCarsCreator(INSERT_SQL, customerCarsPO), keyHolder);
		System.out.println(keyHolder.getKey().intValue() + "主键");
		return keyHolder.getKey().intValue();
	}

	/**
	 * 获取用户信息
	 * 
	 * @param id
	 * @return
	 */
	public CustomerCarsPO get(Integer id, Integer merchantId) {
		return this.getJdbcTemplate().query(GET_SQL,
				new Object[] { merchantId, id }, new CustomerCarsExtractor());
	}

	/**
	 * 根据车牌号码查找用户
	 * 
	 * @param phone
	 * @return
	 */
	public CustomerCarsPO getByNumber(String carNumber) {
		return this.getJdbcTemplate().query(GET_BY_NAME,
				new Object[] { carNumber }, new CustomerCarsExtractor());
	}

	/**
	 * 根据车辆识别号查找车辆
	 * 
	 * @param vin
	 * @return
	 */
	public CustomerCarsPO getByVin(String vin) {
		return this.getJdbcTemplate().query(GET_BY_VIN, new Object[] { vin },
				new CustomerCarsExtractor());
	}

	/**
	 * 修改用户车辆
	 * 
	 * @param customerPO
	 * @return
	 */
	public boolean update(CustomerCarsPO customerCarsPO) {
		Integer count = this.getJdbcTemplate().update(UPDATE_SQL,
				customerCarsPO.getCarNumber(), customerCarsPO.getCarType(),
				customerCarsPO.getCustomerId(), customerCarsPO.getVin(),
				customerCarsPO.getId());
		return count > 0;
	}

	/**
	 * 删除用户车辆
	 * 
	 * @param status
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id) {
		Integer count = this.getJdbcTemplate().update(DELETE_SQL, id);
		return count > 0;
	}

	// 插入用户车辆SQL
	private static final String INSERT_SQL = "insert into customer_cars (carNumber,carType,createdTime,customerId,vin) values (?,?,?,?,?)";
	// 车牌号码查询用户车辆的SQL
	private static final String GET_BY_NAME = "select *,'' name,'' phone,'' idCard,'' cardNO from  customer_cars where carNumber=? and flag='使用'";
	// 车牌号码查询用户车辆的SQL
	private static final String GET_BY_VIN = "select *,'' name,'' phone,'' idCard,'' cardNO from  customer_cars where vin=? and flag='使用'";
	// 根据ID查找用户车辆的SQL
	private static final String GET_SQL = "select c.*,ci.`name`,ci.phone,ci.idCard,a.cardNO from  customer_cars c inner join customer ci on c.customerId=ci.id INNER JOIN account a on a.customerId=ci.id and a.merchantId=? and a.`status`='正常' where c.id=? and flag='使用'";
	// 修改用户车辆的SQL
	private static final String UPDATE_SQL = "update customer_cars set carNumber=?,carType=?,customerId=?,vin=? where id=?";
	// 删除用户车辆的SQL
	private static final String DELETE_SQL = "update customer_cars set flag='删除' where id=?";

}
