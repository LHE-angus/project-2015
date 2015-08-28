<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:if test="${ecUser.user_type eq 1 }">康佳EPP内部优惠购机平台</c:if><c:if test="${ecUser.user_type eq 2 }">康佳触网直销商城</c:if></title> 
<link rel=stylesheet type=text/css  href="${ctx}/styles/touch/css/global1.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/touch/css/epp.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/touch/css/touch.css" />
<style type="text/css">
	.viper-grow-bg{margin-left:60px}
	.viper-grow-line{background-color:#FACD94;display:inline-block;height:18px;line-height:13px;overflow:hidden;text-align:left;vertical-align:middle;width:400px;}
	.viper-grow-state{background-color:#F60;color:#FFF;display:inline-block;height:18px;line-height:13px;text-align:right}
	.viper-grow-level{color:#676767;height:13px;line-height:18px}
</style>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<!-- head start -->
<div class="topbox">
<jsp:include page="/touch/__inc/top.jsp" flush="true" />
<jsp:include page="/touch/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/touch/__inc/nav.jsp" flush="true" />
<!-- topnav end --> 
<!-- second start --> 
<div class="maincont">
<jsp:include page="/touch/__inc/_mleft.jsp" flush="true" />
    <!--right-->
    <div class="touch_right padbot45">
   	  <div class="position"><a href="${ctx }/touch/Index.do">首页</a> &gt; 会员中心</div>
        <div class="touchtab2">
        	<h3>亲爱的<span style="font-size:20px;color: red;"> ${ecUser.real_name }</span>欢迎您来到康佳触网直销商城！上一次登录时间：<fmt:formatDate value="${ecUser.last_login_time}" pattern="yyyy-MM-dd HH:mm:ss" /></h3>
         <c:if test="${touch eq 1 }">
        <h3>您现在的会员等级是：<span style="font-size:20px;color: red;"> ${ecUser.ecBaseCardLevel.card_level_name}</span>，距离升级到 <span style="font-size:20px;color: red;" class="s1"></span>&nbsp;&nbsp;还差<span style="font-size:20px;color: red;">${need_jf}</span>付款积分！
                 <!--  <span class="viper-grow-line" style="margin-left: 20px;">
                     <span class="viper-grow-state">${payTotalScore}/<c:if test="${ecUser.ecBaseCardLevel.card_level_name eq '银卡会员'}">30000</c:if><c:if test="${ecUser.ecBaseCardLevel.card_level_name eq '金卡会员'}">100000</c:if></span>
                 </span>
                    <span class="viper-grow-level"></span> -->
        </h3></c:if>
        </div><c:if test="${ecUser.user_type eq 1}"><c:if test="${ecUser.is_act ne 0 or ecUser.login_count <20 }">
        <div class="touchtab2">
        <a href="http://epp.konka.com/touch/KonkaGroupPeArticleInfo.do?method=view&id=755139" target="_blank"><img src="${ctx}/styles/touch/images/ec_hygz.jpg" /></a>
        <a href="http://epp.konka.com/touch/KonkaGroupPeArticleInfo.do?method=view&id=753541" target="_blank"><img src="${ctx}/styles/touch/images/ec_hyqy.jpg" /></a>
        <a href="http://epp.konka.com/touch/KonkaGroupPeArticleInfo.do?method=view&id=755186" target="_blank"><img src="${ctx}/styles/touch/images/ec_jfgz.jpg" /></a>
        <a href="http://epp.konka.com/touch/KonkaGroupPeArticleInfo.do?method=view&id=755187" target="_blank"><img src="${ctx}/styles/touch/images/ec_yjgz.jpg" /></a>
        </div></c:if></c:if>
        <div class="touchtab3">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="touch_table1">
        <tbody>
        <tr>
        	<td width="34%"><h3 class="touch_tdh3">订单提醒：</h3><h4 class="touch_tdh4"><a href="Orders.do">待处理订单(${pShowOrderNum})</a></h4></td> 
         	<td><c:if test="${touch eq 1 }"><h3 class="touch_tdh3">我的积分：</h3><h4 class="touch_tdh4"><a href="${ctx }/touch/EcGift.do"> ${totalScore}分</a></h4></c:if></td>
        </tr>
        </tbody>
        </table>
        </div>
        <ul class="touchtit3"><li class="curli" id="lia1"><a href="JavaScript:void(0);">限时抢购</a></li><li id="lia2"><a  href="JavaScript:void(0);">您关注的商品</a></li></ul>
        <div class="touchtab4">
        <ul class="touchul2" id="ula1">
        <c:forEach items="${bcomp_pd_list_5}" var="cur" varStatus="vs">
	        <li>
		        <div class="touchlipic">
		        	<a href="<c:url value='/touch/PshowOrderPanicBuying.do?method=add&goods_id=${cur.id}' />"><img alt="${cur.pd_name}" src="${ctx}/${fn:substringBefore(cur.main_pic, '.')}_095.jpg" /></a>
		        </div>
		        <h3 class="touchlitit"><a href="<c:url value='/touch/PshowOrderPanicBuying.do?method=add&goods_id=${cur.id}' />" >${cur.pd_sn} </a> </h3>
		        <h3 class="touchlitit">原 价：<font class="midtxt">￥<fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></font> </h3>
		        <h3 class="touchlitit">抢购价：<a class="fontblue14" href="<c:url value='/touch/PshowOrderPanicBuying.do?goods_id=${cur.id}' />" >￥<fmt:formatNumber value="${cur.ecGoodsPrice.price}" pattern="0" /></a></h3>
	        </li>
        </c:forEach>
        </ul>
        <ul class="touchul2" id="ula2" style="display:none;">
         <c:forEach var="cur" items="${ecUserFavotriesList}" varStatus="vs"> 
	        <li><div class="touchlipic"><a href="${ctx }/touch/PdShow.do?goods_id=${cur.goods_id }">
	         <img src="${ctx}/${fn:substringBefore(cur.img_url, '.')}_095.jpg" /></a></div>
	         <h3 class="touchlitit"><a href="${ctx }/touch/PdShow.do?goods_id=${cur.goods_id }"><c:out value="${cur.md_name }" /></a></h3>
	         <h3 class="touchlitit">关注价格：<font class="fontblue14">￥${cur.price }</font></h3> 
	        </li>
         </c:forEach>
         <c:if test="${empty ecUserFavotriesList }">
         <li style="width:100%;font-size:14px;height:40px;text-align:center"> 您还没有关注任何商品,点击<a href="${ctx }/touch/Index.do"><font color="#2233ff">这里</font></a>去关注您喜爱的商品吧 </li>
         </c:if>
        </ul>
        </div>
        <ul class="touchtit3"><li class="curli" id="lib1"><a class="redfont" href="JavaScript:void(0);">猜你喜欢</a></li><li id="lib2"><a href="JavaScript:void(0);">近期订单</a></li></ul>
        <div id="ulb1">
        <div class="touchtab4" >
        <i class="to_left">
			<a id="butPrevGroup" href="javascript:void(0);"><img src="${ctx }/styles/touch/images/arrow_left.gif" /></a>
		</i>
        <div class="touchulbox3" >
        <ul class="touchul3" id="hotPd" >            
	        <c:forEach items="${bcomp_pd_list_2}" var="cur" varStatus="vs">
	        <li id="hotPd${vs.count}" <c:if test="${vs.count>6 }">style="display:none"</c:if>>
		        <div class="touchlipic">
		        	<a href="<c:url value='/touch/PdShow.do?goods_id=${cur.id}' />" title="康佳${cur.pd_sn}&nbsp;${cur.pd_name}"> <img alt="${cur.pd_name}" src="${ctx}/${fn:substringBefore(cur.main_pic, '.')}_095.jpg" align="absmiddle" /> </a>
		        </div>
		        <h3 class="touchlitit"><a href="<c:url value='/touch/PdShow.do?goods_id=${cur.id}' />" title="${cur.pd_name}" >${fnx:abbreviate_si(fn:escapeXml(cur.pd_name), 24)}</a></h3>
		        <h3 class="touchlitit">会员价：<a class="fontblue14" href="<c:url value='/touch/PdShow.do?goods_id=${cur.id}' />">￥<c:if test="${ecUser.user_type eq 1 }"><fmt:formatNumber value="${cur.ecGoodsPrice.price}" pattern="0" /></c:if>
                <c:if test="${ecUser.user_type eq 2 }"><fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></c:if></a></h3>
	        </li>
        </c:forEach> 
        </ul>
        </div>
        <i class="to_right">
			<a id="butNextGroup" href="javascript:void(0);"><img src="${ctx }/styles/touch/images/arrow_right.gif" /></a>
		</i> 
        </div>
        </div>
        <div id="ulb2" style="display:none;"><br/>
        <table width="99%" border="0" cellspacing="0" cellpadding="0" class="touch_form_table2">
	        <tr class="tr1">
	          <td width="8%" nowrap="nowrap"  align="center">订单编号</td>
	          <td width="8%" nowrap="nowrap" align="center">订单商品</td>
	          <td width="8%" nowrap="nowrap" align="center">收货人</td>
	          <td width="8%" nowrap="nowrap" align="center">订单应付金额</td>
	          <td width="8%" nowrap="nowrap" align="center">下单时间</td>
	          <td width="8%" nowrap="nowrap" align="center">支付类型</td>
	          <td width="8%" nowrap="nowrap" align="center">订单状态</td>
	          <td width="8%" nowrap="nowrap" align="center">操作</td>
	        </tr>
        <tbody>
        <c:forEach var="cur" items="${pShowOrderList}" varStatus="vs">
            <tr height="60">
              <td align="center" nowrap="nowrap"><a href="Orders.do?method=view&id=${cur.id}"><c:out value="${cur.trade_index}"/></a></td>
              <td align="center" nowrap="nowrap"><c:forEach items="${cur.pshowOrdeDetailsList}" var="cur2">
                  <c:set value="${fn:split(cur2.map.main_pic, ',') }" var="imgs" />
                  <a href="${ctx }/touch/PdShow.do?goods_id=${cur2.pd_id}"><c:forEach items="${imgs }" var="img1" begin="0" end="0"><img src="${ctx }/${img1}" alt="${cur2.map.pd_name }" width="51" height="40"/></c:forEach></a>
                  <br/>
                </c:forEach>
              </td>
              <td align="center" nowrap="nowrap"><c:out value="${cur.buyer_name}" />
              </td>
              <td align="center" nowrap="nowrap"><c:out value="￥${cur.pay_price}" />
              </td>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss" />
              </td>
              <td align="center" nowrap="nowrap"><c:if test="${cur.pay_way eq 0}">货到付款</c:if>
                <c:if test="${cur.pay_way eq 1}">在线支付</c:if>
                <c:if test="${cur.pay_way eq 2}">支付宝</c:if>
                <c:if test="${cur.pay_way eq 3}">银联</c:if>
                <c:if test="${cur.pay_way eq 5}">民生e支付</c:if>
              </td>
              <td align="center" nowrap="nowrap"><c:if test="${cur.state eq -30 }">退货成功</c:if>
                <c:if test="${cur.state eq -20 }">处理失败</c:if>
                <c:if test="${cur.state eq -10 }">已取消</c:if>
                <c:if test="${cur.state eq 0 }">待付款</c:if>
                <c:if test="${cur.state eq 10 }">已付款待处理</c:if>
                <c:if test="${cur.state eq 20 }">订单处理中</c:if>
                <c:if test="${cur.state eq 30 }">订单处理中</c:if>
                <c:if test="${cur.state eq 40 }">已发货</c:if>
                <c:if test="${cur.state eq 50 }">已换货</c:if>
                <c:if test="${cur.state eq 60 }">交易完成</c:if>
              </td>
              <td align="center" nowrap="nowrap" style="line-height:23px;">
              	<c:if test="${cur.state eq -30 }"><a href="Orders.do?method=view&id=${cur.id}"><font color="#0022ff">订单详情</font></a></c:if>
                <c:if test="${cur.state eq -20 }"><a href="Orders.do?method=view&id=${cur.id}"><font color="#0022ff">订单详情</font></a></c:if>
                <c:if test="${cur.state eq -10 }"><a href="Orders.do?method=view&id=${cur.id}"><font color="#0022ff">订单详情</font></a></c:if>
                <c:if test="${cur.state eq 0 }"><a class="btn btn-4" href="<c:url value='/touch/Payment.do?trade_index=${cur.trade_index}' />"><s></s>付款</a><br/> <a href="#" id="t${cur.id }" onclick="deleteInfo(this);"><font color="#0022ff">取消订单</font></a></c:if>
                <c:if test="${cur.state eq 10 }"><a href="Orders.do?method=view&id=${cur.id}"><font color="#0022ff">订单详情</font></a> </c:if>
                <c:if test="${cur.state eq 20 }"><a href="Orders.do?method=view&id=${cur.id}"><font color="#0022ff">订单详情</font></a> </c:if>
                <c:if test="${cur.state eq 30 }"><a href="Orders.do?method=view&id=${cur.id}"><font color="#0022ff">订单详情</font></a></c:if>
                <c:if test="${cur.state eq 40 }"><a href="Orders.do?method=view&id=${cur.id}"><font color="#0022ff">订单详情</font></a></c:if>
                <c:if test="${cur.state eq 50 }"><a href="Orders.do?method=view&id=${cur.id}"><font color="#0022ff">订单详情</font></a></c:if>
                <c:if test="${cur.state eq 60 }"><a href="Orders.do?method=view&id=${cur.id}"><font color="#0022ff">订单详情</font></a> </c:if>
              </td>
            </tr>
          </c:forEach>
          <c:if test="${empty pShowOrderList }">
            <tr height="60">
              <td align="center" nowrap="nowrap" colspan="8"> 近期没有订单 </td>
            </tr>
          </c:if>
        </tbody>
      </table>
         </div>
    </div>
  <div class="clear"></div>
</div>
<jsp:include page="/touch/__inc/footer.jsp" flush="true" />
<!-- six footer -->
</body>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
    
		$("#lia1").click(function(){
			 $("#lia1").addClass("curli");
			 $("#lia2").removeClass("curli");
			 $("#ula1").show();
			 $("#ula2").hide();
		});
		$("#lia2").click(function(){
			 $("#lia2").addClass("curli");
			 $("#lia1").removeClass("curli");
			 $("#ula2").show();
			 $("#ula1").hide();
		});
		$("#lib1").click(function(){
			 $("#lib1").addClass("curli");
			 $("#lib2").removeClass("curli");
			 $("#ulb1").show();
			 $("#ulb2").hide();
		});
		$("#lib2").click(function(){
			 $("#lib2").addClass("curli");
			 $("#lib1").removeClass("curli");
			 $("#ulb2").show();
			 $("#ulb1").hide();
		});
		
		$("#butPrevGroup").click(function(){			
			var pds=$('#hotPd').children().length;
			var f=0;
			 for(var i=1;i<=pds;i++){
				var show= $('#hotPd'+i).css('display');	
				if(show!='none'){
					f=i;
				}
			 }  
			 if(f-6>0){
				 $('#hotPd'+(f-6)).css('display','block'); 
				 $('#hotPd'+f).css('display','none');  
			 } 			 
		});
		
		$("#butNextGroup").click(function(){	
			var pds=$('#hotPd').children().length; 
			var f=0;
			 for(var i=1;i<=pds;i++){
				 var show= $('#hotPd'+i).css('display'); 
				if(show!='none'){
					f=i;
				}
			 } 
			 if(f-5>0&&f<pds){
			 $('#hotPd'+(f-5)).css('display','none'); 
			 $('#hotPd'+(f+1)).css('display','block'); 
			 }else{
				 alert('已经是最后一个产品了');
			 }			 
		});

		var card_level="${ecUser.ecBaseCardLevel.card_level_name}";
		if(card_level=="银卡会员"){
			$(".s1").text("金卡会员");
		}else if(card_level=="金卡会员"){
			$(".s1").text("白金卡会员");
		}else{
			$(".s1").text("白金卡会员");
		}

		var wd="${wd}";
		$(".viper-grow-state").css({"width":wd});
		
}); 

function deleteInfo(obj){
	var	id ="&id="+ obj.id.replace("t",""); 
	if(confirm("您确定取消订单吗？")){
		location.href="${ctx}/touch/center/Orders.do?method=delete"+id; 
	}
}
//]]></script>
</html>