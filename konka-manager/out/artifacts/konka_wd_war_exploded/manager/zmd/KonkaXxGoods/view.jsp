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
  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable4">
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right" width="15%">物料名称：</td>
          <td>${af.map.goods_name}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right">仓位：</td>
          <td>${store.store_name}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right">数量：</td>
          <td>${af.map.counts}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right">备注：</td>
          <td>${af.map.memo}</td>
        </tr>
      <tr>
        <td>&nbsp;</td>
        <td><label>
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
          </label></td>
      </tr>
    </table>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

});
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
