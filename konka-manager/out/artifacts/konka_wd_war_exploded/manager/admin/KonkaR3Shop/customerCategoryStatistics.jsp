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

</div>

<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>

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

    
});
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>


