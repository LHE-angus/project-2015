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
    <html-el:form action="/admin/KonkaR3MmtMatch">
      <html-el:hidden property="method" value="sdfSave" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="str_pks" value="${fn:join(af.map.pks,',')}" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td width="50%" valign="top"><table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
              <tr>
                <td colspan="2" nowrap="nowrap"><strong>客户信息</strong></td>
              </tr>
              <c:forEach var="cur" items="${konkaR3ShopList}" varStatus="vs">
                <tr>
                  <td width="12%" nowrap="nowrap" class="title_item" align="right">客户名称： </td>
                  <td width="88%" align="left"><c:out value="${cur.customer_name}" /></td>
                </tr>
              </c:forEach>
            </table></td>
        </tr>
        <tr>
        	<td colspan="2" nowrap="nowrap"><strong>R3分类：</strong>
        	<html-el:select property="shop_type" styleId="shop_type">
        		<html-el:option value="1">送达方</html-el:option>
        		<html-el:option value="0">售达方</html-el:option>
        	</html-el:select>
        	</td>
        </tr>
        
      </table>
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td width="20%"></td>
          <td><label>
              <input class="but4" type="submit" name="save" id="send" value="提交" />
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
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
$("#send").click(function(){
	var isSubmit = Validator.Validate(this.form, 3);
	if (isSubmit) {
		$(":button").attr("disabled", "true");
		$(":reset").attr("disabled", "true");
		this.form.submit();
	}
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
