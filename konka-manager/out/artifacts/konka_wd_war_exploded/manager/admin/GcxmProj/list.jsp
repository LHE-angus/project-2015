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
    <html-el:form action="/admin/GcxmProj" >  
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
      <tr>
      	<td nowrap="nowrap">&nbsp;&nbsp;<strong class="fb">项目名称</strong>
        <html-el:text property="proj_name_like" styleId="proj_name_like" size="20" maxlength="20" />
        <strong class="fb">&nbsp;&nbsp;项目编号</strong>
        <html-el:text property="proj_code_like" styleId="proj_code_like" size="20" maxlength="20" />
        <strong class="fb">&nbsp;采购人/投标人</strong>
        <html-el:text property="buyer_like" styleId="buyer_like" size="20" maxlength="20" />
        </td></tr>
        <tr>
        	<td nowrap="nowrap">&nbsp;&nbsp;<strong class="fb">创 建 人</strong>
        	<html-el:text property="create_name_like" styleId="create_name_like" size="20" maxlength="20" />
        	&nbsp;<strong class="fb">分 公 司</strong>
      		<html-el:select property="fgs_id" styleId="fgs_id" onchange="this.form.submit();">
        	<html-el:option value="">请选择...</html-el:option>
        	<c:forEach var="cur" items="${sybDeptInfoList}">
        	<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
        	</c:forEach>
        	</html-el:select>
        	&nbsp;<strong class="fb">项目类型</strong>
      		<html-el:select property="proj_type" styleId="proj_type" style="width:80px;" onchange="this.form.submit();">
        		<html-el:option value="">--请选择--</html-el:option>
        		<html-el:option value="1">政府采购</html-el:option>
        		<html-el:option value="2">酒店采购</html-el:option>
        		<html-el:option value="3">企业采购</html-el:option>
        		<html-el:option value="4">其他</html-el:option>
        	</html-el:select>
        	&nbsp;<strong class="fb">审核状态</strong>  
        	<html-el:select property="info_state" styleId="info_state" style="width:80px;" onchange="this.form.submit();">
        		<html-el:option value="">--请选择--</html-el:option>
        		<html-el:option value="-1">未提交</html-el:option>
        		<html-el:option value="0">审核中</html-el:option>
        		<html-el:option value="1">已完结</html-el:option>
        </html-el:select>
        &nbsp;<strong class="fb">单据状态</strong>  
        	<html-el:select property="is_validate" styleId="is_validate" style="width:80px;" onchange="this.form.submit();">
        		<html-el:option value="0">有效</html-el:option>
        		<html-el:option value="1">无效</html-el:option>
        </html-el:select>
        	</td></tr>
        <tr>
        	<td nowrap="nowrap">&nbsp;
        	<strong class="fb">采购尺寸</strong>
            <html-el:text property="size_like" styleId="size_like" size="5" maxlength="5" />
        	<strong class="fb">开标/报价日期</strong>
        	<html-el:text property="offer_date_start" styleId="offer_date_start"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
				至
				<html-el:text property="offer_date_end" styleId="offer_date_end"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
        	&nbsp;<strong class="fb">创建日期</strong>
        	<html-el:text property="create_date_start" styleId="create_date_start"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
				至
				<html-el:text property="create_date_end" styleId="create_date_end"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
        	&nbsp;<input class="but1" type="submit" name="Submit" value="搜索" />
        </td></tr>
      </table>
      </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>
          <logic-el:match name="popedom" value="+1+">
        <input style="align:left" name="button" type="button"  value="新增" class="but2" onclick="location.href='GcxmProj.do?method=add&mod_id=${af.map.mod_id}';" />
          </logic-el:match>
           <logic-el:match name="popedom" value="+4+">
          <input class="but3" type="submit" name="Submit3" value="删除" onclick="confirmDeleteAll(document.getElementById('listForm'));" />
          </logic-el:match>
          <logic-el:match name="popedom" value="+128+">
          <input class="but_excel" type="button" name="export_excel" id="export_excel" value="导出" />
         </logic-el:match>
        </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1" style="overflow: auto;">
    <form id="listForm" name="listForm" method="post" action="GcxmProj.do?method=delete">
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
        <tr class="tabtt1">
          <td width="5%" align="center"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
          <td width="5%" nowrap="nowrap" align="center">序号</td>
          <td width="5%" nowrap="nowrap" align="center">项目编号</td>
          <td width="10%" nowrap="nowrap" align="center">分公司</td>
          <td width="10%" nowrap="nowrap" align="center">项目类型</td>
          <td width="10%" nowrap="nowrap" align="center">项目名称</td>
          <td width="10%" nowrap="nowrap" align="center">采购人/投标人</td>
          <td width="10%" nowrap="nowrap" align="center">采购人/投标人电话</td>
          <td width="10%" nowrap="nowrap" align="center">开标/报价日期</td>
          <td width="10%" nowrap="nowrap" align="center">采购尺寸</td>
          <td width="10%" nowrap="nowrap" align="center">采购数量</td>
          <td width="10%" nowrap="nowrap" align="center">采购预算</td>
          <td width="10%" nowrap="nowrap" align="center">审核状态</td>
          <td width="10%" nowrap="nowrap" align="center">推荐机型1</td>
          <td width="10%" nowrap="nowrap" align="center">推荐机型2</td>
          <td width="10%" nowrap="nowrap" align="center">推荐机型3</td>
          <td width="10%" nowrap="nowrap" align="center">创建日期</td>
          <td width="10%" nowrap="nowrap" align="center">创建人</td>
          <td width="10%" nowrap="nowrap" align="center">单据状态</td>
          <td width="10%" nowrap="nowrap" align="center">操  作</td>
        </tr>    
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="center" nowrap="nowrap" > 
                <c:if test="${cur.del_mark eq 0 and cur.info_state eq -1}"><input name="pks" type="checkbox" id="pks_${cur.id}" value="${cur.id}" /></c:if>
                <c:if test="${cur.del_mark ne 0 or cur.info_state ne -1}"><input name="pks" type="checkbox" id="pks_${cur.id}" value="${cur.id}" disabled="disabled" /></c:if>
              </td>
              <td align="center" nowrap="nowrap"> ${vs.count}</td>
              <td nowrap="nowrap" align="left" ><a style="color: blue;" href="#" onclick="doNeedMethod(null, 'GcxmProj.do', 'view', 'id=${cur.id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())"> ${cur.proj_code}</a></td>
              <td align="center" nowrap="nowrap">${cur.map.dept_name}</td> 
              <td align="center" nowrap="nowrap"> 
              	<c:if test="${cur.proj_type eq 1}">政府采购</c:if>
				<c:if test="${cur.proj_type eq 2}">酒店采购</c:if>
				<c:if test="${cur.proj_type eq 3}">企业采购</c:if>
				<c:if test="${cur.proj_type eq 4}">其他</c:if>
              </td>
              <td align="left" nowrap="nowrap" title="${cur.proj_name}"> 
              <c:choose>
                 <c:when test="${fn:length(cur.proj_name) > 20}">
                 <c:out value="${fn:substring(cur.proj_name, 0, 20)}...." />
                 </c:when>
             <c:otherwise>
                 <c:out value="${cur.proj_name}" />
                 </c:otherwise>
             </c:choose>
             </td>
              <td align="left" nowrap="nowrap"> ${cur.buyer}</td>
              <td align="center" nowrap="nowrap"> ${cur.buyer_tel}</td>
              <td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.offer_date}"  pattern="yyyy-MM-dd"></fmt:formatDate></td>
              <td align="center" nowrap="nowrap"> ${cur.size}</td>
              <td align="left" nowrap="nowrap"> ${cur.buyer_num}</td>
              <td align="left" nowrap="nowrap"> ${cur.budget}</td>
              <td align="center" nowrap="nowrap"> 
              	<c:if test="${cur.info_state eq -1}">未提交</c:if>
				<c:if test="${cur.info_state eq 0}">审核中</c:if>
				<c:if test="${cur.info_state eq 1}">已完结</c:if>
              </td>
              <td align="center" nowrap="nowrap"> ${cur.map.model_1}</td>
              <td align="center" nowrap="nowrap"> ${cur.map.model_2}</td>
              <td align="center" nowrap="nowrap"> ${cur.map.model_3}</td>
              <td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.create_date}"  pattern="yyyy-MM-dd"></fmt:formatDate></td>
              <td align="center" nowrap="nowrap"> ${cur.create_name}</td>
                <td align="center" nowrap="nowrap">
                	<c:if test="${cur.is_validate eq 0}">有效</c:if>
                	<c:if test="${cur.is_validate eq 1}">无效</c:if>
                </td>
              <td align="center"  nowrap="nowrap">
              <c:if test="${cur.is_validate eq 0}">
              <c:if test="${cur.info_state eq 0 }">
                <c:choose>
                <c:when test="${cur.map.is_audit}">
                 <logic-el:match name="popedom" value="+8+">
                 <span style="cursor: pointer;color: blue;" onclick="doNeedMethod(null, 'GcxmProj.do', 'audit', 'id=${cur.id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())">审核</span>
                </logic-el:match>
                 <logic-el:notMatch name="popedom" value="+8+"><span style="color: #CCC">审核</span>
                 </logic-el:notMatch>
                 </c:when>
                <c:otherwise><span style="color: #CCC">审核</span></c:otherwise>
                </c:choose>
              </c:if>
              <c:if test="${cur.info_state ne 0 }"><span style="color: #CCC">审核</span> </c:if>
              |
              <c:if test="${cur.info_state eq -1 and cur.create_user_id eq af.map.user_id}">
               <logic-el:match name="popedom" value="+2+">
              <span style="cursor: pointer;color: blue;" onclick="confirmUpdate(null, 'GcxmProj.do', 'id=${cur.id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())">修改</span>
			  </logic-el:match>
			   <logic-el:notMatch name="popedom" value="+2+">
			    <span style="color: #CCC">修改</span>
			   </logic-el:notMatch>
			  </c:if>
			  <c:if test="${cur.info_state ne -1 or cur.create_user_id ne af.map.user_id}"> <span style="color: #CCC">修改</span> </c:if>
			  |	             
              <c:if test="${cur.info_state eq -1 and cur.create_user_id eq af.map.user_id}">
               <logic-el:match name="popedom" value="+4+">
              <span style="cursor: pointer;color: blue;" onclick="confirmDelete(null, 'GcxmProj.do', 'id=${cur.id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())">删除</span>
              </logic-el:match>
              <logic-el:notMatch name="popedom" value="+4+">
			    <span style="color: #CCC">删除</span>
			   </logic-el:notMatch>
              </c:if>
               <c:if test="${cur.info_state ne -1 || cur.create_user_id ne af.map.user_id}"> <span style="color: #CCC">删除</span> </c:if>
               |
               <c:if test="${cur.info_state eq 0 and cur.create_user_id eq af.map.user_id}">
               <logic-el:match name="popedom" value="+2+">
                <span style="cursor: pointer;color: blue;" onclick="doNeedMethod('您确定撤回吗？', 'GcxmProj.do', 'back', 'id=${cur.id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())">撤回</span>
              </logic-el:match>
              <logic-el:notMatch name="popedom" value="+2+">
			    <span style="color: #CCC">撤回</span>
			   </logic-el:notMatch>
              </c:if>
               <c:if test="${cur.info_state ne 0 || cur.create_user_id ne af.map.user_id}">
               <span style="color: #CCC">撤回</span>  
               </c:if>
               |
              <c:if test="${cur.gcxmProjSupply.info_state eq 1 }">
               <logic-el:match name="popedom" value="+4+">
              <span style="cursor: pointer;color: blue;" onclick="doNeedMethod('您确定无效吗？', 'GcxmProj.do', 'validate', 'id=${cur.id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())">无效</span>
              </logic-el:match>
               <logic-el:notMatch name="popedom" value="+4+">
			    <span style="color: #CCC">无效</span>
			   </logic-el:notMatch>
              </c:if>
              <c:if test="${!(cur.gcxmProjSupply.info_state eq 1)}">
              <span style="color: #CCC">无效</span>
              </c:if>
              </c:if>
               <c:if test="${cur.is_validate eq 1}">
               <span style="color: #CCC">审核</span> | <span style="color: #CCC">修改</span> |
               <span style="color: #CCC">删除</span> | <span style="color: #CCC">撤回</span> |
               <span style="color: #CCC">无效</span>
               </c:if>
              </td></tr>
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
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="GcxmProj.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
			var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");  
            pager.addHiddenInputs("fgs_id", "${fn:escapeXml(af.map.fgs_id)}");
			pager.addHiddenInputs("proj_code_like", "${af.map.proj_code_like}");
			pager.addHiddenInputs("proj_name_like", "${af.map.proj_name_like}");
			pager.addHiddenInputs("proj_type", "${af.map.proj_type}");
			pager.addHiddenInputs("buyer_like", "${af.map.buyer_like}");
			pager.addHiddenInputs("offer_date_start", "${af.map.offer_date_start}");
			pager.addHiddenInputs("offer_date_end", "${af.map.offer_date_end}");
			pager.addHiddenInputs("size_like", "${af.map.size_like}");
			pager.addHiddenInputs("info_state", "${af.map.info_state}");
			pager.addHiddenInputs("is_validate", "${af.map.is_validate}");
			pager.addHiddenInputs("create_date_start", "${af.map.create_date_start}");
			pager.addHiddenInputs("create_date_end", "${af.map.create_date_end}");
			pager.addHiddenInputs("create_name_like", "${af.map.create_name_like}");
			document.write(pager.toString());
            </script>
           </td>
        </tr>
      </table>
    </form>
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
			var forms =  document.forms[0];
	    	 this.value="正在提交...";
	    	 this.disabled=true;
	    	 forms.action="${ctx}/manager/admin/GcxmProj.do?method=sheet";
	    	 forms.submit();
	    	 forms.action="${ctx}/manager/admin/GcxmProj.do";
	    	 this.value="导出Excel";
	    	 this.disabled=false;
	    });  
		
   });                                      
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
