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
<style type="text/css">
.login_div{position: absolute;	border: solid 1px #d1e3f5;top:35%;text-align: center;border:1px solid #E03425;background: #f5f4f4;left:40%;width:400px;padding-bottom: 0px;padding-top: 0px;display: none;z-index:10002;} 
fieldset {text-align:left;padding:10px;margin-top:5px; background:#fff;}
fieldset legend {color:#1E7ACE;font-weight:bold;padding:3px 20px 3px 20px;border:1px solid #E03425;background:#fff;}
fieldset label {float:left;width:100px;text-align:right;padding:4px;margin:1px;}
fieldset div {clear:left;margin-top:15px;margin-bottom:18px;}   
fieldset .buttom {width:44px; padding:1px 10px; margin-left:80px;font-size:12px;border:1px #1E7ACE solid;background:#D0F0FF;}
fieldset .buttom1 {width:44px; padding:1px 10px; margin-left:10px;font-size:12px;border:1px #1E7ACE solid;background:#D0F0FF;}
#login_bg_div {display: none; position: absolute; top: 0%; left: 0%; width: 100%; height: 100%; background-color: black; z-index:10000; -moz-opacity: 0.7; opacity:.70; filter: alpha(opacity=60);}
 
.zxmall_form_table1 td{
	padding: 8px 0px 6px 10px; 
}
</style>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
</head>
<body id="web_body">
<jsp:include page="/zxmall/__inc/2015/top.jsp" flush="true" />  
<jsp:include page="/zxmall/__inc/2015/nav.jsp" flush="true" />  
<div class="main" style="padding-top:0px;">
<div class="w1200">
<!-- second start -->
<div class="maincont">
  <jsp:include page="/zxmall/__inc/_mleft.jsp" flush="true" />
 <!--  
  <div class="login_div" id="login_div" style="display:none;z-index:10000;position:absolute;">
<form action="" method="post" name="apLogin" id="apLogin">
	<fieldset>		
		<h3><span id="x_close" title="关闭" style="float:right;font-size:18px;color:red;cursor:pointer;">×</span></h3>
		<font id="login_msg" color="#E03425"></font>
		<div><label for="vouchers_code">优惠券编码</label><input type="text" name="vouchers_code" id="vouchers_code" size="18" maxlength="30" /><font color="red" id="u_msg"></font><br/></div>
		<div><label for="vouchers_pwd">优惠券密码</label><input type="password" name="vouchers_pwd" id="vouchers_pwd" size="18" maxlength="30" /><font color="red" id="p_msg"></font><br/> </div>
		<div class="cookiechk">
			<input name="login791" id="sbt_login" type="button" class="buttom" value="录入" />	<input name="cancel" type="button" id="login_close" class="buttom1" value="取消" />  
		</div>
	</fieldset>
</form>
</div>  
-->
  <!--right-->
  <div class="zxmall_right padbot45">
    <div class="position"><a href="${ctx }/zxmall/Index.do">首页</a> &gt; <a href="${ctx }/zxmall/center/Index.do">会员中心</a> &gt; 购物券兑换</div>
    <div class="zxmalltab3">
        <%@ include file="/commons/pages/messages.jsp" %>
         <p style="margin-top:15px;font-size:14px;"> 您当前的积分:<span style="color:#FF2200;font-weight:bold;">${totalScore}</span></p>
        <p style="margin-top:10px;color:#fc7200;font-weight:bold;">可兑换列表</p>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="zxmall_form_table1">
          <tr class="tr1">
          <td width="20%" nowrap="nowrap" align="center">兑换券名称</td>
          <td width="10%" nowrap="nowrap" align="center">金额(元)</td>
          <td width="10%" nowrap="nowrap" align="center">所需积分</td>
          <td width="10%" nowrap="nowrap" align="center">操作</td>
          </tr>
           <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr height="30">
              <td align="center" nowrap="nowrap">${cur.title}</td>
              <td align="center" nowrap="nowrap"><fmt:formatNumber type="number" value="${cur.price}" pattern="#0.00"/></td>
              <td align="center" nowrap="nowrap"> <fmt:formatNumber type="number" value="${cur.price*100}" maxFractionDigits="0"/></td>
              <td align="center" nowrap="nowrap"><a href="${ctx}/zxmall/center/EcVouchers.do?method=add&id=${cur.id}">兑换</a></td>
            </tr>
          </c:forEach>
          <c:if test="${empty entityList}">
            <tr height="60">
              <td align="center" nowrap="nowrap" colspan="5"> 暂无可兑换券 </td>
            </tr>
          </c:if>
        </tbody>
        </table>
        <form action="${ctx}/zxmall/center/EcVouchers.do" id="f1" >
        <input type="hidden" name="method" value="save1"/>
        <p style="margin-top:10px;color:#fc7200;font-weight:bold;">其他</p>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="zxmall_form_table1">
          <tr>
            <td width="80%" nowrap="nowrap">
               	兑换金额：<input id="dh_money" name="dh_money" maxlength="6" onfocus="javascript:setOnlyInt(this);"/>
               	所需积分：<input id="dh_jf" name="dh_jf" readonly="readonly"/> <span id="s1"></span>
            </td>
            <td align="center" width="20%">
               <input class="inputbtn" type="button" name="Submit4" id="btn_submit" value="兑换" />
            </td>
          </tr>
        </table>
        </form> 
        <form action="${ctx}/zxmall/center/EcVouchers.do" id="f2"  >    
        <input type="hidden" name="method" value="ajaxSave"/>
        <p style="margin-top:10px;color:#fc7200;font-weight:bold;">绑定</p>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="zxmall_form_table1">
          <tr>
            <td width="80%" nowrap="nowrap">
               	优惠券编码：<input id="vouchers_code" name="vouchers_code" maxlength="20" />
               	优惠券密码：<input id="vouchers_pwd" name="vouchers_pwd" maxlength="20"/> 
            </td>
            <td align="center" width="20%">
               <input class="inputbtn" type="button" name="Submit4" id="btn_submit_for_ec" value="绑定" />
            </td>
          </tr>
        </table>
        </form>     
        <div style="margin-top:10px;color:#fc7200;font-weight:bold;display: none;"><input class="inputbtn" type="button" name="Submit4" id="btn_submit_2" value="录入"/> </div>       
        <p style="margin-top:30px;color:#fc7200;font-weight:bold;">我的购物券&nbsp;&nbsp;</p>    
         <table width="100%" border="0" cellpadding="0" cellspacing="0" class="zxmall_form_table1" style="table-layout:fixed;">  
          <tr class="tr1">
          <td width="20%" nowrap="nowrap" align="center">优惠券名称</td>
          <td width="10%" nowrap="nowrap" align="center">金额(元)</td>
          <td align="center">可使用商品</td>  
          <td width="10%" nowrap="nowrap" align="center">可使用类别</td>
          <td width="10%" nowrap="nowrap" align="center">可使用属性</td>
          <td width="10%" nowrap="nowrap" align="center">有效开始时间</td>
          <td width="10%" nowrap="nowrap" align="center">有效截止时间</td>
          <td width="10%" nowrap="nowrap" align="center">来源</td>
          <td width="10%" nowrap="nowrap" align="center">使用状态</td>
          </tr>
           <tbody>
          <c:forEach var="cur" items="${entityList1}" varStatus="vs">
            <tr height="30">
              <td align="center" nowrap="nowrap">${cur.title}</td>
              <td align="center" nowrap="nowrap"><fmt:formatNumber type="number" value="${cur.price}" pattern="#0.00"/></td>
           	  <td align="center" style="word-break:break-all;">   
           	  <c:if test="${empty cur.map.pd_sn}">--</c:if>
           	  <c:if test="${not empty cur.map.pd_sn}">${cur.map.pd_sn}</c:if>
           	  </td>	
           	  <td align="center" nowrap="nowrap">
           	  <c:if test="${empty cur.map.pd_type}">所有</c:if>
           	  <c:if test="${not empty cur.map.pd_type}">${cur.map.pd_type}</c:if>
           	  </td>	
           	  <td align="center" nowrap="nowrap">
           	 <c:if test="${empty cur.map.goods_type}">所有</c:if>
           	  <c:if test="${not empty cur.map.goods_type}">${cur.map.goods_type}</c:if>
           	  </td>
           	  <td align="center" nowrap="nowrap">
           	  	<fmt:formatDate value="${cur.start_date}" pattern="yyyy-MM-dd"/>
           	  </td>
           	  <td align="center" nowrap="nowrap">
           	  	<fmt:formatDate value="${cur.effective_date}" pattern="yyyy-MM-dd"/>
           	  </td>
           	  <td align="center" nowrap="nowrap">
           	  <c:if test="${empty cur.vouchers_code and empty cur.vouchers_pwd}">积分兑换</c:if>
           	  <c:if test="${not empty cur.vouchers_code or not empty cur.vouchers_pwd}">申请发放</c:if> 
           	  </td>		
           	  <td align="center" nowrap="nowrap">
           	  <c:if test="${cur.info_state eq 0}">未使用</c:if>
           	  <c:if test="${cur.info_state eq 1}">已使用</c:if>  
           	  </td>	
            </tr>
          </c:forEach>
          <c:if test="${empty entityList1}">  
            <tr height="60">
              <td align="center" nowrap="nowrap" colspan="9"> 暂无兑换券 </td>
            </tr>
          </c:if>
        </tbody>
        </table>
    </div>
  </div>
  <div class="clear"></div>
  </div>
</div> 
</div>
<jsp:include page="/zxmall/__inc/2015/footer.jsp" flush="true" /> 
<!-- six footer -->
</body>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[

var i=0;
$(document).ready(function(){
	var ff=0;
	$("#dh_money").change(function(){
		var dh_money=$("#dh_money").val();
		$("#dh_jf").val(dh_money*100);
		var dh_jf = $("#dh_jf").val();
		var tt=${totalScore};
		if(dh_jf>tt){
			ff=1;
			$("#s1").html("<font color='red'>积分不够</font>");
		}else if(dh_jf==0){
			ff=2;
			$("#s1").html("<font color='red'>兑换金额不能为0</font>");
		}else{
			ff=0;
			$("#s1").text('');	
		}
	});

	//$("#btn_submit_2").click(function(){$("#login_div").fadeIn(500);$("#login_msg").html('');});
	//$("#login_close").click(function(){$("#login_div").fadeOut(500);});  
	//$("#x_close").click(function(){$("#login_div").fadeOut(500);});  

	//if(i==0){  
		//i++;
		//$("#sbt_login").click(function(){
			//var vouchers_code=$('#vouchers_code').val();
			//var vouchers_pwd=$('#vouchers_pwd').val();
			//$('#u_msg').html('');
			//$('#p_msg').html('');
			//if(vouchers_code!=null){vouchers_code=vouchers_code.replace(/(^\s*)|(\s*$)/g, "");}
			//if(vouchers_pwd!=null){vouchers_pwd=vouchers_pwd.replace(/(^\s*)|(\s*$)/g, "");}
			//if(vouchers_code==''){$('#u_msg').html('请输入优惠券编码');return false;	}
			//if(vouchers_pwd==''){$('#p_msg').html('请输入密码');	return false;} 
			//this.disabled=true;
			//this.value="正在验证。。。"; 
			//$.ajax({
				//type: "POST",
				//url:"${ctx}/zxmall/center/EcVouchers.do",  
				//data: {"method":"ajaxValide", "vouchers_code":vouchers_code, "vouchers_pwd":vouchers_pwd , "timestamp":new Date().getTime() },
				//dataType: "text",sync: true,
				//error: function (xhr, ajaxOptions, thrownError) {document.getElementById("sbt_login").value="录入";document.getElementById("sbt_login").disabled=false; 
				//},success: function(result){
					//if(result=='1'){ 
						//$("#login_div").hide();
						//alert('恭喜您!录入成功');location.reload();
					//}else{ 
						//$('#u_msg').html('优惠券编码或密码错误');document.getElementById("sbt_login").value="录入";document.getElementById("sbt_login").disabled=false; 
					//}
				//}
			//});
		//});
	//}
	

	$("#btn_submit").click(function(){
		var f1=document.getElementById("f1");
		$("#dh_money").attr("dataType", "Require").attr("msg", "兑换金额不能为空");
		
		var isSubmit = Validator.Validate(f1,1); 
		if(ff==1){
			$("#dh_money").focus();
			alert('积分不够');
			return false;
		}else if(ff==2){
			$("#dh_money").focus();
			alert('兑换金额不能为0');
			return false;
		}else if($("#dh_money").val()==null){
			alert('兑换金额不能为空');
			return false;
		}
		if (isSubmit) {
			$("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
			f1.submit();
		}
	});


	$("#btn_submit_for_ec").click(function(){
		var f2=document.getElementById("f2");  

		$("#vouchers_code").attr("dataType", "Require").attr("msg", "优惠券编码不能为空");
		//$("#vouchers_pwd").attr("dataType", "Require").attr("msg", "优惠券密码不能为空");
		var isSubmit = Validator.Validate(f2,1);
		
		if (isSubmit) {
			$("#btn_submit_for_ec").attr("value", "正在提交...").attr("disabled", "true");
			f2.submit();
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

function setOnlyNum(obj) {
	$(obj).css("ime-mode", "disabled");
	$(obj).attr("t_value", "");
	$(obj).attr("o_value", "");
	$(obj).bind("dragenter",function(){
		return false;
	});
	$(obj).keypress(function (){
		if(!obj.value.match(/^[\+\-]?\d*?\.?\d*?$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))obj.o_value=obj.value;
	}).keyup(function (){
		if(!obj.value.match(/^[\+\-]?\d*?\.?\d*?$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))obj.o_value=obj.value;
	}).blur(function (){
		if(!obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\.\d+$/))obj.value=0+obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
		if(isNaN(obj.value)) obj.value = "";
	});
}
//]]></script>
</html>
