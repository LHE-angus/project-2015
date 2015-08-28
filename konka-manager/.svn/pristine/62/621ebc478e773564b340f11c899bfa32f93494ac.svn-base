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
<body>
<div class="oarcont" id="body_oarcont">
  <div class="rtabcont1" id="divExcel" title="明细表${date}">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
	     <td width="5%" align="center" nowrap="nowrap">序号</td>
        <td align="center" nowrap="nowrap">日期</td>
        <td width="4%" align="center" nowrap="nowrap">分公司</td>
        <td width="6%" align="center" nowrap="nowrap">经办</td>
        <td align="center" nowrap="nowrap">客户名称</td>
        <td align="center" nowrap="nowrap">客户R3编码</td>
        <td align="center" nowrap="nowrap">门店R3编码</td>
        <td width="6%" align="center" nowrap="nowrap">客户类型</td>
        <td width="6%" align="center" nowrap="nowrap">细分类型</td>
        <td width="4%" nowrap="nowrap" align="center">上报人</td>
        <td align="center" nowrap="nowrap">上报门店</td>
        <td align="center" nowrap="nowrap">门店ID</td>
        <td width="3%" nowrap="nowrap" align="center">尺寸</td>
        <td width="8%" nowrap="nowrap" align="center">产品型号</td>
        <td width="8%" nowrap="nowrap" align="center">串码</td>
        <td width="3%" nowrap="nowrap" align="center">数量</td>
        <td width="6%" nowrap="nowrap" align="center">挂牌价</td>
        <td width="6%" nowrap="nowrap" align="center">上样时间</td>
        <td width="6%" nowrap="nowrap" align="center">下架时间</td>
        <td width="6%" nowrap="nowrap" align="center">状态</td>
        <td width="6%" nowrap="nowrap" align="center">总销售量</td>
        <td width="6%" nowrap="nowrap" align="center">总销售额</td>
         <td width="6%" nowrap="nowrap" align="center">正面</td>
        <td width="6%" nowrap="nowrap" align="center">背面</td>
        <td width="6%" nowrap="nowrap" align="center">侧面</td>
        <td width="12%" nowrap="nowrap" align="center">备注</td>
<!--         <td width="12%" nowrap="nowrap" align="center">数据来源</td> -->
        </tr>
        <c:set var="now">
      		<fmt:formatDate value="${today}" pattern="yyyy-MM-dd" />
    	</c:set>
        <c:forEach var="cur" items="${entityList1}" varStatus="vs">
        <tr class="list-tr">
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
          <td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.report_date}" pattern="yyyy/MM/dd" /></td>
          <td align="left" nowrap="nowrap">${cur.subcomp_name}</td>
          <td align="left" nowrap="nowrap">${cur.office_name}</td>
          <td align="left" nowrap="nowrap">${cur.channel_a_name}</td>
          <td align="left" nowrap="nowrap">${cur.channel_b_name}</td>
          <td align="left" nowrap="nowrap">${cur.map.store_r3_sn}</td>
          <td align="left" nowrap="nowrap">${cur.map.c_comm}</td>
          
          <td align="left" nowrap="nowrap">${cur.map.c_name}</td>
          <td align="left" nowrap="nowrap">${cur.report_name}</td>
          <td align="left" nowrap="nowrap">${cur.dept_name}</td>
          <td align="left" nowrap="nowrap">${cur.dept_id}</td>
          <td align="center" nowrap="nowrap">${cur.measure_name}</td>
          <td align="left" nowrap="nowrap">${cur.model_name}</td>
          <td align="left" nowrap="nowrap">${cur.code}</td>
          <td align="right" nowrap="nowrap">${cur.num}</td>
          <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.price}" type="currency" />
            </span></td>
          <td align="right" nowrap="nowrap"><fmt:formatDate value="${cur.up_date}"/></td>
          <td align="right" nowrap="nowrap"><fmt:formatDate value="${cur.down_date}"/></td>
          <c:set var="downDate">
            <fmt:formatDate value="${cur.down_date}" pattern="yyyy-MM-dd" />
          </c:set>
          <td align="right" nowrap="nowrap"> 
          <c:if test="${not empty downDate}">
	          <c:if test="${now lt downDate}"><span style="color:#009900;">上架中</span></c:if>
	          <c:if test="${now ge downDate}"><span style="color:#CD0000;">已下架</span></c:if>
          </c:if>
          <c:if test="${empty downDate}"> 
          	 <span style="color:#009900;">上架中</span>
          </c:if>
          </td>
          <td align="right" nowrap="nowrap">
          ${cur.map.all_num}
          </td>
          <td align="right" nowrap="nowrap">
          <span class="kz-price-12"><fmt:formatNumber value="${cur.map.all_price_1}" type="currency" /> </span>
          </td>
          <td align="center" nowrap="nowrap">
          <c:if test="${not empty cur.map.zm}">
          <a href="${ctx}/${cur.map.zm}" target="_blank">&nbsp;正面&nbsp;</a>
          </c:if>
          </td>
          <td align="center" nowrap="nowrap">
          <c:if test="${not empty cur.map.bm}">
          <a href="${ctx}/${cur.map.bm}" target="_blank">&nbsp;背面&nbsp;</a>
          </c:if>
          </td> 
          <td align="center" nowrap="nowrap">
           <c:if test="${not empty cur.map.cm}">
          <a href="${ctx}/${cur.map.cm}" target="_blank">&nbsp;侧面&nbsp;</a>
          </c:if>
          </td>     
          <td align="left" nowrap="nowrap">
           <c:set var="ss" value="${cur.memo}" />
           <c:choose>
		     <c:when test="${fn:length(ss) > 15}">
		        ${fnx:abbreviate(ss,15,'...')}
		     </c:when>
		     <c:otherwise>
		        <c:out value="${ss}" />
		     </c:otherwise>
		   </c:choose>
          </td>
<%--           <td align="center" nowrap="nowrap">${fn:split('手机端,WEB端,IOS手机端,外部导入',',')[cur.data_source]}</td> --%>
        </tr>
      </c:forEach>
      </table>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/scripts/print.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	window.onload=function(){
		toExcel('divExcel', '?method=toExcel');
	};
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
