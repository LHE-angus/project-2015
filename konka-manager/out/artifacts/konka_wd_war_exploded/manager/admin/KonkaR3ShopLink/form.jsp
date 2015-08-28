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
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/KonkaR3ShopLink" >
      <html-el:hidden property="link_id" styleId="link_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="mod_id" styleId="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <c:if test="${af.map.link_id ne null}">
     <table class="rtable3" width="100%" border="0" cellspacing="1"
		cellpadding="0">
		<tr>
			<th width="15%" height="45"
				style="font-size: 15px; font-weight: bold; font-family: Microsoft YAHEI, '黑体', '宋体';"><span>详细信息</span></th>
			<th width="10%">&nbsp;</th>
			<th width="10%">&nbsp;</th>
			<th width="20%">&nbsp;</th>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">客户名称：</td>
			<td><c:out value="${af.map.map.customer_name}" /></td>
			<td nowrap="nowrap" class="title_item" align="right">R3编码：</td>
			<td><c:out value="${af.map.map.r3_code}" /></td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">分公司：</td>
			<td><c:out value="${af.map.map.dept_name}"/></td>
			<td nowrap="nowrap" class="title_item" align="right"><span style="color:red">*&nbsp;</span>姓名：</td>
			<td><html-el:text property="real_name" styleId="real_name"/></td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item" align="right"><span style="color:red">*&nbsp;</span>职务：</td>
			<td><html-el:select property="position" styleId="position"
				styleClass="webinput">
				<html-el:option value="1">付款</html-el:option>
				<html-el:option value="2">对账</html-el:option>
				<html-el:option value="3">业务</html-el:option>
				<html-el:option value="4">法人</html-el:option>
				<html-el:option value="5">售后</html-el:option>
				<html-el:option value="6">收货</html-el:option>
				<html-el:option value="7">送货</html-el:option>
				<html-el:option value="8">发票</html-el:option>
				<html-el:option value="9">其他</html-el:option>
				</html-el:select></td>
			<td nowrap="nowrap" class="title_item" align="right">岗位：</td>
			<td><html-el:text property="job" styleId="job" /></td>
		</tr>
		<tr>
		    <td nowrap="nowrap" class="title_item" align="right"><span style="color:red">*&nbsp;</span>性别：</td>
			<td><html-el:select property="sex" styleId="sex"
				styleClass="webinput">
				<html-el:option value="0">男</html-el:option>
				<html-el:option value="1">女</html-el:option>
				<html-el:option value="2">未知</html-el:option>
			</html-el:select></td>
			<td nowrap="nowrap" class="title_item" align="right">生日：</td>
			<td>
			<fmt:formatDate
				value="${af.map.birthday}" var="_s_date" pattern="yyyy-MM-dd"/>
			<html-el:text property="birthday" styleId="birthday" 
				size="10" maxlength="10" value="${_s_date}" readonly="true"
				onclick="new Calendar(1920, 2021, 0).show(this);"
				style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">移动电话：</td>
			<td><html-el:text property="tel" styleId="tel"/></td>
			<td nowrap="nowrap" class="title_item" align="right">固定电话：</td>
			<td><html-el:text property="telephone" styleId="telephone"/></td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">电子邮箱：</td>
			<td><html-el:text property="email" styleId="email" /></td>
			<td nowrap="nowrap" class="title_item" align="right">传真：</td>
			<td><html-el:text property="fax" styleId="fax" /></td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">QQ号：</td>
			<td><html-el:text property="qq" styleId="qq" /></td>
			<td nowrap="nowrap" class="title_item" align="right">微信号：</td>
			<td><html-el:text property="weixin" styleId="weixin"/></td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">是否默认：</td>
			<td><html-el:select property="is_default" styleId="is_default"
				styleClass="webinput">
				<html-el:option value="0">是</html-el:option>
				<html-el:option value="1">否</html-el:option>
			</html-el:select></td>
			<td nowrap="nowrap" class="title_item" align="right">是否有效：</td>
			<td><html-el:select property="is_valid" styleId="is_valid"
				styleClass="webinput">
				<html-el:option value="0">是</html-el:option>
				<html-el:option value="1">否</html-el:option>
			</html-el:select></td>
		</tr>
		<tr>
		   <td nowrap="nowrap" class="title_item" align="right">客户喜好：</td>
		   <td colspan="5"><html-el:textarea property="customer_preferences" styleId="customer_preferences" style="resize:none;width:600px;height:100px"/></td>
		</tr>
        
        <tr>
        </tr>
      </table>
      </c:if>
      <c:if test="${af.map.link_id eq null}">
     <table class="rtable3" width="100%" border="0" cellspacing="1"
		cellpadding="0">
		<tr>
			<th width="15%" height="45"
				style="font-size: 15px; font-weight: bold; font-family: Microsoft YAHEI, '黑体', '宋体';"><span>详细信息</span></th>
			<th width="10%">&nbsp;</th>
			<th width="10%">&nbsp;</th>
			<th width="20%">&nbsp;</th>
		</tr>
			<tr>
				<td class="title_item" align="right" nowrap="nowrap"><font
					color="red">* </font>客户：</td>
				<td align="left" width="300px" colspan="3"><html-el:select
					property="customer_code_select" styleId="customer_code_select"
					onchange="customer_code_chg();">
					<html-el:option value="" style="width:300px">--请选择--</html-el:option>
					<c:forEach items="${customerList}" var="cur" varStatus="vs">
						<html-el:option value="${cur.map.customer_code}">[${cur.map.customer_code}]${cur.map.customer_name}</html-el:option>
					</c:forEach>
				</html-el:select></td>
			</tr>
			<tr>
			<td nowrap="nowrap" class="title_item" align="right"><span style="color:red">*&nbsp;</span>客户名称：</td>
			<td><html-el:text property="customer_name" styleId="customer_name" style="width:250px"/></td>
			<td nowrap="nowrap" class="title_item" align="right"><span style="color:red">*&nbsp;</span>R3编码：</td>
			<td><html-el:text property="r3_code" styleId="r3_code" /></td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item" align="right"><span style="color:red">*&nbsp;</span>分公司：</td>
			<td><html-el:select property="dept_id" styleId="dept_id"
				style="width:100px">
				<html-el:option value=" ">请选择...</html-el:option>
				<c:forEach var="cur" items="${sybDeptInfoList}">
					<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
				</c:forEach>
			</html-el:select></td>
			<td nowrap="nowrap" class="title_item" align="right"><span style="color:red">*&nbsp;</span>姓名：</td>
			<td><html-el:text property="real_name" styleId="real_name"/></td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item" align="right"><span style="color:red">*&nbsp;</span>职务：</td>
			<td><html-el:select property="position" styleId="position"
				styleClass="webinput">
				<html-el:option value="1">付款</html-el:option>
				<html-el:option value="2">对账</html-el:option>
				<html-el:option value="3">业务</html-el:option>
				<html-el:option value="4">法人</html-el:option>
				<html-el:option value="5">售后</html-el:option>
				<html-el:option value="6">收货</html-el:option>
				<html-el:option value="7">送货</html-el:option>
				<html-el:option value="8">发票</html-el:option>
				<html-el:option value="9">其他</html-el:option>
				</html-el:select></td>
			<td nowrap="nowrap" class="title_item" align="right">岗位：</td>
			<td><html-el:text property="job" styleId="job" /></td>
		</tr>
		<tr>
		    <td nowrap="nowrap" class="title_item" align="right"><span style="color:red">*&nbsp;</span>性别：</td>
			<td><html-el:select property="sex" styleId="sex"
				styleClass="webinput">
				<html-el:option value="0">男</html-el:option>
				<html-el:option value="1">女</html-el:option>
				<html-el:option value="2">未知</html-el:option>
			</html-el:select></td>
			<td nowrap="nowrap" class="title_item" align="right">生日：</td>
			<td>
			<fmt:formatDate
				value="${af.map.birthday}" var="_s_date" pattern="yyyy-MM-dd"/>
			<html-el:text property="birthday" styleId="birthday" 
				size="10" maxlength="10" value="${_s_date}" readonly="true"
				onclick="new Calendar(1920, 2021, 0).show(this);"
				style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">移动电话：</td>
			<td><html-el:text property="tel" styleId="tel"/></td>
			<td nowrap="nowrap" class="title_item" align="right">固定电话：</td>
			<td><html-el:text property="telephone" styleId="telephone"/></td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">电子邮箱：</td>
			<td><html-el:text property="email" styleId="email" /></td>
			<td nowrap="nowrap" class="title_item" align="right">传真：</td>
			<td><html-el:text property="fax" styleId="fax" /></td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">QQ号：</td>
			<td><html-el:text property="qq" styleId="qq" /></td>
			<td nowrap="nowrap" class="title_item" align="right">微信号：</td>
			<td><html-el:text property="weixin" styleId="weixin"/></td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">是否默认：</td>
			<td><html-el:select property="is_default" styleId="is_default"
				styleClass="webinput">
				<html-el:option value="0">是</html-el:option>
				<html-el:option value="1">否</html-el:option>
			</html-el:select></td>
			<td nowrap="nowrap" class="title_item" align="right">是否有效：</td>
			<td><html-el:select property="is_valid" styleId="is_valid"
				styleClass="webinput">
				<html-el:option value="0">是</html-el:option>
				<html-el:option value="1">否</html-el:option>
			</html-el:select></td>
		</tr>
		<tr>
		   <td nowrap="nowrap" class="title_item" align="right">客户喜好：</td>
		   <td colspan="5"><html-el:textarea property="customer_preferences" styleId="customer_preferences" style="resize:none;width:600px;height:100px"/></td>
		</tr>
        
        <tr>
        </tr>
      </table>
      </c:if>
      <table>
	<tr>
		<td style="width:500px;"></td>
          <td><html-el:button property="" value="提交" styleClass="but4" styleId="btn_submit" />
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();return false;" /></td>
		</tr>
	</table>

    </html-el:form>
    
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	//$("#is_del").attr("dataType", "Require").attr("msg", "请选择是否删除");
	//$("#is_lock").attr("dataType", "Require").attr("msg", "请选择是否锁定");
	//$("#par_type_id").attr("dataType", "Require").attr("msg", "请选择父类级别");
	//$("#order_sort").attr("dataType", "Require").attr("msg", "请填写排序值");
	//$("#order_sort" ).focus(function(){setOnlyInt(this);});
    
    
    var tel = $("#tel").val();
    var telephone = $("#telephone").val();
	//Ajax列表查询
	$("#btn_submit").click(function(){
	  //$("#id").attr("dataType","Require").attr("msg","请填写ID");
	  //$("#user_id").attr("dataType","Require").attr("msg","请填写用户ID");
	  //$("#password").attr("dataType","Require").attr("msg","请填写密码");
	  //$("#role_id").attr("dataType","Require").attr("msg","请填写角色ID");
	  //$("#user_name").attr("dataType","Require").attr("msg","请填写用户名");
	  $("#real_name").attr("dataType","Require").attr("msg","姓名不能为空！");
	  $("#customer_name").attr("dataType","Require").attr("msg","客户名称不能为空！");
	  $("#r3_code").attr("dataType","Require").attr("msg","R3编码不能为空！");
	  $("#dept_id").attr("dataType","Require").attr("msg","分公司名称不能为空！");
	    if($("#tel").val().length==0 && $("#telephone").val().length==0){
	    	alert("固定电话和移动电话不能同时为空！");
		    return ;
	    }
		if (Validator.Validate(this.form, 3)) {
			$(":button").attr("disabled", "true");
			$(":reset").attr("disabled", "true");
			this.form.submit();
		}
	});
});

function customer_code_chg(){
	$("#r3_code").val($("#customer_code_select").val());
	var old_customer_name=$("#customer_code_select").find("option:selected").text();
	 var start_index = old_customer_name.indexOf("]")+1;
	 var customer_name=(old_customer_name.substring(start_index)).trim();
	$("#customer_name").val(customer_name);
	
}

function setOnlyInt(obj) {
	$(obj).css("ime-mode", "disabled");
	$(obj).attr("t_value", "");
	$(obj).attr("o_value", "");
	$(obj).bind("dragenter",function(){
		return false;
	});
	$(obj).keypress(function (){
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).keyup(function (){
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).blur(function (){
		if(!obj.value.match(/^(?:\d+(?:\d+)?|\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\d+$/))obj.value=obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
		if(obj.value.length == 0 || isNaN(obj.value) || obj.value == 0) obj.value = "0";
	});
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

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
