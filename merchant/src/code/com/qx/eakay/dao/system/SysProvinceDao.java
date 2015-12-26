package com.qx.eakay.dao.system;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.qx.common.dao.BaseDao;

/**
 * 
 * @author jixf
 * @date 2015年8月5日
 */
@Repository
public class SysProvinceDao extends BaseDao {

	/**
	 * 条件查询省
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findProvinces() {
		return this.findListBySQL(SELECT_SQL, null);
	}

	private static final String SELECT_SQL = "select * from sys_province ORDER BY proSort";

}
