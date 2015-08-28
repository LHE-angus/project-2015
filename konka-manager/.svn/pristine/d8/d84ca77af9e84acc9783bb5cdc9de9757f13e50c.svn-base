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
    <table width="600" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString} - ${ecHomeFloor.title}_${baseTypeData.type_name}</td>
      </tr>
    </table>
  </div>
 	<div class="rtabcont2">
    <html-el:form action="/spgl/EcHomeFloorData" enctype="multipart/form-data">
      <html-el:hidden property="id"  />
      <html-el:hidden property="floor_id"  />
      <html-el:hidden property="data_type_id"/>
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td nowrap="nowrap" class="title_item"><span style="color: #F00;">* </span>标题：</td>
          <td><html-el:text property="title" size="30" maxlength="130" /></td>
        </tr><c:if test="${fn:contains(baseTypeData.memo, '图片')}">
        <tr>
          <td nowrap="nowrap" class="title_item"><span style="color: #F00;">* </span>图片：</td>
          <td><c:if test="${not empty (af.map.img_url)}" var="hasImage"> <img src="${fn:substringBefore(af.map.img_url, '.jpg')}_240.jpg" /> <br />
              <label for="chkReUploadImage">
                <input type="checkbox" name="chkReUploadImage" id="chkReUploadImage" value="1" onclick="$('#img_file').toggle();" />
                重新上传图片</label> <br />
              <html-el:file property="img_file" style="display:none;width:500px;" styleId="img_file" />
            </c:if>
            <c:if test="${empty (af.map.img_url)}">
              <html-el:file property="img_file" style="width:500px;" styleId="img_file" />
            </c:if>
            <div class="desc">图片格式为jpg</div></td>
        </tr></c:if><c:if test="${fn:contains(baseTypeData.memo, '链接')}">
        <tr>
          <td nowrap="nowrap" class="title_item"><span style="color: #F00;">* </span>链接：</td>
          <td><html-el:text property="link_url" size="30" maxlength="130" /></td>
        </tr></c:if><c:if test="${fn:contains(baseTypeData.memo, '市场价')}">
        <tr>
          <td nowrap="nowrap" class="title_item"><span style="color: #F00;">* </span>市场价：</td>
          <td><html-el:text property="original_price" size="30" maxlength="130" /></td>
        </tr></c:if><c:if test="${fn:contains(baseTypeData.memo, '会员价')}">
        <tr>
          <td nowrap="nowrap" class="title_item"><span style="color: #F00;">* </span>会员价：</td>
          <td><html-el:text property="price" size="30" maxlength="130" /></td>
        </tr></c:if>
        <tr>
          <td nowrap="nowrap" class="title_item">备注：</td>
          <td><FCK:editor instanceName="content">
              <jsp:attribute name="value">${af.map.content}</jsp:attribute>
            </FCK:editor>
           </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">排序值：</td> 
          <td><html-el:text property="order_sort" styleId="order_sort" style="width:40px;" maxlength="4" size="4" styleClass="webinput" />
           	 取值范围：0 - 9999， 值越大，显示越靠前。&nbsp;&nbsp;</td>
        </tr> 
        <tr>
          <td colspan="2" align="center"><input id="save_button" class="but4" type="button" name="save" value="保存 " onclick="if(checkSubmit(this))this.form.submit();" />
            <input type="reset" name="reset" class="bgButtonReset" value="重填 " />
            <input type="button" class="but5" name="return" value="返回 " onclick="history.back();" />
	       <font color="red">${baseTypeData.memo}</font>
	       </td>
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
	$("input[name='title']").attr("dataType" , "Require").attr("msg" , "标题没有填写"); 
	<c:if test="${empty (af.map.img_url)}">
	<c:if test="${fn:contains(baseTypeData.memo, '图片')}">
	$("input[name='chkReUploadImage']").attr("dataType" , "Require").attr("msg" , "图片没有上传 "); 
	</c:if>
	</c:if>
	<c:if test="${fn:contains(baseTypeData.memo, '链接')}">
	$("input[name='link_url']").attr("dataType" , "Require").attr("msg" , "跳转链接不能为空 "); 
	</c:if>
	<c:if test="${fn:contains(baseTypeData.memo, '市场价')}">
	$("input[name='original_price']").attr("dataType" , "Require").attr("msg" , "市场价不能为空 "); 
	</c:if>
	<c:if test="${fn:contains(baseTypeData.memo, '会员价')}">
	$("input[name='price']").attr("dataType" , "Require").attr("msg" , "会员价不能为空 "); 
	</c:if>
});

String.prototype.getBytesLength = function() {    
    return this.replace(/[^\x00-\xff]/gi, "--").length;    
}
function checkSubmit(obj) {
	var f = document.forms[0]; 
	
	var isSubmit = Validator.Validate(f, 3);
	if (!isSubmit) return false;

	$("#save_button").attr("disabled", "true").val("正在提您的请求...");
	$(":submit").attr("disabled", "true");
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