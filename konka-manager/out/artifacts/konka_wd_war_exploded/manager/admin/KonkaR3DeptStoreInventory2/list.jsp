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
    	<form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaR3DeptStoreInventory2.do">
      <html-el:form action="/admin/KonkaR3DeptStoreInventory2">
        <html-el:hidden property="method" value="list" />
        <html-el:hidden property="mod_id" styleId="mod_id" />
        <table width="100%" border="0" cellspacing="1" cellpadding="5" class="rtable1">
          <tr align="center">
            <td>
            	<strong class="fb">分公司：</strong>
              	<html-el:select property="dept_name" styleId="dept_name">
                	<html-el:option value="">-请选择-</html-el:option>
                	<c:forEach var="cur" items="${kDeptStoreList}">
                  		<c:if test="${not empty cur.map.fgs_name}">
                    		<html-el:option value="${cur.map.fgs_name}">${cur.map.fgs_name}</html-el:option>
                  		</c:if>
                	</c:forEach>
              	</html-el:select>
           	</td>
           	<td>
            	<strong class="fb">工厂、库位(必填):</strong>
              	<select name="zwerks" id="zwerks" onchange="fac_sn_change();">
                	<option value="">-工厂-</option>
              	</select>
              	&nbsp;
              	<select name="zlgort" id="zlgort">
                	<option value="">-库位-</option>
              	</select>
          	</td>
         	<td></td>
          </tr>
          <tr align="center">
            <td>
            	<strong class="fb">仓位(可选):</strong>
              	<html-el:text property="zlgpla" styleId="zlgpla"  styleClass="webinput" maxlength="30" />
            </td>
            <td>
            	<strong class="fb">机型(可选):</strong>
              	<html-el:text property="zmatnr" styleId="zmatnr"  styleClass="webinput" maxlength="30"/>
            </td>
            <td align="left">
              	<input name="button" type="submit" class="bgSearch" id="s_button" value="搜 索" />
              	&nbsp;&nbsp;&nbsp;&nbsp;
			  	<input type="button" id="export" value="导出" class="but_excel" />               
            </td>
          </tr>
        </table>
      </html-el:form>
    </div>
    <div style="margin:20px"></div>
    <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2" id="data_table">
        <tr class="tabtt1">
          <td width="5%" nowrap="nowrap">序号</td>
          <td width="8%" nowrap="nowrap">工厂</td>
          <td width="8%" nowrap="nowrap">WhN</td>
          <td width="8%" nowrap="nowrap">类</td>
          <td width="8%" nowrap="nowrap">库位</td>
          <td width="8%" nowrap="nowrap">仓位</td>
          <td width="8%" nowrap="nowrap">仓储类型名</td>
          <td width="8%" nowrap="nowrap">名称</td>
          <td width="8%" nowrap="nowrap">物料(机型)</td>
          <td width="8%" nowrap="nowrap">可用库存</td>
          <td width="8%" nowrap="nowrap">交货日期</td>
          <td width="8%" nowrap="nowrap">物料机型(描述)</td>
          <td width="8%" nowrap="nowrap">移动价格</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs" >
          <tr>
            <td align="center" nowrap="nowrap" height="20">${vs.count}</td>
            <td align="center" nowrap="nowrap" >${cur.WERKS}</td>
            <td align="center" nowrap="nowrap" >${cur.LGNUM}</td>
            <td align="center" nowrap="nowrap" >${cur.LGTYP}</td>
            <td align="center" nowrap="nowrap" >${cur.LGORT}</td>
            <td align="center" nowrap="nowrap" >${cur.LGPLA}</td>
            <td align="center" nowrap="nowrap" >${cur.LGTYP}</td>
            <td align="center" nowrap="nowrap" >${cur.NAME1}</td>
            <td align="center" nowrap="nowrap" >${cur.MATNR}</td>
            <td align="center" nowrap="nowrap" >${cur.VERME}</td>
            <td align="center" nowrap="nowrap" >${cur.EDATU}</td>
            <td align="center" nowrap="nowrap" >${cur.MAKTX}</td>
            <td align="center" nowrap="nowrap" >${cur.VERPR}</td>
          </tr>
        </c:forEach>
      </table>
    </div>
    <div class="clear"></div>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

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

	
	$("#s_button").click(function(){
		if($("#zwerks").val()==null || $("#zwerks").val()==""){
			alert("工厂不能为空");
			return false ;
		}
		if($("#zlgort").val()==null || $("#zlgort").val()==""){
			alert("库位不能为空");
			return false ;
		}
		return true;
		
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
	$("#zwerks").empty();
	$("<option value=''>-工厂-</option>").appendTo($("#zwerks"));
	$.ajax({
		type: "POST",
		url: "KonkaR3DeptStoreInventory2.do",
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
					option.appendTo($("#zwerks"));
				});
				if('${af.map.zwerks}' != null || '${af.map.zwerks}' != '' ){
					$("#zwerks").val('${af.map.zwerks}');
					fac_sn_change();
				}
			}
		}
	});
}

//分公司-工厂联动
function fac_sn_change(){
	$("#zlgort").empty();
	$("<option value=''>-库位-</option>").appendTo($("#zlgort"));
	$.ajax({
		type: "POST",
		url: "KonkaR3DeptStoreInventory2.do",
		data: "method=getStore_sn&fac_sn="+$('#zwerks').val()+"&dept_name="+$("#dept_name").val(),
		dataType: "json",
		error: function(request, settings) {
			alert("检查工厂名失败，请稍候再次尝试。");
			return null;
		},
		success: function(data) {
			if(data != null){
				$.each(data, function(i, item) {
					var option = $("<option></option>").val(item[0]).text(item[0]);
					option.appendTo($("#zlgort"));
				});
				if('${af.map.zlgort}' != null || '${af.map.zlgort}' != '' ){
					$("#zlgort").val('${af.map.zlgort}');
				}
			}
		}
	});
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
