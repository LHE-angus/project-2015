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
  <div class="rtabcont2">
    <html-el:form action="/manager/JSubSellConfirm.do">
      <html-el:hidden property="method" value="save"/>
      <html-el:hidden property="sub_sell_id" value="${sub_sell_id}"/>
      <html-el:hidden property="mod_id" value="${mod_id }"/>
      <html-el:hidden property="c_id" value="${c_id }"/>
      <html-el:hidden property="bill_id" value="${bill_id }"/>
      <html-el:hidden property="con_state" styleId="con_state"/>
      <html-el:hidden property="queryString" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td colspan="5"  bgcolor="#CCCCCC" style="font-weight:bold;">登记信息查看</td>
        </tr>
        <c:if test="${not empty bill_confirm }">
        <tr>
          <td width="10px"></td>
          <td width="30px" nowrap="nowrap" class="title_item">分销商名称：</td>
          <td width="400px">${af.map.map.part_name}</td>
          <td width="30px" nowrap="nowrap" class="title_item">分销商编码：</td>
          <td nowrap="nowrap">${af.map.map.part_sn}</td>
        </tr>
        </c:if>
        <tr>
          <td width="10px"></td>
          <td width="30px" nowrap="nowrap" class="title_item"><span style="color:red;">* </span>网&nbsp;&nbsp;&nbsp;&nbsp;点：</td>
          <td width="400px">${agent.partner_name}</td>
          <td width="10px" nowrap="nowrap" class="title_item"><span style="color:red;">* </span>${bill_sn_title}：</td>
          <td nowrap="nowrap">${af.map.bill_sn}</td>
        </tr>
        <tr>
          <td width="10px"></td>
          <td nowrap="nowrap" class="title_item"><span style="color:red;">* </span>交货方式：</td>
          <td>
          	<c:if test="${af.map.send_type eq 0 }">自提</c:if>
          	<c:if test="${af.map.send_type eq 1 }">配送</c:if>
          </td>
          <td nowrap="nowrap" class="title_item"><span style="color:red;">* </span>创建日期：</td>
          <td><fmt:formatDate value="${af.map.opr_date}" pattern="yyyy-MM-dd"/></td>
        </tr>
        <tr>
          <td width="10px"></td>
          <td nowrap="nowrap" class="title_item">联&nbsp;系&nbsp;人：</td>
          <td>${agent.link_name }</td>
          <td nowrap="nowrap" class="title_item">联系电话：</td>
          <td>${agent.link_mobile }</td>
        </tr>
        <tr>
          <td width="10px"></td>
          <td nowrap="nowrap" class="title_item">单据状态：</td>
          <td>
          	<c:if test="${af.map.bill_state eq 0 }">待确认</c:if>
          	<c:if test="${af.map.bill_state eq 1 }">已确认</c:if>
          </td>
          <td nowrap="nowrap" class="title_item">创&nbsp;建&nbsp;人：</td>
          <td>${af.map.map.add_name }</td>
        </tr>
        <tr>
          <td width="10px"></td>
          <td nowrap="nowrap" class="title_item">网点地址：</td>
          <td colspan="3">${agent.map._PROVINCE}${agent.map._CITY }${agent.map._COUNTRY }${agent.map._TOWN }${af.map.consignee_street }
          </td>
        </tr>
        <tr>
          <td width="10px"></td>
          <td nowrap="nowrap" class="title_item">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
          <td colspan="3"><textarea rows="3" cols="70" readonly="readonly">${af.map.bill_memo }</textarea>
          </td>
        </tr>
        <tr>
          <td colspan="5"  bgcolor="#CCCCCC">商品明细</td>
        </tr>
        <tr>
          <td colspan="5">
          	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
          		<tr>
          			<c:if test="${af.map.bill_type eq 40 }">
	          			<td width="10%" align="center" nowrap="nowrap">入库仓库</td>
	          		</c:if>
	          		<c:if test="${af.map.bill_type eq 42 }">
	          			<td width="10%" align="center" nowrap="nowrap">出库仓库</td>
	          		</c:if>
          			<td width="20%" align="center" nowrap="nowrap">商品</td>
          			<td width="5%" align="center" nowrap="nowrap">单位</td>
          			<td width="6%" align="center" nowrap="nowrap">数量</td>
          			<td width="8%" align="center" nowrap="nowrap">${price_title}（元）</td>
          			<td width="8%" align="center" nowrap="nowrap">${goods_money_title}（元）</td>
          			<td width="8%" align="center" nowrap="nowrap">折扣金额（元）</td>
          			<td width="8%" align="center" nowrap="nowrap">应收金额（元）</td>
          			<td width="9%" align="center" nowrap="nowrap">行备注</td>
          		</tr>
          		<c:if test="${not empty detailsList}">
          			<c:forEach var="cur" items="${detailsList}">
          			<tr class="tr_pd">
	          			<td align="center" class="td_pd" nowrap="nowrap">
	          				<html-el:hidden property="bill_item_id" styleId="bill_item_id" value="${cur.bill_item_id }"/>
	          				<html-el:select property="in_store_id" styleClass="in_store_id" style="width:100px">
				          		<c:forEach var="st" items="${storelist}">
				          			<html-el:option value="${st.store_id}">${st.store_name}</html-el:option>
				          		</c:forEach>
				          	</html-el:select>
	          			</td>
	          			<td align="center" class="td_pd" nowrap="nowrap">
	          				<html-el:hidden property="goods_name" styleId="goods_name" value="${cur.map.goods_name }"/>
	          				${cur.map.goods_name}
	          			</td>
	          			<td align="center" nowrap="nowrap"><span class="dw">${cur.map.unit}</span></td>
	          			<td nowrap="nowrap" style="padding-right: 5px">
	          				<html-el:hidden property="num" styleId="num" value="${cur.num }"/>
	          				${cur.num}
	          			</td>
	          			<td nowrap="nowrap" style="padding-right: 5px">
	          				<html-el:hidden property="price" styleId="price" value="${cur.price }"/>
	          				${cur.price}
	          			</td>
	          			<td nowrap="nowrap" style="padding-right: 5px">
	          				<html-el:hidden property="money" styleId="money" value="${cur.money }"/>
	          				<fmt:formatNumber type="currency" value="${cur.money}"></fmt:formatNumber>
	          			</td>
	          			<td nowrap="nowrap" style="padding-right: 5px"><fmt:formatNumber type="currency" value="${cur.dis_money}"></fmt:formatNumber></td>
	          			<td nowrap="nowrap" style="padding-right: 5px">
	          				<html-el:hidden property="rec_money" styleId="rec_money" value="${cur.rec_money }"/>
	          				<fmt:formatNumber type="currency" value="${cur.rec_money}"></fmt:formatNumber></td>
	          			<td align="center" nowrap="nowrap">${cur.notes}</td>
	          		</tr>
	          		</c:forEach>
          		</c:if>
          	</table>
          </td>
        </tr>
        <tr>
          <td width="10px"></td>
          <td nowrap="nowrap" class="title_item">${rec_money_title}：</td>
          <td colspan="3">
          	${af.map.rec_money }&nbsp;元
          <html-el:hidden property="rec_money" styleId="rec_money"/>
          </td>
        </tr>
       	<tr style="display:${af.map.bill_type eq 20 ? '' : 'none'};">
          <td width="10px"></td>
          <td nowrap="nowrap" class="title_item">折扣：</td>
          <td colspan="3"><html-el:text property="discount" styleId="discount" size="40" onfocus="javascript:setOnlyNum(this)" styleClass="webinput" maxlength="5" disabled="${readonly}" />&nbsp;%</td>
        </tr>
        <tr>
            <td width="10px"></td>
        	<c:if test="${bill_confirm eq 0}">
	          <td nowrap="nowrap" class="title_item">${money_title}：</td>
	          <td colspan="3"><html-el:text property="confirm_money" styleId="confirm_money" value="${af.map.money }" size="40" styleClass="webinput" onfocus="javascript:setOnlyNum(this)" maxlength="10" readonly="readonly" />&nbsp;元</td>
        	</c:if>
        	<c:if test="${bill_confirm eq 1 }">
	          <td nowrap="nowrap" class="title_item">${money_title}：</td>
	          <td colspan="3"><html-el:text property="confirm_money" styleId="confirm_money" size="40" styleClass="webinput" onfocus="javascript:setOnlyNum(this)" maxlength="10" disabled="${readonly}" value="${af.map.rec_money }"/>&nbsp;元</td>
        	</c:if>
        </tr>
        <tr>
          <td colspan="5" align="center">
          	<c:if test="${bill_confirm eq 1 }">
          		<c:if test="${af.map.bill_type eq 40 }">
	          		<input type="button" value="确  认" class="but4" onclick="confirmBill(1)" />&nbsp;&nbsp;&nbsp;&nbsp;
          		</c:if>
          		<c:if test="${af.map.bill_type eq 42 }">
	          		<input type="button" value="出  库" class="but4" onclick="confirmBill(1)" />&nbsp;&nbsp;&nbsp;&nbsp;
          		</c:if>
          		<input type="button" value="退  回" class="but3" onclick="confirmBill(2)" />&nbsp;&nbsp;&nbsp;&nbsp;
          	</c:if>
           	<html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();" />
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">//<![CDATA[
                                          
	function confirmBill(flag){
		if(flag=='1'){
			var val = $("#confirm_money").val();
			if(val==''){
				alert("请输入确认金额");
				return;
			}
		}
		$("#con_state").val(flag);
		document.forms[0].submit();
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
		if(obj.value.length == 0 || isNaN(obj.value) || obj.value == 0) obj.value = "1";
	});
}


//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
