<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<style type="text/css">
select {
	font-family:Microsoft YAHEI;
	font-size:12px;
}
input {
	font-family:Microsoft YAHEI;
	font-size:12px;
}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oartop">
  <table width="400" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
      <td>当前位置：${naviString}</td>
    </tr>
  </table>
</div>
<div>
  <div class="rtabcont1">
  	<html-el:form action="/pshow/PshowOrderConfirm" method="post">
      <html-el:hidden property="id" styleId="id" value="${af.map.id}" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <html-el:hidden property="state2" value="60" />
      <div class="rtabcont1">
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
          <tr>
          	<td colspan="6"  style="background:#CCCCCC" >订单信息</td>
          </tr>	
          <tr>
            <td class="title_item" width="15%" >交易流水号：</td>
            <td colspan="2"><c:out value="${af.map.trade_index}" /></td>
            <td class="title_item"  width="15%">下单人姓名：</td>
            <td colspan="2"><c:out value="${af.map.order_user_name}" /> </td>
          </tr>
          <tr>
            <td class="title_item"  width="15%">下单人姓名：</td>
            <td colspan="2" ><c:out value="${af.map.order_user_name}" /></td>
            <td class="title_item"  width="15%">购买人姓名：</td>
            <td colspan="2"> <c:out value="${af.map.buyer_name}" /> </td>
          </tr>
          <tr>
            <td  class="title_item" width="15%">购买人手机号码：</td>
            <td colspan="2"><c:out value="${af.map.buyer_mp}" />
            </td>
            <td  class="title_item" width="15%">购买人固定电话：</td>
             <td colspan="2"><c:out value="${af.map.buyer_tel}" />
            </td>
          </tr>
          <tr>
            <td class="title_item" width="15%">支付方式：</td>
            <td colspan="2">
            <c:if test="${af.map.pay_way eq 0}">货到付款</c:if>
            <c:if test="${af.map.pay_way eq 1}">在线支付</c:if>
            <c:if test="${af.map.pay_way eq 2}">支付宝</c:if>
            <c:if test="${af.map.pay_way eq 3}">银联</c:if>
            </td>
            <td class="title_item" width="15%">发货方式：</td>
            <td colspan="2">
            <c:if test="${af.map.deliver_way eq 0}">上门自提</c:if>
             <c:if test="${af.map.deliver_way eq 1}">商家配送</c:if>
            </td>
          </tr>
           <tr>
            <td class="title_item" width="15%">支付单号：</td>
            <td colspan="2"><c:out value="${af.map.trade_no}" />
            </td>
            <td class="title_item" width="15%">收货地址：</td>
             <td colspan="2"><c:out value="${af.map.buyer_addr}" />
            </td>
          </tr>
          <tr>
          	 <td  class="title_item" width="15%">产品总数量：</td>
             <td colspan="2">${t_num }</td>
             <td  class="title_item" width="15%">订单总价格：</td>
             <td colspan="2"><fmt:formatNumber value="${t_price}" pattern="0.00" /></td>
          </tr>
          <tr>
            <td class="title_item" width="15%">所在地：</td>
            <td colspan="5"><c:out value="${p_index_name}" /></td>
          </tr>
        </table>
      </div>
      
      <div align="left" style="margin-top: 30px">
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
          <tr>
          	<td colspan="5"  style="background:#CCCCCC" >商品明细信息</td>
          </tr>	
          <tr>
          <td class="title_item" width="15%" style="text-align: center;">产品型号</td>
		  <td class="title_item" width="8%" style="text-align: center;">数量</td>
          <td class="title_item" width="8%" style="text-align: center;">单价</td>
          <td class="title_item" width="8%" style="text-align: center;">总金额</td>
          <td class="title_item" width="30%" style="text-align: center;">卖家留言</td>
          </tr>
          
          <c:forEach items="${pshowOrdeDetails}" var="cur">
          <tr>
          <td nowrap="nowrap" style="text-align: center;">${cur.map.pd_sn }</td>
          <td width="8%" nowrap="nowrap" style="text-align: center;">${cur.num }</td>
		  <td width="8%" nowrap="nowrap" style="text-align: center;">${cur.price }</td>
          <td width="8%" nowrap="nowrap" style="text-align: center;">${cur.total_price}</td>
          <td width="30%" nowrap="nowrap" style="text-align: center;">${cur.remark }</td>
          </tr>
          </c:forEach>
          <tr>
          <td class="title_item" width="15%" style="text-align: center;" >订单当前状态：</td>
          <td colspan="4">
     		 <c:if test="${af.map.state eq -30 }">已退货</c:if>
             <c:if test="${af.map.state eq -20}">审核未通过</c:if>
	         <c:if test="${af.map.state eq -10 }">已关闭</c:if>
	         <c:if test="${af.map.state eq 0 }">已预订</c:if>
	         <c:if test="${af.map.state eq 10 }">已付款</c:if>
	         <c:if test="${af.map.state eq 20 }">审核通过</c:if>
	         <c:if test="${af.map.state eq 30 }">下发处理</c:if>
	         <c:if test="${af.map.state eq 40 }">商家发货</c:if>
	         <c:if test="${af.map.state eq 50 }">客户已换货</c:if>
	         <c:if test="${af.map.state eq 60 }">确认收货</c:if>     
	
          </td>
        </tr>
         <tr>
          <td class="title_item" width="15%" style="text-align: center;" >审核意见：</td>
          <td colspan="4"><html-el:text property="remark1"  maxlength="256" style="width:70%"></html-el:text></td>
        </tr>
          <tr>
          	  <td colspan="5"  height="40" align="center"><input class="bgButtonSave" type="button" name="Submit4" value=" 保存 " id="btn_submit" />
              <input class="bgButtonBack" type="button" name="Submit5" value="返回" onclick="history.back();return false;" id="btn_back" /></td>
        		</tr>
        </table>
      </div>
      </html-el:form>
      
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 3)){
				$("#btn_submit").attr("disabled",true);
				$("#btn_back").attr("disabled",true);
				this.form.submit();
		}
	});
});


//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
