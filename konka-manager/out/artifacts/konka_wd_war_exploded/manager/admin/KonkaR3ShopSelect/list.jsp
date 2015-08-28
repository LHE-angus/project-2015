<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>请选择供应商</title>
<base target="_self" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="rtabcont1">
    <html-el:form action="/admin/KonkaR3ShopSelect">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <c:if test="${not isHeadquarters}"><html-el:hidden property="fgs_dept_id" value="${af.map.fgs_dept_id}" /></c:if>
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td><strong class="fb">分公司：</strong>
            <html-el:select property="fgs_dept_id" styleId="fgs_dept_id" value="${af.map.fgs_dept_id}">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${konkaDeptList}">
                <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
              </c:forEach>
            </html-el:select></td>
          <td><strong class="fb">经营部：</strong>
            <html-el:select property="jyb_dept_id" styleId="jyb_dept_id">
              <html-el:option value="">请选择...</html-el:option>
            </html-el:select></td>
          <td><strong class="fb">办事处：</strong>
            <html-el:select property="bsc_dept_id" styleId="bsc_dept_id">
              <html-el:option value="">请选择...</html-el:option>
            </html-el:select></td>
          <td><strong class="fb">模糊搜索：</strong>
            <html-el:text property="keyword" size="10" maxlength="20" styleId="keyword" title="请输入客户名称或所属地区或分公司所在地"/></td>
          <td><strong class="fb">R3编码：</strong>
            <html-el:text property="code_like" size="10" maxlength="20" styleId="code_like"  /></td>
          <td><html-el:submit styleClass="but1" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
        <td width="30" align="center">序号</td>
        <td nowrap="nowrap" width="55">R3编码</td>
        <td nowrap="nowrap">网点名称</td>
        <td nowrap="nowrap">客户类型</td>
        <td nowrap="nowrap">区域信息</td>
        <td nowrap="nowrap">经办名称</td>
        <td nowrap="nowrap" align="center" width="60">操作</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
          <td align="left" nowrap="nowrap">${fn:escapeXml(cur.r3_code)}</td>
          <td align="left">${fn:escapeXml(cur.customer_name)} </td>
          <td>${cur.customer_type}</td>
          <td>${fn:escapeXml(cur.area_name)}</td>
          <td>${fn:escapeXml(cur.handle_name)}</td>
          <td align="center" nowrap="nowrap"><input type="button" class="btn_sub" value="选择" title="${cur.r3_code},${cur.customer_name}" /></td>
        </tr>
        <c:if test="${vs.last eq true}">
          <c:set var="i" value="${vs.count}" />
        </c:if>
      </c:forEach>
      <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
        <tr align="center">
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </c:forEach>
    </table>
    <br />
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaR3ShopSelect.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			pager.addHiddenInputs("keyword", "${fn:escapeXml(af.map.keyword)}");	
			pager.addHiddenInputs("code_like", "${fn:escapeXml(af.map.code_like)}");	
			pager.addHiddenInputs("fgs_dept_id", "${fn:escapeXml(af.map.fgs_dept_id)}");	
			pager.addHiddenInputs("jyb_dept_id", "${fn:escapeXml(af.map.jyb_dept_id)}");	
			pager.addHiddenInputs("bsc_dept_id", "${fn:escapeXml(af.map.bsc_dept_id)}");	
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$(".btn_sub").click(function(){
		var val = $(this).attr("title");
		window.returnValue = { ids : val.split(",")[0], names : val.split(",")[1]};
		window.close();
	});

	// 判断是否是分公司
	if('${isHeadquarters}' == 'false'){
		$("#fgs_dept_id").attr("disabled", true);
	} 
	
	// 根据网点信息动态显示
	dept_chage();
	jyb_chage();
	
	$("#fgs_dept_id").change( function() {
		var dept_id = $("#fgs_dept_id").val();
		$("#jyb_dept_id").empty();
		$("#bsc_dept_id").empty();
	  // 	$("#ywy_user_id").empty();

		if(""==dept_id){
	   		var opt1 = new Option( "请选择...",""); 
	   		var opt2 = new Option( "请选择...",""); 
	   		//var opt3 = new Option( "请选择...",""); 
			$("#jyb_dept_id").get(0).options.add(opt1);
			$("#bsc_dept_id").get(0).options.add(opt2);
			//$("#ywy_user_id").get(0).options.add(opt3);
		   	}
	   	if(dept_id!=""){
		   	$.ajax({
				type: "POST",
				cache: false,
				url: "${ctx}/manager/admin/CsAjax.do",
				data: "method=getJybDeptListByFgsId&fgs_dept_id=" + $("#fgs_dept_id").val(),
				dataType: "json",
				error: function(request, settings){},
				success: function(data) {
					if (data.length >= 1) {
						var opt1 = new Option( "请选择...",""); 
						$("#jyb_dept_id").get(0).options.add(opt1);

						var opt2 = new Option( "请选择...",""); 
						$("#bsc_dept_id").get(0).options.add(opt2);
						
						for(var i = 0; i < data.length - 1; i++) {
							var opt = new Option( data[i].name,data[i].id); 
							$("#jyb_dept_id").get(0).options.add(opt);
						}
					}
				}
			});
	   	}
	   	
	});
	
	$("#jyb_dept_id").change( function() {
		var dept_id = $("#jyb_dept_id").val();
		$("#bsc_dept_id").empty();
	   //	$("#ywy_user_id").empty();

	   	if(""==dept_id){
	   		var fgs_dept_id = $("#fgs_dept_id").val();
			//getYwyUserListByDeptId(fgs_dept_id);
				
			   var opt = new Option( "请选择...",""); 
				$("#bsc_dept_id").get(0).options.add(opt);
			}
	   	else if(dept_id!=""){
		   	$.ajax({
				type: "POST",
				cache: false,
				url: "${ctx}/manager/admin/CsAjax.do",
				data: "method=getBscDeptListByJybId&jyb_dept_id=" + $("#jyb_dept_id").val(),
				dataType: "json",
				error: function(request, settings){},
				success: function(data) {
					if (data.length >= 1) {
						var opt = new Option( "请选择...",""); 
						$("#bsc_dept_id").get(0).options.add(opt);
						
						for(var i = 0; i < data.length - 1; i++) {
							var opt = new Option( data[i].name,data[i].id); 
							$("#bsc_dept_id").get(0).options.add(opt);
						}
					}
				}
			});
	   	}
	});
});


function dept_chage(){
	var dept_id = $("#fgs_dept_id").val();
	$("#jyb_dept_id").empty();
	$("#bsc_dept_id").empty();
   	//$("#ywy_user_id").empty();
   	var jyb_value = '${af.map.jyb_dept_id}';
	if(""==dept_id){
   		var opt1 = new Option( "请选择...",""); 
   		var opt2 = new Option( "请选择...",""); 
   		//var opt3 = new Option( "请选择...",""); 
		$("#jyb_dept_id").get(0).options.add(opt1);
		$("#bsc_dept_id").get(0).options.add(opt2);
		//$("#ywy_user_id").get(0).options.add(opt3);
	   	}
   	if(dept_id!=""){
	   	$.ajax({
			type: "POST",
			cache: false,
			async:false,
			url: "${ctx}/manager/admin/CsAjax.do",
			data: "method=getJybDeptListByFgsId&fgs_dept_id=" + $("#fgs_dept_id").val(),
			dataType: "json",
			error: function(request, settings){},
			success: function(data) {
				if (data.length >= 1) {
					var opt1 = new Option( "请选择...",""); 
					$("#jyb_dept_id").get(0).options.add(opt1);

					var opt2 = new Option( "请选择...",""); 
					$("#bsc_dept_id").get(0).options.add(opt2);
					
					for(var i = 0; i < data.length - 1; i++) {
						var opt = new Option( data[i].name,data[i].id); 
						$("#jyb_dept_id").get(0).options.add(opt);
					}
					$("#jyb_dept_id option[value='"+jyb_value+"']").attr("selected",true);
				}
			}
		});
   	}
}

function jyb_chage(){
	var dept_id = $("#jyb_dept_id").val();
	var bsc_value = '${af.map.bsc_dept_id}';
	$("#bsc_dept_id").empty();
   	if(""==dept_id){
   		var fgs_dept_id = $("#fgs_dept_id").val();
		   var opt = new Option( "请选择...",""); 
			$("#bsc_dept_id").get(0).options.add(opt);
		}
   	else if(dept_id!=""){
	   	$.ajax({
			type: "POST",
			cache: false,
			async:false,
			url: "${ctx}/manager/admin/CsAjax.do",
			data: "method=getBscDeptListByJybId&jyb_dept_id=" + $("#jyb_dept_id").val(),
			dataType: "json",
			error: function(request, settings){},
			success: function(data) {
				if (data.length >= 1) {
					var opt = new Option( "请选择...",""); 
					$("#bsc_dept_id").get(0).options.add(opt);
					
					for(var i = 0; i < data.length - 1; i++) {
						var opt = new Option( data[i].name,data[i].id); 
						$("#bsc_dept_id").get(0).options.add(opt);
					}
					$("#bsc_dept_id option[value='"+bsc_value+"']").attr("selected",true);
				}
			}
		});
   	}
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>