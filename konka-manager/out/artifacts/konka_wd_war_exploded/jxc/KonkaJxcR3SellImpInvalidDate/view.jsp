<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>R3销售无效数据</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
-->
</style>
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;"/> 当前位置：${naviString}</div>
  <div class="rtabcont1">
    <html-el:form action="/KonkaJxcStockBill">
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th colspan="2">R3销售无效数据</th>
        </tr>
        <tr>
          <td  width="30%" nowrap="nowrap" class="title_item">R3销售单日期：</td>
          <td><fmt:formatDate value="${af.map.r3_sell_date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
        </tr>
        <tr>
          <td class="title_item">售达方：</td>
          <td>${fn:escapeXml(af.map.r3_code)}</td>
        </tr>
        <tr>
          <td class="title_item">分：</td>
          <td>${fn:escapeXml(af.map.f)}</td>
        </tr>
        <tr>
          <td class="title_item">分类描述：</td>
          <td>${fn:escapeXml(af.map.type_desc)}</td>
        </tr>
        <tr>
          <td class="title_item">名称（售）：</td>
          <td>${fn:escapeXml(af.map.r3_name)}</td>
        </tr>
        <tr>
          <td class="title_item">送达方：</td>
          <td>${fn:escapeXml(af.map.send_r3_code)}</td>
        </tr>
        <tr>
          <td class="title_item">名称（送）：</td>
          <td>${fn:escapeXml(af.map.send_r3_name)}</td>
        </tr>
        <tr>
          <td class="title_item">创建日期：</td>
          <td><fmt:formatDate value="${af.map.creat_date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
        </tr>
        <tr>
          <td class="title_item">订单号：</td>
          <td>${fn:escapeXml(af.map.order_sn)}</td>
        </tr>
        <tr>
          <td class="title_item">订单类型：</td>
          <td>${fn:escapeXml(af.map.order_type)}</td>
        </tr>
        <tr>
          <td class="title_item">项目：</td>
          <td>${fn:escapeXml(af.map.project)}</td>
        </tr>
        <tr>
          <td class="title_item">机型：</td>
          <td>${fn:escapeXml(af.map.pd_name)}</td>
        </tr>
        <tr>
          <td class="title_item">数量：</td>
          <td>${fn:escapeXml(af.map.count)}</td>
        </tr>
        <tr>
          <td class="title_item">单价（含税 ￥）：</td>
          <td><fmt:formatNumber value="${af.map.price}" pattern="0.00" /></td>
        </tr>
        <tr>
          <td class="title_item">总金额（含税 ￥）：</td>
          <td><fmt:formatNumber value="${af.map.sum_money}" pattern="0.00" /></td>
        </tr>
        <tr>
          <td class="title_item">K007（含税 ￥）：</td>
          <td><fmt:formatNumber value="${af.map.k007_money}" pattern="0.00" /></td>
        </tr>
        <tr>
          <td class="title_item">RB00（含税 ￥）：</td>
          <td><fmt:formatNumber value="${af.map.rb00_money}" pattern="0.00" /></td>
        </tr>
        <tr>
          <td class="title_item">总净值金额（含税 ￥）：</td>
          <td><fmt:formatNumber value="${af.map.zjz_money}" pattern="0.00" /></td>
        </tr>
        <tr>
          <td class="title_item">交货日期：</td>
          <td><fmt:formatDate value="${af.map.jh_date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
        </tr>
        <tr>
          <td class="title_item">KF交货单：</td>
          <td>${fn:escapeXml(af.map.kf_jh_bill)}</td>
        </tr>
        <tr>
          <td class="title_item">物流交货单：</td>
          <td>${fn:escapeXml(af.map.wl_jh_bill)}</td>
        </tr>
        <tr>
          <td class="title_item">发货仓位：</td>
          <td>${fn:escapeXml(af.map.fh_store)}</td>
        </tr>
        <tr>
          <td class="title_item">采购订单编号：</td>
          <td>${fn:escapeXml(af.map.purchase_order_sn)}</td>
        </tr>
        <tr>
          <td class="title_item">客户物料号：</td>
          <td>${fn:escapeXml(af.map.custom_wl_sn)}</td>
        </tr>
        <tr>
          <td class="title_item">办事处：</td>
          <td>${fn:escapeXml(af.map.bsc)}</td>
        </tr>
        <tr>
          <td class="title_item">销售组织：</td>
          <td>${fn:escapeXml(af.map.sell_org)}</td>
        </tr>
        <tr>
          <td class="title_item">添加时间：</td>
          <td><fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd"/></td>
        </tr>
        <tr>
          <td class="title_item">添加人：</td>
          <td>${fn:escapeXml(af.map.add_user_id)}</td>
        </tr>
        <tr>
          <td colspan="2" align="center"><html-el:button property="back" value=" 返回 " onclick="history.back();" styleClass="bgButtonBack"/></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
