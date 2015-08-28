<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<div id="loading" style="display:none;background:#333 url(${ctx}/images/load.gif) no-repeat center center;position:fixed !important;position:absolute;top:0;left:0;height:100%; width:100%; z-index:999;opacity:0.6; filter:alpha(opacity=60);font-size:14px;line-height:20px;">
  <p id="loading-one" style="color:#fff;position:absolute; top:50%; left:50%; margin:20px 0 0 -50px; padding:3px 10px;">数据加载中...</p>
</div>