<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:if test="${ecUser.user_type eq 1 }">康佳EPP内部优惠购机平台</c:if><c:if test="${ecUser.user_type eq 2 }">康佳网上直销商城</c:if></title>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/member/css/global1.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/member/css/epp.css" />  
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/purchase.css" /> 
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/cloud-zoom/styles/image_show.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
<style>
.gray {-webkit-filter: grayscale(100%); -moz-filter: grayscale(100%); -ms-filter: grayscale(100%); -o-filter: grayscale(100%); filter: grayscale(100%); filter: gray;}
</style>
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
  <div class="position"><a href="<c:url value='/member/Index.do' />">首页</a> &gt; <a href="<c:url value='/member/Auction.do' />">拍卖活动</a></div>
  <div class="viewbox1" style="min-height:450px;">
  	<!-- 产品展示详细 -->
    <jsp:include page="../__inc/ec_lucky_images.jsp" flush="true" /> 
    <!-- 表单开始 -->
    <form action="<c:url value='/member/Auction.do' />" id="form_pd_buy" method="post">
    <input type="hidden" name="buy_json_object" value="${buy_json_object}" id="buy_json_object" />
    <input type="hidden" name="method" value="stepTwo" />
    <input type="hidden" name="auction_id" id="auction_id" value="${entity.id}" /> 
    <input type="hidden" name="p_index" id="p_index" value="${p_index}" /> 
    <input type="hidden" name="price" id="price" value="${buy_price}" /> 
    <input type="hidden" name="buy_count" id="buy_count" value="${buy_count}" /> 
    <input type="hidden" name="delay_num" id="delay_num" value="${entity.delay_num}" /> 
    <input type="hidden" name="add_price" id="add_price" value="${entity.add_price}" /> 
    <input type="hidden" name="hyj_price" id="hyj_price" value="${entity.price}" /> 
    <div class="viewright">
      <div class="viewtit1"><h3><c:out value="${entity.title}" /></h3><h4><c:out value="${entity.brief}" escapeXml="false"/></h4><div class="clear"></div>
      </div>
      <div class="viewtab1">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="proxltab"> 
            <tr>
              <td colspan="3" height="22">会员价  ￥ ${entity.price} 元</td>
            </tr> 
            <tr>
              <td colspan="2" height="22" width="30%">当前价 <strong class="yel16b redfont" id="price_tag">￥ ${buy_price }</strong> 元      </td><td>出价<span style='color:darkorange;' id="buy_count_tag"><fmt:formatNumber value="${buy_count}" pattern="#0" /></span>次</td>
            </tr> 
            <tr>
              <td colspan="2" height="22" width="30%"><span id="divTimeRemains"></span> </td><td>延时<span style='color:darkorange;' id="delay_num_tag"><fmt:formatNumber value="${entity.delay_num}" pattern="#0" /></span>次</td>
            </tr> 
            <tr>
            <td height="22" width="12%">出价金额</td>
            <td height="22" width="18%">
           		 <input type="text" name="buy_price" id="buy_price" value="<fmt:formatNumber value="${buy_price +entity.add_price }" pattern="#0" />" maxlength="8" style="text-align: center;width:60px;height:30px;display:inline-block;"  onfocus="javascript:setOnlyInt(this);"/> 
    		</td> 
            <td height="22"> 
                    <div style="width:17px;border:#666 solid 1px;padding:0;height:17px;line-height:17px;cursor:pointer;margin:4px 15px 0 0;" ><img src="${ctx}/styles/member/images/add.gif" id="num_jia" style="width:16px;cursor:pointer;" /> </div>
              		<div style="width:17px;border:#666 solid 1px;padding:0;height:17px;line-height:17px;cursor:pointer;margin:4px 15px 0 0;"><img src="${ctx}/styles/member/images/edd.gif" id="num_jian" style="width:16px;cursor:pointer;" /> </div>  </td>
            </tr>
        </table>
      </div>
      <div class="viewtab2">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="proxltab">
          <tbody>
            <tr>
              <td width="10%">状态：</td>
              <td width="90%"><c:if test="${entity.auction_state eq 0 }"><font  style="font-size: 18px;color: red;margin-left:15px;">竞拍中</font></c:if>  
              <c:if test="${entity.auction_state ne 0 }"><font style="font-size: 18px;color: red;margin-left:15px;">已结束</font></c:if>  
              </td>
            </tr> 
            <tr>
              <td colspan="2">
              	<c:if test="${is_success eq '1' }">
              	<div class="padtop20"><font  style="font-size: 18px;color: green;margin-left:35px;">恭喜您，竞拍成功  <a href="?method=stepTwo&auction_id=${entity.id }" style="font-weight:bold;color: red;">去下单</a></font> 截止下单时间：<fmt:formatDate value="${entity.auction_order_time}" pattern="yyyy-MM-dd hh:mm:ss" /></div>
              	</c:if>
              	<c:if test="${is_success eq '0' }">
              	<div class="padtop20"><font  style="font-size: 18px;color: green;margin-left:35px;">竞拍结束，用户 ${ecAuctionBuy.user_name } 竞拍成功</font></div>
              	</c:if>
              	<c:if test="${empty is_success}">
              	<div class="padtop20"><img class="gray" alt="出价" src="${ctx}/styles/member/images/but_chu_jia.jpg" style="cursor:pointer;" id="buy_btn" /></div>
              	</c:if>
              	<div>起拍价：￥${entity.auction_price } ，加价幅度：￥${entity.add_price }      延时周期：截拍前${entity.delay_time }秒内有人出价，延时${entity.delay_time }秒</div>    
              </td>
            </tr> 
          </tbody>
        </table>
      </div>
      <div class="viewtit1" style="height:60px;" >
       <font  style="font-size: 14px;color: red;margin-left:5px;line-height:26px;">	领先人</font>
       <table style="width:100%;" border="1" id="table_0"> 
			<tr>
				<td align="center" width="15%">流水号 </td><td align="center" width="20%">竞拍时间 </td><td align="center" width="10%">金额 </td><td align="center" width="15%">竞拍人 </td>
			</tr><c:if test="${not empty ecAuctionBuy }">
			<tr> 
				<td align="center"><span id="auctionbuy_trade_index_tag">${ecAuctionBuy.trade_index }</span></td>
				<td align="center"><span id="auctionbuy_add_date_tag"><fmt:formatDate value="${ecAuctionBuy.add_date}" pattern="yyyy-MM-dd HH:mm:ss"/></span></td>
				<td align="center"><span id="auctionbuy_price_tag">${ecAuctionBuy.price }</span></td>
				<td><span id="auctionbuy_user_name_tag">${ecAuctionBuy.user_name }</span></td>
			</tr></c:if><c:if test="${empty ecAuctionBuy }">
			<tr> 
				<td align="center"><span id="auctionbuy_trade_index_tag"> </span></td>
				<td align="center"><span id="auctionbuy_add_date_tag"> </span></td>
				<td align="center"><span id="auctionbuy_price_tag"> </span></td>
				<td><span id="auctionbuy_user_name_tag"> </span></td>
			</tr></c:if>
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
        <ul class="listul4" style="padding:0;"><c:forEach items="${bcomp_pd_list_top_5}" var="cur" varStatus="vs">
           	 <c:set var="li_class" value="" /><c:if test="${vs.last}"><c:set var="li_class" value="noline4" /></c:if>
	         <li style="padding:0;" class="${li_class}">
	            <div class="listpic1"><table border="0" cellspacing="0" cellpadding="0" width="120"><tr><td height="110" style="overflow:hidden;" width="120" valign="middle" align="center"><a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />"><img src="${ctx}/${cur.main_pic}" style="max-height:100px;" width="100"/></a></td></tr></table></div>
	            <div class="listrxt1">
	              <h3><a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />">康佳&nbsp;<c:out value="${cur.pd_sn}" />&nbsp;<c:out value="${cur.pd_name}" /></a></h3>
	              <h4>会员价：￥<fmt:formatNumber value="${cur.ecGoodsPrice.price}" pattern="0" /></h4>
	            </div>
	            <div class="clear"></div>
	         </li></c:forEach>
        </ul>
      </div>
      <div class="clear"></div>
    </div>
  </div>
  <!--right-->
  <div class="liscont3">  
	<div class="viewtit2"  >	
      <ul>
        <li class="cur pd_content" id="pdc1">拍品详情</li>
        <li class="pd_content" id="pdc2">拍品参数</li> 
        <li class="pd_content" id="pdc3">出价记录</li> 
        <li class="pd_content" id="pdc4">拍卖规则</li> 
      </ul>
    </div>   
    <div class="viewbox4" id="pdc1_pdc1" style="display:'';overflow-x:hidden;">    
    	<c:out value="${entity.content}" escapeXml="false" /> 
    </div> 
    <div class="viewbox4" id="pdc2_pdc2" style="display:none;overflow-x:hidden;" > 
    	<c:out value="${entity.auction_spec}" escapeXml="false" /> 
    </div> 
    <div class="viewbox4" id="pdc3_pdc3" style="display:none;overflow-x:hidden;" >
        <img src="${ctx}/images/loading45.gif" id="buy_list_loading_gif" style="display:none;" />
        <iframe id="buy_list_iframe" name="buy_list_iframe" src="javascript:void(0);" height="50" width="100%"  frameborder="0" marginwidth="0"  marginheight="0"  scrolling="no"  allowtransparency="yes" onload="javascript:dyniframesizeForBuyList('buy_list_iframe');" ></iframe>
    </div>
    <div class="viewbox4" id="pdc4_pdc4" style="display:none;overflow-x:hidden;" >
    	<c:out value="${entity.auction_memo}" escapeXml="false" /> 
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
var flg=0;    
var msg="";
<c:if test="${entity.auction_state ne 0 }">
msg="对不起,活动未开始";
</c:if> 

$(document).ready(function(){
	// 出价
	$(document).delegate("#buy_btn", "click", function(){
		if(msg!=""){
			alert(msg); 
		}else{
			if(flg==0){
				alert("活动未开始");
				return false;
			}
			var buy_json_object=$("#buy_json_object").val();
			var hyj_price=parseFloat($("#hyj_price").val());
			var price=$("#buy_price").val();
			var price_value=parseFloat($("#price").val())+parseFloat($("#add_price").val());
			if(parseFloat(price)+0!= price){
				alert("出价金额不正确,请重新输入");
				return false;
			}
			
			if(price!=''){
				if(parseFloat(price)<price_value){
					alert("最低加价幅度${entity.add_price}");
					return false;
				}
				if(parseFloat(price)>hyj_price){
					alert("出价已经超出会员价${entity.price},建议您直接搜索该商品以会员价购买");
					return false;
				}
			}else{
				alert("出价金额不能为空");
				return false;
			}
			if(confirm("您的出价："+price+",确认出价？")){
				
				
			}else{
				return false;
			}
			$.ajax({
				type: "POST",
				url: "<c:url value='/member/Auction.do' />",
				data: { "method":"ajaxAddBuy", "buy_json_object":buy_json_object, "price":price, "timestamp":new Date().getTime() },
				dataType: "json",
				sync: true,
				error: function (xhr, ajaxOptions, thrownError) { alert("数据加载请求失败 "); },
				success: function(result) {
					if(result.status == '1'){
						alert("出价成功");
					} else { 
						alert(result.msg);
					}
				}
			});
		}
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
				 if("pdc3" == id){
						var url = "${ctx}/member/Auction.do?method=listBuy&auction_id=" + $("#auction_id").val() + "&timestamp=" + new Date().getTime();
						$("#buy_list_iframe").hide();
						$("#buy_list_loading_gif").show();
						$("#buy_list_iframe").attr("src", url);
				 }
			} else {
				$(this).removeClass("cur pd_content").addClass("pd_content");
				 $("#" + id + "_" + id).hide();
			}
		}); 
		$(this).removeAttr("hover");
	}); 
	
	// 减少一个数量
	$(document).delegate("#num_jian", "click", function() { 
		var price=parseFloat($("#price").val());
		var add_price=parseFloat($("#add_price").val());
		var buy_price = parseFloat($("#buy_price").val()) ; 
		if(buy_price>=add_price*2+price){
			buy_price=buy_price-add_price;
 			$("#buy_price").val(buy_price);
		}
		
	}); 
	
	// 增减一个数量
	$(document).delegate("#num_jia", "click", function(){
		var price=parseFloat($("#price").val());
		var add_price=parseFloat($("#add_price").val());
		var buy_price = parseFloat($("#buy_price").val()) ;  
			buy_price=buy_price+add_price;
 			$("#buy_price").val(buy_price); 
	});
})
 
var timeEnd = '${timeRemains}';
var timeStart = '${timeRemainsStart}';; 
var timeRemains = timeStart;//timeEnd;
var iTime = timeRemains/1000;
var Account;
var is_update =0;
//RemainTime();
RemainTimeStart();
function RemainTime()
{
    var iDay,iHour,iMinute,iSecond;
    var sDay="",sHour="",sMinute="",sSecond="",sTime="";
    if (iTime >= 0)
    {
        iDay = parseInt(iTime/24/3600);
        if (iDay > 0)
        {
            sDay = "<font color='red'>"+iDay + "</font>天";
        }
        iHour = parseInt((iTime/3600)%24);
        if (iHour > 0){
            sHour = "<font color='red'>"+iHour + "</font>小时";
        }
        iMinute = parseInt((iTime/60)%60);
        if (iMinute > 0){
            sMinute = "<font color='red'>"+iMinute + "</font>分钟";
        }
        iSecond = parseInt(iTime%60);
        if (iSecond >= 0){
            sSecond ="<font color='red'>"+ iSecond + "</font>秒";
        }
        if ((sDay=="")&&(sHour=="")){
            sTime="<span style='color:darkorange;'>" + sMinute+sSecond + "</font>";
        }else{
            sTime=sDay+sHour+sMinute+sSecond;
        }
        if(iTime==0){
            clearTimeout(Account);
            sTime="<span style='color:red'>活动已结束！</span>";
            flg=0;
            $("#buy_btn").addClass("gray");
        }else{
            Account = setTimeout("RemainTime()",1000);
        }
        iTime=iTime-1;
    }else{
            sTime="<span style='color:red'>活动已结束！</span>";
            flg=0;
            $("#buy_btn").addClass("gray");
    }
    if(iTime>0){
    	 flg=1;
    	 $("#buy_btn").removeClass("gray");
    	 sTime="距结束："+sTime;
    }
    if(sTime!=""){ 
        document.getElementById("divTimeRemains").innerHTML = sTime;
        getMainInfo();
    }
}

function RemainTimeStart()
{
    var iDay,iHour,iMinute,iSecond;
    var sDay="",sHour="",sMinute="",sSecond="",sTime="";
    if (iTime >= 0)
    {
        iDay = parseInt(iTime/24/3600);
        if (iDay > 0)
        {
            sDay = "<font color='red'>"+iDay + "</font>天";
        }
        iHour = parseInt((iTime/3600)%24);
        if (iHour > 0){
            sHour = "<font color='red'>"+iHour + "</font>小时";
        }
        iMinute = parseInt((iTime/60)%60);
        if (iMinute > 0){
            sMinute = "<font color='red'>"+iMinute + "</font>分钟";
        }
        iSecond = parseInt(iTime%60);
        if (iSecond >= 0){
            sSecond ="<font color='red'>"+ iSecond + "</font>秒";
        }
        if ((sDay=="")&&(sHour=="")){
            sTime="<span style='color:darkorange;'>" + sMinute+sSecond + "</font>";
        }else{
            sTime=sDay+sHour+sMinute+sSecond;
        }
        if(iTime==0){
            clearTimeout(Account);
            iTime = timeEnd/1000;
            flg=1;
            sTime="";
            RemainTime();
        }else{
            Account = setTimeout("RemainTimeStart()",1000);
        }
        iTime=iTime-1;
    }else{
    	iTime = timeEnd/1000;
    	flg=1;
    	sTime="";
    	RemainTime();
    }
    if(iTime>0){
    	 flg=0;
    	 $("#buy_btn").addClass("gray");
    	 if(sTime!="")
    	 sTime="倒计时："+sTime;
    }
    if(sTime!=""){
    	 document.getElementById("divTimeRemains").innerHTML = sTime;	
    }  
}

function getMainInfo(){
	if(is_update==0){
	is_update =1;
	var buy_json_object=$("#buy_json_object").val(); 
	$.ajax({
		type: "POST",
		url: "<c:url value='/member/Auction.do' />",
		data: { "method":"ajaxGETMain", "buy_json_object":buy_json_object, "timestamp":new Date().getTime() },
		dataType: "json",
		sync: true,
		error: function (xhr, ajaxOptions, thrownError) { ; },
		success: function(result) {
			if(result.status == '1'){
				var buy_count = parseInt(result.buy_count);
				var price =parseFloat(result.price);
				var delay_num =parseInt(result.delay_num);
				var timeRemains=result.timeRemains; 
				var auctionbuy =result.auctionbuy;
				if(auctionbuy == '1'){
					var auctionbuy_price =result.auctionbuy_price;
					var auctionbuy_trade_index =result.auctionbuy_tradex_index;
					var auctionbuy_user_name =result.auctionbuy_user_name;
					var auctionbuy_add_date =result.auctionbuy_add_date; 
					
					$("#auctionbuy_price_tag").html(""+auctionbuy_price);
					$("#auctionbuy_trade_index_tag").html(""+auctionbuy_trade_index);
					$("#auctionbuy_user_name_tag").html(""+auctionbuy_user_name);
					$("#auctionbuy_add_date_tag").html(""+auctionbuy_add_date);
				}
				
				var delay_num_value=parseInt($("#delay_num").val());
				var buy_count_value=parseInt($("#buy_count").val());
				var price_value=parseFloat($("#price").val());
				var add_price_value=parseFloat($("#add_price").val());
				var buy_price_value=parseFloat($("#buy_value").val());
				
				if(delay_num_value<delay_num){ 
					iTime = timeRemains/1000; 	 
					$("#delay_num_tag").html(""+delay_num);
					$("#delay_num").val(delay_num);
				}
				if(buy_count_value<buy_count){
					$("#buy_count").val(buy_count);
					$("#buy_count_tag").html(""+buy_count); 
					$("#price").val(price);
					$("#price_tag").html(""+price);
					if(buy_price_value<price+add_price_value){
						$("#buy_price").val(price+add_price_value);
					}  
				}
				is_update=0;
			} else { 
				alert(result.msg); 
				is_update=0;
			}
		}
	});
	
	}
}

function dyniframesizeForBuyList(iframeid) {
	$("#buy_list_loading_gif").hide();
	$("#" + iframeid).show();	
	var ifm= document.getElementById(iframeid); 
	var subWeb = document.frames ? document.frames[iframeid].document : ifm.contentDocument; 
	if(ifm != null && subWeb != null) { 
		ifm.height = subWeb.body.scrollHeight; 
	} 
}

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

//]]></script>
</body>
</html>