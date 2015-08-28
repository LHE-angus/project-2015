<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>内部服务器异常错误</title>
<style type="text/css">
/*<![CDATA[*/
<!--
body, h2 {
	font-size: 14px;
}
h1 {
	font-size: 16px;
}
-->
/*]]>*/
</style>
</head>
<body>
<h1>内部服务器异常</h1>
<h2>在操作数据库时，发生异常。</h2>
<hr noshade="noshade" size="1px" />
<p>请尝试下列操作：</p>
<ul>
  <li>打开 <a href="${ctx}/">${ctx}/</a> 主页，然后查找与所需信息相关的链接。 </li>
  <li>单击<a href="javascript:location.reload(true);">刷新</a>按钮，或稍后重试。 </li>
</ul>
<p>内部服务器异常错误</p>
<hr noshade="noshade" size="1px" />
<span style="font-style:italic;"><a href="${ctx}/">返回首页</a></span>
</body>
</html>
