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
.datagrid-cell.datagrid-cell-c1-add_date {
  width: 200px;
}
.datagrid-wrap.panel-body {
  text-align: center;
}
div#toolbar {
  text-align: left;
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
input,textarea,select{font-family:Microsoft Yahei;font-size:12px; width:100px;}
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
	</form>
	</div>
	<div  id="tb" style="height: auto; padding-right: 10px;display: none;" align="right">
		<a href="#" class="easyui-linkbutton" id="search" iconCls="icon-search">查询</a>&nbsp; 
		<a href="#"  class="easyui-linkbutton" id="exprot" iconCls="icon-redo">导 出</a>
	</div>

<div style="margin-left: 5px; margin-right: 5px; margin-top: 5px;">
<table id="table" class="easyui-datagrid" title="签到时间表"  
        data-options="url:'${ctx}/webservice/YwtAttendanceSignTime.do?method=list',fitColumns:true,singleSelect:true"
        rownumbers="true" idField="id" toolbar="#toolbar" singleSelect="false" pagination="true"  loadMsg="正在拼命加载中，请稍后..."  fitColumns="true" >   
    <thead>   
    <tr style="bgcolor:orange;" >
        <th field="ck" checkbox="true"></th>  
	    <th width="80px;"  field="am_sign_in_date"  formatter="get_am_sign_in_date">上午上班时间</th>
	    <th width="80px;"  field="am_sign_out_date" formatter="get_am_sign_out_date">上午下班时间</th>
	    <th width="80px;"  field="pm_sign_in_date"  formatter="get_pm_sign_in_date">下午上班时间</th>
	    <th width="80px;"  field="pm_sign_out_date" formatter="get_pm_sign_out_date">下午下班时间</th>
	    <th width="80px;"  field="am_center_pm" formatter="get_am_center_pm">午休分界点</th>
	    <th width="80px;"  field="add_date" formatter="get_add_date">添加时间</th>
	    <th width="80px;"  field="dept_name">总部/分公司</th>
	    <th width="150px;"  field="id" formatter="get_id_update"><b>操作</b></th>
    </tr>
   </thead>
         
</table>  
</div>
<div id="toolbar" style="margin-left:5px;margin-top:5px;border-top-style: solid;border-top-width: 1px; border-top-color:#C9CFCD "> 
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newYwtAttendanceSignTime()">新增</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editYwtAttendanceSignTime()">修改</a>
</div>    

<!-- 新增和修改页面dialog -->
	<div id="dlg" class="easyui-dialog" style="width:700px;height:250px;padding:10px 20px"  closed="true" buttons="#dlg-buttons">  
      <form id="addOrEdit" method="post" novalidate="false" enctype="multipart/form-data"> 
          <input type="hidden" name="id" id="id"></input> 
		  <input type="hidden" name="mod_id" id="mod_id"></input> 
          <table width="100%" border="0" align="right" cellspacing="0" cellpadding="0" class="rtable3"> 
          <tr>
          	<td>
			<label for="am_sign_in_date"><strong class="fb">上午上班时间: </strong></label>
			<input class="ss" id="am_sign_in_date" name="am_sign_in_date" required="true" style="width:100px;"> 
		     </td>
		     <td>
		     <label for="am_sign_out_date"><strong class="fb">上午下班时间：</strong></label>
		     <input class="ss" id="am_sign_out_date" name="am_sign_out_date" required="true" style="width:100px;">
<!--		     <input id="am_sign_out_date" name="am_sign_out_date" class="easyui-datetimebox"  style="width:100px"/>-->
			</td>
		 </tr>
          <tr>
          	<td>
			<label for="pm_sign_in_date"><strong class="fb">下午上班时间: </strong></label>
			<input class="ss" id="pm_sign_in_date" name="pm_sign_in_date" required="true" style="width:100px;">
<!--			<input id="pm_sign_in_date" name="pm_sign_in_date" class="easyui-datetimebox" style="width:100px;" />-->
		     </td>
		      <td>
		     <label for="pm_sign_out_date"><strong class="fb">下午下班时间：</strong></label>
		     <input class="ss" id="pm_sign_out_date" name="pm_sign_out_date" required="true" style="width:100px;">
<!--		     <input id="pm_sign_out_date" name="pm_sign_out_date" class="easyui-datetimebox" style="width:100px;" />-->
			</td>
		 </tr>
          <tr>
		     <td>
		     <label for="am_center_pm"><strong class="fb">午休分界点：</strong></label>
		     <input class="ss" id="am_center_pm" name="am_center_pm" required="true" style="width:100px;">
<!--		     <input id="am_center_pm" name="am_center_pm" class="easyui-datetimebox" style="width:100px;" />-->
			</td>
<!-- 			<td> -->
<!-- 		     <label for="add_date"><strong class="fb">添加时间：</strong></label> -->
<!-- 		     <input class="ss" id="add_date" id="add_date" required="true" style="width:80px;"> -->
<!-- 		     <input id="add_date" name="add_date" class="easyui-datetimebox" style="width:100px;" /> -->
<!-- 			</td> -->
          </tr>
           <tr>
		     <td>
		    <div id="hidden">  <label for="dept_name"><strong class="fb">总部/分公司：</strong></label>
		     <input id="dept_name" class="easyui-combobox" name="dept_name" style="width:100px;" /></div>
			</td>
			<td>
			</td>
          </tr>
          </table>
       </form>
		</div>
	 <div id="dlg-buttons">
	    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveYwtAttendanceSignTime()" style="width:90px">保存</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">退出</a>
	</div> 
<!--查看-->
<div id="dlg_view" class="easyui-dialog" style="width:700px;height:250px;padding:10px 20px"  closed="true" buttons="#dlg_view-buttons">  
      <form id="view" method="post" novalidate="false" enctype="multipart/form-data"> 
          <input type="hidden" name="id" id="id"></input> 
		  <input type="hidden" name="mod_id" id="mod_id"></input> 
         <table width="100%" border="0" align="right" cellspacing="0" cellpadding="0" class="rtable3"> 
          <tr>
          	<td>
			<label for="am_sign_in_date"><strong class="fb">上午上班时间: </strong></label>
			<input class="ss" id="am_sign_in_date" name="am_sign_in_date" required="true" style="width:80px;">
		     </td>
		     <td>
		     <label for="am_sign_out_date"><strong class="fb">上午下班时间：</strong></label>
		     <input class="ss" id="am_sign_out_date" name="am_sign_out_date" required="true" style="width:80px;">
			</td>
		 </tr>
          <tr>
          	<td>
			<label for="pm_sign_in_date"><strong class="fb">下午上班时间: </strong></label>
			<input class="ss" id="pm_sign_in_date" name="pm_sign_in_date" required="true" style="width:80px;">
		     </td>
		      <td>
		     <label for="pm_sign_out_date"><strong class="fb">下午下班时间：</strong></label>
		     <input class="ss" id="pm_sign_out_date" name="pm_sign_out_date" required="true" style="width:80px;">
			</td>
		 </tr>
           <tr>
		     <td>
		     <label for="am_center_pm"><strong class="fb">午休分界点：</strong></label>
		     <input class="ss" id="am_center_pm" name="am_center_pm" required="true" style="width:80px;">
			</td>
          </tr>
           <tr>
		     <td>
		      <div id="hidden"><label for="dept_name"><strong class="fb">总部/分公司：</strong></label>
		    <input class="easyui-combobox" id="dept_name" name="dept_name" ></input></div>
			</td>
			<td>
			</td>
          </tr>
          </table>
       </form>
		</div>
	 <div id="dlg_view-buttons">
	    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg_view').dialog('close')" style="width:90px">退出</a>
	</div> 
	<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/commons/scripts/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/commons/scripts/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${ctx}/commons/scripts/public.js"></script>
	<script type="text/javascript">
		$(function(){
			//初始化
			
			$.post('${ctx}/webservice/YwtAttendanceSignTime.do?method=init&mod_id=108010',function(result){
				//$("#_sign_in_date").datetimebox('setValue',result._sign_in_date);
				//$("#_sign_out_date").datetimebox('setValue',result._sign_out_date);
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
				//_sign_in_date: $("#_sign_in_date").datetimebox('getValue'),
				//_sign_out_date: $("#_sign_out_date").datetimebox('getValue'),
	   		});
			//查询按钮绑定事件 
			$("#search").bind('click',function(){
				$('#table').datagrid('load',{
					//_sign_in_date: $("#_sign_in_date").datetimebox('getValue'),
					//_sign_out_date: $("#_sign_out_date").datetimebox('getValue'),
		   		});
			});
		    
			$("#exprot").bind('click',function(){
				$.messager.confirm('温馨提示', '是否导出数据？', function(r){
					if (r){
						var str = '&excel_all=1';
						$("#fm").attr("action", "${ctx}/webservice/YwtAttendanceSignTime.do?method=list"+str);
			    		$("#fm").submit();
					}  
				});

			});
		});
		});
		//上午上班时间
		function get_am_sign_in_date(value,rec){
            return rec.map.am_sign_in_date;
		}
		//上午下班时间
		function get_am_sign_out_date(value,rec){
			return rec.map.am_sign_out_date;     
		}
		//下午上班时间
		function get_pm_sign_in_date(value,rec){
			return rec.map.pm_sign_in_date;     
		}
		//下午下班时间
		function get_pm_sign_out_date(value,rec){
			return rec.map.pm_sign_out_date;
		}
		//上下午分界点
		function get_am_center_pm(value,rec){
			return rec.map.am_center_pm;
		}
		//添加时间
		function get_add_date(value,rec){
			return rec.map.add_date;
		}
		
		function get_id_update(value,rec){
			var cz='';
			    cz+=' <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editTask('+rec.id+')">删除</a>';
			    return cz;
		}

		//添加时间
		var url;
	    function newYwtAttendanceSignTime(){
	    	$("#hidden").attr("hidden",false);
	    	 $.post('${ctx}/webservice/YwtAttendanceSignTime.do?method=add',function(result){
                if(result.dept_id !=0 && result.total==1){
                     alert("不可设置多个考勤时间！");
                     return false;
                    }
		        $('#dlg').dialog('open').dialog('setTitle','新增签到时间'); 
		        $('#addOrEdit').form('clear');   
		        $('.ss').timespinner({ 
			    	   showSeconds:true 
			    	   });
		        $('#addOrEdit #am_sign_in_date').timespinner("setValue","09:00:00");
		 		$('#addOrEdit #am_sign_out_date').timespinner("setValue","12:00:00");
		 		$('#addOrEdit #pm_sign_in_date').timespinner("setValue","14:00:00");
		 		$('#addOrEdit #pm_sign_out_date').timespinner("setValue","18:00:00");
		 		$('#addOrEdit #am_center_pm').timespinner("setValue","13:00:00");
		 		
		 		
		 		$("#addOrEdit #dept_name").combogrid({
		    	    panelWidth: 110,
		    	    idField: 'dept_id',
		 	 	    textField: 'dept_name',
		  		    data:result.deptList,
		    	    columns: [[
		    	     {field:'dept_name',title:'请选择',width:120}
		    	    ]],
		    	    editable:false,
		    	    fitColumns: true
		    	 });
		 		 $("#addOrEdit #dept_name").combobox('setValue',"请选择");
	         });
	    		
	     url = '${ctx}/webservice/YwtAttendanceSignTime.do?method=save';
	    }
	    //保存计划
	    function saveYwtAttendanceSignTime(){
	    	$.post('${ctx}/webservice/YwtAttendanceSignTime.do?method=save&'+$('#addOrEdit').serialize(),function(result){
	    	/*	if (null!=result.res&&result.res!=''){
                    $.messager.show({
                        title: '操作提示！',
                        msg: result.msg
                    });
                } else {
                	
                	$.messager.show({
                        title: '操作提示！ ',
                        msg: result.msg
                    });
               }*/
               $.messager.show({
                   title: '操作提示！ ',
                   msg: result.msg
               });
                $('#dlg').dialog('close');        // close the dialog
                $('#table').datagrid('load',{
    	   		});
	    	},'json');
	    }
	    //修改计划
	 	function editYwtAttendanceSignTime(){
		 	//编辑时只能选择一行记录
		 	$("#hidden").attr("hidden","hidden");
	 	   var flag = false;
	       var row = $('#table').datagrid('getSelections');
	       if(row.length != 1){
        		alert("请您选择一行数据!"); 
        		return false;  
	        }
	       $('.ss').timespinner({ 
	    	   showSeconds:true 
	    	   }); 
	 		$.post('${ctx}/webservice/YwtAttendanceSignTime.do?method=edit&id='+row[0].id,function(result){
		 		if(result.error=="2"){
		 			alert(result.res); 
	        		return false;  
			 	}
		 		$('#addOrEdit #am_sign_in_date').timespinner("setValue",result.am_sign_in_date.substr(result.am_sign_in_date.indexOf(" ")));
		 		$('#addOrEdit #am_sign_out_date').timespinner("setValue",result.am_sign_out_date.substr(result.am_sign_out_date.indexOf(" ")));
		 		$('#addOrEdit #pm_sign_in_date').timespinner("setValue",result.pm_sign_in_date.substr(result.pm_sign_in_date.indexOf(" ")));
		 		$('#addOrEdit #pm_sign_out_date').timespinner("setValue",result.pm_sign_out_date.substr(result.pm_sign_out_date.indexOf(" ")));
		 		$('#addOrEdit #am_center_pm').timespinner("setValue",result.am_center_pm.substr(result.am_center_pm.indexOf(" ")));
		 		$("#addOrEdit #add_date").datetimebox('setValue',result.add_date);

	 			$("#addOrEdit #id").val(result.entity.id);  
			    $("#addOrEdit #mod_id").val(result.entity.mod_id);  
			    $("#addOrEdit #dept_name").val(result.entity.dept_name);
			    
			    
		/*    	$("#addOrEdit #am_sign_in_date").datetimebox('setValue',result.am_sign_in_date);
		    	$("#addOrEdit #am_sign_out_date").datetimebox('setValue',result.am_sign_out_date);
		    	$("#addOrEdit #pm_sign_in_date").datetimebox('setValue',result.pm_sign_in_date);
		    	$("#addOrEdit #pm_sign_out_date").datetimebox('setValue',result.pm_sign_out_date);
		    	$("#addOrEdit #am_center_pm").datetimebox('setValue',result.am_center_pm);
		    	$("#addOrEdit #add_date").datetimebox('setValue',result.add_date);
		*/
		        $('#dlg').dialog('open').dialog('setTitle','修改签到时间'); 
	         });
	     url = '${ctx}/webservice/YwtAttendanceSignTime.do?method=save';
		}
		function delYwtAttendanceSignTime(){
		    
			//编辑时只能选择一行记录
	 		var flag = false;
	          var row = $('#table').datagrid('getSelections');
	          if(row.length < 1){  
	        	  alert("最少选择一行数据!"); 
		           	return false;  
	          }else{
	        	  $.messager.confirm('温馨提示', '删除签到时间将导致无法签到？', function(r){
						if (r){
							 for (var i = 0; i < row.length; i++) {
					        	  $.post('${ctx}/webservice/YwtAttendanceSignTime.do?method=delete&id='+row[i].id,function(result){});
					          }
							 $('#table').datagrid('load',{
						   	  });
						}  
					});
	          }
		}
		
		
		function editTask(id){
			//查看时只能选择一行记录
			
			 	$.post('${ctx}/webservice/YwtAttendanceSignTime.do?method=delete&id='+id,function(result){
			 		 $.messager.show({
	                        title: '操作提示！',
	                        msg: result.msg
	                    });
			 		$('#table').datagrid('load',{
				   	  });
		         }); 
		}
		
		
		function viewYwtAttendanceSignTime(){
			//查看时只能选择一行记录
		 	   var flag = false;
		       var row = $('#table').datagrid('getSelections');
		       if(row.length != 1){
	        		alert("请您选择一行数据!"); 
	        		return false;  
		        }
		 		$.post('${ctx}/webservice/YwtAttendanceSignTime.do?method=view&id='+row[0].id,function(result){
		 			$("#view #id").val(result.entity.id);  
				    $("#view #mod_id").val(result.entity.mod_id);  
				    $("#view #dept_name").val(result.entity.dept_name);
			    	$("#view #am_sign_in_date").datetimebox('setValue',result.am_sign_in_date);
			    	$("#view #am_sign_out_date").datetimebox('setValue',result.am_sign_out_date);
			    	$("#view #pm_sign_in_date").datetimebox('setValue',result.pm_sign_in_date);
			    	$("#view #pm_sign_out_date").datetimebox('setValue',result.pm_sign_out_date);
			    	$("#view #am_center_pm").datetimebox('setValue',result.am_center_pm);
			    	$("#view #add_date").datetimebox('setValue',result.add_date);
			    	
			        $('#dlg_view').dialog('open').dialog('setTitle','查看签到时间'); 
		         });
		}
	
	</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
