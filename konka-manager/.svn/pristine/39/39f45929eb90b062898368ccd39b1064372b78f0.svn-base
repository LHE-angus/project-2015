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
.float_div{
	position: absolute;
	border: solid 1px #d1e3f5;
	top:220px;
	text-align: center;
	background: #f5f4f4;
	left:40%;
	width:400px;
	padding-bottom: 20px;
	padding-top: 0px;
	display: none;
	z-index:1000;
}
.float_div div{margin-top: 0px;}
.close{ float: right;bottom: 0px;color:red;}
.but11{width:150px;height:30px;background-color: #FFCC00;border: 1px;text-align:center;cursor:pointer; -moz-border-radius: 2px; -webkit-border-radius: 2px; }

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
  <div class="rtabcont1">
    <html-el:form action="/spgl/PshowOrderLook">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
           <td width="100%" nowrap="nowrap">&nbsp;&nbsp;<strong class="fb">交易流水号：</strong>
            <html-el:text property="trade_index_like" styleId="trade_index_like" style="width:100px;" maxlength="20"/>&nbsp;
			<strong class="fb">运单号：</strong>
            <html-el:text property="logistic_sn_like" styleId="logistic_sn_like" style="width:100px;" maxlength="20"/>&nbsp;
           	<strong class="fb">下单人姓名：</strong>
            <html-el:text property="order_user_name_like" styleId="order_user_name_like" style="width:100px;" maxlength="20"/>&nbsp;
            <strong class="fb">订单状态：</strong>
            <html-el:select property="state" styleId="state" onchange="this.form.submit();">
           	 <html-el:option value="">请选择</html-el:option>
           	  <html-el:option value="-30">已退货</html-el:option>
           	  <html-el:option value="-20">审核未通过</html-el:option>
           	  <html-el:option value="-10">已关闭</html-el:option>
           	  <html-el:option value="0">已预订</html-el:option>
           	  <html-el:option value="5">待确认</html-el:option>
           	  <html-el:option value="10">已确认</html-el:option>
              <html-el:option value="20">审核通过</html-el:option>
              <html-el:option value="30">下发处理</html-el:option>
              <html-el:option value="40">商家发货</html-el:option>
              <html-el:option value="50">客户已换货</html-el:option>
              <html-el:option value="60">确认收货</html-el:option>    
            </html-el:select>&nbsp;
            
            </td>
       </tr>
       <tr> 
           <td width="100%" nowrap="nowrap">&nbsp;
            <strong class="fb">购买人姓名：</strong>
            <html-el:text property="buyer_name_like" styleId="buyer_name_like" style="width:100px;" maxlength="20"/>
            &nbsp;<strong class="fb">购买人手机：</strong>
            <html-el:text property="buyer_mp_like" styleId="buyer_mp_like" style="width:100px;" maxlength="20"/>
            &nbsp;<strong class="fb">下单日期：</strong>
        		<html-el:text property="add_time_start" styleId="add_time_start"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
				至
				<html-el:text property="add_time_end" styleId="add_time_end"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
            &nbsp;<strong class="fb">是否安装调试：</strong>
            <html-el:select property="deliver_way" styleId="deliver_way" style="width:120px;" onchange="this.form.submit();">
              <html-el:option value="">请选择...</html-el:option>
              <html-el:option value="0">否</html-el:option>
              <html-el:option value="1">是</html-el:option>
            </html-el:select>&nbsp;
            </td>
        </tr>
        <tr><td width="100%" nowrap="nowrap">
            &nbsp;&nbsp;<strong class="fb">订单类型：</strong>
            <html-el:select property="order_type" styleId="order_type" style="width:120px;" onchange="this.form.submit();">
              <html-el:option value="">请选择...</html-el:option>
              <html-el:option value="0">普通订单</html-el:option>
              <html-el:option value="1">处理机</html-el:option>
              <html-el:option value="2">零售</html-el:option>
              <html-el:option value="3">碎屏重发</html-el:option>
              <html-el:option value="4">退货</html-el:option>
           	  <html-el:option value="9">大宗采购</html-el:option>
            </html-el:select>&nbsp; 
              <strong class="fb">退换货：</strong>
            <html-el:select property="tuihuo" styleId="tuihuo" style="width:80px;" onchange="this.form.submit();">
              <html-el:option value="">请选择...</html-el:option>
              <html-el:option value="0">换货</html-el:option>
           	  <html-el:option value="1">退货</html-el:option>
            </html-el:select>
            &nbsp;
            <strong class="fb">工卡号：</strong>
            <html-el:text property="card_no_like" styleId="card_no_like" style="width:100px;" maxlength="20"/>
            &nbsp;
              <strong class="fb">产品品类：</strong>
            <html-el:select property="prod_type" styleId="prod_type" style="width:80px;" onchange="this.form.submit();">
              <html-el:option value="">请选择...</html-el:option>
              <html-el:option value="0">彩电</html-el:option>
            	<html-el:option value="3">小家电</html-el:option>
            	<html-el:option value="4">冰箱</html-el:option>
            	<html-el:option value="5">洗衣机</html-el:option>
            	<html-el:option value="6">空调</html-el:option>
            	<html-el:option value="7">手机</html-el:option>
            	<html-el:option value="8">团购</html-el:option> 
            	<html-el:option value="9">戴尔</html-el:option>
            	<html-el:option value="10">配件</html-el:option>
            	<html-el:option value="21">食品饮料</html-el:option>
            </html-el:select>
            &nbsp;
            <strong class="fb">产品名称：</strong>
            <html-el:text property="pd_name_like" styleId="pd_name_like" style="width:100px;" maxlength="20"/>
        </td></tr>
        <tr><td width="100%" nowrap="nowrap">
        	&nbsp;&nbsp;<strong class="fb">发货分公司：</strong>
            <html-el:select property="dept_id" styleId="dept_id" style="width:120px;" onchange="this.form.submit();">
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
               <html-el:option value="2274">戴尔事业部</html-el:option>
               </c:if>
            </html-el:select>&nbsp;
			<strong class="fb">订单来源：</strong>
            <html-el:select property="order_from" styleId="order_from" style="width:120px;" onchange="this.form.submit();">
              <html-el:option value="">请选择...</html-el:option>
              <html-el:option value="1">工卡</html-el:option>
           	  <html-el:option value="2">触网</html-el:option>
           	  <html-el:option value="3">顺丰</html-el:option>
           	  <html-el:option value="5">会员</html-el:option>
            </html-el:select>&nbsp;
            <strong class="fb">支付方式：</strong>
            <html-el:select property="pay_way" styleId="pay_way" style="width:120px;" onchange="this.form.submit();">
              <html-el:option value="">请选择...</html-el:option>
              <html-el:option value="0">货到付款</html-el:option>
           	  <html-el:option value="1">银行汇款</html-el:option>
           	  <html-el:option value="2">支付宝</html-el:option>
           	  <html-el:option value="3">银联</html-el:option>
           	  <html-el:option value="4">财付通</html-el:option>
           	  <html-el:option value="5">民生银行</html-el:option>
           	  <html-el:option value="8">嘿客代收货款</html-el:option>
           	  <html-el:option value="9">线下处理</html-el:option>
            </html-el:select>&nbsp;
            <strong class="fb">是否自提：</strong>
            <html-el:select property="is_self" styleId="is_self" style="width:120px;" onchange="this.form.submit();">
              <html-el:option value="">请选择...</html-el:option>
              <html-el:option value="0">否</html-el:option>
           	  <html-el:option value="1">是</html-el:option>
            </html-el:select>&nbsp;
              
        </td></tr>
        <tr>
        <td width="100%" nowrap="nowrap">
        &nbsp;&nbsp;<strong class="fb">二次配送：</strong> 
            <html-el:select property="is_ps" styleId="is_ps" onchange="this.form.submit();">
              <html-el:option value="">请选择...</html-el:option>
              <html-el:option value="0">否</html-el:option>
           	  <html-el:option value="1">是</html-el:option>
            </html-el:select>
            &nbsp;<strong class="fb">是否安装调试：</strong>
            <html-el:select property="deliver_way" styleId="deliver_way" style="width:120px;" onchange="this.form.submit();">
              <html-el:option value="">请选择...</html-el:option>
              <html-el:option value="0">否</html-el:option>
              <html-el:option value="1">是</html-el:option>
            </html-el:select>&nbsp;
            <strong class="fb">嘿客订单号：</strong>
            <html-el:text property="sf_order_id_like" styleId="sf_order_id_like" style="width:100px;"  maxlength="20"/>&nbsp;
        <input class="but1" type="submit" id="t1" name="Submit" value="搜索" />
        <input type="button" value="Excel" id="export_excel" class="but_excel" style="margin-left: 10px;" />
        </td>
        </tr> 
      </table>
    </html-el:form>
  </div>
  <c:if test="${is_admin eq 0}">
  <div class="rtabcont2" id="r3moneyTb">
   <%@ include file="/commons/pages/messages.jsp"%>
  	<input type="button" style="cursor: pointer;" class="but11" id="syncBtn" value="物流费用更新"></input>
    <input type="button" style="cursor: pointer;" class="but11" id="syncBtn2" value="订单状态更新"></input>
 	<font color="#696969">由于客户下单到顺丰发货之间有时间差，所以不能马上获取订单物流费用，建议一天更新一次!</font>
  </div>
  <div class="rtabcont2" id="r3moneyTb" >
  	<input type="button" style="cursor: pointer;" class="but11" id="syncBtn6" value="同步顺丰嘿客订单" onclick="getTime()"></input>
  	<font color="#696969">点击进入同步页面，当同步开始后，请不要关闭同步窗口，同步完会自动关闭并刷新页面!</font>
  </div>
  <div class="rtabcont2" id="r3moneyTb" >
  	<input type="button" style="cursor: pointer;" class="but11" id="syncBtn7" value="初始化顺丰嘿客物流公司" ></input>
  	<font color="#696969">初始化顺丰嘿客物流公司，目前只需要初始化一次即可!</font>
  </div>
  </c:if>
  <div class="rtabcont1" style="font-weight:700;color:#F00;">
  	您一共选择了个${totolCount}订单，合计商品${total_num}件，合计金额${total_p}元
  </div>
  <div class="rtabcont1" style="overflow-x:auto;">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
          <tr class="tabtt1">
             <td width="5%" nowrap="nowrap" align="center">序号</td>
            <td nowrap="nowrap" align="center">交易流水号</td>
            <td nowrap="nowrap" width="10%" >&nbsp;&nbsp;订单状态&nbsp;&nbsp;</td>
            <td nowrap="nowrap" width="7%">当前处理部门</td>
            <td nowrap="nowrap" width="7%">发货分公司</td>
            <td nowrap="nowrap" width="7%">订单来源</td>
            <td width="10%" nowrap="nowrap" align="center">下单人姓名</td>
             <td width="10%" nowrap="nowrap" align="center">下单人工卡号</td>
            <td width="10%" nowrap="nowrap" align="center">购买人姓名</td>
            <td width="160" nowrap="nowrap" align="center">购买人地区</td>
            <td width="10%" nowrap="nowrap" align="center">支付单号</td>
            <td width="10%" nowrap="nowrap" align="center">购买人手机</td>
            <td width="10%" nowrap="nowrap" align="center">支付方式</td>
            <td width="10%" nowrap="nowrap" align="center">运单号</td>
            <td width="10%" nowrap="nowrap" align="center">嘿客单号</td>
            <td width="10%" nowrap="nowrap" align="center">物流费用</td>
            <td width="10%" nowrap="nowrap" align="center">数量</td>
            <td width="10%" nowrap="nowrap" align="center">应付金额</td>
            <td width="10%" nowrap="nowrap" align="center"> &nbsp;下单时间&nbsp; </td>
            <td width="10%" nowrap="nowrap" align="center">二次配送</td>
            <td width="10%" nowrap="nowrap" align="center">是否自提</td>
            <td width="10%" nowrap="nowrap" align="center">安装调试</td>
            <td width="10%" nowrap="nowrap" align="center">订单商品</td>
            <td width="10%" nowrap="nowrap" align="center">延保服务</td>
            <td width="15%" nowrap="nowrap" align="center" >操作</td>
          </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
	         <td height="28"  align="center">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
	         <td align="right"><c:out value="${cur.trade_index}" /></td>
	         <td align="center">
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
	         <c:if test="${cur.pay_way eq 9}"><br/>（线下处理）</c:if>
	         </td>
	         <td align="left" valign="middle"> <c:out value="${cur.map.dept_name}" /></td>
	          <td align="left" valign="middle"> <c:out value="${cur.map.dept_name}" /></td>
	          <td align="left">
	          <c:if test="${cur.order_from eq 1 }">工卡</c:if>
	          <c:if test="${cur.order_from eq 2 and cur.map.is_sf ne true}">触网</c:if>
	          <c:if test="${cur.order_from eq 2 and cur.map.is_sf eq true }">顺丰</c:if>
	          <c:if test="${cur.order_from eq 5 }">会员</c:if>
	          </td>
              <td align="left"><c:out value="${cur.order_user_name}" /></td>
              <td align="left"><c:out value="${cur.map.card_no}" /></td>
              <td align="left"><c:out value="${cur.buyer_name}" /></td>
              <td align="left"><c:out value="${cur.map.full_name}" /></td>
              <td align="center" nowrap="nowrap"><c:out value="${cur.trade_no}" /></td>
              <td align="right" nowrap="nowrap"><c:out value="${cur.buyer_mp}" /></td>
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
              <td align="left"><font color="red"><c:out value="${cur.map.log_sn}" /></font></td>
               <td align="left"><font color="red"><c:out value="${cur.map.sfhk_order_id}" /></font></td>
              <td align="right" nowrap="nowrap">
                <span class="kz-price-12">
            	<fmt:formatNumber value="${cur.map.logistic_price}" type="currency" />
            	</span>
              </td>
               <td align="right" nowrap="nowrap"><c:out value="${cur.map.total_num}" /></td>
              <td align="right" nowrap="nowrap">
                <span class="kz-price-12">
            	<fmt:formatNumber value="${cur.pay_price}" type="currency" />
            	</span>
              </td>
              <td align="left" ><fmt:formatDate value="${cur.add_date}" pattern="yy-MM-dd HH:mm:ss" /></td>
              <td align="left" nowrap="nowrap"> <c:if test="${cur.is_ps eq 0}">否</c:if>
              <c:if test="${cur.is_ps eq 1}">是</c:if>
              </td>
              <td align="left" nowrap="nowrap"> <c:if test="${cur.is_self eq 0}">否</c:if>
              <c:if test="${cur.is_self eq 1}">是</c:if>
              </td>
              <td align="left" nowrap="nowrap"> <c:if test="${cur.deliver_way ne 1}">否</c:if>
              <c:if test="${cur.deliver_way eq 1}">是</c:if>
              </td>
              <td align="center" nowrap="nowrap"><c:forEach items="${cur.pshowOrdeDetailsList}" var="cur2">
                  ${cur2.map.pd_sn}*${cur2.num}
                  <br/>
                </c:forEach>
              </td>
              <td align="center" nowrap="nowrap"><c:out value="${cur.map.pd_fuwu}" /></td>
              <td align="center" nowrap="nowrap">
              <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'PshowOrderLook.do','view' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">查看</span>
             <c:if test="${not empty cur.map.in_sf}">
             <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'PshowOrderLook.do', 'showPrint3','id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())" title="打印顺丰电子运单">顺丰运单</span>
             </c:if>
             <c:if test="${af.map.user_id eq 23794 or af.map.user_id eq 42843 or af.map.user_id eq 45437 or af.map.user_id eq 63255 or af.map.user_id eq 40724 }">
             <c:if test="${cur.order_user_id eq 128008 and cur.order_from eq 2 and cur.map.is_out eq 1}">  
             </c:if>
             </c:if>
             	<input type="button" style="cursor: pointer;" class="but11" id="syncBtn_${cur.id}" value="顺丰嘿客订单出库" onclick="out_stock('${cur.id}')" ></input>  
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
    </div>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="PshowOrderLook.do">
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
	            pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
	            pager.addHiddenInputs("trade_no_like", "${af.map.trade_no_like}");
	            pager.addHiddenInputs("order_from", "${af.map.order_from}");
	            pager.addHiddenInputs("pay_way", "${af.map.pay_way}");
	            pager.addHiddenInputs("add_time_start", "${af.map.add_time_start}");
				pager.addHiddenInputs("add_time_end", "${af.map.add_time_end}");
				pager.addHiddenInputs("is_ps", "${af.map.is_ps}");
				pager.addHiddenInputs("tuihuo", "${af.map.tuihuo}");
				pager.addHiddenInputs("pd_name_like", "${af.map.pd_name_like}");			
				pager.addHiddenInputs("order_type", "${af.map.order_type}");
				pager.addHiddenInputs("card_no_like", "${af.map.card_no_like}");
				pager.addHiddenInputs("prod_type", "${af.map.prod_type}");
				pager.addHiddenInputs("is_self", "${af.map.is_self}");
				pager.addHiddenInputs("deliver_way", "${af.map.deliver_way}");
				pager.addHiddenInputs("logistic_sn_like", "${af.map.logistic_sn_like}");
				pager.addHiddenInputs("sf_order_id_like", "${af.map.sf_order_id_like}");
	            document.write(pager.toString());
	      </script></td>
        </tr>
      </table>
    </form>
 
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript">
$(document).ready(function(){ 
	
	$("#syncBtn").click(function(){
		$("#syncBtn").attr("value", "正在更新...请稍后").attr("disabled", "true");
		$.ajax({
			type: "POST",
			url: "PshowOrderLook.do",
			data: "method=orderFy",
			dataType: "text",
			error: function(request, settings) {
				
			},
			success: function(oper) {
				alert(oper);
				$("#syncBtn").removeAttr("disabled").attr("value","物流费用更新");
			}
		});

	});

	$("#syncBtn2").click(function(){
		$("#syncBtn2").attr("value", "正在更新...请稍后").attr("disabled", "true");
		$.ajax({
			type: "POST",
			url: "PshowOrderLook.do",
			data: "method=updateorderState",
			dataType: "text",
			error: function(request, settings) {
				
			},
			success: function(oper) {
				alert(oper);
				$("#syncBtn2").removeAttr("disabled").attr("value","订单状态更新");
			}
		});

	});

	$("#syncBtn7").click(function(){

		var pass = window.prompt("请输入同步密码!","");
		if(pass ==null || pass==""){
			return ;
		}
		
		if(pass !='753'){
			alert("sorry , 同步密码不对!");
			return ;
		}
		
		$("#syncBtn7").attr("value", "正在初始化...请稍后").attr("disabled", "true");
		$.ajax({
			type: "POST",
			url: "PshowOrderLook.do",
			data: "method=syncSfCompany",
			dataType: "text",
			error: function(request, settings) {
				
			},
			success: function(oper) {
				alert(oper);
				$("#syncBtn7").removeAttr("disabled").attr("value","初始化顺丰嘿客物流公司");
			}
		});

	});

	

	// 导出excel
    $("#export_excel").click(function(){
    	 this.value="正在提交...";
    	 this.disabled=true;
    	 this.form.action="${ctx}/manager/spgl/PshowOrderLook.do?method=excel";
    	 this.form.submit();
    	 this.form.action="${ctx}/manager/spgl/PshowOrderLook.do";
    	 this.value="导出Excel";
    	 this.disabled=false;
    });

	
});

function getTime(){
	var pass = window.prompt("请输入同步密码!","");
	if(pass ==null || pass==""){
		return ;
	}
	
	if(pass !='753'){
		alert("sorry , 同步密码不对!");
		return ;
	}
	
	var returnValue = window.showModalDialog("PshowOrderLook.do?method=selectTime&azaz=" + Math.random(),window,"dialogWidth:396px;status:no;dialogHeight:368px");  
	window.parent.resizeFrameHeight('mainFrame', 3); 
}

function out_stock(id){
	$("#syncBtn_"+id).attr("value", "正在出库中...请稍后").attr("disabled", "true");
	$.ajax({
		type: "POST",
		url: "PshowOrderLook.do",
		data: "method=outstock&id="+id,
		dataType: "text",
		error: function(request, settings) {
			
		},
		success: function(oper) {
			alert(oper);
			$("#syncBtn_"+id).removeAttr("disabled").attr("value","顺丰嘿客订单出库");
			//location.reload();   刷新当前页面
		}
	}); 
}


</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
