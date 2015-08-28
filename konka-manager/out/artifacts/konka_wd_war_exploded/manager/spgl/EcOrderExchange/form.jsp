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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/spgl/EcOrderExchange" method="post">
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">交易流水号：</td>
          <td width="44%" align="left" >${af.map.oldPshowOrder.trade_index}</td>
          <td nowrap="nowrap" class="title_item" align="right"  width="12%">退换货：</td>
          <td align="left" width="44%"><c:if test="${af.map.is_exchange eq 1 }">退货</c:if>  <c:if test="${af.map.is_exchange eq 2 }">换货</c:if></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"  width="12%">型号：</td>
          <td align="left" width="44%">${af.map.pshowOrdeDetails.map.pd_sn} </td>       
          <td nowrap="nowrap" class="title_item" align="right"  width="12%">数量：</td>
          <td align="left" width="44%">${af.map.pshowOrdeDetails.num} </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">金额：</td>
          <td align="left"> ${af.map.pshowOrdeDetails.total_price} </td> 
          <td nowrap="nowrap" class="title_item" align="right">收货人：</td>
          <td align="left">${af.map.oldPshowOrder.buyer_name} </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">收货人电话：</td>
          <td align="left">${af.map.oldPshowOrder.buyer_mp} </td>       
          <td nowrap="nowrap" class="title_item" align="right">收货地址：</td>
          <td align="left"><c:out value="${af.map.oldPshowOrder.map.full_name}" />&nbsp;<c:out value="${af.map.oldPshowOrder.buyer_addr}" /> </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">客服跟踪：</td>
          <td align="left" colspan="3" > 
	          <table width="100%" border="0" cellspacing="1" cellpadding="0"> 
	          		<tr>
		           	  <td width="25%" nowrap="nowrap" align="center">机器状态</td>
			          <td width="25%" nowrap="nowrap" align="center">退机类型</td> 
			          <td width="25%" nowrap="nowrap" align="center">报险方式</td>
			          <td width="25%" nowrap="nowrap" align="center">报险金额</td>
			        </tr>
		            <tr> 
			           	<td align="center">
			           	<html-el:select property="pd_step">
				           	<html-el:option value="">请选择</html-el:option>
				           	<html-el:option value="1">客户家里</html-el:option>
				           	<html-el:option value="2">拉回在途</html-el:option>
				           	<html-el:option value="3">分公司</html-el:option>
			           	</html-el:select>
			           	</td>
			            <td align="center">
			            <html-el:select property="exchange_info">
				           	<html-el:option value="">请选择</html-el:option>
				           	<html-el:option value="1">质量机退货</html-el:option>
				           	<html-el:option value="2">碎屏</html-el:option>
				           	<html-el:option value="3">好机退货</html-el:option>
				           	<html-el:option value="4">其它</html-el:option>
			           	</html-el:select>				        
				         </td> 
				       	 <td align="center">
				       	 	<html-el:select property="insurance_way">
					           	<html-el:option value="">请选择</html-el:option>
					           	<html-el:option value="1">顺丰理赔</html-el:option>
					           	<html-el:option value="2">保险+顺丰理赔</html-el:option>
					           	<html-el:option value="3">保险理赔</html-el:option>
			           		</html-el:select>
				       	 </td>
				       	 <td align="center">
				       	 <html-el:text property="insurance_price" maxlength="8" size="8" styleId="insurance_price"></html-el:text>元
				       	 </td>
			        </tr>
	          </table>
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">财务跟踪：</td>
          <td align="left" colspan="3">
          <table width="100%" border="0" cellspacing="1" cellpadding="0"> 
          	<tr>
	           	  <td width="25%" nowrap="nowrap" align="center">机器入库</td>
		          <td width="25%" nowrap="nowrap" align="center">财务核销</td>
		          <td width="25%" nowrap="nowrap" align="center" dir="rtl">理赔是否到账</td>
		          <td width="25%" nowrap="nowrap" align="center">退款金额</td>
	        </tr>
          	<tr>
	           	 <td align="center">
	           	 				<html-el:select property="pd_store">
						           	<html-el:option value="">请选择</html-el:option>
						           	<html-el:option value="1">未入库</html-el:option>
						           	<html-el:option value="2">入库</html-el:option> 
				           		</html-el:select>
				 </td>
		       	 <td align="center">
								<html-el:select property="exchange_state">
						           	<html-el:option value="">请选择</html-el:option>
						           	<html-el:option value="1">未办退换货</html-el:option>
						           	<html-el:option value="2">已办退换货</html-el:option> 
				           		</html-el:select>	       	 
		       	 </td>
		       	 <td align="center">
		       	 				<html-el:select property="insurance_state">
						           	<html-el:option value="">请选择</html-el:option>
						           	<html-el:option value="1">未到账</html-el:option>
						           	<html-el:option value="2">已到账</html-el:option> 
				           		</html-el:select>	
		       	 </td>
		       	 <td align="center">
		       	 			<html-el:text property="exchange_price" maxlength="8" size="8" styleId="exchange_price"></html-el:text>元
		       	 </td>
	       	 </tr>
          </table>
          </td>
        </tr> 
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">备注：</td>
          <td width="88%" align="left" colspan="3"> <html-el:text property="remark" maxlength="120" size="60"></html-el:text></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><html-el:button property="" value="提交" styleClass="but4" styleId="btn_submit" />
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();return false;" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#exchange_price").keypress(function(){//单价
		var exchange_price = $("#exchange_price").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(price)) {
			$("#exchange_price").val(0);
		}
	}).keyup(function(){
		var exchange_price = $("#exchange_price").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(price)) {
			$("#exchange_price").val(0);
		}
	}).blur(function(){
		var exchange_price = $("#exchange_price").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(price)) {
			$("#exchange_price").val(0);
		}
	});	
	
	$("#insurance_price").keypress(function(){//单价
		var insurance_price = $("#insurance_price").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(insurance_price)) {
			$("#insurance_price").val(0);
		}
	}).keyup(function(){
		var insurance_price = $("#insurance_price").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(insurance_price)) {
			$("#insurance_price").val(0);
		}
	}).blur(function(){
		var insurance_price = $("#insurance_price").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(insurance_price)) {
			$("#insurance_price").val(0);
		}
	});	
	
	$("#btn_submit").click(function(){
		var isSubmit = Validator.Validate(this.form, 3);
		if (isSubmit) {
			$(":button").attr("disabled", "true");
			this.form.submit();
		}
	});
});


//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
