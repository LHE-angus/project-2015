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
			        	<td>&nbsp;当前位置：<span id='nav'></span></td>
			      	</tr>
			    </table>
			</div>
		  	<div style="padding:5px">
		    	<form id="fm" method="post" >
		    		<input type="hidden" id='sort_text' />
		    		<input type="hidden" id='order_text' />
		    		<input type="hidden" id='mod_id' value='${mod_id }'/>
		    		<input type="hidden" id='flag' value='${flag }'/>
		    		<input type="hidden" id='queryStr' value='${queryStr }'/>
					<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
						<tr align="center">
						    <td>
						    	<label for="dept_id"><strong class="fb">分公司 ：</strong></label>
						   		<input id="dept_id" name="dept_id" style="width:148px"/>	
						   	</td>
						   	<td>
						   		<label for="name_like"><strong class="fb">名称 ：</strong></label>
						   		<input id="name_like" name="name_like" style="width:148px"/>
						   	</td>
						   	<td>
						   		<label for="audit_type"><strong class="fb">类别：</strong></label>
						   		<select id="audit_type" class="easyui-combobox" name="audit_type" style="width:150px" data-options="editable:false">
									<option value="r3_shop">R3客户</option>
									<option value="agent">网点</option>
									<option value="store">门店</option>
						   		</select>
						   	</td>
						</tr>
						<tr align="center">
						   	<td>
						   		<label for="audit_status"><strong class="fb">审批状态：</strong></label>
						   		<select id="audit_status" class="easyui-combobox" name="audit_status" style="width:150px" data-options="editable:false">
									<option value="">-请选择-</option>
									<option value="0">待确认</option>
									<option value="1">已驳回</option>
									<option value="2">已确认</option>
						   		</select>
						   	</td>
						   	<td>
						   		<label for="ope_date_begin"><strong class="fb">时间 ：</strong></label>
						   		<input name="ope_date_begin" id="ope_date_begin" style="width: 110px" title="点击选择日期" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true})" />－
						   		<input name="ope_date_end" id="ope_date_end" style="width: 110px" title="点击选择日期" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true})" />
						   	</td>
						   	<td>
						   		<label for="oper_type"><strong class="fb">申请类型：</strong></label>
						   		<select id="oper_type" class="easyui-combobox" name="oper_type" style="width:150px" data-options="editable:false">
									<option value="">-请选择-</option>
									<option value="stop">停用</option>
									<option value="start">启用</option>
									<option value="modify">变更</option>
						   		</select>
						   	</td>
				        </tr>
						<tr align="center">
						   	<td colspan="2"></td>
						   	<td>
						   		<a href="#" class="easyui-linkbutton" id="search" iconCls="icon-search">查   询</a>
						   		&nbsp;
						   		<a href="#" class="easyui-linkbutton" id="exprot" iconCls="icon-redo">导   出</a>
						   	</td>
				        </tr>
				        
		      		</table>
		      	</form>
		  	</div>
			<div id='tabdiv' style="margin:5px;height: 400px;width: 800">
				<table id="table" class="panel" width="100%" border="0" cellspacing="0" cellpadding="0"></table>  
		  	</div>
		  	<jsp:include page="/__analytics.jsp" />
		</div>
		<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script> 
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
				//分公司初始化
				$('#dept_id').combobox({                 
					url:'${ctx}/manager/admin/CsAjax.do?method=getAjaxKonkaDeptList',  
				 	method:'post',              
				 	editable:false, //不可编辑状态                
				 	cache: false,               
				 	valueField:'DEPT_ID',                   
					textField:'DEPT_NAME'                   
				});
				
				
				//初始化
				$.post('${ctx}/manager/admin/WaitAuditList.do?method=init&mod_id='+mod_id,function(result){
					$("#nav").text(result.local_str);
					var dept_id = result.dept_id;
					$('#dept_id').combobox("setValue", dept_id);
					$("#ope_date_begin").val(result.date_begin);
					$("#ope_date_end").val(result.date_end);
					
					//恢复查询条件
					var dept_id = GetFormString("queryStr","dept_id");
					if(dept_id!=''){
						$('#dept_id').combobox("setValue", dept_id);
					}
					var audit_type = GetFormString("queryStr","audit_type");
					if(audit_type!=''){
						$('#audit_type').combobox("setValue", audit_type);
					}
					/* var audit_status = GetFormString("queryStr","audit_status");
					if(audit_status!=''){
						$('#audit_status').combobox("setValue", audit_status);
					} */
					
					//表格初始化
					$("#table").datagrid({
						title: '审批列表',
						url:'${ctx}/manager/admin/WaitAuditList.do?method=queryList',
						method: 'post',
						queryParams:{
							dept_id: $("#dept_id").combobox("getValue"),
							name_like: $("#name_like").val(),
							audit_type: $("#audit_type").combobox("getValue"),
							audit_status: $("#audit_status").combobox("getValue"),
							oper_type: $("#oper_type").combobox("getValue"),
							ope_date_begin: $("#ope_date_begin").val(),
							ope_date_end: $("#ope_date_end").val(),
							mod_id:mod_id
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
					     {title:'日期',field:'ADD_DATE',width:60,editor:'text',sortable:'true',align:'center'},
					     {title:'分公司',field:'DEPT_NAME',width:60,editor:'text',sortable:'true',align:'center'},
					     {title:'类别',field:'AUDIT_TYPE',width:50,editor:'text',sortable:'true',align:'center'},
					     {title:'名称',field:'TYPE_NAME',width:150,editor:'text',sortable:'true',align:'left',
					    	formatter: function(value,row,index){
					    		var dept_id = $("#dept_id").combobox("getValue");
							    var audit_type=$("#audit_type").combobox("getValue");
					    		return "<span style='cursor:pointer;' class='fblue' onclick='location.href = \"${ctx}/manager/admin/WaitAuditList/view.jsp?mod_id="+mod_id+"&id="+row.ID+"&dept_id="+dept_id+"&audit_type="+audit_type+"&operation=view"+ "\"'>"+value+"</span>";
					    	}	 
					     },
					     {title:'编码',field:'TYPE_CODE',width:80,editor:'text',sortable:'true',align:'center'},
					     {title:'申请类型',field:'OPER_NAME',width:50,editor:'text',sortable:'true',align:'center'},
					     {title:'申请原因',field:'OPER_REASON',width:200,editor:'text',sortable:'true',align:'left'},
					     {title:'确认状态',field:'STATUS',width:60,editor:'text',sortable:'true',align:'center',
					    	formatter: function(value,row,index){
					    		var stat = '';
					    		 if(value=='0'){
					    			 stat = '待确认';
					    		 }
					    		 if(value=='1'){
					    			 stat = '已驳回';
					    		 }
					    		 if(value=='2'){
					    			 stat = '已确认';
					    		 }
					    		 return stat;
					    	}	 
					     },
					     {title:'申请人',field:'CREAT_NAME',width:60,editor:'text',sortable:'true',align:'center'},
					     {title:'确认人',field:'MODIFY_NAME',width:60,editor:'text',sortable:'true',align:'center'}
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
					loadData();
				});
				
				function loadData(){
					$('#table').datagrid('load',{
						dept_id: $("#dept_id").combobox("getValue"),
						name_like: $("#name_like").val(),
						audit_type: $("#audit_type").combobox("getValue"),
						audit_status: $("#audit_status").combobox("getValue"),
						oper_type: $("#oper_type").combobox("getValue"),
						ope_date_begin: $("#ope_date_begin").val(),
						ope_date_end: $("#ope_date_end").val(),
						mod_id:mod_id
			   		}); 
				}
				
				//导出
				$("#exprot").bind('click',function(){
					$.messager.confirm('温馨提示', '是否导出数据？', function(r){  
						if (r){  
							//_czc.push(["_trackEvent", "维护记录下载", "导出", "Excel", 0, "export"]);
							var str = '&dept_id='+$("#dept_id").combobox('getValue')+'&name_like='+$("#name_like").val()+
									  '&audit_type='+$("#audit_type").combobox('getValue')+'&audit_status='+$("#audit_status").combobox('getValue')+
									  '&oper_type='+$("#oper_type").combobox('getValue')+'&ope_date_begin='+$("#ope_date_begin").val()+
									  '&ope_date_end='+$("#ope_date_end").val()+'&mod_id='+mod_id;
							$("#fm").attr("action", "${ctx}/manager/admin/WaitAuditList.do?method=exportData"+str);
				    		$("#fm").submit();
						}  
					});
				});
				
			});
		</script>
	</body>
</html>
