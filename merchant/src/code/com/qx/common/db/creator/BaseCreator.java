package com.qx.common.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.qx.common.model.BasePO;

public abstract class BaseCreator<T extends BasePO> implements
		PreparedStatementCreator {

	private T t;
	private String sql;

	public BaseCreator() {

	}

	public BaseCreator(T t, String sql) {
		this.t = t;
		this.sql = sql;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		return getPreStat(ps, t);
	}

	/**
	 * 
	 * @param ps
	 * @param t
	 * @return
	 * @throws SQLException
	 */
	protected abstract PreparedStatement getPreStat(PreparedStatement ps, T t)
			throws SQLException;

}
