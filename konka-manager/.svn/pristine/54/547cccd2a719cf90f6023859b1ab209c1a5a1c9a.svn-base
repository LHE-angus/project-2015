<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:if test="${ecUser.user_type eq 1 }">康佳EPP内部优惠购机平台</c:if><c:if test="${ecUser.user_type eq 2 }">康佳网上直销商城</c:if></title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/global1.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/epp.css" /> 
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/purchase.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/jifen.css" />  
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>

</head>
<body>
<!-- head start -->
<div class="topbox">
<jsp:include page="/member/__inc/top.jsp" flush="true" />
<jsp:include page="/member/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/member/__inc/nav.jsp" flush="true" />
<!-- topnav end -->
<!-- first start -->
<!-- first end -->
<!-- second start -->
<div class="maincont margintop10">
  <!--left-->
  <div class="listleft">
    <c:if test="${not empty ecUser}">
      <div class="liscont1">
        <div class="listit1">个人信息</div>
        <div class="jifen-box">
          <div class="jifen-font">
            <h1>${fn:escapeXml(ecUser.real_name)}   </h1> 
            <span>会员等级：${ecUser.ecBaseCardLevel.card_level_name} </span>
            <span>积分余额：${surplus_integral}积分</span>
            <span> 积分不足： <a href="#">[去充值]</a> </span>
            <span></span></div>        </div>
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
              <li class="s">${fnx:abbreviate(cur.pd_sn, 2 * 7, '.')}&nbsp;${fnx:abbreviate(cur.pd_name, 2 * 7, '.')}</li>
            </c:if>
            <c:if test="${vs.count eq 2}">
              <li class="b">${fnx:abbreviate(cur.pd_sn, 2 * 7, '.')}&nbsp;${fnx:abbreviate(cur.pd_name, 2 * 7, '.')}</li>
            </c:if>
            <c:if test="${vs.count eq 3}">
              <li class="c">${fnx:abbreviate(cur.pd_sn, 2 * 7, '.')}&nbsp;${fnx:abbreviate(cur.pd_name, 2 * 7, '.')}</li>
            </c:if>
            <c:if test="${vs.count eq 4}">
              <li class="d">${fnx:abbreviate(cur.pd_sn, 2 * 7, '.')}&nbsp;${fnx:abbreviate(cur.pd_name, 2 * 7, '.')}</li>
            </c:if>
            <c:if test="${vs.count eq 5}">
              <li class="e">${fnx:abbreviate(cur.pd_sn, 2 * 7, '.')}&nbsp;${fnx:abbreviate(cur.pd_name, 2 * 7, '.')}</li>
            </c:if>
            <c:if test="${vs.count eq 6}">
              <li class="f">${fnx:abbreviate(cur.pd_sn, 2 * 7, '.')}&nbsp;${fnx:abbreviate(cur.pd_name, 2 * 7, '.')}</li>
            </c:if>
            <c:if test="${vs.count eq 7}">
              <li class="g">${fnx:abbreviate(cur.pd_sn, 2 * 7, '.')}&nbsp;${fnx:abbreviate(cur.pd_name, 2 * 7, '.')}</li>
            </c:if>
            <c:if test="${vs.count eq 8}">
              <li class="h">${fnx:abbreviate(cur.pd_sn, 2 * 7, '.')}&nbsp;${fnx:abbreviate(cur.pd_name, 2 * 7, '.')}</li>
            </c:if>
            <c:if test="${vs.count eq 9}">
              <li class="i">${fnx:abbreviate(cur.pd_sn, 2 * 7, '.')}&nbsp;${fnx:abbreviate(cur.pd_name, 2 * 7, '.')}</li>
            </c:if>
            <c:if test="${vs.count eq 10}">
              <li class="j">${fnx:abbreviate(cur.pd_sn, 2 * 7, '.')}&nbsp;${fnx:abbreviate(cur.pd_name, 2 * 7, '.')}</li>
            </c:if>
          </c:forEach>
        </ul>
      </div>
    </div>
  </div>
  <!--right-->
  <div class="listright">
  <div class="search" style="margin-top:10px;width:100%"><span >积分在线充值</span></div>
  <hr/>
  <html-el:form action="/EcGiftJf">
  <input type="hidden" name="method" value="createOrder" />
  <div style="font-size:16px;">
   <table width="100%" border="0" cellpadding="0" cellspacing="0" >
   <tr>
	   <td width="15%" align="right" height="44"><b>充值积分</b>:</td>
	   <td width="85%"><input type="text" name="integral" value="" id="integral" onchange="getPrice();" maxlength="10"/> 应付金额：<font color="red" id="price"></font></td>
   </tr>
   <tr>
	   <td width="15%" align="right" height="34"></td>
	   <td><span id="go_buy_btn" style="cursor:pointer;padding:5px;background-color:#FF5200"><font style="color:#ffffff;">去支付</font></span>  <font style="font-size:12px;color:#cccccc;">最低充值100积分</font> </td>
   </tr>
   </table>
   </div>
  </html-el:form>
  </div>
</div>
<!-- second end -->
<!-- six footer -->
<jsp:include page="../__inc/footer.jsp" flush="true" />
<!-- six footer -->
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){ 
	$("#integral" ).focus(function(){
		setOnlyInt(this); 
	});
	$(document).delegate("#go_buy_btn", "click", function(){
		if($("#integral").val()!=null){
			if(parseInt($("#integral" ).val())<100){
				alert("最少充值100积分!");
				return false;
			}
		}else{
			alert("请输入充值积分!");
			return false;
		}
		$("#af").submit();
	});
});

function setOnlyInt(obj) {
	$(obj).css("ime-mode", "disabled");
	$(obj).attr("t_value", "");
	$(obj).attr("o_value", "");
	$(obj).bind("dragenter",function(){
		return false;
	});
	$(obj).keypress(function (){
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).keyup(function (){
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).blur(function (){
		if(!obj.value.match(/^(?:\d+(?:\d+)?|\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\d+$/))obj.value=obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
		if(obj.value.length == 0 || isNaN(obj.value) || obj.value == 0) obj.value = "0";
	});
} 

function getPrice(){
if($("#integral" ).val()!=null){ 
	var price=parseInt($("#integral" ).val())/100; 
	$("#price" ).html(price+" 元");
}else{
	$("#price" ).html();
}

}
//]]></script>
</body>
</html>
