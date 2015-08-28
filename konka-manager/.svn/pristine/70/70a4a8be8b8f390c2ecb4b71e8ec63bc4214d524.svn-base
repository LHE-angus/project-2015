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
        <li onclick="location.href='<c:url value='/wap/center/Orders.do?method=list&orderState=2'/>';"><h3 class="ico1">已完成订单</h3><div class="clear"></div></li>
        <li onclick="location.href='<c:url value='/wap/center/Orders.do?method=list&orderState=1'/>';"><h3 class="ico2">进行中订单</h3><div class="clear"></div></li>
        <li onclick="location.href='<c:url value='/wap/center/Orders.do?method=list&orderState=3'/>';"><h3 class="ico3">已取消订单</h3><div class="clear"></div></li>
        <c:if test="${ecUser.user_type eq 1 and touch eq 1 }"> 
        <li onclick="location.href='<c:url value='/wap/center/EcPdEavl.do?' />';"><h3 class="ico4">商品评价</h3> <div class="clear"></div></li>
        <li onclick="location.href='<c:url value='/wap/center/EcUserAddrs.do?' />';"><h3 class="ico4">收货地址</h3><div class="clear"></div></li>
        <li onclick="location.href='<c:url value='/wap/center/User.do?method=view' />';"><h3 class="ico4"> 账户中心</h3><div class="clear"></div></li></c:if>
        </ul>
    <div class="clear"></div>
    </div>
    <div class="cont1c">
  		<input class="but_ping" value="退出登录" type="button" onclick="exit();">
	</div>
	<div class="clear"></div>
</div>
<script type="text/javascript">
	function exit(){
		location.href = "${ctx}/wap/login.do?method=logout";
	} 
</script>
</body>
</html>