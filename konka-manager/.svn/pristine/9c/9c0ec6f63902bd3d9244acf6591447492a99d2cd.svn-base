<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康佳产品展示平台</title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/pshow/css/global.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/pshow/css/sale.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/pshow/css/order.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/customer/pshow/easydialog/easydialog.css" />
<script type="text/javascript" src="${ctx}/styles/customer/pshow/easydialog/easydialog.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/pshow/easydialog/easydialog.min.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<!-- head start -->
<div id="topbox">
  <jsp:include page="/customer/PShow/_inc/_top2.jsp" flush="true" />
  <jsp:include page="/customer/PShow/_inc/_head2.jsp" flush="true" />
</div>
<!-- head end --> 
<!-- topnav start -->
<jsp:include page="/customer/PShow/_inc/_head_nav22.jsp" flush="true" />
<!-- topnav end --> 

<!-- third start -->
<div class="maincont">
	<form action="${ctx}/Payment.do" method="post" target="_blank">
		<input type="hidden" name="method" value="pay" />
		<input type="hidden" name="trade_index" value="${trade_index}" />
		<div class="content">
			<div class="xinxi">
				<h2>
					<span>${ordersNum}笔订单：<a href="${ctx}/Payment.do?method=view&trade_index=${trade_index}" target="_blank">详单</a></span>
					<b><strong id="tot_money_1"><fmt:formatNumber value="${total_money}" type="currency" /></strong>&nbsp;元</b>
				</h2>
				
				<div class="xx_list">
					<div class="mscz">
						<b>核对无误，确认支付</b>
				        <span>待付款总额为：<i id="tot_money_2"><fmt:formatNumber value="${total_money}" type="currency" /></i>元</span>
					</div>
					<ul class="ycts">
				      <b>友情提示：</b>
				      <li>·请正确评估货的质量，一分钱一分货，不要对图片精美的低价抱高质量幻想；</li>
				      <li>·请通过具有担保功能的银联、支付宝付款，切勿绕过康佳商城私下交易，很容易被骗！</li>
				      <li>·避免与卖家的纠纷请阅读：<a href="#">《退换货规则》</a></li>
				    </ul>
				</div>
				
				<div class="conzi">
				  <h3>请选择支付方式：</h3>
				  <div class="conzi_box">
				   
				    <h4>网上银行</h4>
				    <ul class="wangyin">
			    		<li>
				        	<input type="radio" name="pay_way" id="pay_way_3" value="3" />
				        	<label for="pay_way_3"><img src="${ctx}/styles/customer/pshow/images/unionpay2.jpg" width="135" height="45" style="vertical-align:middle;cursor:pointer;" /></label>
				      	</li>
				    </ul>
				
				    <h4>支付宝</h4>
				    <ul class="wangyin">
				      	<li>
				        	<input type="radio" name="pay_way" id="pay_way_2" value="2" />
				        	<label for="pay_way_2"><img src="${ctx}/styles/customer/pshow/images/alipay.gif" width="135" height="45" style="vertical-align:middle;cursor:pointer;" /></label>
				      	</li>
				    </ul>
				    <input type="button" id="pay_btn" name="pay_btn" class="s_btn" value="下一步" onclick="pay(this.form)" />&nbsp;
				    <span style="position: relative;">
				    	<span id="payWay_Tip" class="action1">
				    		<span class="action_po">
				    			<span class="action_po_top">请选择支付方式！<br />&nbsp;</span>
				    			<span class="action_po_bot"></span>
				    		</span>
				    	</span>
				    </span>
				  </div>    
				</div>
			</div>
		</div>
	</form>
  <div class="clear"></div>
</div>
<!-- third end --> 

<!-- six start -->
<div class="maincont"> 
  <!-- footer start-->
  <jsp:include page="/customer/PShow/_inc/_footer2.jsp" flush="true" />
  <!-- footer end--> 
</div>
<!-- six end --> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

});
function pay(f){
	var pay_way = $("input[type='radio'][name='pay_way']:checked").val();
	if (pay_way == undefined || pay_way == "") {
		$("#payWay_Tip").show();
	} else {
		$("#payWay_Tip").hide();
		
		easyDialog.open({
		  container : {
		    header : '&nbsp;',
		    content : '<table>' +
			    		'<tr>' +
		        			'<td width="54" align="center"><img src="${ctx}/styles/customer/pshow/images/close3.jpg" width="30" height="30"></td>' +
		        			'<td colspan="2" align="left"><font class="hei16px">请您在新打开的页面完成付款</font></td>' +
		      			'</tr>' +
		      			'<tr>' +
		        			'<td height="51">&nbsp;</td>' +
		        			'<td colspan="2" align="left"><font class="hei14px">付款前请不要关闭此窗口<br>完成付款后请根据您的情况点击下面按钮：</font></td>' +
		      			'</tr>' +
		      			'<tr>' +
		        			'<td height="54">&nbsp;</td>' +
		        			'<td width="94" align="left"><img src="${ctx}/styles/customer/pshow/images/close1.jpg" width="91" height="35" style="cursor:pointer" onclick="checkOrderStatus()"></td>' +
		        			'<td width="202" align="left"><img src="${ctx}/styles/customer/pshow/images/close2.jpg" width="111" height="35" style="cursor:pointer" onclick="gotoHelp()"></td>' +
		      			'</tr>' +
		      			'<tr>' +
		        			'<td height="38">&nbsp;</td>' +
		        			'<td colspan="2" align="left"><font class="hui16px"><a href="#" onclick="easyDialog.close();">&lt;&lt; 返回选择其他方式支付</a></font></td>' +
		        		'</tr>' +
		    		  '</table>',
		    fixed : false
		  }
		});

		f.submit();
	}
}
function gotoHelp(){
	//location.href = "${ctx}/Payment.do?method=gotoHelp";
}

function checkOrderStatus(){
	location.href = "${ctx}/Payment.do?method=checkOrderStatus&trade_index=${trade_index}";
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
