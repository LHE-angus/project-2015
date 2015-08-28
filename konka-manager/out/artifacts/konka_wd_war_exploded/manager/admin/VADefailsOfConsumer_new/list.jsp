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
		        <tr>
		        	<td align="right">
		        		<strong class="fb">销售日期：</strong>
		        	</td>
		        	<td>
				   		<input id="date_begin" class="easyui-datebox" style="width:100px;" />
		          		-
		          		<input id="date_end" class="easyui-datebox" style="width:100px;" />
		        	</td>
				   	<td align="right">
				   		<strong class="fb">分公司/办事处：</strong>
				   	</td>
				   	<td>
				   		<input id="dept_id" name="dept_id" style="width:200px"/>				   		
				   	</td>
				</tr>
				<tr>
				   	<td align="right">
				   		<strong class="fb">门店名称：</strong>
				   	</td>
				   	<td>
				   		<input id="store_name" name="store_name" style="width:218px"/>
				   	</td>
				    <td align="right">
				    	<strong class="fb">产品品类/尺寸/型号：</strong>
				    </td>
				    <td>  
						<input class="easyui-validatebox" type="text" name="pro_type" id="pro_type" style="width:80px"></input>
						<input class="easyui-validatebox" type="text" name="pro_size" id="pro_size" style="width:90px"></input>
						<input class="easyui-validatebox" type="text" name="pro_modle" id="pro_modle" style="width:130px"></input>
				   	</td>
		        </tr>
		        <tr>
		          	<td align="right">
		          		<strong class="fb">消费者姓名：</strong> 
		          	</td>
		          	<td>
		          		<input id="xf_name" name="xf_name" style="width:218px"/>
		           	</td>
		           	<td align="right">
		           		<strong class="fb">联系电话：</strong>
		           	</td>
		           	<td>
		           		<input class="easyui-numberbox" id="telephone" name="telephone" style="width:218px"/> 
				   	</td>
		        </tr>
		        <tr>
		          	<td align="right">
		          		<strong class="fb">客户名称：</strong>
		          	</td>
		          	<td> 
		          		<input id="cust_name" name="cust_name" style="width:218px"/>
		           	</td>
		           	<td align="right">
		           		<strong class="fb">上报人员：</strong>
		           	</td>
		           	<td>
		           		<input class="easyui-validatebox" type="text" name="add_name" id="add_name"></input> 
				   	</td>
		        </tr>
		        <!-- <tr>
		        	<td align="right">
		        		<strong class="fb">是否有效：</strong>
		        	</td>
		        	<td>
		        		<select id="is_valid" class="easyui-combobox" name="is_valid" style="width:80px;" data-options="editable:false">
							<option value="0">有效</option>
							<option value="1">无效</option>
		        		</select>
		        	</td>
		        	<td colspan="2"></td>
		        </tr> -->
		      </table>
		      </form>
		</div>
	 	<div id="tb" style="height: auto;padding-right:10px" align="right">
			<a href="#" class="easyui-linkbutton" id="search" iconCls="icon-search">查   询</a>&nbsp;
			<a href="#" class="easyui-linkbutton" id="exprot" iconCls="icon-redo">导   出</a>
		</div>
	  
	  	<div style="margin-left:5px;margin-right:5px;margin-top:5px;">
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
			//获取模块id
			var mod_id = GetQueryString("mod_id");
			//初始化
			$.post('${ctx}/manager/admin/VADefailsOfConsumer.do?method=init&mod_id='+mod_id,function(result){
				$("#nav").text(result.local_str);
				$("#date_begin").datebox('setValue',result.date_begin);
				$("#date_end").datebox('setValue',result.date_end);
				
				//分公司/办事处初始化
				$('#dept_id').combobox({                 
					url:'${ctx}/manager/admin/VADefailsOfConsumer.do?method=getDeptList',  
				 	method:'post',              
				 	editable:false, //不可编辑状态                
				 	cache: false,               
				 	valueField:'DEPT_ID',                   
					textField:'TREE_NAME'                   
				}); 
				$('#dept_id').combobox("setValue", '');
				
				//产品品类
				var pro_type = $('#pro_type').combobox({                 
					url:'${ctx}/manager/admin/VADefailsOfConsumer.do?method=getProType',  
				 	method:'post',              
				 	editable:false, //不可编辑状态                
				 	cache: false,               
				 	valueField:'cls_id',                   
					textField:'cls_name',
					onSelect:function(record){  
						//刷新数据，重新读取品类
						pro_modle.combobox({  
							disabled:false,  
							url:'${ctx}/manager/admin/VADefailsOfConsumer.do?method=getProModel&pro_type='+$("#pro_type").combobox('getValue')+'&pro_size='+$('#pro_size').combobox('getValue'),
							method:'post',
							valueField:'PD_ID',                   
							textField:'MD_NAME',
							editable:false, //不可编辑状态                
						 	cache: false
						}).combobox('clear');
					}                  
				}); 
				$('#pro_type').combobox("setValue", '');
				
				//尺寸
				$('#pro_size').combobox({                 
					url:'${ctx}/manager/admin/VADefailsOfConsumer.do?method=getProSize',  
				 	method:'post',              
				 	editable:false, //不可编辑状态                
				 	cache: false,               
				 	valueField:'value',                   
					textField:'name',
					onSelect:function(record){  
						//刷新数据，重新读取品类
						pro_modle.combobox({  
							disabled:false,  
							url:'${ctx}/manager/admin/VADefailsOfConsumer.do?method=getProModel&pro_type='+$("#pro_type").combobox('getValue')+'&pro_size='+$('#pro_size').combobox('getValue'),
							valueField:'PD_ID',                   
							textField:'MD_NAME',
							editable:false, //不可编辑状态                
						 	cache: false
						}).combobox('clear');
					}                    
				}); 
				$('#pro_size').combobox("setValue", '');
				
				//型号
				var pro_modle = $('#pro_modle').combobox({  
					url:'${ctx}/manager/admin/VADefailsOfConsumer.do?method=getProModel&pro_type='+$("#pro_type").combobox('getValue')+'&pro_size='+$('#pro_size').combobox('getValue'),
					method:'post',
					valueField:'PD_ID',                   
					textField:'MD_NAME',
					editable:false, //不可编辑状态                
				 	cache: false
				});
				$('#pro_modle').combobox("setValue", '');
				
				//表格初始化
				$("#table").datagrid({
					title: '消费者信息',
					url:'${ctx}/manager/admin/VADefailsOfConsumer.do?method=queryList',
					method: 'post',
					queryParams:{
						date_begin: $("#date_begin").datebox('getValue'),
						date_end: $("#date_end").datebox('getValue'),
						dept_id: $("#dept_id").combobox('getValue'),
						store_name: $("#store_name").val(),
						pro_type: $("#pro_type").combobox('getValue'),
						pro_size: $("#pro_size").combobox('getValue'),
						pro_modle: $("#pro_modle").val(),
						xf_name: $("#xf_name").val(),
						telephone: $("#telephone").val(),
						cust_name: $("#cust_name").val(),
						add_name: $("#add_name").val()
						//is_valid: $("#is_valid").val()
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
				     {title:'消费者姓名',field:'BUYER_NAME',width:80,editor:'text',sortable:'true',align:'center'},
				     {title:'电话',field:'BUYER_TEL',width:100,editor:'text',align:'center',
				     	formatter: function(value,row,index){
				     		if(value == null){
					    	 	return "<span style='color:#C0C0C0'>未填写</span>";
				     		}else{
				     			return value;
				     		}
				     	}
				     },
				     {title:'身份证',field:'BUYER_ID',width:150,editor:'text',align:'center',
				     	formatter: function(value,row,index){
				     		if(value == null){
					    	 	return "<span style='color:#C0C0C0'>未填写</span>";
				     		}else{
				     			return value;
				     		}
				     	}
				     },
				     {title:'地址',field:'BUYER_LINK_ADDR',width:210,editor:'text',align:'center',
				     	formatter: function(value,row,index){
				     		if(value == null){
					    	 	return "<span style='color:#C0C0C0'>未填写</span>";
				     		}else{
				     			return value;
				     		}
				     	}
				     },
				     {title:'分公司',field:'DEPT_NAME',width:70,editor:'text',align:'center'},
				     {title:'经办',field:'L4_DEPT_NAME',width:90,editor:'text',sortable:'true',align:'center'},
				     {title:'客户名称',field:'CUSTOMER_NAME',width:180,editor:'text',align:'center'},
				     {title:'客户R3编码',field:'R3_CODE',width:90,editor:'text',align:'center'},
				     {title:'上报人',field:'ADD_USER_NAME',width:80,editor:'text',align:'center'},
				     {title:'门店名称',field:'STORE_NAME',width:190,editor:'text',align:'center'},
				     {title:'销售日期',field:'SELL_DATE',width:80,editor:'text',align:'center'},
				     {title:'产品型号',field:'MD_NAME',width:100,editor:'text',align:'center'},
				     {title:'数量',field:'COUNTS',width:50,editor:'text',align:'center'},
				     {title:'金额',field:'TOTAL_MONEY',width:100,editor:'text',align:'center'}
				     //{title:'状态',field:'ID6',width:100,editor:'text',align:'center',
				     //	formatter: function(value,row,index){
				     //		return "<span style='cursor:pointer;' class='fblue' onclick='location.href = \"${ctx}/manager/admin/SystemAplication/view.jsp?mod_id="+mod_id+"&id=" + value + "\"'>有效</span>"+
					 //   	 	   "&nbsp;|&nbsp;<span style='color:#C0C0C0'>无效</span>";
				     //	}
				     //}
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
			
			//查询按钮绑定事件 
			$("#search").bind('click',function(){
				$('#table').datagrid('load',{
		   			date_begin: $("#date_begin").datebox('getValue'),
					date_end: $("#date_end").datebox('getValue'),
					dept_id: $("#dept_id").combobox('getValue'),
					store_name: $("#store_name").val(),
					pro_type: $("#pro_type").combobox('getValue'),
					pro_size: $("#pro_size").combobox('getValue'),
					pro_modle: $("#pro_modle").combobox('getValue'),
					xf_name: $("#xf_name").val(),
					telephone: $("#telephone").val(),
					cust_name: $("#cust_name").val(),
					add_name: $("#add_name").val()
					//is_valid: $("#is_valid").val()
		   		}); 
			});
			
			//导出
			$("#exprot").bind('click',function(){
				$.messager.confirm('温馨提示', '是否导出数据？', function(r){  
					if (r){  
						var str = '&date_begin='+$("#date_begin").datebox('getValue')+'&date_end='+$("#date_end").datebox('getValue')+
								  '&dept_id='+$("#dept_id").combobox('getValue')+'&store_name='+$("#store_name").val()+
								  '&store_name='+$("#store_name").val()+'&pro_modle='+$("#pro_modle").combobox('getValue')+
								  '&xf_name='+$("#xf_name").val()+'&telephone='+$("#telephone").val()+
								  '&cust_name='+$("#cust_name").val()+'&add_name='+$("#add_name").val();
						$("#fm").attr("action", "${ctx}/manager/admin/VADefailsOfConsumer.do?method=exportData"+str);
			    		$("#fm").submit();
					}  
				});
			});
		});
	</script>
	<jsp:include page="/__analytics.jsp" />
</body>
</html>
