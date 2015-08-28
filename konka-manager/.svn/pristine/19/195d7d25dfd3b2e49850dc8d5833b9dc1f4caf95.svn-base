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
<body >
<div class="oarcont">
  <div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：统计数据</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2" >
      <table width="100%" border="0" cellspacing="5" cellpadding="0" >
        <tr>
        	<td>
	        	<input type="button" style="cursor: pointer;" class="but8" id="syncBtn" value="同步数据"></input>
        	</td>
        </tr>
        <tr>
        	<td>
        		<span id='msgs' style="display: none">正在统计中，请稍后。。。</span>
        		<span id='data_text'></span>
        	</td>
        </tr>
      </table>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/common.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#syncBtn").click(function(){
		$("#msgs").show();
		$.post('${ctx}/manager/admin/KonkaCountData.do?method=computerData',function(result){
			$("#msgs").hide();
			$("#data_text").text(result.OPRATION_COMMENT);
		},'json');
	});
});
</script>
</body>
</html>
