<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<title>topFrame</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.STYLE3 {
	font-size: 21px;
	font-family: "微软雅黑";
	color: white;
}
-->
</style>
</head>
<body>
<div class="topcont">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td class="topleft"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="logo"><img alt="康佳" src="${ctx}/images/manager/logo.gif" /></td>
            <td class="toptit"><img alt="渠道管理系统" src="${ctx}/images/manager/toptit.gif" /></td>
            <c:if test="${is_fengongsi}">
              <td class="STYLE3" nowrap="nowrap">&nbsp;
                <c:out value="${dept_name}" /></td>
            </c:if>
          </tr>
        </table></td>
      <td class="rtopcont"><img src="${ctx}/images/manager/icon1.gif" />　欢迎您！${userInfo.user_name}&nbsp;
      <a class="fyel" href="${ctx}/manager/admin/Frames.do?method=main" target="mainFrame">系统首页</a>&nbsp;
      <a class="fyel" href="${ctx}/manager/admin/KonkaSxOperLog.do?mod_id=906000" target="mainFrame">操作日志</a>&nbsp;
      <a class="fyel" href="${ctx}/manager/admin/TerminalFeedbackForSelf.do" target="mainFrame">意见反馈</a>&nbsp;
      <a class="fyel" href="${ctx}/manager/admin/Password.do" target="mainFrame">修改密码</a>&nbsp;
      <a class="fyel" href="${ctx}/manager/admin/Frames2.do?method=index" target="_top">新版后台</a>&nbsp;
      <a class="fyel" href="${ctx}/login.do?method=logout" target="_top">[退出]</a></td>
    </tr>
  </table>
  <div class="clear"></div>
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
