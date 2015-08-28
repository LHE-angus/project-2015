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
<script type="text/javascript">
	//输入分公司时，清空下拉框
	function clearDept(){
	 	$("#dept_sn").val("");
	 	$("#dept_sn")
	 }
	 
	 //选择分公司时，清空输入框
	 function clearDeptName(){
	 	var val = $("#dept_sn").val();
	 	if(val != ''){
	 		$("#dept_name").val("");
	 	}
	 }
</script>
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
    <html-el:form action="/admin/KonkaR3DeptStore">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="id" value="${af.map.id}" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
         <c:if test="${is_admin eq 1}">
          <tr>
            <td nowrap="nowrap" height="28" class="title_item" align="right" width="15%"><span style="color:red;">* </span>分公司：</td>
            <td width="85%">
            	<html-el:select property="dept_sn" styleId="dept_sn" style="width:100px;" onchange="clearDeptName()">
                	<html-el:option value="">-请选择-</html-el:option>
              	</html-el:select>
              	或：<html-el:text property="dept_name" styleId="dept_name" size="25" maxlength="50" onfocus="clearDept()"></html-el:text>
              	<span id='fgs_msg' style="color:red;display:none"> * 请选择或填写分公司！</span>
            </td>
          </tr>
        </c:if>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right" width="15%"><span style="color:red;">* </span>工厂：</td>
          <td width="85%"><html-el:text property="fac_sn" styleId="fac_sn" size="25" maxlength="50"></html-el:text></td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right"><span style="color:red;">* </span>仓库：</td>
          <td><html-el:text property="store_sn" styleId="store_sn" size="25" maxlength="50"></html-el:text></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">仓储地点描述：</td>
          <td>
          	<html-el:textarea property="store_desc" styleId="store_desc" style="width:200px; height:100px"></html-el:textarea>
          </td>
        </tr>
        <tr>
          <td>&nbsp;&nbsp;</td>
          <td><input class="but4" type="button" name="Submit4" value="保存" id="send" />
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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#fac_sn").attr("dataType", "Require").attr("msg", "请填写工厂！");
	$("#store_sn").attr("dataType", "Require").attr("msg", "请填写库位！");
	$("#store_desc").attr("dataType", "Limit").attr("max", "50").attr("msg", "备注不能超过50个文字");
	
	$("#send").click(function(){
			var isSubmit = Validator.Validate(this.form, 3);
			var sn = $("#dept_sn").val();
			var name = $("#dept_name").val();
			if(sn == name){
				$("#fgs_msg").show();
				isSubmit = false;
			}else{
				$("#fgs_msg").hide();
			}
			if (isSubmit) {
				$(":button").attr("disabled", "true");
				$(":reset").attr("disabled", "true");
				this.form.submit();
			}
	 });
	 
	 //初始化分公司列表
	$("#dept_sn").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.dept_sn}"});
	$("#dept_sn").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "0", false);
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
