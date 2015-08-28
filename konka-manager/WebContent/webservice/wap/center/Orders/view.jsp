<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no"> 
<title>触网</title>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/global.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/epp_index.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script>  
<style type="text/css"> 
.bx1{padding:5px 0 5px 0}
.mt{padding-top:3px;padding-bottom:2px;line-height:25px;border-top:1px solid #FFCC9A;margin-left:-5px;background:#FEF2E6;font-weight:900;color:#cc0000;}
.mt a{text-decoration:underline;color:#005AA0}.mt span{font-weight:normal;margin-left:5px}
</style>
</head>
<body>
<div class="top_class"><span class="lspan"><a href="javascript:void(0);history.back();"><img src="${ctx}/styles/epp/mobile/images/return.gif" /></a></span><h3>订单详情</h3><a href="javascript:void(0);showNav();" id="btnJdkey" class="new-a-jd"><span>开心猫</span></a>
	<div class="new-jd-tab" style="top:45px;display:none;" id="jdkey">
	<div class="new-tbl-type">
	<a href="<c:url value='/webservice/wap/Index.do' />" class="new-tbl-cell"><span class="icon">首页</span><p style="color:#6e6e6e;">首页</p>	</a>
	<a href="<c:url value='/webservice/wap/ProdType.do' />" class="new-tbl-cell"><span class="icon2">产品分类</span><p style="color:#6e6e6e;">产品分类</p></a>
	<a href="<c:url value='/webservice/wap/ShoppingCar.do' />" id="html5_cart" class="new-tbl-cell"><span class="icon3 on">购物车</span><p style="color:#6e6e6e;" class="on">购物车</p></a>
	<a href="<c:url value='/webservice/wap/center/Index.do' />" class="new-tbl-cell"><span class="icon4">会员中心</span> <p style="color:#6e6e6e;">会员中心</p></a>
	</div>
	</div>
</div>
<div id="content"> 
<div class="mainbox">
<div class="maincont">
<div class="mt" style="color:#c40000"><span>订单信息 </span></div>
<div class="bx1">
            订单号:${fn:escapeXml(af.map.trade_index)} <br/>
            订单状态：<c:if test="${af.map.state eq -30 }">退货成功</c:if><c:if test="${af.map.state eq -20 }">处理失败</c:if><c:if test="${af.map.state eq -10 }">已取消</c:if><c:if test="${af.map.state eq 0 }">待付款</c:if><c:if test="${af.map.state eq 5 }">待确认</c:if><c:if test="${af.map.state eq 10 }">已确认待处理</c:if><c:if test="${af.map.state eq 20 }">订单处理中</c:if><c:if test="${af.map.state eq 30 }">订单处理中</c:if><c:if test="${af.map.state eq 40 }">已发货</c:if><c:if test="${af.map.state eq 50 }">已换货</c:if>
            <c:if test="${af.map.state eq 60 }">交易完成</c:if><c:if test="${af.map.pay_way eq 9}"> 线下处理</c:if>     <br/>
            订单金额：<font color="red" style="font-family:Arial;font-weight:bold"><fmt:formatNumber value="${af.map.pay_price}" pattern="￥0.00元" /></font><br/>
            下单时间：<fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd hh:mm:ss" /><br/>
</div>
<div class="mt" style="color:#c40000"><span>配送跟踪</span></div>
<div class="bx1">
                顺丰单号：<c:if test="${empty af.map.sf_dh}">暂无</c:if><c:if test="${not empty af.map.sf_dh}">${af.map.sf_dh }</c:if><br/>
    		<c:if test="${af.map.state eq 60 }">客户已经签收&nbsp;&nbsp;</c:if><c:if test="${af.map.is_sf eq 0 }">您提交了订单，请等待系统确认&nbsp;&nbsp;</c:if>
            <c:if test="${af.map.is_sf eq 1}"><input type="button" style="cursor: pointer;" class="but9" id="syncBtn" value="实时跟踪"></input><br/>
              顺丰接口查询比较慢，请移步到顺丰官网查询<br/></c:if>
            <span id="s1" style="color: green;"></span><br/>
</div>
<div class="mt"><span>付款方式: <c:if test="${af.map.state gt 0 }"><c:if test="${af.map.pay_way eq 1}">在线支付</c:if><c:if test="${af.map.pay_way eq 2}">支付宝</c:if><c:if test="${af.map.pay_way eq 3}">银联</c:if><c:if test="${af.map.pay_way eq 5}">民生e支付</c:if> <c:if test="${af.map.pay_way eq 9}">线下处理</c:if></c:if> </span></div>
<div class="bx1">
                商品金额: <font color="red" style="font-family:Arial;font-weight:bold"><fmt:formatNumber value="${af.map.total_price}" pattern="￥0.00元" /></font><br/>
                 抵扣金额: <font color="red" style="font-family:Arial;font-weight:bold"><fmt:formatNumber value="${af.map.dedu_price}" pattern="￥0.00元" /></font><br/>
                应支付金额: <font color="red" style="font-family:Arial;font-weight:bold"><fmt:formatNumber value="${af.map.pay_price}" pattern="￥0.00元" /></font> <br/>
</div>
<div class="mt"><span>收货人信息  </span></div>
<div class="bx1">
               收货人:  ${fn:escapeXml(af.map.buyer_name)}<br/>
               地址: ${p_index_name} &nbsp;${fn:escapeXml(af.map.buyer_addr)}<br/>
               手机: ${fn:escapeXml(af.map.buyer_mp)} <br/>
               固定电话： ${fn:escapeXml(af.map.buyer_tel)}<br/>
</div>
<div class="mt"><span>配送信息  </span></div>
<div class="bx1">
              配送方式: <c:if test="${af.map.logistic_type eq 1}">顺丰</c:if><c:if test="${af.map.logistic_type eq 2}">EMS</c:if><br/>
              送货日期: <c:if test="${af.map.deliver_time eq 0}">只工作日送货（双休日、假日不用送）</c:if><c:if test="${af.map.deliver_time eq 1}">工作日、双休日与假日均可送货</c:if> <c:if test="${af.map.deliver_time eq 2}">只双休日、假日送货（工作日不送货）</c:if><br/>
             发货前电话确认：<c:if test="${af.map.deliver_is_call eq 0}">否</c:if><c:if test="${af.map.deliver_is_call eq 1}">是</c:if><br/>
</div>
<div class="mt"><span>发票信息  </span></div>
<div class="bx1">
              是否开具发票：<c:if test="${af.map.bill_is_add eq 0}">否</c:if><c:if test="${af.map.bill_is_add eq 1}">是</c:if> <br/>
              发票类型： <c:if test="${af.map.bill_type eq 0}">普通发票</c:if><c:if test="${af.map.bill_type eq 1}">增值税发票</c:if><br/>
              发票抬头：<c:if test="${af.map.bill_head eq 0}">个人</c:if> <c:if test="${af.map.bill_head eq 1}">单位　${fn:escapeXml(af.map.bill_company)}  </c:if><br/>
</div>        
<div class="mt" style="color:#c40000"><span>订单商品</span></div>
<div class="bx1"><c:forEach items="${pshowOrdeDetails}" var="cur">
	<p> ${fn:escapeXml(cur.pd_name)} </p> 
	<p>单价：<span class="ftx31"> <font color="red" style="font-family:Arial;font-weight:bold"><fmt:formatNumber value="${fn:escapeXml(cur.price)}" pattern="￥0.00" /></font>   </span>&nbsp;&nbsp;数量：${cur.num}</p>
	</c:forEach>
</div>
<div class="clear"></div>
</div>
</div>
</div>
<!-- six footer -->
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){ 
var id = "${af.map.id}";
$("#syncBtn").click(function(){
$("#s1").empty();
$("#syncBtn").attr("value", "正在查询...").attr("disabled", "true");
$.ajax({
	type: "post",url: "${ctx}/webservice/wap/center/Orders.do?method=sfList",data: {"id" : id},dataType: "json",
	error: function(request, settings) {}, 
	success: function(data) {
			for(var i = 0 ;i < data.length ;i++){
				var span = $("<span>"+data[i].accept_time+","+data[i].remark+"</span><br/>");
				span.appendTo($("#s1"));
				$("#syncBtn").removeAttr("disabled").attr("value","实时查询");
			}
		}
	});
});	
$("#li1").click(function(){
	$("#li1").addClass("curli");
	$("#li2").removeClass("curli");
	$("#tbl1").show();
	$("#tbl2").hide();
});
$("#li2").click(function(){
	$("#li2").addClass("curli");
	$("#li1").removeClass("curli");
	$("#tbl2").show();
	$("#tbl1").hide();
});		
});

function showNav(){  
	if(document.getElementById("jdkey").style.display=='none'){ 
		document.getElementById("jdkey").style.display='block';
	}else{
		document.getElementById("jdkey").style.display='none';
	} 
}
//]]></script>
</body>
</html>
