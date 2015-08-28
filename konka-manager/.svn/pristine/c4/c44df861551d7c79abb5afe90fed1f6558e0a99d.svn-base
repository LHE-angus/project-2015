<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>零售量分析</title>
<link rel="stylesheet" href="${ctx}/webservice/KonkaMobileDateReport/css/base.css">
</head>
<body>
<!-- <header class="navbar layout_box box_v_c"> -->
<%-- 	<div class="navbar-left"><span class="backpic"><img src="${ctx}/webservice/KonkaMobileDateReport/images/topback.png"></span></div> --%>
<!--     <div class="navbar-center"><span class="red2 ft30">（日/月/季/年）零售量分析</span></div> -->
<!-- </header> -->
<article class="main-sjfx">
	<ul class="cont-nav-list2 qhNavList">
		<li class="current">分公司</li>
		<li>按渠道</li>
<!-- 		<li>按类别</li> -->
		<li>按尺寸</li>
<!-- 		<li>按客户</li> -->
		<li>...</li>
	</ul>
    <div class="sjfx-cont qhContList">
    	<iframe id="mainFrame" src="${ctx}/webservice/KonkaMobileDateReport.do?method=list&type=0&start_time=${start_time}&end_time=${end_time}&user_id=${user_id}&userpass=${userpass}&dept_id=${dept_id}" width="100%"></iframe>
    </div>
</article>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/scripts/print.js"></script> 
<script type="text/javascript">
$(".qhNavList li").on("click",function(){
	$(this).addClass("current").siblings(".current").removeClass("current");
	//alert($(this).index());
	if($(this).index() != 3){
	$("#mainFrame").attr("src","${ctx}/webservice/KonkaMobileDateReport.do?method=list&type="+$(this).index()+"&start_time=${start_time}&end_time=${end_time}&user_id=${user_id}&userpass=${userpass}&dept_id=${dept_id}");
	}
	//$(".qhContList").children(":eq("+$(this).index()+")").css("display","block").siblings().css("display","none");
});
</script>
</body>
</html>