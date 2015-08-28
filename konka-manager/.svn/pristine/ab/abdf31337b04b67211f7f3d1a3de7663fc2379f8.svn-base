<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单审核</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：${naviString}</div>
  <div class="rtabcont1">
    <html-el:form action="/KonkaJxcJnhmBillsAudit.do">
      <html-el:hidden property="queryString" />
      <html-el:hidden property="method" value="save"/>
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="id" styleId="id" value="${af.map.id}" />
      <html-el:hidden property="shop_id" styleId="shop_id" value="${af.map.shop_id}" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass" id="tb">
        <tr>
          <th colspan="4">订单信息</th>
        </tr>
        <tr>
          <td width="15%" class="title_item">销售单号：</td>
          <td colspan="3">${fn:escapeXml(af.map.sn)}</td>
        </tr>
        <tr>
          <td  width="15%" class="title_item" nowrap="nowrap">产品型号：</td>
          <td width="35%">${fn:escapeXml(pd_name)}</td>
          <td width="15%" class="title_item" nowrap="nowrap">产品唯一编码：</td>
          <td>${fn:escapeXml(pd_unique_code)}</td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap">购买数量（台）：</td>
          <td>${count}</td>
          <td class="title_item" nowrap="nowrap">产品单价（元）：</td>
          <td><fmt:formatNumber value="${price}" pattern="#0.00" /></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap">单台补贴金额（元）：</td>
          <td><fmt:formatNumber value="${allowance}" pattern="#0.00" /></td>
          <td class="title_item" nowrap="nowrap">总补贴金额（元）：</td>
          <td><fmt:formatNumber value="${allowance_money}" pattern="#0.00" /></td>
        </tr>
        <tr>
          <td colspan="1" class="title_item" nowrap="nowrap">附件：</td>
          <td colspan="3"><c:if test="${not empty (attachmentList)}">
              <c:forEach var="cur" items="${attachmentList}" varStatus="vs">
                <c:if test="${not empty cur.save_path and cur.source eq 2}"><img src="${ctx}/${fn:substringBefore(cur.save_path, '.')}_240.jpg" /></c:if>
              </c:forEach>
            </c:if>
            <c:if test="${empty (attachmentList)}"> <span style="color:gray;">无</span> </c:if></td>
        </tr>
        <tr>
          <th colspan="4">消费者信息</th>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap">消费者姓名：</td>
          <td align="left">${fn:escapeXml(cus_name)}
            <c:if test="${empty cus_name}"><span style="color:gray;">未填写</span></c:if></td>
          <td class="title_item" nowrap="nowrap">消费者身份证号：</td>
          <td align="left">${fn:escapeXml(cus_idcard)}
            <c:if test="${empty cus_idcard}"><span style="color:gray;">未填写</span></c:if></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap">审核状态：</td>
          <td nowrap="nowrap" colspan="3" align="left"><label>
              <html-el:radio property="state" value="1" />
              审核通过</label>
            <label>
              <html-el:radio property="state" value="0" />
              待审核 </label>
            <span id="state_msg1" /></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap">审核结果批注：</td>
          <td nowrap="nowrap" colspan="3" align="left"><html-el:textarea property="audit_remarks" styleId="audit_remarks" /></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap">审核人：</td>
          <td nowrap="nowrap" colspan="3" align="left"><html-el:text property="audit_user_name" styleId="audit_user_name" /></td>
        </tr>
        <tr>
          <td align="center" colspan="4" style="text-align:center"><html-el:button property="save" styleClass="bgButtonSave" styleId="btn_submit" value="提交"/>
            &nbsp;
            <html-el:button styleClass="bgButtonBack" property="" styleId="btn_back" value="返回 " onclick="history.back();" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
	$(document).ready(function(){
		$("#audit_user_name").attr("dataType","Require").attr("msg","请填写审核人！");
	});

	$("#btn_submit").click(function() {

		if($('input:radio[name="state"]:checked').val() == null){
			$("#state_msg").remove();
			$("#state_msg1").after('<span id="state_msg" style="color:#FF0000;">*&nbsp;请选择购买人类型。<\/span>');
			  return false ;
		} else {
			$("#state_msg").remove();
		}

		if($('input:radio[name="state"]:checked').val() == 0){
			$("#audit_remarks").attr("dataType","Require").attr("msg","请填写审核结果批注！");
		}

		if(Validator.Validate(this.form, 3)){
			return this.form.submit();
		}
		return false;
	});
//]]></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>