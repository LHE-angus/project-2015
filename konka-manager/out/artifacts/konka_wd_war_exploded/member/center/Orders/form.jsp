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
        <td align="center"><input type="button" class="but2" value="关闭" id="btn_sub"  />
        </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
        <td nowrap="nowrap" width="10%" align="center">序号</td>
        <td nowrap="nowrap" align="center" >时间</td>
        <td nowrap="nowrap" align="center" >地点</td>
      </tr>
      <c:forEach items="${entityList}" var="cur" varStatus="vs">
        <tr>
        <td align="left" width="5%" nowrap="nowrap">${vs.count}</td>
          <td align="left" nowrap="nowrap" width="10%" >
            ${cur.accept_time}  
          </td> 
          <td align="left" nowrap="nowrap" width="10%" >
            ${cur.remark}  
          </td>
        </tr>
      </c:forEach>
    </table>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#btn_sub").click(function(){
		window.close();
	});

});

//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
