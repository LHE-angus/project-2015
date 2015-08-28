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
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/spgl/EcCust" method="post">
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>所属组织：</td>
          <td width="88%" align="left">
          	<html-el:select property="group_id" styleId="group_id" >
                <html-el:option value="">请选择...</html-el:option>
                <c:forEach var="cur" items="${groupList}"> 
                  <html-el:option value="${cur.id}">${cur.group_name}</html-el:option>
                </c:forEach>
              </html-el:select>  
          
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>客户名称：</td>
          <td width="88%" align="left"><html-el:text property="cust_name" styleId="cust_name" size="30" maxlength="20"/></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>客户编码：</td>
          <td width="88%" align="left"><html-el:text property="cust_code" styleId="cust_code" size="30" maxlength="15"/><span style="color:#f00;display:none;" id="user_name_exist_error">该客户编码已被使用，请重新输入！</span><span style="color:#f00;display:none;" id="user_name_erro">客户编码不能含空白字符！</span>
          </td>
        </tr>
        <tr id="t1" style="display: none;">
          <td width="12%" nowrap="nowrap" class="title_item" align="right">R3编码：</td>
          <td width="88%" align="left">
	           <html-el:text readonly="true" property="r3_code_show" styleId="r3_code_show" value="${af.map.r3_code_show}" style="width:250px;" size="60" maxlength="60" />  
		       <html-el:hidden property="r3_code" styleId="r3_code" value="${af.map.r3_code}"  /> 
          </td>
        </tr>
        <tr style="display: none;">
            <td width="12%" nowrap="nowrap" class="title_item" align="right">所属组织：</td>
            <td width="88%" align="left"><html-el:select property="dept_id" styleId="dept_id" >
                <html-el:option value="">请选择...</html-el:option>
                <c:forEach var="cur" items="${sybDeptInfoList}">
                  <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
                </c:forEach>
              </html-el:select>
            </td>
          </tr>
          <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>客户类型：</td>
          <td width="88%" align="left">
          	<html-el:select property="cust_type" styleId="cust_type" styleClass="cust_type" >
                <html-el:option value="">请选择...</html-el:option>
                <html-el:option value="0">R3客户</html-el:option>
                <html-el:option value="1">虚拟客户</html-el:option>
              </html-el:select>
          </td>
          </tr>
          <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">备注：</td>
          <td width="88%" align="left"><html-el:text property="remark" styleId="remark" size="100" maxlength="80"/>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><html-el:button property="" value="提交" styleClass="but4" styleId="btn_submit" />
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();return false;" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=discuz"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#cust_name").attr("datatype", "Require").attr("msg", "请填写会员名称");
	$("#cust_code").attr("datatype", "Require").attr("msg", "请填写会员编码");
	$("#cust_type").attr("datatype", "Require").attr("msg", "请填写客户类型");
	
	<c:if test="${not empty af.map.id}">
	var cc = '${af.map.cust_type}';
	if(cc == "0"){
		$("#t1").show();
	}
	</c:if>

//条件切换JS代码判断cust_type是否等于1，是隐藏，否也隐藏。
	$(".cust_type").change(function(){
		if($(this).val()=="0"){
			$("#t1").show();
		}else if($(this).val()=="1"){
			$("#t1").hide();
		}else{
			$("#t1").hide(); 
		}  
	});

	// 选择客户
	$("#r3_code_show").click(function(){   
		var fgs_id = $("#group_id").val();  
		if(""==fgs_id || null==fgs_id){
			alert("请选择所属组织");
			return;
		}    
		
		$.dialog({
			title:  "选择R3客户",
			width:  550,
			height: 452,
			left:'60%', 
			top:'40%',  
	        lock:true ,
			content:"url:${ctx}/manager/spgl/EcCust.do?method=listCustomer&fgs_id="+fgs_id+"&id=${af.map.id}"
		});
	});

	$("#btn_submit").click(function(){
		var cust_type=$("#cust_type").val();
		if(cust_type=="0"){
			var r3_code=$("#r3_code").val();
			if(r3_code==""){
				alert("r3客户r3编码不能为空！");
				return false;
			}
		}
		var isSubmit = Validator.Validate(this.form, 3);
		if (isSubmit) {
			$(":button").attr("disabled", "true");
			$(":reset").attr("disabled", "true");
			this.form.submit();
		}
	});


	// 验证用户名是否存在
	$("#cust_code").blur(function(){
		$("#btn_submit").attr("disabled", "disabled");
		var cust_code = $("#cust_code").val();
		if(null == $(this).val() || $(this).val() == ''){
			$("#user_name_exist_error").hide();
			$("#user_name_erro").hide();
			$(this).css("background-color", "#fff");
			return ;
		}
		if($(this).val().indexOf(' ')>-1){
			$("#user_name_exist_error").hide();
			$("#user_name_erro").show();
			return;
		}
		
		$("#user_name_erro").hide();
		$("#user_name_exist_error").hide();
		$(this).css("background-color", "#fff");
		if(cust_code != '${af.map.cust_code}'){
			$.ajax({
				type: "POST",
				url: "EcCust.do",
				data: "method=validateName&cust_code=" + $(this).val(),
				dataType: "json",
				error: function(request, settings) {
					alert("检查客户编码重复失败，请稍候再次尝试。");
					$("#user_name_exist_error").show();
					$(this).css("background-color", "#ddcc00");
					$(this).focus();
				},
				success: function(oper) {
					if (oper.result) {
						alert("该客户编码已存在！");
						$("#cust_code").val("");
						return;
					} else {
						$("#user_name_exist_error").hide();
						$("#cust_code").css("background-color", "#fff");
						$("#btn_submit").removeAttr("disabled");
					}
				}
			});
		}
	});	

	
	
});


function setOnlyInt(obj) {
	$(obj).css("ime-mode", "disabled");
	$(obj).attr("t_value", "");
	$(obj).attr("o_value", "");
	$(obj).bind("dragenter",function(){
		return false;
	});
	$(obj).keypress(function (){
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).keyup(function (){
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).blur(function (){
		if(!obj.value.match(/^(?:\d+(?:\d+)?|\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\d+$/))obj.value=obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
		if(obj.value.length == 0 || isNaN(obj.value) || obj.value == 0) obj.value = "0";
	});
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
