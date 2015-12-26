package com.qx.eakay.dao.merchant;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.qx.common.dao.BaseDao;

/**
 * 
 * @author jixf
 * @date 2015年7月11日
 */
@Repository
public class MerchantUserModelsDao extends BaseDao {

	/**
	 * 批量保存用户功能模块菜单
	 * 
	 * @param userId
	 * @param moudlesIds
	 * @return
	 */
	public boolean batchCreate(final Integer userId,
			final List<Integer> moudlesIds) {
		int[] count = this.getJdbcTemplate().batchUpdate(INSERT_SQL,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ps.setInt(1, userId);
						ps.setInt(2, moudlesIds.get(i));
					}

					@Override
					public int getBatchSize() {
						return moudlesIds.size();
					}
				});
		return count.length == moudlesIds.size();
	}

	/**
	 * 批量删除用户功能模块菜单
	 * 
	 * @param userId
	 * @return
	 */
	public boolean batchDelete(Integer userId) {
		int count = this.getJdbcTemplate().update(DELETE_SQL, userId);
		return count >= 0;
	}

	// 新增用户功能模块菜单SQL
	private static final String INSERT_SQL = "insert into merchant_user_modules (userId,moduleId) values(?,?)";
	// 删除用户功能模块菜单SQL
	private static final String DELETE_SQL = "delete from merchant_user_modules where userId=?";

}
