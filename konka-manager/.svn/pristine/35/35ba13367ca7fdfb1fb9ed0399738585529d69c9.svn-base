<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<!-- <link href="${ctx}/scripts/jquery-ui/themes/redmond/jquery-ui-1.8.2.custom.css" rel="stylesheet" type="text/css" /> -->
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jGrowl.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/orderdetail.css" rel="stylesheet" type="text/css" />
</head>
<body>
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
    <c:if test="${af.map.sell_state ge 20 and af.map.sell_state ne 21}">
      <div style="position:absolute;top:60px;right:265px;z-index:1;"><img src="${ctx}/styles/images/pass.png" alt="已审核" title="财务已审核通过" width="150" /></div>
    </c:if>
    <html-el:form action="/manager/KonkaXxZmdAuditSalesOrder">
      <div style="font-size:20px;font-weight:700;height:35px;text-align:center;">销售单</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
        <!-- 销售单信息Begin -->
        <tr>
          <td colspan="4" style="font-weight:900;"><span style="float:right;">销售单流水号：<b style="color:#FF0000;font-family:'Arial Narrow','宋体';font-size:200%;font-weight:800;">${fnx:leftPad_sis(af.map.sell_bill_id, 12, '0')}</b></span></td>
        </tr>
        <tr>
          <td colspan="4" style="font-weight:900;">销售单基本信息</td>
        </tr>
        <tr>
          <td width="15%" class="title_item" nowrap="nowrap" align="right" ><strong>分公司：</strong></td>
          <td width="35%" align="left" style="padding-bottom:5px;"><c:out value="${af.map.dept_name}" /></td>
          <td width="15%" class="title_item" nowrap="nowrap" align="right">专卖店：</td>
          <td width="35%" align="left" style="padding-bottom:5px;"><c:out value="${af.map.zmd_sn}" /></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">销售人员：</td>
          <td align="left" style="padding-bottom:5px;"><c:out value="${af.map.sell_man}"/></td>
          <td class="title_item" nowrap="nowrap" align="right">销售时间：</td>
          <td align="left" style="padding-bottom:5px;"><fmt:formatDate value="${af.map.sell_date}" pattern="yyyy-MM-dd" /></td>
        </tr>
        <!-- 购买人信息Begin -->
        <tr>
          <td colspan="4" style="font-weight:900;">购买人信息</td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right" ><strong>顾客姓名：</strong></td>
          <td align="left" style="padding-bottom:5px;"><c:out value="${af.map.buyer_name}"/></td>
          <td class="title_item" nowrap="nowrap" align="right">手机：</td>
          <td align="left" style="padding-bottom:5px;"><c:out value="${af.map.buyer_phone}"/></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">收货人姓名：</td>
          <td align="left" style="padding-bottom:5px;"><c:out value="${af.map.sell_rec_name}"/></td>
          <td class="title_item" nowrap="nowrap" align="right">收货人电话：</td>
          <td align="left" style="padding-bottom:5px;"><c:out value="${af.map.sell_rec_link_mp}" /></td>
        </tr>
        <tr>
	        <td class="title_item" nowrap="nowrap" align="right">配送方式：</td>
			<td>
			<c:choose>
	          	<c:when test="${af.map.send_type eq 1 }">自提</c:when>
	          	<c:when test="${af.map.send_type eq 2 }">配送</c:when>
          </c:choose>
			</td>
		</tr>
         <tr>
          <td class="title_item" nowrap="nowrap" align="right">送货地区：</td>
          <td align="left" colspan="3" style="padding-bottom:5px;">${af.map.sell_post_area}&nbsp;${af.map.sell_post_addr}</td>
        </tr>
        <!-- 销售单信息End -->
        <!-- 商品信息Begin -->
        <tr>
          <td colspan="4" style="font-weight:900;">商品明细清单（<span style="cursor:pointer;" class="fblue" onclick="location.href='KonkaXxZmdSearchSalesOrder.do?method=showDetail&mod_id=${af.map.mod_id}&sell_bill_id=${af.map.sell_bill_id}';" >导出明细</span>）</td>
        </tr>
        <tr>
          <td colspan="4" align="center" style="padding-top:5px;padding-bottom:5px;"><div id="div_2">
              <!-- 产品信息Begin -->
              <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
                <tr class="tabtt1" style="background-image:url('${ctx}/images/manager/tabtitbg1.gif') repeat-x;background:#F0F0F0;">
                  <td width="5%" align="center" nowrap="nowrap">序号</td>
                  <td width="10%" align="center" nowrap="nowrap">产品型号</td>
                  <td width="10%" align="center" nowrap="nowrap">产品品类</td>
                  <td width="10%" align="center" nowrap="nowrap">赠品</td>
                  <td width="4%" align="center" nowrap="nowrap">数量</td>
                  <td width="8%" align="center" nowrap="nowrap">单价</td>
                  <td width="8%" align="center" nowrap="nowrap">金额</td>
                  <td width="5%" align="center" nowrap="nowrap">商品类型</td>
                  <td width="30%" align="center" nowrap="nowrap">仓位信息</td>
                  <td width="10%" align="center" nowrap="nowrap">销售备注</td>
                </tr>
                <c:forEach items="${konkaXxSellBillDetailsList}" var="cur" varStatus="vs">
                  <tr>
                    <td align="center" nowrap="nowrap">${vs.count}</td>
                    <td align="left" nowrap="nowrap" class="pdIds"><c:out value="${cur.md_name}" /></td>
                    <td align="center" nowrap="nowrap">${cur.pd_cls_name}</td>
                    <td align="left"><c:out value="${cur.gift}" /></td>
                    <td align="right" nowrap="nowrap"><c:out value="${cur.counts}" /></td>
                    <td align="right" nowrap="nowrap"><span class="kz-price-12">
                      <fmt:formatNumber type="currency" value="${cur.price}" />
                      </span></td>
                    <td align="right" nowrap="nowrap"><span class="kz-price-12">
                      <fmt:formatNumber type="currency" value="${cur.map.all_money}" />
                      </span></td>
                    <td align="center" nowrap="nowrap"><c:forEach items="${baseTypesList70000}" var="cur_">
                        <c:if test="${cur_.type_id eq cur.pd_type}">${cur_.type_name}</c:if>
                      </c:forEach></td>
                    <td align="left" nowrap="nowrap"><ul>
                        <c:forEach items="${cur.map.dstList}" var="cur_" varStatus="vs_">
                          <li>仓位${vs_.count}. ${cur_.factory_id}_${cur_.store_id}_${cur_.store_name}：<span style="cursor:pointer;font-weight:800;" class="fblue" title="出库数量">${cur_.counts}</span>台；</li>
                        </c:forEach>
                      </ul></td>
                    <td align="left"><c:out value="${cur.xs_remarks}" /></td>
                  </tr>
                </c:forEach>
                <tr id="total">
                  <td align="center" colspan="2" style="font-weight:bold;font-size:14px;">合计</td>
                  <td colspan="2"></td>
                  <td align="right">${total_counts}</td>
                  <td></td>
                  <td align="right" nowrap="nowrap"><span id="allAmount" class="kz-price">
                    <fmt:formatNumber type="currency" value="${af.map.total_money}" />
                    </span></td>
                  <td colspan="3"></td>
                </tr>
              </table>
              <!-- 产品信息End -->
            </div></td>
        </tr>
        <!-- 商品信息End -->
        <!-- 购买人信息Begin -->
        <tr>
          <td colspan="4" style="font-weight:900;">&nbsp;</td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">付款方式：</td>
          <td align="left" style="padding-bottom:5px;" colspan="3">
          <c:choose>
          	<c:when test="${af.map.pay_way eq 1 }">现金支付</c:when>
          	<c:when test="${af.map.pay_way eq 2 }">POS刷卡</c:when>
          </c:choose>	  			
          </td>
        </tr>
        <!-- 财务审核信息End -->
        <tr>
          <td colspan="4" height="40"  align="center">
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" id="btn_back" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>