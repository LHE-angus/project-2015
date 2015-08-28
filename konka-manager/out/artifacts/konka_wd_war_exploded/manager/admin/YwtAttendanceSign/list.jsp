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
tr td{
 text-align:center;
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
		<td>&nbsp;当前位置：<span>客户管理 > 业务通 > 考勤管理 > 签到管理</span></td>

	</tr>
</table>
</div>
<div style="padding: 5px">
<form id="fm" method="post">
<table width="100%" border="0" cellspacing="5" cellpadding="0"
	class="rtable1">
	<tr>
	 <td align="left"><label for="_fgs_dept_id"><strong class="fb">分 公 司：</strong></label>
	    <input id="_fgs_dept_id" name="_fgs_dept_id" class="easyui-combobox" style="width:150px"/>			   		
	</td>
	<td align="left"><label for="_sign_in_date"><strong class="fb"> 开始时间： </strong></label>
		<input id="_sign_in_date" class="easyui-datebox" style="width:100px;" />
	</td>	
	
	<td align="left"><label for="_sign_out_date"><strong class="fb"> 结束时间： </strong></label>
		<input id="_sign_out_date" class="easyui-datebox" style="width:100px;" />
	</td>
	<td align="left"><label for="_real_name_like"><strong class="fb">签到人： </strong></label>
		<input id="_real_name_like" name="_real_name_like"
			style="width: 100px" />
	</td>
	<td align="left"><label for="isSignInState"><strong class="fb">签到状态：</strong></label>
	     <select id="isSignInState" class="easyui-combobox" name="isSignInState" style="width:150px;" editable="false" data-options="required:true">
      		   <option value="-1">--请选择--</option>
      		   <option value="1">正常</option>
      		   <option value="2">迟到</option>
    	</select>
	</td>
	<td align="left"><label for="isSignOutState"><strong class="fb">签退状态：</strong></label>
	     <select id="isSignOutState" class="easyui-combobox" name="isSignOutState" style="width:150px;" editable="false" data-options="required:true">
      		   <option value="-1">--请选择--</option>
      		   <option value="1">正常</option>
      		   <option value="3">早退</option>
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

<div style="margin-left: 5px; margin-right: 5px; margin-top: 5px;height: 420px">
<table id="table" class="easyui-datagrid" title=""  
        data-options="url:'${ctx}/webservice/YwtAttendanceSign.do?method=list'"
        rownumbers="true" idField="id" toolbar="#toolbar" singleSelect="true" pagination="true"  loadMsg="正在拼命加载中，请稍后..."  fitColumns="false" >   
    <thead>
    <tr style="bgcolor:orange;" >
        <th width="65px;" rowspan="2" field="ALL_DATE" ><b>签到日期</b></th>
	    <th width="60px;" rowspan="2" field="FGS_NAME" ><b>分公司</b></th>
	    <th width="80px;" rowspan="2" field="JB_NAME" ><b>经办</b></th>
	    <th width="60px;" rowspan="2" field="REAL_NAME" ><b>签到人</b></th>
	    <th width="30px;" rowspan="2" field="sign4" formatter="get_sign4"><b>状态</b></th>
	     <th width="80px;"  colspan="2" ><b>上午签到签退情况</b></th>
	    <th width="80px;"  colspan="2" ><b>下午签到签退情况</b></th>
	    <th width="80px;"  colspan="8" ><b>上午签到签退情况</b></th>
	    <th width="80px;"  colspan="8" ><b>下午签到签退情况</b></th>
    </tr>
    <tr class="titleTR">
      <th width="60px;"  field="AM_SIGN_IN" formatter="get_AM_SIGN_IN"><b>签到</b></th>
      <th width="60px;"  field="AM_SIGN_OUT" formatter="get_AM_SIGN_OUT"><b>签退</b></th>
      <th width="60px;"  field="PM_SIGN_IN" formatter="get_PM_SIGN_IN"><b>签到</b></th>
      <th width="60px;"  field="PM_SIGN_OUT" formatter="get_PM_SIGN_OUT"><b>签退</b></th>
	   
	    <th width="300px;"  field="AM_ADDR_IN" align="left"><b>签到地址</b></th>
	    <th width="90px;"  field="AM_IP_IN" ><b>签到IP</b></th>
	    <th width="80px;"  field="AM_PHONE_MODEL_IN" formatter="get_AM_PHONE_MODEL_IN"><b>签到手机型号</b></th>
	    <th width="100px;" align="left" field="AM_IMEI_IN" formatter="get_AM_IMEI_IN"><b>签到手机IMEI</b></th>
	    
	    
	    <th width="300px;" align="left" field="AM_ADDR_OUT" ><b>签退地址</b></th>
	    <th width="90px;"  field="AM_IP_OUT" ><b>签退IP</b></th>
	    <th width="80px;"  field="AM_PHONE_MODEL_OUT" formatter="get_AM_PHONE_MODEL_OUT"><b>签到手机型号</b></th>
	    <th width="100px;" align="left" field="AM_IMEI_OUT" formatter="get_AM_IMEI_OUT"><b>签退手机IMEI</b></th>
	    
	   
	    <th width="300px;" align="left" field="PM_ADDR_IN" ><b>签到地址</b></th>
	    <th width="90px;"  field="PM_IP_IN" ><b>签到IP</b></th>
	    <th width="80px;"  field="PM_PHONE_MODEL_IN" formatter="get_PM_PHONE_MODEL_IN"><b>签到手机型号</b></th>
	    <th width="100px;"  align="left" field="PM_IMEI_IN" formatter="get_PM_IMEI_IN"><b>签到手机IMEI</b></th>
	    
	   
	    <th width="300px;" align="left" field="PM_ADDR_OUT" ><b>签退地址</b></th>
	    <th width="90px;"  field="PM_IP_OUT" ><b>签退IP</b></th>
	    <th width="80px;"  field="PM_PHONE_MODEL_OUT" formatter="get_PM_PHONE_MODEL_OUT"><b>签退手机型号</b></th>
	    <th width="100px;" align="left" field="PM_IMEI_OUT" formatter="get_PM_IMEI_OUT"><b>签退手机IMEI</b></th>
    </tr>
   </thead>      
</table> 					


</div>
<div id="toolbar" style="margin-left:5px;margin-top:5px;border-top-style: solid;border-top-width: 1px; border-top-color:#C9CFCD "> 
<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="newYwtAttendanceSign()">签到</a>
<label id="fgsSignTime" style="color: red;"></label>
</div>    

<div id="dlg_sign" class="easyui-dialog" style="width:300px;height:500px;padding:10px 20px"  closed="true" buttons="#dlg_sign-buttons">  
     <label id="fgsSignTime1" style="color: red;"></label>
      <table id="sign_table" width="100%" border="0" align="center" cellspacing="0" cellpadding="0" class="rtable3"> 
          <tr>
	       <td>
	            <button  id="sign" style="width:220px;height:60px;margin:5px" iconCls="icon-edit" plain="true" ></button>
	       </td> 
	      </tr>
	      <tr>
	        <td>
	        <button id="location" style="width:220px;height:60px;margin:5px" iconCls="icon-edit" plain="true"><h1>定位</h1></button>
	        </td>
	      </tr>
       </table>
		</div>
		 <div id="dlg_sign-buttons">
	    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:back();" style="width:90px">退出</a>
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
			$.post('${ctx}/webservice/YwtAttendanceSign.do?method=init&mod_id=108028',function(result){
				$("#_sign_in_date").datebox('setValue',result._sign_in_date);
				$("#_sign_out_date").datebox('setValue',result._sign_out_date);
				$("#fgsSignTime").text(result.fgsSignTimeStr);//分公司考勤时间
				$("#fgsSignTime1").text(result.fgsSignTimeStr);//分公司考勤时间
				//$("#table").datagrid('setTitle',result.fgsSignTimeStr);
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
                //分公司
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
				_fgs_dept_id: $("#_fgs_dept_id").combobox('getValue'),
				_sign_in_date: $("#_sign_in_date").datebox('getValue'),
				_sign_out_date: $("#_sign_out_date").datebox('getValue'),
				_real_name_like: $("#_real_name_like").val(),
				isSignInState: $("#isSignInState").combobox('getValue'),
				isSignOutState: $("#isSignOutState").combobox('getValue')
	   		});
			//查询按钮绑定事件 
			$("#search").bind('click',function(){
				$('#table').datagrid('load',{
					_fgs_dept_id: $("#_fgs_dept_id").combobox('getValue'),
					_sign_in_date: $("#_sign_in_date").datebox('getValue'),
					_sign_out_date: $("#_sign_out_date").datebox('getValue'),
					_real_name_like: $("#_real_name_like").val(),
					isSignInState: $("#isSignInState").combobox('getValue'),
					isSignOutState: $("#isSignOutState").combobox('getValue')
		   		});
			});
			
			$("#exprot").bind('click',function(){
				$.messager.confirm('温馨提示', '是否导出数据？', function(r){
					if (r){
						var str = '&excel_all=1&_real_name_like='+$("#_real_name_like").val()+'&_sign_in_date='+$("#_sign_in_date").datebox('getValue')+
								  '&_sign_out_date='+$("#_sign_out_date").datebox('getValue')+'&isSignInState='+$("#isSignInState").combobox('getValue')+"&isSignOutState="+$("#isSignOutState").combobox('getValue');
						$("#fm").attr("action", "${ctx}/webservice/YwtAttendanceSign.do?method=list"+str);
			    		$("#fm").submit();
					}  
				});
			});
		});
		});
		//var i=0;
		function get_sign4(value,rec){
			var rec_am_in=rec.AM_SIGN_STATE_IN;
			var rec_am_out=rec.AM_SIGN_STATE_OUT;
			var rec_pm_in=rec.PM_SIGN_STATE_IN;
			var rec_pm_out=rec.PM_SIGN_STATE_OUT;
			if (rec_am_in==1 && rec_pm_in==1 && rec_pm_out==1 && rec_am_out==1){
					return "正常";
			}else{
				return "<lable style='color: red;'><b>异常</b></lable>";
			}
			
        }

        
	function get_AM_SIGN_IN(value,rec) {
			if(rec.AM_SIGN_STATE_IN=="1"){
				return value==null?'':value;
			}else{
				return "<lable style='color: red;'><b>"+(value==null?'':value)+"</b></lable>";
			}
		}
		function get_AM_SIGN_OUT(value,rec) {
			if(rec.AM_SIGN_STATE_OUT=="1"){
				return value==null?'':value;
			}else{
				return "<lable style='color: red;'><b>"+(value==null?'':value)+"</b></lable>";
			}
		}
		function get_PM_SIGN_IN(value,rec) {
			if(rec.PM_SIGN_STATE_IN=="1"){
				return value==null?'':value;
			}else{
				return "<lable style='color: red;'><b>"+(value==null?'':value)+"</b></lable>";
			}
		}
		function get_PM_SIGN_OUT(value,rec) {
			if(rec.PM_SIGN_STATE_OUT=="1"){
				return value==null?'':value;
			}else{
				return "<lable style='color: red;'><b>"+(value==null?'':value)+"</b></lable>";
			}
		}
		
		function get_AM_PHONE_MODEL_IN(value, rec) {
		    if (rec.haveRole) {
		        return rec.AM_PHONE_MODEL_IN;
		    }
		}
		function get_AM_IMEI_IN(value, rec) {
		    if (rec.haveRole) {
		        return rec.AM_IMEI_IN;
		    }
		}
		function get_AM_PHONE_MODEL_OUT(value, rec) {
		    if (rec.haveRole) {
		        return rec.AM_PHONE_MODEL_OUT;
		    }
		}
		function get_AM_IMEI_OUT(value, rec) {
		    if (rec.haveRole) {
		        return rec.AM_IMEI_OUT;
		    }
		}
		function get_PM_PHONE_MODEL_IN(value, rec) {
		    if (rec.haveRole) {
		        return rec.PM_PHONE_MODEL_IN;
		    }
		}
		function get_PM_IMEI_IN(value, rec) {
		    if (rec.haveRole) {
		        return rec.PM_IMEI_IN;
		    }
		}
		function get_PM_PHONE_MODEL_OUT(value, rec) {
		    if (rec.haveRole) {
		        return rec.PM_PHONE_MODEL_OUT;
		    }
		}
		function get_PM_IMEI_OUT(value, rec) {
		    if (rec.haveRole) {
		        return rec.PM_IMEI_OUT;
		    }
		}
		//初始化签到信息
		var url;
	    function newYwtAttendanceSign(){
	    	 $.post('${ctx}/webservice/YwtAttendanceSign.do?method=add',function(result){
	    		 $("#sign_table #sign").attr("sign",result.signType).html('<h1>'+result.signTypeName+'</h1>');
	    		 $("#sign_table #sign").click(function(){
		    	       var sign=$(this).attr("sign");
		    	       $("#sign_table #sign").hide();
		    	        $.ajax({
				    	   type: "POST",
				    	   url: "${ctx}/webservice/YwtAttendanceSign.do?method=newsign",
				    	   data: "sign_type="+sign,
				    	   success: function(result){
				    	     alert( "提示: " + result.res);
				    	     if(result.signType){
				    	    	 $("#sign_table #sign").attr("sign",result.signType);
						     }
						     if(result.signTypeName){
						    	 $("#sign_table #sign").html('<h1>'+result.signTypeName+'</h1>');
							 }
				    	     $("#sign_table #sign").show();
				    	   },
				    	   error:function(XMLHttpRequest, textStatus, errorThrown) {
				    		    alert("操作异常");
				    		    $("#sign_table #sign").show();
				    		}
				    	});
		    	 });
	    		 $("#sign_table #location").click(function(){
	    			  var sign=$(this).attr("location");
		    	       $("#sign_table #location").hide();
		    	        alert("定位异常");
		    		    $("#sign_table #location").show();
		    	 });
		        $('#dlg_sign').dialog('open').dialog('setTitle','签到'); 
	         });
	    }
	    
	    function back(){
	    	$("#sign_table #sign").unbind("click");
	    	 $('#table').datagrid('reload');
	    	$('#dlg_sign').dialog('close');
	    	
		}
	</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
