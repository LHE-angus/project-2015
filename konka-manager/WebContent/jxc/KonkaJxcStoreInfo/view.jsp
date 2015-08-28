<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>基础数据管理&gt; 仓库信息</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
-->
</style>
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;"/> 当前位置：基础数据管理&gt; 仓库信息</div>
  <div class="rtabcont1">
    <html-el:form action="/KonkaJxcStoreInfo.do" enctype="multipart/form-data">
      <html-el:hidden property="id" />
      <html-el:hidden property="add_user_id" />
      <html-el:hidden property="add_dept_id" />
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th colspan="2">仓库信息</th>
        </tr>
        <tr>
          <td width="10%" class="title_item">仓库名称：</td>
          <td>${fn:escapeXml(af.map.store_name)}</td>
        </tr>
        <tr>
          <td class="title_item">仓库地址：</td>
          <td>${fn:escapeXml(af.map.store_addr)}</td>
        </tr>
        <tr>
          <td class="title_item">负责人：</td>
          <td>${fn:escapeXml(af.map.link_man)}</td>
        </tr>
        <tr>
          <td class="title_item">联系电话：</td>
          <td>${af.map.link_tel}</td>
        </tr>
        <tr>
          <td class="title_item">备注：</td>
          <td>${af.map.remark}</td>
        </tr>
        <tr>
          <td colspan="2" align="center"><html-el:button property="back" value="返 回 " onclick="history.back();" styleClass="bgButtonBack"/></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript">//<![CDATA[
	$(document).ready(function(){
	});
//]]></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
