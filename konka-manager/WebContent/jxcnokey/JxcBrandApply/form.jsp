<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<title>填写/修改品牌</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：库存管理 &gt; 品牌申请</div>
<div class="rtabcont1">
  <html-el:form action="/JxcBrandApply" enctype="multipart/form-data">
    <html-el:hidden property="method" value="save"/>
    <html-el:hidden property="mod_id" value=""/>
    <html-el:hidden property="queryString" />
    <html-el:hidden property="keySeq" />
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
      <tr>
        <th colspan="2" height="56">品牌申请 </th>
      </tr>
      <tr>
        <td width="12%" height="30" align="right" bgcolor="#fff2dc" style="padding-left:10px;" class="title_item"><span><font style="color: red">*</font></span>品牌名称：</td>
        <td width="88%" height="30" align="left" style="padding-left:10px;" ><input name="brand_name" type="text"  id="brand_name" value="${af.map.brand_name}" class="webinput"/>
          <input name="id" type="hidden"  id="id" value="${af.map.id}" class="webinput" />
          <span id="tip" style="color:#FF0000;display: none;"><img src="${ctx}/commons/styles/themes/blue/images/reg_error.gif" />&nbsp;对不起，该品牌名称已存在！</span></td>
      </tr>
      <tr>
        <td width="15%" height="30" align="right" bgcolor="#fff2dc" style="padding-left:10px;" class="title_item">品牌LOGO：</td>
        <td width="85%" height="30" align="left" style="padding-left:10px;"><c:if test="${not empty (af.map.brand_logo)}" var="hasImage"> <img src="${ctx}/${fn:substringBefore(af.map.brand_logo, '.')}_120.jpg"  /> <br />
            <label for="chkReUploadImage">
              <input class="webinput"  type="checkbox" name="chkReUploadImage" id="chkReUploadImage" value="1" onclick="$('#brand_logo').toggle();" />
              重新上传主图</label>
            <label for="del_brand_logo">
              <input class="webinput"  type="checkbox" name="del_brand_logo" id="del_brand_logo" value="1" />
              删除该主图</label>
            <br />
            <html-el:file styleClass="webinput" property="brand_logo" style="display:none;width:500px;" styleId="brand_logo" />
          </c:if>
          <c:if test="${empty (af.map.brand_logo)}">
            <html-el:file styleClass="webinput" property="brand_logo" style="width:500px;" styleId="brand_logo" />
          </c:if></td>
      </tr>
      <tr>
        <td height="30" colspan="2" align="center"><input type="button" class="bgButtonSave" value="保存"  id="but_sub" />
          <input type="button" class="bgButtonBack" value="返回" onclick="location.href='JxcBrandApply.do?keySeq=${af.map.keySeq}'"/></td>
      </tr>
    </table>
  </html-el:form>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/cs.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	$("#brand_name").attr("dataType","Require").attr("msg","请填写品牌名称！");
	$("#but_sub").click(function() {
if(Validator.Validate(this.form,1)){
			this.form.submit();
}else{
	return false;
}

		
});


	$("#brand_name").blur(function(){
		if("${af.map.edit}"==1){
		$.ajax({
			type: "POST",
			url: "JxcBrandApply.do",
			data: "method=validateName&brand_name=" + $("#brand_name").val()+"&keySeq=${af.map.keySeq}&id=${af.map.id}",
			dataType: "json",
			error: function(request, settings) {alert("数据加载请求失败！"); },
			success: function(isExist) {
				if(isExist == 1) {
					$("#tip").show(); 
					$("#but_sub").attr("disabled", "true");
					return false;
				} else if(isExist == 0){
					$("#tip").hide();
					$("#but_sub").attr("disabled", "");
				} 
			}
	});
		}
	});
	
});

//]]></script>
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
