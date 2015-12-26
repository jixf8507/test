package com.qx.eakay.dao.system;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.qx.common.dao.BaseDao;

@Repository("settingDao")
public class SysSettingDao extends BaseDao {

	public List<Map<String, Object>> findByKey(String key) {
		return this.findListBySQL(SELECT_SQL, new Object[] { key });
	}

	private static final String SELECT_SQL = "select s.id,s.key,s.value from sys_setting s where  s.key= ?";

}
