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
    <html-el:form action="/jf/MemberInfo">
      <html-el:hidden property="method" value="memberCardSave" />
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="queryString" />
      <div align="left">
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">必填</td>
            <td colspan="3"></td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">停卡原因：</td>
            <td ><html-el:select styleId="stop_reason" property="stop_reason"> 
                <html-el:option value="0">积分卡丢失</html-el:option>
                <html-el:option value="1">重新换卡</html-el:option>
                <html-el:option value="2">无聊</html-el:option>
                <html-el:option value="3">打酱油</html-el:option>
              	</html-el:select>
            	&nbsp;</td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">新会员卡号：</td>
            <td colspan="3"><html-el:text property="now_user_sn" styleId="now_user_sn" maxlength="" size="40"/>
              &nbsp;</td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">原会员卡号：</td>
            <td colspan="3"><html-el:text property="user_sn" styleId="user_sn" maxlength="" size="40"/>
              &nbsp;</td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">会员类型：</td>
            <td >
             	<html-el:select styleId="user_level" property="user_level"> 
                <html-el:option value="0">个人会员</html-el:option>
                <html-el:option value="1">白银会员</html-el:option>
                <html-el:option value="2">黄金会员</html-el:option>
                <html-el:option value="3">钻石会员</html-el:option>
              	</html-el:select>
            	&nbsp;
             </td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">姓名：</td>
            <td colspan="3"><html-el:text property="user_name" styleId="user_name" maxlength="" size="40"/>
              &nbsp;</td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">性别：</td>
            <td><label for="sex1" style="width:80px;">
              <html-el:radio property="sex" styleClass="sex" styleId="sex1" value="1" >男</html-el:radio>
              </label>
              <label for="sex2" style="width:80px;">
              <html-el:radio property="sex" styleClass="sex" styleId="sex2" value="2">女</html-el:radio>
              </label>
              <label for="sex3" style="width:80px;">
              <html-el:radio property="sex" styleClass="sex" styleId="sex3" value="3" >保密</html-el:radio>
              </label>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">手机：</td>
            <td colspan="3"><html-el:text property="mp" styleId="mp" maxlength="20" size="40"/>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">邮箱：</td>
            <td colspan="3"><html-el:text property="email" styleId="email" maxlength="80" size="40"/>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">身份证：</td>
            <td colspan="3"><html-el:text property="id_card" styleId="id_card" maxlength="80" size="40"/>&nbsp;</td>
          </tr>
          <tr>
            <td width="15%" valign="middle" nowrap="nowrap" class="title_item" align="right">所在地：</td>
            <td width="85%" valign="middle" nowrap="nowrap">
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
              </select>
            </td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">联系地址：</td>
            <td colspan="3">
            <html-el:text property="addr" styleId="addr" maxlength="130" size="80"/>&nbsp; 
            </td>
          </tr>
          
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">选填</td>
            <td colspan="3"></td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">生日：</td>
            <td colspan="3">
            <fmt:formatDate var="_birthday" value="${af.map.birthday}" pattern="yyyy-MM-dd"/>
            <html-el:text property="birthday" value="${_birthday}" styleId="birthday" size="10" maxlength="10" />
            </td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">婚姻：</td>
            <td><label for="marriage1" style="width:80px;">
              <html-el:radio property="marriage" styleClass="marriage" styleId="marriage1" value="1" >已婚</html-el:radio>
              </label>
              <label for="marriage2" style="width:80px;">
              <html-el:radio property="marriage" styleClass="marriage" styleId="marriage2" value="2">未婚</html-el:radio>
              </label>
              <label for="marriage3" style="width:80px;">
              <html-el:radio property="marriage" styleClass="marriage" styleId="marriage3" value="3" >保密</html-el:radio>
              </label></td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">月收入：</td>
            <td >
             	<html-el:select styleId="income" property="income">
                <html-el:option value="1">1000以下</html-el:option>
                <html-el:option value="2">1000-2000</html-el:option>
                <html-el:option value="3">2000-4000</html-el:option>
                <html-el:option value="4">4000-6000</html-el:option>
                <html-el:option value="5">6000-10000</html-el:option>
                <html-el:option value="6">10000-20000</html-el:option>
                <html-el:option value="7">20000以上</html-el:option>
              </html-el:select>
             </td>
          </tr>
          <tr>
          <td nowrap="nowrap" height="28" class="title_item">兴趣爱好：</td>
          <td><html-el:textarea property="hobby" styleId="hobby" cols="77" rows="5"/>
            <span class="note"> 最多可输入150个字</span></td>
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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	// 区域
	$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${province}","dataType":"Require","msg":"请选择省/直辖市/自治区！"});
	$("#city").attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${city}"});
	$("#country").attr({"subElement": "town","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${country}"});
	$("#town").attr({"defaultText": "-请选择乡/镇-", "defaultValue": "", "selectedValue": "${town}"});
	
	$("#province").cs_ext("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false,"p_index_join","${af.map.p_index_join}");
	$("#province").change();

	$("#now_user_sn").attr("datatype", "Require").attr("msg", "请填写会员卡号！");
	$("#user_sn").attr("datatype", "Require").attr("msg", "请填写会员卡号！");
	$("#user_level").attr("datatype", "Require").attr("msg", "请选择会员类型！");	
	$("#user_name").attr("datatype", "Require").attr("msg", "请填写会员姓名！");
	
	$("#id_card").attr("datatype", "IdCard").attr("msg", "请填写正确的身份证！");
	$("#email").attr("datatype", "Email").attr("msg", "请填写正确的邮箱！").attr("require", "false");
	$("#ph" ).attr("datatype", "Mobile").attr("msg", "请填写正确的手机号码！").attr("require", "false");

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

}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
