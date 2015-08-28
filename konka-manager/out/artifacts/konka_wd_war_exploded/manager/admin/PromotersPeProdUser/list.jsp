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
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/PromotersPeProdUser.do">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr style="height:25px;">
        	<td style="padding-left: 20px"><strong class="fb">姓&nbsp;&nbsp;&nbsp;&nbsp;名：</strong></td>
          	<td style="height:25px;" nowrap="nowrap">
            	<html-el:text property="name_like" styleId="name_like" style="width:140px;" />
            </td>
            <td><strong class="fb">岗位ID：</strong></td>
            <td><html-el:text property="job_like" styleId="job_like" style="width:140px;" /></td>
            <c:if test="${empty role_id_eq_10}">
            	<td><strong class="fb">是否删除：</strong></td>
              	<td>
              		<html-el:select property="is_del" styleId="is_del" style="width:70px;">
                		<html-el:option value="0">未删除</html-el:option>
                		<html-el:option value="1">已删除</html-el:option>
              		</html-el:select>
              	</td>
            </c:if>
            <c:if test="${!empty role_id_eq_10}">
            	<td><strong class="fb">部门：</strong></td>
              	<td>
              		<html-el:select property="dept_id" styleId="dept_id" style="width:200px;">
                		<html-el:option value="">请选择...</html-el:option>
                		<c:forEach var="cur" items="${peDeptList}">
                  			<html-el:option value="${cur.dept_id}">${fn:escapeXml(cur.map.tree_name)}</html-el:option>
                		</c:forEach>
              		</html-el:select>
              	</td>
            </c:if>
            <td></td>
         </tr>
         <tr style="height:25px;">
         	<td style="height:25px;padding-left: 20px" nowrap="nowrap"><strong class="fb">关联门店：</strong></td>
         	<td><html-el:text property="store_name_like" styleId="store_name_like" style="width:140px;" /></td>
          	<c:if test="${!empty role_id_eq_10}">
         	<td><strong class="fb">是否删除：</strong></td>
         	<td>
                 <html-el:select property="is_del" styleId="is_del" style="width:70px;">
                   <html-el:option value="0">未删除</html-el:option>
                   <html-el:option value="1">已删除</html-el:option>
                 </html-el:select>
         	</td>
            </c:if>
            <c:if test="${empty role_id_eq_10}">
         	<td></td>
         	<td></td>
            </c:if>
            <td>
            	<strong class="fb">促销员类型：</strong>
            </td>
            <td>
            	<html-el:select property="sales_type" styleId="sales_type" style="width:70px;">
                   <html-el:option value="">-请选择-</html-el:option>
                   <html-el:option value="1">兼职</html-el:option>
                   <html-el:option value="2">全职</html-el:option>
                </html-el:select>
            </td>
            <td>
            </td>
        </tr style="height:25px;">
        	<td style="height:25px;padding-left: 20px" nowrap="nowrap"><strong class="fb">R3人员编号：</strong></td>
        	<td>
	            <html-el:text property="r3_job_id" styleId="r3_job_id" style="width:100px;" maxlength="30"/>
          	</td>
        	<td></td><td></td>
        	<td></td><td></td>
        	<td>
              <input class="but1" type="submit" name="Submit" value="搜索" />
            </td>
        <tr>
        	
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
        <input class="but2" type="submit" name="Submit2" value="新增" onclick="location.href='PromotersPeProdUser.do?method=add&mod_id=${af.map.mod_id}&tree_param=${tree_param}';return false;" />
        <input type="button" value="导出" id="export_excel" class="but_excel" style="margin-left: 10px;" />
        </logic-el:match>
        </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <form id="listForm" name="listForm" method="post" action="PromotersPeProdUser.do">
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
          <tr class="tabtt1">
            <td width="5%" nowrap="nowrap"><font class="blue">序号</font></td>
            <td width="8%" nowrap="nowrap" ><font class="blue">用户名</font></td>
            <td width="8%" nowrap="nowrap" ><font class="blue">岗位ID</font></td>
            <td width="8%" nowrap="nowrap" ><font class="blue">姓名</font></td>
            <td nowrap="nowrap" align="center"><font class="blue">R3人员编号</font></td>
            <td width="20%" nowrap="nowrap"><font class="blue">部门</font></td>
            <td nowrap="nowrap" ><font class="blue">促销员类型</font></td>
            <td nowrap="nowrap" ><font class="blue">关联门店</font></td>
            <td width="10%" nowrap="nowrap" align="center"><font class="blue">手机/电话</font></td>
            <td width="14%" align="center"><font class="blue">操作</font></td>
          </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <c:if test="${cur.user_type eq 99}">
              <c:set var="is_admin" value="true" />
            </c:if>
            <tr>
              <td width="5%" height="28"  align="center">${vs.count}</td>
              <td align="left"><c:out value="${cur.user_name}" /></td>
              <td align="left"><c:out value="${cur.job_id}" /></td>
              <td align="left" valign="middle"><a style="text-decoration:underline;" href="PromotersPeProdUser.do?method=view&user_id=${cur.id}&role_id=${cur.map.role_id}&mod_id=${af.map.mod_id}&tree_param=${tree_param}">${cur.real_name}</a></td>
              <td align="left"><c:out value="${cur.r3_job_id}" /></td>
              <td align="left"><c:out value="${cur.map.full_dept_name}" /></td>
              <td align="left">
              	<c:if test="${cur.map.sales_type eq 1 }">兼职</c:if>
              	<c:if test="${cur.map.sales_type eq 2 }">全职</c:if>
              </td>
              <td align="left">
              	<c:if test="${empty cur.map.konkaMobileSpRelationList}"><span style="color:#ccc;">暂无关联门店</span></c:if>
              	<c:if test="${not empty cur.map.konkaMobileSpRelationList}">
              		<c:forEach items="${cur.map.konkaMobileSpRelationList}" var="cur2" varStatus="vs2">
              			<c:out value="${cur2.map.store_name}" /><c:if test="${not vs2.last}">，</c:if>
              		</c:forEach>
              	</c:if>
              </td>
              <td align="center"><c:out value="${not empty cur.link_phone ? cur.link_phone : (not empty cur.link_tel ? cur.link_tel : '')}" /></td>
              <td align="center" nowrap="nowrap"><c:if test="${cur.is_del eq 0}">
                   <logic-el:match name="popedom" value="+2+"><span style="cursor:pointer;" class="fblue" onclick="if(confirm('是否重置？')){doNeedMethod(null, 'PromotersPeProdUser.do', 'initPassword','user_id=${cur.id}&'+$('#bottomPageForm').serialize())}" title="重置密码">重置密码</span></logic-el:match>
                   <logic-el:notMatch name="popedom" value="+2+"><span style="color:#ccc" class="fblue" title="重置密码">重置密码</span></logic-el:notMatch>
                 |
                  <!-- <c:if test="${userInfo.id eq 1}"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'PeProdUser.do', 'userLogin','user_id=${cur.id}&'+$('#bottomPageForm').serialize())">登陆</span> | </c:if> -->
                  <c:if test="${1 eq 2 }"> <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'SetModPopedom.do', 'edit', 'mod_id=${af.map.mod_id}&user_id=${cur.id}&url=PromotersPeProdUser&'+$('#bottomPageForm').serialize())">授权</span> | </c:if>
                  <logic-el:match name="popedom" value="+2+">
                  <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'PromotersPeProdUser.do', 'edit','user_id=${cur.id}&'+$('#bottomPageForm').serialize())">修改</span>
                  </logic-el:match>
                  <logic-el:notMatch name="popedom" value="+2+">
                  <span style="color:#CCC;" class="fblue">修改</span>
                  </logic-el:notMatch>
                  <logic-el:match name="popedom" value="+4+">
                  <c:if test="${cur.user_type ne 0}">
                 |
                  <span style="cursor:pointer;" class="fblue" onclick="confirmDelete(null, 'PromotersPeProdUser.do','user_id=${cur.id}&'+$('#bottomPageForm').serialize())">删除</span>
                  </c:if>
                  </logic-el:match>
                  <c:if test="${cur.user_type eq 0}"> 
                 |
                  <span style="color:#ccc" class="fblue">删除</span> </c:if>
                </c:if>
                <c:if test="${cur.is_del eq 1}"> <span style="color:#ccc" class="fblue" >重置密码</span> | <span style="color:#ccc" class="fblue" >修改</span> | <span class="fblue" style="color:#ccc">删除</span> </c:if></td>
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
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="PromotersPeProdUser.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("tree_param", "${tree_param}");
	            pager.addHiddenInputs("name_like", "${fn:escapeXml(af.map.name_like)}");
	            pager.addHiddenInputs("position_id", "${af.map.position_id}");
	            pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
	            pager.addHiddenInputs("store_name_like", "${af.map.store_name_like}");
	            pager.addHiddenInputs("is_del", "${af.map.is_del}");
	            pager.addHiddenInputs("job_like", "${af.map.job_like}");
	            pager.addHiddenInputs("sales_type", "${af.map.sales_type}");
	            pager.addHiddenInputs("r3_job_id", "${af.map.r3_job_id}");
	            document.write(pager.toString());
	      </script></td>
        </tr>
      </table>
    </form>
    <div class="rtabcont1" style="overflow-x: auto; display: none;" id="divExcel_all" title="促销员信息">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
          <tr class="tabtt1">
            <td width="5%" nowrap="nowrap"><font class="blue">序号</font></td>
            <td width="8%" nowrap="nowrap" ><font class="blue">用户名</font></td>
            <td width="8%" nowrap="nowrap" ><font class="blue">岗位ID</font></td>
            <td width="8%" nowrap="nowrap" ><font class="blue">姓名</font></td>
            <td nowrap="nowrap" align="center"><font class="blue">R3人员编号</font></td>
            <td width="20%" nowrap="nowrap"><font class="blue">部门</font></td>
            <td nowrap="nowrap" ><font class="blue">促销员类型</font></td>
            <td nowrap="nowrap" ><font class="blue">关联门店</font></td>
            <td width="10%" nowrap="nowrap" align="center"><font class="blue">手机/电话</font></td>
          </tr>
          <c:forEach var="cur" items="${allList}" varStatus="vs">
            <tr>
              <td width="5%" height="28"  align="center">${vs.count}</td>
              <td align="left"><c:out value="${cur.user_name}" /></td>
              <td align="left"><c:out value="${cur.job_id}" /></td>
              <td align="left" valign="middle">${cur.real_name}</td>
			  <td align="left"><c:out value="${cur.r3_job_id}" /></td>   
              <td align="left" ><c:out value="${cur.map.full_dept_name}" /></td>
              <td align="left">
              	<c:if test="${cur.map.sales_type eq 1 }">兼职</c:if>
              	<c:if test="${cur.map.sales_type eq 2 }">全职</c:if>
              </td>
              <td align="left">
              	<c:if test="${empty cur.map.konkaMobileSpRelationList}"><span style="color:#ccc;">暂无关联门店</span></c:if>
              	<c:if test="${not empty cur.map.konkaMobileSpRelationList}">
              		<c:forEach items="${cur.map.konkaMobileSpRelationList}" var="cur2" varStatus="vs2">
              			<c:out value="${cur2.map.store_name}" /><c:if test="${not vs2.last}">，</c:if>
              		</c:forEach>
              	</c:if>
              </td>
              <td align="center"><c:out value="${not empty cur.link_phone ? cur.link_phone : (not empty cur.link_tel ? cur.link_tel : '')}" /></td>         
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
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	// 导出excel
    $("#export_excel").click(function(){
    	if(confirm("提示，您确认导出数据？")){
    		$("#bottomPageForm").append("<input type='hidden' name='excel_all' value='1' />");
    		$("#bottomPageForm").submit();
    	}
    });

    var excel_all = "${af.map.excel_all}";
	if("1" == excel_all){
		toExcel('divExcel_all', '${ctx}/manager/admin/PromotersPeProdUser.do?method=toExcel');
	}
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
