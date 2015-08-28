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
<style type="text/css">
select{font-family:Microsoft YAHEI;font-size:12px;}
input{font-family:Microsoft YAHEI;font-size:12px;}
label {cursor:pointer;}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div style="width:100%">
	<div class="oartop">
	    <table width="400" border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
	        <td>当前位置：${naviString}</td>
	      </tr>
	    </table>
  	</div>
  	<div class="rtabcont2">
  		<c:if test="${not empty no_user_tip}">
  		<div>
  		<table cellpadding="5" width="100%" cellspacing="8px" class="noteMacro" border="0" align="center" style="background-color:#FFFFBB;">
		  <tr>
		    <td width="40" align="center" valign="middle" height="25"><img src="${ctx}/commons/styles/message/images/warning.gif" width="16" height="16" style="vertical-align:middle;" alt="" border="0" /></td>
		    <td><p>业务员 ${customerUserInfo.real_name}，您好！该客户暂没有分配登录用户，无法登录康佳客户端，系统提醒您请尽快分配登录用户并告知。</p></td>
		  </tr>
		</table>
  		</div>
  		</c:if>
	  	<html-el:form action="/admin/KonkaPartership" enctype="multipart/form-data">
			<html-el:hidden property="method" value="save" styleId="method"/>
	      	<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	      	<html-el:hidden property="id" styleId="id" value="${af.map.id}"/>
	      	<html-el:hidden property="queryString" />
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="rtable3">
		    	<tr>
		            <th width="15%"  height="45" style="font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';color: red;"><span>合作伙伴关系</span></th>
					<th colspan="3" >&nbsp;</th>
		        </tr>
		    </table>
		    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="rtable3">
				 <tr>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>售达方&nbsp;
<!-- 					<span style="color:red;" onclick="getKonkaR3List();">选择</span> -->
						<input class="but2" type="button" name="Submit2" value="选择" onclick="getKonkaR3List();" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td class="title_item" align="left" nowrap="nowrap">&nbsp;</td>
					<td class="title_item" align="left" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">* </font>送达方&nbsp;
<!-- 					<span style="color:red;" onclick="getKonkaOtherR3List();">选择</span> -->
						<input class="but2" type="button" name="Submit3" value="选择" onclick="getKonkaOtherR3List();" />
					</td>
				 </tr>
				 <tr>
					<td class="title_item" align="right" nowrap="nowrap">
						<html-el:text property="shoudf_id" styleId="shoudf_id" style="width:200px;" maxlength="30" readonly="true"/>
					</td>
					<td class="title_item" align="left" nowrap="nowrap">&nbsp;</td>
					<td class="title_item" align="left" nowrap="nowrap">
						<html-el:text property="songdf_id" styleId="songdf_id" style="width:200px;" maxlength="30" readonly="true"/>
					</td>
				</tr>
		        <tr>
		          <td colspan="3" align="center">
		          
		            <input type="button" name="temp_save" class="bgButtonSave" value="保存" id="btn_temp_save"/>
		            <input class="bgButtonBack" type="reset" name="reset" value="返回" id="btn_back" onclick="history.back();return false;"/>
		            <br/>
		            <div style="height: 50px">&nbsp;</div>
		          </td>
		        </tr>
		    </table>
		</html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.jxc.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/external/bgiframe/jquery.bgiframe.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script> 
<script type="text/javascript">//<![CDATA[                                       
var f = document.forms[0];
$(document).ready(function(){

	$("#shoudf_id").attr("dataType", "Require").attr("msg", "请填写售达方");
// 	$("#shoudf_name").attr("dataType", "Require").attr("msg", "请选择售达方名称");
	$("#songdf_ids").attr("dataType", "Require").attr("msg", "请选择送达方");
// 	$("#songdf_name").attr("dataType", "Require").attr("msg", "请填写送达方名称");
	
 
 $("#btn_temp_save").click(function() {
	 if(Validator.Validate(this.form, 3)){
		this.form.submit();
	 }
 });
 
});

function getKonkaR3List() { 
	var returnValue = window.showModalDialog("${ctx}/manager/admin/KonkaPartership.do?method=shouDFList&azaz=" + Math.random(),window,"dialogWidth:650px;status:no;dialogHeight:390px");
	if (!returnValue) returnValue = window.returnValue;
	$("#shoudf_id").val(returnValue.r3_code);
}
	
function getKonkaOtherR3List() { 
	var returnValue = window.showModalDialog("${ctx}/manager/admin/KonkaPartership.do?method=shouDFList&azaz=" + Math.random(),window,"dialogWidth:650px;status:no;dialogHeight:390px");
	if (!returnValue) returnValue = window.returnValue;
	$("#songdf_id").val(returnValue.r3_code);
// 	if (returnValue != null && returnValue.ids != "") {
// 	    var r3_code_array = new Array();
// 	    var r3_code_name_array = new Array();
	
// 	    r3_code_array = returnValue.ids.split(",");
// 	    r3_code_name_array = returnValue.names.split(",");
		
// 	    if(r3_code_array.length>10){
// 	    	alert("送达方超过10位，请重新选择！");
// 	    } else {
// 		   var  r3_codes = '';
// 		   for(var i = 0; i<r3_code_array.length; i++){
// 			   var code = "";
// 			   code = r3_code_array[i];
// 			   r3_codes += code + ",\n";
// 		   }
// 		    $("#songdf_ids").attr("value",r3_codes);
// 	    }
// 	}
}	

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>