<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:if test="${ecUser.user_type eq 1 }">康佳EPP内部优惠购机平台</c:if><c:if test="${ecUser.user_type eq 2 }">康佳网上直销商城</c:if></title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/touch/css/global1.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/touch/css/epp.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/touch/css/purchase.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/touch/css/togo.css" />
<link rel=stylesheet type=text/css  href="${ctx}/commons/styles/pager3.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
</head>
<body>
<!-- head start -->
<div class="topbox">
<jsp:include page="/touch/__inc/top.jsp" flush="true" />
<jsp:include page="/touch/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/touch/__inc/nav.jsp" flush="true" />
<!-- topnav end -->
<!-- first start -->
<!-- first end -->
<!-- togo start -->
<div class="togo">
  <div class="togo_left">
    <div class="togo_up">
      <div class="tg_center"><font class="gran">${fn:escapeXml(konkaBcompPd.pd_desc)}</font></div>
      <div class="tg_l">
        <div class="tg_left">
          <div class="tg_k">
            <div class="tg_up"><font class="pink22px">
              <fmt:formatNumber value="${konkaBcompPd.ecGoodsPrice.price}" pattern="0" />
              </font></div>
            <div class="tg_cn"><a href="<c:url value='/touch/PshowOrderPanicBuying.do?method=add&goods_id=${konkaBcompPd.id}' />"><img src="${ctx}/styles/touch/images/qianggou.jpg" width="117" height="51" /></a></div>
            <div class="tg_down">
              <ul>
                <li>原价<br>
                  <font class="black20px">￥
                  <fmt:formatNumber value="${konkaBcompPd.ecGoodsPrice.original_price}" pattern="0" />
                  </font></li>
                <li>折扣<br>
                  <font class="black20px">
                  <c:if test="${empty konkaBcompPd.ecGoodsPrice.original_price or konkaBcompPd.ecGoodsPrice.original_price eq '0'}" var="no_price_flag">-</c:if>
                  <c:if test="${!no_price_flag}"> ${(konkaBcompPd.ecGoodsPrice.price / konkaBcompPd.ecGoodsPrice.original_price)*10} </c:if>
                  </font></li>
                <li>节省<br>
                  <font class="black20px">￥
                  <fmt:formatNumber value="${konkaBcompPd.ecGoodsPrice.original_price - konkaBcompPd.ecGoodsPrice.price}" pattern="0" />
                  </font></li>
              </ul>
            </div>
          </div>
          <div class="tg_clock"><font class="black_14">剩余时间<br>
            <div id="divTimeRemains">&nbsp;</div>
            </font></div>
          <div class="tg_success"><font class="pink16px">${konkaBcompPd.sale_num}</font> 人已购买<br>
            抢购成功!可继续购买!</div>
        </div>
        <div class="tg_right box_606_317">
          <table border="0" cellspacing="0" cellpadding="0" width="606">
            <tr>
              <td height="317" style="overflow:hidden;" width="606" valign="middle" align="center"><c:set var="_main_pic" value="${ctx}/${fn:substringBefore(konkaBcompPd.main_pic, '.')}_600.jpg" />
                <c:if test="${fn:length(konkaBcompPd.picArray) ge 2}">
                  <c:set var="_main_pic" value="${ctx}/${fn:substringBefore(konkaBcompPd.picArray[1], '.')}_600.jpg" />
                </c:if>
                <img src="${_main_pic}" align="absmiddle" /> </td>
            </tr>
          </table>
        </div>
      </div>
    </div>
    <div class="togo_down">
      <div class="viewtit2">
        <ul>
          <li class="cur pd_content" id="pdc1">商品介绍</li>
          <li class="pd_content" id="pdc2">规格参数</li>
          <li class="pd_content" id="pdc4">售后服务</li>
        </ul>
      </div>
      <div class="viewbox4" id="pdc1_pdc1" style="display:'';overflow-x:hidden;">
        <c:forEach items="${konkaBcompPd.konkaBcompPdContentList}" var="cur">
          <c:if test="${cur.type eq '1'}">
            <c:out value="${cur.content}" escapeXml="false" />
          </c:if>
        </c:forEach>
      </div>
      <div class="viewbox4" id="pdc2_pdc2" style="display:none;overflow-x:hidden;">
        <c:forEach items="${konkaBcompPd.konkaBcompPdContentList}" var="cur">
          <c:if test="${cur.type eq '2'}">
            <c:out value="${cur.content}" escapeXml="false" />
          </c:if>
        </c:forEach>
      </div>
      <div class="viewbox4" id="pdc4_pdc4" style="display:none;overflow-x:hidden;">
        <c:forEach items="${konkaBcompPd.konkaBcompPdContentList}" var="cur">
          <c:if test="${cur.type eq '3'}">
            <c:out value="${cur.content}" escapeXml="false" />
          </c:if>
        </c:forEach>
      </div>
      <div class="clear"></div>
    </div>
  </div>
  <div class="togo_right">
    <div class="togo_box"><font class="orange14px">新闻资讯</font></div>
    <div class="togo_box_down">
      <ul>
        <c:forEach items="${konkaPeArticleInfoList}" var="cur" varStatus="vs">
      		<li><a title="${cur.title}" href="<c:url value='/touch/KonkaGroupPeArticleInfo.do?method=view&id=${cur.id}' />">${fnx:abbreviate(cur.title, 2 * 18, '.')}</a></li>
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
        <div class="title99"><a title="" href="<c:url value='/touch/PshowOrderPanicBuying.do?method=view&goods_id=${pd_list_cate5[0].id}' />" target="_blank">${fn:escapeXml(pd_list_cate5[0].pd_desc)}</a></div>
        <div class="pic99 box_270_202">
          <table border="0" cellspacing="0" cellpadding="0" width="270">
            <tr>
              <td height="202" style="overflow:hidden;" width="270" valign="middle" align="center"><a title="" href="<c:url value='/touch/PshowOrderPanicBuying.do?method=view&goods_id=${pd_list_cate5[0].id}' />" target="_blank">
                <c:set var="_main_pic" value="${ctx}/${fn:substringBefore(pd_list_cate5[0].main_pic, '.')}_240.jpg" />
                <c:if test="${fn:length(pd_list_cate5[0].picArray) ge 2}">
                  <c:set var="_main_pic" value="${ctx}/${fn:substringBefore(pd_list_cate5[0].picArray[1], '.')}_240.jpg" />
                </c:if>
                <img src="${_main_pic}" alt="" align="absmiddle" /></a></td>
            </tr>
          </table>
        </div>
        <div class="buy99">
          <div class="n-tg"><span class="tg-mg1"><a title="查看详情" href="<c:url value='/touch/PshowOrderPanicBuying.do?method=view&goods_id=${pd_list_cate5[0].id}' />" target="_blank"> </a></span> </div>
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
	
	// 商品描述，商品规格，等切换
	$(document).delegate(".pd_content", "click", function(){
		$(this).attr("hover", "hover"); // 这里标记鼠标指向的那个图片

	    var class_name = $(this).attr("class");
		$(".pd_content").each(function(){
			var id = $(this).attr("id");
		    if($(this).attr("hover") == "hover"){
				 $(this).removeClass("cur pd_content").addClass("cur pd_content");
				 $("#" + id + "_" + id).show();
			} else {
				$(this).removeClass("cur pd_content").addClass("pd_content");
				 $("#" + id + "_" + id).hide();
			}
		});

		$(this).removeAttr("hover");
	});
	
});

var timeNow = '${today}';

var timeEnd = '${konkaBcompPd.timeRemains}';

var timeRemains =  timeEnd - timeNow;

var iTime = timeRemains/1000;
var Account;
RemainTime();

function RemainTime()
{
    var iDay,iHour,iMinute,iSecond;
    var sDay="",sHour="",sMinute="",sSecond="",sTime="";
    if (iTime >= 0)
    {
        iDay = parseInt(iTime/24/3600);
        if (iDay > 0)
        {
            sDay = iDay + "天";
        }
        iHour = parseInt((iTime/3600)%24);
        if (iHour > 0){
            sHour = iHour + "小时";
        }
        iMinute = parseInt((iTime/60)%60);
        if (iMinute > 0){
            sMinute = iMinute + "分钟";
        }
        iSecond = parseInt(iTime%60);
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
        if(iTime==0){
            clearTimeout(Account);
              sTime="<span style='color:red'>抢购已结束！</span>";
        }
        else
        {
            Account = setTimeout("RemainTime()",1000);
        }
        iTime=iTime-1;
    }
    else
    {
            sTime="<span style='color:red'>抢购已结束！</span>";
    }
    document.getElementById("divTimeRemains").innerHTML = sTime;
}

//]]></script>
</body>
</html>
