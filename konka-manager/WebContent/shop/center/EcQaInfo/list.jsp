<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:if test="${ecUser.user_type eq 1 }">康佳EPP内部优惠购机平台</c:if><c:if test="${ecUser.user_type eq 2 }">康佳粉丝会</c:if></title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/shop/css/global1.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/shop/css/epp.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/shop/css/shop.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
</head>
<body>
<!-- head start -->
<div class="topbox">
<jsp:include page="/shop/__inc/top.jsp" flush="true" />
<jsp:include page="/shop/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/shop/__inc/nav.jsp" flush="true" />
<!-- second start -->
<div class="maincont">
  <jsp:include page="/shop/__inc/_mleft.jsp" flush="true" />
  <!--right-->
  <div class="shop_right padbot45">
    <div class="position"><a href="${ctx }/shop/Index.do">首页</a> &gt; <a href="${ctx }/shop/center/Index.do">会员中心</a> &gt;咨询信息</div>
    <div class="shoptab3">
      <p style="margin-top:15px;font-size:14px;"> <input class="inputbtn" type="button" name="button4" id="button4" value="咨询" onclick="location.href='EcQaInfo.do?method=add&qa_type_code=${af.map.qa_type_code}';"/></p>
      <ul class="shoptit3">
        <li 
        <c:if test="${af.map.qa_type_code eq 0 }">class="curli"</c:if> ><a href="EcQaInfo.do?method=list&qa_type_code=0">咨询</a>
        </li>
        <li 
        <c:if test="${af.map.qa_type_code eq 1 }">class="curli"</c:if> ><a href="EcQaInfo.do?method=list&qa_type_code=1">投诉</a>
        </li>
        <li 
        <c:if test="${af.map.qa_type_code eq 2 }">class="curli"</c:if> ><a href="EcQaInfo.do?method=list&qa_type_code=2">建议</a>
        </li>
      </ul>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="shop_form_table2">
       
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr height="100">
              <td align="left" style="line-height:22px;"><p style="margin-top:2px;font-size:12px;">
                  	我的咨询：<c:out value="${cur.q_title }"/>
                  &nbsp;&nbsp;&nbsp;
                  <fmt:formatDate value="${cur.q_date}" pattern="yyyy-MM-dd hh:mm:ss" />
                  <br/>
                  &nbsp;&nbsp;
                  <c:out value="${cur.q_content }"/>
                </p>
                <p style="margin-top:2px;font-size:12px;">
                <c:if test="${not empty cur.a_content}">
                  <font color="#f60">回&nbsp;&nbsp;复：</font>
                  <c:out value="${cur.a_content }"/>
                  <br/>
                  <font color="#f60">回复人：</font>
                  <c:out value="${cur.a_name}"/>
                  &nbsp;&nbsp;&nbsp;
                  <fmt:formatDate value="${cur.a_date}" pattern="yyyy-MM-dd hh:mm:ss" /></c:if>
                  <c:if test="${empty cur.a_content}"> <font color="#f60">未回复</font></c:if>
                </p></td>
            </tr>
          </c:forEach>
          <c:if test="${empty entityList }">
            <tr height="60">
              <td align="center" nowrap="nowrap" colspan="5"> 暂无信息 </td>
            </tr>
          </c:if>
        </tbody>
      </table>
      <c:if test="${af.map.pager.pageCount>1}">
        <form id="bottomPageForm" name="bottomPageForm" method="post" action="?">
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td height="40"><div style="text-align: right; padding-right: 5px;">
                  <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
                  <script type="text/javascript">
			                     var pager = new Pager(document.bottomPageForm, parseInt('${af.map.pager.recordCount}'), parseInt('${af.map.pager.pageSize}'), parseInt('${af.map.pager.currentPage}'));
			                     pager.addHiddenInputs("method", "list");
			                     pager.addHiddenInputs("qa_type_code", "<c:out value='${af.map.qa_type_code}'/>"); 	
			                     document.write(pager.toString());
			                 </script>
                </div></td>
            </tr>
          </table>
        </form>
      </c:if>
    </div>
  </div>
  <div class="clear"></div>
</div>
<jsp:include page="/shop/__inc/footer.jsp" flush="true" />
<!-- six footer -->
</body>
</html>
