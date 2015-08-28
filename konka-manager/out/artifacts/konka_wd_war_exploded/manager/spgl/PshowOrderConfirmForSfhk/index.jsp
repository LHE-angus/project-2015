<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/spgl/PshowOrderConfirmForSfhk">
      <html-el:hidden property="method" value="index" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr style="height:280px;" >
         <td style="height:280px;" width="20%" align="center"><strong class="fb">交易流水号：</strong> </td>
          <td style="height:280px;" width="50%" >            
           	<html-el:textarea property="trade_index_all" style="width:400px;height:260px;"></html-el:textarea>多个用","号分隔 
           </td>
           <td style="height:280px;" width="30%" align="center">
            <input class="but1" type="submit" id="t1" name="Submit" value="搜索" /> 
           </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %> 
  </div>
  <c:if test="${not empty entityList}">
  <div class="rtabcont1">
  	  <form id="listForm" name="listForm" method="post" action="PshowOrderConfirmForSfhk.do?method=saveBatch">
      <input type="hidden" name="method" id="method" value="saveBatch" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
          <tr class="tabtt1"> 
            <td width="5%" nowrap="nowrap" align="center">序号</td>
            <td nowrap="nowrap" align="center">交易流水号</td>
            <td nowrap="nowrap" width="10%" >订单状态</td>
            <td nowrap="nowrap" width="7%">正在处理部门</td>
            <td nowrap="nowrap" width="7%">订单类型</td>
            <td width="10%" nowrap="nowrap" align="center">下单人姓名</td>
            <td width="10%" nowrap="nowrap" align="center">购买人姓名</td>
            <td width="15%" nowrap="nowrap" align="center">购买人地区</td> 
            <td width="10%" nowrap="nowrap" align="center">购买人手机</td>
            <td width="10%" nowrap="nowrap" align="center">支付方式</td>
            <td width="10%" nowrap="nowrap" align="center">订单金额</td> 
          </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr> 
	         <td height="28" align="center"><span style="display:none;"><input name="pks" type="checkbox" id="pks" value="${cur.id}" checked="true"/> </span>${vs.count}</td>
	         <td align="left"><c:out value="${cur.trade_index}" /></td>
	         <td align="left">
		         <c:if test="${cur.state eq -30 }">已退货</c:if>
		         <c:if test="${cur.state eq -20 }">审核未通过</c:if>
		         <c:if test="${cur.state eq -10 }">已关闭</c:if>
		         <c:if test="${cur.state eq 0 }">已预订</c:if>
		         <c:if test="${cur.state eq 5 }">待确认</c:if>
		         <c:if test="${cur.state eq 10 }">已确认</c:if>
		         <c:if test="${cur.state eq 20 }">审核通过</c:if>
		         <c:if test="${cur.state eq 30 }">下发处理</c:if>
		         <c:if test="${cur.state eq 40 }">商家发货</c:if>
		         <c:if test="${cur.state eq 50 }">客户已换货</c:if>
		         <c:if test="${cur.state eq 60 }">确认收货</c:if>
	         </td>
	         <td align="left" valign="middle"><c:out value="${cur.map.dept_name}" /></td>
             <td align="left">
	          <c:if test="${cur.order_from eq 1 }">工卡</c:if><c:if test="${cur.order_from eq 2 and cur.map.is_sf ne true}">触网</c:if><c:if test="${cur.order_from eq 2 and cur.map.is_sf eq true }">顺丰</c:if>
	         </td>
             <td align="left"><c:out value="${cur.order_user_name}" /></td>
             <td align="left"><c:out value="${cur.buyer_name}" /></td>
             <td align="left"><c:out value="${cur.map.full_name}" /></td> 
             <td align="center" nowrap="nowrap"><c:out value="${cur.buyer_mp}" /></td>
             <td align="center">
              <c:if test="${cur.pay_way eq 0}">货到付款</c:if>
              <c:if test="${cur.pay_way eq 1}">银行汇款</c:if>
              <c:if test="${cur.pay_way eq 2}">支付宝</c:if>
              <c:if test="${cur.pay_way eq 3}">银联</c:if>
              <c:if test="${cur.pay_way eq 4}">财付通</c:if>
              <c:if test="${cur.pay_way eq 5}">民生银行</c:if>
               <c:if test="${cur.pay_way eq 8}">嘿客代收货款</c:if>
              <c:if test="${cur.pay_way eq 9}">线下处理</c:if>
             </td>
             <td align="center"><c:out value="${cur.pay_price}" /></td> 
            </tr> 
          </c:forEach> 
        </tbody>
      </table>
       <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
          <tr class="tabtt1"><td align="center">  <input  type="submit"  name="Submit" value="全部确认" /> </td>
          </tr>
        </tbody>
        </table>
      </form> 
  </div> 
  </c:if>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">
$(document).ready(function(){ 
});
 

</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
