<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>绑定手机</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div style="width:100%">
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
    <table width="50%" border="0" cellpadding="0" cellspacing="1" class="tableClass" align="center">
      <tr>
        <th colspan="2">商铺信息</th>
      </tr>
      <tr>
        <td width="5%" class="title_item">登录名</td>
        <td width="45%">${fn:escapeXml(user.user_name)}</td>
      </tr>
      <tr>
        <td width="5%" class="title_item">商铺名称</td>
        <td width="45%">${fn:escapeXml(entpShopName)}</td>
      </tr>
      <c:if test="${user.map.is_binding eq 1}">
      	<tr>
          <td width="5%" class="title_item">已绑定号码</td>
          <td width="45%">${fn:escapeXml(konkaJxcBindingMobile.mobile)}</td>
      	</tr>
      </c:if>
      <tr>
        <c:if test="${user.map.is_binding eq 0}">
          <td width="5%" class="title_item"><html-el:button property="" value="绑定手机" styleId="binding_mobile"></html-el:button></td>
          <td width="45%"><html-el:button property="" value="解除绑定" styleId="delete_mobile" disabled="true"></html-el:button></td>
        </c:if>
        <c:if test="${user.map.is_binding eq 1}">
          <td width="5%" class="title_item"><html-el:button property="" value="更换号码" styleId="update_mobile"></html-el:button></td>
          <td width="45%"><html-el:button property="" value="解除绑定" styleId="delete_mobile"></html-el:button></td>
        </c:if>
      </tr>
      <tr id="div_mobile" style="display: none;">
        <td colspan="2"><html-el:form action="/KonkaJxcBindingMobile.do">
            <c:if test="${user.map.is_binding eq 1}">
              <html-el:hidden property="id" value="${konkaJxcBindingMobile.id}" />
            </c:if>
            <html-el:hidden property="method" value="save" />
            <html-el:hidden property="keySeq" styleId="keySeq" value="${af.map.keySeq}"/>
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
              <tr>
                <td width="5%" nowrap="nowrap" class="title_item">手机号码：</td>
                <td width="45%"><c:if test="${user.map.is_binding eq 1}">
                    <html-el:text property="mobile" styleId="mobile" maxlength="11" style="width:200px" value="${konkaJxcBindingMobile.mobile}"/>
                  </c:if>
                  <c:if test="${user.map.is_binding eq 0}">
                    <html-el:text property="mobile" styleId="mobile" maxlength="11" style="width:200px"/>
                  </c:if>
                  <span>请填写11位手机号码，格式如：138********</span></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><label>
                    <html-el:button property="" value="确定" styleClass="bgButtonSave" styleId="btn_submit" />
                  </label>
                  <label>
                    <html-el:button property="" value="返 回" styleClass="bgButtonBack" styleId="btn_back" onclick="history.back();" />
                  </label></td>
              </tr>
            </table>
          </html-el:form></td>
      </tr>
    </table>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var f=document.forms[0];
	$("#binding_mobile").click(function(){
		$("#mobile").attr("dataType", "Mobile").attr("msg", "请确保填写的手机号码，符合格式要求！");
		$("#div_mobile").toggle();
		$("#btn_submit").click(function(){
			if(Validator.Validate(f, 3)){
				f.submit();
			}else{
				return false;
			}
	 	});
	 });
	$("#update_mobile").click(function(){
		$("#mobile").attr("dataType", "Mobile").attr("msg", "请确保填写的手机号码，符合格式要求！");
		$("#div_mobile").toggle();
		$("#btn_submit").click(function(){
			if(Validator.Validate(f, 3)){
				f.submit();
			}else{
				return false;
			}
	 	});
	 });
	$("#delete_mobile").click(function(){
		if(!confirm("确定解除？")){
			return false;
		}else{
			location.href='KonkaJxcBindingMobile.do?method=delete&id=${konkaJxcBindingMobile.id}&keySeq=${af.map.keySeq}';
		}
	 });
});
//]]></script>
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>