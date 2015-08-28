<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><%@ include file="/commons/pages/taglibs.jsp" %>
<table> 
<tr>
          <td width="5%" align="center"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
          <td width="5%" nowrap="nowrap" align="center">序号</td>
          <td width="5%" nowrap="nowrap" align="center">项目编号</td>
          <td width="10%" nowrap="nowrap" align="center">分公司</td>
          <td width="10%" nowrap="nowrap" align="center">项目类型</td>
          <td width="10%" nowrap="nowrap" align="center">项目名称</td>
          <td width="10%" nowrap="nowrap" align="center">报价型号</td>
          <td width="10%" nowrap="nowrap" align="center">分公司报价</td>
          <td width="10%" nowrap="nowrap" align="center">交货日期</td>
          <td width="10%" nowrap="nowrap" align="center">项目状态</td>
          <td width="15%" nowrap="nowrap" align="center">是否中标</td>
          <td width="15%" nowrap="nowrap" align="center">供货机型</td>
          <td width="15%" nowrap="nowrap" align="center">供货数量</td>
          <td width="15%" nowrap="nowrap" align="center">创建日期</td>
          <td width="15%" nowrap="nowrap" align="center">创建人</td>
          <td width="5%" nowrap="nowrap" align="center">单据状态</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr> 
              <td align="center" nowrap="nowrap" >
                <c:if test="${cur.gcxmProjSupply.del_mark eq 0 and cur.gcxmProjSupply.info_state eq -1}"><input name="pks" type="checkbox" id="pks_${cur.id}" value="${cur.id}" /></c:if>
                <c:if test="${cur.gcxmProjSupply.del_mark ne 0 or cur.gcxmProjSupply.info_state ne -1}"><input name="pks" type="checkbox" id="pks_${cur.id}" value="${cur.id}" disabled="disabled" /></c:if>
              </td>
              <td align="center" nowrap="nowrap"> ${vs.count}</td>
              <td nowrap="nowrap" align="left"  ><a style="color: blue;" href="#" onclick="doNeedMethod(null, 'GcxmProjSupply.do', 'view', 'id=${cur.id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())">${cur.proj_code}</a></td>
              <td align="center" nowrap="nowrap">${cur.map.dept_name}</td> 
              <td align="center" nowrap="nowrap"> 
              	<c:if test="${cur.proj_type eq 1}">政府采购</c:if>
				<c:if test="${cur.proj_type eq 2}">酒店采购</c:if>
				<c:if test="${cur.proj_type eq 3}">企业采购</c:if>
				<c:if test="${cur.proj_type eq 4}">其他</c:if>
              </td>
               <td align="left" nowrap="nowrap" title="${cur.proj_name}"> 
              <c:choose>
                 <c:when test="${fn:length(cur.proj_name) > 20}">
                 <c:out value="${fn:substring(cur.proj_name, 0, 20)}...." />
                 </c:when>
             <c:otherwise>
                 <c:out value="${cur.proj_name}" /> 
                 </c:otherwise>
             </c:choose>
             </td>
              <td align="left" nowrap="nowrap">${cur.gcxmProjOffer.offer_model}</td>
              <td align="left" nowrap="nowrap">${cur.gcxmProjOffer.offer_price}</td>
              <td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.gcxmProjOffer.delivery_date}"  pattern="yyyy-MM-dd"></fmt:formatDate></td>
              <td align="center" nowrap="nowrap"> 
                <c:if test="${empty cur.gcxmProjSupply.info_state}">未提交</c:if>
              	<c:if test="${cur.gcxmProjSupply.info_state eq -1}">进行中</c:if>
				<c:if test="${cur.gcxmProjSupply.info_state eq 1}">已结束</c:if>
              </td>
              <td align="center" nowrap="nowrap">
              <c:if test="${cur.is_win eq 0}">中标</c:if>
              <c:if test="${cur.is_win eq 1}">未中标</c:if>
              <c:if test="${empty cur.is_win}">无</c:if>
              </td>
              <td align="left" nowrap="nowrap"> ${cur.gcxmProjSupply.supply_model}</td>
              <td align="center" nowrap="nowrap"> ${cur.gcxmProjSupply.supply_num}</td>
              <td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.gcxmProjSupply.create_date}"  pattern="yyyy-MM-dd"></fmt:formatDate></td>
              <td align="center" nowrap="nowrap"> ${cur.gcxmProjSupply.create_name}</td>
              <td align="center" nowrap="nowrap"> 
                  <c:if test="${cur.is_validate eq 0}">有效</c:if>
                  <c:if test="${cur.is_validate eq 1}">无效</c:if>
              </td>
              </tr>
</c:forEach>
</table>