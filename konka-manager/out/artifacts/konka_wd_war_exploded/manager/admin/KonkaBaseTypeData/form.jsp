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
    <html-el:form action="/admin/KonkaBaseTypeData" method="post" enctype="multipart/form-data">
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="type_id" styleId="type_id" />
      <html-el:hidden property="par_type_id" styleId="par_type_id" />
      <html-el:hidden property="field2" styleId="field2" />
      
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
			<td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>类别ID：</td>
			<td width="88%" align="left"><html-el:text property="_type_id" disabled="true"
				styleId="_type_id"/></td>
		</tr>

		<tr>
			<td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>类别名称：</td>
			<td width="88%" align="left"><html-el:text property="type_name"
				styleId="type_name" /></td>
		</tr>
        <tr>
			<td width="12%" nowrap="nowrap" class="title_item" align="right">属性：</td>
			<td width="88%" align="left"><html-el:textarea property="field1" style="width:50%;height:60px;resize:none;"
				styleId="field1"/></td>
		</tr>
		<tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>父类别ID：</td>
             <td width="88%" align="left"><html-el:text property="_par_type_id" disabled="true"
				styleId="_par_type_id" /></td>
        </tr>
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>备注：</td>
          <td width="88%" align="left">
          <html-el:textarea property="memo" style="width:50%;height:60px;resize:none;"
				styleId="memo" /></td>
        </tr>
         <tr >
            <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>是否删除：</td>
            <td width="88%" align="left">
                 <html-el:select property="is_del" styleId="is_del" style="width:120px;">
	      		   <html-el:option value="0">正常</html-el:option>
	      		   <html-el:option value="1">已删除</html-el:option>
        	   </html-el:select>
            </td>
          </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>是否锁定：</td>
          <td width="88%" align="left">
           <html-el:select property="is_lock" styleId="is_lock" style="width:120px;">
	      		   <html-el:option value="0">正常</html-el:option>
	      		   <html-el:option value="1">已锁定</html-el:option>
        	   </html-el:select> 
          </td>
          </tr>
		 <tr>
			<td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>级别：</td>
			<td width="88%" align="left"><html-el:text property="_field2" disabled="true"
				styleId="_field2"/></td>
		</tr>
          <tr >
            <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>排序值：</td>
            <td width="88%" align="left"><html-el:text property="order_sort" styleId="order_sort" size="12" maxlength="12"/>
            </td>
          </tr>
           <tr >
            <td width="12%" nowrap="nowrap" class="title_item" align="right">附件：</td>
            <td width="88%" align="left">
                 <div id="fj"></div>
            </td>
          </tr>
        <tr>
          <td>&nbsp;</td>
          <td><html-el:button property="" value="提交" styleClass="but4" styleId="btn_submit" />
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();return false;" /></td>
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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.imgAddShow.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#type_name").attr("dataType", "Require").attr("msg", "请填写类别名称");
	$("#is_del").attr("dataType", "Require").attr("msg", "请选择是否删除");
	$("#is_lock").attr("dataType", "Require").attr("msg", "请选择是否锁定");
	$("#par_type_id").attr("dataType", "Require").attr("msg", "请选择父类级别");
	$("#order_sort").attr("dataType", "Require").attr("msg", "请填写排序值");
	$("#order_sort" ).focus(function(){setOnlyInt(this);});

	 //附件
	 $("#fj").imgshow({
		ctx:"${ctx}",
		delUrl:"${ctx}/manager/admin/KonkaSpActivityManager.do?method=deleteFile1",
		data:jQuery.parseJSON('${fj_paths}')
     });
	
	//Ajax列表查询
	$("#btn_submit").click(function(){

		if (Validator.Validate(this.form, 3)) {
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

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
