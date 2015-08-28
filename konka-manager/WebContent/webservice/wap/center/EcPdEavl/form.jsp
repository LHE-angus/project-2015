<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>触网</title> 
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/global.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/epp_index.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/address.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/zxmall/2015/css/star.css" />
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
<body><div class="top_class"><span class="lspan"><a href="javascript:void(0);history.back();"><img src="${ctx}/styles/epp/mobile/images/return.gif" /></a></span><h3>收货地址编辑</h3><a href="javascript:void(0);showNav();" id="btnJdkey" class="new-a-jd"><span>触网</span></a>
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
	  <p style="margin-top:5px;font-size:14px;">商品评价  - <c:out value="${details.pd_name}"></c:out> </p>
      <html-el:form action="/wap/center/EcPdEavl.do" enctype="multipart/form-data">
        <html-el:hidden property="method" styleId="method" value="save" />  
	    <input type="hidden"  name="goods_id" value="${af.map.goods_id }"/>
	    <input type="hidden"  name="detail_id" value="${af.map.detail_id }" id="detail_id"/> 	   
	    <span class="new-txt2 new-mg-b5">评分：</span>
		<span class="pj-s-cont"> 
			<input id="eval_score" type="hidden" name="eval_score" value="5"   />
		    <span class="pj-star-icon on" onclick="selSurvey(3,1)" id="style_3_1" val="评分">星星</span>
		    <span class="pj-star-icon on" onclick="selSurvey(3,2)" id="style_3_2" val="评分">星星</span>
		    <span class="pj-star-icon on" onclick="selSurvey(3,3)" id="style_3_3" val="评分">星星</span>
		    <span class="pj-star-icon on" onclick="selSurvey(3,4)" id="style_3_4" val="评分">星星</span>
		    <span class="pj-star-icon on" onclick="selSurvey(3,5)" id="style_3_5" val="评分">星星</span>
		</span><br/>
	    <span class="new-txt2 new-mg-b5">心得：</span>
		<span class="new-input-span new-mg-b10"> <input type="text" name="eval_con_sumary" class="new-input" maxlength="100" id="eval_title"/> </span>
		<span class="new-txt2 new-mg-b5">晒单:</span>
		<span  class="new-input-span new-mg-b10" id="img_div" ></span> 
	    <span style="display:none"><input id="sendImage1" type="file" name="sendImage1" value="" accept=".jpg,jpeg,.JPG,.JPEG" /><input id="sendImage2" type="file" name="sendImage2" value="" accept=".jpg,jpeg,.JPG,.JPEG"/><input id="sendImage3" type="file" name="sendImage3" value="" accept=".jpg,jpeg,.JPG,.JPEG"/></span>
	      	<br/>  
	      	<label for="sendImage1" title="上传图片" id="label_1" style=""><font size="20">+</font> </label>
	        <label for="sendImage2" title="上传图片" id="label_2" style="display:none;"><font size="20">+</font> </label>
	        <label for="sendImage3" title="上传图片" id="label_3" style="display:none;"><font size="20">+</font> </label>
	        	图片上传：只支持 *.jpg格式文件 
	    <div id="ProComment_PanLogin" > 
	     	 <a href="javascript:void();" name="btn_submit" id="btn_submit" class="new-abtn-type new-mg-t15">提交</a>
	    </div> 
 	</html-el:form>  
<div class="clear"></div>
</div>
</div> 
</div>
</body> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript">//<![CDATA[   
var c=0;                                         
$(document).ready(function(){ 
	 document.getElementById('sendImage1').addEventListener('change', function() {
         if (this.files.length != 0) {
             var file = this.files[0], reader = new FileReader() ;
             reader.onload = function(e) {
               showLabel();
               displayImage(e.target.result,1);
             };
             reader.readAsDataURL(file);
         };
     }, false); 
	 document.getElementById('sendImage2').addEventListener('change', function() {
         if (this.files.length != 0) {
             var file = this.files[0], reader = new FileReader() ;
             reader.onload = function(e) {

                 showLabel();
               displayImage(e.target.result,2);
             };
             reader.readAsDataURL(file);
         };
     }, false); 
	 document.getElementById('sendImage3').addEventListener('change', function() {
         if (this.files.length != 0) {
             var file = this.files[0], reader = new FileReader() ;
             reader.onload = function(e) { 
               showLabel();
               displayImage(e.target.result,3);
             };
             reader.readAsDataURL(file);
         };
     }, false); 

	$("#eval_con_sumary").keyup(check);   
	$("#eval_con_sumary").attr("dataType", "Require").attr("msg", "请填写心得！"); 
	
	$("#btn_submit").click(function(){
		if($("#detail_id").val()==""){
			alert('请先购买产品再评论');
			return false;
		}
		if(c==0){
			var str = $("#eval_con_sumary").val(); 
			str = trim(str);  
			if(str==""){
				alert("心得内容不能为空");
				return false;
				
			} 
			c=1; 
			$("#eval_con_sumary").val(str);
			document.forms[0].submit();
		} 
	});
     
});

function check() {
    var str = $("#eval_con_sumary").val();  
    var len = strlen(str);  
    var info = 250 - len;  
    info = info + "";  
    if(info.indexOf('.') > 0){
        info = info.substring(0, info.indexOf('.'));
    } 
    if(len <= 250) {
    	c=0;
        $("#info1").html("还能输入");
        $("#info").css('color', 'gray');
        $("#info").html(info);
    }else {
    	c=1;
        info = info.substr(1)
        $("#info1").html("超出");
        $("#info").css('color', 'red');
        $("#info").html(info);
     }
}  

function trim(str) {
    return (str + '').replace(/(\s+)$/g, '').replace(/^\s+/g, '');
}

function strlen(str) {
    str = trim(str);  
    var len = 0;  
    for (var i = 0; i < str.length; i++) {  
        len += str.charCodeAt(i) > 0 && str.charCodeAt(i) < 255 ? 0.5 : 1;  
    }
    return len;
}
 
function displayImage(imgData,index) {
   var html ='<span id="img_'+index+'"><a href="' + imgData + '" target="_blank"><img src="' + imgData + '" width="60"/></a> <a href="javascript:void(0);" title="删除" onclick="remove_img(\''+index+'\');"><font color="red" size="7">×</font></a></span>'; 
   document.getElementById("img_div").innerHTML=document.getElementById("img_div").innerHTML+html;
}

function remove_img(index){
	var span_img = document.getElementById("img_"+index);
	span_img.parentNode.removeChild(span_img); 
	document.getElementById("sendImage"+index).value="";
	showLabel();
}

function showLabel() {
	var flg=true;
	for(var i=1;i<4;i++){ 
		document.getElementById("label_"+i).style.display="none"; 
	}
	for(var i=1;i<4;i++){ 
		document.getElementById("label_"+i).style.display="none";
		if(flg&&document.getElementById("sendImage"+i).value==""){ 
			flg=false; 
			document.getElementById("label_"+i).style.display="block";
		} 
	}
}

function selSurvey(n,fid){
	for(var i=1; i<=5; i++){
		if($('#style_'+n+'_'+i).hasClass("on")){
			$('#style_'+n+'_'+i).removeClass("on");
		}
	}
	
	for(var i=1; i<=fid; i++){
		$('#style_'+n+'_'+i).addClass("on");
	}
	$('#eval_score').val(fid);
}


     
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