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
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <html-el:form action="/admin/KonkaR3Backmoney.do" enctype="multipart/form-data">
          <html-el:hidden property="method" value="saveBatch"/>
          <html-el:hidden property="mod_id" value="${af.map.mod_id}"/>
          <html-el:hidden property="type" value="${af.map.type}"/>
           <tr>
        <td width="120"><strong class="fb">年份：</strong></td>
        <td>
         <html-el:select property="year" styleId="year" style="width:80px;">
                  <html-el:option value="">-请选择-</html-el:option>
                  <html-el:option value="${year-10}">${year-10}</html-el:option>
                  <html-el:option value="${year-9}">${year-9}</html-el:option>
                  <html-el:option value="${year-8}">${year-8}</html-el:option>
                  <html-el:option value="${year-7}">${year-7}</html-el:option>
                  <html-el:option value="${year-6}">${year-6}</html-el:option>
                  <html-el:option value="${year-5}">${year-5}</html-el:option>
                  <html-el:option value="${year-4}">${year-4}</html-el:option>
                  <html-el:option value="${year-3}">${year-3}</html-el:option>
                  <html-el:option value="${year-2}">${year-2}</html-el:option>
                  <html-el:option value="${year-1}">${year-1}</html-el:option>
                  <html-el:option value="${year}">${year}</html-el:option>
                </html-el:select>
            </td>
            <td ></td>
            <td ></td>
          </tr>
          <tr>
            <td width="120"><strong class="fb">选择Excel文件:</strong></td>
            <td width=""><input type="file" name="excel" id="excel" onKeyDown="return false" onpaste="return false"/></td>
            <td width=""><input type="button" name="btn_submit" id="btn_submit" class="but4" value="导入"/></td>
            <td width=""><a style="cursor:pointer;color:blue;" href="${ctx}/files/upload/R3_Backmoney_import_help.doc">导入说明</a>|<a style="cursor:pointer;color:blue;" href="${ctx}/files/upload/R3_Backmoney_import_template.xls">Excel模板下载 </a></td>
          </tr>
        </html-el:form>
      </table>
      <p>&nbsp;</p>
    </div>
    <div class="rtabcont2">
      <table class="rtable2" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr class="title_top">
          <td align="center" nowrap="nowrap">1</td>
          <td nowrap="nowrap">请确认需要导入的R3客户回款符合导入模板的标准</td>
        </tr>
        <tr class="title_top">
          <td align="center" nowrap="nowrap">2</td>
          <td nowrap="nowrap">无效数据：1.R3编码为空；2.售达方（R3编码）找不到对应R3网点；</td>
        </tr>
        <tr>
          <c:if test="${z ne null }">
            <td width="40" align="center" nowrap="nowrap">3</td>
            <td nowrap="nowrap"> 本次管理员导入的Excel表总记录是${z}条，新增${y }条，更新${x }条</td>
          </c:if>
        </tr>
        <c:if test="${ error ne null}">
          <tr>
            <td width="40" align="center" nowrap="nowrap">3</td>
            <td  nowrap="nowrap">${error}</td>
          </tr>
        </c:if>
      </table>
    </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/common.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript"><!--//<![CDATA[
$(document).ready(function(){
	var f = document.forms["af"];
	String.prototype.isPath=function() { return /[^\n\r\t]*\.[x|X][l|L][s|S]/i.test(this); };
	 $("#year").attr("dataType", "Require").attr("msg", "请选择年份");
	$("#btn_submit").click(function(){
		//var path = f.excel.value;
		var path = $("#excel").val();
	    if (path == "" || !path.isPath()) {
	        alert("请选择一个有效Excel文件!");
	        return false;
	    }
		if(Validator.Validate(this.form, 3)){
            $("#btn_submit").attr("value", "导入中").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});	

})
//]]>--></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
