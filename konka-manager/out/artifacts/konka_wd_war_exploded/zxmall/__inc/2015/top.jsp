<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/zxmall/qqonline/css/qqonline_blue.css" /> 
<script type="text/javascript" src="${ctx}/styles/zxmall/qqonline/jquery.Sonline.js"></script>
<style type="text/css">
.app_div{position: absolute;border: solid 1px #d1e3f5;top:30px;text-align:
 center;background: #f5f4f4;left:30%;width:177px;padding-bottom: 5px;padding-top: 0px;display: none;	z-index:1000;}
.app_div div{margin-top: 0px;}
.m1{float:left;}
</style>
<div class="web-top">
	<div class="w1200">
    	<p class="top-wel"> 
    	<c:if test="${not empty zxmall}">Hi,${zxmall.user_name}！    	
    	<a href="${ctx}/zxmall/login.do?method=logout" title="退出">退出</a></c:if>
    	<c:if test="${empty zxmall}">Hi,游客！</c:if>
    	&nbsp;&nbsp;&nbsp;&nbsp;<div class="m1"><h3><strong><a href="#" id="app">手机APP下载吧</a></strong>&nbsp; </h3></div>
    	</p>
        <div class="top-nav">
        <c:if test="${empty zxmall}"><a id="top_login" href="${ctx}/zxmall/login.do">请登录</a>&nbsp;<a id="top_login_2" href="${ctx}/zxmall/RegisterNew.do">免费注册</a>&nbsp;</c:if>       
        <a href="${ctx}/zxmall/Index.do">首页</a><c:if test="${not empty zxmall}">| <a href="<c:url value='/zxmall/center/Index.do?' />">会员中心</a>|<a href="<c:url value='/zxmall/ShoppingCar.do' />">购物车<span class="tip" id="top_cart_num">0</span></a>|<a href="<c:url value='/zxmall/center/Orders.do?'/>">我的订单</a></c:if>
        |<a href="<c:url value='/zxmall/KonkaGroupPeArticleInfo.do?' />method=view&id=999999">常见问题</a>|<a href="<c:url value='/zxmall/KonkaGroupPeArticleInfo.do?' />method=view&id=888888">客户服务<span class="icon_bg"></span></a></div>
    </div>
</div>
<div class="header">
	<div class="w1200">
    	<div class="header-cont">
        	<h3 class="h-logo">
        	<!--img src="${ctx}/styles/zxmall/2015/images/klogo.png" alt="交通银行内部购机平台" height="55"  -->
        	<a href="${ctx}/zxmall/Index.do">
		     <script src="${ctx }/zxmall/KonkaGroupPeArticleInfo.do?method=jsLogInfo"></script>
		    </a>
        	</h3>
            <div class="h-search"> <form method="post" action="<c:url value='/zxmall/KonkaBcompPd.do' />" id="search_form_top"><input class="h-search-i" type="text" name="pd_sn_or_pd_name_like" id="pd_sn_or_pd_name_like" value="<c:out value='${af.map.pd_sn_or_pd_name_like}' />"><input class="h-search-btn" type="button" value="搜索" id="search_btn_sub_top"/></form></div>
            <div class="h-phone"><p><span>客服电话</span>0755-86711423（直线）</p></div>
        </div>
        <div class="header-nav">
        	<ul class="header-nav-l">
               <li class="current"><a href="<c:url value='/zxmall/Index.do' />">首页</a></li>
               <script src="${ctx }/zxmall/KonkaGroupPeArticleInfo.do?method=jsNavInfo" ></script>
            </ul>
            <div class="shopcart"><p class="icon_bg"><span class="icon_bg tips" id="cart_num">0</span><a href="<c:url value='/zxmall/ShoppingCar.do' />">去购物车结算<span class="icon_bg"></span></a></p></div>
        </div>
    </div>
</div>
<div class="app_div" id="appPanel" style="display:none;z-index:10000;position:absolute;width:350px;">
<table width="100%"  cellspacing="0" cellpadding="0" border="0" >
<tr> 
<td align="center"><h3><strong><a class="greenfont" href="http://qdgl.konka.com/files/konka/KonkaManagerAndroidInstaller_cw.apk" >Android版本</a></strong></h3></td>
<td  align="center"><h3><strong><a class="redfont" href="http://d.91.com/Soft/iPhone/com.yishangshuma.newKaNineOne-1.0-1.html">IOS版本</a></strong> </h3></td>
</tr>
<tr>
<td align="center"><img src="${ctx}/styles/zxmall/images/app_code_cw.jpg"  width="147" /> </td>
<td align="center"><img src="${ctx}/styles/zxmall/images/app_code_ios.jpg"  width="147" /> </td>
</tr>
</table> 
</div>
<script type="text/javascript" src="${ctx}/scripts/jquery.cookie.js"></script> 
<script type="text/javascript">
$(document).ready(function(){
	$(document).delegate("#search_btn_sub_top", "click", function(){
		$("#search_form_top").submit();
	}); 
	showShopCar();

	
	var weixin_flg=0;
	
	$("#app").mouseover(function(){$("#appPanel").fadeIn(500); weixin_flg=2;});
	$("#app").click(function(){$("#appPanel").fadeIn(500); weixin_flg=1;});
	 
	$(document).click(function(){
		weixin_flg++;
		if(weixin_flg>2){
			$("#wxPanel").fadeOut(500);
			$("#appPanel").fadeOut(500);
			$("#iosappPanel").fadeOut(500); 
			weixin_flg=0;
		}
	});

	//$("#app").onfocus(function(){$("#appPanel").fadeOut(500);});
});

function showShopCar(){
	$.ajax({
		type: "POST",
		url: "<c:url value='/zxmall/ShoppingCar.do' />",
		data: { "method":"ajaxGETCar", "timestamp":new Date().getTime() },
		dataType: "json",
		sync: true,
		error: function (xhr, ajaxOptions, thrownError) {},
		success: function(result) {
			var shop = result.shoppingCarTableList; 
			 var shop_pd_list_html="<ul id='mcart-sigle'>";
			 var shop_pd_all_html="";
			 var all_num=0;
			 var all_price=0.0;
			 if(shop!=null)
			 for(var i = 0; i < shop.length; i ++) {
		         if(shop[i].goods_id != goods_id) {
		        	var goods_id= shop[i].goods_id;
		        	var p_index = shop[i].p_index;
		        	var md_name=shop[i].md_name;
		        	var buy_num=shop[i].buy_num;
		        	var price=shop[i].price;
		        	var service_ids= shop[i].service_ids;
		        	var img_url= shop[i].img_url;
		        	shop_pd_list_html+="<li>";
		        	shop_pd_list_html+="<div class='p-img fl'><a href='${ctx}/zxmall/PdShow.do?goods_id="+goods_id+"' target='_blank'><img src='${ctx}/"+img_url+"' width='50' height='50' ></a></div>";
		        	shop_pd_list_html+="<div class='p-name'><a href='${ctx}/zxmall/PdShow.do?goods_id="+goods_id+"' title='"+md_name+"' target='_blank'>"+md_name+"</a></div>";
		        	shop_pd_list_html+="<div class='p-detail fr ar'><span class='p-price'><strong>￥"+price+"</strong>×"+buy_num+"</span><br><a class='delete' data-id='"+goods_id+"' data-type='RemoveProduct' href='#'>删除</a></div>";
		        	shop_pd_list_html+="</li>";
		        	all_num+=parseInt(buy_num);
		        	all_price+=parseFloat(price)*parseInt(buy_num);
		        	
		         } 
		      }
			
			 shop_pd_list_html+="</ul>";
			 shop_pd_all_html= "共<b>"+all_num+"</b>件商品　共计<strong>￥"+all_price+"</strong>";
			 //document.getElementById("shop_pd_list").innerHTML=shop_pd_list_html;
			 //document.getElementById("shop_pd_all").innerHTML=shop_pd_all_html;
			 if( document.getElementById("top_cart_num")){ 
				 document.getElementById("top_cart_num").innerHTML=""+all_num;
			 }
			 document.getElementById("cart_num").innerHTML=""+all_num;
		}
	}); 
}

$(function(){$("body").Sonline({
	Position:"right",Top:300,Effect:true,DefaultsOpen:false,
	Qqlist:"1120919532|客服01,1752410003|客服02"
});})
</script>