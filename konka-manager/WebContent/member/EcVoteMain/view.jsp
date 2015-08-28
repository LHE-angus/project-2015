<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>开心猫 - ${entity.title }</title>
<link href="${ctx }/member/EcVoteMain/css/common.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/member/EcVoteMain/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
</head>
<body>
<div class="top">
	<div class="nav">
    	<ul>
        	<li><img src="${ctx }/member/EcVoteMain/images/nav1.jpg" /></li>
            <li class="line"><img src="${ctx }/member/EcVoteMain/images/line.jpg" /></li>
        </ul>
    </div>
    <div class="lookresult"><a href="#result"><img src="${ctx }/member/EcVoteMain/images/lookresult.jpg" /></a></div>
</div>
<div class="main">
<div class="votebox">
		<c:forEach items="${voteOptionList}" var="cur" varStatus="vs">
    	<div class="vote">
        	<div class="vote_tit">${cur.title }</div>
            <div class="vote_pic"><a href="?method=viewOption&vote_option_id=${cur.id }" target="_blank"><img src="${ctx}/${fn:substringBefore(cur.main_pic, '.')}.jpg" height="151" width="150" /></a></div>
            <div class="vote_num">票数：<font class="white">${cur.record_count}</font></div>
            <div class="vote_tp"><a href="javascript:void(0);" onclick="vote('${cur.vote_id}','${cur.id }');"><img src="${ctx }/member/EcVoteMain/images/vote.jpg" /></a></div>
        </div>
       </c:forEach>
    </div>
    <div class="voteresult_title"><a name="result"></a><img src="${ctx }/member/EcVoteMain/images/voteresult.jpg" /></div>
    <div class="voteresult">
    	<c:forEach items="${voteOptionList}" var="cur" varStatus="vs">
    	<div class="voteresult_list">
        	<div class="voteresult_list_l"><img src="${ctx }/member/EcVoteMain/images/${vs.count}.jpg" /></div>
            <div class="voteresult_list_r">
            	<div class="voteresult_pic"><a href="?method=viewOption&vote_option_id=${cur.id }" target="_blank"><img src="${ctx}/${fn:substringBefore(cur.main_pic, '.')}.jpg" height="79" width="78" /></a></div>
                <div class="voteresult_con">
                	<div class="voteresult_tit">${cur.title}</div>
					<div class="voteresult_cd"><div class="width80" style="width:${cur.record_count*100/all_count}%"></div></div>
                </div>
                <div class="voteresult_num">${cur.record_count}</div>
            </div>
        </div>
        </c:forEach> 
    </div>
    <div class="description">
    	 ${entity.content }
	</div>
    <div class="clear"></div>
</div>
<div class="foot"></div>
<script type="text/javascript">//<![CDATA[
var f=0;  
var msg="";
function vote(vote_id,vote_option_id){
	if(f==0){
		f++;
		$.ajax({
					type: "POST",
					url: "<c:url value='/member/EcVoteMain.do' />",
					data: { "method":"save","vote_id":vote_id,"vote_option_id":vote_option_id,"timestamp":new Date().getTime()},
					dataType: "json",
					sync: true,
					error: function (xhr, ajaxOptions, thrownError) { },
					success: function(data) {
						if(data.result=='1'){
						  alert("恭喜:"+data.msg);
						  location.reload();
						}else{
						  msg="对不起,"+data.msg;
						  alert("对不起,"+data.msg);
						  f++;
						} 
					}
	
	 	});
	}else{
		if(f>1){
			alert(msg);
		}else{ 
			alert('请稍等..');
		}
	}
}
</script>
</body>
</html>