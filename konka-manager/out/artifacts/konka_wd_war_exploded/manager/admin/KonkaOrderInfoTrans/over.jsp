<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css"
	rel="stylesheet" type="text/css" />
<style type="text/css">
select {
	font-family: Microsoft YAHEI;
	font-size: 12px;
}

input {
	font-family: Microsoft YAHEI;
	font-size: 12px;
}

label {
	cursor: pointer;
}

td {
	align: center;
	nowrap: nowrap;
}

.title_item_no_bc {
	align: right;
	text-align: right;
	width: 100px;
	font-weight: bold;
	color: #74685F;
}
</style>
</head>
<body style="font-family: Microsoft Yahei;">
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
		<div class="rtabcont2">
			<div class="tab1" style="overflow-x: auto;">
				<html-el:form  action="/admin/KonkaOrderInfoTrans.do" method="post">
					<html-el:hidden property="method" value="overSave" />

					<div align="left" style="font-weight: bold; color: #74685F;margin-bottom:10px;">发货单详细</div>
					<div style="overflow-x:scoll;">
					<table width="100%" id="fhdetails" border="0" cellspacing="0" cellpadding="0" 
						class="rtable2">
						<tr class="tabtt1">
							<td align="center" nowrap="nowrap">物流交货单号</td>
							<td align="center" nowrap="nowrap">订单流水号</td>
							<td align="center" nowrap="nowrap">提交日期</td>
							<td align="center" nowrap="nowrap">客户名称</td>
							<td align="center" nowrap="nowrap">型号</td>
							<td align="center" nowrap="nowrap">数量</td>
							<td align="center" nowrap="nowrap">已发货数量</td>
							<td align="center" nowrap="nowrap">已签收数量</td>
							<td align="center" nowrap="nowrap">未签收数量</td>
						</tr>
						<c:if test="${not empty entityList}">
							<c:forEach var="cur" items="${entityList}" varStatus="vs">
								<input type="hidden" name="pks" id="pks" value="${cur.trans_index_detail}" />
								<tr>
									<td nowrap="nowrap">${cur.r3_vbedl }</td>
									<td nowrap="nowrap" align="center">${cur.trade_index }</td>
									<td nowrap="nowrap" align="center"><fmt:formatDate value="${cur.add_date }" pattern="yyyy-MM-dd"/></td>
									<td nowrap="nowrap">${cur.map.customer_name }</td>
									<td nowrap="nowrap">${cur.model_name }</td>
									<td nowrap="nowrap" align="right">${cur.model_num }</td>
									<td nowrap="nowrap" align="right">${cur.trans_num }</td>
									<td nowrap="nowrap" align="right">${cur.trans_ensured_num }</td>
									<td nowrap="nowrap" align="right">${cur.trans_num-cur.trans_ensured_num }</td>
								</tr>
							</c:forEach>
						</c:if>
						<tbody class="add_tbody"></tbody>
					</table>
					</div>
					<!--上面的详情list---end -->
<!-- 					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2"> -->
<!-- 					<tr> -->
<!-- 						<td>&nbsp;</td> -->
<!-- 						<td align="center"> -->
							<div align="center" id="div_button">
							  <input name="over" type="button" id="btn_submit" class="but4" value="结案"/>
							  <input name="close" type="button" class="bgButtonBack" value="关闭" />
							</div>	
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 					</table> -->
				</html-el:form>
			</div>



		</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">                                    
var f = document.forms[0];
$(document).ready(function(){
	 $(".bgButtonBack").click(function(){
		 window.close();
	});

	$("#btn_submit").click(function(){
		if(confirm("您确认结案吗？")){
// 			$(this).attr("disabled","true");
			f.submit();
		}
	});
});
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>