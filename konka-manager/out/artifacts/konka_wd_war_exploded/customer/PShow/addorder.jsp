<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康佳产品展示平台</title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/pshow/css/global.css">
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/pshow/css/sale.css">
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/pshow/css/buyer.css">
<link href="${ctx}/styles/customer/jquery-ui/themes/redmond/jquery-ui-1.8.2.custom.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<!-- head start -->
<div id="topbox">
  <jsp:include page="_inc/_top2.jsp" flush="true" />
  <jsp:include page="_inc/_head2.jsp" flush="true" />
</div>
<!-- head end --> 
<!-- topnav start -->
<jsp:include page="_inc/_head_nav22.jsp" flush="true" />
<!-- topnav end --> 

<!-- third start -->
<div class="maincont" style="padding-top:20px;">
  <html-el:form action="/manager/PShow" method="post" styleClass="class_form"> 
    <input type="hidden" name="method" value="addOrderTwo" />
    <input type="hidden" name="id" value="${entity.id}" />
    <input type="hidden" name="num" value="${af.map.num}" />
    <input type="hidden" name="real_name" id="real_name" value="${customerUserInfo.real_name}" />
    <input type="hidden" name="link_addr" id="link_addr" value="${customerUserInfo.link_addr}" />
    <input type="hidden" name="link_phone" id="link_phone" value="${customerUserInfo.link_phone}" />
    <input type="hidden" name="link_tel" id="link_tel" value="${customerUserInfo.link_tel}" />
    <input type="hidden" name="link_p_index" id="link_p_index" value="${link_p_index}" />
    <input type="hidden" name="link_zip" id="link_zip"  />
    <div class="buyone"></div>
    <div class="buy_list"><font class="orange14px">收货人信息</font></div>
    <div class="buybox">
      <div class="address">
        <ul id="address-list" class="address-list">
          <li class="selected"><span class="marker-tip">寄送至：</span><span>&nbsp;${link_p_index_name}(${customerUserInfo.real_name}&nbsp;收)&nbsp;<em>${customerUserInfo.link_phone}<c:if test="${not empty customerUserInfo.link_tel}">/${customerUserInfo.link_tel}</c:if></em></span></li>
        </ul>
        <a id="J_newAddressBtn" class="new">使用新地址</a> </div>
    </div>
    <div class="buy_list"><font class="orange14px">购买的商品</font></div>
    <c:if test="${not empty entity}">
      <table width="990" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px;">
        <tr>
          <td height="20" align="center" nowrap="nowrap" style="background:url(${ctx}/styles/customer/pshow/images/az.jpg) repeat-x;border-bottom:1px dotted #B2D1FF;"><strong>图片</strong></td>
          <td width="469" align="left" nowrap="nowrap" style="background:url(${ctx}/styles/customer/pshow/images/az.jpg) repeat-x;border-bottom:1px dotted #B2D1FF;"><strong>商品名称</strong></td>
          <td width="90" align="center" nowrap="nowrap" style="background:url(${ctx}/styles/customer/pshow/images/az.jpg) repeat-x;border-bottom:1px dotted #B2D1FF;"><strong>单价</strong></td>
          <td width="80" align="center" nowrap="nowrap" style="background:url(${ctx}/styles/customer/pshow/images/az.jpg) repeat-x;border-bottom:1px dotted #B2D1FF;"><strong>数量</strong></td>
          <td width="100" align="center" nowrap="nowrap" style="background:url(${ctx}/styles/customer/pshow/images/az.jpg) repeat-x;border-bottom:1px dotted #B2D1FF;"><strong>小计</strong></td>
        </tr>
        <tr style="background:#FAFCFF;">
          <td width="106" align="center" nowrap="nowrap" style="border-bottom:1px dotted #B2D1FF;"><img src="${ctx}/${fn:substringBefore(entity.main_pic, '.')}_086.jpg" /></td>
          <td height="81" align="left" nowrap="nowrap" style="border-bottom:1px dotted #B2D1FF;"><a href="${ctx}/customer/manager/PShow.do?method=OrderNow&id=${entity.id}" target="_blank">${af.map.md_name}</a></td>
          <td height="81" align="center" nowrap="nowrap" style="border-bottom:1px dotted #B2D1FF;"><font style="font-size:13px;font-family:verdana;"><fmt:formatNumber value="${entity.buy_price}" type="currency" /></font></td>
          <td height="81" align="center" nowrap="nowrap" style="border-bottom:1px dotted #B2D1FF;"><font style="font-size:13px;font-family:verdana;">${af.map.num}</font></td>
          <td height="81" align="center" nowrap="nowrap" style="border-bottom:1px dotted #B2D1FF;"><font class="orange" style="font-size:13px;font-family:verdana;"><fmt:formatNumber value="${entity.buy_price * af.map.num}" type="currency" /></font></td>
        </tr>
        <tr style="background:#F2F7FF;">
          <td colspan="5" style="border-right:1px solid #fff;" valign="middle"><div style="text-align:left;padding:0 10px;margin-bottom:5px;margin-top:10px;">
              <label style="vertical-align:top;">给卖家留言：</label>
              <textarea title="选填，可以告诉卖家您对商品的特殊要求，如：颜色、尺码等" name="remark" id="remark" class="remark" style="width:396px;height:40px;font-size:12px;"></textarea>
              <div id="info_note"  style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/styles/images/tishi.gif" style="vertical-align:middle;" /></div>
            </div>
            <div style="text-align:left;padding:0 10px;line-height:30px;">
              <label style="vertical-align:top;">是否需要发票？</label>
              <input type="checkbox" name="bill_is_add" id="bill_is_add" value="1" />
            </div></td>
        </tr>
      </table>
    </c:if>
    <table width="990" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:20px;">
      <tr>
        <td colspan="2" align="left" style="padding-left:10px; font-size:14px; font-weight:bold;padding-bottom:5px;">结算信息</td>
      </tr>
      <tr>
        <td width="250" height="46" align="left" bgcolor="#fef7e8" style="border-top:1px #F60 solid; padding-left:10px;"></td>
        <td width="740" bgcolor="#fef7e8" nowrap="nowrap" style="border-top:1px #F60 solid;font-weight:bold;font-size:14px;padding-right:10px;text-align:right;">应付总额：<font class="orange" style="font-size:22px;">
          <fmt:formatNumber value="${entity.buy_price * af.map.num}" type="currency" /></font>&nbsp;</td>
      </tr>
    </table>
    <table width="990" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px; margin-bottom:30px;">
      <tr>
        <td align="right" nowrap="nowrap"><input type="button" id="orderSvBtn" class="orderSvBtn" style="border:0px;" name="orderSvBtn" value="确认无误，提交订单" /></td>
      </tr>
    </table>
    <!--</form>--> 
  </html-el:form>
  <div class="clear"></div>
</div>
<!-- third end --> 

<!-- six start -->
<div class="maincont"> 
  <!-- footer start-->
  <jsp:include page="_inc/_footer2.jsp" flush="true" />
  <!-- footer end--> 
</div>
<!-- six end --> 
<div id="newUserRelationDiv" style="display:none;">
	<form id="userRelationForm" method="post" action="${ctx}/manager/PShow.do">
	<table width="100%" align="center" border="0" cellpadding="2" cellspacing="0">
		<tr>
			<td width="12%" align="right" nowrap="nowrap"><font style="color:#f00;font-size:14px;vertical-align:middle;">*</font>&nbsp;所属地区：</td>
			<td width="50%" align="left" height="40" nowrap="nowrap">
				<select name="province" id="province" onmouseover="this.style.width='110px';" onchange="this.style.width='110px';" onblur="this.style.width='110px';" style="width:110px;">
                  	<option value="">-请选择-</option>
                </select>
                <select name="city" id="city" onmouseover="this.style.width='110px';" onchange="this.style.width='110px';" onblur="this.style.width='110px';" style="width:110px;">
                	<option value="">-请选择市-</option>
                </select>
                <select name="country" id="country" onmouseover="this.style.width='110px';" onchange="this.style.width='110px';" onblur="this.style.width='110px';" style="width:110px;">
                	<option value="">-请选择县-</option>
                </select>
                <select name="town" id="town" onmouseover="this.style.width='110px';" onchange="this.style.width='110px';" onblur="this.style.width='110px';" style="width:110px;">
                	<option value="">-请选择乡镇-</option>
                </select>
            </td>
            <td width="5%" align="right" nowrap="nowrap"><font style="color:#f00;font-size:14px;vertical-align:middle;">*</font>&nbsp;邮政编码：</td>
            <td align="left"><input type="text" name="rel_zip" id="rel_zip" maxlength="6" style="width:116px;" /></td>
		</tr>
		<tr>
			<td align="right" nowrap="nowrap" style="border-bottom:1px solid #ccc;"><font style="color:#f00;font-size:14px;vertical-align:middle;">*</font>&nbsp;街道地址：</td>
			<td align="left" colspan="3" style="padding-bottom:10px;border-bottom:1px solid #ccc;">
				<textarea name="rel_addr" id="rel_addr" maxlength="50"  cols="80" rows="2" class="address-txt" style="font-size:14px;width:666px;"></textarea>
				<div id="info_chat_content_2"  style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/styles/images/tishi.gif" style="vertical-align:middle;" /></div>
			</td>
		</tr>
		<tr>
			<td align="right" nowrap="nowrap"><font style="color:#f00;font-size:14px;vertical-align:middle;">*</font>&nbsp;收货人姓名：</td>
			<td align="left" colspan="3" style="padding-top:10px;" height="35"><input type="text" name="rel_name" id="rel_name" maxlength="10" /></td>
		</tr>
		<tr>
			<td align="right" nowrap="nowrap">手机：</td>
			<td align="left" colspan="3" height="35"><input type="text" name="rel_phone" id="rel_phone" maxlength="20" /></td>
		</tr>
		<tr>
			<td align="right" nowrap="nowrap">电话：</td>
			<td align="left" colspan="3" height="35"><input type="text" name="rel_tel" id="rel_tel" maxlength="20" /></td>
		</tr>
	</table>
	</form>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#province").attr({"subElement": "city", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.province}", "datatype": "Require", "msg": "请选择省名称！"});
	$("#city"    ).attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${af.map.city}", "datatype": "Require", "msg": "请选择市名称！"});
	$("#country" ).attr({"subElement": "town", "defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${af.map.country}", "datatype": "Require", "msg": "请选择县名称！"});
	$("#town"    ).attr({"defaultText": "-请选择乡镇-", "defaultValue": "", "selectedValue": "${af.map.town}"});
	
	$("#province").cs_ext("${ctx}/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false, "p_index_join", "${af.map.p_index_join}");
	$("#province").change();
	
	// 卖家留言
	$("#remark").textbox({
		maxLength: 100,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/styles/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_note").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_note").slideUp("normal");
	});
	
	// 街道地址
	$("#rel_addr").textbox({
		maxLength: 50,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/styles/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_chat_content_2").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_chat_content_2").slideUp("normal");
	});
	
	//添加新的收货人
	$("#J_newAddressBtn").click(function(){
		$("#newUserRelationDiv").dialog({
      		title: '<span style="font-size:14px;font-weight:bold;font-family:黑体;">使用新地址</span>', 
	      	width: 850,
	      	height: 340,
	      	draggable: true,
	      	resizable: false,
	      	position:'center',
	      	modal : true,
	      	open: function(event, ui) {
      			$("#province", "#newUserRelationDiv").attr("dataType", "Require").attr("msg", "请选择省/直辖市/自治区");
      		  	$("#city", "#newUserRelationDiv").attr("dataType", "Require").attr("msg", "请选择市");
      		  	$("#country", "#newUserRelationDiv").attr("dataType", "Require").attr("msg", "请选择区/县");
      		  	$("#rel_zip", "#newUserRelationDiv").attr("dataType", "Require").attr("msg", "请填写邮政编码");
      		  	$("#rel_addr", "#newUserRelationDiv").attr("dataType", "Require").attr("msg", "请填写街道地址");
      		  	$("#rel_name", "#newUserRelationDiv").attr("dataType", "Require").attr("msg", "请填写收货人姓名");
      		  	$("#rel_tel").attr("dataType","Phone").attr("msg","电话格式不正确，正确格式为：“0755-88888888”！").attr("Require", "false");
      			$("#rel_phone").attr("dataType","Mobile").attr("msg","手机格式不正确，正确格式为：“13888888888”！").attr("Require", "false");
      		},
      		close: function(event, ui) {
      			$("#province", "#newUserRelationDiv").removeAttr("dataType");
      		  	$("#city", "#newUserRelationDiv").removeAttr("dataType");
      		  	$("#country", "#newUserRelationDiv").removeAttr("dataType");
      		  	$("#rel_zip", "#newUserRelationDiv").removeAttr("dataType");
      		  	$("#rel_addr", "#newUserRelationDiv").removeAttr("dataType");
      		  	$("#rel_name", "#newUserRelationDiv").removeAttr("dataType");
      		  	$("#rel_tel", "#newUserRelationDiv").removeAttr("dataType");
      		  	$("#rel_phone", "#newUserRelationDiv").removeAttr("dataType");
      		},
	      	buttons: {"确认": function() {
								if(Validator.Validate(document.getElementById("userRelationForm"), 1)){
  									if(confirm("确定保存使用该收货地址？")){
  										// 收货地址修改
  										var rel_tel = $("#rel_tel").val();
  										if(rel_tel != "") rel_tel = "/" + rel_tel;
  										
  										var town = "";
  										if($("#town").val() != "")
  											town = $("#town").find("option:selected").text(); 
  										
  										$("#address-list").html('<li class="selected"><span class="marker-tip">寄送至：</span>&nbsp;' + $("#province").find("option:selected").text() + '&nbsp;' + $("#city").find("option:selected").text() + '&nbsp;' + $("#country").find("option:selected").text() + '&nbsp;' + town + '&nbsp;' + $("#rel_addr").val() +'(' + $("#rel_name").val() + '&nbsp;收)&nbsp;<em>' + $("#rel_phone").val() + rel_tel + '</em></li>');
										
  										// 地区处理
  										var link_p_index = $("#province").val();
										if("" != $("#city").val()) link_p_index = $("#city").val();
										if("" != $("#country").val()) link_p_index = $("#country").val();
										if("" != $("#town").val()) link_p_index = $("#town").val();
										
										// 赋值
										$("#link_p_index").val(link_p_index);
										$("#link_addr").val($("#rel_addr").val());
										$("#real_name").val($("#rel_name").val());
										$("#link_phone").val($("#rel_phone").val());
										$("#link_tel").val($("#rel_tel").val());
										$("#link_zip").val($("#rel_zip").val());
										
  										$(this).dialog("close");
  									}
    							}
							},
					  "关闭": function() {$(this).dialog("close"); }
					 }
		}).dialog("open");
		
	});
	
	// 设置必填字段
	$("#link_p_index").attr("dataType", "Require").attr("msg", "提示，收货地址信息不全！");
	$("#real_name"   ).attr("dataType", "Require").attr("msg", "提示，缺少收货人姓名！！");
	$("#link_phone"  ).attr("dataType", "Require").attr("msg", "提示，缺少收货人联系电话！！");
	
	//提交订单
	$("#orderSvBtn").click(function(){
		if(Validator.Validate(this.form, 1)){
		  if(confirm("亲爱的“${customerUserInfo.real_name}”，您好！确定要提交表单？")){
			$(".class_form").submit();
		  }
		}
	});
});
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
