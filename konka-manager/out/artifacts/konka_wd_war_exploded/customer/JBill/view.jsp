<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${naviString}</title>
<base target="_self" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.tab {
	margin-bottom:5px;
}
.tab td {
	padding:5px;
}
.titleTab {
	border-bottom:1px solid #CCC;
	margin-top:10px;
	font-weight:800;
	font-size:12px;
}
.item-title {
	text-align:right;
	font-weight:700;
	white-space:nowrap;
}
input[type='text'][readonly] {
	background-color:#eee;
	border:1px solid #ccc;
}
span.sel-tab {
	height: 30px;
	display: block;
	float : right;
	padding: 0px 10px;
	margin-top: 2px;
	margin-right: 10px;
	border-radius:3px 3px 0px 0px;
}
span.sel-tab-active {
	border-bottom : 0px;
	border-top: 1px solid #ccc;
	border-left: 1px solid #ccc;
	border-right: 1px solid #ccc;
	background-color: #FFF;
}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont">
  <div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <%@ include file="/commons/pages/messages.jsp" %>
      <table width="100%" border="0" cellspacing="1" cellpadding="0" class="tab">
        <tr>
          <td width="10%" class="item-title">销售日期：</td>
          <td width="10%">
            <fmt:formatDate value="${af.map.opr_date}" pattern="yyyy-MM-dd" var="_opr_date" />
            ${_opr_date}
          </td>
          <td width="15%" class="item-title">零售单号：</td>
          <td width="15%">${af.map.bill_sn }</td>
          <td width="10%" class="item-title">制单人：</td>
          <td width="10%">${empty customerUserInfo.real_name ? customerUserInfo.user_name : customerUserInfo.real_name}</td>
        </tr>
        <tr>
          <td class="item-title">销售单位：</td>
          <td colspan="5">${xs_name }</td>
        </tr>
        <tr class="consignee">
          <td width="10%" class="item-title"><span id="partner_name">顾客姓名</span>：</td>
          <td>${af.map.partner.link_name }</td>
          <td width="10%" class="item-title">联系电话：</td>
          <td>${af.map.partner.link_mobile }</td>
          <td width="10%" class="item-title"></td>
          <td nowrap="nowrap"></td>
        </tr>
        <tr>
          <td class="item-title">收货人：</td>
          <td class="consignee">${af.map.partner.consignee_name }</td>
          <td class="item-title">收货人电话：</td>
          <td class="consignee">${af.map.partner.consignee_tel }</td>
          <c:if test="${af.map.bill_type eq 20}">
          <td class="item-title">预约送货时间：</td>
          <td width="30%"><fmt:formatDate value="${af.map.plan_hand_time}" pattern="yyyy-MM-dd" /></td>
          </c:if>
          <c:if test="${af.map.bill_type eq 21 }">
          <td></td>
          <td></td>
          </c:if>
        </tr>
        <tr>
          <td nowrap="nowrap" class="item-title"><span style="color:red;">* </span>交货方式：</td>
          <td colspan="5">
          		<c:if test="${af.map.send_type eq 0 }">
		          	<label><input type='checkbox' id="send_type1" name="send_type" value="0" checked="checked" disabled="disabled"></input>自提</label>
		          	&nbsp;&nbsp;
		          	<label><input type='checkbox' id="send_type2" name="send_type" value="1" disabled="disabled"></input>配送</label>
          		</c:if>
          		<c:if test="${af.map.send_type eq 1 }">
		          	<label><input type='checkbox' id="send_type1" name="send_type" value="0" disabled="disabled"></input>自提</label>
		          	&nbsp;&nbsp;
		          	<label><input type='checkbox' id="send_type2" name="send_type" value="1" checked="checked" disabled="disabled"></input>配送</label>
          		</c:if>
          </td>
        </tr>
        <tr class="consignee">
          <td class="item-title">送货地址：</td>
          <td colspan="5">
          	${af.map.partner.map._PROVINCE }${af.map.partner.map._CITY }${af.map.partner.map._COUNTRY }${af.map.partner.map._TOWN }${af.map.partner.consignee_street }
          </td>
        </tr>
        <c:if test="${af.map.bill_type eq 20}">
        <tr>
          <td class="item-title">开票开具：</td>
          <td colspan="2">${af.map.bills_title }</td>
          <td class="item-title">单据摘要：</td>
          <td colspan="2">${af.map.bills_sumary }</td>
        </tr>
        </c:if>
        <c:if test="${af.map.bill_type eq 21 }">
        	<tr>
	          <td nowrap="nowrap" class="item-title">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
	          <td colspan="3"><textarea rows="3" style="width:100%;" readonly="readonly">${af.map.bill_memo }</textarea>
	          </td>
	        </tr>
        </c:if>
      </table>
      <table width="100%" border="0" cellspacing="1" cellpadding="0" class="tab titleTab">
        <tr>
          <td>商品明细</td>
        </tr>
      </table>
      <div style="overflow-x:auto;">
      	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
   			<tr>
   				<td width="9%" align="center" nowrap="nowrap">${store_title}</td>
                <td align="center" nowrap="nowrap">商品</td>
                <td width="3%" align="center" nowrap="nowrap">单位</td>
                <td width="6%" align="center" nowrap="nowrap"><span style="color:red;">* </span>数量</td>
                <td width="8%" align="center" nowrap="nowrap"><span style="color:red;">* </span>${price_title}（元）</td>
                <td width="8%" align="center" nowrap="nowrap">${goods_money_title}（元）</td>
                <td width="6%" align="center" nowrap="nowrap">赠品</td>
                <td width="6%" align="center" nowrap="nowrap">赠品数量</td>
                <td width="6%" align="center" nowrap="nowrap">赠品说明</td>
                <td width="6%" align="center" nowrap="nowrap">行备注</td>
   			</tr>
   			<c:forEach var="cur" items="${detailsList}">
   			<tr class="tr_pd">
    			<td align="center" class="td_pd" nowrap="nowrap"><c:out value="${cur.map.store_name}" /></td>
     			<td align="center" class="td_pd" nowrap="nowrap">${cur.map.goods_name}</td>
     			<td align="center" nowrap="nowrap"><span class="dw">${cur.map.unit}</span></td>
     			<td align="center" nowrap="nowrap">
     				<c:if test="${cur.num gt 0 }">${cur.num }</c:if>
                    <c:if test="${cur.num lt 0 }">${-cur.num }</c:if>
     			</td>
     			<td align="center" nowrap="nowrap">${cur.price}</td>
     			<td align="center" nowrap="nowrap">${cur.money}</td>
     			<td align="center" nowrap="nowrap">${cur.map.gift_name}</td>
     			<td align="center" nowrap="nowrap">${cur.gift_count}</td>
     			<td align="center" nowrap="nowrap">${cur.gift_desc}</td>
     			<td align="center" nowrap="nowrap">${cur.notes}</td>
    		</tr>
    		</c:forEach>
   		</table>
   	</div>
   	<table width="100%" border="0" cellspacing="1" cellpadding="0" class="tab">
        <tr>
          <td nowrap="nowrap" class="title_item">${rec_money_title}：
          	${af.map.rec_money }&nbsp;元
          </td>
        </tr>
      </table>
      <c:if test="${af.map.bill_type eq 20 }">
      <table width="100%" border="0" cellspacing="1" cellpadding="0" class="tab titleTab">
        <tr>
          <td>备注</td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="1" cellpadding="0" class="tab">
        <tr>
          <td colspan="2">
          	<textarea rows="3" style="width:100%;" readonly="readonly">${af.map.bill_memo }</textarea>
          </td>
        </tr>
      </table>
      </c:if>
  </div>
  <div align="center">
   	<input type="button" value="返  回" class="but5" onclick="history.back()" />
  </div>
</div>
<script type="text/javascript">//<![CDATA[
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
