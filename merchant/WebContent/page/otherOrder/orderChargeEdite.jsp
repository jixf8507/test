<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/plugins/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/page/otherOrder/orderChargeEdite.js"></script>
</head>

<body>
	<form id="form1" class="stdform stdform2" method="post">
		<div id="wizard" class="wizard">
			<br />
			<ul class="hormenu">
				<li><a href="#wiz1step1"> <span class="h2">第一步</span> <span
						class="dot"><span></span></span> <span class="label">选择收费车辆</span>
				</a></li>
				<li><a href="#wiz1step2"> <span class="h2">第二步</span> <span
						class="dot"><span></span></span> <span class="label">取/还车信息</span>
				</a></li>
				<li><a href="#wiz1step3"> <span class="h2">第三步</span> <span
						class="dot"><span></span></span> <span class="label">确认收费</span>
				</a></li>
			</ul>
			<br clear="all" /> <br /> <br />

			<div id="wiz1step1" class="formwiz">
				<h4>第一步: 车辆信息</h4>
				<p></p>
				<p>
					<label>车牌号码</label> <span class="field">${orderPO.carPO.carNumber}&nbsp;
						<input type="hidden" name="id" value="${orderPO.id}" /> 
					</span>
				</p>
				<p>
					<label>订单编号</label> <span class="field">ZCDD_${orderPO.id}&nbsp;</span>
				</p>
				<p>
					<label>联系人</label> <span class="field">${orderPO.merchantUserPO.userName}&nbsp;</span>
				</p>
				<p>
					<label>角色</label> <span class="field">${orderPO.merchantUserPO.rights}&nbsp;&nbsp;</span>
				</p>
				<p>
					<label>联系电话</label> <span class="field">${orderPO.merchantUserPO.mobilePhone}&nbsp;&nbsp;</span>
				</p>
			</div>
			<!--#wiz1step1-->
			<br/>
			<div id="wiz1step2" class="formwiz">
				<h4>第二步: 取/还车信息</h4>
				<p></p>
				<p>
					<label>取车租赁点</label> <span class="field">${orderPO.gsitePO.siteName}&nbsp;</span>
				</p>
				<p>
					<label>取车时间</label> <span class="field">${orderPO.realityGetTime}&nbsp;</span>
				</p>
				<p>
					<label>取车续航里程</label> <span class="field">${orderPO.surplusKmsForGet}&nbsp;</span>
				</p>
				<p>
					<label>取车码表公里数</label> <span class="field">${orderPO.kmsForGet}&nbsp;</span>
				</p>
				<p>
					<label>取车经办人</label> <span class="field">${orderPO.menForGet}&nbsp;</span>
				</p>
				<p>
					<label>还车租赁点</label> <span class="field">${orderPO.rsitePO.siteName}&nbsp;</span>
					<input type="hidden" id="relityReturnSiteId"
						name="relityReturnSiteId" value="${orderPO.relityReturnSiteId}" />
				</p>
				<p>
					<label>还车时间</label> <span class="field">${orderPO.realityReturnTime}&nbsp;</span>
				</p>
				<p>
					<label>还车续航里程</label> <span class="field">${orderPO.surplusKmsForReturn}&nbsp;</span>
				</p>
				<p>
					<label>还车码表公里数</label> <span class="field">${orderPO.kmsForReturn}&nbsp;</span>
				</p>
				<p>
					<label>还车经办人</label> <span class="field">${orderPO.menForReturn}&nbsp;</span>
				</p>
				<%--<p>
					<label>取车详情</label> <span class="field">${orderPO.getCarStatus}&nbsp;</span>
				</p>
				<p>
					<label>还车详情</label> <span class="field">${orderPO.returnCarStatus}&nbsp;</span>
				</p> --%>
			</div>
			<!--#wiz1step2-->
			<br/>
			<div id="wiz1step3" class="formwiz">
				<h4>第三步: 确认收费</h4>
				<p></p>
				<p>
					<label>用车时间</label> <span class="field">${fn:replace(orderPO.useTime, ":", "小时")}分&nbsp;
					</span>
				</p>
				<p>
					<label>用车事由</label> <span class="field">${orderPO.orderDescribe }&nbsp;
					</span>
				</p>
				<p>
					<label>租赁收费</label> <span class="field"><input type="text"
						value="${orderPO.useCost}" name="useCost" id="useCost"
						readonly="readonly" style="width: 200px;" />&nbsp;&nbsp;(元)<input
						type="hidden" value="${orderPO.useCost}" id="hid_useCost" /></span>
				</p>
				<p>
					<label>行驶公里数</label> <span class="field"><input type="text"
						value="${orderPO.kmsForReturn-orderPO.kmsForGet}" id="kms"
						style="width: 200px;" readonly="readonly" />&nbsp;&nbsp;(公里)</span>
				</p>
				<input type="hidden" id="perKmsCost" value="${orderPO.perKmsCost}" />
				<p>
					<label>维修收费</label> <span class="field"><input type="text"
						value="${orderPO.maintenanceCost}" name="maintenanceCost"
						id="maintenanceCost" readonly="readonly" style="width: 200px;" />&nbsp;&nbsp;(元)</span>
				</p>
				<p>
					<label>其它收费</label> <span class="field"><input type="text"
						value="${orderPO.otherCost}" name="otherCost" id="otherCost"
						readonly="readonly" style="width: 200px;" />&nbsp;&nbsp;(元)</span>
				</p>
				<p>
					<label>其它收费说明</label> <span class="field"><input type="text"
						name="otherDescribe" id="otherDescribe" readonly="readonly"
						value="${orderPO.otherDescribe}" style="width: 200px;" />&nbsp;&nbsp;</span>
				</p>
				<p>
					<label>总收费</label> <span class="field"><input type="text"
						name="totalCost" id="totalCost" readonly="readonly"
						value="${orderPO.totalCost}" class="longinput"
						style="width: 200px;" />&nbsp;&nbsp;(元)</span>
				</p>
			</div>
			<!--#wiz1step2-->
		</div>
		<!--#wizard-->
	</form>
</body>
</html>
