<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:if test="${ecUser.user_type eq 1 }">康佳EPP内部优惠购机平台</c:if><c:if test="${ecUser.user_type eq 2 }">康佳网上直销商城</c:if></title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/touch/css/global1.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/touch/css/epp.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/touch/css/touch.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<!-- head start -->
<div class="topbox">
<jsp:include page="/touch/__inc/top.jsp" flush="true" />
<jsp:include page="/touch/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/touch/__inc/_nav3.jsp" flush="true" />
<!-- second start -->
<div class="maincont">
  <jsp:include page="/touch/__inc/_mleft.jsp" flush="true" />
  <!--right-->
  <div class="touch_right padbot45">
    <div class="position"><a href="${ctx }/touch/Index.do">首页</a> &gt; <a href="${ctx }/touch/center/Index.do">会员中心</a> &gt; 完善用户资料</div>
   <c:if test="${ecUser.is_act ne 0 or ecUser.login_count <20 }">
        <div class="touchtab2">
        <a href="http://epp.konka.com/touch/KonkaGroupPeArticleInfo.do?method=view&id=755139" target="_blank"><img src="${ctx}/styles/touch/images/ec_hygz.jpg" /></a>
        <a href="http://epp.konka.com/touch/KonkaGroupPeArticleInfo.do?method=view&id=753541" target="_blank"><img src="${ctx}/styles/touch/images/ec_hyqy.jpg" /></a>
        <a href="http://epp.konka.com/touch/KonkaGroupPeArticleInfo.do?method=view&id=755186" target="_blank"><img src="${ctx}/styles/touch/images/ec_jfgz.jpg" /></a>
        <a href="http://epp.konka.com/touch/KonkaGroupPeArticleInfo.do?method=view&id=755187" target="_blank"><img src="${ctx}/styles/touch/images/ec_yjgz.jpg" /></a>
        </div></c:if>
    <div class="touchtab3" id="div_info">
     <p style="margin-left:20px;margin-top:15px;font-size:16px;line-height:36px;"> 
     	${ecUser.real_name }您好，
     	<c:if test="${is_act eq 0 }"> 您的资料已经填写完成，审核通过,欢迎 <a href="${ctx }/touch/Index.do">购物</a>,建议您先进入会员中心 查看您的会员权益!</c:if>
     	<c:if test="${is_act eq 1 }"> 您的资料没有填写完整 ，请完善 >> <a href="${ctx }/touch/center/RegUser.do?">用户资料</a> </c:if>
     	<c:if test="${is_act eq 2 }"> 您的资料已经填写完成，等待审核。。。审核结果稍后将发送到您登记的邮箱<br/></c:if>
     	<c:if test="${is_act eq 3 }"> 您的资料审核不通过，请重新完善 >> <a href="${ctx }/touch/center/RegUser.do?method=edit">用户资料</a> </c:if>
     </p>
    </div>
  </div>
</div>
<jsp:include page="/touch/__inc/footer.jsp" flush="true" />
<!-- six footer -->
</body>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript">//<![CDATA[
 
//]]></script>
</html>
