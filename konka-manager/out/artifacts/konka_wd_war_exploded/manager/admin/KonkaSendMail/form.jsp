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
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  	</div>
    <div class="rtabcont2">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td nowrap="nowrap" class="title_item" align="center" width="15%"><c:if test="${af.map.info_type eq 11}">A类经办彩电销售业绩排名</c:if>
    	  <c:if test="${af.map.info_type eq 12}">B类经办彩电销售业绩排名</c:if>
    	  <c:if test="${af.map.info_type eq 13}">C类经办彩电销售业绩排名</c:if>
    	  <c:if test="${af.map.info_type eq 21}">彩电综合业绩排名（分公司结算）</c:if>
    	  <c:if test="${af.map.info_type eq 22}">彩电综合业绩排名（总部结算）</c:if>      
          报表发送的用户：</td>
          <td width="80%"><ul id="to_ul">
	           <c:forEach var="cur" items="${entityList}" varStatus="vs">
	           <li><label><c:if test="${cur.send_type eq 1}">主送</c:if><c:if test="${cur.send_type eq 2}">抄送</c:if>,${cur.real_name}&lt;${cur.email}&gt; </label><a id="att_del_${cur.id}" href="">删除</a></li>
	           </c:forEach> 
	           </ul>
          </td>
        </tr>
      </table>
  </div>
 	<div class="rtabcont2">
    <html-el:form action="/admin/KonkaSendMail" >
      <html-el:hidden property="id" value="${af.map.id}" />
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="page_flag" />
      <html-el:hidden property="queryString" />
       <html-el:hidden property="info_type" value="${af.map.info_type}" />
      <html-el:hidden styleId="info_mod_id" property="info_mod_id" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td colspan="2" class="item_class" align="left" ><strong class="fb">接收人添加</strong></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item"><span style="color: #F00;">* </span>姓　 名：</td>
          <td><html-el:text property="real_name" size="30" maxlength="130" />&nbsp; </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item"><span style="color: #F00;">* </span>邮　 箱：</td>
          <td><html-el:text property="email" size="30" maxlength="130" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">主送/抄送：</td>
          <td><label for="send_type_01">
              <html-el:radio styleId="send_type_01" property="send_type" value="1" />
          主送</label>
            <label for="send_type_02">
              <html-el:radio styleId="send_type_02" property="send_type" value="2" />
           抄送</label></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">状 　态：</td>
          <td><label for="state_01">
              <html-el:radio styleId="state_01" property="state" value="0" />
           不发送</label>
            <label for="state_02">
              <html-el:radio styleId="state_02" property="state" value="1" />
              发送</label></td>
        </tr>
       <tr>
          <td nowrap="nowrap" class="title_item">排 序 号：</td>
          <td><html-el:text property="order_value" size="4" maxlength="4" />
            <span class="desc">值越大，显示越靠前，范围：0-9999</span></td>
        </tr>
        <tr>
          <td colspan="2" align="center"><input id="save_button" class="but4" type="button" name="save" value="保存 " onclick="if(checkSubmit(this))this.form.submit();" />
            <input type="reset" name="reset" class="bgButtonReset" value="重填 " />
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

	$("a[id^='att_del_']").click(function() {
	  	  var a = this;
	  	   if(confirm('确实要删除此用户？')) {
	  	    $.post("KonkaSendMail.do", { method : "deleteUser", id : $(this).attr("id").replace(/att_del_/g, '')}, function(success) {
	  	  	});  
	  	     alert("删除成功");
	  	   }
	   }); 


	
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