<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" /> 
<title>开心猫</title> 
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/global.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/epp_index.css" /> 
</head>
<body><div class="top_class"><span class="lspan"><a href="javascript:void(0);history.back();"><img src="${ctx}/styles/epp/mobile/images/return.gif" /></a></span><h3>分类</h3>
	<a href="javascript:void(0);showNav();" id="btnJdkey" class="new-a-jd"><span >开心猫</span></a>
	<div class="new-jd-tab" style="top:45px;display:none;" id="jdkey">
	<div class="new-tbl-type">
	<a href="<c:url value='/epp/mobile/Index.do' />" class="new-tbl-cell"><span class="icon">首页</span><p style="color:#6e6e6e;">首页</p>	</a>
	<a href="<c:url value='/epp/mobile/ProdType.do' />" class="new-tbl-cell"><span class="icon2">产品分类</span><p style="color:#6e6e6e;">产品分类</p></a>
	<a href="<c:url value='/epp/mobile/ShoppingCar.do' />" id="html5_cart" class="new-tbl-cell"><span class="icon3 on">购物车</span><p style="color:#6e6e6e;" class="on">购物车</p></a>
	<a href="<c:url value='/epp/mobile/center/Index.do' />" class="new-tbl-cell"><span class="icon4">会员中心</span> <p style="color:#6e6e6e;">会员中心</p></a>
	</div>
	</div>
</div>
<div id="content">
<div class="mainbox">
	<div class="maincont">
    	<ul class="classul">
        <li><a href="<c:url value='/epp/mobile/KonkaBcompPd.do?prod_type=0'/>"><div class="lclass_pic" ><img alt="液晶电视" src="${ctx}/styles/epp/mobile/images/class_1.gif" /></div><div class="rclass_tit"><h3>液晶电视</h3><p>4K电视/智能电视/3D电视</p></div></a><div class="clear"></div></li>
        <li><a href="<c:url value='/epp/mobile/KonkaBcompPd.do?prod_type=4'/>"><div class="lclass_pic" ><img alt="冰箱" src="${ctx}/styles/epp/mobile/images/class_2.gif" /></div><div class="rclass_tit"><h3>冰箱</h3><p>冰箱</p></div></a><div class="clear"></div></li>
        <li><a href="<c:url value='/epp/mobile/KonkaBcompPd.do?prod_type=5'/>"><div class="lclass_pic" ><img alt="洗衣机" src="${ctx}/styles/epp/mobile/images/class_6.gif" /></div><div class="rclass_tit"><h3>洗衣机</h3><p>洗衣机</p></div></a><div class="clear"></div></li>
        <li><a href="<c:url value='/epp/mobile/KonkaBcompPd.do?prod_type=3'/>"><div class="lclass_pic" ><img alt="生活电器" src="${ctx}/styles/epp/mobile/images/class_3.gif" /></div><div class="rclass_tit"><h3>生活电器</h3><p>电饭锅/电磁炉</p></div></a><div class="clear"></div></li>
        <li><a href="<c:url value='/epp/mobile/KonkaBcompPd.do?prod_type=10'/>"><div class="lclass_pic" ><img alt="配件专区" src="${ctx}/styles/epp/mobile/images/class_7.gif" /></div><div class="rclass_tit"><h3>配件专区</h3><p>电视挂架</p></div></a><div class="clear"></div></li>
        </ul>
   		<div class="clear"></div>
    </div>
	<div class="clear"></div>
</div>
</div>
<script type="text/javascript">//<![CDATA[
function showNav(){  
	if(document.getElementById("jdkey").style.display=='none'){ 
		document.getElementById("jdkey").style.display='block';
	}else{
		document.getElementById("jdkey").style.display='none';
	} 
}
//]]>
</script>
</body>
</html>
