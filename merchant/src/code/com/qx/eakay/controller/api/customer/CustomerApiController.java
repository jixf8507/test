package com.qx.eakay.controller.api.customer;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qx.eakay.controller.BaseController;
import com.qx.eakay.model.customer.CustomerPO;
import com.qx.eakay.service.customer.CustomerService;
import com.qx.eakay.util.MSG;

/**
 * 客户接口
 * 
 * @author sdf
 * @date 2015年12月4日
 */
@Controller
@RequestMapping("/api/customer")
public class CustomerApiController extends BaseController {

	@Autowired
	private CustomerService customerService;

	/**
	 * 获取客户信息
	 * 
	 * @param request
	 * @param merchantId
	 *            商家ID
	 * @param phone
	 *            手机号码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("queryCustomerByPhone.htm")
	@ResponseBody
	public HashMap<String, Object> getCustomer(HttpServletRequest request)
			throws Exception {

		MSG msg = new MSG();
		HashMap<String, Object> result = new HashMap<>();
		String merchantId = request.getParameter("merchantId");
		String phone = request.getParameter("phone");
		if (phone == null || "".equals(phone)) {
			msg.setSuccess(false);
			msg.setInfo("手机号码为空");
		}else if(merchantId == null || "".equals(merchantId)){
			msg.setSuccess(false);
			msg.setInfo("商家ID为空");
		} else {
			CustomerPO customerPO = customerService.getCustomerByPhone(phone,Integer.parseInt(merchantId));
			if (customerPO != null) {
				if (!"已通过".equals(customerPO.getStatus())) {
					msg.setSuccess(false);
					msg.setInfo("该客户未通过审核，暂不能绑定充电卡");
				} else {
					Map<String, Object> map = new HashMap<String, Object>();
					// 客户信息
					map.put("id", customerPO.getId());
					map.put("idCard", customerPO.getIdCard());
					map.put("name", customerPO.getName());
					map.put("phone", customerPO.getPhone());
					map.put("sex", customerPO.getSex());
					map.put("address", customerPO.getAddress());
					map.put("status", customerPO.getStatus());
					// 账户信息
					map.put("accountStatus", customerPO.getAccountPO()
							.getStatus());
					map.put("accountId", customerPO.getAccountPO().getId());
					map.put("balance", customerPO.getAccountPO().getBalance()
							+ "");

					msg.setSuccess(true);
					msg.setInfo("获取客户信息成功");

					result.put("customer", map);
				}
			} else {
				msg.setSuccess(false);
				msg.setInfo("未查到客户信息");
			}
		}

		result.put("msg", msg);
		return result;
	}

}
