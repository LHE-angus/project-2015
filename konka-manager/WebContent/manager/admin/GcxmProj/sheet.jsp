<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><%@ include file="/commons/pages/taglibs.jsp" %>
<table> 
<tr>
          <td width="5%" nowrap="nowrap" align="center">序号</td>
          <td width="5%" nowrap="nowrap" align="center">项目编号</td>
          <td width="10%" nowrap="nowrap" align="center">分公司</td>
          <td width="10%" nowrap="nowrap" align="center">项目类型</td>
          <td width="10%" nowrap="nowrap" align="center">项目名称</td>
          <td width="10%" nowrap="nowrap" align="center">采购人/投标人</td>
          <td width="10%" nowrap="nowrap" align="center">采购人/投标人电话</td>
          <td width="10%" nowrap="nowrap" align="center">开标/报价日期</td>
          <td width="10%" nowrap="nowrap" align="center">采购尺寸</td>
          <td width="15%" nowrap="nowrap" align="center">采购数目</td>
          <td width="15%" nowrap="nowrap" align="center">采购预算</td>
          <td width="15%" nowrap="nowrap" align="center">审核状态</td>
          <td width="15%" nowrap="nowrap" align="center">推荐机型1</td>
          <td width="15%" nowrap="nowrap" align="center">推荐机型2</td>
          <td width="15%" nowrap="nowrap" align="center">推荐机型3</td>
          <td width="15%" nowrap="nowrap" align="center">创建日期</td>
          <td width="15%" nowrap="nowrap" align="center">创建人</td>
</tr><c:forEach var="cur" items="${entityList}" varStatus="vs">
<tr>
          <td align="center" nowrap="nowrap"> ${vs.count}</td>
              <td nowrap="nowrap" align="left"  >${cur.proj_code}</td>
              <td align="center" nowrap="nowrap">${cur.map.dept_name}</td>
              <td align="center" nowrap="nowrap"> 
              	<c:if test="${cur.proj_type eq 1}">政府采购</c:if>
				<c:if test="${cur.proj_type eq 2}">酒店采购</c:if>
				<c:if test="${cur.proj_type eq 3}">企业采购</c:if>
				<c:if test="${cur.proj_type eq 4}">其他</c:if>
              </td>
              <td align="center" nowrap="nowrap"> ${cur.proj_name}</td>
              <td align="center" nowrap="nowrap"> ${cur.buyer}</td>
              <td align="center" nowrap="nowrap"> ${cur.buyer_tel}</td>
              <td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.offer_date}"  pattern="yyyy-MM-dd"></fmt:formatDate></td>
              <td align="center" nowrap="nowrap"> ${cur.size}</td>
              <td align="center" nowrap="nowrap"> ${cur.buyer_num}</td>
              <td align="center" nowrap="nowrap"> ${cur.budget}</td>
              <td align="center" nowrap="nowrap"> 
              	<c:if test="${cur.info_state eq -1}">未提交</c:if>
				<c:if test="${cur.info_state eq 0}">审核中</c:if>
				<c:if test="${cur.info_state eq 1}">已完结</c:if>
              </td>
              <td align="center" nowrap="nowrap"> ${cur.map.model_1}</td>
              <td align="center" nowrap="nowrap"> ${cur.map.model_2}</td>
              <td align="center" nowrap="nowrap"> ${cur.map.model_3}</td>
              <td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.create_date}"  pattern="yyyy-MM-dd"></fmt:formatDate></td>
              <td align="center" nowrap="nowrap"> ${cur.create_name}</td>
        </tr>
</c:forEach>
</table>