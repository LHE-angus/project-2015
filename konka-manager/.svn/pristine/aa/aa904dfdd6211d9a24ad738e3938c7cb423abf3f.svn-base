<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<title>(${not empty fgs_name ? fgs_name : '总部端' }) - 欢迎您登录康佳渠道管理系统</title>
<link rel="stylesheet" type="text/css"  href="${ctx}/styles/admin-index3/css/css.css" />
<link rel="stylesheet" type="text/css"  href="${ctx}/styles/admin-index3/css/aaa.css" />
<link rel="stylesheet" type="text/css"  href="${ctx}/styles/admin-index3/css/global.css" />
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
function setIFrameHeight(iframe_id, offset) {
	offset = offset || 0;
	var iframeid = document.getElementById(iframe_id); //iframe id
	if (document.getElementById) {
		if (iframeid && !window.opera) {
			if (iframeid.contentDocument && iframeid.contentDocument.body.offsetHeight) {
				iframeid.height = iframeid.contentDocument.body.offsetHeight;
			} else if (iframeid.Document && iframeid.Document.body.scrollHeight) {
				iframeid.height = iframeid.Document.body.scrollHeight;
			}
		}
	}
	iframeid.height = parseInt(iframeid.height) + offset;
}

/**
 * 如果下级页面高度存在在文档加载后变化，在相关时间执行完成后，执行下面的函数（需要拷贝到相关页面）：
 * function resizeFrameHeight(offset, min_height) {
 *	 $("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
 * }
 * 或下面的一段代码：
 * window.parent.resizeFrameHeight('mainFrame', 3);
 *
 * 注：#mainFrame即父窗口iframe的ID
 **/
function resizeFrameHeight(iframe_id, offset, min_height) {
	var $document = $(window.frames[iframe_id].document);
	offset = offset || 0 , min_height = min_height || 0;
	$("#" + iframe_id).height(Math.max(min_height, $document.find("body").height(), $document.children().height()) + offset);
}

function setTab(name, cursel, n) {
	for (var i = 1; i <= n; i++) {
		var menu = document.getElementById(name + i);
		var con = document.getElementById("left_contcont_" + i);
		menu.className = i == cursel ? "k_nav_000" + i : "k_nav_00" + i;
		con.style.display = i == cursel ? "block" : "none";
	}
}
//]]></script>


</head>
<body>
<div style="position:absolute;top:75px;right:10px;"><a href="javascript:void(0);" onclick="this.href=$('#mainFrame').attr('src');" target="_blank" class="target-top"><img src="${ctx}/styles/admin-index3/images/new_window.png" width="22" height="18" alt="新窗口打开" title="新窗口打开" /></a></div>
	<!-- 一级菜单 -->
	<div class="mtop" id="top_banner">
	  <div class="mtop_one">
	    <div style="float:right; width:280px; line-height:25px;">
	      <ul>
<!-- 			<li class="mone">当前在线人数：<span id="login_count">0</span>&nbsp;&nbsp;</li> -->
	        <li class="mtwo"><a href="${ctx}/manager/admin/TerminalFeedbackForSelf.do" target="mainFrame">意见反馈</a></li>
	        <li class="mthree"><a href="${ctx}/manager/admin/Password.do" target="mainFrame">修改密码</a></li>
	        <li class="mfour"><a href="${ctx}/login.do?method=logout" class="target-top">退出系统</a></li>
	      </ul>
	    </div>
	  </div>
	  <div class="mtop_two">
	    <div class="mtop_l">
	    	<font class="white"><span title="${roleNames}">${userInfo.user_name}</span>，您好！</font>
	    </div>
	    <div class="m_r" style="width:${(70+20) * (fn:length(sysModuleList_1))  }px">
	      <ul id="top-nav">
	      <c:forEach var="cur" items="${sysModuleList_1}" varStatus="vs">
	      	<li id="top-nav-${vs.count}"><img src="${ctx}/styles/admin-index3/images/${cur.mod_id eq 500000 ? fn:substringAfter(cur.icon_path, '/') : fn:substringBefore(cur.icon_path, '/')}" width="70" height="72" data-img="${fn:substringBefore(cur.icon_path, '/')}" data-activeimg="${fn:substringAfter(cur.icon_path, '/')}" data-url="${cur.mod_url}" /></li>
	      </c:forEach>
	      </ul>
	    </div>
	  </div>
	</div>

<!--一级菜单与二级菜单 -->
<div class="mian" id="con_two_1">
  <c:forEach var="cur1" items="${sysModuleList_1}" varStatus="vs1">
  	<div id="left_contcont_${vs1.count}" class="left_contcont" style="display:${vs1.first ? '' : 'none'}">
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
					        <a  href="${cur.mod_url}${fn:indexOf(cur.mod_url, '?') > -1 ? '&' : '?'}mod_id=${cur.mod_id}" target="mainFrame">${cur.mod_name}</a>
				      	</c:if>
				      </div>
				    </div>
			    </c:if>
			    <c:if test="${cur.tree_level eq 3}">
			    	<c:if test="${last_level eq 4}"></div></c:if>
			    	<c:if test="${last_level eq 2}"><div class="sub_1" style="display:none;"></c:if>

			    	<c:if test="${empty cur.mod_url}">
			    		<div class="ys_li_b"><span>+</span>${cur.mod_name}</div>
			    	</c:if>
			    	<c:if test="${not empty cur.mod_url}">
			    		<div class="ys_li_b"><a  href="${cur.mod_url}${fn:indexOf(cur.mod_url, '?') > -1 ? '&' : '?'}mod_id=${cur.mod_id}" target="mainFrame">${cur.mod_name}</a></div>
			    	</c:if>
			    </c:if>
			    <c:if test="${cur.tree_level eq 4}">
			    	<c:if test="${last_level eq 3}"><div class="sub_2" style="display:none;"></c:if>
			    	
			    	<c:if test="${empty cur.mod_url}">
			    		<div class="ys_li_b1">${cur.mod_name}</div>
			    	</c:if>
			    	<c:if test="${not empty cur.mod_url}">
			    		<div class="ys_li_b1"><a  href="${cur.mod_url}${fn:indexOf(cur.mod_url, '?') > -1 ? '&' : '?'}mod_id=${cur.mod_id}" target="mainFrame">${cur.mod_name}</a></div>
			    	</c:if>
			    </c:if>
				<c:set var="last_level" value="${cur.tree_level}" />
  			</c:if>
  		</c:forEach>
    	<c:if test="${last_level eq 4}"></div></div></c:if>
    	<c:if test="${last_level eq 3}"></div></c:if>
  	</div>
  </c:forEach>
  
  <div id="right_contcont" class="right_contcont">
    <iframe name="mainFrame" id="mainFrame" src="${ctx}/manager/admin/Frames3/mainFrame_index.jsp" frameborder="0" width="100%" scrolling="no" style="margin:0;padding:0;" onload="resizeFrameHeight('mainFrame', -2, $(window).height() - $('#top_banner').outerHeight() - $('#navbar').outerHeight());" onresize="this.onload();">
    </iframe>
  </div>
  
</div>


<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
String.prototype.startsWith	= function(prefix){ return this.indexOf(prefix) === 0; };

// 	function remainTime(){ 
// 		$.post('${ctx}/manager/admin/CMSSeverStatus.do?method=getCount',function(result){
// 			$("#login_count").text(result.count);
			
// 		},'json');
// 	    setTimeout("remainTime()",60000);  
// 	}  
// 	remainTime();
$(function(){
	var indexUrl = '${ctx}/manager/admin/Frames3.do?method=main';
	var last_url = indexUrl;
	var $last_left = $("#left_contcont_1"), $last_li = $("#top-nav-1");

	var img_dir = "${ctx}/styles/admin-index3/images/";
	
	//如果前的菜单链接没有子节点,那么删除span
	$(".left40, .left4").each(function(){
		if($(this).next('.sub_1').length == 0)	$(this).find('span').remove();
	});
	$(".ys_li_b").each(function(){
		if($(this).next('.sub_2').length == 0)	$(this).find('span').remove();
	});
	//top nav 
	$("#top-nav li").css("cursor", "pointer");
	$("#top-nav li").click(function(){	
		var index = $(this).attr("id").replace("top-nav-", "");
		if(index == '0') {
			if (indexUrl != last_url) {
				$("#mainFrame").attr('src', indexUrl);
				last_url = indexUrl;
			}
			return;
		} else {
			$last_left.hide();
			$last_left = $("#left_contcont_" + index).show();
		}
		
		var $last_li_img = $last_li.children("img");
		$last_li_img.attr("src", img_dir + $last_li_img.data("img"));
		
		var $img = $(this).children("img");
		$img.attr("src", img_dir + $img.data("activeimg"));
		
		var targetUrl = $img.data("url");
		if(last_url != targetUrl && index != '0' && $.trim(targetUrl).length > 0) {
			$("#mainFrame").attr('src', targetUrl);
			$(window.frames["mainFrame"].document).children().html("<div style='padding:12px;color:#ccc;font-style:italic;'>服务器正在加载您的请求，请稍候...</div>");
			last_url = targetUrl;
		}
		$last_li = $(this);
	});
	
	//二级菜单 
	$(".left40, .left4").click(function(){	
		
		//同级别兄弟节点只能打开一个 
		$(this).siblings(".left40, .left4").each(function(){
			$(this).find('span').text('+');
			var $bnext = $(this).next('.sub_1');
			if($bnext.is(':visible')) {
				$bnext.hide();
			} 
		});
		
		//
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
		last_url = url;
	}
	
	var chHeight = function() {
		var height = $(window).height() - $("#top_banner").outerHeight() - 2;
		$(".left_contcont, #right_contcont").height(height).css('overflow', 'auto');
	};
	chHeight();
	$(window).resize(chHeight);

});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>