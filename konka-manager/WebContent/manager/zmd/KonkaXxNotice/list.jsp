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
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
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
    <html-el:form action="/zmd/KonkaXxNotice">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td><strong class="fb">标题：</strong>
            <html-el:text property="notice_title_like" styleId="notice_title_like" size="40" maxlength="30"></html-el:text>
            &nbsp;<strong class="fb">是否发布：</strong>
            <html-el:select property="is_public" styleId="is_public" onchange="this.form.submit();">
              <html-el:option value="">==请选择==</html-el:option>
              <html-el:option value="0">未发布</html-el:option>
              <html-el:option value="1">已发布</html-el:option>
            </html-el:select>
            <br/>
            <strong class="fb">添加时间：</strong>
            <html-el:text property="add_date_ge" styleId="add_date_ge" size="8" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
            -
            <html-el:text property="add_date_le" styleId="add_date_le" size="8" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
            &nbsp;<strong class="fb">发布时间：</strong>
            <html-el:text property="public_date_ge" styleId="public_date_ge" size="8" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
            -
            <html-el:text property="public_date_le" styleId="public_date_le" size="8" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
            &nbsp;
            <input class="but1" type="submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp"%>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><input class="but2" type="submit" name="Submit2" value="新增" onclick="location.href='KonkaXxNotice.do?method=add&mod_id=${af.map.mod_id}&tree_param=${tree_param}';" /></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td nowrap="nowrap" align="center" width="5%">序号</td>
        <td nowrap="nowrap" align="center">标题</td>
        <td nowrap="nowrap" align="center" width="8%">添加人</td>
        <td nowrap="nowrap" align="center" width="12%">添加时间</td>
        <td nowrap="nowrap" align="center" width="8%">是否发布</td>
        <td nowrap="nowrap" align="center" width="8%">发布人</td>
        <td nowrap="nowrap" align="center" width="12%">发布时间</td>
        <td nowrap="nowrap" align="center" width="12%">操作</td>
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
            <td align="left" title="${cur.notice_title}">${fnx:abbreviate(cur.notice_title, 2 * 26, '')}</td>
            <td align="center" title="${cur.map.add_user_name}"><c:out value="${fnx:abbreviate(cur.map.add_user_name,2 * 6, '')}"/></td>
            <td align="center"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
            <td align="center"><c:choose>
                <c:when test="${cur.is_public eq 0}">未发布</c:when>
                <c:when test="${cur.is_public eq 1}">已发布</c:when>
              </c:choose></td>
            <td align="center" title="${cur.map.public_user_name}"><c:out value="${fnx:abbreviate(cur.map.public_user_name,2 * 6, '')}"/>
              <c:if test="${empty cur.map.public_user_name}"><span style="color:gray;">未填写</span></c:if></td>
            <td align="center"><fmt:formatDate value="${cur.public_date}" pattern="yyyy-MM-dd HH:mm:ss" />
              <c:if test="${empty cur.public_date}"><span style="color:gray;">未填写</span></c:if></td>
            <td align="center" nowrap="nowrap"><span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxNotice.do', 'view','id=${cur.id}&'+$('#bottomPageForm').serialize())">查看</span>|
              <c:if test="${cur.is_public eq 0}"> <span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxNotice.do', 'edit','id=${cur.id}&'+$('#bottomPageForm').serialize())">修改</span>|<span style="cursor: pointer;" class="fblue" onclick="doNeedMethod('确定要发布该记录吗？', 'KonkaXxNotice.do', 'publish','id=${cur.id}&'+$('#bottomPageForm').serialize())">发布</span>| </c:if>
              <c:if test="${cur.is_public eq 1}"> <span style="color:#ccc;">修改</span>|<span style="color:#ccc;">发布</span>| </c:if>
              <span style="cursor: pointer;" class="fblue" onclick="doNeedMethod('确定要删除该记录吗？', 'KonkaXxNotice.do', 'delete','id=${cur.id}&'+$('#bottomPageForm').serialize())">删除</span></td>
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
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxNotice.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("tree_param", "${tree_param}");
				pager.addHiddenInputs("notice_title_like", "${af.map.notice_title_like}");
				pager.addHiddenInputs("add_date_ge", "${af.map.add_date_ge}");
				pager.addHiddenInputs("add_date_le", "${af.map.add_date_le}");
				pager.addHiddenInputs("public_date_ge", "${af.map.public_date_ge}");
				pager.addHiddenInputs("public_date_le", "${af.map.public_date_le}");
				pager.addHiddenInputs("is_public", "${af.map.is_public}");
				document.write(pager.toString());
			  </script>
            </div></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#add_date_ge").datepicker();
	$("#add_date_le").datepicker();
	$("#public_date_ge").datepicker();
	$("#public_date_le").datepicker();

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
