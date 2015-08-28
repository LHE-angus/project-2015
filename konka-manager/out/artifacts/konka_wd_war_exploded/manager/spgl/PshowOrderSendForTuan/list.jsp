<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
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
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="login_div" id="login_div" style="display:none;z-index:10000;position:absolute;">
<form action="" method="post" name="apLogin" id="apLogin">
	<fieldset>		
		<h3>请输入您手机的验证码<span id="x_close" title="关闭" style="float:right;font-size:18px;color:red;cursor:pointer;">×</span></h3>
		<font id="login_msg" color="#E03425"></font>
		<div><label for="code">唯一码</label><input type="text" name="code" id="code"  size="18" maxlength="30" /><font color="red" id="u_msg"></font><br/></div>
		<div class="cookiechk">
			<input name="login791" id="sbt_login" type="button" class="buttom" value="提交" style="margin-left:130px;" onclick="sub();"/>	<input name="cancel" type="button" id="login_close" class="buttom" value="取消" />
		</div>
	</fieldset>
</form>
</div>
  <div class="rtabcont1">
    <html-el:form action="/spgl/PshowOrderSendForTuan">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr style="height:25px;">
          <td style="height:25px;" nowrap="nowrap">&nbsp;<strong class="fb">交易流水号：</strong>
            <html-el:text property="trade_index_like" styleId="trade_index_like" style="width:100px;" />
            &nbsp;<strong class="fb">下单人姓名：</strong>
            <html-el:text property="order_user_name_like" styleId="order_user_name_like" style="width:100px;" />
            &nbsp;<strong class="fb">订单类型：</strong>
            <html-el:select property="order_from" styleId="order_from" style="width:120px;">
              <html-el:option value="">请选择...</html-el:option>
              <html-el:option value="1">工卡</html-el:option>
           	  <html-el:option value="2">触网</html-el:option>
           	  <html-el:option value="3">会员</html-el:option>
            </html-el:select>&nbsp;</td></tr><tr><td>&nbsp;
             <strong class="fb">购买人姓名：</strong>
            <html-el:text property="buyer_name_like" styleId="buyer_name_like" style="width:100px;" />
            &nbsp;
            <strong class="fb">购买人手机：</strong>
            <html-el:text property="buyer_mp_like" styleId="buyer_mp_like" style="width:100px;" />
            &nbsp;
            &nbsp;<strong class="fb">当前处理的部门：</strong>
            <html-el:select property="dept_id" styleId="dept_id" style="width:120px;">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${sybDeptInfoList}">
                <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
              </c:forEach>
               <c:if test="${is_admin eq 0}">
               <html-el:option value="2108">电商大宗部</html-el:option>
               <html-el:option value="122">小家电事业部</html-el:option>
               <html-el:option value="2137">配件部</html-el:option>
               <html-el:option value="744">白电事业部</html-el:option>
               <html-el:option value="745">白电空调事业部</html-el:option>
               <html-el:option value="2108">电商大宗部</html-el:option>
               </c:if>
            </html-el:select>&nbsp;
              <input class="but1" type="submit" id="t1" name="Submit" value="搜索" />
               <input type="button" value="导出Excel" id="export_excel" class="but8" style="margin-left: 10px;float:right;" />
              <br/>
            </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1" style="overflow: auto;">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
          <tr class="tabtt1">
            <td width="5%" nowrap="nowrap" align="center">序号</td>
            <td nowrap="nowrap" align="center">交易流水号</td>
            <td nowrap="nowrap" width="10%" >订单状态</td>
            <td nowrap="nowrap" width="5%">当前处理的部门</td>
            <td nowrap="nowrap" width="7%">订单类型</td>
            <td width="10%" nowrap="nowrap" align="center">下单人姓名</td>
            <td width="10%" nowrap="nowrap" align="center">购买人姓名</td>
            <td width="20%" nowrap="nowrap" align="center">购买人地区</td> 
            <td width="10%" nowrap="nowrap" align="center">购买人手机</td>
            <td width="10%" nowrap="nowrap" align="center">订单商品</td>
             <td width="10%" nowrap="nowrap" align="center">应付金额</td>
            <td width="10%" nowrap="nowrap" align="center">支付方式</td>
            <td width="15%" nowrap="nowrap" align="center" >操作</td>
          </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
	         <td height="28"  align="center">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
	         <td align="left"><c:out value="${cur.trade_index}" /></td>
	         <td align="left">
	         <c:if test="${cur.state eq -30 }">已退货</c:if>
	         <c:if test="${cur.state eq -20 }">审核未通过</c:if>
	         <c:if test="${cur.state eq -10 }">已关闭</c:if>
	         <c:if test="${cur.state eq 0 }">已预订</c:if>
	         <c:if test="${cur.state eq 5 }">待确认</c:if> 
	         <c:if test="${cur.state eq 10 }">已确认</c:if>
	         <c:if test="${cur.state eq 20 }">审核通过</c:if>
	         <c:if test="${cur.state eq 30 }">下发处理</c:if>
	         <c:if test="${cur.state eq 40 }">商家发货</c:if>
	         <c:if test="${cur.state eq 50 }">客户已换货</c:if>
	         <c:if test="${cur.state eq 60 }">确认收货</c:if>
	         </td>
	         <td align="left" valign="middle"> <c:out value="${cur.map.dept_name}" /></td>
              <td align="left">
	          <c:if test="${cur.order_from eq 1 }">工卡</c:if>
	          <c:if test="${cur.order_from eq 2 and cur.map.is_sf ne true}">触网</c:if>
	          <c:if test="${cur.order_from eq 2 and cur.map.is_sf eq true }">顺丰</c:if>
	          </td>
              <td align="left"><c:out value="${cur.order_user_name}" /></td>
              <td align="left"><c:out value="${cur.buyer_name}" /></td>
              <td align="left"><c:out value="${cur.map.full_name}" /></td> 
              <td align="center" nowrap="nowrap"><c:out value="${cur.buyer_mp}" /></td>
              <td align="center" nowrap="nowrap"><c:forEach items="${cur.pshowOrdeDetailsList}" var="cur2">
                  ${cur2.map.pd_sn}*${cur2.num}
                  <br/>
                </c:forEach>
              </td>
              <td align="right" nowrap="nowrap">
                <span class="kz-price-12">
            	<fmt:formatNumber value="${cur.pay_price}" type="currency" />
            	</span>
              </td>
              <td align="center">
              	<c:if test="${cur.pay_way eq 0}">货到付款</c:if>
              <c:if test="${cur.pay_way eq 1}">银行汇款</c:if>
              <c:if test="${cur.pay_way eq 2}">支付宝</c:if>
              <c:if test="${cur.pay_way eq 3}">银联</c:if>
              <c:if test="${cur.pay_way eq 4}">财付通</c:if>
              <c:if test="${cur.pay_way eq 5}">民生银行</c:if>
              <c:if test="${cur.pay_way eq 8}">嘿客代收货款</c:if>
              <c:if test="${cur.pay_way eq 9}">线下处理</c:if>
              </td>
              <td align="center" nowrap="nowrap">
              <c:choose>
              
              <c:when test="${cur.state eq 5}">  
              <span style="cursor:pointer;" class="fblue" onclick="send('${cur.id}')">发货</span>
              <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'PshowOrderSendForTuan.do', 'showPrint','id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())" title="打印配货单">配货单</span>
              <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'PshowOrderSendForTuan.do', 'showPrint2','id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())" title="打印购物单">购物单</span>
              <!--  <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'PshowOrderSendForTuan.do', 'showPrint3','id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">打印顺丰电子账单</span>	 -->
              </c:when>
              <c:otherwise>
               <span style="color:#CCC;" class="fblue">发货</span>
              </c:otherwise>
              </c:choose>
              </td>
            </tr>
            <c:if test="${vs.last eq true}">
              <c:set var="i" value="${vs.count}" />
            </c:if>
          </c:forEach>
          <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
            <tr align="center">
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td> 
              <td>&nbsp;</td>    
            </tr>
          </c:forEach>
        </tbody>
      </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="PshowOrderSendForTuan.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("trade_index_like", "${af.map.trade_index_like}");
	            pager.addHiddenInputs("order_user_name_like", "${af.map.order_user_name_like}");
	            pager.addHiddenInputs("state", "${af.map.state}");
	            pager.addHiddenInputs("buyer_name_like", "${af.map.buyer_name_like}");
	            pager.addHiddenInputs("buyer_mp_like", "${af.map.buyer_mp_like}");
	            pager.addHiddenInputs("order_from", "${af.map.order_from}");
	            pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
	            document.write(pager.toString());
	      </script></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">
$(document).ready(function(){

	// 导出excel
    $("#export_excel").click(function(){
    	 this.value="正在提交...";
    	 this.disabled=true;
    	 this.form.action="${ctx}/manager/spgl/PshowOrderSendForTuan.do?method=excel";
    	 this.form.submit();
    	 this.form.action="${ctx}/manager/spgl/PshowOrderSendForTuan.do";
    	 this.value="导出Excel";
    	 this.disabled=false;
    });	

});

function send(id){
		$("#login_div").fadeIn(500);   
		$("#login_close").click(function(){$("#login_div").fadeOut(500);});  
		$("#x_close").click(function(){$("#login_div").fadeOut(500);});

		$("#sbt_login").click(function(){ 
			var code=$('#code').val();
			$('#u_msg').html('');
			if(code!=null){code=code.replace(/(^\s*)|(\s*$)/g, "");}
			if(code==''){$('#u_msg').html('请输入验证码');return false;} 
			this.disabled=true;
			this.value="正在验证。。。";
			$.ajax({
				type: "POST",
				url:"${ctx}/manager/spgl/PshowOrderSendForTuan.do",
				data: {"method":"ajaxSave","id":id,"mod_id":"${af.map.mod_id}","code":code,"timestamp":new Date().getTime() },
				dataType: "json",
				sync: false, 
				error: function (xhr, ajaxOptions, thrownError) {alert('网络异常，请稍后再试。。。');document.getElementById("sbt_login").value="提交";document.getElementById("sbt_login").disabled=false; 
				},success: function(result){
					if(result.status==1){  
						$("#login_div").hide();
						location.href="${ctx}/manager/spgl/PshowOrderSendForTuan.do?method=send&id="+id+"&v_code="+result.code+"&mod_id="+result.mod_id;  
					}else{ 
						alert("无效的验证码！");
						document.getElementById("sbt_login").value="提交";document.getElementById("sbt_login").disabled=false; 
					}
				}
			});
		});	
	
	
}


</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
