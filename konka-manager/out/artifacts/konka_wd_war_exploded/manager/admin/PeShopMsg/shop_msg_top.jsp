<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<!-- ****** Main Frame Begin ****** -->
<div class="rtabcont1"><a class="li"  href="javascript:changeTo('a')" id="a">收件箱</a>&nbsp;|&nbsp;<a id="b" class="li"  href="javascript:changeTo('b')">发件箱</a>&nbsp;|&nbsp;<a id="c" class="li"  href="javascript:changeTo('c')">草稿箱</a>&nbsp;|&nbsp;<a id="d" class="li"  href="javascript:changeTo('d')">发送短消息</a>
  <!-- ****** Main Frame End ****** -->
</div>
<script type="text/javascript">//<![CDATA[
window.onload = function(){
	changeStyle();
}
function changeStyle(){
	document.getElementById("${tag_id}").style.fontSize = "14px";
	document.getElementById("${tag_id}").style.fontWeight = "bold";
}
// $(document).ready(function(){
//	I8右宽度不能自动适应加载，IE7,FireFox都可以的
// 	$(".frame_right").width($(window).width() - 158);
// });
function changeTo(tag){
	var path = "";
	switch(tag){
 	case 'a':
		path="${ctx}/manager/admin/PeShopMsg.do?method=list";
	break; 
	case 'b':
		path="${ctx}/manager/admin/PeShopMsg.do?method=listOut";
	break;
	case 'c':
		path="${ctx}/manager/admin/PeShopMsg.do?method=listDraft";
	break;
	case 'd':
		path="${ctx}/manager/admin/PeShopMsg.do?method=editMsg";
	break;
	}
	
	window.location.href = path+"&tag_id="+tag+"&mod_id=${af.map.mod_id}&tree_param=${tree_param}&receive_user_type=${af.map.receive_user_type}&public_target=${af.map.public_target}";
};
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
