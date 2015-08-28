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
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
      <tr>
        <th colspan="2">发货登记基本信息</th>
      </tr>
      <tr>
        <td width="15%" class="title_item">发货编号</td>
        <td width="85%">${fn:escapeXml(af.map.sn)}</td>
      </tr>
      <tr>
        <td width="15%" class="title_item">发货总数</td>
        <td width="85%">${fn:escapeXml(af.map.fh_sum_count)}</td>
      </tr>
      <tr>
        <td width="15%" class="title_item">应收金额（￥）</td>
        <td width="85%">${fn:escapeXml(af.map.money_must)}</td>
      </tr>
      <tr>
        <td width="15%" class="title_item">实收金额（￥）</td>
        <td width="85%">${fn:escapeXml(af.map.money_result)}</td>
      </tr>
      <tr>
        <td width="15%" class="title_item">发货日期</td>
        <td width="85%"><fmt:formatDate value="${af.map.fh_date}" pattern="yyyy-MM-dd"/></td>
      </tr>
      <tr>
        <td width="15%" class="title_item">添加人</td>
        <td width="85%">${fn:escapeXml(af.map.add_user_name)}</td>
      </tr>
      <tr>
        <td width="15%" class="title_item">添加人部门</td>
        <td width="85%">${fn:escapeXml(af.map.add_dept_name)}</td>
      </tr>
      <c:if test="${not empty is_fenjingban}">
        <tr>
          <td width="15%" class="title_item">是否确认收货</td>
          <td width="85%"><c:choose>
              <c:when test="${af.map.is_confirm eq 0}"> <span style="color:#F00;">未确认</span></c:when>
              <c:when test="${af.map.is_confirm eq 1}"> <span style="color:#060;">已确认</span></c:when>
            </c:choose></td>
        </tr>
      </c:if>
      <!--      <tr>--> 
      <!--        <td width="15%" class="title_item">修改人</td>--> 
      <!--        <td width="85%"><c:if test="${not empty af.map.map.update_user_name}"> ${fn:escapeXml(af.map.map.update_user_name)} </c:if>--> 
      <!--          <c:if test="${empty af.map.map.update_user_name}"> 无 </c:if></td>--> 
      <!--      </tr>--> 
      <!--      <tr>--> 
      <!--        <td width="15%" class="title_item">更新时间</td>--> 
      <!--        <td width="85%"><c:if test="${not empty af.map.update_date}">--> 
      <!--            <fmt:formatDate value="${af.map.update_date}" pattern="yyyy-MM-dd HH:mm:ss"/>--> 
      <!--          </c:if>--> 
      <!--          <c:if test="${empty af.map.update_date}"> 无 </c:if></td>--> 
      <!--      </tr>-->
      <tr>
        <td colspan="2" align="center"><table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
            <tr>
              <th colspan="9">发货登记明细</th>
            </tr>
            <tr>
              <td width="7%" align="center">序号</td>
              <td width="10%" align="center">产品大类</td>
              <td width="10%" align="center">产品品牌</td>
              <td width="15%" align="center">产品型号</td>
              <td width="8%" align="center">发货数量</td>
              <td width="10%" align="center">单价（￥）</td>
              <td width="15%" align="center"><c:if test="${not empty is_shiyebu}">分公司</c:if>
                <c:if test="${not empty is_fenjingban}">网点</c:if></td>
              <td width="15%" align="center">出货仓库</td>
              <!--              <td width="12%" align="center">添加人</td>--> 
              <!--              <td width="10%" align="center">添加人部门</td>--> 
              <!--              <td width="10%" align="center">添加时间</td>--> 
              <!--              <td width="15%" align="center">修改人</td>--> 
              <!--              <td width="10%" align="center">更新时间</td>-->
              <td width="10%" align="center">备注</td>
            </tr>
            <c:forEach items="${konkaJxcFhBillDetailsList}" var="cur" varStatus="vs">
              <tr>
                <td align="center">${vs.count}</td>
                <td align="center">${fn:escapeXml(cur.pd_type_name)}</td>
                <td align="center">${fn:escapeXml(cur.brand_name)}</td>
                <td align="center">${fn:escapeXml(cur.pd_name)}</td>
                <td align="center">${fn:escapeXml(cur.count)}</td>
                <td align="center">${fn:escapeXml(cur.price)}</td>
                <td align="center"> 
                <c:if test="${empty cur.map.branch_name}">暂无</c:if>
                <c:if test="${not empty cur.map.branch_name}">${fn:escapeXml(cur.map.branch_name)}</c:if> </td>
                <td align="center">
                <c:if test="${empty cur.map.store_name}">暂无</c:if>
                <c:if test="${not empty cur.map.store_name}">${fn:escapeXml(cur.map.store_name)}</c:if></td>
                <!--                <td align="center">${fn:escapeXml(cur.map.add_user_name)}</td>--> 
                <!--                <td align="center">${fn:escapeXml(cur.map.add_dept_name)}</td>--> 
                <!--                <td align="center"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>--> 
                <!--                <td align="center"><c:if test="${not empty cur.map.update_user_name}"> ${fn:escapeXml(cur.map.update_user_name)} </c:if>--> 
                <!--                  <c:if test="${empty cur.map.update_user_name}"> 无 </c:if></td>--> 
                <!--                <td align="center"><c:if test="${not empty cur.update_date}">--> 
                <!--                    <fmt:formatDate value="${cur.update_date}" pattern="yyyy-MM-dd HH:mm:ss" />--> 
                <!--                  </c:if>--> 
                <!--                  <c:if test="${empty cur.update_date}"> 无 </c:if></td>-->
                <td align="center">${fn:escapeXml(cur.remark)}</td>
              </tr>
            </c:forEach>
            <tr>
              <td align="center" colspan="9" style="text-align:center"><input type="button" class="bgButton" value=" 返回 " onclick="history.back();" /></td>
            </tr>
          </table></td>
      </tr>
    </table>
  </div>
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>