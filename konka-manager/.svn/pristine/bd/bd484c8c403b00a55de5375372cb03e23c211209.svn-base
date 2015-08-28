<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<table border="1">
	<tr >
        <td width="5%" nowrap="nowrap" align="center">序号</td>
        <td width="20%" nowrap="nowrap" align="center">商品名称</td>
        <td width="20%" nowrap="nowrap" align="center" >评论标题</td>
        <td width="5%" nowrap="nowrap" align="center">评论时间</td>
        <td width="7%" nowrap="nowrap" align="center">审核状态</td> 
 	</tr><c:forEach items="${entityList}" var="cur" varStatus="vs">
	<tr>
        <td align="center" nowrap="nowrap">${vs.count}</td>
        <td align="center"  nowrap="nowrap">${cur.map.pd_name}</td>
        <td align="center"  nowrap="nowrap">${fn:escapeXml(fnx:abbreviate(cur.eval_title, 2 * 20, "..."))}</td>
        <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.eval_date}"  pattern="yyyy-MM-dd"></fmt:formatDate></td>
        <td align="center"nowrap="nowrap"><c:choose><c:when test="${cur.audit_state eq 0}">未审核</c:when><c:when test="${cur.audit_state eq 1}">已审核</c:when></c:choose></td>
    </tr></c:forEach>
</table>