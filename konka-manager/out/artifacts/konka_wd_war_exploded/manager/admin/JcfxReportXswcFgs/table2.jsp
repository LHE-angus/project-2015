<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
	<div style="text-align:right;margin-right:20px;">单位：万元</div> 
	        <table class="gridtable"  style="width:100%;font-size:12px;line-height:22px;">
	        <tr style="background-color: #072B31;color:#ffffff;">
	        	<td align="center" width="80" rowspan="2" >分公司</td><td  align="center" rowspan="2" >目标销售额</td><td  align="center" rowspan="2" >实际销售额</td><td align="center"  rowspan="2" >同期销售额</td><td  align="center"  rowspan="2" >完成率</td><td align="center"  rowspan="2" >增长率</td><td align="center"  rowspan="2" >累计目标额</td><td align="center"  rowspan="2" >累计销售额</td><td align="center"  rowspan="2" >累计完成率</td>
	        	<td align="center" colspan="7">各渠道销售额</td>
	        </tr>
	        <tr style="background-color: #072B31;color:#ffffff;">
	        	 <td align="center">国美</td>
	        	 <td align="center">苏宁</td>
	        	 <td align="center">其他连锁</td>
	        	 <td align="center">超市渠道</td>
	        	 <td align="center">城市客户</td>
	        	 <td align="center">县乡客户</td>
	        	 <td align="center">其他客户</td> 
	        </tr>
	          <c:set var="s_month_customer_sale" value="0"></c:set>
	          <c:set var="s_month_settle_money" value="0"></c:set>
	          <c:set var="s_pre_year_settle_money" value="0"></c:set>
	          <c:set var="s_month_settle_money_tbzf" value="0"></c:set>
	          <c:set var="s_month_settle_money_bz" value="0"></c:set>
	          <c:set var="s_customer_sale_lj" value="0"></c:set>
	          <c:set var="s_settle_money_lj" value="0"></c:set>
	          <c:set var="s_fgs_month_settle_money" value="0"></c:set>
	          
	          <c:set var="s_month_settle_money_gm" value="0"></c:set>
		      <c:set var="s_month_settle_money_sn" value="0"></c:set>
		      <c:set var="s_month_settle_money_qtls" value="0"></c:set>
		      <c:set var="s_month_settle_money_csqd" value="0"></c:set>
		      <c:set var="s_month_settle_money_cskh" value="0"></c:set>
		      <c:set var="s_month_settle_money_xxkh" value="0"></c:set>
		      <c:set var="s_month_settle_money_qtkh" value="0"></c:set>
		        
	          <c:forEach items="${fgsList}" var="cur" varStatus="vs">
	          	<c:set var="s_fgs_month_settle_money" value="${s_fgs_month_settle_money + cur.month_settle_money}"></c:set>
	          </c:forEach>
	          
	        <c:forEach items="${fgsList}" var="cur" varStatus="vs"> 
	          <c:set var="s_month_customer_sale" value="${s_month_customer_sale + cur.month_customer_sale}"></c:set>
	          <c:set var="s_month_settle_money" value="${s_month_settle_money + cur.month_settle_money}"></c:set>
	          <c:set var="s_pre_year_settle_money" value="${s_pre_year_settle_money + cur.pre_year_settle_money}"></c:set>
	          <c:set var="s_month_settle_money_bz" value="${s_month_settle_money_bz + cur.month_settle_money_bz}"></c:set>
	          <c:set var="s_customer_sale_lj" value="${s_customer_sale_lj + cur.map.customer_sale_lj}"></c:set>
	          <c:set var="s_settle_money_lj" value="${s_settle_money_lj + cur.map.settle_money_lj}"></c:set>
	          <c:set var="s_month_settle_money_gm" value="${s_month_settle_money_gm + cur.map.month_settle_money_gm}"></c:set>
		      <c:set var="s_month_settle_money_sn" value="${s_month_settle_money_sn + cur.map.month_settle_money_sn}"></c:set>
		      <c:set var="s_month_settle_money_qtls" value="${s_month_settle_money_qtls + cur.map.month_settle_money_qtls}"></c:set>
		      <c:set var="s_month_settle_money_csqd" value="${s_month_settle_money_csqd + cur.map.month_settle_money_csqd}"></c:set>
		      <c:set var="s_month_settle_money_cskh" value="${s_month_settle_money_cskh + cur.map.month_settle_money_cskh}"></c:set>
		      <c:set var="s_month_settle_money_xxkh" value="${s_month_settle_money_xxkh + cur.map.month_settle_money_xxkh}"></c:set>
		      <c:set var="s_month_settle_money_qtkh" value="${s_month_settle_money_qtkh + cur.map.month_settle_money_qtkh}"></c:set>
	        <tr  style="background-color: #E1F3EB;">
		        <td align="center">${cur.map.name }</td>
		        <td align="right" style="color: blue;"><fmt:formatNumber value="${cur.month_customer_sale}" pattern="0.00"/></td>
		        <td align="right" style="color: red;"><fmt:formatNumber value="${cur.month_settle_money}" pattern="0.00"/></td>
		        <td align="right"><fmt:formatNumber value="${cur.pre_year_settle_money}" pattern="0.00"/></td>
		        <td align="center">
		        	<c:if test="${cur.month_customer_sale gt 0 or cur.month_customer_sale lt 0 }">
		        		<fmt:formatNumber value="${cur.month_settle_money*100/cur.month_customer_sale}" pattern="0.00"/>%
		        	</c:if>
		        	<c:if test="${!(cur.month_customer_sale gt 0 or cur.month_customer_sale lt 0) }">
		        		-
		        	</c:if>
		        </td>
		        <td align="right">
		        	<c:if test="${cur.pre_year_settle_money gt 0 or cur.pre_year_settle_money lt 0 }">
		        		<c:if test="${cur.month_settle_money gt cur.pre_year_settle_money}">
			        		<fmt:formatNumber value="${(cur.month_settle_money-cur.pre_year_settle_money)*100/cur.pre_year_settle_money}" pattern="0.00"/>%
		        			<font color="green">↑</font>
		        		</c:if>
		        		<c:if test="${cur.month_settle_money lt cur.pre_year_settle_money}">
			        		<fmt:formatNumber value="${(cur.month_settle_money-cur.pre_year_settle_money)*100/cur.pre_year_settle_money}" pattern="0.00"/>%
		        			<font color="red">↓</font>
		        		</c:if>
		        	</c:if>
		        	<c:if test="${!(cur.month_settle_money gt 0 or cur.pre_year_settle_money lt 0) }">
		        		-
		        	</c:if>
		        </td> 
		        <td align="right" style="color: blue;"><fmt:formatNumber value="${cur.map.customer_sale_lj}" pattern="0.00"/></td>
		        <td align="right" style="color: red;"><fmt:formatNumber value="${cur.map.settle_money_lj}" pattern="0.00"/></td>
		        <td align="center">
		        	<c:if test="${cur.map.customer_sale_lj gt 0 or cur.map.customer_sale_lj lt 0 }">
		        		<fmt:formatNumber value="${cur.map.settle_money_lj*100/cur.map.customer_sale_lj}" pattern="0.00"/>%
		        	</c:if>
		        	<c:if test="${!(cur.map.customer_sale_lj gt 0 or cur.map.customer_sale_lj lt 0) }">
		        		-
		        	</c:if>
		        </td>
		        <td align="right" ><fmt:formatNumber value="${cur.map.month_settle_money_gm}" pattern="0.00"/></td>
		        <td align="right" ><fmt:formatNumber value="${cur.map.month_settle_money_sn}" pattern="0.00"/></td>
		        <td align="right" ><fmt:formatNumber value="${cur.map.month_settle_money_qtls}" pattern="0.00"/></td>
		        <td align="right" ><fmt:formatNumber value="${cur.map.month_settle_money_csqd}" pattern="0.00"/></td>
		        <td align="right" ><fmt:formatNumber value="${cur.map.month_settle_money_cskh}" pattern="0.00"/></td>
		        <td align="right" ><fmt:formatNumber value="${cur.map.month_settle_money_xxkh}" pattern="0.00"/></td>
		        <td align="right" ><fmt:formatNumber value="${cur.map.month_settle_money_qtkh}" pattern="0.00"/></td>
	         </tr>
	         </c:forEach>
	        <tr style="background-color: #5BCAC6;">
		        <td align="center" >合计</td>
		        <td align="right" style="color: blue;"><fmt:formatNumber value="${s_month_customer_sale}" pattern="0.00"/></td>
		        <td align="right" style="color: red;"><fmt:formatNumber value="${s_month_settle_money}" pattern="0.00"/></td>
		        <td align="right"><fmt:formatNumber value="${s_pre_year_settle_money}" pattern="0.00"/></td>
		        <td align="center">
		        	<c:if test="${s_month_customer_sale gt 0 or s_month_customer_sale lt 0 }">
		        		<fmt:formatNumber value="${s_month_settle_money*100/s_month_customer_sale}" pattern="0.00"/>%
		        	</c:if>
		        	<c:if test="${!(s_month_customer_sale gt 0 or s_month_customer_sale lt 0) }">
		        		-
		        	</c:if>
		        </td>
		        <td align="center">
		         	<c:if test="${s_pre_year_settle_money gt 0 or s_pre_year_settle_money lt 0 }">
		        		<c:if test="${s_month_settle_money gt s_pre_year_settle_money}">
			        		<fmt:formatNumber value="${(s_month_settle_money-s_pre_year_settle_money)*100/s_pre_year_settle_money}" pattern="0.00"/>%
		        			<font color="green">↑</font>
		        		</c:if>
		        		<c:if test="${s_month_settle_money lt s_pre_year_settle_money}">
		        			<fmt:formatNumber value="${(s_month_settle_money-s_pre_year_settle_money)*100/s_pre_year_settle_money}" pattern="0.00"/>%
		        			<font color="red">↓</font>
		        		</c:if> 
		        	</c:if>
		        	<c:if test="${!(s_pre_year_settle_money gt 0 or s_pre_year_settle_money lt 0) }">
		        		-
		        	</c:if>
		        </td> 
		        <td align="right" style="color: blue;"><fmt:formatNumber value="${s_customer_sale_lj}" pattern="0.00"/></td>
		        <td align="right" style="color: red;"><fmt:formatNumber value="${s_settle_money_lj}" pattern="0.00"/></td>
		        <td align="center">
		        	<c:if test="${s_customer_sale_lj gt 0 or s_customer_sale_lj lt 0 }">
		        		<fmt:formatNumber value="${s_settle_money_lj*100/s_customer_sale_lj}" pattern="0.00"/>%
		        	</c:if>
		        	<c:if test="${!(s_customer_sale_lj gt 0 or s_customer_sale_lj lt 0) }">
		        		-
		        	</c:if>
		        </td>
		      <td align="right" >${s_month_settle_money_gm}</td>
		      <td align="right" >${s_month_settle_money_sn}</td>
		      <td align="right" >${s_month_settle_money_qtls}</td>
		      <td align="right" >${s_month_settle_money_csqd}</td>
		      <td align="right" >${s_month_settle_money_cskh}</td>
		      <td align="right" >${s_month_settle_money_xxkh}</td>
		      <td align="right" >${s_month_settle_money_qtkh}</td> 
	         </tr>
	        </table> 