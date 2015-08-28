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
    <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
      <tr>
        <td colspan="5"  bgcolor="#CCCCCC" style="font-weight:bold;">登记信息填写</td>
      </tr>
      <tr>
        <td width="10px"></td>
        <td width='8%' nowrap="nowrap" class="title_item"><span style="color:red;">* </span>供&nbsp;应&nbsp;商：</td>
        <td width="30%">${af.map.partner.partner_name}</td>
        <td width="8%" nowrap="nowrap" class="title_item" ><span style="color:red;">* </span>${bill_sn_title}：</td>
        <td>${af.map.bill_sn }</td>
      </tr>
      <tr>
        <td width="10px"></td>
        <td nowrap="nowrap" class="title_item"><span style="color:red;">* </span>交货方式：</td>
        <td>
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
        <td nowrap="nowrap" class="title_item"><span style="color:red;">* </span>采购日期：</td>
        <td>
          <fmt:formatDate value="${af.map.opr_date}" pattern="yyyy-MM-dd" var="_opr_date" />
          ${_opr_date}
        </td>
      </tr>
      <tr>
        <td width="10px"></td>
        <td nowrap="nowrap" class="title_item" style="padding-left: 14px">联&nbsp;系&nbsp;人：</td>
        <td>
          ${af.map.partner.link_name }
        </td>
        <td nowrap="nowrap" class="title_item" style="padding-left: 14px">联系电话：</td>
        <td>
          ${af.map.partner.link_mobile }
        </td>
      </tr>
      <tr>
        <td width="10px"></td>
        <td nowrap="nowrap" class="title_item" style="padding-left: 14px">创&nbsp;建&nbsp;人：</td>
        <td>${empty customerUserInfo.real_name ? customerUserInfo.user_name : customerUserInfo.real_name}</td>
        <td nowrap="nowrap" class="title_item" style="padding-left: 14px">创建时间：</td>
        <td>
          <fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd" var="_add_date" />
          ${_add_date}
        </td>
      </tr>
      <tr>
        <td width="10px"></td>
        <td nowrap="nowrap" class="title_item" style="padding-left: 14px">单据状态：</td>
        <td>
          <c:if test="${af.map.bill_state eq 0 }">待确认</c:if>
          <c:if test="${af.map.bill_state eq 1 }">已确认</c:if>
        </td>
        <td nowrap="nowrap" class="title_item" style="padding-left: 14px">供应商地址：</td>
        <td>${af.map.partner.map._PROVINCE }${af.map.partner.map._CITY }${af.map.partner.map._COUNTRY }${af.map.partner.map._TOWN }${af.map.partner.consignee_street }</td>
      </tr>
      <tr>
        <td width="10px"></td>
        <td nowrap="nowrap" class="title_item" style="padding-left: 14px">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
        <td colspan="3"><textarea rows="3" style="width:60%;" readonly="readonly">${af.map.bill_memo }</textarea></td>
      </tr>
    </table>
      <table width="100%" border="0" cellspacing="1" cellpadding="0" class="tab titleTab">
        <tr>
          <td>商品明细</td>
        </tr>
      </table>
      <div style="overflow-x:auto;">
      	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
   			<tr>
              <td width="15%" align="center" nowrap="nowrap"><span style="color:red;">* </span>${store_title}&nbsp;&nbsp;<a onclick="javascript:location.href='${ctx}/customer/manager/JBaseStore.do?method=add&returnUrl=' + encodeURIComponent(location.href) + '&mod_id=199010500';return true;" style="color:blue;cursor:pointer;">新建</a></td>
              <td width="10%" align="center" nowrap="nowrap"><span style="color:red;">* </span>商品类型</td>
              <td width="15%" align="center" nowrap="nowrap"><span style="color:red;">* </span>商品&nbsp;&nbsp;<a onclick="javascript:location.href='${ctx}/customer/manager/JBaseGoods.do?method=add&returnUrl=' + encodeURIComponent(location.href) + '&mod_id=199010100';return true;" style="color:blue;cursor:pointer;">新建</a></td>
              <td width="5%" align="center" nowrap="nowrap">单位</td>
              <td width="9%" align="center" nowrap="nowrap"><span style="color:red;">* </span>数量</td>
              <td width="9%" align="center" nowrap="nowrap"><span style="color:red;">* </span>${price_title}（元）</td>
              <td width="9%" align="center" nowrap="nowrap">${goods_money_title}（元）</td>
              <td width="10%" align="center" nowrap="nowrap">行备注</td>
   			</tr>
   			<c:forEach var="cur" items="${detailsList}">
   			<tr class="tr_pd">
    			<td align="center" class="td_pd" nowrap="nowrap"><c:out value="${cur.map.store_name}" /></td>
                <td align="center" class="td_pd" nowrap="nowrap">${cur.map.type}</td>
     			<td align="center" class="td_pd" nowrap="nowrap">${cur.map.goods_name}</td>
     			<td align="center" nowrap="nowrap"><span class="dw">${cur.map.unit}</span></td>
     			<td align="center" nowrap="nowrap">
     				<c:if test="${cur.num gt 0 }">${cur.num }</c:if>
                    <c:if test="${cur.num lt 0 }">${-cur.num }</c:if>
     			</td>
     			<td align="center" nowrap="nowrap">${cur.price}</td>
     			<td align="center" nowrap="nowrap">${cur.money}</td>
     			<td align="center" nowrap="nowrap">${cur.notes}</td>
    		</tr>
    		</c:forEach>
   		</table>
   	</div>
   	<table width="100%" border="0" cellspacing="1" cellpadding="0" class="tab">
        <tr>
          <td width="40%" nowrap="nowrap" class="title_item">
            ${rec_money_title}：${af.map.rec_money }&nbsp;元
          </td>
          <td d width="60%" nowrap="nowrap" class="title_item">
            折扣金额：${af.map.dis_money }&nbsp;元
          </td>
        </tr>
        <tr>
        <td nowrap="nowrap" class="title_item">
          ${money_title}：${af.map.money }&nbsp;元
        </td>
        <td></td>
      </tr>
    </table>
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
