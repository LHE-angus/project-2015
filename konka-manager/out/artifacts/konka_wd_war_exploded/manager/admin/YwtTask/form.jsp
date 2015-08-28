<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
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
    <html-el:form action="/admin/YwtTask"  method="post" enctype="multipart/form-data">
    <html-el:hidden property="id" styleId="id" value="${allmap.entity.id }" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
         <tr><td colspan="2">
         </td></tr>
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">*</font>子任务名称:</td>
          <td width="12%" nowrap="nowrap" class="title_item" align="left">
		   	<html-el:text property="task_name" styleId="task_name" value="${allmap.entity.task_name}"></html-el:text>
		   	&nbsp;&nbsp;&nbsp;
		   	<font color="red">*</font>父任务名称：
		   	<html-el:select property="par_task_id" styleId="par_task_id" value="${allmap.entity.par_task_id}">
	    		<c:forEach items="${revetasklist }" var="cur">
	    		<html-el:option value="${cur.task_id}">${cur.task_name}</html-el:option>
	    		</c:forEach>
	    	</html-el:select>
          </td>
        </tr>
  	   <tr>
    		 <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">*</font>任务类型：</td>
    		<td width="12%" nowrap="nowrap" class="title_item" align="left">
    		<html-el:select property="task_type" styleId="task_type" value="${allmap.entity.task_type}">
    		<html-el:option value="">-请选择-</html-el:option>
	    		<c:forEach items="${taskTypeList}" var="cur">
	    		<html-el:option value="${cur.type_id}">${cur.type_name}</html-el:option>
	    		</c:forEach>
	    	</html-el:select>
	    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    	任务状态：
	    	<html-el:select property="" styleId="" disabled="true" value="${allmap.entity.task_state}">
	    		<html-el:option value="0">未开始</html-el:option>
	    		<html-el:option value="1">进行中</html-el:option>
	    		<html-el:option value="2">已完成</html-el:option>
	    	</html-el:select>
	    	<html-el:select property="task_state" styleId="task_state" value="${allmap.entity.task_state}">
	    		<html-el:option value="0">未开始</html-el:option>
	    		<html-el:option value="1">进行中</html-el:option>
	    		<html-el:option value="2">已完成</html-el:option>
	    	</html-el:select>
	    	</td>
  	   </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">
            <font color="red">*</font>开始日期：
            </td>
          <td width="12%" nowrap="nowrap" class="title_item" align="left">
          <fmt:formatDate value="${allmap.entity.begin_date }" var="_s_date" pattern="yyyy-MM-dd" />
		    <html-el:text property="begin_date" styleId="begin_date" size="10" maxlength="10" value="${_s_date}" readonly="true" onclick="new Calendar(1900, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             <font color="red">*</font>结束日期：
            <fmt:formatDate value="${allmap.entity.finsh_date }" var="_e_date" pattern="yyyy-MM-dd" />
		    <html-el:text property="finsh_date" styleId="finsh_date" size="10" maxlength="10" value="${_e_date}" readonly="true" onclick="new Calendar(1900, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
          </td>
        </tr>
         <tr>
	   		<td align="right" nowrap="nowrap" class="title_item">子任务发起人：</td>
	    	<td align="left" nowrap="nowrap">
	        	<html-el:text property="" styleId="" value="${allmap.assigned_user_name }" disabled="true"></html-el:text>
	        	<html-el:hidden property="assigned_user_id" styleId="assigned_user_id" value="${allmap.assigned_user_id }"></html-el:hidden>
	   		</td>
		</tr>
         <tr>
    		
    		<td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">*</font>指派给：</td>
    		<td align="left" nowrap="nowrap">
    		<input type="text" id="receive_id" onclick="selectUser()"></input>
    		<html-el:select property="receive_user_id" styleId="receive_user_id" multiple="multiple" style="width:150px;" size="2" >
	    	</html-el:select>
	    	</td>
  	   </tr>
		 <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">子任务内容</td>
          <td width="88%" align="left">
             <html-el:textarea property="content" styleId="content" styleClass="webinput" style="width:70%;height:60px;" value="${allmap.entity.content}"/>
             <div id="content_note"  style="color:#0066FF;font-size:12px;" ><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" />请将字数限制在 250字内！</div>
          </td>
        </tr>
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"></td>
          <td width="88%" align="left">
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">备注说明</td>
          <td width="88%" align="left">
             <html-el:textarea property="remark" styleId="remark" styleClass="webinput" style="width:70%;height:60px;" value="${allmap.entity.remark}"/>
             <div id="remark_note"  style="color:#0066FF;font-size:12px;"><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" />请将字数限制在 250字内！</div>
          </td>
        </tr>
        <tr>
    		 <td width="12%" nowrap="nowrap" class="title_item" align="right">优先级：</td>
    		<td width="12%" nowrap="nowrap" class="title_item" align="left">
    		<html-el:select property="priority" styleId="priority" value="${allmap.entity.priority}">
	    		<html-el:option value="0">高</html-el:option>
	    		<html-el:option value="1">中</html-el:option>
	    		<html-el:option value="2">低</html-el:option>
	    	</html-el:select>
	    	
    		状态：
    		<html-el:select property="is_del" styleId="is_del" value="${allmap.entity.is_del}">
	    		<html-el:option value="0">启用</html-el:option>
	    		<html-el:option value="1">停用</html-el:option>
	    	</html-el:select>
	    	</td>
  	   </tr>
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"></td>
          <td width="88%" align="left">
          </td>
        </tr>
        <tr>
            <td class="title_item" align="right" nowrap="nowrap">附件:</td>
					<td>
					
					<div id="policydivFileHidden" style="display: none;" >
	                <input name="activity_policy" type="file" id="activity_policy" style="width: 250px;" />
	                <img src="../../images/x.gif" style="vertical-align:middle; cursor: pointer;" id="policyDelTr" title="删除"/>
	                </div>
	              	<div id="policydivFile">
	                <input name="policy_file" type="file" id="policy_file" style="width: 250px;" />
	                <img src="../../images/+.gif" style="vertical-align:middle; cursor: pointer;" id="policyAddTr" title="再添加一个" /></div>
	                <ol>
	            	<c:forEach items="${allmap.fj_paths}" var="cur">
					  <li><a href="${ctx}/${cur.save_path}" target="_blank">${cur.file_name}</a>
					  &nbsp;&nbsp;&nbsp;<a href="#" id="att_del_${cur.id}"><img src="../../images/x.gif" style="vertical-align:middle; cursor: pointer;" title="删除"/></a></li>
					  </c:forEach>
					</ol>
					
          			</td>
		</tr>
		
		
		
        <tr>	 
          <td>&nbsp;</td>
         <td nowrap="nowrap" class="title_item" align="left">
							<c:if test="${view!=1 }"><html-el:button property="" value="提交" styleClass="but4" styleId="btn_submit" /> </c:if>
							&nbsp;&nbsp;<html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();return false;" />
						<td>
        </tr>
        <tr style="height: 100px;"></tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=jtop"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/My97DatePicker/WdatePicker.js"></script>




<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>

<script type="text/javascript"><!--//<![CDATA[
$(document).ready(function(){
	$("#remark_note").attr("hidden",true);
	$("#content_note").attr("hidden",true);
	$("#content_note").attr("hidden",true);
	$("#receive_user_id").attr("hidden",true);
	//处理那个分派人的回显 beign
	   var str = "";
		var arr = "${allmap.receive_user_id_name_arry}";
		var attr_str = arr.split(",");
		for ( var k = 0; k < attr_str.length; k++) {
			var cur = attr_str[k];
			var index = cur.indexOf("&");
			if (index != -1) {
				str = str + cur.substring(index + 1) + ",";
			}
		}
		$("#receive_id").val(str);
		for ( var k = 0; k < attr_str.length; k++) {
			var cur = attr_str[k];
			var index = cur.indexOf("&");
			if (index != -1) {
				$("#receive_user_id").append("<option value="+attr_str[k]+">"+cur.substring(index + 1)+"</option>");
			}
			
		}
		var op = document.getElementById("receive_user_id");
		for ( var i = 0; i < op.options.length; i++) {
			op.options[i].selected = true;
		}
		//处理那个分派人的回显  end
		
		
				$("#policyAddTr")
						.click(
								function() {
									$("#policydivFileHidden").clone().find(
											"#activity_policy").attr(
											"name",
											"policy_file_"
													+ new Date().getTime())
											.end()
											.appendTo($("#policydivFile"))
											.show();
									$("img[id='policyDelTr']").each(function() {
										$(this).click(function() {
											$(this).parent().remove();
											resizeFrameHeight();
										});
									});
								});
				$("a[id^='att_del_']").click(function() {
					var a = this;
					if (!confirm('确实要删除此附件？'))
						return;
					$.post("KonkaSpActivityManager.do", {
						method : "deleteFile",
						id : $(this).attr("id").replace(/att_del_/g, '')
					}, function(success) {
						if (success) {
							alert("恭喜您，删除附件成功!");
							$(a).parent().remove();
						} else
							alert("很抱歉，删除附件出错!");
					});
					resizeFrameHeight();
				});

				//multi("state");

			});

	document.getElementById("task_state").style.visibility = "hidden";//隐藏  

	$("#btn_submit").click(
			function() {
				var taskname = $("#task_name").val();
				if (taskname == "") {
					alert("任务名称不能为空！");
					return false;
				}
				if (taskname.length > 50) {
					alert("任务名称不能超过50个字!");
					return false;
				}
				var tasktype = $("#task_type").val();
				if (tasktype == "") {
					alert("任务类型不能为空!");
					return false;
				}
				var content = $("#content").val();
				if (content == "") {
					alert("任务内容不能为空!");
					return false;
				}
				if (content.length > 250) {
					alert("任务内容不能超过250个字!");
					return false;
				}
				var receives = $('#receive_user_id').val();
				if (receives == "") {
					alert("任务接受至少选一个!");
					return false;
				}
				var Sysdate = new Date();
				var begin = new Date($("#begin_date").val());
				if (Sysdate.toLocaleDateString() > begin.toLocaleDateString()) {
					alert("日期不能早于当前时刻");
					return false;
				}
				var finshdate = $("#finsh_date").val();
				if (finshdate == "") {
					alert("预计完成日期不能为空!");
					return false;
				}
				var finsh = new Date($("#finsh_date").val());
				if (Sysdate.toLocaleDateString() > finsh.toLocaleDateString()
						|| begin.toLocaleDateString() > finsh
								.toLocaleDateString()) {
					alert("预计完成日期不能早于任务开始时间，且要晚于当前时刻!");
					return false;
				}
				var isdel = $("#is_del").val();
				if (isdel == "") {
					alert("状态不能为空!");
					return false;
				}
				var priority = $("#priority").val('getValue');
				if (priority == "") {
					alert("优先级不能为空!");
					return false;
				}

				var remark = $("#remark").val();
				if (remark.length > 250) {
					alert("备注不能超过250个字!");
					return false;
				}
				if (Validator.Validate(this.form)) {
					if (confirm("确定提交表单？")) {
						$("#btn_submit").attr("value", "正在提交...").attr(
								"disabled", "true");
						$("#btn_back").attr("disabled", "true");
						this.form.submit();
					} else {
						return false;
					}
				}
			});

	function selectUser(obj) {
		var curr_task_id = $("#id").val();
		var selectUser = $("#receive_user_id").val();
		var returnValue = window.showModalDialog(
				"${ctx}/webservice/YwtTask.do?method=selectUser&curr_task_id="
						+ curr_task_id, selectUser,
				"dialogWidth:900px;status:no;dialogHeight:500px");

		try {
			if (returnValue != null && returnValue.new_select_user) {
				var f = document.getElementById("receive_user_id");
				var childs = f.childNodes;
				for ( var i = childs.length - 1; i >= 0; i--) {
					f.removeChild(childs[i]);
				}
				data = returnValue.new_select_user;
				data.forEach(function(e) {
					$("#receive_user_id").append(
							"<option value="+e+">"
									+ e.substring(e.indexOf("&") + 1)
									+ "</option>");
				});
				var op = document.getElementById("receive_user_id");
				for ( var i = 0; i < op.options.length; i++) {
					op.options[i].selected = true;
				}
				
				var str="";
				$("#receive_id").val(str);
				var selectUser = $("#receive_user_id").val();
								
				selectUser.forEach(function (a) {
					var index = a.indexOf("&");
					if (index != -1) {
						str = str + a.substring(index + 1) + ",";
					}
				});
				
				
				$("#receive_id").val(str);
			}
		} catch (e) {
		}
	}

	$("#content").blur(function() {
		if ($("#content").val().length > 250) {
			$("#content_note").attr("hidden", false);
		} else {
			$("#content_note").attr("hidden", true);
		}
	});
	$("#remark").blur(function() {
		if ($("#remark").val().length > 250) {
			$("#remark_note").attr("hidden", false);
		} else {
			$("#remark_note").attr("hidden", true);
		}
	});
	function setOnlyInt(obj) {
		$(obj).css("ime-mode", "disabled");
		$(obj).attr("t_value", "");
		$(obj).attr("o_value", "");
		$(obj).bind("dragenter", function() {
			return false;
		});
		$(obj).keypress(function() {
			if (!obj.value.match(/^\d*$/))
				obj.value = obj.t_value;
			else
				obj.t_value = obj.value;
			if (obj.value.match(/^(?:\d+(?:\d+)?)?$/))
				obj.o_value = obj.value;
		}).keyup(function() {
			if (!obj.value.match(/^\d*$/))
				obj.value = obj.t_value;
			else
				obj.t_value = obj.value;
			if (obj.value.match(/^(?:\d+(?:\d+)?)?$/))
				obj.o_value = obj.value;
		}).blur(function() {
			if (!obj.value.match(/^(?:\d+(?:\d+)?|\d*?)?$/))
				obj.value = obj.o_value;
			else {
				if (obj.value.match(/^\d+$/))
					obj.value = obj.value;
				if (obj.value.match(/^\.$/))
					obj.value = 0;
				obj.o_value = obj.value;
			}
			if (obj.value.length == 0 || isNaN(obj.value) || obj.value == 0)
				obj.value = "0";
		});
	}
//]]>-->-->
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
