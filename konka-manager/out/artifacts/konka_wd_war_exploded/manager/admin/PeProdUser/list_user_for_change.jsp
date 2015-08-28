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
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
       <tbody>
          <tr class="tabtt1">
              <td width="5%" align="center" ><font class="blue">序号</font></td>
              <td width="10%" nowrap="nowrap" align="center"><font class="blue">岗位ID</font></td>
              <td width="10%" nowrap="nowrap" align="center"><font class="blue">原登录名</font></td>
              <td width="10%" nowrap="nowrap" align="center"><font class="blue">原员工姓名</font></td>
              <td width="10%" nowrap="nowrap" align="center"><font class="blue">新登录名</font></td>
              <td width="10%" nowrap="nowrap"  align="center"><font class="blue">新员工姓名</font></td>
              <td width="15%" nowrap="nowrap" align="center"><font class="blue">开始时间</font></td>
              <td width="15%" nowrap="nowrap" align="center"><font class="blue">结束时间</font></td>
               <td width="15%" nowrap="nowrap" align="center"><font class="blue">离职原因</font></td>
          </tr>
         <c:forEach var="cur" items="${userChangeInfoList}" varStatus="vs">
           <tr>
                <td align="center">${vs.count}</td>
                <td align="left" >${cur.job_id}</td>
                <td align="left">${cur.user_name_old}</td>
                <td align="left">${cur.real_name_old}</td>
                 <td align="left">${cur.user_name_new}</td>
                <td align="left">${cur.real_name_new}</td>
                <td align="center">
                <fmt:formatDate value="${cur.start_date}" pattern="yyyy-MM-dd HH:mm:ss" />
               </td>
                <td align="center">
               <c:if test="${not empty cur.end_date}"><fmt:formatDate value="${cur.end_date}" pattern="yyyy-MM-dd HH:mm:ss" /></c:if>
               <c:if test="${empty cur.end_date}">至今</c:if> 
                </td>
                 <td align="center"><c:if test="${cur.chan_status eq 0}">转岗</c:if>
                 <c:if test="${cur.chan_status eq 1}">离职</c:if>
                 <c:if test="${cur.chan_status eq 2}">其他</c:if>
                 </td>
           </tr>
            <c:if test="${vs.last eq true}">
              <c:set var="i" value="${vs.count}" />
            </c:if>
          </c:forEach>
          <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
            <tr align="center">
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
               <td>&nbsp;</td>
            </tr>
          </c:forEach> 
          <tr><td colspan="9" align="center"><input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" /></td></tr> 
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
