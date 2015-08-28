<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<table width="100%" border="1" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td nowrap="nowrap" align="center" width="3%">序号</td>
        <td nowrap="nowrap" align="center" width="5%">采购日期</td>
        <td nowrap="nowrap" align="center" width="5%">采购单号</td>
        <td nowrap="nowrap" align="center" width="5%">供应商名称</td>
        <td nowrap="nowrap" align="center" width="5%">供应商编码</td>
        <td nowrap="nowrap" align="center" width="5%">数量</td>
        <td nowrap="nowrap" align="center" width="5%">应付金额（元）</td>
        <td nowrap="nowrap" align="center" width="5%">折扣率</td>
        <td nowrap="nowrap" align="center" width="5%">实付金额（元）</td>
        <td nowrap="nowrap" align="center" width="5%">单据状态</td>
        <td nowrap="nowrap" align="center" width="5%">创建日期</td>
        <td nowrap="nowrap" align="center" width="5%">创建人</td>
      </tr>
      <c:forEach var="cur" items="${allList}" varStatus="vs">
        <tr>
          <td nowrap="nowrap" align="center">${vs.count}</td>
          <td nowrap="nowrap" align="center"><fmt:formatDate value="${cur.OPR_DATE}" pattern="yyyy-MM-dd" /></td>
          <td nowrap="nowrap" align="center">
            <span title="点击可查看" style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, '${ctx}/customer/manager/JBill.do', 'view','bill_id=${cur.BILL_ID}&'+$('#bottomPageForm').serialize())"><font class="blue12px">${cur.BILL_SN}</font></span>
          </td>
          <td nowrap="nowrap" align="left">${cur.PARTNER_NAME }</td>
          <td nowrap="nowrap" align="left">${cur.PARTNER_SN }</td>
          <td nowrap="nowrap" align="right">
            <c:if test="${cur.TOTAL_NUM gt 0 }">${cur.TOTAL_NUM }</c:if>
            <c:if test="${cur.TOTAL_NUM lt 0 }">${-cur.TOTAL_NUM }</c:if>
          </td>
          <td nowrap="nowrap" align="right">${cur.REC_MONEY}</td>
          <td nowrap="nowrap" align="center">
              ${cur.DISCOUNT}%
          </td>
          <td nowrap="nowrap" align="right">${cur.MONEY }</td>
          <td nowrap="nowrap" align="center">
            <c:if test="${cur.BILL_STATE eq 0 }">待确认</c:if>
            <c:if test="${cur.BILL_STATE eq 1 }">已确认</c:if>
          </td>
          <td nowrap="nowrap" align="center"><fmt:formatDate value="${cur.ADD_DATE}" pattern="yyyy-MM-dd" /></td>
          <td nowrap="nowrap" align="center">${cur.ADD_USER_NAME }</td>
          <td nowrap="nowrap" align="center">
            <c:choose>
              <c:when test="${cur.BILL_STATE eq 1}">
                <span class="fblue" style="color:#ccc;cursor:pointer;">修改</span>&nbsp;
                <span class="fblue" style="color:#ccc;cursor:pointer;">删除</span>&nbsp;
                <span class="fblue" style="color:#ccc;cursor:pointer;">财务确认</span>&nbsp;
              </c:when>
              <c:otherwise>
                <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, '${ctx}/customer/manager/JBill.do', 'edit','bill_id=${cur.BILL_ID}&bill_type=${cur.BILL_TYPE }&'+$('#bottomPageForm').serialize())">修改</span>&nbsp;
                <span style="cursor:pointer;" class="fblue" onclick="deleteBill('${cur.BILL_ID}','1');">删除</span>&nbsp;
                <span style="cursor:pointer;" class="fblue" onclick="confirmJBill('${cur.BILL_ID}')">财务确认</span>&nbsp;
              </c:otherwise>
            </c:choose>
            <span style="cursor:pointer;" class="fblue" onclick="printJBill('${cur.BILL_ID}')">打印</span>
          </td>
        </tr>
      </c:forEach>
    </table>
</body>
</html>