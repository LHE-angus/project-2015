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
<script type="text/javascript" src="${ctx}/manager/scripts/charts.js"></script>
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
  <c:if test="${is_admin}">
  <div class="rtabcont1">
    <html-el:form action="/admin/PersonCount">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td><strong class="fb">分公司：</strong>
          	<html-el:select property="dept_id" styleId="dept_id" onchange="this.form.submit();">
          		<html-el:option value="">-请选择-</html-el:option>
          		<c:forEach var="cur" items="${sybDeptInfoList}">
                <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
              </c:forEach>
  			</html-el:select> 
          </td>
          <td><html-el:submit styleClass="but1" styleId="search" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  </c:if>
  <div class="rtabcont1" style="overflow-x:auto;" >
  <table width="100%">
  	<tr>
  		<td width="50%" valign="top" >
  	<c:if test="${not empty entityList}">
	      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
	        <tr class="tabtt1">
	          <td width="5%" align="center" nowrap="nowrap">序号</td>
	          <td nowrap="nowrap" align="center">分公司</td>
	          <td width="12%" nowrap="nowrap">人员总数</td>
	          <td width="12%" nowrap="nowrap">业务员总数</td>
	          <td width="12%" nowrap="nowrap">促销员总数</td>
	        </tr>
	        <c:forEach var="cur" items="${entityList}" varStatus="vs">
	          <tr>
	            <td align="center" nowrap="nowrap">${vs.count}</td>
	            <td align="left" nowrap="nowrap"><a onclick="changeValueSubmitForL('${cur.map.dept_id}');" style="color: blue;cursor:pointer;">${cur.map.dept_name}</a></td>
	            <td align="right" nowrap="nowrap">
	            	<c:if test="${empty cur.map.totalcount2}">0</c:if>
	            	<c:if test="${not empty cur.map.totalcount2}">${cur.map.totalcount2}</c:if>			
	            </td>
	            <td align="right" nowrap="nowrap">
	            	<c:if test="${empty cur.map.ywycount2}">0</c:if>
	            	<c:if test="${not empty cur.map.ywycount2}">${cur.map.ywycount2}</c:if>
	            </td>
	            <td align="right" nowrap="nowrap">
	            	<c:if test="${empty cur.map.cxycount2}">0</c:if>
	            	<c:if test="${not empty cur.map.cxycount2}">${cur.map.cxycount2}</c:if>
	            </td>
	          </tr>
	        </c:forEach>
	        <tr>
	        	<td align="center" colspan="2" nowrap="nowrap">合计</td>
	        	<td align="right" nowrap="nowrap">${totalCount}</td>
	          	<td align="right" nowrap="nowrap">${ywyCount}</td>
	         	<td align="right" nowrap="nowrap">${cxyCount}</td>
	        </tr>
	      </table>
        </c:if>
    </td>
    <td width="2%">&nbsp;</td>
	<td width="46%" valign="top">
	<div id="funsion_id" >
		<!-- 
		<table align="center" width="100%" cellpadding="0" cellspacing="0">
      			<tr align="center">
      				<td>
      					<iframe id="fusionchart"
							name="fusionchart"
							src="javascript:void(0);"
							height="50"
							width="100%" 
							frameborder="no" 
							border="0" 
							marginwidth="0" 
							marginheight="0" 
							scrolling="no" 
							allowtransparency="yes"
							onload="javascript:dyniframesize('fusionchart');" >
						</iframe>
      				</td>
      			</tr>
		</table> -->
	</div>	
	</td>
    </tr>
    </table>
  </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript">//<![CDATA[
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

	if("" != "${af.map.dept_id}"){
		changeValueSubmitForL('${af.map.dept_id}');
	}
	
	$("a").click(function(){
		var topheight = $(this).offset().top;
		var h_heig = 0;
		if(topheight > 400){
			h_heig = topheight - 400;
		} else {
			h_heig=$(this).offset().top - 50;
		}
	    $("#funsion_id").offset({top:h_heig,left:600});
	});
	
		
});



function changeValueSubmitForL(dept_id){
	$("#funsion_id").empty();
		
	var flash = "Pie3D.swf";
	var chart = new FusionCharts("${ctx}/scripts/fusioncharts/swf/"+flash, "ChartId", "100%", "400", "0", "0");
	var dataStr = "PersonCount.do?method=getPersonJsonForBranch&dept_id=" + dept_id;
	$.ajax({
	    type: "POST",
	    url: dataStr,
	    error: function(request, settings) {alert("数据加载请求失败"); },
	    success: function(data) {
	        if (data != ''){
	            chart.setDataXML(data);
	            chart.render("funsion_id");
	        }
	    }
	});
			
}


//辅助iframe自适应高度
function dyniframesize(iframeid) {
	$("#" + iframeid).show();	
	var ifm= document.getElementById(iframeid); 
	var subWeb = document.frames ? document.frames[iframeid].document : ifm.contentDocument; 
	if(ifm != null && subWeb != null) { 
		ifm.height = subWeb.body.scrollHeight; 
	} 
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
