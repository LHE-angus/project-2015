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
<style type="text/css">
select{font-family:Microsoft YAHEI;font-size:12px;}
input{font-family:Microsoft YAHEI;font-size:12px;}
label {cursor:pointer;}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div style="width:100%">
	<div class="oartop">
	    <table width="400" border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
	        <td>当前位置：${naviString}</td>
	      </tr>
	    </table>
  	</div>
  	<div class="rtabcont1">
  		<c:if test="${not empty no_user_tip}">
  		<div>
  		<link href="${ctx}/commons/styles/message/message.css" rel="stylesheet" type="text/css" />
  		<table cellpadding="5" width="100%" cellspacing="8px" class="noteMacro" border="0" align="center" style="background-color:#FFFFBB;">
		  <tr>
		    <td width="40" align="center" valign="middle" height="25"><img src="${ctx}/commons/styles/message/images/warning.gif" width="16" height="16" style="vertical-align:middle;" alt="" border="0" /></td>
		    <td><p>业务员 ${customerUserInfo.real_name}，您好！该客户暂没有分配登录用户，无法登录康佳客户端，系统提醒您请尽快分配登录用户并告知。</p></td>
		  </tr>
		</table>
  		</div>
  		</c:if>
	  	<html-el:form action="/admin/KonkaOrderMeetingManager" enctype="multipart/form-data">
		    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
		        <tr>
		          <th width="15%"  height="45" style="font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';color: red;"><span>计划上报</span></th>
					<th width="35%" >&nbsp;</th>
					<th width="15%" height="45" style="font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';"><span>单据编号：</span></th>
					<th colspan="3" align="left"><span><font color="red"></font><font color="red">${af.map.meeting_sn}</font></span>
					<html-el:hidden property="meeting_sn" value="${af.map.meeting_sn}"/>
					</th>
		        </tr>
		        <tr>
					<td nowrap="nowrap" class="title_item" align="right">主题：</td>
					<td align="left" colspan="3" width="90%">
					${af.map.meeting_name}
					</td>
				</tr>
		        <tr>
					<td nowrap="nowrap" class="title_item" align="right">会议类型：</td>
					<td align="left" width="40%">
					${af.map.hd_type}
					</td>
					<td nowrap="nowrap" class="title_item" align="right">分公司：</td>
					<td align="left" width="40%">
					${af.map.dept_name}
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">订货目标（万元）：</td>
					<td align="left" colspan="3" style="width:90%">
					${af.map.order_target}
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">召开时间：</td>
					<td align="left">
					<fmt:formatDate value="${af.map.open_date}" pattern="yyyy-MM-dd" />
					</td>
					<td nowrap="nowrap" class="title_item" align="right">会议状态：</td>
					<td align="left">
					<c:choose>
              		<c:when test="${fn:escapeXml(af.map.meeting_status) eq 0}">未上报</c:when>
              		<c:when test="${fn:escapeXml(af.map.meeting_status) eq 30}">进行中</c:when>
              		<c:when test="${fn:escapeXml(af.map.meeting_status) eq 50}">已结束</c:when>
              		</c:choose>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">负责人：</td>
					<td align="left">
					${af.map.charge_person}
					</td>
					<td nowrap="nowrap" class="title_item" align="right">联系电话：</td>
					<td align="left">
					${af.map.charge_person_tel}
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">上报人：</td>
					<td align="left">
					${af.map.report_user_name }
					</td>
					<td nowrap="nowrap" class="title_item" align="right">创建日期：</td>
					<td align="left">
					<fmt:formatDate value="${af.map.add_meeting_date}" pattern="yyyy-MM-dd" />
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">地址：</td>
					<td align="left" colspan="3" style="width:90%">
					${af.map.hd_addr}
					</td>
				</tr>
		        <tr>
		          <td nowrap="nowrap" class="title_item" align="right">参与客户：</td>
		          <td colspan="3">
		            <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
		              <tr class="title_top">
		                <td width="10%" nowrap="nowrap">R3编码</td>
		                <td width="50%" >客户名称</td>
		                <td width="10%" nowrap="nowrap">客户类型</td>
		                <td width="10%" nowrap="nowrap">业务员</td>
		                <td width="10%" nowrap="nowrap">经营部</td>
		              </tr>
		              <tbody id="tbodyContent">
		               <c:forEach items="${af.map.orderMeetingCustomerList}" var="cur">
		              <tr id="trHidden" style="">
		                <td align="left">
		                  ${cur.r3_code}
		                </td>
		                <td align="left">
		                ${cur.customer}
		                </td>
		                <td align="left">
		                ${cur.c_name}
		                </td>
		                <td align="left">
		                ${cur.ywy_nmae}
		                </td>
		                <td align="left">
		                ${cur.jyb_name}
		                </td>
		              </tr>
		              </c:forEach>
		              </tbody>
		            </table>
		          </td>
		        </tr>
		        <tr>
					<td nowrap="nowrap" class="title_item" align="right">会议备注：</td>
					<td align="left" colspan="3">
					${af.map.meeting_memo}
					</td>
				</tr>
		        <tr>
					<th align="left" height="45" style="font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';color: red;">
					过程管理
					</th>
					<th colspan="3"></th>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">维护日期：</td>
					<td align="left" colspan="3">
					<fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd" />
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">会议流程：</td>
					<td align="left" colspan="3">
					${af.map.meeting_process}
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">过程描述：</td>
					<td align="left" colspan="3">
					${af.map.meeting_process_des}
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">会议政策：</td>
					<td colspan="3" width="95%">
	                <ol>
	            	<c:forEach items="${af.map.peAttachmentsList}" var="cur">
					<c:if test="${fn:startsWith(cur.file_desc, 'policy_file')}">
					 <li><a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}">${cur.file_name}</a>
					 </li>
					</c:if>
					</c:forEach>
					</ol>
	            	</td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">会议照片：</td>
					<td colspan="3" width="95%">
	            	<ol>
	            	<c:forEach items="${af.map.peAttachmentsList}" var="cur">
					<c:if test="${fn:startsWith(cur.file_desc, 'photo_file')}">
					 <li><a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}">${cur.file_name}</a>
					</li>
					</c:if>
					</c:forEach>
					</ol>
	            	</td>
				</tr>
				<tr>
					<th align="left" height="45" style="font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';color: red;">
					结果管理
					</th>
					<th colspan="3"></th>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">订货金额：</td>
					<td align="left" width="40%">
					${empty af.map.order_money?0:af.map.order_money}万元
					</td>
					<td nowrap="nowrap" class="title_item" align="right">订货数量：</td>
					<td align="left" width="40%">
					${empty af.map.order_num?0:af.map.order_num} 台
					</td>
				</tr>
				<tr>
				<td nowrap="nowrap" class="title_item" align="right">附件：</td>
				<td colspan="3" width="95%">
	                <ol>
	            	<c:forEach items="${af.map.peAttachmentsList}" var="cur">
					<c:if test="${fn:startsWith(cur.file_desc, 'attachment')}">
					 <li><a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}">${cur.file_name}</a>
					</li>
					</c:if>
					</c:forEach>
					</ol>
	            </td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">指定机型订货：</td>
					<td align="left" colspan="3">
					 <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
		                <tr>
						<td align="center">品类/型号</td>
						<td align="center">金额（单位：元）</td>
						<td align="center">数量（单位：台）</td>
						<td align="center">备注</td>
		              </tr>
		              <tbody id="tbodyOrder">
		               <c:forEach items="${af.map.konkaSpMdSailList}" var="cur">
		              <tr>
		                <td align="center">${cur.md_name}</td>
						<td align="center">${cur.money}</td>
						<td align="center">${cur.num}</td>
						<td align="center">${cur.memo}</td>
		              </tr>
		              </c:forEach>
		              </tbody>
		               <tr class="title_top">
		                <td>合计</td>
		                <td align="center">￥${af.map.dh_money}</td>
		                <td align="center">${af.map.dh_sum} </td>
		                <td> </td>
		              </tr>
		            </table>
				</td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">结果说明：</td>
					<td align="left" colspan="3">
					${af.map.memo}
					</td>
				</tr>
		    <tr>
	          <td colspan="4" align="center">
	            <input class="bgButtonBack" type="button" name="return" value="返回" id="btn_reset" onclick="history.back();"/>
	          </td>
	        </tr>
	        </table>
		</html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.jxc.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/external/bgiframe/jquery.bgiframe.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script> 
<script type="text/javascript">//<![CDATA[

var f = document.forms[0];
$(document).ready(function(){
 calcPdNumAndPrice("tbodyOrder");
 
});//ready end
	/*处理返回值数据 ===start===*/

//计算 合计的 数量、 金额和折扣金额
function calcPdNumAndPrice(tbody_id) {
	var dd_count_sum = 0;
	var dd_money_sum = 0;
	$("[id^=md_num]", $("#" + tbody_id)).each(function(){
		var this_value = parseFloat($(this).attr("value"));
		if(isNaN(this_value)) this_value = 0;
		dd_count_sum += this_value;
	});

	$("[id^=md_money]", $("#" + tbody_id)).each(function(){
		var this_value = parseFloat($(this).val());
		if(isNaN(this_value)) this_value = 0;
		dd_money_sum += this_value;
	});
	
	
	$("#dd_count_sum").text(dd_count_sum);
	$("#dd_money_sum").text("￥" + (dd_money_sum.toFixed(2)));
	//$("#pay_money").val(parseFloat(dd_money_sum.toFixed(2)));
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>