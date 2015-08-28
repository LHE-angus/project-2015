<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>批量更新</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body style="background-color:#fff;">
<div class="oarcont" id="body_oarcont">
	<div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
      <html-el:form action="/spgl/EcBaseCardNo">
        <html-el:hidden property="queryString" styleId="queryString" />
        <html-el:hidden property="method" styleId="method" value="save1" />
        <html-el:hidden property="mod_id" styleId="mod_id" />
        <html-el:hidden property="id" styleId="id" />
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="datagrid">
        <tr>
        	<td nowrap="nowrap">
        	<table width="100%" border="0" align="left" >
                <tr >
                  <td width="286px;"><span id="area_name_0" class="title_item">供选择用户列表</span><br /><html-el:select property="select1" multiple="true" style="width:260px;height:200px;" styleId="select1" onchange="moveSelected(this.form.select1, this.form.select2);">
                      <c:if test="${not empty entityList}">
                        <html-el:optionsCollection label="member_name" value="id"  name="entityList" />
                      </c:if>
                    </html-el:select></td>
                  <td><span id="area_name_0" class="title_item">已选择用户列表</span><br /><html-el:select property="select2" multiple="true" style="width:260px;height:200px;" styleId="select2" onchange="moveSelected(this.form.select2, this.form.select1);">
                    </html-el:select></td>
                </tr>
              </table>
        	</td>
        </tr>
          <tr>
            <td class="title_item" height="29" nowrap="nowrap">会员卡等级：
            <html-el:select property="card_level" styleId="card_level" >
                <html-el:option value="">请选择...</html-el:option>
                <c:forEach var="cur" items="${elList}">
                  <html-el:option value="${cur.card_level}">${cur.card_level_name}</html-el:option>
                </c:forEach>
              </html-el:select>
            </td>
          </tr>
            <tr>
            <td class="title_item" height="29" nowrap="nowrap">操作描述：
            <html-el:textarea property="opr_desc" styleId="opr_desc" cols="5" style="width:450px;height:70px;" />
          	<div id="info_tip" style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div>
              &nbsp;</td>
          </tr>
          <tr>
            <td class="title_item" height="29" nowrap="nowrap"><html-el:button property="" value="提 交" styleClass="but4" styleId="btn_submit" />
              &nbsp;&nbsp;<html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();return false;" />&nbsp;&nbsp;&nbsp;</td>
          </tr>
          </table>
      </html-el:form>
    </div>
  </div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){


	$("#opr_desc").textbox({
		maxLength: 450,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_tip").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_tip").slideUp("normal");
	});
	
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 3)){
			var opts=document.getElementById("select2");
			for(var i=0;i<opts.length;i++){
				opts[i].selected=true;
			}
			
			$("#btn_submit").attr("disabled", true);
			$("#btn_back"  ).attr("disabled", true);
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
