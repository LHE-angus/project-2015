<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>

<div id="process" class="section4">
<div class="node fore ready">
  <ul>
  	<li class="tx1">&nbsp;</li>
  	<li class="tx2">提交订单</li>
  	<li class="tx3">
  		<fmt:formatDate value="${bill.add_date}" pattern="yyyy-MM-dd HH:mm:ss"/><br -=""> 
  		<a href="#" class="fblue" onclick="openUserDiv(${bill.add_user_id})">${bill.add_user_realname}</a>
  	</li>
  </ul>
</div>

<!-- 财务审核 -->
<c:if test="${bill.sell_state ge 20 and bill.sell_state ne 21}">
  <div class="proce ready"><ul><li class="tx1">&nbsp;</li></ul></div>
  <div class="node ready">
  	<ul>
  		<li class="tx1">&nbsp;</li>
  		<li class="tx2">财务审核</li>
  		<li class="tx3">
  			<fmt:formatDate value="${bill.audit_date}" pattern="yyyy-MM-dd HH:mm:ss"/><br -=""> 
  			<a href="#" class="fblue" onclick="openUserDiv(${bill.audit_user_id})">${bill.audit_real_name}</a>
  		</li>
  	</ul>
  </div>
</c:if>
<c:if test="${bill.sell_state lt 20 or bill.sell_state eq 21}">
<div class="proce doing"><ul><li class="tx1">&nbsp;</li></ul></div>
<div class="node singular"><ul><li class="tx1">&nbsp;</li><li class="tx2">财务审核</li></ul></div>
</c:if>

<!-- 发货 -->
<c:if test="${bill.sell_state ge 30}">
<div class="proce ready"><ul><li class="tx1">&nbsp;</li></ul></div>
<div class="node ready">
  <ul>
  	<li class="tx1">&nbsp;</li>
  	<li class="tx2">发货</li>
  	<li class="tx3">
  		<fmt:formatDate value="${bill.dist_date}" pattern="yyyy-MM-dd HH:mm:ss"/><br -="">
  		<a href="#" class="fblue" onclick="openUserDiv(${bill.dist_user_id})">${bill.dist_real_name}</a> 
  	</li>
  </ul>
</div>
</c:if>
<c:if test="${bill.sell_state ge 20 and bill.sell_state lt 30 and bill.sell_state ne 21}">
<div class="proce doing"><ul><li class="tx1">&nbsp;</li></ul></div>
<div class="node singular"><ul><li class="tx1">&nbsp;</li><li class="tx2">发货</li></ul></div>
</c:if>
<c:if test="${bill.sell_state lt 20}">
<div class="proce wait"><ul><li class="tx1">&nbsp;</li></ul></div>
<div class="node wait"><ul><li class="tx1">&nbsp;</li><li class="tx2">发货</li></ul></div>
</c:if>

<!-- 确认消费者收货 -->
<c:if test="${bill.sell_state ge 40}">
  <div class="proce ready"><ul><li class="tx1">&nbsp;</li></ul></div>
  <div class="node ready">
  	<ul>
  		<li class="tx1">&nbsp;</li>
  		<li class="tx2">确认消费者收货</li>
  		<li class="tx3">
  			<fmt:formatDate value="${bill.take_googs_date}" pattern="yyyy-MM-dd HH:mm:ss"/><br -=""> 
  			<a href="#" class="fblue" onclick="openUserDiv(${bill.check_recgoods_user_id})">${bill.check_recgoods_real_name}</a>
  		</li>
  	</ul>
  </div>
</c:if>
<c:if test="${bill.sell_state ge 30 and bill.sell_state lt 40}">
<div class="proce doing"><ul><li class="tx1">&nbsp;</li></ul></div>
<div class="node singular"><ul><li class="tx1">&nbsp;</li><li class="tx2">确认消费者收货</li></ul></div>
</c:if>
<c:if test="${bill.sell_state lt 30}">
<div class="proce wait"><ul><li class="tx1">&nbsp;</li></ul></div>
<div class="node wait"><ul><li class="tx1">&nbsp;</li><li class="tx2">确认消费者收货</li></ul></div>
</c:if>
<!-- 交易成功 -->
<c:if test="${bill.sell_state ge 60}">
<div class="proce ready"><ul><li class="tx1">&nbsp;</li></ul></div>
<div class="node ready"><ul><li class="tx1">&nbsp;</li><li class="tx2">交易成功</li><li class="tx3"><fmt:formatDate value="${bill.jz_date}" pattern="yyyy-MM-dd HH:mm:ss"/><br -=""> <fmt:formatDate value="${bill.jz_date}" pattern="HH:mm:ss"/></li></ul></div>
</c:if>
<c:if test="${bill.sell_state ge 40 and bill.sell_state lt 60}">
<div class="proce doing"><ul><li class="tx1">&nbsp;</li></ul></div>
<div class="node singular"><ul><li class="tx1">&nbsp;</li><li class="tx2">交易成功</li></ul></div>
</c:if>
<c:if test="${bill.sell_state lt 40}">
<div class="proce wait"><ul><li class="tx1">&nbsp;</li></ul></div>
<div class="node wait"><ul><li class="tx1">&nbsp;</li><li class="tx2">交易成功</li></ul></div>
</c:if>
</div>
<div id="userInfoDiv" style="display:none;">
    <div id="loadingUserDiv" align="left" style=""><img src="${ctx}/images/loading.gif" style="vertical-align:middle;" />正在查询用户信息,请稍等...</div>
    <!-- 用户信息列表 Begin -->
    <div id="user_tbody_div"></div>
    <!-- 用户信息列表 End -->
</div>
<script type="text/javascript">//<![CDATA[
function openUserDiv(user_id){
	$("#user_tbody_div").empty();
	$("#userInfoDiv").dialog({
	      title: '用户信息', 
	      width: 400,
	      height: 200,
	      draggable: true,
	      resizable: false,
	      position:'center',
	      modal : true,
	      open: function(event, ui) {
					getUserInfo(user_id);
	      		},
	      close: function(event, ui) {
	    	  		$("#loadingUserDiv").show();
		  		}
		  //buttons: {//"确认": function() {
			 		// 			$(this).dialog("close");
		  			//		},
			//		"关闭": function() {
    		//					$(this).dialog("close");
    		//				}
			//  	   }
	}).dialog("open");
}

function getUserInfo(user_id){
	//$("#loadingDiv").ajaxStart( function (){$(this).show();}); 
    //$("#loadingDiv").ajaxStop ( function (){$(this).hide();});
    $.ajax({
    	type: "POST" , 
        url: 'KonkaXxZmdAddSalesOrder.do' , 
        data: "method=getUserInfo&user_id=" + user_id + "&n=" + Math.random(), 
        dataType: "json" , 
        async: true, 
        error: function (request, settings) {alert(" 数据加载请求失败！ "); },
        success: function (result) {
            $("#loadingUserDiv").hide();
            var noWriteHtml = '<span style="color:#999;">未填写</span>';
            var user_name, dept_name, tel, mobile, email, qq;
            if (result.user_name == null) {
				user_name = noWriteHtml;
			} else {
				user_name = result.user_name;
			}
			//alert(user_name);
			if (result.dept_name == null) {
				dept_name = noWriteHtml;
			} else {
				dept_name = result.dept_name;
			}
			//alert(dept_name);
			if (result.tel == null) {
				tel = noWriteHtml;
			} else {
				tel = result.tel;
			}
			//alert(tel);
			if (result.mobile == null) {
				mobile = noWriteHtml;
			} else {
				mobile = result.mobile;
			}
			//alert(mobile);
			if (result.email == null) {
				email = noWriteHtml;
			} else {
				email = result.email;
			}
			//alert(email);
			if (result.qq == null) {
				qq = noWriteHtml;
			} else {
				qq = result.qq;
			}
			//alert(qq);
        	$("#user_tbody_div").append('<div class="rtabcont1" align="left">' +
									 '<table width="95%" border="0" cellspacing="5" cellpadding="0" class="rtable1">' +
									 	'<tr>' +
											'<td>' +
												'<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable4">' +
													'<tr>' +
					        						    '<td width="15%" class="title_item" nowrap="nowrap" align="right">' +
										    				'<strong class="fb">姓名：</strong>' +
										    			'</td>' +
										    			'<td width="35%" align="left" nowrap="nowrap">' + user_name + '</td>' +
										    			'<td width="15%" class="title_item" nowrap="nowrap" align="right">' +
										    				'<strong class="fb">部门：</strong>' +
										    			'</td>' +
										    			'<td align="left" nowrap="nowrap">' + dept_name + '</td>' +
				 						        '</tr>' +
													'<tr>' +
					        						    '<td width="15%" class="title_item" nowrap="nowrap" align="right">' +
										    				'<strong class="fb">电话：</strong>' +
										    			'</td>' +
										    			'<td width="35%" align="left" nowrap="nowrap">' + tel + '</td>' +
										    			'<td width="15%" class="title_item" nowrap="nowrap" align="right">' +
										    				'<strong class="fb">手机：</strong>' +
										    			'</td>' +
										    			'<td align="left" nowrap="nowrap">' + mobile + '</td>' +
				 						        '</tr>' +
													'<tr>' +
					        						    '<td width="15%" class="title_item" nowrap="nowrap" align="right">' +
										    				'<strong class="fb">邮箱：</strong>' +
										    			'</td>' +
										    			'<td width="35%" align="left" nowrap="nowrap">' + email + '</td>' +
										    			'<td width="15%" class="title_item" nowrap="nowrap" align="right">' +
										    				'<strong class="fb">QQ：</strong>' +
										    			'</td>' +
										    			'<td align="left" nowrap="nowrap">' + qq + '</td>' +
				 						        '</tr>' +
										        '</table>' +
											'</td>' +
										'</tr>' +
									  '</table>');
        }
    });
}
//]]></script>