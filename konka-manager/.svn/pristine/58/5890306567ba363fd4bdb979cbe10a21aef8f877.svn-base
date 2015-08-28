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
    <html-el:form action="/manager/PShowOrder">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
           <td width="100%" nowrap="nowrap">&nbsp;&nbsp;<strong class="fb">交易流水号：</strong>&nbsp;
            <html-el:text property="trade_index_like" styleId="trade_index_like" style="width:100px;" />&nbsp;
           	<strong class="fb">支付单号：</strong>&nbsp;
            <html-el:text property="trade_no_like" styleId="trade_no_like" style="width:100px;" />&nbsp;&nbsp;
            <input name="button" type="submit" class="bgSearch" id="button" value="搜 索" />
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
            <td nowrap="nowrap" width="15%" align="center">产品型号</td>
            <td nowrap="nowrap"width="10%" align="left" >订单状态</td>
            <td width="5%" nowrap="nowrap" align="right">产品数量</td>
            <td width="6%" nowrap="nowrap" align="right">产品单价</td>
            <td width="7%" nowrap="nowrap" align="center">总价</td>
            <td width="15%" nowrap="nowrap" align="center">支付单号</td>
            <td width="10%" nowrap="nowrap" align="center">下单时间</td>
            <td width="10%" nowrap="nowrap" align="center">产品图片</td> 
            <td width="15%" nowrap="nowrap" align="center" >操作</td>
          </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
	         <td height="28"  align="center">${vs.count}</td>
	         <td align="left"><a href="${ctx}/Payment.do?method=view&trade_index=${cur.trade_index}" 
	         style="color: red;text-decoration: underline"><c:out value="${cur.trade_index}" /></a></td>
	         <td align="center"><a href="${ctx}/customer/manager/PShow.do?method=OrderNow&id=${cur.map.pd_id}" 
	         style="color: red;text-decoration: underline"><c:out value="${cur.map.pd_sn}" /></a></td>
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
	         <td align="right" valign="middle"> <c:out value="${cur.map.num}" /></td>
              <td align="right"><c:out value="${cur.map.price}" /></td>
              <td align="right"><c:out value="${cur.map.total_price}" /></td>
              <td align="center" nowrap="nowrap"><c:out value="${cur.trade_no}" /></td>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd"/></td>
               <td align="center" nowrap="nowrap">
               <img src="${ctx}/${fn:substringBefore(cur.map.main_pic, '.')}_086.jpg" height="40"/>
               </td>
              <td align="center" nowrap="nowrap">
              <c:choose>
              <c:when test="${cur.state eq 0}">
              <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'PShowOrder.do','remove' ,'id=${cur.id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())">取消</span>
              </c:when>
              <c:otherwise>
               <span style="color:#CCC;" class="fblue">取消</span>
              </c:otherwise>
              </c:choose>
               <c:choose>
              <c:when test="${cur.state eq 0}">
              <a class="fblue" href="${ctx}/Payment.do?method=list&trade_index=${cur.trade_index}" style="text-decoration:underline">支付</a>
              </c:when>
              <c:otherwise>
              <span style="color:#CCC;" class="fblue">支付</span>
              </c:otherwise>
              </c:choose>
              <c:choose>
              <c:when test="${cur.state eq 40}">
              <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'PShowOrder.do','confirm' ,'id=${cur.id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())">确认收货</span>
             </c:when>
             <c:otherwise>
             <span style="color:#CCC;" class="fblue">确认收货</span>
             </c:otherwise>
             </c:choose>
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
            </tr>
          </c:forEach>
        </tbody>
      </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="PShowOrder.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("trade_index_like", "${af.map.trade_index_like}");
	            pager.addHiddenInputs("trade_no_like", "${af.map.trade_no_like}");
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
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
