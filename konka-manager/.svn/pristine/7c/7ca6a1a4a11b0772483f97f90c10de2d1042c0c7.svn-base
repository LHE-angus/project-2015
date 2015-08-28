<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
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
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
<div class="rtabcont1">
<html-el:form action="/zmd/KonkaXxZmdKcSearch">
	<html-el:hidden property="method" styleId="method" value="list" />
	<html-el:hidden property="mod_id" styleId="mod_id" />
	<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
	      <td><strong class="fb">产品型号：</strong><html-el:select property="md_name" styleId="md_name">
	      	<html-el:option value="">-请选择-</html-el:option>
	      	<c:forEach items="${konkaXxPdList}" var="cur">
	      	   <html-el:option value="${cur.md_name}">-${cur.md_name}-</html-el:option>
	      	</c:forEach>
	       </html-el:select></td>       
		  <td><input class="but1" type="button" name="Submit" value="搜索" /></td>
		</tr>
	</table>
</html-el:form>
<div id="msgShow" style="display: none;text-align: center;color: #f00;font-weight: 800;height: 100px;font-size: large;padding-top: 50px;">
</div>
</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#md_name").attr("dataType" , "Require").attr("msg" , "请填写产品型号！");
	$(".but1").click(function(){
		var isSubmit = Validator.Validate(this.form, 3);
		if(isSubmit){
			this.form.submit();
		}
	});
	if(${not empty af.map.md_name}){
		if(${not empty zmd_id_is_null}){
			$("#msgShow").html("该专卖店信息不存在！").show();
		}else {
			$("#msgShow").html("${af.map.md_name}的库存量为：${ret}").show();
		}
	}
});
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>