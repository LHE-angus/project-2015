<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康佳网上直销商城</title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/global1.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/epp.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/slider.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/purchase.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/news.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
</head>
<body>
<!-- head start -->
<div class="topbox">
<jsp:include page="/zxmall/__inc/top.jsp" flush="true" />
<jsp:include page="/zxmall/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/zxmall/__inc/nav.jsp" flush="true" />
<!-- first start -->
<!-- first end -->
<!-- second start -->
<div class="maincont margintop10">
  <!--left-->
  <div class="listleft-news">
    <div class="larticle-list"> 
      <div class="larticle_category">
        <ul>
          <li><a class="a_cat_a" href="javascript:void(0);">资讯动态</a>
	          <a class="a_cat_b" href="<c:url value='/zxmall/KonkaGroupPeArticleInfo.do?article_type_id=2000' />">康佳资讯</a>
		  </li>
          <li><a class="a_cat_a" href="javascript:void(0);">帮助中心</a>
	          <a class="a_cat_b" href="<c:url value='/zxmall/KonkaGroupPeArticleInfo.do?article_type_id=2100' />">新手上路</a>
	          <a class="a_cat_b" href="<c:url value='/zxmall/KonkaGroupPeArticleInfo.do?article_type_id=2200' />">支付方式</a>
	          <a class="a_cat_b" href="<c:url value='/zxmall/KonkaGroupPeArticleInfo.do?article_type_id=2300' />">物流配送</a>
	          <a class="a_cat_b" href="<c:url value='/zxmall/KonkaGroupPeArticleInfo.do?article_type_id=2400' />">售后服务</a>
	          <a class="a_cat_b" href="<c:url value='/zxmall/KonkaGroupPeArticleInfo.do?article_type_id=2500' />">特色服务</a>
	          <a class="a_cat_b" href="<c:url value='/zxmall/KonkaGroupPeArticleInfo.do?article_type_id=2600' />">走进康佳</a>
	          <!--  <a class="a_cat_b" href="<c:url value='/zxmall/KonkaGroupPeArticleInfo.do?article_type_id=2800' />">球队介绍</a>  -->
	        </li>
        </ul>
      </div>
    </div>
  </div>
  <!--right-->
  <div class="listright-news">
    <div class="position"><a href="<c:url value='/zxmall/Index.do' />">首页</a> <c:if test="${not empty af.map.article_type_id}">&gt; <a href="<c:url value='/zxmall/KonkaGroupPeArticleInfo.do' />?article_type_id=${af.map.article_type_id}"><c:if test="${empty af.map.article_type_id}">新闻资讯</c:if>
    	<c:if test="${af.map.article_type_id eq 2000}">康佳资讯</c:if>
    	<c:if test="${af.map.article_type_id eq 2100}">新手上路</c:if>
    	<c:if test="${af.map.article_type_id eq 2200}">支付方式</c:if>
    	<c:if test="${af.map.article_type_id eq 2300}">物流配送</c:if>
    	<c:if test="${af.map.article_type_id eq 2400}">售后服务</c:if>
    	<c:if test="${af.map.article_type_id eq 2500}">特色服务</c:if>
    	<c:if test="${af.map.article_type_id eq 2600}">走进康佳</c:if>
    	<c:if test="${af.map.article_type_id eq 2700}">会员规则</c:if>
    	<!--<c:if test="${af.map.article_type_id eq 2800}">球队介绍</c:if>-->
    	</a> </c:if>&gt; ${af.map.title}</div>
    <div class="news-box">
      <div class="box_list">
      	<c:if test="${not empty af.map.id and not empty af.map.is_del}">
	        <div class="wenzi1">
			    <h2 align="center"><font style="font-size:32px; margin-top: 20px;line-height:32px;">${af.map.title}</font></h2>
			    <div class="riqi">浏览次数：${af.map.view_counts}次  &nbsp;&nbsp;发布时间：
			      <fmt:formatDate value="${af.map.pub_date}" pattern="yyyy-MM-dd HH:mm:ss" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    </div>
			    <c:if test="${not empty af.map.img_path}">
			      <div style="text-align:center;"> <img src="${ctx}/${fn:substringBefore(af.map.img_path, '.')}_240.jpg" title="${af.map.img_desc}" />
			        <c:if test="${not empty af.map.img_desc}"> <br />
			          <c:out value="${af.map.img_desc}" />
			        </c:if>
			      </div>
			    </c:if>
			    <div class="n_rong" style="padding-left: 5%;padding-right: 5%;">
			      <c:out value="${af.map.content}" escapeXml="false" />
			    </div>
			    <c:if test="${not empty attachmentList}">
			      <div class="riqi"></div>
			      <div style="padding-left: 10px;">
			        <c:forEach var="cur" items="${attachmentList}" varStatus="vs">${vs.count}、<a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}">${cur.file_name}</a>
			        </c:forEach>
			      </div>
			    </c:if>
			 </div>
		 </c:if>
      </div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<!-- second end -->
<!-- six footer -->
<jsp:include page="../__inc/footer.jsp" flush="true" />
<!-- six footer -->
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var form = document.listForm;

});

//]]></script>
</body>
</html>
