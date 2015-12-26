<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/plugins/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/page/merchant/drawMoneyApply.js"></script>
<body>
					<form id="form1" class="stdform stdform2" method="post"
						action="${ctx}/merchant/drawMoney/saveAdd.htm?">
						<p>
							<label>当前可取金额</label> <span class="field">${merchantPO.balance}元&nbsp;</span>
							<input type="hidden" id="balance" value="${merchantPO.balance}"/>
						</p>
						<p>
							<label>提款企业银行帐号</label> <span class="field">${merchantPO.alipayAccount}&nbsp;</span>
						</p>
						<p>
							<label>提款企业账户名</label> <span class="field">${merchantPO.alipayAccountName}&nbsp;</span>
						</p>
						<p>
							<label>提款银行</label> <span class="field">${merchantPO.bankTypeName}&nbsp;</span>
						</p>

						<p>
							<label>提款金额</label> <span class="field"><input type="text"
								name="money" id="money" class="longinput" style="width: 200px;" />&nbsp;&nbsp;元</span>
						</p>

						<p>
							<label>提款说明</label> <span class="field"> <textarea
									cols="80" rows="5" name="remarks" id="remarks"
									class="mediuminput"></textarea></span>
						</p>
					</form>
</body>
</html>
