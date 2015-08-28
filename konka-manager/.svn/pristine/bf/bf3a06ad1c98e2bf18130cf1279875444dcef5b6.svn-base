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
<link href="${ctx}/styles/tips/css/Popup.css" rel="stylesheet" type="text/css" />
<style type="text/css">
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
<body>
<div class="oarcont" style="position:relative;overflow:hidden;">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}<span class="sel-tab"><a href="${ctx}/customer/manager/JBill.do?method=list&mod_id=${af.map.mod_id}&bill_type=20&to_default_page=1">一步价经营模式</a></span><span class="sel-tab sel-tab-active">分公司返利经营模式</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/manager/KonkaXxZmdSearchSalesOrder">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <div id="condition_div" style="100%;overflow-x:auto;" >
        <table id="condition_table" width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable5">
          <tr>
            <td width="10%">&nbsp;<strong class="fb">专卖店编号：</strong></td>
            <td width="40%"><c:if test="${empty zmd_sn}">
                <html-el:select property="zmd_id" onchange="this.form.submit();" style="width:154px;">
                  <html-el:option value="">==请选择==</html-el:option>
                  <c:forEach var="cur" items="${zmdList}">
                    <html-el:option value="${cur.zmd_id}">${cur.zmd_sn}</html-el:option>
                  </c:forEach>
                </html-el:select>
              </c:if>
              <c:if test="${not empty zmd_sn}">
                <label style="color:blue;">${zmd_sn}</label>
              </c:if></td>
            <td width="10%"><strong class="fb">订单流水号： </strong></td>
            <td width="40%"><html-el:text property="sell_bill_id" styleId="sell_bill_id" size="20" maxlength="12" onfocus="javascript:setOnlyInt(this)"></html-el:text></td>
          </tr>
          <tr>
            <td><strong class="fb">&nbsp;销售单状态：</strong></td>
            <td><html-el:select property="sell_state" styleId="sell_state" onchange="this.form.submit();" style="width:154px;">
                <html-el:option value="">==请选择==</html-el:option>
                <html-el:option value="0">未付款</html-el:option>
                <html-el:option value="10">待审核</html-el:option>
                <html-el:option value="20">已审核</html-el:option>
                <html-el:option value="30">已发货</html-el:option>
                <html-el:option value="70">交易成功</html-el:option>
              </html-el:select></td>
            <td><strong class="fb">消费者姓名：</strong></td>
            <td><html-el:text property="buyer_name_like" styleId="buyer_name_like" size="20" maxlength="20" ></html-el:text></td>
          </tr>
          <tr>
            <td><strong class="fb">&nbsp;开单人姓名：</strong></td>
            <td><html-el:text property="sell_man_like" styleId="sell_man_like" size="20" maxlength="20" ></html-el:text></td>
            <td><strong class="fb">开单日期：</strong></td>
            <td><html-el:text property="add_date_ge" styleId="add_date_ge" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
              至
              <html-el:text property="add_date_le" styleId="add_date_le" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
              &nbsp;
              <html-el:select property="timeScope" styleId="timeScope">
                <html-el:option value="">=请选择=</html-el:option>
                <html-el:option value="1">过去一周</html-el:option>
                <html-el:option value="2">过去一个月</html-el:option>
                <html-el:option value="3">过去三个月</html-el:option>
              </html-el:select></td>
          </tr>
          <tr>
            <td><strong class="fb">&nbsp;付款方式：</strong></td>
            <td><html-el:select property="pay_way" styleId="pay_way" onchange="this.form.submit();" style="width:154px;">
                <html-el:option value="">==请选择==</html-el:option>
                <html-el:option value="1">现金支付</html-el:option>
                <html-el:option value="2">POS机刷卡</html-el:option>
                <html-el:option value="3">货到付款</html-el:option>
              </html-el:select></td>
            <td colspan="2"><html-el:select property="stock_from" styleId="stock_from" onchange="this.form.submit();" style="width:154px;">
                <html-el:option value="">==库存来源==</html-el:option>
                <html-el:option value="0">专卖店库存</html-el:option>
                <html-el:option value="1">分公司库存</html-el:option>
              </html-el:select>
              
              <!-- 
              <html-el:select property="order_type" styleId="stock_from" onchange="this.form.submit();" style="width:154px;">
                <html-el:option value="">==订单分类==</html-el:option>
                <html-el:option value="1">销售单</html-el:option>
                <html-el:option value="2">进货单</html-el:option>
              </html-el:select>
               --> 
              &nbsp;&nbsp;
              <input class="but1" type="button" id="btn_submit" name="Submit" value="搜索" /></td>
          </tr>
        </table>
      </div>
    </html-el:form>
  </div>
  <div class="rtabcont1" style="overflow:auto;">
    <%@ include file="/commons/pages/messages.jsp" %>
   
    <div>
    <a href="${ctx}/customer/manager/KonkaXxZmdDztzSearch.do?mod_id=${af.map.mod_id}">销售电子台帐查询</a>
    <a href="${ctx}/customer/manager/KonkaXxSellBillCstSatform.do?mod_id=${af.map.mod_id}">用户回访</a>
    <a href="${ctx}/customer/manager/KonkaXxZmdSellJsSearch.do?mod_id=${af.map.mod_id}">佣金查询</a>
    <input type="submit" value="批量转R3" onclick="confirmSwitchAll(document.getElementById('listForm1'));" />
    </div>
     <form id="listForm1" name="listForm1"  method="post" action="${ctx}/customer/manager/KonkaXxZmdSearchSalesOrder.do">
    <input type="hidden" name="method" id="method" value="switchR3All" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
      <td align="center" nowrap="nowrap">
      	<input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" />
      	</td>
        <td width="10%" align="center" nowrap="nowrap">订单流水号</td>
        <c:if test="${empty zmd_sn}">
          <td width="8%" align="center" nowrap="nowrap">专卖店编号</td>
        </c:if>
        <td width="8%" align="center" nowrap="nowrap">销售单状态</td>
        <td width="8%" align="center" nowrap="nowrap">开单时间</td>
        <td align="center" nowrap="nowrap">开单人</td>
        <td width="8%" align="center" nowrap="nowrap">收货人</td>
        <td width="8%" align="center" nowrap="nowrap">付款方式</td>
        <td width="10%" align="center" nowrap="nowrap">审核时间</td>
        <td width="10%" align="center" nowrap="nowrap">发货时间</td>
        <td width="10%" align="center" nowrap="nowrap">收货时间</td>
        <td width="20%" align="center" nowrap="nowrap">操作</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
       <td align="center" nowrap="nowrap">
         <c:if test="${cur.is_switch ne 1}">
       <input name="pks" type="checkbox" id="pks" value="${cur.sell_bill_id}" />
       </c:if>
       </td>
          <td align="center" nowrap="nowrap"><a href="${ctx}/customer/manager/KonkaXxZmdAddSalesOrder.do?method=view&sell_bill_id=${cur.sell_bill_id}&mod_id=${af.map.mod_id}"><font class="fblue">
            <c:out value="${fnx:leftPad_sis(cur.sell_bill_id, 12, '0')}" />
            </font></a></td>
          <c:if test="${empty zmd_sn}">
            <td align="left" nowrap="nowrap">${cur.zmd_sn}</td>
          </c:if>	
          <td align="center" nowrap="nowrap"><c:choose>
              <c:when test="${cur.sell_state eq 0}"><span style="color:#ccc;">未付款</span></c:when>
              <c:when test="${cur.sell_state eq 10}"><span style="color:red;">待审核</span></c:when>
              <c:when test="${cur.sell_state eq 20}">已审核</c:when>
              <c:when test="${cur.sell_state eq 21}">已审核不通过</c:when>
              <c:when test="${cur.sell_state eq 30}">已发货</c:when>
              <c:when test="${cur.sell_state eq 70}"><span style="color:green;">交易成功</span></c:when>
              <c:otherwise>-</c:otherwise>
            </c:choose></td>
          <td align="center" nowrap="nowrap" title="<fmt:formatDate value="${cur.add_date}" pattern="yyyy年MM月dd日 HH:mm:ss" />
          ">
          <fmt:formatDate value="${cur.add_date}" pattern="yyyy/MM/dd HH:mm" />
          <c:out value="${empty cur.add_date ? '-' : ''}" />
            </td>
          <td align="left" nowrap="nowrap">${cur.sell_man}</td>
          <td align="left" nowrap="nowrap">${cur.sell_rec_name}</td>
          <td align="center" nowrap="nowrap">${fn:split(' ,现金付款,POS机刷卡,货到付款', ',')[cur.pay_way]} </td>
          <td align="center" nowrap="nowrap" title="<fmt:formatDate value="${cur.audit_date}" pattern="yyyy年MM月dd日 HH:mm:ss" />
          ">
          <fmt:formatDate value="${cur.audit_date}" pattern="yyyy/MM/dd HH:mm" />
          ${empty cur.audit_date ? '-' : ''}
            </td>
          <td align="center" nowrap="nowrap" title="<fmt:formatDate value="${cur.dist_date}" pattern="yyyy年MM月dd日 HH:mm:ss" />
          ">
          <fmt:formatDate value="${cur.dist_date}" pattern="yyyy/MM/dd HH:mm" />
          ${empty cur.dist_date ? '-' : ''}
            </td>
          <td align="center" nowrap="nowrap" title="<fmt:formatDate value="${cur.take_googs_date}" pattern="yyyy年MM月dd日 HH:mm:ss" />
          ">
          <fmt:formatDate value="${cur.take_googs_date}" pattern="yyyy/MM/dd HH:mm" />
          ${empty cur.take_googs_date ? '-' : ''}
            </td>
          <td align="center" nowrap="nowrap"><!-- <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxZmdAddSalesOrder.do', 'view','sell_bill_id=${cur.sell_bill_id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">查看</span> -->
          <c:if test="${cur.is_switch ne 1}">
            <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('是否确认转R3订单？', 'KonkaXxZmdSearchSalesOrder.do', 'switchR3', 'sell_bill_id=${cur.sell_bill_id}&mod_id=${af.map.mod_id}');">转R3单</span> |&nbsp;
           </c:if>
            <c:if test="${cur.is_switch eq 1}">
           <span style="color:#ccc">转R3单</span>&nbsp;|&nbsp;
             </c:if>
            <c:choose>
              <c:when test="${cur.return_state eq 0 && (cur.sell_state le 10 || cur.sell_state eq 21) && role_id_eq_400}"> &nbsp;<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxZmdAddSalesOrder.do', 'edit', 'sell_bill_id=${cur.sell_bill_id}&mod_id=${af.map.mod_id}');$('#message_tip').show();">编辑</span> |&nbsp;<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxZmdSearchSalesOrder.do', 'jfEdit', 'sell_bill_id=${cur.sell_bill_id}&mod_id=${af.map.mod_id}');$('#message_tip').show();">调整积分</span> </c:when>
              <c:otherwise>&nbsp;<span style="color:#ccc">编辑</span>|&nbsp;<span style="color:#ccc">调整积分</span></c:otherwise>
            </c:choose>
            |&nbsp;<img src="${ctx}/images/print2.png" /> <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxPrintReceipt.do', 'showReceipt','sell_bill_id=${cur.sell_bill_id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">打印收款单</span></td>
        </tr>
      </c:forEach>
      <c:forEach begin="${fn:length(entityList)}" end="${af.map.pager.pageSize - 1}" step="1">
        <tr>
          <c:if test="${empty zmd_sn}">
            <td>&nbsp;</td>
          </c:if>
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
    </table>
    </form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxZmdSearchSalesOrder.do?method=list">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id","${af.map.mod_id}");
			pager.addHiddenInputs("zmd_id", "${af.map.zmd_id}");	
            pager.addHiddenInputs("sell_bill_id", "${af.map.sell_bill_id}");
            pager.addHiddenInputs("add_date_ge", "${af.map.add_date_ge}");
            pager.addHiddenInputs("add_date_le", "${af.map.add_date_le}");
            pager.addHiddenInputs("sell_state", "${af.map.sell_state}");
			pager.addHiddenInputs("sell_man_like", "${fn:escapeXml(af.map.sell_man_like)}");
			pager.addHiddenInputs("buyer_name_like", "${fn:escapeXml(af.map.buyer_name_like)}");
			pager.addHiddenInputs("pay_way", "${af.map.pay_way}");
			pager.addHiddenInputs("stock_from", "${af.map.stock_from}");
			pager.addHiddenInputs("order_type", "${af.map.order_type}");
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
  <div id="message_tip" style="display:none;">
    <div class="ui-overlay">
      <div class="ui-widget-overlay"></div>
      <div class="ui-widget-shadow ui-corner-all" style="width:302px;height:152px;position:absolute;left:35%;top:25%"></div>
    </div>
    <div style="position:absolute;width:280px;height:130px;left:35%;top:25%;padding:10px;" class="ui-widget ui-widget-content ui-corner-all"> <span><img id="loading" src="${ctx}/images/loading.gif" style="display:none;" /><img src="${ctx}/images/search_wangwang.gif" />&nbsp;正在查询，请稍等...</span> </div>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
<script type="text/javascript" src="${ctx}/styles/tips/js/tips.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#add_date_ge").datepicker();
	$("#add_date_le").datepicker();
	$("#add_date_ge").attr("dataType", "Require").attr("msg", "请选择开单起始时间！");
	$("#add_date_le").attr("dataType", "Require").attr("msg", "请选择开单结束时间！");

	$("#timeScope").change(function(){
		var today = new Date();
		today = today.valueOf();
		var yes = today - 1 * 1000 * 60 * 60 * 24; //昨天
		yes = new Date(yes).format('yyyy-MM-dd');
		var yesVal = new Date(yes).valueOf();
		
		if ($(this).val() == 1) {
			var lastWeek = yesVal - 6 * 1000 * 60 * 60 * 24; //过去一周
			lastWeek = new Date(lastWeek).format('yyyy-MM-dd');
			$("#add_date_ge").val(lastWeek);
			$("#add_date_le").val(yes);
		} else if ($(this).val() == 2) {
			var lastMonth = yesVal - 30 * 1000 * 60 * 60 * 24; //过去一个月
			lastMonth = new Date(lastMonth).format('yyyy-MM-dd');
			$("#add_date_ge").val(lastMonth);
			$("#add_date_le").val(yes);
		} else if ($(this).val() == 3) {
			var lastThreeMonth = yesVal - 91 * 1000 * 60 * 60 * 24; //过去三个月
			lastThreeMonth = new Date(lastThreeMonth).format('yyyy-MM-dd');
			$("#add_date_ge").val(lastThreeMonth);
			$("#add_date_le").val(yes);
		}
	});
	
	$("#btn_submit").click(function(){
		var s = $("#add_date_ge").val();
		var e = $("#add_date_le").val();
		if($.trim(s).length > 0 && $.trim(e).length > 0){
			var d_d = (new Date(e.replace("-", "/")).getTime() - new Date(s.replace("-", "/")).getTime()) / 1000 / 60 / 60 / 24;
			if (s > e) {
				alert("开始时间不得大于结束时间！");
				return false;
			}
			if (d_d > 91) {
				alert("查询时间范围不能超过3个月！");
				return false;
			}
		}
		if($.trim(s).length == 0 || $.trim(e).length == 0){
			alert("请选择时间范围！");
			return false;
		}
		this.form.submit();
	});
});

Date.prototype.format = function(format)    {        
	var o = {        
			"M+" : this.getMonth()+1, //month        
			"d+" : this.getDate(),    //day        
			"h+" : this.getHours(),   //hour        
			"m+" : this.getMinutes(), //minute        
			"s+" : this.getSeconds(), //second        
			"q+" : Math.floor((this.getMonth()+3)/3),  //quarter       
			"S" : this.getMilliseconds() //millisecond        
			}        
	if(/(y+)/.test(format)) format=format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));        
	for(var k in o)if(new RegExp("("+ k +")").test(format))        
	format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k]:("00"+ o[k]).substr((""+ o[k]).length));return format;
}

//正则表达式：只能输入正数字
function setOnlyInt(obj) {
	$(obj).css("ime-mode", "disabled");
	$(obj).attr("t_value", "");
	$(obj).attr("o_value", "");
	$(obj).bind("dragenter",function(){
		return false;
	});
	$(obj).keypress(function (){
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).keyup(function (){
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).blur(function (){
		if(!obj.value.match(/^(?:\d+(?:\d+)?|\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\d+$/))obj.value=obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
		if(obj.value.length == 0 || isNaN(obj.value) || obj.value == 0) obj.value = "0";
	});
}
function confirmSwitchAll(form){
	
	//form.action = 'KonkaXxZmdSearchSalesOrder.do';
	//form.method.value = 'switchR3All';
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
		alert("请至少选择一个！");
	} else {
			form.submit();
	}
}  



//]]></script>

<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>