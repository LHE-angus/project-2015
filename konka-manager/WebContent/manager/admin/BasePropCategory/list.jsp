<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
  <html-el:form action="/admin/BasePropCategory">
    <html-el:hidden property="method" value="list" />
    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
    <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
        <td width="50%" nowrap="nowrap"><strong class="fb">&nbsp;&nbsp;&nbsp;&nbsp;属性类别：</strong>
          <html-el:text property="category_name_like" styleId="category_name_like"  maxlength="20" size="20" />
          &nbsp;&nbsp;<strong class="fb">产品类别：</strong>
          <html-el:select property="cls_id" styleId="cls_id" style="width:150px;" onchange="showCagegory();">
            <c:forEach var="cur" items="${basePdClazzList}">
              <html-el:option value="${cur.cls_id}" styleId="${cur.full_name}_${cur.is_leaf}" >${fn:escapeXml(cur.tree_name)}</html-el:option>
            </c:forEach>
          </html-el:select>
          &nbsp;&nbsp;
       <input class="but1" type="submit" name="Submit" value="搜索" />
       </td>
      </tr>
    </table>
  </html-el:form>
 </div>
  <div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>
			<logic-el:match name="popedom" value="+1+">  
		    <input name="button" type="button"  class="but2" value=" 新增 " onclick="location.href='BasePropCategory.do?method=add&mod_id=${af.map.mod_id}';" />
		    </logic-el:match>
		    <logic-el:match name="popedom" value="+4+">
		    <input class="but3" type="submit" name="Submit3" value="回收站" onclick="confirmDeleteAll(document.getElementById('listForm'));" />
		    </logic-el:match> 
		 </td>
	    </tr>
	  </table>  
  </div>
<div class="rtabcont1">
<form id="listForm" name="listForm" method="post" action="BasePropCategory.do?method=delete">
 <input type="hidden" name="method" id="method" value="delete" />
 <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
   <tr class="tabtt1">
    <c:if test="${is_division_or_admin}">
      <td width="30" align="center"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
     </c:if>
     <c:if test="${!is_division_or_admin}">
      <td width="30" align="center" nowrap="nowrap">序号</td>
     </c:if>
      <td nowrap="nowrap">属性类别</td>
      <td nowrap="nowrap">产品类别</td>
      <td width="120" align="center" >操作</td>
    </tr>
    <tbody>
    <c:forEach var="cur" items="${entityList}" varStatus="vs">
      <tr>
        <c:if test="${is_division_or_admin}">
          <td align="center" nowrap="nowrap"><input name="pks" type="checkbox" id="pks" value="${cur.category_id}" /></td>
        </c:if>
        <c:if test="${! is_division_or_admin}">
          <td nowrap="nowrap" align="center">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
        </c:if>
        <td align="left">${cur.category_name}</td>
        <td align="left">${fn:escapeXml(cur.map.cls_full_name)}</td>
        <td align="center">
        <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'BasePropCategory.do', 'view','category_id=${cur.category_id}&'+$('#bottomPageForm').serialize())">查看</span>
        |
        <logic-el:match name="popedom" value="+2+"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'BasePropCategory.do', 'edit','category_id=${cur.category_id}&'+$('#bottomPageForm').serialize())">修改</span></logic-el:match>
		<logic-el:notMatch name="popedom" value="+2+"><span style="color:#ccc;" class="fblue" >修改</span></logic-el:notMatch>
		|
		<logic-el:match name="popedom" value="+4+"><span style="cursor:pointer;" class="fblue" onclick="confirmDelete(null, 'BasePropCategory.do','category_id=${cur.category_id}&'+$('#bottomPageForm').serialize())">删除</span></logic-el:match>
		<logic-el:notMatch name="popedom" value="+4+"><span style="color:#ccc;" class="fblue" >删除</span></logic-el:notMatch></td>
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
      </tr>
    </c:forEach>
    </tbody>
 </table>
</form>
  <form id="bottomPageForm" name="bottomPageForm" method="post" action="BasePropCategory.do">
   <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
          <td height="40"><div style="text-align:right; padding-right:5px;"> 
          <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
          <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("tree_param", "${tree_param}");
            pager.addHiddenInputs("category_name_like", "${fn:escapeXml(af.map.category_name_like)}");
            pager.addHiddenInputs("cls_id", "${af.map.cls_id}");
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
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#span_help").click(function(){
        $("#cvtooltipClose").cvtooltip({
            panel: "#body_oarcont",
            direction: 1,                
            width: 420,
            left: 320,
            top: 5,
            speed: 500,
            delay: 10000
        });
    });	
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
