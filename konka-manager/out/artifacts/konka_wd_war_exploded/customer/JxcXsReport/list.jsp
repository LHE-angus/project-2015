<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base target="_self" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/manager/scripts/charts.js"></script>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont">
<div class="oartop">
  <table width="400" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
      <td nowrap="nowrap">当前位置：${naviString}</td>
    </tr>
  </table>
</div>
<div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
</div>
<%@ include file="search.jsp" %>
<c:if test="${not empty entityList}">
	<div class="rtabcont1" style="overflow-x:auto;">
		<div id="divExcel" title="交易明细表">
			<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
				<tr>
					<th nowrap="nowrap" align="center" width="5%">行号</th>
					<th nowrap="nowrap" align="center">销售网点</th>
					<th nowrap="nowrap" align="center">网点级别</th>
					<th nowrap="nowrap" align="center" >商品</th>
					<th nowrap="nowrap" align="center" width="10%">交易类型</th>
					<th nowrap="nowrap" align="center" width="10%">日期</th>
					<th nowrap="nowrap" align="center" width="10%">价格</th>
					<th nowrap="nowrap" align="center" width="10%">数量</th>
					<th nowrap="nowrap" align="center" width="10%">成本</th>
					<th nowrap="nowrap" align="center" width="10%">金额</th>
					<th nowrap="nowrap" align="center" width="20%">交易单号</th>
				</tr>
				<c:set var="total_num" value="0" />
				<c:set var="total_money" value="0" />
				<c:set var="total_cost" value="0" />
				<c:forEach items="${entityList}" var="cur" varStatus="vs">
				<tr>
					<td nowrap="nowrap" align="center">${vs.count}</td>
					<td nowrap="nowrap" align="left">${cur[13]}</td>
					<td nowrap="nowrap" align="left"><c:if test="${not empty cur[12]}">${cur[12]}级网点</c:if></td>
					<td nowrap="nowrap" align="left">${cur[1]}</td>
					<td nowrap="nowrap" align="center">${cur[2]}</td>
					<td nowrap="nowrap" align="center">${fn:substring(cur[3], 0, 10)}</td>
					<td nowrap="nowrap" align="right"><span class="kz-price-12"><fmt:formatNumber value="${cur[4]}" type="currency" /></span></td>				
					<td nowrap="nowrap" align="right">${cur[5]}</td>
					<td nowrap="nowrap" align="right"><span class="kz-price-12"><fmt:formatNumber value="${cur[7]}" type="currency" /></span>
					</td>
					<td nowrap="nowrap" align="right"><span class="kz-price-12"><fmt:formatNumber value="${cur[6]}" type="currency" /></span></td>
					<td nowrap="nowrap" align="center">${cur[8]}</td>
				</tr>
				<c:set var="total_num" value="${total_num+cur[5]}" />
				<c:set var="total_money" value="${total_money+cur[6]}" />
				<c:set var="total_cost" value="${total_cost+cur[7]}" />
				</c:forEach>
				<tr>
					<td align="center" colspan="2"><span style="font-size:14px;font-family:verdana;font-weight:700;">总计</span></td>
					<td align="center"><span style="font-size:14px;font-family:verdana;font-weight:700;">/</span></td>
					<td align="center"><span style="font-size:14px;font-family:verdana;font-weight:700;">/</span></td>
					<td align="center"><span style="font-size:14px;font-family:verdana;font-weight:700;">/</span></td>
					<td align="right"><span style="font-size:14px;font-family:verdana;font-weight:700;">${total_num}</span></td>
					<td align="right"><span class="kz-price"><fmt:formatNumber value="${total_cost}" type="currency" /></span></td>
					<td align="right"><span class="kz-price"><fmt:formatNumber value="${total_money}" type="currency" /></span></td>
					<td align="center"><span style="font-size:14px;font-family:verdana;font-weight:700;">/</span></td>
					<td align="center"><span style="font-size:14px;font-family:verdana;font-weight:700;">/</span></td>
					<td align="center"><span style="font-size:14px;font-family:verdana;font-weight:700;">/</span></td>
				</tr>
			</table>
		</div>
	</div>
</c:if>

<div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">

selectGoodType('${af.map.type_id}');
   
function selectGoodType(type_id){
	$.ajax({
		type: "POST",
		url: "JBill.do",
		data: {method : "ajaxGetGoodsList", "type_id": type_id},
		dataType: "json",
		cache:false,
		error: function(){alert("数据加载请求失败！");},
		success: function(ret){
			if(ret){
				$("#goods_id").empty();
				var html = "<option value=''>请选择</option>";
				for(var i=0; i<ret.list.length; i++){
					if(${not empty af.map.goods_id}){
						var goods_id_ = '${af.map.goods_id}';
						if(goods_id_ == ret.list[i].goods_id){
							html += "<option value='" + ret.list[i].goods_id + "' selected='selected'>" + ret.list[i].goods_name +"</option>";
						}else {
							html += "<option value='" + ret.list[i].goods_id + "'>" + ret.list[i].goods_name +"</option>";
						}
					}else {
						html += "<option value='" + ret.list[i].goods_id + "'>" + ret.list[i].goods_name +"</option>";
					}			
				}
				$("#goods_id").html(html);

			}
		}
   });
}

</script>
<jsp:include page="/customer/__analytics.jsp" />
</body>