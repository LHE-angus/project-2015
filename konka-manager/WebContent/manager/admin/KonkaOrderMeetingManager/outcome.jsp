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
<link href="${ctx}/commons/styles/select2.min.css" rel="stylesheet" type="text/css" />
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
			<html-el:hidden property="method" value="save" styleId="method"/>
	      	<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	      	<html-el:hidden property="id" value="${af.map.id}" />
	      	<html-el:hidden property="queryString" styleId="queryString"/>
	      	<html-el:hidden property="file_status" styleId="file_status"/>
	      	<html-el:hidden property="meeting_sn" styleId="meeting_sn" value="${af.map.meeting_sn}" />
	      	<html-el:hidden property="cust_id" styleId="cust_id" value="${cust_id}" />
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
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>主题：</td>
					<td align="left" colspan="3" width="90%">
					<html-el:text property="meeting_name" styleId="meeting_name" readonly="true" style="width:300px;" maxlength="80" />
					</td>
				</tr>
		        <tr>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>会议类型：</td>
					<td align="left" width="40%">
					<html-el:select property="meeting_id" styleId="meeting_id" style="background-color:#fff;" disabled="true">
					<html-el:option value="">请选择…</html-el:option>
					<html-el:optionsCollection name="spActivityList" label="hd_type" value="id"/>
					</html-el:select>
					</td>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>分公司：</td>
					<td align="left" width="40%">
					<html-el:hidden property="dept_id" value="${af.map.dept_id}"  styleId="dept_id" />
					<html-el:text property="dept_name" value="${af.map.dept_name}"  styleId="dept_name" readonly="true" maxlength="20" style="width:200px;"/>
					</td>
				</tr>
				<tr>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>订货目标（万元）：</td>
					<td align="left" colspan="3" style="width:90%">
					<html-el:text property="order_target" styleId="order_target" readonly="true" maxlength="10" style="width:100px;" />
					</td>
				</tr>
				<tr>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>召开时间：</td>
					<td align="left">
					<fmt:formatDate var="open_date" value="${af.map.open_date}" pattern="yyyy-MM-dd" />
					<html-el:text property="open_date" styleId="open_date" value="${open_date}" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;width:80px;" />
					</td>
					<td class="title_item" align="right" nowrap="nowrap">会议状态：</td>
					<td align="left">
					<html-el:select property="meeting_status" styleId="meeting_status" value="50">
					<html-el:option value="50">已结束</html-el:option>
					<html-el:option value="30">进行中</html-el:option>
					</html-el:select>
					</td>
				</tr>
				<tr>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>负责人：</td>
					<td align="left">
					<html-el:hidden property="charge_person" />
					<html-el:select property="charge_person_job_id" styleId="charge_person_job_id" disabled="true" style="background-color:#fff;">
					<c:forEach items="${user_list}" var="cur">
					<html-el:option value="${cur.job_id}">${cur.department}->${cur.user_name}</html-el:option>
					</c:forEach>
					</html-el:select>
					</td>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>联系电话：</td>
					<td align="left">
					<html-el:text property="charge_person_tel" styleId="charge_person_tel" readonly="true"  maxlength="20" style="width:100px;"/>
					</td>
				</tr>
				<tr>
					<td class="title_item" align="right" nowrap="nowrap">上报人：</td>
					<td align="left">
					<html-el:hidden property="report_user_job_id" />
					<html-el:text property="report_user_name" value="${af.map.report_user_name }" styleId="report_user_name" readonly="true" maxlength="20" style="width:200px;"/>
					</td>
					<td class="title_item" align="right" nowrap="nowrap">创建日期：</td>
					<td align="left">
					<fmt:formatDate var="meeting_date" value="${af.map.add_meeting_date}" pattern="yyyy-MM-dd" />
					<html-el:text property="add_meeting_date" maxlength="30" styleId="add_meeting_date" value="${meeting_date}" readonly="true" style="width:80px;"/>
					</td>
				</tr>
				<tr>
					<td class="title_item" align="right" nowrap="nowrap">地址：</td>
					<td align="left" colspan="3" style="width:90%">
					<html-el:text property="hd_addr" styleId="hd_addr" readonly="true"  maxlength="40" style="width:200px;"/>
					</td>
				</tr>
		        <tr>
		          <td class="title_item" align="right" nowrap="nowrap">参与客户：</td>
		          <td colspan="3">
		            <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
		              <tr class="title_top">
		                <td nowrap="nowrap">客户名称</td>
		                <td width="5%" nowrap="nowrap">客户类型</td>
		                <td width="8%" nowrap="nowrap">业务员</td>
		                <td width="10%" nowrap="nowrap">经营部</td>
		              </tr>
		              <tr id="trHidden" style="display:none;">
		                <td align="center">
		                 <html-el:hidden property="r3_code" styleId="r3_code"/>
		                <html-el:text property="customer" styleId="customer" readonly="true" style="width:99%;" styleClass="webinput good_count" />
		                </td>
		                <td align="center">
		                <html-el:text property="c_name" styleId="c_name" readonly="true" styleClass="webinput" />
		                <html-el:hidden property="c_type" styleId="c_type" />
		                </td>
		                <td align="center">
		                <html-el:text property="ywy_name" styleId="ywy_name" readonly="true" styleClass="webinput" />
		                <html-el:hidden property="ywy_job_id" styleId="ywy_job_id" />
		                </td>
		                <td align="center">
		                  <html-el:text property="jyb_name" styleId="jyb_name" readonly="true" styleClass="webinput" />
		                  <html-el:hidden property="jyb_id" styleId="jyb_id" />
		                </td>
		              </tr>
		              <tbody id="tbodyContent">
		               <c:forEach items="${af.map.orderMeetingCustomerList}" var="cur">
		              <tr id="trHidden" style="">
		                <td align="center">
		                  <html-el:hidden property="r3_code" styleId="${cur.r3_code}" value="${cur.r3_code}"/>
		                <html-el:text property="customer_${cur.r3_code}" styleId="customer_${cur.r3_code}" readonly="true" value="${cur.customer}" style="width:99%;" styleClass="webinput good_count" />
		                </td>
		                <td align="center">
		                <html-el:text property="c_name_${cur.r3_code}" styleId="c_name_${cur.r3_code}" readonly="true" value="${cur.c_name}" styleClass="webinput" />
		                <html-el:hidden property="c_type_${cur.r3_code}" styleId="c_type_${cur.r3_code}" value="${cur.c_type}" />
		                </td>
		                <td align="center">
		                <html-el:text property="ywy_name_${cur.r3_code}" styleId="ywy_name_${cur.r3_code}" readonly="true" value="${cur.ywy_nmae}" styleClass="webinput" />
		                <html-el:hidden property="ywy_job_id_${cur.r3_code}" styleId="ywy_job_id_${cur.r3_code}" value="${cur.ywy_job_id}" />
		                </td>
		                <td align="center">
		                  <html-el:text property="jyb_name_${cur.r3_code}" styleId="jyb_name_${cur.r3_code}" readonly="true" value="${cur.jyb_name}" styleClass="webinput" />
		                  <html-el:hidden property="jyb_id_${cur.r3_code}" styleId="jyb_id_${cur.r3_code}" value="${cur.jyb_id}" />
		                </td>
		              </tr>
		              </c:forEach>
		              </tbody>
		            </table>
		          </td>
		        </tr>
		        <tr>
					<td class="title_item" align="right" nowrap="nowrap">会议备注：</td>
					<td align="left" colspan="3">
					<html-el:textarea property="meeting_memo" styleId="meeting_memo" style="resize:none;" readonly="true" cols="50" rows="5"></html-el:textarea>
					</td>
				</tr>
		        <tr>
					<th align="left" height="45" style="font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';color: red;">
					过程管理
					</th>
					<th colspan="3"></th>
				</tr>
				<tr>
					<td class="title_item" align="right" nowrap="nowrap">维护日期：</td>
					<td align="left" colspan="3">
					<fmt:formatDate var="add_date" value="${af.map.add_date}" pattern="yyyy-MM-dd" />
					<html-el:text property="add_date" maxlength="30" value="${add_date}" styleId="add_date" style="width:80px;" readonly="true" />
					</td>
				</tr>
				<tr>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>会议流程：</td>
					<td align="left" colspan="3"><html-el:text property="meeting_process" readonly="true" styleId="meeting_process" maxlength="100" style="width:300px;"/></td>
				</tr>
				<tr>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>过程描述：</td>
					<td align="left" colspan="3">
					<html-el:textarea property="meeting_process_des" styleId="meeting_process_des" readonly="true" style="resize:none;" cols="50" rows="5"></html-el:textarea>
					</td>
				</tr>
				<tr>
					<td class="title_item" align="right" nowrap="nowrap">会议政策：</td>
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
					<td class="title_item" align="right" nowrap="nowrap">会议照片：</td>
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
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>订货金额：</td>
					<td align="left" width="40%">
					<html-el:text property="order_money" styleId="order_money" value="${empty af.map.order_money?'':af.map.order_money }" maxlength="21" style="width:150px;"/>万元
					</td>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>订货数量：</td>
					<td align="left" width="40%">
					<html-el:text property="order_num" styleId="order_num" value="${empty af.map.order_num?'':af.map.order_num }"  maxlength="16" style="width:150px;"/>台
					</td>
				</tr>
				<tr>
				<td class="title_item" align="right" nowrap="nowrap">附件：</td>
				<td colspan="3" width="95%">
					<div id="divFileHidden" style="display: none;" >
		                <input name="file_hidden" type="file" id="file_hidden" style="width: 250px;" />
		                <img src="../../images/x.gif" style="vertical-align:middle; cursor: pointer;" id="imgDelTr" title="删除"/>
	                </div>
	                
	               <div id="divFile">
	                	<input name="attachment" type="file" id="attachment" style="width: 250px;" />
	                	<img src="../../images/+.gif" style="vertical-align:middle; cursor: pointer;" id="imgAddTr" title="再添加一个" />
	               </div>
	               
	                <ol>
	            	<c:forEach items="${af.map.peAttachmentsList}" var="cur">
						<c:if test="${fn:startsWith(cur.file_desc, 'attachment')}">
						 <li>
							 <a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}">${cur.file_name}</a>
							 &nbsp;&nbsp;&nbsp;
							 <a href="#" id="att_del_${cur.id}"><img src="../../images/x.gif" style="vertical-align:middle; cursor: pointer;" title="删除"/></a></li>
						</c:if>
					</c:forEach>
					</ol>
	            </td>
				</tr>
				<tr>
					<td class="title_item" align="right" nowrap="nowrap">指定机型订货：</td>
					<td align="left" colspan="3">
					 <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
		                <tr>
						<td align="center">品类/型号</td>
						<td align="center">金额（单位：元）</td>
						<td align="center">数量（单位：台）</td>
						<td align="center">备注</td>
						<td width="8%" align="center" nowrap="nowrap" id="addPdTD" style="cursor:pointer;"><img src="${ctx}/images/+.gif" style="vertical-align:middle;" /></td>
						<td></td>
		              </tr>
		              <tr id="orderHidden" style="display:none;">
						<td align="center" >
							<html-el:select property="md_name" styleClass="md_name" style="width:150px">
				        		<html-el:option value="">请选择</html-el:option>
				          		<c:forEach var="cur" items="${konkaSpMdTypeList}" varStatus="vs">
				          			<html-el:option value="${cur.md_name}">${cur.md_name}</html-el:option>
				          		</c:forEach>
				            </html-el:select>
						<br />
						</td>
						<td align="center"><html-el:text property="md_money" styleId="md_money" maxlength="13" styleClass="md_money"  style="width:100px;"/><br />
						</td>
						<td align="center"><html-el:text property="md_num" styleId="md_num" styleClass="md_num" maxlength="10" style="width:100px;"/><br />
						</td>
						<td align="center"><html-el:text property="md_memo" styleId="md_memo" styleClass="md_memo" style="width:100px;"/><br />
						</td>
		                <td align="center" title="删除"><img src="${ctx}/styles/jxc/images/x.gif" style="vertical-align:text-bottom;"/></td>
		              </tr>
		              <tbody id="tbodyOrder">
		               <c:forEach items="${af.map.konkaSpMdSailList}" var="cur">
		              <tr id="trOrderHidden" style="">
		                <td align="center" >
							<html-el:select property="md_name" styleClass="md_name" style="width:150px" value="${cur.md_name}">
				        		<html-el:option value="">请选择</html-el:option>
				          		<c:forEach var="cur1" items="${konkaSpMdTypeList}" varStatus="vs">
				          			<html-el:option value="${cur1.md_name}">${cur1.md_name}</html-el:option>
				          		</c:forEach>
				            </html-el:select>
						<br />
						</td>
						<td align="center">
						<html-el:text property="md_money" value="${cur.money}" styleId="md_money" maxlength="13" styleClass="md_money" style="width:100px; "/><br />
						</td>
						<td align="center">
						<html-el:text property="md_num" value="${cur.num}" styleId="md_num" styleClass="md_num" maxlength="10" style="width:100px;"/><br />
						</td>
						<td align="center">
						<html-el:text property="md_memo" value="${cur.memo}" styleId="md_memo" styleClass="md_memo" style="width:100px;"/><br />
						</td>
		                <td align="center" title="删除" style="cursor: pointer;"><img src="${ctx}/styles/jxc/images/x.gif" style="vertical-align:text-bottom;"/></td>
		              </tr>
		              </c:forEach>
		              </tbody>
		              <tbody id="showAddTrsTbody"></tbody>
		               <tr class="title_top">
		                <td>合计</td>
		                <td align="center">
		                	<span id="dd_money_sum" style="background-color: white;"></span>
		                </td>
		                <td align="center">
		                	<span id="dd_count_sum"></span>
		                </td>
		                <td> &nbsp; </td>
		                <td> &nbsp; </td>
		              </tr>
		            </table>
				</td>
				</tr>
				<tr>
					<td class="title_item" align="right" nowrap="nowrap">结果说明：</td>
					<td align="left" colspan="3">
					<html-el:textarea property="memo" styleId="memo" style="resize:none;" cols="50" rows="5"></html-el:textarea>
					<div id="info_tip" style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div>
					</td>
				</tr>
		    <tr>
	          <td colspan="4" align="center">
	          	<input type="button" name="save" class="bgButtonSave" value="提交" id="btn_submit"/>
	            <input type="button" name="temp_save" class="bgButtonSave" value="保存" id="btn_save"/>
	            <input class="bgButtonBack" type="button" name="return" value="返回" id="btn_reset" onclick="history.back();"/>
	            <br/>
		        <div style="height: 50px" id="process_tips">&nbsp;</div>
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
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/select2.min.js?t=1"></script>

<script type="text/javascript">//<![CDATA[

var f = document.forms[0];
$(document).ready(function(){
	$("#meeting_name").attr("dataType", "Require").attr("msg", "请填写");
	$("#meeting_id").attr("dataType", "Require").attr("msg", "请填写");
	$("#order_target").attr("Require",true).attr("dataType", "Double").attr("msg", "请正确填写，只能为数字");//shu
  	$("#open_date").attr("dataType", "Require").attr("msg", "请填写");
	$("#charge_person_job_id").attr("dataType", "Require").attr("msg", "请填写");
	$("#charge_person_tel").attr("require", "true").attr("msg", "请正确填写联系电话");
  	$("#meeting_process").attr("datatype", "Limit"  ).attr("max", "50").attr("min","1").attr("msg", "会议流程必须在50个字之内");
  	$("#meeting_process_des").attr("datatype", "Limit"  ).attr("max", "200").attr("min","1").attr("msg", "过程描述必须在200个字之内");
  	$("#order_money").focus(setOnlyNum); 
 	$("#order_num").focus(setOnlyNum2); 
 	$("#order_money").attr("require","true").attr("dataType", "Custom").attr("regexp", "^\\d{1,16}(\\.\\d{0,4})?$").attr("msg" ,"订货金额必填，整数最多16位，小数最多4位");
 	$("#order_num").attr("require","true").attr("dataType", "Custom").attr("regexp", "^\\d{1,16}(\\.\\d{0,0})?$").attr("msg" ,"请填写整数");

  	$("#meeting_memo").attr("datatype", "Limit"  ).attr("max", "200").attr("min","0").attr("msg", "会议备注必须在200个字之内");
  	$("#memo").attr("datatype", "Limit"  ).attr("max", "200").attr("min","0").attr("msg", "结果说明必须在200个字之内");
  	$("#memo").textbox({
		maxLength: 200,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_tip").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_tip").slideUp("normal");
	});
  	$("#meeting_photo" ).attr("dataType", "Filter" ).attr("msg", "图片的格式必须是(bmp,gif,jpeg,jpg,png)！").attr("require", "false").attr("accept", "bmp, gif, jpeg, jpg, png");
	$("#photo_file" ).attr("dataType", "Filter" ).attr("msg", "图片的格式必须是(bmp,gif,jpeg,jpg,png)！").attr("require", "false").attr("accept", "bmp, gif, jpeg, jpg, png");
	$("[id^='md_num']").focus(setOnlyNum2);
	$("[id^='md_money']").focus(setOnlyNum);
	
	$("#imgAddTr").click(function (){
        $("#divFileHidden").clone().find("#file_hidden").attr("name", "attachment_" + new Date().getTime()).end().appendTo($("#divFile")).show();
        resizeFrameHeight();
        $("img[id='imgDelTr']").each(function(){
            $(this).click(function (){
                $(this).parent().remove();
                resizeFrameHeight(2);
            });
        });
	 });
	
	$("#addPdTD").click(function(){
		$("#orderHidden").clone(true).attr("class","tr_pd").appendTo($("#showAddTrsTbody")).show();
		var lastTR = $("tr:last", "#showAddTrsTbody");
		
		$("td:last", lastTR).click(function (){
			$(this).parent().remove();
			$(".md_money").blur(); //重新计算
			$(".md_num").blur(); //重新计算
			resizeFrameHeight(2);
	   }).css("cursor", "pointer");
		   
		//iframe高度自适应
		resizeFrameHeight(2);
	});
	
 
var lastOTR = $("tr", "#tbodyOrder");
$("td:last", lastOTR).click(function (){
 	$(this).parent().remove();
 	$(".md_money").blur();
 	$(".md_num").blur();
	
 }).css("cursor", "pointer");
 
$("a[id^='att_del_']").click(function() {
	  var a = this;
	   if(!confirm('确实要删除此附件？')) return;
	    $.post("${ctx}/manager/chengduoa/ExpenseClaims.do", { method : "deleteFile", id : $(this).attr("id").replace(/att_del_/g, '')}, function(success) {
	   if (success){alert("恭喜您，删除附件成功!");$(a).parent().remove();} else alert(" 很抱歉，删除附件出错!"); 
	  });
 });
 
$(".md_money").blur(function(){
	var md_money_v = $(this).val();
		 var total_md_money = 0;
		 $(".md_money").each(function(){
			var md_money = $(this).val();
		 if($.trim(md_money).length > 0){
			total_md_money = parseFloat(total_md_money) + parseFloat(md_money);
		 }
		 });
		 $("#dd_money_sum").html(total_md_money);
});
$(".md_num").blur(function(){
	var md_num_v = $(this).val();
		 var total_md_num = 0;
		 $(".md_num").each(function(){
			var md_num = $(this).val();
			if($.trim(md_num).length > 0){
				total_md_num = parseFloat(total_md_num) + parseFloat(md_num);
			}
		 });
		 $("#dd_count_sum").html(total_md_num);
});

$(".md_name").change(function(){
	var md_name = $(this).val();
	var md_name_count = 0;
	$(".md_name").each(function(){
		if($.trim($(this).val()).length > 0 && md_name == $(this).val()){
			md_name_count= md_name_count + 1;
		}
	});
	if(md_name_count>1){
		alert(md_name+"的指定机型已经存在！");
		$(this).val("");
	}
});



//可查询选择框
$('#charge_person_job_id').select2({
    allowClear: true
});
	
	
 
$("#btn_submit").click(function() {
	 if(confirm("点击提交后无法修改，如果您不想提交，可以点击保存。您确定要提交吗？")) {
		if(Validator.Validate(this.form, 3)){
			$("#file_status").val("2");
			this.form.submit();
			
			$('#process_tips').html('提交正在执行，请耐心多等它一会！').css('color','red');
			
			$(this).attr('disabled','disabled');
			$('#btn_reset').attr('disabled','disabled');
			$('#btn_save').attr('disabled','disabled');
			
			resizeFrameHeight(2);
		}
		
	 }
 });
 
 $("#btn_save").click(function() {
	 if(Validator.Validate(this.form, 3)){
		$("#file_status").val("1");
		this.form.submit();
		
		$('#process_tips').html('保存正在执行，请耐心多等它一会！').css('color','red');
		
		$(this).attr('disabled','disabled');
		$('#btn_submit').attr('disabled','disabled');
		$('#btn_reset').attr('disabled','disabled');
		
		resizeFrameHeight(2);
		
	 }
	 
 });
 
 bindJhCountAndJhPrice($("[id^='md_num']", "#tbodyOrder"), $("[id^='md_money']", "#tbodyOrder"), $("#tbodyOrder"));
 
 $("#meeting_id").change(function() {	 
 	var meeting_id = $("#meeting_id").val();
	$.ajax({
		type: "POST",
		url: "KonkaOrderMeetingManager.do",
		data: "method=addSdSail&meeting_id=" + meeting_id,
		dataType: "json",
		error: function(request, settings) {
			alert("检查失败，请稍候再次尝试。");
		},
		success: function(oper) {
			$("#tbodyOrder > *").each(function (){
		    	$(this).remove();
		    });
			for(var x in oper){
				if(oper[x].type) {		
					$("#orderHidden").clone().appendTo($("#tbodyOrder")).show();
			    	var lastTR = $("tr:last", "#tbodyOrder");
					resizeFrameHeight();
			    	var md_type = $("#md_type", lastTR);//R3客户管理
			    	var md_name = $("#md_name", lastTR);//隐藏域经营部ID
			    	var md_money = $("#md_money", lastTR);
			    	var md_num = $("#md_num", lastTR);
			    	var md_memo = $("#md_memo", lastTR); 
			    	
			    	md_name.attr("id",oper[x].md_name);
			    	md_type.attr("id","md_type" + oper[x].md_name);
			    	md_money.attr("id","md_money" + oper[x].md_name);
			    	md_num.attr("id","md_num" + oper[x].md_name);
			    	md_memo.attr("id","md_memo" + oper[x].md_name);
			    	
			    	md_type.attr("name","md_type" + oper[x].md_name);
			    	md_money.attr("name","md_money" + oper[x].md_name);
			    	md_num.attr("name","md_num" + oper[x].md_name);
			    	md_memo.attr("name","md_memo" + oper[x].md_name);
			    	
			    	md_type.attr("value",oper[x].type);
			    	md_name.attr("value",oper[x].md_name);
			    	md_money.attr("value",0);
			    	md_num.attr("value",0);
			    	
			    	bindJhCountAndJhPrice(md_num, md_money, lastTR);
			    	
			    	$("td:last", lastTR).click(function (){
				    	$(this).parent().remove();
				    }).css("cursor", "pointer");
				}
			}
		}
	});
 });
 
 calcPdNumAndPrice("tbodyOrder");
 resizeFrameHeight();
});//ready end
	
	function resizeFrameHeight(offset, min_height) {
		$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
	}

	function getKonkaR3MmtMatch() { 
		var returnValue = window.showModalDialog("${ctx}/manager/admin/KonkaR3MmtMatch.do?method=orderMeetingList&azaz=" + Math.random(),window,"dialogWidth:600px;status:no;dialogHeight:370px");
		if (returnValue != null && returnValue.ids != "") {
			//前一次操作返回的值  + 当前操作返回的值 = 当前的值
		    
		    var r3_value_array = new Array();
		    var r3_code = new Array();
		
		    r3_value_array = returnValue.ids.split(",");
		    r3_code = returnValue.names.split(",");
		
			for(var i = 0; i < r3_value_array.length; i++){
	
				$("#trHidden").clone().appendTo($("#tbodyContent")).show();
		    	var lastTR = $("tr:last", "#tbodyContent");
		    	resizeFrameHeight();
		    	var r3_code_va = $("#r3_code", lastTR);//R3客户管理
		    	var l4_dept_id = $("#jyb_id", lastTR);//隐藏域经营部ID
		    	var l4_dept_name = $("#jyb_name", lastTR);//经营部
				var ywy_user_name = $("#ywy_name", lastTR);//业务员
				var customer_name = $("#customer", lastTR);//客户名称
				var customer_type = $("#c_type", lastTR);//隐藏域 客户类型ID
				var customer_type_name = $("#c_name", lastTR);//客户类型
				var job_id = $("#ywy_job_id", lastTR);//隐藏域 业务员岗位ID
				
				var value_array = r3_value_array[i].split("_");
				
				l4_dept_id.attr("value", value_array[0]);
				l4_dept_name.attr("value", value_array[1]);
				ywy_user_name.attr("value", value_array[2]);
				customer_name.attr("value", value_array[3]);
				customer_type.attr("value", value_array[4]);
				customer_type_name.attr("value", value_array[5]);
				job_id.attr("value", value_array[6]);
				r3_code_va.attr("value", r3_code[i]);
		      	
		      	r3_code_va.attr("id",r3_code[i]);//改变id
		    	l4_dept_id.attr("id","jyb_id_" + r3_code[i]);//隐藏域经营部ID
		    	l4_dept_name.attr("id","jyb_name_" + r3_code[i]);//经营部
				ywy_user_name.attr("id","ywy_name_" + r3_code[i]);//业务员
				customer_name.attr("id","customer_" + r3_code[i]);//客户名称
				customer_type.attr("id","c_type_" + r3_code[i]);//隐藏域 客户类型ID
				customer_type_name.attr("id","c_name_" + r3_code[i]);//客户类型
				job_id.attr("id","ywy_job_id_" + r3_code[i]);//隐藏域 业务员岗位ID
				
		    	l4_dept_id.attr("name","jyb_id_" + r3_code[i]);//隐藏域经营部ID
		    	l4_dept_name.attr("name","jyb_name_" + r3_code[i]);//经营部
				ywy_user_name.attr("name","ywy_name_" + r3_code[i]);//业务员
				customer_name.attr("name","customer_" + r3_code[i]);//客户名称
				customer_type.attr("name","c_type_" + r3_code[i]);//隐藏域 客户类型ID
				customer_type_name.attr("name","c_name_" + r3_code[i]);//客户类型
				job_id.attr("name","ywy_job_id_" + r3_code[i]);//隐藏域 业务员岗位ID
	
				//删除操作，影响弹出页面的返回值
				$("td:last", lastTR).click(function (){
			    	$(this).parent().remove();
			    }).css("cursor", "pointer");
			}
			resizeFrameHeight(2);
		}
	}

	//输入数量和台数的绑定计算函数
	function bindJhCountAndJhPrice(good_count, good_price, lastTR) {
		good_count.keyup(function(){//数量
			var _reg = /^\d+$/;
			var count =(this.value);
	   		if (!_reg.test(count)) {
	   			good_count.val(1);
	   			count = 1;
	   		}
	   		var price = parseFloat(good_price.val());//单价
	   		if(isNaN(price)) price = 0;
	   		
	   		count = parseFloat(count);//数量
	   		if(isNaN(count)) count = 1;
	   	   	
			calcPdNumAndPrice("tbodyOrder");
		}); 
	
		good_price.keyup(function(){//单价
			var price = (this.value);
			var _reg = /^[\+\-]?\d*?\.?\d*?$/;
			if (!_reg.test(price)) {
				good_price.val(0);
				price = 0;
			}
			price = parseFloat(this.value);
			if(isNaN(price)) price = 0;
			if(price > parseFloat(99999.99)){
				alert("价格太大了，上限为10万！");
				good_price.val(99999.99);
				price = 99999.99;
			}
	   		var count = parseFloat(good_count.val());//数量
	   		if(isNaN(count)) count = 1;
	
			calcPdNumAndPrice("tbodyOrder");
		});
	
		calcPdNumAndPrice("tbodyOrder");
	}

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
	}
	//订货金额限制输入中文字符！
	function setOnlyNum() {//可以输入小数
		$(this).css("ime-mode", "disabled").attr("t_value", "").attr("o_value", "").bind("dragenter",function(){
		return false;
		});
		$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
		}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
		}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		if(this.value.length == 0) this.value = "0";
		});
		//this.text.selected;
		}
		
		
	function setOnlyNum2() {//不可以输入小数
		$(this).css("ime-mode", "disabled");
		$(this).attr("t_value", "");
		$(this).attr("o_value", "");
		$(this).bind("dragenter",function(){
			return false;
		});
		$(this).keypress(function (){
			if(!this.value.match(/^\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
		}).keyup(function (){
			if(!this.value.match(/^\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
		}).blur(function (){
			if(!this.value.match(/^(?:[\+]?\d+(?:\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=this.value;this.o_value=this.value};
		});
	}


//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>