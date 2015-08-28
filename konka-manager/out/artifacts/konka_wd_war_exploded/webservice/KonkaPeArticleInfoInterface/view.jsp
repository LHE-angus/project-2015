<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/common.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	.np-post-body{ padding-left:10px;border-top:solid 1px #CCCCCC;bmargin-bottom:8px;border-top-color:#929CA8; }
 	.np-post-header{margin-top:8px;margin-bottom:5px; } 
	.np-post-name{margin-right:3px;color:#686868;font-size: 12px; }
	.np-post-date{margin-left:15px;color:#929CA8;font-size: 12px;font-family:SimHei; }
	.np-post-content{ margin-bottom:2px;font-size: 15px;line-height: 20px;word-break:break-all;}
	.np-post-replay-div{margin-bottom:6px;}   
	.np-post-body-parent{margin-bottom: 3px;margin-left:8px;margin-right:8px;padding-left:4px;padding-right:4px;margin-top:1px;background-color:#F1F1F1;border: 1px solid #BBBBBB; }
</style>
</head>
<body>
<div class="headtab31" >
  <div class="wenzi1">
    <h2><font style="font-size:18px; margin-top: 20px;line-height:32px;">${entity.title}</font></h2>
    <div class="riqi"><!-- 浏览次数：${entity.view_counts}次  &nbsp;&nbsp;  -->发布时间：
      <fmt:formatDate value="${entity.pub_date}" pattern="yyyy-MM-dd HH:mm:ss" />
    </div>
    <c:if test="${not empty entity.img_path}">
      <div style="text-align:center;"> <img src="${ctx}/${fn:substringBefore(entity.img_path, '.')}_240.jpg" title="${entity.img_desc}" />
        <c:if test="${not empty entity.img_desc}"> <br />
          <c:out value="${entity.img_desc}" />
        </c:if>
      </div>
    </c:if>
    <div class="n_rong" style="padding-left: 5%;padding-right: 5%;">
      <c:out value="${entity.content}" escapeXml="false" />
    </div>
    <c:if test="${not empty attachmentList}">
      <div class="riqi"></div>
      <div style="padding-left: 10px;">
        <c:forEach var="cur" items="${attachmentList}" varStatus="vs"> ${vs.count}、<a href="${ctx}/webservice/KonkaPeArticleInfoInterface.do?method=downloadFile&save_name=${cur.save_name}">${cur.file_name}</a>
        </c:forEach>
      </div>
    </c:if>
  </div>
</div>
<c:if test="${not empty is_mobile }">
	<div style="padding-bottom: 35px;padding-left: 5%;padding-right: 5%;">
		<div id="cheader" style="font-weight: normal; font-size: 18px; font-family: "微软雅黑", "黑体"; line-height: 35px;"><b>评论</b></div>
		<div id="content" style="margin-bottom: 35px">
			<form action="" method="post" name="apLogin" id="apLogin">
				<div style="margin-top:10px;"><textarea name="inputcontent" id="inputcontent" rows="3" style="width:100%;resize:none;"></textarea><font color="red" id="u_msg"></font><br/></div>
				<div style="margin-top:8px;float: left;" align="left">  
					<input type="checkbox" id="anonymous" name="anonymous" value="1"> 匿名评论</input>
				</div>
				<div style="margin-top:6px;float: right;" align="right">  
					<input name="login791" id="sbt_login" type="button" class="bgButton" value=" 发表 " />	
				</div>
		    </form>
		</div>
		<div id="d2" style="margin-top: 20px"></div>
		<div style="height:150px">&nbsp;<div>
	</div>
</c:if>

<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	ajax();
	$("#sbt_login").click(function(){
		var content=$('#inputcontent').val();
		var anonymous=0;
		if($("#anonymous").is(":checked")){
			anonymous=1;
		}
		var user_id ='${user_id}'; 
		var id ='${id}'; 
		if(content.length>100){$('#u_msg').html('最多只能输入100个字');return false;	}
		if(content==''||$.trim(content).length==0){$('#u_msg').html('请输入评论信息');return false;	}
		$.ajax({
			type: "POST",url: "<c:url value='/webservice/KonkaPeArticleInfoInterface.do' />",
			data: {"method":"ajaxSaveCommentInfo", "content":content, "user_id":user_id, "id":id, "anonymous":anonymous,  "timestamp":new Date().getTime() },
			dataType: "text",sync: true,
			error: function (xhr, ajaxOptions, thrownError) {$('#u_msg').html('数据异常');},
			success: function(result){
				if(result=='1'){  
					$('#inputcontent').val('');
					ajax();
				}else{ 
					$('#u_msg').html('数据异常');
				}
			}
		});
	});
});

//将json传过来的日期格式化
function toDate(time){
    var datetime = new Date();
    datetime.setTime(time);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
}

function clickreplay(info_id){
	if("回复" == $("#reply_"+info_id).text()){
		$("#reply_"+info_id).text("取消");//“回复”按钮变成取消按钮
		$("#content_"+info_id).show();
	} else if("取消" == $("#reply_"+info_id).text()){
		$("#reply_"+info_id).text("回复");
		$("#content_"+info_id).hide();
	}
}

//点击回复按钮提交内容
function reply_submit(par_id,comment_id,user_id){
	var content=$("#inputcontent_"+par_id).val();
	var anonymous=0;
	if($("#anonymous_"+par_id).is(":checked")){
		anonymous=1;
	}
	if(content.length>100){$("#u_msg_"+par_id).html('最多只能输入100个字');return false;	}
	if(content==''||$.trim(content).length==0){$("#u_msg_"+par_id).html('请输入评论信息');return false;	}
	$.ajax({
		type: "POST",url: "<c:url value='/webservice/KonkaPeArticleInfoInterface.do' />",
		data: {"method":"ajaxSaveCommentInfo", "content":content, "user_id":user_id, "id":comment_id,"par_id":par_id, "anonymous":anonymous,  "timestamp":new Date().getTime() },
		dataType: "text",sync: true,
		error: function (xhr, ajaxOptions, thrownError) {$("#u_msg_"+par_id).html('数据异常');},
		success: function(result){
			if(result=='1'){  
				$("#inputcontent_"+par_id).val('');
				$("#reply_"+par_id).text("回复");
				$("#content_"+par_id).hide();
				ajax();
			}else{ 
				$('#u_msg_'+par_id).html('数据异常');
				$("#anonymous_"+par_id).removeAttr("checked");
			}
		}
	});
}

var flag = 0;
var info_id = 0;
function ajax(){ 
	var user_id ='${user_id}'; 
	var id ='${id}';
	if(flag==0){  
		flag=1;
		$.ajax({
			type: "POST",url: "<c:url value='/webservice/KonkaPeArticleInfoInterface.do' />",
			data: {"method":"ajaxGetMessage", "user_id":user_id, "id":id, "info_id":info_id,"timestamp":new Date().getTime() },
			dataType: "json",sync: true,
			error: function (xhr, ajaxOptions, thrownError) {flag=1;},
			success: function(data){
				flag=0;
				var ss="";
				if(data.length>0){
					for(i=0;i<data.length;i++){
						if(info_id<data[i].id){//将循环中最大的ID赋值给info_id然后在后台判断
							info_id=data[i].id;
						}
						
						ss=ss+"<div class='np-post-body'>";
						ss=ss+"<div class='np-post-header'><span class='np-post-name'>";
						if(data[i].anonymous==1){
							ss=ss+"匿名评论";
						} else {
							ss=ss+data[i].user_name;
						}
						ss=ss+"</span><span class='np-post-date'>"+toDate(data[i].add_date)+"<span></div>";
						//判断是否有父评论，如果没有父评论，说明这是第一层评论咨询的评论，如果有父评论，则说明这次一天针对评论的回复
						if(null != data[i].commentInfo && undefined != data[i].commentInfo){
							ss=ss+"<div class='np-post-body-parent'>";
							if(data[i].commentInfo.is_del==0){
								ss=ss+"<div class='np-post-header'><span class='np-post-name'>";
								if(data[i].commentInfo.anonymous==1){
									ss=ss+"匿名评论";
								} else {
									ss=ss+data[i].commentInfo.user_name;
								}
								ss=ss+"</span><span class='np-post-date'>"+toDate(data[i].commentInfo.add_date)+"</span></div><div class='np-post-content'>"+data[i].commentInfo.content+"</div>"; 
							} else {
								ss=ss+"<div style='padding-left:10px;color:#929CA8;font-size: 15px;font-family:SimHei;'>评论已经删除！</div>"
							}
							ss=ss+"</div>";
						}
						
						ss=ss+"<div class='np-post-content'>"+data[i].content+"</div>";
						ss=ss+"<div class='np-post-replay-div' align='right'><span id='reply_"+data[i].id+"' class='replay_btn' onclick='clickreplay("+data[i].id+")' style='color:#949492;font-size: 12px;'>回复</span></div>";
						
						//每条回复的后面隐藏一个回复框的DIV
						ss=ss+"<div id='content_"+data[i].id+"' style='margin-bottom: 30px;padding-left:2px;padding-right:2px;display:none;'>"+
						"<div style='margin-top:5px;'><textarea name='inputcontent' id='inputcontent_"+data[i].id+"' rows='2' style='width:100%;resize:none;'></textarea><font color='red' id='u_msg_"+data[i].id+"'></font><br/></div>"+
						"<div style='margin-top:4px;float: left;' align='left'>"+  
							"<input type='checkbox' id='anonymous_"+data[i].id+"' name='anonymous1' value='1'> 匿名评论</input>"+
						"</div>"+
						"<div style='margin-top:3px;float: right;' align='right'>"+  
							"<input name='login791' id='sbt_login_"+data[i].id+"' onclick='reply_submit("+data[i].id+","+data[i].link_id+","+data[i].user_id+");' type='button' style='background:url(../images/manager/butbg1.gif) repeat-x;border:1px #c4c4c4 solid;font:normal 12px/20px '宋体';color:#333;padding: 0px 1px 0px 1px !important;padding: 0px 1px 0px 1px;letter-spacing: 1px;font-size:12px; cursor:pointer;' value='回复' />"+	
						"</div>"+
						"</div>";
						
						ss=ss+"</div>"; 
						
					}
					$("#d2").prepend(ss);  
					$(".replay_btn").each().click();
				} else {
					$("#d2").prepend("<div style='height:311px'>&nbsp;<div>"); 
				}  
			}
		});
	} 
}

// function ajaxLogin(){ //首次页面载入时加载
// 	var user_id ='${user_id}'; 
// 	var id ='${id}';
// 	$.ajax({
// 		type: "POST",url: "<c:url value='/webservice/KonkaPeArticleInfoInterface.do' />",
// 		data: {"method":"ajaxGetMessage", "user_id":user_id, "id":id, "info_id":0,"timestamp":new Date().getTime() },
// 		dataType: "json",sync: true,
// 		error: function (xhr, ajaxOptions, thrownError) {},
// 		success: function(data){
// 			if(data.length>0){
// 				for(i=0;i<data.length;i++){
// 					if(info_id<data[i].id){//将循环中最大的ID赋值给info_id然后在后台判断
// 						info_id=data[i].id;
// 					}
// 					var ss="";
// 					ss=ss+"<div class='np-post-body'><div class='np-post-header'><span class='np-post-name'>";
// 					if(data[i].anonymous==1){
// 						ss=ss+"匿名评论";
// 					} else {
// 						ss=ss+data[i].user_name;
// 					}
// 					ss=ss+"</span><span class='np-post-date'>"+toDate(data[i].add_date)+"<span></div><div class='np-post-content'>"+data[i].content+"</div></div>"; 
// 					$("#d2").append(ss);  
// 				}
// 			}  
// 		}
// 	});
// }
//]]></script>
</body>
</html>
