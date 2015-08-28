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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
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
  <html-el:form action="/admin/BasePropCategory">
    <html-el:hidden property="category_id" value="${af.map.category_id}" />
    <html-el:hidden property="method" value="save" />
    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
    <html-el:hidden property="queryString" />
    <c:set var="readonly" value="false" />
    <c:if test="${not empty af.map.category_id}">
      <c:set var="readonly" value="true" />
      <html-el:hidden property="pd_type_big" value="${af.map.pd_type_big}"/>
      <html-el:hidden property="pd_type" value="${af.map.pd_type}"/>
    </c:if>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item">产品类别：</td>
          <td><html-el:select property="cls_id" styleId="cls_id" style="width:200px;">
              <c:forEach var="cur" items="${basePdClazzList}">
                <html-el:option value="${cur.cls_id}" styleId="${cur.full_name}_${cur.is_leaf}" >${fn:escapeXml(cur.tree_name)}</html-el:option>
              </c:forEach>
            </html-el:select>
            <span style="color:red" >*</span>
            <span id="cls_full_name" class="note" style="padding-left:4px;"></span> </td>
        </tr>
        <tr id="cate_name_tr" style="display:none;">
          <td nowrap="nowrap" height="28" class="title_item">已存在的属性类别</td>
          <td id="cate_name_td"></td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item">属性类别名称：</td>
          <td><html-el:text property="category_name" styleId="category_name" style="width:250px;" size="26" maxlength="10"/>
          <span class="note">属性类别名称最长为20个字，超出部分不被保存</span>
          <span id="category_name_msg" style="color:red;display:none;margin-left:2px;">* 属性类别名称已存在，请重新输入！</span> </td>
        </tr>
        <tr>
          <td height="28" class="title_item">备注：</td>
          <td><html-el:text property="memo" styleId="memo" style="width:250px;" size="50" maxlength="40"/></td>
        </tr>
        <tr>
          <td nowrap="nowrap"  height="28" class="title_item">排序值：</td>
          <td><html-el:text property="order_value" styleId="order_value" maxlength="8" size="8"/>
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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	var G_cate_names_had = "";
	
	$("#cls_id" ).attr("dataType" , "Require").attr("msg" , "请选择产品类别！");
	$("#category_name" ).attr("dataType" , "Require").attr("msg" , "请填写产品类别名称！");
	$("#order_value" ).attr("focus",setOnlyNum);
	$("#memo").focus(setLength).attr("len", 100);
	
	var full_name = $("#cls_id option:selected").attr("id").split('_')[0];
	$("#cls_full_name").html(full_name.replace(new RegExp(',', 'g'), ' &gt;&gt; '));
	
	$("#cls_id").change(function(){
		var cls_id = $("#cls_id option:selected").val();
		var full_name = $("#cls_id option:selected").attr("id").split('_')[0];
		$("#cls_full_name").html(full_name.replace(new RegExp(',', 'g'), ' &gt;&gt; '));
		$.ajax({
			type: "POST",
			url: "BasePropCategory.do",
			data: "method=getHadCateList&cls_id=" + cls_id,
			dataType: "json",
			error: function(request, settings) {tag = false;},
			success: function(oper) {
				if("" != oper.result){
					var html = "<table width='100%' border='0' cellpadding='0' cellspacing='0' >";
					var strs = oper.result.split(";");
					var cate_names_had = "";
					for(i = 0; i < strs.length - 1; i++){
						var temps = strs[i].split(":");
						cate_names_had = cate_names_had + temps[1] + ",";
						html = html + "<tr><td style='border:none;color:#0066ff;font-weight:bold;' width='20%' valign='top'>" + temps[0] + "</td><td style='border:none;color:#0066ff;font-weight:bold;'>"  + temps[1].replace(new RegExp(',', 'g'), '，') + "</td></tr>";
					}
					html = html + "</table>";
					G_cate_names_had = cate_names_had;
					$("#cate_name_td").html(html);
					$("#cate_name_tr").show();
				}else {
					$("#cate_name_tr").hide();
					return ;
				}
			}
		});
		
	});
	
	 $("#send").click(function(){
		  var is_exist = false;
		  var category_name = $("#category_name").val().replace(/(^\s*)|(\s*$)/g, "");
		  var category_name_string = G_cate_names_had;
		  var category_name_list;
		  if(category_name_string != ""){
			  category_name_list = category_name_string.split(",");
			  for(var i=0; i<category_name_list.length - 1; i++){
				  var temp = category_name_list[i].replace(/(^\s*)|(\s*$)/g, "");
				  if(category_name == temp){
					  is_exist = true;
				  }
			  }
		  }
			var isSubmit = Validator.Validate(this.form, 2);
			if(is_exist){
				$("#category_name_msg").show();
				return;
			}else{
				$("#category_name_msg").hide();
			}
			if (isSubmit) {
				$(":button").attr("disabled", "true");
				$(":reset").attr("disabled", "true");
				this.form.submit();
			}
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
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}
		if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;
}

function setLength(){
	$(this).keypress(function (){
		if(this.value.length > this.len){
			this.value = this.value.substring(0, this.len);
		}
	}).keyup(function (){
		if(this.value.length > this.len){
			this.value = this.value.substring(0, this.len);
		}
	}).blur(function (){
		if(this.value.length > this.len){
			this.value = this.value.substring(0, this.len);
		}
	});
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
