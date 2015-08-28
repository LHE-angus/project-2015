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
    <div class="position"><a href="${ctx }/shop/Index.do">首页</a> &gt; 我的订单</div>
    <div class="shoptab3">
      <html-el:form action="/center/Orders">
        <html-el:hidden property="method" styleId="method" value="list" />
        <html-el:hidden property="orderState" />
        <table width="100%" border="0" cellspacing="0" cellpadding="0"  class="shop_form_table0">
          <tr>
            <td width="30%"> 订单编号
              <html-el:text property="trade_index" maxlength="20" styleClass="input_txt" style="width:160px;"></html-el:text>
            </td>
            <td width="40%"><input class="inputbtn" type="submit" name="Submit" value="查询" /></td>
          </tr>
        </table> 
      </html-el:form>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="shop_form_table2">
        <tr class="tr1">
          <td width="8%" nowrap="nowrap"  align="center">订单编号</td>
          <td width="8%" nowrap="nowrap" align="center">订单商品</td>
          <td width="8%" nowrap="nowrap" align="center">收货人</td>
          <td width="8%" nowrap="nowrap" align="center">订单金额</td>
          <td width="8%" nowrap="nowrap" align="center">订单优惠抵扣金额</td> 
          <td width="8%" nowrap="nowrap" align="center">应付金额</td>
          <td width="8%" nowrap="nowrap" align="center">下单时间</td>
          <td width="8%" nowrap="nowrap" align="center">支付类型</td>
          <td width="8%" nowrap="nowrap" align="center">订单状态</td>
          <td width="8%" nowrap="nowrap" align="center">操作</td>
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr height="60">
              <td align="center" nowrap="nowrap"><a href="Orders.do?method=view&trade_index=<c:out value="${cur.trade_index}"/>"><c:out value="${cur.trade_index}"/></a></td>
              <td align="center" nowrap="nowrap"><c:forEach items="${cur.pshowOrdeDetailsList}" var="cur2">
                  <c:set value="${fn:split(cur2.map.main_pic, ',') }" var="imgs" />
                  <a href="${ctx }/shop/PdShow.do?goods_id=${cur2.pd_id}"><c:forEach items="${imgs }" var="img1" begin="0" end="0"><img src="${ctx }/${img1}" alt="${cur2.map.pd_name }" width="51" height="50"/></c:forEach></a>
                  <br/>
                </c:forEach>
              </td>
              <td align="center" nowrap="nowrap"><c:out value="${cur.buyer_name}" /></td>
              <td align="center" nowrap="nowrap"><c:out value="￥${cur.total_price}" /></td>
              <td align="center" nowrap="nowrap"><c:out value="￥${cur.dedu_price}" /></td>
              <td align="center" nowrap="nowrap"><c:out value="￥${cur.pay_price}" /></td>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss" />
              </td>
              <td align="center" nowrap="nowrap"><c:if test="${cur.pay_way eq 0}">货到付款</c:if>
                <c:if test="${cur.pay_way eq 1}">银行汇款</c:if>
                <c:if test="${cur.pay_way eq 2}">支付宝</c:if>
                <c:if test="${cur.pay_way eq 3}">银联</c:if>
                <c:if test="${cur.pay_way eq 5}">民生e支付</c:if>
              </td>
              <td align="center" nowrap="nowrap"><a href="Orders.do?method=view&trade_index=<c:out value="${cur.trade_index}"/>"><c:if test="${cur.state eq -30 }">退货成功</c:if>
                <c:if test="${cur.state eq -20 }">处理失败</c:if>
                <c:if test="${cur.state eq -10 }">已取消</c:if>
                <c:if test="${cur.state eq 0 }"><c:if test="${cur.pay_way eq 0 or cur.pay_way eq 1}">待审核  </c:if> <c:if test="${cur.pay_way ne 0 and cur.pay_way ne 1 }">待付款  </c:if></c:if>
                <c:if test="${cur.state eq 10 }">已确认待处理</c:if>
                <c:if test="${cur.state eq 20 }">订单处理中</c:if>
                <c:if test="${cur.state eq 30 }">订单处理中</c:if>
                <c:if test="${cur.state eq 40 }">已发货</c:if>
                <c:if test="${cur.state eq 50 }">已换货</c:if>
                <c:if test="${cur.state eq 60 }">交易完成</c:if></a>
              </td>
              <td align="center" nowrap="nowrap" style="line-height:23px;">
              	<c:if test="${cur.state eq -30 }"><a href="Orders.do?method=view&trade_index=<c:out value="${cur.trade_index}"/>"><font color="#0022ff">订单详情</font></a></c:if>
                <c:if test="${cur.state eq -20 }"><a href="Orders.do?method=view&trade_index=<c:out value="${cur.trade_index}"/>"><font color="#0022ff">订单详情</font></a></c:if>
                <c:if test="${cur.state eq -10 }"><a href="Orders.do?method=view&d}&trade_index=<c:out value="${cur.trade_index}"/>"><font color="#0022ff">订单详情</font></a></c:if>
                <c:if test="${cur.state eq 0 }"><a href="Orders.do?method=view&trade_index=<c:out value="${cur.trade_index}"/>"><font color="#0022ff">订单详情</font></a><br/>
                <c:if test="${cur.pay_way ne 0 and cur.pay_way ne 1}"> <a class="btn btn-4" href="<c:url value='/shop/Payment.do?trade_index=${cur.trade_index}' />"><s></s>付款</a><br/></c:if>
                 <a href="#" id="t<c:out value="${cur.trade_index}"/>" onclick="deleteInfo(this);" ><font color="#0022ff">取消订单</font></a></c:if>
                <c:if test="${cur.state eq 10 }"><a href="Orders.do?method=view&trade_index=<c:out value="${cur.trade_index}"/>"><font color="#0022ff">订单详情</font></a> </c:if>
                <c:if test="${cur.state eq 20 }"><a href="Orders.do?method=view&trade_index=<c:out value="${cur.trade_index}"/>"><font color="#0022ff">订单详情</font></a> </c:if>
                <c:if test="${cur.state eq 30 }"><a href="Orders.do?method=view&trade_index=<c:out value="${cur.trade_index}"/>"><font color="#0022ff">订单详情</font></a></c:if>
                <c:if test="${cur.state eq 40 }"><a href="Orders.do?method=view&trade_index=<c:out value="${cur.trade_index}"/>"><font color="#0022ff">订单详情</font></a></c:if>
                <c:if test="${cur.state eq 50 }"><a href="Orders.do?method=view&trade_index=<c:out value="${cur.trade_index}"/>"><font color="#0022ff">订单详情</font></a></c:if>
                <c:if test="${cur.state eq 60 }"><a href="Orders.do?method=view&trade_index=<c:out value="${cur.trade_index}"/>"><font color="#0022ff">订单详情</font></a><br />
                <a class="btn btn-4" href="#" onclick="getState('${cur.trade_index}');"><s></s>物流查询</a> 
                 </c:if>
              </td>
            </tr>
          </c:forEach>
          <c:if test="${empty entityList }">
            <tr height="60">
              <td align="center" nowrap="nowrap" colspan="10">请输入您的订单号查询 </td>
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
			                     pager.addHiddenInputs("ts", "<c:out value='${af.map.ts}'/>"); 	
			                     pager.addHiddenInputs("orderState", "<c:out value='${af.map.orderState}'/>"); 	
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
<script type="text/javascript">//<![CDATA[ 
                                          
function deleteInfo(obj){
	var	id ="&trade_index="+ obj.id.replace("t",""); 
	if(confirm("您确定取消订单吗？")){
		location.href="${ctx}/shop/center/Orders.do?method=delete"+id; 
	}
}

function getState(trade_index){
	var returnValue = window.showModalDialog("Orders.do?method=sfList2&trade_index="+trade_index+"&azaz=" + Math.random(),window,"dialogWidth:700px;status:no;dialogHeight:300px");
	window.parent.resizeFrameHeight('mainFrame', 3);
}
 
//]]></script>
</html>
