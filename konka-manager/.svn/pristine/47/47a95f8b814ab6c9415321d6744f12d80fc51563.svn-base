<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>意见反馈管理</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：意见反馈管理</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/TerminalFeedback" method="post">
      <html-el:hidden property="feed_id" styleId="feed_id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="reply" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table width="80%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">反馈类别：</td>
          <td width="88%" align="left"><c:out value="${entity.map.c_name}" /></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">内容：</td>
          <td width="88%" align="left"><c:out value="${af.map.content}" /></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">提交人：</td>
          <c:if test="${entity.map.subcomp_name ne null && entity.map.office_name ne null}">
            <td width="88%" align="left"><c:out value="${entity.map.subcomp_name }--${entity.map.office_name }--${af.map.question_person}" /></td>
          </c:if>
          <c:if test="${entity.map.subcomp_name eq null || entity.map.office_name eq null}">
            <td width="88%" align="left"><c:out value="${af.map.question_person}" /></td>
          </c:if>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">提交时间：</td>
          <td width="88%" align="left"><fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
        </tr>
        <tr>
          <td colspan="2"></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">回复：</td>
          <td width="88%" align="left"><textarea name="fb_content" id="fb_content" cols="80" rows="4"></textarea></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><label>
              <input class="but4" type="button" name="Submit4" id="btn_submit" value="提交" />
              <input class="but5" type="button" name="Submit5" id="btn_back"value="返回" onclick="history.back();return false;" />
            </label></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	 $("#fb_content").attr("datatype","LimitB").attr("max","1000").attr("min","1").attr("msg","摘要不能超过1000个字或者为空");
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 2)){
		 $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
         $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
