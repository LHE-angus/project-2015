<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<c:set var="naviString" value="客户端首页商品管理" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/jquery-ui/ui/ui.core.js"></script>


</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="250" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="5%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form  enctype="multipart/form-data"  action="/admin/KonkaVipPdManage.do" method="post" >
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="id" value="${af.map.id}" />
      <html-el:hidden property="pic_edit_id" value="${pic_edit_id}" />
      <html-el:hidden property="mod_id"  value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
       
       <tr>
    		<td align="center" nowrap="nowrap" class="title_item"><font color="red">*</font>商品型号</td>
    		<td align="left" nowrap="nowrap">
    		<html-el:select property="pd_id" styleId="pd_id">
	    	 <html-el:option value="">-请选择-</html-el:option>
	    		<c:forEach items="${pdlist}" var="cur">
	    		<c:if test="${not empty cur.md_name}">
	    		<html-el:option value="${cur.pd_id}">${cur.md_name}</html-el:option>
	    		</c:if>
	    		</c:forEach>
	    	</html-el:select>
	    	</td>
  	   </tr>
        <tr>
          <td align="center" nowrap="nowrap" class="title_item"><font color="red">*</font>所属分类</td>
    		<td align="left" nowrap="nowrap">
    		<html-el:select property="pd_type"  styleId="pd_type">
	    	 <html-el:option value="">-请选择-</html-el:option>
	    		<html-el:option value="1">新品首发</html-el:option>
	    		<html-el:option value="2">精品推荐</html-el:option>
	    		<html-el:option value="3">热销商品</html-el:option>
	    		<html-el:option value="4">特惠商品</html-el:option>
	    		
	    	</html-el:select>
	    	</td> </tr>
	    	
	    	
<!--   <tr> -->
<!--     <td align="center" nowrap="nowrap" class="title_item">价格</td> -->
<%--     <td align="left" nowrap="nowrap"><html-el:text property="price" size="20" maxlength="8" styleId="price" /></td> --%>
<!--   </tr> -->
<!--    <tr > -->
<!--           <td width="12%" nowrap="nowrap" class="title_item" align="center"><font color="red">* </font>上样时间：</td> -->
<!--           <td width="88%" align="left"> -->
<%--           	<fmt:formatDate value="${af.map.up_date}" pattern="yyyy-MM-dd" var="_up_date" /> --%>
<%-- 				<html-el:text property="up_date" styleId="up_date" size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期"  value="${_up_date}"/> --%>
<!--           </td> -->
<!--    </tr> -->
<!--      <tr > -->
<!--           <td width="12%" nowrap="nowrap" class="title_item" align="center">下样时间：</td> -->
<!--           <td width="88%" align="left"> -->
<%--           	<fmt:formatDate value="${af.map.down_date}" pattern="yyyy-MM-dd" var="_down_date" /> --%>
<%-- 			<html-el:text property="down_date" styleId="down_date" size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期"  value="${_down_date}"/> --%>
<!--           </td> -->
<!--    </tr> -->
   <tr>
	    <td width="25%" align="center" nowrap="nowrap" class="title_item"><span style="color:red">*</span>分公司</td>
<!-- 	    <td width="75%" align="left" nowrap="nowrap"> -->
<%-- 	    	<html-el:select property="dept_id" styleId="dept_id"> --%>
<%-- 	    	 <html-el:option value="">-请选择-</html-el:option> --%>
<%-- 	    		<c:forEach items="${sybDeptInfoList}" var="cur"> --%>
<%-- 	    		<c:if test="${not empty cur.dept_name}"> --%>
<%-- 	    		<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option> --%>
<%-- 	    		</c:if> --%>
<%-- 	    		</c:forEach> --%>
<%-- 	    	</html-el:select></td> --%>
<td>
<html-el:hidden property="deptids" styleId="deptids"/>
         <select id ="sela"  multiple="multiple" name="example-basic" size="5">
                    <c:forEach items="${sybDeptInfoList}" var="cur" >
                    <option id="${cur.dept_id}" value="${cur.dept_id}"  
                    <c:if test="${cur.dept_id eq af.map.fgs_id}">
                     selected
                    </c:if>
                    >${cur.dept_name}</option>
   
  </c:forEach>
  </select>
  </td>
</tr> 
<tr>
<td class="title_item" align="center" nowrap="nowrap"><span style="color:red">*</span>商品图片：</td>
					<td>
          			<c:if test="${empty af.map.id}">
          			<html-el:file property="main_pic_file" styleId="main_pic_file" styleClass="webinput" style="width:" />
          				&nbsp;&nbsp;&nbsp;&nbsp;<font color="blue">(为了达到最优显示效果，建议上传图片尺寸为1：1.4)</font>
          			</c:if>
          			<c:if test="${not empty af.map.id}">
          				<div id="main_pic_div" class="main_box">
	          				<div class="pic_box">
	          					<a id="main_pic_a" href="${ctx}/${save_path}" title="">
		        					<img width="100px" height="100px" src="${ctx}/${save_path}" />
		        				<html-el:hidden property="main_pic_hidden" styleId="main_pic_hidden" value="${af.map.main_pic_file}" />
		        				<a class="xubox_close xubox_close1_0" href="javascript:delPic('main_pic_div');"></a>
		        			</div>
          				</div>
          				<div style="margin-top:5px;">
		        			<input type="checkbox" name="chkReUploadMainPicFileImage" id="chkReUploadMainPicFileImage" value="1" onclick="$('#main_pic_file').toggle();" style="vertical-align:middle;cursor:pointer;" />
			            	<label for="chkReUploadMainPicFileImage" style="vertical-align:middle;cursor:pointer;">重新上传</label>
			            	&nbsp;&nbsp;&nbsp;&nbsp;<font color="blue">(为了达到最优显示效果，建议上传图片尺寸为1：1.4)</font>
          				</div>
		            	<html-el:file property="main_pic_file" styleId="main_pic_file" styleClass="webinput" style="display:none;width:" />
		            	
          			</c:if>
          		</td>
</tr>
 <tr>
          <td align="center" nowrap="nowrap" class="title_item"><font color="red">*</font>是否锁定</td>
    		<td align="left" nowrap="nowrap">
    		<html-el:select property="is_locked"  styleId="is_locked">
	    	 <html-el:option value="">-请选择-</html-el:option>
	    		<html-el:option value="0">锁定</html-el:option>
	    		<html-el:option value="1">未锁定</html-el:option>
	    	</html-el:select>
	    	</td> </tr>
	    	 <tr>
          <td align="center" nowrap="nowrap" class="title_item"><font color="red">*</font>是否展示</td>
    		<td align="left" nowrap="nowrap">
    		<html-el:select property="is_hidden"  styleId="is_hidden">
	    	 <html-el:option value="">-请选择-</html-el:option>
	    		<html-el:option value="0">展示</html-el:option>
	    		<html-el:option value="1">未展示</html-el:option>
	    		
	    		
	    	</html-el:select>
	    	</td> </tr>
  <tr>
    <td align="center" nowrap="nowrap" class="title_item">文字说明</td>
    <td align="left" nowrap="nowrap">
    	<html-el:textarea property="pd_desc" styleId="pd_desc" cols="5" style="width:450px;height:100px;" />
          	<div id="info_tip" style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div></td>
  </tr>
       
        <tr>
        <td width="20%"></td>
          <td><label>
           <input class="but4" type="button" name="Submit4" id="btn_submit" value="提交" />&nbsp;
              <input class="but3" type="button" name="Submit5" value="重置" onclick="this.form.reset();return false;" />&nbsp;
          <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();" /></label></td>
        </tr>
      </table>
    </html-el:form>
  </div>
   
          <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
// 	$("#dept_id").multiselect({
// 		noneSelectedText: '==请选择==',
// 		selectedList: 1,
// 		multiple: false,
// 		minWidth:150,
// 		click: function(event, ui){
// 	       if(ui.value != ""){
// 	    	   $("#dept_id").val(ui.value);
// 	       }
// 		}
// 	}).multiselectfilter();
	
	$("#pd_id").multiselect({
		noneSelectedText: '==请选择==',
		selectedList: 1,
		multiple: false,
		minWidth:150,
		click: function(event, ui){
	       if(ui.value != ""){
	    	   $("#pd_id").val(ui.value);
	       }
		}
	}).multiselectfilter();
	
	$(function(){
	    $("#sela").multiselect({
	        noneSelectedText: "==请选择==",
	        checkAllText: "全选",
	        uncheckAllText: '全不选',
	        selectedList:5
	    });
	});
	
	$("#btn_submit").click(function(){
		var issubmit=true;
		//判断是否选择商品
		var pd_id=$("#pd_id").val();
		if(pd_id==null ||pd_id==""){
			issubmit=false;
			alert("请至少选择一个商品");
		}
		var is_locked=$("#is_locked").val();
		if(is_locked==null ||is_locked==""){
			issubmit=false;
			alert("请选择是否锁定");
		}
		var is_hidden=$("#is_hidden").val();
		if(is_hidden==null ||is_hidden==""){
			issubmit=false;
			alert("请选择是否展示");
		}

		
		//判断是否选择单位
		var valuestr  = $("#sela").multiselect("getChecked").map(function(){
			   return this.value;    
		}).get();
		$("#deptids").val(valuestr);
		if(valuestr==null||valuestr==""){
			issubmit=false;
			alert("请至少选择一个单位");
		}
         //判断是否选择商品分类
		var pd_type=$("#pd_type").val();
		if(pd_type==null ||pd_type==""){
			issubmit=false;
			alert("请至少选择一个商品分类");
		}
		<c:if test="${empty af.map.id}">
		var main_pic_file =$("#main_pic_file").val();
		if(main_pic_file==null ||main_pic_file==""){
			issubmit=false;
			alert("请选择商品图片");
		}
		</c:if>
		if(issubmit){
			$("form").submit();
		}
		
	});
	
	
	
	
})



//]]></script>


<style type="text/css">

input.blur{ 
     border:1px solid #99BBE8; 
     background:#FFFFFF; 
     height:18px; 
} 
.tableborder{ 
border:solid 1px #CCCCCC; 
border-collapse:collapse; 
font-size:12px; 

}
</style>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
