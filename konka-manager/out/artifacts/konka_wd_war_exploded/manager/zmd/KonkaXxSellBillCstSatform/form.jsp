<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
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
  <html-el:form action="/zmd/KonkaXxSellBillCstSatform">
    <html-el:hidden property="method" value="save" />
    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
    <html-el:hidden property="sell_bill_id" value="${af.map.sell_bill_id}" />
    <html-el:hidden property="queryString" value="${queryString}" />
    <table width="99%" border="0" cellpadding="0" cellspacing="1" class="rtable2" style="margin-top:3px;" align="center">
      <!-- 销售单信息Begin -->
      <tr>
        <td colspan="4" style="font-weight:900;">销售单信息（订单流水号：<span style="color:#FF0000;font-size:15px;">${fnx:leftPad_sis(af.map.sell_bill_id, 12, '0')}</span>）</td>
      </tr>
      <tr>
        <td width="15%" class="title_item" nowrap="nowrap" align="right" ><strong>分公司：</strong></td>
        <td width="35%" align="left" style="padding-bottom:5px;"><c:out value="${af.map.dept_name}" /></td>
        <td width="15%" class="title_item" nowrap="nowrap" align="right">专卖店：</td>
        <td align="left" style="padding-bottom:5px;"><c:out value="${af.map.zmd_sn}" /></td>
      </tr>
      <tr>
        <td width="15%" class="title_item" nowrap="nowrap" align="right">销售人员：</td>
        <td width="35%" align="left" style="padding-bottom:5px;"><c:out value="${af.map.sell_man}" /></td>
        <td width="15%" class="title_item" nowrap="nowrap" align="right">销售时间：</td>
        <td align="left" style="padding-bottom:5px;"><fmt:formatDate value="${af.map.sell_date}" pattern="yyyy-MM-dd" var="_sell_date" />
          ${_sell_date}</td>
      </tr>
      <tr>
        <td width="15%" class="title_item" nowrap="nowrap" align="right">付款方式：</td>
        <td align="left" colspan="3" style="padding-bottom:5px;">${fn:split(' ,现金付款,POS机刷卡,货到付款', ',')[af.map.pay_way]}</td>
      </tr>
      <c:if test="${not empty af.map.money_of_deposit}">
        <tr id="money_of_deposit_tr">
          <td width="15%" class="title_item" nowrap="nowrap" align="right">定金：</td>
          <td align="left" colspan="3" style="padding-bottom:5px;"><span class="kz-price">
            <fmt:formatNumber type="currency" value="${af.map.money_of_deposit}" />
            </span></td>
        </tr>
      </c:if>
      <!-- 销售单信息End -->
    </table>
    <div class="rtabcont1" >
      <div style="100%;overflow-x:auto;border-left:1px solid #ccc;border-right:1px solid #ccc;">
        <!-- 产品信息Begin -->
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2" align="center" style="border-left:0px;border-right:0px;">
          <tr>
            <td colspan="11" style="font-weight:900;">订单商品详细信息</td>
          </tr>
          <tr class="tabtt1">
            <td width="50" align="center" nowrap="nowrap">序号</td>
            <td width="90" align="center" nowrap="nowrap">商品型号</td>
            <td width="100" align="center" nowrap="nowrap">标题</td>
            <td width="60" align="center" nowrap="nowrap">评分</td>
            <td width="70" align="center" nowrap="nowrap">优点</td>
            <td width="110" align="center" nowrap="nowrap">不足</td>
            <td width="100" align="center" nowrap="nowrap">使用心得</td>
          </tr>
          <c:forEach items="${konkaXxSellBillDetailsList}" var="cur" varStatus="vs">
            <c:set var="had_value_flag" value="0" />
            <c:forEach items="${konkaXxSellBillCstSatformList}" var="cur_cst">
              <c:if test="${cur.md_name eq cur_cst.md_name}">
                <c:set var="had_value_flag" value="1" />
                <tr>
                  <td align="center" >${vs.count}</td>
                  <td align="left" class="pdIds"><c:out value="${cur.md_name}" /></td>
                  <td align="left"><input type="text" name="${cur.sell_bill_details_id}_title" value="${cur_cst.title}" maxlength="30" style="width:200px;" class="title" /></td>
                  <td align="left"><html-el:select property="${cur.sell_bill_details_id}_mark_star" style="width:90px;" value="${cur_cst.mark_star}" styleClass="mark_star">
                      <html-el:option value="">=请选择=</html-el:option>
                      <html-el:option value="5">很喜欢</html-el:option>
                      <html-el:option value="4">喜欢</html-el:option>
                      <html-el:option value="3">一般</html-el:option>
                      <html-el:option value="2">不喜欢</html-el:option>
                      <html-el:option value="1">很不喜欢</html-el:option>
                    </html-el:select></td>
                  <td align="left"><html-el:textarea property="${cur.sell_bill_details_id}_pros" styleId="${cur.sell_bill_details_id}_pros" value="${cur_cst.pros}" cols="25" rows="2" styleClass="textarea_info" />
                    <div id="${cur.sell_bill_details_id}_pros_msg"  style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/styles/jxc/images/tishi.gif" style="vertical-align:middle;" /></div></td>
                  <td align="left"><html-el:textarea property="${cur.sell_bill_details_id}_cons" styleId="${cur.sell_bill_details_id}_cons" value="${cur_cst.cons}" cols="25" rows="2" styleClass="textarea_info" />
                    <div id="${cur.sell_bill_details_id}_cons_msg"  style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/styles/jxc/images/tishi.gif" style="vertical-align:middle;" /></div></td>
                  <td align="left"><html-el:textarea property="${cur.sell_bill_details_id}_content" styleId="${cur.sell_bill_details_id}_content" value="${cur_cst.content}" cols="25" rows="2" styleClass="textarea_info" />
                    <div id="${cur.sell_bill_details_id}_content_msg"  style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/styles/jxc/images/tishi.gif" style="vertical-align:middle;" /></div></td>
                </tr>
              </c:if>
            </c:forEach>
            <c:if test="${had_value_flag eq '0'}">
              <tr>
                <td align="center" >${vs.count}</td>
                <td align="left" class="pdIds"><c:out value="${cur.md_name}" /></td>
                <td align="left"><input type="text" name="${cur.sell_bill_details_id}_title"  maxlength="30" style="width:200px;" class="title" /></td>
                <td align="left"><html-el:select property="${cur.sell_bill_details_id}_mark_star" style="width:90px;"  styleClass="mark_star">
                    <html-el:option value="">=请选择=</html-el:option>
                    <html-el:option value="5">很喜欢</html-el:option>
                    <html-el:option value="4">喜欢</html-el:option>
                    <html-el:option value="3">一般</html-el:option>
                    <html-el:option value="2">不喜欢</html-el:option>
                    <html-el:option value="1">很不喜欢</html-el:option>
                  </html-el:select></td>
                <td align="left"><html-el:textarea property="${cur.sell_bill_details_id}_pros" styleId="${cur.sell_bill_details_id}_pros" cols="25" rows="2" styleClass="textarea_info" />
                  <div id="${cur.sell_bill_details_id}_pros_msg"  style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/styles/jxc/images/tishi.gif" style="vertical-align:middle;" /></div></td>
                <td align="left"><html-el:textarea property="${cur.sell_bill_details_id}_cons" styleId="${cur.sell_bill_details_id}_cons" cols="25" rows="2" styleClass="textarea_info" />
                  <div id="${cur.sell_bill_details_id}_cons_msg"  style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/styles/jxc/images/tishi.gif" style="vertical-align:middle;" /></div></td>
                <td align="left"><html-el:textarea property="${cur.sell_bill_details_id}_content" styleId="${cur.sell_bill_details_id}_content" cols="25" rows="2" styleClass="textarea_info" />
                  <div id="${cur.sell_bill_details_id}_content_msg"  style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/styles/jxc/images/tishi.gif" style="vertical-align:middle;" /></div></td>
              </tr>
            </c:if>
          </c:forEach>
        </table>
        <!-- 产品信息End -->
      </div>
    </div>
    <table width="99%" border="0" cellpadding="0" cellspacing="1" class="rtable2" align="center">
      <!-- 客户信息Begin -->
      <tr>
        <td colspan="4" style="font-weight:900;">客户信息</td>
      </tr>
      <tr>
        <td width="15%" class="title_item" nowrap="nowrap" align="right" ><strong>客户姓名：</strong></td>
        <td width="35%" align="left" style="padding-bottom:5px;"><c:out value="${af.map.buyer_name}"></c:out>
          <c:if test="${empty af.map.buyer_name}"><span style="color:gray;">未填写</span></c:if></td>
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
          <c:if test="${empty _buyer_birthday}"><span style="color:gray;">未填写</span></c:if></td>
      </tr>
      <tr>
        <td width="15%" class="title_item" nowrap="nowrap" align="right" ><strong>电话：</strong></td>
        <td width="35%" align="left" style="padding-bottom:5px;"><c:out value="${af.map.buyer_tel}" />
          <c:if test="${empty af.map.buyer_tel}"><span style="color:gray;">未填写</span></c:if></td>
        <td width="15%" class="title_item" nowrap="nowrap" align="right">手机：</td>
        <td align="left" style="padding-bottom:5px;"><c:out value="${af.map.buyer_phone}" />
          <c:if test="${empty af.map.buyer_phone}"><span style="color:gray;">未填写</span></c:if></td>
      </tr>
      <tr>
        <td width="15%" class="title_item" nowrap="nowrap" align="right">客户联系地址：</td>
        <td align="left" colspan="3" style="padding-bottom:5px;"><c:out value="${af.map.buyer_link_addr}" />
          <c:if test="${empty af.map.buyer_link_addr}"><span style="color:gray;">未填写</span></c:if></td>
      </tr>
      <tr>
        <td width="15%" class="title_item" nowrap="nowrap" align="right">消费者备注：</td>
        <td align="left" colspan="3" style="padding-bottom:5px;"><c:out value="${af.map.buyer_memo}" />
          <c:if test="${empty af.map.buyer_memo}"><span style="color:gray;">无</span></c:if></td>
      </tr>
      <!-- 客户信息End -->
      <tr>
        <td colspan="6" height="40"  align="center"><input class="but4" type="button" name="Submit4" id="send" value="提交" />
          <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" id="btn_back" /></td>
      </tr>
    </table>
  </html-el:form>
</div>
<div id="message_tip" style="display:none;z-index:888888888;">
  <div class="ui-overlay">
    <div class="ui-widget-overlay"></div>
    <div class="ui-widget-shadow ui-corner-all" style="width:182px;height:102px;position:absolute;left:35%;top:25%"></div>
  </div>
  <div style="position:absolute;width:160px;height:80px;left:35%;top:25%;padding:10px;" class="ui-widget ui-widget-content ui-corner-all"> <span><img id="loading" src="${ctx}/images/loading.gif" />正在提交，请稍等...</span> </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery1.5.1.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$(".title").attr("dataType", "Require").attr("msg", "请填写标题！");
	$(".mark_star").attr("dataType", "Require").attr("msg", "请选择评分！");
	$(".textarea_info").attr("dataType", "Require").attr("msg", "请填写优点、不足和使用心得！");
	
	$("#send").click(function(){
		$('#message_tip').show();
		if(Validator.Validate(this.form, 1)) {
			this.form.submit();
		} else {
			$('#message_tip').hide();
		}
	});
	
	$(".textarea_info").textbox({
		maxLength: 200,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/styles/jxc/images/tishi.gif" style="vertical-align:middle;" />';
			$("#" + $(this).attr("id") + "_msg").slideDown("fast").html(img + " 您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#" + $(this).attr("id") + "_msg").slideUp("normal");
	});
});
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>