package com.qx.eakay.dao.car;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.qx.common.base.PageResults;
import com.qx.common.dao.BaseDao;
import com.qx.common.sql.JSONSqlMapping;
import com.qx.eakay.db.creator.CarManufacturerCreator;
import com.qx.eakay.db.extractor.CarManufacturerExtractor;
import com.qx.eakay.model.car.CarManufacturerPO;

/**
 * 车辆生产厂商数据库操作方法
 * 
 * @author jixf
 * @date 2015年6月30日
 */
@Repository
public class CarManufacturerDao extends BaseDao {

	@Resource(name = "car_manufacturer_select_sql")
	private JSONSqlMapping manufacturerSelectSql;

	/**
	 * 分页查询车辆供应商列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findManufacturerPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return this.findPageByJSONSqlMapping(manufacturerSelectSql, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 条件查询车辆供应商
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findManufaturers(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(manufacturerSelectSql, jsonObject);
	}

	/**
	 * 创建车辆生产厂商
	 * 
	 * @param carManufacturerPO
	 * @return
	 */
	public int create(CarManufacturerPO carManufacturerPO) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(
				new CarManufacturerCreator(INSERT_SQL, carManufacturerPO),
				keyHolder);
		System.out.println(keyHolder.getKey().intValue() + "主键");
		return keyHolder.getKey().intValue();
	}

	/**
	 * 删除车辆供应商
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id) {
		int count = this.getJdbcTemplate().update(DELETE_SQL, id);
		return count > 0;
	}

	/**
	 * 修改供应商信息
	 * 
	 * @param carManufacturerPO
	 * @return
	 */
	public boolean updateManufacturer(CarManufacturerPO carManufacturerPO) {
		int count = this.getJdbcTemplate().update(UPDATE_SQL,
				carManufacturerPO.getManufacturerName(),
				carManufacturerPO.getFullName(),
				carManufacturerPO.getAddress(), carManufacturerPO.getLinkman(),
				carManufacturerPO.getLinkphone(), carManufacturerPO.getEmail(),
				carManufacturerPO.getId());
		return count > 0;
	}

	/**
	 * 根据名称查找供应商
	 * 
	 * @param ManufacturerMame
	 * @param merchantId
	 * @return
	 */
	public CarManufacturerPO findByName(String manufacturerName,
			Integer merchantId) {
		return this.getJdbcTemplate().query(SELECT_BY_NAME_SQL,
				new Object[] { manufacturerName, merchantId },
				new CarManufacturerExtractor());
	}

	/**
	 * 根据ID查找供应商
	 * 
	 * @param id
	 * @return
	 */
	public CarManufacturerPO get(Integer id) {
		return this.getJdbcTemplate().query(GET_SQL, new Object[] { id },
				new CarManufacturerExtractor());
	}

	// 插入车辆生产厂商SQL
	private static final String INSERT_SQL = "insert into car_manufacturer (manufacturerName,fullName,address,linkman,linkphone,merchantId,email) values (?,?,?,?,?,?,?)";

	// 插入车辆生产厂商SQL
	private static final String UPDATE_SQL = "update car_manufacturer set manufacturerName=?,fullName=?,address=?,linkman=?,linkphone=?,email=? where id=?";

	// 根据名称查找供应商的SQL
	private static final String GET_SQL = "select * from  car_manufacturer where id=?";

	// 根据ID查找供应商的SQL
	private static final String SELECT_BY_NAME_SQL = "select * from  car_manufacturer where manufacturerName=? and merchantId=?";

	// 删除供应商的SQL
	private static final String DELETE_SQL = "delete from  car_manufacturer where id=?";

}
