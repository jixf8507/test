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
	src="${ctx}/js/plugins/jquery.datetimepicker.js"></script>
<script type="text/javascript"
	src="${ctx}/js/page/order/returnCarEdite.js"></script>
</head>

<body>

	<form id="form1" class="stdform stdform2" method="post" action="">
		<p>
			<label>选择还车租赁点</label> <span class="field"><select
				data-placeholder="选择租赁点" name="relityReturnSiteId"
				id="relityReturnSiteId" class="chzn-select" style="width: 200px;"
				tabindex="2">
					<option value=""></option>
			</select></span>
		</p>
		<p>
			<label>还车时间</label> <span class="field"><input type="text"
				name="reaReturnTime" id="realityReturnTime"
				class="longinput hasDatepicker" /> <input type="hidden"
				name="orderPO.realityGetTime" id="realityGetTime"
				value="${orderPO.realityGetTime}" /> <input type="hidden"
				name="orderPO.kmsForReturn" id="kmsForReturn" class="longinput"
				style="width: 200px;" value="0" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>车牌号码</label> <span class="field">${orderPO.carPO.carNumber}&nbsp;&nbsp;
				<input type="hidden" name="id" value="${orderPO.id}" /> <input
				type="hidden" name="carId" value="${orderPO.carId}" /><input
				type="hidden" name="orderType" value="${orderPO.orderType}" /><input
				type="hidden" name="priceTypeTableName" value="${orderPO.priceTypeTableName}" />
			</span>
		</p>
		<p>
			<label>订单编号</label> <span class="field">ZCDD_${orderPO.id}&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>取车租赁点</label> <span class="field">${orderPO.gsitePO.siteName}&nbsp;</span>
		</p>
		<p>
			<label>姓名</label> <span class="field">${orderPO.merchantUserPO.userName}&nbsp;</span>
		</p>	
		<p>
			<label>取车时间</label> <span class="field">${orderPO.realityGetTime}&nbsp;</span>
		</p>
		<p>
			<label>取车车辆码表公里数</label> <span class="field">${orderPO.kmsForGet}&nbsp;</span>
		</p>
			<p>
			<label>取车车辆续航里程</label> <span class="field">${orderPO.surplusKmsForGet}&nbsp;</span>
		</p>
		<p>
			<label>取车经办人</label> <span class="field">${orderPO.menForGet}&nbsp;</span>
		</p>

	</form>

</body>
</html>
