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
		        <tr align="center">
				   	<td>
				   		<label for="dept_id"><strong class="fb">分 公 司：</strong></label>
				   		<input id="dept_id" name="dept_id" style="width:150px"/>				   		
				   	</td>
		        	<td>
			            <label for="store_id"><strong class="fb">门 店 ID：</strong></label>  
						<input class="easyui-validatebox" type="text" name="store_id" id="store_id"></input>
		        	</td>
		        	<td>
		        		<label for="store_name"><strong class="fb">门店名称：</strong></label>  
						<input class="easyui-validatebox" type="text" name="store_name" id="store_name"></input>
		        	</td>
				</tr>
				<tr align="center">
				    <td >
				    	<label for="store_type"><strong class="fb">门店类型：</strong></label>
				   		<input id="store_type" name="store_type" style="width:150px"/>
				   	</td>
				   	<td>
				   		<label for="data_type"><strong class="fb">数据类别：</strong></label>
				   		<input id="data_type" name="data_type" style="width:150px"/>
				   	</td>
				   	<td>
				   		<label for="cxy_name"><strong class="fb">促 销 员：</strong></label>  
						<input class="easyui-validatebox" type="text" name="cxy_name" id="cxy_name"></input>
				   	</td>
		        </tr>
		        <tr align="center">
		          	<td>
		          		<label for="date_begin"><strong class="fb">时间：</strong></label> 
		          		<input id="date_begin" class="easyui-datebox" style="width:90px;" />
		          		-
		          		<input id="date_end" class="easyui-datebox" style="width:90px;" />
		           	</td>
		           	<td>
		           		<label for="model_name_like"><strong class="fb">型&nbsp;&nbsp;&nbsp;&nbsp;号：</strong></label>
		           		<input class="easyui-validatebox" type="text" name="model_name_like" id="model_name_like"></input> 
				   	</td>
				   	<td></td>
		        </tr>
		      </table>
		      </form>
		</div>
	 	<div id="tb" style="height: auto;padding-right:10px" align="right">
			<a href="#" class="easyui-linkbutton" id="search" iconCls="icon-search">查   询</a>&nbsp;
			<a href="#" class="easyui-linkbutton" id="exprot" iconCls="icon-redo">导   出</a>
		</div>
	  	<div class="rtabcont1" style="font-weight:700;color:#F00;margin-top: 0;margin-bottom: 0">
	  		零售额：<span id='allmoney'></span>&nbsp;元，零售量：<span id='allnum'></span>&nbsp;台
	  	</div>
	  	<div class="rtabcont1" style="font-weight:normal;color:#A8A8A8;margin-bottom: 0;margin-top: 0">
	  		注意：查询数据范围以您被授权查看数据的部门为基础. 
	  	</div>
	  
	  	<div style="margin:5px;height: 400px;width: 800">
			<table id="dg" width="100%" border="0" cellspacing="0" cellpadding="0"></table>
	  	</div>
	</div>	
	<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script> 
	<script type="text/javascript" src="${ctx}/commons/scripts/jquery.easyui.min.js"></script>  
	<script type="text/javascript" src="${ctx}/commons/scripts/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${ctx}/commons/scripts/public.js"></script>
	<script type="text/javascript">
		$(function(){
			//初始化查询总额
			$.post('${ctx}/manager/admin/StarOfDaBan.do?method=init&mod_id=520210',function(result){
				$("#nav").text(result.local_str);
				$("#date_begin").datebox('setValue',result.date_begin);
				$("#date_end").datebox('setValue',result.date_end);
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
				 
				//门店类型初始化
				$('#store_type').combobox({                 
				 	url:'${ctx}/manager/admin/StarOfDaBan.do?method=getStoreType',  
				 	method:'post',              
				 	editable:false, //不可编辑状态                
				 	cache: false,               
				 	valueField:'code',                   
				 	textField:'text'                   
				});
				
				//数据类别初始化
				$('#data_type').combobox({                 
				 	url:'${ctx}/manager/admin/StarOfDaBan.do?method=getDataType',  
				 	method:'post',              
				 	editable:false, //不可编辑状态                
				 	cache: false,               
				 	valueField:'code',                   
				 	textField:'text'                
				}); 
				$('#data_type').combobox("setValue", "");
				
				//表格初始化
				$("#dg").datagrid({
					title: '门店零售排名',
					url:'${ctx}/manager/admin/StarOfDaBan.do?method=queryList',
					method: 'post',
					fitColumns: false,
					queryParams:{
						dept_id: $("#dept_id").combobox('getValue'),
						store_id: $("#store_id").val(),
						store_name: $("#store_name").val(),
						store_type: $("#store_type").combobox('getValue'),
						data_type: $("#data_type").combobox('getValue'),
						cxy_name: $("#cxy_name").val(),
						date_begin: $("#date_begin").datebox('getValue'),
						date_end: $("#date_end").datebox('getValue'),
						model_name_like: $("#model_name_like").val()
					},
					onLoadSuccess:function(data){
						$("#allnum").text(commafy(data.ALLNUM));
						$("#allmoney").text(commafy(data.ALLMONEY));
					},
				    onLoadError:function(){
				    	$.messager.alert('温馨提示','数据请求失败!','error');  
				    },
				    loadMsg:'正在拼命加载中，请稍后。。。。',
				    remoteSort:false,
				    pagination:true,
				    columns:[[
				     {title:'排名',field:'STORE_RANK',width:30,editor:'text',sortable:'true',align:'center'},
				     {title:'分公司',field:'SUBCOMP_NAME',width:60,editor:'text'},
				     {title:'门店ID',field:'DEPT_ID',width:80,editor:'text'},
				     {title:'门店名称',field:'DEPT_NAME',width:200,editor:'text'},
				     {title:'门店类型',field:'STORE_TYPE',width:60,editor:'text',sortable:'true',align:'center'},
				     {title:'促销员',field:'CXY_NAME',width:80,editor:'text'},
				     {title:'销售量',field:'NUM',width:60,editor:'text',sortable:'true',align:'right',
				    	 formatter: function(value,row,index){
				    	 	return commafy(value);
				     	 }
				     },
				     {title:'销售额',field:'ALL_PRICE',width:100,editor:'text',sortable:'true',align:'right',
				    	 formatter: function(value,row,index){
				    	 	return commafy(value);
				     	 }
					 },
				     {title:'省',field:'PROVINCE',width:60,editor:'text',align:'center'},
				     {title:'市',field:'CITY',width:80,editor:'text',align:'center'},
				     {title:'县',field:'COUNTRY',width:200,editor:'text',align:'center'},
				     {title:'镇',field:'TOWN',width:200,editor:'text',align:'center'}
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
				var pager = $('#dg').datagrid('getPager');  
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
	          		var fields = $('#dg').datagrid('getColumnFields');  
	          		for(var i=0; i<fields.length; i++){  
	               		$('<div iconCls="icon-ok"/>').html(fields[i]).appendTo(tmenu);  
	           		}  
	           		tmenu.menu({  
	                	onClick: function(item){  
		                    if (item.iconCls=='icon-ok'){  
		                    	$('#dg').datagrid('hideColumn', item.text);  
		                       	tmenu.menu('setIcon', {  
		                        	target: item.target,  
		                            iconCls: 'icon-empty'  
		                       	});  
		                   	} else {  
		                        $('#dg').datagrid('showColumn', item.text);  
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
				$('#dg').datagrid('load',{
		   			dept_id: $("#dept_id").combobox('getValue'),
					store_id: $("#store_id").val(),
					store_name: $("#store_name").val(),
					store_type: $("#store_type").combobox('getValue'),
					data_type: $("#data_type").combobox('getValue'),
					cxy_name: $("#cxy_name").val(),
					date_begin: $("#date_begin").datebox('getValue'),
					date_end: $("#date_end").datebox('getValue'),
					model_name_like: $("#model_name_like").val()
		   		}); 
			});
			
			//导出
			$("#exprot").bind('click',function(){
				$.messager.confirm('温馨提示', '是否导出数据？', function(r){  
					if (r){  
						//CNZZ统计代码
						//_czc.push(["_trackEvent", "门店零售排名下载", "导出", "Excel", 0, "export_excel"]);
						var str = '&dept_id='+$("#dept_id").combobox('getValue')+'&store_id='+$("#store_id").val()+
								  '&store_name='+$("#store_name").val()+'&store_type='+$("#store_type").combobox('getValue')+
								  '&data_type='+$("#data_type").combobox('getValue')+'&cxy_name='+$("#cxy_name").val()+
								  '&date_begin='+$("#date_begin").datebox('getValue')+'&date_end='+$("#date_end").datebox('getValue')+
								  '&model_name_like='+$("#model_name_like").val();
						$("#fm").attr("action", "${ctx}/manager/admin/StarOfDaBan.do?method=exportData"+str);
			    		$("#fm").submit();
					}  
				});
			});
		});
	</script>
	<jsp:include page="/__analytics.jsp" />
</body>
</html>
