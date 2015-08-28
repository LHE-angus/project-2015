<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<c:set var="naviString" value="康佳事项完成情况" />
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
        <html-el:form action="/manager/KonkaItemFinish.do" enctype="multipart/form-data">
          <html-el:hidden property="queryString" styleId="queryString" />
          <html-el:hidden property="method" value="save" />
          <html-el:hidden property="mod_id" />
          <html-el:hidden property="id" />
          <br />
          <%@ include file="/commons/pages/messages.jsp" %>
          <table width="100%" align="center" class="list">
            <tr>
              <td width="15%" nowrap="nowrap" class="title_item" align="right">事项内容：</td>
              <td width="85%">${fn:escapeXml(af.map.item_content)}</td>
            </tr>
            <tr>
              <td width="15%" nowrap="nowrap" class="title_item" align="right">负责人：</td>
              <td>${fn:escapeXml(af.map.receive_user_name)}</td>
            </tr>
            <tr>
              <td width="15%" nowrap="nowrap" class="title_item" align="right">计划完成时间：</td>
              <td width="85%">
                <fmt:formatDate value="${af.map.plan_finish_date}" pattern="yyyy-MM-dd" />
              </td>
            </tr>
            <tr>
              <td width="15%" nowrap="nowrap" class="title_item" align="right"><span style="color: #F00;">*</span>计划完成百分比：</td>
              <td width="85%">
                <html-el:text property="plan_finish_rate" styleId="plan_finish_rate" size="5" maxlength="5"/>
                %</td>
            </tr>
            <tr>
              <td width="15%" nowrap="nowrap" class="title_item" align="right"><span style="color: #F00;">*</span>是否已完成：</td>
              <td width="85%">
                <html-el:select property="is_finished">
                  <html-el:option value="0">未完成</html-el:option>
                  <html-el:option value="1">已完成</html-el:option>
                </html-el:select>
              </td>
            </tr>
            <tr>
              <td width="15%" nowrap="nowrap" class="title_item" align="right"><span style="color: #F00;">*</span>完成情况：</td>
              <td width="85%">
                <html-el:textarea property="finish_status" styleId="finish_status"></html-el:textarea>
              </td>
            </tr>
            <tr>
              <td width="15%" nowrap="nowrap" class="title_item" align="right">属性1：</td>
              <td width="85%">${fn:escapeXml(af.map.map.p_type1_name)}</td>
            </tr>
            <tr>
              <td width="15%" nowrap="nowrap" class="title_item" align="right">属性2：</td>
              <td width="85%">${fn:escapeXml(af.map.map.p_type2_name)}</td>
            </tr>
            <tr>
              <td width="15%" nowrap="nowrap" class="title_item" align="right">添加时间：</td>
              <td width="85%">
                <fmt:formatDate value="${af.map.add_time}" pattern="yyyy-MM-dd" />
              </td>
            </tr>
            <tr>
              <td width="15%" nowrap="nowrap" class="title_item" align="right">更新时间：</td>
              <td width="85%">
                <fmt:formatDate value="${af.map.last_update_time}" pattern="yyyy-MM-dd" />
              </td>
            </tr>
            <tr>
              <td class="title_item">&nbsp;</td>
              <td>
                <html-el:button property="btn_submit" styleId="btn_submit" value=" 保存 " />
                <html-el:button property="btn_back" styleId="btn_back" value=" 返回 " onclick="history.back();" />
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
   $("#plan_finish_rate").attr("datatype","Require").attr("msg","请填写计划完成百分比");
   $("#finish_status" ).attr({"dataType":"Limit","min":"1","max":"250","msg":"请填写填写完成情况且字数少于250个"});
   $("#plan_finish_rate").focus(setOnlyNum);

   $("#p_name").focus(function(){
	   $("#p_name_exist").hide();
   });
   $("#p_name").blur(function(){
		var p_name = $("#p_name").val();;
		var p_type = $("#p_type").val();
		if(p_name == ''){
			$("#p_name_exist").hide();
			$("#btn_submit").attr("disabled", "");
			return false;
		} 
		$.ajax({
			type: "POST",
			url: "KonkaItemFinish.do",
			data: "method=validateProperty&p_name=" + p_name+"&p_type="+p_type,
			dataType: "json",
			error: function(request, settings) {
				alert("检查库存数量失败，请稍候再次尝试。");
				$("#user_name_exist_error").show();
				$(this).css("background-color", "#ddcc00");
				$(this).focus();
			},
			success: function(oper) {
				if(oper.result){
					$("#p_name_exist").hide();
					$("#btn_submit").attr("disabled", "");
				}else{
					$("#p_name_exist").show();
					$("#btn_submit").attr("disabled", "true");
				}
			}
		});
   });

   var f = document.forms[0];
	$("#btn_submit").click(function(){
		var isSubmit = Validator.Validate(f, 3);
		if (isSubmit){
			$(":submit").attr("value", "正在提交...").attr("disabled", "true");
			$(":button").attr("disabled", "true");
			$(":reset").attr("disabled", "true");
			f.submit();
		}
	});	
});

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
		if(this.value > 100){
			alert("分数值超出范围（0~100），请重新填写！");
			this.value = '';
		}
	});
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
