<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
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
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align: middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/zmd/KonkaXxZmdHdSet">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td><strong class="fb">信用类型：</strong>
            <html-el:text property="credit_type_like" styleId="credit_type_like" size="16" maxlength="20"></html-el:text>
            &nbsp;&nbsp;
            <input class="but1" type="submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp"%>
  </div>
  <c:if test="${!empty dept_id}">
    <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><input class="but2" type="submit" name="Submit2" value="新增" onclick="location.href='KonkaXxZmdCredit.do?method=add&mod_id=${af.map.mod_id}&tree_param=${tree_param}&dept_id=${dept_id}';" /></td>
        </tr>
      </table>
    </div>
  </c:if>
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td nowrap="nowrap" align="center" width="5%">序号</td>
        <td nowrap="nowrap" align="center" width="14%">分公司</td>
        <td nowrap="nowrap" align="center" width="14%">信用类型</td>
        <td nowrap="nowrap"  align="center" width="14%">信用额度（元）</td>
        <td nowrap="nowrap" align="center" >信用类型说明</td>
        <td nowrap="nowrap"  align="center" width="14%">操作时间</td>
        <td nowrap="nowrap"  align="center" width="14%">操作人</td>
        <td nowrap="nowrap" align="center" width="8%">操作</td>
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
            <td align="left">${fn:escapeXml(dept_name)}</td>
            <td align="left">${fn:escapeXml(cur.credit_type)}</td>
            <td align="right"><fmt:formatNumber value="${cur.credit_money}" pattern="#0.00" /></td>
            <td align="left" title="${cur.credit_des}"><c:out value="${fnx:abbreviate(cur.credit_des, 2 * 10, '...')}" />
              <c:if test="${empty cur.credit_des}">无</c:if></td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
            <td align="right">${cur.map.add_user_name}</td>
            <td align="center" nowrap="nowrap"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxZmdCredit.do', 'edit','id=${cur.id}&mod_id=${af.map.mod_id}&detp_id=${af.map.detp_id}&'+$('#bottomPageForm').serialize())">修改</span></td>
          </tr>
        </c:forEach>
        <c:forEach begin="${fn:length(entityList)}" end="${af.map.pager.pageSize - 1}" step="1">
          <tr>
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
      </tbody>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxZmdCredit.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("tree_param", "${tree_param}");
				pager.addHiddenInputs("credit_type_like", "${af.map.credit_type_like}");
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
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}
});
//]]>
</script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
