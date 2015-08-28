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
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/GiftInfo">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="gift_id" value="${af.map.gift_id}" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
      	<tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right" width="15%"><span style="color: red;">*</span>赠品名称：</td>
          <td width="85%"><html-el:text property="gift_name" styleId="gift_name" size="25" maxlength="20" /><span id="s_after"></span></td>
        </tr>
        <tr>
           <td nowrap="nowrap" height="28" class="title_item" align="right" width="15%"><span style="color: red;">*</span>赠品类别：</td>
            <td width="85%"><html-el:select property="type_id" styleId="type_id">
           		<c:forEach items="${kList}" var="cur">
           			<html-el:option value="${cur.c_index}">${cur.c_name}</html-el:option>
           		</c:forEach>
           		<html-el:option value="-1">其他</html-el:option>
            </html-el:select>
            &nbsp;<span style="display: none;" id="type_span"><html-el:text property="other_type" styleId="other_type"/></span>
            </td>
        </tr>
        <c:if test="${is_admin eq 1}">
          <tr>
            <td nowrap="nowrap" height="28" class="title_item" align="right" width="15%"><span style="color: red;">*</span>所属分公司：</td>
            <td width="85%"><html-el:select property="dept_id" styleId="dept_id">
            	<html-el:option value="">请选择</html-el:option>
           		<c:forEach items="${deptList}" var="cur">
           			<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
           		</c:forEach>
            </html-el:select>
            </td>
          </tr>
        </c:if>
        <c:if test="${is_admin ne 1}">
          <html-el:hidden property="dept_id" value="${af.map.dept_id}"/>
        </c:if>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right" width="15%">参考单价：</td>
          <td width="85%"><html-el:text property="ref_price" styleId="ref_price" size="25" maxlength="8" onchange="javascript:setOnlyDouble(this);"></html-el:text></td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right">排序值：</td>
          <td><html-el:text property="order_value" styleId="order_value" size="25" maxlength="4" onchange="javascript:setOnlyNum(this);"></html-el:text></td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right">赠品描述：</td>
          <td><html-el:textarea property="gift_desc" styleId="gift_desc" style="width:370px;"></html-el:textarea></td>
        </tr>
        <tr>
          <td>&nbsp;&nbsp;</td>
          <td><input class="but4" type="button" name="Submit4" value="保存" id="add_btn" />
            <input class="but3" type="reset"  value="重填 " />
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back()" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<!-- ****** Main Frame End ****** -->
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#gift_name").attr("dataType", "Require").attr("msg", "请填写赠品名称！");
	$("#type_id").attr("dataType", "Require").attr("msg", "请选择赠品类别！");
	$("#dept_id").attr("dataType", "Require").attr("msg", "请选择分公司！");
	$("#gift_desc").attr("dataType", "Limit").attr("max", "200").attr("msg", "赠品描述不能超过200个文字");

	var is_admin = '${is_admin}';
	if(is_admin == 2){
		$("#dept_id").attr("dataType", "Require").attr("msg", "请选择分公司！");
	}

	$("#type_id").change(function(){
		if(this.value == "-1"){
			$("#type_span").show();
		} else {
			$("#type_span").hide();
			$("#other_type").val("");
		}
	});

	
	$("#add_btn").click(function(){
		if($("#type_id").val()=="-1"){
			$("#other_type").attr("dataType", "Require").attr("msg", "请填写类别名称！");
		}
		
			var isSubmit = Validator.Validate(this.form, 3);
			if (isSubmit) {
				$(":button").attr("disabled", "true");
				$(":reset").attr("disabled", "true");
				this.form.submit();
			}
	 });

	$("#gift_name").change(function(){
		   $("span[id=__ErrorMessagePanel]").remove();
		   var gift_name = '${af.map.gift_name}';
		   if(gift_name != this.value){
			if(this.value.length > 0){
				$("#tip_gift_name").remove();
				$("#add_btn").attr("disabled", "true");
				$.ajax({
					type: "POST",
					url: "GiftInfo.do",
					data: "method=validateGiftName&gift_name=" + this.value,
					dataType: "json",
					error: function(request, settings) {alert("数据加载请求失败！"); },
					success: function(isExist) {
						$("#tip_zmd_sn").remove();
						if(isExist == 11) {
							$("#s_after").after('<span id="tip_gift_name" style="color:#FF0000;"><img src="${ctx}/commons/styles/themes/blue/images/reg_error.gif" />&nbsp;该赠品名称已存在！<\/span>'); 
							$("#add_btn").attr("disabled", "true");
							return;
						} else if (isExist == 0){
							$("#add_btn").removeAttr("disabled");
						} 
					}
				});
				} else {
					$("#tip_gift_name").remove();
				}
		}
	});

	$("#other_type").blur(function(){
		   var other_type = '${af.map.other_type}';
		   if(gift_name != this.value){
			if(this.value.length > 0){
				$.ajax({
					type: "POST",
					url: "GiftInfo.do",
					data: "method=validateOtherTypeName&type_name=" + this.value,
					dataType: "json",
					error: function(request, settings) {alert("数据加载请求失败！"); },
					success: function(isExist) {
						if(isExist == 11) {
							alert("该类别已经存在，请重新添加！");
							$("#other_type").val("");
						}
					}
				});
			}
		}
	});	 
		 
});

function setOnlyNum(obj) {
	var v = obj.value.replace(/[^\d\.]/gi, '');
	if( v == 0 ){
		obj.value = '';
	} else {
		obj.value = v;
	}
}

function setOnlyDouble(obj) {
	var v = obj.value.replace(/[^\d+(\.\d+)?]/gi,'');
		obj.value=v;
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
