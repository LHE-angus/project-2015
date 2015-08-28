<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta charset="utf-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
		<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
  		<link href="${ctx}/manager/leader/style/bootstrap.css" rel="stylesheet">
	  	<link rel="stylesheet" href="${ctx}/manager/leader/style/font-awesome.css"> 
	  	<link rel="stylesheet" href="${ctx}/manager/leader/style/jquery-ui.css"> 
	  	<link rel="stylesheet" href="${ctx}/manager/leader/style/fullcalendar.css">
	  	<link rel="stylesheet" href="${ctx}/manager/leader/style/prettyPhoto.css">  
	  	<link rel="stylesheet" href="${ctx}/manager/leader/style/rateit.css">
	  	<link rel="stylesheet" href="${ctx}/manager/leader/style/bootstrap-datetimepicker.min.css">
	  	<link rel="stylesheet" href="${ctx}/manager/leader/style/jquery.cleditor.css"> 
	  	<link rel="stylesheet" href="${ctx}/manager/leader/style/bootstrap-switch.css">
	  	<link href="${ctx}/manager/leader/style/style.css" rel="stylesheet">
	  	<link href="${ctx}/manager/leader/style/widgets.css" rel="stylesheet">   
	  	<link rel="stylesheet" type="text/css" href="${ctx}/commons/scripts/themes/default/easyui.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/commons/scripts/themes/icon.css"/>
		<link href="${ctx}/manager/leader/style/showLoading.css" rel="stylesheet" type="text/css" />
	  
	  <!-- HTML5 Support for IE -->
	  <!--[if lt IE 9]>
	  <script src="js/html5shim.js"></script>
	  	<![endif]-->
	
	  	<link rel="shortcut icon" href="img/favicon/favicon.png">
		
		<title><fmt:message key="bi.title" bundle="${lang }"/></title>
	</head>

	<body style="padding-top:0px">
		<%-- <div class="navbar navbar-fixed-top bs-docs-nav" role="banner">
			<jsp:include page="/manager/leader/head.jsp"></jsp:include>
		</div> --%>
		<div class="content" >
			<%-- <div class="sidebar">
				<jsp:include page="/manager/leader/left.jsp"></jsp:include>
			</div> --%>
			<div class="mainbar" style="margin-left: 0px">
				<div class="page-head">
					<h2 class="pull-left"><i class="icon-home"></i><fmt:message key="product.area.analyse" bundle="${lang }"/></h2>
					<div class="clearfix"></div>
				</div>
				<div class="matter">
					<div class="container">
						<div id="first" style="display: ${map_show}">
							<form action="${ctx}/manager/leader/JcfxProductAreaMap.do" method="post">
							<div class="widget">
								<div class="widget-head">
									<div class="pull-left"><fmt:message key="query.condition" bundle="${lang }"/></div>
									<div class="widget-icons pull-right">
										<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
										<!-- <a href="#" class="wclose"><i class="icon-remove"></i></a> -->
									</div>
									<div class="clearfix"></div>
								</div>
								<div class="widget-content referrer">
									<div style="padding: 5px;" align="center"  >
										<strong><fmt:message key="year" bundle="${lang }"/>：</strong> 
										<%-- <select id="year" class="easyui-combobox" name="year" style="width: 80px;">
											 <c:forEach items="${yearList}" var="cur">
									            <option value="${cur}">${cur}</option>
									         </c:forEach>
										</select> --%>
										<html-el:select property="year" styleId="year" value="${af.map.year}">
											<c:forEach items="${yearList}" var="cur">
												<html-el:option value="${cur}">${cur}年</html-el:option>
											</c:forEach>
										</html-el:select>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 									<strong><fmt:message key="month" bundle="${lang }"/>：</strong> 
										<%-- <select id="month" class="easyui-combobox" name="month" style="width: 80px;">
											 <c:forEach items="${monList}" var="cur">
									            <option value="${cur}">${cur}</option>
									         </c:forEach>
										</select> --%>
										<%-- <select id="month" class="easyui-combobox" name="month" style="width: 80px;">
											<c:forEach begin="1" end="12" varStatus="vs" step="1">
								            	<option value="${vs.current}">${vs.current}</option>
								          	</c:forEach>
										</select> --%>
										<html-el:select property="month" styleId="month" value="${af.map.month}">
											<c:forEach begin="1" end="12" varStatus="vs" step="1">
								            	<html-el:option value="${vs.current}">${vs.current}月</html-el:option>
								          	</c:forEach>
										</html-el:select>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span style="display: ${map_show}">
	 									<strong><fmt:message key="province" bundle="${lang }"/>：</strong> 
	 									<%-- <select id="province" class="easyui-combobox" name='province'>
								            <c:forEach items="${provinceList}" var="cur">
								            	<option value="${cur.PROVINCE_ID}">${cur.PROVINCE_NAME}</option>
								           	</c:forEach>
										</select> --%>
										<html-el:select property="province" styleId="province" value="${af.map.province}">
											<c:forEach items="${provinceList}" var="cur">
								            	<html-el:option value="${cur.PROVINCE_ID}">${cur.PROVINCE_NAME}</html-el:option>
								           	</c:forEach>
										</html-el:select>
										&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="submit" class="btn btn-success" id="submit" value="<fmt:message key='button.query' bundle='${lang }'/>" style="width:80px"/>
										</span>
									</div>
								</div>
							</div>
							</form>
						
							<div id="right_map" style="height: 500px;width:50%; float: right;" align="right"></div>
							<div id="left_map" style="height: 500px;width:50%;"></div>
							<div id="top_map" style="height: 600px;width:100%;"></div>
							<div class="widget">
	                			<div class="widget-head">
	                  				<div class="pull-left">${title_text }各城市零售量/零售额分析</div>
	                  				<div class="widget-icons pull-right">
	                    				<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
	                    				<!-- <a href="#" class="wclose"><i class="icon-remove"></i></a> -->
	                  				</div>
	                  				<div class="clearfix"></div>
	                			</div>
	                  			<div class="widget-content">
	                    			<table class="table table-striped table-bordered table-hover" id="table1">
	                      				<thead>
	                        			<tr>
	                          				<th style="text-align:center;">序号</th>
	                          				<th style="text-align:center;">城市</th>
					                        <th style="text-align:center;">零售量</th>
					                        <th style="text-align:center;">零售额</th>
					                        <th style="text-align:center;">平均单价</th>
					                        <th style="text-align:center;">上年同期零售量</th>
					                        <th style="text-align:center;">上年同期零售额</th>
					                        <th style="text-align:center;">同期增长率（量）</th>
					                        <th style="text-align:center;">同期增长率（额）</th>
					                 	</tr>
	                      				</thead>
	                      				<tbody>
	                      				<c:forEach var="c" items="${city_list }" varStatus="vs">
		                        			<tr align="center">		
		                          				<td>${vs.count }</td>
		                          				<td>
		                          					<a class="fblue" href="${ctx}/manager/leader/JcfxProductAreaMap.do?method=toViewModel&city_id=${c.CITY_ID}&year=${year}&month=${month}&province=${province}">${c.CITY_NAME }</a>
		                          				</td>
		                          				<td align="right"><fmt:formatNumber value="${c.NUM }" groupingUsed="true" type="number"></fmt:formatNumber></td>
		                          				<td align="right"><fmt:formatNumber value="${c.MONEY }" groupingUsed="true" type="currency" maxFractionDigits="2"></fmt:formatNumber></td>
		                          				<td align="right">
		                          					<c:choose>
		                          						<c:when test="${c.NUM eq 0 }">0</c:when>
		                          						<c:otherwise><fmt:formatNumber value="${c.MONEY/c.NUM }" groupingUsed="true" type="currency" maxFractionDigits="2"></fmt:formatNumber></c:otherwise>
		                          					</c:choose>
		                          				</td>
		                          				<td align="right"><fmt:formatNumber value="${c.PAR_NUM }" groupingUsed="true" type="number"></fmt:formatNumber></td>
		                          				<td align="right"><fmt:formatNumber value="${c.PAR_MONEY }" groupingUsed="true" type="currency" maxFractionDigits="2"></fmt:formatNumber></td>
		                          				<td align="right"><fmt:formatNumber value="${c.PER_NUM }" type="percent"></fmt:formatNumber></td>
		                          				<td align="right"><fmt:formatNumber value="${c.PER_MONEY }" type="percent"></fmt:formatNumber></td>
		                        			</tr>
					                   </c:forEach>
	                      				</tbody>
	                    			</table>
	                  			</div>
	                  		</div>
	                  	</div>
						<div id='mod' class="widget" style="display: ${table_show}">
							<form action="${ctx}/manager/leader/JcfxProductAreaMap.do" method="post">
								<%-- <input type="hidden" name="year" value="${year }"/>
								<input type="hidden" name="month" value="${month }"/>
								<input type="hidden" name="province" value="${province }"/> --%>
	                			<div class="widget-head">
	                  				<div class="pull-left">${year }年${month }月${city_name }各型号零售量/零售额分析</div>
	                  				<div class="widget-icons pull-right">
	                    				<!-- <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>  -->
	                    				<input type="button" class="btn btn-success" id="back" value="<fmt:message key='button.back' bundle='${lang }'/>" style="width:80px" onclick="history.back();"/>
	                  				</div>
	                  				<div class="clearfix"></div>
	                			</div>
	                  			<div class="widget-content">
	                    			<table class="table table-striped table-bordered table-hover" id="table2">
	                      				<thead>
	                        			<tr>
	                          				<th style="text-align:center;">序号</th>
	                          				<th style="text-align:center;">型号</th>
					                        <th style="text-align:center;">易TV</th>
					                        <th style="text-align:center;">4K</th>
					                        <th style="text-align:center;">零售量</th>
					                        <th style="text-align:center;">零售额</th>
					                        <th style="text-align:center;">平均单价</th>
					                        <th style="text-align:center;">上年同期零售量</th>
					                        <th style="text-align:center;">上年同期零售额</th>
					                        <th style="text-align:center;">同期增长率（量）</th>
					                        <th style="text-align:center;">同期增长率（额）</th>
					                 	</tr>
	                      				</thead>
	                      				<tbody>
	                      				<c:forEach var="c" items="${modellist }" varStatus="vs">
			                        			<tr align="center">		
			                          				<td>${vs.count }</td>
			                          				<td>${c.MODEL_NAME }</td>
			                          				<td>
			                          					<c:if test="${c.IS_YTV eq 1 }">是</c:if>
			                          					<c:if test="${c.IS_YTV eq 0 }">否</c:if>
			                          				</td>
			                          				<td>
			                          					<c:if test="${c.IS_4K eq 1 }">是</c:if>
			                          					<c:if test="${c.IS_4K eq 0 }">否</c:if>
													</td>
			                          				<td align="right"><fmt:formatNumber value="${c.NUMS }" groupingUsed="true" type="number"></fmt:formatNumber></td>
			                          				<td align="right"><fmt:formatNumber value="${c.MONEYS }" groupingUsed="true" type="currency" maxFractionDigits="2"></fmt:formatNumber></td>
			                          				<td align="right"><fmt:formatNumber value="${c.PRICES }" groupingUsed="true" type="currency" maxFractionDigits="2"></fmt:formatNumber></td>
			                          				<td align="right"><fmt:formatNumber value="${c.PAR_NUM }" groupingUsed="true" type="number"></fmt:formatNumber></td>
			                          				<td align="right"><fmt:formatNumber value="${c.PAR_MONEY }" groupingUsed="true" type="currency" maxFractionDigits="2"></fmt:formatNumber></td>
			                          				<td align="right"><fmt:formatNumber value="${c.PER_NUM }" type="percent"></fmt:formatNumber></td>
		                          					<td align="right"><fmt:formatNumber value="${c.PER_MONEY }" type="percent"></fmt:formatNumber></td>
			                        			</tr>
						                   </c:forEach>
	                      				</tbody>
	                    			</table>
	                  			</div>
	                  		</form>
                  		</div>
						<br/>
						<br/>
						<br/>
					</div>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
		<footer><jsp:include page="/manager/leader/foot.jsp"></jsp:include></footer>
		<span class="totop"><a href="#"><i class="icon-chevron-up"></i></a></span>
		
		<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
		<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script> 
		<script type="text/javascript" src="${ctx}/commons/scripts/jquery.easyui.min.js"></script>  
		<script type="text/javascript" src="${ctx}/commons/scripts/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js"></script>
		<script type="text/javascript" src="${ctx}/manager/leader/js/jquery.showLoading.min.js"></script>
		 
		<script type="text/javascript">
			
        	// 路径配置
        	require.config({
            	paths: {
                	echarts: 'http://echarts.baidu.com/build/dist'
            	}
        	});
        	
			$(".sidebar #nav > li > a").removeAttr("class");
			$(".sidebar #nav > li > a:eq(5)").attr("class","open subdrop");
			
		/* 	//初始化信息
			$('#year').val('${year}');
			$('#month').val('${month}');
			$('#province').val('${province}'); */
        
	        // 使用
	        require(
	            [
	                'echarts',
	                'echarts/chart/map',
	                'echarts/chart/bar',
	                'echarts/chart/scatter'
	            ],
	            DrawCharts
	            );
	        
	        function DrawCharts(ec) {
	            FunDraw1(ec);
	            FunDraw2(ec);
	            FunDraw3(ec);
	        }
	        
	        function FunDraw1(ec) {
	                // 基于准备好的dom，初始化echarts图表
	                var leftChart = ec.init(document.getElementById('left_map')); 
	                var option1 = {
	                    title: {
	                        text : '${map1_title}',
	                        x:'center'
	                    },
	                    legend: {
	                        orient: 'vertical',
	                        x:'left',
	                        data:['${map1_legend_data}']
	                    },
	                    dataRange: {
	                        min: 0,
	                        max: 1000,
	                        color:['orange','yellow'],
	                        text:['高','低'],           // 文本，默认为数值文本
	                        calculable : true,
	                        itemWidth:10
	                    },
	                    series : [
	                        {
	                            name: '${map1_legend_data}',
	                            type: 'map',
	                            mapType: '${map1_maptype}',
	                            selectedMode : 'single',
	                            itemStyle:{
	                                normal:{label:{
	                                	show:true,
	                                	textStyle:{
	                                		fontSize:7
	                                	}}},
	                                emphasis:{label:{
	                                	show:true,
	                                	textStyle:{
	                                		fontSize:7
	                                	}}}
	                            },
	                            data:[${map1_data}]
	                        }
	                    ]
	                };
	                                    
	                // 为echarts对象加载数据 
	                leftChart.setOption(option1); 
	       }
	        
	        function FunDraw2(ec) {
	    	   var rightChart = ec.init(document.getElementById('right_map')); 
	   			var option2 = {
   					title: {
                        text : '${map2_title}',
                        x:'center'
                    },
	       	    	tooltip : {
	       	        	trigger: 'axis',
	       	        	axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	       	            	type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	       	        	}
	           	    },
	           	    legend: {
	           	        data:[${map2_legend_data}],
	           	     	x:'left',
	           	     	y:30,
	           	     	padding:0,
	           	     	itemGap:0
	           	    },
	           	    toolbox: {
	           	    	orient:'vertical',
	           	        show : true,
	           	        feature : {
	           	            dataView : {show: true, readOnly: false},
	           	            magicType : {show: true, type: ['stack', 'tiled']},
	           	            restore : {show: true},
	           	            saveAsImage : {show: true}
	           	        },
	           	        y:50
	           	    },
	           	    calculable : true,
	           	    xAxis : [
	           	        {
	           	            type : 'value'
	           	        }
	           	    ],
	           	    yAxis : [
	           	        {
	           	            type : 'category',
	           	            data : ${map2_y_data}
	           	        }
	           	    ],
	           	    series : [${map2_data}]
	           	};
	          	rightChart.setOption(option2);  
	       }
	        
	        function FunDraw3(ec) {
	    	   var topChart = ec.init(document.getElementById('top_map')); 
	   			var option3 = {
	   				    title : {
	   				        text : '${map3_title}',
	   				        x:'center'
	   				    },
	   				    tooltip : {
	   				        trigger: 'item',
	   				        formatter : function (params) {
	   				            return '${map3_machine_type}：' + params.seriesName +'<br/>'
	   				                   + "${map3_retail_number}：" + params.value[1] + ', ' 
	   				                   + "${map3_average_price}：" + params.value[2]; 
	   				        }
	   				    },
	   				    toolbox: {
	   				    	orient:'vertical',
	   				        show : true,
	   				        feature : {
	   				            dataView : {show: true, readOnly: false},
	   				            restore : {show: true},
	   				            saveAsImage : {show: true}
	   				        }
	   				    },
	   				    /*dataRange: {
	   				        min: 0,
	   				        max: 100,
	   				        orient: 'horizontal',
	   				        x: 'center',
	   				        y:35,
	   				        color:['lightgreen','orange'],
	   				        splitNumber: 5
	   				    },*/
	   				    xAxis : [
	   				        {
	   				            type : 'category',
	   				         	axisLabel:{
	   				         		show:true,
	   				         		interval:0,
	   				         		rotate:${map3_rotate}  //当横坐标文字过长时，设置倾斜显示
	   				         	},
	   				            data :${map3_x_data}
	   				        }
	   				    ],
	   				    yAxis : [
	   				        {
	   				            type : 'value'
	   				        }
	   				    ],
	   				    animation: true,
	   				    series : [${map3_data}]
	   				};
	   			topChart.setOption(option3);  
	       }
	        
	       function hidet(){
	    	   $("#first").hide();
	    	   $("#mod").show();
	       }
	        
        /* begin-点击标签按钮实现收起，展开 */
        $(".wclose").click(function(){	
        	$(this).parent().parent().parent().hide(100);	
        });
      	  	$('.wminimize').click(function(e){
      		    e.preventDefault();
      		    var $wcontent = $(this).parent().parent().next('.widget-content');
      		    if($wcontent.is(':visible')) 
      		    {
      		      $(this).children('i').removeClass('icon-chevron-up');
      		      $(this).children('i').addClass('icon-chevron-down');
      		    }
      		    else 
      		    {
      		      $(this).children('i').removeClass('icon-chevron-down');
      		      $(this).children('i').addClass('icon-chevron-up');
      		    }            
      		    $wcontent.toggle(500);
      		}); 
        /* end */
	    </script>
	</body>
</html>