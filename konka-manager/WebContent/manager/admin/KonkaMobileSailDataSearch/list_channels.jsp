<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
.rtable1 td {
	padding:2px 5px;
}


.i {
color: #f60;
font-style: normal;
}

</style>
</head>
<body>
<div class="oarcont" id="body_oarcont">
	<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td>
          	<strong class="fb">&nbsp;&nbsp;客户类型：</strong>${c_comm}
          </td>
        </tr>
    </table>
    <div style="text-align:right;padding-right: 10px">单位：台、元</div>
  	<div class="rtabcont1" style="overflow-x: auto;" id="divExcel_all" title="销售明细">
  	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
  		<tr class="tabtt1">
  			<td width="5%" align="center" nowrap="nowrap">序号</td>
        	<td width="10%" align="center" nowrap="nowrap">细分类型名称</td>
            <td width="10%" nowrap="nowrap" align="center">进货量</td>
        	<td width="10%" nowrap="nowrap" align="center">进货额</td>       	
        	<td width="10%" nowrap="nowrap" align="center">销售量</td>
        	<td width="10%" nowrap="nowrap" align="center">销售额</td>  
         	<td width="10%" nowrap="nowrap" align="center">库存量</td>
        	<td width="10%" nowrap="nowrap" align="center">库存额</td>    
        	<td width="10%" nowrap="nowrap" align="center">周转天数（量）</td>
        	<td width="10%" nowrap="nowrap" align="center">周转天数（额）</td>   
  		</tr>
  		<c:forEach var="cur" items="${entityList}" varStatus="vs">
  			<tr class="list-tr">
  				<td align="center" nowrap="nowrap">${vs.count}</td>
  				<td align="left" nowrap="nowrap">${cur.C_NAME}</td>  				
  				<td align="right" nowrap="nowrap" ><fmt:formatNumber value="${cur.IN_NUM}" pattern="#,###"/></td> 
   				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.IN_MONEY}" pattern="#,##0.00"/></td>
  				<td align="right" nowrap="nowrap" ><fmt:formatNumber value="${cur.OUT_NUM}" pattern="#,###"/></td> 
  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.OUT_MONEY}" pattern="#,##0.00"/></td>
  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.STORE_NUM}" pattern="#,###"/></td>
  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.STORE_MONEY}" pattern="#,##0.00"/></td>
  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.ZZ_NUM}" pattern="#,##0.00"/></td>
  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.ZZ_MONEY}" pattern="#,##0.00"/></td>
  			</tr>
  		</c:forEach>
  	</table>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=discuz"></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>