<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>康佳</title>
<link rel="stylesheet" href="${ctx}/styles/zxmall/2015/css/base.css"/>
<link rel="stylesheet" href="${ctx}/styles/zxmall/2015/css/index.css"/> 

<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/purchase.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/citybox.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/cloud-zoom/styles/image_show.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/zxmall.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
</head>
<body id="web_body">
<jsp:include page="/zxmall/__inc/2015/top.jsp" flush="true" />  
<jsp:include page="/zxmall/__inc/2015/nav.jsp" flush="true" />  
<div class="main" style="padding-top:0px;">
<div class="w1200">
<!-- second start -->
<div class="maincont">
  <jsp:include page="/zxmall/__inc/_mleft.jsp" flush="true" />
  <!--right-->
  <div class="zxmall_right padbot45">
    <div class="position"><a href="${ctx }/zxmall/Index.do">首页</a> &gt; <a href="${ctx }/zxmall/center/Index.do">会员中心</a> &gt; 完善用户资料</div>
   <c:if test="${zxmall.is_act ne 0 or zxmall.login_count <20 }">
        <div class="zxmalltab2">
        <a href="http://epp.konka.com/zxmall/KonkaGroupPeArticleInfo.do?method=view&id=755139" target="_blank"><img src="${ctx}/styles/zxmall/images/ec_hygz.jpg" /></a>
        <a href="http://epp.konka.com/zxmall/KonkaGroupPeArticleInfo.do?method=view&id=753541" target="_blank"><img src="${ctx}/styles/zxmall/images/ec_hyqy.jpg" /></a>
        <a href="http://epp.konka.com/zxmall/KonkaGroupPeArticleInfo.do?method=view&id=755186" target="_blank"><img src="${ctx}/styles/zxmall/images/ec_jfgz.jpg" /></a>
        <a href="http://epp.konka.com/zxmall/KonkaGroupPeArticleInfo.do?method=view&id=755187" target="_blank"><img src="${ctx}/styles/zxmall/images/ec_yjgz.jpg" /></a>
        </div></c:if>
    <div class="zxmalltab3" id="div_info">
     <p style="margin-left:20px;margin-top:15px;font-size:16px;line-height:36px;"> 
     	${zxmall.real_name }您好，
     	<c:if test="${is_act eq 0 }"> 您的资料已经填写完成，审核通过,欢迎 <a href="${ctx }/zxmall/Index.do">购物</a>,建议您先进入会员中心 查看您的会员权益!</c:if>
     	<c:if test="${is_act eq 1 }"> 您的资料没有填写完整 ，请完善 >> <a href="${ctx }/zxmall/center/RegUser.do?">用户资料</a> </c:if>
     	<c:if test="${is_act eq 2 }"> 您的资料已经填写完成，等待审核。。。审核结果稍后将发送到您登记的邮箱<br/></c:if>
     	<c:if test="${is_act eq 3 }"> 您的资料审核不通过，请重新完善 >> <a href="${ctx }/zxmall/center/RegUser.do?method=edit">用户资料</a> </c:if>
     </p>
    </div>
  </div>
</div>
<jsp:include page="/zxmall/__inc/2015/footer.jsp" flush="true" />
<!-- six footer -->
</body>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript">//<![CDATA[
 
//]]></script>
</html>
