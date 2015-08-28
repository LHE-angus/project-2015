<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<style type="text/css">
body {
	font-size:12px;
}
label {
	cursor:pointer;
}
.ti {
	background-color:#eee;
	text-align:right;
	white-space:nowrap;
}
.grp {
	font-weight:bold;
	color:#74685F;
	border-bottom:1px solid #74685F;
	padding:5px;
	margin-top:5px;
}
.td-title {
	padding-top:30px;
	font-size:12px;
	font-weight:700;
	border-bottom:1px solid #ccc;
}
.btn {
	padding:3px 8px;
	color:#555;
	border:1px solid #ccc;
	border-radius : 3px;
	background-color: #eee;
}
.btn:hover {
	background-color: #ccc;
}
</style>
<title>订单记录</title>
</head>
<body>
<html-el:form action="/manager/JxcKonkaOrderRegister.do">
  <html-el:hidden property="method" value="listInMobile" />
  <html-el:hidden property="mod_id" styleId="mod_id" />
  <html-el:hidden property="is_th" styleId="is_th" value="1" />
  <html-el:hidden property="cust_id" styleId="cust_id" />
  <table width="100%" border="0" cellspacing="0" cellpadding="5">
    <tr>
      <td width="15%" class="ti">订单日期：</td>
      <td><input type="text" name="add_date_gt" id="add_date_gt" class="webinput" value="${af.map.add_date_gt}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:center;width:80px;" />
        至
        <input type="text" name="add_date_lt" id="add_date_lt" class="webinput" value="${af.map.add_date_lt}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:center;width:80px;"/></td>
    </tr>
    <tr>
      <td class="ti">交易流水号：</td>
      <td><html-el:text property="trade_index_like" styleClass="webinput" styleId="trade_index_like" maxlength="40"/></td>
    </tr>
    <tr>
      <td class="ti">产品型号：</td>
      <td><html-el:text property="pd_name_like" styleClass="webinput" styleId="pd_name_like" maxlength="40"/></td>
    </tr>
    <tr>
      <td colspan="2" align="center"><html-el:select property="audit_state_x" styleClass="webinput" styleId="audit_state_x" >
          <html-el:option value="">订单状态</html-el:option>
          <html-el:option value="10">未提交</html-el:option>
          <html-el:option value="20">审核中</html-el:option>
          <html-el:option value="30">已通过</html-el:option>
        </html-el:select>
        <html-el:select property="is_delivered" styleClass="webinput" styleId="is_delivered" >
          <html-el:option value="">发货状态</html-el:option>
          <html-el:option value="0">未发货</html-el:option>
          <html-el:option value="1">已发货</html-el:option>
        </html-el:select>
        <html-el:select property="pay_type" styleClass="webinput" styleId="pay_type" >
          <html-el:option value="">选择支付</html-el:option>
          <html-el:option value="4">现汇</html-el:option>
          <html-el:option value="5">帐期</html-el:option>
          <html-el:option value="6">承兑</html-el:option>
          <html-el:option value="45">现汇/帐期</html-el:option>
          <html-el:option value="46">现汇/承兑</html-el:option>
          <html-el:option value="56">帐期/承兑</html-el:option>
          <html-el:option value="456">现汇/帐期/承兑</html-el:option>
        </html-el:select>
        <html-el:select property="send_type" styleClass="webinput" styleId="send_type" >
          <html-el:option value="">选择配送</html-el:option>
          <html-el:option value="1">自提</html-el:option>
          <html-el:option value="2">配送</html-el:option>
        </html-el:select></td>
    </tr>
    <tr>
      <td colspan="2" align="center"><input name="button" type="submit" class="bgSearch" id="button" value=" 搜 索 " style="width:100%;height:30px;" /></td>
    </tr>
  </table>
</html-el:form>
<div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
</div>
<table width="100%" border="0" cellpadding="3" cellspacing="0">
  <c:forEach items="${konkaOrderInfoList}" var="cur" varStatus="vs">
    <tr>
      <td class="td-title">订单号：<a href="JxcKonkaOrderRegister.do?method=viewInMobile&mod_id=${af.map.mod_id}&id=${cur.id}&order_id=${cur.id}">${cur.trade_index}</a></td>
      <td class="td-title" align="right"><fmt:formatDate value="${cur.order_date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
    </tr>
    <tr>
      <td colspan="2">数量：
        <fmt:formatNumber value="${cur.order_num}" pattern="0" />
        金额：
        <fmt:formatNumber value="${cur.money}" pattern="0.00" />
        折扣：
        <fmt:formatNumber value="${cur.good_discount_price}" pattern="0.00" /></td>
    </tr>
    <tr>
      <td nowrap="nowrap">订单状态：
        <c:if test="${cur.is_submit eq 0}"><span style="color:#F00;">未提交</span></c:if>
        <c:if test="${cur.is_submit eq 1}">
          <c:if test="${cur.audit_state ne 3}"><span style="color:#00f;">审核中 </span></c:if>
          <c:if test="${cur.audit_state eq 3}"><span style="color:#060;">已通过 </span></c:if>
        </c:if>
        （${fn:split('等待确认,不用确认,您已确认', ',')[cur.kh_confirm_state + 1]}） </td>
      <td align="right"><c:choose>
          <c:when test="${cur.pay_type eq 4}">现汇</c:when>
          <c:when test="${cur.pay_type eq 5}">帐期</c:when>
          <c:when test="${cur.pay_type eq 6}">承兑</c:when>
          <c:when test="${cur.pay_type eq 45}">现汇+帐期</c:when>
          <c:when test="${cur.pay_type eq 46}">现汇+承兑</c:when>
          <c:when test="${cur.pay_type eq 56}">帐期+承兑</c:when>
          <c:when test="${cur.pay_type eq 456}">现汇+帐期+承兑</c:when>
        </c:choose>
        /
        <c:if test="${cur.send_type eq 1}">自提</c:if>
        <c:if test="${cur.send_type eq 2}">配送</c:if>
        
        <c:if test="${cur.audit_state eq 3 and cur.kh_confirm_state eq -1}">&nbsp; <span style="cursor:pointer;" class="fblue btn"  onclick="doNeedMethod('请您核对一下订单再确认，是否确认！', 'JxcKonkaOrderRegister.do', 'confirmOrderOnMobile','&mod_id=${af.map.mod_id}&order_id=${cur.id}&cust_id=${cur.cust_id}&'+$('#bottomPageForm').serialize())">确认</span></c:if>
       	<c:if test="${cur.kh_confirm_state eq 1 or cur.kh_confirm_state eq 0 or cur.audit_state lt 3}">&nbsp; <span class="btn" style="cursor:pointer;color:#ccc;" title="提示：此订单暂不可确认，可能是：&#13;1、不是已被审核完成订单；&#13;2、没有被管理人员修改过；&#13;3、已经被确认的订单。">确认</span></c:if></td>
    </tr>
  </c:forEach>
</table>
<div class="rtabcont1" style="margin-top:30px;">
  <c:if test="${not empty konkaOrderInfoList}">
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="JxcKonkaOrderRegister.do?method=listInMobile">
      <table width="100%" border="0" align="center" cellspacing="0" cellpadding="0">
        <tr>
          <td height="40" align="left"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script> 
            <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("audit_state_x", "${af.map.audit_state_x}");
				pager.addHiddenInputs("add_date_gt", "${af.map.add_date_gt}");
				pager.addHiddenInputs("add_date_lt", "${af.map.add_date_lt}");
				pager.addHiddenInputs("trade_index_like", "${af.map.trade_index_like}");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("cust_id", "${af.map.cust_id}");
				pager.addHiddenInputs("pay_type", "${af.map.pay_type}");
				pager.addHiddenInputs("send_type", "${af.map.send_type}");
				pager.addHiddenInputs("audit_state", "${af.map.audit_state}");
				pager.addHiddenInputs("pd_name_like", "${af.map.pd_name_like}");
				pager.addHiddenInputs("is_delivered", "${af.map.is_delivered}");
				document.write(pager.toString());
			</script></td>
        </tr>
      </table>
    </form>
  </c:if>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var f=document.forms[0];
	$(".bgSearch").click(function(){
		var s_time = $("#add_date_gt").val();
		var e_time = $("#add_date_lt").val();
		if ("" != s_time && "" != e_time && s_time > e_time) {
			alert("开始日期不能大于结束日期！");
			return false;
		}
		if(!Validator.Validate(f, 1)){
			return false;
		}
	});
});
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>