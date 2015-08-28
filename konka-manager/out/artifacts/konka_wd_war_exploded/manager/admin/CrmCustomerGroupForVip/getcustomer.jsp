<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<base target="_self"></base>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<meta http-equiv="Expires" CONTENT="0">
<meta http-equiv="Cache-Control" CONTENT="no-cache">
<meta http-equiv="Pragma" CONTENT="no-cache">
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />

<style>
.tip {
	background-color: yellow;
	height: 20px;
	width: 100%;
	text-align: center;
	border: 1px;
	color: red;
	border-style: solid;
	font-weight: bold;
	font-size: 12px;
}
</style>



</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="rtabcont1">
    <form action="CrmCustomerGroupForVip.do" id="form1" method="post" >
      <input type="hidden" name="method" value="getCustomerList" id="method">
      <input type="hidden" name="mod_id" value="${af.map.mod_id}">
      <input type="hidden" name="deptid" value="${af.map.deptid}">
      <!-- 保存的时候提交  -->

      <input type="hidden" name="id" value="${af.map.id }">
      <input type="hidden" name="ids">
      <input type="hidden" name="r3codes">
      <input type="hidden" name="customernames">
      <input type="hidden" name="branch_area_name_2s">
      <input type="hidden" name="branch_area_names">
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr style="height:25px;"> 
        	<td>
            <strong class="fb">客户类型：</strong>
            <td >
        		<select name="v_customer_type2" id="v_customer_type2">
        			<option value="">-请选择-</option>
        			<c:forEach items="${konkaCategoryList}" var="cur">
        				<option value="${cur.c_index }">[${cur.c_comm}] ${cur.c_name}</option>
        			</c:forEach>
        		</select>
		   </td>
            </td>
        	<td>
            <strong class="fb">客户编码：</strong>
            <input type="text" name="customercode" style="width:130px;"/>
            </td>
            <td> 
            <strong class="fb">客户名称：</strong>
            <input type="text" name="customername" style="width:130px;"/>
           </td>
           <td>
              <input class="but1" type="submit" name="Submit" value="搜索" />
           </td>
        </tr>
      </table>
    </form>
  </div>
  
  <div class="rtabcont1">
  
  <div class="tip" >
  	<label >已经在当前客户群组存在的客户不会被查询到!</label>
  </div>
  
  </div>
      <div class="rtabcont1">
	    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
	        <tr class="tabtt1">
	          <td align="center" nowrap="nowrap" width="10%" ><input type="checkbox" id="chkAll"/></td>
	          <td align="center" nowrap="nowrap" width="10%" style="display:none">客户ID</td>
	          <td align="center" nowrap="nowrap" width="10%">客户编码</td>
	          <td align="center" nowrap="nowrap" width="10%">客户名称</td>
	          <td align="center" nowrap="nowrap" width="10%">客户所在部门编码</td>
	          <td align="center" nowrap="nowrap" width="10%">客户所在部门名称</td>
	        </tr>
			<c:forEach var="cur" items="${ctmList}" varStatus="vs">
			        <tr>
			            <td align="center" nowrap="nowrap"><input name="checkbox" type="checkbox" value="${cur.id}:${cur.r3_code}:${cur.customer_name }:${cur.branch_area_name_2 }:${cur.branch_area_name }"/></td>
			            <td align="center" nowrap="nowrap" style="display:none">${cur.id }</td>
			            <td align="center" nowrap="nowrap">${cur.r3_code }</td>
			            <td align="center" nowrap="nowrap">${cur.customer_name }</td>
			            <td align="center" nowrap="nowrap">${cur.branch_area_name_2 }</td>
			            <td align="center" nowrap="nowrap">${cur.branch_area_name }</td>
			        </tr>
			</c:forEach>
	      </table>
	     <form id="bottomPageForm" name="bottomPageForm" method="post" action="CrmCustomerGroupForVip.do">
		     <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		       <tr>
		         <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
		           <script type="text/javascript">
					  var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
						  pager.addHiddenInputs("method", "getCustomerList");
						  pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
						  pager.addHiddenInputs("id", "${af.map.id}");
						  pager.addHiddenInputs("deptid", "${af.map.deptid}");
						  pager.addHiddenInputs("customercode", "${af.map.customercode}");
						  pager.addHiddenInputs("customername", "${af.map.customername}");
						  document.write(pager.toString());
					</script>
				</td>
		       </tr>
		     </table>
	     </form>
	</div>
  <div class="clear"></div>
  	<div align="center">
  			<input class="but6" id="btn_confirm"/>
            <input class="but5" id="btn_colse" value="关闭" onclick="closeWin();"/></td>
  	</div>

</div>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">
//<![CDATA[
$(document).ready(function(){
	//全选||非全选
	$("#chkAll").click(function() {
		if ($("#chkAll").attr("checked") == "checked") {
			$("input[name='checkbox']").attr("checked", 'checked');//全选
		} else {
			$("input[name='checkbox']").removeAttr("checked");//取消全选
		}
	});
	var ids = [];
	var r3codes = [] ;
	var customernames = [] ;
	var branch_area_name_2s = [] ;
	var branch_area_names = [] ;

	//提交的时候处理数据(目前选中的数据只有在一页,翻页后选中丢失)
	$("#btn_confirm").click(function(){
		var sum = 0 ;
		$("input[name='checkbox']:checkbox:checked").each(function(){
			sum ++;
	 	});

		if(sum == 0){
			alert("好歹选一个客户吧!");
			return false;
		}

		ids= [];
		r3codes = [] ;
		customernames = [] ;
		branch_area_name_2s = [] ;
		branch_area_names = [] ;
		$("input[name='checkbox']:checkbox:checked").each(function(){
				//分割 :
				temp = $(this).val().split(":");
				ids.push(temp[0]);
				r3codes.push(temp[1]);
				customernames.push(temp[2]);
				branch_area_name_2s.push(temp[3]);
				branch_area_names.push(temp[4]);
		 });

		$("[name='ids']").val(ids);
		$("[name='r3codes']").val(r3codes);
		$("[name='customernames']").val(customernames);
		$("[name='branch_area_name_2s']").val(branch_area_name_2s);
		$("[name='branch_area_names']").val(branch_area_names);

		$("#method").val("saveCrmCustomerDetails");
		$("#form1").submit();

	});




	//$("#btn_colse").click(function(){
// 		ids= null;
// 		r3codes = null ;
// 		customernames = null ;
// 		branch_area_name_2s =null ;
// 		branch_area_names = null ;
	//	window.opener = null;
	//	window.close();
	//});

});
//]]>
function closeWin()
{
	window.close();
};


</script>
</body>
</html>
