<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<div id="topnavbox">
  <div id="topnavcont">
    <div class="topnavl">选择商品</div>
    <div class="topnavr">
      <div class="topnavrr">
        <div class="topnavrr1">我的购物车<em>0</em>件</div>
        <div class="topnavrr2"><a href="#">去结算</a></div>
      </div>
      <ul class="topnavul">
        <li><a href="<c:url value='/customer/manager/PShow.do' />">首 页</a></li>
        <li><a href="${ctx}/customer/manager/PShow.do?method=list">电 视</a></li>
        <li><a href="#">配 件</a></li>
        <li><a href="#">积分商城</a></li>
      </ul>
    </div>
  </div>
</div>