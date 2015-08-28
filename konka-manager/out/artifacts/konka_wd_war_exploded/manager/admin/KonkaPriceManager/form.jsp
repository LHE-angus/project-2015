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
    <html-el:form action="/admin/KonkaPriceManager">
      <html-el:hidden property="id" value="${af.map.id}" />
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
      	<tr>
          <th height="28" colspan="2">您正在${empty af.map.id ? '创建' : '编辑'}集采订单价格信息</th>
        </tr>
        <tr class="oartop">
          <td colspan="2">集采订单价格信息</td>
        </tr>
        <c:if test="${not empty af.map.id}">
        	<c:set var="readonly" value="true" />
        </c:if>
        <c:if test="${empty af.map.id}">
        	<c:set var="readonly" value="false" />
        </c:if>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item"><font color="red">* </font>型号：</td>
          <td><html-el:text property="goods_name" styleId="goods_name" size="20" maxlength="30" readonly="${readonly}" /></td>
        </tr>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item"><font color="red">* </font>库存地点：</td>
          <td><html-el:text property="store_sn" styleId="store_sn" size="20" maxlength="30"/>&nbsp;</td>
        </tr>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item"><font color="red">* </font>供货价格：</td>
          <td><html-el:text property="price" styleId="price" size="20" maxlength="10"  onfocus="javascript:setOnlyNum(this);" />&nbsp;元/台</td>
        </tr>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item"><font color="red">* </font>开始时间：</td>
          <td>
          	<fmt:formatDate value="${af.map.start_date}" pattern="yyyy-MM-dd" var="start_date" />
			<input name="start_date" id="start_date" size="12" value="${start_date}" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'2013-11-01',maxDate:'#F{$dp.$D(\'end_date\')}'})" />
          &nbsp;</td>
        </tr>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item"><font color="red">* </font>结束时间：</td>
          <td>
        	<fmt:formatDate value="${af.map.end_date}" pattern="yyyy-MM-dd" var="end_date" />
			<input name="end_date" id="end_date" size="12" value="${end_date}" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'#F{$dp.$D(\'start_date\')||\'2013-11-01\'}'})" />
          &nbsp;</td>
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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	$("#goods_name" ).attr("dataType" , "Require").attr("msg" , "请填写型号！");
	$("#store_name" ).attr("dataType" , "Require").attr("msg" , "请填写库存地点！");
	$("#price" ).attr("dataType" , "Require").attr("msg" , "请填写供货价格！");
	$("#start_date" ).attr("dataType" , "Require").attr("msg" , "请填写开始时间！");
	$("#end_date" ).attr("dataType" , "Require").attr("msg" , "请填写结束时间！");
	$("#send").click(function(){
		var isSubmit = Validator.Validate(this.form, 2);
		if (isSubmit) {
			$("#send").attr("disabled", "true");
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