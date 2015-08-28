<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
#imgPreviewWithStyles {
    background: #757575;
    -moz-border-radius: 5px;
    -webkit-border-radius: 5px;
    padding: 15px;
    z-index: 999;
    border: none;
    max-width: 330px;
}

/* Text below image */
#imgPreviewWithStyles span {
    color: white;
    text-align: center;
    display: block;
    padding: 10px 0 3px 0;
}
</style>
</head>
<body>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
   <div class="rtabcont1">
      <html-el:form action="/spgl/EcNavInfo">
        <html-el:hidden property="method" value="list" />
        <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
        <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
          <tr>
            <td nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">标题</strong>：
              <html-el:text property="title" maxlength="20" size="20" />
               &nbsp; 
            &nbsp;&nbsp;<strong class="fb">&nbsp;&nbsp;&nbsp;&nbsp;信息状态：</strong>
              <html-el:select property="del_mark" onchange="this.form.submit()"> 
                <html-el:option value="">全部</html-el:option>
                <html-el:option value="0">发布中</html-el:option>
                <html-el:option value="1">已关闭</html-el:option>
              </html-el:select>
               &nbsp;&nbsp;<strong class="fb">&nbsp;&nbsp;&nbsp;&nbsp;栏目类型：</strong>
              <html-el:select property="plat_sys" onchange="this.form.submit()"> 
                <html-el:option value="">全部</html-el:option>
                <html-el:option value="0">总部</html-el:option>
                <html-el:option value="1">分公司</html-el:option>
              </html-el:select>
              &nbsp;&nbsp;
              <strong class="fb"> 所属组织：</strong><html-el:select property="dept_id" styleId="dept_id" style="width:120px;">
	          				<html-el:option value="">请选择</html-el:option>
	          				<c:forEach items="${deptList}" var="cur">
	          					<html-el:option value="${cur.id}">${cur.group_name}</html-el:option>
	          				</c:forEach>
	          			</html-el:select>&nbsp;&nbsp; <html-el:submit styleId="search_btn" styleClass="but1" value="搜索" /></td>
          </tr>
        </table>
      </html-el:form>
    <div class="rtabcont1">
    	<%@ include file="/commons/pages/messages.jsp" %>
    </div>
    <html-el:form action="/spgl/EcNavInfo?method=delete">
      <div class="rtabcont1" style="overflow: auto;"> 
       <!--   <input type="button" class="but3" name="delete" id="delete" value="回收站" onclick="confirmDeleteAll(this.form);" />  -->
        <logic-el:match name="popedom" value="+1+"> 
        <input type="button" class="but2" name="add" value="添加 " onclick="location.href='EcNavInfo.do?method=add&mod_id=${af.map.mod_id}';" />
        </logic-el:match>
        <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
        <html-el:hidden property="method" value="delete" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <!--  
          <td width="5%" align="center" nowrap="nowrap"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>  -->
          <td nowrap="nowrap" width="5%" align="center">序号</td>
          <td nowrap="nowrap" align="center">标题</td>
          <td width="8%" align="center" nowrap="nowrap" style="display: none;">链接url</td>
          <td width="10%" align="center" nowrap="nowrap">所属系统</td>
          <td width="8%" align="center" nowrap="nowrap">栏目类型</td>
          <td width="8%" align="center" nowrap="nowrap">所属组织</td>
          <td width="8%" align="center" nowrap="nowrap">状态</td>
          <td width="8%" align="center" nowrap="nowrap">添加时间</td>
          <td width="8%" align="center" nowrap="nowrap">添加人</td>
          <td width="8%" align="center" nowrap="nowrap">更新时间</td>
          <td width="8%" align="center" nowrap="nowrap">操作</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <c:set var="disabled" value="" />
          <c:if test="${cur.del_mark eq 1}">
            <c:set var="disabled" value='disabled' />
          </c:if>
          <tr>
           <!-- 
            <td align="center"><c:if test="${empty disabled}">
                <input name="pks" type="checkbox" id="pks" value="${cur.id}" />
              </c:if>
              <c:if test="${not empty disabled}">
                <input name="pks" type="checkbox" id="pks" value="${cur.id}" disabled="disabled" /> 
              </c:if></td>  -->
            <td align="center">${vs.count}</td>  
            <td align="left">${fn:escapeXml(cur.title)}</td>  
            <td align="left" style="display: none;">${fn:substring(cur.logo_url, 0, 40)}.....</td> 
            <td align="left" nowrap="nowrap"> 
            <c:if test="${cur.own_sys eq 1}">工卡系统</c:if>
			<c:if test="${cur.own_sys eq 2}">触网系统</c:if>
			</td>
            <td align="left" nowrap="nowrap">
             <c:if test="${cur.plat_sys eq 0}">总部</c:if>
			 <c:if test="${cur.plat_sys eq 1}">分公司</c:if>
            </td>
            <td align="left" nowrap="nowrap">
            	${cur.map.group_name}
            </td>
            <td align="left" nowrap="nowrap">
             <c:if test="${cur.del_mark eq 0}">发布中</c:if>
			 <c:if test="${cur.del_mark eq 1}">已关闭</c:if>
            </td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" /></td>
            <td align="left" nowrap="nowrap">
            	${cur.map.add_user_name}
            </td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.update_date}" pattern="yyyy-MM-dd" /></td>
            <td align="center" nowrap="nowrap">
           <!--   <c:if test="${cur.del_mark eq 1}">
              <span style="color:#ccc">修改 | 删除</span>
            </c:if>  -->
                <logic-el:match name="popedom" value="+2+"> 
              	<a href="#" onclick="confirmUpdate(null, 'EcNavInfo.do', 'id=${cur.id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())">修改</a> 
                </logic-el:match>
                <logic-el:notMatch name="popedom" value="+2+"> 
            	 <span style="color: #ccc">修改</span>
            	 </logic-el:notMatch>
            <!--    <a href="#" onclick="confirmDelete(null, 'EcNavInfo.do', 'mod_id=${af.map.mod_id}&id=${cur.id}&pager.requestPage=${af.map.pager.requestPage}')">删除</a>  -->
            </td>
          </tr>
        </c:forEach>
        <c:forEach begin="${fn:length(entityList)}" end="${af.map.pager.pageSize - 1}">
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
    </html-el:form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="../spgl/EcNavInfo.do">
      <table width="98%" border="0" align="right" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
			pager.addHiddenInputs("del_mark", "${af.map.del_mark}");  
			pager.addHiddenInputs("plat_sys", "${af.map.plat_sys}");  
			pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			pager.addHiddenInputs("dept_id", "${af.map.dept_id}");			
			pager.addHiddenInputs("title", "${fn:escapeXml(af.map.title)}");	
            document.write(pager.toString());
        </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.inputable.js"></script>
<script type="text/javascript">//<![CDATA[

$(document).ready(function(){
	
	
});
//]]></script>

<jsp:include page="/__analytics.jsp" />
</body>
</html>
