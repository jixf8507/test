package com.qx.eakay.controller.work;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qx.common.base.PageHelp;
import com.qx.common.base.PageResults;
import com.qx.common.tools.JSONTools;
import com.qx.common.tools.StringTools;
import com.qx.eakay.controller.BaseController;
import com.qx.eakay.login.SessionUserBO;
import com.qx.eakay.model.work.WorkOrderPO;
import com.qx.eakay.service.work.WorkOrderService;
import com.qx.eakay.util.MSG;

/**
 * 工单管理
 * 
 * @author sdf
 * @date 2015年12月16日
 */
@Controller
@RequestMapping("/work/order")
public class WorkOrderController extends BaseController {

	@Autowired
	private WorkOrderService workOrderService;

	/**
	 * 进入工单发布页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("release.htm")
	public ModelAndView info() throws Exception {
		return new ModelAndView("work/release");
	}

	/**
	 * 分页查询工单列表
	 * 
	 * @param aoData
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxData.htm")
	@ResponseBody
	public PageResults ajaxData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		// jquery.datatables 分页查询的参数
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);

		PageResults pageResults = workOrderService.findPage(jsonObject,
				pageHelp.getiDisplayLength(), pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}

	/**
	 * 得到请求参数条件
	 * 
	 * @param paraData
	 * @param session
	 * @return
	 */
	private JSONObject getJsonPara(String paraData, HttpSession session) {
		paraData = StringTools.decodeMethod(paraData);
		// 动态查询条件参数
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		SessionUserBO sessionUser = this.getSessionUser(session);
		jsonObject.put("merchantId", sessionUser.getMerchantId());
		// 站点管理员和站点工作人员只可以看到自己发布的工单列表
		if (!sessionUser.isManagerUser()) {
			jsonObject.put("userId", sessionUser.getMerchantUserPO().getId());
		}
		return jsonObject;
	}

	/**
	 * 导出工单列表到Excel表格
	 * 
	 * @param paraData
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("exportToExcel.htm")
	public ModelAndView exportToExcel(String paraData, HttpSession session,
			HttpServletResponse response) throws Exception {
		JSONObject jsonObject = getJsonPara(paraData, session);
		workOrderService.excelExportWorkOrder(response, jsonObject);
		return null;
	}

	/**
	 * 进入新增工单发布页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add.htm")
	public ModelAndView add() throws Exception {
		return new ModelAndView("work/releaseAdd");
	}

	/**
	 * 保存新增工单发布
	 * 
	 * @param workOrderPO
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveAdd.htm")
	@ResponseBody
	public MSG saveAdd(WorkOrderPO workOrderPO, HttpSession session)
			throws Exception {
		MSG msg = new MSG();
		String transactUser = this.getSessionUser(session).getName();
		workOrderPO.setTransactUser(transactUser);
		msg.setSuccess(workOrderService.saveAdd(workOrderPO));
		return msg;
	}

	/**
	 * 进入工单处理列表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("handle.htm")
	public ModelAndView handle() throws Exception {
		return new ModelAndView("work/handle");
	}

	/**
	 * 进入工单处理信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("doWork.htm")
	public ModelAndView doWork(Integer id) throws Exception {
		WorkOrderPO workOrderPO = workOrderService.getWorkOrder(id);
		return new ModelAndView("work/doWork", "workOrderPO", workOrderPO);
	}

	/**
	 * 保存工单处理
	 * 
	 * @param workOrderPO
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveEdite.htm")
	@ResponseBody
	public MSG saveEdite(WorkOrderPO workOrderPO) throws Exception {
		MSG msg = new MSG();
		msg.setSuccess(workOrderService.saveEdite(workOrderPO));
		return msg;
	}

	/**
	 * 进入修改工单页面
	 * */
	@RequestMapping("updatepage.htm")
	public ModelAndView updatepage(Integer id) throws Exception {
		WorkOrderPO workOrderPO = workOrderService.getWorkOrder(id);
		return new ModelAndView("work/workorderUpdate", "workOrderPO",
				workOrderPO);
	}

	/**
	 * 修改原有的工单记录内容
	 * */
	@RequestMapping("updateWorkOrder.htm")
	@ResponseBody
	public MSG updateWorkType(WorkOrderPO workOrderPO, HttpSession session)
			throws Exception {
		MSG msg = new MSG();
		String transactUser = this.getSessionUser(session).getName();
		workOrderPO.setTransactUser(transactUser);
		msg.setSuccess(workOrderService.updateWorkwork(workOrderPO));
		return msg;

	}

	/**
	 * 删除工单记录
	 */
	@RequestMapping("delete.htm")
	@ResponseBody
	public MSG deleteFamily(Integer id) {
		MSG msg = new MSG();
		msg.setSuccess(workOrderService.delete(id));
		return msg;
	}

}
