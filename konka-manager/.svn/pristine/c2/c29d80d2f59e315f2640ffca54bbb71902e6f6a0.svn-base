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
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
      <td nowrap="nowrap">当前位置：${naviString}</td>
    </tr>
  </table>
  <div class="rtabcont2">
    <html-el:form action="/zmd/KonkaXxUserInfoUpdate.do" enctype="multipart/form-data">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <html-el:hidden property="queryString" />
      <div align="left">
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable4">
          <tr>
            <td colspan="4">基础信息</td>
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
            <td colspan="3"  height="40" align="left"><input class="but4" type="button" name="Submit4" value=" 保存 " id="btn_submit" />
              <input class="but3" type="reset"  value="重填 " id="reset" />
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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript">//<![CDATA[
	$(document).ready(function(){
		
		$("#user_name").attr("datatype", "Require").attr("msg", "请输入用户名！");
		$("#real_name").attr("datatype", "Chinese").attr("msg", "真实姓名只允许中文！");
		
		$("#link_msn").attr("datatype", "Email").attr("msg", "请填写正确的MSN！").attr("require", "false");
		$("#email").attr("datatype", "Email").attr("msg", "请填写正确的邮箱！").attr("require", "false");
		$("#link_phone" ).attr("datatype", "Mobile").attr("msg", "请填写正确的手机号码！").attr("require", "false");
		$("#link_tel" ).attr("datatype", "Phone").attr("msg", "请填写正确的电话号码！").attr("require", "false");
		$("#link_qq" ).attr("focus",setOnlyNum);
		$("#link_post" ).attr("focus",setOnlyNum);
		$("#birthday").datepicker();
		
		$("#btn_submit").click(function(){
			if(Validator.Validate(this.form, 3)){
				$("#btn_submit"   ).attr("disabled",true);
				$("#reset").attr("disabled",true);
				$("#btn_back"     ).attr("disabled",true);
				this.form.submit();
			}
		});

		 // 区域
		$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${af.map.province}"});
		$("#city"    ).attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${af.map.city}"});
		$("#country" ).attr({"subElement": "town","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${af.map.country}"});
		$("#town"    ).attr({"defaultText": "-请选择乡/镇-", "defaultValue": "", "selectedValue": "${af.map.town}"});

		$("#province").cs_ext("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false,"p_index_join","${af.map.p_index_join}");
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

//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
