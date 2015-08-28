<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.operate {
	height: 40px;
	width: 150px;
	magrgin-top:20px;
	padding-top:10px;
	margin-left:150px;
	bgcolor:grey;
	float:center;
}

.op {
	height: 40px;

	width: 150px;
	margin-left:10px;
	magrgin-top: 10px;
}

</style>


</head>
<body>
<div class="oarcont">
<div class="oartop">
<table width="400" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="3%"><img
			src="${ctx}/styles/admin-index/images/k_tup.jpg"
			style="vertical-align: middle;" /></td>
		<td>当前位置：${naviString}</td>
	</tr>
</table>
</div>
<div class="rtabcont1"><%@ include
	file="/commons/pages/messages.jsp"%></div>
<div class="rtabcont2">

<table style="width:100%;">
  	<tr style="magrgin-top:20px;"><td></td><td align="left" colspan="3"><font color="red" size="2">定时器手动触发</font></td></tr>
  	 <tr>
	    <td width="30%"></td> 
	    <td><input class="op" type="button" value="同步回款" onclick="operate(1);"></input></td> 
	    <td><input class="op" type="button" value="同步账期"	onclick="operate(2);"></input></td> 
	    <td width="30%"></td>
 	</tr>
	<tr>
	    <td width="30%"></td> 
	    <td><input class="op" type="button" value="同步集采数据" onclick="operate(3);"></input></td> 
	    <td><input class="op" type="button" value="同步客户产品数据" onclick="operate(4);"></input></td> 
	    <td width="30%"></td>
 	</tr>
 	<tr>
	    <td width="30%"></td> 
	    <td><input class="op" type="button" value="邮件发送" onclick="operate(5);"></input></td> 
	    <td><input class="op" type="button" value="客户分类统计表-年度" onclick="operate(6);"></input></td> 
	    <td width="30%"></td>
 	</tr>
 	<tr>
	    <td width="30%"></td> 
	    <td><input class="op" type="button" value="客户分类统计表-月度" onclick="operate(7);"></input></td> 
	    <td><input class="op" type="button" value="自动结转客户库存" onclick="operate(9);"></input></td> 
	    <td width="30%"></td>
 	</tr>
 	<tr>
	    <td width="30%"></td> 
	    <td><input class="op" type="button" value="初始化零售统计数据" onclick="operate(10);"></input></td> 
	    <td><input class="op" type="button" value="初始化统计区域数据" onclick="operate(11);"></input></td> 
	    <td width="30%"></td>
 	</tr>
 	<tr>
	    <td width="30%"></td> 
	    <td><input class="op" type="button" value="初始化统计分公司数据" onclick="operate(12);"></input></td> 
	    <td><input class="op" type="button" value="初始化统计门店数据" onclick="operate(13);"></input></td> 
	    <td width="30%"></td>
 	</tr>
 	<tr>
	    <td width="30%"></td> 
	    <td><input class="op" type="button" value="初始化统计时间数据" onclick="operate(14);"></input></td> 
	    <td><input class="op" type="button" value="初始化统计商品数据" onclick="operate(15);"></input></td> 
	    <td width="30%"></td>
 	</tr>
 	
 	<tr>
	    <td width="30%"></td> 
	    <td><input class="op" type="button" value="同步电子商务订单至DRP" onclick="operate(16);"></input></td> 
	    <td><input class="op" type="button" value="客户库存汇总(每2小时)" onclick="operate(17);"></input></td>
	    <td width="30%"></td>
 	</tr>
 	
 	<c:if test="${not empty is_superUser}">
 	<tr style="magrgin-top:20px;"><td></td><td align="left" colspan="3"><font color="red" size="2">客户库存清理</font></td></tr>
 		<tr>
	    <td width="30%"></td> 
	    <td>r3编码:<input style="width:110px;"  name="r3_code" id="r3_code" /></td> 
	    <td><input class="op" style="height:30px;width:150px;" type="button" value="清空客户库存" onclick="operate1(8);"></input></td> 
	    <td width="30%"></td>
 	</tr>
 	</c:if>
</table>

<div>


</div>


</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript"
	src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
<script type="text/javascript">
	//<![CDATA[
	$(document).ready(function() {

	});
	function operate(type) {
		doNeedMethod("确认执行此操作吗？", 'HandOperate.do', 'Operate','type=' + type);
	}
function operate1(type) {
     var r3_code=$("#r3_code").val();
     if(""==r3_code){
      return;
         }
     var flag=confirm("确定清空客户"+r3_code+"的库存记录?");
      if(flag){
		doNeedMethod(null, 'HandOperate.do', 'Operate','type=' + type+'&r3_code='+r3_code);
	}
     
	}
	//]]>
</script>
</body>
</html>