<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>任务查询</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/commons/scripts/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/commons/scripts/themes/icon.css" />
<style type="text/css">
.rtable1 td {
	padding: 2px 2px;
}
</style>
<style type="text/css">
<!--easyui-->
.webinput {
	background:#f5f4f4;
	padding-left: 5px;
	height: 19px;
	line-height: 19px;
	border: #ccc solid 1px;
}
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
<div class="oartop">
<table width="400" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="3%"><img
			src="${ctx}/styles/admin-index/images/k_tup.jpg"
			style="vertical-align: middle;" /></td>
		<td>&nbsp;当前位置：客户管理 > 业务通 > 任务交办 > <span> 任务查询</span></td>
	</tr>
</table>
</div>
<div style="padding: 5px">
<form id="fm" method="post">
<table width="100%" border="0" cellspacing="5" cellpadding="0"
	class="rtable1">
	<tr>
   <td align="left"><label for="dept_id"><strong class="fb">&nbsp&nbsp分 公 司：</strong></label>
	    <input id="_fgs_dept_id" name="_fgs_dept_id" class="easyui-combobox" style="width:150px"/>		
	    <input id="_mod_id" name="_mod_id" type="hidden" value="108034" style="width: 100px" /> 	   		
	</td>
	<td align="left"><label for="_reve_real_name_like"><strong class="fb">&nbsp&nbsp接收人：</strong></label>
	    <input id="_reve_real_name_like" name="_reve_real_name_like"
			style="width: 100px" maxlength="30"/>   		
	</td>
	<td align="left"><label for="_task_name_like"><strong class="fb">任务名称： </strong></label>
		<input id="_task_name_like" name="_task_name_like"
			style="width: 100px" maxlength="30"/>
	</td>
	
	</tr>
	<tr>
	<td align="left"><label for="_is_receive"><strong class="fb">&nbsp&nbsp接收状态：</strong></label>
		<select id="_is_receive" class="easyui-combobox" name="_is_receive" style="width:150px;" editable="false" data-options="required:true">
      		   <option value="-1">--请选择--</option>
      		   <option value="0"> 接收 </option>
      		   <option value="1">拒绝</option>
    	</select>
	</td>
	<td align="left"><label for="_state"><strong class="fb">完成状态：</strong></label>
		<select id="_state" class="easyui-combobox" name="_state" style="width:150px;" editable="false" data-options="required:true">
      		   <option value="-1">--请选择--</option>
      		   <option value="0">完成</option>
      		   <option value="1">未完成</option>
    	</select>
	</td>
	<td align="left"><label for="_audit_state"><strong class="fb">审核状态：</strong></label>
		<select id="_audit_state" class="easyui-combobox" name="_audit_state" style="width:150px;" editable="false" data-options="required:true">
      		   <option value="-1">--请选择--</option>
      		   <option value="0">已审核 </option>
      		   <option value="1">未审核</option>
    	</select>
	</td>
	</tr>
	<tr>
	<td align="left"><label for="_begin_add_date"><strong class="fb">&nbsp 接收日期： </strong></label>
		<input id="_begin_add_date" class="easyui-datebox" style="width:100px;" />
		-
		<input id="_end_add_date" class="easyui-datebox" style="width:100px;" />
	</td>
		<td align="left"><label for="_begin_reve_finsh_date"><strong class="fb"> 完成日期： </strong></label>
			<input id="_begin_reve_finsh_date" class="easyui-datebox" style="width:100px;" />
	        -
			<input id="_end_reve_finsh_date" class="easyui-datebox" style="width:100px;" />
		</td>
		
		<td align="left"><label for="_begin_audit_date"><strong class="fb">审核日期： </strong></label>
			<input id="_begin_audit_date" class="easyui-datebox" style="width:100px;" />
		     -
			<input id="_end_audit_date" class="easyui-datebox" style="width:100px;" />
		</td>
	</tr>
	<tr>
	   <td align="left" colspan="3"><label for="_par_task_name_like"><strong class="fb">父任务名称： </strong></label>
		<input id="_par_task_name_like" name="_par_task_name_like"
			style="width: 100px" maxlength="30"/>
	  </td>
	</tr>
</table>
</form>
</div>
<div id="tb" style="height: auto; padding-right: 10px" align="right">
<a href="#" class="easyui-linkbutton" id="search" iconCls="icon-search">查
询</a>&nbsp; <a href="#" class="easyui-linkbutton" id="exprot"
	iconCls="icon-redo">导 出</a></div>

<div style="margin-left: 5px; margin-right: 5px; margin-top: 5px;">
<table id="table" class="easyui-datagrid" title="接收任务(说明：点击任务名称可以查看当前任务信息)"  
        data-options="url:'${ctx}/webservice/YwtTaskReceive.do?method=list&mod_id=108034'"
        rownumbers="true" idField="id" singleSelect="true" pagination="true"  loadMsg="正在拼命加载中，请稍后..."  fitColumns="false" >   
    <thead>
    <tr style="bgcolor:orange;" >
         <th width="80px;" field="fgs_Dept"formatter="get_fgs_Dept"><b>分公司</b></th>
        <th width="80px;"  field="user_name" formatter="get_user_name"><b>接收人</b></th>
        <th width="80px;"  field="par_task_name" formatter="get_par_task_name"><b>父任务名称</b></th>
        <th width="80px;"  field="task_name" formatter="get_task_name"><b>任务名称</b></th>
        <th width="80px;"  field="sub_task_count" formatter=get_sub_task_count><b>子任务数</b></th>
        <th width="80px;"  field="is_receive" formatter="get_is_receive"><b>接收状态</b></th>
        <th width="80px;"  field="add_date" formatter="get_add_date"><b>接收日期</b></th>
        <th width="80px;"  field="priority" formatter="get_priority"><b>优先级</b></th>
        <th width="80px;"  field="memo" formatter="get_memo"><b>任务类型</b></th>
        <th width="80px;"  field="begin_date" formatter="get_begin_date"><b>任务开始日期</b></th>
        <th width="80px;"  field="finsh_date" formatter="get_finsh_date"><b>任务完成期限</b></th>
	    <th width="80px;"  field="state" formatter="get_state"><b>完成状态</b></th>
	    <th width="80px;"  field="reve_finsh_date" formatter="get_reve_finsh_date"><b>完成日期</b></th>
	    <th width="80px;"  field="audit_state"  formatter="get_audit_state"><b>审核状态</b></th>
	    <th width="80px;"  field="audit_date" formatter="get_audit_date"><b>审核日期</b></th>
	    <th width="80px;"  field="audit_info" ><b>审核意见</b></th>
	    <th width="80px;"  field="fj_paths" formatter="get_fj_paths"><b>附件</b></th>
        <th width="80px;"  field="id" formatter="get_id_update"><b>操作</b></th>
    </tr>
   </thead>      
</table>  
</div>
<!--查看-->
<div id="dlg_view" class="easyui-dialog" style="width:700px;height:500px;padding:10px 20px"  closed="true" buttons="#dlg_view-buttons">  
      <form id="view" method="post" novalidate="false" enctype="multipart/form-data"> 
         <table width="100%" border="0" align="right" cellspacing="0" cellpadding="0" class="rtable3"> 
          
          <tr>
	          <td align="right"><label for="task_name"><strong class="fb">任务名称: </strong></label></td><td align="left"><input name="task_name" disabled="disabled" class="easyui-validatebox" id="task_name" style="width:110px"></input></td>
	          <td align="right"><label for="is_receive"><strong class="fb">接收状态：</strong></label></td>
	          <td align="left">
	             <input class="easyui-combobox" id="is_receive" disabled="disabled" name="is_receive" /> 
				 <input type="hidden" name="id" id="id"></input>
				 <input type="hidden" name="mod_id" id="mod_id"></input>
			 </td>
          </tr>
<!--          <tr>-->
<!--	          <td align="right">  <label for="state"><strong class="fb" id="state">完成状态：</strong></label></td><td align="left"> <input class="easyui-combobox" disabled="disabled" id="state" name="state" /></td>-->
<!--	          <td align="right">  <label for="audit_state"><strong class="fb" id="audit_state">审核状态：</strong></label></td><td align="left"> <input class="easyui-combobox" disabled="disabled" id="audit_state" name="audit_state" /> </td>-->
<!--          </tr>-->
          <tr>
	          <td align="right"><label for="assigned_user_name"><strong class="fb">任务发起人：</strong></label></td><td align="left"><input name="assigned_user_name" disabled="disabled" id="assigned_user_name" style="width:110px"></input></td>
	          <td align="right"><label for="receive_user_id"><strong class="fb">当前人：</strong></label></td><td align="left"><input name="receive_user_id" disabled="disabled"  id="receive_user_id" style="width:110px"></input> </td>
          </tr>
          <tr>
	          <td align="right"><label for="finsh_date"><strong class="fb"> 任务完成期限：</strong></label></td><td align="left"><input id="finsh_date"  name="finsh_date" disabled="disabled" class="easyui-datebox" style="width:100px;" /></td>
			  <td align="right"><label for="task_type"><strong class="fb">子任务类型：</strong></label></td><td align="left"><input class="easyui-combobox" id="task_type" name="task_type"></input></td>
	          <td align="right"><label for="audit_name"><strong class="fb" id="audit_name">审核人姓名: </strong></label></td><td align="left"> <input name="audit_name" disabled="disabled"  id="audit_name" style="width:110px"></input> </td>
          </tr>
          <tr>
	          <td align="right"><label for="content"><strong class="fb">任务内容: </strong></label></td><td colspan="3" align="left"><textarea id="content" name="content" disabled="disabled" class="easyui-validatebox" style="width:500px;height: 200px;" data-options="required:true"></textarea></td>
          </tr>
          <tr>
	          <td align="right"><label for=""><strong class="fb">附件：</strong></label></td><td colspan="3" align="left"><div id="fj"></div></td>
          </tr>
           <tr>
	          <td align="right"><label for="remark"><strong class="fb">备注： </strong></label></td><td colspan="3" align="left"><textarea id="remark" name="remark" disabled="disabled" class="easyui-validatebox" style="width:500px;height: 50px;" data-options="required:true"></textarea></td>
          </tr>
          
          <!--<tr>
          	<td>
			<label for="task_name"><strong class="fb">任务名称: </strong></label>
			 <input name="task_name" disabled="disabled" class="easyui-validatebox" id="task_name" style="width:110px"></input> 
			  <label for="is_receive"><strong class="fb">接收状态：</strong></label>
		     <input class="easyui-combobox" id="is_receive" disabled="disabled" name="is_receive" /> 
			 <input type="hidden" name="id" id="id"></input>
			 <input type="hidden" name="mod_id" id="mod_id"></input> 
		     </td>
          	</tr>
          <tr>
          	<td>
          	 <label for="state"><strong class="fb">完成状态：</strong></label>
		     <input class="easyui-combobox" disabled="disabled" id="state" name="state" />
		       <label for="audit_state"><strong class="fb">审核状态：</strong></label>
		     <input class="easyui-combobox" disabled="disabled" id="audit_state" name="audit_state" /> 
		     </td>
		 </tr>
          <tr>
		     <td>
		     <label for="assigned_user_name"><strong class="fb">任务发起人：</strong></label>
		      <input name="assigned_user_name" disabled="disabled" id="assigned_user_name" style="width:110px"></input> 
			<label for="receive_user_id"><strong class="fb">接收任务人：</strong></label>
		      <input name="receive_user_id" disabled="disabled"  id="receive_user_id" style="width:110px"></input> 
			</td>
          </tr>
           <tr>
		     <td>
		      <label for="finsh_date"><strong class="fb"> 预计任务完成日期：</strong></label>
		     <input id="finsh_date"  name="finsh_date" disabled="disabled" class="easyui-datebox" style="width:100px;" />
		     <label for="audit_name"><strong class="fb">审核人姓名: </strong></label>
			 <input name="audit_name" disabled="disabled"  id="audit_name" style="width:110px"></input> 
			</td>
          </tr>
          <tr>
          	<td>
			<label for="content"><strong class="fb">任务内容: </strong></label>
			<textarea id="content" name="content" disabled="disabled" class="easyui-validatebox" style="width:500px;height: 200px;" data-options="required:true"></textarea> 
		     </td>
		 </tr>
		  <tr >
          	<td width="100%" colspan="2">
          	<label for=""><strong class="fb">附件：</strong></label>
          	    <div id="fj"></div>
          	</td>
          </tr>
          -->
          
          </table>
       </form>
		</div>
	 <div id="dlg_view-buttons">
	    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg_view').dialog('close')" style="width:90px">退出</a>
	</div> 
<script type="text/javascript"
	src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script>
<script type="text/javascript"
	src="${ctx}/commons/scripts/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${ctx}/commons/scripts/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/public.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.imgAddShow.js"></script>
<script type="text/javascript">
		$(function(){
			//初始化
			$.post('${ctx}/webservice/YwtTaskReceive.do?method=init&mod_id=108034',function(result){
				//$("#_begin_add_date").datebox('setValue',result._begin_add_date);
				//$("#_end_add_date").datebox('setValue',result._end_add_date);
				//$("#_is_receive").combobox('setValue',result._is_receive);
				//$("#_audit_state").combobox('setValue',result._audit_state);
				//$("#_state").combobox('setValue',result._state);
				$("#_mod_id").val(result._mod_id);
				
				$("#nav").text(result.local_str);
				// 得到datagrid的pager对象，初始化分页栏
				var pager = $('#table').datagrid('getPager');  
				pager.pagination({
			   		showPageList:false,    
			    	pageSize:10,  
			    	//displayMsg:'当前显示从{from}到{to}，共' + 4 + '条记录',
				    onBeforeRefresh:function(pageNumber, pageSize){
				     	$(this).pagination('loading');
				     	$(this).pagination('loaded');
				    }
				});
				
				$('#_fgs_dept_id').combobox({                 
					url:'${ctx}/manager/admin/CsAjax.do?method=getAjaxKonkaDeptList',  
				 	method:'post',              
				 	editable:false, //不可编辑状态                
				 	cache: false,               
				 	valueField:'DEPT_ID',                   
					textField:'DEPT_NAME'                   
				}); 
				
			//初次进来需要初始化查询数据
			$('#table').datagrid('load',{
						_task_name_like :$("#_task_name_like").val(),
						_par_task_name_like:$("#_par_task_name_like").val(),
						_begin_add_date: $("#_begin_add_date").datebox('getValue'),
						_end_add_date: $("#_end_add_date").datebox('getValue'),
						_is_receive: $("#_is_receive").combobox('getValue'),
						_audit_state:$("#_audit_state").combobox('getValue'),
						_state: $("#_state").combobox('getValue'),
						_fgs_dept_id:$("#_fgs_dept_id").combobox('getValue'),
						_reve_real_name_like:$("#_reve_real_name_like").val(),
						_begin_reve_finsh_date: $("#_begin_reve_finsh_date").datebox('getValue'),
						_end_reve_finsh_date: $("#_end_reve_finsh_date").datebox('getValue'),
						_begin_audit_date: $("#_begin_audit_date").datebox('getValue'),
						_end_audit_date :$("#_end_audit_date").datebox('getValue')
			   		});
			//查询按钮绑定事件 
			$("#search").bind('click',function(){
				//$('#table').datagrid('reload');
				//查询按钮绑定事件 
					$('#table').datagrid('load',{
						_task_name_like :$("#_task_name_like").val(),
						_par_task_name_like:$("#_par_task_name_like").val(),
						_begin_add_date: $("#_begin_add_date").datebox('getValue'),
						_end_add_date: $("#_end_add_date").datebox('getValue'),
						_is_receive: $("#_is_receive").combobox('getValue'),
						_audit_state:$("#_audit_state").combobox('getValue'),
						_state: $("#_state").combobox('getValue'),
						_fgs_dept_id:$("#_fgs_dept_id").combobox('getValue'),
						_reve_real_name_like:$("#_reve_real_name_like").val(),
						_begin_reve_finsh_date: $("#_begin_reve_finsh_date").datebox('getValue'),
						_end_reve_finsh_date: $("#_end_reve_finsh_date").datebox('getValue'),
						_begin_audit_date: $("#_begin_audit_date").datebox('getValue'),
						_end_audit_date :$("#_end_audit_date").datebox('getValue')
			   		});
			});

			$("#exprot").bind('click',function(){
				$.messager.confirm('温馨提示', '是否导出数据？', function(r){
					if (r){
						var str ='&excel_all=1'
							+'&_task_name_like='+$("#_task_name_like").val()
							+'&_par_task_name_like='+$("#_par_task_name_like").val()
							+'&_begin_add_date='+$("#_begin_add_date").datebox('getValue')
							+'&_end_add_date='+$("#_end_add_date").datebox('getValue')
							+'&_is_receive='+$("#_is_receive").combobox('getValue')
							+'&_audit_state='+$("#_audit_state").combobox('getValue')
							+'&_state='+$("#_state").combobox('getValue')
							+'&_fgs_dept_id='+$("#_fgs_dept_id").combobox('getValue')
							+'&_reve_real_name_like='+$("#_reve_real_name_like").val()
							+'&_begin_reve_finsh_date='+$("#_begin_reve_finsh_date").datebox('getValue')
							+'&_end_reve_finsh_date='+$("#_end_reve_finsh_date").datebox('getValue')
							+'&_begin_audit_date='+$("#_begin_audit_date").datebox('getValue')
							+'&_end_audit_date='+$("#_end_audit_date").datebox('getValue')
							+'&mod_id='+$("#_mod_id").val();
						$("#fm").attr("action", "${ctx}/webservice/YwtTaskReceive.do?method=list"+str);
			    		$("#fm").submit();
					}  
				});
			});
		});
		});
		
		//接收人
		function get_user_name(value,rec){
            return rec.map.user_name;
    	}
    	
		//任务查看
		function get_task_name(value,rec){
			 return '<a href="javascript:void(0)" style="color:red;" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="viewYwtTaskReceive('+rec.id+')">'+value+'</a>';
		}
		//子任务数
		function get_sub_task_count(value,rec){
			return rec.map.sub_task_count;
		}
		//父任务名
		function get_par_task_name(value,rec){
			return rec.map.par_task_name;
		}
    	//任务开始日期
		function get_begin_date(value,rec){
            return rec.map.begin_date;
		}
		
		//接收日期
		function get_add_date(value,rec){
            return rec.map.add_date;
		}

		//类型
		function get_memo(value,rec){
            return rec.map.memo;
		}
		//优先级
		function get_priority(value,rec){
			if(rec.map.priority==0){
			     return "高";
			}else if(rec.map.priority==1){
				return "中";
            }else if(rec.map.priority==2){
                return "低";    
			}
		}
		//接收状态
		function get_is_receive(value){
			if(value==0){
			    return "<lable style='color: green;'><b>接收</b></lable>";
			}else if(value==1){
				return "拒绝";
            }else if(value==2){
				return "<lable style='color: red;'><b>未接受</b></lable>";
            }
		}
		
		//完成状态
		function get_state(value,rec){
			if(value==0){
			     return "<lable style='color: green;'><b>完成</b></lable>";
			}else if(value==1&&rec.is_receive==1){
				return "未完成";
            }
		}
		//完成日期
		function get_reve_finsh_date(value,rec){
            return rec.map.reve_finsh_date;
		}
		//审核状态
		function get_audit_state(value){
			if(value==0){
			     return "已审核 ";
			}else if(value==1){
				return "未审核";
            }
		}
		//审核日期
		function get_audit_date(value,rec){
            return rec.map.audit_date;
		}
		//部门
		function get_fgs_Dept(value,rec){
            return rec.map.fgs_Dept;
		}
		//任务完成期限
		function get_finsh_date(value,rec){
            return rec.map.finsh_date;
		}
		
		//附件显示
		function get_fj_paths(value,rec){
			if (rec.map.fj_paths) {
				var fjs=rec.map.fj_paths.split(",");
                var fjsStr="";
				$.each(fjs, function(i, n){
					fjsStr += '<a href="'+"${ctx}/"+n+'" style="color:red;" target="_blank" iconCls="icon-add" plain="true" >'+"附件"+(i+1)+'</a>';
					});
			}
            return fjsStr;
		}
		//修改
		function get_id_update(value,rec){
			var cz='';
			 cz+='<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="showSubTask('+rec.task_id+')">子任务</a>';
			 cz+='&nbsp&nbsp<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="viewYwtTaskReceive('+value+')">查看</a>';
			 return cz;
		}
		function back(){
		     $('#dlg').dialog('close');
		    // $('#table').datagrid('reload');
		}
		//查看任务
		function viewYwtTaskReceive(id){
		 	//编辑时只能选择一行记录
		 		$.post('${ctx}/webservice/YwtTaskReceive.do?method=edit&id='+id,function(result){
		 			//$('#addOrEdit').form('clear');
			       $('#view #task_type').combogrid({
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
			       	$('#view #task_type').combobox({
						  data:[]
					 });
			    	 $('#view #task_type').combobox({
				    	 valueField:"type_id", 
				    	 textField:"type_name", 
						 editable:false,
						  data:result.taskTypeList
					  });
			    	 $('#view #is_receive').combobox({
				    	 valueField:"id", 
				    	 textField:"text", 
						 editable:false,
							 data:[{id:"-1",text:"-请选择-"},
				                   {id:0,text:"接收"},
				                   {id:1,text:"拒绝"},{id:2,text:"未接受"}]});
					   
			    	 $('#view #state').combobox({
				    	 valueField:"id", 
				    	 textField:"text", 
						 editable:false,
							 data:[{id:"-1",text:"-请选择-"},
				                   {id:0,text:"完成"},
				                   {id:1,text:"未完成"}]});

			    	/* $('#view #task_type').combobox({
						  data:[]
					 });
			    	 $('#view #task_type').combobox({
				    	 valueField:"type_id", 
				    	 textField:"type_name", 
						 editable:false,
						  data:result.taskTypeList
					  });
					  */
			    	 $('#view #audit_state').combobox({
				    	 valueField:"id", 
				    	 textField:"text", 
						 editable:false,
							 data:[{id:"-1",text:"-请选择-"},
				                   {id:0,text:"已审核"},
				                   {id:1,text:"未审核"}]});
			    	 $("#view #id").val(result.entity.id);
				     $("#view #task_name").val(result.entity.task_name); 
				     $('#view #is_receive').combobox('setValue',result.entity.is_receive);
		//		     $('#view #state').combobox('setValue',result.entity.state);
		//			 $('#view #audit_state').combobox('setValue',result.entity.audit_state);
					 $("#view #assigned_user_name").val(result.ywttask.map.reveUser); 
					 $("#view #receive_user_id").val(result.entity.map.reve_name);
					 $('#view #audit_state').attr("disabled","disabled");
				     $("#view #finsh_date").datebox('setValue',result.ywttask.map.finsh_date);
				     $("#view #audit_name").val(result.entity.audit_name);
				     $("#view #content").val(result.ywttask.content);
				     $("#view #task_type").combobox({ disabled: true });
				     $("#view #task_type").combobox('setValue',result.ywttask.task_type);
					 $("#view #remark").val(result.ywttask.remark);
				     //隐藏
				     $("#view #audit_name").attr("hidden",true);
			     	 $("#audit_name").attr("hidden",true);  
			     	    
		//		     $("#view #audit_state").attr("hidden",true);
		//	     	 $("#audit_state").attr("hidden",true);     
		//		     $("#view #state").attr("hidden",true);
		//	     	 $("#state").attr("hidden",true); 
				     $("#view #fj").imgshow({
							ctx:"${ctx}",
							isAdd:false,
							//delUrl:"${ctx}/manager/admin/KonkaSpActivityManager.do?method=deleteFile1",
							data:result.fj_paths
					    });
				     $('#dlg_view').dialog('open').dialog('setTitle','查看任务'); 
		         });
			      
		}
		function showSubTask(task_id){
			 $("#_task_name_like").val();
			 $("#_par_task_name_like").val();
			 $("#_begin_add_date").datebox('setValue',null);
			 $("#_end_add_date").datebox('setValue',null);
			 $("#_is_receive").combobox('setValue',-1);
			 $("#_audit_state").combobox('setValue',-1);
			 $("#_state").combobox('setValue',-1);
			 $("#_fgs_dept_id").combobox('setValue',null);
			 $("#_reve_real_name_like").val(null);
			 $("#_begin_reve_finsh_date").datebox('setValue',null);
			 $("#_end_reve_finsh_date").datebox('setValue',null);
			 $("#_begin_audit_date").datebox('setValue',null);
			 $("#_end_audit_date").datebox('setValue',null);
			
			$('#table').datagrid('reload',{
				_task_id :task_id
	   		});
		}
		
   </script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
