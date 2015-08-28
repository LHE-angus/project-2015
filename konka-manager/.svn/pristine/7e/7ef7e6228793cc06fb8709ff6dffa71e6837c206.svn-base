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
    <html-el:form action="/zmd/KonkaXxDistEmployee">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td width="15%" nowrap="nowrap"><strong class="fb">姓名：</strong>
            <html-el:text property="real_name_like" styleId="real_name_like" size="10" maxlength="20"></html-el:text>
          </td>
          <td width="15%" nowrap="nowrap"><strong class="fb">联系手机：</strong>
            <html-el:text property="mobile_phone_like" styleId="mobile_phone_like" size="10" maxlength="20"></html-el:text>
          </td>
          <td width="15%" nowrap="nowrap"><strong class="fb">是否删除：</strong>
            <html-el:select property="is_del" styleId="is_del" style="width:90px;">
	        	<html-el:option value="">=请选择=</html-el:option>
	        	<html-el:option value="0">未删除</html-el:option>
	        	<html-el:option value="1">已删除</html-el:option>
	        </html-el:select>
          </td>
          <td align="left">&nbsp;&nbsp;<input class="but1" type="submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp"%>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><input class="but2" type="submit" name="Submit2" value="新增" onclick="location.href='KonkaXxDistEmployee.do?method=add&mod_id=${af.map.mod_id}&tree_param=${tree_param}';" /></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td nowrap="nowrap" align="center" width="5%">序号</td>
        <td nowrap="nowrap" align="center" width="10%">姓名</td>
        <td nowrap="nowrap" align="center" width="10%">联系电话</td>
        <td nowrap="nowrap" align="center" width="10%">联系手机</td>
        <td nowrap="nowrap" align="center" width="20%">身份证号码</td>
        <td nowrap="nowrap" align="center" width="10%">生日</td>
        <td nowrap="nowrap" align="center" width="10%">添加人</td>
        <td nowrap="nowrap" align="center" width="10%">添加时间</td>
        <td nowrap="nowrap" align="center" width="8%">是否删除</td>
        <td nowrap="nowrap" align="center" width="7%">操作</td>
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
            <td align="left">${fn:escapeXml(cur.real_name)}</td>
            <td align="left">${fn:escapeXml(cur.link_phone)}</td>
            <td align="left">${fn:escapeXml(cur.mobile_phone)}</td>
            <td align="left">${fn:escapeXml(cur.idcard)}</td>
            <td align="center"><fmt:formatDate value="${cur.birthday}" pattern="yyyy-MM-dd" /></td>
            <td align="center" nowrap="nowrap">${fn:escapeXml(cur.map.add_user_name)}</td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
            <td align="center" nowrap="nowrap">
            	<c:choose>
            		<c:when test="${cur.is_del eq 0}"><span style="color:green;">未删除</span></c:when>
            		<c:when test="${cur.is_del eq 1}"><span style="color:#f00;">已删除</span></c:when>
            		<c:otherwise>未知错误</c:otherwise>
            	</c:choose>
            </td>
            <td align="center" nowrap="nowrap"><span style="cursor:pointer;" class="fblue" onclick="confirmUpdate(null, 'KonkaXxDistEmployee.do', 'dist_employee_id=${cur.dist_employee_id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())">修改</span>
	              		|&nbsp;<span style="cursor:pointer;" class="fblue" onclick="confirmDelete('确定删除？', 'KonkaXxDistEmployee.do', 'dist_employee_id=${cur.dist_employee_id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">删除</span></td>
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
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxDistEmployee.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("real_name_like", "${af.map.real_name_like}");
				pager.addHiddenInputs("is_del", "${af.map.is_del}");
				pager.addHiddenInputs("mobile_phone_like", "${af.map.mobile_phone_like}");
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

});
//]]>
</script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
