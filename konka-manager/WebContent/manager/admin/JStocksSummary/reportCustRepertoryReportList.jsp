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
<body>
<div class="oarcont" id="body_oarcont">
  <div class="rtabcont1" style="overflow-x: auto;">
      <table width="100%" border="1" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" align="center">序号</td>
          <td nowrap="nowrap" align="left" width="8%">分公司</td>
          <td nowrap="nowrap" align="left" width="8%">部门</td>
          <td nowrap="nowrap" align="left">经办</td>
          <td nowrap="nowrap" align="left" width="8%">R3编码</td>
          <td nowrap="nowrap" align="left">客户名称</td>
          <td nowrap="nowrap" align="left">客户类型</td>
          <td nowrap="nowrap" align="left">细分类型</td>
          <td nowrap="nowrap" align="left">业务员</td>
          <td nowrap="nowrap" align="left" width="6%">型号分类</td>
          <td nowrap="nowrap" align="left" width="8%">型号</td>
          <td nowrap="nowrap" align="right" width="8%">前四周销量</td>
          <td nowrap="nowrap" align="right" width="6%">最高存量</td>
          <td nowrap="nowrap" align="right" width="8%">最低存量</td>
          <td nowrap="nowrap" align="right" width="6%">当前库存</td>
          <td nowrap="nowrap" align="left" width="8%">预警状态</td>
          <td nowrap="nowrap" align="center" width="8%">操作</td>
        </tr>
        <c:out value="${empty allList}"></c:out>
        <c:forEach var="cur" items="${allList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.DEPT_NAME)}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.L4_DEPT_NAME)}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.L5_DEPT_NAME)}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.R3_CODE)}</td>
            <td align="left" nowrap="nowrap">${cur.CUSTOMER_NAME}</td>
            <td align="left" nowrap="nowrap">${cur.PAR_CUSTOMER_TYPE_NAME}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.CUSTOMER_TYPE_NAME)}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.YWY_USER_NAME)}</td>
            <td align="left" nowrap="nowrap">
               <c:if test="${cur.GOODS_NAME_TYPE eq 0}">新品</c:if>
               <c:if test="${cur.GOODS_NAME_TYPE eq 1}">主销</c:if>
               <c:if test="${cur.GOODS_NAME_TYPE eq 2}">短缺</c:if>
            </td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.GOODS_NAME)}</td>
            <td align="right" nowrap="nowrap">${fn:escapeXml(cur.TOTAL_NUM)}</td>
             <td align="right" nowrap="nowrap">${fn:escapeXml(cur.TOTAL_NUM)}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.AVG_NUM}" pattern="0" /></td>
            <td align="right" nowrap="nowrap">${fn:escapeXml(cur.TOTLE_CUR_NUM)}</td>
            <td align="left" nowrap="nowrap">
	            <c:if test="${cur.TOTLE_CUR_NUM lt cur.AVG_NUM}"><label style="color: red;">赶紧补货</label></c:if>
	            <c:if test="${cur.TOTLE_CUR_NUM gt cur.TOTAL_NUM}"><label style="color: green;">库存超了</label></c:if>
            </td>
            <td align="center" nowrap="nowrap"><a href="javascript:void()" style="color: blue" >推送</a> </td>
          </tr>
        </c:forEach>
        <c:forEach begin="${fn:length(allList)}" end="${af.map.pager.pageSize - 1}">
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
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
      </table>
  </div>
</div>
</body>
</html>