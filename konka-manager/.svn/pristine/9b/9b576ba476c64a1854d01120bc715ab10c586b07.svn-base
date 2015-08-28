<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div style="width: 100%">
		<div class="oartop">
			<img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align: text-top;" /> 当前位置：${naviString}
		</div>
		<div class="rtabcont1" style="overflow: auto;">
			<html-el:form action="/admin/GcxmAuditProcess">
				<html-el:hidden property="method" value="save" />
				<html-el:hidden property="id" />
				<html-el:hidden property="mod_id" />
				<html-el:hidden property="queryString" />
				<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
					<tr>
						<th colspan="4">编辑流程</th>
					</tr>
					<tr>
						<td width="15%" nowrap="nowrap" class="title_item"><font color="red">特别提醒：</font></td>
						<td colspan="3"><span><font color="red">角色请按审核步骤由低到高依次添加，否则将会影响流程正常审核！</font></span></td>
					</tr>
					<tr>
					    <td width="15%" nowrap="nowrap" class="title_item"><span style="color: red">*</span>流程名称：</td>
					    <td width="35%"><html-el:text property="process_name" styleId="process_name" maxlength="30" ></html-el:text></td>
						<td width="25%" nowrap="nowrap" class="title_item"><span style="color: red">*</span>审核类型：</td>
						<td width="25%"><html-el:select property="audit_type" styleId="audit_type">
								<html-el:option value="">请选择...</html-el:option>
								  <html-el:option value="1001">工程项目审核</html-el:option>
              					  <html-el:option value="1002">工程项目报价审核</html-el:option>
							</html-el:select></td>
						
					</tr>
					<tr class="title_top">
						<td align="center" nowrap="nowrap" bgcolor="#fff2dc"><span style="color: red">*</span><font class="bigall" title="[由低到高依次对应流程审核步骤]">审核步骤</font></td>
						<td width="30%" align="center" nowrap="nowrap" bgcolor="#fff2dc" colspan="2"><span style="color: red">*</span><font class="bigall">审核角色<br /> <span class="jxcTip">[根据需求选择一个角色]</span></font></td>
						<td width="5%" align="center" id="addPdInput" title="添加"><img src="${ctx}/styles/jxc/images/+.gif" style="vertical-align: text-bottom;" /></td>
					</tr>
					<tr id="role_audit_row" class="role_audit_row" style="display: none;">
						<!-- hasPdmodel tbody_fhdj-->
						<td align="center"><span id="audit_level_show" class="jxcTip"></span><input type="hidden" name="node_id" /></td>  
						<td align="center" nowrap="nowrap" colspan="2"><html-el:text property="audit_role_name" styleId="audit_role_name" style="width:80%;" readonly="true" title="点击即可选择角色" /> 
						<html-el:hidden property="audit_role_id" styleId="audit_role_id" /></td>
						<td align="center" title="删除"><img src="${ctx}/styles/jxc/images/x.gif" style="vertical-align: text-bottom;" /></td>
					</tr>
					<tbody id="tbody_andit_role">
						<c:forEach var="cur" items="${gcxmAuditProcessNodeList}" varStatus="vs">
							<tr id="role_audit_row" class="role_audit_row">
								<td align="center"><span id="audit_level_show" class="jxcTip">${vs.count}</span><input type="hidden" name="node_id" value="${cur.id}" /></td>
								<td align="center" nowrap="nowrap" colspan="2"><html-el:text property="audit_role_name" styleId="audit_role_name" value="${cur.audit_role_name}" style="width:99%;" readonly="true"
										title="点击即可选择角色" /> <html-el:hidden property="audit_role_id" styleId="audit_role_id" value="${cur.audit_role_id}" /></td>
								<td align="center" title="删除"><img src="${ctx}/styles/jxc/images/x.gif" style="vertical-align: text-bottom;" /></td>
							</tr>
						</c:forEach>
					</tbody>
					<tr>
						<td nowrap="nowrap" style="font-weight: bold;" align="center">备注</td>
						<td colspan="3"><html-el:text property="memo" styleId="memo" size="80" maxlength="200" style="width:99%;" /></td>
					</tr>
					<tr>
						<td colspan="4" align="center"><html-el:button property="" value="提交" styleClass="bgButtonSave" styleId="btn_submit" /> &nbsp; <html-el:reset property=""
								value="重填" styleClass="bgButtonBack" styleId="btn_reset" /> &nbsp; <html-el:button property="" value="返 回" styleClass="bgButtonBack" styleId="btn_back"
								onclick="history.back();" /></td>
					</tr>
				</table>
			</html-el:form> 
		</div>
	</div>
	<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
	<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/commons.plugin.jxc.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/jquery-ui/external/bgiframe/jquery.bgiframe.js"></script>
	<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#process_name").attr("datatype", "Require").attr("msg", "请填写流程名称！");
	$("#audit_type").attr("datatype", "Require").attr("msg", "请选择审核类型！");

    var id = '${af.map.id}';
    if(id == ''||id == null){   
	$("#addPdInput").click(function(){
		$("#role_audit_row").clone().appendTo($("#tbody_andit_role")).show();
		
		var lastTR = $("tr:last", "#tbody_andit_role");
		var JQ_role_id= $("#audit_role_id", lastTR);//角色Id
		var JQ_role_name= $("#audit_role_name", lastTR);//角色名称
		
		JQ_role_id.attr("dataType", "Require").attr("msg", "请选择角色！");
		

		$("#tbody_andit_role .jxcTip").each(function(i){
			$(this).text("第" + (i + 1) + "步");
		});

		JQ_role_name.click(function(){//添加角色
			
			var selectedIds = JQ_role_id.val();
			var returnValue = window.showModalDialog("GcxmAuditProcess.do?method=list_role&selectedIds=" + selectedIds +"&azaz=" + Math.random(),window,"dialogWidth:450px;status:no;dialogHeight:450px");
			if (!returnValue) returnValue = window.returnValue;
			JQ_role_id.val(returnValue.ids);
			JQ_role_name.val(returnValue.names);

			var role_names = [];
			$("#tbody_andit_role input[name='audit_role_name']").each(function(i){
				role_names[role_names.length] = $(this).val() == '' ? '待定' : $(this).val();
			});
			
		});
	

		//删除按钮
	   	$("td:last", lastTR).click(function (){
	    	$(this).parent().remove();
	    	
	    	$("#tbody_andit_role .jxcTip").each(function(i){
	    		$(this).text("第" + (i + 1) + "步");
			});
	    	
	    	//iframe高度自适应
	    	window.parent.resizeFrameHeight('mainFrame', 3);
	    	
	    }).css("cursor", "pointer");

		//iframe高度自适应
		window.parent.resizeFrameHeight('mainFrame', 3);
		
	}).css("cursor", "pointer");
	
    }

	

	$("#btn_submit").click(function(){
		if ($("#audit_role_id", "#tbody_andit_role").length == 0) {
	        alert("请至少添加一个角色！");
	        return false;
		}

		if(isRepeat()){
			alert("审核角色重复！");	  
			return false;
		}
	
		if (Validator.Validate(this.form, 3)) {
			if(confirm("确认您添加的角色顺序？流程一旦提交后将不能修改！")){
				this.form.submit();
				$("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
				$("#btn_reset").attr("disabled", "true");
				$("#btn_back").attr("disabled", "true");
			}else{
				return false;
			}
		}
		
	});
	
	
});

function isRepeat(){ //判断添加的角色是否重复
	var flag = false;
	var arrays_v = [];
	$("tr", "#tbody_andit_role").each(function(){
		var _thisTr = $(this);
		var role_ids = $("#audit_role_id", _thisTr).val();
		
		if ("" == role_ids) {
			return false;
		} else {
			for(var r = 0;r < role_ids.split(",").length;r++){
				arrays_v.push(role_ids.split(",")[r]);
			}
		}
	});
	var sort_arrays_v = arrays_v.sort();
	for(var i = 0; i < sort_arrays_v.length; i++) {
		if (sort_arrays_v[i] == sort_arrays_v[i + 1]) {
			flag = true;
			break;
		}  
    }
	return flag;
}
//]]></script>
	<jsp:include page="/__analytics.jsp" />
</body>
</html>
