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
<style type="text/css"></style>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/zmd/KonkaXxZmdVerification"  styleClass="form_cust">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="zmd_id" value="${af.map.zmd_id}" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="is_audit" styleId="is_audit" value="0" />
      <html-el:hidden property="role_id_value" value="${af.map.role_id_value}" />
      <html-el:hidden property="queryString" value="${af.map.queryString}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable4">
        <tr>
          <td colspan="4" style="font-weight:bold;font-size:18px;height:40px;text-align:center;border-bottom:none;">备案审核</td>
        </tr>
        <tr>
          <td colspan="4" style="font-weight:900;">专卖店基础信息</td>
        </tr>
        <tr>
          <td width="18%" class="title_item" align="right">分公司：</td>
          <html-el:hidden property="dept_id" value="${userInfo.dept_id}" />
          <td colspan="3" align="left">${af.map.map.dept_name}</td>
        </tr>
        <tr>
          <td width="20%" class="title_item" align="right">R3专卖店名称：</td>
          <td width="30%">${af.map.r3_name}
            <c:if test="${empty af.map.r3_name}"><span style="color:#999">未填写</span></c:if></td>
          <td width="20%" class="title_item" align="right">专卖店编号：</td>
          <td width="30%">${af.map.zmd_sn}</td>
        </tr>
        <tr>
          <td width="20%" class="title_item" align="right">R3编码：</td>
          <td width="30%">${af.map.r3_id}
            <c:if test="${empty af.map.r3_id}"><span style="color:#999">未填写</span></c:if></td>
          <td width="18%" class="title_item" align="right">R3送达方编码：</td>
          <td width="32%">${af.map.r3_send_num}</td>
        </tr>
        <tr>
          <td width="20%" class="title_item" align="right">专卖店地址：</td>
          <td width="30%">${fn:substring(af.map.addr,0,36)}</td>
          <td width="18%" class="title_item" align="right">营业面积(平方米)：</td>
          <td width="32%">${af.map.busi_area}
            <c:if test="${empty af.map.busi_area}"><span style="color:#999;">未填写</span></c:if></td>
        </tr>
        <tr>
          <td class="title_item" align="right">租赁期：</td>
          <td><c:choose>
              <c:when test="${empty af.map.rent_start or empty af.map.rent_end}"> <span style="color:#999;">未填写</span> </c:when>
              <c:otherwise>
                <fmt:formatDate value="${af.map.rent_start}" pattern="yyyy-MM-dd" />
                至
                <fmt:formatDate value="${af.map.rent_end}" pattern="yyyy-MM-dd" />
              </c:otherwise>
            </c:choose></td>
          <td class="title_item" align="right">年租金（万元）：</td>
          <td>${af.map.rent_fee}
            <c:if test="${empty af.map.rent_fee}"><span style="color:#999;">未填写</span></c:if></td>
        </tr>
        <tr>
          <td class="title_item" align="right">经营性质：</td>
          <td ><font class="blue12px">
            <c:forEach var="cur_2" items="${baseTypesList10000}">
              <c:if test="${cur_2.type_id eq af.map.busi_type}">${cur_2.type_name} </c:if>
            </c:forEach>
            </font>
            <c:if test="${empty af.map.busi_type}"><span style="color:#999;">未填写</span></c:if></td>
          <td class="title_item" align="right">经营模式：</td>
          <td ><font class="blue12px">
            <c:forEach var="cur_2" items="${baseTypesList100000}">
              <c:if test="${cur_2.type_id eq af.map.busi_mod}">${cur_2.type_name} </c:if>
            </c:forEach>
            </font>
            <c:if test="${empty af.map.busi_mod}"><span style="color:#999;">未填写</span></c:if></td>
        </tr>
        <tr>
          <td class="title_item" align="right">预计年销售（万元）：</td>
          <td colspan="3">${af.map.money_of_sell_by_year_plan}
            <c:if test="${empty af.map.money_of_sell_by_year_plan}"><span style="color:#999;">未填写</span></c:if></td>
        </tr>
        <tr>
          <td class="title_item" align="right">申请样机额度（万元）：</td>
          <td><c:if test="${is_admin eq 1}"><html-el:text property="money_of_dm_apply" styleId="money_of_dm_apply" /></c:if>
          	  <c:if test="${is_admin ne 1}">${af.map.money_of_dm_apply}</c:if>
         </td>
          <td class="title_item" align="right">计划投放专卖店样机：</td>
          <td style="word-break:break-all; word-wrap:break-word;">${af.map.put_dm_plan}
            <c:if test="${empty af.map.put_dm_plan}"><span style="color:#999;">未填写</span></c:if></td>
        </tr>
        <tr>
          <td class="title_item" align="right">申请建店费用（万元）：</td>
          <td colspan="3"><c:if test="${is_admin eq 1}"><html-el:text property="money_of_dcrt_apply" styleId="money_of_dcrt_apply" /></c:if>
          	<c:if test="${is_admin ne 1}">${af.map.money_of_dcrt_apply}</c:if></td>
        </tr>
        <tr>
         <td colspan="4" style="padding-left: 5%" width="90%">
       	 	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2" id="categorys_td">
              	<tr class="tabtt1" style="height: 28px;">
              	  <td width="" align="center" nowrap="nowrap">工程项目</td>
              	  <td width="10%" align="center" nowrap="nowrap">品名</td>
              	  <td width="10%" align="center" nowrap="nowrap">规格</td>
              	  <td width="10%" align="center" nowrap="nowrap">数量</td>
              	  <td width="10%" align="center" nowrap="nowrap">单位</td>
              	  <td width="12%" align="center" nowrap="nowrap">单价（元）</td>
              	  <td width="12%" align="center" nowrap="nowrap">小计（元）</td>
              	</tr>
              	<c:forEach items="${konkaXxZmdGcysList}" var="cur">
              	  <tr>
              	  	<td>${cur.item_name}</td>
              	  	<td align="left">${cur.pd_name}</td>
              	  	<td align="left">${cur.model_name}</td>
              	  	<td align="right">${cur.item_num}</td>
              	  	<td align="center">${cur.unit}</td>
              	  	<td align="right">${cur.price}</td>
              	  	<td align="right">${cur.total}</td>
              	  </tr>
              	</c:forEach>
              	<tr>
              		<td><span><strong>总计</strong></span></td>
              		<td colspan="7"><span class="title_item" style="color: red;"><fmt:formatNumber value="${total_money}" pattern="#0.00" /></span>&nbsp;元</td>
              	</tr>
            </table>
          </td>
        </tr>
        <tr>
          <td colspan="4" style="font-weight:900;">专卖店管理人员信息</td>
        </tr>
        <tr>
          <td class="title_item" align="right">专卖店负责人：</td>
          <td>${af.map.host_name}
            <c:if test="${empty af.map.host_name}"><span style="color:#999;">未填写</span></c:if></td>
          <td class="title_item" align="right">联系电话：</td>
          <td>${af.map.host_phone}
            <c:if test="${empty af.map.host_phone}"><span style="color:#999;">未填写</span></c:if></td>
        </tr>
        <tr>
          <td class="title_item" align="right">分公司负责人：</td>
          <td>${af.map.dept_leader_name}
            <c:if test="${empty af.map.dept_leader_name}"><span style="color:#999;">未填写</span></c:if></td>
          <td class="title_item" align="right">联系电话：</td>
          <td>${af.map.dept_leader_phone}
            <c:if test="${empty af.map.dept_leader_phone}"><span style="color:#999;">未填写</span></c:if></td>
        </tr>
        <tr>
          <td class="title_item" align="right">分公司总经理：</td>
          <td>${af.map.dept_main_man}
            <c:if test="${empty af.map.dept_main_man}"><span style="color:#999;">未填写</span></c:if></td>
          <td class="title_item" align="right">分公司财务经理：</td>
          <td>${af.map.dept_fnc_man}
            <c:if test="${empty af.map.dept_fnc_man}"><span style="color:#999;">未填写</span></c:if></td>
        </tr>
        <tr>
        <td align="right" class="title_item">附件下载：</td>
        <td colspan="3">
          <c:forEach var="cur" items="${attachmentList}" varStatus="vs"><span>${vs.count}、<a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}" target="_blank">${cur.file_name}</a> <br />
              </span></c:forEach></td>
        </tr>
        <tr>
          <td colspan="4" align="right"> 拟制人:${af.map.write_man} </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">审核状态：</td>
          <td align="left" colspan="3"><label style="cursor:pointer">
              <html-el:radio property="audit_status" styleClass="audit_status" styleId="audit_status1" value="1">审核通过</html-el:radio>
            </label>
            <label style="cursor:pointer">
              <html-el:radio property="audit_status" styleClass="audit_status" styleId="audit_status2" value="2">驳回</html-el:radio>
            </label></td>
        </tr>
        <tr id="role_tr" style="display: none;">
          <td style="font-weight:900;" align="right"><strong class="fb"><span style="color:red">[必填]</span>驳回角色：</strong></td>
          <td colspan="3">
          	<html-el:select styleId="audit_user_role" property="audit_user_role">
          		<html-el:option value="">-请选择-</html-el:option>
          		<c:forEach items="${konkaXxAuditNoteList}" var="cur">
          			<html-el:option value="${cur.audit_node_id}">${cur.audit_node_name}</html-el:option>
          		</c:forEach>
          	</html-el:select>
          </td>
        </tr>
        <c:if test="${is_admin eq 1}">
	        <tr>
	          <td class="title_item" nowrap="nowrap" align="right">是否发送邮件：</td>
	          <td align="left" colspan="3"><label style="cursor:pointer">
	              <html-el:radio property="is_send_mail" styleClass="is_send_mail" styleId="is_send_mail1" value="1">是</html-el:radio>
	            </label>
	            <label style="cursor:pointer">
	              <html-el:radio property="is_send_mail" styleClass="is_send_mail" styleId="is_send_mail2" value="2">否</html-el:radio>
	            </label></td>
	        </tr>
	        <tr id="is_send_mail_tr" style="display: none;">
	           <td class="title_item" nowrap="nowrap" align="right">发送用户</td>
               <td align="left" colspan="3">
                    <html-el:hidden property="link_user_ids" styleId="link_user_ids" />
                    <input type="text" name="link_users" id="link_users" readonly="readonly"  style="width:400px;" />
                    <img id="add_link_user" src="${ctx}/images/search.gif" style='margin: 0 0 -6px 0; cursor: pointer;' alt='选择人员' />
                    <br />
                    <span style="color: red;">注：“是否发现邮件”选择“是”的时候，参与过审核流程的用户会自动发送，此处选择的用户也会发送。</span>
                    </td>
                </tr>
        </c:if>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">审核意见：</td>
          <td align="left" colspan="3"><html-el:textarea property="audit_comment" styleId="audit_comment" cols="70" rows="5" />
            <div id="info_chat_content"  style="color:#0066FF;font-size:12px;display:none"><img src="../../images/tishi.gif" style="vertical-align:middle;" /></div></td>
        </tr>
        <tr>
          <td align="center" colspan="4"><input class="but4" type="button" id="add_btn" value="提交" />
            &nbsp;
            <input class="but3" type="reset" id="add_rst" value="重置" />
            &nbsp;
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" id="btn_back" /></td>
        </tr>
        <!-- 
        <tr>
          <td colspan="4" height="40"  align="center"><input class="bgButton" type="button"  value="备案通过" id="audit_1" />
            <input class="bgButton" type="button" value="备案不通过" id="audit_2" />
            <input class="bgButton" type="button" onclick="history.back();return false;" value="返回" /></td>
        </tr>
         -->
      </table>
    </html-el:form>
  </div>
</div>
<!-- ****** Main Frame End ****** -->
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

$("input[type='radio'][name='audit_status']").eq(1).attr("dataType", "Group").attr("msg", "请选择审核是否通过！");
	
	$(".audit_status").click(function(){
		if (this.value == 2) {
			$("#role_tr").show();
			$("#audit_comment").attr("dataType", "Require").attr("msg", "请填写驳回原因！");
			$("#audit_user_role").attr("dataType", "Require").attr("msg", "请选择驳回角色！");
		} else if (this.value == 1){
			$("#audit_comment").removeAttr("dataType");
			$("#audit_user_role").removeAttr("dataType");
			$("#audit_user_role").val("");
			$("#role_tr").hide();
		} 
		resizeFrameHeight();
	});	

	$("#audit_comment").val("");
	
   $(".is_send_mail").click(function(){
		if(this.value == 2){
			$("#is_send_mail_tr").hide();
		 } else {
		    $("#is_send_mail_tr").show();
		}
		resizeFrameHeight();
   });
	
	$("#add_link_user").click(function(){
		var link_users = $("#link_users").val();
		var link_user_ids = $("#link_user_ids").val();
		var returnValue = window.showModalDialog("${ctx}/manager/oa/DiaLog.do?azaz=" + Math.random() + "&method=selectUser&selectedUsersID=" + link_user_ids + "&selectedUsers=" + link_users + "%20", window, "dialogWidth:610px;status:no;dialogHeight:415px;scroll:no");
	    if(returnValue != null) {
	        var names = returnValue.user_link_names;
	        names = names.substring(0, names.length - 1);
	        $("#link_users").val(names);
	        $("#link_user_ids").val(returnValue.user_link_ids);
		};
	});
	
	$("#audit_comment").textbox({
		maxLength: 200,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/styles/jxc/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_chat_content").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
			resizeFrameHeight();
		}
	}).blur(function() {
		$("#info_chat_content").slideUp("normal");
		resizeFrameHeight();
	});
	  
	$("#add_btn").click(function(){
		if(Validator.Validate(this.form, 3)){
			$(this.form).submit();
			$("#add_btn").attr("disabled", "true");
		} else {
			$("#add_btn").removeAttr("disabled");
			return false;
		}
	});
});

function resizeFrameHeight(offset, min_height) {
	// frame id can be found in page /konka-wd/WebContent/manager/admin/Frames2/indexFrame.jsp, and search 'iframe' to get.
	$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
}
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
