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
<link href="${ctx}/commons/styles/select2.min.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
  
    <html-el:form action="/admin/PeProdUser.do">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <table width="100%" border="0" cellspacing="3" cellpadding="0" class="rtable1">
        <tr style="height:25px;">
	          <td style="height:25px;padding-left: 20px" width="10%" nowrap="nowrap"><strong class="fb">部&nbsp;&nbsp;&nbsp;&nbsp;门：</strong></td>
	          <td>  
		            <html-el:select property="l3_dept_id" styleId="l3_dept_id" disabled="${disabled}">
		          		<html-el:option value="">-分公司/经营部-</html-el:option>
		          	</html-el:select>
		          	&nbsp;
		          	<html-el:select property="l4_dept_id" styleId="l4_dept_id">
		          		<html-el:option value="">-请选择下级部门-</html-el:option>
		          	</html-el:select>
		          	&nbsp;
		          	<html-el:select property="l5_dept_id" styleId="l5_dept_id">
		          		<html-el:option value="">-请选择下级部门-</html-el:option>
		          	</html-el:select>
		          	&nbsp;<label for="contains_sub_depts"><html-el:checkbox property="contains_sub_depts" styleId="contains_sub_depts" /> 包含子部门</label>
	         </td>
	         <td style="height:25px;padding-left: 20px" width="10%"><strong class="fb">创建时间：</strong></td>
	         <td>
	          	 	<html-el:text property="add_date_s" styleId="add_date_s"  size="12" maxlength="10" readonly="true" onclick="new Calendar(2010, 2050, 0).show(this);" style="cursor:pointer;text-align:center; width:100px;" title="点击选择日期" />->
					<html-el:text property="add_date_e" styleId="add_date_e"  size="12" maxlength="10" readonly="true" onclick="new Calendar(2010, 2050, 0).show(this);" style="cursor:pointer;text-align:center; width:100px;" title="点击选择日期" />
	         </td>
	          <td style="padding-left: 20px" width="10%"><strong class="fb">类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型：</strong></td>
	          <td>
	          	<html-el:select property="sales_type" styleId="sales_type" style="width:103px" onchange="this.form.submit();">
	                	<html-el:option value="">请选择...</html-el:option>
	                	<html-el:option value="1">兼职</html-el:option>
	                	<html-el:option value="2">全职</html-el:option>
	              </html-el:select>
	          </td>
         </tr>
         
         <tr>
          <td style="padding-left: 20px" width="10%"><strong class="fb">岗&nbsp;位&nbsp;ID：</strong></td>
          <td><html-el:text property="job_id" styleId="job_id" style="width:190px;" maxlength="30"/></td>
          <td style="padding-left: 20px" width="10%"><strong class="fb">人员姓名：</strong></td>
          <td><html-el:text property="name_like" styleId="name_like" style="width:100px;" maxlength="60"/></td>
          
          <td style="padding-left: 20px" width="10%"><strong class="fb">R3人员编码：</strong></td>
          <td>
	            <html-el:text property="r3_job_id" styleId="r3_job_id" style="width:100px;" maxlength="30"/>
          </td>
         </tr>
          
         <tr>
          <td style="padding-left: 20px" width="10%"><strong class="fb">职&nbsp;&nbsp;&nbsp;&nbsp;务：</strong></td>
          <td colspan="1">
	            <html-el:select property="role_id" styleId="role_id" style="width:193px">
	              <html-el:option value="">请选择...</html-el:option>
	              <c:forEach var="cur" items="${roleInfoList}">
	                <html-el:option value="${cur.role_id}">${fn:escapeXml(cur.role_name)}</html-el:option>
	              </c:forEach>
	            </html-el:select>
          </td>
          <td style="padding-left: 20px" width="10%"><strong class="fb">是否停用：</strong></td>
          <td>
          	<html-el:select property="is_del" styleId="is_del" style="width:103px" onchange="this.form.submit();">
            	<html-el:option value="0">未停用</html-el:option>
                <html-el:option value="1">已停用</html-el:option>
            </html-el:select>
          </td>
          <td colspan="2" style="padding-left: 20px"><input class="but1" id="sb1" type="submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  
  
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>
         <logic-el:match name="popedom" value="+1+">
        <input class="but2" type="submit" name="Submit2" value="新增" onclick="location.href='PeProdUser.do?method=add&mod_id=${af.map.mod_id}&tree_param=${tree_param}';return false;" />
        <input type="button" value="导出" id="export_excel" class="btn_download" style="margin-left: 10px;" />
        <input type="button" value="历史" id="export_excel_all" class="btn_download" style="margin-left: 10px;"/>
        </logic-el:match>
        </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
  <div style="overflow-x: auto">
    <form id="listForm" name="listForm" method="post" action="PeProdUser.do">
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
          <tr class="tabtt1">
            <td width="3%" nowrap="nowrap" align="center"><font class="blue">序号</font></td>
            <td width="10%" nowrap="nowrap" align="center"><font class="blue">岗位ID</font></td>
            <td width="8%" nowrap="nowrap" align="center"><font class="blue">登录名</font></td>
            <td width="8%" nowrap="nowrap" align="center"><font class="blue">在岗人员</font></td>
            <td width="10%" nowrap="nowrap" align="center"><font class="blue">R3人员编码</font></td>
            <td width="20%" nowrap="nowrap" align="center"><font class="blue">部门</font></td>
            <td width="20%" nowrap="nowrap" align="center" ><font class="blue">职务</font></td>
            <td width="5%" nowrap="nowrap" align="center" ><font class="blue">类型</font></td>
            <td width="5%" nowrap="nowrap" align="center" ><font class="blue">离职率</font></td>
            <td width="10%" nowrap="nowrap" align="center"><font class="blue">手机/电话</font></td>
            <td width="10%" nowrap="nowrap" align="center" ><font class="blue">添加日期</font></td>
            <td width="6%" nowrap="nowrap" align="center"><font class="blue">登陆次数</font></td>
            <!-- <td width="6%" nowrap="nowrap" align="center"><font class="blue">照片</font></td> -->
            <td width="6%" nowrap="nowrap" align="center"><font class="blue">客户端显示</font></td>
            <td width="10%" align="center"><font class="blue">操作</font></td>
          </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <c:if test="${cur.user_type eq 99}">
              <c:set var="is_admin" value="true" />
            </c:if>
            <tr>
              <td height="28"  align="center">${vs.count}</td>
              <td align="left"><c:out value="${cur.job_id}" /><c:if test="${empty cur.job_id}"><span style="color:#F00;">未填写</span></c:if></td>
              <td align="left"><c:out value="${cur.user_name}" /></td>
              <td align="left" valign="middle"><a style="text-decoration:underline;" href="PeProdUser.do?method=view&user_id=${cur.id}&role_id=${cur.map.role_id}&mod_id=${af.map.mod_id}&tree_param=${tree_param}">${cur.real_name}</a></td>
              <td align="left"><c:out value="${cur.r3_job_id}" /></td>
              <td align="left"><c:out value="${cur.map.full_dept_name}" /></td>
              <td align="left"><c:out value="${cur.map.role_name}" /></td>
              <td align="left">
              	<c:if test="${cur.map.sales_type eq 1 }">兼职</c:if> 
              	<c:if test="${cur.map.sales_type eq 2 }">全职</c:if>
              </td>
              <td align="center"><c:out value="${cur.map.lzl}%" /></td> 
              <td align="center"><c:out value="${not empty cur.link_phone ? cur.link_phone : (not empty cur.link_tel ? cur.link_tel : '')}" /></td>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" /></td>
              <td align="right"><c:out value="${cur.login_count}" /></td>
              <!-- <td align="center"><a href="${ctx}/${cur.map.sava_path}" target="_blank" >${cur.map.file_name}</a></td> -->
              <td align="center">
             
              <c:if test="${ fn:contains(cur.map.role_name,'IHS经理') or fn:contains(cur.map.role_name,'IHS内勤')  }">
              <c:if test="${cur.is_show eq  1}"> <input type="checkbox" name="is_show" checked="checked" onclick="show(this,'${cur.id}');" /></c:if>
              <c:if test="${cur.is_show eq  0}"> <input type="checkbox" name="is_show" onclick="show(this,'${cur.id}');" /></c:if>
              </c:if>
              </td>  
              <td nowrap="nowrap">
              <c:if test="${cur.is_del eq 0}">
                   <logic-el:match name="popedom" value="+2+">
                   	<span style="cursor:pointer;" class="fblue" onclick="if(confirm('是否重置？')){doNeedMethod(null, 'PeProdUser.do', 'initPassword','user_id=${cur.id}&'+$('#bottomPageForm').serialize())}" title="重置密码">重置</span>
                   </logic-el:match>
                   <logic-el:notMatch name="popedom" value="+2+">
                   	<span style="color:#ccc" class="fblue" title="重置密码">重置</span>
                   </logic-el:notMatch>
                   |
                  <c:if test="${userInfo.id eq 1 or userInfo.id eq 0 
                  or userInfo.id eq 23795
                  or userInfo.id eq 18942 
                  or userInfo.id eq 23794 
                  or userInfo.id eq 51694 
                  or userInfo.id eq 51695
                  or userInfo.id eq 33556
                  or userInfo.id eq 72145}"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'PeProdUser.do', 'userLogin','user_id=${cur.id}&'+$('#bottomPageForm').serialize())">登陆</span> | </c:if>
                  <c:if test="${1 eq 2 }"> <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'SetModPopedom.do', 'edit', 'mod_id=${af.map.mod_id}&user_id=${cur.id}&url=PeProdUser&'+$('#bottomPageForm').serialize())">授权</span> | </c:if>
                  
                  <logic-el:match name="popedom" value="+2+">
                  <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'PeProdUser.do', 'edit','user_id=${cur.id}&'+$('#bottomPageForm').serialize())">修改</span><br/>
                  <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'PeProdUser.do', 'change','job_id=${cur.job_id}&'+$('#bottomPageForm').serialize())">历史</span>
                  </logic-el:match>
                  <logic-el:notMatch name="popedom" value="+2+">
                  <span style="color:#CCC;" class="fblue">修改</span><br/>
                  </logic-el:notMatch>
                  <logic-el:match name="popedom" value="+4+">
                 | <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('是否将该人员停用?', 'PeProdUser.do','stop','user_id=${cur.id}&'+$('#bottomPageForm').serialize())">停用</span>
                  </logic-el:match>
              </c:if>
              <c:if test="${cur.is_del eq 1}">
                <span style="color:#ccc" class="fblue" title="重置密码">重置</span>
                <span style="color:#CCC;" class="fblue">修改</span>  
                <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('确定启用吗？', 'PeProdUser.do', 'reStart','user_id=${cur.id}&'+$('#bottomPageForm').serialize())">启用</span>
              </c:if></td>
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
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="PeProdUser.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("job_id", "${af.map.job_id}");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("tree_param", "${tree_param}");
	            pager.addHiddenInputs("name_like", "${fn:escapeXml(af.map.name_like)}");
	            pager.addHiddenInputs("position_id", "${af.map.position_id}");
	            pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
	            pager.addHiddenInputs("role_id", "${af.map.role_id}");
	            pager.addHiddenInputs("is_del", "${af.map.is_del}");
	            pager.addHiddenInputs("add_date_s", "${af.map.add_date_s}");
	            pager.addHiddenInputs("add_date_e", "${af.map.add_date_e}");					
				pager.addHiddenInputs("l3_dept_id", "${af.map.l3_dept_id}");							
				pager.addHiddenInputs("l4_dept_id", "${af.map.l4_dept_id}");							
				pager.addHiddenInputs("l5_dept_id", "${af.map.l5_dept_id}");	
				pager.addHiddenInputs("contains_sub_depts", "${af.map.contains_sub_depts}");	
				pager.addHiddenInputs("sales_type", "${af.map.sales_type}");
				pager.addHiddenInputs("r3_job_id", "${af.map.r3_job_id}");
	            document.write(pager.toString());
	      </script></td>
        </tr>
      </table>
    </form>
    </div>
  </div>
  <div class="clear"></div>
  <div class="rtabcont1" style="overflow-x: auto; display: none;" id="divExcel_all_1" title="岗位列表">
  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
          <tr class="tabtt1">
            <td width="30" nowrap="nowrap" ><font class="blue">序号</font></td>
            <td nowrap="nowrap" ><font class="blue">岗位ID</font></td>
            <td nowrap="nowrap" ><font class="blue">原登录名</font></td>
            <td nowrap="nowrap" ><font class="blue">原员工姓名</font></td>
            <td nowrap="nowrap"><font class="blue">新登录名</font></td>
            <td nowrap="nowrap"><font class="blue">新员工姓名</font></td>
            <td nowrap="nowrap"><font class="blue">开始时间</font></td>
            <td nowrap="nowrap"><font class="blue">结束时间</font></td>
          </tr>
          <c:forEach var="cur" items="${allList_1}" varStatus="vs">
            <tr>
              <td align="center">${vs.count}</td>
                <td align="left" >${cur.job_id}</td>
                <td align="left">${cur.user_name_old}</td>
                <td align="left">${cur.real_name_old}</td>
                 <td align="left">${cur.user_name_new}</td>
                <td align="left">${cur.real_name_new}</td>
                <td align="left">
                <fmt:formatDate value="${cur.start_date}" pattern="yyyy-MM-dd HH:mm:ss" />
               </td>
                <td align="left">
               <c:if test="${not empty cur.end_date}"><fmt:formatDate value="${cur.end_date}" pattern="yyyy-MM-dd HH:mm:ss" /></c:if>
               <c:if test="${empty cur.end_date}">至今</c:if> 
                </td>
            </tr>
          </c:forEach>
      </table>
    </div>
    <div class="clear"></div>
     <div class="rtabcont1" style="overflow-x: auto; display: none;" id="divExcel_all" title="岗位列表">
  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
          <tr class="tabtt1">
            <td width="30" nowrap="nowrap" ><font class="blue">序号</font></td>
            <td nowrap="nowrap" ><font class="blue">岗位ID</font></td>
            <td nowrap="nowrap" ><font class="blue">登录名</font></td>
            <td nowrap="nowrap" ><font class="blue">在岗人员</font></td>
            <td nowrap="nowrap"><font class="blue">部门</font></td>
            <td nowrap="nowrap" ><font class="blue">职务</font></td>
            <td nowrap="nowrap" align="center"><font class="blue">手机/电话</font></td>
          </tr>
          <c:forEach var="cur" items="${allList}" varStatus="vs">
            <c:if test="${cur.user_type eq 99}">
              <c:set var="is_admin" value="true" />
            </c:if>
            <tr>
              <td height="28"  align="center">${vs.count}</td>
              <td align="left"><c:out value="${cur.job_id}" /><c:if test="${empty cur.job_id}"><span style="color:#F00;">未填写</span></c:if></td>
              <td align="left"><c:out value="${cur.user_name}" /></td>
              <td align="left" valign="middle">${cur.real_name}</td>
              <td align="left"><c:out value="${cur.map.full_dept_name}" /></td>
              <td align="left"><c:out value="${cur.map.role_name}" /></td>
              <td align="center"><c:out value="${not empty cur.link_phone ? cur.link_phone : (not empty cur.link_tel ? cur.link_tel : '')}" /></td>
            </tr>
          </c:forEach>
      </table>
    </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/select2.min.js?t=1"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#job_id").keyup(function(){
		var job_id = $(this).val();
		$(this).val(SBC2DBC(job_id));
	});
	$("#name_like").keyup(function(){
		var job_id = $(this).val();
		$(this).val(SBC2DBC(job_id));
	});
	
	$("#l3_dept_id").attr({"subElement": "l4_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l3_dept_id}"});
	$("#l4_dept_id"    ).attr({"subElement": "l5_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
	$("#l5_dept_id" ).attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l5_dept_id}"});

	$("#l3_dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "${empty cs_par_id ? 0 : cs_par_id}");
	$("#l3_dept_id").change();
	
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
   
	 $("#export_excel").click(function(){
	    	if(!confirm("提示，您确认导出数据？")){
	    		return false;
	    	}
	    	$("#bottomPageForm").append("<input type='hidden' name='excel_all' value='1' id='excel_all' />");
			$("#bottomPageForm").submit();
			$("#excel_all").val("");
	 });

	 var excel_all = "${af.map.excel_all}";
		if("1" == excel_all){
			toExcel('divExcel_all', '?method=toExcel');
		}

	 $("#export_excel_all").click(function(){
	    	if(!confirm("提示，您确认导出数据？")){
	    		return false;
	    	}
	    	location.href = "${ctx}/manager/admin/PeProdUser.do?method=listForChange&excel_all_1=1&mod_id="+${af.map.mod_id};
	 });

	 var excel_all_1= "${af.map.excel_all_1}";
		if("1" == excel_all_1){
			toExcel('divExcel_all_1', '?method=toExcel');
		}	
    
	// 可查询选择框
	$('#role_id').select2({
	    minimumInputLength: 2,
	    allowClear: true
	});
});

//全角转换成半角
function SBC2DBC(str){
	var   i;  
	var   result= '';  
	for(i=0;i <str.length;i++)   {
		var  code=str.charCodeAt(i)
		//“65281”是“！”，“65373”是“｝”
		if(code >= 65281&&code < 65373){
			//     “65248”是转换码距
			result+=String.fromCharCode(str.charCodeAt(i)-65248);
		} else {
			result+=str.charAt(i);
		}
	}  
	return result;  
}


function show(obj,id){
	var state;
	if(obj.checked){
		state=1;//选中
	}else{
		state=0;//未选中
	}
	
	$.ajax({
		type: "POST",
		url: "${ctx}/manager/admin/PeProdUser.do",
		data: "method=ajaxUpdate&is_show="+state+"&id="+id,
		dataType: "json",
		success:function(result) {
			if(result==0){
				 alert("操作成功！");
				 document.getElementById('sb1').form.submit(); 
			}else if(result==1){
				alert("无效的用户！");
				document.getElementById('sb1').form.submit(); 
			}else{
				alert("操作失败！");
				document.getElementById('sb1').form.submit(); 
			}
		}
	});
	
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
