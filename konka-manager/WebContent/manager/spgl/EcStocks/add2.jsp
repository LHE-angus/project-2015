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
  <div class="rtabcont2">
    <html-el:form action="/spgl/EcStocks" method="post">
      <html-el:hidden property="store_id" styleId="store_id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save2" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <html-el:hidden property="type1" styleId="type1" value="0"/>
      <html-el:hidden property="own_sys" styleId="own_sys"  value="${af.map.own_sys}"/>
      <c:set var="readonly" value="${empty af.map.store_id ? false : true}"/>
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
      	  <tr>
      	  	<td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>仓库类型：</td>
            <td width="88%" align="left">
            	<html-el:radio property="store_type" styleId="store_type_0" styleClass="store_type"  value="0" disabled="${readonly}"><span id="store_type_0_p">全国仓库</span></html-el:radio>
            	<html-el:radio property="store_type" styleId="store_type_1" styleClass="store_type"  value="1" disabled="${readonly}"><span id="store_type_1_p">区域仓库</span></html-el:radio>
            	<html-el:radio property="store_type" styleId="store_type_2" styleClass="store_type"  value="2" disabled="${readonly}">分公司仓库</html-el:radio>
            </td>
      	  </tr>	
      	  <c:if test="${empty af.map.store_id}">
          <tr id="t0" >
            <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>全国仓库：</td>
            <td width="88%" align="left"><html-el:select property="store_id_1" styleId="store_id_1">
                <html-el:option value="">请选择...</html-el:option>
                <c:forEach var="cur" items="${entityList1}">
                  <html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
                </c:forEach>
              </html-el:select>
            </td>
          </tr>
          </c:if>	
          <tr id="t1" style="display: none">
            <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>区域仓库：</td>
            <td width="88%" align="left"><html-el:select property="store_id_2" styleId="store_id_2">
                <html-el:option value="">请选择...</html-el:option>
                <c:forEach var="cur" items="${entityList2}">
                  <html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
                </c:forEach>
              </html-el:select>
            </td>
          </tr>
          <tr id="t2" style="display: none">
            <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>分公司仓库：</td>
            <td width="88%" align="left"><html-el:select property="store_id_3" styleId="store_id_3">
                <html-el:option value="">请选择...</html-el:option>
                <c:forEach var="cur" items="${entityList3}">
                  <html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
                </c:forEach>
              </html-el:select>
            </td>
          </tr>
        <html-el:hidden property="goods_id" styleId="goods_id"  value="${af.map.goods_id}" />
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>仓库数量：</td>
          <td width="88%" align="left"><html-el:text property="stocks" styleId="stocks" size="15" maxlength="15"/>
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">累计成本：</td>
          <td width="88%" align="left"><html-el:text property="total_cost" styleId="total_cost" size="13" maxlength="13"/>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><html-el:button property="" value="提交" styleClass="but4" styleId="btn_submit" />
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="location.href = '${ctx}/manager/spgl/EcStocks.do?method=list2&mod_id=${af.map.mod_id}&goods_id=${af.map.goods_id}&own_sys=${af.map.own_sys}' " /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	$("#stocks").attr("datatype", "Integer").attr("msg", "请填写库存！");

	var is_fgs = '${is_fgs}';
	if(is_fgs == 1){
		//$("#store_type_0").hide();
		$("#store_type_1").hide();
		//$("#store_type_0_p").hide();
		$("#store_type_1_p").hide();
		//$("#t0").hide();
		//var store_type ='${af.map.store_type}';
		//if(store_type == 2 ){
			//$("#t2").show();
		//}
		$("#store_id_1").attr("datatype", "Require").attr("msg", "请选择仓库！");
		$("#store_id_3").removeAttr("dataType").removeAttr("msg").val("");
		$("#store_id_2").removeAttr("dataType").removeAttr("msg").val("");
	}	

	
	$(".store_type").click(function(){
		if($(this).val() == 0){
			$("#t0").show();
			$("#t1").hide();
			$("#t2").hide();
			$("#type1").val(0);
			$("#store_id_1").attr("datatype", "Require").attr("msg", "请选择仓库！");
			$("#store_id_2").removeAttr("dataType").removeAttr("msg").val("");
			$("#store_id_3").removeAttr("dataType").removeAttr("msg").val("");
		}else if($(this).val() == 1){
			$("#t0").hide();
			$("#t1").show();
			$("#t2").hide();
			$("#type1").val(1);
			$("#store_id_2").attr("datatype", "Require").attr("msg", "请选择仓库！");
			$("#store_id_1").removeAttr("dataType").removeAttr("msg").val("");
			$("#store_id_3").removeAttr("dataType").removeAttr("msg").val("");
		}else if($(this).val() == 2){
			$("#t0").hide();
			$("#t1").hide();
			$("#t2").show();
			$("#type1").val(2);
			$("#store_id_3").attr("datatype", "Require").attr("msg", "请选择仓库！");
			$("#store_id_1").removeAttr("dataType").removeAttr("msg").val("");
			$("#store_id_2").removeAttr("dataType").removeAttr("msg").val("");
		}		
	});

	
	$("#btn_submit").click(function(){

		//<c:if test="${is_admin eq 1}">
		if($("#type1").val() == 0){
			$("#store_id_1").attr("datatype", "Require").attr("msg", "请选择仓库！");
			$("#store_id_2").removeAttr("dataType").removeAttr("msg").val("");
			$("#store_id_3").removeAttr("dataType").removeAttr("msg").val("");
		}
		//</c:if >
		
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
