<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/sfmall/css/index.css" />  
<link rel="stylesheet" type="text/css" href="${ctx}/styles/sfmall/css/pulldown.css">
<div class="nav" >
	<div id="allclass">
			<dl class="">
				<dt class="ld"><a href="http://epp.konka.com/sfmall/Index.do#">全部商品分类</a></dt>
				<dd><div class="category_l">
                <ul>
                <li><h2 class="dianshi"><a href="#">品牌</a></h2><p class="mod_cate_r"><a href="<c:url value='/sfmall/KonkaBcompPd.do?brand_name=KONKA' />">KONKA</a><a href="<c:url value='/sfmall/KonkaBcompPd.do?brand_name=KKTV' />">KKTV</a><a href="<c:url value='/sfmall/KonkaBcompPd.do?brand_name=HYUNDAI' />">HYUNDAI</a></p><div class="clear"></div></li>
        		<li><h2 class="pingban"><a href="#">品类</a></h2><p class="mod_cate_r">
        		<a href="<c:url value='/sfmall/KonkaBcompPd.do?pd_type=2' />">智能电视</a>
	            <a href="<c:url value='/sfmall/KonkaBcompPd.do?pd_type=1' />">3D电视</a>
	            <a href="<c:url value='/sfmall/KonkaBcompPd.do?pd_type=4' />">4K电视</a></p><div class="clear"></div>
	            </li>
                <li><h2 class="peijian"><a href="#">配件专区</a></h2><p class="mod_cate_r"><a href="#">电视挂架</a><a href="#">影音线材</a><a href="#">遥控器</a></p><div class="clear"></div></li>
                </ul>
              </div></dd>
			</dl>
	</div>
    <div class="nav_r">
    	<ul>
        	<li><a href="<c:url value='/sfmall/Index.do' />">首页</a></li>
            <li><a href="<c:url value='/sfmall/KonkaBcompPd.do?prod_type=0' />">电视</a></li>
            <li><a href="<c:url value='/sfmall/KonkaBcompPd.do?prod_type=3' />">生活电器</a></li>
            <li><a href="<c:url value='/sfmall/KonkaBcompPd.do?prod_type=4' />">冰箱</a></li>
            <li><a href="<c:url value='/sfmall/KonkaBcompPd.do?prod_type=5' />">洗衣机</a></li>
        </ul>
      <div style="float:right;width:220px;margin-right:11px;margin-top:8px;">
    	<div id="my360buy-2013">
			<dl class="" load="1">
				<dt class="ld"><a href="<c:url value='/sfmall/center/Index.do' />">我的开心猫</a></dt>
				<dd>
                <div class="prompt">
                <span class="fl"><strong> </strong></span>
                <span class="fr"><a href="<c:url value='/sfmall/center/Index.do' />">去我的开心猫首页&nbsp;&gt;</a></span>
                </div>
                <div id="jduc-orderlist">
                	<div class="orderlist">
                    <div class="smt"><h4>最新订单状态：</h4><div class="extra"><a href="<c:url value='/sfmall/center/Orders.do' />" target="_blank">查看所有订单&nbsp;&gt;</a></div><div class="clear"></div></div>                	</div>
                 </div>
                  <div class="uclist"> 
                 <ul class="fore1 fl">
	                 <li><a target="_blank" href="<c:url value='/sfmall/center/Orders.do' />">待处理订单<span id="num-unfinishedorder"><font style="color:#ccc">(0)</font></span></a></li>            
	               	 <li><a target="_blank" href="<c:url value='/sfmall/center/EcUserFavotrites.do' />">我的关注&nbsp;&gt;</a></li>
	               	 <li> </li>
                 </ul>                
                 <ul class="fore2 fl"> 
	                 <li><a target="_blank" href="<c:url value='/sfmall/center/Orders.do' />">我的订单&nbsp;&gt;</a></li>
	                 <li> </li>
	                 <li> </li>
                 </ul> 
                 </div>
                  <div class="viewlist"></div>
               </dd>
			</dl>
		</div>
        <!--mybuy end-->
    	<div id="settleup-2013">
			<dl class="">
				<dt class="ld"><a href="#" id="go_shopping_car_btn">去购物车结算</a></dt>
				<dd>
                <div id="settleup-content">
                	<div class="smt"><h4 class="fl">最新加入的商品</h4></div>
                    <div class="smc" id="shop_pd_list"></div>
                    <div class="smb ar"><span id="shop_pd_all">共<b>0</b>件商品　共计<strong>￥0.00</strong></span><br><a href="<c:url value='/sfmall/ShoppingCar.do' />" title="去购物车结算" id="btn-payforgoods">去购物车结算</a></div>
                  </div>
                </dd>
		  	</dl>
		 </div>
    </div> 
   </div>
</div>
<script type="text/javascript" src="${ctx}/scripts/jquery.cookie.js"></script> 
<script type="text/javascript">
$(document).ready(function(){
	// 去结算
	$(document).delegate("#go_shopping_car_btn", "click", function(){
		location.href = "<c:url value='/sfmall/ShoppingCar.do' />";
	});  
	$("#my360buy-2013").hover(function(){ 
		$("dl",this).addClass("hover");
		},
	function(){ 
		$("dl",this).removeClass("hover");		
	});
	$("#settleup-2013").hover(function(){ 
			$("dl",this).addClass("hover"); 
			},
	function(){ 
		$("dl",this).removeClass("hover");	
	});
	$("#allclass").hover(function(){ 
			$("dl",this).addClass("hover");
			},
	function(){ 
		$("dl",this).removeClass("hover");	
	});   
});

function showShopCar(){
	// 购物车数量
	var SHOPING_CAR_COOKIE = $.cookie("SHOPING_CAR_COOKIE");   
	
	if(null != SHOPING_CAR_COOKIE && "" != SHOPING_CAR_COOKIE){
		var shop = eval("(" + SHOPING_CAR_COOKIE + ")"); 
		 var shop_pd_list_html="<ul id='mcart-sigle'>";
		 var shop_pd_all_html="";
		 var all_num=0;
		 var all_price=0.0;
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
	        	shop_pd_list_html+="<div class='p-img fl'><a href='${ctx}/sfmall/PdShow.do?goods_id="+goods_id+"' target='_blank'><img src='${ctx}/"+img_url+"' width='50' height='50' ></a></div>";
	        	shop_pd_list_html+="<div class='p-name'><a href='${ctx}/sfmall/PdShow.do?goods_id="+goods_id+"' title='"+md_name+"' target='_blank'>"+md_name+"</a></div>";
	        	shop_pd_list_html+="<div class='p-detail fr ar'><span class='p-price'><strong>￥"+price+"</strong>×"+buy_num+"</span><br><a class='delete' data-id='"+goods_id+"' data-type='RemoveProduct' href='#'>删除</a></div>";
	        	shop_pd_list_html+="</li>";
	        	all_num+=parseInt(buy_num);
	        	all_price+=parseFloat(price)*parseInt(buy_num);
	        	
	         } 
	      }
		
		 shop_pd_list_html+="</ul>";
		 shop_pd_all_html= "共<b>"+all_num+"</b>件商品　共计<strong>￥"+all_price+"</strong>";
		 document.getElementById("shop_pd_list").innerHTML=shop_pd_list_html;
		 document.getElementById("shop_pd_all").innerHTML=shop_pd_all_html;
	} 	
}

function showMyEpp(){<c:if test="${not empty ecUser}">
	$.ajax({
		type: "POST",
		url: "<c:url value='/sfmall/center/Index.do' />",
		data: { "method":"getAjaxMyEpp", "timestamp":new Date().getTime() },
		dataType: "json",
		sync: true,
		error: function (xhr, ajaxOptions, thrownError) { alert("数据加载请求失败【" + xhr.statusText + "," + xhr.responseText + "," + xhr.status + "," + thrownError +"】！"); },
		success: function(result) {
			if(result.status == '1'){
				$("#num-unfinishedorder").html("<font style='color:#c00'>("+result.orderNum+")</font>"); 
				 $("#num-consultation").html("<font style='color:#c00'>("+result.qaCount+")</font>"); 
				 $("#num-ticket").html("<font style='color:#c00'>("+result.ecvCount+")</font>");
				 
			} 
		}
	}); </c:if>
}
</script>