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
    <html-el:form action="/admin/KonkaMobileCollectionForJB" enctype="multipart/form-data">
      <html-el:hidden property="coll_id" styleId="coll_id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="sendRecSave" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="rtable3">
		<tr>
			<td nowrap="nowrap" class="title_item" align="right" width="147">操作存品：</td>
			<td width="88%" align="left">
				<label>${af.map.map.coll_name}</label>
				<html-el:hidden property="storage_id" styleId="storage_id" />
			</td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item" align="right" width="147">操作类型：</td>
			<td width="88%" align="left">
				<c:choose>
	              <c:when test="${af.map.op_type eq 0}"><span style="color:#F00;">出库</span></c:when>
	              <c:when test="${af.map.op_type eq 1}"><span style="color:#060;">入库</span></c:when>
	              <c:when test="${af.map.op_type eq 2}"><span style="color:#060;">锁定</span></c:when>
	              <c:when test="${af.map.op_type eq 3}"><span style="color:#060;">解锁</span></c:when>
	            </c:choose>
				<html-el:hidden property="op_type" styleId="op_type" />
			</td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item" align="right" width="147">数量：</td>
			<td width="88%" align="left">
				<html-el:text property="op_num" size="40" maxlength="30" styleId="op_num" />
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<input class="but4" type="button" name="Submit4" value="提交" id="btn_submit" />
            	<input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();" />
			</td>
		</tr>
	</table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#op_num").attr("dataType", "Number").attr("msg", "请填写数量");
	
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 3)){
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>

