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
  	<html-el:form action="/spgl/EcGiftOrdePaifa" method="post">
      <html-el:hidden property="id" styleId="id" value="${af.map.id}" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <html-el:hidden property="type" styleId="type" value="1"/>
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
            <td class="title_item" width="15%">配送方式：</td>
            <td colspan="2">
            <c:if test="${af.map.deliver_way eq 0}">上门自提</c:if>
             <c:if test="${af.map.deliver_way eq 1}">商家配送</c:if>
            </td>
            <td class="title_item" width="15%">发货前电话确认：</td>
            <td colspan="2">
            <c:if test="${af.map.deliver_is_call eq '0'}">否</c:if>
            <c:if test="${af.map.deliver_is_call eq 1}">是</c:if>
            </td>
          </tr>
           <tr>
            <td class="title_item" width="15%">送货日期：</td>
            <td colspan="2"><c:if test="${af.map.deliver_time eq '0'}">只工作日送货（双休日、假日不用送）</c:if>
            <c:if test="${af.map.deliver_way eq 1}">工作日、双休日与假日均可送货</c:if>
            <c:if test="${af.map.deliver_way eq 2}">只双休日、假日送货（工作日不用送）</c:if>
            </td>
            <td class="title_item" width="15%">收货地址：</td>
            <td colspan="2"><c:out value="${af.map.buyer_addr}" />
            </td>
          </tr>
          <tr>
          	 <td class="title_item" width="15%">订单积分：</td>
             <td colspan="5"><fmt:formatNumber value="${af.map.integral}" pattern="0" /></td>
          </tr>
          <tr>
            <td class="title_item" width="15%">所在地：</td>
            <td colspan="5"><c:out value="${p_index_name}" /></td>
          </tr>
        </table>
      </div>
      
      <div align="left" style="margin-top: 30px">
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
          <tr>
          	<td colspan="4"  style="background:#CCCCCC" >商品信息</td>
          </tr>	
          <tr>
          <td class="title_item" width="15%" style="text-align: center;">商品编码</td>
          <td class="title_item" width="15%" style="text-align: center;">商品名称</td>
		  <td class="title_item" width="8%" style="text-align: center;">数量</td>
          <td class="title_item" width="8%" style="text-align: center;">所需积分</td>
          </tr>
          <tr>
          <td nowrap="nowrap" style="text-align: center;">${fn:escapeXml(af.map.ecGift.pd_sn)}</td>
          <td nowrap="nowrap" style="text-align: center;">${fn:escapeXml(af.map.ecGift.pd_name)}</td>
          <td width="8%" nowrap="nowrap" style="text-align: center;">1</td>
		  <td width="8%" nowrap="nowrap" style="text-align: center;"><fmt:formatNumber value="${fn:escapeXml(af.map.integral)}" pattern="0" /></td>
          </tr>
          <tr>
          <td class="title_item" width="15%" style="text-align: center;" >处理方式：</td>
          	<td colspan="4">
              	<input type="radio"  name="sex"  id="r1" value="0" />总部处理
             	<input type="radio"  name="sex" checked="checked" id="r2" value="1"/>派发处理
            </td>
          </tr>
          <tr id="t2">
          <td class="title_item" width="15%" style="text-align: center;">派发：</td>
          <td colspan="4"><html-el:select property="dept_id" styleId="dept_id" style="width:120px;">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${sybDeptInfoList}">
                <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
              </c:forEach>
            </html-el:select>&nbsp;&nbsp;</td>
       	  </tr>
         <tr>
          <td class="title_item" width="15%" style="text-align: center;">审核意见：</td>
          <td colspan="4"><html-el:text property="remark1"  maxlength="256" style="width:70%"></html-el:text></td>
        </tr>
          <tr>
          	  <td colspan="5"  height="40" align="center"><input class="bgButtonSave" type="button" name="Submit4" value=" 保存 " id="btn_submit" />
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
		}
		if(document.all("sex")[1].checked){
		$("#t2").css("display","block");
		}
	});

	$("#r1").click(function(){
		$("#type").val(0);
		$("#t2").hide();
	});
	$("#r2").click(function(){
		$("#type").val(1);
		$("#t2").show();
	});
	
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 3)){
				$("#btn_submit").attr("disabled",true);
				$("#btn_back").attr("disabled",true);
				this.form.submit();
		}
	});
});


//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
