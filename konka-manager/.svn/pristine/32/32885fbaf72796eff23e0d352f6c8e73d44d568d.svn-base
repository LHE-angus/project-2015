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
    <html-el:form action="/admin/KonkaActInfo" enctype="multipart/form-data">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="act_id" styleId="act_id" />
      <html-el:hidden property="dept_id" styleId="dept_id" value="${af.map.dept_id}" />
      <html-el:hidden property="queryString" />
      <c:set var="readyOnly" value="${empty af.map.act_id?false:true}"  />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td class="title_item" align="right">活动编号：</td>
          <td ><html-el:text property="file_no_left" styleId="file_no_left" maxlength="4" style="width:20px;backgound-color:#E0E0E0;" readonly="true"/>
            <html-el:text property="file_no_middle" styleId="file_no_middle" maxlength="8" style="width:32px;backgound-color:#E0E0E0;" readonly="true"/>
            <html-el:text property="file_no_right" styleId="file_no_right" value="自动生成" maxlength="16" style="width:80px;backgound-color:#E0E0E0;" readonly="true"/>
          </td>
          <td class="title_item" align="right">活动标题：</td>
           <td>
           		<html-el:text property="title" styleId="title"  maxlength="20" style="width:200px;" />
           </td>
        </tr>
        <tr>
          <td class="title_item" align="right">分公司：</td>
           <td>
           		<html-el:text property="dept_name" styleId="dept_name" value="${af.map.dept_name}"  maxlength="20" style="width:200px;" readonly="true" />
           </td>
           <td class="title_item" align="right">上报人：</td>
          <td>
              <html-el:text property="add_user_name" styleId="add_user_name"  value="${af.map.add_user_name}" maxlength="20" style="width:200px;" readonly="true" />
          </td>
        </tr>
        <tr>
         <td class="title_item" nowrap="nowrap" align="right"><font style="color:red;">*</font>活动开始时间：</td>
          <td width="50%" >
            <fmt:formatDate var="_start_date" value="${af.map.start_date}" pattern="yyyy-MM-dd" />
            <html-el:text property="start_date" value="${_start_date}" styleId="start_date" size="10" maxlength="10" readonly="true"  style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
            </td>
           <td class="title_item" nowrap="nowrap" align="right"><font style="color:red;">*</font>活动结束时间：</td>
          <td width="50%" >
            <fmt:formatDate var="_end_date" value="${af.map.end_date}" pattern="yyyy-MM-dd" />
            <html-el:text property="end_date" value="${_end_date}" styleId="end_date" size="10" maxlength="10" readonly="true"  style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
           </td>
        </tr>
        <tr>
          <td height="28" nowrap="nowrap" class="title_item" align="right"><font style="color:red;">*</font>客户R3编码：</td>
          <td><c:if test="${empty af.map.r3_code}"><html-el:text readonly="true" property="r3_code" styleId="r3_code" style="width:150px;" size="30" maxlength="30" /></c:if>
          <c:if test="${not empty af.map.r3_code}">${af.map.r3_code}<html-el:hidden property="r3_code"  styleId="r3_code" value="${af.map.r3_code}" /> </c:if></td>
          <td nowrap="nowrap" class="title_item" align="right">客户名称：</td>
          <td><c:if test="${empty af.map.r3_code}"><html-el:text readonly="true" property="c_name" styleId="c_name" size="30" maxlength="30" /></c:if>
          <c:if test="${not empty af.map.r3_code}"><c:if test="${empty af.map.c_name}"><html-el:text property="c_name" styleId="c_name" size="30" maxlength="30" /> </c:if>
        	<c:if test="${not empty af.map.c_name}">${af.map.c_name}<html-el:hidden property="c_name" value="${af.map.c_name}" /></c:if>
          </c:if></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">客户电话：</td>
          <td width="50%">
            <html-el:text property="c_link_name" styleId="c_link_name" maxlength="40" style="width:200px;"  />
          </td>
          <td class="title_item" nowrap="nowrap" align="right"><font style="color:red;">*</font>活动类型：</td>
          <td width="50%">
           	 <html-el:select property="type_id" styleId="type_id">
            	<html-el:option value="">--请选择--</html-el:option>
            	<html-el:option value="30010">县级</html-el:option>
            	<html-el:option value="30020">乡镇联合</html-el:option>
            </html-el:select>
          </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">活动目标（万元）：</td>
          <td width="50%" >
            <html-el:text property="target_money" styleId="target_money" maxlength="10" style="width:200px;"  />
          </td>
          <td class="title_item" nowrap="nowrap" align="right"><font style="color:red;">*</font>活动达成（万元）：</td>
          <td width="50%" >
            <html-el:text property="done_money" styleId="done_money" maxlength="10" style="width:100px;" readonly="true"  />
            &nbsp;<input class="but4" type="button"  id="btn_submit_2" value="查询" onclick="selectSaleDatas()" />
          </td>
        </tr>
         <tr>
          <td class="title_item" nowrap="nowrap" align="right">备注：</td>
          <td width="88%" colspan="3">
            <html-el:textarea property="memo" styleId="memo" cols="5" style="width:600px;height:100px;" />
          	<div id="info_tip" style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div></td>
         </tr>
         <tr>
          <td class="title_item" nowrap="nowrap" align="right">销售情况：</td>
          </tr>
          <tr>
          <td colspan="4" align="center">
          		<table id="t1" class="rtable3">
          		</table>
          </td>
          </tr>
          <c:if test="${not empty af.map.act_id}">
          <tr>
            <td height="28" class="title_item" align="right">零售数据：</td>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
               <tr class="tabtt1">
		           <td width="5%" nowrap="nowrap" align="right">零售机型</td>
		           <td width="5%" nowrap="nowrap"align="right" >数量</td>
		           <td width="8%" nowrap="nowrap"align="right" >单价</td>
		           <td width="5%" nowrap="nowrap" align="right">总金额</td>
        	   </tr>
        	  <c:forEach var="cur1" items="${entityList}" varStatus="vs">
              <tr>
	              <td align="left" nowrap="nowrap" >${cur1.model_name}</td>
	              <td align="left" nowrap="nowrap">${cur1.num}</td>
	              <td align="left" nowrap="nowrap">${cur1.single_price}</td>
	              <td align="left" nowrap="nowrap">${cur1.all_price}</td>
              </tr>
             </c:forEach> 
       		 </table>
            </td>
          </tr>
          </c:if>
         <tr>
            <td nowrap="nowrap" class="title_item" align="right">活动照片：</td>
            <td><div id="divFileHidden" style="display: none;">
                <input name="file_hidden" type="file" id="file_hidden" style="width: 250px;" />
                <img src="../../images/x.gif" style="vertical-align:middle; cursor: pointer;" id="imgDelTr" title="删除"/></div>
              <div id="divFile">
                <input name="file_show" type="file" id="file_show" style="width: 250px;" />
                <img src="../../images/+.gif" style="vertical-align:middle; cursor: pointer;" id="imgAddTr" title="再添加一个" /></div>
               <c:if test="${not empty attachmentList}">
          <tr>
            <td height="28" class="title_item" align="right">已上传的照片：</td>
            <td><ol>
                <c:forEach var="cur" items="${attachmentList}" varStatus="vs">
                  <li><a href="${ctx}/manager/admin/Download.do?method=downloadFile1&save_name=${cur.save_name}" target="_blank">${cur.file_name}</a>&nbsp;&nbsp;&nbsp;<a href="#" id="att_del_${cur.id}">删除</a></li>
                </c:forEach>
              </ol></td>
            </tr>
           </c:if>
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
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#start_date").attr("dataType", "Require").attr("msg", "请选择活动开始时间！");
	$("#end_date").attr("dataType", "Require").attr("msg", "请选择活动开始时间！");
	$("#done_money").attr("dataType", "Require").attr("msg", "请选择活动达成！");
	$("#r3_code").attr("dataType", "Require").attr("msg", "请选择r3编码！");
	$("#type_id").attr("dataType", "Require").attr("msg", "请选择活动类型！");
	$("#file_show").attr("dataType", "Filter" ).attr("msg", "图片的格式必须是(bmp,gif,jpeg,jpg,png)").attr("require", "false").attr("accept", "bmp, gif, jpeg, jpg, png");
	$("#memo").textbox({
		maxLength: 200,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_tip").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_tip").slideUp("normal");
	});

	$("#r3_code").click(function(){
		var fgs_id = $("#dept_id").val();
		var returnValue = window.showModalDialog("KonkaR3Store.do?method=listCustomer&fgs_id=" + fgs_id +"&ts=" + Math.random(), window, "dialogWidth:800px;status:no;dialogHeight:600px");
		if (!returnValue) returnValue = window.returnValue;
		$("#r3_code").val(returnValue.cust_r3_code);
		$("#c_name").val(returnValue.cust_name);
		$("#c_link_name").val(returnValue.link_man_tel);
	});

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

	 $("a[id^='att_del_']").click(function() {
	  	  var a = this;
	  	   if(!confirm('确实要删除此照片？')) return;
	  	    $.post("KonkaActInfo.do", { method : "deleteFile", id : $(this).attr("id").replace(/att_del_/g, '')}, function(success) {
	  	   if (success){alert("恭喜您，删除照片成功!");$(a).parent().remove();} else alert(" 很抱歉，删除照片出错"); 
	  	  });
	   });   
	
	
	// 提交
	$("#btn_submit").click(function(){

		if (this.form.start_date.value != "" && this.form.end_date.value != "") {
			if (this.form.end_date.value < this.form.start_date.value) {
				alert("发布时间结束日期不得小于开始日期,请重新选择!");
				return false;
			}
		}
		
		if(Validator.Validate(this.form, 2)){
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});

});

function resizeFrameHeight(offset, min_height) {
	// frame id can be found in page /konka-wd/WebContent/manager/admin/Frames2/indexFrame.jsp, and search 'iframe' to get.
	$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
}

function selectSaleDatas(){

	if(""!=$("#start_date").val()&&""!=$("#end_date").val()&&""!=$("#r3_code").val()){
		$(".d1").remove();
		$("#d2").remove();
		$.ajax({
			type: "POST",
			cache: false,
			url: "${ctx}/manager/admin/KonkaActInfo.do",
			data: "method=getSaleDatas&start_date=" + $("#start_date").val()+"&end_date="+$("#end_date").val()+"&r3_code="+$("#r3_code").val(),
			dataType: "json",
			error: function(request, settings){},
			success: function(data) {
				var sum = 0.00;
				if (data.length >= 1) {
					$("<tr id='d2'><td>零售机型</td><td>台数</td><td>单价</td><td>总价</td></tr>").appendTo($("#t1"));
					for(var i = 0; i < data.length - 1; i++) {
						var tr = "<tr class='d1'><td>" + data[i].model_name + "</td><td>" + data[i].num + "</td><td>" + data[i].all_price/data[i].num + "</td><td class='a1'>" + data[i].all_price + "</td></tr>";
						$(tr).appendTo($("#t1"));
						sum += parseFloat(data[i].all_price);
					}
					resizeFrameHeight();
				}
				$("#done_money").val(sum.toFixed(2)/10000);
			}
		});
		resizeFrameHeight();
	}else{
		alert("请选择开始时间，结束时间，R3客户编码");
	}
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
