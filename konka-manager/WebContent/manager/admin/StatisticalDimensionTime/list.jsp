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
    <html-el:form action="/admin/StatisticalDimensionTime">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}"/>
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td>
           <strong class="fb">年度：</strong>
           <html-el:select property="year" styleId="year">
           		<html-el:option value="">-请选择-</html-el:option>
           		<c:forEach begin="2008" end="2020" var="cur" varStatus="vs">
           			<html-el:option value="${cur}">${cur}</html-el:option>
           		</c:forEach>
           	</html-el:select>
           &nbsp;&nbsp;
           <strong class="fb">年度类别：</strong>
           	<html-el:select property="is_cw" styleId="is_cw">
           		<html-el:option value="">-请选择-</html-el:option>
           		<html-el:option value="0">自然年度</html-el:option>
           		<html-el:option value="1">财务年度</html-el:option>
           	</html-el:select>
           &nbsp;&nbsp;
           <input class="but1" type="submit" name="Submit" value="搜索" /></td>
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
        	<input type="button" class="but2" name="add" value="新增" onclick="location.href='StatisticalDimensionTime.do?method=add&mod_id=${af.map.mod_id}';" />
<%--           </logic-el:match>	 --%>
        </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td nowrap="nowrap" align="center" width="5%">序号</td>
        <td nowrap="nowrap" align="center" width="10%">年度</td>
        <td nowrap="nowrap" align="center" width="10%">开始时间</td>
        <td nowrap="nowrap" align="center" width="10%">结束时间</td>
        <td nowrap="nowrap" align="center" width="12%">添加时间</td>
        <td nowrap="nowrap" align="center" width="10%">年度类别</td>
        <td nowrap="nowrap" align="center" width="8%">操作</td>
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
            <td align="center">${fn:escapeXml(cur.year)}</td>
            <td align="center"><fmt:formatDate value="${cur.start_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
            <td align="center"><fmt:formatDate value="${cur.end_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
            <td align="center"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
            <td align="center"><c:choose>
            	<c:when test="${cur.is_cw eq 0}">自然年度</c:when>
            	<c:otherwise>财务年度</c:otherwise>
            </c:choose></td>
            <td align="center">
<%--             	<logic-el:match name="popedom" value="+2+"> --%>
<%-- 	            	<span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'StatisticalDimensionTime.do', 'view','id=${cur.id}&'+$('#bottomPageForm').serialize())">查看</span>| --%>
	            	<span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'StatisticalDimensionTime.do', 'edit','id=${cur.id}&'+$('#bottomPageForm').serialize())">修改</span>
	            	|<span style="cursor: pointer;" class="fblue" onclick="doNeedMethod('确定删除吗？', 'StatisticalDimensionTime.do', 'delete','id=${cur.id}&'+$('#bottomPageForm').serialize())">删除</span>
<%--               	</logic-el:match> --%>
            </td>
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
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="StatisticalDimensionTime.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("year", "${af.map.year}");
				pager.addHiddenInputs("is_cw", "${af.map.is_cw}");
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
