<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <div class="rtabcont1">
      <%@ include file="/commons/pages/messages.jsp" %>
    </div>
    <div class="rtabcont1">
    	<form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaR3DeptStoreInventory.do">
      <html-el:form action="/admin/KonkaR3DeptStoreInventory">
        <html-el:hidden property="method" value="list" />
        <html-el:hidden property="mod_id" styleId="mod_id" />
        <table width="100%" border="0" cellspacing="1" cellpadding="5" class="rtable1">
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
              &nbsp;&nbsp; <strong class="fb">工厂仓位：</strong>
              <select name="fac_sn" id="fac_sn" onchange="fac_sn_change();">
                <option value="">-工厂-</option>
              </select>
              &nbsp;
              <select name="store_sn" id="store_sn">
                <option value="">-仓位-</option>
              </select>
              &nbsp;&nbsp; <strong class="fb">物料机型：</strong>
              <html-el:text property="zmatnr" styleId="zmatnr" maxlength="30" />
              &nbsp;&nbsp;&nbsp;
              <html-el:button styleId="btn_submit" styleClass="but1" property="">搜索</html-el:button>
              &nbsp;&nbsp;&nbsp;
			  <input type="button" id="export" value="导出" class="but_excel" />              
              </td>
          </tr>
        </table>
      </html-el:form>
      </form>
    </div>
    <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2" id='data_table'>
        <tr class="tabtt1">
          <td nowrap="nowrap" width="5%" align="center">序号</td>
          <td align="center">物料机型</td>
          <td width="10%" nowrap="nowrap" align="center" >工厂</td>
          <td width="10%" nowrap="nowrap" align="center" >工厂名称</td>
          <td width="10%" nowrap="nowrap" align="center" >仓位</td>
          <td width="10%" nowrap="nowrap" align="center" >仓位名称</td>
          <td width="12%" nowrap="nowrap" align="center" >非限制使用库存</td>
		  <td width="12%" nowrap="nowrap" align="center" >交货单锁定库存</td>
          <td width="10%" nowrap="nowrap" align="center" >剩余量</td>
        </tr>
        <c:forEach var="cur" items="${entitylist}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap" height="20">${vs.count}</td>
            <td align="left" nowrap="nowrap" >${cur.matnr}</td>
            <td align="left" nowrap="nowrap" >${cur.werks}</td>
            <td align="left" nowrap="nowrap" >${cur.name1}</td>
            <td align="left" nowrap="nowrap" >${cur.lgort}</td>
            <td align="left" nowrap="nowrap" >${cur.lgobe}</td>
            <td align="right" nowrap="nowrap" >${cur.labst}</td>
			<td align="right" nowrap="nowrap" >${cur.speme}</td>
            <td align="right" nowrap="nowrap" >${cur.lamount}</td>
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
	}).multiselectfilter();

	dept_name_change();

	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 1)) {
			this.form.submit();
		}
	});
	
	//导出
	$("#export").click(function(){
		var table = document.getElementById("data_table");
		var rows = table.rows.length;
		if(rows>1){
			if(confirm("提示，您确认导出数据？")){
				$("#bottomPageForm").append("<input type='hidden' name='excel_to_all' value='1' id='excel_to_all' />");
	    		$("#bottomPageForm").submit();
	    		$("#excel_to_all").val("");
			}
		}else{
		    alert("请先查询数据后导出");	
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
