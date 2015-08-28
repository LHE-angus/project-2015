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
<title>仓库信息</title>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oartop">
  <table width="400" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
      <td>当前位置：${naviString}</td>
    </tr>
  </table>
</div>
<html-el:form action="/admin/AgentsList">
  <html-el:hidden property="method" value="jxsShopList" />
  <html-el:hidden property="mod_id" styleId="mod_id" />
  <html-el:hidden property="r3_id" styleId="r3_id" />
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
      <tr>
        <td height="36" align="left" style="padding-left:5px;">&nbsp;<strong class="fb">客户：</strong>
          <html-el:text property="partner_name_like" styleClass="webinput" styleId="partner_name_like" maxlength="40"/>
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
  <input name="input" type="button" id="gotoAdd" class="bgButtonAdd" value="新 增" onclick="location.href='AgentsList.do?method=addJxsShop&r3_id=${af.map.r3_id}&mod_id=${af.map.mod_id}'" />
</div>
<div class="rtabcont1">
  <div style="overflow-x:auto;">
  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
    <tr class="tabtt1">
      <td width="5%" align="center" nowrap="nowrap">序号</td>
      <td width="5%" align="center" nowrap="nowrap">名称</td>
      <td width="8%" align="center" nowrap="nowrap">编码</td>
      <td width="8%" align="center" nowrap="nowrap">网点级别</td>
      <td width="8%" align="center" nowrap="nowrap">上级网点</td>
      <td align="center" nowrap="nowrap">地址</td>
      <td width="6%" align="center" nowrap="nowrap">联系人姓名</td>
      <td width="8%" align="center" nowrap="nowrap">电话</td>
      <td width="5%" align="center" nowrap="nowrap">停用</td>
      <td width="5%" align="center" nowrap="nowrap">账户</td>
      <td width="6%" align="center" nowrap="nowrap">添加时间</td>
      <td width="8%" align="center" nowrap="nowrap">操作</td>
    </tr>
    <c:forEach items="${entityList}" var="cur" varStatus="vs">
      <tr>
        <td nowrap="nowrap" align="center">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
        <td nowrap="nowrap" align="left">${cur.partner_name}</td>
        <td nowrap="nowrap" align="left"><a style="color:#00F;text-decoration:underline;" href="AgentsList.do?method=view&r3_id=${af.map.r3_id}&partner_id=${cur.partner_id}&mod_id=${af.map.mod_id}">${cur.partner_sn}</a></td>
        <td nowrap="nowrap" align="left">${cur.map.j_level}级网点</td>
        <td nowrap="nowrap" align="left">${cur.map.par_name}</td>
        <td nowrap="nowrap" align="left">${cur.partner_addr}</td>
        <td nowrap="nowrap" align="left">${cur.link_name}</td>
        <td nowrap="nowrap" align="left">${cur.link_mobile}</td>
        <td nowrap="nowrap" align="center"><c:if test="${cur.is_del eq 0}"><span style="color:green;">否</span></c:if>
          <c:if test="${cur.is_del eq 1}"><span style="color:#f00;">是</span></c:if></td>
        <td nowrap="nowrap" align="center">${empty cur.partner_c_id ? '无' : '有'}</td>
        <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" /></td>
        <td align="center" nowrap="nowrap"><c:if test="${cur.is_del eq 0}"> <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'AgentsList.do', 'edit','&mod_id=${af.map.mod_id}&partner_id=${cur.partner_id}&r3_id=${af.map.r3_id}&'+$('#bottomPageForm').serialize())">修改</span> | <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('您确定停用吗？', 'AgentsList.do', 'delete','&partner_id=${cur.partner_id}&r3_id=${af.map.r3_id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">停用</span> </c:if>
          <c:if test="${cur.is_del eq 1}"> <span class="fblue" style="color:#ccc;">修改</span> <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('您确定启用吗？', 'AgentsList.do', 'reStart','&mod_id=${af.map.mod_id}&r3_id=${af.map.r3_id}&partner_id=${cur.partner_id}&'+$('#bottomPageForm').serialize())">启用</span> </c:if></td>
      </tr>
    </c:forEach>
     <c:forEach begin="${fn:length(entityList)}" end="${af.map.pager.pageSize - 1}">
        <tr>
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
  </table>
  </div>
  <c:if test="${not empty entityList}">
    <div class="rtabcont3">
      <form id="bottomPageForm" name="bottomPageForm" method="post" action="AgentsList.do">
        <table width="100%" border="0" align="center" cellspacing="0" cellpadding="0">
          <tr>
            <td height="40" align="center"><script type="text/javascript">
		            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		            pager.addHiddenInputs("method", "jxsShopList");
		            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
		            pager.addHiddenInputs("partner_name_like", "${af.map.partner_name_like}");
		            pager.addHiddenInputs("is_del", "${af.map.is_del}");
		            pager.addHiddenInputs("r3_id", "${af.map.r3_id}");
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
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>