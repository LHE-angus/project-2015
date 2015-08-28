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
      <html-el:hidden property="method" styleId="method" value="save1" />
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
          <td class="title_item" width="15%" >备注：</td>
          <td colspan="5"><html-el:text property="remark"  maxlength="200" style="width:70%"></html-el:text></td>
          </tr>
        </table>
      </div>
      
      <div style="margin-top: 30px">
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
          <td class="title_item" width="20%" style="text-align: center;">备注说明</td>
          </tr>          
          <c:forEach items="${pshowOrdeDetails}" var="cur">
          <tr>
          <td style="text-align: left;">(${cur.map.pd_sn })${cur.pd_name }</td>
          <td nowrap="nowrap" style="text-align: center;">${cur.num }</td>
		  <td nowrap="nowrap" style="text-align: center;">${cur.price }</td>
          <td nowrap="nowrap" style="text-align: center;">${cur.total_price}</td>
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
          <tr >
        	<td nowrap="nowrap" colspan="6"  >
        	<table width="100%" border="0" style="margin-left: 250px;">
                <tr >
                  <td width="286px;"><span id="area_name_0" class="title_item">供选择商品列表</span><br />
                    <html-el:select property="select1" multiple="true" style="width:300px;height:200px;" styleId="select1" onchange="moveSelected(this.form.select1, this.form.select2);">
                        <c:forEach var="cur1" items="${pshowOrdeDetails2}" varStatus="vs">
                        <option value="${cur1.bill_item_id}">${cur1.map.pd_sn}/${cur1.num}台/${cur1.price}元/${cur1.total_price}元</option>
                      	</c:forEach>
                    </html-el:select>
                    <html-el:hidden property="s1" styleId="s1"/>
                    </td>
                  <td><span id="area_name_0" class="title_item">已选择商品列表</span><br /><html-el:select property="select2" multiple="true" style="width:300px;height:200px;" styleId="select2" onchange="moveSelected(this.form.select2, this.form.select1);">
                    </html-el:select>
                      <html-el:hidden property="s2" styleId="s2"/>
                    </td>
                </tr>
              </table>
        	</td>
        </tr>
         <tr >
        	<td nowrap="nowrap" colspan="6"  >
        	<table width="100%" border="0" style="margin-left: 250px;">
                <tr >
                  <td width="286px;"><span id="area_name_0" class="title_item">供选择抵用券列表</span><br />
                    <html-el:select property="select3" multiple="true" style="width:300px;height:200px;" styleId="select3" onchange="moveSelected(this.form.select3, this.form.select4);">
                        <c:forEach var="cur1" items="${ecvList}" varStatus="vs">
                        <option value="${cur1.id}">${cur1.title}</option>
                      	</c:forEach>
                    </html-el:select>
                    <html-el:hidden property="s3" styleId="s3"/>
                    </td>
                  <td><span id="area_name_0" class="title_item">已选择抵用券列表</span><br /><html-el:select property="select4" multiple="true" style="width:300px;height:200px;" styleId="select4" onchange="moveSelected(this.form.select4, this.form.select3);">
                    </html-el:select>
                      <html-el:hidden property="s4" styleId="s4"/>
                    </td>
                </tr>
              </table>
        	</td>
        </tr>
          <tr>
          <td class="title_item" width="15%" style="text-align: center;" >拆单说明：</td>
          <td colspan="5"><html-el:text property="remark1"  maxlength="200" style="width:70%"></html-el:text></td>
          </tr>
          <tr>
          	  <td colspan="6" height="40" align="center"><input class="bgButtonSave" type="button" name="Submit4" value="拆单 " id="btn_submit" />
              <input class="bgButtonBack" type="button" name="Submit5" value="返回" onclick="history.back();return false;" id="btn_back" /></td>
        		</tr>
        </table>
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

	//alert('${pshowordedetails}');
	
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
			   if(!confirm("确定拆单吗？")) return false;
				var arrs_pls1 = new Array();
				var obj_pls1 = document.getElementById("select1");
				for(var i = 0; i < obj_pls1.length; i++){
					arrs_pls1.push(obj_pls1.options[i].value);
				}
				$("#s1").val(arrs_pls1); 
				

				var arrs_pls2 = new Array();
				var obj_pls2 = document.getElementById("select2");
				for(var i = 0; i < obj_pls2.length; i++){
					arrs_pls2.push(obj_pls2.options[i].value);
				}
				$("#s2").val(arrs_pls2);

				var arrs_pls3 = new Array();
				var obj_pls3 = document.getElementById("select3");
				for(var i = 0; i < obj_pls3.length; i++){
					arrs_pls3.push(obj_pls3.options[i].value);
				}
				$("#s3").val(arrs_pls3);

				var arrs_pls4 = new Array();
				var obj_pls4 = document.getElementById("select4");
				for(var i = 0; i < obj_pls4.length; i++){
					arrs_pls4.push(obj_pls4.options[i].value);
				}
				$("#s4").val(arrs_pls4);
				

				if(""==$("#s1").val()||""==$("#s2").val()){
					alert("请至少保持左右两边有一个商品！");
					return false;
				}
			
				$("#btn_submit").attr("disabled",true);
				$("#btn_back").attr("disabled",true);
				this.form.submit();
		}
	});
});


function moveSelected(sourceSelect, targetSelect, isDelete){
	var cachOptionsArray = new Array();
	var index = 0;
	for (var i = sourceSelect.options.length - 1; i >= 0; i--){
		if (sourceSelect.options[i].selected){
			cachOptionsArray[index] = new Option(sourceSelect.options[i].text, sourceSelect.options[i].value);
			if(isDelete==undefined || isDelete==true){
				sourceSelect.options[i] = null;
			}
			index++;
		}
	}
	var exist = false;
	for (var i = cachOptionsArray.length - 1; i >= 0; i--){
		exist = false;
		for (var j = 0; j < targetSelect.options.length; j++){
			if (targetSelect.options[j].value.toString() == cachOptionsArray[i].value.toString()){
				exist = true; 
				break;
			}
		}
		if (!exist){
			targetSelect.options[targetSelect.options.length] = cachOptionsArray[i];
		}
	}
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
