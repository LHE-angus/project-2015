<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>结果提示</title>
<script type="text/javascript" src="../../commons/scripts/jquery.js"></script>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div>
		<c:choose>
		    <c:when test="${!empty orderNO }">
		      	生成的R/3订单号:${orderNO} ,
		    </c:when>
		   <c:otherwise>   
		     	 生成R/3订单失败  ${orderNO}
		   </c:otherwise>
	  </c:choose>
	</div>
		
	<div>
	生成R/3订单处理结果,详情如下:
		<c:forEach items="${excuteMsg }" var="em" > 
			${ em.type } ==>  ${em.message } <br/>
		</c:forEach>
	</div>
	
	<div>
		<c:if test="${isOk==0}">
			<font style="font-size: 15 ;color: red">订单库存校验结果,详情如下:</font>
			<c:forEach items="${stockCheckResultList}" var="sr" > 
				机型:${sr.matnr} ,需求量 : ${sr.amount} ,剩余量:${sr.lamount},校验结果:${sr.isOk}  <br/>
			</c:forEach>
		</c:if>
	</div>
</body>
</html>