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
    <html-el:form action="/admin/R3UserAssign">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="str_pks" value="${fn:join(af.map.pks,',')}" />
      <html-el:hidden property="dls_r3_id" styleId="dls_r3_id"/>
      <html-el:hidden property="ywy_user_id_old" styleId="ywy_user_id_old" value="${af.map.ywy_user_id}" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td width="100%" valign="top" colspan="2"><table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
              <tr>
                <td colspan="2" nowrap="nowrap"><strong>客户信息</strong></td>
                <td nowrap="nowrap"><strong>客户地址</strong></td>
              </tr>
              <c:forEach var="cur" items="${konkaR3ShopList}" varStatus="vs">
                <tr>
                  <td width="5%" nowrap="nowrap" class="title_item" align="right">客户名称： </td>
                  <td width="30%" align="left"><c:out value="${cur.customer_name}" /></td>
				  <td width="65%">
						<select name="province_${cur.id}" id="province_${cur.id}" class="class_province" style="width:180px;">
				        	<option value="">-请选择省/直辖市/自治区-</option>
				        </select>
						<select name="city_${cur.id}" id="city_${cur.id}" style="width:100px;">
							<option value="">-请选择市-</option>
						</select>
						<select name="country_${cur.id}" id="country_${cur.id}"  style="width:100px;">
							<option value="">-请选择县-</option>
						</select>
						<select name="town_${cur.id}" id="town_${cur.id}" style="width:100px;">
							<option value="">-请选择乡镇-</option>
						</select>
				  </td>
                </tr>
              </c:forEach>
            </table></td>
          </tr>
          <tr>
          <td width="50%" valign="top"><table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
              <tr>
                <td colspan="2" nowrap="nowrap"><strong>客户分配</strong></td>
              </tr>
              <tr>
                <td nowrap="nowrap" width="100" class="title_item" align="right">分公司：</td>
                <td><html-el:select property="fgs_dept_id" styleId="fgs_dept_id">
                    <html-el:option value="">请选择...</html-el:option>
                    <c:forEach var="cur" items="${sybDeptInfoList}">
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
                <td nowrap="nowrap" class="title_item" align="right">业务员：</td>
                <td><html-el:select property="ywy_user_id" styleId="ywy_user_id">
                    <html-el:option value="">请选择...</html-el:option>
                  </html-el:select></td>
              </tr>
            </table>
            </td>
            <td width="50%" valign="top">
            <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
              <tr>
                <td colspan="2" nowrap="nowrap"><strong>客户分类</strong></td>
              </tr>
              <tr>
                <td nowrap="nowrap" width="20%" align="right"></td>
                <td><label><html-el:radio property="category_id" styleId="category_id_1" value="-1">普通客户</html-el:radio></label></td>
              </tr>
              <tr>
                <td nowrap="nowrap" align="right"></td>
                <td><label><html-el:radio property="category_id" styleId="category_id_2" value="10">是代理商</html-el:radio></label></td>
              </tr>
              <tr>
                <td nowrap="nowrap" align="right"></td>
                <td><label><html-el:radio property="category_id" styleId="category_id_3" value="20">是经销商</html-el:radio></label></td>
              </tr>
               <tbody id="r3_shop" style="display:none;">
               <tr>
                <td nowrap="nowrap" align="left"><strong>选择上级代理商： </strong></td>
                <td>
               <html-el:text property="customer_name" styleId="customer_name" style="width:60%;"  maxlength="100" readonly="true" />
             	&nbsp;<input id="gsBTN" type='button' value='选择' class="but1" onclick="getR3Shop();"/>
            	<html-el:hidden property="r3_shop_id" styleId="r3_shop_id" />
               </td>
            </tr>
               </tbody>
            </table></td>
        </tr>
      </table>
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td align="center">
              <input class="but4" type="button" name="save" id="send" value="提交" />&nbsp;&nbsp;&nbsp;&nbsp;
              <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();" />
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#fgs_dept_id").change( function() {
		var dept_id = $("#fgs_dept_id").val();
		$("#jyb_dept_id").empty();
		$("#bsc_dept_id").empty();
	   	$("#ywy_user_id").empty();

		if(""==dept_id||dept_id==null){
	   		var opt1 = new Option( "请选择...",""); 
	   		var opt2 = new Option( "请选择...",""); 
	   		var opt3 = new Option( "请选择...",""); 
			$("#jyb_dept_id").get(0).options.add(opt1);
			$("#bsc_dept_id").get(0).options.add(opt2);
			$("#ywy_user_id").get(0).options.add(opt3);
		   	}
		else{
		   	$.ajax({
				type: "POST",
				cache: false,
				url: "${ctx}/manager/admin/CsAjax.do",
				data: "method=getJybDeptListByFgsId&fgs_dept_id=" + $("#fgs_dept_id").val(),
				dataType: "json",
				error: function(request, settings){},
				success: function(data) {
					var opt1 = new Option( "请选择...",""); 
					$("#jyb_dept_id").get(0).options.add(opt1);
					var opt2 = new Option( "请选择...",""); 
					$("#bsc_dept_id").get(0).options.add(opt2);
					var opt3 = new Option( "请选择...",""); 
					$("#bsc_dept_id").get(0).options.add(opt3);
					if (data.length >= 1) {
						
						for(var i = 0; i < data.length - 1; i++) {
							var opt = new Option( data[i].name,data[i].id); 
							$("#jyb_dept_id").get(0).options.add(opt);
						}
						<c:if test="${not empty af.map.jyb_dept_id }">
							$("#jyb_dept_id").val("${af.map.jyb_dept_id}");
							$("#jyb_dept_id").change();
						</c:if>
						<c:if test="${empty af.map.jyb_dept_id }">
							getYwyUserListByDeptId(dept_id);
						</c:if>
					}
				}
			});
	   	}
	   	
	});
	$("#jyb_dept_id").change( function() {
		var dept_id = $("#jyb_dept_id").val();
		$("#bsc_dept_id").empty();
	   	$("#ywy_user_id").empty();

	   	if(""==dept_id||dept_id==null){
	   		var fgs_dept_id = $("#fgs_dept_id").val();
			getYwyUserListByDeptId(fgs_dept_id);
				
			   var opt = new Option( "请选择...",""); 
				$("#bsc_dept_id").get(0).options.add(opt);
			}
	   	else{
		   	$.ajax({
				type: "POST",
				cache: false,
				url: "${ctx}/manager/admin/CsAjax.do",
				data: "method=getBscDeptListByJybId&jyb_dept_id=" + $("#jyb_dept_id").val(),
				dataType: "json",
				error: function(request, settings){},
				success: function(data) {
					var opt = new Option( "请选择...",""); 
					$("#bsc_dept_id").get(0).options.add(opt);
					if (data.length >= 1) {
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
		$("#ywy_user_id").empty();

		var dept_id = $("#bsc_dept_id").val();
		if(""==dept_id||dept_id==null){
			
			var fgs_dept_id = $("#fgs_dept_id").val();
			var jyb_dept_id = $("#jyb_dept_id").val();
			 	if(jyb_dept_id!=""){
			 		getYwyUserListByDeptId(jyb_dept_id);
				 }
			 	else if(jyb_dept_id=="" && (fgs_dept_id!=null&&fgs_dept_id!='')){
			 		getYwyUserListByDeptId(fgs_dept_id);
				 	}
		}else{
			getYwyUserListByDeptId(dept_id);
		}
	});
	
	<c:if test="${not empty af.map.fgs_dept_id }">
		$("#fgs_dept_id").val("${af.map.fgs_dept_id}");
		$("#fgs_dept_id").change();
	</c:if>
	
	 $("#send").click(function(){
			var isSubmit = Validator.Validate(this.form, 3);
			if (isSubmit) {
				$(":button").attr("disabled", "true");
				$(":reset").attr("disabled", "true");
				this.form.submit();
			}
		});
	 
	    <c:if test="${af.map.category_id eq 20}">
	    $("#category_id_3").trigger("click");
	    $("#r3_shop").show();$("#customer_name").attr("dataType", "Require").attr("msg", "请选择代理商");
	    </c:if>
	    <c:if test="${af.map.category_id eq 10}">
	    $("#category_id_2").trigger("click");
	    </c:if>
	    <c:if test="${af.map.category_id ne 10 and af.map.category_id ne 20}">
	    $("#category_id_1").trigger("click");
	    </c:if>
	 
	 $("#category_id_1").click(function(){$("#r3_shop").hide();$("#customer_name").attr("dataType", null);});
	 $("#category_id_2").click(function(){$("#r3_shop").hide();$("#customer_name").attr("dataType", null);});
	 $("#category_id_3").click(function(){$("#r3_shop").show();$("#customer_name").attr("dataType", "Require").attr("msg", "请选择代理商");});

	 	//所在地市联动
	 	var v_province = "";
	 	var v_city = "";
	 	var v_country = "";
	 	var v_town = "";
	 	var v_p_index = "";
	 	var v_id = "";
	 	<c:forEach var="cur" items="${konkaR3ShopList}" varStatus="vs">
		v_id = "${cur.id}";
		v_p_index = "${cur.entp_p_index}";
		if(v_p_index.length>=6){
		 	v_province = v_p_index.substr(0,2)+"0000";
		 	v_city = v_p_index.substr(0,4)+"00";
			v_country = v_p_index.substr(0,6);
			v_town = v_p_index;
		}else{
			v_province = "";
		 	v_city = "";
			v_country = "";
			v_town = "";
		}
		$("#province_"+v_id).attr({"subElement": "city_"+v_id, "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": v_province});
		$("#city_"+v_id    ).attr({"subElement": "country_"+v_id, "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": v_city});
		$("#country_"+v_id ).attr({"subElement": "town_"+v_id,"defaultText": "-请选择县-", "defaultValue": "", "selectedValue": v_country, "dataType":"Require","msg":"请选择县！"});
		$("#town_"+v_id ).attr({"defaultText": "-请选择乡镇-", "defaultValue": "", "selectedValue": v_town});
		$("#province_"+v_id).cs("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false);
		</c:forEach>
});

function getYwyUserListByDeptId(dept_id){
	$.ajax({
		type: "POST",
		cache: false,
		url: "${ctx}/manager/admin/CsAjax.do",
		data: "method=getYwyUserListByDeptId&dept_id=" + dept_id,
		dataType: "json",
		error: function(request, settings){
			alert("查询出错了！");
		},
		success: function(data) {
			var opt = new Option( "请选择...",""); 
			$("#ywy_user_id").get(0).options.add(opt);
			if (data.length >= 1) {
				for(var i = 0; i < data.length - 1; i++) {
					var opt = new Option( data[i].name,data[i].id); 
					$("#ywy_user_id").get(0).options.add(opt);
				}
				<c:if test="${not empty af.map.ywy_user_id }">$("#ywy_user_id").val("${af.map.ywy_user_id}");</c:if>
			}
		}
	});
}

function getPeShopCategoryInfo(){
	if ($('input:radio[name=category_id]:checked').val() == 10) {
		var returnValue = window.showModalDialog("PeShopCategory.do?method=listForLevel&is_assign=true&selects=" + $("#peShopCategoryId").val() +  "&azaz=" + Math.random(),window,"dialogWidth:600px;status:no;dialogHeight:500px");
	} else {
		var returnValue = window.showModalDialog("PeShopCategory.do?method=listForLevel&is_assign=false&selects=" + $("#peShopCategoryId").val() +  "&azaz=" + Math.random(),window,"dialogWidth:600px;status:no;dialogHeight:500px");
	}
	if (returnValue != null) {
		$("#peShopCategoryId").val(returnValue.ids);
		$("#peShopCategoryName").val(returnValue.names);
	};	
}

//选择申请客户
function getR3Shop() { 
	    var dls_r3_id;
	    <c:if test="${not empty af.map.dls_r3_id}">
	    	dls_id=$("#dls_r3_id").val();
	    </c:if>
	    <c:if test="${empty af.map.dls_r3_id}">
	   		 dls_id="";
	    </c:if>
	    var returnValue = window
	            .showModalDialog(
	                    "SelectKonkaR3Shop.do?selectype=signal&category_r3=1&selects=" + $("#r3_shop_id").val() + "&dls_r3_id="+ dls_id +"&azaz=" + Math.random(),
	                    window,
	                    "dialogWidth:600px;status:no;dialogHeight:530px");
	    if (null != returnValue) {
	        $("#r3_shop_id").val(returnValue.ids);
	        $("#customer_name").val(returnValue.names);
	    };
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
