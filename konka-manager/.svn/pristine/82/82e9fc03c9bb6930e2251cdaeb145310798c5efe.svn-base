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
    <html-el:form action="/spgl/EcBaseCardNo" method="post">
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <c:set value="${empty af.map.id?false:true}" var="readyOnly" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>会员卡号：</td>
          <td width="88%" align="left"><html-el:text property="card_no" styleId="card_no" size="60" maxlength="60" readonly="${readyOnly}"/>
          <span style="color:#f00;display:none;" id="card_no_exist_error">该会员卡号已被使用，请重新输入！</span><span style="color:#f00;display:none;" id="card_no_erro">会员卡号不能含空白字符！</span>
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">发卡日期：</td>
          <td width="88%" align="left"><fmt:formatDate value="${af.map.card_pub_date}" pattern="yyyy-MM-dd" var="_card_pub_date" />
            <html-el:text property="card_pub_date" size="10" maxlength="10" readonly="true" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:center;" value="${_card_pub_date}" /></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">发卡人|客户：</td>
          <td width="88%" align="left"><html-el:text property="card_sender" styleId="card_sender" size="15" maxlength="15"/>
          </td>
        </tr>
        <tr >
            <td width="12%" nowrap="nowrap" class="title_item" align="right">发卡人部门：</td>
            <td width="88%" align="left"><html-el:select property="card_sender_dept" styleId="card_sender_dept" >
                <html-el:option value="">请选择...</html-el:option>
                <c:forEach var="cur" items="${sybDeptInfoList}">
                  <html-el:option value="${cur.dept_name}">${cur.dept_name}</html-el:option>
                </c:forEach>
              </html-el:select>
            </td>
          </tr>
          <tr >
            <td width="12%" nowrap="nowrap" class="title_item" align="right">会员卡分类：</td>
            <td width="88%" align="left"><html-el:select property="card_type" styleId="card_type" >
                <html-el:option value="">请选择...</html-el:option>
                <c:forEach var="cur" items="${etList}">
                  <html-el:option value="${cur.card_type}">${cur.card_type_name}</html-el:option>
                </c:forEach>
              </html-el:select>
            </td>
          </tr>
          <tr >
            <td width="12%" nowrap="nowrap" class="title_item" align="right"><font style="color: red;">*</font>会员卡等级：</td>
            <td width="88%" align="left"><html-el:select property="card_level" styleId="card_level" >
                <html-el:option value="">请选择...</html-el:option>
                <c:forEach var="cur" items="${elList}">
                  <html-el:option value="${cur.card_level}">${cur.card_level_name}</html-el:option>
                </c:forEach>
              </html-el:select>
            </td>
          </tr>
          <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">会员姓名：</td>
          <td width="88%" align="left"><html-el:text property="member_name" styleId="member_name" size="15" maxlength="15"/>
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">会员手机号码：</td>
          <td width="88%" align="left"><html-el:text property="member_tel" styleId="member_tel" size="15" maxlength="15"/>
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">会员身份证：</td>
          <td width="88%" align="left"><html-el:text property="member_id" styleId="member_id" size="20" maxlength="20"/>
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">会员卡激活有效期：</td>
          <td width="88%" align="left"><fmt:formatDate value="${af.map.card_act_valid_date}" pattern="yyyy-MM-dd" var="_card_act_valid_date" />
            <html-el:text property="card_act_valid_date" size="10" maxlength="10" readonly="true" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:center;" value="${_card_act_valid_date}" />
           </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">会员卡激活时间：</td>
          <td width="88%" align="left"><fmt:formatDate value="${af.map.card_act_date}" pattern="yyyy-MM-dd" var="_card_act_date" />
            <html-el:text property="card_act_date" size="10" maxlength="10" readonly="true" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:center;" value="${_card_act_date}" />
           </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">会员卡制作人：</td>
          <td width="88%" align="left"><html-el:text property="card_creater" styleId="card_creater" size="15" maxlength="15"/>
          </td>
        </tr>
        <tr >
            <td width="12%" nowrap="nowrap" class="title_item" align="right">制作人部门：</td>
            <td width="88%" align="left"><html-el:select property="card_create_dept" styleId="card_create_dept" >
                <html-el:option value="">请选择...</html-el:option>
                <c:forEach var="cur" items="${sybDeptInfoList}">
                  <html-el:option value="${cur.dept_name}">${cur.dept_name}</html-el:option>
                </c:forEach>
              </html-el:select>
            </td>
          </tr>
           <tr>
          <td nowrap="nowrap" class="title_item" align="right">会员卡有效期开始：</td>
          <td width="88%" align="left"><fmt:formatDate value="${af.map.card_limit_start}" pattern="yyyy-MM-dd" var="_card_limit_start" />
            <html-el:text property="card_limit_start" size="10" maxlength="10" readonly="true" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:center;" value="${_card_limit_start}" />
           </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">会员卡有效期结束：</td>
          <td width="88%" align="left"><fmt:formatDate value="${af.map.card_limit_end}" pattern="yyyy-MM-dd" var="_card_limit_end" />
            <html-el:text property="card_limit_end" size="10" maxlength="10" readonly="true" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:center;" value="${_card_limit_end}" />
           </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">会员卡备注：</td>
          <td width="88%" align="left">
          <html-el:textarea property="card_memo" styleId="card_memo" cols="5" style="width:450px;height:70px;" />
          	<div id="info_tip" style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div>
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">是否允许多次激活：</td>
          <td width="88%" align="left">
          <html-el:radio property="card_allow_mul_act" value="0">不允许</html-el:radio>
          <html-el:radio property="card_allow_mul_act" value="1">允许</html-el:radio>
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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	$("#card_no").attr("datatype", "Require").attr("msg", "请填写会员卡号！");
	$("#card_level").attr("dataType", "Require" ).attr("msg", "请选择会员卡等级！");
	
	$("#card_memo").textbox({
		maxLength: 450,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_tip").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_tip").slideUp("normal");
	});

	$("#card_no").blur(function(){
		var card_no = $("#card_no").val();
		if(null == $(this).val() || $(this).val() == ''){
			$("#card_no_exist_error").hide();
			$("#card_no_erro").hide();
			$(this).css("background-color", "#fff");
			return ;
		}
		if($(this).val().indexOf(' ')>-1){
			$("#card_no_exist_error").hide();
			$("#card_no_erro").show();
			return;
		}
		
		$("#card_no_erro").hide();
		$("#card_no_exist_error").hide();
		$(this).css("background-color", "#fff");
		if(card_no != '${af.map.card_no}'){
			$.ajax({
				type: "POST",
				url: "EcBaseCardNo.do",
				data: "method=validateCardNo&card_no=" + $(this).val(),
				dataType: "json",
				error: function(request, settings) {
					alert("检查用户名重复失败，请稍候再次尝试。");
					$("#card_no_exist_error").show();
					$(this).css("background-color", "#ddcc00");
					$(this).focus();
				},
				success: function(oper) {
					if (oper.result) {
						alert("该会员卡号已存在！");
						$("#card_no").val("");
						return;
					} else {
						$("#card_no_exist_error").hide();
						$("#card_no").css("background-color", "#fff");
					}
				}
			});
		}
	});	

	$("#btn_submit").click(function(){
		var isSubmit = Validator.Validate(this.form, 3);
		if (isSubmit) {
			$(":button").attr("disabled", "true");
			$(":reset").attr("disabled", "true");
			this.form.submit();
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
