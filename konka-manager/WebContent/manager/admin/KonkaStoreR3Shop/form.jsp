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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/KonkaStoreR3Shop">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="store_id" styleId="${konkaR3Store.store_id}" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td colspan="2" nowrap="nowrap" align="right"><strong>网点信息</strong></td>
        </tr>
        <tr>
          <td nowrap="nowrap" width="100" align="right" class="title_item">网点名称：</td>
          <td>${konkaR3Store.store_name}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" align="right" class="title_item">所属分公司：</td>
          <td>${konkaR3Store.dept_name}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" align="right" class="title_item">详细地址：</td>
          <td>${konkaR3Store.map.p_name}${konkaR3Store.addr}</td>
        </tr>
        <tr>
          <td colspan="2" nowrap="nowrap" align="right"><strong>R3网点关联</strong></td>
        </tr>
        <tr>
          <td nowrap="nowrap" width="100" align="right" class="title_item">分公司：</td>
          <td><html-el:select property="fgs_dept_id" styleId="fgs_dept_id" value="${konkaR3Store.dept_id}">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${konkaDeptList}">
                <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
              </c:forEach>
            </html-el:select></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">经营部：</td>
          <td><html-el:select property="jyb_dept_id" styleId="jyb_dept_id">
              <html-el:option value="">请选择...</html-el:option>
            </html-el:select></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">办事处：</td>
          <td><html-el:select property="bsc_dept_id" styleId="bsc_dept_id">
              <html-el:option value="">请选择...</html-el:option>
            </html-el:select></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">R3网点：</td>
          <td>
          	<input type="hidden" name="r3_code" id="r3_code" />
          	<input type="hidden" name="kh_name" id="kh_name" />
          	<span id="r3_name"></span>
            <input class="but8" type="button" name="Submit5" value="点击选择" onclick="getR3Shop();" style="margin-left:5px;" /></td>
        </tr>
      </table>
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td width="20%"></td>
          <td><label>
              <input class="but4" type="button" name="save" id="send" value="提交" />
              <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
            </label></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	// 判断是否是分公司
	if('${isHeadquarters}' == 'false'){
		$("#fgs_dept_id").attr("disabled", true);
	} 
	
	// 根据网点信息动态显示
	dept_chage();
	
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
	   	$("#ywy_user_id").empty();

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
					}
				}
			});
	   	}
	   	
	});
	
	$("#bsc_dept_id").change( function() {
		$("#ywy_user_id").empty();

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

	$("#r3_code").attr("dataType" , "Require").attr("msg" , "请选择R3网点！");
	
	 $("#send").click(function(){
			var isSubmit = Validator.Validate(this.form, 3);
			if (isSubmit) {
				$(":button").attr("disabled", "true");
				$(":reset").attr("disabled", "true");
				this.form.submit();
			}
	});
});

function dept_chage(){
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
}

//选择申请网点
function getR3Shop() { 
	var fgs_dept_id = $("#fgs_dept_id").val();
	var jyb_dept_id = $("#jyb_dept_id").val();
	var bsc_dept_id = $("#bsc_dept_id").val();
    var returnValue = window.showModalDialog("KonkaR3ShopSelect.do?fgs_dept_id=" + fgs_dept_id + "&jyb_dept_id="+ jyb_dept_id + "&bsc_dept_id="+ bsc_dept_id +"&azaz=" + Math.random(), window, "dialogWidth:750px;status:no;dialogHeight:430px");
    if (null != returnValue) {
      $("#r3_code").val(returnValue.ids);
      $("#r3_name").html(returnValue.names);
      $("#kh_name").val(returnValue.names);
    }
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
