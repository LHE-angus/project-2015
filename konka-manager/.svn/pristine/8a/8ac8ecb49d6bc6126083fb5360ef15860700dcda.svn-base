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
    <html-el:form action="/spgl/EcBaseExpressReachDay" method="post">
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
         <tr>
            <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>仓库：</td>
            <td width="88%" align="left"><html-el:select property="store_id" styleId="store_id">
                <html-el:option value="">请选择...</html-el:option>
                <c:forEach var="cur" items="${entityList}">
                  <html-el:option value="${cur.store_id}">${cur.store_name}/
                  <c:if test="${cur.store_type eq 0}">全国仓库</c:if>
                  <c:if test="${cur.store_type eq 1}">区域仓库</c:if>
                  <c:if test="${cur.store_type eq 2}">分公司仓库</c:if>
                  </html-el:option>
                </c:forEach>
              </html-el:select>
            </td>
          </tr>
           <tr>
            <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>送达区域：</td>
            <td width="88%" align="left"><select name="province" id="province" onmouseover="this.style.width='110px';" onchange="this.style.width='110px';" onblur="this.style.width='110px';" style="width:110px;">
                <option value="">-请选择-</option>
              </select>
              <select name="city" id="city" onmouseover="this.style.width='110px';" onchange="this.style.width='110px';" onblur="this.style.width='110px';" style="width:110px;">
                <option value="">-请选择市-</option>
              </select>
               <select name="country" id="country" onmouseover="this.style.width='110px';" onchange="this.style.width='110px';" onblur="this.style.width='110px';" style="width:110px;">
                <option value="">-请选择县-</option>
              </select>
              <select name="town" id="town" onmouseover="this.style.width='110px';" onchange="this.style.width='110px';" onblur="this.style.width='110px';" style="width:110px;">
                <option value="">-请选择乡镇-</option>
              </select>
              </td>
          </tr>
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>最长天数：</td>
          <td width="88%" align="left"><html-el:text property="max_reach_day" styleId="max_reach_day" size="10" maxlength="2"/>
         <span> &nbsp;&nbsp;&nbsp;备注：如果填写-1，表示该区域不能送达</span>
          </td>
        </tr>
        
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">描述：</td>
          <td width="88%" align="left"><html-el:text property="desc" styleId="desc" size="30" maxlength="30"/></td>
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
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#store_id").attr("datatype", "Require").attr("msg", "请选择仓库");
	$("#max_reach_day").attr("datatype", "Require").attr("msg", "请填写最大送达天数");
	$("#province").attr({"subElement": "city", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${province}", "datatype": "Require", "msg": "请选择省名称！"});
	$("#city"    ).attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${city}", "datatype": "Require", "msg": "请选择市名称！"});
	$("#country" ).attr({"subElement": "town", "defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${country}", "datatype": "Require", "msg": "请选择县名称！"});
	$("#town"    ).attr({"defaultText": "-请选择乡镇-", "defaultValue": "", "selectedValue": "${town}"});
	
	$("#province").cs_ext("${ctx}/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false, "p_index_join", "${af.map.p_index_join}");
	$("#province").change();

	$("#max_reach_day").blur(function(){
		var price = $("#max_reach_day").val();
		var _reg = /^-?\d+$/;
		if (!_reg.test(price)) {
			$("#max_reach_day").val("");
		}
	});	
	
	
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
