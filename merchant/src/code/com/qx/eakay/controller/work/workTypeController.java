package com.qx.eakay.controller.work;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import com.qx.eakay.model.work.workTypePO;
import com.qx.eakay.service.work.workTypeService;
import com.qx.eakay.util.MSG;

/**
 * 
 * @author jixf
 * @date 2015年7月8日
 */
@Controller
@RequestMapping("/work/manage")
public class workTypeController extends BaseController {

	@Autowired
	private workTypeService worktypeService;

	/**
	 * 进入工单信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("work/worktype");
	}

	/**
	 * 分页查询工单信息
	 * 
	 * @param aoData
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxData.htm")
	@ResponseBody
	public PageResults ajaxData1(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		// jquery.datatables 分页查询的参数
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);

		PageResults pageResults = worktypeService.findcworkTypePage(jsonObject,
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
		return jsonObject;
	}

	/**
	 * 新增工单类型
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add.htm")
	public ModelAndView add() throws Exception {
		return new ModelAndView("work/workTypeAdd");
	}

	/**
	 * 
	 * 进入负责人的信息页面
	 */

	@RequestMapping("selectUser.htm")
	public ModelAndView selectUser() throws Exception {
		return new ModelAndView("work/selectUser");
	}

	/**
	 * 进入修改工单类型页面
	 * */
	@RequestMapping("updatepage.htm")
	public ModelAndView updatepage(Integer id) throws Exception {
		workTypePO worktypePO = worktypeService.getworkTypePO(id);
		return new ModelAndView("work/worktypeUpdate", "worktypePO", worktypePO);
	}

	/**
	 * 查询所有的负责人信息
	 * 
	 * 
	 */
	@RequestMapping(value = "userajaxData.htm")
	@ResponseBody
	public PageResults ajaxData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		// jquery.datatables 分页查询的参数
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);

		PageResults pageResults = worktypeService.findUser(jsonObject,
				pageHelp.getiDisplayLength(), pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}

	/**
	 * 保存新增的工单类型记录
	 * 
	 * 
	 **/

	@RequestMapping(value = "saveAdd.htm")
	@ResponseBody
	public MSG saveAdd(workTypePO worktypePO, HttpSession session,
			HttpServletRequest request) {
		MSG msg = new MSG();
		String typeName = request.getParameter("typeName");
		String workDes = request.getParameter("workDes");
		String userId = request.getParameter("userId");
		worktypePO.setTypeName(typeName);
		worktypePO.setUserId(Integer.valueOf(userId));
		worktypePO.setWorkDes(workDes);

		msg.setSuccess(worktypeService.saveAdd(worktypePO));
		return msg;
	}

	/**
	 * 修改原有的工单记录内容
	 * */
	@RequestMapping("updateWorkType.htm")
	@ResponseBody
	public MSG updateWorkType(workTypePO worktypePO) throws Exception {
		MSG msg = new MSG();
		msg.setSuccess(worktypeService.updateWorkType(worktypePO));
		return msg;

	}

	/**
	 * 删除工单类型记录
	 */
	@RequestMapping("delete.htm")
	@ResponseBody
	public MSG deleteFamily(Integer id) {
		MSG msg = new MSG();
		msg.setSuccess(worktypeService.delete(id));
		return msg;
	}

	/**
	 * 导出下载客户信息到Excel表格
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
		worktypeService.excelExport(response, jsonObject);
		return null;
	}

	/**
	 * 动态加载工单类型
	 * 
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxWorkType.htm")
	@ResponseBody
	public List<Map<String, Object>> ajaxWorkType(String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		return worktypeService.findWorkType(jsonObject);
	}
	
	
	 
}
