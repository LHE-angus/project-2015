<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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

<div style="margin-left:5%;margin-right:5%;">
<div style="font-weight: normal; font-size: 18px; font-family: "微软雅黑", "黑体";"><b>评论</b></div>
<br/>
 <%@ include file="/commons/pages/messages.jsp" %>
<html-el:form action="/admin/KonkaGroupPeArticleInfo">
     <html-el:hidden property="method" value="editComment" />
     <html-el:hidden property="mod_id" styleId="mod_id" />
     <html-el:hidden property="link_id" styleId="link_id" />
     <html-el:hidden property="https" styleId="https" value="${https}" />
	<c:if test="${not empty commentList }">
		<c:forEach var="cur" items="${commentList}" varStatus="vs">
			<div class='np-post-body'>
				<div class='np-post-header'>
					<span class='np-post-name'>
						<c:if test="${cur.anonymous eq 1 }">匿名评论</c:if>
						<c:if test="${cur.anonymous ne 1 }">${cur.user_name }</c:if>
					</span>
					<span class='np-post-date'>
						<fmt:formatDate value="${cur.add_date }" pattern="yyyy-MM-dd HH:mm:ss"/>
					</span>
				</div>
					<!-- 判断是否有父评论，如果没有父评论，说明这是第一层评论咨询的评论，如果有父评论，则说明这次一条针对评论的回复 -->
					<c:if test="${not empty cur.commentInfo }">
						<div class='np-post-body-parent'>
							<c:if test="${cur.commentInfo.is_del eq 0}">
								<div class='np-post-header'>
									<span class='np-post-name'>
										<c:if test="${cur.commentInfo.anonymous eq 1 }">匿名评论</c:if>
										<c:if test="${cur.commentInfo.anonymous ne 1 }">${cur.user_name }</c:if>
									</span>
									<span class='np-post-date'><fmt:formatDate value="${cur.commentInfo.add_date}" pattern="yyyy-MM-dd HH:mm:ss"/><span>
								</div>
								<div class='np-post-content'>${cur.commentInfo.content}</div>
							</c:if>
							<c:if test="${cur.commentInfo.is_del eq 1}">
								<div style="padding-left:10px;color:#929CA8;font-size: 15px;font-family:SimHei;">评论已经删除！</div>
							</c:if>
						</div>
					</c:if>
					<div class='np-post-content'>${cur.content }</div>
					<div class='np-post-replay-div' align='right'>
						<span onclick="doNeedMethod(null, 'KonkaGroupPeArticleInfo.do', 'deleteComment','id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())" style="cursor:pointer;font-size: 12px;" class="fblue">删除</span>
					</div>
			</div>
		</c:forEach>
	</c:if>
</html-el:form>
<br />
<form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaGroupPeArticleInfo.do">
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
       <script type="text/javascript">
     	var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
        pager.addHiddenInputs("method", "editComment");
        pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
        pager.addHiddenInputs("link_id", "${af.map.link_id}");
        document.write(pager.toString());
		</script></td>
    </tr>
  </table>
</form>
</div>

<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
// 	$("#sbt_login").click(function(){
// 		var content=$('#inputcontent').val();
// 		var anonymous=0;
// 		if($("#anonymous").is(":checked")){
// 			anonymous=1;
// 		}
// 		var user_id ='${user_id}'; 
// 		var id ='${id}'; 
// 		if(content.length>100){$('#u_msg').html('最多只能输入100个字');return false;	}
// 		if(content==''||$.trim(content).length==0){$('#u_msg').html('请输入评论信息');return false;	}
// 		$.ajax({
// 			type: "POST",url: "<c:url value='/webservice/KonkaPeArticleInfoInterface.do' />",
// 			data: {"method":"ajaxSaveCommentInfo", "content":content, "user_id":user_id, "id":id, "anonymous":anonymous,  "timestamp":new Date().getTime() },
// 			dataType: "text",sync: true,
// 			error: function (xhr, ajaxOptions, thrownError) {$('#u_msg').html('数据异常');},
// 			success: function(result){
// 				if(result=='1'){  
// 					$('#inputcontent').val('');
// 					ajax();
// 				}else{ 
// 					$('#u_msg').html('数据异常');
// 				}
// 			}
// 		});
// 	});
});

//点击回复按钮提交内容
// function reply_submit(par_id,comment_id,user_id){
// 	var content=$(".inputcontent_"+par_id).val();
// 	var anonymous=0;
// 	if($(".anonymous_"+par_id).is(":checked")){
// 		anonymous=1;
// 	}
// 	if(content.length>100){$(".u_msg_"+par_id).html('最多只能输入100个字');return false;	}
// 	if(content==''||$.trim(content).length==0){$(".u_msg_"+par_id).html('请输入评论信息');return false;	}
// 	$.ajax({
// 		type: "POST",url: "<c:url value='/webservice/KonkaPeArticleInfoInterface.do' />",
// 		data: {"method":"ajaxSaveCommentInfo", "content":content, "user_id":user_id, "id":comment_id,"par_id":par_id, "anonymous":anonymous,  "timestamp":new Date().getTime() },
// 		dataType: "text",sync: true,
// 		error: function (xhr, ajaxOptions, thrownError) {$('#u_msg').html('数据异常');},
// 		success: function(result){
// 			if(result=='1'){  
// 				$(".inputcontent_"+par_id).val('');
// 				$(".reply_"+par_id).text("回复");
// 				$(".content_"+par_id).hide();
// 				ajax();
// 			}else{ 
// 				$('#u_msg').html('数据异常');
// 				$(".anonymous_"+par_id).removeAttr("checked");
// 			}
// 		}
// 	});
// }

//]]></script>
</body>
</html>
