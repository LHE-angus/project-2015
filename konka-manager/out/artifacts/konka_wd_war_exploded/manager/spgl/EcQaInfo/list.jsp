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
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.webinput {
	background:#f5f4f4;
	padding-left: 5px;
	height: 19px;
	line-height: 19px;
	/*font-family: Arial, Helvetica, sans-serif;*/
	border: #ccc solid 1px;
}
ul.ckUl {
	list-style-type:none;
	display:inline;
}
ul.ckUl li {
	float:left;
	margin:auto 5px auto 0px;/*padding:2px 5px;*/
}
input, textarea, select {
	font-family:Microsoft Yahei;
	font-size:12px;
}
.ck-li {
	position:relative;
}
.ck-li .hidden-accessible {
	position:absolute !important;
	clip:rect(1px 1px 1px 1px);
}
.ck-li .ck-default {
	font-family:Microsoft yahei, verdana, Geneva, Tahoma, Georgia;
	font-size:12px;
	display:inline-block;
	padding:1px 8px;
	height:16px;
	line-height:16px;
	border:1px solid #CCC;
	background: #F6F6F6;
	font-weight: bold;
	color:#C4C4C4;
	cursor:pointer;
}
.ck-li .ck-hover {
	font-family:Microsoft yahei, verdana, Geneva, Tahoma, Georgia;
	font-size:12px;
	display:inline-block;
	padding:1px 8px;
	height:16px;
	line-height:16px;
	border:1px solid #FBCB09;
	background:#FDF5CE;
	font-weight: bold;
	color:#C77405;
	cursor:pointer;
}
.ck-li .ck-visited {
	font-family:Microsoft yahei, verdana, Geneva, Tahoma, Georgia;
	font-size:12px;
	display:inline-block;
	padding:1px 8px;
	height:16px;
	line-height:16px;
	border:2px solid #EF0F28/*#FF4800/*FBD850*/;
	background:white url("${ctx}/styles/customer/images/ck-visited.gif") right bottom no-repeat;
	font-weight:bold;
	color:#EF0F28/*#FF4800/*#EB8F00*/;
	cursor:pointer;
}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1" style="overflow: auto;">
    <html-el:form action="/spgl/EcQaInfo">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td height="36" align="left" style="padding-left:5px;">&nbsp;评论标题：
            <html-el:text property="q_title_like" styleId="q_title_like" styleClass="webinput" maxlength="30"  size="40"/>
            &nbsp;问题类型：
            <html-el:select  property="qa_type_code" styleId="qa_type_code">
              <html-el:option value="">请选择...</html-el:option>
              <html-el:option value="0">咨询</html-el:option>
              <html-el:option value="1">投诉</html-el:option>
              <html-el:option value="2">建议</html-el:option>
            </html-el:select>
            &nbsp;状态：
            <html-el:select  property="info_state" styleId="info_state">
              <html-el:option value="">请选择...</html-el:option>
              <html-el:option value="-1">已删除</html-el:option>
              <html-el:option value="0">未回复</html-el:option>
              <html-el:option value="1">已回复</html-el:option>
            </html-el:select>
            <input name="button" type="submit" class="bgSearch" id="button" value="搜 索" style="font-family:Microsoft YAHEI;" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center">序号</td>
        <td width="10%" nowrap="nowrap"  align="center">问题标题</td>
        <td width="5%" nowrap="nowrap" align="center">提问时间</td>
        <td width="7%" nowrap="nowrap" align="center">问题类型</td>
        <td width="7%" nowrap="nowrap" align="center">状态</td>
        <td width="7%" nowrap="nowrap" align="center">操作</td>
      </tr>
      <c:forEach items="${entityList}" var="cur" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap">${vs.count}</td>
          <td align="left"  nowrap="nowrap"title="${cur.q_title}">${fn:substring(cur.q_title,0,40)}</td> 
          <td  align="center" nowrap="nowrap"><fmt:formatDate value="${cur.q_date}"  pattern="yyyy-MM-dd"></fmt:formatDate></td>
          <td align="center"nowrap="nowrap"><c:choose>
              <c:when test="${cur.qa_type_code eq 0}">咨询</c:when>
              <c:when test="${cur.qa_type_code eq 1}">投诉</c:when>
              <c:when test="${cur.qa_type_code eq 2}">建议</c:when>
            </c:choose></td>
          <td align="center"nowrap="nowrap"><c:choose>
              <c:when test="${cur.info_state eq -1}">已删除</c:when>
              <c:when test="${cur.info_state eq 0}">未回复</c:when>
              <c:when test="${cur.info_state eq 1}">已回复</c:when>
            </c:choose></td>
          <td align="center" width="10%" nowrap="nowrap"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'EcQaInfo.do','edit' ,'qa_id=${cur.qa_id}&' + $('#bottomPageForm').serialize())">回复</span></td>
        </tr>
      </c:forEach>
    </table>
    <c:if test="${not vs.last}">
      <div style="height:40px;"></div>
    </c:if>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="EcQaInfo.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;"> 
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
              <script type="text/javascript">
								var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
								pager.addHiddenInputs("method", "list");
								pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
								pager.addHiddenInputs("qa_type_code", "${af.map.qa_type_code}");
								pager.addHiddenInputs("info_state", "${af.map.info_state}");
								pager.addHiddenInputs("q_title_like", "${af.map.q_title_like}");
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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/imgpreview.0.22.js"></script> 
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	
});

//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
