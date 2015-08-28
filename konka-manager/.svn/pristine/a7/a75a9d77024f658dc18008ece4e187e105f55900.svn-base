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
					<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
				        <tr align="center">
						   	<td>
						   		<label for="order_sn"><strong class="fb">订 单 号：</strong></label>
						   		<input class="easyui-validatebox" type="text" name="order_sn" id="order_sn"></input>		   		
						   	</td>
				        	<td>
					            <label for="goods_type"><strong class="fb">商品类型：</strong></label>  
					            <input class="easyui-validatebox" type="text" name="goods_type" id="goods_type"></input>
				        	</td>
				        	<td>
				        		<label for="goods_sn"><strong class="fb">商品/型号：</strong></label>  
								<input class="easyui-validatebox" type="text" name="goods_sn" id="goods_sn" onkeyup="upperCase(this.id)"></input>
				        	</td>
				        	<td>
				        		<label for="business_type"><strong class="fb">业务类型：</strong></label>  
								<select id="business_type" class="easyui-combobox" name="business_type" style="width:150px" data-options="editable:false">
									<option value="">-请选择-</option>
									<option value="0">进货</option>
									<option value="1">退货</option>
						   		</select>
				        	</td>
						</tr>
						<tr align="center">
						    <td >
						    	<label for="store_id"><strong class="fb">仓 库 名：</strong></label>
						   		<input class="easyui-validatebox" type="text" name="store_id" id="store_id"></input>
						   	</td>
						   	<td>
						   		<label for="partner_id"><strong class="fb">供&nbsp;应&nbsp;商：</strong></label>
						   		<input class="easyui-validatebox" type="text" name="partner_id" id="partner_id"></input>
						   	</td>
						   	<td>
						   		<label for="bills_stat"><strong class="fb">&nbsp;单据状态：</strong></label>  
								<select id="bills_stat" class="easyui-combobox" name="bills_stat" style="width:150px" data-options="editable:false">
									<option value="">-请选择-</option>
									<option value="1">未提交</option>
									<option value="2">审核中</option>
									<option value="3">已确认</option>
									<option value="4">已作废</option>
						   		</select>
						   	</td>
						   	<td>
						   		<label for="date_begin"><strong class="fb">时间：</strong></label> 
		          				<input id="date_begin" class="easyui-datebox" style="width:90px;" />
		          				-
		          				<input id="date_end" class="easyui-datebox" style="width:90px;" />
						   	</td>
				        </tr>
		      		</table>
		      	</form>
		  	</div>
		  	<div id="tb" style="height: auto;padding-right:10px" align="right">
				<a href="#" class="easyui-linkbutton" id="search" iconCls="icon-search">查   询</a>
				&nbsp;
				<a href="#" class="easyui-linkbutton" id="exprot" iconCls="icon-redo">导   出</a>
			</div>
			<div class="rtabcont1" style="font-weight:700;color:#F00;margin-top: 0;margin-bottom: 0">
	  			当前页合计：数量：<span id='current_num'>0</span>&nbsp;&nbsp;金额：<span id='current_money'>0</span>&nbsp;&nbsp;&nbsp;&nbsp;
	  			查询结果合计：数量：<span id='total_num'>0</span>&nbsp;&nbsp;金额：<span id='total_money'>0</span>
	  		</div>
			<div id='tabdiv' style="margin:5px;height: 400px;">
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
				$.post('${ctx}/customer/manager/KonkaOrderDetails.do?method=init&mod_id='+mod_id,function(result){
					$("#nav").text(result.local_str);
					$("#date_begin").datebox('setValue',result.date_begin);
					$("#date_end").datebox('setValue',result.date_end);
					
					//商品类型初始化
					$('#goods_type').combobox({                 
						url:'${ctx}/customer/manager/KonkaOrderDetails.do?method=getGoodsTypeList',  
					 	method:'post',              
					 	editable:false, //不可编辑状态                
					 	cache: false,               
					 	valueField:'TYPE_ID',                   
						textField:'TYPE_NAME'                   
					});
					$('#goods_type').combobox('setValue','');
					
					//初始化仓库名称列表
					$('#store_id').combobox({                 
						url:'${ctx}/customer/manager/KonkaOrderDetails.do?method=getStoreList',  
					 	method:'post',              
					 	editable:false, //不可编辑状态                
					 	cache: false,               
					 	valueField:'STORE_ID',                   
						textField:'STORE_NAME'                   
					});
					$('#store_id').combobox('setValue','');
					
					//初始化供应商列表
					$('#partner_id').combobox({                 
						url:'${ctx}/customer/manager/KonkaOrderDetails.do?method=getPartnerList',  
					 	method:'post',              
					 	editable:false, //不可编辑状态                
					 	cache: false,               
					 	valueField:'PARTNER_ID',                   
						textField:'PARTNER_NAME'                   
					});
					$('#partner_id').combobox('setValue','');

					//表格初始化
					$("#table").datagrid({
						title: '订单明细结果列表',
						url:'${ctx}/customer/manager/KonkaOrderDetails.do?method=queryList',
						method: 'post',
						queryParams:{
							order_sn: $("#order_sn").val(),
							goods_type: $("#goods_type").combobox('getValue'),
							goods_type_text: $("#goods_type").combobox('getText'),
							goods_sn: $("#goods_sn").val(),
							business_type: $("#business_type").combobox('getValue'),
							store_id: $("#store_id").combobox('getValue'),
							provider: $("#provider").val(),
							bills_stat: $("#bills_stat").combobox('getValue'),
							date_begin: $("#date_begin").datebox('getValue'),
							date_end: $("#date_end").datebox('getValue')
						},
						fitColumns: true,
					    onLoadError:function(){
					    	$.messager.alert('温馨提示','数据请求失败!','error');  
					    },
					    loadMsg:'正在拼命加载中，请稍后。。。。',
					    remoteSort:true,
					    pagination:true,
					    rownumbers:true,
					    columns:[[
					     {title:'订单号',field:'ORDER_SN',width:160,editor:'text',sortable:'true',align:'center'},
					     {title:'商品类型',field:'GOODS_TYPE',width:80,editor:'text',sortable:'true',align:'center'},
					     {title:'商品/型号',field:'GOODS_NAME',width:120,editor:'text',sortable:'true',align:'center'},
					     {title:'业务类型',field:'BES_TYPE',width:60,editor:'text',sortable:'true',align:'center',
					     	formatter: function(value,row,index){
					    	 	if(value==1){
					    	 		return '退货';
					    	 	}else{
					    	 		return '进货';
					    	 	}
					     	}
					     },
					     {title:'数量',field:'GOODS_NUM',width:80,editor:'text',sortable:'true',align:'center'},
					     {title:'单位',field:'GOODS_UNIT',width:40,editor:'text',sortable:'true',align:'center'},
					     {title:'单价',field:'GOODS_PRICE',width:100,editor:'text',sortable:'true',align:'center',
					     	formatter: function(value,row,index){
					    	 	return commafy(value);
					     	 }
					     },
					     {title:'订单金额',field:'ORDER_MONEY',width:100,editor:'text',sortable:'true',align:'center',
					     	formatter: function(value,row,index){
					     		return commafy(value);
					     	 }
					     },
					     {title:'实际金额',field:'TRUE_MONEY',width:100,editor:'text',sortable:'true',align:'center',
					     	formatter: function(value,row,index){
					    	 	return commafy(value);
					     	 }
					     },
					     {title:'仓库名',field:'STORE_NAME',width:100,editor:'text',sortable:'true',align:'center'},
					     {title:'供应商',field:'PARTNER_NAME',width:150,editor:'text',sortable:'true',align:'center'},
					     {title:'创建时间',field:'ORDER_DATE',width:80,editor:'text',sortable:'true',align:'center'},
					     {title:'单据状态',field:'AUDIT_STATE',width:60,editor:'text',sortable:'true',align:'center',
					     	formatter: function(value,row,index){
					    	 	if(value==1){
					    	 		return '未提交';
					    	 	}else if(value==2){
					    	 		return '审核中';
					    	 	}else if(value==3){
					    	 		return '已确认';
					    	 	}else if(value==4){
					    	 		return '已作废';
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
		               	},
		               	onLoadSuccess:function(data){
							$("#current_num").text(commafy(data.CUR_NUM));
							$("#current_money").text(commafy(data.CUR_MONEY));
							$("#total_num").text(commafy(data.TOTAL_NUM));
							$("#total_money").text(commafy(data.TOTAL_MONEY));
							$("#sort_text").val(data.SORT);
							$("#order_text").val(data.ORDER);
						}
					});
					
					// 得到datagrid的pager对象，初始化分页栏
					var pager = $('#table').datagrid('getPager');  
					pager.pagination({    
				   		showPageList:true, 
				   		pageList:[10,20,50],   
				    	pageSize:10,  
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
			   			order_sn: $("#order_sn").val(),
						goods_type: $("#goods_type").combobox('getValue'),
						goods_type_text: $("#goods_type").combobox('getText'),
						goods_sn: $("#goods_sn").val(),
						business_type: $("#business_type").combobox('getValue'),
						store_id: $("#store_id").combobox('getValue'),
						partner_id: $("#partner_id").combobox('getValue'),
						bills_stat: $("#bills_stat").combobox('getValue'),
						date_begin: $("#date_begin").datebox('getValue'),
						date_end: $("#date_end").datebox('getValue')
			   		}); 
				});
				
				//导出
				$("#exprot").bind('click',function(){
					$.messager.confirm('温馨提示', '是否导出数据？', function(r){  
						if (r){  
							var str = '&order_sn='+$("#order_sn").val()+'&goods_type='+$("#goods_type").combobox('getValue')+
									  '&goods_type_text='+$("#goods_type").combobox('getText')+'&goods_sn='+$("#goods_sn").val()+
									  '&business_type='+$("#business_type").combobox('getValue')+'&store_id='+$("#store_id").combobox('getValue')+
									  '&partner_id='+$("#partner_id").combobox('getValue')+'&bills_stat='+$("#bills_stat").combobox('getValue')+
									  '&date_begin='+$("#date_begin").datebox('getValue')+'&date_end='+$("#date_end").datebox('getValue')+
									  '&sort='+$("#sort_text").val()+'&order='+$("#order_text").val();
							$("#fm").attr("action", "${ctx}/customer/manager/KonkaOrderDetails.do?method=exportData"+str);
				    		$("#fm").submit();
						}  
					});
				});
			});

			function upperCase(x)
			{
			var y=document.getElementById(x).value
			document.getElementById(x).value=y.toUpperCase()
			}
		</script>
		<jsp:include page="/customer/__analytics.jsp" />
	</body>
</html>
