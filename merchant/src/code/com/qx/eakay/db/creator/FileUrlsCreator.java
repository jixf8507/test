package com.qx.eakay.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.qx.eakay.model.sys.SysFileUrlsPO;

public class FileUrlsCreator implements PreparedStatementCreator {

	private String sql;
	private SysFileUrlsPO fileUrlsPO;

	public FileUrlsCreator(String sql, SysFileUrlsPO fileUrlsPO) {
		this.sql = sql;
		this.fileUrlsPO = fileUrlsPO;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setString(1, fileUrlsPO.getFileName());
		ps.setString(2, fileUrlsPO.getFileType());
		ps.setInt(3, fileUrlsPO.getTableId());
		ps.setString(4, fileUrlsPO.getTableName());
		ps.setString(5, fileUrlsPO.getFileUrl());
		ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
		return ps;
	}

}
