<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>康佳</title>
<link rel="stylesheet" href="${ctx}/styles/zxmall/2015/css/base.css"/>
<link rel="stylesheet" href="${ctx}/styles/zxmall/2015/css/index.css"/> 

<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/purchase.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/citybox.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/cloud-zoom/styles/image_show.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/zxmall.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
</head>
<body id="web_body">
<jsp:include page="/zxmall/__inc/2015/top.jsp" flush="true" />  
<jsp:include page="/zxmall/__inc/2015/nav.jsp" flush="true" />  
<div class="main" style="padding-top:0px;">
<div class="w1200">
<!-- second start -->
<div class="maincont">
  <jsp:include page="/zxmall/__inc/_mleft.jsp" flush="true" />
  <!--right-->
  <div class="zxmall_right padbot45">
    <div class="position"><a href="${ctx }/zxmall/Index.do">首页</a> &gt; <a href="${ctx }/zxmall/center/Index.do">会员中心</a> &gt; 完善用户资料</div>
     <div class="zxmalltab3" id="div_user" >
      <html-el:form action="/center/RegUser" enctype="multipart/form-data">
        <html-el:hidden property="method" styleId="method" value="save" />
        <html-el:hidden property="id"/>
        <%@ include file="/commons/pages/messages.jsp" %>
      <p style="margin-left:20px;margin-top:15px;font-size:16px;"> 会员信息</p>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="zxmall_form_table1">
        <tr>
            <td width="20%" align="right">会员类型：</td>
            <td width="80%" align="left"><c:if test="${zxmall.user_type eq 1 and zxmall.dept_id >1 }">分公司会员</c:if><c:if test="${zxmall.user_type eq 2 }">触网会员</c:if>
            </td>
          </tr>
          <tr>
            <td width="20%" align="right">会员等级：</td>
            <td width="80%" align="left"> ${zxmall.ecBaseCardLevel.card_level_name} 
            </td>
          </tr> 
          <tr>
            <td width="20%" align="right">部门：</td>
            <td width="80%" align="left">${af.map.group_name}</td>
          </tr> 
           <tr>
            <td width="20%" align="right"><font color="red">*</font> 登录名：</td>
            <td width="80%" align="left"><html-el:text property="user_name" styleId="user_name" maxlength="20"  styleClass="input_txt" style="width:160px;" ></html-el:text><span style="color:#f00;display:none;" id="user_name_exist_error">该登录名已被使用，请重新输入！</span><span style="color:#f00;display:none;" id="user_name_erro">登录名不能含空白字符！</span></td>
          </tr>
         <c:if test="${empty af.map.pass_word }">
          <tr>
            <td width="20%" align="right"><font color="red">*</font> 登录密码：</td>
            <td width="80%" align="left"><html-el:password property="pass_word"  maxlength="20" styleId="pass_word" styleClass="input_txt" style="width:160px;" ></html-el:password>
            <span style="font-size:10px;font-color:red;"> 
          	<br/>注：登陆密码是推荐游客登陆平台，产品页面隐藏佣金、积分、个人中心等相关信息。</span>
            </td>
         	
          </tr>
          <tr>
            <td width="20%" align="right"><font color="red">*</font> 重复登录密码：</td>
            <td width="80%" align="left"><html-el:password property="pass_word_repeat" styleId="pass_word_repeat" maxlength="20"  styleClass="input_txt" style="width:160px;" ></html-el:password></td>
          </tr></c:if>  
          <tr>
            <td width="20%" align="right"><font color="red">*</font> 真实姓名：</td>
            <td width="80%" align="left"><html-el:text property="real_name" maxlength="10"  styleClass="input_txt" style="width:160px;" ></html-el:text></td>
          </tr>
           <tr>
            <td align="right">出生日期：</td>
            <td align="left"><input class="input_txt" style="width:160px;" type="text" value="<fmt:formatDate value="${af.map.birthday}" pattern="yyyy-MM-dd" />" name="birthday" size="10" maxlength="10" readonly="true" onclick="new Calendar(1920, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" id="birthday" /> </td>
          </tr>  
          <tr>
            <td align="right"><font color="red">*</font> 所属地区：</td>
            <td align="left"><select name="province" id="province"  class="input_txt" style="width:160px;" >
                <option value="">-请选择省/直辖市/自治区-</option>
              </select>
              <select name="city" id="city"  class="input_txt" style="width:100px;">
                <option value="">-请选择市-</option>
              </select>
              <select name="country" id="country"  class="input_txt" style="width:100px;">
                <option value="">-请选择县-</option>
              </select>
              <select name="town" id="town"  class="input_txt" style="width:100px;">
                <option value="">-请选择乡/镇-</option>
              </select>
            </td>
          </tr>
          <tr>
            <td align="right">详细地址：</td>
            <td align="left"><html-el:text styleClass="input_txt" property="link_addr"  maxlength="100" style="width:300px;"></html-el:text>
            </td>
          </tr>　 
          <tr>
            <td align="right"><font color="red">*</font>手机号码：</td>
            <td align="left"><html-el:text styleClass="input_txt" style="width:160px;" property="link_phone"  maxlength="11"></html-el:text> </td>
          </tr> 
           <tr>
            <td align="right"><font color="red">*</font>邮箱：</td>
            <td align="left"><html-el:text styleClass="input_txt" property="email"  maxlength="30" style="width:160px;"></html-el:text>
          <span style="font-size:10px;font-color:red;"> 
          	<br/>注：如果您注册邮箱为非康佳邮箱，由于163、腾讯QQ邮箱对外部邮件的限制，会将我们的邮件划入垃圾邮件， <br/>当遇到这种情况，请您在您的垃圾箱中查看邮件信息。
          </span>  
          </td>
          </tr>　 
         </table>  <c:if test="${empty af.map.pay_pwd }">
         <p style="margin-left:20px;margin-top:15px;font-size:16px;">设置支付密码</p>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="zxmall_form_table1">
          <tr>
            <td width="20%" align="right"><font color="red">*</font> 支付密码：</td>
            <td width="80%" align="left"><html-el:password property="pay_pwd"  maxlength="20" styleId="pay_pwd" styleClass="input_txt" style="width:160px;" ></html-el:password><font>&nbsp;&nbsp;请输入6-20个字符的英文字母或数字！</font>
            <span style="font-size:10px;font-color:red;"> 
          	<br/>注：支付密码是本人登陆平台，产品页面显示佣金、积分、个人中心等相关信息。</span> 
            </td>
          </tr>
          <tr>
            <td width="20%" align="right"><font color="red">*</font> 重复支付密码：</td>
            <td width="80%" align="left"><html-el:password property="pay_pwd_repeat" styleId="pay_pwd_repeat" maxlength="20"  styleClass="input_txt" style="width:160px;" ></html-el:password></td>
          </tr>  
           </table></c:if>     
        <p style="margin-left:20px;margin-top:15px;font-size:16px;"> 银行账号信息</p>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="zxmall_form_table1">
          <tr>
            <td width="20%" align="right"><font color="red"></font> 开户银行：</td>
	            <td width="80%" align="left"><html-el:text property="bank_name" maxlength="60"  styleClass="input_txt" style="width:240px;" ></html-el:text><font color="red">填写银行，**银行**分行或支行,如招商银行深圳华侨城支行</font></td>
          </tr> 
          <tr>
            <td width="20%" align="right"><font color="red"></font> 开户姓名：</td>
            <td width="80%" align="left"><html-el:text property="bank_account_name" maxlength="60"  styleClass="input_txt" style="width:240px;" ></html-el:text> <font color="#cccccc">填写姓名，如：张三</font></td>
          </tr> 
         <tr>
            <td width="20%" align="right"><font color="red"></font> 银行卡号：</td>
            <td width="80%" align="left"><html-el:text property="bank_account" maxlength="40"  styleClass="input_txt" style="width:240px;" ></html-el:text> </td>
          </tr>  
          <tr>
            <td align="right">是否本人银行账号：</td>
            <td align="left"><html-el:radio property="is_own" value="1">是</html-el:radio>
              <html-el:radio property="is_own" value="0">否</html-el:radio>&nbsp;&nbsp;  <font color="red">注： 发放佣金时，如绑定为本人的银行卡，将连同工资计算扣税，如绑定为非本人的银行卡，将按劳务费扣税 .  </font>
            </td>
          </tr>                      
         </table> 
         <p style="margin-left:200px;margin-top:15px;font-size:16px;">  <input class="inputbtn" type="button" name="Submit4" id="btn_submit" value="确认" />
         </p>    
      </html-el:form>
    </div>
  </div>
  <div class="clear"></div>
</div>
</div> 
</div>
<jsp:include page="/zxmall/__inc/2015/footer.jsp" flush="true" />
<!-- six footer -->
</body>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
   
   		// 区域
		$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${province}","dataType":"Require","msg":"请选择省/直辖市/自治区！"});
		$("#city").attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${city}"});
		$("#country").attr({"subElement": "town","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${country}"});
		$("#town").attr({"defaultText": "-请选择乡/镇-", "defaultValue": "", "selectedValue": "${town}"});
		
		$("#province").cs_ext("${ctx}/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false,"p_index_join","");
		$("#province").change();
		
		<c:if test="${empty af.map.pass_word }">
		$("#pass_word").attr("dataType", "Require").attr("msg", "请填写登录密码！");
		$("#pass_word_repeat").attr("datatype","Repeat").attr("to", "pass_word").attr("msg","两次输入的密码不一致！");
		</c:if><c:if test="${empty af.map.pay_pwd }">
		$("#pay_pwd").attr("dataType","User_name").attr("Require","true").attr("msg", "请填写支付密码,且必须在6-20个字符之间！");
		$("#pay_pwd_repeat").attr("datatype","Repeat").attr("to", "pay_pwd").attr("msg","两次输入的密码不一致！");
		</c:if>
		$("input[name='real_name']").attr("dataType", "Require").attr("msg", "真实姓名必须填写！");
		$("input[name='user_name']").attr("dataType", "Require").attr("msg", "登录名必须填写！"); 
		$("select[name='province']").attr("dataType", "Require").attr("msg", "请选择省/直辖市/自治区！"); 
		$("select[name='city']").attr("dataType", "Require").attr("msg", "请选择市！"); 
		$("select[name='country']").attr("dataType", "Require").attr("msg", "请选择县！"); 
		//$("input[name='link_addr']").attr("dataType", "Require").attr("msg", "联系地址必须填写！"); 
		$("input[name='link_phone']").attr("dataType", "Mobile").attr("msg", "手机格式不正确！");  
		$("input[name='email']").attr("dataType", "Email").attr("msg", "请填写正确格式邮箱"); 
		//$("input[name='bank_name']"	).attr("dataType", "Require").attr("msg", "开户银行必须填写！"); 
		//$("input[name='bank_account_name']"	).attr("dataType", "Require").attr("msg", "开户名必须填写！"); 
		//$("input[name='bank_account']").attr("dataType", "Require").attr("msg", "银行卡号必须填写！"); 

		// 验证用户名是否存在
		$("#user_name").blur(function(){
			//$("#btn_submit").attr("disabled", "disabled"); 
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
					url: "RegUser.do",
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
							//$("#user_name_exist_error").show();
							//$("#btn_submit").attr("disabled", "disabled");
							//$("#user_name").css("background-color", "#ddcc00");
							//$("#user_name").focus();
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
		
		
		
		$("#btn_submit").click(function(){ 
			var isSubmit = Validator.Validate(this.form,3);
			if (isSubmit) {
				 if($("#pass_word").val()==$("#pay_pwd").val()){
					 alert("请不要把支付密码设置与登录密码相同");
					 $("#pay_pwd").val("");
					 $("#pay_pwd_repeat").val("");
					 $("#pay_pwd").focus();
					 return false;
				 }
				 $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
				 this.form.submit();
			}
		});
		
}); 
//]]></script>
</html>
