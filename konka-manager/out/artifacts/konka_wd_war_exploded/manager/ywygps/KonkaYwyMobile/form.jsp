<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
      <html-el:form action="/ywygps/KonkaYwyMobile">
        <html-el:hidden property="method" value="save" />
        <html-el:hidden property="mod_id" />
        <html-el:hidden property="id" value="${af.map.id}"/>
        <html-el:hidden property="queryString" />
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
           <tr class="tabtt1">
               <td colspan="2" align="center">
               <c:if test="${empty af.map.id}">添加业务员手机</c:if>
               <c:if test="${not empty af.map.id}">修改业务员手机</c:if>
               </td>
           </tr>
           <tr>
            <td width="18%" class="title_item" nowrap="nowrap" align="right"><strong>手机号：</strong></td>
            <td align="left" >           
                	<html-el:text property="mobile_no" styleId="mobile_no" maxlength="11" size="80" style="width:240px;"/>
                	&nbsp;<span style="color:red">*</span><span style="color:#f00;display:none;" id="mobile_no_exist_error">该手机号已在使用中，请停用该手机号或输入其他手机号！</span>
                	<span style="color:#f00;display:none;" id="mobile_no_erro">手机号不能含空白字符！</span>
            </td>
          </tr>
          <tr>
            <td width="18%" class="title_item" nowrap="nowrap" align="right"><strong>关联业务员：</strong></td>
            <td align="left" >分公司：<html-el:select property="fgs_dept_id" styleId="fgs_dept_id" style="width:120px;">
                    <html-el:option value="">请选择...</html-el:option>
                    <c:forEach var="cur" items="${sybDeptInfoList}">
                      <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
                    </c:forEach>
                    </html-el:select><br/>
                                                    经营部：<html-el:select property="jyb_dept_id" styleId="jyb_dept_id" style="width:120px;">
                       <html-el:option value="">请选择...</html-el:option>
                    </html-el:select><br/>
                                                   办事处：<html-el:select property="bsc_dept_id" styleId="bsc_dept_id" style="width:120px;">
                       <html-el:option value="">请选择...</html-el:option>
                    </html-el:select><br/>
                                                   业务员：<html-el:select property="user_id" styleId="user_id" style="width:120px;">
                      <html-el:option value="">请选择...</html-el:option>
                    </html-el:select>
            </td>
          </tr>
          <tr style="display:none;">
            <td width="20%" align="right"><strong>确认公司：</strong></td>
            <td><html-el:select property="entp_crc" styleId="entp_crc">
                    <c:forEach var="cur" items="${entpList}">
                      <html-el:option value="${cur.id}_${cur.entp_crc}">${cur.entp_name}</html-el:option>
                    </c:forEach>
                </html-el:select>
            </td>
          </tr>
          <tr>
            <td width="20%" align="right"><strong>月度设置方案：</strong></td>
            <td><html-el:select property="setplan_crc" styleId="setplan_crc">
                    <html-el:option value="">请选择...</html-el:option>
                </html-el:select>
            </td>
          </tr>
          <tr>
            <td colspan="2" align="center">
              <html-el:button value=" 保存 " styleId="btn_submit" property="btn_submit" styleClass="but4" />
              &nbsp;&nbsp;<html-el:button property="back" value=" 返回 " onclick="history.back();" styleId="btn_back" styleClass="but5"/></td>
          </tr>
        </table>
      </html-el:form>
    </div>

<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#mobile_no").attr("datatype", "Require").attr("msg", "请输入业务员手机号！");
	$("#user_id").attr("datatype", "Require").attr("msg", "请选择关联的公司业务员！");
	$("#entp_crc").attr("datatype", "Require").attr("msg", "请选择确认公司！");
	$("#setplan_crc").attr("datatype", "Require").attr("msg", "请选择月度设置方案！");
		
	$("#fgs_dept_id").change( function() {
		var dept_id = $("#fgs_dept_id").val();
		$("#jyb_dept_id").empty();
		$("#bsc_dept_id").empty();
	   	$("#user_id").empty();

		if(""==dept_id){
	   		var opt1 = new Option( "请选择...",""); 
	   		var opt2 = new Option( "请选择...",""); 
	   		var opt3 = new Option( "请选择...",""); 
			$("#jyb_dept_id").get(0).options.add(opt1);
			$("#bsc_dept_id").get(0).options.add(opt2);
			$("#user_id").get(0).options.add(opt3);
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
						<c:if test="${not empty af.map.jyb_dept_id }">$("#jyb_dept_id").val("${af.map.jyb_dept_id}");$("#jyb_dept_id").change();</c:if>
						<c:if test="${empty af.map.jyb_dept_id }">getYwyUserListByDeptId(dept_id);</c:if>
					}
				}
			});
	   	}
	   	
	});
	$("#jyb_dept_id").change( function() {
		var dept_id = $("#jyb_dept_id").val();
		$("#bsc_dept_id").empty();
	   	$("#user_id").empty();

	   	if(""==dept_id){
	   		var fgs_dept_id = $("#fgs_dept_id").val();
			getYwyUserListByDeptId(fgs_dept_id);
				
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
							<c:if test="${not empty af.map.bsc_dept_id }">$("#bsc_dept_id").val("${af.map.bsc_dept_id}");$("#bsc_dept_id").change();</c:if>
							<c:if test="${empty af.map.bsc_dept_id }">getYwyUserListByDeptId(dept_id);</c:if>
					}
				}
			});
	   	}
	   	
	});

	
	$("#bsc_dept_id").change( function() {
		$("#user_id").empty();

		var dept_id = $("#bsc_dept_id").val();
		if(dept_id!=null){
			getYwyUserListByDeptId(dept_id);
		}
		if(""==dept_id){
			
			var fgs_dept_id = $("#fgs_dept_id").val();
			var jyb_dept_id = $("#jyb_dept_id").val();
			 	if(jyb_dept_id!=""){
			 		getYwyUserListByDeptId(jyb_dept_id);
				 }
			 	else if(jyb_dept_id=="" && fgs_dept_id!=null){
			 		getYwyUserListByDeptId(fgs_dept_id);
				 	}
		}
	});
	
	<c:if test="${not empty af.map.fgs_dept_id }">
		$("#fgs_dept_id").val("${af.map.fgs_dept_id}");
		$("#fgs_dept_id").change();
	</c:if>

	//$("#entp_crc").change( function() {
	//	if($("#entp_crc").val() != ''){
			getMonthSetPlan($("#entp_crc").val());
	//	}
	//});

	<c:if test="${not empty af.map.entp_crc }">
	   $("#entp_crc").val("${af.map.entp_id}_${af.map.entp_crc}");
	   $("#entp_crc").change();
    </c:if>
    
	$("#btn_submit").click(function(){
		
		if(Validator.Validate(this.form, 3)){
			$("#btn_submit"   ).attr("disabled",true);
			$("#btn_back"     ).attr("disabled",true);
			
			this.form.submit();
		}
	});

	//验证手机号是否存在
	$("#mobile_no").blur(function(){
		$("#btn_submit").attr("disabled", "disabled");
		var mobile_no = $("#mobile_no").val();
		if(null == $(this).val() || $(this).val() == ''){
			$("#mobile_no_exist_error").hide();
			$("#mobile_no_erro").hide();
			$(this).css("background-color", "#fff");
			return ;
		}
		if($(this).val().indexOf(' ')>-1){
			$("#mobile_no_exist_error").hide();
			$("#mobile_no_erro").show();
			return;
		}
		
		$("#mobile_no_erro").hide();
		$("#mobile_no_exist_error").hide();
		$(this).css("background-color", "#fff");
		
		$.ajax({
			type: "POST",
			url: "KonkaYwyMobile.do",
			data: "method=validateMobile&mobile_no=" + $(this).val(),
			dataType: "json",
			error: function(request, settings) {
				alert("检查手机号失败，请稍候再次尝试。");
				$("#mobile_no_exist_error").show();
				$(this).css("background-color", "#ddcc00");
				$(this).focus();
			},
			success: function(oper) {
				if (oper.result) {
					$("#mobile_no_exist_error").show();
					$("#btn_submit").attr("disabled", "disabled");
					$("#mobile_no").css("background-color", "#ddcc00");
					$("#mobile_no").focus();
				} else {
					$("#mobile_no_exist_error").hide();
					$("#mobile_no").css("background-color", "#fff");
					$("#btn_submit").removeAttr("disabled");
				}
			}
		});
	});	
	
});

function getYwyUserListByDeptId(dept_id){
	$.ajax({
		type: "POST",
		cache: false,
		url: "${ctx}/manager/admin/CsAjax.do",
		data: "method=getYwyUserListByDeptId&dept_id=" + dept_id,
		dataType: "json",
		error: function(request, settings){
			alert("error");
		},
		success: function(data) {
			if (data.length >= 1) {
				var opt = new Option( "请选择...",""); 
				$("#user_id").get(0).options.add(opt);
				for(var i = 0; i < data.length - 1; i++) {
					var opt = new Option( data[i].name,data[i].id); 
					$("#user_id").get(0).options.add(opt);
				}
				<c:if test="${not empty af.map.user_id }">$("#user_id").val("${af.map.user_id}");</c:if>
			}
		}
	});
}

function getMonthSetPlan(entp_crc){
	$.ajax({
		type: "POST",
		cache: false,
		url: "${ctx}/manager/ywygps/KonkaYwyMobile.do",
		data: "method=getMonthSetPlan&id_crc=" + entp_crc,
		dataType: "json",
		error: function(request, settings){
			alert("error");
		},
		success: function(data) {
			$("#setplan_crc").empty();
			var opt = new Option( "请选择...",""); 
			$("#setplan_crc").get(0).options.add(opt);
			if (data.length >= 1) {
				for(var i = 0; i < data.length - 1; i++) {
					var opt = new Option( data[i].name,data[i].setplan_crc); 
					$("#setplan_crc").get(0).options.add(opt);
				}
				<c:if test="${not empty af.map.setplan_crc }">$("#setplan_crc").val("${af.map.setplan_crc}");</c:if>
			}
		}
	});
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
