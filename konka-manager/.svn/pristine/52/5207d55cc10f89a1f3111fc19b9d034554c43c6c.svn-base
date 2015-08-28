<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>进货登记</title>
<base target="_self" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
  <div class="rtabcont2">
    <%@ include file="/commons/pages/messages.jsp" %>
    <html-el:form action="/manager/JBaseStore">
      <html-el:hidden property="method" value="save"/>
      <html-el:hidden property="queryString" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="bill_type" />
      <html-el:hidden property="store_id" />
      <html-el:hidden property="store_r3_id" value="${store_r3_id}" />
      <html-el:hidden property="returnUrl" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td colspan="2"  bgcolor="#CCCCCC" style="font-weight:bold;">仓库信息填写</td>
        </tr>
        <tr>
          <td width="17%" nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>仓库名称：</td>
          <td width="83%" align="left">
          <c:if test="${af.map.store_name eq '总库' && (r3_code eq sale_r3_code)}">总库</c:if>
           <c:if test="${af.map.store_name eq '总库' && (r3_code ne sale_r3_code)}">
           <html-el:text property="store_name" size="40" maxlength="15" styleId="store_name" />
           </c:if>
          <c:if test="${af.map.store_name ne '总库'}">
          <html-el:text property="store_name" size="40" maxlength="15" styleId="store_name" />
          </c:if></td>
       </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">仓库编码：</td>
          <td align="left">
          <c:if test="${empty af.map.store_sn }"><font color="red">自动生成</font></c:if>
          <c:if test="${not empty af.map.store_sn }">
	          <html-el:text property="store_sn" size="40" maxlength="15" styleId="store_sn" readonly="true"/>
          </c:if>
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">仓库所属售达方编码：</td>
          <td>
           <html-el:text property="r3_code" size="40" value="${r3_code}"  maxlength="15" styleId="r3_code" readonly="readonly"/>
           </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">仓库所属送达方编码：</td>
          <td align="left">
           <html-el:select property="sale_r3_code" styleId="sale_r3_code" value="${sale_r3_code}">
           <html-el:option value="">-请选择-</html-el:option>
            <c:if test="${not empty sale_r3_code}" >
            <html-el:option value="${sale_r3_code}">${sale_r3_code}</html-el:option>
            </c:if>
			<c:forEach var="cur" items="${jBaseStoreR3List}" varStatus="vs">
			<html-el:option value="${cur.sale_r3_code}">${cur.sale_r3_code}</html-el:option>
			</c:forEach>     
           </html-el:select>
           </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">仓库地址：</td>
          <td align="left"><html-el:text property="store_addr" size="80" maxlength="120" styleId="store_addr" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">备注：</td>
          <td align="left">
          	<textarea name="remarks" rows="3" cols="50" id="remarks">${af.map.remarks}</textarea>
          	<div id="remarks_1"  style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/styles/jxc/images/tishi.gif" style="vertical-align:middle;" /></div>
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>默认进货仓库：</td>
          <td align="left">
          <c:if test="${af.map.store_name ne '总库'}">
          	否<html-el:hidden property="is_dft_buy_store" value="0"/>
          </c:if>
          <c:if test="${af.map.store_name eq '总库'}">
	          <c:if test="${af.map.is_dft_buy_store eq 0}">
	          <html-el:select property="is_dft_buy_store" styleId="is_dft_buy_store">
	          		<html-el:option value="0">否</html-el:option>
	          		<html-el:option value="1">是</html-el:option>
	          </html-el:select>
	          </c:if>
	          <c:if test="${af.map.is_dft_buy_store eq 1}">是<html-el:hidden property="is_dft_buy_store" value="1"/></c:if>
	      </c:if>
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>默认出货仓库：</td>
          <td align="left">
          <c:if test="${af.map.store_name ne '总库'}">
          	否<html-el:hidden property="is_dft_sell_store" value="0"/>
          </c:if>
          <c:if test="${af.map.store_name eq '总库'}">
	          <c:if test="${af.map.is_dft_sell_store eq 0}">
	          <html-el:select property="is_dft_sell_store" styleId="is_dft_sell_store">
	          		<html-el:option value="0">否</html-el:option>
	          		<html-el:option value="1">是</html-el:option>
	          </html-el:select>
	          </c:if>
	          <c:if test="${af.map.is_dft_sell_store eq 1}">是<html-el:hidden property="is_dft_sell_store" value="1"/></c:if>
          </c:if>
          </td>
        </tr>
<!--         <tr> -->
<!--           <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>是否删除：</td> -->
<!--           <td align="left"> -->
<%--           <c:if test="${af.map.store_name ne '总库'}"> --%>
<%--           <html-el:select property="is_del" styleId="is_del"> --%>
<%--           		<html-el:option value="">请选择</html-el:option> --%>
<%--           		<html-el:option value="0">否</html-el:option> --%>
<%--           		<html-el:option value="1">是</html-el:option> --%>
<%--           </html-el:select> --%>
<%--           </c:if> --%>
<%--           <c:if test="${af.map.store_name eq '总库'}"> 该仓库不能被删除 </c:if> --%>
<!--           </td> -->
<!--         </tr> -->
        <tr>
          <td>&nbsp;</td>
          <td><html-el:button property="" value="提 交" styleClass="but4" styleId="btn_submit" />
            <html-el:button property="" value="重 置" styleClass="but5" styleId="btn_reset" onclick="this.form.reset();" />
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();" />
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#store_name").attr("dataType", "Require").attr("msg", "请填写仓库名称！");
	$("#is_dft_buy_store").attr("dataType", "Require").attr("msg", "请选择默认进货仓库！");
	$("#is_dft_sell_store").attr("dataType", "Require").attr("msg", "请选择默认出货仓库！");
// 	$("#is_del").attr("dataType", "Require").attr("msg", "请选是否删除！");
	$("#r3_code").attr("readonly","readonly");

	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 3)){
			$("#tr_model").remove();
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_reset").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});

	$("#remarks").textbox({
		maxLength: 100,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/styles/jxc/images/tishi.gif" style="vertical-align:middle;" />';
			$("#remarks_1").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#remarks_1").slideUp("normal");
	});
});
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
