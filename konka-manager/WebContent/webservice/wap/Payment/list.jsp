<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" /> 
<title>触网</title> 
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/global.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/epp_index.css" />  
<script type="text/javascript" src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script> 
<style type="text/css">
.bank {float: left;width: 145px;height: 40px;margin-left: 25px;border: 1px solid #DDD;background-image: url('${ctx}/images/pay.png');background-repeat: no-repeat;cursor: pointer;text-indent: -9999px;}
.bank--alipay {background-position: 0 -1023px;}	
.bank--unionpay{background-position: 0 -1333px;} 
.bank--epay{float: left;width: 145px;height: 40px;margin-left: 25px;border: 1px solid #DDD;background-image: url('${ctx}/images/epay.png');background-repeat: no-repeat;cursor: pointer;text-indent: -9999px;}	
.bank--credit_card{float: left;width: 145px;height: 40px;margin-left: 25px;border: 1px solid #DDD;background-image: url('${ctx}/images/credit_card.jpg');background-repeat: no-repeat;cursor: pointer;text-indent: -9999px;}	
.bank--bank_card{float: left;width: 145px;height: 40px;margin-left: 25px;border: 1px solid #DDD;background-image: url('${ctx}/images/bank_card.jpg');background-repeat: no-repeat;cursor: pointer;text-indent: -9999px;}		  
</style>
</head>
<body><div class="top_class"><h3>在线支付</h3></div>
<div id="content" >
<div class="mainbox">
	<div class="maincont">    
<form action="<c:url value='/webservice/wap/Payment.do' />" method="post" id="pay_form" target="_blank">
  <input type="hidden" name="method" value="pay" />
  <input type="hidden" name="trade_index" value="${af.map.trade_index}" />
  <input type="hidden" name="pay_way" id="pay_way" value="${af.map.pay_way}" />
  <table style="width:100%;">
  	 <tr>
  		<td width="40%"><p style="margin-top:10px;font-size:14px;"> <b>请选择支付方式 </b></p>  </td >
  		<td width="60%" align="center"><p style="margin-top:10px;">稍后支付&gt;&gt;<a href="${ctx }/webservice/wap/center/Orders.do?method=list&orderState=1">查看我的订单</a></p></td>
	  </tr>
	  <tr><td colspan="2"><hr/></td></tr>
	  <c:if test="${is_daier ne 1}">
	  <tr>
	  	 <td height="55" colspan="2" align="center"> <label class="bank bank--alipay btn_sub" data="2">支付宝</label></td>
	  </tr></c:if>
	  <tr>
	  	 <td height="55"  colspan="2" align="center"> <label class="bank--credit_card btn_sub" data="5">民生E支付</label></td>
	  </tr>
	  <tr>
	  	 <td height="55"  colspan="2" align="center"> <label class="bank--bank_card btn_sub" data="5">民生E支付</label> </td>
	  </tr>
	  <tr>
	  	 <td colspan="2">
	  	 <div> <br/>请在新打开的页面完成付款 </div>  
  		 <hr /> 
  		 <p style="margin-top:10px;font-size:14px;line-height:24px;" >
  		 <b>温馨提示：</b><br/>
  		 <font color="#ff4400"> 支付宝用户请在支付宝入口进入 。 </font>
  		 </p>
  		</td>
  	 </tr>
  </table>
</form> 
<div id="loading_table" style="display:none;margin-top:10px;"  >
<table style="width:100%;">
  <tr>
    <td width="54" align="center"><img src="${ctx}/styles/customer/pshow/images/close3.jpg" width="30" height="30"/></td>
    <td colspan="2" align="left"><font class="hei16px">请您在新打开的页面完成付款</font></td>
  </tr>
  <tr>
    <td height="51">&nbsp;</td>
    <td colspan="2" align="left"><font class="hei14px">付款前请不要关闭此窗口<br/>
      完成付款后请根据您的情况点击下面按钮：</font></td>
  </tr>
  <tr>
    <td height="54">&nbsp;</td>
    <td width="94" align="left"><img src="${ctx}/styles/customer/pshow/images/close1.jpg" width="91" height="35" style="cursor:pointer" id="heckOrderStatus" /></td>
    <td width="202" align="left"><img src="${ctx}/styles/customer/pshow/images/close2.jpg" width="111" height="35" style="cursor:pointer" id="gotoHelp" /></td>
  </tr>
  <tr>
    <td height="38">&nbsp;</td>
    <td colspan="2" align="left"><font class="hui16px"><a href="javascript:void(0)" style="cursor:pointer;" id="closeBtn">&lt;&lt; 返回选择其他方式支付</a></font></td>
  </tr>
</table> 
</div> 
</div>
</div>
</div>
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=idialog"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){ 
	var table = $("#loading_table").html(); 
	var load_data; 
	// 点击提交订单
	$(document).delegate(".btn_sub", "click", function(){
		$("#pay_way").val($(this).attr("data"));		
		load_data = $.dialog({content: table , lock: true, max: false, min: false, title: '提示！'});
		$("#pay_form").submit();
	});
	
	// 判断是否有支付方式，如果有直接支付
	var pay_way = "${af.map.pay_way}";
	if("" != pay_way&&pay_way!=5){
		  var payname="";
		  if(pay_way==2){
			  payname=" 支付宝";
		  }else if(pay_way==3){
			  payname=" 银联";
		  }else if(pay_way==5){
			  payname="  民生e支付";
		  }
		  if(confirm('您选择的支付方式是'+payname+' 确定立即支付？\n点击"取消"选择其他支付方式。')){
			  load_data = $.dialog({content: table , lock: true, max: false, min: false, title: '提示！'});
 				$("#pay_form").submit();
		  }
	}
	
	// 点击关闭
	$(document).delegate("#closeBtn", "click", function(){ 
		load_data.close();
	});
	
	// 检查状态
	$(document).delegate("#heckOrderStatus", "click", function(){
		location.href = "${ctx}/webservice/wap/Payment.do?method=checkOrderStatus&trade_index=${af.map.trade_index}"
	}); 
	
});
//]]></script>
</body>
</html>