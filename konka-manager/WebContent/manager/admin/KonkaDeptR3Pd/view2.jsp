<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
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
  <div>
    <h3 align="center" ><strong class="fb">分公司客户价格查看</strong></h3>
  </div>
  <div class="rtabcont2">
      <div style="height:10px;"></div>
      <table width="80%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr>
          <td class="title_item" nowrap="nowrap">分公司：</td>
          <td width="88%" >${af.map.dept_name} </td>
        </tr>
        <tr>
          <td class="title_item">客户R3编码：</td>
          <td>${af.map.r3_code}</td>
        </tr>
        <tr>
          <td class="title_item">年份：</td>
          <td>
          	${af.map.year}
          </td>
        </tr>
        <tr>
          <td class="title_item">月份：</td>
          <td>	${af.map.month}
          </td>
        </tr>
        <tr>
          <td  class="title_item">参考价格：</td>
          <td >${af.map.price}</td>
        </tr>
        <tr>
          <td class="title_item">最低价格：</td>
          <td>${af.map.min_price}</td>
        </tr>
        <tr>
          <td class="title_item">最高价格：</td>
          <td>${af.map.max_price}</td>
        </tr>
        <tr>
          <td class="title_item">添加时间：</td>
          <td><fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd HH:mm" /></td>
        </tr>
      </table>
    <div> <br />
      <label >
      <input class="but5" type="button"  value="返回" onclick="history.back();return false;" />
      </label>
    </div>
  </div>
  <div class="clear"></div>
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
