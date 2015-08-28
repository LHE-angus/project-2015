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
    <html-el:form action="/KonkaJxcThApply.do">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
        <tr>
          <td align="left" nowrap="nowrap"><strong class="fb">&nbsp;&nbsp;产品类别：</strong>
            <html-el:select property="pd_type_id">
              <html-el:option value="">请选择</html-el:option>
              <c:forEach items="${basePdClassList}" var="cur" varStatus="vs">
                <html-el:option value="${cur.cls_id}">${fn:escapeXml(cur.tree_name)}</html-el:option>
              </c:forEach>
            </html-el:select>
            &nbsp; <strong class="fb">所属仓库：</strong>
            <html-el:select property="th_store_id_son" styleId="th_store_id_son">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${storeList}">
                <html-el:option value="${cur.id}">${fn:escapeXml(cur.store_name)}</html-el:option>
              </c:forEach>
            </html-el:select>
            &nbsp;
            <input class="bgSearch" type="submit" name="Submit" value="搜 索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <form id="listForm" name="listForm" method="post" action="KonkaJxcThApply.do?method=delete">
    <input type="hidden" name="method" id="method" value="delete" />
    <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
    <div class="rtabcont1">
      <%@ include file="/commons/pages/messages.jsp"%>
      <input class="bgButtonReset" type="button" name="delete" id="delete" value="删除所选" onclick="this.form.action += '&' + $('#bottomPageForm').serialize();confirmDeleteAll(this.form);" />
      <input class="bgButtonAdd" type="submit" name="Submit2" value="新 增" onclick="location.href='KonkaJxcThApply.do?method=add&mod_id=${af.map.mod_id}';return false;" />
    </div>
    <div class="rtabcont1">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th width="5%"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></th>
          <th width="15%" nowrap="nowrap">产品类别</th>
          <th nowrap="nowrap">产品型号</th>
          <th width="8%" nowrap="nowrap">退货产品类型</th>
          <th width="5%" nowrap="nowrap">退货数量</th>
          <th nowrap="nowrap">产品单价</th>
          <th width="15%" nowrap="nowrap">退货日期</th>
          <th width="10%" nowrap="nowrap">审批状态</th>
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
            <td align="center"><c:if test="${cur.th_type eq 0}">残次品</c:if>
              <c:if test="${cur.th_type eq 1}">正品</c:if></td>
            <td align="center">${cur.th_count}</td>
            <td align="center">${cur.price}</td>
            <td align="center"><fmt:formatDate value="${cur.in_date}" pattern="yyyy-MM-dd" /></td>
            <td align="center"><c:if test="${cur.is_audit eq 0}">未审批</c:if>
              <c:if test="${cur.is_audit eq -1}"><span style="color:#F00;">未通过</span></c:if>
              <c:if test="${cur.is_audit eq 1}"><span style="color:#060;">已通过</span></c:if></td>
            <td align="center" nowrap="nowrap"><span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'KonkaJxcThApply.do', 'view','id=${cur.id}&'+$('#bottomPageForm').serialize())">查看</span> |
              <c:if test="${(cur.is_del eq 1)}" var="isDel"> <span style="color:#ccc;" class="fblue"  >确认</span> | <span style="color:#ccc;" class="fblue"  >删除</span> </c:if>
              <c:if test="${not isDel}">
                <c:if test="${(cur.is_audit eq 0)}" var="notAudit">
                  <!--<span style="cursor:pointer;" onclick="doNeedMethod(null, 'KonkaJxcHhApply.do', 'edit','id=${cur.id}&'+$('#bottomPageForm').serialize())">修改</span>-->
                  <span style="color:#ccc;" class="fblue"  >确认</span> | <span style="cursor:pointer;"  class="fblue"  onclick="confirmDelete(null, 'KonkaJxcThApply.do','id=${cur.id}&'+$('#bottomPageForm').serialize())">删除</span> </c:if>
                <c:if test="${not notAudit}">
                  <!--<span style="color:#ccc;">修改</span> |
              		-->
                  <c:if test="${(cur.is_audit eq 1)}">
                    <c:if test="${(cur.th_is_confirm eq 1)}"> <span style="color:#ccc;" class="fblue"  >确认</span> | </c:if>
                    <c:if test="${(cur.th_is_confirm ne 1)}"> <span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod('点击确认后，将更新退货仓库的库存，是否确认？', 'KonkaJxcThApply.do', 'confirm','id=${cur.id}&'+$('#bottomPageForm').serialize())">确认</span> | </c:if>
                  </c:if>
                  <c:if test="${(cur.is_audit eq -1)}"> <span style="color:#ccc;" class="fblue"  >确认</span> | </c:if>
                  <span style="color:#ccc;" class="fblue"  >删除</span> </c:if>
              </c:if></td>
          </tr>
          <c:if test="${vs.last eq true}">
            <c:set var="i" value="${vs.count}" />
          </c:if>
        </c:forEach>
      </table>
    </div>
  </form>
  <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaJxcThApply.do">
    <table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
      <tr>
        <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
          <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("pd_type_id", "${fn:escapeXml(af.map.pd_type_id)}");
	            pager.addHiddenInputs("store_id", "${fn:escapeXml(af.map.store_id)}");
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
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>