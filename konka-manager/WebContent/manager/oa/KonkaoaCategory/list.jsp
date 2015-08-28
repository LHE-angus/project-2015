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
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div style="padding-left: 5px;padding-top: 3px;">
  	<input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" /></div>
  <div class="rtabcont1">
  	<%@ include file="/commons/pages/messages.jsp" %>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
        <tr class="tabtt1">
          <td width="5%" align="center">序号</td>
          <td width="16%" nowrap="nowrap" align="center">模板名称</td>
          <td nowrap="nowrap" align="center">子项名称</td>
          <td width="10%" nowrap="nowrap" align="center">排序值</td>
          <td width="10%" nowrap="nowrap" align="center">操作</td>
        </tr>    
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="center" valign="top">${vs.count}</td>
              <td align="left" valign="top" >${fn:escapeXml(af.map.module_name)}</td>
              <td align="left" valign="top" >${cur.c_name}</td>
              <td align="right" valign="top" >${cur.order_value}</td>
              <td align="center" valign="top">
                <span class="fblue" style="cursor: pointer;" onclick="confirmDelete(null, 'KonkaoaCategory.do', 'c_index=${cur.c_index}&mod_id=${af.map.mod_id}&module_id=${cur.module_id}&' + $('#bottomPageForm').serialize())">删除</span>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
