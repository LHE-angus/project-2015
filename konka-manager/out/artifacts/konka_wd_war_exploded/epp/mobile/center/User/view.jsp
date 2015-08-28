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
<style type="text/css">
body {font-family: 'microsoft yahei',Verdana,Arial,Helvetica,sans-serif;}
</style>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script>
</head>
<body><div class="top_class"><span class="lspan"><a href="javascript:void(0);history.back();"><img src="${ctx}/styles/epp/mobile/images/return.gif" /></a></span><h3>账户中心</h3><a href="javascript:void(0);showNav();" id="btnJdkey" class="new-a-jd"><span>开心猫</span></a>
	<div class="new-jd-tab" style="top:45px;display:none;" id="jdkey">
	<div class="new-tbl-type">
	<a href="<c:url value='/epp/mobile/Index.do' />" class="new-tbl-cell"><span class="icon">首页</span><p style="color:#6e6e6e;">首页</p>	</a>
	<a href="<c:url value='/epp/mobile/ProdType.do' />" class="new-tbl-cell"><span class="icon2">产品分类</span><p style="color:#6e6e6e;">产品分类</p></a>
	<a href="<c:url value='/epp/mobile/ShoppingCar.do' />" id="html5_cart" class="new-tbl-cell"><span class="icon3 on">购物车</span><p style="color:#6e6e6e;" class="on">购物车</p></a>
	<a href="<c:url value='/epp/mobile/center/Index.do' />" class="new-tbl-cell"><span class="icon4">会员中心</span> <p style="color:#6e6e6e;">会员中心</p></a>
	</div>
	</div>
</div>
<div id="content">
<div class="mainbox">
<div class="maincont">  
<html-el:form action="/center/User" enctype="multipart/form-data">
        <html-el:hidden property="method" styleId="method" value="save" />
        <html-el:hidden property="id"/> 
        <p style="margin-left:60px;margin-top:15px;font-size:16px;line-height:40px;"> 基本信息 [<a href="?"><font color="red">编辑</font></a>]</p>
         <table style="100%;line-height:26px;">
          <tr>
            <td width="100" align="right"><font color="red">*</font> 真实姓名：</td>
            <td align="left"> <html-el:text property="real_name" maxlength="10"  styleClass="input_txt" style="width:160px;" ></html-el:text>
            </td>
          </tr>
          <tr>
            <td align="right">性别：</td>
            <td align="left"> <html-el:radio property="sex" value="0">男</html-el:radio>
              <html-el:radio property="sex" value="1">女</html-el:radio>
            </td>
          </tr>
          <tr>
            <td align="right"><font color="red">*</font> 所属地区：</td>
            <td align="left"> <select name="province" id="province"  class="input_txt" style="width:100px;" >
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
            <td align="left"> <input class="input_txt" style="width:160px;" type="text" value="<fmt:formatDate value="${af.map.birthday}" pattern="yyyy-MM-dd" />" name="birthday" size="10" maxlength="10" readonly="true" onclick="new Calendar(1920, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" id="birthday" /> </td>
          </tr>
          <tr>
            <td align="right">详细地址：</td>
            <td align="left"> <html-el:text styleClass="input_txt" property="link_addr"  maxlength="100" style="width:160px;"></html-el:text>
            </td>
          </tr>
          <tr>
            <td align="right">邮编：</td>
            <td align="left"> <html-el:text styleClass="input_txt" style="width:160px;" property="link_post" maxlength="10"></html-el:text>
            </td>
          </tr>
          <tr>
            <td align="right">手机号码：</td>
            <td align="left"> <html-el:text styleClass="input_txt" style="width:160px;" property="link_phone"  maxlength="11"></html-el:text>
            </td>
          </tr>
          <tr>
            <td align="right">固定电话：</td>
            <td align="left"> <html-el:text styleClass="input_txt" style="width:160px;" property="link_tel" maxlength="16"></html-el:text>
            </td>
          </tr>
          <tr>
            <td align="right">邮箱：</td>
            <td align="left"> <html-el:text styleClass="input_txt" style="width:160px;" property="email" maxlength="30"></html-el:text>
            </td>
          </tr>
         </table>         
        <p style="margin-left:20px;margin-top:15px;font-size:16px;"> 会员信息</p>
        <table style="100%;line-height:26px;">
         <tr>
            <td width="100" align="right">会员类型：</td>
            <td align="left"> <c:if test="${ecUser.user_type eq 1 }">工卡会员</c:if><c:if test="${ecUser.user_type eq 2 }">触网会员</c:if>
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
            <td align="left"> <html-el:text property="bank_name" maxlength="60"  styleClass="input_txt" style="width:160px;" ></html-el:text> <font color="#cccccc">填写银行，如：招商银行深圳支行</font></td>
          </tr> 
          <tr>
            <td align="right"><font color="red">*</font> 开户姓名：</td>
            <td align="left"> <html-el:text property="bank_account_name" maxlength="60"  styleClass="input_txt" style="width:160px;" ></html-el:text> <font color="#cccccc">填写姓名，如：张三</font></td>
          </tr> 
          <tr>
            <td align="right"><font color="red">*</font> 银行卡号：</td>
            <td align="left"> <html-el:text property="bank_account" maxlength="40"  styleClass="input_txt" style="width:160px;" ></html-el:text></td>
          </tr>  
          <tr>
            <td align="right" valign="top">是否本人银行账号：</td>
            <td align="left" valign="top"> <html-el:radio property="is_own" value="1">是</html-el:radio><html-el:radio property="is_own" value="0">否</html-el:radio>  
              <br/><font color="red" style="line-height:0px;">注： 发放佣金时，如绑定为本人的银行卡，将连同工资计算扣税，如绑定为非本人的银行卡，将按劳务费扣税 .  </font>
            </td>
          </tr>         
         </table> 
    </html-el:form> 
</div>
<div class="clear"></div>
</div>
</div> 
</body>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript">//<![CDATA[ 
$(document).ready(function(){// 区域
$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${province}","dataType":"Require","msg":"请选择省/直辖市/自治区！"});
$("#city").attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${city}"});
$("#country").attr({"subElement": "town","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${country}"});
$("#town").attr({"defaultText": "-请选择乡/镇-", "defaultValue": "", "selectedValue": "${town}"});	
$("#province").cs_ext("${ctx}/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false,"p_index_join","");
$("#province").change();
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