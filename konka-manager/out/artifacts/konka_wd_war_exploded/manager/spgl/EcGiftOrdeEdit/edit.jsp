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
  	<html-el:form action="/spgl/EcGiftOrdeEdit" method="post">
      <html-el:hidden property="id" styleId="id" value="${af.map.id}" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
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
            <td colspan="2"> <html-el:text property="buyer_name" styleId="buyer_name"/>
            </td>
          </tr>
          <tr>
            <td  class="title_item" width="15%">购买人手机号码：</td>
            <td colspan="2">
            <html-el:text property="buyer_mp" styleId="buyer_mp" />
            </td>
            <td  class="title_item" width="15%">购买人固定电话：</td>
             <td colspan="2">
            <html-el:text property="buyer_tel" styleId="buyer_tel"/>
            </td>
          </tr>
          <tr>
            <td class="title_item" width="15%">配送方式：</td>
            <td colspan="2">
            <html-el:radio property="deliver_way" value="0">上门自提</html-el:radio>
            <html-el:radio property="deliver_way" value="1">商家配送</html-el:radio>
            </td>
            <td class="title_item" width="15%">发货前电话确认：</td>
            <td colspan="2">
            <html-el:radio property="deliver_is_call" value="0">否</html-el:radio>
            <html-el:radio property="deliver_is_call" value="1">是</html-el:radio>
            </td>
          </tr>
           <tr>
            <td class="title_item" width="15%">送货日期：</td>
            <td colspan="2">
            <html-el:radio property="deliver_time" value="0">只工作日送货（双休日、假日不用送）</html-el:radio>
            <html-el:radio property="deliver_time" value="1">工作日、双休日与假日均可送货</html-el:radio>
            <html-el:radio property="deliver_time" value="2">只双休日、假日送货（工作日不用送）</html-el:radio>
            </td>
            <td class="title_item" width="15%">订单状态：</td>
            <td colspan="2">
           <html-el:select property="state" styleId="state">
           		<html-el:option value="-30">已退货</html-el:option>
           		<html-el:option value="-20">审核未通过</html-el:option>
           		<html-el:option value="-10">已关闭</html-el:option>
           		<html-el:option value="0">已预订</html-el:option>
           		<html-el:option value="10">已兑换</html-el:option>
           		<html-el:option value="20">审核通过</html-el:option>
           		<html-el:option value="30">下发处理</html-el:option>
           		<html-el:option value="40">商家发货</html-el:option>
           		<html-el:option value="50">客户已换货</html-el:option>
           		<html-el:option value="60">确认收货</html-el:option>
           </html-el:select>
            </td>
          </tr>
          <tr>
             <td  class="title_item" width="15%">订单积分：</td>
             <td colspan="5"><fmt:formatNumber value="${af.map.integral}" pattern="0" /></td>
          </tr>
          <tr>
          	<td  class="title_item" width="15%">收货地址：</td>
             <td colspan="5">
            <html-el:text property="buyer_addr" styleId="buyer_addr"/>
            </td>
          </tr>
          <tr>
            <td class="title_item" width="15%">所在地：</td>
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
        </table>
      </div>
      
      <div style="margin-top: 30px">
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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${province}","dataType":"Require","msg":"请选择省/直辖市/自治区！"});
	$("#city").attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${city}"});
	$("#country").attr({"subElement": "town","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${country}"});
	$("#town").attr({"defaultText": "-请选择乡/镇-", "defaultValue": "", "selectedValue": "${town}"});
	
	$("#province").cs_ext("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false,"p_index_join","${af.map.p_index_join}");
	$("#province").change();

	
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
