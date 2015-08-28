<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>退换货管理 &gt; 退货申请</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
-->
</style>
</head>
<body>
<div style="width: 100%;">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;"/> 当前位置：退换货管理 &gt; 退货申请</div>
  <div class="rtabcont1">
    <html-el:form action="/JxcThApply">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="keySeq" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableTop">
        <tr>
          <td nowrap="nowrap"><strong class="fb">产品大类：</strong>
            <html-el:select property="pd_type_id" styleId="pd_type_id" style="width:80px;">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${basePdTypeList}">
                <html-el:option value="${cur.pd_type}">${fn:escapeXml(cur.pd_name)}</html-el:option>
              </c:forEach>
              <html-el:option value="0">其他</html-el:option>
            </html-el:select>
            &nbsp;
            <input class="bgSearch" type="submit" name="Submit" value="搜 索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <form id="listForm" name="listForm" method="post" action="JxcThApply.do?method=delete">
    <input type="hidden" name="method" id="method" value="delete" />
    <input type="hidden" name="mod_id" id="mod_id" />
    <div class="rtabcont1">
      <%@ include file="/commons/pages/messages.jsp" %>
      <input class="bgButtonReset" type="button" name="delete" id="delete" value="删除所选" onclick="this.form.action += '&' + $('#bottomPageForm').serialize();confirmDeleteAll(this.form);" />
      <input class="bgButtonAdd" type="button" name="Submit2" value="新 增" onclick="location.href='JxcThApply.do?method=add&keySeq=${af.map.keySeq}';" />
    </div>
    <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="1" cellpadding="0" class="tableClass">
        <tr>
          <th width="5%" align="center"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></th>
          <th width="10%"nowrap="nowrap" align="center">产品大类</th>
          <th width="10%" nowrap="nowrap" align="center">产品品牌</th>
          <th width="16%" nowrap="nowrap" align="center">产品型号</th>
          <th nowrap="nowrap">产品单价</th>
          <th width="7%" nowrap="nowrap" align="center">退货数量</th>
          <th  nowrap="nowrap" align="center">退货原因</th>
          <th width="12%" nowrap="nowrap" align="center">退货日期</th>
          <th width="7%" nowrap="nowrap" align="center">是否审核</th>
          <th width="8%" nowrap="nowrap" align="center">操作</th>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap"><c:if test="${(cur.is_audit eq 1)}">
                <input name="pks" type="checkbox" id="pks" value="${cur.id}" disabled="disabled" />
              </c:if>
              <c:if test="${(cur.is_audit ne 1)}">
                <input name="pks" type="checkbox" id="pks" value="${cur.id}" />
              </c:if></td>
            <td align="center">${fn:escapeXml(cur.pd_type_name)}</td>
            <td align="center">${fn:escapeXml(cur.brand_name)}</td>
            <td align="center">${fn:escapeXml(cur.pd_name)}</td>
            <td align="center">${cur.price}</td>
            <td align="center">${cur.th_count}</td>
            <td align="center">${fn:escapeXml(cur.th_reason)}</td>
            <td align="center"><fmt:formatDate value="${cur.in_date}" pattern="yyyy-MM-dd" /></td>
            <td align="center"><c:choose>
                <c:when test="${cur.is_audit eq 0}">未审批</c:when>
                <c:when test="${cur.is_audit eq -1}"><span style="color:#F00;">未通过</span></c:when>
                <c:when test="${cur.is_audit eq 1}"><span style="color:#060;">已通过</span></c:when>
              </c:choose></td>
            <td align="center" nowrap="nowrap"><span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'JxcThApply.do', 'view','id=${cur.id}&'+$('#bottomPageForm').serialize())">查看</span> |
              <c:if test="${(cur.is_audit eq 1) and (cur.th_is_confirm eq 0)}" var="canQr"> <span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod('点击确认后，将会减少改产品的库存，是否确认？', 'JxcThApply.do', 'confirm','id=${cur.id}&'+$('#bottomPageForm').serialize())">确认</span> | </c:if>
              <c:if test="${not canQr}"> <span style="color:#ccc;"  class="fblue"  >确认</span> | </c:if>
              <c:if test="${cur.is_audit eq 1}"><span style="color:#ccc;"  class="fblue"  >删除</span></c:if>
              <c:if test="${cur.is_audit ne 1}"><span style="cursor:pointer;"  class="fblue"  onclick="confirmDelete(null, 'JxcThApply.do','id=${cur.id}&'+$('#bottomPageForm').serialize())">删除</span></c:if></td>
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
          </tr>
        </c:forEach>
      </table>
    </div>
  </form>
  <form id="bottomPageForm" name="bottomPageForm" method="post" action="JxcThApply.do">
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
      <tr>
        <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
          <script type="text/javascript">
			            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
			            pager.addHiddenInputs("method", "list");
			            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			            pager.addHiddenInputs("pd_type_id", "${af.map.pd_type_id}");
			            pager.addHiddenInputs("keySeq", "${af.map.keySeq}");
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
<script type="text/javascript">
//]]></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
