<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="MSThemeCompatible" content="no" />
		<meta http-equiv="X-UA-Compatible" content="IE=7" />
		<title>${naviString}</title>
		<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css"  href="${ctx}/styles/customer/css/base.css" />
		<link rel="stylesheet" type="text/css"  href="${ctx}/styles/customer/css/index.css" />
	</head>
	<body >
		<div class="oarcont">
			<div class="oartop">
				<table width="400" border="0" cellpadding="0" cellspacing="0">
			    	<tr>
			        	<td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
			        	<td>&nbsp;当前位置：<font color="red">销售管理</font></td>
			      	</tr>
			    </table>
			</div>
			<div class="content clearfix">  
	            <div class="columnA" style="width: 600px; margin-bottom: 5px; margin-right: 5px">
	                <div class="border-box" style="min-height: 400px;padding:0 5px 0 5px">
	                	<span style="color:#74685f;">数据来源：实际销售来源于分销与零售数据</span>
	                	<div>
	                		<table width="100%" border="0">
	                			<tr>
	                				<td style="padding-right: 5px" width="35%">
	                					<table width="100%" border="0">
	                						<tr>
	                							<td width="20px" style="background-color: #02B0F0;text-align:center;vertical-align:middle"><font color="white">本<br/>年</font></td>
	                							<td>
	                								<div id="this_year" style="height: 110px;width:100%;" align="center"></div>
	                								<div style="border-top:1px solid #02B0F0;border-bottom:1px solid #02B0F0; border-right:1px solid #02B0F0; color: #02B0F0; text-align: center">
	                									销售目标：<span id="target_year"></span>元<br/>
	                									实际销售：<span id="sales_year"></span>元
	                								</div>
	                							</td>
	                						</tr>
	                					</table>
	                				</td>
	                				<td style="padding-right: 5px" width="35%">
	                					<table width="100%" border="0">
	                						<tr>
	                							<td width="20px" style="background-color: #01AF4F;text-align:center;vertical-align:middle"><font color="white">本<br/>月</font></td>
	                							<td>
	                								<div id="this_month" style="height: 110px;width:100%;" align="center"></div>
	                								<div style="border-top:1px solid #01AF4F;border-bottom:1px solid #01AF4F; border-right:1px solid #01AF4F;color: #01AF4F; text-align: center">
	                									销售目标：<span id="target_month"></span>元<br/>
	                									实际销售：<span id="sales_month"></span>元
	                								</div>
	                							</td>
	                						</tr>
	                					</table>
	                				</td>
	                				<td>
	                					<div style="border:1px solid #FFC01E;margin-bottom: 13px">
	                						<table width="100%" border="0">
	                						<tr>
	                							<td width="20px" style="background-color: #FFC01E;text-align:center;vertical-align:middle"><font color="white">昨<br/>日</font></td>
	                							<td style="color: #FFC01E; padding-left: 5px">
	                								销售总额：<span id="sales_money_yesterday"></span>元<br/>
	                								销售总量：<span id="sales_num_yesterday"></span>台<br/>
	                								平均单价：<span id="average_yesterday"></span>元/台
	                							</td>
	                						</tr>
	                					</table>
	                					</div>
	                					<div style="border:1px solid #FF4B21;">
	                						<table width="100%" border="0">
	                						<tr>
	                							<td width="20px" style="background-color: #FF4B21;text-align:center;vertical-align:middle"><font color="white">今<br/>日</font></td>
	                							<td style="color: #FF4B21; padding-left: 5px">
	                								销售总额：<span id="sales_money_today"></span>元<br/>
	                								销售总量：<span id="sales_num_today"></span>台<br/>
	                								平均单价：<span id="average_today"></span>元/台
	                							</td>
	                						</tr>
	                					</table>
	                					</div>
	                				</td>
	                			</tr>
	                		</table>
	                	</div>
	                	<div id="loading" align="center" style="height: 100px;padding-top:100px"><img src="${ctx }/styles/images/loading.gif" />&nbsp;&nbsp;正在加载数据。。。</div>
	                	<div id='this_year_month' style="height: 340px;width: 100%; margin-top: 5px">
	                	</div>
	                </div>
	            </div>
	            <div class="columnA" style="width: 470px">
	                <div class="border-box" style="min-height: 80px; padding-top: 5px">
	                    <div align="center" style="height: 30px"><font size="5">本月度各机型销售统计</font></div>
	                    <div id="listDiv" align="center"><img src="${ctx }/styles/images/loading.gif" />&nbsp;&nbsp;正在加载数据。。。</div>
	                </div>
	            </div>
	        </div>
		</div>
		<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="${ctx}/commons/scripts/json2.js"></script> 
		<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
		
	<script type="text/javascript">
		// 路径配置   Add by Liang Houen on 2015-07-16
		require.config({
	    	paths: {
	        	echarts: 'http://echarts.baidu.com/build/dist'
	    	}
		});
		
		//加载指定类型图形并生成
        require(
            [
                'echarts',
                'echarts/chart/gauge',
                'echarts/chart/bar',
                'echarts/chart/line'
            ],
            DrawCharts
        );
		
		//分开调用画图方法
        function DrawCharts(ec) {
			getListData();
            FunDraw1(ec);
            FunDraw2(ec);
        }
		
		//ajax查询机型销售数据列表
		function getListData(){
			$.post('${ctx}/customer/manager/Indexs.do?method=getSalesListData',function(result){
				var list_num = result.salesList.length;
				var total_money = 0;
				var total_num = 0;
				if(list_num>0){
					var str = "<table width='100%' border='0' cellspacing='0' cellpadding='0' class='rtable2'><tr><td colspan='6'>数据（单位：元，台）</td></tr>"+
						      "<tr align='center'><td>序号</td><td>商品类型</td><td>机型</td><td>销售金额</td><td>销售数量</td><td>平均单价</td></tr>";
					jQuery.each(result.salesList,function(index,da){
						index += 1;
						total_money += Number(da.SALE_MONEY);
						total_num += Number(da.SALE_NUM);
						str += "<tr><td align='center'>"+index+"</td><td align='center'>"+da.BRAND_TYPE+"</td><td>&nbsp;&nbsp;"+da.MD_NAME+
						       "</td><td align='right'>"+formatCurrency(da.SALE_MONEY)+"</td><td align='right'>"+da.SALE_NUM+"</td><td align='right'>"+formatCurrency(da.AVER_PRICE)+"</td></tr>";
					});
					var average_price = 0;
					if(total_num>0){
						average_price = total_money/total_num;
					}
					$("#listDiv").html(str+"<tr><td colspan='3' align='center'>合计</td><td align='right'>"+formatCurrency(total_money)+"</td><td align='right'>"+total_num+"</td><td align='right'>"+formatCurrency(average_price)+"</td></tr></table>");
				}else{
					$("#listDiv").html("暂无数据！");
				}
			},'json');
		}
		
		
		//生成本年度目标任务完成图
        function FunDraw1(ec) {
            // 基于准备好的dom，初始化echarts图表
            var yearChart = ec.init(document.getElementById('this_year')); 
            var monthChart = ec.init(document.getElementById('this_month'));
            $.post('${ctx}/customer/manager/Indexs.do?method=getTargetAndData',function(result){
            	$("#target_year").html(result.target_year);
            	$("#sales_year").html(result.sales_year);
            	$("#target_month").html(result.target_month);
            	$("#sales_month").html(result.sales_month);
            	$("#sales_money_yesterday").html(result.sales_money_yesterday);
            	$("#sales_num_yesterday").html(result.sales_num_yesterday);
            	$("#average_yesterday").html(result.average_yesterday);
            	$("#sales_money_today").html(result.sales_money_today);
            	$("#sales_num_today").html(result.sales_num_today);
            	$("#average_today").html(result.average_today);
            	
	            var option1 = {
	            	    tooltip : {
	            	        formatter: "{a} <br/>{b} : {c}%"
	            	    },
	            	    series : [
	            	        {
	            	            name:'业务指标',
	            	            type:'gauge',
	            	            startAngle: 180,  //开始角度
	            	            endAngle: 0,  //结束角度
	            	            center : ['50%', '70%'],    // 圆心坐标，默认全局居中
	            	            radius : 70, //半径
	            	            axisLine: {            // 坐标轴线
	            	                lineStyle: {       // 属性lineStyle控制线条样式
	            	                    width: 20
	            	                }
	            	            },
	            	            axisTick: {            // 坐标轴小标记
	            	                splitNumber: 10,   // 每份split细分多少段
	            	                length :10        // 属性length控制线长
	            	            },
	            	            axisLabel: {           // 坐标轴文本标签，详见axis.axisLabel
	            	                
	            	                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
	            	                    color: '#ccc',
	            	                    fontSize: 10,
	            	                    fontWeight: 'bolder'
	            	                }
	            	            },
	            	            pointer: {   //指针样式
	            	                width:10,
	            	                length: '90%',
	            	                color: '#FFC01E'
	            	            },
	            	            detail : {
	            	                show : true,
	            	                backgroundColor: 'rgba(0,0,0,0)',
	            	                borderWidth: 0,
	            	                borderColor: '#ccc',
	            	                width: 50,
	            	                height: 40,
	            	                offsetCenter: [0, -0],       // x, y，单位px
	            	                formatter:'{value}%',
	            	                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
	            	                    fontSize : 20
	            	                }
	            	            },
	            	            data:[{value: result.year_rate, name: ''}]
	            	        }
	            	    ]
	            };
	         	// 为echarts对象加载数据 
	            yearChart.setOption(option1); 
	            var option2 = {
	            	    tooltip : {
	            	        formatter: "{a} <br/>{b} : {c}%"
	            	    },
	            	    series : [
	            	        {
	            	            name:'业务指标',
	            	            type:'gauge',
	            	            startAngle: 180,  //开始角度
	            	            endAngle: 0,  //结束角度
	            	            center : ['50%', '70%'],    // 圆心坐标，默认全局居中
	            	            radius : 70, //半径
	            	            axisLine: {            // 坐标轴线
	            	                lineStyle: {       // 属性lineStyle控制线条样式
	            	                    width: 20
	            	                }
	            	            },
	            	            axisTick: {            // 坐标轴小标记
	            	                splitNumber: 10,   // 每份split细分多少段
	            	                length :10       // 属性length控制线长
	            	            },
	            	            axisLabel: {           // 坐标轴文本标签，详见axis.axisLabel
	            	                
	            	                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
	            	                    color: '#ccc',
	            	                    fontSize: 10,
	            	                    fontWeight: 'bolder'
	            	                }
	            	            },
	            	            pointer: {   //指针样式
	            	                width:10,
	            	                length: '90%',
	            	                color: '#FFC01E'
	            	            },
	            	            detail : {
	            	                show : true,
	            	                backgroundColor: 'rgba(0,0,0,0)',
	            	                borderWidth: 0,
	            	                borderColor: '#01AF4F',
	            	                width: 50,
	            	                height: 40,
	            	                offsetCenter: [0, -0],       // x, y，单位px
	            	                formatter:'{value}%',
	            	                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
	            	                    fontSize : 20
	            	                }
	            	            },
	            	            data:[{value: result.month_rate, name: ''}]
	            	        }
	            	    ]
	            };                  
	            // 为echarts对象加载数据 
	            monthChart.setOption(option2); 
            },'json');
            
   		}
		
      	//生成本月目标任务完成图
        function FunDraw2(ec) {
            // 基于准备好的dom，初始化echarts图表
            var leftChart = ec.init(document.getElementById('this_year_month')); 
            $.post('${ctx}/customer/manager/Indexs.do?method=salesInfo',function(result){
	            var option1 = {
	            		title : {
	            	        text: result.year+'年'+result.month+'月份销售动态'
	            	    },
	            	    tooltip : {
	            	        trigger: 'axis'
	            	    },
	            	    toolbox: {
	            	        show : true,
	            	        feature : {
	            	            magicType: {show: true, type: ['line', 'bar']},
	            	            restore : {show: true}
	            	        }
	            	    },
	            	    calculable : true,
	            	    legend: {
	            	        data:['平均单价','销售额']
	            	    },
	            	    xAxis : [
	            	        {
	            	            type : 'category',
	            	            data : JSON.parse(result.days)
	            	        }
	            	    ],
	            	    yAxis : [
	            	        {
	            	            type : 'value',
	            	            name : '销售额',
	            	            axisLabel : {
	            	                formatter: '{value} 元'
	            	            }
	            	        },
	            	        {
	            	            type : 'value',
	            	            name : '单价',
	            	            axisLabel : {
	            	                formatter: '{value} 元'
	            	            }
	            	        }
	            	    ],
	            	    series : [
	            	        {
	            	            name:'销售额',
	            	            type:'bar',
	            	            data: JSON.parse(result.moneys)
	            	        },
	            	        {
	            	            name:'平均单价',
	            	            type:'line',
	            	            yAxisIndex: 1,
	            	            data: JSON.parse(result.prices)
	            	        }
	            	    ]
	            	};
                                
	            // 为echarts对象加载数据 
	            $("#loading").hide();
	            leftChart.setOption(option1); 
            },'json');
   		}
      	
      	
      	//格式化金额
      	function formatCurrency(num) {
		    num = num.toString().replace(/\$|\,/g,'');
		    if(isNaN(num))
		    num = "0";
		    sign = (num == (num = Math.abs(num)));
		    num = Math.floor(num*100+0.50000000001);
		    cents = num%100;
		    num = Math.floor(num/100).toString();
		    if(cents<10)
		    cents = "0" + cents;
		    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
		    num = num.substring(0,num.length-(4*i+3))+','+
		    num.substring(num.length-(4*i+3));
		    return (((sign)?'':'-') + num + '.' + cents);
		}
	</script>
	<jsp:include page="/customer/__analytics.jsp" />
	</body>
</html>
