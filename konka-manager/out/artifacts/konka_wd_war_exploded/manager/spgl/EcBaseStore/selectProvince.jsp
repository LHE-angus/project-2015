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
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont" id="body_oarcont">
    <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><input type="button" class="but2" value="确认" id="btn_sub"  onclick="selectPd();" />
        </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
  	 <div style="border: 1px #ccc solid;">
  	 <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center">&nbsp;</td>
        <td nowrap="nowrap" align="center">省份名称</td>
        <td width="5%" nowrap="nowrap" align="center">&nbsp;</td>
        <td nowrap="nowrap" align="center">省份名称</td>
        <td width="5%" nowrap="nowrap" align="center">&nbsp;</td>
        <td nowrap="nowrap" align="center">省份名称</td>
        <td width="5%" nowrap="nowrap" align="center">&nbsp;</td>
        <td nowrap="nowrap" align="center">省份名称</td>
        <td width="5%" nowrap="nowrap" align="center">&nbsp;</td>
        <td nowrap="nowrap" align="center">省份名称</td>
      </tr>
      <tr>
        <c:set value="0" var="vs_count" />
      	<c:forEach items="${entityList1}" var="cur" varStatus="vs">
      		<c:set value="${1 + vs_count}" var="vs_count" />
      	    <td align="center"><input type="checkbox" class="tt" id="${cur.map.p_index}" checked="checked" /></td>
      	    <td><span id="tj_${cur.map.p_index}">${cur.map.p_name}</span></td>
      	    <c:if test="${vs_count mod 5 eq 0}"><c:out value="</tr><tr>" escapeXml="false" /></c:if>
      	</c:forEach>
      
       <c:forEach items="${entityList2}" var="cur" varStatus="vs2">
       	  <c:set value="${1 + vs_count}" var="vs_count" />
       	  <td align="center"><input type="checkbox" class="tt" id="${cur.map.p_index}" /></td>
       	  <td><span id="tj_${cur.map.p_index}">${cur.map.p_name}</span></td>
       	  <c:if test="${vs_count mod 5 eq 0 and not vs.last}"><c:out value="</tr><tr>" escapeXml="false" /></c:if>
       </c:forEach>
       <c:forEach begin="${vs_count mod 5}" end="4">
       	  <td>&nbsp;</td>
       	  <td>&nbsp;</td>
       </c:forEach>
     </tr>
	</table>
      </div>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

});

function selectPd(){
	var return_value = "";
	$(".tt").each(function(){
		if($(this).attr("checked")){
			var str = "";
			var id = this.id;
			return_value += $("#tj_"+id).text()+"@"+id;
			return_value = return_value + str + "##";
		}
	});
	window.opener.set_value(return_value);
	window.close();

}

//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
