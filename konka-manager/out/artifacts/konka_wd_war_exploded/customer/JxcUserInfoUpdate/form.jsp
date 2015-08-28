<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<style type="text/css">
select {
	font-family:Microsoft YAHEI;
	font-size:12px;
}
input {
	font-family:Microsoft YAHEI;
	font-size:12px;
}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oartop">
  <table width="400" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
      <td>当前位置：${naviString}</td>
    </tr>
  </table>
</div>
<div>
  <div class="rtabcont1">
    <html-el:form action="/manager/JxcUserInfoUpdate.do">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <html-el:hidden property="queryString" />
      <div align="left">
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
          <tr>
            <th colspan="4" style="font-size:15px;">个人信息</th>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">姓名：</td>
            <td colspan="3"><html-el:text property="real_name" styleId="real_name" maxlength="" size="40"/>
              &nbsp;<span style="color:red">*</span></td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">性别：</td>
            <td><html-el:select styleId="sex" property="sex">
                <html-el:option value="">请选择</html-el:option>
                <html-el:option value="0">男</html-el:option>
                <html-el:option value="1">女</html-el:option>
                <html-el:option value="2">保密</html-el:option>
              </html-el:select></td>
          </tr>
          <tr>
            <td width="15%" valign="middle" nowrap="nowrap" class="title_item" align="right">居住地：</td>
            <td width="85%" valign="middle" nowrap="nowrap"><select name="province" id="province" class="bd">
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
            <td nowrap="nowrap"  height="28" class="title_item" align="right">联系地址：</td>
            <td colspan="3"><html-el:text property="link_addr" styleId="link_addr" maxlength="130" size="80"/></td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">邮编：</td>
            <td colspan="3"><html-el:text property="link_post" styleId="link_post" maxlength="10" size="40"/></td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">手机：</td>
            <td colspan="3"><html-el:text property="link_phone" styleId="link_phone" maxlength="20" size="40"/></td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">电话：</td>
            <td colspan="3"><html-el:text property="link_tel" styleId="link_tel" maxlength="130" size="40"/>
              &nbsp;<span class="note">注：电话号码格式如0551-4567888</span></td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">邮箱：</td>
            <td colspan="3"><html-el:text property="email" styleId="email" maxlength="80" size="40"/></td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">QQ：</td>
            <td colspan="3"><html-el:text property="link_qq" styleId="link_qq" maxlength="20" size="40"/></td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">MSN：</td>
            <td colspan="3"><html-el:text property="link_msn" styleId="link_msn" maxlength="30" size="40"/></td>
          </tr>
          <tr>
            <td></td>
            <td colspan="3"  height="40" align="left"><input class="bgButtonSave" type="button" name="Submit4" value=" 保存 " id="btn_submit" />
              <input class="bgButtonBack" type="button" name="Submit5" value="返回" onclick="history.back();return false;" id="btn_back" /></td>
          </tr>
        </table>
      </div>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	// 区域
	$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${province}","dataType":"Require","msg":"请选择省/直辖市/自治区！"});
	$("#city").attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${city}"});
	$("#country").attr({"subElement": "town","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${country}"});
	$("#town").attr({"defaultText": "-请选择乡/镇-", "defaultValue": "", "selectedValue": "${town}"});
	
	$("#province").cs_ext("${ctx}/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false,"p_index_join","${af.map.p_index_join}");
	$("#province").change();
	
	$("#real_name").attr("datatype", "Chinese").attr("msg", "真实姓名只允许中文！");
	
	$("#link_msn").attr("datatype", "Email").attr("msg", "请填写正确的MSN！").attr("require", "false");
	$("#email").attr("datatype", "Email").attr("msg", "请填写正确的邮箱！").attr("require", "false");
	$("#link_phone" ).attr("datatype", "Mobile").attr("msg", "请填写正确的手机号码！").attr("require", "false");
	$("#link_tel" ).attr("datatype", "Phone").attr("msg", "请填写正确的电话号码！").attr("require", "false");
	$("#link_post" ).attr("focus",setOnlyNum);
	$("#link_phone").focus(function() {
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
         $("#link_phone").val($.trim(DBC2SBC(this.value)));
		//end 
	});
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 3)){
			$("#btn_submit").attr("disabled",true);
			$("#btn_back").attr("disabled",true);
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

function DBC2SBC(str)
{
 var result = '';
 for (i=0 ; i<str.length; i++)
 {
  code = str.charCodeAt(i);//获取当前字符的unicode编码
  if (code >= 65281 && code <= 65373)//在这个unicode编码范围中的是所有的英文字母已及各种字符
  {
   result += String.fromCharCode(str.charCodeAt(i) - 65248);//把全角字符的unicode编码转换为对应半角字符的unicode码
  }else if (code == 12288)//空格
  {
   result += String.fromCharCode(str.charCodeAt(i) - 12288 + 32);
  }else
  {
   result += str.charAt(i);
  }
 }
 return result;
}
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
