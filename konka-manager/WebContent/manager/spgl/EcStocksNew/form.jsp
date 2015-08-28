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
    <html-el:form action="/spgl/EcStocks" method="post">
      <html-el:hidden property="store_id" styleId="store_id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <html-el:hidden property="store_type" styleId="store_type" value="${af.map.store_type}" />
      
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
      	  <c:if test="${empty af.map.store_id}">
          <tr>
            <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>仓库：</td>
            <td width="88%" align="left"><html-el:select property="store_id_1" styleId="store_id_1">
                <html-el:option value="">请选择...</html-el:option>
                <c:forEach var="cur" items="${entityList}">
                  <html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
                </c:forEach>
              </html-el:select>
            </td>
          </tr>
          </c:if>
          <c:if test="${not empty af.map.store_id}">
           <tr>
            <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>仓库：</td>
            <td width="88%" align="left"><html-el:select property="store_id_1" styleId="store_id_1" disabled="true">
                <html-el:option value="">请选择...</html-el:option>
                <c:forEach var="cur" items="${entityList}">
                  <html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
                </c:forEach>
              </html-el:select>
            </td>
          </tr>
          </c:if>
        <html-el:hidden property="goods_id" styleId="goods_id"  value="${af.map.goods_id}" />
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>仓库数量：</td>
          <td width="88%" align="left"><html-el:text property="stocks" styleId="stocks" size="15" maxlength="15"/>
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">累计成本：</td>
          <td width="88%" align="left"><html-el:text property="total_cost" styleId="total_cost" size="15" maxlength="15"/>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><html-el:button property="" value="提交" styleClass="but4" styleId="btn_submit" />
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="location.href='${ctx}/manager/spgl/EcStocks.do?method=view&id=${af.map.goods_id}&store_type=${af.map.store_type}&mod_id=${af.map.mod_id} ' " /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	$("#store_id_1").attr("datatype", "Require").attr("msg", "请选择仓库！");
	$("#stocks").attr("datatype", "Integer").attr("msg", "请填写库存！");

	$("#btn_submit").click(function(){
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
