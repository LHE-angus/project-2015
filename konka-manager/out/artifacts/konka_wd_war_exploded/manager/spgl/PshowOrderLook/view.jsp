<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单详细</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	.Noprint {DISPLAY:  none;}
</style>
</head>
<body>
<div style="width:100%;overflow-x:auto;">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;"/> 当前位置：订单管理 &gt; 订单记录 &gt; 订单详细</div>
  <div class="rtabcont1" >
  <div class="rtabcont1" id="div_print">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th colspan="7">订单信息</th>
        </tr>
        <tr>
          <td class="title_item" width="15%">订单流水号：</td>
          <td colspan="4"><span><font color="red">${fn:escapeXml(af.map.trade_index)}</font></span></td>
          <td class="title_item" width="15%">下单人姓名：</td>
          <td colspan="1">${af.map.order_user_name}</td>
        </tr>
        <tr>
          <td class="title_item" width="15%">送货日期：</td>
          <td colspan="4">
            <c:if test="${af.map.deliver_time eq 0}">只双休日、假日送货（工作日不用送）</c:if>
            <c:if test="${af.map.deliver_time eq 1}">工作日、双休日与假日均可送货</c:if>
            <c:if test="${af.map.deliver_time eq 2}">只工作日送货（双休日、假日不用送）</c:if>
           </td>
          <td class="title_item" width="15%">订单顺丰状态：<c:if test="${af.map.order_state eq 1}">
          	<input type="button" style="cursor: pointer;" class="but9" id="syncBtn" value="实时查询"></input>
          	</c:if></td>
         <td colspan="1">
          	<c:if test="${af.map.order_state eq 0}">
          		该订单没有通过顺丰物流发货
          		<c:if test="${ not empty af.map.is_add}">
          		<input type="button" style="cursor: pointer;" class="but9" id="syncBtn2" value="点击入顺丰"></input>
          		</c:if>
          	</c:if>
          	<c:if test="${is_admin eq 0 and af.map.user_id eq 1}"> 
          	  <input type="button" style="cursor: pointer;" class="but9" id="syncBtn3" value="下订单返回"></input>
          	  <input type="button" style="cursor: pointer;" class="but9" id="syncBtn4" value="路由查询"></input>
          	  <input type="button" style="cursor: pointer;" class="but9" id="syncBtn5" value="运单查询"></input>   
          	</c:if>
          	<span id="s1" style="color: green;"></span>
          </td>
        </tr>
        <tr>
          <td class="title_item" width="15%">发货前电话确认：</td>
          <td colspan="4">
            <c:if test="${af.map.deliver_is_call eq 0}">否</c:if>
            <c:if test="${af.map.deliver_is_call eq 1}">是</c:if>
            </td>
          <td class="title_item" width="15%">发票是否索要：</td>
          <td colspan="1">
          <c:if test="${af.map.bill_is_add eq 0}">否</c:if>
            <c:if test="${af.map.bill_is_add eq 1}">是</c:if></td>
        </tr>
        <tr>
          <td class="title_item" width="15%">发票类型：</td>
          <td colspan="4">
            <c:if test="${af.map.bill_type eq 0}">普通发票</c:if>
            <c:if test="${af.map.bill_type eq 1}">增值税发票</c:if>
            </td>
          <td class="title_item" width="15%">发票抬头：</td>
          <td colspan="1">
          <c:if test="${af.map.bill_head eq 0}">个人</c:if>
            <c:if test="${af.map.bill_head eq 1}">单位</c:if></td>
        </tr>
        <tr>
          <td class="title_item" width="15%">发票内容：</td>
          <td colspan="4">
            <c:if test="${af.map.bill_content eq 0}">明细</c:if>
            <c:if test="${af.map.bill_content eq 1}">办公用品</c:if>
            <c:if test="${af.map.bill_content eq 2}">电脑配件</c:if>
            <c:if test="${af.map.bill_content eq 3}">耗材</c:if>
            </td>
          <td class="title_item" width="15%">发票单位：</td>
          <td colspan="1">
          	${af.map.bill_company}
          </td>
        </tr>
        <tr>
          <td class="title_item" width="15%">货款支付方式：</td>
          <td colspan="4"><c:if test="${af.map.pay_way eq 0}">货到付款</c:if>
              <c:if test="${af.map.pay_way eq 1}">银行汇款</c:if>
              <c:if test="${af.map.pay_way eq 2}">支付宝</c:if>
              <c:if test="${af.map.pay_way eq 3}">银联</c:if>
              <c:if test="${af.map.pay_way eq 4}">财付通</c:if>
              <c:if test="${af.map.pay_way eq 5}">民生银行</c:if>
              <c:if test="${af.map.pay_way eq 8}">嘿客代收货款</c:if>
              <c:if test="${af.map.pay_way eq 9}">线下处理</c:if> 
              <c:if test="${not empty af.map.trade_no}">(支付单号<c:out value="${af.map.trade_no}" />)</c:if>
              </td>
           <td class="title_item" width="15%">运单号：</td>
          <td colspan="1">
          	<span><font color="red">${af.map.logistic_sn}</font></span>
          </td>   
        </tr>
        <tr>
          <td class="title_item" width="15%">是否自提：</td>
          <td colspan="4"><c:if test="${af.map.is_self eq 0}">否</c:if>
              <c:if test="${af.map.is_self eq 1}">是</c:if>
              </td>
           <td class="title_item" width="15%">是否安装调试：</td>
          <td colspan="1">
          	<c:if test="${af.map.deliver_way ne 1}">否</c:if>
            <c:if test="${af.map.deliver_way eq 1}">是</c:if>
          </td>   
        </tr>
        <c:if test="${not empty af.map.ecVouchersList }">
         <tr>
            <td class="title_item" width="15%" >使用购物券：</td>
            <td colspan="6"> <c:forEach items="${af.map.ecVouchersList }" var="cur">
            ${fn:escapeXml(cur.title)} &nbsp;￥<font color="red"> ${fn:escapeXml(cur.price)}</font>元<br/>
            </c:forEach>
			</td>
         </tr>
        </c:if>
        <tr>
            <td class="title_item" width="15%" >顺丰嘿客订单号：</td><td colspan="6"><c:out value="${af.map.sfhk_order}" />  </td>  
         </tr>
        <tr>
            <td class="title_item" width="15%" >客户备注：</td><td colspan="6"><c:out value="${af.map.cs_mark}" />  </td>  
         </tr>
        <tr>
            <td class="title_item" width="15%" >备注：</td><td colspan="6"><c:out value="${af.map.remark}" />  </td>
         </tr>
        <tr>
          <td colspan="7"><table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
              <tr>
                <th colspan="7">订单产品信息</th>
              </tr>
              <tr class="title_top">
                <td width="30%" nowrap="nowrap" colspan="2">(型号)商品名称</td>
                <td width="5%" nowrap="nowrap">数量</td>
                <td width="8%" nowrap="nowrap">单价</td>
                <td width="8%" nowrap="nowrap">金额</td>
                <td width="8%" nowrap="nowrap">增值服务</td>
                <td width="20%" nowrap="nowrap">备注</td>
              </tr>
              <c:forEach items="${pshowOrdeDetails}" var="cur">
                <tr>
                  <td align="left" colspan="2">(${cur.map.pd_sn })${cur.pd_name }</td>
                  <td align="center">${fn:escapeXml(cur.num)}</td>
                  <td align="center"><fmt:formatNumber value="${fn:escapeXml(cur.price)}" pattern="0.00" /></td>
                  <td align="center"><fmt:formatNumber value="${fn:escapeXml(cur.total_price)}" pattern="0.00" /></td>
                  <td align="center">
						 <c:forEach items="${bddetailsList}" var="cu">
						 	<c:if test="${cu.details_id eq cur.bill_item_id}">
						 		${cu.goods_name} ￥${cu.price}<br />
						 	</c:if>
						 </c:forEach>
				  </td>
                  <td align="left"><c:if test="${cur.state eq 1 }">退货&nbsp;</c:if><c:if test="${cur.state eq 2 }">换货&nbsp;</c:if>${fn:escapeXml(cur.remark)}</td>
                </tr>
              </c:forEach>
            </table></td>
        </tr>
        <c:if test="${not empty zOrderlist }">
         <tr>
            <td colspan="7" align="left">关联子订单</td>
         </tr>
        <c:forEach items="${zOrderlist}" var="cur"> 
         <tr>
            <td class="title_item" >订单编号：</td><td colspan="4"><font color="red"><c:out value="${cur.trade_index}" /></font> &nbsp; </td>
            <td class="title_item"  >订单状态：</td>
            <td><c:if test="${cur.state eq -1}">已取消</c:if><c:if test="${cur.state eq -30 }">已退货</c:if> <c:if test="${cur.state eq -20 }">审核未通过</c:if><c:if test="${cur.state eq -10 }">已关闭</c:if><c:if test="${cur.state eq 0 }">已预订</c:if> <c:if test="${cur.state eq 5 }">待确认</c:if><c:if test="${cur.state eq 10 }">已确认</c:if><c:if test="${cur.state eq 20 }">审核通过</c:if>  <c:if test="${cur.state eq 30 }">下发处理</c:if><c:if test="${cur.state eq 40 }">商家发货</c:if><c:if test="${cur.state eq 50 }">客户已换货</c:if><c:if test="${cur.state eq 60 }">确认收货</c:if> <c:if test="${cur.pay_way eq 9}">（线下处理）</c:if></td>
         </tr>
         <tr>
          <td colspan="7"><table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
              <tr>
                <th colspan="7">关联订单产品信息</th>
              </tr>
              <tr class="title_top">
                <td width="30%" nowrap="nowrap" colspan="2">(型号)商品名称</td>
                <td width="5%" nowrap="nowrap">数量</td>
                <td width="8%" nowrap="nowrap">单价</td>
                <td width="8%" nowrap="nowrap">金额</td>
                <td width="8%" nowrap="nowrap">增值服务</td> 
                <td width="20%" nowrap="nowrap">备注</td>
              </tr>
              <c:forEach items="${cur.pshowOrdeDetailsList}" var="cur1">
                <tr>
                  <td align="left" colspan="2">(${cur1.map.pd_sn })${cur1.pd_name }</td>
                  <td align="center">${fn:escapeXml(cur1.num)}</td>
                  <td align="center"><fmt:formatNumber value="${fn:escapeXml(cur1.price)}" pattern="0.00" /></td>
                  <td align="center"><fmt:formatNumber value="${fn:escapeXml(cur1.total_price)}" pattern="0.00" /></td> 
                  <td align="center">
						 <c:forEach items="${bddetailsList}" var="cu">
						 	<c:if test="${cu.details_id eq cur1.bill_item_id}">
						 		${cu.goods_name} ￥${cu.price}<br />
						 	</c:if>
						 </c:forEach>
				  </td>
                  <td align="left"><c:if test="${cur1.state eq 1 }">退货&nbsp;</c:if><c:if test="${cur1.state eq 2 }">换货&nbsp;</c:if>${fn:escapeXml(cur1.remark)}</td>
                </tr>
              </c:forEach>
            </table></td>
        </tr>
        </c:forEach></c:if>
         <c:if test="${not empty af.map.pshowOrdeExchangeList }">
          <tr>
            <td colspan="7" align="left">退换货跟踪表</td>
         </tr>
          <tr>
            <td colspan="7" align="left">
            <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
            <tr class="tabtt1">
	           <td width="5%" nowrap="nowrap" align="center" rowspan="2">序号</td> 
	           <td width="5%" nowrap="nowrap" align="center" rowspan="2">型号</td> 
	           <td width="5%" nowrap="nowrap" align="center" rowspan="2">数量</td>
	           <td width="5%" nowrap="nowrap" align="center" rowspan="2">金额</td> 
	           <td width="25%" nowrap="nowrap" align="center" colspan="5">客服跟踪</td>
	           <td width="20%" nowrap="nowrap" align="center" colspan="4">财务跟踪</td>  
          </tr>
          <tr class="tabtt1">
	          <td width="5%" nowrap="nowrap" align="center">机器状态</td>
	          <td width="5%" nowrap="nowrap" align="center">退机类型</td>
	          <td width="5%" nowrap="nowrap" align="center">是否换货</td>
	          <td width="5%" nowrap="nowrap" align="center">报险方式</td>
	          <td width="5%" nowrap="nowrap" align="center">报险金额</td>
	          <td width="5%" nowrap="nowrap" align="center">机器入库</td>
	          <td width="5%" nowrap="nowrap" align="center">财务核销</td>
	          <td width="5%" nowrap="nowrap" align="center">理赔是否到账</td>
	          <td width="5%" nowrap="nowrap" align="center">退款金额</td>
          </tr>          
          <c:forEach var="cur" items="${af.map.pshowOrdeExchangeList}" varStatus="vs">
            <tr>
	         <td height="28"  align="center">${vs.count}</td>
	         <td align="center"><c:out value="${cur.pshowOrdeDetails.map.pd_sn}" /></td>
	         <td align="center"><c:out value="${cur.pshowOrdeDetails.num}" /></td>
	         <td align="center"><c:out value="${cur.pshowOrdeDetails.total_price}" /></td> 
	         <td align="center">
	         <c:if test="${cur.pd_step eq 1 }">客户家里</c:if>  <c:if test="${cur.pd_step eq 2 }">拉回在途</c:if><c:if test="${cur.pd_step eq 3 }">分公司</c:if>
	         </td> 
	         <td align="left">
	         <c:if test="${cur.exchange_info eq 1 }">质量机退货</c:if>  <c:if test="${cur.exchange_info eq 2 }">拒收</c:if><c:if test="${cur.exchange_info eq 3 }">好机退货</c:if>
	         </td>
	         <td align="center"><c:if test="${cur.is_exchange eq 1 }">退货</c:if>  <c:if test="${cur.is_exchange eq 2 }">换货</c:if></td>
	       	 <td align="center"><c:if test="${cur.insurance_way eq 1 }">顺丰理赔</c:if>  <c:if test="${cur.insurance_way eq 2 }">保险+顺丰理赔</c:if><c:if test="${cur.insurance_way eq 3 }">保险理赔</c:if></td>
	       	 <td align="center"><c:out value="${cur.insurance_price}" /></td>
	       	 <td align="center"><c:if test="${cur.pd_store eq 1 }">未入库</c:if>  <c:if test="${cur.pd_store eq 2 }">入库</c:if></td>
	       	 <td align="center"><c:if test="${cur.exchange_state eq 1 }">未退换货</c:if>  <c:if test="${cur.exchange_state eq 2 }">已退换货</c:if></td>
	       	 <td align="center"><c:if test="${cur.insurance_state eq 1 }">未到账</c:if>  <c:if test="${cur.insurance_state eq 2 }">已到账</c:if></td>
	       	 <td align="center"><c:out value="${cur.exchange_price}" /></td>
	       	</tr> 
          </c:forEach>
             </table>
            </td>
         </tr>
         </c:if>
        <tr>
          <td class="title_item" width="15%">收货人固定电话：</td>
          <td>${fn:escapeXml(af.map.buyer_tel)}</td>
          <td class="title_item" width="15%">收货人手机：</td>
          <td colspan="4">${fn:escapeXml(af.map.buyer_mp)}</td>
        </tr>
        <tr>
          <td class="title_item" width="15%">购买人姓名：</td>
          <td>${fn:escapeXml(af.map.buyer_name)}</td>
           <td class="title_item" width="15%">收货人所属地区：</td>
          <td colspan="4">${p_index_name}</td>
        </tr> 
        <tr>
          <td class="title_item" width="15%">订单产品数量：</td>
          <td >${t_num }</td>
          <td class="title_item" width="15%">订单总金额：</td>
          <td colspan="4"><fmt:formatNumber value="${af.map.total_price}" pattern="0.00" /> (元)</td>
        </tr>
         <tr>
          <td class="title_item" width="15%">抵扣金额：</td>
          <td>${fn:escapeXml(af.map.dedu_price)} (元)</td>
          <td class="title_item" width="15%">应付金额：</td>
          <td colspan="4"><fmt:formatNumber value="${af.map.pay_price}" pattern="0.00" /> (元)</td>
        </tr>
        <tr>
        	<td class="title_item" width="15%">订单状态：</td>
          	<td ><c:if test="${af.map.state eq -1}">已取消</c:if>
             <c:if test="${af.map.state eq -30 }">已退货</c:if>
	         <c:if test="${af.map.state eq -20 }">审核未通过</c:if>
	         <c:if test="${af.map.state eq -10 }">已关闭</c:if>
	         <c:if test="${af.map.state eq 0 }">已预订</c:if>
	         <c:if test="${af.map.state eq 5 }">待确认</c:if>
	         <c:if test="${af.map.state eq 10 }">已确认</c:if>
	         <c:if test="${af.map.state eq 20 }">审核通过</c:if>
	         <c:if test="${af.map.state eq 30 }">下发处理</c:if>
	         <c:if test="${af.map.state eq 40 }">商家发货</c:if>
	         <c:if test="${af.map.state eq 50 }">客户已换货</c:if>
	         <c:if test="${af.map.state eq 60 }">确认收货</c:if>
	         <c:if test="${af.map.pay_way eq 9}">（线下处理）</c:if>
	         </td>
	         <td class="title_item" width="15%">收货地址：</td>
             <td colspan="4">${fn:escapeXml(af.map.buyer_addr)}</td>
        </tr>
          <tr>
            <th colspan="7">订单审核信息</th>
          </tr><c:if test="${empty af.map.par_order_id}"> 
          <tr>
          	<td align="left" colspan="7">&nbsp;客户下单时间 ：<fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td> 
           </tr></c:if>
          <tr>
            <td colspan="7"><table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass"> 
                <tr>
                  <td class="title_item" width="5%" style="text-align:center;" nowrap="nowrap">序号</td>
                  <td class="title_item" width="18%" style="text-align:center;" nowrap="nowrap">登录名</td>
                   <td class="title_item" width="10%" style="text-align:center;" nowrap="nowrap">真实姓名</td>
                  <td class="title_item" width="18%" style="text-align:center;" nowrap="nowrap">操作时间</td>
                  <td class="title_item" width="18%" style="text-align:center;" nowrap="nowrap">订单应付金额</td>
                  <td class="title_item" width="18%" style="text-align:center;" nowrap="nowrap">审核结果</td>
                  <td class="title_item" style="text-align:center;" nowrap="nowrap">意见</td>
                </tr>
                <c:forEach items="${PshowOrdeAudits}" var="cur" varStatus="st">
                  <tr>
                    <td align="center" >${st.count}</td>
                    <td align="center">${cur.opr_user_real_name } </td>
                     <td align="center">${cur.map.real_name} </td>
                    <td align="center"><fmt:formatDate value="${cur.oper_date }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td align="center">${cur.total_price}</td>
                    <td align="center"><c:if test="${cur.state eq -1}">已取消</c:if>
			            <c:if test="${cur.state eq -30 }">已退货</c:if>
				         <c:if test="${cur.state eq -20 }">审核未通过</c:if>
				         <c:if test="${cur.state eq -10 }">已关闭</c:if>
				         <c:if test="${cur.state eq 0 }">已预订</c:if>
				         <c:if test="${cur.state eq 5 }">待确认</c:if>
				         <c:if test="${cur.state eq 10 }">已确认</c:if>
				         <c:if test="${cur.state eq 20 }">审核通过</c:if>
				         <c:if test="${cur.state eq 30 }">下发处理</c:if>
				         <c:if test="${cur.state eq 40 }">商家发货</c:if>
				         <c:if test="${cur.state eq 50 }">客户已换货</c:if>
				         <c:if test="${cur.state eq 60 }">确认收货</c:if>
				         <c:if test="${cur.state eq 70 }"><c:if test="${af.map.order_user_id eq 128008}">(顺丰嘿客)</c:if>确认回款</c:if>
				         <c:if test="${cur.state eq 80 }">退货</c:if>
					     <c:if test="${cur.state eq 90 }">换货</c:if>
				         <c:if test="${af.map.pay_way eq 9}">（线下处理）</c:if>
				         </td>
                    <td align="left">${cur.remark}</td>
                  </tr>
                </c:forEach>
              </table></td>
         </tr>
        <tr>
          <td colspan="7" align="center">
            &nbsp;&nbsp;
            <input class="bgButtonBack" type="submit" name="order_back" value="返回" onclick="history.back();return false;" />
            <input class="bgButtonPrint" type="button"  value="打印" onclick="printdiv('div_print');" /> 
            <input class="but_excel" type="button"  value="导出" onclick="location.href='?method=sheet&id=${af.map.id}';" /> 
            </td>
        </tr>
      </table>
      </div>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){ 
var id = "${af.map.id}";
	
  $("#syncBtn").click(function(){
	  
	$("#s1").empty();
	$("#syncBtn").attr("value", "正在查询...").attr("disabled", "true");
	$.ajax({
		type: "post",
		url: "${ctx}/manager/spgl/PshowOrderLook.do?method=sfList",
		data: {"id" : id,"timestamp":new Date().getTime()},
		dataType: "json",
		error: function(request, settings) {
			}, 
		success: function(data) {
			for(var i = 0 ;i < data.length ;i++){
				var span = $("<span>"+data[i].accept_time+","+data[i].remark+"</span><br/>");
				span.appendTo($("#s1"));
				$("#syncBtn").removeAttr("disabled").attr("value","实时查询");
			}
			window.parent.resizeFrameHeight('mainFrame', 3);
		}
	});

  }); 


var trade_index = "${af.map.trade_index}";
$("#syncBtn2").click(function(){
	$("#syncBtn2").attr("value", "正在查询...").attr("disabled", "true");
	$.ajax({
		type: "POST",
		url: "PshowOrderLook.do",
		data: "method=orderSend&trade_index=" + trade_index +"&timestamp=" +new Date().getTime(),
		dataType: "text",
		error: function(request, settings) {
			
		},
		success: function(result) {
			if(result=="1"){
				alert("订单入顺丰成功！");
				$("#syncBtn2").remove();
			}else if(result=="0"){
				alert("订单入顺丰失败！");
				$("#syncBtn2").removeAttr("disabled");
			}else{
				alert(result);
				$("#syncBtn2").removeAttr("disabled").attr("value","点击入顺丰");
			}
		}
	});

});


$("#syncBtn3").click(function(){
	$("#syncBtn3").attr("value", "正在查询...").attr("disabled", "true");
	$.ajax({
		type: "POST",
		url: "PshowOrderLook.do",
		data: "method=orderSend2&trade_index=" + trade_index+"&timestamp=" +new Date().getTime(),
		dataType: "text",
		error: function(request, settings) {
			
		},
		success: function(result) {
			alert(result);
			$("#syncBtn3").removeAttr("disabled").attr("value","查询顺丰返回结果");
		}
	});

});


$("#syncBtn4").click(function(){
	$("#syncBtn4").attr("value", "正在查询...").attr("disabled", "true");
	$.ajax({
		type: "POST",
		url: "PshowOrderLook.do",
		data: "method=sfList2&id=" + id,
		dataType: "text",
		error: function(request, settings) {
			
		},
		success: function(result) {
			alert(result);
			$("#syncBtn4").removeAttr("disabled").attr("value","查询路由信息");
		}
	});

});

$("#syncBtn5").click(function(){
	$("#syncBtn5").attr("value", "正在查询...").attr("disabled", "true");
	$.ajax({
		type: "POST",
		url: "PshowOrderLook.do",
		data: "method=orderFind&trade_index=" + trade_index +"&timestamp=" +new Date().getTime(),
		dataType: "text",
		error: function(request, settings) {
			
		},
		success: function(result) {
			alert(result);
			$("#syncBtn5").removeAttr("disabled").attr("value","运单查询");
		}
	});

});


});  


function printdiv(printpage)
{
var headstr = "<html><head><title></title></head><body>";
var footstr = "</body>";
var newstr = document.all.item(printpage).innerHTML;
var oldstr = document.body.innerHTML;
	newstr = newstr.replace("bgButtonBack","Noprint");
	newstr = newstr.replace("but_excel","Noprint");
	newstr = newstr.replace("bgButtonPrint","Noprint");
	newstr = newstr.replace("but9","Noprint");
	newstr = newstr.replace('border="0"','border="1"');
	newstr = newstr.replace('tableClass','');
	
document.body.innerHTML = headstr+newstr+footstr;
window.print(); 
document.body.innerHTML = oldstr;
return false;
}

                                        
//]]></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
