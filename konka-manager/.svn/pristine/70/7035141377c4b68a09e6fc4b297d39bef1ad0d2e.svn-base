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
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/scripts/fusioncharts/FusionCharts.js"></script>
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/zmd/KonkaXxPdStatistics" styleClass="form_search">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="search_flag" value="1" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
            <td width="15">&nbsp;</td>
            <c:if test="${role_id_btw_200_300}">
	            <td><strong class="fb">分公司：</strong><html-el:select property="dept_id" style="width:154px;" styleId="dept_id" onchange="$('#message_tip').show();deptChange(0,0);">
	              	<html-el:option value="">==请选择==</html-el:option>
	           		<c:forEach var="cur" items="${konkaDeptList}">
	           			<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
	           		</c:forEach>
	              </html-el:select>
	            </td>  
	            <td><strong class="fb">专卖店编号：</strong><html-el:select property="zmd_id" style="width:154px;" styleId="zmd_id">
	              	<html-el:option value="">==请选择==</html-el:option>
	              </html-el:select>
	            </td>         
            </c:if>
            <c:if test="${role_id_btw_300_400}">
            	<input type="hidden" name="dept_id" value="${userInfo.dept_id}" />
	            <td><strong class="fb">专卖店编号：</strong><html-el:select property="zmd_id" style="width:154px;" styleId="zmd_id">
	              	<html-el:option value="">==请选择==</html-el:option>
	           		<c:forEach var="cur" items="${zmdList}">
	           			<html-el:option value="${cur.zmd_id}">${cur.zmd_sn}</html-el:option>
	           		</c:forEach>
	              </html-el:select>
	            </td>
            </c:if>
            <c:if test="${role_id_eq_400}">
				<input type="hidden" name="zmd_id" value="${zmd_id}" />
            </c:if>
            <td><strong class="fb">产品型号：</strong><html-el:select property="md_name" style="width:154px;" styleId="md_name">
	              	<html-el:option value="">==请选择==</html-el:option>
	           		<c:forEach var="cur" items="${konkaXxPdList}">
	           			<html-el:option value="${cur.md_name}">${cur.md_name}</html-el:option>
	           		</c:forEach>
	              </html-el:select>
            </td>
            <td><strong class="fb">日期：</strong>
            	<html-el:text property="date_start" styleId="date_start" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />&nbsp;至&nbsp;<html-el:text property="date_end" styleId="date_end" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
            </td>
			<td><input class="but1" type="button" value="查看" id="btn_search" /></td>
		</tr>
	  </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
	<c:if test="${af.map.search_flag ne '1'}">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
			<tr>
				<td align="left" height="28"><strong class="fb" style="color:green;">请选择查询条件点击查看！</strong></td>
			</tr>
		</table>
	</c:if>
	<c:if test="${af.map.search_flag eq '1'}">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr>
          <td>
          	  <div id="chartdivPie3DForNum" align="center"></div>
	          <script type="text/javascript">
				var chart = new FusionCharts("${ctx}/scripts/fusioncharts/swf/Pie3D.swf", "ChartId", "400", "400", "0", "0");
				chart.setDataUrlParams("dept_id", "${af.map.dept_id}");
				chart.setDataUrlParams("zmd_id", "${af.map.zmd_id}");
				chart.setDataUrlParams("md_name", "${af.map.md_name}");
				chart.setDataUrlParams("date_start", "${af.map.date_start}");
				chart.setDataUrlParams("date_end", "${af.map.date_end}");
				chart.setDataURL("${ctx}/manager/zmd/KonkaXxPdStatistics.do?method=pie3DForNum");
				chart.render("chartdivPie3DForNum");
			  </script>
          </td>
          <td>
          	  <div id="chartdivPie3DForMoney" align="center"></div>
	          <script type="text/javascript">
				var chart = new FusionCharts("${ctx}/scripts/fusioncharts/swf/Pie3D.swf", "ChartId", "400", "400", "0", "0");
				chart.setDataUrlParams("dept_id", "${af.map.dept_id}");
				chart.setDataUrlParams("zmd_id", "${af.map.zmd_id}");
				chart.setDataUrlParams("md_name", "${af.map.md_name}");
				chart.setDataUrlParams("date_start", "${af.map.date_start}");
				chart.setDataUrlParams("date_end", "${af.map.date_end}");
				chart.setDataURL("${ctx}/manager/zmd/KonkaXxPdStatistics.do?method=pie3DForMoney");
				chart.render("chartdivPie3DForMoney");
			  </script>
          </td>
        </tr>
      </table>
	</c:if>
  </div>
  <div class="clear"></div>
</div>
<!-- Ajax 提交 覆盖层  -->
<div id="message_tip" style="display:none;z-index:888888888;">
    <div class="ui-overlay">
  	  <div class="ui-widget-overlay"></div>
  	  <div class="ui-widget-shadow ui-corner-all" style="width:302px;height:152px;position:absolute;left:35%;top:25%"></div>
    </div>
    <div style="position:absolute;width:280px;height:130px;left:35%;top:25%;padding:10px;" class="ui-widget ui-widget-content ui-corner-all">
	  <span><img id="loading" src="${ctx}/images/loading.gif" />正在查询，请稍等...</span>
    </div>
 </div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	// 日期控件
	$("#date_start").datepicker();
	$("#date_end").datepicker();
	
	<c:if test="${role_id_btw_200_300}">
		$("#dept_id").attr("dataType" , "Require").attr("msg" , "选择分公司！");
	</c:if>
	$("#md_name"   ).attr("dataType" , "Require").attr("msg" , "请选择产品型号！");
	$("#date_start").attr("dataType" , "Require").attr("msg" , "请选择开始日期！");
	$("#date_end"  ).attr("dataType" , "Require").attr("msg" , "请选择结束日期！");
	
	$("#btn_search").click(function (){
		if(Validator.Validate(this.form, 1)){
			$(".form_search").submit();
		}
	});
	
	// 总部管理员回显使用
	<c:if test="${role_id_btw_200_300}">
		var zmd_id = "${af.map.zmd_id}";
		var md_name = "${af.map.md_name}";
		if("" != zmd_id || "" != md_name){
			if("" == zmd_id)
				zmd_id = 0;
			if("" == md_name)
				md_name = 0;
			deptChange(zmd_id, md_name);
		}
	</c:if>
});

function deptChange(zmd_id, md_name){
    $("#zmd_id" ).empty();
    $("#zmd_id" ).append("<option value=''>==请选择==</option>");  
    $("#md_name").empty();
    $("#md_name").append("<option value=''>==请选择==</option>");  
    var dept_id = $("#dept_id").val();
    if("" == dept_id){
    	dept_id = "${af.map.dept_id}";
    }
	$.ajax({
    	type: "POST" , 
        url: "KonkaXxPdStatistics.do" , 
        data: "method=ajaxGetZmdList&dept_id=" + dept_id + "&t=" + (new Date()).getTime(), 
        dataType: "json" , 
        async: true, // false 为同步，true为异步
        error: function (request, settings) {alert(" 数据加载请求失败！ "); $('#message_tip').hide();}, 
        success: function (data) {
        	$('#message_tip').hide();
        	
            if (data.result == "false") {
				alert("请求失败，请重新访问！");
				return ;
			} 
            if(data.konka_xx_pd_list.length == 0){
            	alert("该分公司没有产品，请初始化产品！");
				return ;
            }
            
            // 专卖店
			var zmd_list = data.zmd_list.split("#");
			for(var i = 0; i < zmd_list.length; i++){
				var option = zmd_list[i];
				 $("#zmd_id").append("<option value=\"" + option.split(",")[0] + "\">" + option.split(",")[1] + "</option>");  
			}
			// 分公司产品
			var konka_xx_pd_list = data.konka_xx_pd_list.split("#");
			for(var i = 0; i < konka_xx_pd_list.length; i++){
				var option = konka_xx_pd_list[i];
				 $("#md_name").append("<option value=\"" + option.split(",")[0] + "\">" + option.split(",")[1] + "</option>");  
			}
			
			// 回显使用
			if(0 != zmd_id){
				$("#zmd_id").val(zmd_id);
			}
			if(0 != md_name){
				$("#md_name").val(md_name);
			}
        }
	}); 
}
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>