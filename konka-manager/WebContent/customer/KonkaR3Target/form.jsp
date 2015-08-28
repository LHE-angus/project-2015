<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet"	type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet"	type="text/css" />
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css"	rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet"	type="text/css" />
<link href="${ctx}/styles/customer/css/tab.css" rel="stylesheet"	type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css"	rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/fancybox/fancybox.css"	rel="stylesheet" type="text/css" />
<style type="text/css">
	table tr td {
		padding:3px 0 4px 0;
	}
</style>
</head>
<body>
	<div class="oarcont">
		<div class="oartop">
			<table width="500" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="3%"><img
						src="${ctx}/styles/admin-index/images/k_tup.jpg"
						style="vertical-align: middle;" /></td>
					<td>当前位置：${naviString}</td>
				</tr>
			</table>
		</div>
		<div class="rtabcont2">
			<html-el:form action="/manager/KonkaR3Target">
				<html-el:hidden property="c_id" value="${c_id}" />
				<html-el:hidden property="method" value="save" />
				<html-el:hidden property="mod_id" value="${mod_id}" />
				<html-el:hidden property="queryString" styleId="queryString" />
				<html-el:hidden property="flag" styleId="flag" value="${flag }"/>
				<table width="100%">
					<tr>
						<td width="10%">年度：</td>
						<td>
							<html-el:select property="sale_year" styleId="sale_year" style="width:160px">
								<html-el:option value="">-请选择年度-</html-el:option>
								<c:forEach items="${yearList}" var="cur">
					            	<html-el:option value="${cur}">${cur}年</html-el:option>
					            </c:forEach>
							</html-el:select>
						</td>
					</tr>
					<tr><td colspan="2">&nbsp;</td></tr>
					<tr>
						<td colspan="2"><strong class="fb">各月份目标详细：</strong></td>
					</tr>
					<tr>
						<td>1月份：</td>
						<td><html-el:text property="jan" onfocus="javascript:setOnlyInt(this)"></html-el:text>&nbsp;元</td>
					</tr>
					<tr>
						<td>2月份：</td>
						<td><html-el:text property="feb" onfocus="javascript:setOnlyInt(this)"></html-el:text>&nbsp;元</td>
					</tr>
					<tr>
						<td>3月份：</td>
						<td><html-el:text property="mar" onfocus="javascript:setOnlyInt(this)"></html-el:text>&nbsp;元</td>
					</tr>
					<tr>
						<td>4月份：</td>
						<td><html-el:text property="apr" onfocus="javascript:setOnlyInt(this)"></html-el:text>&nbsp;元</td>
					</tr>
					<tr>
						<td>5月份：</td>
						<td><html-el:text property="may" onfocus="javascript:setOnlyInt(this)"></html-el:text>&nbsp;元</td>
					</tr>
					<tr>
						<td>6月份：</td>
						<td><html-el:text property="june" onfocus="javascript:setOnlyInt(this)"></html-el:text>&nbsp;元</td>
					</tr>
					<tr>
						<td>7月份：</td>
						<td><html-el:text property="july" onfocus="javascript:setOnlyInt(this)"></html-el:text>&nbsp;元</td>
					</tr>
					<tr>
						<td>8月份：</td>
						<td><html-el:text property="aug" onfocus="javascript:setOnlyInt(this)"></html-el:text>&nbsp;元</td>
					</tr>
					<tr>
						<td>9月份：</td>
						<td><html-el:text property="sept" onfocus="javascript:setOnlyInt(this)"></html-el:text>&nbsp;元</td>
					</tr>
					<tr>
						<td>10月份：</td>
						<td><html-el:text property="oct" onfocus="javascript:setOnlyInt(this)"></html-el:text>&nbsp;元</td>
					</tr>
					<tr>
						<td>11月份：</td>
						<td><html-el:text property="nov" onfocus="javascript:setOnlyInt(this)"></html-el:text>&nbsp;元</td>
					</tr>
					<tr>
						<td>12月份：</td>
						<td>
							<html-el:text property="dece" onfocus="javascript:setOnlyInt(this)"></html-el:text>&nbsp;元
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input class="but4" type="button" name="Submit4" value="提交" id="btn_submit" />
					 		<input class="but5" type="button" name="Submit5" value="返回" id="btn_back" onclick="history.back();return false;" />
						</td>
					</tr>
				</table>
			</html-el:form>
		</div>
		<div class="clear"></div>
	</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<jsp:include page="/__analytics.jsp" />
<script type="text/javascript">
$(document).ready(function(){
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}
	$("#sale_year" ).attr("dataType" , "Require").attr("msg" , "请选择年度！");
	
	
	//点击提交按钮事件
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 3)){
			$("#tr_model").remove();
            $("#btn_submit").attr("value", "正在提交").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});
})

//正则表达式：只能输入数字
function setOnlyInt(obj) {
	$(obj).css("ime-mode", "disabled");
	$(obj).attr("t_value", "0");
	$(obj).attr("o_value", "0");
	$(obj).bind("dragenter",function(){
		return false;
	});
	$(obj).keypress(function (){
		//先把非数字的都替换掉，除了数字和.
		obj.value = obj.value.replace(/[^\d.]/g,"");
		//必须保证第一个为数字而不是.
		obj.value = obj.value.replace(/^\./g,"");
		//保证只有出现一个.而没有多个.
		obj.value = obj.value.replace(/\.{2,}/g,".");
		//保证.只出现一次，而不能出现两次以上
		obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	}).keyup(function (){
		//先把非数字的都替换掉，除了数字和.
		obj.value = obj.value.replace(/[^\d.]/g,"");
		//必须保证第一个为数字而不是.
		obj.value = obj.value.replace(/^\./g,"");
		//保证只有出现一个.而没有多个.
		obj.value = obj.value.replace(/\.{2,}/g,".");
		//保证.只出现一次，而不能出现两次以上
		obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	}).blur(function (){
		//先把非数字的都替换掉，除了数字和.
		obj.value = obj.value.replace(/[^\d.]/g,"");
		//必须保证第一个为数字而不是.
		obj.value = obj.value.replace(/^\./g,"");
		//保证只有出现一个.而没有多个.
		obj.value = obj.value.replace(/\.{2,}/g,".");
		//保证.只出现一次，而不能出现两次以上
		obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	});
}
</script>
</body>
</html>
