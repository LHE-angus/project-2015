<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>产品销售 &gt; 销售登记  &gt; 其他产品销售</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
-->
</style>
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;"/> 当前位置：产品销售 &gt; 销售登记&gt; 其他产品销售</div>
  <div class="rtabcont1">
    <html-el:form action="/JxcSellBill.do" >
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <input type="hidden" name="sn" value="${sn}"/>
      <html-el:hidden property="keySeq" />
      <html-el:hidden property="queryString" />
       <html-el:hidden property="p_index" styleId="p_index" />
       <html-el:hidden property="jnhm_flag" styleId="jnhm_flag" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th colspan="4" align="center">销售登记 </th>
        </tr>
        <tr>
          <td class="title_item"><font color="red">*</font>销售日期：</td>
          <td><html-el:text property="sell_date" styleId="sell_date" size="9" maxlength="9" readonly="true" styleClass="webinput" value="${today}" onclick="new Calendar(2011, 2031, 0).show(this);" /></td>
          <td class="title_item"><font color="red">*</font>销售编号：</td>
          <td><span><font color="red">NO.</font><font color="red">${sn}</font></span></td>
        </tr>
        
        <tr>
          <th colspan="4">客户信息</th>
        </tr>
        <tr>
          <td width="15%" class="title_item"><font color="red">*</font>购买人类型：</td>
          <td colspan="3"><label for="cus_idcard_type_1">
              <html-el:radio property="cus_idcard_type" styleId="cus_idcard_type_1" value="1">个人</html-el:radio>
            </label>
            &nbsp;
            <label for="cus_idcard_type_5">
              <html-el:radio property="cus_idcard_type" styleId="cus_idcard_type_5" value="5" >组织机构</html-el:radio>
            </label><span id="type_msg1" /></td>
        </tr>
        <tr id="tr_type_1" style="display: none;">
          <td class="title_item" width="16%"><span><font style="color: red">*</font></span>消费者姓名：</td>
          <td><input class="webinput" maxlength="40" name="customer_name" type="text"  id="customer_name" /></td>
          <td class="title_item" width="16%"><span><font style="color: red">*</font></span>身份证号：</td>
          <td><input class="webinput" maxlength="18" name="customer_idcard" type="text"  id="customer_idcard" /></td>
        </tr>
        <tr id="tr_type_5" style="display: none;">
          <td class="title_item" width="16%"><span><font style="color: red">*</font></span>机构名称：</td>
          <td><input class="webinput" maxlength="40" name="cus_name" type="text"  id="cus_name" /></td>
          <td class="title_item" width="16%"><span><font style="color: red">*</font></span>机构代码：</td>
          <td><input class="webinput" maxlength="20" name="cus_idcard" type="text"  id="cus_idcard" /></td>
        </tr>
        <!--
        <tr>
          <td class="title_item" width="16%"><span><font style="color: red">*</font></span>联系人姓名：</td>
          <td colspan="3"><input class="webinput" maxlength="128" name="customer_link_name" type="text"  id="customer_link_name" /></td>
        </tr>
        -->
        <tr>
          <td class="title_item"><span><font style="color: red">*</font></span>联系电话：</td>
          <td colspan="3"><input class="webinput" name="customer_tel" type="text"  id="customer_tel" />
            <span id="span_msg_tel__error" style="display: none;"><font style="color: red">请填写正确的联系电话</font></span></td>
        </tr>
        <tr>
          <td class="title_item"><span><font style="color: red">*</font></span>所属区域：</td>
          <td colspan="3">
          <html-el:select property="province" styleId="province" style="width:100px;" >
            <html-el:option value="">请选择...</html-el:option>
          </html-el:select>
          &nbsp;
          <html-el:select property="city" styleId="city" style="width:100px;" >
            <html-el:option value="">请选择...</html-el:option>
          </html-el:select>
          &nbsp;
          <html-el:select property="county" styleId="county" style="width:100px;">
            <html-el:option value="">请选择...</html-el:option>
          </html-el:select>
            
           </td>
        </tr>
        <tr>
          <td class="title_item">详细地址：</td>
          <td colspan="3"><input style="width:500px;" class="webinput" name="customer_addr" type="text"  id="customer_addr" maxlength="100" /></td>
        </tr>
        <tr>
          <td class="title_item">备注:</td>
          <td colspan="3"><input style="width:60%;" class="webinput"  name="customer_remark" type="text"  id="customer_remark"   maxlength="100"/>
            <!--<textarea class="webtextarea" name="customer_remark"  id="customer_remark"></textarea>--></td>
        </tr>
        <!--
         <tr>
           <td class="title_item" ><span><font style="color: red">*</font></span>电子邮箱：</td>
           <td colspan="3"><input class="webinput"  name="customer_e_mail" type="text"  id="customer_e_mail"   maxlength="50"/>
             <span id="span_msg_e_mail__error" style="display: none;"><font style="color: red">请填写正确的邮箱</font></span></td>
         </tr>
        -->
        <tr>
          <th colspan="4">产品信息</th>
        </tr>
        <tr>
          <td class="title_item"><font color="red">*</font>出货开发票日期：</td>
          <td><html-el:text property="ch_invoice_date" styleId="ch_invoice_date" size="9" maxlength="9" readonly="true" styleClass="webinput" value="${today}" onclick="new Calendar(2011, 2031, 0).show(this);" /></td>
          <td class="title_item"><font color="red">*</font>增值税（销售）发票号：</td>
          <td><input class="webinput" maxlength="64" name="invoice_bh" type="text"  id="invoice_bh" /></td>
        </tr>
        <tr>
          <td class="title_item"><font color="red">*</font>产品类型：</td>
          <td colspan="3"><html-el:select property="pd_type" styleId="pd_type" style="width:170px">
              <c:forEach items="${basePdTypeList}" var="cur">
                <html-el:option value="${cur.pd_type}">${cur.pd_name}</html-el:option>
              </c:forEach>
              <html-el:option value="0">其他</html-el:option>
            </html-el:select>
            <span id="otherPdType" style="display:none">
            <html-el:select property="jxc_pd_type_id" styleId="jxc_pd_type_id" styleClass="bdfont" style="width:170px">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach items="${JxcPdTypeList}" var="cur">
                <html-el:option value="${cur.id}">${cur.name}</html-el:option>
              </c:forEach>
            </html-el:select>
            </span></td>
        </tr>
        <tr>
          <td class="title_item"><font color="red">*</font>产品品牌：</td>
          <td colspan="3"><input  class="webinput" title="点击选择产品品牌" name="brand_name" id="brand_name"  readonly="readonly" onclick="openChild()" />
            <input class="webinput" type=hidden name="brand_id" id="brand_id" />
            <span class="jxcTip">点击输入框显示品牌数据</span></td>
        </tr>
        <tr>
          <td class="title_item"><font color="red">*</font>产品型号：</td>
          <td colspan="3"><span id="pd_id_span"></span>
            <html-el:hidden property="pd_id" styleId="pd_id"/></td>
        </tr>
        
        <tr>
          <th colspan="4">销售信息</th>
        </tr>
        <tr>
          <td class="title_item"><font color="red">*</font>销售数量：</td>
          <td colspan="3"><input type="hidden" value="" name="maxPdNum" id="maxPdNum" />
            <input class="webinput" name="count" type="text" id="count" maxlength="8" value="1"/></td>
        </tr>
        <tr>
          <td class="title_item"><font color="red">*</font>销售单价：</td>
          <td colspan="3"><input type="hidden" value="" name="maxPdNum" id="maxPdNum" />
            <input class="webinput" name="price" type="text" id="price" maxlength="8" value="0"/></td>
        </tr>
        <tr>
          <td class="title_item">产品唯一编码：</td>
          <td colspan="3"><input class="webinput" maxlength="2000" name="pd_unique_code" type="text" id="pd_unique_code" style="width:90%;" readonly="readonly"/>
          <br/><span class="jxcTip"  id="span_tip">如果是节能惠民产品，每台都有产品唯一编码,请输入与销售台数相同个数的产品唯一编码并以","分隔</span>
          </td>
        </tr>
        <tr>
          <td class="title_item">单台补贴金额￥：</td>
          <td colspan="3"><input class="webinput" name="allowance" type="text" id="allowance"  style="width:10%;" readonly="readonly"/>
          	<span class="jxcTip" id="allowance_list"></span></td>
        </tr>
        <tr>
          <td class="title_item">总补贴金额￥：</td>
          <td colspan="3"><input class="webinput" name="allowance_money" type="text" id="allowance_money"  readonly="readonly"/></td>
        </tr>
        <tr>
          <td class="title_item">优惠：</td>
          <td colspan="3"><input class="webinput" name="favorable" type="text" id="favorable"  maxlength="100" style="width:60%;" readonly="readonly"/></td>
        </tr>
        <tr>
          <td class="title_item">销售金额￥：</td>
          <td colspan="3"><input class="webinput" name="total_price" type="text" id="total_price"  maxlength="12" value="0" readonly="readonly"/></td>
        </tr>
        <tr class="class_konka" style="display:none;">
          <td class="title_item">产品编号：</td>
          <td colspan="3"><input class="webinput" name="pd_bh" type="text" id="pd_bh"  maxlength="100"/></td>
        </tr>
        <tr  class="class_konka" style="display:none;">
          <td class="title_item">产品串号：</td>
          <td colspan="3"><input class="webinput" name="pd_ch" type="text" id="pd_ch"  maxlength="100"/></td>
        </tr>
        <tr >
          <td class="title_item">说明：</td>
          <td colspan="3"><input style="width:60%;" class="webinput" name="remarks" type="text" id="remarks"  maxlength="100"/></td>
        </tr>
        <tr>
          <td class="title_item">经办人：</td>
          <td colspan="3"><input type="hidden" value="" name="maxPdNum" id="maxPdNum" />
            <input class="webinput" name="opr_man" type="text" id="opr_man" value="${userName}" size="10" maxlength="20" /></td>
        </tr>
        <tr>
          <td class="title_item">应收金额(￥)：</td>
          <td><input type="hidden" value="" name="maxPdNum" id="maxPdNum" />
            <input class="webinput" name="money" type="text" id="money"  maxlength="12" value="0.00" readonly="readonly"/></td>
          <td class="title_item"><font color="red">*</font>本次收款(￥)：</td>
          <td><input type="hidden" value="" name="maxPdNum" id="maxPdNum" />
            <input class="webinput" name="pay_money" type="text" id="pay_money"  maxlength="12" value="0.00"/></td>
        </tr>
        <tr>
          <td colspan="4" align="center"><input class="bgButtonSave" type="button" name="btn_submit" id="btn_submit"  value="保 存" />
            &nbsp;
            <input class="bgButtonBack" type="button" name="btn_back"  id="btn_back" value="返 回" onclick="history.back();"/></td>
        </tr>
        <!--<tr>
            <td class="title_item">上传附件：</td>
            <td colspan="3"><div id="divFileHidden" style="display: none;">
                <input name="file_hidden" type="file" id="file_hidden" style="width: 250px;" />
                <img src="../images/x.gif" style="vertical-align:middle; cursor: pointer;" id="imgDelTr" title="删除"/></div>
              <div id="divFile">
                <input name="file_show" type="file" id="file_show" style="width: 250px;" />
                <img src="../images/+.gif" style="vertical-align:middle; cursor: pointer;" id="imgAddTr" title="再添加一个" /></div>
               <c:if test="${not empty attachmentList}">
          <tr>
            <td height="28" class="title_item">已上传的附件：</td>
            <td colspan="3"><ol>
                <c:forEach var="cur" items="${attachmentList}" varStatus="vs">
                  <li><a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}">${cur.file_name}</a>&nbsp;&nbsp;&nbsp;<a href="#" id="att_del_${cur.id}">删除</a></li>
                </c:forEach>
              </ol></td>
            </tr>
           </c:if>
            </td>
          </tr>
      -->
      </table>
    </html-el:form>
  </div>
    <!-- 弹出层部分 start -->
  <div id="addOrgSnDiv" style="display:none;" title="填写组织机构代码">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
      <tr>
        <td class="title_item"><font color="red">*</font> 组织机构代码：</td>
        <td align="left"><input type="text" name="org_sn" id="org_sn" class="webinput" maxlength="40"/></td>
      </tr>
      <tr>
        <td colspan="2" align="center"><input type="button" name="addOrgSnButton" class="bgButton" value="添加" onclick="ajaxAddOrgSn(this);" /></td>
      </tr>
    </table>
  </div>
  <!-- 弹出层部分 end --> 
  
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.jxc.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/external/bgiframe/jquery.bgiframe.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script>
<script type="text/javascript"><!--//<![CDATA[
$(document).ready(function(){

	//验证是否存在组织机构代码
	checkOrgSn();
	
	//地市选择
	$("#province").attr({"subElement": "city", "defaultText": "请选择...", "defaultValue": "", "selectedValue": "${af.map.province}", "datatype": "Require", "msg": "请选择省份"});
	$("#city").attr({"subElement": "county", "defaultText": "请选择...", "defaultValue": "", "selectedValue": "${af.map.city}", "datatype": "Require", "msg": "请选择市"});
	$("#county").attr({"defaultText": "请选择...", "defaultValue": "", "selectedValue": "${af.map.country}"});
	$("#province").cs("${ctx}/jxcnokey/CsAjax.do?method=getBaseProvinceList", "p_index", "0", false);
	$("#province").change(function(){
		if (this.value.length != 0) {
			this.form.p_index.value = this.value;
			$("#city").val("");
			$("#county").val("");
		}
	});
	$("#city").change(function(){
		if (this.value.length != 0) {
			this.form.p_index.value = this.value;
			$("#county").val("");
		} else {
			this.form.p_index.value = this.form.province.value;
		}
	});
	$("#county").change(function(){
		if (this.value.length != 0) {
			this.form.p_index.value=this.value;
		} else {
			this.form.p_index.value = this.form.city.value;
		}
	});
	
	/**  客户ajax start
	$("#province").attr({"subElement": "city", "defaultText": "请选择省...", "defaultValue": "", "selectedValue": "${af.map.province}"});
	$("#city").attr({"subElement": "country", "defaultText": "请选择市...", "defaultValue": "", "selectedValue": "${af.map.city}"});
	$("#country" ).attr({"defaultText": "请选择县...", "defaultValue": "", "selectedValue": "${af.map.country}"});
	//$("#end_date"  ).attr("dataType" , "Require").attr("msg" , "请选择失效时间！");
	
	$("#province").cs("${ctx}/jxc/CsAjax.do?method=getBaseProvinceList", "p_index", "0", false);
	
	$("#province").change(function(){
		if (this.value.length != 0) {
			this.form.p_index.value = this.value;
			$("#city").val("");
			$("#county").val("");
		}
	});

	$("#city").change(function(){
		if (this.value.length != 0) {
			this.form.p_index.value = this.value;
			$("#county").val("");
		} else {
			this.form.p_index.value = this.form.province.value;
		}
	});

	$("#county").change(function(){
		if (this.value.length != 0) {
			this.form.p_index.value=this.value;
		} else {
			this.form.p_index.value = this.form.city.value;
		}
	});


	$("#tel").bind("blur",function(){
			var mobile=/^((\(\d{2,3}\))|(\d{3}\-))?(1[3458]\d{9})$/; 
			var phone=/^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/;
			if (mobile.exec(this.value)||phone.exec(this.value)){
				$("#span_msg_tel__error").hide();
				$("#addCustomerButton").removeAttr("disabled");
				
			}else{
				$("#span_msg_tel__error").show();
				$("#addCustomerButton").attr("disabled","true");
			}

		});
	$("#e_mail").bind("blur",function(){
			var email=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/; 
			if (email.exec(this.value)){
				$("#span_msg_e_mail__error").hide();
				$("#addCustomerButton").removeAttr("disabled");
			}else{
				$("#span_msg_e_mail__error").show();
				$("#addCustomerButton").attr("disabled","true");
			}

		});
		
	$("#customer_id").attr("dataType","Require").attr("msg","请选择客户！");

	客户ajax end
	*/
	$("#sell_date").attr("dataType","Require").attr("msg","请选择销售日期！");
	$("#ch_invoice_date").attr("dataType","Require").attr("msg","请选择出货开发票日期！");
	$("#invoice_bh").attr("dataType","Require").attr("msg","请填写增值税（销售）发票号！");
	$("#brand_id").attr("dataType","Require").attr("msg","请选择品牌！");
	$("#count").attr("dataType","Number").attr("msg","请填写销售数量,且只能为正整数据！");
	//$("#pd_unique_code").attr("dataType","Require").attr("msg","请填写与销售数量个数相同，并且不重复的产品唯一编码！");
	//$("#price").attr("dataType","Currency").attr("msg","请填写零售价，且只能为正数！");
	$("#price").attr("dataType","Currency").attr("msg","请填写零售价，且只能为正数！");
	$("#pay_money").attr("dataType","Currency").attr("msg","请填写本次付款，且只能为正数！");
	//$("#pay_money").focus(setOnlyNum).attr("dataType","Currency").attr("msg","请填写本次付款！");
	/*********************验证客户信息 start**********************************/
	$("#customer_name").attr("dataType","Require").attr("msg","请填写消费者姓名！");
	//$("#customer_link_name").attr("dataType","Require").attr("msg","请填写联系人姓名！");
	//$("#customer_addr").attr("dataType","Require").attr("msg","请填写地址！");
	$("#customer_tel").attr("dataType","Require").attr("msg","请填写联系电话！");
	
	$("#customer_tel").bind("blur",function(){
		var mobile=/^((\(\d{2,3}\))|(\d{3}\-))?(1[3458]\d{9})$/; 
		var phone=/^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/;
		if (mobile.exec(this.value)||phone.exec(this.value)){
			$("#span_msg_tel__error").hide();
			$("#addCustomerButton").removeAttr("disabled");
			
		}else{
			$("#span_msg_tel__error").show();
			$("#addCustomerButton").attr("disabled","true");
		}

	});

	/*购买人类型 Begin*/
	$("input[type='radio'][name='cus_idcard_type']").click(function(){
		var cus_idcard_type = $(this).val();
		if(1 == cus_idcard_type) { //个人
			$("#tr_type_1").show();
			$("#tr_type_5").hide();
			$("#type_msg").remove();
			$("#cus_name").removeAttr("dataType");
			$("#cus_idcard").removeAttr("dataType");
		} 
		if(5 == cus_idcard_type) { //组织机构
			$("#tr_type_1").hide();
			$("#tr_type_5").show();
			$("#type_msg").remove();
			$("#customer_name").removeAttr("dataType");
			$("#customer_idcard").removeAttr("dataType");
		}
	});
	/*购买人类型 End*/
	

	$("#imgAddTr").click(function (){
        $("#divFileHidden").clone().find("#file_hidden").attr("name", "file_" + new Date().getTime()).end().appendTo($("#divFile")).show();
        $("img[id='imgDelTr']").each(function(){
            $(this).click(function (){
                $(this).parent().remove();
            });
        });
    });
	/*********************验证客户信息 end**********************************/

		var f = document.forms[0];
		$("#btn_submit").click(function(){//提交

			
			//节能惠民产品，pd_unique_code产品唯一编码必填
			//if($("#allowance").val() != ""){
			//	$("#pd_unique_code").attr("dataType","Require").attr("msg","请填写与销售数量个数相同，并且不重复的产品唯一编码");
			//}else{
			//	$("#pd_unique_code").removeAttr("dataType");
			//}
			
			if($('input:radio[name="cus_idcard_type"]:checked').val() == null){
				$("#type_msg").remove();
				$("#type_msg1").after('<span id="type_msg" style="color:#FF0000;">*&nbsp;请选择购买人类型。<\/span>');
				  return false ;
			} else {
				$("#type_msg").remove();
			}

			if($('input:radio[name="cus_idcard_type"]:checked').val() == 1){
				$("#cus_name").removeAttr("dataType");
				$("#cus_idcard").removeAttr("dataType");
				$("#customer_name").attr("dataType","Require").attr("msg","请填写消费者姓名");
				$("#customer_idcard").attr("dataType","Require").attr("msg","请填写身份证");
			} else if($('input:radio[name="cus_idcard_type"]:checked').val() == 5) {
				$("#customer_name").removeAttr("dataType");
				$("#customer_idcard").removeAttr("dataType");
				$("#cus_name").attr("dataType","Require").attr("msg","请填写机构名称");
				$("#cus_idcard").attr("dataType","Require").attr("msg","请填写机构代码");
			}
			
			var isSubmit = Validator.Validate(f, 3);
			if (isSubmit) {
				var count = $("#count").val();
				if(count == 0){
					alert("您输入的销售数量为0,请重新输入!");
					$("#count").focus();
					return false;
				}
				if($("#pd_id").val()==''){
					alert("请选择产品型号，为空将不能提交！");
					$("#pd_id").focus();
					return false;
				}

				if ($("#jnhm_flag").val() != "") {
					if($("#allowance").val() == ""){
						alert("请选择输入单台补贴金额");
						$("#allowance").focus();
						return false;
					} else {
						if ($("#jnhm_flag").val().indexOf($("#allowance").val()) < 0) {
							alert("您输入单台补贴金额不在补贴金额列表中，请重新输入");
							$("#allowance").focus();
							return false;
						}
					}
					if($("#pd_unique_code").val() == ""){
						alert("请填写与销售数量个数相同，并且不重复的产品唯一编码");
						$("#pd_unique_code").focus();
						return false;
					} else {
						if ($("#pd_unique_code").val().split(",").length != $("#count").val()) {
							alert("请输入" + $("#count").val() + "个不重复的产品唯一编码");
						} else {
							// 验证产品唯一编码是否已重复
							$.ajax({
								type: "POST",
								url: "JxcSellBill.do",
								data: "method=checkPdUnique&pd_type_id=" + $("#pd_type").val() + "&brand_id=" + $("#brand_id").val() + "&pd_unique_codes=" + $("#pd_unique_code").val(),
								dataType: "json",
								error: function(request, settings) {alert("数据加载请求失败,请重新选择产品尝试！");return false; },
								success: function(data) {
									if(data.length > 1){
										return false;
										
										
									}
								}
							});
						}
					}
				}
				
				$("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
				$("#btn_reset").attr("disabled", "true");
				$("#btn_back").attr("disabled", "true");
				f.submit();
			}
			
		});	
		
});

function checkOrgSn(){
	$.ajax({
		type: "POST",
		url: "JxcSellBill.do",
		data: "method=checkOrgSn"+ "&keySeq=${af.map.keySeq}",
		dataType: "json",
		error: function(request, settings) {alert("数据加载请求失败,未获取组织机构代码！");return false; },
		success: function(data) {
			if(data.length <=1){
				//弹窗，输入机构代码
				$("#addOrgSnDiv").dialog({// 弹出添加层
					width: 700,
					height: 500,
					resizable: false,
					closeOnEscape: false,
					dialogClass: "hide-my-dialog" ,
					//beforeclose: function(){if($("#org_sn").val() == ""){alert("请填写组织机构代码"); return false;} },
					//position:'right',
					modal : true
				}).dialog("open");
			}
		}
	});

}

//添加验证供应商:验证供应商是否存在
function ajaxAddOrgSn(obj){
	var shop_id = ${user.entp_shop.shop_id};//商铺id
	var org_sn = $("#org_sn").val();
	if("" == $.trim(org_sn)) {
		alert("请填写组织机构代码");
		return false;
	}
	obj.disabled = true;
	$.ajax({
		type: "POST",
		url: "JxcSellBill.do",
		async : false,
		data: "method=saveOrgSn&org_sn=" + org_sn + "&keySeq=${af.map.keySeq}" ,
		dataType: "json",
		error: function(request, settings) {alert("数据加载请求失败！"); },
		success: function(id) {
			if(0 < id) {
				$.jGrowl("恭喜，信息添加成功！",{theme:  'success'});
			} else {
				if(-1 == id){
					$.jGrowl("对不起，密钥key丢失或者没有插入！",{theme:  'error'});
				}
				if(-2 == id){
					$.jGrowl("对不起，您填写的组织机构代码已存在，请重新填写！",{theme:  'error'});
					obj.disabled = false;
					return false;
				}
			}
			obj.disabled = false;
			$("#addOrgSnDiv").dialog("close");
		}
	});
}


var nameHtml = '<input type="text" name="name" value="${af.map.name}" id="name" style="width:220px;" maxlength="50" dataType="Require" msg="请填写产品型号！"/>';
var outSysIdHtml = '<select name="out_sys_id" multiple="multiple" style="display:none;" id="out_sys_id" styleClass="bdfont"></select>';


/*产品类型 （与自定义大类控制是否下级可选）Begin*/
$("#pd_type").change(function(){
	backSellInfo();
	var pd_type = $(this).val();
	$("#brand_id").val("");
	$("#brand_name").val("");
	if(0 == pd_type) {// 选择"其他"时，提供自己的产品大类列表选择
		$("#otherPdType").show();
		$("#jxc_pd_type_id").attr("dataType","Require").attr("msg","请选择其他产品类型！");
	} else {
		$("#otherPdType").hide();
		$("#jxc_pd_type_id").removeAttr("dataType").val("");
	}
	
});

//计算销售金额
$("#count").bind("keyup", function(){
	var _reg = /^\d+$/;
	var _this = $(this);
	var this_value = _this.val();//进货数量
	if("" != this_value) {
		if (!_reg.test(this_value)) {//非数字清空金额
			alert(_this.attr("msg"));
			_this.val(1);
			$("#total_price").val("0.00");
			$("#money").val("0.00");
			$("#pay_money").val("0.00");
			return false;
		}

		/*if(Number(this_value) > parseFloat($("#maxPdNum").val())){
			alert("您输入的销售产品数量超过该产品最大库存[" + parseFloat($(this).prev().val()) + "]，请重新输入！");
			_this.focus();
			_this.val(1);
		} */
		getMoney(this_value);
	}

		
});

$("#price").bind("change",function(){
	var _this = $(this);
	var this_value = $.trim(_this.val());
	//alert("this_value--"+this_value);
	if ("" != this_value){
		var reg = /^\d+(\.\d+)?$/;
		if (!reg.test(this_value)) {
			alert(_this.attr("msg"));
			_this.val(0);
			$("#total_price").val("0.00");
			$("#money").val("0.00");
			$("#pay_money").val("0.00");
			return false;
		}
		getMoney(this_value);
	}
});

$("#allowance").bind("keyup", function(){
	var _reg = /^\d+$/;
	var _this = $(this);
	var this_value = _this.val();//补贴金额
	if("" != this_value) {
		if (!_reg.test(this_value)) {//非数字清空金额
			//alert(_this.attr("msg"));
			_this.val("");
			$("#allowance_money").val("");
			return false;
		}
		getMoney(this_value);
	}

		
});


/*  添加新客户  Begin*/
$("#addOtherCustomerSpan").click(function(){
	$("#jxc_pd_type_name").val("");
	$("#addCustomerDiv").dialog({
		width: 645,
		height: 530,
		resizable: false,
		position:'right',
		modal : true
	}).dialog("open");
});
/*  添加新客户   End*/  
/* 计算金额*/
function getMoney(this_value){
	if("" == this_value) {//非数字清空金额
		$("#total_price").val("0.00");
		$("#money").val("0.00");
		$("#pay_money").val("0.00");

		$("#allowance").val("");
		$("#allowance_money").val("");
		return false;
	}
	var count = $("#count").val();
	var price = $("#price").val();
	var allowance = $("#allowance").val();// 补贴标准
	if (allowance != "") {
		$("#allowance_money").val(count*allowance);
		$("#favorable").val("节能惠民补贴");
	}else{
		$("#allowance_money").val("");
		$("#favorable").val("");
		allowance = 0;
	}
	$("#total_price").val((parseFloat(price)*parseFloat(count)).toFixed(2));
	$("#money").val((parseFloat(price)*parseFloat(count) - parseFloat(allowance)*parseFloat(count)).toFixed(2));
	$("#pay_money").val((parseFloat(price)*parseFloat(count) - parseFloat(allowance)*parseFloat(count) ).toFixed(2));
	
}

/* 恢复初始销售信息 */
function backSellInfo(){

	$("#kcTip").remove();
	$("#pd_id").val("");//清空选择的型号
	$("#pd_id_span").empty();//清空选择的型号
	
	$("#count").val(1);//赋初值
 	$("#price").val(0);
	$("#total_price").val("0.00");
	$("#money").val("0.00");
	$("#pay_money").val("0.00");
	
	$("#allowance").val("");
	$("#allowance_money").val("");
}

function openChild(){//根据大类选品牌
 	var pd_type = $("#pd_type").val();
 	var returnValue = window.showModalDialog("JxcSellBill.do?method=chooseBrand&pd_type="+pd_type+"&time="+new Date(), window, "dialogWidth:800px;status:no;dialogHeight:680px"); 
  	if(returnValue != null) {
		var value = new Array();
		value = returnValue.split(",");
   		document.forms[0].brand_name.value = value[0];
    	document.forms[0].brand_id.value = value[1];
  	}
 	var brand_id = $("#brand_id").val();
  	if (brand_id!='' && brand_id=="${KONKA_BRAND_ID}" ) {//是康佳产品显示pd-bh/pd_ch
  		$(".class_konka").show();
	 } else {
		 $(".class_konka").hide();
 	}
    backSellInfo();
    getProductType(pd_type, brand_id);
}

function getProductType(pd_type, brand_id, out_sys_id){//根据大类、品牌选型号
	
	if (pd_type == "" || brand_id == "") {
		return false;
	}
	
	var own_sys = $("input[type='radio'][name='own_sys']:checked").val();
	if (undefined != out_sys_id && 0 == own_sys && "" != "${af.map.name}" && out_sys_id < 0) {
		return false;
	}
	var jxc_pd_type_id = $("#jxc_pd_type_id").val();
	$.ajax({
		type: "POST",
		url: 'JxcSellBill.do',
		data: "method=getPdModel&pd_type=" + pd_type + "&brand_id=" + brand_id + "&jxc_pd_type_id=" + jxc_pd_type_id + "&keySeq=${af.map.keySeq}",
		dataType: "json",
		async: false,
		error: function(request, settings) {alert("数据加载请求失败！"); },
		success: function(Dates) {
		
			$("#pd_id_span").empty();
			if (Dates.length > 1) {
				$("#pd_id_span").html(outSysIdHtml);
				notFindPd();
				for ( var i = 0; i < Dates.length - 1; i++) {
					var md_name ="产品编码:["+ Dates[i].pd_id +"]  " + Dates[i].md_name;
					//$("#out_sys_id").get(0).options.add(new Option(Dates[i].md_name, Dates[i].pd_id));
					$("#out_sys_id").get(0).options.add(new Option(md_name, Dates[i].values));
					//document.forms[0].price.value=Data[i].price;//默认价格
				}
				
				var el = $("#out_sys_id").multiselect({
					selectedList: 1,
					multiple: false,
					minWidth:380,
					click:function(event,ui){
					if(ui.value != ""){
						var s = ui.value.split("#$");
						$("#pd_id").val(s[0]);
						$("#price").val(s[1]);//取零售价
						$("#maxPdNum").val(s[2]);//最大库存
						//$("#allowance").val(s[3]);//单台补贴金额
						if (s[3] != "") {
							$("#jnhm_flag").val(s[3]);// 用来提交时验证是节能惠民产品必须输入项目 产品唯一便民和补贴信息
							$("#allowance_list").text("节能惠民补贴金额参考值：" + s[3]);

							$("#pd_unique_code").removeAttr("readonly");
							$("#allowance").removeAttr("readonly");
							//$("#allowance_money").removeAttr("readonly");
							$("#favorable").removeAttr("readonly");
						} else {
							$("#jnhm_flag").val("");// 为空
							$("#allowance_list").text("");

							$("#pd_unique_code").attr("readonly","readonly").val("");
							$("#allowance").attr("readonly","readonly").val("");
							$("#allowance_money").attr("readonly","readonly").val("");
							$("#favorable").attr("readonly","readonly").val("");
						}
						$("#kcTip").remove();
						$("#count").after("<span style='color:red;padding-left:5px;' id='kcTip' class='maxPdNumTip'>该产品当前库存为：【" + s[2]+ "】</span>");
						getMoney(ui.value);//计算金额
					}
				}
				}).multiselectfilter({width:280});//.attr({"datatype" : "Ms" , "msg" : "请选择产品型号！"});
			   	$("#out_sys_id option[value='${af.map.out_sys_id}']").attr("selected", "selected");
				el.multiselect('refresh');
				
			} else {
				var own_sys = $("input[type='radio'][name='own_sys']:checked").val();
				if (0 == own_sys){
					$("#pd_id_span").html(nameHtml);
					
				} else {
					$("#pd_id_span").append('<span style="color:#f00;padding:5px;">没有型号,请重新选择品牌.</span>');//.append(getPdModelListHtml).append(outSysIdHiddenHtml);
					//alert($("#pd_id_span").html());
				}
			}
		}
	});
	if(undefined != out_sys_id && "" != out_sys_id){
		$("#out_sys_id").val(out_sys_id);
	}
}
function notFindPd(obj) {
	$("#notFindPd").click(function(){
		var own_sys = $("input[type='radio'][name='own_sys']:checked").val();
		if (0 == own_sys) {
			$("#pd_id_span").empty().append(nameHtml)
		} else {
			$("#pd_id_span").empty().append(getPdModelListHtml).append(outSysIdHiddenHtml);
			openChildForPdModel();
		}
	});
}


function gotoJxcBrandApply(){//申请品牌
	//window.location.href="JxcBrandApply.do?method=add&keySeq=${af.map.keySeq}";
}


function ajaxAddCustomer(obj){//添加新客户 
/**	obj.disabled = true;
	var name = $.trim($("#name").val());
	var link_name = $.trim($("#link_name").val());
	var tel = $.trim($("#tel").val());
	if ("" == name) {
		alert("请填写客户名称！");
		return false;
	}
	if ("" == link_name) {
		alert("请填写联系人姓名！");
		return false;
	}
	if ("" == tel) {
		alert("请填写联系人电话！");
		return false;
	}
	$.ajax({
		type: "POST",
		url: "JxcSellBill.do",
		async : false,
		data: "method=saveJxcCustomer&" + $("#addCustomentForm").serialize(),
		dataType: "json",
		error: function(request, settings) {alert("数据加载请求失败！"); },
		success: function(id) {
			if(-1 != id) {
				$.jGrowl("恭喜，信息添加成功！",{theme:  'success'});
				$("#customer_id").get(0).options.add(new Option(name, id));
				$("#customer_id").val(id);
			} else {
				$.jGrowl("对不起，您添加的产品类型名称已存在！",{theme:  'error'});
			}
			obj.disabled = false;
			$("#addCustomerDiv").dialog("close");
		}
	});
	*/
}

//正则表达式：只能输入数字和带小数点数字
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
//正则表达式：只能输入数字
function setOnlyNum1() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	var regInteger = /^[\+]?\d+$/;
	$(this).keypress(function (){
		if(!this.value.match(regInteger))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(regInteger))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(regInteger))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(regInteger))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(regInteger))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		if(this.value.length == 0) this.value = "0";
	});
}
//]]>--></script>
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>