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
        <td width="15%" nowrap="nowrap" class="title_item" align="right">分公司名称：</td>
        <td><strong class="fb">${fn:escapeXml(dept_name)}</strong></td>
      </tr>
      <tr>
        <td width="15%" nowrap="nowrap" class="title_item" align="right">专卖店编号：</td>
        <td width="85%">${zmd_sn}</td>
      </tr>
      <tr>
        <td width="15%" nowrap="nowrap" class="title_item" align="right">产品型号：</td>
        <td width="85%">${af.map.md_name} </td>
      </tr>
      <tr>
        <td width="15%" nowrap="nowrap" class="title_item" align="right">反佣类型：</td>
        <td width="85%"><c:forEach var="cur" items="${baseTypesList70000}">
            <c:if test="${af.map.reward_type eq cur.type_id}">${cur.type_name}</c:if>
          </c:forEach></td>
      </tr>
      <tr>
        <td width="15%" nowrap="nowrap" class="title_item" align="right">反佣比例：</td>
        <td width="85%">${af.map.reward_ratio} %</td>
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
