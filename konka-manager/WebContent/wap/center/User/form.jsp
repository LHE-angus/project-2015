<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" /> 
<title>开心猫</title> 
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/global.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/epp_index.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script>
</head>
<body> 
<div id="content">
<div class="mainbox">
<div class="maincont">  
<html-el:form action="/center/User" enctype="multipart/form-data">
        <html-el:hidden property="method" styleId="method" value="save" />
        <html-el:hidden property="id"/>
        <p style="margin-left:20px;margin-top:15px;font-size:16px;line-height:40px;"> 基本信息</p>
         <table style="100%;line-height:26px;">
          <tr>
            <td width="100" align="right"><font color="red">*</font> 真实姓名：</td>
            <td align="left"><html-el:text property="real_name" maxlength="10"  styleClass="input_txt" style="width:160px;" ></html-el:text>
            </td>
          </tr>
          <tr>
            <td align="right">性别：</td>
            <td align="left"><html-el:radio property="sex" value="0">男</html-el:radio>
              <html-el:radio property="sex" value="1">女</html-el:radio>
            </td>
          </tr>
          <tr>
            <td align="right"><font color="red">*</font> 所属地区：</td>
            <td align="left"><select name="province" id="province"  class="input_txt" style="width:100px;" >
                <option value="">-省-</option>
              </select>
              <select name="city" id="city"  class="input_txt" style="width:100px;">
                <option value="">-市-</option>
              </select>
              <select name="country" id="country"  class="input_txt" style="width:100px;">
                <option value="">-县-</option>
              </select>
              <select name="town" id="town"  class="input_txt" style="width:100px;">
                <option value="">-乡/镇-</option>
              </select>
            </td>
          </tr>
          <tr>
            <td align="right">出生日期：</td>
            <td align="left"><input class="input_txt" style="width:160px;" type="text" value="<fmt:formatDate value="${af.map.birthday}" pattern="yyyy-MM-dd" />" name="birthday" size="10" maxlength="10" readonly="true" onclick="new Calendar(1920, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" id="birthday" /> </td>
          </tr>
          <tr>
            <td align="right">详细地址：</td>
            <td align="left"><html-el:text styleClass="input_txt" property="link_addr"  maxlength="100" style="width:160px;"></html-el:text>
            </td>
          </tr>
          <tr>
            <td align="right">邮编：</td>
            <td align="left"><html-el:text styleClass="input_txt" style="width:160px;" property="link_post" maxlength="10"></html-el:text>
            </td>
          </tr>
          <tr>
            <td align="right">手机号码：</td>
            <td align="left"><html-el:text styleClass="input_txt" style="width:160px;" property="link_phone"  maxlength="11"></html-el:text>
            </td>
          </tr>
          <tr>
            <td align="right">固定电话：</td>
            <td align="left"><html-el:text styleClass="input_txt" style="width:160px;" property="link_tel" maxlength="16"></html-el:text>
            </td>
          </tr>
          <tr>
            <td align="right">邮箱：</td>
            <td align="left"><html-el:text styleClass="input_txt" style="width:160px;" property="email" maxlength="30"></html-el:text>
            </td>
          </tr>
         </table>         
        <p style="margin-left:20px;margin-top:15px;font-size:16px;line-height:40px;"> 会员信息</p>
        <table style="100%;line-height:26px;">
         <tr>
            <td width="100" align="right">会员类型：</td>
            <td align="left"><c:if test="${ecUser.user_type eq 1 }">工卡会员</c:if><c:if test="${ecUser.user_type eq 2 }">触网会员</c:if>
            </td>
          </tr>
          <tr>
            <td align="right">会员等级：</td>
            <td align="left"> ${ecUser.ecBaseCardLevel.card_level_name} 
            </td>
          </tr>
          <c:if test="${ecUser.user_type eq 1 }">
          <tr>
            <td align="right">会员卡号：</td>
            <td align="left">${af.map.card_no} </td>
          </tr></c:if>
         <tr>
            <td align="right"><font color="red">*</font> 开户银行：</td>
            <td align="left"><html-el:text property="bank_name" maxlength="60"  styleClass="input_txt" style="width:160px;" ></html-el:text> <font color="#cccccc">填写银行，如：招商银行深圳支行</font></td>
          </tr> 
          <tr>
            <td align="right"><font color="red">*</font> 开户姓名：</td>
            <td align="left"><html-el:text property="bank_account_name" maxlength="60"  styleClass="input_txt" style="width:160px;" ></html-el:text> <font color="#cccccc">填写姓名，如：张三</font></td>
          </tr> 
          <tr>
            <td align="right"><font color="red">*</font> 银行卡号：</td>
            <td align="left"><html-el:text property="bank_account" maxlength="40"  styleClass="input_txt" style="width:160px;" ></html-el:text></td>
          </tr>  
          <tr>
            <td align="right" valign="top">是否本人银行账号：</td>
            <td align="left" valign="top"><html-el:radio property="is_own" value="1">是</html-el:radio><html-el:radio property="is_own" value="0">否</html-el:radio>  
              <br/><font color="red" style="line-heght:10px;">注： 发放佣金时，如绑定为本人的银行卡，将连同工资计算扣税，如绑定为非本人的银行卡，将按劳务费扣税 .  </font>
            </td>
          </tr>         
         </table> 
         <p style="margin-left:120px;margin-top:15px;font-size:16px;">  <input class="inputbtn" type="button" name="Submit4" id="btn_submit" value=" 保 存 " /></p>            
      </html-el:form> 
</div>
<div class="clear"></div>
</div>
</div> 
</body>

<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript">//<![CDATA[ 
$(document).ready(function(){// 区域
$("#province").attr({"subElement": "city", "defaultText": "-请选择省-", "defaultValue": "", "selectedValue": "${province}","dataType":"Require","msg":"请选择省/直辖市/自治区！"});
$("#city").attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${city}"});
$("#country").attr({"subElement": "town","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${country}"});
$("#town").attr({"defaultText": "-请选择乡/镇-", "defaultValue": "", "selectedValue": "${town}"});	
$("#province").cs_ext("${ctx}/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false,"p_index_join","");
$("#province").change();
	
$("input[name='real_name']").attr("dataType", "Require").attr("msg", "收货人姓名必须填写！"); 
$("select[name='province']").attr("dataType", "Require").attr("msg", "请选择省/直辖市/自治区！"); 
$("select[name='city']").attr("dataType", "Require").attr("msg", "请选择市！"); 
$("select[name='country']").attr("dataType", "Require").attr("msg", "请选择县！");  
$("input[name='link_post']").attr("dataType", "Zip").attr("Require", "false").attr("msg", "邮政编码不正确！"); 
$("input[name='email']").attr("dataType", "Email").attr("Require", "false").attr("msg", "邮箱格式不正确！"); 
$("input[name='link_tel']").attr("dataType", "Phone").attr("Require", "false").attr("msg", "电话格式不正确！"); 
$("input[name='link_phone']").attr("dataType", "Mobile").attr("Require", "false").attr("msg", "手机格式不正确！");  
$("input[name='bank_name']"	).attr("dataType", "Require").attr("msg", "开户姓名必须填写！"); 
$("input[name='bank_account_name']"	).attr("dataType", "Require").attr("msg", "开户名必须填写！"); 
$("input[name='bank_account']").attr("dataType", "Require").attr("msg", "银行账号必须填写！"); 

$("#btn_submit").click(function(){ 
	var isSubmit = Validator.Validate(this.form,3);
	if (isSubmit) {
		$("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
		this.form.submit();
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