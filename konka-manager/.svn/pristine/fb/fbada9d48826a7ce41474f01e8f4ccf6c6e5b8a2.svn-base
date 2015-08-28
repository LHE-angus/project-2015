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
    <html-el:form action="/jf/JfExchange">
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="saveEx" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <th colspan="2" height="45" style="font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';"><span>积分兑换信息</span></th>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>会员卡号：</td>
          <td width="88%" align="left"><html-el:text property="user_sn" size="20" maxlength="20" readonly="true" styleId="user_sn" /></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>总积分：</td>
          <td width="88%" align="left"><html-el:text property="total_scorts" size="20" maxlength="20" readonly="true" styleId="total_scorts" /></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>兑换礼品：</td>
          <td width="88%" align="left"><html-el:select property="gift_id" styleId="gift_id">
              <html-el:option value="">请选择</html-el:option>
              <c:forEach var="cur" items="${giftList}">
              	<html-el:option value="${cur.id}">${cur.gift_name}</html-el:option>
              </c:forEach>
              
            </html-el:select></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>兑换积分：</td>
          <td width="88%" align="left"><html-el:text property="scorts" size="8" maxlength="8" readonly="true" styleId="scorts" /></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">备注：</td>
          <td width="88%" align="left"><html-el:text property="remark" size="120" maxlength="120" styleId="remark" />
          &nbsp;<span id="jf_value_desc" class="note"></span>
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
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#gift_id").attr("dataType", "Require").attr("msg", "请选择兑换礼品");
	//$("#scorts").attr("dataType", "Require").attr("msg", "请填写兑换积分").focus(function(){setOnlyNum(this);});

	$("#gift_id").change(function(){
		var gift_id = $(this).val();
		$.ajax({
			type: "POST",
			url: "JfExchange.do",
			data: {method : "ajaxSetScortsByGift", "gift_id": gift_id},
			dataType: "json",
			cache:false,
			error: function(){alert("数据加载请求失败！");},
			success: function(ret){
				if(ret){
					$("#scorts").val(ret.scores);
				}else{
					alert("该礼品已经没有库存，请联系管理员！");
				}
			}
		});
	});

	$("#btn_submit").click(function(){
		var isSubmit = Validator.Validate(this.form, 3);
		if (isSubmit) {
			var total_scorts = parseFloat($("#total_scorts").val());
			var scorts = parseFloat($("#scorts").val());
			if(total_scorts >= scorts){
				$(":button").attr("disabled", "true");
				$(":reset").attr("disabled", "true");
				this.form.submit();
			}else{
				alert("您当前的积分不够，请重新选择其他礼品");
				return false;
			}
			
		}
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
		if(isNaN(obj.value) || typeof(obj.value) == "undefined") obj.value = "";
	}).keyup(function (){
		if(!obj.value.match(/^[\+\-]?\d*?\.?\d*?$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))obj.o_value=obj.value;
		if(isNaN(obj.value) || typeof(obj.value) == "undefined") obj.value = "";
	}).blur(function (){
		if(!obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\.\d+$/))obj.value=0+obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
		if(isNaN(obj.value) || typeof(obj.value) == "undefined") obj.value = "";
	});
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
