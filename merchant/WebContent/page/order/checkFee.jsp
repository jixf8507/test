<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript" src="${ctx}/js/page/order/checkFee.js"></script>
<script type="text/javascript"
	src="${ctx}/js/plugins/jquery.validate.min.js"></script>
</head>
<body>
	<div id="contentwrapper" class="contentwrapper">
		<form id="form1" class="stdform stdform2">
			<p>
				<label>票据编号</label> <span class="field"><input type="text"
					style="width: 200px;" value="${orderPO.ticket}" name="ticket"
					id="ticket" />&nbsp;&nbsp;</span> <input type="hidden"
					value="${orderPO.id}" name="id" />
			</p>
			<p>
				<label>核对描述</label> <span class="field"><input type="text"
					style="width: 200px;" value="${orderPO.checkDescribe}"
					name="checkDescribe" id="checkDescribe" />&nbsp;&nbsp;</span>
			</p>

			<p>
				<label>订单编号</label> <span class="field">ZCDD_${orderPO.id}&nbsp;&nbsp;</span>
			</p>
			<c:if test="${orderPO.unitCustomerPO.enterpriseName == null}">
				<p>
					<label>客户姓名</label> <span class="field">${orderPO.customerPO.name}&nbsp;&nbsp;</span>
				</p>
				<p>
					<label>联系电话</label> <span class="field">${orderPO.customerPO.phone}&nbsp;&nbsp;</span>
				</p>
			</c:if>

			<c:if test="${orderPO.customerPO.name == null}">
				<p>
					<label>企业名称</label> <span class="field">${orderPO.unitCustomerPO.enterpriseName}&nbsp;&nbsp;</span>
				</p>
				<p>
					<label>联系人</label> <span class="field">${orderPO.unitCustomerPO.contactPerson}&nbsp;&nbsp;</span>
				</p>
				<p>
					<label>联系电话</label> <span class="field">${orderPO.unitCustomerPO.contactPhone}&nbsp;&nbsp;</span>
				</p>
			</c:if>

			<p>
				<label>行驶公里数</label> <span class="field">
					${orderPO.kmsForReturn-orderPO.kmsForGet} &nbsp;&nbsp;(公里数)</span>
			</p>
			<p>
				<label>租赁收费</label> <span class="field">${orderPO.useCost}&nbsp;&nbsp;(元)</span>
			</p>
			<c:if test="${orderPO.unitCustomerPO.enterpriseName == null}">
				<p>
					<label>超出公里数</label> <span class="field">${orderPO.perKms}&nbsp;&nbsp;(公里)</span>
				</p>
				<p>
					<label>超出公里数收费</label> <span class="field">${orderPO.perKmsCost}&nbsp;&nbsp;(元)</span>
				</p>
			</c:if>
			<p>
				<label>其它收费</label> <span class="field">${orderPO.otherCost}&nbsp;&nbsp;(元)</span>
			</p>
			<p>
				<label>其它收费说明</label> <span class="field">${orderPO.otherDescribe}&nbsp;&nbsp;</span>
			</p>
			<p>
				<label>维修收费</label> <span class="field">${orderPO.maintenanceCost}&nbsp;&nbsp;(元)</span>
			</p>
			<p>
				<label>优惠券编号</label> <span class="field"><c:if
						test="${orderPO.couponId != 0&&orderPO.couponId !=null}">ykzc-${orderPO.couponId}</c:if>&nbsp;&nbsp;</span>
			</p>
			<p>
				<label>优惠券名称</label> <span class="field">${orderPO.couponPo.couponName}&nbsp;&nbsp;</span>
			</p>
			<p> 
				<label>优惠券金额</label> <span class="field"><c:if
						test="${orderPO.couponId != 0&&orderPO.couponId !=null}">-${orderPO.couponPo.balance}&nbsp;&nbsp;(元)</c:if>&nbsp;&nbsp;</span>
			</p>
			<p>
				<label>总收费</label> <span class="field">${orderPO.totalCost}&nbsp;&nbsp;(元)</span>
			</p>
			<p>
				<label>收费方式</label> <span class="field">${orderPO.payment}&nbsp;&nbsp;</span>
			</p>
			<p>
				<label>收费经办人</label> <span class="field">${orderPO.menForCharge}&nbsp;&nbsp;</span>
			</p>
		</form>
	</div>
</body>
</html>
