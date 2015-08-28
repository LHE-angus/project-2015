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
    <html-el:form action="/admin/JoinInfoFollow">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table>
        <tr>
          <td width="80%" nowrap="nowrap">
            &nbsp;<strong class="fb">标题：</strong>
            <html-el:text property="title" size="20" style="width:90px;" maxlength="10" styleId="title" styleClass="webinput" />
            
            &nbsp;<strong class="fb">添加时间：</strong>
            <html-el:text property="st_add_date" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
            至
            <html-el:text property="en_add_date" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />

            &nbsp;&nbsp;
            <input type="button" name="" value="搜索" id="btn_submit" class="but1" />
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
  
  </div>
  <div class="rtabcont1">
    <form id="listForm" name="listForm" method="post" action="JoinInfoFollow.do?method=delete">
      <div style="text-align: left">
        <input type="hidden" name="method" id="method" value="delete" />
        <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      </div>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td width="27" align="center" nowrap="nowrap">序号</td>
          <td nowrap="nowrap" align="center" >标题</td>
          <td width="80" align="center" nowrap="nowrap">添加人</td>
          <td width="70" nowrap="nowrap" align="center" >添加时间</td>
          <td  width="150" nowrap="nowrap" align="center" >操作</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${vs.count }</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.title)}</td>
            <td align="center" nowrap="nowrap">${fn:escapeXml(cur.add_user_name)}</td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" /></td>
            <td align="center">
	              <span style="cursor:pointer;" class="fblue"  onclick="doNeedMethod(null, 'JoinInfoManager.do', 'view','id=${cur.id}&'+$('#bottomPageForm').serialize())">查看详细</span>
	              |
	              <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'JoinInfoFollow.do', 'visitList','article_id=${cur.id}&'+$('#bottomPageForm').serialize())">点击网点列表</span> 
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
    </form>
    <br />
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="JoinInfoFollow.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			pager.addHiddenInputs("title", "${fn:escapeXml(af.map.title)}");							
			pager.addHiddenInputs("st_add_date", "${fn:escapeXml(af.map.st_add_date)}");	
			pager.addHiddenInputs("en_add_date", "${fn:escapeXml(af.map.en_add_date)}");	
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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var f = document.forms[0];
	$("#btn_submit").click(function(){
		if (this.form.st_add_date.value != "" && this.form.en_add_date.value != "") {
			if (this.form.en_add_date.value < this.form.st_add_date.value) {
				alert("发布时间结束日期不得小于开始日期,请重新选择!");
				return false;
			}
		}
		this.form.submit();
	});
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
