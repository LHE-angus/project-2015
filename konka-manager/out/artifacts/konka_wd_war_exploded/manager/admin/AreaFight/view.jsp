<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
</head>
<body >
   <div>
    <table width="100%" border="1" cellspacing="0" cellpadding="0" class="rtable2">
  		<tr>
  			<td width="5%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">序号</td>
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">分公司</td>
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">区域</td>  
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">省</td>  
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">市</td>
        	<td width="3%" nowrap="nowrap" align="center" style="background-color: #97FFFF;">县(区)</td>
        	<td width="6%" nowrap="nowrap" align="center" style="background-color: #97FFFF;">乡镇街</td>  
        	<td width="4%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">乡镇街编码</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">类型</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">所辖村数(个)</td>
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">GDP(万元)</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">人口(万人)</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">面积(平方公里)</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">市场容量(数量：台)</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">市场容量(金额：万元)</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">家电入驻</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">康佳入驻</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">客户数</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">门店/网点数</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">本年结算额(万元)</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">本年结算量(台)</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">本年销售额(万元)</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">本年销售量(台)</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">康佳排名</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">市场占有率</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">第一对手</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">第二对视</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">第三对手</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">康佳未进入原因</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">预计进入日期</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">乡镇状态</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">维护人</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">维护日期</td>
  		</tr>
  		<c:forEach var="cur" items="${allList}" varStatus="vs">
  			<tr class="list-tr">
  				<td>${vs.count}</td>
  				<td align="left" nowrap="nowrap">${cur.DEPT_NAME}</td>
  				<td align="left" nowrap="nowrap">${cur.AREA_NAME}</td>
  				<td align="left" nowrap="nowrap">${cur.PROVINCE }</td>
  				<td align="left" nowrap="nowrap">${cur.CITY }</td>
  				<td align="right" nowrap="nowrap">${cur.COUNTRY }</td>
  				<td align="right" nowrap="nowrap">${cur.TOWN }</td>
  				<td align="left" nowrap="nowrap">${cur.P_INDEX}</td>
  				<td align="left" nowrap="nowrap">
  					<c:choose>
  						<c:when test="${cur.T_TYPE eq 1 }">乡镇</c:when>
  						<c:when test="${cur.T_TYPE eq 2 }">街道</c:when>
  						<c:when test="${cur.T_TYPE eq 3 }">开发区</c:when>
  						<c:otherwise>未指定</c:otherwise>
  					</c:choose>
  				</td>  	
  				<td align="left" nowrap="nowrap">${cur.T_NUM}</td>
  				<td align="left" nowrap="nowrap">${cur.GDP}</td>
  				<td align="left" nowrap="nowrap">${cur.HUMAN}</td>
  				<td align="left" nowrap="nowrap">${cur.AREA_SIZE}</td>
  				<td align="left" nowrap="nowrap">${cur.MARKET_NUM}</td>
  				<td align="left" nowrap="nowrap">${cur.MARKET_MONEY}</td>
  				<td align="left" nowrap="nowrap">
  					<c:choose>
  						<c:when test="${cur.JD_IN eq 0 }">是</c:when>
  						<c:when test="${cur.JD_IN eq 1 }">否</c:when>
  						<c:otherwise>未指定</c:otherwise>
  					</c:choose>
  				</td>
  				<td align="left" nowrap="nowrap">
  					<c:choose>
  						<c:when test="${cur.KONKA_IN eq 0 }">是</c:when>
  						<c:when test="${cur.KONKA_IN eq 1 }">否</c:when>
  						<c:otherwise>未指定</c:otherwise>
  					</c:choose>
  				</td>
  				<td align="left" nowrap="nowrap">${cur.KH_NUM}</td>
  				<td align="left" nowrap="nowrap">${cur.AGENT_NUM}</td>
  				<td align="left" nowrap="nowrap">${cur.CCOUNT_MONEY}</td>
  				<td align="left" nowrap="nowrap">${cur.CCOUNT_NUM}</td>
  				<td align="left" nowrap="nowrap">${cur.SALE_MONEY}</td>
  				<td align="left" nowrap="nowrap">${cur.SALE_NUM}</td>
  				<td align="left" nowrap="nowrap">
  					<c:choose>
  						<c:when test="${not empty cur.KONKA_RANK}">第${cur.KONKA_RANK }名</c:when>
  						<c:when test="${empty cur.KONKA_RANK }">未指定</c:when>
  						<c:otherwise>未指定</c:otherwise>
  					</c:choose>
  				</td>
  				<td align="left" nowrap="nowrap">${cur.FIRST_COMP}</td>
  				<td align="left" nowrap="nowrap">${cur.SECOND_COMP}</td>
  				<td align="left" nowrap="nowrap">${cur.THIRD_COMP}</td>
  				<td align="left" nowrap="nowrap">${cur.NOT_IN_REASON}</td>
  				<td align="left" nowrap="nowrap">${cur.IN_DATE}</td>
  				<td align="left" nowrap="nowrap">${cur.T_STATUS}</td>
  				<td align="left" nowrap="nowrap">${cur.MODIFY_NAME}</td>
  				<td align="left" nowrap="nowrap">${cur.MODIFY_DATE}</td>
  				
  			</tr>
  		</c:forEach>
  	</table>
</div>
<script type="text/javascript"><!--//<![CDATA[
//]]>--></script>
</body>
</html>
