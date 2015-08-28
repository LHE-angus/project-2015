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
      <html-el:form action="/wap/center/EcRebates">
<html-el:hidden property="method" styleId="method" value="list" />
	<table style="width:100%;">
		<tr height="40">
			<td width="30%"><font style="margin-left: 10px;">佣金状态：</font></td>
			<td><html-el:select property="rebates_status" onchange="this.form.submit();" styleClass="input_txt" style="width:120px;">
				 <html-el:option value="">==全部==</html-el:option>
		         <html-el:option value="0">需确认提现、兑换积分</html-el:option>
		         <html-el:option value="1">提现 已发放</html-el:option>
		         <html-el:option value="2">提现 等待发放</html-el:option>
		         <html-el:option value="3">已兑换积分</html-el:option>
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
					商品名称：<c:out value="${cur.pd_name }"/><br/>
					单价数量：<fmt:formatNumber value="${fn:escapeXml(cur.price)}" pattern="￥0.00" /> * ${cur.num }<br/>
					获得佣金：<c:if test="${cur.rebates_status eq 1 }"><font color="green">
		              <fmt:formatNumber value="${fn:escapeXml(cur.rebates)}" pattern="￥0.00" /></font></c:if><c:if test="${cur.rebates_status ne 1 }"><font color="red">
		              <fmt:formatNumber value="${fn:escapeXml(cur.rebates)}" pattern="￥0.00" /></font></c:if><br/>
					时间：<fmt:formatDate value="${cur.map.add_date}" pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
				<td width="30%">
					<c:if test="${cur.rebates_status eq 1 }">提现 已发放</c:if>
	              	<c:if test="${cur.rebates_status eq 0 }"><c:if test="${not empty cur.rebates and cur.rebates>0}">[<a href="#" onclick="encash('2','${cur.bill_item_id}')" >提现</a>]<br/> [<a href="#" onclick="encash('3','${cur.bill_item_id}')">兑换</a>]</c:if></c:if>
	              	<c:if test="${cur.rebates_status eq 2 }">提现 等待发放</c:if>
	              	<c:if test="${cur.rebates_status eq 3 }">已兑换积分</c:if>
	              	<c:if test="${cur.rebates_status eq 4 }">抵扣货款</c:if>
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
		window.location.href="EcRebates.do?"+"page="+page+"&forward=-1&ts="+ts;
	}
}

function goforward(page,ts) {
	if(goPage(null,page,1,5)){
		window.location.href="EcRebates.do?"+"page="+page+"&forward=1&ts="+ts;
	}
}

function encash(type,id){
	var is_ws = "${af.map.is_ws}";
	var user_id = "${af.map.user_id}";     
	if(is_ws == "0"){
		if(type == "2"){
			alert("没有关联银行卡！");
			return false;
		} else if(type == "3"){
			location.href="${ctx}/webservice/wap/center/EcRebates.do?method=save&type="+type+"&id="+id; 
		}
	}else if(is_ws == "1"){
		location.href="${ctx}/webservice/wap/center/EcRebates.do?method=save&type="+type+"&id="+id;
	}
	
	
}
  
//]]></script>
</html>
