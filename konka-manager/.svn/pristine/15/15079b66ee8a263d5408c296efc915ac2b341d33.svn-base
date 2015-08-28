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
      <div style="overflow-x:auto;">
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td colspan="5"  bgcolor="#CCCCCC" style="font-weight:bold;">登记信息查看</td>
        </tr>
        <c:if test="${not empty bill_confirm }">
        <tr>
          <td width="10px">&nbsp;</td>
          <td width="30px" nowrap="nowrap" class="title_item" >分销商名称：</td>
          <td width="400px">${af.map.map.part_name}</td>
          <td width="30px" nowrap="nowrap" class="title_item" >分销商编码：</td>
          <td nowrap="nowrap">${af.map.map.part_sn}</td>
        </tr>
        </c:if>
        <tr>
          <td width="10px">&nbsp;</td>
          <td width="30px" nowrap="nowrap" class="title_item"><span style="color:red;">* </span>网&nbsp;&nbsp;&nbsp;&nbsp;点：</td>
          <td width="400px">${agent.partner_name}</td>
          <td width="30px" nowrap="nowrap" class="title_item"><span style="color:red;">* </span>${bill_sn_title}：</td>
          <td nowrap="nowrap">${af.map.bill_sn}</td>
        </tr>
        <tr>
          <td width="10px">&nbsp;</td>
          <td nowrap="nowrap" class="title_item" ><span style="color:red;">* </span>交货方式：</td>
          <td>
          	<c:if test="${af.map.send_type eq 0 }">自提</c:if>
          	<c:if test="${af.map.send_type eq 1 }">配送</c:if>
          </td>
          <td nowrap="nowrap" class="title_item" ><span style="color:red;">* </span>创建日期：</td>
          <td><fmt:formatDate value="${af.map.opr_date}" pattern="yyyy-MM-dd"/></td>
        </tr>
        <tr>
          <td width="10px">&nbsp;</td>
          <td nowrap="nowrap" class="title_item" >联&nbsp;系&nbsp;人：</td>
          <td>${agent.link_name }</td>
          <td nowrap="nowrap" class="title_item" >联系电话：</td>
          <td>${agent.link_mobile }</td>
        </tr>
        <tr>
          <td width="10px">&nbsp;</td>
          <td nowrap="nowrap" class="title_item" >单据状态：</td>
          <td>
          	<c:if test="${af.map.bill_state eq 0 }">待确认</c:if>
          	<c:if test="${af.map.bill_state eq 1 }">已确认</c:if>
          </td>
          <td nowrap="nowrap" class="title_item" >创&nbsp;建&nbsp;人：</td>
          <td>${af.map.map.add_name }</td>
        </tr>
        <tr>
          <td width="10px">&nbsp;</td>
          <td nowrap="nowrap" class="title_item" >网点地址：</td>
          <td colspan="3">${agent.map._PROVINCE}${agent.map._CITY }${agent.map._COUNTRY }${agent.map._TOWN }${af.map.consignee_street }
          </td>
        </tr>
        <tr>
          <td width="10px">&nbsp;</td>
          <td nowrap="nowrap" class="title_item" >备&nbsp;&nbsp;&nbsp;&nbsp;注&nbsp;：</td>
          <td colspan="3"><textarea rows="3" cols="70" readonly="readonly">${af.map.bill_memo }</textarea>
          </td>
        </tr>
        <tr>
          <td colspan="5"  bgcolor="#CCCCCC">商品明细</td>
        </tr>
        <tr>
          <td colspan="5"><table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
          		<tr>
          			<td width="10%" align="center" nowrap="nowrap">出货仓库</td>
          			<td width="20%" align="center" nowrap="nowrap">商品</td>
          			<td width="5%" align="center" nowrap="nowrap">单位</td>
          			<td width="6%" align="center" nowrap="nowrap">数量</td>
          			<td width="8%" align="center" nowrap="nowrap">${price_title}（元）</td>
          			<td width="8%" align="center" nowrap="nowrap">${goods_money_title}（元）</td>
          			<td width="8%" align="center" nowrap="nowrap">折扣金额（元）</td>
          			<td width="8%" align="center" nowrap="nowrap">应收金额（元）</td>
          			<td width="9%" align="center" nowrap="nowrap">行备注</td>
          		</tr>
          		<c:if test="${not empty detailsList}">
          			<c:forEach var="cur" items="${detailsList}">
          			<tr class="tr_pd">
	          			<td align="center" class="td_pd" nowrap="nowrap"><c:out value="${cur.map.store_name}" /></td>
	          			<td align="center" class="td_pd" nowrap="nowrap">${cur.map.goods_name}</td>
	          			<td align="center" nowrap="nowrap"><span class="dw">${cur.map.unit}</span></td>
	          			<td align="center" nowrap="nowrap">${cur.num}</td>
	          			<td align="center" nowrap="nowrap">${cur.price}</td>
	          			<td align="center" nowrap="nowrap">${cur.money}</td>
	          			<td align="center" nowrap="nowrap">${cur.dis_money}</td>
	          			<td align="center" nowrap="nowrap">${cur.rec_money}</td>
	          			<td align="center" nowrap="nowrap">${cur.notes}</td>
	          		</tr>
	          		</c:forEach>
          		</c:if>
          		</table>
          		</td>
        </tr>
        <c:if test="${af.map.bill_type eq 40 }">
	        <tr>
	          <td width="10px">&nbsp;</td>
	          <td nowrap="nowrap" class="title_item" >总&nbsp;金&nbsp;额&nbsp;：</td>
	          <td colspan="3">${af.map.sum_money}&nbsp;元</td>
	        </tr>
	        <tr>
	          <td width="10px">&nbsp;</td>
	          <td nowrap="nowrap" class="title_item" >折扣金额：</td>
	          <td colspan="3">${af.map.dis_money}&nbsp;元
	          	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>折扣率：&nbsp;</b>${af.map.discount}&nbsp;%
	          </td>
	        </tr>
	        <tr>
	          <td width="10px">&nbsp;</td>
	          <td nowrap="nowrap" class="title_item" >折后金额：</td>
	          <td colspan="3">${af.map.rec_money}&nbsp;元</td>
	        </tr>
        </c:if>
        <c:if test="${af.map.bill_type eq 42 }">
        	<tr>
        	  <td width="10px">&nbsp;</td>
	          <td nowrap="nowrap" class="title_item" >退货金额：</td>
	          <td colspan="3">${af.map.rec_money }&nbsp;元</td>
	        </tr>
        </c:if>
        <tr>
          <td width="10px">&nbsp;</td>
          <td align="center" colspan="4">
          	<input type="button" value="返  回" class="but5" onclick="history.back()" />
          </td>
        </tr>
      </table>
      </div>
  </div>
</div>

<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>