<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康佳&顺丰网上直销商城</title>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/sfmall/css/global1.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/sfmall/css/epp.css" /> 
<link rel=stylesheet type=text/css  href="${ctx}/styles/sfmall/css/slider.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/sfmall/css/purchase.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/sfmall/css/news.css" /> 
<link rel=stylesheet type=text/css  href="${ctx}/commons/styles/pager3.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script> 
</head>
<body>
<!-- head start -->
<!-- top start -->
<div class="topbox">
<jsp:include page="/sfmall/__inc/top.jsp" flush="true" />
<jsp:include page="/sfmall/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/sfmall/__inc/nav.jsp" flush="true" />
<!-- top end --> 
<jsp:include page="/sfmall/__inc/slider.jsp" flush="true" /> 
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
              <div class="listpic1"><table border="0" cellspacing="0" cellpadding="0" width="120"><tr><td height="110" style="overflow:hidden;" width="120" valign="middle" align="center"><a href="<c:url value='/sfmall/PdShow.do?goods_id=${cur.id}' />"><img src="${ctx}/${fn:substringBefore(cur.main_pic, '.')}_120.jpg" style="max-height:100px;" /></a></td></tr></table></div>
              <div class="listrxt1">
                <h3><a href="<c:url value='/sfmall/PdShow.do?goods_id=${cur.id}' />">康佳&nbsp;${fnx:abbreviate(cur.pd_sn, 2 * 7, '.')}&nbsp;${fn:escapeXml(cur.pd_size)}英寸</a></h3>
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
            <li><div class="listpic1"><a href="${ctx }/sfmall/PdShow.do?goods_id=${cur.goods_id }"><img src="${ctx}/${cur.img_url }" width="118"  height="81"/></a></div><div class="listrxt1"><h3><a href="${ctx }/sfmall/PdShow.do?goods_id=${cur.goods_id }" ><c:out value="${cur.md_name }"/></a></h3><h4>关注价格：￥${cur.price }</h4></div><div class="clear"></div></li>
             </c:forEach>
            </ul>
            </div>
     </div></c:if>
  </div>
   <!--right-->
    <div class="listright"><div class="position"><a href="${ctx }/memer/Index.do">首页</a> &gt; <a href="${ctx }/sfmall/ArticleInfo.do">内购咨询</a> </div>
      <div class="news-box">
        <div class="box_list">
        	<ul>
	          <c:forEach items="${entityList}" var="cur"> 
	          <li class="border"><span class="r grayfont"><fmt:formatDate value="${cur.pub_date}" pattern="yyyy/MM/dd" /></span><a href="${ctx }/sfmall/ArticleInfo.do?method=view&id=${cur.id}" target="_blank" title="${cur.title}">${fnx:abbreviate(cur.title, 2 * 45, '...')}</a></li>
	          </c:forEach>
         	</ul>
          <div style="padding:20px; text-align: center;"> 
           <form id="bottomPageForm" name="bottomPageForm" method="post" action="ArticleInfo.do"> 
		        <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
		        <script type="text/javascript">
					var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
					pager.addHiddenInputs("method", "list");
					document.write(pager.toString());
				</script> 
		    </form>
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
