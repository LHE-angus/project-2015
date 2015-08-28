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
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/KonkaSpList">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="id" value="${af.map.id}" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right" width="15%">年月：</td>
          <td width="85%">
	          <html-el:select property="year" styleId="year">
	              <c:forEach items="${yearList}" var="cur">
	                <html-el:option value="${cur}">${cur}年</html-el:option>
	              </c:forEach>
	            </html-el:select>
	            <html-el:select property="month" styleId="month">
	              <html-el:option value="1">1月</html-el:option>
	              <html-el:option value="2">2月</html-el:option>
	              <html-el:option value="3">3月</html-el:option>
	              <html-el:option value="4">4月</html-el:option>
	              <html-el:option value="5">5月</html-el:option>
	              <html-el:option value="6">6月</html-el:option>
	              <html-el:option value="7">7月</html-el:option>
	              <html-el:option value="8">8月</html-el:option>
	              <html-el:option value="9">9月</html-el:option>
	              <html-el:option value="10">10月</html-el:option>
	              <html-el:option value="11">11月</html-el:option>
	              <html-el:option value="12">12月</html-el:option>
                </html-el:select>
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right">起始日期：</td>
          <td><fmt:formatDate value="${af.map.s_date}" pattern="yyyy-MM-dd" var="_s_date"/>
            <html-el:text styleId="s_date" property="s_date" value="${_s_date}" size="10" maxlength="20" readonly="true" onclick="new Calendar(2000, 2030, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right">结束日期：</td>
          <td><fmt:formatDate value="${af.map.e_date}" pattern="yyyy-MM-dd" var="_e_date"/>
            <html-el:text styleId="e_date" property="e_date" value="${_e_date}" size="10" maxlength="20" readonly="true" onclick="new Calendar(2000, 2030, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
        </tr>
        <tr>
          <td>&nbsp;&nbsp;</td>
          <td><input class="but4" type="button" name="Submit4" value="提交" id="send" />
            <input class="btn_reset" type="reset"  value="重填 " />
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back()" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<!-- ****** Main Frame End ****** -->
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#s_date").attr("dataType", "Require").attr("msg", "请选择起始日期！");
	$("#e_date").attr("dataType", "Require").attr("msg", "请选择结束日期！");
	$("#year").attr("dataType", "Require").attr("msg", "请选择年份！");
	$("#month").attr("dataType", "Require").attr("msg", "请选择月份！");
	
	$("#send").click(function(){
		var s_date = ($("#s_date").val()).replace(/\-/g,"");
		var e_date = ($("#e_date").val()).replace(/\-/g,"");
		
		if(Number(s_date) > Number(e_date)){
			alert("起始日期不能大于结束日期！");
			return null;
		}
		
		var isSubmit = Validator.Validate(this.form, 3);
		if (isSubmit) {
			$(":button").attr("disabled", "true");
			$(":reset").attr("disabled", "true");
			this.form.submit();
		}
	 });
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
