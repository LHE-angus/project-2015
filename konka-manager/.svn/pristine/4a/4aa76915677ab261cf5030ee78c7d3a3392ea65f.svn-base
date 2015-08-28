<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<div style="text-align:right;margin-right:20px;">单位：万元</div> 
<table class="gridtable"  style="width:100%;font-size:12px;line-height:22px;">
	   		<tr style="background-color: #072B31;color:#ffffff;">
	        	<td align="center" width="60">渠道</td><td  align="center">目标零售额</td><td  align="center">实际零售额</td><td  align="center">同期零售额</td><td  align="center">完成率</td><td align="center">增长率</td><td align="center">实际零售额占比</td><td align="center">累计目标额</td><td align="center">累计零售额</td><td align="center">累计完成率</td>
	        </tr>
	          <c:set var="s_month_retail_money_task" value="0"></c:set>
	          <c:set var="s_month_retail_money" value="0"></c:set>
	          <c:set var="s_pre_year_retail_money" value="0"></c:set>
	          <c:set var="s_month_retail_money_tbzf" value="0"></c:set>
	          <c:set var="s_month_retail_money_bz" value="0"></c:set>
	          <c:set var="s_month_retail_money_task_lj" value="0"></c:set>
	          <c:set var="s_retail_money_lj" value="0"></c:set>
	          <c:set var="s_fgs_month_retail_money" value="0"></c:set>
	          <c:forEach items="${areaList}" var="cur" varStatus="vs">
	          	<c:set var="s_fgs_month_retail_money" value="${s_fgs_month_retail_money + cur.month_retail_money}"></c:set>
	          </c:forEach>
	        <c:forEach items="${areaList}" var="cur" varStatus="vs"> 
	          <c:set var="s_month_retail_money_task" value="${s_month_retail_money_task + cur.month_retail_money_task}"></c:set>
	          <c:set var="s_month_retail_money" value="${s_month_retail_money + cur.month_retail_money}"></c:set>
	          <c:set var="s_pre_year_retail_money" value="${s_pre_year_retail_money + cur.pre_year_retail_money}"></c:set>
	          <c:set var="s_month_retail_money_bz" value="${s_month_retail_money_bz + cur.month_retail_money_bz}"></c:set>
	          <c:set var="s_month_retail_money_task_lj" value="${s_month_retail_money_task_lj + cur.map.month_retail_money_task_lj}"></c:set>
	          <c:set var="s_retail_money_lj" value="${s_retail_money_lj + cur.map.retail_money_lj}"></c:set>
	        <tr style="background-color: #E1F3EB;">
		        <td align="center">
		        <a href="${ctx}/manager/admin/JcfxReportLswcFgs.do?year=${year}&month=${month}&fgs_id=${fgs_id}&filter_by=${cur.map.name}">
		        ${cur.map.name}</a></td>
		        <td align="right" style="color: blue;"><fmt:formatNumber value="${cur.month_retail_money_task}" pattern="0.00"/></td>
		        <td align="right" style="color: red;"><fmt:formatNumber value="${cur.month_retail_money}" pattern="0.00"/></td>
		        <td align="right"><fmt:formatNumber value="${cur.pre_year_retail_money}" pattern="0.00"/></td>
		        <td align="center">
		        	<c:if test="${cur.month_retail_money_task gt 0 or cur.month_retail_money_task lt 0 }">
		        		<fmt:formatNumber value="${cur.month_retail_money*100/cur.month_retail_money_task}" pattern="0.00"/>%
		        	</c:if>
		        	<c:if test="${!(cur.month_retail_money_task gt 0 or cur.month_retail_money_task lt 0) }">
		        		-
		        	</c:if>
		        </td>
		        <td align="right">
		        	<c:if test="${cur.pre_year_retail_money gt 0 or cur.pre_year_retail_money lt 0 }">
		        		<c:if test="${cur.month_retail_money gt cur.pre_year_retail_money}">
			        		<fmt:formatNumber value="${(cur.month_retail_money-cur.pre_year_retail_money)*100/cur.pre_year_retail_money}" pattern="0.00"/>%
		        			<font color="green">↑</font>
		        		</c:if>
		        		<c:if test="${cur.month_retail_money lt cur.pre_year_retail_money}">
			        		<fmt:formatNumber value="${(cur.month_retail_money-cur.pre_year_retail_money)*100/cur.pre_year_retail_money}" pattern="0.00"/>%
		        			<font color="red">↓</font>
		        		</c:if>
		        	</c:if>
		        	<c:if test="${!(cur.month_retail_money gt 0 or cur.pre_year_retail_money lt 0) }">
		        		-
		        	</c:if>
		        </td>
		        <td align="right">
		        	<c:if test="${s_fgs_month_retail_money gt 0 or s_fgs_month_retail_money lt 0 }">
		        		<fmt:formatNumber value="${cur.month_retail_money*100/s_fgs_month_retail_money}" pattern="0.00"/>%
		        	</c:if>
		        	<c:if test="${!(s_fgs_month_retail_money gt 0 or s_fgs_month_retail_money lt 0) }">
		        		-
		        	</c:if>
		        </td>
		        <td align="right" style="color: blue;"><fmt:formatNumber value="${cur.map.month_retail_money_task_lj}" pattern="0.00"/></td>
		        <td align="right" style="color: red;"><fmt:formatNumber value="${cur.map.retail_money_lj}" pattern="0.00"/></td>
		        <td align="center">
		        	<c:if test="${cur.map.month_retail_money_task_lj gt 0 or cur.map.month_retail_money_task_lj lt 0 }">
		        		<fmt:formatNumber value="${(cur.map.retail_money_lj-cur.map.month_retail_money_task_lj)*100/cur.map.month_retail_money_task_lj}" pattern="0.00"/>%
		        	</c:if>
		        	<c:if test="${!(cur.map.month_retail_money_task_lj gt 0 or cur.map.month_retail_money_task_lj lt 0) }">
		        		-
		        	</c:if>
		        </td>
	         </tr>
	         </c:forEach>
	        <tr style="background-color: #5BCAC6;">
		        <td align="center" >合计</td>
		        <td align="right" style="color: blue;"><fmt:formatNumber value="${s_month_retail_money_task}" pattern="0.00"/></td>
		        <td align="right" style="color: red;"><fmt:formatNumber value="${s_month_retail_money}" pattern="0.00"/></td>
		        <td align="right"><fmt:formatNumber value="${s_pre_year_retail_money}" pattern="0.00"/></td>
		        <td align="center">
		        	<c:if test="${s_month_retail_money_task gt 0 or s_month_retail_money_task lt 0 }">
		        		<fmt:formatNumber value="${s_month_retail_money*100/s_month_retail_money_task}" pattern="0.00"/>%
		        	</c:if>
		        	<c:if test="${!(s_month_retail_money_task gt 0 or s_month_retail_money_task lt 0) }">
		        		-
		        	</c:if>
		        </td>
		        <td align="center">
		        	<c:if test="${s_pre_year_retail_money gt 0 or s_pre_year_retail_money lt 0 }">
		        		<c:if test="${s_month_retail_money gt s_pre_year_retail_money}">
			        		<fmt:formatNumber value="${(s_month_retail_money-s_pre_year_retail_money)*100/s_pre_year_retail_money}" pattern="0.00"/>%
		        			<font color="green">↑</font>
		        		</c:if>
		        		<c:if test="${s_month_retail_money lt s_pre_year_retail_money}">
		        			<fmt:formatNumber value="${(s_month_retail_money-s_pre_year_retail_money)*100/s_pre_year_retail_money}" pattern="0.00"/>%
		        			<font color="red">↓</font>
		        		</c:if>
		        	</c:if>
		        	<c:if test="${!(s_pre_year_retail_money gt 0 or s_pre_year_retail_money lt 0) }">
		        		-
		        	</c:if>
		        </td>
		        <td align="right">100%</td>
		        <td align="right" style="color: blue;"><fmt:formatNumber value="${s_month_retail_money_task_lj}" pattern="0.00"/></td>
		        <td align="right" style="color: red;"><fmt:formatNumber value="${s_retail_money_lj}" pattern="0.00"/></td>
		        <td align="center">
		        	<c:if test="${s_month_retail_money_task_lj gt 0 or s_month_retail_money_task_lj lt 0 }">
		        		<fmt:formatNumber value="${s_retail_money_lj*100/s_month_retail_money_task_lj}" pattern="0.00"/>%
		        	</c:if>
		        	<c:if test="${!(s_month_retail_money_task_lj gt 0 or s_month_retail_money_task_lj lt 0) }">
		        		-
		        	</c:if>
		        </td>
	         </tr>
</table> 