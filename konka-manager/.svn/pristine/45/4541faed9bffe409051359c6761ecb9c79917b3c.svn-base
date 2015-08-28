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
					<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
				        <tr align="center">
						   	<td>
						   		<label for="year_list"><strong class="fb">所属年份：</strong></label>
						   		<input id="year_list" name="year_list" style="width:150px"/>				   		
						   	</td>
				        	<td>
					            <label for="r3_code"><strong class="fb">&nbsp;&nbsp;R3编码：</strong></label>  
								<input class="easyui-validatebox" type="text" name="r3_code" id="r3_code"></input>
				        	</td>
				        	<td>
				        		<label for="cus_name"><strong class="fb">客户名称：</strong></label>  
								<input class="easyui-validatebox" type="text" name="cus_name" id="cus_name"></input>
				        	</td>
						</tr>
						<tr align="center">
						    <td >
						    	<label for="dept_id"><strong class="fb">分&nbsp;公&nbsp;司：</strong></label>
						   		<input id="dept_id" name="dept_id" style="width:150px"/>
						   	</td>
						   	<td>
						   		<label for="com_type"><strong class="fb">统计维度：</strong></label>
						   		<input id="com_type" name="com_type" style="width:150px"/>
						   	</td>
						   	<td>
						   		<label for="cus_type1"><strong class="fb">客户类型：</strong></label>  
								<input class="easyui-validatebox" type="text" name="cus_type1" id="cus_type1" style="width:80px"></input>
								<input class="easyui-validatebox" type="text" name="cus_type2" id="cus_type2" style="width:120px"></input>
						   	</td>
				        </tr>
		      		</table>
		      	</form>
		  	</div>
		  	<div id="tb" style="height: auto;padding-right:10px" align="right">
				<a href="#" class="easyui-linkbutton" id="search" iconCls="icon-search">查   询</a>
				&nbsp;
				<a href="#" class="easyui-linkbutton" id="exprot" iconCls="icon-redo">导   出</a>
				&nbsp;
				<a href="#" class="easyui-linkbutton" id="syn_data" iconCls="icon-reload" title="我们建议同步数据操作还是由系统自动完成!">同   步</a>
			</div>
			<br/>
			<div style="margin:5px">
				<table id="table" width="100%" border="0" cellspacing="0" cellpadding="0"></table> 
		  	</div>
		</div>
		<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script> 
		<script type="text/javascript" src="${ctx}/commons/scripts/jquery.easyui.min.js"></script>  
		<script type="text/javascript" src="${ctx}/commons/scripts/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${ctx}/commons/scripts/public.js"></script> 
		<script type="text/javascript">
			$(function(){
				//初始化
				$.post('${ctx}/manager/admin/KonkaOrderInfoStatistics.do?method=init&mod_id=206500',function(result){
					$("#nav").text(result.local_str);
					//年份初始化
					$('#year_list').combobox({                 
						url:'${ctx}/manager/admin/KonkaOrderInfoStatistics.do?method=getYearList',  
					 	method:'post',              
					 	editable:false, //不可编辑状态                
					 	cache: false,               
					 	valueField:'code_value',                   
						textField:'text_value'
					}); 
					$('#year_list').combobox("setValue",result.year);
					
					//分公司初始化
					$('#dept_id').combobox({                 
						url:'${ctx}/manager/admin/CsAjax.do?method=getAjaxKonkaDeptList',  
					 	method:'post',              
					 	editable:false, //不可编辑状态                
					 	cache: false,               
					 	valueField:'DEPT_ID',                   
						textField:'DEPT_NAME'                   
					}); 
					$('#dept_id').combobox("setValue", result.dept_id);

					//统计维度初始化
					$('#com_type').combobox({                 
						url:'${ctx}/manager/admin/KonkaOrderInfoStatistics.do?method=getComType',  
					 	method:'post',              
					 	editable:false, //不可编辑状态                
					 	cache: false,               
					 	valueField:'code_value',                   
						textField:'text_value'            
					});
					$('#com_type').combobox("setValue","2");

					//一级客户类型初始化
					var cus_type1 = $('#cus_type1').combobox({                 
						url:'${ctx}/manager/admin/CsAjax.do?method=getCusType&par_id=0',  
					 	method:'post',              
					 	editable:false, //不可编辑状态                
					 	cache: false,               
					 	valueField:'PAR_INDEX',                   
						textField:'C_COMM',
						onLoadSuccess: function () { //加载完成后,设置选中第一个
			            	var val = $(this).combobox("getData");
			                $(this).combobox("select", val[0].PAR_INDEX);
						},
						onSelect:function(record){  
							//刷新数据，重新读取省份下的城市，并清空当前输入的值  
							cus_type2.combobox({  
								disabled:false,  
								url:'${ctx}/manager/admin/CsAjax.do?method=getCusType&par_id='+cus_type1.combobox('getValue'),  
								valueField:'C_INDEX',                   
								textField:'C_NAME',
								editable:false, //不可编辑状态                
							 	cache: false,
								onLoadSuccess: function () { //加载完成后,设置选中第一个
					            	var val = $(this).combobox("getData");
					                $(this).combobox("select", val[0].C_INDEX);
								}
							}).combobox('clear');
						}  
					});

					//二级客户类型
					var cus_type2 = $('#cus_type2').combobox({  
						editable:false, //不可编辑状态                
					 	cache: false
					});

					//表格初始化
					$("#table").datagrid({
						title: '列表数据以集团封账月为核算标准月',
						url:'${ctx}/manager/admin/KonkaOrderInfoStatistics.do?method=queryList',
						method: 'post',
						fitColumns: false,
					    onLoadError:function(){
					    	$.messager.alert('温馨提示','数据请求失败!','error');  
					    },
					    queryParams:{
					    	year_list: $("#year_list").combobox('getValue'),
					    	r3_code: $("#r3_code").val(),
					    	cus_name: $("#cus_name").val(),
					    	dept_id: $("#dept_id").combobox('getValue'),
					    	com_type: $("#com_type").combobox('getValue'),
					    	cus_type1: $("#cus_type1").combobox('getValue'),
					    	cus_type2: $("#cus_type2").combobox('getValue')
						},
					    loadMsg:'正在拼命加载中，请稍后。。。。',
					    remoteSort:false,
					    pagination:true,
					    rownumbers:true,
					    columns:[[
					     {title:'分公司',field:'DEPT_NAME',width:60,editor:'text',align:'center'},
					     {title:'一级部门',field:'L4_DEPT_NAME',width:80,editor:'text',align:'center'},
					     {title:'二级部门',field:'L5_DEPT_NAME',width:80,editor:'text',align:'center'},
					     {title:'业务员',field:'YWY_USER_NAME',width:60,editor:'text',align:'center'},
					     {title:'客户类型',field:'PAR_CUSTOMER_TYPE_NAME',width:80,editor:'text',align:'center'},
					     {title:'细分类型',field:'CUSTOMER_TYPE_NAME',width:80,editor:'text',align:'center'},
					     {title:'客户名称',field:'CUSTOMER_NAME',width:200,editor:'text',align:'left'},
					     {title:'R3编码',field:'R3_CODE',width:60,editor:'text',align:'center'},
					     {title:'年份',field:'YEAR',width:40,editor:'text',align:'center'},
					     {title:'1月',field:'MONTH1',width:80,editor:'text',align:'right',sortable:'true',
					    	 formatter: function(value,row,index){
					    	 	return commafy(value);
					     	 }
					     },
					     {title:'2月',field:'MONTH2',width:80,editor:'text',align:'right',sortable:'true',
					    	 formatter: function(value,row,index){
					    	 	return commafy(value);
					     	 }
				     	 },
					     {title:'3月',field:'MONTH3',width:80,editor:'text',align:'right',sortable:'true',
				     		formatter: function(value,row,index){
					    	 	return commafy(value);
					     	 }
				     	 },
					     {title:'4月',field:'MONTH4',width:80,editor:'text',align:'right',sortable:'true',
				     		formatter: function(value,row,index){
					    	 	return commafy(value);
					     	 }
				     	 },
					     {title:'5月',field:'MONTH5',width:80,editor:'text',align:'right',sortable:'true',
				     		formatter: function(value,row,index){
					    	 	return commafy(value);
					     	 }
				     	 },
					     {title:'6月',field:'MONTH6',width:80,editor:'text',align:'right',sortable:'true',
				     		formatter: function(value,row,index){
					    	 	return commafy(value);
					     	 }
				     	 },
					     {title:'7月',field:'MONTH7',width:80,editor:'text',align:'right',sortable:'true',
				     		formatter: function(value,row,index){
					    	 	return commafy(value);
					     	 }
				     	 },
					     {title:'8月',field:'MONTH8',width:80,editor:'text',align:'right',sortable:'true',
				     		formatter: function(value,row,index){
					    	 	return commafy(value);
					     	 }
				     	 },
					     {title:'9月',field:'MONTH9',width:80,editor:'text',align:'right',sortable:'true',
				     		formatter: function(value,row,index){
					    	 	return commafy(value);
					     	 }
				     	 },
					     {title:'10月',field:'MONTH10',width:80,editor:'text',align:'right',sortable:'true',
				     		formatter: function(value,row,index){
					    	 	return commafy(value);
					     	 }
				     	 },
					     {title:'11月',field:'MONTH11',width:80,editor:'text',align:'right',sortable:'true',
				     		formatter: function(value,row,index){
					    	 	return commafy(value);
					     	 }
				     	 },
					     {title:'12月',field:'MONTH12',width:80,editor:'text',align:'right',sortable:'true',
				     		formatter: function(value,row,index){
					    	 	return commafy(value);
					     	 }
				     	 }
					    ]]
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
						year_list: $("#year_list").combobox('getValue'),
				    	r3_code: $("#r3_code").val(),
				    	cus_name: $("#cus_name").val(),
				    	dept_id: $("#dept_id").combobox('getValue'),
				    	com_type: $("#com_type").combobox('getValue'),
				    	cus_type1: $("#cus_type1").combobox('getValue'),
				    	cus_type2: $("#cus_type2").combobox('getValue')
			   		}); 
				});
				
				//导出
				$("#exprot").bind('click',function(){
					$.messager.confirm('温馨提示', '是否导出数据？', function(r){  
						if (r){ 
							//CNZZ统计代码
							var str = '&year_list='+$("#year_list").combobox('getValue')+'&r3_code='+$("#r3_code").val()+
									  '&cus_name='+$("#cus_name").val()+'&dept_id='+$("#dept_id").combobox('getValue')+
									  '&com_type='+$("#com_type").combobox('getValue')+'&cus_type1='+$("#cus_type1").combobox('getValue')+
									  '&cus_type2='+$("#cus_type2").combobox('getValue');
							$("#fm").attr("action", "${ctx}/manager/admin/KonkaOrderInfoStatistics.do?method=exportData"+str);
				    		$("#fm").submit();
						}  
					});
				});

				//同步
				$("#syn_data").bind('click',function(){
					$.messager.prompt('温馨提示', '请输入同步密码', function(r){  
						if ('951'==r){  
							var win = $.messager.progress({  
								title:'数据同步',  
								msg:'正在同步数据，请稍后。。。',
								interval:1000   //1秒走十分之一
							});  
							$.post('${ctx}/manager/admin/KonkaOrderInfoStatistics.do?method=synData',function(result){
								$.messager.progress('close');
								$.messager.alert('同步完成','已同步成功'+result.data_count+'条数据','info',function(r){
									$('#table').datagrid('load',{
										year_list: $("#year_list").combobox('getValue'),
								    	r3_code: $("#r3_code").val(),
								    	cus_name: $("#cus_name").val(),
								    	dept_id: $("#dept_id").combobox('getValue'),
								    	com_type: $("#com_type").combobox('getValue'),
								    	cus_type1: $("#cus_type1").combobox('getValue'),
								    	cus_type2: $("#cus_type2").combobox('getValue')
							   		}); 
								});
							});
						}else if(r==undefined){
							$.messager.progress('close');
						}else{
							$.messager.alert('温馨提示','密码错误！','info'); 
							
						}
					});
				});
			});
		</script>
		<jsp:include page="/__analytics.jsp" />
	</body>
</html>
