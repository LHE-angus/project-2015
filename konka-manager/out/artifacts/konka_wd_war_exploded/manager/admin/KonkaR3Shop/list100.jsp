<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  
  <div class="rtabcont1">
  	<table width="50%" border="0" cellpadding="0" cellspacing="1" class="rtable6">
		<tr class="tabtt6">
			 <td align="center">
			 <a style="cursor:pointer;color:${af.map.rank_type eq 100 ? '#FFFF31':'#FFFFFF'};" href="${ctx}/manager/admin/KonkaCategoryStatistics.do?method=list&mod_id=${af.map.mod_id}&rank_type=100">客户分类对比</a>
			  |
			 <a style="cursor:pointer;color:${af.map.rank_type eq 200 ? '#FFFF31':'#FFFFFF'};" href="${ctx}/manager/admin/KonkaCategoryStatistics.do?method=list&mod_id=${af.map.mod_id}&rank_type=200">客户月度增长趋势</a>
			 </td>
		</tr>      
	</table>
  </div>

  <div class="rtabcont1">
    <html-el:form action="/admin/KonkaCategoryStatistics.do">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="rank_type" styleId="rank_type"/>
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0"  cellpadding="0" class="rtable1">
        		
        		<tr id="tr_1">
	        	<td >
	          	 分公司： 
	          	<html-el:select property="v_company" styleId="v_company">
	             <html-el:option value="">请选择</html-el:option>
	          	 <c:forEach items="${konkaDeptList}" var="vs">
	          	 <html-el:option value="${vs.dept_name}">${vs.dept_name}</html-el:option>
	          	 </c:forEach>	
	         	</html-el:select>
	        	 日期： 
					<html-el:select property="v_year" styleId="v_year">
	          		  	<html-el:option value="2011">2011年</html-el:option>
	          		  	<html-el:option value="2012">2012年</html-el:option>
	          		  	<html-el:option value="2013">2013年</html-el:option>
	          		  	<html-el:option value="2014">2014年</html-el:option>
	          		  	<html-el:option value="2015">2015年</html-el:option>
	          		  	<html-el:option value="2016">2016年</html-el:option>
	          		  	<html-el:option value="2017">2017年</html-el:option>
	          		  	<html-el:option value="2018">2018年</html-el:option>
	          		  	<html-el:option value="2019">2019年</html-el:option>
	          		  	<html-el:option value="2020">2020年</html-el:option>
	          		  	<html-el:option value="2021">2021年</html-el:option>
	          		  	<html-el:option value="2022">2022年</html-el:option>
	          		  	<html-el:option value="2023">2023年</html-el:option>
	         		 </html-el:select>
	         		  -
	         		 <html-el:select property="v_month" styleId="v_month" >
				      	<html-el:option value="1">1月</html-el:option>
				      	<html-el:option value="2">2月</html-el:option>
				      	<html-el:option value="3">3月</html-el:option>
				      	<html-el:option value="4">4月</html-el:option>
				      	<html-el:option value="5">5月</html-el:option>
				      	<html-el:option value="6">6月</html-el:option>
				      	<html-el:option value="7">7月</html-el:option>
				      	<html-el:option value="8">8月</html-el:option>
				      	<html-el:option value="9">9月</html-el:option>
				      	<html-el:option value="10">10月</html-el:option>
				      	<html-el:option value="11">11月</html-el:option>
				      	<html-el:option value="12">12月</html-el:option>
			      	</html-el:select>
			      	&nbsp;&nbsp;
			      	<html-el:submit styleClass="but1" styleId="but1" value="搜索"></html-el:submit>
			      	</td>
			</tr>
      </table>
    </html-el:form>
   </div>
	  <div class="rtabcont1" id="div_100">
	  	<table width="100%">
	  		<tr>
	  			<td width="49%" valign="top" >
	  				<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
				      <tr class="tabtt1">
				        <td width="20%" nowrap="nowrap" align="center" >客户分类</td>
				        <td width="20%" nowrap="nowrap" align="center" >客户总数</td>
				        <td width="10%" nowrap="nowrap" align="center" >当月新增客户数</td>
				      </tr>
				    
				      <c:set var="last_par_id" value="0" />
				      <c:forEach items="${entityList}" var="cur" varStatus="vs">
				      	<c:if test="${cur[0] ne last_par_id}">
					      	<tr>
					          <td align="left" nowrap="nowrap" class="dalei" data-parid="${cur[0]}"><font color="blue" style="font-weight: bold ">${cur[1]}</font></td>
					          <td align="center" nowrap="nowrap"></td>
					          <td align="center" nowrap="nowrap"></td>
					        </tr>
				      	</c:if>
				        <tr>
				          <td align="right" nowrap="nowrap">${cur[3]}</td>
				          <td align="center" nowrap="nowrap" class="xiaolei_${cur[0]}">${cur[4]}</td>
				          <td align="center" nowrap="nowrap" class="xiaolei_currentmonth_${cur[0]}">${cur[5]}</td>
				        </tr>
				      	<c:set var="last_par_id" value="${cur[0]}" />
				      </c:forEach>
				    </table>
	  			</td>
	  			<td width="1%">&nbsp;</td>
	  			<td width="50%" valign="top">
					<table align="center" width="100%" cellpadding="0" cellspacing="0">
		       			<tr align="center">
		       				<td>
		       					<div id="chartdiv"></div>
		       				</td>
		       			</tr>
	    			</table>
				</td>
	  		</tr>
	  	</table>
	  </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/manager/scripts/charts.js"></script>

<script type="text/javascript">   
$(document).ready(function(){

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
	//客户大类
	 var typeName = [];
	//客户月度增长量
	 var totalAddNumber = [];
	//客户总数(不会随过滤条件变更而变更)
	 var totalNumber = [];
	
	$(".dalei").each(function(){
		var $this = $(this);
		var par_id = $(this).data("parid");
		
		var total = 0;
		$(".xiaolei_" + par_id).each(function(){
			total += parseInt($.trim($(this).text()));
		});

		var currrentmonth_total = 0;
		$(".xiaolei_currentmonth_" + par_id).each(function(){
			currrentmonth_total  += parseInt($.trim($(this).text()));
		});

		$this.next().html(total).css({"color":"blue","font-weight":"bold"}).next().html(currrentmonth_total).css({"color":"blue","font-weight":"bold"});
		//var _key = $this.text();
		//var _value = currrentmonth_total ;
		
		typeName.push($this.text());
		totalAddNumber.push(currrentmonth_total);
		totalNumber.push(total);
	});
	
	// x坐标的数据 包括 totalAddNumber totalNumber
// 	var Xdata = [totalAddNumber,totalNumber];
// 	for(var i = 0 ;i< Xdata.length ;i++){
// 		for(var j = 0 ;j<Xdata[i].length ;j++){
// 			//alert(Xdata[i][j]);
// 		}
// 	}

	if("${not empty entityList}" == "false"){
		var flash = "MSBar3D.swf";
		var chart = new FusionCharts("${ctx}/scripts/fusioncharts/swf/"+flash, "ChartId", "100%", "${char_x_heigth}", "0", "0");
 		var xmlData = "";
		xmlData += "<chart palette='2' caption='客户增长趋势图' subcaption='按客户分类统计' shownames='1' showvalues='1' decimals='2' formatNumberScale='0' useRoundEdges='1' legendBorderAlpha='0' baseFontColor='666666' BaseFontSize ='12' showBorder='1' bgSWFAlpha='0' canvasBgAlpha='0'>";
		xmlData +="<categories>";
		for(var index in typeName){
			 xmlData +="<category label='"+typeName[index]+"'/>";//y坐标
		 }
	    xmlData +="</categories>";
	    
	 	xmlData +="<dataset seriesName='客户增加量' showValues='1' color='1D8BD1' >";
	  	for(var index in totalAddNumber){
			xmlData += "<set value='"+totalAddNumber[index]+"'/>";
		 }
	    xmlData +="</dataset>";
	    xmlData +="</chart>";
	    chart.setDataXML(xmlData);
	    chart.render("chartdiv");
	}
	
	if ("${not empty entityList}" == "true"){
		var flash ="MSLine.swf";
		var chart = new FusionCharts("${ctx}/scripts/fusioncharts/swf/"+flash, "ChartId", "100%", "${char_x_heigth}", "0", "0");
		var xml = "";
		xml += "<chart caption='客户增长对比图[按客户大类统计]' lineThickness='1' decimals='2' showValues='0' formatNumberScale='0' anchorRadius='2'   divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' showAlternateHGridColor='1' alternateHGridAlpha='5' alternateHGridColor='CC3300' shadowAlpha='40' labelStep='1' numvdivlines='5' chartRightMargin='35' bgColor='666666' bgAngle='270' bgAlpha='0'>";
		xml += "<categories >";
		for ( var index in typeName) {
			xml += "<category label='"+typeName[index]+"'/>";
		}
		xml += "</categories>";
	
		xml += "<dataset seriesName='客户增加量' showValues='1' color='1D8BD1' >";
		for ( var index in totalAddNumber) {
			xml += "<set value='"+totalAddNumber[index]+"'/>";
		}
		xml += "</dataset>";
		
		xml += "<dataset seriesName='客户总数' showValues='1' color='ff4500' >";
		for ( var index in totalNumber) {
			xml += "<set value='"+totalNumber[index]+"'/>";
		}
		xml += "</dataset>";
		
		xml += "<styles>";
		xml += "<definition>";
		xml += " <style name='CaptionFont' type='font' size='12'/>";
		xml += " <style name='SUBCAPTIONFont' type='font' size='12'/>";
	    xml += " </definition>";
		xml += " <application>";
		xml += " <apply toObject='CAPTION' styles='CaptionFont' />";
		xml += " <apply toObject='SUBCAPTION' styles='SUBCAPTIONFont' />";
		xml += "  </application>";
		xml += " </styles>";
		xml += "</chart>";
		chart.setDataXML(xml);
		chart.render("chartdiv");
	}

});
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>


