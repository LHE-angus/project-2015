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
	<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css"	rel="stylesheet" type="text/css" />
	<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet"	type="text/css" />
	<link href="${ctx}/styles/customer/css/tab.css" rel="stylesheet"	type="text/css" />
	<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css"	rel="stylesheet" type="text/css" />
	<link href="${ctx}/styles/customer/fancybox/fancybox.css"	rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/commons/scripts/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/commons/scripts/themes/icon.css"/>
	<!-- <style type="text/css">
		.rtable1 td {
			padding:3px 5px;
		}
	</style> -->
</head>
<body >
	<div class="oarcont">
	  	<div class="oartop">
			<table width="400" border="0" cellpadding="0" cellspacing="0">
		    	<tr>
		        	<td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
		        	<td>&nbsp;当前位置：<span id='nav'></span></td>
		      	</tr>
		    </table>
		</div>
		<div class="rtabcont2">
			<form id='fm' method="post">
				<div style="float:right;">
					<!-- <a href="#" class="easyui-linkbutton" id="add" iconCls="icon-save" style="display: none">提&nbsp;&nbsp;交</a>
		            &nbsp;&nbsp;&nbsp; -->
		            <a href="#" class="easyui-linkbutton" id="back" iconCls="icon-undo" onclick="goback()">返&nbsp;&nbsp;回</a>
				</div>
				<ul id="tabs">
					<li><a href="#" name="tab1">申请详情</a></li>
					<!-- <li><a href="#" name="tab2">审核信息</a></li> -->
				</ul>
				<div id="content">
					<div id="tab1">
						<table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr class="oartop" style="margin-bottom: 5px; height: 20px; line-height: 12px;">
								<td colspan="4"><span class="fb">申请信息</span></td>
							</tr>
							<tr>
								<td align="right" style="padding-left:10px">
									<strong class="fb">申请时间：</strong>	
								</td>
								<td>
									<span id="today"></span>
								</td>
								<td align="right">
									<strong class="fb">部&nbsp;&nbsp;&nbsp;&nbsp;门：</strong>	
								</td>
								<td width="30%">
									<span id="dept_name"></span>
								</td>
							</tr>
							<tr>
								<td align="right">
									<strong class="fb"><span style="color:red;">* </span>申 请 人：</strong>
								</td>
								<td>
									<span id='create_name'></span>
								</td>
								<td align="right">
									<strong class="fb"><span style="color:red;">* </span>申请类型：</strong>	
								</td>
								<td>
									<span id="apply_type"></span>
								</td>
							</tr>
							<tr>
					        	<td align="right">
					          		<strong class="fb"><span style="color:red;">* </span><span id='con_text'></span></strong> 
					        	</td>
					          	<td colspan="3">
									<textarea rows="5" cols="70" id="content_text" style="resize:none;" readonly="readonly"></textarea>
								</td>
							</tr>
					    </table>
					</div>
				
					<!-- <div id="tab2">
					    <table id='audit_detail' width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1" style="margin-bottom: 10px; display: none;">
							<tr class="oartop" style="margin-bottom: 10px; height: 12px; line-height: 12px;">
								<td><strong class="fb">审批信息：</strong></td>
							</tr>
							<tr>
								<td>
									<div id="audit_info"></div>
								</td>
							</tr>
					    </table>
					    <table id='audit_show' width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1" style="margin-bottom: 10px; display: none;">
							<tr class="oartop" style="margin-bottom: 10px; height: 12px; line-height: 12px;">
								<td colspan="2"><strong class="fb">提交审批：</strong></td>
							</tr>
					        <tr>
							   	<td width='20%' align="right">
							   		<strong class="fb">审批结果：</strong>
							   	</td>
							   	<td>
							   		<select id="audit_res" class="easyui-combobox" name="" style="width:150px" data-options="editable:false">
										<option value="">-请选择-</option>
										<option value="0">同意</option>
										<option value="1">驳回</option>
							   		</select>		   		
							   	</td>
							</tr>
					        <tr>
							   	<td align="right">
							   		<strong class="fb">审批意见：</strong>
							   	</td>
							   	<td>
							   		<textarea rows="5" cols="70" id="audit_comment" name='audit_comment'></textarea>		   		
							   	</td>
							</tr>
					    </table>
					</div> -->
				</div>
		    </form>
		</div>
	</div>
	<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script> 
	<script type="text/javascript" src="${ctx}/commons/scripts/jquery.easyui.min.js"></script>  
	<script type="text/javascript" src="${ctx}/commons/scripts/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${ctx}/commons/scripts/public.js"></script>
	<script type="text/javascript">
		
		//tabs切换Begin
		/*$("#content div[id^=tab]").hide(); // Initially hide all content
	    $("#tabs li:first").attr("id","current"); // Activate first tab
	    $("#content div[id^=tab]:first").fadeIn(); // Show first tab content
	    $('#tabs a').click(function(e) {
	        e.preventDefault();
	        if ($(this).closest("li").attr("id") == "current"){ //detection for current tab
	         	return       
	        } else{             
		        $("#content div[id^=tab]").hide(); //Hide all content
		        $("#tabs li").attr("id",""); //Reset id's
		        $(this).parent().attr("id","current"); // Activate this
		        $('#' + $(this).attr('name')).fadeIn(); // Show content for current tab
	        }
	        window.parent.resizeFrameHeight('mainFrame', 3);
	    });*/
		//tabs切换End
	
		//获取模块id
		var mod_id = GetQueryString("mod_id");
		var id = GetQueryString("id");
		var dept_id = GetQueryString("dept_id");
		var audit_type = GetQueryString("audit_type");
		var operation = GetQueryString("operation");
		
		function resizeFrameHeight(offset, min_height) {
			// frame id can be found in page /konka-wd/WebContent/manager/admin/Frames2/indexFrame.jsp, and search 'iframe' to get.
			$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
		}
		
		//返回
		function goback(){
			location.href='${ctx}/manager/admin/WaitAuditList.do?method=backToList&mod_id='+mod_id+
					      '&dept_id='+dept_id+'&audit_type='+audit_type;
		}
		
		$(document).ready(function(){
			
			//初始化
			$.post('${ctx}/manager/admin/WaitAuditList.do?method=view&id='+id+'&mod_id='+mod_id+'&operation='+operation,function(result){
				$("#nav").text(result.local_str);
				$("#today").text(result.CREATE_DATE);
				$("#dept_name").text(result.DEPT_NAME);
				$("#create_name").text(result.USER_NAME);
				
				var apply_type = "";
				if("modify"==result.OPER_TYPE){
					apply_type = "修改信息";
					$("#con_text").text("修改内容：");
					$("#modify_table").show();
					$("#content_text").html(result.OPER_REASON);
				}
				if("stop"==result.OPER_TYPE){
					apply_type = "停用";
					$("#con_text").text("停用原因：");
					$("#modify_table").hide();
					$("#content_text").show();
					$("#content_text").html(result.OPER_REASON);
				}
				if("start"==result.OPER_TYPE){
					apply_type = "启用";
					$("#con_text").text("启用原因：");
					$("#modify_table").hide();
					$("#content_text").show();
					$("#content_text").html(result.OPER_REASON);
				}
				$("#apply_type").text(apply_type);
				
				/*if(result.auditlist!=''){
					var tab = "<table id='shenpi' width='100%' border='0' cellspacing='0' cellpadding='0' class='rtable2' align='center' style='margin-bottom: 5px;''>"+
			    			  "<thead style='background:#f5f4f4;'><tr class='oartop' align='center'>"+
							  "<td width='5%' nowrap='nowrap' class='td_bord'><strong class='fb'>序号</strong></td>"+
			    			  "<td width='15%' nowrap='nowrap' class='td_bord'><strong class='fb'>日期</strong></td>"+
			          		  "<td width='15%' nowrap='nowrap' class='td_bord'><strong class='fb'>审核人</strong></td>"+
			          		  "<td width='15%' nowrap='nowrap' class='td_bord'><strong class='fb'>职务</strong></td>"+
			          		  "<td width='5%' nowrap='nowrap' class='td_bord'><strong class='fb'>结果</strong></td>"+
			          		  "<td nowrap='nowrap' class='td_bord'><strong class='fb'>意见</strong></td></tr></thead>";
			        
	          		jQuery.each(result.auditlist,function(index,aud){
	          			var res = '';
						if(aud.AUDIT_RESULT=='0'){
							res = "<span style='color:green' >同意</span>";
						}else if (aud.AUDIT_RESULT=='1'){
							res = "<span style='color:#F00;' >驳回</span>";
						}
						var role = aud.AUDIT_LEVEL;
						if(role==1){
							role = '分公司管理员';    
						}
						if(role==2){
							role = '总部管理员';    
						}
						var comment = "";
						if(aud.AUDIT_COMMENT!=null){
							comment = aud.AUDIT_COMMENT;
						}
						var datetime = "";
						if(aud.AUDIT_DATETIME!=null){
							datetime = aud.AUDIT_DATETIME;
						}
	          			tab += "<tr align='center'><td>"+index+"</td><td>"+datetime+"</td><td>"+aud.AUDIT_USER+"</td><td>"+role+"</td><td>"+res+"</td><td>"+comment+"</td></tr>";
					});
	          		tab += "</table>";  
	          		$("#audit_info").html(tab);
	          		$("#audit_detail").show();
				}
				
				//根据角色选择是否显示审批部分
				if(result.SHOW_AUDIT){
					$("#audit_show").show();
					$("#add").show();
				}*/
				
				//iframe高度自适应
				window.parent.resizeFrameHeight('mainFrame', 3);
				
				//提交审批
				$("#add").bind('click',function(){
					var audit_res = $("#audit_res").combobox("getValue");
					if(audit_res!=''){
						$("#fm").attr("action", "${ctx}/manager/admin/WaitAuditList.do?method=addAudit&id="+id+"&audit_res="+audit_res+"&audit_comment="+$("#audit_comment").val()+"&mod_id="+mod_id);
			    		$("#fm").submit();
					}else{
						$.messager.alert('温馨提示','请选择审批结果!','info'); 
					}
				});
			},'json');

		});
	</script>
	<jsp:include page="/__analytics.jsp" />
</body>
</html>
