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
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
  		<tr class="tabtt1">
  			<td width="5%" align="center" nowrap="nowrap">序号</td>
        	<td align="center" nowrap="nowrap">客户名称</td>
        	<td align="center" nowrap="nowrap">R3编码</td>        	
            <td width="3%" nowrap="nowrap" align="center">进货量</td>
        	<td width="6%" nowrap="nowrap" align="center">进货额</td>       	
        	<td width="3%" nowrap="nowrap" align="center">销售量</td>
        	<td width="6%" nowrap="nowrap" align="center">销售额</td>  
         	<td width="6%" nowrap="nowrap" align="center">库存量</td>
        	<td width="3%" nowrap="nowrap" align="center">库存额</td>    
        	<td width="6%" nowrap="nowrap" align="center">周转天数（量）</td>
        	<td width="3%" nowrap="nowrap" align="center">周转天数（额）</td>   
        	<td width="4%" align="center" nowrap="nowrap">分公司</td>
        	<td width="6%" align="center" nowrap="nowrap">一级部门</td>
        	<td width="6%" align="center" nowrap="nowrap">二级部门</td>
        	<td align="center" nowrap="nowrap">业务员</td>
        	<td width="6%" align="center" nowrap="nowrap">客户类型</td>
        	<td width="6%" align="center" nowrap="nowrap">客户细分类型</td>        	
  		</tr>
  		<c:forEach var="cur" items="${entityList}" varStatus="vs">
  			<tr class="list-tr">
  				<td align="center" nowrap="nowrap">${vs.count}</td>
  				<td align="left" nowrap="nowrap">
  					<span title="点击可查看该客户详情" class="fblue" style="cursor:pointer;" onclick="showModels('${cur.CUST_NAME}','${cur.R3_ID}','${cur.R3_CODE}');">
  						<font class="blue12px">${cur.CUST_NAME}</font>
  					</span>
  				</td>
  				<td align="left" nowrap="nowrap">
  					<span title="点击可查看该客户详情" class="fblue" style="cursor:pointer;" onclick="showModels('${cur.CUST_NAME}','${cur.R3_ID}','${cur.R3_CODE}');">
  						<font class="blue12px">${cur.R3_CODE}</font>
  					</span>
  				</td>  				
  				<td align="right" nowrap="nowrap" ><fmt:formatNumber value="${cur.IN_NUM}" pattern="#,###"/></td> 
   				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.IN_MONEY}" pattern="#,##0.00"/></td>
  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.OUT_NUM}" pattern="#,###"/></td>
  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.OUT_MONEY}" pattern="#,##0.00"/></td>
  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.STORE_NUM}" pattern="#,###"/></td>
  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.STORE_MONEY}" pattern="#,##0.00"/></td>
  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.ZZ_NUM}" pattern="#,##0.00"/></td>
  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.ZZ_MONEY}" pattern="#,##0.00"/></td>
  				<td align="left" nowrap="nowrap">${cur.FGS_NAME}</td>
  				<td align="left" nowrap="nowrap">${cur.DEPT_NAME1}</td>
  				<td align="left" nowrap="nowrap">${cur.DEPT_NAME2}</td>  	
  				<td align="left" nowrap="nowrap">${cur.YWY_NAME}</td>
  				<td align="left" nowrap="nowrap">${cur.CUST_TYPE1}</td>
  				<td align="left" nowrap="nowrap">${cur.CUST_TYPE2}</td>
  			</tr>
  		</c:forEach>
  	</table>
  </div>
</body>
</html>