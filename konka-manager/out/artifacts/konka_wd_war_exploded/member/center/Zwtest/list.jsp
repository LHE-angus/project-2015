<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:if test="${ecUser.user_type eq 1 }">康佳EPP内部优惠购机平台</c:if><c:if
		test="${ecUser.user_type eq 2 }">康佳网上直销商城</c:if></title>
<link rel=stylesheet type=text/css
	href="${ctx}/styles/member/css/global1.css" />
<link rel=stylesheet type=text/css
	href="${ctx}/styles/member/css/epp.css" />
<link rel=stylesheet type=text/css
	href="${ctx}/styles/member/css/member.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
	<html-el:form action="/center/Zwtest">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="member_form_table0">
			<tr>
			  <td>用户名</td><td><html-el:text property="user_name_like"></html-el:text></td>
			</tr>
			<tr>
			  <td>地址</td><td><html-el:text property="link_addr"></html-el:text></td>
			</tr>
			<tr>
			  <td colspan="2"><input class="inputbtn" type="submit" name="Submit" value="查询" /></td>
			  <input type="button" value="Excel" id="export_excel" class="but_excel" />
			</tr>
		</table>
	</html-el:form>
	<div>
	真实姓名：<input class="inputbtn" type="text" id="real_name" name="real_name"/>
	<input class="inputbtn" type="button" id="ajaxEcUserList" name="ajaxEcUserList" value="ajax列表查询" />
	显示数据:<div id="ajaxdata"></div>
	</div>
	<html-el:form action="/center/Zwtest?method=login">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="member_form_table0">
			<tr>
			  <td>用户名</td><td><html-el:text property="user_name"></html-el:text></td>
			</tr>
			<tr>
			  <td>密码</td><td><html-el:text property="pass_word"></html-el:text></td>
			</tr>
			<tr>
			  <td colspan="2"><input class="inputbtn" type="submit" name="Submit" value="登陆" /></td>
			</tr>
		</table>
	</html-el:form>
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		class="member_form_table2">
		<tr class="tr1">
			<td align="center" nowrap="nowrap">编号</td>
			<td align="center" nowrap="nowrap">用户名</td>
            <td align="center" nowrap="nowrap">email</td>
            <td align="center" nowrap="nowrap">地址</td>
		</tr>
		<tbody>
			<c:forEach var="cur" items="${userlist}" varStatus="vs">
				<tr>
					<td align="center" nowrap="nowrap">${vs.count}</td>
					<td align="center" nowrap="nowrap">${cur.user_name}</td>
					<td align="center" nowrap="nowrap">${cur.email}</td>
					<td align="center" nowrap="nowrap">${cur.link_addr}</td>
				</tr>
			</c:forEach>
			<c:if test="${empty userlist }">
            <tr height="60">
              <td align="center" nowrap="nowrap" colspan="10"> 暂无用户 </td>
            </tr>
          </c:if>
		</tbody>
	</table>
	<input id="pageCount" value="${af.map.pager.pageCount}"/>
	 <c:if test="${af.map.pager.pageCount>1}">
	    <form id="bottomPageForm" name="bottomPageForm" method="post" action="?">
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td height="40"><div style="text-align: right; padding-right: 5px;">
                  <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
                  <script type="text/javascript">
	                     var pager = new Pager(document.bottomPageForm, parseInt('${af.map.pager.recordCount}'), parseInt('${af.map.pager.pageSize}'), parseInt('${af.map.pager.currentPage}'));
	                     pager.addHiddenInputs("method", "list");
	                     pager.addHiddenInputs("user_name_like", "${af.map.user_name_like}"); 	
	                     pager.addHiddenInputs("link_addr", "${af.map.link_addr}"); 	
	                     document.write(pager.toString());
			        </script>
                </div></td>
            </tr>
          </table>
        </form>
        </c:if>
</body>

<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	// 导出excel
    $("#export_excel").click(function(){
    	 this.value="正在提交...";
    	 this.disabled=true;
    	 this.form.action="${ctx}/member/center/Zwtest.do?method=excel";
    	 this.form.submit();
    	 this.form.action="${ctx}/member/center/Zwtest.do";
    	 this.value="导出Excel";
    	 this.disabled=false;
    });
	
    //Ajax列表查询
	$("#ajaxEcUserList").click(function(){
		var real_name=$("#real_name").val();
		$.ajax({
			type: "POST",
			/* url: "<c:url value='/member/center/Zwtest.do' />", */
			url: "${ctx}/member/center/Zwtest.do",
			data: { "method":"ajaxEcUserList", "real_name":real_name},
			dataType: "json",
			sync: true, // jsonp不支持同步
			error: function (xhr, ajaxOptions, thrownError) { alert("数据加载请求失败【" + xhr.statusText + "," + xhr.responseText + "," + xhr.status + "," + thrownError +"】！"); },
			success: function(data) {
				console.log(typeof(data));
				var entity='';
				for(var i=0;i<data.length;i++){
				   entity+='<div color="#00000"></div>'+data[i].add_date+'</br>';
				}
				$("#ajaxdata").append(entity);
				console.log(data);
			}
		});
	});
});
</script>

</html>
