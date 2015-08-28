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
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/KonkaMobileCollection" enctype="multipart/form-data">
      <html-el:hidden property="coll_id" styleId="coll_id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save"/>
      <html-el:hidden property="base_num_old" styleId="base_num_old" value="${af.map.base_num}" />
      <html-el:hidden property="busi_num_old" styleId="busi_num_old" value="${af.map.busi_num}" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td nowrap="nowrap" class="title_item" align="right" width="147">存品型号：<span style="color:red">*</span></td>
          <td>
          	<html-el:select property="coll_pd_id" styleId="coll_pd_id">
          		<html-el:option value="">请选择...</html-el:option>
          		<html-el:optionsCollection name="pePdModelList" label="md_name" value="pd_id"/>
          	</html-el:select>
          		<html-el:hidden property="coll_name" styleId="coll_name"  />
          </td>
        </tr>
        
        <tr>
          <td nowrap="nowrap" class="title_item" align="right" width="147">选择仓库：<span style="color:red">*</span></td>
          <td><html-el:hidden property="storage_id" styleId="storage_id" />
				<html-el:text property="storage_name" styleId="storage_name" size="30" maxlength="20" value="${af.map.map.storage_name}" />
				<img id="select_storage" src="${ctx}/images/search.gif" style='margin: 0 0 -3px 0; cursor: pointer;' alt='选择仓库'/>		
          </td>
        </tr>
        
        <tr>
          <td nowrap="nowrap" class="title_item" align="right" width="147">选择仓位：</td>
          <td><html-el:hidden property="storage_area_id" styleId="storage_area_id" />
				<html-el:text property="storage_area_name" styleId="storage_area_name" size="30" maxlength="20" value="${af.map.map.storage_area_name}" />
				<img id="select_storage_area" src="${ctx}/images/search.gif" style='margin: 0 0 -3px 0; cursor: pointer;' alt='选择仓位'/>		
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right" width="147">初始库存：<span style="color:red">*</span></td>
          <td><html-el:text property="base_num" styleId="base_num" size="18" styleClass="webinput" maxlength="15" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right" width="147">备注：</td>
          <td><html-el:textarea property="remark" styleId="remark" cols="50" rows="7"></html-el:textarea> </td>
        </tr>
       
        <tr>
          <td>&nbsp;</td>
          <td><input class="but4" type="button" name="Submit4" value="提交" id="btn_submit" />
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();" /></td>
        </tr>
      </table>
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
	$("#base_num").attr("dataType", "Currency").attr("msg", "请正确填写初始库存");

	$('#coll_pd_id').change(function(){
		$('#coll_name').val($('#coll_pd_id').find('option:selected').text());
	});
	// 提交
	$("#btn_submit").click(function(){
		   if(Validator.Validate(this.form, 3)){
				$("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
				$("#btn_back").attr("disabled", "true");
				this.form.submit();
		   }
	});

	//选择仓库
	$("#select_storage").click(function() {
		var returnValue = window
				.showModalDialog(
						"selectStorages.do?storage_type=2&azaz=" + Math.random(),
						window,
						"dialogWidth:550px;status:no;dialogHeight:530px");
		if (returnValue != null) {
			$("#storage_name").val(returnValue.storagename);
			$("#storage_id").val(returnValue.storageid);
		};
	});
	//选择仓位
	$("#select_storage_area").click(function() {
		var storage_par_id = $('#storage_id').val();
		if(storage_par_id == ''){
			alert('请先选择仓库.');
			return false;
		}else{
			var returnValue = window
					.showModalDialog(
							"selectStorages.do?storage_type=3&storage_par_id="+storage_par_id+"&azaz=" + Math.random(),
							window,
							"dialogWidth:550px;status:no;dialogHeight:530px");
			if (returnValue != null) {
				$("#storage_area_name").val(returnValue.storagename);
				$("#storage_area_id").val(returnValue.storageid);
			}
		}
	});
});

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
