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
				<span style="padding-right: 10px;">${sell_cust_name}&nbsp;
				<c:if test="${af.map.bill_type eq 20 }">零售单</c:if>
				<c:if test="${af.map.bill_type eq 22 }">零售退货单</c:if>
				<c:if test="${af.map.bill_type eq 10 }">采购单</c:if>
				<c:if test="${af.map.bill_type eq 11 }">采购退货单</c:if>
				</span>
				</th>
			</tr>
			<tr style="font-size: 9pt">
				<td colspan="7" align="right" nowrap="nowrap">
					<span style="margin-right: 65px">下单时间：<fmt:formatDate value="${af.map.add_date}" type="both" dateStyle="long" /></span>
					销售单号：${af.map.bill_sn }
				</td>
			</tr>
			<tr style="height: 5px"><td></td></tr>
			<tr>
				<c:if test="${af.map.bill_type eq 20 or af.map.bill_type eq 21}">
					<td nowrap="nowrap" width="50px" class="title_t">销售单位：</td>
					<td nowrap="nowrap" colspan="3" width="50%" class="title_content">${p_name }</td>
				</c:if>
				<c:if test="${af.map.bill_type eq 10 or af.map.bill_type eq 11}">
					<td nowrap="nowrap" width="50px" class="title_t">供应商：</td>
					<td nowrap="nowrap" colspan="3" width="50%" class="title_content">${partner.partner_name }</td>
				</c:if>
				<td nowrap="nowrap" width="50px" class="title_t">交货方式：</td>
				<td nowrap="nowrap" colspan="2" class="title_content">
					<c:if test="${check1}">
						<label><input type='checkbox' id="send_type1" name="send_type" checked="checked"  value="0" disabled="disabled"></input>自提</label>
	          			&nbsp;&nbsp;
	          			<label><input type='checkbox' id="send_type2" name="send_type"  value="1" disabled="disabled"></input>配送</label>
					</c:if>
					<c:if test="${check2}">
						<label><input type='checkbox' id="send_type1" name="send_type"  value="0" disabled="disabled"></input>自提</label>
	          			&nbsp;&nbsp;
	          			<label><input type='checkbox' id="send_type2" name="send_type" checked="checked" value="1" disabled="disabled"></input>配送</label>
					</c:if>
          		</td>
          	</tr>
			<tr>
				<c:if test="${af.map.bill_type eq 20 or af.map.bill_type eq 21}">
					<td nowrap="nowrap" class="title_t">顾客姓名：</td>
					<td nowrap="nowrap" colspan="3" class="title_content">${partner.link_name}</td>
				</c:if>
				<c:if test="${af.map.bill_type eq 10 or af.map.bill_type eq 11}">
					<td nowrap="nowrap" class="title_t">联&nbsp;系&nbsp;人：</td>
					<td nowrap="nowrap" colspan="3" class="title_content">${partner.link_name}</td>
				</c:if>
				<td nowrap="nowrap" class="title_t">联系电话：</td>
				<td nowrap="nowrap" colspan="2" class="title_content">${partner.link_tel}</td>
          	</tr>
			<c:if test="${af.map.bill_type eq 20 or af.map.bill_type eq 21}">
			<tr>
				<td nowrap="nowrap" class="title_t">收货人：</td>
				<td nowrap="nowrap" colspan="3" class="title_content">${partner.link_name}</td>
				<td nowrap="nowrap" class="title_t">收货人电话：</td>
				<td nowrap="nowrap" colspan="2" class="title_content">${partner.link_tel}</td>
          	</tr>
			</c:if>
			<tr>
				<c:if test="${af.map.bill_type eq 20 or af.map.bill_type eq 21}">
					<td nowrap="nowrap" class="title_t">送货地址：</td>
				</c:if>
				<c:if test="${af.map.bill_type eq 10 or af.map.bill_type eq 11}">
					<td nowrap="nowrap" class="title_t">供应商地址：</td>
				</c:if>
				<td nowrap="nowrap" colspan="3" class="title_content">
					${partner.map._PROVINCE }${partner.map._CITY }${partner.map._COUTRY }${partner.map._TOWN }${partner.consignee_street }
				</td>
				<td nowrap="nowrap" class="title_t">出/入库仓位：</td>
				<td nowrap="nowrap" colspan="2" class="title_content">${store_name }</td>
          	</tr>
		</table>
		<table width="100%" border="1" >
			<tr class="table_head">
				<th nowrap="nowrap" align="center"><b>商品类型</b></th>
				<th nowrap="nowrap" align="center"><b>机型</b></th>
				<th nowrap="nowrap" width="5%" align="center"><b>数量</b></th>
				<th nowrap="nowrap" width="5%" align="center"><b>单位</b></th>
				<th nowrap="nowrap" width="15%" align="center"><b>单价</b></th>
				<th nowrap="nowrap" align="center"><b>金额</b></th>
				<th nowrap="nowrap" width="20%" align="center"><b>行备注</b></th>
			</tr>
			<c:set var="num_sum" value="0"></c:set>
			<c:set var="price_sum" value="0"></c:set>
			<c:set var="money_sum" value="0"></c:set>
			<c:set var="dis_sum" value="0"></c:set>
			<c:forEach items="${entityList}" var="cur" varStatus="vs">
			<tr class="title_content">
				<td align="center" >${cur.map.type}</td>
				<td align="center">${cur.map.goods_name }</td>
				<td nowrap="nowrap" align="right" style="padding-right: 3px">${cur.num}</td>
				<td nowrap="nowrap" align="center">${cur.map.unit}</td>
				<td nowrap="nowrap" align="right" style="padding-right: 3px">${cur.price}</td>
				<td nowrap="nowrap" align="right" style="padding-right: 3px">${cur.money}</td>
				<td nowrap="nowrap">${cur.notes}</td>
				<c:set var="num_sum" value="${num_sum+ cur.num}"></c:set>
				<c:set var="price_sum" value="${price_sum+ cur.price}"></c:set>
				<c:set var="money_sum" value="${money_sum+ cur.money}"></c:set>
			</tr>
			</c:forEach>
			<tr class="title_content">
				<td align="center" colspan="2">合计</td>
				<td align="right" style="padding-right: 3px">${num_sum }</td>
				<td align="right"></td>
				<td align="right"></td>
				<td align="right" style="padding-right: 3px">${money_sum }</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="9" class="title_t">备注：${af.map.bill_memo }</td>
			</tr>
			<tr style="font-size: 9pt">
				<td colspan="9">打印时间：<fmt:formatDate value="${nowDate}" type="both" dateStyle="long" /></td>
			</tr>
		</table>
		<table width="100%" style="font-size: 13pt">
			<c:if test="${af.map.bill_type eq 20 or af.map.bill_type eq 21}">
			<tr class="title_t">
				<td >经手人:&nbsp;</td>
				<td >日期:&nbsp;</td>
			</tr>
			</c:if>
			<c:if test="${af.map.bill_type eq 10 or af.map.bill_type eq 11}">
			<tr class="title_t">
				<td >总经理签字：</td>
				<td >财务签字：</td>
				<td >主管签字：</td>
				<td >采购员：</td>
			</tr>
			</c:if>

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