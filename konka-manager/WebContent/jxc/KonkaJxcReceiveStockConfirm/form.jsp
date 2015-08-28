<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>收货确认</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：进货管理 &gt; 收货确认</div>
  <div class="rtabcont1">
    <html-el:form action="/KonkaJxcReceiveStockConfirm">
    <html-el:hidden property="queryString" />
    <html-el:hidden property="method" value="saveConfirm" />
    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
    <html-el:hidden property="id" styleId="id" value="${af.map.id}" />
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass" id="tb">
      <tr>
        <th colspan="2">发货登记基本信息</th>
      </tr>
      <tr>
        <td width="15%" class="title_item">发货单号</td>
        <td width="85%">${fn:escapeXml(af.map.sn)}</td>
      </tr>
      <tr>
        <td width="15%" class="title_item">发货数量</td>
        <td width="85%">${fn:escapeXml(af.map.fh_sum_count)}</td>
      </tr>
      <tr>
        <td width="15%" class="title_item">应收金额</td>
        <td width="85%"><fmt:formatNumber value="${af.map.money_must}" pattern="0.00" /></td>
      </tr>
      <tr>
        <td width="15%" class="title_item">实收金额</td>
        <td width="85%"><fmt:formatNumber value="${af.map.money_result}" pattern="0.00" /></td>
      </tr>
      <tr>
        <td width="15%" class="title_item">发货日期</td>
        <td width="85%"><fmt:formatDate value="${af.map.fh_date}" pattern="yyyy-MM-dd"/></td>
      </tr>
      <tr>
        <td width="15%" class="title_item">发货人</td>
        <td width="85%">${fn:escapeXml(af.map.add_user_name)}</td>
      </tr>
      <tr>
        <td width="15%" class="title_item">供应商</td>
        <td width="85%">${fn:escapeXml(af.map.add_dept_name)}</td>
      </tr>
      <c:if test="${not empty is_fenjingban}">
        <tr>
          <td width="15%" class="title_item">是否确认收获</td>
          <td width="85%"><c:choose>
              <c:when test="${af.map.is_confirm eq 0}"> <span style="color:#F00;">未确认</span></c:when>
              <c:when test="${af.map.is_confirm eq 1}"> <span style="color:#060;">已确认</span></c:when>
            </c:choose></td>
        </tr>
      </c:if>
      <tr>
        <td colspan="2" align="center"><table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
            <tr>
              <th colspan="7">发货登记明细</th>
            </tr>
            <tr class="title_top">
              <td width="5%" align="center">序号</td>
              <td width="20%" align="center">产品大类</td>
              <td width="20%" align="center">产品品牌</td>
              <td align="center">产品型号</td>
              <td width="10%" align="center">发货数量</td>
              <td width="10%" align="center">单价</td>
              <td width="15%" align="center"><span style="color:red">*</span>收货仓库</td>
            </tr>
            <c:forEach items="${konkaJxcFhBillDetailsList}" var="cur" varStatus="vs">
              <tr>
                <td align="center">${vs.count}</td>
                <td align="center">${fn:escapeXml(cur.pd_type_name)}</td>
                <td align="center">${fn:escapeXml(cur.brand_name)}</td>
                <td align="center">${fn:escapeXml(cur.pd_name)}</td>
                <td align="center">${fn:escapeXml(cur.count)}</td>
                <td align="center"><html-el:hidden property="fh_bill_detail_id" styleId="fh_bill_detail_id" value="${cur.id}" />
                	<html-el:hidden property="fh_bill_detail_count" styleId="fh_bill_detail_count" value="${cur.count}" />
                	<html-el:hidden property="price" styleId="price" value="${cur.price}" />
                	<fmt:formatNumber value="${cur.price}" pattern="0.00" /></td>
                <td align="center"><html-el:select property="store_id" styleId="store_id" style="width:130px;">
		        	<option value="">请选择...</option>
		        	<c:forEach items="${storeList}" var="cur">
		                <option value="${cur.id}">${cur.store_name}</option>
		        	</c:forEach>
		            </html-el:select></td>
              </tr>
            </c:forEach>
            <tr>
              <td align="center" colspan="8" style="text-align:center">
              	<html-el:button property="save" styleClass="bgButtonSave" styleId="btn_submit" value="保 存"/>
              	&nbsp;<input type="button" class="bgButtonBack" value="返回 " onclick="history.back();" />
              </td>
            </tr>
          </table></td>
      </tr>
    </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	var f = document.forms[0];
	$("#btn_submit").click(function(){
		var isStoteBlank = false;
		$("#store_id","#tb").each(function(){
			var this_value = $(this).val();
			if(this_value == ""){
				alert("每个产品的收货仓库是必选的，请选择收货仓库！");
				isStoteBlank = true;
		        return false;
			}
		});
		if(isStoteBlank){
			return false;
		}

		var isSubmit = Validator.Validate(f, 3);
		if (isSubmit){
			$(":submit").attr("value", "正在提交...").attr("disabled", "true");
			$(":button").attr("disabled", "true");
			f.submit();
		}
	});
	
});
//]]></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>