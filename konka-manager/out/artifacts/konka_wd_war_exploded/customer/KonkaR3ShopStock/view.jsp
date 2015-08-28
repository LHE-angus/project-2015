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
<style type="text/css">
	.tabtt1 td{background-color:#eff;}
</style>
<style type="text/css">
	select{font-family:Microsoft YAHEI;font-size:12px;}
	input{font-family:Microsoft YAHEI;font-size:12px;}
label {cursor:pointer;}
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
  <html-el:form action="/manager/KonkaR3ShopStock.do">
  <html-el:hidden property="method" value="view"/>
  <html-el:hidden property="mod_id" styleId="mod_id" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td nowrap="nowrap"></td>
          <td nowrap="nowrap">
          	<strong class="fb">客户名称：</strong>${konkaR3Shop.customer_name}
          </td>
          <td nowrap="nowrap">
          	<strong class="fb">客户R3编码：</strong>${konkaR3Shop.r3_code}
          </td>
		<td nowrap="nowrap">
			<strong class="fb">仓库：</strong>
	          <html-el:select property="store_id" styleId="store_id">
	        		<html-el:option value="">请选择</html-el:option>
	          		<c:forEach var="cur" items="${jBaseStoreList}" varStatus="vs">
	          			<html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
	          		</c:forEach>
	            </html-el:select>
	     </td>
		<td nowrap="nowrap">
			<strong class="fb">型号：</strong>
	          <html-el:select property="goods_id" styleId="goods_id">
	        		<html-el:option value="">请选择</html-el:option>
	          		<c:forEach var="cur" items="${jBaseGoodsList}" varStatus="vs">
	          			<html-el:option value="${cur.goods_id}">${cur.goods_name}</html-el:option>
	          		</c:forEach>
	            </html-el:select>
	     </td>
          <td align="left">
          <input name="button" type="button" class="bgSearch" id="btn_submit" value="搜 索" />
          <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
    		<input type="button" id="export_excel" value="导出" class="but_excel" onclick="toExcel('divExcel_all', '?method=toExcel');" />
    		<label style="float:right;" for="filter_nulls"><input type="checkbox" name="filter_nulls" id="filter_nulls" checked="checked" /> 过滤无效数据&nbsp;</label>
    		</td>
        </tr>
      </table>
  </html-el:form>
  </div>
  <div class="rtabcont1">
      <div style="text-align:right;">单位：台、元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" align="center" nowrap="nowrap" rowspan="2">序号</td>
          <td align="center" nowrap="nowrap" rowspan="2">型号名称</td>
          <td align="center" nowrap="nowrap" colspan="2">盘存</td>
          <td align="center" nowrap="nowrap" colspan="2">进</td>
          <td align="center" nowrap="nowrap" colspan="2">销</td>
          <td width="15%" align="center" nowrap="nowrap" rowspan="2">实时库存</td>
         </tr>
         <tr class="tabtt1">
          <td width="10%" align="center" nowrap="nowrap">期初库存</td>
          <td width="10%" align="center" nowrap="nowrap">盘存时间</td>
          <td width="10%" align="center" nowrap="nowrap">进货数量</td>
          <td width="10%" align="center" nowrap="nowrap">进货金额</td>
          <td width="10%" align="center" nowrap="nowrap">销售数量</td>
          <td width="10%" align="center" nowrap="nowrap">销售金额</td>
         </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr class="tr_${cur.map.stocks}${cur.map.come_num}${cur.map.out_num} sortable">
            <td align="center" nowrap="nowrap">${vs.count }</td>
            <td align="left" nowrap="nowrap">${cur.map.pd_name}</td>
<%--             <td align="right" nowrap="nowrap">${cur.map.initcount}</td> --%>
            <td align="right" nowrap="nowrap">${cur.map.stocks}</td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.map.ts}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
<%--             <td align="right" nowrap="nowrap">${cur.map.incount}</td> --%>
            <td align="right" nowrap="nowrap">${cur.map.come_num}</td>
            <td align="right" nowrap="nowrap"><span class="kz-price-12"><fmt:formatNumber value="${cur.map.inmoney}" type="currency" /></span></td>
<%--             <td align="right" nowrap="nowrap">${cur.map.outcount}</td> --%>
            <td align="right" nowrap="nowrap">${cur.map.out_num}</td>
            <td align="right" nowrap="nowrap"><span class="kz-price-12"><fmt:formatNumber value="${cur.map.outmoney}" type="currency" /></span></td>
          	<td align="right" nowrap="nowrap"><span style="font-size:150%;font-weight:800;font-family:verdana;color:#C00;">${cur.map.stocks + cur.map.come_num - cur.map.out_num}</span></td>
          </tr>
        </c:forEach>
      </table>
      <div style="color:#555;margin-top:20px;">
      注1：产品需要进行
      <a href="${ctx}/customer/manager/JBaseGoodsInitStock.do?mod_id=115030000" style="color:#F00;text-decoration:underline;">初始化库存</a>
      &nbsp;/&nbsp;
      <a href="${ctx}/customer/manager/KonkaStockInventory.do?mod_id=115020000" style="color:#F00;text-decoration:underline;">库存盘点</a>
      后方可查询库存；<br />
     注2：“期初库存”即最近一次的盘点库存，默认采用客户“总库”（为客户仓库名称）作为产品的盘点仓库；<br />    
      注3：“进货数量”统计自康佳R3系统分公司已发货订单记录；<br />    
      注4：“销售数量”统计自客户/促销员通过手机端上报的零售数据、通过WEB端录入的销售、分销数据等；<br />    
      注5：“无效数据”期初库存、进货库存、销售数量都为0的时候，就是作为无效数据处理的；<br />   
      </div>
  </div>
</div>
<div class="rtabcont1" style="overflow-x: auto; display: none;" id="divExcel_all" title="${konkaR3Shop.customer_name}康佳彩电库存明细">
<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="25%">
          	<strong class="fb">客户名称：</strong>${konkaR3Shop.customer_name}
          </td>
          <td width="25%">
          	<strong class="fb">客户R3编码：</strong>${konkaR3Shop.r3_code}
          </td>
        </tr>
      </table>
      <div style="text-align:right;">单位：台、元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" align="center" nowrap="nowrap" rowspan="2">序号</td>
          <td align="center" nowrap="nowrap" rowspan="2">型号名称</td>
          <td align="center" nowrap="nowrap" colspan="2">盘存（<span>总库</span>）</td>
          <td align="center" nowrap="nowrap" colspan="2">进</td>
          <td align="center" nowrap="nowrap" colspan="2">销</td>
          <td width="15%" align="center" nowrap="nowrap" rowspan="2">实时库存</td>
         </tr>
         <tr class="tabtt1">
          <td width="10%" align="center" nowrap="nowrap">期初库存</td>
          <td width="10%" align="center" nowrap="nowrap">盘存时间</td>
          <td width="10%" align="center" nowrap="nowrap">进货数量</td>
          <td width="10%" align="center" nowrap="nowrap">进货金额</td>
          <td width="10%" align="center" nowrap="nowrap">销售数量</td>
          <td width="10%" align="center" nowrap="nowrap">销售金额</td>
         </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${vs.count }</td>
            <td align="left" nowrap="nowrap">${cur.map.pd_name}</td>
<%--             <td align="right" nowrap="nowrap">${cur.map.initcount}</td> --%>
            <td align="right" nowrap="nowrap">${cur.map.stocks}</td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.map.ts}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
<%--             <td align="right" nowrap="nowrap">${cur.map.incount}</td> --%>
            <td align="right" nowrap="nowrap">${cur.map.come_num}</td>
            <td align="right" nowrap="nowrap"><span class="kz-price-12"><fmt:formatNumber value="${cur.map.inmoney}" type="currency" /></span></td>
<%--             <td align="right" nowrap="nowrap">${cur.map.outcount}</td> --%>
            <td align="right" nowrap="nowrap">${cur.map.out_num}</td>
            <td align="right" nowrap="nowrap"><span class="kz-price-12"><fmt:formatNumber value="${cur.map.outmoney}" type="currency" /></span></td>
          	<td align="right" nowrap="nowrap"><span style="font-size:150%;font-weight:800;font-family:verdana;color:#C00;">${cur.map.stocks + cur.map.come_num - cur.map.out_num}</span></td>
          </tr>
        </c:forEach>
      </table>
      <div style="color:#555;margin-top:20px;">
      注1：产品需要进行
      <a href="${ctx}/customer/manager/JBaseGoodsInitStock.do?mod_id=115030000" style="color:#F00;text-decoration:underline;">初始化库存</a>
      &nbsp;/&nbsp;
      <a href="${ctx}/customer/manager/KonkaStockInventory.do?mod_id=115020000" style="color:#F00;text-decoration:underline;">库存盘点</a>
      后方可查询库存；<br />
      注2：“期初库存”即最近一次的盘点库存，默认采用客户“总库”（为客户仓库名称）作为产品的盘点仓库；<br />    
      注3：“进货数量”统计自康佳R3系统分公司已发货订单记录；<br />    
      注4：“销售数量”统计自客户/促销员通过手机端上报的零售数据、通过WEB端录入的销售、分销数据等；<br />    
      注5：“无效数据”期初库存、进货库存、销量都为0的情况下作为无效数据处理的；<br />   
      </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.jxc.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
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
	
// 	check();
// 	function check(){
// 		if($("#is_sdf").attr("checked")){
// 			$("#div_sdf").show();
// 		} else {
// 			$("#div_sdf").hide();
// 		}
// 	}
	
// 	$("#is_sdf").click(function(){
// 		if($(this).is(":checked")){
// 			$("#div_sdf").show();
// 			$("#song_r3_code").removeAttr("disabled");
// 			$("#song_r3_code").attr("dataType","Require").attr("msg","送达方必须选择！");
// 		} else {
// 			$("#div_sdf").hide();
// 			$("#song_r3_code").attr("disabled","true");
// 			$("#song_r3_code").removeAttr("dataType");
// 		}
// 	});
	
	$("#btn_submit").click(function(){
// 		if($("#is_sdf").attr("checked")){
// 			$("#song_r3_code").attr("dataType","Require").attr("msg","送达方必须选择！");
// 		}
		if(Validator.Validate(this.form, 1)){
			this.form.submit();
		}
	});
});
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>