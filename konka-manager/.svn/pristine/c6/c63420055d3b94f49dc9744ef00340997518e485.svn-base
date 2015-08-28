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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
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
    <html-el:form action="/spgl/EcJieSuanSum">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="show_value" value="1" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr style="height:25px;">
          <td style="height:25px;" nowrap="nowrap"> <strong class="fb"><font color="red">*</font>销售日期：</strong>
          
           <html-el:text property="month_start" styleId="month_start"  size="10" maxlength="10" readonly="true" onclick="new Calendar(2000, 2030, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
              至
              <html-el:text property="month_end" styleId="month_end"  size="10" maxlength="10" readonly="true" onclick="new Calendar(2000, 2030, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
          
            	&nbsp;<strong class="fb">发货分公司：</strong>
            <html-el:select property="dept_id" styleId="dept_id" style="width:120px;">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${sybDeptInfoList}">
                <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
              </c:forEach>
            </html-el:select>&nbsp;<strong class="fb">订单来源</strong>
             <html-el:select property="order_from" styleId="order_from">
           					<html-el:option value="">请选择...</html-el:option>
					        <html-el:option value="1">工卡</html-el:option>
					      	<html-el:option value="2">触网</html-el:option>
					      	<html-el:option value="3">其他</html-el:option> 
					    </html-el:select>
            &nbsp;<strong class="fb">订单状态</strong>
             <html-el:select property="state" styleId="state">
           					<html-el:option value="">全部...</html-el:option>
					        <html-el:option value="40">发货中</html-el:option>
					      	<html-el:option value="60">已确认收货</html-el:option> 
			</html-el:select>
            <input class="but1" type="button" id="t1" name="Submit" value="搜索" />
            <c:if test="${af.map.show_value eq 1}">    
              <input type="button" value="Excel" id="export_excel" class="but_excel" style="margin-left: 10px;float:right;" />
            </c:if><br/>
            </td>
        </tr>
      </table>
    </html-el:form>
  </div> 
  <div class="rtabcont1">
  <div style="overflow-x:auto;">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
         <c:if test="${af.map.show_value ne 1}">
          <tr class="tabtt1">
           <td nowrap="nowrap" align="center"><font color="red">请选择销售开始日期结束日期,订单来源,订单状态等条件搜索 </font></td>
          </tr>
         </c:if>
        <c:if test="${af.map.show_value eq 1}">
          <tr class="tabtt1">
         <!-- 发货分公司 发货月份  产品  销售价格  现款价 佣金  税金  物流费用（单独导入）  佣金  支付宝佣金（收入*2%）  平台运营费用（销售价格-销售价格）*2%  结算金额-->
	        <td width="5%" nowrap="nowrap" align="center">序号</td> 
	        <td width="10%" nowrap="nowrap" align="center">机型</td>
	         <td width="10%" nowrap="nowrap" align="center">部门名称</td>
            <td width="10%" nowrap="nowrap" align="center">销售价格</td>
            <td width="10%" nowrap="nowrap" align="center">抵扣金额</td>
            <td width="10%" nowrap="nowrap" align="center">实付金额</td> 
            <td width="10%" nowrap="nowrap" align="center">数量</td>
            <td width="15%" nowrap="nowrap" align="center">佣金</td>
            <td width="10%" nowrap="nowrap" align="center">奖励积分</td> 
            <td width="10%" nowrap="nowrap" align="center">物流费用</td>
            <td width="10%" nowrap="nowrap" align="center">支付费用 </td>  
          </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
	         <td height="28"  align="center">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td> 
	         <td align="left"><c:out value="${cur.pd_sn}" /> </td>
	         <td align="left"><c:out value="${cur.department}" /> </td>  
	         <td align="left"><fmt:formatNumber value="${cur.total_price}" pattern="0.00" /> </td> 	
	         <td align="left"><fmt:formatNumber value="${cur.dedu_price}" pattern="0.00" /> </td>
	         <td align="left"><fmt:formatNumber value="${cur.pay_price}" pattern="0.00" /> </td> 	     
	         <td align="left"><c:out value="${cur.num}" /> </td> 
	         <td align="left"><fmt:formatNumber value="${cur.rebates}" pattern="0.00" /> </td> 
	         <td align="left"><fmt:formatNumber value="${cur.integral}" pattern="0" /> </td>  
	         <td align="left"><fmt:formatNumber value="${cur.price_wl}" pattern="0.00" /> </td> 
	         <td align="left"><fmt:formatNumber value="${cur.zffy}" pattern="0.00" /> </td>  
            </tr> 
          </c:forEach> 
          </c:if>
        </tbody>
      </table>
      </div>    
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var f=true;
	// 导出excel
    $("#export_excel").click(function(){
    	 if($("#month_start").val()==""){
        	 f=false;
        	 $("#month_start").focus();
         }else{
        	 f=true;
         }
         if($("#month_end").val()==""){
        	 f=false;
        	 $("#month_end").focus();
         }else{
        	 f=true;
         }
         if(f){
    	 this.value="正在提交...";
    	 this.disabled=true;
    	 this.form.action="${ctx}/manager/spgl/EcJieSuanSum.do?method=excel";
    	 this.form.submit();
    	 this.form.action="${ctx}/manager/spgl/EcJieSuanSum.do";
    	 this.value="Excel";
    	 this.disabled=false;
         }else{
        	alert('提示：销售开始日期和结束日期不能为空！');
         }
    });
	
    $("#t1").click(function(){
     if($("#month_start").val()==""){
    	 $("#month_start").focus();
    	 f=false;
     }else{
    	 f=true;
     }
     if($("#month_end").val()==""){
    	 $("#month_end").focus();
    	 f=false;
     }else{
    	 f=true;
     }
     if(f){
	   	 this.value="正在搜索...";
	   	 this.disabled=true;
	   	 this.form.action="${ctx}/manager/spgl/EcJieSuanSum.do";
	   	 this.form.submit(); 
	   	 this.disabled=false;
     }else{
    	 alert('提示：销售开始日期和结束日期不能为空！');
     }
   });

   
});
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
