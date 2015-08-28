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
      <html-el:hidden property="method" value="list" />
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
              <tr>
                <td width="15%" align="left" nowrap="nowrap" style="padding:2px">&nbsp;&nbsp;<strong class="fb">产品类别：</strong></td>
                <td width="35%" align="left" nowrap="nowrap" style="padding:2px"><html-el:text property="pd_cls_name_like" styleId="pd_cls_name_like" size="20" maxlength="12" ></html-el:text></td>
                <td width="15%" align="left" nowrap="nowrap" style="padding:2px"><strong class="fb">商品型号：</strong></td>
                <td width="35%" align="left" nowrap="nowrap" style="padding:2px"><html-el:text property="md_name_like" styleId="md_name_like" size="20" maxlength="12" ></html-el:text></td>
              </tr>
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
                <td width="15%" align="left" nowrap="nowrap" style="padding:0px"><strong class="fb">发货状态：</strong></td>
                <td width="35%" align="left" nowrap="nowrap" style="padding:0px"><html-el:select property="lock_stock_state" styleId="lock_stock_state" onchange="this.form.submit();">
                    <html-el:option value="">==请选择==</html-el:option>
                    <html-el:option value="1">未发货</html-el:option>
                    <html-el:option value="2">已发货</html-el:option>
                  </html-el:select>
                  &nbsp;&nbsp;
                  <input class="but1" type="submit" name="Submit" value="搜索" /></td>
              </tr>
            </table></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
  <div style="overflow-x: auto">
    <table id="trMergeTable_3" width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2" >
      <tr class="tabtt1">
        <td width="10%" nowrap="nowrap" align="center">订单流水号</td>
        <td nowrap="nowrap" align="center">专卖店编号</td>
        <td width="7%" nowrap="nowrap" align="center">型号名称</td>
        <td width="6%" nowrap="nowrap" align="center">产品类别</td>
        <td width="8%" nowrap="nowrap" align="center">单价</td>
        <td width="5%" nowrap="nowrap" align="center">数量</td>
        <td width="7%" nowrap="nowrap" align="center">收货人</td>
        <td width="8%" nowrap="nowrap" align="center">收货人电话</td>
        <td width="10%" nowrap="nowrap" align="center">开单时间</td>
        <td width="5%" nowrap="nowrap" align="center">订单状态</td>
        <td colspan="2" width="8%" nowrap="nowrap" align="center">操作</td>
      </tr>
      <c:forEach var="cur" items="${list}" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap" class="sell_bill_id"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxZmdAddSalesOrder.do', 'view', 'sell_bill_id=${cur.sell_bill_id}&mod_id=802001');$('#message_tip').show();">${fnx:leftPad_sis(cur.sell_bill_id, 12, '0')}</span></td>
          <td align="left" nowrap="nowrap" class="zmd_sn">${cur.map.zmd_sn}</td>
          <td align="left" nowrap="nowrap">${cur.md_name}</td>
          <td align="center" nowrap="nowrap">${cur.pd_cls_name}</td>
          <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.price}" type="currency" />
            </span></td>
          <td align="right" nowrap="nowrap">${cur.counts}</td>
          <td align="center" nowrap="nowrap" class="buyer_name">${cur.map.sell_rec_name}</td>
          <td align="center" nowrap="nowrap" class="buyer_name">${cur.map.sell_rec_link_mp}</td>
          <td align="center" nowrap="nowrap" class="add_date">${cur.map.add_date}</td>
          <td align="center" nowrap="nowrap" class="state_${cur.sell_bill_id}"><c:choose>
              <c:when test="${cur.map.sell_state eq 20}"><span style="color:#ff0000;">待发货</span></c:when>
              <c:when test="${cur.map.sell_state eq 30}"><span style="color:green;">已发货</span></c:when>
              <c:otherwise>未知状态</c:otherwise>
            </c:choose></td>
          <!-- 发货操作Begin -->
          <td align="center" nowrap="nowrap"><!-- 商品已锁定 -->
            <c:if test="${cur.lock_stock_state ne 2}">
              <!-- 审核通过 -->
              <c:if test="${cur.map.sell_state eq 20}"> <span style="color:#ff0000;">
                <!--		              	<span style="cursor:pointer;" class="fblue" onclick="openDeliveryDiv('${fnx:leftPad_sis(cur.sell_bill_id, 12, '0')}', '${cur.sell_bill_details_id}', ${cur.map.zmd_id}, '${cur.md_name}', '${cur.map.zmd_sn}', ${cur.counts}, '${cur.pd_cls_name}', '${cur.map.fee_of_post}')">发货</span>-->
                <!--							<span style="cursor:pointer;" class="fblue" onclick="location.href='KonkaXxZmdSalesOrderDelivery.do?method=saveDelivery&sell_bill_id=${cur.sell_bill_id}&sell_bill_details_id=${cur.sell_bill_details_id}&mod_id=${af.map.mod_id}'">发货</span>-->
                <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('确定发货？', 'KonkaXxZmdSalesOrderDelivery.do', 'saveDelivery', 'sell_bill_id=${cur.sell_bill_id}&sell_bill_details_id=${cur.sell_bill_details_id}&mod_id=${af.map.mod_id}');">发货</span> </span> </c:if>
            </c:if>
            <c:if test="${cur.lock_stock_state eq 2}"><span style="color:#CCC;">发货</span></c:if></td>
          <!-- 发货操作End -->
        </tr>
        <c:if test="${vs.last eq true}">
          <c:set var="i" value="${vs.count}" />
        </c:if>
      </c:forEach>
      <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
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
        </tr>
      </c:forEach>
    </table>
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
			            pager.addHiddenInputs("method", "list");
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
      </table>
    </form>
    </div>
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
<div id="newDeliveryDiv" style="display:none;">
  <html-el:form action="/zmd/KonkaXxZmdSalesOrderDelivery">
    <html-el:hidden property="method" value="save" />
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
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script>
<script type="text/javascript"><!--//<![CDATA[
$(document).ready(function(){
	$("#add_date_ge").datepicker();
	$("#add_date_le").datepicker();

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

	trMerge("zmd_sn", $("#trMergeTable_3"));
	trMerge("sell_bill_id", $("#trMergeTable_3"));
	trMerge("add_date", $("#trMergeTable_3"));

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

function trMerge1(para, parentObj) {
	var that;
	$("." + para, parentObj).each(function(){
		if ((that != undefined) && ($(this).attr("class") == $(that).attr("class"))) {
			rowspan = $(that).attr("rowSpan");
			if (rowspan == undefined) {
				$(that).attr("rowSpan", 1);   
				rowspan = $(that).attr("rowSpan");   
			}
			rowspan = Number(rowspan) + 1;
			$(that).attr("rowSpan", rowspan); // do your action for the colspan cell here
			$(this).remove(); // .remove(); // do your action for the old cell here
	    } else {
			that = this;
	    }
	});
}

function openDeliveryDiv(sell_bill_id, sell_bill_details_id, zmd_id, md_name, zmd_sn, cus_counts, pd_cls_name, fee_of_post){
	if ("" == fee_of_post) {
		alert("对不起，订单流水号为〖" + sell_bill_id + "〗的订单，您还未确认物流费用！");
		return false;
	}
	$("#tbody_div").empty();
	//alert(sell_bill_id + "__" + sell_bill_details_id + "__" + zmd_id + "__" + md_name + "__" + zmd_sn + "__" + cus_counts + "__" + pd_cls_name);
	
	$("#dts_sell_bill_id").val(sell_bill_id);
	$("#dts_sell_bill_details_id").val(sell_bill_details_id);
	$("#dts_pd_cls_name").val(pd_cls_name);
	$("#dts_md_name").val(md_name);

	$("#newDeliveryDiv").dialog({
	      title: '销售单发货', 
	      width: 600,
	      height: 350,
	      draggable: true,
	      resizable: false,
	      position:'center',
	      modal : true,
	      buttons: {"确认发货": function() {
	      				var dts_num = 0;
						$(".dts_counts").each(function(){
							var dts_n = parseInt($(this).val(), 10);
							if (isNaN(dts_n)) {
								dts_n = 0;
							}
							dts_num += parseInt(dts_n, 10);
						});
						
						if (dts_num== 0) {
							alert("请至少选择一个仓库出货！");
							return false;
						} else if (dts_num < cus_counts) {
							alert("您出货的数量小于客户购买量！");
							return false;
						} else if (dts_num > cus_counts) {
							alert("您出货的数量大于客户购买量！");
							return false;
						}
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
						$("#fix_fee").removeAttr("dataType");
						$("#dts_sell_bill_id").val("");
						$("#dts_sell_bill_details_id").val("");
						$("#dts_pd_cls_name").val("");
						$("#dts_md_name").val("");
		      			$(this).dialog("close");
		      		}
		  } 
	}).dialog("open");

	$("#loading").ajaxStart( function (){$( this ).show();}); 
    $("#loading").ajaxStop ( function (){$( this ).hide();});
    $.ajax({
    	type: "POST" , 
        url: 'KonkaXxZmdSalesOrderDelivery.do' , 
        data: "method=getPdStoresList&sell_bill_id=" + sell_bill_id + "&sell_bill_details_id=" + sell_bill_details_id + "&zmd_id=" + zmd_id + "&md_name=" + md_name + "&n=" + Math.random(), 
        dataType: "json" , 
        //async: false, 
        error: function (request, settings) {alert(" 数据加载请求失败！ "); }, 
        success: function (result) {
			$("#tbody_div").append('<div class="rtabcont1" align="left">'+
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
		        								    			'</td width="35%" align="left">' +
		        								    			'<td>' + sell_bill_id + '</td>' +
					        						        '</tr>' +
						        						    '<tr>' +
							        						    '<td width="15%" class="title_item" nowrap="nowrap" align="right">' +
		        								    				'<strong class="fb">商品型号：</strong>' +
		        								    			'</td>' +
		        								    			'<td width="35%" align="left"><span class="fblue">' + md_name + '</span></td>' +
		        								    			'<td width="15%" class="title_item" nowrap="nowrap" align="right">' +
		        								    				'<strong class="fb">客户购买数量：</strong>' +
		        								    			'</td>' +
		        								    			'<td>' + cus_counts + '</td>' +
					        						        '</tr>' +
					        						        '<tr>' +
							        						    '<td width="15%" class="title_item" nowrap="nowrap" align="right">' +
		        								    				'<strong class="fb">安装费：</strong>' +
		        								    			'</td>' +
		        								    			'<td align="left" colspan="3"><input type="text" name="fix_fee" id="fix_fee" size="5" maxlength="10" value="0" /><img src="${ctx}/images/yen.png" alt="￥" title="RMB" /></td>' +
					        						        '</tr>' +
					        						    '</table>' +
					        						//'</div>' +  
		        								'</td>' +
		        							'</tr>' +
		        						'</table>'+
								  		'<table width="95%" border="0" cellspacing="0" cellpadding="0" class="rtable2" style="margin-top:5px;">' +
									        '<tr class="tabtt1">' +
									          '<td width="3%" align="center" nowrap="nowrap">序号</td>' +
									          '<td width="15%" align="center" nowrap="nowrap">工厂</td>' +
									          '<td width="15%" align="center" nowrap="nowrap">仓位编号</td>' +
									          '<td width="15%" align="center" nowrap="nowrap">仓位名称</td>' +
									          '<td width="15%" align="center" nowrap="nowrap">库存</td>' +
									          '<td width="7%" align="center" nowrap="nowrap">出库数量</td>' +
									          '<td width="30%" align="center" nowrap="nowrap">出库备注</td>' +
									        '</tr>' +
						                    '<tbody id="stores_tbody"></tbody>' +
	                 					'</table>' +
	                 			   '</div>');
			if (result[0].count == 0) {
	              $("#stores_tbody").append('<tr>' +
		                                       '<td colspan="9" style="color:red" align="center">没有查到相关仓库信息</td>' +
		                                   '</tr>');
	        } else {
				for ( var i in result[0].list) {
					
					var factory_id;//工厂
	                if (result[0].list[i].factory_id == "null") {
	                  factory_id = "";
	                } else {
	                  factory_id = result[0].list[i].factory_id;
	                }
	                factory_id = factory_id.toString();
	                
	                var store_id;//仓库
	                if (result[0].list[i].store_id == "null") {
	                	store_id = "";
	                } else {
	                	store_id = result[0].list[i].store_id;
	                }

	                var storeNum;//仓位编号
	                if (result[0].list[i].storeNum == "null") {
	                	storeNum = "";
	                } else {
	                	storeNum = result[0].list[i].storeNum;
	                }

	                var dstCount;//产品出库数量
	                if (result[0].list[i].dstCount == "null") {
	                	dstCount = 0;
					} else {
						dstCount = result[0].list[i].dstCount;
					}
	                
	                var store_desc;//仓位名称
	                if (result[0].list[i].store_desc == "null") {
	                	store_desc = "";
	                } else {
	                	store_desc = result[0].list[i].store_desc;
	                }
						
					$("#stores_tbody").append('<tr>' +
					                            '<td align="center" nowrap="nowrap">' + result[0].list[i].vs + '</td>' +
					                            '<td align="left" nowrap="nowrap"><input type="hidden" id="factory_id" name="factory_id" value="' + factory_id + '" />' + factory_id + '</td>' +
					                            '<td align="left" nowrap="nowrap"><input type="hidden" id="store_id" name="store_id" value="' + store_id + '" />' + store_id + '</td>' +
					                            '<td align="left" nowrap="nowrap"><input type="hidden" id="store_desc" name="store_desc" value="' + store_desc + '" />' + store_desc + '</td>' +
					                            '<td align="left" nowrap="nowrap">' + storeNum + '</td>' +
					                            '<td align="left" nowrap="nowrap"><input type="text" name="dts_counts" id="dts_counts" class="dts_counts" size="5" maxlength="10" value="' + dstCount + '" /></td>' +
					                            '<td align="left" nowrap="nowrap"><input type="text" name="dst_remarks" id="dst_remarks" size="20" maxlength="40" /></td>' +
					                          '</tr>');
				}
				$("#fix_fee").val(result[0].fix_fee).focus(setOnlyNum1).attr("dataType", "Require").attr("msg", "请填写安装费用！");
				
				$(".dts_counts").each(function(){
					$(this).focus(setOnlyNum).keyup(function(){
						var thisDtsCounts = parseInt($(this).val(), 10);
						if (isNaN(thisDtsCounts)) {
							thisDtsCounts = 0;
							$(this).val(thisDtsCounts);//Chrome error
						}
						if (thisDtsCounts > cus_counts) {
							alert("您输入的出货数量已超过〖该客户购买数量〗！");
							$(this).val(0);
							return false;
						}
						var thisStoreNum = parseInt($(this).parent().prev().text(), 10);
						if (isNaN(thisStoreNum)) {
							thisStoreNum = 0;
						}
						if (thisDtsCounts > thisStoreNum) {
							alert("您输入的出货数量已超过〖该仓库最大库存〗！");
							$(this).val(0);
							return false;
						}
					});
				});
		    }

				
        }
    });
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

function setOnlyNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	var regInteger = /^[-\+]?\d+$/;//整数
	$(this).keypress(function (){
		if(!this.value.match(regInteger))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(regInteger))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(regInteger))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(regInteger))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(regInteger))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		if(this.value.length == 0) this.value = "0";
	});
}

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
		//if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;
}
//]]>--></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
