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
  <div class="position"><a href="<c:url value='/member/Index.do' />">首页</a> &gt; 团购&nbsp;<c:out value="${konkaBcompPd.pd_sn}" /></div>
  <div class="viewbox1" style="min-height:450px;">
  	<!-- 产品展示详细 -->
    <jsp:include page="../__inc/_pd_images.jsp" flush="true" />
    
    <!-- 表单开始 -->
    <div class="viewright">
      <div class="viewtit1"><h3> 
      			&nbsp;<c:out value="${konkaBcompPd.pd_sn}" /> &nbsp;</h3>
                <h4><c:out value="${konkaBcompPd.pd_name}" /></h4><div class="clear"></div>
      </div>
      <div class="viewtab1">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="proxltab">
        	<c:if test="${ecUser.user_type eq 1 }">
             <tr>
              <td colspan="3" height="22">团 购 价：<strong  id="price_tag" style="color: #f76120;font: 700 20px tahoma,sans-serif;">￥ 
              <c:if test="${konkaBcompPd.ecGoodsPrice.price gt 0}">
              <fmt:formatNumber value="${konkaBcompPd.ecGoodsPrice.price}" pattern="#0.00" />
              </c:if>
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
            </tr> 
            </c:if>
            <tr>
              <td colspan="3" height="22">商家信息：<strong class="redfont">${konkaBcompPd.map.shop_name}</strong>&nbsp;${konkaBcompPd.map.shop_addr}&nbsp;|&nbsp;${konkaBcompPd.map.shop_tel}</td>
            </tr>
            <tr>
              	<td colspan="3" height="22">有效时间：截止至 ${konkaBcompPd.map.end_time}<span style="margin-left: 6px;color: #f76120;">周末、法定节假日通用</span> </td>  
            </tr>
             <tr>
              	<td colspan="3" height="22">使用时间：10:00 至 22:00 </td>
            </tr>
            <tr>
              	<td colspan="3" height="22">已 售 出：${konkaBcompPd.map.buy_num}  </td>
            </tr>
            
             <tr>
              <td colspan="3" height="22">浏 览 量：<strong class="redfont">${empty konkaBcompPd.view_counts ? '0' : konkaBcompPd.view_counts}</strong>&nbsp;次</td>
            </tr> 
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
            <tr>
              <td colspan="2">&nbsp;&nbsp;<img alt="立即购买" src="${ctx}/styles/member/images/but_buy2.gif" style="cursor:pointer;" id="buy_btn" /></td>  
            </tr>
          </tbody>
        </table>
      </div>
    </div>
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
               <c:if test="${not fn:contains(cur.pd_name, 'HYUNDAI')}">康佳</c:if>&nbsp;<c:out value="${cur.pd_sn}" />&nbsp;<c:out value="${cur.pd_name}" /></a></h3>
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
	<div class="viewtit2" >	
      <ul>
        <li class="cur pd_content" id="pdc1">本单详情</li>
        <li class="pd_content" id="pdc2">商家介绍</li>
        <li class="pd_content" id="pdc3">购买须知</li>
        <li class="pd_content" id="pdc4">商品评论</li>
      </ul>
    </div>
    <div class="viewbox4" id="pdc1_pdc1" style="display:'';overflow-x:hidden;">
    	<c:out value="${konkaBcompPd.map.content}" escapeXml="false" />
    </div>
    <div class="viewbox4" id="pdc2_pdc2" style="display:none;overflow-x:hidden;">
    	<c:out value="${konkaBcompPd.map.auction_spec}" escapeXml="false" /> 
    </div>
    <div class="viewbox4" id="pdc4_pdc4" style="display:none;overflow-x:hidden;">
    	<img src="${ctx}/images/loading45.gif" id="pc_pd_eavl_loading_gif" style="display:none;" />
    	<iframe id="pc_pd_eavl_iframe" name="pc_pd_eavl_iframe" src="javascript:void(0);" height="50" width="100%"  frameborder="no"  border="0"  marginwidth="0"  marginheight="0"  scrolling="no"  allowtransparency="yes" onload="javascript:dyniframesizeForPdEval('pc_pd_eavl_iframe');"
	   ></iframe>
    </div>
    <div class="viewbox4" id="pdc3_pdc3" style="display:none;overflow-x:hidden;">
    	<c:out value="${konkaBcompPd.map.auction_memo}" escapeXml="false" /> 
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
		var buy_num = $("#buy_num").val();
		if(parseInt(buy_num) <1){
			buy_num = 1;
		}
         location.href="${ctx}/member/EcGroupBuy.do?method=add&goods_id=${konkaBcompPd.id}&group_id=${af.map.group_id}&buy_num="+buy_num;
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
					var url = "${ctx}/member/EcPdEavl.do?goods_id=${konkaBcompPd.id}" + "&timestamp=" + new Date().getTime();
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

//]]></script>
</body>
</html>