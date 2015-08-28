<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" /> 
<title>触网</title> 
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/global.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/epp_index.css" />
<style type="text/css"> 
body {font-family: 'microsoft yahei',Verdana,Arial,Helvetica,sans-serif;}
.new-mg-tb30{margin:30px 0;}.new-abtn-type{display:block;padding:8px;border-radius:2px;background-color:#c00;font-size:14px;color:#fff;text-align:center;}
.new-ct{min-height:300px;background-color:#fff;}.new-mu_l2cw{display:block;overflow:hidden;_zoom:1;}.new-p-re {position: relative;}div {display: block;}
.new-input {width: 100%;border: 0;border-radius: 0;background: white;font-size: 12px;line-height: 24px;font-weight: normal;color: #BDBDBD;text-indent: 10px;vertical-align: top;-webkit-appearance: none;}
select {margin: 0;padding: 0;}.new-input-span {display: block; border: 1px solid #CCC;}.new-mg-b10 {margin-bottom: 10px;} .new-select {width: 100%;height: 30px; }
select {-webkit-appearance: menulist;box-sizing: border-box;-webkit-box-align: center;border: 1px solid;border-image: initial;white-space: pre;-webkit-rtl-ordering: logical;color: black;background-color: white;cursor: default;}
</style>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script>
</head>
<body><div class="top_class"><span class="lspan"><a href="javascript:void(0);history.back();"><img src="${ctx}/styles/epp/mobile/images/return.gif" /></a></span><h3>会员注册</h3><a href="javascript:void(0);showNav();" id="btnJdkey" class="new-a-jd"><span>触网</span></a>
	<div class="new-jd-tab" style="top:45px;display:none;" id="jdkey">
	<div class="new-tbl-type">
	<a href="<c:url value='/webservice/wap/Index.do' />" class="new-tbl-cell"><span class="icon">首页</span><p style="color:#6e6e6e;">首页</p>	</a>
	<a href="<c:url value='/webservice/wap/ProdType.do' />" class="new-tbl-cell"><span class="icon2">产品分类</span><p style="color:#6e6e6e;">产品分类</p></a>
	<a href="<c:url value='/webservice/wap/ShoppingCar.do' />" id="html5_cart" class="new-tbl-cell"><span class="icon3 on">购物车</span><p style="color:#6e6e6e;" class="on">购物车</p></a>
	<a href="<c:url value='/webservice/wap/center/Index.do' />" class="new-tbl-cell"><span class="icon4">会员中心</span> <p style="color:#6e6e6e;">会员中心</p></a>
	</div>
	</div>
</div>
<div id="content">
<div class="mainbox">
<div class="maincont">
 <div class="membertab3" id="div_user" style="line-height:28px;font-size:13px;font-family: 'microsoft yahei',Verdana,Arial,Helvetica,sans-serif;">
      <html-el:form action="/wap/center/RegUser" enctype="multipart/form-data">
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="id"/> 
      <p style="margin-left:5px;font-size:16px;"> 会员信息</p>
      <table style="width:100%">
        	<tr>
            <td align="left" colspan="2"><p style="margin-left:15px;">
         	  会员类型： <c:if test="${ecUser.user_type eq 1 }">工卡会员</c:if><c:if test="${ecUser.user_type eq 2 }">触网会员</c:if>  
			 &nbsp;&nbsp;会员等级：${ecUser.ecBaseCardLevel.card_level_name}  <br/>
      		 会员卡号：  ${ecUser.card_no} &nbsp;&nbsp;部    门：${ecUser.department}</p>
            </td>
          </tr> 
          <tr>
            <td align="right" width="100"  valign="top"><font color="red">*</font> 真实姓名：</td>
            <td align="left"><html-el:text property="real_name" maxlength="10"  styleClass="new-input" style="width:200px;" ></html-el:text></td>
          </tr>
          <tr>
            <td align="right">出生日期：</td>
            <td align="left"><input class="new-input" style="width:200px;" type="text" value="<fmt:formatDate value="${af.map.birthday}" pattern="yyyy-MM-dd" />" name="birthday" size="10" maxlength="10" readonly="true" onclick="new Calendar(1920, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" id="birthday" /> </td>
          </tr>  
          <tr>
            <td align="right" valign="top"><font color="red">*</font> 所属地区：</td>
            <td align="left"><select name="province" id="province"  class="new-select" style="width:200px;" >
                <option value="">-请选择省/直辖市/自治区-</option>
              </select><br/>
              <select name="city" id="city"  class="new-select" style="width:200px;">
                <option value="">-请选择市-</option>
              </select><br/>
              <select name="country" id="country"  class="new-select" style="width:200px;">
                <option value="">-请选择县-</option>
              </select><br/>
              <select name="town" id="town"  class="new-select" style="width:200px;">
                <option value="">-请选择乡/镇-</option>
              </select>
            </td>
          </tr>
          <tr>
            <td align="right">详细地址：</td>
            <td align="left"><html-el:text styleClass="new-input" property="link_addr"  maxlength="100" style="width:200px;"></html-el:text>
            </td>
          </tr>　 
          <tr>
            <td align="right"  valign="top"><font color="red">*</font>手机号码：</td>
            <td align="left"><html-el:text styleClass="new-input" style="width:200px;" property="link_phone"  maxlength="11"></html-el:text> </td>
          </tr> 
           <tr>
            <td align="right"  valign="top"><font color="red">*</font>邮箱：</td>
            <td align="left"><html-el:text styleClass="new-input" property="email"  maxlength="30" style="width:200px;"></html-el:text></td>
          </tr>　 
        </table><c:if test="${empty af.map.pay_pwd }">
        <p style="margin-left:5px;margin-top:5px;font-size:16px;">设置密码</p>
        <font>&nbsp;&nbsp;请输入6-20个字符的英文字母或数字！</font>
        <table style="width:100%"><c:if test="${empty af.map.pass_word }">
          <tr>
            <td width="100" align="right"  valign="top"><font color="red">* 登录密码：</font></td>
            <td align="left">
            	<html-el:password property="pass_word" maxlength="20" styleId="pass_word" styleClass="new-input" style="width:200px;" ></html-el:password>            
            </td>
          </tr>
          <tr>
            <td width="100" align="right"  valign="top"><font color="red">*</font> 重复登录密码：</td>
            <td align="left"><html-el:password property="pass_word_repeat" styleId="pass_word_repeat" maxlength="20" styleClass="new-input" style="width:200px;" ></html-el:password></td>
          </tr></c:if>  
          <tr>
            <td align="right" valign="top"><font color="red">* 支付密码：</font></td>
            <td align="left"><html-el:password property="pay_pwd"  maxlength="20" styleId="pay_pwd" styleClass="new-input" style="width:200px;" ></html-el:password>           
            </td>
          </tr>
          <tr>
            <td align="right"  valign="top"><font color="red">*</font> 重复支付密码：</td>
            <td align="left"><html-el:password property="pay_pwd_repeat" styleId="pay_pwd_repeat" maxlength="20"  styleClass="new-input" style="width:200px;" ></html-el:password></td>
          </tr>  
        </table></c:if>     
        <p style="margin-left:5px;margin-top:5px;font-size:16px;"> 银行账号信息</p>
        <table style="width:100%">
          <tr>
            <td width="100" align="right" valign="top"><font color="red"></font> 开户银行：</td>
	        <td align="left"> <font color="#cccccc">填XX支行，如招商银行深圳华侨城支行</font><br/>
	        <html-el:text property="bank_name" maxlength="60"  styleClass="new-input" style="width:200px;" ></html-el:text>
	       </td>
          </tr> 
          <tr>
            <td align="right" valign="top"><font color="red"></font> 开户姓名：</td>
            <td align="left"><font color="#cccccc">填姓名，如：张三</font><br/>
            <html-el:text property="bank_account_name" maxlength="60"  styleClass="new-input" style="width:200px;" ></html-el:text> 
            </td>
          </tr> 
         <tr>
            <td align="right" valign="top"><font color="red"></font> 银行卡号：</td>
            <td align="left"><html-el:text property="bank_account" maxlength="40"  styleClass="new-input" style="width:200px;" ></html-el:text> </td>
          </tr>  
          <tr>
            <td align="right" valign="top">本人银行账号：</td>
            <td align="left">
            	<html-el:radio property="is_own" value="1">是</html-el:radio><html-el:radio property="is_own" value="0">否</html-el:radio>
            </td>
          </tr>                      
         </table> 
         <p style="margin-top:15px;font-size:16px;"> <a href="javascript:void();" name="btn_submit" id="btn_submit" class="new-abtn-type new-mg-t15">保存</a> </p>  
         <p style="margin-left:15px;color:red;font-size:10px;line-height:20px;"> 注： <br/>
         	1.登陆密码是推荐游客登陆平台，产品页面隐藏佣金、积分、个人中心等相关信息。 <br/>
         	2.支付密码是本人登陆平台，产品页面显示佣金、积分、个人中心等相关信息。 <br/>
          	3.发放佣金时，如绑定为本人的银行卡，将连同工资计算扣税，如绑定为非本人的银行卡，将按劳务费扣税 .  </p>
         <div style="margin-left:10px;margin-top:20px;font-size:14px;"> 温馨提示，该账号仅限员工及亲友使用购机，商品不能二次销售，祝您购物愉快.</div>   
      </html-el:form>
    </div>
</div>
</div>
</div> 
</body>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript">//<![CDATA[ 
$(document).ready(function(){//  
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
	$("select[name='province']").attr("dataType", "Require").attr("msg", "请选择省/直辖市/自治区！"); 
	$("select[name='city']").attr("dataType", "Require").attr("msg", "请选择市！"); 
	$("select[name='country']").attr("dataType", "Require").attr("msg", "请选择县！"); 
	$("input[name='link_phone']").attr("dataType", "Mobile").attr("msg", "手机格式不正确！");  
	$("input[name='email']").attr("dataType", "Email").attr("msg", "请填写正确格式邮箱");

	$("#btn_submit").click(function(){ 
		var isSubmit = Validator.Validate(document.forms[0],3);
		if (isSubmit) {
			 if($("#pass_word").val()==$("#pay_pwd").val()){
				 alert("请不要把支付密码设置与登录密码相同");
				 $("#pay_pwd").val("");
				 $("#pay_pwd_repeat").val("");
				 $("#pay_pwd").focus();
				 return false;
			 }
			// $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
			 document.forms[0].submit();
		}
	});
});                                          
                                          
function showNav(){
	if(document.getElementById("jdkey").style.display=='none'){ 
		document.getElementById("jdkey").style.display='block';
	}else{
		document.getElementById("jdkey").style.display='none';
	} 
}
//]]>
</script> 
</html>