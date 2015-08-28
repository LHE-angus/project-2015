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
    <html-el:form action="/admin/KonkaSellReportDetail">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="add_date_start" value="${af.map.add_date_start}"/>
      <html-el:hidden property="add_date_end" value="${af.map.add_date_end}"/>
      <html-el:hidden property="r3_shop_id" />
      <table width="100%" border="0" cellspacing="1" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td><strong class="fb">R3编码　：</strong>
            <html-el:text property="r3_code_like" size="15" maxlength="20" styleId="r3_code_like"  /></td>
          <td><strong class="fb">客户名称：</strong>
            <html-el:text property="customer_name_like" size="15" maxlength="20" styleId="customer_name_like"  /></td>
          <td><strong class="fb">业务员　：</strong>
            <html-el:text property="ywy_name_like" size="15" maxlength="20" styleId="ywy_name_like"  /></td>   
        </tr>
        <tr>
          <td width="15"></td>
          <td><strong class="fb">销售状态：</strong>
            <html-el:select property="state">
              <html-el:option value="">请选择报表状态</html-el:option>
              <html-el:option value="0">未上报</html-el:option>
              <html-el:option value="1">已上报</html-el:option>
            </html-el:select></td>
          <td><strong class="fb">销售时间：</strong>
			      <html-el:text property="sell_date_start"  size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
			              至
			      <html-el:text property="sell_date_end"  size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
          <td><html-el:submit styleClass="but1" value="搜索" /> </td>
        </tr>
      </table>
    </html-el:form>
  </div>
    <div class="rtabcont1">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
        <td width="30" nowrap="nowrap">序号</td>
        <td nowrap="nowrap">标题</td>
        <td width="20%" nowrap="nowrap">上报部门</td>
        <td width="10%" nowrap="nowrap">上报时间</td>
        <td width="10%" nowrap="nowrap">上报人</td>
        <td width="5%" nowrap="nowrap">状态</td>
        <td width="10%" align="center">操作</td>
      </tr>
      <c:forEach items="${konkaSellList}" var="cur" varStatus="vs">
        <tr>
          <td align="center">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
          <td align="left" nowrap="nowrap" title="${cur.title}">${fnx:abbreviate(cur.title, 2 * 20, '')}</td>
          <td align="left" nowrap="nowrap" title="${cur.add_dept_name}">${fnx:abbreviate(cur.add_dept_name, 2 * 18, '')}</td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy/MM/dd HH:mm" /></td>
          <td align="left" nowrap="nowrap"><c:out value="${cur.add_real_name}" /></td>
          <td align="center" nowrap="nowrap">
          	<c:choose>
          		<c:when test="${cur.state eq 0}">已暂存</c:when>
          		<c:when test="${cur.state eq 1}">已上报</c:when>
          		<c:when test="${cur.state eq 2}">已锁定</c:when>
          		<c:otherwise>状态错误</c:otherwise>
          	</c:choose>
          </td>
          <td align="center" nowrap="nowrap">
          	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaSell.do', 'view','s_id=${cur.s_id}&r3_code=F1181XPDH&'+$('#bottomPageForm').serialize())">查看</span>
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
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </c:forEach>
    </table>
  </div>
  <form id="bottomPageForm" name="bottomPageForm" method="post" action="${ctx}/manager/admin/KonkaSellReportDetail.do">
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
          <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("r3_code_like", "${fn:escapeXml(af.map.r3_code_like)}");	
            pager.addHiddenInputs("cus_sn_like", "${af.map.cus_sn_like}");
            pager.addHiddenInputs("sell_date_start", "${af.map.sell_date_start}");
            pager.addHiddenInputs("sell_date_end", "${af.map.sell_date_end}");
            pager.addHiddenInputs("state", "${af.map.state}");
            pager.addHiddenInputs("handle_name_like", "${fn:escapeXml(af.map.handle_name_like)}");
            pager.addHiddenInputs("customer_name_like", "${fn:escapeXml(af.map.customer_name_like)}");
            pager.addHiddenInputs("ywy_name_like", "${fn:escapeXml(af.map.ywy_name_like)}");
            document.write(pager.toString());
            </script></td>
      </tr>
    </table>
  </form>
  </div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
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


