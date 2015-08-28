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
      <html-el:hidden property="pro_ids" styleId="pro_ids"/>
	  <html-el:hidden property="pro_names" styleId="pro_names"/>
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
           		<html-el:option value="20">审核通过</html-el:option>
           		<html-el:option value="30">下发处理</html-el:option>
           		<html-el:option value="40">商家发货</html-el:option>
           		<html-el:option value="50">客户已换货</html-el:option>
           		<html-el:option value="60">确认收货</html-el:option>
           </html-el:select>
            </td>
          </tr>
          <tr>
          	 <td  class="title_item" width="15%">产品总数量：</td>
             <td colspan="2">${t_num }</td>
             <td  class="title_item" width="15%">订单总价格：</td>
             <td colspan="2">
             	<html-el:text property="pay_price" styleId="pay_price" size="15" maxlength="10"/>
             </td>
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
          <tr>
	          <td class="title_item" width="15%" >备注：</td>
	          <td colspan="5"><html-el:text property="remark"  maxlength="200" style="width:70%"></html-el:text></td>
          </tr>
        </table>
      </div>
      
      <div style="margin-top: 30px">
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
          <tr>
          	<td colspan="5"  style="background:#CCCCCC" >商品明细信息</td>
          </tr>	
          <tr>
          <td class="title_item" width="15%" style="text-align: center;">产品型号</td>
		  <td class="title_item" width="8%" style="text-align: center;">数量</td>
          <td class="title_item" width="8%" style="text-align: center;">单价</td>
          <td class="title_item" width="8%" style="text-align: center;">总金额</td>
           <td class="title_item" width="8%" style="text-align: center;">增值服务</td>
          <td class="title_item" width="30%" style="text-align: center;">备注说明</td>
          </tr>
          
          <c:forEach items="${pshowOrdeDetails}" var="cur">
          <tr>
          <td nowrap="nowrap" style="text-align: center;">${cur.map.pd_sn}</td>
          <td  nowrap="nowrap" style="text-align: center;">${cur.num }</td>
		  <td  nowrap="nowrap" style="text-align: center;">${cur.price }</td>
          <td  nowrap="nowrap" style="text-align: center;">${cur.total_price}</td>
          <td nowrap="nowrap" style="text-align: center;">
						 <c:forEach items="${bddetailsList}" var="cu">
						 	<c:if test="${cu.details_id eq cur.bill_item_id}">
						 		${cu.goods_name} ￥${cu.price}<br />
						 	</c:if>
						 </c:forEach>
				  </td>
          <td  nowrap="nowrap" style="text-align: center;">${cur.remark }</td>
          </tr>
          </c:forEach>
          <tr>
							<td colspan="6">
  								<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass" style="margin:5px 0px;">
    								<tr class="title_top_no_bc">
										<td width="14%" nowrap="nowrap">产品型号&nbsp;</td>
										<td width="8%" nowrap="nowrap">数量</td>
										<td width="8%" nowrap="nowrap">单位</td>
										<td width="8%" nowrap="nowrap">单价</td>
										<td width="10%" nowrap="nowrap">金额</td>
										<td width="5%" align="center" style="cursor:pointer;" title="添加" onclick="getPePdModel('${af.map.id}');"><img src="${ctx}/styles/jxc/images/+.gif" style="vertical-align:text-bottom;"/></td>
									</tr>
									<tr id="trHidden" style="display:none;">
										<td align="center">
											<html-el:hidden property="pd_ids" styleId="pd_ids" />
											<html-el:hidden property="pd_id" styleId="pd_id" />
											<html-el:hidden property="md_name" styleId="md_name1" styleClass="md_name1" /> 
											<span id="md_name" class="md_name"></span>
										</td>
										<td align="center"><html-el:text property="good_count" styleId="good_count" value="1" size="4" maxlength="4" styleClass="good_count" /></td>
										<td align="center"><html-el:text property="good_unit" styleId="good_unit" value="台" maxlength="20" size="5" styleClass="" /></td>
										<td align="center"><html-el:text property="good_price" value="0" styleId="good_price"  maxlength="8" size="8" styleClass="" /></td>
										<td align="center">
											<html-el:text property="good_sum_price" styleId="good_sum_price" value="0" style="width:80px;" styleClass="webinput"  />
											<html-el:hidden property="sum_price" styleId="sum_price" />
										</td>
										<td align="center" title="删除"><img src="${ctx}/styles/jxc/images/x.gif" style="vertical-align:text-bottom;"/></td>
									</tr>
									<tbody id="tbodyContent"></tbody>
									<tr class="title_top_no_bc">
										<td>合计</td>
										<td><html-el:text property="dd_count_sum" styleId="dd_count_sum" value="0" style="width:50px;" styleClass="webinput" readonly="true" />
											<html-el:hidden property="order_num" styleId="order_num" />
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>
											<html-el:text property="dd_money_sum" styleId="dd_money_sum" value="0" style="width:100px;" styleClass="webinput" readonly="true" />
											<html-el:hidden property="money_sum" styleId="money_sum" />
										</td>
										<td>&nbsp;</td>
									</tr>
								</table>
							</td>
						</tr>
          <tr>
          <td class="title_item" width="15%" style="text-align: center;" >拆分说明：</td>
          <td colspan="5"><html-el:text property="remark1"  maxlength="200" style="width:70%"></html-el:text></td>
          </tr>
          <tr>
          	  <td colspan="6" height="40" align="center"><input class="bgButtonSave" type="button" name="Submit4" value="拆分 " id="btn_submit" />
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
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=jtop"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${province}","dataType":"Require","msg":"请选择省/直辖市/自治区！"});
	$("#city").attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${city}","datatype": "Require", "msg": "请选择市名称！"});
	$("#country").attr({"subElement": "town","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${country}"});
	$("#town").attr({"defaultText": "-请选择乡/镇-", "defaultValue": "", "selectedValue": "${town}"});
	
	$("#province").cs_ext("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false,"p_index_join","${af.map.p_index_join}");
	$("#province").change();


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


function getPePdModel(id) { 
	//var returnValue = window.showModalDialog("${ctx}/manager/spgl/SelectPePdModel.do?selectype=mutiple&id="+id+"&selects=" + $("#pd_ids").val() + "&azaz=" + Math.random(),window,"dialogWidth:620px;status:no;dialogHeight:390px");
    
	//if (returnValue != null && returnValue.ids != "") {
		$.dialog({
		title:  "产品列表",
		width:  400,
		height: 390,
        lock:true ,
		content:"url:${ctx}/customer/manager/SelectPePdModel.do?method=newList&selectype=mutiple&selects=" + $("#pd_ids").val() + "&azaz=" + Math.random()
	});
}

//取得窗口选择的产品并回显
function getProInfo(){
	var ids = $("#pro_ids").val();
	var names = $("#pro_names").val();
	if (ids != "") {
		//前一次操作返回的值  + 当前操作返回的值 = 当前的值
		var pd_ids_temp = "";
		if("" != $("#pd_ids").val()){
			pd_ids_temp = $("#pd_ids").val()+ "," + ids ;
		}else{
			pd_ids_temp = ids ;
		}
	    $("#pd_ids").val(pd_ids_temp);
	    
	    var pd_id_array = new Array();
	    var md_name_array = new Array();
	
	    pd_id_array = ids.split(",");
	    md_name_array = names.split(",");
	    alert(pd_id_array);
	    alert(md_name_array);
	    
		for(var i = 0; i < pd_id_array.length; i++){
			$("#trHidden").clone().appendTo($("#tbodyContent")).show();
	    	var lastTR = $("tr:last", "#tbodyContent");
	    	
	    	var pd_id = $("#pd_id", lastTR);//产品型号id
	    	var md_name = $("#md_name", lastTR);//隐藏域产品型号名称
	    	var md_name1 = $("#md_name1", lastTR);//产品编号
	    	
			var good_count = $("#good_count", lastTR);//数量
			var good_unit = $("#good_unit", lastTR);//单位
			var good_price = $("#good_price", lastTR);//单价
			
			var good_sum_price = $("#good_sum_price", lastTR);//金额
			var sum_price = $("#sum_price", lastTR);//隐藏域金额
			
			good_count.attr("dataType","Number").attr("msg","请填写数量,且必须为正整数！");
			good_price.attr("dataType","Double").attr("msg","请填写单价！");
			
			pd_id.val(pd_id_array[i]);//隐藏域选择的产品
			var pd_sn = md_name_array[i];
			var pd_sn_array = pd_sn.split("/");
	      	md_name.text(pd_sn_array[0]);
	      	md_name1.val(pd_sn_array[0]);
	      	
			//ajax动态取产品价格
	    	//$.ajax( {
	    		//type : "POST",
	    		//cache : false,
	    		//url : "${ctx}/CsAjax.do",
	    		//data : "method=getKonkaR3PdPrice&pd_sn=" + md_name_array[i]+"&r3_code=" + r3_code,
	    		//dataType: "json",
	    		//error : function(data) {good_price.val("0");},
	    		//success : function(data) {
	    			//good_price.val(parseFloat(data[0].price).toFixed(2));
	        //	}
	    	//});
	      	
			good_count.val(pd_sn_array[1]);
			good_unit.val("台");
			good_price.val(pd_sn_array[2]);
			
			good_sum_price.val(pd_sn_array[3]);
			//sum_price.val("0");
			calcPdNumAndPrice("tbodyContent");//合计金额

			//calcOneCountAndJhPrice(good_count, good_price, good_sum_price, sum_price);
			//good_discount_price.val(parseFloat(good_sum_price.val())*parseFloat(good_discount.val())/100);//折扣金额
			//discount_price.val(parseFloat(good_sum_price.val())*parseFloat(good_discount.val())/100);//隐藏域折扣金额
			//calcPdNumAndPrice("tbodyContent");
			//bindJhCountAndJhPrice(good_count, good_price, good_sum_price, sum_price, good_discount, good_discount_price, discount_price, single_discount_price,lastTR);
			//bindJhCountAndJhPrice(md_name1,good_count, good_price, good_sum_price, sum_price, good_discount, good_discount_price, discount_price,af_discount_good_price,af_discount_price,lastTR);
			
			//删除操作，影响弹出页面的返回值
			$("td:last", lastTR).click(function (){
				var pd_ids_new1 = $("#pd_ids").val();
				
				if (pd_ids_new1.substring(pd_ids_new1.length -1, pd_ids_new1.length) == ",") {
					pd_ids_new1 = $("#pd_ids").val();
				} else {
					pd_ids_new1 = $("#pd_ids").val()+",";
				}
				alert(pd_ids_new1);
				var pd_id_td1 = $("#pd_id", $(this).parent()).val()+",";
				alert(pd_id_td1);
				var pd_ids_new2 = pd_ids_new1.replace(pd_id_td1,"");
				alert(pd_ids_new2);
				$("#pd_ids").val(pd_ids_new2);//删除掉的id，得到新的已经选择的id数组
				
		    	$(this).parent().remove();
		    	calcPdNumAndPrice("tbodyContent");

		    	//iframe高度动态变化
		    	window.parent.resizeFrameHeight('mainFrame', 3); 
		    }).css("cursor", "pointer");
		}
		resizeFrameHeight(2);
	}
}

function resizeFrameHeight(offset, min_height) {
	// frame id can be found in page /konka-wd/WebContent/manager/admin/Frames2/indexFrame.jsp, and search 'iframe' to get.
	$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
}

//计算 合计的 数量、 金额和折扣金额
function calcPdNumAndPrice(tbody_id) {
	var dd_count_sum = 0;
	var dd_money_sum = 0;
	
	$("#good_count", $("#" + tbody_id)).each(function(){
		var this_value = parseFloat($(this).val());
		if(isNaN(this_value)) this_value = 0;
		dd_count_sum += this_value;
	});

	$("#good_sum_price", $("#" + tbody_id)).each(function(){
		var this_value = parseFloat($(this).val());
		if(isNaN(this_value)) this_value = 0;
		dd_money_sum += this_value;
	});
	
	
	$("#dd_count_sum").val(dd_count_sum);
	$("#dd_money_sum").val("￥" + (dd_money_sum.toFixed(2)));
	
}

function judgeSelectedValueIsRepeat(objId, parObj){
	var isRepeat = false;
	var ary = new Array();
	$("#" + objId, parObj).each(function() {
		if ("" != $(this).val()) {
			ary.push($(this).val());
		}
	});
	var nary = ary.sort();  
	for(var i = 0; i < nary.length - 1; i++) {
		if (nary[i] == nary[i + 1]) {
			isRepeat = true;
			break;
		}  
    }  
	return isRepeat;
}


//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
