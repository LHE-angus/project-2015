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
        <table width="100%" border="0"  cellpadding="0" cellspacing="1" class="rtable2">
	      <tr>
	        <td colspan="2">数据上报信息预览</td>
	      </tr>
          <tr>
            <td width="15%" class="title_item" nowrap="nowrap" align="right"><strong>客户R3编码：</strong></td>
            <td>${af.map.cus_sn}</td>
          </tr>
          <tr>
            <td class="title_item" align="right"><strong>客户名称：</strong></td>
            <td>${af.map.cus_name}</td>
          </tr>
          <tr>
            <td class="title_item" align="right"><strong>销售日期：</strong></td>
            <td><fmt:formatDate value="${af.map.sell_date}" pattern="yyyy年MM月dd日 " /></td>
          </tr>
          <tr>
            <td class="title_item" align="right"><strong>信息填写时间：</strong></td>
            <td><fmt:formatDate value="${af.map.add_date}" pattern="yyyy年MM月dd日" /></td>
          </tr>
          <tr>
             <td class="title_item" align="right"></td>
             <td>
             <table width="60%" border="0"  cellpadding="0" cellspacing="1" class="rtable2">
		          <tr>
		            <td width="15%" align="center" ><strong>型号名称</strong></td>
		            <td><strong>销售情况</strong></td>
		          </tr>
				  <c:forEach items="${kpmList}" var="kpm">
				    <c:forEach items="${af.map.konkaSellDetailsList}" var="ksd">
				      <c:if test="${kpm.pd_id eq ksd.pd_id}">
				        <c:if test="${ksd.sell_count*ksd.sell_money ne 0}"> 
						  <tr>
				            <td align="left">${kpm.md_name}</td>
				            <td align="left">销售数量：<span style="color:#F00;">${ksd.sell_count}</span> 台， 销售单价：<span style="color:#F00;"><fmt:formatNumber pattern="#0.00" value="${ksd.sell_money}" /></span>元，销售总价：<span style="color:#F00;"><fmt:formatNumber pattern="#0.00" value="${ksd.sell_count*ksd.sell_money}" /></span>元。</td>
				          </tr>
				          </c:if>
				      </c:if>
			       </c:forEach>
				  </c:forEach>
				  <tr>
		            <td width="15%" align="center" ><strong>合计</strong></td>
		            <td align="left">销售数量：<span style="color:#F00;">${af.map.sell_count_totle}</span> 台，销售总价：<span style="color:#F00;"><fmt:formatNumber pattern="#0.00" value="${af.map.sell_cost_totle}" /></span>元。</td>
		          </tr>
		     </table>
		   </td>
		  </tr>
          <tr>
            <td colspan="2" align="center"><input type="button" value=" 返回 " onclick="history.back();" />
            	<!--<input type="button" value=" 下载数据" id="btn_toExcel" />--></td>
          </tr>
        </table>
    </div>

    <!-- <div id="divExcel" style="display:none;" title="${af.map.cus_name}&nbsp;-&nbsp;<fmt:formatDate value="${af.map.sell_date}" pattern="yyyy-MM-dd" />销售记录">
	    <table width="600" border="1" cellpadding="0" cellspacing="0" class="datagrid">
		    <tbody style="overflow:auto;">
	          <tr>
	            <td width="70" align="center">1</td>
	      	    <td colspan="3" align="left" width="530" height="40">
	      	    	<span>客户R3编码：${af.map.cus_sn}</span><br />
					<span>客户名称：${af.map.cus_name}</span>
	      	    </td>
	      	  </tr>
	      	  <tr>
	            <td>&nbsp;</td>
	      	    <td colspan="3" align="left" style="padding-left:20px;" height="28">销售日期：<fmt:formatDate value="${af.map.sell_date}" pattern="yyyy年MM月dd日" />&nbsp;&nbsp;&nbsp;&nbsp;信息填写时间：<fmt:formatDate value="${af.map.add_date}" pattern="yyyy年MM月dd日" /></td>
	      	  </tr>
	      	  <tr>
	            <td width="70">&nbsp;</td>
	      	    <td width="160" align="center"><strong>型号名称</strong></td>
            	<td width="370"><strong>销售数量</strong></td>
            	<td width="370"><strong>销售单价</strong></td>
	      	  </tr>
		      <c:forEach items="${kpmList}" var="kpm" varStatus="vt">
			    <c:forEach items="${af.map.konkaSellDetailsList}" var="ksd" varStatus="vs">
			      <c:if test="${kpm.pd_id eq ksd.pd_id}">
					  <tr>
			            <td align="center">${vt.count + 1}</td>
			            <td align="left">${kpm.md_name}</td>
			            <td align="left" style="mso-number-format:'\@';">${ksd.sell_count}</td>
			            <td align="left" style="mso-number-format:'\@';">${ksd.sell_money}</td>
			          </tr>
			      </c:if>
		       </c:forEach>
			  </c:forEach>
		      </tbody>
	    </table>
    </div> -->
   </div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#btn_toExcel").click(function (){
		toExcel('divExcel', '${ctx}/manager/admin/KonkaSell.do?method=toExcel');
	});
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
