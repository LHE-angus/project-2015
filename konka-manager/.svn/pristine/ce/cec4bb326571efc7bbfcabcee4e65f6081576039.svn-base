<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont" id="body_oarcont">
	<div class="oartop">
	    <table width="100%" border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
	        <td>当前位置：${naviString}</td>
	        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
	      </tr>
	    </table>
  	</div>
  	<div class="rtabcont1">
  		<input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
  		<input class="but_excel" type="button" name="Submit5" value="导出" onclick="exportExcel();" />
  	</div>
  	<div class="rtabcont1" style="overflow-x:auto;">
  		<div id="divExcel" title="表查询">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
			<tr class="tabtt1">
				<td width="5%" nowrap="nowrap" align="center">序号</td>
				<c:forEach items="${diplay_name_list}" var="cur">
					<td nowrap="nowrap" align="center">${fn:escapeXml(cur)}</td>
				</c:forEach>
				
			</tr>
			<c:forEach items="${entityList}" var="cur" varStatus="vs">
				<tr>
					<td align="center" nowrap="nowrap">${vs.count}</td>
					<c:forEach begin="0" end="${show_num-1}" var="num">
					<td align="center">${fn:escapeXml(cur[num])}</td>
					</c:forEach>
				</tr>
			</c:forEach>
		</table> 
		</div>
  	</div>

</div>

<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/print.js"></script> 
<script type="text/javascript">//<![CDATA[    

function exportExcel(){
	toExcel('divExcel', '?method=toExcel');
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>