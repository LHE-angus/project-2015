<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<div class="banner" style="border-top:1px solid #e0eaf3;">
	<div class="w1200" style="width:0;">
    	<jsp:include page="/zxmall/__inc/2015/menu.jsp" flush="true" />
    </div> 
    </div>
<script>
$(function(){
	//banner导航切换
	var chsHeight = $(".menu-nav").css("height");
	var overHeight = $(".menu-nav h3").css("height"); 
	$(".menu-nav h3").hover(function(){ 
		$(".menu-nav").animate({"height":"435px"});
		},
		function(){    
		$(".menu-cont").css("display","block");
	});
	
	$(".menu-cont").hover(function(){ 
		$(".menu-cont").fadeIn("fast"); 
		},
		function(){   
		$(".menu-cont").fadeOut("fast");
		$(".menu-list .current").removeClass("current"); 
		$(".menu-nav").animate({"height":overHeight}).find("li.current").removeClass("current");
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