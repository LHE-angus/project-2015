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
table{width: 100%}
 tr{text-align: center;}
.tr_title{background-color:#4731A3;color:white;font-size: 14px;}
.tr_kb{padding-left:10px;padding-right:10px;font-size: 12px;background-color:white;}
.tr_desc{font-size: 20px;}
.tr_td_name{font-weight:bold}
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
<body style="font-family:Microsoft Yahei;">
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr align="left">
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
       	<tr align="right">
       	    <td>年月：
       	      <html-el:select property="_year" styleId="_year"
				style="width:100px" value="${_year}">
					<html-el:option  value="2010">2010</html-el:option >
					<html-el:option  value="2011">2011</html-el:option >
					<html-el:option  value="2012">2012</html-el:option >
					<html-el:option  value="2013">2013</html-el:option >
	      		   <html-el:option  value="2014">2014</html-el:option >
	      		   <html-el:option  value="2015">2015</html-el:option >
	      		   <html-el:option  value="2016">2016</html-el:option >
	      		   <html-el:option  value="2017">2017</html-el:option >
	      		   <html-el:option  value="2018">2018</html-el:option >
	      		   <html-el:option  value="2019">2019</html-el:option >
	      		   <html-el:option  value="2020">2020</html-el:option >
			</html-el:select>
			
			<html-el:select property="_month" styleId="_month"
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
			</html-el:select>
       		</td>
       		<td>
       		分&nbsp;公&nbsp;司：
            	<html-el:select property="_dept_id" styleId="_dept_id"
				style="width:100px" value="${_dept_id}">
				<html-el:option value=" ">--请选择--</html-el:option>
				<c:forEach var="cur" items="${sybDeptInfoList}">
					<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
				</c:forEach>
			</html-el:select>
       		</td>
       		<td>
       		数据包含：
       		   <html-el:select property="_data_type" styleId="_data_type"
				style="width:100px" value="${_month}">
				<html-el:option value="">--全部--</html-el:option>
	       		    <html-el:option value="is_lingshou">零售</html-el:option>
	       		    <html-el:option value="is_fenxiao">分销</html-el:option>
			   </html-el:select>
       		</td>
       		<td>
              <input type="button"  id="search" value="搜索"/>
       		</td>
       	</tr>
      </table>
  </div>
  <div class="rtabcont1">
		<%@ include file="/commons/pages/messages.jsp"%>
  </div>
  <div align="center" >
   <!-- 为ECharts准备一个具备大小（宽高）的Dom(品牌零售占比)  -->
    <div id="brand_bfb" class=""  style="height:400px;width:33.3%;float: left">1</div>
     <!-- 为ECharts准备一个具备大小（宽高）的Dom （品类零售额对比）-->
    <div id="brandType_bfb" class=""  style="height:400px;;width:33.3%;float: left">2</div>
     <!-- 为ECharts准备一个具备大小（宽高）的Dom （尺寸段零售额占比）-->
    <div id="sizeSec_bfb" class=""  style="height:400px;width:33.3%;float: left">3</div>
  </div>
  <div class="" align="center" style="width: 100%">
	<div id="brandList" class="div_body">品牌信息统计正在查询 请稍等。。。。</div>
    <div id="brandTypeList" class="div_body">品牌类型统计正在查询 请稍等。。。。</div>
    <div id="sizeSecList" class="div_body">尺寸段统计正在查询 请稍等。。。。</div>
    <div id="MdSizeList" class="div_body">尺寸信息统计正在查询 请稍等。。。。</div>
    <div id="priceDuanList" class="div_body">价格段信息正在统计中 请稍等。。。。</div>
  </div>
</div>
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
	                    name:'访问来源',
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
	            	            indicator : [
								{text : '昨日零售额', max  : tempData.maxvalue.max_YESTERDAY_MONEY},
								{text : '当月零售额', max  : tempData.maxvalue.max_CUR_MONTH_MONEY},
								{text : '同期零售额', max  : tempData.maxvalue.max_UP_MONTH_MONEY},
								{text : '年度零售额', max  : tempData.maxvalue.max_YEAR_MONEY}
	            	            ],
	            	            radius : 130
	            	        }
	            	    ],
	            	    series : [
	            	        {
	            	            name: '品类零售额数据',
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
	                    name:'面积模式',
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
        	     nameValueObj.value=tempdata.CUR_MONTH_MONEY;
        	     nameValueTemp[i]=nameValueObj;
         }
         tempData.title=titleTemp;
         tempData.nameValue=nameValueTemp;
         tempData.titleText=data.titleText;
         return tempData;
    }

    //处理成需要的数据   雷达图
    function paramFormat1(data){
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
		   url: "${ctx}/manager/admin/jcfxReportLscp.do",
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
		   url: "${ctx}/manager/admin/jcfxReportLscp.do",
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
		   url: "${ctx}/manager/admin/jcfxReportLscp.do",
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
		   url: "${ctx}/manager/admin/jcfxReportLscp.do",
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
		   url: "${ctx}/manager/admin/jcfxReportLscp.do",
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
	 tempStr= '<table><tr ><td class="tr_desc tr_td_name">'
		+ (result.title?result.title:'') +'</td><td class="tr_kb"></td><td colspan="5" class="tr_desc1">'
		+ (result.title1?result.title1:'')+'</td><td class="tr_kb"></td><td colspan="5" class="tr_desc1">'
		+ (result.title2?result.title2:'')+'</td><td class="tr_kb"></td><td colspan="5" class="tr_desc1">'
		+ (result.title3?result.title3:'')+'</td></tr>'
		
        +'<tr class="tr_title"><td align="left" class="tr_td_name">品牌</td><td align="right" class="tr_kb"></td><td align="right" >昨日零售额</td><td align="right" >当月零售额</td><td align="right">同期零售额</td><td align="right">同期增长率</td>'
		+'<td align="right">年度累计零售额</td><td class="tr_kb" align="right"></td><td align="right">昨日零售量</td><td align="right">当月零售量</td><td align="right">同期零售量</td><td align="right">同期增长率</td><td align="right">年度累计零售量</td>'
		+'<td class="tr_kb" align="right"></td><td align="right">昨日均价</td><td align="right">当月均价</td><td align="right">年度均价</td></tr>';


		$.each(rows, function(i, n){
			var row=rows[i];
			tempStr+='<tr><td class="tr_td_name">'+nullformat(row.TYPENAME)+'</td><td class="tr_body" align="right"></td><td align="right">'+nullformat(row.YESTERDAY_MONEY)+'</td><td  align="right">'+nullformat(row.CUR_MONTH_MONEY)+'</td><td  align="right">'+nullformat(row.UP_MONTH_MONEY)
					+'</td><td align="right">'+tongqi(row.CUR_MONTH_MONEY,row.UP_MONTH_MONEY)+'</td><td class="tr_td_name" align="right">'+nullformat(row.YEAR_MONEY)+'</td><td class="tr_body" align="right"></td><td align="right">'+nullformat(row.YESTERDAY_NUM)+'</td><td align="right">'
					+ nullformat(row.CUR_MONTH_NUM)+'</td><td align="right">'+nullformat(row.UP_MONTH_NUM)+'</td><td align="right">'+tongqi(row.CUR_MONTH_NUM,row.UP_MONTH_NUM)+'</td><td  align="right" class="tr_td_name">'+nullformat(row.YEAR_NUM)+'</td><td class="tr_body"></td><td  align="right">'
					+ numformat(row.YESTERDAY_AVG_PRICE)+'</td><td align="right">'+numformat(row.CUR_MONTH_AVG_PRICE)+'</td><td  align="right" class="tr_td_name">'+numformat(row.YEAR_AVG_PRICE)+'</td></tr>';
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
//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
