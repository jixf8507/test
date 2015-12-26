<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${ctx}/js/page/merchant/couponAdd.js"></script>
<script type="text/javascript" src="${ctx}/js/plugins/jquery.datetimepicker.js"></script>
<body>
	<form id="form1" class="stdform stdform2" method="post" action="">

		<p>
			<label>优惠券名</label> <span class="field"><input type="text"
								name="couponName" id="couponName" class="longinput" /></span>
		</p>
		<p>
			<label>数量</label> <span class="field"><input type="text"
								name="number" id="number" class="longinput" /></span>
		</p>
		<p>
			<label>金额</label> <span class="field"><input type="text"
								name="balance" id="balance" class="longinput" /></span>
		</p>
		
		<p>
			<label>到期时间</label> <span class="field"><input type="text"
								name="toDateStr" id="toDateStr" class="longinput" /></span>
		</p>
		
		<p>
			<label>优惠券类型</label>
			<span class="field">
				</select> &nbsp; <select id="flag" name="couponType" id="couponType" class="chzn-select"
						style="width: 200px;" tabindex="2">
						<option value="1">现金优惠券</option>
						<option value="2">里程优惠券</option>
				</select>
			</span>
		</p>
		
		<p>
			<label>描述</label> <span class="field"><input type="text"
								name="describe" id="describe" class="longinput" /></span>
		</p>
		
		
	</form>
</body>
</html>