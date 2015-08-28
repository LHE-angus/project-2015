<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/zmd/KonkaXxDistJs">
      <html-el:hidden property="method" styleId="method" value="list" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td><strong class="fb">订单流水号：</strong>
            <html-el:text property="sell_bill_id" styleId="sell_bill_id" size="15" maxlength="20"></html-el:text>
            &nbsp;<strong class="fb">专卖店编号：</strong>
            <html-el:text property="zmd_sn_like" styleId="zmd_sn_like" />
            &nbsp;<strong class="fb">销售时间：</strong>
            <html-el:text styleId="sell_date_begin" property="sell_date_begin" size="10" maxlength="10" readonly="true" style="cursor:pointer;text-align:center;" title="点击选择日期" />
            至
            <html-el:text styleId="sell_date_end" property="sell_date_end" size="10" maxlength="10" readonly="true" style="cursor:pointer;text-align:center;" title="点击选择日期" />
            &nbsp;
            <input class="but1" type="submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><input style="align:left" name="button" type="button"  value=" 批量结算 " class="but8" onclick="confirmJs(document.getElementById('listForm'));" /></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <form id="listForm" name="listForm" method="post" action="KonkaXxDistJs.do?method=jiesMethod">
      <input type="hidden" name="method" id="method" value="jiesMethod" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" align="center"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
          <td align="center" nowrap="nowrap" align="center">
          订单流水号
          </td>
          <td align="center" nowrap="nowrap" align="center">
          分公司
          </td>
          <td  align="center" nowrap="nowrap" align="center">
          专卖店
          </td>
          <td  align="center" nowrap="nowrap" align="center">
          合计金额
          </td>
          <td  align="center" nowrap="nowrap" align="center">
          销售时间
          </td>
          <td  align="center" nowrap="nowrap" align="center">
          销售单状态
          </td>
          <td  align="center" nowrap="nowrap" align="center">
          付款方式
          </td>
          <td  align="center" nowrap="nowrap" align="center">
          送货费用
          </td>
          <td  align="center" nowrap="nowrap" align="center">
          定金
          </td>
          <td  align="center" nowrap="nowrap" align="center">
          开单时间
          </td>
          <td align="center" width="50" nowrap="nowrap">操作</td>
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="center" nowrap="nowrap"><input name="pks" type="checkbox" id="pks" value="${cur.sell_bill_id}" /></td>
              <td align="center" nowrap="nowrap"><c:out value="${fnx:leftPad_sis(cur.sell_bill_id, 12, '0')}" /></td>
              <td align="center" nowrap="nowrap">${cur.map.dept_name}</td>
              <td align="center" nowrap="nowrap">${cur.zmd_sn}</td>
              <td align="right" nowrap="nowrap"><span class="kz-price-12">
                <fmt:formatNumber type="currency" value="${cur.total_money}" />
                </span></td>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.sell_date}" pattern="yyyy-MM-dd" /></td>
              <td align="center" nowrap="nowrap">确认收货</td>
              <td align="center" nowrap="nowrap">${fn:split(' ,现金付款,POS机刷卡,货到付款', ',')[cur.pay_way]}</td>
              <td align="right" nowrap="nowrap"><span class="kz-price-12">
                <fmt:formatNumber type="currency" value="${empty cur.fee_of_post ? 0 : cur.fee_of_post}" />
                </span></td>
              <td align="right" nowrap="nowrap"><span class="kz-price-12">
                <fmt:formatNumber type="currency" value="${empty cur.money_of_deposit ? 0 : cur.money_of_deposit}" />
                </span></td>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" /></td>
              <td align="center"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('确定结算吗？', 'KonkaXxDistJs.do', 'jiesMethod', 'sell_bill_id=${cur.sell_bill_id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())">结算</span></td>
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
              <td>&nbsp;</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxDistJs.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align:right; padding-right:5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
							var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
							pager.addHiddenInputs("method", "list");
							pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
							pager.addHiddenInputs("sell_bill_id", "${af.map.sell_bill_id}");
							pager.addHiddenInputs("zmd_sn_like", "${af.map.zmd_sn_like}");
							pager.addHiddenInputs("sell_date_begin", "${af.map.sell_date_begin}");
							pager.addHiddenInputs("sell_date_end", "${af.map.sell_date_end}");
							document.write(pager.toString());
						</script>
            </div></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#sell_date_begin").datepicker();
	$("#sell_date_end").datepicker();
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}

	$("#sell_bill_id").focus(setOnlyNum);
});
function confirmJs(form){
	var checkedCount = 0;
	if (!form.pks) {
		return;
	}
	if(!form.pks.length) {
		if (form.pks.checked == true) {
			checkedCount = 1;
		}
	}
	for (var i = 0; i < form.pks.length; i++) {
		if (form.pks[i].checked == true) {
			checkedCount++;
		}
	}
	if (checkedCount == 0) {
		alert("至少选择一个结算项！");
	} else {
		if(confirm("确定结算吗 ？")) {
			form.method.value = "jiesMethod";
			form.submit();
		}
	}
}

function setOnlyNum() {
	$(this).css("ime-mode", "disabled").attr("t_value", "").attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		this.t_value = '';
	});
	//this.text.selected;
}
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
