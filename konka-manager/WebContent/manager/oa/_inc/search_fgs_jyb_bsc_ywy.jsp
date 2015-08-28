<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<strong class="fb">发件部门：</strong>
<html-el:select property="fgs_dept_id" styleId="fgs_dept_id" >
  <html-el:option value="">请选择分公司</html-el:option>
  <c:forEach var="cur" items="${sybDeptInfoList}">
    <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
  </c:forEach>
</html-el:select>
<html-el:select property="jyb_dept_id" styleId="jyb_dept_id" >
  <html-el:option value="">请选择经营部</html-el:option>
</html-el:select>
<html-el:select property="bsc_dept_id" styleId="bsc_dept_id" >
  <html-el:option value="">请选择办事处</html-el:option>
</html-el:select>
<html-el:select property="ywy_user_id" styleId="ywy_user_id" >
  <html-el:option value="">请选择业务员</html-el:option>
</html-el:select>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#fgs_dept_id").change( function() {
		var dept_id = $("#fgs_dept_id").val();
		$("#jyb_dept_id").empty();
		$("#bsc_dept_id").empty();
	   	$("#ywy_user_id").empty();

		if(""==dept_id){
	   		var opt1 = new Option( "请选择...",""); 
	   		var opt2 = new Option( "请选择...",""); 
	   		var opt3 = new Option( "请选择...",""); 
			$("#jyb_dept_id").get(0).options.add(opt1);
			$("#bsc_dept_id").get(0).options.add(opt2);
			$("#ywy_user_id").get(0).options.add(opt3);
		   	}
	   	if(dept_id!=""){
		   	$.ajax({
				type: "POST",
				cache: false,
				url: "${ctx}/manager/admin/CsAjax.do",
				data: "method=getJybDeptListByFgs&fgs_dept_id=" + $("#fgs_dept_id").val(),
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
						<c:if test="${empty af.map.jyb_dept_id }">getYwyUserListByDept(dept_id);</c:if>
					}
				}
			});
	   	}
	   	
	});
	$("#jyb_dept_id").change( function() {
		var dept_id = $("#jyb_dept_id").val();
		$("#bsc_dept_id").empty();
	   	$("#ywy_user_id").empty();

	   	if(""==dept_id){
	   		var fgs_dept_id = $("#fgs_dept_id").val();
			getYwyUserListByDept(fgs_dept_id);
				
			   var opt = new Option( "请选择...",""); 
				$("#bsc_dept_id").get(0).options.add(opt);
			}
	   	else if(dept_id!=""){
		   	$.ajax({
				type: "POST",
				cache: false,
				url: "${ctx}/manager/admin/CsAjax.do",
				data: "method=getBscDeptListByJyb&jyb_dept_id=" + $("#jyb_dept_id").val(),
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
							<c:if test="${empty af.map.bsc_dept_id }">getYwyUserListByDept(dept_id);</c:if>
					}
				}
			});
	   	}
	   	
	});

	
	$("#bsc_dept_id").change( function() {
		$("#ywy_user_id").empty();

		var dept_id = $("#bsc_dept_id").val();
		if(dept_id!=null){
			getYwyUserListByDept(dept_id);
		}
		if(""==dept_id){
			
			var fgs_dept_id = $("#fgs_dept_id").val();
			var jyb_dept_id = $("#jyb_dept_id").val();
			 	if(jyb_dept_id!=""){
			 		getYwyUserListByDept(jyb_dept_id);
				 }
			 	else if(jyb_dept_id=="" && fgs_dept_id!=null){
			 		getYwyUserListByDept(fgs_dept_id);
				 	}
		}
	});
	
	<c:if test="${not empty af.map.fgs_dept_id }">
		$("#fgs_dept_id").val("${af.map.fgs_dept_id}");
		$("#fgs_dept_id").change();
	</c:if>
	
});

function getYwyUserListByDept(dept_id){
	$.ajax({
		type: "POST",
		cache: false,
		url: "${ctx}/manager/admin/CsAjax.do",
		data: "method=getYwyUserListByDept&dept_id=" + dept_id,
		dataType: "json",
		error: function(request, settings){
			alert("error");
		},
		success: function(data) {
			if (data.length >= 1) {
				var opt = new Option( "请选择...",""); 
				$("#ywy_user_id").get(0).options.add(opt);
				for(var i = 0; i < data.length - 1; i++) {
					var opt = new Option( data[i].name,data[i].id); 
					$("#ywy_user_id").get(0).options.add(opt);
				}
				<c:if test="${not empty af.map.ywy_user_id }">$("#ywy_user_id").val("${af.map.ywy_user_id}");</c:if>
			}
		}
	});
}

//]]></script>