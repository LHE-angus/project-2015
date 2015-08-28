<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<title>${naviString}</title>
<!--[if IE]>
<link href="${ctx}/styles/manager/ieHack.css" rel="stylesheet" type="text/css" />
<![endif]-->
</head>
<body>
<div id="navTab" class="tabsPage" style="text-align:left;">
  <div class="tabsPageHeader">
    <div class="tabsPageHeaderContent">
      <ul class="navTab-tab">
        <li class="main"><a><span><span class="home_icon">${naviString}</span></span></a></li>
      </ul>
    </div>
  </div>
  <div class="navTab-panel tabsPageContent">
    <div class="page">
      <div class="pageContent">
        <div style="height:10px;"></div>
        <html-el:form action="/admin/SetCategory">
          <html-el:hidden property="c_index" styleId="c_index" />
          <html-el:hidden property="mod_id" styleId="mod_id" />
          <html-el:hidden property="method" styleId="method" value="save" />
          <html-el:hidden property="queryString" styleId="queryString" />
          <html-el:hidden property="order_value" styleId="order_value" value="0" />
          <table width="100%" border="0" cellpadding="0" cellspacing="1" class="list">
            <tr>
              <td width="12%" align="right">类别名称：<font color="red">*</font></td>
              <td><html-el:text property="c_name" size="80" maxlength="80" styleId="c_name" /></td>
            </tr>
            <tr>
              <td width="12%" align="right">类别类型：<font color="red">*</font></td>
              <td>
              <html-el:select property="c_type" styleId="c_type">
              	<html-el:option value="10">咨询类型</html-el:option>
              	<html-el:option value="2">补助类型</html-el:option>
              	<html-el:option value="3">扣罚类型</html-el:option>
              	<html-el:option value="4">故障类型</html-el:option>
              	<html-el:option value="5">业务分类</html-el:option>
              	<html-el:option value="6">维修级别</html-el:option>
              	<html-el:option value="7">服务方式</html-el:option>
              	<html-el:option value="8">产品类型</html-el:option>
              	<html-el:option value="9">新闻类型</html-el:option>
              	<html-el:option value="12">回访模板类别</html-el:option>
              </html-el:select></td>
            </tr>
            <tr>
              <td align="right">扩展字段1：</td>
              <td><html-el:text property="add1" styleId="add1" /></td>
            </tr>
            <tr>
              <td align="right">扩展字段2：</td>
              <td><html-el:text property="add2" styleId="add2" /></td>
            </tr>
            <tr>
              <td align="right">扩展字段3：</td>
              <td><html-el:text property="add3" styleId="add3" /></td>
            </tr>
            <tr>
              <td align="right">类别说明：</td>
              <td><html-el:textarea property="c_comm" rows="8" cols="80" styleId="c_comm" /></td>
            </tr>
            <tr>
              <td align="right">排序值：<font color="red">*</font></td>
              <td><html-el:text property="order_sort" size="4" maxlength="4" styleId="order_sort" /></td>
            </tr>
            <tr>
              <td align="right">类别状态：</td>
              <td><html-el:select property="is_del">
                  <html-el:option value="0">正常(未删除)</html-el:option>
                  <html-el:option value="1">已删除</html-el:option>
                </html-el:select></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td><html-el:button property="" value="提 交" styleClass="websub" styleId="btn_submit" />
                <html-el:button property="" value="重 置" styleClass="websub" styleId="btn_reset" onclick="this.form.reset();" />
                <html-el:button property="" value="返 回" styleClass="websub" styleId="btn_back" onclick="history.back();return false;" /></td>
            </tr>
          </table>
        </html-el:form>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#c_name"    ).attr("dataType", "Require").attr("msg", "请填写类别名称");
	$("#c_type"    ).attr("dataType", "Require").attr("msg", "请选择类别类型");
	$("#c_comm" ).attr({"dataType":"Limit","min":"0","max":"500","msg":"类别说明不能多于500个"});
	$("#solve_method" ).attr({"dataType":"Limit","min":"1","max":"500","msg":"请填写排除办法且字数少于500个"});
	$("#order_sort").attr("dataType", "Require").attr("msg", "请填写排序值").focus(setOnlyNum);

	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 3)){
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_reset").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});
});

function changePar_index(){
	if($("#c_index").val() ==$("#par_index").val()) {
		
		alert("父级目录不能选择自己，请重新选择！");
		$("#par_index").val("${af.map.par_index}");
	    return null;
	}
}

function setOnlyNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	})
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}
		if(this.value.length == 0) this.value = 0;
	})
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
