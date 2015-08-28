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
      <html-el:form action="/admin/KonkaR3YjKcSearh">
        <html-el:hidden property="method" value="list" />
        <html-el:hidden property="mod_id" styleId="mod_id" />
        <table width="100%" border="0" cellspacing="1" cellpadding="5" class="rtable1">
          <tr>
            <td>&nbsp;&nbsp;<strong class="fb">分公司：</strong>
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
              &nbsp;&nbsp; <strong class="fb">客户R3编码：</strong>
              <html-el:select property="r3_code" styleId="r3_code">
                <html-el:option value="">-请选择-</html-el:option>
                <c:forEach var="cur" items="${kR3ShopsList}">
                  <html-el:option value="${cur.map.r3_code}">${cur.map.r3_code}</html-el:option>
                </c:forEach>
              </html-el:select></td>
          </tr>
          <tr>
            <td>&nbsp;&nbsp;<strong class="fb">物料机型：</strong>
              <html-el:select property="zmatnr" styleId="zmatnr">
                <html-el:option value="">-请选择-</html-el:option>
                <c:forEach var="cur" items="${pdModelList}">
                  <html-el:option value="${cur.md_name}">${cur.md_name}</html-el:option>
                </c:forEach>
              </html-el:select>
              &nbsp;&nbsp;&nbsp;
              <html-el:button styleId="btn_submit" styleClass="but1" property="">搜索</html-el:button></td>
          </tr>
        </table>
      </html-el:form>
    </div>
    <div id="div_ox" class="rtabcont1" style="overflow-x: auto;">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" nowrap="nowrap">序号</td>
          <td width="5%" nowrap="nowrap">仓位</td>
          <td width="8%" nowrap="nowrap">仓库号/混合仓库 </td>
          <td width="8%" nowrap="nowrap">仓储类型</td>
          <td width="8%" nowrap="nowrap"> 名称 1</td>
          <td width="8%" nowrap="nowrap">存储区</td>
          <td width="8%" nowrap="nowrap">仓位类型</td>
          <td width="8%" nowrap="nowrap">仓位类型说明</td>
          <td width="8%" nowrap="nowrap">物料号</td>
          <td width="8%" nowrap="nowrap">物料描述</td>
          <td width="8%" nowrap="nowrap">批号</td>
          <td width="8%" nowrap="nowrap">工厂</td>
          <td width="8%" nowrap="nowrap">库存地点</td>
          <td width="8%" nowrap="nowrap">基本计量单位</td>
          <td width="8%" nowrap="nowrap">移动平均价格/周期单价</td>
          <td width="8%" nowrap="nowrap">估价的总库存价值</td>
          <td width="8%" nowrap="nowrap">可用库存</td>
          <td width="8%" nowrap="nowrap">未清转储数量</td>
          <td width="8%" nowrap="nowrap"> 计划行日期 </td>
          <td width="8%" nowrap="nowrap">一般标记</td>
          <td width="8%" nowrap="nowrap">位字符字段</td>
          <td width="8%" nowrap="nowrap">一般标记</td>
        </tr>
        <c:forEach var="cur" items="${entitylist}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap" height="20">${vs.count}</td>
            <td align="center" nowrap="nowrap" >${cur.LGPLA}</td>
            <td align="center" nowrap="nowrap" >${cur.LGNUM}</td>
            <td align="center" nowrap="nowrap" >${cur.LGTYP}</td>
            <td align="center" nowrap="nowrap" >${cur.NAME1}</td>
            <td align="center" nowrap="nowrap" >${cur.LGBER}</td>
            <td align="center" nowrap="nowrap" >${cur.LPTYP}</td>
            <td align="center" nowrap="nowrap" >${cur.PTYPT}</td>
            <td align="center" nowrap="nowrap" >${cur.MATNR}</td>
            <td align="center" nowrap="nowrap" >${cur.MAKTX}</td>
            <td align="center" nowrap="nowrap" >${cur.CHARG}</td>
            <td align="center" nowrap="nowrap" >${cur.WERKS}</td>
            <td align="center" nowrap="nowrap" >${cur.LGORT}</td>
            <td align="center" nowrap="nowrap" >${cur.MEINS}</td>
            <td align="center" nowrap="nowrap" >${cur.VERPR}</td>
            <td align="center" nowrap="nowrap" >${cur.SALK3}</td>
            <td align="center" nowrap="nowrap" >${cur.VERME}</td>
            <td align="center" nowrap="nowrap" >${cur.TRAME}</td>
            <td align="center" nowrap="nowrap" >${cur.EDATU}</td>
            <td align="center" nowrap="nowrap" >${cur.NEWPAGE}</td>
            <td align="center" nowrap="nowrap" >${cur.LINES}</td>
            <td align="center" nowrap="nowrap" >${cur.NEWP}</td>
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
	$("#r3_code").attr("datatype", "Require").attr("msg", "请选择R3客户编码！");

	var count = '${count}';
	if(count.length > 0 && count > 0){
		$("#div_ox").attr("overflow-x","auto");
	} else {
		$("#div_ox").css("overflow-x","hidden");
	};
	
	//选择型号
	$("#dept_name").multiselect({
		noneSelectedText: '==请选择==',
		selectedList: 1,
		multiple: false,
		minWidth:180,
		click: function(event, ui){
	       if(ui.value != ""){
	    	   $("#dept_name").val(ui.value);
	    	   dept_name_change();
	       }
		}
	}).multiselectfilter();

	//选择型号
	$("#zmatnr").multiselect({
		noneSelectedText: '==请选择==',
		selectedList: 1,
		multiple: false,
		minWidth:180,
		click: function(event, ui){
	       if(ui.value != ""){
	    	   
	       }
		}
	}).multiselectfilter();

	//选择客户
	$("#r3_code").multiselect({
		noneSelectedText: '==请选择==',
		selectedList: 1,
		multiple: false,
		minWidth:180,
		click: function(event, ui){
	       if(ui.value != ""){
	    	   
	       }
		}
	}).multiselectfilter();

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
			alert("检查用户名重复失败，请稍候再次尝试。");
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
			alert("检查用户名重复失败，请稍候再次尝试。");
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