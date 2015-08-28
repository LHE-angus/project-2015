<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %> 
<div class="top-t"  style="position:fixed;left:0;top:0;z-index:99999;">    
	   <div class="top">
	      <div class="le"><c:if test="${not empty zxmall}">${zxmall.user_name}！</c:if><c:if test="${empty zxmall}">游客！</c:if><a href="<c:url value='/zxmall/Index.do' />">欢迎光临康佳直销商城！ </a> </div>
	      <div class="m">
	       <!-- <div class="m1"><h3><strong><a href="#" id="app">手机APP下载</a></strong>&nbsp; </h3></div>   
	        <div> &nbsp; &nbsp;<a href="<c:url value='/zxmall/KonkaGroupPeArticleInfo.do?method=view&id=806598'/>" ><font color="red">新手上路</font></a> </div> -->
	      </div>
	      <div class="ri"><c:if test="${not empty zxmall}"> &nbsp;  <a href="${ctx}/zxmall/center/Index.do">会员中心</a> |  
	      <c:if test="${not empty zxmall  }"> <a href="${ctx}/zxmall/login.do?method=logout">退出登录</a></c:if>    
	      </c:if>
        <c:if test="${empty zxmall}">[&nbsp;<a id="top_login" href="${ctx}/zxmall/login.do">请登录</a>&nbsp;][&nbsp;<a id="top_login_2" href="${ctx}/zxmall/RegisterNew.do">免费注册</a>&nbsp;]</c:if>      
        | <a href="<c:url value='/zxmall/KonkaGroupPeArticleInfo.do?' />method=view&id=999999">常见问题</a> | <a href="<c:url value='/zxmall/KonkaGroupPeArticleInfo.do?' />method=view&id=888888">客户服务</a>
		</div>
	 </div>
</div> 