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
          <td width="25%">
          	<strong class="fb">&nbsp;&nbsp;客户名称：</strong>${kh_name}
          </td>
          <td width="25%">
          	<strong class="fb">客户R3编码：</strong>${r3_code}
          </td>
        </tr>
    </table>
    <div style="text-align:right;padding-right: 10px">单位：台、元</div>
  	<div class="rtabcont1" style="overflow-x: auto;" id="divExcel_all" title="销售明细">
  	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
  		<tr class="tabtt1">
  			<td width="5%" align="center" nowrap="nowrap">序号</td>
  			<c:if test="${ope_type eq 'in'}">
  			<td width="5%" align="center" nowrap="nowrap">送达方编码</td>
  			<td width="5%" align="center" nowrap="nowrap">送达方名称</td>
  			</c:if>       	
  			<c:if test="${ope_type eq 'out'}">
  			<td width="5%" align="center" nowrap="nowrap">终端ID</td>
  			<td width="5%" align="center" nowrap="nowrap">终端名称</td>
  			</c:if>       	
        	<td width="10%" align="center" nowrap="nowrap">型号名称</td>        	
            <td width="10%" nowrap="nowrap" align="center">时间</td>
        	<td width="6%" nowrap="nowrap" align="center">数量</td>       	
        	<td width="10%" nowrap="nowrap" align="center">金额</td>
        	<td width="6%" nowrap="nowrap" align="center">方向</td>  
         	<td width="6%" nowrap="nowrap" align="center">类型</td>
  		</tr>
  		<c:forEach var="cur" items="${entityList}" varStatus="vs">
  			<tr class="list-tr">
  				<td align="center" nowrap="nowrap">${vs.count}</td>
  				<c:if test="${ope_type eq 'in'}">
	  			<td align="center" nowrap="nowrap">${cur.SDF_CODE }</td>
	  			<td align="left" nowrap="nowrap">${cur.SDF_NAME}</td>
	  			</c:if>       	
	  			<c:if test="${ope_type eq 'out'}">
	  			<td width="5%" align="center" nowrap="nowrap">${cur.STORE_ID }</td>
	  			<td width="5%" align="center" nowrap="nowrap">${cur.STORE_NAME }</td>
	  			</c:if>
  				<td align="left" nowrap="nowrap">${cur.MODEL_NAME}</td>
  				<td align="center" nowrap="nowrap">
  					<fmt:formatDate value="${cur.DATE}" pattern="yyyy-MM-dd HH:mm:ss" />
  				</td>  				
  				<td align="right" nowrap="nowrap">
  					<fmt:formatNumber value="${cur.NUM}" pattern="#,###"/>
  				</td>  				
  				<td align="right" nowrap="nowrap" >
  					<fmt:formatNumber value="${cur.MONEY}" pattern="#,##0.00"/>
  				</td> 
  				<td align="center" nowrap="nowrap">${cur.DIRE }</td>
  				<td align="center" nowrap="nowrap">${cur.TYPE}</td>
  			</tr>
  		</c:forEach>
  	</table>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
</body>
</html>