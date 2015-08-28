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
    <html-el:form action="/admin/ArticleImg" enctype="multipart/form-data">
      <html-el:hidden property="id" value="${af.map.id}" />
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="page_flag" />
      <html-el:hidden property="queryString" />
      <html-el:hidden styleId="info_mod_id" property="info_mod_id" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td colspan="2" class="item_class" align="left" ><strong class="fb">轮播焦点图添加</strong></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item"><span style="color: #F00;">*</span>发布栏目：</td>
          <td > <html-el:select property="news_module">
                <!-- <html-el:option value="">全部</html-el:option> -->
                <html-el:optionsCollection name="sysModuleWebList" label="c_name" value="c_index" />
              </html-el:select></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item"><span style="color: #F00;">* </span>标题：</td>
          <td><html-el:text property="title" size="30" maxlength="130" />&nbsp;
            <span class="desc">为了便于首页美观显示，建控制在16个汉字以内</span></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item"><span style="color: #F00;">* </span>上传图片：</td>
          <td><c:if test="${not empty (af.map.image_path)}" var="hasImage"> <img src="${ctx}/${fn:substringBefore(af.map.image_path, '.')}_240.jpg" title="${af.map.image_desc}" /> <br />
              <label for="chkReUploadImage">
                <input type="checkbox" name="chkReUploadImage" id="chkReUploadImage" value="1" onclick="$('#image_path').toggle();" />
                重新上传主图</label>
              <!--<label for="del_main_image">
                <input type="checkbox" name="del_main_image" id="del_main_image" value="1" />
                删除该主图</label>
              --><br />
              <html-el:file property="image_path" style="display:none;width:500px;" styleId="image_path" />
            </c:if>
            <c:if test="${empty (af.map.image_path)}">
              <html-el:file property="image_path" style="width:500px;" styleId="image_path" />
            </c:if>
            <div class="desc">上传的主图尺寸为370 * 210(像素)，格式为jpg，否则在首页幻灯片显示中会变形或模糊</div></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item"><span style="color: #F00;">* </span>链接：</td>
          <td><html-el:text property="image_url" size="40" maxlength="100" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">图片说明：</td>
          <td><html-el:textarea property="image_desc" cols="50" rows="6" style="overflow:auto;resize:none;" styleId="image_desc"/>
          <div class="desc">注：不可以超过260个字节（汉字占两个字节），背景色为黄色即超过，请截取</div></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">发布时间：</td>
          <td><fmt:formatDate value="${af.map.pub_date}" pattern="yyyy-MM-dd" var="_add_datetime" />
            <html-el:text property="pub_date" size="10" maxlength="10" readonly="true" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:center;" value="${_add_datetime}" />
            <span class="desc">默认为 今天</span></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">失效时间：</td>
          <td><fmt:formatDate value="${af.map.invalid_date}" pattern="yyyy-MM-dd" var="_invalid_date" />
            <html-el:text property="invalid_date" size="10" maxlength="10" readonly="true" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:center;" value="${_invalid_date}" />
           </td>
        </tr>
        <c:if test="${not empty af.map.modify_date}"> </c:if>
        <tr>
          <td nowrap="nowrap" class="title_item">信息状态：</td>
          <td><label for="info_state_01">
              <html-el:radio styleId="info_state_01" property="info_state" value="0" />
              关闭</label>
            <label for="info_state_02">
              <html-el:radio styleId="info_state_02" property="info_state" value="1" />
              发布</label></td>
        </tr>
       <tr>
          <td nowrap="nowrap" class="title_item">排 序 号：</td>
          <td><html-el:text property="order_value" size="4" maxlength="4" />
            <span class="desc">值越大，显示越靠前，范围：0-9</span></td>
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

	$("select[name='tunnel']").attr("dataType" , "Require").attr("msg" , "发布栏目没有选择");	
	$("input[name='title']").attr("dataType" , "Require").attr("msg" , "标题没有填写");
	$("input[name='image_url']").attr("dataType" , "Require").attr("msg" , "链接没有填写");
	
	$("input[name='order_value']").focus(setOnlyNum);

	$("textarea[name='summary']").focus(function(){$(this).next().slideDown("normal");}).blur(function(){$(this).next().slideUp("normal");});
});

String.prototype.getBytesLength = function() {    
    return this.replace(/[^\x00-\xff]/gi, "--").length;    
}
function checkSubmit(obj) {
	var f = document.forms[0];
	<c:if test="${hasImage eq false}">
		if(f.image_path.value.length == 0) {
			$("input[type='file'][name='image_path']").attr("dataType" , "Require").attr("msg" , "图片没有上传");
		}
	</c:if>
	
	var isSubmit = Validator.Validate(f, 3);
	if (!isSubmit) return false;
	
	var image_path = document.getElementById("image_path");

	var msg = "";
	var msg_count = 1;
	
	if (f.image_desc.value.getBytesLength() > 260) {
		msg += msg_count++ + ". 图片说明不可超过260个字节\n";
	}
	if (f.pub_date.value == "") {
		f.pub_date.value = new Date().format("yyyy-MM-dd");
	}
	if (f.invalid_date.value == "") {
		f.invalid_date.value = new Date(2019, 12, 31, 23, 59, 59).format("yyyy-MM-dd");
	}
	if (f.pub_date.value > f.invalid_date.value) {
		msg += msg_count++ + ". 发布日期不可以大于失效日期\n";
	}

	if (msg != "") { alert(msg); return false; }

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