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
    <html-el:form action="/zmd/KonkaXxStdStore">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable4">
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right">工厂：</td>
          <td><html-el:text property="factory_id" styleId="factory_id" size="25" maxlength="30"></html-el:text></td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right">库位：</td>
          <td><html-el:text property="store_id" styleId="store_id" size="25" maxlength="30"></html-el:text></td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right">仓储地点描述：</td>
          <td><html-el:textarea property="store_desc" styleId="store_desc" ></html-el:textarea></td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right">分公司：</td>
          <c:if test="${!empty af.map.dept_name}">
            <td width="85%"><html-el:select property="dept_name" styleId="dept_name" style="width:200px;" disabled="true">
                <html-el:option value="${af.map.dept_name}">${fn:escapeXml(af.map.dept_name)}</html-el:option>
              </html-el:select></td>
          </c:if>
          <c:if test="${empty af.map.dept_name}">
            <td width="85%"><html-el:select property="dept_name" styleId="dept_name" style="width:200px;">
                <html-el:option value="">==请选择==</html-el:option>
                <c:forEach var="cur" items="${konkaDeptList}">
                  <html-el:option value="${cur.dept_name}">${fn:escapeXml(cur.dept_name)}</html-el:option>
                </c:forEach>
              </html-el:select></td>
          </c:if>
        </tr>
        <tr>
          <td>&nbsp;&nbsp;</td>
          <td><input class="but4" type="button" name="Submit4" value="保存" id="send" />
            <input class="but3" type="reset"  value="重填 " />
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back()" /></td>
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
	$("#factory_id").attr("dataType", "Require").attr("msg", "请填写工厂！");
	$("#store_id").attr("dataType", "Require").attr("msg", "请填写库位！");
	$("#dept_name").attr("dataType", "Require").attr("msg", "请选择分公司！");
	$("#store_desc").attr("dataType", "Limit").attr("max", "30").attr("msg", "备注不能超过30个文字");
	
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
