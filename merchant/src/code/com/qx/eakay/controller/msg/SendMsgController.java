package com.qx.eakay.controller.msg;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qx.eakay.controller.BaseController;
import com.qx.eakay.help.MsgFactory;
import com.qx.eakay.util.MSG;
import com.qx.msg.CrMsgDao;

@Controller
@RequestMapping("/msg/send")
public class SendMsgController extends BaseController {

	@Autowired
	private CrMsgDao crMsgDao;

	/**
	 * 发送短信验证码
	 * 
	 * @param phone
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "updateStatus.htm")
	@ResponseBody
	public MSG updateStatus(String phone, HttpSession session) {
		String verCode = getVerCode();
		session.setAttribute(phone, verCode);
		logger.info("---->>发送短信验证码：phone = "+phone+" , verCode = "+verCode);
		
//		MSG msg = new MSG();
//		msg.setSuccess(true);
//		return msg;
		return crMsgDao.send(MsgFactory.getRegistMsg(verCode), phone);
	}
	
	
	/**
	 * 检查输入的验证码是否正确
	 * 
	 * @param phone
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "check.htm")
	@ResponseBody
	public MSG check(String phone, HttpSession session) {
		String verCode = "";
		MSG msg = new MSG();
		try {
			verCode = session.getAttribute(phone).toString();
			
		} catch (Exception e) {
			msg.setInfo("");
		}
		msg.setInfo(verCode);
		return msg;
	}

	/**
	 * 获取随机验证码
	 * 
	 * @return
	 */
	public String getVerCode() {
		StringBuffer sb = new StringBuffer();
		char[] ch = "0123456789".toCharArray();
		int index, len = ch.length;
		Random r = new Random();
		for (int i = 0; i < 4; i++) {
			index = r.nextInt(len);
			sb.append(ch[index]);
		}
		return sb.toString();
	}

}
