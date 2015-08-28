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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script>
</head>
<body>
<div class="oarcont">
  <div class="rtabcont2" style="height: 480px;">
    <html-el:form action="/spgl/EcGoodsPrice" method="post">
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save2" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <html-el:hidden property="is_fgs_id" styleId="is_fgs_id"  value="${af.map.is_fgs_id}"/>
      <html-el:hidden property="type1" styleId="type1" value="0"/>
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
          <tr>
      	  	<td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>价格类型：</td>
            <td width="88%" align="left">
            	<!-- <c:if test="${is_admin eq 1}"></c:if> -->
            	<html-el:radio property="price_type" styleClass="price_type"  value="0"  >全国价格</html-el:radio>
            	<html-el:radio property="price_type" styleClass="price_type"  value="2" >分公司价格</html-el:radio>
            	<html-el:radio property="price_type" styleClass="price_type"  value="3" >市县价格</html-el:radio>
            	<html-el:radio property="price_type" styleClass="price_type"  value="4" >客户价格</html-el:radio>
            </td>
      	  </tr>	
      	  <!-- <c:set var="readyOnly" value="${is_admin eq 1 ? 'none':''}" /> -->
      	   <tr id="t4" >
            <td width="12%" nowrap="nowrap" class="title_item" align="right">分公司：</td>
            <td width="88%" align="left"><html-el:select property="dept_id_2" styleId="dept_id_2">
                <html-el:option value="">请选择...</html-el:option>
                <c:forEach var="cur" items="${sybDeptInfoList}">
                  <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
                </c:forEach>
              </html-el:select>
            </td>
          </tr>
      	  
           <tr id="t1" style="display: none">
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
               
              <!-- <span style="color: red">&nbsp;&nbsp;&nbsp;请至少选择到市级区域</span>--> </td> 
          </tr>
          <tr id="t3" style="display: none">
	          <td align="right" nowrap="nowrap" class="title_item"><font color="red">* </font>选择R3客户：</td>
	          <td><html-el:text readonly="true" property="r3_code" styleId="r3_code" style="width:150px;" size="30" maxlength="30" />
	          	  <html-el:hidden property="c_id" styleId="c_id" value="" /> 
	          </td>
      	 </tr>
          
          
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>产品名称：</td>
          <td width="88%" align="left">${af.map.pd_name}</td>
        </tr>
          <html-el:hidden property="goods_id" styleId="goods_id"  value="${af.map.goods_id}" />
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font> <c:if test="${is_fgs eq 1}">市场终端价</c:if><c:if test="${is_fgs ne 1}">原价格</c:if>：</td>
          <td width="88%" align="left"><html-el:text property="original_price" styleId="original_price" size="15" maxlength="10"/>
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font> <c:if test="${is_fgs eq 1}">裸价</c:if><c:if test="${is_fgs ne 1}">销售价格</c:if>：</td>
          <td width="88%" align="left"><html-el:text property="price" styleId="price" size="15" maxlength="10"/>
          </td>
        </tr>
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">价格2：</td>
          <td width="88%" align="left"><html-el:text property="price2" styleId="price2" size="15" maxlength="10"/>
          </td>
        </tr>
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">价格3：</td>
          <td width="88%" align="left"><html-el:text property="price3" styleId="price3" size="15" maxlength="10"/>
          </td>
        </tr>
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">价格4：</td>
          <td width="88%" align="left"><html-el:text property="price4" styleId="price4" size="15" maxlength="10"/>
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>所属系统：</td>
          <td width="88%" align="left">
          <html-el:select property="own_sys" styleId="own_sys" >
         		<html-el:option value="">-请选择-</html-el:option>
         		<c:if test="${is_admin eq 1}">
         		 <html-el:option value="1">工卡</html-el:option>
         	    </c:if>
         	    <c:if test="${is_fgs eq 1 and empty af.map.epp_fgs}">
         		 <html-el:option value="2">触网</html-el:option>
         		 </c:if>
         		  <c:if test="${is_fgs eq 1 and not empty af.map.epp_fgs}"> 
         		 <html-el:option value="1">工卡</html-el:option>
         		 </c:if>
         </html-el:select>
          </td>
        </tr>
        <c:if test="${af.map.label_of_cate eq 5}">
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>开始时间：</td>
          <td width="88%" align="left"><fmt:formatDate value="${af.map.start_time}" pattern="yyyy-MM-dd HH:mm:ss" var="_start_time" />
             <html-el:text styleId="start_time" property="start_time" size="20" maxlength="15" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="cursor:pointer;text-align:center;" value="${_start_time}" />
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>结束时间：</td>
          <td width="88%" align="left"><fmt:formatDate value="${af.map.end_time}" pattern="yyyy-MM-dd HH:mm:ss" var="_end_time" />
          	<html-el:text styleId="start_time" property="end_time" size="20" maxlength="15" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="cursor:pointer;text-align:center;" value="${_end_time}" />
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
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="location.href = '${ctx}/manager/spgl/EcGoodsPrice.do?method=list2&mod_id=905101&goods_id=${af.map.goods_id}&is_fgs_id=${af.map.is_fgs_id}' " /></td>
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
	$("#price").attr("datatype", "Require").attr("msg", "销售价格不能为空！");
	$("#own_sys").attr("datatype", "Require").attr("msg", "请选择所属系统！");

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
	
	$("#price2").keypress(function(){//单价
		var price = $("#price2").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(price)) {
			$("#price2").val(0);
		}
	}).keyup(function(){
		var price = $("#price2").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(price)) {
			$("#price2").val(0);
		}
	}).blur(function(){
		var price = $("#price2").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(price)) {
			$("#price2").val(0);
		}
	});	
	
	$("#price3").keypress(function(){//单价
		var price = $("#price3").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(price)) {
			$("#price3").val(0);
		}
	}).keyup(function(){
		var price = $("#price3").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(price)) {
			$("#price3").val(0);
		}
	}).blur(function(){
		var price = $("#price3").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(price)) {
			$("#price3").val(0);
		}
	});	
	
	$("#price4").keypress(function(){//单价
		var price = $("#price4").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(price)) {
			$("#price4").val(0);
		}
	}).keyup(function(){
		var price = $("#price4").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(price)) {
			$("#price4").val(0);
		}
	}).blur(function(){
		var price = $("#price4").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(price)) {
			$("#price4").val(0);
		}
	});	


	//<c:if test="${is_fgs eq 1}">
		//$("#dept_id").attr("datatype", "Require").attr("msg", "请选择分公司！");
		//$("#province").removeAttr("dataType").removeAttr("msg").val("");
		//$("#city").removeAttr("dataType").removeAttr("msg").val("");
   // </c:if>
	

	$(".price_type").click(function(){
		if($(this).val() == 0){
			$("#t1").hide();
			$("#t2").hide();
			$("#t3").hide();
			$("#t4").show();
			$("#type1").val(0);
			<c:if test="${is_fgs eq 1}">
			$("#dept_id_2").attr("datatype", "Require").attr("msg", "请选择分公司！");
			</c:if>
			$("#dept_id").removeAttr("dataType").removeAttr("msg").val("");
			$("#province").removeAttr("dataType").removeAttr("msg").val("");
			$("#city").removeAttr("dataType").removeAttr("msg").val("");
			$("#c_id").removeAttr("dataType").removeAttr("msg").val("");
		}else if($(this).val() == 2){
			$("#t1").show();
			$("#t2").hide();
			$("#t3").hide();
			$("#t4").hide();
			$("#type1").val(1);
			$("#dept_id").attr("datatype", "Require").attr("msg", "请选择分公司！");
			$("#province").removeAttr("dataType").removeAttr("msg").val("");
			$("#city").removeAttr("dataType").removeAttr("msg").val("");
			$("#c_id").removeAttr("dataType").removeAttr("msg").val("");
			$("#dept_id_2").removeAttr("dataType").removeAttr("msg").val("");
		}else if($(this).val() == 3){
			$("#t1").hide();
			$("#t2").show();
			$("#t3").hide();
			$("#t4").hide();
			$("#type1").val(2);
			$("#province").attr({"subElement": "city", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.province}", "datatype": "Require", "msg": "请选择省名称！"});
			$("#city"    ).attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${af.map.city}", "datatype": "Require", "msg": "请选择市名称！"});
			$("#country" ).attr({"subElement": "town", "defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${af.map.country}"});

			<c:if test="${is_admin eq 1}">
			$("#province").cs_ext("${ctx}/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false, "p_index_join", "${af.map.p_index_join}");
			</c:if>
			<c:if test="${is_fgs eq 1}">
			$("#province").cs_ext("${ctx}/CsAjax.do?method=getBaseProvinceFourListByUser", "p_index", "0", false, "p_index_join", "${af.map.p_index_join}");
			</c:if>
			$("#province").change();
			$("#dept_id").removeAttr("dataType").removeAttr("msg").val("");
			$("#dept_id_2").removeAttr("dataType").removeAttr("msg").val("");
			$("#c_id").removeAttr("dataType").removeAttr("msg").val("");
		}else if($(this).val() == 4){	
			$("#t1").show();
			$("#t2").hide();
			$("#t3").show();
			$("#t4").hide();
			$("#type1").val(3);
			$("#dept_id").attr("datatype", "Require").attr("msg", "请选择分公司！");
			$("#province").removeAttr("dataType").removeAttr("msg").val("");
			$("#city").removeAttr("dataType").removeAttr("msg").val("");
			$("#c_id").attr("datatype", "Require").attr("msg", "请选择R3客户！");
			$("#dept_id_2").removeAttr("dataType").removeAttr("msg").val("");
		}		
	});

	var time1 = "${af.map.label_of_cate}";
	if(time1 == 5){
		$("#start_time").attr("datatype", "Require").attr("msg", "请选择开始时间！");
		$("#end_time").attr("datatype", "Require").attr("msg", "请选择结束时间！");
	}	

	$("#remarks").textbox({
		maxLength: 100,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_tip").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_tip").slideUp("normal");
	});


	// 选择客户
	$("#r3_code").click(function(){
		var fgs_id = '${af.map.fgs_id}';
		if ($.trim(fgs_id).length == 0) {
			alert("对不起！你不是分公司管理员");
			return;
		}
		var returnValue = window.showModalDialog("${ctx}/manager/admin/KonkaR3Store.do?method=listCustomer&is_xx=0&fgs_id=" + fgs_id +"&ts=" + Math.random(), window, "dialogWidth:800px;status:no;dialogHeight:600px");
		if (!returnValue) returnValue = window.returnValue;
		$("#r3_code").val(returnValue.cust_name);
		$("#c_id").val(returnValue.cust_id);
	});

	
	
	$("#btn_submit").click(function(){
		
		var p1 = $("#original_price").val();
		var p2 = $("#price").val();
		if(parseFloat(p1)<parseFloat(p2)){
			alert("销售价格不能高于原价！");
			return;
		}

		<c:if test="${is_fgs eq 1}">
		var type1 = $("#type1").val();
		if(type1 == 0){
			var dept_id = $("#dept_id_2").val(); 
			if(""==dept_id || null==dept_id){
				alert("请选择分公司");
				return;
			}

		}
		</c:if>
		
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
<jsp:include page="/__analytics.jsp" />
</body>
</html>
