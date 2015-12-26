package com.qx.msg;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

import com.qx.eakay.util.MSG;

/**
 * 短信发送 
 * 
 * @author jixf
 * @date 2015年8月21日
 */
public class CrMsgDao {
	
	protected Logger logger = Logger.getLogger(getClass());

	private String msgUrl = "http://web.cr6868.com/asmx/smsservice.aspx?";
	private String sign = "易开租车";
	private String name = "18955506754";
	private String pwd = "5C584B3BD8322CE493709BBA7B57";
	
	public MSG send(String content, String phone) {

		try {
			// 创建StringBuffer对象用来操作字符串
			StringBuffer sb = new StringBuffer(msgUrl);

			// 向StringBuffer追加用户名
			sb.append("name=" + name);

			// 向StringBuffer追加密码（登陆网页版，在管理中心--基本资料--接口密码，是28位的）
			sb.append("&pwd=" + pwd);

			// 向StringBuffer追加手机号码
			sb.append("&mobile=" + phone);

			// 向StringBuffer追加消息内容转URL标准码
			sb.append("&content=" + URLEncoder.encode(content, "UTF-8"));
			// 追加发送时间，可为空，为空为及时发送
			sb.append("&stime=");
			// 加签名
			sb.append("&sign=" + URLEncoder.encode(sign, "UTF-8"));

			// type为固定值pt extno为扩展码，必须为数字 可为空
			sb.append("&type=pt&extno=");
			// 创建url对象
			// String temp = new String(sb.toString().getBytes("GBK"),"UTF-8");
			logger.info("------>>发送短信" + sb.toString());
			URL url = new URL(sb.toString());

			// 打开url连接
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();

			// 设置url请求方式 ‘get’ 或者 ‘post’
			connection.setRequestMethod("POST");

			// 发送
			InputStream is = url.openStream();

			// 转换返回值
			String returnStr = convertStreamToString(is);

			String str[] = returnStr.split(",");
			MSG msg = new MSG();
			int code = Integer.parseInt(str[0]);
			msg.setCode(code);
			msg.setSuccess(code == 0);
			msg.setInfo(str[1]);
			return msg;
		} catch (IOException e) {
			return new MSG("短信发送网络异常", 99);
		}

	}

	/**
	 * 转换返回值类型为UTF-8格式.
	 * 
	 * @param is
	 * @return
	 */
	public static String convertStreamToString(InputStream is) {
		StringBuilder sb1 = new StringBuilder();
		byte[] bytes = new byte[4096];
		int size = 0;

		try {
			while ((size = is.read(bytes)) > 0) {
				String str = new String(bytes, 0, size, "UTF-8");
				sb1.append(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb1.toString();
	}

	public String getMsgUrl() {
		return msgUrl;
	}

	public void setMsgUrl(String msgUrl) {
		this.msgUrl = msgUrl;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
