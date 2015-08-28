<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:if test="${ecUser.user_type eq 1 }">康佳EPP内部优惠购机平台</c:if><c:if test="${ecUser.user_type eq 2 }">康佳网上直销商城</c:if></title>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/member/css/global1.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/member/css/epp.css" /> 
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/slider.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/purchase.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/citybox.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/cloud-zoom/styles/image_show.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
</head>
<body>
<!-- head start -->
<!-- top start -->
<div class="topbox">
<jsp:include page="/member/__inc/top.jsp" flush="true" />
<jsp:include page="/member/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/member/__inc/nav.jsp" flush="true" />
<!-- top end -->  
<!-- first end --> 

<!-- second start -->
<div class="maincont margintop10" >
<br/>
  <div class="position"><a href="<c:url value='/member/Index.do' />">首页</a> &gt; <c:if test="${fn:contains(konkaBcompPd.pd_name, 'HYUNDAI')}">HYUNDAI</c:if><c:if test="${fn:contains(konkaBcompPd.pd_name, '戴尔')}">戴尔</c:if><c:if test="${not fn:contains(konkaBcompPd.pd_name, 'HYUNDAI') and not fn:contains(konkaBcompPd.pd_name, '戴尔')}"></c:if>&nbsp;<c:out value="${konkaBcompPd.pd_sn}" /></div>
  <div class="viewbox1" style="min-height:450px;">
  	<!-- 产品展示详细 -->
    <jsp:include page="../__inc/_pd_images.jsp" flush="true" />
    
    <!-- 表单开始 -->
    <form action="<c:url value='/member/ShoppingCar.do' />" id="form_pd_buy" method="post">
    <input type="hidden" name="method" value="buyByGoodsId" />
      <input type="hidden" name="tuangou" id="tuangou" value="6" />
    <input type="hidden" name="goods_id" id="goods_id" value="${konkaBcompPd.id}" />
    <input type="hidden" name="pd_name" id="pd_name" value="${konkaBcompPd.pd_name}" />
    <input type="hidden" name="price" id="price" value="${konkaBcompPd.ecGoodsPrice.price}" />
    <input type="hidden" name="original_price" id="original_price" value="${konkaBcompPd.ecGoodsPrice.original_price}" />
    <input type="hidden" name="p_index" id="p_index" value="${p_index}" />
    <input type="hidden" name="img_url" id="img_url" value="${konkaBcompPd.main_pic}" />
    <input type="hidden" name="had_stock_num" id="had_stock_num" value="${konkaBcompPd.ecStocks.stocks}" />
    <div class="viewright">
      <div class="viewtit1"><h3> 
      			<c:if test="${fn:contains(konkaBcompPd.pd_name, 'HYUNDAI')}">HYUNDAI</c:if>   
      			<c:if test="${fn:contains(konkaBcompPd.pd_name, '戴尔')}">戴尔</c:if> 
                <c:if test="${not fn:contains(konkaBcompPd.pd_name, 'HYUNDAI') and not fn:contains(konkaBcompPd.pd_name, '戴尔')}"></c:if>&nbsp;<c:out value="${konkaBcompPd.pd_sn}" /> &nbsp;<span style="color: #d40207"><c:if test="${konkaBcompPd.label_of_cate eq 0}">新品</c:if><c:if test="${konkaBcompPd.label_of_cate eq 2}">热销</c:if><c:if test="${konkaBcompPd.label_of_cate eq 3}">特惠</c:if><c:if test="${konkaBcompPd.label_of_cate eq 7}">精品</c:if></span></h3>
                <h4><c:out value="${konkaBcompPd.pd_name}" /></h4><div class="clear"></div>
      </div>
      <div class="viewtab1">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="proxltab">
        	<c:if test="${ecUser.user_type eq 1 }">
             <tr>
              <td colspan="3" height="22">团 购 价：<strong class="redfont" id="price_tag" style="color: #f76120;font: 700 20px tahoma,sans-serif;">￥<c:if test="${konkaBcompPd.ecGoodsPrice.price gt 0}"><fmt:formatNumber value="${konkaBcompPd.ecGoodsPrice.price}" pattern="#,##0" /></c:if>
                <c:if test="${konkaBcompPd.ecGoodsPrice.price lt 0.001}">
              <fmt:formatNumber value="${konkaBcompPd.ecGoodsPrice.price}" pattern="#0" /> 
              </c:if>
              </strong><img id="price_tag_loading" style="display:none;" src="${ctx}/images/loading5.gif" /></td>
            </tr>
            <tr>
              <td colspan="3" height="22">市 场 价：<font class="midtxt" id="original_price_tag">￥
              <c:if test="${konkaBcompPd.ecGoodsPrice.original_price gt 0}">
              <fmt:formatNumber value="${konkaBcompPd.ecGoodsPrice.original_price}" pattern="#0.00" /> 
              </c:if>
              <c:if test="${konkaBcompPd.ecGoodsPrice.original_price lt 0.001}">
              <fmt:formatNumber value="${konkaBcompPd.ecGoodsPrice.original_price}" pattern="#0" />
              </c:if>
              </font><img id="original_price_tag_loading" style="display:none;" src="${ctx}/images/loading5.gif" /></td>
            </tr> </c:if> 
           <tr>
              	<td colspan="3" height="22">团购时间：2015-03-04 至 2015-03-06 </td>       
            </tr>
            <tr>
              <td colspan="3" height="22">已参与团购：<fmt:formatNumber value="${konkaBcompPd.sale_num}" pattern="#0" /> <font color="red">(满50件开团)</font></td>
            </tr>
            <tr>
              <td colspan="3" height="22">浏 览 量：<strong class="redfont">${empty konkaBcompPd.view_counts ? '0' : konkaBcompPd.view_counts}</strong>&nbsp;次</td>
            </tr>
			<jsp:include page="../__inc/_citybox.jsp" flush="true" />
        </table>
      </div>
      <div class="viewtab2">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="proxltab">
          <tbody>
            <tr>
              <td width="10%">购买数量：</td>
              <td width="90%"><div class="addtab"><img src="${ctx}/styles/member/images/edd.gif" id="num_jian" style="cursor:pointer;" /></div>
                <div class="proinput1">
                  <input type="text" class="proinput2" name="buy_num" id="buy_num" value="1" onfocus="javascript:setOnlyInt(this);" maxlength="3" />
                </div>
                <div class="addtab"><img src="${ctx}/styles/member/images/add.gif" id="num_jia" style="cursor:pointer;" /></div></td>
            </tr>
            <c:if test="${not empty konkaBcompPd.ecBindingPdListForService}">
            <tr>
              <td>服　　务：</td>
              <td valign="middle"><span id="service_html">
              	  <c:forEach items="${konkaBcompPd.ecBindingPdListForService}" var="cur" varStatus="vs">
              	  	<input name="service" type="checkbox" id="service_${cur.id}" class="service" value="${cur.id}" /><label for="service_${cur.id}" style="cursor:pointer;margin-left:5px;">${cur.goods_name} ￥<fmt:formatNumber value="${cur.price}" pattern="#0.00" /></label>
              	   <c:if test="${vs.count mod 3 eq 0 }"><br/></c:if>
              	  </c:forEach></span>
              </td>
            </tr>
            <tr style="color:#f00;display:none;" id="had_select_tr">
            	<td colspan="2" id="had_select_td">
            		<strong>已选择：</strong>
            	</td>
            </tr>
            </c:if>
            <tr>
              <td colspan="2"><div class="padtop20"> &nbsp;&nbsp;<img alt="立即购买" src="${ctx}/styles/member/images/but_buy2.gif" style="cursor:pointer;" id="buy_btn" /></a>&nbsp;&nbsp;</div></td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
	</form>    
    <div class="clear"></div>
  </div>
  <div class="clear"></div>
</div>
<div class="maincont"> 
  <!--left-->
  <div class="listleft">
    <div class="liscont1">
      <div class="listit1">推荐热卖</div>
      <div class="listbox1">
        <ul class="listul4" style="padding:0;">
           <c:forEach items="${bcomp_pd_list_top_5}" var="cur" varStatus="vs">
           	  <c:set var="li_class" value="" />
              <c:if test="${vs.last}"><c:set var="li_class" value="noline4" /></c:if>  
	          <li style="padding:0;" class="${li_class}">
	            <div class="listpic1"><table border="0" cellspacing="0" cellpadding="0" width="120"><tr><td height="110" style="overflow:hidden;" width="120" valign="middle" align="center"><a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank"><img src="${ctx}/${cur.main_pic}" style="max-height:100px;" width="100"/></a></td></tr></table></div>
	            <div class="listrxt1">
	              <h3><a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank"><c:if test="${fn:contains(cur.pd_name, 'HYUNDAI')}">HYUNDAI</c:if>
               <c:if test="${not fn:contains(cur.pd_name, 'HYUNDAI')}"></c:if>&nbsp;<c:out value="${cur.pd_sn}" />&nbsp;<c:out value="${cur.pd_name}" /></a></h3>
	              <h4>会员价：￥<fmt:formatNumber value="${cur.ecGoodsPrice.price}" pattern="0" /></h4>
	            </div>
	            <div class="clear"></div>
	          </li>
           </c:forEach>
        </ul>
      </div>
      <div class="clear"></div>
    </div>
  </div>
  <!--right-->
  <div class="liscont3"> 
	<div class="viewtit2"  >	
      <ul>
        <li class="cur pd_content" id="pdc1"> 商品详情</li>
        <li class="pd_content" id="pdc2">规格参数</li>
        <li class="pd_content" id="pdc3">商品评论</li>
        <li class="pd_content" id="pdc4">购买须知</li>
      </ul>
    </div>
    <div class="viewbox4" id="pdc1_pdc1" style="display:'';overflow-x:hidden;">
    	<c:forEach items="${konkaBcompPd.konkaBcompPdContentList}" var="cur">
    		<c:if test="${cur.type eq '1'}"><c:out value="${cur.content}" escapeXml="false" /></c:if>
    	</c:forEach>
    </div>
    <div class="viewbox4" id="pdc2_pdc2" style="display:none;overflow-x:hidden;">
    	<c:forEach items="${konkaBcompPd.konkaBcompPdContentList}" var="cur">
    		<c:if test="${cur.type eq '2'}"><c:out value="${cur.content}" escapeXml="false" /></c:if>
    	</c:forEach>
    </div>
    <div class="viewbox4" id="pdc3_pdc3" style="display:none;overflow-x:hidden;">
    	<img src="${ctx}/images/loading45.gif" id="pc_pd_eavl_loading_gif" style="display:none;" />
    	<iframe id="pc_pd_eavl_iframe" name="pc_pd_eavl_iframe" src="javascript:void(0);" height="50" width="100%"  frameborder="no"  border="0"  marginwidth="0"  marginheight="0"  scrolling="no"  allowtransparency="yes" onload="javascript:dyniframesizeForPdEval('pc_pd_eavl_iframe');"
	   ></iframe>
    </div>
    <div class="viewbox4" id="pdc4_pdc4" style="display:none;overflow-x:hidden;">
    	<c:forEach items="${konkaBcompPd.konkaBcompPdContentList}" var="cur">
    		<c:if test="${cur.type eq '3'}"><c:out value="${cur.content}" escapeXml="false" /></c:if>
    	</c:forEach>
    </div>
    <div class="clear"></div>
  </div>
  <div class="clear"></div>
</div>
<!-- second end --> 

<!-- six footer -->
<jsp:include page="../__inc/footer.jsp" flush="true" />
<!-- six footer -->

<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	// 立即购买
	$(document).delegate("#buy_btn", "click", function(){
		var had_stock_num = $("#had_stock_num").val();
		var buy_num = $("#buy_num").val();
		var price = $("#price").val();
		if(price ==""){
			alert("提示：对不起，该地区产品暂未上架！");
			return false;
		}
		if(parseInt(buy_num) > parseInt(had_stock_num)||had_stock_num==""){
			alert("提示：对不起，您购买的商品库存不足，请您联系客服！");
			return false;
		}

		if(parseInt(buy_num) <1){
			$("#buy_num").val(1);
		}
         var exit_num = '${exit_num}';
         var max_num = '${max_num}';
         var all_num = parseInt(exit_num)+parseInt($("#buy_num").val());
         if(parseInt(all_num) > parseInt(max_num)){
        	 alert("提示：对不起，该商品每月每人限购"+max_num+"台！");
 			return false;
          }
		
		$("#form_pd_buy").submit();	
	});
	 
	
	// 加入购物车
	$(document).delegate("#add_shoping_car_btn", "click", function(){
		var session_u_id = "${ecUser.id}";
		
		// 处理绑定的服务
		var checkbox = $(".service");
		var service_ids = "";
		for(var i = 0; i < checkbox.length; i++){
			if("checked" == $(checkbox[i]).attr("checked")){
				service_ids += $(checkbox[i]).val() + "|";
			}
		}
		service_ids = service_ids.substring(0, service_ids.length - 1);
		
		var goods_id = $("#goods_id").val();
		var md_name = $("#pd_name").val();
		var buy_num = $("#buy_num").val();
		var price = $("#price").val();
		var img_url = $("#img_url").val();
		var p_index = $("#p_index").val();
		var had_stock_num = $("#had_stock_num").val();
		if(parseInt(buy_num) > parseInt(had_stock_num)||had_stock_num==""){
			alert("提示：对不起，您购买的商品库存不足，请您联系客服！");
			return false;
		}

		var exit_num = '${exit_num}';
        var max_num = '${max_num}';
        if(parseInt(buy_num) <1){
			$("#buy_num").val(1);
		}
        var all_num = parseInt(exit_num)+parseInt($("#buy_num").val());
        if(parseInt(all_num) > parseInt(max_num)){
       	 alert("提示：对不起，该商品每月每人限购"+max_num+"台！");
			return false;
         }

		
		// 处理COOKIE中的值
		try {
		   var value = "["; 
		   var SHOPING_CAR_COOKIE = $.cookie("SHOPING_CAR_COOKIE"); 
		   var flag = false; // 状态位标识是否已经添加cookie
		   var cookie_count = 0; // 记录cookie中的购物车商品数
		   if(null != SHOPING_CAR_COOKIE && "" != SHOPING_CAR_COOKIE) {
		      var json = eval("(" + SHOPING_CAR_COOKIE + ")"); 
		      cookie_count = json.length;
		      for(var i = 0; i < json.length; i ++) {
		         if(json[i].goods_id == goods_id) {
		        	flag = true;
		            value += "{\"goods_id\":\"" + goods_id + "\",\"p_index\":\"" + p_index + "\",\"md_name\":\"" + md_name + "\",\"buy_num\":\"" + buy_num + "\",\"price\":\"" + price + "\",\"service_ids\":\"" + service_ids + "\",\"img_url\":\"" + img_url + "\"}," 
		         } else {
		            value += "{\"goods_id\":\"" + json[i].goods_id + "\",\"p_index\":\"" + json[i].p_index + "\",\"md_name\":\"" + json[i].md_name + "\",\"buy_num\":\"" + json[i].buy_num + "\",\"price\":\"" + json[i].price + "\",\"service_ids\":\"" + json[i].service_ids + "\",\"img_url\":\"" + json[i].img_url + "\"}," 
		         }
		      }
		   }
		   if(!flag){ // cookie中没有需要的商品则新增
			   // 判断
			   if(cookie_count >= 10){
				   alert("提示，购物车只能保存10件商品，请前往购物车修改！");
				   return;
			   }
			   value += "{\"goods_id\":\"" + goods_id + "\",\"p_index\":\"" + p_index + "\",\"md_name\":\"" + md_name + "\",\"buy_num\":\"" + buy_num + "\",\"price\":\"" + price + "\",\"service_ids\":\"" + service_ids + "\",\"img_url\":\"" + img_url + "\"},"
			   value = value.substring(0, value.length - 1) + "]"; 
			   $.cookie("SHOPING_CAR_COOKIE", value, { expires : 30 }); 
			   
				// 更新导航中的购物车数量
				var shopping_car_num = parseInt($("#shopping_car_num").html());
				$("#shopping_car_num").html(shopping_car_num + 1);
			
		   } else{ // 添加过购物车
			   value = value.substring(0, value.length - 1) + "]"; 
			   $.cookie("SHOPING_CAR_COOKIE", value, { expires : 30 }); 
		   }
		   
		   // 用户没用登陆，Cookie添加以后则提示添加成功
		   if(session_u_id == "")
			   alert("恭喜，成功添加商品到购物车！");
		}catch(e) { alert("提示，请启用浏览器Cookie.");}
		
		// 判断并处理Session 调用Ajax报错
		if("" != session_u_id){ // 已登录用户调用Ajax添加商品至数据库购物车
			$.ajax({
				type: "POST",
				url: "<c:url value='/member/ShoppingCar.do' />",
				data: { "method":"ajaxAddCar", "goods_id":goods_id, "p_index":p_index, "md_name":md_name, "buy_num":buy_num, "price":price, "img_url":img_url, "service_ids":service_ids, "timestamp":new Date().getTime() },
				dataType: "json",
				sync: true, // jsonp不支持同步
				error: function (xhr, ajaxOptions, thrownError) { alert("数据加载请求失败【" + xhr.statusText + "," + xhr.responseText + "," + xhr.status + "," + thrownError +"】！"); },
				success: function(result) {
					if(result.status == '0'){ 
						alert(result.msg);
					} else {
						alert("恭喜，成功添加商品到购物车！");
					}
				}
			});
		}
	});
	
	// 添加关注
	$(document).delegate("#add_favotrites_btn", "click", function(){
		var session_u_id = "${ecUser.id}";
		if("" == session_u_id){
			alert("对不起，登陆以后才能添加关注！");
			return false;
		}
		
		var goods_id = $("#goods_id").val();
		var md_name = $("#pd_name").val();
		var price = $("#price").val();
		<c:if test="${ecUser.user_type eq 2 }">
		price = $("#original_price").val();
		</c:if>
		var img_url = $("#img_url").val();
		
		$.ajax({
			type: "POST",
			url: "<c:url value='/member/ShoppingCar.do' />",
			data: { "method":"ajaxAddFavotrites", "user_id":session_u_id, "goods_id":goods_id, "md_name":md_name, "price":price, "img_url":img_url, "timestamp":new Date().getTime() },
			dataType: "json",
			sync: true,
			error: function (xhr, ajaxOptions, thrownError) { alert("数据加载请求失败【" + xhr.statusText + "," + xhr.responseText + "," + xhr.status + "," + thrownError +"】！"); },
			success: function(result) {
				if(result.status == '0'){
					alert(result.msg);
				} else if (result.status == '1'){
					alert("恭喜，添加关注成功！");
				} else{
					alert(result.msg);
				}
			}
		});
	});
	
	// 商品描述，商品规格，等切换
	$(document).delegate(".pd_content", "click", function(){
		$(this).attr("hover", "hover"); // 这里标记鼠标指向的那个图片

	    var class_name = $(this).attr("class");
		$(".pd_content").each(function(){
			var id = $(this).attr("id");
		    if($(this).attr("hover") == "hover"){
				 $(this).removeClass("cur pd_content").addClass("cur pd_content");
				 $("#" + id + "_" + id).show();
				 
				 // 如果是评论则加载Iframe
				 if("pdc3" == id){
					var url = "${ctx}/member/EcPdEavl.do?goods_id=" + $("#goods_id").val() + "&timestamp=" + new Date().getTime();
					$("#pc_pd_eavl_iframe").hide();
					$("#pc_pd_eavl_loading_gif").show();
					$("#pc_pd_eavl_iframe").attr("src", url);
				 }
			} else {
				$(this).removeClass("cur pd_content").addClass("pd_content");
				 $("#" + id + "_" + id).hide();
			}
		});

		$(this).removeAttr("hover");
	});
	
	// 动态监测商品服务 checkbox 是否选择
	$(document).delegate(".service", "click", function(){
		$("#had_select_tr").hide();
		
		var checkbox = $(".service");
		var count=0;
		var content = "<strong>已选择：</strong>";
		for(var i = 0; i < checkbox.length; i++){
			if(checkbox[i].id !=this.id){
				checkbox[i].checked=false;
			}
		}
		for(var i = 0; i < checkbox.length; i++){
			if("checked" == $(checkbox[i]).attr("checked")){
				var value = $(checkbox[i]).next().html();
				content += "<strong>" + value + "</strong>，"
				count++;
			}
		}
		
		if(count != 0){
			$("#had_select_td").html(content.substring(0, content.length - 1));
			$("#had_select_tr").show();
		}
	});
	
	
	// 减少一个数量
	$(document).delegate("#num_jian", "click", function() { 
		var num = parseInt($("#buy_num").val()) - 1;
		if(num <= 0) num = 1;
		$("#buy_num").val(num);
	}); 
	
	// 增减一个数量
	$(document).delegate("#num_jia", "click", function(){
		var num = parseInt($("#buy_num").val()) + 1;
		if(num >= 999) num = 999;
		$("#buy_num").val(num);
	});
});

//正则表达式：只能输入数字
function setOnlyInt(obj) {
	$(obj).css("ime-mode", "disabled");
	$(obj).attr("t_value", "");
	$(obj).attr("o_value", "");
	$(obj).bind("dragenter",function(){
		return false;
	});
	$(obj).keypress(function (){
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).keyup(function (){
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).blur(function (){
		if(!obj.value.match(/^(?:\d+(?:\d+)?|\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\d+$/))obj.value=obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
	});
}

function dyniframesizeForPdEval(iframeid) {
	$("#pc_pd_eavl_loading_gif").hide();
	$("#" + iframeid).show();	
	var ifm= document.getElementById(iframeid); 
	var subWeb = document.frames ? document.frames[iframeid].document : ifm.contentDocument; 
	if(ifm != null && subWeb != null) { 
		ifm.height = subWeb.body.scrollHeight; 
	} 
}

function getAllpj(){
	var z=0;
	var price =parseFloat($("#price").val()); 
	var o_price =parseFloat($("#price").val());
	var goods=document.getElementsByName("goods_ids"); 
	for(var i=0;i<goods.length;i++){
		 if(goods[i].checked==true){ 
			 price+=parseFloat($(goods[i]).attr('wmeprice'));
			 o_price+=parseFloat($(goods[i]).attr('wmaprice'));
			 z++;
		 }
	}
	$("#all_price").html("￥"+price);
	$("#all_o_price").html("￥"+o_price);
	$("#pj_num").html(""+z);
	
} 

$(function(){
    $(".tab-cat li").bind("click",function(){
       $(".tab-cat-content li").eq($(this).index()).show().siblings().hide();
    })
 })
//]]></script>
</body>
</html>