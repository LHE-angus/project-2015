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
    <html-el:form action="/spgl/EcRule" method="post">
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <c:set var="readOnly"  value="${empty af.map.id?false:true}" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>规则标题：</td>
          <td width="88%" align="left" colspan="5"><html-el:text property="rule_title" styleId="rule_title" size="25" maxlength="15"/>
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>规则信息：</td>
          <td width="88%" align="left" colspan="5"><html-el:text property="rule_msg" styleId="rule_msg" size="30" maxlength="30"/></td>
        </tr>
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>规则类型：</td>
          <td width="88%" align="left" colspan="5">
          	<html-el:select property="rule_type" styleId="rule_type" >
          				<html-el:option value="">-请选择-</html-el:option>
          				<html-el:option value="1">限购数量</html-el:option>
          				<html-el:option value="2">限购地区</html-el:option>
          				<html-el:option value="3">优惠费用</html-el:option>
          				<html-el:option value="4">增加费用</html-el:option>
          				<html-el:option value="9">组合套餐</html-el:option>
          	</html-el:select>
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red"> </font>规则费用：</td>
          <td align="left"><html-el:text property="rule_price" styleId="rule_price" size="8" maxlength="8"/></td>
       	  <td width="12%" nowrap="nowrap" class="title_item" align="right">是否启用规则费用：</td>
          <td align="left" colspan="3">
            	<html-el:radio property="is_price" styleClass="is_price"  value="0" >不启用</html-el:radio>
            	<html-el:radio property="is_price" styleClass="is_price"  value="1" >启用</html-el:radio>
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">最小购买数量：</td>
          <td align="left"><html-el:text property="rule_num_min" styleId="rule_num_min" size="4" maxlength="4"/></td>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">最大购买数量：</td>
          <td align="left"><html-el:text property="rule_num_max" styleId="rule_num_max" size="4" maxlength="4"/></td>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">是否启用限制购买数量：</td>
          <td align="left">
            	<html-el:radio property="is_num" styleClass="is_num"  value="0" >不启用</html-el:radio>
            	<html-el:radio property="is_num" styleClass="is_num"  value="1" >启用</html-el:radio>
           </td>
        </tr>
        <tr>
            <td height="28" width="15%" nowrap="nowrap" class="title_item">限制区域：</td>
            <td width="88%" align="left" colspan="5">
            	 <select name="shop_province" id="shop_province" class="bd"><option value="">-请选择省/直辖市/自治区-</option></select>
	            <select name="shop_city" id="shop_city" class="bd"><option value="">-请选择市-</option></select>
	            <select name="shop_country" id="shop_country" class="bd"><option value="">-请选择县-</option></select>
	            <!-- <select name="shop_town" id="shop_town" class="bd"><option value="">-请选择乡/镇-</option></select> -->
	        <input class="but8" type="button" name="Submit4" value="点击查询 " id="btn_shop" />
            <table class="areause1" >
              <tbody>
                <tr>
                  <td><span id="area_name_0">供选择区域列表</span><br />
                    <html-el:select property="select1" multiple="true" style="width:260px;height:200px;" styleId="select1" onchange="moveSelected(this.form.select1, this.form.select2);"> </html-el:select></td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  <td><span id="area_name_1">已选择区域列表</span><br />
                    <html-el:select property="select2" multiple="true" style="width:260px;height:200px;" styleId="select2" onchange="moveSelected(this.form.select2, this.form.select1);">
                      <c:if test="${not empty list1}">
                        <html-el:optionsCollection label="full_name" value="p_index" name="list1" />
                      </c:if>
                    </html-el:select></td>
                </tr>
              </tbody>
            </table></td>
          </tr>
          <tr>
            <td height="28" width="15%" nowrap="nowrap" class="title_item">允许区域：</td>
            <td width="88%" align="left" colspan="5">
            	 <select name="shop_province1" id="shop_province1" class="bd"><option value="">-请选择省/直辖市/自治区-</option></select>
	            <select name="shop_city1" id="shop_city1" class="bd"><option value="">-请选择市-</option></select>
	            <select name="shop_country1" id="shop_country1" class="bd"><option value="">-请选择县-</option></select>
	            <!-- <select name="shop_town" id="shop_town" class="bd"><option value="">-请选择乡/镇-</option></select> -->
	        <input class="but8" type="button" name="Submit4" value="点击查询 " id="btn_shop2" />
            <table class="areause1" >
              <tbody>
                <tr>
                  <td><span id="area_name_0">供选择区域列表</span><br />
                    <html-el:select property="select3" multiple="true" style="width:260px;height:200px;" styleId="select3" onchange="moveSelected(this.form.select3, this.form.select4);"> </html-el:select></td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  <td><span id="area_name_1">已选择区域列表</span><br />
                    <html-el:select property="select4" multiple="true" style="width:260px;height:200px;" styleId="select4" onchange="moveSelected(this.form.select4, this.form.select3);">
                      <c:if test="${not empty list2}">
                        <html-el:optionsCollection label="full_name" value="p_index" name="list2" />
                      </c:if>
                    </html-el:select></td>
                </tr>
              </tbody>
            </table></td>
          </tr>
      	   <tr>
      	  	<td width="12%" nowrap="nowrap" class="title_item" align="right">是否启用限制区域：</td>
            <td width="88%" align="left" colspan="5">
            	<html-el:radio property="is_area_limit" styleClass="is_area_limit"  value="0" >不启用</html-el:radio>
            	<html-el:radio property="is_area_limit" styleClass="is_area_limit"  value="1" >启用</html-el:radio>
            </td>
      	  </tr>
      	   <tr>
      	  	<td width="12%" nowrap="nowrap" class="title_item" align="right">是否启用允许区域：</td>
            <td width="88%" align="left" colspan="5">
            	<html-el:radio property="is_area_allow" styleClass="is_area_allow"  value="0" >不启用</html-el:radio>
            	<html-el:radio property="is_area_allow" styleClass="is_area_allow"  value="1" >启用</html-el:radio>
            </td>
      	  </tr>
           <tr>
      	  	<td width="12%" nowrap="nowrap" class="title_item" align="right">有效开始日期：</td>
            <td width="88%" align="left" colspan="5">
             <fmt:formatDate var="_rule_start_date" value="${af.map.rule_start_date}" pattern="yyyy-MM-dd" />
            <html-el:text property="rule_start_date" value="${_rule_start_date}" styleId="rule_start_date" size="10" maxlength="10" readonly="true"  style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />	
            </td>
      	  </tr>
      	   <tr>
      	  	<td width="12%" nowrap="nowrap" class="title_item" align="right">有效截止日期：</td>
            <td width="88%" align="left" colspan="5">
             <fmt:formatDate var="_rule_end_date" value="${af.map.rule_end_date}" pattern="yyyy-MM-dd" />
            <html-el:text property="rule_end_date" value="${_rule_end_date}" styleId="rule_end_date" size="10" maxlength="10" readonly="true"  style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />	
            </td>
      	  </tr>
      	  <tr>
          <td class="title_item" nowrap="nowrap" align="right">备注：</td>
          <td width="88%" colspan="5">
            <html-el:textarea property="memo" styleId="memo" cols="5" style="width:600px;height:100px;resize: none;" />  
          	<div id="info_tip" style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div></td>
         </tr>
          <tr >
            <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>分公司：</td>
            <td width="88%" align="left" colspan="5"><html-el:select property="dept_id" styleId="dept_id" disabled="${readonly}">
                <html-el:option value="">请选择...</html-el:option>
                <c:if test="${is_admin eq 1}">
                <html-el:option value="0">多媒体事业总部</html-el:option>
                </c:if>
                <c:forEach var="cur" items="${sybDeptInfoList}">
                  <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
                </c:forEach>
              </html-el:select>
            </td>
          </tr>
      	   <tr >
            <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>所属系统：</td>
            <td width="88%" align="left" colspan="5"><html-el:select property="own_sys" styleId="own_sys" >
                <html-el:option value="">请选择...</html-el:option>
                <c:if test="${is_admin eq 1}">
                <html-el:option value="1">工卡系统</html-el:option>
                </c:if> 
                <html-el:option value="2">触网系统</html-el:option>
              </html-el:select>
            </td>
          </tr>
      	  <tr>
      	  	<td width="12%" nowrap="nowrap" class="title_item" align="right">状态：</td>
            <td width="88%" align="left" colspan="5">
            	<html-el:radio property="info_state" styleClass="info_state"  value="0" >不启用</html-el:radio>
            	<html-el:radio property="info_state" styleClass="info_state"  value="1" >启用</html-el:radio>
            </td>
      	  </tr>
      	  
          
        <tr>
          <td>&nbsp;</td>
          <td colspan="5"><html-el:button property="" value="提交" styleClass="but4" styleId="btn_submit" />
           &nbsp;&nbsp;<html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();return false;" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	// 地区信息
	$("#shop_province").attr({"subElement": "shop_city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${af.map.shop_province}"});
	$("#shop_city"    ).attr({"subElement": "shop_country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${af.map.shop_city}"});
	$("#shop_country" ).attr({"subElement": "shop_town","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${af.map.shop_country}"});
	//$("#shop_town"    ).attr({"defaultText": "-请选择乡/镇-", "defaultValue": "", "selectedValue": "${af.map.shop_town}"});

	$("#shop_province").cs_ext("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false,"p_index_join","${af.map.p_index_join}");
	$("#shop_province").change();

	$("#shop_province1").attr({"subElement": "shop_city1", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${af.map.shop_province1}"});
	$("#shop_city1"    ).attr({"subElement": "shop_country1", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${af.map.shop_city1}"});
	$("#shop_country1" ).attr({"subElement": "shop_town1","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${af.map.shop_country1}"});
	//$("#shop_town"    ).attr({"defaultText": "-请选择乡/镇-", "defaultValue": "", "selectedValue": "${af.map.shop_town}"});

	$("#shop_province1").cs_ext("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false,"p_index_join","${af.map.p_index_join}");
	$("#shop_province1").change();

	$("#rule_title").attr("datatype", "Require").attr("msg", "请填写规则标题");
	$("#rule_msg").attr("datatype", "Require").attr("msg", "请填写规则信息");
	$("#dept_id").attr("datatype", "Require").attr("msg", "请选择分公司");
	$("#own_sys").attr("datatype", "Require").attr("msg", "请选择所属系统");
	$("#rule_type").attr("datatype", "Require").attr("msg", "请选择规则类型");
	$("#rule_price" ).attr("focus",setOnlyNum);
	$("#rule_num_min" ).focus(function(){setOnlyInt(this);});
	$("#rule_num_max" ).focus(function(){setOnlyInt(this);});

	
	$("#memo").textbox({
		maxLength: 300,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_tip").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_tip").slideUp("normal");
	});


	$("#btn_shop").click(function(){
		  $.ajax({
				type: "POST",
				url: "EcRule.do",
				data: "method=" + "ajaxSelectPindexList" + "&shop_province=" + $("#shop_province").val()+"&shop_city=" + $("#shop_city").val() + "&shop_country=" + $("#shop_country").val() + "&t=" + (new Date()).getTime(),
				dataType: "json",
				error: function(request, settings) { alert('查询错误'); },
				success: function(data) {
					//console.info(data);
					if(data.status == "0"){
						alert("查询结果过大，请您缩小查询范围！");
					} else if (data.status == "-1"){
						alert("查询结果为空，请重新选择查询条件！");
					} else {
						var values =  data.data;
						$("#select1").empty();
						for(var i = 0; i < values.length; i++) {
							var opt = new Option(values[i].full_name, values[i].p_index); 
							$("#select1").get(0).options.add(opt);
						}
					}
			    }
		  });
		});

	$("#btn_shop2").click(function(){
		  $.ajax({
				type: "POST",
				url: "EcRule.do",
				data: "method=" + "ajaxSelectPindexList2" + "&shop_province1=" + $("#shop_province1").val()+"&shop_city1=" + $("#shop_city1").val() + "&shop_country1=" + $("#shop_country1").val() + "&t=" + (new Date()).getTime(),
				dataType: "json",
				error: function(request, settings) { alert('查询错误'); },
				success: function(data) {
					//console.info(data);
					if(data.status == "0"){
						alert("查询结果过大，请您缩小查询范围！");
					} else if (data.status == "-1"){
						alert("查询结果为空，请重新选择查询条件！");
					} else {
						var values =  data.data;
						$("#select3").empty();
						for(var i = 0; i < values.length; i++) {
							var opt = new Option(values[i].full_name, values[i].p_index); 
							$("#select3").get(0).options.add(opt);
						}
					}
			    }
		  });
		});
	

	
	$("#btn_submit").click(function(){
		var isSubmit = Validator.Validate(this.form, 3);
		if (isSubmit) {

			var opts=document.getElementById("select2");
			for(var i=0;i<opts.length;i++){
				opts[i].selected=true;
			}

			var opts4=document.getElementById("select4");
			for(var i=0;i<opts4.length;i++){
				opts4[i].selected=true;
			}
			
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

function setOnlyNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}
		if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;

}

function moveSelected(sourceSelect, targetSelect, isDelete){
	var cachOptionsArray = new Array();
	var index = 0;
	for (var i = sourceSelect.options.length - 1; i >= 0; i--){
		if (sourceSelect.options[i].selected){
			cachOptionsArray[index] = new Option(sourceSelect.options[i].text, sourceSelect.options[i].value);
			if(isDelete==undefined || isDelete==true){
				sourceSelect.options[i] = null;
			}
			index++;
		}
	}
	var exist = false;
	for (var i = cachOptionsArray.length - 1; i >= 0; i--){
		exist = false;
		for (var j = 0; j < targetSelect.options.length; j++){
			if (targetSelect.options[j].value.toString() == cachOptionsArray[i].value.toString()){
				exist = true; 
				break;
			}
		}
		if (!exist){
			targetSelect.options[targetSelect.options.length] = cachOptionsArray[i];
		}
	}
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
