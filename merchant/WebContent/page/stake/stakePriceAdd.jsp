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
	src="${ctx}/js/page/stake/stakePriceAdd.js"></script>
</head>
			<div id="contentwrapper" class="contentwrapper">
				<div id="list" class="subcontent">
					<form id="form2" class="stdform stdform2" method="post">
						<p>
							<label>套餐名称</label> <span class="field"><input type="text"
								style="width: 200px;" name="name" id="name" class="longinput" />
							</span>
						</p>
						<input type="hidden" name="id" id="id" value="-1"/>
						<p>
							<label>时间段A开始时间</label> <span class="field"> <select
								data-placeholder="选择开始时间" name="time1" id="time1"
								class="chzn-select" style="width: 210px;" tabindex="2">
									<option value=""></option>
							</select>
							</span>
						</p>
						<p>
							<label>收费A</label> <span class="field"> <input
								type="text" style="width: 200px;" id="PriceA" name="PriceA" class="longinput"/>
							</span>
						</p>
						<p>
							<label>时间段B开始时间</label> <span class="field"><select
								data-placeholder="选择开始时间" name="time2" id="time2"
								class="chzn-select" style="width: 210px;" tabindex="2">
									<option value=""></option>
							</select>
							</span>
						</p>
						<p>
							<label>收费B</label> <span class="field"> <input
								type="text" style="width: 200px;" id="PriceB" name="PriceB" class="longinput"/>
							</span>
						</p>
						<p> 
							<label>时间段C开始时间</label> <span class="field"><select
								data-placeholder="选择开始时间" name="time3" id="time3"
								class="chzn-select" style="width: 210px;" tabindex="2">
									<option value=""></option>
							</select>
							</span>
						</p>
						<p>
							<label>收费C</label> <span class="field"> <input
								type="text" style="width: 200px;" id="PriceC" name="PriceC" class="longinput"/>
							</span>
						</p>
						<p>
							<label>时间段D开始时间</label> <span class="field"><select
								data-placeholder="选择开始时间" name="time4" id="time4"
								class="chzn-select" style="width: 210px;" tabindex="2">
									<option value=""></option>
							</select>
							</span>
						</p>
						<p>
							<label>收费D</label> <span class="field"> <input
								type="text" style="width: 200px;" id="PriceD" name="PriceD" class="longinput"/>
							</span>
						</p>
						<p>
							<label>备注信息</label> <span class="field"> <textarea  class="longinput"
								style="width: 200px;" name="memo" id="memo"></textarea>
							</span>
						</p>
					</form>
					<br />
				</div>
			</div>
</html>
