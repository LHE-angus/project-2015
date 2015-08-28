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
    <html-el:form action="/admin/KonkaInterfaceLicenses" method="post">
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>授权码：</td>
          <td width="88%" align="left"><html-el:text property="licenses_sn" styleId="licenses_sn" size="25" maxlength="25"/><span style="color:#f00;display:none;" id="user_name_exist_error">该授权码已被使用，请重新输入！</span><span style="color:#f00;display:none;" id="user_name_erro">授权码不能含空白字符！</span>
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>授权单位：</td>
          <td width="88%" align="left"><html-el:text property="org_name" styleId="org_name" size="60" maxlength="60"/></td>
        </tr> 
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>是否注销：</td>
          <td width="88%" align="left">
          <html-el:select property="info_state" styleId="info_state" >
          		<html-el:option value="0">正常</html-el:option>
          		<html-el:option value="1">已注销</html-el:option>
          </html-el:select>
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>是否删除：</td>
          <td width="88%" align="left">
          <html-el:select property="is_del" styleId="is_del" >
          		<html-el:option value="0">正常</html-el:option>
          		<html-el:option value="1">已删除</html-el:option>
          </html-el:select>
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">备注：</td>
          <td width="88%" align="left">
          	<html-el:textarea property="remarks" styleId="remarks" cols="5" style="width:450px;height:70px;" />
          	<div id="info_tip" style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><html-el:button property="" value="提交" styleClass="but4" styleId="btn_submit" />
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

	$("#licenses_sn").attr("dataType", "Require").attr("msg", "请输入授权码！");
	$("#org_name").attr("dataType", "Require").attr("msg", "请输入授权单位！");


	// 验证用户名是否存在
	$("#licenses_sn").blur(function(){
		var licenses_sn = $("#licenses_sn").val();
		if(null == $(this).val() || $(this).val() == ''){
			$("#user_name_exist_error").hide();
			$("#user_name_erro").hide();
			$(this).css("background-color", "#fff");
			return ;
		}
		if($(this).val().indexOf(' ')>-1){
			$("#user_name_exist_error").hide();
			$("#user_name_erro").show();
			$("#licenses_sn").val("");  
			return;
		}
		
		$("#user_name_erro").hide();
		$("#user_name_exist_error").hide();
		$(this).css("background-color", "#fff");
		if(licenses_sn != '${af.map.licenses_sn}'){
			$.ajax({
				type: "POST",
				url: "KonkaInterfaceLicenses.do",
				data: "method=validateLicenses_sn&licenses_sn=" + $(this).val(),
				dataType: "json",
				error: function(request, settings) {
					alert("检查用户名重复失败，请稍候再次尝试。");
					$("#user_name_exist_error").show();
					$(this).css("background-color", "#ddcc00");
					$(this).focus();
				},
				success: function(oper) {
					if (oper.result) {
						alert("该授权码已存在！");
						$("#licenses_sn").val("");
						return;
					} else {
						$("#user_name_exist_error").hide();
						$("#user_name").css("background-color", "#fff");
					}
				}
			});
		}
	});	

	
	$("#remarks").textbox({
		maxLength: 100,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_tip").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_tip").slideUp("normal");
	});
 
	
	$("#btn_submit").click(function(){
		
		var isSubmit = Validator.Validate(this.form, 3);
		if (isSubmit) {
			$(":button").attr("disabled", "true");
			$(":reset").attr("disabled", "true");
			this.form.submit();
		}
	});
	
});


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
