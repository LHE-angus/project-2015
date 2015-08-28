<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>康佳</title>
<link rel="stylesheet" href="${ctx}/styles/zxmall/2015/css/base.css"/>
<link rel="stylesheet" href="${ctx}/styles/zxmall/2015/css/index.css"/>  
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/news.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
</head>
<body id="web_body">
<jsp:include page="/zxmall/__inc/2015/top.jsp" flush="true" />  
<jsp:include page="/zxmall/__inc/2015/nav.jsp" flush="true" />  
<div class="main">
	<div class="w1200">
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
			          </li>
		        </ul>
		      </div>
		    </div>
	  </div>
  <!--right-->
	 <div class="listright-news">
		   <div class="position"><a href="<c:url value='/zxmall/Index.do' />">首页</a> <c:if test="${not empty af.map.article_type_id}">&gt; <a href="<c:url value='/zxmall/KonkaGroupPeArticleInfo.do' />?article_type_id=${af.map.article_type_id}"> <c:if test="${af.map.article_type_id eq 2000}">康佳资讯</c:if>
		    	<c:if test="${af.map.article_type_id eq 2100}">新手上路</c:if>
		    	<c:if test="${af.map.article_type_id eq 2200}">支付方式</c:if>
		    	<c:if test="${af.map.article_type_id eq 2300}">物流配送</c:if>
		    	<c:if test="${af.map.article_type_id eq 2400}">售后服务</c:if>
		    	<c:if test="${af.map.article_type_id eq 2500}">特色服务</c:if>
		    	<c:if test="${af.map.article_type_id eq 2600}">走进康佳</c:if>
		    	<c:if test="${af.map.article_type_id eq 2700}">会员规则</c:if></a> </c:if>
		    </div>
		    <div class="news-box">
		      <div class="box_list">
		        <ul>
		        	<c:forEach items="${entityList}" var="cur" varStatus="vs">
		        	     <c:if test="${cur.is_link_out eq 1  }">
		        		<li class="border"><span class="r grayfont"><fmt:formatDate value="${cur.pub_date}" pattern="yyyy/MM/dd" /></span><a href="${cur.link_out_addr }" target="_blank">${fn:escapeXml(cur.title)}</a></li>
		        		</c:if>  <c:if test="${cur.is_link_out eq 0  }">
		        		<li class="border"><span class="r grayfont"><fmt:formatDate value="${cur.pub_date}" pattern="yyyy/MM/dd" /></span><a href="<c:url value='/zxmall/KonkaGroupPeArticleInfo.do?method=view&id=${cur.id}' />">${fn:escapeXml(cur.title)}</a></li>
		        		</c:if>
		        		<c:if test="${vs.last eq true}">
			              <c:set var="i" value="${vs.count}" />
			            </c:if>
		        	</c:forEach>
		          	<c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
		            	<li class="border">&nbsp;</li>
		           	</c:forEach>
		        </ul>
		        <!--page--> 
		        <div style="padding:20px; text-align: center;">
			        <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaGroupPeArticleInfo.do">
			          <table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
			            <tbody>
			              <tr>
			                <td height="10"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
			                  <script type="text/javascript">
								var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
								pager.addHiddenInputs("method", "list");
								pager.addHiddenInputs("article_type_id", "${fn:escapeXml(af.map.article_type_id)}");
								document.write(pager.toString());
							</script></td>
			              </tr>
			            </tbody>
			          </table>
			        </form>
			      </div>
		    </div> 
		  </div>
	</div>
	</div>
</div> 
<jsp:include page="/zxmall/__inc/2015/footer.jsp" flush="true" /> 
</body>
</html>