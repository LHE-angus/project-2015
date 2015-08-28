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
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/commons/scripts/themes/icon.css" />
<style type="text/css">
table{width: 100%; border-left: solid 0px; border-top: solid 1px grey;}
        td{ border-right: solid 0px; border-bottom: solid 1px grey;}
 tr{text-align: center;}
.tr_title{background-color:#1AAEF3;color:white;font-size: 14px;}
.tr_kb{padding-left:10px;padding-right:10px;border-left: solid 1px grey;border-right: solid 1px grey;border-top: solid 0px grey;border-bottom: solid 0px grey;font-size: 12px;background-color:white;}
.tr_desc{font-size: 20px;}
.tr_td_name{font-weight:bold;width:5%;}
.tr_desc1{text-align: right}
.tr_body{padding-left:10px;padding-right:10px;}
.imgshow{width: 33.3%}
.div_body{
padding-top: 5px;
padding-bottom: 5px;
width: 100%;
}
</style>
</head>
<c:if test="${not empty is_bi}">
<jsp:include page="/manager/leader/extend.jsp"></jsp:include>
</c:if>
<c:if test="${empty is_bi}">
<jsp:include page="/manager/leader/extend_not_bi.jsp"></jsp:include>
</c:if>
<body style="font-family:Microsoft Yahei;">
<c:if test="${not empty is_bi}">
<div class="navbar navbar-fixed-top bs-docs-nav" role="banner">
<jsp:include page="/manager/leader/head.jsp"></jsp:include>
</div>
</c:if>
<div class="content" >
<c:if test="${not empty is_bi}">
<div class="sidebar"><jsp:include page="/manager/leader/left.jsp"></jsp:include>
</c:if>
</div>

<!-- Sidebar ends --> <!-- Main bar -->
<div class="mainbar"><!-- Page heading -->
<div class="page-head">
<h2 class="pull-left"><i class="icon-home"></i>零售产品结构分析</h2>
<div class="clearfix"></div>
</div>
<!-- Page heading ends --> <!-- Matter -->

<div class="matter">
<div class="container" align="center">
<!--查询条件开始-->
<div class="widget">
<div class="widget-head">
<div class="pull-left">查询条件</div>
<div class="widget-icons pull-right"><a href="#" class="wminimize"><i
	class="icon-chevron-up"></i></a> <a href="#" class="wclose"><i
	class="icon-remove"></i></a></div>
<div class="clearfix"></div>
</div>
<div class="widget-content referrer">
<table width="100%" border="0" cellspacing="5" cellpadding="0"
	class="table table-striped table-bordered table-hover">
	<tr >
		<td align="right"><strong>年月：</strong> <html-el:select property="_year"
			styleId="_year" style="width:100px" value="${_year}">
			<html-el:option value="2010">2010</html-el:option>
			<html-el:option value="2011">2011</html-el:option>
			<html-el:option value="2012">2012</html-el:option>
			<html-el:option value="2013">2013</html-el:option>
			<html-el:option value="2014">2014</html-el:option>
			<html-el:option value="2015">2015</html-el:option>
			<html-el:option value="2016">2016</html-el:option>
			<html-el:option value="2017">2017</html-el:option>
			<html-el:option value="2018">2018</html-el:option>
			<html-el:option value="2019">2019</html-el:option>
			<html-el:option value="2020">2020</html-el:option>
		</html-el:select> <html-el:select property="_month" styleId="_month"
			style="width:100px" value="${_month}">
			<html-el:option value="01">1</html-el:option>
			<html-el:option value="02">2</html-el:option>
			<html-el:option value="03">3</html-el:option>
			<html-el:option value="04">4</html-el:option>
			<html-el:option value="05">5</html-el:option>
			<html-el:option value="06">6</html-el:option>
			<html-el:option value="07">7</html-el:option>
			<html-el:option value="08">8</html-el:option>
			<html-el:option value="09">9</html-el:option>
			<html-el:option value="10">10</html-el:option>
			<html-el:option value="11">11</html-el:option>
			<html-el:option value="12">12</html-el:option>
		</html-el:select> <strong> 分公司：</strong> <html-el:select property="_dept_id"
			styleId="_dept_id" style="width:100px" value="${_dept_id}">
			<html-el:option value=" ">--请选择--</html-el:option>
			<c:forEach var="cur" items="${sybDeptInfoList}">
				<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
			</c:forEach>
		</html-el:select> <strong>数据包含：</strong> <html-el:select property="_data_type"
			styleId="_data_type" style="width:100px" value="${_month}">
			<html-el:option value="">--全部--</html-el:option>
			<html-el:option value="is_lingshou">零售</html-el:option>
			<html-el:option value="is_fenxiao">分销</html-el:option>
		</html-el:select></td>
		<td><input type="button" id="search" class="btn btn-success"
			value="搜索" /></td>
	</tr>
</table>
</div>
</div>
<!--查询条件结束-->



  <div class="row">
							<div class="col-md-4">
								<div class="widget">	
									<div class="widget-head">
										<div class="pull-left">品牌零售额占比</div>
										<div class="widget-icons pull-right">
											<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
											<a href="#" class="wclose"><i class="icon-remove"></i></a>
										</div>
										<div class="clearfix"></div>
									</div>
									<div class="widget-content referrer">
  
  
   <!-- 为ECharts准备一个具备大小（宽高）的Dom(品牌零售占比)  -->
    <div id="brand_bfb" class=""  style="height:400px;"></div>
    
    </div></div></div>
    
    
    
    <div class="col-md-4">
								<div class="widget">	
									<div class="widget-head">
										<div class="pull-left">品类零售额对比</div>
										<div class="widget-icons pull-right">
											<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
											<a href="#" class="wclose"><i class="icon-remove"></i></a>
										</div>
										<div class="clearfix"></div>
									</div>
									<div class="widget-content referrer">
  
  
  <!-- 为ECharts准备一个具备大小（宽高）的Dom （品类零售额对比）-->
    <div id="brandType_bfb" class=""  style="height:400px;width:90%;"></div>
    
    </div></div></div>
    
    
    
     
    
    <div class="col-md-4">
								<div class="widget">	
									<div class="widget-head">
										<div class="pull-left">尺寸段零售额占比</div>
										<div class="widget-icons pull-right">
											<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
											<a href="#" class="wclose"><i class="icon-remove"></i></a>
										</div>
										<div class="clearfix"></div>
									</div>
									<div class="widget-content referrer">
  
  
   <!-- 为ECharts准备一个具备大小（宽高）的Dom （尺寸段零售额占比）-->
    <div id="sizeSec_bfb" class=""  style="height:400px;"></div>
    
    </div></div></div>
    </div>
    
   
     


  
<div class="widget">	
									<div class="widget-head">
										<div class="pull-left">品牌</div>
										<div class="widget-icons pull-right">
											<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
											<a href="#" class="wclose"><i class="icon-remove"></i></a>
										</div>
										<div class="clearfix"></div>
									</div>
									<div class="widget-content referrer">  
	<div id="brandList" class="div_body" style="height:130px;">品牌信息统计正在查询 请稍等。。。。</div>
  </div> </div>
  
  
  <div class="widget">	
									<div class="widget-head">
										<div class="pull-left">品类</div>
										<div class="widget-icons pull-right">
											<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
											<a href="#" class="wclose"><i class="icon-remove"></i></a>
										</div>
										<div class="clearfix"></div>
									</div>
									<div class="widget-content referrer"> 
    <div id="brandTypeList" class="div_body" style="height:150px;">品牌类型统计正在查询 请稍等。。。。</div>
   </div> </div>
  
  
  <div class="widget">	
									<div class="widget-head">
										<div class="pull-left">尺寸</div>
										<div class="widget-icons pull-right">
											<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
											<a href="#" class="wclose"><i class="icon-remove"></i></a>
										</div>
										<div class="clearfix"></div>
									</div>
									<div class="widget-content referrer"> 
  
    <div id="sizeSecList" class="div_body" style="height:280px;">尺寸段统计正在查询 请稍等。。。。</div>
  
   </div> </div>
  
  
  <div class="widget">	
									<div class="widget-head">
										<div class="pull-left">尺寸信息</div>
										<div class="widget-icons pull-right">
											<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
											<a href="#" class="wclose"><i class="icon-remove"></i></a>
										</div>
										<div class="clearfix"></div>
									</div>
									<div class="widget-content referrer"> 
    <div id="MdSizeList" class="div_body" style="height:720px;">尺寸信息统计正在查询 请稍等。。。。</div>
  
   </div> </div>
  
  
  <div class="widget">	
									<div class="widget-head">
										<div class="pull-left">价格段</div>
										<div class="widget-icons pull-right">
											<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
											<a href="#" class="wclose"><i class="icon-remove"></i></a>
										</div>
										<div class="clearfix"></div>
									</div>
									<div class="widget-content referrer"> 
  
  
    <div id="priceDuanList" class="div_body" style="height:450px;">价格段信息正在统计中 请稍等。。。。</div>
    </div></div>
  </div>
</div>
</div>
<!-- Matter ends --></div>
</div>
<!-- Mainbar ends -->
<div class="clearfix"></div>

</div>
<!-- Content ends -->

<!-- Footer starts -->
<footer>
<jsp:include page="/manager/leader/foot.jsp"></jsp:include>
</footer>

<!-- Footer ends -->

<!-- Scroll to top -->
<span class="totop"><a href="#"><i class="icon-chevron-up"></i></a></span>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/public.js"></script>
<!--ECharts单文件引入 -->
<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
<script type="text/javascript">
$(".sidebar #nav > li > a").removeAttr("class");
$(".sidebar #nav > li > a:eq(1)").attr("class","open subdrop");

       //品牌零售额占比
    function brand_graph(data){
        var tempData=paramFormat(data);
    	 // 按需加载需要的图标插件路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
            
	    // 品牌零售占比 使用
	    require(
	        [
	            'echarts',
	            'echarts/chart/pie', // 使用柱状图就加载pie模块，按需加载
	            'echarts/chart/funnel'//饼漏斗图
	        ],
	        function (ec) {
	            // 基于准备好的dom，初始化echarts图表
	            var myChart = ec.init(document.getElementById('brand_bfb')); 
	            
	            var option = {
	            	title : {
	                text: tempData.titleText,
	                x:'left'
	            },
	            tooltip : {
	                trigger: 'item',
	                formatter: "{a} <br/>{b} : {c} ({d}%)"
	            },
	            toolbox: {
	                show : true,
	                feature : {
	                    mark : {show: true},
	                    dataView : {show: true, readOnly: false},
	                    magicType : {
	                        show: true, 
	                        type: ['pie', 'funnel'],
	                        option: {
	                            funnel: {
	                                x: '25%',
	                                width: '50%',
	                                funnelAlign: 'left',
	                                max: 1548
	                            }
	                        }
	                    },
	                    restore : {show: true},
	                    saveAsImage : {show: true}
	                }
	            },
	            calculable : true,
	            series : [
	                {
	                    name:'品牌零售额占比',
	                    type:'pie',
	                    radius : '55%',
	                    center: ['50%', '60%'],
	                    data:tempData.nameValue
	                }
	            ]
	        };
	            // 为echarts对象加载数据 
	            myChart.setOption(option); 
	        }
	    );
    }
    //品类零售额对比
    function brand_type_graph(data){
    	var tempData=paramFormat1(data);
    	 // 按需加载需要的图标插件路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
            
	    // 品类零售额对比  使用
	    require(
	        [
	            'echarts',
	            'echarts/chart/radar', // 使用雷达图模块，按需加载
	        ],
	        function (ec) {
	            // 基于准备好的dom，初始化echarts图表
	            var myChart = ec.init(document.getElementById('brandType_bfb')); 
	            
	            option = {
	            	    title : {
	            	        text: tempData.titleText,
	            	        x:'left'
	            	    },
	            	    tooltip : {
	            	        trigger: 'axis'
	            	    },
	            	    toolbox: {
	            	        show : true,
	            	        feature : {
	            	            mark : {show: true},
	            	            dataView : {show: true, readOnly: false},
	            	            restore : {show: true},
	            	            saveAsImage : {show: true}
	            	        }
	            	    },
	            	    calculable : true,
	            	    polar : [
	            	        {
	            	            indicator :tempData.textMax ,
	            	            radius : 130
	            	        }
	            	    ],
	            	    series : [
	            	        {
	            	            name: '品类零售额对比 ',
	            	            type: 'radar',
	            	            itemStyle: {
	            	                normal: {
	            	                    areaStyle: {
	            	                        type: 'default'
	            	                    }
	            	                }
	            	            },
	            	            data : tempData.nameValue
	            	        }
	            	    ]
	            	};
	            // 为echarts对象加载数据 
	            myChart.setOption(option); 
	        }
	    );
    }
    //尺寸段零售额占比
    function sizeSec_graph(data){
    	var tempData=paramFormat(data);
    	 // 按需加载需要的图标插件路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
            
	    // 尺寸段零售额占比    使用
	    require(
	        [
	            'echarts',
	            'echarts/chart/pie'//饼漏斗图
	        ],
	        function (ec) {
	            // 基于准备好的dom，初始化echarts图表
	            var myChart = ec.init(document.getElementById('sizeSec_bfb')); 
	            
	            var option = {
	            title : {
	                text: tempData.titleText,
	                x:'left'
	            },
	            tooltip : {
	                trigger: 'item',
	                formatter: "{a} <br/>{b} : {c} ({d}%)"
	            },
	            toolbox: {
	                show : true,
	                feature : {
	                    mark : {show: true},
	                    dataView : {show: true, readOnly: false},
	                    magicType : {
	                        show: true, 
	                        type: ['pie', 'funnel']
	                    },
	                    restore : {show: true},
	                    saveAsImage : {show: true}
	                }
	            },
	            calculable : true,
	            series : [
	                {
	                    name:'尺寸段零售额占比',
	                    type:'pie',
	                    radius : [30, 110],
	                    center : ['50%', 200],
	                    roseType : 'area',
	                    x: '50%',               // for funnel
	                    max: 40,                // for funnel
	                    sort : 'ascending',     // for funnel
	                    data:tempData.nameValue
	                }
	            ]
	        };
	            // 为echarts对象加载数据 
	            myChart.setOption(option); 
	        }
	    );
    }

    //处理成需要的数据
    function paramFormat(data){
         var tempData={};
         //tempData.titletext=$("").val();
         var titleTemp=[];//名字数组
         var nameValueTemp=[];//名字和金额数组
         data=data.rows;
         for(var i=0;i<data.length;i++){
             var  tempdata=data[i];
             titleTemp[i]=tempdata.TYPENAME;//类型名称
        	 
        	 var nameValueObj={};//名字和金额
        	     nameValueObj.name=tempdata.TYPENAME;
        	     nameValueObj.value=tempdata.CUR_MONTH_MONEY==null?0:tempdata.CUR_MONTH_MONEY;
        	     nameValueTemp[i]=nameValueObj;
         }
         tempData.title=titleTemp;
         tempData.nameValue=nameValueTemp;
         tempData.titleText=data.titleText;
         return tempData;
    }

    //处理成需要的数据   雷达图
    function paramFormatx(data){
        var tempData={};
        //tempData.titletext=$("").val();
        var titleTemp=[];//名字数组
        var nameValueTemp=[];//名字和金额数组
        
        data=data.rows;
        for(var i=0;i<data.length;i++){
            var  tempdata=data[i];
            titleTemp[i]=tempdata.TYPENAME;//类型名称
       	 var nameValueObj={};//名字和金额
       	     nameValueObj.name=tempdata.TYPENAME;

       	 var tempvalue=[];
			tempvalue[0]=tempdata.YESTERDAY_MONEY==null?0:tempdata.YESTERDAY_MONEY;
			tempvalue[1]=tempdata.CUR_MONTH_MONEY==null?0:tempdata.CUR_MONTH_MONEY;
			tempvalue[2]=tempdata.UP_MONTH_MONEY==null?0:tempdata.UP_MONTH_MONEY;
			tempvalue[3]=tempdata.YEAR_MONEY==null?0:tempdata.YEAR_MONEY;
       	     nameValueObj.value=tempvalue;
       	     nameValueTemp[i]=nameValueObj;
        }
        var max_YESTERDAY_MONEY=0;
        var max_CUR_MONTH_MONEY=0;
        var max_UP_MONTH_MONEY=0;
        var max_YEAR_MONEY=0;
        for(var j=0;j<nameValueTemp.length;j++){
             var maxdata=nameValueTemp[j].value;
             if(maxdata[0]>max_YESTERDAY_MONEY){max_YESTERDAY_MONEY=maxdata[0];}
		     if(maxdata[1]>max_CUR_MONTH_MONEY){max_CUR_MONTH_MONEY=maxdata[1];}
		     if(maxdata[2]>max_UP_MONTH_MONEY){max_UP_MONTH_MONEY=maxdata[2];}
		     if(maxdata[3]>max_YEAR_MONEY){max_YEAR_MONEY=maxdata[3];}
        }
        tempData.maxvalue={'max_YESTERDAY_MONEY':max_YESTERDAY_MONEY,'max_CUR_MONTH_MONEY':max_CUR_MONTH_MONEY,'max_UP_MONTH_MONEY':max_UP_MONTH_MONEY,'max_YEAR_MONEY':max_YEAR_MONEY};
        tempData.title=titleTemp;
        tempData.nameValue=nameValueTemp;
        tempData.titleText=data.titleText;
        return tempData;
   }
    //处理成需要的数据   雷达图
    function paramFormat1(data){
        var tempData={};
        //tempData.titletext=$("").val();
        var titleTemp=['昨日','当月','上月','年度'];//名字数组
        var nameValueTemp=[];//名字和金额数组
        
        data=data.rows;
       
    	var nameValueObj0={};//名字和金额
    	var nameValueObj1={};//名字和金额
    	var nameValueObj2={};//名字和金额
    	var nameValueObj3={};//名字和金额
   	 	var tempvalue0=[]; 
   	 	var tempvalue1=[]; 
   		var tempvalue2=[]; 
   		var tempvalue3=[]; 
   		var textMax=[];
        for(var i=0;i<data.length;i++){
           	var row= data[i];    
			tempvalue0[i]=row.YESTERDAY_MONEY==null?0:row.YESTERDAY_MONEY;
			tempvalue1[i]=row.CUR_MONTH_MONEY==null?0:row.CUR_MONTH_MONEY;
			tempvalue2[i]=row.UP_MONTH_MONEY==null?0:row.UP_MONTH_MONEY;
			tempvalue3[i]=row.YEAR_MONEY==null?0:row.YEAR_MONEY; 
			var _textMax={};
			_textMax.text=row.TYPENAME;
			_textMax.max=row.YEAR_MONEY==null?0:row.YEAR_MONEY; 
			textMax[i]=_textMax;
        }
        nameValueObj0.name ='昨日'; 
        nameValueObj1.name ='当月'; 
        nameValueObj2.name ='上月'; 
        nameValueObj3.name ='年度'; 
        nameValueObj0.value=tempvalue0; 
        nameValueObj1.value=tempvalue1; 
        nameValueObj2.value=tempvalue2; 
        nameValueObj3.value=tempvalue3; 
        nameValueTemp[0] =nameValueObj0; 
        nameValueTemp[1] =nameValueObj1; 
        nameValueTemp[2] =nameValueObj2; 
        nameValueTemp[3] =nameValueObj3; 

        tempData.nameValue=nameValueTemp;
        tempData.title=titleTemp;
        tempData.titleText=data.titleText;
        tempData.textMax=textMax;
        return tempData;
   }
</script>

<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
		brandList();
		brandTypeList();
		sizeSecList();
		MdSizeList();
	    priceDuanList();
	$("#search").click( function () { 
		brandList();
		brandTypeList();
		sizeSecList();
		MdSizeList();
		priceDuanList();
	});
});
//品牌
 function brandList(){
	 var param='method=brandList';
	 var _year=$("#_year").val();
	 var _month=$("#_month").val();
	 var _dept_id=$("#_dept_id").val();
	 var _data_type=$("#_data_type").val();
	 if(_year!=''&&_year!=null&_month!=''&_month!=null){param+='&_year='+_year+'&_month='+_month;}
	 if(_dept_id!=''&&_dept_id!=null){param+='&_dept_id='+_dept_id;}
	 if(_data_type!=''&&_data_type!=null){param+='&_data_type='+_data_type;}
	 $.ajax({
		   type: "POST",
		   url: "${ctx}/manager/leader/jcfxReportLscp.do",
		   data: param,
		   success: function(result){
			   $("#brandList").empty();
			   $("#brandList").append(showlist(result));
			   //图形显示
			    brand_graph(result);
		   },
		   error:function(){
			   alert("品牌信息加载失败！");
		   }
		});
}
//品类
 function brandTypeList(){
	 var param='method=brandTypeList';
	 var _year=$("#_year").val();
	 var _month=$("#_month").val();
	 var _dept_id=$("#_dept_id").val();
	 var _data_type=$("#_data_type").val();
	 if(_year!=''&&_year!=null&_month!=''&_month!=null){param+='&_year='+_year+'&_month='+_month;}
	 if(_dept_id!=''&&_dept_id!=null){param+='&_dept_id='+_dept_id;}
	 if(_data_type!=''&&_data_type!=null){param+='&_data_type='+_data_type;}
	 $.ajax({
		   type: "POST",
		   url: "${ctx}/manager/leader/jcfxReportLscp.do",
		   data: param,
		   success: function(result){
			   $("#brandTypeList").empty();
			   $("#brandTypeList").append(showlist(result));
			   brand_type_graph(result); 
		   },
		   error:function(){
			   alert("品类信息加载失败！");
		   }
		});
}
//尺寸段
 function sizeSecList(){
	 var param='method=sizeSecList';
	 var _year=$("#_year").val();
	 var _month=$("#_month").val();
	 var _dept_id=$("#_dept_id").val();
	 var _data_type=$("#_data_type").val();
	 if(_year!=''&&_year!=null&_month!=''&_month!=null){param+='&_year='+_year+'&_month='+_month;}
	 if(_dept_id!=''&&_dept_id!=null){param+='&_dept_id='+_dept_id;}
	 if(_data_type!=''&&_data_type!=null){param+='&_data_type='+_data_type;}
	 $.ajax({
		   type: "POST",
		   url: "${ctx}/manager/leader/jcfxReportLscp.do",
		   data: param,
		   success: function(result){
			   $("#sizeSecList").empty();
			   $("#sizeSecList").append(showlist(result));
			   sizeSec_graph(result);
		   },
		   error:function(){
			   alert("尺寸段信息加载失败！");
		   }
		});
}
//尺寸列表
 function MdSizeList(){
	 var param='method=MdSizeList';
	 var _year=$("#_year").val();
	 var _month=$("#_month").val();
	 var _dept_id=$("#_dept_id").val();
	 var _data_type=$("#_data_type").val();
	 if(_year!=''&&_year!=null&_month!=''&_month!=null){param+='&_year='+_year+'&_month='+_month;}
	 if(_dept_id!=''&&_dept_id!=null){param+='&_dept_id='+_dept_id;}
	 if(_data_type!=''&&_data_type!=null){param+='&_data_type='+_data_type;}
	 $.ajax({
		   type: "POST",
		   url: "${ctx}/manager/leader/jcfxReportLscp.do",
		   data: param,
		   success: function(result){
			   $("#MdSizeList").empty();
			   $("#MdSizeList").append(showlist(result));
		   },
		   error:function(){
			   alert("尺寸信息加载失败！");
		   }
		});
}
//价格段列表
 function priceDuanList(){
	 var param='method=priceDuanList';
	 var _year=$("#_year").val();
	 var _month=$("#_month").val();
	 var _dept_id=$("#_dept_id").val();
	 var _data_type=$("#_data_type").val();
	 if(_year!=''&&_year!=null&_month!=''&_month!=null){param+='&_year='+_year+'&_month='+_month;}
	 if(_dept_id!=''&&_dept_id!=null){param+='&_dept_id='+_dept_id;}
	 if(_data_type!=''&&_data_type!=null){param+='&_data_type='+_data_type;}
	 $.ajax({
		   type: "POST",
		   url: "${ctx}/manager/leader/jcfxReportLscp.do",
		   data: param,
		   success: function(result){
			   $("#priceDuanList").empty();
			   $("#priceDuanList").append(showlist(result));
		   },
		   error:function(){
			   alert("价格段信息加载失败！");
		   }
		});
}

function showlist(result){
	var tempStr='';
	 var rows=result.rows;
	 tempStr= '<table>'
		 +'<tr class="">'
	     +'				<td colspan="2"></td>'
	     +'				<td colspan="5">零售额（单位：万元）</td>'
	     +'				<td ></td>'
	     +'				<td colspan="5">零售量（单位：万元）</td>'
	     +'				<td ></td>'
	     +'				<td colspan="5">均价（单位：千元）</td>'
	     +'	</</tr>'
        +'<tr class="tr_title">'
        +'				<td class="tr_td_name">品牌</td>'
        +'				<td class="tr_kb"></td>'
        +'				<td align="right">昨日</td>'
        +'				<td align="right">当月</td>'
        +'				<td align="right">同期</td>'
        +'				<td align="right">同期增长率</td>'
		+'				<td align="right">年</td>'
        +'				<td class="tr_kb"></td>'
        +'				<td align="right">昨日</td>'
        +'				<td align="right">当月</td>'
        +'				<td align="right">同期</td>'
        +'				<td align="right">同期增长率</td>'
        +'				<td align="right">年</td>'
		+'				<td class="tr_kb"></td>'
        +'				<td align="right">昨日</td>'
        +'				<td align="right">当月</td>'
        +'				<td align="right">年度</td>'
        +'	</tr>';


		$.each(rows, function(i, n){
			var row=rows[i];
			tempStr+='<tr>'
		        	  +'				<td class="tr_td_name">'+nullformat(row.TYPENAME)+'</td>'
	       			  +'				<td class="tr_kb"></td>'
		        	  +'				<td align="right">'+numformat(row.YESTERDAY_MONEY)+'</td>'
		        	  +'				<td align="right">'+numformat(row.CUR_MONTH_MONEY)+'</td>'
		        	  +'				<td align="right">'+numformat(row.UP_MONTH_MONEY)+'</td>'
		        	  +'				<td align="right">'+tongqi(row.CUR_MONTH_MONEY,row.UP_MONTH_MONEY)+'</td>'
		        	  +'				<td class="" align="right">'+numformat(row.YEAR_MONEY)+'</td>'
		        	  +'				<td class="tr_kb"></td>'
		        	  +'				<td align="right">'+nullformat(row.YESTERDAY_NUM)+'</td>'
		        	  +'				<td>'+ nullformat(row.CUR_MONTH_NUM)+'</td>'
		        	  +'				<td align="right">'+nullformat(row.UP_MONTH_NUM)+'</td>'
		        	  +'				<td align="right">'+tongqi(row.CUR_MONTH_NUM,row.UP_MONTH_NUM)+'</td>'
		        	  +'				<td  class="" align="right">'+nullformat(row.YEAR_NUM)+'</td>'
		        	  +'				<td class="tr_kb"></td>'
		        	  +'				<td align="right">'+ numformat(row.YESTERDAY_AVG_PRICE*10000)+'</td>'
		        	  +'				<td align="right">'+numformat(row.CUR_MONTH_AVG_PRICE*10000)+'</td>'
		        	  +'				<td  class="" align="right">'+numformat(row.YEAR_AVG_PRICE*10000)+'</td>'
		        	  +'</tr>';
		});
	 tempStr+='</table>';
	 return tempStr;
}
function tongqi(cur,up){
	if(isNaN(cur)||cur==null){cur = 0;}
	if(isNaN(up)||up==null){up= 0;}
	if(cur==0&&up==0){return '0.00%';}
	if(cur==up){return "0.00%";}
	if(up!=0){
		  return (numformat((cur-up)/up*100))+'%';
	}else{
			if(cur==0){
				return '0.00%';
			}else{
				return 100.00+'%';
			}
	}
}
// 处理数字为空的时候默认为0
function nullformat(num){
	if(null==num){
         return 0;
	}else{
       return num;
	}
}
//格式化数字
function numformat(num){
	if(null!=num){
	   return parseFloat(num).toFixed(2);
	}else{
      return 0;
	}
}
function get_up_money_month_bfb(value,rec){
	if(rec.UP_MONTH_MONEY !=0){
		 return (rec.CUR_MONTH_MONEY-rec.UP_MONTH_MONEY)/rec.UP_MONTH_MONEY;
	}else{
         return 0;
	}
}
function get_up_num_month_bfb(value,rec){
	if(rec.UP_MONTH_MONEY !=0){
		 return (rec.CUR_MONTH_NUM-rec.UP_MONTH_NUM)/rec.UP_MONTH_NUM;
	}else{
         return 0;
	}
}
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
//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
