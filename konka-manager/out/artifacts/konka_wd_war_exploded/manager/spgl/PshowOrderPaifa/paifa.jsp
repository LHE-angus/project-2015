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
  	<html-el:form action="/spgl/PshowOrderPaifa" method="post">
      <html-el:hidden property="id" styleId="id" value="${af.map.id}" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <html-el:hidden property="type" styleId="type" value="1"/>
      <div class="rtabcont1">
       <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
          <tr>
          	<td colspan="7"  style="background:#CCCCCC" >订单信息</td>
          </tr>	
          <tr>
            <td class="title_item" width="15%" >交易流水号：</td>
            <td colspan="4"><c:out value="${af.map.trade_index}" /></td>
            <td class="title_item"  width="15%">下单人姓名：</td>
            <td colspan="1"><c:out value="${af.map.order_user_name}" /> </td>
          </tr>
          <tr>
            <td class="title_item"  width="15%">下单人姓名：</td>
            <td colspan="4" ><c:out value="${af.map.order_user_name}" /></td>
            <td class="title_item"  width="15%">购买人姓名：</td>
            <td colspan="1"> <c:out value="${af.map.buyer_name}" /> </td>
          </tr>
          <tr>
            <td  class="title_item" width="15%">购买人手机号码：</td>
            <td colspan="4"><c:out value="${af.map.buyer_mp}" />
            </td>
            <td  class="title_item" width="15%">购买人固定电话：</td>
             <td colspan="1"><c:out value="${af.map.buyer_tel}" />
            </td>
          </tr>
          <tr>
            <td class="title_item" width="15%">支付方式：</td>
            <td colspan="4">
             <c:if test="${af.map.pay_way eq 0}">货到付款</c:if>
            <c:if test="${af.map.pay_way eq 1}">银行汇款</c:if>
            <c:if test="${af.map.pay_way eq 2}">支付宝</c:if>
            <c:if test="${af.map.pay_way eq 3}">银联</c:if>
             <c:if test="${af.map.pay_way eq 4}">财付通</c:if>
             <c:if test="${af.map.pay_way eq 5}">民生银行</c:if>
              <c:if test="${af.map.pay_way eq 8}">嘿客代收货款</c:if>
            </td>
            <td class="title_item" width="15%">所在地：</td>
            <td colspan="1">
            <c:out value="${p_index_name}" />
            </td>
          </tr>
           <tr>
            <td class="title_item" width="15%">支付单号：</td>
            <td colspan="4"><c:out value="${af.map.trade_no}" />
            </td>
            <td class="title_item" width="15%">收货地址：</td>
             <td colspan="1"><c:out value="${af.map.buyer_addr}" />
            </td>
          </tr>
           <tr>
          <td class="title_item" width="15%">发货前电话确认：</td>
          <td colspan="4">
            <c:if test="${af.map.deliver_is_call eq 0}">否</c:if>
            <c:if test="${af.map.deliver_is_call eq 1}">是</c:if>
            </td>
          <td class="title_item" width="15%">发票是否索要：</td>
          <td colspan="1">
          <c:if test="${af.map.bill_is_add eq 0}">否</c:if>
            <c:if test="${af.map.bill_is_add eq 1}">是</c:if></td>
        </tr>
        <tr>
          <td class="title_item" width="15%">发票类型：</td>
          <td colspan="4">
            <c:if test="${af.map.bill_type eq 0}">普通发票</c:if>
            <c:if test="${af.map.bill_type eq 1}">增值税发票</c:if>
            </td>
          <td class="title_item" width="15%">发票抬头：</td>
          <td colspan="1">
          <c:if test="${af.map.bill_head eq 0}">个人</c:if>
            <c:if test="${af.map.bill_head eq 1}">单位</c:if></td>
        </tr>
        <tr>
          <td class="title_item" width="15%">发票内容：</td>
          <td colspan="4">
            <c:if test="${af.map.bill_content eq 0}">明细</c:if>
            <c:if test="${af.map.bill_content eq 1}">办公用品</c:if>
            <c:if test="${af.map.bill_content eq 2}">电脑配件</c:if>
            <c:if test="${af.map.bill_content eq 3}">耗材</c:if>
            </td>
          <td class="title_item" width="15%">发票单位：</td>
          <td colspan="1">
          	${af.map.bill_company}
          </td>
        </tr>   
          <tr>
	         <td class="title_item" width="15%">订单产品数量：</td><td colspan="4">${t_num }</td>
	         <td class="title_item" width="15%">订单总金额：</td><td colspan="1"><fmt:formatNumber value="${af.map.total_price}" pattern="0.00" /> (元)</td>
	      </tr>
	      <tr>
	         <td class="title_item" width="15%">抵扣金额：</td><td colspan="4">${fn:escapeXml(af.map.dedu_price)} (元)</td>
	         <td class="title_item" width="15%">应付金额：</td><td colspan="1"><fmt:formatNumber value="${af.map.pay_price}" pattern="0.00" /> (元)</td>
	      </tr>
	      <c:if test="${not empty af.map.ecVouchersList }">
		<tr>
			<td class="title_item" width="15%" >使用购物券：</td>
			<td colspan="6"><c:forEach items="${af.map.ecVouchersList }" var="cur">
			            ${fn:escapeXml(cur.title)} &nbsp;￥<font color="red"> ${fn:escapeXml(cur.price)}</font>元<br/>
			     </c:forEach>
			</td>
		</tr>
		</c:if>
		<tr>
            <td class="title_item" width="15%" >客户备注：</td><td colspan="6"><c:out value="${af.map.logistic_sn}" />  </td>
          </tr>
          <tr>
            <td class="title_item" width="15%" >备注：</td><td colspan="6"><c:out value="${af.map.remark}" />  </td>
          </tr>
        </table>
      </div> 
      <div align="left" style="margin-top: 30px">
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
          <td class="title_item" width="8%" style="text-align: center;">增值服务</td>
          <td class="title_item" width="8%" style="text-align: center;">库存</td>
          <td class="title_item" width="20%" style="text-align: center;">备注</td>
          </tr>          
          <c:forEach items="${pshowOrdeDetails}" var="cur">
           <tr>
          <td style="text-align: left;">(${cur.map.pd_sn })${cur.pd_name }</td>
          <td  nowrap="nowrap" style="text-align: center;">${cur.num }</td>
		  <td  nowrap="nowrap" style="text-align: center;">${cur.price }</td>
          <td  nowrap="nowrap" style="text-align: center;">${cur.total_price}</td>
          <td nowrap="nowrap" style="text-align: center;"><html-el:text property="integral"  value="${cur.integral}" maxlength="10" size="10" onblur="yy(this)"></html-el:text></td>
          <html-el:hidden property="detai_id" value="${cur.bill_item_id}" />
          <td  nowrap="nowrap" style="text-align: center;">
						 <c:forEach items="${bddetailsList}" var="cu">
						 	<c:if test="${cu.details_id eq cur.bill_item_id}">
						 		${cu.goods_name} ￥${cu.price}<br />
						 	</c:if>
						 </c:forEach>
		   </td>
		  <td nowrap="nowrap" style="text-align: center;">${cur.map.goods_count}</td>
          <td style="text-align: left;"><c:if test="${cur.state eq 1 }">退货&nbsp;</c:if><c:if test="${cur.state eq 2 }">换货&nbsp;</c:if>${cur.remark }</td>
          </tr>
          </c:forEach>
          <tr>
          <td class="title_item" width="15%" style="text-align: center;" >处理方式：</td>
          	<td colspan="7">
              	<input type="radio"  name="sex"  id="r1" value="0" />总部处理
             	<input type="radio"  name="sex" checked="checked" id="r2" value="1"/>派发处理
            </td>
          </tr>
          <tr id="t2">
          <td class="title_item" width="15%" style="text-align: center;">派发：</td>
          <td colspan="7"><html-el:select property="dept_id" styleId="dept_id" style="width:120px;">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${sybDeptInfoList}">
                <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
              </c:forEach>
            </html-el:select>&nbsp;&nbsp;</td>
       	  </tr>
       	   <tr id="t3" style="display: none">
          <td class="title_item" width="15%" style="text-align: center;">派发：</td>
          <td colspan="7"><html-el:select property="dept_id_2" styleId="dept_id_2" style="width:120px;">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${kkList}">
                <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
              </c:forEach>
            </html-el:select>&nbsp;&nbsp;</td>
       	  </tr>
         <tr>
          <td class="title_item" width="15%" style="text-align: center;">审核意见：</td>
          <td colspan="7"><html-el:text property="remark1"  maxlength="200" style="width:70%"></html-el:text></td>
        </tr>
          <tr>
          	  <td colspan="8"  height="40" align="center"><input class="bgButtonSave" type="button" name="Submit4" value=" 保存 " id="btn_submit" />
              <input class="bgButtonBack" type="button" name="Submit5" value="返回" onclick="history.back();return false;" id="btn_back" /></td>
        		</tr>
        </table>
      </div>
      </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("input[name='radio']").each(function(){
		if(!document.all("sex")[0].checked){
		$("#t2").css("display","none");
		$("#t3").css("display","block");
		}
		if(document.all("sex")[1].checked){
		$("#t2").css("display","block");
		$("#t3").css("display","none");
		}
	});

	$("#r1").click(function(){
		$("#type").val(0);
		$("#t2").hide();
		$("#t3").show();
	});
	$("#r2").click(function(){
		$("#type").val(1);
		$("#t2").show();
		$("#t3").hide();
	});
	
	$("#btn_submit").click(function(){
		if($("#type").val() == 1){
			$("#dept_id").attr("datatype", "Require").attr("msg", "请选择分公司！");
			$("#dept_id_2").removeAttr("dataType").removeAttr("msg").val("");
		}
		if($("#type").val() == 0){
			$("#dept_id_2").attr("datatype", "Require").attr("msg", "请选择分公司！");
			$("#dept_id").removeAttr("dataType").removeAttr("msg").val("");
		}
		
		if(Validator.Validate(this.form, 3)){
			
			//var is_jf = $("input[name='is_jf']:checked").val();
			//if(is_jf == 1){
				//if(!confirm("您确定该订单不送奖励积分吗?")){
					//return false;
				//}
			//}
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
