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
	src="${ctx}/js/page/order/orderChargeEdite.js"></script>
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
						<input type="hidden" name="id" value="${orderPO.id}" /> <input
						type="hidden" id="accountId" name="accountId"
						value="${orderPO.customerPO.accountPO.id}" />
					</span>
				</p>
				<p>
					<label>订单编号</label> <span class="field">ZCDD_${orderPO.id}&nbsp;</span>
				</p>
				<p>
					<label>租赁套餐</label> <span class="field">${orderPO.priceTypePO.typeName}&nbsp;</span>
				</p>

				<c:if test="${orderPO.unitCustomerPO.enterpriseName == null}">
					<p>
						<label>客户姓名</label> <span class="field">${orderPO.customerPO.name}&nbsp;</span>
						<input type="hidden" id="customerId" name="customerId"
							value="${orderPO.customerId}" />
					</p>
					<p>
						<label>账户余额</label> <span class="field">${orderPO.customerPO.accountPO.balance}&nbsp;</span>
					</p>
					<input type="hidden" id="balance" name="balance"
						value="${orderPO.customerPO.accountPO.balance}" />
				</c:if>

				<c:if test="${orderPO.customerPO.name == null}">
					<p>
						<label>企业名称</label> <span class="field">${orderPO.unitCustomerPO.enterpriseName}&nbsp;</span>
						<input type="hidden" id="customerId" name="customerId"
							value="${orderPO.customerId}" />
					</p>
					<p>
						<label>账户余额</label> <span class="field">${orderPO.unitCustomerPO.balance}&nbsp;</span>
					</p>
					<input type="hidden" id="balance" name="balance"
						value="${orderPO.unitCustomerPO.balance}" />
				</c:if>
			</div>
			<!--#wiz1step1-->
			<br />
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
				<%-- <p>
					<label>取车详情</label> <span class="field">${orderPO.getCarStatus}&nbsp;</span>
				</p>
				<p>
					<label>还车详情</label> <span class="field">${orderPO.returnCarStatus}&nbsp;</span>
				</p> --%>
			</div>
			<!--#wiz1step2-->
			<br />
			<div id="wiz1step3" class="formwiz">
				<h4>第三步: 确认收费</h4>
				<p></p>
				<p>
					<label>用车时间</label> <span class="field">${fn:replace(orderPO.useTime, ":", "小时")}分&nbsp;
					</span>
				</p>
				<p>
					<label>租赁收费</label> <span class="field"><input type="text"
						value="${orderPO.useCost}" name="useCost" id="useCost"
						readonly="readonly" style="width: 200px;" />&nbsp;&nbsp;(元)</span>
				</p>
				<p>
					<label>行驶公里数</label> <span class="field"><input type="text"
						value="${orderPO.kmsForReturn-orderPO.kmsForGet}" id="kms"
						style="width: 200px;" readonly="readonly" />&nbsp;&nbsp;(公里)</span>
				</p>
				<c:if test="${orderPO.customerPO.name != null}">
					<p>
						<label>超出公里数</label> <span class="field"><input type="text"
							value="${orderPO.perKms}" name="perKms" id="perKms"
							readonly="readonly" style="width: 200px;" />&nbsp;&nbsp;(公里)</span>
					</p>
					<p>
						<label>超出公里数收费</label> <span class="field"><input
							type="text" name="perKmsCost" id="perKmsCost"
							value="${orderPO.perKmsCost}" readonly="readonly"
							style="width: 200px;" />&nbsp;&nbsp;(元)</span>
					</p>
				</c:if>
				<c:if test="${orderPO.customerPO.name == null}">
					<input type="hidden" id="perKmsCost" name="perKmsCost"
						value="${orderPO.perKmsCost}" />
				</c:if>

				<input type="hidden" id="orderType" value="${orderPO.orderType}" />
				<input type="hidden" id="perKmsCost" value="${orderPO.perKmsCost}" />
				<input type="hidden" id="hid_perKmsCost"
					value="${orderPO.perKmsCost}" />
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
					<label>优惠券编码</label> <span class="field"><input type="text"
						name="couponNo" id="couponNo" class="longinput"
						readonly="readonly" style="width: 200px;" onclick="getCoupons()" />&nbsp;&nbsp;<input
						type="button" value="使用优惠券" onclick="getCoupons()" /> <input
						type="hidden" name="couponId" id="couponId" /> </span>
				</p>
				<p>
					<label>优惠券类型</label> <span class="field"><input type="text"
						id="couponType" class="longinput" readonly="readonly"
						style="width: 200px;" />&nbsp;&nbsp;</span>
				</p>
				<p>
					<label>优惠券金额</label> <span class="field"><input type="text"
						readonly="readonly" name="couponBalance" id="couponBalance"
						class="longinput" style="width: 200px;" /> &nbsp;&nbsp;</span>
				</p>
				<p>
					<label>优惠费用</label> <span class="field"><input type="text"
						readonly="readonly" name="couponCost" id="couponCost" value="0.00"
						class="longinput" style="width: 200px;" /> &nbsp;&nbsp;</span>
				</p>
				<p>
					<label>总收费</label> <span class="field"><input type="text"
						name="totalCost" id="totalCost" readonly="readonly"
						value="${orderPO.totalCost}" class="longinput"
						style="width: 200px;" />&nbsp;&nbsp;(元)<input type="hidden"
						value="${orderPO.totalCost}" id="hid_totalCost" /></span>
				</p>
				<p>
					<label>付费方式</label> <span class="field"> <input type="radio"
						value="刷卡付费" name="payment" checked="checked" />&nbsp;刷卡付费
						&nbsp;&nbsp; <input type="radio" value="现金付费" name="payment" />&nbsp;现金付费
						&nbsp;&nbsp; <input type="radio" value="余额付费" name="payment" />&nbsp;余额付费
						&nbsp;&nbsp;
					</span>
				</p>
				<p>
					<label>票据编号</label> <span class="field"><input type="text"
						name="ticket" id="ticket" class="longinput" style="width: 200px;" />&nbsp;&nbsp;</span>
				</p>
			</div>
			<!--#wiz1step2-->
		</div>
		<!--#wizard-->
	</form>
</body>
</html>
