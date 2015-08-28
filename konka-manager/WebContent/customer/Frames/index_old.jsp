<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<title>${shop.customer_name}<c:if test="${role_id_eq_400}">（康佳专卖店）</c:if></title>
<link rel="stylesheet" type="text/css"  href="${ctx}/styles/admin-index/css/css.css" />
<link rel="stylesheet" type="text/css"  href="${ctx}/styles/admin-index/css/global.css" />
<style type="text/css">
* {overflow:hidden;}
body::-webkit-scrollbar-track-piece { background-color: white;}
::-webkit-scrollbar { width: 6px; height: 6px;}
::-webkit-scrollbar-track-piece { background-color: transparent;}
::-webkit-scrollbar-track-piece:no-button {}
::-webkit-scrollbar-thumb { background-color: #e60012; border-radius: 3px;}
::-webkit-scrollbar-thumb:hover { background-color: #e65512;}
::-webkit-scrollbar-thumb:active { background-color: #e65512;}

::-webkit-scrollbar-button:vertical { width: 6px;}
::-webkit-scrollbar-button:horizontal { width: 6px;}
::-webkit-scrollbar-button:vertical:start:decrement { background-color: white;width:0px;height:0px;}
::-webkit-scrollbar-button:vertical:end:increment { background-color: white;width:0px;height:0px;}
::-webkit-scrollbar-button:horizontal:start:decrement { background-color: white;width:0px;height:0px;}
::-webkit-scrollbar-button:horizontal:end:increment { background-color: white;width:0px;height:0px;}
</style>
<script type="text/javascript">//<![CDATA[

/**
 * 如果下级页面高度存在在文档加载后变化，在相关时间执行完成后，执行下面的函数（需要拷贝到相关页面）：
 * function resizeFrameHeight(offset, min_height) {
 *	 $("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
 * }
 * 或下面的一段代码：
 * window.parent.resizeFrameHeight('mainFrame', 3);
 *
 * 注：mainFrame即父窗口iframe的ID
 **/
function resizeFrameHeight(iframe_id, offset, min_height) {
	var $document = $(window.frames[iframe_id].document);
	$("#" + iframe_id).height(Math.max(
			(min_height || 0), 
			$document.find("body").height(), 
			$document.children().height()) + (offset || 0)
		);
}
function setTab(name, cursel, n, curl) {
	for (var i = 1; i <= n; i++) {
		var menu = document.getElementById(name + i);
		var con = document.getElementById("left_contcont_" + i);
		if (!!menu) menu.className = i == cursel ? "k_nav_000" + i : "k_nav_00" + i;
		if (!!con) con.style.display = i == cursel ? "block" : "none";
	}
	
	reloadIndex(cursel,curl);
}

function reloadIndex(cursel,curl) {
	cursel = cursel || 1;
	if(cursel == 1) {
		//var default_index = '${ctx}/customer/manager/EShop.do?method=decorate';
		var default_index = curl;
		if ($("#mainFrame").attr('src') != default_index) {
			$("#mainFrame").attr('src', default_index);
		}
		$("#mainFrame").attr('src', curl);
		$("#right_contcont").css("margin-left", "0px");
	} else {
		//var default_main = '${ctx}/customer/manager/Frames.do?method=main';
		var default_main = curl;
		if ($("#mainFrame").attr('src') != default_main) {
			$("#mainFrame").attr('src', default_main);
		}
		$("#mainFrame").attr('src', curl);
		$("#right_contcont").css("margin-left", "174px");
	}
}
//]]></script>
</head>
<body style="font-family:Microsoft Yahei;">
<div id="top_banner" class="top_banner">
  <div class="k_logo"><img src="${ctx}/styles/admin-index/images/k_logo_min.gif" border="0" usemap="#Map2" id="logo" /></div>
  <div style="height:59px;line-height:59px;float:left;font-family:'华文中宋','宋体',Microsoft Yahei;font-size:20px;font-weight:bold;color:#454545;"><c:if test="${role_id_eq_400}"><span title="${is_zmd_100200 ? '分公司返利经营模式' : '一步价经营模式'}">（专卖店）${is_admin_show}</span></c:if><c:if test="${not role_id_eq_400}"></c:if></div>
  <div class="k_guanli">欢迎您：<c:if test="${not empty fgsName}">（${fgsName}）</c:if>${shop.customer_name}（${customerUserInfo.user_name}）<a href="${ctx}/customer/login.do?method=logout" class="target-top">【退出】</a></div>
  <div class="k_top_r">
    <div class="k_home"><a href="${ctx}/customer/manager/Frames.do?method=main">系统首页</a></div>
    <div class="k_xgmm"><a href="${ctx}/customer/manager/JxcPasswardUpdate.do?method=list&mod_id=199990000" target="indexFrame">修改密码</a></div>
    <div class="k_czrz"><a href="${ctx}/customer/manager/EShop.do" class="target-top">在线销售</a></div>
  </div>
  <div class="clear"></div>
</div>

<div id="navbar" class="riqi">
  <ul class="k_nav">
     <c:forEach var="cur" items="${sysModuleList_1}" varStatus="vs">
     	<c:if test="${cur.tree_level eq 1}">
	     	<li class="k_nav_00${cur.mod_id eq 105000000 ? '0' : ''}${vs.count + 1}" id="two${vs.count + 1}" onclick="setTab('two', ${vs.count + 1}, ${fn:length(sysModuleList_1) + 1},'${cur.mod_url}')"><a href="javascript:void(0);" id="img${vs.count + 1}">${cur.mod_name}</a></li>
     	</c:if>
     </c:forEach>
  </ul>
  <div style="position:absolute;top:70px;right:10px;"><a href="javascript:void(0);" onclick="this.href=$('#mainFrame').attr('src');" target="_blank" class="target-top"><img src="${ctx}/styles/admin-index/images/new-window.png" alt="新窗口打开" title="新窗口打开" /></a></div>
</div>
<!--栏目1-->
<div class="mian" id="con_two_1">
  <c:forEach var="cur1" items="${sysModuleList_1}" varStatus="vs1">
  	<div id="left_contcont_${vs1.count + 1}" class="left_contcont" style="display:${vs1.count eq 1 ? '' : 'none'};">
  		<c:set var="last_level" value="1" />
  		<c:forEach var="cur" items="${sysModuleList}" varStatus="vs">
  			<c:if test="${cur.root_id eq cur1.mod_id}">
			    <c:if test="${cur.tree_level eq 2}">
			    	<c:if test="${last_level eq 4}"></div></div></c:if>
			    	<c:if test="${last_level eq 3}"></div></c:if>
	  				<div class="left4">
				      <div style="float:right;"><span>+</span></div>
				      <div style="float:left">
				      <c:if test="${empty cur.mod_url}">${cur.mod_name}</c:if>
			    	  <c:if test="${not empty cur.mod_url}">
				        <a href="${cur.mod_url}${fn:indexOf(cur.mod_url, '?') > -1 ? '&' : '?'}mod_id=${cur.mod_id}" target="mainFrame">${cur.mod_name}</a>
				      </c:if></div>
				    </div>
			    </c:if>
			    <c:if test="${cur.tree_level eq 3}">
			    	<c:if test="${last_level eq 4}"></div></c:if>
			    	<c:if test="${last_level eq 2}"><div class="sub_1" style="display:none;"></c:if>
			    	<c:if test="${empty cur.mod_url}">
			    		<div class="ys_li_b"><span>+</span>${cur.mod_name}</div>
			    	</c:if>
			    	<c:if test="${not empty cur.mod_url}">
			    		<div class="ys_li_b"><span>+</span><a href="${cur.mod_url}${fn:indexOf(cur.mod_url, '?') > -1 ? '&' : '?'}mod_id=${cur.mod_id}" target="mainFrame">${cur.mod_name}</a></div>
			    	</c:if>
			    </c:if>
			    <c:if test="${cur.tree_level eq 4}">
			    	<c:if test="${last_level eq 3}"><div class="sub_2" style="display:none;"></c:if>
			    	<c:if test="${empty cur.mod_url}">
			    		<div class="ys_li_b1">${cur.mod_name}</div>
			    	</c:if>
			    	<c:if test="${not empty cur.mod_url}">
			    		<div class="ys_li_b1"><a href="${cur.mod_url}${fn:indexOf(cur.mod_url, '?') > -1 ? '&' : '?'}mod_id=${cur.mod_id}" target="mainFrame">${cur.mod_name}</a></div>
			    	</c:if>
			    </c:if>
			    <c:set var="last_level" value="${cur.tree_level}" />
  			</c:if>
  		</c:forEach>
    	<c:if test="${last_level eq 4}"></div></div></c:if>
    	<c:if test="${last_level eq 3}"></div></c:if>
  	</div><!-- left_contcont_${vs1.count} end -->
  </c:forEach>
  <div id="right_contcont" class="right_contcont">
  <c:if test="${not empty defaulturl}">
   <iframe name="mainFrame" id="mainFrame" src="${ctx}/customer/manager/${defaulturl}" frameborder="0" width="100%" scrolling="no" style="margin:0;padding:0;" onload="resizeFrameHeight('mainFrame', -2, $(window).height() - $('#top_banner').outerHeight() - $('#navbar').outerHeight());" onresize="this.onload();"></iframe>
  </c:if>
  <c:if test="${empty defaulturl}">
    <iframe name="mainFrame" id="mainFrame" src="${ctx}/customer/manager/CeppOrder.do?mothed=listPic" frameborder="0" width="100%" scrolling="no" style="margin:0;padding:0;" onload="resizeFrameHeight('mainFrame', -2, $(window).height() - $('#top_banner').outerHeight() - $('#navbar').outerHeight());" onresize="this.onload();"></iframe>
  </c:if>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
                                          
String.prototype.startsWith	= function(prefix){ return this.indexOf(prefix) === 0 };
$(function(){
	
	//如果前的菜单链接没有子节点,那么删除span
	$(".left40, .left4").each(function(){
		if($(this).next('.sub_1').length == 0)	$(this).find('span').remove();
	});
	$(".ys_li_b").each(function(){
		if($(this).next('.sub_2').length == 0)	$(this).find('span').remove();
	});
	
	//二级菜单 
	$(".left40, .left4").click(function(){	
		$(".left40").attr('class', 'left4');
		$(this).attr('class', 'left40');
		var $next = $(this).next('.sub_1');
		if($next.is(':visible')) {
			$next.hide();
			$(this).find('span').text('+');
		} else {
			$next.show();
			$(this).find('span').text('-');
		}
// 		//有可能是url 
		var url = $(this).find('a').attr('href');
		if(url){
			open(url);
		}
	});
	
	//3级菜单 
	$(".ys_li_b").click(function(){	
		var $next = $(this).next('.sub_2');
		if($next.is(':visible')) {
			$next.hide();
			$(this).find('span').text('+');
		} else {
			$next.show();
			$(this).find('span').text('-');
		} 
		open($(this).find('a').attr('href'));
	});
	
	//4级菜单 
	$(".ys_li_b1").click(function(){	
		 open($(this).find('a').attr('href'));
	});
	
	$("#top_banner a:not(.target-top)").click(function(){	
		open($(this).attr("href"));
		return false;
	});
	
	function open(url) {
		//if (!!console) console.debug('open url : ', url);
		if(!url || '#' == url || url.toLowerCase().startsWith('javascript:')) return;
		$("#mainFrame").attr("src", url);
		$(window.frames["mainFrame"].document).children().html("<div style='padding:12px;color:#ccc;font-style:italic;'>服务器正在加载您的请求，请稍候...</div>");
	}
	
	var chHeight = function() {
		var height = $(window).height() - $("#top_banner").outerHeight() - $("#navbar").outerHeight() - 2;
		$(".left_contcont, #right_contcont").height(height).css('overflow', 'auto');
		
		if ($(document).width() > 1211) {
			$("#logo").attr("src", "${ctx}/styles/admin-index/images/k_logo.gif");
		} else {
			$("#logo").attr("src", "${ctx}/styles/admin-index/images/k_logo_min.gif");
		}
	};
	chHeight();
	$(window).resize(chHeight);

	reloadIndex();
	
	//是否提醒月末的全部盘点和月初的结转
	var pdAndJzRemind = '${pdAndJzRemind}';
	if(null != pdAndJzRemind && pdAndJzRemind != ''){
		alert(pdAndJzRemind);
	}
	
});
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
