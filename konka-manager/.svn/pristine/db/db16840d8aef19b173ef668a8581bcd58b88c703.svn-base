<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
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
    <html-el:form action="/admin/KonkaSysModuleManager.do">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="url" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <th width="10%" style="text-align: center">层级</th>
          <th width="35%">栏目</th>
          <th>分公司授权标识<span style="color: red;">（打钩-此功能授权的权限可以开发给分公司）</span></th>
        </tr>
        <c:forEach var="cur" items="${entityList}">
          <tbody>
            <c:if var="urlIsNull" test="${(cur.mod_url eq null) or(cur.tree_level eq 1)}">
              <tr class="cls_${cur.mod_id}">
                <td style="text-align:center;">${cur.tree_level}</td>
                <td style="text-align:left;"><c:forEach begin="1" end="${cur.tree_level}">&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach>
                  <strong>${cur.mod_name}</strong></td>
                <td>&nbsp;</td> 
              </tr>
              </c:if>
            <c:if test="${not urlIsNull}">
              <tr class="cls_${cur.mod_id}">
                <td style="text-align:center;">${cur.tree_level}</td>
                <td style="text-align:left;"><c:forEach begin="1" end="${cur.tree_level}">&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach>
                  ${cur.mod_name}</td>
                <td><input type="checkbox" id="checkbox_${cur.mod_id}" name="checkbox_${cur.mod_id}" <c:if test="${cur.is_view eq 1}">checked="checked"</c:if>/></td>
              </tr>
            </c:if>
          </tbody>
        </c:forEach>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td><label>
           <input class="but4" type="submit" name="Submit4" id="btn_submit" value="提交" />&nbsp;
              <input class="but3" type="button" name="Submit5" value="重置" onclick="this.form.reset();return false;" />&nbsp;
          <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();" /></label></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript">//<![CDATA[
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
