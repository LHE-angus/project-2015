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
<title>订单记录</title>
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
<html-el:form action="/manager/JBaseType.do">
  <html-el:hidden property="method" value="list" />
  <html-el:hidden property="mod_id" styleId="mod_id" />
  <html-el:hidden property="par_id" styleId="par_id" value="${af.map.par_id}" />
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
      <tr>
        <td height="36" align="left" style="padding-left:5px;">&nbsp;<strong class="fb">
          <c:if test="${af.map.par_id eq 10001}">商品类型： </c:if>
          <c:if test="${af.map.par_id eq 10002}">商品单位： </c:if>
          </strong>
          <html-el:text property="type_name_like" styleClass="webinput" styleId="type_name_like" maxlength="40"/>
          &nbsp;
          <input name="button" type="submit" class="bgSearch" id="button" value="搜 索" />
          <!-- &nbsp;<span id="searchTip" class="jxcTip">默认不显示数据，点击搜索显示数据</span> --></td>
      </tr>
    </table>
  </div>
</html-el:form>
<div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
  <input name="input" type="button" id="gotoAdd" class="bgButtonAdd" value="新 增" onclick="location.href='${ctx}/customer/manager/JBaseType.do?method=add&mod_id=${af.map.mod_id}&par_id=${af.map.par_id}'" />
</div>
<div class="rtabcont1">
<div style="overflow-x: auto;">
  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
    <tr>
      <th align="center" width="5%">行号</th>
      <th align="center" width="12%"><c:if test="${af.map.par_id eq 10001}">商品类型 </c:if>
        <c:if test="${af.map.par_id eq 10002}">商品单位 </c:if></th>
      <th align="center" width=""><c:if test="${af.map.par_id eq 10001}">商品类型描述 </c:if>
        <c:if test="${af.map.par_id eq 10002}">商品单位描述 </c:if></th>
      <th align="center" width="10%">排序值</th>
      <th align="center" width="10%">操作</th>
    </tr>
    <c:forEach items="${entityList}" var="cur" varStatus="vs">
      <tr>
        <td align="center" bgcolor="#fff2dc">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
        <td align="left">${cur.type_name}</td>
        <td align="left">${cur.type_desc}</td>
        <td align="right">${cur.order_value}</td>
        <td align="center" nowrap="nowrap"><span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'JBaseType.do', 'edit','&mod_id=${af.map.mod_id}&type_id=${cur.type_id}&par_id=${cur.par_id}&'+$('#bottomPageForm').serialize())">修改</span>| 
        <span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod('是否删除！', 'JBaseType.do', 'delete','&mod_id=${af.map.mod_id}&type_id=${cur.type_id}&par_id=${cur.par_id}&'+$('#bottomPageForm').serialize())">删除</span></td>
      </tr>
      <c:if test="${vs.last}">
        <c:forEach begin="1" end="${9 - vs.index}">
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </c:forEach>
      </c:if>
    </c:forEach>
    <c:if test="${empty entityList}">
      <c:forEach begin="0" end="9">
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </c:forEach>
    </c:if>
  </table>
  </div>
  <div class="rtabcont3">
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="JBaseType.do">
      <script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
      <table width="100%" border="0" align="center" cellspacing="0" cellpadding="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript">
		            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		            pager.addHiddenInputs("method", "list");
		            pager.addHiddenInputs("type_name_like", "${af.map.type_name_like}");
		            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
		            pager.addHiddenInputs("par_id", "${af.map.par_id}");
		            document.write(pager.toString());
		            </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/customer/script/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>