<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:if test="${ecUser.user_type eq 1 }">康佳EPP内部优惠购机平台</c:if><c:if test="${ecUser.user_type eq 2 }">康佳网上直销商城</c:if></title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/global1.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/epp.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/member.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<style type="text/css">
.login_div{position: absolute;	border: solid 1px #d1e3f5;top:35%;text-align: center;border:1px solid #E03425;background: #f5f4f4;left:40%;width:350px;padding-bottom: 0px;padding-top: 0px;display: none;z-index:10002;} 
fieldset {text-align:left;padding:10px;margin-top:5px; background:#fff;}
fieldset legend {color:#1E7ACE;font-weight:bold;padding:3px 20px 3px 20px;border:1px solid #E03425;background:#fff;}
fieldset label {float:left;width:100px;text-align:right;padding:4px;margin:1px;}
fieldset div {clear:left;margin-top:15px;margin-bottom:18px;}
fieldset .buttom {width:44px; padding:1px 10px; margin-left:10px;font-size:12px;border:1px #1E7ACE solid;background:#D0F0FF;}
#login_bg_div {display: none; position: absolute; top: 0%; left: 0%; width: 100%; height: 100%; background-color: black; z-index:10000; -moz-opacity: 0.7; opacity:.70; filter: alpha(opacity=60);}
</style> 
</head>
<body>
<!-- head start -->
<div class="topbox">
<jsp:include page="/member/__inc/top.jsp" flush="true" />
<jsp:include page="/member/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/member/__inc/nav.jsp" flush="true" />
<!-- second start -->
<div class="maincont">
  <jsp:include page="/member/__inc/_mleft.jsp" flush="true" />
  <div class="login_div" id="login_div" style="display:none;z-index:10000;position:absolute;">
<form action="" method="post" name="apLogin" id="apLogin">
	<fieldset>		
		<h3>请输入此订单的购买人手机号码<span id="x_close" title="关闭" style="float:right;font-size:18px;color:red;cursor:pointer;">×</span></h3>
		<font id="login_msg" color="#E03425"></font>
		<div><label for="buyer_mp">手机号码</label><input type="text" name="buyer_mp" id="buyer_mp"  size="18" maxlength="30" /><font color="red" id="u_msg"></font><br/></div>
		<div class="cookiechk">
			<input name="login791" id="sbt_login" type="button" class="buttom" value="提交" style="margin-left:130px;" onclick="sub();"/>	<input name="cancel" type="button" id="login_close" class="buttom" value="取消" />
		</div>
	</fieldset>
</form>
</div>
  <!--right-->
  <div class="member_right padbot45">
    <div class="position"><a href="${ctx }/member/Index.do">首页</a> &gt; <a href="${ctx }/member/center/Index.do">会员中心</a> &gt; 我的订单</div>
    <div class="membertab3">
      <html-el:form action="/center/Orders">
        <html-el:hidden property="method" styleId="method" value="list" />
        <html-el:hidden property="orderState" />
        <table width="100%" border="0" cellspacing="0" cellpadding="0"  class="member_form_table0">
          <tr>
            <td width="30%"><html-el:select property="ts" onchange="this.form.submit();" styleClass="input_txt"  style="width:120px;">
                <html-el:option value="0">全部订单</html-el:option>
                <html-el:option value="1">最近三个月订单</html-el:option>
                <html-el:option value="2">三个月以前订单</html-el:option>
              </html-el:select>
            </td>
            <td width="30%">订单编号
              <html-el:text property="trade_index" maxlength="20" styleClass="input_txt" style="width:160px;"></html-el:text>
            </td>
            <td width="40%"><input class="inputbtn" type="submit" name="Submit" value="查询" /></td>
          </tr>
        </table>
        <ul class="membertit3">
          <li 
          <c:if test="${af.map.orderState eq '1' }">class="curli"</c:if> ><a href="Orders.do?method=list&orderState=1&ts=<c:out value='${af.map.ts}'/>">进行中</a>
          </li>
          <c:if test="${touch eq 1 }">
          <li 
          <c:if test="${af.map.orderState eq '2' }">class="curli"</c:if> ><a href="Orders.do?method=list&orderState=2&ts=<c:out value='${af.map.ts}'/>">已完成</a>
          </li>
          <li 
          <c:if test="${af.map.orderState eq '3' }">class="curli"</c:if> ><a href="Orders.do?method=list&orderState=3&ts=<c:out value='${af.map.ts}'/>">已取消</a>
          </li>
          </c:if>
        </ul>
      </html-el:form>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="member_form_table2">
        <tr class="tr1">
          <td width="8%" nowrap="nowrap"  align="center">订单编号</td>
          <td width="8%" nowrap="nowrap" align="center">订单商品</td>
          <td width="8%" nowrap="nowrap" align="center">收货人</td>
          <td width="8%" nowrap="nowrap" align="center">订单金额</td>
          <td width="8%" nowrap="nowrap" align="center">订单优惠抵扣金额</td> 
          <td width="8%" nowrap="nowrap" align="center">应付金额</td>
          <td width="8%" nowrap="nowrap" align="center">下单时间</td>
          <td width="8%" nowrap="nowrap" align="center">支付类型</td>
          <td width="8%" nowrap="nowrap" align="center">订单状态</td>
          <td width="8%" nowrap="nowrap" align="center">操作</td>
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr height="60">
              <td align="center" nowrap="nowrap"><a onclick="view('${cur.id}')"><c:out value="${cur.trade_index}"/></a></td>
              <td align="center" nowrap="nowrap"><c:forEach items="${cur.pshowOrdeDetailsList}" var="cur2">
                  <c:set value="${fn:split(cur2.map.main_pic, ',') }" var="imgs" />
                  <c:if test="${cur2.pd_id eq 100018651}">
                   <a href="${ctx}/member/PdShowForTuan.do?method=list&goods_id=${cur2.pd_id}" target="_blank">
                  <c:forEach items="${imgs }" var="img1" begin="0" end="0"><img src="${ctx }/${img1}" alt="${cur2.map.pd_name }" width="51" height="50"/>
                  </c:forEach>
                  </a>
                  </c:if>
                  <c:if test="${cur2.pd_id eq 100028246}">
                   <a href="${ctx}/member/EcGroupBuy.do?method=view&group_id=262102" target="_blank"> 
                  <c:forEach items="${imgs }" var="img1" begin="0" end="0"><img src="${ctx }/${img1}" alt="${cur2.map.pd_name }" width="51" height="50"/>
                  </c:forEach>
                  </a>
                  </c:if>
                  <c:if test="${cur2.pd_id ne 100018651 and cur2.pd_id ne 100028246}">  
                  <a href="${ctx}/member/PdShow.do?goods_id=${cur2.pd_id}" target="_blank">
                  <c:forEach items="${imgs }" var="img1" begin="0" end="0"><img src="${ctx }/${img1}" alt="${cur2.map.pd_name }" width="51" height="50"/>
                  </c:forEach>
                  </a>
                  </c:if>
                  <br/>
                </c:forEach>
              </td>
              <td align="center" nowrap="nowrap"><c:out value="${cur.buyer_name}" /></td>
              <td align="center" nowrap="nowrap"><c:out value="￥${cur.total_price}" /></td>
              <td align="center" nowrap="nowrap"><c:out value="￥${cur.dedu_price}" /></td>
              <td align="center" nowrap="nowrap"><c:out value="￥${cur.pay_price}" /></td>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss" />
              </td>
              <td align="center" nowrap="nowrap"><c:if test="${cur.pay_way eq 0}">货到付款</c:if>
                <c:if test="${cur.pay_way eq 1}">银行汇款</c:if>
                <c:if test="${cur.pay_way eq 2}">支付宝</c:if>
                <c:if test="${cur.pay_way eq 3}">银联</c:if>
                <c:if test="${cur.pay_way eq 5}">民生e支付</c:if>
              </td>
              <td align="center" nowrap="nowrap"><a onclick="view('${cur.id}')"><c:if test="${cur.state eq -30 }">退货成功</c:if>
                <c:if test="${cur.state eq -20 }">处理失败</c:if>
                <c:if test="${cur.state eq -10 }">已取消</c:if>
                <c:if test="${cur.state eq 0 }"><c:if test="${cur.pay_way eq 0 or cur.pay_way eq 1}">待审核  </c:if> <c:if test="${cur.pay_way ne 0 and cur.pay_way ne 1 }">待付款  </c:if></c:if>
                <c:if test="${cur.state eq 5 }">待确认</c:if>
                <c:if test="${cur.state eq 10 }">已确认待处理</c:if>
                <c:if test="${cur.state eq 20 }">订单处理中</c:if>
                <c:if test="${cur.state eq 30 }">订单处理中</c:if>
                <c:if test="${cur.state eq 40 }">已发货</c:if>
                <c:if test="${cur.state eq 50 }">已换货</c:if>
                <c:if test="${cur.state eq 60 }">交易完成</c:if></a>
              </td>
              <td align="center" nowrap="nowrap" style="line-height:23px;">
              	<c:if test="${cur.state eq -30 }"><a onclick="view('${cur.id}')"><font color="#0022ff">订单详情</font></a></c:if>
                <c:if test="${cur.state eq -20 }"><a onclick="view('${cur.id}')"><font color="#0022ff">订单详情</font></a></c:if>
                <c:if test="${cur.state eq -10 }"><a onclick="view('${cur.id}')"><font color="#0022ff">订单详情</font></a></c:if>
                <c:if test="${cur.state eq 0 }"><a onclick="view('${cur.id}')"><font color="#0022ff">订单详情</font></a><br/>
                <c:if test="${cur.pay_way ne 0 and cur.pay_way ne 1}"> <a class="btn btn-4" href="<c:url value='/member/Payment.do?trade_index=${cur.trade_index}' />"><s></s>付款</a><br/></c:if>
                 <a href="#" id="t${cur.id }" data-mp="${cur.buyer_mp}" onclick="deleteInfo(this);" ><font color="#0022ff">取消订单</font></a></c:if>
          		<c:if test="${cur.state eq 5 }"><a onclick="view('${cur.id}')"><font color="#0022ff">订单详情</font></a> </c:if>
                <c:if test="${cur.state eq 10 }"><a onclick="view('${cur.id}')"><font color="#0022ff">订单详情</font></a> </c:if>
                <c:if test="${cur.state eq 20 }"><a onclick="view('${cur.id}')"><font color="#0022ff">订单详情</font></a> </c:if>
                <c:if test="${cur.state eq 30 }"><a onclick="view('${cur.id}')"><font color="#0022ff">订单详情</font></a></c:if>
                <c:if test="${cur.state eq 40 }"><a onclick="view('${cur.id}')"><font color="#0022ff">订单详情</font></a></c:if>
                <c:if test="${cur.state eq 50 }"><a onclick="view('${cur.id}')"><font color="#0022ff">订单详情</font></a></c:if>
                <c:if test="${cur.state eq 60 }"><a onclick="view('${cur.id}')"><font color="#0022ff">订单详情</font></a><br />
                <a class="btn btn-4" href="#" onclick="getState('${cur.id}');"><s></s>物流查询</a> 
                 </c:if>
              </td>
            </tr>
          </c:forEach>
          <c:if test="${empty entityList }">
            <tr height="60">
              <td align="center" nowrap="nowrap" colspan="10"> 暂无订单 </td>
            </tr>
          </c:if>
        </tbody>
      </table> 
      <c:if test="${af.map.pager.pageCount>1}">
        <form id="bottomPageForm" name="bottomPageForm" method="post" action="?">
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td height="40"><div style="text-align: right; padding-right: 5px;">
                  <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
                  <script type="text/javascript">
			                     var pager = new Pager(document.bottomPageForm, parseInt('${af.map.pager.recordCount}'), parseInt('${af.map.pager.pageSize}'), parseInt('${af.map.pager.currentPage}'));
			                     pager.addHiddenInputs("method", "list");
			                     pager.addHiddenInputs("ts", "<c:out value='${af.map.ts}'/>"); 	
			                     pager.addHiddenInputs("orderState", "<c:out value='${af.map.orderState}'/>"); 	
			                     document.write(pager.toString());
			                 </script>
                </div></td>
            </tr>
          </table>
        </form>
      </c:if>
    </div>
  </div>
  <div class="clear"></div>
</div>
<jsp:include page="/member/__inc/footer.jsp" flush="true" />  
<!-- six footer -->
</body>
<script type="text/javascript">//<![CDATA[ 
                                          
function deleteInfo(obj){
	//alert($(obj).data("mp")); 
	var	id ="&id="+ obj.id.replace("t",""); 
	if(confirm("您确定取消订单吗？")){
		location.href="${ctx}/member/center/Orders.do?method=delete"+id; 
	}
}

function view(id){
	var touch = '${touch}';
	if(touch == '1'){
		location.href="${ctx}/member/center/Orders.do?method=view&id="+id;
	}else{
		$("#login_div").fadeIn(500);   
		$("#login_close").click(function(){$("#login_div").fadeOut(500);});  
		$("#x_close").click(function(){$("#login_div").fadeOut(500);});

		$("#sbt_login").click(function(){ 
			var buyer_mp=$('#buyer_mp').val();
			$('#u_msg').html('');
			if(buyer_mp!=null){buyer_mp=buyer_mp.replace(/(^\s*)|(\s*$)/g, "");}
			if(buyer_mp==''){$('#u_msg').html('请输入手机号码');return false;} 
			this.disabled=true;
			this.value="正在验证。。。";
			$.ajax({
				type: "POST",
				url:"${ctx}/member/center/Orders.do",
				data: {"method":"ajaxSave","id":id,"buyer_mp":buyer_mp,"timestamp":new Date().getTime() },
				dataType: "text",sync: false,
				error: function (xhr, ajaxOptions, thrownError) {alert('网络异常，请稍后再试。。。');document.getElementById("sbt_login").value="提交";document.getElementById("sbt_login").disabled=false; 
				},success: function(result){
					if(result=='1'){ 
						$("#login_div").hide();
						location.href="${ctx}/member/center/Orders.do?method=view&id="+id; 
					}else{ 
						alert("手机号码不正确，您没有权限操作！");
						document.getElementById("sbt_login").value="提交";document.getElementById("sbt_login").disabled=false; 
					}
				}
			});
		});	
	}  
	
}

function getState(id){
	var returnValue = window.showModalDialog("Orders.do?method=sfList2&id="+id+"&azaz=" + Math.random(),window,"dialogWidth:700px;status:no;dialogHeight:300px");
	window.parent.resizeFrameHeight('mainFrame', 3);
}
 
//]]></script>
</html>
