<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" />  
<title>触网</title> 
<link rel=stylesheet type=text/css href="${ctx}/mobile/css/common.css" />
<link rel=stylesheet type=text/css href="${ctx}/styles/wap/css/css.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script>
<style type="text/css">
.xt_fenye {font-weight: normal;height: 58px;line-height: 58px;text-align: right;padding-right: 30px;padding-top: 20px;}.top_class {height:54px;margin:0 auto;clear:both;background-color:#df511f;}.top_class h3 {font-size:24px;font-family:"Microsoft YaHei";text-align:center;line-height:54px;color:#fff;}.top_class span {float:left;padding-top:19px;padding-left:5%;}.cont1c {margin:0 auto;clear:both;width:100%;padding:10px 0;}
.but_ping {background-color:#e80000;width:100%;height:50px;border:0; text-align: center;font-style:normal;font-family:"Microsoft YaHei";font-size:22px;line-height:50px;color:#FFF;letter-spacing:2px;cursor:pointer;border-radius:3px;}
</style>
</head>
<body>
<div class="top_class"><span class="lspan"><a href="<c:url value='/webservice/wap/center/Index.do?'/>"><img src="${ctx}/styles/epp/mobile/images/return.gif" /></a></span><h3>我的订单</h3></div><div class="maincont"><!--right-->
<div class="member_right padbot45">
<div class="membertab3"><html-el:form action="/wap/center/Orders"><html-el:hidden property="method" styleId="method" value="list" /><html-el:hidden property="orderState" />
	<table style="width:100%;">
		<tr height="40">
			<td width="30%"><font style="margin-left: 10px;">下单时间：</font></td>
			<td><html-el:select property="ts" onchange="this.form.submit();" styleClass="input_txt" style="width:120px;">
				<html-el:option value="0">全部订单</html-el:option>
				<html-el:option value="1">最近三个月订单</html-el:option>
				<html-el:option value="2">三个月以前订单</html-el:option>
				</html-el:select>
			</td>
		</tr>
		<tr height="40">
			<td width="30%"><font style="margin-left: 10px;">订单编号：</font></td><td><html-el:text property="trade_index" maxlength="20" styleClass="input_txt" style="width:120px;"></html-el:text> &nbsp;<input class="inputbtn" type="submit" name="Submit" value="查询" /></td>
		</tr> 
</table></html-el:form><c:forEach var="cur" items="${entityList}" varStatus="vs">
<div class="out">
	<div class="in">
		<table style="width:100%;">
			<tr>
				<td colspan="4" width="70%">
					订单编号：<a href="javascript:void(0);" onclick="view(${cur.id});"><font  style="color: red;"><c:out value="${cur.trade_index}" /></font></a><br/>
					实付金额：<font  style="color: red;"><c:out value="￥${cur.pay_price}" /></font><br/>
					收货人姓名：<c:out value="${cur.buyer_name}" /><br/>
					订单状态：<c:if test="${cur.state eq -30 }">退货成功</c:if> <c:if test="${cur.state eq -20 }">处理失败</c:if> <c:if test="${cur.state eq -10 }">已取消</c:if> <c:if test="${cur.state eq 0 }">待付款</c:if> <c:if test="${cur.state eq 5 }">待确认</c:if><c:if test="${cur.state eq 10 }">已确认待处理</c:if> <c:if test="${cur.state eq 20 }">订单处理中</c:if> <c:if test="${cur.state eq 30 }">订单处理中</c:if> <c:if test="${cur.state eq 40 }">已发货</c:if> <c:if test="${cur.state eq 50 }">已换货</c:if> <c:if test="${cur.state eq 60 }">交易完成</c:if><c:if test="${cur.pay_way eq 9}"> 线下处理</c:if>
				</td>
				<td width="30%">
					<div style="line-height:26px;font-size:15px;">
					<a href="javascript:void(0);" onclick="view(${cur.id});"><font color="#0022ff">订单详情</font></a>
					<c:if test="${cur.state eq 0 }"><br/><c:if test="${cur.pay_way ne 9 and cur.pay_way ne 0 and cur.pay_way ne 1}"> <a class="btn btn-4" href="<c:url value='/webservice/wap/Payment.do?trade_index=${cur.trade_index}' />"><s></s>付款</a><br/></c:if> <a href="#" id="t${cur.id }" onclick="deleteInfo(this);" ><font color="#0022ff">取消订单</font></a> </c:if>  
					</div>
				</td>
			</tr>
			<tr><td colspan="5"><div class="divcss5-4"></div></td></tr>
			<c:forEach items="${cur.pshowOrdeDetailsList}" var="cur2">
			<tr height="60">
				<td align="left" nowrap="nowrap" colspan="2" width="30%">
				<c:set value="${fn:split(cur2.map.main_pic, ',') }" var="imgs" /><c:forEach items="${imgs }" var="img1" begin="0" end="0"><img src="${ctx }/${img1}" alt="${cur2.map.pd_name }" width="51" height="50" /></c:forEach>
				</td>
				<td colspan="2">${cur2.map.pd_name } <p>单价：<font style="color: red;">￥${cur2.price}</font></p><p>数量：${cur2.num}</p></td>
				<td><c:if test="${cur.state eq 60 and cur2.map.is_eavl eq 0}"><div style="line-height:26px;font-size:15px;"><a href ="EcPdEavl.do?method=add&goods_id=${cur2.pd_id}&detail_id=${cur2.bill_item_id}">评价</a></div></c:if></td>
			</tr></c:forEach>
		</table>
	</div>
</div></c:forEach><c:if test="${empty entityList }">
<div style="margin:0 auto;" align="center"> <font style="font-size: 20px;">暂无订单,欢迎选购商品！</font> </div>
</c:if><c:if test="${af.map.pageCount >1}">
<div class="xt_fenye"><a onclick="goback('${af.map.currentPage}','${af.map.orderState}');"><img src="${ctx}/mobile/images/xt_shagnyiye.jpg" width="66" height="24" /></a>
<a onclick="goforward('${af.map.currentPage}','${af.map.orderState}');"><img src="${ctx}/mobile/images/xt_xiayye.jpg" width="66" height="24" /></a></div>
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
function exit(){
	location.href = "${ctx}/webservice/wap/login.do?method=logout";
}

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
	}else if(page == pagelimit && forward == 1){
		alert("已到尾页！");
		return false;
	}else if(pagelimit == 0){
		alert("无翻页信息！");
		return false;
	}else{
		return true;
	}		
}

function goback(page,orderState) {
	if(goPage(null,page,-1,5)){
		window.location.href="Orders.do?"+"page="+page+"&forward=-1&orderState="+orderState;
	}
}

function goforward(page,orderState) {
	if(goPage(null,page,1,5)){
		window.location.href="Orders.do?"+"page="+page+"&forward=1&orderState="+orderState;
	}
}

function deleteInfo(obj){
	var	id ="&id="+ obj.id.replace("t",""); 
	if(confirm("您确定取消订单吗？")){
		location.href="${ctx}/webservice/wap/center/Orders.do?method=delete"+id; 
	}
}

function view(id){
	var buyer_mp="";
	if("${touch}"!="1"){
		var buyer_mp=prompt("查看订单,请输入购买人手机号","");
		if(buyer_mp==null||buyer_mp==""){
			alert("请输入购买人手机号");
			return false;
		}
	}
	var	id ="&id="+ id+"&buyer_mp="+buyer_mp;
	location.href="${ctx}/webservice/wap/center/Orders.do?method=view"+id;  
}
  
  
//]]></script>
</html>
