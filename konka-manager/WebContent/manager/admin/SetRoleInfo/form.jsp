<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<title>${naviString}</title>
</head>
<body>
<div id="navTab" class="tabsPage" style="text-align:left;">
  <div class="tabsPageHeader">
    <div class="tabsPageHeaderContent"> 
      <!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
      <ul class="navTab-tab">
        <li class="main"><a><span><span class="home_icon">角色基本信息</span></span></a></li>
      </ul>
    </div>
  </div>
  <div class="navTab-panel tabsPageContent">
    <div class="page">
      <div class="pageContent">
        <html-el:form action="/admin/SetRoleInfo" enctype="multipart/form-data">
          <html-el:hidden property="queryString" styleId="queryString" />
          <html-el:hidden property="method" styleId="method" value="save" />
          <html-el:hidden property="mod_id" styleId="mod_id" />
          <html-el:hidden property="id" styleId="id" />
          <div style="height:10px;"></div>
          <table width="98%" align="center" class="list">
            <tbody>
              <tr>
                <td width="12%" nowrap="nowrap" class="title_item">角色名称：</td>
                <td width="88%"><html-el:text property="role_name" styleId="role_name" maxlength="30" style="width:200px" />
                  <span style="color:#f00;">*</span></td>
              </tr>
              <tr>
                <td nowrap="nowrap" class="title_item">角色说明：</td>
                <td><html-el:text property="role_desc" maxlength="120" style="width:200px" /></td>
              </tr>
              <tr>
                <td nowrap="nowrap" class="title_item">排序值：</td>
                <td><html-el:text property="order_value" styleId="order_value" maxlength="4" size="4" />
                  值越大，显示越靠前</td>
              </tr>
              <tr>
                <td nowrap="nowrap" class="title_item">是否删除：</td>
                <td><html-el:select property="is_del">
                    <html-el:option value="0">未删除</html-el:option>
                    <html-el:option value="1">已删除</html-el:option>
                  </html-el:select></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><html-el:button property="" value="提 交" styleClass="websub" styleId="btn_submit" />
                  <html-el:button property="" value="重 置" styleClass="websub" styleId="btn_reset" onclick="this.form.reset();" />
                  <html-el:button property="" value="返 回" styleClass="websub" styleId="btn_back" onclick="history.back();return false;" />
                  <span style="color:#f00;">*</span>为必填项</td>
              </tr>
            </tbody>
          </table>
        </html-el:form>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#role_name"	).attr("dataType", "Require").attr("msg", "角色名必须填写");
	$("#order_value").attr("dataType", "Integer").attr("msg", "排序值必须为整数");
	
	String.prototype.trim = function(){
        return this.replace(/(^\s*)|(\s*$)/g, "");
    }
	$("#role_name").blur(function() {
        $(this).val(this.value.trim());                           
    });
	
	$("#order_value").focus(setOnlyNum);

	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 1)){
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_reset").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});
});

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
		if(this.value.length == 0) this.value = 0;
	})
}

var f = document.forms[0];
$("#role_name").blur(function(){
	if(null == f.role_name.value){
		return ;
	}
	$.ajax({
		type: "POST",
		url: "SxRoleInfo.do",
		data: "method=validateRolename&role_name=" + f.role_name.value,
		dataType: "json",
		error: function(request, settings) {},
		success: function(oper) {
			if(oper.result){
				$("#role_name").val("");
				alert("系统已存在此角色");
				f.role_name.focus();				
			}else {
				return;
			}
		}
	});
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
