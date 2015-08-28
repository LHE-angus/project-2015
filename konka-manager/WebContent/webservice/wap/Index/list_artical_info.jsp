<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" /> 
<title>触网</title> 
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/global.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/epp_index.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/lrtk.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="${ctx}/styles/epp/mobile/js/pptBox.js"></script> 
<style type="text/css">
.menutab li{
height:60px;
}
.menutab li img{
padding: 5px 0px;
}
</style>
</head>
<body onclick="document.getElementById('search_top').class='top_class';">

  <div>
 	${ecArticleInfo.content} 
 </div>
  

<!--first end-->
<script type="text/javascript">
$(document).ready(function(){
	$(document).delegate("#search_btn_sub_top", "click", function(){
		if($("#pd_sn_or_pd_name_like").val()=='搜索商品'){
			$("#pd_sn_or_pd_name_like").val('');
		}
		$("#search_form_top").submit();
	});
});	

function exit(){
	location.href = "${ctx}/webservice/wap/login.do?method=logout";
} 
function showNav(){  
	if(document.getElementById("jdkey").style.display=='none'){ 
		document.getElementById("jdkey").style.display='block';
	}else{
		document.getElementById("jdkey").style.display='none';
	} 
}
</script>
</body>
</html>