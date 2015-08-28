<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/fancybox/fancybox.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<style type="text/css">
select{font-family:Microsoft YAHEI;font-size:12px;}
input{font-family:Microsoft YAHEI;font-size:12px;}
button{height:25px;font-family:Microsoft YAHEI;}
textarea {font-family:Microsoft YAHEI;font-size:12px;}
.error_rz{border:1px solid #FBC2C4;background:#FBE3E4;color:#CD0000;padding-left:5px;height:19px;line-height:19px;}
</style>
</head>
<body >
<div style="width:100%">
	<div class="oartop">
	    <table width="400" border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
	        <td>当前位置：${naviString}</td>
	      </tr>
	    </table>
  	</div>
  	<div class="rtabcont1">
  		<html-el:form action="/manager/KonkaStockInventory" enctype="multipart/form-data">
  			<html-el:hidden property="method" value="save" styleId="method"/>
	      	<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	      	<html-el:hidden property="cust_id" styleId="cust_id" value="${cust_id}" />
		    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
		    	<tr>
		          <td colspan="4" align="center"><span style="font-size: 20 ;font-weight: bold; " >盘点信息</span></td>
		        </tr>
		        <tr>
			          <td class="title_item" width="15%">盘点日期：</td>
			          <td><html-el:text property="opr_date" styleId="opr_date" value="${today}" styleClass="webinput" size="10" readonly="true" style="cursor:pointer;" />
			          <select name="HH">
			          	<c:forEach begin="0" end="23" varStatus="vs">
							<option value="${fnx:leftPad_sis(vs.index, 2, '0')}">${fnx:leftPad_sis(vs.index, 2, '0')}时</option>		          	
			          	</c:forEach>
			          </select>
			          <select name="mm">
			          	<c:forEach begin="0" end="59" varStatus="vs">
							<option value="${fnx:leftPad_sis(vs.index, 2, '0')}">${fnx:leftPad_sis(vs.index, 2, '0')}分</option>		          	
			          	</c:forEach>
			          </select>
			          <span style="color:gray;padding-left: 20">(精确到日期时分)</span>
			          </td>
		        </tr>
		        <tr>
		          <td class="title_item" width="15%">仓库：</td>
		          <td>
		          	<select name="storesList" id="storesList" multiple="multiple">
						<c:forEach items="${jBaseStoreList}" var="cur" varStatus="vs">
							<option value="${cur.store_id}">${cur.store_name}</option>
						</c:forEach>
					</select>
		          </td>
		        </tr>
		        <tr>
		          <td class="title_item" width="15%">商品名称：</td>
		          <td>
		          	<select name="goodsList" id="goodsList" multiple="multiple">
		          		<c:forEach items="${jBaseGoodsList}" var="cur" varStatus="vs">
		          			<option value="${cur.goods_id}">${cur.goods_name}</option>
		          		</c:forEach>
		          	</select>
		          </td>
		        </tr>
		        <!--<tr>
		          <td class="title_item" width="15%">条码：</td>
		          <td></td>
		        </tr>-->
		        <tr>
		          <td class="title_item" width="15%">盘点前：</td>
		          <td><html-el:text property="stocks" styleId="stocks" styleClass="webinput"  size="23" readonly="true" /></td>
		        </tr>
		        <tr>
		          <td class="title_item" width="15%">盘点后：</td>
		          <td><html-el:text property="ver_stocks" styleId="ver_stocks"  size="24" readonly="false"  onfocus="setOnlyInt(this);" maxlength="8"/></td>
		        </tr>
		        <tr>
		          <td class="title_item">结果
		          	<a id="pyAuto" style="display:none;" href="#pyDiv">盘盈div</a>
		          	<a id="pkAuto" style="display:none;" href="#pkDiv">盘亏div</a>
		          </td>
		          <td id="vsResultTd"></td>
		        </tr>
		        <tr>
		          <td class="title_item" width="15%">备注：</td>
		          <td><html-el:textarea property="memo" styleId="memo" styleClass="webinput" cols="60" style="height:60px;" /></td>
		        </tr>
		        <tr>
		          <td colspan="2" align="center">
		          	<input type="button" id="btn_submit" name="save" class="bgButtonSave" value="提 交" />
		            <input type="button" id="btn_back" name="back" class="bgButtonBack" value="返回" onclick="history.back();" />
		          </td>
		        </tr>
		    </table>
		    
		    <div style="display:none;">
			    <!-- 盘盈DIV -->
				<div id="pyDiv" style="width:390px;max-height:240px;">
					<div style="width:100%;height:210px;overflow:auto;">
						<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
							<tr>
								<th align="left" colspan="4"><div style="float:center;margin-left:5px;">盘盈商品价格填写</div></th>
							</tr>
							<tr>
								<td width="3%" class="title_item" nowrap="nowrap">仓库：</td>
								<td width="47%"><span id="pyStoreSpan" style="color:#6495ED"></span></td>
								<td width="3%" class="title_item" nowrap="nowrap">商品：</td>
								<td width="47%"><span id="pyGoodSpan" style="color:#6495ED;"></span></td>
							</tr>
							<tr>
								<td width="3%" class="title_item" nowrap="nowrap">结果：</td>
								<td id="pyResultTd" colspan="3"></td>
							</tr>
							<tr>
								<td align="center" nowrap="nowrap" style="background:#EBEBEB;padding-top:1px;padding-bottom:1px;">序号</td>
					  			<td align="center" nowrap="nowrap" style="background:#EBEBEB;padding-top:1px;padding-bottom:1px;" colspan="2">成本价（元）</td>
					  			<td align="center" nowrap="nowrap" style="background:#EBEBEB;" colspan="1"><u style="color:blue" id="link_copy">一键填写</u></td>
							</tr>
							<tbody id="pyTbody"></tbody>
						</table>
					</div>
					<div style="float:right;margin-top:8px;">
						<input type="button" id="pyButton" name="pyButton" class="webbutton" value="确定" style="width:46px;" />
					</div>
				</div>
				
				<!-- 盘亏DIV -->
				<div id="pkDiv" style="width:510px;max-height:340px;">
					<div style="width:100%;height:310px;overflow:auto;">
						<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
							<tr>
								<th align="left" colspan="6"><div style="float:center;margin-left:5px;">盘亏待处理清单</div></th>
							</tr>
							<tr>
								<td width="3%" class="title_item" nowrap="nowrap">仓库：</td>
								<td width="47%" colspan="2"><span id="pkStoreSpan" style="color:#6495ED"></span></td>
								<td width="3%" class="title_item" nowrap="nowrap">商品：</td>
								<td width="47%" colspan="2"><span id="pkGoodSpan" style="color:#6495ED;"></span></td>
							</tr>
							<tr>
								<td width="3%" class="title_item" nowrap="nowrap">结果：</td>
								<td id="pkResultTd" colspan="5"></td>
							</tr>
							<tr>
								<td align="center" nowrap="nowrap" style="background:#EBEBEB;padding-top:1px;padding-bottom:1px;">序号</td>
					  			<td align="center" nowrap="nowrap" style="background:#EBEBEB;padding-top:1px;padding-bottom:1px;">交易类型</td>
					  			<td align="center" nowrap="nowrap" style="background:#EBEBEB;padding-top:1px;padding-bottom:1px;">单据编号</td>
					  			<td align="center" nowrap="nowrap" style="background:#EBEBEB;padding-top:1px;padding-bottom:1px;">日期</td>
					  			<td align="center" nowrap="nowrap" style="background:#EBEBEB;padding-top:1px;padding-bottom:1px;">成本价（元）</td>
					  			<td align="center" nowrap="nowrap" style="background:#EBEBEB;padding-top:1px;padding-bottom:1px;">选择</td>
							</tr>
							<tbody id="pkTbody"></tbody>
						</table>
					</div>
					<div style="float:right;margin-top:8px;">
						<input type="button" id="pkButton" name="pkButton" class="webbutton" value="确定" style="width:46px;" />
					</div>
				</div>
		    </div>
  		</html-el:form>
  	</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	// 日期限定 
	$("#opr_date").datepicker({yearRange:'2010:2050',maxDate:'${today}'});

	$("#opr_date").attr("dataType", "Require").attr("msg", "请选择盘点日期！");
	$("#ver_stocks").attr("dataType", "Require").attr("msg", "请填写盘点后数量！");

	$("#storesList").multiselect({ //选择仓库
		noneSelectedText: '<span >=请选择=</span>',
		selectedList: 1,
		multiple: false,
		minWidth:160,
		click: function(event, ui){
			var storeId = ui.value;
			var goodId = $("#goodsList").val();
			if (null != storeId && null != goodId) {
				// 查选中商品的库存 
				getStockNumForSelectPd(storeId, goodId);
			}
		}
	}).multiselectfilter({label:"<span'>搜索：</span>"}).attr("datatype", "Require").attr("msg", "请选择仓库！");
	//$("#storesList").val("${main_store_id}").multiselect("refresh");
	
	$("#goodsList").multiselect({
		noneSelectedText: '<span >=请选择=</span>',
		selectedList: 1,
		multiple: false,
		minWidth:160,
		click: function(event, ui){
			var storeId = $("#storesList").val();
			var goodId = ui.value;
			if (null != storeId && null != goodId) {
				// 查选中商品的库存 
				getStockNumForSelectPd(storeId, goodId);
			}
		}
	}).multiselectfilter({label:"<span>搜索：</span>"}).attr("datatype", "Require").attr("msg", "请选择商品！");

	//填写盘点库存数量
	$("#ver_stocks").blur(function(){
		$("#pyStoreSpan").empty();
		$("#pyGoodSpan").empty();
		$("#pyResultTd").empty();
		$("#pyTbody").empty();
		$("#pkStoreSpan").empty();
		$("#pkGoodSpan").empty();
		$("#pkResultTd").empty();
		$("#pkTbody").empty();
		
		var CurInveNum = $("#stocks").val(); //当前系统库存
		var VerStocks = $(this).val(); //盘点数量
		if ("" != CurInveNum && "" != VerStocks) {
			//alert(CurInveNum + "___" + VerStocks);
			//var reg = /^[-\+]?\d+$/;
			var reg = /^\d+$/;
			if (VerStocks.match(reg)) {
				// 库存检查 
				var count = parseInt(VerStocks, 10) - parseInt(CurInveNum, 10);
				if (count > 0) {
					//alert("盘盈");
					jLoading("&nbsp;&nbsp;正在处理...", 
							{autoHide:true, 
							 HorizontalPosition:"center", 
							 VerticalPosition:"center", 
							 MinWidth:150, 
							 TimeShown:1000, 
							 onClosed:function(){$("#vsResultTd").empty().append('<span>盘点数量大于当前系统库存数量，当前库存状况为：<span style="color:#009900;">盘盈</span></span>');}
				 	});
// 					jLoading("&nbsp;&nbsp;正在处理...", 
// 							{autoHide:true, 
// 							 HorizontalPosition:"center", 
// 							 VerticalPosition:"center", 
// 							 MinWidth:150, 
// 							 TimeShown:1000, 
// 							 onClosed:function(){
// 						 		$("#vsResultTd").empty().append('盘点数量大于当前系统库存数量，当前库存状况为：大于当前系统库存数量<div id="hxDiv" style="margin-top:5px;"></div>');
// 						 		$("#pyTbody").empty();
						 		
// 						 		var storesName = $("#storesList option:selected").text();
// 						 		var goodsName = $("#goodsList option:selected").text();
// 						 		$("#pyStoreSpan").text(storesName);
// 						 		$("#pyGoodSpan").text(goodsName);
// 						 		$("#pyResultTd").empty().append('盘点数量大于当前系统库存数量，当前库存状况为：<span style="color:#009900;">盘盈</span>');
// 						 		for ( var i = 0; i < count; i++) {
// 									$("#pyTbody").append('<tr>' + 
// 															'<td align="center" nowrap="nowrap">' + (i+1) + '</td>' + 
// 															'<td align="center" nowrap="nowrap" colspan="2"><input type="text" id="pyBuyPrice_' + i + '" name="pyBuyPrice" class="webinput" size="14" /></td>' + 
// 															'<td align="center" nowrap="nowrap" colspan="1"></td>' + 
// 															'</tr>');
// 								}
// 						 		//库存信息查询完毕后弹出盘盈DIV层
// 						 		openPyDiv();
// 						 	}
// 			 		});
			 		//*********************************************************盘盈END**********************************************************//
			 		
				} else if (count < 0) {
					//alert("盘亏");
					var num = parseInt(CurInveNum, 10) - parseInt(VerStocks, 10);
					jLoading("&nbsp;&nbsp;正在处理...", 
							{autoHide:true, 
							 HorizontalPosition:"center", 
							 VerticalPosition:"center", 
							 MinWidth:150, 
							 TimeShown:1000, 
							 onClosed:function(){
						 		$("#vsResultTd").empty().append('盘点数量小于当前系统库存数量，当前库存状况为：<span style="color:#CD0000;">盘亏</span><div id="hxDiv" style="margin-top:5px;">');

								$("#pkTbody").empty();
						 		var storesName = $("#storesList option:selected").text();
						 		var goodsName = $("#goodsList option:selected").text();
						 		var storesId = $("#storesList option:selected").val();
						 		var goodsId = $("#goodsList option:selected").val();
						 		$("#pkStoreSpan").text(storesName);
						 		$("#pkGoodSpan").text(goodsName);
						 		$("#pkResultTd").empty().append('盘点数量小于当前系统库存数量，当前库存状况为：<span style="color:#CD0000;">盘亏</span>');
								//Ajax异步取盘亏待处理清单...
// 								$.ajax({
// 									type: "POST" , 
// 									url: "KonkaStockInventory.do" , 
// 									data:"method=getPkGoodsAndStoresList&store_id=" + storesId + "&good_id=" + goodsId + "&num=" + num + "&t=" + new Date(),
// 									dataType: "json" , 
// 							        async: true, 
// 							        error: function (request, settings) {alert("数据加载请求失败");
// 							        											$("#pyStoreSpan").empty();
// 																				$("#pyGoodSpan").empty();
// 																				$("#pyResultTd").empty();
// 																				$("#pyTbody").empty();
// 																				$("#pkStoreSpan").empty();
// 																				$("#pkGoodSpan").empty();
// 																				$("#pkResultTd").empty();
// 																				$("#pkTbody").empty();
// 																				$("#vsResultTd").empty();
// 																				$("#ver_stocks").val("");
// 																		}, 
// 							        success: function (result) {
// 										//alert(result.state);
// 										if (result.state == 0) {
// 											jError("操作失败：参数丢失，请重试！", {HorizontalPosition:"center", VerticalPosition:"center", TimeShown:3000});
// 											$("#ver_stocks").val("");
// 											$("#vsResultTd").empty();
// 										} else if (result.state == -1) {
// 											jError("操作失败：查询列表失败！", {HorizontalPosition:"center", VerticalPosition:"center", TimeShown:3000});
// 											$("#ver_stocks").val("");
// 											$("#vsResultTd").empty();
// 										} else if (result.state == 1) {
// 											//库存信息查询完毕后打开盘亏DIV
// 											openPkDiv();
// 											// 生成list列表 
// 											for ( var j in result.list) {
// 												$("#pkTbody").append('<tr>' + 
// 														'<td align="center" nowrap="nowrap">' + (Number(j)+1) + '</td>' + 
// 														'<td align="center" nowrap="nowrap">' + result.list[j].business_type + '</td>' + 
// 														'<td align="center" nowrap="nowrap">' + result.list[j].bill_id_push + '</td>' + 
// 														'<td align="center" nowrap="nowrap">' + result.list[j].bill_date + '</td>' + 
// 														'<td align="center" nowrap="nowrap">' + result.list[j].goods_cost + '</td>' + 
// 														'<td align="center" nowrap="nowrap"><input type="checkbox" id="stackId_' + result.list[j].stack_id + '"                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   " value="' + result.list[j].stack_id + '" checked="checked" style="cursor:pointer;" /></td>' + 
// 													 '</tr>');
// 											}
// 							 				//alert(result.list);
// 										}
// 							        }
// 								});
						 	}
				 	});
					//*********************************************************盘亏END**********************************************************//
					
				} else {
					jLoading("&nbsp;&nbsp;正在处理...", 
							{autoHide:true, 
							 HorizontalPosition:"center", 
							 VerticalPosition:"center", 
							 MinWidth:150, 
							 TimeShown:1000, 
							 onClosed:function(){$("#vsResultTd").empty().append('<span>盘点数量等于当前系统库存数量，当前库存状况为：库实相符</span>');}
				 	});
				}
			} else {
				$("#vsResultTd").empty();
				jError("操作失败：盘点数量【" + VerStocks + "】格式非法！", {HorizontalPosition:"center", VerticalPosition:"center", TimeShown:3000, onClosed:function(){$("#ver_stocks").val("").focus();}});
			}
		}
	});

	//盘盈成本价格提交按钮(在盘盈div)
// 	$("#pyButton").click(function(){
// 		//alert($("input[type='text'][name='pyBuyPrice']").length);
// 		var num = $("input[type='text'][name='pyBuyPrice']").length;
// 		if (num == 0) {
// 			jError("操作失败：请重新盘点！", {HorizontalPosition:"center", VerticalPosition:"center", TimeShown:3000});
// 		} else {
// 			//验证是否为空
// 			var fa1 = true;
// 			$("input[type='text'][name='pyBuyPrice']").each(function(){
// 				if ("" == $(this).val() || null == $(this).val()) {
// 					fa1 = false;
// 					$(this).removeClass("webinput").addClass("error_rz");
// 				} else {
// 					$(this).removeClass("error_rz").addClass("webinput");
// 				}
// 			});
// 			if (!fa1) {
// 				jError("操作失败：盘盈商品成本价格不能为空！", {HorizontalPosition:"center", VerticalPosition:"center", TimeShown:3000});
// 				return false;
// 			}
// 			//验证输入值是否非法
// 			var fa2 = true;
// 			var reg = /^\d+(\.\d{1,2})?$/;
// 			$("input[type='text'][name='pyBuyPrice']").each(function(){
// 				if (!$(this).val().match(reg)) {
// 					fa2 = false;
// 					$(this).removeClass("webinput").addClass("error_rz");
// 				} else {
// 					$(this).removeClass("error_rz").addClass("webinput");
// 				}
// 			});
// 			if (!fa2) {
// 				jError("操作失败：盘盈商品成本价格填写非法，最大精度保留两位小数！", {HorizontalPosition:"center", VerticalPosition:"center", TimeShown:3000});
// 				return false;
// 			}
			
// 			if (confirm("确定？")) {
// 				$.fancybox.close();
// 				$("#hxPyTable").remove();
// 				$("#hxDiv").append('<table id="hxPyTable" border="0" cellpadding="0" cellspacing="1" class="tableClass" style="width:280px;">' +
// 											'<tr>' +
// 												'<th align="left" colspan="2"><div style="float:center;margin-left:5px;">盘盈信息</div><div style="float:right;margin-right:5px;margin-top:2px;"><a id="hxPyResult" href="javascript:openPyDiv();"><img src="${ctx}/styles/customer/images/xiugai_1.gif" /></a></div></th>' +
// 											'</tr>' +
// 											'<tr>' +
// 												'<td width="3%" align="center" nowrap="nowrap" style="background:#EBEBEB;padding-top:1px;padding-bottom:1px;">序号</td>' +
// 									  			'<td align="center" nowrap="nowrap" style="background:#EBEBEB;padding-top:1px;padding-bottom:1px;">成本价（元）</td>' +
// 											'</tr>' +
// 											'<tbody id="hxPyTbody"></tbody>' +
// 										'</table>');
// 				$("input[name='pyBuyPrice']", "#pyTbody").each(function(i, val){
// 					$("#hxPyTable").append('<tr>' + 
// 											'<td align="center" nowrap="nowrap">' + (Number(i) + 1) + '</td>' + 
// 											'<td align="center" nowrap="nowrap">' + val.value + '</td>' + 
// 										 '</tr>');
// 				});
// 				//iframe高度自适应
// 				window.parent.resizeFrameHeight('mainFrame', 3);
// 			}
// 		}
// 	});

	//盘亏清单提交按钮(在盘亏div)
// 	$("#pkButton").click(function(){
// 		var num = $("input[type='checkbox'][name='stack_id']").length;
// 		if (num == 0) {
// 			jError("操作失败：请重新盘点！", {HorizontalPosition:"center", VerticalPosition:"center", TimeShown:3000});
// 		} else {
// 			var CurInveNum = $("#stocks").val(); //当前系统库存
// 			var VerStocks = $("#ver_stocks").val(); //盘点数量
// 			var count = parseInt(CurInveNum, 10) - parseInt(VerStocks, 10);
			
// 			var selectNum = 0;
// 			$("input[type='checkbox'][name='stack_id']").each(function(){
// 				if ($(this).attr("checked") == "checked") {
// 					selectNum++;
// 				}
// 			});
// 			if (0 == selectNum) { //未选择push对象
// 				jError("操作失败：请选择！", {HorizontalPosition:"center", VerticalPosition:"center", TimeShown:3000});
// 				return false;
// 			} else if (count < selectNum) { //选择push对象过多
// 				jError("操作失败：选择过多！", {HorizontalPosition:"center", VerticalPosition:"center", TimeShown:3000});
// 				return false;
// 			} else if (count > selectNum) { //选择push对象过少
// 				jError("操作失败：选择过少！", {HorizontalPosition:"center", VerticalPosition:"center", TimeShown:3000});
// 				return false;
// 			} else if (count == selectNum) { //成功
// 				if (confirm("确定？")) {
// 					$.fancybox.close();
// 					$("#hxPkTable").remove();
// 					$("#hxDiv").append('<table id="hxPkTable" border="0" cellpadding="0" cellspacing="1" class="tableClass" style="width:510px;">' +
// 											'<tr>' +
// 												'<th align="left" colspan="5"><div style="float:center;margin-left:5px;">盘亏待处理清单</div><div style="float:right;margin-right:5px;margin-top:2px;"><a id="hxPkResult" href="javascript:openPkDiv();"><img src="${ctx}/styles/customer/images/xiugai_1.gif" /></a></div></th>' +
// 											'</tr>' +
// 											'<tr>' +
// 												'<td align="center" nowrap="nowrap" style="background:#EBEBEB;padding-top:1px;padding-bottom:1px;">序号</td>' +
// 									  			'<td align="center" nowrap="nowrap" style="background:#EBEBEB;padding-top:1px;padding-bottom:1px;">交易类型</td>' +
// 									  			'<td align="center" nowrap="nowrap" style="background:#EBEBEB;padding-top:1px;padding-bottom:1px;">单据编号</td>' +
// 									  			'<td align="center" nowrap="nowrap" style="background:#EBEBEB;padding-top:1px;padding-bottom:1px;">日期</td>' +
// 									  			'<td align="center" nowrap="nowrap" style="background:#EBEBEB;padding-top:1px;padding-bottom:1px;">成本价（元）</td>' +
// 											'</tr>' +
// 										'</table>');
// 					$("input[type='checkbox'][name='stack_id']", "#pkTbody").each(function(){
// 						if ($(this).attr("checked") == "checked") {
// 							$("#hxPkTable").append('<tr>' +
// 														'<td align="center" nowrap="nowrap">' + $(this).parent().prev().prev().prev().prev().prev().text() + '</td>' +
// 														'<td align="center" nowrap="nowrap">' + $(this).parent().prev().prev().prev().prev().text() + '</td>' +
// 														'<td align="center" nowrap="nowrap">' + $(this).parent().prev().prev().prev().text() + '</td>' +
// 														'<td align="center" nowrap="nowrap">' + $(this).parent().prev().prev().text() + '</td>' +
// 														'<td align="center" nowrap="nowrap">' + $(this).parent().prev().text() + '</td>' +
// 													'</tr>');
// 						}
// 					});
// 					//iframe高度自适应
// 					window.parent.resizeFrameHeight('mainFrame', 3);
// 				}
// 			}
// 		}
// 	});
	
	//价格一键复制 
// 	$("#link_copy").click(function(){
// 		var val = "";
// 		$("input[type='text'][name='pyBuyPrice']").each(function(){
// 			if ((this.value).length > 0) {
// 				val = $(this).val();
// 				return false;
// 			}
// 		});
// 		if(val.length > 0){
// 			$("input[type='text'][name='pyBuyPrice']").each(function(){
// 				$(this).val(val);
// 			});
// 		}
// 	});
	
	//表单提交
	$("#btn_submit").click(function(){
		// 盘赢或盘亏时,没有生成列表信息 校验? 
		if($("#vsResultTd").text()=='盘点数量等于当前系统库存数量，当前库存状况为：库实相符'){
			//不需要结果列表
		}else{
			//如果没有结果列表 ,不能提交 
// 			if($("#hxDiv").text()==null || $("#hxDiv").text()==""){
// 				$("#vsResultTd").empty().append("<span style='color:red'>盘点结果异常,请重新盘点! </span>");
// 				return false;
// 			};
		}
		// post 
		if(Validator.Validate(this.form, 3)){
			if(confirm("确定提交表单？")){
	            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
	            $("#btn_reset").attr("disabled", "true");
// 	            $("#btn_back").attr("disabled", "true");
				this.form.submit();
			} else {
				return false;
			}
		}
	});
	
});

//取选择仓库中的商品库存
function getStockNumForSelectPd(storeId, goodId){
	//alert("仓库ID = " + storeId + "\n商品ID = " + goodId);
	if (null != storeId && null != goodId && "" != storeId && "" != goodId) {
		$("#stocks").val(""); //库存当前数量归零
		$("#ver_stocks").val("").attr("readonly", "true"); //手输盘点数量归零
		$("#vsResultTd").empty(); //库存结果清空

		//结果清空
		$("#pyStoreSpan").empty();
		$("#pyGoodSpan").empty();
		$("#pyResultTd").empty();
		$("#pyTbody").empty();
		
		jLoading("&nbsp;&nbsp;正在查询当前系统库存...", {autoHide:false, HorizontalPosition:"center", VerticalPosition:"center", MinWidth:150});
		$.ajax({
			type: "POST" , 
			url: "KonkaStockInventory.do" , 
			data:"method=getStockNumForSelectPd&store_id=" + storeId + "&good_id=" + goodId + "&t=" + new Date(),
			dataType: "json" , 
	        async: true, 
	        error: function (request, settings) {alert(" 数据加载请求失败！ "); }, 
	        success: function (result) {
				if (result.state == -1) { //参数丢失
					$("#opr_date").datepicker({yearRange:'2000:2040',maxDate:'${today}'}).datepicker('refresh');
					jError("操作失败：参数丢失，请重试！", {HorizontalPosition:"center", VerticalPosition:"center", TimeShown:3000});
				} else if(result.state == 0) { //查询得当前系统库存
					$("#stocks").val(result.stocks);
					$("#ver_stocks").removeAttr("readonly");
					var lastOprDate = result.lastOprDate;
					if ("" != lastOprDate) { //最近一次盘点日期
						$("#opr_date").datepicker("destroy");
						$("#opr_date").datepicker({yearRange:'2000:2040',maxDate:'${today}',minDate:lastOprDate}).datepicker('refresh');
					} else {
						$("#opr_date").datepicker({yearRange:'2000:2040',maxDate:'${today}'}).datepicker('refresh');
					}
					$.jNotify._close();
				}
	        }
		});
	}
}

function openPyDiv(){
	$("#pyAuto").fancybox({'modal':false,
			'overlayShow':true, //显示遮罩层
			'hideOnOverlayClick':false, //如果为true则点击遮罩层关闭fancybox
			'hideOnContentClick':false, //如果为true则点击播放内容关闭fancybox
			'enableEscapeButton':false, //如果为true，则启用ESC来关闭fancybox
			'showCloseButton':true, //显示关闭按钮
			'centerOnScroll':true, //如果为true，当你滚动滚动条时，fancybox将会一直停留在浏览器中心
			'autoScale':false, //如果为true，fancybox可以自适应浏览器窗口大小
			'padding':10,
			'width':680,
			'height':500}).trigger('click');
}

function openPkDiv(){
	$("#pkAuto").fancybox({'modal':false,
			'overlayShow':true, //显示遮罩层
			'hideOnOverlayClick':false, //如果为true则点击遮罩层关闭fancybox
			'hideOnContentClick':false, //如果为true则点击播放内容关闭fancybox
			'enableEscapeButton':false, //如果为true，则启用ESC来关闭fancybox
			'showCloseButton':true, //显示关闭按钮
			'centerOnScroll':true, //如果为true，当你滚动滚动条时，fancybox将会一直停留在浏览器中心
			'autoScale':false, //如果为true，fancybox可以自适应浏览器窗口大小
			'padding':10,
			'width':680,
			'height':500}).trigger('click');
}

//正则表达式：只能输入数字
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
		//if(obj.value.length == 0) obj.value = "0";
	});
}
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>