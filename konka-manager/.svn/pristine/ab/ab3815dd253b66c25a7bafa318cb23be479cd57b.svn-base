<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/commons/styles/EntpShopSearch_style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/EntpShopGSite/map.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}> 直接开拓</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
     <html-el:form action="/admin/AgentsList">
      <html-el:hidden property="method" value="searchKonkaShop" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <html-el:hidden property="shop_id" value="${af.map.shop_id}" />
      <html-el:hidden property="r3_id" value="${af.map.r3_id}" />
      
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tdTab">
      <tr>
      <td>
          <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tdTab">
                  <tr>
                    <td colspan="2" valign="middle" nowrap="nowrap" class="title_item">
                        <center>当前代理商信息</center>
                    </td>
                  </tr>
                  <tr>
                    <td width="15%" valign="middle" nowrap="nowrap" class="title_item">R3编码：</td>
                    <td width="85%" valign="middle" nowrap="nowrap">
                     ${R3Shop.r3_code}
                    </td>
                  </tr>
                  <tr>
                    <td width="15%" valign="middle" nowrap="nowrap" class="title_item">代理商名称：</td>
                    <td width="85%" valign="middle" nowrap="nowrap">
                     ${R3Shop.customer_name}
                     </td>
                  </tr>
                  <tr>
                    <td width="15%" valign="middle" nowrap="nowrap" class="title_item">代理商的网点：</td>
                    <td width="85%" valign="middle" nowrap="nowrap">
                      <span style="float:right;margin-right:20px;">
                          <html-el:button property="dls_shop" styleId="dls_shop_btn" styleClass="but1" onclick="doNeedMethod(null, 'AgentsList.do', 'jxslist','r3_id=${R3Shop.id}&shop_id=${R3Shop.map.mmt_shop_id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())" value="查看" />
                      </span>
                     </td>
                  </tr>
             </table>
      </td>
         <td>
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tdTab">
                  <tr>
                    <td width="15%" valign="middle" nowrap="nowrap" class="title_item">区域选择：</td>
                    <td width="85%" valign="middle" nowrap="nowrap">
                      <select name="province" id="province" class="bd">
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
                    <td width="15%" valign="middle" nowrap="nowrap" class="title_item">网点名称：</td>
                    <td width="85%" valign="middle" nowrap="nowrap">
                     <html-el:text property="shop_name_like" styleId="shop_name_like" maxlength="40" size="40" />
                     </td>
                  </tr>
                  <tr>
                    <td align="left">&nbsp;</td>
                    <td align="left">
                      <span style="color:#f00;float:left;">★ 说明：不输入任何查询条件，系统默认不进行查询操作！</span> 
                    </td>
                  </tr>             
                  <tr>
                    <td align="left">&nbsp;</td>
                    <td align="left">
                      <span style="color:#f00;float:left;">&nbsp;</span> 
                      <span style="float:right;margin-right:20px;">
                          <html-el:button property="search" styleId="search_btn" styleClass="but1" value="搜索" />
                      </span></td>
                  </tr>
             </table>
         </td>
        </tr>
      </table>

    </html-el:form>
  </div>

  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1" id="page_list">
      <!-- 网点列表 -->
      <%@ include file="shop_list.jsp" %>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
<%@ include file="shop_list_js.jsp" %>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
    // 区域
	$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${af.map.province}"});
	$("#city"    ).attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${af.map.city}"});
	$("#country" ).attr({"subElement": "town","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${af.map.country}"});
	$("#town"    ).attr({"defaultText": "-请选择乡/镇-", "defaultValue": "", "selectedValue": "${af.map.town}"});

	$("#province").cs_ext("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceFourListByUser", "p_index", "0", false,"p_index_join","${af.map.p_index_join}");
	$("#province").change();

	// 查询事件处理 start
	$("#search_btn").click(function(){

		var province = $("#province").val();
		var city = $("#city").val();
		var country = $("#country").val();
		var town = $("#town").val();

		var sub_val = $.trim($("#shop_name_like").val());
		var shop_name = sub_val == $("#shop_name_like").attr("emptyValue") ? '' : sub_val;
		if($.trim(province).length == 0 && $.trim(city).length == 0 
				&& $.trim(country).length == 0 && $.trim(town).length == 0 
				&& shop_name.length == 0){
			alert("对不起，请至少选择或输入一个查询条件！");
			return false;
		}
		
		var isSubmit = Validator.Validate(this.form, 3);
		if (isSubmit) {
            $(this).attr("disabled","true").attr("value","正在查询...");
            $("#shop_name_like").val(shop_name);	
            this.form.submit();
		}
	});
	// 查询事件处理end
    $("#shop_name_like").keydown(function(e){ if (e.keyCode == 13) $("#search_btn").click(); }).textInputWithVal("请输入商铺名称/商铺产品名称/商铺简介搜索！");

    var shop_name_search = "${af.map.shop_name_like}";
    if(shop_name_search != "")
    {
    	$("#shop_name_like").val(shop_name_search);
    }
});

//以下为自定义的jQuery插件
$.fn.extend({
	textInputWithVal : function(val){
		var $this = this;
		return $this.attr("emptyValue", val).blur(function() {
			if ($.trim($this.val()).length == 0) { $this.val(val); }
		}).focus(function() {
			if ($.trim($this.val()) == val) { $this.val(""); }
		}).val(val);
	},
	json : function(){
		var obj = {};
		var ss = $(this).serialize().split("&");
		for (var i = 0; i < ss.length; i++) {
			obj[ss[i].split("=")[0]] = ss[i].split("=")[1];
		}
		return obj;
	}
});
$.extend({
	isBlank : function(s) { return (s === undefined) || (s === null) || /^\s*$/g.test(s.replace("null", "")); },
	isNotBlank : function(s) { return !$.isBlank(s); }
});

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>