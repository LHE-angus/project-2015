<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/KonkaMobilePayment">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="dept_id" value="${af.map.dept_id}" styleId="dept_id" />
      <html-el:hidden property="id" value="${af.map.id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <html-el:hidden property="returnUrl" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <c:if test="${empty af.map.id and isHeadquarters}">
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item"><span style="color:red">*</span>分公司/办事处：</td>
          <td>
         	<html-el:select property="dept_id_temp" styleId="dept_id_temp" style="width:150px;">
         	    <html-el:option value="">请选择...</html-el:option>
         		<c:forEach items="${konkaDeptList}" var="cur">
         			<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
         		</c:forEach>
   			</html-el:select>
          </td>
        </tr>
        </c:if>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item"><span style="color:red">*</span>基本工资：</td>
          <td><html-el:text property="base_payment" styleId="base_payment" style="width:150px;" size="30" maxlength="12"/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><label>
            <input class="but4" type="button" name="Submit4" value="提交" id="send"/>
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
            </label>
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}
	
	$("#base_payment").attr("dataType" , "Require").attr("msg" , "请填写基本工资！");
	<c:if test="${empty af.map.id and isHeadquarters}">
		$("#dept_id_temp").attr("dataType" , "Require").attr("msg" , "请请选择分公司！");
	</c:if>
	$("#base_payment").attr("focus", setOnlyNum);

	$("#dept_id_temp").change(function(){
		$("#dept_id").val($(this).val());
	});
	
	 $("#send").click(function(){
			var isSubmit = Validator.Validate(this.form, 2);
			if (isSubmit) {
				$(":button").attr("disabled", "true");
				$(":reset").attr("disabled", "true");
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
<jsp:include page="/__analytics.jsp" />
</body>
</html>
