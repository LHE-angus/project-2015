<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/member/css/index.css" />  
<link rel="stylesheet" type="text/css" href="${ctx}/styles/member/css/pulldown.css">
<div class="nav" >
	<div id="allclass">
			<dl class="">
				<dt class="ld"><a href="http://epp.konka.com/member/Index.do#">全部商品分类</a></dt>
				<dd><div class="category_l">
                <ul>
             	  <li><h2 class="pingban"><a href="<c:url value='/member/KonkaBcompPd.do?prod_type=0' />">平板电视</a></h2></li>
             	  <li class="mod_cate"><h2 class="sheng"><a href="<c:url value='/member/KonkaBcompPd.do?prod_type=3' />">生活电器</a></h2></li>
			      <li><h2 class="bingx"><a href="<c:url value='/member/KonkaBcompPd.do?prod_type=4' />">冰箱</a>  <a href="<c:url value='/member/KonkaBcompPd.do?prod_type=5' />">洗衣机</a> </h2></li>
			      <!--li class="mod_cate"><h2 class="kongtiao"><a href="#">空 调</a></h2></li  -->
			      <li class="mod_cate"><h2 class="sj"><a href="<c:url value='/member/KonkaBcompPd.do?prod_type=7' />">手 机</a>  <a href="<c:url value='/member/KonkaBcompPd.do?prod_type=9' />">电脑</a>  <a href="<c:url value='/member/KonkaBcompPd.do?prod_type=22' />">游戏设备</a></h2></li>
			      <li class="mod_cate"><h2 class="spyl"><a href="<c:url value='/member/KonkaBcompPd.do?prod_type=21' />">食品饮料</a></h2></li>
               	  <li class="mod_cate"><h2 class="peijian"><a href="<c:url value='/member/KonkaBcompPd.do?prod_type=10' />">配件专区</a></h2></li>
                </ul>
              </div></dd>
			</dl>
	</div>
    <div class="nav_r">
    	<ul>
        	<li><a href="<c:url value='/member/Index.do' />">首页</a></li>          
            <li><a href="<c:url value='/member/TvPd.do' />">电视商城</a></li>
            <!-- li><a href="<c:url value='/member/KonkaBcompPd.do?prod_type=4' />">冰箱</a></li>
            <li><a href="<c:url value='/member/KonkaBcompPd.do?prod_type=5' />">洗衣机</a></li>
            <li><a href="<c:url value='/member/KonkaBcompPd.do?prod_type=6' />">空调</a></li>
            <li><a href="<c:url value='/member/KonkaBcompPd.do?prod_type=3' />">生活电器</a></li>
            <li><a href="<c:url value='/member/Ybfw.do' />">延保服务</a></li-->
            <!-- <li><a href="<c:url value='/member/Dzcg.do' />">大宗采购</a></li> -->
            <li><a href="<c:url value='/member/Hdzt.do?mod_id=915703&id=869820' />">戴尔专区</a></li>
            <!--li><a href="<c:url value='/member/Lucky.do?method=view&lucky_id=227790' />">双11抢红包</a></li>
            <li><a href="<c:url value='/member/PshowOrderPanicBuying.do' />">双11特价抢购</a></li--> 
            <c:if test="${ecUser.user_type eq 1 and touch eq 1 }"><li><a href="<c:url value='/member/EcGift.do' />">积分商城</a></li></c:if>
            <li><a href="<c:url value='/member/Hdzt.do?mod_id=915703&id=889505' />">韩国现代</a></li> 
           <!-- <li><a href="<c:url value='/member/PdShowYd.do?goods_id=100031423' />">开心猫团购</a></li>  -->
        	<!--li><a href="<c:url value='/member/YjPd.do' />">样机特区</a></li>
            <li><a href="<c:url value='/member/Hdzt.do?mod_id=915703&id=833285' />">电信专区</a></li> 
       		<li><a href="<c:url value='/member/Auction.do' />">0元抢拍</a></li>
       		<li><a href="<c:url value='/member/Hdzt.do?mod_id=915703&id=857515' />">特惠套购</a></li-->
        </ul>
      <div style="float:right;width:220px;margin-right:11px;margin-top:8px;">
    	<div id="my360buy-2013">
			<dl class="" load="1">
				<dt class="ld"><a href="<c:url value='/member/center/Index.do' />">我的开心猫</a></dt>
				<dd>
                <div class="prompt">
                <span class="fl"><strong> </strong></span>
                <span class="fr"><a href="<c:url value='/member/center/Index.do' />">去我的开心猫首页&nbsp;&gt;</a></span>
                </div>
                <div id="jduc-orderlist">
                	<div class="orderlist">
                    <div class="smt"><h4>最新订单状态：</h4><div class="extra"><a href="<c:url value='/member/center/Orders.do' />" target="_blank">查看所有订单&nbsp;&gt;</a></div><div class="clear"></div></div>                	</div>
                 </div>
                 <div class="uclist"><c:if test="${touch eq 1 }">
                 <ul class="fore1 fl">
                 <li><a target="_blank" href="<c:url value='/member/center/Orders.do' />">待处理订单<span id="num-unfinishedorder"><font style="color:#ccc">(0)</font></span></a></li>              
               	 <li><a target="_blank" href="<c:url value='/member/center/EcQaInfo.do'/>" >咨询回复<span id="num-consultation"><font style="color:#ccc">(0)</font></span></a></li>  
                 <li><a target="_blank" href="<c:url value='/member/center/EcVouchers.do' />">购 物 券<span id="num-ticket"><font style="color:#c00">(0)</font></span></a></li>
                 <li> </li>
                 </ul>                
                 <ul class="fore2 fl"> 
                 <li><a target="_blank" href="<c:url value='/member/center/Orders.do' />">我的订单&nbsp;&gt;</a></li>
                 <li><a target="_blank" href="<c:url value='/member/center/EcUserFavotrites.do' />">我的关注&nbsp;&gt;</a></li>
                 <li><a target="_blank" href="<c:url value='/member/center/EcUserAddrs.do' />">地 址 簿&nbsp;&gt;</a></li>
                 <li> </li>
                 </ul></c:if><c:if test="${touch ne 1 }">
                 <ul class="fore1 fl">
                 <li><a target="_blank" href="<c:url value='/member/center/Orders.do' />">待处理订单<span id="num-unfinishedorder"><font style="color:#ccc">(0)</font></span></a></li>            
               	 <li><a target="_blank" href="<c:url value='/member/center/EcUserFavotrites.do' />">我的关注&nbsp;&gt;</a></li>
               	 <li> </li>
                 </ul>                
                 <ul class="fore2 fl"> 
                 <li><a target="_blank" href="<c:url value='/member/center/Orders.do' />">我的订单&nbsp;&gt;</a></li>
                 <li> </li>
                 <li> </li>
                 </ul></c:if>
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
                    <div class="smb ar"><span id="shop_pd_all">共<b>0</b>件商品　共计<strong>￥0.00</strong></span><br><a href="<c:url value='/member/ShoppingCar.do' />" title="去购物车结算" id="btn-payforgoods">去购物车结算</a></div>
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
		location.href = "<c:url value='/member/ShoppingCar.do' />";
	}); 

	showMyEpp();
	$("#my360buy-2013").hover(function(){ 
		$("dl",this).addClass("hover");
		},
	function(){ 
		$("dl",this).removeClass("hover");		
	});
	$("#settleup-2013").hover(function(){ 
			$("dl",this).addClass("hover");
			showShopCar();		
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
	// 删除购物车
	$(document).delegate(".delete", "click", function(){
		if(!confirm("确定删除？")) return false;
		var goods_id = $(this).attr("data-id");
		// 处理COOKIE中的值
		try {
		   var value = "["; 
		   var SHOPING_CAR_COOKIE = $.cookie("SHOPING_CAR_COOKIE"); 
		   var cookie_count = 0; // 记录cookie中的购物车商品数
		   if(null != SHOPING_CAR_COOKIE && "" != SHOPING_CAR_COOKIE) {
		      var json = eval("(" + SHOPING_CAR_COOKIE + ")"); 
		      cookie_count = json.length;
		      for(var i = 0; i < json.length; i ++) {
		         if(json[i].goods_id != goods_id) {
		        	 value += "{\"goods_id\":\"" + json[i].goods_id + "\",\"p_index\":\"" + json[i].p_index + "\",\"md_name\":\"" + json[i].md_name + "\",\"buy_num\":\"" + json[i].buy_num + "\",\"price\":\"" + json[i].price + "\",\"service_ids\":\"" + json[i].service_ids + "\",\"img_url\":\"" + json[i].img_url + "\"},"
		         } 
		      }
		   }
		   if(cookie_count > 1){
			   value = value.substring(0, value.length - 1) + "]"; 
		   } else{
			   value += "]"; 
		   }
		   
		   // 将处理过后的数据存入COOKIE
		   $.cookie("SHOPING_CAR_COOKIE", value, { expires : 30 }); 
		}catch(e) { alert("提示，请启用浏览器Cookie.");}
		
		// Ajax后台删除数据
		$.ajax({
			type: "POST",
			url: "<c:url value='/member/ShoppingCar.do' />",
			data: { "method":"ajaxDelCar", "goods_id":goods_id, "timestamp":new Date().getTime() },
			dataType: "json",
			sync: true,
			//error: function (xhr, ajaxOptions, thrownError) { alert("数据加载请求失败【" + xhr.statusText + "," + xhr.responseText + "," + xhr.status + "," + thrownError +"】！"); },
			success: function(result) {
				if(result.status == '0'){
					alert(status.msg);
				}else{
				 alert("提示，删除成功！");
				}
			}
		}); 
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
	        	shop_pd_list_html+="<div class='p-img fl'><a href='${ctx}/member/PdShow.do?goods_id="+goods_id+"' target='_blank'><img src='${ctx}/"+img_url+"' width='50' height='50' ></a></div>";
	        	shop_pd_list_html+="<div class='p-name'><a href='${ctx}/member/PdShow.do?goods_id="+goods_id+"' title='"+md_name+"' target='_blank'>"+md_name+"</a></div>";
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

function showMyEpp(){
	$.ajax({
		type: "POST",
		url: "<c:url value='/member/center/Index.do' />",
		data: { "method":"getAjaxMyEpp", "timestamp":new Date().getTime() },
		dataType: "json",
		sync: true,
		//error: function (xhr, ajaxOptions, thrownError) { alert("数据加载请求失败【" + xhr.statusText + "," + xhr.responseText + "," + xhr.status + "," + thrownError +"】！"); },
		success: function(result) {
			if(result.status == '1'){
				$("#num-unfinishedorder").html("<font style='color:#c00'>("+result.orderNum+")</font>"); 
				 $("#num-consultation").html("<font style='color:#c00'>("+result.qaCount+")</font>"); 
				 $("#num-ticket").html("<font style='color:#c00'>("+result.ecvCount+")</font>");
				 
			} 
		}
	}); 
}
</script>