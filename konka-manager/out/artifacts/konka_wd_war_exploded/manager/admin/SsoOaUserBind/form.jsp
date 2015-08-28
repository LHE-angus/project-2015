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
<c:if test="${!empty is_add}">
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
</c:if>
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
    <html-el:form action="/admin/SsoOaUserBind" enctype="multipart/form-data">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="id" styleId="id" /> 
      <html-el:hidden property="queryString" />    
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td class="title_item" align="right" width="100"><font color="red">*</font>OA用户名：</td>
          <td ><html-el:text property="samaccount_name" styleId="samaccount_name"  maxlength="40" style="width:200px;" />   </td>
        </tr>  
        <tr> 
          <td class="title_item" align="right"><font color="red">*</font>绑定用户名：</td>
           <td>
           		<html-el:text property="user_name" styleId="user_name"  maxlength="40" style="width:200px;" /> 
           </td>
        </tr> 
        <tr> 
          <td class="title_item" align="right"><font color="red">*</font>绑定用户ID：</td>
           <td>
           		<html-el:text property="user_id" styleId="user_id"  maxlength="16" style="width:200px;" /> 非新增用户无需修改
           </td>
        </tr> 
        <tr> 
          <td class="title_item" align="right">状态：</td>
           <td>
           	 <html-el:select property="state" styleId="state"> 
            	<html-el:option value="0">正常</html-el:option>
            	<html-el:option value="1">注销</html-el:option>
            </html-el:select>
           </td>
        </tr> 
        <tr>
          <td>&nbsp;</td>
          <td align="center" ><label>
            <input class="but4" type="button"  id="btn_submit" value="提交" />
            <input class="but5" type="button"  id="btn_back" value="返回" onclick="history.back();return false;" /> </label>
            用户名信息修改后，OA绑定用户须在此修改用户名，新增用户须修改绑定用户ID
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/pinyin.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#samaccount_name").attr("dataType", "Require").attr("msg", "OA账号！"); 
	$("#user_name").attr("dataType", "Require").attr("msg", "绑定用户名不能为空！"); 
	$("#user_id").attr("dataType", "Require").attr("msg", "绑定用户ID不能为空！"); 
	 
 
	// 提交
	$("#btn_submit").click(function(){ 
		if(Validator.Validate(this.form, 2)){
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});

});

function resizeFrameHeight(offset, min_height) {
	// frame id can be found in page /konka-wd/WebContent/manager/admin/Frames2/indexFrame.jsp, and search 'iframe' to get.
	$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
}
 
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
