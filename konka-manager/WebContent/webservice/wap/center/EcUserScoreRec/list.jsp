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
<div class="top_class"><span class="lspan"><a href="<c:url value='/webservice/wap/center/Index.do?'/>"><img src="${ctx}/styles/epp/mobile/images/return.gif" /></a></span><h3>我的积分</h3></div><div class="maincont"><!--right-->
<div class="member_right padbot45">
<div class="membertab3">
      <html-el:form action="/wap/center/EcUserScoreRec">
<html-el:hidden property="method" styleId="method" value="list" />
	<table style="width:100%;">
		<tr height="40">
			<td width="30%"><font style="margin-left: 10px;">当前积分:</font></td>
			<td><font  style="color: red;">${totalScore }</font></td>
		</tr>
		<tr height="40">
			<td width="30%"><font style="margin-left: 10px;">时&nbsp;&nbsp;间：</font></td>
			<td><html-el:select property="ts" onchange="this.form.submit();" styleClass="input_txt" style="width:120px;">
				<html-el:option value="last3months">最近三个月记录</html-el:option>
				<html-el:option value="before3months">三个月以前记录</html-el:option>
				</html-el:select>
			</td>
		</tr>
</table></html-el:form>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
<div class="out">
	<div class="in">
		<table style="width:100%;">
			<tr>
				<td colspan="4" width="70%">
					时间：<fmt:formatDate value="${cur.opr_date}" pattern="yyyy-MM-dd HH:mm:ss" /><br/>
					获得积分：<font  style="color: red;"><c:if test="${cur.opr_type eq 0}"> ${cur.score }</c:if>
                <c:if test="${cur.opr_type eq 1}">-</c:if></font><br/>
					消费积分：<c:if test="${cur.opr_type eq 1}"> ${cur.score }</c:if>
                <c:if test="${cur.opr_type eq 0}">-</c:if><br/>
					详细说明：<c:out value="${cur.note }"/>
				</td>
			</tr>
			<tr><td colspan="5"><div class="divcss5-4"></div></td></tr>
		</table>
	</div>
</div></c:forEach><c:if test="${empty entityList }">
<div style="margin:0 auto;" align="center"> <font style="font-size: 20px;">暂无记录！</font> </div>
</c:if><c:if test="${af.map.pageCount >1}">
<div class="xt_fenye"><a onclick="goback('${af.map.currentPage}','${af.map.ts}');"><img src="${ctx}/mobile/images/xt_shagnyiye.jpg" width="66" height="24" /></a>
<a onclick="goforward('${af.map.currentPage}','${af.map.ts}');"><img src="${ctx}/mobile/images/xt_xiayye.jpg" width="66" height="24" /></a></div>
</c:if>
</div>
</div>
<div class="clear"></div>
</div>  
<!-- six footer -->
</body>
<script type="text/javascript">//<![CDATA[                                           
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

function goback(page,ts) {
	if(goPage(null,page,-1,5)){
		window.location.href="EcUserScoreRec.do?"+"page="+page+"&forward=-1&ts="+ts;
	}
}

function goforward(page,ts) {
	if(goPage(null,page,1,5)){
		window.location.href="EcUserScoreRec.do?"+"page="+page+"&forward=1&ts="+ts;
	}
}
  
  
//]]></script>
</html>
