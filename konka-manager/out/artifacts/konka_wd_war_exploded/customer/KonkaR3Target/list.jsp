<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
<style>
.filed_border{
	border-left: 1px solid #ccc;;
	border-right: 1px solid #ccc;;
	border-bottom:1px solid #ccc;;
}
</style>
</head>
<body>
<div class="oarcont" id="body_oarcont" style="position:relative;overflow:hidden;">
<div class="oartop">
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
      <td>当前位置：${naviString}</td>
    </tr>
  </table>
</div>
<div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
  <input name="input" type="button" id="gotoAdd" class="bgButtonAdd" value="新 增" onclick="location.href='KonkaR3Target.do?method=add&mod_id=${af.map.mod_id}'" />
</div>
  <div class="rtabcont1" style="overflow-x:auto;">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" align="center" nowrap="nowrap">序号</td>
          <td width="5%" align="center" nowrap="nowrap">年度</td>
          <td width="5%" align="center" nowrap="nowrap">1月</td>
          <td width="5%" align="center" nowrap="nowrap">2月</td>
          <td width="5%" align="center" nowrap="nowrap">3月</td>
          <td width="5%" align="center" nowrap="nowrap">4月</td>
          <td width="5%" align="center" nowrap="nowrap">5月</td>
          <td width="5%" align="center" nowrap="nowrap">6月</td>
          <td width="5%" align="center" nowrap="nowrap">7月</td>
          <td width="5%" align="center" nowrap="nowrap">8月</td>
          <td width="5%" align="center" nowrap="nowrap">9月</td>
          <td width="5%" align="center" nowrap="nowrap">10月</td>
          <td width="5%" align="center" nowrap="nowrap">11月</td>
          <td width="5%" align="center" nowrap="nowrap">12月</td>
          <td width="5%" align="center" nowrap="nowrap">年度任务总额(元)</td>
          <td width="5%" align="center" nowrap="nowrap">操作</td>
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td nowrap="nowrap" align="center">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
              <td align="center" nowrap="nowrap">${cur.sale_year }</td>
              <td align="center" nowrap="nowrap">${cur.jan }</td>
              <td align="center" nowrap="nowrap">${cur.feb }</td>
              <td align="center" nowrap="nowrap">${cur.mar }</td>
              <td align="center" nowrap="nowrap">${cur.apr }</td>
              <td align="center" nowrap="nowrap">${cur.may }</td>
              <td align="center" nowrap="nowrap">${cur.june }</td>
              <td align="center" nowrap="nowrap">${cur.july }</td>
              <td align="center" nowrap="nowrap">${cur.aug }</td>
              <td align="center" nowrap="nowrap">${cur.sept }</td>
              <td align="center" nowrap="nowrap">${cur.oct }</td>
              <td align="center" nowrap="nowrap">${cur.nov }</td>
              <td align="center" nowrap="nowrap">${cur.dece }</td>
              <td align="center" nowrap="nowrap">${cur.year_total }</td>
              <td align="center" nowrap="nowrap">
              	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaR3Target.do', 'edit','&mod_id=${af.map.mod_id}&sales_year=${cur.sale_year}&c_id=${cur.c_id}')">修改</span>
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
  <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaR3Store.do">
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
		            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		            pager.addHiddenInputs("method", "list");
		            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
		            document.write(pager.toString());
		   </script></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
