<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:if test="${ecUser.user_type eq 1 }">康佳EPP内部优惠购机平台</c:if><c:if test="${ecUser.user_type eq 2 }">康佳粉丝会</c:if></title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/shop/css/global1.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/shop/css/epp.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/shop/css/shop.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<!-- head start -->
<div class="topbox">
<jsp:include page="/shop/__inc/top.jsp" flush="true" />
<jsp:include page="/shop/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/shop/__inc/nav.jsp" flush="true" />
<!-- second start -->
<div class="maincont">
  <jsp:include page="/shop/__inc/_mleft.jsp" flush="true" />
  <!--right-->
  <div class="shop_right padbot45">
    <div class="position"><a href="${ctx }/shop/Index.do">首页</a> &gt; <a href="${ctx }/shop/center/Orders.do">我的订单</a> </div>
    <div class="shoptab3">
      <p style="margin-top:15px;font-size:16px;"> 我的订单</p>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="shop_form_table0"  style="border:1px solid #e3e3e3;">
        <tr >
          <td width="40%" >订单流水号:${fn:escapeXml(af.map.trade_index)}</td>
          <td width="60%" >下单时间：
            <fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
        </tr>
        <tr>
          <td colspan="2">订单状态：
            <c:if test="${af.map.state eq -30 }">退货成功</c:if>
            <c:if test="${af.map.state eq -20 }">处理失败</c:if>
            <c:if test="${af.map.state eq -10 }">已取消</c:if>
            <c:if test="${af.map.state eq 0 }"><c:if test="${af.map.pay_way eq 0 or af.map.pay_way eq 1 }">待审核  </c:if> <c:if test="${af.map.pay_way ne 0 and af.map.pay_way ne 1 }">待付款  </c:if></c:if>
            <c:if test="${af.map.state eq 5 }">待确认</c:if>
            <c:if test="${af.map.state eq 10 }">已确认待处理</c:if>
            <c:if test="${af.map.state eq 20 }">订单处理中</c:if>
            <c:if test="${af.map.state eq 30 }">订单处理中</c:if>
            <c:if test="${af.map.state eq 40 }">已发货</c:if>
            <c:if test="${af.map.state eq 50 }">已换货</c:if>
            <c:if test="${af.map.state eq 60 }">交易完成</c:if>
          <c:if test="${af.map.pay_way ne 0 and  af.map.pay_way ne 1}">
             <c:if test="${af.map.state eq 0 }">&nbsp;&nbsp; &nbsp;&nbsp; <a class="btn btn-4" href="<c:url value='/shop/Payment.do?trade_index=${af.map.trade_index}' />"><s></s>付款</a></c:if>
             </c:if>
          </td>
        </tr>
      </table>
      <ul class="shoptit3" >
        <li class="curli" id="li1">订单跟踪 </li>
        <li id="li2">付款信息</li>
      </ul>
      <table id="tbl1" width="100%" border="0" cellpadding="0" cellspacing="0" class="shop_form_table0" >
        <tr style="background:#fff9e9;border:1px solid #e3e3e3;border-bottom:1px solid #ffbe91;">
          <td align="left" width="20%" >运单号</td>
          <td width="20%" align="left">顺丰物流信息 </td>
          <td width="60%" align="left">详细信息 </td>
        </tr>
        <tr style="border:1px solid #e3e3e3;">
          <td align="left">
          <c:if test="${af.map.is_sf eq 0}">
          	暂无
          </c:if>
          <c:if test="${af.map.is_sf eq 1}">
          	${af.map.sf_dh}
          </c:if>
          </td>
          <td >
          <c:if test="${af.map.is_sf eq 0}">
          	暂无
          </c:if>
          <c:if test="${af.map.is_sf eq 1}">
          	 <input type="button" style="cursor: pointer;" class="but9" id="syncBtn" value="实时查询"></input>
          </c:if>
          </td>
          <td>
          	<span id="s1" style="color: green;"></span>
          </td>
        </tr>
         	
      </table>
      <table id="tbl2" width="100%" border="0" cellpadding="0" cellspacing="0" class="shop_form_table0" style="border:1px solid #e3e3e3;display:none;" >
        <tr>
          <td align="left">付款方式：
            <c:if test="${af.map.pay_way eq 0}">货到付款</c:if>
            <c:if test="${af.map.pay_way eq 1}">银行汇款</c:if>
            <c:if test="${af.map.pay_way eq 2}">支付宝</c:if>
            <c:if test="${af.map.pay_way eq 3}">银联</c:if>
            <c:if test="${af.map.pay_way eq 5}">民生e支付</c:if>
            <br/>
            商品金额：
            <fmt:formatNumber value="${t_price}" pattern="￥0.00" />
            <br/>
            应付金额：
            <fmt:formatNumber value="${af.map.total_price}" pattern="￥0.00" />
            <br/><c:if test="${af.map.pay_way gt 1}">
            支付状态：
            <c:if test="${af.map.state eq -10 ||af.map.state eq 0 }">未支付</c:if>
            <c:if test="${af.map.state ne -10 && af.map.state ne 0 }">已付款  
            付款时间：        <fmt:formatDate value="${af.map.pay_date}" pattern="yyyy-MM-dd HH:mm:ss" />
            </c:if>
            </c:if> 
          </td>
        </tr>
      </table>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="shop_form_table0" style="border:1px solid #e3e3e3;" >
        <tr>
          <td align="left" style="color:#00beff;font-weight:bold;border-bottom:1px solid #91beff;">订单信息 </td>
        </tr>
        <tr>
          <td align="left" style="line-height:22px;border-bottom:1px solid #e3e3e3;"><p style="margin-top:1px;font-size:14px;color:#ff0000;font-weight:bold;">订单信息收货人信息</p>
            收 货 人： ${fn:escapeXml(af.map.buyer_name)}<br/>
            地 址：${p_index_name} &nbsp;${fn:escapeXml(af.map.buyer_addr)}<br/>
            手机号码：${fn:escapeXml(af.map.buyer_mp)} <br/>
            固定电话： ${fn:escapeXml(af.map.buyer_tel)}<br/>
            邮政编码： ${fn:escapeXml(af.map.buyer_zip)} </td>
        </tr>
        <tr >
          <td align="left" style="line-height:22px;border-bottom:1px solid #e3e3e3;"><p style="margin-top:1px;font-size:14px;color:#ff0000;font-weight:bold;">支付及配送方式</p>
            支付方式： <c:if test="${af.map.pay_way eq 0}">货到付款</c:if>
              <c:if test="${af.map.pay_way eq 1}">银行汇款</c:if>
              <c:if test="${af.map.pay_way eq 2}">支付宝</c:if>
              <c:if test="${af.map.pay_way eq 3}">银联</c:if>
              <c:if test="${af.map.pay_way eq 4}">财付通</c:if>
              <c:if test="${af.map.pay_way eq 5}">民生e支付</c:if>
            <br/>
            配送方式：
            <c:if test="${af.map.logistic_type eq 1}">顺丰</c:if>
            <c:if test="${af.map.logistic_type eq 2}">EMS</c:if>
            <br/>
            送货日期：
            <c:if test="${af.map.deliver_time eq 0}">只工作日送货（双休日、假日不用送）</c:if>
            <c:if test="${af.map.deliver_time eq 1}">工作日、双休日与假日均可送货</c:if>
            <c:if test="${af.map.deliver_time eq 2}">只双休日、假日送货（工作日不送货）</c:if>
             <br/>
   	 发货前电话确认：
   	 		<c:if test="${af.map.deliver_is_call eq 0}">否</c:if>
            <c:if test="${af.map.deliver_is_call eq 1}">是</c:if>        
          </td>
        </tr>
        <tr >
          <td align="left" style="line-height:22px;border-bottom:1px solid #e3e3e3;"><p style="margin-top:1px;font-size:14px;color:#ff0000;font-weight:bold;">发票信息</p>
            是否开具发票：
            <c:if test="${af.map.bill_is_add eq 0}">否</c:if>
            <c:if test="${af.map.bill_is_add eq 1}">是 <br/>
              发票类型：
              <c:if test="${af.map.bill_type eq 0}">普通发票</c:if>
              <c:if test="${af.map.bill_type eq 1}">增值税发票</c:if>
              <br/>
              发票抬头：<c:if test="${af.map.bill_head eq 0}">个人</c:if>
              <c:if test="${af.map.bill_head eq 1}">单位　${fn:escapeXml(af.map.bill_company)}  </c:if><br/> 
              发票单位：${fn:escapeXml(af.map.bill_company)}  </c:if>
          </td>
        </tr>
           <tr >
          <td align="left" style="line-height:22px;border-bottom:1px solid #e3e3e3;">
          <p style="margin-top:1px;font-size:14px;color:#ff0000;font-weight:bold;">订单备注</p> 
        	${fn:escapeXml(af.map.remark)} <br/>
          </td>
        </tr>
      </table>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="shop_form_table0" style="border:1px solid #e3e3e3;" >
        <tr >
          <td align="left" colspan="5" style="font-weight:bold;border-bottom:1px solid #91beff;">商品清单 </td>
        </tr>
        <tr style="background:#fff9e9;border:1px solid #e3e3e3;border-bottom:1px solid #ffbe91;">
          <td width="15%" align="center" nowrap="nowrap">商品名称</td>
          <td width="7%" align="center" nowrap="nowrap">数量</td>
          <td width="10%" align="left" nowrap="nowrap">单价</td>
          <td width="10%" align="left" nowrap="nowrap">增值服务</td>
          <td width="10%" align="left" nowrap="nowrap">金额</td>
        </tr>
        <c:forEach items="${pshowOrdeDetails}" var="cur">
          <tr style="border:1px solid #e3e3e3;">
            <td align="left">康佳${fn:escapeXml(cur.map.pd_sn)} </td>
            <td align="center">${cur.num}</td>
            <td align="left"><fmt:formatNumber value="${fn:escapeXml(cur.price)}" pattern="￥0.00" /></td>
             <td align="left">
             <c:forEach items="${ecBindingDetailList}" var="cur2">
             <c:if test="${cur.bill_item_id eq cur2.details_id }">
             ${fn:escapeXml(cur2.goods_name)} &nbsp;<fmt:formatNumber value="${cur2.price}" pattern="￥#,#00" />*${cur2.num} <br/>
             </c:if>
             </c:forEach>
             </td>
            <td align="left"><fmt:formatNumber value="${fn:escapeXml(cur.total_price)}" pattern="￥0.00" /></td>
          </tr>
        </c:forEach>
      </table>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="shop_form_table0" style="border:1px solid #e3e3e3;" >
        <tr >
          <td align="left"  colspan="2" style="color:#005eff;font-weight:bold;border-bottom:1px solid #91beff;">结算信息 </td>
        </tr>
        <tr style="height:60px;">
          <td width="60%" align="left"  > 
          <c:forEach items="${pshowOrdeDetails}" var="cur">康佳${fn:escapeXml(cur.map.pd_sn)}
	         <font color="#ff5300">
	          <fmt:formatNumber value="${fn:escapeXml(cur.price)}" pattern="￥0.00" /> * ${cur.num}  
	          </font> 
	         <c:forEach items="${ecBindingDetailList}" var="cur2">
            	 <c:if test="${cur.bill_item_id eq cur2.details_id }">
            	 + ${fn:escapeXml(cur2.goods_name)}  <font color="#ff5300"><fmt:formatNumber value="${cur2.price}" pattern="￥#,#00" />*${cur2.num} </font>  
             	</c:if>
             </c:forEach> <br/>
           </c:forEach>
           <c:forEach items="${ecVouchersList}" var="cur"><br/>
           -${fn:escapeXml(cur.title)}  <font color="#ff5300">  <fmt:formatNumber value="${fn:escapeXml(cur.price)}" pattern="￥0.00" /></font> 
           </c:forEach>           
            </td>
          <td width="40%" align="left" ><br/>
            <br/>
            订单总金额：<font color="#ff5300">
            <fmt:formatNumber value="${af.map.total_price}" pattern="￥0.00元" />
            </font> 
           - 抵扣金额：<font color="#ff5300">
            <fmt:formatNumber value="${af.map.dedu_price}" pattern="￥0.00元" />
            </font>    
     <br/>=  应付金额：<font color="#ff5300">
            <fmt:formatNumber value="${af.map.pay_price}" pattern="￥0.00元" />
            </font>          
            </td>
        </tr>
      </table>
      <div style="margin:10px 300px 20px 0;float:right;">
        <input class="inputbtn" type="button" name="order_back" value="返回" onclick="history.back();" />
       <c:if test="${af.map.pay_way ne 0 and  af.map.pay_way ne 1}">
        <c:if test="${af.map.state eq 0 }">&nbsp;&nbsp; &nbsp;&nbsp; <a class="btn btn-4" href="<c:url value='/shop/Payment.do?trade_index=${af.map.trade_index}' />"><s></s>付款</a></c:if>
     	</c:if>
      </div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<jsp:include page="/shop/__inc/footer.jsp" flush="true" />
<!-- six footer -->
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){  
	
  var id = "${af.map.id}";

  $("#syncBtn").click(function(){
		$("#s1").empty();
		$("#syncBtn").attr("value", "正在查询...").attr("disabled", "true");
		$.ajax({
			type: "post",
			url: "${ctx}/shop/center/Orders.do?method=sfList",
			data: {"id" : id},
			dataType: "json",
			error: function(request, settings) {
				}, 
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
//]]></script>
</body>
</html>
