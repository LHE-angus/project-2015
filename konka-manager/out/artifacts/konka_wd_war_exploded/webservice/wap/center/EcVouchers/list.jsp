<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" />  
<title>触网</title> 
<link rel=stylesheet type=text/css href="${ctx}/mobile/css/common.css" />
<link rel=stylesheet type=text/css href="${ctx}/styles/wap/css/css.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script>
<style type="text/css">
.xt_fenye {font-weight: normal;height: 58px;line-height: 58px;text-align: right;padding-right: 30px;padding-top: 20px;}.top_class {height:54px;margin:0 auto;clear:both;background-color:#df511f;}.top_class h3 {font-size:24px;font-family:"Microsoft YaHei";text-align:center;line-height:54px;color:#fff;}.top_class span {float:left;padding-top:19px;padding-left:5%;}.cont1c {margin:0 auto;clear:both;width:100%;padding:10px 0;}
.but_ping {background-color:#e80000;width:100%;height:50px;border:0; text-align: center;font-style:normal;font-family:"Microsoft YaHei";font-size:22px;line-height:50px;color:#FFF;letter-spacing:2px;cursor:pointer;border-radius:3px;}
</style>
</head>
<body>
<div class="top_class"><span class="lspan"><a href="<c:url value='/webservice/wap/center/Index.do?'/>"><img src="${ctx}/styles/epp/mobile/images/return.gif" /></a></span><h3>我的购物券</h3></div><div class="maincont"><!--right-->
<div class="member_right padbot45">
<div class="membertab3">
 <p style="margin-top:15px;font-size:14px;"> 您当前的积分:<span style="color:#FF2200;font-weight:bold;">${totalScore}</span></p>
  <form action="${ctx}/webservice/wap/center/EcVouchers.do" id="f1" >
        <input type="hidden" name="method" value="save1"/>
        <p style="margin-left:10px;margin-top:10px;color:#fc7200;font-weight:bold;">兑换购物券</p>
        <table  style="width:100%;">
          <tr>
            <td>
               		&nbsp;&nbsp;兑换金额：<input id="dh_money" name="dh_money" maxlength="6" onfocus="javascript:setOnlyInt(this);"/><br/>
               		&nbsp;&nbsp;所需积分：<input id="dh_jf" name="dh_jf" readonly="readonly"/> <span id="s1"></span>
            </td>
           </tr>
           <tr>
            <td align="center">
               <input class="inputbtn" type="button" name="Submit4" id="btn_submit" value="兑换" />
            </td>
          </tr>
        </table>
</form> 
<form action="${ctx}/zxmall/center/EcVouchers.do" id="f2"  >    
       <input type="hidden" name="method" value="ajaxSave"/>
       <p style="margin-left:10px;margin-top:10px;color:#fc7200;font-weight:bold;">绑定</p>
       <table style="width:100%;">
          <tr>
            <td >
               	优惠券编码：<input id="vouchers_code" name="vouchers_code" maxlength="20" /><br/>
               	优惠券密码：<input id="vouchers_pwd" name="vouchers_pwd" maxlength="20"/> 
            </td>
             </tr>
           <tr>
            <td align="center" >
               <input class="inputbtn" type="button" name="Submit4" id="btn_submit_for_ec" value="绑定" />
            </td>
          </tr>
      </table>
</form>         
<div class="out">
	<div class="in">
		<p style="margin-top:30px;color:#fc7200;font-weight:bold;">我的购物券</p>    
		<table style="width:100%;"> 
          <tbody>
          <c:forEach var="cur" items="${entityList1}" varStatus="vs">
          <tr>
	          <td width="20%" nowrap="nowrap" align="left"><strong>优惠券名称</strong></td>
	          <td align="left"><strong>金额(元)</strong></td>  
          </tr>
          <tr>
              <td align="left" >${cur.title}</td>
              <td align="left" nowrap="nowrap"><fmt:formatNumber type="number" value="${cur.price}" pattern="#0.00"/></td>    
           </tr>
           <tr>
             <td align="left"  colspan="2"><strong>可使用商品 </strong><br/>
           	  <c:if test="${empty cur.map.pd_sn}">--</c:if>
           	  <c:if test="${not empty cur.map.pd_sn}">${cur.map.pd_sn}</c:if>
           	  </td>	
           </tr>
           <tr>             
	          <td><strong>可使用类别：</strong>
           	  <c:if test="${empty cur.map.pd_type}">所有</c:if>
           	  <c:if test="${not empty cur.map.pd_type}">${cur.map.pd_type}</c:if></td>
	          <td><strong>可使用属性：</strong>
           	 <c:if test="${empty cur.map.goods_type}">所有</c:if>
           	  <c:if test="${not empty cur.map.goods_type}">${cur.map.goods_type}</c:if>
           	  </td>
            </tr> 
            <tr>
             <td colspan="2"><strong>有效期：</strong> 
           	  	<fmt:formatDate value="${cur.start_date}" pattern="yyyy-MM-dd"/> - <fmt:formatDate value="${cur.effective_date}" pattern="yyyy-MM-dd"/>
           	  </td>
           	 </tr>
           	 <tr> 
           	  <td><strong>来源 :</strong>
           	  <c:if test="${empty cur.vouchers_code and empty cur.vouchers_pwd}">积分兑换</c:if>
           	  <c:if test="${not empty cur.vouchers_code or not empty cur.vouchers_pwd}">申请发放</c:if> 
           	  </td>
         	  <td><strong>使用状态: </strong>
	           	  <c:if test="${cur.info_state eq 0}">未使用</c:if>
	           	  <c:if test="${cur.info_state eq 1}">已使用</c:if>  
           	  </td>
           	 </tr>
           	 <tr><td colspan="2"><div class="divcss5-4"></div></td></tr>
          </c:forEach>
		</table>
	</div>
</div>
<c:if test="${empty entityList }">
<div style="margin:0 auto;" align="center"> <font style="font-size: 20px;">暂无购物券！</font> </div>
</c:if>
<c:if test="${af.map.pageCount >1}">
<div class="xt_fenye"><a onclick="goback('${af.map.currentPage}');"><img src="${ctx}/mobile/images/xt_shagnyiye.jpg" width="66" height="24" /></a>
<a onclick="goforward('${af.map.currentPage}');"><img src="${ctx}/mobile/images/xt_xiayye.jpg" width="66" height="24" /></a></div>
</c:if></div>
</div>
<div class="clear"></div>
</div>  
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

                                          
                                          
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return (r[2]);
    return null;
}

function exit(){
	location.href = "${ctx}/webservice/wap/login.do?method=logout";
}

var recordNum = '${af.map.recordCount}';
function goview(url){
	var href_value = "${ctx}/" + url;
	window.location.href = href_value ;
} 

function goPage(method,page,forward,pagelimit){
	pagelimit = Math.ceil(recordNum / pagelimit);
	if(page == 1 && forward == -1){
		alert("已到首页！");
		return false;
	}else if(page == pagelimit && forward == 1){
		alert("已到尾页！");
		return false;
	}else if(pagelimit == 0){
		alert("无翻页信息！");
		return false;
	}else{
		return true;
	}		
}

function goback(page) {
	if(goPage(null,page,-1,5)){
		window.location.href="Orders.do?"+"page="+page+"&forward=-1";
	}
}

function goforward(page,orderState) {
	if(goPage(null,page,1,5)){
		window.location.href="Orders.do?"+"page="+page+"&forward=1";
	}
}
 


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
