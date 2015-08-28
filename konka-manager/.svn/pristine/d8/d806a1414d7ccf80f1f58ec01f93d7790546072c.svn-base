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
select {font-family:Microsoft YAHEI;font-size:12px;}input {font-family:Microsoft YAHEI;font-size:12px;}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oartop">
<table width="400" border="0" cellpadding="0" cellspacing="0"><tr><td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td><td>当前位置：${naviString}</td></tr></table>
</div>
<div>
<div class="rtabcont1">
<html-el:form action="/spgl/PshowOrderExchange" method="post">
<html-el:hidden property="id" styleId="id" value="${af.map.id}" />
<html-el:hidden property="mod_id" styleId="mod_id" />
<html-el:hidden property="order_type" styleId="order_type" />
<html-el:hidden property="method" styleId="method" value="save" />
<html-el:hidden property="queryString" styleId="queryString" />
<input type="hidden" name="details_ids" id="details_ids"/>
<input type="hidden" name="details_nums" id="details_nums"/> 
     <div class="rtabcont1">
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
          <tr><td colspan="6"  style="background:#CCCCCC" >订单信息</td></tr>	
          <tr>
            <td class="title_item" width="15%" >交易流水号：</td><td colspan="2"><c:out value="${af.map.trade_index}" /></td>
            <td class="title_item"  width="15%">下单人姓名：</td><td colspan="2"><c:out value="${af.map.order_user_name}" /> </td>
          </tr>
          <tr>
            <td class="title_item"  width="15%">下单人姓名：</td><td colspan="2" ><c:out value="${af.map.order_user_name}" /></td>
            <td class="title_item"  width="15%">购买人姓名：</td><td colspan="2"> <c:out value="${af.map.buyer_name}" /> </td>
          </tr>
          <tr>
            <td class="title_item" width="15%">购买人手机号码：</td><td colspan="2"><c:out value="${af.map.buyer_mp}" /></td>
            <td class="title_item" width="15%">购买人固定电话：</td><td colspan="2"><c:out value="${af.map.buyer_tel}" /></td>
          </tr>
          <tr>
            <td class="title_item" width="15%">支付方式：</td><td colspan="2"><c:if test="${af.map.pay_way eq 0}">货到付款</c:if><c:if test="${af.map.pay_way eq 1}">银行汇款</c:if><c:if test="${af.map.pay_way eq 2}">支付宝</c:if><c:if test="${af.map.pay_way eq 3}">银联</c:if><c:if test="${af.map.pay_way eq 4}">财付通</c:if><c:if test="${af.map.pay_way eq 5}">民生银行</c:if><c:if test="${af.map.pay_way eq 8}">嘿客代收货款</c:if></td>
            <td class="title_item" width="15%">所在地：</td><td colspan="2"> <c:out value="${p_index_name}" /> </td>
          </tr>
          <tr>
            <td class="title_item" width="15%">支付单号：</td><td colspan="2"><c:out value="${af.map.trade_no}" /></td>
            <td class="title_item" width="15%">收货地址：</td><td colspan="2"><c:out value="${af.map.buyer_addr}" /></td>
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
	         <td class="title_item" width="15%" >备注：</td><td colspan="5"><c:out value="${af.map.remark}" />  </td>
	      </tr>
        </table>
        <c:if test="${not empty af.map.pshowOrdeExchangeList }">
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass" style="margin-top: 5px">    
          <tr>
            <td colspan="7" align="left">退换货跟踪表</td>
         </tr>
          <tr>
            <td colspan="7" align="left">
            <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
            <tr class="tabtt1">
	           <td width="5%" nowrap="nowrap" align="center" rowspan="2">序号</td> 
	           <td width="5%" nowrap="nowrap" align="center" rowspan="2">型号</td> 
	           <td width="5%" nowrap="nowrap" align="center" rowspan="2">数量</td>
	           <td width="5%" nowrap="nowrap" align="center" rowspan="2">金额</td> 
	           <td width="25%" nowrap="nowrap" align="center" colspan="5">客服跟踪</td>
	           <td width="20%" nowrap="nowrap" align="center" colspan="4">财务跟踪</td>  
          </tr>
          <tr class="tabtt1">
	          <td width="5%" nowrap="nowrap" align="center">机器状态</td>
	          <td width="5%" nowrap="nowrap" align="center">退机类型</td>
	          <td width="5%" nowrap="nowrap" align="center">是否换货</td>
	          <td width="5%" nowrap="nowrap" align="center">报险方式</td>
	          <td width="5%" nowrap="nowrap" align="center">报险金额</td>
	          <td width="5%" nowrap="nowrap" align="center">机器入库</td>
	          <td width="5%" nowrap="nowrap" align="center">财务核销</td>
	          <td width="5%" nowrap="nowrap" align="center">理赔是否到账</td>
	          <td width="5%" nowrap="nowrap" align="center">退款金额</td>
          </tr>          
          <c:forEach var="cur" items="${af.map.pshowOrdeExchangeList}" varStatus="vs">
            <tr>
	         <td height="28"  align="center">${vs.count}</td>
	         <td align="center"><c:out value="${cur.pshowOrdeDetails.map.pd_sn}" /></td>
	         <td align="center"><c:out value="${cur.pshowOrdeDetails.num}" /></td>
	         <td align="center"><c:out value="${cur.pshowOrdeDetails.total_price}" /></td> 
	         <td align="center">
	         <c:if test="${cur.pd_step eq 1 }">客户家里</c:if>  <c:if test="${cur.pd_step eq 2 }">拉回在途</c:if><c:if test="${cur.pd_step eq 3 }">分公司</c:if>
	         </td> 
	         <td align="left">
	         <c:if test="${cur.exchange_info eq 1 }">质量机退货</c:if>  <c:if test="${cur.exchange_info eq 2 }">拒收</c:if><c:if test="${cur.exchange_info eq 3 }">好机退货</c:if>
	         </td>
	         <td align="center"><c:if test="${cur.is_exchange eq 1 }">退货</c:if>  <c:if test="${cur.is_exchange eq 2 }">换货</c:if></td>
	       	 <td align="center"><c:if test="${cur.insurance_way eq 1 }">顺丰理赔</c:if>  <c:if test="${cur.insurance_way eq 2 }">保险+顺丰理赔</c:if><c:if test="${cur.insurance_way eq 3 }">保险理赔</c:if></td>
	       	 <td align="center"><c:out value="${cur.insurance_price}" /></td>
	       	 <td align="center"><c:if test="${cur.pd_store eq 1 }">未入库</c:if>  <c:if test="${cur.pd_store eq 2 }">入库</c:if></td>
	       	 <td align="center"><c:if test="${cur.exchange_state eq 1 }">未退换货</c:if>  <c:if test="${cur.exchange_state eq 2 }">已退换货</c:if></td>
	       	 <td align="center"><c:if test="${cur.insurance_state eq 1 }">未到账</c:if>  <c:if test="${cur.insurance_state eq 2 }">已到账</c:if></td>
	       	 <td align="center"><c:out value="${cur.exchange_price}" /></td>
	       	</tr> 
          </c:forEach>
             </table>
            </td>
         </tr>
         </table>
         </c:if>
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass" style="margin-top: 5px">    
	        <tr>
			    <th colspan="5"><span id="shouMsg" onclick="shouMsg();" style="cursor: pointer">订单审核信息（收起-）</span></th>
			</tr><c:if test="${empty af.map.par_order_id}"> 
            <tr>
          		<td align="left" colspan="5">&nbsp;客户下单时间 ：<fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td> 
            </tr></c:if>
         </table>
         <div id="audt_msg"  > 
            	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass" id="audt_table">		       
	                <tr>
	                  <td class="title_item" width="5%" style="text-align:center;" nowrap="nowrap">序号</td>
	                  <td class="title_item" width="8%" style="text-align:center;" nowrap="nowrap">登录名</td>
	                  <td class="title_item" width="8%" style="text-align:center;" nowrap="nowrap">真实姓名</td>
	                  <td class="title_item" width="8%" style="text-align:center;" nowrap="nowrap">操作时间</td>
	                  <td class="title_item" width="8%" style="text-align:center;" nowrap="nowrap">订单应付金额</td>
	                  <td class="title_item" width="8%" style="text-align:center;" nowrap="nowrap">审核结果</td>
	                  <td class="title_item" width="25%" style="text-align:left;" nowrap="nowrap">意见</td>
	                </tr><c:forEach items="${pshowOrdeAudits}" var="cur" varStatus="st">
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
	                 </tr></c:forEach>
              </table> 
        </div>     
       <div align="left" style="margin-top: 5px">
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">        
          <tr><th colspan="7" align="center">选择退换货商品</th></tr>	
          <tr>
	          <td class="title_item" width="20%" style="text-align: center;">(型号)商品名称</td>
			  <td class="title_item" width="8%" style="text-align: center;">单价*数量</td> 
	          <td class="title_item" width="8%" style="text-align: center;">总金额</td>
	          <td class="title_item" width="8%" style="text-align: center;">佣金</td>
	          <td class="title_item" width="8%" style="text-align: center;">奖励积分</td>
	          <td class="title_item" width="8%" style="text-align: center;">增值服务</td> 
	          <td class="title_item" width="25%" style="text-align: left;"> 退换货商品|数量</td> 
          </tr><c:forEach items="${pshowOrdeDetails}" var="cur">
          <tr>
	          <td style="text-align: left;">(${cur.map.pd_sn })${cur.pd_name }</td>
	          <td nowrap="nowrap" style="text-align: center;">${cur.price }*${cur.num }</td> 
	          <td nowrap="nowrap" style="text-align: center;">${cur.total_price}</td>
	           <td nowrap="nowrap" style="text-align: center;">${cur.rebates}</td>
	            <td nowrap="nowrap" style="text-align: center;">${cur.integral}</td>
	          <td nowrap="nowrap" style="text-align: left;">
				<c:forEach items="${bddetailsList}" var="cu"><c:if test="${cu.details_id eq cur.bill_item_id}">
					${cu.goods_name} ￥${cu.price}<br />
				</c:if></c:forEach>
			  </td>
	          <td nowrap="nowrap" style="text-align: left;">&nbsp;&nbsp;
	          <input type="hidden" name="price_${cur.bill_item_id}" value="${cur.total_price }" id="price${cur.bill_item_id}"/>
	          <input type="hidden" name="num_${cur.bill_item_id}" value="${cur.num }" id="num${cur.bill_item_id}"/>
	          <input type="checkbox" name="details_id" value="${cur.bill_item_id}" id="id${cur.bill_item_id}"></input>
	          	数量<input type="text" name="details_num_${cur.bill_item_id}" value="1" size="6" maxlength="3" id="details_num${cur.bill_item_id}"/>
	          	<font id="msg${cur.bill_item_id}" color="red"></font>
	          </td> 
         </tr></c:forEach> 
         <tr><th colspan="7" align="center">修改换货订单应付金额（退货无需填写）</th></tr>	
         <tr>
	         <td style="text-align: center;" ><font color="red">*新单应付金额</font></td>
	         <td colspan="6" ><input type="text" name="new_total_price" value="" size="7" maxlength="10" id="new_total_price"/>
	         <a href="javascript:void(0);" onclick="toCalcu();"><font color="blue">计算</font></a>
	         
	          <font color="red">应付金额<= <fmt:formatNumber value="${af.map.pay_price}" pattern="0.00" /></font>
	         </td>
	         <!-- td colspan="2"  style="text-align: center;" ><font color="red">修改后原订单应付金额</font></td>
	         <td colspan="2"><font color="red" id="old_pay_price"><fmt:formatNumber value="${af.map.pay_price}" pattern="0.00" /></font> (元)</td -->
	     </tr>	 
	     <tr>
	         <td style="text-align: center;" >退换货说明：</td> <td colspan="6"><html-el:text property="remark1"  maxlength="200" style="width:70%" ></html-el:text></td>
	     </tr>
	     <tr>
	         <td style="text-align: center;" >退机类型：</td> 
	         <td colspan="6">
	     					<select name="exchange_info">
				           	<option value="">请选择</option>
				           	<option value="1">质量机退货</option>
				           	<option value="2">碎屏</option>
				           	<option value="3">好机退货</option>
				           	<option value="4">其它</option>
			           	</select>		
			</td>
		</tr>    	   
	     <tr>  
	         <td colspan="7" height="40" align="center"> 
	          <input class="bgButtonSave" type="button" name="Submit1" value=" 退 货  " id="btn_submit1" />
	          &nbsp;&nbsp;<input class="bgButtonSave" type="button" name="Submit4" value=" 换 货  " id="btn_submit2" />
	          &nbsp;&nbsp;<input class="bgButtonBack" type="button" name="Submit5" value="返 回" onclick="history.back();return false;" id="btn_back" />
	         </td>
	     </tr>
       </table> 
     </div> 
    </div>   	  
 </html-el:form>
</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#btn_submit1").click(function(){
		if(!checkpd()){
			return false;
		}
		if($("#details_ids").val()==""){
			alert('请选择退货商品');
			return false;
		}
		if(Validator.Validate(this.form, 3)){
			if(confirm("您确定退货吗？")){
				$("#order_type").val('1');
				$("#btn_submit1").attr("disabled",true);
				$("#btn_submit2").attr("disabled",true);
				$("#btn_back").attr("disabled",true);
				this.form.submit();
			} 
		}
	});
	
	$("#btn_submit2").click(function(){
		if(!checkpd()){
			return false;
		}
		if($("#details_ids").val()==""){
			alert('请选择换货商品');
			return false;
		}
		if($("#new_total_price").val()==''){
			alert('请填写新订单应付金额');
			$("#new_total_price").focus();
			return false;
		}
		if(Validator.Validate(this.form, 3)){
			if(confirm("您确定换货吗？")){
				$("#order_type").val('2');
				$("#btn_submit1").attr("disabled",true);
				$("#btn_submit2").attr("disabled",true);
				$("#btn_back").attr("disabled",true);
				this.form.submit();
			}
		}
	});
	
	$("#new_total_price").keypress(function(){//单价
		var price = $("#new_total_price").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(price)) {
			//$("#old_pay_price").val('');
		}else{
			jisuan();
		}
	}).keyup(function(){
		var price = $("#new_total_price").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(price)) {
			$("#new_total_price").val('');
		}else{
			jisuan();
		}
	}).blur(function(){
		var price = $("#new_total_price").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(price)) {
			$("#new_total_price").val('');
		}else{
			jisuan();
		}
	});	
	
});

function checkpd(){
var flg=true; 
var details = document.getElementsByName("details_id");
var details_ids="";
var details_nums="";
if(details!=null&&details.length>0){
	for(var i=0;i<details.length;i++){
		$("#msg"+details[i].value).html('');
		if(details[i].checked==true){
			var num=parseInt(document.getElementById("num"+details[i].value).value);
			var details_num=parseInt(document.getElementById("details_num"+details[i].value).value); 
			 if(isNaN(details_num)||details_num!=document.getElementById("details_num"+details[i].value).value){  
				 document.getElementById("details_num"+details[i].value).value=details_num;
				 $("#msg"+details[i].value).html('请输入正确数字!');
				 flg=false;
			 } 
			if(details_num>num){
				flg=false;
				$("#msg"+details[i].value).html('换货数量不能大于订单商品数量!');
			}else if(details_num<1){
				flg=false;
				$("#msg"+details[i].value).html('换货数量不能小于1!');
			} 
			if(flg){
				if(details_ids!=""){
					details_ids+=",";
					details_nums+=",";
				}
				details_ids+=details[i].value;
				details_nums+=details_num;
			}
		}
	}
	$("#details_ids").val(details_ids);
	$("#details_nums").val(details_nums);
}
return flg;	
}

function toCalcu(){
	var flg=true; 
	var details = document.getElementsByName("details_id");
	var details_ids="";
	var details_nums="";
	var all_price=0.0;
	var old_pay_price=<fmt:formatNumber value="${af.map.pay_price}" pattern="0.00" />;
	if(details!=null&&details.length>0){
		for(var i=0;i<details.length;i++){
			$("#msg"+details[i].value).html('');
			if(details[i].checked==true){
				var num=parseInt(document.getElementById("num"+details[i].value).value); 
				var total_price=parseFloat(document.getElementById("price"+details[i].value).value);
				var details_num=parseInt(document.getElementById("details_num"+details[i].value).value); 
				if(isNaN(details_num)||details_num!=document.getElementById("details_num"+details[i].value).value){  
					 document.getElementById("details_num"+details[i].value).value=details_num;
					 $("#msg"+details[i].value).html('请输入正确数字!');
					 flg=false;
				}  
				if(details_num>num){
					flg=false;
					$("#msg"+details[i].value).html('换货数量不能大于订单商品数量!');
				}else if(details_num<1){
					flg=false;
					$("#msg"+details[i].value).html('换货数量不能小于1!');
				} 
				if(flg){
					if(details_ids!=""){
						details_ids+=",";
						details_nums+=",";
					}
					details_ids+=details[i].value;
					details_nums+=details_num;
				}
				all_price+=total_price/num*details_num;
			}
		}
		$("#details_ids").val(details_ids);
		$("#details_nums").val(details_nums);
	}
	
	if(flg){
		var old_price=parseFloat(old_pay_price)-parseFloat(all_price); 
		if(old_price<0){
			alert('订单金额大于原订单应付金额,请手工计算');
			$("#new_total_price").val('');
		}else{
			//$("#old_pay_price").text(""+old_price);
			$("#new_total_price").val(all_price);
		}
	}else{
		$("#new_total_price").val('');
	} 
	
	return flg;	
}

function jisuan(){
	var old_pay_price=<fmt:formatNumber value="${af.map.pay_price}" pattern="0.00" />;
	if($("#new_total_price").val()!=''){
		var old_price=old_pay_price-parseFloat($("#new_total_price").val()); 
		if(old_price<0||parseFloat($("#new_total_price").val())<0){
			alert('订单应付金额错误');
			$("#new_total_price").val('');
		}else{
			var str=""+old_price;
			if(str.indexOf(".")!=-1){//修正浮点数精度
				str=str+'00';
				str=str.substring(0, str.indexOf(".")+3);
			}
			//$("#old_pay_price").text(str); 
		}
	}
}
	
	
function shouMsg(){
 	if(	document.getElementById("audt_msg").style.display=='none'){
 		document.getElementById("audt_msg").style.display='block';
 		$("#shouMsg").html('订单审核信息(收起-)');
 	}else{
 		document.getElementById("audt_msg").style.display='none';
 		$("#shouMsg").html('订单审核信息(展开+)');
 	}
 	window.parent.resizeFrameHeight('mainFrame', 3);
} 
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
