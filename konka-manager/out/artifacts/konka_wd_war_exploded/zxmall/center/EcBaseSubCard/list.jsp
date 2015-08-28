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
    <div class="position"><a href="${ctx }/zxmall/Index.do">首页</a> &gt; <a href="${ctx }/zxmall/center/Index.do">会员中心</a> &gt; 我的下级会员</div>
    <div class="zxmalltab3">
       <html-el:form action="/center/EcBaseSubCard.do">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="zxmall_form_table0">
          <tr class="tr1">
          <td style="height:25px;" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">用户名：</strong>
            <html-el:text property="user_name_like" styleId="user_name_like" style="width:160px;"  maxlength="20"/>
            &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">电话：</strong>
            <html-el:text property="link_phone_like" styleId="link_phone_like" style="width:160px;" maxlength="20"/>
            &nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td width="30%">  
             <input class="inputbtn" type="submit" name="Submit" value="查询" />
         </td>
         </tr>
      </table>
    </html-el:form>
      <form id="listForm" name="listForm" method="post" action="EcBaseSubCard.do">
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="zxmall_form_table2">
        <tbody>
          <tr class="tr1">
            <td width="5%" nowrap="nowrap" ><font class="blue">序号</font></td>
            <td width="10%" nowrap="nowrap" ><font class="blue">用户名</font></td>
            <td width="10%" nowrap="nowrap" ><font class="blue">真实姓名</font></td>
            <td width="10%" nowrap="nowrap" ><font class="blue">级别</font></td>
           	<td width="10%" nowrap="nowrap" ><font class="blue">上级</font></td>
            <td width="10%" nowrap="nowrap" ><font class="blue">手机/电话</font></td>            
            <td width="10%" nowrap="nowrap" ><font class="blue">注册时间</font></td>
          </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr height="60">  
              <td  nowrap="nowrap"  align="left">${af.map.pager.pageSize*(af.map.pager.currentPage-1)+vs.count}</td>
              <td  nowrap="nowrap" align="left"><c:out value="${cur.user_name}" /></td>
              <td  nowrap="nowrap" align="left"><c:out value="${cur.real_name}" /></td>
              <td  nowrap="nowrap" align="left"><c:out value="${cur.map.leve}" /></td>
              <td  nowrap="nowrap" align="left"><c:out value="${cur.link_user_name}" /></td> 
              <td  nowrap="nowrap" align="left"><c:out value="${cur.link_phone}" /></td>
              <td  nowrap="nowrap" align="left"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
            </tr>
            <c:if test="${vs.last eq true}">
              <c:set var="i" value="${vs.count}" />    
            </c:if>
          </c:forEach>
        </tbody>
      </table>
    </form>
      <c:if test="${af.map.pager.pageCount>1}">
        <form id="bottomPageForm" name="bottomPageForm" method="post" action="EcBaseSubCard.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("user_name_like", "${af.map.user_name_like}");
	            pager.addHiddenInputs("link_phone_like", "${af.map.link_phone_like}");
	            document.write(pager.toString());
	      </script></td>
        </tr>
      </table>
    </form>
      </c:if>
    </div>
  </div>
  <div class="clear"></div>
</div>
</div> 
</div>
<jsp:include page="/zxmall/__inc/2015/footer.jsp" flush="true" />
<!-- six footer -->
</body>
<script type="text/javascript">//<![CDATA[ 
                                          
//]]></script>
</html>
