<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>活动类型维护</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：活动类型维护</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/KonkaSpActivityType">
      <html-el:hidden property="method" styleId="method" value="list" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="p_type" styleId="p_type" value="${af.map.p_type}"/>
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td><strong class="fb">活动类型：</strong>
          <html-el:text property="hd_type_like" size="20" style="width:90px;" maxlength="10" styleId="hd_type_like" styleClass="webinput" /></td>
          <td><input class="but1" type="submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><input class="but2" type="submit" name="Submit2" value="新增" onclick="location.href='KonkaSpActivityType.do?method=add&p_type=${af.map.p_type}&mod_id=${af.map.mod_id}';" /></td>
      </tr>
    </table>
    </div>
  <div class="rtabcont1">
  	<div style="overflow-x:auto;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center">序号</td>
        <td width="15%" nowrap="nowrap" align="center">活动类型</td>
        <td width="15%" align="center">活动类型编码</td>
        <td width="8%" align="center">活动开始时间</td>
        <td width="8%" nowrap="nowrap" align="center">活动结束时间</td>
        <c:if test="${0 eq af.map.p_type}">
        <td width="8%" nowrap="nowrap" align="center">活动达标金额（万元）</td>
        </c:if>
        <td width="8%" nowrap="nowrap" align="center">操作</td>
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap"> ${vs.count}</td>
            <td align="left" nowrap="nowrap">${cur.hd_type}</td>
            <td align="left" nowrap="nowrap">${cur.hd_type_sn}</td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.s_date}" pattern="yyyy-MM-dd" /></td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.e_date}" pattern="yyyy-MM-dd" /></td>
            <c:if test="${0 eq af.map.p_type}">
            <td align="right" nowrap="nowrap">${cur.money}</td>
            </c:if>
            <td nowrap="nowrap" align="center"><logic-el:match name="popedom" value="+0+">
				<a class="butbase" href="javascript:void(0);"><span style="cursor:pointer;" class="fblue"  onclick="doNeedMethod(null, 'KonkaSpActivityType.do', 'view','id=${cur.id}&p_type=${af.map.p_type}&mod_id=${af.map.mod_id}')">查看</span></a>
			  </logic-el:match>
			   |
              <logic-el:match name="popedom" value="+2+">
                <a class="butbase" href="javascript:void(0);"><span style="cursor:pointer;" class="fblue"  onclick="confirmUpdate(null, 'KonkaSpActivityType.do', 'id=${cur.id}&p_type=${af.map.p_type}&' + $('#bottomPageForm').serialize())">修改</span></a>
              </logic-el:match>
            <logic-el:match name="popedom" value="+4+">
                <a class="butbase" href="javascript:void(0);"></a>
              </logic-el:match></td>
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
            <c:if test="${0 eq af.map.p_type}">
            <td>&nbsp;</td>
            </c:if>
            <td>&nbsp;</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    </div>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaSpActivityType.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align:right; padding-right:5px;"> 
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
              <script type="text/javascript">
							var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
							pager.addHiddenInputs("method", "list");
							pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
							pager.addHiddenInputs("hd_type_like", "${af.map.hd_type_like}");
							document.write(pager.toString());
						</script> 
            </div></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	subcomp_id_chg();
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>