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
      <html-el:hidden property="method" value="saveProperty" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="md_name" value="${af.map.md_name}" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable4">
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">产品类型</td>
          <td width="35%">${fn:escapeXml(full_cls_name)}</td>
          <td width="10%" nowrap="nowrap" class="title_item" align="right">型号名称</td>
          <td width="15%">${fn:escapeXml(af.map.md_name)}</td>
        </tr>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">产品属性赋值</td>
          <td colspan="5" style="padding: 0"><table width="100%" border="0" cellpadding="0" cellspacing="1" class="datagrid">
              <tr>
                <td width="20%" nowrap="nowrap" align="center">属性名称</td>
                <td width="80%" nowrap="nowrap" align="left">属性值</td>
              </tr>
              <c:set var="category_name" value="" />
              <c:forEach var="cur" items="${bpList}">
                <html-el:hidden property="prop_ids" value="${cur.prop_id}" />
                <c:set var="p_unit" value="${cur.prop_unit}" />
                <c:if test="${category_name ne cur.map.category_name}">
                  <tr>
                    <td colspan="2" align="right" style="font-weight:bold;padding-right:10px;">${fn:escapeXml(cur.map.category_name eq 'default' ? '其他属性' : cur.map.category_name)}</td>
                  </tr>
                </c:if>
                <c:set var="category_name" value="${cur.map.category_name}" />
                <tr>
                  <td align="right"><c:if test="${cur.is_required eq 1}"> <span style="color:red;">[必填]</span></c:if>
                    ${fn:escapeXml(cur.prop_name)}：</td>
                  <td align="left"><c:if test="${cur.prop_type eq 0}">
                      <c:if test="${cur.prop_val_type eq 2}">
                        <html-el:text property="prop_values" styleId="prop_values_${cur.prop_id}" value="${cur.map.prop_value}"  size="10" maxlength="20" readonly="true" onclick="new Calendar(2000, 2030, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
                      </c:if>
                      <c:if test="${cur.prop_val_type ne 2}">
                        <html-el:text property="prop_values" styleId="prop_values_${cur.prop_id}" value="${cur.map.prop_value}" style="width: 20%" styleClass="P_V" />
                        ${fn:escapeXml(p_unit)} </c:if>
                    </c:if>
                    <c:if test="${cur.prop_type eq 1}">
                      <html-el:select property="prop_values" styleId="prop_values_${cur.prop_id}" value="${cur.map.prop_value}" style="width: 20%" styleClass="P_V">
                        <html-el:option value="">请选择...</html-el:option>
                        <c:forEach var="ppv" items="${cur.basePropValItemList}">
                          <html-el:option value="${ppv.prop_item_name}">${fn:escapeXml(ppv.prop_item_name)}${fn:escapeXml(p_unit)}</html-el:option>
                        </c:forEach>
                      </html-el:select>
                    </c:if>
                    <c:if test="${cur.prop_type eq 2}">
                      <html-el:hidden property="prop_values" value="" />
                      <c:forEach items="${cur.basePropValItemList}" var="ppv" varStatus="vs">
                        <c:set var="checked" value="false" />
                        <c:forEach items="${fn:split(cur.map.prop_value,',')}" var="temp">
                          <c:if test="${temp eq ppv.prop_item_name}">
                            <c:set var="checked" value="true" />
                          </c:if>
                        </c:forEach>
                        <c:if test="${checked}">
                          <input type="checkbox" id="checkbox_${cur.prop_id}" name="check_box_${cur.prop_id}" value="${ppv.prop_item_id}-${ppv.prop_item_name}" checked="checked" />
                        </c:if>
                        <c:if test="${not checked}">
                          <input type="checkbox" id="checkbox_${cur.prop_id}" name="check_box_${cur.prop_id}" value="${ppv.prop_item_id}-${ppv.prop_item_name}" />
                        </c:if>
                        <c:out value="${fn:escapeXml(ppv.prop_item_name)}" />
                        ${fn:escapeXml(p_unit)}
                        &nbsp;
                        <c:if test="${vs.count mod 5 eq 0}"><br />
                        </c:if>
                      </c:forEach>
                    </c:if></td>
                </tr>
              </c:forEach>
            </table></td>
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
	<c:forEach items="${bpList}" var="cur">
	<c:if test="${cur.is_required eq 1}">
		<c:if test="${cur.prop_type eq 0 || cur.prop_type eq 1}">
			$("#prop_values_${cur.prop_id}").attr("datatype","Require").attr("msg","请填写${cur.prop_name}的属性值");
		</c:if>
		<c:if test="${cur.prop_type eq 2}">
			$("#checkbox_${cur.prop_id}").attr("datatype","Group").attr("min","1").attr("msg","请至少选择一个${cur.prop_name}的属性值");
		</c:if>
	</c:if>
		</c:forEach>
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
