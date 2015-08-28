<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单打印</title>
<style>
	table{
		border-collapse:collapse;
	}
	table tr{
	    line-height:25px;
	}
	.title_t{
		font-size: 11pt;
	}
	.title_content{
		font-size: 11pt;
	}
	.table_head{
		font-size: 11pt;
	}
</style>
</head>
<body>
	<div style="padding-left: 5px; padding-right: 5px">
		<table width="100%" border="0" >
			<tr style="font-size: 15pt">
				<th colspan="7" align="center" nowrap="nowrap">
				<span style="padding-right: 10px;">${shop.customer_name}&nbsp;
				<c:if test="${order.doc_type != 'ZFRE' }">进货单</c:if>
				<c:if test="${order.doc_type eq 'ZFRE' }">退货单</c:if>
				</span>
				</th>
			</tr>
			<tr style="font-size: 9pt">
				<td colspan="7" align="right" nowrap="nowrap">
					<span style="margin-right: 65px">下单时间：<fmt:formatDate value="${order.order_date}" type="both" dateStyle="long" /></span>
					销售单号：${order.trade_index }
				</td>
			</tr>
			<tr style="height: 5px" ><td colspan="7"></td></tr>
			<tr>
				<td nowrap="nowrap" class="title_t" align="right">供&nbsp;应&nbsp;商：</td>
				<td nowrap="nowrap" colspan="3" width="40%" class="title_content">康佳集团股份有限公司</td>
				<td nowrap="nowrap" class="title_t" align="right">交货方式：</td>
				<td nowrap="nowrap" colspan="2" class="title_content">
					<c:if test="${order.send_type eq 1}">
						<label><input type='checkbox' id="send_type1" name="send_type" checked="checked"  value="0" disabled="disabled"></input>自提</label>
	          			&nbsp;&nbsp;
	          			<label><input type='checkbox' id="send_type2" name="send_type"  value="1" disabled="disabled"></input>配送</label>
					</c:if>
					<c:if test="${order.send_type eq 2}">
						<label><input type='checkbox' id="send_type1" name="send_type"  value="0" disabled="disabled"></input>自提</label>
	          			&nbsp;&nbsp;
	          			<label><input type='checkbox' id="send_type2" name="send_type" checked="checked" value="1" disabled="disabled"></input>配送</label>
					</c:if>
          		</td>
          	</tr>
			<tr>
				<td nowrap="nowrap" class="title_t" align="right">联&nbsp;系&nbsp;人：</td>
				<td nowrap="nowrap" colspan="3" class="title_content">${order.user_name}</td>
				<td nowrap="nowrap" class="title_t" align="right">联系电话：</td>
				<td nowrap="nowrap" colspan="2" class="title_content">${order.user_phone}</td>
          	</tr>
			<tr>
				<td nowrap="nowrap" class="title_t" align="right">收货地址：</td>
				<td nowrap="nowrap" colspan="3" class="title_content">
					${order.user_addr }
				</td>
				<td nowrap="nowrap" class="title_t" align="right">入库仓位：</td>
				<td nowrap="nowrap" colspan="2" class="title_content">${store_name }</td>
          	</tr>
		</table>
		<table width="100%" border="1" >
			<tr >
				<th nowrap="nowrap" width="9%" align="center"><b>商品类型</b></th>
				<th nowrap="nowrap" width="11%" align="center"><b>机型</b></th>
				<th nowrap="nowrap" width="5%" align="center"><b>数量</b></th>
				<th nowrap="nowrap" width="5%" align="center"><b>单位</b></th>
				<th nowrap="nowrap" width="10%" align="center"><b>单价</b></th>
				<th nowrap="nowrap" width="13%" align="center"><b>金额</b></th>
				<th nowrap="nowrap" width="13%" align="center"><b>折扣金额</b></th>
				<th nowrap="nowrap" width="13%" align="center"><b>折后金额</b></th>
				<th nowrap="nowrap" align="center"><b>行备注</b></th>
			</tr>
			<c:set var="num_sum" value="0"></c:set>
			<c:set var="price_sum" value="0"></c:set>
			<c:set var="money_sum" value="0"></c:set>
			<c:set var="dis_sum" value="0"></c:set>
			<c:forEach items="${detailsList}" var="cur" varStatus="vs">
			<tr class="title_content">
				<td align="center" >${cur.brand_name}</td>
				<td align="center">${cur.pd_name }</td>
				<td nowrap="nowrap" align="right" style="padding-right: 3px">${cur.good_count}</td>
				<td nowrap="nowrap" align="center">${cur.good_unit}</td>
				<td nowrap="nowrap" align="right" style="padding-right: 3px">${cur.good_price}</td>
				<td nowrap="nowrap" align="right" style="padding-right: 3px">${cur.good_sum_price}</td>
				<td nowrap="nowrap" align="right">${cur.good_discount}</td>
				<td nowrap="nowrap" align="right">${cur.good_sum_price - cur.good_discount}</td>
				<td nowrap="nowrap">${cur.pd_remark}</td>
				<c:set var="num_sum" value="${num_sum+ cur.good_count}"></c:set>
				<c:set var="price_sum" value="${price_sum+ cur.good_price}"></c:set>
				<c:set var="money_sum" value="${money_sum+ cur.good_sum_price}"></c:set>
			</tr>
			</c:forEach>
			<tr class="title_content">
				<td align="center" colspan="2">合计</td>
				<td align="right" style="padding-right: 3px">${num_sum }</td>
				<td align="right"></td>
				<td align="right"></td>
				<td align="right" style="padding-right: 3px">${money_sum }</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="9" class="title_t">备注：${order.remark }</td>
			</tr>
			<tr style="font-size: 9pt">
				<td colspan="9">打印时间：<fmt:formatDate value="${nowDate}" type="both" dateStyle="long" /></td>
			</tr>
		</table>
		<table width="100%" style="font-size: 13pt">
			<tr class="title_t">
				<td >总经理签字：</td>
				<td >财务签字：</td>
				<td >主管签字：</td>
				<td >采购员：</td>
			</tr>
		</table>
	</div>
	<div align="center" id="div_button">
	  <input name="print" type="button" class="bgButtonPrint" value="打印"/>
	  <input name="close" type="button" class="bgButtonBack" value="关闭" />
	</div>



<script type="text/javascript" src="${ctx}/scripts/print.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=discuz"></script>
<script type="text/javascript">//<![CDATA[
var api = frameElement.api;
$(document).ready(function(){
	$(".bgButtonPrint").click(function(){
		$("#div_button").hide();
		window.print();
		$("#div_button").show();
	});
	 $(".bgButtonBack").click(function(){
		 api.close();
	});
});
		
//]]></script>
</body>
</html>