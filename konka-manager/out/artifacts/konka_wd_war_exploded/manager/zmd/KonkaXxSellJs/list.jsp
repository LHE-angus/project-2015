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
.but_update {
	width:90px;
	height:20px;
	background:url(${ctx}/images/manager/but_u.gif) no-repeat;
	font:normal 12px/20px "宋体";
	text-align:left;
	color:#fff;
	padding-left:27px;
	border:1px #ccc solid;
	border-left:0;
	cursor:pointer;
}
</style>
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/zmd/KonkaXxSellJs">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15">&nbsp;</td>
          <td><strong class="fb">订单流水号：</strong>
            <html-el:text property="sell_bill_id" maxlength="15" styleId="sell_bill_id"/></td>
          <td><strong class="fb">专卖店编号：</strong>
            <html-el:select property="zmd_sn" onchange="this.form.submit();" style="width:154px;">
              <html-el:option value="">==请选择==</html-el:option>
              <c:forEach var="cur" items="${zmdList}">
                <html-el:option value="${cur.zmd_sn}">${cur.zmd_sn}</html-el:option>
              </c:forEach>
            </html-el:select></td>
          <td><strong class="fb">销售时间：</strong>
            <html-el:text property="sell_date_begin" styleId="sell_date_begin" size="10" maxlength="10" readonly="true" style="cursor:pointer;text-align:center;" title="点击选择日期" />
            &nbsp;至&nbsp;
            <html-el:text property="sell_date_end" styleId="sell_date_end" size="10" maxlength="10" readonly="true" style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
          <td><input class="but1" type="submit" name="submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <c:if test="${role_id_true}">
      <!-- 不是分公司用户不让结算 -->
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><input name="button" type="button"  class="but_update" value=" 批量操作" onclick="$('#message_tip').show();confirmJs(document.getElementById('listForm'));" /></td>
        </tr>
      </table>
    </c:if>
  </div>
  <div class="rtabcont1">
    <form id="listForm" name="listForm" method="post" action="KonkaXxSellJs.do">
      <input type="hidden" name="method" id="method" value="save" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" align="center"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
          <td width="8%" align="center" nowrap="nowrap">订单流水号</td>
          <td width="15%" align="center" nowrap="nowrap">专卖店编号</td>
          <td width="10%" align="center" nowrap="nowrap">合计金额</td>
          <td width="10%" align="center" nowrap="nowrap">销售时间</td>
          <td width="15%" align="center" nowrap="nowrap">销售单状态</td>
          <td width="10%" align="center" nowrap="nowrap">付款方式</td>
          <td width="10%" align="center" nowrap="nowrap">物流费用</td>
          <td width="10%" align="center" nowrap="nowrap">定金</td>
          <td align="center" width="10%">操作</td>
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="center" nowrap="nowrap"><input name="pks" type="checkbox" id="pks" class="sell_bill_pks" value="${cur.sell_bill_id}" /></td>
              <td align="center" nowrap="nowrap"><font class="blue12px">
                <c:out value="${fnx:leftPad_sis(cur.sell_bill_id, 12, '0')}" />
                </font></td>
              <td align="left" nowrap="nowrap">${cur.zmd_sn}</td>
              <td align="right" nowrap="nowrap" class="kz-price-12"><fmt:formatNumber value="${cur.total_money}" type="currency" /></td>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.sell_date}" pattern="yyyy-MM-dd" /></td>
              <td align="center" nowrap="nowrap"><span style="color:green;">确认收货</span></td>
              <td align="center" nowrap="nowrap">${fn:split(' ,现金付款,POS机刷卡,货到付款', ',')[cur.pay_way]}</td>
              <td align="right" nowrap="nowrap" class="kz-price-12"><fmt:formatNumber value="${cur.fee_of_post}" type="currency" /></td>
              <td align="right" nowrap="nowrap" class="kz-price-12"><c:if test="${not empty cur.money_of_deposit}">
                  <fmt:formatNumber value="${cur.money_of_deposit}" type="currency" />
                </c:if>
                <c:if test="${empty cur.money_of_deposit}"> <span style="color:#999;">无定金</span> </c:if></td>
              <td align="center" nowrap="nowrap"><!--<span style="cursor:pointer;" class="fblue" onclick="$('#message_tip').show();do_js('${cur.sell_bill_id}','');">快速结算</span> | --><span style="cursor:pointer;" class="fblue" onclick="$('#message_tip').show();doNeedMethod(null, 'KonkaXxSellJs.do', 'edit', 'sell_bill_id=${cur.sell_bill_id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize());">确认佣金</span> | <span style="cursor:pointer;" class="fblue" onclick="$('#message_tip').show();doNeedMethod(null, 'KonkaXxZmdAddSalesOrder.do', 'view', 'sell_bill_id=${cur.sell_bill_id}&mod_id=802001');">查看</span></td>
            </tr>
          </c:forEach>
          <c:forEach begin="${fn:length(entityList)}" end="${af.map.pager.pageSize - 1}">
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
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxSellJs.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td><input name="button" type="button" class="but_update" value=" 导出数据 " onclick="jsDownload();" /></td>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("zmd_sn", "${af.map.zmd_sn}");
				pager.addHiddenInputs("sell_date_begin", "${af.map.sell_date_begin}");
				pager.addHiddenInputs("sell_date_end", "${af.map.sell_date_end}");
				pager.addHiddenInputs("sell_bill_id", "${af.map.sell_bill_id}");
				document.write(pager.toString());
	      </script></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<!-- 弹出结算窗口 START... -->
<div id="div_js" style="display: none;" class="rtabcont1">
  <form action="KonkaXxSellJs.do" method="post" id="js_form">
    <input type="hidden" name="method" value="save_js" />
    <input type="hidden" name="mod_id"value="${af.map.mod_id}" />
    <input type="hidden" name="queryString" id="queryString" value="" />
    <!-- 辅助提交数据 数据格式 : sell_bill_id,sell_bill_details_id,zmd_fee,formula#sell_bill_id,sell_bill_details_id,zmd_fee,formula#.... -->
    <input type="hidden" name="form_values" id="form_values" value="" />
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="rtable2">
      <tr class="tabtt1">
        <td width="10%" nowrap="nowrap" align="center">品类</td>
        <td width="10%" nowrap="nowrap" align="center">型号</td>
        <td width="10%" nowrap="nowrap" align="center">返佣类型</td>
        <td width="8%" nowrap="nowrap" align="center">数量</td>
        <td width="8%" nowrap="nowrap" align="center">单价</td>
        <td width="8%" nowrap="nowrap" align="center">佣金</td>
        <td width="8%" nowrap="nowrap" align="center">调整</td>
        <td width="38%" nowrap="nowrap" align="center">结算公式</td>
      </tr>
      <tbody id="details_td">
      </tbody>
      <tr>
        <td align="right" nowrap="nowrap" style="font-weight:bold;color:#f00;font-size:14px;height:30px;">合计佣金</td>
        <td align="right" nowrap="nowrap" colspan="7" id="total_reward" style="font-weight:bold;color:#f00;font-size:14px;" title="0">&nbsp;</td>
      </tr>
    </table>
  </form>
</div>
<!-- 弹出结算窗口 END. -->
<!-- Ajax 提交 覆盖层  -->
<div style="display:none;top:20%;left:20%;background:#fff;font-size:12px;z-index:999999;" id="ajax_view">
  <table border="0" cellspacing="0" cellpadding="0" >
    <tr>
      <td><img src="${ctx}/images/ajax-loader.gif" /></td>
    </tr>
  </table>
</div>
<div id="message_tip" style="display:none;z-index:888888888;">
  <div class="ui-overlay">
    <div class="ui-widget-overlay"></div>
    <div class="ui-widget-shadow ui-corner-all" style="width:302px;height:152px;position:absolute;left:35%;top:25%"></div>
  </div>
  <div style="position:absolute;width:280px;height:130px;left:35%;top:25%;padding:10px;" class="ui-widget ui-widget-content ui-corner-all"> <span><img id="loading" src="${ctx}/images/loading.gif" />正在查询，请稍等...</span> </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/lightBox.js"></script>
<script type="text/javascript">//<![CDATA[
 // AJAX覆盖层
 var ajax_view = new LightBox("ajax_view");
 ajax_view.Over = true;  //是否启用覆盖层  :true 、 false;
 ajax_view.OverLay.Color = "#000"; //覆盖层颜色 ，必须启用覆盖层才有作用
 ajax_view.OverLay.Opacity = 10; //覆盖层透明度 
 ajax_view.Fixed = true; // 是否定位
 ajax_view.Center = true; //是否居中

$(document).ready(function(){
	// 日期控件
	$("#sell_date_begin").datepicker();
	$("#sell_date_end").datepicker();
	$("#sell_bill_id").focus(setOnlyNum);
});

function do_js(sell_bill_id, sell_bill_id_pks){
	$("#details_td").empty(); // 清除数据,防止在同一页面中多次查看BUG.
	
	$.ajax({
		type: "POST",
		url: "${ctx}/manager/zmd/KonkaXxSellJs.do",
		data: "method=ajaxGetReward&sell_bill_id=" + sell_bill_id + "&sell_bill_id_pks=" + sell_bill_id_pks,
		dataType: "json",
		async: true, // false 为同步，true为异步
		error: function(request, settings) {alert("请求失败，请您检查数据并重新请求！");},
		success: function(data) {
			if(data.result == 0){ // 查询出错
			  $("#message_tip").hide(); // 关闭覆盖层
			  alert(data.msg);
			  return;
			}

			// 合计佣金
			$("#total_reward").html("<img src=\"${ctx}/images/yen.png\" alt=\"￥\" title=\"RMB\" /><span title=\"" + data.total_reward +"\" id=\"temp_total_reward\">" + data.total_reward + "</span>");
						
			$("#form_values").val(""); //清空数据
			var details = data.details;
			for(var i = 0; i < details.length; i++){
				// 拼接表格页面：品类，型号，返佣类型，数量，单价，佣金，调整，结算公式
				$("#details_td").append("<tr><td>" + details[i].pd_cls_name 
										  + "</td><td>" + details[i].md_name 
										  + "</td><td>" + details[i].pd_type 
										  + "</td><td align=\"right\" id=\"counts_" + details[i].sell_bill_details_id + "\">" + details[i].counts 
										  + "</td><td align=\"right\" id=\"price_" + details[i].sell_bill_details_id + "\">" + details[i].price 
										  + "</td><td align=\"right\" id=\"zmd_fee_" + details[i].sell_bill_details_id + "\" title=\"" + details[i].zmd_fee + "\">" + details[i].zmd_fee 
										  + "</td><td><input type=\"text\" name=\"tzjs_" + details[i].sell_bill_details_id +"\" value=\"0\" class=\"tzjs\" maxlength=\"8\" size=\"1\" />" 
										  + "</td><td>" + details[i].formula 
										  + "</td></tr>");

				//辅助提交数据 数据格式 : sell_bill_id,sell_bill_details_id,zmd_fee,formula#sell_bill_id,sell_bill_details_id,zmd_fee,formula#.... 
				if(i == (details.length - 1)){
					$("#form_values").val($("#form_values").val() + details[i].sell_bill_id + "," + details[i].sell_bill_details_id + "," + details[i].zmd_fee + "," + details[i].formula);
				} else {
					$("#form_values").val($("#form_values").val() + details[i].sell_bill_id + "," + details[i].sell_bill_details_id + "," + details[i].zmd_fee + "," + details[i].formula + "#");
				}
		    }
		    
			//alert($("#form_values").val());
			
			// 设置调整数据只能输入数字
			$(".tzjs").focus(setOnlyNumForTz);
			
			$("#div_js").dialog({
			      title: '专卖店销售单确认佣金', 
			      width: 750,
			      height: 350,
			      draggable: true, //是否可以使用标题头进行拖动
			      resizable: false, //是否可以改变dialog的大小
			      position:'center', //dialog的显示位置
			      modal : true, //是否使用模式窗口，模式窗口打开后，页面其他元素将不能点击，直到关闭模式窗口
			      buttons: { "结算": js_form_sbumit , "关闭": function(){$(this).dialog("close");}} 
			}).dialog("open");	

			$("#message_tip").hide();
		}
	});
}


// 开始返佣处理
function js_form_sbumit(){
	$("#div_js").dialog("close");
	ajax_view.Show(); // 启动覆盖层
	
	$("#queryString").val($('#bottomPageForm').serialize());
	$("#js_form").submit();
}

// 多数据处理
function confirmJs(form){
	var sell_bill_pks = "";
	$(".sell_bill_pks").each(function (){
		if(this.checked)
			sell_bill_pks = sell_bill_pks + $(this).val() + ",";
	});
	
	if(sell_bill_pks == 0){
		$("#message_tip").hide();
		alert("请至少选择一个结算项！");
		return;
	}
	 
	do_js("", sell_bill_pks.substring(0, sell_bill_pks.length -1));
}

// 导出数据
function jsDownload(form){
	window.open("KonkaXxSellJs.do?method=exportData");
}

function setOnlyNumForTz() {
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/)){
			this.value=this.t_value;
		} else { 
			this.t_value=this.value;
		}
		if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/)){
		   this.o_value=this.value;
		}
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/)){
			this.value=this.t_value;
		}else{ 
			this.t_value=this.value;
		}
		if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/)){
			this.o_value=this.value;
		}
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))
			this.value=this.o_value;
		else{
			if(this.value.match(/^\.\d+$/))
				this.value=0+this.value;
			if(this.value.match(/^\.$/))
				this.value=0;
			this.o_value=this.value;
		}
		if(this.value.length == 0) 
			this.value = "0";


	   // 判断结算最后的光标移去佣金计算
	   var id = this.name.split("_")[1];
	   var zmd_fee = parseFloat(this.value) +  parseFloat($("#zmd_fee_" + id).attr("title"));
	   if(zmd_fee < 0){
		 alert("调整佣金结果不能小于0，请重新输入！");
		 this.value = "0";
		 return ;			
	   }
	   var total_price = parseFloat($("#counts_" + id).html()) * parseFloat($("#price_" + id).html());
	   if(total_price < zmd_fee){
		   alert("调整佣金结果不能大于销售额，请重新输入！");
		   this.value = "0";
		   return ;
	   }
	   
	   $("#zmd_fee_" + id).html(changeTwoDecimal_f(zmd_fee));
	   

	   // 计算最后的总佣金
	   var total_reward_value = 0;
	   $(".tzjs").each(function (){
		   total_reward_value = total_reward_value + parseFloat($(this).attr("value"));
		});
	   $("#temp_total_reward").html(changeTwoDecimal_f(parseFloat(total_reward_value) +  parseFloat($("#temp_total_reward").attr("title"))));
	});
}

function changeTwoDecimal_f(x){
	var f_x = parseFloat(x);
	if (isNaN(f_x)){
		alert('function:changeTwoDecimal->parameter error');
		return false;
	}
	var f_x = Math.round(x*100)/100;
	var s_x = f_x.toString();
	var pos_decimal = s_x.indexOf('.');
	if (pos_decimal < 0){
		pos_decimal = s_x.length;
		s_x += '.';
	}
	while (s_x.length <= pos_decimal + 2){
		s_x += '0';
	}
	return s_x;
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