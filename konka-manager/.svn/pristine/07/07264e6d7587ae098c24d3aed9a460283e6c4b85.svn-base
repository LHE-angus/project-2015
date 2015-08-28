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
        <html-el:form action="/admin/SelfEventCenter" enctype="multipart/form-data">
          <html-el:hidden property="queryString" styleId="queryString" />
          <html-el:hidden property="method" styleId="method" value="save" />
          <html-el:hidden property="mod_id" styleId="mod_id" />
          <html-el:hidden property="id" styleId="id" />
          <html-el:hidden property="is_agent" />
          <html-el:hidden property="agent_user_id" />
          <html-el:hidden property="file_type" />
          <html-el:hidden property="is_node" style="is_node" value="1" />
          <div style="height:10px;"></div>
          <c:set var="oper_name" value="审批" />
          <table width="90%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
          <tr>
              <td width="10%" align="center" nowrap="nowrap">负&nbsp;责&nbsp;人</td>
              <td >${fn:escapeXml(af.map.apply_user_name)}</td>
              <td width="10%" align="center" nowrap="nowrap">电 &nbsp;&nbsp; 话</td>
              <td width="20%" align="left" nowrap="nowrap">${fn:escapeXml(af.map.apply_user_tel)}</td>
              <td width="10%" align="center" nowrap="nowrap">编　　号</td>
              <td width="15%">${fn:escapeXml(af.map.file_no)}</td>
            </tr>
            <tr>
              <td align="center">审&nbsp;批&nbsp;人</td>
              <td><div id="audits">
                  <c:set var="audit_node_num" value="2" />
                  <c:if test="${not empty _filesAuditNodeList}">
                    <c:forEach var="cur" items="${_filesAuditNodeList}" varStatus="vs"> ${cur.audit_user}
                      <c:if test="${vs.last ne true}">，</c:if>
                    </c:forEach>
                  </c:if>
                </div></td>   
              <td align="center" nowrap="nowrap">申&nbsp;请&nbsp;人</td>
              <td align="left">${af.map.submit_user}</td>
              <td align="center" nowrap="nowrap">申请时间</td>
              <td nowrap="nowrap" align="left"><fmt:formatDate value="${af.map.submit_datetime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
            </tr>
           <!-- 提交文件 -->
           <c:if test="${af.map.file_type eq 0 }">
           <tr>
              <td align="center">标 &nbsp;&nbsp; 题</td>
              <td colspan="3">${fn:escapeXml(af.map.file_title)}</td> 
              <td align="center">时　　限</td>
              <td>${fn:escapeXml(af.map.time_limit)}天</td>
            </tr>
            <tr>
              <td align="center">请示内容</td>
              <td height="200" colspan="5" valign="top" style="table-layout:fixed;word-break:break-all;word-wrap:break-word;">${af.map.content}
                <c:forEach var="cur" items="${attachmentList}" varStatus="vs"><span>${vs.count}、<a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}" target="_blank">${cur.file_name}</a> <br />
                  </span></c:forEach></td>
            </tr>
           </c:if>
            <!-- 费用申请 -->
            <c:if test="${af.map.file_type ne 0 }">
            <tr style="display: none;">
              <td align="center">标 &nbsp;&nbsp; 题</td>
              <td>${fn:escapeXml(af.map.file_title)}</td> 
              <td align="center">申请网点</td>
              <td>${fn:escapeXml(shop_name)}</td>
              <td align="center">时　　限</td>
              <td>${fn:escapeXml(af.map.time_limit)}天</td>
            </tr>
            <tr style="display: none;">
               <td align="center">费用总额</td>
               <td colspan="5"><html-el:text property="column_6" styleId="column_6" style="width:120px;background:#cccccc;" maxlength="10" size="10" value="${af.map.column_6}" readonly="true" /></td>
            </tr>
            <c:if test="${not empty filesPropertyList}">
            <tr style="display: none;">
             <td width="10%" align="center" nowrap="nowrap">费用类别</td>
              <td colspan="5"><table width="96%" border="0" align="left" cellpadding="0" cellspacing="0" id="categorys_td" style="border:#ccc 1px solid;">
                <tr>
                  <td colspan="4"></td>
                  <c:forEach items="${propertyList}" var="cur" step="${fn:length(filesPropertyList)}">
                  <td align="center" colspan="2">${cur.map.real_name }</td>
                  </c:forEach>
                  <td colspan="2" align="center" >审批</td>
                </tr>
                <tr>
                  <td align="left">费用类别</td>
                  <td align="center">说明</td>
                  <td align="center" width="25">数量</td>
                  <td align="center" width="35">单价</td>
                  <c:forEach items="${propertyList}" var="cur" step="${fn:length(filesPropertyList)}">
                  <td align="center" width="25">数量</td>
                  <td align="center" width="35">单价</td>
                  </c:forEach>
                  <td align="center" width="25">数量</td>
                  <td align="center" width="35">单价</td>
                </tr>
                <c:forEach items="${filesPropertyList}" var="_cur" >
                <tr>
                  <td align="left">${fn:escapeXml(_cur.map.c_name)}<html-el:hidden property="c_index" styleId="c_index_1" value="${_cur.c_index}" /></td>
                  <td align="center">${fn:escapeXml(_cur.c_desc)}</td>
                  <td align="right">${fn:escapeXml(_cur.amount)}</td>
                  <td align="right"><fmt:formatNumber pattern="0.00" value="${_cur.cost}" /></td>
                  <c:forEach items="${_cur.map.appList}" var="ct">
                 	 <td align="right"><fmt:formatNumber pattern="0" value="${ct.amount}" /></td>
                 	 <td align="right"><fmt:formatNumber pattern="0.00" value="${ct.cost}" /></td>
                  </c:forEach>
                  <td><html-el:text property="app_amount_${_cur.c_index}" styleId="app_amount_${_cur.c_index}" maxlength="8" size="5" onkeyup="javascript:setOnlyNum(this);" value="${_cur.map.app_amount} " onblur="sum_money(this);"/></td>
                  <td><html-el:text property="app_money_${_cur.c_index}" styleId="app_money_${_cur.c_index}" maxlength="8" size="5" onkeyup="javascript:setOnlyDouble(this);" value="${_cur.map.app_money}" onblur="sum_money(this);"/></td>
                </tr>
                </c:forEach>
            </table><br /></td>
            </tr>
            </c:if>
            <tr>
              <td align="center">详细内容</td>
              <td height="150" colspan="5" valign="top" style="table-layout:fixed;word-break:break-all;word-wrap:break-word;">${af.map.content}
              <c:forEach var="cur" items="${attachmentList}" varStatus="vs"><span>${vs.count}、<a href="${ctx}/${cur.save_path}" target="_blank">${cur.file_name}</a> <br />
              </span></c:forEach>
              </td>
            </tr>
            </c:if>
            <c:if var="is_huiqian" test="${af.map.file_status ne -1 and af.map.file_status ne 1 and af.map.file_status ne 0}">
            <tr style="display:${display};">
              <td align="center">下发范围</td>
              <td colspan="5">${af.map.fa_names} </td>
            </tr>
            </c:if>
            <c:if test="${!is_huiqian}">
            <tr style="display: none;">
              <td align="center">是否下发</td>
              <td colspan="5"><html-el:radio property="is_xiafa" value="0" styleId="is_xiafa_0" />
                <label for="is_xiafa_0"> 否 </label>
                <html-el:radio property="is_xiafa" value="1" styleId="is_xiafa_1" />
                <label for="is_xiafa_1"> 是 </label>
                <c:set var="display" value="none" />
                <c:if test="${af.map.is_forward eq 1}">
                  <c:set var="display" value="" />
                </c:if></td>
            </tr>
            <tbody id="is_xiafa_tr" style="display: none;">
              <tr>
                <td align="center">下发用户</td>
                <td colspan="5"><input type="hidden" name="receive_type_1_ids" id="receive_type_1_ids" value="${af.map.fa_ids}" />
                  <input type="text" name="receive_type_1_names" id="receive_type_1_names" value="${af.map.fa_names}" readonly="readonly"  style="width:95%;vertical-align:middle;" />
                  <img id="add_fa" src="${ctx}/images/search.gif" style='vertical-align:middle;cursor: pointer;' alt='选择人员' /> <br />
              </tr>
              <tr>
                <td align="center">下发部门</td>
                <td colspan="5"><input type="hidden" name="receive_dept_1_ids" id="receive_dept_1_ids" value="${af.map.dept_ids}" />
                  <input type="text" name="receive_dept_1_names" id="receive_dept_1_names" value="${af.map.dept_names}" readonly="readonly"  style="width:95%;vertical-align:middle;" />
                  <img id="add_dept" src="${ctx}/images/search.gif" style='vertical-align:middle; cursor: pointer;' alt='选择部门' /> <br />
              </tr>
            </tbody>
            </c:if>
            <tr>
              <td colspan="6" height="5"></td>
            </tr>
            <tr>
              <td colspan="3" align="center">${oper_name}意见</td>
              <td align="center">${oper_name}签名</td>
              <td colspan="2" align="center" >${oper_name}时间</td>
            </tr>
            <tr>
              <td colspan="3" align="center"><html-el:textarea property="audit_comment" styleId="audit_comment"  rows="3" cols="80" style="width:95%"></html-el:textarea></td>
              <td align="center">${userInfo.real_name}</td>
              <td colspan="2" align="center" ><fmt:formatDate value="${af.map.now}" pattern="yyyy-MM-dd HH:mm:ss" var="_now" />
                <html-el:hidden property="audit_datetime" styleId="audit_datetime" value="${_now}" />
                ${_now}</td>
            </tr>
          </table>
          <table width="90%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
              <tr>
                <td nowrap="nowrap" class="title_item" align="center" width="70">审批结果</td>
                <td><html-el:radio property="audit_result" value="2" styleId="audit_result_2"  />
                  <label for="audit_result_2" style="color:#090;font-weight:bold;">同意</label>
                  <html-el:radio property="audit_result" value="1" styleId="audit_result_1" />
                  <label for="audit_result_1" style="color:#f00;font-weight:bold;">驳回</label>
                   <!-- 费用申请 -->
                   <!-- 
                 	<c:if test="${af.map.file_type ne 0 }">
                   		<html-el:radio property="audit_result" value="0" styleId="audit_result_3" />
                   		<label for="audit_result_3" style="color:#00f;font-weight:bold;">不审批，直接转发给其他人审批</label>
                	</c:if>
                 -->
                  </td>
              </tr>
               <!-- 费用申请 -->
              <!-- 
            <c:if test="${af.map.file_type ne 0 }">
              <tr>
                <td nowrap="nowrap" align="center" class="title_item">是否转发</td>
                <td><table width="100%" border="0" cellspacing="0" cellpadding="0" style="border:0px;">
                    <tr style="border:0px;">
                      <td width="100" style="border:0px;"><html-el:radio property="is_forward" value="0" styleId="is_forward_0" />
                        <label for="is_forward_0"> 否 </label>
                        <html-el:radio property="is_forward" value="1" styleId="is_forward_1" />
                        <label for="is_forward_1"> 是 </label></td>
                      <td style="border:0px;"><div style="padding:5px 2px;display:none;" id="is_forward_div">审批人：
                          <input type="hidden" name="audit_user_id" id="audit_user_id" value="${cur.audit_user_id}" />
                          <input type="text" name="audit_user_name" id="audit_user_name" readonly="readonly" value="${cur.audit_user}" />
                          <img onclick="selectUser(this);" src="${ctx}/images/search.gif" style='margin: 0 0 -6px 0; cursor: pointer;' alt='选择人员' title="选择人员" /> </div></td>
                    </tr>
                  </table></td>
              </tr>
           </c:if>
           -->
            </table>
            <br/>
          <c:if test="${not empty filesAuditNodeList}">
          <table width="90%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
              <tr>
                <td width="70" align="center" nowrap="nowrap">审批状况</td>
                <td width="130" align="center" nowrap="nowrap" >审批开始时间</td>
                <td width="130" align="center" nowrap="nowrap">审批结束时间</td>
                <td align="center" nowrap="nowrap">审批意见</td>
                <td width="140" align="center" nowrap="nowrap" >审批人/部门</td>
              </tr>
              <c:forEach var="cur" items="${filesAuditNodeList}" varStatus="vs">
                <c:set var="i" value="${vs.count}" />
                <c:if test="${cur.audit_result eq 2}">
                  <c:set var="audit_result" value="同意" />
                </c:if>
                <c:if test="${cur.audit_result eq 1}">
                  <c:set var="audit_result" value="<span style='color:#f00;'>驳回</span>" />
                </c:if>
                <c:if test="${cur.audit_result eq -1}">
                  <c:set var="audit_result" value="<span style='color:#f00;'>转发</span>" />
                </c:if>
                <c:if var="is_vs_last" test="${vs.last}">
                	<c:set var="begin_time" value="${af.map.submit_datetime}" />
                </c:if>
                <c:if test="${!is_vs_last}">
                	<c:set var="begin_time" value="${filesAuditNodeList[i].audit_datetime}" />
                </c:if>
                <tr>
                  <td align="center" width="130" >${audit_result}</td>
                  <td align="center" width="130" ><fmt:formatDate value="${begin_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                  <td align="center"><fmt:formatDate value="${cur.audit_datetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                  <c:set var="begin_time" value="${cur.audit_datetime}" />
                  </td>
                  <td>${fn:escapeXml(cur.audit_comment)}</td>
                  <td align="center" width="140" >${cur.audit_user}</td>
                </tr>
              </c:forEach>
            </table>
          </c:if>
          <br/>
          <div align="center" style="padding:15px; 0 0;"><input class="but4" type="button" name="Submit4" id="btn_submit" value="提交" />
            <input class="but5" type="button" name="Submit5"  value="返回" onclick="history.back();return false;" /></div>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#audit_comment" ).attr({"dataType":"LimitB","max":"500","msg":"填写${oper_name}意见不能超过500个字符"});
	$("input[name=audit_result]:first").attr("dataType", "Group").attr("msg", "请选择审批结果");
	$("#audit_result_2").attr("checked", true);
	$("#is_forward_0").attr("checked", true);
	
	<c:if test="${af.map.file_type ne 0 }">
	$("[id^=app_amount_]").attr("dataType", "Integer").attr("msg", "请正确填写数量");
	$("[id^=app_money_]").attr("dataType", "Double").attr("msg", "请正确填写金额");
	</c:if>
	
 	// 提交
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 1)){
			if (confirm("是否提交审批流程?")) {
	            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
	            $("#btn_reset").attr("disabled", "true");
	            $("#btn_back").attr("disabled", "true");
				this.form.submit();
			}
		}
	});
	
	<c:if test="${!is_huiqian}">
	$("#is_xiafa_0").trigger("click");
    <c:if test="${af.map.is_forward eq 1}">
    	$("#is_xiafa_1").trigger("click");
    </c:if>
    $("#is_xiafa_0").click(function(){
    	$("#is_xiafa_tr").hide();
        
    	//人员
        $("#receive_type_1_ids").val("");
    	$("#receive_type_1_names").val("");
    	$("#receive_type_2_ids").val("");
    	$("#receive_type_3_ids").val(""); 
    	//部门
    	$("#receive_dept_1_ids").val("");
    	$("#receive_dept_1_names").val("");
    	$("#receive_dept_2_ids").val("");
    	$("#receive_dept_3_ids").val(""); 
        resizeFrameHeight();	
     });
    $("#is_xiafa_1").click(function(){
        $("#is_xiafa_tr").show();
        resizeFrameHeight();
    });
    </c:if>
	
	$("#is_forward_0").click(function(){
		setForwardDivHide();
		resizeFrameHeight();
	});
	
	$("#is_forward_1").click(function(){
		setForwardDivShow();
		 resizeFrameHeight();
	});
	
	$("#audit_result_1").click(function(){notForwardOtherPeople();});
	$("#audit_result_2").click(function(){$("#is_forward_0, #is_forward_1").removeAttr("disabled");});
	$("#audit_result_3").click(function(){forwardOtherPeople();});
	
	$("#add_fa").click(function(){
		var returnValue = window.showModalDialog("DiaLog.do?azaz=" + Math.random() + "&method=selectUser&selectedUsersID=" + $("#receive_type_1_ids").val() + "&selectedUsers=" + getInputContent("receive_type_1_names") , window, "dialogWidth:545px;status:no;dialogHeight:390px;scroll:no");
		if (returnValue != null) {
			var names = returnValue.user_link_names;
			names = names.substring(0, names.length - 1);//alert(names +  "\n" + returnValue.user_link_ids);
			$("#receive_type_1_names").val(names);
			$("#receive_type_1_ids"  ).val(returnValue.user_link_ids);
		}
	});	
	
	$("#add_dept").click(function(){
		var returnValue = window.showModalDialog("DiaLog.do?azaz=" + Math.random() + "&method=selectDept&selectedDeptsID=" + $("#receive_dept_1_ids").val() + "&selectDepts=" + getInputContent("receive_dept_1_names"), window, "dialogWidth:545px;status:no;dialogHeight:390px;scroll:no");
		if (returnValue != null) {
			var names = returnValue.user_link_names;
			names = names.substring(0, names.length - 1);//alert(names +  "\n" + returnValue.user_link_ids);
			// alert(names);
			$("#receive_dept_1_names").val(names);
			$("#receive_dept_1_ids"  ).val(returnValue.user_link_ids);
		}
	});	
})

function setOnlyNum(obj) {
	var v = obj.value.replace(/[^\d-]/gi,'');
	if(v==0){
		obj.value='';
	}else{
		obj.value=v;
	}
}
function setOnlyDouble(obj) {
	var v = obj.value.replace(/[^\d+(\.\d+)?-]/gi,'');
	if(v==0){
		obj.value='';
	}else{
		obj.value=v;
	}
}

function selectUser(obj) {
	var $p = $(obj).parent();
	var returnValue = window.showModalDialog("DiaLog.do?azaz=" + Math.random() + "&method=selectUser&selectype=signal&selectedUsersID=" + $("#audit_user_id").val() + "&selectedUsers=" + getInputContent("audit_user_name"), window, "dialogWidth:545px;status:no;dialogHeight:390px;scroll:no");
	if (returnValue != null) {
		var names = returnValue.user_link_names, ids = returnValue.user_link_ids;
		ids = ids.substring(0, ids.length - 1);
		names = names.substring(0, names.length - 1);
		$("#audit_user_name", $p).val(names);
		$("#audit_user_id", $p).val(ids);
	}
}

function forwardOtherPeople(){
	$("#is_forward_0").attr("disabled", "disabled");
	$("#is_forward_1").attr("checked", true);
	setForwardDivShow();
}
function notForwardOtherPeople(){
	$("#is_forward_1").attr("disabled", "disabled");
	$("#is_forward_0").attr("checked", true);
	setForwardDivHide();	
}
function setForwardDivShow(){
	$("#is_forward_div").show();
	$("#audit_user_name").attr("dataType", "Require").attr("msg", "请选择审批人");
}
function setForwardDivHide(){
	$("#is_forward_div").hide();
	$("#audit_user_name").removeAttr("dataType");
}

function getInputContent(id){
    var v = $("#" + id).val();
    if(v.length != 0) {
        return v + ",";
    }
    
    return v;
}

//计算总金额
function sum_money(){
	var price = 0;//单价
    var num = 0; //数量
    var total = 0; //总金额
    $("[id^=app_amount_]").each(function(){
		total+=parseFloat($(this).val()) * parseFloat($('#' + ($(this).attr("id")).replace("app_amount_","app_money_")).val());
	});
	
    $('#column_6').val(total.toFixed(2));
}

function resizeFrameHeight(offset, min_height) {
	// frame id can be found in page /konka-wd/WebContent/manager/admin/Frames2/indexFrame.jsp, and search 'iframe' to get.
	$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>