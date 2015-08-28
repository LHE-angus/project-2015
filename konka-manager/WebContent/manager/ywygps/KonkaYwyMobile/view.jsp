<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont">
<div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
    <div align="left">
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
	      <tr>
	        <th colspan="2">数据上报信息预览</th>
	      </tr>
         <tr>
            <td width="20%" class="title_item" nowrap="nowrap" align="right"><strong>客户名称：</strong></td>
            <td align="left" colspan="2">
	            <c:out value="${r3Shop.customer_name}"/>
                </td>
          </tr>
          <tr>
            <td width="20%" class="title_item" nowrap="nowrap" align="right"><strong>客户R3编码：</strong></td>
            <td align="left" colspan="2"><c:out value="${r3Shop.r3_code}" /></td>
          </tr>
          <tr>
            <td align="center" width="20%"><strong>型号名称</strong></td>
            <td width="20%"><strong>库存数量</strong></td>
            <td><strong>库存成本单价</strong></td>
          </tr>
			  <c:forEach items="${kpmList}" var="kpm">
			     <c:if test="${not empty kpm.map.md_name}">
					  <tr>
			            <td align="left">${kpm.map.md_name}</td>
			            <td align="left"><fmt:formatNumber pattern="#0.00" value="${kpm.current_cost}" /></td>
			            <td align="left">${kpm.stock_cost}</td>
			          </tr>
			     </c:if>
			  </c:forEach>
          <tr>
            <td colspan="2" align="center"><input type="button" value=" 返回 " onclick="history.back();" />
            	<input type="button" value=" 下载数据" id="btn_toExcel" /></td>
          </tr>
        </table>
    </div>
  </div>
    <div id="divExcel" style="display:none;" title="${af.map.cus_name}&nbsp;-&nbsp;<fmt:formatDate value="${af.map.stock_date}" pattern="yyyy-MM-dd" />盘存记录">
	    <table width="600" border="1" cellpadding="0" cellspacing="0" class="datagrid">
		    <tbody style="overflow:auto;">
	          <tr>
	            <td width="70" align="center">1</td>
	      	    <td colspan="2" align="left" width="530" height="40">
	      	    	<span>客户名称：${r3Shop.customer_name}</span><br />
					<span>客户R3编码：${r3Shop.r3_code}</span>
	      	    </td>
	      	  </tr>
	      	  <tr>
	            <td width="70">&nbsp;</td>
	      	    <td width="160" align="center"><strong>型号名称</strong></td>
            	<td width="370"><strong>库存数量</strong></td>
            	<td width="370"><strong>库存成本单价</strong></td>
	      	  </tr>
		      <c:forEach items="${kpmList}" var="kpm" varStatus="vt">
		          <c:if test="${not empty kpm.map.md_name}">
					  <tr>
			            <td align="center">${vt.count + 1}</td>
			            <td align="left">${kpm.map.md_name}</td>
			            <td align="left" style="mso-number-format:'\@';">${kpm.stock_count}</td>
			            <td align="left" style="mso-number-format:'\@';">${kpm.stock_cost}</td>
			          </tr>
			      </c:if>
			  </c:forEach>
		      </tbody>
	    </table>
    </div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#btn_toExcel").click(function (){
		toExcel('divExcel', '${ctx}/manager/admin/KonkaStock.do?method=toExcel');
	});
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
