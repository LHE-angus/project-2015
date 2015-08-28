<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<c:set var="naviString" value="康佳事项维护" />
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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<style type="text/css">
.title_item {
	text-align: right;
}
</style>
</head>
<body>
<div id="navTab" class="tabsPage" style="text-align:left;">
  <div class="tabsPageHeader">
    <div class="tabsPageHeaderContent">
      <!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
      <ul class="navTab-tab">
        <li class="main"><a href="javascript:void(0)"><span class="home_icon">${naviString}</span></a></li>
      </ul>
    </div>
  </div>
  <div class="navTab-panel tabsPageContent" style="width:100%;">
    <div class="page">
      <div class="pageContent">
        <html-el:form action="/manager/KonkaItemManager.do" enctype="multipart/form-data">
          <html-el:hidden property="method" value="save" />
          <html-el:hidden property="mod_id" />
          <html-el:hidden property="id" />
          <html-el:hidden property="queryString" />
          <table width="98%" align="center" class="list">
            <tr>
              <td width="15%" class="title_item"><span style="color: #F00;">*</span>事项：</td>
              <td>
                <html-el:text property="item_content" styleId="item_content" style="width:50%" />
              </td>
            </tr>
            <tr>
              <td class="title_item"><span style="color: #F00;">*</span>负责人：</td>
              <td>
                <html-el:text property="receive_user_name" styleId="receive_user_name" style="width:20%" />
                <span id="message_user" style="display:none;color: red;"></span>
                <html-el:hidden property="receive_user_id" styleId="receive_user_id" />
              </td>
            </tr>
            <tr>
              <td class="title_item"><span style="color: #F00;">*</span>属性1：</td>
              <td>
                <html-el:select property="p_type_1" styleId="p_type_1" style="width:30%">
                  <html-el:option value="">请选择...</html-el:option>
                  <c:forEach items="${propertyList}" var="cur">
                    <c:if test="${cur.p_type eq 0}">
                      <html-el:option value="${cur.p_id}">${fn:escapeXml(cur.p_name)}</html-el:option>
                    </c:if>
                  </c:forEach>
                </html-el:select>
              </td>
            </tr>
            <tr>
              <td class="title_item"><span style="color: #F00;">*</span>属性2：</td>
              <td>
                <html-el:select property="p_type_2" styleId="p_type_2" style="width:30%">
                  <html-el:option value="">请选择...</html-el:option>
                  <c:forEach items="${propertyList}" var="cur">
                    <c:if test="${cur.p_type eq 1}">
                      <html-el:option value="${cur.p_id}">${fn:escapeXml(cur.p_name)}</html-el:option>
                    </c:if>
                  </c:forEach>
                </html-el:select>
              </td>
            </tr>
            <tr>
              <td class="title_item">计划完成时间：</td>
              <td>
                <fmt:formatDate value="${af.map.plan_finish_date}" var="plan_finish_date_"/>
                <html-el:text property="plan_finish_date" size="10" maxlength="10" readonly="true" style="cursor:pointer;width:65px;" onclick="new Calendar(2005, 2030, 0).show(this);" value="${plan_finish_date_}" />
              </td>
            </tr>
            <tr>
              <td class="title_item">排序值：</td>
              <td>
                <html-el:text property="order_value" styleId="order_value" size="4" maxlength="4" />
              </td>
            </tr>
            <tr>
              <td class="title_item">&nbsp;</td>
              <td>
                <html-el:button property="btn_submit" styleId="btn_submit" styleClass="websub" value=" 保存 " />
                <html-el:button property="btn_back" styleId="btn_back" styleClass="websub" value=" 返回 " onclick="history.back();return false;" />
              </td>
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
   $("#item_content").attr("datatype","Require").attr("msg","标题必须填写！");
   $("#receive_user_name").attr("datatype","Require").attr("msg","负责人必须填写！");
   $("#p_type_1").attr("datatype","Require").attr("msg","属性1必须填写！");
   $("#p_type_2").attr("datatype","Require").attr("msg","属性2必须填写！");
   $("#order_value").attr("datatype","Range").attr("min","-1").attr("max","10000").attr("msg","排序值必须在0~9999之间").focus(setOnlyNum);

	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 3)){
			if($("#receive_user_id").val() == ""){
				alert("负责人名称输入不正确！！");
				return null;
			}
		
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_reset").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
            
			this.form.submit();
		}
	});
	
	$("#receive_user_name").blur(function(){
		$("#receive_user_id").val("");
		var user_name = $(this).val();
		
		if("" != user_name){
			$.ajax({
				type : "post",
				url : "KonkaItemManager.do?method=validateUserName",
				data:"user_name=" + user_name,
				error:function(request, settings) {alert("数据加载请求失败！"); },
				success:function(date){
					var user_id = date.user_id;
					if("" == user_id){
						$("#message_user").html("查无此人！").attr("style","display:;color: red;");
					}else{
						$("#message_user").html("负责人输入正确！").attr("style","display:;color: #00DB00;");
						$("#receive_user_id").val(user_id);
					}
				}
			});
		}else{
			$("#message_user").attr("style","display:none;");
		}
	});

	$("#receive_user_name").blur();
})

function setOnlyNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		if(this.value.length == 0) this.value = 0;
	});
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
