<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：${naviString}</div>
  <div class="rtabcont1">
    <html-el:form action="/KonkaJxcOrderAudit.do">
      <html-el:hidden property="queryString" />
      <html-el:hidden property="method" value="saveAudit"/>
      <html-el:hidden property="audit_level" value="${af.map.audit_level}" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="id" styleId="id" value="${af.map.id}" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass" id="tb">
        <tr>
          <th colspan="2">订单基本信息</th>
        </tr>
        <tr>
          <td width="15%" class="title_item">交易流水号：</td>
          <td>${fn:escapeXml(af.map.trade_index)}</td>
        </tr>
        <tr>
          <td class="title_item">买卖提网点名称：</td>
          <td>${fn:escapeXml(af.map.map.shop_name)}</td>
        </tr>
        <tr>
          <td  class="title_item">下单时间：</td>
          <td><fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd"/></td>
        </tr>
        <tr>
          <td  class="title_item">订单产品数量：</td>
          <td>${fn:escapeXml(af.map.order_num)}</td>
        </tr>
        <tr>
          <td  class="title_item">订单金额（元）：</td>
          <td><fmt:formatNumber value="${af.map.money}" pattern="0.00" /></td>
        </tr>
        <tr>
          <td  class="title_item">订单折扣金额（元）：</td>
          <td><fmt:formatNumber value="${af.map.good_discount_price}" pattern="0.00" /></td>
        </tr>
        <c:if test="${not empty af.map.konkaOrderInfoAuditList}">
          <c:set var="is_audit" value=""/>
          <c:forEach items="${af.map.konkaOrderInfoAuditList}" var="cur1">
            
              <td class="title_item">${fn:escapeXml(cur1.map.role_name)}审核信息：</td>
              <td> 审核结果：【
                <c:if test="${cur1.audit_result eq 1}">通过</c:if>
                <c:if test="${cur1.audit_result eq 0}">未审核</c:if>
                <c:if test="${cur1.audit_result eq -1}">未通过</c:if>
                】&nbsp;&nbsp;
                
                审核意见：【
                <c:if test="${empty cur1.audit_comment }">无</c:if>
                <c:if test="${not empty cur1.audit_comment }">${fn:escapeXml(cur1.audit_comment)}</c:if>
                】 &nbsp;&nbsp;
                审核人：【${fn:escapeXml(cur1.audit_user)}】&nbsp;&nbsp;
                审核时间：【
                <fmt:formatDate value="${cur1.audit_datetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                】 </td>
            <tr> </tr>
          </c:forEach>
        </c:if>
        <tr>
          <td class="title_item">订单审核状态:</td>
          <td><c:if test="${empty af.map.konkaOrderInfoAuditList}"> <span>未审核</span> </c:if>
            <c:if test="${not empty af.map.konkaOrderInfoAuditList}">
              <c:set var="is_now_max_level" value="0"/>
              <c:set var="is_max_level" value="0"/>
              <c:set var="audit_last_result" value=""/>
              <c:forEach items="${af.map.konkaOrderInfoAuditList}" var="cur1">
                <c:if test="${cur1.audit_result eq -1}">
                  <c:set var="is_now_max_level" value="1"/>
                </c:if>
                <c:if test="${cur1.audit_level eq af.map.map.max_audit_level}">
                  <c:set var="is_max_level" value="1"/>
                  <c:set var="audit_last_result" value="${cur1.audit_result}"/>
                </c:if>
              </c:forEach>
              <c:if test="${is_max_level eq 0 && is_now_max_level eq 0}"> <span style="color:#060;">审核进行中</span> </c:if>
              <c:if test="${is_max_level eq 0 && is_now_max_level eq 1}"> <span style="color:#060;">审核结束 【审核结果：未通过】</span> </c:if>
              <c:if test="${is_max_level eq 1}"> <span style="color:#060;">审核结束 
                【审核结果：
                <c:if test="${audit_last_result eq 1}">通过</c:if>
                <c:if test="${audit_last_result eq 0}">未审核</c:if>
                <c:if test="${audit_last_result eq -1}">未通过</c:if>
                】</span></c:if>
            </c:if></td>
        </tr>
        <tr>
          <td  class="title_item">说明：</td>
          <td>${fn:escapeXml(af.map.remark)}</td>
        </tr>
        <tr>
          <td colspan="2" align="center"><table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
              <tr>
                <th colspan="10">订单明细</th>
              </tr>
              <tr class="title_top">
                <td width="5%" align="center">序号</td>
                <td width="10%" align="center">产品大类</td>
                <td width="10%" align="center">产品品牌</td>
                <td width="15%" align="center">产品型号</td>
                <td width="8%" align="center">产品数量</td>
                <td width="8%" align="center">产品单位</td>
                <td width="10%" align="center">产品单价（元）</td>
                <td width="14%" align="center">产品金额（元）</td>
                <td width="10%" align="center">折扣（%）</td>
                <td width="10%" align="center">折扣金额（元）</td>
              </tr>
              <c:forEach items="${konkaOrderInfoDetailsList}" var="cur" varStatus="vs">
                <tr>
                  <td align="center">${vs.count}</td>
                  <td align="center">${fn:escapeXml(cur.pd_type_name)}</td>
                  <td align="center">${fn:escapeXml(cur.brand_name)}</td>
                  <td align="center">${fn:escapeXml(cur.pd_name)}</td>
                  <td align="center">${fn:escapeXml(cur.good_count)}</td>
                  <td align="center">${fn:escapeXml(cur.good_unit)}</td>
                  <td align="center"><fmt:formatNumber value="${cur.good_price}" pattern="0.00" /></td>
                  <td align="center"><fmt:formatNumber value="${cur.good_sum_price}" pattern="0.00" /></td>
                  <td align="center"><fmt:formatNumber value="${cur.good_discount}" pattern="0" /></td>
                  <td align="center"><fmt:formatNumber value="${cur.good_discount_price}" pattern="0.00" /></td>
                </tr>
              </c:forEach>
              <tr>
                <td align="center" colspan="10" style="text-align:center"><html-el:button styleClass="bgButtonBack" property="" styleId="btn_back" value="返回 " onclick="history.back();" /></td>
              </tr>
            </table></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>