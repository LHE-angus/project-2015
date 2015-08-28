<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>客户端登录——康佳渠道管理系统</title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/shop/css/global.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/shop/css/top.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/shop/css/font.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/shop/css/index.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/shop/css/bottom.css" />
<script src="${ctx}/styles/customer/cloud-zoom/scripts/jquery-1.8.2.min.js"></script>
<script src="${ctx}/styles/customer/smartFloat/smartFloat.js"></script>
<style type="text/css">
.ContSearchBtnWrap{float:left;padding:9px 0;padding-left:50px;}
/*.ContSearchBtnWrap span{display:none;font-size:12px;background:url(${ctx}/styles/customer/shop/images/bg.gif) no-repeat 100% -1803px;height:20px;padding-right:16px;text-align:center;margin:0 5px;float:left;position:relative;}
.ContSearchBtnWrap cite{display:inline-block;font-style:normal;background:url(${ctx}/styles/customer/shop/images/bg.gif) no-repeat 0 -1760px;height:20px;line-height:20px;padding:0 5px;}*/
.ContSearchBtnWrap span{display:none;font-size:12px;background:url(${ctx}/styles/customer/shop/images/cond_r.gif) no-repeat 100%;height:20px;padding-right:16px;text-align:center;margin:0 5px;float:left;position:relative;}
.ContSearchBtnWrap span a{color:#C00;font-size:12px;}
.ContSearchBtnWrap cite{display:inline-block;font-style:normal;background:url(${ctx}/styles/customer/shop/images/cond_l.gif) no-repeat;height:20px;line-height:20px;padding:0 5px;}
.ContSearchBtnWrap i{display:inline-block;width:16px;height:20px;overflow:hidden;position:absolute;top:0;right:0;cursor:pointer;}

</style>
</head>
<body>
<!-- top -->
<!--<jsp:include page="_top.jsp" flush="true" />-->	
<!-- head:nav -->
<jsp:include page="_head.jsp" flush="true" />

<!--one-->
<div class="shop_list">
	<div class="b_l">
 		<div class="list_shop">
    		<div class="list_up"><font class="white">品 类</font></div>
		    <div class="list_down">
		      <ul>
		        <li><a href="#">3D电视</a></li>
		        <li><a href="#">智能电视</a></li>
		        <li><a href="#">网络电视</a></li>
		      </ul>
		    </div>
  		</div>
   		<div class="five_up"><font class="white">一周销量排行</font></div>
   		<div class="five_down">
   			<ul>
   				<li class="aa">康佳 DLFDLAEIDJ102312</li>
      			<li class="bb">康佳 DLFDLAEIDJ102312</li>
         		<li class="cc">康佳 DLFDLAEIDJ102312</li>
            	<li class="dd">康佳 DLFDLAEIDJ102312</li>
               	<li class="ee">康佳 DLFDLAEIDJ102312</li>
                <li class="ff">康佳 DLFDLAEIDJ102312</li>
                <li class="gg">康佳 DLFDLAEIDJ102312</li>
                <li class="hh">康佳 DLFDLAEIDJ102312</li>
   			</ul>
   		</div>
	</div>

	<div class="b_r">
		<div class="shop_right">
 			<div class="two_right_up">
      			<div class="two_r_u"><font class="gran18">特惠机型</font></div>
    		</div>
    		<div class="two_r_d">
    			<ul>
	    			<c:forEach items="${cate3List}" var="cur" varStatus="vs">
		    			<c:choose>
							<c:when test="${cur.label_of_cate eq 0}">
								<c:set value="xinpin" var="sup_class" />
								<c:set value="新品" var="sup_name" />
							</c:when>
							<c:when test="${cur.label_of_cate eq 1}">
								<c:set value="remai" var="sup_class" />
								<c:set value="热卖" var="sup_name" />
							</c:when>
							<c:when test="${cur.label_of_cate eq 2}">
								<c:set value="jingbaojia" var="sup_class" />
								<c:set value="直降" var="sup_name" />
							</c:when>
							<c:when test="${cur.label_of_cate eq 3}">
								<c:set value="tejia" var="sup_class" />
								<c:set value="特惠" var="sup_name" />
							</c:when>
							<c:when test="${cur.label_of_cate eq 4}">
								<c:set value="cuxiao" var="sup_class" />
								<c:set value="推荐" var="sup_name" />
							</c:when>
						</c:choose>
	    				<li>
		        			<div class="t_l">
					          <p class="t_l_p1">康佳彩电${cur.map.md_name}<br/>${fnx:abbreviate(cur.pd_name, 2*22, '')}<br/><c:out value="${cur.pd_size}" />英寸</p>
					          <p><font class="red"><fmt:formatNumber value="${cur.sale_price}" type="currency" /></font></p>
					          <h3 class="p3" onclick="location.href='${ctx}/customer/manager/EShop.do?method=OrderNow&id=${cur.id}'">立即购买</h3>
		        			</div>
		        			<div class="t_r">
		          				<c:set value="${fn:split(cur.main_pic, ',')[0]}" var="main_pic_path" />
		  						<img src="${ctx}/${fn:substringBefore(main_pic_path, '.')}_120.jpg" width="95" height="95" />
		  						<c:if test="${not empty sup_class}">
									<sup class="${sup_class}"><c:out value="${sup_name}" /></sup>
								</c:if>
		        			</div>
	    				</li>
	    				<c:if test="${vs.last}">
			    			<c:forEach begin="1" end="${2 - vs.index}">
			    				<li class="wb"></li>
			    			</c:forEach>
			    		</c:if>
	    			</c:forEach>
	    			<c:if test="${empty cate3List}">
			    		<c:forEach begin="1" end="3">
		    				<li class="wb"></li>
			    		</c:forEach>
			    	</c:if>
    			</ul>
      		</div>   
      	</div>
      	
      	<html-el:form action="/manager/EShop.do" method="post">
      		<html-el:hidden property="method" value="list" />
      		<html-el:hidden property="label_3d" />
      		<html-el:hidden property="label_int" />
      		<html-el:hidden property="label_www" />
      		<html-el:hidden property="mark" />
      		<html-el:hidden property="size_val" styleId="size_val" value="" />
			<html-el:hidden property="price_val" styleId="price_val" value="" />
			<html-el:hidden property="resolution_val" styleId="resolution_val" value="" />
			<html-el:hidden property="cate_val" styleId="cate_val" value="" />
	        <div class="list_s">
	        	<div style="height:35px;line-height:35px">
	        		<table width="100%" cellpadding="0" cellspacing="0" border="0">
	        			<tr>
	        				<td width="100"><span style="float:left;font-size:14px; font-weight:bold;margin-left:10px;padding:0px;">商品筛选</span></td>
	        				<td>
	        					<span class="ContSearchBtnWrap">
					        		<span><cite id="size_text"></cite><i></i></span>
									<span><cite id="price_text"></cite><i></i></span>
									<span><cite id="resolution_text"></cite><i></i></span>
									<span><cite id="cate_text"></cite><i></i></span>
					        	</span>
	        				</td>
	        			</tr>
	        		</table>
	        	</div>
	            <div class="list_s_box">
	              	<div class="filter">
	              		<div class="filter_l">尺寸：</div>
	              		<div class="filter_r">
	              			<ul id="size">
				              <li class="cdr"><a rel="" hidefocus="true">全部</a></li>
				              <li><a rel="32|l" hidefocus="true">32英寸以下</a></li>
				              <li><a rel="32" hidefocus="true">32英寸</a></li>
				              <li><a rel="37" hidefocus="true">37英寸</a></li>
				              <li><a rel="39" hidefocus="true">39英寸</a></li>
				              <li><a rel="42" hidefocus="true">42英寸</a></li>
				              <li><a rel="47" hidefocus="true">47英寸</a></li>
				              <li><a rel="50" hidefocus="true">50英寸</a></li>
				              <li><a rel="55" hidefocus="true">55英寸</a></li>
				              <li><a rel="55|g" hidefocus="true">55英寸以上</a></li>
	              			</ul>
	              		</div>
	            	</div>
	            	<div class="filter">
	              		<div class="filter_l">价格：</div>
	           			<div class="filter_r">
	           				<ul id="price">
				              <li class="cdr"><a rel="" hidefocus="true">全部</a></li>
				              <li><a rel="1-1999" hidefocus="true">1-1999元</a></li>
				              <li><a rel="2000-3999" hidefocus="true">2000-3999元</a></li>
				              <li><a rel="4000-5999" hidefocus="true">4000-5999元</a></li>
				              <li><a rel="6000-10000" hidefocus="true">6000-10000元</a></li>
				              <li><a rel="10000|g" hidefocus="true">10000元以上</a></li>
	           				</ul>
	           			</div>
	           		</div>
	               	<div class="filter">
	              		<div class="filter_l">分辨率：</div>
	              		<div class="filter_r">
	              			<ul id="resolution">
				              <li class="cdr"><a rel="" hidefocus="true">全部</a></li>
				              <li><a rel="3840,2160" hidefocus="true">3840*2160</a></li>
				              <li><a rel="1920,1080" hidefocus="true">1920*1080</a></li>
				              <li><a rel="1366,768" hidefocus="true">1366*768</a></li>
				              <li><a rel="1024,768" hidefocus="true">1024*768</a></li>
				              <li><a rel="9999" hidefocus="true">其他</a></li>
	              			</ul>
	              		</div>
	              	</div>
	              	<div class="filter">
	              		<div class="filter_l">分类标签：</div>
	              		<div class="filter_r">
	              			<ul id="cate">
	              			  <li class="cdr"><a rel="" hidefocus="true">全部</a></li>
				              <li><a rel="0" hidefocus="true">新品</a></li>
				              <li><a rel="1" hidefocus="true">热卖</a></li>
				              <li><a rel="2" hidefocus="true">直降</a></li>
				              <li><a rel="3" hidefocus="true">特惠</a></li>
				              <li><a rel="4" hidefocus="true">推荐</a></li>
	              			</ul>
	              		</div>
	              	</div>
	              </div>
			</div>
      	</html-el:form>
		<script type="text/javascript">
			$(function(){
				var Btn = $('.list_s_box li a');
				function Tsfn(obj,type){
					//var Scroll = $(obj).closest('.ScrollBg').find('.SrcrollCurBG');
					var Tsname = $(obj).closest('ul').attr('id');
					var TsVal = $(obj).attr("rel");
					var TsWidth = $(obj).innerWidth();
					//var Tstip = $(obj).closest('.ScrollBg').find('.tip');
					if($(obj).index()==0){
						var TsIndex = 1;
						var TsWidth = 8;
						var scrollWidth = TsWidth*TsIndex;
					}else{
						var TsIndex	= $(obj).index();
						var scrollWidth = (TsWidth*TsIndex)+8;
					}
					if(type=='TsClick'){	
						//Scroll.stop(true,true).animate({width:scrollWidth+'px'},1000);
						if(Tsname=="Discount"){
							$('#'+Tsname+'_text').empty().append(TsVal+'折').closest('span').css({display:'inline-block'});
							$('#'+Tsname+'_tip').html(TsVal+'折以下');
							$('#'+Tsname+'_val').val(TsVal);
						}else if (Tsname=="Price"){
							$('#'+Tsname+'_text').empty().append('节省'+TsVal+'元').closest('span').css({display:'inline-block'});
							$('#'+Tsname+'_tip').html(TsVal+'元以上');
							$('#'+Tsname+'_val').val(TsVal);
						}else{
							var con = "";
							if (Tsname == "size") {
								con = "尺寸：";
							} else if (Tsname == "price") {
								con = "价格：";
							} else if (Tsname == "resolution") {
								con = "分辨率：";
							} else if (Tsname == "cate") {
								con = "分类标签：";
							}
							var Tshtml = $(obj).html();
							$('#'+Tsname+'_text').empty().append('<a title="' + Tshtml + '" href="javascript:void(0);">' + con + Tshtml + '</a>').closest('span').css({display:'inline-block'});
							$('#'+Tsname+'_val').val(TsVal);

							//根据条件提交表单
							$("#af").submit();
						}
					}else if (type=='Tshover'){
						//Tstip.find('.tip_C').html(TsVal);
						//var TstipWidth = scrollWidth-4-(Tstip.innerWidth()/2);
						//Tstip.stop(true,true).animate({left:TstipWidth+'px'},1000);
					}
				}
				//$(Btn).first().css({ 'padding-left':'0'});
				Btn.click(function(){
					Tsfn($(this),'TsClick');
				});
				Btn.mouseenter(function(){
					Tsfn($(this),'Tshover');
				});
				var CloseBtn = $('.ContSearchBtnWrap i');
				$(CloseBtn).click(function(){
					var Closename =  $(this).prev('cite').attr('id').slice(0,-5);
					//var Scroll = $('#'+Closename).find('.SrcrollCurBG');
					//var Tstip = $('#'+Closename).find('.tip');
					$('#'+Closename+'_text').closest('span').hide();
					//Tstip.stop(true,true).animate({left:'-13px'},1000).find('.tip_C').html('不限');
					$('#'+Closename+'_val').val("");
					//Scroll.stop(true,true).animate({width:0},1000);
					
					//删除条件提交表单
					$("#af").submit();
				});

				//回显
				var size_val = "${af.map.size_val}";
				if ("" != size_val) {
					echo(size_val, "size");
				}
				var price_val = "${af.map.price_val}";
				if ("" != price_val) {
					echo(price_val, "price");
				}
				var resolution_val = "${af.map.resolution_val}";
				if ("" != resolution_val) {
					echo(resolution_val, "resolution");
				}
				var cate_val = "${af.map.cate_val}";
				if ("" != cate_val) {
					echo(cate_val, "cate");
				}
				
				function echo(val, type){
					//alert(val+ "___" + type);
					var con = "";
					if (type == "size") {
						con = "尺寸：";
					} else if (type == "price") {
						con = "价格：";
					} else if (type == "resolution") {
						con = "分辨率：";
					} else if (type == "cate") {
						con = "分类标签：";
					}
					$("a", "#" + type).each(function(){
						$(this).parent().removeClass("cdr");
						if ($(this).attr("rel") == val) {
							$(this).parent().addClass("cdr");
							var Tshtml = $(this).html();
							$("#" + type + "_text").empty().append('<a title="' + Tshtml + '" href="javascript:void(0);">' + con + Tshtml + '</a>').closest('span').css({display:'inline-block'});
							$("#" + type + "_val").val(val);
						}
					});
				}
			});
		</script>

 		<div id="nav" class="six_list">
 			<div class="six_one">
 				<div class="six_l">排序：</div>
 				<div class="six_c">
 					<ul>
					 	<!--<li class="cmr"><a href="#">销量</a></li>-->
					 	<li><a href="#">销量</a></li>
					  	<li><a href="#">价格</a></li>
					   	<li><a href="#">上架时间</a></li>
 					</ul>
 				</div>
 				<form id="bottomPageForm" name="bottomPageForm" method="post" action="EShop.do">
	              <script type="text/javascript" src="${ctx}/styles/customer/pager/pager.js">;</script> 
	              <script type="text/javascript">
					var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage}, "online");
					pager.addHiddenInputs("method", "list");
					pager.addHiddenInputs("label_3d", "${af.map.label_3d}");
					pager.addHiddenInputs("label_int", "${af.map.label_int}");
					pager.addHiddenInputs("label_www", "${af.map.label_www}");
					pager.addHiddenInputs("mark", "${af.map.mark}");
					pager.addHiddenInputs("size_val", "${af.map.size_val}");
					pager.addHiddenInputs("price_val", "${af.map.price_val}");
					pager.addHiddenInputs("resolution_val", "${af.map.resolution_val}");
					pager.addHiddenInputs("cate_val", "${af.map.cate_val}");
					document.write(pager.toString());
				  </script> 
			    </form>
 			</div>
 		</div>
 		<script type="text/javascript">
			var div=document.getElementById("nav");
			var w = getStyle(div,'marginTop');
			var _top = $("#nav").position().top;
			$("#nav").smartFloat(w);

			function getStyle(obj,attr){   
				if(obj.currentStyle){ 
					return obj.currentStyle[attr];   
				} else { 
					return document.defaultView.getComputedStyle(obj, null)[attr]; 
				}   
			}

			if ("" != "${af.map.isNext}") {
				$("#isNext").val("${af.map.isNext}");
				$("html,body").animate({scrollTop: _top}, 1000);
			}
		</script>
		
 		<div class="six_box">
 			<ul>
	 			<c:forEach items="${cateList}" var="cur" varStatus="vs">
	 				<c:choose>
	 					<c:when test="${cur.label_of_cate eq 0}">
	 						<c:set value="xinpin" var="sup_class" />
	 						<c:set value="新品" var="sup_name" />
	 					</c:when>
	 					<c:when test="${cur.label_of_cate eq 1}">
	 						<c:set value="remai" var="sup_class" />
	 						<c:set value="热卖" var="sup_name" />
	 					</c:when>
	 					<c:when test="${cur.label_of_cate eq 2}">
	 						<c:set value="jingbaojia" var="sup_class" />
	 						<c:set value="直降" var="sup_name" />
	 					</c:when>
	 					<c:when test="${cur.label_of_cate eq 3}">
	 						<c:set value="tejia" var="sup_class" />
	 						<c:set value="特惠" var="sup_name" />
	 					</c:when>
	 					<c:when test="${cur.label_of_cate eq 4}">
	 						<c:set value="cuxiao" var="sup_class" />
	 						<c:set value="推荐" var="sup_name" />
	 					</c:when>
	 				</c:choose>
	 				<li>
	 					<p style="padding:10px;margin-bottom:10px;" title="${cur.pd_desc}">
	 						<c:if test="${not empty cur.main_pic}">
				  				<c:set value="${fn:split(cur.main_pic, ',')[0]}" var="main_pic_path" />
				  				<img src="${ctx}/${fn:substringBefore(main_pic_path, '.')}_240.jpg" width="234" height="234" onclick="location.href='${ctx}/customer/manager/EShop.do?method=OrderNow&id=${cur.id}'" />
			  				</c:if>
	 					</p>
	 					<p class="l_list">康佳${cur.map.md_name} ${cur.pd_name}<!--<br/> ${fnx:abbreviate(cur.pd_desc, 2*22, '')} --><c:out value="${cur.pd_size}" />英寸</p>
	 					<p class="l_list"><font class="red"><fmt:formatNumber value="${cur.sale_price}" type="currency" /></font></p>
	 					<div class="bbq">
	 						<div class="bcd">加入购物车</div>
	 						<div class="bdd" style="cursor:pointer;" onclick="location.href='${ctx}/customer/manager/EShop.do?method=OrderNow&id=${cur.id}'"><font class="orange">立刻购买</font></div>
	 					</div>
	 					<c:if test="${not empty sup_class}">
		 					<sup class="${sup_class}">${sup_name}</sup>
	 					</c:if>
	 				</li>
	 			</c:forEach>
 			</ul>
 			<!--<ul>
 				<li><p><div class="th"><font class="white">特惠</font></div><img src="../images/ys.jpg" width="266" height="229" /></p><p class="l_list">康佳彩电LCD-52LX640A52英寸3D智能LED，智能畅享海量52英LED，智能畅享海量视频</p><p class="l_list"><font class="red">￥3599</font></p><div class="bbq"><div class="bcd">加入购物车</div><div class="bdd"><font class="orange">立刻购买</font></div></div></li>
 				<li><p><img src="../images/ys.jpg" width="266" height="229" /></p><p class="l_list">康佳彩电LCD-52LX640A52英寸3D智能LED，智能畅享海量52英LED，智能畅享海量视频</p><p class="l_list"><font class="red">￥3599</font></p><div class="bbq"><div class="bcd">加入购物车</div><div class="bdd"><font class="orange">立刻购买</font></div></div></li>
 				<li><p><img src="../images/ys.jpg" width="266" height="229" /></p><p class="l_list">康佳彩电LCD-52LX640A52英寸3D智能LED，智能畅享海量52英LED，智能畅享海量视频</p><p class="l_list"><font class="red">￥3599</font></p><div class="bbq"><div class="bcd">加入购物车</div><div class="bdd"><font class="orange">立刻购买</font></div></div></li>
			 	<li><p><img src="../images/ys.jpg" width="266" height="229" /></p><p class="l_list">康佳彩电LCD-52LX640A52英寸3D智能LED，智能畅享海量52英LED，智能畅享海量视频</p><p class="l_list"><font class="red">￥3599</font></p><div class="bbq"><div class="bcd">加入购物车</div><div class="bdd"><font class="orange">立刻购买</font></div></div></li>
			 	<li><p><img src="../images/ys.jpg" width="266" height="229" /></p><p class="l_list">康佳彩电LCD-52LX640A52英寸3D智能LED，智能畅享海量52英LED，智能畅享海量视频</p><p class="l_list"><font class="red">￥3599</font></p><div class="bbq"><div class="bcd">加入购物车</div><div class="bdd"><font class="orange">立刻购买</font></div></div></li>
			 	<li><p><img src="../images/ys.jpg" width="266" height="229" /></p><p class="l_list">康佳彩电LCD-52LX640A52英寸3D智能LED，智能畅享海量52英LED，智能畅享海量视频</p><p class="l_list"><font class="red">￥3599</font></p><div class="bbq"><div class="bcd">加入购物车</div><div class="bdd"><font class="orange">立刻购买</font></div></div></li>
 			</ul>-->
 		</div>
	</div>
</div>
<!--one end-->

<!-- foot -->
<jsp:include page="_footer.jsp" flush="true" />
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>