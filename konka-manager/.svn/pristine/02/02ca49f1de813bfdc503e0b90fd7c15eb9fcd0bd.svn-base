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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
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
    <div class="position"><a href="${ctx }/member/Index.do">首页</a> &gt; <a href="${ctx }/member/center/Index.do">会员中心</a> &gt; 积分充值</div>
    <div class="membertab3">
      <p style="margin-top:15px;font-size:14px;">积分充值订单</p>
      <html-el:form action="/center/EcGiftJfBuy">
        <html-el:hidden property="method" styleId="method" value="list" />
        <table width="100%" border="0" cellspacing="0" cellpadding="0"  class="member_form_table0">
          <td width="40%"><html-el:text styleClass="input_txt" property="add_date_start" size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="width:120px;cursor:pointer;text-align:center;" title="点击选择日期" styleId="add_date_start" ></html-el:text>
              至
              <html-el:text styleClass="input_txt" property="add_date_end" size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="width:120px;cursor:pointer;text-align:center;" title="点击选择日期" styleId="add_date_end" ></html-el:text>
            </td>
            <td width="80%"><input class="inputbtn" type="submit" name="Submit" value="查询" />
            </td>
          </tr>
        </table>
      </html-el:form>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="member_form_table2">
        <tr class="tr1">
          <td width="8%" nowrap="nowrap" align="center">订单流水号</td>
          <td width="8%" nowrap="nowrap" align="center">积分</td>
          <td width="8%" nowrap="nowrap" align="center">金额</td>
          <td width="8%" nowrap="nowrap" align="center">下单时间</td>
          <td width="15%" nowrap="nowrap" align="center">订单状态</td>
          <td width="15%" nowrap="nowrap" align="center">操作</td>
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr height="40">
              <td align="center" nowrap="nowrap"><c:out value="${cur.trade_index}"/></td>
              <td align="center" nowrap="nowrap"><c:out value="${cur.integral}"/></td> 
              <td align="center" nowrap="nowrap"><c:out value="￥${cur.price}" /></td>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd hh:mm:ss" /></td>
              <td align="center" nowrap="nowrap"><c:if test="${cur.state eq 0 }">等待付款</c:if>
                <c:if test="${cur.state eq 1 }">已支付
		                （支付方式 ：  <c:if test="${cur.pay_type eq 2 }">支付宝</c:if><c:if test="${cur.pay_type eq 5 }">民生e支付</c:if>
		                支付单号： ${cur.trade_no }）
                </c:if>
                <c:if test="${cur.state eq 2 }">已取消</c:if> 
              </td>
               <td width="8%" nowrap="nowrap" align="center">
               <c:if test="${cur.state eq 0 }">[<a href="${ctx }/member/EcGiftJfPay.do?trade_index=${cur.trade_index}" target="_blank"><font color="#ff5200">去支付</font></a>] [<a href="#" id="t${cur.id }"   onclick="deleteInfo(this);" >取消订单</a>]</c:if>
               <c:if test="${cur.state ne 0 }"><font color="#cccccc">[去支付] [取消订单]</font></c:if>
               </td>
          </c:forEach>
          <c:if test="${empty entityList }">
            <tr height="60">
              <td align="center" nowrap="nowrap" colspan="6"> 暂无记录 </td>
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
			                     pager.addHiddenInputs("add_date_start", "<c:out value='${af.map.add_date_start}'/>"); 	
			                     pager.addHiddenInputs("add_date_end", "<c:out value='${af.map.add_date_end}'/>"); 	
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
<jsp:include page="/member/__inc/footer.jsp" flush="true" />
<!-- six footer -->
<script type="text/javascript">//<![CDATA[ 
                                          
function deleteInfo(obj){
	//alert($(obj).data("mp")); 
	var	id ="&id="+ obj.id.replace("t",""); 
	if(confirm("您确定取消订单吗？")){
		location.href="${ctx}/member/center/EcGiftJfBuy.do?method=delete"+id; 
	}
}
//]]></script>
</body>
</html>
