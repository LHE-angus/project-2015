<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<META http-equiv=Expires content=0>
<META http-equiv=Cache-Control content=no-cache>
<META http-equiv=Pragma content=no-cache>
<title>零售通-康佳电器</title>

</head>
<body>
<div data-role="page">
  <div data-role="header" data-position="inline">
    <h1>匹配信息</h1>
    <a href="${ctx}/mobile/admin/Frames.do" data-icon="home" data-iconpos="notext" data-direction="reverse" class="ui-btn-left jqm-home">返回</a>
	<a onclick="logout();" data-icon="forward" data-iconpos="notext" data-direction="reverse" class="ui-btn-right jqm-home">退出</a>
  </div>
  
    <div data-role="fieldcontain">
    <ul data-role="listview" data-inset="true" data-theme="b" data-divider-theme="a">
    <li data-role="list-divider">R3网点信息</li>
    <li><p>R3网点名称（客户名称）：<c:out value="${af.map.customer_name}"/></p></li>
    <li><p>所在区域名称：<c:out value="${af.map.area_name}"/></p></li>
    <li><p>分公司所在地名称：<c:out value="${af.map.branch_area_name}"/></p></li>
    <li><p>客户类型：<c:out value="${af.map.customer_type}"/></p></li>
    <li><p>交易状态：
    <c:if test="${af.map.status eq 1}">
    	<c:out value="有交易"/>
	</c:if>
	<c:if test="${af.map.status eq 2}">
		<c:out value="无交易"/>
	</c:if></p></li>
    <li><p>R3编码：<c:out value="${af.map.r3_code}"/></p></li>
    <li><p>经办名称：<c:out value="${af.map.handle_name}"/></p></li>
    <li><p>合并客户编码：<c:out value="${af.map.customer_code}"/></p></li>
    <li><p>备注：<c:out value="${af.map.r3_desc}"/></p></li>
    <li><p>2010合并编码：<c:out value="${af.map.merge_code_2010}"/></p></li>
    <li><p>导入日期：<fmt:formatDate value="${af.map.import_date}" pattern="yyyy-mm-dd" /></p></li>
    <li><p>导入人姓名：<c:out value="${af.map.map.import_user_name}"/></p></li>
    <li><p>备注：<c:out value="${af.map.r3_desc}"/></p></li>
    <li data-role="list-divider">匹配买卖提网点信息</li>
    <li><p>匹配买卖提网点名称：<c:out value="${af.map.map.mmt_shop_name}"/></p></li>
    <li><p>地区邮编：<c:out value="${entpShop.post_code}"/></p></li>
    <li><p>所属地区：<c:forEach var="cur" items="${baseProvinceListList}">${cur.p_name}</c:forEach></p></li>
    <li><p>网点地址：<c:out value="${entpShop.street_addr}"/></p></li>
    <li><p>联系人：<c:out value="${entpShop.link_user}"/></p></li>
    <li><p>联系电话：<c:out value="${entpShop.link_phone}"/></p></li>
    <li><p>网点级别：
    <c:choose>
    <c:when test="${entpShop.shop_level eq 0}">普通网点</c:when>
	<c:when test="${entpShop.shop_level eq 1}">铜牌网点</c:when>
	<c:when test="${entpShop.shop_level eq 3}">金牌网点</c:when>
	</c:choose>
    </p></li>
    </ul>
    </div>

<div data-role="footer" class="ui-bar" data-position="fixed">
   	<div data-role="controlgroup"  data-type="horizontal">
		<a onclick="javascript:history.back(-1);" data-role="button" data-icon="back" data-theme="b" data-inline="true">返回</a>
	</div>
</div>

</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>