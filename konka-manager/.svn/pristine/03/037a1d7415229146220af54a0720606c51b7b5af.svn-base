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
<div class="oarcont" id="body_oarcont">
	<div class="oartop">
	    <table width="100%" border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
	        <td>当前位置：${naviString}</td>
	        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
	      </tr>
	    </table>
    </div>
    <div class="rtabcont1">
    	<html-el:form action="/admin/KonkaR3OrSellReport">
    	<html-el:hidden property="method" value="list_search" />
		<html-el:hidden property="mod_id" />
		<table width="100%" border="0" cellspacing="1" cellpadding="0" class="rtable1">
			<tr>
				<td width="15"></td>
				<td><strong class="fb">查询标题：</strong>
					<html-el:text property="filter_name" size="15" maxlength="40" styleId="filter_name"  />
					&nbsp;
					<strong class="fb">查询类别：</strong>
					<html-el:select property="filter_type">
						<html-el:option value="">全部</html-el:option>
						<html-el:option value="1">订单统计</html-el:option>
						<html-el:option value="2">综合查询分析</html-el:option>
					</html-el:select>
					<html-el:submit styleClass="but1" value="搜索" /></td>
			</tr>
		</table>
    	</html-el:form>
    </div>
    <div class="rtabcont1">
    	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
    		<tr class="tabtt1">
    			<td width="5%" nowrap="nowrap">序号</td>
    			<td nowrap="nowrap">查询标题</td>
    			<td width="10%" align="center">查询类别</td>
    			<td width="10%" align="center">查询时间</td>
    			<td width="10%" align="center">操作</td>
    		</tr>
    		<c:forEach items="${entityList}" var="cur" varStatus="vs">
    			<tr>
    				<td align="center">${vs.count}</td>
    				<td align="left">${fn:escapeXml(cur.filter_name) }</td>
    				<td align="center">${cur.filter_type eq 1 ? '订单统计':'综合查询分析'}</td>
    				<td align="center"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" /></td>
    				<td align="center"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaR3OrSellReport.do', 'view_search','id=${cur.id}&filter_type=${cur.filter_type}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">查询</span></td>
    			</tr>
    		</c:forEach>
    	</table>
	    <br />
	    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaR3OrSellReport.do">
	      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
	        <tr>
	          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
	            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list_search");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("filter_name", "${af.map.filter_name}");
	            pager.addHiddenInputs("filter_type", "${af.map.filter_type}");
	            document.write(pager.toString());
	            </script></td>
	        </tr>
	      </table>
	    </form>
    </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">//<![CDATA[    
$(document).ready(function(){
	$("#span_help").click(function(){
        $("#cvtooltipClose").cvtooltip({
            panel: "#body_oarcont",
            direction: 1,                
            width: 420,
            left: 320,
            top: 5,
            speed: 500,
            delay: 10000
        });
    });

});

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>