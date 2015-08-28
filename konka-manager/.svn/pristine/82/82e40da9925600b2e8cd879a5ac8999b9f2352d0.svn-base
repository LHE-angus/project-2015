<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>信息接收 &gt; ${navString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
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
-->
</style>
</head>
<body>
<div class="oarcont">
	<div class="oartop">
	  <table width="500" border="0" cellpadding="0" cellspacing="0">
	    <tr>
	      <td width="20"><img src="${ctx}/images/manager/arrow3.gif" alt="" style="vertical-align:middle;" /></td>
	      <td>当前位置：当前位置：&nbsp;&gt;&nbsp;信息接收&nbsp;&gt;&nbsp;站内信回复</td>
	    </tr>
	  </table>
	</div>
	<div class="rtabcont1">
	    <%@ include file="/jxc/JxcReceivePeShopMsg/shop_msg_top.jsp" %>
	  </div>
	<div class="rtabcont1">
	  <html-el:form action="/JxcReceivePeShopMsg">
	    <html-el:hidden property="method" value="saveReply" />
	    <input type="hidden" name="keySeq" id="keySeq" value="${af.map.keySeq}" />
	    <input type="hidden" name="par_id" id="par_id" value="${entity.id}" />
	    <html-el:hidden property="state" styleId="state" />
	    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
	    <c:if test="${not empty entity}">
	      <tr>
	        <th colspan="2" align="right"><b>站内信原文</b></th>
	      </tr>
	      <tr >
	        <td height="28" width="15%" nowrap="nowrap" class="title_item">发件人：</td>
	        <td><c:out value="${entity.send_user_name}" /></td>
	      </tr>
	      <tr >
	        <td height="28" width="15%" nowrap="nowrap" class="title_item">标题：</td>
	        <td><c:out value="${entity.title}" /></td>
	      </tr>
	      <tr >
	        <td height="28" width="15%" nowrap="nowrap" class="title_item">内容：</td>
	        <td><c:out value="${entity.content}" /></td>
	      </tr>
	      <tr >
	        <td height="28" width="15%" nowrap="nowrap" class="title_item">发送时间：</td>
	        <td><fmt:formatDate value="${entity.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	      </tr>
	    </c:if>
	    <c:if test="${empty entity }">
	     <tr><th colspan="2" align="right" ><b>站内信原文被发件人删除</b></th></tr>
	    </c:if>
	      <tr>
	        <th colspan="2" align="right"><b>站内信回复</b></th>
	      </tr>
	      <tr>
	        <td nowrap="nowrap" class="title_item" width="15%">标&nbsp;&nbsp;&nbsp;&nbsp;题：</td>
	        <td width="85%"><c:if test="${not empty replyEntity}">
	            <html-el:hidden property="id" styleId="id" value="${replyEntity.id}"/>
	            <html-el:text property="title" styleId="title" style="width:300px" maxlength="60" size="40" value="${replyEntity.title}" onkeyup="limitTitleLength()"/>
	          </c:if>
	          <c:if test="${empty replyEntity}">
	            <html-el:text property="title" styleId="title" style="width:300px" maxlength="60" size="40" value="回复：${entity.title}" onkeyup="limitTitleLength()"/>
	          </c:if>
	          &nbsp;<span style="color:red">*</span> <font style="font-size: 12px;color: gray" id="title_msg">不超过30个汉字</font></td>
	      </tr>
	      <tr>
	        <td nowrap="nowrap" class="title_item">内&nbsp;&nbsp;&nbsp;&nbsp;容：</td>
	        <td><c:if test="${not empty replyEntity}">
	            <html-el:textarea property="content" styleId="content"  style="height:200px;width:600px;" value="${replyEntity.content }" onkeyup="limitContentLength()"/>
	          </c:if>
	          <c:if test="${empty replyEntity}">
	            <html-el:textarea property="content" styleId="content"  style="height:200px;width:600px;" value="${entity.content }" onkeyup="limitContentLength()"/>
	          </c:if>
	          &nbsp;<span style="color:red">*</span> <br />
	          <font style="font-size: 12px;color: gray" id="content_msg">不超过500个汉字</font></td>
	      </tr>
	      <tr>
	        <td colspan="2" align="center"><input type="button" name="save" class="bgButtonSave" value="发送" id="btn_submit"/>
	          <input class="but2" type="button" name="Submit5" id="btn_zc" value="暂存" onclick="msg_save();"/>
	          <html-el:button property="back" styleId="back" styleClass="bgButtonBack" value="返回" onclick="history.back();" /></td>
	      </tr>
	    </table>
	  </html-el:form>
	</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
var f = document.forms[0];
$(document).ready(function(){
	$("#title").attr("dataType", "Require").attr("msg", "请输入标题！");
	$("#content").attr("dataType", "Require").attr("msg", "请填写短信息内容！");

	
	$("#btn_submit").click(function(){
		limitTitleLength();
		limitContentLength();
		if( Validator.Validate(f, 3)){
			$("#state").val(1);
			$("#btn_submit").attr("value", "提交中").attr("disabled", "true");
	        $("#btn_zc").attr("disabled", "true");
	        $("#back").attr("disabled", "true");
			f.submit();
		}
	});

});

function msg_save(){
	limitTitleLength();
	limitContentLength();
	if( Validator.Validate(f, 3)){
		$("#state").val(0);
		$("#btn_submit").attr("value", "提交中").attr("disabled", "true");
	    $("#btn_zc").attr("disabled", "true");
	    $("#back").attr("disabled", "true");
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
//]]>--></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
