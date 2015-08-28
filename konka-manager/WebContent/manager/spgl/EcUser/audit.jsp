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
<link href="${ctx}/styles/customer/multiselect/jquery.multiselect2side.css" rel="stylesheet" type="text/css" />
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
    <html-el:form action="/spgl/EcUser.do" enctype="multipart/form-data">
      <html-el:hidden property="user_id" value="${af.map.user_id}" />
      <html-el:hidden property="method" value="save1" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="queryString" />
      <html-el:hidden property="returnUrl" />
      <div align="left">
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="datagrid">
          <tr>
            <td align="right" class="title_item">用户类型：</td>
            <td align="left">
                <c:if test="${af.map.user_type eq 1}">工卡会员</c:if>
                <c:if test="${af.map.user_type eq 2}">触网会员</c:if>
	        </td>
          </tr>
          <tr>
            <td align="right" class="title_item">会员等级：</td>
            <td align="left">
                ${level_name}
	        </td>
          </tr>
            <tr>
              <td align="right" class="title_item">登录名：</td>
              <td align="left">${af.map.user_name}
                &nbsp;</td>
            </tr>
          <tr>
            <td align="right" class="title_item">真实姓名：</td>
            <td align="left">
            ${af.map.real_name}
              &nbsp;</td>
          </tr>
          <tr>
            <td align="right" class="title_item">性别：</td>
            <td align="left"><c:if test="${af.map.sex eq 0}">男</c:if>
            <c:if test="${af.map.sex eq 1}">女</c:if>
            <c:if test="${af.map.sex eq 2}">保密</c:if></td>
          </tr>
          <tr>
          <td align="right" class="title_item">居住地：</td>
          <td align="left">
            <select name="province" id="province" class="bd" disabled="disabled">
              <option value="">-请选择省/直辖市/自治区-</option>
            </select>
            <select name="city" id="city" class="bd" disabled="disabled">
              <option value="">-请选择市-</option>
            </select>
            <select name="country" id="country" class="bd" disabled="disabled">
              <option value="">-请选择县-</option>
            </select>
            <select name="town" id="town" class="bd" disabled="disabled">
              <option value="">-请选择乡/镇-</option>
            </select></td>
          </tr>
         <tr>
            <td align="right" class="title_item">出生日期：</td>
            <td align="left"><fmt:formatDate value="${af.map.birthday}" pattern="yyyy-MM-dd" /></td>
          </tr>
          <tr>
            <td align="right" class="title_item">联系地址：</td>
            <td align="left">
            ${af.map.link_addr}
            </td>
          </tr>
          <tr>
            <td align="right" class="title_item">邮编：</td>
            <td align="left">
            ${af.map.link_post}
            </td>
          </tr>
          <tr>
            <td align="right" class="title_item">手机：</td>
            <td  align="left">
            ${af.map.link_phone}
            </td>
          </tr>
           <tr>
            <td align="right" class="title_item">电话：</td>
            <td  align="left">
            ${af.map.link_tel}
            </td>
          </tr>
          <tr>
            <td align="right" class="title_item">邮箱：</td>
            <td align="left">
             ${af.map.email}
           </td>
          </tr>
          <tr>
            <td align="right"  class="title_item">开户名：</td>
            <td align="left" >
              ${af.map.bank_account_name}
            </td>
           </tr>
          <tr>  
         	<td align="right" class="title_item">开户银行：</td>
            <td align="left">
            	${af.map.bank_name}
            </td>
          </tr>
          <tr>
            <td align="right"  class="title_item">银行账号：</td>
            <td align="left">
            ${af.map.bank_account}
            </td>
          </tr>
           <tr>
            <td align="right"  class="title_item">工卡卡号：</td>
            <td align="left">
            ${af.map.card_no}
            </td>
          </tr>
           <tr>
            <td align="right"  class="title_item">审核情况：</td>
            <td align="left"><html-el:select property="is_act" styleId="is_act"  >
            		<html-el:option value="">请选择</html-el:option>
            		<html-el:option value="0">审核通过</html-el:option>
            		<html-el:option value="3">审核不通过</html-el:option>
            	</html-el:select>
            </td>
          </tr>
          <tr>
            <td  align="center" class="title_item" colspan="2"><input class="but4" type="button" name="Submit4" value=" 保存 " id="btn_submit" />
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
<script type="text/javascript" src="${ctx}/styles/customer/multiselect/jquery.multiselect2sideYwy.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
		$("#is_act").attr("datatype", "Require").attr("msg", "请审核！");
		$("#btn_submit").click(function(){
			if(Validator.Validate(this.form, 3)) {
				$("#btn_submit"   ).attr("disabled",true);
				$("#reset").attr("disabled",true);
				$("#btn_back"     ).attr("disabled",true);
				this.form.submit();
			}
		});

	    // 区域
		$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "","datatype": "Require", "msg": "请选择省名称！","selectedValue": "${province}"});
		$("#city"    ).attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "","datatype": "Require", "msg": "请选择市！","selectedValue": "${city}"});
		$("#country" ).attr({"subElement": "town","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${country}"});
		$("#town"    ).attr({"defaultText": "-请选择乡/镇-", "defaultValue": "", "selectedValue": "${town}"});

		$("#province").cs_ext("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false,"p_index_join","${p_index_join}");
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
<jsp:include page="/__analytics.jsp" />
</body>
</html>
