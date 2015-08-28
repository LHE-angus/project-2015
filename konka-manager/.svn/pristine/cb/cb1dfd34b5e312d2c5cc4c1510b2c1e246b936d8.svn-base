<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />

<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/commons/ckeditor/ckeditor.js"></script>
</head>
<div style="width:1200px;height:1100px;">
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/oa/KonkaOaModuleType" enctype="multipart/form-data">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="module_id" styleId="module_id" />
      <html-el:hidden property="dept_id" styleId="dept_id" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
      	<tr>
          <td class="title_item" width="15%" align="right">分公司：</td>
          <td width="85%"><span style="color: red;">${af.map.dept_name}</span></td>
        </tr>
        <tr>
	        <td class="title_item" width="15%" align="right">模板类型：</td><!--  -->
	        <td>
	        	<html-el:select property="oa_type" styleId="oa_type" style="width:225px;"> 
		        <html-el:option value="">==请选择==</html-el:option>
		              <c:forEach var="cur" items="${af.map.oaTypeList}">
		                <html-el:option value="${cur.type_id}">${fn:escapeXml(cur.type_name)}</html-el:option>
		              </c:forEach>
		        </html-el:select>
	        </td>
	     </tr>
        <tr>
          <td class="title_item" width="15%" align="right">模板名称：</td>
          <td width="85%"><html-el:text property="module_name" styleId="module_name" maxlength="40" size="70" />
          <span style="" id="s_after"></span> &nbsp;<span id="loading" style="display:none;"><img src="${ctx}/images/ajax-loader.gif" style="vertical-align:middle; margin: 2px;" />正在处理...</span></td>
        </tr>
        <tr>
          <td class="title_item" width="15%" align="right">模板描述：</td>
          <td width="85%"><html-el:textarea property="module_desc" styleId="module_desc" style="width:500px;height:100px;" /></td>
        </tr>
        <tr>
	        <td class="title_item">内容：</td>
            <td colspan="3"><span style="color: gray">复制Word文档的内容,会引入大量的文档样式。会影响在浏览器的显示效果，请尽量避免类似操作,并且注意预览模板的效果</span></td>
          </tr>
          <tr>
           <td class="title_item"></td>
            <td colspan="3">
              <textarea rows="50" cols="70" name="module_content" id="content_msg">${af.map.module_content}</textarea>
			  <script type="text/javascript">CKEDITOR.replace('module_content');</script>
            </td>
          </tr>
          
          <tr>
            <td class="title_item">&nbsp;</td>
        </tr> 
        <tr>
          <td class="title_item" width="15%" align="right">排序值：</td>
          <td width="85%"><html-el:text property="order_value" styleId="order_value" maxlength="4" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><label>
            <input class="but4" type="button" name="Submit4" id="btn_submit" value="提交" />
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();" />
            </label></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>

<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
    $("#module_name").attr("dataType", "Require").attr("msg", "模板名称不能为空！");
    $("#module_desc").attr("dataType", "Limit").attr("max", "200").attr("msg", "备注不能超过200个文字");
	$("#order_value").attr("focus",setOnlyNum);

	// 提交
	$("#btn_submit").click(function(){
		
	//var oEditor = FCKeditorAPI.GetInstance('module_content');
	//	if(oEditor.GetXHTML(true) == ''){
		//	alert("模板内容不能为空!");
	//		return null;
	//	}
		
			if(Validator.Validate(this.form, 2)){
	            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
	            $("#btn_back").attr("disabled", "true");
				this.form.submit();
			}
		});
		
	$("#module_name").change(function(){
			var module_name = (this.value).replace(/\s+/g, "");
			var dept_id = $("#dept_id").val();
			//alert(module_name);
			if(module_name.length > 0){
				$("#tip_module_name").remove();
				$("#btn_submit").attr("disabled", "true");
				if(module_name != '${af.map.module_name}') {
				$.ajax({
					type: "POST",
					url: "KonkaOaModuleType.do",
					data: "method=validateModulename&module_name=" + module_name + "&dept_id="+dept_id,
					dataType: "json",
					error: function(request, settings) {alert("数据加载请求失败！"); },
					success: function(isExist) {
						$("#tip_module_name").remove();
						if(isExist == 11) {
							$("#__ErrorMessagePanel").remove();
							$("#s_after").after('<span id="tip_module_name" style="color:#FF0000;"><img src="${ctx}/commons/styles/themes/blue/images/reg_error.gif" />&nbsp;对不起，模板名称已存在！<\/span>'); 
							$("#btn_submit").attr("disabled", "true");
							return;
						} else if (isExist == 0){
							$("#__ErrorMessagePanel").remove();
							$("#s_after").after('<span id="tip_module_name" style="color:#5A8E4A;"><img src="${ctx}/commons/styles/themes/blue/images/reg_success.gif" />&nbsp;恭喜，该模板名称可用！<\/span>'); 
							$("#btn_submit").removeAttr("disabled");
						} 
					}
				});
			   }
			} 
		});
});

function setOnlyNum() {
	$(this).css("ime-mode", "disabled").attr("t_value", "").attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</div>
</html>
