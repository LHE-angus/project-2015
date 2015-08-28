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
    <html-el:form action="/admin/KonkaMobileSailData">
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <th colspan="2" height="45" style="font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';"><span>销售信息</span></th>
        </tr>
        <tr>
	    <td width="25%" align="center" nowrap="nowrap" class="title_item"><span style="color:red">*</span>门店</td>
	    <td width="75%" align="left" nowrap="nowrap">
	    	<html-el:text property="dept_name" size="40" maxlength="40" styleId="dept_name" readonly="true"/>
	    </td>
       </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="center"><font color="red">* </font>型号：</td>
          <td width="88%" align="left">
              <html-el:hidden property="pd_id" styleId="pd_id" value="${af.map.model_id}" />
              <html-el:hidden property="model_name" styleId="model_name" />
			  <html-el:text property="md_name" styleId="md_name" size="40" styleClass="webinput" value="${af.map.model_name}" readonly="true" onclick="${empty af.map.id ?'':'openWindow();'} "/>
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="center"><font color="red">* </font>数量：</td>
          <td width="88%" align="left"><html-el:text property="num" size="8" maxlength="4" styleId="num" onfocus="javascript:setOnlyInt(this);" />&nbsp;</td>
        </tr>
  <tr>
    <td align="center" nowrap="nowrap" class="title_item">单价</td>
    <td align="left" nowrap="nowrap"><html-el:text property="s_price" size="20" maxlength="8" styleId="s_price"  onfocus="javascript:setOnlyInt(this);"/></td>
  </tr>
  <tr>
    <td align="center" nowrap="nowrap" class="title_item">金额</td>
    <td align="left" nowrap="nowrap"><html-el:text property="all_price" size="20" maxlength="9" styleId="all_price"   readonly="true"/></td>
  </tr>
  <tr>
    <td align="center" nowrap="nowrap" class="title_item">备注</td>
    <td align="left" nowrap="nowrap">
    	<html-el:textarea property="memo" styleId="memo" cols="5" style="width:450px;height:70px;" />
          	<div id="info_tip" style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div></td>
  </tr>
  <tr>
    <td align="center" nowrap="nowrap" class="title_item">顾客姓名</td>
    <td align="left" nowrap="nowrap"><html-el:text property="realname" size="20" maxlength="10" styleId="realname" />
      </td>
  </tr>
  <tr>
    <td align="center" nowrap="nowrap" class="title_item">顾客电话</td>
    <td align="left" nowrap="nowrap"><html-el:text property="phonenum" size="20" maxlength="20" styleId="phonenum" />
     </td>
  </tr>
  <tr>
    <td align="center" nowrap="nowrap" class="title_item">顾客地址</td>
    <td align="left" nowrap="nowrap"><html-el:text property="addresss" size="20" maxlength="20" styleId="addresss" />
      </td>
  </tr>
  <tr>
    <td align="center" nowrap="nowrap" class="title_item">顾客身份证 </td>
    <td align="left" nowrap="nowrap"><html-el:text property="mastercode" size="20" maxlength="18" styleId="mastercode" />
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
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#md_name").attr("datatype", "Require").attr("msg", "请选择型号");
	$("#num").attr("datatype", "Require").attr("msg", "请填写数量");
	$("#all_price").attr("datatype", "Require").attr("msg", "请填写总金额");

	$("#memo").textbox({
		maxLength: 100,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_tip").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_tip").slideUp("normal");
	});

	$("#num, #s_price").change(function(){
		var num = $("#num").val();
		var s_price = $("#s_price").val();
		if(num==0||s_price==0){
		    $("#all_price").val(0);
		}else{
			$("#all_price").val((s_price*num).toFixed(2));
		}
	});
	$("#s_price").blur(function(){
		var s_price = $(this).val();
		if(s_price<500 || s_price>9000){
			alert("单价需在500到9000之间!");
			$(this).val("");
		}
	});

	$("#btn_submit").click(function(){
		var isSubmit = Validator.Validate(this.form, 3);
		var s_price = $("#s_price").val();
		if(s_price<500 || s_price>9000){
			alert("单价需在500到9000之间!");
			return false;
		}
		if (isSubmit) {
			$(":button").attr("disabled", "true");
			$(":reset").attr("disabled", "true");
			this.form.submit();
		}
	});
});

function openWindow(){
	 window.open("SelectMdName.do?azaz=" + Math.random(),'window','height=450,width=600,top=150,left=300,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
}

function set_value(pd_id,md_name){
	$("#pd_id").val(pd_id);
	$("#md_name").val(md_name);
	$("#model_name").val(md_name);
}


//正则表达式：只能输入数字
//正则表达式：只能输入数字
function setOnlyNum(obj) {
	$(obj).css("ime-mode", "disabled");
	$(obj).attr("t_value", "");
	$(obj).attr("o_value", "");
	$(obj).bind("dragenter",function(){
		return false;
	});
	$(obj).keypress(function (){
		if(!obj.value.match(/^[\+\-]?\d*?\.?\d*?$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))obj.o_value=obj.value;
	}).keyup(function (){
		if(!obj.value.match(/^[\+\-]?\d*?\.?\d*?$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))obj.o_value=obj.value;
	}).blur(function (){
		if(!obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\.\d+$/))obj.value=0+obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
		if(isNaN(obj.value)) obj.value = "";
	});
}


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
