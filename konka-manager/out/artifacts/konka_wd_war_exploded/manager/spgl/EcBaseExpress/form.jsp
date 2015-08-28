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
  <div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/spgl/EcBaseExpress" method="post">
      <html-el:hidden property="express_id" styleId="express_id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <c:set var="readOnly"  value="${empty af.map.express_id?false:true}" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>快递公司名称：</td>
          <td width="88%" align="left"><html-el:text property="express_name" styleId="express_name" size="25" maxlength="15"/>
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>快递公司套打URL：</td>
          <td width="88%" align="left"><html-el:text property="express_model" styleId="express_model" size="30" maxlength="50"/></td>
        </tr>
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>物流类型：</td>
          <td width="88%" align="left">
          	<html-el:select property="express_ui_type" styleId="express_ui_type" disabled="${readOnly}">
          				<html-el:option value="">-请选择-</html-el:option>
          				<html-el:option value="1">顺丰物流</html-el:option>
          				<html-el:option value="100">其他物流</html-el:option>
          	</html-el:select>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><html-el:button property="" value="提交" styleClass="but4" styleId="btn_submit" />
         <!--   <html-el:button property="" value="测试创建div" styleClass="but4" styleId="btn5" /> -->
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();return false;" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#express_name").attr("datatype", "Require").attr("msg", "请填写快递公司名称");
	$("#express_model").attr("dataType", "Url").attr("msg", "请按有效格式填写URL，如https://www.alipay.com").attr("require", "true");
	$("#express_ui_type").attr("datatype", "Require").attr("msg", "请选择物流类型");

	//f_creatediv(1); 
	
	$("#btn_submit").click(function(){
		var isSubmit = Validator.Validate(this.form, 3);
		if (isSubmit) {
			$(":button").attr("disabled", "true");
			$(":reset").attr("disabled", "true");
			this.form.submit();
		}
	});

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
