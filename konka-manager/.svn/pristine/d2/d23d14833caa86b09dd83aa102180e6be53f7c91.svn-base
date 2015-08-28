<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康佳网上直销商城</title>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/zxmall/css/global1.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/zxmall/css/epp.css" /> 
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/slider.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/purchase.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/news.css" />
<link rel=stylesheet type=text/css  href="${ctx}/commons/styles/pager3.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script> 
</head>
<body>
<!-- head start -->
<!-- top start -->
<div class="topbox">
<jsp:include page="/zxmall/__inc/top.jsp" flush="true" />
<jsp:include page="/zxmall/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/zxmall/__inc/nav.jsp" flush="true" />
<!-- top end --> 
<jsp:include page="/zxmall/__inc/slider.jsp" flush="true" /> 
<!-- first end -->
<!-- second start -->
<div class="maincont margintop10">
  <!--left-->
  <div class="listleft">
    <div class="liscont1">
      <div class="listit1">推荐热卖</div>
      <div class="listbox1">
        <ul class="listul4">
          <c:forEach items="${bcomp_pd_list_top_5}" var="cur" varStatus="vs">
          	<c:set var="li_class" value="" />
            <c:if test="${vs.last}"><c:set var="li_class" value="noline4" /></c:if>
            <li style="padding:0;" class="${li_class}">
              <div class="listpic1"><table border="0" cellspacing="0" cellpadding="0" width="120"><tr><td height="110" style="overflow:hidden;" width="120" valign="middle" align="center"><a href="<c:url value='/zxmall/PdShow.do?goods_id=${cur.id}' />"><img src="${ctx}/${fn:substringBefore(cur.main_pic, '.')}_120.jpg" style="max-height:100px;" /></a></td></tr></table></div>
              <div class="listrxt1">
                <h3><a href="<c:url value='/zxmall/PdShow.do?goods_id=${cur.id}' />">康佳&nbsp;${fnx:abbreviate(cur.pd_sn, 2 * 7, '.')}&nbsp;${fn:escapeXml(cur.pd_size)}英寸</a></h3>
                <h4>价格：￥
                  <fmt:formatNumber value="${cur.ecGoodsPrice.price}" pattern="0" />
                </h4>
              </div>
              <div class="clear"></div>
            </li>
          </c:forEach>
        </ul>
      </div>
      <div class="clear"></div>
    </div>
      <c:if test="${not empty ecUserFavotriesList}">
     <div class="liscont1 margintop10">
        	<div class="listit2">关注商品</div>
            <div class="listbox1">
            <ul class="listul4">
            <c:forEach var="cur" items="${ecUserFavotriesList}" varStatus="vs"> 
            <li><div class="listpic1"><a href="${ctx }/zxmall/PdShow.do?goods_id=${cur.goods_id }"><img src="${ctx}/${cur.img_url }" width="118"  height="81"/></a></div><div class="listrxt1"><h3><a href="${ctx }/zxmall/PdShow.do?goods_id=${cur.goods_id }" ><c:out value="${cur.md_name }"/></a></h3><h4>关注价格：￥${cur.price }</h4></div><div class="clear"></div></li>
             </c:forEach>
            </ul>
            </div>
     </div></c:if>
  </div>
   <!--right-->
    <div class="listright"><div class="position"><a href="${ctx }/memer/Index.do">首页</a> &gt; <a href="${ctx }/zxmall/ArticleInfo.do">内购咨询</a> &gt; 咨询正文</div>
     <div class="news-box">
        <div class="news_body"><h1 class="news_title">${entity.title }</h1>
		<p class="news_meta"> 浏览次数：${entity.view_counts}次     发表日期：<fmt:formatDate value="${entity.pub_date}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
		<div class="news_main">
			<c:if test="${not empty entity.img_path}">
		    <div style="text-align:center;"> <img src="${ctx}/${fn:substringBefore(entity.img_path, '.')}_240.jpg" title="${entity.img_desc}" />
		      <c:if test="${not empty entity.img_desc}"> <br />
		        <c:out value="${entity.img_desc}" />
		      </c:if>
		    </div>
		  </c:if>
		  <div class="rtabcont2">
		    <c:out value="${entity.content}" escapeXml="false" />
		  </div>
		  <c:if test="${not empty attachmentList}">
		    <div class="rtabcont2">
		      <c:forEach var="cur" items="${attachmentList}" varStatus="vs"> ${vs.count}、<a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}">${cur.file_name}</a><br />
		      </c:forEach>
		    </div>
		  </c:if>		
	　      </div>
		</div>
	</div>
	</div>
</div>
<!-- second end -->
<!-- six footer -->
<jsp:include page="../__inc/footer.jsp" flush="true" />
<!-- six footer -->
</body>
</html>
