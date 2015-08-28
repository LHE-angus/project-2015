<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />-->
<title>${naviString}</title>
<style type="text/css">
body {
	font-size:12px;
}
select {
	font-family:Microsoft YAHEI;
	font-size:12px;
}
input {
	font-family:Microsoft YAHEI;
	font-size:12px;
}
label {
	cursor:pointer;
}
.title_item {
	text-align:right;
	white-space:nowrap;
}
</style>
</head>
<body>
<div id="content">
  <div id="tab1">
  <html-el:form  method="post">
  <html-el:hidden property="method" value="view" />
  <html-el:hidden property="pro_ids" styleId="pro_ids"/>
  <html-el:hidden property="pro_names" styleId="pro_names"/>
    <table width="100%" border="0" cellpadding="5" cellspacing="1">
      <tr>
        <td class="title_item" valign="top"><font color="red">*</font>订单流水号：</td>
        <td colspan="2" valign="top"><font color="red">${af.map.trade_index}</font></span> <span style="float:right;">
          <html-el:button property="btn_back" styleId="btn_back" value=" 返 回 " onclick="history.back();" />
          </span></td>
      </tr>
      <tr>
        <td width="15%" class="title_item">客户名称：</td>
        <td>${af.map.user_shop_name}</td>
      </tr>
      <tr>
        <td class="title_item">售达方编码：</td>
        <td>${af.map.r3_code}</td>
      </tr>
      <!-- 常规信息 -->
      <tr>
        <td colspan="2" style="font-weight:bold;color:#74685F;">常规信息</td>
      </tr>
      <tr>
        <td class="title_item"><font color="red">*</font>订单流程：</td>
        <td><c:if test="${not empty af.map.process_id}">
            <c:forEach var="cur" items="${processList}">
              <c:if test="${af.map.process_id eq cur.id}">
                <c:if test="${cur.add_dept_id eq 0}">★[统一流程] </c:if>
                <c:if test="${cur.add_dept_id ne 0}">[${cur.add_dept_name}]</c:if>
                ${cur.process_desc} </c:if>
            </c:forEach>
          </c:if>
          <c:if test="${empty af.map.process_id}">未确定订单流程</c:if></td>
      </tr>
      <tr>
        <td class="title_item">提交日期：</td>
        <td><fmt:formatDate value="${af.map.order_date}" pattern="yyyy-MM-dd"/></td>
      </tr>
      <tr>
        <td class="title_item">创建日期：</td>
        <td><fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd"/></td>
      </tr>
      <tr>
        <td class="title_item">订单状态：</td>
        <td><c:choose>
            <c:when test="${af.map.is_submit eq 0}"><span style="color:#F00;">未提交</span></c:when>
            <c:when test="${af.map.is_submit eq 1}">
              <c:choose>
                <c:when test="${af.map.audit_state ne 3}"><span style="color:#F00;">审核中</span></c:when>
                <c:when test="${af.map.audit_state eq 3}"><span style="color:green;">审核通过</span></c:when>
              </c:choose>
            </c:when>
          </c:choose></td>
      </tr>
      <tr>
        <td class="title_item">出货状态：</td>
        <td><c:choose>
            <c:when test="${af.map.is_delivered ne 0}"><span style="color:#F00;">未出货</span></c:when>
            <c:when test="${af.map.is_delivered eq 1}"><span style="color:green;">已出货</span></c:when>
          </c:choose></td>
      </tr>
      <tr>
        <td class="title_item">制单人：</td>
        <td>${af.map.userName}</td>
      </tr>
      <tr>
        <td class="title_item">业务员：</td>
        <td>${ywy_user_name}</td>
      </tr>
       <tr>
       <td class="title_item">第三方采购订单编号：</td>
		<td>${af.map.third_cg_order_num}</td>
      </tr>
       <tr>
        <td class="title_item">采购订单日期：</td>
		<td><fmt:formatDate value="${af.map.cg_order_date}" pattern="yyyy-MM-dd" /></td>
      </tr>
      <tr>
        <td class="title_item">备注：</td>
        <td>${af.map.remark}
          <c:if test="${empty af.map.remark}"><span style="color:#ccc;">未填写备注信息</span></c:if></td>
      </tr>
      <!-- 产品明细 -->
      <tr>
        <td colspan="2" style="font-weight:bold;color:#74685F;">产品明细</td>
      </tr>
      <tr>
        <td colspan="2"><table width="100%" border="0" cellpadding="2" cellspacing="0">
            <c:set var="sum_count" value="0" />
            <c:set var="sum_price" value="0" />
            <c:set var="sum_discount_count" value="0" />
            <c:forEach items="${konkaOrderInfoDetailsList}" var="cur">
              <c:set var="sum_count" value="${cur.good_count + sum_count}" />
              <c:set var="sum_price" value="${cur.good_sum_price + sum_price}" />
              <c:set var="sum_discount_count" value="${(cur.good_discount_price + cur.good_sum_price*cur.good_discount/100) + sum_discount_count}" />
              <tr>
                <td style="padding-top:10px; border-bottom:1px solid #ccc;font-weight:bold;">型号：${fn:escapeXml(cur.pd_name)}</td>
              </tr>
              <tr>
                <td>　　数量：${fn:escapeXml(cur.good_count)} ${fn:escapeXml(cur.good_unit)}
                  单价：
                  <fmt:formatNumber value="${fn:escapeXml(cur.good_price)}" pattern="0.00" />
                  金额：
                  <fmt:formatNumber value="${fn:escapeXml(cur.good_sum_price)}" pattern="0.00" /></td>
              </tr>
              <tr>
                <td>折扣(%)-K007:
                  <fmt:formatNumber value="${fn:escapeXml(cur.good_discount)}" pattern="0.00" />
                  折扣金额-RB00：
                  <fmt:formatNumber value="${fn:escapeXml(cur.good_discount_price)}" pattern="0.00" />
                  总折扣：
                  <fmt:formatNumber value="${fn:escapeXml(cur.good_discount_price + cur.good_sum_price*cur.good_discount/100)}" pattern="0.00" /></td>
              </tr>
              <c:if test="${not empty cur.pd_remark}">
                <tr>
                  <td>备注：${fn:escapeXml(cur.pd_remark)}</td>
                </tr>
              </c:if>
            </c:forEach>
            <tr>
              <td style="padding-top:10px; border-bottom:1px solid #ccc;font-weight:bold;">合计</td>
            </tr>
            <tr>
              <td style="padding-right:3px;">　　数量：
                <fmt:formatNumber value="${sum_count}" pattern="0" />
                金额：
                <fmt:formatNumber value="${sum_price}" pattern="0.00" />
                折扣：
                <fmt:formatNumber value="${sum_discount_count}" pattern="0.00" /></td>
            </tr>
          </table></td>
      </tr>
      <!-- 汇总信息 -->
      <tr>
        <td colspan="2" style="font-weight:bold;color:#74685F;">汇总信息</td>
      </tr>
      <tr>
        <td class="title_item">申请数量：</td>
        <td style="color:#009900;font-family:tahoma;">${good_counts}</td>
      </tr>
      <tr>
        <td class="title_item">申请金额：</td>
        <td style="color:#CD0000;font-family:tahoma;"><fmt:formatNumber value="${good_total_price}" type="currency" /></td>
      </tr>
      <tr>
        <td class="title_item">审核数量：</td>
        <td><c:choose>
            <c:when test="${af.map.is_submit eq 0}">0</c:when>
            <c:when test="${af.map.is_submit eq 1}"> ${af.map.order_num} </c:when>
          </c:choose></td>
      </tr>
      <tr>
        <td class="title_item">审核金额：</td>
        <td style="color:#CD0000;font-family:tahoma;"><c:choose>
            <c:when test="${af.map.is_submit eq 0}">0</c:when>
            <c:when test="${af.map.is_submit eq 1}">
              <fmt:formatNumber value="${af.map.money}" type="currency" />
            </c:when>
          </c:choose></td>
      </tr>
      <!-- 付款信息 -->
      <tr>
        <td colspan="2" style="font-weight:bold;color:#74685F;">付款信息</td>
      </tr>
      <tr>
        <td class="title_item"><font color="red">*</font>货款支付方式：</td>
        <td><c:choose>
            <c:when test="${af.map.pay_type eq 4}">现汇</c:when>
            <c:when test="${af.map.pay_type eq 5}">帐期</c:when>
            <c:when test="${af.map.pay_type eq 6}">承兑</c:when>
            <c:when test="${af.map.pay_type eq 45}">现汇、帐期</c:when>
            <c:when test="${af.map.pay_type eq 46}">现汇、承兑</c:when>
            <c:when test="${af.map.pay_type eq 56}">帐期、承兑</c:when>
            <c:when test="${af.map.pay_type eq 456}">现汇、帐期、承兑</c:when>
          </c:choose></td>
      </tr>
      <tr>
        <td class="title_item">可用额度：</td>
        <td>-</td>
      </tr>
      <!-- 收货信息 -->
      <tr>
        <td colspan="2" style="font-weight:bold;color:#74685F;">收货信息</td>
      </tr>
      <tr>
        <td class="title_item"><font color="red">*</font>配送方式：</td>
        <td><c:if test="${af.map.send_type eq 1}">自提</c:if>
          <c:if test="${af.map.send_type eq 2}">配送</c:if></td>
      </tr>
      <tr>
        <td class="title_item">收货人姓名：</td>
        <td>${af.map.user_name}</td>
      </tr>
      <tr>
        <td class="title_item">收货人固定电话：</td>
        <td>${af.map.user_tel}</td>
      </tr>
      <tr>
        <td class="title_item">收货人手机：</td>
        <td>${af.map.user_phone}</td>
      </tr>
      <tr>
        <td class="title_item">收货人所属地区：</td>
        <td>${af.map.fullName}</td>
      </tr>
      <tr>
        <td class="title_item">收货人地址：</td>
        <td>${af.map.user_addr}</td>
      </tr>
      <tr>
        <td class="title_item">收货人备注：</td>
        <td>${af.map.user_remark}</td>
      </tr>
      <!-- 同步信息 -->
      <tr>
        <td colspan="2" style="font-weight:bold;color:#74685F;">同步信息</td>
      </tr>
      <tr>
        <td class="title_item">销售凭证类型</td>
        <td>${af.map.doc_type}</td>
      </tr>
      <tr>
        <td class="title_item">销售组织</td>
        <td>${sales_org}</td>
      </tr>
      <tr>
        <td class="title_item">分销渠道</td>
        <td>10</td>
      </tr>
      <tr>
        <td class="title_item">产品组</td>
        <td>10</td>
      </tr>
      <tr>
        <td class="title_item">售达方</td>
        <td><c:if test="${empty agList}">${ag}</c:if>
          <c:if test="${not empty agList}">
            <html-el:select property="ag" disabled="true">
              <c:forEach var="cur" items="${agList}" varStatus="vs">
                <html-el:option value="${cur.KUNN2}">${cur.KUNN2}</html-el:option>
              </c:forEach>
            </html-el:select>
          </c:if></td>
      </tr>
      <tr>
        <td class="title_item">出具发票方</td>
        <td><c:if test="${empty reList}">${re}</c:if>
          <c:if test="${not empty reList}">
            <html-el:select property="re" disabled="true">
              <c:forEach var="cur" items="${reList}" varStatus="vs">
                <html-el:option value="${cur.KUNN2}">${cur.KUNN2}</html-el:option>
              </c:forEach>
            </html-el:select>
          </c:if></td>
      </tr>
      <tr>
        <td class="title_item">付款方</td>
        <td><c:if test="${empty rgList}">${rg}</c:if>
          <c:if test="${not empty rgList}">
            <html-el:select property="rg" disabled="true">
              <c:forEach var="cur" items="${rgList}" varStatus="vs">
                <html-el:option value="${cur.KUNN2}">${cur.KUNN2}</html-el:option>
              </c:forEach>
            </html-el:select>
          </c:if></td>
      </tr>
      <tr>
        <td class="title_item">送达方</td>
        <td><c:if test="${empty weList}">${we}</c:if>
          <c:if test="${not empty weList}">
            <html-el:select property="we" disabled="true">
              <c:forEach var="cur" items="${weList}" varStatus="vs">
                <html-el:option value="${cur.KUNN2}">${cur.KUNN2}</html-el:option>
              </c:forEach>
            </html-el:select>
          </c:if></td>
      </tr>
    </table>
  </html-el:form>
  </div>
  <c:if test="${not empty af.map.konkaOrderInfoAuditList}">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="navClass" style="margin:10px 0 10px 0;">
      <thead id="nav" style="width:99%;background:#abd589;">
        <tr>
          <th width="5%" align="center">序号</th>
          <th align="center">审核人</th>
          <th align="center">职位</th>
          <th align="center">结果</th>
          <th align="center">日期</th>
          <th align="center">审核意见</th>
        </tr>
      </thead>
      <c:forEach items="${af.map.konkaOrderInfoAuditList}" var="cur1" varStatus="vs">
        <tr>
          <td align="center">${vs.count}</td>
          <td align="center" nowrap="nowrap">${fn:escapeXml(cur1.audit_user)}</td>
          <td align="center" nowrap="nowrap">${fn:escapeXml(cur1.map.role_name)}</td>
          <td align="center" nowrap="nowrap"><c:choose>
              <c:when test="${cur1.audit_result eq 1}"><span style="color:green;">审核通过</span></c:when>
              <c:when test="${cur1.audit_result eq -1}"><span style="color:#F00;">驳回（至审核人）</span></c:when>
              <c:when test="${cur1.audit_result eq 0}"><span style="color:#00F;">驳回（至制单）</span></c:when>
              <c:when test="${cur1.audit_result eq -9}"><span style="color:#999;">（客户）撤销</span></c:when>
            </c:choose></td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur1.audit_datetime}" pattern="yyyy/MM/dd HH:mm"/></td>
          <td><c:if test="${empty cur1.audit_comment}">无</c:if>
            <c:if test="${not empty cur1.audit_comment }">${fn:escapeXml(cur1.audit_comment)}</c:if></td>
        </tr>
      </c:forEach>
    </table>
  </c:if>
  <!-- 附件 -->
  <c:if test="${not empty attachmentList}">
    <table width="100%" border="0" cellpadding="0" cellspacing="1">
      <tr>
        <td class="title_item" style="text-align:center;font-size:15px;font-weight:bold;color:#74685F">附件</td>
      </tr>
      <tr>
        <td align="center"><ul>
          <c:forEach items="${attachmentList}" var="cur" varStatus="vs">
          <li>
        <td align="center">${vs.count}</td>
        <td nowrap="nowrap"><a href="${ctx}/${cur.save_path}">${cur.file_name}</a>
        <td>${cur.file_desc}</td>
          </li>
          </c:forEach>
          </ul>
          </td>
      </tr>
    </table>
  </c:if>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.jxc.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=jtop"></script> 
<script type="text/javascript">//<![CDATA[
var f = document.forms[0];
$(document).ready(function(){
	//$("#right_contcont", window.parent.document).css("background", "#8B0000");//"#D4CDC7");//"#EBE3E2");
	
	$("#user_zip").keyup(function(){//邮编必须为数字
		var _reg = /^\d+$/;
		var user_zip =(this.value);
   		if (!_reg.test(user_zip)) {
   			$("#user_zip").val("");
   		}
	});
	$("#pay_type_6").attr("dataType", "Group").attr("msg", "至少选择一种货款支付方式！");
	$("#send_type_2").attr("dataType", "Group").attr("msg", "至少选择一种配送方式！");
	$("#process_id").attr("dataType", "Require").attr("msg", "请选择订单类型！");

	//tabs切换Begin
	$("#content div[id^=tab]").hide(); // Initially hide all content
    $("#tabs li:first").attr("id","current"); // Activate first tab
    $("#content div[id^=tab]:first").fadeIn(); // Show first tab content
    $('#tabs a').click(function(e) {
        e.preventDefault();
        if ($(this).closest("li").attr("id") == "current"){ //detection for current tab
         	return       
        } else{             
	        $("#content div[id^=tab]").hide(); //Hide all content
	        $("#tabs li").attr("id",""); //Reset id's
	        $(this).parent().attr("id","current"); // Activate this
	        $('#' + $(this).attr('name')).fadeIn(); // Show content for current tab
        }
        window.parent.resizeFrameHeight('mainFrame', 3);
    });
	//tabs切换End
	
	$("td").each(function(){
		if ($(this).html().trim().length == 0) {
			$(this).append("<span style='color:#999;'>未填写</span");
		}
	});
	
});

/*处理返回值数据 ===start===*/
function resizeFrameHeight(offset, min_height) {
	// frame id can be found in page /konka-wd/WebContent/manager/admin/Frames2/indexFrame.jsp, and search 'iframe' to get.
	$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
}

function getPePdModel() { 
	//var returnValue = window.showModalDialog("${ctx}/jxcnokey/SelectPePdModel.do?selectype=mutiple&selects=" + $("#pd_ids").val() + "&azaz=" + Math.random(),window,"dialogWidth:600px;status:no;dialogHeight:370px");
    
	//if (returnValue != null && returnValue.ids != "") {
		$.dialog({
		title:  "产品列表",
		width:  400,
		height: 390,
        lock:true ,
		content:"url:${ctx}/customer/manager/SelectPePdModel.do?method=newList&selectype=mutiple&selects=" + $("#pd_ids").val() + "&azaz=" + Math.random()
	});
}

//取得窗口选择的产品并回显
function getProInfo(){
	var ids = $("#pro_ids").val();
	var names = $("#pro_names").val();
	if(ids != null)
		//前一次操作返回的值  + 当前操作返回的值 = 当前的值
		var pd_ids_temp = "";
		if("" != $("#pd_ids").val()){
			pd_ids_temp = $("#pd_ids").val()+ "," + ids ;
		}else{
			pd_ids_temp = ids ;
		}
	    $("#pd_ids").val(pd_ids_temp);
	    
	    var pd_id_array = new Array();
	    var md_name_array = new Array();
	
	    pd_id_array = ids.split(",");
	    md_name_array = names.split(",");
	    
		for(var i = 0; i < pd_id_array.length; i++){

			$("#trHidden").clone().appendTo($("#tbodyContent")).show();
	    	var lastTR = $("tr:last", "#tbodyContent");
	    	
	    	var pd_id = $("#pd_id", lastTR);//产品型号id
	    	var md_name = $("#md_name", lastTR);//隐藏域产品型号名称
	    	var span_1 = $("#span_1", lastTR);//隐藏域产品型号名称
	    	var md_name1 = $("#md_name1", lastTR);//产品编号
	    	
			var good_count = $("#good_count", lastTR);//数量
			var good_unit = $("#good_unit", lastTR);//单位
			var good_price = $("#good_price", lastTR);//单价
			
			var good_sum_price = $("#good_sum_price", lastTR);//金额
			var sum_price = $("#sum_price", lastTR);//隐藏域金额
			var good_discount = $("#good_discount", lastTR);//折扣
			var good_discount_price = $("#good_discount_price", lastTR);//折扣金额
			var discount_price = $("#discount_price", lastTR);//隐藏域折扣金额
			
			var pd_remark = $("#pd_remark", lastTR);//产品备注
			
			good_count.attr("dataType","Number").attr("msg","请填写数量,且必须为正整数！");
			good_price.attr("dataType","Currency").attr("msg","请填写单价，且必须为正数！");
			good_discount.attr("dataType","Currency").attr("msg","请填写折扣，且必须为正数！");
			
			pd_id.val(pd_id_array[i]);//隐藏域选择的产品
	      	md_name.text(md_name_array[i]);
	      	md_name1.val(md_name_array[i]);
	      	
	      	
			good_count.val("1");
			good_unit.val("台");
			
			good_price.val("0");
			
			span_1.attr("id",md_name_array[i]);//改变id
			
			good_sum_price.text("0");
			sum_price.val("0");
			
			good_discount.val("0");
			good_discount_price.val("0");

			pd_remark.val("");

			calcOneCountAndJhPrice(good_count, good_price, good_sum_price, sum_price);
			good_discount_price.val(parseFloat(good_sum_price.text())*parseFloat(good_discount.val())/100);//折扣金额
			discount_price.val(parseFloat(good_sum_price.text())*parseFloat(good_discount.val())/100);//隐藏域折扣金额
			calcPdNumAndPrice("tbodyContent");

			bindJhCountAndJhPrice(good_count, good_price, good_sum_price, sum_price, good_discount, good_discount_price, discount_price, lastTR);

			//删除操作，影响弹出页面的返回值
			$("td:last", lastTR).click(function (){
				var pd_id_td1 = $("#pd_id", $(this).parent()).val()+",";
				var pd_id_td2 = ","+$("#pd_id", $(this).parent()).val();
				var pd_ids_new1 = $("#pd_ids").val().replace(pd_id_td1,"");
				pd_ids_new2 = pd_ids_new1.replace(pd_id_td2,"");
				
				$("#pd_ids").val(pd_ids_new2);//删除掉的id，得到新的已经选择的id数组
				
		    	$(this).parent().remove();
		    	calcPdNumAndPrice("tbodyContent");

		    	//iframe高度动态变化
		    	window.parent.resizeFrameHeight('mainFrame', 3); 
		    }).css("cursor", "pointer");
		}
		resizeFrameHeight(2);
	}
}
/*处理返回值数据 ===end===*/

//判断产品型号是否选择重复
function judgeSelectedValueIsRepeat(objId, parObj){
	var isRepeat = false;
	var ary = new Array();
	$("#" + objId, parObj).each(function() {
		if ("" != $(this).val()) {
			ary.push($(this).val());
		}
	});
	var nary = ary.sort();  
	for(var i = 0; i < nary.length - 1; i++) {
		if (nary[i] == nary[i + 1]) {
			isRepeat = true;
			break;
		}  
    }  
	return isRepeat;
}

//输入数量和台数的绑定计算函数
function bindJhCountAndJhPrice(good_count, good_price, good_sum_price, sum_price, good_discount, good_discount_price, discount_price, lastTR) {
	good_count.keyup(function(){//数量
		var _reg = /^\d+$/;
		var count =(this.value);
   		if (!_reg.test(count)) {
   			good_count.val(1);
   			count = 1;
   		}
   		var price = parseFloat(good_price.val());//单价
   		if(isNaN(price)) price = 0;
   		
   		var discount = parseFloat(good_discount.val());//折扣
   		if(isNaN(discount)) discount = 0;
   		
   		count = parseFloat(count);//数量
   		if(isNaN(count)) count = 1;
   	   	
   		good_sum_price.text((count * price).toFixed(2));//金额
   		sum_price.val((count * price).toFixed(2));//隐藏域金额
   		//good_discount_price.val((count * price * discount/100).toFixed(2));//折扣金额
   		//discount_price.val((count * price * discount/100).toFixed(2));//隐藏域折扣金额
   		
   		dis_money = good_discount_price.val();
   		if(isNaN(dis_money)) dis_money = 0;
   		good_discount.val((count * price) == 0 ? '-' : (dis_money * 100 / (count * price)).toFixed(2));//折扣率
   		
		calcPdNumAndPrice("tbodyContent");
	});

	good_price.keyup(function(){//单价
		var price = (this.value);
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(price)) {
			good_price.val(0);
			price = 0;
		}
		price = parseFloat(this.value);
		if(isNaN(price)) price = 0;
		if(price > parseFloat(99999.99)){
			alert("价格太大了，上限为10万！");
			good_price.val(99999.99);
			price = 99999.99;
		}
   		var count = parseFloat(good_count.val());//数量
   		if(isNaN(count)) count = 1;

   		var discount = parseFloat(good_discount.val());//折扣
   		if(isNaN(discount)) discount = 0;
   		good_sum_price.text((count * price).toFixed(2));//金额
   		sum_price.val((count * price).toFixed(2));//隐藏域金额
   		//good_discount_price.val((count * price * discount/100).toFixed(2));//折扣金额
   		//discount_price.val((count * price * discount/100).toFixed(2));//隐藏域折扣金额
   		
   		dis_money = good_discount_price.val();
   		if(isNaN(dis_money)) dis_money = 0;
   		good_discount.val((count * price) == 0 ? '-' : (dis_money * 100 / (count * price)).toFixed(2));//折扣率
   		
		calcPdNumAndPrice("tbodyContent");
	});

	good_discount.keyup(function(){//折扣
		var discount = (this.value);
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(discount)) {
			good_discount.val(0);
			discount = 0;
		}
		discount = parseFloat(this.value);
		if(isNaN(discount)) discount = 0;
		if(discount > 100) {
			discount = 0;
			this.value = 0;
		}
   		var sum_price = parseFloat(good_sum_price.text());//金额
   		if(isNaN(sum_price)) sum_price = 0;
	   	
   		good_discount_price.val((discount * sum_price/100).toFixed(2));//折扣金额
   		discount_price.val((discount * sum_price/100).toFixed(2));//隐藏域折扣金额
		calcPdNumAndPrice("tbodyContent");
	});
	
	// 下面的代码是xxd新增，为放开可修改折扣金额输入框 add by xxd @20130702
	good_discount_price.keyup(function(){ // 折扣金额
		var discount = (this.value);
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(discount)) {
			good_discount_price.val(0);
			discount = 0;
		}
		discount = parseFloat(this.value);
		if(isNaN(discount)) discount = 0;
   		var sum_price = parseFloat(good_sum_price.text());//金额
   		if(isNaN(sum_price)) sum_price = 0;
	   	
   		good_discount.val(sum_price == 0 ? '-' : (discount * 100 / sum_price).toFixed(2));//折扣率
		calcPdNumAndPrice("tbodyContent");
	});
	// end. add by xxd @20130702
	
	calcPdNumAndPrice("tbodyContent");
}

//计算 单个的金额
function calcOneCountAndJhPrice(good_count, good_price, good_sum_price, sum_price) {
	var good_count = parseFloat(good_count.val());//数量
   	if(isNaN(good_count)) good_count = 1;
   	var good_price =  parseFloat(good_price.val());//单价
   	if(isNaN(good_price)) good_price = 0;
   	
   	good_sum_price.text((good_count * good_price).toFixed(2));//金额
   	sum_price.val((good_count * good_price).toFixed(2));//隐藏域金额
}

//计算 合计的 数量、 金额和折扣金额
function calcPdNumAndPrice(tbody_id) {
	var dd_count_sum = 0;
	var dd_money_sum = 0;
	var dd_discount_sum = 0;
	$("#good_count", $("#" + tbody_id)).each(function(){
		var this_value = parseFloat($(this).val());
		if(isNaN(this_value)) this_value = 0;
		dd_count_sum += this_value;
	});

	$("#good_sum_price", $("#" + tbody_id)).each(function(){
		var this_value = parseFloat($(this).text());
		if(isNaN(this_value)) this_value = 0;
		dd_money_sum += this_value;
	});
	
	$("#good_discount_price", $("#" + tbody_id)).each(function(){
		var this_value = parseFloat($(this).text());
		if(isNaN(this_value)) this_value = 0;
		dd_discount_sum += this_value;
	});
	
	$("#dd_count_sum").text(dd_count_sum);
	$("#dd_money_sum").text("￥" + (dd_money_sum.toFixed(2)));
	$("#dd_discount_sum").text("￥" + (dd_discount_sum.toFixed(2)));
	$("#pay_money").val(parseFloat(dd_money_sum.toFixed(2)-dd_discount_sum.toFixed(2)).toFixed(2));
	//$("#money").val(dd_money_sum.toFixed(2)-dd_discount_sum.toFixed(2));
	$("#order_num").val(dd_count_sum);//隐藏域订单总数
	$("#money_sum").val(dd_money_sum.toFixed(2));//隐藏域订单金额
	$("#discount_price_sum").val(dd_discount_sum.toFixed(2));//隐藏域订单折扣金额
}
//]]></script>
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>