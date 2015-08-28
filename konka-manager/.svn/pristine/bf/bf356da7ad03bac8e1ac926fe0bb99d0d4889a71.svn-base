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
<c:if test="${!empty is_add }">
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
</c:if>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
          <td>当前位置：行政办公系统&nbsp;&gt;&nbsp;费用申请</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/chengduoa/ExpenseClaims" enctype="multipart/form-data">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="file_id" styleId="file_id" />
      <html-el:hidden property="is_node" styleId="is_node" value="1"/>
      <html-el:hidden property="mod_type" styleId="mod_type" value="expense" />
      <!-- 区分是不是直接提交到流程-->
      <html-el:hidden property="send_to_process" value="" styleId="send_to_process"/>
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
          <tr>
            <td nowrap="nowrap" class="title_item">负 责 人：</td>
            <td nowrap="nowrap"><html-el:text property="apply_user_name" styleId="apply_user_name" maxlength="50" style="width:60%" value="${apply_user_name}"/>
              <span style="color:red">&nbsp;*</span></td>
            <td nowrap="nowrap" class="title_item">电　　话：</td>
            <td nowrap="nowrap"><html-el:text property="apply_user_tel" styleId="apply_user_tel" maxlength="20" style="width:155px" value="${apply_user_tel}"/></td>
          </tr>
          <tr>
            <td nowrap="nowrap" class="title_item" width="80px">文件标题：</td>
            <td nowrap="nowrap"><html-el:text property="file_title" styleId="file_title" maxlength="50" style="width:60%" />
              <span style="color:red">&nbsp;*</span></td>
            <td nowrap="nowrap" class="title_item">文件编号：</td>
            <td nowrap="nowrap"><html-el:text property="file_no_left" styleId="file_no_left" maxlength="4" style="width:20px;backgound-color:#E0E0E0;" readonly="true"/>
              <html-el:text property="file_no_middle" styleId="file_no_middle" maxlength="8" style="width:30px;backgound-color:#E0E0E0;" readonly="true"/>
              <html-el:text property="file_no_right" styleId="file_no_right" value="后四位自动生成" maxlength="16" style="width:95px;backgound-color:#E0E0E0;" readonly="true"/></td>
          </tr>
          <tr>
            <td class="title_item">申请 人：</td>
            <td nowrap="nowrap"><html-el:text property="submit_user" styleId="submit_user" maxlength="100" style="width:60%;vertical-align:middle;" readonly="true" value="${submit_user}" /></td>
            <td class="title_item">申请时间：</td>
            <td nowrap="nowrap"><fmt:formatDate value="${submit_datetime}" pattern="yyyy-MM-dd HH:mm:ss" var="_submit_datetime" />
              <html-el:text property="submit_datetime" styleId="submit_datetime" size="20" maxlength="20" readonly="true" value="${_submit_datetime}" style="width:155px;" /></td>
          </tr>
          <tr>
            <td class="title_item">审批流程：</td>
            <td nowrap="nowrap">
	           <html-el:select property="audit_node_id" styleId="audit_node_id" style="width:385px;">
	              <html-el:option value="">请选择...</html-el:option>
	              <html-el:optionsCollection name="KonkaoaFilesAuditNodeList" label="audit_node_name" value="link_id" />
	            </html-el:select>
            &nbsp;<span style="color:red">*</span>
            </td>
            <td class="title_item">时　　限：</td>
            <td><html-el:select property="time_limit" style="width:160px;">
                <html-el:option value="1">1天</html-el:option>
                <html-el:option value="2">2天</html-el:option>
                <html-el:option value="3">3天</html-el:option>
              </html-el:select></td>
          </tr>
          <tr>
            <td class="title_item">申请客户：</td>
            <td><html-el:text property="customer_name" styleId="customer_name" style="width:60%;"  maxlength="100" readonly="true" value="${customer_name }" />
              &nbsp;
              <input id="gsBTN" type='button' value='选择' onclick="getR3Shop();"/>
              <html-el:hidden property="r3_shop_id" styleId="r3_shop_id" /></td>
            <td nowrap="nowrap" class="title_item">费用总额：</td>
            <td nowrap="nowrap"><html-el:text property="column_6" styleId="column_6" style="width:120px;background:#cccccc;" maxlength="10" size="10" value="${konkaExpenseClaims.column_6}" readonly="true" />
              <span class="note">单位：元</span></td>
          </tr>
          <!-- ADD -->
          <c:if test="${!empty is_add }">
	          <tr>
	            <td class="title_item">模板名称</td>
	            <td><html-el:select property="module_name" styleId="module_name" style="width:225px;">
	                <html-el:option value="">==请选择==</html-el:option>
	                <c:forEach var="cur" items="${konkaOaModuleTypeList}">
	                  <html-el:option value="${cur.module_id}">${fn:escapeXml(cur.module_name)}</html-el:option>
	                </c:forEach>
	              </html-el:select></td>
	          </tr>
          </c:if>
          
          <tr>
            <td class="title_item">费用明细：</td>
            <html-el:hidden property="select_indexes" styleId="select_indexes"/>
            <td colspan="5"><table width="96%" border="0" align="left" cellpadding="0" cellspacing="0" id="categorys_td" style="border:#ccc 1px solid;">
                <tr>
                  <td width="200" align="left">费用类别</td>
                  <td align="center">说明</td>
                  <td width="120" align="center">数量</td>
                  <td width="120" align="center">单价</td>
                  <td width="30"  align="center"><img src="${ctx}/images/+.gif" name="imgCategoryAddTr" id="imgCategoryAddTr" style="vertical-align:middle; cursor: pointer;margin-left: 2px;" title="再添加一个" /></td>
                </tr>
                <c:if test="${empty af.map.file_id and empty af.map.submit_user }">
                  <tr>
                    <td width="200" align="left"><span id="category_index" style="display:none;">1</span>
                      <html-el:text property="c_name" readonly="true" maxlength="120" styleId="c_name_1" />
                      <input id="gsBTN" type='button' value='选择' onclick="getCategory(1);"/>
                      <html-el:hidden property="c_index" styleId="c_index_1" /></td>
                    <td align="center"><html-el:text property="c_desc" maxlength="8" styleId="c_desc_1" style="width:95%" /></td>
                    <td width="120" align="center"><html-el:text property="amount" style="width:60px;" maxlength="8" styleId="amount_1"  onkeyup="javascript:setOnlyDouble(this);" onblur="sum_money(this);" value="0"/></td>
                    <td width="120" align="center"><html-el:text property="cost" style="width:60px;" maxlength="8" styleId="cost_1" onkeyup="javascript:setOnlyDouble(this);" onblur="sum_money(this);" value="0"/>
                      <html-el:hidden styleId="total_money_1" property="total_money_1" /></td>
                    <td width="30" align="center"><img src="${ctx}/images/x.gif" style="vertical-align:middle;cursor:pointer;margin-left:2px;" id="imgDelTr" title="删除"/></td>
                  </tr>
                </c:if>
                <c:forEach items="${filesPropertyList}" var="_cur" >
                  <c:if test="${not empty _cur.map.c_name }">
                    <tr>
                      <td width="200" align="left"><span id="category_index" style="display:none;">1</span>
                        <html-el:text property="c_name" readonly="true" maxlength="120" styleId="c_name_${_cur.c_index }" value="${fn:escapeXml(_cur.map.c_name)}" />
                        <input id="gsBTN" type='button' value='选择' onclick="getCategory('${_cur.c_index }','');"/>
                        <html-el:hidden property="c_index" styleId="c_index_${_cur.c_index }" value="${_cur.c_index}" /></td>
                      <td align="center"><html-el:text property="c_desc" maxlength="8" styleId="c_desc_${_cur.id}" value="${fn:escapeXml(_cur.c_desc)}" style="width:95%" /></td>
                      <td width="120" align="center"><html-el:text property="amount" style="width:60px;" maxlength="8" styleId="amount_${_cur.id}"  onkeyup="javascript:setOnlyDouble(this);" onblur="sum_money(this);" value="${fn:escapeXml(_cur.amount)}"/></td>
                      <td width="120" align="center"><html-el:text property="cost" style="width:60px;" maxlength="8" styleId="cost_${_cur.id}"  onkeyup="javascript:setOnlyDouble(this);" onblur="sum_money(this);" value="${fn:escapeXml(_cur.cost)}"/></td>
                      <td width="30" align="center"><img src="${ctx}/images/x.gif" style="vertical-align:middle; cursor: pointer;margin-left: 2px;" id="imgDelTr" title="删除"/></td>
                    </tr>
                  </c:if>
                </c:forEach>
                <tr id="clone_div" style="display:none;">
                  <td align="left" width="200"><span id="category_index" style="display:none;"></span>
                    <html-el:text property="c_name"  readonly="true" maxlength="120" styleId="c_name"  value="${bean.c_name}" />
                    <input id="gsBTN" type='button' value='选择'/>
                    <html-el:hidden property="c_index" styleId="c_index" /></td>
                  <td align="center" ><html-el:text property="c_desc" maxlength="8" styleId="c_desc" style="width:95%"/></td>
                  <td align="center" width="120"><html-el:text property="amount" style="width:60px;" maxlength="8" styleId="amount"  onkeyup="javascript:setOnlyDouble(this);" onblur="sum_money(this);" value="0"/></td>
                  <td align="center" width="120"><html-el:text property="cost" style="width:60px;" maxlength="8" styleId="cost"  onkeyup="javascript:setOnlyDouble(this);" onblur="sum_money(this);" value="0"/>
                    <html-el:hidden property="total_money" styleId="total_money" /></td>
                  <td align="center" width="30"><img src="${ctx}/images/x.gif" style="vertical-align:middle; cursor: pointer;margin-left: 2px;" id="imgDelTr" title="删除"/></td>
                </tr>
              </table>
              <br /></td>
          </tr>
          <tr>
            <td class="title_item" style="width:100px">详细内容：</td>
            <td colspan="5"><FCK:editor instanceName="content" width="96%" height="250">
                <jsp:attribute name="value">${af.map.content}</jsp:attribute>
              </FCK:editor></td>
          </tr>
          <tr>
            <td nowrap="nowrap" class="title_item">上传附件：</td>
            <td><div id="divFileHidden" style="display: none;">
                <input name="file_hidden" type="file" id="file_hidden" style="width: 250px;" />
                <img src="../../images/x.gif" style="vertical-align:middle; cursor: pointer;" id="imgFileDelTr" title="删除"/></div>
              <div id="divFile">
                <input name="file_show" type="file" id="file_show" style="width: 250px;" />
                <img src="../../images/+.gif" style="vertical-align:middle; cursor: pointer;" id="imgFileAddTr" title="再添加一个" /></div></td>
          </tr>
          <c:if var="is_atta_edit" test="${not empty attachmentList and not empty af.map.file_id}">
            <tr>
              <td height="28" class="title_item">已上传的附件：</td>
              <td><ol>
                  <c:forEach var="cur" items="${attachmentList}" varStatus="vs">
                    <li><a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}">${cur.file_name}</a>&nbsp;&nbsp;&nbsp;<a href="#"id="att_del_${cur.id}">
                      <html-el:hidden property="copy_file_id" value="${cur.id}" />
                      <img src="../../images/x.gif" style="vertical-align:middle; cursor: pointer;" title="删除"/></a></li>
                  </c:forEach>
                </ol></td>
            </tr>
          </c:if>
          <tr>
            <td class="title_item">是否下发：</td>
            <td colspan="5"><html-el:radio property="is_forward" value="0" styleId="is_forward_0" />
              <label for="is_forward_0"> 否 </label>
              <html-el:radio property="is_forward" value="1" styleId="is_forward_1" />
              <label for="is_forward_1"> 是 </label>
              <c:set var="display" value="none" />
              <c:if test="${af.map.is_forward eq 1}">
                <c:set var="display" value="" />
              </c:if></td>
          </tr>
          <tbody id="is_forward_tr" style="display:${display};">
            <tr>
              <td class="title_item">下发用户：</td>
              <td colspan="5">
                <input type="hidden" name="receive_type_1_ids" id="receive_type_1_ids" value="${af.map.fa_ids}" />
                <input type="text" name="receive_type_1_names" id="receive_type_1_names" value="${af.map.fa_names}" readonly="readonly"  style="width:80%;vertical-align:middle;" />
                <img id="add_fa" src="${ctx}/images/search.gif" style='vertical-align:middle;cursor: pointer;' alt='选择人员' /> <br />
            </tr>
            <tr>
              <td class="title_item">下发部门：</td>
              <td colspan="5"><input type="hidden" name="receive_dept_1_ids" id="receive_dept_1_ids" value="${af.map.dept_ids}" />
                <input type="text" name="receive_dept_1_names" id="receive_dept_1_names" value="${af.map.dept_names}" readonly="readonly"  style="width:80%;vertical-align:middle;" />
                <img id="add_dept" src="${ctx}/images/search.gif" style='vertical-align:middle; cursor: pointer;' alt='选择部门' /> <br />
            </tr>
          </tbody>
        <c:if test="${af.map.file_status eq 2 and not empty af.map.file_no}">
          <c:if test="${not empty filesAuditNodeList}">
            <table width="90%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
              <tr>
                <td width="90" align="center" nowrap="nowrap" class="td_bord">审批状况</td>
                <td width="15%" align="center" nowrap="nowrap" class="td_bord">审批开始时间</td>
                <td width="15%" align="center" nowrap="nowrap" class="td_bord">审批结束时间</td>
                <td align="center" nowrap="nowrap" class="td_bord">审批意见</td>
                <td width="15%" align="center" nowrap="nowrap" class="td_bord">审批人/部门</td>
              </tr>
              <c:set var="begin_time" value="${af.map.submit_datetime}" />
              <c:forEach var="cur" items="${filesAuditNodeList}" varStatus="vs">
                <c:set var="audit_result" value="未审批" />
                <c:if test="${cur.audit_result eq 2}">
                  <c:set var="audit_result" value="<span style='color:#090;'>同意</span>" />
                </c:if>
                <c:if test="${cur.audit_result eq 1}">
                  <c:set var="audit_result" value="<span style='color:#f00;'>驳回</span>" />
                </c:if>
                <tr>
                  <td align="center">${audit_result}</td>
                  <td align="center"><c:if test="${not empty cur.audit_datetime}">
                      <fmt:formatDate value="${begin_time}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </c:if></td>
                  <td align="center"><c:if test="${not empty cur.audit_datetime}">
                      <fmt:formatDate value="${cur.audit_datetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </c:if></td>
                  <td><c:if test="${not empty cur.audit_comment}"> ${fn:escapeXml(cur.audit_comment)} </c:if></td>
                  <td>${cur.audit_user}</td>
                </tr>
              </c:forEach>
            </table>
          </c:if>
        </c:if>
      </table>
      <div align="center" style="padding:15px; 0 0;">
        <input class="but4" type="button" name="Submit4" value="暂存" id="btn_submit_save" style="align:center;"/>
        <input class="but4" type="button" name="Submit4" value="提交" id="btn_submit_process" style="align:center;"/>
        <input class="but5" type="button" name="Submit5" value="返回" style="align:center;" onclick="history.back();" />
      </div>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/pinyin.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<c:if test="${!empty is_add }">
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
</c:if>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
    var acceptUploadFileExts = "7z, aiff, asf, avi, bmp, csv, doc,docx, fla, flv, gif, gz, gzip, jpeg, jpg, mid, mov, mp3, mp4, mpc, mpeg, mpg, ods, odt, pdf, png, ppt,pptx, pxd, qt, ram, rar, rm, rmi, rmvb, rtf, sdc, sitd, swf, sxc, sxw, tar, tgz, tif, tiff, txt, vsd, wav, wma, wmv, xls,xlsx, xml, zip, exe";
    <c:if test="${af.map.file_status ne -1}">
    $("#file_title"  ).attr("dataType", "Require").attr("msg", "请填写标题！"); 
    $("input[name=is_forward]:first").attr("dataType", "Group").attr("min", "1").attr("msg", "是否下发必须选择！");
    $("input[name=audit_user_name]:visible").attr("dataType", "Require").attr("msg", "请选择审批人！");
   
    $("#file_show"   ).attr("dataType", "Filter" ).attr("msg", "您上传的文件格式不受支持，请上传正确格式的文本、音频、视频。").attr("require", "false").attr("accept", acceptUploadFileExts);
    $("#file_hidden" ).attr("dataType", "Filter" ).attr("msg", "您上传的文件格式不受支持，请上传正确格式的文本、音频、视频。").attr("require", "false").attr("accept", acceptUploadFileExts);

    $("#column_1").attr("dataType", "Require").attr("msg", "请填写广告受益客户！");
    $("#apply_user_name").attr("dataType", "Require").attr("msg", "请填写负责人！");
    $("#audit_node_id").attr("dataType", "Require").attr("msg", "审批流程必须选择");
    //$("#r3_shop_id"	 ).attr("dataType", "Require").attr("msg", "请选择申请网点！");
    </c:if>
    
    $("#imgFileAddTr").click(function (){
        $("#divFileHidden").clone().find("#file_hidden").attr("name", "file_" + new Date().getTime()).end().appendTo($("#divFile")).show();
        $("img[id='imgFileDelTr']").each(function(){
            $(this).click(function (){
                $(this).parent().remove();
            });
        });
    });
    
  //增加一个类别
	$("#imgCategoryAddTr").click(function (){   
	    var code = new Date().getTime();   //后缀
	    var div = $('#clone_div').clone(true);

	    //加上验证
        div.find("#c_name").attr("dataType", "Require").attr("msg", "请选择商品");
        div.find("#cost").attr("dataType", "Currency").attr("msg", "请正确填写金额");
        div.find("#amount").attr("dataType", "Currency").attr("msg", "请正确填写数量");
        

	    //给控件id加上标识  
	    div.find('#c_name').attr("id","c_name_"+code); //类别名称
	    div.find('#c_index').attr("id","c_index_"+code); //类别ID
	    div.find('#c_desc').attr("id","c_desc_"+code); //类别ID
	    div.find('#amount').attr("id","amount_"+code); //费用金额
	    div.find('#cost').attr("id","cost_"+code); //费用金额
	    div.find('#total_money').attr("id","total_money_"+code);//总金额
	    div.find('#imgDelTr').attr("onclick","sum_money();");
	    
	    div.find('#gsBTN').bind('click',function(){getCategory(code,'');});//选择商品
	    div.attr('id','clone_div__'+code);
	    
	    div.appendTo($("#categorys_td")).show();
	    changeIndex();

	    resizeFrameHeight();
	});

	//删除一个类别
	$("img[id='imgDelTr']").each(function(){
        $(this).click(function (){
            $(this).parent().parent().remove();
            changeIndex();
            sum_money();

            resizeFrameHeight();
        });
    });

    $("a[id^='att_del_']").click(function() {
    	  var a = this;
    	   if(!confirm('确实要删除此附件？')) return;
    	    $.post("ExpenseClaims.do", { method : "deleteFile", id : $(this).attr("id").replace(/att_del_/g, '')}, function(success) {
    	   if (success){alert("恭喜您，删除附件成功!");$(a).parent().remove();} else alert(" 很抱歉，删除附件出错!"); 
    	  });
     });
    
    <c:if test="${af.map.is_forward ne 1}">
    $("#is_forward_0").trigger("click");
    </c:if>


  //文件编号生成---新增
    <c:if test="${empty af.map.file_no}">
       var fgs_dept_name = '${af.map.fgs_dept_name}';
       var yymm = '${af.map.yymm}';
       var file_first = '';
       if(fgs_dept_name != ''){
    	  for(var i=0; i < fgs_dept_name.length; i++){
    		  var s = fgs_dept_name.charAt(i);
    		  file_first = file_first + ucfirstLetter(CC2PY(s));
    	  }
       }else{
    	    file_first = 'KK';
       }
       $("#file_no_left").val(file_first);
       $("#file_no_middle").val(yymm);
    </c:if>
    //文件编号初始化---编辑
    <c:if test="${not empty af.map.file_no}">
       var file_no_str = '${af.map.file_no}';
       $("#file_no_left").val(file_no_str.substr(0,2));
       $("#file_no_middle").val(file_no_str.substr(2,4));
       $("#file_no_right").val(file_no_str.substr(6));
    </c:if>
    
	 // 提交
    $("#btn_submit_process").click(function(){
    	var result = new Array();
        $("[name = oausergroup]:checkbox").each(function () {
            if ($(this).is(":checked")) {
                result.push($(this).attr("value"));
            }
        });
        //提交到流程
        $('#send_to_process').val("send_to_process");
        $('#receive_group_ids').val(result.join(","));
        if(Validator.Validate(this.form, 1)){
            $("#btn_submit_save").attr("disabled", "true");
            $("#btn_submit_process").attr("value", "提交中...").attr("disabled", "true");
            $("#btn_reset").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
            this.form.submit();
        }
    });
    
    //保存 
    $("#btn_submit_save").click(function(){
    	var result = new Array();
        $("[name = oausergroup]:checkbox").each(function () {
            if ($(this).is(":checked")) {
                result.push($(this).attr("value"));
            }
        });
      	//不是提交到流程 
        $('#send_to_process').val("");
        $('#receive_group_ids').val(result.join(","));
        if(Validator.Validate(this.form, 1)){
            $("#btn_submit_process").attr("disabled", "true");
            $("#btn_submit_save").attr("value", "保存 ...").attr("disabled", "true");
            $("#btn_reset").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
            this.form.submit();
        }
    });

   $("#add_fa").click(function(){
		var returnValue = window.showModalDialog("DiaLog.do?azaz=" + Math.random() + "&method=selectUser&selectedUsersID=" + $("#receive_type_1_ids").val() + "&selectedUsers=" + getInputContent("receive_type_1_names")  , window, "dialogWidth:610px;status:no;dialogHeight:415px;scroll:no");
		if (returnValue != null) {
			var names = returnValue.user_link_names;
			names = names.substring(0, names.length - 1);//alert(names +  "\n" + returnValue.user_link_ids);
			$("#receive_type_1_names").val(names);
			$("#receive_type_1_ids"  ).val(returnValue.user_link_ids);
		}
	});	
	
	$("#add_dept").click(function(){
		var returnValue = window.showModalDialog("DiaLog.do?azaz=" + Math.random() + "&method=selectDept&selectedDeptsID=" + $("#receive_dept_1_ids").val() + "&selectDepts=" + getInputContent("receive_dept_1_names")  , window, "dialogWidth:610px;status:no;dialogHeight:415px;scroll:no");
		if (returnValue != null) {
			var names = returnValue.user_link_names;
			names = names.substring(0, names.length - 1);//alert(names +  "\n" + returnValue.user_link_ids);
			// alert(names);
			$("#receive_dept_1_names").val(names);
			$("#receive_dept_1_ids"  ).val(returnValue.user_link_ids);
		}
	});	
	
// 	$("#add_coun").click(function(){
// 		var returnValue = window.showModalDialog("DiaLog.do?azaz=" + Math.random() + "&method=selectUser&selectedUsersID=" + $("#countersign_ids").val() + "&selectedUsers=" + getInputContent("countersign_names"), window, "dialogWidth:610px;status:no;dialogHeight:415px;scroll:no");
// 		if (returnValue != null) {
// 			var names = returnValue.user_link_names;
// 			names = names.substring(0, names.length - 1);
// 			$("#countersign_names").val(names);
//  	  		$("#countersign_ids").val(returnValue.user_link_ids);
// 		}
// 	});	

    $("#is_forward_0").click(function(){
        $("#is_forward_tr").hide();
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
    	
    	//群组
    	$("#receive_group_ids").val("");
        
    	resizeFrameHeight();
    });
    $("#is_forward_1").click(function(){$("#is_forward_tr").show();
    	resizeFrameHeight();
    });

    //模板
    var is_add = '${is_add}';
    if(is_add == 2){
	    $("#module_name").multiselect({
	    	noneSelectedText: '==请选择==',
	    	selectedList: 1,
	    	multiple: false,
	    	minWidth:220,
	    	click: function(event, ui){
	           if(ui.value != ""){
	               showContent(ui.value);
	               getExpenseClaims(ui.value);
	           } else {
	                //模板
	        		var oEditor = FCKeditorAPI.GetInstance('content');  
	        		oEditor.SetHTML(""); 
	
	        		//模板值
	        		$("#column_6").val('');
	        		$("#select_indexes").val('');
	        		
	        		$("tr[id^= 'clone_div__']").each(function(){
	   					$(this).children().remove();
	   				});
	           }
	    	}
	    }).multiselectfilter();
    }
});

function showContent(module_id){
	$.ajax({
		type: "POST",
		url: "${ctx}/manager/oa/KonkaOaModuleType.do",
		data: "method=getContent&module_id=" + module_id,
		dataType: "json",
		error: function(request, settings) {alert("数据加载请求失败！"); },
		success: function(data) {

			var oEditor = FCKeditorAPI.GetInstance('content');  
			oEditor.SetHTML(data[0]["content"]); 
		}
	});
} 

function getExpenseClaims(module_id){
	$.ajax({
		type: "POST",
		url: "${ctx}/manager/oa/KonkaOaModuleType.do",
		data: "method=getExpenseClaims&module_id=" + module_id,
		dataType: "json",
		error: function(request, settings) {alert("数据加载请求失败！"); },
		success: function(data) {

			$("tr[id^= 'clone_div__']").each(function(){
				$(this).children().remove();
			});

			$("#column_6").val('');
			
		    //新增所有列
			var listitem=new Array();
			listitem = eval(data);
			
			$("#select_indexes").val('');
			
			var indexes='0';
			for (var i = 0; i < listitem.length ; i++) {
				//indexes += listitem[i]["c_index"] + ',';
				var code = new Date().getTime()+listitem[i]["c_index"];   //后缀
				var div = $('#clone_div').clone(true);

				 $("[id^=c_index_]").each(function(){
			        	if(''!= $(this).val()){
			        		indexes+=$(this).val()+',';
			        	}
					});
				
			    //加上验证
		        div.find("#c_name").attr("dataType", "Require").attr("msg", "请选择商品");
		        div.find("#cost").attr("dataType", "Currency").attr("msg", "请正确填写金额");
		        div.find("#amount").attr("dataType", "Currency").attr("msg", "请正确填写数量");
		        

			   	//赋值
			    div.find('#c_name').attr("value",listitem[i]["c_name"]);
			    div.find('#c_index').attr("value",listitem[i]["c_index"]);
			     
			    //给控件id加上标识  
			    div.find('#c_name').attr("id","c_name_"+code); //类别名称
			    div.find('#c_index').attr("id","c_index_"+code); //类别ID
			    div.find('#c_desc').attr("id","c_desc_"+code); //类别ID
			    div.find('#amount').attr("id","amount_"+code); //费用金额
			    div.find('#cost').attr("id","cost_"+code); //费用金额
			    div.find('#total_money').attr("id","total_money_"+code);//总金额
			    div.find('#imgDelTr').attr("onclick","sum_money();");
			    
			    div.find('#gsBTN').bind('click',function(){getCategory(code,module_id);});//选择商品
			    div.attr('id','clone_div__'+code);

			    div.appendTo($("#categorys_td")).show();
			    changeIndex();

			    resizeFrameHeight();
			}
			$("#select_indexes").val(indexes);
		}
	});
} 


function removeAuditUser(obj){
    $(obj).parent().remove();
    a_u_n--;
    var n = 1;
    $("#num", $audits).each(function(){$(this).text(n++);});
}

function selectUser(obj) {
    var $p = $(obj).parent();
    var returnValue = window.showModalDialog("DiaLog.do?azaz=" + Math.random() + "&method=selectUser&selectype=signal&selectedUsersID=" +  $("#audit_user_id", $p).val(), window, "dialogWidth:610px;status:no;dialogHeight:415px;scroll:no");
    if (returnValue != null) { 
        var names = returnValue.user_link_names, ids = returnValue.user_link_ids;
        ids = ids.substring(0, ids.length - 1);
        names = names.substring(0, names.length - 1);
        $("#audit_user_name", $p).val(names);
        $("#audit_user_id", $p).val(ids);
    }
}

function getAuditUsers(obj) {
    var users = "${userInfo.id},";
    $("input[name=audit_user_id]").each(function(){
        if($(this).val().length != 0 && $(this).val() != obj.value) {
            users += $(this).val() + ",";
        }
    });
    return users.substring(0, users.length - 1);
}

function getInputContent(id){
    var v = $("#" + id).val();
    if(v.length != 0) {
        return v + ",";
    }
    
    return v;
}

function setOnlyNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		//if(this.value.length == 0) this.value = "0";
	});
}

function changeIndex(){
	var n = 1;
	$('#category_index','#categorys_td').each(function(){
	    $(this).text(n++);
	});
}

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
		obj.value=v;
}
//选择类别
function getCategory(code,module_id) { 
	    var indexes='0';
	        $("[id^=c_index_]").each(function(){
	        	if(''!= $(this).val()){
	        		indexes+=$(this).val()+',';
	        	}
			});
	        $("#select_indexes").val(indexes);
	    var returnValue = window
	            .showModalDialog(
	                    "SelectKonkaOaCategory.do?selectype=signal&selects="+$("#c_index_"+code).val()+"&module_id="+module_id+"&azaz=" + Math.random(), //+"&select_indexes="+ $("#select_indexes").val() 
	                    window,
	                    "dialogWidth:600px;status:no;dialogHeight:530px");
	    if (null != returnValue) {
	        $("#c_index_"+code).val(returnValue.ids);
	        $("#c_name_"+code).val(returnValue.names);
	        // $("#goods_price__"+code).val(returnValue.purchase_price * returnValue.deduction_rate / 100);
	    };
}

//选择申请网点
function getR3Shop() { 
	    var returnValue = window
	            .showModalDialog(
	                    "SelectKonkaR3Shop.do?selectype=signal&selects=" + $("#r3_shop_id").val() + "&azaz=" + Math.random(),
	                    window,
	                    "dialogWidth:600px;status:no;dialogHeight:530px");
	    if (null != returnValue) {
	        $("#r3_shop_id").val(returnValue.ids);
	        $("#customer_name").val(returnValue.names);
	    };
}

//计算总金额
function sum_money(){
	var price = 0;//单价
    var num = 0; //数量
    var total = 0; //总金额
	$("[id^=amount_]").each(function(){	
			total=total+parseFloat($(this).val()) * parseFloat($('#' + ($(this).attr("id")).replace("amount_","cost_")).val());	
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