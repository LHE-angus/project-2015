<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>客户库存周转</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<style type="text/css">
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
.rtable1 td {
	padding:0px 2px;
}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <html-el:form action="/admin/JcfxReportKczzfx">
  <div class="rtabcont1">
      <html-el:hidden property="method" value="view2" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="isfirst" value="first"/>
      <html-el:hidden property="c_index" styleId="c_index" value="${af.map.c_index}"/>
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
       	<tr>
       		<td align="right"><strong class="fb">年月：</strong></td>
       		<td>
       			 <html-el:select property="year" styleId="year">
	              <c:forEach items="${yearList}" var="cur">
	                <html-el:option value="${cur}">${cur}年</html-el:option>
	              </c:forEach>
	            </html-el:select>
	            <html-el:select property="month" styleId="month">
	              <html-el:option value="01">1月</html-el:option>
	              <html-el:option value="02">2月</html-el:option>
	              <html-el:option value="03">3月</html-el:option>
	              <html-el:option value="04">4月</html-el:option>
	              <html-el:option value="05">5月</html-el:option>
	              <html-el:option value="06">6月</html-el:option>
	              <html-el:option value="07">7月</html-el:option>
	              <html-el:option value="08">8月</html-el:option>
	              <html-el:option value="09">9月</html-el:option>
	              <html-el:option value="10">10月</html-el:option>
	              <html-el:option value="11">11月</html-el:option>
	              <html-el:option value="12">12月</html-el:option>
	            </html-el:select>
       		</td>
       		<td>
       		分&nbsp;公&nbsp;司：
            	<html-el:select property="branch_area_name_2" styleId="branch_area_name_2"
				style="width:100px" value="${branch_area_name_2}">
				<html-el:option value=" ">--请选择--</html-el:option>
				<c:forEach var="cur" items="${sybDeptInfoList}">
					<html-el:option value="${cur.dept_sn}">${cur.dept_name}</html-el:option>
				</c:forEach>
			</html-el:select>
       		</td>
       		<td>
       		<input name="button" type="button" class="bgSearch" id="button" value="搜 索" style="font-family:Microsoft YAHEI;" />
<!--	         <input class="but_excel" type="button" name="export_excel" disabled="true" id="export_excel" value="导出" />-->
	         <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
       		</td>
       	</tr>
      </table>
  </div><!--
  <div style="text-align: left;padding-left: 10px">
  	<input name="button" type="button" class="bgSearch" id="button" value="搜 索" style="font-family:Microsoft YAHEI;" />
	<input class="but_excel" type="button" name="export_excel" disabled="disabled" id="export_excel" value="导出" />
  </div>
  --></html-el:form>
 <div class="rtabcont_img" style="width: 100%" >
    <div id="jcfx_cust_scatter" class=""  style="height:400px;width:100%;float: right;">2</div>
    <div align="center" style="color: red;"> X轴代表周转天数，Y代表期末库存</div>
  </div>
  <div class="rtabcont1" style="overflow-x:auto;">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
        <td nowrap="nowrap" width="150px;" align="right" colspan="10" >单位：万元</td>
      </tr>
      <tr class="tabtt1" style="font-size: 200px;" >
        <td width="5%" nowrap="nowrap" align="center">序号</td>
        <td nowrap="nowrap" align="center" >合并R3编码</td>
        <td  nowrap="nowrap" align="left">客户名称</td> 
        <td nowrap="nowrap" align="left" >所属渠道</td>
        <td  nowrap="nowrap" align="right">期初库存</td> 
        <td nowrap="nowrap" align="right" >本期进货</td>
        <td nowrap="nowrap" width="150px;" align="right">本期销售</td>
        <td nowrap="nowrap" width="150px;" align="right">期末库存</td>
        <td nowrap="nowrap" width="150px;" align="center">本月周转天数</td>
        <td nowrap="nowrap" width="150px;" align="center">目标周转天数</td>
      </tr>
      <c:forEach items="${entityList}" var="cur" varStatus="vs">
      <tr>
      	<td align="center" nowrap="nowrap">${vs.count}</td>
      	<td align="left" nowrap="nowrap">${cur.R3_CODE}</td>
      	<td align="left" nowrap="nowrap"><a style="color: blue;" href="${ctx}/manager/admin/JStocksSummary.do?type=1&mod_id=220720&r3_code_like=${cur.R3_CODE}&year=${af.map.year}&month=${af.map.month}">${cur.CUSTOMER_NAME}</a></td>
      	<td align="left" nowrap="nowrap">${cur.CUST_TYPE}</td>
      	<td align="right" nowrap="nowrap">${cur.INIT_MONEY}</td>
        <td align="right" nowrap="nowrap">${cur.COMM_MONEY}</td>
        <td align="right" nowrap="nowrap">${cur.OUT_MONEY}</td>
        <td align="right" nowrap="nowrap">${cur.END_MONEY}</td> 
        <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.CUR_DAY}" pattern="0" /></td> 
        <td align="right" nowrap="nowrap"> <fmt:formatNumber value="${cur.TARGET_DAY}" pattern="0" /></td> 
      </tr>
      </c:forEach>
    </table>
    <c:if test="${not vs.last}">
      <div style="height:10px;"></div>
    </c:if>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
<!--ECharts单文件引入 -->
<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
<script type="text/javascript">//<![CDATA[
function set_jcfx_cust_scatter(data){
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
            'echarts/chart/scatter', // 散点图
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var myChart = ec.init(document.getElementById('jcfx_cust_scatter')); 
            
            var option = {
            	title : {
                text: '客户库存周转散点图'
            },
            tooltip : {
                trigger: 'axis',
                showDelay : 0,
                axisPointer:{
                    type : 'cross',
                    lineStyle: {
                        type : 'dashed',
                        width : 1
                    }
                }
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataZoom : {show: true},
                    dataView : {show: true, readOnly: false},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            xAxis : [
                {
                    type : 'value',
                    scale:true,
                    axisLabel : {
                        formatter: '{value} 天'
                    }
                }
            ],
            yAxis : [
                {
                    type : 'value',
                    scale:true,
                    axisLabel : {
                        formatter: '{value} 万元'
                    }
                }
            ]
        };
            option.series=tempData.series; 
            // 为echarts对象加载数据 
            myChart.setOption(option); 
        }
    );
}

//处理成需要的数据
function paramFormat(data){
     var tempData={};
       var listdata= data.entityList;
       var series = new Array();
       $.each(listdata, function(i, n){
    	   var templistkh=listdata[i];
    	   var kh={
              name:templistkh.CUSTOMER_NAME,
               type:'scatter',
               tooltip : {
                   trigger: 'item',
                   formatter : function (params) {
                       if (params.value.length > 1) {
                    	   return params.seriesName + ' :<br/>'
                           + params.name + ' 周转天数（天）: '
                           + params.value[0] + '' + '<br/>库存金额（万）: '
                           + params.value[1];
                       }
                       else {
                           return params.seriesName + ' :<br/>'
                              + params.name + ' : '
                              + params.value + '万';
                       }
                   }
               },
               data: [[numformat(templistkh.CUR_DAY),numformat(templistkh.END_MONEY),i]]

               
           };
    	   series[i]=kh;
    	 });
     tempData.series=series;
     return tempData;
}

                                          
$(document).ready(function(){
	 var paramCust='method=ajaxview2';
	 var year='${af.map.year}';
	 var month='${af.map.month}';
	 var c_index='${af.map.c_index}';
	 var branch_area_name_2='${af.map.branch_area_name_2}';
	 if(year!=''&&year!=null&month!=''&month!=null){paramCust+='&year='+year+'&month='+month;}
	 if(branch_area_name_2!=''&&branch_area_name_2!=null){paramCust+='&branch_area_name_2='+branch_area_name_2;}
	 if(c_index!=''&&c_index!=null){paramCust+='&c_index='+c_index;}else{alert("渠道信息获取失败!");}
		//客户库存周转散点图
	 $.ajax({
		   type: "POST",
		   url: "${ctx}/manager/admin/JcfxReportKczzfx.do",
		   data: paramCust,
		   success: function(result){
			   $("#jcfx_cust_scatter").empty();
				set_jcfx_cust_scatter(result);
		   },
		   error:function(){
			   alert("客户库存周转散点图加载失败！");
		   }
		});
		
$("#button").click(function(){
	if (Validator.Validate(this.form, 1)){
		this.form.submit();
		}
});
});
//格式化数字
function numformat(num){
	if(null!=num){
	   return parseFloat(num).toFixed(0);
	}else{
      return 0;
	}
}
//导出excel
$("#export_excel").click(function(){
	if(confirm("提示，您确认导出数据？")){
		$("#export_id").val(1);
		$("#bottomPageForm").submit();
	}
	$("#export_id").val(0);
});
//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
