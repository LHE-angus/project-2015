<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/wmj/css/db.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/styles/wmj/css/global.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
<html-el:form action="/KonkaR3JsBackMoneySail.do">
  <input type="hidden" id="user_id" name="user_id" value="${af.map.user_id}" />
  <input type="hidden" id="userpass" name="userpass" value="${af.map.userpass}" />
  <div class="yearbox">
    <div class="topt2"> 年度：
      <html-el:select property="year" styleId="year">
        <c:forEach items="${yearList}" var="cur">
          <html-el:option value="${cur}">${cur}年</html-el:option>
        </c:forEach>
      </html-el:select>
    </div>
  </div>
  <div class="mbox">
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td><div class="left_arrow" id="l_btn" style="cursor: pointer;"> <img src="${ctx}/styles/wmj/images/m_1.jpg" /></div>
          <div id="li">
            <div class="mbox1" id="li_1" style="display: none;">1月</div>
            <div class="mbox1" id="li_2" style="display: none;">2月</div>
            <div class="mbox1" id="li_3" style="display: none;">3月</div>
            <div class="mbox1" id="li_4" style="display: none;">4月</div>
            <div class="mbox1" id="li_5" style="display: none;">5月</div>
            <div class="mbox1" id="li_6" style="display: none;">6月</div>
            <div class="mbox1" id="li_7">7月</div>
            <div class="mbox1" id="li_8">8月</div>
            <div class="mbox1" id="li_9">9月</div>
            <div class="mbox1" id="li_10">10月</div>
            <div class="mbox1" id="li_11">11月</div>
            <div class="mbox1" id="li_12">12月</div>
          </div>
          <div class="right_arrow" id="r_btn" style="cursor: pointer;"> <img src="${ctx}/styles/wmj/images/m_2.jpg" /> </div></td>
      </tr>
    </table>
  </div>
</html-el:form>
<div class="yearbox">
  <div class="topt4">当月任务金额：
    <fmt:formatNumber value="${rw.map.rw_money}" pattern="###,##0.00" />
    <c:if test="${empty rw.map.rw_money}">0</c:if>
    万元 </div>
</div>
<table width="90%" border="0" align="center" cellpadding="0" align="center" cellspacing="0" bgcolor="#FFFFFF">
	 <tr>
      	<td align="right"><span style="font-size: 12px;color: red;">单位：万元</span></td>
      </tr>
<tr>
  <td><table width="86%" border="0" align="center" cellpadding="0"cellspacing="0">
      <tr style="height: 210px;">
        <td width="28%" valign="bottom"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td height="20"><div align="center"> <span class="dtextsmall1">零售额<br />
                  <fmt:formatNumber value="${sail.map.sale_all_price}" pattern="###,##0.00" />
                   </span> </div></td>
            </tr>
            <tr>
              <td height="${sail_length eq 0 ? 0 : sail_length}" bgcolor="#ff6600">&nbsp;</td>
            </tr>
          </table></td>
        <td>&nbsp;</td>
        <td width="28%" valign="bottom"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td height="20"><div align="center"> <span class="dtextsmall1">结算额<br />
                  <fmt:formatNumber value="${js.map.js_money}" pattern="###,##0.00" />
                  </span> </div></td>
            </tr>
            <tr>
              <td height="${js_length eq 0 ? 0 : js_length}" bgcolor="#0099ff">&nbsp;</td>
            </tr>
          </table></td>
        <td>&nbsp;</td>
        <td width="28%" valign="bottom"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td height="20"><div align="center"> <span class="dtextsmall1">回款额<br />
                  <fmt:formatNumber value="${hk.map.hk_money}" pattern="###,##0.00" />
                 </span> </div></td>
            </tr>
            <tr>
              <td height="${hk_length eq 0 ? 0 : hk_length}" bgcolor="#00af50">&nbsp;</td>
            </tr>
          </table></td>
      </tr>
    </table></td>
</tr>
</table>
<table width="75%" border="0" align="center" cellpadding="0" cellspacing="0" class="dtextsmall">
  <tr>
    <td>零售额 <span style="color: blue;">
      <fmt:formatNumber value="${sail.map.sale_all_price}" pattern="###,##0.00" />
      万元</span>,同比 <span  <c:if test="${sail.map.sail_price_tb ge 0}">style="color: green;"</c:if> <c:if test="${sail.map.sail_price_tb lt 0}">style="color: red;"</c:if>>
      <fmt:formatNumber value="${sail.map.sail_price_tb}" pattern="###,##0.00" />
      %</span>,环比 <span <c:if test="${sail.map.sail_price_hb ge 0}">style="color: green;"</c:if> <c:if test="${sail.map.sail_price_hb lt 0}">style="color: red;"</c:if>>
      <fmt:formatNumber value="${sail.map.sail_price_hb}" pattern="###,##0.00" />
      %</span>. </td>
  </tr>
  <tr>
    <td>零售量 <span style="color: blue">
      <fmt:formatNumber value="${sail.map.sale_num}" pattern="###,##0" />
      台</span>，同比 <span <c:if test="${sail.map.sail_num_tb ge 0}">style="color: green;"</c:if> <c:if test="${sail.map.sail_num_tb lt 0}">style="color: red;"</c:if>>
      <fmt:formatNumber value="${sail.map.sail_num_tb}" pattern="###,##0.00" />
      %</span>,
      环比 <span <c:if test="${sail.map.sail_num_hb ge 0}">style="color: green;"</c:if> <c:if test="${sail.map.sail_num_hb lt 0}">style="color: red;"</c:if>>
      <fmt:formatNumber value="${sail.map.sail_num_hb}" pattern="###,##0.00" />
      %</span>,平均单价 <span style="color: blue">
      <fmt:formatNumber value="${sail.map.sail_pj_price}" pattern="###,##0.00" />
      元/台</span>。<br /></td>
  </tr>
  <tr>
    <td>结算额 <span style="color: blue;">
      <fmt:formatNumber value="${js.map.js_money}" pattern="###,##0.00" />
      万元</span>,同比 <span <c:if test="${js.map.js_money_zb ge 0}">style="color: green;"</c:if> <c:if test="${js.map.js_money_zb lt 0}">style="color: red;"</c:if>>
      <fmt:formatNumber value="${js.map.js_money_zb}" pattern="###,##0.00" />
      %</span>,环比 <span <c:if test="${js.map.js_money_hb ge 0}">style="color: green;"</c:if> <c:if test="${js.map.js_money_hb lt 0}">style="color: red;"</c:if>>
      <fmt:formatNumber value="${js.map.js_money_hb}" pattern="###,##0.00" />
      %</span></td>
  </tr>
  <tr>
    <td> 结算量 <span style="color: blue">
      <fmt:formatNumber value="${js.map.js_num}" pattern="###,##0" />
      台</span>，
      同比 <span <c:if test="${js.map.js_num_tb ge 0}">style="color: green;"</c:if> <c:if test="${js.map.js_num_tb lt 0}">style="color: red;"</c:if>>
      <fmt:formatNumber value="${js.map.js_num_tb}" pattern="###,##0.00" />
      %</span>,环比 <span <c:if test="${js.map.js_num_hb ge 0}">style="color: green;"</c:if> <c:if test="${js.map.js_num_hb lt 0}">style="color: red;"</c:if>>
      <fmt:formatNumber value="${js.map.js_num_hb}" pattern="###,##0.00" />
      %</span>,平均单价 <span style="color: blue">
      <fmt:formatNumber value="${js.map.js_pj_price}" pattern="###,##0.00" />
      元/台</span>。<br /></td>
  </tr>
  <tr>
    <td>回款额 <span style="color: blue;">
      <fmt:formatNumber value="${hk.map.hk_money}" pattern="###,##0.00" />
      万元</span>，同比 <span <c:if test="${hk.map.tq_hk_tb ge 0}">style="color: green;"</c:if> <c:if test="${hk.map.tq_hk_tb lt 0}">style="color: red;"</c:if>>
      <fmt:formatNumber value="${hk.map.tq_hk_tb}" pattern="###,##0.00" />
      %</span>，环比 <span <c:if test="${hk.map.ly_hk_hb ge 0}">style="color: green;"</c:if> <c:if test="${hk.map.ly_hk_hb lt 0}">style="color: red;"</c:if>>
      <fmt:formatNumber value="${hk.map.ly_hk_hb}" pattern="###,##0.00" />
      %</span>。 </td>
  </tr>
</table>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[ 	
 $(document).ready(function(){
	 $("div[id^='li_']").each(function(){
		$(this).removeClass();
		$(this).addClass("mbox1");	
	 });

	var month = '${af.map.month}';
	$("#li_"+month).addClass("mbox2");

	showMonth(month);
	 
	 $("#l_btn").click(function(){			
			var pds=$('#li').children().length;
			var f=0;
			 for(var i=1;i<=pds;i++){
				var show= $('#li_'+i).css('display');	
				if(show!='none'){
					f=i;
				}
			 } 
			 if(f-3>0){
				 $('#li_'+(f-3)).css('display','block'); 
				 $('#li_'+f).css('display','none');  
			 } 			 
		}); 

	 $("#r_btn").click(function(){			
		 var pds=$('#li').children().length; 
			var f=0;
			 for(var i=1;i<=pds;i++){
				 var show= $('#li_'+i).css('display'); 
				if(show!='none'){
					f=i;
				}
			 } 
			 if(f-2 > 0 && f < pds){
			 $('#li_'+(f-2)).css('display','none'); 
			 $('#li_'+(f+1)).css('display','block'); 
			 }		 
		}); 
	 
	 $("div[id^='li_']").click(function(){
		 var year = $("#year").val();
		 var month = (this.id).split("li_")[1];
		 var user_id = $("#user_id").val();
		 var userpass = $("#userpass").val();
		 location.href = "${ctx}/webservice/KonkaR3JsBackMoneySail.do?user_id=" + user_id + "&userpass=" + userpass + "&year=" + year + "&month=" + month;
				
     });
     	
 });

 function showMonth(i){
	if( i<=2){
		for(var k = 1;k <= 12;k++){
			if(k <= 3){
				$("#li_"+k).css("display","block");
			}else{
				$("#li_"+k).css("display","none");
			}
		}
	} else if (i <= 12 && i > 10){
		for(var k = 1;k <= 12;k++){
			if( k >= 10 && k<=12){
				$("#li_"+ k).css("display","block");
			} else {
				$("#li_"+(k)).css("display","none");
			}
		}
	} else {
		for(var k = 1;k <= 12;k++){
			if(k <=(12-(12-1-i)) && k >= (i-1)){
				$("#li_"+ k).css("display","block");
			} else {
				$("#li_"+ k).css("display","none");
			}
	}
  }
}                                   
//]]></script>
</body>
</html>	