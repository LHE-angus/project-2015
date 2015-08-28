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
<!-- second start -->
<div class="maincont">
  <jsp:include page="/zxmall/__inc/_mleft.jsp" flush="true" />
  <!--right-->
  <div class="zxmall_right padbot45">
    <div class="position"><a href="${ctx }/zxmall/Index.do">首页</a> &gt; <a href="${ctx }/zxmall/center/Index.do">会员中心</a> &gt; 我的订单</div>
    <div class="zxmalltab3">
      <html-el:form action="/center/Orders">
        <html-el:hidden property="method" styleId="method" value="list" />
        <html-el:hidden property="orderState" />
        <table width="100%" border="0" cellspacing="0" cellpadding="0"  class="zxmall_form_table0">
          <tr>
            <td width="30%"><html-el:select property="ts" onchange="this.form.submit();" styleClass="input_txt"  style="width:120px;">
                <html-el:option value="0">全部订单</html-el:option>
                <html-el:option value="1">最近三个月订单</html-el:option>
                <html-el:option value="2">三个月以前订单</html-el:option>
              </html-el:select>
            </td>
            <td width="30%">订单编号
              <html-el:text property="trade_index" maxlength="20" styleClass="input_txt" style="width:160px;"></html-el:text>
            </td>
            <td width="40%"><input class="inputbtn" type="submit" name="Submit" value="查询" /></td>
          </tr>
        </table>
        <ul class="zxmalltit3">
          <li 
          <c:if test="${af.map.orderState eq '1' }">class="curli"</c:if> ><a href="Orders.do?method=list&orderState=1&ts=<c:out value='${af.map.ts}'/>">进行中</a>
          </li>
          <c:if test="${touch eq 1 }">
          <li 
          <c:if test="${af.map.orderState eq '2' }">class="curli"</c:if> ><a href="Orders.do?method=list&orderState=2&ts=<c:out value='${af.map.ts}'/>">已完成</a>
          </li>
          <li 
          <c:if test="${af.map.orderState eq '3' }">class="curli"</c:if> ><a href="Orders.do?method=list&orderState=3&ts=<c:out value='${af.map.ts}'/>">已取消</a>
          </li>
          </c:if>
        </ul>
      </html-el:form>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="zxmall_form_table2">
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
              <td align="center" nowrap="nowrap"><a href="Orders.do?method=view&id=${cur.id}"><c:out value="${cur.trade_index}"/></a></td>
              <td align="center" nowrap="nowrap"><c:forEach items="${cur.pshowOrdeDetailsList}" var="cur2">
                  <c:set value="${fn:split(cur2.map.main_pic, ',') }" var="imgs" />
                  <a href="${ctx }/zxmall/PdShow.do?goods_id=${cur2.pd_id}"><c:forEach items="${imgs }" var="img1" begin="0" end="0"><img src="${ctx }/${img1}" alt="${cur2.map.pd_name }" width="51" height="50"/></c:forEach></a>
                   <c:if test="${cur.state eq 60 and cur2.map.is_eavl eq 0}"><a href ="EcPdEavl.do?method=add&goods_id=${cur2.pd_id}&detail_id=${cur2.bill_item_id}"><font color="#0022ff">商品评价</font></a></c:if> <br/>
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
              <td align="center" nowrap="nowrap"><a href="Orders.do?method=view&id=${cur.id}"><c:if test="${cur.state eq -30 }">退货成功</c:if>
                <c:if test="${cur.state eq -20 }">处理失败</c:if>
                <c:if test="${cur.state eq -10 }">已取消</c:if>
                <c:if test="${cur.state eq 0 }"><c:if test="${cur.pay_way eq 0 or cur.pay_way eq 1}">待审核  </c:if> <c:if test="${cur.pay_way ne 0 and cur.pay_way ne 1 }">待付款  </c:if></c:if>
                <c:if test="${cur.state eq 5 }">待确认</c:if>
                <c:if test="${cur.state eq 10 }">已确认待处理</c:if>
                <c:if test="${cur.state eq 20 }">订单处理中</c:if>
                <c:if test="${cur.state eq 30 }">订单处理中</c:if>
                <c:if test="${cur.state eq 40 }">已发货</c:if>
                <c:if test="${cur.state eq 50 }">已换货</c:if>
                <c:if test="${cur.state eq 60 }">交易完成</c:if></a>
              </td>
              <td align="center" nowrap="nowrap" style="line-height:23px;">
              	<c:if test="${cur.state eq -30 }"><a href="Orders.do?method=view&id=${cur.id}"><font color="#0022ff">订单详情</font></a></c:if>
                <c:if test="${cur.state eq -20 }"><a href="Orders.do?method=view&id=${cur.id}"><font color="#0022ff">订单详情</font></a></c:if>
                <c:if test="${cur.state eq -10 }"><a href="Orders.do?method=view&id=${cur.id}"><font color="#0022ff">订单详情</font></a></c:if>
                <c:if test="${cur.state eq 0 }"><a href="Orders.do?method=view&id=${cur.id}"><font color="#0022ff">订单详情</font></a><br/>
                <c:if test="${cur.pay_way ne 0 and cur.pay_way ne 1}"> <a class="btn btn-4" href="<c:url value='/zxmall/Payment.do?trade_index=${cur.trade_index}' />"><s></s>付款</a><br/></c:if>
                 <a href="#" id="t${cur.id }" onclick="deleteInfo(this);" ><font color="#0022ff">取消订单</font></a></c:if>
          		<c:if test="${cur.state eq 5 }"><a href="Orders.do?method=view&id=${cur.id}"><font color="#0022ff">订单详情</font></a> </c:if>
                <c:if test="${cur.state eq 10 }"><a href="Orders.do?method=view&id=${cur.id}"><font color="#0022ff">订单详情</font></a> </c:if>
                <c:if test="${cur.state eq 20 }"><a href="Orders.do?method=view&id=${cur.id}"><font color="#0022ff">订单详情</font></a> </c:if>
                <c:if test="${cur.state eq 30 }"><a href="Orders.do?method=view&id=${cur.id}"><font color="#0022ff">订单详情</font></a></c:if>
                <c:if test="${cur.state eq 40 }"><a href="Orders.do?method=view&id=${cur.id}"><font color="#0022ff">订单详情</font></a></c:if>
                <c:if test="${cur.state eq 50 }"><a href="Orders.do?method=view&id=${cur.id}"><font color="#0022ff">订单详情</font></a></c:if>
                <c:if test="${cur.state eq 60 }"><a href="Orders.do?method=view&id=${cur.id}"><font color="#0022ff">订单详情</font></a><br />
                <a class="btn btn-4" href="#" onclick="getState('${cur.id}');"><s></s>物流查询</a> 
                 </c:if> 
              </td>
            </tr>
          </c:forEach>
          <c:if test="${empty entityList }">
            <tr height="60">
              <td align="center" nowrap="nowrap" colspan="10"> 暂无订单 </td>
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


</div> 
</div>
<jsp:include page="/zxmall/__inc/2015/footer.jsp" flush="true" />
<!-- six footer -->
</body>
<script type="text/javascript">//<![CDATA[ 
                                          
function deleteInfo(obj){
	var	id ="&id="+ obj.id.replace("t",""); 
	if(confirm("您确定取消订单吗？")){
		location.href="${ctx}/zxmall/center/Orders.do?method=delete"+id; 
	}
}

function getState(id){
	var returnValue = window.showModalDialog("Orders.do?method=sfList2&id="+id+"&azaz=" + Math.random(),window,"dialogWidth:700px;status:no;dialogHeight:300px");
	window.parent.resizeFrameHeight('mainFrame', 3);
}
 
//]]></script>
</html>
