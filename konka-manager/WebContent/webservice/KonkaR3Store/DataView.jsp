<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" href="${ctx}/webservice/KonkaMobileDateReport/css/base.css">
</head>
<title>康佳</title>
<link rel="stylesheet" href="css/base.css">
</head>
<div class="sjfx-contE" width="100%" width="100%">
        	<h3 class="sjfx-cont-t">按${title}分析</h3>
            <ul class="sjfx-cont-list">
            	<li><span>${title}</span><span>销售量</span><span>增长率</span></li>
            	<c:forEach items="${entityList}" var="cur" >
                <li>
                <span>
               	<c:forEach items="${sizeSecList}" var="sizeSec" varStatus="vs">
					<c:if test="${sizeSec.field1 eq cur.map.type_name}">${sizeSec.type_name}</c:if>
				</c:forEach>
                </span><span>${cur.map.num}</span>
                <span>
                <fmt:formatNumber value="${cur.map.range*100}" pattern="#0.00" />%
                <c:if test="${cur.map.range gt 0}"> 
                <img src="${ctx}/webservice/KonkaMobileDateReport/images/arrowrt.png">
                </c:if>
                <c:if test="${cur.map.range lt 0}"> 
                <img src="${ctx}/webservice/KonkaMobileDateReport/images/arrowgb.png">
                </c:if>
                </span>
                </li>
                </c:forEach>
               </ul>
        </div>
</body>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/scripts/print.js"></script> 
</html>