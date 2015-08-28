<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base target="_self" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/commons/styles/select2.min.css" rel="stylesheet" type="text/css" />
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
    <%@ include file="/commons/pages/messages.jsp" %>
    <html-el:form action="/manager/JBaseGoods">
      <html-el:hidden property="method" value="save"/>
      <html-el:hidden property="queryString" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="goods_id" styleId="goods_id" />
      <html-el:hidden property="returnUrl" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td colspan="2"  bgcolor="#CCCCCC" style="font-weight:bold;">商品信息填写</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>商品名称：</td>
          <td align="left">
	          <html-el:select property="is_konka" styleId="is_konka">
	          		<html-el:option value="1">康佳电视</html-el:option>
	          		<html-el:option value="0">其他商品</html-el:option>
	          </html-el:select>
	          <span id="id_select" style="display:${af.map.is_konka eq 1 or empty af.map.goods_id ? '' : 'none'};">
		          <html-el:select property="goods_name_select" styleId="goods_name_select" value="${af.map.goods_name}" style="display:${af.map.is_konka eq 1 or empty af.map.goods_id ? '' : 'none'}; width:250px">
		          		<html-el:option value="">请选择康佳电视</html-el:option>
		          		<c:forEach var="cur" items="${konkaPdModelList}" varStatus="vs">
		          			<html-el:option value="${cur.md_name}">${cur.md_name}</html-el:option>
		          		</c:forEach>
		          </html-el:select>
	          </span>
	          <html-el:text property="goods_name_input" size="40" maxlength="40" styleId="goods_name_input" value="${af.map.goods_name}" style="display:${af.map.is_konka eq 0 ? '' : 'none'};" />
			  <html-el:hidden property="goods_name" styleId="goods_name" />          
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">商品条码：</td>
          <td align="left"><html-el:text property="goods_sn" size="30" maxlength="20" styleId="goods_sn" />&nbsp;&nbsp;&nbsp;<span style="margin-left:10px;color:blue;">提示，建议使用条码枪扫描，可快速录入商品。</span>
          </td>
        </tr>
        <tr>
          <td width="17%" nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>商品类型：</td>
          <td width="83%" align="left"><html-el:select property="goods_type" styleId="goods_type">
          		<html-el:option value="">请选择</html-el:option>
          		<c:forEach var="cur" items="${list10001}" varStatus="vs">
          			<html-el:option value="${cur.type_id}">${cur.type_name}</html-el:option>
          		</c:forEach>
          </html-el:select>&nbsp;&nbsp;&nbsp;<a onclick="javascript:location.href='${ctx}/customer/manager/JBaseType.do?method=add&par_id=10001&returnUrl=' + encodeURIComponent(location.href) + '&mod_id=199010100';return true;" style="color:blue;cursor:pointer;">新建商品类型</a></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>商品单位：</td>
          <td align="left"><html-el:select property="goods_unit" styleId="goods_unit">
          		<c:forEach var="cur" items="${list10002}" varStatus="vs">
          			<html-el:option value="${cur.type_id}">${cur.type_name}</html-el:option>
          		</c:forEach>
          </html-el:select>&nbsp;&nbsp;&nbsp;<a onclick="javascript:location.href='${ctx}/customer/manager/JBaseType.do?method=add&par_id=10002&returnUrl=' + encodeURIComponent(location.href) + '&mod_id=199010100';return true;" style="color:blue;cursor:pointer;">新建商品单位</a></td></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>商品状态：</td>
          <td align="left"><html-el:select property="goods_stutes" styleId="goods_stutes">
          		<html-el:option value="0">上架</html-el:option>
          		<html-el:option value="1">下架</html-el:option>
          </html-el:select></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">商品描述：</td>
          <td align="left">
          	<textarea name="goods_desc" rows="5" cols="60" id="goods_desc" style="resize:none">${af.map.goods_desc}</textarea>
          	<div id="goods_desc_1"  style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/styles/jxc/images/tishi.gif" style="vertical-align:middle;" /></div>
          </td>
        </tr>
        <tr>
          <td colspan="2" nowrap="nowrap" align="right">&nbsp;</td>
        </tr>
<!--         <tr> -->
<!--           <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>初始化库存：</td> -->
<%--           <td align="left"><html-el:text property="init_count" size="20" maxlength="6" styleId="init_count" onfocus="javascript:setOnlyNum(this);"/></td> --%>
<!--         </tr> -->
<%--         <c:if test="${empty af.map.goods_id}"> --%>
<!--         	<tr> -->
<!--           		<td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>初始化库存进货单价（元）：</td> -->
<%--           		<td align="left"><html-el:text property="buy_price" size="20" maxlength="8" styleId="buy_price" onfocus="javascript:setOnlyNum(this);" /></td> --%>
<!--         	</tr> -->
<%--         </c:if> --%>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>建议销售单价（元）：</td>
          <td align="left"><html-el:text property="sell_price" size="20" maxlength="8" styleId="sell_price" onfocus="javascript:setOnlyNum(this);" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><html-el:button property="" value="提 交" styleClass="but4" styleId="btn_submit" />
            <html-el:button property="" value="重 置" styleClass="but5" styleId="btn_reset" onclick="this.form.reset();" />
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();" />
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/select2.min.js?t=1"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#is_konka").change(function() {
		var v1 = $(this).attr("selected","true").val();
		if (v1 == "1") {
			$("#goods_name_input").hide().prev().show();
			$("#goods_name_input").val("");
		} else {
			$("#id_select").hide();
			$("#goods_name_input").show();
		}
	});
	
	if('${af.map.goods_id}'){
		$('#is_konka').attr("disabled","disabled");
		$('#goods_name_select').attr("disabled","disabled");
		$('#goods_name_input').attr("disabled","disabled");
	}
	
	$("#goods_type").attr("dataType", "Require").attr("msg", "请选则商品类型！");
	$("#goods_unit").attr("dataType", "Require").attr("msg", "请选择商品单位！");
	$("#goods_stutes").attr("dataType", "Require").attr("msg", "请选择商品状态！");
	$("#store_id").attr("dataType","Require").attr("msg","请选择添加商品仓库！");
	$("#sell_price").attr("dataType", "Require").attr("msg", "请填写建议销售单价！");
	$("#is_konka").attr("dataType", "Require").attr("msg", "请选择康佳商品标识！");
	

	$("#btn_submit").click(function(){
		if ($("#is_konka").val() == "1") {
			$("#goods_name_select"  ).attr("dataType", "Require").attr("msg", "请选择一个康佳商品！");
			$("#goods_name_input"  ).removeAttr("dataType").removeAttr("msg");
		} else {
			$("#goods_name_input"  ).attr("dataType", "Require").attr("msg", "请填写商品名称！");
			$("#goods_name_select"  ).removeAttr("dataType").removeAttr("msg");
		}
		
		if(Validator.Validate(this.form, 3)){
			$("#tr_model").remove();
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_reset").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
            
            if ($("#is_konka").val() == "1") {
            	$("#goods_name").val($("#goods_name_select").val());
            } else {
            	$("#goods_name").val($("#goods_name_input").val());
            }
            
			this.form.submit();
		}
	});

	$("#goods_desc").textbox({
		maxLength: 120,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/styles/jxc/images/tishi.gif" style="vertical-align:middle;" />';
			$("#goods_desc_1").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#goods_desc_1").slideUp("normal");
	});
	
	// 可查询选择框
	$('#goods_name_select').select2({
	    minimumInputLength: 2,
	    allowClear: true
	});
});
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
		//if(obj.value.length == 0) obj.value = "0";
	});
}

//正则表达式：只能输入数字
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
		//if(obj.value.length == 0) obj.value = "0";
	});
}
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
