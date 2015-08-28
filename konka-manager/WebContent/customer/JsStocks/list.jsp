<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	select{font-family:Microsoft YAHEI;font-size:12px;}
	input{font-family:Microsoft YAHEI;font-size:12px;}
</style>
<title>库存</title>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
</head>
<body style="font-family:Microsoft Yahei;">
	<div class="oartop">
	    <table width="400" border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
	        <td>当前位置：${naviString}</td>
	      </tr>
	    </table>
  	</div>
	<html-el:form action="/manager/JsStocks">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
	  <div class="rtabcont1">
	    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
	      <tr>
	        <td height="36" align="left" style="padding-left:5px;">
	          &nbsp;<strong class="fb">产品型号：</strong>
              <html-el:text property="md_name_like" styleClass="webinput" styleId="md_name_like" size="20"/>
	          &nbsp;<strong class="fb">订单时间：</strong>
			  <input type="text" name="add_date_ge" id="add_date_ge" class="webinput" value="${af.map.add_date_ge}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:center;width:80px;" />
            至
              <input type="text" name="add_date_le" id="add_date_le" class="webinput" value="${af.map.add_date_le}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:center;width:80px;"/>
	          &nbsp;
	          <input name="button" type="submit" class="bgSearch" id="button" value="搜 索" />
	        </td>
	      </tr>
	    </table>
	  </div>
	</html-el:form>
	<div class="rtabcont1">
	  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
	    <tr>
	      <th width="5%">序号</th>
	   
	      <th width="10%">仓库名称</th>
	      <th width="10%">产品型号</th>
	      <th width="10%">平均成本</th>
	      <th width="10%">库存金额</th>
	      <th width="10%">库存量</th>
	      <th width="10%">已销售量</th>
	      <th width="10%">总量</th>
	      <th width="10%">订单时间</th>
	    </tr>
	    <c:forEach items="${entityList}" var="cur" varStatus="vs">
	      <tr>
	        <td align="center" bgcolor="#fff2dc">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
	      
	        <td align="left" nowrap="nowrap">${cur.map.store_name}</td>
	        <td align="left" nowrap="nowrap">${cur.md_name}</td>
	        <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.cost}" pattern="0.00" /></td>
	        <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.total_cost}" pattern="0.00" /></td>
	        <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.stocks}" pattern="0" /></td>
	        <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.num-cur.stocks}" pattern="0" /></td>
	        <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.num}" pattern="0" /></td>
	        <td align="center"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	      </tr>
	      <c:if test="${vs.last}">
              <c:forEach begin="1" end="${9 - vs.index}">
                <tr>
                  <td>&nbsp;</td>
                 
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                </tr>
              </c:forEach>
           	</c:if>
	    </c:forEach>
	    <c:if test="${empty entityList}">
        	<c:forEach begin="0" end="9">
        	  <tr>
                <td>&nbsp;</td>
               
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
        	</c:forEach>
        </c:if>
	  </table>
	  <c:if test="${not empty entityList}">
	    <div class="rtabcont3">
	      <form id="bottomPageForm" name="bottomPageForm" method="post" action="JsStocks.do">
	        <table width="100%" border="0" align="center" cellspacing="0" cellpadding="0">
	          <tr>
	            <td height="40" align="center"><script type="text/javascript">
		            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		            pager.addHiddenInputs("method", "list");
		            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
		            pager.addHiddenInputs("add_date_ge", "${af.map.add_date_ge}");
		            pager.addHiddenInputs("add_date_le", "${af.map.add_date_le}");
		            pager.addHiddenInputs("md_name_like", "${fn:escapeXml(af.map.md_name_like)}");
		            document.write(pager.toString());
		            </script></td>
	          </tr>
	        </table>
	      </form>
	    </div>
	  </c:if>
	</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/customer/script/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

});	
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>