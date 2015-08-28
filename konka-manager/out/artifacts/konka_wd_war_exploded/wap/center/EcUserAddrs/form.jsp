<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>开心猫</title> 
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/global.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/epp_index.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/address.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script>
<style type="text/css">
body {font-family: 'microsoft yahei',Verdana,Arial,Helvetica,sans-serif;}
.new-mg-tb30{margin:30px 0;}.new-abtn-type{display:block;padding:8px;border-radius:2px;background-color:#c00;font-size:14px;color:#fff;text-align:center;}
.new-ct{min-height:300px;background-color:#fff;}.new-mu_l2cw{display:block;overflow:hidden;_zoom:1;}.new-p-re {position: relative;}div {display: block;}
.new-input {width: 100%;border: 0;border-radius: 0;background: white;font-size: 12px;line-height: 24px;font-weight: normal;color: #BDBDBD;text-indent: 10px;vertical-align: top;-webkit-appearance: none;}
select {margin: 0;padding: 0;}.new-input-span {display: block; border: 1px solid #CCC;}.new-mg-b10 {margin-bottom: 10px;} .new-select {width: 100%;height: 30px; }
select {-webkit-appearance: menulist;box-sizing: border-box;-webkit-box-align: center;border: 1px solid;border-image: initial;white-space: pre;-webkit-rtl-ordering: logical;color: black;background-color: white;cursor: default;}
</style>
</head>
<body>
<div class="mainbox">
<div class="maincont">   
<div class="new-ct"><html-el:form action="/center/EcUserAddrs">
    <html-el:hidden property="method" styleId="method" value="save" /><html-el:hidden property="id"/>
	<div class="new-addr">
        <div class="new-info-box">
            <div class="new-set-info">
                <span class="new-txt2 new-mg-b5">收货人姓名</span>
				<span class="new-input-span new-mg-b10"><html-el:text property="rel_name" styleId="rel_name" styleClass="new-input" maxlength="20" /></span> 
				<span class="new-txt2 new-mg-b5">手机号码</span>
                <span class="new-input-span new-mg-b10"><html-el:text property="rel_phone" styleId="rel_phone" styleClass="new-input" maxlength="20" /></span>                    
                <span class="new-txt2 new-mg-b5">所属地区</span>              
                <span class="new-input-span new-mg-b10"><select name="province" id="province"  class="new-select" ><option value="">-请选择省/直辖市/自治区-</option></select></span>
			  	<span class="new-input-span new-mg-b10"><select name="city" id="city"  class="new-select" ><option value="">-请选择市-</option></select>  </span>
			    <span class="new-input-span new-mg-b10"><select name="country" id="country"  class="new-select" ><option value="">-请选择县-</option></select></span>
			    <span class="new-input-span new-mg-b10"><select name="town" id="town" class="new-select" ><option value="">-请选择乡/镇-</option></select></span> 
                <span class="new-txt2 new-mg-10">街道地址</span>
                <span class="new-input-span new-mg-b10"><html-el:text property="rel_addr" styleId="rel_addr" styleClass="new-input" maxlength="200" /></span>
            </div>
            <a href="javascript:void();" name="btn_submit" id="btn_submit" class="new-abtn-type new-mg-t15">保存地址</a>
        </div>
	</div></html-el:form>
</div>
<div class="clear"></div>
</div>
</div>
</body> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
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
		
$("#rel_name").attr("dataType", "Require").attr("msg", "收货人姓名必须填写");
$("#rel_phone").attr("dataType", "Mobile").attr("msg", "手机格式不正确"); 
$("select[name='province']").attr("dataType", "Require").attr("msg", "请选择省/直辖市/自治区"); 
$("select[name='city']").attr("dataType", "Require").attr("msg", "请选择市"); 
$("select[name='country']").attr("dataType", "Require").attr("msg", "请选择县"); 
$("#rel_addr").attr("dataType", "Require").attr("msg", "详细街道地址必须填写");  
var flg=0;		
$("#btn_submit").click(function(){
	var isSubmit = Validator.Validate(document.forms[0],3);
	if (isSubmit&&flg==0) {
		flg=1;
		$("#btn_submit").html("正在提交...");
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