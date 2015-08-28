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
<link href="${ctx}/commons/styles/EntpShopSearch_style.css" rel="stylesheet" type="text/css" />

<style>
.box1 {
	background-color: #CCFFFF
}

p {
	background-color: yellow;
	height: 40px;
	width: 80%;
	text-align: center;
	border: 1px;
	color: red;
	border-style: solid;
	font-weight: bold;
	font-size: 20px;
}

.but11 {
	width: 150px;
	height: 30px;
	background-color: #FFCC00;
	border: 1px;
	text-align: center;
	cursor: pointer;
	-moz-border-radius: 2px;
	-webkit-border-radius: 2px;
}
</style>

</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
      <html-el:form action="/admin/SapExecuteLog">
      <html-el:hidden property="method" value="list" styleId="method"/>
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
         <tr>
         	<td colspan="1">
         	<input class="but11" type="submit"  value="GET" id="GET" />
         	<input class="but11" type="button"  value="CLS" id="CLS" align="left"/>
         	</td>
         	<td colspan="1"></td>
         </tr>
    </html-el:form>
  </div>
    
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  
  <div class="rtabcont1">
    <table width="80%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td align="center" nowrap="nowrap" width="10%">接口</td>	
          <td align="center" nowrap="nowrap" width="10%">服务次数</td>	
          <td align="center" nowrap="nowrap" width="10%">最蜗牛</td>	
          <td align="center" nowrap="nowrap" width="10%">最神速</td>	
          <td align="center" nowrap="nowrap" width="10%">平均耗时</td>	
        </tr>
		<c:forEach var="cur" items="${entityList}" varStatus="vs">
		       <tr>
		            <td align="left" nowrap="nowrap">${cur.FUNCDESC }</td>
		            <td align="left" nowrap="nowrap" class="servercount" >${cur.SERVERCOUNT }</td>
		            <td align="left" nowrap="nowrap" bgcolor="#FFFFCC">${cur.LASTONE }</td>
		            <td align="left" nowrap="nowrap">${cur.FIRSTONE }</td>
		            <td align="left" nowrap="nowrap">${cur.SERVERAVG }</td>
		        </tr>
		</c:forEach>
      </table>
  </div>
  <div>
  	<p >
  	<label>当前月共提交服务次数:<a id="allcount"></a>
  	</label>
  	</p>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">//<![CDATA[
	$(document).ready(function(){
		var i = 0 ;
		$(".servercount").each(function(){
		    i += Number($(this).text());
		  });
		$("#allcount").text(i);
		
		$("#CLS").click(function(){
			$("#CLS").attr("value", "执行中...").attr("disabled", "true");
			$("#GET").attr("disabled", "true");
			$("#method").val("edit");
			$("form").submit();
		});
		
		
	}); 
//]]>
</script>
</body>
</html>