<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/global1.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/epp.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/member.css" />
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.red{ color:#F00;}
.bla{ color:#000; font-size:12px; font-weight:bold;}
.note {color:#777;margin-left:10px;}
span.required {color:#FF0000;font-weight:normal;background-color:#F4FAFF;}
-->
</style>
</head>
<body>
  <div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <!--right-->
  <div >
    <div >
    <p style="margin-top:15px;font-size:14px;"> 您当前的 可兑换积分:<span style="color:#FF2200;font-weight:bold;">${totalScore }</span>，
      	已兑换积分:<span style="color:#FF2200;font-weight:bold;">${xfScore }</span>，
       	其中付款积分<span style="color:#FF2200;font-weight:bold;">${payTotalScore }</span> ，
   	   	 奖励积分<span style="color:#FF2200;font-weight:bold;">${jlScore }</span></p>
      <p style="margin-top:15px;font-size:14px;">积分消费 、佣金兑换积分记录</p>
      <html-el:form action="/center/EcUserScoreRec">
        <html-el:hidden property="method" styleId="method" value="list" />
      </html-el:form>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="member_form_table1">
        <tr class="tabtt1">
          <td width="30%" nowrap="nowrap" align="center">时间</td>
          <td width="20%" nowrap="nowrap" align="center">获得积分</td>
          <td width="20%" nowrap="nowrap" align="center">消费积分</td>
          <td width="30%" nowrap="nowrap" align="center">详细说明</td>
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.opr_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
              <td align="center" nowrap="nowrap"><c:if test="${cur.opr_type eq 0}"> ${cur.score }</c:if>
                <c:if test="${cur.opr_type eq 1}">-</c:if>
              </td>
              <td align="center" nowrap="nowrap"><c:if test="${cur.opr_type eq 1}"> ${cur.score }</c:if>
                <c:if test="${cur.opr_type eq 0}">-</c:if>
              </td>
              <td align="left" nowrap="nowrap"><c:out value="${cur.note }"/></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
      
      <p style="margin-top:15px;font-size:14px;">购物获得积分记录</p>
       <table width="100%" border="0" cellspacing="0" cellpadding="0" class="member_form_table1">
        <tr class="tabtt1">
          <td width="20%" nowrap="nowrap" align="center">时间</td> 
          <td width="20%" nowrap="nowrap" align="center">商品名称</td>
          <td width="20%" nowrap="nowrap" align="center">付款积分</td>
          <td width="20%" nowrap="nowrap" align="center">奖励积分</td>
          <td width="20%" nowrap="nowrap" align="center">合计积分</td>
        </tr>
        <tbody>
          <c:forEach var="cur" items="${orderDetailList}" varStatus="vs">
           <tr>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.map.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
              <td align="center" nowrap="nowrap">${fnx:abbreviate(cur.pd_name, 2 * 16, '')}  </td>
              <td align="center" nowrap="nowrap" class="s1_${vs.count}"> <fmt:formatNumber value="${cur.pay_integral}" pattern="#0.00" /> </td>
              <td align="center" nowrap="nowrap" class="s2_${vs.count}">
              <c:if test="${af.map.dept_id eq 2110}">0</c:if>
              <c:if test="${af.map.dept_id ne 2110}">
              <fmt:formatNumber value="${cur.map.jl_jf}" pattern="#0.00" />   
              </c:if>
              </td>
              <td align="center" nowrap="nowrap" class="s3_${vs.count}"><fmt:formatNumber value="${cur.pay_integral+cur.map.jl_jf}" pattern="#0.00" /></td>
            </tr>
          </c:forEach>  
          <tr class="tabtt1">
          <td colspan="2" width="80%" nowrap="nowrap" align="center">合计</td>
          <td width="20%" nowrap="nowrap" align="center" id="t1"></td>
          <td width="20%" nowrap="nowrap" align="center" id="t2"></td>
          <td width="20%" nowrap="nowrap" align="center" id="t3"></td>
          </tr>
          </tbody>
          <tr>
          <td colspan="5" height="40"  align="center">
                <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
          </td>
        </tr>
        </table>
    </div>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
	$(document).ready(function(){
		
		
	});
//]]></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	var len = "${fn:length(orderDetailList)}";

	var last = 0;
	for (var i = 1; i < len+1; i++) {
		$(".s1_" + i).each(function(){
			var tt = $.trim($(this).text());
			if("" == tt||null == tt){
				tt = 0;
			}
			last += parseFloat(tt);
		});
	}
	$("#t1").text(last);

	var last2 = 0;
	for (var i = 1; i < len+1; i++) {
		$(".s2_" + i).each(function(){
			var tt = $.trim($(this).text());
			if("" == tt||null == tt){
				tt = 0;
			}
			last2 = last2 + parseFloat(tt);
		});
	}
	$("#t2").text(last2);

	$("#t3").text(parseFloat($("#t1").text())+parseFloat($("#t2").text())); 
	
    
		$("#btn_submit").click(function(){ 
			var isSubmit = Validator.Validate(this.form,3);
			if (isSubmit) {
				 $("#btn_submit").attr("value", "正在查询...").attr("disabled", "true");
				 this.form.submit();
			}
		});
		
});

 
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>