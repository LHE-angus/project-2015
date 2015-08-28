<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
      <html-el:form action="/ywygps/KonkaYwyMobileSet">
        <html-el:hidden property="method" value="save" />
        <html-el:hidden property="mod_id" />
        <html-el:hidden property="id" value="${af.map.id}"/>
        <html-el:hidden property="queryString" />
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        
          <tr>
            <td width="18%" class="title_item" nowrap="nowrap" align="right"><strong>公司名称：</strong></td>
            <td align="left" >           
                <c:if test="${empty af.map.id}">
                	<html-el:text property="entp_name" styleId="entp_name" maxlength="30" size="80" style="width:240px;"/>
                </c:if>
                <c:if test="${not empty af.map.id}">
                	${af.map.entp_name}
                </c:if>
            </td>
          </tr>
          <tr>
            <td width="18%" class="title_item" nowrap="nowrap" align="right"><strong>手机端URL：</strong></td>
            <td align="left" ><html-el:text property="soft_url" styleId="soft_url" maxlength="100" size="80" style="width:240px;"/></td>
          </tr>
          <tr>
            <td width="20%" align="right"><strong>手机配置URL：</strong></td>
            <td><strong><html-el:text property="config_url" styleId="config_url" maxlength="100" size="80" style="width:240px;"/></strong></td>
          </tr>
          <tr>
            <td width="20%" align="right"><strong>GPS信息URL：</strong></td>
            <td><strong><html-el:text property="gps_url" styleId="gps_url" maxlength="100" size="80" style="width:240px;"/></strong></td>
          </tr>
          <tr>
            <td colspan="2" align="center">
              <html-el:button value=" 保存 " styleId="btn_submit" property="btn_submit" styleClass="but4" />
              &nbsp;&nbsp;<html-el:button property="back" value=" 返回 " onclick="history.back();" styleId="btn_back" styleClass="but5"/></td>
          </tr>
        </table>
      </html-el:form>
    </div>

<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	$("#btn_submit").click(function(){
		
		if(Validator.Validate(this.form, 3)){
			$("#btn_submit"   ).attr("disabled",true);
			$("#btn_back"     ).attr("disabled",true);
			
			this.form.submit();
		}
	});

});

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
