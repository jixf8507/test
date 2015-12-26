package com.qx.eakay.dao.parking;

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
import com.qx.eakay.db.creator.ParkingSpaceCreator;
import com.qx.eakay.db.extractor.ParkingSpaceExtrator;
import com.qx.eakay.model.parking.ParkingSpacePO;

/**
 * 
 * @author jixf
 * @date 2015年7月16日
 */
@Repository
public class ParkingSpaceDao extends BaseDao {

	@Resource(name = "parking_space_select_sql")
	private JSONSqlMapping spaceSelectSQL;

	/**
	 * 分页查找充电收费记录列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findSpacePage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(spaceSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 条件查找充电收费记录列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findSpace(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(spaceSelectSQL, jsonObject);
	}

	/**
	 * 创建停车位
	 * 
	 * @param carPO
	 * @return
	 */
	public Integer create(ParkingSpacePO spacePO) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(
				new ParkingSpaceCreator(INSERT_SQL, spacePO), keyHolder);
		System.out.println(keyHolder.getKey().intValue() + "主键");
		return keyHolder.getKey().intValue();
	}

	/**
	 * 根据ID查找停车位
	 * 
	 * @param id
	 * @return
	 */
	public ParkingSpacePO get(Integer id) {
		return this.getJdbcTemplate().query(GET_SQL, new Object[] { id },
				new ParkingSpaceExtrator());
	}

	/**
	 * 删除停车位
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id) {
		int count = this.getJdbcTemplate().update(DELETE_SQL, id);
		return count > 0;
	}

	/**
	 * 更改停车位
	 * 
	 * @param batteryPO
	 * @return
	 */
	public boolean update(ParkingSpacePO spacePO) {
		int count = this.getJdbcTemplate().update(UPDATE_SQL,
				spacePO.getParkingId(), spacePO.getSpaceNO(),
				spacePO.getArea(), spacePO.getAddress(), spacePO.getElectric(),
				spacePO.getFactoryNo(), spacePO.getPosition(),
				spacePO.getGrooveNo(), spacePO.getIsCharge(),
				spacePO.getIsLock(), spacePO.getLng(), spacePO.getLat(),
				spacePO.getId());
		return count > 0;
	}

	// 插入停车位SQL
	private static final String INSERT_SQL = "insert into parking_space (parkingId,spaceNO,area,lng,lat,address,electric,"
			+ "isCharge,status,factoryNo,position,grooveNo,isLock) values (?,?,?,?,?,?,?,?,'空闲',?,?,?,?)";
	// 根据ID查找停车位的SQL
	private static final String GET_SQL = "select ps.*,sd.parkType,s.siteName,sd.deviceNo,sd.nameplate,sd.deviceTypeNo from parking_space ps "
			+ "inner join stake_devicename sd on (ps.factoryNo = sd.factoryNo) left join site s on (s.id = ps.parkingId) where ps.id =?";
	// 删除停车位的SQL
	private static final String DELETE_SQL = "delete from parking_space where id =?";
	// 修改停车位的SQL
	private static final String UPDATE_SQL = "update parking_space set parkingId =?,spaceNO=?,"
			+ "area=?,address=?,electric=?,factoryNo=?,position=?,grooveNo=?,isCharge=?,isLock=?,lng=?,lat=? where id =?";

}
