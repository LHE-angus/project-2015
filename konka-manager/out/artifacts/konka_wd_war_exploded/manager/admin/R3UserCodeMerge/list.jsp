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
					<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
				        <tr align="center">
						   	<td>
						   		<label for="merge_code"><strong class="fb">合并编码：</strong></label>
				   				<input class="easyui-validatebox" type="text" id="merge_code" name="merge_code"/>			   		
						   	</td>
				        	<td>
					            <label for="r3_code"><strong class="fb">R3编码：</strong></label>  
					            <input class="easyui-validatebox" type="text" name="r3_code" id="r3_code"></input>
				        	</td>
				        	<td>
				        		<label for="merge_name"><strong class="fb">合并客户名称：</strong></label>  
								<input class="easyui-validatebox" type="text" name="merge_name" id="merge_name"></input>
				        	</td>
						</tr>
						<tr align="center">
						    <td>
						    	<label for="dept_id"><strong class="fb">分 公 司 ：</strong></label>
						   		<input id="dept_id" name="dept_id" style="width:148px"/>	
						   	</td>
						   	<td>
						   		<!-- <label for="merge_type"><strong class="fb">合并客户类型：</strong></label>
						   		<input name="merge_type" id="merge_type" style="width:110px"></input> -->
						   		<label for="cus_type1"><strong class="fb">客户类型：</strong></label>  
								<input class="easyui-validatebox" type="text" name="cus_type1" id="cus_type1" style="width:80px"></input>
								<input class="easyui-validatebox" type="text" name="cus_type2" id="cus_type2" style="width:120px"></input>
						   	</td>
						   	<td>
						   		<label for="ywy_name"><strong class="fb">业 务 员：</strong></label>
						   		<input class="easyui-validatebox" type="text" name="ywy_name" id="ywy_name"></input>
						   	</td>
				        </tr>
				        <tr align="center">
				          <td>
				          	  <label for="cust_stat"><strong class="fb">客户状态：</strong></label>
				          	  <select id="cust_stat" class="easyui-combobox" name="cust_stat" style="width:150px" data-options="editable:false">
									<option value="">-请选择-</option>
									<option value="1">新客户</option>
									<option value="2">正式客户</option>
									<option value="3">静止客户</option>
									<option value="4">无效客户</option>
						   	  </select>
				          </td>
				          <td>
				          	<label for="area_name"><strong class="fb">所在区域：</strong></label>
				          	<select id="area_name" class="easyui-combobox" name="area_name" style="width:150px" data-options="editable:false">
								<option value="">-请选择-</option>
								<option value="华东">华东</option>
				                <option value="山东">山东</option>
								<option value="东北">东北</option>
								<option value="华北">华北</option>
								<option value="华南">华南</option>
								<option value="西南">西南</option>
								<option value="华中">华中</option>
								<option value="西北">西北</option>
					   	  	</select>
				          </td>
				          <td>
				          	 <label for="branch_name"><strong class="fb">事 业 部：</strong></label>
					         <select id="branch_name" class="easyui-combobox" name="branch_name" style="width:150px" data-options="editable:false">
								<option value="">-请选择-</option>
								<option value="1">白电</option>
								<option value="2">多媒体</option>
					   	  	</select>
				          </td>
				        </tr>
						<tr align="center">
						    <td>
						    	<label for="is_stop"><strong class="fb">是否停用：</strong></label>
						   		<select id="is_stop" class="easyui-combobox" name="is_stop" style="width:150px" data-options="editable:false">
									<option value="">-请选择-</option>
									<option value="0" selected="selected">未停用</option>
									<option value="1">已停用</option>
						   		</select>
						   	</td>
						    <td>
						    	<label for="is_sdf"><strong class="fb">R3分类：</strong></label>
						   		<select id="is_sdf" class="easyui-combobox" name="is_sdf" style="width:150px" data-options="editable:false">
									<option value="">-请选择-</option>
									<option value="0" selected="selected">售达方</option>
									<option value="1">送达方</option>
						   		</select>
						   	</td>
						   	<td>
						   		<label for="date_begin"><strong class="fb">加盟时间：</strong></label> 
				          		<input id="date_begin" class="easyui-datebox" style="width:90px;" />
				          		-
				          		<input id="date_end" class="easyui-datebox" style="width:90px;" />
						   	</td>
				        </tr>
		      		</table>
		      	</form>
		  	</div>
		  	<div id="tb" style="height: auto;padding-right: 10px" align="right">
				<a href="#" class="easyui-linkbutton" id="search" iconCls="icon-search">查   询</a>
				&nbsp;
				<a href="#" class="easyui-linkbutton" id="exprot" iconCls="icon-redo">导   出</a>
			</div>
			<div class="rtabcont1" style="font-weight:normal;margin-bottom: 0;margin-top: 0">
	  			截止到 <span id='current_date'></span>：合并客户数量：<span id='all_merge_cust' style="color: red">0</span>个&nbsp;&nbsp;&nbsp;&nbsp;交易客户：<span id='cust_num' style="color: red">0</span>个 
	  		</div>
			<div id='tabdiv' style="margin:5px;height: 550px;width: 800">
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
		
			/**
			 * 客户停用/启用
			 * @param msg
			 * @param page
			 * @param queryString
			 * @param method
			 * @return
			 */
			function stPartner(msg, alertmsg, page, method, queryString) {
				$.messager.confirm('温馨提示', msg, function(r){  
					if (r){  
						$.post(page + "?method=" + method + "&" + queryString,function(result){
							if(result.success == 1){
								$.messager.alert('温馨提示',alertmsg+'成功！','info',function(r){
									$('#table').datagrid('load',{
										merge_code: $("#merge_code").val(),
										r3_code: $("#r3_code").val(),
										merge_name: $("#merge_name").val(),
										dept_id: $("#dept_id").combobox("getValue"),
										cus_type1: $("#cus_type1").combobox('getValue'),
								    	cus_type2: $("#cus_type2").combobox('getValue'),
										ywy_name: $("#ywy_name").val(),
										cust_stat: $("#cust_stat").combobox("getValue"),
										area_name: $("#area_name").combobox("getValue"),
										branch_name: $("#branch_name").combobox("getValue"),
										is_stop: $("#is_stop").combobox("getValue"),
										is_sdf: $("#is_sdf").combobox("getValue"),
										date_begin: $("#date_begin").datebox("getValue"),
										date_end: $("#date_end").datebox("getValue")
							   		}); 
								});
							}else{
								if(result.msg != ''){
									$.messager.alert('温馨提示',result.msg,'info');
								}else{
									$.messager.alert('温馨提示',alertmsg+'失败！','info');
								}
							}
						},'json');
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
				
				//初始化
				$.post('${ctx}/manager/admin/R3UserCodeMerge.do?method=init&mod_id='+mod_id,function(result){
					$("#nav").text(result.local_str);
					$("#current_date").text(result.c_date);
					
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
						title: '客户列表',
						url:'${ctx}/manager/admin/R3UserCodeMerge.do?method=queryList',
						method: 'post',
						queryParams:{
							merge_code: $("#merge_code").val(),
							r3_code: $("#r3_code").val(),
							merge_name: $("#merge_name").val(),
							dept_id: $("#dept_id").combobox("getValue"),
							cus_type1: $("#cus_type1").combobox('getValue'),
					    	cus_type2: $("#cus_type2").combobox('getValue'),
							ywy_name: $("#ywy_name").val(),
							cust_stat: $("#cust_stat").combobox("getValue"),
							area_name: $("#area_name").combobox("getValue"),
							branch_name: $("#branch_name").combobox("getValue"),
							is_stop: $("#is_stop").combobox("getValue"),
							is_sdf: $("#is_sdf").combobox("getValue"),
							date_begin: $("#date_begin").datebox("getValue"),
							date_end: $("#date_end").datebox("getValue")
						},
						fitColumns: false,
					    onLoadError:function(){
					    	$.messager.alert('温馨提示','数据请求失败!','error');  
					    },
					    onLoadSuccess:function(data){
							$("#all_merge_cust").text(commafy(data.total));
							$("#cust_num").text(commafy(data.custCount));
						},
					    loadMsg:'正在拼命加载中，请稍后。。。。',
					    remoteSort:false,
					    pagination:true,
					    rownumbers:true,
					    columns:[[
					     {title:'分公司',field:'DEPT_NAME',width:70,editor:'text',sortable:'true',align:'center'},
					     {title:'合并编码',field:'R3_CODE',width:90,editor:'text',sortable:'true',align:'left',
					    	 formatter: function(value,row,index){
					    		 return "<a class='fblue' href='${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&customer_code="+value+"'>"+value+"</a>";
					    	 }
					     },
					     {title:'合并客户名称',field:'CUSTOMER_NAME',width:150,editor:'text',sortable:'true',align:'left'},
					     {title:'户头',field:'CUST_NUM',width:40,editor:'text',sortable:'true',align:'center'},
					     {title:'R3编码',field:'R3_CODE_LIST',width:200,editor:'text',sortable:'true',align:'left',
					    	 formatter: function(value,row,index){
					    		 var temp = value.split(",");
					    		 var append = '';
					    		 jQuery.each(temp,function(index,da){
					    			 if(index>0&&index<temp.length){
					    				 append += ",";
					    			 }
					    			 if(index%3==0&&index>0){
					    				 append += "<br/>";
					    			 }
					    			 append += "<a class='fblue' href='${ctx}/manager/admin/KonkaR3MmtMatch.do?method=detail&id="+row.ID+"&mod_id=101010&merge_flag=1'>"+da+"</a>";
								 });
					    		 return append;
					    	 }
					     },
					     {title:'客户类型',field:'C_NAME',width:80,editor:'text',sortable:'true',align:'center'},
					     {title:'合并客户状态',field:'SHOP_STATUS',width:80,editor:'text',sortable:'true',align:'center',
					    	 formatter: function(value,row,index){
					    		 if(value==1){
					    			 return "新客户";
					    		 }
								 if(value==2){
									 return "正式客户";				    			 
								 }
								 if(value==3){
									 return "静止客户";
								 }
								 if(value==4){
									 return "无效客户";
								 }
								 if(value==5){
									 return "门店网点"; 
								 }
					    	 }
					     },
					     {title:'区域信息',field:'AREA_NAME',width:80,editor:'text',sortable:'true',align:'center',
					    	 formatter: function(value,row,index){
					    		 if(value==null){
					    			 return row.DEPT_NAME;
					    		 }else{
					    			 return value+" - " + row.DEPT_NAME;
					    		 }
					    	 }
					     },
					     {title:'业务员',field:'USER_NAME',width:65,editor:'text',sortable:'true',align:'center'},
					     {title:'当月结算',field:'CUR_CLS_MONEY',width:60,editor:'text',sortable:'true',align:'center'},
					     {title:'去年同期',field:'LASTYEAR_CLS_MONEY',width:80,editor:'text',sortable:'true',align:'center'},
					     {title:'当月回款',field:'CUR_BACK_MONEY',width:80,editor:'text',sortable:'true',align:'center'},
					     {title:'去年同期',field:'LASTYEAR_BACK_MONEY',width:60,editor:'text',sortable:'true',align:'center'},
					     {title:'剩余额度',field:'CREDIT_BALANCE',width:60,editor:'text',sortable:'true',align:'center'},
					     {title:'加盟时间',field:'ADD_DATE',width:70,editor:'text',sortable:'true',align:'center'},
					     {title:'更新时间',field:'CREATE_DATE',width:70,editor:'text',sortable:'true',align:'center'},
					     {title:'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;作',field:'IS_DEL',width:150,editor:'text',sortable:'true',align:'left',
					     	formatter: function(value,row,index){
					     		var lng = '';
					     		if(row.B_LNG==null){
					     			lng = "<span style='color:#CCC;' title='该客户还未进行位置标注，无法获取其地理位置！''>位置</span>";
					     		}else{
					     			lng = "<a class='fblue' href='${ctx}/manager/admin/KonkaR3MmtMatch.do?method=locatInMap&mod_id="+mod_id+"&id="+row.ID+"'>位置</a>";
					     		}
					     		
					     		if(value==1){    //已停用
					     			return "<a style='color:#CCC'>门店</a>"+
					     					"&nbsp;&nbsp;"+
						    	 		   "<span style='cursor:pointer;' class='fblue' onclick='stPartner(\"是否启用该客户？\",\"客户启用\",\"${ctx}/manager/admin/R3UserCodeMerge.do\",\"stopOrStart\",\"id="+row.ID+"&mod_id="+mod_id+"&flag=start\")'>启用</span>"+
						    	 		  	"&nbsp;&nbsp;"+
						    	 		   "<a style='color:#CCC' title='该账户已被停用'>账户</a>"+
						    	 		  	"&nbsp;&nbsp;"+
						    	 		  	lng+"<br/>"+
						    	 		   "<a class='fblue' href='${ctx}/manager/admin/KonkaR3Backmoney.do?mod_id="+mod_id+"&r3_code="+row.R3_CODE+"&is_kh=1'>回款</a>"+
						    	 		  	"&nbsp;&nbsp;"+
						    	 		   "<a class='fblue' href='${ctx}/manager/admin/ChannelDataImport.do?mod_id="+mod_id+"&keyword="+row.R3_CODE+"&is_kh=1'>订单</a>"+
						    	 		  	"&nbsp;&nbsp;"+
						    	 		   "<a class='fblue' href='${ctx}/manager/admin/QueryCustomerAccount.do?mod_id="+mod_id+"&r3_code="+row.R3_CODE+"'>账期</a>";
					     		}else{
							   		return "<a class='fblue' href='${ctx}/manager/admin/KonkaR3Store.do?mod_id=106001&merge_r3_code="+row.R3_CODE+"'>门店</a>"+
							   				"&nbsp;&nbsp;"+
						    	 		   "<span style='cursor:pointer;' class='fblue' onclick='stPartner(\"是否停用该客户？\",\"客户停用\",\"${ctx}/manager/admin/R3UserCodeMerge.do\",\"stopOrStart\",\"id="+row.ID+"&mod_id="+mod_id+"&flag=stop\")'>停用</span>"+
						    	 		  	"&nbsp;&nbsp;"+
						    	 		   "<a class='fblue' href='${ctx}/manager/admin/CustomerUsers.do?method=list&mod_id="+mod_id+"&cust_id="+row.ID+"'>账户</a>"+
						    	 		  	"&nbsp;&nbsp;"+
						    	 		  	lng+"<br/>"+
						    	 		   "<a class='fblue' href='${ctx}/manager/admin/KonkaR3Backmoney.do?mod_id="+mod_id+"&r3_code="+row.R3_CODE+"&is_kh=1'>回款</a>"+
						    	 		  	"&nbsp;&nbsp;"+
						    	 		   "<a class='fblue' href='${ctx}/manager/admin/ChannelDataImport.do?mod_id="+mod_id+"&keyword="+row.R3_CODE+"&is_kh=1'>订单</a>"+
						    	 		  	"&nbsp;&nbsp;"+
						    	 		   "<a class='fblue' href='${ctx}/manager/admin/QueryCustomerAccount.do?mod_id="+mod_id+"&r3_code="+row.R3_CODE+"'>账期</a>";
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
						merge_code: $("#merge_code").val(),
						r3_code: $("#r3_code").val(),
						merge_name: $("#merge_name").val(),
						dept_id: $("#dept_id").combobox("getValue"),
						cus_type1: $("#cus_type1").combobox('getValue'),
				    	cus_type2: $("#cus_type2").combobox('getValue'),
						ywy_name: $("#ywy_name").val(),
						cust_stat: $("#cust_stat").combobox("getValue"),
						area_name: $("#area_name").combobox("getValue"),
						branch_name: $("#branch_name").combobox("getValue"),
						is_stop: $("#is_stop").combobox("getValue"),
						is_sdf: $("#is_sdf").combobox("getValue"),
						date_begin: $("#date_begin").datebox("getValue"),
						date_end: $("#date_end").datebox("getValue")
			   		}); 
				});
				
				//导出
				$("#exprot").bind('click',function(){
					$.messager.confirm('温馨提示', '是否导出数据？', function(r){  
						if (r){  
							//_czc.push(["_trackEvent", "R3客户管理下载", "导出", "Excel", 0, "export"]);
							var str = '&merge_code='+$("#merge_code").val()+'&r3_code='+$("#r3_code").val()+
									  '&merge_name='+$("#merge_name").val()+'&dept_id='+$("#dept_id").combobox('getValue')+
									  '&cus_type1='+$("#cus_type1").combobox("getValue")+'&cus_type2='+$("#cus_type2").combobox("getValue")+
									  '&ywy_name='+$("#ywy_name").val()+'&cust_stat='+$("#cust_stat").combobox('getValue')+
									  '&area_name='+$("#area_name").combobox("getValue")+'&branch_name='+$("#branch_name").combobox("getValue");
									  '&is_stop='+$("#is_stop").combobox("getValue")+'&is_sdf='+$("#is_sdf").combobox("getValue");
									  '&date_begin='+$("#date_begin").combobox("getValue")+'&date_end='+$("#date_end").combobox("getValue");
							$("#fm").attr("action", "${ctx}/manager/admin/R3UserCodeMerge.do?method=exportData"+str);
				    		$("#fm").submit();
						}  
					});
				});
			});
		</script>
	</body>
</html>
