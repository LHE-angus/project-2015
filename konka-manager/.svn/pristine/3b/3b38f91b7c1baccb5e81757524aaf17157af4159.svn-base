<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>选择人员</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body style="background-color:#fff;">
<div align="center" style="margin-top: 20px;">
     <table>
 <tr>
 <td rowspan="2">
    <input type="hidden" name="curr_task_id" id="curr_task_id" value="${curr_task_id}" />
               部门列表：<br/>
     <select name="dept_ids" multiple="multiple" id="dept_ids" style="width:150px;height:350px;">
	 </select>
 </td>
 <td>
       <table>
		 <tr>
		    <td align="right">快速搜索:</td>
			<td align="center" ><input type="text" name="no_choose_user_like" id="no_choose_user_like" size="24" maxlength="30" style="width:100px;" /></td>
			<td align="left" ><input type="button" name="search" id="search" size="24" value="搜索" /></td>
		 </tr>
		 <tr>
		 <td align="center">待选列表</td><td></td><td align="center">已选列表</td>
		 </tr>
		 <tr>
		   <td rowspan="4"> 
		   <select name="select1" multiple="multiple" style="width:235px;height:235px;" id="select1">
            </select>
		   </td>
	      <td>
		    <div><input type="button" name="addUser" id="addUser" size="24" value="添加》》" /></div>
			<div><input type="button" name="deleteUser" id="deleteUser" size="24" value="《《删除" /></div>
			<div><input type="button" name="allAddUser" id="allAddUser" size="24" value="全部添加》》" /></div>
			<div><input type="button" name="allDeleteUser" id="allDeleteUser" size="24" value="《《全部删除" /></div>
		  </td>
		  <td rowspan="4">
		 <select name="select2" multiple="multiple" style="width:235px;height:235px;" id="select2">
                    </select>
		  </td>
		 </tr>
	  </table>
 </td>
 
 </tr>
 <tr>
 <td align="center">  <input type="button" name="btn_submit" id="btn_submit" size="24" value="提交" /> 
 <input type="button" name="back" id="back" size="24" value="取消" /></td>
 </tr>
 </table>
  
  
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var dept_ids=$("#dept_ids");//部门选项
	var select1=$("#select1");//待选
	var select2=$("#select2");//已选
	var search=$("#search");//搜索
	var addUser=$("#addUser");//添加用户
	var deleteUser=$("#deleteUser");//删除用户
	var allAddUser=$("#allAddUser");//添加所有用户
	var allDeleteUser=$("#allDeleteUser");//删除所有用户
	var btn_submit=$("#btn_submit");//删除所有用户
	var back=$("#back");//删除所有用户
	
	 $.ajax({
  	   type: "POST",
  	   url: "${ctx}/webservice/YwtTask.do",
  	   onSubmit: function(){
  		if(null==$("#curr_task_id").val()||$("#curr_task_id").val()==""){
  		return false;
  		}
  	   },
  	   data: "method=noChooseUser"+"&curr_task_id="+$("#curr_task_id").val(),
  	   success: function(result){
  	  	 //初始化部门信息
  	     var depts=result.reveDeptList;
  	     var optionsdepts='';
 	     $.each(depts,function(i, n){
 	 	   var dept=depts[i];
 	 	    optionsdepts+='<option value="'+dept.dept_id+'">'+dept.map.full_name+'</option>';
 		 });
 	    dept_ids.append(optionsdepts);
 	 //初始化待选人信息
 	   var noChooses=result.noChooseMapList;
 	   var optionsnoChooses='';
	     $.each(noChooses,function(i, n){
	 	   var noChoose=noChooses[i];
	 	   optionsnoChooses+='<option value="'+noChoose.ID+'">'+noChoose.REAL_NAME+'</option>';
		 });
	     select1.append(optionsnoChooses);
  	   },
  	   error:function(XMLHttpRequest, textStatus, errorThrown) {
  		    alert("加载异常");
  		}
  	});
	 var selectObj = window.dialogArguments;
	 if(null!=selectObj){
		 var optionschooses='';
		 $.each(selectObj, function(i, n){
		    var choose=selectObj[i];
		    var selEntity= choose.split("&");
		    optionschooses+='<option value="'+choose+'">'+selEntity[1]+'</option>';
		});
		 select2.append(optionschooses);
     }
		 	
	 //查找已选用户列表
	 /**if($("#curr_task_id").val()!=""){
		 $.ajax({
		  	   type: "POST",
		  	   url: "${ctx}/webservice/YwtTask.do",
		  	   data: "method=chooseUser"+"&curr_task_id="+$("#curr_task_id").val(),
		  	   success: function(result){
		  	  	 //初始化部门信息
		  	     var chooses=result.chooseMapList;
		  	     if(chooses.length>0){
		  	     var optionschooses='';
		 	     $.each(chooses,function(i, n){
		 	 	   var choose=chooses[i];
		 	 	   optionschooses+='<option value="'+choose.ID+'">'+choose.REAL_NAME+'</option>';
		 		 });
		 	    select2.append(optionschooses);
		  	     }
		  	   },
		  	   error:function(XMLHttpRequest, textStatus, errorThrown) {
		  		    alert("加载异常");
		  		}
		});
	 }**/
	search.click(function(){
	  	var parem = "method=noChooseUser";
		var curr_task_id=$("#curr_task_id").val();
		var user_like=   $("#no_choose_user_like").val();
		parem+="&no_choose_user_like="+user_like;
		parem+="&curr_task_id="+curr_task_id;
		var deptidstr="";
		for(var i=0;i<dept_ids.get(0).options.length;i++){
			if(dept_ids.get(0).options[i].selected){
				deptidstr+=dept_ids.get(0).options[i].value+",";
			}
		}
		parem+="&select_depts="+deptidstr;
		$.ajax({
		  	   type: "POST",
		  	   url: "${ctx}/webservice/YwtTask.do",
		  	   data: parem,
		  	   success: function(result){
		  		 select1.empty();
			 	 //初始化待选人信息
		 	    var noChooses=result.noChooseMapList;
		 	    var optionsnoChooses='';
				$.each(noChooses,function(i, n){
			 	   var noChoose=noChooses[i];
			 	   optionsnoChooses+='<option value="'+noChoose.ID+'">'+noChoose.REAL_NAME+'</option>';
				});
				select1.append(optionsnoChooses);
		  	   },
		  	   error:function(XMLHttpRequest, textStatus, errorThrown) {
		  		    alert("加载异常");
		  		}
		  	});
	});
	dept_ids.change(function () {
		var parem = "method=noChooseUser";
		var curr_task_id=$("#curr_task_id").val();
		var user_like=   $("#no_choose_user_like").val();
		parem+="&no_choose_user_like="+user_like;
		parem+="&curr_task_id="+curr_task_id;
		var deptidstr="";
		for(var i=0;i<dept_ids.get(0).options.length;i++){
			if(dept_ids.get(0).options[i].selected){
				deptidstr+=dept_ids.get(0).options[i].value+",";
			}
		}
		parem+="&select_depts="+deptidstr;
		$.ajax({
		  	   type: "POST",
		  	   url: "${ctx}/webservice/YwtTask.do",
		  	   data: parem,
		  	   success: function(result){
		  		 select1.empty();
			 	 //初始化待选人信息
		 	    var noChooses=result.noChooseMapList;
		 	    var optionsnoChooses='';
				$.each(noChooses,function(i, n){
			 	   var noChoose=noChooses[i];
			 	   optionsnoChooses+='<option value="'+noChoose.ID+'">'+noChoose.REAL_NAME+'</option>';
				});
				select1.append(optionsnoChooses);
		  	   },
		  	   error:function(XMLHttpRequest, textStatus, errorThrown) {
		  		    alert("加载异常");
		  		}
		  	});
	});
	select1.dblclick(function () {
		moveSelected(select1,select2);
	 });
	select2.dblclick(function () { 
		 moveSelected(select2,select1);
	 });
	addUser.click(function(){
	      moveSelected(select1,select2);
	});
	deleteUser.click(function(){
	      moveSelected(select2,select1);
	});
	allAddUser.click(function(){
	      allSelect(select1);//全部选中待选的
	      moveSelected(select1,select2);  
	});
	allDeleteUser.click(function(){
	      allSelect(select2);//全部选中已选的
	      moveSelected(select2,select1);    
	});	
	//提交选择下发认为人
	btn_submit.click(function(){
		var selectedUsersId = [];
		if(select2.get(0).options.length>0){
		for(var i=0;i<select2.get(0).options.length;i++){
			selectedUsersId[i]=select2.get(0).options[i].value;
		}
		window.returnValue = {
				new_select_user : selectedUsersId
		};
		window.close();
		}
	});
	
	back.click(function(){
		window.close();
	});
	
});

function moveSelected(sourceSelect, targetSelect, isDelete){
	var cachOptionsArray = new Array();
	var index = 0;
	for (var i = sourceSelect.get(0).options.length - 1; i >= 0; i--){
		if (sourceSelect.get(0).options[i].selected){
			cachOptionsArray[index] = new Option(sourceSelect.get(0).options[i].text, sourceSelect.get(0).options[i].value);
			if(isDelete==undefined || isDelete==true){
				sourceSelect.get(0).options[i] = null;
			}
			index++;
		}
	}
	var exist = false;
	for (var i = cachOptionsArray.length - 1; i >= 0; i--){
		exist = false;
		for (var j = 0; j < targetSelect.get(0).options.length; j++){
			if (targetSelect.get(0).options[j].value.toString() == cachOptionsArray[i].value.toString()){
				exist = true; 
				break;
			}
		}
		if (!exist){
			targetSelect.get(0).options[targetSelect.get(0).options.length] = cachOptionsArray[i];
		}
	}
}
//全部选中下拉列表
function allSelect(allUserSelect){
	for (var i =0;i< allUserSelect.get(0).options.length;i++){
		allUserSelect.get(0).options[i].selected=true;
	}
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
