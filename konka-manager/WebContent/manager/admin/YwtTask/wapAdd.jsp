<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/commons/scripts/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/commons/scripts/themes/icon.css" />
<style type="text/css">
.rtable1 td {
	padding: 2px 5px;
}
</style>
<style type="text/css">
ul.ckUl{list-style-type:none;display:inline;}
ul.ckUl li{float:left;margin:auto 5px auto 0px;/*padding:2px 5px;*/}
input,textarea,select{font-family:Microsoft Yahei;font-size:12px;}
.ck-li{position:relative;}
.ck-li .hidden-accessible{position:absolute !important;clip:rect(1px 1px 1px 1px);}
.ck-li .ck-default{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #CCC;background: #F6F6F6;font-weight: bold;color:#C4C4C4;cursor:pointer;}
.ck-li .ck-hover{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #FBCB09; background:#FDF5CE;font-weight: bold;color:#C77405;cursor:pointer;}
.ck-li .ck-visited{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:2px solid #EF0F28/*#FF4800/*FBD850*/; background:white url("${ctx}/styles/customer/images/ck-visited.gif") right bottom no-repeat;font-weight:bold;color:#EF0F28/*#FF4800/*#EB8F00*/;cursor:pointer;}
</style>
</head>
<body>
<!-- 新增和修改页面dialog -->
      <form id="addOrEdit" method="post" novalidate="false" enctype="multipart/form-data"> 
          <table width="100%" border="0" align="right" cellspacing="0" cellpadding="0" class="rtable3"> 
          <tr>
          	<td>
			<label for="task_name"><strong class="fb">任务名称: </strong></label>
			 <input name="task_name" class="easyui-validatebox" id="task_name" style="width:110px"></input> 
			 <label for="task_type"><strong class="fb">任务类型：</strong></label>
		     <input class="easyui-combobox" id="task_type" name="task_type"></input> 
		     <label for="task_state"><strong class="fb">任务状态：</strong></label>
		     <input class="easyui-combobox" id="task_state" name="task_state" />
			 <input type="hidden" name="id" id="id"></input>
			 <input type="hidden" name="par_task_id" id="par_task_id"></input>
			  <input type="hidden" name="par_task_name" id="par_task_name"></input>    
			 <input type="hidden" name="mod_id" id="mod_id"></input> 
		     </td>
          	</tr>
          <tr>
          	<td>
			<label for="content"><strong class="fb">任务内容: </strong></label>
			<textarea id="content" name="content" class="easyui-validatebox" style="width:500px;height: 150px;" data-options="required:true"></textarea> 
		     </td>
		 </tr>
          <tr>
		     <td>
		     <input name="assigned_user_id" type="hidden" id="assigned_user_id"></input>
		     <label for="assigned_user_name"><strong class="fb">任务发起人：</strong></label>
		      <input name="assigned_user_name" disabled="disabled" id="assigned_user_name" style="width:110px"></input> 
		      <label for="add_date"><strong class="fb"> 添加日期：</strong></label>
		     <input id="add_date" name="add_date" disabled="disabled" class="easyui-datebox" style="width:100px;" />
			</td>
          </tr>
           <tr>
		     <td>
		     <label for="receive_user_id"><strong class="fb">指派给：</strong></label>
		      <input name="receive_user_id"  id="receive_user_id" style="width:110px"></input> 
		      <label for="finsh_date"><strong class="fb"> 预计任务完成时间：</strong></label>
		     <input id="finsh_date" name="finsh_date" class="easyui-datebox" style="width:100px;" />
			</td>
          </tr>
          <tr>
          	<td width="100%" colspan="2">
          		<label for="remark"><strong class="fb">备注：</strong></label>
				<textarea id="remark" name="remark" class="easyui-validatebox" style="width:500px;height: 150px;" data-options="required:true"></textarea> 
          	</td>
          </tr>
          <tr>
          	<td width="100%" colspan="2">
          	<label for="is_del"><strong class="fb">状态：</strong></label>
			   <select id="is_del" class="easyui-combobox" name="is_del" style="width:150px;" editable="false" data-options="required:true">
      		   <option value="-1">--请选择--</option>
      		   <option value="0">正常</option>
      		   <option value="1">删除</option>
    	     </select>
    	     <label for="priority"><strong class="fb">优先级：</strong></label>
			   <select id="priority" class="easyui-combobox" name="priority" style="width:150px;" editable="false" data-options="required:true">
      		   <option value="-1">--请选择--</option>
      		   <option value="0">高</option>
      		   <option value="1">中</option>
      		   <option value="2">低</option>
    	     </select>
          	</td>
          </tr>
          <tr>
          <td width="100%" colspan="2">
          	  <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveYwtTask()" style="width:90px">保存</a>
          	  <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">退出</a>
          	</td>
          </tr>
          </table>
       </form>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript"
	src="${ctx}/commons/scripts/jquery.min.js"></script>
<script type="text/javascript"
	src="${ctx}/commons/scripts/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${ctx}/commons/scripts/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/public.js"></script>
<script type="text/javascript">

		$(function(){
	    	 $.post('${ctx}/webservice/YwtTask.do?method=add',function(result){
	    		 $('#addOrEdit').form('clear');
	    		 $("#addOrEdit #add_date").datebox('setValue',result.add_date);
	    		 $("#addOrEdit #assigned_user_id").val(result.assigned_user_id);
	    		 $("#addOrEdit #assigned_user_name").val(result.assigned_user_name);
		    	 $("#addOrEdit #receive_user_id").combogrid({
		    	    panelWidth: 110,
		    	    multiple: true,
		    	    idField: 'id',
		    	    textField: 'real_name',
		    	    data:result.reveUserList,
		    	    columns: [[
		    	     {field:'ck',checkbox:true},
		    	     {field:'real_name',title:'请选择',width:120}
		    	    ]],
		    	    editable:false,
		    	    fitColumns: true
		    	   });
		    	   
		    	 $("#addOrEdit #task_type").combogrid({
			    	    panelWidth: 110,
			    	    idField: 'type_id',
			    	    textField: 'type_name',
			    	    data:result.taskTypeList,
			    	    columns: [[
			    	     {field:'type_name',title:'请选择',width:120}
			    	    ]],
			    	    editable:false,
			    	    fitColumns: true
			    	   });
		    	 $('#addOrEdit #task_state').combobox({
			    	 valueField:"id", 
			    	 textField:"text", 
					 editable:false,
						 data:[{id:"-1",text:"-请选择-"},
			                   {id:0,text:"未开始"},
			                   {id:1,text:"进行中"},
							   {id:2,text:"已完成"}]});
	         });
	  
	});
		   function saveYwtTask(){
		        $('#addOrEdit').form('submit',{
		            url: '${ctx}/webservice/YwtTask.do?method=save',
		            onSubmit: function(){
		        	   /**var content=$('#addOrEdit #content').val();
		        	   if (content.length>250) {
						alert("任务内容不能多于250字");
						return false;
					   }
						var add_user_id_name =  $('#addOrEdit #add_user_id_name').combobox("getValues");
						if(add_user_id_name == ''||add_user_id_name == null){
							alert("至少选择一个用户！");
							return false;
						}
						return $(this).form('validate'); **/
				    },
		            success: function(result){
		                if (null!=result.res&&result.res!=''){
		                    $.messager.show({
		                        title: '操作提示！',
		                        msg: result.msg
		                    });
		                } else {
		                	$.messager.show({
		                        title: '操作提示！',
		                        msg: result.msg
		                    });
		                }
		            }
		        });
		    }
	    //修改任务
	 	function editTask(){
		 	//编辑时只能选择一行记录
	 	   var flag = false;
	       var row = $('#table').datagrid('getSelections');
	       if(row.length != 1){
       		alert("请您选择一行数据!"); 
       		return false;  
	        }
	 		$.post('${ctx}/webservice/YwtTask.do?method=edit&id='+row[0].id,function(result){
	 			//$('#addOrEdit').form('clear');
		        $("#addOrEdit #receive_user_id").combogrid({
		    	    panelWidth: 110,
		    	    multiple: true,
		    	    idField: 'id',
		    	    textField: 'real_name',
		    	    data:result.userlist,
		    	    columns: [[
		    	     {field:'ck',checkbox:true},
		    	     {field:'real_name',title:'请选择',width:120}
		    	    ]],
		    	    editable:false,
		    	    fitColumns: true
		    	   });
		    	   
		    	 $('#addOrEdit #task_type').combobox({
			    	 valueField:"type_id", 
			    	 textField:"type_name", 
					 editable:false,
						 data:result.taskTypeList
				  });
				 
		    	 $('#addOrEdit #task_state').combobox({
			    	 valueField:"id", 
			    	 textField:"text", 
					 editable:false,
						 data:[{id:"-1",text:"-请选择-"},
			                   {id:0,text:"未开始"},
			                   {id:1,text:"进行中"},
							   {id:2,text:"已完成"}]});

		    	 $("#addOrEdit #id").val(result.entity.id);
		    	 $("#addOrEdit #par_task_id").val(result.entity.par_task_id);
			     $("#addOrEdit #task_name").val(result.entity.task_name); 
			     
			     $("#addOrEdit #task_type").combobox('setValue',result.entity.task_type);
			     $("#addOrEdit #task_state").combobox('setValue',result.entity.task_state);    
			         
			     $("#addOrEdit #content").val(result.entity.content);     
			     $("#addOrEdit #assigned_user_id").val(result.assigned_user_id);
	    		 $("#addOrEdit #assigned_user_name").val(result.assigned_user_name);
			     $("#addOrEdit #add_date").datebox('setValue',result.add_date);
			     
			     if(result.receive_user_id_name_arry){
			    	 $('#addOrEdit #receive_user_id').combogrid('setValues',result.receive_user_id_name_arry);
			       }
			     $("#addOrEdit #finsh_date").datebox('setValue',result.finsh_date);
			     $("#addOrEdit #remark").val(result.entity.remark);  
			     $("#addOrEdit #is_del").combobox('setValue',result.entity.is_del);    
			     $("#addOrEdit #priority").combobox('setValue',result.entity.priority);
		        $('#dlg').dialog('open').dialog('setTitle','修改任务'); 
	         });
	     url = '${ctx}/webservice/YwtTask.do?method=save';
		}
	</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
