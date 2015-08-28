<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %> 
	<div class="top-t"  style="position:fixed;left:0;top:0;z-index:99999;">
	   	 <div class="top">
	      <div class="le"><a href="<c:url value='/shop/Index.do' />">欢迎光临<c:if test="${ecUser.user_type eq 1 }">康佳EPP内部购机优惠平台</c:if><c:if test="${ecUser.user_type eq 2 }">康佳粉丝会</c:if>! </a> </div>
	      <div class="m"> 
	         <div>
	        &nbsp; &nbsp;<a href="${ctx}/shop/KonkaGroupPeArticleInfo.do?method=view&id=739322" ><font color="red">新手上路</font></a>
	        </div>
	      </div>
	      <div class="ri"><c:if test="${not empty ecUser}"> &nbsp;${ecUser.real_name } |&nbsp;  <a href="${ctx}/shop/center/Orders.do">订单查询</a> 
	      </c:if>
        <c:if test="${empty ecUser}">[<a id="top_login" href="${ctx}/shop/login.do?">请登录</a>]</c:if>
        | <a href="<c:url value='/shop/KonkaGroupPeArticleInfo.do?' />method=view&id=751921">常见问题</a> | <a href="<c:url value='/shop/KonkaGroupPeArticleInfo.do?' />method=view&id=751913">客户服务</a>
		</div>
	   	 </div>
 	 </div>
 