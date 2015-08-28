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
    <html-el:form action="/admin/KonkaYjglPlan" method="post">
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <c:set var="readonly" value="${empty af.map.id ? false : true}"/>
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>分公司：</td>
          <td width="88%" align="left"><html-el:select property="dept_id" styleId="dept_id" style="width:120px;"  disabled="${readonly}">
	          				<html-el:option value="">请选择</html-el:option>
	          				<c:forEach items="${deptList}" var="cur">
	          					<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
	          				</c:forEach>
	          			</html-el:select>
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>年度：</td>
          <td width="88%" align="left"><html-el:select property="plan_year" styleId="plan_year" style="width:120px;"  disabled="${readonly}">
	          				<html-el:option value="">请选择</html-el:option>
	          					<html-el:option value="2013">2013</html-el:option>  
	          					<html-el:option value="2014">2014</html-el:option>
	          					<html-el:option value="2015">2015</html-el:option>
	          					<html-el:option value="2016">2016</html-el:option>
	          					<html-el:option value="2017">2017</html-el:option>
	          					<html-el:option value="2018">2018</html-el:option>
	          			</html-el:select></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>额度类型：</td>
          <td width="88%" align="left"><html-el:select property="type_id" styleId="type_id" style="width:120px;" disabled="${readonly}">  
	          				<html-el:option value="">请选择</html-el:option>
	          				<c:forEach items="${typeList}" var="cur">
	          					<html-el:option value="${cur.type_id}">${cur.type_name}</html-el:option>
	          				</c:forEach>
	          			</html-el:select></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>额度：</td>
          <td width="88%" align="left"><html-el:text property="plan_ed" styleId="plan_ed" size="10" maxlength="10"/>
          </td>
        </tr>
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>计划上样时间：</td>
          <td width="88%" align="left">
          <c:if test="${empty af.map.id}">
          <fmt:formatDate var="_plan_cy_date" value="${af.map.plan_cy_date}" pattern="yyyy-MM-dd" />  
          <input  name="plan_cy_date" id="plan_cy_date"   value="${_plan_cy_date}" size="9" maxlength="10" readonly="readonly" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
          </c:if>
          <c:if test="${not empty af.map.id}">
            <fmt:formatDate  value="${af.map.plan_cy_date}" pattern="yyyy-MM-dd" />
          </c:if>	
          </td>   
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">备注：</td>
          <td width="88%" align="left"> <html-el:text property="remark" styleId="remark"  maxlength="200" style="width:70%"></html-el:text>
          </td>
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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>  
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#dept_id").attr("datatype", "Require").attr("msg", "请选择分公司");
	$("#plan_year").attr("datatype", "Require").attr("msg", "请选择年份");
	$("#plan_ed").attr("datatype", "Require").attr("msg", "请填写额度");
	<c:if test="${empty af.map.id}">
	$("#plan_cy_date").attr("datatype", "Require").attr("msg", "请选择样机上样时间");
	</c:if>  

	$("#type_id").attr("datatype", "Require").attr("msg", "请选择额度类型");
	$("#plan_ed" ).attr("focus",setOnlyNum); 
	
	$("#btn_submit").click(function(){

		<c:if test="${empty af.map.id}">

		var up_date = $("#plan_cy_date").val();
		var now_date = '${date}';
		
		var _up_date = up_date.replace(/-/g,"").replace(/:/g,"").replace(/ /g,"");
		var _now_date = now_date.replace(/-/g,"").replace(/:/g,"").replace(/ /g,"");
		
		if(_up_date <_now_date){  
			alert("计划上样时间只能是今天或者今天以后");   
			return false; 
		}
		</c:if>

		
		var plan_ed = $("#plan_ed").val(); 
		if(plan_ed.length > 10){
			alert("额度的长度不能超过10位");
			return false;
		}  
		
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

//正数
function setOnlyPosNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}
		if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;
}

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
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}
		if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
