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
public class SysCityDao extends BaseDao {

	/**
	 * 条件查询市
	 * 
	 * @param proID
	 *            省ID
	 * @return
	 */
	public List<Map<String, Object>> findCitys(String pName) {
		return this.findListBySQL(SELECT_SQL, new Object[] { pName });
	}

	private static final String SELECT_SQL = "select c.* from sys_city c INNER JOIN sys_province p on c.proID=p.id WHERE p.proName=? ORDER BY c.citySort";

}
