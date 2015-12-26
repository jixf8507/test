package com.qx.eakay.controller.customer;

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
import com.qx.eakay.model.customer.AccountPO;
import com.qx.eakay.model.customer.CustomerPO;
import com.qx.eakay.service.customer.CustomerPotentialService;
import com.qx.eakay.util.MSG;

/**
 * 
 * @author jixf
 * @date 2015年7月9日
 */
@Controller
@RequestMapping("/customer/potential")
public class CustomerPotentialController extends BaseController {

	@Autowired
	private CustomerPotentialService customerPotentialService;

	/**
	 * 进入用户审核办卡页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("customer/customerPotential");
	}

	/**
	 * 审核办卡
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("check.htm")
	public ModelAndView check(Integer id) throws Exception {
		CustomerPO customerPO = customerPotentialService.getCustomer(id);
		return new ModelAndView("customer/customerCheck", "customerPO",
				customerPO);
	}

	/**
	 * 保存用户办卡信息
	 * 
	 * @param customerPO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveCheck.htm")
	@ResponseBody
	public MSG saveCheck(AccountPO accountPO, HttpSession session)
			throws Exception {
		MSG msg = new MSG();
		SessionUserBO sessionUser = this.getSessionUser(session);
		accountPO.setMerchantId(sessionUser.getMerchantId());
		accountPO.setTransactUser(sessionUser.getName());
		boolean bool = customerPotentialService.createAccount(accountPO);
		msg.setSuccess(bool);
		return msg;
	}

	@RequestMapping(value = "ajaxData.htm")
	@ResponseBody
	public PageResults ajaxData(String aoData, String paraData,
			HttpSession session) {
		paraData = StringTools.decodeMethod(paraData);
		// 动态查询条件参数
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		SessionUserBO sessionUser = this.getSessionUser(session);
		jsonObject.put("merchantId", sessionUser.getMerchantId());
		// jquery.datatables 分页查询的参数
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);

		PageResults pageResults = customerPotentialService
				.findCustomerPotentialPage(jsonObject,
						pageHelp.getiDisplayLength(),
						pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}

}
