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
		<td>&nbsp;当前位置：<span>客户管理 > 业务通 > 任务交办 > 任务审核</span></td>
	</tr>
</table>
</div>
<div style="padding: 5px">
<form id="fm" method="post">
<table width="100%" border="0" cellspacing="5" cellpadding="0"
	class="rtable1">
	<tr>
	  <td align="left"><label for="_begin_add_date"><strong class="fb"> 接收起始时间： </strong></label>
		<input id="_begin_add_date" class="easyui-datebox" style="width:100px;" />
	</td>	
	<td align="left"><label for="_end_add_date"><strong class="fb"> 接收结束时间： </strong></label>
		<input id="_end_add_date" class="easyui-datebox" style="width:100px;" />
	</td>
	<td align="left"><label for="_is_receive"><strong class="fb">接收状态：</strong></label>
		<select id="_is_receive" class="easyui-combobox" name="_is_receive" style="width:150px;" editable="false" data-options="required:true">
      		   <option value="-1">--请选择--</option>
      		   <option value="0"> 接收 </option>
      		   <option value="1">拒绝</option>
      		    <option value="2">未接受</option>
    	</select>
	</td>
	<td align="left"><label for="_audit_state"><strong class="fb">审核状态：</strong></label>
		<select id="_audit_state" class="easyui-combobox" name="_audit_state" style="width:150px;" editable="false" data-options="required:true">
      		   <option value="-1">--请选择--</option>
      		   <option value="0">已审核 </option>
      		   <option value="1">未审核</option>
    	</select>
	</td>
	<td align="left"><label for="_state"><strong class="fb">完成状态：</strong></label>
		<select id="_state" class="easyui-combobox" name="_state" style="width:150px;" editable="false" data-options="required:true">
      		   <option value="-1">--请选择--</option>
      		   <option value="0">完成</option>
      		   <option value="1">未完成</option>
    	</select>
	</td>
	</tr>
</table>
</form>
</div>
<div id="tb" style="height: auto; padding-right: 10px" align="right">
<a href="#" class="easyui-linkbutton" id="search" iconCls="icon-search">查
询</a></div>
    <div>
	<table id="table" class="easyui-datagrid" title="任务审核列表"  
	        data-options="url:'${ctx}/webservice/YwtTask.do?method=reveList1&task_id=${task_id}'"
	        rownumbers="true" idField="id" singleSelect="false" pagination="true"  loadMsg="正在拼命加载中，请稍后..."  fitColumns="false" >   
	    <thead>
	    <tr style="bgcolor:orange;" >
	        <th width="80px;"  field="user_name" formatter="get_user_name">接收人</th>
	        <th width="80px;"  field="task_name" >任务名称</th>
	        <th width="80px;"  field="add_date" formatter="get_add_date">接收时间</th>
		    <th width="80px;"  field="is_receive" formatter="get_is_receive">接收状态</th>
		    <th width="80px;"  field="state" formatter="get_state">完成状态</th>
<!--		    <th width="80px;"  field="user_dept_name" formatter="get_user_dept_name">接收部门</th>-->
		    <th width="80px;"  field="finsh_date" formatter="get_finsh_date">完成时间</th>
		    <th width="80px;"  field="audit_state"  formatter="get_audit_state">审核状态</th>
		     <th width="80px;"  field="audit_info" >审核意见</th>
		    <th width="80px;"  field="id" formatter="get_audit">审核</th>
	    </tr>
	   </thead>      
	</table>
	<div align="right">
	    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:window.close();" style="width:90px">退出</a>
	</div>
	</div>
	  
	<!-- 审核 -->
	<div id="dlg_audit" class="easyui-dialog" style="width:400px;height:300px;padding:10px 20px"  closed="true" buttons="#audit-buttons">  
      <form id="audit" method="post" novalidate="false" > 
          <table width="100%" border="0" align="right" cellspacing="0" cellpadding="0" class="rtable3"> 
           <tr>
          	<td>
          	 <input type="hidden" name="id" id="id"></input>
			 <input type="hidden" name="mod_id" id="mod_id"></input> 
			<label for="audit_info"><strong class="fb">任务状态: </strong></label>
			<select id="is_receive" class="easyui-combobox" name="is_receive" style="width:150px;" editable="false" data-options="required:true">
      		   <option value="-1">--请选择--</option>
      		   <option value="0"> 接收 </option>
      		   <option value="1">拒绝</option>
      		    <option value="2">未接受</option>
    	    </select>
		     </td>
		 </tr>
          <tr>
          	<td>
			<label for="audit_info"><strong class="fb">审核意见: </strong></label>
			<textarea id="audit_info" name="audit_info" class="easyui-validatebox" style="width:250px;height: 150px;" data-options="required:true"></textarea> 
		    <div id="audit_info_note"  style="margin-left:50px;color:#0066FF;font-size:12px;display:none" ><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div>
		     </td>
		 </tr>
          </table>
       </form>
		</div>
	 <div id="audit-buttons">
	    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveAudit()" style="width:90px">审核</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg_audit').dialog('close')" style="width:90px">退出</a>
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
<script type="text/javascript">
$(function(){
	$.post('${ctx}/webservice/YwtTask.do?method=reveInit&mod_id=108027',function(result){
		$("#_begin_add_date").datebox('setValue',result._begin_add_date);
		$("#_end_add_date").datebox('setValue',result._end_add_date);
		$("#_is_receive").combobox('setValue',result._is_receive);
		$("#_audit_state").combobox('setValue',result._audit_state);
		$("#_state").combobox('setValue',result._state);
		
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
		_task_name_like :$("#_task_name_like").val(),
		_begin_add_date: $("#_begin_add_date").datebox('getValue'),
		_end_add_date: $("#_end_add_date").datebox('getValue'),
		_is_receive: $("#_is_receive").combobox('getValue'),
		_audit_state:$("#_audit_state").combobox('getValue'),
		_state: $("#_state").combobox('getValue')
		});
	//查询按钮绑定事件 
	$("#search").bind('click',function(){
		$('#table').datagrid('load',{
			_task_name_like :$("#_task_name_like").val(),
			_begin_add_date: $("#_begin_add_date").datebox('getValue'),
			_end_add_date: $("#_end_add_date").datebox('getValue'),
			_is_receive: $("#_is_receive").combobox('getValue'),
			_audit_state:$("#_audit_state").combobox('getValue'),
			_state: $("#_state").combobox('getValue')
   		});
	});

	$("#exprot").bind('click',function(){
		$.messager.confirm('温馨提示', '是否导出数据？', function(r){
			if (r){
				var str = '&excel_all=1&_state='+$("#_state").combobox('getValue');
				$("#fm").attr("action", "${ctx}/webservice/YwtTaskReceive.do?method=list"+str);
	    		$("#fm").submit();
			}  
		});

	});
});
});
    
	function get_user_name(value,rec){
	    return rec.map.user_name;
	}
	//接收时间
	function get_add_date(value,rec){
	    return rec.map.add_date;
	}
	//接收状态
	function get_is_receive(value){
		if(value==0){
		    return "接收";
		}else if(value==1){
			return "拒绝";
        }else if(value==2){
			return "未接受";
        }
	}
	//完成状态
	function get_state(value){
		if(value==0){
		     return "完成";
		}else if(value==1){
			return "未完成";
        }
	}
	//完成状态
	function get_audit_state(value){
		if(value==0){
		     return "已审核 ";
		}else if(value==1){
			return "未审核";
        }
	}
	//接收人部门
	/**function get_user_dept_name(value,rec){
		return rec.map.user_dept_name;
	}**/
	//完成时间
	function get_finsh_date(value,rec){
        return rec.map.reve_finsh_date;
	}
	
	//审核
	function get_audit(value,rec){
		return '<a href="javascript:void(0)" style="color:red;" iconCls="icon-add" plain="true" onclick="audit('+value+',\''+rec.is_receive+'\''+',\''+rec.state+'\''+',\''+rec.audit_state+'\')">审核</a>';
	}
	
	function audit(id,is_receive,state,audit_state){
		if(state==1){alert("未完成任务不能审核");return false;}
		if(audit_state==0){alert("不可重复审！");return false;}
		$("#audit #audit_info").textbox({
    		maxLength: 250,
    		onInput: function(event, status) {
    			var img = '<img src="${ctx}/styles/images/tishi.gif" style="vertical-align:middle;" />';
    			$("#audit_info_note").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
    		}
    	}).blur(function() {
    		$("#visit_desc_note").slideUp("normal");
    	});
		$("#audit #id").val(id);
		$("#audit #is_receive").combobox('setValue',is_receive);  
		$('#dlg_audit').dialog('open').dialog('setTitle','审核任务'); 
	}
	 function saveAudit(){
	        $("#audit").form('submit',{
	            url: "${ctx}/webservice/YwtTask.do?method=audit_save",
	            onSubmit: function(){
		        	 var audit_info= $("#audit #audit_info").val();
		        	 if(audit_info.length==""){
		        		 alert("任务内容不能为空!");
		        		 return false;
			         }
		        	 if(audit_info.length>250){
			        	 alert("任务内容不能超过250个字!");
			        	 return false;
			         }
			        $("#audit-buttons").hide();
			    },
	            success: function(result){
			     result=jQuery.parseJSON(result);
                 $.messager.show({
                     title: '操作提示！',
                     msg: result.res
                 });
	                $('#dlg_audit').dialog('close');        // close the dialog
	                $('#table').datagrid('clearSelections');
	                
	                $('#table').datagrid('reload');
	                $("#audit-buttons").show();
	            }
	        });
	    }
	</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
