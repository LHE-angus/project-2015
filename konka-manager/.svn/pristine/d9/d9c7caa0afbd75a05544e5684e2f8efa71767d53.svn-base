<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
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
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align: middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/JBasePartnerForFgsKh">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td>
          <c:if test="${not empty deptList}">
           <strong class="fb">分公司：</strong>
           <html-el:select property="dept_id" styleId="dept_id">
           	<html-el:option value="">请选择...</html-el:option>
           	<c:forEach items="${deptList}" var="cur">
           		<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
           	</c:forEach>
           </html-el:select>
           &nbsp;&nbsp;
           </c:if>
            <strong class="fb">客户名称：</strong>
            <html-el:text property="customer_name_like" styleId="customer_name_like" size="16" maxlength="20"></html-el:text>
            &nbsp;&nbsp; <strong class="fb">客户R3编码：</strong>
            <html-el:text property="r3_code_like" styleId="r3_code_like" size="16" maxlength="20"></html-el:text>
           </td>
        </tr>
        <tr>
          <td width="15"></td>
          <td>
          <strong class="fb">供应商名称：</strong>
            <html-el:text property="partner_name_like" styleId="partner_name_like" size="16" maxlength="20"></html-el:text>
            &nbsp;&nbsp; 
           <strong class="fb">状态：</strong>
           	<html-el:select property="is_del" styleId="is_del">
           		<html-el:option value="0">未停用</html-el:option>
           		<html-el:option value="1">已停用</html-el:option>
           	</html-el:select>
           &nbsp;&nbsp;
            <input class="but1" type="submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td nowrap="nowrap" align="center" width="5%">序号</td>
        <td nowrap="nowrap" align="center" width="8%">分公司</td>
        <td nowrap="nowrap" align="center">供应商</td>
        <td nowrap="nowrap" align="center" width="8%">供应商编号</td>
        <td nowrap="nowrap" align="center" width="6%">类型</td>
        <td nowrap="nowrap" align="center" width="12%">所属客户</td>
        <td nowrap="nowrap" align="center" width="8%">客户编码</td>
        <td nowrap="nowrap" align="center" width="8%">联系人</td>
        <td nowrap="nowrap" align="center" width="8%">联系方式</td>
        <td nowrap="nowrap" align="center" width="8%">添加时间</td>
        <td nowrap="nowrap" align="center" width="8%">状态</td>
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.map.dept_name)}</td>
            <td align="left">${fn:escapeXml(cur.map.partner_name)}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.map.partner_sn)}</td>
            <td align="center" nowrap="nowrap"><c:choose>
            	<c:when test="${cur.is_del eq 0}">个人</c:when>
            	<c:otherwise>组织/单位</c:otherwise>
            </c:choose>
            </td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.map.customer_name)}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.map.r3_code)}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.map.link_name)}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.map.link_mobile)}
            	<c:if test="${empty cur.map.link_mobile}">${cur.map.link_tel}</c:if>
            </td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.map.add_date}" pattern="yyyy-MM-dd" /></td>
            <td align="center" nowrap="nowrap"><c:choose>
            	<c:when test="${cur.map.is_del eq 0}">未停用</c:when>
            	<c:otherwise>已停用</c:otherwise>
            </c:choose></td>
          </tr>
        </c:forEach>
        <c:forEach begin="${fn:length(entityList)}" end="${af.map.pager.pageSize - 1}" step="1">
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
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="JBasePartnerForFgsKh.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("tree_param", "${tree_param}");
				pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
				pager.addHiddenInputs("partner_name_like", "${af.map.partner_name_like}");
				pager.addHiddenInputs("customer_name_like", "${af.map.customer_name_like}");
				pager.addHiddenInputs("r3_code_like", "${af.map.r3_code_like}");
				pager.addHiddenInputs("is_del", "${af.map.is_del}");
				document.write(pager.toString());
			  </script>
            </div></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}
});
//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
