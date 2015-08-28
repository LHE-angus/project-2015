<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:if test="${ecUser.user_type eq 1 }">康佳EPP内部优惠购机平台</c:if><c:if test="${ecUser.user_type eq 2 }">康佳网上直销商城</c:if></title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/global1.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/epp.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/member.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<!-- head start -->
<div class="topbox">
<jsp:include page="/member/__inc/top.jsp" flush="true" />
<jsp:include page="/member/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/member/__inc/nav.jsp" flush="true" />
<!-- second start -->
<div class="maincont">
  <jsp:include page="/member/__inc/_mleft.jsp" flush="true" />
  <!--right-->
  <div class="member_right padbot45">
    <div class="position"><a href="${ctx }/member/Index.do">首页</a> &gt; <a href="${ctx }/member/center/Index.do">会员中心</a> &gt; 我的团购</div>
    <div class="membertab3">
      <html-el:form action="/center/EcVouchers" >
        <html-el:hidden property="method" styleId="method" value="save" />
        <html-el:hidden property="id" value="${af.map.id}"/>
        <%@ include file="/commons/pages/messages.jsp" %>
        <p style="margin-left:20px;margin-top:15px;font-size:16px;"> 团购券使用情况</p>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="member_form_table1">  
          <tr>
            <td align="right" width="20%">团购标题：</td>
            <td align="left" width="80%">
              ${af.map.map.title}
            </td>
          </tr>
          <tr>
            <td align="right" width="20%">金额(元)：</td>
            <td align="left" width="80%">
              <fmt:formatNumber type="number" value="${af.map.price}" pattern="#0.00"/>
            </td>
          </tr>
          <tr>
            <td align="right" width="20%">数量：</td>
            <td align="left" width="80%">1
            </td>
          </tr>
          <c:if test="${af.map.is_del eq 0}">
          <tr>
            <td align="right" width="20%">使用时间：</td>
            <td align="left" width="80%">
              <fmt:formatDate  value="${af.map.used_date}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
          </tr>
          </c:if>
          <c:if test="${af.map.is_del eq 1}">   
          <tr>
            <td align="right" width="20%">作废时间：</td>
            <td align="left" width="80%">
              <fmt:formatDate  value="${af.map.del_date}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
          </tr>
          </c:if>
         </table>  
         <p style="margin-left:200px;margin-top:15px;font-size:16px;"> 
         <html-el:button property="" value="返 回" styleClass="inputbtn" styleId="btn_back" onclick="history.back();return false;" />
         </p>            
      </html-el:form>
    </div>
  </div>
  <div class="clear"></div>
</div>
<jsp:include page="/member/__inc/footer.jsp" flush="true" />
<!-- six footer -->
</body> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript">//<![CDATA[


//]]></script>
</html>
