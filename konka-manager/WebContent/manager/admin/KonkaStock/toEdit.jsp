<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
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
  <html-el:form action="/admin/KonkaStock">
    <html-el:hidden property="method" value="editSave" />
    <html-el:hidden property="mod_id" />
    <html-el:hidden property="id" />
    <html-el:hidden property="r3_shop_id" />
    <html-el:hidden property="queryString" />
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr>
        <td colspan="4">数据上报信息编辑</td>
      </tr>
      <tr>
        <td width="20%" class="title_item" nowrap="nowrap" align="right"><strong>客户名称：</strong></td>
        <td align="left" colspan="3"><c:out value="${r3Shop.customer_name}"/></td>
      </tr>
      <tr>
        <td width="20%" class="title_item" nowrap="nowrap" align="right"><strong>客户R3编码：</strong></td>
        <td align="left" colspan="3"><c:out value="${r3Shop.r3_code}" /></td>
      </tr>
      <tr>
        <td width="20%" class="title_item" nowrap="nowrap" align="right"></td>
        <td align="left" colspan="3"><table width="650" border="0" cellpadding="0" cellspacing="1" class="rtable2">
            <tr>
              <td width="20%"><strong>型号名称</strong></td>
              <td width="20%"><strong>库存数量</strong></td>
              <td width="20%"><strong>库存成本单价</strong></td>
            </tr>
            <tr>
              <td align="left"><c:out value="${stock.map.md_name}"/></td>
              <td align="left"><input type="text" name="stock_count" id="stock_count" value="${stock.stock_count }"  onkeyup="javascript:setOnlyNum(this);" maxlength="15"/></td>
              <td align="left"><input type="text" name="stock_cost" id="stock_cost" value="${stock.stock_cost}" onkeyup="javascript:setOnlyDouble(this);" maxlength="8"/></td>
            </tr>
          </table></td>
      </tr>
      <tr>
        <td colspan="4" align="center"><html-el:button value=" 保存 " styleId="btn_submit" property="btn_submit" styleClass="but4" />
          <html-el:button property="back" value=" 返回 " onclick="history.back();" styleId="btn_back" styleClass="but5"/></td>
      </tr>
    </table>
  </html-el:form>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	$("#btn_submit").click(function(){
		
		if(Validator.Validate(this.form, 3)){
			$("#btn_submit"   ).attr("disabled",true);
			$("#btn_temporary").attr("disabled",true);
			$("#btn_back"     ).attr("disabled",true);
			
			this.form.submit();
		}
	});

});

function setOnlyNum(obj) {
	var v = obj.value.replace(/[^\d-]/gi,'');
	
		obj.value=v;
	
}
function setOnlyDouble(obj) {
	var v = obj.value.replace(/[^\d+(\.\d+)?-]/gi,'');
	
		obj.value=v;
	
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
