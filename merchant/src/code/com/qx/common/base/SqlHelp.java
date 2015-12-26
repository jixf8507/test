package com.qx.common.base;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.qx.common.sql.Condition;
import com.qx.common.sql.JSONSqlMapping;

/**
 * SQL查询帮助类
 * 
 * @author Administrator
 * 
 */
public class SqlHelp {

	private StringBuffer sql;
	private Object[] args;

	public SqlHelp(JSONSqlMapping jsonSqlMapping) {
		sql = new StringBuffer(" " + jsonSqlMapping.getSelect());
		args = new Object[] {};
		JSONObject jsonObject = jsonSqlMapping.getJsonObject();
		List<Condition> conditions = jsonSqlMapping.getConditions();
		if (jsonObject != null && conditions != null && conditions.size() > 0) {
			for (Condition condition : conditions) {
				String key = condition.getKey();
				if (jsonObject.get(key) != null
						&& StringUtils.isNotBlank(jsonObject.get(key) + "")) {
					sql.append(" " + condition.getValue());
					args = ArrayUtils.add(args, args.length,
							jsonObject.get(key));
				}
			}
		}
		sql.append(" " + jsonSqlMapping.getOrder());
	}
	
	public SqlHelp() {

	}
	
	public SqlHelp(StringBuffer sql, Object[] args) {
		super();
		this.sql = sql;
		this.args = args;
	}
	
	public String getSql() {
		return sql.toString();
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public void appendSql(String str) {
		sql.append(str);
	}

	public Object[] getArgs() {
		return args;
	}

}
