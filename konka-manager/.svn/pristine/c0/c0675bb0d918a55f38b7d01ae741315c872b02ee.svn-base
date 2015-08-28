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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
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
    <html-el:form action="/admin/SetUserInfo">
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">网点名称：</td>
          <td width="88%" align="left"><c:out value="${af.map.shop_name}" /></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">地区邮编：</td>
          <td width="88%" align="left"><c:out value="${af.map.post_code}" /></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">所属地区：</td>
          <td width="88%" align="left"><c:forEach var="cur" items="${baseProvinceListList}">${cur.p_name}</c:forEach></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">网点地址：</td>
          <td width="88%" align="left"><c:out value="${af.map.street_addr}" /></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">联系人：</td>
          <td width="88%" align="left"><c:out value="${af.map.link_user}" /></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">联系电话：</td>
          <td width="88%" align="left"><c:out value="${af.map.link_phone}" /></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">网点级别：</td>
          <td width="88%" align="left"><c:choose>
                  <c:when test="${af.map.shop_level eq 0}">普通网点</c:when>
                  <c:when test="${af.map.shop_level eq 1}">铜牌网点</c:when>
                  <c:when test="${af.map.shop_level eq 3}">金牌网点</c:when>
                </c:choose></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();return false;" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont3"></div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#user_name").attr("dataType", "Require").attr("msg", "请填写用户名");
	$("#password" ).attr("dataType", "Require").attr("msg", "请填写密码");
	$("#real_name" ).attr("dataType", "Require").attr("msg", "请填写真实姓名");
	$("#post_id" ).attr("dataType", "Require").attr("msg", "请选择职务");
	
	String.prototype.trim = function(){
        return this.replace(/(^\s*)|(\s*$)/g, "");
    }

	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 3)){
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_reset").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});
});

<c:if test="${af.map.user_type eq 20}">
	$("#area_link").removeAttr("display").show();
	$("#par_code" ).attr("dataType", "Require").attr("msg", "请选择关联地区");
</c:if>

$("#user_type").change(function(){
	if(this.value == 20){
		$("#area_link").removeAttr("disabled").removeAttr("display").show();
		$("#par_code" ).attr("dataType", "Require").attr("msg", "请选择关联地区");
	} else {
		$("#area_link").attr("display","none").attr("disabled","disabled").hide();
		$("#par_code" ).removeAttr("dataType");
	}
});

$("#par_code").change(function(){
	if(this.value.length != 0){
		$("#area_code").cs("${ctx}/Cs.do?method=getBaseAreaInfoList", "par_code", this.value, false);	
	} else {
		$("#area_code").get(0).options.length = 1;
		$("#area_code").get(0).options[0].text = "请选择...";
		$("#area_code").get(0).options[0].value = "";
	}
});
$("#par_code").attr("subElement", "area_code").attr("defaultText", "请选择...").attr("defaultValue", "").attr("selectedValue", "${af.map.par_code}");
$("#area_code").attr("defaultText", "请选择...").attr("defaultValue", "").attr("selectedValue", "${af.map.area_code}");
$("#par_code").trigger("change");

var f = document.forms[0];
$("#user_name").blur(function(){
	if(null == f.user_name.value){
		return ;
	}
	$.ajax({
		type: "POST",
		url: "UserInfo.do",
		data: "method=validateUsername&user_name=" + f.user_name.value,
		dataType: "json",
		error: function(request, settings) {},
		success: function(oper) {
			if(oper.result){
				$("#user_name").val("");
				alert("系统已存在此用户");
				f.user_name.focus();				
			}else {
				return;
			}
		}
	});
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
