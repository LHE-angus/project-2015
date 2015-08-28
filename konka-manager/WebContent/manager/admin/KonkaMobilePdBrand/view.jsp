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
</head>
<body style="font-family:Microsoft Yahei,'宋体';">
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
<div class="rtabcont2">
	<html-el:form action="/admin/KonkaMobilePdBrand" enctype="multipart/form-data">
      <html-el:hidden property="brand_id" value="${af.map.brand_id}" />
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
      	<tr>
	  	  <td align="center" colspan="4" style="font-weight:900;">品牌添加</td>
	  	</tr>
      	<tr>
          <td width="12%" nowrap="nowrap" class="title_item">品牌名称：</td>
          <td width="88%">${af.map.brand_name}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">描述：</td>
          <td>${af.map.type_desc}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">排序值：</td>
          <td>${af.map.order_value}</td>
        </tr>
        
        <tr>
            <td colspan="4" height="40"  align="center">
            	<input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" id="btn_back" />
            </td>
        </tr>
      </table>
    </html-el:form>
</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery1.5.1.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript"><!--//<![CDATA[
$(document).ready(function(){
	
});
//]]>--></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>