<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<style type="text/css">
.rtable2 {border-top:1px #ccc solid;border-left:1px #ccc solid;}
.rtable2 td {border-right:1px #e3e3e3 solid;border-bottom:1px #e3e3e3 solid;padding:5px 5px 0px 5px;}
.rtable2 .tabtt1 {height:23px;background:url(../images/manager/tabtitbg1.gif) repeat-x;}
.rtable2 .tabtt1 td {border-right:1px #e3e3e3 solid;border-bottom:1px #ccc solid;padding:5px 5px 0px 5px;font-family:Microsoft yahei,"宋体";font-size:12px;color:#666;}
.rtable1 td {
	padding:2px 5px;
}

.filed_border{
	border-left: 1px solid #ccc;;
	border-right: 1px solid #ccc;;
	border-bottom:1px solid #ccc;;
}
</style>
</head>
<body>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center">序号</td>
        <td width="10%" nowrap="nowrap" align="center">流水号</td>
        <td width="8%" nowrap="nowrap" align="center">提交日期</td>
        <td width="8%" nowrap="nowrap" align="center">R3客户编码</td>
        <td nowrap="nowrap" align="center">客户名称</td>
        <td nowrap="nowrap" align="center">客户类型</td>
        <td width="6%" nowrap="nowrap" align="center">数量</td>
        <td width="6%" nowrap="nowrap" align="center">金额￥</td>
        <td width="6%" nowrap="nowrap" align="center">折扣￥</td>
        <c:if test="${af.map.dept_type eq 1}"> 
          <!-- 系统管理员 -->
          <td width="6%" nowrap="nowrap" align="center">待审核角色</td>
          <td width="6%" nowrap="nowrap" align="center">订单状态</td>
        </c:if>
        <c:if test="${af.map.dept_type eq 2}"> 
          <!-- 非系统管理员 -->
          <td width="6%" nowrap="nowrap" align="center">订单状态</td>
        </c:if>
        <td width="6%" nowrap="nowrap" align="center">R3单号</td>
        <td width="6%" nowrap="nowrap" align="center">同步人</td>
        <td width="6%" nowrap="nowrap" align="center">同步时间</td>
        <td width="6%" nowrap="nowrap" align="center">发货状态</td>
        <td width="6%" nowrap="nowrap" align="center">R3物流单号</td>
        <td width="6%" nowrap="nowrap" align="center">发货时间</td>
        <td width="6%" nowrap="nowrap" align="center">收货时间</td>
        <td nowrap="nowrap" align="center">分公司</td>
        <td nowrap="nowrap" align="center">经办</td>
        <td width="6%" nowrap="nowrap" align="center">变更短信</td>
        <td width="6%" nowrap="nowrap" align="center">流程</td>
      </tr>
      <c:forEach var="cur" items="${allList}" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
          <td align="center" nowrap="nowrap">${cur.trade_index}</td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.order_date}" pattern="yyyy-MM-dd"/></td>
          <td align="center" nowrap="nowrap">${cur.ag}</td>
          <td nowrap="nowrap">${cur.user_shop_name}</td>
          <td nowrap="nowrap" align="center">${cur.map.customer_type_name}</td>
          <td align="right" nowrap="nowrap">${cur.order_num}</td>
          <td align="right" nowrap="nowrap">
          	<span class="kz-price-12">
            	<fmt:formatNumber value="${cur.money}" type="currency" />
            </span>
          </td>
          <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.good_discount_price}" type="currency" />
            </span></td>
          <c:if test="${af.map.dept_type eq 1}"> 
            <!-- 系统管理员 -->
            <td align="center" nowrap="nowrap"><c:choose>
                <c:when test="${empty cur.map.next_audit_role_name}">无</c:when>
                <c:when test="${not empty cur.map.next_audit_role_name}">${cur.map.next_audit_role_name}</c:when>
              </c:choose></td>
            <td align="center" nowrap="nowrap"><c:choose>
                <c:when test="${(cur.audit_state eq 3) and (cur.kh_confirm_state ne -1) }"><span style="color:#00F;">已完结</span></c:when>
                <c:when test="${(cur.audit_state eq 4)}"><span style="color:orange;">已作废</span></c:when>
                <c:otherwise><span style="color:#F00;">审核中</span></c:otherwise>
              </c:choose></td>
          </c:if>
          <c:if test="${af.map.dept_type eq 2}"> 
            <!-- 非系统管理员 -->
            <td align="center" nowrap="nowrap"><c:choose>
                <c:when test="${cur.audit_state eq 3}"><span style="color:#00F;">已完结</span></c:when>
                <c:when test="${cur.audit_state eq 4}"><span style="color:orange;">已作废</span></c:when>
                <c:otherwise>
                  <c:choose>
                    <c:when test="${cur.map.states eq 0}"><span style="color:#F00;">待审核</span></c:when>
                    <c:when test="${cur.map.states eq 1}"><span style="color:green;">审核中</span></c:when>
                  </c:choose>
                </c:otherwise>
              </c:choose></td>
          </c:if>
          <td align="center" nowrap="nowrap">${empty cur.r3_id ? '未同步' : cur.r3_id}</td>
          <td align="center" nowrap="nowrap">${cur.sync_user}</td>
          <td align="center" nowrap="nowrap">${cur.sync_date}</td>
          <td align="center" nowrap="nowrap"><c:if test="${cur.is_delivered eq 0}">未发货</c:if>
            <c:if test="${cur.is_delivered eq 1}">已发货</c:if></td>
          <td align="right" nowrap="nowrap">${cur.map.vbedl}</td>
          <td align="right" nowrap="nowrap">${cur.map.lfdat}</td>
          <td align="right" nowrap="nowrap">${cur.map.shdat}</td>
          <td align="right" nowrap="nowrap">${cur.map.fgsName}</td>
          <td align="right" nowrap="nowrap">${cur.map.jbName}</td>
          <td align="center" nowrap="nowrap">${fn:split('待确认,不用确认,已确认', ',')[cur.kh_confirm_state + 1]}</td>
          <td align="center" nowrap="nowrap">${empty cur.process_id ? '未确定' : '已确定'}</td>
        </tr>
      </c:forEach>
    </table>
</body>
</html>