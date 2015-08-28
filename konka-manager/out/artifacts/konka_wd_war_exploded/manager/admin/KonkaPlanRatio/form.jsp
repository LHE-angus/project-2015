<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/KonkaPlanRatio">
      <html-el:hidden property="id" value="${af.map.id}" />
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="this_year" value="${af.map.this_year}" />
      <html-el:hidden property="dept_type" value="${af.map.dept_type}" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
      	<tr>
          <th height="28" colspan="2">您正在${empty af.map.id ? '创建' : '编辑'}年度任务总额信息</th>
        </tr>
        <tr class="oartop">
          <td colspan="2">任务总额信息</td>
        </tr>
        <c:if test="${not empty af.map.id}">
        	<c:set var="readonly" value="true" />
        </c:if>
        <c:if test="${empty af.map.id}">
        	<c:set var="readonly" value="false" />
        </c:if>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item">年度：</td>
          <td><html-el:text property="y" styleId="y" size="10" maxlength="4" readonly="${readonly}" onfocus="javascript:setOnlyNum(this);" /></td>
        </tr>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item">1月任务额：</td>
          <td><html-el:text property="m1" styleId="m1" size="20" maxlength="10" value="${konkaPlanMoney.map.m1 }" onfocus="javascript:setOnlyNum(this);" />&nbsp;万元</td>
        </tr>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item">2月任务额：</td>
          <td><html-el:text property="m2" styleId="m2" size="20" maxlength="10" value="${konkaPlanMoney.map.m2 }" onfocus="javascript:setOnlyNum(this);" />&nbsp;万元</td>
        </tr>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item">3月任务额：</td>
          <td><html-el:text property="m3" styleId="m3" size="20" maxlength="10" value="${konkaPlanMoney.map.m3 }" onfocus="javascript:setOnlyNum(this);" />&nbsp;万元</td>
        </tr>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item">4月任务额：</td>
          <td><html-el:text property="m4" styleId="m4" size="20" maxlength="10" value="${konkaPlanMoney.map.m4 }" onfocus="javascript:setOnlyNum(this);" />&nbsp;万元</td>
        </tr>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item">5月任务额：</td>
          <td><html-el:text property="m5" styleId="m5" size="20" maxlength="10" value="${konkaPlanMoney.map.m5 }" onfocus="javascript:setOnlyNum(this);" />&nbsp;万元</td>
        </tr>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item">6月任务额：</td>
          <td><html-el:text property="m6" styleId="m6" size="20" maxlength="10" value="${konkaPlanMoney.map.m6 }" onfocus="javascript:setOnlyNum(this);" />&nbsp;万元</td>
        </tr>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item">7月任务额：</td>
          <td><html-el:text property="m7" styleId="m7" size="20" maxlength="10" value="${konkaPlanMoney.map.m7 }" onfocus="javascript:setOnlyNum(this);" />&nbsp;万元</td>
        </tr>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item">8月任务额：</td>
          <td><html-el:text property="m8" styleId="m8" size="20" maxlength="10" value="${konkaPlanMoney.map.m8 }" onfocus="javascript:setOnlyNum(this);" />&nbsp;万元</td>
        </tr>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item">9月任务额：</td>
          <td><html-el:text property="m9" styleId="m9" size="20" maxlength="10" value="${konkaPlanMoney.map.m9 }" onfocus="javascript:setOnlyNum(this);" />&nbsp;万元</td>
        </tr>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item">10月任务额：</td>
          <td><html-el:text property="m10" styleId="m10" size="20" maxlength="10" value="${konkaPlanMoney.map.m10 }" onfocus="javascript:setOnlyNum(this);" />&nbsp;万元</td>
        </tr>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item">11月任务额：</td>
          <td><html-el:text property="m11" styleId="m11" size="20" maxlength="10" value="${konkaPlanMoney.map.m11 }" onfocus="javascript:setOnlyNum(this);" />&nbsp;万元</td>
        </tr>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item">12月任务额：</td>
          <td><html-el:text property="m12" styleId="m12" size="20" maxlength="10" value="${konkaPlanMoney.map.m12 }" onfocus="javascript:setOnlyNum(this);" />&nbsp;万元</td>
        </tr>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item">任务总额：</td>
          <td><html-el:text property="m" styleId="m" size="20" maxlength="10" onfocus="javascript:setOnlyNum(this);" />&nbsp;万元</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><label>
            <input class="but4" type="button" name="Submit4" value="提交" id="send"/>
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
            </label>
          </td>
        </tr>
      </table>
    </html-el:form> 
  </div> 

</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#y" ).attr("dataType" , "Require").attr("msg" , "请填写年度！");
	$("#m" ).attr("dataType" , "Require").attr("msg" , "请填写任务总额！");
	$("#send").click(function(){
		var isSubmit = Validator.Validate(this.form, 2);
		if (isSubmit) {
			$(":button").attr("disabled", "true");
			$(":reset").attr("disabled", "true");
			this.form.submit();
		}
	});
	
});


//正则表达式：只能输入数字
function setOnlyNum(obj) {
	$(obj).css("ime-mode", "disabled");
	$(obj).attr("t_value", "");
	$(obj).attr("o_value", "");
	$(obj).bind("dragenter",function(){
		return false;
	});
	$(obj).keypress(function (){
		if(!obj.value.match(/^[\+\-]?\d*?\.?\d*?$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))obj.o_value=obj.value;
	}).keyup(function (){
		if(!obj.value.match(/^[\+\-]?\d*?\.?\d*?$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))obj.o_value=obj.value;
	}).blur(function (){
		if(!obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\.\d+$/))obj.value=0+obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
		//if(obj.value.length == 0) obj.value = "0";
	});
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>