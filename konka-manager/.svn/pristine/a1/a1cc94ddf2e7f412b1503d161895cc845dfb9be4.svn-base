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
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
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
  <%@ include file="/commons/pages/messages.jsp"%>
  <div class="rtabcont2">
    <html-el:form action="/zmd/KonkaXxZmdAuditUserInfoAudit" styleClass="form_cust">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="dept_id" value="${af.map.dept_id}" />
      <html-el:hidden property="role_id_value" value="${af.map.role_id_value}" />
      <html-el:hidden property="zmd_user_id" styleId="zmd_user_id" value="${af.map.zmd_user_id}" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td colspan="4" style="font-weight:bold;font-size:18px;height:40px;text-align:center;border-bottom:none;">专卖店客户资质审核</td>
        </tr>
        <tr>
          <td colspan="2" align="left" class="title_item">分公司：<span style="color: red;">${dept_name}</span></td>
          <td align="right" class="title_item">提交日期：</td>
          <td align="left" class="title_item"><fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd"/></td>
        </tr>
        <tr>
          <td colspan="4" style="font-weight:900;"><strong class="fb">个人信息</strong></td>
        </tr>
        <tr>
          <td width="20%" align="right" class="title_item">姓 名：</td>
          <td width="20%">${af.map.user_name}</td>
          <td width="20%" align="right" class="title_item">性 别：</td>
          <td width="30%"><c:choose>
              <c:when test="${af.map.sex eq 0}">保密</c:when>
              <c:when test="${af.map.sex eq 1}">男</c:when>
              <c:when test="${af.map.sex eq 2}">女</c:when>
              <c:when test="${af.map.sex eq 3}">不详</c:when>
            </c:choose></td>
        </tr>
        <tr>
          <td width="13%" align="right" class="title_item">出生年月：</td>
          <td width="14%"><fmt:formatDate value="${af.map.birthday}" pattern="yyyy-MM-dd" /></td>
          <td align="right" class="title_item">教育背景：</td>
          <td>${af.map.edu_bg}</td>
        </tr>
        <tr>
          <td align="right" class="title_item">婚姻状况：</td>
          <td><c:choose>
              <c:when test="${af.map.marriage eq 0}">未婚</c:when>
              <c:when test="${af.map.marriage eq 1}">已婚</c:when>
            </c:choose></td>
          <td align="right" class="title_item">准备投入的资金、资源：</td>
          <td>${af.map.resources}</td>
        </tr>
        <tr>
          <td align="right" class="title_item">是否有自有门店：</td>
          <td><c:choose>
              <c:when test="${af.map.is_stores eq 0}">否</c:when>
              <c:when test="${af.map.is_stores eq 1}">是</c:when>
            </c:choose></td>
          <td align="right" class="title_item">自有门店地址：</td>
          <td>${af.map.stores_addr}</td>
        </tr>
        <tr>
          <td align="right" class="title_item">上一个工作单位：</td>
          <td>${af.map.last_unit}</td>
          <td align="right" class="title_item">上一个工作职务：</td>
          <td>${af.map.last_post}</td>
        </tr>
        <tr>
          <td align="right" class="title_item">从事销售行业年限：</td>
          <td>${af.map.sell_work_year}</td>
          <td align="right" class="title_item">彩电从业年限：</td>
          <td>${af.map.tv_wor_year}</td>
        </tr>
        <tr>
          <td align="right" class="title_item">通讯地址：</td>
          <td>${af.map.com_addr}</td>
          <td align="right" class="title_item">邮政编码：</td>
          <td>${af.map.post_code}</td>
        </tr>
        <tr>
          <td align="right" class="title_item">电子邮箱：</td>
          <td>${af.map.email}</td>
          <td align="right" class="title_item">联系电话：</td>
          <td>${af.map.tel}</td>
        </tr>
        <tr>
          <td colspan="4" style="font-weight:900;"><strong class="fb">门店信息</strong></td>
        </tr>
        <tr>
          <td align="right" class="title_item">工商注册时间：</td>
          <td><fmt:formatDate value="${af.map.reg_date}" pattern="yyyy-MM-dd"/></td>
          <td align="right" class="title_item">注册资本（万元）：</td>
          <td>${af.map.reg_money}</td>
        </tr>
        <tr>
          <td align="right" class="title_item">营业执照经营范围：</td>
          <td>${af.map.business_scope}</td>
          <td align="right" class="title_item">营业执照注册号：</td>
          <td>${af.map.reg_num}</td>
        </tr>
        <tr>
          <td align="right" class="title_item">目前正在经营品牌：</td>
          <td>${af.map.business_brand}</td>
          <td align="right" class="title_item">正在经营门店的地址：</td>
          <td>${af.map.ope_sto_addr}</td>
        </tr>
        <tr>
          <td align="right" class="title_item">是否已有R3编码：</td>
          <td><c:choose>
              <c:when test="${af.map.is_r3 eq 0}">否</c:when>
              <c:when test="${af.map.is_r3 eq 1}">是</c:when>
            </c:choose></td>
          <td align="right" class="title_item">能否参加节能补贴：</td>
          <td><c:choose>
              <c:when test="${af.map.is_e_subsidy eq 0}">否</c:when>
              <c:when test="${af.map.is_e_subsidy eq 1}">是</c:when>
            </c:choose></td>
        </tr>
        <tr>
          <td align="right" class="title_item">意向门店地址：</td>
          <td colspan="3">${af.map.in_stores_addr}</td>
        </tr>
        <tr>
         <td align="right" class="title_item">附件：</td>
         <td colspan="3">
           <c:forEach var="cur" items="${attachmentList}" varStatus="vs"><span>${vs.count}、<a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}" target="_blank">${cur.file_name}</a> <br />
              </span></c:forEach></td>
        </tr>
        <tr>
          <td align="right" class="title_item">个人目标与事业目标：</td>
          <td colspan="3">${af.map.target}</td>
        </tr>
        <tr>
          <td style="font-weight:900;" align="right"><strong class="fb">主要简历：</strong> <br/>
            <span style="color: red;">（按起止年月、在何单位、任何职位顺序填写）</span></td>
          <td colspan="3">${mainly_resume}</td>
        </tr>
        <tr>
          <td align="right" class="title_item">自我评价及工作业绩：</td>
          <td colspan="3">${af.map.eva_performance}</td>
        </tr>
        <c:if test="${is_admin eq 1}">
	        <tr>
	          <td align="right" class="title_item">专卖店编号：</td>
	          <td colspan="3">${af.map.zmd_sn}</td>
	        </tr>
        </c:if>
        <tr>
          <td colspan="4" style="font-weight:900;">审核</td>
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
        <tr id="zmd_sn_tr" style="display: none;">
          <td style="font-weight:900;" align="right"><strong class="fb"><span style="color:red">[必填]</span>专卖店编码：</strong></td>
          <td colspan="3"><html-el:text property="zmd_sn" styleId="zmd_sn" maxlength="20"/>
            <span style="" id="s_after"></span> &nbsp;<span id="loading" style="display:none;"><img src="${ctx}/images/ajax-loader.gif" style="vertical-align:middle; margin: 2px;" />正在处理...</span></td>
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
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">审核意见：</td>
          <td align="left" colspan="3"><html-el:textarea property="audit_opinion" styleId="audit_opinion" cols="70" rows="5" />
            <div id="info_chat_content"  style="color:#0066FF;font-size:12px;display:none"><img src="../../images/tishi.gif" style="vertical-align:middle;" /></div></td>
        </tr>
        <tr>
          <td align="center" colspan="4"><input class="but4" type="button" id="add_btn" value="提交" />
            &nbsp;
            <input class="but3" type="reset" id="add_rst" value="重置" />
            &nbsp;
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" id="btn_back" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<!-- ****** Main Frame End ****** -->
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("input[type='radio'][name='audit_status']").eq(1).attr("dataType", "Group").attr("msg", "请选择审核是否通过！");
	$("#audit_opinion").val("");
	$(".audit_status").click(function(){
		if (this.value == 2) {
			$("#zmd_sn_tr").hide();
			$("#role_tr").show();
			$("#zmd_sn_tr").val("");
			$("#zmd_sn").removeAttr("dataType");
			$("#audit_opinion").attr("dataType", "Require").attr("msg", "请填写驳回原因！");
			$("#audit_user_role").attr("dataType", "Require").attr("msg", "请选择驳回角色！");
		} else if (this.value == 1){
			$("#audit_opinion").removeAttr("dataType");
			$("#audit_user_role").removeAttr("dataType");
			$("#audit_user_role").val("");
			$("#role_tr").hide();
			if('${is_gz_user}' == 1){
				$("#zmd_sn_tr").show();
				$("#zmd_sn").attr("dataType", "Require").attr("msg", "请填写专卖店编码！");
			}
		} else {
			$("#zmd_sn_tr").hide();
			$("#zmd_sn").removeAttr("dataType");
			$("#zmd_sn_tr").val("");
		}
		resizeFrameHeight();
	});

	$("#audit_opinion").textbox({
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

	$("#zmd_sn").change(function(){
		   
		   $("span[id=__ErrorMessagePanel]").remove();
		   // if($("#zmd_sn").val().match(/^(\w|[\u4E00-\u9FA5])*$/)&& $.trim($("#zmd_sn").val()).length > 0) {
		   //  $("#add_btn").removeAttr("disabled");
		   // } else {
		   //   alert("用户名只允许为英文，下划线，数字和汉字的混合,\n请检查是否前后有空格或者其他符号"); 
		   //   $("#add_btn").attr("disabled", "true");
		   //   return false ;
		   //}

			if(this.value.length > 0){
				$("#tip_zmd_sn").remove();
				$("#add_btn").attr("disabled", "true");
				$.ajax({
					type: "POST",
					url: "KonkaXxZmdAuditUserInfoAudit.do",
					data: "method=validateZmsSn&zmd_sn=" + this.value,
					dataType: "json",
					error: function(request, settings) {alert("数据加载请求失败！"); },
					success: function(isExist) {
						$("#tip_zmd_sn").remove();
						if(isExist == 11) {
							$("#s_after").after('<span id="tip_zmd_sn" style="color:#FF0000;"><img src="${ctx}/commons/styles/themes/blue/images/reg_error.gif" />&nbsp;对不起，专卖店编号已存在！<\/span>'); 
							$("#add_btn").attr("disabled", "true");
							return;
						} else if (isExist == 0){
							$("#s_after").after('<span id="tip_zmd_sn" style="color:#5A8E4A;"><img src="${ctx}/commons/styles/themes/blue/images/reg_success.gif" />&nbsp;恭喜，专卖店编号可用！<\/span>'); 
							$("#add_btn").removeAttr("disabled");
						} 
					}
				});
				} else {
					$("#tip_zmd_sn").remove();
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
