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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script>
</head>
<body>
<div class="top_class"><span class="lspan"><a href="<c:url value='/epp/mobile/Index.do?'/>"><img style="margin-left:-5px;margin-top:-10px;" alt="开心猫" src="${ctx}/styles/epp/mobile/images/wap_logo2.gif" width="40" height="39" /></a></span><h3>会员中心</h3><a href="javascript:void(0);showNav();" id="btnJdkey" class="new-a-jd"><span>开心猫</span></a><div class="new-jd-tab" style="top:45px;display:none;" id="jdkey">
	<div class="new-tbl-type">
	<a href="<c:url value='/epp/mobile/Index.do' />" class="new-tbl-cell"><span class="icon">首页</span><p style="color:#6e6e6e;">首页</p>	</a>
	<a href="<c:url value='/epp/mobile/ProdType.do' />" class="new-tbl-cell"><span class="icon2">产品分类</span><p style="color:#6e6e6e;">产品分类</p></a>
	<a href="<c:url value='/epp/mobile/ShoppingCar.do' />" id="html5_cart" class="new-tbl-cell"><span class="icon3 on">购物车</span><p style="color:#6e6e6e;" class="on">购物车</p></a>
	<a href="<c:url value='/epp/mobile/center/Index.do' />" class="new-tbl-cell"><span class="icon4">会员中心</span> <p style="color:#6e6e6e;">会员中心</p></a>
	</div>
	</div>
</div>
<div id="content">
	<div class="membertab1">
	<table style="width:100%">
    <tbody>
    <tr>
    <td width="40%" rowspan="2" align="center" valign="middle"><img src="${ctx}/styles/epp/mobile/images/mem_photo.png" width="129" height="106" /></td>
    <td><h3 class="membertit"> ${ecUser.real_name }</h3></td>
    </tr>
    <tr>
      <td><h3 class="membertit">${ecUser.ecBaseCardLevel.card_level_name}</h3></td>
    </tr>
    </tbody>
    </table>
</div>
<div class="mainbox">
	<div class="maincont2">
    	<ul class="memberul">
        <li onclick="location.href='<c:url value='/epp/mobile/center/Orders.do?method=list&orderState=2'/>';"><h3 class="ico1">已完成订单</h3><div class="clear"></div></li>
        <li onclick="location.href='<c:url value='/epp/mobile/center/Orders.do?method=list&orderState=1'/>';"><h3 class="ico2">进行中订单</h3><div class="clear"></div></li>
        <li onclick="location.href='<c:url value='/epp/mobile/center/Orders.do?method=list&orderState=3'/>';"><h3 class="ico3">已取消订单</h3><div class="clear"></div></li>
        <c:if test="${ecUser.user_type eq 1 and touch eq 1 }"> 
        <li onclick="location.href='<c:url value='/epp/mobile/center/EcPdEavl.do?' />';"><h3 class="ico4">商品评价</h3> <div class="clear"></div></li>
        <li onclick="location.href='<c:url value='/epp/mobile/center/EcUserAddrs.do?' />';"><h3 class="ico4">收货地址</h3><div class="clear"></div></li>
        <li onclick="location.href='<c:url value='/epp/mobile/center/User.do?method=view' />';"><h3 class="ico4"> 账户中心</h3><div class="clear"></div></li></c:if>
        </ul>
    <div class="clear"></div>
    </div>
    <div class="cont1c">
  		<input class="but_ping" value="退出登录" type="button" onclick="exit();">
	</div>
	<div class="clear"></div>
</div>
</div>
<script type="text/javascript">
	function exit(){
		location.href = "${ctx}/epp/mobile/login.do?method=logout";
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