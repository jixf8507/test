<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/page/stake/print.js"></script>
</head>
			<div id="contentwrapper" class="contentwrapper">
				<div id="list" class="subcontent">
					<form id="form1" class="stdform stdform2" method="post">
						<p>
							<label>充电订单号</label> <span class="field">${costPO.id }&nbsp;&nbsp;
							</span>
						</p>
						<p>
							<label>充电卡号</label> <span class="field">${costPO.cardID }&nbsp;&nbsp;
							</span>
						</p>
						<p>
							<label>充电方式</label> <span class="field">${costPO.kind }&nbsp;&nbsp;
							</span>
						</p>
						<p>
							<label>客户</label> <span class="field">${costPO.customerPO.name }&nbsp;&nbsp;
							</span>
						</p>
						<p>
							<label>联系电话</label> <span class="field">${costPO.customerPO.phone }&nbsp;&nbsp;
							</span>
						</p>
						<p>
							<label>开始充电时间</label> <span class="field">${costPO.startCharge }&nbsp;&nbsp;
							</span>
						</p>
						<p>
							<label>结束充电时间</label> <span class="field">${costPO.lastCharge }&nbsp;&nbsp;
							</span>
						</p>
						<p>
							<label>充电电量</label> <span class="field">${costPO.EQ }&nbsp;&nbsp;( 度 )
							</span>
						</p>
						<p>
							<label>收费</label> <span class="field">${costPO.cost }&nbsp;&nbsp;( 元 )
							</span>
						</p>
						<p>
							<label>充电地点</label> <span class="field">${costPO.sitePO.siteName }&nbsp;&nbsp;
							</span>
						</p>
						<p>
							<label>充电商家</label> <span class="field">${costPO.merchantPO.merchantName }&nbsp;&nbsp;
							</span>
						</p>
						
					</form>
					<br />
				</div>
			</div>
</html>
