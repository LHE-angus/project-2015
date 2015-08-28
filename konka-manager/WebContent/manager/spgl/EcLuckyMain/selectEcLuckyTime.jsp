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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont" id="body_oarcont">
  <div class="rtabcont1">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
     <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>开始时间：</td>
          <td width="88%" align="left"><fmt:formatDate value="${af.map.start_date}" pattern="yyyy-MM-dd HH:mm:ss" var="_start_date" />
             <html-el:text styleId="start_date" property="start_date" size="20" maxlength="15" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="cursor:pointer;text-align:center;" value="${_start_date}" />
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>结束时间：</td>
          <td width="88%" align="left"><fmt:formatDate value="${af.map.end_date}" pattern="yyyy-MM-dd HH:mm:ss" var="_end_time" />
          	<html-el:text styleId="end_date" property="end_date" size="20" maxlength="15" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="cursor:pointer;text-align:center;" value="${_end_date}" />
          </td>
        </tr>
    </table>
  </div>
  <div class="rtabcont1" style="margin-left:145px;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><input type="button" class="but2" value="确认" id="btn_sub"  />
        </td>
      </tr>
    </table>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#start_date").attr("datatype", "Require").attr("msg", "请选择活动开始时间");
	$("#end_date").attr("datatype", "Require").attr("msg", "请选择活动结束时间");

	$("#btn_sub").click(function(){
		var return_value = "";
		var start_date = $("#start_date").val();
		var end_date = $("#end_date").val();

		if("" == start_date){
			alert("请选择活动开始时间");
			return false;
		}
		if("" == end_date){
			alert("请选择活动结束时间");
			return false;
		}

		//alert(new Date("2014//05//06"));    
		var s_date = start_date.replace(/-/g,"").replace(/:/g,"").replace(/ /g,""); 	
		var e_date = end_date.replace(/-/g,"").replace(/:/g,"").replace(/ /g,"");
		if(s_date > e_date){
			alert("活动开始时间不能大于结束时间");
			return false;
		}

		
		return_value = start_date + "##" + end_date;
		window.returnValue = return_value;
		window.close();
		
	});

});

//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
