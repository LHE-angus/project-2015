<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<style type="text/css">
  .btn_back {width:100px;height:20px;background:url(${ctx}/images/manager/but_l.gif) no-repeat;font:normal 12px/20px "宋体";text-align:left;color:#fff;padding-left:27px;border:1px #ccc solid;border-left:0;cursor:pointer;}
</style>
</head>
<body>
<div class="oarcont" style="position:relative;overflow:hidden;">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/zmd/KonkaXxSellBillJSStatis">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="zmd_id" value="${af.map.zmd_id}" />
        <table id="condition_table" width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable5">
          <tr>
            <td width="15">&nbsp;</td>
            <td width="260" nowrap="nowrap"><strong class="fb">日期：</strong>
            	<html-el:text property="date_start" styleId="date_start" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
            	&nbsp;至&nbsp;
            	<html-el:text property="date_end" styleId="date_end" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
            </td>
            <td width="150" nowrap="nowrap"><strong class="fb">付款方式：</strong><html-el:select property="pay_way" styleId="pay_way" style="width:100px;">
                <html-el:option value="1">现金支付</html-el:option>
                <html-el:option value="2">POS机刷卡</html-el:option>
                <html-el:option value="3">货到付款</html-el:option>
              </html-el:select>
            </td>
            <td width="200" nowrap="nowrap"><strong class="fb" id="checkout_zmd_state_text">专卖店结算状态：</strong><html-el:select property="checkout_zmd_state" styleId="checkout_zmd_state" style="width:100px;">
                <html-el:option value="1">已结算</html-el:option>
                <html-el:option value="0">未结算</html-el:option>
              </html-el:select>
            </td>
            <td width="200" nowrap="nowrap" id="td_checkout_dist_state"><strong class="fb">物流结算状态：</strong><html-el:select property="checkout_dist_state" styleId="checkout_dist_state" style="width:100px;">
                <html-el:option value="">==请选择==</html-el:option>
                <html-el:option value="1">已结算</html-el:option>
                <html-el:option value="0">未结算</html-el:option>
              </html-el:select>
            </td>
            <td align="left"><input class="but1" type="button" id="btn_submit" name="Submit" value="搜索" /></td>
          </tr>
        </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1" style="overflow:auto;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td width="10%" align="center" nowrap="nowrap">订单流水号</td>
        <td width="8%" align="center" nowrap="nowrap">专卖店编号</td>
        <td width="6%" align="center" nowrap="nowrap">销售单状态</td>
        <td width="8%" align="center" nowrap="nowrap">开单时间</td>
        <td width="5%" align="center" nowrap="nowrap">开单人</td>
        <td width="5%" align="center" nowrap="nowrap">收货人</td>
        <td width="5%" align="center" nowrap="nowrap">付款方式</td>
        <c:if test="${af.map.pay_way eq 3}">
           <td width="5%" align="center" nowrap="nowrap">专卖店结算状态</td>
           <td width="5%" align="center" nowrap="nowrap">物流结算状态</td>
        </c:if>
        <td width="5%" align="center" nowrap="nowrap">财务结算</td>
        <td width="8%" align="center" nowrap="nowrap">订单总额</td>
        <td width="8%" align="center" nowrap="nowrap">专卖店应收款</td>
        <c:if test="${af.map.pay_way eq 3}">
        	<td width="8%" align="center" nowrap="nowrap">物流应收款</td>
        </c:if>
        <td width="5%" align="center" nowrap="nowrap">操作</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap"><a href="${ctx}/manager/zmd/KonkaXxZmdAddSalesOrder.do?method=view&sell_bill_id=${cur.sell_bill_id}&mod_id=${af.map.mod_id}"><font class="fblue"><c:out value="${fnx:leftPad_sis(cur.sell_bill_id, 12, '0')}" /></font></a></td>
          <td align="left" nowrap="nowrap">${cur.zmd_sn}</td>
          <td align="center" nowrap="nowrap"><c:choose>
              <c:when test="${cur.sell_state eq 0}"><span style="color:#ccc;">未付款</span></c:when>
              <c:when test="${cur.sell_state eq 10}"><span style="color:red;">待审核</span></c:when>
              <c:when test="${cur.sell_state eq 20}">已审核</c:when>
              <c:when test="${cur.sell_state eq 30}">已发货</c:when>
              <c:when test="${cur.sell_state eq 40}">已确认收货</c:when>
              <c:when test="${cur.sell_state eq 70}"><span style="color:green;">交易成功</span></c:when>
              <c:otherwise>-</c:otherwise>
            </c:choose></td>
          <td align="center" nowrap="nowrap" title="<fmt:formatDate value="${cur.add_date}" pattern="yyyy年MM月dd日 HH:mm:ss" />"><fmt:formatDate value="${cur.add_date}" pattern="yy/MM/dd HH:mm" /><c:out value="${empty cur.add_date ? '-' : ''}" /></td>
          <td align="left" nowrap="nowrap">${cur.add_user_realname}</td>
          <td align="left" nowrap="nowrap">${cur.sell_rec_name}</td>
          <td align="center" nowrap="nowrap">${fn:split(' ,现金付款,POS机刷卡,货到付款', ',')[cur.pay_way]} </td>
          <c:if test="${af.map.pay_way eq 3}">
          	<td align="center" nowrap="nowrap"><c:if test="${cur.checkout_zmd_state eq 0}"><span style="color:#FF0000;">未结算</span></c:if>
            	<c:if test="${cur.checkout_zmd_state eq 1}"><span style="color:green;">已结算</span></c:if>
            </td>
          	<td align="center" nowrap="nowrap"><c:if test="${cur.checkout_dist_state eq 0}"><span style="color:red;">未结算</span></c:if>
            	<c:if test="${cur.checkout_dist_state eq 1}"><span style="color:green;">已结算</span></c:if>
            </td>
          </c:if>
          <td align="center" nowrap="nowrap"><c:if test="${cur.checkout_state eq 0}"><span style="color:red;">未结算</span></c:if>
            <c:if test="${cur.checkout_state eq 1}"><span style="color:green;">已结算</span></c:if>
          </td>
          <td align="right" nowrap="nowrap">
          	<c:set var="all_money" value="${ all_money + cur.total_money }" />
          	<span class="kz-price-12"><fmt:formatNumber value="${cur.total_money}" type="currency" /></span></td>
          <td align="right" nowrap="nowrap">
            <c:if test="${af.map.pay_way eq 1}">
            	<c:set var="zmd_money" value="${ zmd_money + cur.total_money }" />
          		<span class="kz-price-12"><fmt:formatNumber value="${cur.total_money}" type="currency" /></span>
            </c:if>
            <c:if test="${af.map.pay_way eq 2}">
            	<c:set var="zmd_money" value="${ zmd_money + 0 }" />
          		<span class="kz-price-12"><fmt:formatNumber value="0" type="currency" /></span>
            </c:if>
            <c:if test="${af.map.pay_way eq 3}">
            	<c:set var="zmd_money" value="${ zmd_money + cur.money_of_deposit }" />
          		<span class="kz-price-12"><fmt:formatNumber value="${cur.money_of_deposit}" type="currency" /></span>
            </c:if>
          </td>
          <c:if test="${af.map.pay_way eq 3}">
          	<c:set var="wl_money" value="${ wl_money + cur.total_money - cur.money_of_deposit}" />
          	<td align="right" nowrap="nowrap"><span class="kz-price-12"><fmt:formatNumber value="${cur.total_money - cur.money_of_deposit}" type="currency" /></span></td>
          </c:if>
          <td align="center" nowrap="nowrap"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxZmdAddSalesOrder.do', 'view','sell_bill_id=${cur.sell_bill_id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">查看</span></td>
        </tr>
      </c:forEach>
      <c:forEach begin="${fn:length(entityList)}" end="${af.map.pager.pageSize - 1}" step="1">
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <c:if test="${af.map.pay_way eq 3}">
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          	<td>&nbsp;</td>
          </c:if>
        </tr>
      </c:forEach>
    </table>
    <c:if test="${not empty entityList}">
	    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2" style="border-top:0px;">
	    	<tr>
	    		<td width="200" align="right" height="35" nowrap="nowrap"><span style="font-weight:bold;">订单总额合计（当前数据）：</span></td>
	    		<td width="120" align="right" nowrap="nowrap"><span class="kz-price-12"><fmt:formatNumber value="${all_money}" type="currency" /></span></td>
	    		<td width="230" align="right" nowrap="nowrap"><span style="font-weight:bold;">专卖店应收款合计（当前数据）：</span></td>
	    		<td width="120" align="right" nowrap="nowrap"><span class="kz-price-12"><fmt:formatNumber value="${zmd_money}" type="currency" /></span></td>
	    		<c:if test="${af.map.pay_way eq 3}">
	    			<td width="230" align="right" nowrap="nowrap"><span style="font-weight:bold;">物流应收款合计（当前数据）：</span></td>
	    			<td width="120" align="right" nowrap="nowrap"><span class="kz-price-12"><fmt:formatNumber value="${wl_money}" type="currency" /></span></td>
	    		</c:if>
	    		<td>&nbsp;</td>
	    	</tr>
	    </table>
    </c:if>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxSellBillJSStatis.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="200" align="left"><input class="btn_back" type="button" id="btn_back" value="返回上一步" onclick="$('#message_tip').show();goBack('${af.map.zmd_id}','${af.map.date_start}','${af.map.date_end}','${af.map.mod_id}');" /></td>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script> 
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id","${af.map.mod_id}");
			pager.addHiddenInputs("pay_way", "${af.map.pay_way}");
			pager.addHiddenInputs("zmd_id", "${af.map.zmd_id}");
			pager.addHiddenInputs("date_start", "${af.map.date_start}");
			pager.addHiddenInputs("date_end", "${af.map.date_end}");
			pager.addHiddenInputs("checkout_dist_state", "${af.map.checkout_dist_state}");
			pager.addHiddenInputs("checkout_zmd_state", "${af.map.checkout_zmd_state}");
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<!-- 提交 覆盖层  -->
<div id="message_tip" style="display:none;z-index:888888888;">
  <div class="ui-overlay">
    <div class="ui-widget-overlay"></div>
    <div class="ui-widget-shadow ui-corner-all" style="width:302px;height:152px;position:absolute;left:35%;top:25%"></div>
  </div>
  <div style="position:absolute;width:280px;height:130px;left:35%;top:25%;padding:10px;" class="ui-widget ui-widget-content ui-corner-all"> <span><img id="loading" src="${ctx}/images/loading.gif" />正在查询，请稍等...</span> </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#add_date_ge").datepicker();
	$("#add_date_le").datepicker();

	$("#date_start").attr("dataType" , "Require").attr("msg" , "请选择开始日期！");
	$("#date_end"  ).attr("dataType" , "Require").attr("msg" , "请选择结束日期！");
	
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 1)){
			var s = $("#date_start").val();
			var e = $("#date_end").val();
			var d_d = (new Date(e.replace("-", "/")).getTime() - new Date(s.replace("-", "/")).getTime()) / 1000 / 60 / 60 / 24;
			if (s > e) {
				alert("开始时间不得大于结束时间！");
				return false;
			}
			if (d_d > 91) {
				alert("查询时间范围不能超过3个月！");
				return false;
			}
			
			var pay_way = $("#pay_way").val();
			if("3" == pay_way){ // 是货到付款条件
				var checkout_dist_state = $("#checkout_dist_state").val();
				if("" == checkout_dist_state){
					alert("请选择物流结算状态！");
					return false;
				}
			} else {
				$("#checkout_dist_state").val("");
			}
			
			$('#message_tip').show(); // 查询会比较慢，提供覆盖层
			//$(".form_search").submit();
			this.form.submit();
		}
	});
	
	// 初始化查询条件
	var pay_way = $("#pay_way").val();
	if("3" != pay_way){ // 不是货到付款则隐藏物流条件
		$("#td_checkout_dist_state").hide();
	}
	
	$("#pay_way").change(function (){
		var pay_way = $("#pay_way").val();
		if("3" != pay_way){ // 不是货到付款则隐藏物流条件
			$("#td_checkout_dist_state").hide();
		} else {
			$("#td_checkout_dist_state").show();
		}	
	});
});

function goBack(zmd_id, date_start, date_end, mod_id){
	location.href = "${ctx}/manager/zmd/KonkaXxSellBillJSStatis.do?method=zmdView&date_start=" + date_start + "&date_end=" + date_end + "&zmd_id=" + zmd_id + "&mod_id=" + mod_id;
}
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>