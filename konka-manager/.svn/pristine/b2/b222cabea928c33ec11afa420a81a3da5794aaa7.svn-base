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
      <html-el:form action="/admin/GcxmProj" enctype="multipart/form-data">
      <html-el:hidden property="method" value="auditSave" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="is_tj" styleId="is_tj"  />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
      	<tr>
      		<td class="title_item" align="right" width="15%">项目类型：</td>
      		<td width="35%">
      		<c:if test="${af.map.proj_type eq 1}">政府采购</c:if>
      		<c:if test="${af.map.proj_type eq 2}">酒店采购</c:if>
      		<c:if test="${af.map.proj_type eq 3}">企业采购</c:if>
      		<c:if test="${af.map.proj_type eq 4}">其他</c:if>
      		</td>
             <td class="title_item" align="right" width="15%">项目编号：</td>
             <td width="35%">
             ${af.map.proj_code}
             </td>
      	</tr> 
      	<tr>
      		<td class="title_item" align="right">项目名称：</td>
      		<td colspan="3">
      		${af.map.proj_name}
      		</td>
      	</tr>
        <tr>
          <td class="title_item" align="right">&nbsp;分公司&nbsp;：</td>
          <td>
          ${af.map.fgs_dept_name}
          </td>
           <td class="title_item" align="right">审核状态：</td>
          <td width="35%">
          <c:if test="${empty af.map.id}">未提交</c:if>
          <c:if test="${not empty af.map.id}">
              <c:if test="${af.map.info_state eq -1}">未提交</c:if>
              <c:if test="${af.map.info_state eq 0}">审核中</c:if>
              <c:if test="${af.map.info_state eq 1}">审核结束</c:if>
          </c:if>
          </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">&nbsp;<span id="s_cg">采购人</span>&nbsp;：</td>
          <td width="50%" >
          ${af.map.buyer}
          </td>
          <td class="title_item" nowrap="nowrap" align="right"><span id="s_dh">采购人电话：</span></td>
          <td width="50%" >
           ${af.map.buyer_tel}
          </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right"><span id="s_rq">报价日期：</span></td>
          <td width="50%" >
          <fmt:formatDate  value="${af.map.offer_date}" pattern="yyyy-MM-dd" />
          </td>
          <td class="title_item" nowrap="nowrap" align="right">交货日期：</td>
          <td width="50%" >
          <fmt:formatDate  value="${af.map.delivery_date}" pattern="yyyy-MM-dd" />
          </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">采购尺寸：</td>
          <td width="50%" >
          ${af.map.size}
          </td>
          <td class="title_item" nowrap="nowrap" align="right">采购数量：</td>
          <td width="50%" >
          ${af.map.buyer_num}
          </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">采购预算：</td>
          <td width="50%" >
          ${af.map.budget}
          </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">参数要求：</td>
          <td width="88%" colspan="3">
          ${af.map.memo}
          </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">&nbsp;备 注：</td>
          <td width="88%" colspan="3">
          ${af.map.remark}
          </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">&nbsp;创建人：</td>
          <td width="50%" >
          ${af.map.user_name}
          </td>
          <td class="title_item" nowrap="nowrap" align="right">创建日期：</td>
          <td width="50%" >
          <fmt:formatDate value="${af.map.create_date}" pattern="yyyy-MM-dd" /> 
          </td>
        </tr>
        <c:if test="${not empty attachmentList}">
          <tr>
            <td height="28" class="title_item" align="right">已上传的附件：</td>
            <td><ol>
                <c:forEach var="cur" items="${attachmentList}" varStatus="vs">
                  <li><a href="${ctx}/manager/admin/Download.do?method=downloadFile1&save_name=${cur.save_name}" target="_blank">${cur.file_name}</a>&nbsp;&nbsp;&nbsp;</li>
                </c:forEach>
              </ol></td>
          </tr>
        </c:if>
        <tr><td colspan="4">
        <table width="100%">
        
        <c:if test="${empty af.map.tj_model_is_empty}">
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">推荐机型1：</td>
          <td width="30%" ><input type="text" name="model_1" maxlength="125" value="${af.map.model_1}"  id="model_1" style="width: 280px; color: rgb(153, 153, 153);" class="webinput" />
          </td>
          <td class="title_item" nowrap="nowrap" align="right">参数附件1：</td>
          <td width="30%" >
           &nbsp;
          </td>
            <td width="20%"> <c:if test="${not empty af.map.id }"><a href="${ctx}/${af.map.fj_1}" target="_blank" >${af.map.fj_name_1}</a></c:if></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">推荐机型2：</td>
          <td width="30%" ><input type="text" name="model_2" maxlength="125" value="${af.map.model_2}"  id="model_2" style="width: 280px; color: rgb(153, 153, 153);" class="webinput" />
          </td >
          <td class="title_item" nowrap="nowrap" align="right">参数附件2：</td>
          <td width="30%" >
           &nbsp;
          </td >
           <td width="20%"> <c:if test="${not empty af.map.id }"><a href="${ctx}/${af.map.fj_2}" target="_blank" >${af.map.fj_name_2}</a></c:if></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">推荐机型3：</td>
          <td width="30%" ><input type="text" name="model_3" maxlength="125" value="${af.map.model_3}"  id="model_3" style="width: 280px; color: rgb(153, 153, 153);" class="webinput" />
          </td>
          <td class="title_item" nowrap="nowrap" align="right">参数附件3：</td>
          <td width="30%" >
          &nbsp;   
          </td>
           <td width="20%"><c:if test="${not empty af.map.id }">
          <a href="${ctx}/${af.map.fj_3}" target="_blank" >${af.map.fj_name_3}</a>
           </c:if></td>
        </tr>
        </c:if>
        
         <c:if test="${not empty af.map.tj_model_is_empty}">
         <tr>
          <td class="title_item" nowrap="nowrap" align="right">推荐机型1：</td>
          <td width="30%" ><input type="text" name="model_1" maxlength="125" value="${af.map.model_1}" id="model_1" style="width: 280px; color: rgb(153, 153, 153);" class="webinput" />
          </td>
          <td class="title_item" nowrap="nowrap" align="right">参数附件1：</td>
          <td width="30%" >
          <input name="file_1" type="file" id="file_1" style="width: 300px;" />
          </td>
            <td width="20%"> <c:if test="${not empty af.map.id }"><a href="${ctx}/${af.map.fj_1}" target="_blank" >${af.map.fj_name_1}</a></c:if></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">推荐机型2：</td>
          <td width="30%" ><input type="text" name="model_2" maxlength="125" value="${af.map.model_2}" id="model_2" style="width: 280px; color: rgb(153, 153, 153);" class="webinput" />
          </td >
          <td class="title_item" nowrap="nowrap" align="right">参数附件2：</td>
          <td width="30%" >
          <input name="file_2" type="file" id="file_2" style="width: 300px;" />
          </td >
           <td width="20%"> <c:if test="${not empty af.map.id }"><a href="${ctx}/${af.map.fj_2}" target="_blank" >${af.map.fj_name_2}</a></c:if></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">推荐机型3：</td>
          <td width="30%" ><input type="text" name="model_3" maxlength="125" value="${af.map.model_3}" id="model_3" style="width: 280px; color: rgb(153, 153, 153);" class="webinput" />
          </td>
          <td class="title_item" nowrap="nowrap" align="right">参数附件3：</td>
          <td width="30%" >
          <input name="file_3" type="file" id="file_3" style="width: 300px;" />   
          </td>
           <td width="20%"><c:if test="${not empty af.map.id }">
          <a href="${ctx}/${af.map.fj_3}" target="_blank" >${af.map.fj_name_3}</a>
           </c:if></td>
        </tr>
         </c:if>
        
        <tr>
        <td class="title_item2">审核结果：</td>
          <td colspan="3"><html-el:select property="audit_result" styleId="audit_result" style="width:120px;">
              <html-el:option value="1">审核通过</html-el:option>
              <html-el:option value="-1">驳回</html-el:option>
              <html-el:option value="-3">驳回至制单人</html-el:option>
            </html-el:select>&nbsp;&nbsp;
            </td>
        </tr>
        <tr class="one" id="audit_poss_tr" style="display:none;">
        	<td class="title_item2">驳回位置：</td>
        	<td colspan="3"><c:forEach var="cur" items="${nodeList}" varStatus="vs">
        			<label for="${cur.id}"><html-el:radio styleId="${cur.id}" property="node_id"  value="${cur.id}">${cur.audit_role_name}</html-el:radio></label> 
	        		<c:if test="${vs.last ne true}">
						--&gt;
					</c:if>
        	</c:forEach>
        	</td>
        </tr>
        <tr>
         <td class="title_item2">是否满足参数：</td>
          <td colspan="3"><html-el:select property="is_meet" styleId="is_meet" style="width:120px;">
              <html-el:option value="0">满足</html-el:option>
              <html-el:option value="1">不满足</html-el:option>
            </html-el:select>&nbsp;&nbsp;
            </td>
        </tr>
        <tr>
        <td class="title_item2">审核意见：</td>
          <td colspan="3">
           <html-el:textarea property="audit_idea" styleId="audit_idea" cols="5" style="width:820px;height:100px;" />
          <div id="info_tip" style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div>
            </td>
        </tr>
        </table>
        </td>
        </tr>
        <tr><td colspan="4" class="title_item" align="left">审核记录</td></tr>
        <tr>
        <td colspan="4">
        <table width="100%">
        		<tr bgcolor="pink"><td>审核人</td><td>审核时间</td><td>能否满足参数</td><td>审核结果</td><td>审批意见</td></tr>
        		<c:forEach items="${auditList}" var="cur">
        		<tr>
        			<td>${cur.map.audit_user_name}</td>
        		<td><fmt:formatDate value="${cur.audit_date}"  pattern="yyyy-MM-dd"></fmt:formatDate></td>
        		<td>
        		<c:if test="${cur.is_meet eq 0}">满足</c:if>
        		<c:if test="${cur.is_meet eq 1}">不满足</c:if>
        		</td>
        		<td>
        		<c:if test="${cur.audit_result eq 1}">审核通过</c:if>
        		<c:if test="${cur.audit_result eq -1}">驳回</c:if>
        		<c:if test="${cur.audit_result eq -2}">撤回</c:if>
        		<c:if test="${cur.audit_result eq -3}">驳回至制单人</c:if>
        		</td>
        		<td>${cur.audit_idea}</td>
        		</tr>
        		</c:forEach>
        </table>
        </td>
        </tr>
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
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=discuz"></script> 
<script type="text/javascript">//<![CDATA[
                                        
$(document).ready(function(){ 
	$("#audit_idea").textbox({   
		maxLength: 200,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_tip").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_tip").slideUp("normal");  
	});
	$("#audit_result").change(function(){
		if($(this).val() == -1){
			$("#audit_poss_tr").show();
		}else{
			$("#audit_poss_tr").hide();
		}
		//iframe高度自适应
		window.parent.resizeFrameHeight('mainFrame', 3);
	});


	// 提交btn_submit 
	$("#btn_submit").click(function(){

		var node_id = $('input:radio[name="node_id"]:checked').val();
		var audit_result = $("#audit_result").val();
		
		if(audit_result == -1 && (node_id == null || node_id == 'undefined' || node_id =="")){
			alert("请选择驳回位置！");
			return false;
		}
		
		if(audit_result == 1){
			$('input:radio[name="node_id"]').removeAttr("checked");

			<c:if test="${not empty af.map.tj_model_is_empty}">

			var file_1 = $("#file_1").val();
			var model_1 = $("#model_1").val();
			var file_2 = $("#file_2").val();
			var model_2 = $("#model_2").val();
			var file_3 = $("#file_3").val();
			var model_3 = $("#model_3").val();

			if(model_1==""&&model_2==""&&model_3==""){
				alert("3个推荐机型不能都为空，请至少维护一个推荐机型！")
				return false;
			}
			
			
			if(file_1!=""){
				if(model_1==""){
					alert("附件已经存在，请选择推荐机型1");
					return false;
				}

			}

			
			if(file_2!=""){
				if(model_2==""){
					alert("附件已经存在，请选择推荐机型2");
					return false;
				}

			}

			
			if(file_3!=""){
				if(model_3==""){
					alert("附件已经存在，请选择推荐机型3"); 
					return false; 
				}

			}

			$("#is_tj").val(1);
			</c:if>
			
		}
		
		if(Validator.Validate(this.form, 2)){
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});
	

});

function openModelChild(title){
	var url="${ctx}/manager/admin/GcxmProj.do?method=chooseModel&title="+title;
	$.dialog({
		title:  "选择推荐机型",  
		width:  625,
		height: 412, 
        lock:true ,
		content:"url:"+url
	});
}




//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
