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
    <!-- <div class="rtabcont2" style="padding-right: 30px;">
  		<input type="button" style="cursor: pointer;" class="but9" id="syncBtn" value="发送短信"></input>
   </div> -->
  <div class="rtabcont1">
  	<html-el:form action="/spgl/PshowOrderSendForTuan" method="post">
      <html-el:hidden property="id" styleId="id" value="${af.map.id}" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <html-el:hidden property="logistic_sn" styleId="logistic_sn" />
      <html-el:hidden property="order_from" styleId="order_from" />
      <html-el:hidden property="order_to" styleId="order_to" />
      <html-el:hidden property="type" styleId="type" />
      <html-el:hidden property="v_code" styleId="v_code" value="${af.map.v_code}" />
       <html-el:hidden property="trade_index" styleId="trade_index" value="${af.map.trade_index}"/>
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
            <td colspan="2"> <c:out value="${af.map.buyer_name}" /> </td>
          </tr>
          <tr>
            <td  class="title_item" width="15%">购买人手机号码：</td>
            <td colspan="2"><c:out value="${af.map.buyer_mp}" />
            </td>
            <td  class="title_item" width="15%">购买人固定电话：</td>
             <td colspan="2"><c:out value="${af.map.buyer_tel}" />
            </td>
          </tr>
          <tr>
            <td class="title_item" width="15%">支付方式：</td>
            <td colspan="2">
            <c:if test="${af.map.pay_way eq 0}">货到付款</c:if>
            <c:if test="${af.map.pay_way eq 1}">银行汇款</c:if>
            <c:if test="${af.map.pay_way eq 2}">支付宝</c:if>
            <c:if test="${af.map.pay_way eq 3}">银联</c:if>
            <c:if test="${af.map.pay_way eq 4}">财付通</c:if>
            <c:if test="${af.map.pay_way eq 5}">民生银行</c:if>
            <c:if test="${af.map.pay_way eq 8}">嘿客代收货款</c:if>
            </td>
            <td class="title_item" width="15%">所在地：</td>
            <td colspan="2">
            <c:out value="${p_index_name}" />
            </td>
          </tr>
           <tr>
            <td class="title_item" width="15%">支付单号：</td>
            <td colspan="2"><c:out value="${af.map.trade_no}" />
            </td>
            <td class="title_item" width="15%">收货地址：</td>
             <td colspan="2"><c:out value="${af.map.buyer_addr}" />
            </td>
          </tr>
          <tr>
	         <td class="title_item" width="15%">订单产品数量：</td><td colspan="2">${t_num }</td>
	         <td class="title_item" width="15%">订单总金额：</td><td colspan="2"><fmt:formatNumber value="${af.map.total_price}" pattern="0.00" /> (元)</td>
	      </tr>
	      <tr>
	         <td class="title_item" width="15%">抵扣金额：</td><td colspan="2">${fn:escapeXml(af.map.dedu_price)} (元)</td>
	         <td class="title_item" width="15%">应付金额：</td><td colspan="2"><fmt:formatNumber value="${af.map.pay_price}" pattern="0.00" /> (元)</td>
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
            <td class="title_item" width="15%" >客户备注：</td><td colspan="5"><c:out value="${af.map.logistic_sn}" />  </td>
          </tr>
          <tr>
            <td class="title_item" width="15%" >备注：</td><td colspan="5"><html-el:text property="remark"  maxlength="100" size="100" styleId="remark"></html-el:text>  </td>
          </tr>
          <tr>
          	 <td  class="title_item" width="15%">机器码：</td>
             <td colspan="5"><html-el:text property="code"  maxlength="256" size="100" styleId="code"></html-el:text></td>
          </tr>
        </table>
      </div>      
      <div align="left" style="margin-top: 10px">
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
          <tr>
          	<td colspan="6"  style="background:#CCCCCC" >商品明细信息</td>
          </tr>	
          <tr>
          <td class="title_item" width="30%" style="text-align: center;">(型号)商品名称</td>
		  <td class="title_item" width="8%" style="text-align: center;">数量</td>
          <td class="title_item" width="8%" style="text-align: center;">单价</td>
          <td class="title_item" width="8%" style="text-align: center;">总金额</td>
          <td class="title_item" width="8%" style="text-align: center;">增值服务</td>
          <td class="title_item" width="20%" style="text-align: center;">备注</td>
          </tr>          
          <c:forEach items="${pshowOrdeDetails}" var="cur">
          <tr>
          <td style="text-align: center;">(${cur.map.pd_sn })${cur.pd_name }</td>
          <td  nowrap="nowrap" style="text-align: center;">${cur.num }</td>
		  <td  nowrap="nowrap" style="text-align: center;">${cur.price }</td>
          <td  nowrap="nowrap" style="text-align: center;">${cur.total_price}</td>
          <td align="center">
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
            <th colspan="6">订单审核信息</th>
         </tr>
          <tr>
            <td colspan="6"><table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass"> 
                <tr>
                  <td class="title_item" width="5%" style="text-align:center;" nowrap="nowrap">序号</td>
                  <td class="title_item" width="18%" style="text-align:center;" nowrap="nowrap">登录名</td>
                   <td class="title_item" width="10%" style="text-align:center;" nowrap="nowrap">真实姓名</td>
                  <td class="title_item" width="18%" style="text-align:center;" nowrap="nowrap">操作时间</td>
                  <td class="title_item" width="18%" style="text-align:center;" nowrap="nowrap">订单应付金额</td>
                  <td class="title_item" width="18%" style="text-align:center;" nowrap="nowrap">审核结果</td>
                  <td class="title_item" style="text-align:center;" nowrap="nowrap">意见</td>
                </tr>
                <c:forEach items="${PshowOrdeAudits}" var="cur" varStatus="st">
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
          <tr >
           <td class="title_item" width="15%" style="text-align: center;" >选择物流：</td>
          	<td colspan="7">
              	<input type="radio"  name="epp_type"  id="r1" value="0" />不使用EPP顺丰
             	<input type="radio"  name="epp_type"  id="r2" value="1"/>使用EPP顺丰
            </td>
          </tr>
        <tr id="t2" style="display: none"> 
          <td width="15%" style="text-align: center;" >快递公司编号：</td>
          <td colspan="5">
          	<html-el:select property="express_id_2" styleId="express_id_2" styleClass="express_id_2">
          		<html-el:option value="">-请选择-</html-el:option>
          		 <c:forEach var="cur" items="${ecBaseExpressList2}">
                <html-el:option value="${cur.express_id}"  styleId="${cur.express_ui_type}">${cur.express_name}/
                <c:if test="${cur.express_ui_type eq 1}">顺丰物流</c:if>
                <c:if test="${cur.express_ui_type eq 100}">其他物流</c:if>
                </html-el:option>
              </c:forEach>
          	</html-el:select>
          </td>
          </tr>
          <tr id="t3" style="display: none">
          <td width="15%" style="text-align: center;" >快递公司编号：</td>
          <td colspan="5">
          	<html-el:select property="express_id" styleId="express_id" styleClass="express_id" >
          		<html-el:option value="">-请选择-</html-el:option>
          		 <c:forEach var="cur" items="${ecBaseExpressList}">
                <html-el:option value="${cur.express_id}"  styleId="${cur.express_ui_type}">${cur.express_name}/
                <c:if test="${cur.express_ui_type eq 1}">顺丰物流</c:if>
                <c:if test="${cur.express_ui_type eq 100}">其他物流</c:if>
                </html-el:option>
              </c:forEach>
          	</html-el:select>
          </td>
          </tr> 
        <tr>
          <td nowrap="nowrap" style="text-align: center;">审核意见：</td>
          <td colspan="5"><html-el:text property="remark1"  maxlength="200" style="width:70%"></html-el:text></td>
        </tr>
          <tr>
          	  <td colspan="6"  height="40" align="center"><input class="bgButtonSave" type="button" name="Submit4" value=" 保存 " id="btn_submit" />
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

var mobile="${af.map.buyer_mp}";
$(document).ready(function(){

	$("#r1").click(function(){
		$("#type").val("0");
		$("#t2").show();
		$("#t3").hide();
		$("#express_id").val("");  
		//$("#express_id_2").val("");     
	});
	$("#r2").click(function(){
		$("#type").val("1");  
		$("#t2").hide();
		$("#t3").show(); 
		//$("#express_id").val("");   
		$("#express_id_2").val("");  
	});
 
	var id = "${af.map.id}";
	//$("#syncBtn").click(function(){
		//$.ajax({
			//type: "POST" , 
			//url: "${ctx}/SendMobileMessage.do" , 
			//data:"method=sendMobileMessage&mobile=" + mobile + "&t=" + new Date(),
			//dataType: "json" , 
	       // async: true, 
	       // error: function (request, settings) {alert(" 数据加载请求失败！ "); },
	       // success: function (result) {
	        	//if (result.state == 1) { //短信验证码已发送
		        	//alert("发送成功");
				//} else { //短信验证码发送失败
					//alert("发送失败");
					//return false;
				//}
	       // } 
		//});

	//});

	var trade_index = "${af.map.trade_index}";
	$("#btn_submit").click(function(){

		var epp_type = $("input[name='epp_type']:checked").val();
		if(undefined == epp_type){
			alert("提示，请选择物流方式");
			return;
		}

		var v_code = $("#v_code").val();
		if(v_code=""){
			alert("验证码已经失效，请重新选择");
		}

		if($("#type").val()=="0"){
			var express_id_2 = $('#express_id_2 option:selected').val(); 
			if(null==express_id_2||""==express_id_2||undefined==express_id_2){
				alert("提示，请选择快递公司");
				return; 
			}
		}
		if($("#type").val()=="1"){  
			var express_id = $('#express_id option:selected').val(); 
			if(null==express_id||""==express_id||undefined==express_id){ 
				alert("提示，请选择快递公司");    
				return;
			}
		}

 
		//alert($("#express_id option:selected").attr("id"));
		//if(""==$("#express_id").val()||null==$("#express_id").val()){
			//alert("请选择快递公司！");
			//return;
		//}
		//var option=$("#express_id option:selected").attr("id");
		//var express_id=$("#express_id option:selected").val();
		//if(option=="1"){
			//$.ajax({
				//type: "POST",
				//url: "<c:url value='/manager/spgl/PshowOrderSendForTuan.do' />",
				//data: { "method":"getLogistic_sn","trade_index":trade_index,"express_id":express_id,"timestamp":new Date().getTime() },
				//dataType: "json",
				//sync: true,
				//error: function (xhr, ajaxOptions, thrownError) { alert("数据加载请求失败【" + xhr.statusText + "," + xhr.responseText + "," + xhr.status + "," + thrownError +"】！"); },
				//success: function(result) {
					//if(result.state=="1"){
						//alert(result.logistic_sn);
						//alert(result.order_from);
						//alert(result.order_from);
						//$("#logistic_sn").val(result.logistic_sn);
						//$("#order_from").val(result.order_from);
						//$("#order_to").val(result.order_from);
						//if(null!=result.logistic_sn&&""!=result.logistic_sn){
							//sendMessage(result.logistic_sn);
							if(Validator.Validate(this.form, 3)){
								$("#btn_submit").attr("disabled",true);
								$("#btn_back").attr("disabled",true);
								this.form.submit();
							}
						//}else{
							//alert("订单没有进入顺丰系统，请重新提交");
							//return;
						//}
					//}else{
						//alert("订单没有进入顺丰系统，请重新提交");
						//return;
					//}
						
				//}
			//}); 
		//}else{
			//if(Validator.Validate(this.form, 3)){
				//$("#btn_submit").attr("disabled",true);
				//$("#btn_back").attr("disabled",true);
				//this.form.submit();
			//}
		//}
		
	});
});

function sendMessage(logistic_sn){
	alert(logistic_sn);
	if (null!=logistic_sn&&""!=logistic_sn) {
		$.ajax({
			type: "POST" , 
			url: "PshowOrderSendForTuan.do" , 
			data:"method=sendMobileMessage&mobile=" + mobile +"&logistic_sn=" + logistic_sn + "&t=" + new Date(),
			dataType: "json" , 
	        async: true, 
	        error: function (request, settings) {alert(" 短信发送失败，请重新发送！ "); },
	        success: function (result) {
	        	if (result.code == 2) { //短信发送成功
	        		alert("短信发送成功");
	        	} else { //短信验证码发送失败
					alert("短信发送失败，请重新发送");
					return false;
				}
	        } 
		});
	}
}


//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
