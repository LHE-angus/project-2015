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
    <html-el:form action="/spgl/EcArticleInfo" enctype="multipart/form-data">
      <html-el:hidden property="id" value="${af.map.id}" />
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td nowrap="nowrap" class="title_item"><span style="color: #F00;">* </span>标题：</td>
          <td><html-el:text property="title" size="30" maxlength="130" />&nbsp;
            <span class="desc">为了便于首页美观显示，建控制在16个汉字以内</span></td>
        </tr> 
        <tr>  
          <td nowrap="nowrap" class="title_item"><span style="color: #F00;">* </span>所属系统：</td>
          <td>
          	<html-el:select property="own_sys" styleId="own_sys">
          			<html-el:option value="">请选择--</html-el:option>
          			<html-el:option value="2">触网</html-el:option>
          	</html-el:select>
          </td>
        </tr>
         <tr>
          <td nowrap="nowrap" class="title_item"><span style="color: #F00;">* </span>总部/分公司：</td>
          <td>
          	<html-el:select property="plat_sys" styleId="plat_sys">
          			<html-el:option value="">请选择--</html-el:option>
          			<c:if test="${is_admin eq 1}">
          			<html-el:option value="0">总部</html-el:option>
          			</c:if>    
          			<html-el:option value="1">分公司</html-el:option>
          	</html-el:select>
          </td>
        </tr>
        <tr>
            <td width="12%" nowrap="nowrap" class="title_item" align="left"><font color="red">* </font>所属组织：</td>  
            <td width="88%" align="left"><html-el:select property="dept_id" styleId="dept_id" disabled="${readonly}">
                <html-el:option value="">请选择--</html-el:option>
                <c:forEach var="cur" items="${deptList}">  
                  <html-el:option value="${cur.id}">${cur.group_name}</html-el:option>
                </c:forEach>
              </html-el:select>
            </td>
        </tr> 
        <tr>
          <td nowrap="nowrap" class="title_item">内容：</td>
          <td><FCK:editor instanceName="content" toolbarSet="EcTemp">
              <jsp:attribute name="value">${af.map.content}</jsp:attribute>
            </FCK:editor>
            <div class="note">1、此处上传的图片不会自动缩放，请用相关做图软件缩放成您想要的大小；</div>
            <div class="note">2、点击最后一排倒数第三个按钮可实现全屏编辑。</div>
            <div class="note"><span style="color:red;">3、修改链接地址只需要修改goods_id的值就可以。</span></div>
            </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">信息状态：</td>
          <td><label for="info_state_01">
              <html-el:radio styleId="info_state_01" property="info_state" value="0" />
          未发布</label>
            <label for="info_state_02">
              <html-el:radio styleId="info_state_02" property="info_state" value="1" />
           已发布</label></td>
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

	$("input[name='title']").attr("dataType" , "Require").attr("msg" , "标题没有填写");
	$("#own_sys").attr("dataType" , "Require").attr("msg" , "请选择所属系统");
	$("#dept_id").attr("dataType" , "Require").attr("msg" , "请选择所属组织");
	$("#plat_sys").attr("dataType" , "Require").attr("msg" , "请选择所属总部/分公司");

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