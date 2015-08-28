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
 <td>
 <form id="table" action="${ctx}/manager/admin/JcfxKczzKhfz.do?method=save" method="post" >
       <table>
        <tr>
        <td>分组名称</td>
            <td> 
            <input type="hidden" id="_khfz_id" name="_khfz_id" value="${_khfz_id}"/>
            <input type="text" id="title" name="title" />
             </td>
        </tr>
		 <tr >
		    <td align="right">快速搜索:</td>
			<td align="center" >
				R3编码：<input type="text" name="_r3_code_like" id="_r3_code_like" size="24" maxlength="30" style="width:100px;" />
				客户名称：<input type="text" name="_customer_name_like" id="_customer_name_like" size="24" maxlength="30" style="width:100px;" />
			</td>
			<td align="left" ><input type="button" name="search" id="search" size="24" value="搜索" /></td>
		 </tr>
		 <tr>
		 <td align="center">待选列表</td><td></td><td align="center">已选列表</td>
		 </tr>
		 <tr>
		   <td rowspan="4"> 
		   <select name="select1" multiple="multiple" style="width:300px;height:350px;" id="select1">
            </select>
		   </td>
	      <td align="center">
		    <div><input type="button" name="addUser" id="addUser" size="24" value="添加》》" /></div>
			<div><input type="button" name="deleteUser" id="deleteUser" size="24" value="《《删除" /></div>
			<div><input type="button" name="allAddUser" id="allAddUser" size="24" value="全部添加》》" /></div>
			<div><input type="button" name="allDeleteUser" id="allDeleteUser" size="24" value="《《全部删除" /></div>
		  </td>
		  <td rowspan="4">
		     <select name="select2" multiple="multiple" style="width:300px;height:350px;" id="select2">
             </select>
		  </td>
		 </tr>
	  </table>
	</form>
 </td>
 
 </tr>
 <tr>
 <td align="center">  <input type="button" name="btn_submit" id="btn_submit" size="24" value="提交" /> 
 <input type="button" name="back" id="back" size="24" value="取消" /></td>
 </tr>
 </table>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var select1=$("#select1");//待选
	var select2=$("#select2");//已选
	var search=$("#search");//搜索
	var addUser=$("#addUser");//添加用户
	var deleteUser=$("#deleteUser");//删除用户
	var allAddUser=$("#allAddUser");//添加所有用户
	var allDeleteUser=$("#allDeleteUser");//删除所有用户
	var btn_submit=$("#btn_submit");//保存选择用户
	var back=$("#back");//返回上一页
	
	 $.ajax({
  	   type: "POST",
  	   url: "${ctx}/manager/admin/JcfxKczzKhfz.do",
  	   data: "method=noChooseMapList"+"&_khfz_id="+$("#_khfz_id").val(),
  	   success: function(result){
  		  $("#title").val(result.title);
 	    //初始化待选人信息
	 	   var noChooses=result.noChooseMapList;
	 	   var optionsnoChooses='';
		     $.each(noChooses,function(i, n){
		 	   var noChoose=noChooses[i];
		 	   optionsnoChooses+='<option value="'+noChoose.id+"#"+noChoose.cust_id+'#'+noChoose.r3_code+'#'+noChoose.coustmer_name+'">'+'['+noChoose.r3_code+']'+noChoose.coustmer_name+'</option>';
			 });
	       select1.append(optionsnoChooses);
  	   },
  	   error:function(XMLHttpRequest, textStatus, errorThrown) {
  		    alert("加载异常");
  		}
  	});
	  		 	
	 //查找已选用户列表
	 if($("#_khfz_id").val()!=""){
		 $.ajax({
		  	   type: "POST",
		  	   url: "${ctx}/manager/admin/JcfxKczzKhfz.do",
		  	   data: "method=chooseMapList"+"&_khfz_id="+$("#_khfz_id").val(),
		  	   success: function(result){
		  	  	 //初始化部门信息
		  	     var chooses=result.chooseMapList;
		  	     if(chooses.length>0){
		  	     var optionschooses='';
		 	     $.each(chooses,function(i, n){
		 	 	   var choose=chooses[i];
		 	 	   optionschooses+='<option value="'+choose.khfz_id+"#"+choose.cust_id+'#'+choose.r3_code+'#'+choose.coustmer_name+'">'+'['+choose.r3_code+']'+choose.coustmer_name+'</option>';
		 		 });
		 	       select2.append(optionschooses);
		  	     }
		  	   },
		  	   error:function(XMLHttpRequest, textStatus, errorThrown) {
		  		    alert("加载异常");
		  		}
		});
	 }
	 
	search.click(function(){
	  	var parem = "method=noChooseMapList";
	  	var _khfz_id=$("#_khfz_id").val();
		var _r3_code_like=$("#_r3_code_like").val();
		var _customer_name_like=$("#_customer_name_like").val();

		if(_r3_code_like){parem+="&_r3_code_like="+_r3_code_like;}
		if(_customer_name_like){parem+="&_customer_name_like="+_customer_name_like;}
		if(_khfz_id){parem+="_khfz_id="+_khfz_id;}else{alert("分組！");return false;}
		 $.ajax({
		  	   type: "POST",
		  	   url: "${ctx}/manager/admin/JcfxKczzKhfz.do",
		  	   data: parem,
		  	   success: function(result){
		  	   select1.empty();
		 	 //初始化待选人信息
		 	   var noChooses=result.noChooseMapList;
		 	   var optionsnoChooses='';
			     $.each(noChooses,function(i, n){
			 	   var noChoose=noChooses[i];
			 	   optionsnoChooses+='<option value="'+noChoose.cust_id+'#'+noChoose.r3_code+'#'+noChoose.coustmer_name+'">'+'['+noChoose.r3_code+']'+noChoose.coustmer_name+'</option>';
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
	
	//提交选择分配客户
	btn_submit.click(function(){
		//if (Validator.Validate(this.form, 1)){
		    var title=$("#title").val();
		    if(title!=''){
			    allSelect(select2);//全部选中待选的
				$("#table").submit();
				window.close();
		    }else{
               alert("分组名不能为空!");
               return false;
			}
		//}
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
