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
       
          <td width="6%" nowrap="nowrap">
           <strong class="fb">查询类型：</strong> &nbsp;
            <html-el:select property="search_type" styleId="search_type" style="width:120px;">
              <html-el:option value="1">待审核订单</html-el:option>
              <html-el:option value="2">已审核订单</html-el:option>
         </html-el:select>
          &nbsp;<strong class="fb">流程类型：</strong> &nbsp;
            <html-el:select property="process_state" styleId="process_state" style="width:120px;">
              <html-el:option value="">全部订单</html-el:option>
              <html-el:option value="1">普通订单</html-el:option>
              <html-el:option value="2">特殊订单</html-el:option>
            </html-el:select>
            &nbsp;<strong class="fb">交易流水号：</strong>
            <html-el:text property="trade_index_like" styleClass="webinput" styleId="trade_index_like" />
             <span id="audit_state_type_default">
            <c:if test="${af.map.search_type_default eq 1}">
             &nbsp;<strong class="fb">当前角色审核状态：</strong>
            <html-el:select property="audit_state_order" styleId="audit_state_order" style="width:120px;">
              <html-el:option value="">全部</html-el:option>
              <html-el:option value="0">未审核</html-el:option>
              <html-el:option value="1">审核通过</html-el:option>
            </html-el:select>
            </c:if>
            </span>
            </td>
        </tr>
        <tr>
          <td><strong class="fb">下单日期：</strong>
            <html-el:text property="start_date" styleId="start_date" size="9" maxlength="9" readonly="true" styleClass="webinput" style="cursor:pointer;text-align:left;width:80px;" onclick="new Calendar(2011, 2031, 0).show(this);" />
            &nbsp;<span>至</span>
            <html-el:text property="end_date" styleId="end_date" size="9" maxlength="9" readonly="true" styleClass="webinput" style="cursor:pointer;text-align:left;width:80px;" onclick="new Calendar(2011, 2031, 0).show(this);" />
             &nbsp;<strong class="fb"> 分公司：</strong>
          <html-el:select property="fgs_dept_id" styleId="fgs_dept_id">
            <html-el:option value="">请选择...</html-el:option>
            <c:forEach var="cur" items="${sybDeptInfoList}">
              <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
            </c:forEach>
          </html-el:select>
          &nbsp;<strong class="fb"> 经营部：</strong>
          <html-el:select property="jyb_dept_id" styleId="jyb_dept_id">
            <html-el:option value="">请选择...</html-el:option>
          </html-el:select>
          &nbsp; <strong class="fb">办事处：</strong>
          <html-el:select property="bsc_dept_id" styleId="bsc_dept_id">
            <html-el:option value="">请选择...</html-el:option>
          </html-el:select>
            
            </td>
        </tr>
        <tr>
        <td>
           <strong class="fb">客户名称模糊搜索：</strong>
          <html-el:text property="customer_name_like" size="20" maxlength="20" styleId="customer_name_like" title="请输入客户名称或所属地区或分公司所在地"/>
          &nbsp;<input name="button" type="submit" class="bgSearch" id="button" value="搜 索" /></td>
      </tr>
      </table>
    </html-el:form>
  </div>
  <form id="listForm" name="listForm" method="post" action="KonkaJxcOrderAudit.do?method=delete">
    <div class="rtabcont1">
      <%@ include file="/commons/pages/messages.jsp" %>
    </div>
    <div class="rtabcont1" style="overflow-x:auto;">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th width="4%" nowrap="nowrap">序号</th>
          <th width="18%" nowrap="nowrap">下单日期</th>
          <th width="15%" nowrap="nowrap">交易流水号</th>
          <th width="10%" nowrap="nowrap">客户名称</th>
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
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm"/></td>
              <td align="center" nowrap="nowrap">${cur.trade_index} </td>
              <td align="center" nowrap="nowrap">${cur.user_shop_name} </td>
              <td align="center" nowrap="nowrap">${cur.order_num}</td>
              <td align="center" nowrap="nowrap"><fmt:formatNumber value="${cur.money}" pattern="0.00" /></td>
              <td align="center" nowrap="nowrap"><fmt:formatNumber value="${cur.good_discount_price}" pattern="0.00" /></td>
              <td align="center" nowrap="nowrap"><c:choose>
                  <c:when test="${cur.process_state eq 1}"> <span style="color:#060;">普通订单</span></c:when>
                  <c:when test="${cur.process_state eq 2}"> <span style="color:#f00;">特殊订单</span></c:when>
                </c:choose></td>
              <td align="center" nowrap="nowrap">
              <c:if test="${af.map.search_type eq 2}">
	              <c:if test="${empty is_last_audit}">
		              	<c:choose>
		              	<c:when test="${cur.map.audit_result eq 1}"> <span style="color:#060;">审核通过</span></c:when>
		                <c:when test="${cur.map.audit_result eq -1}"> <span style="color:#f00;">审核未通过</span></c:when>
		                </c:choose>
	               </c:if>
	               <c:if test="${not empty is_last_audit}">
		              	<c:choose>
		              	<c:when test="${cur.audit_state eq 1}"> <span style="color:#00f;">审核中</span></c:when>
		                <c:when test="${cur.audit_state eq 2}"> <span style="color:#f00;">审核未通过</span></c:when>
		                <c:when test="${cur.audit_state eq 3}"> <span style="color:#060;">审核通过</span></c:when>
		                </c:choose>
	               </c:if>
              </c:if>
              <c:if test="${af.map.search_type eq 1}">
              	 <c:if test="${cur.map.audit_level eq -1}">
               <c:if test="${empty cur.konkaOrderInfoAuditList}"> <span>未审核</span> </c:if>
               <c:if test="${not empty cur.konkaOrderInfoAuditList}">
                    <c:set var="is_audit" value=""/>
                    <c:forEach items="${cur.konkaOrderInfoAuditList}" var="cur1">
                      <c:if test="${cur1.audit_result eq 1}">
                        <c:set var="is_audit" value="1"/>
                      </c:if>
                      <c:if test="${cur1.audit_result eq -1}">
                        <c:set var="is_audit" value="2"/>
                      </c:if>
                    </c:forEach>
                    <c:if test="${is_audit eq 1}"> <span style="color:#060;">审核通过</span> </c:if>
                    <c:if test="${is_audit eq 2}"> <span style="color:#f00;">审核未通过</span> </c:if>
                  </c:if>
              </c:if>
              <c:if test="${cur.map.audit_level eq 1}">
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
                    <c:if test="${not empty not_in_process}">
                      <c:forEach items="${cur.konkaOrderInfoAuditList}" var="cur1">
                        <c:if test="${cur1.audit_result eq 1}">
                          <c:set var="is_audit" value="2"/>
                        </c:if>
                        <c:if test="${cur1.audit_result eq -1}">
                          <c:set var="is_audit" value="3"/>
                        </c:if>
                      </c:forEach>
                    </c:if>
                    <c:if test="${empty not_in_process}">
                      <c:if test="${not empty fgs_managerer && cur.map.process_state eq 1}">
                        <c:forEach items="${cur.konkaOrderInfoAuditList}" var="cur1">
                          <c:if test="${(cur1.audit_level eq cur.map.audit_level-1) && (cur1.audit_result eq 1)}">
                            <c:set var="is_audit" value="2"/>
                          </c:if>
                          <c:if test="${(cur1.audit_level eq cur.map.audit_level-1) && (cur1.audit_result eq -1)}">
                            <c:set var="is_audit" value="3"/>
                          </c:if>
                        </c:forEach>
                      </c:if>
                      <c:if test="${empty fgs_managerer or cur.map.process_state eq 2}">
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
                      </c:if>
                    </c:if>
                    <c:if test="${is_audit eq 1}"> <span>未审核</span> </c:if>
                    <c:if test="${is_audit eq 2}"> <span style="color:#060;">审核通过</span> </c:if>
                    <c:if test="${is_audit eq 3}"> <span style="color:#f00;">审核未通过</span> </c:if>
                  </c:if>
                </c:if>
              </c:if>
             </td>
              <!--  订单审核状态              -->
              <td align="center" nowrap="nowrap">
                 <c:if test="${cur.audit_state eq 0}"> <span>未审核</span> </c:if>
                <c:if test="${cur.audit_state eq 1}"> <span style="color:#060;">审核进行中</span> </c:if>
                <c:if test="${cur.audit_state eq 2}"> <span style="color:#f00;">审核结束【未通过】</span> </c:if>
                <c:if test="${cur.audit_state eq 3}"> <span style="color:#060;">审核结束【通过】</span> </c:if>
          </td>
              <td align="center" nowrap="nowrap">
               <c:if test="${af.map.search_type eq 1}">
              
              
              <c:if test="${cur.map.audit_level eq 0}"> <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaJxcOrderAudit.do', 'audit','id=${cur.id}&mod_id=${af.map.mod_id}&process_state=${cur.map.process_state}&audit_level=${cur.map.audit_level}&is_update_authority=${cur.map.is_update_authority}&'+$('#bottomPageForm').serialize())">审核</span> </c:if>
                <c:if test="${cur.map.audit_level eq 1}">
                  <c:if test="${empty cur.konkaOrderInfoAuditList}"> <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaJxcOrderAudit.do', 'audit','id=${cur.id}&mod_id=${af.map.mod_id}&process_state=${cur.map.process_state}&audit_level=${cur.map.audit_level}&is_update_authority=${cur.map.is_update_authority}&'+$('#bottomPageForm').serialize())">审核</span> </c:if>
                  <c:if test="${not empty cur.konkaOrderInfoAuditList}">
                    <c:set var="is_audit" value=""/>
                    <c:forEach items="${cur.konkaOrderInfoAuditList}" var="cur1">
                      <c:if test="${cur1.audit_level ge 1}">
                        <c:set var="is_audit" value="1"/>
                      </c:if>
                    </c:forEach>
                    <c:if test="${is_audit eq 1}"><span style="color:#ccc;" title="您已经审核，不能再次审核!">审核</span></c:if>
                  </c:if>
                </c:if>
                <c:if test="${cur.map.audit_level gt 1}">
               <c:if test="${empty is_last_audit}">
                  <c:if test="${empty cur.konkaOrderInfoAuditList}"> <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaJxcOrderAudit.do', 'audit','id=${cur.id}&mod_id=${af.map.mod_id}&process_state=${cur.map.process_state}&audit_level=${cur.map.audit_level}&is_update_authority=${cur.map.is_update_authority}&'+$('#bottomPageForm').serialize())">审核</span> </c:if>
                  <c:if test="${not empty cur.konkaOrderInfoAuditList}">
                    <c:set var="is_audit" value=""/>
                    <c:if test="${not empty fgs_managerer && cur.map.process_state eq 1}">
                      <c:forEach items="${cur.konkaOrderInfoAuditList}" var="cur1">
                        <c:if test="${(cur1.audit_level eq cur.map.audit_level-1) && (cur1.audit_result eq 1)}">
                          <c:set var="is_audit" value="2"/>
                        </c:if>
                        <c:if test="${(cur1.audit_level eq cur.map.audit_level-1) && (cur1.audit_result eq -1)}">
                          <c:set var="is_audit" value="3"/>
                        </c:if>
                      </c:forEach>
                    </c:if>
                    <c:if test="${empty fgs_managerer or cur.map.process_state eq 2}">
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
                    </c:if>
                    <c:if test="${is_audit eq 1}"> <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaJxcOrderAudit.do', 'audit','id=${cur.id}&mod_id=${af.map.mod_id}&process_state=${cur.map.process_state}&audit_level=${cur.map.audit_level}&is_update_authority=${cur.map.is_update_authority}&'+$('#bottomPageForm').serialize())">审核</span> </c:if>
                    <c:if test="${is_audit eq 2}"><span style="color:#ccc;" title="您已审核通过，不能再次审核!">审核</span></c:if>
                    <c:if test="${is_audit eq 3}"><span style="color:#ccc;" title="您已审核不通过，不能再次审核!">审核</span></c:if>
                  </c:if>
               </c:if>
                  <c:if test="${not empty is_last_audit}">
                    <c:if test="${cur.audit_state lt 2}"> <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaJxcOrderAudit.do', 'audit','id=${cur.id}&mod_id=${af.map.mod_id}&process_state=${cur.map.process_state}&audit_level=${cur.map.audit_level}&is_update_authority=${cur.map.is_update_authority}&'+$('#bottomPageForm').serialize())">审核</span> </c:if>
                    <c:if test="${cur.audit_state ge 2}"><span style="color:#ccc;" title="您已审核，不能再次审核!">审核</span></c:if>
                  </c:if>
                </c:if>
                | &nbsp;
                </c:if>
                <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaJxcOrderAudit.do', 'view','id=${cur.id}&mod_id=${af.map.mod_id}&audit_level=${cur.map.audit_level}&is_update_authority=${cur.map.is_update_authority}&'+$('#bottomPageForm').serialize())">查看</span>
                 | &nbsp;
                <span style="cursor:pointer;" class="fblue" onclick="window.showModalDialog('?method=print&' + encodeURI('id=' + ${cur.id} +'&random=' + Math.random()), window, 'dialogWidth:900px;status:no;dialogHeight:580px')">打印</span>
                </td>
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
            pager.addHiddenInputs("fgs_dept_id", "${af.map.fgs_dept_id}");
            pager.addHiddenInputs("jyb_dept_id", "${af.map.jyb_dept_id}");
            pager.addHiddenInputs("bsc_dept_id", "${af.map.bsc_dept_id}");
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
	$("#fgs_dept_id").change( function() {
		var dept_id = $("#fgs_dept_id").val();
		$("#jyb_dept_id").empty();
		$("#bsc_dept_id").empty();

		if(""==dept_id){
	   		var opt1 = new Option( "请选择...",""); 
	   		var opt2 = new Option( "请选择...",""); 
			$("#jyb_dept_id").get(0).options.add(opt1);
			$("#bsc_dept_id").get(0).options.add(opt2);
		   	}
	   	if(dept_id!=""){
		   	$.ajax({
				type: "POST",
				cache: false,
				url: "CsAjax.do",
				data: "method=getJybDeptListByFgsId&fgs_dept_id=" + $("#fgs_dept_id").val(),
				dataType: "json",
				error: function(request, settings){},
				success: function(data) {
					if (data.length >= 1) {
						var opt1 = new Option( "请选择...",""); 
						$("#jyb_dept_id").get(0).options.add(opt1);

						var opt2 = new Option( "请选择...",""); 
						$("#bsc_dept_id").get(0).options.add(opt2);
						
						for(var i = 0; i < data.length - 1; i++) {
							var opt = new Option( data[i].name,data[i].id); 
							$("#jyb_dept_id").get(0).options.add(opt);
						}
						<c:if test="${not empty af.map.jyb_dept_id }">$("#jyb_dept_id").val("${af.map.jyb_dept_id}");$("#jyb_dept_id").change();</c:if>
					}
				}
			});
	   	}
	   	
	});
	$("#jyb_dept_id").change( function() {
		var dept_id = $("#jyb_dept_id").val();
		$("#bsc_dept_id").empty();

	   	if(""==dept_id){
	   		var fgs_dept_id = $("#fgs_dept_id").val();
			   var opt = new Option( "请选择...",""); 
				$("#bsc_dept_id").get(0).options.add(opt);
			}
	   	else if(dept_id!=""){
		   	$.ajax({
				type: "POST",
				cache: false,
				url: "CsAjax.do",
				data: "method=getBscDeptListByJybId&jyb_dept_id=" + $("#jyb_dept_id").val(),
				dataType: "json",
				error: function(request, settings){},
				success: function(data) {
					if (data.length >= 1) {
						var opt = new Option( "请选择...",""); 
						$("#bsc_dept_id").get(0).options.add(opt);
						
						for(var i = 0; i < data.length - 1; i++) {
							var opt = new Option( data[i].name,data[i].id); 
							$("#bsc_dept_id").get(0).options.add(opt);
						}
							<c:if test="${not empty af.map.bsc_dept_id }">$("#bsc_dept_id").val("${af.map.bsc_dept_id}");</c:if>
					}
				}
			});
	   	}
	   	
	});

	<c:if test="${not empty af.map.fgs_dept_id }">
	$("#fgs_dept_id").val("${af.map.fgs_dept_id}");
	$("#fgs_dept_id").change();
</c:if>
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

	 $("#search_type").change(function(){
		 var search_type = $("#search_type").val();
		 if(search_type == '1'){//audit_state 
			 $("#audit_state_type_default").show();
		 }else{
			 if(search_type == '2'){
				 $("#audit_state_type_default").hide();
			}
			 
		}
		});
	});
//]]></script>
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>