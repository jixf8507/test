package com.qx.eakay.dao.merchant;

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
import com.qx.eakay.db.creator.MerchantAccountRecordCreator;
import com.qx.eakay.model.merchant.MerchantAccountRecordPO;

/**
 * 
 * @author jixf
 * @date 2015年7月9日
 */
@Repository
public class MerchantAccountRecordDao extends BaseDao {

	@Resource(name = "merchant_accountrecord_select_sql")
	private JSONSqlMapping accountRecordSelectSQL;

	/**
	 * 分页查找商家账户交易明细记录列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findAccountRecordPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return this.findPageByJSONSqlMapping(accountRecordSelectSQL,
				jsonObject, pageSize, iDisplayStart);
	}

	/**
	 * 条件搜索商家账户交易明细记录
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findAccountRecord(JSONObject jsonObject) {
		return this
				.findListByJSONSqlMapping(accountRecordSelectSQL, jsonObject);
	}

	/**
	 * 创建商家账户交易明细
	 * 
	 * @param accountRecordPO
	 * @return
	 */
	public Integer create(MerchantAccountRecordPO accountRecordPO) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(
				new MerchantAccountRecordCreator(INSERT_SQL, accountRecordPO),
				keyHolder);
		System.out.println(keyHolder.getKey().intValue() + "主键");
		return keyHolder.getKey().intValue();
	}

	// 插入商家帐号SQL
	private static final String INSERT_SQL = "insert into merchant_account_recode (merchantId,oldBalance,addBalance,newBalance,createdTime,type,transactUser,siteId) values(?,?,?,?,?,?,?,?)";

}
