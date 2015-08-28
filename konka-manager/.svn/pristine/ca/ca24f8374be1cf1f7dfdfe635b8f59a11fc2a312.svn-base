<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.webinput {
	background:#f5f4f4;
	padding-left: 5px;
	height: 19px;
	line-height: 19px;
	/*font-family: Arial, Helvetica, sans-serif;*/
	border: #ccc solid 1px;
}
ul.ckUl{list-style-type:none;display:inline;}
ul.ckUl li{float:left;margin:auto 5px auto 0px;/*padding:2px 5px;*/}
input,textarea,select{font-family:Microsoft Yahei;font-size:12px;}
.ck-li{position:relative;}
.ck-li .hidden-accessible{position:absolute !important;clip:rect(1px 1px 1px 1px);}
.ck-li .ck-default{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #CCC;background: #F6F6F6;font-weight: bold;color:#C4C4C4;cursor:pointer;}
.ck-li .ck-hover{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #FBCB09; background:#FDF5CE;font-weight: bold;color:#C77405;cursor:pointer;}
.ck-li .ck-visited{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:2px solid #EF0F28/*#FF4800/*FBD850*/; background:white url("${ctx}/styles/customer/images/ck-visited.gif") right bottom no-repeat;font-weight:bold;color:#EF0F28/*#FF4800/*#EB8F00*/;cursor:pointer;}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont" id="body_oarcont">
	<div class="oartop">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
				<td nowrap="nowrap">当前位置：${naviString}</td>
			</tr>
		</table>
	</div>
	<div class="rtabcont1">
		<html-el:form action="/spgl/EcGift">
			<html-el:hidden property="method" value="list" />
			<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
			 <html-el:hidden property="own_sys" value="${af.map.own_sys}" />
			<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
				<tr>
					<td height="36" align="left" style="padding-left:5px;">
		        		&nbsp;商品编码：
		        		<html-el:text property="pd_sn_like" styleId="pd_sn_like" styleClass="webinput" />
		        		&nbsp;商品名称：
		        		<html-el:text property="pd_name_like" styleId="pd_name_like" styleClass="webinput" />
		        		&nbsp;&nbsp;
						<input name="button" type="submit" class="bgSearch" id="button" value="搜 索" style="font-family:Microsoft YAHEI;" />
		        	</td>
				</tr>
			</table>
		</html-el:form>
	</div>
	<div class="rtabcont1">
		<%@ include file="/commons/pages/messages.jsp"%>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<input type="button" class="but2" value="新 增" onclick="location.href='EcGift.do?method=add&mod_id=${af.map.mod_id}&own_sys=${af.map.own_sys}';" />
				</td>
			</tr>
		</table>		
	</div>
	<div class="rtabcont1">
	    <c:set var="now"><fmt:formatDate value="${today}" pattern="yyyy-MM-dd" /></c:set>
		<c:forEach items="${entityList}" var="cur" varStatus="vs">
			<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
				<tr>
					<td colspan="3" style="background:#f5f4f4">
						<div style="float:left;">商品名称：${fn:escapeXml(cur.pd_name)}</div>
						<div style="float:right;">商品编码：${fn:escapeXml(cur.pd_sn)}</div>
					</td>
				</tr>
				<tr>
		  			<td align="center" width="20%" rowspan="3" style="background:#FFFFFF;">
		  				<c:if test="${not empty cur.main_pic}">
			  				<c:set value="${fn:split(cur.main_pic, ',')[0]}" var="main_pic_path" />
			  				<img src="${ctx}/${fn:substringBefore(main_pic_path, '.')}_120.jpg" />
		  				</c:if>
		  			</td>
		  			<td width="15%" align="left" style="height:60px;">所需积分</td>
		  			<td align="left">${cur.need_integral}&nbsp;</td>
		  		</tr>
		  		<tr>
		  			<td align="left" style="height:60px;">市场价</td>
		  			<td align="left">${cur.original_price}</td>
		  		</tr>
		  		<tr>
		  			<td align="left" style="height:60px;">浏览次数</td>
		  			<td align="left">${cur.view_counts}</td>
		  		</tr>
			</table>
			<c:choose>
		  		<c:when test="${not empty cur.down_time}"><c:set var="downDate"><fmt:formatDate value="${cur.down_time}" pattern="yyyy-MM-dd" /></c:set></c:when>
		  		<c:otherwise><c:set var="downDate"></c:set></c:otherwise>
		  	</c:choose>
		  	<div style="margin:3px auto;">
		  		<div style="float:left;">
		  			<div style="float:left;">[
		  					<c:choose>
		  						<c:when test="${empty downDate}"><span style="color:#CD0000;">已下架</span></c:when>
		  						<c:otherwise>
		  							<c:if test="${now lt downDate}"><span style="color:#009900;">已上架</span></c:if>
		  							<c:if test="${now ge downDate}"><span style="color:#CD0000;">已下架</span></c:if>
		  						</c:otherwise>
		  					</c:choose>
		  				]&nbsp;
		  			</div>
		  		</div>
		  		<div style="float:right;">
		  			<a style="color: blue" href="javascript:loading();doNeedMethod(null, 'EcGift.do', 'edit', 'id=${cur.id}&own_sys=${af.map.own_sys}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">修改</a> | 
		  			<c:choose>
						<c:when test="${empty downDate}"><a style="color: blue" href="javascript:loading();doNeedMethod(null, 'EcGift.do', 'upShelf', 'id=${cur.id}&own_sys=${af.map.own_sys}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">上架</a> | </c:when>
						<c:otherwise>
							<c:if test="${now lt downDate}"><span class="fblue" style="color: blue"  onclick="doNeedMethod('确定下架此商品？', 'EcGift.do', 'offShelf', 'id=${cur.id}&own_sys=${af.map.own_sys}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">下架</span> | </c:if>
							<c:if test="${now ge downDate}"><a style="color: blue"  href="javascript:loading();doNeedMethod(null, 'EcGift.do', 'upShelf', 'id=${cur.id}&own_sys=${af.map.own_sys}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">上架</a> | </c:if>
						</c:otherwise>
					</c:choose> 
		  			<a style="color: blue"  href="javascript:loading();doNeedMethod(null, 'EcGift.do', 'view', 'id=${cur.id}&own_sys=${af.map.own_sys}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">详细</a> 
		  		</div>
		  	</div>
		  	<c:if test="${not vs.last}"><div style="height:40px;"></div></c:if>
		</c:forEach>
		<form id="bottomPageForm" name="bottomPageForm" method="post" action="EcGift.do">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="40">
						<div style="text-align: right; padding-right: 5px;"> 
							<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
							<script type="text/javascript">
								var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
								pager.addHiddenInputs("method", "list");
								pager.addHiddenInputs("mod_id", "${fn:escapeXml(af.map.mod_id)}");
								pager.addHiddenInputs("pd_sn_like", "${fn:escapeXml(af.map.pd_sn_like)}");
								pager.addHiddenInputs("pd_name_like", "${fn:escapeXml(af.map.pd_name_like)}");
								pager.addHiddenInputs("own_sys", "${af.map.own_sys}");
								document.write(pager.toString());
							</script> 
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>  
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	//alert("${popedom}");

	
});

	//$(this).keypress(function (){
		//if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value
	//}).keyup(function (){
		//if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value
	//}).blur(function (){
		//if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}
		//if(this.value.length == 0) this.value = "0";
	//});
	//this.text.selected;
	//}

function showCkboxEcho(name, value){
	var obj = document.getElementsByName(name);
	for ( var i = 0; i < obj.length; i++) {
		if (value == obj[i].value) {
			obj[i].checked = true;
			//obj[i].nextSibling.getElementsByTagName("span")[0].className = "ck-visited";
			var parId = obj[i].parentNode.id;
			$("span", "#" + parId).addClass("ck-visited");
		}
	}
}

function loading(){
	jLoading("&nbsp;&nbsp;正在加载数据...", {autoHide:false, HorizontalPosition:"center", VerticalPosition:"center", MinWidth:150});
}
//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>