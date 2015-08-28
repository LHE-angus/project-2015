<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
</head>
<body>
    <div>
   <table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="rtable2">
					<tr class="tabtt1">
						<td width="5%" nowrap="nowrap" align="center">序号</td>
						<td width="10%" nowrap="nowrap" align="center">类别名称</td>
						<td width="10%" nowrap="nowrap" align="center">类别取值</td>
						<td width="10%" nowrap="nowrap" align="center">父类别</td>
						<td width="10%" nowrap="nowrap" align="center">级别</td>
						<td width="10%" nowrap="nowrap" align="center">是否删除</td>
						<td width="10%" nowrap="nowrap" align="center">是否锁定</td>
						<td width="10%" nowrap="nowrap" align="center">添加人</td>
						<td width="10%" nowrap="nowrap" align="center">添加时间</td>
						<td width="10%" nowrap="nowrap" align="center">更新人</td>
						<td width="10%" nowrap="nowrap" align="center">备注</td>
					    <td nowrap="nowrap" width="150px;" align="center">附件</td>
					</tr>
					<c:forEach items="${allList}" var="cur" varStatus="vs">
						<tr>
							<td align="center" nowrap="nowrap">${vs.count}</td>
							<td align="left" nowrap="nowrap">
							<a class="fblue" href="javascript:doNeedMethod(null, 'KonkaBaseTypeData.do', 'list', '_par_type_id=${cur.type_id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize());">${cur.type_name}</a>
							</td>
							<td align="left" nowrap="nowrap">${cur.field1}</td>
							<td align="right" nowrap="nowrap">${cur.map.par_type_name}</td>
							<td align="left" nowrap="nowrap">${cur.field2}</td>
							<td align="left" nowrap="nowrap">
							<c:if test="${cur.is_del eq 0}">正常</c:if>
							<c:if test="${cur.is_del eq 1}">已删除</c:if>
							</td>
							<td align="left" nowrap="nowrap">
							<c:if test="${cur.is_lock eq 0}">正常</c:if>
							<c:if test="${cur.is_lock eq 1}">已锁定</c:if>
							</td>
							<td align="left" nowrap="nowrap">${cur.map.add_user}</td>
                            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
							<td align="right" nowrap="nowrap">${cur.map.update_user}</td>
							<td align="left" nowrap="nowrap">${cur.memo}</td>
							<td>
					           <c:if test="${not empty cur.map.fj_paths}">
						          <c:set var="fj_paths" value="${fn:split(cur.map.fj_paths,',')}" />
						          <c:forEach items="${fj_paths}" var="fj_path" varStatus="vs1">
						          	      <td><a href="http://qdgl.konka.com/${fj_path}" target="_blank">&nbsp;附件${vs1.count}&nbsp;</a></td>
						          </c:forEach>
					          </c:if>
					          </td>
						</tr>
					</c:forEach>
				</table>
    </div>
</body>
</html>
