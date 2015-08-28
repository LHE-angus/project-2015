<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
.areause1 {
}
.areause1 td {
	border-bottom: 0px solid #E3E3E3;
	border-right: 0px solid #E3E3E3;
	padding: 0px 0px 0px;
}
#areaList0 {
}
#areaList1 {
}
#areaList0 td {
	border-bottom: 2px dotted #FFDCB9;
	padding-left:5px;
}
#areaList1 td {
	border-bottom: 2px dotted #FFDCB9;
	padding-left:5px;
}
</style>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="20"><img src="${ctx}/images/manager/arrow3.gif" style="vertical-align:middle;" /></td>
        <td>当前位置：&nbsp;&gt;&nbsp;信息接收&nbsp;&gt;&nbsp;站内信息&nbsp;&gt;&nbsp;发送短消息</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <%@ include file="/jxc/JxcReceivePeShopMsg/shop_msg_top.jsp" %>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/JxcReceivePeShopMsg.do" enctype="multipart/form-data" >
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="keySeq" styleId="keySeq" value="${af.map.keySeq}" />
      <html-el:hidden property="state" styleId="state" />
      <html-el:hidden property="id" styleId="id" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td nowrap="nowrap" class="title_item" width="15%">标&nbsp;&nbsp;&nbsp;&nbsp;题：</td>
          <td width="85%"><html-el:text property="title" styleId="title" style="width:300px" maxlength="60" size="40"  onkeyup="limitTitleLength()"/>
            &nbsp;<span style="color:red">*</span> <br />
            <font style="font-size: 12px;color: gray" id="title_msg">不超过30个汉字</font></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">内&nbsp;&nbsp;&nbsp;&nbsp;容：</td>
          <td><html-el:textarea property="content" styleId="content"  style="height:200px;width:600px;"  onkeyup="limitContentLength()"/>
            &nbsp;<span style="color:red">*</span> <br />
            <font style="font-size: 12px;color: gray" id="content_msg">不超过500个汉字</font></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="left">发件方式：</td>
          <td><html-el:select property="receive_user_type" styleId="receive_user_type">
              <html-el:option value="1" >分公司指定人员</html-el:option>
              <html-el:option value="0">分公司全部人员</html-el:option>
            </html-el:select>
            &nbsp;<span style="color:red">*</span></td>
        </tr>
        <tr id="select_1" 
        <c:if test="${af.map.receive_user_type eq 0}"> style="display: none;"</c:if>
        >
        <td nowrap="nowrap" class="title_item" align="left">选择收件分公司角色：</td>
          <td><html-el:textarea property="role_names" styleId="role_names"  readonly="true"  style="width:240px;height:60px;" />
            &nbsp;
            <input id="gsBTN" type='button' class="but6" value='' onclick="getRole();"/>
            <html-el:hidden property="role_ids" styleId="role_ids" />
            &nbsp;<span style="color:red">*</span></td>
        </tr>
        <tr id="select_2" 
        <c:if test="${af.map.receive_user_type eq 0}"> style="display: none;"</c:if>
        >
        <td nowrap="nowrap" class="title_item" align="left">选择分公司收件人：</td>
          <td><html-el:textarea property="user_names" styleId="user_names"  readonly="true"  style="width:240px;height:60px;" />
            &nbsp;
            <input id="gsBTN" type='button' class="but6" value='' onclick="getUesrInfoList();"/>
            <html-el:hidden property="user_ids" styleId="user_ids" />
            &nbsp;<span style="color:red">*</span></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><input class="but4" type="button" name="Submit5" value="发送" onclick="msg_save(1)" />
            <input class="but2" type="button" name="Submit5" value="暂存" onclick="msg_save(0)"  />
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<%@ include file="/commons/pages/areamove.jsp"%>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#title").attr("dataType", "Require").attr("msg", "请输入标题！");
	$("#content").attr("dataType", "Require").attr("msg", "请填写短信息内容！");
    $("#receive_user_type").attr("dataType", "Require").attr("msg", "请选择发件方式");
    <c:if test="${af.map.receive_user_type eq 0}"> 
    $("#role_ids").attr("dataType", "Require").attr("msg", "请选择收件分公司角色!");
    $("#user_ids").attr("dataType", "Require").attr("msg", "请选择分公司收件人!");
    </c:if>
   
	$("#receive_user_type").change(function(){if(this.value==0){$("#select_1, #select_2").hide();}else{$("#select_1, #select_2").show();}});
  
});

var f = document.forms[0];
function msg_save(type){
	$("#state").val(type);
	if( Validator.Validate(f, 3)){
		f.submit();
	}
}

function limitTitleLength(){
	var len = strlen($("#title").val());
	if(len>60){
		document.getElementById("title_msg").style.color="red";
		checkbutton();
		return;
	}else{
		document.getElementById("title_msg").style.color="gray";
		checkbutton();
		return;
	}
	
}
function limitContentLength(){
	var len = strlen($("#content").val());
	if(len>1000){
		document.getElementById("content_msg").style.color="red";
		checkbutton();
		return;
	}else{
		document.getElementById("content_msg").style.color="gray";
		checkbutton();
		return;
	}
	
}
function checkbutton(){
	if(strlen($("#content").val())>1000||strlen($("#title").val())>60){
		$(":button").attr("disabled","true");
	}else{
		$(":button").removeAttr("disabled");
	}
}

function strlen(str) {   
    var len = 0;   
    for (var i = 0; i < str.length; i++) {
        if (str.charCodeAt(i) > 255 || str.charCodeAt(i)<0) {
        	len += 2; 
        }
        else len ++;   
    }   
    return len;   
};  

//新添加 js
function getRole(){
	var selectedIds = $("#role_ids").val();
	var returnValue = window.showModalDialog("JxcReceivePeShopMsg.do?method=list_role&keySeq=${af.map.keySeq}&selectedIds="+selectedIds +  "&azaz=" + Math.random(),window,"dialogWidth:400px;status:no;dialogHeight:300px");
	if (returnValue != null) {
		$("#role_ids").val(returnValue.ids);
		$("#role_names").val(returnValue.names);
	};	
}
function getUesrInfoList(){
	var role_ids = $("#role_ids").val();
	var selectedIds = $("#user_ids").val();
	var dept_id_fgs = ${dept_id_fgs};
	if(role_ids == ''){
		alert("请先选择收件角色!");
		return false;
	}
	if(dept_id_fgs == ''){
		alert("您所在的网点所属分公司不明确，请联系分公司有关人员!");
		return false;
	}
	
	var returnValue = window.showModalDialog("JxcReceivePeShopMsg.do?method=list_user&keySeq=${af.map.keySeq}&role_ids="+role_ids+"&dept_id_fgs="+ dept_id_fgs + "&selectedIds="+ selectedIds + "&azaz=" + Math.random(),window,"dialogWidth:600px;status:no;dialogHeight:500px");
	if (returnValue != null) {
		$("#user_ids").val(returnValue.ids);
		$("#user_names").val(returnValue.names);
	};	
}


//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
