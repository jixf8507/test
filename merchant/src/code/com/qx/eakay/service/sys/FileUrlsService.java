package com.qx.eakay.service.sys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qx.eakay.dao.sys.SysFileUrlsDao;
import com.qx.eakay.model.sys.SysFileUrlsPO;
import com.qx.eakay.service.BaseService;
import com.qx.eakay.util.MSG;

/**
 * 
 * @author jixf
 * @date 2015年9月1日
 */
@Service
public class FileUrlsService extends BaseService {

	@Resource
	private SysFileUrlsDao fileUrlsDao;

	/**
	 * 保存审核图片
	 * 
	 * @param fileUrlsPO
	 * @return
	 */
	@Transactional
	public SysFileUrlsPO createFileUrls(SysFileUrlsPO fileUrlsPO) {
		Integer id = fileUrlsDao.create(fileUrlsPO);
		if (id == null) {
			return null;
		}
		fileUrlsPO.setId(id);
		return fileUrlsPO;
	}

	/**
	 * 删除sys_file_url表中的一条记录。
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteSysFileUrl(Integer id) {
		return fileUrlsDao.deleteSysFileUrl(id);
	}

	/**
	 * 保存审核图片
	 * 
	 * @param fileUrlsPO
	 * @return
	 */
	@Transactional
	public SysFileUrlsPO createImgUpload(SysFileUrlsPO fileUrlsPO, Integer carId) {
		Integer id = fileUrlsDao.create(fileUrlsPO);
		if (id == null) {
			return null;
		}
		fileUrlsPO.setId(id);
		// 保存车辆图片关系
		fileUrlsDao.createCarImgRelation(carId, id);
		// 保存订单图片关系
		fileUrlsDao.createOrderImgRelation(fileUrlsPO);
		return fileUrlsPO;
	}

	public SysFileUrlsPO get(Integer id) {
		return fileUrlsDao.get(id);
	}

	/**
	 * 查找车辆审核图片
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findList(JSONObject jsonObject) {
		return fileUrlsDao.findList(jsonObject);
	}

	/**
	 * 查找车辆图片
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findCarImgFile(JSONObject jsonObject) {
		return fileUrlsDao.findCarImgFile(jsonObject);
	}

	/**
	 * 删除车辆图片
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id) {
		return fileUrlsDao.delete(id);
	}

	/**
	 * 保存车辆图片
	 * 
	 * @param fileUrlsPO
	 * @return
	 */
	@Transactional
	public boolean createCarImgUpload(SysFileUrlsPO fileUrlsPO, Integer carId) {
		// 保存图片
		Integer fileId = fileUrlsDao.create(fileUrlsPO);
		// 保存车辆图片关系
		return fileUrlsDao.createCarImgRelation(carId, fileId);
	}

	/**
	 * 保存图片
	 * 
	 * @param fileUrlsPO
	 * @param fileUrls
	 * @param fileNames
	 * @return
	 */
	@Transactional
	public MSG savePic(SysFileUrlsPO fileUrlsPO, String[] fileNames,
			String[] fileUrls) {
		MSG msg = new MSG();
		List<Integer> id = new ArrayList<Integer>();

		for (int i = 0; i < fileNames.length; i++) {
			fileUrlsPO.setFileName(fileNames[i]);
			fileUrlsPO.setFileUrl(fileUrls[i]);
			int key = fileUrlsDao.create(fileUrlsPO);
			id.add(key);
			if (key < 0) {
				msg.setSuccess(false);
				msg.setInfo(fileNames[i] + "图片保存失败");
			}
		}
		msg.setInfo(id + "");
		return msg;
	}

}
