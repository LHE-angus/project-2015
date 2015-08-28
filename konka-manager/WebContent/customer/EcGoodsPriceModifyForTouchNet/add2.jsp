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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
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
    <html-el:form action="/spgl/EcGoodsPriceModifyForTouchNet" method="post">
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save2" />
      <html-el:hidden property="queryString" styleId="queryString" />
       <html-el:hidden property="own_sys" styleId="own_sys" value="${af.map.own_sys}" />
      <html-el:hidden property="type1" styleId="type1" value="0"/>
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
          <tr>
      	  	<td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>价格类型：</td>
            <td width="88%" align="left">
            	<c:if test="${is_admin eq 1}">
            		<html-el:radio property="price_type" styleClass="price_type"  value="0"  >全国价格</html-el:radio>
            	</c:if>
            	<html-el:radio property="price_type" styleClass="price_type"  value="2" >分公司价格</html-el:radio>
            	<html-el:radio property="price_type" styleClass="price_type"  value="3" >市县价格</html-el:radio>
            </td>
      	  </tr>	
      	  <c:set var="readyOnly" value="${is_admin eq 1 ? 'none':''}" />
           <tr id="t1" style="display: ${readyOnly}">
            <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>分公司：</td>
            <td width="88%" align="left"><html-el:select property="dept_id" styleId="dept_id">
                <html-el:option value="">请选择...</html-el:option>
                <c:forEach var="cur" items="${sybDeptInfoList}">
                  <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
                </c:forEach>
              </html-el:select>
            </td>
          </tr>
          <tr id="t2" style="display: none">
            <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>地市区域：</td>
            <td width="88%" align="left"><select name="province" id="province" onmouseover="this.style.width='110px';" onchange="this.style.width='110px';" onblur="this.style.width='110px';" style="width:110px;">
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
               
              <!-- <span style="color: red">&nbsp;&nbsp;&nbsp;请至少选择到市级区域</span>--> </td> 
          </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>产品名称：</td>
          <td width="88%" align="left">${af.map.pd_name}</td>
        </tr>
          <html-el:hidden property="goods_id" styleId="goods_id"  value="${af.map.goods_id}" />
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>原价格：</td>
          <td width="88%" align="left"><html-el:text property="original_price" styleId="original_price" size="15" maxlength="10"/>
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>销售价格：</td>
          <td width="88%" align="left"><html-el:text property="price" styleId="price" size="15" maxlength="10"/>
          </td>
        </tr>
        <c:if test="${af.map.label_of_cate eq 5}">
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>开始时间：</td>
          <td width="88%" align="left"><fmt:formatDate value="${af.map.start_time}" pattern="yyyy-MM-dd" var="_start_time" />
            <html-el:text property="start_time" styleId="start_time" size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期"  value="${_start_time}"/>
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>结束时间：</td>
          <td width="88%" align="left"><fmt:formatDate value="${af.map.end_time}" pattern="yyyy-MM-dd" var="_end_time" />
            <html-el:text property="end_time" styleId="end_time" size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期"  value="${_end_time}"/>
          </td>
        </tr>
        </c:if>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">备注：</td>
          <td width="88%" align="left">
          	<html-el:textarea property="remarks" styleId="remarks" cols="5" style="width:450px;height:70px;" />
          	<div id="info_tip" style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div>
          </td>
        </tr>
        
        <tr>
          <td>&nbsp;</td>
          <td><html-el:button property="" value="提交" styleClass="but4" styleId="btn_submit" />
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="location.href = '${ctx}/manager/spgl/EcGoodsPriceModifyForTouchNet.do?method=list2&mod_id=${af.map.mod_id}&goods_id=${af.map.goods_id}&own_sys=${af.map.own_sys}' " /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#original_price").attr("datatype", "Require").attr("msg", "原价格不能为空!");
	$("#price").attr("datatype", "Require").attr("msg", "请填写！");

	$("#original_price").keypress(function(){//单价
		var price = $("#original_price").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(price)) {
			$("#original_price").val(0);
		}
	}).keyup(function(){
		var price = $("#original_price").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(price)) {
			$("#original_price").val(0);
		}
	}).blur(function(){
		var price = $("#original_price").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(price)) {
			$("#original_price").val(0);
		}
	});	
	
	$("#price").keypress(function(){//单价
		var price = $("#price").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(price)) {
			$("#price").val(0);
		}
	}).keyup(function(){
		var price = $("#price").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(price)) {
			$("#price").val(0);
		}
	}).blur(function(){
		var price = $("#price").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(price)) {
			$("#price").val(0);
		}
	});	

	$(".price_type").click(function(){
		if($(this).val() == 0){
			$("#t1").hide();
			$("#t2").hide();
			$("#type1").val(0);
			$("#dept_id").removeAttr("dataType").removeAttr("msg").val("");
			$("#province").removeAttr("dataType").removeAttr("msg").val("");
			$("#city").removeAttr("dataType").removeAttr("msg").val("");
		}else if($(this).val() == 2){
			$("#t1").show();
			$("#t2").hide();
			$("#type1").val(1);
			$("#dept_id").attr("datatype", "Require").attr("msg", "请选择分公司！");
			$("#province").removeAttr("dataType").removeAttr("msg").val("");
			$("#city").removeAttr("dataType").removeAttr("msg").val("");
		}else if($(this).val() == 3){
			$("#t1").hide();
			$("#t2").show();
			$("#type1").val(2);
			$("#province").attr({"subElement": "city", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.province}", "datatype": "Require", "msg": "请选择省名称！"});
			$("#city"    ).attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${af.map.city}", "datatype": "Require", "msg": "请选择市名称！"});
			$("#country" ).attr({"subElement": "town", "defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${af.map.country}"});
			$("#town"    ).attr({"defaultText": "-请选择乡镇-", "defaultValue": "", "selectedValue": "${af.map.town}"});

			<c:if test="${is_admin eq 1}">
			$("#province").cs_ext("${ctx}/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false, "p_index_join", "${af.map.p_index_join}");
			</c:if>
			<c:if test="${is_fgs eq 1}">
			$("#province").cs_ext("${ctx}/CsAjax.do?method=getBaseProvinceFourListByUser", "p_index", "0", false, "p_index_join", "${af.map.p_index_join}");
			</c:if>
			$("#province").change();
			$("#dept_id").removeAttr("dataType").removeAttr("msg").val("");
		}		
	});

	var time1 = "${af.map.label_of_cate}";
	if(time1 == 5){
		$("#start_time").attr("datatype", "Require").attr("msg", "请选择开始时间！");
		$("#end_time").attr("datatype", "Require").attr("msg", "请选择结束时间！");
	}	

	$("#remarks").textbox({
		maxLength: 150,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_tip").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_tip").slideUp("normal");
	});

	
	$("#btn_submit").click(function(){
		
		var isSubmit = Validator.Validate(this.form, 3);
		if (isSubmit) {
			$(":button").attr("disabled", "true");
			$(":reset").attr("disabled", "true");
			this.form.submit();
		}
	});
});


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

//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
