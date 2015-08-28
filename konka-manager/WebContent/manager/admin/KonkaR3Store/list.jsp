<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
<style type="text/css">
.rtable1 td {
	padding:2px 5px;
	
}
.filed_border{
	border-left: 1px solid #ccc;;
	border-right: 1px solid #ccc;;
	border-bottom:1px solid #ccc;;
}
</style>
</head>
<body>
<div class="oarcont" id="body_oarcont" style="position:relative;overflow:hidden;">
<div class="oartop">
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
      <td>当前位置：${naviString}</td>
      <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
    </tr>
  </table>
</div>
<div class="rtabcont1" style="width:100%;overflow-x:auto;"> 
  <fieldset style="border-top:1px solid #ccc;padding-left:10px;" id ="fs_hide">
  	<legend id="l_toggle" style="cursor: pointer;" title="点击查看说明">说明</legend>
  	<ol style="margin-left:20px; padding-left:20px; list-style:decimal;display: none;" id ="ol_hide">
  		<li>“门店所在经办”为门店隶属客户的上级主管业务员所在的经办；</li>
  		<li>默认您可以查询到您所在部门（包含子部门）的门店；</li>
  		<li>如果您的“系统角色”指定了“数据级别”，依据您所有系统角色中“最大数据级别”列出的门店将包括在您的查询结果内；</li>
  		<li>如果您的“分公司角色”指定了“数据透视部门”，所有透视部门向下（包含子部门）的全部门店将包括在您的查询结果内；</li>
  		<li>如果您被分派为某些门店的“业务员”、“业务主管”或“经办经理”，这些门店将包括在您的查询结果内；</li>
  		<li>如果您的数据级别为最小级别（无部门数据查询权限），将视您为客户的业务员作查询。</li>
  	</ol>
  </fieldset>
  <br />
  <html-el:form action="/admin/KonkaR3Store">
    <html-el:hidden property="method" value="list" />
    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
    <html-el:hidden property="r3_code" value="${af.map.r3_code}" />
    <html-el:hidden property="current_fgs_code" styleId="current_fgs_code" value="${current_fgs_code}" />
    <c:if test="${empty only_cxy}">
    <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
      <tr>
      	<td nowrap="nowrap" align="right">
			<strong class="fb">分公司：</strong>
        </td>
        <td>
	       	<html-el:select property="dept_id" styleId="dept_id" style="width:110px" onchange="fgs_id_jb_name();">
              <html-el:option value="">-请选择-</html-el:option>
            </html-el:select>
            
			<html-el:select property="jb_job_id" styleId="jb_job_id">
				<html-el:option value="">-请选择-</html-el:option>
			</html-el:select>
									
        </td>
        <td align="right">
        	<strong class="fb">门店名称：</strong>
        </td>
        <td>
          <html-el:text property="store_name_like" maxlength="40" size="13" />
        </td>
        <td  align="right" >
          <strong class="fb">门店编号：</strong>
        </td>
        <td>
          <html-el:text property="store_id" maxlength="20" size="13" />
        </td>
        <td align="right" >
          <strong class="fb">业务员：</strong>
        </td>
        <td>
          <html-el:text property="ywy_name_like" maxlength="20" size="13" />
        </td>
     </tr>
     <tr>
     	<td align="right" >
          <strong class="fb">促销员：</strong>
        </td>
        <td>
          <html-el:text property="zxy_name_like" maxlength="20" size="13" />
        </td>
     	<td align="right">
          <strong class="fb"> 客户R3编码：</strong>
        </td>
        <td>
          <html-el:text property="r3_code_like" maxlength="40" size="13" value='${af.map.r3_code_like}'/>
        </td>
        <td valign="middle" nowrap="nowrap" class="title_item" align="right">客户类型：</td>
		<td >
       		<html-el:select property="v_customer_type1" styleId="v_customer_type1" style="width:80px;">
            	<html-el:option value="">-请选择-</html-el:option>
            </html-el:select>
            <html-el:select property="v_customer_type2" styleId="v_customer_type2" style="width:130px;">
                <html-el:option value="">-请选择-</html-el:option>
            </html-el:select>
		</td>
        <td align="right">
            <strong class="fb">是否停用：</strong>
        </td>
        <td>
             <html-el:select property="is_del" styleId="is_del" style="width:110px" onchange="this.form.submit();">
              <html-el:option value="">-请选择-</html-el:option>
              <html-el:option value="0">未停用</html-el:option>
               <html-el:option value="1">已停用</html-el:option>
             </html-el:select> 
        </td>
      </tr>
      <tr>
      	<td align="right">
          <strong class="fb">经办名称：</strong>
        </td>
        <td>
          <html-el:text property="jing_ban_like" maxlength="40" size="13" />
        </td>
      	<td align="right">
          <strong class="fb">客户名称：</strong>
        </td>
        <td>
          <html-el:text property="kh_name_like" maxlength="40" size="13" />
        </td>
      	<td align="right">
          <strong class="fb">有/无促销员：</strong>
        </td>
        <td>
          <html-el:select property="havaman" styleId="havaman" style="width:110px" onchange="this.form.submit();">
          	<html-el:option value="">-请选择-</html-el:option>
          	<html-el:option value="1">有</html-el:option>
            <html-el:option value="0">无</html-el:option>
          </html-el:select>
        </td>
      	<td align="right">
      		<strong class="fb">确认状态：</strong>
      	</td>
      	<td>
		  	<html-el:select property="is_sure" styleId="is_sure" style="width:110px">
	         	<html-el:option value="">-请选择-</html-el:option>
	         	<html-el:option value="1">待确认</html-el:option>
	         	<html-el:option value="2">已确认</html-el:option>
	         </html-el:select>
        </td>
      </tr>
      <tr >
      	<td colspan="8" align="right" style="padding-right: 50px"> <input class="but1" type="submit" name="Submit" value="搜索" /></td>
      </tr>
    </table>
    </c:if>
  </html-el:form>
  </div>
  
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <c:if test="${empty only_cxy}">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
    
      <tr>
        <td>
       		<div style="color:#F00;font-weight:700;">注：请及时修改因人员变动导致信息不准确门店资料，其中重要的信息有业务员资料、业务主管资料和经办经理资料。</div>
		    <logic-el:match name="popedom" value="+1+">  
		    <input class="but2" type="button" name="Submit2" value="新增" onclick="location.href='KonkaR3Store.do?method=add&mod_id=${af.map.mod_id}&r3_code=${empty konkaR3Shop ? '' : konkaR3Shop.r3_code}';" />
		    <input type="button" id="export_22" value="导出" class="but_excel" />
		    </logic-el:match>
		</td>
	 </tr>
	</table>
</c:if>
  </div>
  <div class="rtabcont1" style="overflow-x:auto;">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" align="center" nowrap="nowrap">序号</td>
          <!-- <td>门店ID</td> -->
          <td width="5%" align="center" nowrap="nowrap">分公司</td>
          <td align="center" nowrap="nowrap">门店名称</td>
          <td width="6%" align="center" nowrap="nowrap">门店编号</td>
          <td width="10%" align="center" nowrap="nowrap">客户名称</td>
          <td width="5%" align="center" nowrap="nowrap">客户R3编码</td>
          <td align="center" nowrap="nowrap">客户类型</td>
          <td align="center" nowrap="nowrap">细分类型</td>
          <td width="5%" align="center" nowrap="nowrap">业务员岗位</td>
          <td width="5%" align="center" nowrap="nowrap">业务员姓名</td>
          <td align="center" nowrap="nowrap">有/无促销员</td>
          <td align="center" nowrap="nowrap">促销员姓名</td>
          <td align="center" nowrap="nowrap">人员类型</td>
          <td width="5%" align="center" nowrap="nowrap">经办名称</td>
          <!-- <td width="250px" align="center">地区</td> -->
          <td width="5%" align="center" nowrap="nowrap">省</td>
          <td width="5%" align="center" nowrap="nowrap">市</td>
          <td width="5%" align="center" nowrap="nowrap">县</td>
          <td width="5%" align="center" nowrap="nowrap">乡/镇</td>
          <td width="5%" align="center" nowrap="nowrap">仓库名</td>
          <td width="5%" align="center" nowrap="nowrap">仓库送达方编码</td>
          <td width="5%" align="center" nowrap="nowrap">门店R3编码</td>
          <td nowrap="nowrap" width="5%">状态</td>
          <td nowrap="nowrap" width="5%">是否确认</td>
          <td width="5%" align="center" nowrap="nowrap">创建时间</td>
          <td width="5%" align="center" nowrap="nowrap">维护时间</td>
          <td width="5%" align="center" nowrap="nowrap">创建人</td>
          <td width="5%" align="center" nowrap="nowrap">维护人</td>
          <td width="5%" nowrap="nowrap" align="center">操作</td>
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td nowrap="nowrap" align="center">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
              <td align="center" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.DEPT_NAME)}</font></td>
              <td align="left" nowrap="nowrap">
              	<span title="点击可查看该门店详情" style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaR3Store.do', 'view','store_id=${cur.STORE_ID}&'+$('#bottomPageForm').serialize())"><font class="blue12px">${cur.STORE_NAME}</font></span>
              </td>
              <td align="right" nowrap="nowrap"><font class="blue12px">${cur.STORE_ID}</font></td>
              <td align="left" nowrap="nowrap" title="${cur.KH_NAME}"><font class="blue12px">${fn:escapeXml(cur.KH_NAME)}</font></td>
              <td align="left" nowrap="nowrap"><a href="${ctx}/manager/admin/KonkaR3Store.do?mod_id=${af.map.mod_id}&r3_code=${cur.R3_CODE}" class="fblue" title="点击可查询该客户全部门店">${fn:escapeXml(cur.R3_CODE)}</a></td>
              <td align="left" nowrap="nowrap">${cur.CUSTOMER_TYPE1}</td>
              <td align="left" nowrap="nowrap">${cur.CUSTOMER_TYPE2}</td>
              <td align="left" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.YWY_JOB_ID)}</font></td>
              <td align="left" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.YWY_NAME)}</font></td>
              <td align="center"><font class="blue12px">${cur.HAVAMAN}</font></td>
              <td align="left" nowrap="nowrap">
              	<font class="blue12px">${cur.CXY_NAME_ALL}</font>
              </td>
              <td align="left" nowrap="nowrap">
              	<font class="blue12px">${cur.CXY_TYPE_NAME}</font>
              </td>
              <td align="left" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.JB_NAME)}</font></td>
              <td align="left" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.PROVINCE)}</font></td>
              <td align="left" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.CITY)}</font></td>
              <td align="left" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.COUNTRY)}</font></td>
              <td align="left" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.TOWN)}</font></td>
              <td align="left" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.CK_NAME)}</font></td>
              <td align="left" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.MF_SN)}</font></td>
              <td align="left" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.R3_SDF_SN)}</font></td>
              <td>
              	<c:if test="${cur.IS_DEL eq 0 }">启用</c:if>
              	<c:if test="${cur.IS_DEL eq 1 }">停用</c:if>
              </td>
              <td>
              	<c:choose>
					<c:when test="${cur.STATUS eq '0' }">待确认</c:when>
					<c:when test="${cur.STATUS eq '2' }">已确认</c:when>
					<c:otherwise>已确认</c:otherwise>
				</c:choose>
              </td>
              <td align="left" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.ADD_DATE)}</font></td>
              <td align="left" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.MODIFY_DATE)}</font></td>
              <td align="left" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.CREATE_MAN)}</font></td>
              <td align="left" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.MODIFY_MAN)}</font></td>
              <td align="center" nowrap="nowrap">
              	<c:if test="${cur.IS_DEL eq 0}">
              	<!-- <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaR3Store.do', 'view','store_id=${cur.STORE_ID}&'+$('#bottomPageForm').serialize())">查看</span>| -->
              		<logic-el:match name="popedom" value="+2+">
              			<span class="fblue" style="cursor:pointer;" onclick="doNeedMethod(null, 'KonkaR3Store.do', 'edit','store_id=${cur.STORE_ID}&'+$('#bottomPageForm').serialize())">修改</span>
              		</logic-el:match>
              		<logic-el:notMatch name="popedom" value="+2+">
              			<span class="fblue" style="color:#ccc;">修改</span>
              		</logic-el:notMatch>
              		<logic-el:match name="popedom" value="+4+">
              			<span class="fblue" style="cursor:pointer;" onclick="stopOrstart('${cur.STORE_ID}','stop');">停用</span>
              		</logic-el:match>
			  		<logic-el:notMatch name="popedom" value="+4+">
			  			<span class="fblue" style="color:#ccc;">停用</span>
			  		</logic-el:notMatch>
			  		<c:choose>
						<c:when test="${cur.STATUS eq '0' }">
							<c:if test="${user_role eq 0 }">
								<span style="cursor:pointer;" class="fblue" onClick="surePage('${cur.AUDIT_ID}');">确认</span>
							</c:if>
							<c:if test="${user_role eq 1}">
								<span style="color:#CCC;">确认</span>
							</c:if>	
						</c:when>
						<c:when test="${cur.STATUS eq '2' }">
							<span style="color:#CCC;">确认</span>
						</c:when>
						<c:otherwise><span style="color:#CCC;">确认</span></c:otherwise>
					</c:choose>
			  	</c:if>
			  	<c:if test="${cur.IS_DEL eq 1}">
			  		<span class="fblue" style="color:#ccc;">修改</span>
			  		<logic-el:match name="popedom" value="+4+">
			  			<span class="fblue" style="cursor:pointer;" onclick="stopOrstart('${cur.STORE_ID}','start');">启用</span>
			  		</logic-el:match>	
			  		<c:choose>
						<c:when test="${cur.STATUS eq '0' }">
							<c:if test="${user_role eq 0 }">
								<span style="cursor:pointer;" class="fblue" onClick="surePage('${cur.AUDIT_ID}');">确认</span>
							</c:if>
							<c:if test="${user_role eq 1}">
								<span style="color:#CCC;">确认</span>
							</c:if>	
						</c:when>
						<c:when test="${cur.STATUS eq '2' }">
							<span style="color:#CCC;">确认</span>
						</c:when>
						<c:otherwise><span style="color:#CCC;">确认</span></c:otherwise>
					</c:choose>
			  </c:if>
			  
              </td>
            </tr>
            <c:if test="${vs.last eq true}">
              <c:set var="i" value="${vs.count}" />
            </c:if>
          </c:forEach>
         </tbody>
      </table>
	  <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaR3Store.do">
	      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	        <tr>
	          <td height="60" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
	            <script type="text/javascript">
			            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
			            pager.addHiddenInputs("method", "list");
			            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			            pager.addHiddenInputs("tree_param", "${tree_param}");
			            pager.addHiddenInputs("r3_code_like", "${af.map.r3_code_like}");
			            pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
			            pager.addHiddenInputs("jb_job_id", "${af.map.jb_job_id}");
			            pager.addHiddenInputs("store_name_like", "${af.map.store_name_like}");
			            pager.addHiddenInputs("ywy_name_like","${af.map.ywy_name_like}");
			            pager.addHiddenInputs("zxy_name_like","${af.map.zxy_name_like}");
			            pager.addHiddenInputs("is_del", "${af.map.is_del}");
			            pager.addHiddenInputs("r3_code", "${af.map.r3_code}");
			            pager.addHiddenInputs("store_id", "${af.map.store_id}");
			            pager.addHiddenInputs("v_customer_type1", "${af.map.v_customer_type1}");
			            pager.addHiddenInputs("v_customer_type2", "${af.map.v_customer_type2}");
			            pager.addHiddenInputs("jing_ban_like", "${af.map.jing_ban_like}");
			            pager.addHiddenInputs("kh_name_like", "${af.map.kh_name_like}");
			            pager.addHiddenInputs("havaman", "${af.map.havaman}");
			            document.write(pager.toString());
			   </script></td>
	        </tr>
	      </table>
	    </form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=discuz"></script> 
<script type="text/javascript">//<![CDATA[
                                          
	function stopOrstart(id,flag){
		$.dialog({
			title:  "停用/启用申请",
			width:  570,
			height: 200,
	        lock:true ,
			content:"url:WaitAuditList/audit_Form.jsp?id="+id+"&flag="+flag+"&type=store"
		});
	}
	
	//确认窗口
    function surePage(id){
    	
    	$.dialog({
			title:  "停用/启用申请",
			width:  570,
			height: 240,
	        lock:true ,
			content:"url:WaitAuditList/audit_Form.jsp?id="+id+"&type=store&sure=sure"
		});
    }
                                          
$(document).ready(function() {

	$("#l_toggle").click(function(){
		$("#ol_hide").toggle();
		$("#fs_hide").toggleClass("filed_border");
		window.parent.resizeFrameHeight('mainFrame', 3);
	});
	
	//导出
	$("#export_22").click(function(){
		if(confirm("提示，您确认导出数据？")){
			//CNZZ统计代码
			$("#bottomPageForm").append("<input type='hidden' name='excel_to_all' value='1' id='excel_to_all' />");
    		$("#bottomPageForm").submit();
    		$("#excel_to_all").val("");	
		}
	});

	//客户类型初始化
	$("#v_customer_type1").attr({"subElement": "v_customer_type2", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.v_customer_type1}"});
	$("#v_customer_type2").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.v_customer_type2}"});

	$("#v_customer_type1").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaShopType", "par_id", "0", false);
	$("#v_customer_type1").change();

	//分公司列表初始化
	$("#dept_id").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.dept_id}"});
	$("#dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "0", false);
	$("#dept_id").change();
	

	//默认当前分公司
	var fgs = $("#current_fgs_code").val();
	if(fgs!=""){
		$("#dept_id").val(fgs);
		$("#dept_id").change();
	}
});    

function fgs_id_jb_name(){
	var fgs_id = $("#dept_id").val();
	if ($.trim(fgs_id).length == 0) {
		return;
	}
	$("#jb_job_id").empty();
	$("<option value=''>-请选择-</option>").appendTo($("#jb_job_id"));
	var url = "${ctx}/manager/admin/CsAjax.do?method=getJybDeptListByFgsId2&fgs_dept_id="+$('#dept_id').val();
	$.getJSON(url, function(data) {
		if(data != null){
			$.each(data, function(i, item) {
				var option = $("<option></option>").val(item[1]).text(item[0]);
				option.appendTo($("#jb_job_id"));
			});
			if('${af.map.jb__id}' != null || '${af.map.jb_job_id}' != '' ){
				$("#jb_job_id").val('${af.map.jb_job_id}');
			}
		}
	});
}   

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
