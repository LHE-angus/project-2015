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
    <html-el:form action="/zmd/KonkaXxZmdRewardSetPd">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="pd_set_id" value="${af.map.pd_set_id}" />
      <html-el:hidden property="md_name" styleId="md_name" />
      <html-el:hidden property="reward_type" styleId="reward_type" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable4">
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right">分公司名称：</td>
          <td>
          	<c:if test="${empty dept_name}">
                <html-el:select property="dept_id_s" styleId="dept_id_s" style="width:154px;" value="${af.map.dept_id}">
                  <html-el:option value="">==请选择==</html-el:option>
                  <c:forEach var="cur" items="${konkaDepts}">
                    <html-el:option value="${cur.dept_id}" >${cur.dept_name}</html-el:option>
                  </c:forEach>
                </html-el:select>
              </c:if>
              <c:if test="${not empty dept_name}">
                <strong class="fb">${fn:escapeXml(dept_name)}</strong>
              </c:if>
          </td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right"><span style="color:red">[必填]</span>专卖店编号：</td>
          <td width="85%"><html-el:select property="zmd_id" styleId="zmd_id" style="width:154px;">
              	<html-el:option value="">==请选择==</html-el:option>
           		<c:forEach var="cur" items="${zmdList}">
           			<html-el:option value="${cur.zmd_id}">${cur.zmd_sn}</html-el:option>
           		</c:forEach>
              </html-el:select></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right"><span style="color:red">[必填]</span>产品型号：</td>
          <td width="85%"><select name="md_name_temp" id="md_name_temp" multiple="multiple" style="width:225px;">
          	  <c:forEach items="${konkaXxPdList}" var="cur" varStatus="vs"> <option value="${cur.md_name}_${cur.pd_type}" 
                <c:if test="${cur.md_name eq md_name}">selected="selected"</c:if>>${cur.md_name}            
                </option>
              </c:forEach>
             </select></td>
        </tr>
        <tr>
          <td align="right" width="15%" nowrap="nowrap" class="title_item" ><span style="color:#F00;">[必填]</span>反佣类型：</td>
          <td width="85%"><html-el:select property="reward_type_temp" style="width:225px;" styleId="reward_type_temp">
              <html-el:option value="">==请选择==</html-el:option>
              <c:forEach var="cur" items="${baseTypesList70000}">
                <html-el:option value="${cur.type_id}">${cur.type_name}</html-el:option>
              </c:forEach>
            </html-el:select></td>
        </tr>
        <tr>
          <td align="right" width="15%" nowrap="nowrap" class="title_item" ><span style="color:#F00;">[必填]</span>反佣比例(%)：</td>
          <td width="85%"><html-el:text property="reward_ratio" styleId="reward_ratio" maxlength="10" size="12" />&nbsp;(注：百分比值的范围：1-100)</td>
        </tr>
        <tr>
          <td>&nbsp;&nbsp;</td>
          <td><input class="but4" type="button" value="保存" id="send" />
            <input class="but3" type="reset"  value="重填 " />
            <input class="but5" type="button" value="返回" onclick="history.back()" /></td>
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
	
	$("#zmd_id").attr("dataType", "Require").attr("msg", "请选择专卖店！");
	$("#md_name_temp").attr("dataType", "Require").attr("msg", "请填写产品型号！");
	$("#reward_type_temp").attr("dataType", "Require").attr("msg", "请选择反佣类型！");
	$("#reward_ratio").attr("dataType", "Require").attr("msg", "反佣比例！").attr("focus",setOnlyNum);

	//初始化reward_type_temp的值
	$("#reward_type_temp").attr("value","${af.map.reward_type}");
	
	$("#send").click(function(){
			var isSubmit = Validator.Validate(this.form, 3);
			
			if (isSubmit) {
				var reward_ratio = Number($.trim($("#reward_ratio").val()));
				if(reward_ratio < 0 || reward_ratio > 100){
					alert("反佣比例在1-100之间！");
					return false;
				} else {
					$(":button").attr("disabled", "true");
					$(":reset").attr("disabled", "true");
					this.form.submit();
				}
			}
	 });

	//选择型号
	$("#md_name_temp").multiselect({
		noneSelectedText: '==请选择==',
		selectedList: 1,
		multiple: false,
		minWidth:220,
		click: function(event, ui){
	       if(ui.value != ""){
		       $("#md_name").val(ui.value.split("_")[0]);
		       $("#reward_type_temp").attr("value",ui.value.split("_")[1]);
		       $("#reward_type").val(ui.value.split("_")[1]);
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
		this.t_value = '';
	});
	//this.text.selected;
}
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
