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
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/zmd/KonkaXxZmdCredit">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="id" value="${af.map.id}" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="dept_id" value="${af.map.dept_id}" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable4">
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right">分公司：</td>
          <td><strong class="fb"><span style="color: red;">${af.map.dept_name}</span></strong></td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right"><span style="color:red">[必填]</span>信用等级类型：</td>
          <td><c:if test="${empty af.map.id}">
              <html-el:text property="credit_type" styleId="credit_type" size="50" maxlength="40" />
              <span style="" id="s_after"></span> </c:if>
            <c:if test="${!empty af.map.id}"> ${af.map.credit_type} </c:if></td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right"><span style="color:red">[必填]</span>信用额度（元）：</td>
          <td><html-el:text property="credit_money" styleId="credit_money" size="50" maxlength="40" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right">类型描述：</td>
          <td><html-el:textarea property="credit_des" styleId="credit_des" style="width:365px;height:70px;" />
            <div id="info_chat_content"  style="color:#0066FF;font-size:12px;display:none"><img src="../../images/tishi.gif" style="vertical-align:middle;" /></div></td>
        </tr>
        <tr>
          <td>&nbsp;&nbsp;</td>
          <td><input class="but4" type="button" name="Submit4" value="保存" id="send" />
            <input class="but3" type="reset"  value="重填 " />
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back()" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<!-- ****** Main Frame End ****** -->
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#credit_type").attr("dataType", "Require").attr("msg", "请填写信用等级类型！");
	$("#credit_money").attr("dataType", "Require").attr("msg", "请填写信用额度！");

	$("#credit_money").attr("focus",setOnlyNum);
	$("#hd_contend").textbox({
		maxLength: 200,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/styles/jxc/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_chat_content").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_chat_content").slideUp("normal");
	});
	
	$("#send").click(function(){
		 $("#credit_msg").remove();
		 var isSubmit = Validator.Validate(this.form, 3);
		 if (isSubmit) {
			$(":button").attr("disabled", "true");
			$(":reset").attr("disabled", "true");
			this.form.submit();
		 }
	 });

$("#credit_type").blur(function(){
	$("#credit_msg").remove();
		if(this.value.length > 0){
			$.ajax({
				type: "POST",
				url: "KonkaXxZmdCredit.do",
				data: "method=validateType&credit_type=" + this.value,
				dataType: "json",
				error: function(request, settings) {alert("数据加载请求失败！"); },
				success: function(isExist) {
					$("#credit_msg").remove();
					if(isExist == 11) {
						$("#s_after").after('<span id="credit_msg" style="color:#FF0000;"><img src="${ctx}/commons/styles/themes/blue/images/reg_error.gif" />&nbsp;对不起，该种信用等级类型已存在！<\/span>'); 
						$(":button").attr("disabled", "true");
						$(":reset").attr("disabled", "true");
						return;
					}else if(isExist == 0){
						$("#s_after").after('<span id="credit_msg" style="color:#5A8E4A;"><img src="${ctx}/commons/styles/themes/blue/images/reg_success.gif" />&nbsp;恭喜，信用等级类型可用！<\/span>'); 
						$(":button").removeAttr("disabled");
						$(":reset").removeAttr("disabled");
					} 
				 }
		     });
		} else {
			$("#credit_msg").remove();
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
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
