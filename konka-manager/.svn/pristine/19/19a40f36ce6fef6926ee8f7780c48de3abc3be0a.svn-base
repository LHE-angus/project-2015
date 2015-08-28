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
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
     <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
  </div>
  <div class="rtabcont1" style="overflow: auto;"> 
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
       <tbody>
          <tr class="tabtt1">
              <td width="30" align="center"><font class="blue">序号</font></td>
              <td nowrap="nowrap" align="center"><font class="blue">用户名</font></td>
              <td nowrap="nowrap" width="10%" align="center"><font class="blue">手机</font></td>
              <td nowrap="nowrap" width="10%" align="center"><font class="blue">所属组织</font></td>
          </tr>
         <c:forEach var="cur" items="${entityList}" varStatus="vs">
           <tr>
                <td align="center">${vs.count}</td>
                <td align="center">${cur.map.user_name}</td>
                <td align="center">${cur.map.link_phone}</td>
                <td align="center">${cur.map.group_name}</td>
           </tr>
            <c:if test="${vs.last eq true}">
              <c:set var="i" value="${vs.count}" />
            </c:if>
          </c:forEach>
        </tbody>
      </table>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
