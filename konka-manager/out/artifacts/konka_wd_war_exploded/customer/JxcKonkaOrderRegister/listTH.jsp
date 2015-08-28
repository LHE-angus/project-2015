<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
select {
	font-family:Microsoft YAHEI;
	font-size:12px;
}
input {
	font-family:Microsoft YAHEI;  
	font-size:12px;
}
.th td {font-weight:700;background-color:#eee;}
</style>
<title>订单记录</title>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oartop">
  <table width="500" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
      <td>当前位置：${naviString}</td>
    </tr>
  </table>
</div>
<html-el:form action="/manager/JxcKonkaOrderRegister.do">
  <html-el:hidden property="method" value="list" />
  <html-el:hidden property="mod_id" styleId="mod_id" />
  <html-el:hidden property="cust_id" styleId="cust_id" />
  <html-el:hidden property="is_th" styleId="is_th" />
  <div class="rtabcont2">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
      <tr>
        <td width="10%" class="fb">订单日期：</td>
        <td>
        	<input type="text" name="add_date_gt" id="add_date_gt" class="webinput" value="${af.map.add_date_gt}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:center;width:80px;" />
          	-
          	<input type="text" name="add_date_lt" id="add_date_lt" class="webinput" value="${af.map.add_date_lt}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:center;width:80px;"/></td>
        <td width="10%" class="fb">交易号：</td>
        <td><html-el:text property="trade_index_like" styleClass="webinput" styleId="trade_index_like" maxlength="40"/></td>
        <td width="10%" class="fb">送达方：</td>
        <td><html-el:text property="we_like" styleClass="webinput" styleId="we_like" maxlength="40"/></td>
      </tr>
      <tr>
        <td class="fb">订单状态：</td>
        <td><html-el:select property="audit_state_x" styleClass="webinput" styleId="audit_state_x" >
            <html-el:option value="">--请选择--</html-el:option>
            <html-el:option value="10">未提交</html-el:option>
            <html-el:option value="20">审核中</html-el:option>
            <html-el:option value="30">已通过</html-el:option>
          </html-el:select></td>
        <td class="fb">产品型号：</td>
        <td><html-el:text property="pd_name_like" styleClass="webinput" styleId="pd_name_like" onkeyup="upperCase(this.id)" maxlength="40"/></td>
      	<td class="fb">退货类型：</td>
        <td><html-el:select property="return_type" styleId="return_type" >
				<html-el:option value="">请选择..</html-el:option>
				<html-el:option value="1">滞销退货</html-el:option>
				<html-el:option value="2">残次品退货</html-el:option>
				<html-el:option value="3">当月拒收</html-el:option>
				<html-el:option value="6">跨月拒收</html-el:option>
				<html-el:option value="4">异型换机</html-el:option>
				<html-el:option value="5">其他原因</html-el:option>
			</html-el:select>
		</td>
      </tr>
      <tr>
      	<td class="fb">发货状态：</td>
      	<td>
      		<html-el:select property="is_delivered" styleId="is_delivered">
				<html-el:option value="0">未出货</html-el:option>
				<html-el:option value="1">已出货</html-el:option>
			</html-el:select>
      	</td>
      	<td colspan="2"></td>
      	<td width="10%" rowspan="2"><input name="button" type="submit" class="bgSearch" id="button" value="搜 索" /></td>
      </tr>
    </table>
  </div>
</html-el:form>
<div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
  <input name="input" type="button" id="gotoAdd" class="bgButtonAdd" value="新 增" onclick="location.href='${ctx}/customer/manager/JxcKonkaOrderRegister.do?method=addTH&mod_id=${af.map.mod_id}&cust_id=${af.map.cust_id}'" />
</div>
<div class="rtabcont1">
  <div style="overflow-x:auto;">
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="rtable2">
    <tr class="th">
      <td width="5%" nowrap="nowrap" align="center">行号</td>
      <td width="8%" nowrap="nowrap" align="center">日期</td>
      <td nowrap="nowrap" align="center">交易号</td>
      <td nowrap="nowrap" align="center">送达方</td>
      <td nowrap="nowrap" align="center">退货类型</td>
      <td nowrap="nowrap" align="center">发货状态</td>
      <td nowrap="nowrap" align="center">发货时间</td>
      <td nowrap="nowrap" align="center">收货时间</td>
      <td width="8%" nowrap="nowrap" align="center">数量</td>
      <td width="8%" nowrap="nowrap" align="center">金额（元）</td>
      <td width="8%" nowrap="nowrap" align="center">折扣（元）</td>
      <td width="8%" nowrap="nowrap" align="center">审核状态</td>
       <td width="5%" nowrap="nowrap" align="center">是否确认</td>
      <td width="6%" nowrap="nowrap" align="center">订单类型</td>
      <td width="14%" nowrap="nowrap" align="center">操作</td>
    </tr>
    <c:forEach items="${konkaOrderInfoList}" var="cur" varStatus="vs">
      <tr>
        <td align="center">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
        <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.order_date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
        <td align="center"><a href="JxcKonkaOrderRegister.do?method=viewOrder&mod_id=${af.map.mod_id}&id=${cur.id}&order_id=${cur.id}" style="color:blue;text-decoration:underline;">${cur.trade_index}</a></td>
        <td align="center">${cur.we }</td>
        <td align="center">
        	<c:choose>
        		<c:when test="${cur.return_type eq '1' }">滞销退货</c:when>
        		<c:when test="${cur.return_type eq '2' }">残次品退货</c:when>
        		<c:when test="${cur.return_type eq '3' }">当月拒收</c:when>
        		<c:when test="${cur.return_type eq '4' }">异型换机</c:when>
        		<c:when test="${cur.return_type eq '5' }">其他原因</c:when>
        		<c:when test="${cur.return_type eq '6' }">跨月拒收</c:when>
        	</c:choose>
        </td>
        <td align='center'>
        	<c:choose>
        		<c:when test="${cur.is_delivered eq 0 }">未发货</c:when>
        		<c:when test="${cur.is_delivered eq 1 }">已发货</c:when>
        	</c:choose>
        </td>
        <td align="right" nowrap="nowrap">${cur.map.lfdat}</td>
        <td align="right" nowrap="nowrap">${cur.map.shdat}</td>
        <td align="right"><fmt:formatNumber value="${cur.order_num}" pattern="0" /></td>
        <td align="right"><fmt:formatNumber value="${cur.money}" pattern="0.00" /></td>
        <td align="right"><fmt:formatNumber value="${cur.good_discount_price}" pattern="0.00" /></td>
        <td align="center"><c:if test="${cur.is_submit eq 0}"><span style="color:#F00;">未提交</span></c:if>
          <c:if test="${cur.is_submit eq 1}">
            <c:if test="${cur.audit_state ne 3 && cur.audit_state ne 4}"><span style="color:#00f;">审核中 </span></c:if>
            <c:if test="${cur.audit_state eq 3}"><span style="color:#060;">已通过 </span></c:if>
            <c:if test="${cur.audit_state eq 4}"><span style="color:orange;">已作废 </span></c:if>
          </c:if></td>
        <td align="center">${fn:split('等待确认,不用确认,您已确认', ',')[cur.kh_confirm_state + 1]}</td>
        <td align="center">
        	ZFRE
        </td>
        <td align="center" nowrap="nowrap">
      		<span style="cursor:pointer;" class="fblue" onclick="confirmDelete('提示，您确认复制此订单？', 'JxcKonkaOrderRegister.do', '&mod_id=${af.map.mod_id}&order_id=${cur.id}&cust_id=${cur.cust_id}&CUSTID=${CUSTID }&FROMSALESMAN=${FROMSALESMAN }&'+$('#bottomPageForm').serialize(), 'copyTH')">复制</span>
      		<c:if test="${cur.is_submit eq 1}"> <span style="cursor:pointer;color:#ccc;"title="提示，订单已经提交，不能修改！">修改 </span> </c:if>
	        <c:if test="${cur.is_submit eq 0}">
	            <c:if test="${cur.add_user_id eq SESSION_U_ID}"><span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'JxcKonkaOrderRegister.do', 'editOrder','&mod_id=${af.map.mod_id}&order_id=${cur.id}&cust_id=${cur.cust_id}&'+$('#bottomPageForm').serialize())">修改</span> </c:if>
	            <c:if test="${cur.add_user_id ne SESSION_U_ID}"><span style="cursor:pointer;color:#ccc;"title="提示，订单不是本人添加，不能修改！">修改</span> </c:if>
	        </c:if>
      		 <c:if test="${cur.audit_state le 2 and cur.is_submit ne 0}">
            <c:if test="${cur.add_user_id eq SESSION_U_ID and empty cur.r3_id and cur.is_change ne 1 and cur.is_change ne 2}"><span style="cursor:pointer;color:#f00;text-decoration: underline;" onclick="confirmDelete('提示，您确认撤回此订单？', 'JxcKonkaOrderRegister.do', '&mod_id=${af.map.mod_id}&order_id=${cur.id}&cust_id=${cur.cust_id}&is_th=1&process_id=${cur.process_id}&CUSTID=${CUSTID }&FROMSALESMAN=${FROMSALESMAN }&'+$('#bottomPageForm').serialize(), 'withdrawals')">撤回</span> </c:if>
            <c:if test="${cur.add_user_id ne SESSION_U_ID}"><span style="cursor:pointer;color:#ccc;" title="提示，订单不是本人添加，不能再撤回！">撤回</span> </c:if>
          </c:if>
           <c:if test="${!(cur.audit_state le 2 and cur.is_submit ne 0)}">
            <c:if test="${cur.add_user_id ne SESSION_U_ID}"><span style="cursor:pointer;color:#ccc;" title="提示，订单不是本人添加，不能再撤回！">撤回</span> </c:if>
          </c:if>
      		<c:if test="${cur.is_submit eq 0}">
	            <c:if test="${cur.add_user_id eq SESSION_U_ID}"><span style="cursor:pointer;"  class="fblue"  onclick="confirmDelete('提示，您确认删除此订单？', 'JxcKonkaOrderRegister.do', '&mod_id=${af.map.mod_id}&order_id=${cur.id}&cust_id=${cur.cust_id}&is_th=1&'+$('#bottomPageForm').serialize(), 'delete')">删除 </span></c:if>
	            <c:if test="${cur.add_user_id ne SESSION_U_ID}"><span style="cursor:pointer;color:#ccc;" title="提示，订单不是本人添加，不能删除！">删除 </span></c:if>
            </c:if>
            <c:if test="${cur.is_submit ne 0}"> <span style="cursor:pointer;color:#ccc;" title="提示，订单已经提交，不能删除！">删除</span> </c:if>
      		<c:if test="${cur.audit_state eq 3 and cur.kh_confirm_state eq -1}"><span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod('请您核对一下订单再确认，是否确认！', 'JxcKonkaOrderRegister.do', 'confirmOrder','&mod_id=${af.map.mod_id}&order_id=${cur.id}&cust_id=${cur.cust_id}&is_th=1&'+$('#bottomPageForm').serialize())">确认</span></c:if>
       	  	<c:if test="${cur.kh_confirm_state eq 1 or cur.kh_confirm_state eq 0 or cur.audit_state lt 3}"><span style="cursor:pointer;color:#ccc;" title="提示：此订单暂不可确认，可能是：&#13;1、不是已被审核完成订单；&#13;2、没有被管理人员修改过；&#13;3、已经被确认的订单。">确认</span></c:if>
      		<span style="cursor:pointer;" class="fblue" onclick="printJBill('${cur.id}')">打印</span>
      	</td>
      </tr>
    </c:forEach>
  </table>
  </div>
  <c:if test="${not empty konkaOrderInfoList}">
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="JxcKonkaOrderRegister.do?method=list">
      <table width="100%" border="0" align="center" cellspacing="0" cellpadding="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script> 
            <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("add_date_gt", "${af.map.add_date_gt}");
				pager.addHiddenInputs("add_date_lt", "${af.map.add_date_lt}");
				pager.addHiddenInputs("trade_index_like", "${af.map.trade_index_like}");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("cust_id", "${af.map.cust_id}");
				pager.addHiddenInputs("pay_type", "${af.map.pay_type}");
				pager.addHiddenInputs("order_type", "${af.map.order_type}");
				pager.addHiddenInputs("send_type", "${af.map.send_type}");
				pager.addHiddenInputs("audit_state", "${af.map.audit_state}");
				pager.addHiddenInputs("pd_name_like", "${af.map.pd_name_like}");
				pager.addHiddenInputs("is_delivered", "${af.map.is_delivered}");
				pager.addHiddenInputs("audit_state_x", "${af.map.audit_state_x}");
				pager.addHiddenInputs("is_th", "${af.map.is_th}");
				pager.addHiddenInputs("we_like", "${af.map.we_like}");
				pager.addHiddenInputs("return_type", "${af.map.return_type}");
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
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=discuz"></script>
<script type="text/javascript">//<![CDATA[
//打印订单
function printJBill(order_id){
	$.dialog({
		title:  "单据打印",
		width:  900,
		height: 580,
        lock:true ,
		content:"url:JxcKonkaOrderRegister.do?method=print&order_id="+order_id
	});
}  
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

function upperCase(x)
{
var y=document.getElementById(x).value;
document.getElementById(x).value=y.toUpperCase();
}
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>