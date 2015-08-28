<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<logic-el:messagesPresent>
  <html-el:messages id="error">
    <script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
    <script type="text/javascript">//<![CDATA[
	jError("${error}", {HorizontalPosition:"center", VerticalPosition:"top", TimeShown:3000});
	//]]></script>
  </html-el:messages>
</logic-el:messagesPresent>
<logic-el:messagesPresent message="true">
  <html-el:messages id="message" message="true">
    <script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
    <script type="text/javascript">//<![CDATA[
	jSuccess("${message}", {HorizontalPosition:"center", VerticalPosition:"top", TimeShown:3000});
	//]]></script>
  </html-el:messages>
</logic-el:messagesPresent>