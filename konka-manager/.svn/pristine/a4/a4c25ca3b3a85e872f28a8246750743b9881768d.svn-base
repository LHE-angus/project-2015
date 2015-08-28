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

</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont" id="body_oarcont" style="margin-top:15px;">
   <html-el:form action="/spgl/PshowOrderLook">
   <html-el:hidden property="method" value="list" />
   <html-el:hidden property="mod_id" value="${af.map.mod_id}" />    
  	<div class="rtabcont1">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
     <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>开始时间：</td>
          <td width="88%" align="left">
         	<input  name="start_date" id="start_date"  value="${af.map.start_date}" size="9" maxlength="10" readonly="readonly" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>结束时间：</td>
          <td width="88%" align="left">
          <input  name="end_date" id="end_date"   value="${af.map.end_date}" size="9" maxlength="10" readonly="readonly" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
          </td>   
        </tr>
    </table>
  </div>
  <div class="rtabcont1" style="margin-left:100px;"> 
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><input type="button" class="but11" value="开始同步顺丰嘿客订单" id="btn_sub"  />
        </td>
      </tr>
    </table>
  </div>
  </html-el:form>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){


	$("#start_date").attr("datatype", "Require").attr("msg", "请选择活动开始时间");
	$("#end_date").attr("datatype", "Require").attr("msg", "请选择活动结束时间");

	$("#btn_sub").click(function(){
		var start_date = $("#start_date").val();
		var end_date = $("#end_date").val();

		if("" == start_date){
			alert("请选择开始时间");
			return false;
		}
		if("" == end_date){
			alert("请选择结束时间");
			return false;
		}

		//alert(new Date("2014//05//06"));    
		var s_date = start_date.replace(/-/g,"").replace(/:/g,"").replace(/ /g,""); 	
		var e_date = end_date.replace(/-/g,"").replace(/:/g,"").replace(/ /g,"");
		if(s_date > e_date){
			alert("开始时间不能大于结束时间");
			return false;
		}

		$("#btn_sub").attr("value", "正在同步订单中...请不要关闭窗口").attr("disabled", "true");
		$.ajax({
			type: "POST",
			url: "PshowOrderLook.do?method=syncSfhkOrder",
			data: {"start_date":start_date,"end_date":end_date,"timestamp":new Date().getTime()},
			dataType: "text",
			error: function(request, settings) {
				alert("同步发生异常，请重新同步!");
				$("#btn_sub").removeAttr("disabled").attr("value","开始同步顺丰嘿客订单");
			},
			success: function(result) {
				alert(result);
				$("#btn_sub").removeAttr("disabled").attr("value","开始同步顺丰嘿客订单");
				window.close();
				window.dialogArguments.location.reload();  
			}
		});
		
	});

});

//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
