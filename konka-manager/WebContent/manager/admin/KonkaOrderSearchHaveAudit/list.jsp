<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	.rtable1 td {
		padding:2px 5px;
	}
</style>
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/KonkaOrderSearchHaveAudit">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="tj_type" styleId="tj_type" value="${af.map.tj_type}" />
      <table width="100%" border="0" cellspacing="1" cellpadding="0" class="rtable1">
        <tr>
          <td align="right">
          	<strong class="fb">交易流水号：</strong>
          </td>
          <td>
            <html-el:text property="trade_index_like" size="20" maxlength="40" styleId="trade_index_like"  />
          </td>
          <td align="right">
          	<strong class="fb">提交日期：</strong>
          </td>
          <td>
            <html-el:text property="order_date_start" styleId="order_date_start"  size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
            	至
            <html-el:text property="order_date_end" styleId="order_date_end"  size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
		  </td>
		  
          <td align="right">
	          <strong class="fb">部门：</strong>
	      </td> 
	      <td colspan="3">
		  	<c:if test="${empty current_dept}">
			<html-el:select property="dept_id" styleId="dept_id" disabled="${disabled}">
				<html-el:option value="">-分公司/经营部-</html-el:option>
			</html-el:select>
			<html-el:select property="l4_dept_id" styleId="l4_dept_id">
				<html-el:option value="">-请选择-</html-el:option>
			</html-el:select>
			<html-el:select property="l5_dept_id" styleId="l5_dept_id">
				<html-el:option value="">-请选择-</html-el:option>
			</html-el:select>
		 	</c:if>
		 	<c:if test="${not empty current_dept}">
		       	${fn:replace(current_dept.full_name, ',', ' &gt; ')}
		  	</c:if>
		  </td>
        </tr>
        <tr>
       		<td align="right"><strong class="fb">变更短信：</strong></td>
          	<td>
            	<html-el:select property="kh_confirm_state" styleClass="webinput" styleId="kh_confirm_state" >
              		<html-el:option value="">--请选择--</html-el:option>
              		<html-el:option value="0">未变更</html-el:option>
		            <html-el:option value="1">已发送提醒</html-el:option>
              		<html-el:option value="-1">未发送提醒</html-el:option>
            	</html-el:select>
            </td>
            <td align="right"><strong class="fb">支付方式：</strong></td>
            <td>
            	<html-el:select property="pay_type" styleClass="webinput" styleId="pay_type" >
              		<html-el:option value="">--请选择--</html-el:option>
              		<html-el:option value="4">现汇</html-el:option>
              		<html-el:option value="5">帐期</html-el:option>
              		<html-el:option value="6">承兑</html-el:option>
              		<html-el:option value="45">现汇、帐期</html-el:option>
              		<html-el:option value="46">现汇、承兑</html-el:option>
              		<html-el:option value="56">帐期、承兑</html-el:option>
              		<html-el:option value="456">现汇、帐期、承兑</html-el:option>
            	</html-el:select>
            </td>
            <td align="right"><strong class="fb">配送方式：</strong></td>
            <td>
            	<html-el:select property="send_type" styleClass="webinput" styleId="send_type" >
              		<html-el:option value="">--请选择--</html-el:option>
              		<html-el:option value="1">自提</html-el:option>
              		<html-el:option value="2">配送</html-el:option>
            	</html-el:select>
            </td>
            <td align="right"><strong class="fb">审核结果：</strong></td>
            <td>
            	<html-el:select property="dd_result" styleClass="webinput" styleId="dd_result" >
              		<html-el:option value="">--请选择--</html-el:option>
              		<html-el:option value="0">已驳回</html-el:option>
              		<html-el:option value="1">已通过</html-el:option>
            	</html-el:select>
            </td>
        </tr>
        <tr>
        	<td align="right"><strong class="fb">订单状态：</strong></td>
            <td>
            	<html-el:select property="or_audit_state" styleClass="webinput" styleId="or_audit_state" >
              		<html-el:option value="">--请选择--</html-el:option>
	                <html-el:option value="0">未审核</html-el:option>
	                <html-el:option value="1">审核中</html-el:option>
	                <html-el:option value="3">已完结</html-el:option>
	                <html-el:option value="4">已作废</html-el:option>
            	</html-el:select>
            </td>
          	<td align="right"><strong class="fb">发货状态：</strong></td>
            <td>
            	<html-el:select property="is_delivered" styleClass="webinput" styleId="is_delivered" >
              		<html-el:option value="">--请选择--</html-el:option>
              		<html-el:option value="0">未发货</html-el:option>
              		<html-el:option value="1">已发货</html-el:option>
            	</html-el:select>
            </td>
            <td align="right">
            	<strong class="fb">订单类型：</strong>
            </td>
            <td>
            	<html-el:select property="doc_type" styleClass="webinput" styleId="doc_type" >
            		<html-el:option value="">--请选择--</html-el:option>
            		<html-el:option value="ZFOR">ZFOR</html-el:option>
            		<html-el:option value="ZFGC">ZFGC</html-el:option>
             		<html-el:option value="ZFCR">ZFCR</html-el:option>
             		<html-el:option value="ZFRE">ZFRE</html-el:option>
          		</html-el:select>
          	</td>
          	<td align="right">
            	<strong class="fb">客户类型：</strong>
            </td>
            <td>
            	<html-el:select property="customer_type_index" styleId="customer_type_index">
              		<html-el:option value="">请选择...</html-el:option>
              		<c:forEach var="cur" items="${konkaCategoryList}">
                		<html-el:option value="${cur.c_index}">${cur.c_name}</html-el:option>
              		</c:forEach>
            	</html-el:select>
            </td> 
        </tr>
        <tr>
          	<td align="right"><strong class="fb">产品型号：</strong></td>
          	<td><html-el:text property="pd_name_like" styleClass="webinput" styleId="pd_name_like" maxlength="40"/></td>
            <td align="right"><strong class="fb">R3客户编码：</strong></td>
            <td><html-el:text property="ag_like" styleClass="webinput" styleId="ag_like" maxlength="40"/></td>
            <td align="right"><strong class="fb">客户名称：</strong></td>
            <td><html-el:text property="user_shop_name_like" size="20" maxlength="40" styleId="user_shop_name_like"  /></td>
            <td align="right"><strong class="fb">R3单号：</strong></td>
            <td>
            	<html-el:text property="r3_id"  styleClass="webinput" title="不能超过18位数字" styleId="r3_id" maxlength="18"/>
            </td>
       	</tr>
        <tr>
           	<td align="right"><strong class="fb">订单同步状态：</strong></td>
            <td>
            	<html-el:select property="sync_state" styleClass="webinput" styleId="sync_state" >
              		<html-el:option value="">--请选择--</html-el:option>
              		<html-el:option value="0">未同步</html-el:option>
              		<html-el:option value="1">已同步</html-el:option>
            	</html-el:select>
            </td>
            <td align="right">
	            <strong class="fb">R3物流单号：</strong>
	        </td>
	        <td>
            	<html-el:text property="vbedl_like" styleClass="webinput" styleId="vbedl_like" maxlength="40"/>
<!--            <html-el:checkbox property="vbedl_null" styleId="vbedl_null" value="0" /> 无R3物流号&nbsp;</input>-->
			</td>
			<td align="right">
             	<strong class="fb">R3物流单号：</strong>
            </td>
            <td>
            	<html-el:select property="vbedl_null" styleClass="webinput" styleId="vbedl_null" >
              		<html-el:option value="">--请选择--</html-el:option>
              		<html-el:option value="0">有R3物流单号</html-el:option>
              		<html-el:option value="1">无R3物流单号</html-el:option>
               	</html-el:select>
            </td>
            <td align="right">
                <strong class="fb">订单来源：</strong>
            </td>
            <td>
                <html-el:select property="order_type" styleClass="webinput" styleId="order_type" >
	              	<html-el:option value="">-请选择-</html-el:option>
					<html-el:option value="0">在线下单</html-el:option>
					<html-el:option value="1">图片下单</html-el:option>
					<html-el:option value="2">触网转单</html-el:option>
					<html-el:option value="4">从返利转</html-el:option>
					<html-el:option value="5">DRP转入</html-el:option>
               	</html-el:select>
           	</td>
       	</tr>
        <tr>
        	<td colspan="8">
        		<input class="but_excel" type="button" name="export_excel" id="export_excel" value="导出" />
	            <c:if test="${af.map.dept_type eq 1}"> 
	            <!-- 系统管理员 -->
	            <input type="button" id="syncBtnAllsys" class="but8" value="同步物流"></input>
	            <font color="#696969"></font>
	            </c:if>
	            
	            <c:if test="${af.map.dept_type eq 2 and (role_id_eq_30 or role_id_eq_57)}"> 
	            <!-- 系统管理员 -->
	            <input type="button" id="syncBtnAllfgs" class="but8"  value="同步物流"></input>
	            <font color="#696969"></font>
	            </c:if>
	            <html-el:button styleId="btn_submit" property="btn_submit" styleClass="but1" value="搜索" />
            </td>
        </tr>
      </table>
    </html-el:form>
  </div>
    <%@ include file="/commons/pages/messages.jsp" %>
    <div style="overflow-x: auto;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
     <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center">序号</td>
<!--        <td width="4%" nowrap="nowrap" align="center">同步信息</td>-->
         <td width="10%" nowrap="nowrap" align="center">流水号</td>
            <td width="8%" nowrap="nowrap" align="center">提交日期</td>
          <td width="8%" nowrap="nowrap" align="center">R3客户编码</td>
           <td nowrap="nowrap" align="center">客户名称</td>
           <td nowrap="nowrap" align="center">客户类型</td>
          <td width="6%" nowrap="nowrap" align="center">数量</td>
        <td width="6%" nowrap="nowrap" align="center">金额￥</td>
        <td width="6%" nowrap="nowrap" align="center">折扣￥</td>
           <c:if test="${af.map.dept_type eq 1}"> 
          <!-- 系统管理员 -->
          <!--<td width="6%" nowrap="nowrap" align="center">我的状态</td>-->
          <td width="6%" nowrap="nowrap" align="center">待审核角色</td>
        </c:if>
        <c:if test="${af.map.dept_type eq 2}"> 
          <!-- 非系统管理员 -->
<!--          <td width="6%" nowrap="nowrap" align="center">我的状态</td>-->
        </c:if>
         <c:if test="${af.map.dept_type eq 2 and role_id_eq_30}"> 
         <td width="6%" nowrap="nowrap" align="center">待审核角色</td>
          </c:if>
        <td width="6%" nowrap="nowrap" align="center">订单状态</td>
        <td width="5%" nowrap="nowrap" align="center">R3单号</td>
        <td width="6%" nowrap="nowrap" align="center">发货状态</td>
        <td width="5%" nowrap="nowrap" align="center">R3物流单号</td>
              <td width="5%" nowrap="nowrap" align="center">发货时间</td>
        <td width="5%" nowrap="nowrap" align="center">收货时间</td>
        
        <td nowrap="nowrap" align="center">分公司</td>
        <td nowrap="nowrap" align="center">经办</td>
<!--        <td width="6%" nowrap="nowrap" align="center">变更短信</td>-->
        <td width="5%" nowrap="nowrap" align="center">流程</td>
        <td width="5%" nowrap="nowrap" align="center">订单来源</td>
       <td width="5%" nowrap="nowrap" align="center">创建日期</td>
<!--        <td width="9%" nowrap="nowrap" align="center">操作</td>-->
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
          
			<!-- 流水号           -->
           <td align="center" nowrap="nowrap"><a href="${ctx}/manager/admin/KonkaOrderSearch.do?method=view&order_id=${cur.id}&mod_id=${af.map.mod_id}" style="color:blue;text-decoration:underline;">${cur.trade_index}</a></td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.order_date}" pattern="yyyy-MM-dd"/></td>
          <!-- R3客户编码 -->
           <td align="center" nowrap="nowrap"><a style="cursor:pointer;color:blue;" href="KonkaR3MmtMatch.do?method=detail&id=${cur.cust_id}&mod_id=101010&key=1">${cur.ag}</a></td>
           <!-- 客户名称 -->
           <td nowrap="nowrap"><a style="cursor:pointer;color:blue;" href="KonkaR3MmtMatch.do?method=detail&id=${cur.cust_id}&mod_id=101010&key=1">${cur.user_shop_name}</a></td>
        <td nowrap="nowrap" align="center">${cur.map.customer_type_name}</td>
          <td align="right" nowrap="nowrap">${cur.order_num}</td>
          <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.money}" type="currency" />
            </span></td>
          <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.good_discount_price}" type="currency" />
            </span></td>
              <c:if test="${af.map.dept_type eq 1}"> 
            <td align="center" nowrap="nowrap"><c:choose>
               <c:when test="${empty cur.map.next_audit_role_name}">无</c:when>
                <c:when test="${not empty cur.map.next_audit_role_name}">${cur.map.next_audit_role_name}</c:when>
             </c:choose></td>
          </c:if>
             <c:if test="${af.map.dept_type eq 2 and role_id_eq_30}"> 
          <td align="center" nowrap="nowrap"><c:choose>
                <c:when test="${empty cur.map.next_audit_role_name}">无</c:when>
                <c:when test="${not empty cur.map.next_audit_role_name}">${cur.map.next_audit_role_name}</c:when>
              </c:choose></td>
              </c:if>
          <td align="center" nowrap="nowrap">
          	<c:choose>
               <c:when test="${cur.audit_state eq 0}"><span style="color:#F00;">未审核</span></c:when>
               <c:when test="${cur.audit_state eq 1}"><span style="color:green;">审核中</span></c:when>
               <c:when test="${cur.audit_state eq 3}"><span style="color:00F;">已完结</span></c:when>
               <c:when test="${cur.audit_state eq 4}"><span style="color:grey;">已作废</span></c:when>
             </c:choose>
		  </td>
          <td align="center" nowrap="nowrap">${empty cur.r3_id ? '未同步' : cur.r3_id}</td>
           <td align="center" nowrap="nowrap"><c:if test="${cur.is_delivered eq 0}">未发货</c:if>
            <c:if test="${cur.is_delivered eq 1}">已发货</c:if></td>
             <td align="right" nowrap="nowrap">${cur.map.vbedl}</td>
            <td align="right" nowrap="nowrap">${cur.map.lfdat}</td>
            <td align="right" nowrap="nowrap">${cur.map.shdat}</td>
            
            
          <td align="right" nowrap="nowrap">${cur.map.fgsName}</td>
          <td align="right" nowrap="nowrap">${cur.map.jbName}</td>
          <td align="center" nowrap="nowrap">${empty cur.process_id ? '未确定' : '已确定'}</td>
          <td align="center" nowrap="nowrap"><c:choose>
                    <c:when test="${cur.order_type eq 0}"><span style="color:green;">在线下单</span></c:when>
                    <c:when test="${cur.order_type eq 1}"><span style="color:green;">图片下单</span></c:when>
                    <c:when test="${cur.order_type eq 2}"><span style="color:green;">触网转单</span></c:when>
                    <c:when test="${cur.order_type eq 4}"><span style="color:green;">从返利转</span></c:when>
                    <c:when test="${cur.order_type eq 5}"><span style="color:green;">DRP转入</span></c:when>
                  </c:choose></td>
          <td align="center" nowrap="nowrap">
          <fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" />
          </td>
        </tr>
      </c:forEach>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaOrderSearchHaveAudit.do">
    <input id='export_id' style="display:none"  name='excel_all' value='0' />
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="60" align="center">
            <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
						var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
						pager.addHiddenInputs("method", "list");
						pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
						pager.addHiddenInputs("trade_index_like", "${af.map.trade_index_like}");
						pager.addHiddenInputs("order_date_start", "${af.map.order_date_start}");
						pager.addHiddenInputs("order_date_end", "${af.map.order_date_end}");
						pager.addHiddenInputs("user_name_like", "${af.map.user_name_like}");
						pager.addHiddenInputs("process_state", "${af.map.process_state}");
						pager.addHiddenInputs("process_id", "${af.map.process_id}");
						
						pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
						pager.addHiddenInputs("l4_dept_id", "${af.map.l4_dept_id}");
						pager.addHiddenInputs("l5_dept_id", "${af.map.l5_dept_id}");
						
						pager.addHiddenInputs("kh_confirm_state", "${af.map.kh_confirm_state}");
						pager.addHiddenInputs("pay_type", "${af.map.pay_type}");
						pager.addHiddenInputs("doc_type", "${af.map.doc_type}");
						pager.addHiddenInputs("send_type", "${af.map.send_type}");
						pager.addHiddenInputs("my_audit_state", "${af.map.my_audit_state}");
						pager.addHiddenInputs("or_audit_state", "${af.map.or_audit_state}");
						pager.addHiddenInputs("pd_name_like", "${af.map.pd_name_like}");
						pager.addHiddenInputs("is_delivered", "${af.map.is_delivered}");
						pager.addHiddenInputs("ag_like", "${af.map.ag_like}");
						pager.addHiddenInputs("r3_id", "${af.map.r3_id}");
						pager.addHiddenInputs("sync_state", "${af.map.sync_state}");
						pager.addHiddenInputs("vbedl_like", "${af.map.vbedl_like}");
						pager.addHiddenInputs("vbedl_null", "${af.map.vbedl_null}");
						pager.addHiddenInputs("order_type", "${af.map.order_type}");
						pager.addHiddenInputs("user_shop_name_like", "${af.map.user_shop_name_like}");
						pager.addHiddenInputs("customer_type_index", "${af.map.customer_type_index}");
						pager.addHiddenInputs("dd_result", "${af.map.dd_result}");
						document.write(pager.toString());
					</script></td>
        </tr>
      </table>
    </form>
    </div>
    
    
    <div class="rtabcont1" style="overflow-x: auto;display:none; " id="divExcel_all" title="订单">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center">序号</td>
        <td nowrap="nowrap" align="center">分公司</td>
        <td nowrap="nowrap" align="center">经办</td>
        <td width="8%" nowrap="nowrap" align="center">下单日期</td>
        <td width="10%" nowrap="nowrap" align="center">流水号</td>
        <td width="8%" nowrap="nowrap" align="center">R3客户编码</td>
        <td nowrap="nowrap" align="center">客户名称</td>
        <td width="6%" nowrap="nowrap" align="center">数量</td>
        <td width="6%" nowrap="nowrap" align="center">金额￥</td>
        <td width="6%" nowrap="nowrap" align="center">折扣￥</td>
        <c:if test="${af.map.dept_type eq 1}"> 
          <!-- 系统管理员 -->
          <td width="6%" nowrap="nowrap" align="center">审核状态</td>
          <td width="6%" nowrap="nowrap" align="center">待审核角色</td>
        </c:if>
        <c:if test="${af.map.dept_type eq 2}"> 
          <!-- 非系统管理员 -->
          <td width="6%" nowrap="nowrap" align="center">审核状态</td>
        </c:if>
        <td width="6%" nowrap="nowrap" align="center">变更短信</td>
        <td width="6%" nowrap="nowrap" align="center">发货状态</td>
        <td width="6%" nowrap="nowrap" align="center">R3物流单号</td>
        <td width="6%" nowrap="nowrap" align="center">发货时间</td>
        <td width="6%" nowrap="nowrap" align="center">收货时间</td>
        <td width="6%" nowrap="nowrap" align="center">流程</td>
        <td width="6%" nowrap="nowrap" align="center">R3订单号</td>
        
      </tr>
      <c:forEach var="cur" items="${allList}" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
          <td align="right" nowrap="nowrap">${cur.map.fgsName}</td>
          <td align="right" nowrap="nowrap">${cur.map.jbName}</td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.order_date}" pattern="yyyy-MM-dd"/></td>
          <td align="center" nowrap="nowrap">${cur.trade_index}</td>
          <td align="center" nowrap="nowrap">${cur.ag}</td>
          <td nowrap="nowrap">${cur.user_shop_name}</td>
          <td align="right" nowrap="nowrap">${cur.order_num}</td>
          <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.money}" type="currency" />
            </span></td>
          <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.good_discount_price}" type="currency" />
            </span></td>
          <c:if test="${af.map.dept_type eq 1}"> 
            <!-- 系统管理员 -->
            <td align="center" nowrap="nowrap"><c:choose>
                <c:when test="${(cur.audit_state eq 3) and (cur.kh_confirm_state ne -1) }"><span style="color:#00F;">已完结</span></c:when>
                <c:when test="${(cur.audit_state eq 4)}"><span style="color:#00F;">已作废</span></c:when>
                <c:otherwise><span style="color:#F00;">审核中</span></c:otherwise>
              </c:choose></td>
            <td align="center" nowrap="nowrap"><c:choose>
                <c:when test="${empty cur.map.next_audit_role_name}">无</c:when>
                <c:when test="${not empty cur.map.next_audit_role_name}">${cur.map.next_audit_role_name}</c:when>
              </c:choose></td>
          </c:if>
          <c:if test="${af.map.dept_type eq 2}"> 
            <!-- 非系统管理员 -->
            <td align="center" nowrap="nowrap"><c:choose>
                <c:when test="${cur.audit_state eq 3}"><span style="color:#00F;">已完结</span></c:when>
                 <c:when test="${cur.audit_state eq 4}"><span style="color:#00F;">已作废</span></c:when>
                <c:otherwise>
                  <c:choose>
                    <c:when test="${cur.map.states eq 0}"><span style="color:#F00;">待审核</span></c:when>
                    <c:when test="${cur.map.states eq 1}"><span style="color:green;">审核中</span></c:when>
                  </c:choose>
                </c:otherwise>
              </c:choose></td>
          </c:if>
          <td align="center" nowrap="nowrap">${fn:split('未发送提醒,未变更,已发送提醒', ',')[cur.kh_confirm_state + 1]}</td>
          <td align="center" nowrap="nowrap"><c:if test="${cur.is_delivered eq 0}">未发货</c:if>
            <c:if test="${cur.is_delivered eq 1}">已发货</c:if></td>
          <td align="right" nowrap="nowrap">${cur.map.vbedl}</td>
          <td align="right" nowrap="nowrap">${cur.map.lfdat}</td>
          <td align="right" nowrap="nowrap">${cur.map.shdat}</td>
          <td align="center" nowrap="nowrap">${empty cur.process_id ? '未确定' : '已确定'}</td>
          <td align="center" nowrap="nowrap">${empty cur.r3_id ? '未同步' : cur.r3_id}</td>
        </tr>
      </c:forEach>
    </table>
    </div>
  
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
<script type="text/javascript">//<![CDATA[    
$(document).ready(function(){
	$("#span_help").click(function(){
        $("#cvtooltipClose").cvtooltip({
            panel: "#body_oarcont",
            direction: 1,                
            width: 420,
            left: 320,
            top: 5,
            speed: 500,
            delay: 10000
        });
    });
	//if("${af.map.vbedl_null}"=="1"){
	//	$("#vbedl_null").attr("checked","checked");
//}
	$("#order_date_start").attr("dataType", "Require").attr("msg", "请选择下单开始日期！");
	$("#order_date_end").attr("dataType", "Require").attr("msg", "请选择下单结束日期！");

	var current_dept = '${empty current_dept}';
	if(current_dept != 'false'){
		$("#dept_id").attr({"subElement": "l4_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.dept_id}"});
		$("#l4_dept_id"    ).attr({"subElement": "l5_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
		$("#l5_dept_id" ).attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l5_dept_id}"});

		$("#dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "${empty cs_par_id ? 0 : cs_par_id}");
		$("#dept_id").change();
	}

	$("#vbedl_null").change(function(){
		var isChecked = $('#vbedl_null').val();
		if (isChecked=='1') {
			$("#vbedl_like").val("");
			$("#vbedl_null").val("1");
			}
			});
	
	
	$("#btn_submit").click(function(){
		var isSubmit = Validator.Validate(this.form, 1);
		if (isSubmit) {
			var min_date = $("#order_date_start").val();
			if (min_date != '' && min_date < '2013-08-10') {
				alert("2013年8月9日14:10:51及之前的订单请按照说明3的方式查询。");
				return;
			}
			this.form.submit();	
		}
	});
});

// 回车提交表单
function keyEnter(){ if(event.keyCode == 13) $("#btn_submit").click(); } 
document.onkeydown = keyEnter; 

//导出excel
$("#export_excel").click(function(){
	if(confirm("提示，您确认导出数据？")){
		//CNZZ统计代码
	//	$("#bottomPageForm").append("<input id='export_id'  name='excel_all' value='1' />");
		$("#export_id").val(1);
		$("#bottomPageForm").submit();
	}
	$("#export_id").val(0);
});
var excel_all = "${af.map.excel_all}";
if("1" == excel_all){
	toExcel('divExcel_all', '${ctx}/manager/admin/KonkaOrderSearch.do?method=toExcel');
	
}


$("#syncBtnAllsys").click(function(){
	var isExecute = doSyncMethod("此操作用于同步所有分公司R3物流数据,确认操作?", 'KonkaOrderSearch.do','ZbSync','mod_id=${af.map.mod_id}' ,$('#bottomPageForm').serialize());
	if( isExecute == true){
		$("#syncBtnAllsys").attr("disabled","disabled");
		$("font").text("数据同步中...").css("color","red");
	}
});
$("#syncBtnAllfgs").click(function(){
	var isExecute = doSyncMethod("此操作用于同步分公司所有R3物流数据,确认操作?", 'KonkaOrderSearch.do','ZbSync','mod_id=${af.map.mod_id}' ,$('#bottomPageForm').serialize());
	if( isExecute == true){
		$("#syncBtnAllfgs").attr("disabled","disabled");
		$("font").text("数据同步中...").css("color","red");
	}
});


function doSyncMethod(msg, page, method, queryString) {

	if(msg != null) {
		if(!confirm(msg))
			return false;
	}
	page = page || "?";
	page = page.indexOf("?") != -1 ? page : (page + "?");
	location.href = page  + "method=" + method + "&" + encodeURI(queryString);
	return true;
}

$("#r3_id").blur(function(){
    var text =this.value;
    regx = /[^\n\d\r]/;
    if(regx.test(text)){
        alert('R3单号只允许输入数字');
        this.value="";
        }
    });
function  show(id){
	var X = $('#message_'+id).offset().top-10;
	var Y = $('#message_'+id).offset().left-410;
	$("#msgshow_"+id).css({"top":X,"left":Y});
$("#msgshow_"+id).show();
}
function  hide(id){
	$("#msgshow_"+id).hide();
	}

function sendmsg(id,count){
	if(count<=5){
		if(confirm("提示，您确认发送提醒短信？")){
   			$.ajax({type: "post", 
		   url : "${ctx}/manager/admin/KonkaOrderSearch.do?method=sendmsg", 
		   dataType:'json',
		   sync: true, 
		   error: function(){alert("短信发送未成功！");},
		   data: {"order_id" :id},
		   success: function(json){
		      var num=json.messagecount;
			$("#message_"+id).html("已发送提醒&nbsp;"+num+"次");
			var list=json.konkaorderinfomessagesendlist;
			$("#msgshow_"+id).empty();
			$("#msgshow_"+id).append("<ul  style='list-style-type:none;' id='msgshow_ul_"+id+"'>");
			for(var k=0;k<list.length;k++){
				var time =new Date(list[k].send_date).toLocaleDateString();
				$("#msgshow_ul_"+id).after("<li style='list-style-type:none;' > 发送人："+list[k].sender_name+"&emsp;接收人:"+list[k].receiver_name+"&emsp;时间："+time+"</li>");
			}
			$("#msgshow_"+id).append("</ul>")
			 if (num> count){
				 alert("提醒短信已成功发送");
				 }else{
			    	alert("提醒短信发送失败！请确保制单人手机号码不为空。");
			    }
			}
		});
   }
}}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>