package com.qx.eakay.dao.merchant;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.qx.common.base.PageResults;
import com.qx.common.base.SqlHelp;
import com.qx.common.dao.BaseDao;
import com.qx.common.sql.JSONSqlMapping;
import com.qx.common.tools.StringTools;
import com.qx.eakay.db.creator.SiteCreator;
import com.qx.eakay.db.extractor.SiteExtractor;
import com.qx.eakay.model.merchant.SitePO;

@Repository
public class SiteDao extends BaseDao {

	@Resource(name = "site_select_sql")
	private JSONSqlMapping siteSelectSQL;


	/**
	 * 分页查询租赁点
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findSitePage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(siteSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}
	
	
	
	@Resource(name = "site_select_by_type_sql")
	private JSONSqlMapping siteSelectByTypeSQL;
	/**
	 * 分页查询type为租赁点（type & 1 ==1）的租赁点
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findSitePageByType(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(siteSelectByTypeSQL, jsonObject,
				pageSize, iDisplayStart);
	}
	

	/**
	 * 条件查询租赁点
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findSites(JSONObject jsonObject) {		
		return this.findListByJSONSqlMapping(siteSelectSQL, jsonObject);
	}

	@Resource(name = "site_cost_statistics_select_sql")
	private JSONSqlMapping siteCostStatisticsSelectSQL;

	/**
	 * 分页查询统计租赁点收费情况
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
//	public PageResults findSiteCostPage(JSONObject jsonObject, int pageSize,
//			int iDisplayStart) {
//		return this.findPageByJSONSqlMapping(siteCostStatisticsSelectSQL,
//				jsonObject, pageSize, iDisplayStart);
//	}

	
	public PageResults findSiteCostPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		SqlHelp sqlHelp = pageSqlHelp(jsonObject);
		return this.findPageBySqlHelp(sqlHelp, pageSize, iDisplayStart);
	}
	
	// 查询统计租赁点收费情况SQL
	private static final String SITE_COST_STATISTICS_SELECT_SQL = " select s.id,s.type,s.siteName,g.getCount,r.returnCount,r.totalCost from site s "
			+ "LEFT JOIN (select o.relityGetSiteId,count(*) getCount from `order` o where (o.orderStatus='已取车' or o.orderStatus='已还车' or o.orderStatus='已付费') and o.orderType=1 ";
			
	
	private SqlHelp pageSqlHelp(JSONObject jsonObject) {
		String sql = SITE_COST_STATISTICS_SELECT_SQL;
		Object[] args = new Object[] {  };
		
		if (StringTools.isNotBlank(jsonObject.get("beginTime"))) {
			sql += " and o.realityGetTime >= ? ";
			args = ArrayUtils.add(args, jsonObject.get("beginTime"));
		}
		if (StringTools.isNotBlank(jsonObject.get("endTime"))) {
			sql += " and o.realityGetTime <= ? ";
			args = ArrayUtils.add(args, jsonObject.get("endTime"));
		}
		
		sql += "group BY o.relityGetSiteId) g on g.relityGetSiteId =s.id "
			+ "LEFT JOIN  (select o.relityReturnSiteId,count(*) returnCount,SUM(totalCost) totalCost from `order` o "
			+ "where (o.orderStatus='已还车' or o.orderStatus='已付费') and o.orderType=1 ";
		
		if (StringTools.isNotBlank(jsonObject.get("beginTime"))) {
			sql += " and o.realityReturnTime >= ? ";
			args = ArrayUtils.add(args, jsonObject.get("beginTime"));
		}
		if (StringTools.isNotBlank(jsonObject.get("endTime"))) {
			sql += " and o.realityReturnTime <= ? ";
			args = ArrayUtils.add(args, jsonObject.get("endTime"));
		}
		
		sql += "group BY o.relityReturnSiteId) r on r.relityReturnSiteId=s.id "
			+ "where 1=1 ";
		
		if (StringTools.isNotBlank(jsonObject.get("merchantId"))) {
			sql += " and s.merchantId = ? ";
			args = ArrayUtils.add(args, jsonObject.get("merchantId"));
		}
		if (StringTools.isNotBlank(jsonObject.get("siteName"))) {
			sql += " and s.siteName like ? ";
			args = ArrayUtils.add(args, "%"+jsonObject.get("siteName")+"%");
		}
		
		sql += " order by s.id desc ";
		SqlHelp sqlHelp = new SqlHelp(new StringBuffer(sql), args);
		return sqlHelp;
	}

	public List<Map<String, Object>> statisticsGetCount(JSONObject jsonObject) {
		SqlHelp sqlHelp = pageSqlHelp(jsonObject);
		return this.findListBySQLHelp(sqlHelp);
	}
	
	
	/**
	 * 条件查询统计租赁点收费情况
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findSiteCosts(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(siteCostStatisticsSelectSQL,
				jsonObject);
	}
	
	

	/**
	 * 新增租赁点
	 * 
	 * @param sitePO
	 * @return
	 */
	public Integer create(SitePO sitePO) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(new SiteCreator(INSERT_SQL, sitePO),
				keyHolder);
		System.out.println(keyHolder.getKey().intValue() + "主键");
		return keyHolder.getKey().intValue();
	}

	/**
	 * 根据ID查找租赁点
	 * 
	 * @param id
	 * @return
	 */
	public SitePO get(Integer id) {
		return this.getJdbcTemplate().query(GET_SQL, new Object[] { id },
				new SiteExtractor());
	}

	/**
	 * 根据名称查找租赁点
	 * 
	 * @param carNumber
	 * @return
	 */
	public SitePO getByName(String siteName, Integer merchantId) {
		return this.getJdbcTemplate().query(GET_BY_SITENAME,
				new Object[] { siteName, merchantId }, new SiteExtractor());
	}

	/**
	 * 删除租赁点
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id) {
		int count = this.getJdbcTemplate().update(DELETE_SQL, id);
		return count > 0;
	}

	/**
	 * 更改租赁点
	 * 
	 * @param sitePO
	 * @return
	 */
	public boolean update(SitePO sitePO) {
		int count = this.getJdbcTemplate().update(UPDATE_SQL,
				sitePO.getSiteName(), sitePO.getPhone(), sitePO.getAddress(),
				sitePO.getPrincipal(), sitePO.getLat(), sitePO.getLng(),
				sitePO.getType(), sitePO.getImgUrl(), sitePO.getProvince(),
				sitePO.getCity(), sitePO.getId());
		return count > 0;
	}

	// 插入租赁点SQL
	private static final String INSERT_SQL = "insert into site (siteName,phone,address,flag,principal,lat,lng,merchantId,createdTime,updatedTime,type,imgUrl,province,city) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	// 根据名称查找租赁点的SQL
	private static final String GET_BY_SITENAME = "select * from  site where siteName=? and merchantId=? and flag='正常'";
	// 根据ID查找租赁点的SQL
	private static final String GET_SQL = "select * from  site where id=?";
	// 删除租赁点的SQL
	private static final String DELETE_SQL = "update site set flag='删除' where id=?";
	// 更改租赁点SQL
	private static final String UPDATE_SQL = "update site set siteName=?,phone=?,address=?,principal=?,lat=?,lng=?,type=?,imgUrl=?,province=?,city=? where id=?";

}
