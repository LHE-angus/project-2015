<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:if test="${ecUser.user_type eq 1 }">康佳EPP内部优惠购机平台</c:if><c:if test="${ecUser.user_type eq 2 }">康佳网上直销商城</c:if></title>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/member/css/global1.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/member/css/epp.css" />  
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/purchase.css" /> 
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/cloud-zoom/styles/image_show.css" />

<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
<style>
.gray {-webkit-filter: grayscale(100%); -moz-filter: grayscale(100%); -ms-filter: grayscale(100%); -o-filter: grayscale(100%); filter: grayscale(100%); filter: gray;}
</style>
</head>
<body>
<!-- head start -->
<!-- top start -->
<div class="topbox">
<jsp:include page="/member/__inc/top.jsp" flush="true" />
<jsp:include page="/member/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/member/__inc/nav.jsp" flush="true" />
<!-- top end -->  
<!-- first end --> 

<!-- second start -->
<div class="maincont margintop10" >
<br/>
  <div class="position"><a href="<c:url value='/member/Index.do' />">首页</a> &gt; 0元抽奖</div>
  <div class="viewbox1" style="min-height:450px;">
  	<!-- 产品展示详细 -->
    <jsp:include page="../__inc/ec_lucky_images.jsp" flush="true" /> 
    <!-- 表单开始 -->
    <form action="<c:url value='/member/Lucky.do' />" id="form_pd_buy" method="post">
    <input type="hidden" name="method" value="stepTwo" />
    <input type="hidden" name="lucky_id" id="lucky_id" value="${entity.id}" /> 
    <input type="hidden" name="p_index" id="p_index" value="${p_index}" /> 
    <div class="viewright">
      <div class="viewtit1"><h3><c:out value="${entity.title}" /></h3><h4><c:out value="${entity.brief}" escapeXml="false"/></h4><div class="clear"></div>
      </div>
      <div class="viewtab1">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="proxltab"> 
             <tr>
              <td colspan="3" height="22"><strong class="yel16b redfont" id="price_tag">￥ 0</strong> 元</td>
            </tr> 
            <tr>
              <td colspan="3" height="22">已抢到<span style='color:darkorange;'><fmt:formatNumber value="${buy_count}" pattern="#0" /></span>楼， 浏览<span style='color:darkorange;'><fmt:formatNumber value="${entity.view_counts}" pattern="#0" /></span>次</td>
            </tr> 
        </table>
      </div>
      <div class="viewtab2">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="proxltab">
          <tbody>
            <tr>
              <td width="10%">状态：</td>
              <td width="90%"><c:if test="${entity.lucky_state eq 0 }"><font  style="font-size: 18px;color: red;margin-left:-35px;">活动未开始</font></c:if>   
              <c:if test="${entity.lucky_state eq 1 }"><font  style="font-size: 18px;color: red;margin-left:-35px;">活动进行中</font></c:if>
              <c:if test="${entity.lucky_state eq 2 }"><font  style="font-size: 18px;color: red;margin-left:-35px;">活动已结束</font></c:if>
              </td>
            </tr> 
            <tr>
              <td colspan="2">
              	<div class="padtop20"><img class="gray" alt="立即抢购" src="${ctx}/styles/member/images/but_qianglou.jpg" style="cursor:pointer;" id="buy_btn" />
              	每人每天可以参与<font color="red">${entity.lucky_num }</font>次，您已参与<font color="red">${buy_times}</font>次
              	</div>
              </td>
            </tr>
            <c:if test="${entity.lucky_state eq 1 }">  
            <tr>
               <td colspan="2">
               	<span id="divTimeRemains"></span>
               </td>
            </tr>
            </c:if>
          </tbody>
        </table>
      </div>
    </div>
	</form>    
    <div class="clear"></div>
  </div>
  <div class="clear"></div>
</div>
<div class="maincont"> 
  <!--left--> 
  <div class="listleft">
    <div class="liscont1">
      <div class="listit1">推荐热卖</div>
      <div class="listbox1">
        <ul class="listul4" style="padding:0;">
           <c:forEach items="${bcomp_pd_list_top_5}" var="cur" varStatus="vs">
           	  <c:set var="li_class" value="" />
              <c:if test="${vs.last}"><c:set var="li_class" value="noline4" /></c:if>
	          <li style="padding:0;" class="${li_class}">
	            <div class="listpic1"><table border="0" cellspacing="0" cellpadding="0" width="120"><tr><td height="110" style="overflow:hidden;" width="120" valign="middle" align="center"><a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />"><img src="${ctx}/${cur.main_pic}" style="max-height:100px;" width="100"/></a></td></tr></table></div>
	            <div class="listrxt1">
	              <h3><a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />">康佳&nbsp;<c:out value="${cur.pd_sn}" />&nbsp;<c:out value="${cur.pd_name}" /></a></h3>
	              <h4>会员价：￥<fmt:formatNumber value="${cur.ecGoodsPrice.price}" pattern="0" /></h4>
	            </div>
	            <div class="clear"></div>
	          </li>
           </c:forEach>
        </ul>
      </div>
      <div class="clear"></div>
    </div>
  </div>
  <!--right-->
  <div class="liscont3">  
	<div class="viewtit2"  >	
      <ul>
        <li class="cur pd_content" id="pdc1">活动详情</li>
        <li class="pd_content" id="pdc2">活动须知</li> 
        <li class="pd_content" id="pdc3">抢楼记录</li> 
        <li class="pd_content" id="pdc4">开奖信息</li> 
      </ul>
    </div>
    <div class="viewbox4" id="pdc3_pdc3" style="display:none;overflow-x:hidden;">
      	<a href="javascript:void(0);" onclick="showT(0);" id="h_0"><b>今天</b></a><c:set var="days" value="0" /><c:set var="cdays" value="0" /><c:set var="c" value="0" />
	    <c:forEach items="${entity.ecLuckyBuyList}" var="cur" varStatus="vs"><c:if test="${cur.map.days ne 0 }"><c:set var="c" value="${c+1 }" /><c:if test="${cdays ne cur.map.days  }"><c:set var="cdays" value="${cur.map.days}" /><c:set var="days" value="${days+1 }" /><c:set var="c" value="1" />
		&nbsp;&nbsp;<a href="javascript:void(0);" onclick="showT(${days });" id="h_${days }"><b><fmt:formatDate value="${cur.add_date}"  pattern="yyyy-MM-dd"/></b></a>
		</c:if></c:if></c:forEach><br/><br/><c:set var="days" value="0" /><c:set var="cdays" value="0" /><c:set var="c" value="0" />
		<c:forEach items="${entity.ecLuckyBuyList}" var="cur" varStatus="vs"><c:if test="${cur.map.days ne 0  }"><c:set var="c" value="${c+1 }" /><c:if test="${cdays ne cur.map.days  }"><c:set var="cdays" value="${cur.map.days}" /><c:set var="days" value="${days+1 }" /><c:set var="c" value="1" /><c:if test="${days >1  }"></table></c:if><table style="width:100%;" border="1" id="table_${days }"> 
			<tr><td colspan="5">第${days }天&nbsp;<fmt:formatDate value="${cur.add_date}"  pattern="yyyy-MM-dd"/></td></tr><tr><td width="5%">楼层 </td><td width="15%">流水号 </td><td align="center" width="20%">抢楼时间 </td><td align="center" width="10%">数量 </td><td align="center" width="15%">购买人 </td></tr></c:if> 
			<tr><td>${c}</td><td>${cur.trade_index }</td><td><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td><td>${cur.num }</td><td>${cur.user_name }</td></tr></c:if></c:forEach>
		    <table style="width:100%;" border="1" id="table_0"> 
			 	<tr><td colspan="5">今天&nbsp;<fmt:formatDate value="${cur.add_date}"  pattern="yyyy-MM-dd"/></td></tr>
				<tr><td width="5%">楼层 </td><td width="15%">流水号 </td><td width="20%">抢楼时间 </td><td width="10%">数量 </td><td width="15%">购买人 </td></tr><c:set var="c1" value="0" /><c:forEach items="${entity.ecLuckyBuyList}" var="cur" varStatus="vs"><c:if test="${cur.map.days eq 0  }"><c:set var="c1" value="${c1+1 }" />  
			    <tr><td>${c1}</td><td>${cur.trade_index }</td><td><fmt:formatDate value="${cur.add_date}"  pattern="yyyy-MM-dd HH:mm:ss"/></td><td>${cur.num }</td><td>${cur.user_name }</td></tr></c:if></c:forEach>
		 	</table>
		<script type="text/javascript">//<![CDATA[ 
		function showT(t){
			for(var i=0;i<=10;i++){
				 if(i==t){$("#h_"+i).css("color","red"); $("#table_"+i).show();}else{$("#table_"+i).hide();	 $("#h_"+i).css("color","blue");}
			}
		}
		$(document).ready(function(){
			 showT(0); 
		});
		//]]></script> 
    </div>
    <div class="viewbox4" id="pdc4_pdc4" style="display:none;overflow-x:hidden;">
    	 <c:out value="${entity.lucky_msg}" escapeXml="false" /> 
    </div> 
    <div class="viewbox4" id="pdc2_pdc2" style="display:none;overflow-x:hidden;"> 
    	 <c:out value="${entity.lucky_memo}" escapeXml="false" /> 
    </div> 
    <div class="viewbox4" id="pdc1_pdc1" style="display:'';overflow-x:hidden;">    
    	 <c:out value="${entity.content}" escapeXml="false" /> 
    </div> 
    <div class="clear"></div>
  </div>
  <div class="clear"></div>
</div>
<!-- second end -->  
<!-- six footer -->
<jsp:include page="../__inc/footer.jsp" flush="true" />
<!-- six footer --> 
<script type="text/javascript">//<![CDATA[
var flg=0;    
var msg="";
<c:if test="${entity.lucky_state ne 1 || is_act eq 0  }">
msg="对不起,活动未开始";
</c:if>
<c:if test="${buy_times ge entity.lucky_num }">
msg="对不起,您已参与了${buy_times}次活动";
</c:if>
$(document).ready(function(){
	// 立即购买
	$(document).delegate("#buy_btn", "click", function(){ 
		if(msg!=""){
			alert(msg);
			return false;
		}
		<c:if test="${entity.lucky_state eq 1}">
		if(flg==1){
		$("#form_pd_buy").submit();	 
		}
		 </c:if> 
	}); 
	
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
})

$(function(){
    $(".tab-cat li").bind("click",function(){
       $(".tab-cat-content li").eq($(this).index()).show().siblings().hide();
    })
 })
 
 
var timeEnd = '${timeRemains}';
var timeRemains =  timeEnd;
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
            sDay = "<font color='red'>"+iDay + "</font>天";
        }
        iHour = parseInt((iTime/3600)%24);
        if (iHour > 0){
            sHour = "<font color='red'>"+iHour + "</font>小时";
        }
        iMinute = parseInt((iTime/60)%60);
        if (iMinute > 0){
            sMinute = "<font color='red'>"+iMinute + "</font>分钟";
        }
        iSecond = parseInt(iTime%60);
        if (iSecond >= 0){
            sSecond ="<font color='red'>"+ iSecond + "</font>秒";
        }
        if ((sDay=="")&&(sHour=="")){
            sTime="<span style='color:darkorange;'>" + sMinute+sSecond + "</font>";
        }
        else
        {
            sTime=sDay+sHour+sMinute+sSecond;
        }
        if(iTime==0){
            clearTimeout(Account);
              sTime="<span style='color:red'>活动已结束！</span>";
              flg=0;
              $("#buy_btn").addClass("gray");
        }
        else
        {
            Account = setTimeout("RemainTime()",1000);
        }
        iTime=iTime-1;
    }
    else
    {
            sTime="<span style='color:red'>活动已结束！</span>";
            flg=0;
            $("#buy_btn").addClass("gray");
    }
    if(iTime>0){
    	 flg=1;
    	 $("#buy_btn").removeClass("gray");
    	 sTime="剩余时间："+sTime;
    }
    document.getElementById("divTimeRemains").innerHTML = sTime;
}

//]]></script>
</body>
</html>