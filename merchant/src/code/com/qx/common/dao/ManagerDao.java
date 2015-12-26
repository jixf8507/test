package com.qx.common.dao;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.qx.common.model.BasePO;

/**
 * 
 * @author jixf
 * @date 2015年7月1日
 */
public abstract class ManagerDao<T extends BasePO> extends BaseDao {

	/**
	 * 创建对象
	 * 
	 * @param t
	 * @return
	 */
	public int create(T t) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(getCreator(t), keyHolder);
		System.out.println(keyHolder.getKey().intValue() + "主键");
		return keyHolder.getKey().intValue();
	}
	
//	public T get(Integer id){
//		return this.getJdbcTemplate().query(GET_SQL, new Object[] { id },
//				new CarManufacturerExtractor());
//	}

	/**
	 * 
	 * @param t
	 * @return
	 */
	protected abstract PreparedStatementCreator getCreator(T t);

}
