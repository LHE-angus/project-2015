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
<div class="oarcont" id="body_oarcont">
  <div class="rtabcont1" id="divExcel" title="明细表${date}">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
 		 <td width="40" align="center" nowrap="nowrap">序号</td>
	          <c:if test="${not empty af.map.selected01 }">
		          	 <td align="center" nowrap="nowrap">申请人</td>
		          </c:if>
	          <c:if test="${not empty af.map.selected02 }">
		          	 <td align="center" nowrap="nowrap">客户名称</td>
		          </c:if>
	          <c:if test="${not empty af.map.selected03 }">
		          	 <td align="center" nowrap="nowrap">月份</td>
		          </c:if>
	          <c:if test="${not empty af.map.selected04 }">
		          	 <td align="center" nowrap="nowrap">费用类别</td>
		          </c:if>
	          <td width="120" nowrap="nowrap" align="center" >申请单数量</td>
	          <td width="120" nowrap="nowrap" align="center" >申请数量合计</td>
	          <td width="120" nowrap="nowrap" align="center" >申请费用合计</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
	            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
	            
	          <c:if test="${not empty af.map.selected01 }">
		          	 <td align="center">
			          	 <c:forEach var="c_map" items="${cur}">
			           	 	<c:if test="${fn:containsIgnoreCase(c_map.key,'submit_user')}">
			           	 			${c_map.value}
			           	 	</c:if>
			            </c:forEach>
		          	</td>
		          </c:if>
	          
	          <c:if test="${not empty af.map.selected02 }">
		          	 <td align="center">
		          	  <c:forEach var="c_map" items="${cur}">
			           	 	<c:if test="${fn:containsIgnoreCase(c_map.key,'r3_shop_id')}">
			           	 			${c_map.value}
			           	 	</c:if>
			            </c:forEach>
			           </td>
		          </c:if>
	          
	          <c:if test="${not empty af.map.selected03 }">
		          	 <td align="center">
							<c:forEach var="c_map" items="${cur}">
			           	 	<c:if test="${fn:containsIgnoreCase(c_map.key,'submit_date')}">
			           	 			${c_map.value}
			           	 	</c:if>
			            </c:forEach>		          	 
			           </td>
		          </c:if>
	          <c:if test="${not empty af.map.selected04 }">
		          	 <td align="center">
		          	  <c:forEach var="c_map" items="${cur}">
			           	 	<c:if test="${fn:containsIgnoreCase(c_map.key,'c_index')}">
			           	 			${c_map.value}
			           	 	</c:if>
			            </c:forEach>
			           </td>
		          </c:if>
	            <td align="right" nowrap="nowrap">
	            	<c:forEach var="c_map" items="${cur}">
		           	 	<c:if test="${fn:containsIgnoreCase(c_map.key,'num') }">
		            	<c:set var="num" value="${c_map.value}"></c:set>
		           	 			${c_map.value}
		           	 	</c:if>
		            </c:forEach>
	            </td>
	             <td align="right" nowrap="nowrap">
	            	<c:forEach var="c_map" items="${cur}">
		           	 	<c:if test="${fn:containsIgnoreCase(c_map.key,'count') }">
		            	<c:set var="num" value="${c_map.value}"></c:set>
		           	 			${c_map.value}
		           	 	</c:if>
		            </c:forEach>
	            </td>
	         
	            <td align="right" nowrap="nowrap">
		            <c:forEach var="c_map" items="${cur}">
			           	 	<c:if test="${fn:containsIgnoreCase(c_map.key,'all_price') }">
			           	 			<fmt:formatNumber type="currency" value="${c_map.value }"></fmt:formatNumber> 
			           	 	</c:if>
			            </c:forEach>
	             </td>
          </tr>
          <c:if test="${vs.last eq true}">
            <c:set var="i" value="${vs.count}" />
          </c:if>
        </c:forEach>
      </table>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/scripts/print.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	window.onload=function(){
		toExcel('divExcel', '?method=toExcel');
	};
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
