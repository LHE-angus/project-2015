<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css"
	rel="stylesheet" type="text/css" />
<style type="text/css">
select {
	font-family: Microsoft YAHEI;
	font-size: 12px;
}

input {
	font-family: Microsoft YAHEI;
	font-size: 12px;
}

label {
	cursor: pointer;
}

td {
	align: center;
	nowrap: nowrap;
}

.title_item_no_bc {
	align: right;
	text-align: right;
	width: 100px;
	font-weight: bold;
	color: #74685F;
}
</style>
</head>
<body style="font-family: Microsoft Yahei;">
		<div class="oartop">
			<table width="400" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="3%"><img
						src="${ctx}/styles/admin-index/images/k_tup.jpg"
						style="vertical-align: middle;" /></td>
					<td>当前位置：${naviString}</td>
				</tr>
			</table>
		</div>
		<div class="rtabcont2">
			<!--手动添加模块 -->
			<div class="rtabcont2">
			<div align="right"><img src="${ctx}/images/QRCode.jpg?s=http://qdgl.konka.com/webservice/KonkaOrderInfoTransKh.do?param=${trans_index_detail},${user_id},${password}" /></div>
               <div align="left" style="font-weight: bold; color: #74685F;">手动添加订单</div>
               <div style="margin-top:10px;">
               <strong>发货单号：</strong>&nbsp;&nbsp;&nbsp;
				<input name="add_trans_index_detail" id="add_trans_index_detail" style="width: 200px;" maxlength="30" /> <input type="button" value="添加" id="add" class="but2"></input>
					</div>
			</div>
			<div class="tab1" style="overflow-x: auto;">
				<html-el:form  action="/manager/KonkaOrderInfoTrans.do" method="post">
					<html-el:hidden property="method" value="confirmSave" />
					<html-el:hidden property="mod_id" styleId="mod_id" value="${mod_id }"/>
					<html-el:hidden property="trans_id" value="${af.map.trans_id}" />
					<!--上面的详情list---begin -->

					<div align="left" style="font-weight: bold; color: #74685F;margin-bottom:10px;">已选择订购单详细</div>
					<div style="overflow-x:scoll;">
					<table width="100%" id="fhdetails" border="0" cellspacing="0" cellpadding="0" 
						class="rtable2">
						<tr class="tabtt1">
							<td align="center" nowrap="nowrap">物流交货单号</td>
							<td align="center" nowrap="nowrap">订单流水号</td>
							<td align="center" nowrap="nowrap">发货单号</td>
							<td align="center" nowrap="nowrap">提交日期</td>
							<td align="center" nowrap="nowrap">客户名称</td>
							<td align="center" nowrap="nowrap">型号</td>
							<td align="center" nowrap="nowrap">数量</td>
							<td align="center" nowrap="nowrap">已发货数量</td>
							<td align="center" nowrap="nowrap">已签收数量</td>
							<td align="center" nowrap="nowrap">未签收数量</td>
							<td align="center" nowrap="nowrap">本次签收数量<font color="red">*</font></td>
							<td align="center" nowrap="nowrap">签收说明</td>
						</tr>
						<c:if test="${not empty entityList}">
							<c:forEach var="cur" items="${entityList}" varStatus="vs">
								<tr>
									<html-el:hidden property="ensu_id" styleId="ensu_id" value="${cur.ensu_id}"/>
									<td nowrap="nowrap">${cur.r3_vbedl }</td>
									<td nowrap="nowrap" align="center">${cur.trade_index }</td>
									<td nowrap="nowrap" align="center"><div class="div_trans_index_detail">${cur.trans_index_detail }</div></td>
									<td nowrap="nowrap" align="center"><fmt:formatDate value="${cur.add_date }" pattern="yyyy-MM-dd"/></td>
									<td nowrap="nowrap">${cur.map.customer_name }</td>
									<td nowrap="nowrap">${cur.model_name }</td>
									<td nowrap="nowrap" align="right">${cur.model_num }</td>
									<td nowrap="nowrap" align="right">${cur.trans_num }</td>
									<td nowrap="nowrap" align="right">${empty cur.trans_ensured_num?0:cur.trans_ensured_num }</td>
									<td nowrap="nowrap" align="right"><input type="hidden" name="trans_unensu_num" value="${cur.trans_num-cur.trans_ensured_num }"/>${cur.trans_num-cur.trans_ensured_num }</td>
									<td nowrap="nowrap" align="right"><html-el:text property="trans_ensu_num" styleId="trans_ensu_num" styleClass="trans_ensu_num" onfocus="setOnlyInt(this)"/></td>
									<td nowrap="nowrap" align="right"><html-el:text property="trans_ensu_desc" styleId="trans_ensu_desc" styleClass="webinput" maxlength="200"/></td>
								</tr>
							</c:forEach>
						</c:if>
						<tbody class="add_tbody"></tbody>
					</table>
					</div>
					<!--上面的详情list---end -->
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
					<tr>
						<td width="8%" align="right">签收人姓名</td>
						<td width="92%"><html-el:text property="trans_ensu_user" styleId="trans_ensu_user" styleClass="webinput" maxlength="60"/></td>
					</tr>
					<tr>
						<td align="right">联系电话</td>
						<td><html-el:text property="trans_ensu_user_phone" styleId="trans_ensu_user_phone" styleClass="webinput" maxlength="30"/></td>
					</tr>
					<tr>
						<td align="right">收货时间</td>
						<td><html-el:text property="trans_ensu_date" styleId="trans_ensu_date" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);"
									style="cursor:pointer;text-align:center;width:80px;"/></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td align="center">
							<input type="button" id="btn_submit" class="but4" value="提交" /> 
							<input type="button" id="btn_back" class="but5" value="返回" onclick="javascript:history.back()"/> 
						</td>
					</tr>
					</table>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
				</html-el:form>
			</div>



		</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.jxc.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/external/bgiframe/jquery.bgiframe.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript">//<![CDATA[                                       
var f = document.forms[0];
$(document).ready(function(){
	$("input[name='trans_ensu_num']").attr("dataType", "Require").attr("msg", "请填写！");
	$("#trans_ensu_user_phone").attr("require", "true").attr("datatype","Custom").attr("regexp","(^(0[0-9]{2,3}-)?([2-9][0-9]{6,7})+(-[0-9]{1,4})?$)|(^((13[0-9]{9})|(14[0-9]{9})|(15[0-9]{9})|(18[0-9]{9}))$)").attr("msg","请正确填写联系电话，格式为“电话：0755-88888888”，“手机：13888888888”！");
	$("#trans_ensu_user").attr("dataType", "Require").attr("msg", "请填写签收人姓名！");
	$("#trans_ensu_date").attr("dataType", "Require").attr("msg", "请填写收货时间！");

	//提交表单(验证价格是否为负和字段是否超过长度)
	$("#btn_submit").click(function(){
		if (Validator.Validate(this.form, 3)){
			f.submit();
		}
	});
	
	//判断本次签收数量是否小于未签收数量
	$(".trans_ensu_num").blur(function(){
		var $trans_ensu_num = $(this);
		var trans_ensu_num = $(this).val();
		var trans_num = $(this).parent().parent().children('td').eq(7).text();
		var have_trans_ensured_num = $(this).parent().parent().children('td').eq(8).text();
		if(trans_ensu_num > (parseFloat(trans_num) - parseFloat(have_trans_ensured_num))){
			alert("本次签收数量大于未签收数量！");
			$trans_ensu_num.val(0);
		}
	});
	
	$("#add").click(function(){
		var trans_index_detail = $("#add_trans_index_detail").val();
		var flag = true;
		if(trans_index_detail.length<1){
			alert("发货单号不能为空!");
			flag = false;
			return false;
		}
		$(".div_trans_index_detail").each(function(){
			if($(this).text() == trans_index_detail){
				alert("发货单号已存在，不能重复添加!");
				flag = false;
				return false;
			}	
		});
		if(flag){
			$.ajax({
				type: "POST",
				url: "KonkaOrderInfoTrans.do",
				data: {method : "ajaxSetTransDetails", "trans_index_detail": trans_index_detail},
				dataType: "json",
				cache:false,
				error: function(){alert("数据加载请求失败！");},
				success: function(ret){
					if(ret){
						if(null != ret.list && ret.list.length > 0){
							var html = "";
							for(var i = 0; i < ret.list.length; i++){
								var cur = ret.list[i];
								html += "<tr><input type='hidden' name='ensu_id' value='" + cur.ensu_id + "' />";
								html += "<td nowrap='nowrap'>" + cur.r3_vbedl + "</td>";
								html += "<td nowrap='nowrap' align='center'>" + cur.trade_index + "</td>";
								html += "<td nowrap='nowrap' align='center'><div class='div_trans_index_detail'>" + cur.trans_index_detail + "</div></td>";
								html += "<td nowrap='nowrap' align='center'>" + cur.add_date + "</td>";
								html += "<td nowrap='nowrap'>" + cur.customer_name + "</td>";
								html += "<td nowrap='nowrap'>" + cur.model_name + "</td>";
								html += "<td nowrap='nowrap' align='right'>" + cur.model_num + "</td>";
								html += "<td nowrap='nowrap' align='right'>" + cur.trans_num + "</td>";
								html += "<td nowrap='nowrap' align='right'>" + cur.trans_ensured_num + "</td>";
								html += "<td nowrap='nowrap' align='right'><input type='hidden' name='trans_unensu_num' value='"+ cur.result_num +"'/>" + cur.result_num + "</td>";
								html += "<td nowrap='nowrap' align='right'><input type='text' name='trans_ensu_num' id='trans_ensu_num' dataType='Require' msg='请填写！' class='trans_ensu_num' onfocus='setOnlyInt(this)'/></td>";
								html += "<td nowrap='nowrap' align='right'><input type='text' name='trans_ensu_desc' id='trans_ensu_desc' class='trans_ensu_desc' maxlength='200'/></td>";
								html += "</tr>";
							}
							$(".add_tbody:first").clone(true).appendTo("#fhdetails");
							$(".add_tbody:last").html(html);
							$("#add_trans_index_detail").val("");
							//iframe高度自适应
							window.parent.resizeFrameHeight('mainFrame', 3);
						} else {
							alert("对不起，没有数据！");
							$("#add_trans_index_detail").val("");
						}
					}
				}
		   }); 
		}
	});
	
});

function resizeFrameHeight(offset, min_height) {
	$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
}

//正则表达式：只能输入正整数
function setOnlyInt(obj) {
	$(obj).css("ime-mode", "disabled");
	$(obj).attr("t_value", "");
	$(obj).attr("o_value", "");
	$(obj).bind("dragenter",function(){
		return false;
	});
	$(obj).keypress(function (){
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).keyup(function (){
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).blur(function (){
		if(!obj.value.match(/^(?:\d+(?:\d+)?|\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\d+$/))obj.value=obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
		if(obj.value.length == 0 || isNaN(obj.value) || obj.value == 0) obj.value = "0";
	});
}

//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>