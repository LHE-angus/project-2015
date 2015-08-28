<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<style type="text/css">
select {
	font-family:Microsoft YAHEI;
	font-size:12px;
}
input {
	font-family:Microsoft YAHEI;
	font-size:12px;
}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div style="width:100%">
  <div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/manager/JBaseType">
      <html-el:hidden property="method" value="save" styleId="method"/>
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="par_id" styleId="par_id" value="${af.map.par_id}" />
      <html-el:hidden property="type_id" styleId="type_id" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <td class="title_item" width="15%"><font color="red">*</font>
            <c:if test="${af.map.par_id eq 10001}">商品类型： </c:if>
            <c:if test="${af.map.par_id eq 10002}">商品单位： </c:if></td>
          <td colspan="3"><html-el:text styleId="type_name" property="type_name" maxlength="20" styleClass="webinput" style="width:40%"/></td>
        </tr>
        <tr>
          <td class="title_item"><c:if test="${af.map.par_id eq 10001}">商品类型描述： </c:if>
            <c:if test="${af.map.par_id eq 10002}">商品单位描述： </c:if></td>
          <td colspan="3"><html-el:textarea styleId="type_desc" property="type_desc" styleClass="webinput" style="width:40%;height:80px;"/></td>
        </tr>
        <tr>
          <td class="title_item">排序值： </td>
          <td colspan="3"><html-el:text styleId="order_value" property="order_value"  maxlength="4" styleClass="webinput" style="width:40%"/><span style="color: red;">*排序值越大越靠前</span></td>
        </tr>
        <tr>
          <td colspan="4" align="center"><input type="button" name="save" class="bgButtonSave" value=" 提 交 " id="btn_submit"/>
            <input class="bgButtonBack" type="reset" name="reset" value="重填" id="btn_reset"/>
            <input class="bgButtonBack" type="button" name="back" value="返回" onclick="history.back();return false;" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
var f = document.forms[0];
$(document).ready(function(){
	
	var par_id = '${af.map.par_id}';
	var type_msg = "";

	if(par_id == 10001){
		type_msg = "商品类型";
	} else if(par_id == 10002) {
		type_msg = "商品单位";
	}
	
	$("#type_name").attr("dataType","Require").attr("msg","请填写"+ type_msg);
	$("#type_desc").attr("dataType", "Limit").attr("max", "100").attr("msg", "描述不能超过100个文字");
	$("#order_value" ).attr("focus",setOnlyNum);
	
	$("#type_name").change(function(){
		var type_name = (this.value).replace(/\s+/g, "");
		
		if(type_name.length > 0 && type_name != '${af.map.type_name}'){
			$.ajax({
				type: "POST",
				url: "${ctx}/customer/manager/JBaseType.do",
				data: "method=validateName&type_name=" + type_name+"&par_id=" + '${af.map.par_id}',
				dataType: "json",
				error: function(request, settings) {alert("数据加载请求失败！"); },
				success: function(isExist) {
					if(isExist == 11) {
						alert("该" + type_msg + "已存在，请重新填写！");
						$("#type_name").val("");
						return ;
					}  
				}
			});
			} 
		});
	$("#btn_submit").click(function(){
		
		if (Validator.Validate(this.form, 1)){
			
			$("#btn_submit").attr("value", "提交中").attr("disabled", "true");
	        $("#btn_reset").attr("disabled", "true");
			f.submit();
		}
	});
});//ready end

function setOnlyNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}
		if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;

}
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>