<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
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
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/SellDataStatistics">
      <html-el:hidden property="method" value="statistics" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="add_date_start" value="${af.map.add_date_start}"/>
      <html-el:hidden property="add_date_end" value="${af.map.add_date_end}"/>
      <html-el:hidden property="r3_shop_id" value="${af.map.r3_shop_id}"/>
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
         <td width="15"></td>
          <td width="350"><strong class="fb">销售时间：</strong> <html-el:text property="sell_date_start"  size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
                 	 至
      					<html-el:text property="sell_date_end"  size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
      	  </td>
      	<td>
      		<input class="but1" type="submit" name="Submit" id="btn_submit" value="搜索" />
      	</td>
      </tr>
      </table>
     </html-el:form>
  </div>
  <div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
        <td width="30" nowrap="nowrap" align="center">序号</td>
        <td nowrap="nowrap">标题</td>
        <td width="40" nowrap="nowrap" align="center">时间</td>
        <td width="60" nowrap="nowrap">业务员</td>
        <td width="120" nowrap="nowrap" align="center">操作</td>
      </tr>
      <c:forEach items="${konkaSellList}" var="cur" varStatus="vs">
        <tr>
          <td align="center">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
          <td align="left" title="${cur.title}">${fnx:abbreviate(cur.title, 2 * 20, '')}</td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="HH:mm" /></td>
          <td align="left" nowrap="nowrap"><c:out value="${cur.add_real_name}" /></td>
          <td align="center" nowrap="nowrap">
          	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaSell.do', 'view','s_id=${cur.s_id}&'+$('#bottomPageForm').serialize())">查看</span>
          	<a class="fblue" href="SellDataStatistics.do?method=download&s_id=${cur.s_id}" target="_blank">下载</a> 
          	<a class="fblue" href="SellDataStatistics.do?method=downloadAll&ids=${ids}&sell_date_start=${af.map.sell_date_start}&sell_date_end=${af.map.sell_date_end}" target="_blank">下载全部</a> 
          </td>
        </tr>
        <c:if test="${vs.last eq true}">
          <c:set var="i" value="${vs.count}" />
        </c:if>
      </c:forEach>
      <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
        <tr align="center">
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </c:forEach>
    </table>
  </div>
  <form id="bottomPageForm" name="bottomPageForm" method="post" action="${ctx}/manager/admin/SellDataStatistics.do">
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
          <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "statistics");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("r3_shop_id", "${af.map.r3_shop_id}");
            pager.addHiddenInputs("cus_sn_like", "${af.map.cus_sn_like}");
            pager.addHiddenInputs("sell_date_start", "${af.map.sell_date_start}");
            pager.addHiddenInputs("sell_date_end", "${af.map.sell_date_end}");
            document.write(pager.toString());
            </script></td>
      </tr>
    </table>
  </form>
 </div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[    
$(document).ready(function(){
	
	var f = document.getElementById('af');
	
	$("#btn_submit").click(function(){
		if (f.sell_date_start.value != "" && f.sell_date_end.value != "") {
			if (f.sell_date_end.value < f.sell_date_start.value) {
				alert("盘存时间结束日期不得大于开始日期,请重新选择!");
				return false;
			}
		}
		f.submit();
	});	
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
