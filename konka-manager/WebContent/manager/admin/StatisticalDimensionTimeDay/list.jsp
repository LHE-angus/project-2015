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
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align: middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/StatisticalDimensionTimeDay">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}"/>
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td>
           <strong class="fb">日：</strong>
           <input name="day_start" id="day_start" size="12" value="${af.map.day_start}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'2008-01-01',maxDate:'#F{$dp.$D(\'day_end\')}'})" />
           -
           <input name="day_end" id="day_end" size="12"  value="${af.map.day_end}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'#F{$dp.$D(\'day_start\')||\'2008-01-01\'}'})" />
           &nbsp;&nbsp;
           <strong class="fb">周：</strong>
           	<html-el:select property="week" styleId="week">
           		<html-el:option value="">-请选择-</html-el:option>
           		<c:forEach begin="1" end="53" var="cur" varStatus="vs">
           			<html-el:option value="${cur}">${cur}</html-el:option>
           		</c:forEach>
           	</html-el:select>
           &nbsp;&nbsp;
           <strong class="fb">月：</strong>
           	<html-el:select property="month" styleId="month">
           		<html-el:option value="">-请选择-</html-el:option>
           		<c:forEach begin="1" end="12" var="cur" varStatus="vs">
           			<html-el:option value="${cur}">${cur}</html-el:option>
           		</c:forEach>
           	</html-el:select>
           &nbsp;&nbsp;
           <strong class="fb">季度：</strong>
           	<html-el:select property="quarter" styleId="quarter">
           		<html-el:option value="">-请选择-</html-el:option>
           		<c:forEach begin="1" end="4" var="cur" varStatus="vs">
           			<html-el:option value="${cur}">${cur}</html-el:option>
           		</c:forEach>
           	</html-el:select>
           </td>
        </tr>
        <tr>
          <td width="15"></td>
          <td>
           <strong class="fb">年：</strong>
           	<html-el:select property="year" styleId="year">
           		<html-el:option value="">-请选择-</html-el:option>
           		<c:forEach begin="2012" end="2020" var="cur" varStatus="vs">
           			<html-el:option value="${cur}">${cur}</html-el:option>
           		</c:forEach>
           	</html-el:select>
           &nbsp;&nbsp;
           <strong class="fb">财务月：</strong>
           	<html-el:select property="month_cw" styleId="month_cw">
           		<html-el:option value="">-请选择-</html-el:option>
           		<c:forEach begin="1" end="12" var="cur" varStatus="vs">
           			<html-el:option value="${cur}">${cur}</html-el:option>
           		</c:forEach>
           	</html-el:select>
           &nbsp;&nbsp;
           <strong class="fb">财务季度：</strong>
           	<html-el:select property="quarter_cw" styleId="quarter_cw">
           		<html-el:option value="">-请选择-</html-el:option>
           		<c:forEach begin="1" end="4" var="cur" varStatus="vs">
           			<html-el:option value="${cur}">${cur}</html-el:option>
           		</c:forEach>
           	</html-el:select>
           &nbsp;&nbsp;
           <strong class="fb">财务年：</strong>
           	<html-el:select property="year_cw" styleId="year_cw">
           		<html-el:option value="">-请选择-</html-el:option>
           		<c:forEach begin="2012" end="2020" var="cur" varStatus="vs">
           			<html-el:option value="${cur}">${cur}</html-el:option>
           		</c:forEach>
           	</html-el:select>
           &nbsp;&nbsp;
           <input class="but1" type="submit" name="Submit" value="搜索" />
           </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp"%>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>
<%--           <logic-el:match name="popedom" value="+1+"> --%>
        	<input type="button" class="but2" name="add" value="类别维护" onclick="location.href='StatisticalDimensionTimeDay.do?method=add&mod_id=${af.map.mod_id}';" />
<%--           </logic-el:match>	 --%>
        </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td nowrap="nowrap" align="center" width="4%">序号</td>
        <td nowrap="nowrap" align="center" width="6%">日</td>
        <td nowrap="nowrap" align="center" width="4%">周</td>
        <td nowrap="nowrap" align="center" width="4%">月</td>
        <td nowrap="nowrap" align="center" width="4%">季度</td>
        <td nowrap="nowrap" align="center" width="4%">年</td>
        <td nowrap="nowrap" align="center" width="4%">财务月</td>
        <td nowrap="nowrap" align="center" width="4%">财务季</td>
        <td nowrap="nowrap" align="center" width="4%">财务年</td>
        <td nowrap="nowrap" align="center" width="4%">5.1</td>
        <td nowrap="nowrap" align="center" width="4%">5.21</td>
        <td nowrap="nowrap" align="center" width="4%">10.1</td>
        <td nowrap="nowrap" align="center" width="4%">春节</td>
        <td nowrap="nowrap" align="center" width="10%">添加时间</td>
<!--         <td nowrap="nowrap" align="center" width="8%">操作</td> -->
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
            <td align="center"><fmt:formatDate value="${cur.day}" pattern="yyyy-MM-dd" /></td>
            <td align="center">${fn:escapeXml(cur.week)}</td>
            <td align="center">${fn:escapeXml(cur.month)}</td>
            <td align="center">${fn:escapeXml(cur.quarter)}</td>
            <td align="center">${fn:escapeXml(cur.year)}</td>
            <td align="center">${fn:escapeXml(cur.month_cw)}</td>
            <td align="center">${fn:escapeXml(cur.quarter_cw)}</td>
            <td align="center">${fn:escapeXml(cur.year_cw)}</td>
            <td align="center"><c:if test="${cur.five_p_one eq 1 }"><font color="green">是</font></c:if><c:if test="${cur.five_p_one eq 0 }"><font color="grey">否</font></c:if></td>
            <td align="center"><c:if test="${cur.five_p_twenty_one eq 1 }"><font color="green">是</font></c:if><c:if test="${cur.five_p_twenty_one eq 0 }"><font color="grey">否</font></c:if></td>
            <td align="center"><c:if test="${cur.ten_p_one eq 1 }"><font color="green">是</font></c:if><c:if test="${cur.ten_p_one eq 0 }"><font color="grey">否</font></c:if></td>
            <td align="center"><c:if test="${cur.spring_festival eq 1 }"><font color="green">是</font></c:if><c:if test="${cur.spring_festival eq 0 }"><font color="grey">否</font></c:if></td>
            <td align="center"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
<!--             <td align="center"> -->
<%--             	<logic-el:match name="popedom" value="+2+"> --%>
<%-- 	            	<span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'StatisticalDimensionTimeDay.do', 'view','id=${cur.id}&'+$('#bottomPageForm').serialize())">查看</span>| --%>
<%-- 	            	<span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'StatisticalDimensionTimeDay.do', 'edit','id=${cur.id}&'+$('#bottomPageForm').serialize())">修改</span> --%>
<%-- 	            	|<span style="cursor: pointer;" class="fblue" onclick="doNeedMethod('确定删除吗？', 'StatisticalDimensionTimeDay.do', 'delete','id=${cur.id}&'+$('#bottomPageForm').serialize())">删除</span> --%>
<%--               	</logic-el:match> --%>
<!--             </td> -->
          </tr>
        </c:forEach>
        <c:forEach begin="${fn:length(entityList)}" end="${af.map.pager.pageSize - 1}" step="1">
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
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
<!--             <td>&nbsp;</td> -->
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="StatisticalDimensionTimeDay.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("day_start", "${af.map.day_start}");
				pager.addHiddenInputs("day_end", "${af.map.day_end}");
				pager.addHiddenInputs("week", "${af.map.week}");
				pager.addHiddenInputs("month", "${af.map.month}");
				pager.addHiddenInputs("quarter", "${af.map.quarter}");
				pager.addHiddenInputs("year", "${af.map.year}");
				pager.addHiddenInputs("month_cw", "${af.map.month_cw}");
				pager.addHiddenInputs("quarter_cw", "${af.map.quarter_cw}");
				pager.addHiddenInputs("year_cw", "${af.map.year_cw}");
				document.write(pager.toString());
			  </script>
            </div></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/scripts/print.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}
});
//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
