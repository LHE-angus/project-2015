<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title> </title>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/zxmall/css/global1.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/zxmall/css/epp.css" />  
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/purchase.css" />  
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script> 
</head>
<body> 
  	 	  <a href="javascript:void(0);" onclick="showT(0);"><b>今天</b> </a> 
  	   	  <c:set var="days" value="0" />
	      <c:set var="cdays" value="0" /> 
	      <c:set var="c" value="0" />
	      <c:forEach items="${entity.ecLuckyBuyList}" var="cur" varStatus="vs">
		       <c:if test="${cur.map.days ne 0 }"> 
		     	<c:set var="c" value="${c+1 }" />
			      	<c:if test="${cdays ne cur.map.days  }"> 
				     	<c:set var="cdays" value="${cur.map.days}" />
				      	<c:set var="days" value="${days+1 }" />
				     	<c:set var="c" value="1" />
				      	&nbsp; &nbsp;<a href="javascript:void(0);" onclick="showT(${days });"><b><fmt:formatDate value="${cur.add_date}"  pattern="yyyy-MM-dd"/></b></a>
			     	 </c:if>
		     	</c:if>
	      </c:forEach><br/><br/>
			 <c:set var="days" value="0" />
			  <c:set var="cdays" value="0" /> 
			  <c:set var="c" value="0" />
		      <c:forEach items="${entity.ecLuckyBuyList}" var="cur" varStatus="vs">  
		        <c:if test="${cur.map.days ne 0  }"> 
			      	<c:set var="c" value="${c+1 }" />
				    <c:if test="${cdays ne cur.map.days  }"> 
					     <c:set var="cdays" value="${cur.map.days}" />
					     <c:set var="days" value="${days+1 }" />
					     <c:set var="c" value="1" />
					  	<c:if test="${days >1  }"></table></c:if>
			 		 <table style="width:100%;" border="1" id="table_${days }"> 
			 		 <tr><td colspan="5">第${days }天&nbsp;<fmt:formatDate value="${cur.add_date}"  pattern="yyyy-MM-dd"/></td></tr>
					 <tr><td align="center" width="5%">楼层 </td><td align="center" width="15%">流水号 </td><td align="center" width="20%">抢楼时间 </td><td align="center" width="10%">数量 </td><td align="center" width="15%">购买人 </td></tr>
					</c:if> 
			      	<tr id="tr_${days }_${c}" >
				       	<td align="center">${c} </td>
				       	<td align="center">${cur.trade_index }</td>
				    	<td align="center"><fmt:formatDate value="${cur.add_date}"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
				    	<td align="center">${cur.num }</td>
				    	<td>${cur.user_name } </td>
			    	</tr> 
		    	</c:if> 
		      </c:forEach>
		      <table style="width:100%;" border="1" id="table_0"> 
			 	<tr><td colspan="5">今天&nbsp;<fmt:formatDate value="${cur.add_date}"  pattern="yyyy-MM-dd"/></td></tr>
				<tr><td align="center" width="5%">楼层 </td><td align="center" width="15%">流水号 </td><td align="center" width="20%">抢楼时间 </td><td align="center" width="10%">数量 </td><td align="center" width="15%">购买人 </td></tr>
		        <c:set var="c1" value="0" />
		     	<c:forEach items="${entity.ecLuckyBuyList}" var="cur" varStatus="vs">  
		        <c:if test="${cur.map.days eq 0  }"> 
		          	<c:set var="c1" value="${c1+1 }" />  
			      	<tr id="tr_0_${c1}" >
				       	<td align="center">${c1} </td>
				       	<td align="center">${cur.trade_index }</td>
				    	<td align="center"><fmt:formatDate value="${cur.add_date}"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
				    	<td align="center">${cur.num }</td>
				    	<td>${cur.user_name } </td>
			    	</tr>  
		    	</c:if> 
		      	</c:forEach>
		 </table><br/> 
<script type="text/javascript">//<![CDATA[ 
function showT(t){
	for(var i=0;i<=10;i++){
		 if(i==t){
			 $("#table_"+i).show();
		 }else{
			 $("#table_"+i).hide();
		 }
	}
}
$(document).ready(function(){
	 showT(0);
});
//]]></script>
</body>
</html>