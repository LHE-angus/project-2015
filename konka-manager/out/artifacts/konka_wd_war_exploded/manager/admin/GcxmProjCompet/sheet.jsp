<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><%@ include file="/commons/pages/taglibs.jsp" %>
<table> 
<tr>
        <td width="5%" nowrap="nowrap" align="center">序号</td>
          <td width="10%" nowrap="nowrap" align="center">报备日期</td>
          <td width="10%" nowrap="nowrap" align="center">品牌</td>
          <td width="10%" nowrap="nowrap" align="center">型号</td>
          <td width="10%" nowrap="nowrap" align="center">报价（元/台）</td>
          <td width="10%" nowrap="nowrap" align="center">说明</td>
          <td width="10%" nowrap="nowrap" align="center">分公司</td>
          <td width="5%" nowrap="nowrap" align="center">项目编号</td>
          <td width="10%" nowrap="nowrap" align="center">项目名称</td>
          <td width="10%" nowrap="nowrap" align="center">项目类型</td>
          <td width="10%" nowrap="nowrap" align="center">项目状态</td>
          <td width="15%" nowrap="nowrap" align="center">报备人</td>
</tr><c:forEach var="cur" items="${entityList}" varStatus="vs">
<tr>
           <td align="center" nowrap="nowrap"> ${vs.count}</td>
              <td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.map.create_date}"  pattern="yyyy-MM-dd"></fmt:formatDate></td>
              <td align="center" nowrap="nowrap">${cur.brand_name}</td>
              <td align="center" nowrap="nowrap">${cur.compet_model}</td>
              <td align="center" nowrap="nowrap">${cur.compet_price}</td>
              <td align="left" nowrap="nowrap" title="${cur.compet_memo}"> 
              <c:choose>
                 <c:when test="${fn:length(cur.compet_memo) > 20}">
                 <c:out value="${fn:substring(cur.compet_memo, 0, 20)}...." />
                 </c:when>
             <c:otherwise>
                 <c:out value="${cur.compet_memo}" /> 
                 </c:otherwise>
             </c:choose>
             </td>
              <td align="center" nowrap="nowrap">${cur.map.dept_name}</td> 
              
              <td nowrap="nowrap" align="left"  >${cur.map.pj_code}</td>
              <td align="left" nowrap="nowrap" title="${cur.map.proj_name}"> 
              <c:choose>
                 <c:when test="${fn:length(cur.map.proj_name) > 20}">
                 <c:out value="${fn:substring(cur.map.proj_name, 0, 20)}...." />
                 </c:when>
             <c:otherwise>
                 <c:out value="${cur.map.proj_name}" /> 
                 </c:otherwise>
             </c:choose>
             </td>
              <td align="center" nowrap="nowrap">        
              	<c:if test="${cur.map.proj_type eq 1}">政府采购</c:if>
				<c:if test="${cur.map.proj_type eq 2}">酒店采购</c:if>
				<c:if test="${cur.map.proj_type eq 3}">企业采购</c:if>
				<c:if test="${cur.map.proj_type eq 4}">其他</c:if>
              </td>
               <td align="center" nowrap="nowrap"> 
              	<c:if test="${cur.map.info_state eq -1}">进行中</c:if>
				<c:if test="${cur.map.info_state eq 1}">已结束</c:if>
              </td>
              <td align="center" nowrap="nowrap">${cur.map.create_name}</td>
        </tr>
</c:forEach>
</table>