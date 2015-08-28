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
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/manager/KonkaOrderInfoTrans">
      <html-el:hidden property="method" styleId="method" value="listForFHD" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td nowrap="nowrap">
          &nbsp;&nbsp;<strong class="fb">签收状态：</strong>
          	<html-el:select property="trans_ensu_status" style="width:90px;">
          		<html-el:option value="">--请选择--</html-el:option>
          		<html-el:option value="0">未签收</html-el:option>
          		<html-el:option value="1">部分签收</html-el:option>
          		<html-el:option value="2">已签发</html-el:option>
          		<html-el:option value="3">全部拒收</html-el:option>
          	</html-el:select>
          &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">签收方式：</strong>
          	<html-el:select property="trans_ensu_type" style="width:90px;">
          		<html-el:option value="">--请选择--</html-el:option>
          		<html-el:option value="0">客户签收</html-el:option>
          		<html-el:option value="1">管理端代签</html-el:option>
          		<html-el:option value="2">二维码签收</html-el:option>
          	</html-el:select>
           &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">发货单号：</strong>
          	<html-el:text property="trans_index_like" style="width:150px;" maxlength="30" styleId="trans_index_like" styleClass="webinput" />
           &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">R3物流单号：</strong>
          <html-el:text property="r3_vbedl_like" style="width:90px;" maxlength="20" styleId="r3_vbedl_like" styleClass="webinput" />
          </td>
          </tr>
          <tr>
          <td nowrap="nowrap">
	          &nbsp;&nbsp;<strong class="fb">签收日期：</strong>
	          <input name="trans_ensu_date_s" id="trans_ensu_date_s" size="12" value="${af.map.trans_ensu_date_s}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'2013-11-01',maxDate:'#F{$dp.$D(\'trans_ensu_date_e\')}'})" />
	            	    至
	          <input name="trans_ensu_date_e" id="trans_ensu_date_e" size="12"  value="${af.map.trans_ensu_date_e}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'#F{$dp.$D(\'trans_ensu_date_s\')||\'2013-11-01\'}'})" />
	          &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">客户名称：</strong>
	          <html-el:text property="customer_name_like" style="width:150px;" maxlength="60" styleId="customer_name_like" styleClass="webinput" />
	          &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">R3客户编码：</strong>
	          <html-el:text property="r3_code_like" size="20" style="width:90px;" maxlength="20" styleId="r3_code_like" styleClass="webinput" />
	          &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">送达方：</strong>
	          <html-el:text property="r3_code_sdf_like" size="20" style="width:90px;" maxlength="20" styleId="r3_code_sdf_like" styleClass="webinput" />
          </td>
          </tr>
          <tr>
          <td nowrap="nowrap">
	          &nbsp;&nbsp;<strong class="fb">承运单位：</strong>
	          <html-el:text property="trans_unit_like" style="width:90px;" maxlength="60" styleId="trans_unit_like" styleClass="webinput" />
	          &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">司机姓名：</strong>
	          <html-el:text property="link_name_like" style="width:90px;" maxlength="60" styleId="link_name_like" styleClass="webinput" />
	          &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">司机电话：</strong>
	          <html-el:text property="link_phone_like" style="width:90px;" maxlength="30" styleId="link_phone_like" styleClass="webinput" />
	          &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">配送方式：</strong>
		          <html-el:select property="trans_mode" style="width:90px;">
	          		<html-el:option value="">--请选择--</html-el:option>
	          		<html-el:option value="0">自有物流</html-el:option>
	          		<html-el:option value="1">第三方物流</html-el:option>
	          		<html-el:option value="2">其他</html-el:option>
	          	 </html-el:select>
	          &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">发货状态：</strong>
		          <html-el:select property="trans_detail_status" style="width:90px;">
	          		<html-el:option value="">--请选择--</html-el:option>
	          		<html-el:option value="0">已发货</html-el:option>
	          		<html-el:option value="1">已结案</html-el:option>
	          	 </html-el:select>
          </td>
          </tr>
          <tr>
          <td nowrap="nowrap">
	          &nbsp;&nbsp;<strong class="fb">收货人姓名：</strong>
	          <html-el:text property="trans_recl_user_like" style="width:90px;" maxlength="60" styleId="trans_recl_user_like" styleClass="webinput" />
	          &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">收货人电话：</strong>
	          <html-el:text property="trans_recl_user_phone_like" style="width:90px;" maxlength="20" styleId="trans_recl_user_phone_like" styleClass="webinput" />
	          &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">收货人地址：</strong>
	          <html-el:text property="trans_recl_addr_like" style="width:90px;" maxlength="60" styleId="trans_recl_addr_like" styleClass="webinput" />
	          &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">打印状态：</strong>
		          <html-el:select property="is_print" style="width:90px;">
	          		<html-el:option value="">--请选择--</html-el:option>
	          		<html-el:option value="0">未打印</html-el:option>
	          		<html-el:option value="1">已打印</html-el:option>
	          	 </html-el:select>
	          &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<input class="but1" type="submit" name="Submit" value="搜索" />
	          
          </td>
          </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
  <html-el:form action="/manager/KonkaOrderInfoTran">
  <input type="hidden" name="method" id="method" value="delete" />
  <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>
<%--         	<input class="but2" type="button" name="Submit2" value="批量结案" onclick="location.href='KonkaOrderInfoTrans.do?method=add&mod_id=${af.map.mod_id}';" /> --%>
<!--         	<input class="but3" type="button" name=button3 id="button3" style="width:67px;" value="批量删除" onClick="confirmDeleteAll(this.form);" /> -->
<%--         	<input class="but4" type="button" name="Submit4" value="批量打印" onclick="location.href='KonkaOrderInfoTrans.do?method=add&mod_id=${af.map.mod_id}';" /> --%>
<%--         	<input class="but5" type="button" name="Submit5" value="新增" onclick="location.href='KonkaOrderInfoTrans.do?method=add&mod_id=${af.map.mod_id}';" /> --%>
<!--         	<input type="button" class="but_excel" onClick="doNeedMethod(null, 'KonkaOrderInfoTrans.do', 'list', 'export=true&' + $('#bottomPageForm').serialize())" value="导出" /> -->
<!--           <span style="color:#F00;">注：请先搜索再导出，因数据量过大会导致系统反应缓慢或宕机，因此这些数据将不作导出。</span>  -->
        </td>
      </tr>
    </table>
  	<div style="overflow-x:auto;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
<!--       	<td width="3%" align="center" nowrap="nowrap"> -->
<!--         <input name="chkAll" type="checkbox" id="chkAll" value="-1" onClick="checkAll(this);" /> -->
<!--         </td> -->
         <td width="8%" nowrap="nowrap" align="center">操作</td>
        <td width="5%" nowrap="nowrap" align="center">序号</td>
        <td width="15%" align="center" nowrap="nowrap">发货日期</td>
        <td width="10%" align="center" nowrap="nowrap">发货单号</td>
        <td width="5%" nowrap="nowrap" align="center">R3物流单号</td>
        <td width="15%" nowrap="nowrap" align="center">客户名称</td>
        <td width="8%" nowrap="nowrap" align="center">R3客户编码</td>
        <td width="8%" nowrap="nowrap" align="center">送达方编码</td>
        <td width="8%" nowrap="nowrap" align="center">数量</td>
        <td width="8%" nowrap="nowrap" align="center">金额</td>
        <td width="8%" nowrap="nowrap" align="center">发货数量</td>
        <td width="8%" nowrap="nowrap" align="center">发货状态</td>
        <td width="8%" nowrap="nowrap" align="center">承运单位</td>
        <td width="8%" nowrap="nowrap" align="center">司机姓名</td>
        <td width="8%" nowrap="nowrap" align="center">司机电话</td>
        <td width="8%" nowrap="nowrap" align="center">签收数量</td>
        <td width="8%" nowrap="nowrap" align="center">签收状态</td>
        <td width="8%" nowrap="nowrap" align="center">签收时间</td>
        <td width="8%" nowrap="nowrap" align="center">签收方式</td>
        <td width="8%" nowrap="nowrap" align="center">配送方式</td>
        <td width="8%" nowrap="nowrap" align="center">打印状态</td>
        <td width="8%" nowrap="nowrap" align="center">收货人姓名</td>
        <td width="8%" nowrap="nowrap" align="center">收货人手机</td>
        <td width="8%" nowrap="nowrap" align="center">收货人地址</td>
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap"> 
            <c:if test="${cur.map.trans_status ne 3}">
            	<c:if test="${cur.map.trans_ensu_status ne 2 }">
            		<span style="cursor:pointer;" class="fblue" onClick="doNeedMethod(null, 'KonkaOrderInfoTrans.do','confirm','&trans_index_detail=${cur.map.trans_index_detail}&mod_id=${af.map.mod_id }')">确认</span>
            	</c:if>
            	<c:if test="${cur.map.trans_ensu_status eq 2 }">
            		<span title="已经确认全部收货，不能重复确认">确认 </span>
           		</c:if>
 			</c:if>
            </td>
            <td align="center" nowrap="nowrap"> ${vs.count}</td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.map.trans_date }" pattern="yyyy-MM-dd"/></td>
            <td align="center" nowrap="nowrap">${cur.map.trans_index_detail}</td>
            <td align="left" nowrap="nowrap">${cur.map.r3_vbedl}</td>
            <td align="left" nowrap="nowrap">${cur.map.customer_name}</td>
            <td align="left" nowrap="nowrap">${cur.map.r3_code}</td>
            <td align="left" nowrap="nowrap">${cur.map.we}</td>
            <td align="right" nowrap="nowrap">${cur.map.model_num}</td>
            <td align="right" nowrap="nowrap">${cur.map.model_money}</td>
            <td align="right" nowrap="nowrap">${cur.map.sum_trans_num}</td>
            <td align="center" nowrap="nowrap">
				<c:if test="${cur.map.trans_detail_status eq 0}">已发货</c:if>
	            <c:if test="${cur.map.trans_detail_status eq 1}"><font color="red">已结案</font></c:if>
			</td>
            <td align="left" nowrap="nowrap">${cur.map.trans_unit}</td>
            <td align="left" nowrap="nowrap">${cur.map.link_name}</td>
            <td align="left" nowrap="nowrap">${cur.map.link_phone}</td>
            <td align="right" nowrap="nowrap">${cur.map.trans_ensured_num}</td>
            <td align="center" nowrap="nowrap">
            	<c:if test="${cur.map.trans_ensu_status eq 0}">未签收</c:if>
	            <c:if test="${cur.map.trans_ensu_status eq 1}">部分签收 </c:if>
	            <c:if test="${cur.map.trans_ensu_status eq 2}">确认收货 </c:if>
	            <c:if test="${cur.map.trans_ensu_status eq 3}">全部拒收 </c:if>
            </td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.map.trans_ensu_date}" pattern="yyyy-MM-dd"/></td>
            <td align="center" nowrap="nowrap">
            	<c:if test="${cur.map.trans_ensu_type eq 0}">客户签收</c:if>
	            <c:if test="${cur.map.trans_ensu_type eq 1}">管理端代签</c:if>
	            <c:if test="${cur.map.trans_ensu_type eq 2}">二维码签收 </c:if>
            </td>
            <td align="center" nowrap="nowrap">
            	<c:if test="${cur.map.trans_mode eq 0}">自有物流</c:if>
	            <c:if test="${cur.map.trans_mode eq 1}">第三方物流 </c:if>
	            <c:if test="${cur.map.trans_mode eq 2}">其他 </c:if>
            </td>
            <td align="center" nowrap="nowrap">
            	<c:if test="${cur.map.is_print eq 0}">未打印</c:if>
	            <c:if test="${cur.map.is_print eq 1}">已打印 </c:if>
            </td>
            <td align="left" nowrap="nowrap">${cur.map.trans_recl_user}</td>
            <td align="left" nowrap="nowrap">${cur.map.trans_recl_user_phone}</td>
            <td align="left" nowrap="nowrap">${cur.map.trans_recl_addr}</td>
          </tr>
          <c:if test="${vs.last eq true}">
            <c:set var="i" value="${vs.count}" />
          </c:if>
        </c:forEach>
        <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
          <tr align="center">
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
      </tbody>
    </table>
    </div>
    </html-el:form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaOrderInfoTrans.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align:right; padding-right:5px;"> 
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
              <script type="text/javascript">
							var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
							pager.addHiddenInputs("method", "listForFHD");
							pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
							pager.addHiddenInputs("trans_ensu_status", "${fn:escapeXml(af.map.trans_ensu_status)}");
							pager.addHiddenInputs("trans_ensu_type", "${fn:escapeXml(af.map.trans_ensu_type)}");
							pager.addHiddenInputs("trans_index_like", "${af.map.trans_index_like}");
							pager.addHiddenInputs("r3_vbedl_like", "${af.map.r3_vbedl_like}");
							pager.addHiddenInputs("trans_ensu_date_s", "${af.map.trans_ensu_date_s}");
							pager.addHiddenInputs("trans_ensu_date_e", "${af.map.trans_ensu_date_e}");
							pager.addHiddenInputs("customer_name_like", "${af.map.customer_name_like}");
							pager.addHiddenInputs("r3_code_like", "${af.map.r3_code_like}");
							pager.addHiddenInputs("r3_code_sdf_like", "${af.map.r3_code_sdf_like}");
							pager.addHiddenInputs("trans_unit_like", "${af.map.trans_unit_like}");
							pager.addHiddenInputs("link_name_like", "${af.map.link_name_like}");
							pager.addHiddenInputs("link_phone_like", "${af.map.link_phone_like}");
							pager.addHiddenInputs("trans_mode", "${fn:escapeXml(af.map.trans_mode)}");
							pager.addHiddenInputs("trans_recl_user_like", "${af.map.trans_recl_user_like}");
							pager.addHiddenInputs("trans_recl_user_phone_like", "${af.map.trans_recl_user_phone_like}");
							pager.addHiddenInputs("trans_recl_addr_like", "${af.map.trans_recl_addr_like}");
							pager.addHiddenInputs("trans_detail_status", "${af.map.trans_detail_status}");
							pager.addHiddenInputs("is_print", "${af.map.is_print}");
							document.write(pager.toString());
						</script> 
            </div></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>  
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
<script type="text/javascript">//<![CDATA[

//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>