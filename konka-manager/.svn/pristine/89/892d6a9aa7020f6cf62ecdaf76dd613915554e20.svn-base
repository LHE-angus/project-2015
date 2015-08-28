<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
</head>
<body >
   <div>
    <table width="100%" border="1" cellpadding="0" cellspacing="1">
      <tr class="tabtt1">
        <td align="center" nowrap="nowrap">序号</td>        
        <td align="center" nowrap="nowrap">分公司</td>
        <td align="center" nowrap="nowrap">一级部门</td>
        <td align="center" nowrap="nowrap">二级部门</td>
        <td align="center" nowrap="nowrap">业务员</td>
        <td align="center" nowrap="nowrap">客户细分类型</td>
        <td align="center" nowrap="nowrap">客户名称</td>
        <td align="center" nowrap="nowrap">R3编码</td>
        <td align="center" nowrap="nowrap">年份</td>        
        <td align="center" nowrap="nowrap">1月</td>
        <td align="center" nowrap="nowrap">2月</td>
        <td align="center" nowrap="nowrap">3月</td>
        <td align="center" nowrap="nowrap">4月</td>
        <td align="center" nowrap="nowrap">5月</td>
        <td align="center" nowrap="nowrap">6月</td>
        <td align="center" nowrap="nowrap">7月</td>
        <td align="center" nowrap="nowrap">8月</td>
        <td align="center" nowrap="nowrap">9月</td>
        <td align="center" nowrap="nowrap">10月</td>
        <td align="center" nowrap="nowrap">11月</td>
        <td align="center" nowrap="nowrap">12月</td>
      </tr>
      <c:forEach var="cur" items="${allList}" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
          <td align="left" nowrap="nowrap">${cur.DEPT_NAME}</td>
          <td align="left" nowrap="nowrap">
          	<c:if test="${cur.L4_DEPT_NAME != cur.DEPT_NAME}">
          		${cur.L4_DEPT_NAME}
          	</c:if>
          </td>
          <td align="left" nowrap="nowrap">${cur.L5_DEPT_NAME}</td>
          <td align="left" nowrap="nowrap">${cur.YWY_USER_NAME}</td>
          <td align="left" nowrap="nowrap">${cur.CUSTOMER_TYPE_NAME}</td>
          <td align="left" nowrap="nowrap">${cur.CUSTOMER_NAME}</td>
          <td align="left" nowrap="nowrap">${cur.R3_CODE}</td>    
          <td align="center" nowrap="nowrap">${cur.YEAR}</td>
          <td align="center" nowrap="nowrap">${cur.MONTH1}</td>
          <td align="center" nowrap="nowrap">${cur.MONTH2}</td>
          <td align="center" nowrap="nowrap">${cur.MONTH3}</td>
          <td align="center" nowrap="nowrap">${cur.MONTH4}</td>
          <td align="center" nowrap="nowrap">${cur.MONTH5}</td>
          <td align="center" nowrap="nowrap">${cur.MONTH6}</td>
          <td align="center" nowrap="nowrap">${cur.MONTH7}</td>
          <td align="center" nowrap="nowrap">${cur.MONTH8}</td>
          <td align="center" nowrap="nowrap">${cur.MONTH9}</td>
          <td align="center" nowrap="nowrap">${cur.MONTH10}</td>
          <td align="center" nowrap="nowrap">${cur.MONTH11}</td>
          <td align="center" nowrap="nowrap">${cur.MONTH12}</td>
        </tr>
      </c:forEach>
    </table>
</div>
<script type="text/javascript"><!--//<![CDATA[
//]]>--></script>
</body>
</html>
