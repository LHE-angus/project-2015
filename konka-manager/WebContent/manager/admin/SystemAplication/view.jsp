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
	<link rel="stylesheet" type="text/css" href="${ctx}/commons/scripts/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/commons/scripts/themes/icon.css"/>
	<style type="text/css">
		.rtable1 td {
			padding:3px 5px;
		}
	</style>
</head>
<body >
	<div class="oarcont" style="height: 750px">
	  	<div class="oartop">
			<table width="400" border="0" cellpadding="0" cellspacing="0">
		    	<tr>
		        	<td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
		        	<td>&nbsp;当前位置：<span id='nav'></span></td>
		        	
		      	</tr>
		    </table>
		</div>
		<div style="padding:5px">
			<form id='fm' method="post">
			<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
				<tr class="oartop" style="margin-bottom: 10px; height: 12px; line-height: 12px;">
					<td colspan="4">申请信息</td>
				</tr>
				<tr>
					<td width="20%" align="right">
						<strong class="fb">回 执 单：</strong>
					</td>
					<td>
						<span id="hzd_id"></span>
					</td>
					<td align="right">
						<strong class="fb">申请时间：</strong>	
					</td>
					<td>
						<span id="today"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<strong class="fb">填 写 人：</strong>
					</td>
					<td>
						<span id="write_man"></span>
					</td>
					<td align="right">
						<strong class="fb">部&nbsp;&nbsp;&nbsp;&nbsp;门：</strong>	
					</td>
					<td>
						<span id="dept_name"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<strong class="fb"><span style="color:red;">* </span>申 请 人：</strong>
					</td>
					<td>
						<span id="sq_name"></span>	
					</td>
					<td align="right">
						<strong class="fb"><span style="color:red;">* </span>申请类型：</strong>	
					</td>
					<td>
						<span id="type"></span>
					</td>
				</tr>
				
		        <tr>
		        	<td align="right">
				   		<strong class="fb"><span style="color:red;">* </span>标&nbsp;&nbsp;&nbsp;&nbsp;题：</strong>
		        	</td>
				   	<td colspan="3">
				   		<span id="req_title"></span>			   		
				   	</td>
				</tr>
		        <tr>
		        	<td align="right">
		          		<strong class="fb"><span style="color:red;">* </span>详&nbsp;&nbsp;&nbsp;&nbsp;情：</strong> 
		        	</td>
		          	<td colspan="3">
		          		<textarea rows="10" cols="70" id="content" readonly="readonly" style="resize:none;"></textarea>
		           	</td>
		        </tr>
		        <tr>
		        	<td align="right">
		          		<strong class="fb">附&nbsp;&nbsp;&nbsp;&nbsp;件：</strong> 
		        	</td>
		          	<td colspan="3">
		          		<div id='attachments'>
		          		</div>
              		</td>
		        </tr>
				<tr id='shlc' style="display: none">
					<td align="right">
				    	<strong class="fb"><span style="color:red;">* </span>审批流程：</strong>
					</td>
				    <td colspan="3">
				   		<input id="audit_node" name="audit_node" style="width:250px;"/>
				   	</td>
		        </tr>
				<tr id='spr' style="display: ''">
					<td align="right">
				    	<strong class="fb"><span style="color:red;">* </span>审批流程：</strong>
					</td>
				    <td colspan="3">
				    	<div id='sp_name'></div>
				   	</td>
		        </tr>
		    </table>
		    <br/>
			<table id='view_show' width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1" style="display: none">
				<tr class="oartop" style="margin-bottom: 10px; height: 12px; line-height: 12px;">
					<td colspan="4">处理结果</td>
				</tr>
		        <tr>
				   	<td width='20%' align="right">
				   		<strong class="fb">状&nbsp;&nbsp;&nbsp;&nbsp;态：</strong>
				   	</td>
				   	<td>
				   		<span id="deal_status"></span>			   		
				   	</td>
				   	<td align="right" width="20%">
				   		<strong class="fb">处理时间：</strong>
				   	</td>
				   	<td >
				   		<span id="deal_date"></span>			   		
				   	</td>
				</tr>
		        <tr>
				   	<td align="right">
				   		<strong class="fb">处 理 人：</strong>
				   	</td>
				   	<td colspan="3">
				   		<span id="deal_man"></span>			   		
				   	</td>
				</tr>
		        <tr>
				   	<td align="right">
				   		<strong class="fb">结果说明：</strong>
				   	</td>
				   	<td colspan="3">
				   		<span id="deal_result"></span>	   		
				   	</td>
				</tr>
		    </table>
			<table id='audit_show' width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1" style="display: none">
				<tr class="oartop" style="margin-bottom: 10px; height: 12px; line-height: 12px;">
					<td colspan="4">处理结果</td>
				</tr>
		        <tr>
				   	<td width='20%' align="right">
				   		<strong class="fb">审批结果：</strong>
				   	</td>
				   	<td colspan="3">
				   		<select id="audit_res" class="easyui-combobox" name="" style="width:150px" data-options="editable:false">
							<option value="">-请选择-</option>
							<option value="0">通过</option>
							<option value="1">驳回</option>
				   		</select>		   		
				   	</td>
				</tr>
		        <tr>
				   	<td align="right">
				   		<strong class="fb">结果说明：</strong>
				   	</td>
				   	<td colspan="3">
				   		<textarea rows="5" cols="70" id="AUDIT_COMMENT"></textarea>		   		
				   	</td>
				</tr>
		    </table>
		    </form>
		    <br/>
		    <label style="margin-left:40%">
	            <a href="#" class="easyui-linkbutton" id="add" iconCls="icon-save" style="display: none">提&nbsp;&nbsp;交</a>
	            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	            <a href="#" class="easyui-linkbutton" id="back" iconCls="icon-undo" >返&nbsp;&nbsp;回</a>
            </label>
		</div>
	</div>	
	<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script> 
	<script type="text/javascript" src="${ctx}/commons/scripts/jquery.easyui.min.js"></script>  
	<script type="text/javascript" src="${ctx}/commons/scripts/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${ctx}/commons/scripts/public.js"></script>
	<script type="text/javascript">
		$(function(){
			//获取模块id
			var mod_id = GetQueryString("mod_id");
			var id = GetQueryString("id");
			var deal = GetQueryString("deal");
			//初始化
			$.post('${ctx}/manager/admin/SystemAplication.do?method=view&id='+id+'&mod_id='+mod_id,function(result){
				$("#nav").text(result.local_str);
				$("#hzd_id").text(result.BACK_ID);
				$("#today").text(result.CREATE_DATE);
				$("#write_man").text(result.WRITE_NAME);
				$("#dept_name").text(result.DEPT_NAME);
				$("#sq_name").text(result.NAME);
				$("#type").text(result.C_NAME);
				$("#req_title").text(result.TITLE);

				var app = "<table border='1' width='100%'>";
				jQuery.each(result.auditList,function(index,da){
					var res = '';
					if(da.AUDIT_RESULT=='0'){
						res = "<span style='color:#40DD0B' >通过</span>";
					}else if (da.AUDIT_RESULT=='1'){
						res = "<span style='color:red' >驳回</span>";
					}else{
						res = "<span style='color:#DF8520'>待审批</span>";
					}
					var role = da.ROLE_NAME;
					if((index+1)==result.auditList.length-3){
						role = '部门主管';    
					}
					if((index+1)==result.auditList.length-2){
						role = '分司领导';    
					}
					if((index+1)==result.auditList.length-1){
						role = '部门领导';    
					}
					if((index+1)==result.auditList.length){
						role = '处理人';    
					}
					var comment = "";
					if(da.AUDIT_COMMENT!=null){
						comment = da.AUDIT_COMMENT;
					}
					var datetime = "";
					if(da.AUDIT_DATETIME!=null){
						datetime = da.AUDIT_DATETIME;
					}
					app += "<tr><td align='right' width='8%'>"+role+"：</td><td align='center' width='15%'>"+da.AUDIT_USER+"</td><td align='center' width='15%'>"+datetime+"</td><td align='center' width='6%'>"+res+"</td><td>"+comment+"</td></tr>";
				});
				app += "</table>";
				$("#sp_name").html(app);

				if('yes'==deal){
					$("#audit_show").show();
					$("#add").show();
				}
				
				var content = result.CONTENT;
				content = content.replace(/#br#/g, '<br/>');//IE7-8    
				content = content.replace(/_@/g, '<br/>');//IE9、FF、chrome    
				content = content.replace(/\s/g, '&nbsp;');//空格处理
				$("#content").html(content);

				//循环显示附件信息
				var append = "<table><tr>";
				jQuery.each(result.kpaList,function(index,da){
					if(index%3==0){
						append += '</tr><tr>';
					}
					append += '<td><a href=\"${ctx}/manager/admin/Download.do?method=downloadFile&save_name='+da.SAVE_NAME+'\" target=\"_blank\">'+da.FILE_NAME+'</a>&nbsp;<img src=\"../../../images/x.gif\"'+ 
							  ' style=\"vertical-align:middle; cursor: pointer;\" id=\"att_del_'+da.ID+'\" title=\"删除\"/><td>';
					
				});
				$("#attachments").html(append+'</tr>');

				$("img[id^='att_del_']").click(function() {
			  		var a = this;
			  		var id = $(this).attr("id").replace(/att_del_/g, '');
			  		$.messager.confirm('温馨提示', '是否删除该附件？', function(r){  
						if (r){
							$.post('${ctx}/manager/admin/SystemAplication.do?method=deleteFile&id='+id,function(result){
								if (result.flag==1){
									$.messager.alert('温馨提示','恭喜您，删除附件成功!','info',function(r){
					  	   				$(a).parent().remove();
									});  
				  	   			} else {
				  	   				$.messager.alert('温馨提示','很抱歉，删除附件出错!','info'); 
				  	   			}
							},'json');
						}
			  		});
			   	});

				//提交审批
				$("#add").bind('click',function(){
					var audit_res = $("#audit_res").combobox("getValue");
					if(audit_res!=''){
						$("#fm").attr("action", "${ctx}/manager/admin/SystemAplication.do?method=addAudit&link_id="+result.LINK_ID+"&audit_res="+audit_res+"&audit_comment="+$("#AUDIT_COMMENT").val()+"&mod_id="+mod_id);
			    		$("#fm").submit();
					}else{
						$.messager.alert('温馨提示','请选择审批结果!','info'); 
					}
				});

				$("#back").bind('click',function(){
					location.href='list.jsp?mod_id='+mod_id;
				});
			},'json');

		});
	</script>
	<jsp:include page="/__analytics.jsp" />
</body>
</html>
