<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:if test="${ecUser.user_type eq 1 }">康佳EPP内部优惠购机平台</c:if><c:if test="${ecUser.user_type eq 2 }">康佳网上直销商城</c:if></title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/global1.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/epp.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/shoppingcar.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
</head>
<body>
<!-- head start -->
<div class="topbox">
<jsp:include page="/member/__inc/top.jsp" flush="true" />
<jsp:include page="/member/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/member/__inc/nav.jsp" flush="true" />

<!-- buy start -->
<form action="<c:url value='/member/EcGift.do' />" method="post" id="step_two_form">
<input type="hidden" name="method" value="createOrder" />
<input type="hidden" name="p_index" value="${p_index}" />
<input type="hidden" name="buy_json_object" value="${buy_json_object}" />
<div class="card_center">
  <div class="card_a">我 的 购 物 车</div>
  <div class="card_b">
    <div class="card_dd">
      <ul>
        <li>1、我的购物车</li>
        <li><b>2、确认订单信息</b></li>
        <li>3、提交订单</li>
      </ul>
    </div>
  </div>
  <div class="shoppingcart_c">
    <h3 class="TitleName"><b>友情提示</b></h3>
    <div class="HideBox">
      <div style="font-size:14px; padding:10px 0px;">此地址用于快递发货，请务必认真填写，积分商城为积分兑换的礼品，不提供发票，保修凭保修卡即可。</div>
    </div>
    <h3 class="TitleName"><b>收货人信息</b><span><a style="cursor:pointer;display:${empty ecUserAddrs ? 'none' : ''}" id="rel_modify_btn">[修改]</a></span></h3>
    <div class="HideBox">
      <table border="0" cellspacing="0" cellpadding="0" class="mytable">
        <tbody>
          <tr>
            <td class="item"><span class="modify_class" style="color:red;display:${empty ecUserAddrs.rel_name ? '' : 'none'};">*</span>收货人姓名： </td>
            <td>
            	<span class="modify_class_text"><c:out value="${ecUserAddrs.rel_name}" /></span>
            	<input name="rel_name" type="text" maxlength="20" id="rel_name" class="input modify_class" value="${ecUserAddrs.rel_name}" style="display:${empty ecUserAddrs.rel_name ? '' : 'none'};" />
            </td>
          </tr>
          <tr>
            <td class="item" ><span class="modify_class" style="color:red;display:${empty ecUserAddrs ? '' : 'none'};">*</span>收货地址： </td>
            <td><span class="modify_class_text" style="display:${not empty ecUserAddrs ? '' : 'none'};">${p_name}</span>
            <span class="modify_class" style="display:${empty ecUserAddrs ? '' : 'none'};">
             <select name="province" id="province"  class="input_txt" style="width:160px;" >
                <option value="">-请选择省/直辖市/自治区-</option>
              </select>
              <select name="city" id="city"  class="input_txt" style="width:100px;">
                <option value="">-请选择市-</option>
              </select>
              <select name="country" id="country"  class="input_txt" style="width:100px;">
                <option value="">-请选择县-</option>
              </select>
              <select name="town" id="town"  class="input_txt" style="width:100px;">
                <option value="">-请选择乡/镇-</option>
              </select>
            </span>
            </td>
          </tr>
          <tr>
            <td class="item"><span class="modify_class" style="color:red;display:${empty ecUserAddrs.rel_addr ? '' : 'none'};">*</span>详细地址： </td>
            <td>
            	<span class="modify_class_text"><c:out value="${ecUserAddrs.rel_addr}" /></span>
            	<input name="rel_addr" type="text" maxlength="30" id="rel_addr" class="input modify_class" style="width:300px;display:${empty ecUserAddrs.rel_addr ? '' : 'none'};" value="${ecUserAddrs.rel_addr}" />
            </td>
          </tr>
          <tr>
            <td class="item"><span class="modify_class" style="color:red;display:${empty ecUserAddrs.rel_phone ? '' : 'none'};">*</span>手机号码： </td>
            <td>
            	<span class="modify_class_text"><c:out value="${ecUserAddrs.rel_phone}" /></span>
            	<input name="rel_phone" type="text" maxlength="11" id="rel_phone" class="input modify_class" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" style="width:130px;ime-mode:Disabled;display:${empty ecUserAddrs.rel_phone ? '' : 'none'};" value="${ecUserAddrs.rel_phone}" />
              	<span class="modify_class" style="display:${empty ecUserAddrs.rel_phone ? '' : 'none'};">或者</span> 固定电话：
              	<span class="modify_class_text"><c:out value="${ecUserAddrs.rel_tel}" /></span>
                <input name="rel_tel" type="text" maxlength="20" id="rel_tel" class="input modify_class" style="width:130px;display:${empty ecUserAddrs.rel_tel ? '' : 'none'};" value="${ecUserAddrs.rel_tel}" />
                <span class="modify_class" style="display:${empty ecUserAddrs ? '' : 'none'}"> </span></td>
          </tr>
          <tr>
            <td class="item"> 邮政编码： </td>
            <td>
            	<span class="modify_class_text"><c:out value="${ecUserAddrs.rel_zip}" /></span>
            	<input name="rel_zip" type="text" maxlength="6" id="rel_zip" class="input modify_class" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" style="width:80px;ime-mode:Disabled;display:${empty ecUserAddrs.rel_zip ? '' : 'none'};" value="${ecUserAddrs.rel_zip}" />
              	<span class="modify_class" style="display:${empty ecUserAddrs ? '' : 'none'}">有助于快速确定送货地址</span>
            </td>
          </tr>
           
        </tbody>
      </table>
    </div>
    <h3 class="TitleName"> <b>配送信息</b></h3>
    <div class="HideBox">
      <table border="0" cellspacing="0" cellpadding="0" class="mytable">
        <tbody>
          <tr>
            <td class="item"><font color="red">*</font>配送方式： </td>
            <td><ul>
                <table border="0">
                  <tbody>
                    <tr>
                      <td><input type="radio" name="deliver_way" value="0" id="deliver_way_0" />
                        <label for="pay_way_0" style="cursor:pointer;">上门自提</label></td>
                    </tr>
                    <tr>
                      <td><input type="radio" name="deliver_way" value="1" id="deliver_way_1" />
                        <label for="deliver_way_1" style="cursor:pointer;">商家配送</label>
                        发货时间周一至周五，周末不发货。
                       </td>
                    </tr>
                  </tbody>
                </table>
              </ul></td>
          </tr>
          <tr>
            <td class="item"><font color="red">*</font>送货日期： </td>
            <td><ul>
                <table border="0">
                  <tbody>
                    <tr>
                      <td><input id="deliver_time_1" type="radio" name="deliver_time" value="1" />
                        <label for="deliver_time_1">工作日、双休日与假日均可送货</label></td>
                      <td><input id="deliver_time_2" type="radio" name="deliver_time" value="2" />
                        <label for="deliver_time_2">只双休日、假日送货（工作日不用送）</label></td>
                      <td><input id="deliver_time_0" type="radio" name="deliver_time" value="0" />
                        <label for="deliver_time_0">只工作日送货（双休日、假日不用送）</label></td>
                    </tr>
                  </tbody>
                </table>
              </ul></td>
          </tr>
          <tr>
            <td class="item"> </td>
            <td><ul>
                <table border="0">
                  <tbody>
                    <tr>
                      <td>发货前电话确认：<input id="deliver_is_call_0" type="radio" name="deliver_is_call" value="0" />
                        <label for="deliver_is_call_0">否</label></td>
                      <td><input id="deliver_is_call_1" type="radio" name="deliver_is_call" value="2" />
                        <label for="deliver_is_call_1">是</label></td>
                    </tr>
                  </tbody>
                </table>
              </ul></td>
          </tr>
        </tbody>
      </table>
    </div>
    <h3 class="TitleName"><b>商品清单</b></h3>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="mytable3">
      <tbody>
        <tr class="item">
          <td width="15%">商品编号</td>
          <td>商品名称</td>
          <td width="10%">商品数量</td>
          <td width="10%">所需积分</td>
        </tr>
	        <tr>
	          <td>${ecGift.id}</td>
	          <td><a href="<c:url value='/member/EcGift.do?method=view&gift_id=${ecGift.id}' />" target="_blank">${ecGift.pd_name}</a></td>
	          <td>1</td>
	          <td><fmt:formatNumber value="${ecGift.need_integral}" pattern="0" /></td>
	        </tr>
      </tbody>
    </table>
  </div>
  <div class="clear"></div>
  <div class="but_ca">
    <ul>
      <li><a style="cursor:pointer;" id="step_two_btn">提交订单</a></li>
    </ul>
  </div>
</div>
</form>
<!-- buy end --> 

<!-- six footer -->
<jsp:include page="../__inc/footer.jsp" flush="true" />
<!-- six footer --> 

<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	// 区域
	$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${province}","dataType":"Require","msg":"请选择省/直辖市/自治区！"});
	$("#city").attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${city}"});
	$("#country").attr({"subElement": "town","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": ""});
	$("#town").attr({"defaultText": "-请选择乡/镇-", "defaultValue": "", "selectedValue": ""});
	
	$("#province").cs_ext("${ctx}/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", true,"p_index_join","");
	$("#province").change();
	
	// 收货地址点击修改
	$(document).delegate("#rel_modify_btn", "click", function(){
		$(".modify_class").show();
		$(".modify_class_text").hide();
		$(".rel_modify_btn").hide();
	});
	
	$("#rel_name" ).attr("datatype", "Require").attr("msg", "请填写收货人姓名！");
	$("#rel_addr" ).attr("datatype", "Require").attr("msg", "请填写详细地址！");
	$("#rel_phone").attr("datatype", "Require").attr("msg", "请填写联系方式！");
	
	// 点击提交订单
	$(document).delegate("#step_two_btn", "click", function(){
		if(Validator.Validate($("#step_two_form")[0], 3)) {
			var deliver_way = $("input[name='deliver_way']:checked").val();
			if(undefined == deliver_way){
				alert("提示，请选择配送方式！");
				return;
			}
			$("#step_one_go_shopping").attr("disabled", true).html("提交中...");
			$("#step_two_btn").attr("disabled", true).html("提交中...");
			$("#step_two_form").submit();
		}
	});
	
});
//正则表达式：只能输入数字
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
	});
}
//]]></script>
</body>
</html>