<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>信息接收 &gt; ${navString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
</style>
</head>
<body>
  <div class="oarcont">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="20"><img src="${ctx}/images/manager/arrow3.gif" style="vertical-align:middle;" /></td>
        <td> 当前位置：信息接收 &gt; ${navString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/JxcReceiveInfo">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="keySeq" />
      <html-el:hidden property="infoType" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableTop">
        <tr>
          <td width="20%" nowrap="nowrap">&nbsp;<strong class="fb">产品标题：</strong>
            <html-el:text property="title" size="20" style="width:90px;" maxlength="10" styleId="title" styleClass="webinput" />
            &nbsp;<strong class="fb">发布时间：</strong>
            <html-el:text property="st_pub_date" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
            至
            <html-el:text property="en_pub_date" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
            &nbsp;
            <input type="submit" name="" value="搜 索" id="btn_submit" class="bgSearch" />
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <form id="listForm" name="listForm" method="post" action="JxcReceiveInfo.do?method=delete">
    <input type="hidden" name="method" id="method" value="delete" />
    <input type="hidden" name="mod_id" id="mod_id" />
    <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="1" cellpadding="0" class="tableClass">
        <tr>
            <th width="5%" align="center" >序号</th>
            <th nowrap="nowrap">资讯标题</th>
            <th width="15%" nowrap="nowrap">资讯来源</th>
            <th width="10%">添加人</th>
            <th width="10%" nowrap="nowrap">排序值</th>
            <th width="10%" nowrap="nowrap">操作</th>
          </tr>
          <c:forEach var="cur" items="${konkaPeArticleInfoList}" varStatus="vs">
            <tr>
              <td align="center" nowrap="nowrap">${vs.count}</td>
              <td align="left">${fn:escapeXml(cur.title)}</td>
              <td align="center" nowrap="nowrap">${fn:escapeXml(cur.source)}</td>
              <td align="center" nowrap="nowrap">${fn:escapeXml(cur.add_user_name)}</td>
              <td align="center" nowrap="nowrap">${cur.order_value}</td>
              <td align="center" nowrap="nowrap"><span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'JxcReceiveInfo.do', 'view','id=${cur.id}&infoType=${af.map.infoType}&'+$('#bottomPageForm').serialize())">查看</span></td>
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
            </tr>
          </c:forEach>
      </table>
    </div>
  </form>
  <form id="bottomPageForm" name="bottomPageForm" method="post" action="JxcReceiveInfo.do">
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="tableClass">
      <tr>
        <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
          <script type="text/javascript">
			            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
			            pager.addHiddenInputs("method", "list");
			            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			            pager.addHiddenInputs("keySeq", "${af.map.keySeq}");
			            pager.addHiddenInputs("infoType", "${af.map.infoType}");
			            pager.addHiddenInputs("title", "${fn:escapeXml(af.map.title)}");
			            pager.addHiddenInputs("st_pub_date", "${af.map.st_pub_date}");
			            pager.addHiddenInputs("en_pub_date", "${af.map.en_pub_date}");
			            document.write(pager.toString());
			   </script></td>
      </tr>
    </table>
  </form>
</div>
<div class="rtabcont3"></div>
<div class="clear"></div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
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
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
