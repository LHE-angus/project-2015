<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" /> 
<title>触网</title> 
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/global.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/epp_index.css" /> 
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/shoping.css" /> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script>  
</head>
<body onload="checkSelect();"><div class="top_class"><span class="lspan"><a href="javascript:void(0);history.back();"><img src="${ctx}/styles/epp/mobile/images/return.gif" /></a></span><h3>购物车</h3><a href="javascript:void(0);showNav();" id="btnJdkey" class="new-a-jd"><span>触网</span></a>
	<div class="new-jd-tab" style="top:45px;display:none;" id="jdkey">
		<div class="new-tbl-type">
		<a href="<c:url value='/webservice/wap/Index.do' />" class="new-tbl-cell"><span class="icon">首页</span><p style="color:#6e6e6e;">首页</p>	</a>
		<a href="<c:url value='/webservice/wap/ProdType.do' />" class="new-tbl-cell"><span class="icon2">产品分类</span><p style="color:#6e6e6e;">产品分类</p></a>
		<a href="<c:url value='/webservice/wap/ShoppingCar.do' />" id="html5_cart" class="new-tbl-cell"><span class="icon3 on">购物车</span><p style="color:#6e6e6e;" class="on">购物车</p></a>
		<a href="<c:url value='/webservice/wap/center/Index.do' />" class="new-tbl-cell"><span class="icon4">会员中心</span> <p style="color:#6e6e6e;">会员中心</p></a>
		</div>
	</div>
</div>
<div id="content" >
<span style="font-size:12px;">当前地区:${p_name }</span>
	<div class="mainbox">
		<div class="maincont">    
		<form action="<c:url value='/webservice/wap/ShoppingCar.do' />" method="post" id="shopping_car_form">
		<input type="hidden" name="method" value="stepTwo" />
		<input type="hidden" name="p_index" value="${p_index}" /><c:forEach items="${konkaBcompPdList}" var="cur" varStatus="vs">
		<div class="shoppingcart"  id="product_${cur.id}">
			<input type="hidden" name="select_goods" id="select_goods_${cur.id}" value="${cur.id}"  />
			<input type="hidden" name="goods_id" id="goods_${cur.id}" value="${cur.id}"  />		
			<input type="hidden" name="price_${cur.id}" id="price_${cur.id}" value="${cur.ecGoodsPrice.price}"  />
			<input type="hidden" name="pd_name_${cur.id}" value="${cur.pd_name}"  />
			<input type="hidden" name="service_ids_${cur.id}" id="service_ids_${cur.id}" value="${cur.map.service_ids}" />
			<input type="hidden" name="store_id_${cur.id}" value="${cur.ecStocks.store_id}"  />
			<input type="hidden" name="store_num_${cur.id}" id="store_num_${cur.id}" value="${cur.ecStocks.stocks}"  />
			<div class="shoppingcart_l"><div class="shoppingcart_pic"><img alt="${cur.pd_name}" src="${ctx}/${fn:substringBefore(cur.main_pic, '.')}_350.jpg"  style="marign-left:1px;width:72px;" /></div></div>
		    <div class="shoppingcart_r">
		      <div class="shoppingcart_name"> ${fnx:abbreviate(cur.pd_name, 2 * 34, '...')} </div>
		      <div class="shoppingcart_price">价格：￥<fmt:formatNumber value="${cur.ecGoodsPrice.price}" pattern="#,#00" /></div>
		      <div class="shoppingcart_service"><c:if test="${not empty cur.serviceIds}">增值服务：<c:forEach items="${cur.ecBindingPdListForService}" var="cur1"><c:forEach items="${cur.serviceIds}" var="cur2"><c:if test="${cur1.id eq cur2}">
			  &nbsp;&nbsp;<br/><font color="red">${cur1.goods_name} ￥<fmt:formatNumber value="${cur1.price}" pattern="#,#00" /></font> <input type="hidden" name="service_${cur.id}" value="${cur1.price}"  />
			  </c:if></c:forEach></c:forEach></c:if></div>
		      <div class="shoppingcart_amount">
		        	 数量 <img src="${ctx}/styles/wap/images/shoppingcar_minus.jpg" onclick="buyNum('buy_num_${cur.id}',-1);" style="display:inline;cursor:pointer;"/> 
		               <input class="shoppingcart_input" type="text" name="buy_num_${cur.id}" maxlength="3" id="buy_num_${cur.id}" value="${cur.ecShoppingCart.pd_num}" onfocus="javascript:setOnlyInt(this);" onblur="javascript:setOnlyInt(this);" /> 
		               <img src="${ctx}/styles/wap/images/shoppingcar_plus.jpg"  onclick="buyNum('buy_num_${cur.id}',1);" style="display:inline;cursor:pointer;"/> 
		            <c:if test="${cur.map.xsqy_flg eq 1 }"><span style="font-size:11px;color:red;">该产品不在销售区域</span></c:if>
		            <div id="div_stocks_${cur.id}"><c:if test="${not empty cur.ecStocks.stocks and cur.ecStocks.stocks ge cur.ecShoppingCart.pd_num}"><font class="stocks_flag">现货，下单后发货！</font></c:if>
				    <c:if test="${empty cur.ecStocks.stocks  or cur.ecStocks.stocks lt cur.ecShoppingCart.pd_num}"><font color="red">抱歉，暂时缺货！</font></c:if>
					</div>
		      </div>
		    </div>
		</div></c:forEach>
		<div class="shoppingcart_total"><ul><li>商品数量：	<span  id="g_num">0 </span> </li> <li>总计： <font class="red" id="g_price" >￥00</font></li></ul></div>
		</form>
		</div> 
	<div class="clear"></div>
	</div>
<div class="clear"></div>
</div>
<div class="shoppingcart_js"><input name="" type="button" class="shoppingcart_accounts"  value="清空" onclick="productDel();" style="width:30%;margin-left:15%;"/><input  style="width:30%;margin-left:15%;" name="" type="button" class="shoppingcart_accounts" id="btn_buy"  value="去结算" onclick="btnSumbt();" /></div>
<script type="text/javascript">//<![CDATA[
var xmlHttp; 
var url;
function createXMLHttpRequest() {
    if (window.ActiveXObject) {  xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");  }else if (window.XMLHttpRequest) { xmlHttp = new XMLHttpRequest();}
}                                          
var flg=true;                                          
//结算
function btnSumbt(){ 
	//更新页面
	 //checkSelect();
	 var v=0;
	 var select_goods =document.getElementsByName("select_goods");
	 var goods_id =document.getElementsByName("goods_id");
	 var remove_ids = "";
	 for(var i=0;i<goods_id.length;i++){ 
		 var id=select_goods[i].value;
		 if(goods_id[i].value==""){ 
			 if(remove_ids ==""){
				 remove_ids+=goods_id[i].id;
			 }else{
			 remove_ids+=","+goods_id[i].id;
			 }			 
		 }else{ 
			 v++; 
			 var buy_num =  parseInt(document.getElementById("buy_num_"+id).value);
			 //判断库存
		     var store_num =  parseInt(document.getElementById("store_num_"+id).value);		 
			 if(buy_num > store_num||document.getElementById("store_num_"+id).value==''){ 
				 alert("提示，有商品库存不足，不能提交结算请求！");
				 return false;
			 }
		 }
		 
	 }
	 if(v==0){
		 flg = false;
		 alert("您没有选择商品，请先选择结算商品！");
		 return false;
	 }
	 <c:if test="${not empty xsqy_flg and xsqy_flg eq 1}">
		alert("提示，购物车中有商品不在销售区域！");
		return false;
	</c:if>
	 if(flg){ 
		 flg = false;
		 addCss("btn_buy","gray");
		 document.getElementById("btn_buy").disabled; 
		 if(remove_ids!=""){//未选择的商品不提交，清空未选择的商品
			var ids= remove_ids.split(",");
			for(var r=0;r<ids.length;r++){
				removeObj(ids[r]);
			}
		 } 
		 document.forms[0].submit(); 
	 }
}
                                          
function checkSelect(){
	 var all_select=true; 
	 var all_price=0.0;
	 var all_num=0;
	 
	 var select_goods =document.getElementsByName("select_goods");
	 var goods_id =document.getElementsByName("goods_id");
	 for(var i=0;i<goods_id.length;i++){
		 var id=select_goods[i].value;
		 var btn_pd="btn_pd_"+id;
		 if(goods_id[i].value==""){
			 addCss(btn_pd,"gray");
			 all_select=false;
		 }else{
			 removeCss(btn_pd,"gray");
			 //计算总价、总数量
			 var service_s=document.getElementsByName("service_"+id);
			 var buy_num =  parseInt(document.getElementById("buy_num_"+id).value);
			 var price =  parseFloat(document.getElementById("price_"+id).value);
		     var service_price =0;
		     if(service_s!=null&&service_s.length>0){
		    	 for(var z=0;z<service_s.length;z++){
		    		 service_price +=parseFloat(service_s[z].value); 
		    	 } 
		     }
		     all_price += (price+service_price)*buy_num;
		     all_num+=buy_num;
		     
		     //判断库存
		     var store_num =  parseInt(document.getElementById("store_num_"+id).value);		     
			 if(buy_num > store_num){
				document.getElementById("div_stocks_"+id).innerHTML = "<font color='red' >抱歉，暂时缺货！</font>";
			 }else{
			 	document.getElementById("div_stocks_"+id).innerHTML = "<font class='stocks_flag'>现货，下单后发货！</font>";
			}
		 }
	 }
	 //全选按钮
	 if(all_select){
		 removeCss("btn_select","gray"); 
	 }else{
		 addCss("btn_select","gray");
	 }
	 
	 document.getElementById("g_price").innerHTML = "￥"+all_price;
	 document.getElementById("g_num").innerHTML =all_num;
}

function productDel(){ 
if(confirm("确定清空购物车吗？")){
 var goods_id =document.getElementsByName("goods_id"); 
 var ids="";
	 for(var i=0;i<goods_id.length;i++){
		if(goods_id[i].value!=""){
			ids=ids+","+goods_id[i].value;
		}
	 } 
	url="<c:url value='/webservice/wap/ShoppingCar.do' />";
	var method="ajaxDelCar";
	var timestamp=new Date().getTime();
	url=url+"?method="+method+"&goods_ids="+ids+"&timestamp="+timestamp;	 
	createXMLHttpRequest();
	if(!xmlHttp){  return alert('create failed'); }
	xmlHttp.open("POST", url, true);
	xmlHttp.onreadystatechange = function(){
		if(xmlHttp.readyState == 4){ if(xmlHttp.status == 200){  alert("删除成功");
				location.href="<c:url value='/webservice/wap/ShoppingCar.do' />";
				 var n=ids.split(",");
				 for(var i=0;i<n.length;i++){ 
					removeObj("product_"+n[i]); 
				 }
				 checkSelect();
		} }
	}
	xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	if (window.XMLHttpRequest){ xmlHttp.send(null); }else if (window.ActiveXObject){ xmlHttp.send(); } 
	
} 
}

function selectPd(v){
   var select_goods_ = document.getElementById("select_goods_"+v);
   var goods_ = document.getElementById("goods_"+v);
   if(goods_.value ==""){
	   goods_.value=select_goods_.value; 
   }else{
	   goods_.value=""; 
   } 
   checkSelect();
}

function selectAll(){
	 var all_select=true;
	 var select_goods =document.getElementsByName("select_goods");
	 var goods_id =document.getElementsByName("goods_id");
	 for(var i=0;i<goods_id.length;i++){
		 if(goods_id[i].value==""){
			 all_select=false;
		 }
	 }
	 if(all_select ==false){
		 for(var i=0;i<goods_id.length;i++){
			goods_id[i].value= select_goods[i].value;
		 }
	 }else{
		 for(var i=0;i<goods_id.length;i++){
				goods_id[i].value= "";
		 }
	 }
	 checkSelect();
}
   
//添加css                                          
function addCss(obj,css){	
	if(document.getElementById(obj)!=null){	
	var old_css = document.getElementById(obj).className; 
	if(old_css==null||old_css.indexOf(css)<0){
	old_css +=" " + css;
	document.getElementById(obj).className = old_css;
	}
	}
}      
//删除css
function removeCss(obj,css){	
	if(document.getElementById(obj)!=null){	
	var old_css = document.getElementById(obj).className; 
	if(old_css!=null&&old_css.length>1){
	old_css = old_css.replace(css,""); 
	old_css = old_css.replace("  ",""); 
	document.getElementById(obj).className = old_css;
	}
	}
}
//删除元素
function removeObj(obj){
	var o =document.getElementById(obj);
	if(o!=null&&o.parentNode!=null){
	o.parentNode.removeChild(o);
	}
}
//正则表达式：只能输入数字
function setOnlyInt(obj) {   
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
		var v =parseInt(obj.value);
		if(v>999) v=999;
		if(v<1) v=1;
		obj.value=v; 
		checkSelect();
} 

function buyNum(obj,num){
	var	o=document.getElementById(obj);
	var v =parseInt(o.value)+num;
	if(v>999) v=999;
	if(v<1) v=1;
	o.value=v;
	checkSelect();
}

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
