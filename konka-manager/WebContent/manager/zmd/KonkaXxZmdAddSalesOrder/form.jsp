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
<!--<style type="text/css">-->
<!--   html { overflow:hidden;} -->
<!--</style>-->
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
	  <html-el:form action="/zmd/KonkaXxZmdAddSalesOrder">
		  <html-el:hidden property="method" value="save" />
		  <html-el:hidden property="mod_id" />
		  <html-el:hidden property="sell_bill_id"/>
		  <html-el:hidden property="queryString" />
		  <table id="main_table" width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
		  	<!-- 销售单信息Begin -->
		  	<tr>
		  		<td colspan="4" style="font-weight:900;">销售单信息</td>
		  	</tr>
		  	<tr>
		  		<td width="15%" class="title_item" nowrap="nowrap" align="right" ><strong>分公司：</strong></td>
	            <td width="35%" align="left" style="padding-bottom:5px;">
	            	<html-el:hidden property="dept_id" styleId="dept_id" value="${af.map.dept_id}" />
	            	<c:out value="${af.map.dept_name}" />
	            </td>
	            <td width="15%" class="title_item" nowrap="nowrap" align="right">专卖店编号：</td>
	            <td align="left" style="padding-bottom:5px;">
	            	<html-el:hidden property="zmd_id" styleId="zmd_id" value="${af.map.zmd_id}" />
	            	<html-el:hidden property="zmd_sn" styleId="zmd_sn" value="${af.map.zmd_sn}" />
	            	<c:out value="${af.map.zmd_sn}" /></td>
		  	</tr>
		  	<tr>
		  		<td width="15%" class="title_item" nowrap="nowrap" align="right"><span style="color:#FF0000;">[必填]</span>销售人员：</td>
		  		<td width="35%" align="left" style="padding-bottom:5px;"><html-el:text property="sell_man" styleId="sell_man" value="${af.map.sell_man}" maxlength="20" /></td>
		  		<td width="15%" class="title_item" nowrap="nowrap" align="right"><span style="color:#FF0000;">[必填]</span>销售时间：</td>
		  		<td align="left" style="padding-bottom:5px;"><html-el:text property="sell_date" styleId="sell_date" maxlength="10" size="10" readonly="readonly" style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
		  	</tr>
		  	<tr>
		  		<td width="15%" class="title_item" nowrap="nowrap" align="right"><span style="color:#FF0000;">[必填]</span>付款方式：</td>
		  		<td align="left" colspan="3" style="padding-bottom:5px;">
		  			<c:forEach items="${baseTypesList50000}" var="cur">
		  				<label for="pay_way_${cur.type_id}" style="cursor: pointer;"><html-el:radio property="pay_way" styleId="pay_way_${cur.type_id}" value="${cur.type_id}">${cur.type_name}</html-el:radio></label>
		  			</c:forEach>
		  		</td>
		  	</tr>
		  	<tr id="money_of_deposit_tr" style="display: none;">
		  		<td width="15%" class="title_item" nowrap="nowrap" align="right"><span style="color:#FF0000;">[必填]</span>订金：</td>
		  		<td align="left" colspan="3" style="padding-bottom:5px;"><html-el:text property="money_of_deposit" styleId="money_of_deposit" maxlength="8" />&nbsp;<img src="${ctx}/images/yen.png" alt="￥" title="RMB" /></td>
		  	</tr>
	        
	        <tr>
	        	<td width="15%" class="title_item" nowrap="nowrap" align="right"><span style="color:#FF0000;">[必填]</span>送货地区：</td>
	        	<td align="left" colspan="3" style="padding-bottom:5px;">
	        		<html-el:select property="province" styleId="province" style="width:150px;"></html-el:select>&nbsp;
	        		<html-el:select property="city" styleId="city" style="width:150px;">
	        			<html-el:option value="">=请选择市=</html-el:option>
	        		</html-el:select>&nbsp;
	        		<html-el:select property="country" styleId="country" style="width:150px;">
	        			<html-el:option value="">=请选择区/县=</html-el:option>
	        		</html-el:select>&nbsp;
	        		<html-el:select property="town" styleId="town" style="width:150px;">
	        			<html-el:option value="">=请选择乡/镇=</html-el:option>
	        		</html-el:select>
	        	</td>
	        </tr>
	        <tr>
	        	<td width="15%" class="title_item" nowrap="nowrap" align="right"><span style="color:#FF0000;">[必填]</span>送货街道地址：</td>
	        	<td align="left" colspan="3" style="padding-bottom:5px;"><html-el:text property="sell_post_addr" styleId="sell_post_addr" maxlength="30" size="80" /></td>
	        </tr>
	        <tr>
	        	<td width="15%" class="title_item" nowrap="nowrap" align="right"><span style="color:#FF0000;">[必填]</span>收货人姓名：</td>
	        	<td align="left" colspan="3" style="padding-bottom:5px;"><html-el:text property="sell_rec_name" styleId="sell_rec_name" maxlength="20" /></td>
	        </tr>
	        <tr>
	        	<td width="15%" class="title_item" nowrap="nowrap" align="right"><span style="color:#FF0000;">[必填]</span>收货人联系电话：</td>
	        	<td align="left" colspan="3" style="padding-bottom:5px;"><html-el:text property="sell_rec_link_mp" styleId="sell_rec_link_mp" maxlength="11" /></td>
	        </tr>
	        <tr>
	        	<td width="15%" class="title_item" nowrap="nowrap" align="right"><span style="color:#FF0000;">[必填]</span>发票类型：</td>
		  		<td align="left" colspan="3" style="padding-bottom:5px;">
		  			<c:forEach items="${baseTypesList90000}" var="cur">
		  				<label for="sell_bill_type_${cur.type_id}" style="cursor: pointer;"><html-el:radio property="sell_bill_type" styleId="sell_bill_type_${cur.type_id}" value="${cur.type_id}">${cur.type_name}</html-el:radio></label>
		  			</c:forEach>
	        	</td>
	        </tr>
	        <tr>
	        	<td width="15%" class="title_item" nowrap="nowrap" align="right">备注：</td>
	        	<td align="left" colspan="3" style="padding-bottom:5px;">
	        		<html-el:textarea property="sell_remarks" styleId="sell_remarks" cols="70" rows="5" />
	        		<div id="info_chat_content_1"  style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/styles/jxc/images/tishi.gif" style="vertical-align:middle;" /></div>
	        	</td>
	        </tr>
	        <!-- 销售单信息End -->
	        <!-- 商品信息Begin -->
	        <tr>
		  		<td colspan="4" style="font-weight:900;">商品信息</td>
		  	</tr>
	        <tr>
	        	<td width="15%" class="title_item" nowrap="nowrap" align="right" id="goods_title">
	        	  <div id="div_1">
	        		<strong><span style="color:#FF0000;">[必填]</span>添加商品：</strong>
	        	  </div>
	        	</td>
	        	<td colspan="3" align="left" style="padding-top:5px;padding-bottom:5px;">
	        	  <div id="div_2" style="width:1100px;">
	        		<!-- 产品信息Begin -->
	        		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
				        <tr class="tabtt1">
				          <td width="3%" align="center" nowrap="nowrap">序号</td>
				          <td width="22%" align="center" nowrap="nowrap">产品型号</td>
				          <td width="7%" align="center" nowrap="nowrap">产品大类</td>
				          <td width="15%" align="center" nowrap="nowrap">赠品</td>
				          <td width="6%" align="center" nowrap="nowrap">库存</td>
				          <td width="6%" align="center" nowrap="nowrap">数量</td>
				          <td width="8%" align="center" nowrap="nowrap">单价</td>
				          <td width="10%" align="center" nowrap="nowrap">金额</td>
				          <td width="8%" align="center" nowrap="nowrap">选择仓库</td>
				          <td width="15%" align="center" nowrap="nowrap">销售备注</td>
				          <td width="5%" align="center" nowrap="nowrap" id="addPdTD" style="cursor:pointer;"><img src="${ctx}/images/+.gif" style="vertical-align:middle;" /></td>
				        </tr>
				        <!-- 隐藏TR模版Begin -->
				        <tr id="pdModel" style="display:none;height:40px;">
				        	<td align="center"></td>
				        	<td align="left" class="pdIds">
				        		<select name="pdIdList" id="pdIdList" multiple="multiple">
								<!--<option value="">==请选择==</option>-->
									<c:forEach items="${pdIdList}" var="cur" varStatus="vs">
										<option value="${cur.dept_pd_id}[#####]${cur.pd_cls}[#####]${cur.pd_cls_name}[#####]${cur.md_name}[#####]${cur.pd_type}[#####]${cur.price_ref}[#####]${cur.price_max}[#####]${cur.price_min}">${cur.md_name}</option>
									</c:forEach>
								</select>			        			
				        	</td>
				        	<td align="center" nowrap="nowrap"></td>
				        	<td align="center" nowrap="nowrap">
				        		<html-el:text property="gift" styleId="gift" size="15" maxlength="200"></html-el:text>
				        	</td>
				        	<td align="center" nowrap="nowrap"></td>
				        	<td align="center" nowrap="nowrap">
				        		<html-el:text property="counts" styleId="counts" disabled="true" style="width:40px;" maxlength="5" value="1" />
				        	</td>
				        	<td align="center" nowrap="nowrap">
				        		<img src="${ctx}/images/yen.png" alt="￥" title="RMB" /><html-el:text property="price" styleId="price" disabled="true" style="width:45px;" maxlength="8" />
				        	</td>
				        	<td align="right" nowrap="nowrap"><img src="${ctx}/images/yen.png" alt="￥" title="RMB" /><span id="pdAmount"></span></td>
				        	<td align="center" nowrap="nowrap"></td>
				        	<td align="center" nowrap="nowrap">
				        		<html-el:text property="xs_remarks" styleId="xs_remarks" size="15" maxlength="40"></html-el:text>
				        	</td>
				        	<html-el:hidden property="pdIds" styleId="pdIds" />
			        		<html-el:hidden property="md_name" styleId="md_name" />
			        		<html-el:hidden property="pd_cls" styleId="pd_cls" />
			        		<html-el:hidden property="pd_cls_name" styleId="pd_cls_name" />
			        		<html-el:hidden property="pd_type" styleId="pd_type" />
			        		<html-el:hidden property="pdStocksNum" styleId="pdStocksNum" />
			        		<html-el:hidden property="price_max" styleId="price_max" />
			        		<html-el:hidden property="price_min" styleId="price_min" />
			        		<html-el:hidden property="ck" styleId="ck" />
				        	<td align="center"><img src="${ctx}/images/x.gif" style="vertical-align:middle;top:0px;" /></td>
				        </tr>
				        <!-- 隐藏TR模版End -->
				        <tbody id="showSalesOrderTbody"></tbody>
				        <tr id="total">
				        	<td align="center" colspan="2" style="color:red;font-weight:bold;">合计</td>
				        	<td colspan="3" align="left"></td>
				        	<td align="center" nowrap="nowrap">0</td>
				        	<td></td>
				        	<td align="right" nowrap="nowrap">
				        		<font color="red" style="font-weight:bold;"><img src="${ctx}/images/yen.png" alt="￥" title="RMB" /><span id="allAmount">0.00</span></font>
				        		<html-el:hidden property="total_money" styleId="total_money" value="0.00" />
				        	</td>
				        	<td colspan="3"></td>
				        </tr>
				    </table>
				    <!-- 产品信息End -->
				  </div>
	        	</td>
	        </tr>
	        <tr>
	        	<td width="15%" class="title_item" nowrap="nowrap" align="right" id="goods_title">
	        		<strong><span style="color:#FF0000;">[必填]</span>选择仓位：</strong>
	        	</td>
	        	<td colspan="3">
	        		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
	        		  <tr>
	        		  	<td align="center" style="font-weight:bold;">产品型号</td>
	        			<c:forEach items="${zmdFactoryStoreList}" var="cur" varStatus="vs">
	        				<td colspan="2" align="center" nowrap="nowrap">
	        					<div style="font-weight:bold;">${cur.map.store_desc}</div>
	        					<div>工厂编号：${cur.factory_id}</div>
	        					<div>仓位编号：${cur.store_id}</div>
	        				</td>
	        			</c:forEach>
	        		  </tr>
	        		  <tr id="zmdStores" style="">
	        		  	<td></td>
	        		  	<c:forEach items="${zmdFactoryStoreList}" var="cur" varStatus="vs">
	        		  		<td></td>
	        		  		<td align="center"><html-el:text property="ck_count" styleId="ck_count" maxlength="10" size="4" onkeyup="$(this).focus(setOnlyNum)" /></td>
	        		  	</c:forEach>
	        		  </tr>
	        		  <tbody id="showZmdStoresTbody"></tbody>
	        		</table>
	        	</td>
	        </tr>
	        <!-- 商品信息End -->
	        <!-- 客户信息Begin -->
	        <tr>
		  		<td colspan="4" style="font-weight:900;">客户信息</td>
		  	</tr>
		  	<tr>
	            <td width="15%" class="title_item" nowrap="nowrap" align="right" ><span style="color:#FF0000;">[必填]</span><strong>客户姓名：</strong></td>
	            <td width="35%" align="left" style="padding-bottom:5px;">
	            	<html-el:text property="buyer_name" styleId="buyer_name" maxlength="20" />
	            </td>
	            <td width="15%" class="title_item" nowrap="nowrap" align="right">身份证：</td>
	            <td align="left" style="padding-bottom:5px;"><html-el:text property="buyer_id" styleId="buyer_id" maxlength="18" onblur="getBuyerInfo($(this).val())" /></td>
	        </tr>
	        <tr>
	            <td width="15%" class="title_item" nowrap="nowrap" align="right" ><strong>性别：</strong></td>
	            <td width="35%" align="left" style="padding-bottom:5px;">
	            	<html-el:select property="buyer_sex" styleId="buyer_sex">
	            		<html-el:option value="">==请选择==</html-el:option>
	            		<html-el:option value="1">男</html-el:option>
	            		<html-el:option value="2">女</html-el:option>
	            		<html-el:option value="3">保密</html-el:option>
	            	</html-el:select>
	            </td>
	            <td width="15%" class="title_item" nowrap="nowrap" align="right">生日：</td>
	            <td align="left" style="padding-bottom:5px;"><html-el:text property="buyer_birthday" styleId="buyer_birthday" maxlength="10" size="10" readonly="readonly" style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
	        </tr>
	        <tr>
	            <td width="15%" class="title_item" nowrap="nowrap" align="right" ><strong>电话：</strong></td>
	            <td width="35%" align="left" style="padding-bottom:5px;">
	            	<html-el:text property="buyer_tel" styleId="buyer_tel" maxlength="13"></html-el:text>
	            </td>
	            <td width="15%" class="title_item" nowrap="nowrap" align="right"><span style="color:#FF0000;">[必填]</span>手机：</td>
	            <td align="left" style="padding-bottom:5px;"><html-el:text property="buyer_phone" styleId="buyer_phone" maxlength="11" /></td>
	        </tr>
	        <tr>
	        	<td width="15%" class="title_item" nowrap="nowrap" align="right"><span style="color:#FF0000;">[必填]</span>客户所在地区：</td>
	        	<td align="left" colspan="3" style="padding-bottom:5px;">
	        		<html-el:select property="buyer_province" styleId="buyer_province" style="width:150px;"></html-el:select>&nbsp;
	        		<html-el:select property="buyer_city" styleId="buyer_city" style="width:150px;">
	        			<html-el:option value="">=请选择市=</html-el:option>
	        		</html-el:select>&nbsp;
	        		<html-el:select property="buyer_country" styleId="buyer_country" style="width:150px;">
	        			<html-el:option value="">=请选择区/县=</html-el:option>
	        		</html-el:select>&nbsp;
	        		<html-el:select property="buyer_town" styleId="buyer_town" style="width:150px;">
	        			<html-el:option value="">=请选择乡/镇=</html-el:option>
	        		</html-el:select>
	        	</td>
	        </tr>
	        <tr>
	        	<td width="15%" class="title_item" nowrap="nowrap" align="right">客户联系地址：</td>
	        	<td align="left" colspan="3" style="padding-bottom:5px;"><html-el:text property="buyer_link_addr" styleId="buyer_link_addr" maxlength="30" size="80" /></td>
	        </tr>
	        <tr>
	        	<td width="15%" class="title_item" nowrap="nowrap" align="right">消费者备注：</td>
	        	<td align="left" colspan="3" style="padding-bottom:5px;">
	        		<html-el:textarea property="buyer_memo" styleId="buyer_memo" cols="70" rows="5" />
	        		<div id="info_chat_content_2"  style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/styles/jxc/images/tishi.gif" style="vertical-align:middle;" /></div>
	        	</td>
	        </tr>
	        <!-- 客户信息End -->
	        <!-- 销售单状态Begin -->
	<!--        <tr>-->
	<!--	  		<td colspan="4" style="font-weight:900;">销售单状态</td>-->
	<!--	  	</tr>-->
	<!--	  	<tr>-->
	<!--	  		<td width="15%" class="title_item" nowrap="nowrap" align="right"><span style="color:#FF0000;">[必填]</span>销售单状态：</td>-->
	<!--	  		<td align="left" colspan="3" style="padding-bottom:5px;">-->
	<!--	  			<html-el:select property="sell_state" styleId="sell_state">-->
	<!--	  				<html-el:option value="">==请选择==</html-el:option>-->
	<!--	  				<c:forEach items="${baseTypesList40000}" var="cur">-->
	<!--	  					<c:if test="${cur.type_id le 40200}">-->
	<!--	  						<html-el:option value="${cur.type_id}">${cur.type_name}</html-el:option>-->
	<!--	  					</c:if>-->
	<!--	  				</c:forEach>-->
	<!--					<html-el:option value="0">未付款</html-el:option>-->
	<!--					<html-el:option value="10">已付款未审核</html-el:option>-->
	<!--	  			</html-el:select>-->
	<!--	  		</td>-->
	<!--	  	</tr>-->
	        <!-- 销售单状态End -->
	        <tr>
	            <td colspan="6" height="40"  align="center">
	            	<input class="but4" type="button" name="Submit4" value="保存 " id="btn_submit" />
<!--	            	<input class="but3" type="reset" style="width:100px;" value="重填" id="reset" />-->
	            	<input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" id="btn_back" />
	            </td>
	        </tr>
		  </table>
	  </html-el:form>
  </div>
</div>
<div id="newDeliveryDiv" style="display:none;">
    <div id="loadingDiv" align="left" style=""><img src="${ctx}/images/loading.gif" style="vertical-align:middle;" />正在查询仓库信息,请稍等...</div>
    <!-- 仓库列表 Begin -->
    <div id="tbody_div"></div>
    <!-- 仓库列表 End -->
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery1.5.1.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<!--<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>-->
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.timers.js"></script>
<script type="text/javascript"><!--//<![CDATA[
$(document).ready(function(){
	var today = new Date();
	today = today.format("yyyy-MM-dd"); 
	$("#sell_date").val(today);
		
	$("#sell_date").attr("readonly", "true").datepicker();
	$("#buyer_birthday").attr("readonly", "true").datepicker({yearRange:'1955:2030'});

	window.body_width = 0;
	//body自适应宽度Begin
	var w = 0;
	for ( var i = 1; i <= 2; i++) {
		var w_d = $("#div_" + i).width();
		w = w + w_d + 5 + 5;
	}
	body_width = w + 14 + 60;
	$("body").css("width", body_width + 30).css("overflow", "auto");
	//css("overflow-x", "auto");
	//body自适应宽度End
	
	if ("" != "${af.map.msg}") {
		$.jGrowl("${af.map.msg}",
				 {theme:'success', 
			 	  life: 4500});
	}

	$("#province").attr({"subElement": "city", "defaultText": "=请选择省/市/自治区=", "defaultValue": "", "selectedValue": "${af.map.province}"});
	$("#city").attr({"subElement": "country", "defaultText": "=请选择市=", "defaultValue": "", "selectedValue": "${af.map.city}"});
	$("#country").attr({"subElement": "town","defaultText": "=请选择区/县=", "defaultValue": "", "selectedValue": "${af.map.country}"});
	$("#town").attr({"defaultText": "=请选择乡/镇=", "defaultValue": "", "selectedValue": "${af.map.town}"});
	$("#province").cs_ext("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false,"p_index_join","${af.map.p_index_join}");
	$("#province").change();

	$("#buyer_province").attr({"subElement": "buyer_city", "defaultText": "=请选择省/市/自治区=", "defaultValue": "", "selectedValue": "${af.map.buyer_province}"});
	$("#buyer_city").attr({"subElement": "buyer_country", "defaultText": "=请选择市=", "defaultValue": "", "selectedValue": "${af.map.buyer_city}"});
	$("#buyer_country").attr({"subElement": "buyer_town","defaultText": "=请选择区/县=", "defaultValue": "", "selectedValue": "${af.map.buyer_country}"});
	$("#buyer_town").attr({"defaultText": "=请选择乡/镇=", "defaultValue": "", "selectedValue": "${af.map.buyer_town}"});
	$("#buyer_province").cs_ext("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false,"p_index_join","${af.map.p_index_join}");
	$("#buyer_province").change();
	
	$("#sell_man").attr("dataType", "Require").attr("msg", "请填写销售人员！");
	$("#sell_date").attr("dataType", "Require").attr("msg", "请填写销售时间！");
	$("input[type='radio'][name='pay_way']").eq(3).attr("dataType", "Group").attr("msg", "请选择付款方式！");
	$("#province").attr("dataType", "Require").attr("msg", "请选择省/直辖市/自治区！");
	$("#city").attr("dataType", "Require").attr("msg", "请选择市！");
	$("#country").attr("dataType", "Require").attr("msg", "请填写区/县！");
	$("#sell_rec_name").attr("dataType", "Require").attr("msg", "请填写收货人姓名！");
	$("#sell_rec_link_mp").attr("dataType", "Require").attr("msg", "请填写收货人联系方式！");
	$("input[type='radio'][name='sell_bill_type']").eq(1).attr("dataType", "Group").attr("msg", "请选择发票类型！");
	$("#buyer_province").attr("dataType", "Require").attr("msg", "请选择客户所在选择省/直辖市/自治区！");
	$("#buyer_city").attr("dataType", "Require").attr("msg", "请选择客户所在市！");
	$("#buyer_country").attr("dataType", "Require").attr("msg", "请选择客户所在区/县！");
	$("#sell_post_addr").attr("dataType", "Require").attr("msg", "请填写送货街道地址！");
	$("#buyer_name").attr("dataType", "Require").attr("msg", "请填写客户姓名！");
	$("#buyer_phone").attr("dataType","Mobile").attr("msg","手机格式不正确，请正确填写！");
	$("#buyer_tel").attr("dataType","Phone").attr("msg","电话格式不正确，请正确填写！").attr("Require", "false");
	$("#sell_state").attr("dataType","Require").attr("msg","请选择销售单状态！");
	$("#sell_remarks").textbox({
		maxLength: 200,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/styles/jxc/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_chat_content_1").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_chat_content_1").slideUp("normal");
	});
	$("#buyer_memo").textbox({
		maxLength: 200,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/styles/jxc/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_chat_content_2").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_chat_content_2").slideUp("normal");
	});
	
	
	window.task_index = 0;
	var s = [];
	
	$("#addPdTD").click(function(){
		$("#pdModel").clone().appendTo($("#showSalesOrderTbody")).show();
		task_index++;
		var lastTR = $("tr:last", "#showSalesOrderTbody");
		lastTR.children().eq(0).text(task_index);
		
		$("#md_name", lastTR).attr("dataType", "Require").attr("msg", "请选择商品型号！");
		$("#counts", lastTR).attr("dataType", "Require").attr("msg", "请填写商品数量！");
		$("#price", lastTR).attr("dataType", "Require").attr("msg", "请填写商品单价！");
		
		var totalNum = parseInt($("#total").children().eq(2).text(), 10);
		var addNum = totalNum + 1;
		$("#total").children().eq(2).text(addNum.toString());
		
		//修改商品数量keyup事件
		$("#counts", lastTR).focus(setOnlyNum).keyup(function(){
			var thisPdNum = parseInt($(this).val(), 10);
			if(isNaN(thisPdNum)){
				thisPdNum = 0;
				$(this).val(thisPdNum);//Chrome error
			}
			var thisPrice = parseFloat($("#price", lastTR).val());
			if (isNaN(thisPrice)) {
				thisPrice = 0;
			}

			var pdStockNum = parseInt($("#pdStocksNum", lastTR).val(), 10);
			if (thisPdNum > pdStockNum) {
				alert("您输入的商品数量大于库存，请重新输入！");
				thisPdNum = 1;
				$(this).val(1);
			}
			
			$("#pdAmount", lastTR).text((thisPdNum*thisPrice).toFixed(2));
			//计算合计数量
			getTotalPdNum("counts", $("#showSalesOrderTbody"));
			//计算合计金额
			getTotalPdMoney("pdAmount", $("#showSalesOrderTbody"));
		});
		//修改商品单价keyup事件
		$("#price", lastTR).focus(setOnlyNum1).keyup(function(){
			var thisPrice = parseFloat($(this).val());
			if (isNaN(thisPrice)) {
				thisPrice = 0;
			}
			var price_max = parseFloat($("#price_max", $(this).parent().parent()).val());
			var price_min = parseFloat($("#price_min", $(this).parent().parent()).val());
			if (thisPrice > price_max) {
				alert("您输入的单价大于该商品的价格上限 〖￥" + price_max + "〗！");
				$(this).val(0);
				$("#pdAmount", lastTR).text(0.00);
				//计算合计金额
				getTotalPdMoney("pdAmount", $("#showSalesOrderTbody"));
				return false;
			}
			//if (thisPrice < price_min) {
			//	alert("您输入的单价小于该商品的价格下限 〖￥" + price_min + "〗！");
			//	return false;
			//}
			
			var thisPdNum = parseInt($("#counts", lastTR).val(), 10);
			if (isNaN(thisPdNum)) {
				thisPdNum = 0;
			}
			$("#pdAmount", lastTR).text((thisPdNum*thisPrice).toFixed(2));
			//计算合计金额
			getTotalPdMoney("pdAmount", $("#showSalesOrderTbody"));
		});

		//选择商品事件
		$("#pdIdList", lastTR).multiselect({
			noneSelectedText: '==请选择==',
			selectedList: 1,
			multiple: false,
			minWidth:220,
			click: function(event, ui){
		       if(ui.value != ""){
			       $("#counts", lastTR).removeAttr("disabled").val(1);
			       $("#price", lastTR).removeAttr("disabled");
			       
			       s = ui.value.split("[#####]");
			       var dept_pd_id = s[0];
			       var pd_cls = s[1];
			       var pd_cls_name = s[2];
			       var md_name = s[3];
			       var pd_type = s[4];
			       var price_ref = s[5];
			       var price_max = s[6];
			       var price_min = s[7];
			       $("#pdIds", lastTR).val(dept_pd_id); //分公司产品ID
			       if (judgeSelectedValueIsRepeat("pdIds", $("#showSalesOrderTbody"))) {
				        alert("商品选择有重复，请重新选择！");
				        return false;
				   }
			       
				   //alert(md_name);
				   //alert(replaceAll(md_name, '%', '%25'));
				   lastTR.children().eq(4).empty().append('<img id="loading" src="${ctx}/images/loading.gif" />');
				   $("body").everyTime("1ms", "clock", function(i){ 
						if (i <= 199) {
							//$("#loading").show();
						} else {
						   //查询库存Begin
						   var flag = getPdStocks("${af.map.zmd_id}", md_name, lastTR);
					       if (!flag) {
						       alert("抱歉，查询库存失败！");
						       $("#md_name", lastTR).val(""); //产品型号
							   $("#pd_type", lastTR).val(""); //产品类型
							   $("#pd_cls", lastTR).val(""); //产品类别ID
						       $("#pd_cls_name", lastTR).val(""); //产品类别
						       $("#price", lastTR).val(""); //产品参考价
						       $("#price_max", lastTR).val(""); //产品价格上限
						       $("#price_min", lastTR).val(""); //产品价格下限
						       $("#pdAmount", lastTR).text(""); //产品金额
						       $("#ck", lastTR).val("");//发货分配库位
						       //$("#allAmount").text("0.00");
						       //$("#total_money").val("0.00");
						       return false;	
						   }
					       //查询库存End
						}		
				   }, 200);

				   $("#md_name", lastTR).val(md_name); //产品型号
				   $("#pd_type", lastTR).val(pd_type); //产品类型
			       lastTR.children().eq(2).text(pd_cls_name);
			       $("#pd_cls", lastTR).val(pd_cls); //产品类别ID
			       $("#pd_cls_name", lastTR).val(pd_cls_name); //产品类别
			       $("#price", lastTR).val(price_ref); //产品参考价
			       $("#price_max", lastTR).val(price_max); //产品价格上限
			       $("#price_min", lastTR).val(price_min); //产品价格下限
			       var thisPdNum = parseInt($("#counts", lastTR).val(), 10);
			       var thisPrice = parseFloat($("#price", lastTR).val());
			       var thisMoney = (thisPdNum * thisPrice).toFixed(2);
			       $("#pdAmount", lastTR).text(thisMoney); //产品金额
			       //计算合计数量
				   getTotalPdNum("counts", $("#showSalesOrderTbody"));
			       //计算合计金额
			       getTotalPdMoney("pdAmount", $("#showSalesOrderTbody"));
			       //添加发货按钮
				   lastTR.children().eq(8).empty().append('<button type="button" id="search_btn">仓库</button>');
				   $("#search_btn", lastTR).button(
						   {icons: {primary: "ui-icon-search"//, secondary: "ui-icon-triangle-1-s"
							       }
					       }
				   ).click(function(){
					   openDeliveryDiv(md_name, pd_cls_name, lastTR);
				   });
				   
				   $("#ck", lastTR).attr("dataType", "Require").attr("msg", "请选择产品【" + md_name + "】发货库位！").val("");

				   
		       }
			}
		}).multiselectfilter();//.attr("datatype", "Ms").attr("msg", "请选择商品！");
		
		
		$("td:last", lastTR).click(function (){
			task_index--;
			//alert("task_index = " + task_index);
			$("#md_name", $(this).parent()).removeAttr("dataType");
			$("#counts", $(this).parent()).removeAttr("dataType");
			$("#price", $(this).parent()).removeAttr("dataType");
			$(this).parent().remove();
			//处理删除字符
			$("#goods_title").empty().text("添加商品：");
			var i = 1;
			$("tr", "#showSalesOrderTbody").each(function(){
				if (i <= task_index) {
					$(this).find("td").eq(0).text(i);
					i++;
				}
			});
			//计算合计数量
			getTotalPdNum("counts", $("#showSalesOrderTbody"));
			//计算合计金额
			getTotalPdMoney("pdAmount", $("#showSalesOrderTbody"));
	    }).css("cursor", "pointer");

	});

	//选择付款方式
	$("input[type='radio'][name='pay_way']").click(function(){
		//alert($(this).val());
		if (50300 == $(this).val()) { //预收订金
			$("#money_of_deposit_tr").show();
			$("#money_of_deposit").attr("dataType", "Require").attr("msg", "请填写定金！");
		} else {
			$("#money_of_deposit").removeAttr("dataType");
			$("#money_of_deposit_tr").hide();
		}
	});

	//提交表单
	$("#btn_submit").click(function(){
		if ($(".pdIds").length <= 1) {
			alert("请至少添加一个商品！");
			return false;
		}

		//提交表单验证购买商品数量Begin
		var flag = false;
		var flag1 = false;
		var flag2 = false;
		var mdName = "";
		var obj;
		$("input[type='text'][name='counts']", "#showSalesOrderTbody").each(function(){
			var n = $("#md_name", $(this).parent().parent()).val();
			obj = $(this).parent().parent();
			//判断购买商品数量是否存在0
			var count = parseInt($(this).val(), 10);
			if (isNaN(count)) {
				count = 0;
			}
			if (count == 0) {
				if (n != "" && n != undefined) {
					mdName = mdName.concat("〖").concat(n).concat("〗");
				}
				flag = true;
				return false;
			}
			//判断是否已查询库存
			var pdStockNum = parseInt($("#pdStocksNum", $(this).parent().parent()).val(), 10);
			if (isNaN(pdStockNum)) {
				flag1 = true;
				return false;
			}
			//判断购买的商品数量是否大于库存
			if (count > pdStockNum) {
				flag2 = true;
				return false;
			}
		});
		if (flag) {
			alert("您添加的商品" + mdName +"数量为 0 ，请输入购买数量！");
			$("#counts", obj).focus();
			return false;
		}
		if (flag1) {
			alert("请选择商品查询库存！");
			return false;
		}
		if (flag2) {
			alert("您输入的商品超过库存量");
			$("#counts", obj).focus();
			return false;
		}
		//提交表单验证购买商品数量End

		//提交表单验商品单价上限下限Begin
		var flag3 = false;
		var flag4 = false;
		var price_max;
		var price_min;
		var mdName = "";
		var obj;
		$("input[type='text'][name='price']", "#showSalesOrderTbody").each(function(){
			mdName = "";
			var n = $("#md_name", $(this).parent().parent()).val();
			obj = $(this).parent().parent();
			price_max = parseFloat($("#price_max", $(this).parent().parent()).val());
			price_min = parseFloat($("#price_min", $(this).parent().parent()).val());
			var	price = parseFloat($(this).val());

			if (n != "" && n != undefined) {
				mdName = mdName.concat("〖").concat(n).concat("〗");
			}
			if (price < price_min) {
				flag3 = true;
				return false;
			}
			if (price > price_max) {
				flag4 = true;
				return false;
			}
		});
		if (flag3) {
			alert("您销售的商品" + mdName + "低于该商品的价格下限" + price_min + "￥");
			$("#price", obj).focus();
			return false;
		}
		if (flag4) {
			alert("您销售的商品" + mdName + "高于该商品的价格上限" + price_max + "￥");
			$("#price", obj).focus();
			return false;
		}
		//提交表单验商品单价上限下限End
		
		//验证发货分配库位数量和客户购买数量是否一致Begin
		var flag5 = false;
		var flag6 = false;
		var mdName = "";
		var obj;
		$("#ck", "#showSalesOrderTbody").each(function(){
			//alert($(this).val());
			var s1 = $(this).val();
			mdName = $("#md_name", $(this).parent().parent()).val();
			obj = $(this).parent().parent();
			if (s1 == "" || s1 == undefined) {
				flag5 = true;
				return false;
			} else {
				var arr = new Array();
				arr = s1.split("[@@@@@]");
				//alert("arr.length =" + arr.length);
				var n = 0;
				for ( var i = 0; i < arr.length; i++) {
					var arrs = new Array();
					arrs = arr[i].split("[#####]");
					//alert("arrs[0] = " + arrs[0]);
					if (arrs[0] == "" || arrs[0] == undefined) {
						arrs[0] = 0;
					}
					n = n + parseInt(arrs[0], 10);
				}
				//alert("n = " + n);
				var counts = parseInt($("#counts", obj).val(), 10);
				//alert("counts = " + counts);
				if (n != counts) {
					flag6 = true;
					return false;
				}
			}
		});

		if (flag5) {
			alert("您销售的商品〖" + mdName + "〗还未发货分配库位");
			$("#search_btn", $(obj)).focus();
			return false;
		}
		if (flag6) {
			alert("您销售的商品〖" + mdName + "〗更改了销售数量，请重新发货分配库位");
			$("#search_btn", $(obj)).focus();
			return false;
		}
		//验证发货分配库位数量和客户购买数量是否一致End
		
		if(Validator.Validate(this.form, 1)){
			if(confirm("确定要提交表单？")){
	            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
	            $("#reset").attr("disabled", "true");
	            $("#btn_back").attr("disabled", "true");
				this.form.submit();
			} else {
				return false;
			}
		}
	});
	
});

//计算合计数量
function getTotalPdNum(objId, parObj){
	var totalNum = 0;
	$("#" + objId, parObj).each(function(){
		var num = parseInt($(this).val(), 10);
		if (isNaN(num)) {
			num = 0;
		}
		totalNum = totalNum + num;
	});
	$("#total").children().eq(2).text(totalNum.toString());
}

//计算合计金额
function getTotalPdMoney(objId, parObj){
	var totalMoney = 0;
	$("#" + objId, parObj).each(function(){
		var money = parseFloat($(this).text());
		if (isNaN(money)) {
			money = 0;
		}
		totalMoney = totalMoney + money;
	});
	$("#allAmount").text(totalMoney.toFixed(2));
	$("#total_money").val(totalMoney.toFixed(2));
}

function getPdStocks(zmd_id, md_name, parObj) {
	//alert(md_name);
	var flag;
	//$(parObj).children().eq(4).append('<img id="loading" src="${ctx}/images/loading.gif" />');
	$("#loading", parObj).ajaxStart(function(){$(this).show();});	
	$("#loading", parObj).ajaxStop(function(){$(this).hide();});	
	$.ajax({
    	type: "POST" , 
        url: 'KonkaXxZmdAddSalesOrder.do' , 
        data: "method=getPdStocks&zmd_id=" + zmd_id + "&md_name=" + escape(encodeURIComponent(md_name)) + "&n=" + Math.random(), 
        dataType: "json" , 
        async: false, 
        error: function (request, settings) {alert(" 数据加载请求失败！ "); }, 
        success: function (result) {
            if ("" == result[0].pdStocksNum || undefined == result[0].pdStocksNum) {
				flag = false;
			} else {
            	$("#pdStocksNum", parObj).val(result[0].pdStocksNum);
            	parObj.children().eq(4).text(result[0].pdStocksNum);
            	$("#total").children().eq(1).empty().append('型号为〖<span style="color:#6699ff;">' + md_name + '</span>〗的商品库存为：<span style="color:green;font-weight:bold;">' + result[0].pdStocksNum + '</span>');
            	flag = true;
			}
        }
	}); 
	return flag;
}

function openDeliveryDiv(md_name, pd_cls_name, parObj){
	$("#tbody_div").empty();
	var counts = parseInt($("#counts", parObj).val(), 10);
	var str = $("#ck", parObj).val();
	
	$("#newDeliveryDiv").dialog({
	      title: '选择仓库', 
	      width: 600,
	      height: 350,
	      draggable: true,
	      resizable: false,
	      position:'center',
	      modal : true,
	      open: function(event, ui) {
			  $("body").everyTime("1ms", "clock", function(i){ 
				if (i <= 199) {
					$("#loadingDiv").show();
				} else {
					getPdStoresList(md_name, counts, str, parObj);;
				}		
			  }, 200);
	      },
	      close: function(event, ui) {
	    	  $("#loadingDiv").show();
		  },
	      buttons: {"确认": function() {
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
									} else if (dts_num < counts) {
										alert("您出货的数量小于客户购买量！");
										return false;
									} else if (dts_num > counts) {
										alert("您出货的数量大于客户购买量！");
										return false;
									}

									if(confirm("确定该仓位选择？")){
										//this.form.submit();
										/**出库信息数据封装
										 * [@@@@@]出库数量[#####]工厂ID[#####]仓库ID[#####]仓库名称[@@@@@]出库数量[#####]工厂ID[#####]仓库ID[#####]仓库名称........
										 */
										var ss = "";
										var s = "";
										$("#dts_counts", "#stores_tbody").each(function(){
											if ($(this).val() != 0) {
												var dts_counts = $(this).val().toString();
												var factory_id = $("#factory_id", $(this).parent().parent()).val().toString();
												var store_id = $("#store_id", $(this).parent().parent()).val().toString();
												var store_desc = $("#store_desc", $(this).parent().parent()).val().toString();
												s = dts_counts + "[#####]" + factory_id + "[#####]" + store_id + "[#####]" + store_desc;
												ss = ss + s + "[@@@@@]";
											}
										});
										//alert(ss);
										$("#md_name", "#showSalesOrderTbody").each(function(){
											if ($(this).val() == md_name) {
												$("#ck", parObj).val(ss);
												//$("#gift", $(this).parent().parent().children().eq(3)).val(ss);
											}
										});
										//$("#ck", "#showSalesOrderTbody").each(function(){
										//	alert("$('#ck').val()=" + $(this).val());
										//});
										$(this).dialog("close");
									} else {
										return false;
									}
	      						},
					"关闭": function() {
	      						$(this).dialog("close");
	      					}
				   }
	}).dialog("open");
}

function getPdStoresList(md_name, counts, str, parObj){
	$("#loadingDiv").ajaxStart( function (){$( this ).show();}); 
    $("#loadingDiv").ajaxStop ( function (){$( this ).hide();});
    $.ajax({
    	type: "POST" , 
        url: 'KonkaXxZmdAddSalesOrder.do' , 
        data: "method=getPdStoresList&zmd_id=${af.map.zmd_id}&md_name=" + escape(encodeURIComponent(md_name)) + "&n=" + Math.random(), 
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
												    				'<strong class="fb">分公司：</strong>' +
												    			'</td>' +
												    			'<td width="35%" align="left">${af.map.dept_name}</td>' +
												    			'<td width="15%" class="title_item" nowrap="nowrap" align="right">' +
												    				'<strong class="fb">专卖店编号：</strong>' +
												    			'</td>' +
												    			'<td align="left">${af.map.zmd_sn}</td>' +
					        						        '</tr>' +
						        						    '<tr>' +
							        						    '<td width="15%" class="title_item" nowrap="nowrap" align="right">' +
												    				'<strong class="fb">商品型号：</strong>' +
												    			'</td>' +
												    			'<td width="35%" align="left"><span class="fblue">' + md_name + '</span></td>' +
												    			'<td width="15%" class="title_item" nowrap="nowrap" align="right">' +
												    				'<strong class="fb">客户购买数量：</strong>' +
												    			'</td>' +
												    			'<td align="left">' + counts + '</td>' +
					        						        '</tr>' +
					        						    '</table>' +
					        						//'</div>' +  
												'</td>' +
											'</tr>' +
										'</table>'+
										'<div style="width:95%;color:#FF0000">注：产品库存取自R3系统，需要R3管理员进行库存同步操作，因此库存存在延时现象。请及时通知R3系统管理员作库存同步操作！</div>' +
								  		'<table width="95%" border="0" cellspacing="0" cellpadding="0" class="rtable2" style="margin-top:5px;">' +
									        '<tr class="tabtt1">' +
									          '<td width="3%" align="center" nowrap="nowrap">序号</td>' +
									          '<td width="15%" align="center" nowrap="nowrap">工厂</td>' +
									          '<td width="15%" align="center" nowrap="nowrap">仓位编号</td>' +
									          '<td width="15%" align="center" nowrap="nowrap">仓位名称</td>' +
									          '<td width="15%" align="center" nowrap="nowrap">库存</td>' +
									          '<td width="7%" align="center" nowrap="nowrap">出库数量</td>' +
									          //'<td width="30%" align="center" nowrap="nowrap">出库备注</td>' +
									        '</tr>' +
						                    '<tbody id="stores_tbody"></tbody>' +
					 					'</table>' +
					 			   '</div>');
			if (result[0].count == 0) {
				$("#stores_tbody").append('<tr>' +
                			  				'<td colspan="8" style="color:red" align="center">没有查到相关仓库信息</td>' +
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
	                            '<td align="left" nowrap="nowrap"><input type="text" name="dts_counts" id="dts_counts" class="dts_counts" size="5" maxlength="10" value="0" /></td>' +
	                            //'<td align="left" nowrap="nowrap"><input type="text" name="dst_remarks" id="dst_remarks" size="20" maxlength="40" /></td>' +
	                          '</tr>');
					}
	
					$(".dts_counts").each(function(){
						$(this).focus(setOnlyNum).keyup(function(){
							var thisDtsCounts = parseInt($(this).val(), 10);
							if (isNaN(thisDtsCounts)) {
								thisDtsCounts = 0;
								$(this).val(thisDtsCounts);//Chrome error
							}
							if (thisDtsCounts > counts) {
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
			//回显已分配的仓位信息
			//[@@@@@]出库数量[#####]工厂ID[#####]仓库ID[#####]仓库名称[@@@@@]出库数量[#####]工厂ID[#####]仓库ID[#####]仓库名称........
			if ("" != str) {
				var total_arr = new Array(); 
				total_arr = str.split("[@@@@@]");
				var last_counts = 0;
				for ( var i = 0; i < total_arr.length - 1; i++) {
					var arr = total_arr[i].split("[#####]");
					last_counts = last_counts + parseInt(arr[0], 10);
				}
				//alert("counts = " + counts + "_______" + "last_counts =" + last_counts);
				if (counts == last_counts) {
					//alert("相同");
					for ( var i = 0; i < total_arr.length - 1; i++) {
						var arr = total_arr[i].split("[#####]");
						var num = arr[0];
						var factory_id = arr[1];
						var store_id = arr[2];
						$("tr", "#stores_tbody").each(function(){
							if ($("#factory_id", $(this)).val() == factory_id && $("#store_id", $(this)).val() == store_id) {
								$("#dts_counts", $(this)).val(num);
							}
						});
					}
					return;
				} else {
					//alert("不相同");
					$("#ck", parObj).val("");
					$.jGrowl("您更改了产品[" + md_name + "]购买数量，请重新分配库位！", {theme:'success', life: 4500, header: '提示'});
				}
			}
        }
    }); 
}

function getBuyerInfo(buyerId){
	if ("" != buyerId) {
		if (isIdCard(buyerId)) {
			$.ajax({
				type: "POST" , 
		        url: 'KonkaXxZmdAddSalesOrder.do' , 
		        data: "method=getBuyerInfo&zmd_id=${af.map.zmd_id}" + "&buyer_id=" + buyerId + "&n=" + Math.random(), 
		        dataType: "json" , 
		        //async: false, 
		        error: function (request, settings) {alert(" 数据加载请求失败！ "); }, 
		        success: function (result) {
					if (result.count == 1) {
						$("#buyer_name").val(result.buyer_name);
						$("#buyer_sex").val(result.buyer_sex);
						$("#buyer_birthday").val(result.buyer_birthday);
						$("#buyer_tel").val(result.buyer_tel);
						$("#buyer_phone").val(result.buyer_phone);
						$("#buyer_link_addr").val(result.buyer_link_addr);
						//回显客户所在区域
						$("#buyer_province").val(result.buyer_province).change();
						$("#buyer_city").val(result.buyer_city).change();
						$("#buyer_country").val(result.buyer_country).change();
						$("#buyer_town").val(result.buyer_town);
					}
		        }
			});
		} else {
			alert("请填写正确的身份证号！");
			$("#buyer_id").focus();
		}
	}
}

function isIdCard(number){
    var date, Ai;  
    var verify = "10X98765432";  
    var Wi = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];  
    var area = ['','','','','','','','','','','','北京','天津','河北','山西','内蒙古','','','','','','辽宁','吉林','黑龙江','','','','','','','','上海','江苏','浙江','安微','福建','江西','山东','','','','河南','湖北','湖南','广东','广西','海南','','','','重庆','四川','贵州','云南','西藏','','','','','','','陕西','甘肃','青海','宁夏','新疆','','','','','','台湾','','','','','','','','','','香港','澳门','','','','','','','','','国外'];  
    var re = number.match(/^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}[xX0-9])))$/i);
    if(re == null) return false;
    if(re[1] >= area.length || area[re[1]] == "") return false;  
    if(re[2].length == 12){  
        Ai = number.substr(0, 17);  
        date = [re[9], re[10], re[11]].join("-");  
    }  
    else{  
        Ai = number.substr(0, 6) + "19" + number.substr(6);  
        date = ["19" + re[4], re[5], re[6]].join("-");  
    }  
    if(!this.IsDate(date, "ymd")) return false;
    var sum = 0;
    for(var i = 0;i<=16;i++){
        sum += Ai.charAt(i) * Wi[i];
    }
    Ai +=  verify.charAt(sum%11);  
    return (number.length ==15 || number.length == 18 && number.toUpperCase() == Ai); 
}

function IsDate(op, formatString){
	formatString = formatString || "ymd";
	var m, year, month, day;
	switch(formatString){
		case "ymd" :
			m = op.match(new RegExp("^((\\d{4})|(\\d{2}))([-./])(\\d{1,2})\\4(\\d{1,2})$"));
			if(m == null ) return false;
			day = m[6];
			month = m[5]*1;
			year =  (m[2].length == 4) ? m[2] : GetFullYear(parseInt(m[3], 10));
			break;
		case "dmy" :
			m = op.match(new RegExp("^(\\d{1,2})([-./])(\\d{1,2})\\2((\\d{4})|(\\d{2}))$"));
			if(m == null ) return false;
			day = m[1];
			month = m[3]*1;
			year = (m[5].length == 4) ? m[5] : GetFullYear(parseInt(m[6], 10));
			break;
		default :
			break;
	}
	if(!parseInt(month)) return false;
	month = month==0 ?12:month;
	var date = new Date(year, month-1, day);
    return (typeof(date) == "object" && year == date.getFullYear() && month == (date.getMonth()+1) && day == date.getDate());
	function GetFullYear(y){return ((y<30 ? "20" : "19") + y)|0;}
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
		if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;
}

/**
 * @name replaceAll JS用于替换一个字符串中对应的所有的字符串
 * @param String  initStr 原字符串。
 * @param String  rstr  要转化的字符串。
 * @param String  rs  转换成的字符串
 * @return String str;
 */
function replaceAll(initStr,rstr,rs){ 
 	str='';
  	while(initStr.indexOf(rstr)!=-1){
	   k=initStr.indexOf(rstr);
	   initStr=initStr.replace(rstr,rs);
	   str+=initStr.substr(0,k+rs.length);
	   initStr=initStr.substr(k+rs.length);
  	}
  	str+=initStr;
 	return str;
}

//]]>--></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>