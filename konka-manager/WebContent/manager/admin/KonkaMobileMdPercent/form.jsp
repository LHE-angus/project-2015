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
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
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
    <html-el:form action="/admin/KonkaMobileMdPercent">
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
	   			<html-el:optionsCollection label="dept_name" name="konkaDeptList" value="dept_id"/>
   			</html-el:select>
          </td>
        </tr>
        </c:if>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item"><span style="color:red">*</span>型号名称：</td>
          <td>
	 	  <html-el:select property="size_id" styleId="size_id"  value="${fnx:substring_sii(af.map.md_name, 3, 5)}">
			<html-el:option value="">-选择尺寸-</html-el:option>
			<html-el:optionsCollection label="name" name="sizeList" value="name"/>
      	  </html-el:select>
           &nbsp;
            <select name="pd_id" id="pdIds" multiple="multiple">
		          		
		   </select></td>
        </tr>
        <tr>
          <td height="28" nowrap="nowrap" class="title_item"><span style="color:red">*</span> 类型：</td>
          <td><html-el:select property="type" styleId="type">
    			<html-el:option value="0">比例</html-el:option>
    			<html-el:option value="1">定额</html-el:option>
    		</html-el:select>
          </td>
        </tr>
        <tr>
          <td height="28" nowrap="nowrap" class="title_item"><span style="color:red">*</span> 比例：</td>
          <td><html-el:text property="percent" styleId="percent" style="width:150px;" size="30" maxlength="12"/></td>
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
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}
	
	$("#percent").attr("dataType" , "Require").attr("msg" , "请填写比例！");
	$("#pdIds").attr("dataType" , "Require").attr("msg" , "请选择型号！");
	<c:if test="${empty af.map.id and isHeadquarters}">
		$("#dept_id_temp").attr("dataType" , "Require").attr("msg" , "请请选择分公司！");
	</c:if>
	$("#percent").attr("focus", setOnlyNum);

	$("#dept_id_temp").change(function(){
		$("#dept_id").val($(this).val());
	});

	var pd_id_value = '${af.map.pd_id}';
	$("#pdIds").multiselect({
		noneSelectedText: '==请选择==',
		selectedList: 1,
		multiple: false,
		minWidth:220,
		click: function(event, ui){
	       if(ui.value != ""){
	       }
		}
	}).multiselectfilter(); 

	if(pd_id_value.length > 0){
		category_id_chg();
	}
	

	$("#size_id").change(function(){
		$("#pdIds").multiselect({
			noneSelectedText: '==请选择==',
			selectedList: 1,
			multiple: false,
			minWidth:220,
			click: function(event, ui){
		       if(ui.value != ""){
			      // alert(ui.value);
		       }
			}
		}).multiselectfilter();
		$("#pdIds").empty();
		category_id_chg();

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

//类别-连动-型号
function category_id_chg(){
		$.ajax({
			type: "POST" , 
			url: "${ctx}/manager/admin/KonkaMobileSailDatas.do" , 
			data:"method=getModel&size_id="+$('#size_id').val()+"&n=" + Math.random(),
			dataType: "json" , 
	        async: true, 
	        error: function (request, settings) {alert(" 数据加载请求失败！ "); }, 
	        success: function (result) {
				if (result.state == 1) {
					for ( var x in result.list) {
						$("#pdIds").append('<option value="' + result.list[x].pd_id + '">' + result.list[x].md_name + '</option>');
					}
					if (""!= "${af.map.pd_id}") {//修改回显
						$("#pdIds").val("${af.map.pd_id}");
					}
					$("#pdIds").multiselect("refresh");
				}else{
					$("#pdIds").multiselect("refresh");
				}
	        }
		});

}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
