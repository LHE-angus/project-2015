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
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/ping.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/cloud-zoom/styles/image_show.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/zxmall.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
</head>
<body id="web_body">
<jsp:include page="/zxmall/__inc/2015/top.jsp" flush="true" />  
<jsp:include page="/zxmall/__inc/2015/nav.jsp" flush="true" />  
<div class="main" style="padding-top:0px;">
<div class="w1200">
<!-- second start -->
<div class="maincont">
  <jsp:include page="/zxmall/__inc/_mleft.jsp" flush="true" />
  <!--right-->
  <div class="zxmall_right padbot45">
    <div class="position"><a href="${ctx }/zxmall/Index.do">首页</a> &gt; <a href="${ctx }/zxmall/center/Index.do">会员中心</a> &gt;商品评价</div>
    <div class="zxmalltab3">
      <p style="margin-top:15px;font-size:14px;">商品评价  - <c:out value="${details.pd_name}"></c:out> </p>
      <html-el:form action="/center/EcPdEavl" enctype="multipart/form-data">
        <html-el:hidden property="method" styleId="method" value="save" />  
	    <input type="hidden"  name="goods_id" value="${af.map.goods_id }"/>
	    <input type="hidden"  name="detail_id" value="${af.map.detail_id }" id="detail_id"/> 
	    <table cellpadding="0" cellspacing="0" style="margin-top:5px;">
	      <tbody>
	        <tr>
	          <td valign="bottom" align="right"  width="62">评&nbsp;&nbsp;分：</td>
	          <td><table id="ProComment_rdo_point" border="0">
	              <tbody>
	                <tr>
	                  <td><input id="ProComment_rdo_point_0" type="radio" name="eval_score" value="5" checked="checked" />
	                    <label for="ProComment_rdo_point_0"><span class="star5"></span></label></td>
	                  <td><input id="ProComment_rdo_point_1" type="radio" name="eval_score" value="4" />
	                    <label for="ProComment_rdo_point_1"><span class="star4"></span></label></td>
	                  <td><input id="ProComment_rdo_point_2" type="radio" name="eval_score" value="3" />
	                    <label for="ProComment_rdo_point_2"><span class="star3"></span></label></td>
	                  <td><input id="ProComment_rdo_point_3" type="radio" name="eval_score" value="2" />
	                    <label for="ProComment_rdo_point_3"><span class="star2"></span></label></td>
	                  <td><input id="ProComment_rdo_point_4" type="radio" name="eval_score" value="1" />
	                    <label for="ProComment_rdo_point_4"><span class="star1"></span></label></td>
	                </tr>
	              </tbody>
	            </table></td>
	          <td> </td>
	        </tr>
	      </tbody>
	    </table>
	    <p style="height: 32px;">&nbsp;&nbsp;&nbsp;&nbsp;关键字：
	    <input type="text" name="eval_title" class="comm_input" maxlength="100" id="eval_title"/>
	    </p> 
	    <dl>&nbsp;&nbsp;&nbsp;&nbsp;心&nbsp;&nbsp;得：
	     	<textarea name="eval_con_sumary" rows="8" cols="20" id="eval_con_sumary" style="width:60%;"></textarea>
		    <label id="info1" style="font-size: 12px;"> 还能输入</label>
		    <span id="info" style=" font-size: 14px; ">250</span>
		    <label id="info2" style=" font-size: 12px;"> 个字</label>   
	    </dl> 
	    <dl>
	    <table>
	    <tr>
	     <td width="62" align="right">晒&nbsp;&nbsp;单： </td>
	      <td><span id="img_div"></span></td>
	     <td>
	    	<span style="position:absolute;top:-999px;left:-999px;" id="span_file">
	    	<input id="sendImage1" type="file" name="sendImage1" value="" accept=".jpg,jpeg,.JPG,.JPEG"  onchange="show(this,1);" />
	    	<input id="sendImage2" type="file" name="sendImage2" value="" accept=".jpg,jpeg,.JPG,.JPEG"  onchange="show(this,2);" />
	    	<input id="sendImage3" type="file" name="sendImage3" value="" accept=".jpg,jpeg,.JPG,.JPEG"  onchange="show(this,3);" />
	    	</span>
	        <label for="sendImage1" title="上传图片" id="label_1" style=""><font size="20">+</font> </label>
	        <label for="sendImage2" title="上传图片" id="label_2" style="display:none;"><font size="20">+</font> </label>
	        <label for="sendImage3" title="上传图片" id="label_3" style="display:none;"><font size="20">+</font> </label>
	        	图片上传：只支持 *.jpg格式文件
	       </td>
	    </tr>
	    </table>     	
    </dl> 
    <div id="ProComment_PanLogin" > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     	 <input type="button" class="btn_comment" id="btn_submit" /> 
    </div> 
        
      </html-el:form> 
    </div>
  </div>
  <div class="clear"></div>
</div>
</div> 
</div>
<jsp:include page="/zxmall/__inc/2015/footer.jsp" flush="true" />
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
var c=0;                                         
$(document).ready(function(){  
	$("#eval_con_sumary").keyup(check);  
	$("#eval_title"	).attr("dataType", "Require").attr("msg", "请填写关键字！"); 
	$("#eval_con_sumary").attr("dataType", "Require").attr("msg", "请填写心得！"); 
	
	$("#btn_submit").click(function(){
		if($("#detail_id").val()==""){
			alert('请先购买产品再评论');
			return false;
		}
		var isSubmit = Validator.Validate(this.form,3);
		if (isSubmit) {
			if(c==0){
				c=1; 
			    var str = $("#eval_con_sumary").val(); 
			    str = trim(str);  
			    $("#eval_con_sumary").val(str);
				 this.form.submit();
			}else{
				alert("心得字数超出");
			}
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
 
//显示图片 
function displayImage(imgData,index) {
   var html ='<span id="img_'+index+'"><a href="' + imgData + '" target="_blank"><img src="' + imgData + '" width="60"/></a> <a href="javascript:void(0);" title="删除" onclick="remove_img(\''+index+'\');"><font color="red" size="9">×</font></a></span>'; 
   document.getElementById("img_div").innerHTML=document.getElementById("img_div").innerHTML+html;
}
//ie浏览器兼容问题  显示图片文件名称 
function showImgName(index){
	var html="<span id='img_"+index+"' style='width:60px;height:20px;'>"+document.getElementById("sendImage"+index).value+"<a href='javascript:void(0);' title='删除' onclick='remove_img("+index+");'><font color='red' size='7'>×</font></a><br/></span>";
	 document.getElementById("img_div").innerHTML=document.getElementById("img_div").innerHTML+html;
} 

function remove_img(index){
	//删除图片
	var span_img = document.getElementById("img_"+index);
	span_img.parentNode.removeChild(span_img); 	
	//清空file值
	document.getElementById("sendImage"+index).parentNode.removeChild(document.getElementById("sendImage"+index)); 	
	var html ="<input id=\"sendImage"+index+"\" type=\"file\" name=\"sendImage"+index+"\" accept=\".jpg,jpeg,.JPG,.JPEG\" value=\"\" onchange=\"show(this,"+index+")\"/>"; 
	$("#span_file").append(html);
	showLabel();//重置
}

function showLabel() {
	var flg=true;
	for(var i=1;i<4;i++){ 
		document.getElementById("label_"+i).style.display="none"; 
	}
	for(var i=1;i<4;i++){  
		if(flg&&document.getElementById("sendImage"+i).value==""){ 
			flg=false; 
			document.getElementById("label_"+i).style.display="block";
		} 
	}
}

function show(o,index) {
	 if (!RegExp("\.(" +  "jpg|jpeg"+ ")$", "i").test(o.value.toLowerCase())) {
         alert("选择文件错误,图片格式仅支持jpg|jpeg");
         o.value = "";
         return false;
     }
	 try{		 
        if (o.files.length != 0) {       	
            var file = o.files[0], reader = new FileReader() ;
            reader.onload = function(e) {
              showLabel();
              displayImage(e.target.result,index);
            };
            reader.readAsDataURL(file);	        	 
        }
	 }catch(e){
		 showImgName(index);
		 showLabel();
	 }
}
//]]>
</script> 
</body>
</html>
