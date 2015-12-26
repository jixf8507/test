<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<link rel="stylesheet" href="${ctx}/js/imgSwitch/css/style.css"
	type="text/css" />
<script type="text/javascript" src="${ctx}/js/page/order/orderDetail.js"></script>
</head>

<body>

	<div class="pageheader">
		<ul class="hornav">
			<li class="current"><a href="#customer">用户信息</a></li>
			<li><a href="#order">取还车详情</a></li>
			<li><a href="#charge">收费详情</a></li>
			<li><a href="#get" id="getPic">取车图片</a></li>
			<c:if
				test="${orderPO.orderStatus == '已还车' || orderPO.orderStatus == '已付费'}">
				<li><a href="#return" id="returnPic">还车图片</a></li>
			</c:if>

		</ul>
	</div>
	<!--pageheader-->

	<div id="contentwrapper" class="contentwrapper">
		<div id="customer" class="subcontent">
			<form class="stdform stdform2">
				<p>
					<label>车牌号码</label> <span class="field">${orderPO.carPO.carNumber}&nbsp;&nbsp;</span>
				</p>
				<p>
					<label>订单编号</label> <span class="field">ZCDD_${orderPO.id}&nbsp;&nbsp;</span>
				</p>
				<p>
					<label>租赁套餐</label> <span class="field">${orderPO.priceTypePO.typeName}&nbsp;&nbsp;</span>
				</p>

				<c:if test="${orderPO.unitCustomerPO.enterpriseName == null}">
					<p>
						<label>客户姓名</label> <span class="field">${orderPO.customerPO.name}&nbsp;&nbsp;</span>
					</p>
					<p>
						<label>账户余额</label> <span class="field">${orderPO.customerPO.accountPO.balance}&nbsp;&nbsp;</span>
					</p>
					<p>
						<label>保证金</label> <span class="field">${orderPO.customerPO.accountPO.moneyOfassure}&nbsp;&nbsp;</span>
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
					<p>
						<label>账户余额</label> <span class="field">${orderPO.unitCustomerPO.balance}&nbsp;&nbsp;</span>
					</p>
					<p>
						<label>保证金</label> <span class="field">${orderPO.unitCustomerPO.moneyOfassure}&nbsp;&nbsp;</span>
					</p>
				</c:if>
			</form>
		</div>

		<div id="order" class="subcontent" style="display: none;">
			<form class="stdform stdform2">
				<p>
					<label>取车租赁点</label> <span class="field">${orderPO.gsitePO.siteName}
						&nbsp;&nbsp;</span>
				</p>
				<p>
					<label>取车时间</label> <span class="field">${orderPO.realityGetTime}&nbsp;&nbsp;</span>
				</p>
				<p>
					<label>最迟还车时间</label> <span class="field">${orderPO.schemingReturnTime}&nbsp;&nbsp;</span>
				</p>
				<p>
					<label>取车车辆码表公里数</label> <span class="field">${orderPO.kmsForGet}&nbsp;&nbsp;</span>
				</p>
				<p>
					<label>取车车辆续航里程</label> <span class="field">${orderPO.surplusKmsForGet}&nbsp;&nbsp;</span>
				</p>
				<p>
					<label>取车经办人</label> <span class="field">${orderPO.menForGet}&nbsp;&nbsp;</span>
				</p>
				<p>
					<label>还车租赁点</label> <span class="field"><c:if
							test="${orderPO.orderStatus == '已还车' || orderPO.orderStatus == '已付费'}">${orderPO.rsitePO.siteName}</c:if>&nbsp;&nbsp;</span>
				</p>
				<p>
					<label>还车时间</label> <span class="field">${orderPO.realityReturnTime}&nbsp;&nbsp;</span>
				</p>
				<p>
					<label>还车车辆码表公里数</label> <span class="field"><c:if
							test="${orderPO.orderStatus == '已还车' || orderPO.orderStatus == '已付费'}">${orderPO.kmsForReturn}</c:if>&nbsp;&nbsp;</span>
				</p>
				<p>
					<label>还车车辆续航里程</label> <span class="field"><c:if
							test="${orderPO.orderStatus == '已还车' || orderPO.orderStatus == '已付费'}">${orderPO.surplusKmsForReturn}</c:if>&nbsp;&nbsp;</span>
				</p>
				<p>
					<label>还车经办人</label> <span class="field">${orderPO.menForReturn}&nbsp;&nbsp;</span>
				</p>
				<%-- <p>
					<label>取车详情</label> <span class="field" id="getCarStatus">${orderPO.getCarStatus}&nbsp;</span>
				</p>
				<p>
					<label>还车详情</label> <span class="field" id="returnCarStatus">${orderPO.returnCarStatus}&nbsp;</span>
				</p> --%>
			</form>
		</div>
		<!--#wiz1step2-->

		<div id="charge" class="subcontent" style="display: none;">
			<form class="stdform stdform2">
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
					<label>优惠券类型</label> <span class="field"><c:if
							test="${orderPO.couponPo.couponType == 1}">现金优惠券</c:if> <c:if
							test="${orderPO.couponPo.couponType == 2}">里程优惠券</c:if>
						&nbsp;&nbsp;</span>
				</p>
				<p>
					<label>优惠券金额</label> <span class="field"><c:if
							test="${orderPO.couponId != 0&&orderPO.couponId !=null}">-${orderPO.couponPo.balance}&nbsp;&nbsp;(元)</c:if>&nbsp;&nbsp;</span>
				</p>
				<p>
					<label>优惠金额</label> <span class="field">${orderPO.couponCost}&nbsp;&nbsp;(元)</span>
				</p>
				<p>
					<label>总收费</label> <span class="field">${orderPO.totalCost}&nbsp;&nbsp;(元)</span>
				</p>
				<p>
					<label>收费方式</label> <span class="field">${orderPO.payment}&nbsp;&nbsp;</span>
				</p>
				<p>
					<label>票据编号</label> <span class="field">${orderPO.ticket}&nbsp;&nbsp;</span>
				</p>
				<p>
					<label>收费经办人</label> <span class="field">${orderPO.menForCharge}&nbsp;&nbsp;</span>
				</p>
				<p>
					<label>核对描述</label> <span class="field">${orderPO.checkDescribe}&nbsp;&nbsp;</span>
				</p>
				<p>
					<label>核对人</label> <span class="field">${orderPO.checkUser}&nbsp;&nbsp;</span>
				</p>
			</form>
		</div>

		<!-- #activities -->

		<div id="get" class="subcontent" style="display: none;">
			<form class="stdform stdform2">
				<!-- 取车轮播图片 -->
				<div id="home_banner">

					<a id="big_a"><div id="big_img"></div></a>

					<div class="relative">
						<div id="small_img">
							<div class="maxwidth">
								<a id="small_pre"><</a>
								<div id="small_imgs"></div>
								<a id="small_next">></a>
							</div>
						</div>
					</div>
				</div>
				<!-- 取车轮播图片 -->

				<input type="hidden" name="tableId" id="tableId"
					value="${orderPO.id}" /> <input type="hidden" name="tableName"
					id="tableName" value="order" /><input type="hidden" name="carId"
					id="carId" value="${orderPO.carId}" />

			</form>
		</div>


		<div id="return" class="subcontent" style="display: none;">
			<form class="stdform stdform2">
				<!-- 还车轮播图片 -->
				<div id="home_banner1">

					<a id="big_a1"><div id="big_img1"></div></a>

					<div class="relative1">
						<div id="small_img1">
							<div class="maxwidth1">
								<a id="small_pre1"><</a>
								<div id="small_imgs1"></div>
								<a id="small_next1">></a>
							</div>
						</div>
					</div>
				</div>
				<!-- 还车轮播图片 -->
			</form>

		</div>

		<br />
	</div>
</body>
</html>
