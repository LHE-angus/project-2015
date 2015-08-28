<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
</head>
<body>
<div class="oarcont" id="body_oarcont" style="position:relative;overflow:hidden;">

<div class="rtabcont1">
  <html-el:form action="/admin/KonkaR3ShopHomePage">
    <html-el:hidden property="method" value="list" />
    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
    <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
      <tr>
          <td width="10%" nowrap="nowrap"><strong class="fb">&nbsp;&nbsp;&nbsp;&nbsp;客户类型：</strong>
          		<html-el:select property="customer_par_index" styleId="customer_par_index" style="width:200px;" onchange="customer_par_index_chg();">
          		    <html-el:option value="">请选择...</html-el:option>
          			<c:forEach items="${konkaCategoryList}" var="cur" varStatus="vs">
          				<c:if test="${not empty cur.map.c_comm}">
          					<html-el:option value="${cur.map.par_index}">${cur.map.c_comm}</html-el:option>
          				</c:if>
          			</c:forEach>
          		</html-el:select>
          		&nbsp;<strong class="fb">客户细分类型：</strong>
          		<select name="customer_type" id="customer_type" style="width:200px;">
	              <option value="">请选择...</option>
	            </select>
          </td>
      </tr>
      <tr>
          <td width="10%" nowrap="nowrap"><strong class="fb">&nbsp;&nbsp;&nbsp;&nbsp;创建时间：</strong>
          	<html-el:text property="add_date_start" styleId="add_date_start"  size="9" maxlength="10" readonly="true" onclick="new Calendar(1980, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
			至
			<html-el:text property="add_date_end" styleId="add_date_end"  size="9" maxlength="10" readonly="true" onclick="new Calendar(1980, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
            &nbsp;<strong class="fb">当年结算规模：</strong>
          		<html-el:text property="r3_money_gt" styleId="r3_money_gt" size="9" styleClass="webinput" onfocus="javascript:setOnlyInt(this);" />
          		 -&nbsp;
          		<html-el:text property="r3_money_lt" styleId="r3_money_lt" size="9" styleClass="webinput" onfocus="javascript:setOnlyInt(this);" />&nbsp;万元
          		&nbsp;&nbsp;<html-el:submit styleId="btn_submit" styleClass="but1">搜索</html-el:submit>
          </td>
      </tr>
    </table>
  </html-el:form>
  </div>
  <div class="rtabcont1">
      <a href="${ctx}/manager/admin/KonkaR3ShopHomePage.do" style="background: #FF6600;color:#fff;padding:3px;">客户分布</a>&nbsp;&nbsp;<a href="${ctx}/manager/admin/KonkaR3StoreHomePage.do">门店分布</a>
  	  &nbsp;&nbsp;&nbsp;&nbsp;
      <a id="map_zoom" href="javascript:map_zoom(1);" style="padding:5px;color:#fff;background:blue;">地图缩放</a>
  </div>
  <div class="rtabcont1">
      <div id="main" style="width:700px;height:400px;border:1px solid #ccc;padding:10px;"></div>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script src="${ctx}/commons/scripts/echarts/esl.js"></script>
<script type="text/javascript">//<![CDATA[
   $(document).ready(function() {
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
		customer_par_index_chg();
		map_show();
   }); 
   
   function map_zoom(map_size){
	    if(map_size == '1'){
	    	$("#main").removeAttr("style");
		    $("#main").attr("style","width:900px;height:600px;border:1px solid #ccc;padding:10px;");
		    $("#map_zoom").removeAttr("href");
		    $("#map_zoom").attr("href","javascript:map_zoom(0);");
	    }else {
	    	$("#main").removeAttr("style");
		    $("#main").attr("style","width:700px;height:400px;border:1px solid #ccc;padding:10px;");
		    $("#map_zoom").removeAttr("href");
		    $("#map_zoom").attr("href","javascript:map_zoom(1);");
	    }
	    map_show();
  }
   
 //类别-连动-客户类别
   function customer_par_index_chg(){
   	$("#customer_type").empty();
   	$("<option value=''>请选择...</option>").appendTo($("#customer_type"));
   	var url = "${ctx}/manager/admin/KonkaMobileSailData.do?method=getCType&c_comm="+$('#customer_par_index').val();
   	$.getJSON(url, function(data) {
   		if(data != null){
   			$.each(data, function(i, item) {
   				var option = $("<option></option>").val(item[1]).text(item[0]);
   				option.appendTo($("#customer_type"));
   			});
   			if('${af.map.customer_type }' != null || '${af.map.customer_type}' != '' ){
   				$("#customer_type").val('${af.map.customer_type }');
   			}
   		}
   	});
   }
   
 
   function map_show(){
	   require.config({
	       paths:{ 
	           echarts:'${ctx}/commons/scripts/echarts/echarts-map',
	           'echarts/chart/map' : '${ctx}/commons/scripts/echarts/echarts-map'
	       }
	   });
	   
	   var option = {
	           title: {
				text : '全国34个省市自治区'
				},
				tooltip : {
					show : true,
					trigger: 'item',
					showDelay: 10,
					transitionDuration: 0.1
					//formatter: '滚轮切换或点击进入该省<br/>{b}'
				},
				legend: {
					orient: 'vertical',
					x:'right',
					data:['客户数量']
				},
				dataRange: {
					min: 0,
					max: ${max_num},
					color:['red','yellow'],
					text:['高','低'],           // 文本，默认为数值文本
					calculable : true
				},
				series : [
					{
						name: '客户数量',
						type: 'map',
						mapType: 'china',
						selectedMode : 'single',
						itemStyle:{
							normal:{label:{show:true}},
							emphasis:{label:{show:true}}
						},
						data:[
						<c:forEach items="${entityList}" var="cur" varStatus="vs">
							<c:if test="${vs.count ne 1}">
								,
							</c:if>
							<c:if var="p_name_flag" test="${fn:contains(cur.map.p_name,'内蒙古') or fn:contains(cur.map.p_name,'黑龙江')}">
					   			{name:'${fn:substring(cur.map.p_name,0,3)}',value:parseInt('${cur.map.num}')}
					   		</c:if>
					   		<c:if test="${!p_name_flag}">
					   			{name:'${fn:substring(cur.map.p_name,0,2)}',value:parseInt('${cur.map.num}')}
					   		</c:if>
						</c:forEach>
						]
						
					}
				]
			};
	   
	
		require(
		   [
		       'echarts',
				'echarts/chart/map'
		   ],
		   function(ec) {
		       var myChart = ec.init(document.getElementById('main'));
		
		       //option.series[0].data = "";
		       myChart.setOption(option);
		
				var ecConfig = require('echarts/config');
				var zrEvent = require('zrender/tool/event');
				var curIndx = 0;
				var mapType = [
					'china',
					// 23个省
					'广东', '青海', '四川', '海南', '陕西', 
					'甘肃', '云南', '湖南', '湖北', '黑龙江',
					'贵州', '山东', '江西', '河南', '河北',
					'山西', '安徽', '福建', '浙江', '江苏', 
					'吉林', '辽宁', '台湾',
					// 5个自治区
					'新疆', '广西', '宁夏', '内蒙古', '西藏', 
					// 4个直辖市
					'北京', '天津', '上海', '重庆',
					// 2个特别行政区
					'香港', '澳门'
				];
		
				myChart.on(ecConfig.EVENT.MAP_SELECTED, function(param){
				
					var selected = param.selected;
					var str = '';
					for (var p in selected) {
						if (selected[p]) {
							str += p;
							//selected[p] = false;
						}
					}
					myChart.setOption(option);
					showDetails(str);
					
				});
		   }
		);
    }


	function showDetails(value){
		var p_names = [];
		var province = "";
		<c:forEach items="${entityList}" var="cur" varStatus="vs">
			p_names[p_names.length] = '${cur.map.p_name},${cur.map.p_index}';
		</c:forEach>
		for(var i=0;i<p_names.length;i++){
			if(p_names[i].indexOf(value) >= 0){
				var array = p_names[i].split(",");
				province = array[1];
				break;
			}
		}
		var url = "${ctx}/manager/admin/KonkaR3ShopHomePage.do?method=view&province=" + province;
		url += "&customer_par_index=" + $("#customer_par_index").val();
		url += "&customer_type=" + $("#customer_type").val();
		url += "&add_date_start=" + $("#add_date_start").val();
		url += "&add_date_end=" + $("#add_date_end").val();
		url += "&r3_money_gt=" + $("#r3_money_gt").val();
		url += "&r3_money_lt=" + $("#r3_money_lt").val();
		var returnValue = window.showModalDialog(url,window,"dialogHeight:400px;dialogWidth:600px;");
		//alert("liuzx:"+value);
	}
	
	
	//正则表达式：只能输入整数
	function setOnlyInt(obj) {
		$(obj).css("ime-mode", "disabled");
		$(obj).attr("t_value", "");
		$(obj).attr("o_value", "");
		$(obj).bind("dragenter",function(){
			return false;
		});
		$(obj).keypress(function (){
			if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
		}).keyup(function (){
			if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
		}).blur(function (){
			if(!obj.value.match(/^(?:\d+(?:\d+)?|\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\d+$/))obj.value=obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
			if(obj.value.length == 0 || isNaN(obj.value) || obj.value == 0) obj.value = "";
		});
	}
   
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
