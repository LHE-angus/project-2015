<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 <html xmlns="http://www.w3.org/1999/xhtml"> 
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
		<meta http-equiv="MSThemeCompatible" content="no" />
		<meta http-equiv="X-UA-Compatible" content="IE=7" />
		<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
		<style>
			.rtable1 td {
				padding:2px 5px;
			}
			body {font-family:微软雅黑,宋体; }
		</style> 
	</head> 
	<body >
		<div class="oarcont">
			    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
			        <tr align="left">
							<td ><font style="font-size: 14pt;">total of [${sessioncount }] connections on current server, the real user has [${fn:length(onlineUserAccounts)}],they are:</font></td>
			        </tr>
			        <tr>
						<td>
							<c:forEach var="cur" items="${onlineUserAccounts}" varStatus="status">
								${cur }&nbsp;&nbsp;
							    <c:if test="${status.count%16==0}">
							        <br /> 
							    </c:if>
							</c:forEach>
						</td>
					</tr>
			      </table>
		</div>
	</body> 
	
	
</html> 
