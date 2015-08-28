<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${naviString}</title>
<base target="_self" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/fancybox/fancybox.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont" >
	<div class="oartop">
	    <table width="400" border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
	        <td>当前位置：${naviString}</td>
	      </tr>
	    </table>
  	</div>
  	<div class="rtabcont2">
  		<html-el:form action="/manager/JStockInventory">
  			<html-el:hidden property="method" value="save"/>
	      	<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	      	<div style="color:red;">注：盘点前/后数量相差10以上必须填写差异原因。</div>
			<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
		    <tr>
		    	<th width="2%">序号</th>
		    	<th width="12%">仓库</th>
		    	<th width="6%">商品</th>
		    	<th width="4%">盘点前数量</th>
		    	<th width="8%">盘点前金额</th>
				<th width="8%">差异数量</th>
				<th width="10%">差异单价</th>
		    	<th width="10%">盘点后数量</th>
		    	<th width="10%">盘点后金额</th>
		    	<th width="4%">结果</th>
		    	<th width="12%">差异原因</th>
		    </tr>
		    <c:forEach items="${entityList}" var="cur" varStatus="vs">
		    	<tr>
		    		<html-el:hidden property="store_id" styleClass="store_id" value="${cur.map.store_id }"/>
		    		<html-el:hidden property="goods_id" styleClass="goods_id" value="${cur.map.goods_id }"/>
		    		<td align="center" nowrap="nowrap" bgcolor="#fff2dc">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
		    		<td align="center">${cur.map.store_name } </td>
		    		<td align="center">${cur.map.goods_name } </td>
					<td align="center"><html-el:text property="stocks" styleClass="stocks"  size="13" readonly="true" value="${cur.map.stocks}"/><br /></td>
					<td align="center"><html-el:text property="money" styleClass="money"  size="13" readonly="true" value="${cur.map.money}" /><br /></td>
					<td align="center"><html-el:text property="ver_stocks_diff" styleClass="ver_stocks_diff"  size="13" readonly="false"  onfocus="setOnlyNum(this);" maxlength="8" value="0"/><br /></td>
						<td align="center">
						<html-el:text property="price" styleClass="price"  size="13" readonly="false"  onfocus="setOnlyNum(this);" maxlength="10" value="0"/>
					</td>
					<td align="center"><html-el:text property="ver_stocks" styleClass="ver_stocks"  size="13" readonly="false"  onfocus="setOnlyNum(this);" maxlength="8" value="${cur.map.stocks}"/><br /></td>

					<td align="center"><html-el:text property="ver_money" styleClass="ver_money"  size="13" readonly="false"  onfocus="setOnlyNum(this);" maxlength="10" value="${cur.map.money}"/><br /></td>
					<td align="center" nowrap="nowrap">
		    			<div class="result"><span>库实相符</span></div>
		    		</td>
		    		<td align="left" nowrap="nowrap"><html-el:text property="memo" styleClass="memo" maxlength="100" style="width:150px;"/></td>
		    	</tr>
		    </c:forEach>
		    <c:forEach items="${allList}" var="cur" varStatus="vs">
		    	<tr>
		    		<html-el:hidden property="store_id" styleClass="store_id" value="${cur.map.store_id }"/>
		    		<html-el:hidden property="goods_id" styleClass="goods_id" value="${cur.map.goods_id }"/>
		    		<td align="center" nowrap="nowrap" bgcolor="#fff2dc">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
		    		<td align="center">${cur.map.store_name } </td>
		    		<td align="center">${cur.map.goods_name } </td>
					<td align="center"><html-el:text property="stocks" styleClass="stocks"  size="13" readonly="true" value="${cur.map.stocks}"/><br /></td>
					<td align="center"><html-el:text property="money" styleClass="money"  size="13" readonly="true" value="${cur.map.money}" /><br /></td>
					<td align="center"><html-el:text property="ver_stocks_diff" styleClass="ver_stocks_diff"  size="13" readonly="false"  onfocus="setOnlyNum(this);" value="${cur.map.ver_stocks-cur.map.stocks}" maxlength="8" /><br /></td>
						<td align="center">
						<html-el:text property="price" styleClass="price"  size="13" readonly="false"  onfocus="setOnlyNum(this);" maxlength="10" value="${cur.map.price}"/>
					</td>
					<td align="center"><html-el:text property="ver_stocks" styleClass="ver_stocks"  size="13" readonly="false"   onfocus="setOnlyNum(this);" maxlength="8" value="${cur.map.ver_stocks}"/><br /></td>
					<td align="center"><html-el:text property="ver_money" styleClass="ver_money"  size="13" readonly="false"  onfocus="setOnlyNum(this);" maxlength="10" value="${cur.map.ver_money}"/><br /></td>
					<td align="center" nowrap="nowrap">
		    			<c:if test="${cur.map.ver_stocks>cur.map.stocks}">
		    				<span style="color:#009900;">盘盈</span>
		    			</c:if>
		    			<c:if test="${cur.map.ver_stocks==cur.map.stocks}">
		    				<span>库实相符</span>
		    			</c:if>
		    			<c:if test="${cur.map.ver_stocks<cur.map.stocks}">
		    				<span style="color:#CD0000;">盘亏</span>
		    			</c:if>
		    		</td>
		    		<td align="left" nowrap="nowrap"><html-el:text property="memo" styleClass="memo" maxlength="100" style="width:150px;" value="${cur.map.memo}"/></td>
		    	</tr>
		    </c:forEach>
		  </table>
		  	<div align="center">
		  		<html-el:button property="" value="提 交" styleClass="bgButtonSave" styleId="btn_submit" />&nbsp;&nbsp;
		        <html-el:button property="" value="返 回" styleClass="bgButtonBack" styleId="btn_back" onclick="history.back();return false;" />
		        &nbsp;&nbsp;<html-el:button property="" value="导 出" styleClass="bgButtonExport" styleId="btn_export" onclick="window.location.href='${ctx}/customer/manager/JStockInventory.do?method=add&mod_id=${af.map.mod_id}&export=true';"/>
		        &nbsp;&nbsp;<html-el:button property="" value="导 入" styleClass="but_excel" styleId="btn_excel" onclick="window.location.href='${ctx}/customer/manager/JStockInventory.do?method=excel&mod_id=${af.map.mod_id}';" />
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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){



	$(".ver_stocks_diff").blur(function(){
		$this = $(this);
//		var $store = $this.parent().parent().children('td').eq(0).children();
//		var $goods = $this.parent().parent().children('td').eq(1).children();
		var $stock = $this.parent().parent().children('td').eq(3).children();
		var $money = $this.parent().parent().children('td').eq(4).children();
		
		var $price = $this.parent().parent().children('td').eq(6).children();
		var $ver_stocks = $this.parent().parent().children('td').eq(7).children();
		var $ver_money = $this.parent().parent().children('td').eq(8).children();
		var $result = $this.parent().parent().children('td').eq(9).children();
		var stock = $stock.val();
		var ver_stocks_diff = $this.val();
		$result.html("");
		var count = parseInt(ver_stocks_diff,10) + parseInt(stock,10);
		$ver_stocks.val(count);
		if(ver_stocks_diff>0){//盘盈
			$result.html('<span style="color:#009900;">盘盈</span>');
			$price.focus();
			$ver_money.val("0");
		} else if(ver_stocks_diff == 0){//库实相符
			$result.html('<span>库实相符</span>');
			//$price.val("");
			$price.focus();
			$ver_money.val("0");
		} else if(ver_stocks_diff < 0){//盘亏
			$result.html('<span style="color:#CD0000;">盘亏</span>');
			//$price.val("");
			$price.focus();
			$ver_money.val("0");
		}

	});



	$(".ver_stocks").blur(function(){
		$this = $(this);
		var $stock = $this.parent().parent().children('td').eq(3).children();
		var $money = $this.parent().parent().children('td').eq(4).children();
		var $ver_stocks_diff = $this.parent().parent().children('td').eq(5).children();
		var $price = $this.parent().parent().children('td').eq(6).children();
		var $ver_stocks = $this.parent().parent().children('td').eq(7).children();
		var $ver_money = $this.parent().parent().children('td').eq(8).children();
		var $result = $this.parent().parent().children('td').eq(9).children();
		var stock = $stock.val();
		var ver_stocks = $this.val();
		var ver_stocks_diff =parseInt(ver_stocks,10) - parseInt(stock,10);
		$result.html("");
		$ver_stocks_diff.val(ver_stocks_diff);
		//var count = parseInt(ver_stocks,10) - parseInt(stock,10);
		if(ver_stocks_diff>0){//盘盈
			$result.html('<span style="color:#009900;">盘盈</span>');
			$memo.removeAttr("dataType");
			if(ver_stocks_diff>=10){
			$memo.attr("dataType", "Require").attr("msg", "请填写！");
			}
			$ver_money.focus();
		} else if(ver_stocks_diff == 0){//库实相符
			 $result.html('<span>库实相符</span>');
			 $memo.removeAttr("dataType");
			 $ver_money.focus();
		} else if(ver_stocks_diff < 0){//盘亏
			 $result.html('<span style="color:#CD0000;">盘亏</span>');
			 $memo.removeAttr("dataType");
			 if(ver_stocks_diff<=-10){
				$memo.attr("dataType", "Require").attr("msg", "请填写！");
			 }
			 $ver_money.focus();
		}
	});

	$(".ver_money").blur(function(){
		$this = $(this);
		var $stock = $this.parent().parent().children('td').eq(3).children();
		var $money = $this.parent().parent().children('td').eq(4).children();
		var $ver_stocks_diff = $this.parent().parent().children('td').eq(5).children();
		var $price = $this.parent().parent().children('td').eq(6).children();
		var $ver_stocks = $this.parent().parent().children('td').eq(7).children();
		var $ver_money = $this.parent().parent().children('td').eq(8).children();
		var $result = $this.parent().parent().children('td').eq(9).children();

		var stock = $stock.val();
		var money = $money.val();
		var ver_stocks = $ver_stocks.val();
		var ver_money = $this.val();
		
		var ver_stocks_diff =$ver_stocks_diff.val();
	
		//var count = parseInt(ver_stocks,10) - parseInt(stock,10);
		if(parseInt(ver_stocks_diff,10)==0){
			$price.val(0);
			 $price.val((ver_money-money).toFixed(2))
			}else{
				 var diff_price=((ver_money-money)/parseInt(ver_stocks_diff,10)).toFixed(2);
	        	   $price.val(diff_price);
				}
	});
	
	
	$(".price").blur(function(){
		var $stock = $this.parent().parent().children('td').eq(3).children();
		var $money = $this.parent().parent().children('td').eq(4).children();
		var $ver_stocks_diff = $(this).parent().parent().children('td').eq(5).children();
		var $ver_stocks = $(this).parent().parent().children('td').eq(7).children();
		var $ver_money = $(this).parent().parent().children('td').eq(8).children();
		//计算初始化金额
		if($(this).val().length>0){
			$ver_money.val(formatFloat(
					parseFloat($money.val())+
					parseFloat($ver_stocks_diff.val()) * parseFloat($(this).val()),2));
		}
	});

//	$(".price").blur(function(){
//		var $ver_stocks = $(this).parent().parent().children('td').eq(6).children();
//		var $ver_money = $(this).parent().parent().children('td').eq(8).children();
//		//计算初始化金额
//		if($(this).val().length>0){
//			$ver_money.val(formatFloat(parseFloat($ver_stocks.val()) * parseFloat($(this).val()),2));
//		}
//	});
	
	//表单提交
	$("#btn_submit").click(function(){
		$(".ver_stocks").attr("dataType", "Require").attr("msg", "请填写！");
		$(".ver_money").attr("dataType", "Require").attr("msg", "请填写！");
		if(Validator.Validate(this.form, 3)){
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
//             $("#btn_back").attr("disabled", "true");
			this.form.submit();
		} else {
			//iframe高度自适应
			window.parent.resizeFrameHeight('mainFrame', 3);
		}
	});
});
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

//正则表达式：只能输入数字
function setOnlyNum(obj) {
	$(obj).css("ime-mode", "disabled");
	$(obj).attr("t_value", "");
	$(obj).attr("o_value", "");
	$(obj).bind("dragenter",function(){
		return false;
	});
	$(obj).keypress(function (){
		if(!obj.value.match(/^[\+\-]?\d*?\.?\d*?$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))obj.o_value=obj.value;
	}).keyup(function (){
		if(!obj.value.match(/^[\+\-]?\d*?\.?\d*?$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))obj.o_value=obj.value;
	}).blur(function (){
		if(!obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\.\d+$/))obj.value=0+obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
		//if(obj.value.length == 0) obj.value = "0";
	});
}
//正则表达式：只能输入数字（不包括负数）
function setOnlyPositiveNum(obj) {
	$(obj).css("ime-mode", "disabled");
	$(obj).attr("t_value", "");
	$(obj).attr("o_value", "");
	$(obj).bind("dragenter",function(){
		return false;
	});
	$(obj).keypress(function (){
		if(!obj.value.match(/^\d*?\.?\d*?$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\.\d+)?)?$/))obj.o_value=obj.value;
	}).keyup(function (){
		if(!obj.value.match(/^\d*?\.?\d*?$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\.\d+)?)?$/))obj.o_value=obj.value;
	}).blur(function (){
		if(!obj.value.match(/^(?:\d+(?:\.\d+)?|\.\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\.\d+$/))obj.value=0+obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
		if(isNaN(obj.value)) obj.value = "";
	});
}
//格式化数字，pos为保留几位小数
function formatFloat(src, pos) 
{ 
    return Math.round(src*Math.pow(10, pos))/Math.pow(10, pos); 
} 
//]]>
</script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>