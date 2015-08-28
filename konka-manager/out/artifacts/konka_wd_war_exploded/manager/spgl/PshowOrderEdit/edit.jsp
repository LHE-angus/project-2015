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
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<style type="text/css">
select {
	font-family:Microsoft YAHEI;
	font-size:12px;
}
input {
	font-family:Microsoft YAHEI;
	font-size:12px;
}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oartop">
  <table width="400" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
      <td>当前位置：${naviString}</td>
    </tr>
  </table>
</div>
<div>
  <div class="rtabcont1">
  	<html-el:form action="/spgl/PshowOrderEdit" method="post">
      <html-el:hidden property="id" styleId="id" value="${af.map.id}" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <div class="rtabcont1">
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
          <tr>
          	<td colspan="6"  style="background:#CCCCCC" >订单信息</td>
          </tr>	
          <tr>
            <td class="title_item" width="15%" >交易流水号：</td>
            <td colspan="2"><c:out value="${af.map.trade_index}" /></td>
            <td class="title_item"  width="15%">下单人姓名：</td>
            <td colspan="2"><c:out value="${af.map.order_user_name}" /> </td>
          </tr>
          <tr>
            <td class="title_item"  width="15%">下单人姓名：</td>
            <td colspan="2" ><c:out value="${af.map.order_user_name}" /></td>
            <td class="title_item"  width="15%">购买人姓名：</td>
            <td colspan="2"> <html-el:text property="buyer_name" styleId="buyer_name" maxlength="20"/>
            </td>
          </tr>
          <tr>
            <td  class="title_item" width="15%">购买人手机号码：</td>
            <td colspan="2">
            <html-el:text property="buyer_mp" styleId="buyer_mp" maxlength="11" />
            </td>
            <td  class="title_item" width="15%">购买人固定电话：</td>
             <td colspan="2">
            <html-el:text property="buyer_tel" styleId="buyer_tel" maxlength="20"/>
            </td>
          </tr>
          <tr>
            <td class="title_item" width="15%">支付方式：</td>
            <td colspan="2">
            <html-el:radio property="pay_way" value="0">货到付款</html-el:radio>
            <html-el:radio property="pay_way" value="1">银行汇款</html-el:radio>
            <html-el:radio property="pay_way" value="2">支付宝</html-el:radio>
            <html-el:radio property="pay_way" value="3">银联</html-el:radio>
            <html-el:radio property="pay_way" value="4">财付通</html-el:radio>
            <html-el:radio property="pay_way" value="5">民生银行</html-el:radio>
             <html-el:radio property="pay_way" value="8">嘿客代收货款</html-el:radio>
            </td>
            <td  class="title_item" width="15%">收货人详细地址：</td>
             <td colspan="2">
            <html-el:text property="buyer_addr" styleId="buyer_addr" maxlength="30" size="40"/>
            </td>
          </tr>
           <tr>
            <td class="title_item" width="15%">支付单号：</td>
            <td colspan="2">
            <html-el:text property="trade_no" styleId="trade_no" maxlength="30" />
            </td>
            <td class="title_item" width="15%">订单状态：</td>
            <td colspan="2">
           <html-el:select property="state" styleId="state">
           		<html-el:option value="-30">已退货</html-el:option>
           		<html-el:option value="-20">审核未通过</html-el:option>
           		<html-el:option value="-10">已关闭</html-el:option>
           		<html-el:option value="0">已预订</html-el:option>
           		<html-el:option value="5">待确认</html-el:option>
           		<html-el:option value="10">已确认</html-el:option>
           </html-el:select>
            </td>
          </tr>
         <tr>
	          <td class="title_item" width="15%">订单产品数量：</td>
	          <td colspan="2">${t_num }</td>
	          <td class="title_item" width="15%">订单总金额：</td>
	          <td colspan="2"><fmt:formatNumber value="${af.map.total_price}" pattern="0.00" /> (元)</td>
         </tr>
         <tr>
          	 <td class="title_item" width="15%">抵扣金额：</td>
             <td colspan="2"><fmt:formatNumber value="${af.map.dedu_price}" pattern="0.00" /> (元)</td>
             <td class="title_item" width="15%">应付金额：</td>
             <td colspan="2"> <html-el:text property="pay_price" styleId="pay_price" size="15" maxlength="10"/>(元)</td>
          </tr>
          <tr>
            <td class="title_item" width="15%">收货人所属地区：</td>
            <td colspan="5"><select name="province" id="province" class="bd" >
                <option value="">-请选择省/直辖市/自治区-</option>
              </select>
              <select name="city" id="city" class="bd">
                <option value="">-请选择市-</option>
              </select>
              <select name="country" id="country" class="bd">
                <option value="">-请选择县-</option>
              </select>
              <select name="town" id="town" class="bd">
                <option value="">-请选择乡/镇-</option>
              </select></td>
          </tr>
          <tr>
             <td  class="title_item" width="15%">发票类型：</td>
             <td colspan="2">
             	<html-el:radio property="bill_type" value="0">普通发票</html-el:radio>
            	<html-el:radio property="bill_type" value="1">增值税发票</html-el:radio>
             </td>
              <td  class="title_item" width="15%">是否二次配送：</td>
             <td colspan="2">
             	<html-el:radio property="is_ps" value="0">否</html-el:radio>
            	<html-el:radio property="is_ps" value="1">是</html-el:radio>
             </td>
          </tr>
           <tr>
             <td  class="title_item" width="15%">发票抬头：</td>
             <td colspan="2">
             	<html-el:radio property="bill_head" value="0">个人</html-el:radio>
            	<html-el:radio property="bill_head" value="1">单位</html-el:radio>
             </td>
              <td  class="title_item" width="15%">发票单位：</td>
             <td colspan="2">
             	<html-el:text property="bill_company"  size="25" maxlength="25" ></html-el:text>
             </td>
          </tr>
          <tr>
             <td  class="title_item" width="15%">订单类型：</td>
             <td colspan="2">
             	<html-el:select property="order_type" styleId="order_type" > 
             		<html-el:option value="">--请选择--</html-el:option>
             		<html-el:option value="0">普通订单</html-el:option>
             		<html-el:option value="1">处理机</html-el:option>
	              <html-el:option value="2">零售</html-el:option>
	              <html-el:option value="3">碎屏重发</html-el:option>
	              <html-el:option value="4">退货</html-el:option>
	               <html-el:option value="6">团购</html-el:option>
             		<html-el:option value="9">大宗采购</html-el:option>
             	</html-el:select>
             </td>
              <td  class="title_item" width="15%">是否索要发票：</td>
             <td colspan="2">
             	<html-el:radio property="bill_is_add" value="0">否</html-el:radio>
            	<html-el:radio property="bill_is_add" value="1">是</html-el:radio>
             </td>
          </tr>
          <tr>
             <td  class="title_item" width="15%">送货日期：</td>
             <td colspan="5">
             	<html-el:radio property="deliver_time" value="0">只双休日、假日送货（工作日不用送）</html-el:radio>
            	<html-el:radio property="deliver_time" value="1">工作日、双休日与假日均可送货</html-el:radio>
            	<html-el:radio property="deliver_time" value="2">只工作日送货（双休日、假日不用送）</html-el:radio>
             </td>
          </tr>
          <c:if test="${not empty af.map.ecVouchersList }">
			<tr>
			    <td class="title_item" width="15%" >使用购物券：</td>
			    <td colspan="5"><c:forEach items="${af.map.ecVouchersList }" var="cur">
			            ${fn:escapeXml(cur.title)} &nbsp;￥<font color="red"> ${fn:escapeXml(cur.price)}</font>元<br/>
			     </c:forEach>
				</td>
			</tr>
			</c:if>
		  <tr>
          <td class="title_item" width="15%" >客户备注：</td>
          <td colspan="5"><html-el:text property="logistic_sn"  maxlength="200" style="width:70%" ></html-el:text></td>
          </tr>	
          <tr>  
          <td class="title_item" width="15%" >备注：</td>
          <td colspan="5"><html-el:text property="remark"  maxlength="200" style="width:70%" ></html-el:text></td>   
          </tr>
        </table>      
      <div style="margin-top: 10px">
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
          <tr>
          	<td colspan="6"  style="background:#CCCCCC" >商品明细信息</td>
          </tr>	
          <tr>
          <td class="title_item" width="30%" style="text-align: center;">(型号)商品名称</td>
		  <td class="title_item" width="8%" style="text-align: center;">数量</td>
          <td class="title_item" width="8%" style="text-align: center;">单价</td>
          <td class="title_item" width="8%" style="text-align: center;">总金额</td>
          <td class="title_item" width="8%" style="text-align: center;">奖励积分</td>
          <td class="title_item" width="8%" style="text-align: center;">付款积分</td>
          <td class="title_item" width="8%" style="text-align: center;">佣金</td>
           <td class="title_item" width="8%" style="text-align: center;">增值服务</td>
          <td class="title_item" width="20%" style="text-align: center;">备注说明</td>
          </tr>
          
          <c:forEach items="${pshowOrdeDetails}" var="cur">
          <tr>
          <td style="text-align: left;">(${cur.map.pd_sn })${cur.pd_name }</td>
          <td nowrap="nowrap" style="text-align: center;">${cur.num }</td>
		  <td nowrap="nowrap" style="text-align: center;">${cur.price }</td>
          <td nowrap="nowrap" style="text-align: center;">${cur.total_price}</td>
          <td nowrap="nowrap" style="text-align: center;"><html-el:text property="integral"  value="${cur.integral}" maxlength="10" size="10" onblur="yy(this)"></html-el:text></td>
          <td nowrap="nowrap" style="text-align: center;"><html-el:text property="pay_integral"  value="${cur.pay_integral}" maxlength="10" size="10" onblur="yy(this)"></html-el:text></td>  
           <td nowrap="nowrap" style="text-align: center;"><html-el:text property="rebates"   value="${not empty cur.rebates ? cur.rebates:0}" maxlength="10" size="10" onblur="yy(this)" readonly="${cur.rebates_status eq 4 ? 'true':''}" /></td>    
          <html-el:hidden property="detai_id" value="${cur.bill_item_id}" />
          <html-el:hidden property="rebates_status" value="${cur.rebates_status}" />
          <td nowrap="nowrap" style="text-align: center;">
						 <c:forEach items="${bddetailsList}" var="cu">
						 	<c:if test="${cu.details_id eq cur.bill_item_id}">
						 		${cu.goods_name} ￥${cu.price}<br />
						 	</c:if>
						 </c:forEach>
				  </td>
          <td style="text-align: left;"><c:if test="${cur.state eq 1 }">退货&nbsp;</c:if><c:if test="${cur.state eq 2 }">换货&nbsp;</c:if>${cur.remark }</td>
          </tr>
          </c:forEach>
           <tr>
            <th colspan="9">订单审核信息</th>
         </tr>
          <tr>
            <td colspan="9"><table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass"> 
                <tr>
                  <td class="title_item" width="5%" style="text-align:center;" nowrap="nowrap">序号</td>
                  <td class="title_item" width="18%" style="text-align:center;" nowrap="nowrap">登录名</td>
                   <td class="title_item" width="10%" style="text-align:center;" nowrap="nowrap">真实姓名</td>
                  <td class="title_item" width="18%" style="text-align:center;" nowrap="nowrap">操作时间</td>
                  <td class="title_item" width="18%" style="text-align:center;" nowrap="nowrap">订单应付金额</td>
                  <td class="title_item" width="18%" style="text-align:center;" nowrap="nowrap">审核结果</td>
                  <td class="title_item" style="text-align:center;" nowrap="nowrap">意见</td>
                </tr>
                <c:forEach items="${pshowOrdeAudits}" var="cur" varStatus="st">
                  <tr>
                    <td align="center" >${st.count}</td>
                    <td align="center">${cur.opr_user_real_name } </td>
                     <td align="center">${cur.map.real_name} </td>
                    <td align="center"><fmt:formatDate value="${cur.oper_date }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td align="center">${cur.total_price}</td>
                    <td align="center"><c:if test="${cur.state eq -1}">已取消</c:if>
			            <c:if test="${cur.state eq -30 }">已退货</c:if>
				         <c:if test="${cur.state eq -20 }">审核未通过</c:if>
				         <c:if test="${cur.state eq -10 }">已关闭</c:if>
				         <c:if test="${cur.state eq 0 }">已预订</c:if>
				         <c:if test="${cur.state eq 5 }">待确认</c:if>
				         <c:if test="${cur.state eq 10 }">已确认</c:if>
				         <c:if test="${cur.state eq 20 }">审核通过</c:if>
				         <c:if test="${cur.state eq 30 }">下发处理</c:if>
				         <c:if test="${cur.state eq 40 }">商家发货</c:if>
				         <c:if test="${cur.state eq 50 }">客户已换货</c:if>
				         <c:if test="${cur.state eq 60 }">确认收货</c:if>
				         <c:if test="${cur.state eq 70 }">确认回款</c:if>
				         <c:if test="${cur.state eq 80 }">退货</c:if>
					     <c:if test="${cur.state eq 90 }">换货</c:if>
				         <c:if test="${af.map.pay_way eq 9}">（线下处理）</c:if>
				         </td>
                    <td align="left">${cur.remark}</td>
                  </tr>
                </c:forEach>
              </table></td>
         </tr>
          <tr>
          <td class="title_item" width="15%" style="text-align: center;" >修改说明：</td>
          <td colspan="8"><html-el:text property="remark1"  maxlength="200" style="width:70%"></html-el:text></td>
          </tr>
          <tr>
          	  <td colspan="9" height="40" align="center"><input class="bgButtonSave" type="button" name="Submit4" value=" 保存 " id="btn_submit" />
              <input class="bgButtonBack" type="button" name="Submit5" value="返回" onclick="history.back();return false;" id="btn_back" /></td>
        		</tr>
        </table> 
      </div> 
      </div>
      </html-el:form> 
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${province}","dataType":"Require","msg":"请选择省/直辖市/自治区！"});
	$("#city").attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${city}","datatype": "Require", "msg": "请选择市名称！"});
	$("#country").attr({"subElement": "town","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${country}"});
	$("#town").attr({"defaultText": "-请选择乡/镇-", "defaultValue": "", "selectedValue": "${town}"});
	
	$("#province").cs_ext("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false,"p_index_join","${af.map.p_index_join}");
	$("#province").change();

	$("#order_type").attr("dataType", "Require").attr("msg", "请选择订单类型！");

	$("#total_price").keypress(function(){//单价
		var price = $("#total_price").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(price)) {
			$("#total_price").val(0);
		}
	}).keyup(function(){
		var price = $("#total_price").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(price)) {
			$("#total_price").val(0);
		}
	}).blur(function(){
		var price = $("#total_price").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(price)) {
			$("#total_price").val(0);
		}
	});	

	
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 3)){
				$("#btn_submit").attr("disabled",true);
				$("#btn_back").attr("disabled",true);
				this.form.submit();
		}
	});
});

function yy(obj){
	var integel = obj.value;
	var _reg = /^[\+]?\d*?\.?\d*?$/;
	if (!_reg.test(integel)) {
		obj.value=0;
	}
}


//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
