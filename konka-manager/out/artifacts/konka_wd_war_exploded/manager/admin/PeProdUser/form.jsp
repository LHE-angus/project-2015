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
<link href="${ctx}/styles/customer/multiselect/jquery.multiselect2side.css" rel="stylesheet" type="text/css" />
<style type="text/css">
#sel{margin-top:10px}
</style> 
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/PeProdUser.do" enctype="multipart/form-data">
      <html-el:hidden property="user_id" value="${af.map.user_id}" />
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="pass_word_old" value="${af.map.pass_word}" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <html-el:hidden property="queryString" value="${af.map.queryString}"  />
      <html-el:hidden property="returnUrl" />
      <c:if test="${pe_prod_user_session.user_type eq 0}">
        <c:set value="true" var="is_admin" />
      </c:if>
      <div align="left">
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="datagrid">
          <tr class="oartop">
            <td height="28" colspan="4" style="padding-bottom:10px;fontweight:bold;"><c:if test="${empty af.map.id}">企业人员添加</c:if>
              <c:if test="${not empty af.map.id}">编辑 岗位<span style="font-size:14px;font-weight:bolder;">${empty af.map.job_id ? '未指定岗位ID，请尽快完善' : af.map.job_id}</span></c:if></td>
          </tr>
          <tr class="oartop">
            <td colspan="4">组织信息</td>
          </tr>
          <tr>
            <td width="15%" nowrap="nowrap" height="28" class="title_item">用户类型：</td>
            <td colspan="3">
	            <c:if test="${!role_id_eq_10}"><html-el:hidden property="user_type" value="1" />分公司用户</c:if>
	            <c:if test="${role_id_eq_10}"><html-el:select property="user_type">
	            	<html-el:option value="0">总部用户</html-el:option>
	            	<html-el:option value="1">分公司用户</html-el:option>
	            </html-el:select> </c:if>
	        </td>
          </tr> 
          
          <tr>
            <td height="28" width="15%" nowrap="nowrap" class="title_item">部门：</td>
            <td colspan="3">
          	<html-el:select property="dept_id" styleId="dept_id" style="width:200px;">
                <html-el:option value="">请选择...</html-el:option>
                <c:forEach var="cur" items="${peDeptList}">
                  <html-el:option value="${cur.dept_id}">${fn:escapeXml(cur.map.tree_name)}</html-el:option>
                </c:forEach>
              </html-el:select>
          	  <c:if test="${role_id_eq_10}"> <span class="note"><a href="KonkaDept.do?mod_id=902200&tree_param=${tree_param}" onclick="this.href=this.href + '&returnUrl=' + encodeURIComponent(window.location.href.toString());">点此添加部门</a></span> </c:if>
          	</td>
          </tr>
          <tr>
            <td height="28" width="15%" nowrap="nowrap" class="title_item">职务：</td>
            <td colspan="3">
	            <input name="role_name_like" id="role_name_like" maxlength="" size="40"/>
	        	<input class="but8" type="button" name="Submit4" value="点击查询 " id="btn_role" />
            <table class="areause1" >
              <tbody>
                <tr>
                  <td><span id="area_name_0">供选择职务列表</span><br />
                    <html-el:select property="select1" multiple="true" style="width:260px;height:200px;" styleId="select1" onchange="moveSelected(this.form.select1, this.form.select2);">
                    	<c:forEach var="cur" items="${roleInfoList}"> 
 	                  		<option value="${cur.role_id}" title="${cur.role_id lt 10000 ? '[系统角色]' : ''}${cur.role_name}"><c:out value="${cur.role_id lt 10000 ? '[系统角色]' : ''}${fn:escapeXml(cur.role_name)}" /></option> 
 	                	</c:forEach>
                    </html-el:select></td>
                  <td width="20"></td>
                  <td><span id="area_name_1">选择的职务列表</span><br />
                    <html-el:select property="select2" multiple="true" style="width:260px;height:200px;" styleId="select2" onchange="moveSelected(this.form.select2, this.form.select1);">
                      <c:if test="${not empty roleUserList1}">
                        <html-el:optionsCollection label="map.role_name" value="role_id" name="roleUserList1" />
                      </c:if>
                    </html-el:select>
                    <html-el:hidden property="roleIds" styleId="roleIdsHidden" />
                    </td>
                </tr>
              </tbody>
            </table>
	            <div>注：分公司管理员请注意，请谨慎删除系统角色，系统角色是由总部系统管理员统一管理与授权，删除后需要重新分配请联系系统管理员。</div>
            </td>
          </tr>
          <tr class="oartop">
            <td colspan="4">登录信息</td>
          </tr>
            <tr>
              <td nowrap="nowrap" height="28" class="title_item">登录名：</td>
              <td colspan="3"><html-el:text property="user_name" styleId="user_name" size="40" maxlength="20" value="${af.map.user_name}" />
                &nbsp;<span style="color:red">*</span> <span style="color:#f00;display:none;" id="user_name_exist_error">该登录名已被使用，请重新输入！</span><span style="color:#f00;display:none;" id="user_name_erro">登录名不能含空白字符！</span></td>
            </tr>
          <tr>
            <td nowrap="nowrap" height="28" class="title_item">登录密码：</td>
            <td colspan="3"><html-el:password property="pass_word" styleId="pass_word" size="40" maxlength="20" />
               &nbsp;<span style="color:red">*</span> <span>切勿在密码中间输入空格字符！请牢记系统默认密码为:888888</span></td>
          </tr>
          <tr class="oartop">
            <td colspan="4">岗位信息</td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" valign="top">岗位ID：</td>
            <td colspan="3">
            	<c:if test="${not empty af.map.job_id}">${af.map.job_id}</c:if>
            	<c:if test="${empty af.map.job_id}">
            		<html-el:text property="job_id" styleId="job_id" maxlength="30" size="40"/>&nbsp;<span style="color:red">*</span><span style="color:#f00;display:none;" id="job_id_exist_error">该岗位ID已被使用，请重新输入！</span><span style="color:#f00;display:none;" id="job_id_erro">岗位ID不能含空白字符！</span>
            		<div style="color:#f00;font-weight:800;">注：岗位ID一经填写，不可修改，并且由至少6位数字、字母、_ 或 - 组成；因各分公司岗位ID不一致，建议统一使用分公司简称的首字母（如深圳“SZ”）作前缀。</div></c:if>
            </td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" valign="top">岗&nbsp;&nbsp;&nbsp;&nbsp;位：</td>
            <td>
            	<html-el:text property="position" styleId="position" value="${af.map.map.position }"></html-el:text>
            </td>
            <td nowrap="nowrap"  height="28" class="title_item" valign="top">R3人员编号：</td>
            <td>
            	<html-el:text property="r3_job_id" styleId="r3_job_id" value="${af.map.r3_job_id}"></html-el:text>
            </td>
            
          </tr>
          <tr>
           <td nowrap="nowrap"  height="28" class="title_item" valign="top">类&nbsp;&nbsp;&nbsp;&nbsp;型：</td>
          <td>
            	<html-el:select property="sales_type" styleId="sales_type" style="width:80px;" value='${af.map.map.sales_type }'>
                	<html-el:option value="">请选择...</html-el:option>
                	<html-el:option value="1">兼职</html-el:option>
                	<html-el:option value="2">全职</html-el:option>
              </html-el:select><span style="color:red">*</span>
            </td>
          	<td nowrap="nowrap"  height="28" class="title_item" valign="top">入职日期：</td>
          	<td >
          		<fmt:formatDate value="${af.map.map.work_date}" var="_work_date" pattern="yyyy-MM-dd" />
				<html-el:text property="work_date" styleId="work_date" size="10" maxlength="10" value="${_work_date}" readonly="true" onclick="new Calendar(1900, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
          	</td>
          </tr>
          
          <tr class="oartop">
            <td colspan="4">在岗人员信息</td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item">姓名：</td>
            <td colspan="3"><html-el:text property="real_name" styleId="real_name" maxlength="20" size="40"/>
              &nbsp;<span style="color:red">*</span> <span>请务必填写真实姓名</span></td>
               </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item">附件（照片）：</td>
            <td colspan="3"><html-el:file property="fj"  styleId="fj" />&nbsp;&nbsp;<span style="color:red">*</span> <font color="blue">为避免照片失真，尺寸要求宽220,高310</font>
            <c:if test="${not empty af.map.save_path}"><br />现有照片：<a href="${ctx}/${af.map.save_path}" target="_blank" ><font color="red">${af.map.file_name}</font></a>
            </c:if></td>  
          </tr>
          <tr id="tt" style="display: none;">
            <td nowrap="nowrap"  height="28" class="title_item">转岗原因：</td>
            <td colspan="3"><html-el:select  property="chan_status"  styleId="chan_status">
            				<html-el:option value="">--请选择--</html-el:option>
            				<html-el:option value="0">转岗</html-el:option>
            				<html-el:option value="1">离职</html-el:option>
            				<html-el:option value="2">其他</html-el:option>
            </html-el:select><span style="color:red">*</span> 
            </td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item">性别：</td>
            <td><label for="sex0" style="width:80px;">
              <html-el:radio property="sex" styleClass="sex" styleId="sex0" value="0" >男</html-el:radio>
              </label>
              <label for="sex1" style="width:80px;">
              <html-el:radio property="sex" styleClass="sex" styleId="sex1" value="1">女</html-el:radio>
              </label>
              <label for=sex2 style="width:80px;">
              <html-el:radio property="sex" styleClass="sex" styleId="sex2" value="2" >保密</html-el:radio>
              </label></td>
            <td nowrap="nowrap" height="28" class="title_item">出生日期：</td>
            <td><fmt:formatDate value="${af.map.birthday}" pattern="yyyy-MM-dd" var="_birthday"/>
              <html-el:text property="birthday" value="${_birthday}"  size="10" maxlength="20" readonly="true" onclick="new Calendar(1950, 2030, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
          </tr>
          <tr>
          <td width="15%" valign="middle" nowrap="nowrap" class="title_item">居住地：</td>
          <td colspan="3" valign="middle" nowrap="nowrap">
            <select name="province" id="province" class="bd">
              <option value="">-请选择省/直辖市/自治区-</option>
            </select>
            <select name="city" id="city" class="bd">
              <option value="">-请选择市-</option>
            </select>
            <select name="country" id="country" class="bd">
              <option value="">-请选择县-</option>
            </select>
            <select name="town" id="town" class="bd">
              <option value="">-请选择乡/镇-</option>
            </select></td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item">街道地址：</td>
            <td colspan="3"><html-el:text property="link_addr" styleId="link_addr" maxlength="130" size="80"/></td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item">手机：</td>
            <td width="35%"><html-el:text property="link_phone" styleId="link_phone" maxlength="11" size="40"/></td>
            <td width="15%" nowrap="nowrap"  height="28" class="title_item">电话：</td>
            <td width="35%"><html-el:text property="link_tel" styleId="link_tel" maxlength="130" size="40"/>
              &nbsp;<span class="note">格式如0551-4567888</span></td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item">邮箱：</td>
            <td><html-el:text property="email" styleId="email" maxlength="80" size="40"/></td>
            <td nowrap="nowrap"  height="28" class="title_item">邮编：</td>
            <td><html-el:text property="link_post" styleId="link_post" maxlength="10" size="40"/></td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item">QQ：</td>
            <td><html-el:text property="link_qq" styleId="link_qq" maxlength="20" size="40"/></td>
            <td nowrap="nowrap"  height="28" class="title_item">MSN：</td>
            <td><html-el:text property="link_msn" styleId="link_msn" maxlength="30" size="40"/></td>
          </tr>
          <tr class="oartop">
            <td colspan="4">其他信息</td>
          </tr>
           <tr>
            <td nowrap="nowrap" height="28" class="title_item">排序值：</td>
            <td><html-el:text property="order_value" styleId="order_value" style="width:40px;" maxlength="4" size="4" styleClass="webinput" />
                               取值范围：0 - 9999，值越大，显示越靠前。&nbsp;&nbsp;</td>
          </tr>
          <tr>
            <td colspan="6" height="40"  align="center"><input class="but4" type="button" name="Submit4" value=" 保存 " id="btn_submit" />
              <input class="btn_reset" type="reset"  value=" 重填" id="reset" />
              <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" id="btn_back" /></td>
          </tr>
        </table>
      </div>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/multiselect/jquery.multiselect2sideYwy.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	/*
	$("#l3_dept_id").attr({"subElement": "l4_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l3_dept_id}"});
	$("#l4_dept_id"    ).attr({"subElement": "l5_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
	$("#l5_dept_id" ).attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l5_dept_id}"});

	$("#l3_dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId2", "par_id", "${empty cs_par_id ? 0 : cs_par_id}");
	$("#l3_dept_id").change();
	*/


	$("#fj").attr("dataType","Filter").attr("require",false).attr("accept","bmp, gif, jpeg, jpg, png").attr("msg","请上传格式为(bmp, gif, jpeg, jpg, png)的图片");
	$("#sales_type").attr("datatype", "Require").attr("msg", "请选择类型！");
	$("#job_id").keyup(function(){
		var job_id = $(this).val();
		$(this).val(SBC2DBC(job_id));
	});
	$("#user_name").keyup(function(){
		var job_id = $(this).val();
		$(this).val(SBC2DBC(job_id));
	});
	
	$("#btn_role").click(function(){
		  $.ajax({
				type: "POST",
				url: "PeProdUser.do",
				data: "method=" + "ajaxSelectRoleList" + "&role_name_like=" + $("#role_name_like").val() + "&t=" + (new Date()).getTime(),
				dataType: "json",
				error: function(request, settings) { alert('查询错误'); },
				success: function(data) {
					if(data.status == "0"){
						alert("查询条件不能为空！");
					} else {
						var values =  data.data;
						$("#select1").empty();
						for(var i = 0; i < values.length; i++) {
							var opt = new Option(values[i].role_name, values[i].role_id); 
							$("#select1").get(0).options.add(opt);
						}
					}
			    }
		  });
		});
	
		var isRomveYwy = '${isRomveYwy}';	
	
		<c:forEach items="${bpList}" var="cur">
		<c:if test="${cur.is_required eq 1}">
			<c:if test="${cur.prop_type eq 0 || cur.prop_type eq 1}">
				$("#prop_values_${cur.prop_id}").attr("datatype","Require").attr("msg","请填写${cur.prop_name}的属性值");
			</c:if>
			<c:if test="${cur.prop_type eq 2}">
				$("#checkbox_${cur.prop_id}").attr("datatype","Group").attr("min","1").attr("msg","请至少选择一个${cur.prop_name}的属性值");
			</c:if>
		</c:if>
		</c:forEach>
		
		<c:if test="${empty af.map.user_id}">
		$("#pass_word").val("888888");
		</c:if>

		<c:if test="${not empty af.map.user_id}">
		$("#user_name,#real_name").blur(function(){
		    var user_name1 = $("#user_name").val();
			var real_name = $("#real_name").val();
			if(user_name1 != '${af.map.user_name}'||real_name != '${af.map.real_name}'){
				$("#tt").show();
				$("#chan_status").attr("datatype","Require").attr("msg","请选择转岗原因！");
			}else{
				$("#tt").hide();
				$("#chan_status").removeAttr("datatype").removeAttr("msg").val("");
			}
			
		});
		</c:if>

		
		//处理职务多选，Ren,zhong，2013-06-25
// 		if(isRomveYwy.length > 0){//修改
// 			$("#roleIds").multiselect2side({
// 			  selectedPosition: 'right',
// 			    moveOptions: false,
// 				labelsx: '待选区',
// 				labeldx: '已选区',
// 				isAddTips: isRomveYwy,
// 				isRomveYwy: isRomveYwy   //如果该用户对应是业务员角色；false：表示不能移出，true:表示可以移出
// 		   	});
// 		} else {//新增
// 			$("#roleIds").multiselect2side({
// 				  selectedPosition: 'right',
// 				    moveOptions: false,
// 					labelsx: '待选区',
// 					labeldx: '已选区'
// 			   	});
// 		}

		
	   	//回显职务
// 	   	if ("" != "${roleUserList}") {
// 		   	$("#select2").val("");//2013-06-27，解决总是选中【系统管理员】的bug
// 		   	var arrs = new Array();
// 	   		<c:forEach var="cur" items="${roleUserList}">
// 				//alert("${cur.role_id}" + "____" + "${cur.map.role_name}");
// 				$("#roleIds_ms2side__sx option[value='${cur.role_id}']").attr("selected", true).dblclick();
// 				arrs.push("${cur.role_id}");
// 			</c:forEach>
// 			$("#roleIdsHidden").val(arrs);
// 		}
		
		$("#user_name").attr("dataType", "Require").attr("msg", "请输入用户名！");
		$("#job_id").attr("dataType", "Require").attr("msg", "请输入岗位ID！");
		$("#real_name").attr("dataType", "Chinese").attr("msg", "真实姓名只允许中文！");
		$("#dept_id").attr("dataType", "Require").attr("msg", "请选择部门！");
		$("#position_id").attr("dataType", "Require").attr("msg", "请选择职位！");
		$("#pass_word").attr("dataType", "Require").attr("msg", "请填写密码！");
		
		$("#link_msn").attr("dataType", "Email").attr("msg", "请填写正确的MSN！").attr("require", "false");
		$("#email").attr("dataType", "Email").attr("msg", "请填写正确的邮箱！").attr("require", "false");
		$("#link_phone" ).attr("dataType", "Mobile").attr("msg", "请填写正确的手机号码！").attr("require", "false");
		$("#link_tel" ).attr("dataType", "Phone").attr("msg", "请填写正确的电话号码！").attr("require", "false");
		$("#r3_job_id" ).attr("datatype", "Number").attr("msg", "必须为数字！").attr("require", "false");
		$("#link_qq" ).attr("focus",setOnlyNum);
		$("#link_post" ).attr("focus",setOnlyNum);
		$("#user_name").focus(function() {
		    //获得焦点时，如果值为默认值，则设置为空
		    //if (this.value == vdefault) {
		    //    this.value = "";
		    //    this.style.color='#333';
		    //}
		}).blur(function() {
		    //失去焦点时，如果值为空，则设置为默认值
		    if (this.value == "") {
		        //this.value = vdefault;
		        this.style.color='#999999';
		    }
		    
		  	//begin 转化全角为半角
			$("#user_name").val(DBC2SBC(this.value).NoSpace());
			//end 
		});
		
		String.prototype.NoSpace = function() 
		{ 
			return this.replace(/\s+/g, ""); 
		}
		$("#pass_word").focus(function() {
		    //获得焦点时，如果值为默认值，则设置为空
		    //if (this.value == vdefault) {
		    //    this.value = "";
		    //    this.style.color='#333';
		    //}
		}).blur(function() {
		    //失去焦点时，如果值为空，则设置为默认值
		    if (this.value == "") {
		    //    this.value = vdefault;
		        this.style.color='#999999';
		    }
		    
		  	//begin 转化全角为半角
			$("#pass_word").val(DBC2SBC(this.value).NoSpace());
			//end 
		});
		$("#btn_submit").click(function(){
			var num = $("#select2 option").size();
			if(num == 0 || null == num){
				alert("请选择职务！");
				return false;
			} else {
				var roleIds = "";
				$("#select2 option").each(function(){
					roleIds = roleIds + $(this).val()+",";
				});
				roleIds_ = roleIds.substring(0, roleIds.length-1);
				$("#roleIdsHidden").val(roleIds_);
			}
			
			
		    var phonestr = $("#link_phone").val();
			if(phonestr!=""){
				var reg = /^1\d{10}$/ ;
			    if (reg.test(phonestr)) {
			    } else {
			    	alert(phonestr+" 不是正确的手机号码!");
			    	return false;
			    }
			}
			
			<c:if test="${empty af.map.user_name}">
				var user_name = $("#user_name").val();
				if("" == user_name || user_name.indexOf(' ')>-1) {
						$("#user_name_erro").show();
						return false;	
				}
   			</c:if>
   			
			if(Validator.Validate(this.form, 3)) {
				// 去掉R3人员编号前后空格
				$("#r3_job_id" ).val($.trim($("#r3_job_id" ).val()));
				$("#btn_submit"   ).attr("disabled",true);
				$("#reset").attr("disabled",true);
				$("#btn_back"     ).attr("disabled",true);
				if(confirm('岗位ID一经填写，不可修改，并且由至少6位数字、字母、_ 或 - 组成，确定提交请点击确定。')){ 
					this.form.submit();
				} else {
					$("#btn_submit"   ).removeAttr("disabled");
					$("#reset").removeAttr("disabled");
					$("#btn_back"     ).removeAttr("disabled");
				};
			}
		});
		
		$("#position_id").change(function(){
			if(this.value == 0){
				$("#user_name_is_require").show();
				$("#user_name").attr("require", "true");
			}else{
				$("#user_name_is_require").hide();
				$("#user_name").attr("require", "false");
			}
		}).change();

	

		// 验证用户名是否存在
		$("#user_name").blur(function(){
			var user_name = $("#user_name").val();
			if(null == $(this).val() || $(this).val() == ''){
				$("#user_name_exist_error").hide();
				$("#user_name_erro").hide();
				$(this).css("background-color", "#fff");
				return ;
			}
			if($(this).val().indexOf(' ')>-1){
				$("#user_name_exist_error").hide();
				$("#user_name_erro").show();
				return;
			}
			
			$("#user_name_erro").hide();
			$("#user_name_exist_error").hide();
			$(this).css("background-color", "#fff");
			if(user_name != '${af.map.user_name}'){
				$.ajax({
					type: "POST",
					url: "CustomerUsers.do",
					data: "method=validateName&user_name=" + $(this).val(),
					dataType: "json",
					error: function(request, settings) {
						alert("检查用户名重复失败，请稍候再次尝试。");
						$("#user_name_exist_error").show();
						$(this).css("background-color", "#ddcc00");
						$(this).focus();
					},
					success: function(oper) {
						if (oper.result) {
							//$("#user_name_exist_error").show();
							//$("#btn_submit").attr("disabled", "disabled");
							//$("#user_name").css("background-color", "#ddcc00");
							//$("#user_name").focus();
							alert("该用户名已存在！");
							$("#user_name").val("");
							return;
						} else {
							$("#user_name_exist_error").hide();
							$("#user_name").css("background-color", "#fff");
						}
					}
				});
			}
		});	
		

		// 验证岗位ID是否存在
		$("#job_id").blur(function(){
			$("#btn_submit").attr("disabled", "disabled");
			var job_id = $("#job_id").val();
			if(null == $(this).val() || $(this).val() == ''){
				$("#job_id_exist_error").hide();
				$("#job_id_erro").hide();
				$(this).css("background-color", "#fff");
				return ;
			}
			if($(this).val().indexOf(' ')>-1){
				$("#job_id_exist_error").hide();
				$("#job_id_erro").show();
				return;
			}
			if (!/^[a-zA-Z_0-9-]{6,}$/.test($(this).val())) {
				alert("岗位ID由至少6位数字、字母、_ 或 - 组成。");
				return;
			}
			
			$("#job_id_erro").hide();
			$("#job_id_exist_error").hide();
			$(this).css("background-color", "#fff");
			if(job_id != '${af.map.job_id}'){
				$.ajax({
					type: "POST",
					url: "PeProdUser.do",
					data: "method=validateJobId&job_id=" + $(this).val(),
					dataType: "json",
					error: function(request, settings) {
						alert("检查用户名重复失败，请稍候再次尝试。");
						$("#job_id_exist_error").show();
						$(this).css("background-color", "#ddcc00");
						$(this).focus();
					},
					success: function(oper) {
						if (oper.result) {
							//$("#job_id_exist_error").show();
							//$("#btn_submit").attr("disabled", "disabled");
							//$("#job_id").css("background-color", "#ddcc00");
							//$("#job_id").focus();
							alert("该岗位ID已存在！");
							$("#job_id").val("");
							return;
						} else {
							$("#job_id_exist_error").hide();
							$("#job_id").css("background-color", "#fff");
							$("#btn_submit").removeAttr("disabled");
						}
					}
				});
			}
		});	
		
	    // 区域
		$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${province}"});
		$("#city"    ).attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${city}"});
		$("#country" ).attr({"subElement": "town","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${country}"});
		$("#town"    ).attr({"defaultText": "-请选择乡/镇-", "defaultValue": "", "selectedValue": "${town}"});

		$("#province").cs_ext("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false,"p_index_join","${p_index_join}");
		$("#province").change();
		
		//防止浏览器将登录名回显成当前用户的
		if('${af.map.id}'!=''&&'${af.map.id}'!='${current_user_id}'){
			if($("#user_name").val()!='' && $("#user_name").val()== '${current_user_name}'){
				$("#user_name").val('${af.map.user_name}');
			}
			if(''==$("#pass_word").val()){
				$("#pass_word").val('${af.map.pass_word}');
			}
		}
});
function DBC2SBC(str){
    var result = '';
    for (i=0 ; i<str.length; i++){
        code = str.charCodeAt(i);//获取当前字符的unicode编码
        if (code >= 65281 && code <= 65373){//在这个unicode编码范围中的是所有的英文字母已及各种字符
   			result += String.fromCharCode(str.charCodeAt(i) - 65248);//把全角字符的unicode编码转换为对应半角字符的unicode码
  		}else if (code == 12288){//空格
   			result += String.fromCharCode(str.charCodeAt(i) - 12288 + 32);
  		}else{
   			result += str.charAt(i);
  		}
   }
   return result;
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
			if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}
			if(this.value.length == 0) this.value = "0";
		});
		//this.text.selected;

}

function moveSelected(sourceSelect, targetSelect, isDelete){
	if(sourceSelect == document.forms[0].select1){
		if(sourceSelect.value == 60){
			alert("职务包含业务员，请及时分配客户信息！");
		}
	} else {
		if(sourceSelect.value == 60){
			alert("对不起，请先清除分配的客户后，再移出业务员职务！");
		}
	}
	var cachOptionsArray = new Array();
	var index = 0;
	for (var i = sourceSelect.options.length - 1; i >= 0; i--){
		if (sourceSelect.options[i].selected){
			cachOptionsArray[index] = new Option(sourceSelect.options[i].text, sourceSelect.options[i].value);
			if(isDelete==undefined || isDelete==true){
				sourceSelect.options[i] = null;
			}
			index++;
		}
	}
	var exist = false;
	for (var i = cachOptionsArray.length - 1; i >= 0; i--){
		exist = false;
		for (var j = 0; j < targetSelect.options.length; j++){
			if (targetSelect.options[j].value.toString() == cachOptionsArray[i].value.toString()){
				exist = true; 
				break;
			}
		}
		if (!exist){
			targetSelect.options[targetSelect.options.length] = cachOptionsArray[i];
		}
	}
}

//全角转换成半角
function SBC2DBC(str){
	var   i;  
	var   result= '';  
	for(i=0;i <str.length;i++)   {
		var  code=str.charCodeAt(i)
		// “65281”是“！”，“65373”是“｝”
		if(code >= 65281&&code < 65373){
			//     “65248”是转换码距
			result+=String.fromCharCode(str.charCodeAt(i)-65248);
		} else {
			result+=str.charAt(i);
		}
	}  
	return result;  
}

//]]></script>
</body>
</html>
