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
<style media="print">  
.Noprint  {DISPLAY:  none;}  
</style> 
</head>
<body>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<div class="oarcont">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div id="print" style="width:100%;height:100%;border: 1px #ccc solid " align="left" title="电子账单" style="margin-top:4px;">
    	<img src="${ctx}/images/SFCode.jpg?order_id=${af.map.id}" style="width:856px;height:720px"></img>
  </div>
  <div align="left" style="margin-left:10px;margin-bottom:20px;">
    <input class="but5" type="button"  value="返回" onclick="history.back();return false;" />
    <input class="but_excel" type="button"  value="打印" onclick="win_print();" />
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
});

function win_print(){
	prnhtml=$("#print").html();
	var oWin=window.open("","_blank");  

	 var strPrint="<style media=\"print\">\n"; 
    strPrint=strPrint + ".Noprint  {DISPLAY:  none;}\n"; 
    strPrint=strPrint + " </style> \n"; 
    strPrint=strPrint+" <div class='Noprint'> <h4 style=’font-size:18px; text-align:center;’>打印预览区</h4></div>\n";  
    strPrint=strPrint + "<script type=\"text/javascript\">\n";  
    strPrint=strPrint + "function printWin()\n";  
    strPrint=strPrint + "{";  
    strPrint=strPrint +    "var oWin=window.open(\"\",\"_blank\");\n";  
    strPrint=strPrint + "oWin.document.write(document.getElementById(\"content\").innerHTML);\n";  
    strPrint=strPrint + "oWin.focus();\n";  
    strPrint=strPrint + "oWin.document.close();\n";  
    strPrint=strPrint + "oWin.print()\n";  
    strPrint=strPrint + "oWin.close()\n";  
    strPrint=strPrint + "}\n";  
    strPrint=strPrint + "<\/script>\n";  
      
    strPrint=strPrint + "<hr size='1'/>\n";  
    strPrint=strPrint + "<div id=\"content\">\n";  
    strPrint=strPrint + prnhtml + "\n";  
    strPrint=strPrint + "</div>\n";  
    strPrint=strPrint + "<hr size='1' />\n";  
    strPrint=strPrint + "<div class='Noprint' style='text-align:center'><button onclick='window.print()' style='padding-left:4px;padding-right:4px;'>打  印</button><button onclick='window.opener=null;window.close();' style='padding-left:4px;padding-right:4px;'>关  闭</button></div>\n";  
    oWin.document.write(strPrint);  
    oWin.focus();  
    oWin.document.close();  
}

//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
