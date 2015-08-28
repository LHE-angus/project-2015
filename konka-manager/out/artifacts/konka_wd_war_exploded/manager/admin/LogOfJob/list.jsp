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
		<style>
			.rtable1 td {
				padding:2px 5px;
			}
		</style>
	</head>
	<body >
		<div class="oarcont">
			<div class="oartop">
				<table width="400" border="0" cellpadding="0" cellspacing="0">
			    	<tr>
			        	<td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
			        	<td>&nbsp;<fmt:message key="current.location" bundle="${lang}"/><span id='nav'></span></td>
			      	</tr>
			    </table>
			</div>
		  	<div style="padding:5px">
		    	<form id="fm" method="post" >
		    		<input type="hidden" id='mod_id' value='${mod_id }'/>
					<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
				        <tr align="center">
						   	<td>
						   		<label for="syn_date_begin"><strong class="fb">同步时间：</strong></label>
				   				<input name="syn_date_begin" id="syn_date_begin" style="width: 110px" title="点击选择日期" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true})" />－
				   				<input name="syn_date_end" id="syn_date_end" style="width: 110px" title="点击选择日期" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true})" />
						   	</td>
				        	<td>
					            <label for="ope_name"><strong class="fb">操作人：</strong></label>  
					            <input class="easyui-validatebox" type="text" name="ope_name" id="ope_name" maxlength="30"></input>
				        	</td>
				        	<td>
					            <label for="syn_type"><strong class="fb">同步类型：</strong></label>  
					            <input class="easyui-validatebox" type="text" name="syn_type" id="syn_type" maxlength="30"></input>
				        	</td>
						</tr>
		      		</table>
		      	</form>
		  	</div>
		  	<div id="tb" style="height: auto;padding-right: 10px" align="right">
				<a href="#" class="easyui-linkbutton" id="search" iconCls="icon-search">查   询</a>
			</div>
			<div id='tabdiv' style="margin:5px;height: 400px;width: 800">
				<table id="table" class="easyui-datagrid" width="100%" border="0" cellspacing="0" cellpadding="0"></table> 
		  	</div>
		</div>
		<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/commons/scripts/jquery.easyui.min.js"></script>  
		<script type="text/javascript" src="${ctx}/commons/scripts/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/commons/scripts/public.js"></script> 
		<script type="text/javascript">
			
			//初始化
			$(function(){
				//获取模块id
				var mod_id = GetQueryString("mod_id");
				if(mod_id==''){
					mod_id = $("#mod_id").val();
				}
				
				//初始化
				$.post('${ctx}/manager/admin/LogOfJob.do?method=init&mod_id='+mod_id,function(result){
					$("#nav").text(result.local_str);
					$("#syn_date_begin").val(result.syn_date_begin);
					$("#syn_date_end").val(result.syn_date_end);
					
					//同步类型初始化
					$('#syn_type').combobox({                 
						url:'${ctx}/manager/admin/LogOfJob.do?method=getSynTypeList',  
					 	method:'post',              
					 	editable:false, //不可编辑状态                
					 	cache: false,               
					 	valueField:'JOB_TYPE',                   
						textField:'JOB_TYPE'
					}); 
					$('#syn_type').combobox("setValue", "-请选择-");
					
					//表格初始化
					$("#table").datagrid({
						title: '同步日志',
						url:'${ctx}/manager/admin/LogOfJob.do?method=queryList',
						method: 'post',
						queryParams:{
							syn_date_begin: $("#syn_date_begin").val(),
							syn_date_end: $("#syn_date_end").val(),
							ope_name: $("#ope_name").val(),
							syn_type: $("#syn_type").combobox("getValue")
						},
						fitColumns: false,
					    onLoadError:function(){
					    	$.messager.alert('温馨提示','数据请求失败!','error');  
					    },
					    loadMsg:'正在拼命加载中，请稍后。。。。',
					    remoteSort:false,
					    pagination:true,
					    rownumbers:true,
					    columns:[[
					     {title:'操作人',field:'OPER_REAL_NAME',width:70,editor:'text',sortable:'true',align:'center'},
					     {title:'操作时间',field:'START_TIME',width:130,editor:'text',sortable:'true',align:'center'},
					     {title:'操作耗时',field:'SPEND_TIME',width:60,editor:'text',sortable:'true',align:'center'},
					     {title:'操作IP',field:'OPER_USER_IP',width:80,editor:'text',sortable:'true',align:'center'},
					     {title:'操作主表',field:'LINK_TABLE',width:150,editor:'text',sortable:'true',titleAlign:'center'},
					     {title:'方法名称',field:'OPER_FUN',width:150,editor:'text',sortable:'true',align:'center'},
					     {title:'同步类型',field:'JOB_TYPE',width:200,editor:'text',sortable:'true',align:'center'},
					     {title:'同步状态',field:'JOB_STATUS',width:60,editor:'text',sortable:'true',align:'center'},
					     {title:'备注',field:'COMMENTS',width:600,editor:'text',sortable:'true',align:'center'}
					    ]],
					    onHeaderContextMenu: function(e, field){
		                	e.preventDefault();  
		                    if (!$('#tmenu').length){  
		                    	createColumnMenu();  
		                  	}  
		                 	$('#tmenu').menu('show', {  
		                    	left:e.pageX,  
		                       	top:e.pageY  
		                   	});  
		               	}
					});
					
					// 得到datagrid的pager对象，初始化分页栏
					var pager = $('#table').datagrid('getPager');  
					pager.pagination({    
				   		showPageList:false,    
				    	pageSize:10,  
					    onBeforeRefresh:function(pageNumber, pageSize){
					    	$(this).pagination({pageNumber:$(".pagination-num").val()});
					     	$(this).pagination('loading');
					    }
					});
				},'json');
				 
				//查询按钮绑定事件 
				$("#search").bind('click',function(){
					$('#table').datagrid('load',{
						syn_date_begin: $("#syn_date_begin").val(),
						syn_date_end: $("#syn_date_end").val(),
						ope_name: $("#ope_name").val(),
						syn_type: $("#syn_type").combobox("getValue")
			   		}); 
				});
			});
		</script>
	</body>
</html>
