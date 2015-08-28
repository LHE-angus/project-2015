<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<style type="text/css">
select {
	font-family:Microsoft YAHEI;
	font-size:12px;
}
input {
	font-family:Microsoft YAHEI;
	font-size:12px;
}
</style>
<title>选择往来单位</title>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
<base target="_self">
</head>
<body style="font-family:Microsoft Yahei;">
<html-el:form action="/manager/JBasePartner?method=list_select">
  <html-el:hidden property="mod_id" styleId="mod_id" />
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
      <tr>
        <td height="36" align="left" style="padding-left:5px;">&nbsp;<strong class="fb">来往单位名称：</strong>
          <html-el:text property="partner_name_like" styleClass="webinput" styleId="partner_name_like" maxlength="40"/>
          &nbsp;<strong class="fb">伙伴类型：</strong> 	
          <html-el:select property="partner_type">
            <html-el:option value="">请选择...</html-el:option>
            <html-el:option value="0">供应商</html-el:option>
            <html-el:option value="1">客户</html-el:option>
            <html-el:option value="10">供应商和客户</html-el:option>
          </html-el:select>
          &nbsp; <strong class="fb">是否停用：</strong>
          <html-el:select property="is_del" styleId="is_del" style="width:70px;" onchange="this.form.submit();">
            <html-el:option value="0">未停用</html-el:option>
            <html-el:option value="1">已停用</html-el:option>
          </html-el:select>
          &nbsp;&nbsp;&nbsp;
          <input name="button" type="submit" class="bgSearch" id="button" value="搜 索" /></td>
      </tr>
    </table>
  </div>
</html-el:form>
<div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
  <input name="input" type="button" id="gotoAdd" class="bgButtonAdd" value="新 增" onclick="location.href='${ctx}/customer/manager/JBasePartner.do?method=add&mod_id=${af.map.mod_id}'" />
</div>
<div class="rtabcont1">
<div style="overflow-x: auto">
  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
    <tr>
      <th width="5%" nowrap="nowrap">序号</th>
      <th width="5%" nowrap="nowrap">名称</th>
      <th width="8%" nowrap="nowrap">编码</th>
      <th width="6%" nowrap="nowrap">伙伴类型</th>
      <th width="6%" nowrap="nowrap">伙伴对象</th>
      <th nowrap="nowrap">地址</th>
      <th width="6%" nowrap="nowrap">联系人姓名</th>
      <th width="8%" nowrap="nowrap">电话</th>
      <th width="5%" nowrap="nowrap">停用</th>
      <th width="5%" nowrap="nowrap">账户</th>
      <th width="6%" nowrap="nowrap">添加时间</th>
      <th width="8%" nowrap="nowrap">选择</th>
    </tr>
    <c:forEach items="${entityList}" var="cur" varStatus="vs">
      <tr>
        <td nowrap="nowrap" align="center" bgcolor="#fff2dc">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
        <td nowrap="nowrap" align="left">${cur.partner_name}</td>
        <td nowrap="nowrap" align="left">${cur.partner_sn}</td>
        <td align="left" nowrap="nowrap"><c:if test="${cur.partner_type eq '0'}"><span>供应商</span></c:if>
          <c:if test="${cur.partner_type eq '1'}"><span>客户</span></c:if>
          <c:if test="${cur.partner_type eq '10'}"><span>供应商和客户</span></c:if></td>
        <td nowrap="nowrap" align="left"><c:if test="${cur.partner_obj eq 1}"><span>组织/单位</span></c:if>
          <c:if test="${cur.partner_obj eq 0}"><span>个人</span></c:if></td>
        <td nowrap="nowrap" align="left">${cur.partner_addr}</td>
        <td nowrap="nowrap" align="left">${cur.link_name}</td>
        <td nowrap="nowrap" align="left">${cur.link_mobile}</td>
        <td nowrap="nowrap" align="center"><c:if test="${cur.is_del eq 0}"><span style="color:green;">否</span></c:if>
          <c:if test="${cur.is_del eq 1}"><span style="color:#f00;">是</span></c:if></td>
        <td nowrap="nowrap" align="center">${empty cur.partner_c_id ? '无' : '有'}</td>
        <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" /></td>
        <td align="center" nowrap="nowrap"><a href="javascript:void(0);" onclick="sel('${cur.partner_id}')">选择</a></td>
      </tr>
    </c:forEach>
  </table>
  </div>
  <c:if test="${not empty entityList}">
    <div class="rtabcont3">
      <form id="bottomPageForm" name="bottomPageForm" method="post" action="JBasePartner.do">
        <table width="100%" border="0" align="center" cellspacing="0" cellpadding="0">
          <tr>
            <td height="40" align="center"><script type="text/javascript">
		            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		            pager.addHiddenInputs("method", "list_select");
		            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
		            pager.addHiddenInputs("partner_name_like", "${af.map.partner_name_like}");
		            pager.addHiddenInputs("partner_type", "${af.map.partner_type}");
		            pager.addHiddenInputs("is_del", "${af.map.is_del}");
		            document.write(pager.toString());
		            </script></td>
          </tr>
        </table>
      </form>
    </div>
  </c:if>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
});	
if (typeof (window.opener) == 'undefined') window.opener = window.dialogArguments;

function sel(partner_id) {
	window.returnValue = partner_id;
	if (window.opener && window.opener != null) window.opener.returnValue = partner_id;
	window.close();
}
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>