<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %> 
<style type="text/css">
#biz-service b{background-image:url(${ctx}/styles/sfmall/images/jiantou.png);background-repeat:no-repeat;position:absolute;overflow:hidden}
#biz-service .hover .dd{position:absolute;border:1px solid #DDD;background:#fff;-moz-box-shadow:0 0 10px rgba(0,0,0,.12);-webkit-box-shadow:0 0 10px rgba(0,0,0,.12);box-shadow:0 0 10px rgba(0,0,0,.12)}
#biz-service .hover .dd{left:5px;width:73px} 
#biz-service .hover .dd div{padding-left:6px;line-height:22px} 
</style>
<div class="top-t" style="position:fixed;left:0;top:0;z-index:99999;">
	<div class="top">
	    <div class="le"><a href="<c:url value='/sfmall/Index.do' />">欢迎光临  康佳&顺丰网上直销商城! </a> </div>
	    <div class="m"><a href="${ctx}/sfmall/KonkaGroupPeArticleInfo.do?method=view&id=811305" ><font color="red">新手上路</font></a></div>
	    <div class="ri"><c:if test="${not empty ecUser}">${ecUser.real_name} | &nbsp;  <a href="${ctx}/sfmall/center/Index.do">会员中心</a> | <a href="${ctx}/sfmall/login.do?method=logout">退出登陆</a></c:if> <c:if test="${empty ecUser}">您还未登录，请先[<a id="top_login" href="#">登录</a>]</c:if> | <a href="<c:url value='/sfmall/KonkaGroupPeArticleInfo.do?' />method=view&id=751921">常见问题</a> | <a href="<c:url value='/sfmall/KonkaGroupPeArticleInfo.do?' />method=view&id=751913">客户服务</a>
	    	| <div <c:if test="${not empty ecUser}">style="width: 55px;margin-left:365px;" </c:if><c:if test="${empty ecUser}">style="width: 55px;margin-left:275px;" </c:if>id="biz-service" onmouseover="mOver(this)" onmouseout="mOut(this)">&nbsp;
			<div style="width:68px;margin-top:-52px;" id="s1">&nbsp;综合服务  
					<b style="top: 10px;width: 7px;height: 4px;background-position: -95px -55px" id="b1"></b> 
			</div>
			<div class="dd" id="d1" style="display:none;width:68px;position:absolute;border:1px solid #DDD;background:#fff;-moz-box-shadow:0 0 10px rgba(0,0,0,.12);-webkit-box-shadow:0 0 10px rgba(0,0,0,.12);box-shadow:0 0 10px rgba(0,0,0,.12)">
				<script src="${ctx }/sfmall/KonkaGroupPeArticleInfo.do?method=jsNewsSf&article_type=3300"></script> 
			</div>
		</div>
	    </div>
	</div>
</div><c:if test="${empty ecUser}"> 
<style type="text/css">
.login_div{position: absolute;	border: solid 1px #d1e3f5;top:35%;text-align: center;border:1px solid #E03425;background: #f5f4f4;left:40%;width:350px;padding-bottom: 0px;padding-top: 0px;display: none;z-index:10002;} 
fieldset {text-align:left;padding:10px;margin-top:5px; background:#fff;}
fieldset legend {color:#1E7ACE;font-weight:bold;padding:3px 20px 3px 20px;border:1px solid #E03425;background:#fff;}
fieldset label {float:left;width:100px;text-align:right;padding:4px;margin:1px;}
fieldset div {clear:left;margin-top:15px;margin-bottom:18px;}
fieldset .buttom {width:44px; padding:1px 10px; margin-left:10px;font-size:12px;border:1px #1E7ACE solid;background:#D0F0FF;}
#login_bg_div {display: none; position: absolute; top: 0%; left: 0%; width: 100%; height: 100%; background-color: black; z-index:10000; -moz-opacity: 0.7; opacity:.70; filter: alpha(opacity=60);}
</style> 
<div class="login_div" id="login_div" style="display:none;z-index:10000;position:absolute;">
<form action="" method="post" name="apLogin" id="apLogin">
	<fieldset>		
		<h3>顺丰用户登录<span id="x_close" title="关闭" style="float:right;font-size:18px;color:red;cursor:pointer;">×</span></h3>
		<font id="login_msg" color="#E03425"></font>
		<div><label for="user_name">用户名</label><input type="text" name="user_name" id="user_name" size="18" maxlength="30" /><font color="red" id="u_msg"></font><br/></div>
		<div><label for="password">密　码</label><input type="password" name="password" id="password" size="18" maxlength="30" /><font color="red" id="p_msg"></font><br/> </div>
		<div class="cookiechk">
			<label><input type="checkbox" name="is_remember" id="is_remember" value="1" /><a href="#" title="选择是否记录您的信息">记住我</a></label>
			<input name="login791" id="sbt_login" type="button" class="buttom" value="登录" />	<input name="cancel" type="button" id="login_close" class="buttom" value="取消" />
		</div>
	</fieldset>
</form>
</div>
<script type="text/javascript">
$(document).ready(function(){ 
	$("#top_login").click(function(){$("#login_div").fadeIn(500);$("#login_msg").html('');});
	$("#login_close").click(function(){$("#login_div").fadeOut(500);});  
	$("#x_close").click(function(){$("#login_div").fadeOut(500);});  
	<c:if test="${af.map.is_login eq 0}">$("#login_div").fadeIn(500);$("#login_msg").html('<br/>&nbsp;&nbsp;请先登录康佳网上直销商城');</c:if>
	$("#sbt_login").click(function(){
		var user_name=$('#user_name').val();
		var pass_word=$('#password').val();
		$('#u_msg').html('');
		$('#p_msg').html('');
		if(user_name!=null){user_name=user_name.replace(/(^\s*)|(\s*$)/g, "");}
		if(pass_word!=null){pass_word=pass_word.replace(/(^\s*)|(\s*$)/g, "");}
		if(user_name==''){$('#u_msg').html('请输入用户名');return false;	}
		if(pass_word==''){$('#p_msg').html('请输入密码');	return false;} 
		this.disabled=true;
		this.value="正在验证。。。"; 
		$.ajax({
			type: "POST",url: "<c:url value='/sfmall/login.do' />",
			data: {"method":"ajax", "user_name":user_name, "password":pass_word , "timestamp":new Date().getTime() },
			dataType: "text",sync: true,
			error: function (xhr, ajaxOptions, thrownError) {alert('对不起登录失败，请稍后再试。。。');document.getElementById("sbt_login").value="登录";document.getElementById("sbt_login").disabled=false; 
			},success: function(result){
				if(result=='1'){ 
					$("#login_div").hide();
					alert('尊敬的顺丰嘿客人员，欢迎登录康佳&顺丰直销平台\n'+
							'温馨提示\n'+
							'一、	平台发货时间：\n'+
							'电视：每天17点由顺丰速运人员去我司仓库取当日件配送；【平常周日不发货，于周一统一发；另外每月我司封账期间可以下单，发货时间为下月一号】\n'+
							'小家电：每天15点由顺丰速运人员去我司仓库取当日件配送；【平常周六、日不发货，于周一统一发】\n'+
							'二、	告知消费者：\n'+
							'1、收到快递时一定要开箱验机，如果出现屏碎机需要直接拒收，并由嘿客门店人员于当天第一时间告知平台客服，否则产生的费用需要由顺丰或者消费者承担；\n'+
							'2、如若消费者在签收后7天内电视通电，发现屏幕内屏碎裂\n'+
							'（1）先打电话给4008800016，由售后上门开具鉴定单。\n'+
							'（2）带着鉴定单到嘿客门店，由嘿客门店人员电话告知平台客服；\n'+
							'3、严禁顺丰门店人员私自拖回消费者问题机器，需要由我司售后人员上门取件。\n');location.reload();
				}else{ 
					$('#u_msg').html('用户名或密码错误');document.getElementById("sbt_login").value="登录";document.getElementById("sbt_login").disabled=false; 
				}
			}
		});
	});
}); 

function showLogin(msg){ 
	$("#login_div").fadeIn(500);
	$("#login_msg").html(msg);	
	$('#u_msg').html('');
	$('#p_msg').html('');
}
</script></c:if>
<script type="text/javascript">
function mOver(obj){
	$("#b1").css({"background-position":"-95px -45px"});
	$("#s1").css({"border":"0px solid #DDD","background":"#fff"});
	$("#d1").show();
}
function mOut(obj){
	$("#b1").css({"background-position":"-95px -55px"});
	$("#s1").css({"border":"","background":""});    
	$("#d1").hide();
}

</script>