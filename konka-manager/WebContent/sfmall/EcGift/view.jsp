<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康佳&顺丰网上直销商城</title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/sfmall/css/global1.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/sfmall/css/epp.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/sfmall/css/slider.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/sfmall/css/purchase.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/sfmall/css/citybox.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/cloud-zoom/styles/image_show.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
</head>
<body>
<!-- head start -->
<div class="topbox">
<jsp:include page="/sfmall/__inc/top.jsp" flush="true" />
<jsp:include page="/sfmall/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/sfmall/__inc/nav.jsp" flush="true" />
<!-- first start -->
<jsp:include page="../__inc/slider.jsp" flush="true" />
<!-- first end --> 

<!-- second start -->
<div class="maincont margintop10">
  <div class="position"><a href="<c:url value='/sfmall/Index.do' />">首页</a> &gt; <a href="<c:url value='/sfmall/EcGift.do' />">积分商城</a> &gt; 康佳&nbsp;<c:out value="${ecGift.pd_sn}" /></div>
  <div class="viewbox1">
    <jsp:include page="../__inc/ec_gift_images.jsp" flush="true" />
    <!-- 表单开始 -->
    <form action="<c:url value='/sfmall/EcGift.do' />" id="form_pd_buy" method="post">
    <input type="hidden" name="method" value="stepTwo" />
    <input type="hidden" name="gift_id" id="gift_id" value="${ecGift.id}" />
    <input type="hidden" name="pd_name" id="pd_name" value="${ecGift.pd_name}" />
    <input type="hidden" name="need_integral" id="need_integral" value="${ecGift.need_integral}" />
    <input type="hidden" name="p_index" id="p_index" value="${p_index}" />
    <input type="hidden" name="img_url" id="img_url" value="${ecGift.main_pic}" />
    <div class="viewright">
      <div class="viewtit1"><h3>康佳&nbsp;<c:out value="${ecGift.pd_sn}" /></h3><h4><c:out value="${ecGift.pd_name}" /></h4><div class="clear"></div>
      </div>
      <div class="viewtab1">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="proxltab">
          <tbody>
            <tr>
              <td colspan="3">所需积分：<strong class="yel16b redfont"><fmt:formatNumber value="${ecGift.need_integral}" pattern="0" /></strong></td>
            </tr>
            <tr>
              <td colspan="3">已兑出：<fmt:formatNumber value="${ecGift.sale_num}" pattern="0" /></td>
            </tr>
            <tr>
              <td colspan="3">浏览量：<strong class="redfont"><fmt:formatNumber value="${ecGift.view_counts}" pattern="0" /></strong>次</td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="viewtab2">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="proxltab">
          <tbody>
            <tr>
              <td><div class="padtop20">&nbsp;&nbsp;<img alt="立即兑换" src="${ctx}/styles/sfmall/images/but_jifen.jpg" style="cursor:pointer;" id="buy_btn" /></div></td>
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
	            <div class="listpic1"><table border="0" cellspacing="0" cellpadding="0" width="120"><tr><td height="110" style="overflow:hidden;" width="120" valign="middle" align="center"><a href="<c:url value='/sfmall/PdShow.do?goods_id=${cur.id}' />"><img src="${ctx}/${fn:substringBefore(cur.main_pic, '.')}_120.jpg" style="max-height:100px;" /></a></td></tr></table></div>
	            <div class="listrxt1">
	              <h3><a href="<c:url value='/sfmall/PdShow.do?goods_id=${cur.id}' />">康佳&nbsp;<c:out value="${cur.pd_sn}" />&nbsp;<c:out value="${cur.pd_name}" /></a></h3>
	              <h4>会员价：￥<fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></h4>
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
    <div class="viewtit2">
      <ul>
        <li class="cur pd_content" id="pdc1">商品介绍</li>
        <li class="pd_content" id="pdc2">规格参数</li>
        <li class="pd_content" id="pdc3">商品评价</li>
        <li class="pd_content" id="pdc4">售后服务</li>
      </ul>
    </div>
    <div class="viewbox4" id="pdc1_pdc1" style="display:'';overflow-x:hidden;">
    	<c:forEach items="${konkaBcompPdContentList}" var="cur">
    		<c:if test="${cur.type eq '1'}"><c:out value="${cur.content}" escapeXml="false" /></c:if>
    	</c:forEach>
    </div>
    <div class="viewbox4" id="pdc2_pdc2" style="display:none;overflow-x:hidden;">
    	<c:forEach items="${konkaBcompPdContentList}" var="cur">
    		<c:if test="${cur.type eq '2'}"><c:out value="${cur.content}" escapeXml="false" /></c:if>
    	</c:forEach>
    </div>
    <div class="viewbox4" id="pdc3_pdc3" style="display:none;overflow-x:hidden;">评价，评价</div>
    <div class="viewbox4" id="pdc4_pdc4" style="display:none;overflow-x:hidden;">
    	<c:forEach items="${konkaBcompPdContentList}" var="cur">
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

<script type="text/javascript" src="${ctx}/scripts/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctx}/scripts/json2.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	// 立即兑换
	$(document).delegate("#buy_btn", "click", function(){
		$("#form_pd_buy").submit();	
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
	
	
	// 兑换按钮
	$(document).delegate("#btn_buy", "click", function(){
		$(".class_form").submit();
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
//]]></script>
</body>
</html>