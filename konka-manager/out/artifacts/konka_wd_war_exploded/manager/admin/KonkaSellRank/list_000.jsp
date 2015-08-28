<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title> 
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="rtabcont1">
  	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable6">
		<tr class="tabtt6">
			<c:if test="${(af.map.dept_type eq 1) or (af.map.role_id eq 34)}">
			<td align="center"><a style="cursor:pointer;color:${af.map.rank_type eq 100 ? '#FFFF31':'#FFFFFF'};" href="KonkaSellRank.do?method=list&mod_id=${af.map.mod_id}&rank_type=100">零售分公司排名</a></td>
			</c:if>
			<td align="center"><a style="cursor:pointer;color:${af.map.rank_type eq 200 ? '#FFFF31':'#FFFFFF'};" href="KonkaSellRank.do?method=list&mod_id=${af.map.mod_id}&rank_type=200">零售经办排名</a></td>
			<%--<td align="center"><a style="cursor:pointer;color:${af.map.rank_type eq 300 ? '#FFFF31':'#FFFFFF'};" href="KonkaSellRank.do?method=list&mod_id=${af.map.mod_id}&rank_type=300">零售业务员排名</a></td>--%>
			<td align="center"><a style="cursor:pointer;color:${af.map.rank_type eq 400 ? '#FFFF31':'#FFFFFF'};" href="KonkaSellRank.do?method=list&mod_id=${af.map.mod_id}&rank_type=400">零售客户排名</a></td>
			<td align="center"><a style="cursor:pointer;color:${af.map.rank_type eq 500 ? '#FFFF31':'#FFFFFF'};" href="KonkaSellRank.do?method=list&mod_id=${af.map.mod_id}&rank_type=500">零售促销员排名</a></td>
			<td width="40%">&nbsp;</td>
		</tr>     
	</table>
  </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
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
	
});


//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>