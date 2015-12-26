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
import com.qx.eakay.db.creator.MerchantDrawMoneyCreator;
import com.qx.eakay.model.merchant.MerchantDrawMoneyPO;

/**
 * 
 * @author jixf
 * @date 2015年7月11日
 */
@Repository
public class MerchantDrawMoneyDao extends BaseDao {

	@Resource(name = "merchant_drawmonty_select_sql")
	private JSONSqlMapping drawMoneysSQL;

	/**
	 * 分页查询提款记录
	 * 
	 * @param
	 * @return
	 */
	public PageResults findDrawMoneysPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(drawMoneysSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 条件搜索商家提款记录
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findDrawMoneys(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(drawMoneysSQL, jsonObject);
	}

	/**
	 * 新增商家提款记录
	 * 
	 * @param drawMoneyPO
	 * @return
	 */
	public Integer create(MerchantDrawMoneyPO drawMoneyPO) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(
				new MerchantDrawMoneyCreator(INSERT_SQL, drawMoneyPO),
				keyHolder);
		System.out.println(keyHolder.getKey().intValue() + "主键");
		return keyHolder.getKey().intValue();
	}

	// 新增提款记录SQL
	private static final String INSERT_SQL = "insert into merchant_draw_money (money,status,merchantId,checkMan,checkRemarks,remarks,createdTime,updatedTime) values (?,?,?,?,?,?,?,?)";

}
