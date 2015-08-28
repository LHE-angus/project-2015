<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${naviString}</title>
<base target="_self" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <html-el:form action="/manager/AgentsList">
      <html-el:hidden property="method" value="save"/>
      <html-el:hidden property="queryString" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="partner_type" styleId="partner_type" value="1" />
      <html-el:hidden property="partner_id" />
      <html-el:hidden property="partner_c_id" styleId="partner_c_id" />
      <html-el:hidden property="partner_obj" styleId="partner_obj" value="1"/>
      <html-el:hidden property="addormodify" styleId="addormodify" value="${addormodify}"/>
      <html-el:hidden property="pagenum" styleId="pagenum" value="${pagenum}"/>
      <html-el:hidden property="local_dept_id" styleId="local_dept_id" value="${local_dept_id}"/>
      <html-el:hidden property="queryStr" styleId="queryStr" value="${queryStr}"/>
      <html-el:hidden property="returnUrl" styleId="returnUrl" value="${returnUrl}"/>
      
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td colspan="4" bgcolor="#CCCCCC" style="font-weight:bold;padding-left:20px;">客户信息</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>上级客户：</td>
          <td align="left" colspan="3" id='partd'>${par_name }</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>名称：</td>
          <td align="left">
          	<c:if test="${not empty af.map.partner_name }">${af.map.partner_name }</c:if>
          	<c:if test="${empty af.map.partner_name }">
	            <html-el:text property="partner_name" size="30" maxlength="20" styleId="partner_name" />
          	</c:if>
          </td>
          <td nowrap="nowrap" class="title_item" align="right">编号：</td>
          <td align="left">
          	<html-el:text property="partner_sn" size="30" maxlength="40" styleId="partner_sn" readonly="true"/>
          </td>
        </tr>
        <tr>
        	<td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>所属分公司/经办：</td>
          	<td align="left">
          		${dept_name }   ${jb_name }
          	</td>
          	<c:if test="${not empty af.map.add_date }">
	          	<td nowrap="nowrap" class="title_item" align="right">创建时间：</td>
	          	<td align="left">
	          		<fmt:formatDate value="${af.map.add_date}" var="_add_date" pattern="yyyy-MM-dd" />
					<html-el:text property="add_date" styleId="add_date" size="10" maxlength="10" value="${_add_date}" readonly="true" onclick="new Calendar(1900, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
	          	</td>	
          	</c:if>
          	<c:if test="${empty af.map.add_date }">
          		<td></td>
          		<td></td>
          	</c:if>
        </tr>
        <tr>
        	<td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>业务员：</td>
          	<td align="left">
          		${ywy_name }
          	</td>
          	<td nowrap="nowrap" class="title_item" align="right" colspan="2"></td>
        </tr>
        <tr>
        	<td nowrap="nowrap" class="title_item" align="right">公司电话：</td>
          	<td align="left"><html-el:text property="partner_company_phone" size="30" maxlength="20" styleId="partner_company_phone" /></td>
          	<td nowrap="nowrap" class="title_item" align="right">公司传真：</td>
          	<td align="left"><html-el:text property="partner_fax" size="30" maxlength="20" styleId="partner_fax" /></td>	
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>所在城市：</td>
          <td align="left" colspan="3">
          	<select name="province" id="province" style="width:180px;">
              <option value="">-请选择省/直辖市/自治区-</option>
            </select>
            <select name="city" id="city" style="width:100px;">
              <option value="">-请选择市-</option>
            </select>
            <select name="country" id="country" style="width:100px;">
              <option value="">-请选择县-</option>
            </select>
            <select name="town" id="town" style="width:100px;">
              <option value="">-请选择乡镇-</option>
            </select>
          </td>
        </tr>
        <tr>
        	<td nowrap="nowrap" class="title_item" align="right">街道地址：</td>
        	<td align="left" colspan="3"><html-el:text property="partner_addr" size="70" maxlength="50" styleId="partner_addr" /></td>
        </tr>
        <tr>
        	<td nowrap="nowrap" class="title_item" align="right">收货地址：</td>
        	<td align="left" colspan="3">
        		<select name="_province" id="_province" style="width:180px;">
	              <option value="">-请选择省/直辖市/自治区-</option>
	            </select>
	            <select name="_city" id="_city" style="width:100px;">
	              <option value="">-请选择市-</option>
	            </select>
	            <select name="_country" id="_country" style="width:100px;">
	              <option value="">-请选择县-</option>
	            </select>
	            <select name="_town" id="_town" style="width:100px;">
	              <option value="">-请选择乡镇-</option>
	            </select>
        		<html-el:text property="consignee_street" size="50" maxlength="50" styleId="consignee_street" />
        	</td>
        </tr>
        <tr>
          <td colspan="4" bgcolor="#CCCCCC" style="font-weight:bold;padding-left:20px;">联系人信息</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>姓名：</td>
          <td align="left"><html-el:text property="link_name" size="30" maxlength="20" styleId="link_name" /></td>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>移动电话：</td>
          <td align="left"><html-el:text property="link_mobile" size="30" maxlength="40" styleId="link_mobile" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">性别：</td>
          <td align="left">
          	<html-el:select property="link_sex" styleId="link_sex">
          		<html-el:option value="">请选择</html-el:option>
          		<html-el:option value="1">男</html-el:option>
          		<html-el:option value="0">女</html-el:option>
          	</html-el:select>
          </td>
          <td nowrap="nowrap" class="title_item" align="right">职务：</td>
          <td align="left"><html-el:text property="link_job" size="30" maxlength="40" styleId="link_job" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">固定电话：</td>
          <td align="left"><html-el:text property="link_tel" size="30" maxlength="20" styleId="link_tel" /></td>
          <td nowrap="nowrap" class="title_item" align="right">QQ/MSN：</td>
          <td align="left"><html-el:text property="link_qq_msn" size="30" maxlength="20" styleId="link_qq_msn" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">证件类型：</td>
          <td align="left"><html-el:select property="link_id_type" styleId="link_id_type">
              <html-el:option value="">请选择</html-el:option>
              <html-el:option value="0">身份证</html-el:option>
              <html-el:option value="1">护照</html-el:option>
              <html-el:option value="2">港澳通行证</html-el:option>
              <html-el:option value="3">台湾通行证</html-el:option>
            </html-el:select></td>
          <td nowrap="nowrap" class="title_item" align="right">证件号码：</td>
          <td align="left"><html-el:text property="link_id" size="30" maxlength="20" styleId="link_id" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">联系地址：</td>
          <td align="left" colspan="3"><html-el:text property="link_addr" size="80" maxlength="120" styleId="link_addr" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">备注：</td>
          <td align="left" colspan="3"><textarea name="memo" rows="3" cols="60" id="memo">${af.map.memo}</textarea>
            <div id="info_tip" style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/styles/jxc/images/tishi.gif" style="vertical-align:middle;" /></div></td>
        </tr>
        <tr>
          <td colspan="4" bgcolor="#CCCCCC" style="font-weight:bold;padding-left:20px;">账户信息</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">
          	<html-el:hidden property="partner_user_id" styleId="partner_user_id" value="${partner_user_info.id}" />
          	<span style="color:red">*</span>&nbsp;用户名：
          </td>
          <td align="left" colspan="3">
          	<c:if test="${empty partner_user_info}">
            	<html-el:text property="user_name" styleId="user_name" size="40" maxlength="20" />
                &nbsp; 
                <span style="color:#f00;display:none;" id="user_name_exist_error">该登录名已被使用，请重新输入！</span>
                <span style="color:#f00;display:none;" id="user_name_erro">登录名不能含空白字符！</span> </c:if>
            <c:if test="${not empty partner_user_info}">${partner_user_info.user_name}</c:if>	
		  </td>
        </tr>
         <tr>
          <td nowrap="nowrap" class="title_item" align="right">
          	<c:if test="${empty partner_user_info}"><span style="color:red">*</span></c:if> 
          		账户${empty partner_user_info ? '' : '新'}密码：
          </td>
          <td align="left" colspan="3">
          	<html-el:password property="pass_word" styleId="pass_word" size="12" maxlength="16" value="${af.map.map.pass_word }"/> 
          	（默认密码为：888888）
          </td>
        </tr>
         <tr>
          <td nowrap="nowrap" class="title_item" align="right">重复密码：</td>
          <td align="left" colspan="3">
          	<html-el:password property="repeat" styleId="repeat" maxlength="16" size="12" value="${af.map.map.pass_word }"/>
          </td>
        </tr>
        <tr style="display:none;">
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>是否删除：</td>
          <td align="left" colspan="3"><html-el:select property="is_del" styleId="is_del">
              <html-el:option value="0" >否</html-el:option>
              <html-el:option value="1">是</html-el:option>
            </html-el:select></td>
        </tr>
        <tr>
          <td colspan="4" align="center"><html-el:button property="" value="提 交" styleClass="but4" styleId="btn_submit" />&nbsp;&nbsp;&nbsp;
            <html-el:button property="" value="重 置" styleClass="but5" styleId="btn_reset" onclick="this.form.reset();" />&nbsp;&nbsp;&nbsp;
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" 
            	onclick="history.back()" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=idialog"></script> 
<script type="text/javascript">//<![CDATA[

	function fgs_id_jb_name(){
		var fgs_id = $("#dept_id").val();
		var local_dept_id = $("#local_dept_id").val();
		if(local_dept_id==''){
		}else{
			if ($.trim(fgs_id).length == 0) {
				alert("请先选择分公司.");
				return;
			}
		}
		$("#jb_job_id").empty();
		$("<option value=''>-请选择经办-</option>").appendTo($("#jb_job_id"));
		var url = "${ctx}/manager/admin/CsAjax.do?method=getJybDeptListByFgsId2&fgs_dept_id="+$('#dept_id').val();
		$.getJSON(url, function(data) {
			if(data != null){
				$.each(data, function(i, item) {
					var option = $("<option></option>").val(item[1]).text(item[0]);
					option.appendTo($("#jb_job_id"));
				});
				if('${af.map.jb_job_id}' != null || '${af.map.jb_job_id}' != '' ){
					$("#jb_job_id").val('${af.map.jb_job_id}');
				}
			}
		});
		
		$("#ywy_job_id").empty();
		$("<option value=''>-请选择业务员-</option>").appendTo($("#ywy_job_id"));
		var url = "${ctx}/manager/admin/CsAjax.do?method=getYwyUserListByDeptId2&jb_id="+$('#dept_id').val();
		$.getJSON(url, function(data) {
			if(data != null){
				$.each(data, function(i, item) {
					var option = $("<option></option>").val(item[1]).text(item[0]);
					option.appendTo($("#ywy_job_id"));
				});
				if('${af.map.ywy_job_id}' != null || '${af.map.ywy_job_id}' != '' ){
					$("#ywy_job_id").val('${af.map.ywy_job_id}');
				}
			}
		});
	} 
	
	
$(document).ready(function(){
	fgs_id_jb_name();
	
	var partner_c_id = $("#partner_c_id").val();
	if(partner_c_id == ""){
		$("#is_r3").val("0");
	} else {
		$("#is_r3").val("1");
	}

	var p_value = '${partner_user_info.id}';
	
	$("#is_r3").attr("dataType", "Require").attr("msg", "不能为空！");
	$("#partner_name").attr("dataType", "Require").attr("msg", "不能为空！");
	$("#partner_obj" ).attr("dataType", "Require").attr("msg", "不能为空！");
	$("#is_del" ).attr("dataType", "Require").attr("msg", "不能为空！");
	$("#link_name" ).attr("dataType", "Require").attr("msg", "不能为空！");
	$("#link_mobile" ).attr("dataType", "Require").attr("msg", "不能为空！");
	$("#c_id").attr("dataType", "Require").attr("msg", "请选择！");
	$("#province").attr("dataType", "Require").attr("msg", "请选择");
	$("#dept_id").attr("dataType", "Require").attr("msg", "请选择");
	$("#ywy_job_id").attr("dataType", "Require").attr("msg", "请选择");
	
	if(p_value.length == 0){
		$("#partner_user_id").val("1");
	}	
	
	$("#btn_submit").click(function(){
		if($("#is_r3").val() == "1" && $("#partner_c_id").val() == ""){
			alert("请选择关联R3网点！");
			return;
		}
		
		//判断密码是否相等
		if($("#pass_word").val()!=$("#repeat").val()){
			alert("两次输入的密码不一致");
			return;
		}
		if(Validator.Validate(this.form, 3)){
			$("#tr_model").remove();
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_reset").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});


	// 验证用户名是否存在
	$("#user_name").blur(function(){
		$("#btn_submit").attr("disabled", "disabled");
		var user_name = $("#user_name").val();
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
		if(user_name != '${af.map.user_name}'){
			$.ajax({
				type: "POST",
				url: "AgentsList.do",
				data: "method=validateName&user_name=" + $(this).val(),
				dataType: "json",
				error: function(request, settings) {
					alert("检查用户名重复失败，请稍候再次尝试。");
					$("#user_name_exist_error").show();
					$(this).css("background-color", "#ddcc00");
					$(this).focus();
				},
				success: function(oper) {
					if (oper.result) {
						alert("该用户名已存在！");
						$("#user_name").val("");
						return;
					} else {
						$("#user_name_exist_error").hide();
						$("#user_name").css("background-color", "#fff");
						$("#btn_submit").removeAttr("disabled");
					}
				}
			});
		}
	});	

	$("#memo").textbox({
		maxLength: 120,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/styles/jxc/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_tip").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_tip").slideUp("normal");
	});
	
	$("#is_r3").change(function(){
		var val = $(this).val();
		if(val == "1"){
		   $(this).next().attr('readonly','readonly');
		   $.dialog({
				title:  "关联网点",
				width:  680,
				height: 425,
				max:    false,  
	            min:    false,  
	            lock:   true ,
				content:"url:${ctx}/manager/admin/JBasePartner.do?method=jxslist"
		   });
		} else {
			$("#partner_c_id").val("");
			$(this).next().removeAttr('readonly');
		}
		resetSn();
	});

	
	$("#partner_obj").change(genSN);
	
	function genSN(){
		var now = new Date();
		var year = now.getFullYear();
		var month = now.getMonth()+1;
		var day= now.getDate();
		var sn = "WD-"+year;
		if(month<10) sn += "0";
		sn += month+"";
		if(day<10) sn += "0";
		sn += day +"";
		$('#partner_sn').val(sn+now.getTime().toString().substr(-4));
	}
	fgs_id_jb_name();
	
	//处理新增与修改时的显示
	var addormodify = $("#addormodify").val();
	if(addormodify!='1'){  //新增
		genSN();
		$("#sn_msg").show();
		$(".passtr").show();
		$("#user_name").attr("dataType", "Require").attr("msg", "用户名不能为空");
		$("#pass_word").attr("dataType", "Require").attr("msg", "密码不能为空");
		$("#repeat" ).attr("datatype", "Repeat" ).attr("to", "pass_word").attr("msg", "两次输入的密码不一致");
		$("#pass_word").val("888888");
		$("#repeat").val("888888");
	}else{  //修改
		<c:if test="${empty partner_user_info}">
			$(".passtr").show();
			$("#user_name").attr("dataType", "Require").attr("msg", "用户名不能为空");
			$("#pass_word").attr("dataType", "Require").attr("msg", "密码不能为空");
			$("#repeat" ).attr("datatype", "Repeat" ).attr("to", "pass_word").attr("msg", "两次输入的密码不一致");
		</c:if>
		<c:if test="${not empty partner_user_info}">
			$(".passtr").hide();
        </c:if>
		$("#add_date").attr("disabled",true);
		$("#ywy_name").attr("disabled",true);
		$("#partner_sn").attr("disabled",true);
		$("#sn_msg").hide();
	}
	
	/* //从页面单击新增按钮进入时
	if(addormodify=='2'){
		$("#par_c_id").remove();
		var input_text = "<input type='text' readonly='true' name='par_c_id_text' id='par_c_id_text' style='width:150px;' size='30' maxlength='30' />";
		var input_hide = "<input type='hidden' name='par_c_id' id='par_c_id' style='width:150px;' size='30' maxlength='30' />";
		$("#partd").append(input_text,input_hide);
		
		$("#par_c_id_text").click(function(){
			var returnValue = window.showModalDialog("AgentsList.do?method=listCustomer&fgs_id="+$("#local_dept_id").val()+"&ts=" + Math.random(), window, "dialogWidth:800px;status:no;dialogHeight:600px");
			if (!returnValue) {
				returnValue = window.returnValue;
			}
			$(this).val(returnValue.cust_name);
			$("#par_c_id").val(returnValue.cust_id);
			$("#r3_id").val(returnValue.cust_id);
		});
	} */
	
	//所在地市联动
	$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${province}"});
	$("#city"    ).attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${city}"});
	$("#country" ).attr({"subElement": "town","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${country}"});
	$("#town" ).attr({"defaultText": "-请选择乡镇-", "defaultValue": "", "selectedValue": "${town}"});
	$("#province").cs("${ctx}/customer/manager/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false);
	$("#province").change();
	
	//收货人所在地市联动
	$("#_province").attr({"subElement": "_city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${_province}"});
	$("#_city"    ).attr({"subElement": "_country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${_city}"});
	$("#_country" ).attr({"subElement": "_town","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${_country}"});
	$("#_town" ).attr({"defaultText": "-请选择乡镇-", "defaultValue": "", "selectedValue": "${_town}"});
	$("#_province").cs("${ctx}/customer/manager/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false);
	$("#province").change();
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
