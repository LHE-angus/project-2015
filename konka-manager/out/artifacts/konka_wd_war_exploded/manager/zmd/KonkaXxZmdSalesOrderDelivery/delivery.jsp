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
    <table id="trMergeTable_1" width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/zmd/KonkaXxZmdSalesOrderDelivery">
      <html-el:hidden property="method" value="delivery" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table id="trMergeTable_2" width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td nowrap="nowrap" style="padding:2px;"><table width="80%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="15%" align="left" nowrap="nowrap" style="padding:2px">&nbsp;&nbsp;<strong class="fb">专卖店编号：</strong></td>
                <td width="35%" align="left" nowrap="nowrap" style="padding:2px"><html-el:select property="zmd_id" styleId="zmd_id" multiple="multiple">
                    <html-el:option value="">=请选择=</html-el:option>
                    <c:forEach items="${zmdList}" var="cur">
                      <html-el:option value="${cur.zmd_id}">${cur.zmd_sn}</html-el:option>
                    </c:forEach>
                  </html-el:select></td>
                <td width="15%" align="left" nowrap="nowrap" style="padding:2px"><strong class="fb">订单流水号：</strong></td>
                <td width="35%" align="left" nowrap="nowrap" style="padding:2px"><html-el:text property="sell_bill_id" styleId="sell_bill_id" size="20" maxlength="12" ></html-el:text></td>
              </tr>
              <!--<tr>
          			<td width="15%" align="left" nowrap="nowrap" style="padding:2px">&nbsp;&nbsp;<strong class="fb">产品类别：</strong></td>
          			<td width="35%" align="left" nowrap="nowrap" style="padding:2px"><html-el:text property="pd_cls_name_like" styleId="pd_cls_name_like" size="20" maxlength="12" ></html-el:text></td>
          			<td width="15%" align="left" nowrap="nowrap" style="padding:2px"><strong class="fb">商品型号：</strong></td>
          			<td width="35%" align="left" nowrap="nowrap" style="padding:2px"><html-el:text property="md_name_like" styleId="md_name_like" size="20" maxlength="12" ></html-el:text></td>
          		</tr>-->
              <tr>
                <td width="15%" align="left" nowrap="nowrap" style="padding:2px">&nbsp;&nbsp;<strong class="fb">开单时间：</strong></td>
                <td width="35%" align="left" nowrap="nowrap" style="padding:2px"><html-el:text property="add_date_ge" styleId="add_date_ge" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
                  至
                  <html-el:text property="add_date_le" styleId="add_date_le" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" /></td>
                <td width="15%" align="left" nowrap="nowrap" style="padding:2px"><strong class="fb">客户姓名：</strong></td>
                <td width="35%" align="left" nowrap="nowrap" style="padding:2px"><html-el:text property="buyer_name_like" styleId="buyer_name_like" size="20" maxlength="20" ></html-el:text></td>
              </tr>
              <tr>
                <td width="15%" align="left" nowrap="nowrap" style="padding:2px">&nbsp;&nbsp;<strong class="fb">销售单状态：</strong></td>
                <td width="35%" align="left" nowrap="nowrap" style="padding:2px"><html-el:select property="sell_state" styleId="sell_state" onchange="this.form.submit();">
                    <html-el:option value="">==请选择==</html-el:option>
                    <html-el:option value="20">待发货</html-el:option>
                    <html-el:option value="30">已发货</html-el:option>
                  </html-el:select></td>
                <td width="15%" align="left" nowrap="nowrap" style="padding:0px"><input class="but1" type="submit" name="Submit" value="搜索" /></td>
                <td width="35%" align="left" nowrap="nowrap" style="padding:0px"><!--<html-el:select property="lock_stock_state" styleId="lock_stock_state" onchange="this.form.submit();">
	           				<html-el:option value="">==请选择==</html-el:option>
	           				<html-el:option value="1">未发货</html-el:option>
	           				<html-el:option value="2">已发货</html-el:option>
	        			</html-el:select>-->
                  &nbsp;&nbsp; </td>
              </tr>
            </table></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
  <div style="overflow-x: auto; width: 100%;">
    <table id="trMergeTable_3" width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2" >
      <tr class="tabtt1">
        <td width="10%" nowrap="nowrap" align="center">订单流水号</td>
        <td width="7%" nowrap="nowrap" align="center">专卖店编号</td>
        <td nowrap="nowrap" align="center">产品信息</td>
        <td width="7%" nowrap="nowrap" align="center">收货人</td>
        <td width="8%" nowrap="nowrap" align="center">收货人电话</td>
        <td width="10%" nowrap="nowrap" align="center">开单时间</td>
        <td width="5%" nowrap="nowrap" align="center">订单状态</td>
        <td colspan="2" width="8%" nowrap="nowrap" align="center">操作</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxZmdAddSalesOrder.do', 'view', 'sell_bill_id=${cur.sell_bill_id}&mod_id=802001');$('#message_tip').show();">${fnx:leftPad_sis(cur.sell_bill_id, 12, '0')}</span></td>
          <td align="left" nowrap="nowrap" class="zmd_sn">${cur.zmd_sn}</td>
          <td align="left" nowrap="nowrap" style="padding-bottom:5px;"><table id="fs_${vs.count}" width="98%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
              <tr class="tabtt1" style="background-image:url('${ctx}/images/manager/tabtitbg1.gif') repeat-x;background:#FCFCFC;">
                <!-- <tr class="tabtt1">-->
                <td align="center" nowrap="nowrap">仓位信息</td>
                <td align="center" nowrap="nowrap">产品类型</td>
                <td align="center" nowrap="nowrap">产品型号</td>
                <td align="center" nowrap="nowrap">产品规格</td>
                <td align="center" nowrap="nowrap">数量</td>
                <td align="center" nowrap="nowrap">单价</td>
              </tr>
              <c:forEach items="${cur.map.dstList}" var="cur_" varStatus="v">
                <tr>
                  <td align="left" class="fs"><font class="blue12px">${cur_.factory_id}_${cur_.store_id}_${cur_.store_name}</font></td>
                  <td align="left"><font class="blue12px">${cur_.pd_cls_name}</font></td>
                  <td align="left"><font class="blue12px">${cur_.md_name}</font></td>
                  <td align="left"><font class="blue12px">${cur_.map.spec}</font></td>
                  <td align="left"><font class="blue12px">${cur_.counts}</font></td>
                  <td align="left"><span class="kz-price-12">
                    <fmt:formatNumber value="${cur_.map.price}" type="currency" />
                    </span></td>
                </tr>
              </c:forEach>
            </table></td>
          <td align="left" nowrap="nowrap">${cur.sell_rec_name}</td>
          <td align="left" nowrap="nowrap">${cur.sell_rec_link_mp}</td>
          <td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" />
            <br/>
            <fmt:formatDate value="${cur.add_date}" pattern="HH:mm:ss" /></td>
          <td align="center"><c:if test="${cur.sell_state eq 20}"> <span style="color:red">未发货</span> </c:if>
            <c:if test="${cur.sell_state eq 30}"> <span style="color:green;">已发货</span> </c:if></td>
          <td align="center"><c:if test="${cur.sell_state eq 20}"> <span style="cursor:pointer;" class="fblue" onclick="openDeliveryDiv(${cur.sell_bill_id})">发货</span> </c:if>
            <c:if test="${cur.sell_state eq 30}"> <span style="cursor:pointer;color:#ccc">发货</span> </c:if></td>
        </tr>
      </c:forEach>
    </table>
    </div>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxZmdSalesOrderDelivery.do">
      <table id="trMergeTable_4" width="98%" border="0" cellpadding="0" cellspacing="0">
        <tr>
        	<td colspan="2">注：此列表列出了待发货和已发货的订单列表，单价单位为元，数量单位为台。</td>
        </tr>
        <tr>
          <td></td>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
			            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
			            pager.addHiddenInputs("method", "delivery");
			            pager.addHiddenInputs("mod_id","${af.map.mod_id}");
			            pager.addHiddenInputs("zmd_id", "${af.map.zmd_id}");
						pager.addHiddenInputs("zmd_sn", "${af.map.zmd_sn}");	
			            pager.addHiddenInputs("sell_bill_id", "${af.map.sell_bill_id}");
			            pager.addHiddenInputs("pd_cls_name_like", "${af.map.pd_cls_name_like}");
			            pager.addHiddenInputs("md_name_like", "${af.map.md_name_like}");
			            pager.addHiddenInputs("add_date_ge", "${af.map.add_date_ge}");
			            pager.addHiddenInputs("add_date_le", "${af.map.add_date_le}");
			            pager.addHiddenInputs("buyer_name_like", "${af.map.buyer_name_like}");
			            pager.addHiddenInputs("sell_state", "${af.map.sell_state}");
			            pager.addHiddenInputs("lock_stock_state", "${af.map.lock_stock_state}");
						pager.addHiddenInputs("add_user_realname_like", "${fn:escapeXml(af.map.add_user_realname_like)}");
						pager.addHiddenInputs("buyer_name_like", "${fn:escapeXml(af.map.buyer_name_like)}");
						pager.addHiddenInputs("pay_way", "${af.map.pay_way}");
	            		document.write(pager.toString());
	            	</script></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
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
    <div style="position:absolute;width:280px;height:130px;left:35%;top:25%;padding:10px;" class="ui-widget ui-widget-content ui-corner-all"> <span><img id="loading" src="${ctx}/images/loading.gif" />正在查询，请稍等...</span> </div>
  </div>
</div>
<div id="newDeliveryDiv" style="display:none;overflow:visible">
  <html-el:form action="/zmd/KonkaXxZmdSalesOrderDelivery">
    <html-el:hidden property="method" value="saveDeliveryWithDetails" />
    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
    <html-el:hidden property="queryString" />
    <html-el:hidden property="dist_sell_bill_id" styleId="dist_sell_bill_id" />
    <div id="loading" align="left" style="display:none;"><img src="${ctx}/images/loading.gif" style="vertical-align:middle;" />正在查询,请稍等...</div>
    <div class="rtabcont2" align="left">
      <table id="main_table" width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td width="15%" class="title_item" nowrap="nowrap" align="right"><strong class="fb">送货人员：</strong></td>
          <td align="left"><html-el:select property="dist_employee_id" styleId="dist_employee_id" multiple="multiple">
              <html-el:option value="">=请选择=</html-el:option>
              <c:forEach items="${distEmployeeList}" var="cur">
                <html-el:option value="${cur.dist_employee_id}">${cur.real_name}</html-el:option>
              </c:forEach>
            </html-el:select></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right"><strong class="fb">产品预送时间：</strong></td>
          <td align="left"><html-el:text property="ready_dist_date" styleId="ready_dist_date" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" /></td>
        </tr>
      </table>
    </div>
    <!-- 仓库列表 Begin -->
    <div id="tbody_div"></div>
    <!-- 仓库列表 End -->
  </html-el:form>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery1.5.1.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#add_date_ge").datepicker();
	$("#add_date_le").datepicker();
	$("#ready_dist_date").datepicker();

	if ("" != "${af.map.msg}") {
		$.jGrowl("${af.map.msg}",
				 {theme:'success', 
			 	  life: 4500});
	}

	//合并仓位信息
	var listLength = parseInt("${fn:length(entityList)}", 10);
	for ( var i = 1; i <= listLength ; i++) {
		trMerge("fs", $("#fs_" + i));
	}
	
	$("#zmd_id").multiselect({
		noneSelectedText: '=请选择=',
		selectedList: 1,
		multiple: false,
		minWidth:200
	}).multiselectfilter();

	$("#dist_employee_id").multiselect({
		noneSelectedText: '=请选择=',
		selectedList: 1,
		multiple: false,
		minWidth:160
	}).multiselectfilter();

	//DIV自适应宽度Begin
	var ww = $(".oarcont").width();
	var w3 = $("#trMergeTable_3").width();
	var w4 = $("#trMergeTable_4").width();
	//alert("ww = " + ww);
	var w = 0;
	for ( var i = 1; i <= 3; i++) {
		var w_t = $("#trMergeTable_" + i).width();
		var className = $("#trMergeTable_" + i).parent().attr("class");
		if (className == "" || className == undefined) {
			className = $("#trMergeTable_" + i).parent().parent().attr("class");
		}
		//alert(className);
		if ("oartop" == className) {
			w_t = w_t + 17;
		} else if ("rtabcont1" == className) {
			w_t = w_t + 7 + 7;
		}
		
		//alert("trMergeTable_" + i +" = " + w_t);
		if (w_t > w) {
			w = w_t;
		}
		//alert("w = " + w);
	}
	$("#trMergeTable_3").css("width", w3);
	$("#trMergeTable_4").css("width", w4);
	$(".oarcont").css("width", w);
	if (ww < w) {
		$("body").css("overflow-x", "scroll");
	}
	//DIV自适应宽度End
});

function openDeliveryDiv(sell_bill_id){
	$("#dist_sell_bill_id").val(sell_bill_id);
	
	$("#newDeliveryDiv").dialog({
	      title: '发货', 
	      width: 400,
	      height: 200,
	      draggable: true,
	      resizable: false,
	      position:'center',
	      modal : true,
	      buttons: {"确认": function() {
		 		 			$("#dist_employee_id").attr("dataType", "Require").attr("msg", "请选择送货人员！");;
		 		 			$("#ready_dist_date").attr("dataType", "Require").attr("msg", "请选择产品预送时间！");
		 		 			var f = document.forms[2];
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
							$(this).dialog("close");
						}
		  	   		}
	});
}
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
