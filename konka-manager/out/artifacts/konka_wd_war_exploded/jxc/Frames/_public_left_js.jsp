<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[ 
$(document).ready(function(){
	var tdJdId = "";
	$("td", "#leftFramesTable").mouseover(function(){
		$(this).addClass("oalnav_cur");
	}).mouseout(function(){
		$(this).removeClass("oalnav_cur");
		if ("" != tdJdId) {
			$("#" + tdJdId).addClass("oalnav_cur");
		}
	});
	var keySeq = "";
	<c:if test="${isLocal}">
	keySeq = "${JXC_TEST_KEY_VALUE}";
	</c:if>
	<c:if test="${not isLocal}">
	keySeq = external.GetPrecis('UsbKey');
	</c:if>
	
	$("a").click(function(){ 
		var _this = $(this);
		var url = _this.attr("href");
		tdJdId = _this.attr("name");
		if(url.indexOf('keySeq') == -1){
			if (url.indexOf('?') > -1) {
				url = url + "&keySeq=" + keySeq;
			} else {
				url = url + "?keySeq=" + keySeq;
			}
		}
		
		_this.attr("href", url);

		$("td", "#leftFramesTable").removeClass("oalnav_cur");
		_this.parent().addClass("oalnav_cur");
	});
	<c:if test="${not empty af.map.clickHrefName}">
	$("a[name='${af.map.clickHrefName}']").click();
	</c:if>
});
//]]></script>
<jsp:include page="../public_page.jsp" flush="true" />