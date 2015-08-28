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
        <td>当前位置：客户进销存 > 客户管理 > 供应商管理</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <%@ include file="/commons/pages/messages.jsp" %>
    <html-el:form action="/manager/JBasePartner">
      <html-el:hidden property="method" value="save"/>
      <html-el:hidden property="queryString" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="partner_type" styleId="partner_type" value="0" />
      <html-el:hidden property="partner_id" />
      <html-el:hidden property="returnUrl" />
      <html-el:hidden property="partner_c_id" styleId="partner_c_id" />
      <html-el:hidden property="type1" styleId="type1" value="0"/>
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td colspan="4" bgcolor="#CCCCCC" style="font-weight:bold;padding-right:30px;">往来单位信息</td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right">类型：</td>
          <td width="35%" nowrap="nowrap" class="title_item" align="left">供应商</td>
          <td width="15%" nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>性质：</td>
          <c:if test="${empty af.map.partner_id}">
            <td width="35%" align="left"><html-el:select property="partner_obj" styleId="partner_obj">
                <html-el:option value="0">个人</html-el:option>
                <html-el:option value="1">组织/单位</html-el:option>
              </html-el:select></td>
          </c:if>
          <c:if test="${not empty af.map.partner_id}">
            <td width="35%" align="left"><c:choose>
                <c:when test="${af.map.partner_obj eq 0}">个人</c:when>
                <c:when test="${af.map.partner_obj eq 1}">组织/单位</c:when>
              </c:choose></td>
          </c:if>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>名称：</td>
          <td align="left"><html-el:select property="is_r3" styleId="is_r3" style="display:none;">
              <html-el:option value="0">非康佳合作客户</html-el:option>
              <html-el:option value="1">是康佳合作客户</html-el:option>
            </html-el:select>
            <html-el:text property="partner_name" size="30" maxlength="20" styleId="partner_name" onblur="if(this.value != '') this.form.link_name.value=this.value;" /></td>
          <td nowrap="nowrap" class="title_item" align="right">编号：</td>
          <td align="left"><html-el:text property="partner_sn" size="30" maxlength="40" styleId="partner_sn" />
            &nbsp;&nbsp;&nbsp;<span style="color:blue;">建议修改后面6位数字</span></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">传真：</td>
          <td align="left" colspan="3"><html-el:text property="partner_fax" size="30" maxlength="40" styleId="partner_fax" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">所在城市：</td>
          <td align="left" colspan="3">
          	<select name="_province" id="_province" style="width:180px;">
              <option value="">-请选择省/直辖市/自治区-</option>
            </select>
            &nbsp;
            <select name="_city" id="_city" style="width:100px;">
              <option value="">-请选择市-</option>
            </select>
            &nbsp;
            <select name="_country" id="_country" style="width:100px;">
              <option value="">-请选择县-</option>
            </select>
            &nbsp;
            <select name="_town" id="_town" style="width:100px;">
              <option value="">-请选择乡镇-</option>
            </select>
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">公司地址：</td>
          <td align="left" colspan="3"><html-el:text property="partner_addr" size="65" maxlength="120" styleId="partner_addr" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">备注：</td>
          <td align="left" colspan="3"><textarea name="memo" rows="3" cols="50" id="memo">${af.map.memo}</textarea>
            <div id="info_tip" style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/styles/jxc/images/tishi.gif" style="vertical-align:middle;" /></div></td>
        </tr>
        <tr>
          <td colspan="4" bgcolor="#CCCCCC" style="font-weight:bold;padding-right:30px;">联系人信息</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">姓名：</td>
          <td align="left"><html-el:text property="link_name" size="30" maxlength="20" styleId="link_name" /></td>
          <td nowrap="nowrap" class="title_item" align="right">移动电话：</td>
          <td align="left"><html-el:text property="link_mobile" size="30" maxlength="40" styleId="link_mobile" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">固定电话：</td>
          <td align="left"><html-el:text property="link_tel" size="30" maxlength="40" styleId="link_tel" /></td>
          <td nowrap="nowrap" class="title_item" align="right">QQ/MSN：</td>
          <td align="left"><html-el:text property="link_qq_msn" size="30" maxlength="40" styleId="link_qq_msn" /></td>
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
          <td align="left"><html-el:text property="link_id" size="30" maxlength="40" styleId="link_id" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">联系人地址：</td>
          <td align="left" colspan="3"><html-el:text property="link_addr" size="80" maxlength="120" styleId="link_addr" /></td>
        </tr>
        <tr>
          <td colspan="4" bgcolor="#CCCCCC" style="font-weight:bold;padding-right:30px;">收货人设置</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">姓名：</td>
          <td align="left"><html-el:text property="consignee_name" size="10" maxlength="10" styleId="consignee_name" /></td>
          <td nowrap="nowrap" class="title_item" align="right">电话：</td>
          <td align="left"><html-el:text property="consignee_tel" size="20" maxlength="20" styleId="consignee_tel" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">地址：</td>
          <td align="left" colspan="3"><html-el:select property="province" styleId="province"></html-el:select>&nbsp;
       		<html-el:select property="city" styleId="city">
       			<html-el:option value="">市</html-el:option>
       		</html-el:select>&nbsp;
       		<html-el:select property="country" styleId="country">
       			<html-el:option value="">区/县</html-el:option>
       		</html-el:select>&nbsp;
       		<html-el:select property="town" styleId="town">
       			<html-el:option value="">乡/镇</html-el:option>
       		</html-el:select>&nbsp;
      		<html-el:text property="consignee_street" size="60" maxlength="100" styleId="consignee_street" /></td>
        </tr>
        <tr style="display: none;">
          <td colspan="4"><table style="display:${(empty partner_user_info and not (af.map.partner_obj eq 1 and fn:indexOf(af.map.partner_type, '1') gt -1)) ? 'none' : ''};" id="userinfo_id" width="100%">
              <tr>
                <td colspan="4" align="right" bgcolor="#CCCCCC" style="font-weight:bold;padding-right:30px;"><c:if test="${empty partner_user_info}">
                    <label style="cursor:pointer;">
                      <input id="input_user" type="checkbox" name="input_user" />
                      &nbsp;创建客户登录帐号？</label>
                  </c:if>
                  <c:if test="${not empty partner_user_info}">客户登录帐号</c:if></td>
              </tr>
              <tbody id="user-info-form" style="display:${empty partner_user_info ? 'none' : ''};">
                <tr>
                  <td nowrap="nowrap" height="28" class="title_item"  align="right" width="15%"><span style="color:red">*</span>&nbsp;用户名：</td>
                  <td><c:if test="${empty partner_user_info}">
                      <html-el:text property="user_name" styleId="user_name" size="40" maxlength="20" />
                      &nbsp; <span style="color:#f00;display:none;" id="user_name_exist_error">该登录名已被使用，请重新输入！</span><span style="color:#f00;display:none;" id="user_name_erro">登录名不能含空白字符！</span> </c:if>
                    <c:if test="${not empty partner_user_info}">${partner_user_info.user_name}
                      <html-el:hidden property="partner_user_id" value="${partner_user_info.id}" />
                    </c:if></td>
                </tr>
                <tr>
                  <td nowrap="nowrap" height="28" class="title_item"  align="right"><span style="color:red">*</span> 账户${empty partner_user_info ? '' : '新'}密码：</td>
                  <td><html-el:password property="pass_word" styleId="pass_word" size="12" maxlength="16" />
                    &nbsp;
                    <input type="button" name="initDefaultPassword" onClick="this.form.pass_word.value='888888';this.form.repeat.value='888888';" value=" 使用默认密码：888888 " /></td>
                </tr>
                <tr>
                  <td nowrap="nowrap" class="title_item"  align="right">重复密码：</td>
                  <td><html-el:password property="repeat" styleId="repeat" maxlength="16" size="12" /></td>
                </tr>
              </tbody>
            </table></td>
        </tr>
        <tr style="display:none;">
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>是否删除：</td>
          <td align="left" colspan="3"><html-el:select property="is_del" styleId="is_del">
              <html-el:option value="0" >否</html-el:option>
              <html-el:option value="1">是</html-el:option>
            </html-el:select></td>
        </tr>
        <tr>
          <td colspan="4" align="center">
          	<html-el:button property="" value="提 交" styleClass="but4" styleId="btn_submit" />&nbsp;&nbsp;
            <html-el:button property="" value="重 置" styleClass="but5" styleId="btn_reset" onclick="this.form.reset();" />&nbsp;&nbsp;
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();" />
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=idialog"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#province").attr({"subElement": "city", "defaultText": "省/市/自治区", "defaultValue": "", "selectedValue": "${af.map.province}"});
	$("#city").attr({"subElement": "country", "defaultText": "市", "defaultValue": "", "selectedValue": "${af.map.city}"});
	$("#country").attr({"subElement": "town","defaultText": "区/县", "defaultValue": "", "selectedValue": "${af.map.country}"});
	$("#town").attr({"defaultText": "请选择乡/镇", "defaultValue": "", "selectedValue": "${af.map.town}"});
	$("#province").cs("${ctx}/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false,"p_index_join","${af.map.p_index_join}");
	$("#province").change();
	
	//所在地市联动
	$("#_province").attr({"subElement": "_city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${af.map._province}"});
	$("#_city"    ).attr({"subElement": "_country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${af.map._city}"});
	$("#_country" ).attr({"subElement": "_town","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${af.map._country}"});
	$("#_town" ).attr({"defaultText": "-请选择乡镇-", "defaultValue": "", "selectedValue": "${af.map._town}"});
	$("#_province").cs("${ctx}/customer/manager/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false);
	$("#_province").change();
	
	var partner_c_id = $("#partner_c_id").val();
	if(partner_c_id == ""){
		$("#is_r3").val("0");
	} else {
		$("#is_r3").val("1");
	}
	$("#partner_type_cb_1").attr("checked", "true");
	
	$("#input_user").click(function(){
		if ($(this).is(":checked")) {
			$("#user_name").attr("dataType", "Require").attr("msg", "用户名不能为空");
			$("#pass_word").attr("dataType", "Require").attr("msg", "密码不能为空");
			$("#repeat" ).attr("datatype", "Repeat" ).attr("to", "pass_word").attr("msg", "两次输入的密码不一致");	
		} else {
			$("#user_name").removeAttr("dataType").removeAttr("msg").val("");
			$("#pass_word").removeAttr("dataType").removeAttr("msg").val("");
			$("#repeat" ).removeAttr("dataType").removeAttr("to").removeAttr("msg").val("");
			
			$("#btn_submit").removeAttr("disabled");
            $("#btn_reset").removeAttr("disabled");
            $("#btn_back").removeAttr("disabled");
		}
		$('#user-info-form').toggle();
		window.parent.resizeFrameHeight('mainFrame', 3);
	});
	
	$("#is_r3"       ).attr("dataType", "Require").attr("msg", "不能为空！");
	$("#partner_name").attr("dataType", "Require").attr("msg", "不能为空！");
	$("#partner_type_cb_0").attr("dataType", "Group").attr("min", "1").attr("msg", "至少选择一种客户类型！");
	$("#partner_obj" ).attr("dataType", "Require").attr("msg", "不能为空！");
	$("#is_del" ).attr("dataType", "Require").attr("msg", "不能为空！");
	$("#link_name" ).attr("dataType", "Require").attr("msg", "不能为空！");
	
	$("#btn_submit").click(function(){
		if($("#is_r3").val() == "1" && $("#partner_c_id").val() == ""){
			alert("请选择关联R3网点！");
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
				url: "JBasePartner.do",
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
		maxLength: 100,
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
				content:"url:${ctx}/customer/manager/JBasePartner.do?method=jxslist"
		   });
		} else {
			$("#partner_c_id").val("");
			$(this).next().removeAttr('readonly');
		}
		resetSn();
	});

	
	$("#partner_obj").change(genSN);
	$("#partner_type_cb_1, #partner_type_cb_0").click(function(){
		genSN();
	});
	
	function genSN(){
		
		var sn = ['GYS', new Date().getTime().toString().substr(-6)].join('-');
		
		$('#partner_sn').val(sn);
	}
	
	genSN();
});
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
