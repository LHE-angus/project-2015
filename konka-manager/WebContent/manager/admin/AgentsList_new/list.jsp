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
		  	<div style="padding:5px">
		    	<form id="fm" method="post" >
		    		<input type="hidden" id='sort_text' />
		    		<input type="hidden" id='order_text' />
		    		<input type="hidden" id='mod_id' value='${mod_id }'/>
		    		<input type="hidden" id='flag' value='${flag }'/>
		    		<input type="hidden" id='queryStr' value='${queryStr }'/>
		    		<input type="hidden" id='return_flag' value='${return_flag }'/>
					<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
				        <tr align="center">
						   	<td>
						   		<label for="dept_id"><strong class="fb">分 公 司：</strong></label>
				   				<input id="dept_id" name="dept_id" style="width:150px"/>			   		
						   	</td>
				        	<td>
					            <label for="agent_name"><strong class="fb">网点名称：</strong></label>  
					            <input class="easyui-validatebox" type="text" name="agent_name" id="agent_name" maxlength="30"></input>
				        	</td>
				        	<td>
				        		<label for="agent_id"><strong class="fb">网点编号：</strong></label>  
								<input class="easyui-validatebox" type="text" name="agent_id" id="agent_id" maxlength="30"></input>
				        	</td>
						</tr>
						<tr align="center">
						    <td>
						    	<label for="agent_level"><strong class="fb">网点级别：</strong></label>
						   		<input id="agent_level" name="agent_level" style="width:150px" maxlength="30"/>	
						   	</td>
						   	<td>
						   		<label for="r3_code"><strong class="fb">上级客户编码：</strong></label>
						   		<input class="easyui-validatebox" type="text" name="r3_code" id="r3_code" maxlength="30"></input>
						   	</td>
						   	<td>
						   		<label for="kh_name"><strong class="fb">上级客户名称：</strong></label>
						   		<input class="easyui-validatebox" type="text" name="kh_name" id="kh_name" maxlength="30"></input>
						   	</td>
				        </tr>
						<tr align="center">
						   	<td>
						   		<label for="ywy_name_like"><strong class="fb">业 务 员：</strong></label>
						   		<input class="easyui-validatebox" type="text" name="ywy_name_like" id="ywy_name_like" maxlength="30"></input>
						   	</td>
						   	<td>
						   		<label for="link_name"><strong class="fb">联 系 人：</strong></label>
						   		<input class="easyui-validatebox" type="text" name="link_name" id="link_name" maxlength="30"></input>
						   	</td>
						    <td>
						    	<label for="jb_name"><strong class="fb">经办名称：</strong></label>
						   		<input class="easyui-validatebox" type="text" name="jb_name" id="jb_name" maxlength="30"></input>
						   	</td>
				        </tr>
						<tr align="center">
						   	<td>
						   		<label for="is_stop"><strong class="fb">是否停用：</strong></label>
						   		<select id="is_stop" class="easyui-combobox" name="is_stop" style="width:150px" data-options="editable:false">
									<option value="">-请选择-</option>
									<option value="0">未停用</option>
									<option value="1">已停用</option>
						   		</select>
						   	</td>
						    <td>
						    	<label for="cus_type1"><strong class="fb">客户类型：</strong></label>  
								<input class="easyui-validatebox" type="text" name="cus_type1" id="cus_type1" style="width:80px"></input>
								<input class="easyui-validatebox" type="text" name="cus_type2" id="cus_type2" style="width:120px"></input>
						   	</td>
						   	<td>
						   		<label for="is_sure"><strong class="fb">确认状态：</strong></label>
						   		<select id="is_sure" class="easyui-combobox" name="is_sure" style="width:150px" data-options="editable:false">
									<option value="">-请选择-</option>
									<option value="1">待确认</option>
									<option value="2">已确认</option>
						   		</select>
						   	</td>
				        </tr>
		      		</table>
		      	</form>
		  	</div>
		  	<div id="tb" style="height: auto;">
		  		<table width="100%">
		  			<tr>
		  				<td style="padding-left:15px">
		  					<a href="#" class="easyui-linkbutton" id="add" iconCls="icon-add">新   增</a>
		  				</td>
		  				<td align="right" style="padding-right:15px">
							<a href="#" class="easyui-linkbutton" id="search" iconCls="icon-search">查   询</a>
							&nbsp;
							<a href="#" class="easyui-linkbutton" id="exprot" iconCls="icon-redo">导   出</a>
		  				</td>
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
				$.dialog({
					title:  "停用/启用申请",
					width:  570,
					height: 200,
			        lock:true ,
					content:"url:WaitAuditList/audit_Form.jsp?id="+id+"&flag="+flag+"&type=agent"
				});
			}
			
			//确认窗口
		    function surePage(id){
		    	
		    	$.dialog({
					title:  "停用/启用申请",
					width:  570,
					height: 240,
			        lock:true ,
					content:"url:WaitAuditList/audit_Form.jsp?id="+id+"&type=agent&sure=sure"
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
				$.post('${ctx}/manager/admin/AgentsList.do?method=init&mod_id='+mod_id,function(result){
					$("#nav").text(result.local_str);
					
					//分公司初始化
					$('#dept_id').combobox({                 
						url:'${ctx}/manager/admin/CsAjax.do?method=getAjaxKonkaDeptList',  
					 	method:'post',              
					 	editable:false, //不可编辑状态                
					 	cache: false,               
					 	valueField:'DEPT_ID',                   
						textField:'DEPT_NAME'                   
					}); 
					var old_dept_id = GetFormString("queryStr","dept_id"); 
					if(old_dept_id!=''){
						$('#dept_id').combobox("setValue", old_dept_id);
					}else{
						$('#dept_id').combobox("setValue", result.dept_id);
					}
					
					//网点级别初始化
					$('#agent_level').combobox({                 
						url:'${ctx}/manager/admin/CsAjax.do?method=getAjaxJBasePartnerList',  
					 	method:'post',              
					 	editable:false, //不可编辑状态                
					 	cache: false,               
					 	valueField:'P_LEVEL',                   
						textField:'LEVEL_NAME'                   
					}); 
					$('#agent_level').combobox("setValue", GetFormString("queryStr","agent_level"));
					
					var cus_type1_val = GetFormString("queryStr","cus_type1"); 
					var cus_type2_val = GetFormString("queryStr","cus_type2"); 
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
			            	if(return_flag=='1'){
								if(cus_type1_val!=''){
									$(this).combobox("select", cus_type1_val);
								}else{
					                $(this).combobox("select", val[0].PAR_INDEX);
								}
							}else{
								$(this).combobox("select", val[0].PAR_INDEX);
							}
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
					            	if(return_flag=='1'){
										if(cus_type2_val!=''){
											$(this).combobox("select", cus_type2_val);
										}else{
							                $(this).combobox("select", val[0].C_INDEX);
										}
									}else{
										$(this).combobox("select", val[0].C_INDEX);
									}
									
									return_flag = "";
									$("#return_flag").val("");
								}
							}).combobox('clear');
						}  
					});

					//二级客户类型
					var cus_type2 = $('#cus_type2').combobox({  
						editable:false, //不可编辑状态                
					 	cache: false
					});
					
					//默认查询未停用
					$('#is_stop').combobox("setValue", '0');
					//恢复查询条件
					$("#agent_name").val(GetFormString("queryStr","agent_name"));
					$("#agent_id").val(GetFormString("queryStr","agent_id"));
					$("#r3_code").val(GetFormString("queryStr","r3_code"));
					$("#kh_name").val(GetFormString("queryStr","kh_name"));
					$("#ywy_name_like").val(GetFormString("queryStr","ywy_name_like"));
					$("#link_name").val(GetFormString("queryStr","link_name"));
					$("#jb_name").val(GetFormString("queryStr","jb_name"));
					var is_stop = GetFormString("queryStr","is_stop");
					if(is_stop!=''){
						$('#is_stop').combobox("setValue", is_stop);
					}
					var is_sure = GetFormString("queryStr","is_sure");
					if(is_sure!=''){
						$('#is_sure').combobox("setValue", is_sure);
					}
					$("#r3_code").val(GetQueryString("r3_code"));
					
					//表格初始化
					$("#table").datagrid({
						title: '代理商网点列表',
						url:'${ctx}/manager/admin/AgentsList.do?method=queryList',
						method: 'post',
						queryParams:{
							dept_id: $("#dept_id").combobox("getValue"),
							agent_name: $("#agent_name").val(),
							agent_id: $("#agent_id").val(),
							agent_level: $("#agent_level").combobox("getValue"),
							r3_code: $("#r3_code").val(),
							kh_name: $("#kh_name").val(),
							ywy_name_like: $("#ywy_name_like").val(),
							link_name: $("#link_name").val(),
							jb_name: $("#jb_name").val(),
							cus_type1: cus_type1_val,
					    	cus_type2: cus_type2_val,
							is_stop: $("#is_stop").combobox("getValue"),
							is_sure: $("#is_sure").combobox("getValue")
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
					     {title:'分公司',field:'DEPT_NAME',width:70,editor:'text',sortable:'true',align:'center'},
					     {title:'经办名称',field:'JB_NAME',width:90,editor:'text',sortable:'true',align:'center'},
					     {title:'网点级别',field:'P_LEVEL',width:60,editor:'text',sortable:'true',align:'center',
					     	formatter: function(value,row,index){
					     		if(value==null){
					     			return "1级网点";
					     		}else{
									return value+"级网点";					     		
					     		}
				     	 	}
					     },
					     {title:'网点编号',field:'PARTNER_SN',width:110,editor:'text',sortable:'true',align:'center'},
					     {title:'网点名称',field:'PARTNER_NAME',width:150,editor:'text',sortable:'true',titleAlign:'center',
					     	formatter: function(value,row,index){
					     		return "<a title='点击查看网点详情' style='cursor:pointer;color:blue;' onclick='doNeedMethod(null,\"${ctx}/manager/admin/AgentsList.do\", \"view\",\""+mod_id+"\",\"partner_id="+row.PARTNER_ID+"&r3_id="+row.R3_ID+"&mod_id="+mod_id+"\",\"fm\")'>"+value+"</a>";
					     	}
					     },
					     {title:'上级客户',field:'CUSTOMER_NAME',width:150,editor:'text',sortable:'true',align:'center'},
					     {title:'上级客户编码',field:'R3_CODE',width:80,editor:'text',sortable:'true',align:'center'},
					     {title:'业务员岗位ID',field:'YWY_JOB_ID',width:80,editor:'text',sortable:'true',align:'center'},
					     {title:'业务员姓名',field:'REAL_NAME',width:65,editor:'text',sortable:'true',align:'center'},
					     {title:'联系人',field:'LINK_NAME',width:60,editor:'text',sortable:'true',align:'center'},
					     {title:'省',field:'PROVINCE',width:80,editor:'text',sortable:'true',align:'center'},
					     {title:'市',field:'CITY',width:80,editor:'text',sortable:'true',align:'center'},
					     {title:'县',field:'COUNTRY',width:60,editor:'text',sortable:'true',align:'center'},
					     {title:'乡/镇',field:'TOWN',width:60,editor:'text',sortable:'true',align:'center'},
					     {title:'街道地址',field:'PARTNER_ADDR',width:60,editor:'text',sortable:'true',align:'center'},
					     {title:'状态',field:'IS_DEL',width:40,editor:'text',sortable:'true',align:'center',
					    	 formatter: function(value,row,index){
					    		 if(value==1){
					    			 return "停用";
					    		 }else{
					    			 return "启用";
					    		 }
					     	 }
					     },
					     {title:'是否确认',field:'STATUS',width:60,editor:'text',sortable:'true',align:'center',
					    	 formatter: function(value,row,index){
					    		 if(value=='0'){
					    			 return "待确认";
					    		 }else if(value=='2'){
					    			 return "已确认";
					    		 }else{
					    			 return "已确认";
					    		 }
					     	 }	 
					     },
					     {title:'创建时间',field:'CREATE_DATE',width:70,editor:'text',sortable:'true',align:'center'},
					     {title:'维护时间',field:'MODIFY_DATE',width:70,editor:'text',sortable:'true',align:'center'},
					     {title:'创建人',field:'CREATE_MAN',width:60,editor:'text',sortable:'true',align:'center'},
					     {title:'维护人',field:'MODIFY_MAN',width:60,editor:'text',sortable:'true',align:'center'},
					     {title:'操作',field:'OPER',width:150,editor:'text',sortable:'true',align:'center',
					     	formatter: function(value,row,index){
								var sta = '';
					     		if(row.STATUS=='0'){
					     			if(result.fgs_m){
					     				sta = "<span style='cursor:pointer;' class='fblue' onClick='surePage(\""+row.AUDIT_ID+"\");'>确认</span>";
					     			}else{
					     				sta = "<span style='color:#CCC;'>确认</span>";
					     			}
					     		}else{
					     			sta = "<span style='color:#CCC;'>确认</span>";
					     		}
					     		if(row.IS_DEL==0){
						    	 	return "<span style='cursor:pointer;' class='fblue' onclick='doNeedMethod(null,\"${ctx}/manager/admin/AgentsList.do\", \"edit\",\""+mod_id+"\",\"partner_id="+row.PARTNER_ID+"&r3_id="+row.C_ID+"&local_dept_id="+result.dept_id+"\",\"fm\")'>修改</span>"+
						    	 		   	"  "+
						    	 		   "<span style='cursor:pointer;' class='fblue' onclick='stopOrstart(\""+row.PARTNER_ID+"\",\"stop\")'>停用</span>"+
						    	 		   "  "+sta+
						    	 		   "  <span style='cursor: pointer;' class='fblue' onclick='doNeedMethod(null, \"${ctx}/manager/admin/CustomerUsers.do\", \"userLogin\",\""+mod_id+"\",\"user_id="+row.USER_ID+"\")'>登陆</span>";
					     		}else{
					     			return "<span style='color:#CCC;'>修改</span>"+
				    	 		   		   "  "+
						    	 		   "<span style='cursor:pointer;' class='fblue' onclick='stopOrstart(\""+row.PARTNER_ID+"\",\"start\")'>启用</span>"+
					     					"  "+sta+
						    	 		   "  <span style='color:#CCC;'>登陆</span>";
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
					
					//新增按钮
					$("#add").bind('click',function(){
						location.href = "${ctx}/manager/admin/AgentsList.do?method=add&mod_id=" +mod_id + "&" + encodeURI($("#fm").serialize()) + "&local_dept_id="+result.dept_id;
					});
				},'json');
				 
				//查询按钮绑定事件 
				$("#search").bind('click',function(){
					$('#table').datagrid('load',{
			   			dept_id: $("#dept_id").combobox("getValue"),
						agent_name: $("#agent_name").val(),
						agent_id: $("#agent_id").val(),
						agent_level: $("#agent_level").combobox("getValue"),
						r3_code: $("#r3_code").val(),
						kh_name: $("#kh_name").val(),
						ywy_name_like: $("#ywy_name_like").val(),
						link_name: $("#link_name").val(),
						jb_name: $("#jb_name").val(),
						cus_type1: $("#cus_type1").combobox('getValue'),
				    	cus_type2: $("#cus_type2").combobox('getValue'),
						is_stop: $("#is_stop").combobox("getValue"),
						is_sure: $("#is_sure").combobox("getValue")
			   		}); 
				});
				
				//导出
				$("#exprot").bind('click',function(){
					$.messager.confirm('温馨提示', '是否导出数据？', function(r){  
						if (r){  
							var str = '&dept_id='+$("#dept_id").combobox('getValue')+'&agent_name='+$("#agent_name").val()+
									  '&agent_id='+$("#agent_id").val()+'&agent_level='+$("#agent_level").combobox('getValue')+
									  '&r3_code='+$("#r3_code").val()+'&kh_name='+$("#kh_name").val()+
									  '&jb_name='+$("#jb_name").val()+'&is_stop='+$("#is_stop").combobox('getValue')+
									  '&ywy_name_like='+$("#ywy_name_like").val()+'&link_name='+$("#link_name").val()+'&is_sure='+$("#is_sure").combobox('getValue');
							$("#fm").attr("action", "${ctx}/manager/admin/AgentsList.do?method=exportData"+str);
				    		$("#fm").submit();
						}  
					});
				});
				
			});
		</script>
	<jsp:include page="/__analytics.jsp" />
	</body>
</html>
