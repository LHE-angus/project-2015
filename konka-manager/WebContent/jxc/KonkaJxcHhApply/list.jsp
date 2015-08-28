<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>退换货管理&gt; 换货申请</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
-->
</style>
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：${naviString}</div>
  <div class="rtabcont1">
    <html-el:form action="/KonkaJxcHhApply.do">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="brand_id" value="114" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
        <tr>
          <td align="left" nowrap="nowrap"><strong class="fb">产品型号：</strong>
            <html-el:text property="pd_name_like" styleClass="webinput" styleId="pd_name_like" />
            &nbsp;
            <input class="bgSearch" type="submit" name="Submit" value="搜 索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <form id="listForm" name="listForm" method="post" action="KonkaJxcHhApply.do?method=delete">
    <input type="hidden" name="method" id="method" value="delete" />
    <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
    <div class="rtabcont1">
      <%@ include file="/commons/pages/messages.jsp"%>
      <input class="bgButtonReset" type="button" name="delete" id="delete" value="删除所选" onclick="this.form.action += '&' + $('#bottomPageForm').serialize();confirmDeleteAll(this.form);" />
      <input class="bgButtonAdd" type="submit" name="Submit2" value="新 增" onclick="location.href='KonkaJxcHhApply.do?method=add&mod_id=${af.map.mod_id}';return false;" />
    </div>
    <div class="rtabcont1">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th width="5%"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></th>
          <th width="20%" nowrap="nowrap">产品类别</th>
          <th width="20%" nowrap="nowrap">产品型号</th>
          <th width="20%" nowrap="nowrap">换货产品类型</th>
          <th nowrap="nowrap">产品单价</th>
          <th width="5%" nowrap="nowrap">换货数量</th>
          <th width="10%" nowrap="nowrap">换货日期</th>
          <th width="10%" nowrap="nowrap">审批状态</th>
          <th width="10%" nowrap="nowrap">是否确认</th>
          <th width="10%" nowrap="nowrap">操作</th>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td height="28"  align="center"><c:if test="${(cur.is_audit ne 0) or (cur.is_del eq 1)}">
                <input name="pks" type="checkbox" id="pks" value="${cur.id}" disabled="disabled" />
              </c:if>
              <c:if test="${(cur.is_del eq 0) and (cur.is_audit eq 0)}">
                <input name="pks" type="checkbox" id="pks" value="${cur.id}" />
              </c:if></td>
            <td align="center">${fn:escapeXml(cur.pd_type_name)}</td>
            <td align="center">${fn:escapeXml(cur.pd_name)}</td>
            <td align="center"><c:if test="${cur.hh_type eq 0}">残次品</c:if>
              <c:if test="${cur.hh_type eq 1}">正品</c:if></td>
            <td align="center">${cur.price}</td>
            <td align="center">${cur.hh_count}</td>
            <td align="center"><fmt:formatDate value="${cur.in_date}" pattern="yyyy-MM-dd" /></td>
            <td align="center"><c:if test="${cur.is_audit eq 0}">未审批</c:if>
              <c:if test="${cur.is_audit eq -1}"><span style="color:#F00;">未通过</span></c:if>
              <c:if test="${cur.is_audit eq 1}"><span style="color:#060;">已通过</span></c:if></td>
            <td align="center"><c:if test="${cur.hh_is_confirm ne 1}"><span style="color:#F00;">未确认</span></c:if>
              <c:if test="${cur.hh_is_confirm eq 1}"><span style="color:#060;">已确认</span></c:if></td>
            <td align="center" nowrap="nowrap"><span style="cursor:pointer;"   class="fblue"  onclick="doNeedMethod(null, 'KonkaJxcHhApply.do', 'view','id=${cur.id}&'+$('#bottomPageForm').serialize())">查看</span> |
              <c:if test="${(cur.is_del eq 1)}" var="isDel">
                <!--
              	<span style="color:#ccc;">修改</span> |
              	-->
                <span style="color:#ccc;"  class="fblue"  >确认</span> | <span style="color:#ccc;"  class="fblue"  >删除</span> </c:if>
              <c:if test="${not isDel}">
                <c:if test="${(cur.is_audit eq 0)}" var="notAudit">
                  <!--<span style="cursor:pointer;" onclick="doNeedMethod(null, 'KonkaJxcHhApply.do', 'edit','id=${cur.id}&'+$('#bottomPageForm').serialize())">修改</span>-->
                  <span style="color:#ccc;"  class="fblue"  >确认</span> | <span style="cursor:pointer;"  class="fblue"  onclick="confirmDelete(null, 'KonkaJxcHhApply.do','id=${cur.id}&'+$('#bottomPageForm').serialize())">删除</span> </c:if>
                <c:if test="${not notAudit}">
                  <!--<span style="color:#ccc;">修改</span> |
              		-->
                  <c:if test="${(cur.is_audit eq 1)}">
                    <c:if test="${(cur.hh_is_confirm eq 1)}"> <span style="color:#ccc;"  class="fblue"  >确认</span> | </c:if>
                    <c:if test="${(cur.hh_is_confirm ne 1)}"> <span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod('是否确认？', 'KonkaJxcHhApply.do', 'confirm','id=${cur.id}&'+$('#bottomPageForm').serialize())">确认</span> | </c:if>
                  </c:if>
                  <c:if test="${(cur.is_audit eq -1)}"> <span style="color:#ccc;"  class="fblue"  >确认</span> | </c:if>
                  <span style="color:#ccc;" class="fblue"  > 删除</span> </c:if>
              </c:if></td>
          </tr>
          <c:if test="${vs.last eq true}">
            <c:set var="i" value="${vs.count}" />
          </c:if>
        </c:forEach>
      </table>
    </div>
  </form>
  <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaJxcHhApply.do">
    <table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
      <tr>
        <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
          <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("brand_id", "114");
	            pager.addHiddenInputs("pd_name_like", "${fn:escapeXml(af.map.pd_name_like)}");
	            document.write(pager.toString());
	      </script></td>
      </tr>
    </table>
  </form>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
});
//]]></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>