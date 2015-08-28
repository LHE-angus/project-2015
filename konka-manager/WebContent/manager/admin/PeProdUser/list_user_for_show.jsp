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
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
     <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
  </div>
  <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
       <tbody>
          <tr class="tabtt1">
              <td width="30"><font class="blue">序号</font></td>
              <td nowrap="nowrap"><font class="blue">姓名</font></td>
              <td width="25%" nowrap="nowrap"><font class="blue">部门</font>(点击可以直接按部门检索)</td>
              <td nowrap="nowrap" width="10%" nowrap="nowrap"><font class="blue">手机/电话</font></td>
              <td width="15%" nowrap="nowrap"><font class="blue">用户名</font></td>
          </tr>
         <c:forEach var="cur" items="${entityList}" varStatus="vs">
           <tr>
                <td align="center">${vs.count}</td>
                <td align="left" valign="middle"><font class="blue1"><a href="PeProdUser.do?method=view&user_id=${cur.id}&mod_id=${af.map.mod_id}&tree_param=${tree_param}"><font class="blue12px">${fn:escapeXml(cur.real_name)}</font></a></font></td>
                <td align="left"><span onclick="$('#dept_id').val('${cur.dept_id}');$('#af').eq(0).submit();" style="cursor:pointer;"><c:out value="${fn:substring(cur.map.full_dept_name, 2,-1)}" /></span></td>
                <td align="center"><c:out value="${not empty cur.link_phone ? cur.link_phone : (not empty cur.link_tel ? cur.link_tel : '未填写')}" /></td>
                <td align="left"><c:out value="${cur.user_name}" /><c:if test="${cur.user_type eq 0}">&nbsp;&nbsp;<span style="color:#F00;">[管理员]</span></c:if></td>
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
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
