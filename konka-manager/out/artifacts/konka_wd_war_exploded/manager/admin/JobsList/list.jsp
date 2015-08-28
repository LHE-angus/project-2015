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
			        	<td>&nbsp;<fmt:message key="current.location" bundle="${lang}"/>：<span id='nav'></span></td>
			      	</tr>
			    </table>
			</div>
			<div id='tabdiv' style="margin:5px;height: 400px;width: 800">
				<table id="table" width="100%" border="0" cellspacing="0" cellpadding="0"></table> 
		  	</div>
		</div>
		<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/commons/scripts/jquery.easyui.min.js"></script>  
		<script type="text/javascript" src="${ctx}/commons/scripts/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=discuz"></script>
		<script type="text/javascript" src="${ctx}/commons/scripts/public.js"></script> 
		<script type="text/javascript">
			
			function stopOrstart(id,flag){
				$.ajax( {
					type : "POST",
					cache : false,
					url : "${ctx}/manager/admin/JobsList.do",
					data : "method=stopOrstart&func_name="+id+"&flag="+flag,
					dataType: "json",
					error : function(data) { /* alert("Sorry! Error Code :" + data.status); */ },
					success : function(data) {
						$('#table').datagrid('load');
					}
				});
			}
			
			//初始化
			$(function(){
				//获取模块id
				var mod_id = GetQueryString("mod_id");
				if(mod_id==''){
					mod_id = $("#mod_id").val();
				}
				var return_flag = $("#return_flag").val();
				
				//初始化
				$.post('${ctx}/manager/admin/JobsList.do?method=init&mod_id='+mod_id,function(result){
					$("#nav").text(result.local_str);
					
					//表格初始化
					$("#table").datagrid({
						title: '定时任务列表',
						url:'${ctx}/manager/admin/JobsList.do?method=queryList',
						method: 'post',
						fitColumns: false,
					    onLoadError:function(){
					    	$.messager.alert('温馨提示','数据请求失败!','error');  
					    },
					    loadMsg:'正在拼命加载中，请稍后。。。。',
					    remoteSort:false,
					    pagination:true,
					    rownumbers:true,
					    columns:[[
					     {title:'定时任务方法',field:'fun_name',width:220,editor:'text',sortable:'true',align:'center'},
					     {title:'任务描述',field:'job_desc',width:150,editor:'text',sortable:'true',align:'center'},
					     {title:'执行时间',field:'excut_time',width:150,editor:'text',sortable:'true',align:'center'},
					     {title:'状态',field:'status',width:80,editor:'text',sortable:'true',align:'center',
						 	formatter: function(value,row,index){
								if(0==value){
									return '已启用';
								}
								if(1==value){
									return '已停用';
								}
							}
						 },
					     {title:'更新时间',field:'up_time',width:150,editor:'text',sortable:'true',titleAlign:'center',
						 	formatter: function(value,row,index){
								return row.map.up_time;
							}
						 },
					     {title:'更新人',field:'updated_name',width:150,editor:'text',sortable:'true',align:'center'},
					     {title:'操作',field:'updated_id',width:80,editor:'text',sortable:'true',align:'center',
					     	formatter: function(value,row,index){
								if(0==row.status){
					     			return "<span style='cursor:pointer;' class='fblue' onclick='stopOrstart(\""+row.fun_name+"\",1)'>停用</span>";
								}else{
					     			return "<span style='cursor:pointer;' class='fblue' onclick='stopOrstart(\""+row.fun_name+"\",0)'>启用</span>";
								}
					     	 }
					     }
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
				    	pageSize:15,  
					    onBeforeRefresh:function(pageNumber, pageSize){
					    	$(this).pagination({pageNumber:$(".pagination-num").val()});
					     	$(this).pagination('loading');
					    }
					});
				},'json');
				
			});
		</script>
	<jsp:include page="/__analytics.jsp" />
	</body>
</html>
