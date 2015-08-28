<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>库存管理 &gt; 盘存管理</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
-->
</style>
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;"/> 当前位置：${naviString}</div>
  <div class="rtabcont1">
    <html-el:form action="/KonkaJxcThAudit">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="id" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th colspan="2">退货审核</th>
        </tr>
        <tr>
          <td class="title_item">类型：</td>
          <td>${af.map.pd_type_name}</td>
        </tr>
        <tr>
          <td class="title_item">型号：</td>
          <td>${af.map.pd_name}</td>
        </tr>
        <tr>
          <td class="title_item">退货数量：</td>
          <td>${af.map.th_count}</td>
        </tr>
        <tr>
          <td class="title_item">产品单价：</td>
          <td>${fn:escapeXml(af.map.price)}</td>
        </tr>
        <tr>
          <td class="title_item">退货日期：</td>
          <td><fmt:formatDate value="${af.map.in_date}" pattern="yyyy-MM-dd" /></td>
        </tr>
        <tr>
          <td class="title_item">退货原因：</td>
          <td>${af.map.th_reason}</td>
        </tr>
        <tr>
          <td class="title_item">退货产品类型：</td>
          <td><c:if test="${af.map.th_type eq 0}">残次品</c:if>
            <c:if test="${af.map.th_type eq 1}">正品</c:if></td>
        </tr>
        <c:if test="${empty af.map.shop_id}">
          <tr>
            <td class="title_item">申请人类型：</td>
            <td>${af.map.role_name}</td>
          </tr>
          <tr>
            <td class="title_item">申请人：</td>
            <td>${af.map.user_name}</td>
          </tr>
          <tr>
            <td class="title_item">申请人部门：</td>
            <td>${af.map.dept_name}</td>
          </tr>
        </c:if>
        <tr>
          <td class="title_item">申请时间：</td>
          <td><fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd" /></td>
        </tr>
        <tr>
          <td width="18%" class="title_item">是否同意：</td>
          <td><html-el:select property="is_audit" styleId="is_audit">
              <html-el:option value="1">同意</html-el:option>
              <html-el:option value="-1">不同意</html-el:option>
            </html-el:select></td>
        </tr>
        <tr id="divTr">
          <td class="title_item"><font color="red">*</font>接收仓库：</td>
          <td><html-el:select property="th_store_id_par" styleId="th_store_id_par">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${storeList}">
                <html-el:option value="${cur.id}">${fn:escapeXml(cur.store_name)}</html-el:option>
              </c:forEach>
            </html-el:select></td>
        </tr>
        <tr>
          <td class="title_item"><font color="red">*</font>审批意见：</td>
          <td><html-el:text property="audit_reason" styleId="audit_reason" styleClass="webinput" maxlength="100" style="width:60%" /></td>
        </tr>
        <tr>
          <td colspan="2" align="center"><html-el:button property="save" styleClass="bgButtonSave" styleId="th_audit_submit" value="保 存"/>
            <input class="bgButtonBack" type="button" name="th_audit_back" id="th_audit_back" value="返 回" onclick="history.back();return false;" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">//<![CDATA[
var f = document.forms[0];                                          

$(document).ready(function() {
	$("#audit_reason").attr("dataType", "Require").attr("msg", "请填写审批说明！");
	
	$("#is_audit").bind("change",function(){
		if ($(this).val() == 1) {
			$("#divTr").attr("style","display:");
			$("#th_store_id_par").attr("dataType", "Require").attr("msg", "请选择接收仓库！");
		} else {
			$("#divTr").attr("style","display:none");
			$("#th_store_id_par").removeAttr("dataType").removeAttr("msg");
		}
	});
	$("#is_audit").change();

});    

$("#th_audit_submit").click(function(){
	if(Validator.Validate(this.form, 3)){
		this.form.submit();
	}
});

//]]></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
