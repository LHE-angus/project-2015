<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
  <div id="topcont">
    <div class="k_logo"><a href="<c:url value='/customer/manager/PShow.do' />"><img alt="康佳产品展示平台" src="${ctx}/styles/customer/pshow/images/k_logo.gif" border="0" /></a></div>
    <div class="k_top_r">
      <div class="k_topsearch">
        <div class="k_topsearch1">
          <div class="searchinput">
            <input class="input_search" name="" type="text" />
          </div>
          <div class="searchbut"><a href="#"><img alt="搜索" src="${ctx}/styles/customer/pshow/images/but_search.gif" /></a></div>
        </div>
        <div class="k_topsearch2"><strong>热门搜索：</strong><a href="${ctx}/customer/manager/PShow.do?method=list&label_3d=1&mark=3d">3D电视</a> <a href="${ctx}/customer/manager/PShow.do?method=list&label_int=1&mark=int">智能电视</a> <a href="${ctx}/customer/manager/PShow.do?method=list&label_www=1&mark=www">网络电视</a></div>
      </div>
      <div class="k_tel"><img alt="全国免费服务热线" src="${ctx}/styles/customer/pshow/images/tel.gif" /></div>
    </div>
  </div>
  <div class="clear"></div>