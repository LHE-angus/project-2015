<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康佳产品展示平台</title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/pshow/css/global.css">
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/pshow/css/sale.css">
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/pshow/css/tabs.css">
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/pshow/js/Slide.jquery.js"></script>
</head>
<body>
<!-- head start -->
<div id="topbox">
 <jsp:include page="_inc/_top2.jsp" flush="true" />
 <jsp:include page="_inc/_head2.jsp" flush="true" />
</div>
<!-- head end --> 
<!-- topnav start -->
 <jsp:include page="_inc/_head_nav2.jsp" flush="true" />
<!-- topnav end --> 
<!-- first start -->
<div class="maincont">
  <jsp:include page="_inc/_navigate.jsp" flush="true" />	
  <!--end topblock --> 
  <!--focus-->
  <div id="wrapper">
	<jsp:include page="_inc/_focus_images.jsp" flush="true" />
  </div>
  <div class="clear"></div>
</div>
<!-- first end--> 
<!-- second start -->
<div class="maincont">
  <div id="tabs3">
    <div class="tabs-title">
      <ul class="tabs-list">
        <li class="tabs-option">特惠机型</li>
        <li class="tabs-option">热卖机型</li>
        <li class="tabs-option">推荐机型</li>
      </ul>
      <div class="clear"></div>
    </div>
    <div class="tabs-box">
      <div class="fatbox">
        <!-- 特惠 -->
        <div class="tabs-content">
          <ul class="listul1">
	       <c:forEach items="${cate3List}" var="cur" varStatus="vs">
	       	  <c:if test="${vs.count gt 1 and (vs.count mod 4) eq 0}"><c:set var="li_class_3" value="rnoline" /></c:if>
		      <li class="${li_class_3}">
		        <div class="ltt">
		          <h3><a href="${ctx}/customer/manager/PShow.do?method=OrderNow&id=${cur.id}">康佳${cur.map.md_name}&nbsp;${fnx:abbreviate(cur.pd_desc, 2*22, '')}</a></h3>
		          <span>零售指导价：<font class="midtxt">${cur.sale_price}</font></span><span>折扣价：<font class="redf16">${cur.buy_price}</font></span></div>
		        <div class="rpic"><a href="${ctx}/customer/manager/PShow.do?method=OrderNow&id=${cur.id}"><img src="${ctx}/${fn:substringBefore(cur.main_pic, '.')}_086.jpg" /></a></div>
		      </li>	
	       </c:forEach>
          </ul>
        </div>
        <!-- 热卖 -->
        <div class="tabs-content">
          <ul class="listul1">
	       <c:forEach items="${cate1List}" var="cur" varStatus="vs">
	       	  <c:if test="${vs.count gt 1 and (vs.count mod 4) eq 0}"><c:set var="li_class_1" value="rnoline" /></c:if>
		      <li class="${li_class_1}">
		        <div class="ltt">
		          <h3><a href="${ctx}/customer/manager/PShow.do?method=OrderNow&id=${cur.id}">康佳${cur.map.md_name}&nbsp;${fnx:abbreviate(cur.pd_desc, 2*22, '')}</a></h3>
		          <span>零售指导价：<font class="midtxt">${cur.sale_price}</font></span><span>折扣价：<font class="redf16">${cur.buy_price}</font></span></div>
		        <div class="rpic"><a href="${ctx}/customer/manager/PShow.do?method=OrderNow&id=${cur.id}"><img src="${ctx}/${fn:substringBefore(cur.main_pic, '.')}_086.jpg" /></a></div>
		      </li>	
	       </c:forEach>
          </ul>
        </div>
        <!-- 推荐  -->
        <div class="tabs-content">
          <ul class="listul1">
	       <c:forEach items="${cate4List}" var="cur" varStatus="vs">
	       	  <c:if test="${vs.count gt 1 and (vs.count mod 4) eq 0}"><c:set var="li_class_4" value="rnoline" /></c:if>
		      <li class="${li_class_4}">
		        <div class="ltt">
		          <h3><a href="${ctx}/customer/manager/PShow.do?method=OrderNow&id=${cur.id}">康佳${cur.map.md_name}&nbsp;${fnx:abbreviate(cur.pd_desc, 2*22, '')}</a></h3>
		          <span>零售指导价：<font class="midtxt">${cur.sale_price}</font></span><span>折扣价：<font class="redf16">${cur.buy_price}</font></span></div>
		        <div class="rpic"><a href="${ctx}/customer/manager/PShow.do?method=OrderNow&id=${cur.id}"><img src="${ctx}/${fn:substringBefore(cur.main_pic, '.')}_086.jpg" /></a></div>
		      </li>	
	       </c:forEach>
	     </ul>  
        </div>
        <div class="clear"></div>
      </div>
    </div>
  </div>
  <script type="text/javascript">
	$("#tabs3").tabs({opacity:true});
  </script>
  <div class="clear"></div>
</div>
<!-- second end --> 
<!-- third start -->
<div class="maincont">
  <div class="tit2"><span><a href="${ctx}/customer/manager/PShow.do?method=list&cate_val=0">更多&gt;&gt;</a></span>
    <h3>新品发布区</h3>
    <div class="clear"></div>
  </div>
  <div class="clear"></div>
  <div class="tabs-box">
    <div class="adv1"><a href="#"><img src="${ctx}/styles/customer/pshow/images/p3.jpg" /></a></div>
    <ul class="listul1">
       <c:forEach items="${cate0List}" var="cur" varStatus="vs">
       	  <c:if test="${vs.count gt 1 and (vs.count mod 4) eq 0}"><c:set var="li_class_0" value="rnoline" /></c:if>
	      <li class="${li_class_0}">
	        <div class="ltt">
	          <h3><a href="${ctx}/customer/manager/PShow.do?method=OrderNow&id=${cur.id}#">康佳${cur.map.md_name}&nbsp;${fnx:abbreviate(cur.pd_desc, 2*22, '')}</a></h3>
	          <span>零售指导价：<font class="midtxt">${cur.sale_price}</font></span><span>折扣价：<font class="redf16">${cur.buy_price}</font></span></div>
	        <div class="rpic"><a href="${ctx}/customer/manager/PShow.do?method=OrderNow&id=${cur.id}"><img src="${ctx}/${fn:substringBefore(cur.main_pic, '.')}_086.jpg" /></a></div>
	      </li>	
       </c:forEach>
    </ul>
  </div>
  <div class="clear"></div>
</div>
<!-- third end --> 
<!-- four start -->
<div class="maincont">
  <div class="tit3"><span><a href="${ctx}/customer/manager/PShow.do?method=list&cate_val=2">更多&gt;&gt;</a></span>
    <h3>折扣区</h3>
    <div class="clear"></div>
  </div>
  <div class="clear"></div>
  <div class="tabs-box">
    <div class="left_price">
      <ul class="priceul">
        <li><a href="#">九五折区</a></li>
        <li><a href="#">九八折区</a></li>
        <li><a href="#">特惠区</a></li>
      </ul>
    </div>
    <div class="right_price">
      <ul class="listul1">
	       <c:forEach items="${cate2List}" var="cur" varStatus="vs">
	       	  <c:if test="${vs.count gt 1 and (vs.count mod 3) eq 0}"><c:set var="li_class_2" value="rnoline" /></c:if>
		      <li class="${li_class_2}">
		        <div class="ltt">
		          <h3><a href="${ctx}/customer/manager/PShow.do?method=OrderNow&id=${cur.id}">康佳${cur.map.md_name}&nbsp;${fnx:abbreviate(cur.pd_desc, 2*22, '')}</a></h3>
		          <span>零售指导价：<font class="midtxt">${cur.sale_price}</font></span><span>折扣价：<font class="redf16">${cur.buy_price}</font></span></div>
		        <div class="rpic"><a href="${ctx}/customer/manager/PShow.do?method=OrderNow&id=${cur.id}"><img src="${ctx}/${fn:substringBefore(cur.main_pic, '.')}_086.jpg" /></a></div>
		      </li>	
	       </c:forEach>
      </ul>
    </div>
  </div>
  <div class="clear"></div>
</div>
<!-- four end --> 
<!-- five start -->
<div class="maincont" style="display:none;">
  <div class="tit4"><span><a href="#">更多&gt;&gt;</a></span>
    <h3>配件区</h3>
    <div class="clear"></div>
  </div>
  <div class="clear"></div>
  <div class="tabs-box">
    <ul class="listul1">
      <li>
        <div class="ltt">
          <h3><a href="#">康佳LED50E510DE 50英寸 全高清3D BBTV百事通 IE浏览器</a></h3>
          <span>零售指导价：<font class="midtxt">￥1699</font></span><span>折扣价：<font class="redf16">￥1499</font></span></div>
        <div class="rpic"><a href="#"><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></div>
      </li>
      <li>
        <div class="ltt">
          <h3><a href="#">康佳LED50E510DE 50英寸 全高清3D BBTV百事通 IE浏览器</a></h3>
          <span>零售指导价：<font class="midtxt">￥1699</font></span><span>折扣价：<font class="redf16">￥1499</font></span></div>
        <div class="rpic"><a href="#"><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></div>
      </li>
      <li>
        <div class="ltt">
          <h3><a href="#">康佳LED50E510DE 50英寸 全高清3D BBTV百事通 IE浏览器</a></h3>
          <span>零售指导价：<font class="midtxt">￥1699</font></span><span>折扣价：<font class="redf16">￥1499</font></span></div>
        <div class="rpic"><a href="#"><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></div>
      </li>
      <li class="rnoline">
        <div class="ltt">
          <h3><a href="#">康佳LED50E510DE 50英寸 全高清3D BBTV百事通 IE浏览器</a></h3>
          <span>零售指导价：<font class="midtxt">￥1699</font></span><span>折扣价：<font class="redf16">￥1499</font></span></div>
        <div class="rpic"><a href="#"><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></div>
      </li>
      <li>
        <div class="ltt">
          <h3><a href="#">康佳LED50E510DE 50英寸 全高清3D BBTV百事通 IE浏览器</a></h3>
          <span>零售指导价：<font class="midtxt">￥1699</font></span><span>折扣价：<font class="redf16">￥1499</font></span></div>
        <div class="rpic"><a href="#"><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></div>
      </li>
      <li>
        <div class="ltt">
          <h3><a href="#">康佳LED50E510DE 50英寸 全高清3D BBTV百事通 IE浏览器</a></h3>
          <span>零售指导价：<font class="midtxt">￥1699</font></span><span>折扣价：<font class="redf16">￥1499</font></span></div>
        <div class="rpic"><a href="#"><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></div>
      </li>
      <li>
        <div class="ltt">
          <h3><a href="#">康佳LED50E510DE 50英寸 全高清3D BBTV百事通 IE浏览器</a></h3>
          <span>零售指导价：<font class="midtxt">￥1699</font></span><span>折扣价：<font class="redf16">￥1499</font></span></div>
        <div class="rpic"><a href="#"><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></div>
      </li>
      <li class="rnoline">
        <div class="ltt">
          <h3><a href="#">康佳LED50E510DE 50英寸 全高清3D BBTV百事通 IE浏览器</a></h3>
          <span>零售指导价：<font class="midtxt">￥1699</font></span><span>折扣价：<font class="redf16">￥1499</font></span></div>
        <div class="rpic"><a href="#"><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></div>
      </li>
    </ul>
  </div>
  <div class="clear"></div>
</div>
<!-- five end --> 
<!-- six start -->
<div class="maincont">
  <div class="infobox" style="display:none;">
    <div class="tit5"><span><a href="#">更多&gt;&gt;</a></span>
      <h3>资讯信息</h3>
    </div>
    <div class="infotab">
      <ul class="infoul">
        <li><span>2013/7/28</span><a href="#">康佳9600系列领航大屏超高清消费升级</a></li>
        <li><span>2013/7/28</span><a href="#">康佳9600系列领航大屏超高清消费升级</a></li>
        <li><span>2013/7/28</span><a href="#">康佳9600系列领航大屏超高清消费升级</a></li>
        <li><span>2013/7/28</span><a href="#">康佳9600系列领航大屏超高清消费升级</a></li>
        <li><span>2013/7/28</span><a href="#">康佳9600系列领航大屏超高清消费升级</a></li>
        <li><span>2013/7/28</span><a href="#">康佳9600系列领航大屏超高清消费升级</a></li>
        <li><span>2013/7/28</span><a href="#">康佳9600系列领航大屏超高清消费升级</a></li>
        <li><span>2013/7/28</span><a href="#">康佳9600系列领航大屏超高清消费升级</a></li>
        <li><span>2013/7/28</span><a href="#">康佳9600系列领航大屏超高清消费升级</a></li>
        <li><span>2013/7/28</span><a href="#">康佳9600系列领航大屏超高清消费升级</a></li>
      </ul>
    </div>
  </div>
  <div class="activebox" style="display:none;">
    <h3>关注康佳</h3>
    <div class="acive1">
      <h5><a href="#"><img src="${ctx}/styles/customer/pshow/images/vxin.gif" /></a></h5>
      <p><a href="#">微信关注</a></p>
    </div>
    <div class="acive2">
      <h5><a href="#"><img src="${ctx}/styles/customer/pshow/images/sina.gif" /></a></h5>
      <p><a href="#">新浪微博</a></p>
    </div>
    <div class="acive2">
      <h5><a href="#"><img src="${ctx}/styles/customer/pshow/images/vbo.gif" /></a></h5>
      <p><a href="#">腾讯微博</a></p>
    </div>
  </div>
  <!-- footer start-->
  <jsp:include page="_inc/_footer2.jsp" flush="true" />
  <!-- footer end-->
</div>
<!-- six end --> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
});
function addFavorite(sURL, sTitle) { 
    try { 
        window.external.addFavorite(sURL, sTitle); 
    } catch (e) { 
        try { 
            window.sidebar.addPanel(sTitle, sURL, ""); 
        } catch (e) { 
            alert("加入收藏失败，请使用Ctrl+D进行添加！"); 
        } 
    } 
}
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
