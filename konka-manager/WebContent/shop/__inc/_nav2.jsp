	
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<div class="logo">
  <div class="logo1">
    <div class="logo-l" id="navlist"  >
      <ul id="topnav">
        <li class="logo1-01"><a href="<c:url value='/shop/Index.do' />"><label class="l1"></label>首  页</a></li>
        <li class="logo1-02"><a href="<c:url value='/shop/KonkaBcompPd.do' />"><label class="l2"></label>康佳电视</a></li><c:if test="${ecUser.user_type eq 1 }">
        <!-- li class="logo1-03"><a href="<c:url value='/shop/EcGift.do' />"><label class="l3"></label>积分商城</a></li --></c:if>
        <!-- li class="logo1-04"><a href="<c:url value='/shop/PshowOrderPanicBuying.do' />"><label class="l4"></label>限时抢购</a></li -->
        <li class="logo1-05"><a href="<c:url value='/shop/Ybfw.do' />"><label class="l5"></label>延保服务</a></li>
        <li class="logo1-06" ><a href="<c:url value='/shop/Dzcg.do' />"><label class="l6"></label>企业大宗采购</a></li>
        <li class="logo1-07" id="show_all_div">
        <a href="<c:url value='/shop/KonkaBcompPd.do' />"><label class="l7"></label>全部产品</a>      
           <div class="sub" style="opacity:0; z-index:999999;">
            <ul>
              <li>
                <h2><a style="cursor:pointer;">商品分类</a></h2>
                <div class="submenu">
                	<dl>
                    	<dt><a href="<c:url value='/shop/KonkaBcompPd.do?' />">康佳电视</a></dt>
                        <dd style="text-align:center"><a href="<c:url value='/shop/KonkaBcompPd.do?pd_type=4' />">4K电视</a></dd>
                        <dd style="text-align:center"><a href="<c:url value='/shop/KonkaBcompPd.do?pd_type=1' />">3D电视</a></dd>
                        <dd style="text-align:center"><a href="<c:url value='/shop/KonkaBcompPd.do?pd_type=2' />">智能电视</a></dd>
                        <dd ><a href="<c:url value='/shop/KonkaBcompPd.do?pd_size_type=31' />" style="font-size:9px;width:67px">32英寸以下</a> </dd>
                        <dd style="text-align:center"><a href="<c:url value='/shop/KonkaBcompPd.do?pd_size_type=32' />" style="font-size:9px;">32英寸</a> </dd>
		                <dd style="text-align:center"><a href="<c:url value='/shop/KonkaBcompPd.do?pd_size_type=37' />" style="font-size:9px;">37英寸</a></dd>
		                <dd style="text-align:center"><a href="<c:url value='/shop/KonkaBcompPd.do?pd_size_type=39' />" style="font-size:9px;">39英寸</a></dd>
		                <dd style="text-align:center"><a href="<c:url value='/shop/KonkaBcompPd.do?pd_size_type=42' />" style="font-size:9px;">42英寸</a></dd>
		                <dd style="text-align:center"><a href="<c:url value='/shop/KonkaBcompPd.do?pd_size_type=47' />" style="font-size:9px;">47英寸</a></dd>
		                <dd style="text-align:center"><a href="<c:url value='/shop/KonkaBcompPd.do?pd_size_type=50' />" style="font-size:9px;">50英寸</a></dd>
		                <dd style="text-align:center"><a href="<c:url value='/shop/KonkaBcompPd.do?pd_size_type=55' />" style="font-size:9px;">55英寸</a></dd>
		                <dd style="text-align:center"><a href="<c:url value='/shop/KonkaBcompPd.do?pd_size_type=56' />" style="font-size:9px;width:65px;">55英寸以上</a></dd>
                    </dl>
                    <dl>
                    	<dt><a href="#">白 电</a></dt>
                        <dd style="text-align:center"><a href="#">冰 箱</a></dd>
                        <dd><a href="#">洗衣机</a></dd>
                    </dl>   
                    <dl>
                    	<dt><a href="#">小家电</a></dt>
                        <dd style="text-align:center"><a href="#">电饭锅</a></dd>
                        <dd style="text-align:center"><a href="#">电磁炉</a></dd>
                        <dd><a href="#">空气净化器</a></dd>
                    </dl> <c:if test="${ecUser.user_type eq 1 }"> 
                    <dl>
                    	<dt><a href="<c:url value='/shop/EcGift.do' />">积分商品</a></dt>
                    	<dd style="text-align:center;width:85px;"><a href="<c:url value='/shop/EcGift.do?need_integral_type=1' />">小于1万积分</a></dd>
                    	<dd style="text-align:center;width:85px;"><a href="<c:url value='/shop/EcGift.do?need_integral_type=2' />">1万-8万积分</a></dd>
                    	<dd style="text-align:center;width:85px;"><a href="<c:url value='/shop/EcGift.do?need_integral_type=3' />">8万-50万积分</a></dd>
                    	<dd style="text-align:center;width:85px;"><a href="<c:url value='/shop/EcGift.do?need_integral_type=4' />">50万积分以上</a></dd>
                    </dl></c:if>                 
                </div>
              </li>
              <li>
                <h2><a style="cursor:pointer;">分类标签</a></h2>
                <div class="submenu2">
                	<dl>
                        <dd style="text-align:center"><a href="<c:url value='/shop/KonkaBcompPd.do?label_of_cate=0' />">新 品</a></dd>
                        <dd style="text-align:center"><a href="<c:url value='/shop/KonkaBcompPd.do?label_of_cate=7' />">精品</a></dd>
		                <dd style="text-align:center"><a href="<c:url value='/shop/KonkaBcompPd.do?label_of_cate=2' />">热 销</a></dd>
		                <dd style="text-align:center"><a href="<c:url value='/shop/KonkaBcompPd.do?label_of_cate=3' />">特 惠</a></dd>
		                <dd style="text-align:center"><a href="<c:url value='/shop/PshowOrderPanicBuying.do' />">限时抢购</a></dd>
                    </dl>   
                </div>
              </li>
              <li style="border:none">
                <h2><a style="cursor:pointer;">价&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格</a></h2>
                <div class="submenu3">
                	<dl>
                        <dd style="text-align:center;"><a href="<c:url value='/shop/KonkaBcompPd.do?pd_price=1' />">1000元以下</a></dd>
		                <dd style="text-align:center"><a href="<c:url value='/shop/KonkaBcompPd.do?pd_price=2' />">1000-2000元</a></dd>
		                <dd style="text-align:center"><a href="<c:url value='/shop/KonkaBcompPd.do?pd_price=3' />">2000-3000元</a></dd>
		                <dd style="text-align:center"><a href="<c:url value='/shop/KonkaBcompPd.do?pd_price=4' />">3000-5000元</a></dd>
		                <dd style="text-align:center"><a href="<c:url value='/shop/KonkaBcompPd.do?pd_price=5' />">5000-10000元</a></dd>
		                <dd><a href="<c:url value='/shop/KonkaBcompPd.do?pd_price=6' />" style="width:80px">10000元以上</a></dd>
                    </dl>   
                </div>
              </li>
            </ul>
          </div>
          
        </li>
      </ul>
    </div>
    <div class="logo-2">
   		 <div class="topnavrr1">我的购物车<em id="shopping_car_num">0</em>件</div>
	     <div class="topnavrr2" id="go_shopping_car_btn">去结算</div>
     </div>
  </div>
</div>

<script type="text/javascript" src="${ctx}/scripts/jquery.hoverIntent.minified.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.cookie.js"></script> 
<script type="text/javascript">
$(document).ready(function(){

	// 去结算
	$(document).delegate("#go_shopping_car_btn", "click", function(){
		location.href = "<c:url value='/shop/ShoppingCar.do' />";
	});
	
	// 购物车数量
	var SHOPING_CAR_COOKIE = $.cookie("SHOPING_CAR_COOKIE"); 
	if(null != SHOPING_CAR_COOKIE && "" != SHOPING_CAR_COOKIE) {
		var json = eval("(" + SHOPING_CAR_COOKIE + ")"); 
		var cookie_count = json.length;// 记录cookie中的购物车商品数
		$("#shopping_car_num").html(cookie_count);
	}
	
	// 全部商品信息展示
	function megaHoverOver(){
		$(this).find(".sub").stop().fadeTo('fast', 1).show();
		
		//计算宽度的ul的
		(function($) { 
			jQuery.fn.calcSubWidth = function() {
				rowWidth = 0;
				//计算行
				$(this).find("ul").each(function(){					
					rowWidth += $(this).width(); 
				});	
			};
		})(jQuery); 

		if($(this).find(".row").length > 0){
			var biggestRow = 0;	
			//计算每一行
			$(this).find(".row").each(function(){							   
				$(this).calcSubWidth();
				//找到最大的行
				if(rowWidth > biggestRow){
					biggestRow = rowWidth;
				}
			});
			//设置宽度
			$(this).find(".sub").css({'width' :biggestRow});
			$(this).find(".row:last").css({'margin':'0'});
		}else{
			$(this).calcSubWidth();
			//设置宽度
			$(this).find(".sub").css({'width' : rowWidth});
		}
	}

	function megaHoverOut(){ 
		$(this).find(".sub").stop().fadeTo('fast', 0, function(){
			$(this).hide(); 
		});
	}

	var config = {    
		sensitivity: 2, // number = sensitivity threshold (must be 1 or higher)    
		interval: 100, // number = milliseconds for onMouseOver polling interval    
		over: megaHoverOver, // function = onMouseOver callback (REQUIRED)    
		timeout: 500, // number = milliseconds delay before onMouseOut    
		out: megaHoverOut // function = onMouseOut callback (REQUIRED)    
	};

	$("ul#topnav li .sub").css({'opacity':'0'});
	$("ul#topnav li").hoverIntent(config);

});
</script>