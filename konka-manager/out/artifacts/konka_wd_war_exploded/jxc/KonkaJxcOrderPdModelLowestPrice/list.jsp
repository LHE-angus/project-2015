<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<title>产品型号限价记录</title>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
</head>
<body>
<div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：${naviString}</div>
<div class="rtabcont1">
  <html-el:form action="/KonkaJxcOrderPdModelLowestPrice.do">
    <html-el:hidden property="method" value="list" />
    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
      <tr>
        <td align="left" nowrap="nowrap"><input type="hidden" name="keySeq" id="keySeq" value="${af.map.keySeq}" />
          <strong class="fb">产品型号：</strong>
          <html-el:select property="pd_id" styleId="pd_id">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${pdList}" varStatus="vs">
    				<html-el:option value="${cur.pd_id}">${cur.md_name}</html-el:option>
  			  </c:forEach>
          </html-el:select>
          &nbsp;<strong class="fb">限定年份：</strong>
          <html-el:select property="set_year" styleId="set_year">
          <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${yearList}">
                <html-el:option value="${cur}">${fn:escapeXml(cur)}</html-el:option>
              </c:forEach>
          </html-el:select>
          &nbsp;<strong class="fb">限定月份：</strong>
          <html-el:select property="set_month" styleId="set_month">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${monthList}">
                <html-el:option value="${cur}">${fn:escapeXml(cur)}</html-el:option>
              </c:forEach>
          </html-el:select>
        </td>
       </tr>
       <tr>
         <td align="left" nowrap="nowrap">
         <strong class="fb">是否删除：</strong>
          <html-el:select property="is_del" styleId="is_del">
              <html-el:option value="">全部</html-el:option>
    		  <html-el:option value="0">未删除</html-el:option>
    		  <html-el:option value="1">已删除</html-el:option>
          </html-el:select>
          &nbsp;
          <input name="button" type="submit" class="bgSearch" id="button" value="搜 索" /></td>
      </tr>
    </table>
  </html-el:form>
</div>

<html-el:form action="/KonkaJxcOrderPdModelLowestPrice.do?method=delete">
<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
<div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
  <c:if test="${empty hide}">
  	<input class="bgButtonReset" type="button" name="delete" id="delete" value="删除所选" onclick="this.form.action += '&' + $('#bottomPageForm').serialize();confirmDeleteAll(this.form);" />
  	<input type="button" name="add" id="add" class="bgButtonAdd" value="新 增" onclick="location.href='KonkaJxcOrderPdModelLowestPrice.do?method=add&mod_id=${af.map.mod_id}';" />
  </c:if>
</div>
<div class="rtabcont1">
  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
    <tr>
      <th width="4%"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></th>
      <th>产品型号</th>
      <th width="12%">品牌</th>
      <th width="10%">产品类型</th>
      <th width="10%">限定时间</th>
      <th width="10%">最低限价</th>
      <th width="15%">部门</th>
      <th width="5%">是否删除</th>
      <th width="12%">操作</th>
    </tr>
    <c:forEach items="${konkaPePdModelLowestPriceList}" var="cur" varStatus="status">
      <tr>
        <td align="center" nowrap="nowrap">
			<c:if test="${cur.is_del eq 0}" var="isDel">
                <input name="pks" type="checkbox" id="pks" value="${cur.id}" />
            </c:if>
            <c:if test="${not isDel}" var="isDel">
                <input name="pks" type="checkbox" id="pks" value="${cur.id}" disabled="disabled" />
            </c:if>
		</td>
        <td align="center" nowrap="nowrap">${fn:escapeXml(cur.pd_name)}</td>
        <td align="center" nowrap="nowrap">${fn:escapeXml(cur.brand_name)}</td>
        <td align="center" nowrap="nowrap">${fn:escapeXml(cur.pd_type_name)}</td>
        <td align="center">${fn:escapeXml(cur.set_year)}-${fn:escapeXml(cur.set_month)}</td>
        <td align="center"><fmt:formatNumber value="${cur.lowest_price}" pattern="0.00" /></td>
        <td align="center">${fn:escapeXml(cur.dept_name)}</td>
        <td align="center">
        	<c:if test="${cur.is_del eq 0}"><span>未删除</span></c:if>
        	<c:if test="${cur.is_del eq 1}"><span style="color:#F00;">已删除</span></c:if>
        </td>
        <td align="center">
        <c:if test="${empty hide}">
        	<span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'KonkaJxcOrderPdModelLowestPrice.do', 'view','&id=${cur.id}&'+$('#bottomPageForm').serialize())">查看</span>
        	<c:if test="${cur.is_del eq 0}">|&nbsp;<span style="cursor: pointer;" class="fblue" onclick="confirmUpdate(null, 'KonkaJxcOrderPdModelLowestPrice.do', 'id=${cur.id}&' + $('#bottomPageForm').serialize())">修改</span></c:if>
        	<c:if test="${cur.is_del eq 1}">|&nbsp;<span title="已经删除，不能修改">修改</span></c:if>
        	<c:if test="${cur.is_del eq 0}">|&nbsp;<span style="cursor: pointer;" class="fblue" onclick="confirmDelete(null, 'KonkaJxcOrderPdModelLowestPrice.do', 'id=${cur.id}&' + $('#bottomPageForm').serialize())">删除</span></c:if>
        	<c:if test="${cur.is_del eq 1}">|&nbsp;<span title="已经删除">删除</span></c:if>
        </c:if>
        <c:if test="${hide eq 1}">
        	<span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'KonkaJxcOrderPdModelLowestPrice.do', 'view','&id=${cur.id}&'+$('#bottomPageForm').serialize())">查看</span>
        </c:if>
        </td>
      </tr>
    </c:forEach>
  </table> 
</div>
</html-el:form>

<div class="rtabcont3">
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaJxcOrderPdModelLowestPrice.do">
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript">
		       var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		       pager.addHiddenInputs("method", "list");
		       pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
		       pager.addHiddenInputs("pd_id", "${fn:escapeXml(af.map.pd_id)}");
		       pager.addHiddenInputs("set_year", "${fn:escapeXml(af.map.set_year)}");
		       pager.addHiddenInputs("set_month", "${fn:escapeXml(af.map.set_month)}");
		       pager.addHiddenInputs("is_del", "${fn:escapeXml(af.map.is_del)}");
		       document.write(pager.toString());
		       </script></td>
        </tr>
      </table>
    </form>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/external/bgiframe/jquery.bgiframe.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#pd_id").multiselect({
		selectedList: 1,
		multiple: false,
		minWidth:280
	}).multiselectfilter({width:180});
});
	
		
//]]></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>