<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${naviString}</title>
<base target="_self" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
  <div class="rtabcont1">
   <%@ include file="/commons/pages/messages.jsp" %>
    <html-el:form action="/manager/EcGoodsPrice">
      <html-el:hidden property="method" value="save"/>
      <html-el:hidden property="queryString" />
      <html-el:hidden property="mod_id" /> 
      <html-el:hidden property="goods_id" /> 
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td colspan="2" align="right" bgcolor="#CCCCCC" style="font-weight:bold;padding-right:30px;">修改市场终端价</td>
        </tr> 
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">结算价格：</td>
          <td align="left">${af.map.price}</td> 
        </tr> 
         <tr>
          <td nowrap="nowrap" class="title_item" align="right"><font color="red">*</font>市场终端价：</td>
          <td align="left">
          <html-el:text property="original_price" size="30" maxlength="10" styleId="original_price" />
          </td> 
        </tr> 
        <tr>
          <td>&nbsp;</td>
          <td>
           <html-el:button property="" value="提 交" styleClass="but4" styleId="btn_submit" />
            <html-el:button property="" value="重 置" styleClass="but5" styleId="btn_reset" onclick="this.form.reset();" />
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();" /></td>
        </tr>
      </table>
      </html-el:form>
  </div>
</div> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#original_price" ).attr("dataType", "Currency").attr("msg", "请填写市场终端价！");
	$("#btn_submit").click(function(){ 
		
		if(Validator.Validate(this.form, 3)){ 
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_reset").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});
});
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
