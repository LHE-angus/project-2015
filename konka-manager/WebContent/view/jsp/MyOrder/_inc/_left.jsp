<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>康佳</title>
</head>
<body>
  <div class="uesrleft">
    <div class="uesrsys"><font class="blue">用户中心</font></div>
    <div class="uesrsysbox">
      <dl>
        <dt><font class="blue">交易管理</font></dt>
        <dd><a href="${ctx}/MyOrder.do">我的订单</a></dd>
        <!-- 
        <dd><a href="${ctx}/customer/manager/UserFavorite.do">我的收藏</a></dd>
        <dd><a href="${ctx}/customer/manager/CustomerScore.do">我的积分</a></dd>
        <dt><font class="blue">用户资料管理</font></dt>
        <dd><a href="${ctx}/customer/manager/CustomerInfo.do">用户资料修改</a></dd>
        <dd><a href="${ctx}/customer/manager/CustomerPassward.do">密码修改</a></dd>
        <dd><a href="${ctx}/customer/manager/CustomerRelation.do">收货地址管理</a></dd>
         -->
      </dl>
    </div>
  </div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
