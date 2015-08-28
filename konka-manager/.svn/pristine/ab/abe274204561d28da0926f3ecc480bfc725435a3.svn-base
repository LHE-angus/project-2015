<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/tips/css/Popup.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
</head>
<body>
<div class="oarcont" style="position:relative;overflow:hidden;">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/zmd/KonkaXxZmdSearchSalesOrder">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <div id="condition_div" style="100%;overflow-x:auto;" >
        <table id="condition_table" width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable5">
          <tr>
            <td width="10%">&nbsp;<strong class="fb">专卖店编号：</strong></td>
            <td width="40%"><c:if test="${empty zmd_sn}">
            	<html-el:select property="zmd_id" styleId="zmd_id" multiple="multiple">
		            		<html-el:option value="">=请选择=</html-el:option>
		            		<c:forEach items="${zmdList}" var="cur">
		            			<html-el:option value="${cur.zmd_id}">${cur.zmd_sn}</html-el:option>
		            		</c:forEach>
		        </html-el:select>
              </c:if>
              <c:if test="${not empty zmd_sn}">
                <label style="color:blue;">${zmd_sn}</label>
              </c:if></td>
            <td width="10%"><strong class="fb">订单流水号： </strong></td>
            <td width="40%"><html-el:text property="sell_bill_id" styleId="sell_bill_id" size="20" maxlength="12" ></html-el:text></td>
          </tr>
          <tr>
            <td><strong class="fb">&nbsp;销售单状态：</strong></td>
            <td><html-el:select property="sell_state" styleId="sell_state"  onchange="this.form.submit();" style="width:154px;">
                <html-el:option value="">==请选择==</html-el:option>
<!--                <html-el:option value="0">未付款</html-el:option>-->
              <html-el:option value="10">待审核</html-el:option>
              <html-el:option value="1">审核中</html-el:option>
              <html-el:option value="20">已审核</html-el:option>
<!--                <html-el:option value="30">已发货</html-el:option>-->
<!--                <html-el:option value="70">交易成功</html-el:option>-->
              </html-el:select></td>
            <td><strong class="fb">消费者姓名：</strong></td>
            <td><html-el:text property="buyer_name_like" styleId="buyer_name_like" size="20" maxlength="20" ></html-el:text></td>
          </tr>
          <tr>
            <td><strong class="fb">&nbsp;开单人姓名：</strong></td>
            <td><html-el:text property="add_user_realname_like" styleId="add_user_realname_like" size="20" maxlength="20" ></html-el:text></td>
            <td><strong class="fb">开单日期：</strong></td>
            <td><html-el:text property="add_date_ge" styleId="add_date_ge" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
              至
              <html-el:text property="add_date_le" styleId="add_date_le" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
              &nbsp;
              <html-el:select property="timeScope" styleId="timeScope">
                <html-el:option value="">=请选择=</html-el:option>
                <html-el:option value="1">过去一周</html-el:option>
                <html-el:option value="2">过去一个月</html-el:option>
                <html-el:option value="3">过去三个月</html-el:option>
              </html-el:select></td>
          </tr>
          <tr>
            <td><strong class="fb">&nbsp;付款方式：</strong></td>
            <td><html-el:select property="pay_way" styleId="pay_way" onchange="this.form.submit();" style="width:154px;">
                <html-el:option value="">==请选择==</html-el:option>
                <html-el:option value="1">现金支付</html-el:option>
                <html-el:option value="2">POS机刷卡</html-el:option>
                <html-el:option value="3">货到付款</html-el:option>
              </html-el:select></td>
            <td colspan="2"><html-el:select property="stock_from" styleId="stock_from" onchange="this.form.submit();" style="width:154px;">
                <html-el:option value="">==库存来源==</html-el:option>
                <html-el:option value="0">专卖店库存</html-el:option>
                <html-el:option value="1">分公司库存</html-el:option>
              </html-el:select>
              <!-- 
              <html-el:select property="order_type" styleId="stock_from" onchange="this.form.submit();" style="width:154px;">
                <html-el:option value="">==订单分类==</html-el:option>
                <html-el:option value="1">销售单</html-el:option>
                <html-el:option value="2">进货单</html-el:option>
              </html-el:select>
               -->
              &nbsp;&nbsp;
              <input class="but1" type="button" id="btn_submit" name="Submit" value="搜索" /></td>
          </tr>
          <tr>
          	<c:if test="${af.map.dept_type eq 1}">
	            <td><strong class="fb">&nbsp;分公司：</strong></td>
	            <td>
	            	<html-el:select property="dept_id" styleId="dept_id">
		                <html-el:option value="">-请选择-</html-el:option>
		            </html-el:select>
		            <html-el:select property="l4_dept_id" styleId="l4_dept_id">
		                <html-el:option value="">-请选择-</html-el:option>
		            </html-el:select>
		            <html-el:select property="l5_dept_id" styleId="l5_dept_id">
		                <html-el:option value="">-请选择-</html-el:option>
		            </html-el:select>
	            </td>
            </c:if>
            <c:if test="${af.map.dept_type ne 1}">
	            <td><strong class="fb">&nbsp;</strong></td>
	            <td>&nbsp;</td>
            </c:if>
            <td colspan="2">
              &nbsp;&nbsp;
              </td>
          </tr>
        </table>
      </div>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <!-- %@ include file="/commons/pages/messages.jsp"%-->
    <!--  <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:-3px;margin-bottom:3px;">-->
    <!--    <tr>-->
    <!--      <td>-->
    <!--      	<input class="but2" type="submit" name="Submit2" value="新增" onclick="location.href='KonkaXxZmdAddSalesOrder.do?method=add&mod_id=${af.map.mod_id}&tree_param=${tree_param}';" /></td>-->
    <!--      <td>-->
       	<input name="button" type="button" class="but_excel" id="toExcel" value="导出" onclick="toExcel('divExcel', 'KonkaXxPd.do?method=toExcel');" />
    <!--        <span style="color: #ff0000">&nbsp;&nbsp;&nbsp;&nbsp;（注：默认导出全部数据,请设置导出数据条数！）</span>-->
    <!--      </td>-->
    <!--    </tr>-->
    <!--  </table>-->
    <div style="overflow:auto;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td align="center" nowrap="nowrap">订单流水号</td>
        <c:if test="${empty zmd_sn}">
          <td width="8%" align="center" nowrap="nowrap">专卖店编号</td>
        </c:if>
<!--        <td width="6%" align="center" nowrap="nowrap">销售单状态</td>-->
        <td width="6%" align="center" nowrap="nowrap">审核状态</td>
        <td width="8%" align="center" nowrap="nowrap">开单时间</td>
        <td width="5%" align="center" nowrap="nowrap">开单人</td>
        <td width="5%" align="center" nowrap="nowrap">收货人</td>
        <td width="5%" align="center" nowrap="nowrap">付款方式</td>
        <td width="10%" align="center" nowrap="nowrap">机型</td>
        <td width="10%" align="center" nowrap="nowrap">数量</td>
        <td width="10%" align="center" nowrap="nowrap">价格</td>
        <td width="10%" align="center" nowrap="nowrap">总额</td>
        <td width="8%" align="center" nowrap="nowrap">审核时间</td>
        <td width="8%" align="center" nowrap="nowrap">发货时间</td>
        <td width="8%" align="center" nowrap="nowrap">收货时间</td>
        <td width="18%" align="center" nowrap="nowrap">操作</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap"><a href="${ctx}/manager/zmd/KonkaXxZmdAddSalesOrder.do?method=view&sell_bill_id=${cur.sell_bill_id}&mod_id=${af.map.mod_id}"><font class="fblue">
            <c:out value="${fnx:leftPad_sis(cur.sell_bill_id, 12, '0')}" />
            </font></a></td>
          <c:if test="${empty zmd_sn}">
            <td align="left" nowrap="nowrap">${cur.zmd_sn}</td>
          </c:if>
<%--          <td align="center" nowrap="nowrap"><c:choose>--%>
<%--               <c:when test="${cur.sell_state eq 0}"><span style="color:#ccc;">未付款</span></c:when>--%>
<%--              <c:when test="${cur.sell_state eq 10}"><span style="color:red;">待审核</span></c:when>--%>
<%--               <c:when test="${cur.sell_state eq 20}">已审核</c:when>--%>
<%--               <c:when test="${cur.sell_state eq 21}">已审核不通过</c:when>--%>
<%--              <c:when test="${cur.sell_state eq 30}">已发货</c:when>--%>
<%--               <c:when test="${cur.sell_state eq 70}"><span style="color:green;">交易成功</span></c:when>--%>
<%--               <c:otherwise>-</c:otherwise>--%>
<%--             </c:choose></td>--%>
             <td align="center" nowrap="nowrap"><c:choose>
              <c:when test="${cur.sell_state eq 10 and cur.map.audit_state ne 1}"><span style="color:orange;">待审核</span></c:when>
              <c:when test="${cur.map.audit_state eq 1}">审核中</c:when>
              <c:when test="${cur.map.audit_state eq 3}"><span style="color:red;">已审核</span></c:when>
              <c:otherwise>-</c:otherwise>
            </c:choose></td>
          <td align="center" nowrap="nowrap" title="<fmt:formatDate value="${cur.add_date}" pattern="yyyy年MM月dd日 HH:mm:ss" />
          ">
          <fmt:formatDate value="${cur.add_date}" pattern="yy/MM/dd HH:mm" />
          <c:out value="${empty cur.add_date ? '-' : ''}" />
          </td>
          <td align="left" nowrap="nowrap">${cur.add_user_realname}</td>
          <td align="left" nowrap="nowrap">${cur.sell_rec_name}</td>
          <td align="center" nowrap="nowrap">${fn:split(' ,现金付款,POS机刷卡,货到付款', ',')[cur.pay_way]} </td>
           <td align="left" nowrap="nowrap">${cur.map.md_name}</td>
             
               <td align="left" nowrap="nowrap">${cur.map.counts}</td>
                <td align="left" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.map.price}" type="currency" />
            </span></td>
                <td align="left" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.map.price*cur.map.counts}" type="currency" />
            </span></td>
          <td align="center" nowrap="nowrap" title="<fmt:formatDate value="${cur.audit_date}" pattern="yyyy年MM月dd日 HH:mm:ss" />
          ">
          <fmt:formatDate value="${cur.audit_date}" pattern="yy/MM/dd HH:mm" />
          ${empty cur.audit_date ? '-' : ''}
          </td>
          <td align="center" nowrap="nowrap" title="<fmt:formatDate value="${cur.dist_date}" pattern="yyyy年MM月dd日 HH:mm:ss" />
          ">
          <fmt:formatDate value="${cur.dist_date}" pattern="yy/MM/dd HH:mm" />
          ${empty cur.dist_date ? '-' : ''}
          </td>
          <td align="center" nowrap="nowrap" title="<fmt:formatDate value="${cur.take_googs_date}" pattern="yyyy年MM月dd日 HH:mm:ss" />
          ">
          <fmt:formatDate value="${cur.take_googs_date}" pattern="yy/MM/dd HH:mm" />
          ${empty cur.take_googs_date ? '-' : ''}
          </td>
          <td align="center" nowrap="nowrap"><!-- <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxZmdAddSalesOrder.do', 'view','sell_bill_id=${cur.sell_bill_id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">查看</span> -->
            <c:choose>
              <c:when test="${cur.return_state eq 0 && (cur.sell_state le 10 || cur.sell_state eq 21) && role_id_eq_400}"> &nbsp;<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxZmdAddSalesOrder.do', 'edit', 'sell_bill_id=${cur.sell_bill_id}&mod_id=${af.map.mod_id}');$('#message_tip').show();">编辑</span> |&nbsp;<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxZmdSearchSalesOrder.do', 'jfEdit', 'sell_bill_id=${cur.sell_bill_id}&mod_id=${af.map.mod_id}');$('#message_tip').show();">调整积分</span> </c:when>
              <c:otherwise>&nbsp;<span style="color:#ccc">编辑</span>|&nbsp;<span style="color:#ccc">调整积分</span></c:otherwise>
            </c:choose>
            |&nbsp;<img src="${ctx}/images/print2.png" /> <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxPrintReceipt.do', 'showReceipt','sell_bill_id=${cur.sell_bill_id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">打印收款单</span></td>
        </tr>
      </c:forEach>
      <c:forEach begin="${fn:length(entityList)}" end="${af.map.pager.pageSize - 1}" step="1">
        <tr>
          <c:if test="${empty zmd_sn}">
            <td>&nbsp;</td>
          </c:if>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </c:forEach>
    </table>
    </div>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxZmdSearchSalesOrder.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id","${af.map.mod_id}");
			pager.addHiddenInputs("zmd_id", "${af.map.zmd_id}");	
            pager.addHiddenInputs("sell_bill_id", "${af.map.sell_bill_id}");
            pager.addHiddenInputs("add_date_ge", "${af.map.add_date_ge}");
            pager.addHiddenInputs("add_date_le", "${af.map.add_date_le}");
           pager.addHiddenInputs("sell_state", "${af.map.sell_state}");
			pager.addHiddenInputs("add_user_realname_like", "${fn:escapeXml(af.map.add_user_realname_like)}");
			pager.addHiddenInputs("buyer_name_like", "${fn:escapeXml(af.map.buyer_name_like)}");
			pager.addHiddenInputs("pay_way", "${af.map.pay_way}");
			pager.addHiddenInputs("stock_from", "${af.map.stock_from}");
			pager.addHiddenInputs("order_type", "${af.map.order_type}");
			if("${af.map.dept_type}" ==  "1"){
				pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
				pager.addHiddenInputs("l4_dept_id", "${af.map.l4_dept_id}");
				pager.addHiddenInputs("l5_dept_id", "${af.map.l5_dept_id}");
			}
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
  
  
  
  <div class="rtabcont1" style="overflow-x: auto;display:none; " id="divExcel" title="专卖店销售单">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td align="center" nowrap="nowrap">订单流水号</td>
        <c:if test="${empty zmd_sn}">
          <td width="8%" align="center" nowrap="nowrap">专卖店编号</td>
        </c:if>
        <td width="6%" align="center" nowrap="nowrap">销售单状态</td>
        <td width="8%" align="center" nowrap="nowrap">开单时间</td>
        <td width="5%" align="center" nowrap="nowrap">开单人</td>
        <td width="5%" align="center" nowrap="nowrap">收货人</td>
        <td width="5%" align="center" nowrap="nowrap">付款方式</td>
        <td width="10%" align="center" nowrap="nowrap">机型</td>
        <td width="10%" align="center" nowrap="nowrap">数量</td>
        <td width="10%" align="center" nowrap="nowrap">价格</td>
        <td width="10%" align="center" nowrap="nowrap">总额</td>
        <td width="8%" align="center" nowrap="nowrap">审核时间</td>
        <td width="8%" align="center" nowrap="nowrap">发货时间</td>
        <td width="8%" align="center" nowrap="nowrap">收货时间</td>
        <td width="18%" align="center" nowrap="nowrap">操作</td>
      </tr>
      <c:forEach var="cur" items="${entityListforExcel}" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap"><a href="${ctx}/manager/zmd/KonkaXxZmdAddSalesOrder.do?method=view&sell_bill_id=${cur.sell_bill_id}&mod_id=${af.map.mod_id}"><font class="fblue">
            <c:out value="${fnx:leftPad_sis(cur.sell_bill_id, 12, '0')}" />
            </font></a></td>
          <c:if test="${empty zmd_sn}">
            <td align="left" nowrap="nowrap">${cur.zmd_sn}</td>
          </c:if>
          <td align="center" nowrap="nowrap"><c:choose>
              <c:when test="${cur.sell_state eq 0}"><span style="color:#ccc;">未付款</span></c:when>
              <c:when test="${cur.sell_state eq 10}"><span style="color:red;">待审核</span></c:when>
              <c:when test="${cur.sell_state eq 20}">已审核</c:when>
              <c:when test="${cur.sell_state eq 21}">已审核不通过</c:when>
              <c:when test="${cur.sell_state eq 30}">已发货</c:when>
              <c:when test="${cur.sell_state eq 70}"><span style="color:green;">交易成功</span></c:when>
              <c:otherwise>-</c:otherwise>
            </c:choose></td>
          <td align="center" nowrap="nowrap" title="<fmt:formatDate value="${cur.add_date}" pattern="yyyy年MM月dd日 HH:mm:ss" />
          ">
          <fmt:formatDate value="${cur.add_date}" pattern="yy/MM/dd HH:mm" />
          <c:out value="${empty cur.add_date ? '-' : ''}" />
          </td>
          <td align="left" nowrap="nowrap">${cur.add_user_realname}</td>
          <td align="left" nowrap="nowrap">${cur.sell_rec_name}</td>
          <td align="center" nowrap="nowrap">${fn:split(' ,现金付款,POS机刷卡,货到付款', ',')[cur.pay_way]} </td>
           <td align="left" nowrap="nowrap">${cur.map.md_name}</td>
             
               <td align="left" nowrap="nowrap">${cur.map.counts}</td>
                <td align="left" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.map.price}" type="currency" />
            </span></td>
                <td align="left" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.map.price*cur.map.counts}" type="currency" />
            </span></td>
          <td align="center" nowrap="nowrap" title="<fmt:formatDate value="${cur.audit_date}" pattern="yyyy年MM月dd日 HH:mm:ss" />
          ">
          <fmt:formatDate value="${cur.audit_date}" pattern="yy/MM/dd HH:mm" />
          ${empty cur.audit_date ? '-' : ''}
          </td>
          <td align="center" nowrap="nowrap" title="<fmt:formatDate value="${cur.dist_date}" pattern="yyyy年MM月dd日 HH:mm:ss" />
          ">
          <fmt:formatDate value="${cur.dist_date}" pattern="yy/MM/dd HH:mm" />
          ${empty cur.dist_date ? '-' : ''}
          </td>
          <td align="center" nowrap="nowrap" title="<fmt:formatDate value="${cur.take_googs_date}" pattern="yyyy年MM月dd日 HH:mm:ss" />
          ">
          <fmt:formatDate value="${cur.take_googs_date}" pattern="yy/MM/dd HH:mm" />
          ${empty cur.take_googs_date ? '-' : ''}
          </td>
          <td align="center" nowrap="nowrap">
          <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxZmdAddSalesOrder.do', 'delete','sell_bill_id=${cur.sell_bill_id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">删除</span>
            <c:choose>
              <c:when test="${cur.return_state eq 0 && (cur.sell_state le 10 || cur.sell_state eq 21) && role_id_eq_400}"> &nbsp;<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxZmdAddSalesOrder.do', 'edit', 'sell_bill_id=${cur.sell_bill_id}&mod_id=${af.map.mod_id}');$('#message_tip').show();">编辑</span> |&nbsp;<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxZmdSearchSalesOrder.do', 'jfEdit', 'sell_bill_id=${cur.sell_bill_id}&mod_id=${af.map.mod_id}');$('#message_tip').show();">调整积分</span> </c:when>
              <c:otherwise>&nbsp;<span style="color:#ccc">编辑</span>|&nbsp;<span style="color:#ccc">调整积分</span></c:otherwise>
            </c:choose>
            |&nbsp;<img src="${ctx}/images/print2.png" /> <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxPrintReceipt.do', 'showReceipt','sell_bill_id=${cur.sell_bill_id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">打印收款单</span></td>
        </tr>
      </c:forEach>
    </table>
    </div>
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  <div class="clear"></div>
  <div id="message_tip" style="display:none;">
    <div class="ui-overlay">
      <div class="ui-widget-overlay"></div>
      <div class="ui-widget-shadow ui-corner-all" style="width:302px;height:152px;position:absolute;left:35%;top:25%"></div>
    </div>
    <div style="position:absolute;width:280px;height:130px;left:35%;top:25%;padding:10px;" class="ui-widget ui-widget-content ui-corner-all"> <span><img id="loading" src="${ctx}/images/loading.gif" style="display:none;" /><img src="${ctx}/images/search_wangwang.gif" />&nbsp;正在查询，请稍等...</span> </div>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/styles/tips/js/tips.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#add_date_ge").datepicker();
	$("#add_date_le").datepicker();

	$("#zmd_id").multiselect({
		noneSelectedText: '=请选择=',
		selectedList: 1,
		multiple: false,
		minWidth:200
	}).multiselectfilter();
	
	$("#add_date_ge").attr("dataType", "Require").attr("msg", "请选择开单起始时间！");
	$("#add_date_le").attr("dataType", "Require").attr("msg", "请选择开单结束时间！");
	
	if("${af.map.dept_type}" == "1"){
		$("#dept_id").attr({"subElement": "l4_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.dept_id}"});
		$("#l4_dept_id"    ).attr({"subElement": "l5_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
		$("#l5_dept_id" ).attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l5_dept_id}"});

		$("#dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "0", false);
		$("#dept_id").change();
	}
	
	$("#timeScope").change(function(){
		var today = new Date();
		today = today.valueOf();
		var yes = today - 1 * 1000 * 60 * 60 * 24; //昨天
		yes = new Date(yes).format('yyyy-MM-dd');
		var yesVal = new Date(yes).valueOf();
		
		if ($(this).val() == 1) {
			var lastWeek = yesVal - 6 * 1000 * 60 * 60 * 24; //过去一周
			lastWeek = new Date(lastWeek).format('yyyy-MM-dd');
			$("#add_date_ge").val(lastWeek);
			$("#add_date_le").val(yes);
		} else if ($(this).val() == 2) {
			var lastMonth = yesVal - 30 * 1000 * 60 * 60 * 24; //过去一个月
			lastMonth = new Date(lastMonth).format('yyyy-MM-dd');
			$("#add_date_ge").val(lastMonth);
			$("#add_date_le").val(yes);
		} else if ($(this).val() == 3) {
			var lastThreeMonth = yesVal - 91 * 1000 * 60 * 60 * 24; //过去三个月
			lastThreeMonth = new Date(lastThreeMonth).format('yyyy-MM-dd');
			$("#add_date_ge").val(lastThreeMonth);
			$("#add_date_le").val(yes);
		}
	});
	
	$("#btn_submit").click(function(){
		var s = $("#add_date_ge").val();
		var e = $("#add_date_le").val();
		if($.trim(s).length > 0 && $.trim(e).length > 0){
			var d_d = (new Date(e.replace("-", "/")).getTime() - new Date(s.replace("-", "/")).getTime()) / 1000 / 60 / 60 / 24;
			if (s > e) {
				alert("开始时间不得大于结束时间！");
				return false;
			}
			if (d_d > 91) {
				alert("查询时间范围不能超过3个月！");
				return false;
			}
		}
		if($.trim(s).length == 0 || $.trim(e).length == 0){
			alert("请选择时间范围！");
			return false;
		}
		this.form.submit();
	});
});

Date.prototype.format = function(format)    {        
	var o = {        
			"M+" : this.getMonth()+1, //month        
			"d+" : this.getDate(),    //day        
			"h+" : this.getHours(),   //hour        
			"m+" : this.getMinutes(), //minute        
			"s+" : this.getSeconds(), //second        
			"q+" : Math.floor((this.getMonth()+3)/3),  //quarter       
			"S" : this.getMilliseconds() //millisecond        
			}        
	if(/(y+)/.test(format)) format=format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));        
	for(var k in o)if(new RegExp("("+ k +")").test(format))        
	format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k]:("00"+ o[k]).substr((""+ o[k]).length));return format;
}





//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>