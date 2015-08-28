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
</head>
<body id="web_body">
<jsp:include page="/zxmall/__inc/2015/top.jsp" flush="true" />  
<jsp:include page="/zxmall/__inc/2015/nav.jsp" flush="true" />  
<div class="main" style="padding-top:0px;">
<div class="w1200">
<!-- topnav end -->
<!-- second start -->
<div class="maincont">
  <jsp:include page="/zxmall/__inc/_mleft.jsp" flush="true" />
  <!--right-->
  <div class="zxmall_right padbot45">
    <div class="position"><a href="${ctx }/zxmall/Index.do">首页</a> &gt; <a href="${ctx }/zxmall/center/Index.do">会员中心</a> &gt; <a href="${ctx }/zxmall/center/EcUserScoreRec.do">我的积分</a></div>
    <div class="zxmalltab3"> 
      <p style="margin-top:15px;font-size:14px;">购物获得积分记录 </p>
       <table width="100%" border="0" cellspacing="0" cellpadding="0" class="zxmall_form_table1">
        <tr class="tabtt1">
          <td width="30%" nowrap="nowrap" align="center">时间</td> 
          <td width="30%" nowrap="nowrap" align="center">商品名称</td>
          <td width="20%" nowrap="nowrap" align="center">付款积分</td>
          <td width="20%" nowrap="nowrap" align="center">奖励积分</td>
        </tr>
        <tbody>
          <c:forEach var="cur" items="${orderDetailList}" varStatus="vs">
           <tr>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.map.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
              <td align="center" nowrap="nowrap"> 
              ${fnx:abbreviate(cur.pd_name, 2 * 16, '')}
              </td>
              <td align="center" nowrap="nowrap"> <fmt:formatNumber value="${cur.pay_integral}" pattern="#0.00" /> </td>
              <td align="left" nowrap="nowrap"><fmt:formatNumber value="${cur.integral}" pattern="#0.00" /></td>
            </tr>
          </c:forEach>
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
</div> 
</div>
<jsp:include page="/zxmall/__inc/2015/footer.jsp" flush="true" /> 
<!-- six footer -->
</body>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
    
		$("#btn_submit").click(function(){ 
			var isSubmit = Validator.Validate(this.form,3);
			if (isSubmit) {
				 $("#btn_submit").attr("value", "正在查询...").attr("disabled", "true");
				 this.form.submit();
			}
		});
		
});

 
//]]></script>
</html>
