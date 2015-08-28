<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单打印</title>
<style>
	table{
		border-collapse:collapse;
	}
	table tr{
	    line-height:25px;
	}
	body{
		font-size:15px;
	}
</style>
<style>
/*
 *    打印相关
*/ 
@media print 
{
    .divprint
    {
        page-break-after:always;
    }     
}

</style>
</head>
<body>
<html-el:form  action="/admin/KonkaOrderInfoTrans.do" method="post">
					<html-el:hidden property="method" value="printSave" />
<c:forEach var="cur" items="${entityList}" varStatus="vs">
<input type="hidden" name="pks" id="pks" value="${cur.map.trans_index_detail}" />
<div class="divprint">
<table border="0" width="90%" align="center">
<tr>
	<td colspan="6" align="center"><font style="font-size:25px;">康佳集团股份有限公司</font></td>
</tr>
<tr>
	<td colspan="6" align="center"><font style="font-size:20px;">货物运输装载单</font></td>
</tr>
<tr>
	<td colspan="6" align="right"><img width="120px" height="120px" src="${ctx}/images/QRCode.jpg?s=http://qdgl.konka.com/webservice/KonkaOrderInfoTrans.do?param=${trans_index_detail},${user_id},${password}" /></td>
</tr>
<tr>
	<td width="20%">发货地点:</td>
	<td colspan="5">&nbsp;${cur.address }</td>
</tr>
<tr>
	<td nowrap="nowrap">发货单位（或个人）:</td>
	<td width="20%">&nbsp;</td>
	<td nowrap="nowrap">&nbsp;</td>
	<td width="17%">&nbsp;</td>
	<td nowrap="nowrap">运单编号:</td>
	<td width="15%" align="left">&nbsp;${cur.map.trans_index_detail }</td>
</tr>
<tr>
	<td nowrap="nowrap">发货地址:</td>
	<td align="left">&nbsp;${cur.map.trans_recl_addr }</td>
	<td nowrap="nowrap">委托收货人:</td>
	<td align="left">&nbsp;${cur.map.trans_recl_user }</td>
	<td nowrap="nowrap">联系电话:</td>
	<td align="left">&nbsp;${cur.map.trans_recl_user_phone }</td>
</tr>
</table>
  <table border="1" width="90%" align="center" >
<tr>
	<td nowrap="nowrap" colspan="1" width='140px'>承运单位:</td>
	<td width="20%" colspan="2">&nbsp;${cur.trans_unit}</td>
	<td nowrap="nowrap" colspan="1" width='140px'>身份证号码:</td>
	<td width="20%" colspan="2">&nbsp;${cur.link_id}</td>
	<td nowrap="nowrap" colspan="1" width='140px'>起运开始时间:</td>
	<td width="20%" colspan="2">&nbsp;<fmt:formatDate value="${cur.trans_plan_date }" pattern="yyyy-MM-dd"/></td>
</tr>
<tr>
	<td nowrap="nowrap" colspan="1" width='140px'>司机姓名:</td>
	<td width="20%" colspan="2">&nbsp;${cur.link_name }</td>
	<td nowrap="nowrap" colspan="1" width='140px'>承运车辆情况:</td>
	<td width="20%" colspan="2">&nbsp;${cur.car_desc }</td>
	<td nowrap="nowrap" colspan="1" width='140px'>约定送达时间:</td>
	<td width="20%" colspan="2">&nbsp;<fmt:formatDate value="${cur.trans_arri_plan_date }" pattern="yyyy-MM-dd"/></td>
</tr>
<tr>
	<td nowrap="nowrap" colspan="1" width='140px'>联系电话:</td>
	<td width="20%" colspan="2">&nbsp;${cur.link_phone}</td>
	<td nowrap="nowrap" colspan="1" width='140px'>约定到车时间:</td>
	<td width="20%" colspan="2">&nbsp;<fmt:formatDate value="${cur.prom_plan_date }" pattern="yyyy-MM-dd"/></td>
	<td nowrap="nowrap" colspan="1" width='140px'>实际送达时间:</td>
	<td width="20%" colspan="2">&nbsp;<fmt:formatDate value="${cur.trans_real_arri_date }" pattern="yyyy-MM-dd"/></td>
</tr>
<tr>
	<td nowrap="nowrap" colspan="1" width='140px'>车牌号:</td>
	<td width="20%" colspan="2">&nbsp;${cur.map.link_car_no }</td>
	<td nowrap="nowrap" colspan="1" width='140px'>实际到车时间:</td>
	<td width="20%" colspan="2">&nbsp;<fmt:formatDate value="${cur.prom_real_date }" pattern="yyyy-MM-dd"/></td>
	<td nowrap="nowrap" colspan="1" width='140px'>卸载完成时间:</td>
	<td width="20%" colspan="2">&nbsp;<fmt:formatDate value="${cur.trans_fini_date }" pattern="yyyy-MM-dd"/></td>
</tr>

<tr>
	<td align="center" colspan="1" width='140px'>产品名称</td>
	<td align="center" colspan="2">数量</td>
	<td align="center" colspan="1">发机单号码</td>
	<td align="center" colspan="2">实收数量</td>
	<td align="center" colspan="3">收货验收记录</td>
</tr>
<c:forEach items="${cur.konkaOrderInfoTransDetailsList }" var="cur1">
	<tr>
		<td  colspan="1">&nbsp;${cur1.model_name}</td>
		<td  colspan="2" align="center">${cur1.model_num}&nbsp;</td>
		<td  colspan="1">&nbsp;${cur1.r3_vbedl}</td>
		<td  colspan="2">&nbsp;</td>
		<td  colspan="3">&nbsp;</td>
	</tr>
</c:forEach>
<tr>
		<td  colspan="1">&nbsp;</td>
		<td  colspan="2">&nbsp;</td>
		<td  colspan="1">&nbsp;</td>
		<td  colspan="2">&nbsp;</td>
		<td  colspan="3">&nbsp;</td>
</tr>
<tr>
	<td colspan="3">
		<table border="0" width="100%">
			<tr>
				<td width="140px">物流分部盖章:</td>
				<td width="140px">&nbsp;</td>
			</tr>
			<tr>
				<td width="140px">经办人签字:</td>
				<td >&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td align="">   年     &nbsp;    月   &nbsp;      日</td>
			</tr>
			<tr>
				<td  clospan="3" align="center">物流业务投诉电话:</td>
			</tr>
			<tr>
				<td clospan="3" align="center">0755-26924643</td>
			</tr>
		</table>
	</td>
	<td colspan="3">
		<table border="0" width="100%">
			<tr>
				<td width="200px">承运单位盖章:</td>
				<td width="140px">&nbsp;</td>
			</tr>
			<tr>
				<td width="200px">委托人签字:</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td align="">   年    &nbsp;     月   &nbsp;      日</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</table>
	</td>
	<td colspan="3">
		<table border="0" width="100%">
			<tr>
				<td width="200px">收货单位盖章:</td>
				<td width="140px">&nbsp;</td>
			</tr>
			<tr>
				<td width="300px">委托收货人签字:</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				
				<td align="right">   年    &nbsp;     月   &nbsp;      日</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</table>
	</td>
</tr>
</table>
<table border="0" width="90%" align="center">
<tr>
	<td>&nbsp;</td>
	<td>编制:</td>
	<td>&nbsp;</td>
	<td>&nbsp;</td>
	<td>日期:</td>
	<td>&nbsp;</td>
</tr>
<tr>
	<td>注:</td>
	<td>&nbsp;</td>
	<td>&nbsp;</td>
	<td>&nbsp;</td>
	<td>&nbsp;</td>
	<td>&nbsp;</td>
</tr>
</table>
<br/>
</div>
</c:forEach>
	<div align="center" id="div_button">
	  <input name="print" type="button" class="bgButtonPrint" value="打印"/>
	  <input name="close" type="button" class="bgButtonBack" value="关闭" />
	</div>

</html-el:form>

<script type="text/javascript" src="${ctx}/scripts/print.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
var f = document.forms[0];
$(document).ready(function(){
	$(".bgButtonPrint").click(function(){
		$(this).attr("disabled","true");
		$("#div_button").hide();
		window.print();
		$("#div_button").show();
		f.submit();
	});
	 $(".bgButtonBack").click(function(){
		 window.close();
	});
});
		
//]]></script>
</body>
</html>