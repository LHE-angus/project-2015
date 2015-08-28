<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<style type="text/css">
select {
	font-family:Microsoft YAHEI;
	font-size:12px;
}
input {
	font-family:Microsoft YAHEI;
	font-size:12px;
}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oartop">
  <table width="400" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
      <td>当前位置：${naviString}</td>
    </tr>
  </table>
</div>
<div>
  <div class="rtabcont1">
      <div align="left">
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
          <tr>
          	<td colspan="4"  style="background:#CCCCCC" >会员信息</td>
          </tr>	
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">会员卡号：</td>
            <td colspan="3"><c:out value="${af.map.user_sn}" /> </td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">会员类型：</td>
            <td >
            	<c:out value="${af.map.user_level eq 0 ? '个人会员' : (af.map.user_level eq 1 ? '白银会员' : (af.map.user_level eq 2 ? '黄金会员' : '钻石会员'))}" />
             </td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">姓名：</td>
            <td colspan="3"><c:out value="${af.map.user_name}" /></td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">性别：</td>
            <td colspan="3"><c:out value="${af.map.sex eq 1 ? '男' : (af.map.sex eq 2 ? '女' : '保密')}" /></td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">手机：</td>
            <td colspan="3"><c:out value="${af.map.mp}" /></td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">邮箱：</td>
            <td colspan="3"><c:out value="${af.map.email}" /></td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">身份证：</td>
            <td colspan="3"><c:out value="${af.map.id_card}" /></td>
          </tr>
          <tr>
            <td width="15%" valign="middle" nowrap="nowrap" class="title_item" align="right">所在地：</td>
            <td colspan="3"><c:out value="${p_index_name}" /><c:if test="${empty p_index_name}"><span style="color:#999;">未填写</span></c:if></td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">联系地址：</td>
            <td colspan="3"><c:out value="${af.map.addr}" /></td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">生日：</td>
            <td colspan="3">
            	<fmt:formatDate value="${af.map.birthday}" pattern="yyyy-MM-dd" /><c:if test="${empty af.map.birthday}"><span style="color:#999;">未填写</span></c:if>
            </td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">婚姻：</td>
            <td>
            	<c:out value="${af.map.marriage eq 1 ? '已婚' : (af.map.marriage eq 2 ? '未婚' : '保密')}" />
            </td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" align="right">月收入：</td>
            <td >
            	<c:if test="${af.map.income eq 1}">1000以下</c:if>
            	<c:if test="${af.map.income eq 2}">1000-2000</c:if>
            	<c:if test="${af.map.income eq 3}">2000-4000</c:if>
            	<c:if test="${af.map.income eq 4}">4000-6000</c:if>
            	<c:if test="${af.map.income eq 5}">6000-10000</c:if>
            	<c:if test="${af.map.income eq 6}">10000-20000</c:if>
            	<c:if test="${af.map.income eq 7}">20000以上</c:if>
             </td>
          </tr>
          <tr>
          <td nowrap="nowrap" height="28" class="title_item">兴趣爱好：</td>
           <td colspan="3"> <c:out value="${af.map.hobby}" /></td>
        </tr>
        </table>
      </div>
        
      <div style="margin-top: 20px">  
       	 <table  width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2" >
        		<tr> <td colspan="7" bgcolor="#CCCCCC">换卡记录 </td></tr>
          		<tr>
          			<td width="5%" align="center">序号</td>
          			<td align="center">会员卡</td>
          			<td align="center">原会员卡</td>
          			<td align="center">新会员卡</td>
          			<td align="center">换卡原因</td>
          			<td align="center">换卡时间</td>
          			<td align="center">备注</td>
          		</tr>
          		<c:forEach var="cur" items="${entityList}" varStatus="vs">
          		<tr>
          			<td align="center">${vs.count}</td>
          			<td>${cur.user_sn}</td>
          			<td>${cur.old_user_sn}</td>
          			<td>${cur.now_user_sn}</td>
          			<td>${cur.reason}</td>
          			<td align="center"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
          			<td align="center">${cur.remarks}</td>
          		</tr>
          		</c:forEach>
          		</table>
      </div>
      
      <div style="margin-top: 20px">
      	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      			<tr> <td colspan="8" bgcolor="#CCCCCC">明细信息 </td></tr>
          		<tr>
          			<td width="5%" align="center">序号</td>
          			<td width="10%" align="center">会员卡号</td>
          			<td width="10%" align="center">积分种类</td>
          			<td width="10%" align="center">分公司</td>
          			<td align="center">产品型号</td>
          			<td width="20%" align="center">购买时间</td>
          			<td width="10%" align="center">积分调整</td>
          			<td width="10%" align="center">所得积分</td>
          		</tr>
          		<c:forEach var="cur" items="${detailsList}" varStatus="vs">
          		<tr>
          			<td align="center">${vs.count}</td>
          			<td align="center">${cur.user_sn}</td>
          			<td align="center"><c:choose>
		          			<c:when test="${cur.jf_cate eq 1}">购买产品</c:when>
		          			<c:when test="${cur.jf_cate eq 2}"><font style="color:blue;">积分调整</font></c:when>
		          		</c:choose></td>
          			<td>${cur.map.dept_name}</td>
          			<td>${cur.pd_id}</td>
          			<td align="center"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
          			<td align="center"><c:out value="${cur.jf_cate eq 2 ? cur.scorts : ''}"/></td>
          			<td align="center">${cur.scorts}</td>
          		</tr>
          		</c:forEach>
          		</table>
      </div>
      
      <div style="margin-top: 20px">
      <table  width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      			<tr> <td colspan="5" bgcolor="#CCCCCC">兑换礼品 </td></tr>
          		<tr>
          			<td width="5%" align="center">序号</td>
          			<td align="center">会员卡号</td>
          			<td align="center">礼品名称</td>
          			<td align="center">兑换时间</td>
          			<td align="center">所用积分</td>
          		</tr>
          		<c:forEach var="cur" items="${exchangeList}" varStatus="vs">
          		<tr>
          			<td align="center">${vs.count}</td>
          			<td align="center">${cur.user_sn}</td>
          			<td>${cur.map.gift_name}</td>
          			<td align="center"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
          			<td align="center">${cur.scorts}</td>
          		</tr>
          		</c:forEach>
          		<tr>
          		<td colspan="5" align="center"><input class="bgButtonBack" type="button" name="Submit5" value="返回" onclick="history.back();return false;" id="btn_back" /></td>
        		</tr>
          		</table>
      </div>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	// 区域
	$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${province}","dataType":"Require","msg":"请选择省/直辖市/自治区！"});
	$("#city").attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${city}"});
	$("#country").attr({"subElement": "town","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${country}"});
	$("#town").attr({"defaultText": "-请选择乡/镇-", "defaultValue": "", "selectedValue": "${town}"});
	
	$("#province").cs_ext("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false,"p_index_join","${af.map.p_index_join}");
	$("#province").change();
	
	$("#user_sn").attr("datatype", "Require").attr("msg", "请填写会员卡号！");
	$("#user_level").attr("datatype", "Require").attr("msg", "请选择会员类型！");	
	$("#user_name").attr("datatype", "Require").attr("msg", "请填写会员姓名！");
	
	$("#id_card").attr("datatype", "IdCard").attr("msg", "请填写正确的身份证！");
	$("#email").attr("datatype", "Email").attr("msg", "请填写正确的邮箱！");
	$("#mp" ).attr("datatype", "Mobile").attr("msg", "请填写正确的手机号码！");
	$("#addr").attr("datatype", "Require").attr("msg", "请填写您的联系地址！");


	if(document.all("sex")[0].checked==false&&document.all("sex")[1].checked==false&&document.all("sex")[2].checked==false){
		document.all("sex")[2].checked=true;//第一个radio选中  
	}	

	var msg=false;
	$("#user_sn").blur(function(){
		if(this.value.length > 0){
			$("#loading1").ajaxStart(function(){$(this).show();});
			$("#loading1").ajaxStop (function(){$(this).hide();});
			$("#tip1").remove();
		$.ajax({
			type:"POST",
			url:"MemberInfo.do",
			data: "method=validateUserSN&user_sn=" + this.value,
			dataType: "json",
			error: function(request, settings) {alert("数据加载请求失败！"); },
			success: function(isExist) {
				if(isExist == 1) {
					msg=false;
					$("#s_after1").after('<span id="tip1" style="color:#FF0000;"><img src="${ctx}/images/reg_error.gif" />&nbsp;对不起，该会员卡已存在！<\/span>');
					} else if(isExist == 0){
				    msg=true;
					$("#s_after1").after('<span id="tip1" style="color:#5A8E4A;"><img src="${ctx}/images/reg_success.gif" />&nbsp;恭喜，该会员卡可用！<\/span>');
				} 
			}
		});
		}else {
			$("#tip1").remove();
		}
	});



	
	$("#btn_submit").click(function(){
		$("#user_sn").blur();
		if(Validator.Validate(this.form, 3)){
			if(msg){
				$("#btn_submit").attr("disabled",true);
				$("#btn_back").attr("disabled",true);
				this.form.submit();
			}
		}
	});
});

function setOnlyNum() {
		$(this).css("ime-mode", "disabled");
		$(this).attr("t_value", "");
		$(this).attr("o_value", "");
		$(this).bind("dragenter",function(){
			return false;
		});
		$(this).keypress(function (){
			if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
		}).keyup(function (){
			if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
		}).blur(function (){
			if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}
			if(this.value.length == 0) this.value = "0";
		});

}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
