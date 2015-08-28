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
  <html-el:form action="/admin/KonkaStock">
    <html-el:hidden property="method" value="save" />
    <html-el:hidden property="mod_id" />
    <html-el:hidden property="queryString" />
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr>
        <td colspan="3">数据上报信息编辑</td>
      </tr>
      <tr>
        <td width="20%" class="title_item" nowrap="nowrap" align="right"><strong>客户名称：</strong></td>
        <td align="left" colspan="2"><c:out value="${r3Shop.customer_name}"/></td>
      </tr>
      <tr>
        <td width="20%" class="title_item" nowrap="nowrap" align="right"><strong>客户R3编码：</strong></td>
        <td align="left" colspan="2"><c:out value="${r3Shop.r3_code}" /></td>
      </tr>
      <tr>
      	<td width="20%" class="title_item" nowrap="nowrap" align="right"><strong>库存日期</strong></td>
        <td align="left" colspan="2">
	        <c:if test="${key eq 1 }">
	        	<input type="text" name="stock_date" id="stock_date"  value="${af.map.stock_date}"  readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:left;width:80px;" />
            </c:if>
            <c:if test="${empty key }">
            	<c:out  value="${af.map.stock_date }" />
            	<input type="hidden" name="stock_date" id="stock_date"  value="${af.map.stock_date}" />
            </c:if>
        </td>
      </tr>
      <tr>
        <td style="padding-left:30px;height:20px;"></td>
        <td><logic-el:match name="popedom" value="+1+"><input id="gsBTN" type='button' value='选择产品进行库存初始化' onclick="getPePdModel();"/></logic-el:match>
          <html-el:hidden property="pd_ids" styleId="pd_ids" />
          <html-el:hidden property="key" styleId="key" value="${key }"/></td>
      </tr>
      <tr>
        <td style="padding-left:30px;height:20px;"></td>
        <td><table width="650" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr>
        <td width="25%"><strong>型号名称</strong></td>
        <td width="25%"><strong>库存数量</strong></td>
        <td width="25%"><strong>库存成本单价</strong></td>
        <td></td>
      </tr>
      <c:if test="${not empty detailsList}">
        <c:forEach items="${detailsList}" var="cur">
          <c:if test="${not empty cur.map.md_name}">
            <tr>
              <td align="left">${cur.map.md_name}</td>
              <td align="left">${cur.stock_count}</td>
              <td align="left"><fmt:formatNumber pattern="#0.00" value="${cur.stock_cost}" /></td>
              <td><logic-el:match name="popedom" value="+2+"><a href="KonkaStock.do?method=toEdit&mod_id=${af.map.mod_id}&id=${cur.id}&r3_shop_id=${af.map.r3_shop_id}">修改</a> </logic-el:match></td>
            </tr>
          </c:if>
        </c:forEach>
      </c:if>
	  <c:forEach items="${pePdList}" var="cur" varStatus="vs">
	   	  <tr>
            <td align="left">${cur.md_name}</td>
            <td align="left"><input type="text" name="${cur.pd_id}" value="0" size="10" onkeyup="javascript:setOnlyNum(this);" maxlength="15" /><span style="color:#666;">台</span> </td>
            <td align="left"><input type="text" name="${cur.pd_id}_1" size="10" value="0"  onkeyup="javascript:setOnlyDouble(this);" maxlength="10" /><span style="color:#666;">元</span> </td>
          	<td></td>
          </tr>
	  </c:forEach>
      <!-- 
		
		
			
			  <c:forEach items="${kpmList}" var="kpm">
			 	 <c:if test="${not empty kpm.map.md_name}">
					  <tr>
			            <td align="left">${kpm.map.md_name}</td>
			            <td align="left"><input type="text" name="${kpm.pd_id}" size="10" onkeyup="javascript:setOnlyNum(this);" maxlength="15" value="${kpm.stock_count}" /></td>
			            <td align="left"><input type="text" name="${kpm.pd_id}_1" size="10" onkeyup="javascript:setOnlyDouble(this);" maxlength="10" value=<fmt:formatNumber pattern="#0.00" value="${kpm.stock_cost}" /> ></input></td>
			          </tr>
			     </c:if>
			  </c:forEach>
	  -->
      
      <tbody  id="t_id">
      </tbody>
    </table></td>
      </tr>
    </table>
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr>
        <td colspan="3" align="center"><html-el:button value=" 保存 " styleId="btn_submit" property="btn_submit" styleClass="but4" />
          <html-el:button property="back" value=" 返回 " onclick="history.back();" styleId="btn_back" styleClass="but5"/></td>
      </tr>
    </table>
  </html-el:form>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#stock_date").attr("dataType", "Require").attr("msg", "请填写日期");
	
	$("#btn_submit").click(function(){
		
		if(Validator.Validate(this.form, 3)){
			$("#btn_submit"   ).attr("disabled",true);
			$("#btn_temporary").attr("disabled",true);
			$("#btn_back"     ).attr("disabled",true);
			
			this.form.submit();
		}
	});

});

function getPePdModel() { 
	var returnValue = window.showModalDialog("SelectPePdModel.do?selectype=mutiple&stockOrSell=stock&selects=" + $("#pd_ids").val() + "&cus_sn=${r3Shop.r3_code}&azaz=" + Math.random(),window,"dialogWidth:600px;status:no;dialogHeight:500px");
	if (returnValue != null) {
		$("#pd_ids").val(returnValue.ids);
		$.ajax({
			type: "POST",
			url: "KonkaStock.do",
			data: "method=getPePdModel&pd_ids=" + $("#pd_ids").val() +"&key="+ $("#key").val(),
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


function setOnlyNum(obj) {
	var v = obj.value.replace(/[^\d-]/gi,'');
	obj.value=v;
	
}
function setOnlyDouble(obj) {
	var v = obj.value.replace(/[^\d+(\.\d+)?-]/gi,'');
	obj.value=v;
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
