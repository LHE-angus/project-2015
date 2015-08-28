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
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/zmd/KonkaXxStdPd">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="md_name" value="${af.map.md_name}" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable4">
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right"><span style="color:red">[必填]</span>产品型号名称：</td>
          <td><c:if test="${empty is_edit}">
              <html-el:text property="md_name" styleId="md_name" maxlength="40" size="50" />
            </c:if>
            <c:if test="${!empty is_edit}">${af.map.md_name}</c:if>
            &nbsp;</td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right"><span style="color:red">[必填]</span>产品大类：</td>
          <td><select name="pd_id" id="pd_id">
              <option value="">--请选择--</option>
              <c:forEach items="${basePdClazzList}" var="cur"> <option value="${cur.cls_id}" 
                <c:if test="${cur.cls_id eq af.map.pd_id}">selected="selected"</c:if>
                >${fn:escapeXml(cur.tree_name)}
                </option>
              </c:forEach>
            </select></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right"><span style="color:red">[必填]</span>产品规格：</td>
          <td><html-el:text property="spec" styleId="spec" maxlength="40" size="50" />
            &nbsp;</td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right"><span style="color:red">[必填]</span>产品种类：</td>
          <td><label for="md_type_0">
              <html-el:radio styleId="md_type_0" property="md_type" value="0" />
              主销</label>
            <label for="md_type_1">
              <html-el:radio styleId="md_type_1" property="md_type" value="1" />
              停产（清理）</label>
            <label for="md_type_2">
              <html-el:radio styleId="md_type_2" property="md_type" value="2" />
              退市</label></td>
        </tr>
        <tr>
          <td>&nbsp;&nbsp;</td>
          <td><input class="but4" type="button" value="保存" id="send" />
            <input class="but5" type="button" value="返回" onclick="history.back()" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<!-- ****** Main Frame End ****** -->
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#md_name").attr("dataType", "Require").attr("msg", "产品型号名称必须填写！");
	$("#pd_id").attr("dataType", "Require").attr("msg", "请选择产品大类！");
	$("#spec").attr("dataType", "Require").attr("msg", "产品规格必须填写！");
	$("#md_type_2").attr("dataType", "Group").attr("msg", "产品种类必须选择一项！");
	
	$("#send").click(function(){
		var isSubmit = Validator.Validate(this.form, 3);
		if (isSubmit) {
			$(":button").attr("disabled", "true");
			$(":reset").attr("disabled", "true");
			this.form.submit();
		}
	 });
});
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
