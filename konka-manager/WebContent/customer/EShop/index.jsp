<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>客户端登录——康佳渠道管理系统</title>
<link rel="stylesheet" type="text/css"  href="${ctx}/styles/customer/shop/css/global.css" />
<link rel="stylesheet" type="text/css"  href="${ctx}/styles/customer/shop/css/top.css" />
<link rel="stylesheet" type="text/css"  href="${ctx}/styles/customer/shop/css/font.css" />
<link rel="stylesheet" type="text/css"  href="${ctx}/styles/customer/shop/css/index.css" />
<link rel="stylesheet" type="text/css"  href="${ctx}/styles/customer/shop/css/bottom.css" />
<!--<link rel="stylesheet" type="text/css"  href="${ctx}/styles/customer/tab/style.css" />-->
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<!--<script type="text/javascript" src="${ctx}/styles/customer/tab/jquery-1.2.6.pack.js"></script>-->
<!--<script type="text/javascript" src="${ctx}/styles/customer/tab/ks-switch.pack.js"></script>-->
<script type="text/javascript" src="${ctx}/styles/customer/jquery.tabs.js"></script>
<style type="text/css">
#focus {width:812px; height:366px; overflow:hidden; position:relative;}
#focus ul {height:380px; position:absolute;}
#focus ul li {float:left; width:812px; height:366px; overflow:hidden; position:relative; background:#FFFFFF;}
#focus ul li div {position:absolute; overflow:hidden;}
#focus .btnBg {position:absolute; width:812px; height:20px; left:0; bottom:0; /*background:#000;*/}
#focus .btn {position:absolute; width:792px; height:10px; padding:5px 10px; right:0; bottom:0; text-align:right;}
#focus .btn span {display:inline-block; _display:inline; _zoom:1; width:10px; height:10px; _font-size:0; margin-left:5px; cursor:pointer; background:#fff;}
#focus .btn span.on {background:#fff;}
#focus .preNext {width:45px; height:100px; position:absolute; top:120px; background:url('${ctx}/styles/customer/shop/images/sprite.png') no-repeat 0 0; cursor:pointer;}
#focus .pre {left:0;}
#focus .next {right:0; background-position:right top;}

/* switchBox */
.switchBox{background:url("${ctx}/styles/customer/shop/images/r.jpg") repeat-x ; overflow:hidden;border-right:1px #bcbbc0 solid; width:803px;padding-top:0px;}
.switchBox dt{width:803px;;height:40px;line-height:40px;margin-top:0px;}
.switchBox span{float:left; width:107px; text-align:center;cursor:pointer;}
.switchBox span.on{border:1px #bcbbc0 solid; border-bottom:none; background:#FFF; display:block; width:106px; height:39px; text-decoration:none;}
.switchBox dt .active{background-color:#ffffcc;font-weight:bolder;}
.switchBox dd{width:804px;clear:both;display:none;height:453px;/*height:auto;/*height:100px;min-height:100px!important;line-height:20px;/*padding:10px;*/font-family:"宋体";}
.switchBox li{font-family:Microsoft Yahei;list-style-type:none;}
#switchBox2{width:803px;height:493px;}

/* tab */
.tab_box .hide{display:none;}
</style>
</head>
<body>
<!-- top -->
<!-- head:nav -->
<jsp:include page="_head.jsp" flush="true" />

<!--one-->
<div class="one">
  <div class="list">
    <div class="list_up"><font class="white">品 类</font></div>
    <div class="list_down">
      <ul>
        <li><a href="#">3D电视</a></li>
        <li><a href="#">智能电视</a></li>
        <!--<li><a href="#">3D智能云电视</a></li>-->
        <li><a href="#">网络电视</a></li>
      </ul>
    </div>
  </div>
  <div class="banner">
	<jsp:include page="_slider.jsp" flush="true" />
  	<!--<img src="${ctx}/styles/customer/shop/images/BANNER.jpg" width="812" height="366" />-->
  </div>
</div>
<!--one end-->
<!--two-->
<div class="two">
  <div class="two_left">
    <div class="two_up"><font class="white">尺 寸</font></div>
    <div class="two_down">
      <ul>
        <li>32寸</li>
        <li>37寸</li>
        <li>39寸</li>
        <li>42寸</li>
        <li>47寸</li>
        <li>50寸</li>
        <li>55寸</li>
      </ul>
    </div>
  </div>
  <div class="two_right">
  	<div class="two_right_up">
      <div class="two_r_u"><font class="gran18">新品机型</font></div>
    </div>
    <div class="two_r_d">
    	<ul>
	    	<c:forEach items="${cate0List}" var="cur_a" varStatus="vs_a">
	    		<c:choose>
					<c:when test="${cur_a.label_of_cate eq 0}">
						<c:set value="xinpin" var="sup_class" />
						<c:set value="新品" var="sup_name" />
					</c:when>
					<c:when test="${cur_a.label_of_cate eq 1}">
						<c:set value="remai" var="sup_class" />
						<c:set value="热卖" var="sup_name" />
					</c:when>
					<c:when test="${cur_a.label_of_cate eq 2}">
						<c:set value="jingbaojia" var="sup_class" />
						<c:set value="直降" var="sup_name" />
					</c:when>
					<c:when test="${cur_a.label_of_cate eq 3}">
						<c:set value="tejia" var="sup_class" />
						<c:set value="特惠" var="sup_name" />
					</c:when>
					<c:when test="${cur_a.label_of_cate eq 4}">
						<c:set value="cuxiao" var="sup_class" />
						<c:set value="推荐" var="sup_name" />
					</c:when>
				</c:choose>
				<li>
	    			<div class="t_l" title="${cur_a.pd_desc}">
	    				<p class="t_l_p1">康佳彩电${cur_a.map.md_name}<br/>${fnx:abbreviate(cur_a.pd_name, 2*22, '')}<br/><c:out value="${cur_a.pd_size}" />英寸</p>
	    				<p><font class="red"><fmt:formatNumber value="${cur_a.sale_price}" type="currency" /></font></p>
	    				<h3 class="p3" onclick="location.href='${ctx}/customer/manager/EShop.do?method=OrderNow&id=${cur_a.id}'">立即订购</h3>
	    			</div>
	    			<div class="t_r">
	    				<c:set value="${fn:split(cur_a.main_pic, ',')[0]}" var="main_pic_path" />
		  				<img src="${ctx}/${fn:substringBefore(main_pic_path, '.')}_120.jpg" width="95" height="95" />
		  				<c:if test="${not empty sup_class}">
							<sup class="${sup_class}"><c:out value="${sup_name}" /></sup>
						</c:if>
	    			</div>
				</li>
	    		<!--<div class="uu">
	    			<div class="t_l" title="${cur_a.pd_desc}">
	    				<p class="t_l_p1">康佳彩电${cur_a.map.md_name}<br/>${fnx:abbreviate(cur_a.pd_desc, 2*22, '')}<br/><c:out value="${cur_a.pd_size}" />英寸</p>
	    				<p><font class="red"><fmt:formatNumber value="${cur_a.sale_price}" type="currency" /></font></p>
	    				<h3 class="p3" onclick="location.href='${ctx}/customer/manager/EShop.do?method=OrderNow&id=${cur_a.id}'">立即订购</h3>
	    			</div>
	    			<div class="t_r">
	    				<c:set value="${fn:split(cur_a.main_pic, ',')[0]}" var="main_pic_path" />
		  				<img src="${ctx}/${fn:substringBefore(main_pic_path, '.')}_120.jpg" width="95" height="95" />
		  				<c:if test="${not empty sup_class}">
							<sup class="${sup_class}"><c:out value="${sup_name}" /></sup>
						</c:if>
	    			</div>
	    		</div>-->
	    		<c:if test="${vs_a.last}">
	    			<c:forEach begin="1" end="${5 - vs_a.index}">
	    				<li class="wb">
		   					<div class="t_l">
			    				<p class="t_l_p1"></p>
			    				<p><font class="red">&nbsp;</font></p>
			    			</div>
			    			<div class="t_r"></div>
		   				</li>
	    			</c:forEach>
	    		</c:if>
	    	</c:forEach>
	    	<c:if test="${empty cate0List}">
	    		<c:forEach begin="1" end="6">
    				<li class="wb">
	   					<div class="t_l">
		    				<p class="t_l_p1"></p>
		    				<p><font class="red">&nbsp;</font></p>
		    			</div>
		    			<div class="t_r"></div>
	   				</li>
	    		</c:forEach>
	    	</c:if>
    	</ul>
    </div>
  </div>
</div>
<!--two end-->

<!--three-->
<div class="two">
  <div class="two_left">
	  <div class="three_up"><font class="white">分辨率</font></div>
	  <div class="three_down">
	    <ul>
	      <li>1920*1080</li>
	      <li>1366*768</li>
	      <li>1024*768</li>
	      <li>其它</li>
	    </ul>
	  </div>
  </div>
  <div class="three_right">
    <div class="two_right_up">
      <div class="two_r_u"><font class="gran18">热卖机型</font></div>
    </div>
    <div class="two_r_d">
    	<ul>
	    	<c:forEach items="${cate1List}" var="cur_b" varStatus="vs_b">
	    		<c:choose>
					<c:when test="${cur_b.label_of_cate eq 0}">
						<c:set value="xinpin" var="sup_class" />
						<c:set value="新品" var="sup_name" />
					</c:when>
					<c:when test="${cur_b.label_of_cate eq 1}">
						<c:set value="remai" var="sup_class" />
						<c:set value="热卖" var="sup_name" />
					</c:when>
					<c:when test="${cur_b.label_of_cate eq 2}">
						<c:set value="jingbaojia" var="sup_class" />
						<c:set value="直降" var="sup_name" />
					</c:when>
					<c:when test="${cur_b.label_of_cate eq 3}">
						<c:set value="tejia" var="sup_class" />
						<c:set value="特惠" var="sup_name" />
					</c:when>
					<c:when test="${cur_b.label_of_cate eq 4}">
						<c:set value="cuxiao" var="sup_class" />
						<c:set value="推荐" var="sup_name" />
					</c:when>
				</c:choose>
				<li>
					<div class="t_l" title="${cur_b.pd_desc}">
	    				<p class="t_l_p1">康佳彩电<c:out value="${cur_b.map.md_name}"/><br/><c:out value="${fnx:abbreviate(cur_b.pd_name, 2*22, '')}" /><br/><c:out value="${cur_b.pd_size}" />英寸</p>
	    				<p><font class="red"><fmt:formatNumber value="${cur_b.sale_price}" type="currency" /></font></p>
	    				<h3 class="p3" onclick="location.href='${ctx}/customer/manager/EShop.do?method=OrderNow&id=${cur_b.id}'">立即订购</h3>
	    			</div>
	    			<div class="t_r">
	    				<c:set value="${fn:split(cur_b.main_pic, ',')[0]}" var="main_pic_path" />
		  				<img src="${ctx}/${fn:substringBefore(main_pic_path, '.')}_120.jpg" width="95" height="95" />
		    			<c:if test="${not empty sup_class}">
							<sup class="${sup_class}"><c:out value="${sup_name}" /></sup>
						</c:if>
	    			</div>
				</li>
	    		<c:if test="${vs_b.last}">
	    			<c:forEach begin="1" end="${5 - vs_b.index}">
		   				<li class="wb">
		   					<div class="t_l">
			    				<p class="t_l_p1"></p>
			    				<p><font class="red">&nbsp;</font></p>
			    			</div>
			    			<div class="t_r"></div>
		   				</li>
	    			</c:forEach>
	    		</c:if>
	    	</c:forEach>
	    	<c:if test="${empty cate1List}">
	    		<c:forEach begin="1" end="6">
	   				<li class="wb">
	   					<div class="t_l">
		    				<p class="t_l_p1"></p>
		    				<p><font class="red">&nbsp;</font></p>
		    			</div>
		    			<div class="t_r"></div>
	   				</li>
	    		</c:forEach>
	    	</c:if>
    	</ul>
    </div>
  </div>
</div>
<!--three end-->

<!--four-->
<div class="two">
  <div class="two_left">
    <div class="four_up"><font class="white">价格分类：品类</font></div>
    <div class="four_down">
      <ul>
        <li>智能</li>
        <li>非智能</li>
        <li>3D</li>
        <li>非3D</li>
        <li>大板</li>
      </ul>
    </div>
  </div>
  <div class="four_right">
      <!--<span style="padding-left:20px;width:160px;"><font class="gran18">推荐机型</font></span>-->
      <ul class="two_right_up tab_menu" id="nav">
        <li class="current"><font class="gran14"><a href="#">销量</a></font></li>
        <li><font class="gran14"><a href="#">价格</a></font></li>
        <li><font class="gran14"><a href="#">上架时间</a></font></li>
      </ul>
      <div class="tab_box">
		<div>
			<div class="two_r_d">
				<c:forEach items="${cate2List}" var="cur_a" varStatus="vs_a">
		    		<c:choose>
						<c:when test="${cur_a.label_of_cate eq 0}">
							<c:set value="xinpin" var="sup_class" />
							<c:set value="新品" var="sup_name" />
						</c:when>
						<c:when test="${cur_a.label_of_cate eq 1}">
							<c:set value="remai" var="sup_class" />
							<c:set value="热卖" var="sup_name" />
						</c:when>
						<c:when test="${cur_a.label_of_cate eq 2}">
							<c:set value="jingbaojia" var="sup_class" />
							<c:set value="直降" var="sup_name" />
						</c:when>
						<c:when test="${cur_a.label_of_cate eq 3}">
							<c:set value="tejia" var="sup_class" />
							<c:set value="特惠" var="sup_name" />
						</c:when>
						<c:when test="${cur_a.label_of_cate eq 4}">
							<c:set value="cuxiao" var="sup_class" />
							<c:set value="推荐" var="sup_name" />
						</c:when>
					</c:choose>
					<li>
		    			<div class="t_l" title="${cur_a.pd_desc}">
		    				<p class="t_l_p1">康佳彩电${cur_a.map.md_name}<br/>${fnx:abbreviate(cur_a.pd_desc, 2*22, '')}<br/><c:out value="${cur_a.pd_size}" />英寸</p>
		    				<p><font class="red"><fmt:formatNumber value="${cur_a.sale_price}" type="currency" /></font></p>
		    				<h3 class="p3" onclick="location.href='${ctx}/customer/manager/EShop.do?method=OrderNow&id=${cur_a.id}'">立即订购</h3>
		    			</div>
		    			<div class="t_r">
		    				<c:set value="${fn:split(cur_a.main_pic, ',')[0]}" var="main_pic_path" />
			  				<img src="${ctx}/${fn:substringBefore(main_pic_path, '.')}_120.jpg" width="95" height="95" />
			  				<c:if test="${not empty sup_class}">
								<sup class="${sup_class}"><c:out value="${sup_name}" /></sup>
							</c:if>
		    			</div>
					</li>
		    		<c:if test="${vs_a.last}">
		    			<c:forEach begin="1" end="${8 - vs_a.index}">
		    				<li class="wb">
			   					<div class="t_l">
				    				<p class="t_l_p1"></p>
				    				<p><font class="red">&nbsp;</font></p>
				    			</div>
				    			<div class="t_r"></div>
			   				</li>
		    			</c:forEach>
		    		</c:if>
		    	</c:forEach>
		    	<c:if test="${empty cate0List}">
		    		<c:forEach begin="1" end="9">
	    				<li class="wb">
		   					<div class="t_l">
			    				<p class="t_l_p1"></p>
			    				<p><font class="red">&nbsp;</font></p>
			    			</div>
			    			<div class="t_r"></div>
		   				</li>
		    		</c:forEach>
		    	</c:if>
			</div>
		</div>
		<div class="hide">
			<div class="two_r_d">
				<c:forEach items="${cate0List}" var="cur_a" varStatus="vs_a">
		    		<c:choose>
						<c:when test="${cur_a.label_of_cate eq 0}">
							<c:set value="xinpin" var="sup_class" />
							<c:set value="新品" var="sup_name" />
						</c:when>
						<c:when test="${cur_a.label_of_cate eq 1}">
							<c:set value="remai" var="sup_class" />
							<c:set value="热卖" var="sup_name" />
						</c:when>
						<c:when test="${cur_a.label_of_cate eq 2}">
							<c:set value="jingbaojia" var="sup_class" />
							<c:set value="直降" var="sup_name" />
						</c:when>
						<c:when test="${cur_a.label_of_cate eq 3}">
							<c:set value="tejia" var="sup_class" />
							<c:set value="特惠" var="sup_name" />
						</c:when>
						<c:when test="${cur_a.label_of_cate eq 4}">
							<c:set value="cuxiao" var="sup_class" />
							<c:set value="推荐" var="sup_name" />
						</c:when>
					</c:choose>
					<li>
		    			<div class="t_l" title="${cur_a.pd_desc}">
		    				<p class="t_l_p1">康佳彩电${cur_a.map.md_name}<br/>${fnx:abbreviate(cur_a.pd_desc, 2*22, '')}<br/><c:out value="${cur_a.pd_size}" />英寸</p>
		    				<p><font class="red"><fmt:formatNumber value="${cur_a.sale_price}" type="currency" /></font></p>
		    				<h3 class="p3" onclick="location.href='${ctx}/customer/manager/EShop.do?method=OrderNow&id=${cur_a.id}'">立即订购</h3>
		    			</div>
		    			<div class="t_r">
		    				<c:set value="${fn:split(cur_a.main_pic, ',')[0]}" var="main_pic_path" />
			  				<img src="${ctx}/${fn:substringBefore(main_pic_path, '.')}_120.jpg" width="95" height="95" />
			  				<c:if test="${not empty sup_class}">
								<sup class="${sup_class}"><c:out value="${sup_name}" /></sup>
							</c:if>
		    			</div>
					</li>
		    		<c:if test="${vs_a.last}">
		    			<c:forEach begin="1" end="${8 - vs_a.index}">
		    				<li class="wb">
			   					<div class="t_l">
				    				<p class="t_l_p1"></p>
				    				<p><font class="red">&nbsp;</font></p>
				    			</div>
				    			<div class="t_r"></div>
			   				</li>
		    			</c:forEach>
		    		</c:if>
		    	</c:forEach>
		    	<c:if test="${empty cate0List}">
		    		<c:forEach begin="1" end="9">
	    				<li class="wb">
		   					<div class="t_l">
			    				<p class="t_l_p1"></p>
			    				<p><font class="red">&nbsp;</font></p>
			    			</div>
			    			<div class="t_r"></div>
		   				</li>
		    		</c:forEach>
		    	</c:if>
		    </div>
		</div>
		<div class="hide">
			<div class="two_r_d">
				<c:forEach begin="1" end="9">
    				<li class="wb">
	   					<div class="t_l">
		    				<p class="t_l_p1"></p>
		    				<p><font class="red">&nbsp;</font></p>
		    			</div>
		    			<div class="t_r"></div>
	   				</li>
	    		</c:forEach>
	    	</div>
		</div>
	  </div>
  	
  	<!-- <dl class="switchBox" id="switchBox2">
		<dt>
			<li style="list-style-type:none;width:140px;float:left;height:40px;line-height:40px;text-align:center;"><font class="gran18">推荐机型</font></li>
			<span class="on"><font class="gran14"><a href="#">销量</a></font></span>
			<span><font class="gran14"><a href="#">价格</a></font></span>
			<span><font class="gran14"><a href="#">上架时间</a></font></span>
		</dt>
		<dd>
			<div class="two_r_d">
				<c:forEach items="${cate2List}" var="cur_a" varStatus="vs_a">
		    		<c:choose>
						<c:when test="${cur_a.label_of_cate eq 0}">
							<c:set value="xinpin" var="sup_class" />
							<c:set value="新品" var="sup_name" />
						</c:when>
						<c:when test="${cur_a.label_of_cate eq 1}">
							<c:set value="remai" var="sup_class" />
							<c:set value="热卖" var="sup_name" />
						</c:when>
						<c:when test="${cur_a.label_of_cate eq 2}">
							<c:set value="jingbaojia" var="sup_class" />
							<c:set value="直降" var="sup_name" />
						</c:when>
						<c:when test="${cur_a.label_of_cate eq 3}">
							<c:set value="tejia" var="sup_class" />
							<c:set value="特惠" var="sup_name" />
						</c:when>
						<c:when test="${cur_a.label_of_cate eq 4}">
							<c:set value="cuxiao" var="sup_class" />
							<c:set value="推荐" var="sup_name" />
						</c:when>
					</c:choose>
					<li>
		    			<div class="t_l" title="${cur_a.pd_desc}">
		    				<p class="t_l_p1">康佳彩电${cur_a.map.md_name}<br/>${fnx:abbreviate(cur_a.pd_desc, 2*22, '')}<br/><c:out value="${cur_a.pd_size}" />英寸</p>
		    				<p><font class="red"><fmt:formatNumber value="${cur_a.sale_price}" type="currency" /></font></p>
		    				<h3 class="p3" onclick="location.href='${ctx}/customer/manager/EShop.do?method=OrderNow&id=${cur_a.id}'">立即订购</h3>
		    			</div>
		    			<div class="t_r">
		    				<c:set value="${fn:split(cur_a.main_pic, ',')[0]}" var="main_pic_path" />
			  				<img src="${ctx}/${fn:substringBefore(main_pic_path, '.')}_120.jpg" width="95" height="95" />
			  				<c:if test="${not empty sup_class}">
								<sup class="${sup_class}"><c:out value="${sup_name}" /></sup>
							</c:if>
		    			</div>
					</li>
		    		<c:if test="${vs_a.last}">
		    			<c:forEach begin="1" end="${8 - vs_a.index}">
		    				<li class="wb"></li>
		    			</c:forEach>
		    		</c:if>
		    	</c:forEach>
		    	<c:if test="${empty cate0List}">
		    		<c:forEach begin="1" end="9">
	    				<li class="wb"></li>
		    		</c:forEach>
		    	</c:if>
			</div>
		</dd>
		<dd>
			<div class="two_r_d">
				<c:forEach items="${cate0List}" var="cur_a" varStatus="vs_a">
		    		<c:choose>
						<c:when test="${cur_a.label_of_cate eq 0}">
							<c:set value="xinpin" var="sup_class" />
							<c:set value="新品" var="sup_name" />
						</c:when>
						<c:when test="${cur_a.label_of_cate eq 1}">
							<c:set value="remai" var="sup_class" />
							<c:set value="热卖" var="sup_name" />
						</c:when>
						<c:when test="${cur_a.label_of_cate eq 2}">
							<c:set value="jingbaojia" var="sup_class" />
							<c:set value="直降" var="sup_name" />
						</c:when>
						<c:when test="${cur_a.label_of_cate eq 3}">
							<c:set value="tejia" var="sup_class" />
							<c:set value="特惠" var="sup_name" />
						</c:when>
						<c:when test="${cur_a.label_of_cate eq 4}">
							<c:set value="cuxiao" var="sup_class" />
							<c:set value="推荐" var="sup_name" />
						</c:when>
					</c:choose>
					<li>
		    			<div class="t_l" title="${cur_a.pd_desc}">
		    				<p class="t_l_p1">康佳彩电${cur_a.map.md_name}<br/>${fnx:abbreviate(cur_a.pd_desc, 2*22, '')}<br/><c:out value="${cur_a.pd_size}" />英寸</p>
		    				<p><font class="red"><fmt:formatNumber value="${cur_a.sale_price}" type="currency" /></font></p>
		    				<h3 class="p3" onclick="location.href='${ctx}/customer/manager/EShop.do?method=OrderNow&id=${cur_a.id}'">立即订购</h3>
		    			</div>
		    			<div class="t_r">
		    				<c:set value="${fn:split(cur_a.main_pic, ',')[0]}" var="main_pic_path" />
			  				<img src="${ctx}/${fn:substringBefore(main_pic_path, '.')}_120.jpg" width="95" height="95" />
			  				<c:if test="${not empty sup_class}">
								<sup class="${sup_class}"><c:out value="${sup_name}" /></sup>
							</c:if>
		    			</div>
					</li>
		    		<c:if test="${vs_a.last}">
		    			<c:forEach begin="1" end="${5 - vs_a.index}">
		    				<li class="wb"></li>
		    			</c:forEach>
		    		</c:if>
		    	</c:forEach>
		    	<c:if test="${empty cate0List}">
		    		<c:forEach begin="1" end="6">
	    				<li class="wb"></li>
		    		</c:forEach>
		    	</c:if>
			</div>
		</dd>
		<dd>
			<div class="two_r_d">
				<c:forEach begin="1" end="9" step="1">
					<li></li>
				</c:forEach>
			</div>
		</dd>
	</dl>-->
	<script type="text/javascript">
	$(function($){
		//$("#switchBox2").switchTab({effect: "slide"});

		var _top = $("#nav").position().top;
		$('.four_right').Tabs({event:'mouseover',callback:tabcallback,timeout:1000});	
		function tabcallback(){
			//alert("我是回调函数 :)");
			//$("html,body").animate({scrollTop: _top}, 1000);		
		}
	});
	</script>
  </div>
</div>
<!--four end-->

<!-- foot -->
<jsp:include page="_footer.jsp" flush="true" />
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
