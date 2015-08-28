<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<script type="text/javascript">//<![CDATA[
window.onload = function () {
	// alert("您没有登录或操作超时，请重新登录！");//\n如果您已经选择了记住密码，系统将自动重新登录。
	top.location.href = "${ctx}/login.do";
};
//]]></script>