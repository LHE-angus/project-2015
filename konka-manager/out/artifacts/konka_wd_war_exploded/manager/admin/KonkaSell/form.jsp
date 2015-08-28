<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
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
      <html-el:form action="/admin/KonkaSell">
        <html-el:hidden property="s_id" styleId="s_id" />
        <html-el:hidden property="method" value="save" />
        <html-el:hidden property="r3_shop_id"/>
        <html-el:hidden property="mod_id" />
        <html-el:hidden property="queryString" />
        <input type="hidden" name="is_temporary" value="0" id="is_temporary" />
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
	      <tr>
	        <td colspan="2">数据上报信息编辑</td>
	      </tr>
          <tr>
            <td width="20%" class="title_item" nowrap="nowrap" align="right" ><strong>客户名称：</strong></td>
            <td align="left"><c:out value="${r3Shop.customer_name}"/></td>
          </tr>
          <tr>
            <td width="20%" class="title_item" nowrap="nowrap" align="right" ><strong>客户R3编码：</strong></td>
            <td align="left"><c:out value="${r3Shop.r3_code}" />  <span style="color:#F00;">注：单个客户对每个销售日期仅能上传一份报表。</span></td>
          </tr>
          <tr>
            <td width="20%" class="title_item" nowrap="nowrap" align="right" ><strong>销售日期：</strong></td>
            <td align="left"><html-el:text property="sell_date_temp" size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" styleId="sell_date_temp" /></td>
          </tr>
          <tr>
            <td width="20%" class="title_item" nowrap="nowrap" align="right" ><strong>信息填写时间：</strong></td>
            <td align="left"><fmt:formatDate value="${af.map.now_date}" pattern="yyyy年MM月dd日" /></td>
          </tr>
          <tr>
         	<td width="20%" class="title_item" nowrap="nowrap" align="right" ></td>
            <td align="left">
            <input id="gsBTN" type='button' value='选择产品进行销售数据上报' onclick="getPePdModel();"/>
            <c:if test="${empty af.map.s_id }">
	            <c:if test="${af.map.report_count ne 0}">
	                 <input id="hisBTN" type='button' value='上次上报产品' onclick="getLastSellRecord();"/>
	            </c:if>
            </c:if>
            <html-el:hidden property="pd_ids" styleId="pd_ids" />
            <html-el:hidden property="selected_pd_ids" styleId="selected_pd_ids" />
            </td>
          </tr>
          
          <%--<c:if test="${empty af.map.s_id}">
			  <c:forEach items="${kpmList}" var="cur" varStatus="vs">
				  <tr>
		            <td align="left">${cur.md_name}</td>
		            <td align="left">销售数量：<input type="text" name="${cur.pd_id}" id="${cur.pd_id}" size="10" onblur="getCalculateAmount('${cur.pd_id}','money_${cur.pd_id}','total_${cur.pd_id}');" onkeyup="javascript:setOnlyNum(this);" maxlength="15" /><span style="color:#666;">台</span>
		            	销售单价：<input type="text" name="money_${cur.pd_id}" id="money_${cur.pd_id}" size="10" onblur="getCalculateAmount('${cur.pd_id}','money_${cur.pd_id}','total_${cur.pd_id}');" onkeyup="javascript:setOnlyDouble(this);" maxlength="10" /> <span style="color:#666;">元</span>
			            销售总价：<input type="text" name="total_${cur.pd_id}" id="total_${cur.pd_id}" size="10" onkeyup="javascript:setOnlyDouble(this);" maxlength="15" disabled="disabled" /> <span style="color:#666;">元</span>
			            </td>
		          </tr>
			  </c:forEach>
		  </c:if> --%>
		  <tr>
		  	<td align="center" width="20%"></td>
            <td>
            <table  width="80%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
            <tr>
            <td align="center" width="20%"><strong>型号名称</strong></td>
            <td><strong>销售情况</strong></td>
          </tr>
		  <c:if test="${not empty af.map.s_id}">
			  <c:forEach items="${kpmList}" var="kpm">
			    <c:forEach items="${af.map.konkaSellDetailsList}" var="ksd">
			      <c:if test="${kpm.pd_id eq ksd.pd_id}">
			     <tr>
			            <td align="left">${kpm.md_name}</td>
			            <td align="left">销售数量：<input type="text" name="${kpm.pd_id}" id="${kpm.pd_id}" size="10" onblur="getCalculateAmount('${kpm.pd_id}','money_${kpm.pd_id}','total_${kpm.pd_id}');" onkeyup="javascript:setOnlyNum(this);" maxlength="15" value="${ksd.sell_count}" /><span style="color:#666;">台</span>
			            销售单价：<input type="text" name="money_${kpm.pd_id}" id="money_${kpm.pd_id}" size="10"  onblur="getCalculateAmount('${kpm.pd_id}','money_${kpm.pd_id}','total_${kpm.pd_id}');" onkeyup="javascript:setOnlyDouble(this);" maxlength="15" value="${ksd.sell_money}" /> <span style="color:#666;">元</span>
			            销售总价：<input type="text" name="total_${kpm.pd_id}" id="total_${kpm.pd_id}" size="10" maxlength="15" value="${ksd.sell_count*ksd.sell_money}" disabled="disabled" /> <span style="color:#666;">元</span>
			            </td>
			     </tr>
			      </c:if>
		       </c:forEach>
			  </c:forEach>
		  </c:if> 
		  <tbody  id="p_id">
          </tbody> 
		  <tbody  id="t_id">
          </tbody>
          </table>
           </td>
           </tr> 
          </table>
         <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
		  <tr>
            <td width="20%" class="title_item" nowrap="nowrap" align="right"><strong>状态：</strong></td>
            <td align="left">
            	<c:choose>
            		<c:when test="${af.map.state eq 0}">已暂存</c:when>
            		<c:when test="${af.map.state eq 1}"><span style="color:#f00;">已上传</span></c:when>
            	</c:choose>
            </td>
          </tr>
          <tr>
            <td colspan="2" align="center"><html-el:button value=" 保存 " styleId="btn_submit" property="btn_submit" styleClass="but4"/>
            	<html-el:button styleClass="but4" value=" 暂存 " styleId="btn_temporary" property="btn_temporary" />
              <html-el:button styleClass="but5" property="back" value=" 返回 " onclick="history.back();" styleId="btn_back" /></td>
          </tr>
        </table>
      </html-el:form>
    </div>

<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#cus_sn"         ).attr("datatype","Require").attr("msg", "请填写客户R3编码！");
	$("#cus_name"       ).attr("datatype","Require").attr("msg", "请选择客户名称！");
	$("#sell_date_temp").attr("datatype","Require").attr("msg", "请选择销售时间！");
	
	$("#btn_submit").click(function(){
		
		if(Validator.Validate(this.form, 3)){
			$("#btn_submit"   ).attr("disabled",true);
			$("#btn_temporary").attr("disabled",true);
			$("#btn_back"     ).attr("disabled",true);
			
			this.form.submit();
		}
	});

	
	$("#btn_temporary").click(function(){
		$("#is_temporary").val("1");
		
		if(Validator.Validate(this.form, 3)){
			$("#btn_submit"   ).attr("disabled",true);
			$("#btn_temporary").attr("disabled",true);
			$("#btn_back"     ).attr("disabled",true);
			
			this.form.submit();
		}
	});
});

function getPePdModel() { 
	var returnValue = window.showModalDialog("SelectPePdModel.do?selectype=mutiple&selects=" + $("#pd_ids").val() +"&selected_pd_ids="+ $("#selected_pd_ids").val() + "&cus_sn=${r3Shop.r3_code}&azaz=" + Math.random(),window,"dialogWidth:600px;status:no;dialogHeight:500px");
    $("#pd_ids").val(returnValue.ids);
	if (returnValue != null) {
		$.ajax({
			type: "POST",
			url: "KonkaSell.do",
			data: "method=getPePdModel&pd_ids=" + $("#pd_ids").val() +"&selected_pd_ids="+ $("#selected_pd_ids").val(),
			//dataType: "json",
			error: function(request, settings) {
				alert("添加产品失败，请重新添加");
			},
			success: function(oper) {
				$('#t_id').empty();
				$('#t_id').append(oper);
			}
		});
	}
}

function getLastSellRecord(){
	var r3_shop_id = '${af.map.r3_shop_id}';
	var s_id ='${af.map.s_id}';	
	$.ajax({
		type: "POST",
		url: "KonkaSell.do",
		data: "method=getLastSellRecord&r3_shop_id=" + r3_shop_id +"&s_id="+ s_id,
		error: function(request, settings) {
			alert("操作失败，请重试！");
		},
		success: function(oper) {
			if(oper != ''){
				$('#p_id').empty();
				$('#p_id').append(oper);
				
			}else{
				alert("该网点没有您上报的产品记录，请选择产品！");
			}
		}
	});
}

function setOnlyNum(obj) {
	var v = obj.value.replace(/[^\d-]/gi,'');
	if(v==0){
		obj.value='';
	}else{
		obj.value=v;
	}
}
function setOnlyDouble(obj) {
	var v = obj.value.replace(/[^\d+(\.\d+)?-]/gi,'');
	if(v==0){
		obj.value='';
	}else{
		obj.value=v;
	}
}
function getCalculateAmount(count, price, total) {
	eval("$('#" + total+ "').val($('#" + count+ "').val()*$('#" + price + "').val())");
	
	}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
