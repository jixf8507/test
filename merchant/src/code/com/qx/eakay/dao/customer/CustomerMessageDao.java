package com.qx.eakay.dao.customer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.qx.common.dao.BaseDao;
import com.qx.eakay.db.creator.CustomerMessageCreator;
import com.qx.eakay.model.customer.AccountRecordPO;
import com.qx.eakay.model.customer.CustomerMessagePO;

/**
 * 
 * @author jixf
 * @date 2015年7月10日
 */
@Repository
public class CustomerMessageDao extends BaseDao {

	/**
	 * 新增用户消息
	 * 
	 * @param customerMessagePO
	 * @return
	 */
	public Integer create(CustomerMessagePO customerMessagePO) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(
				new CustomerMessageCreator(INSERT_SQL, customerMessagePO),
				keyHolder);
		System.out.println(keyHolder.getKey().intValue() + "主键");
		return keyHolder.getKey().intValue();
	}

	/**
	 * 批量保存客户消息通知
	 * 
	 * @param msgs
	 * @return
	 */
	public boolean batchCreateMsg(final List<CustomerMessagePO> msgs) {
		final Timestamp now = new Timestamp(System.currentTimeMillis());
		int[] ids = this.getJdbcTemplate().batchUpdate(INSERT_SQL,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						CustomerMessagePO customerMessagePO = msgs.get(i);
						ps.setInt(1, customerMessagePO.getCustomerId());
						ps.setString(2, customerMessagePO.getContent());
						ps.setString(3, customerMessagePO.getType());
						ps.setTimestamp(4, now);
					}

					@Override
					public int getBatchSize() {
						return msgs.size();
					}
				});
		return ids.length != msgs.size();
	}

	/**
	 * 新增账户充值的消息
	 * 
	 * @param accountRecordPO
	 *            账户充值记录
	 */
	public void createBalanceChargeMsg(AccountRecordPO accountRecordPO) {
		// 创建客户充值消息
		CustomerMessagePO msg = CustomerMessagePO
				.createBalanceChargeMsg(accountRecordPO);
		create(msg);
	}

	// 插入用户消息SQL
	private static final String INSERT_SQL = "insert into customer_message(customerId,content,type,status,createdTime) values (?,?,?,'未读',?)";

	
}
