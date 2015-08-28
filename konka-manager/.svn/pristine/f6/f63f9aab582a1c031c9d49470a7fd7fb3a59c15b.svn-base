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
    <html-el:form action="/admin/KonkaYjglPlanFp" method="post" enctype="multipart/form-data">
      <html-el:hidden property="method" value="impSave" />
      <html-el:hidden property="mod_id" />
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="rtable3">
      	 <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>上样类型：</td>
          <td width="88%" align="left"><html-el:select property="plan_id" styleId="plan_id" styleClass="plan_id" style="width:120px;" >
	          				<html-el:option value="">请选择</html-el:option>
	          				<c:forEach items="${kpList}" var="cur">  
	          					<html-el:option value="${cur.id}">${cur.map.type_name}</html-el:option>
	          				</c:forEach>	
	          			</html-el:select>
	       </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">年度：</td>
          <td width="88%" align="left"><span id="year"></span>
          </td>
        </tr>
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">额度：</td>
          <td width="88%" align="left">
          	<span id="ed"></span>
          </td>   
        </tr>
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">已使用额度：</td>
          <td width="88%" align="left">
          	<span id="used_ed"></span>
          </td>   
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">文件：</td>  
          <td><input type="file" name="up_load_file" id="up_load_file" style="width:250px" />
            &nbsp;&nbsp;&nbsp; <a href="${ctx}/files/template/excel/konka_yjgl_fp.xls" >模板下载</a></td> 
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

	$("#plan_id").attr("datatype", "Require").attr("msg", "请选择额度类型");

	var p_id = $(".plan_id").val(); 
	if(p_id!=""){
		setPlanInfo(p_id); 
	}
	
	
	$(".plan_id").change(function(){
		var plan_id = $(this).val();
		setPlanInfo(plan_id);  
	});
	
	$("#up_load_file").attr("dataType", "Require").attr("msg", "请上传数据Excel文件");

	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 3)){ 
			this.form.submit();
		}
	});
});

function setPlanInfo(plan_id){
	$.ajax({
		type: "POST",
		url: "KonkaYjglPlanFpForSy.do",
		data: {method : "ajaxSetPlanInfo", "plan_id": plan_id},
		dataType: "json",
		cache:false,
		error: function(){alert("数据加载请求失败！");},
		success: function(result){
			if(result.status != 1){  
				$("#year").html("");
				$("#ed").html("");
				$("#used_ed").html("");
			}else if(result.status == 1){
				$("#year").html(result.plan_year);
				$("#ed").html(result.plan_ed);
				$("#used_ed").html(result.used_ed);
			}
		}
	});
}
//]]></script>
</body>
</html>