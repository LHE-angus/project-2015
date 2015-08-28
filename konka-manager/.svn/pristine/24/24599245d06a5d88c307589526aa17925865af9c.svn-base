<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:if test="${ecUser.user_type eq 1 }">康佳EPP内部优惠购机平台</c:if><c:if test="${ecUser.user_type eq 2 }">康佳网上直销商城</c:if></title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/global1.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/epp.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/shoppingcar.css" />
<style type="text/css">
	.bank {float: left;width: 145px;height: 40px;margin-left: 5px;border: 1px solid #DDD;background-image: url('${ctx}/images/pay.png');background-repeat: no-repeat;cursor: pointer;text-indent: -9999px;}
	.bank--alipay {background-position: 0 -1023px;}	
	.bank--tenpay {background-position: 0 -1062px;}	
	.bank--unionpay{background-position: 0 -1333px;}
	.bank--epay{padding-top:3px;float: left;width: 145px;height: 40px;margin-left: 5px;border: 1px solid #DDD;background-image: url('${ctx}/images/epay.png');background-repeat: no-repeat;cursor: pointer;text-indent: -9999px;}	
	.bank--credit_card{padding-top:3px;float: left;width: 145px;height: 40px;margin-left: 5px;border: 1px solid #DDD;background-image: url('${ctx}/images/credit_card.jpg');background-repeat: no-repeat;cursor: pointer;text-indent: -9999px;}	
	.bank--bank_card{padding-top:3px;float: left;width: 145px;height: 40px;margin-left: 5px;border: 1px solid #DDD;background-image: url('${ctx}/images/bank_card.jpg');background-repeat: no-repeat;cursor: pointer;text-indent: -9999px;}	
	.bank--hdfk{padding-top:3px;float: left;width: 145px;height: 40px;margin-left: 5px;border: 1px solid #DDD;background-image: url('${ctx}/images/hdfk.jpg');background-repeat: no-repeat;cursor: pointer;text-indent: -9999px;}
	
	
.login_div{position: absolute;	border: solid 1px #d1e3f5;top:110%;text-align: center;border:1px solid #E03425;background: #f5f4f4;left:30%;width:550px;padding-bottom: 0px;padding-top: 0px;display: none;z-index:10002;} 
fieldset {text-align:left;padding:10px;margin-top:5px; background:#fff;}
fieldset legend {color:#1E7ACE;font-weight:bold;padding:3px 20px 3px 20px;border:1px solid #E03425;background:#fff;}
fieldset label {float:left;width:100px;text-align:right;padding:4px;margin:1px;}
fieldset div {clear:left;margin-top:15px;margin-bottom:18px;}
fieldset .buttom {width:44px; padding:1px 10px; margin-left:10px;font-size:12px;border:1px #1E7ACE solid;background:#D0F0FF;}
#login_bg_div {display: none; position: absolute; top: 0%; left: 0%; width: 100%; height: 100%; background-color: black; z-index:10000; -moz-opacity: 0.7; opacity:.70; filter: alpha(opacity=60);}
fieldset p {line-height:24px;}	
 </style>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.jmpopups-0.5.1.js"></script>
</head>
<body>
<!-- head start -->
<div class="topbox">
<jsp:include page="/member/__inc/top.jsp" flush="true" />
<jsp:include page="/member/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/member/__inc/nav.jsp" flush="true" />

<!-- buy start -->
<form action="<c:url value='/member/LzPdBuy.do' />" method="post" id="step_two_form">
<input type="hidden" name="method" value="createOrder" />
<input type="hidden" name="p_index" value="${p_index}" id="p_index" />
<input type="hidden" name="buy_json_object" value="${buy_json_object}" />
<div class="card_center">
  <div class="card_a">我 的 购 物 车</div>
  <div class="card_b">
    <div class="card_dd">
      <ul>
        <li>1、我的购物车</li>
        <li><b>2、确认订单信息</b></li>
        <li>3、提交订单</li>
      </ul>
    </div>
  </div>
  <div class="shoppingcart_c">
  	 <h3 class="TitleName"><b>收货人信息</b></h3>
  	 <div class="HideBox"> 
      <table border="0" cellspacing="0" cellpadding="0" class="mytable" width="80%" style="height: 110px;">
        <tbody>
          <tr style="height:50px;line-height:10px;">
            <td class="item" width="15%" ><span class="modify_class" style="color:red;">*</span>收货人姓名： </td>
            <td  width="85%" >
            	<input id="rel_name" name="rel_name" type="text" maxlength="20" id="rel_name" class="input modify_class"  />
            </td>
          </tr>
          <tr>
            <td class="item"><span class="modify_class" style="color:red;">*</span>收货人手机号码： </td>
            <td>
            	<input name="rel_phone" type="text" maxlength="11" id="rel_phone" class="input modify_class" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" style="width:130px;ime-mode:Disabled;" />
              	<span class="modify_class" >或者</span> 固定电话：
                <input name="rel_tel" type="text" maxlength="20" id="rel_tel" class="input modify_class" style="width:130px;"  />
                <span class="modify_class">用于接收发货通知短信及送货前确认</span></td>
          </tr>
        </tbody>
      </table>
    </div>
   
    <h3 class="TitleName"> <b>支付配送信息</b></h3>
    <div class="HideBox">
      <table border="0" cellspacing="0" cellpadding="0" class="mytable" style="height: 110px;">
        <tbody> 
          <tr>
            <td class="item"><font color="red">*</font>在线支付： </td>
            <td><ul>
                <table border="0"> 
                    <tr>
                      <td align="center" valign="middle" width="18"><input type="radio" name="pay_way" value="2" id="pay_way_2" /></td>
                      <td align="left" valign="middle"><label class="bank bank--alipay"for="pay_way_2" style="cursor:pointer;">支付宝</label></td>
                      <td align="center" valign="middle" width="22"><input type="radio" name="pay_way" value="5" id="pay_way_5" /></td>
                      <td align="left" valign="middle"><label class="bank--credit_card" for="pay_way_5" style="cursor:pointer;">民生e支付</label></td>
                      <td align="center" valign="middle" width="22"><input type="radio" name="pay_way" value="5" id="pay_way_6" /></td>
                      <td align="left" valign="middle"><label class="bank--bank_card" for="pay_way_6" style="cursor:pointer;">民生e支付</label></td>
                     </tr> 
                </table>
              </ul> 
              </td>
          </tr>
        </tbody>
      </table>
    </div>
    <h3 class="TitleName"><b>商品清单</b></h3>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="mytable3">
      <tbody>
        <tr class="item">
          <td width="15%">商品编号</td>
          <td>商品名称</td>
          <td width="10%">商品数量</td>
          <td width="10%"><c:if test="${ecUser.user_type eq 1 }">内购价</c:if><c:if test="${ecUser.user_type eq 2 }">结算价</c:if></td>
          <td width="10%">物流费</td>
          <td width="10%">优惠</td>
          <td width="30%">增值服务</td>
        </tr><c:forEach items="${konkaBcompPdList}" var="cur">
	    <tr>
	          <td style="border-bottom:solid 1px #E6E6E6;">${cur.id}</td>
	          <td style="border-bottom:solid 1px #E6E6E6;">康佳${cur.pd_sn}</td>
	          <td style="border-bottom:solid 1px #E6E6E6;">${cur.map.buy_num}</td>
	          <td style="border-bottom:solid 1px #E6E6E6;">￥<fmt:formatNumber value="${cur.map.price}" pattern="#,#00" /></td>
	          <td style="border-bottom:solid 1px #E6E6E6;">
	          <c:if test="${cur.map.rule_price eq '0.00'}">无</c:if>
	      	  <c:if test="${cur.map.rule_price ne '0.00'}">
	      	￥<fmt:formatNumber value="${cur.map.rule_price}" pattern="#,#00" />/台
	      	  </c:if>
	          </td>
	          <td style="border-bottom:solid 1px #E6E6E6;">
	          <c:if test="${cur.map.rule_price_2 eq '0.00'}">无</c:if>
	      	  <c:if test="${cur.map.rule_price_2 ne '0.00'}">
	      	￥<fmt:formatNumber value="${cur.map.rule_price_2}" pattern="#,#00" />/台
	      	  </c:if>
	          </td>
	          <td valign="middle" style="text-align:left;border-bottom:solid 1px #E6E6E6;">
	          	 <c:if test="${empty cur.serviceIds}">没有选择增值服务</c:if>
	          	 <c:forEach items="${cur.ecBindingPdListForService}" var="cur1">
	          	 	<c:forEach items="${cur.serviceIds}" var="cur2">
	          	 		<c:if test="${cur1.id eq cur2}">${cur1.goods_name} ￥<fmt:formatNumber value="${cur1.price}" pattern="#0.00" />*${cur.map.buy_num}<br /></c:if>
	          		 </c:forEach>
	          	 </c:forEach>
	          </td>
	        </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
  <div class="clear"></div>
  <div class="but_ca">
    <ul>
      <li><a style="cursor:pointer;" id="step_two_btn">提交订单</a></li>
    </ul>
  </div>
</div>
</form>
<!-- buy end --> 

<!-- six footer -->
<jsp:include page="../__inc/footer.jsp" flush="true" />
<!-- six footer --> 

<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript">//<![CDATA[
var express_flg=0;        
var is_v=0;
$(document).ready(function(){
	is_v=0;
	$("#rel_name" ).attr("datatype", "Require").attr("msg", "请填写收货人姓名！");
	$("#rel_phone").attr("datatype", "Require").attr("msg", "请填写联系方式！");
	
	// 点击提交订单
	$(document).delegate("#step_two_btn", "click", function(){
		if(Validator.Validate($("#step_two_form")[0], 3)) {
			var pay_way = $("input[name='pay_way']:checked").val();
			if(undefined == pay_way){
				alert("提示，请选择支付方式！");
				return;
			}
			
			var msg = '${msg}';
			if(null!=msg&&""!=msg){
				if(!confirm(msg)){
					return false;
				}
			}
			$("#step_one_go_shopping").attr("disabled", true).html("提交中...");
			$("#step_two_btn").attr("disabled", true).html("提交中...");
			$("#step_two_form").submit();
		}
	});

});



//]]></script>
</body>
</html>