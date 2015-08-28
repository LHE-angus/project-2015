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
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
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
		text-align: center;
		width: 67px;
	}
	
	.but5 {
		text-align: center;
		width: 67px;
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
      <ul id="tabs">
	    <li><a href="#" name="tab1">退货信息</a></li>
	    <li><a href="#" name="tab3">附件</a></li>
	    <li><a href="#" name="tab4">意见反馈<c:if test="${not empty af.map.content1}"><font color="red">*</font></c:if></a></li>
	  </ul>
	<div id="content" > 
	    <div id="tab1" >
    	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tableClass">
	        <tr>
				<td colspan="4" align="left" style="font-weight:bold;color:#74685F;border-bottom:1px solid #74685F;">订单基本信息</td>
			</tr>
		
	        <tr>
				<td class="title_item" width="15%"><font color="red">*</font>退货订单流水号：</td>
				<td colspan="3"><span>NO.<font color="red"></font><font color="red">${fn:escapeXml(af.map.trade_index)}</font></span></td>
			</tr>
			<tr>
				<td class="title_item" width="15%">客户名称：</td>
				<td >${fn:escapeXml(af.map.user_shop_name)}</td>
				<td class="title_item" width="15%">售达方编码：</td>
				<td>${r3_code}</td>
			</tr>
			<tr>
				<td class="title_item" width="15%"><font color="red">*</font>退货类型：</td>
				<td colspan="3">
					<c:choose>
						<c:when test="${af.map.return_type eq '1'}">滞销退货</c:when>
						<c:when test="${af.map.return_type eq '2'}">残次品退货</c:when>
						<c:when test="${af.map.return_type eq '3'}">客户拒</c:when>
						<c:when test="${af.map.return_type eq '4'}">异型换机</c:when>
						<c:when test="${af.map.return_type eq '5'}">其他原因</c:when>
					</c:choose>
				</td> 
			</tr>
			<tr>
				<td class="title_item" width="15%"><font color="red" id="red_return_remark"></font>原因说明：</td>
				<td colspan="3">
					${fn:escapeXml(af.map.return_type_remark)}
				</td>
			</tr>
			<!-- 常规信息 -->
			<tr>
				<td colspan="4" align="left" style="font-weight:bold;color:#74685F;border-bottom:1px solid #74685F;">常规信息</td>
			</tr>
			<tr>
				<td class="title_item">提交日期：</td>
				<td><fmt:formatDate value="${af.map.order_date}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
				<td class="title_item">创建日期：</td>
				<td><fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
			</tr>
			<tr>
				<td class="title_item">订单状态：</td>
				<td>
					<c:choose>
						<c:when test="${af.map.audit_state eq '0'}">制单</c:when>
						<c:when test="${af.map.audit_state eq '1'}">审核中</c:when>
						<c:when test="${af.map.audit_state eq '2'}">审核未通过</c:when>
						<c:when test="${af.map.audit_state eq '3'}">审核通过</c:when>
						<c:when test="${af.map.audit_state eq '4'}">已作废</c:when>
					</c:choose>
				</td>
				<td class="title_item"></td>
				<td></td>
			</tr>
			<tr>
				<td class="title_item">制单人：</td>
				<td>${fn:escapeXml(af.map.add_user_name)}</td>
				<td class="title_item">业务员：</td>
				<td>${fn:escapeXml(ywy_user_name)}</td>
			</tr>
			<tr>
				<td class="title_item">采购订单编号：</td>
				<td>${fn:escapeXml(af.map.third_cg_order_num)}</td>
				<td class="title_item">采购订单日期：</td>
				<td><fmt:formatDate value="${af.map.cg_order_date}" pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<td class="title_item">备注：</td>
				<td colspan="3">${fn:escapeXml(af.map.remark)}
					<div id="remark_note"  style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/styles/images/tishi.gif" style="vertical-align:middle;" /></div>
				</td>
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
        <tr>
          <td colspan="4">
          <div class="content">
	          	<div id="tab5">
		          <table style="overflow:auto;" width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
							<tr class="tabtt1">
								<td style="width:80px;">型号</td>
								<td style="width:80px;">数量</td>
				                <td style="width:80px;">单位</td>
				                <td style="width:80px;">单价（元）</td>
				                <td style="width:80px;">金额（元）</td>
				                <td style="width:80px;">折扣金额-RB00</td>
				                <td style="width:80px;">折扣(%)-K007</td>
				                <td style="width:80px;">折后金额</td>
				                <td style="width:80px;">工厂/仓位</td>
				                <td style="width:80px;">产品备注</td>
				                <td style="width:80px;">流水号</td>
		      				</tr>
		      				<tbody id="details_body">
		      				<c:forEach items="${af.map.konkaOrderInfoDetailsList}" var="cur">
		        			<tr>
		            			<td align="center" style="width:80px;"><span id="pd_name_span-${cur.id}">${fn:escapeXml(cur.pd_name)}</span><html-el:hidden property="details_id" value="${cur.id}" /></td>
								<td align="center" style="width:80px;"><html-el:text property="good_count" styleId="good_count-${cur.id}" styleClass="good_count" style="width:90%" maxlength="5" value="${cur.good_count}" onfocus="javascript:setOnlyInt(this)"/></td>
								<td align="center" style="width:80px;">${fn:escapeXml(cur.good_unit)}</td>
								<td align="center" style="width:80px;"><html-el:text property="good_price" styleId="good_price-${cur.id}" styleClass="good_price" style="width:90%" maxlength="8" value="${cur.good_price}" /></td>
								<td align="center" style="width:80px;"><html-el:text property="good_sum_price" styleId="good_sum_price-${cur.id}" styleClass="good_sum_price" style="width:90%" maxlength="10" value="${cur.good_sum_price}" readonly="true"/></td>
									<!-- 折扣金额（元） -->
								<td align="center" style="font-weight:800;"><html-el:text property="good_discount_price"  style="width:60px;"  styleClass="good_discount_price_all_hid"  maxlength="10" value="${cur.good_discount_price}" styleId="good_discount_price_all_hid-${cur.id}" /></td>
								<!-- 折扣比例（%） -->
								<td align="center" style="width:80px;"><html-el:text property="good_discount" styleId="good_discount-${cur.id}"  style="width:60px;"  styleClass="good_discount"  maxlength="10" value="${cur.good_discount}" /></td>
								 <td align="center" style="width:80px;"><html-el:text property="af_discount_price" style="width:80px;" styleId="af_discount_price-${cur.id}" styleClass="af_discount_price" size="10" maxlength="15" value="${cur.good_sum_price+cur.good_discount_price+cur.good_sum_price*cur.good_discount/100}" readonly="true"/></td>
								
								<td>
									<c:if test="${!flag_Zb_role}">
										 <select name="store_key" class="store_key">
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
								</td>
								
								<td style="width:80px;"><html-el:text property="pd_remark" style="width:80px;" value="${cur.pd_remark}" maxlength="100"/></td>
								<td style="width:80px;"> <html-el:text property="pd_trade_index" style="width:80px;" value="${cur.pd_trade_index}" maxlength="100" /></td> 
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
        
        
      	<tr>
			<td colspan="4" align="left" style="font-weight:bold;color:#74685F;border-bottom:1px solid #74685F;">订单流程</td>
		</tr>
        <c:if test="${not empty af.map.konkaOrderInfoAuditList}">
        <tr>
        	<td colspan="4"><strong>审核流程：</strong><br />
        	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2" style="overflow-x:auto;">
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
        				<td align="center">${fn:escapeXml(cur1.map.role_name)}</td>
        				<td align="center"><c:choose>
       						<c:when test="${cur1.audit_result eq 1}"><span style="color:green;">审核通过</span></c:when>
       						<c:when test="${cur1.audit_result eq -1}"><span style="color:#F00;">驳回（至审核人）</span></c:when>
       						<c:when test="${cur1.audit_result eq 0}"><span style="color:#00F;">驳回（至制单）</span></c:when>
       						<c:when test="${cur1.audit_result eq -9}"><span style="color:#999;">（客户）撤销</span></c:when>
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
        	<tr>
        		<td class="title_item"><font color="red">*</font>订单流程：</td>
        		<td  colspan="3">
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
        </c:if>
        <tr>
          <td class="title_item">审核结果：</td>
          <td colspan="3">
          	<html-el:select property="audit_result" styleId="audit_result" style="width:120px;">
              <html-el:option value="1">审核通过</html-el:option>
              <html-el:option value="-1">驳回</html-el:option>
              <html-el:option value="0">重新制单</html-el:option>
            </html-el:select>&nbsp;&nbsp;<span style="color:blue;">审核流程中第一个审核人如果发现问题，可以线下电话通知客户，修复后再审核。</span></td>
        </tr>
        <tr id="audit_poss_tr" style="display:none;">
        	<td class="title_item">驳回位置：</td>
        	<td colspan="3"><c:forEach var="cur" items="${nodeList}" varStatus="vs">
        			<label for="${cur.id}"><html-el:radio styleId="${cur.id}" property="node_id" disabled="${cur.id ge af.map.next_node_id}" value="${cur.id}">${cur.role_name}</html-el:radio></label> 
	        		<c:if test="${vs.last ne true}">
						--&gt;
					</c:if>
        	</c:forEach>
        	<c:if test="${empty nodeList}"><span style="color:#F00;">该订单暂未确定审核流程，只有确定了审核流程的订单方可进行驳回操作。</span></c:if></td>
        </tr>
        <tr>
          <td class="title_item">审核意见：</td>
          <td colspan="3"><html-el:textarea property="audit_comment" styleId="audit_comment" cols="80" rows="3" style="resize:none" />
        <span class="note"> 最多可输入200个字</span></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="3"><label>
              <input class="but4" type="button" name="Submit4" id="send" value="提交" />
              <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
            </label></td>
        </tr>
        
        <!-- 汇总信息 -->
		<tr>
			<td colspan="4" align="left" style="font-weight:bold;color:#74685F;border-bottom:1px solid #74685F;">汇总信息</td>
		</tr>
		<tr>
			<td class="title_item">申请数量：</td>
			<td><span id="order_num">${af.map.order_num}</span></td>
			<td class="title_item">申请金额：</td>
			<td><span id="money">￥${af.map.money}</span></td>
		</tr>
		<tr>
			<td class="title_item">审核数量：</td>
			<td>${af.map.order_num}</td>
			<td class="title_item">审核金额：</td>
			<td>￥${af.map.money}</td>
		</tr>						
		
		<!-- 同步信息 -->
		<tr>
			<td colspan="4" align="left" style="font-weight:bold;color:#74685F;border-bottom:1px solid #74685F;">R3系统订单凭证</td>
		</tr>
		<tr>
			<td width="8%" align="left" class="title_item">退货原因：</td>
			<td width="12%" colspan="3">
				<c:choose>
					<c:when test="${af.map.return_reason eq '001'}">销售会谈</c:when>
					<c:when test="${af.map.return_reason eq '002'}">贸易展览会销售活动</c:when>
					<c:when test="${af.map.return_reason eq '003'}">电视商业</c:when>
					<c:when test="${af.map.return_reason eq '004'}">客户建议</c:when>
					<c:when test="${af.map.return_reason eq '005'}">报纸广告</c:when>
					<c:when test="${af.map.return_reason eq '006'}">极好的价格</c:when>
					<c:when test="${af.map.return_reason eq '007'}">快速交货</c:when>
					<c:when test="${af.map.return_reason eq '008'}">优良服务</c:when>
					<c:when test="${af.map.return_reason eq '100'}">价格差异：价格太高</c:when>
					<c:when test="${af.map.return_reason eq '101'}">质量低劣</c:when>
					<c:when test="${af.map.return_reason eq '102'}">转运中受损</c:when>
					<c:when test="${af.map.return_reason eq '103'}">数量不符</c:when>
					<c:when test="${af.map.return_reason eq '104'}">物料损坏</c:when>
					<c:when test="${af.map.return_reason eq '105'}">免费样本</c:when>
					<c:when test="${af.map.return_reason eq '200'}">价格差异：价格太低</c:when>
					<c:when test="${af.map.return_reason eq 'EDI'}">贷项凭单处理</c:when>
					<c:when test="${af.map.return_reason eq 'F01'}">客户退货</c:when>
					<c:when test="${af.map.return_reason eq 'F02'}">客户补差</c:when>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td width="8%" align="left" class="title_item">销售凭证类型：</td>
			<td width="12%">${af.map.doc_type}
				<span style="color:#ccc;" id="ZFRE_info">备注：退货订单！</span>
			</td>
			<td width="8%" align="left" class="title_item">销售组织：</td>
			<td width="12%">${af.map.sales_org}</td>
		</tr>
		<tr>
			<td width="8%" colspan="1" align="left" class="title_item">分销渠道：</td>
			<td width="12%">10</td>
			<td width="8%" align="left" class="title_item">产品组：</td>
			<td width="12%">10</td>
		</tr>
		<tr>
			<td width="8%" align="left" class="title_item">售达方：</td>
			<td width="12%">${af.map.ag}</td>
			<td width="8%" colspan="1" align="left" class="title_item">出具发票方：</td>
			<td width="12%">${af.map.re}</td>
		</tr>
		<tr>
			<td width="8%" colspan="1" align="left" class="title_item">付款方：</td>
			<td width="12%">${af.map.rg}</td>
			<td width="8%" colspan="1" align="left" class="title_item">送达方：</td>
			<td width="12%">${af.map.we}</td>
		</tr>
		<tr>
			<td colspan="4">注：销售凭证信息系统自动从康佳R3系统实时抓取显示，没有查询到相关信息的字段是默认为康佳客户编码。</td>
		</tr>
        
      </table>
      </div>
      <!-- 附件 -->			    
	    <div id="tab3">
	    	<c:if test="${not empty attachmentList}">
	    	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
	    		<tr>
		        	<td class="title_item" style="text-align:center;font-size:15px;font-weight:bold;color:#74685F">附件</td>
		        </tr>
		        <tr>
		        	<td align="center">
		        		<div style="width:100%;padding:5px 5px 0 5px;">
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
		        		</div>
		        	</td>
		        </tr>
	    	</table>
		  	</c:if>
		  	<c:if test="${empty attachmentList}">
		  		<div style="color:#F00;text-align:center;">没有附件</div>
		  	</c:if>
	    </div>
	    
	     <!-- 意见反馈 -->
		<div id="tab4">
	    	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tableClass">
	    		<tr>
		        	<td  class="title_item" style="text-align:center;font-size:15px;font-weight:bold;color:#74685F">意见反馈</td>
		        </tr>
		        <tr>
		        	<td align="center">   
		        	<textarea name="content1" id="content1" rows="8" cols="65" <c:if test="${af.map.is_submit eq 1}"> disabled="disabled" </c:if> >${af.map.content1}</textarea>
		        	</td>
		        </tr>
	    	</table>
	    	<div style="height:400px;font-size: 25px;" align="center">  
	    		<c:if test="${not empty af.map.show}">
	    		<div style="margin-top:50px;"> 
	    		<span style="margin-left: 15px;"> <a id="various3" style="color: #08C;" href="${ctx}/manager/admin/KonkaOrderSearch.do?method=openiframe&t_id=${af.map.t_id}">点击查看反馈信息和回复</a></span>
	    		</div>
	    		</c:if>
	    	</div> 
	    </div>   
	    
	</div>
    </html-el:form>
  </div>  
</div>

<c:if test="${not empty af.map.konkaOrderInfoDetailsList}">
	<div style="display: none;" id="show_tip">
	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
		<tbody>
		<tr class="tabtt1">
			<td width="12%" nowrap="nowrap" align="center">产品型号</td>
			<td width="8%" nowrap="nowrap" align="center">前四周的销售数量</td>
	        <td width="5%" nowrap="nowrap" align="center">当前库存</td>
	  	</tr>
	  	</tbody>
	  	<c:forEach var="cur" items="${af.map.konkaOrderInfoDetailsList}">
	  	<tr>
	        <td align="center">${cur.pd_name}</td>
			<td align="center">${cur.map.order_fourweek_count}</td>
			<td align="center">${cur.map.curr_ku_count}</td>
		</tr>
	    </c:forEach>
	    <tr>
	        <td colspan="3"><span style="color:red;">提示：如果前四周销售数量减去库存小于订单数量，审核流程将进入下下一个审核人</span></td>
		</tr>
	</table>
	</div>
</c:if>


<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script> 
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
	
	// 将分类默认不选择
	$(".process_state").removeAttr("checked");
	$("#audit_comment" ).attr({"dataType":"Limit","max":"200","msg":"摘要内容的字数应少于250个字！"});
	$(".good_count").attr("dataType", "Require").attr("msg", "请填写数量，且必须为正数！");
	$(".good_price").attr("dataType", "Require").attr("msg", "请填写单价，且必须为正数！");
	<c:if test="${empty af.map.process_id}">
		$("#process_id").attr("dataType", "Require").attr("msg", "请选择订单类型！");
	</c:if>

	$("#audit_result").change(function(){
		var audit_result = $("#audit_result").val();
		if(audit_result == 1){
			$('input:radio[name="node_id"]').attr("checked", false);
		}
	});
	$(".process_id:first").attr("checked","checked");

	$("#various3").fancybox({
	    'overlayOpacity':'0.6', 
		'width':'60%',   
		'height':'75%',
		'autoScale':false,
		'transitionIn':'none',
		'transitionOut':'none',
		'type':'iframe'
	});
	
	//自动选择工厂仓位,某一订单行确认后,其它未填写的自动赋值
	$(".store_key").change(function(){
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
		
		<c:if test="${empty af.map.process_id}">
			 // 判断是否选择了统一流程，如果是则需要选择订单分类
		    $(".process_state").removeAttr("dataType");
			var id = $('input:radio:checked').val();
			var label = $("#process_id_label_" + id).html();
	    </c:if>
	    
		if(Validator.Validate(f, 1)){
			f.submit();	
		}	
		
	});

	$(".good_price").bind("input propertychange", function(){
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test($(this).val())) {
			$(this).val(0);
		}
		var id = $(this).attr("id").split("-")[1];
		var $good_count = $("#good_count-" + id);
		var $good_price = $("#good_price-" + id);
		var $good_sum_price = $("#good_sum_price-" + id);
		var $good_discount = $("#good_discount-" + id);
		var $good_discount_price_all_hid = $("#good_discount_price_all_hid-" + id);
		var $af_discount_price = $("#af_discount_price-" + id);
		
		//动态计算合计后的金额
	    $af_discount_price.val(parseFloat(parseFloat($good_price.val()*$good_count.val()*$good_discount.val()/100)+ parseFloat($good_discount_price_all_hid.val())+parseFloat($good_count.val())*parseFloat($good_price.val())).toFixed(2));
	    
		calcOneCountAndJhPrice($good_count,$good_price,$good_sum_price);
		calcPdNumAndPrice("details_body");
	});

	$(".good_count").bind("input propertychange", function(){
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test($(this).val())) {
			$(this).val(0);
		}

		var id = $(this).attr("id").split("-")[1];
		var $good_count = $("#good_count-" + id);
		var $good_price = $("#good_price-" + id);
		var $good_sum_price = $("#good_sum_price-" + id);
		var pd_name = $("#pd_name_span-"+id).text();
		var $af_discount_price = $("#af_discount_price-" + id);
		var $good_discount_price_all_hid = $("#good_discount_price_all_hid-" + id);
		var $good_discount = $("#good_discount-" + id);

		if(pd_name=='KF-22PB'|| pd_name=='KF-22XD' || pd_name=='KF-22KK'){
			$(this).val(1);
   	   	 }
		
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

		if(pd_name=='KF-22PB'|| pd_name=='KF-22XD' || pd_name=='KF-22KK'){
			$(this).val(0.00);
   	   	 }

		$af_discount_price.val(parseFloat(parseFloat($good_sum_price.val() * $good_discount.val()/100)+parseFloat($good_discount_price_all_hid.val())+parseFloat($good_sum_price.val())).toFixed(2));
		
   		calcPdNumAndPrice();
	});

	$(".good_discount_price_all_hid").blur("input propertychange", function(){ // 折扣金额
		var discount = (this.value);
		var _reg = /^[\-]\d*?\.?\d*?$/;
		if (!_reg.test(discount)) {
			//$good_discount_price.val(0);
			$(this).val(0);
			discount = 0;
		}

		var id = $(this).attr("id").split("-")[1];
		var $good_sum_price = $("#good_sum_price-" + id);
		var pd_name = $("#pd_name_span-"+id).text();
		var $af_discount_price = $("#af_discount_price-" + id);
		var $good_discount_price_all_hid = $("#good_discount_price_all_hid-" + id);
		var $good_discount = $("#good_discount-" + id);

		if(pd_name=='KF-22PB'|| pd_name=='KF-22XD' || pd_name=='KF-22KK'){
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
		window.parent.resizeFrameHeight('mainFrame', 3);
	});
	
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
