<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
<script type="text/javascript" src="${ctx}/commons/scripts/My97DatePicker/WdatePicker.js"></script>
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
  <html-el:form action="/admin/KonkaPdModelPrices">
    <html-el:hidden property="id" value="${af.map.id}" />
    <html-el:hidden property="method" value="save" />
    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
    <html-el:hidden property="queryString" />
    <c:set var="readonly" value="false" />
    <c:if test="${not empty af.map.id}">
      <c:set var="readonly" value="true" />
    </c:if>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        
        <tr>
          <td nowrap="nowrap" height="28" class="title_item">型号：</td>
          <td>
          <html-el:select property="pd_name" styleId="pd_name">
          	<html-el:option value="">请选择</html-el:option>
          	<html-el:optionsCollection name="pePdModelList" label="md_name" value="md_name" />
          </html-el:select>
          <%--
          <span class="note">属性类别名称最长为20个字，超出部分不被保存</span>
          <span id="pd_name_msg" style="color:red;display:none;margin-left:2px;">* 属性类别名称已存在，请重新输入！</span>
          --%> 
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item">现款价：</td>
          <td><html-el:text property="cash_price" styleId="cash_price" style="width:250px;" size="26" maxlength="7"/>
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item">零售指导价：</td>
          <td><html-el:text property="sale_price" styleId="sale_price" style="width:250px;" size="26" maxlength="7"/>
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item">扣点：</td>
          <td><html-el:text property="discount" styleId="discount" style="width:250px;" size="26" maxlength="7"/>
          </td>
        </tr>
        <tr>
         <td nowrap="nowrap" height="28" class="title_item">月份：</td>
          <td>
          <html-el:text property="price_month" styleId="price_month" onfocus="selectMonth()" />
          </td>
        </tr>
      <tr>
        <td>&nbsp;</td>
        <td><label>
            <input class="but4" type="button" name="Submit4" id="send" value="提交" />
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
          </label></td>
      </tr>
    </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script> 
   function selectMonth() {  
        WdatePicker({ dateFmt: 'yyyyMM', isShowToday: false, isShowClear: true });  
   }
</script>  
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#pd_name" ).attr("dataType" , "Require").attr("msg" , "请选择产品型号！");
	$("#price_month" ).attr("dataType" , "Require").attr("msg" , "请选择月份！");
	$("#cash_price" ).attr("focus",setOnlyNum);
	$("#sale_price" ).attr("focus",setOnlyNum);
	$("#discount" ).attr("focus",setOnlyNum);
	$("#send").click(function(){
		var isSubmit = Validator.Validate(this.form, 2);
		if (isSubmit) {
			$(":button").attr("disabled", "true");
			$(":reset").attr("disabled", "true");
			this.form.submit();
		}
	});
});

function setOnlyNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}
		if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;
}


//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
