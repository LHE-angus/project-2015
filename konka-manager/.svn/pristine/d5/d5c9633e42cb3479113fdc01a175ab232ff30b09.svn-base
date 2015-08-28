<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<c:if test="${empty is_end }">
<meta http-equiv="refresh" content="1;URL=KonkaR3Backmoney.do?method=step&amp;mod_id=${af.map.mod_id}&amp;year=${af.map.year}&amp;file_save_path=${file_save_path}&amp;file_save_this=${file_save_this}&amp;x=${x}&amp;y=${y}" />
</c:if>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body >
<div class="oarcont">
  <div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <table class="rtable2" width="100%" border="0" cellspacing="1" cellpadding="0">
      <c:if test="${empty is_end }">
      <tr>
        <c:if test="${z ne null }">
          <td nowrap="nowrap"><span style="color:red">数据正在导入中,请耐心等待，不要进行其他操作</span>  Excel表总记录是${z}条，当前导入了${sum}条，其中新增${y }条，更新${x }条</td>
        </c:if>
      </tr>
      </c:if>
       <c:if test="${not empty is_end }">
          <td nowrap="nowrap">数据导入完成 ，Excel表总记录是${z}条，本次新增${y }条，更新${x }条&nbsp;&nbsp;<a href="KonkaR3Backmoney.do?method=list&amp;mod_id=${af.map.mod_id}"><font color="red" size="+1">返回列表</font> </a></td>
       </c:if>
      <c:if test="${ error ne null}">
        <tr>
          <td  nowrap="nowrap">${error}</td>
        </tr>
      </c:if>
    </table>
  </div>
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
