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
    <html-el:form action="/admin/StatisticalDimensionTimeMonth">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="id" value="${af.map.id}" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right" width="15%">年：</td>
          <td width="85%">
			<html-el:select property="year" styleId="year">
           		<html-el:option value="">-请选择-</html-el:option>
           		<c:forEach begin="2008" end="2020" var="cur" varStatus="vs">
           			<html-el:option value="${cur}">${cur}</html-el:option>
           		</c:forEach>
           	</html-el:select>
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right" width="15%">月：</td>
          <td width="85%">
			<html-el:select property="month" styleId="month">
           		<html-el:option value="">-请选择-</html-el:option>
           		<c:forEach begin="1" end="12" var="cur" varStatus="vs">
           			<html-el:option value="${cur}">${cur}</html-el:option>
           		</c:forEach>
           	</html-el:select>
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right">开始时间：</td>
          <td>
          	<fmt:formatDate value="${af.map.start_date}" pattern="yyyy-MM-dd" var="start_date" />
          	<input name="start_date" id="start_date" size="12" value="${start_date}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'2008-01-01',maxDate:'#F{$dp.$D(\'end_date\')}'})" />
          	&nbsp;<font color="red">默认匹配到00:00:00</font>
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right">截止时间：</td>
          <td>
          	<fmt:formatDate value="${af.map.end_date}" pattern="yyyy-MM-dd" var="end_date" />
            <input name="end_date" id="end_date" size="12"  value="${end_date}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'#F{$dp.$D(\'start_date\')||\'2008-01-01\'}'})" />
          	&nbsp;<font color="red">默认匹配到23:59:59</font>
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right">月度类别：</td>
          <td>
          	<html-el:select property="is_cw" styleId="is_cw">
           		<html-el:option value="0">自然月度</html-el:option>
           		<html-el:option value="1">财务月度</html-el:option>
           	</html-el:select>
          </td>
        </tr>
        <tr>
          <td>&nbsp;&nbsp;</td>
          <td><input class="but4" type="button" name="Submit4" value="保存" id="add_btn" />
            <input class="but3" type="reset"  value="重填 " />
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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#year").attr("dataType", "Require").attr("msg", "请选择年度");
	$("#month").attr("dataType", "Require").attr("msg", "请选择月度");
	$("#is_cw").attr("dataType", "Require").attr("msg", "请选择月度类别");
	$("#start_date").attr("dataType", "Require").attr("msg", "请选择开始时间");
	$("#end_date").attr("dataType", "Require").attr("msg", "请选择结束时间");
	
	$("#add_btn").click(function(){
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
