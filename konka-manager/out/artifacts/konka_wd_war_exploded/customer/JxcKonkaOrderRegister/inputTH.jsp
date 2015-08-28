<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />-->
<title>${naviString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/css/tab.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/fancybox/fancybox.css" rel="stylesheet" type="text/css" />


<style type="text/css">
 select{font-family:Microsoft YAHEI;font-size:12px;}
 input{font-family:Microsoft YAHEI;font-size:12px;}
 label {cursor:pointer;}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div style="width:100%">
	<div class="oartop">
	    <table width="400" border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
	        <td>当前位置：${naviString}</td>
	      </tr>
	    </table>
  	</div>
  	<div class="rtabcont1" style="position:relative;">
  		<html-el:form action="/manager/JxcKonkaOrderRegister" enctype="multipart/form-data" method="post">
			<html-el:hidden property="method" value="saveReturnOrder" styleId="method"/>
	      	<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	      	<html-el:hidden property="cust_id" styleId="cust_id" value="${cust_id}" />
	      	<html-el:hidden property="order_id" styleId="order_id" value="${order_id}" />
	      	<html-el:hidden property="is_submit" styleId="is_submit" value="0" />
	      	<html-el:hidden property="forward_type" styleId="forward_type" value="0" />
	      	<html-el:hidden property="stocks_check_flag" styleId="stocks_check_flag" />
	      	<html-el:hidden property="pay_type_value" styleId="pay_type_value" value="${af.map.pay_type}" />
	      	<html-el:hidden property="order_type" value="0" />
	      	<html-el:hidden property="pro_ids" styleId="pro_ids"/>
	      	<html-el:hidden property="pro_names" styleId="pro_names"/>
			<ul id="tabs">
			    <li><a href="#" name="tab1">退货信息</a></li>
			    <li><a href="#" name="tab3">附件</a></li>
			    <li><a href="#" name="tab4">意见反馈</a></li>
			</ul>
			<div id="content"> 
			    <div id="tab1">
			    	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tableClass">
				        <tr>
				        
				        	<td class="title_item_no_bc" colspan="4" style="text-align:center;font-size:15px;font-weight:bold;color:#74685F">订单信息</td>
				        </tr>
				        <tr>
							<td class="title_item_no_bc" width="15%"><font color="red">*</font>退货订单流水号：</td>
							<td colspan="3">
							<c:if test="${not empty af.map.trade_index}">
								<span>NO.<font color="red"></font><font color="red">${af.map.trade_index}</font></span>
							</c:if>
							<c:if test="${empty af.map.trade_index}">
								<span><font color="red"></font><font color="red">自动生成</font></span>
							</c:if>
							</td>
						</tr>
						<tr>
							<td class="title_item_no_bc" width="15%">客户名称：</td>
							<td width="35%">
								<c:if test="${empty konkaR3ShopList}">
									<html-el:text property="user_shop_name" styleId="user_shop_name" value="${af.map.user_shop_name}" style="width:90%" maxlength="100" styleClass="webinput" />
								</c:if>
								<c:if test="${not empty konkaR3ShopList}">
									<html-el:select property="ag">
										<c:forEach var="cur" items="${konkaR3ShopList}" varStatus="vs">
											<html-el:option value="${cur.id}">${cur.customer_name}</html-el:option>
										</c:forEach>
									</html-el:select>
								</c:if>
							</td>  
							<td class="title_item_no_bc" width="15%">售达方编码：</td>
							<td width="15%"><html-el:text property="r3_code" styleId="r3_code" value="${af.map.r3_code}" style="width:100px;" maxlength="100" styleClass="webinput" readonly="true" /></td>
						</tr>
						<tr>
							<td class="title_item_no_bc" width="15%"><font color="red">*</font>退货类型：</td>
							<td colspan="3">
								<html-el:select property="return_type" styleId="return_type" >
									<html-el:option value="">请选择..</html-el:option>
									<html-el:option value="1">滞销退货</html-el:option>
									<html-el:option value="2">残次品退货</html-el:option>
									<html-el:option value="3">当月拒收</html-el:option>
									<html-el:option value="6">跨月拒收</html-el:option>
									<html-el:option value="4">异型换机</html-el:option>
									<html-el:option value="5">其他原因</html-el:option>
								</html-el:select>
								<!-- 3原来是客户拒收,后来拆分成当月拒收,跨月拒收 20150618-->
							</td>
						</tr>
						<tr>
							<td class="title_item_no_bc" width="15%"><font color="red" id="red_return_remark"></font>原因说明：</td>
							<td colspan="3">
								<html-el:text property="return_type_remark" styleId="return_type_remark" style="width:550px;" maxlength="100" styleClass="webinput" />
							</td>
						</tr>
						<!-- 常规信息 -->
						<tr>
							<td colspan="4" align="left" style="font-weight:bold;color:#74685F;border-bottom:1px solid #74685F;">常规信息</td>
						</tr>
						<tr>
							<td class="title_item_no_bc">订单日期：</td>
							<td><html-el:text property="order_date" styleId="order_date" style="width:130px;" size="10" maxlength="9" readonly="true" styleClass="webinput" value="${af.map.today}"/></td>
							<c:if test="${sessionScope.from_manager_is_salesman eq '1'}">
								<td class="title_item_no_bc"><font color="red">*</font>订单流程：</td>
								<td>
									<html-el:select property="process_id" styleId="process_id" style="width:200px;">
						          		<html-el:option value="">=请选择=</html-el:option>
						          		<c:forEach var="cur" items="${processList}">
						          			<html-el:option value="${cur.id}">
						          			    <c:if test="${cur.add_dept_id eq 0}">★[统一流程] </c:if>
							          			<c:if test="${cur.add_dept_id ne 0}">[${cur.add_dept_name}]</c:if>
							          			${cur.process_desc}
						          			</html-el:option>
						          		</c:forEach>
						          	</html-el:select>
								</td>
							</c:if>
							<c:if test="${sessionScope.from_manager_is_salesman ne '1'}">
							<td class="title_item_no_bc">关联进货单：</td>
							<td><html-el:text property="r_trade_index" size="25" maxlength="30" styleId="r_trade_index" value="${pd_trade_index}" />&nbsp;<input type="button" value=" 选 择 " onclick="openChild()" /></td>
							</c:if>
						</tr>
						<tr>
							<td class="title_item_no_bc">订单状态：</td>
							<td>
								<html-el:select property="audit_state" styleId="audit_state" disabled="true">
									<html-el:option value="0">制单</html-el:option>
									<html-el:option value="1">审核中</html-el:option>
									<html-el:option value="2">审核未通过</html-el:option>
									<html-el:option value="3">审核通过</html-el:option>
								</html-el:select>
							</td>
							<td class="title_item_no_bc">出货状态：</td>
							<td>
								<html-el:select property="" styleId="" disabled="true">
									<html-el:option value="">未出货</html-el:option>
									<html-el:option value="">出货中</html-el:option>
									<html-el:option value="">已出货</html-el:option>
								</html-el:select>
							</td>
						</tr>
						<tr>
							<td class="title_item_no_bc">制单人：</td>
							<td><html-el:text property="userName" value="${af.map.userName}" style="width:100px;" styleClass="webinput" readonly="true" /></td>
							<td class="title_item_no_bc">业务员：</td>
							<td><html-el:text property="ywy_user_name" value="${ywy_user_name}" style="width:100px;" styleClass="webinput" readonly="true" /></td>
						</tr>
						<tr style="display:none;" id="process_id_tr">
							<td class="title_item_no_bc">订单分类：</td>
							<td colspan="3">
							    <label for="process_state_1"><html-el:radio property="process_state" styleId="process_state_1" value="1" /> 一般订单</label>
								<span style="color:#999;margin-left:3em;">一般订单是指订单的审核流程不需要经过分公司总经理审批的订单</span><br />
								<label for="process_state_2"><html-el:radio property="process_state" styleId="process_state_2" value="2" /> 特殊订单</label>
								<span style="color:#999;margin-left:3em;">特殊订单是指订单的审核流程需要经过分公司总经理审批的订单</span></td>
						</tr>
						<tr>
							<td class="title_item_no_bc">采购订单编号：</td>
							<td><html-el:text property="third_cg_order_num" maxlength="50" style="width:100px;" styleClass="webinput" /></td>
							<td class="title_item_no_bc">采购订单日期：</td>
							<td><fmt:formatDate value="${af.map.cg_order_date}" pattern="yyyy-MM-dd" var="cg_order_date" />
            				<html-el:text property="cg_order_date" styleClass="webinput" styleId="cg_order_date" size="10" maxlength="10" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="cursor:pointer;text-align:center;width:100px;" value="${cg_order_date}" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<td class="title_item_no_bc">备注：</td>
							<td colspan="3">
								<html-el:textarea property="remark" styleId="remark" style="width:80%;" rows="2"></html-el:textarea>
								<div id="remark_note"  style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/styles/images/tishi.gif" style="vertical-align:middle;" /></div>
							</td>
						</tr>
						<!-- 产品明细 -->
						<tr>
							<td colspan="4" align="left" style="font-weight:bold;color:#74685F;border-bottom:1px solid #74685F;">产品明细</td>
						</tr>
						<tr>
							<td colspan="4">
									<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass" style="margin:5px 0px;">
    								<tr class="title_top_no_bc">
										<td width="14%" nowrap="nowrap">产品型号</td>
										<td width="8%" nowrap="nowrap">数量</td>
										<td width="5%" nowrap="nowrap">单位</td>
										<td width="8%" nowrap="nowrap">单价</td>
										<td width="10%" nowrap="nowrap">金额</td>
										<td width="10%" nowrap="nowrap">折扣金额-RB00</td>
										<td width="10%" nowrap="nowrap">折扣(%)-K007</td>
										<td width="8%" nowrap="nowrap">折扣后金额</td>
										<td nowrap="nowrap">产品备注</td>
										<td nowrap="nowrap">订单流水号</td>
										<td width="5%" align="center" style="cursor:pointer;" title="添加" onclick="getPePdModel();"><img src="${ctx}/styles/jxc/images/+.gif" style="vertical-align:text-bottom;"/></td>
									</tr>
										<html-el:hidden property="pd_ids" styleId="pd_ids" />
									<tr id="trHidden" style="display:none;">
										<td align="center">
											<html-el:hidden property="pd_id" styleId="pd_id" />
											<html-el:hidden property="md_name" styleId="md_name1" styleClass="md_name1" /> 
											<span id="md_name" class="md_name"></span><span id="span_1"></span>
										</td>
										<td align="center"><html-el:text property="good_count" styleId="good_count" value="1" size="4" maxlength="4" styleClass="good_count" /></td>
										<td align="center"><html-el:text property="good_unit" styleId="good_unit" value="台" maxlength="20" size="5" styleClass="" /></td>
										<td align="center"><html-el:text property="good_price" value="0" styleId="good_price"  maxlength="8" size="8" styleClass="" /></td>
										<td align="center">
											<html-el:text property="good_sum_price" styleId="good_sum_price" value="0" style="width:80px;" styleClass="webinput" readonly="true" />
											<html-el:hidden property="sum_price" styleId="sum_price" />
										</td>
											<td align="center"><html-el:text property="good_discount_price" value="0" styleId="good_discount_price" style="width:80px;"/>
											<html-el:hidden property="discount_price" styleId="discount_price" />
											</td>
											<td align="center">
										    <html-el:text property="good_discount" value="0" styleId="good_discount"  style="width:80px;" /></td>
											<td align="center"><html-el:text property="af_discount_good_price" styleId="af_discount_good_price" styleClass="webinput" style="width:80px;" />
											<html-el:hidden property="af_discount_price" styleId="af_discount_price" /></td>
										<td align="center"><html-el:text property="pd_remark" style="width:80%;" styleId="pd_remark" maxlength="100" styleClass="" /></td>
										<td align="center"><html-el:text property="pd_trade_index" style="width:80%;" styleId="pd_trade_index" maxlength="45" styleClass="pd_trade_index" /></td>
										<td align="center" title="删除"><img src="${ctx}/styles/jxc/images/x.gif" style="vertical-align:text-bottom;"/></td>
									</tr>
									<tbody id="tbodyContent"></tbody>
									<tr class="title_top_no_bc">
										<td>合计</td>
										<td><html-el:text property="dd_count_sum" styleId="dd_count_sum" value="0" style="width:50px;" styleClass="webinput" readonly="true" />
											<html-el:hidden property="order_num" styleId="order_num" />
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>
											<html-el:text property="dd_money_sum" styleId="dd_money_sum" value="0" style="width:100px;" styleClass="webinput" readonly="true" />
											<html-el:hidden property="money_sum" styleId="money_sum" />
										</td >
											<td colspan="2"><html-el:text property="dd_discount_sum" styleId="dd_discount_sum" styleClass="webinput" value="0" readonly="true" />
											<html-el:hidden property="discount_price_sum" styleId="discount_price_sum" /></td>
											<td>
											<html-el:text property="dd_af_discount_sum" styleId="dd_af_discount_sum" value="0" styleClass="webinput" readonly="true" />
											<html-el:hidden property="af_discount_price_sum" styleId="af_discount_price_sum" />
											</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
								</table>
							</td>
						</tr>
						<!-- 汇总信息 -->
						<tr>
							<td colspan="4" align="left" style="font-weight:bold;color:#74685F;border-bottom:1px solid #74685F;">汇总信息</td>
						</tr>
						<tr>
							<td class="title_item_no_bc">申请数量：</td>
							<td><html-el:text property="sqsl_span" styleId="sqsl_span" value="0" style="width:100px;" styleClass="webinput" readonly="true" /></td>
							<td class="title_item_no_bc">申请金额：</td>
							<td><html-el:text property="sqje_span" styleId="sqje_span" value="0" style="width:100px;" styleClass="webinput" readonly="true" /></td>
						</tr>
						<tr>
							<td class="title_item_no_bc">审核数量：</td>
							<td><html-el:text property="shsl_0" value="0" style="width:100px;" styleClass="webinput" readonly="true" /></td>
							<td class="title_item_no_bc">审核金额：</td>
							<td><html-el:text property="shje_0" value="0" style="width:100px;" styleClass="webinput" readonly="true" /></td>
						</tr>						
						
						<!-- 同步信息 -->
						<tr>
							<td colspan="4" align="left" style="font-weight:bold;color:#74685F;border-bottom:1px solid #74685F;">R3系统订单凭证</td>
						</tr>
						<tr>
							<td width="8%" align="left" class="title_item_no_bc">退货原因</td>
							<td width="12%" colspan="3">
								<html-el:select property="return_reason" styleId="return_reason">
									<html-el:option value="F01">客户退货</html-el:option>
									<html-el:option value="001">销售会谈</html-el:option>
									<html-el:option value="002">贸易展览会销售活动</html-el:option>
									<html-el:option value="003">电视商业</html-el:option>
									<html-el:option value="004">客户建议</html-el:option>
									<html-el:option value="005">报纸广告</html-el:option>
									<html-el:option value="006">极好的价格</html-el:option>
									<html-el:option value="007">快速交货</html-el:option>
									<html-el:option value="008">优良服务</html-el:option>
									<html-el:option value="100">价格差异：价格太高</html-el:option>
									<html-el:option value="101">质量低劣</html-el:option>
									<html-el:option value="102">转运中受损</html-el:option>
									<html-el:option value="103">数量不符</html-el:option>
									<html-el:option value="104">物料损坏</html-el:option>
									<html-el:option value="105">免费样本</html-el:option>
									<html-el:option value="200">价格差异：价格太低</html-el:option>
									<html-el:option value="EDI">贷项凭单处理</html-el:option>
									<html-el:option value="F02">客户补差</html-el:option>
								</html-el:select>
							</td>
						</tr>
						<tr>
							<td width="8%" align="left" class="title_item_no_bc">销售凭证类型</td>
							<td width="12%"><html-el:select property="doc_type" styleId="doc_type">
								<html-el:option value="ZFRE">ZFRE</html-el:option>
								</html-el:select>
								<span style="color:#ccc;" id="ZFRE_info">备注：退货订单！</span>
							</td>
							<td width="8%" align="left" class="title_item_no_bc">销售组织</td>
							<td width="12%"><input type="text" name="sales_org" id="sales_org" value="${sales_org}" class="webinput" readonly="readonly" /></td>
						</tr>
						<tr>
							<td width="8%" colspan="1" align="left" class="title_item_no_bc">分销渠道</td>
							<td width="12%"><input type="text" name="distr_chan" value="10" class="webinput" readonly="readonly" /></td>
							<td width="8%" align="left" class="title_item_no_bc">产品组</td>
							<td width="12%"><input type="text" name="division" value="10" class="webinput" readonly="readonly" /></td>
						</tr>
						<tr>
							<td width="8%" align="left" class="title_item_no_bc">售达方</td>
							<td width="12%">
								<c:if test="${empty agList}"><input type="text" name="ag" value="${ag}" class="webinput" readonly="readonly" /></c:if>
								<c:if test="${not empty agList}">
									<html-el:select property="ag">
										<c:forEach var="cur" items="${agList}" varStatus="vs">
											<html-el:option value="${cur.KUNN2}">${cur.KUNN2}</html-el:option>
										</c:forEach>
					            	</html-el:select>
					            </c:if>
							</td>
							<td width="8%" colspan="1" align="left" class="title_item_no_bc">出具发票方</td>
							<td width="12%">
								<c:if test="${empty reList}"><input type="text" name="re" value="${re}" class="webinput" readonly="readonly" /></c:if>
								<c:if test="${not empty reList}">
									<html-el:select property="re">
										<c:forEach var="cur" items="${reList}" varStatus="vs">
											<html-el:option value="${cur.KUNN2}">${cur.KUNN2}</html-el:option>
										</c:forEach>
				            		</html-el:select>
				            	</c:if>
				            </td>
						</tr>
						<tr>
							<td width="8%" colspan="1" align="left" class="title_item_no_bc">付款方</td>
							<td width="12%">
								<c:if test="${empty rgList}"><input type="text" name="rg" value="${rg}" class="webinput" readonly="readonly" /></c:if>
								<c:if test="${not empty rgList}">
									<html-el:select property="rg">
										<c:forEach var="cur" items="${rgList}" varStatus="vs">
											<html-el:option value="${cur.KUNN2}">${cur.KUNN2}</html-el:option>
										</c:forEach>
					            	</html-el:select>
					            </c:if>
				            </td>
							 <td width="8%" colspan="1" align="left" class="title_item_no_bc">送达方</td>
							<td width="12%">
								<c:if test="${empty weList}"><input type="text" name="we" value="${we}" class="webinput" readonly="readonly" /></c:if>
								<c:if test="${not empty weList}">
									<html-el:select property="we" styleId="we">
										<c:forEach var="cur" items="${weList}" varStatus="vs">
										<html-el:option value="${cur.KUNN2}">${cur.KUNN2}</html-el:option>
										</c:forEach>
					            	</html-el:select>
					            </c:if>
							</td>
						</tr>
						<tr>
							<td colspan="4">注：销售凭证信息系统自动从康佳R3系统实时抓取显示，没有查询到相关信息的字段是默认为康佳客户编码。</td>
						</tr>
				    </table>
			    </div>
			    
			    <!--<div id="tab2"></div>-->
				<!-- 附件 -->			    
			    <div id="tab3">
			    	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tableClass">
			    		<tr>
				        	<td class="title_item_no_bc" style="text-align:center;font-size:15px;font-weight:bold;color:#74685F">附件</td>
				        </tr>
				        <tr>
				        	<td align="center">
				        		<div style="width:90%;padding:5px 5px 0 5px;border-bottom:1px solid #74685F;display:block;text-align:left;font-size:14px;">
				        			<a href="javascript:addFile();"><span style="color:#74685F;font-weight:bold;">添加附件</span><img src="${ctx}/styles/jxc/images/+.gif" style="vertical-align:middle;"/></a>
				        		</div>
				        		<div style="width:90%;padding:5px 5px 0 5px;">
				        			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="navClass" style="margin:10px 0 10px 0;">
										<thead id="nav" style="width:99%;background:#abd589;">
								  			<tr>
									  			<th width="3%" nowrap="nowrap">序号</th>
									  			<th width="40%" nowrap="nowrap">附件</th>
									  			<th nowrap="nowrap">备注</th>
									  			<th width="3%" nowrap="nowrap">操作</th>
									  		</tr>
								  		</thead>
								  		<tbody id="fileTbody">
								  			<c:forEach items="${attachmentList}" var="cur" varStatus="vs">
								  				<tr id="picModelTr_${vs.count}">
													<td align="center">${vs.count}</td>
													<td align="left" nowrap="nowrap">
														<a href="${ctx}/${cur.save_path}">${cur.file_name}</a>
														<html-el:hidden property="_id" value="${cur.id}" />
														<html-el:hidden property="_link_id" value="${cur.link_id}" />
														<html-el:hidden property="_link_tab" value="${cur.link_tab}" />
														<html-el:hidden property="_file_name" value="${cur.file_name}" />
														<html-el:hidden property="_file_type" value="${cur.file_type}" />
														<html-el:hidden property="_file_size" value="${cur.file_size}" />
														<html-el:hidden property="_save_path" value="${cur.save_path}" />
														<html-el:hidden property="_save_name" value="${cur.save_name}" />
														<html-el:hidden property="_del_mark" value="${cur.del_mark}" />
													</td>
													<td align="center"><input type="text" name="_file_desc" id="_file_desc_${vs.count}" value="${cur.file_desc}" style="width:94%" /></td>
													<td align="center" nowrap="nowrap" style="cursor:pointer;" onclick="delFile($(this))"><img src="${ctx}/styles/jxc/images/x.gif" style="vertical-align:middle;" /></td>
											   </tr>
								  			</c:forEach>
								  		</tbody>
								  	</table>
				        		</div>
				        	</td>
				        </tr>
			    	</table>
			    </div> 
			    
			     <!-- 意见反馈 -->
				<div id="tab4">
			    	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tableClass">
			    		<tr>
				        	<td  class="title_item_no_bc" style="text-align:center;font-size:15px;font-weight:bold;color:#74685F">意见反馈</td>
				        </tr>
				        <tr>
				        	<td align="center">   
				        	<textarea name="content1" id="content1" rows="8" cols="65" style="resize:none;" <c:if test="${af.map.is_submit eq 1}"> disabled="disabled" </c:if> >${af.map.content1}</textarea>
				        	</td>
				        </tr>
				        <tr>
				        	<td align="center">   
				        	联系电话：
				        	<c:if test="${af.map.is_submit eq 1}"><html-el:text property="tel1" value="${af.map.tel1 }" styleId="tel1" maxlength="20" disabled="disabled" /></c:if>
				        	<c:if test="${af.map.is_submit ne 1}"><html-el:text property="tel1" value="${af.map.tel1 }" styleId="tel1" maxlength="20" /></c:if>
				        	<div id="content1_note"  style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/styles/images/tishi.gif" style="vertical-align:middle;" /></div>
				        	</td>
				        </tr>
			    	</table>
			    </div>   
			    
			</div>
			
			<div style="position:absolute;top:0;right:0;">
				<html-el:button property="btn_submit" styleId="btn_submit" value=" 提 交 " />
				<html-el:button property="btn_temp_save" styleId="btn_temp_save" value=" 保 存 " />
				<html-el:button property="btn_temp_save_add" styleId="btn_temp_save_add" value=" 保存并新建 " />
				<html-el:button property="btn_back" styleId="btn_back" value=" 返 回 " onclick="history.back();" />
			</div>
		</html-el:form>
	</div>
</div>

<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=jtop"></script> 
<script type="text/javascript" src="${ctx}/jquery-ui/ui/ui.core.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script> 
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script>

<script type="text/javascript">//<![CDATA[
var f = document.forms[0];
$(document).ready(function(){
// 	$("#return_reason").attr("dataType", "Require").attr("msg", "退货原因不能为空！");
	$("#tel1").attr("require", "false").attr("datatype","Custom").attr("regexp","(^(0[0-9]{2,3}-)?([2-9][0-9]{6,7})+(-[0-9]{1,4})?$)|(^((13[0-9]{9})|(14[0-9]{9})|(15[0-9]{9})|(18[0-9]{9}))$)").attr("msg","请正确填写联系电话，格式为“电话：0755-88888888”，“手机：13888888888”！");
 	$("#return_type").attr("dataType", "Require").attr("msg", "退货类型不能为空！");
	$("#return_type").change(function(){
		if($(this).val() == "5"){
			$("#red_return_remark").html("*");
		 	$("#return_type_remark").attr("dataType", "Require").attr("msg", "原因说明不能为空！");
		} else {
			$("#red_return_remark").html(" ");
			$("#return_type_remark").removeAttr("dataType");
		}
	});
	if($("#return_type").val() == 5){
		$("#red_return_remark").html("*");
		$("#return_type_remark").attr("dataType", "Require").attr("msg", "原因说明不能为空！");
	} else {
		$("#red_return_remark").html("");
		$("#return_type_remark").removeAttr("dataType");
	}

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
	
	$("#various3").fancybox({
	    'overlayOpacity':'0.6', 
		'width':'40%',   
		'height':'90%',
		'autoScale':false,
		'transitionIn':'none',
		'transitionOut':'none',
		'type':'iframe'
	});

	$("#content1").textbox({
		maxLength: 500,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/styles/images/tishi.gif" style="vertical-align:middle;" />';
			$("#content1_note").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#content1_note").slideUp("normal");
	});
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

	
	// 订单类型改变
	$(document).delegate("#process_id", "change", function(){
		$("#process_id_tr").hide();
		var val = $(this).find("option:selected").text();
		if(val.indexOf("统一") != -1)
			$("#process_id_tr").show();
	});
	
	//提交表单(验证价格是否为负和字段是否超过长度)
	$("#btn_submit").click(function(){
		var content1=$('#content1').val();
		if(content1.length>200){alert('最多只能输入200个字');return false;	}
		
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
	    
					
		if (Validator.Validate(this.form, 1)){
			if (confirm("确定提交表单？")) {
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
		var content1=$('#content1').val();
		if(content1.length>200){alert('最多只能输入200个字');return false;	}
		
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

		if (Validator.Validate(this.form, 1)){
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
		
		if (Validator.Validate(this.form, 1)){
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
			$("#trHidden").clone().appendTo($("#tbodyContent")).show();
	    	var lastTR = $("tr:last", "#tbodyContent");
	    	
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

			var af_discount_good_price = $("#af_discount_good_price", lastTR);//折扣后金额
			var af_discount_price = $("#af_discount_price", lastTR);//折扣后金额
			
			//var af_discount_price = $("#af_discount_good_price", lastTR);//折扣后金额
			
			var pd_remark = $("#pd_remark", lastTR);//产品备注
			var pd_trade_index = $("#pd_trade_index", lastTR);//产品备注
			var span_1 = $("#span_1", lastTR);//产品备注
			
			span_1.attr("id", "${cur.pd_name}");
			
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

			//合计折扣后金额
			var af_dis_price = parseFloat(good_sum_price.val())+parseFloat(good_discount_price.val())+parseFloat(good_discount.val())*parseFloat(good_sum_price.val())/100;
			af_discount_good_price.val(af_dis_price);
			af_discount_price.val(af_dis_price);

			pd_remark.val('${cur.pd_remark}');
			pd_trade_index.val('${cur.pd_trade_index}');

			calcPdNumAndPrice("tbodyContent");
			
			bindJhCountAndJhPrice(md_name,good_count, good_price, good_sum_price, sum_price, good_discount, good_discount_price, discount_price,af_discount_good_price,af_discount_price,af_discount_price,lastTR);
			$("td:last", lastTR).click(function (){
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
		window.parent.resizeFrameHeight('mainFrame', 3);
	});
	window.parent.resizeFrameHeight('mainFrame', 3);
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
	window.parent.resizeFrameHeight('mainFrame', 3);
}

/*处理返回值数据 ===start===*/
function resizeFrameHeight(offset, min_height) {
	// frame id can be found in page /konka-wd/WebContent/manager/admin/Frames2/indexFrame.jsp, and search 'iframe' to get.
	$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
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
	
		    //客户R3编码
		    var r3_code = $("#r3_code").val();
		    
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
				//var single_discount_price = $("#single_discount_price", lastTR);//单台折扣
				var discount_price = $("#discount_price", lastTR);//隐藏域折扣金额
	
				var af_discount_good_price = $("#af_discount_good_price", lastTR);//折扣后金额
				var af_discount_price = $("#af_discount_price", lastTR);//折扣后金额
				
				var pd_remark = $("#pd_remark", lastTR);//产品备注
				var pd_trade_index = $("#pd_trade_index", lastTR);//产品备注
				
				good_count.attr("dataType","Number").attr("msg","请填写数量,且必须为正整数！");
				good_price.attr("dataType","Double").attr("msg","非KF-22PB型号的商品，单价不能为负数！");
				good_discount.attr("dataType","Range").attr("min","-100").attr("max","0.00000001").attr("msg","请填写折扣率，且必须为负数！").attr("Require", "false");
				good_discount_price.attr("dataType","Range").attr("dataType","false").attr("min","-100000").attr("max","0").attr("msg","请填写折扣金额，且必须为负数！").attr("Require", "false");
				
				pd_id.val(pd_id_array[i]);//隐藏域选择的产品
		      	md_name.text(md_name_array[i]);
		      	md_name1.val(md_name_array[i]);
				
		    	// 取价格组维护的机型价格 同一时期内,某一客户只能属于某公司下的一个客户群组 
				// 有了这个客户群组,在同一时期内,为这个客户维护它的所有机型价格 
		    	$.ajax( {
		    		type : "POST",
		    		cache : false,
		    		url : "${ctx}/CsAjax.do",
		    		data : "method=getKonkaR3PdPrice2&pd_sn=" + md_name_array[i]+"&r3_code=" + r3_code,
		    		dataType: "json",
		    		error : function(data) {good_price.val("0");},
		    		success : function(data) {
		    			good_price.val(parseFloat(data[0].price).toFixed(2));
		    			good_discount.val(parseFloat(data[0].discount).toFixed(2));
		        	}
		    	});
		      	
				good_count.val("1");
				good_unit.val("台");
				
				span_1.attr("id",md_name_array[i]);//改变id
				
				good_sum_price.val("0");
				sum_price.val("0");
				
				good_discount.val("0");
				good_discount_price.val("0");
				af_discount_good_price.val("0");
				
				pd_remark.val("");
				pd_trade_index.val("");
	
				calcOneCountAndJhPrice(good_count, good_price, good_sum_price, sum_price);
				good_discount_price.val(parseFloat(good_sum_price.val())*parseFloat(good_discount.val())/100);//折扣金额
				discount_price.val(parseFloat(good_sum_price.val())*parseFloat(good_discount.val())/100);//隐藏域折扣金额
				calcPdNumAndPrice("tbodyContent");
				bindJhCountAndJhPrice(md_name1,good_count, good_price, good_sum_price, sum_price, good_discount, good_discount_price, discount_price,af_discount_good_price,af_discount_price,lastTR);
				
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
function bindJhCountAndJhPrice(md_name1,good_count, good_price, good_sum_price, sum_price, good_discount, good_discount_price, discount_price,af_discount_good_price,af_discount_price,lastTR) {
		
	good_count.keyup(function(){//数量
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
   		
   		var g_discount = parseFloat(good_discount_price.val());//总折扣金额
   		if(isNaN(g_discount)) g_discount = 0;
   		
   		good_sum_price.val((count * price).toFixed(2));//金额
   		sum_price.val((count * price).toFixed(2));//隐藏域金额
   		
   		var g_af_discount_good_price = parseFloat(good_sum_price.val()) + parseFloat(good_discount_price.val()) + parseFloat(good_sum_price.val()*good_discount.val())/100;
   		af_discount_good_price.val(parseFloat(g_af_discount_good_price).toFixed(2));
   		
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

   		good_sum_price.val((count * price).toFixed(2));//金额
   		sum_price.val((count * price).toFixed(2));//隐藏域金额
   		
   		var g_af_discount_good_price = parseFloat(good_sum_price.val()) + parseFloat(good_discount_price.val()) + parseFloat(good_sum_price.val()*good_discount.val())/100;
   		af_discount_good_price.val(parseFloat(g_af_discount_good_price).toFixed(2));
   		
		calcPdNumAndPrice("tbodyContent");
	});

	// 总折扣
	good_discount_price.blur(function(){
		var discount = (this.value); // 折扣总金额
		var md_name = md_name1.val();	
		
		var _reg = /^[\-]\d*?\.?\d*?$/;
		if (!_reg.test(discount)) {
			good_discount_price.val(0);
			discount = 0;
		}

		if(md_name=='KF-22PB'){
			good_discount_price.val(0);
			discount = 0;
   	   	 }
   		
   		good_discount_price.val(parseFloat(discount).toFixed(2));
   		discount_price.val(parseFloat(discount).toFixed(2));
   		var g_af_discount_good_price = parseFloat(good_sum_price.val()) + parseFloat(good_discount_price.val()) + parseFloat(good_sum_price.val()*good_discount.val())/100;
   		af_discount_good_price.val(parseFloat(g_af_discount_good_price).toFixed(2));
   		
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
		
	   	good_discount.val(discount.toFixed(2));
   		var g_af_discount_good_price = parseFloat(good_sum_price.val()) + parseFloat(good_discount_price.val()) + parseFloat(good_sum_price.val()*good_discount.val())/100;
   		af_discount_good_price.val(parseFloat(g_af_discount_good_price).toFixed(2));
   		
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
	var dd_af_discount_sum = 0;
	
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
	
	$("#good_discount_price", $("#" + tbody_id)).each(function(){
		var this_value = parseFloat($(this).val());
		if(isNaN(this_value)) this_value = 0;
		dd_discount_sum += this_value;
	});

	$("#af_discount_good_price",$("#" + tbody_id)).each(function(){
		var this_value = parseFloat($(this).val());
		if(isNaN(this_value)) this_value = 0;
		dd_af_discount_sum += this_value;
	})
	
	$("#dd_count_sum").val(dd_count_sum);
	$("#sqsl_span").val(dd_count_sum); //申请数量
	$("#dd_money_sum").val("￥" + (dd_money_sum.toFixed(2)));
	$("#sqje_span").val("￥" + (dd_money_sum.toFixed(2)));
	$("#dd_discount_sum").val("￥" + (parseFloat(dd_af_discount_sum)-parseFloat(dd_money_sum)).toFixed(2));
	$("#pay_money").val(parseFloat(dd_money_sum.toFixed(2)-dd_discount_sum.toFixed(2)).toFixed(2));
	$("#order_num").val(dd_count_sum);//隐藏域订单总数
	$("#money_sum").val(dd_money_sum.toFixed(2));//隐藏域订单金额
	$("#discount_price_sum").val("0");//隐藏域订单折扣金额
	$("#dd_af_discount_sum").val(dd_af_discount_sum.toFixed(2));//折扣后的金额
	
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

function openChild(){
   $.dialog({
		title:  "关联进货单",
		width:  770,
		height: 550,
		min: false,
		max: false,
        lock:   true ,
        drag: true,
		content:"url:${ctx}/customer/manager/JxcKonkaOrderRegister.do?method=chooseTradeIndex"
	});
}


function SetRTradeIndexDetails(r_trade_index){
	flag = true;
	$(".pd_trade_index").each(function(){//判断是否已经存在
		if($(this).val() == r_trade_index){
			alert("流水号已存在，不能重复添加!");
			flag = false;
			return false;
		}	
	});
	if(flag){
	$.ajax({
		type: "POST",
		url: "JxcKonkaOrderRegister.do",
		data: {method : "ajaxSetRTradeIndexDetails", "r_trade_index": r_trade_index},
		dataType: "json",
		cache:false,
		error: function(){alert("数据加载请求失败！");},
		success: function(ret){
			if(ret){
				if(null != ret.list){
				    var r3_code = $("#r3_code").val();
					var pd_ids_temp = "";
					pd_ids_temp = $("#pd_ids").val();
					for(var i = 0; i < ret.list.length; i++){
						var cur = ret.list[i];
					    
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
							//var single_discount_price = $("#single_discount_price", lastTR);//单台折扣
							var discount_price = $("#discount_price", lastTR);//隐藏域折扣金额

							var af_discount_good_price = $("#af_discount_good_price", lastTR);//折扣后金额
							var af_discount_price = $("#af_discount_price", lastTR);//折扣后金额
							
							var pd_remark = $("#pd_remark", lastTR);//产品备注
							var pd_trade_index = $("#pd_trade_index", lastTR);//产品备注
							
							good_count.attr("dataType","Number").attr("msg","请填写数量,且必须为正整数！");
							good_price.attr("dataType","Double").attr("msg","非KF-22PB型号的商品，单价不能为负数！");
							good_discount.attr("dataType","Range").attr("min","-100").attr("max","0.00000001").attr("msg","请填写折扣率，且必须为负数！").attr("Require", "false");
							good_discount_price.attr("dataType","Range").attr("dataType","false").attr("min","-100000").attr("max","0").attr("msg","请填写折扣金额，且必须为负数！").attr("Require", "false");
							
							pd_id.val(cur.pd_id);//隐藏域选择的产品
					      	md_name.text(cur.pd_name);
					      	md_name1.val(cur.pd_name);
					      	
					      	pd_ids_temp = pd_ids_temp+"," + cur.pd_id;
					    	
					    	good_price.val(cur.good_price);
					       
							good_count.val(cur.good_count);
							good_unit.val(cur.good_unit);
							
							//good_price.val("0");
							
							span_1.attr("id",cur.id);//改变id
							
							good_sum_price.val("0");
							sum_price.val("0");
							
							good_discount.val("0");
							good_discount_price.val("0");
							af_discount_good_price.val("0");
							
							pd_remark.val("");
							pd_trade_index.val(cur.pd_trade_index);

							calcOneCountAndJhPrice(good_count, good_price, good_sum_price, sum_price);
							good_discount_price.val(parseFloat(good_sum_price.val())*parseFloat(good_discount.val())/100);//折扣金额
							discount_price.val(parseFloat(good_sum_price.val())*parseFloat(good_discount.val())/100);//隐藏域折扣金额
							calcPdNumAndPrice("tbodyContent");
							bindJhCountAndJhPrice(md_name1,good_count, good_price, good_sum_price, sum_price, good_discount, good_discount_price, discount_price,af_discount_good_price,af_discount_price,lastTR);
							
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
						resizeFrameHeight(2);
					}
					$("#pd_ids").val(pd_ids_temp);
					//iframe高度自适应
					window.parent.resizeFrameHeight('mainFrame', 3);
				}
			}
		}
   }); 
	}
}


//]]></script>
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>