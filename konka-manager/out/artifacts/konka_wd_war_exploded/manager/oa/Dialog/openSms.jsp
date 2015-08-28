<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>选择人员</title>
<base target="_self" />
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="rtabcont1">
    <html-el:form action="/oa/DiaLog">
        <html-el:hidden property="queryString" styleId="queryString" />
        <html-el:hidden property="method" styleId="method" value="sendSms" />
        <html-el:hidden property="mod_id" styleId="mod_id" />
        <html-el:hidden property="id" styleId="id" />
        <div style="height:5px;"></div>
      <table width="100%" border="0" align="left" class="list">
          <tr>
            <td>接收人：${peProdUser.real_name}　　手机：${peProdUser.link_phone}</td>
          </tr>
          <tr>
            <td><html-el:textarea property="sms_comment" styleId="sms_comment" style="width:430px;height:70px;" value="${fn:escapeXml(KonkaoaFiles.submit_user)} 提交， ${fn:escapeXml(KonkaoaFiles.apply_user_name)} 负责的文件 《${fn:escapeXml(KonkaoaFiles.file_title)}》，请您及时办理。"></html-el:textarea></td>
          </tr>
          <tr>
            <td style="text-align:right;"><html-el:button property="" value="发 送" styleClass="but4" styleId="btn_submit" />
              <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="window.close();" /></td>
          </tr>
        </table>
      </html-el:form>
    </div>
  </div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#sms_comment" ).attr({"dataType":"Limit","min":"1","max":"65","msg":"请填写催办短信且字数不能超过于65个"});
	
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 1)){
            $("#btn_submit").attr("value", "正在发送...").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
            this.form.submit();
        
            //window.close();
        }	
	});

});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
