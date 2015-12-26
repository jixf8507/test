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
<script type="text/javascript" src="${ctx}/js/ajaxfileupload.js"></script>
<script type="text/javascript"
	src="${ctx}/js/page/merchant/editFamily.js"></script>
</head>

<body>

	<div class="pageheader">
		<ul class="hornav">
			<li class="current"><a href="#familyInfoDiv">家庭信息</a></li>
			<li><a href="#memberDiv" id="member">成员管理</a></li>
		</ul>
	</div>

	<div id="contentwrapper" class="contentwrapper">


		<div id="familyInfoDiv" class="subcontent">
			<br clear="all" />
			<form id="form1" class="stdform stdform2" method="post" action="">
				<p>
					<input type="hidden" name="id" id="familyId"
						value="${merchantFamilyPO.id}" />
				</p>
				<p>
					<input type="hidden" name="customerid" id="customerId"
						value="${merchantFamilyPO.customerid}" />
				</p>
				<p>
					<input type="hidden" name="newCustomerid" id="newCustomerId"
						value="${merchantFamilyPO.customerid}" />
				</p>
				<p>
					<input type="hidden" name="status" id="status"
						value="${merchantFamilyPO.status}" />
				</p>
				<p>
					<label>负责人</label> <span class="field"><input type="text" readonly="readonly"
						name="householder" id="householder"
						value="${merchantFamilyPO.householder}" class="longinput"
						style="width: 350px;" />&nbsp;&nbsp;<input type="button"
						id="changeHouseHolder" value="修改负责人" /> </span>
				</p>

				<p>
					<label>家庭住址</label> <span class="field"><input type="text"
						name="homeaddress" id="homeaddress"
						value="${merchantFamilyPO.homeaddress}" class="longinput"
						id="idCard" style="width: 350px;" />&nbsp;&nbsp;</span>
				</p>
				<p>
					<label>联系方式</label> <span class="field"><input type="text"
						name="homephone" id="homephone"
						value="${merchantFamilyPO.homephone}" class="longinput"
						style="width: 350px;" />&nbsp;&nbsp;</span>
				</p>
				<p>
					<label>保证金</label> <span class="field"><input type="text" readonly="readonly"
						name="moneyOfAssure" id="moneyOfAssure"
						value="${merchantFamilyPO.moneyOfAssure}" class="longinput"
						style="width: 350px;" />&nbsp;&nbsp;</span>
				</p>
				<p>
					<label>身份证/户口本图片上传</label> <span class="field"> <input
						type="file" id="idFamilyImgFile" /> <span
						style="color: red; font-size: 14px;">注：只能上传jpg或png文件 <br />
							<br /> <img id="idFamilyImg"
							src="${ctx}${merchantFamilyPO.sysFileUrlsPO.fileUrl}" alt="家庭图片"
							class="uploadImg" /> <input type="hidden" name="familyImgId"
							id="familyImgId" value="${merchantFamilyPO.sysFileUrlsPO.id}" />

							<input type="hidden" name="idFamilyImgHidden"
							id="idFamilyImgHidden" /> <input type="hidden"
							name="familyImgHidden" id="familyImgHidden" />

					</span>
					</span>
				</p>

			</form>
			<!-- form1 -->
			<br /> <br />
		</div>
		<!-- familyInfoDiv -->


		<div id="memberDiv" class="subcontent" style="display: none">
			<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
				id="memberTable">
				<colgroup>
					<col class="con0" />
					<col class="con1" />
					<col class="con0" />
					<col class="con1" />
					<col class="con0" />
					<col class="con1" />
					<col class="con0" />
					<col class="con1" />
					<col class="con0" />
					<col class="con1" />
				</colgroup>
				<thead>
					<tr>
						<th class="head1 "><input style="width: 20px;"
							type="checkbox" class="checkall" id="all" /></th>
						<th class="head0">身份证/驾驶证</th>
						<th class="head1">手机号码</th>
						<th class="head0">姓名</th>
						<th class="head1">家庭住址</th>
						<th class="head0">保证金</th>
						<th class="head1">余额</th>
						<th class="head0">状态</th>
						<th class="head1">开户时间</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<th class="head1 "><input style="width: 20px;"
							type="checkbox" class="checkall" id="all" /></th>
						<th class="head0">身份证/驾驶证</th>
						<th class="head1">手机号码</th>
						<th class="head0">姓名</th>
						<th class="head1">家庭住址</th>
						<th class="head0">保证金</th>
						<th class="head1">余额</th>
						<th class="head0">状态</th>
						<th class="head1">开户时间</th>
					</tr>
				</tfoot>
				<tbody>

				</tbody>
			</table>


		</div>
		<!-- memberDiv -->

	</div>
	<!--contentwrapper-->


</body>
</html>
