<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<c:set var="naviString" value="模块授权" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="200" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/SetModPopedom.do">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="role_id" />
      <html-el:hidden property="user_id" />
      <html-el:hidden property="url" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <th width="5%">层级</th>
          <th width="35%">栏目授权</th>
          <th width="50%">权限设置</th>
          <th width="10%">&nbsp;</th>
        </tr>
        <c:forEach var="cur" items="${sysModuleList}">
          <c:set var="isChecked" value="${false}" />
          <tbody>
            <c:if var="urlIsNull" test="${cur.mod_url eq null}">
              <tr>
                <td style="text-align:center;">${cur.map.level}</td>
                <td colspan="3" style="text-align:left;"><c:forEach begin="1" end="${cur.map.level}">&nbsp;</c:forEach>
                  <strong>${cur.mod_name}</strong></td>
              </tr>
            </c:if>
            <c:if test="${not urlIsNull}">
              <tr>
                <td style="text-align:center;">${cur.map.level}</td>
                <td style="text-align:left;"><c:forEach begin="0" end="${cur.map.level}">&nbsp;&nbsp;</c:forEach>
                  ${cur.mod_name}</td>
                <td style="text-align:left;"><c:forEach var="basePopedom" items="${cur.basePopedomList}">
                    <logic-el:present name="mod_popedom_${cur.mod_id}">
                      <c:set var="isChecked" value="${false}" />
                      <logic-el:iterate id="selectedSxModPopedom" name="mod_popedom_${cur.mod_id}">
                        <c:if var="isChecked" test="${(selectedSxModPopedom eq basePopedom.ppdm_code) or isChecked}" />
                      </logic-el:iterate>
                    </logic-el:present>
                    <c:if test="${isChecked}">
                      <input type="checkbox" id="checkbox_${cur.mod_id}_${basePopedom.ppdm_code}" name="checkbox_${cur.mod_id}" value="${basePopedom.ppdm_code}" onclick="checkFirst('checkbox_${cur.mod_id}', this);" checked="checked" disabled="disabled" />
                    </c:if>
                    <c:if test="${not isChecked}">
                      <input type="checkbox" id="checkbox_${cur.mod_id}_${basePopedom.ppdm_code}" name="checkbox_${cur.mod_id}" value="${basePopedom.ppdm_code}" onclick="checkFirst('checkbox_${cur.mod_id}', this);" disabled="disabled" />
                    </c:if>
                    <label for="checkbox_${cur.mod_id}_${basePopedom.ppdm_code}">
                      <c:out value="${basePopedom.ppdm_desc}" />
                    </label>
                  </c:forEach></td>
                <td style="text-align:center;">&nbsp;</td>
              </tr>
            </c:if>
          </tbody>
        </c:forEach>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><label><input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();" /></label></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript">//<![CDATA[
function checkAll(e) {
	for (var i = 01; i < e.form.elements.length; i++) {
		if (e.form.elements[i].type == "checkbox") {
			e.form.elements[i].checked = e.checked;
		}
	}
}

function checkRow(name, cb) {
	var e = document.getElementsByName(name);
	for(var i = 0; i < e.length; i++) {
		e[i].checked = cb.checked;
	}
}

function checkFirst(name, cb) {
	return;
//	var e = document.getElementsByName(name);
//	for(var i = 0; i < e.length; i++) {
//		if (e[i].checked == true) {
//			e[0].checked = true;
//			break;
//		}
//	}
}

$("#btn_submit").click(function(){
     $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
     this.form.submit();
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
