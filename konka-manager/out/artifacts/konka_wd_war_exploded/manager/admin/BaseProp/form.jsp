<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
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
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
<div class="rtabcont2">
  <html-el:form action="/admin/BaseProp" method="post">
    <html-el:hidden property="prop_id" styleId="prop_id" value="${af.map.prop_id}" />
    <html-el:hidden property="method" value="save" />
    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
    <input type="hidden" name="category_id_set_null"  id="category_id_set_null" />
    <html-el:hidden property="queryString" />
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
      <tr>
         <td nowrap="nowrap" class="title_item">产品类别：</td>
        <td width="85%"><html-el:select property="cls_id"  style="width:250px;" >
            <c:forEach var="cur" items="${basePdClassList}">
              <html-el:option value="${cur.cls_id}" styleId="${cur.full_name}_${cur.is_leaf}" >${fn:escapeXml(cur.tree_name)}</html-el:option>
            </c:forEach>
          </html-el:select>
          <span style="color:red" >*</span>
          <span id="cls_full_name" class="note" style="padding-left:4px;"></span> </td>
      </tr>
      <tr id="showCagegory">
        <td nowrap="nowrap" class="title_item">属性类别：</td>
        <td><html-el:text property="category_name" readonly="true" onclick="openChild(1)" styleId="category_name" style="width:250px;" maxlength="20" value="${af.map.category_name}"/>
          <html-el:hidden property="category_id" styleId="category_id" />
          <a href="#" id="delete_id" style="display:${not empty af.map.category_name ? '' : 'none'}" onclick="deleteCate()">&nbsp;&nbsp;删除&nbsp;&nbsp;</a> </td>
      </tr>
      <tr id="addedPropertyTr" style="display: ${not empty af.map.add_property ? '' : 'none'};">
         <td nowrap="nowrap" class="title_item">已经添加的属性：</td>
        <td id="showAddedProperty" style="color: #0066ff;font-weight: bold;">${af.map.add_property }</td>
      </tr>
      <tr>
         <td nowrap="nowrap" class="title_item">属性名称：</td>
        <td><html-el:text property="prop_name" styleId="prop_name" style="width:250px;" maxlength="20" size="20" />属性名称最大长度为20，超出20个字符则超出部分不被保存
        <span id="prop_name_msg" style="color:red;display:none;margin-left:2px;">* 属性名称已存在，请重新输入！</span></td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item">单位：</td>
        <td><html-el:text property="prop_unit" styleId="" style="width:250px;" maxlength="10"/></td>
      </tr>
      <tr>
         <td nowrap="nowrap" class="title_item">是否必填:</td>
        <td><label for="is_required1" style="width:80px;">
          <html-el:radio property="is_required" styleId="is_required1" value="0">否</html-el:radio>
          </label>
          <label for="is_required2" style="width:80px;">&nbsp;
          <html-el:radio property="is_required" styleId="is_required2" value="1">是</html-el:radio>
          </label><span style="color:red" >*</span>
        </td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item">属性类型：</td>
        <td><label for="prop_type1" style="width:80px;">
          <html-el:radio property="prop_type" styleId="prop_type1" styleClass="prop_type" value="0">输入</html-el:radio>
          </label>
          <label for="prop_type2" style="width:80px;">
          <html-el:radio property="prop_type" styleId="prop_type2" value="1" styleClass="prop_type">单选</html-el:radio>
          </label>
          <label for="prop_type3" style="width:80px;">
          <html-el:radio property="prop_type" styleId="prop_type3" value="2" styleClass="prop_type">多选</html-el:radio>
          </label><span style="color:red" >*</span>
        </td>
      </tr>
      <tr id="prop_val_type_div" style="display:${af.map.prop_type eq 0 ? '' : 'none'};">
         <td nowrap="nowrap" class="title_item">属性值类型：</td>
        <td><label for="prop_val_type1" style="width:80px;">
          <html-el:radio property="prop_val_type" styleId="prop_val_type1" styleClass="prop_val_type" value="0" style="width:50px;">数字</html-el:radio>
          </label>
          <label for="prop_val_type2" style="width:80px;">
          <html-el:radio property="prop_val_type" styleId="prop_val_type2" value="1" styleClass="prop_val_type" style="width:50px;">文本</html-el:radio>
          </label>
          <label for="prop_val_type3" style="width:80px;">
          <html-el:radio property="prop_val_type" styleId="prop_val_type3" value="2" styleClass="prop_val_type" style="width:50px;">日期</html-el:radio>
          </label>
        </td>
      </tr>
      <tr id="prop_val_div_min" class="val" style="display:${(af.map.prop_val_type eq 0 && af.map.prop_type eq 0) ? '' : 'none'};">
         <td nowrap="nowrap" class="title_item">属性值范围：</td>
        <td><html-el:text property="prop_val_min" maxlength="8" size="10" styleId="prop_val_min" />
          ～
          <html-el:text property="prop_val_max" maxlength="8" size="10" styleId="prop_val_max" /></td>
      </tr>
      <tr id="pmore" style="display: ${af.map.prop_type gt 0 ? '' : 'none'};">
         <td nowrap="nowrap" class="title_item">属性值：</td>
        <td style="padding: 0"><table width="90%" border="0" cellpadding="0" cellspacing="1" class="datagrid">
            <tr>
              <td align="left"><html-el:text property="more_values" styleClass="add_row" styleId="more_values1" maxlength="30" style="width: 70%" /></td>
              <td width="20%" align="center" nowrap="nowrap" id="add_MoreValue"><img src="${ctx}/images/+.gif" alt="" style="vertical-align:middle;" />新增一行</td>
            </tr>
            <tr id="hidden_MoreValue" style="display:none">
              <td align="left"><html-el:text property="more_values" styleClass="add_row" maxlength="30" style="width: 70%" /></td>
              <td align="center"><img src="${ctx}/images/x.gif" alt="" style="vertical-align:middle;" />删除该行</td>
            </tr>
            <tbody id="show_MoreValue">
              <c:forEach var="cur" items="${basePropValItemList}">
                <tr>
                  <td align="left"><html-el:text property="more_values" styleClass="add_row" value="${cur.prop_item_name}" style="width:70%" maxlength="30" /></td>
                  <td align="center"><img src="${ctx}/images/x.gif" style="vertical-align:middle;" />删除该行</td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
          <div class="note">注：键入回车可自动新增一行</div></td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item">备注：</td>
        <td><html-el:text property="memo" style="width:320px;height:20px" maxlength="30" /></td>
      </tr>
      <tr>
       <td nowrap="nowrap" class="title_item">排序值：</td>
          <td><html-el:text property="order_value" styleId="order_value" maxlength="8" size="8" styleClass="webinput" />
         &nbsp;&nbsp;取值范围：0 — 99999999， 值越大，显示越靠前。</td>
      </tr>
       <tr>
        <td>&nbsp;</td>
        <td><label>
            <input class="but4" type="button" name="Submit4" id="send" value="提交" />
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
          </label></td>
      </tr>
    </table>
  </html-el:form>
 </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	var G_prop_names_had = "";

	$("#cls_id" ).attr("dataType" , "Require").attr("msg" , "请选择产品小类型号！");
    //$("#category_id" ).attr("dataType" , "Require").attr("msg" , "请选择属性类别！");
	$("#prop_name" ).attr("dataType" , "LimitB").attr("msg" , "请填写属性名称,且不能超过20个字符！").attr("max" , "20");
	$("input[type='radio'][name='is_required']").eq(1).attr({'dataType':'Group' , 'msg':'请选择是否必填！'});
	$("input[type='radio'][name='prop_type']").eq(2).attr({'dataType':'Group' , 'msg':'请选择属性类型！'});
	//$("#order_value" ).attr("dataType" , "Require").attr("msg" , "请填写排序值！");
		
	$("#order_value" ).attr("focus",setOnlyNum);
	$("#prop_val_min" ).attr("focus",setOnlyNum);
	$("#prop_val_max" ).attr("focus",setOnlyNum);
	
	$("#send").click(function(){
		var is_exist = false;
		var addProperty = G_prop_names_had;
		if($.trim(addProperty).length > 0) {
			var prop_name = $("#prop_name").val().replace(/(^\s*)|(\s*$)/g, "");
			var property = addProperty.split(",");
			for(var i = 0; i < property.length - 1; i ++) {
				var temp = property[i].replace(/(^\s*)|(\s*$)/g, "");
				if(prop_name == temp) {
					is_exist = true;
				}
			}
		}
		var isSubmit = Validator.Validate(this.form, 2);
		if(is_exist){
			$("#prop_name_msg").show();
			return;
		}else{
			$("#prop_name_msg").hide();
		}
		if (isSubmit) {
			$(":button").attr("disabled", "true");
			this.form.submit();
		}

	});
	//var full_name = $("#cls_id option:selected").attr("id").split("_")[0];
	//$("#cls_full_name").html(full_name.replace(new RegExp(',', 'g'), ' &gt;&gt; '));
	
	$("#cls_id").change(function(){
		var is_leaf = $("#cls_id option:selected").attr("id").split('_')[1];
		var full_name = $("#cls_id option:selected").attr("id").split('_')[0];
		if (0 == is_leaf) {
			$("#md_name").attr("disabled",true);
		}else{
			$("#md_name").removeAttr("disabled");
		}		
		$("#cls_full_name").html(full_name.replace(new RegExp(',', 'g'), ' &gt;&gt; '));
	});
	
	$(".prop_type").click(function(){
		var prop_type = $(this).val();
		switch(prop_type){
		case '0' :
			$("#prop_val_type_div").show();
			$("#pmore").hide();
			$("input[name='more_values']").removeAttr("datatype").removeAttr("msg");
			break;
		case '1' :
			$("#more_values1").attr("datatype","Require").attr("msg", "请填写属性的值");
			$("#prop_val_type_div").hide();
			$(".val").hide();
			$("#prop_val_min").val('');
			$("#prop_val_max").val('');
			$("#pmore").show();
			break;
		case '2' :
			$("#more_values1").attr("datatype","Require").attr("msg", "请填写属性的值");
			$("#prop_val_type_div").hide();
			$(".val").hide();
			$("#prop_val_min").val('');
			$("#prop_val_max").val('');
			$("#pmore").show();
			break;
		}
	});
	
	$(".prop_val_type").click(function(){
		var prop_val_type = $(this).val();
		switch(prop_val_type){
		case '0' :
			$(".val").show();
			// $("pswddiv").show();
			break;
		case '1' :
			$(".val").hide();
			$("#prop_val_min").val('');
			$("#prop_val_max").val('');
			break;
		case '2' :
			$(".val").hide();
			$("#prop_val_min").val('');
			$("#prop_val_max").val('');
			break;
		}
	});
	
	$("#cls_id").change(function(){
		if($("#cls_id").val() != '') {
			$("#showCagegory").show();
			$('#category_name').val('');
			$('#category_id').val('');
		}else {
			$('#category_name').val('');
			$('#category_id').val('');
		}
		$.ajax({
			type: "POST",
			url: "BaseProp.do",
			data: "method=getAddedProperty&cls_id=" + $("#cls_id").val(),
			dataType: "json",
			error: function(request, settings) {tag = false;},
			success: function(oper) {
				if ("" != oper.result){
					var html = "<table width='100%' border='0' cellpadding='0' cellspacing='0' >";
					var strs = oper.result.split(";");
					var prop_names_had = "";
					for(i = 0; i < strs.length - 1; i++){
						var temps = strs[i].split(":");
						prop_names_had = prop_names_had + temps[1] + ",";
						html = html + "<tr><td style='border:none;color:#0066ff;font-weight:bold;' width='20%' valign='top'>" + temps[0] + "</td><td style='border:none;color:#0066ff;font-weight:bold;'>"  + temps[1].replace(new RegExp(',', 'g'), '，') + "</td></tr>";
					}
					html = html + "</table>";
					G_prop_names_had = prop_names_had;
					$("#showAddedProperty").html(html);
					$("#addedPropertyTr").show();
					
				} else {
					$("#addedPropertyTr").hide();
				}
			}
	    });
	});

});

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
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;
}

function setLength(){
	$(this).keypress(function (){
		if(this.value.length > this.len){
			this.value = this.value.substring(0, this.len);
		}
		$("#count").text(this.len - this.value.length);
	}).keyup(function (){
		if(this.value.length > this.len){
			this.value = this.value.substring(0, this.len);
		}
		$("#count").text(this.len - this.value.length);
	}).blur(function (){
		if(this.value.length > this.len){
			this.value = this.value.substring(0, this.len);
		}
		$("#count").text(this.len - this.value.length);
	});
}

function openChild(num){
	if(num == 1) {
	    var returnValue = window.showModalDialog("BaseProp.do?method=toWindowFramePage&cls_id=" + $("#cls_id").val() + "&time=" + new Date().getTime(), window, "dialogWidth:655px;status:no;dialogHeight:415px");
	    if(returnValue != null) {
			var value = new Array();
			value = returnValue.split(",");
	        document.forms[0].category_name.value = value[0];
	        document.forms[0].category_id.value = value[1];
	        
	        var temp =  document.forms[0].category_name.value;
	        if($.trim(temp).length > 0){
	        	$("#delete_id").show();
	        }
	    }
	}
}

$(".add_row").keydown(function(){ 
    if (event.keyCode == 13){ 
    	$("#add_MoreValue").click();
    }
});

//新增"多选属性值"行
$("#add_MoreValue").click(function (){
	$("#hidden_MoreValue").clone().appendTo($("#show_MoreValue")).show();
	var lastTR = $("tr:last", "#show_MoreValue");
	$("input[name='more_values']", lastTR).attr("datatype","Require").attr("msg", "请填写属性的值").keydown(function(){
		if (event.keyCode == 13){ 
	    	$("#add_MoreValue").click();
	    }
	}).focus();
	$("td:last", lastTR).click(function (){
		$(this).parent().remove();
	}).css("cursor", "pointer");
	initStyle();
}).css("cursor", "pointer");

//删除"多选属性"行
$("tr", "#show_MoreValue").each(function(){
var lastTR = $("tr:last", "#show_MoreValue");
$("input[name='more_values']", lastTR).attr("datatype","Require").attr("msg", "请填写属性的值");
$("td:last", $(this)).click(function (){
	$(this).parent().remove();
}).css("cursor", "pointer");
});

function initStyle(){
	$("input[type='text']").css({border: "1px solid #ccc"});
	$("input[type='text'][readonly]").css({color:"#999"});
    $("input[type='text']").not("[readonly]").focus(function(){
    	$(this).addClass("row_focus");
    }).blur(function(){
    	$(this).removeClass("row_focus");
    });
}

function deleteCate(){
	$("#category_id_set_null").val("yes");
	$("#category_name").val('');
	$("#category_id").val('');
	//$("#showAddedProperty").text('');
	//$("#addedPropertyTr").hide();
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
