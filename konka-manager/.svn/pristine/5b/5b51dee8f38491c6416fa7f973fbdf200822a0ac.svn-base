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
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable4">
      <tr>
        <td width="15%" nowrap="nowrap" class="title_item" align="right">企业名称：</td>
        <td><strong class="fb">康佳集团股份有限公司</strong></td>
      </tr>
      <tr>
        <td width="15%" nowrap="nowrap" class="title_item" align="right">分公司：</td>
        <td width="85%">${dept_name}</td>
      </tr>
      <tr>
        <td width="15%" nowrap="nowrap" class="title_item" align="right">产品型号：</td>
        <td width="85%">${af.map.md_name} </td>
      </tr>
      <tr>
        <td width="15%" nowrap="nowrap" class="title_item" align="right">工厂仓位：</td>
        <td width="85%">${fac_store_name}</td>
      </tr>
      <tr>
        <td width="15%" nowrap="nowrap" class="title_item" align="right">上架时间：</td>
        <td width="85%"><fmt:formatDate value="${af.map.up_time}" pattern="yyyy-MM-dd" /></td>
      </tr>
      <tr>
        <td width="15%" nowrap="nowrap" class="title_item" align="right">下架时间：</td>
        <td width="85%"><fmt:formatDate value="${af.map.down_time}" pattern="yyyy-MM-dd" /></td>
      </tr>
      <tr>
        <td width="15%" nowrap="nowrap" class="title_item" align="right">分公司参考单价：</td>
        <td width="85%"><span class="kz-price">
          <fmt:formatNumber type="currency" value="${af.map.price_ref}" />
          </span></td>
      </tr>
      <tr>
        <td width="15%" nowrap="nowrap" class="title_item" align="right">分公司价格下限：</td>
        <td width="85%"><span class="kz-price">
          <fmt:formatNumber type="currency" value="${af.map.price_min}" />
          </span></td>
      </tr>
      <tr>
        <td width="15%" nowrap="nowrap" class="title_item" align="right">备注：</td>
        <td width="85%">${af.map.remarks}
          <c:if test="${empty af.map.remarks}"><span style="color:gray;">无</span></c:if></td>
      </tr>
      <tr>
        <td>&nbsp;&nbsp;</td>
        <td><input class="but5" type="button" name="Submit5" value="返回" onclick="history.back()" /></td>
      </tr>
    </table>
  </div>
</div>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
