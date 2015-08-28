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
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/fancybox/fancybox.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/colorbox.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	.tabtt1 td{background-color:#eff;}
</style>
<style type="text/css">
	select{font-family:Microsoft YAHEI;font-size:12px;}
	input{font-family:Microsoft YAHEI;font-size:12px;}
label {cursor:pointer;}
</style>
<style type="text/css">
	a:link, a:visited{text-decoration:none; color:#416CE5; border-bottom:1px solid #416CE5;}
	h2{font-size:13px; margin:15px 0 0 0;}
</style>
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
   <html-el:form action="/KonkaR3ShopStock.do">
   <html-el:hidden property="method" value="view" />
  <html-el:hidden property="mod_id" styleId="mod_id" />
  <html-el:hidden property="cust_id" styleId="cust_id" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td width="280">
          	<strong class="fb">客户名称：</strong>${konkaR3Shop.customer_name}
          </td>
          <td width="280">
          	<strong class="fb">客户R3编码：</strong>${konkaR3Shop.r3_code}
          </td>
          <td width="280">
			<strong class="fb">仓库：</strong>
	          <html-el:select property="store_id" styleId="store_id">
	        		<html-el:option value="">请选择</html-el:option>
	          		<c:forEach var="cur" items="${jBaseStoreList}" varStatus="vs">
	          			<html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
	          		</c:forEach>
	            </html-el:select>
	     </td>
		<td width="280">
			<strong class="fb">型号：</strong>
	          <html-el:select property="goods_id" styleId="goods_id">
	        		<html-el:option value="">请选择</html-el:option>
	          		<c:forEach var="cur" items="${jBaseGoodsList}" varStatus="vs">
	          			<html-el:option value="${cur.goods_id}">${cur.goods_name}</html-el:option>
	          		</c:forEach>
	            </html-el:select>
	     </td>
	     <td>
	     <input name="button" type="button" class="bgSearch" id="btn_submit" value="搜 索" />
          <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
          <input class="but_excel" type="button" name="export_excel" id="export_excel" value="导出" onclick="toExcel('divExcel_all', '?method=toExcel');"/>
          <label style="float:right;" for="filter_nulls"><input type="checkbox" name="filter_nulls" id="filter_nulls" checked="checked" /> 过滤无效数据&nbsp;</label></td>
        </tr>
      </table>
      </html-el:form>
  </div>
  <div class="rtabcont1">
      <div style="text-align:right;">单位：台、元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" align="center" nowrap="nowrap" rowspan="2">序号</td>
          <td width="15%" align="center" nowrap="nowrap" rowspan="2">型号名称</td>
          <td align="center" nowrap="nowrap" colspan="3">盘存（<span>总库</span>）</td>
          <td align="center" nowrap="nowrap" colspan="2">进</td>
          <td align="center" nowrap="nowrap" colspan="3">销</td>
          <td width="10%" align="center" nowrap="nowrap" rowspan="2">剩余库存量</td>
          <td width="15%" align="center" nowrap="nowrap" rowspan="2">剩余库存金额</td>
         </tr>
         <tr class="tabtt1">
          <td width="8%" align="center" nowrap="nowrap">期初库存</td>
          <td width="8%" align="center" nowrap="nowrap">期初金额</td>
          <td width="8%" align="center" nowrap="nowrap">盘存时间</td>
          <td width="8%" align="center" nowrap="nowrap">进货数量</td>
          <td width="8%" align="center" nowrap="nowrap">进货金额</td>
          <td width="8%" align="center" nowrap="nowrap">销售数量</td>
          <td width="8%" align="center" nowrap="nowrap">销售成本</td>
          <td width="8%" align="center" nowrap="nowrap">销售金额</td>
         </tr>
          <c:set var="s_init_num" value="0"></c:set>
          <c:set var="s_init_money" value="0"></c:set>
          <c:set var="s_come_num" value="0"></c:set>
          <c:set var="s_come_money" value="0"></c:set>
          <c:set var="s_out_num" value="0"></c:set>
          <c:set var="s_sale_cost" value="0"></c:set>
          <c:set var="s_out_money" value="0"></c:set>
          <c:set var="s_result_money" value="0"></c:set>
          <c:set var="s_result_num" value="0"></c:set>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
        	<c:set var="s_init_num" value="${s_init_num+ cur.map.init_num}"></c:set>
	          <c:set var="s_init_money" value="${s_init_money+ cur.map.init_money}"></c:set>
	          <c:set var="s_come_num" value="${s_come_num+ cur.map.come_num}"></c:set>
	          <c:set var="s_come_money" value="${s_come_money+ cur.map.come_money}"></c:set>
	          <c:set var="s_out_num" value="${s_out_num+ cur.map.out_num}"></c:set>
	          <c:set var="s_sale_cost" value="${s_sale_cost+ cur.map.sale_cost}"></c:set>
	          <c:set var="s_out_money" value="${s_out_money+ cur.map.out_money}"></c:set>
	          <c:set var="s_result_money" value="${s_result_money+ cur.map.init_money + cur.map.come_money - cur.map.sale_cost}"></c:set>
	          <c:set var="s_result_num" value="${s_result_num+ cur.map.init_num + cur.map.come_num - cur.map.out_num}"></c:set>
        
          <tr class="tr_${cur.map.init_num}${cur.map.come_num}${cur.map.out_num} sortable">
            <td align="center" nowrap="nowrap">${vs.count }</td>
            <td align="left" nowrap="nowrap">${cur.map.goods_name}</td>
            <td align="right" nowrap="nowrap">${cur.map.init_num}</td>
            <td align="right" nowrap="nowrap"><span class="kz-price-12"><fmt:formatNumber value="${cur.map.init_money}" type="currency" /></span></td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.map.opr_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
            <td align="right" nowrap="nowrap">
<%-- 	            <span style="cursor:pointer;" class="fblue" onclick="openDialog('${af.map.mod_id}','${af.map.store_id}','${cur.map.goods_id}','${af.map.cust_id }',0)">${cur.map.come_num}</span> --%>
	            <a class='iframea' href="${ctx}/webservice/KonkaR3ShopStock.do?method=viewDetail&mod_id=${af.map.mod_id}&store_id=${af.map.store_id}&goods_id=${cur.map.goods_id}&cust_id=${af.map.cust_id }&direction=0">${cur.map.come_num}</a>
            </td>
            <td align="right" nowrap="nowrap"><span class="kz-price-12"><fmt:formatNumber value="${cur.map.come_money}" type="currency" /></span></td>
            <td align="right" nowrap="nowrap">
<%-- 	            <span style="cursor:pointer;" class="fblue" onclick="openDialog('${af.map.mod_id}','${af.map.store_id}','${cur.map.goods_id}','${af.map.cust_id }',1)">${cur.map.out_num}</span> --%>
	            <a class='iframea' href="${ctx}/webservice/KonkaR3ShopStock.do?method=viewDetail&mod_id=${af.map.mod_id}&store_id=${af.map.store_id}&goods_id=${cur.map.goods_id}&cust_id=${af.map.cust_id }&direction=1">${cur.map.out_num}</a>
            </td>
            <td align="right" nowrap="nowrap"><span class="kz-price-12"><fmt:formatNumber value="${cur.map.sale_cost}" type="currency" /></span></td>
            <td align="right" nowrap="nowrap"><span class="kz-price-12"><fmt:formatNumber value="${cur.map.out_money}" type="currency" /></span></td>
          	<td align="right" nowrap="nowrap"><span style="font-size:150%;font-weight:800;font-family:verdana;color:#C00;">${cur.map.init_num + cur.map.come_num - cur.map.out_num}</span></td>
            <td align="right" nowrap="nowrap"><span class="kz-price-12">
          		<fmt:formatNumber value="${cur.map.init_money + cur.map.come_money - cur.map.sale_cost}" type="currency" />
          	</span></td>
          </tr>
        </c:forEach>
         <tr>
          <td align="center" nowrap="nowrap" style="font-weight:800;">合计</td>
            <td align="left" nowrap="nowrap" style="font-weight:800;"></td>
            <td align="right" nowrap="nowrap" style="font-weight:800;">${s_init_num}</td>
            <td align="right" nowrap="nowrap" style="font-weight:800;"><span class="kz-price-12"><fmt:formatNumber value="${s_init_money}" type="currency" /></span></td>
            <td align="center" nowrap="nowrap" style="font-weight:800;"></td>
            <td align="right" nowrap="nowrap" style="font-weight:800;">${s_come_num}</td>
            <td align="right" nowrap="nowrap" style="font-weight:800;"><span class="kz-price-12"><fmt:formatNumber value="${s_come_money}" type="currency" /></span></td>
            <td align="right" nowrap="nowrap" style="font-weight:800;">${s_out_num}</td>
            <td align="right" nowrap="nowrap" style="font-weight:800;"><span class="kz-price-12"><fmt:formatNumber value="${s_sale_cost}" type="currency" /></span></td>
            <td align="right" nowrap="nowrap" style="font-weight:800;"><span class="kz-price-12"><fmt:formatNumber value="${s_out_money}" type="currency" /></span></td>
          	<td align="right" nowrap="nowrap" style="font-weight:800;"><span style="font-size:150%;font-weight:800;font-family:verdana;color:#C00;">${s_result_num}</span></td>
          	<td align="right" nowrap="nowrap" style="font-weight:800;"><span class="kz-price-12"><fmt:formatNumber value="${s_result_money}" type="currency" /></span></td>
         </tr>
      </table>
      <div style="color:#555;margin-top:20px;">
      注1：产品需要进行<a href="${ctx}/customer/manager/KonkaStockInventory.do?mod_id=115020000" style="color:#F00;text-decoration:underline;">库存初始化/库存盘点</a>后方可查询库存；<br />
      注2：“期初库存”即最近一次的盘点库存，默认采用客户“总库”（为客户仓库名称）作为产品的盘点仓库；<br />    
      注3：“进货数量”统计自康佳R3系统分公司已发货订单记录；<br />    
      注4：“销售数量”统计自客户/促销员通过手机端上报的零售数据、通过WEB端录入的销售、分销数据等；<br />    
      </div>
  </div>
   
    <div class="rtabcont1" style="overflow-x: auto; display: none;" id="divExcel_all" title="客户实时库存">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
         <tr class="tabtt1">
          <td width="5%" align="center" nowrap="nowrap" rowspan="2">序号</td>
          <td align="center" nowrap="nowrap" rowspan="2">型号名称</td>
          <td align="center" nowrap="nowrap" colspan="3">盘存</td>
          <td align="center" nowrap="nowrap" colspan="2">进</td>
          <td align="center" nowrap="nowrap" colspan="3">销</td>
          <td width="10%" align="center" nowrap="nowrap" rowspan="2">剩余库存量</td>
          <td width="15%" align="center" nowrap="nowrap" rowspan="2">剩余库存金额</td>
         </tr>
         <tr class="tabtt1">
          <td width="10%" align="center" nowrap="nowrap">期初库存</td>
          <td width="10%" align="center" nowrap="nowrap">期初金额</td>
          <td width="10%" align="center" nowrap="nowrap">盘存时间</td>
          <td width="10%" align="center" nowrap="nowrap">进货数量</td>
          <td width="10%" align="center" nowrap="nowrap">进货金额</td>
          <td width="10%" align="center" nowrap="nowrap">销售数量</td>
          <td width="10%" align="center" nowrap="nowrap">销售成本</td>
          <td width="10%" align="center" nowrap="nowrap">销售金额</td>
         </tr>
         <c:set var="e_init_num" value="0"></c:set>
          <c:set var="e_init_money" value="0"></c:set>
          <c:set var="e_come_num" value="0"></c:set>
          <c:set var="e_come_money" value="0"></c:set>
          <c:set var="e_out_num" value="0"></c:set>
          <c:set var="e_sale_cost" value="0"></c:set>
          <c:set var="e_out_money" value="0"></c:set>
          <c:set var="e_result_money" value="0"></c:set>
          <c:set var="e_result_num" value="0"></c:set>
       <c:forEach var="cur" items="${entityList}" varStatus="vs">
       		<c:set var="e_init_num" value="${e_init_num+ cur.map.init_num}"></c:set>
	          <c:set var="e_init_money" value="${e_init_money+ cur.map.init_money}"></c:set>
	          <c:set var="e_come_num" value="${e_come_num+ cur.map.come_num}"></c:set>
	          <c:set var="e_come_money" value="${e_come_money+ cur.map.come_money}"></c:set>
	          <c:set var="e_out_num" value="${e_out_num+ cur.map.out_num}"></c:set>
	          <c:set var="e_sale_cost" value="${e_sale_cost+ cur.map.sale_cost}"></c:set>
	          <c:set var="e_out_money" value="${e_out_money+ cur.map.out_money}"></c:set>
	          <c:set var="e_result_money" value="${e_result_money+ cur.map.init_money + cur.map.come_money - cur.map.sale_cost}"></c:set>
	          <c:set var="e_result_num" value="${e_result_num+ cur.map.init_num + cur.map.come_num - cur.map.out_num}"></c:set>
       
          <tr class="tr_${cur.map.init_num}${cur.map.come_num}${cur.map.out_num} sortable">
            <td align="center" nowrap="nowrap">${vs.count }</td>
            <td align="left" nowrap="nowrap">${cur.map.goods_name}</td>
            <td align="right" nowrap="nowrap">${cur.map.init_num}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.map.init_money}" type="currency" /></td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.map.opr_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
            <td align="right" nowrap="nowrap">${cur.map.come_num}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.map.come_money}" type="currency" /></td>
            <td align="right" nowrap="nowrap">${cur.map.out_num}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.map.sale_cost}" type="currency" /></td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.map.out_money}" type="currency" /></td>
          	<td align="right" nowrap="nowrap">${cur.map.init_num + cur.map.come_num - cur.map.out_num}</td>
            <td align="right" nowrap="nowrap"><span class="kz-price-12">
          		<fmt:formatNumber value="${cur.map.init_money + cur.map.come_money - cur.map.sale_cost}" type="currency" />
          	</span></td>
          </tr>
        </c:forEach>
         <tr>
          <td align="center" nowrap="nowrap" style="font-weight:800;">合计</td>
            <td align="left" nowrap="nowrap" style="font-weight:800;"></td>
            <td align="right" nowrap="nowrap" style="font-weight:800;">${e_init_num}</td>
            <td align="right" nowrap="nowrap" style="font-weight:800;"><span class="kz-price-12"><fmt:formatNumber value="${e_init_money}" type="currency" /></span></td>
            <td align="center" nowrap="nowrap" style="font-weight:800;"></td>
            <td align="right" nowrap="nowrap" style="font-weight:800;">${e_come_num}</td>
            <td align="right" nowrap="nowrap" style="font-weight:800;"><span class="kz-price-12"><fmt:formatNumber value="${e_come_money}" type="currency" /></span></td>
            <td align="right" nowrap="nowrap" style="font-weight:800;">${e_out_num}</td>
            <td align="right" nowrap="nowrap" style="font-weight:800;"><span class="kz-price-12"><fmt:formatNumber value="${e_sale_cost}" type="currency" /></span></td>
            <td align="right" nowrap="nowrap" style="font-weight:800;"><span class="kz-price-12"><fmt:formatNumber value="${e_out_money}" type="currency" /></span></td>
          	<td align="right" nowrap="nowrap" style="font-weight:800;"><span class="kz-price-12">${s_result_num}</span></td>
          	<td align="right" nowrap="nowrap" style="font-weight:800;"><span class="kz-price-12"><fmt:formatNumber value="${e_result_money}" type="currency" /></span></td>
         </tr>
      </table>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/scripts/colorbox/jquery.colorbox.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.jxc.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/external/bgiframe/jquery.bgiframe.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	function filter() {
		if ($("#filter_nulls").is(":checked")) {
			$(".tr_000").hide();
			window.parent.resizeFrameHeight('mainFrame', 3);
		} else {
			$(".tr_000").show();
			window.parent.resizeFrameHeight('mainFrame', 3);
		}
	}
	filter();
	$("#filter_nulls").click(filter);
	
	$("#store_id").multiselect({
		noneSelectedText: '<span >=请选择=</span>',
		selectedList: 1,
		multiple: false,
		minWidth:160
	}).multiselectfilter({label:"<span>搜索：</span>"});
	$("#goods_id").multiselect({
		noneSelectedText: '<span >=请选择=</span>',
		selectedList: 1,
		multiple: false,
		minWidth:160
	}).multiselectfilter({label:"<span>搜索：</span>"});
	
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 1)){
			this.form.submit();
		}
	});
	
	$(".iframea").colorbox({iframe:true, width:"60%", height:"480px"});
	
});

// function openDialog(mod_id,store_id,goods_id,cust_id,direction) {
// 	window.open("${ctx}/manager/admin/KonkaR3ShopStock.do?method=viewDetail&mod_id="+mod_id + "&store_id="+store_id+"&goods_id="+goods_id+"&cust_id="+cust_id +"&direction="+direction+"&azaz=" + Math.random(),window,"dialogWidth:930px;status:no;dialogHeight:600px");
// }

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>