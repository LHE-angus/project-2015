<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${naviString}</title>
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
    <table width="100%" border="0" cellpadding="0" cellspacing="0" >
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/DeptPdManager">
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="mod_id" styleId="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="dept_id" styleId="dept_id" />
      <html-el:hidden property="dept_name_like" styleId="dept_name_like" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2" >
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item">部门编号：</td>
          <td width="88%"><c:out value="${af.map.dept_sn}">
            </c:out></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item">部门名称：</td>
          <td width="88%"><c:out value="${af.map.dept_name}">
            </c:out></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item"></td>
          <td width="88%">请选择产品</td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item">产品名称：</td>
          <td width="88%" valign="top"> 产品类别：
            <html-el:select property="cls_id" styleId="cls_id" style="width:200px;">
              <c:forEach var="cur" items="${basePdClazzList}">
                <html-el:option value="${cur.cls_id}">${fn:escapeXml(cur.tree_name)}</html-el:option>
              </c:forEach>
            </html-el:select>
            <table class="areause1" >
              <tbody>
                <tr>
                  <td><span id="area_name_0">供选择产品列表</span><br />
                    <html-el:select property="select1" multiple="true" style="width:290px;height:358px;" styleId="select1" onchange="moveSelected(this.form.select1, this.form.select2);"> </html-el:select></td>
                  <td width="20"></td>
                  <td><span id="area_name_1">选择的产品列表</span><br />
                    <html-el:select property="select2" multiple="true" style="width:290px;height:358px;" styleId="select2" onchange="moveSelected(this.form.select2, this.form.select1);">
                      <c:if test="${not empty deptPdLinkList}">
                        <html-el:optionsCollection label="map.full_name" value="pd_id" name="deptPdLinkList" />
                      </c:if>
                    </html-el:select></td>
                </tr>
                <tr>
                  <td colspan="2" id="notice"><font color="red">*&nbsp;列表项可以通过双击在两个区域间移动</font></td>
                </tr>
              </tbody>
            </table></td>
        </tr>
      </table>
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr>
          <td align="center"><input class="but4" type="submit" name="Submit4" value=" 保存 " id="btn_submit" />
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back()" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
<script type="text/javascript">//<![CDATA[
   $("#cls_id").change(function(){
	   $("#select1").empty();
	  $.ajax({
			type: "POST",
			url: "DeptPdManager.do",
			data: "method=" + "ajaxSelectDeptPdList" + "&cls_id=" + $("#cls_id").val()+"&dept_id="+"${af.map.dept_id}",
			dataType: "json",
			error: function(request, settings) {},
			success: function(data) {
				if (data.length >= 1) {
					for(var i = 0; i < data.length - 1; i++) {
						var opt = new Option( data[i].name,data[i].id); 
						$("#select1").get(0).options.add(opt);
					}
				}
		    }
	 });
	  
	  $("#btn_submit").click(function(){
		  var opts=document.getElementById("select2");
			for(var i=0;i<opts.length;i++)
			{
				opts[i].selected=true;
			}
		  $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
		     this.form.submit();
	  });
	  
	  $("#send").click(function(){
			var isSubmit = Validator.Validate(this.form, 2);
			if (isSubmit) {
				$(":button").attr("disabled", "true");
				$(":reset").attr("disabled", "true");
				this.form.submit();
			}
		});
});

function moveSelected(sourceSelect, targetSelect, isDelete){
	var cachOptionsArray = new Array();
	var index = 0;
	for (var i = sourceSelect.options.length - 1; i >= 0; i--){
		if (sourceSelect.options[i].selected){
			cachOptionsArray[index] = new Option(sourceSelect.options[i].text, sourceSelect.options[i].value);
			if(isDelete==undefined || isDelete==true){
				sourceSelect.options[i] = null;
			}
			index++;
		}
	}
	var exist = false;
	for (var i = cachOptionsArray.length - 1; i >= 0; i--){
		exist = false;
		for (var j = 0; j < targetSelect.options.length; j++){
			if (targetSelect.options[j].value.toString() == cachOptionsArray[i].value.toString()){
				exist = true; 
				break;
			}
		}
		if (!exist){
			targetSelect.options[targetSelect.options.length] = cachOptionsArray[i];
		}
	}
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>

