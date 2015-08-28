<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/fancybox/fancybox.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<style type="text/css">
select{font-family:Microsoft YAHEI;font-size:12px;}
input{font-family:Microsoft YAHEI;font-size:12px;}
</style>
<title>订单记录</title>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
</head>
<body style="font-family:Microsoft Yahei;">
	<div class="oartop">
	    <table width="100%" border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
	        <td>当前位置：${naviString}
	        <span style="float:right;padding-right:15px;"><a href="${ctx}/customer/manager/KonkaR3ShopStockCount.do?mod_id=${af.map.mod_id}">点击查询库存报表</a></span>
	        </td>
	      </tr>
	    </table>
  	</div>
  	<html-el:form action="/manager/KonkaStockManage">
		<html-el:hidden property="method" value="list" />
		<html-el:hidden property="mod_id" styleId="mod_id" />
		<div class="rtabcont1">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
		      <tr>
		        <td height="36" align="left" style="padding-left:5px;">
			        &nbsp;<strong class="fb">仓库：</strong>
		        	<html-el:select property="store_id" styleId="store_id" multiple="multiple">
		        		<html-el:option value="">=请选择=</html-el:option>
		        		<c:forEach items="${jBaseStoreList}" var="cur">
		        			<html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
		        		</c:forEach>
		        	</html-el:select>
		        	&nbsp;<strong class="fb">商品类型：</strong>
		        	<html-el:select property="type_id" styleId="type_id" onchange="this.form.submit();">
		        		<html-el:option value="">=请选择=</html-el:option>
		        		<c:forEach items="${jBaseTypeList}" var="cur">
		        			<c:if test="${cur.type_id ne 627 }">
		        				<html-el:option value="${cur.type_id}">${cur.type_name}</html-el:option>
		        			</c:if>
		        		</c:forEach>
		        	</html-el:select>
		        	&nbsp;<strong class="fb">商品：</strong>
		        	<html-el:select property="goods_id" styleId="goods_id"  multiple="multiple">
		        		<html-el:option value="">=请选择=</html-el:option>
		        		<c:forEach items="${jBaseGoodsList}" var="cur">
		        			<html-el:option value="${cur.goods_id}">${cur.goods_name}</html-el:option>
		        		</c:forEach>
		        	</html-el:select>
		        	&nbsp;
					<input name="button" type="submit" class="bgSearch" id="button" value="搜 索" style="font-family:Microsoft YAHEI;" />
		        </td>
		      </tr>
		    </table>
	    </div>
	</html-el:form>
	<div class="rtabcont1">
	  <%@ include file="/commons/pages/messages.jsp" %>
	  <!--<input name="input" type="button" id="gotoAdd" class="bgButtonAdd" value="盘 点" style="font-family:Microsoft YAHEI;" onclick="location.href='KonkaStockInventory.do?method=add&mod_id=${af.map.mod_id}'" />-->
	</div>
	<div class="rtabcont1">
	  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
	  	<c:forEach items="${entityList}" var="cur" varStatus="evs">
	  		<tr>
	  			<th align="left" colspan="9"><div style="float:left;margin-left:5px;">商品：${cur.goods_name}</div></th>
	  		</tr>
	  		<tr>
	  			<td width="3%" align="center" nowrap="nowrap" style="background:#EBEBEB;padding-top:1px;padding-bottom:1px;">序号</td>
	  			<td width="8%" align="center" nowrap="nowrap" style="background:#EBEBEB;padding-top:1px;padding-bottom:1px;">仓库编号</td>
	  			<td width="" align="center" nowrap="nowrap" style="background:#EBEBEB;padding-top:1px;padding-bottom:1px;">仓库</td>
	  			<td width="6%" align="center" nowrap="nowrap" style="background:#EBEBEB;padding-top:1px;padding-bottom:1px;">默认进货仓库</td>
	  			<td width="6%" align="center" nowrap="nowrap" style="background:#EBEBEB;padding-top:1px;padding-bottom:1px;">默认出货仓库</td>
	  			<td width="22%" align="center" nowrap="nowrap" style="background:#EBEBEB;padding-top:1px;padding-bottom:1px;">仓库地址</td>
	  			<td width="8%" align="center" nowrap="nowrap" style="background:#EBEBEB;padding-top:1px;padding-bottom:1px;">数量</td>
	  			<!--<td width="70" align="center" nowrap="nowrap" style="background:#EBEBEB;padding-top:1px;padding-bottom:1px;">单价（元）</td>
	  			<td width="40" align="center" nowrap="nowrap" style="background:#EBEBEB;padding-top:1px;padding-bottom:1px;">数量</td>
	  			<td width="80" align="center" nowrap="nowrap" style="background:#EBEBEB;padding-top:1px;padding-bottom:1px;">金额（元）</td>-->
	  			<td width="10%" align="center" nowrap="nowrap" style="background:#EBEBEB;padding-top:1px;padding-bottom:1px;">成本金额（元）</td>
	  			<td width="10%" align="center" nowrap="nowrap" style="background:#EBEBEB;padding-top:1px;padding-bottom:1px;">操作</td>
	  		</tr>
	  		<c:forEach items="${cur.map.jStocksList}" var="j_cur" varStatus="vs">
	  			<tr>
	  				<td align="center" nowrap="nowrap" bgcolor="#fff2dc">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
	  				<td align="left">${j_cur.map.store_sn}</td>
	  				<td align="center">${j_cur.map.store_name}</td>
	  				<td align="center">
	  					<c:if test="${j_cur.map.is_dft_buy_store eq 0}">否</c:if>
	  					<c:if test="${j_cur.map.is_dft_buy_store eq 1}">是</c:if>
	  				</td>
	  				<td align="center">
	  					<c:if test="${j_cur.map.is_dft_sell_store eq 0}">否</c:if>
	  					<c:if test="${j_cur.map.is_dft_sell_store eq 1}">是</c:if>
	  				</td>
	  				<td align="left" title="${j_cur.map.store_addr}">${fnx:abbreviate(j_cur.map.store_addr, 2 * 18, '...')}</td>
	  				<td align="center">${j_cur.stocks}</td>
	  				<!--<td align="left" colspan="3">
	  					<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
		  					<c:forEach items="${j_cur.map.jssList}" var="jss_cur">
		  						<tr>
		  							<td align="right" width="64" style="padding-top:1px;padding-bottom:1px;"><fmt:formatNumber value="${jss_cur.goods_cost}" type="currency" /></td>
		  							<td align="center" width="40" style="padding-top:1px;padding-bottom:1px;">${jss_cur.map.num}</td>
		  							<td align="right" style="padding-top:1px;padding-bottom:1px;"><fmt:formatNumber value="${jss_cur.map.num * jss_cur.goods_cost}" type="currency" /></td>
		  						</tr>
		  					</c:forEach>
	  					</table>
	  				</td>-->
	  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${j_cur.map.ck_cost}" type="currency" /></td>
	  				<td align="center" nowrap="nowrap">
	  					<span style="cursor:pointer;" class="fblue" onclick="openStockDetails(${cur.goods_id}, '${cur.goods_name}', ${j_cur.store_id}, '${j_cur.map.store_name}', 'pkAuto_${cur.goods_id}_${j_cur.store_id}');">查看明细<a id="pkAuto_${cur.goods_id}_${j_cur.store_id}" style="display:none;" href="#pkDiv"></a></span>
	  					<span style="cursor:pointer;" class="fblue"  onclick="doNeedMethod(null, 'KonkaStockManage.do', 'edit','&mod_id=${af.map.mod_id}&goods_id=${cur.goods_id}&store_id=${j_cur.store_id}&'+$('#bottomPageForm').serialize())">修改</span>
	  				</td>
	  			</tr>
	  		</c:forEach>
	  		<tr>
	  			<td colspan="9" align="right">总库存：${cur.map.totle_count}&nbsp;&nbsp;&nbsp;&nbsp;累计成本：<fmt:formatNumber value="${cur.map.totle_cost}" type="currency" /></td>
	  		</tr>
	  		<c:if test="${not evs.last}">
		  		<tr>
		  			<td colspan="9">&nbsp;</td>
		  		</tr>
	  		</c:if>
	  	</c:forEach>
  	  </table>
  	</div>
  	<div class="rtabcont3">
	    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaStockManage.do?method=list">
	      <table width="100%" border="0" align="center" cellspacing="0" cellpadding="0">
	        <tr>
	          <td height="40" align="center"><script type="text/javascript">
	           var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, 5, ${af.map.pager.currentPage});
	           pager.addHiddenInputs("method", "list");
	           pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	           pager.addHiddenInputs("goods_id", "${af.map.goods_id}");
	           pager.addHiddenInputs("type_id", "${af.map.type_id}");
	           pager.addHiddenInputs("store_id", "${af.map.store_id}");
	           document.write(pager.toString());
	           </script>
	          </td>
	        </tr>
	      </table>
	    </form>
   	</div>
   	<div style="display:none;">
   		<!-- 盘亏DIV -->
		<div id="pkDiv" style="width:510px;max-height:340px;">
			<div style="width:100%;height:310px;overflow:auto;">
				<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
					<tr>
						<th align="center" colspan="5">库存清单</th>
					</tr>
					<tr>
						<td width="3%" class="title_item" nowrap="nowrap">仓库：</td>
						<td width="47%" colspan="2"><span id="pkStoreSpan" style="color:#6495ED"></span></td>
						<td width="3%" class="title_item" nowrap="nowrap">商品：</td>
						<td width="47%"><span id="pkGoodSpan" style="color:#6495ED;"></span></td>
					</tr>
					<tr>
						<td align="center" nowrap="nowrap" style="background:#EBEBEB;padding-top:1px;padding-bottom:1px;">序号</td>
			  			<td align="center" nowrap="nowrap" style="background:#EBEBEB;padding-top:1px;padding-bottom:1px;">交易类型</td>
			  			<td align="center" nowrap="nowrap" style="background:#EBEBEB;padding-top:1px;padding-bottom:1px;">单据编号</td>
			  			<td align="center" nowrap="nowrap" style="background:#EBEBEB;padding-top:1px;padding-bottom:1px;">日期</td>
			  			<td align="center" nowrap="nowrap" style="background:#EBEBEB;padding-top:1px;padding-bottom:1px;">成本价（元）</td>
					</tr>
					<tbody id="pkTbody"></tbody>
					<tr>
						<td colspan="5" align="right" style="padding-right:10px;">总计：<span id="pdTotalDiv" style="font-weight:bold;font-size:15px;color:#CD0000;"></span> 元</td>
					</tr>
				</table>
			</div>
			<div style="float:right;margin-top:8px;">
				<input type="button" id="pkButton" name="pkButton" class="webbutton" value="关 闭" onclick="$.fancybox.close();" style="width:46px;" />
			</div>
		</div>
    </div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/customer/script/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#goods_id").multiselect({
		noneSelectedText: '==请选择==',
		selectedList: 1,
		multiple: false,
		minWidth:150,
		click: function(event, ui){
	       if(ui.value != ""){
	    	   $("#goods_id").val(ui.value);
	       }
		}
	}).multiselectfilter();
	$("#store_id").multiselect({
		noneSelectedText: '==请选择==',
		selectedList: 1,
		multiple: false,
		minWidth:150,
		click: function(event, ui){
	       if(ui.value != ""){
	    	   $("#store_id").val(ui.value);
	       }
		}
	}).multiselectfilter();
});

function openStockDetails(goods_id, goods_name, store_id, store_name, aId){
	jLoading("&nbsp;&nbsp;正在查询...", {autoHide:false, HorizontalPosition:"center", VerticalPosition:"center", MinWidth:150});
	$("#pkStoreSpan").text(store_name);
	$("#pkGoodSpan").text(goods_name);
	//Ajax异步取盘亏待处理清单...
	$.ajax({
		type: "POST" , 
		url: "KonkaStockManage.do" , 
		data:"method=getPkGoodsAndStoresList&store_id=" + store_id + "&good_id=" + goods_id + "&t=" + new Date(),
		dataType: "json" , 
        async: true, 
        error: function (request, settings) {alert("数据加载请求失败");},
        success: function (result) {
			//alert(result.state);
			$.jNotify._close();
			if (result.state == 0) {
				jError("操作失败：参数丢失，请重试！", {HorizontalPosition:"center", VerticalPosition:"center", TimeShown:3000});
				$("#ver_stocks").val("");
				$("#vsResultTd").empty();
			} else if (result.state == -1) {
				jError("操作失败：查询列表失败！", {HorizontalPosition:"center", VerticalPosition:"center", TimeShown:3000});
				$("#ver_stocks").val("");
				$("#vsResultTd").empty();
			} else if (result.state == 1) {
				$("#pkTbody").empty();
				$("#pdTotalDiv").empty();
				for ( var j in result.list) {
					$("#pkTbody").append('<tr>' + 
							'<td align="center" nowrap="nowrap">' + (Number(j)+1) + '</td>' + 
							'<td align="center" nowrap="nowrap">' + result.list[j].business_type + '</td>' + 
							'<td align="center" nowrap="nowrap">' + result.list[j].bill_id_push + '</td>' + 
							'<td align="center" nowrap="nowrap">' + result.list[j].bill_date + '</td>' + 
							'<td align="center" nowrap="nowrap">' + result.list[j].goods_cost + '</td>' + 
						 '</tr>');
				}
				$("#pdTotalDiv").text(result.total_cost);
				openPkDiv(aId);
			}
		}
	});
}

function openPkDiv(id){
	$("#" + id).fancybox({
			'transitionIn'	: 'elastic',
			'transitionOut'	: 'elastic',
			'titlePosition' : 'inside',
			'scrolling' : 'auto',
			'easingIn' : 'swing',
			'easingOut' : 'swing',
			//'modal':false,
			'overlayShow':true, //显示遮罩层
			'hideOnOverlayClick':true, //如果为true则点击遮罩层关闭fancybox
			'hideOnContentClick':false, //如果为true则点击播放内容关闭fancybox
			'enableEscapeButton':true, //如果为true，则启用ESC来关闭fancybox
			'showCloseButton':true, //显示关闭按钮
			'centerOnScroll':true, //如果为true，当你滚动滚动条时，fancybox将会一直停留在浏览器中心
			'autoScale':false, //如果为true，fancybox可以自适应浏览器窗口大小
			'padding':10,
			'width':540,
			'height':360}).trigger('click');
}
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>