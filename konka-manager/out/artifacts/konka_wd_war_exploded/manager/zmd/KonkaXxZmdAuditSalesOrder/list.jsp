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
<link href="${ctx}/styles/jGrowl.css" rel="stylesheet" type="text/css" />
</head>
<body>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<div class="oarcont">
  <div class="oartop">
    <table id="tab_1" width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td id="ceshi">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
  <html-el:form action="/zmd/KonkaXxZmdAuditSalesOrder">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table id="tab_2" width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td nowrap="nowrap" style="padding:2px;">
          	<table width="80%" border="0" cellpadding="0" cellspacing="0">
          		<tr>
          			<td width="15%" align="left" nowrap="nowrap" style="padding:2px;">&nbsp;&nbsp;<strong class="fb">专卖店编号：</strong></td>
          			<td width="35%" align="left" nowrap="nowrap" style="padding:2px;">
          				<!--<html-el:text property="zmd_sn" styleId="zmd_sn" size="20" maxlength="20" ></html-el:text>-->
          				<html-el:select property="zmd_id" styleId="zmd_id" multiple="multiple">
		            		<html-el:option value="">=请选择=</html-el:option>
		            		<c:forEach items="${zmdList}" var="cur">
		            			<html-el:option value="${cur.zmd_id}">${cur.zmd_sn}</html-el:option>
		            		</c:forEach>
		            	</html-el:select>
          			</td>
          			<td width="15%" align="left" nowrap="nowrap" style="padding:2px;"><strong class="fb">订单流水号：</strong></td>
          			<td width="35%" align="left" nowrap="nowrap" style="padding:2px;"><html-el:text property="sell_bill_id" styleId="sell_bill_id" size="20" maxlength="12" ></html-el:text></td>
          		</tr>
          		<tr>
          			<td width="15%" align="left" nowrap="nowrap" style="padding:2px;">&nbsp;&nbsp;<strong class="fb">开单日期：</strong></td>
          			<td width="35%" align="left" nowrap="nowrap" style="padding:2px;">
          				<html-el:text property="add_date_ge" styleId="add_date_ge" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
          				至
          				<html-el:text property="add_date_le" styleId="add_date_le" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
          			</td>
          			<td width="15%" align="left" nowrap="nowrap" style="padding:2px;"><strong class="fb">销售单状态：</strong></td>
          			<td width="35%" align="left" nowrap="nowrap" style="padding:2px;">
          				<html-el:select property="sell_state" styleId="sell_state">
			           		<html-el:option value="10">已付款未审核</html-el:option>
			           		<html-el:option value="20">已审核通过</html-el:option>
			           		<html-el:option value="21">已审核不通过</html-el:option>
			          	</html-el:select>
          			</td>
          		</tr>
          		<tr>
          			<td width="15%" align="left" nowrap="nowrap" style="padding:2px;">&nbsp;&nbsp;<strong class="fb">开单人姓名：</strong></td>
          			<td width="35%" align="left" nowrap="nowrap" style="padding:2px;"><html-el:text property="add_user_realname_like" styleId="add_user_realname_like" size="20" maxlength="20" ></html-el:text></td>
          			<td width="15%" align="left" nowrap="nowrap" style="padding:2px;"><strong class="fb">消费者姓名：</strong></td>
          			<td width="35%" align="left" nowrap="nowrap" style="padding:2px;"><html-el:text property="buyer_name_like" styleId="buyer_name_like" size="20" maxlength="20" ></html-el:text></td>
          		</tr>
          		<tr>
          			<td width="15%" align="left" nowrap="nowrap" style="padding:2px;">&nbsp;&nbsp;<strong class="fb">付款方式：</strong></td>
          			<td width="35%" align="left" nowrap="nowrap" style="padding:2px;">
          				<html-el:select property="pay_way" styleId="pay_way">
              				<html-el:option value="">==请选择==</html-el:option>
			              <html-el:option value="1">现金支付</html-el:option>
			              <html-el:option value="2">POS机刷卡</html-el:option>
			              <html-el:option value="3">货到付款</html-el:option>
          				</html-el:select>
          				&nbsp;&nbsp;<input class="but1" type="submit" name="Submit" value="搜索" />
          			</td>
          			<td width="15%" align="left" nowrap="nowrap" style="padding:2px;"></td>
          			<td style="padding:2px;"></td>
          		</tr>
          	</table>
          </td>
        </tr>
      </table>
    </html-el:form>
   </div>
   <div class="rtabcont1">
   	  <form id="listForm" name="listForm" method="post" action="KonkaXxZmdAuditSalesOrder.do">
   	  	 <!--<div style="text-align:left;margin-left:5px;margin-top:-5px;margin-bottom:5px;">
   	  	 	<input type="button" id="batchAudit" class="but8" value="批量审核" />
   	  	 </div>-->
   	  	 <table id="tab_3" width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
	        <tr class="tabtt1">
	          <!--<td width="3%" nowrap="nowrap" align="center"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>-->
	          <td width="10%" nowrap="nowrap" align="center">专卖店编号</td>
	          <td width="10%" nowrap="nowrap" align="center">订单流水号</td>
	          <td width="10%" nowrap="nowrap" align="center">开单时间</td>
	          <td width="15%" nowrap="nowrap" align="center">开单人姓名</td>
	          <td width="10%" nowrap="nowrap" align="center">消费者姓名</td>
	          <td width="10%" nowrap="nowrap" align="center">付款方式</td>
	          <td width="10%" nowrap="nowrap" align="center">销售单状态</td>
	          <td width="10%" nowrap="nowrap" align="center">操作</td>
	        </tr>
	          <c:forEach var="cur" items="${entityList}" varStatus="vs">
	            <tr>
	              <!--<td align="center">
	              	<c:if test="${cur.sell_state ne 20}">
	                	<input name="pks" type="checkbox" class="pks" id="pks" value="${cur.sell_bill_id}" />
		            </c:if>
	              	<c:if test="${cur.sell_state eq 20}">
	                	<input name="pks" type="checkbox" class="pks" id="pks" value="${cur.sell_bill_id}" disabled="disabled" />
	              	</c:if>
	              </td>-->
	              <td align="left" nowrap="nowrap"><font class="blue12px">${cur.zmd_sn}</font></td>
	              <td align="center" nowrap="nowrap">
	              		<span class="fblue" style="cursor:pointer;" onclick="doNeedMethod(null, 'KonkaXxZmdAddSalesOrder.do', 'view', 'sell_bill_id=${cur.sell_bill_id}&mod_id=802001');$('#message_tip').show();">${fnx:leftPad_sis(cur.sell_bill_id, 12, '0')}</span>
	              </td>
	              <td align="left" nowrap="nowrap"><font class="blue12px"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></font></td>
	              <td align="left" nowrap="nowrap"><font class="blue12px">${cur.add_user_realname}</font></td>
	              <td align="left" nowrap="nowrap"><font class="blue12px">${cur.buyer_name}</font></td>
	              <td align="left" nowrap="nowrap">
	              	<font class="blue12px">${fn:split(' ,现金付款,POS机刷卡,货到付款', ',')[cur.pay_way]}</font>
	              </td>
	              <td align="left" nowrap="nowrap">
	              	<font class="blue12px">
	              		<c:choose>
				            <c:when test="${cur.sell_state eq 10}">已付款未审核</c:when>
				            <c:when test="${cur.sell_state > 20 && cur.sell_state ne 21}">已审核通过</c:when>
				            <c:when test="${cur.sell_state eq 21}"><span style="color:#FF0000">已审核不通过</span></c:when>
	              			<c:otherwise>*^o^*</c:otherwise>
	              		</c:choose>
	              	</font>
	              </td>
	              <td align="center" nowrap="nowrap">
	              	<!-- 确认物流费用Begin -->
	              	<!--<c:if test="${empty cur.fee_of_post}">
	              	   审核通过 
	              	  <c:if test="${cur.map.sell_state ne 21}">
	              		<span style="color:#ff0000;">
	              			<span style="cursor:pointer;" class="fblue" onclick="openLogisticsDiv('${fnx:leftPad_sis(cur.sell_bill_id, 12, '0')}', ${cur.zmd_id}, '${cur.zmd_sn}', '${cur.buyer_name}', '${cur.buyer_phone}', '${cur.map.full_name}', '${cur.sell_post_addr}', '${cur.map.add_date}')">确认物流</span>
	              		</span>
	              	  </c:if>
	              	   审核不通过 
	              	  <c:if test="${cur.map.sell_state eq 21}">
	              	  	<span style="color:#ccc;">确认物流</span>
	              	  </c:if>
	              	</c:if>-->
	              	<!--<c:if test="${not empty cur.fee_of_post}"><span style="color:#ccc;">确认物流</span></c:if>-->
	              	<!-- 确认物流费用End -->
	              	<c:if test="${cur.sell_state ne 20}">
	              		<!--&nbsp;|&nbsp;<span style="cursor:pointer;" class="fblue" onclick="openAuditDiv(${af.map.mod_id}, '${cur.zmd_sn}', '${fnx:leftPad_sis(cur.sell_bill_id, 12, '0')}', $(this).parent().prev().prev().prev().prev().prev().text(), '${cur.fee_of_post}')">快速审核</span>&nbsp;|&nbsp;--><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxZmdAuditSalesOrder.do', 'Audit','sell_bill_id=${cur.sell_bill_id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize());$('#message_tip').show();">审核</span>
	              	</c:if>
	              	<c:if test="${cur.sell_state eq 20}">
	              		<!--&nbsp;|&nbsp;<span style="color: #ccc;">快速审核</span>&nbsp;|&nbsp;--><span style="color: #ccc;">审核</span>
	              	</c:if>
	              </td>
	            </tr>
	          <c:if test="${vs.last eq true}">
	            <c:set var="i" value="${vs.count}" />
	          </c:if>
	        </c:forEach>
	        <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
	          <tr>
	            <!--<td>&nbsp;</td>-->
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
      <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxZmdAuditSalesOrder.do">
    	<table id="tab_4" width="98%" border="0" cellpadding="0" cellspacing="0">
      		<tr>
        		<td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
	          		<script type="text/javascript">
			            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
			            pager.addHiddenInputs("method", "list");
			            pager.addHiddenInputs("mod_id","${af.map.mod_id}");
						pager.addHiddenInputs("zmd_sn", "${af.map.zmd_sn}");
						 pager.addHiddenInputs("zmd_id", "${af.map.zmd_id}");	
			            pager.addHiddenInputs("sell_bill_id", "${af.map.sell_bill_id}");
			            pager.addHiddenInputs("add_date_ge", "${af.map.add_date_ge}");
			            pager.addHiddenInputs("add_date_le", "${af.map.add_date_le}");
			            pager.addHiddenInputs("sell_state", "${af.map.sell_state}");
						pager.addHiddenInputs("add_user_realname_like", "${fn:escapeXml(af.map.add_user_realname_like)}");
						pager.addHiddenInputs("buyer_name_like", "${fn:escapeXml(af.map.buyer_name_like)}");
						pager.addHiddenInputs("pay_way", "${af.map.pay_way}");
	            		document.write(pager.toString());
	            	</script>
            	</td>
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
    <div style="position:absolute;width:280px;height:130px;left:35%;top:25%;padding:10px;" class="ui-widget ui-widget-content ui-corner-all">
	  <span><img id="loading" src="${ctx}/images/loading.gif" style="display:none;" /><img src="${ctx}/images/search_wangwang.gif" />&nbsp;正在查询，请稍等...</span>
    </div>
  </div>
  <div id="message_tip_2" style="display:none;">
    <div class="ui-overlay">
  	  <div class="ui-widget-overlay"></div>
  	  <div class="ui-widget-shadow ui-corner-all" style="width:302px;height:152px;position:absolute;left:35%;top:30%"></div>
    </div>
    <div style="position:absolute;width:280px;height:130px;left:35%;top:30%;padding:10px;" class="ui-widget ui-widget-content ui-corner-all">
    	<div style="width:95%;margin-bottom:10px;margin-top:10px;font-size:15px;"><img src="${ctx}/styles/message/images/16.gif" />&nbsp;恭喜您，订单<span class="fblue" style="cursor:pointer;">${fnx:leftPad_sis(af.map.printBillId, 12, '0')}</span>成功审核！</div>
    	<button type="button" id="auditClose">关闭</button>
    	<button type="button" id="printDelivery">打印出库单</button>
<!--	  <span><img id="loading" src="${ctx}/images/loading.gif" />正在提交订单，请稍等...</span>-->
    </div>
  </div>
</div>
<div id="plAuditDiv" style="display:none;" title="出库批量处理">
	<html-el:form action="/zmd/KonkaXxZmdAuditSalesOrder" styleClass="form_plAudit">
		<html-el:hidden property="method" value="plAudit" />
		<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
		<html-el:hidden property="queryString" />
		<table id="tab_3" width="95%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
			<tr class="tabtt1">
				<td width="" align="center" nowrap="nowrap">专卖店编号</td>
				<td width="" align="center" nowrap="nowrap">订单流水号</td>
				<td width="" align="center" nowrap="nowrap">开单时间</td>
			</tr>
			<tbody id="salesOrderTbody"></tbody>
		</table>
		<div class="rtabcont1" align="left">
			<table width="95%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
				<tr>
					<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable4">
					    	<tr>
      						    <td width="15%" class="title_item" nowrap="nowrap" align="right">
			    					<strong class="fb">审核状态：</strong>
			    				</td>
			    				<td align="left">
			    					<label for="sell_state1" style="cursor:pointer"><input type="radio" name="sell_state" value="20" id="sell_state1" />审核通过</label>
		  							<label for="sell_state2" style="cursor:pointer"><input type="radio" name="sell_state" value="21" id="sell_state2" />审核不通过</label>
			    				</td>
			    			</tr>
			    			<tr>
			    				<td width="15%" class="title_item" nowrap="nowrap" align="right">
			    					<strong class="fb">审核备注：</strong>
			    				</td>
			    				<td align="left">
			    					<textarea name="cw_remarks" cols="70" rows="5" id="cw_remarks"></textarea>
			    					<div id="info_chat_content"  style="color:#0066FF;font-size:12px;display:none"><img src="../../images/tishi.gif" style="vertical-align:middle;" /></div>
			    				</td>
					        </tr>
			        	</table>
					</td>
				</tr>
			</table>
		</div>
	</html-el:form>
</div>
<div id="newDeliveryDiv" style="display:none;">
	<html-el:form action="/zmd/KonkaXxZmdAuditSalesOrder">
		<html-el:hidden property="method" value="saveLogisticsCosts" />
		<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
		<html-el:hidden property="queryString" />
		<html-el:hidden property="dts_sell_bill_id" styleId="dts_sell_bill_id" />
		<html-el:hidden property="dts_sell_bill_details_id" styleId="dts_sell_bill_details_id" />
		<html-el:hidden property="dts_pd_cls_name" styleId="dts_pd_cls_name" />
		<html-el:hidden property="dts_md_name" styleId="dts_md_name" />
		<html-el:hidden property="account" styleId="account" />
	    <div id="loading" align="left" style="display:none;"><img src="${ctx}/images/loading.gif" style="vertical-align:middle;" />正在查询,请稍等...</div>
	    <!-- 仓库列表 Begin -->
	    <div id="tbody_div"></div>
	    <!-- 仓库列表 End -->
    </html-el:form>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery1.5.1.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.timers.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
//	if ("${sessionScope.roleInfo.role_id}" == "350") {
//		$('body').everyTime('2das','C',function(){
//		    //执行一个会超过20秒以上的程式
//			$("#ceshi").append("测试@");
//			$.ajax({
//		    	type: "POST" , 
//		        url: 'KonkaXxZmdAuditSalesOrder.do' , 
//		        data: "method=getSellBillInfo&dept_id=${sessionScope.userInfo.dept_id}&n=" + Math.random(), //+ "&md_name=" + escape(encodeURIComponent(md_name)) + "&n=" + Math.random(), 
//		        dataType: "json" , 
//		        async: false, 
//		        error: function (request, settings) {alert(" 数据加载请求失败！ "); }, 
//		        success: function (result) {
//
//		        }
//			});
//		},0,true);
//	}
	
	$("#add_date_ge").datepicker();
	$("#add_date_le").datepicker();

	//打印出库单Begin
	if ("" != "${af.map.printBillId}") {
		$("#message_tip_2").show();
	}
	$("#printDelivery").button({icons: {primary: "ui-icon-arrowreturnthick-1-s"}}).click(function(){
		location.href = "KonkaXxZmdOutStoreOrder.do?method=showOutStoreOrder&sell_bill_id=${af.map.printBillId}&mod_id=802007";
	});
	$("#auditClose").button({icons: {primary: "ui-icon-closethick"}}).click(function(){ $("#message_tip_2").hide();});
	//打印出库单End
	
	$("input[type='radio'][name='sell_state']").eq(1).attr("dataType", "Group").attr("msg", "请选择审核状态！");
	//$("#cw_remarks").attr("dataType", "LimitB").attr("max",80).attr("msg", "请填写财务备注！");
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

	//DIV自适应宽度Begin
	var ww = $(".oarcont").width();
	//alert("ww = " + ww);
	var w3 = $("#tab_3").width();
	var w4 = $("#tab_4").width();
	var w = 0;
	for ( var i = 1; i <= 3; i++) {
		var w_t = $("#tab_" + i).width();
		var className = $("#tab_" + i).parent().attr("class");
		if (className == "" || className == undefined) {
			className = $("#tab_" + i).parent().parent().attr("class");
		}
		//alert(className);
		if ("oartop" == className) {
			w_t = w_t + 17;
		} else if ("rtabcont1" == className) {
			w_t = w_t + 7 + 7;
		}
		
		//alert("tab_" + i +" = " + w_t);
		if (w_t > w) {
			w = w_t;
		}
		//alert("w = " + w);
	}
	$("#tab_3").css("width", w3);
	$("#tab_4").css("width", w4);
	$(".oarcont").css("width", w);
	if (ww < w) {
		$("body").css("overflow-x", "scroll");
	}
	//DIV自适应宽度End
	
	if ("" != "${af.map.msg}") {
		$.jGrowl("${af.map.msg}",
				 {theme:'success', 
			 	  life: 4500});
	}

	$("#zmd_id").multiselect({
		noneSelectedText: '=请选择=',
		selectedList: 1,
		multiple: false,
		minWidth:200
	}).multiselectfilter();

	$("#batchAudit").click(function(){
		form = this.form;
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
				//alert(form.pks[i].value);
				<c:forEach items="${entityList}" var="cur" varStatus="vs">
					var sell_bill_id = "${fnx:leftPad_sis(cur.sell_bill_id, 12, '0')}";
					var fee_of_post = "${cur.fee_of_post}";
					if (form.pks[i].value == parseInt(sell_bill_id, 10)) {
						if ("" == fee_of_post) {
							alert("对不起，订单流水号为【" + sell_bill_id + "】的订单，您还未确认物流费用！");
							return false;
						}
					}
				</c:forEach>
				checkedCount++;
			}
		}
		if (checkedCount == 0) {
			alert("请选择要批量审核的销售单！");
			return false;
		} else {
			$("#plAuditDiv").dialog({
			      title: '批量审核', 
			      width: 600,
			      height: 350,
			      draggable: true,
			      position:'center',
			      resizable: false,
			      modal : true,
			      buttons: {"确认": function() {
										var f = document.forms[3];
										if(Validator.Validate(f, 1)){
											if(confirm("确定要批量审核？")){
												f.submit();
											} else {
												return false;
											}
										}
									},
							"关闭": function() {$(this).dialog("close");}
						   }
			}).dialog("open");

			$("#salesOrderTbody").empty();
			var num = $(".pks").length;
			if (num == 1 || num == "1") {
				$("#salesOrderTbody").append(
					'<tr>' +
						'<td>' + $("#pks").parent().next().text() + '</td>' + 
						'<td>' + $("#pks").parent().next().next().text() + '</td>' + 
						'<td>' + $("#pks").parent().next().next().next().text() + '</td>' +
						'<input type="hidden" name="pkss" value="' + form.pks.value + '" />' +
					'</tr>'
				);
			} else {
				for ( var i = 0; i < num; i++) {
					if (form.pks[i].checked == true) {
						<c:forEach items="${entityList}" var="cur" varStatus="vs">
							var sell_bill_id = "${fnx:leftPad_sis(cur.sell_bill_id, 12, '0')}";
							var zmd_sn = "${cur.zmd_sn}";
							var add_date = '<fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss" />';
							if (form.pks[i].value == parseInt(sell_bill_id, 10)) {
								$("#salesOrderTbody").append(
									'<tr>' +
										'<td>' + zmd_sn + '</td>' + 
										'<td>' + sell_bill_id + '</td>' + 
										'<td>' + add_date + '</td>' +
										'<input type="hidden" name="pkss" value="' + parseInt(sell_bill_id, 10) + '" />' +
									'</tr>'
								);
							}
						</c:forEach>
					}
				}
			}
		}
	});
});

function openAuditDiv(mod_id, zmd_sn, sell_bill_id, add_date, fee_of_post){
	if ("" == fee_of_post) {
		alert("对不起，订单流水号为【" + sell_bill_id + "】的订单，您还未确认物流费用！");
		return false;
	}
	$("#plAuditDiv").dialog({
	      title: '审核', 
	      width: 600,
	      height: 350,
	      draggable: true,
	      position:'center',
	      resizable: false,
	      modal : true,
	      buttons: {"确认": function() {
								//var f = document.forms[3];
								var f = $(".form_plAudit")[0];
								if(Validator.Validate(f, 1)){
									if(confirm("确定要审核？")){
										f.submit();
									} else {
										return false;
									}
								}
							},
					"关闭": function() {$(this).dialog("close");}
				   }
	}).dialog("open");
	
	$("#salesOrderTbody").empty();
	$("#salesOrderTbody").append(
			'<tr>' +
				'<td>' + zmd_sn + '</td>' + 
				'<td>' + sell_bill_id + '</td>' + 
				'<td>' + add_date + '</td>' +
				'<input type="hidden" name="sell_bill_id_hidden" value="' + parseInt(sell_bill_id, 10) + '" />' +
			'</tr>'
		);
}

function openLogisticsDiv(sell_bill_id, zmd_id, zmd_sn, buyer_name, buyer_phone, full_name, sell_post_addr, add_date){
	$("#tbody_div").empty();
	$("#account").val("1");
	$("#dts_sell_bill_id").val(sell_bill_id);
	//alert(sell_bill_id + "___" + zmd_id + "___" + zmd_sn + "___" + buyer_name + "___" + buyer_phone + "___" + full_name + "___" + sell_post_addr + "___" + add_date);
	
	$("#newDeliveryDiv").dialog({
	      title: '销售单物流费用结算', 
	      width: 600,
	      height: 330,
	      draggable: true, 
	      resizable: false, 
	      position:'center', 
	      modal : true, 
	      buttons: {"提交": function() {
						var f = document.forms[4];
						if(Validator.Validate(f, 1)){
							if(confirm("确定要提交表单？")){
								//this.form.submit();
								f.submit();
							} else {
								return false;
							}
						}
				   },
				   "关闭": function() {
					   $("#account").val("");
					   $("#dts_sell_bill_id").val("");
					   $("#fee_of_post").remove("dataType");
					   $(this).dialog("close");
				   }
		  } 
	}).dialog("open"); 

	$("#tbody_div").append('<div class="rtabcont1" align="left">'+
							'<div style="width:95%;color:#FF0000;margin-bottom:5px;">注：物流费用请您慎重填写，一旦提交不能修改！</div>' +
					   		'<table width="95%" border="0" cellspacing="5" cellpadding="0" class="rtable1">' +
								'<tr>' +
									'<td>' +
								    //'<div class="rtabcont2">' +
								    	'<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable4">' + 
								    		'<tr>' + 
								    			'<td width="15%" class="title_item" nowrap="nowrap" align="right">' +
								    				'<strong class="fb">专卖店编号：</strong>' +
								    			'</td>' +
								    			'<td width="35%" align="left">' + zmd_sn + '</td>' +
								    			'<td width="15%" class="title_item" nowrap="nowrap" align="right">' +
								    				'<strong class="fb">订单流水号：</strong>' +
								    			'</td>' +
								    			'<td>' + sell_bill_id + '</td>' +
									        '</tr>' +
									        '<tr>' + 
								    			'<td width="15%" class="title_item" nowrap="nowrap" align="right">' +
								    				'<strong class="fb">客户姓名：</strong>' +
								    			'</td>' +
								    			'<td width="35%" align="left">' + buyer_name + '</td>' +
								    			'<td width="15%" class="title_item" nowrap="nowrap" align="right">' +
								    				'<strong class="fb">客户手机：</strong>' +
								    			'</td>' +
								    			'<td>' + buyer_phone + '</td>' +
									        '</tr>' +
									        '<tr>' + 
								    			'<td width="15%" class="title_item" nowrap="nowrap" align="right">' +
								    				'<strong class="fb">送货地区：</strong>' +
								    			'</td>' +
								    			'<td align="left" colspan="3">' + full_name + '</td>' +
								    		'</tr>' +
								    		'<tr>' + 
								    			'<td width="15%" class="title_item" nowrap="nowrap" align="right">' +
								    				'<strong class="fb">送货街道地址：</strong>' +
								    			'</td>' +
								    			'<td align="left" colspan="3">' + sell_post_addr + '</td>' +
								    		'</tr>' +
									        '<tr>' + 
								    			'<td width="15%" class="title_item" nowrap="nowrap" align="right">' +
								    				'<strong class="fb">开单时间：</strong>' +
								    			'</td>' +
								    			'<td align="left" colspan="3">' + add_date + '</td>' +
								    		'</tr>' +
									        '<tr>' +
				    						    '<td width="15%" class="title_item" nowrap="nowrap" align="right">' +
								    				'<strong class="fb">物流费：</strong>' +
								    			'</td>' +
								    			'<td align="left" colspan="3"><input type="text" name="fee_of_post" id="fee_of_post" size="5" maxlength="10" value="0" /><img src="${ctx}/images/yen.png" alt="￥" title="RMB" /></td>' +
									        '</tr>' +
									    '</table>' +
									//'</div>' +  
									'</td>' +
								'</tr>' +
							'</table>');
	$("#fee_of_post").focus(setOnlyNum1).attr("dataType", "Require").attr("msg", "请输入物流费用！");
}
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
