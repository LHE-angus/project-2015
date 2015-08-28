<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>---</title>
<link href="${ctx}/commons/styles/themes/blue/styles/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div align="center">
  <div align="center" style="width: 99%">
    <div align="left" class="nav">${naviString}</div>
    <br />
    <fieldset>
      <legend>快速搜索</legend>
      <html-el:form action="/admin/ImportDataTypes">
        <html-el:hidden property="method" value="list" />
        <html-el:hidden property="mod_code" value="${af.map.mod_code}" />
 		&nbsp;ID：
        <html-el:text property="id" size="20" maxlength="20" styleId="id" styleClass="webinput" />
 		&nbsp;父ID<br />
顶级为0：
        <html-el:text property="par_id" size="20" maxlength="20" styleId="par_id" styleClass="webinput" />
 		&nbsp;数据类别<br />
暂定：<br />
1：单客户，此种类别去R3表中查相关数据<br />
2：客户群<br />
3：分公司，此种类别去组织架构表中查相关数据<br />
4：分大区
：
        <html-el:text property="data_type" size="20" maxlength="20" styleId="data_type" styleClass="webinput" />
 		&nbsp;类别名称：
        <html-el:text property="type_name" size="20" maxlength="20" styleId="type_name" styleClass="webinput" />
 		&nbsp;排序值：
        <html-el:text property="order_value" size="20" maxlength="20" styleId="order_value" styleClass="webinput" />
        <html-el:submit value="快速搜索" />
      </html-el:form>
    </fieldset>
    <%@ include file="/commons/pages/messages.jsp" %>
    <br/>
    <form id="listForm" name="listForm" method="post" action="ImportDataTypes.do?method=delete">
      <div style="text-align: left">
        <input type="button" name="delete" id="delete" value="删除所选" onclick="confirmDeleteAll(this.form);" />
        <input type="button" name="add" id="add" value="添加" onclick="location.href='ImportDataTypes.do?method=add&mod_code=${af.map.mod_code}';" />
        <input type="hidden" name="method" id="method" value="delete" />
        <input type="hidden" name="mod_code" id="mod_code" value="${af.map.mod_code}" />
      </div>
      <br/>
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="datagrid">
        <tr>
          <th width="5%" nowrap="nowrap"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></th>
          <th nowrap="nowrap">ID</th>	
          <th nowrap="nowrap">父ID<br />
顶级为0</th>	
          <th nowrap="nowrap">数据类别<br />
暂定：<br />
1：单客户，此种类别去R3表中查相关数据<br />
2：客户群<br />
3：分公司，此种类别去组织架构表中查相关数据<br />
4：分大区
</th>	
          <th nowrap="nowrap">类别名称</th>	
          <th nowrap="nowrap">排序值</th>	
          <th width="10%">操作</th>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap"><input name="pks" type="checkbox" id="pks" value="${cur.id}" /></td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.id)}</td>            	
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.par_id)}</td>            	
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.data_type)}</td>            	
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.type_name)}</td>            	
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.order_value)}</td>            	
            <td align="center"><span style="cursor:pointer; margin:0 7px 0 7px;" onclick="confirmUpdate(null, 'ImportDataTypes.do', 'id=${cur.id}&' + $('#bottomPageForm').serialize())">修改</span> <span style="cursor:pointer; margin-left:7px;" onclick="confirmDelete(null, 'ImportDataTypes.do', 'id=${cur.id}&' + $('#bottomPageForm').serialize())">删除</span></td>
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
          </tr>
        </c:forEach>
      </table>
    </form>
    <br />
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="ImportDataTypes.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
			pager.addHiddenInputs("id", "${fn:escapeXml(af.map.id)}");	
			pager.addHiddenInputs("par_id", "${fn:escapeXml(af.map.par_id)}");	
			pager.addHiddenInputs("data_type", "${fn:escapeXml(af.map.data_type)}");	
			pager.addHiddenInputs("type_name", "${fn:escapeXml(af.map.type_name)}");	
			pager.addHiddenInputs("order_value", "${fn:escapeXml(af.map.order_value)}");	
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	// javascript...
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>