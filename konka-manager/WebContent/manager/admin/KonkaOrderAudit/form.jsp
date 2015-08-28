<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/css/tab.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/fancybox/fancybox.css" rel="stylesheet" type="text/css" />

<style type="text/css">
	.title_item,.title_item1,.title_item2 {
		background-color: #F5F4F4;
		font-weight: bold;
		text-align: right;
	}
	
	.but4 {
		text-align: cente;
		width: 67px;
	}
	
	.but5 {
		text-align: right;
		width: 67px;
	}
	
	.one {
		
	}
	
	.tabtt1 {
		
	}
</style>


</head>
<body>
<div class="oarcont" id="body_div">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/KonkaOrderAudit" method="post">
      <html-el:hidden property="order_id" styleId="order_id" value="${af.map.id}" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="flag" styleId="flag" value="${flag}" />
      <html-el:hidden property="audit_proc_cond_flag" styleId="audit_proc_cond_flag" value="${has_proc_cond}" />
      <html-el:hidden property="customer_type" styleId="customer_type" value="${customer_type}" />
      <html-el:hidden property="flag_Zb_role" styleId="flag_Zb_role" value="${flag_Zb_role}" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
	        <tr>
	          <th colspan="4"  style="font-weight:bold;color:#74685F;">订单基本信息</th>
	        </tr>
        
	        <tr>
	          <td width="15%" class="title_item">交易流水号：</td>
	          <td colspan="3">${fn:escapeXml(af.map.trade_index)}</td>
	        </tr>
	        <tr>
	          	<td class="title_item" width="15%">客户名称：</td>
				<td width="35%">${fn:escapeXml(af.map.user_shop_name)}</td>
				<td class="title_item" width="15%">售达方编码：</td>
				<td width="15%">${r3_code}</td>
	        </tr>
	        <tr>
	          <td class="title_item">提交日期：</td>
	          <td ><fmt:formatDate value="${af.map.order_date}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
	           <td class="title_item">创建日期：</td>
	          <td><fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
	        </tr>
	        <tr>
	         <td class="title_item" width="15%" >制单人：</td>
			<td width="35%" >${af.map.add_user_name}</td>
			<td class="title_item" width="15%">业务员：</td>
			<td width="35%">${ywy_user_name}</td> 
	        </tr>
	        <c:if test="${not empty af.map.freight}">
	        <tr>
	          <td class="title_item">运费：</td>
	          <td colspan="3"> ${fn:escapeXml(af.map.freight)} </td>
	        </tr>
	        </c:if>
	        <tr>
	          <td class="title_item">备注：</td>
	          <td colspan="3">${fn:escapeXml(af.map.remark)}</td>
	        </tr>
	        
	    
        <!-- 产品明细与产品周转天数tab-->
		<tr>
			<td colspan="4" align="left" style="font-weight:bold;color:#74685F;">
				<ul class="tabs">
				    <li><a href="#" name="tab5">产品明细</a></li>
				    <li><a href="#" name="tab6">产品/子仓周转</a></li>
				</ul>
			</td>
		</tr>   
       
        <tr class="one">
          <td colspan="4">
          	<div class="content">
	          	<div id="tab5">
	          	<div>(<font color="red">！</font><font color="blue" style="font-style: italic;">表示该条记录未选择工厂/仓位</font><font color="red" >&nbsp;&nbsp;无</font><font color="blue" style="font-style: italic;">表示该工厂/仓位没有该型号</font>)</div>
	          	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2" id="order_detail_grid">
						<tr class="tabtt1">
							<td width="12%" nowrap="nowrap" align="center">产品型号</td>
							<td width="8%" nowrap="nowrap" align="center">数量</td>
			                <td width="5%" nowrap="nowrap" align="center">单位</td>
			                <td width="8%" nowrap="nowrap" align="center">单价（元）</td>
			                <td width="8%" nowrap="nowrap" align="center">金额（元）</td>
			                <td width="10%" nowrap="nowrap" align="center">折扣金额-RB00</td>
			                <td width="10%" nowrap="nowrap" align="center">折扣(%)-K007</td>
			                <td width="8%" nowrap="nowrap" align="center">折后金额</td>
			                <td width="12%" nowrap="nowrap" align="center">工厂/仓位(<span id="btn_check" style="text-decoration:underline;cursor:pointer;color:blue;">库存检查</span>)</td>
			                <td nowrap="nowrap">当前<br />库存</td>
			                <td nowrap="nowrap" align="center">产品备注</td>
			                <td width="8%"  nowrap="nowrap" align="center">前${weeks}周<br />销售量</td>
			                <td width="5%" align="center">库存</td>
			                 <td width="5%" align="center">周转 天数</td>
			                <td width="5%" align="center">状态</td>
	      				</tr>
	      				<tbody id="details_body">
	      				<c:forEach items="${af.map.konkaOrderInfoDetailsList}" var="cur">
	        			<tr>
	            			<td align="center"><span class="pd_name_span" id="pd_name_span-${cur.id}">${fn:escapeXml(cur.pd_name)}</span>
	            			<html-el:hidden property="details_id" value="${cur.id}" /></td>
							<td align="center"><html-el:text property="good_count" styleId="good_count-${cur.id}" styleClass="good_count" style="width:90%" maxlength="5" value="${cur.good_count}" onfocus="javascript:setOnlyInt(this)"/></td>
							<td align="center">${fn:escapeXml(cur.good_unit)}</td>
							<td align="center"><html-el:text property="good_price" styleId="good_price-${cur.id}" styleClass="good_price" style="width:90%" maxlength="8" value="${cur.good_price}" /></td>
							<td align="center"><html-el:text property="good_sum_price" styleId="good_sum_price-${cur.id}" styleClass="good_sum_price" style="width:90%" maxlength="15" value="${cur.good_sum_price}" readonly="true"/></td>
							<!-- 折扣金额（元） -->
							<td align="center" style="font-weight:800;"><html-el:text property="good_discount_price" styleClass="good_discount_price_all_hid" style="width:90%" maxlength="10" value="${cur.good_discount_price}" styleId="good_discount_price_all_hid-${cur.id}" /></td>
							<!-- 折扣比例（%） -->
							<td align="center"><html-el:text property="good_discount" styleId="good_discount-${cur.id}" styleClass="good_discount" style="width:90%" maxlength="10" value="${cur.good_discount}" /></td>
							 <td align="center"><html-el:text property="af_discount_price" styleId="af_discount_price-${cur.id}" styleClass="af_discount_price" size="10" maxlength="15" value="${cur.good_sum_price+cur.good_discount_price+cur.good_sum_price*cur.good_discount/100}" readonly="true"/></td>
							
							<!-- 工厂仓位 -->
							<td class="checkstock">
							<c:if test="${!flag_Zb_role}">
								<select name="store_key" class="store_key" >
									<option value="">请选择...</option>
									<c:forEach items="${storeList}" var="cur_s">
										<c:set var="k" value="[${cur_s.fac_sn}-${cur_s.store_sn}]${cur_s.store_desc}" />
										<c:if test="${cur.store_key eq k}">
											<option value="${k}" selected="selected">${k}</option>
										</c:if>
										<c:if test="${cur.store_key ne k}">
											<option value="${k}">${k}</option>
										</c:if>
									</c:forEach>
								</select>
							</c:if>
							
							<c:if test="${flag_Zb_role}">
								<c:forEach items="${storeList}" var="cur_s">
									<c:set var="k" value="[${cur_s.fac_sn}-${cur_s.store_sn}]${cur_s.store_desc}" />
									<c:if test="${k eq cur.store_key}">${k}</c:if>
								</c:forEach>
							</c:if>
							
							<c:if test="${empty storeList}">
							 	<input  value="${cur.store_key}" readonly></input>
							</c:if>
							</td>
							
							<!-- 工厂仓位 -->
							<td>
							<span  id="${fn:escapeXml(cur.pd_name)}" >
							<c:if test="${!cur.map.stockIsok}">
								<font  color="red"> &nbsp;！&nbsp; </font> 
							</c:if>
	            			<c:if test="${not empty cur.map.stockNum}">
		            			<font  color="green">
		            		   		${cur.map.stockNum}
		            			</font>
	            			</c:if>
	            			</span>
							</td>
							
							<td>
							<c:if test="${not empty fn:escapeXml(cur.pd_remark)}">
								<html-el:text property="pd_remark" styleId="pd_remark-${cur.id}" value="${fn:escapeXml(cur.pd_remark)}" maxlength="50" />
							</c:if>
							<c:if test="${empty fn:escapeXml(cur.pd_remark)}">
								<html-el:text property="pd_remark" styleId="pd_remark-${cur.id}" value="" maxlength="50" />
							</c:if>
							</td>
							<td align="center">${fn:escapeXml(cur.sale_count)}</td>
							<td align="center">${fn:escapeXml(cur.store_num)}</td>
							
							<td align="center">
							<c:if test="${cur.sale_count ne 0 and cur.store_num ne 0}">
							<fmt:formatNumber pattern="#00.00" value="${ weeks * 7 /(cur.sale_count/cur.store_num)}"></fmt:formatNumber>
							</c:if>
							<c:if test="${cur.sale_count eq 0 or cur.store_num eq 0}">
							-
							</c:if>
							</td>
							<td align="center">
							<c:if test="${cur.sale_count - cur.store_num < cur.good_count}"><img src="${ctx}/images/yuan_red.png" width="16" height="16"/></c:if>
							<c:if test="${cur.sale_count - cur.store_num >= cur.good_count}"><img src="${ctx}/images/yuan_green.png" width="16" height="16"/></c:if>
							</td>
						</tr>
					    </c:forEach>
					    </tbody>
	       			</table>
		       			<div style="font-size:13px;font-weight:700;">
						          数量总计：<span id="order_num">${af.map.order_num}</span>&nbsp;&nbsp;&nbsp;&nbsp;
						          金额总计：<span id="money">${af.map.money}</span>元&nbsp;&nbsp;&nbsp;&nbsp;
						          折扣金额总计：<span id="good_discount_price">${af.map.good_discount_price}</span>元 &nbsp;&nbsp;&nbsp;&nbsp;
						          折后金额总计：<span id="af_discount_price_sum">${af.map.money + af.map.good_discount_price}</span>元 
		       			</div>
		       </div>
		       
		       <div id="tab6">
					<div id="loding_txt"></div>
			 		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
						<tr class="title_top">
							<td>分公司</td>
							<td>客户</td>
							<td>送达方</td>
							<td>仓库编码</td>
							<td>仓库名称</td>
							<td>机型</td>
							<td>历史周转天数</td>
							<td>初始库存</td>
							<td>进货量</td>
							<td>销售量</td>
							<td>当前库存量</td>
							<td>上次盘存时间</td>
							<td>最近更新时间</td>
						</tr>
					</table>
				</div>
				
			</div> 
            </td>
        </tr>
        
         <!-- 流程信息 -->
        <tr>
			<td colspan="4" align="left" style="font-weight:bold;color:#74685F;">流程信息</td>
		</tr> 
		
        <c:if test="${not empty af.map.konkaOrderInfoAuditList}">
	        <tr class="one">
	        	<td colspan="4"><strong>审核流程：</strong><br /><table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
	        			<tr class="tabtt1">
	        				<td width="5%" align="center">序号</td>
	        				<td width="15%" align="center">审核时间</td>
	        				<td width="15%" align="center">审核人</td>
	        				<td width="15%" align="center">角色</td>
	        				<td width="10%" align="center">审核结果</td>
	        				<td align="center">审核意见</td>
	        			</tr>
	        			<c:forEach items="${af.map.konkaOrderInfoAuditList}" var="cur1" varStatus="vs">
	        			<tr>
	        				<td align="center">${vs.count}</td>
	        				<td align="center"><fmt:formatDate value="${cur1.audit_datetime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	        				<td align="center">${fn:escapeXml(cur1.audit_user)}</td>
	        				<td align="center">
	        				<c:if test="${not empty cur1.map.role_name}">${fn:escapeXml(cur1.map.role_name)}</c:if>
	        				<c:if test="${empty cur1.map.role_name and cur1.audit_result eq -9}"><span style="color:orange;">客户(撤销订单)</span></c:if>
							<c:if test="${empty cur1.map.role_name and cur1.audit_result eq 10}"><span style="color:orange;">订单变更</span></c:if>
	        			</td>
	        				<td align="center"><c:choose>
	       						<c:when test="${cur1.audit_result eq 1}"><span style="color:green;">审核通过</span></c:when>
	       						<c:when test="${cur1.audit_result eq -1}"><span style="color:#F00;">驳回（至审核人）</span></c:when>
	       						<c:when test="${cur1.audit_result eq 0}"><span style="color:#00F;">驳回（至制单）</span></c:when>
	       						<c:when test="${cur1.audit_result eq -9}"><span style="color:#999;">（客户）撤销</span></c:when>
	       						<c:when test="${cur1.audit_result eq 10}"><span style="color:#999;">订单变更</span></c:when>
	        				</c:choose></td>
	        				<td><c:if test="${empty cur1.audit_comment }">无</c:if>
	                  			<c:if test="${not empty cur1.audit_comment }">${fn:escapeXml(cur1.audit_comment)}</c:if></td>
	                  	</tr>
	        			</c:forEach>
	        	    </table>
	            </td>
	        </tr>
        </c:if>
        
        <c:if test="${empty af.map.process_id}">
        	<tr class="one">
        		<td class="title_item2"><font color="red">*</font>订单流程：</td>
        		<td colspan="3">
        			<ul>
		          		<c:forEach var="cur" items="${processList}">
			          		<li>
				          		<label for="process_id_${cur.id}" style="cursor:pointer;" id="process_id_label_${cur.id}">
				          			<html-el:radio styleId="process_id_${cur.id}" property="process_id" styleClass="process_id" value="${cur.id}">&nbsp;
						          		<c:if test="${cur.add_dept_id eq 0}">★[统一流程] </c:if>
					          			<c:if test="${cur.add_dept_id ne 0}">[${cur.add_dept_name}]</c:if>
					          			${cur.process_desc}
				          			</html-el:radio>
			          			</label>
		          			</li>
		          		</c:forEach>
	          		</ul>
				</td>
        	</tr>
        	<tr class="one" style="display:none;" id="process_id_tr">
				<td class="title_item2">订单分类：</td>
				<td colspan="3">
				    <label for="process_state_1" style="cursor:pointer;"><html-el:radio property="process_state" styleId="process_state_1" styleClass="process_state" value="1" /> 一般订单</label>
					<span style="color:#999;margin-left:3em;">一般订单是指下单价格等于或高于分公司标准价格的订单</span><br />
					<label for="process_state_2" style="cursor:pointer;"><html-el:radio property="process_state" styleId="process_state_2" styleClass="process_state" value="2" /> 特殊订单</label>
					<span style="color:#999;margin-left:3em;">特殊订单是指下单价格低于分公司标准价格的订单</span></td>
			</tr>
        </c:if>
        
        <tr class="one">
          <td class="title_item2">审核结果：</td>
          <td colspan="3"><html-el:select property="audit_result" styleId="audit_result" style="width:120px;">
              <html-el:option value="1">审核通过</html-el:option>
              <html-el:option value="-1">驳回</html-el:option>
              <html-el:option value="0">重新制单</html-el:option>
            </html-el:select>&nbsp;&nbsp;<span style="color:blue;">审核流程中第一个审核人如果发现问题，可以线下电话通知客户，修复后再审核。</span></td>
        </tr>
        <tr class="one" id="audit_poss_tr" style="display:none;">
        	<td class="title_item2">驳回位置：</td>
        	<td colspan="3"><c:forEach var="cur" items="${nodeList}" varStatus="vs">
        			<label for="${cur.id}"><html-el:radio styleId="${cur.id}" property="node_id" disabled="${cur.id ge af.map.next_node_id}" value="${cur.id}">${cur.role_name}</html-el:radio></label> 
	        		<c:if test="${vs.last ne true}">
						--&gt;
					</c:if>
        	</c:forEach>
        	<c:if test="${empty nodeList}"><span style="color:#F00;">该订单暂未确定审核流程，只有确定了审核流程的订单方可进行驳回操作。</span></c:if></td>
        </tr>
        <tr class="one">
          <td class="title_item2" >财务与业务的意见：</td>
         <td colspan="3" >
			<html-el:radio styleId="is_need_to_manager_0" property="is_need_to_manager" styleClass="is_need_to_manager" value="0">一致</html-el:radio>&nbsp; &nbsp;
			<html-el:radio styleId="is_need_to_manager_1" property="is_need_to_manager" styleClass="is_need_to_manager" value="1">不一致</html-el:radio>
          </td>
          </tr>
        <tr class="one">
          <td class="title_item2" >审核意见：</td>
          <td colspan="3" >
          <html-el:textarea property="audit_comment" styleId="audit_comment" cols="80" rows="3" style="resize:none" />
         <span class="note"> 最多可输入200个字</span></td>
        </tr>
        <tr class="one">
          <td></td> 
          <td colspan="2">
              <input class="but4" type="button" name="Submit4" id="send" value="提交" style="padding-left: 30px"/>
             <input class="but5" type="button" name="Submit5" value="返回" style="padding-left: 24px" onclick="history.back();return false;" />
          </td>
          <td colspan="2"></td>
        </tr>
        <tr>
        <td colspan="4">
        <table width="100%" >
       		<c:if test="${not empty attachmentList}">
       		<tr><td colspan="4" align="left" >
				        			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="navClass" style="margin:10px 0 10px 0;">
										<thead id="nav" style="width:99%;background:#abd589;">
								  			<tr>
									  			<th width="3%" nowrap="nowrap">序号</th>
									  			<th width="40%" nowrap="nowrap">附件</th>
									  			<th nowrap="nowrap">备注</th>
									  		</tr>
								  		</thead>
								  		<tbody id="fileTbody">
								  			<c:forEach items="${attachmentList}" var="cur" varStatus="vs">
								  				<tr id="picModelTr_${vs.count}">
													<td align="center">${vs.count}</td>
													<td align="left" nowrap="nowrap">
														<a href="${ctx}/${cur.save_path}">${cur.file_name}</a>
														<html-el:hidden property="id_" value="${cur.id}" />
														<html-el:hidden property="link_id_" value="${cur.link_id}" />
														<html-el:hidden property="link_tab_" value="${cur.link_tab}" />
														<html-el:hidden property="file_name_" value="${cur.file_name}" />
														<html-el:hidden property="file_type_" value="${cur.file_type}" />
														<html-el:hidden property="file_size_" value="${cur.file_size}" />
														<html-el:hidden property="save_path_" value="${cur.save_path}" />
														<html-el:hidden property="save_name_" value="${cur.save_name}" />
														<html-el:hidden property="del_mark_" value="${cur.del_mark}" />
													</td>
													<td align="left">${cur.file_desc}</td>
											   </tr>
								  			</c:forEach>
								  		</tbody>
								  	</table>
			    	</td></tr>
				  	</c:if>
       		<!-- 下单人反馈意见以及回复 -->
       		<c:if test="${not empty af.map.show}">
       		<tr>
				<td colspan="4" align="left" style="font-weight:bold;color:#74685F;">下单人反馈意见和回复</td>
			</tr>
			<tr>
				<td class="title_item1">反馈内容：</td>
				<td>${af.map.content1}</td>
				<td class="title_item1">操作：</td>
				<td >
					<a id="various3" style="color: #08C;" href="${ctx}/manager/admin/KonkaOrderSearch.do?method=openiframe&t_id=${af.map.t_id}">查看回复</a>
				</td>
			</tr> 
			</c:if>
     				<!-- 汇总信息 -->
			<tr>
				<td colspan="4" align="left" style="font-weight:bold;color:#74685F;">汇总信息</td>
			</tr>
			<tr>
				<td class="title_item1">申请数量：</td>
				<td>${af.map.order_num}</td>
				<td class="title_item1">申请金额：</td>
				<td >￥${af.map.money}</td>
			</tr>
			<tr>
				<td class="title_item1">审核数量：</td>
				<td>${af.map.order_num}</td>
				<td class="title_item1">审核金额：</td>
				<td style="color:#CD0000;font-family:tahoma;">￥${af.map.money}</td>
			</tr>											
			<!-- 付款信息 -->
			<tr>
				<td colspan="4" align="left" style="font-weight:bold;color:#74685F;">付款信息</td>
			</tr>
			<tr>
				<td class="title_item1"><font color="red">*</font>货款支付方式：</td>
				<td>
					<c:choose>
						<c:when test="${af.map.pay_type eq 4}">现汇</c:when>
						<c:when test="${af.map.pay_type eq 5}">帐期</c:when>
						<c:when test="${af.map.pay_type eq 6}">承兑</c:when>
						<c:when test="${af.map.pay_type eq 45}">现汇、帐期</c:when>
						<c:when test="${af.map.pay_type eq 46}">现汇、承兑</c:when>
						<c:when test="${af.map.pay_type eq 56}">帐期、承兑</c:when>
						<c:when test="${af.map.pay_type eq 456}">现汇、帐期、承兑</c:when>
					</c:choose>
				</td>
				<td class="title_item1">可用额度：</td>
				<td><span id="can_use_money">-</span>
				<html-el:button property="btn_can_use_money" styleId="btn_can_use_money" value="点击查询 " style="margin-left:10px;" />
				</td>
				
         			<td></td>
       		</tr>
			<!-- 收货信息 --> 
			<tr>
				<td colspan="4" align="left" style="font-weight:bold;color:#74685F;">收货信息</td>   
			</tr>
			<tr>
				<td class="title_item1" width="15%"><font color="red">*</font>配送方式：</td>
				<td>
					<c:if test="${af.map.send_type eq 1}">自提</c:if>
					<c:if test="${af.map.send_type eq 2}">配送</c:if>
				</td>
				<td class="title_item1" width="15%">收货人姓名：</td>
         			<td width="35%">${af.map.user_name}</td>
			</tr>
			<tr>
				<td class="title_item1" width="15%">收货人固定电话：</td>
				<td width="35%">${af.map.user_tel}</td>
				<td class="title_item1" width="15%">收货人手机：</td>
				<td>${af.map.user_phone}</td>
			</tr>
			<tr>
				<td class="title_item1" width="15%">收货人所属地区：</td>
				<td colspan="3" width="85%">${fullName}</td>
			</tr>
			<tr>
				<td class="title_item1">收货人地址：</td>
				<td colspan="3">${af.map.user_addr}</td>
			</tr>
			<tr>
				<td class="title_item1">收货人备注：</td>
				<td colspan="3">${af.map.user_remark}</td>
			</tr>
			<!-- 同步信息 -->
			<tr>
				<td colspan="4" align="left" style="font-weight:bold;color:#74685F;">同步信息</td>
			</tr>
			<tr>
				<td width="8%" align="left" class="title_item1">销售凭证类型</td>
				<td width="12%">
				<html-el:select property="doc_type" value="${af.map.doc_type}">
				<html-el:option value="ZFOR">ZFOR</html-el:option>
				<html-el:option value="ZFGC">ZFGC</html-el:option>
				<html-el:option value="ZFCR">ZFCR</html-el:option>
				</html-el:select>
				</td>
				<td width="8%" align="left" class="title_item1">销售组织</td>
				<td width="12%">${af.map.sales_org}</td>
			</tr>
			<tr>
				<td width="8%" align="left" class="title_item1">分销渠道</td>
				<td width="12%">10</td>
				<td width="8%" align="left" class="title_item1">产品组</td>
				<td width="12%">10</td>
			</tr>
			<tr>
				<td width="8%" align="left" class="title_item1">售达方</td>
				<td width="12%">${af.map.ag}</td>
				<td width="8%"  align="left" class="title_item1">出具发票方</td>
				<td width="12%">${af.map.re}</td>
			</tr>
			<tr>
				<td width="8%" align="left" class="title_item1">付款方</td>
				<td width="12%">${af.map.rg}</td>
				<td width="8%"  align="left" class="title_item1">送达方</td>
				<td width="12%">${af.map.we}</td>
			</tr>
        </table>
        </td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
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
    
    $(".tabs li:first").attr("id","current"); // Activate first tab
    $(".content div[id^=tab]:first").fadeIn(); // Show first tab content
    $(".tabs a").click(function(e) {
        e.preventDefault();
        if ($(this).closest("li").attr("id") == "current"){ //detection for current tab
         	return       
        } else{             
	        $(".content div[id^=tab]").hide(); //Hide all content
	        $(".tabs li").attr("id",""); //Reset id's
	        $(this).parent().attr("id","current"); // Activate this
	        $('#' + $(this).attr('name')).fadeIn(); // Show content for current tab
        }
    });
    
	//tabs切换End
	var clicked = false;
	var tradeIndex = '${af.map.trade_index}';
	//触发产品周转查询 
	$(".tabs>li>a:[name ='tab6']").click(function(e) {
        if(!clicked){
        	if(tradeIndex){
        		//1.根据订单流水号到后台找到客户,订单机型,时间范围 
            	//2.调用JStocksStoreSummaryAction#getCustomerInventoryDetail
    			//3.前端解释json
       			  $("#loding_txt").html("<font color='red'>正在努力加载...</font>");
       			  $.ajax({
       				  type: "get",
       				  url: "${ctx}/manager/admin/JStocksStoreSummary.do?method=getCustomerInventoryDetail",
       				  data: {"tradeIndex" : tradeIndex, "timestamp" : new Date().getTime()},
       				  dataType: "json",
       				  sync: true, 
       				  error: function (xhr, ajaxOptions, thrownError) { alert("数据加载请求失败【" + xhr.statusText + "," + xhr.responseText + "," + xhr.status + "," + thrownError +"】！"); },
       				  success: function(result) {
	       					var html ="";
	       					if(result.length>0){
	       						for(var i=0; i<result.length; i++) 
	           					{ 
	           						html +="<tr>";
	           						
	           						html += "<td>"+result[i].fgs+"</td>";
	           						html += "<td>"+result[i].r3code+"</td>";
	           						html += "<td>"+result[i].sdf+"</td>";
	           						html += "<td>"+result[i].inventoryCode+"</td>";
	           						html += "<td>"+result[i].inventoryName+"</td>";
	           						html += "<td>"+result[i].model_code+"</td>";
	           						html += "<td>"+result[i].zzts+"</td>";
	           						
	           						html += "<td>"+result[i].initnum+"</td>";
	           						html += "<td>"+result[i].innum+"</td>";
	           						html += "<td>"+result[i].salenum+"</td>";
	           						html += "<td>"+result[i].xynum+"</td>";
	           						html += "<td>"+result[i].pc_date+"</td>";
	           						html += "<td>"+result[i].sync_date+"</td>";
	           						html +="</tr>";
	           					} 
	       						$("#loding_txt").hide();
	       						$("#tab6 table tr:last").after(html);
	       					}else{
	       						$("#loding_txt").html("<font color='red'>暂时没法查询到库存信息,可能客户库存没有初始化.</font>");
	       					}
       				  }
       			  }); 
       			clicked = true ;
    		}
        }
    });
    
	
	//意见	
	$(".is_need_to_manager").attr("disabled","true");
	<c:if test="${empty af.map.is_need_to_manager}">
	$("#is_need_to_manager_0").attr("checked","true");
	</c:if>
	<c:if test="${flag_cw_yw_role}">
	$(".is_need_to_manager").removeAttr("disabled");
	</c:if>
	// 将分类默认不选择
	$(".process_state").removeAttr("checked");
	$("#audit_comment" ).attr({"dataType":"Limit","max":"250","msg":"摘要内容的字数应少于200个字！"});
	$(".good_count").attr("dataType", "Require").attr("msg", "请填写数量，且必须为正数！");
	//$(".good_price").attr("dataType", "Require").attr("msg", "请填写单价，且必须为正数！");
	$(".good_discount").attr("dataType", "Require").attr("msg", "请填写单台折扣，且必须为正数！");
	$(".good_discount_price").attr("dataType", "Require").attr("msg", "请填写单台折扣额，且必须为正数！");
	<c:if test="${empty af.map.process_id}">
		$("#process_id").attr("dataType", "Require").attr("msg", "请选择订单类型！");
	</c:if>

	$("#audit_result").change(function(){
		var audit_result = $("#audit_result").val();
		if(audit_result == 1){
			$('input:radio[name="node_id"]').attr("checked", false);
		}
	});

	$("#various3").fancybox({
	    'overlayOpacity':'0.6', 
		'width':'40%',   
		'height':'90%',  
		'autoScale':false,
		'transitionIn':'none',
		'transitionOut':'none',
		'type':'iframe'
	}); 
	
	//自动选择工厂仓位,某一订单行确认后,其它未填写的自动赋值
	$(".store_key").change(function(){
			//获取列表行的指定列
			var Inputdata = $(this).val();
			if(null!=Inputdata && Inputdata!=""){
				$(".store_key").each(function(){
					var optionVal =$(this).val();
					if(optionVal ==null || optionVal==""){
						$(this).val(Inputdata).attr("selected",true);
					}
					
				 });
			}
	});
	
	
	var f = document.forms[0];
	$("#send").click(function(){
		var node_id = $('input:radio[name="node_id"]:checked').val();
		var audit_result = $("#audit_result").val();
		var html = $("#show_tip").html();
		
		if(audit_result == -1 && node_id == null){
			alert("请选择驳回位置，或者如果您是第一个审核人，可以线下电话通知客户，无需驳回操作！");
			return false;
		}
		
		if(audit_result == 1){
			$('input:radio[name="node_id"]').removeAttr("checked");
		}
		
		var _process_id = $('input:radio:checked').val();
		var label = $("#process_id_label_" + _process_id).html();
		
		<c:if test="${empty af.map.process_id}">
			 // 判断是否选择了统一流程，如果是则需要选择订单分类
		    $(".process_state").removeAttr("dataType");
			
			if(null == label){
				$("input[type='radio'][name='process_id']").eq(0).attr("dataType", "Group").attr("msg", "请选择一种订单流程！");
			}else{
				if(label.indexOf("统一") > -1){
					$("input[type='radio'][name='process_state']").eq(0).attr("dataType", "Group").attr("msg", "请选择一种订单分类！");
				}
			}
	    </c:if>
	    
	    
		if(Validator.Validate(f, 1)){
			f.submit();	
		}	
		
	});
	
	 // 点击查询额度
	  $(document).delegate("#btn_can_use_money", "click", function(){
		  $("#can_use_money").html("<img src='${ctx}/styles/images/loading.gif' />");
		  $.ajax({
			  type: "POST",
			  url: "${ctx}/manager/admin/KonkaOrderSearch.do?method=getCustomerCreditByR3CodeForAjax",
			  data: {"r3_code" : '${r3_code}', "timestamp" : new Date().getTime()},
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
	 
	 
	 

	$(".good_price").bind("input propertychange", function(){
		var _reg = /^[\+]?\d*?\.?\d*?$/;

		
		var id = $(this).attr("id").split("-")[1];
		var $good_count = $("#good_count-" + id);
		var $good_price = $("#good_price-" + id);
		var $good_sum_price = $("#good_sum_price-" + id);
		var $good_discount = $("#good_discount-" + id);
		var $good_discount_price_all_hid = $("#good_discount_price_all_hid-" + id);
		var $af_discount_price = $("#af_discount_price-" + id);
		var pd_name = $("#pd_name_span-"+id).text();

		if(pd_name=='KF-22PB'|| pd_name=='KF-22XD' || pd_name=='KF-22KK'){
			//	$(this).val(1);
	   	   	 }else{
	   	   	if (!_reg.test($(this).val())) {
				$(this).val(0);
			} }
		
		//动态计算合计后的金额
	    $af_discount_price.val(parseFloat(parseFloat($good_price.val()*$good_count.val()*$good_discount.val()/100)+ parseFloat($good_discount_price_all_hid.val())+parseFloat($good_count.val())*parseFloat($good_price.val())).toFixed(2));
	    
		calcOneCountAndJhPrice($good_count,$good_price,$good_sum_price);

		calcPdNumAndPrice("details_body");
	});

	$(".good_count").bind("input propertychange", function(){
		var _reg = /^[\+]?\d*?\.?\d*?$/;

		var id = $(this).attr("id").split("-")[1];
		var $good_count = $("#good_count-" + id);
		var $good_price = $("#good_price-" + id);
		var $good_sum_price = $("#good_sum_price-" + id);
		var pd_name = $("#pd_name_span-"+id).text();
		var $af_discount_price = $("#af_discount_price-" + id);
		var $good_discount_price_all_hid = $("#good_discount_price_all_hid-" + id);
		var $good_discount = $("#good_discount-" + id);
		
		if(pd_name=='KF-22PB'|| pd_name=='KF-22XD' || pd_name=='KF-22KK'){
		//	$(this).val(1);
   	   	 }else{
   	   	if (!_reg.test($(this).val())) {
			$(this).val(0);
		} }
		
	    $af_discount_price.val(parseFloat(parseFloat($good_price.val()*$good_count.val()*$good_discount.val()/100)+parseFloat($good_discount_price_all_hid.val())+parseFloat($good_count.val())*parseFloat($good_price.val())).toFixed(2));
		
		calcOneCountAndJhPrice($good_count,$good_price,$good_sum_price);

		calcPdNumAndPrice();
	});

	$(".good_discount").blur("input propertychange", function(){//折扣
		var discount = (this.value);
		var _reg = /^[\-]\d*?\.?\d*?$/;
		if (!_reg.test(discount)) {
			$(this).val(0);
			discount = 0;
		}
		discount = parseFloat(this.value);
		if(isNaN(discount)) discount = 0;
		if(discount > 100) {
			discount = 0;
			this.value = 0;
		}

		var id = $(this).attr("id").split("-")[1];
		var $good_sum_price = $("#good_sum_price-" + id);
		var pd_name = $("#pd_name_span-"+id).text();
		var $af_discount_price = $("#af_discount_price-" + id);
		var $good_discount_price_all_hid = $("#good_discount_price_all_hid-" + id);
		var $good_discount = $("#good_discount-" + id);

		if(pd_name=='KF-22PB' || pd_name=='KF-22XD' || pd_name=='KF-22KK'){
			$(this).val(0.00);
   	   	 }

		$af_discount_price.val(parseFloat(parseFloat($good_sum_price.val() * $good_discount.val()/100)+parseFloat($good_discount_price_all_hid.val())+parseFloat($good_sum_price.val())).toFixed(2));
   		
   		calcPdNumAndPrice();
	});

	$(".good_discount_price_all_hid").blur("input propertychange", function(){ // 折扣金额
		var discount = (this.value);
		var _reg = /^[\-]\d*?\.?\d*?$/;
		if (!_reg.test(discount)) {
			$(this).val(0);
			discount = 0;
		}

		var id = $(this).attr("id").split("-")[1];
		var $good_sum_price = $("#good_sum_price-" + id);
		var pd_name = $("#pd_name_span-"+id).text();
		var $af_discount_price = $("#af_discount_price-" + id);
		var $good_discount_price_all_hid = $("#good_discount_price_all_hid-" + id);
		var $good_discount = $("#good_discount-" + id);

		if(pd_name=='KF-22PB' || pd_name=='KF-22XD' || pd_name=='KF-22KK'){
			$(this).val(0.00);
   	   	 }
		$af_discount_price.val(parseFloat(parseFloat($good_sum_price.val()* $good_discount.val()/100)+parseFloat($good_discount_price_all_hid.val())+parseFloat($good_sum_price.val())).toFixed(2));
   		
   		calcPdNumAndPrice();
	});
	
	$("#audit_result").change(function(){
		if($(this).val() == -1){
			$("#audit_poss_tr").show();
		}else{
			$("#audit_poss_tr").hide();
		}
		//iframe高度自适应
		window.parent.resizeFrameHeight('mainFrame', 3);
	});
	
	// 订单类型改变
	<c:if test="${empty af.map.process_id}">
		$(document).delegate(".process_id", "click", function(){
			$("#process_id_tr").hide();
			
			var label = $("#process_id_label_" + $(this).val()).html();
			if(label.indexOf("统一") > -1) $("#process_id_tr").show();
			
			//iframe高度自适应
			window.parent.resizeFrameHeight('mainFrame', 3);
		});
	</c:if>
});

//计算 单个的金额
function calcOneCountAndJhPrice(good_count, good_price, good_sum_price) {
	var good_count = parseFloat(good_count.val());//数量
   	if(isNaN(good_count)) good_count = 1;
   	var good_price =  parseFloat(good_price.val());//单价
   	if(isNaN(good_price)) good_price = 0;
   	
   	good_sum_price.val((good_count * good_price).toFixed(2));//金额
}

//计算 合计的 数量、 金额和折扣金额
function calcPdNumAndPrice() {
	var dd_count_sum = 0;
	var dd_money_sum = 0;
	var dd_discount_sum = 0;
	var dd_af_discount_sum = 0;

	$(".good_count").each(function(){
		var this_value = parseFloat($(this).val());
		if(isNaN(this_value)) this_value = 0;
		dd_count_sum += this_value;
	});

	$(".good_sum_price").each(function(){
		var this_value = parseFloat($(this).val());
		if(isNaN(this_value)) this_value = 0;
		dd_money_sum += this_value;
	});
	
	//折扣后总金额
	$(".af_discount_price").each(function(){
		var this_value = parseFloat($(this).val());
		if(isNaN(this_value)) this_value = 0;
		dd_af_discount_sum += this_value;
	});
	
	$("#order_num").text(dd_count_sum);//订单总数
	$("#money").text(dd_money_sum.toFixed(2));//订单金额
	$("#good_discount_price").text((parseFloat(dd_af_discount_sum) - (parseFloat(dd_money_sum))).toFixed(2));//订单折扣金额
	$("#af_discount_price_sum").text(parseFloat(dd_af_discount_sum).toFixed(2));//折后金额
	
}

$("#btn_check").click(function(){
	$(".pd_name_span").each(function(){
		var pd_name=$.trim($(this).text());
		var stock_key=$.trim($(this).parent().parent().children(".checkstock").children().first().val());
		var stock_old=$("#"+pd_name).text();
 	if(null == stock_key || "" == stock_key){
 		$("#"+pd_name).text(" ！ ").css("color","red");
 	}else{
		$.ajax({
			type: "POST",
			url: "${ctx}/manager/admin/KonkaOrderAudit.do?method=checkStockForAjax",
			data: {"pd_name" :pd_name,"stock_key":stock_key},
			dataType: "json",
			sync: true, 
			error: function (xhr, ajaxOptions, thrownError) {
				alert("数据加载请求失败【" + xhr.statusText + "," + xhr.responseText + "," + xhr.status + "," + thrownError +"】！"); 
				},
			success: function(result) {
				if(result == null){
					$("#"+pd_name).text("无").css("color","red");
					return;
				}else{
					$("#"+pd_name).text(result.lamount).css("color","green");
			}
				}
		}); /// ajax end 
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
		if(obj.value.length == 0 || isNaN(obj.value) || obj.value == 0) obj.value = "0";
	});
}
//]]></script>

<jsp:include page="/__analytics.jsp" />
</body>
</html>
