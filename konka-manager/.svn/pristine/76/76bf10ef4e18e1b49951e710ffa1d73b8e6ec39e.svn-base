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
	<script type="text/javascript" src="${ctx}/commons/scripts/public.js"></script>
	<style type="text/css">
		.rtable1 td {
			padding:2px 5px;
		}
		.panel {
  border: 0 none;
  border-radius: 0;
  margin: 0;
  overflow: auto;
  text-align: left;
}
	</style>
</head>
<body >
	<div class="oarcont" style="height: 500px">
	  	<div class="oartop">
			<table width="400" border="0" cellpadding="0" cellspacing="0">
		    	<tr>
		        	<td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
		        	<td>&nbsp;当前位置：<span id='nav'></span></td>
		        	
		      	</tr>
		    </table>
		</div>
		<div style="padding:5px">
			<form id="fm" method="post" >
			<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
		        <tr align="center">
				   	<td width="33%">
				   		<label for="back_id"><strong class="fb">回执单号：</strong></label>
				   		<input class="easyui-validatebox" type="text" name="back_id" id="back_id"></input>				   		
				   	</td>
		        	<td width="25%">
			            <label for="s_title"><strong class="fb">标&nbsp;&nbsp;&nbsp;&nbsp;题：</strong></label>  
						<input class="easyui-validatebox" type="text" name="s_title" id="s_title"></input>
		        	</td>
		        	<td>
			            <label for="type"><strong class="fb">申请类型：</strong></label>  
						<input id="type" name="type" style="width:150px" />
		        	</td>
				</tr>
		        <tr align="center">
				   	<td width="33%">
				   		<label for="deal_status"><strong class="fb">处理状态：</strong></label>
				   		<select id="deal_status" class="easyui-combobox" name="" style="width:150px" data-options="editable:false">
							<option value="">-请选择-</option>
							<option value="0">未处理</option>
							<option value="1">处理中</option>
							<option value="2">已处理</option>
							<option value="-1">已驳回</option>
				   		</select>
				   	</td>
				   	<td width="25%">
				   		<div id='audit_div' style="display: none">
				   		<label for="audit_status"><strong class="fb">审批状态：</strong></label>
				   		<select id="audit_status" class="easyui-combobox" name="" style="width:150px" data-options="editable:false">
							<option value="">-请选择-</option>
							<option value="0">待审批</option>
							<option value="1">已审批</option>
				   		</select>
				   		</div>
				   	</td>
		        	<td>
		        	</td>
				</tr>
		      </table>
		      </form>
		</div>
	 	<div id="tb" style="height: auto;padding-right:10px" align="right">
			<a href="#" class="easyui-linkbutton" id="search" iconCls="icon-search">查   询</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" id="add" iconCls="icon-add">新   增</a>
		</div>
	  	<div style="margin:5px">
			<table id="table" class="panel"  border="0" cellspacing="0" cellpadding="0"></table> 
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

			//初始化
			$.post('${ctx}/manager/admin/SystemAplication.do?method=init&mod_id='+mod_id,function(result){
				$("#nav").text(result.local_str);
				//审批状态条件的显隐
				if(result.count>0){
					$("#audit_div").show();	
				}else{
					$("#audit_div").hide();
				}

				//类型初始化
				$('#type').combobox({                 
					url:'${ctx}/manager/admin/SystemAplication.do?method=getType',  
				 	method:'post',              
				 	editable:false, //不可编辑状态                
				 	cache: false,               
				 	valueField:'C_INDEX',                   
					textField:'C_NAME'                   
				});
				$('#type').combobox('setValue','');

				//表格初始化
				$("#table").datagrid({
					title: '需求列表',
					url:'${ctx}/manager/admin/SystemAplication.do?method=queryList',
					method: 'post',
					queryParams:{
						back_id: $("#back_id").val(),
						s_title: $("#s_title").val(),
						type: $("#type").combobox("getValue"),
						deal_status: $("#deal_status").combobox("getValue"),
						audit_status: $("#audit_status").combobox("getValue")
					},
					fitColumns: true,
				    onLoadError:function(){
				    	$.messager.alert('温馨提示','数据请求失败!','error');  
				    },
				    loadMsg:'正在拼命加载中，请稍后。。。。',
				    remoteSort:false,
				    pagination:true,
				    rownumbers:true,
				    
				    
				    columns:[[
				     {title:'回执单号',field:'BACK_ID',width:140,editor:'text',sortable:'true',align:'center'},
				     {title:'分公司',field:'DEPT_NAME',width:120,editor:'text',align:'center'},
				     {title:'申请类型',field:'C_NAME',width:80,editor:'text',align:'center'},
				     {title:'标题',field:'TITLE',width:150,editor:'text',align:'center',
				    	 formatter: function(value,row,index){
							if(value){
								if(value.length > 10){
									return value.substr(0, 10)+'...'; 
								}else{
									return value;
								}
							}
				     	}
					 },
				     {title:'详情',field:'CONTENT',width:150,editor:'text',align:'center',
						formatter: function(value,row,index){
							if(value){
								value = value.replace(/#br#/g, '');//IE7-8    
								value = value.replace(/_@/g, '');//IE9、FF、chrome    
								value = value.replace(/\s/g, ' ');//空格处理
								if(value.length > 10){
									return value.substr(0, 10)+'...'; 
								}else{
									return value;
								}
							}
				     	}
					 },
				     {title:'申请时间',field:'CREATE_DATE',width:100,editor:'text',sortable:'true',align:'center'},
				     {title:'处理状态',field:'DEAL_STATUS',width:80,editor:'text',align:'center',
				    	 formatter: function(value,row,index){
				    	 	if(value==0){
					    	 	return '未处理';
				    	 	}else if(value==1){
								return '处理中';
				    	 	}else if(value==2){
				    	 		return '已处理';
				    	 	}else if(value==-1){
				    	 		return '已驳回';
				    	 	}
				     	 }
					 },
				     {title:'操作',field:'ID',width:100,editor:'text',align:'center',
				     	formatter: function(value,row,index){
				     		var append = "<span style='cursor:pointer;' class='fblue' onclick='location.href = \"${ctx}/manager/admin/SystemAplication/view.jsp?mod_id="+mod_id+"&id=" + value + "\"'>查看</span>";
				     		//审批人不为空，当前登录人审批级别等于下一级审批级别，处理状态不为已处理及已驳回，且未审批
					     	if(row.AUDIT_USER_ID != null && (parseInt(row.BAK1)+1) == row.AUDIT_LEVEL && row.DEAL_STATUS != 2 && row.DEAL_STATUS != -1 && row.AUDIT_RESULT == null){ 
					     		append += "&nbsp;|&nbsp;<span style='cursor:pointer;' class='fblue' onclick='location.href = \"${ctx}/manager/admin/SystemAplication/view.jsp?mod_id="+mod_id+"&id=" + value + "&deal=yes\"'>审批</span>";
					     	}
					     	//
					     	else if(row.AUDIT_USER_ID != null && row.BAK1 == row.AUDIT_LEVEL && row.AUDIT_RESULT != null){
					    	 	append += "&nbsp;|&nbsp;<span class='fblue' disabled='disabled'>已审</span>";
						    }
						    return append;
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
			    	pageSize:10,  
			    	//displayMsg:'当前显示从{from}到{to}，共' + 4 + '条记录',
				    onBeforeRefresh:function(pageNumber, pageSize){
				    	$(this).pagination({pageNumber:$(".pagination-num").val()});
				     	$(this).pagination('loading');
				    }
				});
				
				//自定义显示列
				function createColumnMenu(){  
	           		var tmenu = $('<div id="tmenu" style="width:100px;"></div>').appendTo('body');  
	          		var fields = $('#table').datagrid('getColumnFields');  
	          		for(var i=0; i<fields.length; i++){  
	               		$('<div iconCls="icon-ok"/>').html(fields[i]).appendTo(tmenu);  
	           		}  
	           		tmenu.menu({  
	                	onClick: function(item){  
		                    if (item.iconCls=='icon-ok'){  
		                    	$('#table').datagrid('hideColumn', item.text);  
		                       	tmenu.menu('setIcon', {  
		                        	target: item.target,  
		                            iconCls: 'icon-empty'  
		                       	});  
		                   	} else {  
		                        $('#table').datagrid('showColumn', item.text);  
		                        tmenu.menu('setIcon', {  
		                           target: item.target,  
		                           iconCls: 'icon-ok'  
		                        });  
		                   	}  
	              		}  
	            	});  
	       		}
			},'json');

			//新增
			$("#add").bind('click',function(){
				//$.messager.confirm('温馨提示', '是否使用已设定的审批流程？', function(r){  
				//	if (r){ //使用预设流程
				//		location.href="form.jsp?mod_id="+mod_id+"&use_flag=yes";
				//	}else if(r==undefined){
				//		alert("点击了关闭按钮");
				//	}else{//自定义流程
						location.href="form.jsp?mod_id="+mod_id+"&use_flag=no";
				//	}
				//});
				//location.href="form.jsp?mod_id="+mod_id;
			});

			//查询按钮绑定事件 
			$("#search").bind('click',function(){
				$('#table').datagrid('load',{
					back_id: $("#back_id").val(),
					s_title: $("#s_title").val(),
					type: $("#type").combobox("getValue"),
					deal_status: $("#deal_status").combobox("getValue"),
					audit_status: $("#audit_status").combobox("getValue")
		   		}); 
			});
		});
	</script>
	<jsp:include page="/__analytics.jsp" />
</body>
</html>
