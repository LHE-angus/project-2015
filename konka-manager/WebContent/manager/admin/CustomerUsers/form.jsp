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
<style type="text/css">
#sel{margin-top:10px}
</style>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/CustomerUsers.do" enctype="multipart/form-data">
      <html-el:hidden property="user_id" value="${af.map.user_id}" />
      <html-el:hidden property="cust_id" />
      <html-el:hidden property="request_from" />
      <html-el:hidden property="zmd_id" />
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="pass_word_old" value="${af.map.pass_word}" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="queryString" />
      <html-el:hidden property="returnUrl" />
      <c:if test="${pe_prod_user_session.user_type eq 0}">
        <c:set value="true" var="is_admin" />
      </c:if>
      <div align="left">
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="datagrid">
          <tr class="oartop">
            <td height="28" colspan="4">${empty af.map.id ? '添加' : '修改'} <span style="padding-bottom:10px;fontweight:bold;">${konkaR3Shop.customer_name}</span> 客户用户</td>
          </tr>
          <tr>
            <td width="15%" nowrap="nowrap" height="28" class="title_item">用户类型：</td>
            <td colspan="3">康佳客户<html-el:hidden property="user_type" value="2" /></td>
          </tr>
          <tr>
            <td nowrap="nowrap" height="28" class="title_item">登录名：</td>
            <td colspan="3"><html-el:text property="user_name" styleId="user_name" size="40" maxlength="20" />
              &nbsp;<span style="color:red">*</span> <span style="color:#f00;display:none;" id="user_name_exist_error">该登录名已被使用，请重新输入！</span><span style="color:#f00;display:none;" id="user_name_erro">登录名不能含空白字符！</span></td>
          </tr>
          <tr>
            <td nowrap="nowrap" height="28" class="title_item">登录密码：</td>
            <td colspan="3"><html-el:password property="pass_word" styleId="pass_word" size="40" maxlength="20" />
               &nbsp;<span style="color:red">*</span> <span> 请切记系统默认密码为：888888</span></span><span style="color:#f00;display:none;" id="pass_word_erro">密码不能含空白字符！</span></td>
          </tr>
          <tr class="oartop">
            <td colspan="4">用户信息</td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item">姓名：</td>
            <td colspan="3"><html-el:text property="real_name" maxlength="20" size="40" styleId="real_name" />
              &nbsp;<span style="color:red">*</span> <span>请务必填写真实姓名</span></td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item">性别：</td>
            <td><label for="sex0" style="width:80px;">
              <html-el:radio property="sex" styleClass="sex" styleId="sex0" value="0" >男</html-el:radio>
              </label>
              <label for="sex1" style="width:80px;">
              <html-el:radio property="sex" styleClass="sex" styleId="sex1" value="1">女</html-el:radio>
              </label>
              <label for=sex2 style="width:80px;">
              <html-el:radio property="sex" styleClass="sex" styleId="sex2" value="2" >保密</html-el:radio>
              </label></td>
            <td nowrap="nowrap" height="28" class="title_item">出生日期：</td>
            <td><fmt:formatDate value="${af.map.birthday}" pattern="yyyy-MM-dd" var="_birthday"/>
              <html-el:text property="birthday" value="${_birthday}"  size="10" maxlength="20" readonly="true" onclick="new Calendar(1950, 2020, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
          </tr>
          <tr>
          <td width="15%" valign="middle" nowrap="nowrap" class="title_item">居住地：</td>
          <td colspan="3" valign="middle" nowrap="nowrap">
            <select name="province" id="province" class="bd">
              <option value="">-请选择省/直辖市/自治区-</option>
            </select>
            <select name="city" id="city" class="bd">
              <option value="">-请选择市-</option>
            </select>
            <select name="country" id="country" class="bd">
              <option value="">-请选择县-</option>
            </select>
            <select name="town" id="town" class="bd">
              <option value="">-请选择乡/镇-</option>
            </select></td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item">街道地址：</td>
            <td colspan="3"><html-el:text property="link_addr" styleId="link_addr" maxlength="130" size="80"/></td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item">手机：</td>
            <td width="35%"><html-el:text property="link_phone" styleId="link_phone" maxlength="20" size="40"/></td>
            <td width="15%" nowrap="nowrap"  height="28" class="title_item">电话：</td>
            <td width="35%"><html-el:text property="link_tel" styleId="link_tel" maxlength="130" size="40"/>
              &nbsp;<span class="note">格式如0551-4567888</span></td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item">邮箱：</td>
            <td><html-el:text property="email" styleId="email" maxlength="80" size="25"/></td>
            <td nowrap="nowrap"  height="28" class="title_item">邮编：</td>
            <td><html-el:text property="link_post" styleId="link_post" maxlength="10" size="10"/></td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item">QQ：</td>
            <td><html-el:text property="link_qq" styleId="link_qq" maxlength="20" size="15"/></td>
            <td nowrap="nowrap"  height="28" class="title_item">MSN：</td>
            <td><html-el:text property="link_msn" styleId="link_msn" maxlength="30" size="20"/></td>
          </tr>
          <tr>
            <td colspan="6" height="40"  align="center"><input class="but4" type="button" name="Submit4" value=" 保存 " id="btn_submit" />
              <input class="but3" type="reset"  value=" 重填" id="reset" />
              <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" id="btn_back" /></td>
          </tr>
        </table>
      </div>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.pager.js"></script>
<script type="text/javascript">//<![CDATA[
	$(document).ready(function(){
		<c:if test="${empty af.map.user_id}">
		$("#pass_word").val("888888");
		</c:if>

		var isRomveYwy = '${isRomveYwy}';
		
		$("#user_name").attr("datatype", "Require").attr("msg", "请输入用户名！");
		$("#real_name").attr("datatype", "Chinese").attr("msg", "真实姓名只允许中文！");
		$("#pass_word").attr("datatype", "Require").attr("msg", "请填写密码！");
		
		$("#link_msn").attr("datatype", "Email").attr("msg", "请填写正确的MSN！").attr("require", "false");
		$("#email").attr("datatype", "Email").attr("msg", "请填写正确的邮箱！").attr("require", "false");
		$("#link_phone" ).attr("datatype", "Mobile").attr("msg", "请填写正确的手机号码！").attr("require", "true");
		$("#link_tel" ).attr("datatype", "Phone").attr("msg", "请填写正确的电话号码！").attr("require", "false");
		$("#link_qq" ).attr("focus",setOnlyNum);
		$("#link_post" ).attr("focus",setOnlyNum);
		$("#user_name").focus(function() {
		    //获得焦点时，如果值为默认值，则设置为空
		    if (this.value == vdefault) {
		        this.value = "";
		        this.style.color='#333';
		    }
		}).blur(function() {
		    //失去焦点时，如果值为空，则设置为默认值
		    if (this.value == "") {
		        this.value = vdefault;
		        this.style.color='#999999';
		    }
		    
		  	//begin 转化全角为半角
			$("#user_name").val(DBC2SBC(this.value).NoSpace());
			//end 
		});
		String.prototype.NoSpace = function() 
		{ 
			return this.replace(/\s+/g, ""); 
		}
		$("#pass_word").focus(function() {
		    //获得焦点时，如果值为默认值，则设置为空
		    if (this.value == vdefault) {
		        this.value = "";
		        this.style.color='#333';
		    }
		}).blur(function() {
		    //失去焦点时，如果值为空，则设置为默认值
		    if (this.value == "") {
		        this.value = vdefault;
		        this.style.color='#999999';
		    }
		    
		  	//begin 转化全角为半角
			$("#pass_word").val(DBC2SBC(this.value).NoSpace());
			//end 
		});
		
		$("#btn_submit").click(function(){

			<c:if test="${empty af.map.user_name}">
				var user_name = $("#user_name").val();
				if("" == user_name || user_name.indexOf(' ')>-1) {
						$("#user_name_erro").show();
					return false;	
				}
   			</c:if>
			if(Validator.Validate(this.form, 3)) {
				$("#btn_submit"   ).attr("disabled",true);
				$("#reset").attr("disabled",true);
				$("#btn_back"     ).attr("disabled",true);
				this.form.submit();
			}
		});
		
		// 验证用户名是否存在
		$("#user_name").blur(function(){
			$("#btn_submit").attr("disabled", "disabled");
			var user_name = $("#user_name").val();
			if(null == $(this).val() || $(this).val() == ''){
				$("#user_name_exist_error").hide();
				$("#user_name_erro").hide();
				$(this).css("background-color", "#fff");
				return ;
			}
			if($(this).val().indexOf(' ')>-1){
				$("#user_name_exist_error").hide();
				$("#user_name_erro").show();
				return;
			}
			
			$("#user_name_erro").hide();
			$("#user_name_exist_error").hide();
			$(this).css("background-color", "#fff");
			if(user_name != '${af.map.user_name}'){
				$.ajax({
					type: "POST",
					url: "CustomerUsers.do",
					data: "method=validateName&user_name=" + $(this).val(),
					dataType: "json",
					error: function(request, settings) {
						alert("检查用户名重复失败，请稍候再次尝试。");
						$("#user_name_exist_error").show();
						$(this).css("background-color", "#ddcc00");
						$(this).focus();
					},
					success: function(oper) {
						if (oper.result) {
							//$("#user_name_exist_error").show();
							//$("#btn_submit").attr("disabled", "disabled");
							//$("#user_name").css("background-color", "#ddcc00");
							//$("#user_name").focus();
							alert("该用户名已存在！");
							$("#user_name").val("");
							return;
						} else {
							$("#user_name_exist_error").hide();
							$("#user_name").css("background-color", "#fff");
							$("#btn_submit").removeAttr("disabled");
						}
					}
				});
			}
		});	
		
		// 区域
		$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${province}","dataType":"Require","msg":"请选择省/直辖市/自治区！"});
		$("#city").attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${city}"});
		$("#country").attr({"subElement": "town","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${country}"});
		$("#town").attr({"defaultText": "-请选择乡/镇-", "defaultValue": "", "selectedValue": "${town}"});
		
		$("#province").cs_ext("${ctx}/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false,"p_index_join","${af.map.p_index_join}");
		$("#province").change();
});

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
			if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}
			if(this.value.length == 0) this.value = "0";
		});
		//this.text.selected;
}
function DBC2SBC(str){
    var result = '';
    for (i=0 ; i<str.length; i++){
        code = str.charCodeAt(i);//获取当前字符的unicode编码
        if (code >= 65281 && code <= 65373){//在这个unicode编码范围中的是所有的英文字母已及各种字符
   			result += String.fromCharCode(str.charCodeAt(i) - 65248);//把全角字符的unicode编码转换为对应半角字符的unicode码
  		}else if (code == 12288){//空格
   			result += String.fromCharCode(str.charCodeAt(i) - 12288 + 32);
  		}else{
   			result += str.charAt(i);
  		}
   }
   return result;
}


//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
