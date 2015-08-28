<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/fancybox/fancybox.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">   
</script>
<style type="text/css">
	.np-post-body{ padding-left:10px;border-top:solid 1px;margin-bottom:8px;}
	.np-post-header{margin-right:3px;color:#1467B3;font-size: 16px;font-family:SimHei; }
	.np-post-content{ margin-top:1px;font-size: 15px;word-break:break-all;}   
</style>
</head>   
<body >           
<div id="d1" style="border: 0px solid #DDD;">
	<div style="padding: 5px 0;font-weight: 400;border-bottom: 0px solid #e8e8e8;">
		<span style="margin-left:8px;color:#1467B3;font-size: 16px;font-family:SimHei;"><b>
			${af.map.f_person }&nbsp;&nbsp;
			<fmt:formatDate value="${af.map.f_add_date }" pattern="yyyy-MM-dd"/> &nbsp;&nbsp;留言：
			</b>
		</span>
	</div>
  <div style="border: 1px solid #000;font-size: 20px;height:160px;background-color: #FFFFFF;word-break:break-all;"><span style="margin-left: 5px;">${af.map.f_content}</span></div>   
  <div style="padding: 5px 0;">
		<div style="margin-left:8px;color:#1467B3;font-size: 16px;float: left;font-family:SimHei;"><b>
			联系电话：
			</b>
		</div>
		<div style="border: 1px solid #000;background-color: #FFFFFF;color:black;width: 30%;float: left;font-size: 16px;">&nbsp;${af.map.f_tel }</div>
	</div>
	
   <div style="font-size: 16px;clear:both;padding: 5px 0;font-weight: 400;border-bottom: 1px solid #e8e8e8;"><span style="margin-left:8px;"><b>全部评论：</b></span></div> 
  <div id="d2"></div>
  
  <form action="" method="post" name="apLogin" id="apLogin">
		<div style="margin-top:10px;"><textarea name="content" id="content" rows="3" style="width:98%;resize:none;"></textarea><font color="red" id="u_msg"></font><br/></div>
		<div style="margin-top:8px;" align="center">  
			<input name="login791" id="sbt_login" type="button" class="bgButton" value=" 发 送 " />	
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
	ajax();
	document.onkeydown = function(e){     
        var ev = document.all ? window.event : e;    
        if(ev.keyCode==13) {  
        	var content=$('#content').val();
    		var feed_id ='${feed_id}';
    		if(content.length>100){$('#u_msg').html('最多只能输入100个字');return false;	}
    		if(content==''||$.trim(content).length==0){$('#u_msg').html('请输入评论信息');return false;	}
    		$.ajax({
    			type: "POST",url: "<c:url value='/customer/manager/JxcKonkaOrderRegister.do' />",
    			data: {"method":"ajaxSave", "content":content,"feed_id":feed_id,  "timestamp":new Date().getTime() },
    			dataType: "text",sync: true,
    			error: function (xhr, ajaxOptions, thrownError) {},
    			success: function(result){
    				if(result=='1'){   
    					$('#content').val('');
    					ajax();
    				}else{ 
    					$('#u_msg').html('数据异常');
    				}
    			}
    		});
        }
	 };
	
	$("#sbt_login").click(function(){
		var content=$('#content').val();
		var feed_id ='${feed_id}'; 
		if(content.length>100){$('#u_msg').html('最多只能输入100个字');return false;	}
		if(content==''||$.trim(content).length==0){$('#u_msg').html('请输入评论信息');return false;	}
		$.ajax({
			type: "POST",url: "<c:url value='/customer/manager/JxcKonkaOrderRegister.do' />",
			data: {"method":"ajaxSave", "content":content,"feed_id":feed_id,  "timestamp":new Date().getTime() },
			dataType: "text",sync: true,
			error: function (xhr, ajaxOptions, thrownError) {},
			success: function(result){
				if(result=='1'){  
					$('#content').val('');
					ajax();
				}else{ 
					$('#u_msg').html('数据异常');
				}
			}
		});
	});
});  

var flag = 0;
var f_id = 0;
function ajax(){ 
	var feed_id = '${feed_id}';   
	if(flag==0){  
		flag=1;
		$.ajax({
			type: "POST",url: "<c:url value='/customer/manager/JxcKonkaOrderRegister.do' />",
			data: {"method":"ajaxmessage", "feed_id":feed_id,"f_id":f_id,"timestamp":new Date().getTime() },
			dataType: "json",sync: true,
			error: function (xhr, ajaxOptions, thrownError) {flag=1;},
			success: function(data){
				flag=0;
				var ss="";
				if(data.list.length>0){
					for(i=0;i<data.list.length;i++){
						if(f_id<data.list[i].id){
							f_id=data.list[i].id;
						}
						ss=ss+"<div class='np-post-body'><div class='np-post-header'><span>"+data.list[i].question_person+'</span><span style="float: right;">'+data.list[i].add_date+'<span></div>'+"<div class='np-post-content'>"+data.list[i].content+"</div></div>"; 
					}
					$("#d2").append(ss);  
				}  
			}
		});
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

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
