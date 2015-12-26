package com.qx.eakay.dao.sys;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.qx.common.dao.BaseDao;
import com.qx.common.sql.JSONSqlMapping;
import com.qx.eakay.db.creator.FileUrlsCreator;
import com.qx.eakay.db.extractor.FileUrlsExtractor;
import com.qx.eakay.model.sys.SysFileUrlsPO;

@Repository
public class SysFileUrlsDao extends BaseDao {

	@Resource(name = "sys_fileurls_sql")
	private JSONSqlMapping fileUrlsSelectSql;

	@Resource(name = "CAR_IMG_SELECT_SQL")
	private JSONSqlMapping carImgSelectSql;

	/**
	 * 创建车辆
	 * 
	 * @param carPO
	 * @return
	 */
	public int create(SysFileUrlsPO fileUrlsPO) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(
				new FileUrlsCreator(INSERT_SQL, fileUrlsPO), keyHolder);
		return keyHolder.getKey().intValue();
	}

	public SysFileUrlsPO get(Integer id) {
		return this.getJdbcTemplate().query(GET_SQL, new Object[] { id },
				new FileUrlsExtractor());
	}

	/**
	 * 查找车辆审核图片
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findList(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(fileUrlsSelectSql, jsonObject);
	}

	private static final String INSERT_SQL = "insert into sys_file_urls (fileName,fileType,tableId,tableName,fileUrl,createdTime) values (?,?,?,?,?,?)";
	private static final String GET_SQL = "select * from  sys_file_urls where id=?";
	private static final String DELETE_CAR_IMAGES_SQL = "delete from car_images where id=?";
	private static final String INSERT_CAR_IMAGES_SQL = "insert into car_images (carId,fileId) values (?,?)";
	private static final String INSERT_ORDER_IMAGES_SQL = "insert into order_images (orderId,fileId,type) values (?,?,?)";
	private static final String DELETE_SYS_FILE_URL_SQL = "delete from sys_file_urls where id=?";

	/**
	 * 查找车辆图片
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findCarImgFile(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(carImgSelectSql, jsonObject);
	}
	
	/**
	 * 删除sys_file_url表中的一条记录。
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteSysFileUrl(Integer id) {
		return this.getJdbcTemplate().update(DELETE_SYS_FILE_URL_SQL, id) > 0;
	}
	
	

	/**
	 * 删除车辆图片
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id) {
		return this.getJdbcTemplate().update(DELETE_CAR_IMAGES_SQL, id) > 0;
	}

	/**
	 * 保存车辆图片关系
	 * 
	 * @param carId
	 * @param fileId
	 * @return
	 */
	public boolean createCarImgRelation(Integer carId, Integer fileId) {
		int i = this.getJdbcTemplate().update(INSERT_CAR_IMAGES_SQL,
				new Object[] { carId, fileId });
		return i > 0;
	}

	/**
	 * 保存订单图片关系
	 * 
	 * @param carId
	 * @param fileId
	 * @return
	 */
	public boolean createOrderImgRelation(SysFileUrlsPO fileUrlsPO) {
		int i = this.getJdbcTemplate().update(
				INSERT_ORDER_IMAGES_SQL,
				new Object[] { fileUrlsPO.getTableId(), fileUrlsPO.getId(),
						fileUrlsPO.getFileType() });
		return i > 0;
	}

	/**
	 * 批量保存订单图片关系
	 * 
	 * @param orderId
	 * @param fileIds
	 * @return
	 */
	public boolean batchCreateOrderImgRelation(final List<Integer> fileIds,
			final Integer orderId, final String type) {
		int[] ids = this.getJdbcTemplate().batchUpdate(INSERT_ORDER_IMAGES_SQL,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ps.setInt(1, orderId);
						ps.setInt(2, fileIds.get(i));
						ps.setString(3, type);
					}

					@Override
					public int getBatchSize() {
						return fileIds.size();
					}
				});
		return ids.length == fileIds.size();
	}

}
