<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<div class="top">
  <div class="top_one">
    <div class="l" style="padding-left:26px;">收藏康佳在线销售系统</div>
    <div class="r"><font>欢迎您！<c:if test="${not empty fgsName}">（${fgsName}）</c:if>${shop.customer_name}（${customerUserInfo.user_name}）</font> | <a href="${ctx}/MyOrder.do">我的订单</a> | <a href="#">常见问题</a> | <a href="#">客户服务</a> | <a href="${ctx}/customer/login.do?method=logout" class="target-top">【退出】</a></div>
  </div>
  <div class="top_box">
    <div class="logo"><a href="<c:url value='/customer/manager/PShow.do' />"><img src="${ctx}/styles/customer/pshow/images/k_logo.gif" border="0" /></a></div>
    <div class="search">
      <div style="overflow:hidden; width:400px; padding-bottom:6px;">
        <div class="l_one">
          <input name="" type="text" class="se_one" />
        </div>
        <div class="b">
          <input type="button" class="se_two" value="搜索" />
        </div>
      </div><strong>热门搜索：</strong><a href="${ctx}/customer/manager/PShow.do?method=list&label_3d=1&mark=3d">3D电视</a> <a href="${ctx}/customer/manager/PShow.do?method=list&label_int=1&mark=int">智能电视</a> <a href="${ctx}/customer/manager/PShow.do?method=list&label_www=1&mark=www">网络电视</a></div>
    <div class="logo_right"><img src="${ctx}/styles/customer/shop/images/phone.gif" width="169" height="34" /></div>
  </div>
</div>