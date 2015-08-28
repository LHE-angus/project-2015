<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<title>${fgs_name}${fgs_name eq dept_name ? '分公司' : dept_name}管理端 - 欢迎您登录康佳渠道管理系统</title>
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

// 如果下级页面高度存在在文档加载后变化，在相关时间执行完成后，执行下面的函数（需要拷贝到相关页面）：
/**
function resizeFrameHeight(offset, min_height) {
	$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
}
// 注：#mainFrame即父窗口iframe的ID
*/
function setTab(name, cursel, n) {
	for (i = 1; i <= n; i++) {
		var menu = document.getElementById(name + i);
		var con = document.getElementById("left_contcont_" + i);
		menu.className = i == cursel ? "k_nav_000" + i : "k_nav_00" + i;
		con.style.display = i == cursel ? "block" : "none";
	}
}
//]]></script>
</head>
<body>
<div id="top_banner" class="top_banner">
  <div class="k_logo"><img src="${ctx}/styles/admin-index/images/k_logo.gif" width="462" height="59" border="0" usemap="#Map2" /></div>
  <c:if test="${not empty fgs_name}"><div style="font-size:24px;font-weight:800;float:left;line-height:59px;">（${fgs_name}）</div></c:if>
  <div class="k_guanli">欢迎您：${userInfo.user_name}<c:if test="${not empty dept_name}"> [${dept_name}]</c:if></div>
  <div class="k_top_r">
    <div class="k_home"><a href="${ctx}/manager/admin/Frames2.do?method=main" target="mainFrame">系统首页</a></div>
    <!-- 
    <div class="k_czrz"><a href="${ctx}/manager/admin/KonkaSxOperLog.do?mod_id=906000" target="mainFrame">操作日志</a></div>
    <div class="k_yijfk"><a href="${ctx}/manager/admin/TerminalFeedbackForSelf.do" target="mainFrame">意见反馈</a></div> -->
    <div class="k_xgmm"><a href="${ctx}/manager/admin/Password.do" target="mainFrame">修改密码</a></div>
    <div class="k_czrz"><a href="${ctx}/manager/admin/Frames.do?method=index" class="target-top">返回老版</a></div>
    <div class="k_tuichu"><a href="${ctx}/login.do?method=logout" class="target-top">【退出】</a></div>
  </div>
  <div class="clear"></div>
</div>
<div id="navbar" class="riqi">
  <ul class="k_nav">
  	<c:forEach var="cur" items="${sysModuleList_1}" varStatus="vs">
 		<li class="k_nav_00${vs.first ? '0' : ''}${vs.count}" id="two${vs.count}" onclick="setTab('two',${vs.count}, ${fn:length(sysModuleList_1)})"><a href="javascript:void(0);" id="img${vs.count}">${cur.mod_name}</a></li>
  	</c:forEach>
  	<!-- 
    <li class="k_nav_0001" id="two1" onclick="setTab('two',1,7)"><a href="#" id="img1">终端管理</a></li>
    <li class="k_nav_002" id="two2" onclick="setTab('two',2,7)"><a href="#" id="img2">销售管理</a></li>
    <li class="k_nav_003" id="two3" onclick="setTab('two',3,7)"><a href="#" id="img3">财务管理</a></li>
    <li class="k_nav_004" id="two4" onclick="setTab('two',4,7)"><a href="#" id="img4">人员管理</a></li>
    <li class="k_nav_005" id="two5" onclick="setTab('two',5,7)"><a href="#" id="img5">公告管理</a></li>
    <li class="k_nav_006" id="two6" onclick="setTab('two',6,7)"><a href="#" id="img6">促销培训</a></li>
    <li class="k_nav_007" id="two7" onclick="setTab('two',7,7)"><a href="#" id="img7">系统管理</a></li>
     -->
  </ul>
  <div style="position:absolute;top:70px;right:10px;"><a href="javascript:void(0);" onclick="this.href=$('#mainFrame').attr('src');" target="_blank" class="target-top"><img src="${ctx}/styles/admin-index/images/new-window.png" alt="新窗口打开" title="新窗口打开" /></a></div>
</div>
<!--栏目1-->
<div class="mian" id="con_two_1">
  <c:forEach var="cur1" items="${sysModuleList_1}" varStatus="vs1">
  	<div id="left_contcont_${vs1.count}" class="left_contcont" style="display:${vs1.first ? '' : 'none'}">
  		<c:set var="last_level" value="1" />
  		<c:forEach var="cur" items="${sysModuleList}" varStatus="vs">
  			<c:if test="${cur.root_id eq cur1.mod_id}">
			    <c:if test="${cur.tree_level eq 2}">
			    	<c:if test="${last_level eq 4}"></div><!-- sub_2 end. --></div><!-- sub_1 end. --></c:if>
			    	<c:if test="${last_level eq 3}"></div><!-- sub_1 end. --></c:if>
	  				<div class="left4">
				      <div style="float:right;"><span>+</span></div>
				      <div style="float:left">
				      <c:if test="${empty cur.mod_url}"><a href="javascript:void(0);">${cur.mod_name}</a></c:if>
			    	  <c:if test="${not empty cur.mod_url}">
				        <a href="${cur.mod_url}${fn:indexOf(cur.mod_url, '?') > -1 ? '&' : '?'}mod_id=${cur.mod_id}" target="mainFrame">${cur.mod_name}</a>
				      </c:if></div>
				    </div>
			    </c:if>
			    <c:if test="${cur.tree_level eq 3}">
			    	<c:if test="${last_level eq 4}"></div><!-- sub_2 end. --></c:if>
			    	<c:if test="${last_level eq 2}"><div class="sub_1" style="display:none;"></c:if>
			    	<c:if test="${empty cur.mod_url}">
			    		<div class="ys_li_b"><span>+</span><a href="javascript:void(0);">${cur.mod_name}</a></div>
			    	</c:if>
			    	<c:if test="${not empty cur.mod_url}">
			    		<div class="ys_li_b"><span>+</span><a href="${cur.mod_url}${fn:indexOf(cur.mod_url, '?') > -1 ? '&' : '?'}mod_id=${cur.mod_id}" target="mainFrame">${cur.mod_name}</a></div>
			    	</c:if>
			    </c:if>
			    <c:if test="${cur.tree_level eq 4}">
			    	<c:if test="${last_level eq 3}"><div class="sub_2" style="display:none;"></c:if>
			    	<c:if test="${empty cur.mod_url}">
			    		<div class="ys_li_b1"><a href="javascript:void(0);">${cur.mod_name}</a></div>
			    	</c:if>
			    	<c:if test="${not empty cur.mod_url}">
			    		<div class="ys_li_b1"><a href="${cur.mod_url}${fn:indexOf(cur.mod_url, '?') > -1 ? '&' : '?'}mod_id=${cur.mod_id}" target="mainFrame">${cur.mod_name}</a></div>
			    	</c:if>
			    </c:if>
			    <c:set var="last_level" value="${cur.tree_level}" />
  			</c:if>
  		</c:forEach>
    	<c:if test="${last_level eq 4}"></div><!-- sub_2 end. --></div><!-- sub_1 end. --></c:if>
    	<c:if test="${last_level eq 3}"></div><!-- sub_1 end. --></c:if>
  	</div><!-- left_contcont_${vs1.count} end -->
  </c:forEach>
  <div id="right_contcont" class="right_contcont">
    <iframe name="mainFrame" id="mainFrame" src="Frames2.do?method=main&url=${url}" frameborder="0" width="100%" scrolling="no" style="margin:0;padding:0;" onload="resizeFrameHeight('mainFrame', -2, $(window).height() - $('#top_banner').outerHeight() - $('#navbar').outerHeight());" onresize="this.onload();"></iframe>
  </div>
</div>
<!--<div class="di">康佳集团版板所有  　　   </div>--> 

<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
String.prototype.startsWith	= function(prefix){ return this.indexOf(prefix) === 0 };
$(function(){
	
	var $last_sub1;
	$(document).delegate(".left40, .left4", "click", function(){
		if ($last_sub1 != undefined) {
			$last_sub1.hide().prev().find("span").text("+");
		} 
		
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
		$last_sub1 = $next;
		
		open($(this).find('a').attr('href'));
	});
	
	$(".left40, .left4").each(function(){
		if($(this).next('.sub_1').length == 0)	$(this).find('span').remove();
	});
	$(".ys_li, .ys_li_b").each(function(){
		if($(this).next('.sub_2').length == 0)	$(this).find('span').remove();
	});
	
	$(document).delegate(".left40 span, .left4 span", "click", function(){
		switch($(this).text()){
			case '+' :
				var $next = $(this).parent().parent().next('.sub_1');
				if($next.is(':visible')) { 
					$next.hide();
					$(this).text('+');
				} else {
					$next.show();
					$(this).text('-');
				}
				break;
			case '-':
				var $next = $(this).parent().parent().next('.sub_1');
				if($next.is(':visible')) { 
					$next.hide();
					$(this).text('+');
				} else {
					$next.show();
					$(this).text('-');
				}
				break;
		}
	});
	
	var $last_sub2;
	$(document).delegate(".ys_li, .ys_li_b", "click", function(){
		if ($last_sub2 != undefined) {
			$last_sub2.hide().prev().attr('class', 'ys_li_b').find("span").text("+");
		}
		
		var $next = $(this).next('.sub_2');
		$next.is(':visible') ? $next.hide() : $next.show();
		
		$last_sub2 = $next;

		//ys_li down , ys_li_b up
		if ($last_sub2.length > 0) {
			$(this).attr('class', 'ys_li').find("span").text("-");
		}
		
		open($(this).find('a').attr('href'));
	});
	
	var $last_b2;
	$(document).delegate(".ys_li_b1, .ys_li_b2", "click", function(){
		if ($last_b2 != undefined) {
			$last_b2.attr('class', 'ys_li_b1');
		}
		$last_b2 = $(this).attr('class', 'ys_li_b2');
		
		open($(this).find('a').attr('href'));
	});
	
	$(document).delegate(".left40 a, .left4 a", "click", function(){
		$(this).parent().parent().click();
	});
	$(document).delegate(".ys_li a, .ys_li_b a, .ys_li_b1 a, .ys_li_b2 a", "click", function(){
		$(this).parent().click();
	});
	
	$(document).delegate("#top_banner a:not(.target-top)", "click", function(){
		open($(this).attr("href"));
		//$(this).parent().click();
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
	};
	chHeight();
	$(window).resize(chHeight);

});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
