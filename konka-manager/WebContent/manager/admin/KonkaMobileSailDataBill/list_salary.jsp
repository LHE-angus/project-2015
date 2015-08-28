
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
</head>
<body>
<div class="oarcont">
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
<table width="100%" border="0" cellspacing="5" cellpadding="5"
	class="rtable1">
	<tr>
		<td><label for="dept_id"><strong class="fb">分 公
		司：</strong></label> <input id="dept_id" name="dept_id" style="width: 150px" /></td>
		<td align="right"><label for="report_name_like"><strong class="fb">促销员姓名：</strong></label>
		<input id="report_name_like" name="report_name_like"
			style="width: 200px" /></td>
		<td align="right"><label for="year_search"><strong class="fb">所属年份：</strong></label>

		<input class="easyui-combobox" id="year_search" name="year_search" />
	</td>
	</tr>
	<tr>
	<td ><label for="dept_name_like"><strong class="fb">上报门店：</strong></label>
		<input id="dept_name_like" name="dept_name_like"
			style="width: 200px" /></td>
	
	<td align="right"><label for="dept_name_like"><strong class="fb">促销员ID：</strong></label>
		<input id="report_user_like" name="report_user_like"
			style="width: 200px" /></td>
<td></td>
	</tr>
</table>
</form>
</div>
<div id="tb" style="height: auto; padding-right: 10px" align="right">
<a href="#" class="easyui-linkbutton" id="search" iconCls="icon-search">查
询</a>&nbsp; 
<!--<a href="#" class="easyui-linkbutton" id="exprot"-->
<!--	iconCls="icon-redo">导 出</a>-->
	</div>

<div style="margin-left: 5px; margin-right: 5px; margin-top: 5px;">
<table id="table" width="100%" border="0" cellspacing="0"
	cellpadding="0"></table>
</div>
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
			$.post('${ctx}/manager/admin/KonkaMobileSailDataBill.do?method=init&mod_id=211519',function(result){
				$("#nav").text(result.local_str);
				$('#dept_id').combobox({                 
					url:'${ctx}/manager/admin/CsAjax.do?method=getAjaxKonkaDeptList',  
				 	method:'post',              
				 	editable:false, //不可编辑状态                
				 	cache: false,               
				 	valueField:'DEPT_ID',                   
					textField:'DEPT_NAME'                   
				}); 
				$('#dept_id').combobox("setValue", result.dept_name);
				

				$('#year_search').combobox({valueField:"id", textField:"text", 
					editable:false, data:[{id:2011,text:"2011"},{id:2012,text:"2012"},
					      				{id:2013,text:"2013"}
					,{id:2014,text:"2014"},{id:2015,text:"2015"}
					,{id:2016,text:"2016"},{id:2017,text:"2017"}
					,{id:2018,text:"2018"},{id:2019,text:"2019"}
					,{id:2020,text:"2020"}]});
				$('#year_search').combobox("setValue", result.year);
				
			
				//表格初始化
				$("#table").datagrid({
					title: '促销员提成工资',
					url:'${ctx}/manager/admin/KonkaMobileSailDataBill.do?method=ListSalary',
					method: 'post',
					queryParams:{
						dept_id: $("#dept_id").datebox('getValue'),
						year: $("#year_search").combobox('getValue'),
						dept_name_like: $("#dept_name_like").val(),
						report_user_like: $("#report_user_like").val(),
						report_name_like: $("#report_name_like").val()
					},
					fitColumns: false,
				    onLoadError:function(){
				    	$.messager.alert('温馨提示','数据请求失败!','error');  
				    },
				    loadMsg:'正在拼命加载中，请稍后。。。。',
				    remoteSort:false,
				    pagination:true,
				    rownumbers:true,
				    columns:[[
                      {title:'分公司id',field:'SUBCOMP_ID',hidden:true},
                      {title:'门店id',field:'DEPT_ID',hidden:true},
                      {title:'上报人id',field:'REPORT_ID',hidden:true},
				     {title:'分公司',field:'SUBCOMP_NAME',width:80,editor:'text',sortable:'true',align:'center'},
				     {title:'门店',field:'DEPT_NAME',width:100,editor:'text',align:'center',
				     	formatter: function(value,row,index){
				     		if(value == null){
					    	 	return "<span style='color:#C0C0C0'>未填写</span>";
				     		}else{
				     			return value;
				     		}
				     	}
				     },
				     {title:'促销员',field:'REPORT_NAME',width:150,editor:'text',align:'center',
				     	formatter: function(value,row,index){
				     		if(value == null){
					    	 	return "<span style='color:#C0C0C0'>未填写</span>";
				     		}else{
				     			return value;
				     		}
				     	}
				     },
				     {title:'促销员JOB_ID',field:'YWY_JOB_ID',width:210,editor:'text',align:'center',
				     	formatter: function(value,row,index){
				     		if(value == null){
					    	 	return "<span style='color:#C0C0C0'>未填写</span>";
				     		}else{
				     			return value;
				     		}
				     	}
				     },
				     {title:'一月',field:'FINAL_AUDIT_MONEY_ONE',width:70,editor:'text',align:'center',
				    	 formatter: function(value,row,index){
				     	return "<a href='javascript:;' onclick='show("+row.SUBCOMP_ID+","+row.DEPT_ID+","+row.REPORT_ID+",1)' >"+value+"</a>";
				     	}},
				     {title:'二月',field:'FINAL_AUDIT_MONEY_TWO',width:70,editor:'text',align:'center',
					    	 formatter: function(value,row,index){
					     	return "<a href='javascript:;' onclick='show("+row.SUBCOMP_ID+","+row.DEPT_ID+","+row.REPORT_ID+",2)' >"+value+"</a>";
					     	}},
				     {title:'三月',field:'FINAL_AUDIT_MONEY_THREE',width:70,editor:'text',align:'center',
						    	 formatter: function(value,row,index){
						     	return "<a href='javascript:;' onclick='show("+row.SUBCOMP_ID+","+row.DEPT_ID+","+row.REPORT_ID+",3)' >"+value+"</a>";
						     	}},
				     {title:'四月',field:'FINAL_AUDIT_MONEY_FOUR',width:70,editor:'text',align:'center',
							    	 formatter: function(value,row,index){
							     	return "<a href='javascript:;' onclick='show("+row.SUBCOMP_ID+","+row.DEPT_ID+","+row.REPORT_ID+",4)' >"+value+"</a>";
							     	}},
				     {title:'五月',field:'FINAL_AUDIT_MONEY_FIVE',width:70,editor:'text',align:'center',
								    	 formatter: function(value,row,index){
								     	return "<a href='javascript:;' onclick='show("+row.SUBCOMP_ID+","+row.DEPT_ID+","+row.REPORT_ID+",5)' >"+value+"</a>";
								     	}},
				     {title:'六月',field:'FINAL_AUDIT_MONEY_SIX',width:70,editor:'text',align:'center',
									    	 formatter: function(value,row,index){
									     	return "<a href='javascript:;' onclick='show("+row.SUBCOMP_ID+","+row.DEPT_ID+","+row.REPORT_ID+",6)' >"+value+"</a>";
									     	}},
				     {title:'七月',field:'FINAL_AUDIT_MONEY_SEVEN',width:70,editor:'text',align:'center',
										    	 formatter: function(value,row,index){
										     	return "<a href='javascript:;' onclick='show("+row.SUBCOMP_ID+","+row.DEPT_ID+","+row.REPORT_ID+",7)' >"+value+"</a>";
										     	}},
				     {title:'八月',field:'FINAL_AUDIT_MONEY_EIGHT',width:70,editor:'text',align:'center',
											    	 formatter: function(value,row,index){
											     	return "<a href='javascript:;' onclick='show("+row.SUBCOMP_ID+","+row.DEPT_ID+","+row.REPORT_ID+",8)' >"+value+"</a>";
											     	}},
				     {title:'九月',field:'FINAL_AUDIT_MONEY_NINE',width:70,editor:'text',align:'center',
												    	 formatter: function(value,row,index){
												     	return "<a href='javascript:;' onclick='show("+row.SUBCOMP_ID+","+row.DEPT_ID+","+row.REPORT_ID+",9)' >"+value+"</a>";
												     	}},
				     {title:'十月',field:'FINAL_AUDIT_MONEY_TEN',width:70,editor:'text',align:'center',
													    	 formatter: function(value,row,index){
													     	return "<a href='javascript:;' onclick='show("+row.SUBCOMP_ID+","+row.DEPT_ID+","+row.REPORT_ID+",10)' >"+value+"</a>";
													     	}},
				     {title:'十一月',field:'FINAL_AUDIT_MONEY_ELEVEN',width:70,editor:'text',align:'center',
														    	 formatter: function(value,row,index){
														     	return "<a href='javascript:;' onclick='show("+row.SUBCOMP_ID+","+row.DEPT_ID+","+row.REPORT_ID+",11)' >"+value+"</a>";
														     	}},
				     {title:'十二月',field:'FINAL_AUDIT_MONEY_TWELVE',width:70,editor:'text',align:'center',
															    	 formatter: function(value,row,index){
															     	return "<a href='javascript:;' onclick='show("+row.SUBCOMP_ID+","+row.DEPT_ID+","+row.REPORT_ID+",12)' >"+value+"</a>";
															     	}},
				     //{title:'状态',field:'ID6',width:100,editor:'text',align:'center',
				     //	formatter: function(value,row,index){
				     //		return "<span style='cursor:pointer;' class='fblue' onclick='location.href = \"${ctx}/manager/admin/SystemAplication/view.jsp?mod_id="+mod_id+"&id=" + value + "\"'>有效</span>"+
					 //   	 	   "&nbsp;|&nbsp;<span style='color:#C0C0C0'>无效</span>";
				     //	}
				     //}
				    ]]
				//    onHeaderContextMenu: function(e, field){  
	             //   	e.preventDefault();  
	            //        if (!$('#tmenu').length){  
	           //        	createColumnMenu();  
	           //       	}  
	          //      	$('#tmenu').menu('show', {  
	           //         	left:e.pageX,  
	           //           	top:e.pageY  
	           //       	});  
	            //   	}
				});



				
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
					year: $("#year_search").combobox('getValue'),
					dept_name_like:$("#dept_name_like").val(),
					report_user_like: $("#report_user_like").val(),
					report_name_like:$("#report_name_like").val()
		   		}); 
			});
		});


		
			
		});
		function show(sub_id,store_id,report_id,month){
			var year=$("#year_search").combobox('getValue');
// 			var audit_state=$("#audit_state").combobox('getValue');
		        window.showModalDialog("../KonkaMobileSailDataBill.do?method=listSailOrBill&" + encodeURI("user_id=" + report_id +"&store_id=" + store_id +"&year=" + year +"&month=" + month +"&type=bill&audit_state=8&random=" + Math.random()), window, "dialogWidth:900px;status:no;dialogHeight:580px");
			
		}
	</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
