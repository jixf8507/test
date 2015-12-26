package com.qx.eakay.service.work;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qx.common.base.BaseExport;
import com.qx.common.base.PageResults;
import com.qx.eakay.dao.work.workTypeDao;
import com.qx.eakay.export.work.WorkTypeExport;
import com.qx.eakay.model.work.workTypePO;

@Service
public class workTypeService {

	@Autowired
	private workTypeDao worktypeDao;

	/**
	 * 分页查询工单类型
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findcworkTypePage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {

		return worktypeDao.findPage(jsonObject, pageSize, iDisplayStart);
	}

	/**
	 * 
	 * *查询所有的负责人信息
	 * 
	 * */
	public PageResults findUser(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {

		return worktypeDao.findUser(jsonObject, pageSize, iDisplayStart);
	}
	
	/**
	 * 
	 * 保存新增的工单类型
	 * */
	public Boolean saveAdd(workTypePO worktypePO) {
		return worktypeDao.saveAdd(worktypePO) > 0;
	}

	/**
	 * 
	 * 保存修改的工单类型
	 * */
	public Boolean updateWorkType(workTypePO worktypePO) {
		return worktypeDao.updateWorkType(worktypePO);
	}

	/**
	 * 根据id查找工单累型信息
	 * 
	 * @param id
	 * @return
	 */
	public workTypePO getworkTypePO(Integer id) {
		return worktypeDao.getworkTypePO(id);
	}

	/**
	 * 删除工单类型记录
	 */
	@Transactional
	public boolean delete(Integer id) {

		return worktypeDao.delete(id);

	}

	/**
	 * 条件搜索违章记录信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public List<Map<String, Object>> findPeccancyRecords(JSONObject jsonObject) {
		return worktypeDao.findPeccancyRecords(jsonObject);
	}

	/**
	 * 导出违章记录信息
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExport(HttpServletResponse response, JSONObject jsonObject) {
		BaseExport export = new WorkTypeExport();
		List<Map<String, Object>> list = findPeccancyRecords(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 动态加载工单类型
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findWorkType(JSONObject jsonObject) {
		return worktypeDao.findWorkType(jsonObject);
	}

}