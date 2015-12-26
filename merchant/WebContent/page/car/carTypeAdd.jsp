<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/plugins/chosen.jquery.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/js/page/car/carTypeAdd.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">添加车辆型号</h1>
			</div>
			<!--pageheader-->

			<div id="contentwrapper" class="contentwrapper">
				<div id="list" class="subcontent">
					<form id="form1" class="stdform stdform2" method="post"
						action="${ctx}/car/type/saveAdd.htm?">
						<p>
							<label>车辆型号</label> <span class="field"><input type="text"
								name="typeName" id="siteName" class="longinput" style="width: 350px;" /></span>
						</p>
						<p>
							<label>供应商选择</label> <span class="field"> <select
								name="carManufacturerId" id="carManufacturerId">
									<option value="">请选择</option>
							</select>
							</span>
						</p>
						<p>
							<label>座位数</label><span class="field"><input type="text"
								name="seatCount" id="seatCount" class="longinput"  style="width: 350px;"/></span>
						</p>
						<p>
							<label>车颜色</label> <span class="field"><input type="text"
								name="color" id="color" class="longinput"  style="width: 350px;"/></span> <small
								class="desc">注：多种颜色用","号隔开。</small>
						</p>
						<p>
							<label>供能方式</label> <span class="field"> <select
								name="energy" id="energy">
									<option value="">请选择</option>
									<option value="电动">电动</option>
									<option value="燃油">燃油</option>
							</select>
							</span>
						</p>

						<p>
							<table cellpadding="0" cellspacing="0" border="0"
								class="stdtable">
								<colgroup>
									<col class="con0" />
									<col class="con1" />
									<col class="con0" />
									<col class="con1" />
								</colgroup>
								<thead>
									<tr>
										<th class="head0">审核检测项</th>
										<th class="head1">当前状态</th>
										<th class="head0">描述</th>
										<th class="head1"><input type="button" value="新增"
											onclick="checkDetailTable.add('','正常','正常')" /></th>
									</tr>
								</thead>
								<tbody id="checkDetailTable">

								</tbody>
							</table>
						</p>
						<p class="stdformbutton">
							<button class="submit radius2">保存</button>
							<input type="reset" class="reset radius2" value="重置" />
						</p>
					</form>
					<br />
				</div>
				<!-- #add -->

			</div>
			<!--contentwrapper-->
			<br clear="all" />
		</div>
		<!-- centercontent -->
	</div>
	<!--bodywrapper-->

</body>
</html>
