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
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
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
    <html-el:form action="/zmd/KonkaXxLogistics">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
       <html-el:hidden property="logistics_id" value="${af.map.logistics_id}" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable4">
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right">分公司：</td>
            <td width="85%">${fn:escapeXml(dept_name)}</td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right"><span style="color:red">[必填]</span>产品型号：</td>
          <td width="85%"><select name="md_name" id="md_name" multiple="multiple">
              <c:forEach items="${konkaXxStdPdList}" var="cur" varStatus="vs"> <option value="${cur.md_name}" 
                <c:if test="${cur.md_name eq af.map.md_name}">selected="selected"</c:if>>${cur.md_name}            
                </option>
              </c:forEach>
            </select></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right"><span style="color:red">[必填]</span>发货地区：</td>
          <td width="85%">
            <html-el:select property="province_s" styleId="province_s" style="width:150px;">
              <option value="">-请选择省/直辖市/自治区-</option>
            </html-el:select>
            <html-el:select property="city_s" styleId="city_s" style="width:100px;">
              <option value="">-请选择市-</option>
            </html-el:select>
            <html-el:select property="country_s" styleId="country_s" style="width:100px;">
              <option value="">-请选择区/县-</option>
            </html-el:select></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right"><span style="color:red">[必填]</span>收货地区：</td>
          <td width="85%">
            <html-el:select property="province_e" styleId="province_e" style="width:150px;">
              <option value="">-请选择省/直辖市/自治区-</option>
            </html-el:select>
            <html-el:select property="city_e" styleId="city_e" style="width:100px;">
              <option value="">-请选择市-</option>
            </html-el:select>
            <html-el:select property="country_e" styleId="country_e" style="width:100px;">
              <option value="">-请选择区/县-</option>
            </html-el:select></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right"><span style="color:red">[必填]</span>物流费用：</td>
          <td width="85%"><html-el:text property="fee" styleId="fee" size="10" maxlength="8" /> &nbsp;元</td>
        </tr>
        <tr>
          <td>&nbsp;&nbsp;</td>
          <td><input class="but4" type="button" name="Submit4" value="保存" id="send" />
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
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#md_name").attr("dataType", "Require").attr("msg", "请填写产品型号！");
	$("#fee").attr("dataType", "Require").attr("msg", "请填写物流费用！");
	$("#fee").attr("focus",setOnlyNum);
	
	$("#province_e").attr({"subElement": "city_e", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${af.map.province_e}","dataType":"Require","msg":"请选择省/直辖市/自治区！"});
	$("#city_e").attr({"subElement": "country_e", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${af.map.city_e}","dataType":"Require","msg":"请选择市！"});
	$("#country_e").attr({"defaultText": "-请选择区/县-", "defaultValue": "", "selectedValue": "${af.map.country_e}","dataType":"Require","msg":"请选择区/县！"});
	$("#province_e").cs_ext("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceList", "p_index", "0", false);
	$("#province_e").change();

	$("#province_s").attr({"subElement": "city_s", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${af.map.province_s}","dataType":"Require","msg":"请选择省/直辖市/自治区！"});
	$("#city_s").attr({"subElement": "country_s", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${af.map.city_s}","dataType":"Require","msg":"请选择市！"});
	$("#country_s").attr({"defaultText": "-请选择区/县-", "defaultValue": "", "selectedValue": "${af.map.country_s}","dataType":"Require","msg":"请选择区/县！"});
	$("#province_s").cs_ext("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceList", "p_index", "0", false);
	$("#province_s").change();
	
	//选择型号
	$("#md_name").multiselect({
		noneSelectedText: '==请选择==',
		selectedList: 1,
		multiple: false,
		minWidth:220,
		click: function(event, ui){
	       if(ui.value != ""){
	       }
		}
	}).multiselectfilter();

	$("#send").click(function(){
		var isSubmit = Validator.Validate(this.form, 3);
		if(isSubmit){
			this.form.submit();
		}
	});
});

function setOnlyNum() {
	$(this).css("ime-mode", "disabled").attr("t_value", "").attr("o_value", "");
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

//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
