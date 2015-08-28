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
<link href="${ctx}/styles/customer/multiselect/jquery.multiselect2side.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
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
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
  <html-el:form action="/admin/PeRoleInfo">
     <html-el:hidden property="method" value="save" />
     <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
     <html-el:hidden property="tree_param" value="${tree_param}" />
     <html-el:hidden property="is_add_true" value="${af.map.is_add_true}" />
     <html-el:hidden property="returnUrl" />
     <html-el:hidden property="queryString" />
     <div align="left">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="datagrid">
          <tr class="oartop">
              <td colspan="2">职务编辑</td>    
          </tr>
          <c:set value="${false}" var="can_add_role_id" />
          <c:forEach var="cur" items="${roleInfoList}">
          	<c:if test="${cur.role_id eq 10}"><c:set value="${true}" var="can_add_role_id" /></c:if>
          </c:forEach>
          <tr>
              <td height="28" width="15%" nowrap="nowrap" class="title_item"><span class="required">[必填]</span>职务ID：</td>
              <td>
	              <c:if test="${empty af.map.role_id}">
	              	<html-el:text property="role_id" styleId="role_id" size="8" maxlength="6" /> 注：系统职务请填写10000以下的数字，分公司职务请填写10000以上数字。
	              	<html-el:hidden property="add_role_with_role_id" value="1" />
	              </c:if>
	              <c:if test="${not empty af.map.role_id}">
	              	${af.map.role_id}<html-el:hidden property="role_id" />
	              </c:if>
              </td>
          </tr>
          	<tr>
              <td height="28" width="15%" nowrap="nowrap" class="title_item">分公司：</td>
              <td><c:if test="${is_admin}"> 
              <c:if var="sys_role" test="${not empty af.map.role_id and af.map.role_id lt 10000}">系统职务</c:if>
              <c:if test="${not sys_role}">
                  <html-el:select property="dept_id" styleId="dept_id">
                      <html-el:option value="">系统职务</html-el:option>
                    <c:forEach items="${konkaDeptList}" var="cur">
                      <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
                    </c:forEach>
                  </html-el:select>
              </c:if>
                  &nbsp; </c:if>
                  <c:if test="${not is_admin}"> ${af.map.dept_name} <html-el:hidden property="dept_id" />
                  <html-el:hidden property="dept_sn" /></c:if></td>
          </tr>
          
          <c:if test="${is_admin}">
          <tr>
              <td height="28" width="15%" nowrap="nowrap" class="title_item">数据级别：</td>
              <td>
                  <html-el:select property="d_level" styleId="d_level">
                      <html-el:option value="">请选择</html-el:option>
                      <html-el:option value="9">多媒体事业部及下级</html-el:option>
                      <html-el:option value="8">分公司及下级</html-el:option>
                      <html-el:option value="7">所在部门及下级</html-el:option>
                      <html-el:option value="0">个人用户(无部门数据)</html-el:option>
                  </html-el:select>
              </td>
          </tr>
          </c:if>
          
          <tr>
              <td height="28" width="15%" nowrap="nowrap" class="title_item"><span class="required">[必填]</span>职务名称：</td>
              <td><html-el:text property="role_name" styleId="role_name"/></td>
          </tr>
          <c:if test="${af.map.role_id gt 1000}">
	          <tr>
	          <td height="28" width="15%" nowrap="nowrap" class="title_item">可以查看数据的部门：</td>
	          <td><c:if test="${is_admin}">
	          		<div style="padding-bottom: 15px;padding-top: 5px;">
	          			分公司：<select name="dept_ids" id="dept_ids">
	          					<option value="">==请选择==</option>
	              				<c:forEach items="${konkaDeptList}" var="cur1" varStatus="vs"> 
	              					<option value="${cur1.dept_id}">${cur1.dept_name}</option>
	              				</c:forEach>
	            			</select>&nbsp;&nbsp;<input type="button" class="but1" value="检索" onclick="getQueryDeptNames()" />
	          		</div></c:if>	
	          		<div id="sel">
					      <select name="role_dept_" id="role_dept" multiple="multiple" size="8">
					      	<c:forEach var="cur" items="${kTreeList}">
		                  		<option value="${cur.dept_id}">${cur.dept_name}</option>
		                	</c:forEach>
					      </select>
					      <html-el:hidden property="role_dept_ids" styleId="role_dept_ids" />
					</div></td>
	        </tr>
        </c:if>
          <tr>
               <td nowrap="nowrap" height="28" class="title_item">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;职务描述：</td>
               <td>
                  <html-el:textarea property="role_desc" styleId="role_desc" cols="5" style="width:450px;height:70px;" />
          	<div id="info_tip" style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div>
               </td>
          </tr>
          <tr>
          		<td></td>
               <td colspan="1" align="left">        
                  <html-el:button property="" value="提 交" styleClass="but4" styleId="btn_submit" />
                  <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();" style="margin:0 0 0 10px" />
               </td>
         </tr>
      </table>
    </div>
    </html-el:form>
  </div>
  <div class="clear"></div>
  
    <div class="rtabcont1">
    <fieldset style="border:1px solid #ccc; color:#666;padding-left:10px;">
      <legend>说明</legend>
      <ul style="margin-left:20px;">
        <li>1.职务ID说明，其中0-30之间，200-300之间为总部预留职务ID，请勿增加；    </ul>
    </fieldset>
  </div>
  
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/multiselect/jquery.multiselect2side.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}
	
	$("#role_id" ).attr("dataType" , "Require").attr("msg" , "请填写职务ID！");
	$("#role_name" ).attr("dataType" , "Require").attr("msg" , "请填写职务名称！");

	var role_id = '${af.map.role_id}';

	if(role_id > 1000){
		//多部门
		$("#role_dept").multiselect2side({
		    selectedPosition: 'right',
		    moveOptions: false,
			labelsx: '待选区',
			labeldx: '已选区'
	   	});
	
		//回显部门
	   	if('${kDataLevelList}' != null){
		$("#role_dept_ms2side__sx").val("");
		  var arrs = new Array();
		   <c:forEach var="cur" items="${kDataLevelList}">
				 $("#role_dept_ms2side__sx option[value='${cur.dept_id}']").attr("selected", true).dblclick();
				 arrs.push("${cur.dept_id}");
			</c:forEach>
			$("#role_dept_ids").val(arrs);
	   	}
	}
	
	$("#btn_submit").click(function(){
		
		if(role_id > 1000){
			var arrs = new Array();
			var obj = document.forms[0].role_dept_ms2side__dx;
			for(var i = 0; i < obj.length; i++){
				if(arrs.toString().indexOf(obj.options[i].value + "##" + obj.options[i].text) < 0){
					arrs.push(obj.options[i].value + "##" + obj.options[i].text);
				}
			}
	
			$("#role_dept_ids").val(arrs);
		}
		
		if(Validator.Validate(this.form, 3)){
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_reset").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});

	$("#role_desc").textbox({
		maxLength: 150,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_tip").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_tip").slideUp("normal");
	});
	
	// 验证角色是否存在
	$("#role_id").blur(function(){
		var role_id = $("#role_id").val();
		if (role_id == "") {
			return;
		}
		
		if (!/^\d+$/.test(role_id)) {
			alert("职务ID必须填入数字");
			$(this).focus();
			return;
		}
		
		$.ajax({
			type: "POST",
			url: "PeRoleInfo.do",
			data: "method=validateRoleId&role_id=" + $(this).val(),
			dataType: "json",
			error: function(request, settings) {
				alert("检查用户名重复失败，请稍候再次尝试。");
				$(this).css("background-color", "#ddcc00");
				$(this).focus();
			},
			success: function(oper) {
				if (oper.result) {
					alert("该职务ID [" + role_id + "] 已存在！");
					$("#btn_submit").attr("disabled", "disabled");
					$("#role_id").val("").focus();
					return;
				} else {
					$("#btn_submit").removeAttr("disabled");
				}
			}
		});
	});	
});

function getQueryDeptNames() {
	$("#role_dept_ms2side__sx").empty();
   	$.ajax({
		type: "POST",
		cache: false,
		url: "PeRoleInfo.do",
		data: "method=" + "getQueryDeptNames" + "&dept_id=" + $("#dept_ids").val(),
		dataType: "json",
		error: function(request, settings){alert("数据异常，请联系管理员");},
		success: function(data) {
			if (data.length >= 1) {
				for(var i = 0; i <= data.length - 1; i++) {
					var opt = new Option( data[i].dept_name,data[i].dept_id); 
					$("#role_dept_ms2side__sx").get(0).options.add(opt);
				}
			}
		}
	});
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>