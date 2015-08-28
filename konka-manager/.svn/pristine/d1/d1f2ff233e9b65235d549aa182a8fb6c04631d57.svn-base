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
    <html-el:form action="/pshow/PshowOrderConfirm">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr style="height:25px;">
          <td style="height:25px;" nowrap="nowrap">&nbsp;<strong class="fb">交易流水号：</strong>
            <html-el:text property="trade_index_like" styleId="trade_index_like" style="width:100px;" />
            &nbsp;<strong class="fb">下单人姓名：</strong>
            <html-el:text property="order_user_name_like" styleId="order_user_name_like" style="width:100px;" />
            &nbsp;
             <strong class="fb">购买人姓名：</strong>
            <html-el:text property="buyer_name_like" styleId="buyer_name_like" style="width:100px;" />
            &nbsp;
            <strong class="fb">购买人手机：</strong>
            <html-el:text property="buyer_mp_like" styleId="buyer_mp_like" style="width:100px;" />
            &nbsp;
            <!--
            <strong class="fb">订单状态：</strong>
            <select name=state id="state" class="bd">
           	 <option value="">请选择</option>
              <option value="20">审核通过</option>
              <option value="-20">审核未通过</option>
            </select>
            -->
              &nbsp;
              <input class="but1" type="submit" id="t1" name="Submit" value="搜索" />
              <br/>
            </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
          <tr class="tabtt1">
            <td width="5%" nowrap="nowrap" align="center">序号</td>
            <td nowrap="nowrap" align="center">交易流水号</td>
            <td nowrap="nowrap"width="10%" >订单状态</td>
            <td nowrap="nowrap" width="7%">正在处理的部门</td>
            <td width="10%" nowrap="nowrap" align="center">下单人姓名</td>
            <td width="10%" nowrap="nowrap" align="center">购买人姓名</td>
            <td width="15%" nowrap="nowrap" align="center">购买人地区</td>
            <td width="10%" nowrap="nowrap" align="center">购买人固定电话</td>
            <td width="10%" nowrap="nowrap" align="center">购买人手机号码</td>
            <td width="10%" nowrap="nowrap" align="center">支付方式</td>
            <td width="15%" nowrap="nowrap" align="center" >操作</td>
          </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
	         <td height="28"  align="center">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
	         <td align="left"><c:out value="${cur.trade_index}" /></td>
	         <td align="left">
	         <c:if test="${cur.state eq -30 }">已退货</c:if>
	         <c:if test="${cur.state eq -20 }">审核未通过</c:if>
	         <c:if test="${cur.state eq -10 }">已关闭</c:if>
	         <c:if test="${cur.state eq 0 }">已预订</c:if>
	         <c:if test="${cur.state eq 10 }">已付款</c:if>
	         <c:if test="${cur.state eq 20 }">审核通过</c:if>
	         <c:if test="${cur.state eq 30 }">下发处理</c:if>
	         <c:if test="${cur.state eq 40 }">商家发货</c:if>
	         <c:if test="${cur.state eq 50 }">客户已换货</c:if>
	         <c:if test="${cur.state eq 60 }">确认收货</c:if>
	         </td>
	         <td align="left" valign="middle"> <c:out value="${cur.map.dept_name}" /></td>
              <td align="left"><c:out value="${cur.order_user_name}" /></td>
              <td align="left"><c:out value="${cur.buyer_name}" /></td>
              <td align="left"><c:out value="${cur.map.full_name}" /></td>
              <td align="center" nowrap="nowrap"><c:out value="${cur.buyer_tel}" /></td>
              <td align="center" nowrap="nowrap"><c:out value="${cur.buyer_mp}" /></td>
              <td align="center">
              	 <c:if test="${cur.pay_way eq 0}">货到付款</c:if>
              <c:if test="${cur.pay_way eq 1}">在线支付</c:if>
              <c:if test="${cur.pay_way eq 2}">支付宝</c:if>
              <c:if test="${cur.pay_way eq 3}">银联</c:if>
              </td>
              <td align="center" nowrap="nowrap">
              <c:if test="${cur.state eq 40}">
              <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'PshowOrderConfirm.do','confirm' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">确认收货</span>
              </c:if>
               <c:if test="${cur.state ne 40}">
                <span style="color:#CCC;" class="fblue">确认收货</span>
               </c:if>
              </td>
            </tr>
            <c:if test="${vs.last eq true}">
              <c:set var="i" value="${vs.count}" />
            </c:if>
          </c:forEach>
          <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
            <tr align="center">
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="PshowOrderConfirm.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("trade_index_like", "${af.map.trade_index_like}");
	            pager.addHiddenInputs("order_user_name_like", "${af.map.order_user_name_like}");
	            pager.addHiddenInputs("state", "${af.map.state}");
	            pager.addHiddenInputs("buyer_name_like", "${af.map.buyer_name_like}");
	            pager.addHiddenInputs("buyer_mp_like", "${af.map.buyer_mp_like}");
	            document.write(pager.toString());
	      </script></td>
        </tr>
      </table>
    </form>
  </div>
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
