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
      <html-el:form action="/spgl/EcHomeFloor">
        <html-el:hidden property="method" value="list" />
        <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
        <html-el:hidden property="data_type_id"/>
        <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
          <tr>
            <td nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">标题</strong>：
              <html-el:text property="title" maxlength="20" size="20" />
               &nbsp; 
            &nbsp;&nbsp;<strong class="fb">&nbsp;&nbsp;&nbsp;&nbsp;信息状态：</strong>
              <html-el:select property="info_state" onchange="this.form.submit()"> 
                <html-el:option value="">全部</html-el:option>
                <html-el:option value="0">未发布</html-el:option>
                <html-el:option value="1">已发布</html-el:option>
              </html-el:select>
              &nbsp;&nbsp; 
              <strong class="fb"> 所属组织：</strong><html-el:select property="dept_id" styleId="dept_id" style="width:120px;">
	          				<html-el:option value="">请选择</html-el:option>
	          				<c:forEach items="${deptList}" var="cur">
	          					<html-el:option value="${cur.id}">${cur.group_name}</html-el:option>
	          				</c:forEach>
	          			</html-el:select> 
              </td>
          </tr>
           <tr>
            <td nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;
            <strong class="fb"> 所属总部/分公司：</strong>
            <html-el:select property="plat_sys" styleId="plat_sys" style="width:120px;">
              <html-el:option value="">请选择...</html-el:option>
              <html-el:option value="0">总部</html-el:option>
              <html-el:option value="1">分公司</html-el:option>
             </html-el:select>	&nbsp;&nbsp;<strong class="fb">添加时间：</strong>       
		   <html-el:text property="add_time_start" styleId="add_time_start"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
				至
				<html-el:text property="add_time_end" styleId="add_time_end"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />&nbsp;&nbsp;	
          &nbsp;&nbsp;
            
            &nbsp;&nbsp;<html-el:submit styleId="search_btn" styleClass="but1" value="搜索" />
            </td>
            </tr>
        </table>
      </html-el:form>
    <div class="rtabcont1">
    	<%@ include file="/commons/pages/messages.jsp" %>
    </div>
    <html-el:form action="/spgl/EcHomeFloor?method=delete">
      <div class="rtabcont1">
       <logic-el:match name="popedom" value="+4+"> 
        <input type="button" class="but3" name="delete" id="delete" value="回收站" onclick="confirmDeleteAll(this.form);" />
        </logic-el:match>
         <logic-el:match name="popedom" value="+1+"> 
        <input type="button" class="but2" name="add" value="添加 " onclick="location.href='EcHomeFloor.do?method=add&mod_id=${af.map.mod_id}&data_type_id=${af.map.data_type_id}';" />
        </logic-el:match>
        <html-el:hidden property="mod_id" />
        <html-el:hidden property="data_type_id" />
        <html-el:hidden property="method" value="delete" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" align="center" nowrap="nowrap"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
          <td nowrap="nowrap" align="center">标题</td>
          <td width="10%" align="center" nowrap="nowrap">所属系统</td>
          <td width="8%" align="center" nowrap="nowrap">总部/分公司</td>
          <td width="8%" align="center" nowrap="nowrap">所属组织</td>
          <td width="8%" align="center" nowrap="nowrap">状态</td>
          <td width="8%" align="center" nowrap="nowrap">添加时间</td> 
          <td width="8%" align="center" nowrap="nowrap">操作</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <c:set var="disabled" value="" />
          <c:if test="${cur.info_state eq 1}">
            <c:set var="disabled" value='disabled' />
          </c:if>
          <tr>
            <td align="center"><c:if test="${empty disabled}">
                <input name="pks" type="checkbox" id="pks" value="${cur.id}" />
              </c:if>
              <c:if test="${not empty disabled}">
                <input name="pks" type="checkbox" id="pks" value="${cur.id}" disabled="disabled" /> 
              </c:if></td>
            <td align="left">${fn:escapeXml(cur.title)}</td>
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
             <c:if test="${cur.info_state eq 0}">未发布</c:if>
			 <c:if test="${cur.info_state eq 1}">已发布</c:if>
            </td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" /></td> 
            <td align="center" nowrap="nowrap"> 
            <logic-el:match name="popedom" value="+2+"> 
            <a href="EcHomeFloor.do?method=home&id=${cur.id}&mod_id=${af.map.mod_id}">设置</a>
             </logic-el:match>
              <logic-el:notMatch name="popedom" value="+2+"> 
            	 <span style="color: #ccc">设置</span>
            	 </logic-el:notMatch>
             | 
              <logic-el:match name="popedom" value="+2+">
              	<a href="#" onclick="confirmUpdate(null, 'EcHomeFloor.do', 'id=${cur.id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())">修改</a> 
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
              <td>&nbsp;</td>
            </tr>
          </c:forEach>
      </table>
       </div>
    </html-el:form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="../spgl/EcHomeFloor.do">
      <table width="98%" border="0" align="right" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
			pager.addHiddenInputs("info_state", "${af.map.info_state}");  
			pager.addHiddenInputs("mod_id", "${af.map.mod_id}");		
			pager.addHiddenInputs("data_type_id", "${af.map.data_type_id}");		
			pager.addHiddenInputs("title", "${fn:escapeXml(af.map.title)}");
			pager.addHiddenInputs("dept_id", "${af.map.dept_id}");	
			pager.addHiddenInputs("plat_sys", "${af.map.plat_sys}");
			pager.addHiddenInputs("add_time_start", "${af.map.add_time_start}");
			pager.addHiddenInputs("add_time_end", "${af.map.add_time_end}");	
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
	$('a.preview').imgPreview({
		containerID: 'imgPreviewWithStyles',
		srcAttr: 'rel',
		imgCSS: {
			// Limit preview size:
			// height: 200,
			width: 300
		},
		// When container is shown:
		onShow: function(link){
			$('<span>' + link.title.replace("<", "&lt;").replace(">", "&gt;") + '</span>').appendTo(this);
		},
		// When container hides: 
		onHide: function(link){
			$('span', this).remove();
		}
	});
	
});
//]]></script>

<jsp:include page="/__analytics.jsp" />
</body>
</html>
