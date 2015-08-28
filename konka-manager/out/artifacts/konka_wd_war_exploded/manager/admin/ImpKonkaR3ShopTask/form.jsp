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
    <html-el:form action="/admin/ImpKonkaR3ShopTask" method="post" enctype="multipart/form-data">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" />
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="rtable3">
      	<tr>
      	  <td>分公司：</td>
      	  <td>
      	  	<html-el:select property="dept_id" styleId="dept_id">
      	  		<c:if test="${empty is_fgs}"><html-el:option value="">-请选择-</html-el:option></c:if>
      	  		<c:forEach items="${entityList}" var="cur">
      	  			<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
      	  		</c:forEach>
      	  	</html-el:select>
      	  </td>
      	</tr>
      	<tr>
      	  <td>任务类型：</td>
      	  <td>
      	  	<html-el:select property="task_type" styleId="task_type">
      	  		<html-el:option value="">-请选择-</html-el:option>
      	  		<html-el:option value="1">结算任务</html-el:option>
      	  		<html-el:option value="2">零售任务</html-el:option>
      	  		<html-el:option value="3">回款任务</html-el:option>
      	  	</html-el:select>
      	  </td>
      	</tr>
        <tr>
          <td>文件：</td>
          <td><input type="file" name="up_load_file" id="up_load_file" style="width:250px" />
            &nbsp;&nbsp;&nbsp; <a href="${ctx}/files/template/excel/konka-r3-task-imp-templete.xls" target="_blank">模板下载</a></td>
        </tr>
        <tr>
          <td colspan="2">&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><html-el:button property="btn_submit" styleId="btn_submit" styleClass="but_excel" value="导入" />
          <input type="button" name="" value="返回" id="btn_reset" class="but5" onclick="history.back();" />
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
	$("#dept_id").attr("dataType", "Require").attr("msg", "请选择分公司");
	$("#task_type").attr("dataType", "Require").attr("msg", "请选择任务类型");
	$("#up_load_file").attr("dataType", "Require").attr("msg", "请上传数据Excel文件");

	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 1)){
			this.form.submit();
		}
	});
});
//]]></script>
</body>
</html>