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
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="sell_bill_id" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="queryString" />
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
          <td align="left" style="padding-bottom:5px;"><c:out value="${af.map.sell_man}" /></td>
          <td class="title_item" nowrap="nowrap" align="right">销售时间：</td>
          <td align="left" style="padding-bottom:5px;"><fmt:formatDate value="${af.map.sell_date}" pattern="yyyy-MM-dd" var="_sell_date" />
            ${_sell_date}</td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">付款方式：</td>
          <td align="left" style="padding-bottom:5px;">${fn:split(' ,现金付款,POS机刷卡,货到付款', ',')[af.map.pay_way]}</td>
          <td class="title_item" nowrap="nowrap" align="right">发票类型：</td>
          <td align="left" style="padding-bottom:5px;"><c:forEach items="${baseTypesList90000}" var="cur">
              <c:if test="${cur.type_id eq af.map.sell_bill_type}">${cur.type_name}</c:if>
            </c:forEach></td>
        </tr>
        <c:if test="${af.map.pay_way eq 3}">
          <tr id="money_of_deposit_tr">
            <td class="title_item" nowrap="nowrap" align="right">定金：</td>
            <td align="left" colspan="3" style="padding-bottom:5px;"><c:out value="${af.map.money_of_deposit}" />
              元</td>
          </tr>
        </c:if>
        <c:if test="${af.map.pay_way eq 2}">
          <tr id="pos_bill_sn_tr">
            <td class="title_item" nowrap="nowrap" align="right">POS单号：</td>
            <td align="left" colspan="3" style="padding-bottom:5px">${af.map.pos_bill_sn}</td>
          </tr>
        </c:if>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">发票抬头：</td>
          <td align="left" style="padding-bottom:5px"><c:if test="${empty af.map.sell_bill_head}"><span style="color:#999">未填写</span></c:if>
            <c:if test="${not empty af.map.sell_bill_head}">${af.map.sell_bill_head}</c:if></td>
          <td class="title_item" nowrap="nowrap" align="right">发票纳税人识别号：</td>
          <td align="left" style="padding-bottom:5px"><c:if test="${empty af.map.sell_bill_taxpayer_id}"><span style="color:#999">未填写</span></c:if>
            <c:if test="${not empty af.map.sell_bill_taxpayer_id}">${af.map.sell_bill_taxpayer_id}</c:if></td>
        </tr>
        <tr>
          <td width="15%" class="title_item" nowrap="nowrap" align="right">送货地址：</td>
          <td align="left" colspan="3" style="padding-bottom:5px;">${af.map.sell_post_area}&nbsp;${af.map.sell_post_addr}</td>
        </tr>
        <!--	        <tr>-->
        <!--	        	<td width="15%" class="title_item" nowrap="nowrap" align="right">送货街道地址：</td>-->
        <!--	        	<td align="left" colspan="3" style="padding-bottom:5px;"><c:out value="${af.map.sell_post_addr}" /></td>-->
        <!--	        </tr>-->
        <tr>
          <td width="15%" class="title_item" nowrap="nowrap" align="right">收货人姓名：</td>
          <td width="35%" align="left" style="padding-bottom:5px;"><c:out value="${af.map.sell_rec_name}" />
            <c:if test="${empty af.map.sell_rec_name}"><span style="color:#999;">未填写</span></c:if></td>
          <!--	        </tr>-->
          <!--	        <tr>-->
          <td width="15%" class="title_item" nowrap="nowrap" align="right">收货人联系电话：</td>
          <td align="left" colspan="3" style="padding-bottom:5px;"><c:out value="${af.map.sell_rec_link_mp}" />
            <c:if test="${empty af.map.sell_rec_link_mp}"><span style="color:#999;">未填写</span></c:if></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">物流送货人：</td>
          <td align="left" style="padding-bottom:5px;">${af.map.dist_employee_name}
            <c:if test="${empty af.map.dist_employee_name}"><span style="color:#999">未填写</span></c:if></td>
          <td class="title_item" nowrap="nowrap" align="right">产品预送时间：</td>
          <td align="left" style="padding-bottom:5px;"><fmt:formatDate value="${af.map.ready_dist_date}" pattern="yyyy-MM-dd" />
            <c:if test="${empty af.map.ready_dist_date}"><span style="color:#999">未填写</span></c:if></td>
        </tr>
        <tr>
          <td width="15%" class="title_item" nowrap="nowrap" align="right">备注：</td>
          <td align="left" colspan="3" style="padding-bottom:5px;"><c:out value="${af.map.sell_remarks}" />
            <c:if test="${empty af.map.sell_remarks}"><span style="color:#999;">无</span></c:if></td>
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
          <td colspan="4" style="font-weight:900;">购买人信息</td>
        </tr>
        <tr>
          <td width="15%" class="title_item" nowrap="nowrap" align="right" ><strong>购买人姓名：</strong></td>
          <td width="35%" align="left" style="padding-bottom:5px;"><c:out value="${af.map.buyer_name}"></c:out></td>
          <td width="15%" class="title_item" nowrap="nowrap" align="right">身份证：</td>
          <td align="left" style="padding-bottom:5px;"><c:out value="${af.map.buyer_id}" />
            <c:if test="${empty af.map.buyer_id}"><span style="color:gray;">未填写</span></c:if></td>
        </tr>
        <tr>
          <td width="15%" class="title_item" nowrap="nowrap" align="right" ><strong>性别：</strong></td>
          <td width="35%" align="left" style="padding-bottom:5px;"><c:choose>
              <c:when test="${af.map.buyer_sex eq 1}">男</c:when>
              <c:when test="${af.map.buyer_sex eq 2}">女</c:when>
              <c:when test="${af.map.buyer_sex eq 3}">保密</c:when>
              <c:otherwise>不详</c:otherwise>
            </c:choose></td>
          <td width="15%" class="title_item" nowrap="nowrap" align="right">生日：</td>
          <td align="left" style="padding-bottom:5px;"><fmt:formatDate value="${af.map.buyer_birthday}" pattern="yyyy-MM-dd" var="_buyer_birthday" />
            <c:out value="${_buyer_birthday}" />
            <c:if test="${empty af.map.buyer_birthday}"><span>-</span></c:if></td>
        </tr>
        <tr>
          <td width="15%" class="title_item" nowrap="nowrap" align="right" ><strong>电话：</strong></td>
          <td width="35%" align="left" style="padding-bottom:5px;"><c:out value="${af.map.buyer_tel}" />
            <c:if test="${empty af.map.buyer_tel}"><span style="color:gray;">未填写</span></c:if></td>
          <td width="15%" class="title_item" nowrap="nowrap" align="right">手机：</td>
          <td align="left" style="padding-bottom:5px;"><c:out value="${af.map.buyer_phone}" /></td>
        </tr>
        <tr>
          <td width="15%" class="title_item" nowrap="nowrap" align="right">购买人地址：</td>
          <td align="left" colspan="3" style="padding-bottom:5px;"><c:out value="${af.map.buyer_area}" />
            <c:out value="${af.map.buyer_link_addr}" />
            &nbsp;${af.map.buyer_link_addr}</td>
        </tr>
        <tr>
          <td width="15%" class="title_item" nowrap="nowrap" align="right">消费者备注：</td>
          <td align="left" colspan="3" style="padding-bottom:5px;"><c:out value="${af.map.buyer_memo}" />
            <c:if test="${empty af.map.buyer_memo}"><span style="color:gray;">无</span></c:if></td>
        </tr>
        <c:if test="${af.map.sell_state gt 10}">
          <tr>
            <td colspan="4" style="font-weight:900;">订单流程信息</td>
          </tr>
          <tr>
            <td width="15%" class="title_item" nowrap="nowrap" align="right">财务审核结果：</td>
            <td align="left" colspan="3" style="padding-bottom:5px;"><c:if test="${af.map.sell_state ge 20 and af.map.sell_state ne 21}"><span style="color:green;">审核通过。</span>
                <c:if test="${not empty af.map.cw_remarks}">附：</c:if>
              </c:if>
              <c:if test="${af.map.sell_state eq 21}"><span style="color:#F00;">审核不通过</span>。
                <c:if test="${not empty af.map.cw_remarks}">原因：</c:if>
              </c:if>
              <c:if test="${not empty af.map.cw_remarks}">${af.map.cw_remarks}</c:if></td>
          </tr>
          <tr>
            <td class="title_item" nowrap="nowrap" align="right">物流收款（余款）人姓名：</td>
            <td align="left" colspan="1" style="padding-bottom:5px;">${af.map.dist_payee}
              <c:if test="${empty af.map.dist_payee}"><span style="color:gray;">未填写</span></c:if></td>
            <td class="title_item" nowrap="nowrap" align="right">物流收款（余款）人身份证号：</td>
            <td align="left" colspan="1" style="padding-bottom:5px;">${af.map.dist_payee_id}
              <c:if test="${empty af.map.dist_payee_id}"><span style="color:gray;">未填写</span></c:if></td>
          </tr>
          <tr>
            <td class="title_item" nowrap="nowrap" align="right">财务收款结算状态：</td>
            <td align="left" colspan="1" style="padding-bottom:5px;"><c:if test="${af.map.checkout_state eq 0}">未结算</c:if>
              <c:if test="${af.map.checkout_state eq 1}">已结算</c:if></td>
            <td class="title_item" nowrap="nowrap" align="right">财务收款结算状态时间：</td>
            <td align="left" colspan="1" style="padding-bottom:5px;"><fmt:formatDate value="${af.map.checkout_date}" pattern="yyyy-MM-dd" />
              <c:if test="${empty af.map.checkout_date}"><span>-</span></</c:if></td>
          </tr>
          <tr>
            <td class="title_item" nowrap="nowrap" align="right">订单回访时间：</td>
            <td align="left" colspan="1" style="padding-bottom:5px;"><fmt:formatDate value="${af.map.return_v_date}" pattern="yyyy-MM-dd" />
              <c:if test="${empty af.map.return_v_date}"><span>-</span></</c:if></td>
          </tr>
        </c:if>
        <tr>
          <td colspan="4" style="font-weight:900;">订单收款信息</td>
        </tr>
        <tr>
          <td width="15%" class="title_item" nowrap="nowrap" align="right">订单总额：</td>
          <td align="left" style="padding-bottom:5px;" colspan="3"><span class="kz-price">
            <fmt:formatNumber value="${af.map.total_money}" type="currency" />
            </span></td>
        </tr>
        <tr>
          <td width="15%" class="title_item" nowrap="nowrap" align="right">专卖店收款：</td>
          <td align="left" style="padding-bottom:5px;"><span class="kz-price">
            <c:choose>
              <c:when test="${af.map.pay_way eq 3}">
                <fmt:formatNumber value="${af.map.money_of_deposit}" type="currency" />
              </c:when>
              <c:otherwise>
                <fmt:formatNumber value="${af.map.total_money}" type="currency" />
              </c:otherwise>
            </c:choose>
            </span></td>
          <td width="15%" class="title_item" nowrap="nowrap" align="right">物流收款：</td>
          <td align="left" style="padding-bottom:5px;"><c:if test="${af.map.pay_way eq 3}"><span class="kz-price">
              <fmt:formatNumber value="${af.map.total_money - af.map.money_of_deposit}" type="currency" />
              </span></c:if>
            <c:if test="${af.map.pay_way ne 3}"><span class="kz-price">
              <fmt:formatNumber value="0" type="currency" />
              </span></c:if></td>
        </tr>
        <!-- 财务审核信息End -->
        <tr>
          <td colspan="6" height="40"  align="center"><!--	            	<input class="but4" type="button" name="Submit4" value="保存 " id="btn_submit" />-->
            <!--	            	<input class="but3" type="reset" style="width:100px;" value="重填" id="reset" />-->
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" id="btn_back" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery1.5.1.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

//	window.body_width = 0;
	//body自适应宽度Begin
//	var w = 0;
//	for ( var i = 1; i <= 2; i++) {
//		var w_d = $("#div_" + i).width();
		//alert("$(#div_" + i + ").length = " + w_d);
//		w = w + w_d + 5 + 5;
//	}
	//alert("w = " + w);
//	var rtabcont2 = w + 14 + 60;
	//body_width = w + 14 + 60;
//	$("body").css("width", rtabcont2 + 30).css("overflow-x", "auto");
	//body自适应宽度End

	$("input[type='radio'][name='sell_state']").eq(1).attr("dataType", "Group").attr("msg", "请选择审核是否通过！");
	$("#cw_remarks").attr("dataType", "Require").attr("msg", "请填写审核备注！");
	$("#cw_remarks").textbox({
		maxLength: 40,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/styles/jxc/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_chat_content").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_chat_content").slideUp("normal");
	});
	
	//提交表单
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 1)){
			if(confirm("确定要提交表单？")){
	            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
	            $("#reset").attr("disabled", "true");
	            $("#btn_back").attr("disabled", "true");
				this.form.submit();
			} else {
				return false;
			}
		}
	});
});
//]]></script>

<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>