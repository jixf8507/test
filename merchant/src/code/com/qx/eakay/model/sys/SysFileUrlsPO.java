package com.qx.eakay.model.sys;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class SysFileUrlsPO {

	private Integer id;
	private String fileName;
	private String fileType;
	private Integer tableId;
	private String tableName;
	private String fileUrl;
	private Timestamp createdTime;

	public SysFileUrlsPO() {

	}

	public SysFileUrlsPO(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.fileName = rs.getString("fileName");
		this.fileType = rs.getString("fileType");
		this.tableId = rs.getInt("tableId");
		this.tableName = rs.getString("tableName");
		this.fileUrl = rs.getString("fileUrl");
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Integer getTableId() {
		return tableId;
	}

	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

}
