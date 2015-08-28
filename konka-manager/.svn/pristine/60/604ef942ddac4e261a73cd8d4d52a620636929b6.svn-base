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
label {
	cursor:pointer;
}
.ti {
	background-color:#eee;
	text-align:right;
	white-space:nowrap;
}
.grp {
	font-weight:bold;
	color:#74685F;
	border-bottom:1px solid #74685F;
	padding:5px;
	margin-top:5px;
}
</style>
</head>
<body>
<div style="height:30px;line-height:30px; background-color:#F8ECE0;padding-left:5px;"><span style="color:#ccc;">第 1 步：选择产品</span> -&gt; 第 <span style="font-size:150%;color:#F00;font-style:italic;font-weight:800;font-family:verdana;">2</span> 步：填写订单信息 <span style="color:#ccc;">-&gt; 第 3 步：成功下单</span></div>
<div style="width:100%;margin-top:5px;">
  <html-el:form action="/manager/JxcKonkaOrderRegister" enctype="multipart/form-data" method="post">
    <html-el:hidden property="not_redirect_to_list" value="true" />
    <html-el:hidden property="method" value="saveOrderOnMobile" styleId="method"/>
    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
    <html-el:hidden property="trade_index" styleId="trade_index" value="${af.map.trade_index}" />
    <html-el:hidden property="cust_id" styleId="cust_id" value="${cust_id}" />
    <html-el:hidden property="order_id" styleId="order_id" value="${order_id}" />
    <html-el:hidden property="is_submit" styleId="is_submit" value="0" />
    <html-el:hidden property="forward_type" styleId="forward_type" value="0" />
    <html-el:hidden property="stocks_check_flag" styleId="stocks_check_flag" />
    <html-el:hidden property="pay_type_value" styleId="pay_type_value" value="${af.map.pay_type}" />
    <html-el:hidden property="pro_ids" styleId="pro_ids"/>
	<html-el:hidden property="pro_names" styleId="pro_names"/>
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="15%" class="ti"><font color="red">*</font>订单流水号：</td>
        <td width="75%" style="font-size:120%;font-family:verdana;"><font color="red">${af.map.trade_index}</font></span></td>
      </tr>
      <tr>
        <td class="ti">客户名称：</td>
        <td><c:if test="${empty konkaR3ShopList}">
            <html-el:text property="user_shop_name" styleId="user_shop_name" value="${af.map.user_shop_name}" style="width:90%" maxlength="100" styleClass="webinput" readonly="true" />
          </c:if>
          <c:if test="${not empty konkaR3ShopList}">
            <html-el:select property="ag">
              <c:forEach var="cur" items="${konkaR3ShopList}" varStatus="vs">
                <html-el:option value="${cur.id}">${cur.customer_name}</html-el:option>
              </c:forEach>
            </html-el:select>
          </c:if></td>
      </tr>
      <tr>
        <td class="ti">售达方编码：</td>
        <td><html-el:text property="r3_code" styleId="r3_code" value="${af.map.r3_code}" style="width:100px;" maxlength="100" styleClass="webinput" readonly="true" /></td>
      </tr>
      <!-- 常规信息 -->
      <tr>
        <td colspan="2" align="left" class="grp">常规信息</td>
      </tr>
      <tr>
        <td class="ti">提交日期：</td>
        <td><html-el:text property="order_date" styleId="order_date" size="10" maxlength="9" readonly="true" styleClass="webinput" value="${af.map.today}"/></td>
      </tr>
      <tr>
      <c:if test="${empty af.map.add_date}">
        <td class="ti">创建日期：</td>
       <td><html-el:text property="add_date" styleId="add_date" size="10" maxlength="9" readonly="true" styleClass="webinput" value="${af.map.today}"/></td>
	 </c:if>
      </tr>
      <c:if test="${sessionScope.from_manager_is_salesman eq '1'}">
        <tr>
          <td class="ti"><font color="red">*</font>订单流程：</td>
          <td><html-el:select property="process_id" styleId="process_id" style="width:200px;">
              <html-el:option value="">=请选择=</html-el:option>
              <c:forEach var="cur" items="${processList}">
                <html-el:option value="${cur.id}">
                  <c:if test="${cur.add_dept_id eq 0}">★[统一流程] </c:if>
                  <c:if test="${cur.add_dept_id ne 0}">[${cur.add_dept_name}]</c:if>
                  ${cur.process_desc} </html-el:option>
              </c:forEach>
            </html-el:select></td>
        </tr>
      </c:if>
      <tr>
        <td class="ti">订单状态：</td>
        <td><html-el:select property="audit_state" styleId="audit_state" disabled="true">
            <html-el:option value="0">制单</html-el:option>
            <html-el:option value="1">审核中</html-el:option>
            <html-el:option value="2">审核未通过</html-el:option>
            <html-el:option value="3">审核通过</html-el:option>
          </html-el:select></td>
      </tr>
      <tr>
        <td class="ti">出货状态：</td>
        <td><html-el:select property="" styleId="" disabled="true">
            <html-el:option value="">未出货</html-el:option>
            <html-el:option value="">出货中</html-el:option>
            <html-el:option value="">已出货</html-el:option>
          </html-el:select></td>
      </tr>
      <tr>
        <td class="ti">制单人：</td>
        <td><html-el:text property="userName" value="${af.map.userName}" style="width:100px;" styleClass="webinput" readonly="true" /></td>
      </tr>
      <tr>
        <td class="ti">业务员：</td>
        <td><html-el:text property="ywy_user_name" value="${ywy_user_name}" style="width:100px;" styleClass="webinput" readonly="true" /></td>
      </tr>
      
      
      <tr>
							<td class="ti">采购订单编号：</td>
							<td><html-el:text property="third_cg_order_num" maxlength="50" style="width:100px;" styleClass="webinput" /></td>
							
							</tr>
							 <tr>
							<td class="ti">采购订单日期：</td>
							<td><fmt:formatDate value="${af.map.cg_order_date}" pattern="yyyy-MM-dd" var="cg_order_date" />
            				<html-el:text property="cg_order_date" styleClass="webinput" styleId="cg_order_date" size="10" maxlength="10" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="cursor:pointer;text-align:left;width:100px;" value="${cg_order_date}" readonly="readonly" />
							</td>
						</tr>
      
      
      
      
      
      
      <tr style="display:none;" id="process_id_tr">
        <td class="ti">订单分类：</td>
        <td><label for="process_state_1">
            <html-el:radio property="process_state" styleId="process_state_1" value="1" />
            一般订单</label>
          <span style="color:#999;margin-left:3em;">一般订单是指订单的审核流程不需要经过分公司总经理审批的订单</span><br />
          <label for="process_state_2">
            <html-el:radio property="process_state" styleId="process_state_2" value="2" />
            特殊订单</label>
          <span style="color:#999;margin-left:3em;">特殊订单是指订单的审核流程需要经过分公司总经理审批的订单</span></td>
      </tr>
      <tr>
        <td class="ti">备注：</td>
        <td><html-el:textarea property="remark" styleId="remark" style="width:80%;" rows="2"></html-el:textarea>
          <div id="remark_note"  style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/styles/images/tishi.gif" style="vertical-align:middle;" /></div></td>
      </tr>
      <!-- 产品明细 -->
      <tr>
        <td align="left" class="grp" colspan="2">产品明细
        （<span id="btn_check" style="text-decoration:underline;cursor:pointer;color:#F00;">点击检查库存</span>）&nbsp;&nbsp;&nbsp;<span style="cursor:pointer;display:none;" title="添加" onclick="getPePdModel();"><img src="${ctx}/styles/jxc/images/+.gif" style="vertical-align:text-bottom;"/></span>
        <input class="but1" type="button" name="reset" value=" &lt;&lt;返回重新选择产品 " onclick="location.href='JxcKonkaOrderRegister.do?method=listpdselect&pd_ids=${af.map.pd_ids}';" /></td>
      </tr>
      <tr>
        <td colspan="2"><div id="trHidden" style="display:none;">
            <table width="100%" border="0" cellpadding="0" cellspacing="1">
              <tr>
                <th scope="col">产品型号：</th>
                <th colspan="1" scope="col" align="left">
                  <html-el:hidden property="good_unit" styleId="good_unit" value="台" />
                  <html-el:hidden property="pd_id" styleId="pd_id" />
                  <html-el:hidden property="md_name" styleId="md_name1" styleClass="md_name1" />
                  <span id="md_name" class="md_name"></span><span id="span_1"></th>
                <th align="right" id="del"><img src="${ctx}/styles/jxc/images/x.gif" style="vertical-align:text-bottom;" /></th>
              </tr>
              <tr>
                <td>数量：</td>
                <td><html-el:text property="good_count" styleId="good_count" value="1" size="4" maxlength="4" styleClass="good_count" /></td>
                 <td></td>
                </tr>
              <tr><td>单价：</td>
                <td><html-el:text property="good_price" value="0" styleId="good_price"  maxlength="8" size="8" styleClass="" /></td>
              <td></td>
                </tr>
              <tr>
                <td>金额</td>
                <td><html-el:text property="good_sum_price" styleId="good_sum_price" value="0" style="width:80px;" styleClass="webinput" readonly="true" />
                  <html-el:hidden property="sum_price" styleId="sum_price" /></td>
              	 <td></td>
                </tr>
              <tr> <td>折扣金额-RB00</td>
                <td><html-el:text property="good_discount_price" value="0" styleId="good_discount_price"  maxlength="10" size="10" styleClass="webinput" />
                  <html-el:hidden property="discount_price" styleId="discount_price" /></td>
                <td>折扣(%)-K007：</td>
               <td></td>
                </tr>
              <tr>
                <td><html-el:text property="good_discount" value="0" styleId="good_discount"  maxlength="5" size="5" styleClass="" /></td>
              	 <td></td>
                </tr>
              <tr> <td>总折扣：</td>
                  <td><html-el:text property="s_good_discount_price" value="0" styleId="s_good_discount_price"  maxlength="10" size="10" styleClass="webinput" readonly="true" />
                    <html-el:hidden property="s_discount_price" styleId="s_discount_price" /></td>
              <td></td>
                </tr>
              <tr>
                <td>产品备注</td>
                <td colspan="2"><html-el:text property="pd_remark" size="20" styleId="pd_remark" maxlength="100" styleClass="" /></td>
              </tr>
            </table>
          </div>
          <div id="tbodyContent">
            <c:forEach var="cur" items="${pePdModelList}">
              <table width="100%" border="0" cellpadding="0" cellspacing="1">
                <tr>
                  <th scope="col">产品型号：</th>
                  <th colspan="1" scope="col" align="left">
                    <html-el:hidden property="good_unit" styleId="good_unit" value="台" />
                    <html-el:hidden property="pd_id" styleId="pd_id" value="${cur.pd_id}" />
                    <html-el:hidden property="md_name" styleId="md_name1" styleClass="md_name1" value="${cur.md_name}" />
                    <span id="md_name" class="md_name">${cur.md_name}</span><span id="span_1"></span>
                    <span id="${cur.md_name}"></span>
                    </th>
                  <th align="right" id="del"><img src="${ctx}/styles/jxc/images/x.gif" style="vertical-align:text-bottom;"/></th>
                </tr>
                <tr>
                  <td>数量：</td>
                  <td><html-el:text property="good_count" styleId="good_count" value="1" size="4" maxlength="4" styleClass="good_count" /></td>
                <td></td>
                   </tr>
                <tr>
                 
                  <td>单价：</td>
                  <td><html-el:text property="good_price" value="0" styleId="good_price"  maxlength="8" size="8" styleClass="" /></td>
               <td></td>
                </tr>
                <tr>
                  <td>金额</td>
                  <td><html-el:text property="good_sum_price" styleId="good_sum_price" value="0" style="width:80px;" styleClass="webinput" readonly="true" />
                    <html-el:hidden property="sum_price" styleId="sum_price" /></td>
                  <td></td>
                   </tr>
                <tr> <td>折扣金额-RB00：</td>
                  <td><html-el:text property="good_discount_price" value="0" styleId="good_discount_price"  maxlength="10" size="10" styleClass="webinput" />
                    <html-el:hidden property="discount_price" styleId="discount_price" /></td>
               <td></td>
                </tr>
                <tr>
                  <td>折扣(%)-K007：</td>
                  <td><html-el:text property="good_discount" value="0" styleId="good_discount"  maxlength="5" size="5" styleClass="" /></td>
                  <td></td>
                  </tr>
                <tr>  <td>总折扣：</td>
                  <td><html-el:text property="s_good_discount_price" value="0" styleId="s_good_discount_price"  maxlength="10" size="10" styleClass="webinput" readonly="true" />
                    <html-el:hidden property="s_discount_price" styleId="s_discount_price" /></td>
               <td></td>
                </tr>
                <tr>
                  <td>产品备注</td>
                  <td colspan="2"><html-el:text property="pd_remark" size="20" styleId="pd_remark" maxlength="100" styleClass="" /></td>
               <td></td>
                </tr>
              </table>
            </c:forEach>
          </div>
          <table width="100%" border="0" cellpadding="0" cellspacing="1">
            <tr class="title_top_no_bc">
              <td>合计</td>
              <td>
              <html-el:text property="dd_count_sum" styleId="dd_count_sum" value="0" style="width:50px;" styleClass="webinput" readonly="true" />
                <html-el:hidden property="order_num" styleId="order_num" />&nbsp;&nbsp;&nbsp;&nbsp;
                <html-el:text property="dd_money_sum" styleId="dd_money_sum" value="0" style="width:100px;" styleClass="webinput" readonly="true" />
                <html-el:hidden property="money_sum" styleId="money_sum" />&nbsp;&nbsp;&nbsp;&nbsp;
             <html-el:text property="dd_discount_sum" styleId="dd_discount_sum" value="0" style="width:100px;" styleClass="webinput" readonly="true" />
                <html-el:hidden property="discount_price_sum" styleId="discount_price_sum" /></td>
            </tr>
          </table></td>
      </tr>
      <!-- 汇总信息 -->
      <tr>
        <td colspan="2" align="left" class="grp">汇总信息</td>
      </tr>
      <tr>
        <td class="ti">申请数量：</td>
        <td><html-el:text property="sqsl_span" styleId="sqsl_span" value="0" style="width:100px;" styleClass="webinput" readonly="true" /></td>
      </tr>
      <tr>
        <td class="ti">申请金额：</td>
        <td><html-el:text property="sqje_span" styleId="sqje_span" value="0" style="width:100px;" styleClass="webinput" readonly="true" /></td>
      </tr>
      <tr>
        <td class="ti">审核数量：</td>
        <td><html-el:text property="shsl_0" value="0" style="width:100px;" styleClass="webinput" readonly="true" /></td>
      </tr>
      <tr>
        <td class="ti">审核金额：</td>
        <td><html-el:text property="shje_0" value="0" style="width:100px;" styleClass="webinput" readonly="true" /></td>
      </tr>
      <!-- 付款信息 -->
      <tr>
        <td colspan="2" align="left" class="grp">付款信息</td>
      </tr>
      <!-- 
						<tr>
							<td class="ti" valign="top"><font color="red">*</font>货款支付方式：</td>
							<td>
								<label for="pay_type_4"><html-el:radio property="pay_type" styleId="pay_type_4" value="4" /> 现汇</label>
								<span style="color:#999;margin-left:5em;">采用现金汇款或转帐的方式支付货款</span><br />
								<label for="pay_type_5"><html-el:radio property="pay_type" styleId="pay_type_5" value="5" /> 帐期</label>
								<span style="color:#999;margin-left:5em;">使用您的信用额度余额支付货款</span><br />
								<label for="pay_type_6"><html-el:radio property="pay_type" styleId="pay_type_6" value="6" /> 承兑</label>
								<span style="color:#999;margin-left:5em;">使用银行提供的承兑支票支付货款</span><br />
							</td>
							<td class="ti">可用额度：</td>
							<td>
								<span id="can_use_money">-</span>
								<html-el:button property="btn_can_use_money" styleId="btn_can_use_money" value="点击查询 " style="margin-left:10px;" /></td>
		        		</tr>
		        		 -->
      <tr>
        <td class="ti" valign="top"><font color="red">*</font>货款支付方式：</td>
        <td><label for="pay_type_4">
            <input type="checkbox" name="pay_type" id="pay_type_4" value="4" />
            现汇&nbsp;</label>
          <label for="pay_type_5">
            <input type="checkbox" name="pay_type" id="pay_type_5" value="5" />
            帐期&nbsp;</label>
          <label for="pay_type_6">
            <input type="checkbox" name="pay_type" id="pay_type_6" value="6" />
            承兑&nbsp;</label></td>
      </tr>
      <tr>
        <td class="ti">可用额度：</td>
        <td><span id="can_use_money">-</span>
          <html-el:button property="btn_can_use_money" styleId="btn_can_use_money" value="点击查询 " style="margin-left:10px;" /></td>
        <td></td>
      </tr>
      <tr>
        <td class="ti">&nbsp;</td>
        <td><span style="color:#999;">提醒：1、现汇：采用现金汇款或转帐的方式支付货款<br />
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、帐期：使用您的信用额度余额支付货款<br />
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3、承兑：使用银行提供的承兑支票支付货款 </span></td>
      </tr>
      <!-- 收货信息 -->
      <tr>
        <td colspan="2" align="left" class="grp">收货信息</td>
      </tr>
      <tr>
        <td class="ti"><font color="red">*</font>配送方式：</td>
        <td><label for="send_type_1">
            <html-el:radio property="send_type" styleId="send_type_1" value="1" />
            自提</label>
          <label for="send_type_2">
            <html-el:radio property="send_type" styleId="send_type_2" value="2" />
            配送</label></td>
      </tr>
      <tr>
        <td class="ti">收货人姓名：</td>
        <td><html-el:text property="user_name" styleId="user_name" value="${af.map.user_name}" style="width:50%" maxlength="20" /></td>
      </tr>
      <tr>
        <td class="ti">收货人固定电话：</td>
        <td><html-el:text property="user_tel" styleId="user_tel" value="${af.map.user_tel}" style="width:40%" maxlength="20" />
          <span id="span_msg_tel__error" style="display: none;"><font style="color: red">正确格式,如：0551-3698754</font></span></td>
      </tr>
      <tr>
        <td class="ti">收货人手机：</td>
        <td><html-el:text property="user_phone" styleId="user_phone" value="${af.map.user_phone}" style="width:40%" maxlength="11" />
          <span id="span_msg_phone__error" style="display: none;"><font style="color: red">正确格式,如：13966557733</font></span></td>
      </tr>
      <tr>
        <td class="ti">收货人所属地区：</td>
        <td><html-el:hidden property="user_p_index" styleId="user_p_index" />
          <select name="province" id="province" style="width:175px;">
            <option value="${af.map.province}">请选择...</option>
          </select>
          &nbsp;
          <select name="city" id="city" style="width:157px;">
            <option value="${af.map.city}">请选择...</option>
          </select>
          &nbsp;
          <select name="country" id="country" style="width:157px;">
            <option value="${af.map.country}">请选择...</option>
          </select></td>
      </tr>
      <tr>
        <td class="ti">收货人地址：</td>
        <td><html-el:text property="user_addr" styleId="user_addr" value="${af.map.user_addr}" style="width:80%" maxlength="100" /></td>
      </tr>
      <tr>
        <td class="ti">收货人备注：</td>
        <td><html-el:textarea property="user_remark" styleId="user_remark" style="width:80%;" rows="2"></html-el:textarea>
          <div id="user_remark_note"  style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/styles/images/tishi.gif" style="vertical-align:middle;" /></div></td>
      </tr>
      <!-- 同步信息 -->
      <tr>
        <td colspan="2" align="left" class="grp">R3系统订单凭证</td>
      </tr>
      <tr>
        <td align="left" class="ti">销售凭证类型</td>
        <td><html-el:select property="doc_type" styleId="doc_type">
            <html-el:option value="ZFOR">ZFOR</html-el:option>
            <html-el:option value="ZFGC">ZFGC</html-el:option>
          </html-el:select>
          <span style="color:#ccc;" id="ZFOR_info">备注：常规订单！</span> <span style="display:none;color:#ccc;" id="ZFGC_info">备注：工程机订单！</span></td>
      </tr>
      <tr>
        <td align="left" class="ti">销售组织</td>
        <td><input type="text" name="sales_org" id="sales_org" value="${sales_org}" class="webinput" readonly="readonly" /></td>
      </tr>
      <tr>
        <td colspan="1" align="left" class="ti">分销渠道</td>
        <td><input type="text" name="distr_chan" value="10" class="webinput" readonly="readonly" /></td>
      </tr>
      <tr>
        <td align="left" class="ti">产品组</td>
        <td><input type="text" name="division" value="10" class="webinput" readonly="readonly" /></td>
      </tr>
      <tr>
        <td align="left" class="ti">售达方</td>
        <td><c:if test="${empty agList}">
            <input type="text" name="ag" value="${ag}" class="webinput" readonly="readonly" />
          </c:if>
          <c:if test="${not empty agList}">
            <html-el:select property="ag">
              <c:forEach var="cur" items="${agList}" varStatus="vs">
                <html-el:option value="${cur.KUNN2}">${cur.KUNN2}</html-el:option>
              </c:forEach>
            </html-el:select>
          </c:if></td>
      </tr>
      <tr>
        <td colspan="1" align="left" class="ti">出具发票方</td>
        <td><c:if test="${empty reList}">
            <input type="text" name="re" value="${re}" class="webinput" readonly="readonly" />
          </c:if>
          <c:if test="${not empty reList}">
            <html-el:select property="re">
              <c:forEach var="cur" items="${reList}" varStatus="vs">
                <html-el:option value="${cur.KUNN2}">${cur.KUNN2}</html-el:option>
              </c:forEach>
            </html-el:select>
          </c:if></td>
      </tr>
      <tr>
        <td colspan="1" align="left" class="ti">付款方</td>
        <td><c:if test="${empty rgList}">
            <input type="text" name="rg" value="${rg}" class="webinput" readonly="readonly" />
          </c:if>
          <c:if test="${not empty rgList}">
            <html-el:select property="rg">
              <c:forEach var="cur" items="${rgList}" varStatus="vs">
                <html-el:option value="${cur.KUNN2}">${cur.KUNN2}</html-el:option>
              </c:forEach>
            </html-el:select>
          </c:if></td>
      </tr>
      <tr>
        <td colspan="1" align="left" class="ti">送达方</td>
        <td><c:if test="${empty weList}">
            <input type="text" name="we" value="${we}" class="webinput" readonly="readonly" />
          </c:if>
          <c:if test="${not empty weList}">
            <html-el:select property="we">
              <c:forEach var="cur" items="${weList}" varStatus="vs">
                <html-el:option value="${cur.KUNN2}">${cur.KUNN2}</html-el:option>
              </c:forEach>
            </html-el:select>
          </c:if></td>
      </tr>
      <tr>
        <td colspan="2">注：销售凭证信息系统自动从康佳R3系统实时抓取显示，没有查询到相关信息的字段是默认为康佳客户编码。</td>
      </tr>
    </table>
    <div>
      <html-el:button property="btn_submit" styleId="btn_submit" value=" 提 交 " />
      <html-el:button property="btn_temp_save" styleId="btn_temp_save" value=" 保 存 " />
      <html-el:button property="btn_temp_save_add" styleId="btn_temp_save_add" value=" 保存并新建 " />
      <html-el:button property="btn_back" styleId="btn_back" value=" 返 回 " onclick="history.back();" />
      <!--<html-el:button property="btn_retract" styleId="btn_retract" value=" 撤 回 " />--> 
    </div>
    <div style="height:30px;"></div>
  </html-el:form>
</div>
<!--<script src="http://code.jquery.com/jquery-1.7.2.min.js"></script>--> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script> 
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
	$("#pay_type_6").attr("dataType", "Group").attr("msg", "请选择一种货款支付方式！");
	$("#send_type_2").attr("dataType", "Group").attr("msg", "请选择一种配送方式！");
	<c:if test="${sessionScope.from_manager_is_salesman eq '1'}">
		$("#process_id").attr("dataType", "Require").attr("msg", "请选择订单类型！");
	</c:if>
	$("#user_name").attr("dataType", "Require").attr("msg", "收货人姓名必须填写！");
	$("#user_phone").attr("dataType", "Require").attr("msg", "收货人手机不能为空！");
	//所在地市联动
	$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${af.map.province}"});
	$("#city").attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${af.map.city}"});
	$("#country" ).attr({"defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${af.map.country}"});
	$("#province").cs("${ctx}/jxc/CsAjax.do?method=getBaseProvinceList", "p_index", "0", false);

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
    });
	//tabs切换End

	// 订单备注
	$("#remark").textbox({
		maxLength: 500,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/styles/images/tishi.gif" style="vertical-align:middle;" />';
			$("#remark_note").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#remark_note").slideUp("normal");
	});
	
	// 卖家留言
	$("#user_remark").textbox({
		maxLength: 100,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/styles/images/tishi.gif" style="vertical-align:middle;" />';
			$("#user_remark_note").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#user_remark_note").slideUp("normal");
	});

	if("ZFOR" == $("#doc_type").val()){
		$("#ZFOR_info").show();
		$("#ZFGC_info").hide();
	}
	if("ZFGC" == $("#doc_type").val()){
		$("#ZFGC_info").show();
		$("#ZFOR_info").hide();
	}

	//回显付款方式
	if($("#pay_type_value").val().length >0){
		var pay_type_value = $("#pay_type_value").val();
		if(pay_type_value == 4){
			$("#pay_type_4").attr("checked",true);
		} else if (pay_type_value == 5){
			$("#pay_type_5").attr("checked",true);
		} else if (pay_type_value == 6){
			$("#pay_type_6").attr("checked",true);
		}else if (pay_type_value == 45){
			$("#pay_type_4").attr("checked",true);
			$("#pay_type_5").attr("checked",true);
		}else if (pay_type_value == 46){
			$("#pay_type_6").attr("checked",true);
			$("#pay_type_4").attr("checked",true);
		}else if (pay_type_value == 56){
			$("#pay_type_5").attr("checked",true);
			$("#pay_type_6").attr("checked",true);
		}else if (pay_type_value == 456){
			$("#pay_type_4").attr("checked",true);
			$("#pay_type_5").attr("checked",true);
			$("#pay_type_6").attr("checked",true);
		}
	}
	
	// 销售凭证类型改变
	$(document).delegate("#doc_type", "change", function(){
		$("#ZFOR_info").hide();
		$("#ZFGC_info").hide();
		if("ZFOR" == $("#doc_type").val())
			$("#ZFOR_info").show();
		if("ZFGC" == $("#doc_type").val())
			$("#ZFGC_info").show();
	});
	
	// 订单类型改变
	$(document).delegate("#process_id", "change", function(){
		$("#process_id_tr").hide();
		var val = $(this).find("option:selected").text();
		if(val.indexOf("统一") != -1)
			$("#process_id_tr").show();
	});
	
	//提交表单(验证价格是否为负和字段是否超过长度)
	$("#btn_submit").click(function(){

		if ($("#user_tel").val() != "") {
			var phone=/^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/;
			if (phone.exec($("#user_tel").val())){
				$("#span_msg_tel__error").hide();
			}else{
				$("#span_msg_tel__error").show();
				return false;
			}
		}
		if ($("#user_phone").val() != "") {
			var mobile=/^((\(\d{2,3}\))|(\d{3}\-))?(1[34587]\d{9})$/;
			if (mobile.exec($("#user_phone").val())){
				$("#span_msg_phone__error").hide();
			}else{
				$("#span_msg_phone__error").show();
				return false;
			}
		}
		if ($("#pd_id", "#tbodyContent").length == 0) {
	        alert("请至少添加一个订单产品信息！");
	        return false;
		}
		var notSelectedPd = false;
		$("#pd_id", "#tbodyContent").each(function(){
			if ("" == $.trim(this.value)){
				notSelectedPd = true;
			}
		});
		if (notSelectedPd) {
	        alert("您还没有选择产品，请选择！");
	        return false;
		}
		
	    if (judgeSelectedValueIsRepeat("pd_id", $("#tbodyContent"))) {
	        alert("您选择的产品型号有重复，请重新选择！");
	        return false;
	    }

	    // 判断是否选择了统一流程，如果是则需要选择订单分类
	    $("#process_state_2").removeAttr("dataType");
	    if($("#process_id").find("option:selected").text().indexOf("统一") != -1)
	    	$("#process_state_2").attr("dataType", "Group").attr("msg", "请选择一种订单分类！");
	    
		// 判断是否选择了自提，如果是必须填写收货人姓名和手机号码
		$("#user_name").removeAttr("dataType");
		$("#user_phone").removeAttr("dataType");
		$("#user_addr").removeAttr("dataType");
		if($("#send_type_2").attr("checked")){
			$("#user_name").attr("dataType", "Require").attr("msg", "收货人姓名必须填写！");
			$("#user_phone").attr("dataType", "Require").attr("msg", "收货人手机不能为空！");
			$("#user_addr").attr("dataType", "Require").attr("msg", "收货人地址不能为空！");
		}

		//付款信息
		var pay_value = "";
		$("input[name='pay_type']").each(function(){
	        if($(this).is(':checked')){
	        	pay_value += $(this).val() + "";
	       	}
    	})
    	$("#pay_type_value").val(pay_value);
					
		if (Validator.Validate(this.form, 1)){

          /**	if ("${call_r3_interface}" == "true") {
				var check_stocks = $("#stocks_check_flag").val();
				if (check_stocks == '') {
					alert("请先检查库存后再提交！");
					return;
				} else if (check_stocks == 0 && !confirm("部分产品库存不足，提交可能导致部分产品无法发货，确定继续？")) {
					return;
				}
			}*/
			
			if (confirm("确定提交表单？")) {
				if($("#country").val() != ""){
					$("#user_p_index").val($("#country").val());
				}else{
					if($("#city").val() != ""){
						$("#user_p_index").val($("#city").val());
					}else{
						if($("#province").val() != ""){
							$("#user_p_index").val($("#province").val());
						}
					}
				}
				if ("${confirm}" == "1") {
					if (!confirm("没有找到上级分公司，订单将会丢失无法审核，确定要提交吗？（提示：您可以联系管理员进行客户分配后再登记）")) {
						return false;
					}
				}
				$("#is_submit").val(1);
				$("#btn_submit").attr("value", "正在提交...").css("color", "#8B0000").attr("disabled", "true");
		        $("#btn_back").attr("disabled", "true");
		        $("#btn_retract").attr("disabled", "true");
		        $("#btn_temp_save").attr("disabled", "true");
		        $("#btn_temp_save_add").attr("disabled", "true");
				f.submit();
			} else {
				return false;
			}
		}
	});

	// 暂存表单(验证价格是否为负和字段是否超过长度)
	$("#btn_temp_save").click(function(){
		
		if ($("#user_tel").val() != "") {
			var phone=/^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/;
			if (phone.exec($("#user_tel").val())){
				$("#span_msg_tel__error").hide();
			}else{
				$("#span_msg_tel__error").show();
				return false;
			}
		}
		if ($("#user_phone").val() != "") {
			var mobile=/^((\(\d{2,3}\))|(\d{3}\-))?(1[3458]\d{9})$/;
			if (mobile.exec($("#user_phone").val())){
				$("#span_msg_phone__error").hide();
			}else{
				$("#span_msg_phone__error").show();
				return false;
			}
		}
		if ($("#pd_id", "#tbodyContent").length == 0) {
	        alert("请至少添加一个订单产品信息！");
	        return false;
		}
		var notSelectedPd = false;
		$("#pd_id", "#tbodyContent").each(function(){
			if ("" == $.trim(this.value)){
				notSelectedPd = true;
			}
		});
		if (notSelectedPd) {
	        alert("您还没有选择产品，请选择！");
	        return false;
		}
		
	    if (judgeSelectedValueIsRepeat("pd_id", $("#tbodyContent"))) {
	        alert("您选择的产品型号有重复，请重新选择！");
	        return false;
	    }
	    
	 	// 判断是否选择了统一流程，如果是则需要选择订单分类
	    $("#process_state_2").removeAttr("dataType");
	    if($("#process_id").find("option:selected").text().indexOf("统一") != -1)
	    	$("#process_state_2").attr("dataType", "Group").attr("msg", "请选择一种订单分类！");

	 	// 判断是否选择了自提，如果是必须填写收货人姓名和手机号码
	    $("#user_name").removeAttr("dataType");
		$("#user_phone").removeAttr("dataType");
		$("#user_addr").removeAttr("dataType");
		if($("#send_type_2").attr("checked")){
			$("#user_name").attr("dataType", "Require").attr("msg", "收货人姓名必须填写！");
			$("#user_phone").attr("dataType", "Require").attr("msg", "收货人手机不能为空！");
			$("#user_addr").attr("dataType", "Require").attr("msg", "收货人地址不能为空！");
		}

		//付款信息
		var pay_value = "";
		$("input[name='pay_type']").each(function(){
	        if($(this).is(':checked')){
	        	pay_value += $(this).val() + "";
	       	}
    	})
    	$("#pay_type_value").val(pay_value);
		
		if (Validator.Validate(this.form, 1)){
			if($("#country").val() != ""){
				$("#user_p_index").val($("#country").val());
			}else{
				if($("#city").val() != ""){
					$("#user_p_index").val($("#city").val());
				}else{
					if($("#province").val() != ""){
						$("#user_p_index").val($("#province").val());
					}
				}
			}
			if (${confirm == 1}) {
				if (!confirm("没有找到上级分公司，订单将会丢失无法审核，确定要提交吗？（提示：您可以联系管理员进行客户分配后再登记）")) {
					return false;
				}
			}
			$("#forward_type").val(1);//暂存
			$("#btn_submit").attr("disabled", "true");
			$("#btn_retract").attr("disabled", "true");
	        $("#btn_temp_save").attr("value", "正在保存...").css("color", "#8B0000").attr("disabled", "true");
	        $("#btn_temp_save_add").attr("disabled", "true");
	        $("#btn_back").attr("disabled", "true");
			f.submit();
		}
	});

	// 暂存表单并新建 (验证价格是否为负和字段是否超过长度)
	$("#btn_temp_save_add").click(function(){
		if ($("#user_tel").val() != "") {
			var phone=/^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/;
			if (phone.exec($("#user_tel").val())){
				$("#span_msg_tel__error").hide();
			}else{
				$("#span_msg_tel__error").show();
				return false;
			}
		}
		if ($("#user_phone").val() != "") {
			var mobile=/^((\(\d{2,3}\))|(\d{3}\-))?(1[3458]\d{9})$/;
			if (mobile.exec($("#user_phone").val())){
				$("#span_msg_phone__error").hide();
			}else{
				$("#span_msg_phone__error").show();
				return false;
			}
		}
		if ($("#pd_id", "#tbodyContent").length == 0) {
	        alert("请至少添加一个订单产品信息！");
	        return false;
		}
		var notSelectedPd = false;
		$("#pd_id", "#tbodyContent").each(function(){
			if ("" == $.trim(this.value)){
				notSelectedPd = true;
			}
		});
		if (notSelectedPd) {
	        alert("您还没有选择产品，请选择！");
	        return false;
		}
		
	    if (judgeSelectedValueIsRepeat("pd_id", $("#tbodyContent"))) {
	        alert("您选择的产品型号有重复，请重新选择！");
	        return false;
	    }
	    
	 	// 判断是否选择了统一流程，如果是则需要选择订单分类
	    $("#process_state_2").removeAttr("dataType");
	    if($("#process_id").find("option:selected").text().indexOf("统一") != -1)
	    	$("#process_state_2").attr("dataType", "Group").attr("msg", "请选择一种订单分类！");

	 	// 判断是否选择了自提，如果是必须填写收货人姓名和手机号码
	    $("#user_name").removeAttr("dataType");
		$("#user_phone").removeAttr("dataType");
		$("#user_addr").removeAttr("dataType");
		if($("#send_type_2").attr("checked")){
			$("#user_name").attr("dataType", "Require").attr("msg", "收货人姓名必须填写！");
			$("#user_phone").attr("dataType", "Require").attr("msg", "收货人手机不能为空！");
			$("#user_addr").attr("dataType", "Require").attr("msg", "收货人地址不能为空！");
		}

		//付款信息
		var pay_value = "";
		$("input[name='pay_type']").each(function(){
	        if($(this).is(':checked')){
	        	pay_value += $(this).val() + "";
	       	}
    	})
    	$("#pay_type_value").val(pay_value);
		
		if (Validator.Validate(this.form, 1)){
			if($("#country").val() != ""){
				$("#user_p_index").val($("#country").val());
			}else{
				if($("#city").val() != ""){
					$("#user_p_index").val($("#city").val());
				}else{
					if($("#province").val() != ""){
						$("#user_p_index").val($("#province").val());
					}
				}
			}
			if (${confirm == 1}) {
				if (!confirm("没有找到上级分公司，订单将会丢失无法审核，确定要提交吗？（提示：您可以联系管理员进行客户分配后再登记）")) {
					return false;
				}
			}
			$("#forward_type").val(2); //暂存并新建
			$("#btn_submit").attr("disabled", "true");
	        $("#btn_back").attr("disabled", "true");
	        $("#btn_retract").attr("disabled", "true");
	        $("#btn_temp_save").attr("disabled", "true");
	        $("#btn_temp_save_add").attr("value", "正在保存...").css("color", "#8B0000").attr("disabled", "true");
			f.submit();
		}
	});
	
	/*处理回显订单详细数据 ===start===*/
	<c:if test="${not empty konkaOrderInfoDetailsList}">
		$("#tbodyContent").empty();
		<c:forEach items="${konkaOrderInfoDetailsList}" var="cur" varStatus="vs">
			$("#trHidden").clone().children().appendTo($("#tbodyContent")).show();
	    	var lastTR = $("table:last", "#tbodyContent");
	    	
	    	var pd_id = $("#pd_id", lastTR);//产品型号id
	    	var md_name = $("#md_name", lastTR);//产品型号id
	      	
			var good_count = $("#good_count", lastTR);//数量
			var good_unit = $("#good_unit", lastTR);//单位
			var good_price = $("#good_price", lastTR);//单价
			
			var good_sum_price = $("#good_sum_price", lastTR);//金额
			var sum_price = $("#sum_price", lastTR);//隐藏域金额
			var good_discount = $("#good_discount", lastTR);//折扣
			//var single_discount_price = $("#single_discount_price", lastTR);//单台折扣
			var good_discount_price = $("#good_discount_price", lastTR);//折扣金额
			var discount_price = $("#discount_price", lastTR);//隐藏域折扣金额

			var s_good_discount_price = $("#s_good_discount_price", lastTR);//总折扣
			var s_discount_price = $("#s_discount_price", lastTR);//隐藏域总折扣
			
			var pd_remark = $("#pd_remark", lastTR);//产品备注
			var span_1 = $("#span_1", lastTR);//产品备注
			
			span_1.attr("id", "${cur.pd_name}");
			
			good_count.attr("dataType","Number").attr("msg","请填写数量,且必须为正整数！");
			good_price.attr("dataType","Currency").attr("msg","请填写单价，且必须为正数！");
			//good_discount.attr("dataType","Currency").attr("msg","请填写折扣，且必须为正数！");
			good_discount.attr("dataType","Range").attr("min","-100").attr("max","0.00000001").attr("msg","请填写折扣率，且必须为负数！").attr("Require", "false");
			good_discount_price.attr("dataType","Range").attr("dataType","false").attr("min","-100000").attr("max","0").attr("msg","请填写折扣金额，且必须为负数！").attr("Require", "false");
			
			
			pd_id.val('${cur.pd_id}');
	      	md_name.text('${cur.pd_name}');
	      	
			good_count.val('${cur.good_count}');
			good_unit.val('${cur.good_unit}');
			
			good_price.val('${cur.good_price}');
			
			good_sum_price.val('${cur.good_sum_price}');
			sum_price.val('${cur.good_sum_price}');
			
			good_discount.val((Number('${cur.good_discount}')).toFixed(2));
			good_discount_price.val((Number('${cur.good_discount_price}')).toFixed(2));
			//single_discount_price.val((good_discount_price.val() / good_count.val()).toFixed(2));
			discount_price.val((Number('${cur.good_discount_price}')).toFixed(2));

			//计算总折扣
			var sg_price = (Number('cur.good_sum_price')*Number('cur.good_discount') + Number('cur.good_discount_price')).toFixed(2);

			s_good_discount_price.val(sg_price);
			s_discount_price.val(sg_price);		
			alert(33);
			
			pd_remark.val('${cur.pd_remark}');

			calcOneCountAndJhPrice(good_count, good_price, good_sum_price, sum_price);
			calcPdNumAndPrice("tbodyContent");
			
			//bindJhCountAndJhPrice(good_count, good_price, good_sum_price, sum_price, good_discount, good_discount_price, discount_price, single_discount_price, lastTR);
			bindJhCountAndJhPrice(md_name,good_count, good_price, good_sum_price, sum_price, good_discount, good_discount_price, discount_price,s_good_discount_price,s_discount_price,lastTR);
			
			$("table:last", lastTR).click(function (){
				var pd_id_td1 = $("#pd_id", $(this).parent()).val()+",";
				var pd_id_td2 = ","+$("#pd_id", $(this).parent()).val();
				var pd_ids_new1 = $("#pd_ids").val().replace(pd_id_td1,"");
				pd_ids_new2 = pd_ids_new1.replace(pd_id_td2,"");
				
				$("#pd_ids").val(pd_ids_new2);//删除掉的id，得到新的已经选择的id数组
				
		    	$(this).parent().remove();
		    	 calcPdNumAndPrice("tbodyContent");
		    }).css("cursor", "pointer");
	    </c:forEach>
	  </c:if>
	  /*处理回显订单详细数据 ===end===*/
	  
	  // 点击查询额度
	  $(document).delegate("#btn_can_use_money", "click", function(){
		  $("#can_use_money").html("<img src='${ctx}/styles/images/loading.gif' />");
		  $.ajax({
			  type: "POST",
			  url: "${ctx}/customer/manager/JxcKonkaOrderRegister.do?method=getCustomerCreditByR3CodeForAjax",
			  data: {"r3_code" : '${af.map.r3_code}', "timestamp" : new Date().getTime()},
			  dataType: "json",
			  sync: true, 
			  error: function (xhr, ajaxOptions, thrownError) { alert("数据加载请求失败【" + xhr.statusText + "," + xhr.responseText + "," + xhr.status + "," + thrownError +"】！"); },
			  success: function(result) {
				if(result.status == "-1"){
					alert(result.msg);
					return;
				}
				$("#can_use_money").html(result.data);
			  }
		  }); 
	  });
});


//检查库存
$("#btn_check").click(function(){
	
	var sales_org = $("#sales_org").val();
	var md_names = [];
	var good_counts = [];
	
	$(".md_name").each(function(){
		md_names[md_names.length] = $.trim($(this).text());
	});
	
	$(".good_count").each(function(){
		good_counts[good_counts.length] = $.trim($(this).val());
	});
	
	if (md_names.length == 1) {
		alert("请先选择产品物料！");
		return;
	}
	
	md_names.shift();
	good_counts.shift();
	
	//console.info("sales_org : ", sales_org);
	//console.info("md_names : ", md_names);
	//console.info("good_counts : ", good_counts);
	
	/// ajax begin 
	$.ajax({
		type: "POST",
		url: "${ctx}/customer/manager/JxcKonkaOrderRegister.do?method=checkStockForAjax",
		data: {"sales_org" : sales_org, "md_name" : md_names.join(','), "good_count" : good_counts.join(',')},
		dataType: "json",
		sync: true, 
		error: function (xhr, ajaxOptions, thrownError) { alert("数据加载请求失败【" + xhr.statusText + "," + xhr.responseText + "," + xhr.status + "," + thrownError +"】！"); },
		success: function(result) {
			if(result == null){
				alert("库存检查发生异常！");
				return;
			}
			////显示
			//[{"amount":1.000,"bukrs":"KF28","isOk":0,"lamount":0,"lgort":"9028","matnr":"LCM17BT35H","werks":"L00A"}]
			//[{"amount":1.000,"bukrs":"KF28","isOk":0,"lamount":0,"lgort":"9028","matnr":"LCM17BT35H","werks":"L00A"}]
			//alert(result.length);
			var ispass = 1; // 默认所有产品都有库存
			for(var i = 0 ;i< result.length ;i++){
				if(result[i].isOk == 0){
					$("#"+result[i].matnr).text("  库存不足!").css("color","red");
					ispass = ispass * 0;
				} else {
					$("#"+result[i].matnr).text("  有库存!").css("color","green");
				}
			}
			$("#stocks_check_flag").val(ispass);
		}
	}); /// ajax end 
});//检查库存 end 

var n = Number("${fn:length(attachmentList)}");
window.task_index = 0 + n;
window.index = 0 + n;
function addFile(){
	task_index++;
	index++; //解决file控件
	$("#fileTbody").append('<tr id="picModelTr_' + index + '">' +
								'<td align="center">1</td>' +
								'<td align="left" nowrap="nowrap"><input type="file" name="pic_file_' + task_index + '" id="pic_file_' + task_index + '" class="webinput" style="width:150px;" /></td>' +
								'<td align="center"><input type="text" name="file_desc" id="file_desc_' + task_index + '" class="" style="width:94%" /></td>' +
								'<td align="center" nowrap="nowrap" style="cursor:pointer;"><img src="${ctx}/styles/jxc/images/x.gif" style="vertical-align:middle;" /></td>' +
						   '</tr>');

	var lastTR = $("tr:last", "#fileTbody");
	lastTR.children().eq(0).text(task_index);
	$("input[type='file']", lastTR).attr("dataType", "Require").attr("msg", "请上传附件！");
	$("td:last", lastTR).click(function (){
		task_index--;
		$(this).parent().remove();
		var i = 1;
		$("tr", "#fileTbody").each(function(){
			if (i <= task_index) {
				$(this).find("td").eq(0).text(i);
				$("input[type='file']", $(this)).attr("id", "pic_file_" + i);
				$("input[type='file']", $(this)).attr("name", "pic_file_" + i);
				$("input[type='text']", $(this)).attr("id", "file_desc_" + i);
				i++;
			}
		});
	});
}

function delFile(obj){
	var parTr = obj.parent();
	task_index--;
	parTr.remove();
	var i = 1;
	$("tr", "#fileTbody").each(function(){
		if (i <= task_index) {
			$(this).find("td").eq(0).text(i);
			$("input[type='file']", $(this)).attr("id", "pic_file_" + i);
			$("input[type='file']", $(this)).attr("name", "pic_file_" + i);
			$("input[id^=file_desc_]", $(this)).attr("id", "file_desc_" + i);
			i++;
		}
	});
}

function getPePdModel() { 
	$.dialog({
		title:  "产品列表",
		width:  400,
		height: 390,
        lock:true ,
		content:"url:${ctx}/customer/manager/SelectPePdModel.do?method=newList&selectype=mutiple&selects=" + $("#pd_ids").val() + "&azaz=" + Math.random()
	});
}

function getProInfo(){
	//var returnValue = window.open('${ctx}/customer/manager/SelectPePdModel.do?selectype=mutiple&selects=' + $("#pd_ids").val() + '&azaz=' + Math.random(), '机型选择', 'height=390, width=620, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no') 
	//if (returnValue != null && returnValue.ids != "") {
	var ids = $("#pro_ids").val();
	var names = $("#pro_names").val();
	if (ids != "") {
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
			$("#trHidden").clone().children().appendTo($("#tbodyContent")).show();
	    	var lastTR = $("table:last", "#tbodyContent");
	    	
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
			//var single_discount_price = $("#single_discount_price", lastTR);//单台折扣
			var discount_price = $("#discount_price", lastTR);//隐藏域折扣金额

			var s_good_discount_price = $("#s_good_discount_price", lastTR);//总折扣
			var s_discount_price = $("#s_discount_price", lastTR);//隐藏域总折扣
			
			var pd_remark = $("#pd_remark", lastTR);//产品备注
			
			good_count.attr("dataType","Number").attr("msg","请填写数量,且必须为正整数！");
			good_price.attr("dataType","Currency").attr("msg","请填写单价！");
			//good_discount.attr("dataType","Currency").attr("msg","请填写折扣，且必须为正数！");
			good_discount.attr("dataType","Range").attr("min","-100").attr("max","0.00000001").attr("msg","请填写折扣率，且必须为负数！").attr("Require", "false");
			good_discount_price.attr("dataType","Range").attr("dataType","false").attr("min","-100000").attr("max","0").attr("msg","请填写折扣金额，且必须为负数！").attr("Require", "false");
			
			pd_id.val(pd_id_array[i]);//隐藏域选择的产品
	      	md_name.text(md_name_array[i]);
	      	md_name1.val(md_name_array[i]);

			good_count.val("1");
			good_unit.val("台");
			
			good_price.val("0");
			
			span_1.attr("id",md_name_array[i]);//改变id
			
			good_sum_price.val("0");
			sum_price.val("0");
			
			good_discount.val("0");
			good_discount_price.val("0");
			//single_discount_price.val("0");

			s_good_discount_price.val("0");
			s_discount_price.val("0");
			
			pd_remark.val("");

			calcOneCountAndJhPrice(good_count, good_price, good_sum_price, sum_price);
			good_discount_price.val(parseFloat(good_sum_price.val())*parseFloat(good_discount.val())/100);//折扣金额
			discount_price.val(parseFloat(good_sum_price.val())*parseFloat(good_discount.val())/100);//隐藏域折扣金额

			calcPdNumAndPrice("tbodyContent");

			//bindJhCountAndJhPrice(good_count, good_price, good_sum_price, sum_price, good_discount, good_discount_price, discount_price, single_discount_price,lastTR);
			bindJhCountAndJhPrice(md_name1,good_count, good_price, good_sum_price, sum_price, good_discount, good_discount_price, discount_price,s_good_discount_price,s_discount_price,lastTR);			

			//删除操作，影响弹出页面的返回值
			$("#del", lastTR).click(function (){
				var pd_id_td1 = $("#pd_id", $(this).parent()).val()+",";
				var pd_id_td2 = ","+$("#pd_id", $(this).parent()).val();
				var pd_ids_new1 = $("#pd_ids").val().replace(pd_id_td1,"");
				pd_ids_new2 = pd_ids_new1.replace(pd_id_td2,"");
				
				$("#pd_ids").val(pd_ids_new2);//删除掉的id，得到新的已经选择的id数组
				
		    	$(this).closest("table").remove();
		    	calcPdNumAndPrice("tbodyContent");
		    }).css("cursor", "pointer");
		}
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
//function bindJhCountAndJhPrice(good_count, good_price, good_sum_price, sum_price, good_discount, good_discount_price, discount_price, single_discount_price,lastTR) {
function bindJhCountAndJhPrice(md_name1,good_count, good_price, good_sum_price, sum_price, good_discount, good_discount_price, discount_price,s_good_discount_price,s_discount_price,lastTR) {	
	good_count.blur(function(){//数量
		var md_name = md_name1.val();	
		
		var _reg = /^\d+$/;

		var count =(this.value);
   		if (!_reg.test(count)) {
   			good_count.val(1);
   			count = 1;
   		}

   		if(md_name=='KF-22PB'){
   			good_count.val(1);
   			count = 1;
   	   	 }
   		
   		var price = parseFloat(good_price.val());//单价
   		if(isNaN(price)) price = 0;
   		
   		var discount = parseFloat(good_discount_price.val());//单台折扣
   		if(isNaN(discount)) discount = 0;

   		//var g_discount = parseFloat(good_discount_price.val());//总折扣金额
   		//if(isNaN(g_discount)) g_discount = 0;

   		count = parseFloat(count);//数量
   		if(isNaN(count)) count = 1;


   		var g_good_discount = parseFloat(good_discount.val());//折扣率
   		if(isNaN(g_good_discount)) g_good_discount = 0;

   		var g_good_discount_price = (discount + (g_good_discount * price * count)/100).toFixed(2);
   	   	
   		good_sum_price.val((count * price).toFixed(2));//金额
   		sum_price.val((count * price).toFixed(2));//隐藏域金额
   		s_good_discount_price.val(g_good_discount_price);//折扣金额
   		s_discount_price.val(g_good_discount_price);//隐藏域折扣金额
   		
   		///dis_money = good_discount_price.val();
   		//if(isNaN(dis_money)) dis_money = 0;
   		//good_discount.val((count * price) == 0 ? '-' : (dis_money * 100 / (count * price)).toFixed(2));//折扣率
   		
		calcPdNumAndPrice("tbodyContent");
	});

	good_price.keyup(function(){//单价
		
		var price = (this.value);
		var _reg = /^[\+\-]?\d*?\.?\d*?$/;
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

   		var discount = parseFloat(good_discount_price.val());//折扣
   		if(isNaN(discount)) discount = 0;

   		var g_good_discount = parseFloat(good_discount.val());//折扣率
   		if(isNaN(g_good_discount)) g_good_discount = 0;

   		var g_good_discount_price = (discount + (g_good_discount * price * count)/100).toFixed(2);
   		
   		good_sum_price.val((count * price).toFixed(2));//金额
   		sum_price.val((count * price).toFixed(2));//隐藏域金额
   		s_good_discount_price.val(g_good_discount_price);//折扣金额
   		s_discount_price.val(g_good_discount_price);//隐藏域折扣金额
   		
   		//dis_money = good_discount_price.val();
   		//if(isNaN(// 取价格组维护的机型价格 同一时期内,某一客户只能属于某公司下的一个客户群组 dis_money)) dis_money = 0;
   		//good_discount.val((count * price) == 0 ? '-' : (dis_money * 100 / (count * price)).toFixed(2));//折扣率
   		
		calcPdNumAndPrice("tbodyContent");
	});

	// 单台折扣
	//single_discount_price.keyup(function(){
	//	var discount = (this.value); // 单台折扣金额
	//	var _reg = /^[\-]?\d*?\.?\d*?$/;
	//	if (!_reg.test(discount)) {
	//		good_discount_price.val(0);
	//		discount = 0;
	//	}
	//	discount = parseFloat(this.value);
	//	if(isNaN(discount)) discount = 0;
		
	//	var count = good_count.val(); // 台数
	//	if(isNaN(count)) count = 0;
		
   	//	var sum_price = parseFloat(good_sum_price.val());//金额
   	//	if(isNaN(sum_price)) sum_price = 0;


   	//	var g_good_discount = parseFloat(good_discount.val());//折扣率
   	//	if(isNaN(g_good_discount)) g_good_discount = 0;

   	//	var g_good_discount_price = ((discount * count) + (g_good_discount * sum_price)/100).toFixed(2);
   		
   	//	good_discount_price.val(g_good_discount_price);
   		//good_discount_price.val((discount*count) + sum_price*);
   		//discount_price.val((discount*count).toFixed(2));
   	//	discount_price.val(g_good_discount_price);//隐藏域折扣金额
   		//good_discount.val(sum_price == 0 ? '-' : (parseFloat(discount_price.val()) * 100 / sum_price).toFixed(2)); // 折扣率
	//	calcPdNumAndPrice("tbodyContent");
//	});

		// 总折扣
		good_discount_price.blur(function(){
			var discount = (this.value); // 折扣总金额
			var md_name = md_name1.val();	
			
			var _reg = /^[\-]\d*?\.?\d*?$/;
			if (!_reg.test(discount)) {
				good_discount_price.val(0);
				discount = 0;
				alert("折扣金额必须填负数");
			}

			if(md_name=='KF-22PB'){
				good_discount_price.val(0);
				discount = 0;
	   	   	 }
			//discount = parseFloat(this.value);
			
			//if(isNaN(discount)) discount = 0;
			
			//var count = good_count.val(); // 台数
			//if(isNaN(count)) count = 0;
			
	   		var sum_price = parseFloat(good_sum_price.val());//金额
	   		if(isNaN(sum_price)) sum_price = 0;
	   		
	   		var g_good_discount = parseFloat(good_discount.val());//折扣率
   			if(isNaN(g_good_discount)) g_good_discount = 0;

   			var g_good_discount_price = (Number(discount) + Number(g_good_discount * sum_price)/100).toFixed(2);
	   		
   			s_good_discount_price.val(g_good_discount_price);//折扣金额
   	   		s_discount_price.val(g_good_discount_price);//隐藏域折扣金额
	   		discount_price.val(discount);
	   		//good_discount.val(sum_price == 0 ? '-' : (parseFloat(discount_price.val()) * 100 / sum_price).toFixed(4)); // 折扣率
	   		
			calcPdNumAndPrice("tbodyContent");
		});
	
	// 折扣率修改计算单台和总折扣
	good_discount.blur(function(){//折扣
		var discount = (this.value);
		var md_name = md_name1.val();
		
		var _reg = /^[\-]\d*?\.?\d*?$/;

		if (!_reg.test(discount)) {
			good_discount.val(0);
			discount = 0;
			alert("折扣率必须填负数");
		}

		if(md_name=='KF-22PB'){
			good_discount.val(0);
			discount = 0;
   	   	 }
		
		discount = parseFloat(this.value);
		if(isNaN(discount)) discount = 0;
		if(discount < -100) {
			discount = 0;
			this.value = 0;
		}
   		var sum_price = parseFloat(good_sum_price.val());//金额
   		if(isNaN(sum_price)) sum_price = 0;
   		
   		var count = good_count.val(); // 台数
		if(isNaN(count)) count = 0;

		//var g_single_discount_price = parseFloat(single_discount_price.val());//单台折扣金额
   		//if(isNaN(g_single_discount_price)) g_single_discount_price = 0;
   		//var price = good_price.val(); // 单价
		//if(isNaN(price)) price = 0;
   		
   		var g_discount_price = parseFloat(discount_price.val());
   		if(isNaN(g_discount_price)) g_discount_price = 0;
   		
   		var g_good_discount_price = (g_discount_price + sum_price*discount/100).toFixed(2);
   		
   		//var var_sum_price = (count * price).toFixed(2); // 计算总价
	   	
   		s_good_discount_price.val(g_good_discount_price);//折扣金额
   	   	s_discount_price.val(g_good_discount_price);//隐藏域折扣金额
   		//single_discount_price.val((discount_price.val() / count).toFixed(2)); // 单台折扣
   		//good_discount.val(discount.toFixed(2));
		calcPdNumAndPrice("tbodyContent");
	});
	
	calcPdNumAndPrice("tbodyContent");
}

//计算 单个的金额
function calcOneCountAndJhPrice(good_count, good_price, good_sum_price, sum_price) {
	var good_count = parseFloat(good_count.val());//数量
   	if(isNaN(good_count)) good_count = 1;
   	var good_price =  parseFloat(good_price.val());//单价
   	if(isNaN(good_price)) good_price = 0;
   	
   	good_sum_price.val((good_count * good_price).toFixed(2));//金额
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
		var this_value = parseFloat($(this).val());
		if(isNaN(this_value)) this_value = 0;
		dd_money_sum += this_value;
	});
	
	//$("#good_discount_price", $("#" + tbody_id)).each(function(){
	//	var this_value = parseFloat($(this).val());
	//	if(isNaN(this_value)) this_value = 0;
	//	dd_discount_sum += this_value;
	//});

	$("#s_discount_price",$("#" + tbody_id)).each(function(){
		var this_value = parseFloat($(this).val());
		if(isNaN(this_value)) this_value = 0;
		dd_discount_sum += this_value;
	});
	
	$("#dd_count_sum").val(dd_count_sum);
	$("#sqsl_span").val(dd_count_sum); //申请数量
	$("#dd_money_sum").val("￥" + (dd_money_sum.toFixed(2)));
	$("#sqje_span").val("￥" + (dd_money_sum.toFixed(2)));
	$("#dd_discount_sum").val("￥" + (dd_discount_sum.toFixed(2)));
	$("#pay_money").val(parseFloat(dd_money_sum.toFixed(2)-dd_discount_sum.toFixed(2)).toFixed(2));
	//$("#money").val(dd_money_sum.toFixed(2)-dd_discount_sum.toFixed(2));
	$("#order_num").val(dd_count_sum);//隐藏域订单总数
	$("#money_sum").val(dd_money_sum.toFixed(2));//隐藏域订单金额
	$("#discount_price_sum").val(dd_discount_sum.toFixed(2));//隐藏域订单折扣金额
}

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

$(function(){
	$("#tbodyContent table").each(function(){
		var lastTR = $(this);

		//客户R3编码
	    var r3_code = $("#r3_code").val();
		
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
		//var single_discount_price = $("#single_discount_price", lastTR);//单台折扣
		var discount_price = $("#discount_price", lastTR);//隐藏域折扣金额

		var s_good_discount_price = $("#s_good_discount_price", lastTR);//总折扣
		var s_discount_price = $("#s_discount_price", lastTR);//隐藏域总折扣
		
		var pd_remark = $("#pd_remark", lastTR);//产品备注
		
		good_count.attr("dataType","Number").attr("msg","请填写数量,且必须为正整数！");
		good_price.attr("dataType","Double").attr("msg","请填写单价！");
		//good_discount.attr("dataType","Currency").attr("msg","请填写折扣，且必须为正数！");
		
		good_discount.attr("dataType","Range").attr("min","-100").attr("max","0.00000001").attr("msg","请填写折扣率，且必须为负数！").attr("Require", "false");
		good_discount_price.attr("dataType","Range").attr("dataType","false").attr("min","-100000").attr("max","0").attr("msg","请填写折扣金额，且必须为负数！").attr("Require", "false");
		
		
		

		//pd_id.val(pd_id_array[i]);//隐藏域选择的产品
	  	//md_name.text(md_name_array[i]);
	  	//md_name1.val(md_name_array[i]);
	  	
	  	//ajax动态取产品价格
// 	    	$.ajax( {
// 	    		type : "POST",
// 	    		cache : false,
// 	    		url : "${ctx}/CsAjax.do",
// 	    		data : "method=getKonkaR3PdPrice&pd_sn=" + md_name1.val() + "&r3_code=" + r3_code,
// 	    		dataType: "json",
// 	    		error : function(data) {good_price.val("0");},
// 	    		success : function(data) {
// 	    			good_price.val(parseFloat(data[0].price).toFixed(2));
// 	        	}
// 	    	});
	  	
	  	
	    	// 取价格组维护的机型价格 同一时期内,某一客户只能属于某公司下的一个客户群组 
			// 有了这个客户群组,在同一时期内,为这个客户维护它的所有机型价格 
	    	$.ajax( {
	    		type : "POST",
	    		cache : false,
	    		url : "${ctx}/CsAjax.do",
	    		data : "method=getKonkaR3PdPrice2&pd_sn=" +md_name+"&r3_code=" + r3_code,
	    		dataType: "json",
	    		error : function(data) {good_price.val("0");},
	    		success : function(data) {
	    			good_price.val(parseFloat(data[0].price).toFixed(2));
	        	}
	    	});
	    	
	  	
		good_count.val("1");
		good_unit.val("台");
		
		good_price.val("0");
		
		// span_1.attr("id",md_name_array[i]);//改变id
		
		good_sum_price.val("0");
		sum_price.val("0");
		
		good_discount.val("0");
		good_discount_price.val("0");
		//single_discount_price.val("0");
		
		s_good_discount_price.val("0");
		s_discount_price.val("0");

		pd_remark.val("");

		calcOneCountAndJhPrice(good_count, good_price, good_sum_price, sum_price);
		good_discount_price.val(parseFloat(good_sum_price.val())*parseFloat(good_discount.val())/100);//折扣金额
		discount_price.val(parseFloat(good_sum_price.val())*parseFloat(good_discount.val())/100);//隐藏域折扣金额
		calcPdNumAndPrice("tbodyContent");

		//bindJhCountAndJhPrice(good_count, good_price, good_sum_price, sum_price, good_discount, good_discount_price, discount_price, single_discount_price,lastTR);
		bindJhCountAndJhPrice(md_name1,good_count, good_price, good_sum_price, sum_price, good_discount, good_discount_price, discount_price,s_good_discount_price,s_discount_price,lastTR);;
		
		//删除操作，影响弹出页面的返回值
		$("#del", lastTR).click(function (){
			//var pd_id_td1 = $("#pd_id", $(this).parent()).val()+",";
			//var pd_id_td2 = ","+$("#pd_id", $(this).parent()).val();
			//var pd_ids_new1 = $("#pd_ids").val().replace(pd_id_td1,"");
			//pd_ids_new2 = pd_ids_new1.replace(pd_id_td2,"");
			
			//$("#pd_ids").val(pd_ids_new2);//删除掉的id，得到新的已经选择的id数组
			
	    	$(this).closest("table").remove();
	    	calcPdNumAndPrice("tbodyContent");
	    }).css("cursor", "pointer");
	});
});
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>