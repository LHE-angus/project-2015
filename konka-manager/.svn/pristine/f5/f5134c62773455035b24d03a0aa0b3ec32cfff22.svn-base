<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functionsx" prefix="fnx"%>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el" %>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic-el" %>
<c:set var="ctx" value="${pageContext.request['contextPath']}" />
<c:set var="fctx" value="${empty fctx ? ctx : fctx}" />
<c:set var="www_url_encode" value="http://192.168.2.215" />
<c:set var="ctxMmt" value="www.mymyty.com" />
<c:if test="${true}">

	<c:if test="${pageContext.request.serverName eq 'qdgl.konka.com' and fn:startsWith(pageContext.request.requestURI, '/customer/') and not fn:endsWith(pageContext.request.requestURI, '/manager/JxcKonkaOrderRegister.do')}">
	<c:url var="url" value="/customer/login.do" />
	<c:redirect url="http://vip.konka.com${url}" />
	</c:if>

	<c:if test="${pageContext.request.serverName eq 'vip.konka.com' and (pageContext.request.requestURI eq '/' or pageContext.request.requestURI eq '/login.jsp')}">
	<c:url var="url" value="/customer/login.do" />
	<c:redirect url="http://vip.konka.com${url}" />
	</c:if>

	<c:if test="${pageContext.request.serverName eq 'vip.konka.com' and not fn:startsWith(pageContext.request.requestURI, '/customer/')}">
	<c:redirect url="http://qdgl.konka.com/login.do" />
	</c:if>

</c:if>
<fmt:setBundle basename="i18n.messages" var="lang" scope="session"/>
<html-el:xhtml />