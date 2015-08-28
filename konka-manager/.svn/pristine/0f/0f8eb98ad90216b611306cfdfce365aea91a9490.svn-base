<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="MSThemeCompatible" content="no" />
		<meta http-equiv="X-UA-Compatible" content="IE=7" />
		<title>正常客户拜访</title>
		<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/commons/scripts/themes/default/easyui.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/commons/scripts/themes/icon.css"/>
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=647d1ae13c913717f13a9a8a6a9d6de2"></script>
	<script type="text/javascript" src="${ctx}/commons/scripts/convertor2.js"></script> 
		<style>
			.rtable1 td {
				padding:2px 5px;
			}
			.l-btn-plain{
				border: 1px solid #bbb; 
			}
			body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
		    #allmap{height:600px;width:90%;left:5%;top:5%}  
			
		</style>
	</head>
	<body >
		<div class="oarcont">
			<div class="oartop">
				<table width="400" border="0" cellpadding="0" cellspacing="0">
			    	<tr>
			        	<td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
			        	<td>&nbsp;当前位置：<span id='nav'>正常客户拜访</span></td> 
			      	</tr>
			    </table>
			</div>
			<!-- 搜索条件栏 -->
		  	<div style="padding:5px">  
		    	<form id="fm" method="post" >
		    		<input type="hidden" id='mod_id' value='${mod_id}'/>
		    		<input type="hidden" id='report_type' value='${report_type}'/>
		    		<input type="hidden" id='flag' value='${flag }'/>
					<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
				        <tr align="center">
						   	<td>
						   		<label for="subcomp_name_like"><strong class="fb">分公司:</strong></label> 
				   				<input  class="easyui-validatebox" type="text" name="subcomp_name_like" id="subcomp_name_like"/> 			   		
						   	</td>
				        	<td>
					            <label for="report_nae_like"><strong class="fb">上报人：</strong></label>  
					            <input class="easyui-validatebox" type="text" name="report_nae_like" id="report_nae_like"></input>
				        	</td>
				        	<td>
						   		<label for="state"><strong class="fb">拜访状态：</strong></label>
						   		<select id="state" class="easyui-combobox" name="state" style="width:150px" data-options="editable:false">
									<option value="">－请选择－</option>
					      		   <option value="0">需跟踪</option>
					      		   <option value="1">已关闭</option> 
						   		</select>
						   	</td>
						</tr>
						<tr align="center">
						    <td>
						    	<label for="r3_code_like"><strong class="fb">R3编码：</strong></label>
						   		<input class="easyui-validatebox" type="text" name="r3_code_like" id="r3_code_like"></input>	
						   	</td>
						   	<td>
						   		<label for="customer_name_like"><strong class="fb">客户名称：</strong></label>
						   		<input class="easyui-validatebox" type="text" name="customer_name_like" id="customer_name_like"></input>
						   	</td>
						   	<td>
						   		<label for="shop_name_like"><strong class="fb">门店名称：</strong></label>
						   		<input class="easyui-validatebox" type="text" name="shop_name_like" id="shop_name_like"></input>
						   	</td>
				        </tr>
						<tr align="center">
					    	<td>
				          		<label for="date_begin"><strong class="fb">时间：</strong></label> 
				          		<input id="_begin_date" class="easyui-datebox" style="width:90px;" name="_begin_date" editable="false"/>
				          		-
				          		<input id="_end_date" class="easyui-datebox" style="width:90px;" name="_end_date" editable="false"/>
				           	</td>
						   	<td>
						   		<label for="is_del"><strong class="fb">状态：</strong></label>
						   		<select id="is_del" class="easyui-combobox" name="is_del" style="width:150px" data-options="editable:false">
					      		   <option value="0">正常</option>  
					      		   <option value="1">已删除</option> 
						   		</select>
						   	</td>
						   	<td>
					            <label for="cust_type_id"><strong class="fb">客户类型：</strong></label> 
					             <input id="cust_type_id" name="cust_type_id"  style="width:150px" /> 
				        	</td>
				        </tr>
		      		</table>
		      	</form>
		  	</div>
		  	<!-- 查询和导出栏 --> 
		  	<div id="tb" style="height: auto;padding-right: 10px" align="right">
				<a href="#" class="easyui-linkbutton" id="search" iconCls="icon-search">查   询</a>
				&nbsp;
				<a href="#" class="easyui-linkbutton" id="exprot" iconCls="icon-redo">导   出</a>  
			</div>
			<!-- 增删改查工具栏 --> 
			<div id="toolbar" style="margin-left:5px;margin-top:5px;border-top-style: solid;border-top-width: 1px; border-top-color:#C9CFCD "> 
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新增</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="viewUser()">查看</a>
		    </div> 
		    <!-- list页面 --> 
			<div id="tabdiv" style="margin:5px;">    
				<table id="dg" width="100%" border="0" cellspacing="0" cellpadding="0"></table> 
		  	</div> 
		  	
		  	<!-- 新增和修改页面dialog -->
		  	<div id="dlg" class="easyui-dialog" style="width:700px;height:500px;padding:10px 20px"  closed="true" buttons="#dlg-buttons">  
		        <form id="em" method="post" novalidate="false" enctype="multipart/form-data"> 
		            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3"> 
		            <tr>
		            	<td width="50%">
		            		<label for="add_date"><strong class="fb">创建日期：</strong></label>
								   		<span id="add_date"></span>
		            	</td>
		            	<td width="50%">
		            		<label for="_begin_date"><strong class="fb">起始时间：</strong></label>
								   		<input id="_begin_date" class="easyui-datebox" style="width:90px;" required="required" name="_begin_date" editable="false"/>
		            	</td>
		            </tr>
		            <tr>
		            	<td width="50%">
		            		<label for="_end_date"><strong class="fb">结束时间：</strong></label>
								   		<input id="_end_date" class="easyui-datebox" style="width:90px;" name="_end_date" editable="false"/>
		            	</td>
		            	<td width="50%">
		            		<label for="r3_code"><strong class="fb">拜访客户：</strong></label>
								   		 <input id="r3_code" name="r3_code" class="easyui-combobox" style="width:150px" editable="false" data-options="required:true" /> 
		            	</td>
		            </tr>
		            <tr>
		            	<td width="50%">
		            		<label for="r3_code_show"><strong class="fb">客户编码：</strong></label>
								   		<span id="r3_code_show"></span>  
		            	</td>
		            	<td width="50%">
		            		<label for="shop_id"><strong class="fb">拜访门店：</strong></label>
								   		 <input id="shop_id" name="shop_id" class="easyui-combobox" style="width:150px" editable="false" data-options="required:true"/>
		            	</td>
		            </tr>
		            <tr>
		            	<td width="100%" colspan="2">
		            		<label for="visit_type_id"><strong class="fb">拜访类型：</strong></label>
								   		<input id="visit_type_id" name="visit_type_id" class="easyui-combobox" style="width:150px" editable="false" data-options="required:true"/>
		            	</td>
		            </tr>
		            <tr>	
		            	<td width="100%" colspan="2">
		            		<label for="feed_list"><strong class="fb">反馈问题：</strong></label>
								   		  <input name="feed_list" class="easyui-validatebox" id="feed_list" style="width:400px" data-options="required:true" />
		            	</td>
		            </tr>
		            <tr>
		            	<td width="100%" colspan="2">
		            		<label for="visit_desc"><strong class="fb">拜访说明：</strong></label>
								   		<input id="visit_desc" name="visit_desc" class="easyui-validatebox" style="width:400px" data-options="required:true"/> 
		            	</td>
		            </tr>
		            <tr>	
		            	<td width="100%" colspan="2"> 
		            		<label for="remark"><strong class="fb">备注说明：</strong></label>
								   		  <input name="remark" class="easyui-validatebox" id="remark" style="width:400px" data-options="required:true"/>     
		            	</td>
		            </tr>
		             <tr> 
		            	<td width="50%">
		            		<label for="domestic_ranking"><strong class="fb">国产排名：</strong></label>
		                	<select id="domestic_ranking" class="easyui-combobox" name="domestic_ranking" style="width:150px;" editable="false" data-options="required:true" >    
							    <option value="1">1</option>   
							    <option value="2">2</option>
							    <option value="3">3</option>
							    <option value="4">4</option> 
							    <option value="5">5</option> 
							    <option value="6">6</option> 
						    </select>
		            	</td>
		            	<td width="50%">
		            		<label for="consumer_sales"><strong class="fb">零售量（台）：</strong></label>
								   		  <input name="consumer_sales" class="easyui-textbox" id="consumer_sales" style="width:100px;" />    
		            	</td>
		            </tr>
		             <tr>
		            	<td width="30%"> 
		            		<label for="retail_sales"><strong class="fb">零售额（万元）：</strong></label>
								   		  <input name="retail_sales" class="easyui-textbox" id="retail_sales" />
		            	</td>
		            	<td width="70%">  
		            		<label for="consumer_name"><strong class="fb">被拜访人：</strong></label>
								   		  <input name="consumer_name" class="easyui-textbox" id="consumer_name" />
		            	</td>
		            </tr>
		             <tr>
		            	<td width="50%">
		            		<label for="consumer_phone"><strong class="fb">被拜访电话：</strong></label>
								   		  <input name="consumer_phone" class="easyui-textbox" id="consumer_phone" />
		            	</td>
		            	<td width="50%">
		            		<label for="state"><strong class="fb">拜访结果：</strong></label>
								<select id="state" class="easyui-combobox" name="state" style="width:150px;" data-options="required:true" >   
							    <option value="0">--需跟踪--</option>   
							    <option value="1">--已关闭--</option>
							    
						    </select>
		            	</td>
		            </tr>
		            <tr>
		                <td><strong class="fb">附件:</strong></td>  
						<td >        
			        		<div id="policydivFileHidden" style="display: none;" >
			               <input name="activity_policy" type="file" id="activity_policy" style="width: 200px;" />
			               <img src="../../images/x.gif" style="vertical-align:middle; cursor: pointer;" id="policyDelTr" title="删除"/>
			               </div>
			             	<div id="policydivFile">
			               <input name="policy_file" type="file" id="policy_file" style="width: 200px;" /> 
			               <img src="../../images/+.gif" style="vertical-align:middle; cursor: pointer;" id="policyAddTr" title="再添加一个" /></div>
						</td>
					</tr>
					<tr>
					      <td><strong class="fb">已有附件:</strong></td>  
					      <td  id="fj"></td> 
  					</tr>
		               
		            </table>
		        </form>
    		</div>
     <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">退出</a>
    </div> 
    
    <!-- map地图显示dialog -->
    <div id="dlgmap" class="easyui-dialog" style="width:600px;height:650px;padding:10px 20px"  closed="true" buttons="#dlgmap-buttons" resizable="true"> 
    	<div id="allmap"></div> 
    </div>
    <div id="dlgmap-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeMap();" style="width:90px">退出</a>
    </div>
    
    <!--查看页面显示dialog  -->
    <div id="dlgview" class="easyui-dialog" style="width:700px;height:500px;padding:10px 20px"  closed="true" buttons="#dlgview-buttons">  
		        <form id="vm" method="post" novalidate="false" enctype="multipart/form-data"> 
		            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3"> 
		            <tr>
		            	<td width="50%">
		            		<label for="add_date"><strong class="fb">创建日期：</strong></label>
								   		<span id="add_date"></span>
		            	</td>
		            	<td width="50%">
		            		<label for="_begin_date"><strong class="fb">起始时间：</strong></label>
								   		<input id="_begin_date" class="easyui-datebox" style="width:90px;" required="required" name="_begin_date" editable="false" disabled="disabled"/>
		            	</td>
		            </tr>
		            <tr>
		            	<td width="50%">
		            		<label for="_end_date"><strong class="fb">结束时间：</strong></label>
								   		<input id="_end_date" class="easyui-datebox" style="width:90px;" name="_end_date" editable="false" disabled="disabled"/>
		            	</td>
		            	<td width="50%">
		            		<label for="r3_code"><strong class="fb">拜访客户：</strong></label>
								   		 <input id="r3_code" name="r3_code" class="easyui-combobox" style="width:150px" editable="false" data-options="required:true" disabled="disabled" /> 
		            	</td>
		            </tr>
		            <tr>
		            	<td width="50%">
		            		<label for="r3_code_show"><strong class="fb">客户编码：</strong></label>
								   		<span id="r3_code_show"></span>  
		            	</td>
		            	<td width="50%">
		            		<label for="shop_id"><strong class="fb">拜访门店：</strong></label>
								   		 <input id="shop_id" name="shop_id" class="easyui-combobox" style="width:150px" editable="false" data-options="required:true" disabled="disabled"/>
		            	</td>
		            </tr>
		            <tr>
		            	<td width="100%" colspan="2">
		            		<label for="visit_type_id"><strong class="fb">拜访类型：</strong></label>
								   		<input id="visit_type_id" name="visit_type_id" class="easyui-combobox" style="width:150px" editable="false" data-options="required:true" disabled="disabled"/>
		            	</td>
		            </tr>
		            <tr>	
		            	<td width="100%" colspan="2">
		            		<label for="feed_list"><strong class="fb">反馈问题：</strong></label>
								   		  <input name="feed_list" class="easyui-validatebox" id="feed_list" style="width:400px" data-options="required:true" disabled="disabled" />
		            	</td>
		            </tr>
		            <tr>
		            	<td width="100%" colspan="2">
		            		<label for="visit_desc"><strong class="fb">拜访说明：</strong></label>
								   		<input id="visit_desc" name="visit_desc" class="easyui-validatebox" style="width:400px" data-options="required:true" disabled="disabled"/> 
		            	</td>
		            </tr>
		            <tr>	
		            	<td width="100%" colspan="2"> 
		            		<label for="remark"><strong class="fb">备注说明：</strong></label>
								   		  <input name="remark" class="easyui-validatebox" id="remark" style="width:400px" data-options="required:true" disabled="disabled"/>     
		            	</td>
		            </tr>
		             <tr> 
		            	<td width="50%">
		            		<label for="domestic_ranking"><strong class="fb">国产排名：</strong></label>
		                	<select id="domestic_ranking" class="easyui-combobox" name="domestic_ranking" style="width:150px;" editable="false" data-options="required:true" disabled="disabled" >    
							    <option value="1">1</option>   
							    <option value="2">2</option>
							    <option value="3">3</option>
							    <option value="4">4</option> 
							    <option value="5">5</option> 
							    <option value="6">6</option> 
						    </select>
		            	</td>
		            	<td width="50%">
		            		<label for="consumer_sales"><strong class="fb">零售量（台）：</strong></label>
								   		  <input name="consumer_sales" class="easyui-textbox" id="consumer_sales" style="width:100px;"  disabled="disabled"/>    
		            	</td>
		            </tr>
		             <tr>
		            	<td width="30%"> 
		            		<label for="retail_sales"><strong class="fb">零售额（万元）：</strong></label>
								   		  <input name="retail_sales" class="easyui-textbox" id="retail_sales" disabled="disabled"/>
		            	</td>
		            	<td width="70%">  
		            		<label for="consumer_name"><strong class="fb">被拜访人：</strong></label>
								   		  <input name="consumer_name" class="easyui-textbox" id="consumer_name" disabled="disabled"/>
		            	</td>
		            </tr>
		             <tr>
		            	<td width="50%">
		            		<label for="consumer_phone"><strong class="fb">被拜访电话：</strong></label>
								   		  <input name="consumer_phone" class="easyui-textbox" id="consumer_phone" disabled="disabled"/>
		            	</td>
		            	<td width="50%">
		            		<label for="state"><strong class="fb">拜访结果：</strong></label>
								<select id="state" class="easyui-combobox" name="state" style="width:150px;" data-options="required:true" disabled="disabled">   
							    <option value="0">--需跟踪--</option>   
							    <option value="1">--已关闭--</option>
						    </select>
		            	</td>
		            </tr> 
					<tr>
					      <td><strong class="fb">已有附件:</strong></td>  
					      <td  id="fj"></td> 
  					</tr>
		               
		            </table>
		        </form>
    		</div>
    		 <div id="dlgview-buttons">
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlgview').dialog('close')" style="width:90px">退出</a>
		    </div>  
    
		  	<jsp:include page="/__analytics.jsp" />
		</div>
		<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script> 
		<script type="text/javascript" src="${ctx}/commons/scripts/jquery.easyui.min.js"></script>  
		<script type="text/javascript" src="${ctx}/commons/scripts/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${ctx}/commons/scripts/public.js"></script> 
		<script type="text/javascript"> 
		 $(function(){
			 var mod_id = $("#mod_id").val(); 
			 if($("#fm #_begin_date").datebox('getValue')==''||$("#fm #_begin_date").datebox('getValue')==null){
			 	$("#fm #_begin_date").datebox('setValue','${af.map._begin_date}');
			 }
			 if($("#fm #_end_date").datebox('getValue')==''||$("#fm #_end_date").datebox('getValue')==null){
				$("#fm #_end_date").datebox('setValue','${af.map._end_date}');
			 } 
			 if($("#fm #is_del").combobox('getValue')==''||$("#fm #is_del").combobox('getValue')==null){
				 $("#fm #is_del").combobox("setValue","0");   
			 } 	
			 
			//网店类型初始化
			$('#cust_type_id').combobox({                 
				url:'${ctx}/manager/admin/KonkaMobileCustVisitNew.do?method=getCategoryType',  
			 	method:'post',              
			 	editable:false, //不可编辑状态                
			 	cache: false,               
			 	valueField:'PAR_INDEX',                   
				textField:'C_COMM'                   
			}); 

			
			  
			 
		  //表格初始化
			$("#dg").datagrid({
				title: '正常客户拜访列表',
				url:'${ctx}/manager/admin/KonkaMobileCustVisitNew.do?method=initList&mod_id='+mod_id,
				method: 'post',
				fitColumns: false, 
			    loadMsg:'正在拼命加载中，请稍后。。。。',
			    remoteSort:false,
			    pagination:true,
			    rownumbers:true, 
			    singleSelect:false,
			    frozenColumns: [[//选择框

				                 { field: 'ck', checkbox: true }

				                ]],    
				queryParams:{//参数列表
					subcomp_name_like: $("#subcomp_name_like").val(),
					report_nae_like: $("#report_nae_like").val(),
					state: $("#state").combobox("getValue"), 
					is_del: $("#is_del").combobox("getValue"), 
					r3_code_like: $("#r3_code_like").val(),
					customer_name_like: $("#customer_name_like").val(),
					shop_name_like: $("#shop_name_like").val(),
					_begin_date: $("#_begin_date").datebox('getValue'),
					_end_date: $("#_end_date").datebox('getValue'), 
					cust_type_id: $("#cust_type_id").combobox("getValue") 
				},  
				onLoadSuccess:function(data){
					$(".loan_ct_view_button").linkbutton({ text:'定位', plain:true, iconCls:'icon-ok'});  
				},
			    onLoadError:function(){
			    	$.messager.alert('温馨提示','数据请求失败!','error');  
			    },
			    loadMsg:'正在拼命加载中，请稍后。。。。',  
			    columns:[[
			     {title:'开始时间',field:'begin_date',width:120,editor:'text',sortable:'true',align:'center'},
			     {title:'结束时间',field:'end_date',width:120,editor:'text',sortable:'true',align:'center'},
			     {title:'分公司',field:'subcomp_name',width:80,editor:'text',sortable:'true',align:'center'},
			     {title:'上报人',field:'report_nae',width:80,editor:'text',sortable:'true',align:'center'},
			     {title:'客户编码',field:'r3_code',width:80,editor:'text',sortable:'true',align:'center'},
			     {title:'客户名称',field:'customer_name',width:200,editor:'text',sortable:'true',align:'center'},
			     {title:'门店名称',field:'shop_name',width:200,editor:'text',sortable:'true',align:'center'},
			     {title:'客户类型',field:'cust_type_name',width:80,editor:'text',sortable:'true',align:'center'},
			     {title:'拜访类型',field:'visit_type_names',width:150,editor:'text',sortable:'true',align:'center'},
			     {title:'反馈问题',field:'feed_list',width:200,editor:'text',sortable:'true',align:'center'},
			     {title:'拜访说明',field:'visit_desc',width:200,editor:'text',sortable:'true',align:'center'},
			     {title:'被访人',field:'consumer_name',width:80,editor:'text',sortable:'true',align:'center'},
			     {title:'被访人电话',field:'consumer_phone',width:80,editor:'text',sortable:'true',align:'center'},
			     {title:'拜访状态',field:'state',width:80,editor:'text',sortable:'true',align:'center'},
			     {title:'添加时间',field:'add_date',width:120,editor:'text',sortable:'true',align:'center'}, 
			     {title:'状态',field:'is_del',width:80,editor:'text',sortable:'true',align:'center'},
			     {title:'数据来源',field:'data_source',width:80,editor:'text',sortable:'true',align:'center'},
			     {title:'附件',field:'att',width:80,editor:'text',sortable:'true',align:'center'},
			     {title:'当前位置',field:'address',width:240,editor:'text',sortable:'true',align:'center'}
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

			//查询按钮绑定事件 
			$("#search").bind('click',function(){
				$('#dg').datagrid('load',{
					subcomp_name_like: $("#subcomp_name_like").val(),
					report_nae_like: $("#report_nae_like").val(),
					state: $("#state").combobox("getValue"), 
					is_del: $("#is_del").combobox("getValue"),  
					r3_code_like: $("#r3_code_like").val(),
					customer_name_like: $("#customer_name_like").val(),
					shop_name_like: $("#shop_name_like").val(),
					_begin_date: $("#_begin_date").datebox('getValue'),
					_end_date: $("#_end_date").datebox('getValue'),    
					cust_type_id: $("#cust_type_id").combobox("getValue")  
		   		}); 
			});


			$("#policyAddTr").click(function (){
		  	      $("#policydivFileHidden").clone().find("#activity_policy").attr("name", "policy_file_" + new Date().getTime()).end().appendTo($("#policydivFile")).show();
		  	      $("img[id='policyDelTr']").each(function(){
		  	          $(this).click(function (){
		  	              $(this).parent().remove(); 
		  	          });
		  	      });
		  	});  

			//导出
			$("#exprot").bind('click',function(){
				$.messager.confirm('温馨提示', '是否导出数据？', function(r){  
					if (r){  
						var str = '&subcomp_name_like='+$("#subcomp_name_like").val()+'&report_nae_like='+$("#report_nae_like").val()+
								  '&state='+$("#state").combobox("getValue")+'&is_del='+$("#is_del").combobox("getValue")+
								  '&r3_code_like='+$("#r3_code_like").val()+'&customer_name_like='+$("#customer_name_like").val()+
								  '&_begin_date='+$("#_begin_date").datebox('getValue')+'&_end_date='+$("#_end_date").datebox('getValue')+
								  '&shop_name_like='+$("#shop_name_like").val()+'&cust_type_id='+$("#cust_type_id").combobox("getValue");
						$("#fm").attr("action", "${ctx}/manager/admin/KonkaMobileCustVisitNew.do?method=exportData"+str);
			    		$("#fm").submit();
					}  
				});
			});

		     // 得到datagrid的pager对象，初始化分页栏
		    	var pager = $('#dg').datagrid('getPager');      
		    	pager.pagination({
		    		 showPageList:false,   
		    		 pageSize:10,//每页显示几条数据
		    		 pageList:[10,20,30,40],  //可选择显示多少条         
		    		 beforePageText: '第',//页数文本框前显示的汉字  
		    	     afterPageText: '页    共 {pages} 页',  
		    	     displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
		    	     onBeforeRefresh:function(pageNumber, pageSize){
		    	    	 $(this).pagination({pageNumber:$(".pagination-num").val()});
		    	     	$(this).pagination('loading');
		    	    } 
		    	});
		    });

		 var url;
		    function newUser(){
		    	 $.post('${ctx}/manager/admin/KonkaMobileCustVisitNew.do?method=addInit',function(data){
		            	$("#em #add_date").text(data.add_date);       
		          });
			      
		    	$('#em #r3_code').combobox({                 
					url:'${ctx}/manager/admin/KonkaMobileCustVisitNew.do?method=getR3Cust',  
				 	method:'post',              
				 	editable:false, //不可编辑状态                
				 	cache: false,               
				 	valueField:'CUSTOMER_CODE',                   
					textField:'CUSTOMER_NAME'                   
				});
		    	$('#em #shop_id').combobox({                 
					url:'${ctx}/manager/admin/KonkaMobileCustVisitNew.do?method=getR3Shop',  
				 	method:'post',              
				 	editable:false, //不可编辑状态                
				 	cache: false,               
				 	valueField:'STORE_ID',                   
					textField:'STORE_NAME'                   
				}); 
		    	$('#em #visit_type_id').combobox({                 
					url:'${ctx}/manager/admin/KonkaMobileCustVisitNew.do?method=getVisitType',  
				 	method:'post',              
				 	editable:false, //不可编辑状态                
				 	cache: false, 
					multiple:true, //多选                 
				 	valueField:'VISIT_TYPE_ID',                   
					textField:'VISIT_TYPE_NAME'                   
				}); 

		    	$("#em div[id='policydivFileHidden']:gt(0)").remove();//清空之前的div  
		    	

		        $('#dlg').dialog('open').dialog('setTitle','新增数据'); 
		        $('#em').form('clear');   
		        url = '${ctx}/manager/admin/KonkaMobileCustVisitNew.do?method=easyuiSave';
		    }


		    function editUser(){
		        // var row = $('#dg').datagrid('getSelected');
		        
		     	 var flag = false;
		          var row = $('#dg').datagrid('getSelections');
		          if(row.length == 1){  
		          	flag=true; 
		          }else{
		         	alert("请您选择一行数据!"); 
		           	flag = false;  
		           	return false;  
		          }   
		          var ids="";
		          for (var i = 0; i < row.length; i++) {  
		              //获取自定义table 的中的checkbox值  
		            	var id=row[i].visit_id;   //ID这个是你要在列表中取的单个id   
		       		ids = ids + id +",";
		          } 
		          ids = ids.substring(0,ids.length-1);
		         if (flag){
		        	 $('#em #r3_code').combobox({                 
							url:'${ctx}/manager/admin/KonkaMobileCustVisitNew.do?method=getR3Cust',  
						 	method:'post',              
						 	editable:false, //不可编辑状态                
						 	cache: false,               
						 	valueField:'CUSTOMER_CODE',                   
							textField:'CUSTOMER_NAME'                   
						});
				    	$('#em #shop_id').combobox({                 
							url:'${ctx}/manager/admin/KonkaMobileCustVisitNew.do?method=getR3Shop',  
						 	method:'post',              
						 	editable:false, //不可编辑状态                
						 	cache: false,               
						 	valueField:'STORE_ID',                   
							textField:'STORE_NAME'                   
						}); 
				    	$('#em #visit_type_id').combobox({                 
							url:'${ctx}/manager/admin/KonkaMobileCustVisitNew.do?method=getVisitType',  
						 	method:'post',              
						 	editable:false, //不可编辑状态                
						 	cache: false, 
							multiple:true, //多选                 
						 	valueField:'VISIT_TYPE_ID',                   
							textField:'VISIT_TYPE_NAME'                   
						}); 
				    $("#em #fj span").remove();	   
		             $.post('${ctx}/manager/admin/KonkaMobileCustVisitNew.do?method=easyuiEdit',{id:ids},function(data){
		             	$("#em #add_date").text(data.add_date);
		             	$("#em #r3_code_show").text(data.r3_code);    
		             	$("#em #_begin_date").datebox("setValue",data._begin_date);
		             	$("#em #_end_date").datebox("setValue",data._end_date);
		             	$("#em #r3_code").combobox("setValue",data.r3_code);
		             	$("#em #shop_id").combobox("setValue",data.shop_id);
		             	$('#em #visit_type_id').combobox("setValues", data.visit_type_ids.split(","));   
		             	$("#em #feed_list").val(data.feed_list); 
		             	$("#em #visit_desc").val(data.visit_desc);
		             	$("#em #remark").val(data.remark);
		             	$("#em #domestic_ranking").combobox("setValue",data.domestic_ranking);
		             	$("#em #consumer_sales").val(data.consumer_sales);
		             	$("#em #retail_sales").val(data.retail_sales);
		             	$("#em #consumer_name").val(data.consumer_name);
		             	$("#em #consumer_phone").val(data.consumer_phone);
		             	$("#em #state").combobox("setValue",data.state);
		             	$("#em #fj").append(data.att);
		             	 
		              });
		             $("#em div[id='policydivFileHidden']:gt(0)").remove();//清空之前的div   
		             $('#dlg').dialog('open').dialog('setTitle','编辑数据'); 
		             //$('#fm').form('load',row[0]);
		             url = '${ctx}/manager/admin/KonkaMobileCustVisitNew.do?method=easyuiSave&visit_id='+ids;
		         }else{
		        	 $.messager.show({    // show error message
		              title: 'Error',
		              msg: "请您选择一行数据!",
		              style:{
		          		right:'',
		          		top:document.body.scrollTop+document.documentElement.scrollTop,
		          		bottom:''
		          	}
		          });
		     	} 
		     }

		    function viewUser(){
		        // var row = $('#dg').datagrid('getSelected');
		        
		     	 var flag = false;
		          var row = $('#dg').datagrid('getSelections');
		          if(row.length == 1){  
		          	flag=true; 
		          }else{
		         	alert("请您选择一行数据!"); 
		           	flag = false;  
		           	return false;  
		          }   
		          var ids="";
		          for (var i = 0; i < row.length; i++) {  
		              //获取自定义table 的中的checkbox值  
		            	var id=row[i].visit_id;   //ID这个是你要在列表中取的单个id   
		       		ids = ids + id +",";
		          } 
		          ids = ids.substring(0,ids.length-1);
		         if (flag){
		        	 $('#vm #r3_code').combobox({                 
							url:'${ctx}/manager/admin/KonkaMobileCustVisitNew.do?method=getR3Cust',  
						 	method:'post',              
						 	editable:false, //不可编辑状态                
						 	cache: false,               
						 	valueField:'CUSTOMER_CODE',                   
							textField:'CUSTOMER_NAME'                   
						});
				    	$('#vm #shop_id').combobox({                 
							url:'${ctx}/manager/admin/KonkaMobileCustVisitNew.do?method=getR3Shop',  
						 	method:'post',              
						 	editable:false, //不可编辑状态                
						 	cache: false,               
						 	valueField:'STORE_ID',                   
							textField:'STORE_NAME'                   
						}); 
				    	$('#vm #visit_type_id').combobox({                 
							url:'${ctx}/manager/admin/KonkaMobileCustVisitNew.do?method=getVisitType',  
						 	method:'post',              
						 	editable:false, //不可编辑状态                
						 	cache: false, 
							multiple:true, //多选                 
						 	valueField:'VISIT_TYPE_ID',                   
							textField:'VISIT_TYPE_NAME'                   
						}); 
				    $("#vm #fj span").remove();	 
		             $.post('${ctx}/manager/admin/KonkaMobileCustVisitNew.do?method=easyuiView',{id:ids},function(data){
		             	$("#vm #add_date").text(data.add_date);
		             	$("#vm #r3_code_show").text(data.r3_code);    
		             	$("#vm #_begin_date").datebox("setValue",data._begin_date);
		             	$("#vm #_end_date").datebox("setValue",data._end_date);
		             	$("#vm #r3_code").combobox("setValue",data.r3_code);
		             	$("#vm #shop_id").combobox("setValue",data.shop_id);
		             	$('#vm #visit_type_id').combobox("setValues", data.visit_type_ids.split(","));   
		             	$("#vm #feed_list").val(data.feed_list); 
		             	$("#vm #visit_desc").val(data.visit_desc);
		             	$("#vm #remark").val(data.remark);
		             	$("#vm #domestic_ranking").combobox("setValue",data.domestic_ranking);
		             	$("#vm #consumer_sales").val(data.consumer_sales);
		             	$("#vm #retail_sales").val(data.retail_sales);
		             	$("#vm #consumer_name").val(data.consumer_name);
		             	$("#vm #consumer_phone").val(data.consumer_phone);
		             	$("#vm #state").combobox("setValue",data.state);
		             	$("#vm #fj").append(data.att);
		             	 
		              });
		             $('#dlgview').dialog('open').dialog('setTitle','查看数据');  
		             //$('#fm').form('load',row[0]);
		         }else{
		        	 $.messager.show({    // show error message
		              title: 'Error',
		              msg: "请您选择一行数据!",
		              style:{
		          		right:'',
		          		top:document.body.scrollTop+document.documentElement.scrollTop,
		          		bottom:''
		          	}
		          });
		     	} 
		     }
		     var map = new BMap.Map("allmap");  
		     //$("#allmap").children().remove(); 
		     function showMap(id){
				 $.post('${ctx}/manager/admin/KonkaMobileCustVisitNew.do?method=mapInit&id='+id,function(data){ 
		            	if(data.state=="0"){
		            		    var lng = data.lng;
		            			var lat = data.lat;
		            			map.clearOverlays();//清空标注 
		            			var center_point1 = new BMap.Point(lng,lat); // 创建点坐标
		            			map.centerAndZoom(center_point1,15);           // 初始化地图，设置中心点坐标和地图级别。
		            			map.addControl(new BMap.NavigationControl());      // 添加控件
		            			map.enableScrollWheelZoom();
		            			map.enableContinuousZoom(); 

		            			//坐标转换完之后的回调函数
		            			translateCallback = function (point){  
		            				var center_point = new BMap.Point(point.lng,point.lat);
		            				map.centerAndZoom(center_point,15);     
		            				var marker = new BMap.Marker(center_point);
		            				map.addOverlay(marker); 
		            				marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画      

		            				var gc = new BMap.Geocoder();
		            				var addr = "";  
		            				gc.getLocation(center_point, function(rs){
		            					var addComp = rs.addressComponents;
		            					addr = addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber;
		            				});     //真实经纬度转成百度坐标     
		            				setTimeout(function(){
		            					var opts = {
		            					  width : 200,     // 信息窗口宽度
		            					  height: 100,     // 信息窗口高度
		            					  title : "上报人的位置：" , // 信息窗口标题
		            					  enableMessage:false,//设置允许信息窗发送短息
		            					  message:"XXXX"
		            					};
		            					var infoWindow = new BMap.InfoWindow(addr, opts);  // 创建信息窗口对象 
		            					marker.addEventListener("click", function(){          
		            						map.openInfoWindow(infoWindow,center_point); //开启信息窗口
		            					});  
		            				}, 2000);  
		            				
		            			};

		            			BMap.Convertor.translate(center_point1,0,translateCallback);
		            			 $('#dlgmap').dialog('open').dialog('setTitle','地图展示');  
			            }else{


				        }       
		          });
				
			  }

			function closeMap(){
				$('#dlgmap').dialog('close'); 
			}  

		    function destroyUser(){
		        //var row = $('#dg').datagrid('getSelected'); //单行 getSelections
		        var flag = false;
		        var row = $('#dg').datagrid('getSelections');
		        if(row.length == 0){  
		        	alert("请您至少选择一行数据!"); 
		        	flag = false;  
		        	return false;  
		        }else{
		        	flag=true; 
		        }   
		        var ids="";
		        for (var i = 0; i < row.length; i++) {  
		            //获取自定义table 的中的checkbox值  
		          	var id=row[i].visit_id;   //ID这个是你要在列表中取的单个id   
		     		ids = ids + id +",";
		        } 
		        ids = ids.substring(0,ids.length-1);   
		        if (flag){
		            $.messager.confirm('Confirm','您确认删除选中的数据吗?',function(r){
		                if (r){
		                    $.post('${ctx}/manager/admin/KonkaMobileCustVisitNew.do?method=easyuiDel',{id:ids},function(result){
		                        if (result.success){
		                            $('#dg').datagrid('reload');    // reload the user data
		                        } else {
		                            $.messager.show({    // show error message
		                                title: 'Error',
		                                msg: result.errorMsg
		                            });
		                        }
		                    },'json');
		                }
		            });
		        }else{ 
		        	 $.messager.show({    // show error message
		                 title: 'Error',
		                 msg: "请您至少选择一行数据!",
		                 style:{
			         		right:'',
			         		top:document.body.scrollTop+document.documentElement.scrollTop,
			         		bottom:''
			         	}
		             });  
		    	}
		    }  
		    
		    

		    function saveUser(){
		        $('#em').form('submit',{
		            url: url,
		            onSubmit: function(){
						var r3_code =  $('#em #r3_code').combobox("getValue");
						if(r3_code == ''||r3_code == null){
							alert("拜访客户不能为空");
							return false;
						}
						var shop_id =  $('#em #shop_id').combobox("getValue");
						if(shop_id == ''||shop_id == null){
							alert("拜访门店不能为空");
							return false;
						}
						var visit_type_id =  $('#em #visit_type_id').combobox("getValue");
						if(visit_type_id == ''||visit_type_id == null){
							alert("拜访类型不能为空");
							return false;
						}
						var state =  $('#em #state').combobox("getValue");
						if(state == ''||state == null){
							alert("拜访结果不能为空");
							return false;
						}
						var domestic_ranking = $('#em #domestic_ranking').combobox("getValue");
						if(domestic_ranking == ''||domestic_ranking == null){
							alert("国产排名不能为空");  
							return false;
						}
				    },
		            success: function(result){
		                var result = eval('('+result+')');
		                if (result.rs=="1"){
		                    $.messager.show({
		                        title: 'Error',
		                        msg: result.errorMsg
		                    });
		                } else {
		                    $('#dlg').dialog('close');        // close the dialog
		                    $('#dg').datagrid('reload');    // reload the user data
		                }
		            }
		        });
		    }

		    function del(id){
		    	if(!confirm('确实要删除此附件？')) return;
		    	$.ajax({
		    		type: "POST",
		    		url: "${ctx}/manager/admin/KonkaMobileCustVisitNew.do?method=deleteFile",
		    		data: {"id":id},
		    		dataType: "text", 
		    		error: function (xhr, ajaxOptions, thrownError) { alert("数据加载请求失败【" + xhr.statusText + "," + xhr.responseText + "," + xhr.status + "," + thrownError +"】！"); },
		    		success: function(success) {
		    			if (success){
		    		  	  alert("恭喜您，删除附件成功!");  
		    	  	 	  $("#"+id).parent().remove();
		    	  	  } 	
		    	  		  else alert(" 很抱歉，删除附件出错!"); 
		    		}
		    	});
		    }
		    
		</script>
	</body>
</html>
