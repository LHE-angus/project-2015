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
<c:set var="ywy.submit"><fmt:message key="ywy.submit" bundle="${lang}"/></c:set>
<c:set var="audit.time"><fmt:message key="audit.time" bundle="${lang}"/></c:set>
<c:set var="audit.role"><fmt:message key="audit.role" bundle="${lang}"/></c:set>
<c:set var="audit.name"><fmt:message key="audit.name" bundle="${lang}"/></c:set>
<c:set var="click.to.audit.name"><fmt:message key="click.to.audit.name" bundle="${lang}"/></c:set>
<ul class="order_process_view">
	<li class="done">
		<div class="mark" style="height: 2px; width: 80px"></div>
		<div class="role" title="${create_man} ${ywy.submit}">
			<a href="#"><fmt:message key="sumbit.apply" bundle="${lang}"/></a>
			<br/>
			<div style="height: 5px"></div>
			(<a href="#">${create_man}</a>)
		</div>
		<div style="height: 5px"></div>
		<div class="time" title="${audit.time }">
			<fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd HH:mm"/> 
		</div>
	</li>
	<li class="arrow">--&gt;</li>
		<!-- 已审核 -->
		<c:if test="${not empty doneList}">
			<c:forEach var="cur" items="${doneList}" varStatus="vs">
					<li class="done">
						<div class="mark" style="height: 2px; width: 80px"></div>
						<div class="role">
							<a href="#" title="${audit.role }">${cur.audit_user}</a>
							<br/>
							<div style="height: 5px"></div>
							（<a href="#" title="${audit.name }">${cur.agent_audit_user}</a>）
						</div>
						<div style="height: 5px"></div>
						<div class="time" title="${audit.time }">
							<fmt:formatDate value="${cur.audit_datetime}" pattern="yyyy-MM-dd HH:mm"/>
						</div>
					</li>
					<c:if test="${not (vs.last and empty doing_audit_role and empty todoList)}">
						<li class="arrow">
							<!-- 0同意，1驳回 -->
							<c:choose>
								<c:when test="${cur.audit_result eq 0}"><span style="color:green;">-<fmt:message key="agree" bundle="${lang}"/>-&gt;</span></c:when>
								<c:when test="${cur.audit_result eq 1}"><span style="color:#f00;">-<fmt:message key="reject" bundle="${lang}"/>-&gt;</span></c:when>
							</c:choose>
						</li>
					</c:if>
			</c:forEach>
		</c:if>
		<!-- 即将审核 -->
		<c:if test="${not empty doing_audit_role}">
			<li class="doing">
				<div class="mark" style="height: 2px; width: 80px"></div>
				<div class="role" title="${empty sessionScope.userInfo ? '' : click.to.audit.name}">
					<a id="doing_user_list_a" href="#" onclick="openUserList('${af.map.subcomp_id}', '${doing_audit_role.role_id}', '${doing_audit_role.role_name}')">
						${doing_audit_role.role_name}
						<br/>
						<div style="height: 5px"></div>
						<fmt:message key="doing.audit" bundle="${lang}"/>
					</a>
				</div>
				<div class="time" title="${audit.time }"></div>
			</li>
			<c:if test="${not empty todoList}">
				<li class="arrow">--&gt;</li>
			</c:if>
		</c:if>
		<!-- 等待审核 -->
		<c:if test="${not empty todoList}">
			<c:forEach var="cur" items="${todoList}" varStatus="vs">
				<li class="todo">
					<div class="mark" style="height: 2px; width: 80px"></div>
					<div class="role" title="${empty sessionScope.userInfo ? '' :click.to.audit.name}">
						<a href="#" onclick="openUserList('${af.map.subcomp_id}', '${cur.role_id}', '${cur.role_name}')">${cur.role_name}
						<br/>
						<div style="height: 5px"></div>
						<fmt:message key="wait.audit" bundle="${lang}"/></a></div>
				</li>
				<c:if test="${not vs.last}">
					<li class="arrow">--&gt;</li>
				</c:if>
			</c:forEach>
		</c:if>
 </ul>
 
<script type="text/javascript">   
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
}</script>