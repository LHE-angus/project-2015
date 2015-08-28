<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="code-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />  
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/fancybox/fancybox.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">   
</script>
<style type="text/css">
	.np-post-body{ padding-left:10px;border-bottom:solid 1px}
	.np-post-header{margin-right:3px;}
	.np-post-code{ margin-top:1px;} 
</style>
</head>
<body >  
<div id="d1" > 
  <form action="" method="post" name="apLogin" id="apLogin">   
		<div style="height:60px;"><input name="code" id="code" style="height:40px;margin-left:20%;" maxlength="20"></input><font color="red" id="u_msg"></font><br/></div>
		<div style="margin-left:30%">  
			<input name="login791" id="sbt_login" type="button" class="buttom" value="录入" style="width: 100px;height: 60px;"/>	   
		</div>
</form>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script> 

<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#code" ).attr("focus",setOnlyPosNum);   
	
	$("#sbt_login").click(function(){
		var code=$('#code').val();
		if(code.length>20){$('#u_msg').html('最多只能输入20个字符');return false;	}
		if(code==''){$('#u_msg').html('请输入纯数字的验证码');return false;	}
		$.ajax({
			type: "POST",url: "<c:url value='/manager/spgl/ShopInCode.do' />",
			data: {"method":"ajaxSave", "code":code, "timestamp":new Date().getTime() },
			dataType: "json",sync: true,
			error: function (xhr, ajaxOptions, thrownError) {},
			success: function(result){
				if(result.status=='1'){ 
					$('#code').val('');
					$('#u_msg').html('恭喜！录入成功');
					alert("订单流水号:"+result.trade_index+"\n金额:"+result.price+"\n数量:"+result.num+"\n下单时间:"+result.add_date);
				}else if(result.status=='-1'){
					$('#code').val('');    
					$('#u_msg').html('你录入的验证码不正确');   
				}else if(result.status=='-2'){
					$('#code').val('');    
					$('#u_msg').html('该验证码已使用'); 
				}
			}
		}); 
	});
	 //test();
});  
//正数
function setOnlyPosNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}
		if(this.value.length == 0 || this.value == 'undefined') this.value = "0";
	});
	//this.text.selected;
}


//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
