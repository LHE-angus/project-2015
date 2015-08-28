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
<c:if test="${!empty is_add}">
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
</c:if>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/KonkaProSqAudit" enctype="multipart/form-data">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="file_state" styleId="file_state"/>
      <html-el:hidden property="type" styleId="type" value="0"/>
      <html-el:hidden property="queryString" />
      <c:set var="readyOnly" value="${empty af.map.id?false:true}"  />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td class="title_item" align="right">文件编号：</td>
          <td >
               ${af.map.file_no}
          </td>
          <td class="title_item" align="right">投标人名称：</td>
           <td>
           		${fn:escapeXml(af.map.tbr_name)}
           </td>
        </tr>
        <tr>
          <td class="title_item" align="right">分公司：</td>
          <td>
             ${dept_name}
          </td>
          <td class="title_item" align="right">区域：</td>
          <td>
          	  <c:if test="${af.map.area_id eq 10}">华东</c:if>
          	  <c:if test="${af.map.area_id eq 20}">山东</c:if>
          	  <c:if test="${af.map.area_id eq 30}">东北</c:if>
          	  <c:if test="${af.map.area_id eq 40}">华北</c:if>
              <c:if test="${af.map.area_id eq 50}">华南</c:if>
          	  <c:if test="${af.map.area_id eq 60}">西南</c:if>
          	  <c:if test="${af.map.area_id eq 70}">华中</c:if>
          	  <c:if test="${af.map.area_id eq 80}">西北</c:if>
          </td>
        </tr>
         <tr>
          <td class="title_item" nowrap="nowrap" align="right">负责人：</td>
          <td width="50%" >
             ${af.map.fzr_name}
            </td>
            <td class="title_item" nowrap="nowrap" align="right">负责人电话：</td>
          <td width="50%" >
             ${af.map.fzr_tel}
            </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">分公司联系人：</td>
          <td width="50%" >
           ${af.map.fgs_name}
            </td>
            <td class="title_item" nowrap="nowrap" align="right">分公司联系人电话：</td>
          <td width="50%" >
            ${af.map.fgs_tel}
            </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">项目名称：</td>
          <td width="50%" >
           ${af.map.pro_name}
            </td>
            <td class="title_item" nowrap="nowrap" align="right">招标时间：</td>
          <td width="50%" >
            <fmt:formatDate value="${af.map.zb_date}" pattern="yyyy-MM-dd HH:mm" />
            </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">报备人：</td>
          <td width="50%" >
           ${af.map.add_user_name}
            </td>
        </tr>
         <tr>
          <td class="title_item" nowrap="nowrap" align="right">项目进展情况备注：</td>
          <td width="88%" colspan="3">
          	${af.map.remark}
          </td>
        </tr>
         <c:if test="${af.map.is_support eq 0}">
        <tr>
        <td class="title_item" nowrap="nowrap" align="right">是否需要总部支持：</td>
         <td align="left" colspan="3">是</td> 
        <tr>
        <tr>
        <td class="title_item" nowrap="nowrap" align="right">支持内容：</td>
          <td width="88%" colspan="3">
          	${af.map.support_content}
          </td>
        </tr>
        </c:if>
        
        
         <c:if test="${not empty af.map.id && af.map.pro_state ne 0}">
           <tr>
          <td class="title_item" nowrap="nowrap" align="right">所需设备及要求：</td>
          <td width="88%" colspan="3">
            <html-el:textarea property="sb_remark" styleId="sb_remark" cols="5" style="width:600px;height:100px;" />
          	<div id="info_tip_1" style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div></td>
         </tr>
         <tr>
          <td class="title_item" nowrap="nowrap" align="right">分公司（经销商）所需支持及其他说明：</td>
          <td width="88%" colspan="3">
            <html-el:textarea property="zc_remark" styleId="zc_remark" cols="5" style="width:600px;height:100px;" />
          	<div id="info_tip_2" style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div></td>
         </tr>
          <tr>
        	 <td colspan="4" style="font-weight:900;" align="center"><strong class="fb">竞争对手信息:</strong></td>
         </tr>
         <tr>
        	<td colspan="4" width="100%">
	        	 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2" id="categorys_td">
	              	<tr class="tabtt1" style="height: 28px;">
	              	  <td width="15%" align="center" nowrap="nowrap">品牌</td>
	              	  <td width="10%" align="center" nowrap="nowrap">型号</td>
	              	  <td width="10%" align="center" nowrap="nowrap">参数</td>
	              	  <td width="10%" align="center" nowrap="nowrap">市场价</td>
	              	  <td width="10%" align="center" nowrap="nowrap">本地投标价</td>
	              	  <td width="10%" align="center" nowrap="nowrap">备注</td>
	              	  <td width="5%" align="center"><img src="${ctx}/images/+.gif" name="imgCategoryAddTr" id="imgCategoryAddTr" style="vertical-align:middle; cursor: pointer;" title="再添加一个" /></td>
	              	</tr>
	              	<tbody id="tbodyContent" class="rtable2">
	              	<c:forEach items="${fighterInfoList}" var="cur">
	              	<tr >
	              	  	<td><html-el:text property="brand_name" value="${cur.brand_name}" style="width:200px;" styleId="brand_name_${_cur.id}" maxlength="30" /></td>
	              	  	<td><html-el:text property="md_name" value="${cur.md_name}" style="width:100px;" styleId="md_name_${_cur.id}" maxlength="30" /></td>
	              	  	<td><html-el:text property="param" value="${cur.param}" style="width:100px;" maxlength="20" styleId="param_${_cur.id}"   /></td>
	              	  	<td><html-el:text property="sail_money" value="${cur.sail_money}" style="width:80px;" styleId="sail_money_${_cur.id}" maxlength="10" onchange="javascript:setOnlyDouble(this);" /></td>
	              	  	<td><html-el:text property="bd_tb_price" value="${cur.bd_tb_price}" style="width:80px;" styleId="bd_tb_price_${_cur.id}" maxlength="10" onchange="javascript:setOnlyDouble(this);" /></td>
	              	  	<td><html-el:text property="f_remark" value="${cur.f_remark}" style="width:200px;" maxlength="20" styleId="f_remark_${_cur.id}"  /></td>
	              	  	<td align="center"><img src="${ctx}/images/x.gif" style="vertical-align:middle; cursor: pointer;margin-left: 2px;" id="imgDelTr" title="删除"/></td>
	              	</tr>
	              	</c:forEach>
		            </tbody>
	              	<tr id="clone_div" style="display:none;">
	              	  	<td><html-el:text property="brand_name" style="width:200px;" styleId="brand_name" maxlength="30" /></td>
	              	  	<td><html-el:text property="md_name" style="width:80px;" styleId="md_name" maxlength="30" /></td>
	              	  	<td><html-el:text property="param" style="width:80px;" maxlength="20" styleId="param"   /></td>
	              	  	<td><html-el:text property="sail_money" style="width:80px;" styleId="sail_money" maxlength="10" onchange="javascript:setOnlyDouble(this);" /></td>
	              	  	<td><html-el:text property="bd_tb_price" style="width:80px;" styleId="bd_tb_price" maxlength="10" onchange="javascript:setOnlyDouble(this);" /></td>
	              	  	<td><html-el:text property="f_remark" style="width:200px;" maxlength="12" styleId="f_remark"  /></td>
	              	  	<td align="center"><img src="${ctx}/images/x.gif" style="vertical-align:middle; cursor: pointer;margin-left: 2px;" id="imgDelTr" title="删除"/></td>
	              	</tr>
	            </table>
        	</td>
          </tr>
         <tr>
          <td class="title_item" nowrap="nowrap" align="right">项目状态：</td>
          <td width="88%" colspan="3">
            <html-el:radio property="pro_state" value="2">已完结</html-el:radio> 
            <html-el:radio property="pro_state" value="3">已取消</html-el:radio>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>注意事项：如果项目没有完结或者取消，请不要选择</span>
          </td>
         </tr>
        </c:if>
        
       
        
        
         <tr>
          <td class="title_item" nowrap="nowrap" align="right"><span style="color:red">*</span>&nbsp;审核状态：</td>
          <td align="left" colspan="3"><label style="cursor:pointer">
              <html-el:radio property="file_status" styleClass="file_status" styleId="file_status3" value="3">审核通过</html-el:radio>
            </label>
            <label style="cursor:pointer">
              <html-el:radio property="file_status" styleClass="file_status" styleId="file_status4" value="4">驳回</html-el:radio>
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
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">审核意见：</td>
          <td align="left" colspan="3"><html-el:textarea property="audit_comment" styleId="audit_comment" cols="70" rows="5" />
            <div id="info_chat_content"  style="color:#0066FF;font-size:12px;display:none"><img src="../../images/tishi.gif" style="vertical-align:middle;" /></div></td>
        </tr>
        
        <c:if test="${audit_role_id eq 8001}">
         <tr id="is_support_tr" style="display: none;">
          <td class="title_item" nowrap="nowrap" align="right"><span style="color:red">*</span>&nbsp;是否需要总部支持：</td>
          <td align="left" colspan="3"><label style="cursor:pointer">
              <html-el:radio property="is_support" styleClass="is_support" styleId="is_support" value="0">是</html-el:radio>
            </label>
            <label style="cursor:pointer">
              <html-el:radio property="is_support" styleClass="is_support" styleId="is_support" value="1">否</html-el:radio>
            </label></td>
        </tr>
        <tr id="support_tr" style="display: none;">
          <td class="title_item" nowrap="nowrap" align="right">支持内容：</td>
          <td align="left" colspan="3"><html-el:textarea property="support_content" styleId="support_content" cols="70" rows="5" />
            <div id="info_support_content"  style="color:#0066FF;font-size:12px;display:none"><img src="../../images/tishi.gif" style="vertical-align:middle;" />
            </div>
          </td>
        </tr>
        </c:if>
        <tr>
          <td>&nbsp;</td>
          <td align="center" colspan="3" ><label>
            <input class="but4" type="button"  id="btn_submit" value="提交" />
            <input class="but5" type="button"  id="btn_back" value="返回" onclick="history.back();return false;" />
            </label></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/pinyin.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
$("input[type='radio'][name='file_status']").eq(1).attr("dataType", "Group").attr("msg", "请选择审核是否通过！");
	
	$(".file_status").click(function(){
		if (this.value == 4) {
			$("#role_tr").show();
			$("#is_support_tr").hide();
			$("#support_content").removeAttr("dataType");
			$("#support_tr").hide();
			$("#audit_comment").attr("dataType", "Require").attr("msg", "请填写驳回原因！");
			$("#audit_user_role").attr("dataType", "Require").attr("msg", "请选择驳回角色！");
		} else if (this.value == 3){
		    $("#is_support_tr").show();
			$("#audit_comment").removeAttr("dataType");
			$("#audit_user_role").removeAttr("dataType");
			$("#audit_user_role").val("");
			$("#role_tr").hide();
		} 
		resizeFrameHeight();
	});	

	$("#audit_comment").val("");
	
	
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
	
	//选择是否需要总部支持
	
	//$("input[type='radio'][name='is_support']").eq(1).attr("dataType", "Group").attr("msg", "请选择是否需要总部支持！");
	$(".is_support").click(function(){
		if (this.value == 0) {        // 0是1否
		    $("#support_tr").show();
			$("#support_content").attr("dataType", "Require").attr("msg", "请填写支持内容！");
		} else if (this.value == 1){
			$("#support_content").removeAttr("dataType");
			
			$("#support_tr").hide();
		} 
		resizeFrameHeight();
	});	
	//$("#support_content").val("");
	$("#support_content").textbox({
		maxLength: 200,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/styles/jxc/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_support_content").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
			resizeFrameHeight();
		}
	}).blur(function() {
		$("#info_support_content").slideUp("normal");
		resizeFrameHeight();
	});
	
	  
	$("#btn_submit").click(function(){
				
		if(Validator.Validate(this.form, 2)){
			$(this.form).submit();
			$("#btn_submit").attr("disabled", "true");
		} else {
			$("#btn_submit").removeAttr("disabled");
			return false;
		}
	});
	
	
});
function resizeFrameHeight(offset, min_height) {
	$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
}
<c:if test="${not empty af.map.file_no}">
   var file_no_str = '${af.map.file_no}';
   $("#file_no_left").val(file_no_str.substr(0,2));
   $("#file_no_middle").val(file_no_str.substr(2,4));
   $("#file_no_right").val(file_no_str.substr(6));
</c:if>

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>