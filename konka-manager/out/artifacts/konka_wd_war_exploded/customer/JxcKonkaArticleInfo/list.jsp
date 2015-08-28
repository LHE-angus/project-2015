<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<style type="text/css">
select {
	font-family:Microsoft YAHEI;
	font-size:12px;
}
input {
	font-family:Microsoft YAHEI;
	font-size:12px;
}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oartop">
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
      <td>当前位置：${naviString}</td>
    </tr>
  </table>
</div>
<html-el:form action="/manager/JxcKonkaArticleInfo">
  <html-el:hidden property="method" value="list" />
  <html-el:hidden property="receive_user_type" />
  <html-el:hidden property="article_type_id" value="${af.map.article_type_id}" />
  <html-el:hidden property="public_target" />
  <html-el:hidden property="mod_id" styleId="mod_id" />
  <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
    <tr>
      <td width="20%" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">资讯标题：</strong>
        <html-el:text property="title_like" size="20" style="width:90px;" maxlength="10" styleId="title_like" styleClass="webinput" />
        &nbsp;<strong class="fb">发布时间：</strong>
        <html-el:text property="st_pub_date" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
        至
        <html-el:text property="en_pub_date" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
        &nbsp;&nbsp;
        <input type="button" name="" value="搜索" id="btn_submit" class="bgSearch" /></td>
    </tr>
  </table>
</html-el:form>
<div class="rtabcont1">
  <div class="rtabcont1">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
      <tr>
        <th width="5%" align="center" >序号</th>
        <th nowrap="nowrap">资讯标题</th>
        <th width="15%">发布时间</th>
        <th width="20%">发布人</th>
        <th width="5%" nowrap="nowrap" align="center" >操作</th>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap">${vs.count}</td>
          <td align="left">${fn:escapeXml(cur.title)}</td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.pub_date}" pattern="yyyy-MM-dd hh:mm:ss" /></td>
          <td align="left" nowrap="nowrap">${fn:escapeXml(cur.add_user_name)}</td>
          <td align="center" nowrap="nowrap"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'JxcKonkaArticleInfo.do', 'view','id=${cur.id}&'+$('#bottomPageForm').serialize())">查看</span></td>
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
    <br />
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="JxcKonkaArticleInfo.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
            	var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
			            pager.addHiddenInputs("method", "list");
			            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			            pager.addHiddenInputs("tree_param", "${tree_param}");
			            pager.addHiddenInputs("title_like", "${fn:escapeXml(af.map.title_like)}");
			            pager.addHiddenInputs("st_pub_date", "${af.map.st_pub_date}");
			            pager.addHiddenInputs("en_pub_date", "${af.map.en_pub_date}");
			            pager.addHiddenInputs("article_type_id", "${af.map.article_type_id}");
			            document.write(pager.toString());
			   </script></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var f = document.forms[0];
	$("#btn_submit").click(function(){
		if (this.form.st_pub_date.value != "" && this.form.en_pub_date.value != "") {
			if (this.form.en_pub_date.value < this.form.st_pub_date.value) {
				alert("发布时间结束日期不得小于开始日期,请重新选择!");
				return false;
			}
		}
		this.form.submit();
	});
});
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
