<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康佳EPP内部优惠购机平台</title>
<link rel=stylesheet type=text/css href="${ctx}/mobile/css/common.css" />
<link rel=stylesheet type=text/css href="${ctx}/styles/wap/css/css.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<style type="text/css">
.xt_fenye {	font-weight: normal;height: 58px;line-height: 58px;	text-align: right;	padding-right: 30px;padding-top: 20px;}
</style>
</head>
<body>
<!-- head start -->
<!-- second start -->
<div class="maincont"><!--right-->
<div class="member_right padbot45">
<div class="membertab3"><html-el:form action="/Orders">
	<html-el:hidden property="method" styleId="method" value="list" />
	<html-el:hidden property="orderState" />
	<html-el:hidden property="username" value="${af.map.username}" />
	<html-el:hidden property="userpass" value="${af.map.userpass}" />
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr height="40">
			<td width="30%">下单时间：</td>
			<td><html-el:select property="ts" onchange="this.form.submit();"
				styleClass="input_txt" style="width:120px;">
				<html-el:option value="0">全部订单</html-el:option>
				<html-el:option value="1">最近三个月订单</html-el:option>
				<html-el:option value="2">三个月以前订单</html-el:option>
			</html-el:select></td>
		</tr>
		<tr height="40">
			<td width="30%">订单编号：</td>
			<td><html-el:text property="trade_index" maxlength="20" styleClass="input_txt" style="width:160px;"></html-el:text></td>
		</tr>
		<tr height="40">
			<td width="30%"></td> <td width="40%"><input class="inputbtn" type="submit" name="Submit" value="查询" /></td>
		</tr>
	</table></html-el:form>
		<c:forEach var="cur" items="${entityList}" varStatus="vs">
			<div class="out">
				<div class="in">
					<table border="0" width="100%">
						<tr>
							<td colspan="3" width="33%">订单编号：<a href="Orders.do?method=view&id=${cur.id}&username=${af.map.username}&userpass=${af.map.userpass}"><font color="blue;"><c:out value="${cur.trade_index}" /></font></a></td>
							<td width="33%">实付金额：<font  style="color: red;"><c:out value="￥${cur.pay_price}" /></font></td>
							<td rowspan="2" width="34%">
								<c:if test="${cur.state eq -30 }">
									<a href="Orders.do?method=view&id=${cur.id}&username=${af.map.username}&userpass=${af.map.userpass}"><font color="#0022ff">订单详情</font></a>
								</c:if> <c:if test="${cur.state eq -20 }">
									<a href="Orders.do?method=view&id=${cur.id}&username=${af.map.username}&userpass=${af.map.userpass}"><font color="#0022ff">订单详情</font></a>
								</c:if> <c:if test="${cur.state eq -10 }">
									<a href="Orders.do?method=view&id=${cur.id}&username=${af.map.username}&userpass=${af.map.userpass}"><font color="#0022ff">订单详情</font></a>
								</c:if> <c:if test="${cur.state eq 0 }">
									<a class="btn btn-4" href="<c:url value='/wap/Payment.do?trade_index=${cur.trade_index}' />"><s></s>付款</a>
									<br />
									<a href="#" id="t${cur.id }" onclick="deleteInfo(this);"><font color="#0022ff">取消订单</font></a>
								</c:if> <c:if test="${cur.state eq 10 }">
									<a href="Orders.do?method=view&id=${cur.id}&username=${af.map.username}&userpass=${af.map.userpass}"><font color="#0022ff">订单详情</font></a>
								</c:if> <c:if test="${cur.state eq 20 }">
									<a href="Orders.do?method=view&id=${cur.id}&username=${af.map.username}&userpass=${af.map.userpass}"><font color="#0022ff">订单详情</font></a>
								</c:if> <c:if test="${cur.state eq 30 }">
									<a href="Orders.do?method=view&id=${cur.id}&username=${af.map.username}&userpass=${af.map.userpass}"><font color="#0022ff">订单详情</font></a>
								</c:if> <c:if test="${cur.state eq 40 }">
									<a href="Orders.do?method=view&id=${cur.id}&username=${af.map.username}&userpass=${af.map.userpass}"><font color="#0022ff">订单详情</font></a>
								</c:if> <c:if test="${cur.state eq 50 }">
									<a href="Orders.do?method=view&id=${cur.id}&username=${af.map.username}&userpass=${af.map.userpass}"><font color="#0022ff">订单详情</font></a>
								</c:if> <c:if test="${cur.state eq 60 }">
									<a href="Orders.do?method=view&id=${cur.id}&username=${af.map.username}&userpass=${af.map.userpass}"><font color="#0022ff">订单详情</font></a>
								</c:if></td>
						</tr> 
						<tr>
							<td colspan="3" width="33%">收货人：<c:out value="${cur.buyer_name}" /></td>
							<td width="33%">订单状态：<c:if test="${cur.state eq -30 }">退货成功</c:if> <c:if test="${cur.state eq -20 }">处理失败</c:if> <c:if test="${cur.state eq -10 }">已取消</c:if>
								 <c:if test="${cur.state eq 0 }">待付款</c:if><c:if test="${cur.state eq 5 }">待确认</c:if> <c:if test="${cur.state eq 10 }">已确认待处理</c:if> 
								 <c:if test="${cur.state eq 20 }">订单处理中</c:if> <c:if test="${cur.state eq 30 }">订单处理中</c:if> 
								<c:if test="${cur.state eq 40 }">已发货</c:if> <c:if test="${cur.state eq 50 }">已换货</c:if> <c:if test="${cur.state eq 60 }">交易完成</c:if>
							</td>
						</tr>
						<tr><td colspan="5"><div class="divcss5-4"></div></td></tr>
						<c:forEach items="${cur.pshowOrdeDetailsList}" var="cur2">
							<tr height="60">
								<td align="center" nowrap="nowrap" colspan="2"><c:set value="${fn:split(cur2.map.main_pic, ',') }" var="imgs" /> <c:forEach items="${imgs }" var="img1" begin="0" end="0">
									<img src="${ctx }/${img1}" alt="${cur2.map.pd_name }" width="51" height="50" />
								</c:forEach></td>
								<td colspan="3">${cur2.map.pd_name }
								<p>单价：<font style="color: red;">￥${cur2.price}</font></p>
								<p>数量：${cur2.num}</p>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div> 
		</c:forEach>
		<c:if test="${empty entityList }">
			<tr height="60"><td align="center" nowrap="nowrap" colspan="10">暂无订单</td></tr>
		</c:if>
<c:if test="${af.map.pageCount >1}">
	<div class="xt_fenye"><a onclick="goback(${af.map.currentPage},null,null,${af.map.orderState});"><img src="${ctx}/mobile/images/xt_shagnyiye.jpg" width="66" height="24" /></a>
	<a onclick="goforward(${af.map.currentPage},null,null,${af.map.orderState});"><img src="${ctx}/mobile/images/xt_xiayye.jpg" width="66" height="24" /></a></div>
</c:if></div>
</div>
<div class="clear"></div>
</div>
<!-- six footer -->
</body>
<script type="text/javascript">//<![CDATA[ 
                                          
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return (r[2]);
    return null;
}

$(document).ready(function(){

});

var username1='${af.map.username}';
var userpass1='${af.map.userpass}';
var recordNum = '${af.map.recordCount}';

function goview(url){
	var href_value = "${ctx}/" + url;
	window.location.href = href_value ;
}
function goPage(method,page,forward,pagelimit){
	pagelimit = Math.ceil(recordNum / pagelimit);
	if(page == 1 && forward == -1){
		alert("已到首页！");
		return false;
	}
	else if(page == pagelimit && forward == 1){
		alert("已到尾页！");
		return false;
	}
	else if(pagelimit == 0){
		alert("无翻页信息！");
		return false;
	}
	else
		return true;
}

function goback(page,username,userpass,orderState) {
	if(goPage(null,page,-1,5)){
		window.location.href="Orders.do?"+"username="+username1+"&userpass="+userpass1+"&page="+page+"&forward=-1&orderState="+orderState;
	}
}

function goforward(page,username,userpass,orderState) {
	if(goPage(null,page,1,5)){
		window.location.href="Orders.do?"+"username="+username1+"&userpass="+userpass1+"&page="+page+"&forward=1&orderState="+orderState;
	}
}

function deleteInfo(obj){
	var	id ="&id="+ obj.id.replace("t",""); 
	if(confirm("您确定取消订单吗？")){
		location.href="${ctx}/Orders.do?method=delete"+id+"&username="+username1+"&userpass="+userpass1; 
	}
}
 
//]]></script>
</html>
