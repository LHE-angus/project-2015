<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<title>康佳直销商城</title>
<link rel="stylesheet" href="${ctx}/styles/zxmall/2015/css/base.css"/>
<link rel="stylesheet" href="${ctx}/styles/zxmall/2015/css/index.css"/> 
<link rel="stylesheet" href="${ctx}/styles/zxmall/css/banner_slide.css"/>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/styles/zxmall/js/rgbaster.min.js"></script>
<script type="text/javascript" src="${ctx}/styles/zxmall/js/jquery.superslide.2.1.1.js" ></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.jcarousellite.js"></script>
<style type="text/css">
#bg{display:none;position: absolute;top:0%;left: 0%; width: 100%;height:5000px;background-color: black;z-index:10001;-moz-opacity: 0.7;opacity:.70;filter: alpha(opacity=70);} 
.palyer_video_tc {display: none;  position: absolute;  top:20%;  left: 22%; z-index:100021;border: 6px solid;height: 420px;padding-top: 0px;width:720px;margin:80px auto;color: #000000;}
.palyer_right{float:right;}
.palyer_video_tc .palyer_close {background: none repeat scroll 0 0 #000000; font-size: 14px;line-height: 30px; margin-right:-40px; margin-top: 0;text-align: center; width: 40px;}
.palyer_video_tc .palyer_close a {color: #FFFFFF;text-decoration:none;}
</style>
</head>
<body id="web_body">
<jsp:include page="/zxmall/__inc/2015/top.jsp" flush="true" />
<div style="height:400px;" id="banner_bg">
<div class="banner">
	<div class="w1200">
    	 <jsp:include page="/zxmall/__inc/2015/menu.jsp" flush="true" />
    </div>
	<div style="margin-left:213px;width:990px;position:relative;height:400px;border-top:0px solid #e0eaf3;">
		<div class="banner_slide">
            <ul class="bannerpic">
            	<c:forEach var="cur" items="${imgList}">        			
                <li><a href="${ctx }${cur.image_url}" target="_blank"><img src="${ctx }/${cur.image_path}" width="990"/></a></li>
                </c:forEach>
             </ul>
            <a class="prev" href="javascript:void(0)"></a> <a class="next" href="javascript:void(0)"></a>
            <div class="num"><ul></ul></div>
        </div>
        <script>
        /*鼠标移过，左右按钮显示*/
        $(".banner_slide").hover(function(){
            $(this).find(".prev,.next").fadeTo("show",0.1);
        },function(){
            $(this).find(".prev,.next").hide();
        })
        /*鼠标移过某个按钮 高亮显示*/
        $(".prev,.next").hover(function(){
            $(this).fadeTo("show",0.7);
        },function(){
            $(this).fadeTo("show",0.1);
        })
        $(".banner_slide").slide({ titCell:".num ul" , mainCell:".bannerpic" , effect:"fold", autoPlay:true, delayTime:1200 , autoPage:true, startFun:function(i,c){
        	try{
        		RGBaster.colors($(".bannerpic").find("img").get(i), {
                 exclude: [ 'rgb(255,255,255)', 'rgb(0,0,0)' ],
                 success: function(payload) {           	// alert(payload.dominant);
                   // 容器的背景色变成图片的主色 - payload.dominant
                   $("#banner_bg").css("background-color", payload.dominant);
                 }
               });}catch(e){}  
        } });
        </script>
      </div>
</div>
<script> 
$(".menu-nav").animate({"height":"435px"});
$(function(){
	//banner导航切换
	var chsHeight = $(".menu-nav").css("height");
	var overHeight = $(".menu-nav h3").css("height"); 
	$(".menu-cont").hover(function(){ 
		$(".menu-cont").fadeIn("fast"); 
		},
		function(){   
		$(".menu-cont").fadeOut("fast");
		$(".menu-list .current").removeClass("current"); 
	});
	
	$(".menu-list li").hover(function(){  
		$(".menu-cont").fadeIn("fast"); 
		var _index = $(this).index();
		$(".menu-list .current").removeClass("current");
		$(this).addClass("current");
		$(".menu-cont-c:eq("+_index+")").css("display","block").siblings(".menu-cont-c").css("display","none");
		},
		function(){}
	);
});

</script>
</div>
<div class="main">
	<div class="w1200">
    	<div class="contentA clearfix">
	    	<div class="contentA-tj" style="">
	    	<img src="${ctx }/styles/zxmall/2015/images/rmzq.png"/> 
	    	</div>
        	<div class="contentA-l" >
                <ul class="contentA-l-list" ><c:forEach items="${bcomp_pd_list_tj_5}" var="cur" varStatus="vs" end="2">
                	<li>
                    	<a href="<c:url value='/zxmall/PdShow.do?goods_id=${cur.id}' />"><img src="${ctx}/${cur.main_pic}" width="180" height="135" /></a>
                        <p><a href="<c:url value='/zxmall/PdShow.do?goods_id=${cur.id}' />">${cur.pd_sn}</a></p>
                        <p><span class="red"  style="font-size:20px;">￥<fmt:formatNumber value="${cur.ecGoodsPrice.price}" pattern="0" /></span> <span class="delgray">市场价:￥<fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></span></p>
                    </li></c:forEach> 
                </ul>
            </div>
            <div class="contentA-r">
            <iframe border="0" src="${ctx }/zxmall/FlvPlayer.do?method=showImg&id=838505&height=201&width=280" frameborder="0" marginheight="0" marginwidth="0" width="280" height="200" scrolling="no"></iframe>
            </div>
        </div>
        <c:forEach items="${ecHomeFloorList}" var="cur" varStatus="vs" end="5"> 
        	<c:set var="color_10002103" value="" />
        	<c:set var="img_10002101" value="" />
        	<c:set var="title_10002102" value="" />
        	<c:set var="img_10002102" value="" />
        	<c:set var="url_10002102" value="" /> 
        	<c:set var="color_10002108" value="" /> 
        	<c:set var="title_10002107" value="" />
        	<c:set var="img_10002107" value="" />
        	<c:set var="url_10002107" value="" />
        	<c:set var="original_price_10002107" value="" />
        	<c:set var="price_10002107" value="" />
        	<c:forEach items="${cur.ecHomeFloorDataList}" var="cur1" varStatus="vs1"  > 
        		<c:if test="${cur1.data_type_id eq 10002103 and empty color_10002103}">
        		 	<c:set var="color_10002103" value="${cur1.title}" />
        		</c:if>
        		<c:if test="${cur1.data_type_id eq 10002101 and empty img_10002101}">
        		 	<c:set var="img_10002101" value="${cur1.img_url}" />
        		</c:if>
        		<c:if test="${cur1.data_type_id eq 10002102 and empty title_10002102}">
	        		 <c:set var="title_10002102" value="${cur1.title}" />
	        		 <c:set var="img_10002102" value="${cur1.img_url}" />
	        		 <c:set var="url_10002102" value="${cur1.link_url}" />
        		</c:if>
        		<c:if test="${cur1.data_type_id eq 10002108 and empty color_10002108}">
        		 	<c:set var="color_10002108" value="${cur1.title}" />
        		</c:if>
        		<c:if test="${cur1.data_type_id eq 10002107 and empty title_10002107}">
	        		 <c:set var="title_10002107" value="${cur1.title}" />
	        		 <c:set var="img_10002107" value="${cur1.img_url}" />
	        		 <c:set var="url_10002107" value="${cur1.link_url}" />
	        		 <c:set var="original_price_10002107" value="${cur1.original_price}" />
	        		 <c:set var="price_10002107" value="${cur1.price}" />
        		</c:if>
        	</c:forEach> 
        	
        <!-- floor${vs.count} start ${cur.title}-->
        <div class="box clearfix mt20" >
        	<div class="box-left"> 
                <div class="box-left-zq">
                <img  src="${img_10002102 }" width="210" height="280"/>
                </div>
                <div class="box-left-cp">
                <!--10002104	楼层左侧分类1 10002105  楼层左侧分类210002106	楼层左侧分类3 title link_url-->
                	<ul class="clearfix">
                	<c:forEach items="${cur.ecHomeFloorDataList}" var="cur1" varStatus="vs1"  > 
                		<c:if test="${cur1.data_type_id eq 10002104}">
                    	<li class="bgimg"><a href="${cur1.link_url }">${cur1.title }</a></li>
                    	</c:if>
                    </c:forEach> 
                    </ul>
                    <ul class="clearfix">
                    <c:forEach items="${cur.ecHomeFloorDataList}" var="cur1" varStatus="vs1"  > 
                		<c:if test="${cur1.data_type_id eq 10002105}">
                    	<li class="bgimg"><a href="${cur1.link_url }">${cur1.title }</a></li>
                    	</c:if>
                    </c:forEach> 
                    </ul>
                    <div class="box-left-b">
                    <c:forEach items="${cur.ecHomeFloorDataList}" var="cur1" varStatus="vs1"  > 
                		<c:if test="${cur1.data_type_id eq 10002106}">
                		<p style="font-size:14px;line-height:38px;"><a href="${cur1.link_url }">${cur1.title }</a></p> 
                    	</c:if>
                    </c:forEach> 
                    </div>
                </div>
            </div>
            <div class="box-right">
            	<div class="box-r-c clearfix">
            		<!--  10002107	楼层右侧图片一  -->
                	<div class="sp-focus" style="height:399px;">
                	<a href="${url_10002107 }"><img src="${img_10002107 }" width="416" height="399" /></a> 
                    </div> 
                    <ul class="box-r-list clearfix">
                    <!-- 10002109	楼层右侧产品列表一  -->
                    <c:set var="c" value="0" />
                    <c:forEach items="${cur.ecHomeFloorDataList}" var="cur1" varStatus="vs1"  > 
                		<c:if test="${cur1.data_type_id eq 10002109 and c lt 4}"><c:set var="c" value="${c+1}" />
                    	<li class="move_div" style="cursor: pointer;" onclick="location.href='${cur1.link_url}'" <c:if test="${c eq 1}">class="border-r border-b"</c:if><c:if test="${c eq 2}">class="border-b"</c:if><c:if test="${c eq 3}">class="border-r"</c:if>>
                        	<h3>${cur1.title}</h3>
                        	<div style="position:absolute;top:28px;left:0px;margin:0 auto; width:100%;height:150px;z-index:-1;" >
                            <a class="pic-link" href="${cur1.link_url}"><img src="${cur1.img_url}" width="200" height="150"/></a>
                            </div>
                            <p style="margin-top:120px;"><span class="red" style="font-size:20px;">￥${cur1.price}</span> <span class="delgray" >市场价:￥${cur1.original_price}</span> </p>
                        </li>
                      </c:if>
                      </c:forEach>
                    </ul>
                </div>
                <div class="box-scroll-s"  >
                	<div id="sp_jCarouselLite0${vs.count}" class="box-scroll" >
                	<!-- 10002110	楼层右侧产品列表二  -->
                        <ul><c:forEach items="${cur.ecHomeFloorDataList}" var="cur1" varStatus="vs1"  > 
                		<c:if test="${cur1.data_type_id eq 10002110}">
                            <li>
                            	<div class="scroll-c-l"><a href="${cur1.link_url}"><img src="${cur1.img_url}" width="150" height="112.5"/></a></div>
                                <div class="scroll-c-r"><p>${cur1.title}</p><p><span class="red" style="font-size:20px;">￥${cur1.price}</span></p><p><span class="delgray" >市场价:￥${cur1.original_price}</span></p></div>
                            </li>
	                      </c:if>
	                      </c:forEach>
                        </ul>
                    </div>
                    <a class="prev-btn0${vs.count} icon_bg" href="##"></a>
                    <a class="next-btn0${vs.count} icon_bg" href="##"></a>
                </div>
            </div>
        </div>
        <!-- floor${vs.count} start ${cur.title}-->
        </c:forEach> 
    </div>
</div>
<div id="bg"></div> 
<div id="show" class="palyer_video_tc palyer_clear"> 
	<div class="palyer_close palyer_right"><a id="wjPop-close" href="javascript:void(0);"  onclick="hidediv();">关闭</a></div> 
	<iframe border="0" id="player" src="" frameborder="0" marginheight=0 marginwidth=0 width="720" height="426" scrolling="no"></iframe>
</div>  
<script type="text/javascript">
$(function(){ //商品信息滚动	
	<c:forEach items="${ecHomeFloorList}" var="cur" varStatus="vs" end="5">  
	$("#sp_jCarouselLite0${vs.count}").jCarouselLite({auto:3000,btnPrev:'.prev-btn0${vs.count}',btnNext:'.next-btn0${vs.count}',speed:600,visible:3,scroll:1});
	</c:forEach>
});
function hidediv() {
    window.top.document.getElementById("bg").style.display ='none';
    window.top.document.getElementById("show").style.display ='none';
    window.top.document.getElementById("player").src="";
} 

$('.move_div').hover(function() { 
	  $(this).animate({
	    opacity: 0.45,
	    right: '+=15'
	  }, 800, function() {});
},function() {
	 $(this).animate({
	    opacity: 1,
	    right: '-=15'
	  }, 800, function() {});
	}
); 
</script>
<jsp:include page="/zxmall/__inc/2015/footer.jsp" flush="true" />
</body>
</html>