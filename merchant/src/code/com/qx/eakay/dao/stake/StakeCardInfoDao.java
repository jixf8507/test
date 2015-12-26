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
import com.qx.eakay.db.creator.StakeCardInfoCreator;
import com.qx.eakay.db.extractor.StakeCardInfoExtractor;
import com.qx.eakay.model.stake.StakeCardInfoPO;

/**
 * 
 * @author jixf
 * @date 2015年7月25日
 */
@Repository
public class StakeCardInfoDao extends BaseDao {

	@Resource(name = "stake_card_select_sql")
	private JSONSqlMapping cardSelectSQL;

	/**
	 * 分页查找充电卡列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findCardsPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(cardSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 条件查找充电卡列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findCards(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(cardSelectSQL, jsonObject);
	}

	/**
	 * 创建充电卡
	 * 
	 * @param carPO
	 * @return
	 */
	public int create(StakeCardInfoPO cardInfoPO) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(
				new StakeCardInfoCreator(INSERT_SQL, cardInfoPO), keyHolder);
		System.out.println(keyHolder.getKey().intValue() + "主键");
		return keyHolder.getKey().intValue();
	}

	/**
	 * 根据ID查找充电卡
	 * 
	 * @param id
	 * @return
	 */
	public StakeCardInfoPO get(Integer id) {
		return this.getJdbcTemplate().query(GET_SQL, new Object[] { id },
				new StakeCardInfoExtractor());
	}

	/**
	 * 根据内置卡号查找充电设备
	 * 
	 * @param factoryNo
	 * @return
	 */
	public StakeCardInfoPO findByCardID(String cardID,Integer merchantId) {
		return this.getJdbcTemplate().query(GET_BY_CARDID,
				new Object[] { cardID ,merchantId}, new StakeCardInfoExtractor());
	}

	/**
	 * 根据外置卡号查找充电设备
	 * 
	 * @param factoryNo
	 * @return
	 */
	public StakeCardInfoPO findByCardNumber(String cardNumber) {
		return this.getJdbcTemplate().query(GET_BY_NUMBER,
				new Object[] { cardNumber }, new StakeCardInfoExtractor());
	}

	/**
	 * 删除充电卡
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id,String status) {
		int count = this.getJdbcTemplate().update(DELETE_SQL, status,id);
		return count > 0;
	}

	/**
	 * 更改充电卡
	 * 
	 * @param batteryPO
	 * @return
	 */
	public boolean update(StakeCardInfoPO cardInfoPO) {
		int count = this.getJdbcTemplate().update(UPDATE_SQL,
				cardInfoPO.getCardID(), cardInfoPO.getCardNumber(),
				cardInfoPO.getCustomerId(), cardInfoPO.getCarId(),
				cardInfoPO.getCarTableName(), cardInfoPO.getId());
		return count > 0;
	}

	// 插入充电卡SQL
	private static final String INSERT_SQL = "insert into stake_cardinfo (merchantId,cardID,cardNumber,password,openAccountTime,"
			+ "carId,balance,isAnnul,cardType,carTableName,customerId) "
			+ "values (?,?,?,?,?,?,0,'正常','旗翔充电卡',?,?)";
	// 根据内置卡号找充电卡的SQL
	private static final String GET_BY_CARDID = "select *,''  name,'' phone,null customerId from stake_cardinfo where cardID=? and merchantId=?";
	// 根据外置卡号查找充电卡的SQL
	private static final String GET_BY_NUMBER = "select *,''  name,'' phone,null customerId from stake_cardinfo where cardNumber=?";
	// 根据id查找充电卡的SQL
	private static final String GET_SQL = "select sc.*,c.name,c.phone,c.id customerId from stake_cardinfo sc left join customer c on sc.customerId=c.id where sc.id=?";
	// 删除充电卡的SQL
	private static final String DELETE_SQL = "update stake_cardinfo set isAnnul = ? where id=?";
	// 修改充电卡的SQL
	private static final String UPDATE_SQL = "update stake_cardinfo set cardID=?,cardNumber=?,customerId=?,carId=?,carTableName=? where id =?";

}
