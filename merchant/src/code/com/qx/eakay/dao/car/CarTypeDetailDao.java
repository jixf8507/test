package com.qx.eakay.dao.car;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.qx.common.dao.BaseDao;
import com.qx.eakay.model.car.CarTypeDetailPO;

/**
 * 
 * @author jixf
 * @date 2015年7月14日
 */
@Repository
public class CarTypeDetailDao extends BaseDao {

	/**
	 * 查询车型的的检测状态项
	 * 
	 * @param carTypeId
	 *            车型ID
	 * @return
	 */
	public List<Map<String, Object>> findDetails(Integer carTypeId) {
		return this.findListBySQL(SELECT_SQL, new Object[] { carTypeId });
	}

	/**
	 * 批量创建型号的审核状态项
	 * 
	 * @param carDetails
	 * @return
	 */
	public boolean batchCreate(final List<CarTypeDetailPO> carDetails,
			final Integer typeId) {
		int[] ids = this.getJdbcTemplate().batchUpdate(INSERT_SQL,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						CarTypeDetailPO detailPO = carDetails.get(i);
						ps.setInt(1, typeId);
						ps.setString(2, detailPO.getComponent());
						ps.setString(3, detailPO.getStatus());
						ps.setString(4, detailPO.getDescribe());
					}

					@Override
					public int getBatchSize() {
						return carDetails.size();
					}
				});
		return ids.length == carDetails.size();
	}

	/**
	 * 删除详细
	 * 
	 * @param carId
	 * @return
	 */
	public boolean delete(Integer typeId) {
		return this.getJdbcTemplate().update(DELETE_SQL, typeId) > 0;
	}

	// 查询车辆详细状态SQL
	private static final String SELECT_SQL = "select * from car_type_detail where carTypeId=?";
	// 新增车辆详细状态SQL
	private static final String INSERT_SQL = "insert into car_type_detail (carTypeId,component,`status`,`describe`) values (?,?,?,?)";
	// 查询车辆详细状态SQL
	private static final String DELETE_SQL = "delete from car_type_detail where carTypeId=?";

}
