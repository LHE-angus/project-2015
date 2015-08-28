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
<div class="top_class"><span class="lspan"><a href="<c:url value='/webservice/wap/center/Index.do?'/>"><img src="${ctx}/styles/epp/mobile/images/return.gif" /></a></span><h3>商品佣金查询</h3></div><div class="maincont"><!--right-->
<div class="member_right padbot45">
<div class="membertab3">
      <html-el:form action="/wap/center/EcGoodsRebate">
<html-el:hidden property="method" styleId="method" value="list" />
	<table style="width:100%;">
		<tr height="40">
			<td width="30%"><font style="margin-left: 10px;">商品名称：</font></td><td><html-el:text property="pd_sn_or_pd_name_like" maxlength="20" styleClass="input_txt" style="width:120px;"></html-el:text> &nbsp;<input class="inputbtn" type="submit" name="Submit" value="查询" /></td>
		</tr> 
</table></html-el:form>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
<div class="out">
	<div class="in">
		<table style="width:100%;">
			<tr>
				<td colspan="4" width="70%">
					商品名称：<a href="<c:url value='/webservice/wap/PdShow.do?goods_id=${cur.id}' />" title="${fn:escapeXml(cur.pd_name)}"><font  style="color: red;">${fn:escapeXml(cur.pd_name)}</font></a><br/>
					销售金额(元)：<fmt:formatNumber value="${cur.map.price}" pattern="0" /><br/>
					佣金(元)：<c:if test="${empty cur.map.ecGoodsRebate}"> - </c:if>
					<c:if test="${not empty cur.map.ecGoodsRebate}">
		              <c:if test="${cur.map.ecGoodsRebate.rebate_way eq 2  }"><fmt:formatNumber value="${cur.map.price/100.0*cur.map.ecGoodsRebate.rebate_value}" pattern="0.0" /></c:if>
		              <c:if test="${cur.map.ecGoodsRebate.rebate_way eq 1  }"><fmt:formatNumber value="${cur.map.ecGoodsRebate.rebate_value}" pattern="0.0" /></c:if>
              		</c:if>
					<br/>
					佣金比例：<c:if test="${empty cur.map.ecGoodsRebate}"> - </c:if>
					<c:if test="${not empty cur.map.ecGoodsRebate}">
		              <c:if test="${cur.map.ecGoodsRebate.rebate_way eq 2  }"><fmt:formatNumber value="${cur.map.ecGoodsRebate.rebate_value}" pattern="0.00" /></c:if>
		              <c:if test="${cur.map.ecGoodsRebate.rebate_way eq 1 and not empty cur.map.price }"><fmt:formatNumber value="${cur.map.ecGoodsRebate.rebate_value*100.0/cur.map.price}" pattern="0.00" /></c:if>%
	              	</c:if>
				</td>
			</tr>
			<tr><td colspan="5"><div class="divcss5-4"></div></td></tr>
		</table>
	</div>
</div></c:forEach><c:if test="${empty entityList }">
<div style="margin:0 auto;" align="center"> <font style="font-size: 20px;">暂无记录！</font> </div>
</c:if><c:if test="${af.map.pageCount >1}">
<div class="xt_fenye"><a onclick="goback('${af.map.currentPage}','${af.map.pd_sn_or_pd_name_like}');"><img src="${ctx}/mobile/images/xt_shagnyiye.jpg" width="66" height="24" /></a>
<a onclick="goforward('${af.map.currentPage}','${af.map.pd_sn_or_pd_name_like}');"><img src="${ctx}/mobile/images/xt_xiayye.jpg" width="66" height="24" /></a></div>
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

function goback(page,pd_sn_or_pd_name_like) {
	if(goPage(null,page,-1,5)){
		window.location.href="EcGoodsRebate.do?"+"page="+page+"&forward=-1&pd_sn_or_pd_name_like="+pd_sn_or_pd_name_like;
	}
}

function goforward(page,pd_sn_or_pd_name_like) {
	if(goPage(null,page,1,5)){
		window.location.href="EcGoodsRebate.do?"+"page="+page+"&forward=1&pd_sn_or_pd_name_like="+pd_sn_or_pd_name_like;
	}
}
  
  
//]]></script>
</html>
