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
</head>
<body>
<div class="oarcont" id="body_oarcont">
<div class="oartop">
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
      <td>当前位置：${naviString}</td>
      <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
    </tr>
  </table>
</div>
<div class="rtabcont1">
  <html-el:form action="/admin/PeShopCategory">
    <html-el:hidden property="method" value="list" />
    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
    <html-el:hidden property="tree_param" value="${tree_param}" />
    <html-el:hidden property="category_pid" value="${af.map.category_pid}" />
    <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
      <tr>
        <td width="80%" nowrap="nowrap">
         &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">  网点类别名称：</strong>
          <html-el:text property="category_name_like" maxlength="40" style="width:90px;" />
          &nbsp;&nbsp;
        <c:if test="${not empty konkaDeptList}">     
          <strong class="fb">事业部：</strong>
           <html-el:select property="dept_id" styleId="dept_id" style="width:180px;">
                       <html-el:option value="">请选择...</html-el:option>
                       <c:forEach var="cur" items="${konkaDeptList}">
                            <html-el:option value="${cur.dept_id}">${fn:escapeXml(cur.dept_name)}</html-el:option>
                       </c:forEach>
                </html-el:select>
           &nbsp;&nbsp;</c:if>
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
			    <input class="but2" type="button" name="Submit2" value="新增" onclick="location.href='PeShopCategory.do?method=add&mod_id=${af.map.mod_id}&category_pid=${af.map.category_pid}&dept_id=${af.map.dept_id}';" />
			    </logic-el:match>
			    <logic-el:match name="popedom" value="+4+">
			    <input class="but3" type="button" name="Submit3" value="回收站" onclick="confirmDeleteAll(document.getElementById('listForm'));" />
			    </logic-el:match>
			 </td>
		   </tr>
   </table>
  </div>
  <div class="rtabcont1">
    <form id="listForm" name="listForm" method="post" action="PeShopCategory.do?method=delete">
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <input type="hidden" name="category_pid" id="category_pid" value="${af.map.category_pid}" />
      <c:if test="${af.map.category_pid ne 0}">
      <html-el:hidden property="dept_id" value="${af.map.dept_id}" />
      </c:if>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
        <c:if test="${is_division}">
          <td width="30" align="center"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
         </c:if>
          <c:if test="${!is_division}">
          <td width="30" align="center" nowrap="nowrap">序号</td>
         </c:if>
          <td nowrap="nowrap">网点类别名称</td>
          <td width="15%" nowrap="nowrap">事业部</td>
          <td width="15%" align="center">添加时间</td>
          <td width="15%" align="center">操作</td>
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
             <tr>
              <c:if test="${is_division}">
               <td align="center" nowrap="nowrap">
                 <c:if test="${cur.is_lock eq 1 }"><input name="pks" type="checkbox" id="pks" value="${cur.category_id}" disabled="disabled"/></c:if>
                 <c:if test="${cur.is_lock ne 1 }"><input name="pks" type="checkbox" id="pks" value="${cur.category_id}" /></c:if>
                </td>
              </c:if>
                <c:if test="${! is_division}">
              <td nowrap="nowrap" align="center">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
                </c:if>
               <td align="left">
               <div>${fn:escapeXml(cur.category_name)}</div>
               <c:if test="${cur.category_pid ne 0 }">
               <div style="color:green" style="padding-left:20px;">[父类别]</div>&nbsp;&nbsp;${fn:escapeXml(category_pName)}
               </c:if>
               <c:if test="${not empty cur.peShopCategoryList}">
                <div style="color:blue">&nbsp;&nbsp;[子类别]</div>
                <c:forEach var="_cur" items="${cur.peShopCategoryList}">
                <div style="padding-left:20px;">${fn:escapeXml(_cur.category_name)}</div>
                </c:forEach>
                </c:if>
               </td>
               <td align="left"><font class="blue12px">${fn:escapeXml(cur.map.dept_name)}</font></td>
              <td align="center"><font class="blue12px">
                <fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss" />
                </font></td>
              <td align="center" nowrap="nowrap">
                  <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'PeShopCategory.do', 'view','category_id=${cur.category_id}&category_pid=${cur.category_pid}&'+$('#bottomPageForm').serialize())">查看</span>
			      |
                  <logic-el:match name="popedom" value="+2+">
                  <c:if test="${cur.is_lock eq 1}"><span style="color:#CCC;" class="fblue" >修改</span></c:if>
                  <c:if test="${cur.is_lock eq 0}"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'PeShopCategory.do', 'edit','category_id=${cur.category_id}&category_pid=${cur.category_pid}&'+$('#bottomPageForm').serialize())">修改</span></c:if>
                  </logic-el:match>
                  <logic-el:notMatch name="popedom" value="+2+">
                  <span style="color:#CCC;" class="fblue">修改</span>
                  </logic-el:notMatch>
                  |
                  <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'PeShopCategory.do', 'list','category_pid=${cur.category_id}&dept_id=${cur.dept_id}&'+$('#bottomPageForm').serialize())">子类别</span>
                  |
                  <logic-el:match name="popedom" value="+4+">
                  <c:if test="${cur.is_lock eq 1}"><span style="color:#CCC;" class="fblue" >删除</span></c:if>
                  <c:if test="${cur.is_lock eq 0}"><span style="cursor:pointer;" class="fblue" onclick="confirmDelete(null, 'PeShopCategory.do', 'pks=${cur.category_id}&'+$('#bottomPageForm').serialize())">删除</span></c:if>
                  </logic-el:match>
                  <logic-el:notMatch name="popedom" value="+4+">
                  <span style="color:#CCC;" class="fblue" >删除</span>
                  </logic-el:notMatch>
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
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="PeShopCategory.do">
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("tree_param", "${tree_param}");
	            pager.addHiddenInputs("category_name_like", "${af.map.category_name_like}");
	            pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
	            pager.addHiddenInputs("category_pid", "${af.map.category_pid}");
	            document.write(pager.toString());
			   </script></td>
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
   $(document).ready(function() {
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
