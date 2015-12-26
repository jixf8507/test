package com.qx.eakay.dao.car;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.qx.common.dao.BaseDao;
import com.qx.eakay.model.car.CarDetailPO;

/**
 * 
 * @author jixf
 * @date 2015年7月14日
 */
@Repository
public class CarDetailDao extends BaseDao {

	/**
	 * 查询车辆的详细状态
	 * 
	 * @param carId
	 * @return
	 */
	public List<Map<String, Object>> findDetails(Integer carId) {
		return this.findListBySQL(SELECT_SQL, new Object[] { carId });
	}

	/**
	 * 批量修改车辆详细状态
	 * 
	 * @param carDetails
	 * @return
	 */
	public boolean batchUpdateDetails(final List<CarDetailPO> carDetails) {
		int[] ids = this.getJdbcTemplate().batchUpdate(UPDATE_SQL,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						CarDetailPO detailPO = carDetails.get(i);
						ps.setString(1, detailPO.getComponent());
						ps.setString(2, detailPO.getStatus());
						ps.setString(3, detailPO.getDescribe());
						ps.setInt(4, detailPO.getId());
					}

					@Override
					public int getBatchSize() {
						return carDetails.size();
					}
				});
		return ids.length == carDetails.size();
	}

	/**
	 * 批量创建车辆的审核状态项
	 * 
	 * @param carDetails
	 * @return
	 */
	public boolean batchCreate(final List<CarDetailPO> carDetails) {
		int[] ids = this.getJdbcTemplate().batchUpdate(INSERT_SQL,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						CarDetailPO detailPO = carDetails.get(i);
						ps.setInt(1, detailPO.getCarId());
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

	// 查询车辆详细状态SQL
	private static final String SELECT_SQL = "select * from car_detail where carId=?";
	// 更改车辆详细状态SQL
	private static final String UPDATE_SQL = "update car_detail set component=?,`status`=?,`describe`=? where id=?";
	// 新增车辆详细状态SQL
	private static final String INSERT_SQL = "insert into car_detail (carId,component,`status`,`describe`) values (?,?,?,?)";

}
