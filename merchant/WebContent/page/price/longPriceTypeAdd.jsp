<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>

<script type="text/javascript"
	src="${ctx}/js/plugins/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/page/price/longPriceTypeAdd.js"></script>
</head>

<body>
					<form id="form1" class="stdform stdform2" method="post"
						action="">
						<p>
							<label>套餐名称</label> <span class="field"><input type="text" style="width: 200px;"
								name="typeName" id="typeName" class="longinput" /></span>
						</p>
						
						<p>
							<label>月收费</label> <span class="field"><input
								type="text" name="monthFee" id="monthFee"
								class="longinput" style="width: 200px;" />&nbsp;&nbsp;（元）</span>
						</p>
						<p>
							<label>日收费</label> <span class="field"><input
								type="text" name="dayFee" id="dayFee"
								class="longinput" style="width: 200px;" />&nbsp;&nbsp;（元）</span>
						</p>
						<p>
							<label>小时收费</label> <span class="field"><input
								type="text" name="hourFee" id="hourFee"
								class="longinput" style="width: 200px;" />&nbsp;&nbsp;（元）</span>
						</p>
						
					</form>
</body>
</html>
