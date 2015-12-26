package com.qx.eakay.dao.stake;

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
import com.qx.eakay.db.creator.StakeSiteAreaCreator;
import com.qx.eakay.db.extractor.StakeSiteAreaExtractor;
import com.qx.eakay.model.stake.StakeSiteAreaPO;

/**
 * 
 * @author jixf
 * @date 2015年7月16日
 */
@Repository
public class StakeSiteAreaDao extends BaseDao {

	@Resource(name = "stake_sitearea_select_sql")
	private JSONSqlMapping areaSelectSQL;

	/**
	 * 分页查找充电设备列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findAreaPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(areaSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 条件查找充电设备列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findAreas(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(areaSelectSQL, jsonObject);
	}

	/**
	 * 根据充电站查询充电区域
	 * 
	 * @param siteId
	 * @return
	 */
	public List<Map<String, Object>> findAreas(String siteCode) {
		return this.findListBySQL(SELECT_SQL, new Object[] { siteCode });
	}

	private static final String SELECT_SQL = "select id,area_code,area_name from stake_siteArea where site_code=?";

	/**
	 * 创建充电设备
	 * 
	 * @param carPO
	 * @return
	 */
	public int create(StakeSiteAreaPO areaPO) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(
				new StakeSiteAreaCreator(INSERT_SQL, areaPO), keyHolder);
		System.out.println(keyHolder.getKey().intValue() + "主键");
		return keyHolder.getKey().intValue();
	}

	/**
	 * 根据ID查找充电设备
	 * 
	 * @param id
	 * @return
	 */
	public StakeSiteAreaPO get(Integer id) {
		return this.getJdbcTemplate().query(GET_SQL, new Object[] { id },
				new StakeSiteAreaExtractor());
	}

	/**
	 * 根据设备编号码查找充电设备
	 * 
	 * @param factoryNo
	 * @return
	 */
	public StakeSiteAreaPO findByCode(String areaCode, String siteCode) {
		return this.getJdbcTemplate().query(GET_BY_CODE,
				new Object[] { areaCode, siteCode },
				new StakeSiteAreaExtractor());
	}

	/**
	 * 删除充电设备
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id) {
		int count = this.getJdbcTemplate().update(DELETE_SQL, id);
		return count > 0;
	}

	/**
	 * 更改充电设备
	 * 
	 * @param batteryPO
	 * @return
	 */
	public boolean update(StakeSiteAreaPO areaPO) {
		int count = this.getJdbcTemplate().update(UPDATE_SQL,
				areaPO.getSiteCode(), areaPO.getAreaCode(),
				areaPO.getAreaName(), areaPO.getId());
		return count > 0;
	}

	// 插入充电设备SQL
	private static final String INSERT_SQL = "insert into stake_sitearea (site_code,area_code,area_name,created_time,updated_time,flag) values (?,?,?,?,?,'新增')";
	// 根据车牌查找充电设备的SQL
	private static final String GET_BY_CODE = "select * from stake_sitearea where area_code=? and site_code=?";
	// 根据ID查找充电设备的SQL
	private static final String GET_SQL = "select * from stake_sitearea where id=?";
	// 删除充电设备的SQL
	private static final String DELETE_SQL = "delete from  stake_sitearea where id=?";
	// 修改充电设备的SQL
	private static final String UPDATE_SQL = "update stake_sitearea set site_code =?,area_code=?,area_name=?,updated_time=now() where id =?";

}
