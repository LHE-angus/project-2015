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
    <html-el:form action="/admin/KonkaSpActivityManager">
      <html-el:hidden property="method" styleId="method" value="list" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td nowrap="nowrap">
          &nbsp;&nbsp;<strong class="fb">分 公 司：</strong>
            <html-el:select property="dept_id" styleId="dept_id">
              <html-el:option value="">--请选择--</html-el:option>
              <c:forEach var="cur" items="${sybDeptInfoList}">
                <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
              </c:forEach>
            </html-el:select>
          &nbsp;&nbsp;<strong class="fb">主要客户：</strong>
          <html-el:text property="main_customer_like" size="20" style="width:90px;" maxlength="10" styleId="hd_type_like" styleClass="webinput" />
          &nbsp;&nbsp;<strong class="fb">R3客户编码：</strong>
          <html-el:text property="main_r3_code_like" size="20" style="width:90px;" maxlength="10" styleId="hd_type_like" styleClass="webinput" />
          &nbsp;&nbsp;<strong class="fb">单据状态：</strong>
          	<html-el:select property="file_status" style="width:90px;">
          		<html-el:option value="">--请选择--</html-el:option>
          		<html-el:option value="3">有效</html-el:option>
          		<html-el:option value="0">无效</html-el:option>
          	</html-el:select>
          </td>
          </tr>
          <tr>
          <td nowrap="nowrap">
           &nbsp;&nbsp;<strong class="fb">上 报 人：</strong>
          <html-el:text property="report_user_name_like" size="20" style="width:90px;" maxlength="10" styleId="report_user_name_like" styleClass="webinput" />
          &nbsp;&nbsp;<strong class="fb">销售金额：</strong>
          <html-el:text property="total_sail_money_min" styleId="total_sail_money_min" size="12"/>
	 			    至
	 	  <html-el:text property="total_sail_money_max" styleId="total_sail_money_max" size="12"/>
          &nbsp;&nbsp;<strong class="fb">单据编号：</strong>
          <html-el:text property="sp_sn_like" size="20" style="width:90px;" maxlength="22" styleId="sp_sn_like" styleClass="webinput" />
          &nbsp;&nbsp;<strong class="fb">活 动 类 型：</strong>
         	<html-el:select property="hd_id">
         	<html-el:option value="">--请选择--</html-el:option>
         	<c:forEach items="${activityTypeList}" var="cur">
			<html-el:option value="${cur.id}">${cur.hd_type}</html-el:option>
			</c:forEach>
         	</html-el:select>
          </td>
          </tr>
          <tr>
          <td nowrap="nowrap">
          &nbsp;&nbsp;<strong class="fb">活动状态：</strong>
          	<html-el:select property="hd_status" style="width:90px;">
          		<html-el:option value="">--请选择--</html-el:option>
          		<html-el:option value="0">未上报</html-el:option>
          		<html-el:option value="30">进行中</html-el:option>
          		<html-el:option value="50">已结束</html-el:option>
          	</html-el:select>
          &nbsp;&nbsp;<strong class="fb">负 责 人：</strong>
          <html-el:text property="charge_person_like" size="20" style="width:90px;" maxlength="10" styleId="charge_person_like" styleClass="webinput" />
         &nbsp;&nbsp;<strong class="fb">创建日期：</strong>
          <input name="add_sp_date_k" id="add_sp_date_k" size="12" value="${af.map.add_sp_date_k}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'2013-11-01',maxDate:'#F{$dp.$D(\'add_sp_date_j\')}'})" />
            	    至
                <input name="add_sp_date_j" id="add_sp_date_j" size="12"  value="${af.map.add_sp_date_j}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'#F{$dp.$D(\'add_sp_date_k\')||\'2013-11-01\'}'})" />
		  </td>
          </tr>
          <tr>
          <td nowrap="nowrap">
          &nbsp;&nbsp;<strong class="fb">爆发期起：</strong>
          <input name="bf_s_date_k" id="bf_s_date_k" size="12" value="${af.map.bf_s_date_k}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'2013-11-01',maxDate:'#F{$dp.$D(\'bf_s_date_j\')}'})" />
            	    至
                <input name="bf_s_date_j" id="bf_s_date_j" size="12"  value="${af.map.bf_s_date_j}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'#F{$dp.$D(\'bf_s_date_k\')||\'2013-11-01\'}'})" />
		  
		   &nbsp;&nbsp;<strong class="fb">爆发期止：</strong>
          <input name="bf_e_date_k" id="bf_e_date_k" size="12" value="${af.map.bf_e_date_k}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'2013-11-01',maxDate:'#F{$dp.$D(\'bf_e_date_j\')}'})" />
            	    至
                <input name="bf_e_date_j" id="bf_e_date_j" size="12"  value="${af.map.bf_e_date_j}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'#F{$dp.$D(\'bf_e_date_k\')||\'2013-11-01\'}'})" />
		  &nbsp;&nbsp;&nbsp;&nbsp;<input class="but1" type="submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
  <html-el:form action="/admin/KonkaSpActivityManager?method=delete">
  <input type="hidden" name="method" id="method" value="delete" />
  <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><input class="but2" type="button" name="Submit2" value="新增" onclick="location.href='KonkaSpActivityManager.do?method=add&mod_id=${af.map.mod_id}';" />
        	<input class="but3" type="button" name=button3 id="button3" style="width:67px;" value="删除" onClick="confirmDeleteAll(this.form);" />
        	<input type="button" class="but_excel" onClick="doNeedMethod(null, 'KonkaSpActivityManager.do', 'list', 'export=true&' + $('#bottomPageForm').serialize())" value="导出" />
          <span style="color:#F00;">注：请先搜索再导出，因数据量过大会导致系统反应缓慢或宕机，因此这些数据将不作导出。</span> 
        </td>
      </tr>
    </table>
  	<div style="overflow-x:auto;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
      	<td width="3%" align="center" nowrap="nowrap">
        <input name="chkAll" type="checkbox" id="chkAll" value="-1" onClick="checkAll(this);" />
        </td>
        <td width="5%" nowrap="nowrap" align="center">序号</td>
        <td width="15%" align="center" nowrap="nowrap">主题</td>
        <td width="10%" align="center" nowrap="nowrap">活动类型</td>
        <td width="5%" nowrap="nowrap" align="center">活动状态</td>
        <td width="5%" nowrap="nowrap" align="center">单据状态</td>
        <td width="15%" align="center">分公司</td>
        <td width="8%" align="center">经营部</td>
        <td width="8%" nowrap="nowrap" align="center">主要客户</td>
        <td width="8%" nowrap="nowrap" align="center">主要R3编码</td>
        <td width="8%" nowrap="nowrap" align="center">客户类型</td>
        <td width="8%" nowrap="nowrap" align="center">细分类型</td>
        <td width="8%" nowrap="nowrap" align="center">任务金额(万元)</td>
        <td width="8%" nowrap="nowrap" align="center">任务销量(台)</td>
        <td width="8%" nowrap="nowrap" align="center">活动渠道排名</td>
        <!--  <td width="8%" nowrap="nowrap" align="center">目标</td> -->
          <td width="8%" nowrap="nowrap" align="center">预约数</td>
           <td width="8%" nowrap="nowrap" align="center">销售总金额</td>
            <td width="8%" nowrap="nowrap" align="center">销售总数量</td>
             <td width="8%" nowrap="nowrap" align="center">负责人</td>
              <td width="8%" nowrap="nowrap" align="center">创建时间</td>
              <td width="8%" nowrap="nowrap" align="center">结束日期</td>
              <td width="8%" nowrap="nowrap" align="center">附件</td>
        <td width="8%" nowrap="nowrap" align="center">操作</td>
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">
            <c:if test="${fn:escapeXml(cur.file_status) eq 1 and fn:escapeXml(cur.hd_status) eq 0 and true eq cur.map.allowUpdate}">
            <input name="pks" type="checkbox" id="pks" value="${cur.id}" />
            </c:if>
            </td>
            <td align="center" nowrap="nowrap"> ${vs.count}</td>
            <td align="left" nowrap="nowrap"><span style="cursor:pointer;" class="fblue" onClick="doNeedMethod(null, '${ctx}/manager/admin/KonkaSpActivityManager.do','view','&id=${cur.id }&' + $('#bottomPageForm').serialize())">${cur.sp_name}</span></td>
            <td align="left" nowrap="nowrap">&nbsp;${cur.map.hd_type}&nbsp;</td>
            <td align="center" nowrap="nowrap">
            <c:if test="${cur.hd_status eq 0}">未上报</c:if>
            <c:if test="${cur.hd_status eq 30}">进行中</c:if>
            <c:if test="${cur.hd_status eq 50}">已结束</c:if>
            </td>
            <td align="center" nowrap="nowrap">
            <c:if test="${cur.file_status eq 0}">无效</c:if>
            <c:if test="${cur.file_status ne 0}">有效</c:if>
            </td>
            <td align="left" nowrap="nowrap">&nbsp;${cur.dept_name}&nbsp;</td>
            <td align="left" nowrap="nowrap">${cur.jyb_name}</td>
            <td align="left" nowrap="nowrap">${cur.main_customer}</td>
            <td align="left" nowrap="nowrap">${cur.main_r3_code}</td>
            <td align="left" nowrap="nowrap">${cur.par_customer_type_name}</td>
            <td align="left" nowrap="nowrap">${cur.customer_type_name}</td>
            <td align="right" nowrap="nowrap">${cur.task_money}</td>
            <td align="right" nowrap="nowrap">${cur.task_sail_num}</td>
            <td align="right" nowrap="nowrap">${cur.activity_ranking}</td>
            <td align="right" nowrap="nowrap">${cur.yy_num}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.total_sail_money}" pattern="#.00"/> </td>
            <td align="right" nowrap="nowrap">${cur.total_sail_num}</td>
            <td align="right" nowrap="nowrap">${fn:escapeXml(cur.charge_person)}</td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_sp_date}" pattern="yyyy-MM-dd" /></td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.bf_e_date}" pattern="yyyy-MM-dd" /></td>
            <td align="left" nowrap="nowrap">
              	<c:if test="${not empty cur.map.files}">
		           <c:set var="file" value="${fn:split(cur.map.files,',')}" />
		          <c:forEach items="${file}" var="tt" varStatus="vs1">
		          <c:set var="num" value="${fn:length(tt)}" />
		          
		          	<a href=/${tt} target="_blank">&nbsp;附件${vs1.count}&nbsp;</a>
		          	
		          </c:forEach>
		          </c:if>
              </td>
            <td nowrap="nowrap" align="center">
			   <c:choose>
              <c:when test="${fn:escapeXml(cur.file_status) eq 1 and fn:escapeXml(cur.hd_status) eq 0 and true eq cur.map.allowUpdate}">              
              	<span style="cursor:pointer;" class="fblue" onClick="doNeedMethod(null, '${ctx}/manager/admin/KonkaSpActivityManager.do','edit','&id=${cur.id }&' + $('#bottomPageForm').serialize())">修改</span>
              </c:when>
              <c:otherwise>
              <span style="color:#CCC;">修改</span>
              </c:otherwise>
              </c:choose>
              |
              <c:choose>
              <c:when test="${fn:escapeXml(cur.file_status) eq 1 and fn:escapeXml(cur.hd_status) eq 0 and true eq cur.map.allowUpdate}"> 
                  <span style="cursor:pointer;" class="fblue" onClick="confirmDelete('确定删除吗?', '${ctx}/manager/admin/KonkaSpActivityManager.do', 'id=${cur.id}&' + $('#bottomPageForm').serialize())">删除</span>
                 </c:when>
              <c:otherwise>
              <span style="color:#CCC;">删除</span>
              </c:otherwise>
              </c:choose>
              |
            <c:choose>
              <c:when test="${fn:escapeXml(cur.file_status) eq 1 and fn:escapeXml(cur.hd_status) ne 0 and true eq cur.map.allowUpdate}">   
                 <span style="cursor:pointer;" class="fblue" onClick="doNeedMethod(null, '${ctx}/manager/admin/KonkaSpActivityManager.do','edit' ,'id=${cur.id}&mt=process&' + $('#bottomPageForm').serialize())">过程</span>
            </c:when>
              <c:otherwise>
              <span style="color:#CCC;">过程</span>
              </c:otherwise>
              </c:choose>
              |
             <c:choose>
             <c:when test="${fn:escapeXml(cur.file_status) eq 1 and fn:escapeXml(cur.hd_status) ne 0 and true eq cur.map.allowUpdate}"> 
                  <span style="cursor:pointer;" class="fblue" onClick="doNeedMethod(null, '${ctx}/manager/admin/KonkaSpActivityManager.do','edit' ,'id=${cur.id}&mt=result&' + $('#bottomPageForm').serialize())">结果</span>
             </c:when>
              <c:otherwise>
              <span style="color:#CCC;">结果</span>
              </c:otherwise>
              </c:choose>
              |
             <c:choose>
              <c:when test="${fn:escapeXml(cur.file_status) ne 0 and cur.map.allowUpdate}"> 
                <span style="cursor:pointer;" class="fblue" onClick="doNeedMethod('确认无效吗？', '${ctx}/manager/admin/KonkaSpActivityManager.do','enable' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">无效</span>
              </c:when>
              <c:otherwise>
              	<span style="color:#CCC;">无效</span>
              </c:otherwise>
              </c:choose>
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
          </tr>
        </c:forEach>
      </tbody>
    </table>
    </div>
    </html-el:form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaSpActivityManager.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align:right; padding-right:5px;"> 
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
              <script type="text/javascript">
							var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
							pager.addHiddenInputs("method", "list");
							pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
							pager.addHiddenInputs("main_customer_like", "${af.map.main_customer_like}");
							pager.addHiddenInputs("main_r3_code_like", "${af.map.main_r3_code_like}");
							pager.addHiddenInputs("dept_id", "${fn:escapeXml(af.map.dept_id)}");
							pager.addHiddenInputs("hd_id", "${fn:escapeXml(af.map.hd_id)}");
							pager.addHiddenInputs("hd_status", "${fn:escapeXml(af.map.hd_status)}");
							pager.addHiddenInputs("file_status", "${fn:escapeXml(af.map.file_status)}");
							pager.addHiddenInputs("sp_sn_like", "${af.map.sp_sn_like}");
							pager.addHiddenInputs("report_user_name_like", "${af.map.report_user_name_like}");
							pager.addHiddenInputs("charge_person_like", "${af.map.charge_person_like}");
							pager.addHiddenInputs("total_sail_money_min", "${af.map.total_sail_money_min}");
							pager.addHiddenInputs("total_sail_money_max", "${af.map.total_sail_money_max}");
							pager.addHiddenInputs("bf_s_date_k", "${af.map.bf_s_date_k}");
							pager.addHiddenInputs("bf_s_date_j", "${af.map.bf_s_date_j}");
							pager.addHiddenInputs("bf_e_date_k", "${af.map.bf_e_date_k}");
							pager.addHiddenInputs("bf_e_date_j", "${af.map.bf_e_date_j}");
							pager.addHiddenInputs("add_sp_date_k", "${af.map.add_sp_date_k}");
							pager.addHiddenInputs("add_sp_date_j", "${af.map.add_sp_date_j}");
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
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#total_sail_money_min").focus(setOnlyNum);
	$("#total_sail_money_max").focus(setOnlyNum2);
});
function setOnlyNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+]?\d+(?:\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=this.value;this.o_value=this.value};
	});
	//this.text.selected;
}
function setOnlyNum2() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+]?\d+(?:\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=this.value;this.o_value=this.value};
	});
	//this.text.selected;
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>