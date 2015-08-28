<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
textarea {
	width: 527px;
	overflow-y: hidden;
	background: transparent;
}
.title {
	background-color:#eee;
	text-align:right;
}
span.desc {color:#848484;font-size:12px;margin-left:1em;}
div.desc {color:#848484;font-size:12px;}

#to_ul {height:30px;width:750px;list-style-type:none;}
#to_ul li {width:250px;float:left;}

-->
</style>
</head>
<body>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<div class="oarcont" style="margin-bottom: 100px;">
	<div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：邮件发送</td>
      </tr>
    </table>
  </div>
 	<div class="rtabcont2">
    <html-el:form action="/admin/KonkaSendMailInfo" >
      <html-el:hidden property="id" value="${af.map.id}" />
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="page_flag" />
      <html-el:hidden property="queryString" />
      <html-el:hidden styleId="info_mod_id" property="info_mod_id" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td colspan="2" class="item_class" align="left" ><strong class="fb">邮件发送</strong></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="center" width="15%"><span style="color: #F00;">* </span>接收人：</td>
          <td width="80%"><ul id="to_ul">
	           <c:forEach var="cur" items="${toList}" varStatus="vs">
	           <li><input type="checkbox" name="tos" value="${cur.email}" checked="true">${cur.real_name}&lt;${cur.email}&gt; </input></li>
	           </c:forEach> 
	           </ul>
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="center">抄送：</td>
          <td><ul  id="to_ul">
	           <c:forEach var="cur" items="${ccList}" varStatus="vs">
	           <li><input type="checkbox" name="ccs" value="${cur.email}" checked="true">${cur.real_name}&lt;${cur.email}&gt;</input></li>
	           </c:forEach> 
	           </ul>
	       </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="center"><span style="color: #F00;">* </span>标题：</td>
          <td><html-el:text property="title" styleId="title" maxlength="200"  style="width:400px" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="center"> 内容：</td>
          <td><FCK:editor instanceName="content" width="90%" height="350">
              <jsp:attribute name="value">${af.map.content}</jsp:attribute>
            </FCK:editor>
           </td>
        </tr> 
        <tr>
          <td colspan="2" align="center"><input id="save_button" class="but4" type="button" name="save" value="发送" onclick="if(checkSubmit(this))this.form.submit();" />
           <input type="button" class="but5" name="return" value="返回 " onclick="history.back();" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	initStyle();
	$("input[name='real_name']").attr("dataType" , "Require").attr("msg" , "标题没有填写");
	$("input[name='email']").attr("dataType" , "Email").attr("msg" , "邮箱格式不正确");
	
	$("input[name='order_value']").focus(setOnlyNum);
 });

String.prototype.getBytesLength = function() {    
    return this.replace(/[^\x00-\xff]/gi, "--").length;    
}
function checkSubmit(obj) {
	var f = document.forms[0]; 
	
	var isSubmit = Validator.Validate(f, 3);
	if (!isSubmit) return false;
	 

	$("#save_button").attr("disabled", "true").val("正在提您的请求...");
	$(":submit").attr("disabled", "true")
	$(":button").attr("disabled", "true");
	$(":reset").attr("disabled", "true");
	
	return true;
}

function setOnlyNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	})
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}
		if(this.value.length == 0) this.value = "0";
	})
	//this.text.selected;
}

function initStyle(){
	$("input[@type='text'][readonly],input[@type='password'][readonly],textarea[readonly]").css({color:"#999"});

	// input
	$("input[type='text']").focus(function(){
		$(this).css({"background-color":"#FFFF99", "border":"1px solid #3399FF"})
	}).blur(function(){
    	$(this).css({"background-color":"#FFF", "border":"1px solid #CCC"});
    }).css({"background-color":"#FFF", "border":"1px solid #CCC"});

    $("textarea").not("[readonly]").focus(function(){
    	$(this).addClass("row_focus");
    }).blur(function(){
    	$(this).removeClass("row_focus");
    }).keyup(function(){
    	if (this.value.getBytesLength() > 260) {
    		$(this).css({"background-color":"#FFFF99"});
    	} else {
    		$(this).css({"background-color":""});
    	}
    });
    
	$("textarea").each(function(){
		this.onpropertychange = function () {
			if (this.scrollHeight > 16) this.style.posHeight = this.scrollHeight;
		};
	});
	$("input[type='radio'], label").mouseover(function(){$(this).css("cursor" , "pointer");});
}
//]]></script>

<jsp:include page="/__analytics.jsp" />
</body>
</html>