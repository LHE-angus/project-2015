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
      <html-el:form action="/spgl/EcSallareaTemplateEdit">
        <html-el:hidden property="method" value="list2" />
        <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
        <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
          <tr>
            <td nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">标题</strong>：
              <html-el:text property="title" maxlength="20" size="20" />
               &nbsp; 
            &nbsp;&nbsp;<strong class="fb">&nbsp;&nbsp;&nbsp;&nbsp;信息状态：</strong>
              <html-el:select property="state" onchange="this.form.submit()"> 
                <html-el:option value="">全部</html-el:option>
                <html-el:option value="0">发布中</html-el:option>
                <html-el:option value="1">未发布</html-el:option>
              </html-el:select>
              &nbsp;&nbsp;<html-el:submit styleId="search_btn" styleClass="but1" value="搜索" /></td>
          </tr>
        </table>
      </html-el:form>
    <div class="rtabcont1">
    	<%@ include file="/commons/pages/messages.jsp" %>
    </div>
    <html-el:form action="/spgl/EcSallareaTemplateEdit?method=delete">
      <div class="rtabcont1">
         <logic-el:match name="popedom" value="+4+"> 
        <input type="button" class="but3" name="delete" id="delete" value="回收站" onclick="confirmDeleteAll(this.form);" />
        </logic-el:match>
        <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
        <html-el:hidden property="method" value="delete" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" align="center" nowrap="nowrap"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
          <td align="center" nowrap="nowrap">模板名称</td>  
          <td width="15%" align="center" nowrap="nowrap">所属组织</td>
          <td width="8%" align="center" nowrap="nowrap">发布状态</td>
          <td width="8%" align="center" nowrap="nowrap">添加时间</td>
          <td width="8%" align="center" nowrap="nowrap">更新时间</td>
          <td width="8%" align="center" nowrap="nowrap">操作</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center">
                <input name="pks" type="checkbox" id="pks" value="${cur.id}" />
             </td>
            <td align="left">${fn:escapeXml(cur.title)}</td>
            <td align="left">${fn:escapeXml(cur.map.group_name)}</td>
            <td align="left" nowrap="nowrap">  
            <c:if test="${cur.state eq 0}">发布</c:if>
			<c:if test="${cur.state eq 1}">未发布</c:if>
			</td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" /></td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.update_date}" pattern="yyyy-MM-dd" /></td>
            <td align="center" nowrap="nowrap">
                 <logic-el:match name="popedom" value="+2+"> 
              	<a href="#" onclick="confirmUpdate(null, 'EcSallareaTemplateEdit.do', 'id=${cur.id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())">修改</a> 
            	</logic-el:match>
            	 <logic-el:notMatch name="popedom" value="+2+"> 
            	 <span style="color: #ccc">修改</span>
            	 </logic-el:notMatch>
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
            </tr>
          </c:forEach>
      </table>
       </div>
    </html-el:form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="../spgl/EcSallareaTemplateEdit.do">
      <table width="98%" border="0" align="right" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list2");
			pager.addHiddenInputs("state", "${af.map.state}");  
			pager.addHiddenInputs("mod_id", "${af.map.mod_id}");			
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
