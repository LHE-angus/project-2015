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
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/jf/JfRule">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td><strong class="fb">分公司：</strong><html-el:select property="dept_id" styleId="dept_id">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${sybDeptInfoList}">
                <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
              </c:forEach>
            </html-el:select></td>
          <td><strong class="fb">产品型号：</strong><html-el:text property="pd_id_like" size="20" maxlength="20" styleId="pd_id_like" /></td>
          <td><strong class="fb">积分类型： </strong>
            <html-el:select property="jf_type" styleId="jf_type">
              <html-el:option value="">请选择</html-el:option>
              <html-el:option value="1">按数量固定返积分</html-el:option>
              <html-el:option value="2">按金额比例返积分</html-el:option>
            </html-el:select></td>
          <td><strong class="fb">有效期：</strong><html-el:select property="jf_avl_type" styleId="jf_avl_type">
              <html-el:option value="">请选择</html-el:option>
              <html-el:option value="1">长期有效</html-el:option>
              <html-el:option value="2">时间段有效</html-el:option>
            </html-el:select></td>
          <td><html-el:submit styleClass="but1" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
      <%@ include file="/commons/pages/messages.jsp" %>
      <input type="button" class="but2" onclick="location.href='JfRule.do?method=add&mod_id=${af.map.mod_id}';" value="添加" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" align="center" nowrap="nowrap">序号</td>
          <td width="15%" align="center" nowrap="nowrap">分公司</td>
          <td align="center" nowrap="nowrap">产品型号</td>
          <td width="15%" align="center" nowrap="nowrap">积分类型</td>
          <td width="10%" align="center" nowrap="nowrap">积分值/比例（%）</td>
          <td width="15%" align="center" nowrap="nowrap">有效期</td>
          <td nowrap="nowrap" align="center" width="10%">操作</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
          	<td align="center"> ${ (af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
          	<td>${cur.map.dept_name}</td>
          	<td align="center">${cur.pd_id}</td>
          	<td align="center"><c:choose>
          			<c:when test="${cur.jf_type eq 1}">按数量固定返积分</c:when>
          			<c:when test="${cur.jf_type eq 2}">按金额比例返积分</c:when>
          		</c:choose></td>
          	<td align="right">${cur.jf_value}</td>
          	<td align="center"><c:choose> 
          		<c:when test="${cur.jf_avl_type eq 1}">长期有效</c:when>
          		<c:when test="${cur.jf_avl_type eq 2}"><fmt:formatDate value="${cur.jf_avl_start}" pattern="yyyy-MM-dd"/> 至 <fmt:formatDate value="${cur.jf_avl_end}" pattern="yyyy-MM-dd"/></c:when>
          	    </c:choose></td>
            <td align="center" nowrap="nowrap"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'JfRule.do','edit' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">修改</span>
               <span style="cursor:pointer;" class="fblue" onclick="confirmDelete('确定删除吗?', 'JfRule.do', 'id=${cur.id}&' + $('#bottomPageForm').serialize())">删除</span></td>
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
    <br />
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="JfRule.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			pager.addHiddenInputs("dept_id", "${fn:escapeXml(af.map.dept_id)}");	
			pager.addHiddenInputs("pd_id_like", "${fn:escapeXml(af.map.pd_id_like)}");	
			pager.addHiddenInputs("jf_type", "${fn:escapeXml(af.map.jf_type)}");	
			pager.addHiddenInputs("jf_avl_type", "${fn:escapeXml(af.map.jf_avl_type)}");	
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

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>