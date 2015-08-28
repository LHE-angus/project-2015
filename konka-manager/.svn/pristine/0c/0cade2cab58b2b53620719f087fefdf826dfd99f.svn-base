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
			        	<td>&nbsp;当前位置：<font color="red">进货管理</font></td>
			      	</tr>
			    </table>
			</div>
			<div class="content clearfix">  
	            <div class="columnA" style="width: 600px; margin-bottom: 5px; margin-right: 5px">
	                <div class="border-box" style="min-height: 500px;padding:0 5px 0 5px">
	                	<span style="color:#74685f;">数据来源：已确认或已完结的订单记录</span>
	                	<div>
	                		<table width="100%" border="0">
	                			<tr>
	                				<td style="padding-right: 5px" width="35%">
	                					<table width="100%" border="0">
	                						<tr>
	                							<td width="25px" style="background-color: #02B0F0;text-align:center;vertical-align:middle"><font color="white">本<br/>年</font></td>
	                							<td>
	                								<div style="padding-left: 5px;border-top:1px solid #02B0F0;border-bottom:1px solid #02B0F0; border-right:1px solid #02B0F0; color: #02B0F0;">
	                									进货总额：<span id='year_money'></span>元<br/>
	                									进货总量：<span id='year_num'></span>台<br/>
	                									平均单价：<span id='year_aver'></span>元/台
	                								</div>
	                							</td>
	                						</tr>
	                					</table>
	                				</td>
	                				<td style="padding-right: 5px" width="35%">
	                					<table width="100%" border="0">
	                						<tr>
	                							<td width="25px" style="background-color: #01AF4F;text-align:center;vertical-align:middle"><font color="white">本<br/>月</font></td>
	                							<td>
	                								<div style="padding-left: 5px;border-top:1px solid #01AF4F;border-bottom:1px solid #01AF4F; border-right:1px solid #01AF4F;color: #01AF4F; ">
	                									进货总额：<span id='month_money'></span>元<br/>
	                									进货总量：<span id='month_num'></span>台<br/>
	                									平均单价：<span id='month_aver'></span>元/台
	                								</div>
	                							</td>
	                						</tr>
	                					</table>
	                				</td>
	                				<td>
	                					<table width="100%" border="0">
	                						<tr>
	                							<td width="25px" style="background-color: #FF4B21;text-align:center;vertical-align:middle"><font color="white">今<br/>日</font></td>
	                							<td>
	                								<div style="padding-left: 5px;border-top:1px solid #FF4B21;border-bottom:1px solid #FF4B21; border-right:1px solid #FF4B21;color: #FF4B21; ">
	                									进货总额：<span id='today_money'></span>元<br/>
	                									进货总量：<span id='today_num'></span>台<br/>
	                									平均单价：<span id='today_aver'></span>元/台
	                								</div>
	                							</td>
	                						</tr>
	                					</table>
	                				</td>
	                			</tr>
	                		</table>
	                	</div>
	                	<div id="loading" align="center" style="height: 60px;padding-top:60px"><img src="${ctx }/styles/images/loading.gif" />&nbsp;&nbsp;正在加载数据。。。</div>
	                	<div id='this_year_month' style="height: 340px;width: 100%; margin-top: 5px"></div>
	                </div>
	            </div>
	            <div class="columnA" style="width: 470px">
	                <div class="border-box" style="min-height: 80px; padding-top: 5px">
	                    <div align="center" style="height: 30px"><font size="5">本月度各机型进货统计</font></div>
	                    <div id="listDiv" align='center'><img src="${ctx }/styles/images/loading.gif" />&nbsp;&nbsp;正在加载数据。。。</div>
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
                'echarts/chart/bar'
            ],
            DrawCharts
        );
		
		//分开调用画图方法
        function DrawCharts(ec) {
        	getListData();
        	getIndata();
            FunDraw1(ec);
        }
		
      	//ajax查询机型进货数据列表
		function getListData(){
			$.post('${ctx}/customer/manager/Indexs.do?method=getMonthInData',function(result){
				var list_num = result.inlist.length;
				var total_money = 0;
				var total_num = 0;
				if(list_num>0){
					var str = "<table width='100%' border='0' cellspacing='0' cellpadding='0' class='rtable2'><tr><td colspan='6'>数据（单位：元，台）</td></tr>"+
						      "<tr align='center'><td>序号</td><td>商品类型</td><td>机型</td><td>进货金额</td><td>进货数量</td><td>平均单价</td></tr>";
					jQuery.each(result.inlist,function(index,da){
						index += 1;
						total_money += Number(da.IN_MONEY);
						total_num += Number(da.IN_NUM);
						str += "<tr><td align='center'>"+index+"</td><td align='center'>"+da.BRAND_TYPE+"</td><td align='center'>"+da.MD_NAME+
						       "</td><td align='right'>"+formatCurrency(da.IN_MONEY)+"</td><td align='right'>"+da.IN_NUM+"</td><td align='right'>"+formatCurrency(da.AVER_PRICE)+"</td></tr>";
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
		
		//当年，当月，当日进货数据
        function getIndata() {
        	$.post('${ctx}/customer/manager/Indexs.do?method=buyInfo',function(result){
        		$("#year_money").html(result.year_money);
        		$("#year_num").html(result.year_num);
        		$("#year_aver").html(result.year_aver);
        		$("#month_money").html(result.month_money);
        		$("#month_num").html(result.month_num);
        		$("#month_aver").html(result.month_aver);
        		$("#today_money").html(result.today_money);
        		$("#today_num").html(result.today_num);
        		$("#today_aver").html(result.today_aver);
        	},'json');
		}
      	
		//本年度月度进货金额柱状图
        function FunDraw1(ec) {
            // 基于准备好的dom，初始化echarts图表
            var leftChart = ec.init(document.getElementById('this_year_month')); 
            $.post('${ctx}/customer/manager/Indexs.do?method=getMonthsInData',function(result){
	            var option = {
	            	    title : {
	            	        text: result.year+'年月度进货金额对比表'
	            	    },
	            	    tooltip : {
	            	        trigger: 'axis'
	            	    },
	            	    legend: {
	            	        data:['进货金额']
	            	    },
	            	    toolbox: {
	            	        show : true,
	            	        feature : {
	            	            magicType : {show: true, type: ['bar']},
	            	            restore : {show: true}
	            	        }
	            	    },
	            	    calculable : true,
	            	    xAxis : [
	            	        {
	            	            type : 'category',
	            	            data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
	            	        }
	            	    ],
	            	    yAxis : [
	            	        {
	            	            type : 'value',
	            	            name : '金额',
	            	            axisLabel : {
	            	                formatter: '{value} 元'
	            	            }
	            	        }
	            	    ],
	            	    series : [
	            	        {
	            	            name:'进货金额',
	            	            type:'bar',
	            	            data:JSON.parse(result.moneys),
	            	            markPoint : {
	            	                data : [
	            	                    {type : 'max', name: '最大值'},
	            	                    {type : 'min', name: '最小值'}
	            	                ]
	            	            }
	            	        }
	            	    ]
	            	};
	            	                    
	            // 为echarts对象加载数据 
	            $("#loading").hide();
	            leftChart.setOption(option); 
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
	</body>
	<jsp:include page="/customer/__analytics.jsp" />
</html>
