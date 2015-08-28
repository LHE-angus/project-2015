<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<title>R3销售无效数据</title>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
</head>
<body>
<div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：${naviString}</div>
<div class="rtabcont1">
  <html-el:form action="/KonkaJxcR3SellImpInvalidDate.do">
    <html-el:hidden property="method" value="list" />
    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
      <tr>
        <td width="6%" nowrap="nowrap"><strong class="fb">R3销售单日期：</strong>
          <html-el:text property="start_date" styleId="start_date" size="9" maxlength="9" readonly="true" styleClass="webinput" style="cursor:pointer;text-align:left;width:80px;" onclick="new Calendar(2011, 2031, 0).show(this);" />
          &nbsp;<span>至</span>&nbsp;
          <html-el:text property="end_date" styleId="end_date" size="9" maxlength="9" readonly="true" styleClass="webinput" style="cursor:pointer;text-align:left;width:80px;" onclick="new Calendar(2011, 2031, 0).show(this);" />
          &nbsp;&nbsp;
          <html-el:submit value="搜 索" styleClass="bgSearch"/></td>
      </tr>
    </table>
  </html-el:form>
</div>
<div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
</div>
<div class="rtabcont1">
  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
    <tr>
      <th width="5%">行号</th>
      <th width="12%">R3销售单日期</th>
      <th width="12%">名称（售）</th>
      <th width="10%">订单号</th>
      <th width="10%">数量</th>
      <th width="10%">单价（￥）</th>
      <th width="10%">总金额（￥）</th>
      <th width="10%">添加人</th>
      <th width="8%">操作</th>
    </tr>
    <c:forEach items="${konkaR3SellImpInvalidDataList}" var="cur" varStatus="status">
      <tr>
        <td align="center">${status.count}</td>
        <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.r3_sell_date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
        <td align="center">${cur.r3_name}</td>
        <td align="center">${cur.order_sn}</td>
        <td align="center"><fmt:formatNumber value="${cur.count}" pattern="0" /></td>
        <td align="center"><fmt:formatNumber value="${cur.price}" pattern="0.00" /></td>
        <td align="center"><fmt:formatNumber value="${cur.sum_money}" pattern="0.00" /></td>
        <td align="center">${cur.add_user_id}</td>
        <td align="center"><span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'KonkaJxcR3SellImpInvalidDate.do', 'view','id=${cur.id}&'+$('#bottomPageForm').serialize())">查看</span></td>
      </tr>
    </c:forEach>
  </table>
  <div class="rtabcont3">
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaJxcR3SellImpInvalidDate.do">
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript">
		       var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		       pager.addHiddenInputs("method", "list");
		       pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
		       pager.addHiddenInputs("start_date", "${fn:escapeXml(af.map.start_date)}");
	            pager.addHiddenInputs("end_date", "${fn:escapeXml(af.map.end_date)}");
		       document.write(pager.toString());
		       </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var f=document.forms[0];
	 $(".bgSearch").click(function(){
	    	var s_time = $("#start_date").val();
			var e_time = $("#end_date").val();
			if ("" != s_time && "" != e_time && s_time > e_time) {
				alert("开始日期不能大于结束日期！");
				return false;
			}
			if(!Validator.Validate(f, 1)){
				return false;
			}
	    });
	});
//]]></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>