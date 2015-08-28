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
		<div class="rtabcont1">
			<fieldset style="border: 1px solid #ccc; color: #666; padding-left: 10px;">
				<legend>说明</legend>
				<ul>
					<li>1、订单审核流程第1步审核角色一般为业务员，请慎重填写；</li>
					<li>2、下一个审核节点审核语法用来通过条件判断确定当前节点的下一个审核节点，点击<a onclick="$('#next-exp').slideDown();$(this).hide().next().show();" style="color: #00F; cursor: pointer;">展开</a><a
						onclick="$('#next-exp').slideUp();$(this).hide().prev().show();" style="display: none; color: #00F; cursor: pointer;">收起</a>。
						<div id="next-exp" style="display: none; padding-left: 2em;">
							@exp <b>下一个节点表达式语法</b><br />
							<p>NodeId:field1[注释..]-operator-value[注释..],field2-operator-value,...@(and|or);...</p>
							<br /> <b>其中：</b><br /> field_operator_value为条件表达式，多个表达式取交集，具体如下：<br /> 1）field为表单中字段（数据库中的列）名称；<br />
							2）operator为运算符，值有：lt,le,gt,ge,eq,in,between,like,unlike；eq和in的值可以为字母；like和unlike的值可以为字符串；<br /> 3）value为数字或用“|”连接的字符串；<br /> 4）注释可以为空；<br />
							5）field、value以及注释中不能含有以下字符“:”、“@”、“;”、“[”、“]”、“,”、“-”、“|”、“$”以及保留字符；<br /> 6）以下字符为保留字符：“#”、“&”、“*”、“(”、“)”、“{”、“}”、“?”、“/”；<br /> 7）NodeId为“$”表示当前节点为终点；<br />
							8）表达式计算值为从左往右递归计算，当计算结果为“true”时，下面的那个节点不执行。<br /> <b>Examples</b> 1000:x[x1的注释]-gt-1000,x[x2的注释]-lt-10000,y-in-1|2|3,z-eq-helloworld[z选项的注释],...@and;<br />
							2000:如果需要金额大于等于500的订单不走下一个流程写法为：$：money[订单金额]-lt-500[金额数为500]@and<br /> <br />
						</div>
					</li>
				</ul>
			</fieldset>
		</div>
		<div class="rtabcont1">
			<html-el:form action="/KonkaJxcOrderAuditProcessDefine">
				<html-el:hidden property="method" value="save" />
				<html-el:hidden property="id" />
				<html-el:hidden property="mod_id" />
				<html-el:hidden property="queryString" />
				<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
					<tr>
						<th colspan="6">编辑流程</th>
					</tr>
					<tr>
						<td width="15%" nowrap="nowrap" class="title_item"><font color="red">特别提醒：</font></td>
						<td colspan="5"><span><font color="red">角色请按审核步骤由低到高依次添加，否则将会影响流程正常审核！</font></span></td>
					</tr>
					<tr>
						<td width="15%" nowrap="nowrap" class="title_item">客户类型：</td>
						<td colspan="1"><html-el:select property="customer_type" styleId="customer_type">
								<html-el:option value="">请选择...</html-el:option>
								<c:forEach var="cur" items="${konkaCategoryList}">
									<html-el:option value="${cur.c_index}">${cur.c_name}</html-el:option>
								</c:forEach>
							</html-el:select></td>
						<td width="15%" nowrap="nowrap" class="title_item">适用范围：</td>
						<td colspan="3"><html-el:select property="used_field" styleId="used_field">
								<html-el:option value="">--请选择--</html-el:option>
								<html-el:option value="0">正常订单流程</html-el:option>
								<html-el:option value="1">变更订单流程</html-el:option>
								<html-el:option value="2">退货订单流程</html-el:option>
							</html-el:select></td>
					</tr>
					<tr class="title_top">
						<td align="center" nowrap="nowrap" bgcolor="#fff2dc"><span style="color: red">*</span><font class="bigall" title="[由低到高依次对应流程审核步骤]">审核步骤</font></td>
						<td width="30%" align="center" nowrap="nowrap" bgcolor="#fff2dc"><span style="color: red">*</span><font class="bigall">审核角色<br /> <span class="jxcTip">[根据需求选择一个或多个角色]</span></font></td>
						<td align="center" nowrap="nowrap" bgcolor="#fff2dc"><span style="color: red">*</span><font class="bigall">下一个节点表达式<br /></font></td>
						<td align="center" width="10%" bgcolor="#fff2dc"><font class="bigall">审核条件<br /></font></td>
						<td width="10%" align="center" nowrap="nowrap" bgcolor="#fff2dc"><span style="color: red">*</span><font class="bigall" title="[修改订单中产品单价和数量]">修改权限</font></td>
						<td width="5%" align="center" id="addPdInput" title="添加"><img src="${ctx}/styles/jxc/images/+.gif" style="vertical-align: text-bottom;" /></td>
					</tr>
					<tr id="role_audit_row" class="role_audit_row" style="display: none;">
						<!-- hasPdmodel tbody_fhdj-->
						<td align="center"><span id="audit_level_show" class="jxcTip"></span><input type="hidden" name="node_id" /></td>
						<td align="center" nowrap="nowrap"><html-el:text property="role_name" styleId="role_name" style="width:99%;" readonly="true" title="点击即可选择角色" /> <html-el:hidden
								property="role_id" styleId="role_id" /></td>
						<td align="center" nowrap="nowrap"><html-el:text property="next_node" styleId="next_node" style="width:99%;" /></td>
						<td align="center" nowrap="nowrap"><html-el:select property="audit_proc_cond" styleId="audit_proc_cond" style="width:90px">
								<html-el:option value="0">无</html-el:option>
								<html-el:option value="1">前${weeks}（自定义）周零售量减去当前库存大于订单数量</html-el:option>
								<html-el:option value="2">前4周零售量减去当前库存大于订单数量</html-el:option>
								<html-el:option value="3">前6周零售量减去当前库存大于订单数量</html-el:option>
								<html-el:option value="4">前8周零售量减去当前库存大于订单数量</html-el:option>
								<html-el:option value="5">前10周零售量减去当前库存大于订单数量</html-el:option>
							</html-el:select></td>
						<td align="center" nowrap="nowrap"><html-el:select property="is_update_authority" styleId="is_update_authority" style="width:90px">
								<html-el:option value="0">无</html-el:option>
								<html-el:option value="1">有</html-el:option>
							</html-el:select></td>
						<td align="center" title="删除"><img src="${ctx}/styles/jxc/images/x.gif" style="vertical-align: text-bottom;" /></td>
					</tr>
					<tbody id="tbody_andit_role">
						<c:forEach var="cur" items="${orderAuditProcessNodeList}">
							<tr id="role_audit_row" class="role_audit_row">
								<!-- hasPdmodel tbody_fhdj-->
								<td align="center"><span id="audit_level_show" class="jxcTip">${cur.audit_level}</span><input type="hidden" name="node_id" value="${cur.id}" /></td>
								<td align="center" nowrap="nowrap"><html-el:text property="role_name" styleId="role_name" value="${cur.role_name}" style="width:99%;" readonly="true"
										title="点击即可选择角色" /> <html-el:hidden property="role_id" styleId="role_id" value="${cur.role_id}" /></td>
								<td align="center" nowrap="nowrap"><html-el:text property="next_node" styleId="next_node" value="${cur.next_node}" style="width:99%;" /></td>
								<td align="center" nowrap="nowrap"><html-el:select property="audit_proc_cond" styleId="audit_proc_cond" style="width:90px" value="${cur.audit_proc_cond}">
										<html-el:option value="0">无</html-el:option>
										<html-el:option value="1">前四周零售量减去当前库存大于订单数量</html-el:option>
										<html-el:option value="2">前4周零售量减去当前库存大于订单数量</html-el:option>
										<html-el:option value="3">前6周零售量减去当前库存大于订单数量</html-el:option>
										<html-el:option value="4">前8周零售量减去当前库存大于订单数量</html-el:option>
										<html-el:option value="5">前10周零售量减去当前库存大于订单数量</html-el:option>
									</html-el:select></td>
								<td align="center" nowrap="nowrap"><select name="is_update_authority" id="is_update_authority" style="width: 90px">
										<option value="0" ${cur.is_update_authority eq 1 ? 'selected' : ''}>无</option>
										<option value="1" ${cur.is_update_authority eq 1 ? 'selected' : ''}>有</option>
								</select></td>
								<td align="center" title="删除"><img src="${ctx}/styles/jxc/images/x.gif" style="vertical-align: text-bottom;" /></td>
							</tr>
						</c:forEach>
					</tbody>
					<tr>
						<td nowrap="nowrap" class="title_item"><span style="color: red">*</span>流程描述</td>
						<td colspan="5"><html-el:text property="process_desc" styleId="process_desc" size="80" maxlength="200" style="width:99%;" readonly="true" /></span></td>
					</tr>
					<tr>
						<td colspan=6 align="center"><html-el:button property="" value="提交" styleClass="bgButtonSave" styleId="btn_submit" /> &nbsp; <html-el:reset property=""
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
	$("#process_desc").attr("datatype", "Require").attr("msg", "请选择流程标题！");

	var audit_level= 0;//审核级别
	$("#addPdInput").click(function(){
		$("#role_audit_row").clone().appendTo($("#tbody_andit_role")).show();
		
		var lastTR = $("tr:last", "#tbody_andit_role");
		var JQ_role_id= $("#role_id", lastTR);//角色Id
		var JQ_role_name= $("#role_name", lastTR);//角色名称
		var JQ_is_update_authority = $("#is_update_authority", lastTR);//修改权限
		var JQ_selectRole = $("#selectRole", lastTR);//型号
		//var JQ_next_node = $("#next_node",lastTR);//下个审核角色
		
		JQ_role_id.attr("dataType", "Require").attr("msg", "请选择角色！");
		JQ_is_update_authority.attr("dataType", "Require").attr("msg", "请选择修改权限！");
		//JQ_next_node.attr("dataType", "Require").attr("msg", "请填写下个审核角色！");
		

		$("#tbody_andit_role .jxcTip").each(function(i){
			$(this).text("第" + (i + 1) + "步");
		});

		JQ_role_name.click(function(){//添加角色
			
			var selectedIds = JQ_role_id.val();
			var returnValue = window.showModalDialog("KonkaJxcOrderAuditProcessDefine.do?method=list_role&selectedIds=" + selectedIds +"&azaz=" + Math.random(),window,"dialogWidth:450px;status:no;dialogHeight:450px");
			if (!returnValue) returnValue = window.returnValue;
			JQ_role_id.val(returnValue.ids);
			JQ_role_name.val(returnValue.names);

			var role_names = [];
			$("#tbody_andit_role input[name='role_name']").each(function(i){
				role_names[role_names.length] = $(this).val() == '' ? '待定' : $(this).val();
			});
			if(${flag}){
			$("#process_desc").val(role_names.join(" > "));
			}
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

	$("#btn_submit").click(function(){
		if ($("#role_id", "#tbody_andit_role").length == 0) {
	        alert("请至少添加一个角色！");
	        return false;
		}
	
		if (Validator.Validate(this.form, 3)) {
					
			if(confirm("确认您添加的角色顺序？提交后将不能修改！")){
				this.form.submit();
				$("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
				$("#btn_reset").attr("disabled", "true");
				$("#btn_back").attr("disabled", "true");
			}else{
				return false;
			}
		}
		
		return false;
	});
	
	var process_desc = "${af.map.process_desc}";
	if (process_desc == '') {
		var role_names = [];
		$("#tbody_andit_role input[name='role_name']").each(function(i){
			role_names[role_names.length] = $(this).val() == '' ? '待定' : $(this).val();
		});
		$("#process_desc").val(role_names.join(" > ")).after("<div style='color:#F00;'>因上次未保存流程描述，系统自动生成了流程描述，点击保存后即可完成修改。</div>");
	}
});

function isRepeat(){ //判断添加的角色是否重复
	var flag = false;
	var arrays_v = [];
	$("tr", "#tbody_andit_role").each(function(){
		var _thisTr = $(this);
		var role_ids = $("#role_id", _thisTr).val();
		
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
	<jsp:include page="../public_page.jsp" flush="true" />
	<jsp:include page="/__analytics.jsp" />
</body>
</html>
