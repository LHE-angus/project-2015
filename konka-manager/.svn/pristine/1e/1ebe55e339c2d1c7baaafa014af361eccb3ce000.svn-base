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
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/KonkaDeptR3Pd" method="post" enctype="multipart/form-data">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" />
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="rtable3">
        <tr id="t1">
         <td >&nbsp;&nbsp;<strong class="fb">分公司：</strong>
            <c:if test="${empty af.map.fgs_id}">
            <html-el:select property="dept_id" styleId="dept_id">
            <html-el:option value="">-请选择-</html-el:option>
              <c:forEach items="${deptList}" var="cur">
                <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
              </c:forEach>
            </html-el:select>
            </c:if>
            <c:if test="${not empty af.map.fgs_id}">${af.map.fgs_name}
          		<html-el:hidden property="dept_id" styleId="dept_id" value="${af.map.fgs_id}" />
          	</c:if>
        </td>
       
          <td>文件：</td>
          <td><input type="file" name="up_load_file" id="up_load_file" style="width:250px" />
            &nbsp;&nbsp;&nbsp; <a href="${ctx}/files/template/excel/konka_dept_r3_pd.xls" target="_blank">模板下载</a></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><html-el:button property="btn_submit" styleId="btn_submit" styleClass="but_excel" value="导入" />
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#up_load_file").attr("dataType", "Require").attr("msg", "请上传数据Excel文件");
	$("#year").attr("dataType", "Require").attr("msg", "请选择年份");
	$("#month").attr("dataType", "Require").attr("msg", "请选择月份");

	if ("" == "${af.map.id}") {
		$("#dept_id").attr("dataType", "Require").attr("msg", "请选择部门！");
	}

	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 1)){
			this.form.submit();
		}
	});
});
//]]></script>
</body>
</html>