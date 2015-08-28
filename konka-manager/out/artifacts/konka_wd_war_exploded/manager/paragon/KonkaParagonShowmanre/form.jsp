<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
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
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
	<div class="rtabcont2">
		<html-el:form action="/paragon/KonkaParagonShowmanre" enctype="multipart/form-data">
			<html-el:hidden property="re_id" styleId="re_id" />
			<html-el:hidden property="mod_id" styleId="mod_id" />
			<html-el:hidden property="method" styleId="method" value="save" />
			<html-el:hidden property="queryString" styleId="queryString" />
			<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
				<tr>
					<td nowrap="nowrap" class="title_item" >数据上报员：</td>
					<td  width="85%" >
						<html-el:text property="user_name" size="40" maxlength="30" styleId="user_name" disabled="true" value="${af.map.map.sales_name}"/>&nbsp;
						 <input id="gsBTN" type='button' value='选择' onclick="getSalesMan(this);" class="but2" />
	               		 <html-el:hidden property="user_id" styleId="user_id"/>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" >关联门店     ：</td>
					<td width="85%" >
						 <input id="gsBTN" type='button' value='匹配' onclick="getShowInfo(this);" class="but2" />
	               	 </td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><label>
		            <input class="but5" type="button" name=btn_back value="返回" onclick="goBack();" />
		          </label></td>
				</tr>
			</table>
		</html-el:form>
	</div>
	  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 3)){
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
			this.form.submit();
		}
	});
});

function getSalesMan(obj) {
	var $p = $(obj).parent();
	var returnValue = window.showModalDialog("SelectSalesMan.do?selects=" + $("#user_id").val() + "&selectype=signal&azaz=" + Math.random(), window, "dialogWidth:700px;status:no;dialogHeight:500px");
	if (returnValue != null) {
		$("#user_name", $p).val(returnValue.names);
		$("#user_id", $p).val(returnValue.ids);
	}
}

function getShowInfo(obj) {
	if($("#user_id").val()==null || $("#user_id").val()==""){
		alert('请选择数据上报员');
	}else{
		this.location.href = 'SelectShowInfo.do?user_id='+$("#user_id").val()+"&mod_id="+$("#mod_id").val() ;
	}
}
function goBack(){
	this.location.href = 'KonkaParagonShowmanre.do?user_id='+$("#user_id").val()+"&mod_id=506000";
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
