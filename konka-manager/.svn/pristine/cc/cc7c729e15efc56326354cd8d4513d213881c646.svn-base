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
    <html-el:form action="/admin/KonkaShopDevelop" method="post">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="developType" value="0" />
      <html-el:hidden property="develop_id" value="${af.map.develop_id}" />
      <html-el:hidden property="shop_name_like" value="${af.map.shop_name_like}" />
      <html-el:hidden property="shop_develop_status" value="${af.map.shop_develop_status}" />      
      <html-el:hidden property="shop_visit_status" value="${af.map.shop_visit_status}" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
              <tr>
                <td  nowrap="nowrap"><strong>网点信息</strong></td>
              </tr>
              <c:forEach var="cur" items="${konkaEntpShopList}" varStatus="vs">
                <tr>
                  <td width="12%" nowrap="nowrap" >网点名称：${cur.shop_name}</td>
                </tr>
              </c:forEach>
       </table>
       <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
              <tr>
                <td  nowrap="nowrap"><strong>网点分配</strong></td>
              </tr>
              <tr>
                <td nowrap="nowrap" width="110" align="right">分公司：</td>
                <td><html-el:select property="fgs_dept_id" styleId="fgs_dept_id">
                      <html-el:option value="${fgs_dept.dept_id}">${fgs_dept.dept_name}</html-el:option>
                  </html-el:select>
                </td>
              </tr>
              <tr>
                <td nowrap="nowrap" class="title_item" align="right">经营部或办事处：</td>
                <td><html-el:select property="jyb_dept_id" styleId="jyb_dept_id">
                      <html-el:option value="">请选择...</html-el:option>
                    <c:forEach var="cur" items="${jyb_List}">         
                      <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
                    </c:forEach>
                   </html-el:select>
               </td>
              </tr>
              <tr>
                <td nowrap="nowrap" class="title_item" align="right">业务员：</td>
                <td><html-el:select property="ywy_user_id" styleId="ywy_user_id">
                    <html-el:option value="">请选择...</html-el:option>
                  </html-el:select></td>
             </tr>
      </table>
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td  nowrap="nowrap">
              <input class="but4" type="button" name="save" id="send" value="提交" />
              <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
          </td>
         
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
	$("#jyb_dept_id").change( function() {
		$("#ywy_user_id").empty();
	   	$.ajax({
			type: "POST",
			cache: false,
			url: "${ctx}/manager/admin/CsAjax.do",
			data: "method=getYwyUserListByDeptId&dept_id=" + $("#jyb_dept_id").val(),
			dataType: "json",
			error: function(request, settings){
				alert("error");
			},
			success: function(data) {
				if (data.length >= 1) {
					for(var i = 0; i < data.length - 1; i++) {
						var opt = new Option( data[i].name,data[i].id); 
						$("#ywy_user_id").get(0).options.add(opt);
					}
					<c:if test="${not empty ywy_id }">$("#ywy_user_id").val("${ywy_id}");</c:if>
					<c:if test="${peRoleUser.role_id eq 40 or peRoleUser.role_id eq 50}">
						$("#fgs_dept_id option").not(":selected").attr("disabled", "disabled");
						$("#jyb_dept_id option").not(":selected").attr("disabled", "disabled");
					</c:if>
				}
			}
		});
	});
	$("#jyb_dept_id").val("${peRoleUser.dept_id}");
	if($("#jyb_dept_id").val() != null && $("#jyb_dept_id").val() != ""){
		$("#jyb_dept_id").change();
	}

	 $("#send").click(function(){
			var ywy_user_id = $("#ywy_user_id").val();
			if (ywy_user_id != null && ywy_user_id != "") {
				$(":button").attr("disabled", "true");
				$(":reset").attr("disabled", "true");
				this.form.submit();
			}else{
				alert("请选择业务员");
			}
		});	 
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
