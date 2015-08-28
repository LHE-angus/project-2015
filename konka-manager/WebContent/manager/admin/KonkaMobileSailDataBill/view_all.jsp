<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
</head>
<body>
    <table width="100%" border="1" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
      <thead>
        <tr>
          <td width="5%" align="center" nowrap="nowrap">序号</td>
        <td align="center" nowrap="nowrap">日期</td>
        <td width="4%" align="center" nowrap="nowrap">分公司</td>
        <td align="center" nowrap="nowrap">客户名称</td>
        <td align="center" nowrap="nowrap">客户R3编码</td>
        <td width="6%" align="center" nowrap="nowrap">客户类型</td>
        <td width="6%" align="center" nowrap="nowrap">细分类型</td>
        <td width="4%" nowrap="nowrap" align="center">上报人</td>
        <td align="center" nowrap="nowrap">上报门店</td>
        <td width="4%" align="center" nowrap="nowrap">门店所属经办</td>
        
        
        
        <td width="4%" align="center" nowrap="nowrap">送达方</td>
        <td width="4%" align="center" nowrap="nowrap">出货仓</td>
        <td width="3%" nowrap="nowrap" align="center">尺寸</td>
        <td width="8%" nowrap="nowrap" align="center">产品型号</td>
        <td width="3%" nowrap="nowrap" align="center">数量</td>
        <td width="6%" nowrap="nowrap" align="center">单价</td>
        <td width="6%" nowrap="nowrap" align="center">金额</td>
        <td width="6%" nowrap="nowrap" align="center">参考单价</td>
        <td width="6%" nowrap="nowrap" align="center">参考金额</td>
        
        <td width="5%" nowrap="nowrap" align="center">发票单号</td>
        <td width="5%" nowrap="nowrap" align="center">申请提成金额</td>
        <td width="5%" nowrap="nowrap" align="center">初审提成金额</td>
        <td width="5%" nowrap="nowrap" align="center">终审金额</td>
        
        <td width="6%" nowrap="nowrap" align="center">消费者姓名</td>
        <td width="6%" nowrap="nowrap" align="center">电话</td>
        <td nowrap="nowrap" align="center">身份证</td>
        <td width="12%" nowrap="nowrap" align="center">地址</td>
        <td width="12%" nowrap="nowrap" align="center">备注</td>
        <td width="5%" nowrap="nowrap" align="center">数据来源</td>
        </tr>
      </thead>
        <c:forEach var="cur" items="${allList}" varStatus="vs">
          <tr>
            <td>${vs.count}</td>
            <td><fmt:formatDate value="${cur.report_date}" pattern="yyyy-MM-dd" /></td>
            <td>${cur.subcomp_name}</td>
            <td align="left" nowrap="nowrap">${cur.customer_name}</td>
          	<td align="left" nowrap="nowrap">${cur.customer_r3_code}</td>
          	<td align="left" nowrap="nowrap">${cur.map.c_comm}</td>
          	<td align="left" nowrap="nowrap">${cur.map.c_name}</td>
          	<td align="left" nowrap="nowrap">${cur.report_name}</td>
          	<td align="left" nowrap="nowrap">${cur.dept_name}</td>
         	<td align="left" nowrap="nowrap">${cur.office_name}</td>
          
          	<td align="left" nowrap="nowrap">${cur.map.r3_sdf_sn}</td>
          	<td align="left" nowrap="nowrap">${cur.map.ck_name}</td>
          	<td align="center" nowrap="nowrap">${cur.measure_name}</td>
          	<td align="left" nowrap="nowrap">${cur.model_name}</td>
          	<td align="right" nowrap="nowrap">${cur.num}</td> 
          	<td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.num ne 0 ? (cur.all_price / cur.num) : 0 }" type="currency" />
            </span></td>
          	<td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.all_price}" type="currency" />
            </span></td>
          	<td align="right" nowrap="nowrap"><span class="kz-price-12">
            <c:if test="${empty cur.map.price_ref}">-</c:if>
            <c:if test="${not empty cur.map.price_ref}">
              <fmt:formatNumber value="${cur.map.price_ref}" type="currency" />
            </c:if>
            </span></td>
          	<td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.num ne 0 ? cur.map.price_ref * cur.num : 0}" type="currency" />
            </span></td>
            
            <td align="left" nowrap="nowrap">${cur.map.bill_nos}</td>
             <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.map.dec_money}" type="currency" />
            </span></td>
             <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.map.audit_money}" type="currency" />
            </span></td> <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.map.final_audit_money}" type="currency" />
            </span></td>
          	<td align="left" nowrap="nowrap">${cur.realname}</td>
          	<td align="left" nowrap="nowrap">${cur.phonenum}</td>
          	<td align="left" nowrap="nowrap">${cur.mastercode}</td>
          	<td align="left" nowrap="nowrap">${cur.addresss}</td>
          	<td align="left" nowrap="nowrap">${cur.memo}</td>
          	<td align="center" nowrap="nowrap">${fn:split('手机端,WEB端,IOS手机端,外部导入',',')[cur.data_source]}</td>
          </tr>
        </c:forEach>
    </table>
</body>
</html>