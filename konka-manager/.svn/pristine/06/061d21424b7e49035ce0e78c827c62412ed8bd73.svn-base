<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康佳网上直销商城</title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/global1.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/epp.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/zxmall.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<!-- head start -->
<div class="topbox">
<jsp:include page="/zxmall/__inc/top.jsp" flush="true" />
<jsp:include page="/zxmall/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/zxmall/__inc/nav.jsp" flush="true" />
<!-- topnav end -->
<!-- second start -->
<div class="maincont">
  <jsp:include page="/zxmall/__inc/_mleft.jsp" flush="true" />
  <!--right-->
  <div class="zxmall_right padbot45">
    <div class="position"><a href="${ctx }/zxmall/Index.do">首页</a> &gt; <a href="${ctx }/zxmall/center/Index.do">会员中心</a> &gt; 我的积分</div>
    <div class="zxmalltab3">
      <p style="margin-top:15px;font-size:14px;"> 您当前的 可兑换积分:<span style="color:#FF2200;font-weight:bold;">${totalScore }</span>，
      	已兑换积分:<span style="color:#FF2200;font-weight:bold;">${xfScore }</span>，
       	其中付款积分<span style="color:#FF2200;font-weight:bold;">${payTotalScore }</span> ，
   	   	 奖励积分<span style="color:#FF2200;font-weight:bold;">${jlScore }</span></p>
      <p style="margin-top:15px;font-size:14px;">积分消费 、佣金兑换积分记录</p>
      <html-el:form action="/center/EcUserScoreRec">
        <html-el:hidden property="method" styleId="method" value="list" />
      </html-el:form>
      <ul class="zxmalltit3">
        <li 
        <c:if test="${af.map.ts ne 'before3months' }">class="curli"</c:if>
        ><a href="EcUserScoreRec.do?method=list&ts=last3months">最近三个月记录</a>
        </li>
        <li 
        <c:if test="${af.map.ts eq 'before3months' }">class="curli"</c:if>
        ><a href="EcUserScoreRec.do?method=list&ts=before3months">三个月前记录</a>
        </li>
      </ul>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="zxmall_form_table1">
        <tr class="tabtt1">
          <td width="30%" nowrap="nowrap" align="center">时间</td>
          <td width="20%" nowrap="nowrap" align="center">获得积分</td>
          <td width="20%" nowrap="nowrap" align="center">消费积分</td>
          <td width="30%" nowrap="nowrap" align="center">详细说明</td>
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.opr_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
              <td align="center" nowrap="nowrap"><c:if test="${cur.opr_type eq 0}"> ${cur.score }</c:if>
                <c:if test="${cur.opr_type eq 1}">-</c:if>
              </td>
              <td align="center" nowrap="nowrap"><c:if test="${cur.opr_type eq 1}"> ${cur.score }</c:if>
                <c:if test="${cur.opr_type eq 0}">-</c:if>
              </td>
              <td align="left" nowrap="nowrap"><c:out value="${cur.note }"/></td>
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
			                     pager.addHiddenInputs("ts", "<c:out value='${af.map.ts}'/>"); 	 	
			                     document.write(pager.toString());
			                 </script>
                </div></td>
            </tr>
          </table>
        </form>
      </c:if>
      
      <p style="margin-top:15px;font-size:14px;">购物获得积分记录</p>
       <table width="100%" border="0" cellspacing="0" cellpadding="0" class="zxmall_form_table1">
        <tr class="tabtt1">
          <td width="20%" nowrap="nowrap" align="center">时间</td> 
          <td width="20%" nowrap="nowrap" align="center">商品名称</td>
          <td width="20%" nowrap="nowrap" align="center">付款积分</td>
          <td width="20%" nowrap="nowrap" align="center">奖励积分</td>
          <td width="20%" nowrap="nowrap" align="center">合计积分</td>
        </tr>
        <tbody>
          <c:forEach var="cur" items="${orderDetailList}" varStatus="vs">
           <tr>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.map.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
              <td align="center" nowrap="nowrap"> ${fnx:abbreviate(cur.pd_name, 2 * 16, '')} </td>
              <td align="center" nowrap="nowrap" class="s1_${vs.count}"> <fmt:formatNumber value="${cur.map.pay_price}" pattern="#0.00" /> </td>
              <td align="center" nowrap="nowrap" class="s2_${vs.count}"><fmt:formatNumber value="${cur.map.jl_jf}" pattern="#0.00" /></td>
              <td align="center" nowrap="nowrap" class="s3_${vs.count}"><fmt:formatNumber value="${cur.map.pay_price+cur.map.jl_jf}" pattern="#0.00" /></td>
            </tr>
          </c:forEach>
          <tr class="tabtt1">
          <td colspan="2" width="80%" nowrap="nowrap" align="center">合计</td>
          <td width="20%" nowrap="nowrap" align="center" id="t1"></td>
          <td width="20%" nowrap="nowrap" align="center" id="t2"></td>
          <td width="20%" nowrap="nowrap" align="center" id="t3"></td>
          </tr>
          </tbody>
        </table>
          <p style="margin-top:10px;font-size:14px; margin-right:30px;text-align:right;"> <a href="EcUserGwjf.do">更多</a></p>
    </div>
  </div>
  <div class="clear"></div>
</div>
<jsp:include page="/zxmall/__inc/footer.jsp" flush="true" />
<!-- six footer -->
</body>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	var len = "${fn:length(orderDetailList)}";

	var last = 0;
	for (var i = 1; i < len+1; i++) {
		$(".s1_" + i).each(function(){
			var tt = $.trim($(this).text());
			if("" == tt||null == tt){
				tt = 0;
			}
			last += parseFloat(tt);
		});
	}
	$("#t1").text(last.toFixed(2));

	var last2 = 0;
	for (var i = 1; i < len+1; i++) {
		$(".s2_" + i).each(function(){
			var tt = $.trim($(this).text());
			if("" == tt||null == tt){
				tt = 0;
			}
			last2 += parseFloat(tt);
		});
	}
	$("#t2").text(last2.toFixed(2));

	$("#t3").text(parseFloat($("#t1").text())+parseFloat($("#t2").text())); 
	
    
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
