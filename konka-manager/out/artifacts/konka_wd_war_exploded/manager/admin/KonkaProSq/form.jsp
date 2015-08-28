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
    <html-el:form action="/admin/KonkaProSq" enctype="multipart/form-data">
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
          <td ><html-el:text property="file_no_left" styleId="file_no_left" maxlength="4" style="width:20px;backgound-color:#E0E0E0;" readonly="true"/>
            <html-el:text property="file_no_middle" styleId="file_no_middle" maxlength="8" style="width:32px;backgound-color:#E0E0E0;" readonly="true"/>
            <html-el:text property="file_no_right" styleId="file_no_right" value="自动生成" maxlength="16" style="width:80px;backgound-color:#E0E0E0;" readonly="true"/>
          </td>
          <td class="title_item" align="right">投标人名称：</td>
           <td>
           		<html-el:text property="tbr_name" styleId="tbr_name"   maxlength="10" style="width:200px;" />
           </td>
        </tr>
        <tr>
          <td class="title_item" align="right"><span style="color:red">*</span>&nbsp;分公司：</td>
          <td><html-el:select property="dept_id" styleId="dept_id" disabled="${readyOnly}" >
                <html-el:option value="">请选择...</html-el:option>
                <c:forEach var="cur" items="${sybDeptInfoList}">
                  <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
                </c:forEach>
              </html-el:select>
          </td>
           <td class="title_item" align="right">区域：</td>
          <td>
              <div id="s2">
              <html-el:text property="area_name" styleId="area_name"  readonly="true" style="width:200px;" />
              </div> 
              <div style="display: none" id="s1">
          	  <html-el:select property="area_id" styleId="area_id">
                <html-el:option value="">请选择...</html-el:option>
                <c:forEach var="cur" items="${konkaXxBaseTypeList}">
                  <html-el:option value="${cur.type_id}">${cur.type_name}</html-el:option>
                </c:forEach>
              </html-el:select>
          	  <span style="color:red">*</span>
          	  </div>
          </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">负责人：</td>
          <td width="50%" >
            <html-el:text property="fzr_name" styleId="fzr_name" maxlength="10" style="width:200px;"  />
            </td>
            <td class="title_item" nowrap="nowrap" align="right">负责人电话：</td>
          <td width="50%" >
            <html-el:text property="fzr_tel" styleId="fzr_tel" maxlength="20" style="width:200px;" />
            </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">分公司联系人：</td>
          <td width="50%" >
            <html-el:text property="fgs_name" styleId="fgs_name" maxlength="20" style="width:200px;" />
            </td>
            <td class="title_item" nowrap="nowrap" align="right">分公司联系人电话：</td>
          <td width="50%" >
            <html-el:text property="fgs_tel" styleId="fgs_tel" maxlength="20" style="width:200px;" />
            </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right"> <span style="color:red">*</span>&nbsp;项目名称：</td>
          <td width="50%" >
            <html-el:text property="pro_name" styleId="pro_name" maxlength="20" style="width:200px;"  />
            </td>
            <td class="title_item" nowrap="nowrap" align="right">招标时间：</td>
          <td width="50%" >
            <fmt:formatDate var="_zb_date" value="${af.map.zb_date}" pattern="yyyy-MM-dd" />
            <html-el:text property="zb_date" value="${_zb_date}" styleId="zb_date" size="10" maxlength="10" readonly="true"  style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
            </td>
        </tr>
         <tr>
          <td class="title_item" nowrap="nowrap" align="right">项目进展情况备注：</td>
          <td width="88%" colspan="3">
            <html-el:textarea property="remark" styleId="remark" cols="5" style="width:600px;height:100px;" />
          	<div id="info_tip" style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div></td>
         </tr>
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

	$("#pro_name").attr("datatype", "Require").attr("msg", "请填写项目名称");
	$("#dept_id").attr("datatype", "Require").attr("msg", "请选择分公司");
	
	$("#remark").textbox({
		maxLength: 300,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_tip").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_tip").slideUp("normal");
	});

	$("#sb_remark").textbox({
		maxLength: 300,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_tip_1").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_tip_1").slideUp("normal");
	});

	$("#zc_remark").textbox({
		maxLength: 200,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_tip_2").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_tip_2").slideUp("normal");
	});

	

	$("#dept_id").change(function(){
		  $.ajax({
				type: "POST",
				url: "${ctx}/manager/admin/KonkaProSq.do",
				data: "method=chooseArea&dept_id=" + $(this).children('option:selected').val(),
				dataType: "json",
				success: function(area_id) {
					 if(area_id==null){
						 $("#s1").show();
						 $("#s2").hide();
						 $("#area_id").val("");
						 $("#area_name").val("");
						 $("#type").val("1");
					 }else{
						 $("#s2").show();
						 $("#s1").hide();
						 $("#type").val("2");
						 if(area_id=="10"){
						     $("#area_name").val("华东");
						     $("#area_id").val(area_id);	
						 }else if(area_id=="20"){
						     $("#area_name").val("山东");
						     $("#area_id").val(area_id);
						 }else if(area_id=="30"){
							 $("#area_name").val("东北");
							 $("#area_id").val(area_id);
						 }else if(area_id=="40"){
							 $("#area_name").val("华北");
							 $("#area_id").val(area_id);
						 }else if(area_id=="50"){
						     $("#area_name").val("华南");
						     $("#area_id").val(area_id);	
						 }else if(area_id=="60"){
						     $("#area_name").val("西南");
						     $("#area_id").val(area_id);
						 }else if(area_id=="70"){
							 $("#area_name").val("华中");
							 $("#area_id").val(area_id);
						 }else if(area_id=="80"){
							 $("#area_name").val("西北");
							 $("#area_id").val(area_id);
						 }	 
					 }
				}
			}); 
		  window.parent.resizeFrameHeight('mainFrame', 3);
	});



$("#imgCategoryAddTr").click(function (){   
		
	    var code = new Date().getTime();   //后缀
	    var div = $('#clone_div').clone(true);

	    //加上验证
        div.find("#brand_name").attr("dataType", "Require").attr("msg", "请填写品牌名称！");

	    //给控件id加上标识  
	    div.find('#brand_name').attr("id","brand_name_"+code); //品牌
	    div.find('#md_name').attr("id","md_name_"+code);//型号
	    div.find('#param').attr("id","param_"+code);//
	    div.find('#sail_money').attr("id","sail_money_"+code); //工程项目
	    div.find('#pd_name').attr("id","pd_name_"+code); //品名
	    div.find('#bd_tb_price').attr("id","bd_tb_price_"+code); //规格
	    div.find('#f_remark').attr("id","f_remark_"+code); //数量
	    
	   div.attr('id','clone_div__'+code);
	   div.appendTo($("#tbodyContent")).show();
	   window.parent.resizeFrameHeight('mainFrame', 3);
	});

	//删除一个类别
	$("img[id='imgDelTr']").each(function(){
        $(this).click(function (){
            $(this).parent().parent().remove();
            window.parent.resizeFrameHeight('mainFrame', 3);
        });
    });

	
	
	// 提交
	$("#btn_submit").click(function(){
		if($("#type").val()=="1"){
			$("#area_id").attr("datatype", "Require").attr("msg", "请选择大区");
		}
		if(Validator.Validate(this.form, 2)){
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});


});

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
		if(this.value.length == 0) this.value = "";
	});
	//this.text.selected;
}

function setOnlyDouble(obj) {
	var v = obj.value.replace(/[^\d+(\.\d+)?]/gi,'');
		obj.value=v;
}


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

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
