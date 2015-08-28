<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="refresh" content="3;URL=${ctx}/" />
<title>无法找到网页</title>
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
<h1>无法找到网页</h1>
<h2>您正在搜索的网页可能已经删除、更名或暂时不可用。</h2>
<hr noshade="noshade" size="1px" />
<p>请尝试下列操作：</p>
<ul>
  <li>如果您在&ldquo;地址&rdquo;栏中键入了网页地址，请检查其拼写是否正确。</li>
  <li>打开 <a href="${ctx}/">${ctx}/</a> 主页，寻找指向所需信息的链接。 </li>
  <li>单击<a href="javascript:history.go(-1);">后退</a>按钮尝试其他链接。 </li>
</ul>
<p>HTTP 错误 404 - 找不到文件</p>
<hr noshade="noshade" size="1px" />
<span style="font-style:italic;"><a href="${ctx}/">返回首页</a>（本页面3秒后自动转到首页）</span>
</body>
</html>
