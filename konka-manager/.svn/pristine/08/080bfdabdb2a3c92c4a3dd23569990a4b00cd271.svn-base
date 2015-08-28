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
    <html-el:form action="/zmd/KonkaXxZmdAuditUserInfo" styleClass="form_cust" enctype="multipart/form-data">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="dept_id" value="${af.map.dept_id}" />
      <html-el:hidden property="zmd_user_id" styleId="zmd_user_id" value="${af.map.zmd_user_id}" />
      <html-el:hidden property="mainly_resume" styleId="mainly_resume" value="${af.map.mainly_resume}" />
      <html-el:hidden property="edit_value" value="${af.map.edit_value}" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td colspan="4" style="font-weight:bold;font-size:18px;height:40px;text-align:center;border-bottom:none;">提交专卖店客户资质申请</td>
        </tr>
        <tr>
          <td colspan="2" align="left" class="title_item">分公司：<span style="color: red;">${dept_name}</span></td>
          <td align="right" class="title_item">提交日期：</td>
          <td align="left" class="title_item"><c:choose>
              <c:when test="${empty af.map.add_date}"></c:when>
              <c:otherwise>
                <fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd" var="add_date" />
              </c:otherwise>
            </c:choose>
            <html-el:text property="add_date" styleId="add_date" value="${add_date}" readonly="true" styleClass="webinput" style="cursor:pointer;"  title="点击选择日期" /></td>
        </tr>
        <tr>
          <td colspan="4" style="font-weight:900;"><strong class="fb">个人信息</strong></td>
        </tr>
        <tr>
          <td width="20%" align="right" class="title_item">姓 名：</td>
          <td width="20%"><html-el:text styleId="user_name" property="user_name" maxlength="10"/></td>
          <td width="20%" align="right" class="title_item">性 别：</td>
          <td width="30%"><html-el:select property="sex" styleId="sex">
              <html-el:option value="">-请选择-</html-el:option>
              <html-el:option value="0">-保密-</html-el:option>
              <html-el:option value="1">-男-</html-el:option>
              <html-el:option value="2">-女-</html-el:option>
              <html-el:option value="3">-不详-</html-el:option>
            </html-el:select></td>
        </tr>
        <tr>
          <td width="13%" align="right" class="title_item">出生年月：</td>
          <td width="14%"><c:choose>
              <c:when test="${empty af.map.birthday}"></c:when>
              <c:otherwise>
                <fmt:formatDate value="${af.map.birthday}" pattern="yyyy-MM-dd" var="birthday" />
              </c:otherwise>
            </c:choose>
            <html-el:text property="birthday" styleId="birthday" value="${birthday}" readonly="true" styleClass="webinput" style="cursor:pointer;"  title="点击选择日期" /></td>
          <td align="right" class="title_item">教育背景：</td>
          <td><html-el:text  styleId="edu_bg" property="edu_bg" maxlength="20"/></td>
        </tr>
        <tr>
          <td align="right" class="title_item">婚姻状况：</td>
          <td><html-el:select property="marriage" styleId="marriage">
              <html-el:option value="0">-未婚-</html-el:option>
              <html-el:option value="1">-已婚-</html-el:option>
            </html-el:select></td>
          <td align="right" class="title_item">准备投入的资金、资源：</td>
          <td><html-el:text styleId="resources" property="resources" maxlength="100"/></td>
        </tr>
        <tr>
          <td align="right" class="title_item">是否有自有门店：</td>
          <td><html-el:select property="is_stores" styleId="is_stores">
              <html-el:option value="0">-否-</html-el:option>
              <html-el:option value="1">-是-</html-el:option>
            </html-el:select></td>
          <td align="right" class="title_item">自有门店地址：</td>
          <td><html-el:text styleId="stores_addr" property="stores_addr" maxlength="100"/></td>
        </tr>
        <tr>
          <td align="right" class="title_item">上一个工作单位：</td>
          <td><html-el:text styleId="last_unit" property="last_unit" maxlength="100"/></td>
          <td align="right" class="title_item">上一个工作职务：</td>
          <td><html-el:text styleId="last_post" property="last_post" maxlength="100"/></td>
        </tr>
        <tr>
          <td align="right" class="title_item">从事销售行业年限：</td>
          <td><html-el:text styleId="sell_work_year" property="sell_work_year" maxlength="20"/></td>
          <td align="right" class="title_item">彩电从业年限：</td>
          <td><html-el:text styleId="tv_wor_year" property="tv_wor_year" maxlength="20"/></td>
        </tr>
        <tr>
          <td align="right" class="title_item">通讯地址：</td>
          <td><html-el:text styleId="com_addr" property="com_addr" maxlength="100"/></td>
          <td align="right" class="title_item">邮政编码：</td>
          <td><html-el:text styleId="post_code" property="post_code" maxlength="16"/></td>
        </tr>
        <tr>
          <td align="right" class="title_item">电子邮箱：</td>
          <td><html-el:text styleId="email" property="email" maxlength="30"/></td>
          <td align="right" class="title_item">联系电话：</td>
          <td><html-el:text styleId="tel" property="tel" maxlength="50"/></td>
        </tr>
        <tr>
          <td colspan="4" style="font-weight:900;"><strong class="fb">门店信息</strong></td>
        </tr>
        <tr>
          <td align="right" class="title_item">工商注册时间：</td>
          <td><c:choose>
              <c:when test="${empty af.map.reg_date}"></c:when>
              <c:otherwise>
                <fmt:formatDate value="${af.map.reg_date}" pattern="yyyy-MM-dd" var="reg_date" />
              </c:otherwise>
            </c:choose>
            <html-el:text property="reg_date" styleId="reg_date" value="${reg_date}" readonly="true" styleClass="webinput" style="cursor:pointer;"  title="点击选择日期" /></td>
          <td align="right" class="title_item">注册资本（万元）：</td>
          <td><html-el:text styleId="reg_money" property="reg_money" maxlength="12"/></td>
        </tr>
        <tr>
          <td align="right" class="title_item">营业执照经营范围：</td>
          <td><html-el:text styleId="business_scope" property="business_scope" maxlength="100"/></td>
          <td align="right" class="title_item">营业执照注册号：</td>
          <td><html-el:text styleId="reg_num" property="reg_num" maxlength="20"/></td>
        </tr>
        <tr>
          <td align="right" class="title_item">目前正在经营品牌：</td>
          <td><html-el:text styleId="business_brand" property="business_brand" maxlength="100"/></td>
          <td align="right" class="title_item">正在经营门店的地址：</td>
          <td><html-el:text styleId="ope_sto_addr" property="ope_sto_addr" maxlength="100"/></td>
        </tr>
        <tr>
          <td align="right" class="title_item">是否已有R3编码：</td>
          <td><html-el:select property="is_r3" styleId="is_r3">
              <html-el:option value="0">-否-</html-el:option>
              <html-el:option value="1">-是-</html-el:option>
            </html-el:select></td>
          <td align="right" class="title_item">能否参加节能补贴：</td>
          <td align="left"><html-el:select property="is_e_subsidy" styleId="is_e_subsidy">
              <html-el:option value="0">-否-</html-el:option>
              <html-el:option value="1">-是-</html-el:option>
            </html-el:select></td>
        </tr>
        <tr>
          <td align="right" class="title_item">意向门店地址：</td>
          <td colspan="3"><html-el:text styleId="in_stores_addr" property="in_stores_addr" maxlength="100"/></td>
        </tr>
         <tr>
            <td nowrap="nowrap" class="title_item">上传附件：</td>
            <td colspan="3"><div id="divFileHidden" style="display: none;">
                <input name="file_hidden" type="file" id="file_hidden" style="width: 250px;" />
                <img src="../../images/x.gif" style="vertical-align:middle; cursor: pointer;" id="imgDelTr" title="删除"/></div>
              <div id="divFile">
                <input name="file_show" type="file" id="file_show" style="width: 250px;" />
                <img src="../../images/+.gif" style="vertical-align:middle; cursor: pointer;" id="imgAddTr" title="再添加一个" /></div></td>
          </tr>
          <c:if var="is_atta_edit" test="${not empty attachmentList}">
            <tr>
              <td class="title_item">已上传的附件：</td>
              <td colspan="3"><ol>
                  <c:forEach var="cur" items="${attachmentList}" varStatus="vs">
                    <li><a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}">${cur.file_name}</a>&nbsp;&nbsp;&nbsp;<a href="#" id="att_del_${cur.id}">
                      <img src="../../images/x.gif" style="vertical-align:middle; cursor: pointer;" title="删除"/></a></li>
                  </c:forEach>
                </ol></td>
            </tr>
          </c:if>
        <tr>
        	<td colspan="4"><span style="color:red;">提示：文件上传可以上传的格式如下：doc,docx,ppt,pptx,rar,txt,xls,xlsx,zip,exe；图片的上传，可以直接打包成rar或者zip格式上传。</span></td>
        </tr>   
        <tr>
          <td align="right" class="title_item">个人目标与事业目标：</td>
          <td colspan="3"><html-el:textarea styleId="target" property="target" style="width:65%;height:80px;" /></td>
        </tr>
        <tr>
          <td style="font-weight:900;" align="right"><strong class="fb">主要简历：</strong> <br/>
            <span style="color: red;">（按起止年月、在何单位、任何职位顺序填写）</span></td>
          <td colspan="3"><html-el:textarea styleId="self_e" property="self_e" value="${af.map.mainly_resume}" style="width:65%;height:80px;" /></td>
        </tr>
        <tr>
          <td align="right" class="title_item">自我评价及工作业绩：</td>
          <td colspan="3"><html-el:textarea styleId="eva_performance" property="eva_performance" style="width:65%;height:80px;" /></td>
        </tr>
        <tr>
          <td align="center" colspan="4"><input class="but4" type="button" id="add_btn" value="提交" />
            &nbsp;
            <input class="but3" type="reset" id="add_rst" value="重置" />
            &nbsp;<input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<!-- ****** Main Frame End ****** -->
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var acceptUploadFileExts = "7z,doc,docx,ppt,pptx,rar,txt,xls,xlsx,zip, exe";

	$("#user_name").attr("dataType", "Require").attr("msg", "请填写姓名！");
	$("#post_code").attr("dataType", "Zip").attr("msg", "邮政编码格式错误").attr("dataType",false);
	$("#eva_performance").attr("dataType", "Limit").attr("max", "200").attr("msg", "自我评价及工作业绩不能超过200个文字");
	$("#self_e").attr("dataType", "Limit").attr("max", "200").attr("msg", "主要简历不能超过200个文字");
	$("#target").attr("dataType", "Limit").attr("max", "200").attr("msg", "个人目标与事业目标不能超过200个文字");

	$("#birthday").datepicker();
	$("#reg_date").datepicker();
	$("#birthday").datepicker('option','yearRange','1950:2020');
	$("#reg_date").datepicker('option','yearRange','1950:2020');;
	$("#add_date").datepicker();
	$("#reg_money").attr("focus",setOnlyNum);

	$("#file_show"   ).attr("dataType", "Filter" ).attr("msg", "您上传的文件格式不受支持，请上传正确格式的文本、音频、视频。").attr("require", "false").attr("accept", acceptUploadFileExts);
	$("#file_hidden" ).attr("dataType", "Filter" ).attr("msg", "您上传的文件格式不受支持，请上传正确格式的文本、音频、视频。").attr("require", "false").attr("accept", acceptUploadFileExts);

	//附件删除
	$("a[id^='att_del_']").click(function() {
	  	  var a = this;
	  	   if(!confirm('确实要删除此附件？')) return;
	  	    $.post("KonkaXxZmdAuditUserInfo.do", { method : "deleteFile", id : $(this).attr("id").replace(/att_del_/g, '')}, function(success) {
	  	   if (success){alert("恭喜您，删除附件成功!");$(a).parent().remove();} else alert(" 很抱歉，删除附件出错!"); 
	  	  });
	});	
	
	//附件新增
	 $("#imgAddTr").click(function (){
        $("#divFileHidden").clone().find("#file_hidden").attr("name", "file_" + new Date().getTime()).end().appendTo($("#divFile")).show();
        resizeFrameHeight();
        $("img[id='imgDelTr']").each(function(){
            $(this).click(function (){
                $(this).parent().remove();

                resizeFrameHeight();
            });
        });
    });
	        
	var edit_value = '${af.map.edit_value}';
	if(null != edit_value && edit_value != ""){
		$("#user_name").attr("disabled",true);
		$("#tel").attr("disabled",true);
	}
	
	$("#add_btn").click(function(){
		 var ss = $("#self_e").val();   
	     var str = ss.replace(/\n/g, "<br />");
	     $("#mainly_resume").val(str);

	     var isSubmit = Validator.Validate(this.form, 3);
	     if (isSubmit) {
				$(":button").attr("disabled", "true");
				$(":reset").attr("disabled", "true");
				$(this.form).submit();
			}
	});
});

function resizeFrameHeight(offset, min_height) {
	$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
}

function setOnlyNum() {
	$(this).css("ime-mode", "disabled").attr("t_value", "").attr("o_value", "");
	$(this).bind("dragenter",function(){
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
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
