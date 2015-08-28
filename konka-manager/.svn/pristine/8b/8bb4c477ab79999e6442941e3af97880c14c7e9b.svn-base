<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<body>
<div>
  <divid="divExcel">
      <table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr class="tabtt1">
          <td>分公司</td>
          <td>凭证单号</td>
          <td>项目号</td>
          <td>产品型号</td>
          <td>STO订单数量(S)</td>
          <td>STO发货数(S)</td>
          <td>STO收货数(S)</td>
          <td>交货数量(S)</td>
          <td>发货仓位库存</td>
          <td>收货仓位库存</td>
          <td>发货仓位</td>
          <td>收货仓位</td>
          <td>客户编码</td>
          <td>请求号码</td>
          <td>交货单号</td>
          <td>交货单创建日期</td>
          <td>交货单项目号</td>
          <td>交货订单数量</td>
          <td>总部发货数量</td>
          <td>发货日期</td>
          <td>分公司已收数量</td>
          <td>收货日期</td>
        </tr>
        <c:forEach var="cur" items="${entityList1}" varStatus="vs">
          <tr>
			<td>${cur.map.dept_name}</td>
            <td>${cur.ebeln}</td>
	        <td>${cur.ebelp}</td> 
            <td>${cur.matnr}</td>
            <td>${cur.menge}</td>
            <td>${cur.wamng}</td>
            <td>${cur.wemng}</td>
            <td>${cur.lfimg}</td>
            <td>${cur.labst}</td>
            <td>${cur.labst1}</td>
            <td>${cur.reslo}</td>
            <td>${cur.lgort}</td>
            <td>${cur.kunnr}</td>
            <td>${cur.bednr}</td>
            <td>${cur.vbeln}</td>
            <td><fmt:formatDate value="${cur.erdat}" pattern="yyyy-MM-dd"/></td>
            <td>${cur.posnr}</td>
            <td>${cur.lfimg1}</td>
            <td>${cur.menge1}</td>
            <td><fmt:formatDate value="${cur.budat1}" pattern="yyyy-MM-dd"/></td>
            <td>${cur.menge2}</td>
            <td><fmt:formatDate value="${cur.budat2}" pattern="yyyy-MM-dd"/></td>
          </tr>
        </c:forEach>
      </table>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	window.onload=function(){
		//toExcel('divExcel', '?method=toExcel');
	};
});
//]]></script>
</body>
</html>
