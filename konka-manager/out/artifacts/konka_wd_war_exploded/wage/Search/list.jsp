<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>客户端登录——康佳渠道管理系统</title>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/wage/default.css" />
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<style type="text/css">
input,textarea,select,file,button{font-family:Microsoft Yahei;font-size:12px;}
.mt a:LINK {color:#FFFFFF;text-decoration:none;background:#FF0000;padding:1px 2px;}
</style>
</head>
<body>
<html-el:form action="Search.do" method="post">
	<input type="hidden" name="method" value="searchResult" />
	<input type="hidden" name="pwdId" value="${af.map.pwdId}" />
	<input type="hidden" name="id_card_no" value="${af.map.id_card_no}" />
	<table width="100%" border="0" cellpadding="5" cellspacing="0" class="loginTab" style="width:100%;"><!--  style="min-width:480px;" -->
	  	<tr>
	  		<th align="left" colspan="3">
	  			康佳工资查询系统， 欢迎您：${wageUserInfo.real_name}（${wageUserInfo.id_card_no}）！<br/>
	  			密保邮箱：<a href="${ctx}/wage/Email.do?method=edit&pwdId=${af.map.pwdId}&id_card_no=${af.map.id_card_no}" title="点击修改您的密保邮箱">${wageUserInfo.email}</a>【<a href="${ctx}/wage/Password.do?method=edit&pwdId=${af.map.pwdId}&id_card_no=${af.map.id_card_no}">修改密码</a>】【<a href="javascript:if(confirm('是否退出？')){location.href='${ctx}/wage/Login.do?method=logout';} else {return false;}">退出</a>】
	  		</th>
	  	</tr>
	  	<tr>
		    <td align="left" nowrap="nowrap" class="mt"><a href="javascript:loading();location.href='${ctx}/wage/Search.do?method=searchResult&pwdId=${af.map.pwdId}&id_card_no=${af.map.id_card_no}&year=${af.map.lyear}&month=${af.map.lmonth}';">上月</a></td>
		    <td align="center">
		    	年份：
		    	<html-el:select property="year" styleId="year">
		    		<html-el:option value="${localyear}">${localyear}</html-el:option>
		    		<html-el:option value="${localyear - 1}">${localyear - 1}</html-el:option>
		    		<html-el:option value="${localyear - 2}">${localyear - 2}</html-el:option>
		    		<html-el:option value="${localyear - 3}">${localyear - 3}</html-el:option>
		    		<html-el:option value="${localyear - 4}">${localyear - 4}</html-el:option>
		    		<html-el:option value="${localyear - 5}">${localyear - 5}</html-el:option>
		    		<html-el:option value="${localyear - 6}">${localyear - 6}</html-el:option>
		    		<html-el:option value="${localyear - 7}">${localyear - 7}</html-el:option>
		    		<html-el:option value="${localyear - 8}">${localyear - 8}</html-el:option>
		    	</html-el:select>
		    	月份：
		    	<html-el:select property="month" styleId="month">
				    <html-el:option value="1">1</html-el:option>
				    <html-el:option value="2">2</html-el:option>
				    <html-el:option value="3">3</html-el:option>
				    <html-el:option value="4">4</html-el:option>
				    <html-el:option value="5">5</html-el:option>
				    <html-el:option value="6">6</html-el:option>
				    <html-el:option value="7">7</html-el:option>
				    <html-el:option value="8">8</html-el:option>
				    <html-el:option value="9">9</html-el:option>
				    <html-el:option value="10">10</html-el:option>
				    <html-el:option value="11">11</html-el:option>
				    <html-el:option value="12">12</html-el:option>
		    	</html-el:select>
		    </td>
		    <td align="right" nowrap="nowrap" class="mt"><a href="javascript:loading();location.href='${ctx}/wage/Search.do?method=searchResult&pwdId=${af.map.pwdId}&id_card_no=${af.map.id_card_no}&year=${af.map.nyear}&month=${af.map.nmonth}';">下月</a></td>
	  	</tr>
	  	<tr>
	  		<td colspan="3" align="center"><input type="button" name="search" id="btn_submit" value=" 查 询 " /></td>
	  	</tr>
	</table>
	<c:if test="${not empty af.map.id}">
		<table width="100%" border="0" cellpadding="5" cellspacing="0" class="loginTab" style="width:100%;">
		  	<tr>
		    	<th scope="col" colspan="2">查询结果</th>
		    </tr>
		  	<tr>
			    <td width="50%" align="right">年月：</td>
			    <td>${af.map.y}年${af.map.m}月</td>
		  	</tr>
		  	<tr>
			    <td align="right">姓名：</td>
			    <td>${af.map.real_name}</td>
		  	</tr>
		  	<tr>
			    <td align="right">所在分公司：</td>
			    <td>${af.map.fgs_dept_name}</td>
		  	</tr>
		  	<tr>
			    <td align="right">入职日期：</td>
			    <td><fmt:formatDate value="${af.map.employ_date}" pattern="yyyy-MM-dd"/></td>
		  	</tr>
		  	<tr>
			    <td align="right">入职年限：</td>
			    <td>${af.map.employ_years}</td>
		  	</tr>
		  	<tr>
			    <td align="right">月度排名：</td>
			    <td>${af.map.month_rank}</td>
		  	</tr>
		  	<tr>
			    <td align="right">标准工资：</td>
			    <td><c:if test="${not empty af.map.w_std}">${af.map.w_std}元</c:if></td>
		  	</tr>
		  	<tr>
			    <td align="right">基本工资：</td>
			    <td><c:if test="${not empty af.map.w_base}">${af.map.w_base}元</c:if></td>
		  	</tr>
		  	<tr>
			    <td align="right">工龄工资：</td>
			    <td><c:if test="${not empty af.map.employ_year_w}">${af.map.employ_year_w}元</c:if></td>
		  	</tr>
		  	<tr>
			    <td align="right">浮动工资：</td>
			    <td><c:if test="${not empty af.map.w_float}">${af.map.w_float}元</c:if></td>
		  	</tr>
		  	<tr>
			    <td align="right">补助津贴：</td>
			    <td><c:if test="${not empty af.map.w_allowance}">${af.map.w_allowance}元</c:if></td>
		  	</tr>
		  	<tr>
			    <td align="right">提成奖金：</td>
			    <td><c:if test="${not empty af.map.w_rewards}">${af.map.w_rewards}元</c:if></td>
		  	</tr>
		  	<tr>
			    <td align="right">加班费：</td>
			    <td><c:if test="${not empty af.map.w_overtime}">${af.map.w_overtime}元</c:if></td>
		  	</tr>
		  	<tr>
			    <td align="right">奖励金额：</td>
			    <td><c:if test="${not empty af.map.w_encourage}">${af.map.w_encourage}元</c:if></td>
		  	</tr>
		  	<tr>
			    <td align="right" ><font color="red">扣款金额：</font></td>
			    <td><font color="red"><c:if test="${not empty af.map.w_deduct}">${af.map.w_deduct}元</c:if></font></td>
		  	</tr>
		  	<tr >
			    <td align="right">销售额：</td>
			    <td><c:if test="${not empty af.map.sale_money}">${af.map.sale_money}</c:if></td>
		  	</tr>
		  	<tr>
			    <td align="right">销售量：</td>
			    <td><c:if test="${not empty af.map.sale_num}">${af.map.sale_num}元</c:if></td>
		  	</tr>
		  	<tr>
			    <td align="right"><font color="red">负毛利：</font></td>
			    <td><font color="red"><c:if test="${not empty af.map.loss_profit}">${af.map.loss_profit}元</c:if></font></td>
		  	</tr>
		  	<tr>
			    <td align="right" ><font color="red">社保：</font></td>
			    <td><font color="red"><c:if test="${not empty af.map.w_ss}">${af.map.w_ss}元</c:if></font></td>
		  	</tr>
		  	<tr>
			    <td align="right" ><font color="red">公积金：</font></td>
			    <td><font color="red"><c:if test="${not empty af.map.w_cpf}">${af.map.w_cpf}元</c:if></font></td>
		  	</tr>
		  	<tr>
			    <td align="right">税前工资：</td>
			    <td><c:if test="${not empty af.map.w_before_tax}">${af.map.w_before_tax}元</c:if></td>
		  	</tr>
		  	<tr>
			    <td align="right" ><font color="red">税金：</font></td>
			    <td><font color="red"><c:if test="${not empty af.map.w_money_of_tax}">${af.map.w_money_of_tax}元</c:if></font></td>
		  	</tr>
		  	<tr>
			    <td align="right">税后工资：</td>
			    <td><c:if test="${not empty af.map.w_after_tax}">${af.map.w_after_tax}元</c:if></td>
		  	</tr>
		  	<tr>
			    <td align="right">其他：</td>
			    <td><c:if test="${not empty af.map.w_other}">${af.map.w_other}元</c:if></td>
		  	</tr>
		  	<tr>
			    <td align="right">实发工资：</td>
			    <td><c:if test="${not empty af.map.w_fact}">${af.map.w_fact}元</c:if></td>
		  	</tr>
		  	<tr>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
		  	</tr>
		</table>
	</c:if>
</html-el:form>
<div class="bottom">康佳集团 版权所有 KONKA 2010.All Rights Reserved</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#btn_submit").click(function(){
		loading();
		this.form.submit();
	});
});

function loading(){
	//jLoading("正在查询...", {HorizontalPosition:"center", VerticalPosition:"center", TimeShown:3000});
	jLoading("&nbsp;&nbsp;正在查询...", {autoHide:false, HorizontalPosition:"center", VerticalPosition:"center", MinWidth:150});
}
//]]></script>
<jsp:include page="../public_page.jsp"></jsp:include>
<jsp:include page="/__analytics.jsp" />
</body>
</html>