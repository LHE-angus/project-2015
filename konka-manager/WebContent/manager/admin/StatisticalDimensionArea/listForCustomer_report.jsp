<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="rtabcont1">
    <table width="100%" border="1" >
      <tr class="tabtt1">
        <td nowrap="nowrap" align="center" width="4%">序号</td>
        <td nowrap="nowrap" align="center" width="5%">省</td>
        <td nowrap="nowrap" align="center" width="5%">市</td>
        <td nowrap="nowrap" align="center" width="5%">县</td>
        <td nowrap="nowrap" align="center" width="5%">镇</td>
        <td nowrap="nowrap" align="center" width="6%">人口</td>
        <td nowrap="nowrap" align="center" width="6%">面积</td>
        <td nowrap="nowrap" align="center" width="6%">GDP</td>
        <td nowrap="nowrap" align="center" width="6%">市场容量</td>
        <td nowrap="nowrap" align="center" width="6%">我品占比</td>
        <td nowrap="nowrap" align="center" width="6%">创维</td>
        <td nowrap="nowrap" align="center" width="6%">海信</td>
        <td nowrap="nowrap" align="center" width="6%">客户数</td>
        <td nowrap="nowrap" align="center" width="6%">网点数</td>
        <td nowrap="nowrap" align="center" width="6%">门店数</td>
        <td nowrap="nowrap" align="center" width="6%">业务员人数</td>
        <td nowrap="nowrap" align="center" width="6%">促销员人数</td>
        <td nowrap="nowrap" align="center" width="6%">零售额</td>
        <td nowrap="nowrap" align="center" width="6%">零售量</td>
        <td nowrap="nowrap" align="center" width="6%">零售均价</td>
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList1}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
            <td align="left">${cur.province_name}</td>
            <td align="left">${cur.city_name}</td>
            <td align="left">${cur.county_name}</td>
            <td align="left">${cur.town_name}</td>
            <td align="right">${cur.map.population}</td>
            <td align="right">${cur.map.area}</td>
            <td align="right">${cur.map.gdp}</td>
            <td align="right">${cur.map.market_size}</td>
            <td align="right">${cur.map.wpzb}</td>
            <td align="right">${cur.map.jpzb1}</td>
            <td align="right">${cur.map.jpzb2}</td>
            <td align="right">${cur.map.customer_num}</td>
            <td align="right">${cur.map.agent_num}</td>
            <td align="right">${cur.map.store_num}</td>
            <td align="right">${cur.map.ywy_num}</td>
            <td align="right">${cur.map.promoter_num}</td>
            <td align="right"><fmt:formatNumber value="${cur.map.month_retail_money}"  type="currency"/></td>
            <td align="right">${cur.map.month_retail_num}</td>
            <td align="right"><fmt:formatNumber value="${cur.map.retail_price}"  type="currency"/> </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
</div>
</body>
</html>
