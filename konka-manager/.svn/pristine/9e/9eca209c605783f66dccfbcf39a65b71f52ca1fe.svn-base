<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<!--<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />-->
<!--<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />-->
<!--<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />-->
<!--<link href="${ctx}/styles/jGrowl.css" rel="stylesheet" type="text/css" />-->
<style type="text/css">
body {
	font:12px/20px "宋体","\5b8b\4f53",sans-serif;
	color:#1e3257;
	overflow-x:hidden;
	/*background-color: #ffffff; 
	color: #336699;*/ 
}
	
}
.bgButton {background:url('${ctx}/images/manager/butbg1.gif') repeat-x;border:1px #c4c4c4 solid;font:normal 12px/20px "宋体";color:#333;padding: 0px 1px 0px 1px !important;padding: 0px 1px 0px 1px;letter-spacing: 1px;font-size:12px; cursor:pointer;}
a {text-decoration: none;color:#1e3257;}
a:link {text-decoration: none;color:#1e3257;}
a:visited {text-decoration: none;color:#1e3257;}
a:hover {text-decoration: none;color:#FF9900;}

.datagrid {
	width: 100%;
	/*background-color: #6595d6;*/
}
.datagrid caption {
}
.datagrid tr {
}
.datagrid td {
	padding: 5px;
	line-height: 20px;
	/*background-color: #fff;*/
	border: 1px solid #FCF6EA;
}
/* css for OEC form page*/
/*.row_focus {
	background: #B0FFB0;
	border: 1px solid #00cc33;
}
/* css or table row effect */
tr.alt td {
	/*background: #ecf6fc;*/
	background: #FCF6EA;
}
tr.over td {
	/*background: #bcd4ec;*/
	/*background: #CAE1FF;*/
	background-color: #E6F5FF;
	border: 1px solid #CAE1FF;
}
tr.select td{
	background-color:#FFE2A2;
	border:1px solid #FFCC00;
}
</style>
</head>
<body bgcolor="#FCF6EA">
	<div style="overflow-y:auto;height:88%;width:100%;position:fixed;top:0;">
		<table id="msgTb" width="90%" align="center" border="0" cellpadding="0" cellspacing="1" class="datagrid" style="">
			<c:forEach items="${myMessageList}" var="cur" varStatus="vs">
				<tr>
					<td id="td_${vs.count}" onclick="openMessage('KonkaXxMessage.do?method=view&mod_id=${af.map.mod_id}&id=${cur.id}', $(this).parent(), '${cur.state}')" style="cursor:pointer;">
						<div style="float:left;">
<!--							<a href="" target="msgMainFrame">-->
							<a href="javascript:void(0);">
							<span id="span">${cur.msg_title}</span><br />
							<font color="#9a9a9a"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" /></font>
							</a>
						</div>
						<div style="float:right;vertical-align:middle;margin-right:15px;"><img src="${ctx}/styles/message/images/msg_${cur.state}.png" width="32" height="32" title="${cur.state eq 0?'未读':'已读'}" /></div>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty myMessageList}">
				<div align="center" style="margin-top:130px;">您目前没有消息。</div>
			</c:if>
		</table>
	</div>
	<c:if test="${af.map.pager.recordCount ne 0}">
		<div style="position:fixed;bottom:0;background-color:#CAE1FF;width:100%;height:12%">
			<form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxMessage.do">
		    	<table id="tab_4" width="98%" border="0" cellpadding="0" cellspacing="0">
		      		<tr>
		        		<td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
			          		<script type="text/javascript">
					            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage}, "msg");
					            pager.addHiddenInputs("method", "list");
					            pager.addHiddenInputs("mod_id","${af.map.mod_id}");
								pager.addHiddenInputs("user_id", "${sessionScope.userInfo.id}");	
			            		document.write(pager.toString());
			            	</script>
		            	</td>
		      		</tr>
		    	</table>
		  	</form>
	  	</div>
	</c:if>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery1.5.1.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(".datagrid tr").mouseover(function(){  
		$(this).addClass("over");
	}).mouseout(function(){
		$(this).removeClass("over");
	}).mouseup(function(){
		$(this).removeClass("over");
	});
	//$(".datagrid tr:even").addClass("alt");

	<c:forEach items="${myMessageList}" var="cur" varStatus="vs">
		var state = "${cur.state}";
		var vs = "${vs.count}";
		if (state == "1") {
			$("#span", "#td_" + vs).css("color", "#9a9a9a");
		}
	</c:forEach> 
});

function openMessage(url, obj, state){
	//alert(url);
	parent.msgMainFrame.location = url;

	$("tr", "#msgTb").removeClass("select");
	obj.removeClass("over");
	obj.addClass("select");

	if ("0" == state) {//消息未读
		$("#span", obj).css("color", "#9a9a9a");
		$("img", obj).attr("src", "${ctx}/styles/message/images/msg_1.png").attr("title", "已读");
	}
}
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>