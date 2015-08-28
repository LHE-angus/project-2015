<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单审核信息</title>
<link href="http://qdgl.konka.com/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="http://qdgl.konka.com/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="http://qdgl.konka.com/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<link href="http://qdgl.konka.com/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<link href="http://qdgl.konka.com/styles/customer/css/tab.css" rel="stylesheet" type="text/css" />
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
</head>
<body>

<div id="tab2" style="width:1200px">
<div align="left"><input class="bgButtonBack" type="reset" name="reset" value="返回" id="btn_back" onclick="history.back();"/></div> 
  	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
  		<tr>
       	<td class="title_item" style="text-align:center;font-size:15px;font-weight:bold;color:#74685F">审核信息</td>
       </tr>
  		<tr>
       	<td align="center">
       		<div style="width:100%;padding:5px 5px 0 5px;">
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
       		</div>
       	</td>
       </tr>
     		<c:if test="${not empty af.map.auditList}">
        <tr>
        	<td align="center">
	        		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="navClass" style="margin:10px 0 10px 0;">
						<thead id="nav" style="width:99%;background:#abd589;">
		        			<tr>
		        				<th width="5%" align="center">序号</th>
		        				<th width="10%" align="center">日期</th>
		        				<th width="10%" align="center">审核人</th>
		        				<th width="10%" align="center">结果</th>
		        				<th align="center">意见</th>
		        			</tr>
	        			</thead>
	        			<c:forEach items="${af.map.auditList}" var="cur1" varStatus="vs">
		        			<tr>
		        				<td align="center" nowrap="nowrap">${vs.count}</td>
		        				<td align="center" nowrap="nowrap"><fmt:formatDate value="${cur1.audit_datetime}" pattern="yyyy/MM/dd HH:mm"/></td>
		        				<td align="center" nowrap="nowrap">${fn:escapeXml(cur1.audit_user)}</td>
		        				<td align="center" nowrap="nowrap">
		        					<c:choose>
		        						<c:when test="${cur1.audit_result eq 1}"><span style="color:green;">审核通过</span></c:when>
		        						<c:when test="${cur1.audit_result eq -1}"><span style="color:#F00;">驳回（至审核人）</span></c:when>
		        						<c:when test="${cur1.audit_result eq 0}"><span style="color:#00F;">驳回（至制单）</span></c:when>
		        						<c:when test="${cur1.audit_result eq -9}"><span style="color:#999;">（客户）撤销</span></c:when>
		        						<c:when test="${cur1.audit_result eq 10}"><span style="color:#999;">订单变更</span></c:when>
		        					</c:choose>
		        				</td>
		        				<td>
		        					<c:if test="${empty cur1.audit_comment }">无</c:if>
		                  			<c:if test="${not empty cur1.audit_comment }">${fn:escapeXml(cur1.audit_comment)}</c:if>
		                  		</td>
		                  	</tr>
    					</c:forEach>
    	    	</table>
            </td>
        </tr>
		</c:if>
		<tr>
			<td><div style="width:100%; overflow-x:auto;">
				<table width="500" border="0" cellpadding="5" cellspacing="0" class="navClass" style="margin:10px 0 10px 0; float:left;">
						<tr>
							<th align="center">申请数量</th>
							<td align="right">${af.map.applyNumMoney.orderapplynum}</td>
							<th align="center">申请金额</th>
							<td align="right">${af.map.applyNumMoney.orderapplymoney}</td>
						</tr>
				</table>
				</div>
			</td>
		</tr>
</table>
  </div>
</body>
</html>