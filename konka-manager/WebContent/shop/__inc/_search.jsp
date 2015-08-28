<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<div id="search_topbox">
  <div id="search_topcont">
    <div class="search_k_logo"><a href="<c:url value='/shop/manager/Index.do' />"><img alt="康佳EPP 内部购机优惠平台" src="${ctx}/styles/shop/images/k_shop.jpg" border="0" /></a></div>
    <div class="search_k_top_r">
      <div class="search_k_topsearch">
        <div class="search_k_topsearch1">
          <form method="post" action="<c:url value='/shop/manager/KonkaBcompPd.do' />" id="search_form_top">
          <input type="hidden" name="method" value="list" />
          <div class="search_searchinput">
            <input class="search_input_search" name="pd_sn_or_pd_name_like" id="pd_sn_or_pd_name_like" value="${search_pd_name}" type="text" maxlength="50" />
          </div>
          </form>
          <div class="search_searchbut"><img alt="搜索" src="${ctx}/styles/shop/images/but_search.gif" id="search_btn_sub_top" style="cursor:pointer;" /></div>
        </div>
      </div>
      <div class="search_k_tel"><img alt="全国免费服务热线" src="${ctx}/styles/shop/images/tel.gif" /></div>
    </div>
  </div>
  <div class="search_clear"></div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$(document).delegate("#search_btn_sub_top", "click", function(){
		$("#search_form_top").submit();
	});
});
</script>