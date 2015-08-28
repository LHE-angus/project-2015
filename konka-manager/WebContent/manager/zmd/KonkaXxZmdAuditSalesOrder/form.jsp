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
<link href="${ctx}/styles/tips/css/Popup.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont" style="position:relative;">
  <div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/zmd/KonkaXxZmdAuditSalesOrder">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="sell_bill_id" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="queryString" />
      <div style="font-size:20px;font-weight:700;height:35px;text-align:center;">审核销售单</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
        <!-- 销售单信息Begin -->
        <tr>
          <td colspan="4" style="font-weight:900;">销售单信息
            <c:if test="${not empty af.map.sell_bill_id}">（订单流水号：<span style="color:#FF0000;font-size:15px;">${fnx:leftPad_sis(af.map.sell_bill_id, 12, '0')}</span>）</c:if></td>
        </tr>
        <tr>
          <td width="15%" class="title_item" nowrap="nowrap" align="right" ><strong>分公司：</strong></td>
          <td width="35%" align="left"><c:out value="${af.map.dept_name}" /></td>
          <td width="15%" class="title_item" nowrap="nowrap" align="right">专卖店：</td>
          <td width="35%" align="left"><c:out value="${af.map.zmd_sn}" /></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">销售人员：</td>
          <td align="left"><c:out value="${af.map.sell_man}" /></td>
          <td class="title_item" nowrap="nowrap" align="right">销售时间：</td>
          <td align="left"><fmt:formatDate value="${af.map.sell_date}" pattern="yyyy-MM-dd" var="_sell_date" />
            ${_sell_date}</td>
        </tr>
        <c:if test="${empty jfScortsDetailsList}">
          <tr>
            <td class="title_item" nowrap="nowrap" align="right">会员卡号：</td>
            <td align="left"><c:out value="${af.map.consumer_grade_num}" /></td>
            <td class="title_item" nowrap="nowrap" align="right">会员积分：</td>
            <td align="left"><c:out value="${jf_value}" /></td>
          </tr>
        </c:if>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">付款方式：</td>
          <td align="left">${fn:split(' ,现金付款,POS机刷卡,货到付款', ',')[af.map.pay_way]}
            <c:if test="${not empty af.map.money_of_deposit and af.map.money_of_deposit gt 0 and af.map.pay_way eq 3}"> （定金：
              <c:out value="${af.map.money_of_deposit}" />
              元） </c:if>
            <c:if test="${not empty af.map.pos_bill_sn and af.map.pay_way eq 2}"> （POS单号：
              <c:out value="${af.map.pos_bill_sn}" />
              ） </c:if></td>
          <td class="title_item" nowrap="nowrap" align="right">发票类型：</td>
          <td align="left"><c:forEach items="${baseTypesList90000}" var="cur">
              <c:if test="${cur.type_id eq af.map.sell_bill_type}">${cur.type_name}</c:if>
            </c:forEach></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">备注：</td>
          <td align="left" colspan="3"><c:out value="${af.map.sell_remarks}" />
            <c:if test="${empty af.map.sell_remarks}"><span style="#999;">未填写</span></c:if></td>
        </tr>
        <!-- 销售单信息End -->
        <!-- 商品信息Begin -->
        <tr>
          <td colspan="4" style="font-weight:900;">商品清单</td>
        </tr>
        <tr>
          <td colspan="4" align="center" style="padding-top:5px;padding-bottom:5px;"><!-- 产品信息Begin -->
            <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
              <tr class="tabtt1" style="background-image:url('${ctx}/images/manager/tabtitbg1.gif') repeat-x;background:#F0F0F0;">
                <td width="5%" align="center" nowrap="nowrap">序号</td>
                <td width="10%" align="center" nowrap="nowrap">产品品类</td>
                <td align="center" nowrap="nowrap">产品型号</td>
                <td width="5%" align="center" nowrap="nowrap">商品类型</td>
                <td width="4%" align="center" nowrap="nowrap">数量</td>
                <td width="8%" align="center" nowrap="nowrap">单价</td>
                <td width="10%" align="center" nowrap="nowrap">价格下限</td>
                <td width="8%" align="center" nowrap="nowrap">金额</td>
                <!-- <td width="30%" align="center" nowrap="nowrap">仓位信息</td> -->
                <td width="10%" align="center" nowrap="nowrap">赠品</td>
              </tr>
              <c:forEach items="${konkaXxSellBillDetailsList}" var="cur" varStatus="vs">
                <tr title="订单备注：${empty cur.xs_remarks ? '无' : cur.xs_remarks}">
                  <td align="center" nowrap="nowrap">${vs.count}</td>
                  <td align="center" nowrap="nowrap">${cur.pd_cls_name}</td>
                  <td align="left" nowrap="nowrap" class="pdIds"><c:out value="${cur.md_name}" /></td>
                  <td align="center" nowrap="nowrap"><c:forEach items="${baseTypesList70000}" var="cur_">
                      <c:if test="${cur_.type_id eq cur.pd_type}">${cur_.type_name}</c:if>
                    </c:forEach></td>
                  <td align="center" nowrap="nowrap"><c:out value="${cur.counts}" /></td>
                  <td align="right" nowrap="nowrap"><span class="kz-price-12">
                    <fmt:formatNumber type="currency" value="${cur.price}" />
                    </span></td>
                  <td align="right" nowrap="nowrap"><span class="kz-price-12">
                    <fmt:formatNumber type="currency" value="${cur.map.price_min}" />
                    </span></td>
                  <td align="right" nowrap="nowrap"><span class="kz-price-12">￥</span><span class="kz-price-12" id="pdAmount_${vs.count}"></span></td>
                  <!--<td align="left" nowrap="nowrap"><ul>
                      <c:forEach items="${cur.map.dstList}" var="cur_" varStatus="vs_">
                        <li>仓位${vs_.count}. ${cur_.factory_id}_${cur_.store_id}_${cur_.store_name}<span style="display:none;">(<span style="cursor:pointer;font-weight:800;" class="fblue" title="该仓库实时总库存">${cur_.map.storeNum}</span>)</span>：<span style="cursor:pointer;font-weight:800;" class="fblue" title="出库数量">${cur_.counts}</span>台；</li>
                      </c:forEach>
                    </ul></td>-->
                  <td align="left"><c:out value="${empty cur.gift ? '-' : cur.gift}" /></td>
                </tr>
              </c:forEach>
              <tr id="total">
                <td align="center" colspan="2" style="color:red;font-weight:bold;">合计</td>
                <td colspan="2"></td>
                <td align="center"></td>
                <td colspan="2"></td>
                <td align="right" nowrap="nowrap"><span id="allAmount" class="kz-price-12">
                  <fmt:formatNumber value="${af.map.total_money}" type="currency" var="total_money_" />
                  ${total_money_}</span></td>
                <td colspan="3"></td>
              </tr>
            </table>
            <!-- 产品信息End --></td>
        </tr>
        <!-- 商品信息End -->
        <!-- 积分信息 -->
        <tr>
          <td colspan="4" style="font-weight:900;">积分详细</td>
        </tr>
        <tr>
          <td colspan="4" align="center" style="padding-top:5px;padding-bottom:5px;"><!-- 积分信息 -->
            <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
              <tr class="tabtt1" style="background-image:url('${ctx}/images/manager/tabtitbg1.gif') repeat-x;background:#F0F0F0;">
                <td width="5%" align="center" nowrap="nowrap">序号</td>
                <td width="10%" align="center" nowrap="nowrap">产品型号</td>
                <td width="10%" align="center" nowrap="nowrap">积分种类</td>
                <td align="center" nowrap="nowrap">调整原因</td>
                <td width="10%" align="center" nowrap="nowrap">积分</td>
                <td width="10%" align="center" nowrap="nowrap">时间</td>
              </tr>
              <c:forEach items="${jfScortsDetailsList}" var="cur" varStatus="vs">
                <tr>
                  <td align="center" nowrap="nowrap">${vs.count}</td>
                  <td align="left" nowrap="nowrap">${cur.pd_id}</td>
                  <td align="center" nowrap="nowrap"><c:choose>
                      <c:when test="${cur.jf_cate eq 1}">购买商品</c:when>
                      <c:when test="${cur.jf_cate eq 2}">调整积分</c:when>
                    </c:choose></td>
                  <td align="center" nowrap="nowrap"><c:out value="${cur.remark}" /></td>
                  <td align="right" nowrap="nowrap"><c:out value="${cur.scorts}" /></td>
                  <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" /></td>
                </tr>
              </c:forEach>
              <tr id="total">
                <td align="center" colspan="2" style="color:red;font-weight:bold;">合计</td>
                <td colspan="2"></td>
                <td colspan="1" align="right">${bill_count}</td>
                <td colspan="1"></td>
              </tr>
            </table>
            <!-- 积分信息End --></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">是否发货：</td>
          <td align="left" colspan="3"><label for="delivery0" style="cursor:pointer;">
              <html-el:checkbox property="delivery" styleId="delivery0" disabled="true" value="0" onclick="chooseOne(this)" style="valign:middle" />
              是</label>
            &nbsp;
            <label for="delivery1" style="cursor:pointer;">
              <html-el:checkbox property="delivery" styleId="delivery1" disabled="true" value="1" onclick="chooseOne(this)" style="vertical-align:top;" />
              否</label>
            <script type="text/javascript">//<![CDATA[
	        		function chooseOne(cb){   
	        	         //先取得同name的chekcBox的集合物件   
	        	         var obj = document.getElementsByName("delivery");   
	        	         for (i=0; i<obj.length; i++){   
	        	             //判断obj集合中的i元素是否為cb，若否則表示未被點選   
	        	             if (obj[i]!=cb) obj[i].checked = false;   
	        	             //若是 但原先未被勾選 則變成勾選；反之 則變為未勾選   
	        	             //else  obj[i].checked = cb.checked;   
	        	             //若要至少勾選一個的話，則把上面那行else拿掉，換用下面那行   
	        	             else obj[i].checked = true;   
	        	         }
	        	         //alert($("input[name='delivery']:checked").val());   
	        	     }
	        		//]]></script></td>
        </tr>
        <!-- 购买人信息Begin -->
        <tr>
          <td colspan="4" style="font-weight:900;">购买人信息 <span class="fblue" onclick="$('#cust_info').show();$(this).hide().next().show();resizeFrameHeight();" style="cursor:pointer;">展开更多</span><span class="fblue" onclick="$('#cust_info').hide();$(this).hide().prev().show();" style="display:none;cursor:pointer;">收起</span></td>
        </tr>
        <tbody id="cust_info" style="display:none;">
          <tr>
            <td width="15%" class="title_item" nowrap="nowrap" align="right">收货人姓名：</td>
            <td width="35%" align="left"><c:out value="${af.map.sell_rec_name}" /></td>
            <td width="15%" class="title_item" nowrap="nowrap" align="right">收货人联系电话：</td>
            <td width="35%" align="left"><c:out value="${af.map.sell_rec_link_mp}" /></td>
          </tr>
          <tr>
            <td class="title_item" nowrap="nowrap" align="right" ><strong>购买人姓名：</strong></td>
            <td align="left"><c:out value="${af.map.buyer_name}" />
              <c:if test="${empty af.map.buyer_name}"><span style="color:#999;">未填写</span></c:if></td>
            <td class="title_item" nowrap="nowrap" align="right">身份证：</td>
            <td align="left"><c:out value="${af.map.buyer_id}" />
              <c:if test="${empty af.map.buyer_id}"><span style="color:#999;">未填写</span></c:if></td>
          </tr>
          <tr>
            <td class="title_item" nowrap="nowrap" align="right" ><strong>性别：</strong></td>
            <td align="left"><c:choose>
                <c:when test="${af.map.buyer_sex eq 1}">男</c:when>
                <c:when test="${af.map.buyer_sex eq 2}">女</c:when>
                <c:when test="${af.map.buyer_sex eq 3}">保密</c:when>
                <c:otherwise>不详</c:otherwise>
              </c:choose>
              <c:if test="${empty af.map.buyer_sex}"><span style="color:#999;">未填写</span></c:if></td>
            <td class="title_item" nowrap="nowrap" align="right">生日：</td>
            <td align="left"><fmt:formatDate value="${af.map.buyer_birthday}" pattern="yyyy-MM-dd" var="_buyer_birthday" />
              <c:out value="${_buyer_birthday}" />
              <c:if test="${empty af.map.buyer_birthday}"><span>-</span></c:if></td>
          </tr>
          <tr>
            <td class="title_item" nowrap="nowrap" align="right" ><strong>电话：</strong></td>
            <td align="left"><c:out value="${af.map.buyer_tel}" />
              <c:if test="${empty af.map.buyer_tel}"><span style="color:#999;">未填写</span></c:if></td>
            <td class="title_item" nowrap="nowrap" align="right">手机：</td>
            <td align="left"><c:out value="${af.map.buyer_phone}" />
              <c:if test="${empty af.map.buyer_phone}"><span style="color:#999;">未填写</span></c:if></td>
          </tr>
          <tr>
            <td class="title_item" nowrap="nowrap" align="right">购买人地址：</td>
            <td align="left" colspan="3"><c:out value="${af.map.buyer_area}" />
              <c:if test="${empty af.map.buyer_area}"><span style="color:#999;">未填写</span></c:if>
              <c:out value="${af.map.buyer_link_addr}" />
              <c:if test="${empty af.map.buyer_link_addr}"><span style="color:#999;">未填写</span></c:if></td>
          </tr>
          <tr>
            <td class="title_item" nowrap="nowrap" align="right">消费者备注：</td>
            <td align="left" colspan="3"><c:out value="${af.map.buyer_memo}" />
              <c:if test="${empty af.map.buyer_memo}"><span style="color:#999;">无</span></c:if></td>
          </tr>
        </tbody>
        <!-- 购买人信息End -->
        <!-- 财务信息Begin -->
        <tr>
          <td colspan="4" style="font-weight:900">财务信息</td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">总应收款：</td>
          <td align="left" class="kz-price"><fmt:formatNumber value="${af.map.total_money}" type="currency" /></td>
          <td class="title_item" nowrap="nowrap" align="right">${af.map.zmd_sn}专卖店(${r3_id})账期总额度：</td>
          <td align="left" class="kz-price"><fmt:formatNumber value="${zqzed}" type="currency" /></td>
        </tr>
        <!-- 
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">${af.map.zmd_sn}专卖店现金额度：</td>
          <td align="left" colspan="3" class="kz-price"><fmt:formatNumber value="${af.map.total_credit_line - af.map.cur_credit_line }" type="currency" /></td>
        </tr>
         -->
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">专卖店已收款：</td>
          <td align="left"><span class="kz-price">
            <c:if test="${af.map.pay_way eq 3}">
              <fmt:formatNumber value="${empty af.map.money_of_deposit ? 0 : af.map.money_of_deposit}" type="currency" />
            </c:if>
            <c:if test="${af.map.pay_way eq 2}">
              <fmt:formatNumber value="${af.map.total_money}" type="currency" />
            </c:if>
            <c:if test="${af.map.pay_way eq 1}">
              <fmt:formatNumber value="${af.map.total_money}" type="currency" />
            </c:if>
            </span></td>
          <td class="title_item" nowrap="nowrap" align="right">物流应收款：</td>
          <td align="left" ><span class="kz-price">
            <c:if test="${af.map.pay_way eq 3}">
              <fmt:formatNumber value="${empty af.map.money_of_deposit ? af.map.total_money : af.map.total_money - af.map.money_of_deposit}" type="currency" />
            </c:if>
            <c:if test="${af.map.pay_way eq 2}">
              <fmt:formatNumber value="0" type="currency" />
            </c:if>
            <c:if test="${af.map.pay_way eq 1}">
              <fmt:formatNumber value="0" type="currency" />
            </c:if>
            </span></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right" valign="top">物流运费：</td>
          <td align="left"><html-el:text property="fee_of_post" styleId="fee_of_post" maxlength="8" size="6" value="${wl_fee}" />
            <span class="fblue" onclick="$('#wl_details').slideDown();$(this).hide().next().show();$('#_Popup_help').remove()" style="cursor:pointer;">点击展开物流明细</span><span class="fblue" onclick="$('#wl_details').slideUp();$(this).hide().prev().show();" style="display:none;cursor:pointer;">收起</span>
            <div id="wl_details" style="display:none;">
              <ul style="width:410px;">
                <c:forEach items="${konkaXxLogisticsList}" var="cur" varStatus="vs">
                  <li>${cur.md_name}：
                    <c:if test="${cur.fee lt 0}">此产品在该地区暂未维护物流运费，点击<a href="${ctx}/manager/zmd/KonkaXxLogistics.do?method=add&mod_id=835001" class="fblue">这里</a>添加此运费。</c:if>
                    <c:if test="${cur.fee ge 0}">${cur.fee}×${cur.map.xs_counts}=${cur.map.pd_totle_fee}元；</c:if>
                  </li>
                </c:forEach>
              </ul>
            </div></td>
          <td class="title_item" nowrap="nowrap" align="right" valign="top">送货地址：</td>
          <td align="left" valign="top">${af.map.sell_post_area}
            <c:out value="${af.map.sell_post_addr}" /></td>
        </tr>
        <!-- 财务信息End -->
        <!-- 审核Begin -->
        <tr>
          <td colspan="4" style="font-weight:900;">审核</td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">状态：</td>
          <td align="left" colspan="3"><label for="sell_state1" style="cursor:pointer">
              <html-el:radio property="sell_state" styleId="sell_state1" value="20">审核通过</html-el:radio>
            </label>
            <label for="sell_state2" style="cursor:pointer">
              <html-el:radio property="sell_state" styleId="sell_state2" value="21">驳回</html-el:radio>
            </label></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">备注：</td>
          <td align="left" colspan="3"><html-el:textarea property="cw_remarks" styleId="cw_remarks" cols="70" rows="5" />
            <div id="info_chat_content"  style="color:#0066FF;font-size:12px;display:none"><img src="../../images/tishi.gif" style="vertical-align:middle;" /></div></td>
        </tr>
        <!-- 审核End -->
        <tr>
          <td colspan="6" height="40"  align="center"><input class="but4" type="button" name="Submit4" value="提交" id="btn_submit" />
            <input class="but3" type="reset" value="重填" id="reset" />
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" id="btn_back" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div id="message_tip" style="display:none;">
    <div class="ui-overlay">
      <div class="ui-widget-overlay" style="z-index:1001"></div>
      <div class="ui-widget-shadow ui-corner-all" style="width:302px;height:152px;position:fixed;left:35%;top:30%;z-index:1002;"></div>
    </div>
    <div style="position:fixed;width:280px;height:130px;left:35%;top:30%;padding:10px;z-index:1003;" class="ui-widget ui-widget-content ui-corner-all"> <span><img id="loading" src="${ctx}/images/loading.gif" style="display:none;" /><img src="${ctx}/images/wait.gif" />&nbsp;正在提交，请稍等...</span> </div>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery1.5.1.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/styles/tips/js/tips.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("input[name='delivery']:first").attr("checked", "checked");
	
	var total_num = 0;
	<c:forEach items="${konkaXxSellBillDetailsList}" var="cur" varStatus="vs">
		var i = "${vs.count}";
		var counts = parseInt("${cur.counts}");
		var price = parseFloat("${cur.price}");
		var money = counts * price;
		$("#pdAmount_" + i).text(money.toFixed(2));
		total_num = parseInt(total_num) + counts;
	</c:forEach>
	$("#total").children().eq(2).text(total_num);

	var arr = [];
	<c:forEach items="${konkaXxLogisticsList}" var="cur" varStatus="vs">
		var fee = "${cur.fee}";
		arr.push(fee);
	</c:forEach>
	//alert(arr.toString());
	var flag = false;
	for ( var i = 0; i < arr.length; i++) {
		if (parseInt(arr[i], 10) < 0) {
			flag = true;
		}
	}
	if (flag) {
		showHelper('#fee_of_post', '友情提示', '您好！该订单中存在未维护地区物流运费的产品，请您查看物流明细并慎重发货！', 30);
	}
	
	$("#fee_of_post").focus(setOnlyNum1);
	$("input[type='checkbox'][name='delivery']").eq(0).attr({"dataType" : "Group" , "msg" : "请选择是否发货！"});
	$("#fee_of_post").attr("dataType", "Require").attr("msg", "请输入物流费用！");
	$("input[type='radio'][name='sell_state']").eq(1).attr("dataType", "Group").attr("msg", "请选择审核是否通过！");

	$("input[type='radio'][name='sell_state']").click(function(){
		//alert($(this).val());
		if ($(this).val() == 21) {
			$("#cw_remarks").attr("dataType", "Require").attr("msg", "请填写驳回原因！");
		} else {
			$("#cw_remarks").removeAttr("dataType");
		}
	});
	
	$("#cw_remarks").textbox({
		maxLength: 40,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/styles/jxc/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_chat_content").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
			resizeFrameHeight();
		}
	}).blur(function() {
		$("#info_chat_content").slideUp("normal");
		resizeFrameHeight();
	});
	
	//提交表单
	$("#btn_submit").click(function(){
		//判断专卖店可用余额是否大于应付款
		//var total_money  = parseFloat("${af.map.total_money}");
		//var total_credit_line  = parseFloat("${af.map.total_credit_line}");
		//alert("total_money = " + total_money + "\ntotal_credit_line = " + total_credit_line);
		//if (total_credit_line < total_money) {
		//	alert("${af.map.zmd_sn}专卖店的可用发货额度不足");
		//	return false;
		//}
		
		if(Validator.Validate(this.form, 1)){
			if(confirm("确定要提交表单？")){
	            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
	            $("#reset").attr("disabled", "true");
	            $("#btn_back").attr("disabled", "true");
	            $("input[type='checkbox'][name='delivery']").removeAttr("disabled");//移除checkbox的disabled属性，后台可以接收到选中值
	            $("#message_tip").show(); //弹出层
				this.form.submit();
			} else {
				return false;
			}
		}
	});
});

function setOnlyNum1() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;
}

//自适应高度
function resizeFrameHeight(offset, min_height) {
		// frame id can be found in page /konka-wd/WebContent/manager/admin/Frames2/indexFrame.jsp, and search 'iframe' to get.
		$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
}
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>