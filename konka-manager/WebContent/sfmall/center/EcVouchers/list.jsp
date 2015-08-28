<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康佳&顺丰网上直销商城</title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/sfmall/css/global1.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/sfmall/css/epp.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/sfmall/css/sfmall.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<!-- head start -->
<div class="topbox">
<jsp:include page="/sfmall/__inc/top.jsp" flush="true" />
<jsp:include page="/sfmall/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/sfmall/__inc/nav.jsp" flush="true" />
<!-- second start -->
<div class="maincont">
  <jsp:include page="/sfmall/__inc/_mleft.jsp" flush="true" />
  <!--right-->
  <div class="sfmall_right padbot45">
    <div class="position"><a href="${ctx }/sfmall/Index.do">首页</a> &gt; <a href="${ctx }/sfmall/center/Index.do">会员中心</a> &gt; 购物券兑换</div>
    <div class="sfmalltab3">
        <%@ include file="/commons/pages/messages.jsp" %>
         <p style="margin-top:15px;font-size:14px;"> 您当前的积分:<span style="color:#FF2200;font-weight:bold;">${totalScore}</span></p>
        <p style="margin-top:10px;color:#fc7200;font-weight:bold;">可兑换列表</p>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="sfmall_form_table1">
          <tr class="tr1">
          <td width="20%" nowrap="nowrap" align="center">兑换券名称</td>
          <td width="10%" nowrap="nowrap" align="center">金额(元)</td>
          <td width="10%" nowrap="nowrap" align="center">所需积分</td>
          <td width="10%" nowrap="nowrap" align="center">操作</td>
          </tr>
           <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr height="30">
              <td align="center" nowrap="nowrap">${cur.title}</td>
              <td align="center" nowrap="nowrap"><fmt:formatNumber type="number" value="${cur.price}" pattern="#0.00"/></td>
              <td align="center" nowrap="nowrap"> <fmt:formatNumber type="number" value="${cur.price*100}" maxFractionDigits="0"/></td>
              <td align="center" nowrap="nowrap"><a href="${ctx}/sfmall/center/EcVouchers.do?method=add&id=${cur.id}">兑换</a></td>
            </tr>
          </c:forEach>
          <c:if test="${empty entityList}">
            <tr height="60">
              <td align="center" nowrap="nowrap" colspan="5"> 暂无可兑换券 </td>
            </tr>
          </c:if>
        </tbody>
        </table>
        <html-el:form action="/center/EcVouchers" >
        <html-el:hidden property="method" styleId="method" value="save1" />
        <html-el:hidden property="id" value="${af.map.id}"/>
        <p style="margin-top:10px;color:#fc7200;font-weight:bold;">其他</p>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="sfmall_form_table1">
          <tr>
            <td width="80%" nowrap="nowrap">
               	兑换金额：<input id="dh_money" name="dh_money" maxlength="6" onfocus="javascript:setOnlyInt(this);"/>
               	所需积分：<input id="dh_jf" name="dh_jf" readonly="readonly"/> <span id="s1"></span>
            </td>
            <td align="center" width="20%">
               <input class="inputbtn" type="button" name="Submit4" id="btn_submit" value="兑换" />
            </td>
          </tr>
        </table>
        </html-el:form>
        <p style="margin-top:10px;color:#fc7200;font-weight:bold;">我的购物券</p>
         <table width="100%" border="0" cellpadding="0" cellspacing="0" class="sfmall_form_table1">
          <tr class="tr1">
          <td width="20%" nowrap="nowrap" align="center">兑换券名称</td>
          <td width="15%" nowrap="nowrap" align="center">金额(元)</td>
          <td width="15%" nowrap="nowrap" align="center">使用状态</td>
          </tr>
           <tbody>
          <c:forEach var="cur" items="${entityList1}" varStatus="vs">
            <tr height="30">
              <td align="center" nowrap="nowrap">${cur.title}</td>
              <td align="center" nowrap="nowrap"><fmt:formatNumber type="number" value="${cur.price}" pattern="#0.00"/></td>
           	  <td align="center" nowrap="nowrap">
           	  <c:if test="${cur.info_state eq 0}">未使用</c:if>
           	  <c:if test="${cur.info_state eq 1}">已使用</c:if>
           	  </td>	
            </tr>
          </c:forEach>
          <c:if test="${empty entityList1}">
            <tr height="60">
              <td align="center" nowrap="nowrap" colspan="5"> 暂无兑换券 </td>
            </tr>
          </c:if>
        </tbody>
        </table>
    </div>
  </div>
  <div class="clear"></div>
  </div>
<jsp:include page="/sfmall/__inc/footer.jsp" flush="true" />
<!-- six footer -->
</body>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var ff=0;
	$("#dh_money").change(function(){
		var dh_money=$("#dh_money").val();
		$("#dh_jf").val(dh_money*100);
		var dh_jf = $("#dh_jf").val();
		var tt=${totalScore};
		if(dh_jf>tt){
			ff=1;
			$("#s1").html("<font color='red'>积分不够</font>");
		}else if(dh_jf==0){
			ff=2;
			$("#s1").html("<font color='red'>兑换金额不能为0</font>");
		}else{
			ff=0;
			$("#s1").text('');	
		}
	});

	$("#btn_submit").click(function(){
		$("#dh_money").attr("dataType", "Require").attr("msg", "兑换金额不能为空");
		var isSubmit = Validator.Validate(this.form,3);
		if(ff==1){
			$("#dh_money").focus();
			alert('积分不够');
			return false;
		}else if(ff==2){
			$("#dh_money").focus();
			alert('兑换金额不能为0');
			return false;
		}else if($("#dh_money").val()==null){
			alert('兑换金额不能为空');
			return false;
		}
		if (isSubmit) {
			$("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
			this.form.submit();
		}
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

function setOnlyNum(obj) {
	$(obj).css("ime-mode", "disabled");
	$(obj).attr("t_value", "");
	$(obj).attr("o_value", "");
	$(obj).bind("dragenter",function(){
		return false;
	});
	$(obj).keypress(function (){
		if(!obj.value.match(/^[\+\-]?\d*?\.?\d*?$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))obj.o_value=obj.value;
	}).keyup(function (){
		if(!obj.value.match(/^[\+\-]?\d*?\.?\d*?$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))obj.o_value=obj.value;
	}).blur(function (){
		if(!obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\.\d+$/))obj.value=0+obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
		if(isNaN(obj.value)) obj.value = "";
	});
}
//]]></script>
</html>
