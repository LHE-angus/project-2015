<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
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
  		<table cellpadding="5" width="100%" cellspacing="8px" class="noteMacro" border="0" align="center" style="background-color:#FFFFBB;">
		  <tr>
		    <td width="40" align="center" valign="middle" height="25"><img src="${ctx}/commons/styles/message/images/warning.gif" width="16" height="16" style="vertical-align:middle;" alt="" border="0" /></td>
		    <td><p>业务员 ${customerUserInfo.real_name}，您好！该客户暂没有分配登录用户，无法登录康佳客户端，系统提醒您请尽快分配登录用户并告知。</p></td>
		  </tr>
		</table>
  		</div>
  		</c:if>
		    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
		        <tr>
		          <th width="15%"  height="45" style="font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';color: red;"><span>计划上报</span></th>
					<th width="35%" >&nbsp;</th>
					<th width="10%" height="45" style="font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';"><span>单据编号：</span></th>
					<th colspan="3" align="left"><span><font color="red"></font><font color="red">${af.map.sp_sn}</font></span>
					<html-el:hidden property="sp_sn" value="${af.map.sp_sn}"/>
					</th>
		        </tr>
		        <tr>
					<td nowrap="nowrap" class="title_item" align="right">主题：</td>
					<td align="left" colspan="4" width="90%">
					${af.map.sp_name}
					</td>
				</tr>
		        <tr>
					<td nowrap="nowrap" class="title_item" align="right">活动类型：</td>
					<td align="left" width="40%">
					${hd_type}
					</td>
					<td nowrap="nowrap" class="title_item" align="right">活动状态：</td>
					<td align="left" width="40%">
			        <c:choose>
              		<c:when test="${fn:escapeXml(af.map.hd_status) eq 0}">未上报</c:when>
              		<c:when test="${fn:escapeXml(af.map.hd_status) eq 30}">进行中</c:when>
              		<c:when test="${fn:escapeXml(af.map.hd_status) eq 50}">已结束</c:when>
              		</c:choose>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">上报人：</td>
					<td align="left">
					${af.map.report_user_name}
					</td>
					<td nowrap="nowrap" class="title_item" align="right">创建日期：</td>
					<td align="left">
					<fmt:formatDate var="add_sp_date" value="${af.map.add_sp_date}" pattern="yyyy-MM-dd" />
					${add_sp_date}
					</td>
				</tr>
				 <tr>
					<td nowrap="nowrap" class="title_item" align="right">主要客户：</td>
					<td align="left">
					${af.map.main_customer}
					</td>
					<td nowrap="nowrap" class="title_item" align="right">主要R3编码：</td>
					<td align="left" width="40%">
					${af.map.main_r3_code}
					</td>
				</tr>
				 <tr>
					<td nowrap="nowrap" class="title_item" align="right">其他客户：</td>
					<td align="left">
					${af.map.other_customer}
					</td>
					<td nowrap="nowrap" class="title_item" align="right">其他R3编码：</td>
					<td align="left" width="40%">
					${af.map.other_r3_code}
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">活动目标：</td>
					<td align="left">
						<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
							<tr>
								<td class="title_item" align="right" nowrap="nowrap" width="20%"><font color="red">* </font>任务金额:</td>
								<td align="left" width="80%">${af.map.task_money}万元</td>
							</tr>
							<tr>
								<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>任务销量:</td>
								<td align="left">${af.map.task_sail_num}台</td>
							</tr>
							<tr>
								<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>活动渠道排名:</td>
								<td align="left">${af.map.activity_ranking}</td>
							</tr>
						</table>
					</td>
					<td nowrap="nowrap" class="title_item" align="right" colspan="2">
						<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
							<tr>
								<td nowrap="nowrap" class="title_item" align="right">准备期:</td>
								<td><fmt:formatDate value="${af.map.zb_s_date}" pattern="yyyy-MM-dd" var="_zb_s_date" />
		          									${_zb_s_date}</td>
								<td nowrap="nowrap" class="title_item" align="right">至：</td>
								<td><fmt:formatDate value="${af.map.zb_e_date}" pattern="yyyy-MM-dd" var="_zb_e_date" />
       												${_zb_e_date}</td>
							</tr>
							<tr>
								<td nowrap="nowrap" class="title_item" align="right">爆发期:</td>
								<td><fmt:formatDate value="${af.map.bf_s_date}" pattern="yyyy-MM-dd" var="_bf_s_date" />
		          							${_bf_s_date}</td>
								<td nowrap="nowrap" class="title_item" align="right">至：</td>
								<td><fmt:formatDate value="${af.map.bf_e_date}" pattern="yyyy-MM-dd" var="_bf_e_date" />
		          							${_bf_e_date}</td>
							</tr>
						</table>
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
					<td nowrap="nowrap" class="title_item" align="right">分公司：</td>
					<td align="left" width="40%">
					${dept_fgs.dept_name}
					</td> 
					<td nowrap="nowrap" class="title_item" align="right">经营部：</td>
					<td align="left">
					${af.map.jyb_name}
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">客户类型：</td>
					<td align="left" width="40%">
					${af.map.par_customer_type_name}
					</td> 
					<td nowrap="nowrap" class="title_item" align="right">业务员：</td>
					<td align="left">
					${af.map.ywy_name}
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">细分类型：</td>
					<td align="left" colspan="4" style="width:90%">
					${af.map.customer_type_name}
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">地址：</td>
					<td align="left" colspan="4" style="width:90%">
					${af.map.hd_addr}
					</td>
				</tr>
		        <tr>
					<td nowrap="nowrap" class="title_item" align="right">活动备注：</td>
					<td align="left" colspan="4">
					${af.map.hd_remark}
					</td>
				</tr>
				
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">活动方案：</td>
					<td colspan="3" width="95%;">
	                <ol>
	            	<c:forEach items="${af.map.peAttachmentsList}" var="cur">
					<c:if test="${fn:startsWith(cur.file_desc, 'policy_file')}">
					 <li><a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}">${cur.file_name}</a>
					 &nbsp;&nbsp;&nbsp;</li>
					</c:if>
					</c:forEach>
					</ol>
	            	</td>
				</tr>
		        <tr>
					<th width="10%" align="left" height="45" style="font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';color: red;">
					过程管理
					</th>
					<th colspan="3"></th>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">预约数：</td>
					<td align="left" colspan="">
					${af.map.yy_num}
					</td>
					<td nowrap="nowrap" class="title_item" align="right">维护日期：</td>
					<td align="left">
					<fmt:formatDate var="add_date" value="${af.map.add_date}" pattern="yyyy-MM-dd" />
					${add_date}
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">活动准备情况说明 ：</td>
					<td align="left" colspan="3">
					${af.map.file_remark}
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">活动照片：</td>
					<td colspan="3" width="95%">
	            	<ol>
	            	<c:forEach items="${af.map.peAttachmentsList}" var="cur">
					<c:if test="${fn:startsWith(cur.file_desc, 'hd_photo_file_show')}">
					 <li><a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}">${cur.file_name}</a>
					 &nbsp;&nbsp;&nbsp;<a href="#" id="att_del_${cur.id}"></a></li>
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
					<td nowrap="nowrap" class="title_item" align="right">销售总金额：</td>
					<td align="left" width="40%">
					${empty af.map.total_sail_money?0:af.map.total_sail_money}万元
					</td>
					<td nowrap="nowrap" class="title_item" align="right">销售总数量：</td>
					<td align="left" width="40%">
					${empty af.map.total_sail_num?0:af.map.total_sail_num}台
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">考核期结算金额：</td>
					<td align="left" width="40%">
					${empty total_sail_money?0:total_sail_money}万元
					</td>
					<td nowrap="nowrap" class="title_item" align="right">考核期结算数量：</td>
					<td align="left" width="40%">
					${empty total_sail_num?0:total_sail_num}台
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">本月结算金额：</td>
					<td align="left" width="40%">
					${empty month_sail_money?0:month_sail_money}万元
					</td>
					<td nowrap="nowrap" class="title_item" align="right">本月结算数量：</td>
					<td align="left" width="40%">
					${empty month_sail_num?0:month_sail_num}台
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">零售总金额：</td>
					<td align="left" width="40%">
					${empty sum_sail_money?0:sum_sail_money}万元
					</td>
					<td nowrap="nowrap" class="title_item" align="right">零售总数量：</td>
					<td align="left" width="40%">
					${empty sum_sail_num?0:sum_sail_num}台
					</td>
				</tr>
				<tr>
				<td nowrap="nowrap" class="title_item" align="right">活动总结：</td>
				<td colspan="3" width="95%">
	             <c:forEach items="${af.map.peAttachmentsList}" var="cur">
				 <c:if test="${fn:startsWith(cur.file_desc, 'file_show')}">
				 <li><a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}">${cur.file_name}</a>
					 &nbsp;&nbsp;&nbsp;</li>
					</c:if>
					</c:forEach>
				
	            </td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">指定机型销售：</td>
					<td align="left" colspan="3">
					 <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
		                <tr>
						<td align="center">品类/型号</td>
						<td align="center">金额（单位：万元）</td>
						<td align="center">数量（单位：台）</td>
						<td align="center">备注</td>
		              </tr>
		              <tbody id="tbodyOrder">
		               <c:forEach items="${af.map.konkaSpMdSailList}" var="cur">
		              <tr>
		                <td align="left" >
						${cur.md_name}
						<br />
						</td>
						<td align="center">
						${cur.money}<br />
						</td>
						<td align="center">
						${cur.num}<br />
						</td>
						<td align="left">
						${cur.memo}<br />
						</td>
		              </tr>
		              </c:forEach>
		              </tbody>
		               <tr class="title_top">
		                <td>合计</td>
		                <td align="center">
		                	${dd_money_sum }
		                </td>
		                <td align="center">
		                	${dd_num_sum}
		                </td>
		                <td> &nbsp; </td>
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
		            <input class="bgButtonBack" type="reset" name="reset" value="返回" id="btn_back" onclick="history.back();"/>
		            <br/>
		            <div style="height: 50px">&nbsp;</div>
		          </td>
		        </tr>
		    </table>
  </div>
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>