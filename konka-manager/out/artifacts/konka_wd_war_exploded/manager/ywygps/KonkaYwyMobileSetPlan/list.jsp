<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
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
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
      <html-el:form action="/ywygps/KonkaYwyMobileSetPlan">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td>&nbsp;<strong class="fb">月度方案名：</strong>
            <html-el:text property="setplan_name_like" size="20" maxlength="20" styleId="setplan_name_like"  />
            &nbsp;&nbsp;&nbsp;<html-el:submit styleClass="but1" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>
         <logic-el:match name="popedom" value="+1+">
           <input style="align:left" name="button" type="button"  value="新增" class="but2" onclick="location.href='KonkaYwyMobileSetPlan.do?method=add&mod_id=${af.map.mod_id}&s_id=${af.map.fs_id}';" />
         </logic-el:match>
        </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <form id="listForm" name="listForm" method="post" action="KonkaYwyMobileSetPlan.do?method=delete">
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_code" id="mod_code" value="${af.map.mod_code}" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td width="30" align="center" style="height:20px;">序号</td>
          <td nowrap="nowrap">公司名称</td>
          <td align="center" nowrap="nowrap" width="140">月度方案名</td>
          <td align="center" nowrap="nowrap" width="100">方案确认码</td>
          <td align="center" nowrap="nowrap" width="100">年份</td>
          <td align="center" nowrap="nowrap" width="80">月份</td>
          <td align="center" nowrap="nowrap" width="140">下月设置方案</td>
          <td nowrap="nowrap" align="center" width="120">操作</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
            <td align="left" nowrap="nowrap">
               <c:if test="${not empty af.map.fs_id}">${af.map.entp_name}</c:if>
               <c:if test="${empty af.map.fs_id}">${cur.map.entp_name}</c:if>
            </td>
            <td align="left" >${cur.setplan_name}</td>
            <td align="center" >${cur.setplan_crc}</td>
            <td align="center" >${cur.year}年</td>
            <td align="center" >${cur.month}月</td>
            <td align="center" >
               <c:if test="${empty cur.next_id}">未设置</c:if>
               <c:if test="${not empty cur.next_id}">${cur.map.next_setplan_name}</c:if>
            </td> 
            <td align="center"> 
             <logic-el:match name="popedom" value="+2+">             
             <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaYwyMobileSetPlan.do','edit' ,'id=${cur.id}&s_id=${cur.s_id}&' + $('#bottomPageForm').serialize())">修改</span>
             </logic-el:match>
              <logic-el:notMatch name="popedom" value="+2+">
                <span style="cursor:pointer;color:#CCCCCC;" class="fblue">修改</span>
             </logic-el:notMatch>
             |
             <logic-el:match name="popedom" value="+2+">
             <c:if test="${empty cur.next_id}">  
                <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaYwyMobileSetPlan.do','nextMonth' ,'p_id=${cur.id}&s_id=${cur.s_id}&' + $('#bottomPageForm').serialize())">下月设置方案</span>           
             </c:if>
             <c:if test="${not empty cur.next_id}">
                <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaYwyMobileSetPlan.do','view' ,'p_id=${cur.id}&s_id=${cur.s_id}&id=${cur.next_id}&' + $('#bottomPageForm').serialize())">下月设置方案</span>           
             </c:if>
             </logic-el:match>
              <logic-el:notMatch name="popedom" value="+2+">
                 <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaYwyMobileSetPlan.do','view' ,'p_id=${cur.id}&s_id=${cur.s_id}&id=${cur.next_id}&' + $('#bottomPageForm').serialize())">下月设置方案</span>
             </logic-el:notMatch>
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
            <td>&nbsp;</td>
          </tr>
        </c:forEach>
      </table>
    </form>
    <br />
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaYwyMobileSetPlan.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("setplan_name_like", "${af.map.setplan_name_like}");
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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
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