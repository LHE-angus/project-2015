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
<style type="text/css">
<!--
.red{ color:#F00;}
.bla{ color:#000; font-size:12px; font-weight:bold;}
.note {color:#777;margin-left:10px;}
span.required {color:#FF0000;font-weight:normal;background-color:#F4FAFF;}
-->
</style>
</head>
<body>
<div class="oarcont"  id="body_oarcont">
  <div class="rtabcont2">
      <%@ include file="/commons/pages/messages.jsp" %>
      <div style="padding:5px 5px"></div>
      <html-el:form action="/spgl/EcGroup.do">
        <html-el:hidden property="queryString" />
        <html-el:hidden property="edit" />
         <html-el:hidden property="mod_id" styleId="mod_id" value="${af.map.mod_id}"/>
        <html-el:hidden property="method" value="save" />
        <html-el:hidden property="par_id" value="${af.map.par_id}" />
        <html-el:hidden property="id" />
        <br />
        <table width="99%" border="0" align="center" cellpadding="2" cellspacing="0">
          <tr class="oartop">
            <td colspan="2">组织基本信息</td>
          </tr>
          <c:if test="${empty af.map.add_for_zb}">
          <tr>
            <td nowrap="nowrap" height="28">父部门：</td>
            <td>
              <html-el:select property="par_id" styleId="par_id" style="width:200px;" disabled="disabled">
                <html-el:option value="">请选择...</html-el:option>
                <c:forEach var="cur" items="${ecGroupList}">
                  <html-el:option value="${cur.id}">${fn:escapeXml(cur.map.tree_name)}</html-el:option>
                </c:forEach>
              </html-el:select>
            </td>
          </tr>
          </c:if>
          <tr>
            <td nowrap="nowrap" height="28">组织名称：</td>
            <td><html-el:text property="group_name" styleId="group_name" style="width:200px;" maxlength="20" />
              	<span style="" id="s_after"></span> &nbsp; <span id="loading" style="display:none;"> <img src="${ctx}/styles/images/ajax-loader.gif" style="vertical-align:middle; margin: 2px;" />正在处理... </span> 
             </td>
          </tr>
          <tr>
            <td nowrap="nowrap" height="28">关联分公司：</td>
            <td >
            <html-el:select property="link_dept_id" styleId="link_dept_id">
                <html-el:option value="">请选择...</html-el:option>
                <c:if test="${is_zb eq 1}">  
                <html-el:option value="0">多媒体事业部门</html-el:option>
                </c:if>
                <c:forEach var="cur" items="${sybDeptInfoList}">
                  <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
                </c:forEach>
              </html-el:select><span style="font: red;">如果是渠道系统里的组织架构，请选择相应的分公司，如果是虚拟的组织架构，可以不选择分公司</span>
            </td>
          </tr>
          <tr>
            <td height="50">备注：</td>
            <td><html-el:text property="remark" styleId="remark" size="40"  maxlength="50"/></td>
          </tr>
          <tr>
            <td colspan="2" style="text-align:center" height="28">
              <input class="but4" type="button" name="Submit4" value=" 保存 " id="btn_submit" />
              <input class="btn_reset" type="reset"  value="重填 " />
              <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
            </td>
          </tr>
        </table>
      </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<%@ include file="/commons/pages/areamove.jsp"%>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#span_help").click(function(){
        $("#cvtooltipClose").cvtooltip({
            panel: "#body_oarcont",
            direction: 1,                
            width: 420,
            left: 320,
            top: 5,
            speed: 500,
            delay: 10000
        });
    });	

	$("#group_name").blur(function() {   
		if(this.value.length > 0 && this.value != '${af.map.group_name}'){
			$("#loading").ajaxStart(function(){$(this).show();});
			$("#loading").ajaxStop (function(){$(this).hide();});
			$("#tip").remove();  
			$.ajax({
					type: "POST",
					url: "EcGroup.do",
					data: "method=validateGroupName&group_name=" + this.value,
					dataType: "json",
					error: function(request, settings) {alert("数据加载请求失败！"); },
					success: function(isExist) {
						if(isExist == 1) {
							$("#btn_submit").attr("disabled",true);
							$("#s_after").after('<span id="tip" style="color:#FF0000;"><img src="${ctx}/images/reg_error.gif" />&nbsp;对不起，该组织名称已存在！<\/span>');
						} else if(isExist == 0){
							$("#btn_submit").removeAttr("disabled");
							$("#s_after").after('<span id="tip" style="color:#5A8E4A;"><img src="${ctx}/images/reg_success.gif" />&nbsp;恭喜，该部门名称可用。<\/span>');
						} 
					}
			});
		} else {
			$("#tip").remove();
		}
	});

	
	<c:if test="${not empty af.map.add_for_zb}">
	$("#par_id").attr("datatype", "Require").attr("msg", "请选择父组织！");
	</c:if>

	$("#group_name").attr("datatype", "Require").attr("msg", "请填写组织名称！");

	$("#btn_submit").click(function() {
		if(Validator.Validate(this.form, 3)){
			this.form.submit();
		}
	});

	
});




function setOnlyNum() {
	$(this).css("ime-mode", "disabled").attr("t_value", "").attr("o_value", "").bind("dragenter",function(){
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
<jsp:include page="/__analytics.jsp" />
</body>
</html>
