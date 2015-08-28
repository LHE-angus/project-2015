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
		<td>&nbsp;当前位置：客户管理 > 业务通 > 任务交办 > <span>新建任务</span></td>
	</tr>
</table>
</div>
<div style="padding: 5px">
<form id="fm" method="post">
<table width="100%" border="0" cellspacing="5" cellpadding="0"
	class="rtable1">
	<tr>
	<td align="left"><label for="dept_id"><strong class="fb">分 &nbsp&nbsp公&nbsp&nbsp 司：</strong></label>
	    <input id="_fgs_dept_id" name="_fgs_dept_id" class="easyui-combobox" style="width:150px"/>			   		
	</td>
	<td align="left"><label for="_par_task_name_like"><strong class="fb">&nbsp&nbsp父任务名称： </strong></label>
		<input id="_par_task_name_like" name="_par_task_name_like"
			style="width: 100px" />
	</td>
	<td align="left"><label for="_real_name_like"><strong class="fb">任务名称： </strong></label>
		<input id="_task_name_like" name="_task_name_like"
			style="width: 100px" />
	</td>
	<td align="left"><label for="_real_name_like"><strong class="fb">&nbsp&nbsp创建人： </strong></label>
		<input id="_real_name_like" name="_real_name_like"
			style="width: 100px" />
	</td>
		<td align="left"><label for="_is_lock"><strong class="fb">是否启用：</strong></label>
		<select id="_is_del" class="easyui-combobox" name="_is_del" style="width:150px;" editable="false" data-options="required:true">
      		   <option value="-1">--请选择--</option>
      		   <option value="0">启用</option>
      		   <option value="1">未启用</option>
    	</select>
	</td>
	</tr>
    <tr>
	<td align="left"><label for="_begin_begin_date"><strong class="fb"> 任务开始日期： </strong></label>
		<input id="_begin_begin_date" class="easyui-datebox" style="width:100px;" />
	</td>	
	<td align="left"><label for="_end_finsh_date"><strong class="fb"> 任务完成期限： </strong></label>
		<input id="_end_finsh_date" class="easyui-datebox" style="width:100px;" />
	</td>
	<td align="left"><label for="_task_state"><strong class="fb">任务状态：</strong></label>
	     <select id="_task_state" class="easyui-combobox" name="_task_state" style="width:150px;" editable="false" data-options="required:true">
      		   <option value="-1">--请选择--</option>
      		   <option value="0">未开始</option>
      		   <option value="1">进行中</option>
      		   <option value="2">已完成</option>
    	</select>
	</td>
	<td align="left"><label for=""><strong class="fb">创建日期： </strong></label>
		<select id="_year" class="easyui-combobox" name="_year" style="" editable="false" data-options="required:true">
 		   <option value="-1">--请选择--</option>
 		   <option value="2014">2014</option>
 		   <option value="2015">2015</option>
 		   <option value="2016">2016</option>
 		   <option value="2017">2017</option>
 		   <option value="2018">2018</option>
 		   <option value="2018">2019</option>
 		   <option value="2018">2020</option>
    	</select>
    	<select id="_month" class="easyui-combobox" name="_month" style="" editable="false" data-options="required:true">
           <option value="-1">--请选择--</option>
 		   <option value="01">1</option>
 		   <option value="02">2</option>
 		   <option value="03">3</option>
 		   <option value="04">4</option>
 		   <option value="05">5</option>
 		   <option value="06">6</option>
 		   <option value="07">7</option>
 		   <option value="08">8</option>
 		   <option value="09">9</option>
 		   <option value="10">10</option>
 		   <option value="11">11</option>
 		   <option value="12">12</option>
    	</select>
	
	<!--<label for="_assigned_user_name_like"><strong class="fb">任务交办人： </strong></label>
		<input id="_assigned_user_name_like" name="_assigned_user_name_like"
			style="width: 100px" />
	-->
	</td>
	<td>
	 <label for="_priority"><strong class="fb">&nbsp&nbsp优先级：</strong></label>
	      <select id="_priority" class="easyui-combobox" name="_priority" style="width:150px;" editable="false" data-options="required:true">
    		   <option value="-1">--请选择--</option>
    		   <option value="0">高</option>
    		   <option value="1">中</option>
    		   <option value="2">低</option>
  	      </select>
    </td>

	</tr>
	<tr>
	 <td align="left" colspan="10">
	  <label for="_task_type"><strong class="fb">任务类型：</strong></label>
	  <input class="easyui-combobox" id="_task_type" name="_task_type"></input></td>
	</tr>
</table>
</form>
</div>
<div id="tb" style="height: auto; padding-right: 10px" align="right" >
<a href="#" class="easyui-linkbutton" id="search" iconCls="icon-search">查
询</a>&nbsp; <a href="#" class="easyui-linkbutton" id="exprot"
	iconCls="icon-redo">导 出</a></div>

<div style="margin-left: 5px; margin-right: 5px; margin-top: 5px;" class="listCenter">
<table id="table" class="easyui-datagrid listcenter" title="任务交办(说明：点击子任务数查找当前下级任务)"  
        data-options="url:'${ctx}/webservice/YwtTask.do?method=list'"
        rownumbers="true" idField="id" toolbar="#toolbar" singleSelect="true" pagination="true"  loadMsg="正在拼命加载中，请稍后..."  fitColumns="false" >   
    <thead>
    <tr style="bgcolor:orange;">
    <th width="80px;" field="fgs_Dept"formatter="get_fgs_Dept"><b>分公司</b></th>
        <th width="80px;"  field="real_name" ><b>创建人</b></th>
        <th width="80px;"  field="add_date" formatter="get_add_date"><b>创建日期</b></th>
	    <th width="80px;"  field="par_task_name" ><b>父任务名称</b></th>
	    <th width="80px;"  field="task_name" title="查看当前任务" formatter="get_task_name"><b>任务名称</b></th>
	    <th width="80px;"  field="subcount" title="查看子任务" formatter="get_subcount"><b>当前子任务数</b></th>
	    <th width="80px;"  field="priority" formatter="get_priority"><b>优先级</b></th>
	    <th width="80px;"  field="task_type"  formatter="get_task_type"><b>任务类型</b></th>
	    <th width="80px;"  field="content" formatter="get_content"><b>任务内容</b></th>
	    <th width="80px;"  field="remark" formatter="get_remark"><b>备注</b></th>
	    <th width="80px;"  field="task_state" formatter="get_task_state"><b>任务状态</b></th>
	    <th width="80px;"  field="begin_date" formatter="get_begin_date"><b>任务开始日期</b></th>
	     <th width="80px;"  field="finsh_date" formatter="get_finsh_date"><b>任务完成期限</b></th>
<!--	    <th width="80px;"  field="assigned_user_id" formatter="get_assigned_user_name">任务交办人</th>-->
	    <th width="80px;"  field="fj_paths" formatter="get_fj_paths"><b>附件</b></th>
	    <th width="80px;"  field="is_del" formatter="get_is_del"><b>是否启用</b></th>
	    <th width="150px;"  field="id" formatter="get_id_update"><b>操作</b></th>
<!--	    <th width="80px;"  field="del_user_id" formatter="get_del_user_name">删除人</th>-->
<!--	    <th width="80px;"  field="del_date" formatter="get_del_date">删除日期</th>-->
    </tr>
   </thead>      
</table>  
</div>
<div id="toolbar" style="margin-left:5px;margin-top:5px;border-top-style: solid;border-top-width: 1px; border-top-color:#C9CFCD "> 
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newTask()">新增</a>
<!--    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editTask()">修改</a>-->
<!--    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delTask()">删除</a>-->
<!--    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="viewTask()">查看</a>-->
</div>    

<!-- 新增和修改页面dialog -->
	<div id="dlg" class="easyui-dialog" style="width:700px;height:550px;"  closed="true" buttons="#dlg-buttons">  
      <form id="addOrEdit" method="post" novalidate="false" enctype="multipart/form-data"> 
          <table width="100%" border="0" align="right" cellspacing="0" cellpadding="0" class="rtable3"> 
          <tr>
             <td align="right"><label for="task_name"><strong class="fb">子任务名称: </strong></label></td>
             <td align="left"><input name="task_name" id="task_name" style="width:110px"></input> </td>
             <td align="right"><label for="par_task_id"><strong class="fb">父任务名称: </strong></label></td>
             <td align="left">
             	 <input type="text" id="par_task_name" name="par_task_name" disabled="disabled"></input>
             	 <div id="hidden"><input class="easyui-combobox" id="par_task_id" name="par_task_id" ></input></div>
                 <input type="hidden" name="id" id="id"></input> 
				 <input type="hidden" name="mod_id" id="mod_id"></input>
             </td>
          </tr>
          <tr>
             <td align="right"><label for="task_type"><strong class="fb">子任务类型：</strong></label></td><td align="left"><input class="easyui-combobox" id="task_type" name="task_type"></input></td>
             <td align="right"><label for="task_state"><strong class="fb">子任务状态：</strong></label></td><td align="left"><input class="easyui-combobox" id="task_state" disabled="disabled" name="task_state" /></td>
          </tr>
          <tr>
             <td align="right"><label for="content"><strong class="fb">子任务内容: </strong></label></td>
             <td colspan="3" align="left">
                   <textarea id="content" name="content" class="easyui-validatebox" style="width:500px;height: 100px;" data-options="required:true"></textarea> 
             <div id="content_note"  style="margin-left:5px;color:#0066FF;font-size:12px;display:none" ><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div>
             </td>
          </tr>
          <tr>
             <td align="right">
	             <input name="assigned_user_id" type="hidden" id="assigned_user_id"></input>
			     <label for="assigned_user_name"><strong class="fb">子任务发起人：</strong></label>
			     <input name="assigned_user_name" type="hidden" id="assigned_user_name" style="width:110px"></input>
             </td>
             <td align="left"><input name="_assigned_user_name" disabled="disabled" id="_assigned_user_name" style="width:110px"></input></td>
             
             <td align="right"><label for="begin_date" ><strong class="fb"> 任务开始日期：</strong></label></td><td align="left"><input id="begin_date" name="begin_date"  class="easyui-datebox" style="width:100px;" /></td>
          </tr>
          <tr>
             <td align="right"> <label for="receive_user_id"><strong class="fb">指派给：</strong></label></td>
             <td align="left">
                  <div id="receive_user_id1" style="display: inline;">
		            <input name="receive_user_id" class="easyui-validatebox" id="receive_user_id" style="width:110px"></input>
		          </div>
		      </td>
             <td align="right"><label for="finsh_date"><strong class="fb"> 任务完成期限：</strong></label></td><td align="left"><input id="finsh_date" name="finsh_date" class="easyui-datebox" style="width:100px;" /></td>
          </tr>
          <tr>
             <td align="right"><label for="remark"><strong class="fb">备注：</strong></label></td>
             <td align="left" colspan="3">
                 <textarea id="remark" name="remark" class="easyui-validatebox" style="width:500px;height: 100px;" data-options="required:true"></textarea>
				 <div id="remark_note"  style="margin-left:5px;color:#0066FF;font-size:12px;display:none" ><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div>
             </td>
          </tr>
          <tr>
             <td align="right"><label for="priority"><strong class="fb">优先级：</strong></label></td>
             <td align="left">
                  <select id="priority" class="easyui-combobox" name="priority" style="width:150px;" editable="false" data-options="required:true">
		      		   <option value="-1">--请选择--</option>
		      		   <option value="0">高</option>
		      		   <option value="1">中</option>
		      		   <option value="2">低</option>
    	          </select>
             </td>
             <td align="right"><label for="is_del"><strong class="fb">状态：</strong></label></td>
             <td align="left">
                 <select id="is_del" class="easyui-combobox" name="is_del" style="width:150px;" editable="false" data-options="required:true">
	      		   <option value="-1">--请选择--</option>
	      		   <option value="0">启用</option>
	      		   <option value="1">未启用</option>
    	         </select>
             </td>
          </tr>
          <tr>
             <td align="right"><label for=""><strong class="fb">附件：</strong></label></td><td colspan="3" align="left">  <div id="fj"></div></td>
          </tr>
          
          <!--
           <tr>
          	<td>
			<label for="task_name"><strong class="fb">子任务名称: </strong></label>
			 <input name="task_name" id="task_name" style="width:110px"></input> 
			 <label for="_par_task_name"><strong class="fb">父任务名称: </strong></label>
			 <input name="_par_task_name" id="_par_task_name" disabled="disabled" style="width:110px"></input> 
			  <input type="hidden" name="id" id="id"></input> 
			  <input type="hidden" name="par_task_id" id="par_task_id"></input>
			  <input type="hidden" name="par_task_name" id="par_task_name"></input>    
			  <input type="hidden" name="mod_id" id="mod_id"></input> 
		     </td>
          	</tr>
          	 <tr>
          	<td>
			  <label for="task_type"><strong class="fb">子任务类型：</strong></label>
		      <input class="easyui-combobox" id="task_type" name="task_type"></input> 
		      <label for="task_state"><strong class="fb">子任务状态：</strong></label>
		      <input class="easyui-combobox" id="task_state" disabled="disabled" name="task_state" />
		     </td>
          	</tr>
          <tr>
          	<td>
			<label for="content"><strong class="fb">子任务内容: </strong></label>
			<textarea id="content" name="content" class="easyui-validatebox" style="width:500px;height: 150px;" data-options="required:true"></textarea> 
		    <div id="content_note"  style="margin-left:50px;color:#0066FF;font-size:12px;display:none" ><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div>
		     </td>
		 </tr>
          <tr>
		     <td>
		     <input name="assigned_user_id" type="hidden" id="assigned_user_id"></input>
		     <label for="assigned_user_name"><strong class="fb">子任务发起人：</strong></label>
		      <input name="_assigned_user_name" disabled="disabled" id="_assigned_user_name" style="width:110px"></input>
		      <input name="assigned_user_name" type="hidden" id="assigned_user_name" style="width:110px"></input>  
		      <label for="begin_date" ><strong class="fb"> 任务开始日期：</strong></label>
		     <input id="begin_date" name="begin_date"  class="easyui-datebox" style="width:100px;" />
			</td>
          </tr>
           <tr>
		     <td>
		     <div id="receive_user_id1" style="display: inline;">
		      <label for="receive_user_id"><strong class="fb">指派给：</strong></label>
		      <input name="receive_user_id" class="easyui-validatebox" id="receive_user_id" style="width:110px"></input>
		      </div> 
		      <label for="finsh_date"><strong class="fb"> 任务完成期限：</strong></label>
		     <input id="finsh_date" name="finsh_date" class="easyui-datebox" style="width:100px;" />
			</td>
          </tr>
          <tr>
          	<td width="100%" colspan="2">
          		<label for="remark"><strong class="fb">备注：</strong></label>
				<textarea id="remark" name="remark" class="easyui-validatebox" style="width:500px;height: 150px;" data-options="required:true"></textarea>
				<div id="remark_note"  style="margin-left:50px;color:#0066FF;font-size:12px;display:none" ><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div> 
          	</td>
          </tr>
          <tr>
          	<td width="100%" colspan="2">
          	
    	     <label for="priority"><strong class="fb">优先级：</strong></label>
			   <select id="priority" class="easyui-combobox" name="priority" style="width:150px;" editable="false" data-options="required:true">
      		   <option value="-1">--请选择--</option>
      		   <option value="0">高</option>
      		   <option value="1">中</option>
      		   <option value="2">低</option>
    	     </select>
    	     <label for="is_del"><strong class="fb">状态：</strong></label>
			   <select id="is_del" class="easyui-combobox" name="is_del" style="width:150px;" editable="false" data-options="required:true">
      		   <option value="-1">--请选择--</option>
      		   <option value="0">启用</option>
      		   <option value="1">未启用</option>
    	     </select>
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
	 <div id="dlg-buttons">
	    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveYwtTask()" style="width:90px">保存</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">退出</a>
	</div> 
<!--查看-->
<div id="dlg_view" class="easyui-dialog" style="width:700px;height:550px;padding:10px 20px"  closed="true" buttons="#dlg_view-buttons">  
      <form id="view" method="post" novalidate="false" enctype="multipart/form-data"> 
           <table width="100%" border="0" align="right" cellspacing="0" cellpadding="0" class="rtable3">
           <tr>
             <td align="right"><label for="task_name"><strong class="fb">子任务名称: </strong></label></td><td align="left"><input name="task_name" id="task_name" style="width:110px"></input> </td>
             <td align="right"><label for="_par_task_name"><strong class="fb">父任务名称: </strong></label></td><td align="left"><input name="_par_task_name" id="_par_task_name" disabled="disabled" style="width:110px"></input>
                 <input type="hidden" name="id" id="id"></input> 
				 <input type="hidden" name="par_task_id" id="par_task_id"></input>
				 <input type="hidden" name="par_task_name" id="par_task_name"></input>    
				 <input type="hidden" name="mod_id" id="mod_id"></input>
             </td>
          </tr>
          <tr>
             <td align="right"><label for="task_type"><strong class="fb">子任务类型：</strong></label></td><td align="left"><input class="easyui-combobox" id="task_type" name="task_type"></input></td>
             <td align="right"><label for="task_state"><strong class="fb">子任务状态：</strong></label></td><td align="left"><input class="easyui-combobox" id="task_state" disabled="disabled" name="task_state" /></td>
          </tr>
          <tr>
             <td align="right"><label for="content"><strong class="fb">子任务内容: </strong></label></td>
             <td colspan="3" align="left">
                   <textarea id="content" name="content" class="easyui-validatebox" style="width:500px;height: 150px;" data-options="required:true"></textarea> 
             <div id="content_note"  style="margin-left:5px;color:#0066FF;font-size:12px;display:none" ><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div>
             </td>
          </tr>
          <tr>
             <td align="right">
	             <input name="assigned_user_id" type="hidden" id="assigned_user_id"></input>
			     <label for="assigned_user_name"><strong class="fb">子任务发起人：</strong></label>
			     <input name="assigned_user_name" type="hidden" id="assigned_user_name" style="width:110px"></input>
             </td>
             <td align="left"><input name="_assigned_user_name" disabled="disabled" id="_assigned_user_name" style="width:110px"></input></td>
             
             <td align="right"><label for="begin_date" ><strong class="fb"> 任务开始日期：</strong></label></td><td align="left"><input id="begin_date" name="begin_date"  class="easyui-datebox" style="width:100px;" /></td>
          </tr>
          <tr>
             <td align="right"> <label for="receive_user_id"><strong class="fb">指派给：</strong></label></td>
             <td align="left">
                  <div id="receive_user_id1" style="display: inline;">
		            <input name="receive_user_id" class="easyui-validatebox" id="receive_user_id" style="width:110px"></input>
		          </div>
		      </td>
             <td align="right"><label for="finsh_date"><strong class="fb"> 任务完成期限：</strong></label></td><td align="left"><input id="finsh_date" name="finsh_date" class="easyui-datebox" style="width:100px;" /></td>
          </tr>
          <tr>
             <td align="right"><label for="remark"><strong class="fb">备注：</strong></label></td>
             <td align="left" colspan="3">
                 <textarea id="remark" name="remark" class="easyui-validatebox" style="width:500px;height: 150px;" data-options="required:true"></textarea>
				 <div id="remark_note"  style="margin-left:5px;color:#0066FF;font-size:12px;display:none" ><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div>
             </td>
          </tr>
          <tr>
             <td align="right"><label for="priority"><strong class="fb">优先级：</strong></label></td>
             <td align="left">
                  <select id="priority" class="easyui-combobox" name="priority" style="width:150px;" editable="false" data-options="required:true">
		      		   <option value="-1">--请选择--</option>
		      		   <option value="0">高</option>
		      		   <option value="1">中</option>
		      		   <option value="2">低</option>
    	          </select>
             </td>
             <td align="right"><label for="is_del"><strong class="fb">状态：</strong></label></td>
             <td align="left">
                 <select id="is_del" class="easyui-combobox" name="is_del" style="width:150px;" editable="false" data-options="required:true">
	      		   <option value="-1">--请选择--</option>
	      		   <option value="0">启用</option>
	      		   <option value="1">未启用</option>
    	         </select>
             </td>
          </tr>
          <tr>
             <td align="right"><label for=""><strong class="fb">附件：</strong></label></td><td colspan="3" align="left">  <div id="fj"></div></td>
          </tr>
           
           <!-- 
          <tr>
          	<td>
			<label for="task_name"><strong class="fb">子任务名称: </strong></label>
			 <input name="task_name" disabled="disabled" id="task_name" style="width:110px"></input> 
			 <label for="_par_task_name"><strong class="fb">父任务名称: </strong></label>
			 <input name="_par_task_name" id="_par_task_name" disabled="disabled" style="width:110px"></input> 
			  <input type="hidden" name="id" id="id"></input> 
			  <input type="hidden" name="par_task_id" id="par_task_id"></input>
			  <input type="hidden" name="par_task_name" id="par_task_name"></input>    
			  <input type="hidden" name="mod_id" id="mod_id"></input> 
		     </td>
          	</tr>
          	 <tr>
          	<td>
			  <label for="task_type"><strong class="fb">子任务类型：</strong></label>
		      <input class="easyui-combobox" disabled="disabled" id="task_type" name="task_type"></input> 
		      <label for="task_state"><strong class="fb">子任务状态：</strong></label>
		      <input class="easyui-combobox" id="task_state" disabled="disabled" name="task_state" />
		     </td>
          	</tr>
          <tr>
          	<td>
			<label for="content"><strong class="fb">子任务内容: </strong></label>
			<textarea id="content" name="content" disabled="disabled" class="easyui-validatebox" style="width:500px;height: 150px;" data-options="required:true"></textarea> 
		     </td>
		 </tr>
          <tr>
		     <td>
		    <input name="assigned_user_id" type="hidden" id="assigned_user_id"></input>
		     <label for="assigned_user_name"><strong class="fb">子任务发起人：</strong></label>
		      <input name="_assigned_user_name" disabled="disabled" id="_assigned_user_name" style="width:110px"></input>
		      <input name="assigned_user_name"  type="hidden"  id="assigned_user_name" style="width:110px"></input>
		      <label for="begin_date"><strong class="fb"> 任务开始日期：</strong></label>
		     <input id="begin_date" name="begin_date" disabled="disabled" class="easyui-datebox" style="width:100px;" />
			</td>
          </tr>
           <tr>
		     <td><div id="receive_user_id2" style="display: inline">
		     <label for="receive_user_id"><strong class="fb">指派给：</strong></label>
		      <input name="receive_user_id" id="receive_user_id" style="width:110px"></input></div> 
		      <label for="finsh_date"><strong class="fb"> 任务完成期限：</strong></label>
		     <input id="finsh_date" name="finsh_date" disabled="disabled" class="easyui-datebox" style="width:100px;" />
			</td>
          </tr>
          <tr>
          	<td width="100%" colspan="2">
          		<label for="remark"><strong class="fb">&nbsp&nbsp备&nbsp注：</strong></label>
				<textarea id="remark" disabled="disabled" name="remark" class="easyui-validatebox" style="width:500px;height: 150px;" data-options="required:true"></textarea> 
          	</td>
          </tr>
          <tr>
          	<td width="100%" colspan="2">
          	
    	     <label for="priority"><strong class="fb">优先级：</strong></label>
			   <select id="priority" disabled="disabled" class="easyui-combobox" name="priority" style="width:150px;" editable="false" data-options="required:true">
      		   <option value="-1">--请选择--</option>
      		   <option value="0">高</option>
      		   <option value="1">中</option>
      		   <option value="2">低</option>
    	     </select>
    	     <label for="is_del"><strong class="fb">状态：</strong></label>
			   <select id="is_del" class="easyui-combobox" disabled="disabled" name="is_del" style="width:150px;" editable="false" data-options="required:true">
      		   <option value="-1">--请选择--</option>
      		   <option value="0">启用</option>
      		   <option value="1">不启用</option>
    	     </select>
          	</td>
          </tr>
           <tr >
          	<td width="100%" colspan="2">
          	<label for=""><strong class="fb">附件：</strong></label>
          	    <div id="fj"></div>
          	</td>
          </tr>
          --></table>
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
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.imgAddShow.js"></script>
<script type="text/javascript"><!--
		$(function(){
			//初始化
			$.post('${ctx}/webservice/YwtTask.do?method=init&mod_id=108026',function(result){
				$("#_begin_begin_date").datebox('setValue',result._begin_begin_date);
				$("#_end_finsh_date").datebox('setValue',result._end_finsh_date);
				$("#_is_del").combobox('setValue',result._is_del);
				$("#_task_state").combobox('setValue',result._task_state);
				$("#_year").combobox('setValue',result._year);
				$("#_month").combobox('setValue',result._month); 
				$("#_priority").combobox('setValue',result._priority);
				$("#_task_state").combobox('setValue',result._task_state);

				$('#_fgs_dept_id').combobox({                 
					url:'${ctx}/manager/admin/CsAjax.do?method=getAjaxKonkaDeptList',  
				 	method:'post',              
				 	editable:false, //不可编辑状态                
				 	cache: false,               
				 	valueField:'DEPT_ID',                   
					textField:'DEPT_NAME'                   
				}); 
				$('#_task_type').combobox({
			    	 valueField:"type_id", 
			    	 textField:"type_name", 
					 editable:false,
					  data:result.taskTypeList
				  });
				  
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
			//初次进来需要初始化查询数据
			$('#table').datagrid('load',{
				_fgs_dept_id: $("#_fgs_dept_id").combobox("getValue"),
				_real_name_like:$("#_real_name_like").val(),
				_par_task_name_like:$("#_par_task_name_like").val(),
				_task_name_like :$("#_task_name_like").val(),
				_begin_begin_date: $("#_begin_begin_date").datebox('getValue'),
				_end_finsh_date: $("#_end_finsh_date").datebox('getValue'),
				_task_state: $("#_task_state").combobox('getValue'),
				_year: $("#_year").combobox('getValue'),
				_month: $("#_month").combobox('getValue'),
				_priority: $("#_priority").combobox('getValue'), 
				_is_del: $("#_is_del").combobox('getValue'),
				_task_type:$("#_task_type").combobox('getValue')
	   		});
			//查询按钮绑定事件
			$("#search").bind('click',function(){
                  if($("#_year").combobox('getValue')!="-1"&&$("#_month").combobox('getValue')=="-1"){
                         alert("请选择月份");
                         return false;
                   }
                  if($("#_month").combobox('getValue')!="-1"&&$("#_year").combobox('getValue')=="-1"){
                      alert("请选择年份");
                      return false;
                  }
				 /**$('#table').datagrid('reload');**/
				$('#table').datagrid('load',{
					_fgs_dept_id: $("#_fgs_dept_id").combobox("getValue"),
					_real_name_like:$("#_real_name_like").val(),
					_par_task_name_like:$("#_par_task_name_like").val(),
					_task_name_like :$("#_task_name_like").val(),
					_begin_begin_date: $("#_begin_begin_date").datebox('getValue'),
					_end_finsh_date: $("#_end_finsh_date").datebox('getValue'),
					_task_state: $("#_task_state").combobox('getValue'), 
					_year: $("#_year").combobox('getValue'),
					_month: $("#_month").combobox('getValue'),
					_priority: $("#_priority").combobox('getValue'), 
					_is_del: $("#_is_del").combobox('getValue'),
					_task_type:$("#_task_type").combobox('getValue')
		   		});
			});

			$("#exprot").bind('click',function(){
				$.messager.confirm('温馨提示', '是否导出数据？', function(r){
					if (r){
						var str = '&excel_all=1&_is_del='+$("#_is_del").combobox('getValue');
						$("#fm").attr("action", "${ctx}/webservice/YwtTask.do?method=list"+str);
			    		$("#fm").submit();
					}  
				});

			});

		    $("#addOrEdit #receive_user_id1").click(function(){
	    		 var curr_task_id=$("#addOrEdit #id").val();
	    		 
	    		 var selectUser=$("#addOrEdit #receive_user_id").combogrid('getValues');
	    		 //var selectUser="test";
	    		 var returnValue = window.showModalDialog("${ctx}/webservice/YwtTask.do?method=selectUser&curr_task_id=" + curr_task_id,selectUser,"dialogWidth:900px;status:no;dialogHeight:500px");
	    		 
	    		 try{ 
		    		 	if(returnValue!=null&&returnValue.new_select_user) {
			    			 data=returnValue.new_select_user;
			    			 $("#addOrEdit #receive_user_id").combogrid('setValues',returnValue.new_select_user);
		    		 	}
		    	  }catch(e){}
		    	 });
		    $("#view #receive_user_id2").click(function(){
	    		 var curr_task_id=$("#view #id").val();
	    		 var selectUser=$("#view #receive_user_id").combogrid('getValues');
	    		 var returnValue = window.showModalDialog("${ctx}/webservice/YwtTask.do?method=selectUser&curr_task_id=" + curr_task_id,selectUser,"dialogWidth:900px;status:no;dialogHeight:500px");
	    		 try{ 
	    		 	if(returnValue!=null&&returnValue.new_select_user) {
		    			 data=returnValue.new_select_user;
		    			 $("#addOrEdit #receive_user_id").combogrid('setValues',returnValue.new_select_user);
	    		 	}
		    	  }catch(e){}
		     });
		});
		});
		
		function get_add_date(value,rec){
            return rec.map.add_date;
		}
		//查看当前任务
		function get_task_name(value,rec){
			 return '<a href="javascript:void(0)" style="color:red;" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="viewTask('+rec.id+')">'+rec.task_name+'</a>';
		}

		//附件显示
		function get_subcount(value,rec){
			if(null==rec.map.subcount||''==rec.map.subcount){
				 return '0';
			}else{
				  return '<a href="javascript:void(0)" style="color:red;" iconCls="icon-add" plain="true" onclick="subTaskList('+rec.id+',\''+rec.map.subcount+'\')">'+rec.map.subcount+'<b>＋</b></a>';
			}
		}
		
		function get_content(value){
			if(value.length>5){
				return value.substr(0,5)+"...";
			}else{
				return value;
			}
		}
		function get_remark(value){
			if(value.length>5){
				return value.substr(0,5)+"...";
			}else{
				return value;
			}
		}
		
		
		//修改
		function get_id_update(value,rec){
			var cz='';
			 //cz ='<a href="javascript:void(0)" style="color:red;" iconCls="icon-add" plain="true" onclick="newTask('+value+',\''+rec.task_name+'\',\''+rec.task_state+'\',\''+rec.is_del+'\')">添加子任务</a>';
			    //cz+='<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="reveList('+value+')">审核</a>';
			    cz+=' <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editTask('+value+','+rec.par_task_id+')">修改</a>';
			    cz+=' <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="viewTask('+value+')">查看</a>';
			    if(rec.is_del!=1)
			    {
				    
			    	cz+=' <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delTask('+value+')">停用</a>';
			    }else
				{
			    	cz+=' <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="updateIsdel('+value+')">启用</a>';
				}
			    return cz;
		}
		//优先级
		function get_priority(value){
			if(value==0){
			     return "高";
			}else if(value==1){
				return "中";
            }else if(value==2){
                return "低";    
			}
		}
		//任务类型
		function get_task_type(value,rec){
			return rec.map.type_name;
		}
		//任务开始日期
		function get_begin_date(value,rec){
            return rec.map.begin_date;
		}
		//完成期限
		function get_finsh_date(value,rec){
            return rec.map.finsh_date;
		}
		
		//任务状态
		function get_task_state(value){
			if(value==0){
			    return "<lable style='color: red;'><b>未开始</b></lable>";
			}else if(value==1){
				return "<lable style='color: green;'><b>进行中</b></lable>";
            }else if(value==2){
            	return "已完成";    
            }
		}
		//部门
		function get_fgs_Dept(value,rec){
            return rec.map.fgs_Dept;
		}
		//任务交办人
		function get_assigned_user_name(value,rec){
            return rec.map.reveUser;
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
		//是否启用
		function get_is_del(value){
			if(value==0){
			  return "启用";
			}else if(value==1){
              return "未启用";
			}
		}
		//删除人
		function get_del_user_name(value,rec){
            return rec.map.delUser;
		}
		//删除日期
		function get_del_date(value,rec){
            return rec.map.del_date;
		}
		
		//查看子任务列表
		function subTaskList(par_id,subcount){
			if(null==subcount||''==subcount){alert("无子任务！");return false;}
			if(null==par_id||''==par_id){alert("当前任务信息获取失败！");return false;}
			//初次进来需要初始化查询数据
			$('#table').datagrid('load',{
				_par_task_id: par_id
	   		});
		}
		//新建计划
		var url;
	    function newTask(par_id,par_name,task_state,is_del){
	    	$("#par_task_name").attr("hidden",true);
	 		$("#addOrEdit #hidden").attr("hidden",false);
	    	
		    if(null!=task_state&&task_state!=''&&task_state==2){
			    alert("当前任务已经完成不可添加子任务");
			    return false;
			}
		    if(par_id){
		    	if(!confirm("新建子任务？"))
			    {
				    return;
			    }
			    else
				{
		//	    	$("#par_task_name").attr("hidden",false);
			    	$("#addOrEdit #hidden").attr("hidden",true);
			    	//$("#addOrEdit #par_task_name").attr('disable',true);
				}
		    	
	    		if(is_del==1)
		    	{
	    			alert("当前任务停用不可添加子任务");
	    			return;
			    }	
			}
		    
	    	 $.post('${ctx}/webservice/YwtTask.do?method=add',function(result){
	    		 $("#addOrEdit").form('clear');
	    		 $("#addOrEdit #begin_date").datebox('setValue',result.begin_date);
	    		 $("#addOrEdit #assigned_user_id").val(result.assigned_user_id);
	    		 $("#addOrEdit #assigned_user_name").val(result.assigned_user_name);
	    		 $("#addOrEdit #_assigned_user_name").val(result.assigned_user_name);
	    		 
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
		    	   
		    	 $('#addOrEdit #task_type').combogrid({
					  data:[]
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
		    	 $('#addOrEdit #par_task_id').combogrid({
					  data:[]
				 });
		    	 $("#addOrEdit #par_task_id").combogrid({
			    	    panelWidth: 110,
			    	    idField: 'task_id',
			 	 	    textField: 'task_name',
			  		    data:result.revetasklist,
			    	    columns: [[
			    	     {field:'task_name',title:'请选择',width:120}
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
				if (par_id) {
				    $("#addOrEdit #par_task_id").combobox('setValue',par_id);
				}else {
				    $("#addOrEdit #par_task_id").combobox('setValue','');
				}
				if (par_name) {
					$("#addOrEdit #par_task_name").val(par_name);
				}else {
					$("#addOrEdit #par_task_name").val("顶级");
				}
				//$("#addOrEdit #par_task_i").combobox('setValue',par_name);
				$("#addOrEdit #is_del").combobox('setValue',0);    
			    $("#addOrEdit #priority").combobox('setValue',0);
		    	$("#addOrEdit #content").textbox({
		    		maxLength: 250,
		    		onInput: function(event, status) {
		    			var img = '<img src="${ctx}/styles/images/tishi.gif" style="vertical-align:middle;" />';
		    			$("#content_note").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		    		}
		    	}).blur(function() {
		    		$("#content_note").slideUp("normal");
		    	});
		    	$("#addOrEdit #remark").textbox({
		    		maxLength: 250,
		    		onInput: function(event, status) {
		    			var img = '<img src="${ctx}/styles/images/tishi.gif" style="vertical-align:middle;" />';
		    			$("#remark_note").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		    		}
		    	}).blur(function() {
		    		$("#remark_note").slideUp("normal");
		    	});
		    	$("#addOrEdit #remark").textbox({
		    		maxLength: 250,
		    		onInput: function(event, status) {
		    			var img = '<img src="${ctx}/styles/images/tishi.gif" style="vertical-align:middle;" />';
		    			$("#remark_note").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		    		}
		    	}).blur(function() {
		    		$("#remark_note").slideUp("normal");
		    	});
		    	$("#addOrEdit #fj").imgshow({
					ctx:"${ctx}"
			    });
		    	
		    	if(par_id){
		    		$('#dlg').dialog('open').dialog('setTitle','新增子任务'); 
			    }else{
			    	$('#dlg').dialog('open').dialog('setTitle','新增任务'); 
				}
	         });
	     url = '${ctx}/webservice/YwtTask.do?method=save';
	    }
	    //保存任务
	    function saveYwtTask(){
	        $("#addOrEdit").form('submit',{
	            url: url,
	            onSubmit: function(){
		        	 var taskname=$("#addOrEdit #task_name").val();
		        	 if(taskname==""){
			        	 alert("任务名称不能为空！");
			        	 return false;
		        	 }
		        	  if(taskname.length>50){
			        	 alert("任务名称不能超过50个字!");
			        	 return false;
		        	 }
		        	 var tasktype=$("#addOrEdit #task_type").combobox('getValue');
		        	 if(tasktype==""){
			        	 alert("任务类型不能为空!");
			        	 return false;
			         }
		        	 var content= $("#addOrEdit #content").val();
		        	 if(content==""){
		        		 alert("任务内容不能为空!");
		        		 return false;
			         }
		        	 if(content.length>250){
			        	 alert("任务内容不能超过250个字!");
			        	 return false;
			         }
		        	 var receives=$('#addOrEdit #receive_user_id').combogrid('getValues');
			        	 if(receives.length==0){
			        	       alert("任务接受至少选一个!");
			        	 return false;
		        	 }
					 var Sysdate=new Date();
			         var begin=new Date($("#addOrEdit #begin_date").datebox('getValue'));
			         if(Sysdate.toLocaleDateString()>begin.toLocaleDateString()){
					 	alert("日期不能早于当前时刻");
				        	 return false;
			         }
			         var finshdate=$("#addOrEdit #finsh_date").datebox('getValue');
			         if (finshdate=="") {
			        	 alert("预计完成日期不能为空!");
			        	 return false;
					 }
			        var finsh=new Date($("#addOrEdit #finsh_date").datebox('getValue'));
			        if(Sysdate.toLocaleDateString()>finsh.toLocaleDateString() || begin.toLocaleDateString()>finsh.toLocaleDateString())
			        {
			        	alert("预计完成日期不能早于任务开始时间，且要晚于当前时刻!");
			        	 return false;
				    }
			        var isdel= $("#addOrEdit #is_del").combobox('getValue');
			        if(isdel==""){
			        	 alert("状态不能为空!");
			        	 return false;
			         }
			        var priority=$("#addOrEdit #priority").combobox('getValue');
			        if(priority==""){
			        	 alert("优先级不能为空!");
			        	 return false;
			         }
			         
			        var remark=$("#addOrEdit #remark").val();
		        	  if(remark.length>250){
			        	 alert("备注不能超过250个字!");
			        	 return false;
		        	 }
			        
			        $("#dlg-buttons").hide();
			    },
	            success: function(result){
			    	result=jQuery.parseJSON(result);
                    $.messager.show({
                        title: '操作提示！',
                        msg: result.res
                    });
	                $('#dlg').dialog('close');        // close the dialog
	                $('#table').datagrid('clearSelections');
	                $('#table').datagrid('reload');
	                /**$('#table').datagrid('load',{
	                	_fgs_dept_id: $("#_fgs_dept_id").combobox("getValue"),
						_real_name_like:$("#_real_name_like").val(),
						_task_name_like :$("#_task_name_like").val(),
						_par_task_name_like:$("#_par_task_name_like").val(),
						_begin_begin_date: $("#_begin_begin_date").datebox('getValue'),
						_end_finsh_date: $("#_end_finsh_date").datebox('getValue'),
						_task_state: $("#_task_state").combobox('getValue'),
						_year: $("#_year").combobox('getValue'),
						_month: $("#_month").combobox('getValue'), 
						_priority: $("#_priority").combobox('getValue'), 
						_is_del: $("#_is_del").combobox('getValue')
			   		});**/
	                $("#dlg-buttons").show();
	            }
	        });
	    }
	    
	    //查看审核清单
	    function reveList(task_id){
	    	if(task_id==''){alert("审核标示获取失败！");return false;}
	    	var returnValue = window.showModalDialog("${ctx}/webservice/YwtTask.do?method=reveList&task_id=" + task_id,window,"dialogWidth:900px;status:no;dialogHeight:500px");

		}
	    //修改任务
	 	function editTask(id,par_task_id){
	 		$("#addOrEdit #hidden").attr("hidden",true);
	 		if(id==''){alert("修改标示获取失败！");return false;}
	 		$.post('${ctx}/webservice/YwtTask.do?method=edit&id='+id,function(result){
	 			$("#addOrEdit").form('clear');
	 			
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
		        $('#addOrEdit #task_type').combobox({
					  data:[]
				 });
		    	 $('#addOrEdit #task_type').combobox({
			    	 valueField:"type_id", 
			    	 textField:"type_name", 
					 editable:false,
					  data:result.taskTypeList
				  });

// 		    	 $('#addOrEdit #par_task_name').combogrid({
// 					  data:[]
// 				 });
// 		    	 $("#addOrEdit #par_task_name").combogrid({
// 			    	    panelWidth: 110,
// 			    	    idField: 'task_id',
// 			    	    textField: 'task_name',
// 			    	    data:result.revetasklist,
// 			    	    columns: [[
// 			    	     {field:'task_name',title:'请选择',width:120}
// 			    	    ]],
// 			    	    editable:false,
// 			    	    fitColumns: true
// 			     });
// 		    	 $('#addOrEdit #par_task_id').combogrid({
// 					  data:[]
// 				 });
// 		    	 $("#addOrEdit #par_task_id").combogrid({
// 			    	    panelWidth: 110,
// 			    	    idField: 'task_id',
// 			    	    textField: 'task_name',
// 			    	    data:result.revetasklist,
// 			    	    columns: [[
// 			    	     {field:'task_name',title:'请选择',width:120}
// 			    	    ]],
// 			    	    editable:false,
// 			    	    fitColumns: true
// 			    	   });
		    	 $('#addOrEdit #task_state').combobox({
			    	 valueField:"id", 
			    	 textField:"text", 
					 editable:false,
						 data:[{id:"-1",text:"-请选择-"},
			                   {id:0,text:"未开始"},
			                   {id:1,text:"进行中"},
							   {id:2,text:"已完成"}]});
//
	//	    	 $("#addOrEdit #par_task_name").combobox('disable');
	//	    	 $("#addOrEdit #par_task_name").attr('disable',true);
				 $("#addOrEdit #par_task_name").val(result.entity.par_task_name);
		    	 $("#addOrEdit #id").val(result.entity.id);
		    	 $("#addOrEdit #par_task_id").val(result.entity.par_task_id);
		    	 
		    	 $("#addOrEdit #_par_task_name").val(result.entity.par_task_name);
			     $("#addOrEdit #task_name").val(result.entity.task_name); 
			  //   $("#addOrEdit #par_task_id").combobox('setValue',result.entity.par_task_id);
			     $("#addOrEdit #task_type").combobox('setValue',result.entity.task_type);
			     $("#addOrEdit #task_state").combobox('setValue',result.entity.task_state);    
			         
			     $("#addOrEdit #content").val(result.entity.content);     
			     $("#addOrEdit #assigned_user_id").val(result.assigned_user_id);
	    		 $("#addOrEdit #assigned_user_name").val(result.assigned_user_name);
	    		 $("#addOrEdit #_assigned_user_name").val(result.assigned_user_name);
			     $("#addOrEdit #begin_date").datebox('setValue',result.begin_date);
			     
			     if(result.receive_user_id_name_arry){
			    	 $('#addOrEdit #receive_user_id').combogrid('setValues',result.receive_user_id_name_arry);
			       }
			     $("#addOrEdit #finsh_date").datebox('setValue',result.finsh_date);
			     $("#addOrEdit #remark").val(result.entity.remark);  
			     $("#addOrEdit #is_del").combobox('setValue',result.entity.is_del);    
			     $("#addOrEdit #priority").combobox('setValue',result.entity.priority);

			     $("#addOrEdit #content").textbox({
			    		maxLength: 250,
			    		onInput: function(event, status) {
			    			var img = '<img src="${ctx}/styles/images/tishi.gif" style="vertical-align:middle;" />';
			    			$("#content_note").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
			    		}
		    	}).blur(function() {
		    		$("#content_note").slideUp("normal");
		    	});
			    	
		    	$("#addOrEdit #remark").textbox({
		    		maxLength: 250,
		    		onInput: function(event, status) {
		    			var img = '<img src="${ctx}/styles/images/tishi.gif" style="vertical-align:middle;" />';
		    			$("#remark_note").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		    		}
		    	}).blur(function() {
		    		$("#remark_note").slideUp("normal");
		    	});
			     //附件
				 $("#addOrEdit #fj").imgshow({
					ctx:"${ctx}",
					delUrl:"${ctx}/manager/admin/KonkaSpActivityManager.do?method=deleteFile1",
					data:result.fj_paths
			    });
		        $('#dlg').dialog('open').dialog('setTitle','修改任务'); 
	         });
	     url = '${ctx}/webservice/YwtTask.do?method=save&par_task_id='+par_task_id;
		}
		//查看任务
		function viewTask(id){
			//查看时只能选择一行记录
			if(id==''){alert("查看标示获取失败！");return false;}
		 		$.post('${ctx}/webservice/YwtTask.do?method=view&id='+id,function(result){
		 			 $('#view').form('clear');
		 			$("#view #hidden").attr("hidden",true);
		 			 $("#view #receive_user_id").combogrid({
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
			    	   
			 			$('#view #task_type').combobox({
							  data:[]
						 });
				    	 $('#view #task_type').combobox({
					    	 valueField:"type_id", 
					    	 textField:"type_name", 
							 editable:false,
								 data:result.taskTypeList
						  });
						 
				    	 $('#view #task_state').combobox({
					    	 valueField:"id", 
					    	 textField:"text", 
							 editable:false,
								 data:[{id:"-1",text:"-请选择-"},
					                   {id:0,text:"未开始"},
					                   {id:1,text:"进行中"},
									   {id:2,text:"已完成"}]});

				    	 $("#view #id").val(result.entity.id);
				    	 $("#view #par_task_id").val(result.entity.par_task_id);
				    	 $("#view #par_task_name").val(result.entity.par_task_name);
				    	 $("#view #_par_task_name").val(result.entity.par_task_name);
					     $("#view #task_name").val(result.entity.task_name); 
					     
					     $("#view #task_type").combobox('setValue',result.entity.task_type);
					     $("#view #task_state").combobox('setValue',result.entity.task_state);    
					         
					     $("#view #content").val(result.entity.content);     
					     $("#view #assigned_user_id").val(result.assigned_user_id);
			    		 $("#view #assigned_user_name").val(result.assigned_user_name);
			    		 $("#view #_assigned_user_name").val(result.assigned_user_name);
					     $("#view #begin_date").datebox('setValue',result.begin_date);
					     
					     if(result.receive_user_id_name_arry){
					    	 $('#view #receive_user_id').combogrid('setValues',result.receive_user_id_name_arry);
					       }
					     $("#view #finsh_date").datebox('setValue',result.finsh_date);
					     $("#view #remark").val(result.entity.remark);  
					     $("#view #is_del").combobox('setValue',result.entity.is_del);    
					     $("#view #priority").combobox('setValue',result.entity.priority);

					     //附件
						 $("#view #fj").imgshow({
							ctx:"${ctx}",
							isAdd:false,
							//delUrl:"${ctx}/manager/admin/KonkaSpActivityManager.do?method=deleteFile1",
							data:result.fj_paths
					    });
			        $('#dlg_view').dialog('open').dialog('setTitle','查看任务'); 
		         });
		}

		//删除任务
		function delTask(id){
			//编辑时只能选择一行记录
			if(id==''){alert("删除标示获取失败！");return false;}
	        	  $.messager.confirm('温馨提示', '是否删除选中数据？', function(r){
					  $.post('${ctx}/webservice/YwtTask.do?method=delete&id='+id,function(result){});
					  $('#table').datagrid('reload');
							/**$('#table').datagrid('load',{
								    _fgs_dept_id: $("#_fgs_dept_id").combobox("getValue"),
								    _real_name_like:$("#_real_name_like").val(),
									_task_name_like :$("#_task_name_like").val(),
									_par_task_name_like:$("#_par_task_name_like").val(),
									_begin_begin_date: $("#_begin_begin_date").datebox('getValue'),
									_end_finsh_date: $("#_end_finsh_date").datebox('getValue'),
									_task_state: $("#_task_state").combobox('getValue'),
									_year: $("#_year").combobox('getValue'),
									_month: $("#_month").combobox('getValue'), 
									_priority: $("#_priority").combobox('getValue'), 
									_is_del: $("#_is_del").combobox('getValue')
				    	   		});**/
					});
		}
		//启用任务
		function updateIsdel(id){
			//编辑时只能选择一行记录
			if(id==''){alert("启用标示获取失败！");return false;}
	        	  $.messager.confirm('温馨提示', '是否启用选中数据？', function(r){
					  $.post('${ctx}/webservice/YwtTask.do?method=updateIsdel&id='+id,function(result){});
					  $('#table').datagrid('reload');
							/**$('#table').datagrid('load',{
								    _fgs_dept_id: $("#_fgs_dept_id").combobox("getValue"),
								    _real_name_like:$("#_real_name_like").val(),
									_task_name_like :$("#_task_name_like").val(),
									_par_task_name_like:$("#_par_task_name_like").val(),
									_begin_begin_date: $("#_begin_begin_date").datebox('getValue'),
									_end_finsh_date: $("#_end_finsh_date").datebox('getValue'),
									_task_state: $("#_task_state").combobox('getValue'),
									_year: $("#_year").combobox('getValue'),
									_month: $("#_month").combobox('getValue'), 
									_priority: $("#_priority").combobox('getValue'), 
									_is_del: $("#_is_del").combobox('getValue')
				    	   		});**/
					});
		}
	--></script>
</body>
</html>
