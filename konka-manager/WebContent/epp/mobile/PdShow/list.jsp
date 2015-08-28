<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" /> 
<title>开心猫</title> 
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/global.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/epp_index.css" /> 
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/pdshow.css" /> 
<link rel="stylesheet" type="text/css" href="${ctx}/styles/wap/css/citybox.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/idangerous.swiper.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/swiper.css" /> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script> 
<script src="${ctx}/styles/epp/mobile/js/idangerous.swiper-1.9.1.min.js"></script> 
<script src="${ctx}/styles/epp/mobile/js/idangerous.swiper.scrollbar-1.2.js"></script> 
<script src="${ctx}/styles/epp/mobile/js/swiper-demos.js"></script>
</head>
<body><div class="top_class"><span class="lspan"><a href="javascript:void(0);history.back();"><img src="${ctx}/styles/epp/mobile/images/return.gif" /></a></span><h3>商品详情</h3><a href="javascript:void(0);showNav();" id="btnJdkey" class="new-a-jd"><span>开心猫</span></a>
	<div class="new-jd-tab" style="top:45px;display:none;" id="jdkey">
	<div class="new-tbl-type">
		<a href="<c:url value='/epp/mobile/Index.do' />" class="new-tbl-cell"><span class="icon">首页</span><p style="color:#6e6e6e;">首页</p>	</a>
		<a href="<c:url value='/epp/mobile/ProdType.do' />" class="new-tbl-cell"><span class="icon2">产品分类</span><p style="color:#6e6e6e;">产品分类</p></a>
		<a href="<c:url value='/epp/mobile/ShoppingCar.do' />" id="html5_cart" class="new-tbl-cell"><span class="icon3 on">购物车</span><p style="color:#6e6e6e;" class="on">购物车</p></a>
		<a href="<c:url value='/epp/mobile/center/Index.do' />" class="new-tbl-cell"><span class="icon4">会员中心</span> <p style="color:#6e6e6e;">会员中心</p></a>
	</div>
	</div>
</div>
<form action="<c:url value='/epp/mobile/ShoppingCar.do' />" id="form_pd_buy" method="post">
  <input type="hidden" name="method" value="buyByGoodsId" />
  <input type="hidden" name="goods_id" id="goods_id" value="${konkaBcompPd.id}" />
  <input type="hidden" name="pd_name" id="pd_name" value="${konkaBcompPd.pd_name}" />
  <input type="hidden" name="price" id="price" value="${konkaBcompPd.ecGoodsPrice.price}" />
  <input type="hidden" name="original_price" id="original_price" value="${konkaBcompPd.ecGoodsPrice.original_price}" />
  <input type="hidden" name="p_index" id="p_index" value="${p_index}" />
  <input type="hidden" name="img_url" id="img_url" value="${konkaBcompPd.main_pic}" />
  <input type="hidden" name="had_stock_num" id="had_stock_num" value="${konkaBcompPd.ecStocks.stocks}" />
<div id="content" >
<div class="home-device"><a class="arrow-left" href="#"></a><a class="arrow-right" href="#"></a> 
	<div class="swiper-container swiper1">
		<div class="swiper-wrapper">
		 <c:if test="${not empty konkaBcompPd.picArray}">
	       <c:forEach items="${konkaBcompPd.picArray}" var="cur" varStatus="vs" begin="1"> 
	       <div class="swiper-slide"><img src="${ctx}/${fn:substringBefore(cur, '.')}.jpg"  width="100%"/></div>
		   </c:forEach>
	     </c:if>   
		</div>
	</div> 
	<div class="pagination pagination1"></div>
</div> 
<div class="mainbox">
	<div class="maincont"> 
    <c:if test="${ecUser.user_type eq 1 }">
             <tr>
              <td colspan="3" height="22"><p class="cor-f">${konkaBcompPd.pd_name}</p>会 员 价：<strong style="color:red;" id="price_tag">￥<fmt:formatNumber value="${konkaBcompPd.ecGoodsPrice.price}" pattern="#,#00" /></strong><img id="price_tag_loading" style="display:none;" src="${ctx}/images/loading5.gif" /></td>
            </tr>
            <tr>
              <td colspan="3" height="22">市 场 价：<font class="midtxt" id="original_price_tag">￥<fmt:formatNumber value="${konkaBcompPd.ecGoodsPrice.original_price}" pattern="#,#00" /></font><img id="original_price_tag_loading" style="display:none;" src="${ctx}/images/loading5.gif" /></td>
            </tr> 
    </c:if>
    <c:if test="${ecUser.user_type eq 2 }">
            <tr>
              <td colspan="3" height="22"><p class="cor-f">${konkaBcompPd.pd_name}</p>市场终端价：<font class="yel16b redfont" id="original_price_tag">￥<fmt:formatNumber value="${konkaBcompPd.ecGoodsPrice.original_price}" pattern="#,#00" /></font><img id="original_price_tag_loading" style="display:none;" src="${ctx}/images/loading5.gif" /></td>
            </tr>
     </c:if>
   <div class="out2" style="margin-top:8px; background:#FFF;" onclick="location.href='${ctx}/epp/mobile/EcPdEavl.do?goods_id=${konkaBcompPd.id}';">
      <div class="in"><table style="width:100%;"><tr><td><h3 class="font4c"><span class="font4span">好评率${score45 } % <img src='${ctx}/styles/epp/mobile/images/images07.gif'/></span>商品评价（${scoreCount }）</h3></td></tr></table></div>
   </div>
   <div class="out2" style="margin-top:8px; background:#FFF;" onclick="location.href='${ctx}/epp/mobile/PdContent.do?goods_id=${konkaBcompPd.id}&type=1';">
      <div class="in"><table style="width:100%;"><tr><td><h3 class="font4c"><span class="font4span"><img src='${ctx}/styles/epp/mobile/images/images07.gif' /></span>图文详情</h3></td></tr></table></div>
   </div>
   <div class="out2" style="margin-top:8px; background:#FFF;" onclick="location.href='${ctx}/epp/mobile/PdContent.do?goods_id=${konkaBcompPd.id}&type=2';">
   		<div class="in"><table style="width:100%;"><tr><td><h3 class="font4c"><span class="font4span"><img src='${ctx}/styles/epp/mobile/images/images07.gif' /></span>规格参数</h3></td></tr></table></div>
   </div>
   <div class="out2" style="margin-top:8px; background:#FFF;">
        <div class="in">
            <div class="cont1 font14">送至 <input onclick="showArea();" id="p_full_name" class="sendinput"  value="${p_full_name}" readonly="readonly" name="p_full_name" />
				<span id="stock" style="float:right;"><span id="had_stock" style="display:${konkaBcompPd.ecStocks.stocks > 0 ? '' : 'none'};">现货&nbsp;<c:if test="${empty konkaBcompPd.ecBaseExpressReachDay}">付款后立即发货！</c:if><c:if test="${not empty konkaBcompPd.ecBaseExpressReachDay}">付款后预计${konkaBcompPd.ecBaseExpressReachDay.max_reach_day}天送达</c:if></span><span id="no_stock" style="display:${empty konkaBcompPd.ecStocks.stocks ? '' : 'none'};">所选商品暂时无货，非常抱歉！</span></span><img id="stocks_loading" style="  display: none;" src="${ctx}/images/loading5.gif" />
	            <div style="position: relative;top: -235px; width: 345px;z-index: 99999; font: normal 12px 宋体;"><div style="margin-left: -10px; top: 0px; display: none; width: 340px;" id="more_province">
			            <table style="width:340px; top:0px;border: 1px #F60 solid; background: #FFF;"> 
			                <tr><td height="26"><table class="dizhi_nav" style="width:100%"><tr><td class="addr_td1" width="29%" id="addr_province" data="${province_index}" lang="${province_name}" onclick="showProvince();"><span>${province_name}</span></td><td class="addr_td2" width="28%" id="addr_city" data="" lang="" onclick="showCity();">&nbsp;</td><td class="addr_td2" width="28%" id="addr_county" data="">&nbsp;</td><td class="addr_td2" width="15%"><span id="more_province_close" style="cursor: pointer;" onclick="hideArea();">【关闭】</span></td></tr></table></td></tr>
			                <tr><td height="107" align="left" valign="top" style="padding-left: 20px; padding-bottom: 10px;"><dl class="dizhibox" id="province_dl"><c:forEach items="${baseProvinceListFourList}" var="cur"><c:if test="${fn:length(cur.p_name) ge 5}"><c:if test="${cur.p_index eq province_index}"><dd class="text_long"><a class="a_province" data="${cur.p_index}" onclick="javascript:getAjaxCity(${cur.p_index},this);">${cur.p_name}</a></dd></c:if><c:if test="${cur.p_index ne province_index}"><dd class="text_long"><a class="a_province" data="${cur.p_index}" onclick="javascript:getAjaxCity(${cur.p_index},this);">${cur.p_name}</a></dd></c:if></c:if><c:if test="${fn:length(cur.p_name) lt 5}"><c:if test="${cur.p_index eq province_index}"><dd><a class="a_province" data="${cur.p_index}" onclick="javascript:getAjaxCity(${cur.p_index},this);">${cur.p_name}</a></dd></c:if><c:if test="${cur.p_index ne province_index}"><dd><a class="a_province" data="${cur.p_index}" onclick="javascript:getAjaxCity(${cur.p_index},this);">${cur.p_name}</a></dd></c:if></c:if></c:forEach></dl><dl class="dizhibox" id="city_dl" style="display: none;"></dl><dl class="dizhibox" id="county_dl" style="display: none;"></dl><img src="${ctx}/images/loading45.gif" style="display: none;" id="img_loading" /></td></tr> 
			            </table></div>
			     </div>
           </div>
     	</div>
   </div>      
   <div class="out2" id="choose-amount" style="margin-top:8px; background:#FFF;">
         <div class="padding5"><div id="choose-amount"><div class="dt">数量：</div><div class="dd"><div class="wrap-input"><a class="btn-reduce" onclick="buyNum(-1);"  href="javascript:void(0);">减少数量</a><input type="text" name="buy_num" id="buy_num" class="text" onkeyup="setAmount.modify('#buy-num');" value="1" onfocus="javascript:setOnlyInt(this);" onblur="javascript:setOnlyInt(this);"><a class="btn-add" onclick="buyNum(1);" href="javascript:vod(0);">增加数量</a></div></div></div></div>  
   </div>
   <div class="out2" style="margin-top:8px;margin-bottom:50px;background:#FFF;">
          <div class="in"><table style="width:100%;font-family: 'Microsoft YaHei'; font-size: 14px; line-height: 30px;"><tr><td height="30" align="center" style="width:63px;">服务</td><td><c:if test="${not empty konkaBcompPd.ecBindingPdListForService}"><c:forEach items="${konkaBcompPd.ecBindingPdListForService}" var="cur"><input name="service" type="checkbox" id="service_${cur.id}" value="${cur.id}" onclick="selectService('${cur.id}');"/><label for="service_${cur.id}" style="cursor: pointer; margin-left: 5px;" onclick="selectService('${cur.id}');"><font color="red">${cur.goods_name} ￥<fmt:formatNumber value="${cur.price}" pattern="#,#00" /></font></label><br /></c:forEach></c:if></td></tr></table></div>
    </div> 
   	<div class="clear"></div>
    </div>
	<div class="clear"></div>
</div>  
</div>
</form>
<div class="qb_flex" style="width:100%;position: fixed; bottom: 0px; z-index: 10; display: table; text-align:center; "><input type="button" id="add-cart" value="加入购物车" class="mod_btn btn_block btn_em qb_mr10 flex_box" onclick="addShopingCar();"/><input type="button" id="buy-now" value="立即购买" class="mod_btn btn_block btn_strong flex_box" onclick="buy();" /></div> 
<script type="text/javascript">//<![CDATA[
var xmlHttp;
var url;
function createXMLHttpRequest(){
if (window.ActiveXObject) { xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");  }else if (window.XMLHttpRequest) { xmlHttp = new XMLHttpRequest();}
}

function writeCookie(name, value, hours){
var expire = "";
	  if(hours != null){
	    expire = new Date((new Date()).getTime() + hours * 3600000);
	    expire = "; expires=" + expire.toGMTString();
	  }
	  document.cookie = name + "=" + escape(value) + expire;
}

/**
* readCookie
* @param name cookie name
* @useage readCookie("myCookie");
*/
function readCookie(name){
var cookieValue = "";
var search = name + "=";
if(document.cookie.length > 0){
 offset = document.cookie.indexOf(search);
 if (offset != -1){
	 offset += search.length;
	 end = document.cookie.indexOf(";", offset);
	 if (end == -1) end = document.cookie.length;
	 cookieValue = unescape(document.cookie.substring(offset, end));
 }
}
return cookieValue;
}

function buy(){	
	var had_stock_num = document.getElementById("had_stock_num").value;
	var buy_num = document.getElementById("buy_num").value;
	var price = document.getElementById("price").value;
	if(price ==""){ alert("提示：对不起，该地区产品暂未上架！"); return false; }
	if(parseInt(buy_num) > parseInt(had_stock_num)||had_stock_num==""){ alert("提示，对不起，您购买的商品库存不足，请联系客服！"); return false; } 
	document.forms[0].submit(); 
}

function selectService(v){
	var checkbox =document.getElementsByName("service");
	for(var i = 0; i < checkbox.length; i++){
		if(checkbox[i].value!=v){
			checkbox[i].checked=false;
		}
	}
}

//购物车
function addShopingCar(){
var service_ids = "";
var checkbox =document.getElementsByName("service");
	for(var i = 0; i < checkbox.length; i++){
		if(checkbox[i].checked==true){
			if(service_ids!=""){service_ids+="|";}
			service_ids +=checkbox[i].value ;
		}
	}
	var goods_id =document.getElementById("goods_id").value;
	var md_name = encodeURI(encodeURI(document.getElementById("pd_name").value)); 
	var buy_num = document.getElementById("buy_num").value;
	var price = document.getElementById("price").value;
	var img_url = document.getElementById("img_url").value;
	var p_index = document.getElementById("p_index").value;
	var had_stock_num = document.getElementById("had_stock_num").value;
	if(parseInt(buy_num) > parseInt(had_stock_num)||had_stock_num==""){ alert("提示，对不起，您购买的商品库存不足，请联系客服！"); return false; } 

	url="<c:url value='/epp/mobile/ShoppingCar.do' />";
	var method="ajaxAddCar";
	var data="&goods_id=" +goods_id + "&p_index="+p_index+"&md_name="+md_name+"&buy_num="+buy_num+"&price="+price+"&img_url="+img_url+"&service_ids="+service_ids;
	var timestamp=new Date().getTime();
	url=url+"?method="+method+data +"&timestamp="+timestamp;	
	createXMLHttpRequest();
    if(!xmlHttp){ return alert('create failed'); }
    xmlHttp.open("POST", url, true);
    xmlHttp.onreadystatechange = function(){
        if(xmlHttp.readyState == 4){
            if(xmlHttp.status == 200){ if(xmlHttp.responseText == '0'){ alert(xmlHttp.responseText); }else{ alert("恭喜，成功添加商品到购物车！"); }}
        }
    }
    xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded"); 
    if (window.XMLHttpRequest){ xmlHttp.send(null); }else if (window.ActiveXObject){ xmlHttp.send(); }
}

//加载 市
function getAjaxCity(p_index,o){
	showLoading(); 
	hideObj("province_dl");
	document.getElementById("addr_province").innerHTML="<span>"+o.innerText+"</span>"; 
	document.getElementById("addr_city").innerHTML="<span>请选择</span>"; 
	url="<c:url value='/CsAjax.do' />";
	var method="getPindexAndNameJsonObjectByParIndex";
	var par_index=p_index;
	var timestamp=new Date().getTime();
	url=url+"?method="+method+"&par_index="+par_index+"&timestamp="+timestamp;	
	createXMLHttpRequest();
    if(!xmlHttp){ return alert('create failed');}
    xmlHttp.open("POST", url, true);
    xmlHttp.onreadystatechange = function(){
        if(xmlHttp.readyState == 4){
            if(xmlHttp.status == 200){
            	var data=eval(xmlHttp.responseText);//获取json
            	if(data.length>0){
            		var span = "";
            		for(var i = 0; i< data.length; i++){ 
            			var dd = "";
    					if(data[i].p_name.length >= 5){
    						dd = '<dd class="text_long"><a class="a_city" data="' + data[i].p_index + '" onclick="getAjaxCounty('+data[i].p_index +',this)">' + data[i].p_name + '</a></dd>';
    					}else{
    						dd = '<dd><a class="a_city" data="' + data[i].p_index + '" onclick="getAjaxCounty('+data[i].p_index +',this)">' + data[i].p_name + '</a></dd>';
    					}
    					span=span+dd;
    				} 
            		document.getElementById("city_dl").innerHTML=span;
            		document.getElementById("addr_province").setAttribute("class","addr_td2");
            		document.getElementById("addr_city").setAttribute("class","addr_td1");
            		hideLoading();
            		showObj("city_dl");
          	}
         }
        }
    }
    xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded"); 
    if (window.XMLHttpRequest){xmlHttp.send(null);}else if (window.ActiveXObject){ xmlHttp.send();}
}
//加载 县
function getAjaxCounty(p_index,o){
	showLoading(); 
	hideObj("city_dl");
	document.getElementById("addr_city").innerHTML="<span>"+o.innerText+"</span>"; 
	document.getElementById("addr_county").innerHTML="<span>请选择</span>"; 
	url="<c:url value='/CsAjax.do' />";
	var method="getPindexAndNameJsonObjectByParIndex";
	var par_index=p_index;
	var timestamp=new Date().getTime();
	url=url+"?method="+method+"&par_index="+par_index+"&timestamp="+timestamp;	
	createXMLHttpRequest();
    if(!xmlHttp){return alert('create failed'); }
    xmlHttp.open("POST", url, true);
    xmlHttp.onreadystatechange = function(){
        if(xmlHttp.readyState == 4){
            if(xmlHttp.status == 200){
            	var data=eval(xmlHttp.responseText);//获取json 
            		var span = "";
            		for(var i = 0; i< data.length; i++){ 
            			var dd = "";
    					if(data[i].p_name.length >= 5){
    						dd = '<dd class="text_long"><a class="a_city" data="' + data[i].p_index + '" onclick="getProudct('+data[i].p_index +',this)">' + data[i].p_name + '</a></dd>';
    					} else {
    						dd = '<dd><a class="a_city" data="' + data[i].p_index + '" onclick="getProudct('+data[i].p_index +',this)">' + data[i].p_name + '</a></dd>';
    					}
    					span=span+dd;
    				} 
            		document.getElementById("county_dl").innerHTML=span;
            		document.getElementById("addr_city").setAttribute("class","addr_td1 addr_td2");
            		document.getElementById("addr_county").setAttribute("class","addr_td1");
            		hideLoading();
            		showObj("county_dl"); 
            }
        }
    }
    xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    if (window.XMLHttpRequest){ xmlHttp.send(null); }else if (window.ActiveXObject){xmlHttp.send(); }
}

//获取商品库存数量、 价格
function getProudct(p_index,obj){
    var goods_id = document.getElementById("goods_id").value; 
	// 地址回显
	var province =  document.getElementById("addr_province").innerText;
	var city =  document.getElementById("addr_city").innerText;
	document.getElementById("p_full_name").value=province + city + obj.innerText; 
	document.getElementById("p_index").value =p_index;
	hideArea();	 
	document.getElementById("price_tag").innerHTML=""; 
	//document.getElementById("price_tag_original").innerHTML=""; 
	showObj("price_tag_loading");
	//showObj("price_tag_original_loading");
	//hideObj("stock");
	showObj("stocks_loading");   
	
	url="<c:url value='/epp/mobile/PdShow.do' />";
	var method="ajaxGetKonkaBcompPdByGoodsIdAndPindex";	
	var timestamp=new Date().getTime();
	url=url+"?method="+method+"&goods_id="+goods_id+"&p_index="+p_index+"&timestamp="+timestamp;	
	createXMLHttpRequest();
    if(!xmlHttp){  return alert('create failed'); }
	xmlHttp.open("POST", url, true);
	xmlHttp.onreadystatechange = function(){
	     if(xmlHttp.readyState == 4){
	         if(xmlHttp.status == 200){ 
	        	 try{
	             var data=eval("["+xmlHttp.responseText+"]");//获取json  
	        	   document.getElementById("price_tag").innerHTML="￥"+data[0].price; 
	        	   document.getElementById("price").value = data[0].price;
	        	  // document.getElementById("price_tag_original").innerHTML="￥"+data[0].original_price; 
	        	   document.getElementById("original_price").value = data[0].original_price;
	        	   document.getElementById("had_stock_num").value = 0; 
	        		if(data[0].stocks > 0){
	        			 document.getElementById("had_stock_num").value = data[0].stocks; 
						if(data[0].max_reach_day > 0){
							document.getElementById("had_stock").innerHTML ="现货 付款后预计" + data[0].max_reach_day + "天送达";							 
						} else {
							document.getElementById("had_stock").innerHTML ="现货 付款后立即发货"; 
						}
						hideObj("no_stock");
					} else { 
						hideObj("had_stock");
						showObj("no_stock");
					}	        	   
	        		hideObj("price_tag_loading");
	        		//hideObj("price_tag_original_loading");
	        		hideObj("stocks_loading");  
	        		//showObj("stock"); 
	        	 }catch(e){ alert(e); alert(xmlHttp.responseText);}
	         }
	     }
	}
	xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	if (window.XMLHttpRequest){ xmlHttp.send(null);  }else if (window.ActiveXObject){ xmlHttp.send(); }	
}

function showProvince(){
	showObj("province_dl");
	hideObj("city_dl");
	hideObj("county_dl");
	document.getElementById("addr_city").innerHTML=""; 
	document.getElementById("addr_county").innerHTML=""; 
	document.getElementById("city_dl").innerHTML="";
	document.getElementById("county_dl").innerHTML="";
	document.getElementById("addr_province").setAttribute("class","addr_td1");
	document.getElementById("addr_city").setAttribute("class","addr_td2");
	document.getElementById("addr_county").setAttribute("class","addr_td2");
	hideLoading();
}

function showCity(){
	showObj("city_dl");
	hideObj("county_dl"); 
	document.getElementById("addr_county").innerHTML=""; 
	document.getElementById("addr_city").innerHTML="<span>请选择</span>";  
	document.getElementById("county_dl").innerHTML=""; 
	document.getElementById("addr_city").setAttribute("class","addr_td1");
	document.getElementById("addr_county").setAttribute("class","addr_td2");
	hideLoading();
}

function showArea(){document.getElementById("more_province").style.display="block";}
function hideArea(){document.getElementById("more_province").style.display="none";}
function showLoading(){document.getElementById("img_loading").style.display="block";}
function hideLoading(){document.getElementById("img_loading").style.display="none";}
function showObj(objId){document.getElementById(objId).style.display="block";}
function hideObj(objId){document.getElementById(objId).style.display="none";}

//正则表达式：只能输入数字
function setOnlyInt(obj) {   
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
		var v =parseInt(document.getElementById("buy_num").value);
		if(v>999) v=999;
		if(v<1) v=1;
		document.getElementById("buy_num").value=v; 
} 

function buyNum(num){
	var v =parseInt(document.getElementById("buy_num").value)+num;
	if(v>999) v=999;
	if(v<1) v=1;
	document.getElementById("buy_num").value=v;
}

function showNav(){  
	if(document.getElementById("jdkey").style.display=='none'){ 
		document.getElementById("jdkey").style.display='block';
	}else{
		document.getElementById("jdkey").style.display='none';
	} 
}
//]]>
</script>
</body>
</html>