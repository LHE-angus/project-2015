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
  	  <div style="font-size:20px;font-weight:700;height:35px;text-align:center;">确认消费者收货</div>
	  <html-el:form action="/zmd/KonkaXxZmdCustReceiveConfirm">
		  <html-el:hidden property="method" value="save" />
		  <html-el:hidden property="sell_bill_id" />
		  <html-el:hidden property="mod_id" />
		  <html-el:hidden property="queryString" />
		  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
		  	<!-- 销售单信息Begin -->
		  	<tr>
		  		<td colspan="4" style="font-weight:900;">销售单信息</td>
		  	</tr>
		  	<tr>
		  		<td width="15%" class="title_item" nowrap="nowrap" align="right" ><strong>分公司：</strong></td>
	            <td width="35%" align="left"><c:out value="${af.map.dept_name}" /></td>
	            <td width="15%" class="title_item" nowrap="nowrap" align="right">专卖店编号：</td>
	            <td align="left"><c:out value="${af.map.zmd_sn}" /></td>
		  	</tr>
		  	<tr>
		  		<td class="title_item" nowrap="nowrap" align="right">销售人员：</td>
		  		<td align="left"><c:out value="${af.map.sell_man}" /></td>
		  		<td class="title_item" nowrap="nowrap" align="right">销售时间：</td>
		  		<td align="left"><fmt:formatDate value="${af.map.sell_date}" pattern="yyyy-MM-dd" var="_sell_date" />${_sell_date}</td>
		  	</tr>
	        <tr>
	        	<td class="title_item" nowrap="nowrap" align="right">送货地址：</td>
	        	<td align="left" colspan="3">${af.map.post_area} <c:out value="${af.map.sell_post_addr}" /></td>
	        </tr>
	        <tr>
	        	<td class="title_item" nowrap="nowrap" align="right">收货人姓名：</td>
	        	<td align="left"><c:out value="${af.map.sell_rec_name}" /></td>
	        	<td class="title_item" nowrap="nowrap" align="right">收货人联系电话：</td>
	        	<td align="left"><c:out value="${af.map.sell_rec_link_mp}" /></td>
	        </tr>
	        <tr>
	        	<td class="title_item" nowrap="nowrap" align="right">发票类型：</td>
		  		<td align="left" colspan="3">
		  			<c:forEach items="${baseTypesList90000}" var="cur">
		  				<c:if test="${cur.type_id eq af.map.sell_bill_type}">${cur.type_name}</c:if>
		  			</c:forEach>
	        	</td>
	        </tr>
	        <tr>
	        	<td class="title_item" nowrap="nowrap" align="right">备注：</td>
	        	<td align="left" colspan="3"><c:out value="${af.map.sell_remarks}" /></td>
	        </tr>
	        <!-- 销售单信息End -->
	        <!-- 商品信息Begin -->
	        <tr>
		  		<td colspan="4" style="font-weight:900;">产品清单</td>
		  	</tr>
	        <tr>
	        	<td colspan="4" align="center" style="padding-top:5px;padding-bottom:5px;">
	        	  <div id="div_2">
	        		<!-- 产品信息Begin -->
	        		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
				        <tr class="tabtt1" style="background-image:url('${ctx}/images/manager/tabtitbg1.gif') repeat-x;background:#F0F0F0;">
				          <td width="5%" align="center" nowrap="nowrap">序号</td>
				          <td width="10%" align="center" nowrap="nowrap">产品型号</td>
				          <td width="10%" align="center" nowrap="nowrap">产品品类</td>
				          <td width="10%" align="center" nowrap="nowrap">赠品</td>
				          <td width="4%" align="center" nowrap="nowrap">数量</td>
				          <td width="8%" align="center" nowrap="nowrap">单价</td>
				          <td width="8%" align="center" nowrap="nowrap">金额</td>
				          <td width="5%" align="center" nowrap="nowrap">商品类型</td>
				          <td width="30%" align="center" nowrap="nowrap">仓位信息</td>
				          <td width="10%" align="center" nowrap="nowrap">销售备注</td>
				        </tr>
				        <c:forEach items="${konkaXxSellBillDetailsList}" var="cur" varStatus="vs">
				        	<tr>
				        		<td align="center" nowrap="nowrap">${vs.count}</td>
					        	<td align="left" nowrap="nowrap" class="pdIds"><c:out value="${cur.md_name}" /></td>
					        	<td align="center" nowrap="nowrap">${cur.pd_cls_name}</td>
					        	<td align="left"><c:out value="${cur.gift}" /></td>
					        	<td align="right" nowrap="nowrap"><c:out value="${cur.counts}" /></td>
					        	<td align="right" nowrap="nowrap"><img src="${ctx}/images/yen.png" alt="￥" title="RMB" /><c:out value="${cur.price}" /></td>
					        	<td align="right" nowrap="nowrap"><img src="${ctx}/images/yen.png" alt="￥" title="RMB" /><span id="pdAmount_${vs.count}"></span></td>
					        	<td align="center" nowrap="nowrap">
					        		<c:forEach items="${baseTypesList70000}" var="cur_">
						  				<c:if test="${cur_.type_id eq cur.pd_type}">${cur_.type_name}</c:if>
						  			</c:forEach>
					        	</td>
					        	<td align="left" nowrap="nowrap">
					        	  <ul>
				        			<c:forEach items="${cur.map.dstList}" var="cur_" varStatus="vs_">
				        				<li>仓位${vs_.count}. ${cur_.factory_id}_${cur_.store_id}_${cur_.store_name}：<span style="cursor:pointer;font-weight:800;" class="fblue" title="出库数量">${cur_.counts}</span>台；</li>
				        			</c:forEach>
				        		  </ul>
					        	</td>
					        	<td align="left"><c:out value="${cur.xs_remarks}" /></td>
				        	</tr>
				        </c:forEach>
				        <tr id="total">
				        	<td align="center" colspan="2" style="color:red;font-weight:bold;">合计</td>
				        	<td colspan="2"></td>
				        	<td align="center"></td>
				        	<td></td>
				        	<td align="right" nowrap="nowrap">
				        		<font color="red" style="font-weight:bold;">
				        			<img src="${ctx}/images/yen.png" alt="￥" title="RMB" />
				        			<span id="allAmount"><fmt:formatNumber value="${af.map.total_money}" pattern="0.00" var="total_money_" />${total_money_}</span>
				        		</font>
				        	</td>
				        	<td colspan="3"></td>
				        </tr>
				    </table>
				    <!-- 产品信息End -->
				  </div>
	        	</td>
	        </tr>
	        <!-- 商品信息End -->
	        <!-- 客户信息Begin -->
	        <tr>
		  		<td colspan="4" style="font-weight:900;">客户信息</td>
		  	</tr>
		  	<tr>
	            <td class="title_item" nowrap="nowrap" align="right" ><strong>客户姓名：</strong></td>
	            <td align="left"><c:out value="${af.map.buyer_name}"></c:out></td>
	            <td class="title_item" nowrap="nowrap" align="right">身份证：</td>
	            <td align="left"><c:out value="${af.map.buyer_id}" />
	            <c:if test="${empty af.map.buyer_id}"><span style="color:gray;">未填写</span></c:if></td>
	        </tr>
	        <tr>
	            <td width="15%" class="title_item" nowrap="nowrap" align="right" ><strong>性别：</strong></td>
	            <td width="35%" align="left">
	            	<c:choose>
	            		<c:when test="${af.map.buyer_sex eq 1}">男</c:when>
	            		<c:when test="${af.map.buyer_sex eq 2}">女</c:when>
	            		<c:when test="${af.map.buyer_sex eq 3}">保密</c:when>
	            		<c:otherwise>不详</c:otherwise>
	            	</c:choose>
	            </td>
	            <td width="15%" class="title_item" nowrap="nowrap" align="right">生日：</td>
	            <td align="left">
	            	<fmt:formatDate value="${af.map.buyer_birthday}" pattern="yyyy-MM-dd" var="_buyer_birthday" />
	            	<c:out value="${_buyer_birthday}" />
	            	<c:if test="${empty _buyer_birthday}"><span style="color:gray;">未填写</span></c:if>
	            </td>
	        </tr>
	        <tr>
	            <td width="15%" class="title_item" nowrap="nowrap" align="right" ><strong>电话：</strong></td>
	            <td width="35%" align="left"><c:out value="${af.map.buyer_tel}" />
	            <c:if test="${empty af.map.buyer_tel}"><span style="color:gray;">未填写</span></c:if></td>
	            <td width="15%" class="title_item" nowrap="nowrap" align="right">手机：</td>
	            <td align="left"><c:out value="${af.map.buyer_phone}" />
	             <c:if test="${empty af.map.buyer_phone}"><span style="color:gray;">未填写</span></c:if></td>
	        </tr>
	        <tr>
	        	<td width="15%" class="title_item" nowrap="nowrap" align="right">客户地址：</td>
	        	<td align="left" colspan="3"><c:out value="${af.map.cust_area}" />
	        	<c:if test="${empty af.map.cust_area}"><span style="color:gray;">未填写</span></c:if>
	        	<c:out value="${af.map.buyer_link_addr}" />
	        	<c:if test="${empty af.map.buyer_link_addr}"><span style="color:gray;">未填写</span></c:if></td>
	        </tr>
	        <tr>
	        	<td width="15%" class="title_item" nowrap="nowrap" align="right">消费者备注：</td>
	        	<td align="left" colspan="3"><c:out value="${af.map.buyer_memo}" />
	        	<c:if test="${empty af.map.buyer_memo}"><span style="color:gray;">未填写</span></c:if></td>
	        </tr>
	        <!-- 客户信息End -->
	        <tr>
		  		<td colspan="4" style="font-weight:900;">付款信息</td>
		  	</tr>
		  	<tr>
		  		<td class="title_item" nowrap="nowrap" align="right">付款方式：</td>
		  		<td align="left">${fn:split(' ,现金付款,POS机刷卡,货到付款', ',')[af.map.pay_way]}</td>
		  		<td class="title_item" nowrap="nowrap" align="right">订单总金额：</td>
		  		<td align="left">
		  			<span class="kz-price"><fmt:formatNumber type="currency" value="${total_money_}" /></span>
		  		</td>
		  	</tr>
		  	<tr>
		  		<td class="title_item" nowrap="nowrap" align="right">物流应收款：</td>
		  		<td align="left" colspan="3"><!-- span style="font-weight:700;font-size:130%;color:#F00;">-->
		  		<c:choose>
	               <c:when test="${af.map.pay_way eq 3}">
	               		<span class="kz-price"><fmt:formatNumber type="currency" value="${af.map.total_money - af.map.money_of_deposit}" /></span>
	               </c:when>
	               <c:otherwise>
	               		<span class="kz-price"><fmt:formatNumber type="currency" value="0" /></span>
	               </c:otherwise>
	            </c:choose>
	            <!--</span>-->
	            </td>
		  	</tr>
		  	<tr>
		  		<td colspan="4">&nbsp;</td>
		  	</tr>
		  	<c:if test="${af.map.pay_way eq 3}">
			  	<tr>
				  	<td>&nbsp;</td>
				  	<td colspan="3" style="font-weight:900;">
				  	<label for="check_money" style="cursor:pointer;"><html-el:checkbox styleId="check_money" property="check_money"/>&nbsp;<span style="color:red;text-decoration:underline;">已确认收取订单余款 ${af.map.total_money - af.map.money_of_deposit} 元。</span></label>
				  	<span id="dist_payee_span" style="display:none;">
				  	请填写收款人姓名：<input type="text" name="dist_payee" id="dist_payee" size="10" maxlength="20"/> 
				  	收款人身份证号：<input type="text" name="dist_payee_id" id="dist_payee_id" size="20" maxlength="18" />
				  	</span></td>
			  	</tr>
		  	</c:if>
	        <tr>
	            <td colspan="4" height="40" align="center">
	            	<c:choose>
	              	  <c:when test="${af.map.pay_way eq 3}"><input class="but4" type="button" name="Submit4" value="提交 " disabled="disabled" id="btn_submit" /></c:when>
	               	  <c:otherwise><input class="but4" type="button" name="Submit4" value="提交 " id="btn_submit" /></c:otherwise>
	                </c:choose>
	            	<input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" id="btn_back" />
	            </td>
	        </tr>
		  </table>
	  </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery1.5.1.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	var total_num = 0;
	<c:forEach items="${konkaXxSellBillDetailsList}" var="cur" varStatus="vs">
		var i = "${vs.count}";
		var counts = parseInt("${cur.counts}");
		var price = parseFloat("${cur.price}");
		var money = counts * price;
		$("#pdAmount_" + i).text(money.toFixed(2));
		total_num = parseInt(total_num) + counts;
	</c:forEach>
	$("#total").children().eq(2).text(total_num);

    $("#check_money").click(function(){
    	if(this.checked){
    		$("#btn_submit").removeAttr("disabled");
    		$("#dist_payee_span").show();
    		$("#dist_payee").attr("dataType", "Require").attr("msg", "请填写收款人姓名");
   		} else {
   			$("#btn_submit").attr("disabled", "true");
   			$("#dist_payee_span").hide();
   			$("#dist_payee").removAttr("dataType");
       	}
    });
      
	//提交表单
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 1)){
			if(confirm("确定要确认收货？")){
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
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>