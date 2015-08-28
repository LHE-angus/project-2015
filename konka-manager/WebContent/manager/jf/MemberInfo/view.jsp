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
      <html-el:hidden property="method" value="memberCardStop" />
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="queryString" />
      <div align="left">
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
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
            <td nowrap="nowrap"  height="28" class="title_item" align="right">会员卡号：</td>
            <td colspan="3"><c:out value="${af.map.user_sn}" /></td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">会员类型：</td>
            <td >
             	<c:out value="${af.map.user_level eq 0 ? '个人会员' : (af.map.user_level eq 1 ? '白银会员' : (af.map.user_level eq 2 ? '黄金会员' : '钻石会员'))}" />
             </td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">姓名：</td>
            <td colspan="3"><c:out value="${af.map.user_name}" /></td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">性别：</td>
            <td><c:out value="${af.map.sex eq 1 ? '男' : (af.map.sex eq 2 ? '女' : '保密')}" /></td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">手机：</td>
            <td colspan="3"><c:out value="${af.map.mp}" /></td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">邮箱：</td>
            <td colspan="3"><c:out value="${af.map.email}" /></td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">身份证：</td>
            <td colspan="3"><c:out value="${af.map.id_card}" /></td>
          </tr>
          <tr>
            <td width="15%" valign="middle" nowrap="nowrap" class="title_item" align="right">所在地：</td>
            <td colspan="3">
            <c:out value="${p_index_name}" /><c:if test="${empty p_index_name}"><span style="color:#999;">未填写</span></c:if>
            </td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">联系地址：</td>
            <td colspan="3">
            <c:out value="${af.map.addr}" />
            </td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">生日：</td>
            <td colspan="3">
            <fmt:formatDate value="${af.map.birthday}" pattern="yyyy-MM-dd" /><c:if test="${empty af.map.birthday}"><span style="color:#999;">未填写</span></c:if>
            </td>
          </tr>
          
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">婚姻：</td>
            <td><c:out value="${af.map.marriage eq 1 ? '已婚' : (af.map.marriage eq 2 ? '未婚' : '保密')}" /></td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">月收入：</td>
            <td >
             	<c:if test="${af.map.income eq 1}">1000以下</c:if>
            	<c:if test="${af.map.income eq 2}">1000-2000</c:if>
            	<c:if test="${af.map.income eq 3}">2000-4000</c:if>
            	<c:if test="${af.map.income eq 4}">4000-6000</c:if>
            	<c:if test="${af.map.income eq 5}">6000-10000</c:if>
            	<c:if test="${af.map.income eq 6}">10000-20000</c:if>
            	<c:if test="${af.map.income eq 7}">20000以上</c:if>
             </td>
          </tr>
          <tr>
          <td nowrap="nowrap" height="28" class="title_item">兴趣爱好：</td>
          <td><c:out value="${af.map.hobby}" /></td>
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
