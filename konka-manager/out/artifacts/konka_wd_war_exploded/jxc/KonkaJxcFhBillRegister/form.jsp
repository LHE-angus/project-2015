<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;"/> 当前位置：${naviString}</div>
  <div class="rtabcont1">
    <html-el:form action="/KonkaJxcFhBillRegister">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="queryString" />
      <html-el:hidden property="sn" styleId="sn" value="${sn}" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th colspan="8">发货登记</th>
        </tr>
         <tr>
           <td  class="title_item" ><span style="color:red">*</span>
            <c:if test="${not empty shiyebu}">分公司：</c:if>
            <c:if test="${not empty fenjingban}">网点：</c:if>
            </td>
            <td colspan="7">
            <html-el:select property="branch_or_wd_id" styleId="branch_or_wd_id">
              <c:if test="${not empty shiyebu}">
                <option value="">请选择分公司...</option>
                <c:forEach items="${branchDeptList}" var="cur">
                  <option value="${cur.dept_id}">${cur.dept_name}</option>
                </c:forEach>
              </c:if>
              <c:if test="${not empty fenjingban}">
                <option value="">请选择网点...</option>
                <c:forEach items="${konkaR3ShopList}" var="cur">
                  <option value="${cur.id}">${cur.customer_name}</option>
                </c:forEach>
              </c:if>
            </html-el:select></td>
            </tr>
          
        <tr>
          <td class="title_item"><font color="red">*</font>发货日期：</td>
          <td colspan="3">
              <html-el:text property="fh_date" styleId="fh_date" size="9" maxlength="9" readonly="true" styleClass="webinput"  onclick="new Calendar(2011, 2031, 0).show(this);" />
          </td>
          <td class="title_item"><font color="red">*</font>发货编号：</td>
          <td colspan="3"><span><font color="red">NO.</font><font color="red">${sn}</font></span></td>
        </tr>
        <!--
        <tr>
          <td colspan="4" align="center">发货编号：<span><font color="red">NO.</font><font color="red">${sn}</font></span></td>
          <td colspan="4" align="left" ><span style="color:red">*</span>
            <c:if test="${not empty shiyebu}">分公司：</c:if>
            <c:if test="${not empty fenjingban}">网点：</c:if>
            <html-el:select property="branch_or_wd_id" styleId="branch_or_wd_id" multiple="multiple" style="display:none;">
              <c:if test="${not empty shiyebu}">
                <c:forEach items="${branchDeptList}" var="cur">
                  <option value="${cur.dept_id}">${cur.dept_name}</option>
                </c:forEach>
              </c:if>
              <c:if test="${not empty fenjingban}">
                <c:forEach items="${konkaR3ShopList}" var="cur">
                  <option value="${cur.id}">${cur.customer_name}</option>
                </c:forEach>
              </c:if>
            </html-el:select></td>
        </tr>
        -->
        <tr class="title_top">
          <td width="15%" align="center" nowrap="nowrap" bgcolor="#fff2dc"><span style="color:red">*</span><font class="bigall">出货仓库</font></td>
          <td width="15%" align="center" nowrap="nowrap" bgcolor="#fff2dc"><span style="color:red">*</span><font class="bigall">产品类型</font></td>
          <td width="15%" align="center" nowrap="nowrap" bgcolor="#fff2dc"><span style="color:red">*</span><font class="bigall">产品型号</font></td>
          <td width="10%" align="center" nowrap="nowrap" bgcolor="#fff2dc"><span style="color:red">*</span><font class="bigall">发货数量</font></td>
          <td width="10%" align="center" nowrap="nowrap" bgcolor="#fff2dc"><span style="color:red">*</span><font class="bigall">发货单价（￥）</font></td>
          <td width="10%" align="center" nowrap="nowrap" bgcolor="#fff2dc">发货金额（￥）</td>
          <td width="20%" align="center" nowrap="nowrap" bgcolor="#fff2dc"><font class="bigall">备注</font></td>
          <td width="5%" align="center" id="addPdInput" title="添加"><img src="${ctx}/styles/jxc/images/+.gif" style="vertical-align:text-bottom;"/></td>
        </tr>
        <tr id="pdmodel" class="hasPdmodel" style="display: none;">
          <td align="center"><html-el:select property="store_id" styleId="store_id">
              <option value="">请选择...</option>
            </html-el:select></td>
          <td align="center"><html-el:select property="cls_id" styleId="cls_id">
              <option value="">请选择...</option>
            </html-el:select></td>
          <td align="center"><html-el:hidden property="pd_id" styleId="pd_id"/>
            <html-el:select property="pd_id_values" styleId="pd_id_values">
              <option value="">请选择...</option>
            </html-el:select></td>
          <td align="center"><html-el:hidden property="maxfh_count" styleId="maxPdCount"/>
            <html-el:text property="count" value="" styleClass="webinput" styleId="fh_count" maxlength="8" size="4"></html-el:text></td>
          <td align="center"><html-el:text property="price" value="" styleId="price"  maxlength="8" size="4" styleClass="webinput" /></td>
          <td align="center"><span id="fh_moneys"></span></td>
          <td align="center" height="30"><html-el:text property="remark" styleId="remark" styleClass="webinput" maxlength="130"></html-el:text></td>
          <td align="center" title="删除"><img src="${ctx}/styles/jxc/images/x.gif" style="vertical-align:text-bottom;"/></td>
        </tr>
        <tbody id="tbody_fhdj">
        </tbody>
        <tr class="title_top">
          <td>合计</td>
          <td colspan="2"><font id="store_tip" style="color: red;"></font></td>
          <td><span id="fh_count_sum"></span></td>
          <td>&nbsp;</td>
          <td><span id="fh_money_sum"></span></td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td class="title_item">应收金额：￥</td>
          <td colspan="3"><html-el:text styleId="money_must" property="money_must" value="0.00" readonly="true" styleClass="webinput"/></td>
          <td class="title_item"><font color="red">*</font>实收金额：￥</td>
          <td colspan="3"><html-el:text styleId="money_result" property="money_result" value="0.00" maxlength="12" styleClass="webinput"/></td>
        </tr>
        <tr>
          <td colspan="8" align="center"><html-el:button property="" value="发货" styleClass="bgButtonSave" styleId="btn_submit" />
            &nbsp;
            <html-el:reset property="" value="重填" styleClass="bgButtonBack" styleId="btn_reset" />
            &nbsp;
            <html-el:button property="" value="返 回" styleClass="bgButtonBack" styleId="btn_back" onclick="history.back();" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.jxc.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/external/bgiframe/jquery.bgiframe.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#fh_date").attr("datatype", "Require").attr("msg", "请添加发货日期！");
	<c:if test="${not empty shiyebu}">
	$("#branch_or_wd_id").attr("dataType", "Require").attr("msg", "请选择分公司");
	 </c:if>
	<c:if test="${not empty fenjingban}">
	$("#branch_or_wd_id").attr("dataType", "Require").attr("msg", "请选择网点");
	 </c:if>
	var el = $("#branch_or_wd_id").multiselect({
			selectedList: 1,
			multiple: false,
			minWidth:380
	}).multiselectfilter({width:280});
	$("#money_result").attr("dataType", "Currency").attr("msg", "请填写实收金额,且必须是正实数！");

	
	/*$("#btn_reset").click(function(){
		$("#store_tip").empty();//库存提示信息
		$("#fh_count_sum").text("0");//合计数量
		$("#fh_money_sum").text("￥0.00" );//合计金额
		$("#fh_moneys","#tbody_fhdj").each(//发货金额
			function(){
				$(this).text("");
			});
	});*/
	
	var JQ_money_result = $("#money_result");
	$("#btn_submit").click(function(){
		JQ_money_result.change(function(){
			var money_result = (this.value);
			var _reg = /^[\+]?\d*?\.?\d*?$/;
			if (!_reg.test(money_result)) {
				JQ_money_result.val("");
				 return false;
			}
		});
		
		/*验证发货数量和发货单价为0  start*/
		var isZero = false;
		$("#fh_count", "#tbody_fhdj").each(function(){
			var this_value = parseFloat($(this).val());
			if(this_value == 0){
				alert("发货数量不能为 0  ！");
				isZero = true;
		        return false;
			}
		});
		if(isZero){//发货数量为0,返回    不提交
			return false;
		}
		$("#price", "#tbody_fhdj").each(function(){
			var this_value = parseFloat($(this).val());
			if(this_value == 0){
				alert("发货单价不能为 0  ！");
				isZero = true;
		        return false;
			}
		});
		if(isZero){//发货单价为0,返回    不提交
			return false;
		}
		/*验证发货数量和发货单价为0  end*/
		
		var repeat = isRepeat();
		if (repeat) {
			alert("同一个出货仓库的型号有重复，请重新选择或者合并！");
			return false;
		}
		
		if (Validator.Validate(this.form, 1)) {
			if ($(".hasPdmodel").length <= 1) {
				alert("发货信息为空,不能发货！"+"\n"+"请点击【添加】按钮，添加发货信息！");
				return false;
			}
					
			$("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
			$("#btn_reset").attr("disabled", "true");
			$("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});
	
	$("#addPdInput").click(function(){
		$("#pdmodel").clone().appendTo($("#tbody_fhdj")).show();
		
		var lastTR = $("tr:last", "#tbody_fhdj");
		var JQ_store_id= $("#store_id", lastTR);//仓库
		var JQ_fh_counts = $("#fh_count", lastTR);//发货数量
		var JQ_fh_prices = $("#price", lastTR);//发货单价
		var JQ_fh_moneys = $("#fh_moneys", lastTR);//发货金额
		var JQ_cls_id = $("#cls_id", lastTR);//类型
		var JQ_pd_id = $("#pd_id_values", lastTR);//型号
		var JQ_hidden_pd_id = $("#pd_id", lastTR);//隐藏型号
		var JQ_maxPdCount = $("#maxPdCount", lastTR);//隐藏最大库存
		
		
		JQ_store_id.attr("dataType", "Require").attr("msg", "请选择仓库！");
		JQ_cls_id.attr("dataType", "Require").attr("msg", "请选择类型！");
		JQ_pd_id.attr("dataType", "Require").attr("msg", "请选择型号！");
		JQ_fh_counts.attr("dataType", "Number").attr("msg", "请填写发货数量,且必须是正整数！");
		JQ_fh_prices.attr("dataType", "Currency").attr("msg", "请填写发货单价,且必须是正实数！");

		//取仓库
		$.ajax({
			type: "POST",
			url: "CsAjax.do",
			data: "method=getStoreInfoListByDeptId",
			dataType: "json",
			error: function(request, settings) {alert("数据加载请求失败！"); },
			success: function(Dates) {
				JQ_store_id.empty().show();
				JQ_store_id.get(0).options.add(new Option("请选择...", ""));
				if (null!= Dates && Dates.length > 1) {
					for(var i = 0; i < Dates.length - 1; i++) {
						var opt = new Option(Dates[i].date_desc, Dates[i].date_id); 
						opt.title = Dates[i].date_desc;
						JQ_store_id.get(0).options.add(opt);
					}
					JQ_store_id.attr("value", Dates[i].date_id);
				} 
			}
		});
		//取类型
		$.ajax({
				type: "POST",
				url: "CsAjax.do",
				data: "method=getBasePdClazzListByDeptId",
				dataType: "json",
				error: function(request, settings) {alert("数据加载请求失败！"); },
				success: function(Dates) {
					JQ_cls_id.empty().show();
					JQ_cls_id.get(0).options.add(new Option("请选择...", ""));
					if (null!= Dates && Dates.length > 1) {
						for(var i = 0; i < Dates.length - 1; i++) {
							var opt = new Option(Dates[i].date_desc, Dates[i].date_id); 
							opt.title = Dates[i].date_desc;
							JQ_cls_id.get(0).options.add(opt);
						}
						JQ_cls_id.attr("value", Dates[i].date_id);
					} 
				}
		});
		//取型号
		doSelectAjaxForTwoPars("CsAjax.do", JQ_store_id, JQ_cls_id, "", JQ_pd_id, "", "getPePdModelListBySidAndDidAndCid");

		//动态清空类型、型号下拉列表和数量、金额
		JQ_store_id.change(function(){
			$("#store_tip").empty();//库存提示信息
			JQ_cls_id.val("");
			JQ_pd_id.val("");
			JQ_fh_counts.val("");
			JQ_fh_prices.val("");
			JQ_fh_moneys.text("");
			calcPdNumAndPrice("tbody_fhdj");
		});
		
		JQ_cls_id.change(function(){
			$("#store_tip").empty();//库存提示信息
			JQ_pd_id.val("");
			JQ_fh_counts.val("");
			JQ_fh_prices.val("");
			JQ_fh_moneys.text("");
			calcPdNumAndPrice("tbody_fhdj");
		});
		
		JQ_pd_id.change(function(){
			$("#store_tip").empty();//库存提示信息
			JQ_fh_counts.val("");
			JQ_fh_prices.val("");
			JQ_fh_moneys.text("");
			var pdIdValues = JQ_pd_id.val().split("@#");
			JQ_fh_prices.val(pdIdValues[2]);
			calcPdNumAndPrice("tbody_fhdj");
		});
		
		bindJhCountAndJhPrice(JQ_store_id, JQ_cls_id, JQ_pd_id, JQ_hidden_pd_id, JQ_fh_counts, JQ_fh_prices, JQ_maxPdCount, JQ_fh_moneys, lastTR);
		
		//删除按钮
	   	$("td:last", lastTR).click(function (){
	    	$(this).parent().remove();
	    	$("#store_tip").empty();//库存提示信息
	    	calcPdNumAndPrice("tbody_fhdj");
	    }).css("cursor", "pointer");
	}).css("cursor", "pointer");
});

function isRepeat(){ //判断添加的产品是否重复
	var flag = false;
	var arrays_v = [];
	$("tr", "#tbody_fhdj").each(function(){
		var _thisTr = $(this);
		var store_id = $("#store_id", _thisTr).val();
		var cls_id = $("#cls_id", _thisTr).val();
		var pd_id = $("#pd_id", _thisTr).val();
		if ("" == store_id || "" == cls_id || "" == pd_id) {
			return false;
		}
		var a_v = [];
		a_v.push(store_id);
		a_v.push(cls_id);
		a_v.push(pd_id);
		arrays_v.push(a_v.join(","));
	});
	
	var sort_arrays_v = arrays_v.sort();
	for(var i = 0; i < sort_arrays_v.length; i++) {
		if (sort_arrays_v[i] == sort_arrays_v[i + 1]) {
			flag = true;
			break;
		}  
    }
	return flag;
}

function bindJhCountAndJhPrice(JQ_store_id, JQ_cls_id, JQ_pd_id, JQ_hidden_pd_id, JQ_fh_counts, JQ_fh_prices, JQ_maxPdCount, JQ_fh_moneys, lastTR){
	JQ_fh_counts.focus(function(){
		var pdIdValues = JQ_pd_id.val().split("@#");
		JQ_hidden_pd_id.val(pdIdValues[0]);
		JQ_maxPdCount.val(pdIdValues[1]);
		
		var selectPdName = $("option:selected", JQ_pd_id).text();
		if(JQ_store_id.val() == "" || JQ_cls_id.val() == "" || JQ_pd_id.val() == "" ){
			$("#store_tip").empty();
		}else{
			$("#store_tip").empty().append('<span id="tip" style="color:#f00;">产品：[' + selectPdName + ']当前库存数量为:' + JQ_maxPdCount.val() + '</span>');
		}
	}).keyup(function(){
		var _reg = /^\d+$/;
		var fh_count =(this.value);
   		if (!_reg.test(fh_count)) {
   			JQ_fh_counts.val(0);
   			fh_count = 0;
   		}
   		
		fh_count = parseFloat(JQ_fh_counts.val());//发货数量
		if(isNaN(fh_count)) fh_count = 0;
		var fh_price = parseFloat(JQ_fh_prices.val());//发货单价
		if(isNaN(fh_price)) fh_price = 0;

		if (JQ_store_id.val() == "") {
			alert("请先选择出货仓库！");
			$(this).focus().val(0);
			return false;
		}
		if (JQ_cls_id.val() == "") {
			alert("请先选择产品类型！");
			$(this).focus().val(0);
			return false;
		}
		if (JQ_pd_id.val() == "") {
			alert("请先选择产品型号！");
			$(this).focus().val(0);
			return false;
		}
		
		//取库存数量
		if(fh_count > parseFloat(JQ_maxPdCount.val())){
			alert("您输入的发货产品数量，大于库存数量，请重新输入");
			var selectPdName = $("option:selected", JQ_pd_id).text();
			$("#store_tip").empty().append('<span id="tip" style="color:#f00;">产品：[' + selectPdName + ']当前库存数量为:' + JQ_maxPdCount.val() + '</span>');
			fh_count = 1;
			if (JQ_maxPdCount.val() <= 0) {
				fh_count = 0;
			}
		}
		
		$(this).focus().val(fh_count);
		JQ_fh_moneys.text((fh_count * fh_price).toFixed(2));//发货金额
		calcFhCountAndFhPrice(JQ_fh_counts, JQ_fh_prices, JQ_fh_moneys);
		calcPdNumAndPrice("tbody_fhdj");
	});

	JQ_fh_prices.keyup(function(){//发货单价
		var fh_price = (this.value);
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		
		if (!_reg.test(fh_price)) {
			JQ_fh_prices.val(0);
			fh_price = 0;
		}
		
		var fh_count = parseFloat(JQ_fh_counts.val());//发货数量
		if(isNaN(fh_count)) fh_count = 0;
		fh_price = parseFloat(fh_price);//发货单价
		if(isNaN(fh_price)) fh_price = 0;

		if (JQ_store_id.val() == "") {
			alert("请先选择出货仓库！");
			$(this).focus().val(0);
			return false;
		}
		if (JQ_cls_id.val() == "") {
			alert("请先选择产品类型！");
			$(this).focus().val(0);
			return false;
		}
		if (JQ_pd_id.val() == "") {
			alert("请先选择产品型号！");
			$(this).focus().val(0);
			return false;
		}
		
		JQ_fh_moneys.text((fh_count * fh_price).toFixed(2));//发货金额
		calcFhCountAndFhPrice(JQ_fh_counts, JQ_fh_prices, JQ_fh_moneys);
		calcPdNumAndPrice("tbody_fhdj");
	});
	calcPdNumAndPrice("tbody_fhdj");
}

function calcPdNumAndPrice(tbody_fhdj) {//计算 合计的 数量 和 单价
	var fh_count_sum = 0;
	var fh_money_sum = 0;
	$("#fh_count", $("#" + tbody_fhdj)).each(function(){
		var this_value = parseFloat($(this).val());
		if(isNaN(this_value)) this_value = 0;
		fh_count_sum += this_value;
	});

	$("#fh_moneys", $("#" + tbody_fhdj)).each(function(){
		var this_value = parseFloat($(this).text());
		if(isNaN(this_value)) this_value = 0;
		fh_money_sum += this_value;
	});
	$("#fh_count_sum").text(fh_count_sum);
	$("#fh_money_sum").text("￥" + fh_money_sum.toFixed(2));
	$("#money_must").val(fh_money_sum.toFixed(2));
	$("#money_result").val(fh_money_sum.toFixed(2));
}

function calcFhCountAndFhPrice(JQ_fh_counts, JQ_fh_prices, JQ_fh_moneys) {//计算 单条的 数量 和 单价
	var fh_count = parseFloat(JQ_fh_counts.val());//发货数量
   	if(isNaN(fh_count)) fh_count = 0;
   	var fh_price =  parseFloat(JQ_fh_prices.val());//发货单价
   	 if(isNaN(fh_price)) fh_price = 0;
   	JQ_fh_moneys.text((fh_count * fh_price).toFixed(2));//发货金额
}
//]]></script>
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
