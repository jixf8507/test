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
	src="${ctx}/js/page/asset/assetEdit.js"></script>
</head>

<body class="withvernav">

					<form id="form1" class="stdform stdform2" method="post">
						
						<p> 
							<label>资产类型</label> <span class="field"> 
							<select id="categoryId" name="categoryId" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择</option>
							</select>&nbsp;&nbsp;
							</span>
						</p>
						
						<input type="hidden" id="hid_categoryId" value="${assetPO.categoryId }"/>
						<input type="hidden" id="hid_manufacturId" value="${assetPO.manufacturId }"/>
						<input type="hidden" name="id" value="${assetPO.id }"/>
						
						<p>
							<label>资产名称</label> <span class="field"> <input value="${assetPO.assetsName }"
								type="text" name="assetsName" id="assetsName" class="longinput"
								style="width:200px;" />&nbsp;&nbsp;
							</span>
						</p>
						<p>
							<label>资产型号</label> <span class="field"> <input value="${assetPO.model }"
								type="text" name="model" id="model" class="longinput"
								style="width:200px;" />&nbsp;&nbsp;
							</span>
						</p>
						<p>
							<label>增加方式</label> <span class="field"> 
							<select id="increasingMode" name="increasingMode" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择增加方式</option>
								<option value="外购"<c:if test="${assetPO.increasingMode == '外购' }">selected="selected"</c:if> >外购</option>
								<option value="捐赠"<c:if test="${assetPO.increasingMode == '捐赠' }">selected="selected"</c:if> >捐赠</option>
							</select>&nbsp;&nbsp;
							</span>
						</p>
						<p>
							<label>计量单位</label> <span class="field"> 
							<select id="unit" name="unit" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择计量单位</option>
								<option value="个"<c:if test="${assetPO.unit == '个' }">selected="selected"</c:if> >个</option>
								<option value="台"<c:if test="${assetPO.unit == '台' }">selected="selected"</c:if> >台</option>
								<option value="辆"<c:if test="${assetPO.unit == '辆' }">selected="selected"</c:if> >辆</option>
								<option value="张"<c:if test="${assetPO.unit == '张' }">selected="selected"</c:if> >张</option>
								<option value="箱"<c:if test="${assetPO.unit == '箱' }">selected="selected"</c:if> >箱</option>
							</select>&nbsp;&nbsp;
							</span>
						</p>
						<p>
							<label>供应商选择</label> <span class="field"> <select
								name="manufacturId" id="manufacturId">
									<option value="">请选择</option>
							</select>
							</span>
						</p>
						<p>
							<label>购置日期</label> <span class="field"> <input  value="${assetPO.purchaseDate }"
								type="text" name="purchaseDate" id="purchaseDate" class="longinput"
								style="width:200px;" />&nbsp;&nbsp;
							</span>
						</p>
						<p>
							<label>费用</label> <span class="field"> <input value="${assetPO.fee }"
								type="text" name="fee" id="fee" class="longinput"
								style="width:200px;" />&nbsp;&nbsp;
							</span>
						</p>
						
					</form>
</body>
</html>
