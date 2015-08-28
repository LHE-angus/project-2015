<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${naviString}</title>
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/mobile/css/common.css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont" id="body_oarcont">

  <div class="rtabcont1">
    <div class="rtabcont1">
      <%@ include file="/commons/pages/messages.jsp" %>
    </div>
    <div class="rtabcont1">
      <html-el:form action="/KonkaR3DeptStoreInventory">
        <html-el:hidden property="method" value="list" />
        <html-el:hidden property="mod_id" styleId="mod_id" />
        <html-el:hidden property="user_id" styleId="${user_id}" />
        <html-el:hidden property="userpass" styleId="${userpass}" />
        <table width="100%" border="0" cellspacing="1" cellpadding="5" class="rtab2">
          <tr>
            <td><strong class="fb">分公司：</strong>
              <html-el:select property="dept_name" styleId="dept_name">
                <html-el:option value="">-请选择-</html-el:option>
                <c:forEach var="cur" items="${kDeptStoreList}">
                  <c:if test="${not empty cur.map.fgs_name}">
                    <html-el:option value="${cur.map.fgs_name}">${cur.map.fgs_name}</html-el:option>
                  </c:if>
                </c:forEach>
              </html-el:select>
              <br/>
             <strong class="fb">工厂仓位：</strong>
              <select name="fac_sn" id="fac_sn" onchange="fac_sn_change();">
                <option value="">-工厂-</option>
              </select>
              <select name="store_sn" id="store_sn">
                <option value="">-仓位-</option>
              </select>
              <br/>
              <strong class="fb">物料机型：</strong>
              <html-el:text property="zmatnr" styleId="zmatnr" />
             <br/>
              <html-el:button styleId="btn_submit" styleClass="but1" property="">搜索</html-el:button></td>
          </tr>
        </table>
      </html-el:form>
    </div>
    <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr>
        <td align="left">物料机型 <br/>剩余量 </td>
        <td align="left">工厂  <br/>工厂名称 <br/> 非限制使用库存</td>
        <td align="right"> 仓位 <br/>仓位名称  <br/>交货单锁定库存</td>
        </tr>
          <c:forEach var="cur" items="${entitylist}" varStatus="vs">
        <tr>
        <td align="left">  ${cur.matnr}<br/>${cur.lamount}  </td>
        <td align="left">  ${cur.werks}<br/>${cur.name1} <br/> ${cur.labst}</td>
        <td align="right"> ${cur.lgort}<br/> ${cur.lgobe} <br/>${cur.speme}</td>
        </tr>
         </c:forEach>
      </table>
    </div>
    <div class="clear"></div>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	$("#fac_sn").attr("datatype", "Require").attr("msg", "请选择工厂！");
	$("#store_sn").attr("datatype", "Require").attr("msg", "请选择仓位！");
	
	//选择型号
	$("#dept_name").multiselect({
		noneSelectedText: '==请选择==',
		selectedList: 1,
		multiple: false,
		minWidth:220,
		click: function(event, ui){
	       if(ui.value != ""){
	    	   $("#dept_name").val(ui.value);
	    	   dept_name_change();
	       }
		}
	});

	dept_name_change();

	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 1)) {
			this.form.submit();
		}
	});
});

//分公司-工厂联动
function dept_name_change(){
	$("#fac_sn").empty();
	$("<option value=''>-工厂-</option>").appendTo($("#fac_sn"));
	$.ajax({
		type: "POST",
		url: "KonkaR3DeptStoreInventory.do",
		data: "method=getFacSn&dept_name="+$("#dept_name").val(),
		dataType: "json",
		error: function(request, settings) {
			alert("检查工厂名失败，请稍候再次尝试。");
			return null;
		},
		success: function(data) {
			if(data != null){
				$.each(data, function(i, item) {
					var option = $("<option></option>").val(item[0]).text(item[0]);
					option.appendTo($("#fac_sn"));
				});
				if('${af.map.fac_sn}' != null || '${af.map.fac_sn}' != '' ){
					$("#fac_sn").val('${af.map.fac_sn}');
					fac_sn_change();
				}
			}
		}
	});
}

//分公司-工厂联动
function fac_sn_change(){
	$("#store_sn").empty();
	$("<option value=''>-仓位-</option>").appendTo($("#store_sn"));
	$.ajax({
		type: "POST",
		url: "KonkaR3DeptStoreInventory.do",
		data: "method=getStore_sn&fac_sn="+$('#fac_sn').val()+"&dept_name="+$("#dept_name").val(),
		dataType: "json",
		error: function(request, settings) {
			alert("检查工厂名失败，请稍候再次尝试。");
			return null;
		},
		success: function(data) {
			if(data != null){
				$.each(data, function(i, item) {
					var option = $("<option></option>").val(item[0]).text(item[0]);
					option.appendTo($("#store_sn"));
				});
				if('${af.map.store_sn}' != null || '${af.map.store_sn}' != '' ){
					$("#store_sn").val('${af.map.store_sn}');
				}
			}
		}
	});
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>

