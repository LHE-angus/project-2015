<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
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
    <html-el:form action="/admin/KonkaOrderSearch">
      <html-el:hidden property="method" value="listdetails" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="tj_type" styleId="tj_type" value="${af.map.tj_type}" />
      <table width="100%" border="0" cellspacing="1" cellpadding="0" class="rtable1">
        <tr>
          <td>&nbsp;&nbsp;<strong class="fb">交易流水号：</strong>
            <html-el:text property="trade_index_like" size="20" maxlength="40" styleId="trade_index_like"  />
            &nbsp;<strong class="fb">提交日期：</strong>
            <html-el:text property="order_date_start" styleId="order_date_start"  size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
            至
            <html-el:text property="order_date_end" styleId="order_date_end"  size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
            &nbsp; &nbsp; 
            <c:if test="${af.map.dept_type eq 1}">
             <strong class="fb">分公司：</strong>
              <html-el:select property="dept_id" styleId="dept_id">
                <html-el:option value="">-请选择-</html-el:option>
              </html-el:select>
              <html-el:select property="l4_dept_id" styleId="l4_dept_id">
                <html-el:option value="">-请选择-</html-el:option>
              </html-el:select>
              <html-el:select property="l5_dept_id" styleId="l5_dept_id">
                <html-el:option value="">-请选择-</html-el:option>
              </html-el:select>
            </c:if></td>
        </tr>
        <tr>
          <td>&nbsp;&nbsp;<strong class="fb">变更短信：</strong>
            <html-el:select property="kh_confirm_state" styleClass="webinput" styleId="kh_confirm_state" >
              <html-el:option value="">--请选择--</html-el:option>
              <html-el:option value="0">未变更</html-el:option>
              <html-el:option value="1">已发送提醒</html-el:option>
              <html-el:option value="-1">未发送提醒</html-el:option>
            </html-el:select>
            &nbsp;&nbsp;<strong class="fb">支付方式：</strong>
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
            &nbsp;&nbsp; <strong class="fb">配送方式：</strong>
            <html-el:select property="send_type" styleClass="webinput" styleId="send_type" >
              <html-el:option value="">--请选择--</html-el:option>
              <html-el:option value="1">自提</html-el:option>
              <html-el:option value="2">配送</html-el:option>
            </html-el:select>
            &nbsp;&nbsp; <strong class="fb">审核状态：</strong>
            <html-el:select property="audit_state" styleClass="webinput" styleId="audit_state" >
              <html-el:option value="">--请选择--</html-el:option>
           <!--  非系统管理员   -->
              <c:if test="${af.map.dept_type eq 2}">
                <html-el:option value="20">待审核</html-el:option>
                <html-el:option value="21">已审核</html-el:option>
                <html-el:option value="31">已作废</html-el:option>
              </c:if>
              <!--系统管理员-->
              <c:if test="${af.map.dept_type eq 1}">
                <html-el:option value="10">审核中</html-el:option>
                <html-el:option value="11">已完结</html-el:option>
                <html-el:option value="31">已作废</html-el:option>
              </c:if>
            </html-el:select>
        </td>
        <tr>
        <td>
          &nbsp;&nbsp;<strong class="fb">发货状态：</strong>
            <html-el:select property="is_delivered" styleClass="webinput" styleId="is_delivered" >
              <html-el:option value="">--请选择--</html-el:option>
              <html-el:option value="0">未发货</html-el:option>
              <html-el:option value="1">已发货</html-el:option>
            </html-el:select>
            &nbsp;&nbsp; <strong class="fb">订单类型：</strong>
            <html-el:select property="doc_type" styleClass="webinput" styleId="doc_type" >
            <html-el:option value="">--请选择--</html-el:option>
            <html-el:option value="ZFOR">ZFOR</html-el:option>
            <html-el:option value="ZFGC">ZFGC</html-el:option>
          </html-el:select>
        </td>
        </tr>
        
        </tr>
        <tr>
          <td>&nbsp;&nbsp;<strong class="fb">产品型号：</strong>
            <html-el:text property="pd_name_like" styleClass="webinput" styleId="pd_name_like" maxlength="40"/>
            &nbsp;&nbsp;<strong class="fb">R3客户编码：</strong>
            <html-el:text property="ag_like" styleClass="webinput" styleId="ag_like" maxlength="40"/>
            &nbsp;&nbsp; <strong class="fb">客户名称：</strong>
            <html-el:text property="user_shop_name_like" size="20" maxlength="40" styleId="user_shop_name_like"  />
            &nbsp;&nbsp;
            <strong class="fb">R3单号：</strong>
            <html-el:text property="r3_id"  styleClass="webinput" title="不能超过18位数字" styleId="r3_id" maxlength="18"/>&nbsp;&nbsp;
            </td>
           </tr>
           
           <tr>
           <td>
           &nbsp;&nbsp;<strong class="fb">订单同步状态：</strong>
            <html-el:select property="sync_state" styleClass="webinput" styleId="sync_state" >
              <html-el:option value="">--请选择--</html-el:option>
              <html-el:option value="0">未同步</html-el:option>
              <html-el:option value="1">已同步</html-el:option>
            </html-el:select>
           &nbsp;&nbsp;
            <strong class="fb">R3物流单号：</strong>
            <html-el:text property="vbedl_like" styleClass="webinput" styleId="vbedl_like" maxlength="40"/>&nbsp;&nbsp;
            &nbsp;&nbsp;
             <strong class="fb">R3物流单号：</strong>
            <html-el:select property="vbedl_null" styleClass="webinput" styleId="vbedl_null" >
              <html-el:option value="">--请选择--</html-el:option>
              <html-el:option value="0">有R3物流单号</html-el:option>
              <html-el:option value="1">无R3物流单号</html-el:option>
               </html-el:select>
               &nbsp;&nbsp;
                <strong class="fb">订单来源：</strong>
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
           <td>
           &nbsp;&nbsp;<strong class="fb">同步人：</strong>
            	<html-el:text property="sync_user" size="20" maxlength="40" styleId="sync_user"  />
            &nbsp;&nbsp;
          	<strong class="fb">同步时间：</strong>
            <html-el:text property="sync_date_start" styleId="sync_date_start"  size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
                                至
            <html-el:text property="sync_date_end" styleId="sync_date_end"  size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
		  </td>
           </tr>
           <tr>
            <td>
            <input class="but_excel" type="button" name="export_excel" id="export_excel" value="导出" />&nbsp;&nbsp;
            <html-el:button styleId="btn_submit" property="btn_submit" styleClass="but1" value="搜索" />&nbsp;&nbsp;
            </td>
        </tr>
      </table>
    </html-el:form>
  </div>
   <div class="rtabcont1" style="color:#465F6D;">
 &nbsp;&nbsp;注：本页订单商品数量为：${pageCount}&nbsp;台;商品总价为：￥${pageMoney}&nbsp;元;商品总折扣为：￥${pageDiscount}元。
  &nbsp;&nbsp;所有商品数量为：${numCount}&nbsp;台;商品总价为：￥${moneyCount}&nbsp;元;商品总折扣为：￥${disCount}元。
 </div>
    <%@ include file="/commons/pages/messages.jsp" %>
    <div style="overflow-x: auto;">
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
        <td width="5%" nowrap="nowrap" align="center">同步人</td>
        <td width="5%" nowrap="nowrap" align="center">同步日期</td>
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
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
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
          <td align="center" nowrap="nowrap">${cur.sync_user}</td>
          <td align="center" nowrap="nowrap">${fn:substring(cur.sync_date, 0, 11)}</td>
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
    </div>
    
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaOrderSearch.do">
    
    <input id='export_id' style="display:none"  name='excel_all' value='0' />
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right">
            <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
						var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
						pager.addHiddenInputs("method", "listdetails");
						pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
						pager.addHiddenInputs("trade_index_like", "${af.map.trade_index_like}");
						pager.addHiddenInputs("order_date_start", "${af.map.order_date_start}");
						pager.addHiddenInputs("order_date_end", "${af.map.order_date_end}");
						pager.addHiddenInputs("user_name_like", "${af.map.user_name_like}");
						pager.addHiddenInputs("process_state", "${af.map.process_state}");
						pager.addHiddenInputs("process_id", "${af.map.process_id}");
						if("${af.map.dept_type}" ==  "1"){
							pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
							pager.addHiddenInputs("l4_dept_id", "${af.map.l4_dept_id}");
							pager.addHiddenInputs("l5_dept_id", "${af.map.l5_dept_id}");
						}
						pager.addHiddenInputs("kh_confirm_state", "${af.map.kh_confirm_state}");
						pager.addHiddenInputs("pay_type", "${af.map.pay_type}");
						pager.addHiddenInputs("doc_type", "${af.map.doc_type}");
						pager.addHiddenInputs("send_type", "${af.map.send_type}");
						pager.addHiddenInputs("audit_state", "${af.map.audit_state}");
						pager.addHiddenInputs("pd_name_like", "${af.map.pd_name_like}");
						pager.addHiddenInputs("is_delivered", "${af.map.is_delivered}");
						pager.addHiddenInputs("ag_like", "${af.map.ag_like}");
						pager.addHiddenInputs("r3_id", "${af.map.r3_id}");
						pager.addHiddenInputs("sync_state", "${af.map.sync_state}");
						pager.addHiddenInputs("vbedl_like", "${af.map.vbedl_like}");
						pager.addHiddenInputs("vbedl_null", "${af.map.vbedl_null}");
						pager.addHiddenInputs("order_type", "${af.map.order_type}");
						pager.addHiddenInputs("user_shop_name_like", "${af.map.user_shop_name_like}");
						pager.addHiddenInputs("sync_date_start", "${af.map.sync_date_start}");
						pager.addHiddenInputs("sync_date_end", "${af.map.sync_date_end}");
						document.write(pager.toString());
					</script></td>
        </tr>
      </table>
    </form>
    
    <div class="rtabcont1" style="overflow-x: auto;display:none; " id="divExcel_all" title="订单">
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
        <td width="5%" nowrap="nowrap" align="center">同步人</td>
        <td width="5%" nowrap="nowrap" align="center">同步日期</td>
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
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
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
          <fmt:formatNumber value="${cur.map.good_price*cur.map.good_count*cur.map.good_discount/100}" type="currency" />
            </span>
                        </td>
                           <td align="center" nowrap="nowrap">
                           <span class="kz-price-12">
          <fmt:formatNumber value="${cur.map.good_count*cur.map.good_price*(1+cur.map.good_discount/100)+cur.map.d_good_discount_price}" type="currency" />
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
          <td align="center" nowrap="nowrap">${cur.sync_user}</td>
          <td align="center" nowrap="nowrap">${fn:substring(cur.sync_date, 0, 11)}</td>
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
	$("#order_date_start").attr("dataType", "Require").attr("msg", "请选择下单开始日期！");
	$("#order_date_end").attr("dataType", "Require").attr("msg", "请选择下单结束日期！");

	if("${af.map.dept_type}" == "1"){
		$("#dept_id").attr({"subElement": "l4_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.dept_id}"});
		$("#l4_dept_id"    ).attr({"subElement": "l5_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
		$("#l5_dept_id" ).attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l5_dept_id}"});

		$("#dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "0", false);
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


$("#r3_id").blur(function(){
    var text =this.value;
    regx = /[^\n\d\r]/;
    if(regx.test(text)){
        alert('R3单号只允许输入数字');
        this.value="";
        }
    }) ;
    

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>