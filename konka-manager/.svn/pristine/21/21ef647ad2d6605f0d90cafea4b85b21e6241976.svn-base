<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单审核</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：${naviString}</div>
   <div class="rtabcont1">
    <html-el:form action="/KonkaJxcOrderAudit">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="msg" value="${msg}" styleId="msg"/>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
        <tr>
          <td width="6%" nowrap="nowrap"><strong class="fb">流程类型：</strong>
         &nbsp;<html-el:select property="process_state" styleId="process_state" style="width:120px;">
              <html-el:option value="">全部订单</html-el:option>
              <html-el:option value="1">普通订单</html-el:option>
              <html-el:option value="2">特殊订单</html-el:option>
            </html-el:select>
          &nbsp;<strong class="fb">交易流水号：</strong>
          <html-el:text property="trade_index_like" styleClass="webinput" styleId="trade_index_like" />
          &nbsp;<strong class="fb">当前角色审核状态：</strong>
         <html-el:select property="audit_state" styleId="audit_state" style="width:120px;">
              <html-el:option value="">全部</html-el:option>
              <html-el:option value="0">未审核</html-el:option>
              <html-el:option value="1">审核通过</html-el:option>
              <html-el:option value="-1">审核未通过</html-el:option>
            </html-el:select></td>
          </tr>
          <tr>
          <td>
         <strong class="fb">下单日期：</strong>
         <html-el:text property="start_date" styleId="start_date" size="9" maxlength="9" readonly="true" styleClass="webinput" style="cursor:pointer;text-align:left;width:80px;" onclick="new Calendar(2011, 2031, 0).show(this);" />
         &nbsp;<span>至</span>
          <html-el:text property="end_date" styleId="end_date" size="9" maxlength="9" readonly="true" styleClass="webinput" style="cursor:pointer;text-align:left;width:80px;" onclick="new Calendar(2011, 2031, 0).show(this);" />
         &nbsp;<html-el:submit value="搜 索" styleClass="bgSearch"/></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <form id="listForm" name="listForm" method="post" action="KonkaJxcOrderAudit.do?method=delete">
    <div class="rtabcont1">
      <%@ include file="/commons/pages/messages.jsp" %>
    </div>
    <div class="rtabcont1">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th width="4%" nowrap="nowrap">序号</th>
          <th width="18%" nowrap="nowrap">下单日期</th>
          <th width="15%" nowrap="nowrap">交易流水号</th>
          <th width="10%" nowrap="nowrap">买卖提网点名称</th>
          <th width="6%" nowrap="nowrap">订单数量</th>
          <th width="10%" nowrap="nowrap">订单金额(元)</th>
          <th width="10%" nowrap="nowrap">折扣金额(元)</th>
          <th width="8%" nowrap="nowrap">流程类型</th>
          <th width="13%" nowrap="nowrap">当前角色审核状态</th>
          <th width="8%" nowrap="nowrap">订单审核状态</th>
          <th width="8%" nowrap="nowrap">操作</th>
        </tr>
        <c:if test="${not empty entityList}">
          <c:forEach items="${entityList}" var="cur" varStatus="vs">
            <tr>
              <td align="center" nowrap="nowrap">${vs.count} </td>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd"/></td>
              <td align="center" nowrap="nowrap">${cur.trade_index} </td>
              <td align="center" nowrap="nowrap">${cur.map.shop_name} </td>
              <td align="center" nowrap="nowrap">${cur.order_num}</td>
              <td align="center" nowrap="nowrap"><fmt:formatNumber value="${cur.money}" pattern="0.00" /></td>
              <td align="center" nowrap="nowrap"><fmt:formatNumber value="${cur.good_discount_price}" pattern="0.00" /></td>
              <td align="center" nowrap="nowrap"><c:choose>
                  <c:when test="${cur.map.process_state eq 1}"> <span style="color:#060;">普通订单</span></c:when>
                  <c:when test="${cur.map.process_state eq 2}"> <span style="color:#f00;">特殊订单</span></c:when>
                </c:choose></td>
              <td align="center" nowrap="nowrap"><c:if test="${cur.map.audit_level eq 1}">
                  <c:if test="${empty cur.konkaOrderInfoAuditList}"> <span>未审核</span> </c:if>
                  <c:if test="${not empty cur.konkaOrderInfoAuditList}">
                    <c:set var="is_audit" value=""/>
                    <c:forEach items="${cur.konkaOrderInfoAuditList}" var="cur1">
                      <c:if test="${(cur1.audit_level eq 1) && (cur1.audit_result eq 1)}">
                        <c:set var="is_audit" value="1"/>
                      </c:if>
                      <c:if test="${(cur1.audit_level eq 1) && (cur1.audit_result eq -1)}">
                        <c:set var="is_audit" value="2"/>
                      </c:if>
                      <c:if test="${cur1.audit_level eq 2}">
                        <c:set var="is_audit" value="1"/>
                      </c:if>
                    </c:forEach>
                    <c:if test="${is_audit eq 1}"> <span style="color:#060;">审核通过</span> </c:if>
                    <c:if test="${is_audit eq 2}"> <span style="color:#f00;">审核未通过</span> </c:if>
                  </c:if>
                </c:if>
                <c:if test="${cur.map.audit_level gt 1}">
                  <c:if test="${empty cur.konkaOrderInfoAuditList}"> <span>未审核</span> </c:if>
                  <c:if test="${ not empty cur.konkaOrderInfoAuditList}">
                    <c:set var="is_audit" value=""/>
                    <c:forEach items="${cur.konkaOrderInfoAuditList}" var="cur1">
                      <c:if test="${cur1.audit_level eq (cur.map.audit_level-1)}">
                        <c:set var="is_audit" value="1"/>
                      </c:if>
                      <c:if test="${(cur1.audit_level eq cur.map.audit_level) && (cur1.audit_result eq 1)}">
                        <c:set var="is_audit" value="2"/>
                      </c:if>
                      <c:if test="${(cur1.audit_level eq cur.map.audit_level) && (cur1.audit_result eq -1)}">
                        <c:set var="is_audit" value="3"/>
                      </c:if>
                    </c:forEach>
                    <c:if test="${is_audit eq 1}"> <span>未审核</span> </c:if>
                    <c:if test="${is_audit eq 2}"> <span style="color:#060;">审核通过</span> </c:if>
                    <c:if test="${is_audit eq 3}"> <span style="color:#f00;">审核未通过</span> </c:if>
                  </c:if>
                </c:if></td>
              <!--  订单审核状态              -->
              <td align="center" nowrap="nowrap"><c:if test="${empty cur.konkaOrderInfoAuditList}"> <span>未审核</span> </c:if>
                <c:if test="${not empty cur.konkaOrderInfoAuditList}">
                  <c:set var="is_now_max_level" value="0"/>
                  <c:set var="is_max_level" value="0"/>
                  <c:set var="audit_last_result" value=""/>
                  <c:forEach items="${cur.konkaOrderInfoAuditList}" var="cur1">
                    <c:if test="${cur1.audit_result eq -1}">
                      <c:set var="is_now_max_level" value="1"/>
                    </c:if>
                    <c:if test="${cur1.audit_level eq cur.map.max_audit_level}">
                      <c:set var="is_max_level" value="1"/>
                      <c:set var="audit_last_result" value="${cur1.audit_result}"/>
                    </c:if>
                  </c:forEach>
                  <c:if test="${is_max_level eq 0 && is_now_max_level eq 0}"> <span style="color:#060;">审核进行中</span> </c:if>
                  <c:if test="${is_max_level eq 0 && is_now_max_level eq 1}"> <span style="color:#060;">审核结束 【审核结果：未通过】</span> </c:if>
                  <c:if test="${is_max_level eq 1}"> <span style="color:#060;">审核结束 
                    【审核结果：
                    <c:if test="${audit_last_result eq 1}">通过</c:if>
                    <c:if test="${audit_last_result eq 0}">未审核</c:if>
                    <c:if test="${audit_last_result eq -1}">未通过</c:if>
                    】</span></c:if>
                </c:if></td>
              <td align="center" nowrap="nowrap"><c:if test="${cur.map.audit_level eq 1}">
                  <c:if test="${empty cur.konkaOrderInfoAuditList}"> <span style="cursor:pointer;" class="fblue" onClick="doNeedMethod(null, 'KonkaJxcOrderAudit.do', 'audit','id=${cur.id}&mod_id=${af.map.mod_id}&process_state=${cur.map.process_state}&audit_level=${cur.map.audit_level}&is_update_authority=${cur.map.is_update_authority}&'+$('#bottomPageForm').serialize())">审核</span> </c:if>
                  <c:if test="${not empty cur.konkaOrderInfoAuditList}">
                    <c:set var="is_audit" value=""/>
                    <c:forEach items="${cur.konkaOrderInfoAuditList}" var="cur1">
                      <c:if test="${cur1.audit_level eq 1}">
                        <c:set var="is_audit" value="1"/>
                      </c:if>
                    </c:forEach>
                    <c:if test="${is_audit eq 1}"><span style="color:#ccc;" title="您已经审核，不能再次审核!">审核</span></c:if>
                  </c:if>
                </c:if>
                <c:if test="${cur.map.audit_level gt 1}">
                  <c:if test="${empty cur.konkaOrderInfoAuditList}"> <span style="color:#ccc;" title="上一级未审核，您暂不能审核!">审核</span> </c:if>
                  <c:if test="${not empty cur.konkaOrderInfoAuditList}">
                    <c:set var="is_audit" value=""/>
                    <c:forEach items="${cur.konkaOrderInfoAuditList}" var="cur1">
                      <c:if test="${(cur1.audit_level eq (cur.map.audit_level-1)) && (cur1.audit_result eq 1)}">
                        <c:set var="is_audit" value="1"/>
                      </c:if>
                      <c:if test="${(cur1.audit_level eq (cur.map.audit_level-1)) && (cur1.audit_result eq -1)}">
                        <c:set var="is_audit" value="2"/>
                      </c:if>
                      <c:if test="${(cur1.audit_level eq cur.map.audit_level) && (not empty cur1.audit_result)}">
                        <c:set var="is_audit" value="3"/>
                      </c:if>
                    </c:forEach>
                    <c:if test="${is_audit eq 1}"> <span style="cursor:pointer;" class="fblue" onClick="doNeedMethod(null, 'KonkaJxcOrderAudit.do', 'audit','id=${cur.id}&mod_id=${af.map.mod_id}&process_state=${cur.map.process_state}&audit_level=${cur.map.audit_level}&is_update_authority=${cur.map.is_update_authority}&'+$('#bottomPageForm').serialize())">审核</span> </c:if>
                    <c:if test="${is_audit eq 2}"><span style="color:#ccc;" title="上一级审核未通过，您不能审核!">审核</span></c:if>
                    <c:if test="${is_audit eq 3}"><span style="color:#ccc;" title="您已审核，不能再次审核!">审核</span></c:if>
                  </c:if>
                </c:if>
                | &nbsp;<span style="cursor:pointer;" class="fblue" onClick="doNeedMethod(null, 'KonkaJxcOrderAudit.do', 'view','id=${cur.id}&mod_id=${af.map.mod_id}&audit_level=${cur.map.audit_level}&is_update_authority=${cur.map.is_update_authority}&'+$('#bottomPageForm').serialize())">查看</span></td>
            </tr>
          </c:forEach>
        </c:if>
        <c:if test="${empty entityList}">
          <tr>
            <td colspan="11"><div align="center" id="msg_tips" style="display: none;"> <span id="msg_text"></span> </div></td>
          </tr>
        </c:if>
      </table>
    </div>
  </form>
  <div class="rtabcont3">
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaJxcOrderAudit.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("process_state", "${af.map.process_state}");
            pager.addHiddenInputs("audit_state", "${af.map.audit_state}");
            pager.addHiddenInputs("trade_index_like", "${af.map.trade_index_like}");
            pager.addHiddenInputs("start_date", "${fn:escapeXml(af.map.start_date)}");
            pager.addHiddenInputs("end_date", "${fn:escapeXml(af.map.end_date)}");
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var msg = $("#msg").val();
	if("" != msg){
	 	$("#msg_tips").show();
	 	$("#msg_text").empty().append('<font id="tip" style="color:#f00;">' + msg +  '</font>');
	 	//append('<font id="tip" style="color:#f00;">' + msg +  '</font>');
	 	
	}
	var f=document.forms[0];
	 $(".bgSearch").click(function(){
	    	var s_time = $("#start_date").val();
			var e_time = $("#end_date").val();
			if ("" != s_time && "" != e_time && s_time > e_time) {
				alert("开始日期不能大于结束日期！");
				return false;
			}
			if(!Validator.Validate(f, 1)){
				return false;
			}
	    });
	});
//]]></script>
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>