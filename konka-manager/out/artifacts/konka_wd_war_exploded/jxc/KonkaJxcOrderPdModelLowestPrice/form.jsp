<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
-->
</style>
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;"/> 当前位置：${naviString}</div>
  <div class="rtabcont1">
    <html-el:form action="/KonkaJxcOrderPdModelLowestPrice.do">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="queryString" />
      <html-el:hidden property="id" value="${af.map.id}"/>
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th colspan="2"><c:if test="${af.map.flag eq 'add'}">添加产品最低限价</c:if>
          	<c:if test="${af.map.flag eq 'edit'}">修改产品最低限价</c:if>
          </th>
        </tr>
        
        <c:if test="${af.map.flag eq 'add'}">
        <tr>
        <td width="15%" nowrap="nowrap" class="title_item"><font color="red">*</font>产品型号：</td>
        <td>
        	<html-el:hidden property="pd_id" styleId="pd_id"/>
        	<html-el:select property="pd_ids" styleId="pd_ids">
            <html-el:option value="">请选择...</html-el:option>
            <c:forEach var="cur" items="${pdList}" varStatus="vs">
  				<html-el:option value="${cur.pd_id}@#${cur.map.cls_name}@#${cur.map.brand_name}">${cur.md_name}</html-el:option>
			  </c:forEach>
          </html-el:select><span id="clsName" style="display: none;" class="jxcTip"></span>
        </td>
        </tr>
        </c:if>
        
        <c:if test="${af.map.flag eq 'edit'}">
        	<tr>
        	<td width="15%" nowrap="nowrap" class="title_item">产品型号：</td>
          <td>${af.map.pd_name}</td>
          </tr>
          <tr>
        	<td width="15%" nowrap="nowrap" class="title_item">品牌：</td>
          <td>${af.map.brand_name}</td>
          </tr>
          <tr>
        	<td width="15%" nowrap="nowrap" class="title_item">产品类型：</td>
          <td>${af.map.pd_type_name}</td>
          </tr>
        </c:if>
        
        <tr>
          <c:if test="${af.map.flag eq 'add'}">
	          <td width="15%" nowrap="nowrap" class="title_item"><font color="red">*</font>限定年份：</td>
	          <td><html-el:select property="set_year" styleId="set_year">
	              <html-el:option value="">请选择...</html-el:option>
	              <c:forEach var="cur" items="${yearList}">
	                <html-el:option value="${cur}">${fn:escapeXml(cur)}</html-el:option>
	              </c:forEach>
	            </html-el:select></td>
	      </c:if>
          <c:if test="${af.map.flag eq 'edit'}">
          	<td width="15%" nowrap="nowrap" class="title_item">限定年份：</td>
            <td>${af.map.set_year}</td>
          </c:if>
         </tr>
         <tr>   
            <c:if test="${af.map.flag eq 'add'}">
	            <td width="15%" nowrap="nowrap" class="title_item"><font color="red">*</font>限定月份：</td>
	            <td><html-el:select property="set_month" styleId="set_month">
	              <html-el:option value="">请选择...</html-el:option>
	              <c:forEach var="cur" items="${monthList}">
	                <html-el:option value="${cur}">${fn:escapeXml(cur)}</html-el:option>
	              </c:forEach>
	            </html-el:select>
	          </td>
            </c:if>
            <c:if test="${af.map.flag eq 'edit'}">
          	<td width="15%" nowrap="nowrap" class="title_item">限定月份：</td>
            <td>${af.map.set_month}</td>
          </c:if>
        </tr>
        
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item"><font color="red" >*</font>最低限价：</td>
          <td><html-el:text property="lowest_price" styleId="lowest_price" maxlength="10" size="10" styleClass="webinput" /></td>
        </tr>
        
        <tr>
          <td colspan="2" align="center"><html-el:button property="save" styleClass="bgButtonSave" styleId="btn_submit" value="保 存"/>
            &nbsp;<html-el:button property="back" value="返 回 " onclick="history.back();" styleClass="bgButtonBack"/></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.jxc.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/external/bgiframe/jquery.bgiframe.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script> 
<script type="text/javascript">//<![CDATA[
var f = document.forms[0];
$(document).ready(function(){
	$("#set_year").attr("datatype", "Require").attr("msg", "请选择限定年份！");
	$("#set_month").attr("datatype", "Require").attr("msg", "请选择限定月份！");
	$("#pd_ids").attr("datatype", "Require").attr("msg", "请选择产品型号！");
	$("#lowest_price").attr("datatype", "Require").attr("msg", "请输入最低限价！");

	$("#lowest_price").keyup(function(){
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		var price =($("#lowest_price").val());
		if (!_reg.test(price)) {
		$("#lowest_price").val("");
   		}
	});

	$("#pd_ids").multiselect({
		selectedList: 1,
		multiple: false,
		minWidth:280,
		click: function(event, ui){
			if(ui.value != ""){
			    s = ui.value.split("@#");
			    $("#clsName").text(" 产品类型：" + s[1] + " 品牌：" + s[2]);
			    $("#pd_id").val(s[0]);
			    $("#clsName").show();
			}
			if(ui.value == ""){
				$("#pd_id").val("");
				$("#clsName").hide();
			}
		}
	}).multiselectfilter({width:180});
	
	$("#btn_submit").click(function(){

		if (Validator.Validate(this.form, 1)){
			$("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
	        $("#btn_reset").attr("disabled", "true");
	        $("#back").attr("disabled", "true");
			f.submit();
		}
	});	
});


//]]></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
