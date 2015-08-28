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
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/paragon/KonkaParagonShowimg" enctype="multipart/form-data">
      <html-el:hidden property="img_id" styleId="img_id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="show_shop_code"  />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td nowrap="nowrap" class="title_item" align="right" width="147">门店代码：</td>
          <td>${af.map.show_shop_code}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right" width="147">形象图片：<span style="color:red">*</span></td>
          <td valign="bottom">
          		<c:if test="${not empty af.map.img_id}">
	          		<img src="${ctx}/${af.map.img_path}" alt="" /><br />
	          		<html-el:checkbox property="is_cover" styleId="is_cover" onclick="isCover();" value="1"><label for="is_cover">重新上传</label></html-el:checkbox>
	          		<div id="cover_div" style="display: none;">
						<html-el:file property="file0" size="30" styleId="file0"/>
					</div>
          		</c:if>
          		<c:if test="${empty af.map.img_id}">
          			<html-el:file property="file0" size="30" styleId="file0"/>
          		</c:if>
          </td>
        </tr>
          
        <tr>
          <td>&nbsp;</td>
          <td><label>
		            <input class="but4" type="button" name="btn_submit" id="btn_submit" value="提交" />
		            <input class="but5" type="button" name=btn_back value="返回" onclick="history.back();return false;" />
		          </label></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont3"></div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript">//<![CDATA[

$(document).ready(function(){
	$("#equipment_name").attr("dataType", "Require").attr("msg", "请填写");
	<c:if test="${empty af.map.img_id}">
		$("#file0"  ).attr("Require", "true").attr("dataType", "Filter" ).attr("msg", "图片的格式必须是(bmp,gif,jpeg,jpg)").attr("accept", "bmp, gif, jpeg, jpg");
	</c:if>
	// 提交
	$("#btn_submit").click(function(){
		   if(Validator.Validate(this.form, 3)){
				$("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
				$("#btn_back").attr("disabled", "true");
				this.form.submit();
		   }
	});

});
function isCover(){
	var isCover = $('#is_cover').attr('checked') ;
	if(isCover){
		$('#cover_div').css("display","block");
		$('#file0').attr("Require", "false").attr("dataType", "Filter" ).attr("msg", "图片的格式必须是(bmp,gif,jpeg,jpg)").attr("accept", "bmp, gif, jpeg, jpg");;
	}else{
		var file = $('#cover_div').clone();
		$('#cover_div').empty().append(file);
		$('#cover_div').css("display","none");
	}
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
