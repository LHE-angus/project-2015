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
		<td>&nbsp;当前位置：<span id='nav'></span></td>

	</tr>
</table>
</div>
<div style="padding: 5px">
<form id="fm" method="post">
<table width="100%" border="0" cellspacing="5" cellpadding="0"
	class="rtable1">
	<tr>
	<td align="left"><label for="_real_name_like"><strong class="fb">姓名： </strong></label>
		<input id="_real_name_like" name="_real_name_like" maxlength="20"
			style="width: 100px" />
	</td>
	
	<td align="left"><label for="_sign_in_date"><strong class="fb"> 开始休息时间： </strong></label>
		<input id="_sign_in_date" class="easyui-datebox" style="width:100px;" />
	</td>	
	
	<td align="left"><label for="_sign_out_date"><strong class="fb"> 结束休息时间： </strong></label>
		<input id="_sign_out_date" class="easyui-datebox" style="width:100px;" />
	</td>
	<td align="left"><label for="_is_lock"><strong class="fb">是否启用：</strong></label>
		<select id="_is_lock" class="easyui-combobox" name="_is_lock" style="width:150px;" editable="false" data-options="required:true">
      		   <option value="-1">--请选择--</option>
      		   <option value="0">启用</option>
      		   <option value="1">停用</option>
    	</select>
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
<table id="table" class="easyui-datagrid" title="考勤时间设定"  
        data-options="url:'${ctx}/webservice/YwtAttendanceDay.do?method=list',fitColumns:true,singleSelect:true"
        rownumbers="true" idField="id" toolbar="#toolbar" singleSelect="false" pagination="true"  loadMsg="正在拼命加载中，请稍后..."  fitColumns="true" >   
    <thead>   
    <tr style="bgcolor:orange;" >
        <th field="ck" checkbox="true"></th>  
         <th width="80px;"  field="real_name"   >姓名</th>
	    <th width="80px;"  field="sign_in_date"  formatter="get_sign_in_date">休息开始时间</th>
	    <th width="80px;"  field="sign_out_date" formatter="get_sign_out_date">休息结束时间</th>
	    <th width="80px;"  field="add_user_id" formatter="get_user_name">添加人</th>
	    <th width="80px;"  field="add_date" formatter="get_add_date">添加时间</th>
	    <th width="80px;"  field="memo"  >备注</th>
	    <th width="80px;"  field="is_lock" formatter="get_is_lock">是否启用</th>
    </tr>
   </thead>      
</table>  
</div>
<div id="toolbar" style="margin-left:5px;margin-top:5px;border-top-style: solid;border-top-width: 1px; border-top-color:#C9CFCD "> 
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newAttendanceDay()">新增</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editAttendanceDay()">修改</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delAttendanceDay()">删除</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="viewAttendanceDay()">查看</a>
</div>    

<!-- 新增和修改页面dialog -->
	<div id="dlg" class="easyui-dialog" style="width:700px;height:500px;padding:10px 20px"  closed="true" buttons="#dlg-buttons">  
      <form id="addOrEdit" method="post" novalidate="false" enctype="multipart/form-data"> 
          <table width="100%" border="0" align="right" cellspacing="0" cellpadding="0" class="rtable3"> 
          <tr>
          	<td>
			<label for="add_user_id_name"><strong class="fb">姓名: </strong></label>
			 <input name="add_user_id_name" id="add_user_id_name" style="width:110px"></input> 
			 <input type="hidden" name="id" id="id"></input> 
				<input type="hidden" name="mod_id" id="mod_id"></input> 
		     </td>
          	</tr>
          <tr>
          	<td>
			<label for="sign_in_date"><strong class="fb">休息开始时间: </strong></label>
			<input id="sign_in_date" name="sign_in_date" class="easyui-datebox" style="width:100px;" />
		     </td>
		 </tr>
          <tr>
		     <td>
		     <label for="sign_out_date"><strong class="fb">休息结束时间：</strong></label>
		     <input id="sign_out_date" name="sign_out_date" class="easyui-datebox" style="width:100px;" />
			</td>
          </tr>
          <tr>
          	<td width="100%" colspan="2">
          		<label for="memo"><strong class="fb">备注：</strong></label>
				<textarea id="memo" name="memo" class="easyui-validatebox" style="width:500px;height: 150px;" data-options="required:true"></textarea> 
          	</td>
          </tr>
          </table>
       </form>
		</div>
	 <div id="dlg-buttons">
	    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveYwtAttendanceDay()" style="width:90px">保存</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">退出</a>
	</div> 
<!--查看-->
<div id="dlg_view" class="easyui-dialog" style="width:700px;height:500px;padding:10px 20px"  closed="true" buttons="#dlg_view-buttons">  
      <form id="view" method="post" novalidate="false" enctype="multipart/form-data"> 
          <table width="100%" border="0" align="right" cellspacing="0" cellpadding="0" class="rtable3"> 
          
         <tr>
          	<td>
			<label for="add_user_id_name"><strong class="fb">姓名: </strong></label>
			 <input name="add_user_id_name" id="add_user_id_name" style="width:110px"></input> 
		     </td>
          	</tr>
        <tr>
          	<td>
			<label for="sign_in_date"><strong class="fb">休息开始时间: </strong></label>
			<input id="sign_in_date" class="easyui-datebox" style="width:100px;" />
		     </td>
		 </tr>
          <tr>
		     <td>
		     <label for="sign_out_date"><strong class="fb">休息结束时间：</strong></label>
		     <input id="sign_out_date" class="easyui-datebox" style="width:100px;" />
			</td>
          </tr>
          <tr>
          	<td width="100%" colspan="2">
          		<label for="memo"><strong class="fb">备注：</strong></label>
				   		<textarea id="memo" name="memo" class="easyui-validatebox" style="width:500px;height: 150px;" data-options="required:true"></textarea> 
          	</td>
          </tr>
          </table>
       </form>
		</div>
	 <div id="dlg_view-buttons">
	    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg_view').dialog('close')" style="width:90px">退出</a>
	</div> 

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
			//初始化
			$.post('${ctx}/webservice/YwtAttendanceDay.do?method=init&mod_id=108010',function(result){
				$("#_sign_in_date").datebox('setValue',result._sign_in_date);
				$("#_sign_out_date").datebox('setValue',result._sign_out_date);
				$("#_is_lock").combobox('setValue',result._is_lock);
				$("#nav").text(result.local_str);
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
			//初次进来需要初始化查询数据
			$('#table').datagrid('load',{
				_sign_in_date: $("#_sign_in_date").datebox('getValue'),
				_sign_out_date: $("#_sign_out_date").datebox('getValue'),
				_real_name_like: $("#_real_name_like").val(),
				_is_lock: $("#_is_lock").combobox('getValue')
	   		});
			//查询按钮绑定事件 
			$("#search").bind('click',function(){
				$('#table').datagrid('load',{
					_sign_in_date: $("#_sign_in_date").datebox('getValue'),
					_sign_out_date: $("#_sign_out_date").datebox('getValue'),
					_real_name_like: $("#_real_name_like").val(),
					_is_lock: $("#_is_lock").combobox('getValue')
		   		});
			});

			$("#exprot").bind('click',function(){
				$.messager.confirm('温馨提示', '是否导出数据？', function(r){
					if (r){
						var str = '&excel_all=1&_real_name_like='+$("#_real_name_like").val()+'&_sign_in_date='+$("#_sign_in_date").datebox('getValue')+
								  '&_sign_out_date='+$("#_sign_out_date").datebox('getValue')+'_is_lock='+$("#_is_lock").combobox('getValue');
						$("#fm").attr("action", "${ctx}/webservice/YwtAttendanceDay.do?method=list"+str);
			    		$("#fm").submit();
					}  
				});

			});
		});
		});
		//开始休息时间
		function get_sign_in_date(value,rec){
            return rec.map.sign_in_date;
		}
		//结束休息时间
		function get_sign_out_date(value,rec){
			return rec.map.sign_out_date;     
		}
		//添加人
		function get_user_name(value,rec){
			return rec.map.add_user_name;     
		}
		//添加时间
		function get_add_date(value,rec){
			return rec.map.add_date;
		}
		//是否启用
		function get_is_lock(value){
			if(value==0){
			  return "启用";
			}else{
              return "停用";
			}
		}
		
		//新建计划
		var url;
	    function newAttendanceDay(){
	    	 $.post('${ctx}/webservice/YwtAttendanceDay.do?method=add',function(result){
		    	$('#addOrEdit #add_user_id_name').combogrid({
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

		        $('#dlg').dialog('open').dialog('setTitle','新增休息时间'); 
		        $('#addOrEdit').form('clear');   
	         });
	     url = '${ctx}/webservice/YwtAttendanceDay.do?method=save';
	    }
	    //保存计划
	    function saveYwtAttendanceDay(){
	        $('#addOrEdit').form('submit',{
	            url: url,
	            onSubmit: function(){
	        	   var plandesc=$('#addOrEdit #memo').val();
	        	   if (plandesc.length>500) {
					alert("拜访说明不能多于500字");
					return false;
				   }
					var add_user_id_name =  $('#addOrEdit #add_user_id_name').combobox("getValues");
					if(add_user_id_name == ''||add_user_id_name == null){
						alert("至少选择一个用户！");
						return false;
					}
					return $(this).form('validate'); 
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
	                $('#dlg').dialog('close');        // close the dialog
	                $('#table').datagrid('load',{
	    				_sign_in_date: $("#_sign_in_date").datebox('getValue'),
	    				_sign_out_date: $("#_sign_out_date").datebox('getValue'),
	    				_real_name_like: $("#_real_name_like").val(),
	    				_is_lock: $("#_is_lock").combobox('getValue')
	    	   		});
	            }
	        });
	    }
	    //修改计划
	 	function editAttendanceDay(){
		 	//编辑时只能选择一行记录
	 	   var flag = false;
	       var row = $('#table').datagrid('getSelections');
	       if(row.length != 1){
        		alert("请您选择一行数据!"); 
        		return false;  
	        }
	 		$.post('${ctx}/webservice/YwtAttendanceDay.do?method=edit&id='+row[0].id,function(result){
		         $("#addOrEdit #id").val(result.entity.id);     
		         $('#addOrEdit #add_user_id_name').combogrid({
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
		    	if(result.user_id_name_arry){
		    		$('#addOrEdit #add_user_id_name').combogrid('setValues',result.user_id_name_arry);
		    	}
		    	$("#addOrEdit #sign_in_date").datebox('setValue',result.sign_in_date);
		    	$("#addOrEdit #sign_out_date").datebox('setValue',result.sign_out_date);
		    	$("#addOrEdit #memo").val(result.entity.memo);
		    	
		        $('#dlg').dialog('open').dialog('setTitle','修改休息时间'); 
	         });
	     url = '${ctx}/webservice/YwtAttendanceDay.do?method=save';
		}
		function delAttendanceDay(){
			//编辑时只能选择一行记录
	 		var flag = false;
	          var row = $('#table').datagrid('getSelections');
	          if(row.length < 1){  
	        	  alert("最少选择一行数据!"); 
		           	return false;  
	          }else{
	        	  $.messager.confirm('温馨提示', '是否删除选中数据？', function(r){
						if (r){
							 for (var i = 0; i < row.length; i++) {
					        	  $.post('${ctx}/webservice/YwtAttendanceDay.do?method=delete&id='+row[i].id,function(result){});
					          }
							 $('#table').datagrid('load',{
									_sign_in_date: $("#_sign_in_date").datebox('getValue'),
									_sign_out_date: $("#_sign_out_date").datebox('getValue'),
									_real_name_like: $("#_real_name_like").val(),
									_is_lock: $("#_is_lock").combobox('getValue')
						   	  });
						}  
					});
	          }
		}
		function viewAttendanceDay(){
			//查看时只能选择一行记录
		 	   var flag = false;
		       var row = $('#table').datagrid('getSelections');
		       if(row.length != 1){
	        		alert("请您选择一行数据!"); 
	        		return false;  
		        }
		 		$.post('${ctx}/webservice/YwtAttendanceDay.do?method=view&id='+row[0].id,function(result){
			         $("#view #id").text(result.add_date);     
			         $('#view #add_user_id_name').combogrid({
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
			    	if(result.user_id_name_arry){
			    		$('#view #add_user_id_name').combogrid('setValues',result.user_id_name_arry);
			    	}
			    	$("#view #sign_in_date").datebox('setValue',result.sign_in_date);
			    	$("#view #sign_out_date").datebox('setValue',result.sign_out_date);
			    	$("#view #memo").val(result.entity.memo);
			    	
			        $('#dlg_view').dialog('open').dialog('setTitle','查看休息时间'); 
		         });
		}
	
	</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
