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
import com.qx.eakay.db.creator.CarTypeCreator;
import com.qx.eakay.db.extractor.CarTypeExtractor;
import com.qx.eakay.model.car.CarTypePO;

/**
 * 车辆型号数据库操作方法
 * 
 * @author jixf
 * @date 2015年7月1日
 */
@Repository
public class CarTypeDao extends BaseDao {

	@Resource(name = "car_type_select_sql")
	private JSONSqlMapping carTypeSelectSQL;

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
		return this.findPageByJSONSqlMapping(carTypeSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 查找车辆型号列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findList(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(carTypeSelectSQL, jsonObject);
	}

	/**
	 * 创建车辆型号
	 * 
	 * @param typePO
	 * @return
	 */
	public int create(CarTypePO typePO) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(new CarTypeCreator(INSERT_SQL, typePO),
				keyHolder);
		System.out.println(keyHolder.getKey().intValue() + "主键");
		return keyHolder.getKey().intValue();
	}

	/**
	 * 删除车辆型号
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id) {
		int count = this.getJdbcTemplate().update(DELETE_SQL, id);
		return count > 0;
	}

	/**
	 * 修改车辆型号
	 * 
	 * @param carManufacturerPO
	 * @return
	 */
	public boolean updateCarType(CarTypePO typePO) {
		int count = this.getJdbcTemplate().update(UPDATE_SQL,
				typePO.getTypeName(), typePO.getCarManufacturerId(),
				typePO.getSeatCount(), typePO.getColor(), typePO.getEnergy(),
				typePO.getId());
		return count > 0;
	}

	/**
	 * 根据ID查找供应商
	 * 
	 * @param id
	 * @return
	 */
	public CarTypePO get(Integer id) {
		return this.getJdbcTemplate().query(GET_SQL, new Object[] { id },
				new CarTypeExtractor());
	}

	// 插入车辆型号SQL
	private static final String INSERT_SQL = "insert into car_type (typeName,carManufacturerId,seatCount,color,energy) values (?,?,?,?,?)";
	// 插入车辆型号SQL
	private static final String UPDATE_SQL = "update car_type set typeName=?,carManufacturerId=?,seatCount=?,color=?,energy=? where id=?";
	// 根据名称查找型号的SQL
	private static final String GET_SQL = "select * from  car_type where id=?";
	// 删除型号的SQL
	private static final String DELETE_SQL = "delete from  car_type where id=?";

}
