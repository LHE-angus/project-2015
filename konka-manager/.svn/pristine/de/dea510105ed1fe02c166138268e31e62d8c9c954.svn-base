<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %> 
<div class="top-t"  style="position:fixed;left:0;top:0;z-index:99999;">
	   <div class="top">
	      <div class="le"><a href="<c:url value='/member/Index.do' />">欢迎光临<c:if test="${ecUser.user_type eq 1 }">康佳EPP内部购机优惠平台</c:if><c:if test="${ecUser.user_type eq 2 }">康佳网上直销商城</c:if>! </a> </div>
	      <div class="m">
	        <div class="m1"><h3><strong><a href="#" id="app">手机APP下载</a></strong>&nbsp; </h3></div> 
	        <div> &nbsp; &nbsp;<a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=806598'/>" ><font color="red">新手上路</font></a> </div>
	      </div>
	      <div class="ri"><c:if test="${not empty ecUser}"> &nbsp;  <a href="${ctx}/member/center/Index.do">会员中心</a> | 
	      <c:if test="${ecUser.user_type eq 1 }"> <a href="${ctx}/member/login.do?method=logout">退出登陆</a></c:if>
	      <c:if test="${ecUser.user_type eq 2 }"> <a href="${ctx}/touch/login.do?method=logout">退出登陆</a></c:if>
	      </c:if>
        <c:if test="${empty ecUser}">[<a id="top_login" href="${ctx}/member/login.do?">请登录</a>]</c:if>
        | <a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?' />method=view&id=751921">常见问题</a> | <a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?' />method=view&id=751913">客户服务</a>
		</div>
	 </div>
</div> 