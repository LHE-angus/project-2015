<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>leftFrame</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/scripts/global.js"></script>
<style> 
html { overflow-x: hidden; overflow-y:scroll; }
</style> 
</head>
<body>
<div class="leftcont">
  <div class="oalcont" id="leftbar" style="display:block;width:500px;">
    <div class="leftit1"><img src="${ctx}/images/manager/leftit1.gif" alt="系统菜单" width="167" height="26" /></div>
    <div class="oalcont" style="overflow-x:hidden;">
		<c:forEach var="cur1" items="${sysModuleList}">
			<c:if test="${cur1.par_id eq 0}">
				<div id="1_${cur1.mod_id}" class="oalnav_c" onclick="toggle($(this), '2_${cur1.mod_id}', 'oalnav_c2', 'oalnav_c')"><a>${cur1.mod_name}</a></div>
				<input id="mode_id" style="display:none;" value="${cur1.mod_id}" class="mode_id"/>
				<div id="2_${cur1.mod_id}">
					<c:forEach var="cur2" items="${sysModuleList}">
						<c:if test="${cur1.mod_id eq cur2.par_id}">
							<c:if test="${empty cur2.mod_url}">
								<div onclick="toggle($(this), '3_${cur2.mod_id}', 'oalnav_cur', 'oalnav')" class="oalnav">${cur2.mod_name}</div>
								<c:forEach var="cur3" items="${sysModuleList}">
									<c:if test="${cur2.mod_id eq cur3.par_id}">
										<div id="3_${cur3.par_id}_${cur3.mod_id}" class="oaltab">
											<div>
												<c:if test="${empty cur3.mod_url}">
													<div class="L1c" onclick="toggle($(this), '4_${cur3.mod_id}', 'L1', 'L1c')"><a href="javascript:void(0);"><span> ${cur3.mod_name}</span></a></div>
													<ul class="U1">
													<c:forEach var="cur4" items="${sysModuleList}">
								       					<c:if test="${cur3.mod_id eq cur4.par_id}">
								       						<li class="L21" id="4_${cur4.par_id}_${cur4.mod_id}" onclick='openMain("${cur4.mod_url}","mod_id=${cur4.mod_id}", this);'><a href="javascript:void(0);"><span> ${cur4.mod_name}</span></a></li>
								       					</c:if>
													</c:forEach> 
													</ul>
												</c:if>
												<c:if test="${not empty cur3.mod_url}">
													<div class="L1c" onclick='openMain("${cur3.mod_url}", "mod_id=${cur3.mod_id}", this);'><a href="javascript:void(0);"><span> ${cur3.mod_name}</span></a></div>
												</c:if>
												<div class="clear"></div>
											</div>
									    </div>
									</c:if>
								</c:forEach>
							</c:if>
							<c:if test="${not empty cur2.mod_url}">
								<div onclick='openMain("${cur2.mod_url}", "mod_id=${cur2.mod_id}", this);' class="oalnav_cur">${cur2.mod_name}</div>
							</c:if>
						</c:if>
			      	</c:forEach>
				</div>
	      	</c:if>      
      	</c:forEach>
      	<div class="clear"></div>
    </div>
    <div class="clear"></div>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	allLevelHide();
	
	if($("#mode_id").val()==500000){
		$("#1_500000").click();
	}
	$("#1_100000").click();

	$("#1_800000").click(); // 专卖店默认展开一级目录
});

function toggle($t, _id, cur_class, bef_class){
	if($t.attr("class") == bef_class) {// 展
		$t.removeClass(bef_class).addClass(cur_class);
		$("#[id^=" + _id + "]").show();
	} else {// 收
		$t.removeClass(cur_class).addClass(bef_class);
		$("#[id^=" + _id + "]").hide();
	}
}


function openMain(a, url, b){
	if(a != null){
		a = a.indexOf("?") != -1 ? (a + "&" + url) : (a + "?" + url);
		parent.mainFrame.location = a;
	}
	setBgColor(b);
}


function allLevelHide(){
	$("#[id^='2_'],#[id^='3_'],#[id^='4_']").each(function(){
		$(this).hide();
	});
}


var cur_li = "";
$("#[id^='4_']").mouseover(function(){
	$(this).addClass("licurnav");
}).mouseout(function(){
	if(this.getAttribute("id") != cur_li) {
		$(this).removeClass("licurnav"); 
	}
}); 

function setBgColor(o){
	if(cur_li != $(o).attr("id")) {
		$("#" + cur_li).removeClass("licurnav");
	}
	
	cur_li = $(o).attr("id");
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>