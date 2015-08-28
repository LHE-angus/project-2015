<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/css/tab.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/commons/scripts/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/commons/scripts/themes/icon.css"/>
<style type="text/css">
	.list_tab {
		border-top:1px #ccc solid;
		border-left:1px #ccc solid;
	}
	.list_tab td {
		border-right:1px #e3e3e3 solid;
		border-bottom:1px #e3e3e3 solid;
		padding:5px 5px 0px 5px;
		height: 20px;
	}
	.list_tab .table_th {
		height:23px;
		background:#ED7676;
	}
	.list_tab .table_th td {
		border-right:1px #e3e3e3 solid;
		border-bottom:1px #C00 solid;
		padding:5px 5px 0px 5px;
		font:bold 12px "宋体";
		color:#FFFFFF;
	}
</style>
</head>
<body>
<div class="oarcont">
	<div class="oartop">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
				<td>&nbsp;<fmt:message key="current.location" bundle="${lang}"/>：<span id='nav'></span></td>
				<td width="60">&nbsp;</td>
			</tr>
		</table>
	</div>
	<div style="padding: 5px;">
		<ul class="tabs" style="border-color:#FFFFff">
			<li><a href="#" name="tab1">分公司排名</a></li>
			<li><a href="#" name="tab2">经办排名</a></li>
			<li><a href="#" name="tab3">客户排名</a></li>
			<li><a href="#" name="tab4">促销员排名</a></li>
		</ul>
		<div class="content">
			<%--分公司排名--%>
			<div id="tab1" style="padding:5px; height: 580px">
				<form id="form">
				<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
					<tr>
						<td width="2%">&nbsp;</td>
						<td width="29%">
							<strong class="fb">销售时间：</strong>
							<input id="sell_date_start" class="easyui-datebox" style="width:90px;" />
							至
							<input id="sell_date_end" class="easyui-datebox" style="width:90px;" />
						</td>
						<td width="24%">
							<label for="show_top"><strong class="fb">排&nbsp;&nbsp;名：</strong></label>
							<select id="show_top" class="easyui-combobox" name="show_top" style="width:150px" data-options="editable:false">
								<option value="">全部</option>
								<option value="5">Top5</option>
								<option value="10">Top10</option>
								<option value="20">Top20</option>
								<option value="30">Top30</option>
								<option value="40">Top40</option>
								<option value="50">Top50</option>
							</select>
						</td>
						<td width="45%">
							<div id="dept_div" style="display: none">
								<label for="dept_id"><strong class="fb">分&nbsp;公&nbsp;司：</strong></label>
								<input class="easyui-validatebox" type="text" name="dept_id" id="dept_id" style="width:120px" />
								<input class="easyui-validatebox" type="text" name="l4_dept_id" id="l4_dept_id" style="width:120px"/>
								<input class="easyui-validatebox" type="text" name="l5_dept_id" id="l5_dept_id" style="width:120px"/>
							</div>
						</td>
					</tr>
					<tr>
						<td width="5%">&nbsp;</td>
						<td width="29%">
							<label for="customer_type1"><strong class="fb">客户类型：</strong></label>
							<input class="easyui-validatebox" type="text" name="customer_type1" id="customer_type1" style="width:80px" />
							<input class="easyui-validatebox" type="text" name="customer_type2" id="customer_type2" style="width:120px"/>
						</td>
						<td width="24%">
							<label for="tj_type"><strong class="fb">排&nbsp;&nbsp;序：</strong></label>
							<select id="tj_type" class="easyui-combobox" name="tj_type" style="width:150px" data-options="editable:false">
								<option value="1">按零售额</option>
								<option value="2">按零售量</option>
							</select>
						</td>
						<td width="45%" align="left" style="padding-left: 10px">
							<a href="#" class="easyui-linkbutton" id="search" iconCls="icon-search" onclick="queryDatas()">查   询</a>
						</td>
					</tr>
				</table></form>
				<div style="margin-top: 5px; margin-bottom: 10px;padding-left: 10px"><strong class="fb">合计：零售额：<span id="total_money">0</span>元，零售量：<span id="total_num">0</span>台</strong></div>
				<div id="show_div" style="height: 465px; overflow-y:scroll; overflow-x:scroll;border:1px solid;">
					<table id="show_table" border="0" cellspacing="5" cellpadding="0" style="margin-top: 5px">
						<tr>
							<td width="40%" valign="top">
								<div style="margin-top: 5px; margin-bottom: 10px;padding-left: 10px" align="center"><strong class="fb">零售排名列表</strong></div>
								<table id="list_tab" width="100%" border="0" cellpadding="0" cellspacing="1" class="list_tab">
									<tr class="table_th">
										<td width="5%" nowrap="nowrap" align="center">序号</td>
										<td nowrap="nowrap" align="center" id="name_td">分公司</td>
										<td nowrap="nowrap" align="center" id="td1" style="display: none">客户类型</td>
										<td nowrap="nowrap" align="center" id="td2" style="display: none">业务员</td>
										<td width="20%" align="center" nowrap="nowrap">零售额</td>
										<td width="20%" align="center" nowrap="nowrap">零售量</td>
										<td width="20%" align="center" nowrap="nowrap">平均单价</td>
									</tr>
									<tbody id="tbody"></tbody>
								</table>
								<div id="loading1" align="center" style="height: 60px;padding-top:60px"><img src="${ctx }/styles/images/loading.gif" />&nbsp;&nbsp;正在加载数据。。。</div>
							</td>
							<td width="2%">&nbsp;</td>
							<td width="58%" valign="top">
								<div id="loading2" align="center" style="height: 60px;padding-top:60px"><img src="${ctx }/styles/images/loading.gif" />&nbsp;&nbsp;正在加载数据。。。</div>
								<div id='bar_dept' style="width: 100%;"></div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/public.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/json2.js"></script>
<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
<script type="text/javascript">
	//路径配置   Add by Liang Houen on 2015-08-20
	require.config({
		paths: {
			echarts: 'http://echarts.baidu.com/build/dist'
		}
	});

	var dept_names = [];
	var num_arr = [];
	var money_arr = [];
	var type_name = 'tab1';
	var left_width = 0;
	//查询数据
	function queryDatas(){
		dept_names=[];
		num_arr=[];
		money_arr=[];
		$("#total_money").text(0);
		$("#total_num").text(0);
		$("#loading1").show();
		$("#tbody").empty();
		$("#bar_dept").hide();
		$("#loading2").show();
		var s_date = $("#sell_date_start").datebox('getValue');
		var e_date = $("#sell_date_end").datebox('getValue');
		$.ajax( {
			type : "POST",
			cache : false,
			url : "${ctx}/manager/admin/KonkaSellRank.do",
			data : "method=queryDatas&sell_date_start="+s_date+"&sell_date_end="+e_date+"&type_name="+type_name+"&"+$("#form").serialize(),
			dataType: "json",
			error : function(data) { alert("请求失败！") },
			success : function(data) {
				if(data.dataList.length>0){
					$("#tbody").show();
					var str ="";
					var total_money = 0;
					var total_num = 0;
					var temp_arr1 = new Array();
					var temp_arr2 = new Array();
					var temp_arr3 = new Array();
					jQuery.each(data.dataList,function(index,da){
						str = "<tr><td align='center'>"+parseFloat(index+1)+"</td>";
						if("tab1" == type_name){
							str += "<td align='left' nowrap='nowrap'>"+da.DEPT_NAME+"</td>";
						}
						if("tab2" == type_name){
							if($("#l4_dept_id").combobox("getValue")!=''){
								str += "<td align='left' nowrap='nowrap'>"+da.DEPT_NAME+"-"+da.L4_DEPT_NAME+"-"+da.L5_DEPT_NAME+"</td>";
							}else{
							str += "<td align='left' nowrap='nowrap'>"+da.DEPT_NAME+"-"+da.L4_DEPT_NAME+"</td>";
							}
						}
						if("tab3" == type_name){
							str += "<td align='left' nowrap='nowrap'>"+da.DEPT_NAME+"</td>"+
							       "<td align='left' nowrap='nowrap'>"+da.L4_DEPT_NAME+"</td>"+
								   "<td align='left' nowrap='nowrap'>"+da.L5_DEPT_NAME+"</td>";
						}
						str += "<td align='right' nowrap='nowrap'>"+formatCurrency(da.ALL_MONEY,1)+"</td>"+
						"<td align='right' nowrap='nowrap'>"+formatCurrency(da.ALL_NUM,0)+"</td>"+
						"<td align='right' nowrap='nowrap'>"+formatCurrency(da.PRICE,1)+"</td></tr>";
						$("#tbody").append(str);
						total_money += parseFloat(da.ALL_MONEY);
						total_num += parseFloat(da.ALL_NUM);
						if("tab1" == type_name){
							temp_arr1[index] = da.DEPT_NAME;
						}
						if("tab2" == type_name){
							if($("#l4_dept_id").combobox("getValue")!=''){
								temp_arr1[index] = da.DEPT_NAME+"-"+da.L4_DEPT_NAME+"-"+da.L5_DEPT_NAME;
							}else{
								temp_arr1[index] = da.DEPT_NAME+"-"+da.L4_DEPT_NAME;
							}
						}
						if("tab3" == type_name){
							temp_arr1[index] = da.DEPT_NAME;
						}
						temp_arr2[index] = da.ALL_NUM;
						temp_arr3[index] = da.ALL_MONEY;
					});
					jQuery.each(data.dataList,function(index,da){
						dept_names.push(temp_arr1[temp_arr1.length-1-index]);
						num_arr.push(temp_arr2[temp_arr2.length-1-index]);
						money_arr.push(temp_arr3[temp_arr3.length-1-index]);
					});
					$("#loading1").hide();
					$("#total_money").text(formatCurrency(total_money,1));
					$("#total_num").text(formatCurrency(total_num,0));

					//加载指定类型图形并生成
					require(
							[
								'echarts',
								'echarts/chart/bar'
							],
							drawCharts
					);
				}else{
					$("#tbody").hide();
					$("#loading1").html("查询无数据！");
					$("#loading2").html("查询无数据！");
					$("#bar_dept").hide();
				}
			}
		});
	}

	//标准条形图
	function drawCharts(ec){
		$("#bar_dept").show();
		$("#loading2").hide();

		//动态设置图表的高度
		var len = dept_names.length;
		$('#bar_dept').css('height',len*26+117);
		//动态设置图表的显示区域宽度
		$("#show_div").css("width",$("#tab1").css("width"));

		//根据标题长度设置左侧距离
		if("tab1"==type_name){
			left_width = 120;
			$("#show_table").css("width",1050);
		}
		if("tab2"==type_name){
			left_width = 165;
		}
		if("tab3"==type_name){
			left_width = 230;
			$("#show_table").css("width",1500);
			$('#bar_dept').css('height',56771);
		}
		// 基于准备好的dom，初始化echarts图表
		var Chart = ec.init(document.getElementById('bar_dept'));
		var option1 = {
			title : {
				text: '零售排名',
				subtext: '数据来自零售查询',
				x:'center'
			},
			tooltip : {
				trigger: 'axis'
			},
			legend: {
				data:['零售额'],
				x:'right'
			},
			calculable : true,
			xAxis : [
				{
					type : 'value',
					boundaryGap : [0, 0.01]
				}
			],
			yAxis : [
				{
					type : 'category',
					data : dept_names
				}
			],
			grid :{
				x: left_width
			},
			series : [
				{
					name:'零售额',
					type:'bar',
					data:money_arr
				}
			]
		};
		var option2 = {
			title : {
				text: '零售排名',
				subtext: '数据来自零售查询',
				x:'center'
			},
			tooltip : {
				trigger: 'axis'
			},
			legend: {
				data:['零售量'],
				x:'right'
			},
			calculable : true,
			xAxis : [
				{
					type : 'value',
					boundaryGap : [0, 0.01]
				}
			],
			yAxis : [
				{
					type : 'category',
					data : dept_names
				}
			],
			grid :{
				x: left_width
			},
			series : [
				{
					name:'零售量',
					type:'bar',
					data:num_arr
				}
			]
		};
		// 为echarts对象加载数据
		if('1'==$("#tj_type").combobox("getValue")){
			Chart.setOption(option1);
		}else{
			Chart.setOption(option2);
		}
		window.parent.resizeFrameHeight('mainFrame', 3);
	}

	$(document).ready(function(){
		//获取模块id
		var mod_id = GetQueryString("mod_id");
		if(mod_id==''){
			mod_id = $("#mod_id").val();
		}

		$.post('${ctx}/manager/admin/KonkaSellRank.do?method=init&mod_id='+mod_id,function(result) {
			if(result.dept_id){
				$(".tabs li:eq(1)").attr("class","current");
				$(".tabs li:first").attr("class","").hide();
				type_name = "tab2";
				changeText();
			}
			$("#nav").text(result.local_str);
			$("#sell_date_start").datebox('setValue',result.sell_date_start);
			$("#sell_date_end").datebox('setValue',result.sell_date_end);
			//一级客户类型初始化
			var customer_type1 = $('#customer_type1').combobox({
				url:'${ctx}/manager/admin/CsAjax.do?method=getCusType&par_id=0',
				method:'post',
				editable:false, //不可编辑状态
				cache: false,
				valueField:'PAR_INDEX',
				textField:'C_COMM',
				onSelect:function(record){
					customer_type2.combobox({
						disabled:false,
						url:'${ctx}/manager/admin/CsAjax.do?method=getCusType&par_id='+customer_type1.combobox('getValue'),
						valueField:'C_INDEX',
						textField:'C_NAME',
						editable:false, //不可编辑状态
						cache: false,
						onLoadSuccess: function () { //加载完成后,设置选中第一个
							var val = $(this).combobox("getData");
						}
					}).combobox('clear');
				}
			});

			//二级客户类型
			var customer_type2 = $('#customer_type2').combobox({
				editable:false, //不可编辑状态
				cache: false
			});

			//经办
			var l4_dept_id = $('#l4_dept_id').combobox({
				editable:false, //不可编辑状态
				cache: false
			});
			var l5_dept_id = $('#l5_dept_id').combobox({
				editable:false, //不可编辑状态
				cache: false
			});
			//分公司初始化
			var dept_id = $('#dept_id').combobox({
				url:'${ctx}/manager/admin/CsAjax.do?method=getAjaxDeptsList&par_id=0&dept_type=3',
				method:'post',
				editable:false, //不可编辑状态
				cache: false,
				valueField:'DEPT_ID',
				textField:'DEPT_NAME',
				onSelect:function(record){
					l5_dept_id.combobox("clear");
					l4_dept_id.combobox({
						disabled:false,
						url:'${ctx}/manager/admin/CsAjax.do?method=getAjaxDeptsList&dept_type=4&par_id='+record.DEPT_ID,
						valueField:'dept_id',
						textField:'dept_name',
						editable:false, //不可编辑状态
						cache: false,
						onSelect:function(record){
							l5_dept_id.combobox({
								disabled:false,
								url:'${ctx}/manager/admin/CsAjax.do?method=getAjaxDeptsList&dept_type=5&par_id='+record.dept_id,
								valueField:'dept_id',
								textField:'dept_name',
								editable:false, //不可编辑状态
								cache: false,
								onLoadSuccess: function () { //加载完成后,设置选中第一个
									var val = $(this).combobox("getData");
								}
							}).combobox('clear');
						}
					}).combobox('clear');
				}
			});
			$('#dept_id').combobox("setValue", result.dept_id);

			//查询数据
			queryDatas();
		},'json');

		$('#tj_type').combobox({
			onSelect: function (record) {
				queryDatas();
			}
		});

		//tab切换
		$(".tabs li:first").attr("class","current");
		$('.tabs a').click(function(e) {
			e.preventDefault();
			if ($(this).closest("li").attr("class") == "current"){ //detection for current tab
				return   ;
			} else{
				$("#total_money").text(0);
				$("#total_num").text(0);
				$("#loading1").show();
				$("#tbody").empty();
				$("#bar_dept").hide();
				$("#loading2").show();
				$(".tabs li").attr("class","");
				$(this).parent().attr("class","current");
				$('#' + $(this).attr('name')).fadeIn();
				type_name = $(this).attr('name');
				changeText();
				queryDatas();
			}
			window.parent.resizeFrameHeight('mainFrame', 3);
		});

		function changeText(){
			if('tab1'==type_name){
				$("#dept_div").hide();
				$("#name_td").html("分公司");
				$("#td1").hide();
				$("#td2").hide();
			}
			if('tab2'==type_name){
				$("#name_td").html("分公司-经办");
				$("#dept_div").show();
				$("#td1").hide();
				$("#td2").hide();
			}
			if('tab3'==type_name){
				$("#dept_div").show();
				$("#td1").show();
				$("#td2").show();
				$("#name_td").html("客户名称");
			}
		}
	});
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>


