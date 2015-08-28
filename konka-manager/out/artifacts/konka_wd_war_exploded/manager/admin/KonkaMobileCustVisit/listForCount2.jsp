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
<!--新版-->
.webinput {
	background:#f5f4f4;
	padding-left: 5px;
	height: 19px;
	line-height: 19px;
	/*font-family: Arial, Helvetica, sans-serif;*/
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
		<td><label for="dept_id"><strong class="fb">分 公
		司：</strong></label>
		<input id="dept_id" class="easyui-combobox"
			data-options="    
	        valueField: 'id',    
	        textField: 'name',    
	        url: '${ctx}/manager/admin/CsAjax.do?method=getAjaxKonkaDeptForParId&par_id=0',    
	        onSelect: function(rec){
	            var url = '${ctx}/manager/admin/CsAjax.do?method=getAjaxKonkaDeptForParId&par_id='+rec.id;    
	            $('#l4_dept_id').combobox('reload', url);    
	        }" /> 
	        <input id="l4_dept_id" class="easyui-combobox" data-options="valueField:'id',textField:'name'" />
		</td>
		<td align="right"><label for="user_name_like"><strong class="fb"> 上报人： </strong></label>
		<input id="user_name_like" name="user_name_like"
			style="width: 200px" /></td>
			</tr>
		<tr>
		<td ><label for="year_search"><strong class="fb">所属年份：</strong></label>
		<input class="easyui-combobox" id="year_search" name="year_search" />
		<label for="month_search"><strong class="fb"></strong></label>
		<input class="easyui-combobox" id="month_search" name="month_search" />
	</td>
	<td align="right"><label for="jh_visit_count"><strong class="fb">拜访任务数：</strong></label>

		<input class="easyui-combobox" id="jh_visit_count" name="jh_visit_count" />
	</td>
	<td width="20%"></td>
	</tr>
</table>
</form>
</div>
<div id="tb" style="height: auto; padding-right: 10px" align="right">
<a href="#" class="easyui-linkbutton" id="search" iconCls="icon-search">查
询</a>&nbsp; <a href="#" class="easyui-linkbutton" id="exprot"
	iconCls="icon-redo">导 出</a></div>

<div style="margin-left: 5px; margin-right: 5px; margin-top: 5px;">
<table id="table" class="easyui-datagrid" title="业务完成汇总表"  
        data-options="url:'${ctx}/manager/admin/KonkaMobileCustVisit.do?method=listForCount2',fitColumns:true,singleSelect:true"
        rownumbers="true"  pagination="true"  loadMsg="正在拼命加载中，请稍后..."  fitColumns="true" >   
    <thead>   
    <tr style="bgcolor:orange;" >
    
    <th width="80px;"  rowspan="2" field="dept_name_one" >分公司</th>
    <th width="160px;" rowspan="2" field="dept_name_two">一级部门</th>
    <th width="120px;" rowspan="2" field="user_name">上报人</th>
    <th width="80px;" nowrap="nowrap" colspan="3" >拜访客户数</th>
    <th width="80px;"  colspan="3" >拜访终端数</th>
    <th width="80px;" colspan="3">拜访次数</th>
    <th width="80px;" colspan="2">客户拜访</th>
    <th width="80px;" colspan="2">终端拜访</th>
    <th width="80px;" colspan="3">客户开拓</th>
    <th width="160px;" rowspan="2" field="update_time" formatter="formatdate" >日&nbsp;期&nbsp;区&nbsp;间</th>
  </tr>
  </thead>
  <thead>
   <tr >
    <th width="80px;" field="jh_visit_cust_count"  >计&nbsp;&nbsp;划</th>
    <th width="80px;" field="bf_cust_count" >实&nbsp;&nbsp;际</th>
    <th width="160px;" field="custbfb" formatter="formatebfb">完&nbsp;&nbsp;成&nbsp;&nbsp;率</th>
     <th width="80px;" field="jh_visit_shop_count" >计&nbsp;&nbsp;划</th>
    <th width="80px;" field="bf_shop_count" >实&nbsp;&nbsp;际</th>
    <th width="160px;" field="shopbfb" formatter="formatebfb">完&nbsp;&nbsp;成&nbsp;&nbsp;率</th>
    <th width="80px;" field="jh_visit_count" >计&nbsp;&nbsp;划</th>
    <th width="80px;" field="bf_count" >实&nbsp;&nbsp;际</th>
    <th width="160px;" field="bf_bfb" formatter="formatebfb">完&nbsp;&nbsp;成&nbsp;&nbsp;率</th>
    <th width="80px;" field="zc_cust_visit_count" >正&nbsp;&nbsp;常</th>
    <th width="80px;" field="cs_cust_visit_count" >重&nbsp;&nbsp;拾</th>
    <th width="80px;" field="zc_shop_visit_count" >正&nbsp;&nbsp;常</th>
    <th width="80px;" field="cs_shop_visit_count" >重&nbsp;&nbsp;拾</th>
    <th width="80px;" field="jh_dev_cust_count" >计&nbsp;&nbsp;划</th>
    <th width="80px;" field="kt_cust_count" >实&nbsp;&nbsp;际</th>
    <th width="80px;" field="kt_cust_visit_count" >拜&nbsp;&nbsp;访</th>
  </tr>
   </thead>      
</table>  


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
			$.post('${ctx}/manager/admin/KonkaMobileCustVisit.do?method=init&mod_id=108090',function(result){
				$("#nav").text(result.local_str);
				$('#year_search').combobox({valueField:"id", textField:"text", 
					editable:false, data:[{id:2009,text:"2009"},{id:2010,text:"2010"},{id:2011,text:"2011"},{id:2012,text:"2012"},
					      				{id:2013,text:"2013"}
					,{id:2014,text:"2014"},{id:2015,text:"2015"}
					,{id:2016,text:"2016"},{id:2017,text:"2017"}
					,{id:2018,text:"2018"},{id:2019,text:"2019"}
					,{id:2020,text:"2020"}]});
				$('#year_search').combobox("setValue", result.year);
				
				$('#month_search').combobox({valueField:"id", textField:"text", 
					editable:false, data:[{id:"01",text:"1"},{id:"02",text:"2"},
					      				  {id:"03",text:"3"},{id:"04",text:"4"},
										  {id:"05",text:"5"},{id:"06",text:"6"},
										  {id:"07",text:"7"},{id:"08",text:"8"},
										  {id:"09",text:"9"},{id:"10",text:"10"},
										  {id:"11",text:"11"},{id:"12",text:"12"}]});
				$('#month_search').combobox("setValue", result.month);
				
				$('#jh_visit_count').combobox({valueField:"id", textField:"text", 
					editable:false, data:[{id:"",text:"请选择"},{id:1,text:"有任务"},{id:2,text:"无任务"}]});
				
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
			
			//查询按钮绑定事件 
			$("#search").bind('click',function(){
				$('#table').datagrid('load',{
					dept_id: $("#dept_id").datebox('getValue'),
					user_name_like: $("#user_name_like").val(),
					year: $("#year_search").combobox('getValue'),
					month: $("#month_search").combobox('getValue'),
					jh_visit_count:$("#jh_visit_count").datebox('getValue')
		   		});
			});

			$("#exprot").bind('click',function(){
				$.messager.confirm('温馨提示', '是否导出数据？', function(r){  
					if (r){  
						var str = '&dept_id='+$("#dept_id").datebox('getValue')+'&user_name_like='+$("#user_name_like").val()+
								  '&year='+$("#year_search").combobox('getValue')+'&month='+$("#month_search").combobox('getValue')+
								  '&jh_visit_count='+$("#jh_visit_count").datebox('getValue')+'&excel_all=1';
						$("#fm").attr("action", "${ctx}/manager/admin/KonkaMobileCustVisit.do?method=listForCount2"+str);
			    		$("#fm").submit();
					}  
				});

			});
		});
		});
		function formatebfb(value){
			if(value<0){return "没做计划";}
			return (value*100).toFixed(0)+"%";
		}
		function formatdate(value){
			return $("#year_search").combobox('getValue')+'年'+$("#month_search").combobox('getValue')+'月';
		}
	</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
