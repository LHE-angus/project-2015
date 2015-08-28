<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<title>家电销售类型选择 - 买卖商通信息管理平台</title>
<link type="text/css" rel="stylesheet" href="${ctx}/styles/jxc/css/global.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/styles/jxc/css/konka.css" />
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：产品销售 &gt; 销售登记</div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp"%>
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td align="center" style="padding-top:5px;"><ul class="three">
            <c:if test="${empty not_show}">
              <c:if test="${af.map.jdxx}">
                <li><a href="javascript:go('JxcSellBill.do?method=goToJdxxSell&keySeq=${af.map.keySeq}');"><img src="${ctx}/styles/jxc/images/1.gif" width="519" height="94" onmousemove="this.src='${ctx}/styles/jxc/images/1_1.gif'" onmouseout="this.src='${ctx}/styles/jxc/images/1.gif';" /></a></li>
              </c:if>
              <c:if test="${af.map.yjhx}">
                <li><a href="javascript:go('JxcSellBill.do?method=goToYjhxSell&keySeq=${af.map.keySeq}');"><img src="${ctx}/styles/jxc/images/2.gif" width="519" height="94" onmousemove="this.src='${ctx}/styles/jxc/images/1_2.gif'" onmouseout="this.src='${ctx}/styles/jxc/images/2.gif';" /></a></li>
              </c:if>
            </c:if>
              <li><a href="javascript:go('JxcSellBill.do?method=add&keySeq=${af.map.keySeq}');"><img src="${ctx}/styles/jxc/images/3.gif" width="519" height="94" onmousemove="this.src='${ctx}/styles/jxc/images/1_3.gif'" onmouseout="this.src='${ctx}/styles/jxc/images/3.gif';" /></a></li>
              <c:if test="${not empty af.map.hasWds}">
                <li><a href="javascript:go('JxcSellBill.do?method=addForFx&keySeq=${af.map.keySeq}');"><img src="${ctx}/styles/jxc/images/fx_2.gif" width="519" height="94" onmousemove="this.src='${ctx}/styles/jxc/images/fx_1.gif'" onmouseout="this.src='${ctx}/styles/jxc/images/fx_2.gif';" /></a></li>
              </c:if>
          </ul></td>
      </tr>
    </table>
    
    
  </div>
</div>
	
<c:if test="${(not empty isLocal) and (isLocal)}" var="isbd"> </c:if>
<c:if test="${not isbd}"> </c:if>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.jxc.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function (){
	
	/* author Li,Ka 
	 * date 2012-03-09
	 * desc 网点情报站弹出窗口
	*/
	//setTimeout("showNewWindow()",1000);
});

/* author Li,Ka 
* date 2012-03-09
 * desc 网点情报站弹出窗口
*/
function showNewWindow(){
	try {
		external.OpenForm("http://www.mymyty.com/mmall/autoLogin.do?keySeq=${af.map.keySeq}&t=" + new Date().getTime(), "网点情报站简介", 410, 250 , 0);
	} catch(err){
		alert("系统无法打开通知窗口，请下载最新版本！");
	}
}

function go(url){
	location.href = url;
}

//]]></script>
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
