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
		<td ><label for="begin_year"><strong class="fb">计划月份：</strong></label>
		<input class="easyui-combobox" id="begin_year" name="begin_year"  size="10"/>
		<label for="begin_month"><strong class="fb"></strong></label>
		<input class="easyui-combobox" id="begin_month" name="begin_month" size="10"/>
		到
		<label for="end_year"><strong class="fb"></strong></label>
		<input class="easyui-combobox" id="end_year" name="end_year" size="10"/>
		<label for="end_month"><strong class="fb"></strong></label>
		<input class="easyui-combobox" id="end_month" name="end_month" size="10"/>
	</td>
	<td align="left"><label for="report_nae_like"><strong class="fb"> 上&nbsp;报&nbsp;人： </strong></label>
		<input id="report_nae_like" name="report_nae_like"
			style="width: 100px" />
	</td>
	
	<td align="left"><label for="subcomp_name_like"><strong class="fb"> 分&nbsp;公&nbsp;司： </strong></label>
		<input id="subcomp_name_like" name="subcomp_name_like"
			style="width: 100px" />
	</td>	
	</tr>
	<tr>
	<td>
	<label for="r3_code"><strong class="fb">客户名称：</strong></label>
	<input id="r3_code" class="easyui-combobox" /> 	
	<label for="shop_id"><strong class="fb">终端名称：</strong></label>
	 <input id="shop_id" class="easyui-combobox"  /> 
     </td>
     <td>
        <label for="is_del"><strong class="fb">状态：</strong></label>
		<input class="easyui-combobox" id="is_del" name="is_del" />
	</td>
	<td width="20%">
	    <label for="data_source"><strong class="fb">数据来源：</strong></label>
		<input class="easyui-combobox" id="data_source" name="data_source" />
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
<table id="table" class="easyui-datagrid" title="月度计划"  
        data-options="url:'${ctx}/manager/admin/KonkaMobileVisitPlan.do?method=list1',fitColumns:true,singleSelect:true"
        rownumbers="true" idField="plan_id" toolbar="#toolbar" singleSelect="false" pagination="true"  loadMsg="正在拼命加载中，请稍后..."  fitColumns="true" >   
    <thead>   
    <tr style="bgcolor:orange;" >
        <th field="ck" checkbox="true"></th>    
	    <th width="80px;"  field="plan_begin_date" formatter="getplan_begin_date" >计划月份</th>
	    <th width="80px;"  field="subcomp_name" formatter="getsubcomp_name" >分公司</th>
	    <th width="80px;"  field="report_nae"   >上报人</th>
	    <th width="80px;"  field="plan_of_access"  >计划拜访次数</th>
	    <th width="80px;"  field="plan_of_access_cust" >计划拜访客户数</th>
	    <th width="80px;"  field="plan_of_access_shop"  >计划拜访终端数</th>
	    <th width="80px;"  field="plan_of_dev_cust"   >计划开拓数</th>
	    <th width="80px;"  field="customerName"  formatter="getcustomer_names">客户名称</th>
	    <th width="80px;"  field="shopName"  formatter="getshop_names">终端名称</th>
	    <th width="80px;"  field="plan_desc"  >计划说明</th>
	    <th width="80px;"  field="add_date" formatter="getadd_date">创建日期</th>
	    <th width="80px;"  field="update_date" formatter="getupdate_date">最后更新日期</th>
	    <th width="80px;"  field="is_del"  formatter="getis_del">状态</th>
	    <th width="80px;"  field="data_source" formatter="getdata_source" >数据来源</th>
    </tr>
   </thead>      
</table>  
</div>
<div id="toolbar" style="margin-left:5px;margin-top:5px;border-top-style: solid;border-top-width: 1px; border-top-color:#C9CFCD "> 
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newVisitPlan()">新增</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editVisitPlan()">修改</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteVisitPlan()">删除</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="viewVisitPlan()">查看</a>
</div>    

<!-- 新增和修改页面dialog -->
	<div id="dlg" class="easyui-dialog" style="width:700px;height:500px;padding:10px 20px"  closed="true" buttons="#dlg-buttons">  
      <form id="addOrEdit" method="post" novalidate="false" enctype="multipart/form-data"> 
          <table width="100%" border="0" align="right" cellspacing="0" cellpadding="0" class="rtable3"> 
          
          <tr>
          	<td width="50%">
          		<label for="add_date"><strong class="fb">创建日期：</strong></label>
				   		<span id="add_date"></span>
				<input type="hidden" name="plan_id" id="plan_id"></input> 
				<input type="hidden" name="mod_id" id="mod_id"></input> 
          	</td>
          	</tr>
          <tr>
          	<td width="50%">
          	<label for="year"><strong class="fb">计划月份：</strong></label>
			<select id="year" class="easyui-combobox" name="year" style="width:150px;" editable="false" data-options="required:true">
	      		   <option value="2014">2014</option>
	      		   <option value="2015">2015</option>
	      		   <option value="2016">2016</option>
	      		   <option value="2017">2017</option>
	      		   <option value="2018">2018</option>
	      		   <option value="2019">2019</option>
	      		   <option value="2020">2020</option>
	    	</select>年
	    	</td>
	    	<td>
	    	<label for="month"><strong class="fb"></strong></label>
	    	<select id="month" class="easyui-combobox" name="month" style="width:150px;" editable="false" data-options="required:true" >
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
	    	</select>月
          	</td>
          </tr>
          <tr>
          	<td>
			<label for="r3_code_name"><strong class="fb">客户名称</strong></label>
			 <input name="r3_code_name" id="r3_code_name" style="width:110px"></input> 
		     </td>
		     <td>
		     <label for="shop_id_name"><strong class="fb">终端名称</strong></label>
			  <input name="shop_id_name" id="shop_id_name" style="width:110px"></input> 	
			</td>
          </tr>
          <tr>
          	<td width="50%">
          		<label for="plan_of_access_cust"><strong class="fb">计划拜访客户数：</strong></label>
				   		 <input id="plan_of_access_cust" name="plan_of_access_cust"  style="width:150px" editable="false" />
          	</td>
          	<td width="50%">
          		<label for="plan_of_access_shop"><strong class="fb">计划拜访终端数：</strong></label>
				   		 <input id="plan_of_access_shop" name="plan_of_access_shop"  style="width:150px" editable="false" />
          	</td>
          </tr>
           <tr>
          	<td width="50%">
          		<label for="plan_of_access"><strong class="fb">计划拜访次数：</strong></label>
				   		 <input id="plan_of_access" name="plan_of_access" class="easyui-textbox" style="width:150px" editable="false" data-options="required:true"/>
          	</td>
          	<td width="50%">
          		<label for="plan_of_dev_cust"><strong class="fb">计划开拓数：</strong></label>
				   		 <input id="plan_of_dev_cust" name="plan_of_dev_cust" class="easyui-textbox" style="width:150px" editable="false" />
          	</td>
          </tr>
          <tr>
          	<td width="100%" colspan="2">
          		<label for="plan_desc"><strong class="fb">拜访说明：</strong></label>
				   		<textarea id="plan_desc" name="plan_desc" class="easyui-validatebox" style="width:500px;height: 150px;" data-options="required:true"></textarea> 
          	</td>
          </tr>
          </table>
       </form>
		</div>
	 <div id="dlg-buttons">
	    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveVisitPlan()" style="width:90px">保存</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">退出</a>
	</div> 
<!--查看-->
<div id="dlg_view" class="easyui-dialog" style="width:700px;height:500px;padding:10px 20px"  closed="true" buttons="#dlg_view-buttons">  
      <form id="view" method="post" novalidate="false" enctype="multipart/form-data"> 
          <table width="100%" border="0" align="right" cellspacing="0" cellpadding="0" class="rtable3"> 
          
          <tr>
          	<td width="50%">
          		<label for="add_date"><strong class="fb">创建日期：</strong></label>
				   		<span id="add_date"></span>
          	</td>
          	</tr>
          <tr>
          	<td width="50%">
          	<label for="year"><strong class="fb">计划月份：</strong></label>
			<select id="year" class="easyui-combobox" name="year" style="width:150px;" editable="false" >
	      		   <option value="2014">2014</option>
	      		   <option value="2015">2015</option>
	      		   <option value="2016">2016</option>
	      		   <option value="2017">2017</option>
	      		   <option value="2018">2018</option>
	      		   <option value="2019">2019</option>
	      		   <option value="2020">2020</option>
	    	</select>年
	    	</td>
	    	<td>
	    	<label for="month"><strong class="fb"></strong></label>
	    	<select id="month" class="easyui-combobox" name="month" style="width:150px;" editable="false"  >
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
	    	</select>月
          	</td>
          </tr>
          <tr>
          	<td>
			<label for="r3_code_name"><strong class="fb">客户名称</strong></label>
			 <input name="r3_code_name" id="r3_code_name" style="width:110px"></input> 
		     </td>
		     <td>
		     <label for="shop_id_name"><strong class="fb">终端名称</strong></label>
			  <input name="shop_id_name" id="shop_id_name" style="width:110px"></input> 	
			</td>
          </tr>
          <tr>
          	<td width="50%">
          		<label for="plan_of_access_cust"><strong class="fb">计划拜访客户数：</strong></label>
				   		 <input id="plan_of_access_cust" name="plan_of_access_cust" class="easyui-validatebox" style="width:150px" editable="false" />
          	</td>
          	<td width="50%">
          		<label for="plan_of_access_shop"><strong class="fb">计划拜访终端数：</strong></label>
				   		 <input id="plan_of_access_shop" name="plan_of_access_shop" class="easyui-validatebox" style="width:150px" editable="false" />
          	</td>
          </tr>
           <tr>
          	<td width="50%">
          		<label for="plan_of_access"><strong class="fb">计划拜访次数：</strong></label>
				   		 <input id="plan_of_access" name="plan_of_access" class="easyui-textbox" style="width:150px" editable="false" />
          	</td>
          	<td width="50%">
          		<label for="plan_of_dev_cust"><strong class="fb">计划开拓数：</strong></label>
				   		 <input id="plan_of_dev_cust" name="plan_of_dev_cust" class="easyui-textbox" style="width:150px" editable="false" />
          	</td>
          </tr>
          <tr>
          	<td width="100%" colspan="2">
          		<label for="plan_desc"><strong class="fb">拜访说明：</strong></label>
				   		<textarea  id="plan_desc" name="plan_desc" class="easyui-validatebox" style="width:500px;height: 150px;" ></textarea> 
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
			$.post('${ctx}/manager/admin/KonkaMobileVisitPlan.do?method=init&mod_id=108010',function(result){
				$("#nav").text("客户管理 > 业务通 > 月度计划—新版");
				$('#begin_year').combobox({valueField:"id", textField:"text", 
					editable:false, data:[{id:"",text:"-请选择-"},{id:2011,text:"2011"},{id:2012,text:"2012"},
					      				{id:2013,text:"2013"}
					,{id:2014,text:"2014"},{id:2015,text:"2015"}
					,{id:2016,text:"2016"},{id:2017,text:"2017"}
					,{id:2018,text:"2018"},{id:2019,text:"2019"}
					,{id:2020,text:"2020"}]});
				$('#begin_year').combobox("setValue", result.year);
				
				$('#begin_month').combobox({valueField:"id", textField:"text", 
					editable:false, data:[{id:"",text:"-请选择-"},{id:"01",text:"1"},{id:"02",text:"2"},
					      				  {id:"03",text:"3"},{id:"04",text:"4"},
										  {id:"05",text:"5"},{id:"06",text:"6"},
										  {id:"07",text:"7"},{id:"08",text:"8"},
										  {id:"09",text:"9"},{id:"10",text:"10"},
										  {id:"11",text:"11"},{id:"12",text:"12"}]});
				$('#begin_month').combobox("setValue", result.month);

				$('#end_year').combobox({valueField:"id", textField:"text", 
					editable:false, data:[{id:"",text:"-请选择-"},{id:2011,text:"2011"},{id:2012,text:"2012"},
					      				{id:2013,text:"2013"}
					,{id:2014,text:"2014"},{id:2015,text:"2015"}
					,{id:2016,text:"2016"},{id:2017,text:"2017"}
					,{id:2018,text:"2018"},{id:2019,text:"2019"}
					,{id:2020,text:"2020"}]});
				$('#end_year').combobox("setValue", result.year);
				
				$('#end_month').combobox({valueField:"id", textField:"text", 
					editable:false, data:[{id:"",text:"-请选择-"},{id:"01",text:"1"},{id:"02",text:"2"},
					      				  {id:"03",text:"3"},{id:"04",text:"4"},
										  {id:"05",text:"5"},{id:"06",text:"6"},
										  {id:"07",text:"7"},{id:"08",text:"8"},
										  {id:"09",text:"9"},{id:"10",text:"10"},
										  {id:"11",text:"11"},{id:"12",text:"12"}]});
				$('#end_month').combobox("setValue", result.month);

				$('#is_del').combobox({valueField:"id", textField:"text", 
					editable:false, data:[{id:"",text:"-请选择-"},{id:"0",text:"正常"},{id:"1",text:"删除"}]});
				$('#is_del').combobox("setValue", "0");
				
				$('#data_source').combobox({valueField:"id", textField:"text", 
					editable:false, data:[{id:"",text:"-请选择-"},{id:"1",text:"手机端"},{id:"2",text:"web端"}]});
				
				
				$('#r3_code').combobox({valueField:"customer_code", textField:"customer_name",editable:false, data:result.custList});
				$('#shop_id').combobox({valueField:"store_id", textField:"store_name", editable:false, data:result.storeList});
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
					report_nae_like: $("#report_nae_like").val(),
					subcomp_name_like: $("#subcomp_name_like").val(),
					begin_year: $("#begin_year").combobox('getValue'),
					begin_month: $("#begin_month").combobox('getValue'),
					end_year: $("#end_year").combobox('getValue'),
					end_month: $("#end_month").combobox('getValue'),
					r3_code: $("#r3_code").combobox('getValue'),
					shop_id: $("#shop_id").combobox('getValue'),
					is_del: $("#is_del").combobox('getValue'),
					data_source: $("#data_source").combobox('getValue')
		   		});
			//查询按钮绑定事件 
			$("#search").bind('click',function(){
				$('#table').datagrid('load',{
					report_nae_like: $("#report_nae_like").val(),
					subcomp_name_like: $("#subcomp_name_like").val(),
					begin_year: $("#begin_year").combobox('getValue'),
					begin_month: $("#begin_month").combobox('getValue'),
					end_year: $("#end_year").combobox('getValue'),
					end_month: $("#end_month").combobox('getValue'),
					r3_code: $("#r3_code").combobox('getValue'),
					shop_id: $("#shop_id").combobox('getValue'),
					is_del: $("#is_del").combobox('getValue'),
					data_source: $("#data_source").combobox('getValue')
		   		});
			});

			$("#exprot").bind('click',function(){
				$.messager.confirm('温馨提示', '是否导出数据？', function(r){
					if (r){
						var str = '&report_nae_like='+$("#report_nae_like").val()+'&subcomp_name_like='+$("#subcomp_name_like").val()+
								  '&begin_year='+$("#begin_year").combobox('getValue')+'&begin_month='+$("#begin_month").combobox('getValue')+
								  '&is_del='+$("#is_del").combobox('getValue')+'&data_source='+$("#data_source").combobox('getValue')+
								  '&end_year='+$("#end_year").combobox('getValue')+'&end_month='+$("#end_month").combobox('getValue')+
								  '&excel_all=1'+"&r3_code="+$("#r3_code").combobox('getValue')+"&r3_code="+$("#shop_id").combobox('getValue');
						$("#fm").attr("action", "${ctx}/manager/admin/KonkaMobileVisitPlan.do?method=list1"+str);
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
		function getsubcomp_name(value,rec){
            return rec.map.subcomp_name;
		}
		function getcustomer_names(value,rec){
			return rec.map.customer_names;     
		}
		function getshop_names(value,rec){
			return rec.map.shop_names;     
		}
		function getplan_begin_date(value,rec){
			return rec.map.plan_begin_date;
		}
		function getadd_date(value,rec){ 
			return rec.map.add_date;
		} 
		function getupdate_date(value,rec){ 
			return rec.map.update_date;
		} 
		function getis_del(value){
			if(value==0){
			  return "正常";
			}else{
              return "删除";    
			}
		}
		function getdata_source(value){ 
			if(value==0||value==1||value==3){
			  return "手机端";
			}else{
              return "web端";    
			}
		}
		//新建计划
		var url;
	    function newVisitPlan(){
	    	 $.post('${ctx}/manager/admin/KonkaMobileVisitPlan.do?method=add1',function(result){
		         $("#addOrEdit #add_date").text(result.add_date);       

		    	$('#addOrEdit #r3_code_name').combogrid({
		    	    panelWidth: 110,
		    	    multiple: true,
		    	    idField: 'customer_code',
		    	    textField: 'customer_name',
		    	    data:result.custList,
		    	    columns: [[
		    	     {field:'ck',checkbox:true},
		    	     {field:'customer_name',title:'请选择',width:120}
		    	    ]],
		    	    editable:false,
		    	    fitColumns: true
		    	   });

		    	$('#addOrEdit #shop_id_name').combogrid({
		    	    panelWidth: 110,
		    	    multiple: true,
		    	    idField: 'store_id',
		    	    textField: 'store_name',
		    	    data:result.storeList,
		    	    columns: [[
		    	     {field:'ck',checkbox:true},
		    	     {field:'store_name',title:'请选择',width:120}
		    	    ]],
		    	    editable:false,
		    	    fitColumns: true
		    	   });
		    	   
		        $('#dlg').dialog('open').dialog('setTitle','新增计划'); 
		        $('#addOrEdit').form('clear');   
	         });
	     url = '${ctx}/manager/admin/KonkaMobileVisitPlan.do?method=save1';
	    }
	    //保存计划
	    function saveVisitPlan(){
	        $('#addOrEdit').form('submit',{
	            url: url,
	            onSubmit: function(){
	        	   var plandesc=$('#addOrEdit #plan_desc').val();
	        	   if (plandesc.length>500) {
					alert("拜访说明不能多于500字");
					return false;
				   }
					var r3_code_name =  $('#addOrEdit #r3_code_name').combobox("getValues");
					var shop_id_name =  $('#addOrEdit #shop_id_name').combobox("getValues");
					if((r3_code_name == ''||r3_code_name == null)&&(shop_id_name == ''||shop_id_name == null)){
						alert("拜访客户终端必选一个");
						return false;
					}
					var plan_of_access =  $('#addOrEdit #plan_of_access').val();
					var plan_of_access_cust =  $('#addOrEdit #plan_of_access_cust').val();
					var plan_of_access_shop =  $('#addOrEdit #plan_of_access_shop').val();
					 if(parseInt(plan_of_access)<(parseInt(plan_of_access_cust)+parseInt(plan_of_access_shop))){
                         alert("拜访次数必须大于计划客户和终端的总数！");
                         return false;
					    }
					return $(this).form('validate'); 
			    },
	            success: function(result){
		            var result=$.parseJSON(result);
		            
		            if(result.error!=null||result.error!=''){
		            	eval(result.error);
			        }
		            
                	$.messager.show({
                        title: '操作提示！',
                        msg: result.Msg
                    });
                    $('#dlg').dialog('close');        // close the dialog
                    $('#table').datagrid('load',{
    					report_nae_like: $("#report_nae_like").val(),
    					subcomp_name_like: $("#subcomp_name_like").val(),
    					begin_year: $("#begin_year").combobox('getValue'),
    					begin_month: $("#begin_month").combobox('getValue'),
    					end_year: $("#end_year").combobox('getValue'),
    					end_month: $("#end_month").combobox('getValue'),
    					r3_code: $("#r3_code").combobox('getValue'),
    					shop_id: $("#shop_id").combobox('getValue')
    		   		});
	                
	            }
	        });
	    }
	    //修改计划
	 	function editVisitPlan(){
		 	//编辑时只能选择一行记录
	 	   var flag = false;
	       var row = $('#table').datagrid('getSelections');
	       if(row.length != 1){
        		alert("请您选择一行数据!"); 
        		return false;  
	        }
	 		$.post('${ctx}/manager/admin/KonkaMobileVisitPlan.do?method=edit1&plan_id='+row[0].plan_id,function(result){
		         $("#addOrEdit #add_date").text(result.add_date);       
		         $("#addOrEdit #plan_id").text(result.add_date);     
		    	$('#addOrEdit #r3_code_name').combogrid({
		    	    panelWidth: 110,
		    	    multiple: true,
		    	    idField: 'customer_code',
		    	    textField: 'customer_name',
		    	    data:result.custList,
		    	    columns: [[
		    	     {field:'ck',checkbox:true},
		    	     {field:'customer_name',title:'请选择',width:120}
		    	    ]],
		    	    editable:false,
		    	    fitColumns: true
		    	   });
		    	if(result.r3codeArry){
		    		$('#addOrEdit #r3_code_name').combogrid('setValues',result.r3codeArry);
		    	}
		    	$('#addOrEdit #shop_id_name').combogrid({
		    	    panelWidth: 110,
		    	    multiple: true,
		    	    idField: 'store_id',
		    	    textField: 'store_name',
		    	    data:result.storeList,
		    	    columns: [[
		    	     {field:'ck',checkbox:true},
		    	     {field:'store_name',title:'请选择',width:120}
		    	    ]],
		    	    editable:false,
		    	    fitColumns: true
		    	   });
		    	if(result.shopidArry){
		    		$('#addOrEdit #shop_id_name').combogrid('setValues',result.shopidArry);
		    	}
		    	$("#addOrEdit #plan_id").val(row[0].plan_id);
		    	$("#addOrEdit #plan_of_access_cust").val(result.plan.plan_of_access_cust);
		    	$("#addOrEdit #plan_of_access_shop").val(result.plan.plan_of_access_shop);
		    	$("#addOrEdit #plan_of_access").val(result.plan.plan_of_access);
		    	$("#addOrEdit #plan_of_dev_cust").val(result.plan.plan_of_dev_cust);
		    	$("#addOrEdit #plan_desc").val(result.plan.plan_desc);
		    	$("#addOrEdit #year").combobox('setValue',result.year);
		    	$("#addOrEdit #month").combobox('setValue',result.month);
		        $('#dlg').dialog('open').dialog('setTitle','修改计划'); 
	         });
	     url = '${ctx}/manager/admin/KonkaMobileVisitPlan.do?method=save1';
		}
		function deleteVisitPlan(){
			//编辑时只能选择一行记录
	 		var flag = false;
	          var row = $('#table').datagrid('getSelections');
	          if(row.length < 1){  
	        	  alert("最少选择一行数据!"); 
		           	return false;  
	          }else{

	        	  $.messager.confirm('温馨提示', '是否删除选中计划？', function(r){
						if (r){
							 for (var i = 0; i < row.length; i++) {
					        	  $.post('${ctx}/manager/admin/KonkaMobileVisitPlan.do?method=delete1&plan_id='+row[i].plan_id,function(result){});
					             }
								$.messager.show({
			                        title: '操作提示！',
			                        msg: result.Msg
			                    });
				        	  $('#table').datagrid('load',{
			  					report_nae_like: $("#report_nae_like").val(),
			  					subcomp_name_like: $("#subcomp_name_like").val(),
			  					begin_year: $("#begin_year").combobox('getValue'),
			  					begin_month: $("#begin_month").combobox('getValue'),
			  					end_year: $("#end_year").combobox('getValue'),
			  					end_month: $("#end_month").combobox('getValue'),
			  					r3_code: $("#r3_code").combobox('getValue'),
			  					shop_id: $("#shop_id").combobox('getValue'),
			  					is_del: $("#is_del").combobox('getValue'),
								data_source: $("#data_source").combobox('getValue')
			  		   		});
						}  
					});
	          }
		}
		function viewVisitPlan(){
			//编辑时只能选择一行记录
		 	   var flag = false;
		       var row = $('#table').datagrid('getSelections');
		       if(row.length != 1){
	        		alert("请您选择一行数据!"); 
	        		return false;  
		        }
		 		$.post('${ctx}/manager/admin/KonkaMobileVisitPlan.do?method=view1&plan_id='+row[0].plan_id,function(result){
			         $("#view #add_date").text(result.add_date);       
			         $("#view #plan_id").text(result.add_date);     
			    	$('#view #r3_code_name').combogrid({
			    	    panelWidth: 110,
			    	    multiple: true,
			    	    idField: 'customer_code',
			    	    textField: 'customer_name',
			    	    data:result.custList,
			    	    columns: [[
			    	     {field:'ck',checkbox:true},
			    	     {field:'customer_name',title:'请选择',width:120}
			    	    ]],
			    	    editable:false,
			    	    fitColumns: true
			    	   });
			    	if(result.r3codeArry){
			    		$('#view #r3_code_name').combogrid('setValues',result.r3codeArry);
			    	}
			    	$('#view #shop_id_name').combogrid({
			    	    panelWidth: 110,
			    	    multiple: true,
			    	    idField: 'store_id',
			    	    textField: 'store_name',
			    	    data:result.storeList,
			    	    columns: [[
			    	     {field:'ck',checkbox:true},
			    	     {field:'store_name',title:'请选择',width:120}
			    	    ]],
			    	    editable:false,
			    	    fitColumns: true
			    	   });
			    	if(result.shopidArry){
			    		$('#view #shop_id_name').combogrid('setValues',result.shopidArry);
			    	}
			    	$("#view #plan_id").val(row[0].plan_id);
			    	$("#view #plan_of_access_cust").val(result.plan.plan_of_access_cust);
			    	$("#view #plan_of_access_shop").val(result.plan.plan_of_access_shop);
			    	$("#view #plan_of_access").val(result.plan.plan_of_access);
			    	$("#view #plan_of_dev_cust").val(result.plan.plan_of_dev_cust);
			    	$("#view #plan_desc").val(result.plan.plan_desc);
			    	$("#view #year").combobox('setValue',result.year);
			    	$("#view #month").combobox('setValue',result.month);
			        $('#dlg_view').dialog('open').dialog('setTitle','查看计划'); 
		         });
		}
		window.setInterval(function(){
			var r3codelen=$('#r3_code_name').combogrid('getValues').length;
			//alert(r3codelen);
			$("#plan_of_access_cust").val(r3codelen);
		},1000);
		
		window.setInterval(function(){
			var shopidlen=$('#shop_id_name').combogrid('getValues').length;
			//alert(shopidlen);
			$("#plan_of_access_shop").val(shopidlen);
		},1000);
	</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
