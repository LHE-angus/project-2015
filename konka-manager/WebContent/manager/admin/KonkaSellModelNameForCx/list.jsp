<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>${naviString}</title>
	<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/commons/scripts/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/commons/scripts/themes/icon.css"/>
	<style type="text/css">
		.rtable1 td{
			padding: 2px 0 2px 0;
		}
	</style>
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>&nbsp;<fmt:message key="current.location" bundle="${lang}"/>：<span id='nav'></span></td>
        <td width="60">&nbsp;</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
  	<form id="form" action="/admin/KonkaSellModelNameForCx" method="post">
  		<input type="hidden" id="mod_id" name="mod_id"/>
      	<%--<hidden property="tj_type" styleId="tj_type" value="${af.map.tj_type}" />--%>
		<input type="hidden" id='excel_to_all' name="excel_to_all" value=''  />
		<input type="hidden" id='queryStr' value=''/>
      	<table width="100%" border="0" cellspacing="1" cellpadding="0" class="rtable1">
	        <tr>
				<td width="15"></td>
				<td align="right"><strong class="fb">销售时间：</strong></td>
				<td>
					<input id="sell_date_start" class="easyui-datebox" style="width:90px;" />
					至
					<input id="sell_date_end" class="easyui-datebox" style="width:90px;" />
				</td>
				<td align="right"><strong class="fb">排名显示：</strong></td>
				<td>
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
				<td align="right"><strong class="fb">分&nbsp;公&nbsp;司：</strong></td>
				<td><input id="dept_id" name="dept_id" style="width:150px"/>
				</td>
	        </tr>
	        <tr>
	        	<td width="15"></td>
	        	<td align="right"><strong class="fb">产品型号：</strong></td>
				<td>
		        	<input id="model_name" size="15" maxlength="40" name="model_name"  class="easyui-validatebox"/>
				</td>
				<td align="right"><strong class="fb">业&nbsp;务&nbsp;员：</strong></td>
		        <td><input id="ywy_user_name_like" size="15" maxlength="40" name="ywy_user_name_like"  class="easyui-validatebox"/></td>
		        <td align="right"><strong class="fb">尺&nbsp;&nbsp;&nbsp;&nbsp;寸：</strong></td>
		        <td><input id="md_size" size="15" maxlength="10" name="md_size"  class="easyui-validatebox"/></td>
			</tr>
			<tr>
				<td width="15"></td>
		        <td align="right"><strong class="fb">尺&nbsp;寸&nbsp;段：</strong></td>
		        <td>
					<input id="size_sec" name="size_sec" style="width:150px"/>
	        	</td>
				<td align="right"><strong class="fb">客户名称：</strong></td>
				<td><input id="kh_name_like" size="15" maxlength="40" name="kh_name_like"  class="easyui-validatebox"/></td>
				<td align="right"><strong class="fb">R3&nbsp;编&nbsp;码：</strong></td>
				<td><input id="r3_code_like" size="15" maxlength="10" name="r3_code_like" class="easyui-validatebox" /></td>
	        </tr>
	        <tr>
	          	<td width="15"></td>
				<td align="right"><strong class="fb">客户类型：</strong></td>
				<td>
					<input class="easyui-validatebox" type="text" name="v_customer_type1" id="v_customer_type1" style="width:80px" />
					<input class="easyui-validatebox" type="text" name="v_customer_type2" id="v_customer_type2" style="width:120px"/>
				</td>
				<td align="right"><strong class="fb">排序：</strong></td>
				<td>
					<select id="tj_type" class="easyui-combobox" name="tj_type" style="width:150px" data-options="editable:false">
						<option value="1">按零售额</option>
						<option value="2">按零售量</option>
					</select>
				</td>
	          	<td align="right">
					<input class="but1" type="button" name="Submit" value="搜索" onclick="queryData()"/>
				</td>
				<td>&nbsp;&nbsp;
					<input type="button" value="导出" id="export" class="but_excel" style="margin-left: 10px;" title="导出EXCEL表格" />
				</td>
	        </tr>
      </table>
  	</form>
  </div>
  <div class="rtabcont1">
  	<table width="100%">
  		<tr>
  			<td width="48%" valign="top" >
				<div><strong class="fb">合计：零售额：<span id="total_money">0</span>元，零售量：<span id="total_num">0</span>台</strong></div>
				<table id="list_tab" width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable6">
					<tr class="tabtt6">
						<td width="5%" nowrap="nowrap" align="center">序号</td>
						<td nowrap="nowrap" align="center">产品型号</td>
						<td nowrap="nowrap" align="center" width="5%">规格段</td>
						<td width="20%" align="center" nowrap="nowrap">零售额</td>
						<td width="20%" align="center" nowrap="nowrap">零售量</td>
						<td width="20%" align="center" nowrap="nowrap">平均单价</td>
					</tr>
					<tbody id="tbody"></tbody>
				</table>
				<div id="loading1" align="center" style="height: 60px;padding-top:60px"><img src="${ctx }/styles/images/loading.gif" />&nbsp;&nbsp;正在加载数据。。。</div>
  			</td>
  			<td width="2%">&nbsp;</td>
  			<td width="50%" valign="top">
				<div id="loading2" align="center" style="height: 60px;padding-top:60px"><img src="${ctx }/styles/images/loading.gif" />&nbsp;&nbsp;正在加载数据。。。</div>
				<table id="pie_tab" align="center" width="100%" cellpadding="0" cellspacing="0">
	       			<tr align="center">
	       				<td>
							<div id='pie_chart1' style="height: 390px;width: 100%; margin-top: 5px"></div>
	       				</td>
	       			</tr>
					<tr height="10px"></tr>
					<tr align="center">
	       				<td>
							<div id='pie_chart2' style="height: 380px;width: 100%; margin-top: 5px"></div>
	       				</td>
	       			</tr>
    			</table>
			</td>
  		</tr>
  	</table>

  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/json2.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/public.js"></script>
<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
<script type="text/javascript">//<![CDATA[
//路径配置   Add by Liang Houen on 2015-07-16
require.config({
	paths: {
		echarts: 'http://echarts.baidu.com/build/dist'
	}
});
var num1 = 0;var money1=0;
var num2 = 0;var money2=0;
var num3 = 0;var money3=0;
var num4 = 0;var money4=0;
var num5 = 0;var money5=0;
var num6 = 0;var money6=0;
var num7 = 0;var money7=0;
var num8 = 0;var money8=0;
var num9 = 0;var money9=0;
//查询方法
function queryData(){
	num1 = 0; money1=0;
	num2 = 0; money2=0;
	num3 = 0; money3=0;
	num4 = 0; money4=0;
	num5 = 0; money5=0;
	num6 = 0; money6=0;
	num7 = 0; money7=0;
	num8 = 0; money8=0;
	num9 = 0; money9=0;
	$("#total_money").text(0);
	$("#total_num").text(0);
	$("#loading1").show();
	$("#tbody").empty();
	$("#pie_tab").hide();
	$("#loading2").show();
	var s_date = $("#sell_date_start").datebox('getValue');
	var e_date = $("#sell_date_end").datebox('getValue');
	$.ajax( {
		type : "POST",
		cache : false,
		url : "${ctx}/manager/admin/KonkaSellModelNameForCx.do",
		data : "method=queryDatas&sell_date_start="+s_date+"&sell_date_end="+e_date+"&"+$("#form").serialize(),
		dataType: "json",
		error : function(data) { alert("请求失败！") },
		success : function(data) {
			if(data.dataList.length>0){
				$("#tbody").show();
				var str ="";
				var total_money = 0;
				var total_num = 0;
				jQuery.each(data.dataList,function(index,da){
					str = "<tr><td align='center'>"+parseFloat(index+1)+"</td>"+
					"<td align='left' nowrap='nowrap'>"+da.MODEL_NAME+"</td>"+
					"<td align='center' nowrap='nowrap'>"+da.TYPE_NAME+"</td>"+
					"<td align='right' nowrap='nowrap'>"+formatCurrency(da.ALL_PRICE,1)+"</td>"+
					"<td align='right' nowrap='nowrap'>"+formatCurrency(da.ALL_NUM,0)+"</td>"+
					"<td align='right' nowrap='nowrap'>"+formatCurrency(da.PRICE,1)+"</td></tr>";
					$("#tbody").append(str);
					total_money += parseFloat(da.ALL_PRICE);
					total_num += parseFloat(da.ALL_NUM);

					//饼图数据
					if('<32英寸'==da.TYPE_NAME){
						num1 += parseFloat(da.ALL_NUM);
						money1 += parseFloat(da.ALL_PRICE);
					}
					if('32-36英寸'==da.TYPE_NAME){
						num2 += parseFloat(da.ALL_NUM);
						money2 += parseFloat(da.ALL_PRICE);
					}
					if('37-39英寸'==da.TYPE_NAME){
						num3 += parseFloat(da.ALL_NUM);
						money3 += parseFloat(da.ALL_PRICE);
					}
					if('40-45英寸'==da.TYPE_NAME){
						num4 += parseFloat(da.ALL_NUM);
						money4 += parseFloat(da.ALL_PRICE);
					}
					if('46-50英寸'==da.TYPE_NAME){
						num5 += parseFloat(da.ALL_NUM);
						money5 += parseFloat(da.ALL_PRICE);
					}
					if('51-59英寸'==da.TYPE_NAME){
						num6 += parseFloat(da.ALL_NUM);
						money6 += parseFloat(da.ALL_PRICE);
					}
					if('60-69英寸'==da.TYPE_NAME){
						num7 += parseFloat(da.ALL_NUM);
						money7 += parseFloat(da.ALL_PRICE);
					}
					if('>=70英寸'==da.TYPE_NAME){
						num8 += parseFloat(da.ALL_NUM);
						money8 += parseFloat(da.ALL_PRICE);
					}
					if('其他'==da.TYPE_NAME){
						num9 += parseFloat(da.ALL_NUM);
						money9 += parseFloat(da.ALL_PRICE);
					}
				});
				$("#loading1").hide();
				$("#total_money").text(formatCurrency(total_money,1));
				$("#total_num").text(formatCurrency(total_num,0));

				//加载指定类型图形并生成
				require(
						[
							'echarts',
							'echarts/chart/pie'
						],
						DrawCharts
				);
				function DrawCharts(ec){
					$("#pie_tab").show();
					$("#loading2").hide();
					showMoneyChart(ec);
					showNumChart(ec);
				}
			}else{
				$("#tbody").hide();
				$("#loading1").html("查询无数据！");
				$("#loading2").html("查询无数据！");
				$("#pie_tab").hide();
			}
			}
		});
	}

	//根据销售额画图
	function showMoneyChart(ec) {
	// 基于准备好的dom，初始化echarts图表
		var Chart = ec.init(document.getElementById('pie_chart1'));
		var option = {
			title : {
				text: '产品规格段零售额占比图',
				x:'center'
			},
			tooltip : {
				trigger: 'item',
				formatter: "{a} <br/>{b} : {c} ({d}%)"
			},
			calculable : true,
			series : [
				{
					name:'尺寸段',
					type:'pie',
					radius : [30, 110],
					center : ['50%', 200],
					roseType : 'radius',
					width: '40%',       // for funnel
					max: 40,            // for funnel
					sort : 'ascending',
					data:[
						{value:money1,name:'<32英寸'},
						{value:money2,name:'32-36英寸'},
						{value:money3,name:'37-39英寸'},
						{value:money4,name:'40-45英寸'},
						{value:money5,name:'46-50英寸'},
						{value:money6,name:'51-59英寸'},
						{value:money7,name:'60-69英寸'},
						{value:money8,name:'>=70英寸'},
						{value:money9,name:'其他'}
					]
				}
			]
		};
	// 为echarts对象加载数据
		Chart.setOption(option);
		window.parent.resizeFrameHeight('mainFrame', 3);
	}

	//根据销量画图
	function showNumChart(ec) {
	// 基于准备好的dom，初始化echarts图表
		var Chart = ec.init(document.getElementById('pie_chart2'));
		var option = {
			title : {
				text: '产品规格段零售量占比图',
				x:'center'
			},
			tooltip : {
				trigger: 'item',
				formatter: "{a} <br/>{b} : {c} ({d}%)"
			},
			calculable : true,
			series : [
				{
					name:'尺寸段',
					type:'pie',
					radius : [30, 110],
					center : ['50%', 200],
					roseType : 'radius',
					width: '40%',       // for funnel
					max: 40,            // for funnel
					sort : 'ascending',
					data:[
						{value:num1,name:'<32英寸'},
						{value:num2,name:'32-36英寸'},
						{value:num3,name:'37-39英寸'},
						{value:num4,name:'40-45英寸'},
						{value:num5,name:'46-50英寸'},
						{value:num6,name:'51-59英寸'},
						{value:num7,name:'60-69英寸'},
						{value:num8,name:'>=70英寸'},
						{value:num9,name:'其他'}
					]
				}
			]
		};
	// 为echarts对象加载数据
		Chart.setOption(option);
		window.parent.resizeFrameHeight('mainFrame', 3);
	}

	//导出
	$("#export").bind('click',function(){
		$.messager.confirm('温馨提示', '是否导出数据？', function(r){
			if (r){
				var s_date = $("#sell_date_start").datebox('getValue');
				var e_date = $("#sell_date_end").datebox('getValue');
				//_czc.push(["_trackEvent", "零售畅销机型下载", "导出", "Excel", 0, "export"]);
				$("#form").attr("action", "${ctx}/manager/admin/KonkaSellModelNameForCx.do?method=queryDatas&export=1&sell_date_start="+s_date+"&sell_date_end="+e_date+"&"+$("#form").serialize());
				$("#form").submit();
			}
		});
	});

	$(document).ready(function(){
		//获取模块id
		var mod_id = GetQueryString("mod_id");
		if(mod_id==''){
			mod_id = $("#mod_id").val();
		}
		//初始化
		$.post('${ctx}/manager/admin/KonkaSellModelNameForCx.do?method=init&mod_id='+mod_id,function(result) {
			$("#nav").text(result.local_str);
			$("#sell_date_start").datebox('setValue',result.sell_date_start);
			$("#sell_date_end").datebox('setValue',result.sell_date_end);

			//分公司初始化
			$('#dept_id').combobox({
				url:'${ctx}/manager/admin/CsAjax.do?method=getAjaxKonkaDeptList',
				method:'post',
				editable:false, //不可编辑状态
				cache: false,
				valueField:'DEPT_ID',
				textField:'DEPT_NAME'
			});
			$('#dept_id').combobox("setValue", result.dept_id);
			//一级客户类型初始化
			var v_customer_type1 = $('#v_customer_type1').combobox({
				url:'${ctx}/manager/admin/CsAjax.do?method=getCusType&par_id=0',
				method:'post',
				editable:false, //不可编辑状态
				cache: false,
				valueField:'PAR_INDEX',
				textField:'C_COMM',
				onSelect:function(record){
					v_customer_type2.combobox({
						disabled:false,
						url:'${ctx}/manager/admin/CsAjax.do?method=getCusType&par_id='+v_customer_type1.combobox('getValue'),
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
			var v_customer_type2 = $('#v_customer_type2').combobox({
				editable:false, //不可编辑状态
				cache: false
			});

			//尺寸段初始化
			$('#size_sec').combobox({
				url:'${ctx}/manager/admin/CsAjax.do?method=getSizeSecList',
				method:'post',
				editable:false, //不可编辑状态
				cache: false,
				valueField:'field1',
				textField:'type_name'
			});
			//查询列表
			queryData();
		},'json');

		$("#span_help").click(function(){
			$("#cvtooltipClose").cvtooltip({
				panel: "#body_oarcont",
				direction: 1,
				width: 420,
				left: 320,
				top: 5,
				speed: 500,
				delay: 10000
			});
		});

		$('#tj_type').combobox({
			onSelect: function (record) {
				queryData();
			}
		});
	});

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>


