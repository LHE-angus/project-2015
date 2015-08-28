<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康佳网上直销商城</title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/global1.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/epp.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/slider.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/purchase.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/jifen.css" />
<link rel=stylesheet type=text/css  href="${ctx}/commons/styles/pager3.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
<style type="text/css">

#topFilterSelected a {
display: inline;
float: left;
height: 18px;
line-height: 22px\9;
_line-height: 18px;
margin: 0px 4px 5px 0px;;
padding: 0 0 0 6px;
color: #fff;
white-space: nowrap;
background: url(${ctx}/images/repeatX.png) repeat-x 0 -120px;
border-left: 1px solid #fe6e0d;
border-bottom: 1px solid #ff6501;
border-radius: 3px;
}

#topFilterSelected a em {
cursor: pointer;
float: left;
font-style: normal;
}

#topFilterSelected a i {
cursor: pointer;
display: inline;
float: left;
width: 19px;
height: 19px;
background: url(${ctx}/images/topF.png) no-repeat;
background: url(${ctx}/images/topFIe.png) no-repeat\9;
}

.i {
color: #f60;
font-style: normal;
}

#resetFilter {
float: left;
margin-top: 1px;
margin-top: 3px\9;
_margin-top: 1px;
white-space: nowrap;
}

.buttonPBSearch  {
width:46px;
height:20px;
repeat-x;
font:normal 12px/20px "宋体";
text-align:center;
color:#fff;
border:1px #ccc solid;
border-left:0;
background: #F00B0B;
}
</style>
</head>
<body>
<!-- head start -->
<div class="topbox">
<jsp:include page="/zxmall/__inc/top.jsp" flush="true" />
<jsp:include page="/zxmall/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/zxmall/__inc/nav.jsp" flush="true" />
<!-- topnav end -->
<!-- first start -->
<!-- first end -->
<!-- second start -->
<div class="maincont margintop10">
  <!--left-->
  <div class="listleft">
    <c:if test="${not empty zxmall}">
      <div class="liscont1">
        <div class="listit1">个人信息</div>
        <div class="jifen-box">
          <div class="jifen-font">
            <h1>${fn:escapeXml(zxmall.real_name)} [<a href="${ctx}/zxmall/login.do?method=logout">退出</a>]</h1>
            <span>兑换积分余额：${surplus_integral}积分</span><span>会员等级：${zxmall.ecBaseCardLevel.card_level_name} </span><span> <a href="#" alt="">可兑换商品</a> </span><span></span></div>
        </div>
        <div class="clear"></div>
      </div>
      <div class="liscont1 margintop10"> </div>
    </c:if>
    <div class="liscont1">
      <div class="listit2">兑换排行</div>
      <div class="jifen-box2">
        <ul>
          <c:forEach items="${ec_gift_list_top_10}" var="cur" varStatus="vs">
            <c:if test="${vs.count eq 1}">
              <li class="s">&nbsp;康佳&nbsp;${fnx:abbreviate(cur.pd_sn, 2 * 7, '.')}&nbsp;${fnx:abbreviate(cur.pd_name, 2 * 7, '.')}</li>
            </c:if>
            <c:if test="${vs.count eq 2}">
              <li class="b">&nbsp;康佳&nbsp;${fnx:abbreviate(cur.pd_sn, 2 * 7, '.')}&nbsp;${fnx:abbreviate(cur.pd_name, 2 * 7, '.')}</li>
            </c:if>
            <c:if test="${vs.count eq 3}">
              <li class="c">&nbsp;康佳&nbsp;${fnx:abbreviate(cur.pd_sn, 2 * 7, '.')}&nbsp;${fnx:abbreviate(cur.pd_name, 2 * 7, '.')}</li>
            </c:if>
            <c:if test="${vs.count eq 4}">
              <li class="d">&nbsp;康佳&nbsp;${fnx:abbreviate(cur.pd_sn, 2 * 7, '.')}&nbsp;${fnx:abbreviate(cur.pd_name, 2 * 7, '.')}</li>
            </c:if>
            <c:if test="${vs.count eq 5}">
              <li class="e">&nbsp;康佳&nbsp;${fnx:abbreviate(cur.pd_sn, 2 * 7, '.')}&nbsp;${fnx:abbreviate(cur.pd_name, 2 * 7, '.')}</li>
            </c:if>
            <c:if test="${vs.count eq 6}">
              <li class="f">&nbsp;康佳&nbsp;${fnx:abbreviate(cur.pd_sn, 2 * 7, '.')}&nbsp;${fnx:abbreviate(cur.pd_name, 2 * 7, '.')}</li>
            </c:if>
            <c:if test="${vs.count eq 7}">
              <li class="g">&nbsp;康佳&nbsp;${fnx:abbreviate(cur.pd_sn, 2 * 7, '.')}&nbsp;${fnx:abbreviate(cur.pd_name, 2 * 7, '.')}</li>
            </c:if>
            <c:if test="${vs.count eq 8}">
              <li class="h">&nbsp;康佳&nbsp;${fnx:abbreviate(cur.pd_sn, 2 * 7, '.')}&nbsp;${fnx:abbreviate(cur.pd_name, 2 * 7, '.')}</li>
            </c:if>
            <c:if test="${vs.count eq 9}">
              <li class="i">&nbsp;康佳&nbsp;${fnx:abbreviate(cur.pd_sn, 2 * 7, '.')}&nbsp;${fnx:abbreviate(cur.pd_name, 2 * 7, '.')}</li>
            </c:if>
            <c:if test="${vs.count eq 10}">
              <li class="j">&nbsp;康佳&nbsp;${fnx:abbreviate(cur.pd_sn, 2 * 7, '.')}&nbsp;${fnx:abbreviate(cur.pd_name, 2 * 7, '.')}</li>
            </c:if>
          </c:forEach>
        </ul>
      </div>
    </div>
  </div>
  <!--right-->
  <div class="listright">
    <div class="box" style="width:892px;">
      <form id="giftListForm" name="giftListForm" method="post" action="EcGift.do">
        <input type="hidden" name="method" value="list" />
        <input type="hidden" name="need_integral_type" id="need_integral_type" />
        <input type="hidden" name="pd_sn_or_pd_name_like" id="pd_sn_or_pd_name_like" />
        <div class="search" style="width:100%"><span >积分查询</span>
          <ul>
            <li><a href="#" ${empty af.map.need_integral_type ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="need_integral_type_class" title="">全部</a></li>
            <li><a href="#" ${af.map.need_integral_type eq 1 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="need_integral_type_class" title="1,0-10000">小于1万</a></li>
            <li><a href="#" ${af.map.need_integral_type eq 2 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="need_integral_type_class" title="2,10000-80000">1万-8万</a></li>
            <li><a href="#" ${af.map.need_integral_type eq 3 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="need_integral_type_class" title="3,80000-500000">8万-50万</a></li>
            <li><a href="#" ${af.map.need_integral_type eq 4 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="need_integral_type_class" title="4,500000-900000000">50万以上</a></li>
          </ul>
        </div>
      </form>
    </div>
    <div class="clear"></div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabs_list2 margintop10">
      <tbody>
        <tr>
          <c:forEach items="${entityList}" var="cur" varStatus="vs">
          <td><div class="listul2">
              <div class="rpic2 box_295_220"><a title="" href="<c:url value='/zxmall/EcGift.do?method=view&gift_id=${cur.id}' />"><img alt="" src="${ctx}/${fn:substringBefore(cur.main_pic, '.')}.jpg" width="280" height="210" align="absmiddle" /></a></div>
              <div class="ltt2"><a href="<c:url value='/zxmall/EcGift.do?method=view&gift_id=${cur.id}' />">
                <h4>康佳&nbsp;${fn:escapeXml(cur.pd_sn)}</h4>
                <font class="redfont">${fn:escapeXml(cur.pd_name)}</font></a></div>
              <div class="ltt3">
                <h3>&nbsp;&nbsp;<font class="jifen-font">所需积分:</font>${fn:escapeXml(cur.need_integral)}
                 <c:if test="${not empty cur.ecGiftComm and not empty cur.ecGiftComm.price and not empty cur.ecGiftComm.goods_url  }">
                 &nbsp;&nbsp;&nbsp;&nbsp;<font class="jifen-font">商品价格:</font>${fn:escapeXml(cur.ecGiftComm.price)}元
                 </c:if>
                </h3>
                <a href="<c:url value='/zxmall/EcGift.do?method=stepTwo&gift_id=${cur.id}' />"><img alt="立即兑换" src="${ctx}/styles/zxmall/images/but_jifen.jpg" style="cursor:pointer;" /></a>
               <c:if test="${not empty cur.ecGiftComm and not empty cur.ecGiftComm.price and not empty cur.ecGiftComm.goods_url}"> 
                 &nbsp;&nbsp;&nbsp;&nbsp;<a href="${cur.ecGiftComm.goods_url}"><img alt="立即购买" src="${ctx}/styles/zxmall/images/but_comm.jpg" style="cursor:pointer;" /></a>
              </c:if>
              </div>
            </div></td>
          <c:if test="${vs.count mod 3 eq 0 and not vs.last}">
        </tr>
        <tr></c:if>
          </c:forEach>
        </tr>
      </tbody>
    </table>
    <!--page-->
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="EcGift.do">
      <div class="pagebox">
        <script type="text/javascript" src="${ctx}/commons/scripts/pager3.js">;</script>
        <script type="text/javascript">
			var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
			pager.addHiddenInputs("method", "list");
			pager.addHiddenInputs("need_integral_type", "${fn:escapeXml(af.map.need_integral_type)}");
			pager.addHiddenInputs("pd_sn_or_pd_name_like", "${fn:escapeXml(af.map.pd_sn_or_pd_name_like)}");
			document.write(pager.toString());
		</script>
      </div>
    </form>
  </div>
  <div class="clear"></div>
</div>
<!-- second end -->
<!-- six footer -->
<jsp:include page="../__inc/footer.jsp" flush="true" />
<!-- six footer -->
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var form = document.giftListForm;
	
	if($("#topFilter").children().length <= 2){
		$("#topFilter").attr("style","display:none");
	}else {
		$("#topFilter").attr("style","display:''");
	}
	
	//各类条件选择
	//-------------------------------------------
	
	//积分查询
	$(".need_integral_type_class").click(function(){
		$(".need_integral_type_class").removeAttr("style");
		$(this).attr("style","background: #FF6600;color:#fff;padding:2px;");
		
		var strs = new Array()
		strs = $(this).attr("title").split(",");
		if(strs.length == 2){
			$("#need_integral_type").val(strs[0]);
		}else if(strs.length < 2){
			$("#need_integral_type").val("");
		}
		
		form.submit();
	});
	
	//-------------------------------------------
	
	//去除已选择条件
	//----------------------------------------
	$("#need_integral_type_class").click(function(){
		$("#need_integral_type").val("");
		form.submit();
	});

	//----------------------------------------
	
	//重新筛选条件
	//----------------------------------------
	$("#resetFilter").click(function(){
		$("#need_integral_type").val("");

		form.submit();
	});
	//----------------------------------------
});

//]]></script>
</body>
</html>
