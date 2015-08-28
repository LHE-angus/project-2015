<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<div class="nav" style="margin-top:1px;">
  <div class="nav_box">
    <div class="nav_center">
      <div class="l">
        <ul>
          <li class="cur"><a href="${ctx}/customer/manager/EShop.do">首页</a></li>
          <li id="3d"><a href="${ctx}/customer/manager/EShop.do?method=list&label_3d=1&mark=3d">3D电视</a></li>
          <li id="int"><a href="${ctx}/customer/manager/EShop.do?method=list&label_int=1&mark=int">智能电视</a></li>
          <li id="www"><a href="${ctx}/customer/manager/EShop.do?method=list&label_www=1&mark=www">网络电视</a></li>
          <!--<li><a href="#">手机数码</a></li>
          <li><a href="#">生活家电</a></li>
          <li><a href="#">配件专区</a></li>-->
        </ul>
      </div>
      <div class="c" style="display:none;">
        <div class="jxc">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font class="pink"><a href="${ctx}/customer/manager/Frames.do?method=index">我的账户</a></font> </div>
        <div class="jxc_one"><font class="pink"><a href="${ctx}/customer/manager/Frames.do?method=index">进销存</a></font></div>
      </div>
      
      <!--<div class="c">
        <div class="card">我的购物车 <font class="pink"><a href="#">0</a></font> 件</div>
        <div class="card_one"><font class="pink12px">去结算</font></div>
      </div>-->
    </div>
  </div>
</div>
<script type="text/javascript">
	var mark = "${af.map.mark}";
	$("li", ".nav").each(function(){
		if ($(this).attr("id") == mark) {
			$("li", ".nav").removeClass("cur");
			$(this).addClass("cur");
		}
	});
</script>