<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康佳网上直销商城</title>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/zxmall/css/global1.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/zxmall/css/epp.css" />  
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/slider.css" />
<style type="text/css">
	 .ph_list_box{width:993px; margin:0 auto;font-family:"宋体"; margin-top:15px;}
.ph_list_title{background:#f2f2f2; height:27px; width:100%; font-size:16px;  color:#fff;}
.ph_title_font{background:url(../images/ph_title_red_bg.jpg) no-repeat #e80000; background-position:right 0; padding:3px 30px 4px 20px; line-height:25px;}
.ph_con{width:1007px; margin:0 auto; border:1px solid #ccc; border-top:none; margin-top:-4px; padding-bottom:30px;}
.ph_about_1{ font-size:16px; color:#666666; padding-top:25px; padding-left:30px; font-weight:bold;}
.ph_user_table{margin-top:15px; margin-left:28px;}
.ph_user_table td{padding-bottom:10px; padding-left:10px; font-size:14px; color:#666666;}
.ph_user_table td img{vertical-align:middle;}
.ph_user_text{width:198px; height:26px; border:1px solid #ccc; padding-left:4px;padding-top:4px;}
.ph_user_code{width:108px; height:26px; border:1px solid #ccc;padding-left:4px;padding-top:4px; vertical-align:top;}
.ph_code_img{vertical-align:top;}
.ph_user_area{width:514px; height:190px;padding-left:4px; padding-top:4px;}
.ph_user_table td.ph_are_td{padding-left:50px; position:relative;}
.ph_explan{position:absolute; top:8px; left:57px; color:#999; width:500px; line-height:20px;}
.ph_user_btn{background:#606060; width:176px; height:45px; border:none; font-size:18px; color:#ffffff;font-family:"宋体"; cursor:pointer; margin-left:80px; margin-top:15px;}
.ph_user_btn:hover{background:#01adf1;}

.ph_ts_table{padding-top:20px;}
.ph_ts_table td img{ padding-left:17px; padding-top:14px;}

.ph_help_con{padding-bottom:0px;}
.ph_server_sub{float:left;width:188px;height:160px; padding:10px 5px;}
.server_sub_title{line-height:35px; font-weight: bold; color: #444; border-bottom: solid 1px #CCCCCC; margin-right:30px;margin-left:10px; padding-left:23px;background:url(../images/ph_ico.gif) no-repeat; background-position: 5px 11px;}
.ph_service_pic{background:#f1f1f1; height:64px; width:100%;border-top: 1px solid #DDDDDD;}
.server_sub_list li{line-height:22px;margin-left:10px;background:url(../images/ph_point.gif) no-repeat; padding-left:23px; background-position: 9px center; font-size:12px;}
.server_sub_list li a:hover{text-decoration:underline;}
.ph_service_pic_sub {float: left;text-align: center;width: 247px;}
.ph_service_pic_sub img{ border:none;}
.inputbtn {float:left;width:94px;height:28px;margin-left: 120px;}
</style>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
</head>
<body>
<!-- head start -->
<!-- top start -->
<div class="topbox">
<jsp:include page="/zxmall/__inc/top.jsp" flush="true" />
<jsp:include page="/zxmall/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/zxmall/__inc/nav.jsp" flush="true" />
<!-- top end --> 
<!-- first end --> 
<!-- second start -->
<div class="maincont margintop10">
<div id="content" style="margin:20px 10px 20px 10px; min-height:450px;">
		<c:if test="${not empty entity.img_path}">
			      <div style="text-align:center;margin-top: 45px;"> <img src="${ctx}/${fn:substringBefore(entity.img_path, '.')}.jpg" title="${entity.img_desc}" />
			        <c:if test="${not empty entity.img_desc}"> <br />
			          <c:out value="${entity.img_desc}" />
			        </c:if>
			      </div>
			    </c:if>
 		<div class="ph_list_box">
        <div class="dz_dh1"></div> 
        <div class="ph_con">
        ${entity.content }
        </div>
        </div>
  		<div class="ph_list_box">
        <div class="dz_dh2"></div>
        <div class="ph_con">
        <p class="ph_about_1">采购、合作意向请填下表（我们将在第一时间与您联系）</p>
        <html-el:form action="/Dzcg">
        <html-el:hidden property="method" styleId="method" value="save" />
        <html-el:hidden property="id"/>
        <%@ include file="/commons/pages/messages.jsp" %>
            <table border="0" cellpadding="0" cellspacing="0" class="ph_user_table">
                <tbody>
                    <tr>
                        <td>客户姓名：</td>
                        <td><html-el:text styleClass="ph_user_text" styleId="c_name" property="c_name" maxlength="15" /></td>
                        <td class="ph_are_td" rowspan="5">
                        <html-el:textarea styleClass="ph_user_area" styleId="content" property="content"></html-el:textarea>
                        <p class="ph_explan" id="ph_1">采购数量十台以上，或金额超过2万元，请在此留下您的采购、合作意向。数量十台以下或采购金额2万元以下可在商城直接下单。</p>
                        <div id="info_tip" style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div>
                        </td>
                    </tr>
                    <tr>
                        <td>联系电话：</td>
                        <td><html-el:text styleClass="ph_user_text" styleId="c_tel" property="c_tel" maxlength="15" /></td>
                    </tr>
                    <tr>
                        <td>单位名称：</td>
                        <td><html-el:text styleClass="ph_user_text" styleId="c_dw_name" property="c_dw_name" maxlength="30" /></td>
                    </tr>
                    <tr>
                        <td>邮箱地址：</td>
                        <td><html-el:text styleClass="ph_user_text" styleId="c_email" property="c_email" maxlength="20"/></td>
                    </tr>
                    <tr>
                        <td>验&nbsp;证&nbsp;码：</td>
                        <td><html-el:text styleClass="ph_user_code" styleId="verificationCode" property="verificationCode" />&nbsp;&nbsp;<a class="roundCode" hidefocus="hideFocus" style="hide-focus: true"><img src="${ctx}/images/VerificationCode.jpg" width="76" height="24" style="border:1px solid #A1BCA3;cursor:pointer;" alt="验证码，看不清楚请用鼠标点击此处！" onclick="$(this).hide().attr('src', this.src + '?' + new Date().getTime()).fadeIn();" /></a></td>
                    </tr>
                    <tr>
                        <td colspan="3"><input class="inputbtn" type="button" name="Submit4" id="btn_submit" value="提交" /></td>
                    </tr>
                </tbody>
            </table>
        </html-el:form>
        </div>
        </div>
</div> 
</div> 
<jsp:include page="../__inc/footer.jsp" flush="true" /> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript">
$(".ph_user_area").focus(function(){
	var ph_area=$(this).val();
	if(ph_area==""){
		$(".ph_explan").hide();
	}	
});
$(".ph_user_area").blur(function(){
	var ph_area=$(this).val();
	if(ph_area==""){
		$(".ph_explan").show();
	}	
});

$(".ph_user_area").textbox({
	maxLength: 800,
	onInput: function(event, status) {
		var img = '<img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" />';
		$("#info_tip").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
	}
}).blur(function() {
	$("#info_tip").slideUp("normal");
});


	
	$("#btn_submit").click(function(){
		var isSubmit = Validator.Validate(this.form,3);
		if (isSubmit) {

			if($('#c_name').val() == ''){
				alert('客户姓名不能为空！');
				$('#c_name').focus();
				return;
			}
			if($('#c_tel').val() == ''){
				alert('联系电话不能为空！');
				$('#c_tel').focus();
				return;
			}else{
				if(!/^1[3|4|5|8][0-9]\d{4,8}$/.test($('#c_tel').val()) && !/^0\d{2,4}-?\d{7,8}$/.test($('#c_tel').val()))
				{
					alert('联系电话格式不正确！');
					$('#c_tel').focus();
					return;
				}
			}
			if($('#c_dw_name').val() == ''){
				alert('单位名称不能为空！');
				$('#c_dw_name').focus();
				return;
			}
			if($('#c_email').val() == ''){
				alert('邮箱地址不能为空！');
				$('#c_email').focus();
				return;
			}
			else{
				var myreg = /^(([0-9a-zA-Z]+)|([0-9a-zA-Z]+[_.0-9a-zA-Z-]*[0-9a-zA-Z]+))@([a-zA-Z0-9-]+[.])+([a-zA-Z]{2}|net|com|gov|mil|org|edu|int)$/;
				if(!myreg.test(document.getElementById('c_email').value)){
				alert('邮箱地址必须为合法邮箱格式!');
				document.getElementById('c_email').focus();
				$('#c_email').focus();
				return;
				}
			}

			if($('#verificationCode').val() == ''){
				alert('验证码不能为空！');
				$('#verificationCode').focus();
				return;
			}
			
		 $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
		 this.form.submit();
		}
	});

	

	

</script>
</body>
</html>