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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
   <html-el:form action="/admin/KonkaYjglPlanFpForSy" method="post"> 
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
  	<table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
  		<tr>
			<td width="12%" nowrap="nowrap" class="title_item" align="right">分公司：</td>
			<td width="88%" align="left">${af.map.dept_name}</td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">上样类型：</td>
			<td align="left">
				${af.map.type_name}
			</td>
		</tr> 
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">门店名称：</td>
			<td align="left">
				${af.map.store_name}
			</td>
		</tr> 
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">型号：</td>
			<td align="left">
			<html-el:select property="pd_id" styleId="pd_id">
			    <c:forEach items="${pePdModelList}" var="cur">
				<html-el:option value="${cur.pd_id}">${cur.md_name}</html-el:option>
				</c:forEach>
			</html-el:select></td>
		</tr> 
		
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">数量：</td>
			<td align="left"><html-el:text property="num" styleId="num" size="4"  maxlength="6" styleClass="num" />  </td> 
		</tr> 
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">备注：</td>
			<td align="left"><html-el:text property="remark" size="50"  maxlength="50" styleClass="remark" styleId="remark" /></td>
		</tr>
		<tr>
	          <td align="center" colspan="2">
	            <html-el:button property="" value="提交" styleClass="but4" styleId="btn_submit" />
				<input class="but5" type="button" name="Submit5" value="返回" id="btn_back" onclick="history.back()" />
	          </td>
	    </tr>		
  	</table>
  	</html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>  
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#num").attr("datatype", "Require").attr("msg", "请填写数量");
	$("#num" ).attr("focus",setOnlyInt);     

	$("#btn_submit").click(function(){
		var isSubmit = Validator.Validate(this.form, 3);
		if (isSubmit) {
			$(":button").attr("disabled", "true");
			$(":reset").attr("disabled", "true");
			this.form.submit();
		}
	}); 
});

function setOnlyInt() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^\d*$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:\d+(?:\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^\d*$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:\d+(?:\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:\d+(?:\d+)?|\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\d+$/))this.value=this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		if(this.value.length == 0 || isNaN(this.value) || this.value == 0) this.value = "0";
	});
}


//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>