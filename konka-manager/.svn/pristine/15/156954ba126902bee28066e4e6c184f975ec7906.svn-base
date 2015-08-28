<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>基础数据管理&gt; 仓库信息</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
-->
</style>
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;"/> 当前位置：基础数据管理&gt; 仓库信息</div>
  <div class="rtabcont1">
    <html-el:form action="/KonkaJxcStoreInfo.do" enctype="multipart/form-data">
      <html-el:hidden property="id" />
      <html-el:hidden property="add_user_id" />
      <html-el:hidden property="add_dept_id" />
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th colspan="2">添加仓库</th>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item"><font color="red">*</font>仓库名称：</td>
          <td><html-el:text property="store_name" styleClass="webinput" styleId="store_name"  maxlength="100"/>
            <span style="color:#f00;display:none;" id="store_name_exist_error">该仓库名称已存在，请重新输入</span></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item"><font color="red">*</font>仓库地址：</td>
          <td><html-el:text property="store_addr" styleClass="webinput" styleId="store_addr"  maxlength="100"/></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item"><font color="red">*</font>负责人：</td>
          <td><html-el:text property="link_man" styleClass="webinput" styleId="link_man" maxlength="50"/></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item"><font color="red">*</font>联系电话：</td>
          <td><html-el:text property="link_tel" styleClass="webinput" styleId="link_tel"  maxlength="15"/></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item">备注：</td>
          <td><html-el:text property="remark" styleClass="webinput" styleId="remark" maxlength="100" style="width:60%"></html-el:text></td>
        </tr>
        <tr>
          <td colspan="2" align="center"><html-el:button property="save" styleClass="bgButtonSave" styleId="btn_submit" value="保 存"/>
            <html-el:button property="back" value="返 回 " onclick="history.back();" styleClass="bgButtonBack"/></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript">//<![CDATA[
	$(document).ready(function(){
		$("#store_name").attr("datatype", "Require").attr("msg", "请输入仓库名称！");
		$("#store_addr").attr("datatype", "Require").attr("msg", "请输入仓库地址！");
		$("#link_man").attr("datatype", "Require").attr("msg", "请输入负责人！");
		$("#link_tel").attr("datatype", "Require").attr("msg", "请填写正确的联系电话！");

		var f = document.forms[0];
		$("#btn_submit").click(function(){
			var isSubmit = Validator.Validate(f, 3);
			if (isSubmit){
				$(":submit").attr("value", "正在提交...").attr("disabled", "true");
				$(":button").attr("disabled", "true");
				$(":reset").attr("disabled", "true");
				f.submit();
			}
		});	
		
		$("#store_name").change(function(){
			$.ajax({
				type: "POST",
				url: "KonkaJxcStoreInfo.do",
				data: "method=validateName&store_name=" + $(this).val(),
				dataType: "json",
				error: function(request, settings) {
					alert("检查仓库名重复失败，请稍候再次尝试。");
					$("#user_name_exist_error").show();
					$(this).css("background-color", "#ddcc00");
					$(this).focus();
				},
				success: function(oper) {
					if (oper.result == 'true') {
						$("#store_name_exist_error").hide();
						$("#btn_submit").removeAttr("disabled");
					} else {
						$("#store_name_exist_error").show();
						$("#btn_submit").attr("disabled", "disabled");
					}
				}
			});
		});	
});

	f.onsubmit = function () {
		return Validator.Validate(this, 3);
	};
	
function setOnlyNum() {
		$(this).css("ime-mode", "disabled");
		$(this).attr("t_value", "");
		$(this).attr("o_value", "");
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
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
