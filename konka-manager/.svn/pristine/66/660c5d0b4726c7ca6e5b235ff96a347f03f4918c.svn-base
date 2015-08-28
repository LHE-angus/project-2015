<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
</head>

<body>   
     <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
     <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center">序号</td>
       
         <td width="10%" nowrap="nowrap" align="center">流水号</td>
            <td width="8%" nowrap="nowrap" align="center">提交日期</td>
          <td width="8%" nowrap="nowrap" align="center">R3客户编码</td>
           <td nowrap="nowrap" align="center">分公司</td>
           <td nowrap="nowrap" align="center">客户名称(制单人)</td>
           
          <td width="9%" nowrap="nowrap" align="center">产品型号</td>
                      <td width="9%" nowrap="nowrap" align="center">数量</td>
                    <td width="9%" nowrap="nowrap" align="center">单价</td>
                     <td width="9%" nowrap="nowrap" align="center">金额</td>
                      
                       <td width="9%" nowrap="nowrap" align="center"> 折扣(%)-K007</td>
                       <td width="9%" nowrap="nowrap" align="center">折扣金额-RB00</td>
                       <td width="9%" nowrap="nowrap" align="center">产品折扣后金额</td>
                   
                <td  nowrap="nowrap" align="center">第三方采购编号</td>  
                     <td  nowrap="nowrap" align="center">业务员</td>  
               
                <td  nowrap="nowrap" align="center">[R3]销售组织</td>  
                 <td  nowrap="nowrap" align="center">产品组</td>  
                  <td  nowrap="nowrap" align="center">[R3]售达方</td>  
                   <td  nowrap="nowrap" align="center">出具发票方</td>  
                    <td  nowrap="nowrap" align="center">[R3]付款方</td>  
                     <td  nowrap="nowrap" align="center">[R3]送达方</td>  
                      <td width="9%" nowrap="nowrap" align="center">前四周销量</td>
             <td width="9%" nowrap="nowrap" align="center">当前库存</td>
                       <td width="9%" nowrap="nowrap" align="center">创建日期</td>
                       
                       
           <c:if test="${af.map.dept_type eq 1}"> 
          <!-- 系统管理员 -->
          <td width="6%" nowrap="nowrap" align="center">审核状态</td>
          <td width="6%" nowrap="nowrap" align="center">待审核角色</td>
        </c:if>
        <c:if test="${af.map.dept_type eq 2}"> 
          <!-- 非系统管理员 -->
          <td width="6%" nowrap="nowrap" align="center">审核状态</td>
        </c:if>
        
        <td width="5%" nowrap="nowrap" align="center">R3单号</td>
        <td width="6%" nowrap="nowrap" align="center">发货状态</td>
        <td width="5%" nowrap="nowrap" align="center">R3物流单号</td>
              <td width="5%" nowrap="nowrap" align="center">发货时间</td>
        <td width="5%" nowrap="nowrap" align="center">收货时间</td>
        
       
        <td nowrap="nowrap" align="center">经办</td>
        <td width="5%" nowrap="nowrap" align="center">流程</td>
        <td width="5%" nowrap="nowrap" align="center">订单来源</td>
                       <td  nowrap="nowrap" align="center">收货人姓名</td>
              <td  nowrap="nowrap" align="center">收货人固定电话</td>
             <td  nowrap="nowrap" align="center">收货人手机</td> 
               <td  nowrap="nowrap" align="center">送货地址</td>  
                <td width="9%" nowrap="nowrap" align="center">产品备注</td>
        
        
      </tr>
      <c:forEach var="cur" items="${allList}" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
			<!-- 流水号           -->
           <td align="center" nowrap="nowrap"><a href="${ctx}/manager/admin/KonkaOrderSearch.do?method=view&order_id=${cur.id}&mod_id=${af.map.mod_id}" style="color:blue;text-decoration:underline;">${cur.trade_index}</a></td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.order_date}" pattern="yyyy-MM-dd"/></td>
          <!-- R3客户编码 -->
           <td align="center" nowrap="nowrap"><a style="cursor:pointer;color:blue;" href="KonkaR3MmtMatch.do?method=detail&id=${cur.cust_id}&mod_id=101010&key=1">${cur.ag}</a></td>
            <td align="right" nowrap="nowrap">${cur.map.fgsName}</td>
           <!-- 客户名称 -->
           <td nowrap="nowrap"><a style="cursor:pointer;color:blue;" href="KonkaR3MmtMatch.do?method=detail&id=${cur.cust_id}&mod_id=101010&key=1">${cur.user_shop_name}</a></td>
         
                        <td align="center" nowrap="nowrap">${cur.map.pd_name}</td>
                        <td align="center" nowrap="nowrap">${cur.map.good_count}</td>
                     <td align="center" nowrap="nowrap">
                     <span class="kz-price-12">
          <fmt:formatNumber value="${cur.map.good_price}" type="currency" />
            </span>
                     
                     </td>
                     <td align="center" nowrap="nowrap">
                     <span class="kz-price-12">
          <fmt:formatNumber value="${cur.map.good_price*cur.map.good_count}" type="currency" />
            </span>
                     
                     </td>
                    
                        <td align="center" nowrap="nowrap">
                        <fmt:formatNumber type="number" value="${cur.map.good_discount} " maxFractionDigits="2"/>
                        %</td>
                        
                         <td align="center" nowrap="nowrap">
                          <span class="kz-price-12">
          <fmt:formatNumber value="${cur.map.good_price*cur.map.good_count*cur.map.good_discount*0.01+cur.map.d_good_discount_price}" type="currency" />
            </span>
                        </td>
                           <td align="center" nowrap="nowrap">
                           <span class="kz-price-12">
          <fmt:formatNumber value="${cur.map.good_count*cur.map.good_price*(1+cur.map.good_discount*0.01)+cur.map.d_good_discount_price}" type="currency" />
            </span>
                           </td>
                      
               
                <td  nowrap="nowrap" align="center">${cur.third_cg_order_num}</td> 
                <td  nowrap="nowrap" align="center">${cur.map.ywy}</td>
                
                
                <td  nowrap="nowrap" align="center">${cur.sales_org}</td>
                      <td  nowrap="nowrap" align="center">${cur.division}</td>
                       <td  nowrap="nowrap" align="center">${cur.ag}</td>
                        <td  nowrap="nowrap" align="center">${cur.re}</td>
                         <td  nowrap="nowrap" align="center">${cur.rg}</td>
                          <td  nowrap="nowrap" align="center">${cur.we}</td>
                       
                       
                       <td align="center" nowrap="nowrap">${cur.map.sale_count}</td>
                       <td align="center" nowrap="nowrap">${cur.map.store_num}</td>
                       <td align="center" nowrap="nowrap">
                       <fmt:formatDate value="${cur.map.add_date}" pattern="yyyy-MM-dd"/></td>
               <c:if test="${af.map.dept_type eq 1}"> 
            <!-- 系统管理员 -->
            <td align="center" nowrap="nowrap"><c:choose>
             <c:when test="${(cur.audit_state eq 3) and (cur.kh_confirm_state ne -1) }"><span style="color:#00F;">已完结</span></c:when>
                <c:when test="${cur.audit_state eq 4}"><span style="color:grey;">已作废</span></c:when>
                <c:when test="${(cur.audit_state eq 3) and (cur.kh_confirm_state eq -1) }"><span style="color:#00F;">已完结(待发短息)</span></c:when>
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
                 <c:when test="${cur.audit_state eq 4}"><span style="color:grey;">已作废</span></c:when>
                <c:otherwise>
                  <c:choose>
                    <c:when test="${cur.map.states eq 0}"><span style="color:#F00;">待审核</span></c:when>
                    <c:when test="${cur.map.states eq 1}"><span style="color:green;">审核中</span></c:when>
                  </c:choose>
                </c:otherwise>
              </c:choose></td>
          </c:if>
          <td align="center" nowrap="nowrap">${empty cur.r3_id ? '未同步' : cur.r3_id}</td>
           <td align="center" nowrap="nowrap"><c:if test="${cur.is_delivered eq 0}">未发货</c:if>
            <c:if test="${cur.is_delivered eq 1}">已发货</c:if></td>
             <td align="right" nowrap="nowrap">${cur.map.vbedl}</td>
            <td align="right" nowrap="nowrap">${cur.map.lfdat}</td>
            <td align="right" nowrap="nowrap">${cur.map.shdat}</td>
          <td align="right" nowrap="nowrap">${cur.map.jbName}</td>
         
          <td align="center" nowrap="nowrap">${empty cur.process_id ? '未确定' : '已确定'}</td>
          <td align="center" nowrap="nowrap"><c:choose>
                    <c:when test="${cur.order_type eq 0}"><span style="color:green;">在线下单</span></c:when>
                    <c:when test="${cur.order_type eq 1}"><span style="color:green;">图片下单</span></c:when>
                     <c:when test="${cur.order_type eq 2}"><span style="color:green;">触网转单</span></c:when>
                      <c:when test="${cur.order_type eq 4}"><span style="color:green;">从返利转</span></c:when>
                      <c:when test="${cur.order_type eq 5}"><span style="color:green;">DRP转入</span></c:when>
                  </c:choose></td>
           
                        <td  nowrap="nowrap" align="center">${cur.user_name}</td>
              <td  nowrap="nowrap" align="center">${cur.user_tel}</td>
             <td  nowrap="nowrap" align="center">${cur.user_phone}</td> 
               <td  nowrap="nowrap" align="center">${cur.user_addr}</td>  
         
              <td align="center" nowrap="nowrap">
                     <c:choose> 
					    <c:when test="${fn:length(cur.map.pd_remark) > 10}"> 
					     <c:out value="${fn:substring(cur.map.pd_remark, 0, 10)}." /> 
					    </c:when> 
					    <c:otherwise> 
					     <c:out value="${cur.map.pd_remark}"/> 
					    </c:otherwise> 
					   </c:choose> 
                       </td>
        </tr>
      </c:forEach>
    </table>
</body>
</html>