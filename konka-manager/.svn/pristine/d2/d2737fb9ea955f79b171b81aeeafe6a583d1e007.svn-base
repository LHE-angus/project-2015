<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
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
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
	<div class="rtabcont2">
		<html-el:form action="/paragon/KonkaParagonShowinfo" enctype="multipart/form-data">
			<html-el:hidden property="show_shop_id" styleId="show_shop_id" />
			<html-el:hidden property="mod_id" styleId="mod_id" />
			<html-el:hidden property="method" styleId="method" value="save" />
			<html-el:hidden property="queryString" styleId="queryString" />
			<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
			<tr>
				<td nowrap="nowrap" class="title_item">分公司：</td>
				<td width="35%"><html-el:select property="part_company_id"
					style="width:120px;" styleId="part_company_id">
					<html-el:option value="">请选择分公司</html-el:option>
					<html-el:optionsCollection name="deptInfoList" label="dept_name"
						value="dept_id" />
				</html-el:select></td>
				<td nowrap="nowrap" class="title_item">门店代码：</td>
				<td width="35%"><html-el:hidden property="show_shop_code"
					styleId="show_shop_code" /> <html-el:text
					property="show_shop_code1" styleId="show_shop_code1" maxlength="4"
					size="4"/> <html-el:text property="show_shop_code2"
					styleId="show_shop_code2" maxlength="3" size="4"/></td>
			</tr>
			<tr>
				<td nowrap="nowrap" class="title_item">经办：</td>
				<td>
					<div id="select_id" style="display: none;">
						<html-el:select property="channel_id" style="width:120px;"
							styleId="channel_id">
							<html-el:option value="">请选择经办</html-el:option>
						</html-el:select>
						<input id="gsBTN" type='button' value='写入' onclick="writeIn();" class="but2" />
					</div>
					<div id="input_id" style="display: block;">
						<html-el:text property="channel_name" size="20"
								maxlength="10" styleId="channel_name"/>
						<input id="gsBTN" type='button' value='选择' onclick="getType();" class="but2" />
					</div>
					
					
				</td>
				<td nowrap="nowrap" class="title_item">门店名称：</td>
				<td><html-el:text property="show_shop_name" size="40"
					maxlength="30" styleId="show_shop_name"/></td>
			</tr>
			<tr>
				<td nowrap="nowrap" class="title_item">客户姓名：</td>
				<td><html-el:text property="custom_name" size="40"
					maxlength="30" styleId="custom_name"/></td>
				<td nowrap="nowrap" class="title_item">区域：</td>
				<td><html-el:select property="area_id" styleId="area_id">
					 <html-el:option value="">请选择...</html-el:option>
	                <html-el:option value="10">华东</html-el:option>
	                <html-el:option value="20">山东</html-el:option>
	                <html-el:option value="30">东北</html-el:option>
	                <html-el:option value="40">华北</html-el:option>
	                <html-el:option value="50">华南</html-el:option>
	                <html-el:option value="60">西南</html-el:option>
	                <html-el:option value="70">华中</html-el:option>
	                <html-el:option value="80">西北</html-el:option>
				</html-el:select></td>
			</tr>
			<tr>
				<td nowrap="nowrap" class="title_item">客户类别：</td>
				<td colspan="3"><html-el:select property="custom_type" styleId="custom_type">
					<html-el:option value="">请选择...</html-el:option>
					<html-el:option value="1">连锁</html-el:option>
					<html-el:option value="2">超市</html-el:option>
					<html-el:option value="3">县乡客户群</html-el:option>
					<html-el:option value="4">城市客户群</html-el:option>
					<html-el:option value="5">城市专卖店</html-el:option>
				</html-el:select></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
		        <td><label>
		            <input class="but4" type="button" name="btn_submit" id="btn_submit" value="提交" />
		            <input class="but5" type="button" name=btn_back value="返回" onclick="history.back();return false;" />
		          </label></td>
			</tr>
			</table>
		</html-el:form>
 </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[

var mark_val=0;
$(document).ready(function(){
	$("#show_shop_name").attr("dataType", "Require").attr("msg", "请填写");
	$("#area_id").attr("dataType", "Require").attr("msg", "请选择");
	$("#custom_type").attr("dataType", "Require").attr("msg", "请选择");
	$("#custom_name").attr("dataType", "Require").attr("msg", "请填写");
	$("#show_shop_code").attr("datatype","LimitB").attr("max","20").attr("min","1").attr("msg","代码不可为空");
	$("#show_shop_code1").val("${af.map.show_shop_code}".substring(0,4));
	$("#show_shop_code2").val("${af.map.show_shop_code}".substring(4,7));
	
		 $("#show_shop_code").blur(function(){
				var value = this.value;
				if (value != "") {
					var reg = /^[a-zA-Z0-9_!@#\$\*]{7,20}$/;
					if (!reg.test(value)) {
						$("#show_shop_code").val("");
						$("#show_shop_code").focus();
						alert("门店代码格式不正确：6-16位字符（字母和数字），不能包含“%”、“&amp;”、“？”、“=”和空格，请重新输入！");
						return false;
					}
				}
			});
	 $("#part_company_id").change( function() {
				var dept_id = $("#part_company_id").val();
				$("#channel_id").empty();
				mark_val++;
				if(mark_val>1){
				$.ajax({
					type: "POST",
					url: "KonkaParagonSub.do",
					data: "method=getval&comid=" + $("#part_company_id").val(),
					dataType: "html",
					error: function(request, settings) {alert("生成异常，分公司信息维护不完整！"); },
					success: function(code) {
					if(code != null && code != "") {
						$("#show_shop_code1").val(code.substring(0,4));
						$("#show_shop_code2").val(code.substring(4,7));
						return false;
					} else {
						alert("生成异常，分公司信息维护不完整！");
						$("#show_shop_code1").val("");
						$("#show_shop_code2").val("");
					}}});
				}

				if(""==dept_id){
			   		var opt1 = new Option( "请选择...",""); 
					$("#channel_id").get(0).options.add(opt1);
				   	}
			   	if(dept_id!=""){
				   	$.ajax({
						type: "POST",
						cache: false,
						url: "${ctx}/manager/admin/CsAjax.do",
						data: "method=getChannelId&part_company_id=" + $("#part_company_id").val(),
						dataType: "json",
						error: function(request, settings){},
						success: function(data) {
							if (data.length >= 1) {
								var opt1 = new Option( "请选择...",""); 
								$("#channel_id").get(0).options.add(opt1);
								
								for(var i = 0; i < data.length - 1; i++) {
									var opt = new Option( data[i].name,data[i].id); 
									$("#channel_id").get(0).options.add(opt);
								}
								
								<c:if test="${not empty af.map.channel_id }">$("#channel_id").val("${af.map.channel_id}");$("#channel_id").change();</c:if>
								<c:if test="${empty af.map.channel_id}"></c:if>
							}
						}
					});
			   	}
	
			});
		 $("#part_company_id").change();
		
	$("#btn_submit").click(function(){
		$("#show_shop_code").val($("#show_shop_code1").val()+$("#show_shop_code2").val());
    	   if(Validator.Validate(this.form, 3)){
               $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
               $("#btn_back").attr("disabled", "true");
               this.form.submit();
   		}
	});
});

function getType(){
	$("#select_id").css("display","block");
	$("#input_id").css("display","none");
}
function writeIn(){
	$("#select_id").css("display","none");
	$("#input_id").css("display","block");
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
