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
      <html-el:form action="/ywygps/KonkaYwyMobileSet">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td><strong class="fb">公司名称：</strong>
            <html-el:text property="entp_name_like" size="15" maxlength="30" styleId="entp_name_like"  />
            &nbsp;&nbsp;&nbsp;<strong class="fb">公司确认码：</strong>
            <html-el:text property="entp_crc_like" size="20" maxlength="20" styleId="entp_crc_like"  />
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
         <input style="align:left" name="button" type="button"  value="新增" class="but2" onclick="location.href='KonkaYwyMobileSet.do?method=add&mod_id=${af.map.mod_id}';" />
        </logic-el:match>
        </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <form id="listForm" name="listForm" method="post" action="KonkaYwyMobileSet.do?method=delete">
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_code" id="mod_code" value="${af.map.mod_code}" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" nowrap="nowrap" align="center" style="height:20px;">序号</td>
          <td nowrap="nowrap">公司名称</td>
          <td align="center" nowrap="nowrap" width="15%">手机端更新URL</td>
          <td align="center" nowrap="nowrap" width="15%">手机配置URL</td>
          <td align="center" nowrap="nowrap" width="15%">GPS信息URL</td>
          <td align="center" nowrap="nowrap" width="10%">创建时间</td>
          <td align="center" nowrap="nowrap" width="10%">公司确认码</td>
          <td nowrap="nowrap" align="center" width="10%">操作</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
            <td align="left" >${cur.entp_name}</td>
            <td align="left" style="table-layout: fixed;WORD-BREAK: break-all; WORD-WRAP: break-word" >${cur.config_url}</td>
            <td align="left" style="table-layout: fixed;WORD-BREAK: break-all; WORD-WRAP: break-word" >${cur.gps_url}</td>
            <td align="left" style="table-layout: fixed;WORD-BREAK: break-all; WORD-WRAP: break-word" >${cur.soft_url}</td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.create_date }" pattern="yyyy-MM-dd"></fmt:formatDate></td> 
            <td align="center" >${cur.entp_crc}</td>
            <td align="center" nowrap="nowrap"> 
            <logic-el:match name="popedom" value="+2+">         
            <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaYwyMobileSet.do','edit' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">修改</span>
            </logic-el:match>
            <logic-el:notMatch name="popedom" value="+2+">
               <span style="cursor:pointer;color:#CCCCCC;" class="fblue">修改</span>
            </logic-el:notMatch>
            |
            <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaYwyMobileSetPlan.do','list' ,'fs_id=${cur.id}&' + $('#bottomPageForm').serialize())">月度设置方案</span>
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
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaYwyMobileSet.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("entp_name_like", "${af.map.entp_name_like}");
            pager.addHiddenInputs("entp_crc_like", "${af.map.entp_crc_like}");
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