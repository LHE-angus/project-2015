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
    <html-el:form action="/manager/KonkaGroupPeArticleInfo">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="20%" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">资讯标题：</strong>
            <html-el:text property="title" size="20" style="width:90px;" maxlength="10" styleId="title" styleClass="webinput" />
            &nbsp;<strong class="fb">发布时间：</strong>
            <html-el:text property="st_pub_date" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
            至
            <html-el:text property="en_pub_date" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
            &nbsp;&nbsp;<strong class="fb">资讯类别：</strong>
            <html-el:select property="article_type_id" style="width:80px;">
              <html-el:option value="">全部</html-el:option>
              <html-el:option value="-1">通知</html-el:option>
              <c:forEach var="cur" items="${peArticleTypeList}">
              	<html-el:option value="${cur.id}">${cur.type_name}</html-el:option>
              </c:forEach>
            </html-el:select>
            &nbsp;&nbsp;
            <input type="submit" name="" value="搜索" id="btn_submit" class="but1" /></td>  
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
  	<div style="overflow-x:auto;">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
          <tr class="tabtt1">
            <td align="center" width="5%" nowrap="nowrap">序号</td>
            <td align="center" nowrap="nowrap">资讯标题</td>
            <td align="center" nowrap="nowrap" width="10%">资讯类别</td>
            <td align="center" nowrap="nowrap" width="10%">添加人</td>
            <td align="center" nowrap="nowrap" width="12%">发布时间</td>
            <td width="8%" nowrap="nowrap" align="center" >状态</td>
            <td width="5%" nowrap="nowrap" align="center" >排序值</td>
            <td width="6%" nowrap="nowrap" align="center" >访问次数</td>
            <td width="6%" nowrap="nowrap" align="center" >评论次数</td>
            <td width="12%" nowrap="nowrap" align="center" >操作</td>
          </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="left">${vs.count}</td>
              <td align="left">${fn:escapeXml(cur.title)}</td>
              <td align="center">
              <c:if test="${empty cur.map.type_name }">通知</c:if>
              <c:if test="${not empty cur.map.type_name }"> ${fn:escapeXml(cur.map.type_name)}</c:if>
              </td>
              <td align="left" nowrap="nowrap">${fn:escapeXml(cur.add_user_name)}</td>
              <td align="left"><fmt:formatDate value="${cur.pub_date}" pattern="yyyy-MM-dd hh:mm"/></td>
              <td align="center" nowrap="nowrap"><c:choose>
                  <c:when test="${cur.states eq 0 }"><span style="color:red;">未发布</span></c:when>
                  <c:when test="${cur.states eq 1 }"><span style="color:green;">已发布</span></c:when>
                </c:choose></td>
              <td align="right" nowrap="nowrap">${cur.order_value}</td>
              <td align="right" nowrap="nowrap">${cur.view_counts}</td>
              <td align="right" nowrap="nowrap">${cur.map.comment_num}</td>
              <td><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaGroupPeArticleInfo.do', 'view','id=${cur.id}&'+$('#bottomPageForm').serialize())">查看</span></td>
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
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
          </c:forEach>
        </table>
      </div>
      <br />
      <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaGroupPeArticleInfo.do">
        <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
              <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("title", "${fn:escapeXml(af.map.title)}");
            pager.addHiddenInputs("st_pub_date", "${af.map.st_pub_date}");
            pager.addHiddenInputs("en_pub_date", "${af.map.en_pub_date}");
            pager.addHiddenInputs("article_type_id", "${af.map.article_type_id}");
            document.write(pager.toString());
            </script></td>
          </tr>
        </table>
      </form>
    <br />
    
  </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>  
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>