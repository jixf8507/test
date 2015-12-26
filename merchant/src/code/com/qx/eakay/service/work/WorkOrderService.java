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
import com.qx.eakay.dao.work.WorkOrderDao;
import com.qx.eakay.export.work.WorkOrderHandleExport;
import com.qx.eakay.export.work.WorkOrderReleaseExport;
import com.qx.eakay.model.work.WorkOrderPO;
import com.qx.eakay.service.BaseService;

/**
 * 工单发布
 * 
 * @author sdf
 * @date 2015年12月16日
 */
@Service
public class WorkOrderService extends BaseService {

	@Autowired
	private WorkOrderDao workOrderDao;

	/**
	 * 分页查询工单列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return workOrderDao.findPage(jsonObject, pageSize, iDisplayStart);
	}

	/**
	 * 导出工单列表到Excel表格
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportWorkOrder(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export;
		String workStatus = jsonObject.get("workStatus") + "";
		if ("待处理".equals(workStatus)) {
			export = new WorkOrderReleaseExport();
		} else {
			export = new WorkOrderHandleExport();
		}
		List<Map<String, Object>> list = findList(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 条件查询工单列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	private List<Map<String, Object>> findList(JSONObject jsonObject) {
		return workOrderDao.findList(jsonObject);
	}

	/**
	 * 保存新增工单发布
	 * 
	 * @param workOrderPO
	 * @return
	 */
	public boolean saveAdd(WorkOrderPO workOrderPO) {
		return workOrderDao.saveAdd(workOrderPO)>0;
	}

	/**
	 * 根据Id获取工单信息
	 * 
	 * @param id
	 * @return
	 */
	public WorkOrderPO getWorkOrder(Integer id) {
		return workOrderDao.getWorkOrder(id);
	}

	/**
	 * 保存工单处理
	 * 
	 * @param workOrderPO
	 * @return
	 */
	public boolean saveEdite(WorkOrderPO workOrderPO) {
		return workOrderDao.saveEdite(workOrderPO);
	}
	
	/**
	 * 
	 * 保存修改的工单
	 * */
	public Boolean updateWorkwork(WorkOrderPO  workOrderPO) {
		return workOrderDao.updateWorkwork(workOrderPO) > 0;
	}
	
	/**
	 * 删除工单类型记录
	 */
	@Transactional
	public boolean delete(Integer id) {

		return workOrderDao.delete(id);

	}

}
