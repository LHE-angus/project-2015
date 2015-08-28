<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=0.67, maximum-scale=1.0, user-scalable=0"/>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/global.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/epp_index.css" /> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script>
<title>触网</title> 
<style>
img,object {width: 100%;}
</style>
</head>
<body><div class="top_class"><span class="lspan"><a href="javascript:void(0);history.back();"><img src="${ctx}/styles/epp/mobile/images/return.gif" /></a></span><h3>商品详情</h3><a href="javascript:void(0);showNav();" id="btnJdkey" class="new-a-jd"><span>触网</span></a>
	<div class="new-jd-tab" style="top:45px;display:none;" id="jdkey">
	<div class="topsearch">
		<form method="post" action="<c:url value='/webservice/wap/KonkaBcompPd.do' />" id="search_form_top">
		    <table style="width:100%">
			    <tr>
			    <td><div class="searcharea"><input name="pd_sn_or_pd_name_like" id="pd_sn_or_pd_name_like" class="input_search" type="text" maxlength="20" value="搜索商品" onfocus="if(value=='搜索商品') {value='';}" onblur="if (value=='') {value='搜索商品';}"/></div></td>
			    <td class="padl5" width="60">
			    <a href="javascript:void(0);" id="search_btn_sub_top" class="new-tbl-cell"><span class="icon2">搜索</span></a>
			    <!-- a href="javascript:void(0);showNav();" id="btnJdkey" class="new-a-jd"  style="margin-top:0;" ><span>触网</span></a -->
			    </td>
			    </tr>
		    </table>
	    </form> 
    </div>	
	<div class="new-tbl-type">
	<a href="<c:url value='/webservice/wap/Index.do' />" class="new-tbl-cell"><span class="icon">首页</span><p style="color:#6e6e6e;">首页</p>	</a>
	<a href="<c:url value='/webservice/wap/ProdType.do' />" class="new-tbl-cell"><span class="icon2">产品分类</span><p style="color:#6e6e6e;">产品分类</p></a>
	<a href="<c:url value='/webservice/wap/ShoppingCar.do' />" id="html5_cart" class="new-tbl-cell"><span class="icon3 on">购物车</span><p style="color:#6e6e6e;" class="on">购物车</p></a>
	<a href="<c:url value='/webservice/wap/center/Index.do' />" class="new-tbl-cell"><span class="icon4">会员中心</span> <p style="color:#6e6e6e;">会员中心</p></a>
	</div>
	</div>
</div>
<div id="content">
<div class="mainbox">
<div class="maincont"> 
<c:out value="${kbpc.content}" escapeXml="false" /> 
</div>
</div>
</div>  
</body>
<script type="text/javascript">//<![CDATA[  
$(document).ready(function(){
	$(document).delegate("#search_btn_sub_top", "click", function(){
		if($("#pd_sn_or_pd_name_like").val()=='搜索商品'){
			$("#pd_sn_or_pd_name_like").val('');
		}
		$("#search_form_top").submit();
	});
});	

function showNav(){
	if(document.getElementById("jdkey").style.display=='none'){
		document.getElementById("jdkey").style.display='block';
	}else{
		document.getElementById("jdkey").style.display='none';
	} 
}
//]]>
</script> 
</html>