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
    <html-el:form action="/admin/PromotersPeProdUser.do" enctype="multipart/form-data">
      <html-el:hidden property="user_id" value="${af.map.user_id}" />
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="pass_word_old" value="${af.map.pass_word}" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <html-el:hidden property="shop_change" value="${shop_change}" />
      <html-el:hidden property="store_ids" value="${store_ids}" />
      <html-el:hidden property="user_type" value="1" />
      <html-el:hidden property="queryString" />
      <html-el:hidden property="returnUrl" />
      <c:if test="${pe_prod_user_session.user_type eq 0}">
        <c:set value="true" var="is_admin" />
      </c:if>
      <div align="left">
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="datagrid">
          <tr class="oartop">
            <td height="28" colspan="4" style="padding-bottom:10px;fontweight:bold;"><c:if test="${empty af.map.id}">促销员添加</c:if>
              <c:if test="${not empty af.map.id}">促销员<span style="font-size:14px;font-weight:bolder;">${af.map.user_name}</span>的编辑</c:if></td>
          </tr>
          <tr class="oartop">
            <td colspan="4">组织信息</td>
          </tr>
          <tr>
            <td nowrap="nowrap" height="28" class="title_item" style="padding-left: 5%">用户类型：</td>
            <td colspan="3">促销员</td>
          </tr>
          <tr>
            <td height="28" width="15%" nowrap="nowrap" class="title_item" style="padding-left: 5%">部门：</td>
            <td colspan="3"><html-el:select property="dept_id" styleId="dept_id" style="width:200px;">
                <html-el:option value="">请选择...</html-el:option>
                <c:forEach var="cur" items="${peDeptList}">
                  <html-el:option value="${cur.dept_id}">${fn:escapeXml(cur.map.tree_name)}</html-el:option>
                </c:forEach>
              </html-el:select>
              &nbsp;<span style="color:red">*</span>
          <c:if test="${peRoleUser.role_id eq 10}"> <span class="note"><a href="KonkaDept.do?mod_id=902200&tree_param=${tree_param}" onclick="this.href=this.href + '&returnUrl=' + encodeURIComponent(window.location.href.toString());">点此添加部门</a></span> </c:if></td>
          </tr>
          <tr>
            <td height="28" width="15%" nowrap="nowrap" class="title_item" style="padding-left: 5%">关联门店：</td>
            <td colspan="3" nowrap="nowrap">门店名：
	            <input name="shop_name_like" id="shop_name_like" maxlength="" size="40"/>
	        <input class="but8" type="button" name="Submit4" value="点击查询 " id="btn_shop" />
            <table class="areause1" >
              <tbody>
                <tr>
                  <td><span id="area_name_0">供选择门店列表</span><br />
                    <html-el:select property="select1" multiple="true" style="width:260px;height:200px;" styleId="select1" onchange="moveSelected(this.form.select1, this.form.select2);"> </html-el:select></td>
                  <td width="20"></td>
                  <td><span id="area_name_1">选择的门店列表</span><br />
                    <html-el:select property="select2" multiple="true" style="width:260px;height:200px;" styleId="select2" onchange="moveSelected(this.form.select2, this.form.select1);">
                      <c:if test="${not empty konkaMobileSpRelationList}">
                        <html-el:optionsCollection label="map.store_name" value="shop_id" name="konkaMobileSpRelationList" />
                      </c:if>
                    </html-el:select></td>
                </tr>
                <tr>
                  <td colspan="3" ><font color="red">*&nbsp;列表项可以通过单击在两个区域间移动</font></td>
                </tr>
                <tr>
                  <td colspan="3" ><font color="red">*&nbsp;没有促销员的门店由上级客户的业务员负责填报</font></td>
                </tr>
                <tr>
                  <td colspan="3" ><font color="red">*&nbsp;业务员和门店的关系是通过客户和门店的关系关联的</font></td>
                </tr>
                <tr>
                  <td colspan="3" ><font color="red">*&nbsp;业务员需要事先完成与客户的匹配且门店要和客户之间关联起来（通过客户R3编码）</font></td>
                </tr>
              </tbody>
            </table></td>
          </tr>
          
           <tr class="oartop">
            <td colspan="4">岗位信息</td>
          </tr>
          <tr>
            <td nowrap="nowrap"  height="28" class="title_item" valign="top" style="padding-left: 5%">岗位ID：</td>
            <td colspan="3">
	            <c:if test="${not empty af.map.job_id}">${af.map.job_id}</c:if>
	            <c:if test="${empty af.map.job_id}">
	            <html-el:text property="job_id" styleId="job_id" maxlength="" size="40"/>&nbsp;<span style="color:red">*</span><span style="color:#f00;display:none;" id="job_id_exist_error">该岗位ID已被使用，请重新输入！</span><span style="color:#f00;display:none;" id="job_id_erro">岗位ID不能含空白字符！</span>
	            <div style="color:#f00;font-weight:800;">注：岗位ID一经填写，不可修改，并且由至少6位数字、字母、_ 或 - 组成；因各分公司岗位ID不一致，建议统一使用分公司简称的首字母（如深圳“SZ”）作前缀。</div></c:if>
            </td>
          </tr>
          <tr>
          	<td nowrap="nowrap"  height="28" class="title_item" style="padding-left: 5%">R3人员编号：</td>
          	<td><html-el:text property="r3_job_id" styleId="r3_job_id" /></td>
          </tr>
          <tr>
          <td nowrap="nowrap"  height="28" class="title_item" style="padding-left: 5%">促销员类型：</td>
          	<td>
          		<html-el:select property="sales_type" styleId="sales_type" style="width:80px;" value='${af.map.map.sales_type }'>
                	<html-el:option value="">请选择...</html-el:option>
                	<html-el:option value="1">兼职</html-el:option>
                	<html-el:option value="2">全职</html-el:option>
              </html-el:select><span style="color:red">*</span>
          	</td>
          	<td nowrap="nowrap"  height="28" class="title_item" >促销员状态：</td>
          	<td >
          		<html-el:select property="sales_stat" styleId="sales_stat" style="width:80px;" value='${af.map.map.sales_stat }'>
                	<html-el:option value="">请选择...</html-el:option>
                	<html-el:option value="1">在岗</html-el:option>
                	<html-el:option value="2">离岗</html-el:option>
                	<html-el:option value="3">离职</html-el:option>
              </html-el:select><span style="color:red">*</span>
          	</td>
          </tr>
          <tr>
          	<td nowrap="nowrap"  height="28" class="title_item" style="padding-left: 5%">上岗日期：</td>
          	<td width="20%">
          		<fmt:formatDate value="${af.map.map.work_date}" var="_work_date" pattern="yyyy-MM-dd" />
				<html-el:text property="work_date" styleId="work_date" size="10" maxlength="10" value="${_work_date}" readonly="true" onclick="new Calendar(1900, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" /><span style="color:red">*</span>
          	</td>
          	<td nowrap="nowrap"  height="28" class="title_item" width="10%" align="right">工龄：</td>
          	<td>
          		<html-el:text property="work_age" styleId=" " maxlength="20" size="10" value='${af.map.map.work_age }'/>
          	</td>
          </tr>
          
          <tr class="oartop">
            <td colspan="4">基础信息</td>
          </tr>
          <tr>
          	<td colspan="4">
	          	<table width="100%">
	          		<tr>
	          			<td nowrap="nowrap"  height="28" class="title_item" style="padding-left: 5%" width="15%">
	          				姓&nbsp;&nbsp;&nbsp;名：
	          			</td>
            			<td>
            				<html-el:text property="real_name" styleId="real_name" maxlength="20" size="20"/><span style="color:red">*</span>
            			</td>
	          			<td nowrap="nowrap"  height="28" class="title_item" style="padding-left: 5%" width="13%">
	          				身份证号码：
	          			</td>
            			<td>
            				<html-el:text property="identity_id" styleId="identity_id" maxlength="20" size="20" value='${af.map.map.identity_id }'/>
            			</td>
	          			<td nowrap="nowrap"  height="28" class="title_item" style="padding-left: 4%" width="13%">
	          				身份证有效期：
	          			</td>
            			<td>
            				<fmt:formatDate value="${af.map.map.id_valid_date}" var="_id_valid_date" pattern="yyyy-MM-dd" />
							<html-el:text property="id_valid_date" styleId="id_valid_date" size="10" maxlength="10" value="${_id_valid_date}" readonly="true" onclick="new Calendar(1900, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
            			</td>
	          		</tr>
	          		<tr>
	          			<td nowrap="nowrap"  height="28" class="title_item" style="padding-left: 5%">
	          				出生日期：
	          			</td>
            			<td>
            				<fmt:formatDate value="${af.map.birthday}" var="_birthday" pattern="yyyy-MM-dd" />
							<html-el:text property="birthday" styleId="birthday" size="10" maxlength="10" value="${_birthday}" readonly="true" onclick="new Calendar(1900, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
            			</td>
	          			<td nowrap="nowrap"  height="28" class="title_item" style="padding-left: 5%">
	          				性&nbsp;&nbsp;&nbsp;&nbsp;别：
	          			</td>
            			<td>
            				<html-el:select property="sex" styleId="sex" style="width:80px;">
			                	<html-el:option value="">请选择...</html-el:option>
			                	<html-el:option value="0">男</html-el:option>
			                	<html-el:option value="1">女</html-el:option>
			                	<html-el:option value="2">保密</html-el:option>
			              	</html-el:select>
            			</td>
	          			<td nowrap="nowrap"  height="28" class="title_item" style="padding-left: 4%">
	          				民&nbsp;&nbsp;&nbsp;&nbsp;族：
	          			</td>
            			<td>
            				<html-el:text property="nation_name" styleId="nation_name" maxlength="20" size="20" value='${af.map.map.nation_name }'/>
            			</td>
	          		</tr>
	          		<tr>
	          			<td nowrap="nowrap"  height="28" class="title_item" style="padding-left: 5%">
	          				婚&nbsp;&nbsp;&nbsp;&nbsp;否：
	          			</td>
            			<td>
            				<html-el:select property="married" styleId="married" style="width:80px;" value='${af.map.map.married }'>
			                	<html-el:option value="">请选择...</html-el:option>
			                	<html-el:option value="0">未婚</html-el:option>
			                	<html-el:option value="1">已婚</html-el:option>
			              	</html-el:select>
            			</td>
	          			<td nowrap="nowrap"  height="28" class="title_item" style="padding-left: 5%">
	          				学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;历：
	          			</td>
            			<td>
            				<html-el:select property="academic" styleId="academic" style="width:80px;" value='${af.map.map.academic }'>
			                	<html-el:option value="">请选择...</html-el:option>
			                	<c:forEach var="cur" items="${kbtdInfoList}">
                 					<html-el:option value="${cur.type_id}">${fn:escapeXml(cur.type_name)}</html-el:option>
                				</c:forEach>
			              	</html-el:select>
            			</td>
	          			<td nowrap="nowrap"  height="28" class="title_item" style="padding-left: 5%">
	          				QQ：
	          			</td>
            			<td>
            				<html-el:text property="link_qq" styleId="link_qq" maxlength="20" size="20"/>
            			</td>
	          		</tr>
	          		<tr>
	          			<td nowrap="nowrap"  height="28" class="title_item" style="padding-left: 5%">
	          				本人电话：
	          			</td>
            			<td>
            				<html-el:text property="link_phone" styleId="link_phone" maxlength="20" size="20"/>
            			</td>
	          			<td nowrap="nowrap"  height="28" class="title_item" style="padding-left: 5%">
	          				邮&nbsp;&nbsp;&nbsp;&nbsp;箱：
	          			</td>
            			<td colspan="3">
            				<html-el:text property="email" styleId="email" maxlength="20" size="20"/>
            			</td>
	          		</tr>
	          		<tr>
	          			<td nowrap="nowrap"  height="28" class="title_item" style="padding-left: 5%">
	          				紧急联系人：
	          			</td>
            			<td>
            				<html-el:text property="emergency_man" styleId="emergency_man" maxlength="20" size="20" value='${af.map.map.emergency_man }'/>
            			</td>
	          			<td nowrap="nowrap"  height="28" class="title_item" style="padding-left: 5%">
	          				紧急联系电话：
	          			</td>
            			<td colspan="3">
            				<html-el:text property="emergency_tel" styleId="emergency_tel" maxlength="20" size="20" value='${af.map.map.emergency_tel }'/>
            			</td>
	          		</tr>
	          		<tr>
	          			<td nowrap="nowrap"  height="28" class="title_item" style="padding-left: 5%">
	          				居住地：
	          			</td>
            			<td colspan="5">
            				<select name="province" id="province" style="width:180px;">
				                  <option value="">-请选择省/直辖市/自治区-</option>
			                </select>
			                &nbsp;
			                <select name="city" id="city" style="width:100px;">
			                  <option value="">-请选择市-</option>
			                </select>
			                &nbsp;
			                <select name="country" id="country" style="width:100px;">
			                  <option value="">-请选择县-</option>
			                </select>
			                &nbsp;
			                <select name="town" id="town" style="width:100px;">
			                  <option value="">-请选择乡镇-</option>
			                </select>
            			</td>
	          		</tr>
	          		<tr>
	          			<td nowrap="nowrap"  height="28" class="title_item" style="padding-left: 5%">
	          				街道地址：
	          			</td>
            			<td colspan="5">
            				<html-el:text property="link_addr" styleId="link_addr" maxlength="20" size="70"/>
            			</td>
	          		</tr>
	          		<tr>
	          			<td nowrap="nowrap"  height="28" class="title_item" style="padding-left: 5%">
	          				户口所在地：
	          			</td>
            			<td colspan="5">
            				<html-el:text property="residence_addr" styleId="residence_addr" maxlength="20" size="70" value='${af.map.map.residence_addr }'/>
            			</td>
	          		</tr>
	          		<tr>
	          			<td nowrap="nowrap"  height="28" class="title_item" style="padding-left: 5%">
	          				开户银行：
	          			</td>
            			<td>
            				<html-el:text property="bank_name" styleId="bank_name" maxlength="20" size="20" value='${af.map.map.bank_name }'/>
            			</td>
	          			<td nowrap="nowrap"  height="28" class="title_item" style="padding-left: 5%">
	          				银行账号：
	          			</td>
            			<td colspan="3">
            				<input type="hidden" name='bank_account' id='bank_account' value='${af.map.map.bank_account }'/>
            				<input name='show_bank_account' id='show_bank_account'/>
            			</td>
	          		</tr>
	          	</table>
          	</td>
          </tr>
          <tr class="oartop">
            <td colspan="4">工作经历</td>
          </tr>
          <tr>
              <td nowrap="nowrap" height="28" class="title_item" style="padding-left: 5%">工作简历：</td>
              <td colspan="3">
              	<c:if test="${empty attachment }">
	              	<input type="file" name='work_exper' id='work_exper'/>
              	</c:if>
              	<c:if test="${not empty attachment }">
              		<a href="${ctx}/manager/admin/Download.do?method=downloadFile1&save_name=${attachment.save_name}" target="_blank">${attachment.file_name}</a>&nbsp;&nbsp;&nbsp;
					<img src="../../images/x.gif" style="vertical-align:middle; cursor: pointer;" id="imgDelFile" title="删除"/>
              	</c:if>
              </td>
          </tr>
          <tr class="oartop">
            <td colspan="4">登录信息</td>
          </tr>
          <c:if test="${empty af.map.user_name}">
            <tr>
              <td nowrap="nowrap" height="28" class="title_item" style="padding-left: 5%">登录用户名：</td>
              <td colspan="3"><html-el:text property="user_name" styleId="user_name" size="40" maxlength="20" />
                &nbsp;<span style="color:red">*</span><span style="color:#f00;display:none;" id="user_name_exist_error">该登录名已被使用，请重新输入！</span><span style="color:#f00;display:none;" id="user_name_erro">登录名不能含空白字符！</span></td>
            </tr>
          </c:if>
          <c:if test="${not empty af.map.user_name}">
            <tr>
              <td nowrap="nowrap" height="28" class="title_item" style="padding-left: 5%">登录用户名：</td>
              <td colspan="3"><c:out value="${af.map.user_name}" /></td>
            </tr>
          </c:if>
          <tr>
            <td nowrap="nowrap" height="28" class="title_item" style="padding-left: 5%">登录密码：</td>
            <td colspan="3"><html-el:password property="pass_word" styleId="pass_word" size="40" maxlength="20" />
               &nbsp;<span style="color:red">*</span>密码中间切勿输入空格字符！&nbsp;&nbsp;</td>
          </tr>
           <tr>
            <td nowrap="nowrap" height="28" class="title_item" style="padding-left: 5%">排序值：</td>
            <td><html-el:text property="order_value" styleId="order_value" style="width:40px;" maxlength="4" size="4" styleClass="webinput" />
                               取值范围：0 - 9999，值越大，显示越靠前。&nbsp;&nbsp;</td>
          </tr>
          <tr>
            <td colspan="6" height="40"  align="center"><input class="but4" type="button" name="Submit4" value=" 保存 " id="btn_submit" />
              <input class="btn_reset" type="reset"  value="重填" id="reset" />
              <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" id="btn_back" /></td>
          </tr>
        </table> 
      </div>
    </html-el:form>
  </div> 
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.pager.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript">//<![CDATA[
	$(document).ready(function(){
		var count = $("#bank_account").val();
		if(count.length>0){
			var str1 = count.substr(0,4);
			var str2 = count.substr(count.length-4);
			$("#show_bank_account").val(str1+"**********"+str2);
		}
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
		
		$("#user_name").attr("datatype", "Require").attr("msg", "请输入用户名！");
		$("#real_name").attr("datatype", "Chinese").attr("msg", "真实姓名只允许中文！");
		$("#dept_id").attr("datatype", "Require").attr("msg", "请选择部门！");
		$("#position_id").attr("datatype", "Require").attr("msg", "请选择职位！");
		$("#pass_word").attr("datatype", "Require").attr("msg", "请填写密码！");
		$("#sales_type").attr("datatype", "Require").attr("msg", "请选择促销员类型！");
		$("#sales_stat").attr("datatype", "Require").attr("msg", "请选择促销员状态！");
		$("#work_date").attr("datatype", "Require").attr("msg", "请选择上岗日期！");
		$("#job_id").attr("datatype", "Require").attr("msg", "请填写岗位ID！");
		
		$("#email").attr("datatype", "Email").attr("msg", "请填写正确的邮箱！").attr("require", "false");
		$("#link_phone" ).attr("datatype", "Mobile").attr("msg", "请填写正确的手机号码！").attr("require", "false");
		$("#emergency_tel" ).attr("datatype", "Mobile").attr("msg", "请填写正确的手机号码！").attr("require", "false");
		$("#r3_job_id" ).attr("datatype", "Number").attr("msg", "必须为数字！").attr("require", "false");
		$("#link_qq" ).attr("focus",setOnlyNum);
		$("#bank_account" ).attr("focus",setOnlyNum);
		$("#work_age" ).attr("focus",setOnlyNum);
		
		//输入姓名后，默认登录用户名
		$("#real_name").blur(function(){
			if(this.value!=''){
				$("#user_name").val($.trim(DBC2SBC(this.value)));
				
				$.ajax({
					type: "POST",
					url: "CustomerUsers.do",
					data: "method=validateName&user_name=" + $("#user_name").val(),
					dataType: "json",
					success: function(oper) {
						if (oper.result) {
							$("#user_name").val("");
						}
					}
				});
			}
		});
		
		/* $("#user_name").focus(function() {
		    //获得焦点时，如果值为默认值，则设置为空
		   /*  if (this.value == vdefault) {
		        this.value = "";
		        this.style.color='#333';
		    } */
		/*}).blur(function() {
		    //失去焦点时，如果值为空，则设置为默认值
		     if (this.value == "") {
		        this.value = vdefault;
		        this.style.color='#999999';
		    } 
		    
		  	//begin 转化全角为半角
	         
			//end 
		}); */

		$("#pass_word").focus(function() {
		    //获得焦点时，如果值为默认值，则设置为空
		    /* if (this.value == vdefault) {
		        this.value = "";
		        this.style.color='#333';
		    } */
		}).blur(function() {
		    //失去焦点时，如果值为空，则设置为默认值
		    /* if (this.value == "") {
		        this.value = vdefault;
		        this.style.color='#999999';
		    } */
		    
		  	//begin 转化全角为半角
	         $("#pass_word").val($.trim(DBC2SBC(this.value)));
			//end 
		});
		
		$("#btn_submit").click(function(){
			<c:if test="${empty af.map.user_name}">
				var user_name = $("#user_name").val();
				if("" == user_name || user_name.indexOf(' ')>-1) {
						$("#user_name_erro").show();
						return false;	
				}
		    </c:if>
			
			if(Validator.Validate(this.form, 3)){
				// 去掉R3人员编码前后空格
				$("#r3_job_id" ).val($.trim($("#r3_job_id" ).val()));
				var show_bank_account = $("#show_bank_account").val();
				
				if (show_bank_account!=''&&show_bank_account.indexOf('*')<0){
					$("#bank_account").val(show_bank_account);
					
					if(show_bank_account.length<15||show_bank_account.length>19){
						alert("请填写正确的银行账号");
						return;
					}
				}
				
				var opts=document.getElementById("select2");
				for(var i=0;i<opts.length;i++){
					opts[i].selected=true;
				}
				
				$("#btn_submit").attr("disabled", true);
				$("#reset").attr("disabled", true);
				$("#btn_back").attr("disabled", true);
				
				this.form.submit();
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
		

		// 验证用户名是否存在
		$("#user_name").blur(function(){
			$("#user_name").val($.trim(DBC2SBC(this.value)));
			$("#btn_submit").attr("disabled", "disabled");
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
						$("#user_name_exist_error").show();
						$("#btn_submit").attr("disabled", "disabled");
						$("#user_name").css("background-color", "#ddcc00");
						$("#user_name").focus();
					} else {
						$("#user_name_exist_error").hide();
						$("#user_name").css("background-color", "#fff");
						$("#btn_submit").removeAttr("disabled");
					}
				}
			});
		});	
		
		//所在地市联动
		$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${province}"});
		$("#city"    ).attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${city}"});
		$("#country" ).attr({"subElement": "town","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${country}"});
		$("#town" ).attr({"defaultText": "-请选择乡镇-", "defaultValue": "", "selectedValue": "${town}"});
		$("#province").cs("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false);

		$("#btn_shop").click(function(){
		  $.ajax({
				type: "POST",
				url: "PromotersPeProdUser.do",
				data: "method=" + "ajaxSelectShopList" + "&shop_name_like=" + $("#shop_name_like").val() + "&dept_id=" + $("#dept_id").val() + "&t=" + (new Date()).getTime(),
				dataType: "json",
				error: function(request, settings) { alert('查询错误'); },
				success: function(data) {
					//console.info(data);
					if(data.status == "0"){
						alert("查询结果过大，请您缩小查询范围！");
					} else if (data.status == "-1"){
						alert("查询结果为空，请重新选择查询条件！");
					} else {
						var values =  data.data;
						$("#select1").empty();
						for(var i = 0; i < values.length; i++) {
							var opt = new Option(values[i].store_name, values[i].shop_id); 
							$("#select1").get(0).options.add(opt);
						}
					}
			    }
		  });
		});
	
		//删除附件
		$("img[id='imgDelFile']").each(function(){
	        $(this).click(function (){
	        	var beforeOneObj = $(this).parent();
	        	
				beforeOneObj.html("<input name='work_exper' type='file' id='work_exper' style='width: 300px;'/>");
	        });
	    });
		
		//验证身份证号码
		$("#identity_id").blur(function(){
			var val = $(this).val();
			var re = /^(\d{14}|\d{17})(\d|[xX])$/;
		    if (!re.test(val)) {
				alert("请输入正确的身份证号码！");
		    }else{
		    	//获取出生日期
		    	var tmp = val.substring(6, 14);
		    	tmp = tmp.substring(0, 4) + "-" + tmp.substring(4, 6) + "-" + tmp.substring(6);
		    	$("#birthday").val(tmp);
		    	
		    	//获取性别
		    	var sex = val.substring(12, 3);
		    	if(sex % 2 == 0){
		    		$("#sex").val("1");
		    	}else{
		    		$("#sex").val("0");
		    	}
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
			if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}
			if(this.value.length == 0) this.value = "0";
		});
		//this.text.selected;

}
function moveSelected(sourceSelect, targetSelect, isDelete){
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
function DBC2SBC(str){
    var result = '';
    for (var i=0 ; i<str.length; i++){
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


//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
