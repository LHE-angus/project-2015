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
	        <td align="right">
	          	 分公司：
	        </td> 
	        <td>
	        	<html-el:select property="v_company" styleId="v_company" onchange="changeFgsSn();">
	             	<html-el:option value="">请选择</html-el:option>
	          	 	<c:forEach items="${konkaDeptList}" var="vs">
	          	 		<html-el:option value="${vs.dept_name}">${vs.dept_name}</html-el:option>
	          	 	</c:forEach>	
	         	</html-el:select>
	        </td>
	        <td align="right">
	         	经办:
	        </td>
	        <td>  	
	          	<html-el:select property="v_handle_name" styleId="v_handle_name">
	          		<html-el:option value="">请选择</html-el:option>
	          		<c:forEach items="${kkList}" var="vs">
	          	 		<html-el:option value="${vs.handle_name}">${vs.handle_name}</html-el:option>
	          	 	</c:forEach>	
	          	</html-el:select>
		    </td>
		    <td align="right">  
	        	 日期：
	        </td>
	        <td> 
				<html-el:select property="v_year" styleId="v_year">
          		  	<html-el:option value="">年份</html-el:option>
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
         		 <html-el:select property="v_month" styleId="v_month">
			      	<html-el:option value="">月份</html-el:option>
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
		      </td>
		      <td align="right" >
				       客户分类：
			  </td>
			  <td> 
					<html-el:select property="v_customer_type" styleId="v_customer_type" onchange="changeCustomType();">
	          		  	<html-el:option value="">请选择</html-el:option>
	          		  	<c:forEach var="cur" items="${p_CategoryList}">
	                	 <html-el:option value="${cur}">${cur}</html-el:option>
	                	</c:forEach>
         		 	</html-el:select>
         		 	&nbsp;&nbsp;
         		 	<html-el:select property="v_customer_type2" styleId="v_customer_type2" style="width:200px">
	          		  	<html-el:option value="">请选择</html-el:option>
	          		  	<c:forEach var="cur" items="${konkaCategoryList}">
	          		  	<html-el:option value="${cur.c_name}">[${cur.c_comm}] ${cur.c_name}</html-el:option>
	                	</c:forEach>
         		 	</html-el:select>
         	   </td>
         	   <td>
		      		<html-el:button styleClass="but1" styleId="but1" value="搜索" property=""></html-el:button>
				</td>
			</tr>
      </table>
    </html-el:form>
   </div>
   
  	<div class="rtabcont1" id="div_200">
	  	<table width="100%">
	  		<tr>
	  			<td width="30%" valign="top" >
	  				<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
				      <tr class="tabtt1">
				        <td width="10%" align="center">月份</td>
				        <td width="10%" align="center">当月新增数</td>
				        <td width="10%" align="center">截至当月累积数</td>
				      </tr>
				    
				      <c:forEach items="${entityList2}" var="cur" varStatus="vs">
				        <tr>
				          <td align="right" nowrap="nowrap" class="v_m">${cur[0]}</td>
				          <td align="right" nowrap="nowrap" class="v_count">${cur[1]}</td>
				          <td align="right" nowrap="nowrap" class="v_count2">${cur[2]}</td>
				        </tr>
				      </c:forEach>
				    </table>
	  			</td>
	  			<td width="1%">&nbsp;</td>
	  			<td width="60%" valign="top">
					<table align="center" width="100%" cellpadding="0" cellspacing="0">
		       			<tr align="center">
		       				<td>
		       					<div id="chartdiv2"></div>
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
<script type="text/javascript"> //<![CDATA[  
$(document).ready(function(){
	
	//年月
	var vm = [];
	//当月新增客户数
	var vcount = [];
	//当月客户累积客户数
	var vcount2 = [];
	$(".v_m").each(function(){
		var $this = $(this).text();
		vm.push(($this).substr(5,2)) ;
	});
	$(".v_count").each(function(){
		var $this = $(this).text();
		vcount.push($this) ;
	});
	
	$(".v_count2").each(function(){
		var $this = $(this).text();
		vcount2.push($this) ;
	});
	
	if ("${not empty entityList2}" == "true"){
		var flash ="MSLine.swf";
		var chart = new FusionCharts("${ctx}/scripts/fusioncharts/swf/"+flash, "ChartId", "100%", "500", "0", "0");
		var xml = "";
		xml += "<chart caption='客户月度增长趋势' lineThickness='1' decimals='2' showValues='0' formatNumberScale='0' anchorRadius='2'   divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' showAlternateHGridColor='1' alternateHGridAlpha='5' alternateHGridColor='CC3300' shadowAlpha='40' labelStep='1' numvdivlines='5' chartRightMargin='35' bgColor='666666' bgAngle='270' bgAlpha='0'>";
		xml += "<categories >";
		for ( var index in vm) {
			xml += "<category label='"+vm[index]+"'/>";
		}
		xml += "</categories>";
	
		xml += "<dataset seriesName='当月新增数' showValues='1' color='1D8BD1' >";
		for ( var index in vcount) {
			xml += "<set value='"+vcount[index]+"'/>";
		}
		xml += "</dataset>";
		
		xml += "<dataset seriesName='截至当月累积数' showValues='1' color='ff4500' >";
		for ( var index in vcount2) {
			xml += "<set value='"+vcount2[index]+"'/>";
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
		chart.render("chartdiv2");
	}
	
	$("#but1").click(function(){
		var vyear = $("#v_year").val();
		if(vyear == null || vyear == ""){
			alert("请选择年份!");
			return false ;
		}else{
			this.form.submit();
		}
			
	});

	changeFgsSn();
	changeCustomType();
});


function changeFgsSn(){
	
	//取得分公司
	var vcompany = $("#v_company").val();
	if(vcompany !=""){
		$.ajax({
			type: "post",
			url: "${ctx}/manager/admin/KonkaCategoryStatistics.do?method=getJBDataBydeptName",
			data: {"branch_area_name" : vcompany},
			dataType: "json",
			error: function(request, settings) {
				var html = "<option value=''>请选择</option>";
				$("#v_handle_name").empty();
				$("#v_handle_name").html(html);
				}, 
			success: function(result) {
				var html = "<option value=''>请选择</option>";
				for(var i = 0 ;i<result.length ;i++){
					if(result[i] == "${af.map.v_handle_name}"){
						html += "<option selected='selected' value='"+result[i]+"'>"+result[i]+"</option>";
						}else{
							html += "<option value='"+result[i]+"'>"+result[i]+"</option>";
							}
				}
				$("#v_handle_name").empty();
				$("#v_handle_name").html(html);
			}
		});
	}else{
		var html = "<option value=''>请选择</option>";
		$("#v_handle_name").empty();
		$("#v_handle_name").html(html);
	}
	
}

function  changeCustomType(){
	//取得客户大类
	var $customer_type = $("#v_customer_type").val();
	if($customer_type !=""){
		$.ajax({
			type: "post",
			url: "${ctx}/manager/admin/KonkaCategoryStatistics.do?method=getCategoryListByParent",
			data: {"v_customer_type" : $customer_type},
			dataType: "json",
			error: function(request, settings) {
				var html = "<option value=''>请选择</option>";
				$("#v_customer_type2").empty();
				$("#v_customer_type2").html(html);
				}, 
			success: function(result) {
				var html = "<option value=''>请选择</option>";
				for(var i = 0 ;i<result.length ;i++){
					if(result[i] == "${af.map.v_customer_type2}"){
						html += "<option selected='selected' value='"+result[i].c_name+"'>"+result[i].c_name+"</option>";
						}else{
							html += "<option value='"+result[i].c_name+"'>"+result[i].c_name+"</option>";
							}
				}
				$("#v_customer_type2").empty();
				$("#v_customer_type2").html(html);
			}
		});
	}else{
		var html = "<option value=''>请选择</option>";
		$("#v_customer_type2").empty();
		$("#v_customer_type2").html(html);
	}
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>


