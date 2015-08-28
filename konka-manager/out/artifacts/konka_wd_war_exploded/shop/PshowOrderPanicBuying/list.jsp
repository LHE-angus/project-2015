<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:if test="${ecUser.user_type eq 1 }">康佳EPP内部优惠购机平台</c:if><c:if test="${ecUser.user_type eq 2 }">康佳粉丝会</c:if></title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/shop/css/global1.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/shop/css/epp.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/shop/css/slider.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/shop/css/purchase.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/shop/css/togo.css" />
<link rel=stylesheet type=text/css  href="${ctx}/commons/styles/pager3.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
</head>
<body>
<!-- head start -->
<div class="topbox">
<jsp:include page="/shop/__inc/top.jsp" flush="true" />
<jsp:include page="/shop/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/shop/__inc/nav.jsp" flush="true" />
<!-- topnav end -->
<!-- first start -->
<!-- first end -->
<!-- togo start -->
<div class="togo">
  <div class="togo_left">
    <!-- list start -->
    <c:forEach items="${entityList}" var="cur" varStatus="vs">
      <div class="togo_up">
        <div class="tg_center"><font class="gran">${fn:escapeXml(cur.pd_desc)}</font></div>
        <div class="tg_l">
          <div class="tg_left">
            <div class="tg_k">
              <div class="tg_up"><font class="pink22px">
                <fmt:formatNumber value="${cur.ecGoodsPrice.price}" pattern="0" />
                </font></div>
              <div class="tg_cn"><a href="<c:url value='/shop/PshowOrderPanicBuying.do?method=add&goods_id=${cur.id}' />"><img src="${ctx}/styles/shop/images/qianggou.jpg" width="117" height="51" /></a></div>
              <div class="tg_down">
                <ul>
                  <li>原价<br>
                    <font class="black20px">￥
                    <fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" />
                    </font></li>
                  <li>折扣<br>
                    <font class="black20px">
                    <c:if test="${empty cur.ecGoodsPrice.original_price or cur.ecGoodsPrice.original_price eq '0'}" var="no_price_flag">-</c:if>
                    <c:if test="${!no_price_flag}"> ${(cur.ecGoodsPrice.price / cur.ecGoodsPrice.original_price)*10} </c:if>
                    </font></li>
                  <li>节省<br>
                    <font class="black20px">￥
                    <fmt:formatNumber value="${cur.ecGoodsPrice.original_price - cur.ecGoodsPrice.price}" pattern="0" />
                    </font></li>
                </ul>
              </div>
            </div>
            <div class="tg_clock"><font class="black_14">剩余时间<br>
              <div id="divTimeRemains_${vs.count - 1}">&nbsp;</div>
              </font></div>
            <div class="tg_success"><font class="pink16px">${cur.sale_num}</font> 人已购买<br>
              抢购成功!可继续购买!</div>
          </div>
          <div class="tg_right box_606_317">
            <table border="0" cellspacing="0" cellpadding="0" width="606">
              <tr>
                <td height="317" style="overflow:hidden;" width="606" valign="middle" align="center"><a href="<c:url value='/shop/PshowOrderPanicBuying.do?method=view&goods_id=${cur.id}' />">
                  <c:set var="_main_pic" value="${ctx}/${fn:substringBefore(cur.main_pic, '.')}_600.jpg" />
                  <c:if test="${fn:length(cur.picArray) ge 2}">
                    <c:set var="_main_pic" value="${ctx}/${fn:substringBefore(cur.picArray[1], '.')}_600.jpg" />
                  </c:if>
                  <img src="${_main_pic}" align="absmiddle" /> </a> </td>
              </tr>
            </table>
          </div>
        </div>
      </div>
    </c:forEach>
    <!-- list end  -->
  </div>
  <div class="togo_right">
    <div class="togo_box"><font class="orange14px">新闻资讯</font></div>
    <div class="togo_box_down">
      <ul>
      	<c:forEach items="${konkaPeArticleInfoList}" var="cur" varStatus="vs">
      		<li><a title="${cur.title}" href="<c:url value='/shop/KonkaGroupPeArticleInfo.do?method=view&id=${cur.id}' />">${fnx:abbreviate(cur.title, 2 * 18, '.')}</a></li>
      		<c:if test="${vs.last eq true}">
	              <c:set var="i" value="${vs.count}" />
	        </c:if>
      	</c:forEach>
      	<c:forEach begin="${i}" end="4">
            <li>&nbsp;</li>
        </c:forEach>
      </ul>
    </div>
    <div class="mb"></div>
    <div class="togo_box"><font class="orange14px">抢购推荐</font></div>
    <c:if test="${not empty pd_list_cate5}">
      <div class="togo_box_down">
        <div class="title99"><a title="" href="<c:url value='/shop/PshowOrderPanicBuying.do?method=view&goods_id=${pd_list_cate5[0].id}' />" target="_blank">${fn:escapeXml(pd_list_cate5[0].pd_desc)}</a></div>
        <div class="pic99 box_270_202">
          <table border="0" cellspacing="0" cellpadding="0" width="270">
            <tr>
              <td height="202" style="overflow:hidden;" width="270" valign="middle" align="center"><a title="" href="<c:url value='/shop/PshowOrderPanicBuying.do?method=view&goods_id=${pd_list_cate5[0].id}' />" target="_blank">
                <c:set var="_main_pic" value="${ctx}/${fn:substringBefore(pd_list_cate5[0].main_pic, '.')}_240.jpg" />
                <c:if test="${fn:length(pd_list_cate5[0].picArray) ge 2}">
                  <c:set var="_main_pic" value="${ctx}/${fn:substringBefore(pd_list_cate5[0].picArray[1], '.')}_240.jpg" />
                </c:if>
                <img src="${_main_pic}" alt="" align="absmiddle" /></a></td>
            </tr>
          </table>
        </div>
        <div class="buy99">
          <div class="n-tg"><span class="tg-mg1"><a title="查看详情" href="<c:url value='/shop/PshowOrderPanicBuying.do?method=view&goods_id=${pd_list_cate5[0].id}' />" target="_blank"> </a></span> </div>
          <div class="n-zk"><span class="red">
            <fmt:formatNumber value="${pd_list_cate5[0].ecGoodsPrice.price}" pattern="0" />
            </span>元</div>
          <div class="n-zk"><span class="red">${pd_list_cate5[0].sale_num}</span>人已购买</div>
        </div>
      </div>
    </c:if>
  </div>
</div>
<!-- six footer -->
<jsp:include page="../__inc/footer.jsp" flush="true" />
<!-- six footer -->
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var form = document.listForm;
	
});

var timeEnd = [];
<c:forEach items="${entityList}" var="cur" varStatus="vs">
	timeEnd['${vs.count - 1}'] = '${cur.timeRemains}';
</c:forEach>

var timeNow = '${today}';

//var timeEnd = timeEnd[0];

//var timeEnd = new Date(2013, 8, 16, 10, 0, 0);

var timeRemains = [];
//var timeRemains =  timeEnd - timeNow;
var iTime = [];

for(var i=0;i<timeEnd.length;i++){
	timeRemains[i] = timeEnd[i] - timeNow;
	iTime[i] = timeRemains[i]/1000;
}

//var iTime = timeRemains/1000;
var Account;
RemainTime();

function RemainTime()
{
	if(iTime.length > 0){
		var time_count = 0;
		for(var i=0;i<iTime.length;i++){
			var iDay,iHour,iMinute,iSecond;
		    var sDay="",sHour="",sMinute="",sSecond="",sTime="";
		    if (iTime[i] >= 0)
		    {
		        iDay = parseInt(iTime[i]/24/3600);
		        if (iDay > 0)
		        {
		            sDay = iDay + "天";
		        }
		        iHour = parseInt((iTime[i]/3600)%24);
		        if (iHour > 0){
		            sHour = iHour + "小时";
		        }
		        iMinute = parseInt((iTime[i]/60)%60);
		        if (iMinute > 0){
		            sMinute = iMinute + "分钟";
		        }
		        iSecond = parseInt(iTime[i]%60);
		        if (iSecond >= 0){
		            sSecond = iSecond + "秒";
		        }
		        if ((sDay=="")&&(sHour=="")){
		            sTime="<span style='color:darkorange'>" + sMinute+sSecond + "</font>";
		        }
		        else
		        {
		            sTime=sDay+sHour+sMinute+sSecond;
		        }
		        if(iTime[i]==0){
		            clearTimeout(Account);
		            sTime="<span style='color:red'>抢购已结束！</span>";
		            time_count = time_count + 1;
		        }
		        else
		        {
		            //Account = setTimeout("RemainTime()",1000);
		        }
		        iTime[i] = iTime[i] - 1;
		    }
		    else
		    {
		            sTime="<span style='color:red'>抢购已结束！</span>";
		    }
		    document.getElementById("divTimeRemains_"+i).innerHTML = sTime;
		    
		}

	    //还有商品抢购时间未结束，继续进行倒计时
	    if(time_count != iTime.length){
	    	Account = setTimeout("RemainTime()",1000);
	    }
	}
    
}

//]]></script>
</body>
</html>
