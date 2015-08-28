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
    <html-el:form action="/zmd/KonkaXxPd">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="dept_pd_id" value="${af.map.dept_pd_id}" /> 
      <html-el:hidden property="pd_cls" value="1010100" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable4">
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right">企业名称：</td>
          <td><strong class="fb">康佳集团股份有限公司</strong></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right"><span style="color:red">[必填]</span>分公司：</td>
          <c:if test="${not empty dept_id}">
            <td width="85%"><html-el:select property="dept_id" styleId="dept_id" style="width:225px;" disabled="true">
                <html-el:option value="${dept_id}">${fn:escapeXml(dept_name)}</html-el:option>
              </html-el:select></td>
          </c:if>
          <c:if test="${empty dept_id}">
            <td width="85%"><html-el:select property="dept_id" styleId="dept_id" style="width:225px;">
                <html-el:option value="">==请选择==</html-el:option>
                <c:forEach var="cur" items="${konkaDeptList}">
                  <html-el:option value="${cur.dept_id}">${fn:escapeXml(cur.dept_name)}</html-el:option>
                </c:forEach>
              </html-el:select></td>
          </c:if>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right"><span style="color:red">[必填]</span>产品型号：</td>
          <td width="85%"><select name="md_name" id="md_name" multiple="multiple">
              <c:forEach items="${konkaXxStdPdList}" var="cur" varStatus="vs"> <option value="${cur.md_name}" 
                <c:if test="${cur.md_name eq md_name}">selected="selected"</c:if>>${cur.md_name}            
                </option>
              </c:forEach>
            </select></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right"><span style="color:red">[必填]</span>工厂仓位：</td>
          <td width="85%"><select name="fac_store_name" id="fac_store_name" multiple="multiple">
              <c:forEach items="${storeList}" var="cur" varStatus="vs"> <option value="${cur.fac_store}" 
                <c:if test="${cur.fac_store eq fac_store_name}">selected="selected"</c:if>>${cur.store_desc}            
                </option>
              </c:forEach>
            </select></td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right">上架时间：</td>
          <td><html-el:text property="up_time" styleId="up_time"  size="10" maxlength="10" readonly="true" style="cursor:pointer;text-align:center;" title="点击选择日期" value='${af.map.map.up_time}'/></td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right">下架时间：</td>
          <td><html-el:text property="down_time" styleId="down_time" size="10" maxlength="10" readonly="true" style="cursor:pointer;text-align:center;" title="点击选择日期" value='${af.map.map.down_time}' /></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right"><span style="color:red">[必填]</span>零售参考价（元）：</td>
          <td width="85%"><html-el:text property="price_ref" styleId="price_ref" maxlength="8" size="12" /></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right"><span style="color:red">[必填]</span>零售价格下限（元）：</td>
          <td width="85%"><html-el:text property="price_min" styleId="price_min" maxlength="8" size="12" /></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right">备注：</td>
          <td width="85%"><html-el:textarea property="remarks" styleId="remarks" style="height:60px;width:270px;"/></td>
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
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#up_time").datepicker();
	$("#down_time").datepicker();
	$("#dept_id").attr("dataType", "Require").attr("msg", "请选择分公司！");
	$("#fac_store_name").attr("dataType", "Require").attr("msg", "请选择工厂仓位！");
	$("#md_name").attr("dataType", "Require").attr("msg", "请填写产品型号！");
	$("#price_min").attr("dataType", "Require").attr("msg", "请填写价格下限！");
	$("#remarks").attr("dataType", "Limit").attr("max", "200").attr("msg", "备注不能超过200个文字");
	$("#price_min").attr("focus",setOnlyNum);
	$("#price_ref").attr("focus",setOnlyNum);
	$("#send").click(function(){
			var isSubmit = Validator.Validate(this.form, 3);
			
			var price_ref = Number($("#price_ref").val());
			var price_min = Number($("#price_min").val()); 
			
			if(price_ref < price_min){
				alert("该商品的参考单价不能低于价格下限！");
				return false;
			}
			
			var up_time = $("#up_time").val().replace(/-/g, '');
			var down_time = $("#down_time").val().replace(/-/g, '');
			if(up_time > down_time){
				alert("上架时间必须小于或等于下架时间");
				return false;
			}
			
			if (isSubmit) {
				$(":button").attr("disabled", "true");
				$(":reset").attr("disabled", "true");
				this.form.submit();
			}
	 });

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

	//选择型号
	$("#fac_store_name").multiselect({
		noneSelectedText: '==请选择==',
		selectedList: 1,
		multiple: false,
		minWidth:220,
		click: function(event, ui){
	       if(ui.value != ""){
	       }
		}
	}).multiselectfilter();
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
