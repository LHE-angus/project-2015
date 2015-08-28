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
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont">
  <div class="oartop" >
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2" style="padding-left:5px;padding-right:5px">
  	<!--<c:if test="${empty flag }">
	    <div>进入<a href="${ctx}/customer/manager/JSubSellRec.do?method=list&bill_type=${af.map.bill_type}&mod_id=${af.map.history_list_mod_id}" ><font style="font-weight:bold;">${j_bill_history_list_title}</font></a>页面</div>
  	</c:if>-->
    <%@ include file="/commons/pages/messages.jsp" %>
    <html-el:form action="/manager/JBill.do">
      <html-el:hidden property="method" value="save"/>
      <html-el:hidden property="bill_id" value="${af.map.bill_id}"/>
      <html-el:hidden property="mod_id" value="${af.map.mod_id}"/>
      <html-el:hidden property="bill_type" value="${af.map.bill_type}"/>
      <html-el:hidden property="queryString" />
      <c:set var="readonly" value="${empty af.map.bill_id ? false : true}"/>
      <c:if test="${not empty af.map.bill_id }"><c:set var="edit_details" value="1"></c:set></c:if>
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td colspan="5"  bgcolor="#CCCCCC" style="font-weight:bold;">登记信息</td>
        </tr>
       	<tr>
       	  <td width="10px"></td>
          <td colspan="4" nowrap="nowrap" class="title_item"><span style="color:red;">* </span>关联销售单号：
          	<c:if test="${empty flag }">
	          	<html-el:text property="r_bill_sn" size="40" maxlength="30" styleId="r_bill_sn" />&nbsp;
	          	<input type="button" value=" 选 择 " onclick="openChild()" />&nbsp;
	          	<a id="view_a" target="_blank" style="color:blue;display:none;" >查看销售单</a>
          	</c:if>
          	<c:if test="${not empty flag }">
          		${af.map.r_bill_sn }
          	</c:if>
          </td>
        </tr>
        <tr>
        	<td width="10px"></td>
	        <td width="8%" nowrap="nowrap" class="title_item">
	        	<span style="color:red;">* </span>${bill_sn_title}：
	        </td>
          	<td align="left" width="30%">
          		<c:if test="${empty flag }">
          			${af.map.bill_sn }
          			<html-el:hidden property="bill_sn" styleId="bill_sn" />
          		</c:if>
	        	<c:if test="${not empty flag }">${af.map.bill_sn }</c:if>
          	</td>
          	<td width="5%" nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>创建日期：</td>
          	<td width="50%">
	          	<fmt:formatDate value="${af.map.opr_date}" pattern="yyyy-MM-dd" var="_opr_date" />
	          	<c:if test="${empty flag }">
	          		${_opr_date }
	          		<html-el:hidden property="opr_date" styleId="opr_date" value="${_opr_date}"/>
	          	</c:if>
		        <c:if test="${not empty flag }">${_opr_date }</c:if>
         	</td>
        </tr>
        <tr>
          <td width="10px"></td>
          <td nowrap="nowrap" class="title_item" style="padding-left: 14px">顾客姓名：</td>
          <td><span id='link_name'></span></td>
          <td nowrap="nowrap" class="title_item" style="padding-left: 14px" align="right">联系电话：</td>
          <td><span id='link_mobile'></span></td>
        </tr>
        <tr>
          <td width="10px"></td>
          <td nowrap="nowrap" class="title_item" style="padding-left: 14px">退货人：</td>
          <td><span id='consignee_name'></span></td>
          <td nowrap="nowrap" class="title_item" style="padding-left: 14px" align="right">退货人电话：</td>
          <td><span id='consignee_tel'></span></td>
        </tr>
        <tr>
          <td width="10px"></td>
          <td nowrap="nowrap" class="title_item"><span style="color:red;">* </span>交货方式：</td>
          <td colspan="3">
          	<c:if test="${empty flag }">
          		<html-el:hidden property="send_type" styleId="send_type"/>
	          	<label><input type='checkbox' id="send_type1" name="send_type" value="0" disabled="disabled"></input>自提</label>
	          	&nbsp;&nbsp;
	          	<label><input type='checkbox' id="send_type2" name="send_type" value="1" disabled="disabled"></input>配送</label>
          	</c:if>
          	<c:if test="${not empty flag }">
          		<c:if test="${af.map.send_type eq 0 }">
		          	<label><input type='checkbox' id="send_type1" name="send_type" value="0" checked="checked" disabled="disabled"></input>自提</label>
		          	&nbsp;&nbsp;
		          	<label><input type='checkbox' id="send_type2" name="send_type" value="1" disabled="disabled"></input>配送</label>
          		</c:if>
          		<c:if test="${af.map.send_type eq 1 }">
		          	<label><input type='checkbox' id="send_type1" name="send_type" value="0" disabled="disabled"></input>自提</label>
		          	&nbsp;&nbsp;
		          	<label><input type='checkbox' id="send_type2" name="send_type" value="1" checked="checked" disabled="disabled"></input>配送</label>
          		</c:if>
          	</c:if>
          </td>
        </tr>
        <tr>
          <td width="10px"></td>
          <td nowrap="nowrap" class="title_item" style="padding-left: 14px">退货地址：</td>
          <td colspan="3"><span id="send_addr"></span></td>
        </tr>
        <tr>
          <td width="10px"></td>
          <td nowrap="nowrap" class="title_item" style="padding-left: 14px">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
          <td colspan="3">
          	<html-el:textarea property="bill_memo" styleId="bill_memo" cols="70" rows="3" value="${af.map.bill_memo }"></html-el:textarea>
          </td>
        </tr>
        <tr>
          <td colspan="5"  bgcolor="#CCCCCC">商品明细</td>
        </tr>
        <tr>
          <td colspan="5"><table id="xsth_t" width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
          	<tr>
       			<td width="10%" align="center" nowrap="nowrap">仓库名称</td>
       			<td width="15%" align="center" nowrap="nowrap">商品类型</td>
       			<td align="center" nowrap="nowrap">商品</td>
       			<td width="5%" align="center" nowrap="nowrap">单位</td>
       			<td width="6%" align="center" nowrap="nowrap">销售数量</td>
       			<td width="8%" align="center" nowrap="nowrap">销售单价（元）</td>
       			<td width="6%" align="center" nowrap="nowrap"><span style="color:red;">* </span>退货数量</td>
       			<td width="8%" align="center" nowrap="nowrap"><span style="color:red;">* </span>退货单价（元）</td>
       			<td width="8%" align="center" nowrap="nowrap">退货额（元）</td>
       			<td width="9%" align="center" nowrap="nowrap">行备注</td>
       			<td width="2%" align="center" nowrap="nowrap">删除</td>
       		</tr>
	       	<tbody id="xsth_tbody">
       		<c:forEach var="cur" items="${jbillDetailsList}" varStatus="vs">
       			<tr>
					<td align='center' nowrap='nowrap'>
       					<input type='hidden' name='goods_cost' value='${cur.map.goods_cost }'/>
						<input type='hidden' name='store_id' value='${cur.map.store_id }' />${cur.map.store_name }</td>
					<td align='center' nowrap='nowrap'>${cur.map.type_name }</td>
					<td align='center' nowrap='nowrap'><input type='hidden' class='goods_id' name='goods_id' value='${cur.map.goods_id }' />${cur.map.goods_name }</td>
					<td align='center' nowrap='nowrap'>${cur.map.unit }</td>
					<td align='center' nowrap='nowrap'>${cur.map.num }</td>
					<td align='center' nowrap='nowrap'>${cur.map.price }</td>
					<td>
						<c:if test="${cur.map.num gt 0 }">
							<c:set var='goods_money_init' value="${cur.map.num }"/>
						</c:if>
                    	<c:if test="${cur.map.num lt 0 }">
                    		<c:set var='goods_money_init' value="${-cur.map.num }"/>
                    	</c:if>
						<input type='text' name='num' id='num' size='4' onfocus='javascript:setOnlyInt(this)' value='${goods_money_init }' maxlength='6' class='num' /></td>
					<td>
						<input type='text' name='price' size='6' onfocus='javascript:setOnlyPositiveNum(this);' maxlength='10' class='price' value="${cur.price }"/>
					</td>
					<td>
						<span>0</span>
						<input type="hidden" name='goods_money' size='6'class='goods_money' readonly="readonly" value="${cur.map.sell_money }"/>
					</td>
					<td align='center' nowrap='nowrap'><input type='text' name='notes' value='${cur.notes }'></input>${cur.notes }${cur.NOTES }</td>
					<td align='center' style='cursor:pointer;' class='td_del'><img src='${ctx}/images/x.gif' style='vertical-align:middle;top:0px;' /></td>
				</tr>
       		</c:forEach>
	       	</tbody>
          </table></td>
        </tr>
        <tr>
          <td width="10px"></td>
          <td nowrap="nowrap" class="title_item">${rec_money_title}：</td>
          <td colspan="3">
            <span id='rec_money_t'>0</span>&nbsp;元
          	<html-el:hidden property="rec_money" styleId="rec_money" styleClass="webinput" />
          	<html-el:hidden property="sum_money" styleId="sum_money"/>
          	<html-el:hidden property="dis_money" styleId="dis_money" value="0" />
          </td>
        </tr>
        <tr>
          <td colspan="5" align="center">
          	<html-el:button property="" value="提 交" styleClass="but4" styleId="btn_submit" />&nbsp;
            <c:if test="${empty flag }">
	            <html-el:button property="" value="重 置" styleClass="but5" styleId="btn_reset" onclick="this.form.reset();" />
            </c:if>
            <c:if test="${not empty flag or not empty detailsList }">
            	&nbsp;<html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();" />
            </c:if>
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#r_bill_sn").attr("dataType", "Require").attr("msg", "请选择关联销售单号！");

	$(".num").live("blur",function(){
		var num = $(this).val();
		var sell_num = parseInt($(this).parent().parent().children('td').eq(4).html());
		var price = $(this).parent().parent().children('td').eq(7).children().val();
		if(num > sell_num){
			alert("对不起，您设置的退货数量大于销售数量");
			$(this).val(1);
		}else{
			if($.trim(num).length > 0 && $.trim(price).length > 0){
				 var money = num * price;
				 var $money = $(this).parent().parent().children('td').eq(8).children("input");
				 var $money_t = $(this).parent().parent().children('td').eq(8).children("span");
				 $money.val(money);   //金额
				 $money_t.html(money);   

				 var total_money = 0;
				 $(".goods_money").each(function(){
					var goods_money = $(this).val();
					if($.trim(goods_money).length > 0 ){
						total_money = parseFloat(total_money) + parseFloat(goods_money);
					}
				 });
				 $("#rec_money").val(total_money);
				 $("#rec_money_t").html(total_money);
				 $("#sum_money").val(total_money);
				 var discount  = $("#discount").val();
				 //$("#money").val(total_money * parseFloat(discount) / 100);
				 //$("#sum_money").val(total_money * parseFloat(discount) / 100);
			}
		}
	});

	$(".price").live("blur",function(){
		var price = $(this).val();
		var num = $(this).parent().parent().children('td').eq(6).children().val();
		if($.trim(num).length > 0 && $.trim(price).length > 0){
			 var money = num * price;
			 var $money = $(this).parent().parent().children('td').eq(8).children("input");
			 $money.val(money);   //金额
			 var $money_t = $(this).parent().parent().children('td').eq(8).children("span");
			 $money_t.html(money);   //金额

			 var total_money = 0;
			 $(".goods_money").each(function(){
					var goods_money = $(this).val();
					if($.trim(goods_money).length > 0 ){
						total_money = parseFloat(total_money) + parseFloat(goods_money);
					}
			 });
			 $("#rec_money").val(total_money);
			 $("#rec_money_t").html(total_money);
			 $("#sum_money").val(total_money);
			 var discount  = $("#discount").val();
			 //$("#money").val(total_money * parseFloat(discount) / 100);
			 //$("#sum_money").val(total_money * parseFloat(discount) / 100);
		}
	});

	$(".td_del").live("click",function(){
		var temp_money = $(this).parent().children('td').eq(8).children().val();
		$("#rec_money").val($("#rec_money").val()-temp_money);
		$("#rec_money_t").html($("#rec_money").val()-temp_money);
		$("#sum_money").val($("#sum_money").val()-temp_money);
		$(this).parent().remove();	
	});

	$("#btn_submit").click(function(){
		$(".num").attr("dataType", "Require").attr("msg", "请填写！");
		$(".price").attr("dataType", "Require").attr("msg", "请填写！");
		if(Validator.Validate(this.form, 3)){
			if($(".goods_id").length == 0){
				alert("对不起，请选择需要退货的商品！");
				return false;
			}
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_reset").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
		//iframe高度自适应
		window.parent.resizeFrameHeight('mainFrame', 3);
	});
});

function SetRBillSnDetails(r_bill_sn){
	$.ajax({
		type: "POST",
		url: "JSubSellRec.do",
		data: {method : "ajaxGetAgentDetails", "r_bill_sn": r_bill_sn, "bill_sn": '${af.map.bill_sn }'},
		dataType: "json",
		cache:false,
		error: function(){alert("数据加载请求失败！");},
		success: function(ret){
			if(ret){
				$("#partner_name_t").html(ret.agent.partner_name);
				$("#partner_id").val(ret.agent.partner_id);
				$("#link_name").html(ret.agent.link_name);
				$("#link_mobile").html(ret.agent.link_mobile);
				$("#consignee_name").html(ret.agent.consignee_name);
				$("#consignee_tel").html(ret.agent.consignee_tel);
				if(ret.jbill.send_type==0){
					$("#send_type1").attr('checked', true);
				}
				if(ret.jbill.send_type==1){
					$("#send_type2").attr('checked', true);
				}
				$("#send_type").val(ret.jbill.send_type);
				var str = "";
				if(ret.agent.map._PROVINCE!=null){
					str += ret.agent.map._PROVINCE;
				}
				if(ret.agent.map._CITY!=null){
					str += ret.agent.map._CITY;
				}
				if(ret.agent.map._COUNTRY!=null){
					str += ret.agent.map._COUNTRY;
				}
				if(ret.agent.map._TOWN!=null){
					str += ret.agent.map._TOWN;
				}
				if(ret.agent.consignee_street!=null){
					str += ret.agent.consignee_street;
				}
				$("#send_addr").html(str);
				
				if(null != ret.jdetails){
					var html = "";
					var total_money = 0;
					for(var i = 0; i < ret.jdetails.length; i++){
						var cur = ret.jdetails[i];
						html += "<tr><input type='hidden' name='goods_cost' value='" + cur.map.goods_cost + "' />";
						html += "<td align='center' nowrap='nowrap'><input type='hidden' name='store_id' value='" + cur.map.store_id + "' />" + cur.map.store_name + "</td>";
						html += "<td align='center' nowrap='nowrap'><input type='hidden' name='type_id' value='" + cur.map.type_id + "' />" + cur.map.type_name + "</td>";
						html += "<td align='center' nowrap='nowrap'><input type='hidden' class='goods_id' name='goods_id' value='" + cur.map.goods_id + "' />" + cur.map.goods_name + "</td>";
						html += "<td align='center' nowrap='nowrap'>" + cur.map.unit + "</td>";
						html += "<td align='center' nowrap='nowrap'>" + cur.num + "</td>";
						html += "<td align='center' nowrap='nowrap'>" + cur.price + "</td>";
						html += "<td><input type='text' name='num' id='num' size='4' onfocus='javascript:setOnlyInt(this)' value='"+cur.num+"' maxlength='6' class='num' /></td>";
						html += "<td><input type='text' name='price' size='6' onfocus='javascript:setOnlyPositiveNum(this);' maxlength='10' class='price' value='"+cur.price+"'/></td>";
						html += "<td><span>"+(cur.num*cur.price)+"</span><input type='hidden' name='goods_money' class='goods_money' value='"+(cur.num*cur.price)+"'/></td>";
						html += "<td align='center' nowrap='nowrap'><input type='text' name='notes' size='6' value='"+cur.notes+"'/></td>";
						html += "<td align='center' style='cursor:pointer;' class='td_del'><img src='${ctx}/images/x.gif' style='vertical-align:middle;top:0px;' /></td>";
						html += "</tr>";
						
						total_money=total_money+(cur.num*cur.price);
					}
					$("#xsth_tbody").html(html);
					$("#rec_money_t").html(total_money);
					$("#rec_money").html(total_money);
					//iframe高度自适应
					window.parent.resizeFrameHeight('mainFrame', 3);
				}
			}
		}
   }); 
}

//若为修改，则执行
if("${edit_details}" == 1){
	SetRBillSnDetails($("#r_bill_sn").val());
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
		if(isNaN(obj.value)) obj.value = "";
	});
}

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
		if(obj.value.length == 0 || isNaN(obj.value) || obj.value == 0) obj.value = "0";
	});
}

function openChild(){
   $.dialog({
		title:  "关联销售单",
		width:  770,
		height: 550,
		min: false,
		max: false,
        lock:   true ,
        drag: false,
		content:"url:${ctx}/customer/manager/JBill.do?method=chooseBillSn"
	});
}

//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>