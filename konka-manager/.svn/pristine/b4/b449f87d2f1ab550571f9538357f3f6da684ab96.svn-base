<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<link href="${ctx}/styles/customer/fancybox/fancybox.css" rel="stylesheet" type="text/css" />
<style type="text/css">
ul.order_process_view {margin:0px;padding:0px;line-height:normal;}
ul.order_process_view li { float:left; height:70px; text-align:center; font-size:12px; padding:5px; list-style:none; margin-left:5px; min-width:90px;position:relative;}
ul.order_process_view li .mark { font-size:12px;width:100%;height:3px;line-height:50px;background-color:green;margin-bottom:10px;}
ul.order_process_view li.arrow { font-size:14px;line-height:70px;min-width:0px;text-align:center; }

ul.order_process_view li.done .mark { background-color:green; }
ul.order_process_view li.doing .mark { background-color:green; }
ul.order_process_view li.todo .mark { background-color:green; }

ul.order_process_view li.done .arrow { color:#eee; }
ul.order_process_view li.doing .arrow { color:#aaa; }
ul.order_process_view li.todo .arrow { color:#666; }

</style>
<ul class="order_process_view">
	<li class="done">
		<div class="mark"></div>
		<div class="role" title="${order.user_shop_name} 客户提交订单"><a href="#">提交订单</a><br/><a href="#">（${fnx:overlay_ssii(order.user_shop_name, '...', fn:length(order.user_shop_name) / 4, fn:length(order.user_shop_name) - fn:length(order.user_shop_name) / 4)}</a>）</div>
		<div class="time" title="审核时间"><fmt:formatDate value="${order.order_date}" pattern="yyyy-MM-dd HH:mm"/> </div>
	</li>
	<li class="arrow">--&gt;</li>
	<c:if test="${empty doneList and empty doing_audit_role and empty todoList}">
		<li class="doing">
			<div class="mark"></div>
			<div class="role">等待指定审核流程<br/>（分管客户业务员）</div>
		</li>
	</c:if>
	<!-- 已审核 -->
	<c:if test="${not empty doneList}">
		<c:forEach var="cur" items="${doneList}" varStatus="vs">
				<li class="done">
					<div class="mark"></div>
					<div class="role"><a href="#" title="审核角色">
					<c:if test="${cur.audit_result ne 10}">${cur.map.role_name}</c:if>
					<c:if test="${cur.audit_result eq 10}">订单变更</c:if>
					</a><br/>（<a href="#" title="审核人姓名">${cur.audit_user}</a>）</div>
					<div class="time" title="审核时间"><fmt:formatDate value="${cur.audit_datetime}" pattern="yyyy-MM-dd HH:mm"/></div>
				</li>
				<c:if test="${not (vs.last and empty doing_audit_role and empty todoList)}">
					<li class="arrow">
						<!-- 1同意，-1驳回，0-驳回（到客户，重新制单），  -9 撤回（客户操作） -->
						<c:choose>
							<c:when test="${cur.audit_result eq -9}"><span style="color:#00f;">-撤回-&gt;</span></c:when>
							<c:when test="${cur.audit_result eq -1}"><span style="color:#f00;">-驳回-&gt;</span></c:when>
							<c:when test="${cur.audit_result eq 0}"><span style="color:#f00;">-驳回-&gt;</span></c:when>
							<c:when test="${cur.audit_result eq 1}"><span style="color:green;">-同意-&gt;</span></c:when>
							<c:when test="${cur.audit_result eq 10}"><span style="color:orange;">-订单变更-&gt;</span></c:when>
						</c:choose>
					</li>
				</c:if>
		</c:forEach>
	</c:if>
	<!-- 即将审核 -->
	<c:if test="${not empty doing_audit_role}">
		<li class="doing">
			<div class="mark"></div>
			<div class="role" title="${empty sessionScope.userInfo ? '' :'审核角色，点击查询人员'}"><a id="doing_user_list_a" href="#" onclick="openUserList('${order.add_dept_id}', '${doing_audit_role_id}', '${doing_audit_role}')">${doing_audit_role}<br/>（即将审核...）</a></div>
			<div class="time" title="审核时间"></div>
			<div class="cuib" title="订单催办"><a href="#" onclick="sendmessageprocess('${order.id}')">催办</a></div>
		</li>
		
		<c:if test="${not empty todoList}">
			<li class="arrow">--&gt;</li>
		</c:if>
	</c:if>
	<!-- 等待审核 -->
	<c:if test="${not empty todoList}">
		<c:forEach var="cur" items="${todoList}" varStatus="vs">
			<li class="todo">
				<div class="mark"></div>
				<div class="role" title="${empty sessionScope.userInfo ? '' :'审核角色，点击查询人员'}"><a href="#" onclick="openUserList('${order.add_dept_id}', '${cur.role_id}', '${cur.role_name}')">${cur.role_name}<br/>（等待审核...）</a></div>
				<div class="time" title="审核时间"></div>
			</li>
			<c:if test="${not vs.last}">
				<li class="arrow">--&gt;</li>
			</c:if>
		</c:forEach>
	</c:if>
	<!-- 客户确认 -->
	<c:if test="${order.kh_confirm_state eq -1}">
		<li class="arrow">--&gt;</li>
		<li class="todo">
			<div class="mark"></div>
			<div class="role" title="${order.user_shop_name}">（${fnx:overlay_ssii(order.user_shop_name, '...', fn:length(order.user_shop_name) / 4, fn:length(order.user_shop_name) - fn:length(order.user_shop_name) / 4)}）<br/>（等待确认）</div>
			<div class="time" title="确认时间"></div>
		</li>
	</c:if>
	<c:if test="${order.kh_confirm_state eq 1}">
		<li class="arrow">--&gt;</li>
		<li class="todo">
			<div class="mark"></div>
			<div class="role" title="${order.user_shop_name}">（${fnx:overlay_ssii(order.user_shop_name, '...', fn:length(order.user_shop_name) / 4, fn:length(order.user_shop_name) - fn:length(order.user_shop_name) / 4)}）<br/>（已确认）</div>
			<div class="time" title="确认时间"><fmt:formatDate value="${cur.kh_confirm_date}" pattern="yyyy-MM-dd HH:mm"/></div>
		</li>
	</c:if>
 </ul>
 
<c:set value="" var="exp" />
<c:forEach var="cur" items="${nodeList}" varStatus="vs">
<c:if test="${not empty cur.next_node}"><c:set value="${cur.next_node},${exp}" var="exp" /></c:if>
</c:forEach>
<c:if test="${not empty exp}">
<div style="clear:left;"></div>
<div style="text-align:left;">注：该流程图仅作参考，以实际审核流程为准。此流程存在<span title="条件表达式为：${exp}">条件判断</span>。</div>
</c:if>
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript">//<![CDATA[    
function openUserList(dept_id, role_id, role_name){
	$.dialog({
		title:  role_name + "人员列表",
		width:  300,
		height: 350,
		min: false,
		max: false,
        lock: true ,
        drag: false,
	    content:"url:${ctx}/manager/admin/UserList.do?method=list&dept_id=" + dept_id + "&role_id=" + role_id + "&cust_id=" + "${order.cust_id}"
	});
}

function sendmessageprocess(order_id){
	if(confirm("提示，您确认发送催办？")){
		 $.ajax({type: "post", 
		  url : "${ctx}/manager/admin/KonkaOrderMessageProcess.do?method=save", 
		   dataType:'json',
		   sync: true, 
		   error: function(){alert("订单催办未成功！");},
		   data: {"order_id" :order_id},
		   success: function(json){
			   if(json.addcount != 0){
				   alert("催办成功,已经催办"+json.count+"次");
			   }else{
				   alert("请查看下一个审核角色是否有审核人！");
			   }
			   
		   }	});
	}
}
//]]></script>