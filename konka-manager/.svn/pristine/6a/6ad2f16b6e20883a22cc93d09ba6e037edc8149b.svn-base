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
<link href="${ctx}/styles/customer/multiselect/jquery.multiselect2side.css" rel="stylesheet" type="text/css" />
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
    <html-el:form action="/admin/KonkaPeArticleUserGroup">
      <html-el:hidden property="group_id" value="${af.map.group_id}" />
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <html-el:hidden property="dept_value" value="${dept_value}" styleId="dept_value" />
      <html-el:hidden property="returnUrl" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item">群组名称：</td>
          <td><html-el:text property="group_name" styleId="group_name" style="width:400px;" size="30" maxlength="50"/> <span style="color:red">*</span></td>
        </tr>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item">组员：</td>
          <td>
          		<div style="padding-bottom: 15px;padding-top: 5px;">
          			角色：<select name="role_id" id="role_id" multiple="multiple">
          					<option value="">==请选择==</option>
              				<c:forEach items="${peRoleUserList}" var="cur1" varStatus="vs"> 
              					<option value="${cur1.role_id}">${cur1.role_name}</option>
              				</c:forEach>
            			</select>
            			<br />
            			<br />
            		用户名： <html-el:text property="user_name_like" styleId="user_name_like" /> &nbsp;&nbsp;<input type="button" class="but1" value="检索" onclick="getQueryUserNames()" />
          		</div>	
          		<div id="sel">
				      <select name="group_user_" id="group_user" multiple="multiple" size="8">
				      	<c:forEach var="cur" items="${peProdUserList}">
	                  		<option value="${cur.map.user_id}">${cur.map.user_names}</option>
	                	</c:forEach>
				      </select>
				      <html-el:hidden property="group_user_ids" styleId="group_user_ids" />
				</div></td>
        </tr>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item">群组描述：</td>
          <td><html-el:textarea property="group_desc" styleId="group_desc" style="width:65%;height:120px;resize:none;" /></td>
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
<script type="text/javascript" src="${ctx}/styles/customer/multiselect/jquery.multiselect2side.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#group_name").attr("dataType", "Require").attr("msg", "请填写群组名称！");
	$("#group_desc").attr("dataType", "Limit").attr("max", "200").attr("msg", "群组描述不能超过200个文字");
	//处理职务多选，Ren,zhong，2013-06-25
	$("#group_user").multiselect2side({
	    selectedPosition: 'right',
	    moveOptions: false,
		labelsx: '待选区',
		labeldx: '已选区'
   	});
   	
   	//回显职务
   	if('${konkaPeArticleGUsersList}' != null){
	$("#group_user_ms2side__sx").val("");//2013-06-27，解决总是选中【系统管理员】的bug
	  var arrs = new Array();
	   <c:forEach var="cur" items="${konkaPeArticleGUsersList}">
			 $("#group_user_ms2side__sx option[value='${cur.group_user_id}']").attr("selected", true).dblclick();
			 arrs.push("${cur.group_user_id}");
		</c:forEach>
		$("#group_user_ids").val(arrs);
   	}
  //职务
	$("#role_id").multiselect({
		noneSelectedText: '==请选择==',
		selectedList: 1,
		multiple: false,
		minWidth:220,
		click: function(event, ui){
	       if(ui.value != ""){
	       }
		}
	}).multiselectfilter();
	 
	 $("#send").click(function(){

		//处理、验证职务多选，Renzhong, 2013-06-25，Begin
			var num = $("#group_user_ms2side__dx").find("option").length;
			if (0 == num || null == num) {
				alert("请选择组员！");
				$("#group_user_ms2side__dx").focus();
				return false;
			}

			var arrs = new Array();
			var obj = document.forms[0].group_user_ms2side__dx;
			for(var i = 0; i < obj.length; i++){
				if(arrs.toString().indexOf(obj.options[i].value + "##" + obj.options[i].text) < 0){
					arrs.push(obj.options[i].value + "##" + obj.options[i].text);
				}
			}
			//var group_user = arrs.join(",");
			$("#group_user_ids").val(arrs);
			
			var isSubmit = Validator.Validate(this.form, 2);
			if (isSubmit) {
				$(":button").attr("disabled", "true");
				$(":reset").attr("disabled", "true");
				this.form.submit();
			}
		});

});

function getQueryUserNames() {
	$("#group_user_ms2side__sx").empty();
   	$.ajax({
		type: "POST",
		cache: false,
		url: "KonkaPeArticleUserGroup.do",
		data: "method=" + "getQueryUserNames" + "&user_name_like=" + $("#user_name_like").val() + "&role_id=" + $("#role_id").val() + "&dept_value=" + $("#dept_value").val(),
		dataType: "json",
		error: function(request, settings){alert("数据异常，请联系管理员");},
		success: function(data) {
			if (data.length >= 1) {
				for(var i = 0; i <= data.length - 1; i++) {
					var opt = new Option( data[i].map.user_names,data[i].map.user_id); 
					$("#group_user_ms2side__sx").get(0).options.add(opt);
				}
			}
		}
	});
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
