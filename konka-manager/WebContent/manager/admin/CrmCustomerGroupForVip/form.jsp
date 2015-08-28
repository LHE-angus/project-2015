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
<link href="${ctx}/commons/styles/message/message.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/multiselect/jquery.multiselect2side.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont2">
    <html-el:form action="admin/CrmCustomerGroupForVip" method="post">
      <html-el:hidden property="method" value="save" styleId="method"  />
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" styleId="mod_id" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
      	<tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right" width="15%"><span style="color: red;">*</span>群组编码：</td>
          <td ><html-el:text property="groupcode" styleId="groupcode" readonly="true"/></td>
        </tr>
        <tr>
           <td nowrap="nowrap" height="28" class="title_item" align="right" width="15%"><span style="color: red;">*</span>群组名称：</td>
           <td ><html-el:text property="groupname" styleId="groupname"/><span style="color: gray">建议命名:部门+客户分类名称;(北京家乐福)</span></td>
        </tr>
         <tr>
           <td nowrap="nowrap" height="28" class="title_item" align="right" width="15%"><span style="color: red;">*</span>所属部门：</td>
           <td width="85%">
	           <html-el:select property="deptid" styleId="deptid" onchange="afterSelectDeptId(this.value)">
	           		<html-el:option value="">请选择</html-el:option>
	          		<c:forEach items="${deptList}" var="cur">
	          			<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
	          		</c:forEach>
	           </html-el:select>
           </td>
         </tr>
<!--        <tr> -->
<!--           <td nowrap="nowrap" height="28" class="title_item" align="right"><font color="red">* </font>开始时间：</td> -->
<!--           <td> -->
<%--           <fmt:formatDate value="${af.map.begindate}" pattern="yyyy-MM-dd" var="_begindate" /> --%>
<%-- 		       <input name="begindate" id="begindate" size="12" value="${_begindate}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,maxDate:'#F{$dp.$D(\'enddate\')}'})" /> --%>
<!--           </td> -->
<!--           </tr> -->
<!--           <tr> -->
<!--           <td nowrap="nowrap" height="28" class="title_item" align="right">结束时间：</td> -->
<!--           <td> -->
<%--            <fmt:formatDate value="${af.map.enddate}" pattern="yyyy-MM-dd" var="_enddate" /> --%>
<%-- 		          <input name="enddate" id="enddate" size="12" value="${_enddate}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'begindate\')||\'%y-%M-{%d}\'}'})" /></td> --%>
<!--         </tr> -->
       	<tr>
       	<td nowrap="nowrap" height="28" class="title_item" align="right">备注:</td>
       	<TD >
       		<html-el:textarea property="remark" style="width:500px"></html-el:textarea>
       	</TD>
       	</tr>

       	 <div class="clear">
	       	<tr>
	       		<td nowrap="nowrap" class="title_item" colspan="2">
		       		&nbsp;&nbsp;<input class="but2" type="button" title="添加客户" value="添加" onclick="getCtm();" ></input>
		       		<input class="but4" type="button" value="保存" id="btn_save" />
		       		<input class="but5" type="button" value="返回" onclick="location.href='CrmCustomerGroupForVip.do?method=list&deptid=${af.map.deptid }&mod_id=${af.map.mod_id}';" />
	       		</td>
        	</tr>
       	</div>
       	<tr>
       		<td colspan="2">
	       		<div class="rtabcont1">
				    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2" id="ctmtab">
				        <tr class="tabtt1">

				          <td align="center" nowrap="nowrap" width="5%" >序号</td>
				          <td align="center" nowrap="nowrap" width="10%" style="display:none">客户ID</td>
				          <td align="center" nowrap="nowrap" width="10%">客户编码</td>
				          <td align="center" nowrap="nowrap" width="10%">客户名称</td>
				          <td align="center" nowrap="nowrap" width="10%">客户所在部门编码</td>
				          <td align="center" nowrap="nowrap" width="10%">客户所在部门名称</td>
				          <td align="center" nowrap="nowrap" width="10%">操作</td>
				        </tr>
						<c:forEach var="cur" items="${ctmList}" varStatus="vs">
						        <tr>
						            <td align="center" nowrap="nowrap" >${vs.count }</td>
						            <td align="center" nowrap="nowrap" style="display:none">${cur.id }</td>
						            <td align="center" nowrap="nowrap">${cur.map.customercode }</td>
						            <td align="center" nowrap="nowrap">${cur.customername }</td>
						            <td align="center" nowrap="nowrap">${cur.map.deptcode }</td>
						            <td align="center" nowrap="nowrap">${cur.map.deptname }</td>
						            <td align="center"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('确定删除吗？', 'CrmCustomerGroupForVip.do', 'deleteL','line_id=${cur.id}&mod_id=${af.map.mod_id}&id=${af.map.id}&')">删除</span></td>
						        </tr>
						</c:forEach>
				      </table>
				</div>
       		</td>
       	</tr>
      </table>
    </html-el:form>
    <div>
		<form id="bottomPageForm" name="bottomPageForm" method="post" action="CrmCustomerGroupForVip.do">
	     	<html-el:hidden property="method" value="edit" />
		     <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		       <tr>
		         <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
		           <script type="text/javascript">
					  var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
						  pager.addHiddenInputs("id", "${af.map.id}");
						  pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
						  pager.addHiddenInputs("deptid", "${af.map.deptid}");
						  document.write(pager.toString());
					</script>
				</td>
		       </tr>
		     </table> 
      </form>
    </div>
  </div>
  <div class="clear"></div>
</div>
<!-- ****** Main Frame End ****** -->
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/multiselect/jquery.multiselect2sideYwy.js"></script>
<script type="text/javascript">//<![CDATA[
    $(document).ready(function(){
    	/**较验规则**/
    	$("#groupcode").attr("dataType", "Require").attr("msg", "群组编码不能为空！");
    	$("#groupname").attr("dataType", "Require").attr("msg", "群组名称不能为空！");
    	$("#deptid").attr("dataType", "Require").attr("msg", "所属部门不能为空！");
    	$("#remark").attr("dataType", "Limit").attr("max", "50").attr("msg", "备注不能超过50个文字");
    	
//     	$("#begindate").attr("dataType", "Require").attr("msg", "开始时间不能为空！");
        $("#btn_save").click(function(){
        	if(Validator.Validate(this.form, 3)){
    			 $("#btn_submit").attr("disabled", "true");
    			 this.form.submit();
    		}
        });

	});
   	function afterSelectDeptId(value){
    	$("#deptid").value(value);
		return value ;
    }
    // 弹出客户挑选页面,执行操作在弹出页面执行
    function getCtm(){
    	var id = $("#id").val();
    	if(!id){
    		alert("先执行保存再添加客户! ");
    		return ;
    	}
    	var returnValue =  window.showModalDialog("${ctx}/manager/admin/CrmCustomerGroupForVip.do?method=showGetCustomerPage&deptid="+"${af.map.deptid}"+"&id="+id  ,window,"dialogWidth:850px;status:no;dialogHeight:550px");
	    window.location.href=window.location.href;
   }

//]]></script>
</body>
</html>
