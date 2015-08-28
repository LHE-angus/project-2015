<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<logic-el:messagesPresent>
  <html-el:messages id="error">
    <script type="text/javascript">//<![CDATA[
	jError("${error}", {HorizontalPosition:"center", VerticalPosition:"center", TimeShown:3000});
	//]]></script>
  </html-el:messages>
</logic-el:messagesPresent>
<logic-el:messagesPresent message="true">
  <html-el:messages id="message" message="true">
    <script type="text/javascript">//<![CDATA[
	jError("${message}", {HorizontalPosition:"center", VerticalPosition:"center", TimeShown:3000});
	//]]></script>
  </html-el:messages>
</logic-el:messagesPresent>