<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>请选择买卖提网点</title>
<base target="_self" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<!--[if IE]>
<link href="${ctx}/styles/manager/ieHack.css" rel="stylesheet" type="text/css" />
<![endif]-->
</head>
<body>
<div class="oarcont" id="body_oarcont">
<div class="rtabcont1">
      <html-el:form action="/admin/SelectKonkaEntpShop">
        <html-el:hidden property="id" styleId="id"/>
        <html-el:hidden property="method" value="list" />
        <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
 		<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
       	 <tr>
       	    <td width="15"></td>
 	   <%--    <td>所属区域：
               <select name="province" id="province" style="width:180px;">
                  <option value="">-请选择省/直辖市/自治区-</option>
                </select>
                &nbsp;
                <select name="city" id="city" style="width:100px;" >
                  <option value="">-请选择市-</option>
                </select>
                &nbsp;
                <select name="country" id="country" style="width:100px;" >
                  <option value="">-请选择县-</option>
                </select>
 			</td>--%>
 			<td>网点名称：
              <html-el:text property="keyword" styleId="keyword" size="24" maxlength="100" style="width:100px;"/>
       	 </td>
       	 	<td>联系人：<html-el:text property="link_user_like" styleId="link_user_like" size="24" maxlength="30" style="width:100px;"/></td>
       	 	<td>联系电话：<html-el:text property="link_phone_like" styleId="link_phone_like" size="24" maxlength="30" style="width:100px;"/></td>
      	  <td><html-el:submit styleClass="but1" value="搜索" /></td>
        </tr>
       </table>
      </html-el:form>
    </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <form id="listForm" name="listForm" method="post" action="SelectKonkaEntpShop.do?method=delete">
        <input type="hidden" name="method" id="method" value="delete" />
        <input type="hidden" name="mod_code" id="mod_code" value="${af.map.mod_code}" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td width="30" align="center">序号</td>
          <td nowrap="nowrap" width="50%">网点名称</td>	
          <td nowrap="nowrap" width="30%">所属行业</td>	
          <td nowrap="nowrap" align="center" width="70">操作</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap"><c:out value="${vs.count}"/></td>
            <td align="left" nowrap="nowrap"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'SelectKonkaEntpShop.do', 'view', 'id=${cur.shop_id}&' + $('#bottomPageForm').serialize())">${fn:escapeXml(cur.shop_name)}</span></td>            	
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.c_index)}</td> 
            <td align="center"><html-el:button property="" value="选 择" styleClass="but4" styleId="btn_submit" onclick="choose(${cur.shop_id},'${cur.shop_name}');"/></td>
          </tr>
          <c:if test="${vs.last eq true}">
            <c:set var="i" value="${vs.count}" />
          </c:if>
        </c:forEach>
        <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
          <tr align="center">
            <td>&nbsp;</td>
            <td>&nbsp;</td>       	
            <td>&nbsp;</td>       	
            <td>&nbsp;</td>       	
          </tr>
        </c:forEach>
      </table>
    </form>
    <br />
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="SelectKonkaEntpShop.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("province", "${af.map.province}");
			pager.addHiddenInputs("city", "${fn:escapeXml(af.map.city)}");	
			pager.addHiddenInputs("country", "${fn:escapeXml(af.map.country)}");	
			pager.addHiddenInputs("keyword", "${fn:escapeXml(af.map.keyword)}");	
			pager.addHiddenInputs("link_user_like", "${fn:escapeXml(af.map.link_user_like)}");	
			pager.addHiddenInputs("link_phone_like", "${fn:escapeXml(af.map.link_phone_like)}");	
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
});

var request = false; 
var btype=getInternet();  
function getInternet()    
{    
	if(navigator.userAgent.indexOf("MSIE")>0) {    
              return "MSIE";       //IE浏览器  
}  
	if(isFirefox=navigator.userAgent.indexOf("Firefox")>0){    
              return "Firefox";     //Firefox浏览器  
}  
	if(isSafari=navigator.userAgent.indexOf("Safari")>0) {
		if(navigator.userAgent.indexOf("360EE")>0){
              return "360EE";      //360Chromium浏览器  
		}else{
              return "Safari";      //Safan (goole chrome)浏览器  
		}
}  
	if(isCamino=navigator.userAgent.indexOf("Camino")>0){    
              return "Camino";   //Camino浏览器  
}  
	if(isMozilla=navigator.userAgent.indexOf("Gecko/")>0){    
              return "Gecko";    //Gecko浏览器  
}    

}  

function choose(shop_id,shop_name){
	if(btype=='MSIE'){
	window.returnValue = {
			shop_id : shop_id,
			shop_name : shop_name
		};
	}else{
		window.opener.set_value(shop_id,shop_name);
	}
	window.close();
};

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>