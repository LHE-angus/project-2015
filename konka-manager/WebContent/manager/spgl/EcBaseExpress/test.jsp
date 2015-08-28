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
<body>
<div class="oarcont">
  <div style="width: 500px;height:200px;background-color: red;">
    	<iframe id="mainframe" name="mainframe" src="?method=test2" frameBorder="0" width="100%" scrolling="auto" height="100%" 
onload="document.all('mainframe').style.height=mainframe.document.body.scrollHeight+30;"> </iframe>  
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	alert(document.body.clientWidth);
	alert(document.body.clientHeight);
	alert(document.body.offsetWidth);
	alert(document.body.offsetHeight);
	alert(document.body.scrollWidth);
	alert(document.body.scrollHeight);
	alert(document.body.scrollTop);
	alert(document.body.scrollLeft); 
	
	//$("#btn5").click(function(){
		//f_creatediv(1); 
	//});
});

function f_creatediv(divcnt){
	  for(var i=0;i<divcnt;i++){
	   var objdiv = document.createElement("DIV");  
	   var objname="shop_" + i;
	   objdiv.id = objname;
	   objdiv.style.top = 100 * i + 100;
	   objdiv.style.left = 100 * i + 100;
	   objdiv.style.background = '#FFFF00';
	   objdiv.style.visibility = 'visible';
	   objdiv.style.width = 100;
	   objdiv.style.height = 80;
	   objdiv.style.border = "5 groove black";
	   strHtml = "<ul style=\"list-style:none;margin:0px;padding:0px;width:100%\">\n"; 
	   strHtml += " <li style=\"background:#DD828D;text-align:left;padding-left:20px;font-size:14px;font-weight:bold;height:25px;line-height:25px;border:1px solid #F9CADE;\">[自定义提示]</li>\n"; 
	   strHtml += " <li style=\"background:#fff;text-align:center;font-size:12px;height:120px;line-height:120px;border-left:1px solid #F9CADE;border-right:1px solid #F9CADE;\">测试弹出框</li>\n"; 
	   strHtml += " <li style=\"background:#FDEEF4;text-align:center;font-weight:bold;height:25px;line-height:25px; border:1px solid #F9CADE;\"><input type=\"button\" value=\"确 定\" onclick=\"doOk()\" /></li>\n"; 
	   strHtml += "</ul>\n"; 
	   objdiv.innerHTML = strHtml; 
		
	  // objdiv.innerHTML="SHOP_" + i;
	   document.body.appendChild(objdiv);
	   document.getElementById(objname).onmouseover = function()
	   {
	    // alert(this.id);
	   };
	   this.doOk = function(){ 
		   objdiv.style.display = "none";   
	   }; 
	}
}


function setOnlyInt(obj) {
	$(obj).css("ime-mode", "disabled");
	$(obj).attr("t_value", "");
	$(obj).attr("o_value", "");
	$(obj).bind("dragenter",function(){
		return false;
	});
	$(obj).keypress(function (){
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).keyup(function (){
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).blur(function (){
		if(!obj.value.match(/^(?:\d+(?:\d+)?|\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\d+$/))obj.value=obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
		if(obj.value.length == 0 || isNaN(obj.value) || obj.value == 0) obj.value = "0";
	});
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
