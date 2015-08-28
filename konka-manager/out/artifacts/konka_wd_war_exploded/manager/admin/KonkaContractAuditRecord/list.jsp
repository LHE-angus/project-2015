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
      <!--  <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td> --> 
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/KonkaContractAuditRecord">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
       <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td nowrap="nowrap">&nbsp;&nbsp;
          <strong class="fb">分公司：</strong>
        		<html-el:select property="dept_id" styleId="dept_id" >
                <html-el:option value="">请选择...</html-el:option>
                <c:forEach var="cur" items="${sybDeptInfoList}">
                  <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
                </c:forEach>
              </html-el:select>
           <strong class="fb">&nbsp;&nbsp;客户名称：</strong>
            <html-el:text property="customer_name_like" styleId="customer_name_like" size="20" maxlength="20" />
            &nbsp;&nbsp;<strong class="fb">R3编码：</strong>
             <html-el:text property="r3_code_like" styleId="customer_name_like" size="20" maxlength="20" />
            </td></tr>
            <tr><td>
            &nbsp;&nbsp;&nbsp;<strong class="fb">签订时间：</strong>
        		<html-el:text property="qd_time_start" styleId="qd_time_start"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
				至
				<html-el:text property="qd_time_end" styleId="qd_time_end"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
        		&nbsp;&nbsp;<!--<strong class="fb">合同名称：</strong>  -->
        		<!--<html-el:text property="con_name_like" styleId="con_name_like" size="20" maxlength="20" />  --> 
        		 &nbsp;&nbsp;<strong class="fb">合同编码：</strong>
        		 <html-el:text property="con_sn_like" styleId="con_sn_like" size="20" maxlength="20" />
        	</td>
        </tr>
        <tr><td>
            &nbsp;&nbsp;&nbsp;<strong class="fb">业务类型：</strong>
            <html-el:select property="c_type" styleId="c_type" >
                <html-el:option value="">请选择...</html-el:option>
                <html-el:option value="1">加价</html-el:option>
                <html-el:option value="2">倒扣</html-el:option>
              </html-el:select>
        		&nbsp;&nbsp;<strong class="fb">审核状态：</strong>
        		<html-el:select property="audit_state" styleId="audit_state" >
        		  <html-el:option value="">请选择...</html-el:option>
        		 <html-el:option value="1">未审核</html-el:option>
        		 <html-el:option value="2">审核通过</html-el:option>
        		 <html-el:option value="3">驳回</html-el:option>
              </html-el:select>&nbsp;&nbsp;&nbsp;&nbsp;
              <strong class="fb">业务员：</strong>
        		 <html-el:text property="ywy_name_like" styleId="ywy_name_like" size="20" maxlength="20" />
        	</td>
        </tr>
        <tr><td>
            &nbsp;&nbsp;&nbsp;<strong class="fb">合同时间起止：</strong>
        		<html-el:text property="ht_time_start" styleId="ht_time_start"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
				至
				<html-el:text property="ht_time_end" styleId="ht_time_end"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
        		&nbsp;&nbsp;<strong class="fb">创建日期：</strong>
        		<html-el:text property="add_time_start" styleId="add_time_start"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
				至
				<html-el:text property="add_time_end" styleId="add_time_end"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
        		&nbsp;&nbsp;<strong class="fb">制单人：</strong>
        		 <html-el:text property="add_user_name_like" styleId="add_user_name_like" size="20" maxlength="20" />&nbsp;&nbsp;&nbsp;&nbsp;
        		<input class="but1" type="submit" name="Submit" value="搜索" />
        	</td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>
          <input class="but3" type="submit" name="Submit3" value="删除" onclick="confirmDeleteAll(document.getElementById('listForm'));" />
          <input class="but_excel" type="button" name="export_excel" id="export_excel" value="导出" />
        </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1" style="overflow-x:auto; ">
    <form id="listForm" name="listForm" method="post" action="KonkaContractManager.do?method=delete">
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
        <tr class="tabtt1">
          <td width="5%" align="center"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
          <td width="5%" nowrap="nowrap">序号</td>
          <!-- <td width="15%" nowrap="nowrap">合同名称</td> -->
          <td width="10%" nowrap="nowrap">合同编码</td>
          <td width="10%" nowrap="nowrap" align="center">分公司</td>
          <td width="10%" nowrap="nowrap" align="center">客户名称</td>
          <td width="10%" nowrap="nowrap" align="center">R3编码</td>
           <td width="10%" nowrap="nowrap" align="center">签订日期</td>
          <td width="10%" nowrap="nowrap" align="center">合同期限起</td>
          <td width="10%" nowrap="nowrap" align="center">合同期限止</td>
          <td width="10%" nowrap="nowrap" align="center">合同基本目标</td>
          <td width="10%" nowrap="nowrap" align="center">合同挑战目标</td>
          <td width="10%" nowrap="nowrap" align="center">业务类型</td>
          <td width="10%" nowrap="nowrap" align="center">规模返利</td>
          <td width="10%" nowrap="nowrap" align="center">月返</td>
          <td width="10%" nowrap="nowrap" align="center">特价机点位</td>
          <td width="10%" nowrap="nowrap" align="center">工程机点位</td>
          <td width="10%" nowrap="nowrap" align="center">其他返利</td>
          <td width="10%" nowrap="nowrap" align="center">审核状态</td>
          <td width="10%" nowrap="nowrap" align="center">创建日期</td> 
          <td width="10%" nowrap="nowrap" align="center">制单人</td> 
          <td width="10%" nowrap="nowrap" align="center">业务员</td>   
          <td width="15%" nowrap="nowrap" align="center">操作</td>
        </tr>    
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="center" nowrap="nowrap" >
                <c:if test="${cur.audit_state ne 2}">
                <input name="pks" type="checkbox" id="pks_${cur.id}" value="${cur.id}" />
                </c:if>
              </td>
              <td align="center" nowrap="nowrap" >${vs.count}</td>
              <!--<td align="left" nowrap="nowrap" ><span style="cursor:pointer;color: blue;" class="fblue" onclick="doNeedMethod(null, 'KonkaContractManager.do', 'view', 'id=${cur.id}&mod_id=${af.map.mod_id }&' + $('#bottomPageForm').serialize())">${cur.con_name}</span></td>  -->
              <td align="left" nowrap="nowrap" ><span style="cursor:pointer;color: blue;" class="fblue" onclick="doNeedMethod(null, 'KonkaContractManager.do', 'view', 'id=${cur.id}&mod_id=${af.map.mod_id }&' + $('#bottomPageForm').serialize())">${fn:escapeXml(cur.con_sn)}</span></td>
              <td align="left" nowrap="nowrap">${cur.dept_name}</td>
              <td align="left" nowrap="nowrap">${cur.customer_name}</td>
              <td align="left" nowrap="nowrap">${cur.r3_code}</td>
              <td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.sign_date}" pattern="yyyy-MM-dd " /></td>
              <td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.s_date}" pattern="yyyy-MM-dd " /></td>
              <td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.e_date}" pattern="yyyy-MM-dd " /></td>
              <td align="left" nowrap="nowrap">${cur.con_base_money}</td>
              <td align="left" nowrap="nowrap">${cur.con_expect_money}</td>
              <td align="left" nowrap="nowrap">
              <c:if test="${cur.c_type eq 1}">加价</c:if>
              <c:if test="${cur.c_type eq 2}">倒扣</c:if>
              </td>
              <td align="left" nowrap="nowrap">${cur.scale_sb}%</td>
              <td align="left" nowrap="nowrap">${cur.month_sb}%</td>
              <td align="left" nowrap="nowrap">${cur.tjj_money}%</td>
              <td align="left" nowrap="nowrap">${cur.gcj_money}%</td>
              <td align="left" nowrap="nowrap">${cur.other_sb}%</td>
              <td align="left" nowrap="nowrap"><c:if test="${cur.audit_state eq 1}">未审核</c:if>
              <c:if test="${cur.audit_state eq 2}">审核通过</c:if>
              <c:if test="${cur.audit_state eq 3}">驳回</c:if>
              </td>
              <td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd " /></td>
              <td align="left" nowrap="nowrap">${cur.add_user_name}</td>
              <td align="left" nowrap="nowrap">${cur.ywy_name}</td>
              <td align="center" nowrap="nowrap">
              <c:if test="${cur.audit_state ne 2}">
             <span style="cursor: pointer;color: blue;" onclick="doNeedMethod('您确定审核通过吗?', 'KonkaContractAuditRecord.do', 'audit','id=${cur.id}&' + $('#bottomPageForm').serialize())">审核通过</span>
             </c:if>
             <c:if test="${cur.audit_state eq 2}">
             <span style="color:#ccc;">审核通过</span>
             </c:if>
             |
             <c:if test="${cur.audit_state ne 2}">
              <span style="cursor: pointer;color: blue;" onclick="doNeedMethod('您确定驳回吗?', 'KonkaContractAuditRecord.do', 'back','id=${cur.id}&'+$('#bottomPageForm').serialize())">驳回</span>
             </c:if> 
             <c:if test="${cur.audit_state eq 2}">
             <span style="color:#ccc;">驳回</span>
             </c:if>
             |
              <span style="cursor: pointer;color: blue;" onclick="doNeedMethod(null, 'KonkaContractAuditRecord.do', 'auditList','id=${cur.id}&'+$('#bottomPageForm').serialize())">审核记录</span>
              </td>
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
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </form>
     </div>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaContractAuditRecord.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
			var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
			pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
			pager.addHiddenInputs("add_time_start", "${af.map.add_time_start}");
			pager.addHiddenInputs("add_time_end", "${af.map.add_time_end}");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("customer_name_like", "${fn:escapeXml(af.map.customer_name_like)}");
			pager.addHiddenInputs("r3_code_like", "${fn:escapeXml(af.map.r3_code_like)}");
			pager.addHiddenInputs("qd_time_start", "${fn:escapeXml(af.map.qd_time_start)}");
			pager.addHiddenInputs("qd_time_end", "${fn:escapeXml(af.map.qd_time_end)}");
			pager.addHiddenInputs("con_name_like", "${fn:escapeXml(af.map.con_name_like)}");
			pager.addHiddenInputs("con_sn_like", "${fn:escapeXml(af.map.con_sn_like)}");
			pager.addHiddenInputs("c_type", "${fn:escapeXml(af.map.c_type)}");
			pager.addHiddenInputs("audit_state", "${fn:escapeXml(af.map.audit_state)}");
			pager.addHiddenInputs("ywy_name_like", "${fn:escapeXml(af.map.ywy_name_like)}");
			pager.addHiddenInputs("ht_time_start", "${fn:escapeXml(af.map.ht_time_start)}");
			pager.addHiddenInputs("ht_time_end", "${fn:escapeXml(af.map.ht_time_end)}");
			pager.addHiddenInputs("add_user_name_like", "${fn:escapeXml(af.map.add_user_name_like)}");
			document.write(pager.toString());
            </script>
           </td>
        </tr>
      </table>
    </form>
    <div class="rtabcont1" style="overflow-x: auto; display: none;" id="divExcel_all" title="合同管理申报明细">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
          <td width="5%" nowrap="nowrap">序号</td>
          <td width="15%" nowrap="nowrap">合同名称</td>
          <td width="10%" nowrap="nowrap">合同编号</td>
          <td width="10%" nowrap="nowrap" align="center">分公司</td>
          <td width="10%" nowrap="nowrap" align="center">客户名称</td>
          <td width="10%" nowrap="nowrap" align="center">R3编码</td>
          <td width="10%" nowrap="nowrap" align="center">合同期限起</td>
          <td width="10%" nowrap="nowrap" align="center">合同期限止</td>
          <td width="10%" nowrap="nowrap" align="center">合同基本目标</td>
          <td width="10%" nowrap="nowrap" align="center">合同挑战目标</td>
          <td width="10%" nowrap="nowrap" align="center">业务类型</td>
          <td width="10%" nowrap="nowrap" align="center">规模返利</td>
          <td width="10%" nowrap="nowrap" align="center">月返</td>
          <td width="10%" nowrap="nowrap" align="center">特价机点位</td>
          <td width="10%" nowrap="nowrap" align="center">工程机点位</td>
          <td width="10%" nowrap="nowrap" align="center">其他返利</td>
          <td width="10%" nowrap="nowrap" align="center">审核状态</td>
          <td width="10%" nowrap="nowrap" align="center">创建日期</td> 
          <td width="10%" nowrap="nowrap" align="center">制单人</td> 
          <td width="10%" nowrap="nowrap" align="center">业务员</td>
      </tr>
      <c:forEach var="cur" items="${allList}" varStatus="vs">
        <tr class="list-tr">
              <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
              <td align="left" nowrap="nowrap" >${cur.con_name}</td>
              <td align="left" nowrap="nowrap" >${fn:escapeXml(cur.con_sn)}</td>
              <td align="left" nowrap="nowrap">${cur.dept_name}</td>
              <td align="left" nowrap="nowrap">${cur.customer_name}</td>
              <td align="left" nowrap="nowrap">${cur.r3_code}</td>
              <td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.s_date}" pattern="yyyy-MM-dd " /></td>
              <td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.e_date}" pattern="yyyy-MM-dd " /></td>
              <td align="left" nowrap="nowrap">${cur.con_base_money}</td>
              <td align="left" nowrap="nowrap">${cur.con_expect_money}</td>
              <td align="left" nowrap="nowrap">
              <c:if test="${cur.c_type eq 1}">加价</c:if>
              <c:if test="${cur.c_type eq 2}">倒扣</c:if>
              </td>
              <td align="left" nowrap="nowrap">${cur.scale_sb}</td>
              <td align="left" nowrap="nowrap">${cur.month_sb}</td>
              <td align="left" nowrap="nowrap">${cur.tjj_money}</td>
              <td align="left" nowrap="nowrap">${cur.gcj_money}</td>
              <td align="left" nowrap="nowrap">${cur.other_sb}</td>
              <td align="left" nowrap="nowrap"><c:if test="${cur.audit_state eq 1}">未审核</c:if>
              <c:if test="${cur.audit_state eq 2}">审核通过</c:if>
              <c:if test="${cur.audit_state eq 3}">驳回</c:if></td>
              <td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" /></td>
              <td align="left" nowrap="nowrap">${cur.add_user_name}</td>
              <td align="left" nowrap="nowrap">${cur.ywy_name}</td>
        </tr>
      </c:forEach>
    </table>
  </div>
 
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
<script type="text/javascript">//<![CDATA[
   $(document).ready(function() {
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


		// 导出excel
	    $("#export_excel").click(function(){
	    	if(confirm("提示，您确认导出数据？")){
	    		$("#bottomPageForm").append("<input type='hidden' name='excel_all' value='1' />");
	    		$("#bottomPageForm").submit();
	    	}
	    });

	    var excel_all = "${af.map.excel_all}";
		if("1" == excel_all){
			toExcel('divExcel_all', '${ctx}/manager/admin/KonkaContractAuditRecord.do?method=toExcel');
		}
		
   });                                      
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
