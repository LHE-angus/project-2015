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
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont" id="body_oarcont">
	<div class="rtabcont1"> 
		<html-el:form action="/admin/KonkaInterfaceIp">
			<html-el:hidden property="method" value="selectLicenses_sn" />
			<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
			<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
				<tr>
					<td height="36" align="left" style="padding-left:5px;">
		        		&nbsp;授权码：
		        		<html-el:text property="licenses_sn_like" styleId="licenses_sn_like" styleClass="webinput" />
		        		&nbsp;&nbsp;
						<input name="button" type="submit" class="bgSearch" id="button" value="搜 索" style="font-family:Microsoft YAHEI;" />
		        	</td>
				</tr>
			</table>
		</html-el:form>
	</div>
	<div class="rtabcont1">
			<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
				<tr class="tabtt1">
		           <td width="5%" nowrap="nowrap" align="center">序号</td>
		           <td nowrap="nowrap" align="center">授权码</td>
		            <td nowrap="nowrap" align="center">授权单位</td>
		           <td nowrap="nowrap" width="15%" align="center">操作</td>
          		</tr>
				<c:forEach items="${entityList}" var="cur" varStatus="vs">
				<tr>
					<td align="center" nowrap="nowrap">${vs.count}</td>
					<td align="center" nowrap="nowrap">${cur.licenses_sn}</td>
					<td align="center" nowrap="nowrap">${cur.org_name}</td>
					<td align="center" nowrap="nowrap"><a href="javascript:selectPd('${cur.licenses_sn}');">选择</a></td>
				</tr>
  				</c:forEach>
			</table>
	  		<c:if test="${not vs.last}"><div style="height:40px;"></div></c:if>
		<form id="bottomPageForm" name="bottomPageForm" method="post" action="SelectKonkaBcompPd.do">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="40">
						<div style="text-align: right; padding-right: 5px;"> 
							<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
							<script type="text/javascript">
								var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
								pager.addHiddenInputs("method", "list");
								pager.addHiddenInputs("mod_id", "${fn:escapeXml(af.map.mod_id)}");
								pager.addHiddenInputs("licenses_sn_like", "${fn:escapeXml(af.map.licenses_sn_like)}");
								document.write(pager.toString());
							</script> 
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>  
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	
});

function selectPd(licenses_sn){
	window.opener.set_value(licenses_sn);
	window.close();
}

//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>