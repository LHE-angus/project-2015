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
    <html-el:form action="/admin/KonkaR3DeptStore">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td>
          <c:if test="${!empty is_admin}"> <strong class="fb">分公司：</strong>
            	<html-el:text property="dept_name_like" styleId="dept_name_like" size="16" maxlength="20"></html-el:text>
          </c:if>
           &nbsp;&nbsp;
          <strong class="fb">工厂：</strong>
            <html-el:text property="fac_sn_like" styleId="fac_sn_like" size="16" maxlength="20"></html-el:text>
            &nbsp;&nbsp; <strong class="fb">仓库：</strong>
            <html-el:text property="store_sn_like" styleId="store_sn_like" size="16" maxlength="20"></html-el:text>
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
    <%@ include file="/commons/pages/messages.jsp"%>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>
          <logic-el:match name="popedom" value="+1+">
        	<input type="button" class="but2" name="add" value="新增" onclick="location.href='KonkaR3DeptStore.do?method=add&mod_id=${af.map.mod_id}&is_admin=${is_admin}&tree_param=${tree_param}';" />
          </logic-el:match>	
        </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td nowrap="nowrap" align="center" width="5%">序号</td>
        <td nowrap="nowrap" align="center" width="15%">分公司</td>
        <td nowrap="nowrap"  align="center" width="10%">工厂</td>
        <td nowrap="nowrap"  align="center" width="10%">仓库</td>
        <td nowrap="nowrap"  align="center" >仓库描述</td>
        <td nowrap="nowrap"  align="center" width="8%">状态</td>
        <td nowrap="nowrap"  align="center" width="8%">操作</td>
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
            <td align="left">${fn:escapeXml(cur.dept_name)}</td>
            <td align="left">${fn:escapeXml(cur.fac_sn)}</td>
            <td align="left">${fn:escapeXml(cur.store_sn)}</td>
            <td align="left">${fn:escapeXml(cur.store_desc)}</td>
            <td align="center"><c:choose>
            	<c:when test="${cur.is_del eq 0}">未停用</c:when>
            	<c:otherwise>已停用</c:otherwise>
            </c:choose></td>
            <td align="center" nowrap="nowrap"><span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaR3DeptStore.do', 'edit','id=${cur.id}&is_admin=${is_admin}&'+$('#bottomPageForm').serialize())">修改</span>
            	|<c:if test="${cur.is_del eq 0}"><span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaR3DeptStore.do', 'stopStore','id=${cur.id}&'+$('#bottomPageForm').serialize())">停用</span></c:if>
            	 <c:if test="${cur.is_del ne 0}"><span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaR3DeptStore.do', 'resStore','id=${cur.id}&'+$('#bottomPageForm').serialize())">启用</span></c:if>
            </td>
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
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaR3DeptStore.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("tree_param", "${tree_param}");
				pager.addHiddenInputs("dept_name_like", "${af.map.dept_name_like}");
				pager.addHiddenInputs("fac_sn_like", "${af.map.fac_sn_like}");
				pager.addHiddenInputs("store_sn_like", "${af.map.store_sn_like}");
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
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
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
