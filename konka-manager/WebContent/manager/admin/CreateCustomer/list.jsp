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
		    		<input type="hidden" id='role_id1' name='role_id'/>
		    		<input type="hidden" id='role_id2' name='role_id'/>
		    		<input type="hidden" id='role_id3' name='role_id'/>
		    		<input type="hidden" id='role_id4' name='role_id'/>
					<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
						<tr align="center">
						    <td>
						    	<label for="dept_id"><strong class="fb">分 公 司 ：</strong></label>
						   		<input id="dept_id" name="dept_id" style="width:148px"/>	
						   	</td>
						   	<td>
						   		<label for="merge_type"><strong class="fb">客户名称：</strong></label>
						   		<input class="easyui-validatebox" type="text" name="cust_name" id="cust_name"></input>
						   	</td>
						   	<td>
						   		<label for="ywy_name"><strong class="fb">业 务 员：</strong></label>
						   		<input class="easyui-validatebox" type="text" name="ywy_name" id="ywy_name"></input>
						   	</td>
				        </tr>
						<tr align="center">
						    <td>
						    	<label for="cust_stat"><strong class="fb">建户状态：</strong></label>
						   		<select id="cust_stat" class="easyui-combobox" name="cust_stat" style="width:150px" >
									<option value="">-请选择-</option>
									<option value="0">待审核</option>
									<option value="1">审核中</option>
									<option value="2">已审核</option>
									<option value="3">已驳回</option>
									<option value="4">已同步</option>
						   		</select>
						   	</td>
						    <td>
						    	<label for="record_sn"><strong class="fb">单&nbsp;&nbsp;&nbsp;&nbsp;号：</strong></label>
						   		<input class="easyui-validatebox" type="text" name="record_sn" id="record_sn"></input>
						   	</td>
						   	<td>&nbsp;
						   	</td>
				        </tr>
		      		</table>
		      	</form>
		  	</div>
		  	<div id="tb" style="height: auto;width: 100%" align="left">
		  		<table width="100%">
		  			<tr>
		  				<td style="padding-left: 15px">
		  					<label id='show_add' style="display:none"><a href="#" class="easyui-linkbutton" id="add" iconCls="icon-add">新   增</a></label></td>
		  				<td style="padding-right: 15px" align="right"><a href="#" class="easyui-linkbutton" id="search" iconCls="icon-search">查   询</a></td>
		  			</tr>
		  		</table>
			</div>
			<div id='tabdiv' style="margin:5px;height: 500px;width: 800">
				<table id="table" width="100%" border="0" cellspacing="0" cellpadding="0"></table>  
		  	</div>
		  	<jsp:include page="/__analytics.jsp" />
		</div>
		<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script> 
		<script type="text/javascript" src="${ctx}/commons/scripts/jquery.easyui.min.js"></script>  
		<script type="text/javascript" src="${ctx}/commons/scripts/locale/easyui-lang-zh_CN.js"></script>
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
				$('#cust_stat').combobox({  
					editable:true, 
					filter: function(q, row){
					var opts = $(this).combobox('options');
					return row[opts.textField].indexOf(q) == 0;
				}
				}); 
				
				//初始化
				$.post('${ctx}/manager/admin/CreateCustomer.do?method=init&mod_id='+mod_id,function(result){
					$("#nav").text(result.local_str);
					$('#dept_id').combobox("setValue", result.dept_id);
					$('#role_id1').val(result.role_id1);
					$('#role_id2').val(result.role_id2);
					$('#role_id3').val(result.role_id3);
					$('#role_id4').val(result.role_id4);
					
					if(result.is_add){
						$("#show_add").show();
					}
					
					//表格初始化
					$("#table").datagrid({
						title: '建户申请列表',
						url:'${ctx}/manager/admin/CreateCustomer.do?method=queryList',
						method: 'post',
						queryParams:{
							dept_id: $("#dept_id").combobox("getValue"),
							cust_name: $("#cust_name").val(),
							ywy_name: $("#ywy_name").val(),
							record_sn: $("#record_sn").val(),
							cust_stat: $("#cust_stat").combobox("getValue"),
							role_id1: $('#role_id1').val(),
							role_id2: $('#role_id2').val(),
							role_id3: $('#role_id3').val(),
							role_id4: $('#role_id4').val(),
							type:'new'
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
					     {title:'单          号',field:'RECORD_SN',width:130,editor:'text',sortable:'true',align:'center'},
					     {title:'申请日期',field:'CREATE_DATE',width:120,editor:'text',sortable:'true',align:'center'},
					     {title:'分公司',field:'DEPT_NAME',width:70,editor:'text',sortable:'true',align:'center'},
					     {title:'经办名称',field:'JB_NAME',width:90,editor:'text',sortable:'true',align:'center'},
					     {title:'业务员',field:'USER_NAME',width:80,editor:'text',sortable:'true',align:'center'},
					     {title:'客户类别',field:'C_NAME',width:80,editor:'text',sortable:'true',align:'center'
					    	 /*formatter: function(value,row,index){
					    		 return "["+row.C_COMM+"]"+value;
					    	 }*/	 
					     },
					     {title:'客户名称',field:'CUST_NAME',width:180,editor:'text',sortable:'true',align:'left',
					    	 formatter: function(value,row,index){
					    		 return "<a title='点击查看详情' class='fblue' href='${ctx}/manager/admin/CreateCustomer.do?method=view&mod_id="+mod_id+"&cust_id="+row.CUST_ID+"&oper=view'>"+value+"</a>";
					    	 }	 
					     },
					     {title:'客户负责人',field:'LINK_MAN_NAME',width:80,editor:'text',sortable:'true',align:'center'},
					     {title:'联系电话',field:'LINK_MAN_TEL',width:80,editor:'text',sortable:'true',align:'center'},
					     {title:'当前审核节点',field:'ROLE_NAME',width:100,editor:'text',sortable:'true',align:'center'},
					     {title:'建户状态',field:'AUDIT_STAT',width:65,editor:'text',sortable:'true',align:'center',
					    	 formatter: function(value,row,index){
					    		 var stat = '';
					    		 if(value=='0'){
					    			 stat = '待审核';
					    		 }
					    		 if(value=='1'){
					    			 stat = '审核中';
					    		 }
					    		 if(value=='2'){
					    			 stat = '已审核';
					    		 }
					    		 if(value=='3'){
					    			 stat = '已驳回';
					    		 }
					    		 if(row.IS_SYN=='1'){
					    			 stat = '已同步';
					    		 }
					    		 return stat;
					    	 }
					     },
					     /*{title:'R3编码',field:'LINK_R3_CODE',width:80,editor:'text',sortable:'true',align:'center'},
					     {title:'省',field:'PROVINCE',width:60,editor:'text',sortable:'true',align:'center'},
					     {title:'市',field:'CITY',width:60,editor:'text',sortable:'true',align:'center'},
					     {title:'县',field:'COUNTRY',width:60,editor:'text',sortable:'true',align:'center'},
					     {title:'乡/镇',field:'TOWN',width:80,editor:'text',sortable:'true',align:'center'},*/
					     {title:'创建人',field:'REAL_NAME',width:80,editor:'text',sortable:'true',align:'center'},
					     {title:'操作',field:'IS_DEL',width:50,editor:'text',sortable:'true',align:'center',
					     	formatter: function(value,row,index){
					     		var str1 = "";
					     		if(row.ADD_USER_ID==result.current_user_id&&(row.AUDIT_STAT=='0'||row.AUDIT_STAT=='3')&&result.dept_id==row.SUBCOMP_ID){
					     			str1 = "<a class='fblue' href='${ctx}/manager/admin/CreateCustomer.do?method=toModify&mod_id="+mod_id+
					     					"&cust_id="+row.CUST_ID+"&role_id1="+$("#role_id1").val()+"&role_id2="+$("#role_id2").val()+
					     					"&role_id3="+$("#role_id3").val()+"&role_id4="+$("#role_id4").val()+"&role_id5="+$("#role_id5").val()+"'>修改</a>";
					     		}else{
					     			str1 = "<span class='fblue' style='color:#ccc;'>修改</span>";
					     		}
					     		
					     		return str1;
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
					    onBeforeRefresh:function(pageNumber, pageSize){
					    	$(this).pagination({pageNumber:$(".pagination-num").val()});
					     	$(this).pagination('loading');
					    }
					});
				},'json');
				 
				//查询按钮绑定事件 
				$("#search").bind('click',function(){
					$('#table').datagrid('load',{
						dept_id: $("#dept_id").combobox("getValue"),
						cust_name: $("#cust_name").val(),
						ywy_name: $("#ywy_name").val(),
						record_sn: $("#record_sn").val(),
						cust_stat: $("#cust_stat").combobox("getValue"),
						role_id1: $('#role_id1').val(),
						role_id2: $('#role_id2').val(),
						role_id3: $('#role_id3').val(),
						role_id4: $('#role_id4').val(),
						type:'new'
			   		}); 
				});
				
				//新增按钮绑定事件 
				$("#add").bind('click',function(){
					location.href='${ctx}/manager/admin/CreateCustomer.do?method=toAdd&mod_id='+mod_id;
				});
			});
		</script>
	</body>
</html>
