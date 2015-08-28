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
    <html-el:form action="/spgl/EcJieSuanRebatesPay">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr style="height:25px;">
          <td style="height:25px;" nowrap="nowrap"> <strong class="fb">月份选择：</strong>
						<html-el:select property="year" styleId="year">
		          		  	<html-el:option value="2011">2011年</html-el:option>
		          		  	<html-el:option value="2012">2012年</html-el:option>
		          		  	<html-el:option value="2013">2013年</html-el:option>
		          		  	<html-el:option value="2014">2014年</html-el:option>
		          		  	<html-el:option value="2015">2015年</html-el:option>
		          		  	<html-el:option value="2016">2016年</html-el:option>
		          		  	<html-el:option value="2017">2017年</html-el:option>
		          		  	<html-el:option value="2018">2018年</html-el:option>
		          		  	<html-el:option value="2019">2019年</html-el:option>
		          		  	<html-el:option value="2020">2020年</html-el:option>
		          		  	<html-el:option value="2021">2021年</html-el:option>
	          		  	</html-el:select>
	          		  	<html-el:select property="month_start" styleId="month_start">
					      	<html-el:option value="1">1月</html-el:option>
					      	<html-el:option value="2">2月</html-el:option>
					      	<html-el:option value="3">3月</html-el:option>
					      	<html-el:option value="4">4月</html-el:option>
					      	<html-el:option value="5">5月</html-el:option>
					      	<html-el:option value="6">6月</html-el:option>
					      	<html-el:option value="7">7月</html-el:option>
					      	<html-el:option value="8">8月</html-el:option>
					      	<html-el:option value="9">9月</html-el:option>
					      	<html-el:option value="10">10月</html-el:option>
					      	<html-el:option value="11">11月</html-el:option>
					      	<html-el:option value="12">12月</html-el:option>
				      	</html-el:select>
				      	至
					    <html-el:select property="month_end" styleId="month_end">
					        <html-el:option value="1">1月</html-el:option>
					      	<html-el:option value="2">2月</html-el:option>
					      	<html-el:option value="3">3月</html-el:option>
					      	<html-el:option value="4">4月</html-el:option>
					      	<html-el:option value="5">5月</html-el:option>
					      	<html-el:option value="6">6月</html-el:option>
					      	<html-el:option value="7">7月</html-el:option>
					      	<html-el:option value="8">8月</html-el:option>
					      	<html-el:option value="9">9月</html-el:option>
					      	<html-el:option value="10">10月</html-el:option>
					      	<html-el:option value="11">11月</html-el:option>
					      	<html-el:option value="12">12月</html-el:option>
					    </html-el:select>
            &nbsp;<strong class="fb">发货分公司：</strong>
            <html-el:select property="dept_id" styleId="dept_id"  style="width:120px;">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${sybDeptInfoList}">
                <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
              </c:forEach>
            </html-el:select>&nbsp;&nbsp;<strong class="fb">订单来源</strong>
             <html-el:select property="order_from" styleId="order_from">
           					<html-el:option value="">请选择...</html-el:option>
					        <html-el:option value="1">工卡</html-el:option>
					      	<html-el:option value="2">触网</html-el:option>
					      	<html-el:option value="3">其他</html-el:option> 
					    </html-el:select>
            &nbsp;&nbsp; 
              <input class="but1" type="submit" id="t1" name="Submit" value="搜索" />
             <input type="button" value="Excel" id="export_excel" class="but_excel" style="margin-left: 10px;" />
              <br/>
            </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td> 
            <input style="align:left" name="button" type="button"  value="发放" class="btn_sync" onclick="confirmCheckAll(document.getElementById('listForm'));" />
            <input style="align:left" name="button" type="button"  value="短信" class="btn_message" onclick="deleteAll(document.getElementById('listForm'));" />
        </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1"> 
   <form id="listForm" name="listForm" method="post" action="EcJieSuanRebatesPay.do?method=reabates">
      <input type="hidden" name="method" id="method" value="reabates" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <div style="overflow-x:scroll;height:340px;" >      
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
          <tr class="tabtt1">
         <!-- 员工姓名  工卡号  开户银行 银行账号  银行户名   产品  销售价格  现款价 佣金  结算金额-->
            <td width="30" align="center"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>         
	        <td width="5%" nowrap="nowrap" align="center">序号</td>
            <td width="10%" nowrap="nowrap" align="center">员工姓名</td>
            <td width="10%" nowrap="nowrap" align="center">工卡号</td>
            <td width="10%" nowrap="nowrap" align="center">部门</td>
            <td width="10%" nowrap="nowrap" align="center"> 开户银行</td>
            <td width="10%" nowrap="nowrap" align="center"> 银行账号</td>
            <td width="10%" nowrap="nowrap" align="center">银行户名</td>            
            <td width="10%" nowrap="nowrap" align="center">交易流水号</td>
            <td width="10%" nowrap="nowrap" align="center">下单日期</td>
            <td width="10%" nowrap="nowrap" align="center">产品型号</td>
            <td width="10%" nowrap="nowrap" align="center">销售价格</td>
            <td width="10%" nowrap="nowrap" align="center">数量</td>
            <td width="15%" nowrap="nowrap" align="center">佣金</td>       
            <td width="10%" nowrap="nowrap" align="center">发货分公司</td>
            <td width="10%" nowrap="nowrap" align="center">到货分公司</td>
            <td width="10%" nowrap="nowrap" align="center">订单来源 </td>            
            <td width="15%" nowrap="nowrap" align="center">佣金发放状态</td>
            <td width="15%" nowrap="nowrap" align="center">短信发送状态</td>
            <td width="15%" nowrap="nowrap" align="center">发送短信</td>
          </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
             <td align="center" nowrap="nowrap"><input name="pks" type="checkbox" id="pks" value="${cur.bill_item_id}" /> </td>
	         <td height="28"  align="center">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
	         <td align="left"><c:out value="${cur.real_name}" /></td>   
	         <td align="left"><c:out value="${cur.card_no}" /></td> 
	          <td align="left" nowrap="nowrap"><c:out value="${cur.department}" /></td>        
	         <td align="left"><c:out value="${cur.bank_name}" /> </td>
	         <td align="center"><c:out value="${cur.bank_account}" />  </td> 
	         <td align="center"><c:out value="${cur.bank_account_name}" />  </td> 	         
	         <td align="left"><c:out value="${cur.trade_index}" /></td>
	         <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" /> </td> 
	         <td align="left"><c:out value="${cur.pd_sn}" />  </td> 
	         <td align="left"><fmt:formatNumber value="${cur.total_price}" pattern="￥0.00" /> </td> 	         
	         <td align="left"><c:out value="${cur.num}" /> </td> 
	         <td align="left"><fmt:formatNumber value="${cur.rebates}" pattern="￥0.00" /> </td>  
	         <td align="left"><c:out value="${cur.fh_dept_name}" /></td>         
	         <td align="left"><c:out value="${cur.dh_dept_name}" /></td>
	         <td align="left"><c:if test="${cur.order_from eq 1}">工卡</c:if><c:if test="${cur.order_from eq 2}">触网</c:if><c:if test="${cur.order_from eq 3}">其他</c:if></td> 
	         <td align="center"><c:if test="${cur.rebates_status eq 4}">已抵扣货款</c:if><c:if test="${cur.rebates_status eq 2}">未发放</c:if><c:if test="${cur.rebates_status eq 1}">已发放</c:if></td>
         	 <td align="left">
         	  <c:if test="${cur.is_send eq 1}">
         	  未发送/发送失败
         	  </c:if>
         	  <c:if test="${cur.is_send eq 0}">
         	  发送成功
         	  </c:if>
         	 </td>
         	 <td align="center">
         	 <c:if test="${cur.is_send eq 1}">
         	 <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'EcJieSuanRebatesPay.do','send' ,'id=${cur.bill_item_id}&' + $('#bottomPageForm').serialize())">发送短信</span>
         	 </c:if>
         	 <c:if test="${cur.is_send eq 0}">
         	 <span style="color:#CCC;" class="fblue">发送短信</span>
         	 </c:if>
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
            </tr>
          </c:forEach>
        </tbody>
      </table>
      </div>
      </form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="?">
      <input type="hidden" name="method" value="list" />
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}"); 
	            pager.addHiddenInputs("dept_id", "${af.map.dept_id}"); 
	            pager.addHiddenInputs("year", "${af.map.year}"); 
	            pager.addHiddenInputs("month_start", "${af.map.month_start}"); 
	            pager.addHiddenInputs("month_end", "${af.map.month_end}"); 
	            pager.addHiddenInputs("order_from", "${af.map.order_from}"); 	    
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
	 $("#export_excel").click(function(){
    	 this.value="正在提交...";
    	 this.disabled=true;
    	 this.form.action="${ctx}/manager/spgl/EcJieSuanRebatesPay.do?method=excel";
    	 this.form.submit();
    	 this.form.action="${ctx}/manager/spgl/EcJieSuanRebatesPay.do";
    	 this.value="Excel";
    	 this.disabled=false;
	 });
}); 

function deleteAll(form){
	var checkedCount = 0;
	if (!form.pks) {
		return;
	}
	if(!form.pks.length) {
		if (form.pks.checked == true) {
			checkedCount = 1;
		}
	}
	for (var i = 0; i < form.pks.length; i++) {
		if (form.pks[i].checked == true) {
			checkedCount++;
		}
	}
	if (checkedCount == 0) {
		//alert("\u8bf7\u81f3\u5c11\u9009\u62e9\u4e00\u4e2a\u5220\u9664\u9879\uff01");
		alert("请至少选中一项");
	} else {
		if(confirm("\u60a8\u786e\u5b9a\u7fa4\u53d1\u77ed\u4fe1\u5417\uff01")) {
			form.action="${ctx}/manager/spgl/EcJieSuanRebatesPay.do?method=sendBatch";
			form.submit();
		}
	}
	
}

</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
