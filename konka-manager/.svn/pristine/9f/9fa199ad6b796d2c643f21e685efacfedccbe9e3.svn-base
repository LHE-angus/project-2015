<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/multiselect/jquery.multiselect2side.css" rel="stylesheet" type="text/css" />
<style type="text/css">
#sel { margin-top: 10px }
</style>
</head>
<body>
	<div class="oarcont">
		<div class="oartop">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="3%"><img
						src="${ctx}/styles/admin-index/images/k_tup.jpg" alt=""
						style="vertical-align: middle;" /></td>
					<td>当前位置：${naviString}</td>
				</tr>
			</table>
		</div>
		<div class="rtabcont2">
			<html-el:form action="/admin/KonkaCategoryType" method="post">
				<html-el:hidden property="method" value="save" />
				<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
				<table class="rtable3" width="100%" border="0" cellspacing="1"
					cellpadding="0">
					<tr>
						<td nowrap="nowrap"><strong>R3合并编码</strong> <html-el:text property="customer_code" styleId="customer_code" /><span id="span_1"></span></td>
					</tr>

					<tr>
						<td nowrap="nowrap"><strong> 客户名称  </strong> <html-el:text property="customer_name" styleId="customer_name" />
						<input type="button" id="btn_filter" value="查询" onclick="filterByName();" />
						<span id="query_tips" ><font color="#696969">(用于过滤客户名称)</font></span>
						</td><!-- 不要直接用button标签 -->
					</tr>

					<tr>
						<td colspan="3">
							<div id="sel">
								<select name="ids_" id="ids" multiple="multiple" size="8">
									<c:forEach var="cur" items="${konkaR3ShopList}">
										<option value="${cur.id}" title="客户名称">${cur.customer_name}</option>
									</c:forEach>
								</select>
								<html-el:hidden property="id_values" styleId="id_values" />
							</div>
						</td>
					</tr>
					<tr>
						<td width="20%"></td>
						<td>
						<label> 
						<input class="but4" id="btn_commit" type="button" value="提交" /> 
						<input class="but5" type="button" name="cancel" value="返回" onclick="history.back();" />
						</label>
						</td>
					</tr>
				</table>
			</html-el:form>
		</div>
		<div class="clear"></div>
	</div>
	<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/commons/scripts/jquery.pager.js"></script>
	<script type="text/javascript" src="${ctx}/styles/customer/multiselect/jquery.multiselect2side.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#ids").multiselect2side({
				selectedPosition : 'right',
				moveOptions : false,
				labelsx : '待选客户',
				labeldx : '已选客户'
			});

			$("#ids_ms2side__sx").val("");
			var arrs = new Array();
			<c:forEach var="cur" items="${konkaR3ShopList}">
			$("#ids_ms2side__sx option[value='${cur.id}']").attr("selected", true).dblclick();
			arrs.push("${cur.id}");
			</c:forEach>
			$("#id_values").val(arrs);
			
			
			//检查合并编码是否重复
			$("#customer_code").blur(function(){
			var $this = $(this);
			var v_customer_code = $this.val();
			if(v_customer_code=="" || v_customer_code==null) return ;
			
				$.ajax({
					type: "POST",
					url: "R3UserCodeMerge.do?method=isExistCustomerCode",
					data: {"v_customer_code": v_customer_code},
					dataType: "json",
					cache:false,
					error: function(){alert("数据加载请求失败！");},
					success: function(result){
						if(result == true){
							$("#span_1").text("编码已经存在,请更换一个!").css("color","red");
							$("#btn_commit").attr("disabled","disabled");
						}else{
							$("#span_1").text("可以使用!").css("color","green");
							$("#btn_commit").removeAttr("disabled");
						}
					}
				   });
			 }); //end
			 
			//提交保存
			$("#btn_commit").click(function() {
				
				var num = $("#ids_ms2side__dx").find("option").length;
				if (0 == num || null == num) {
					alert("请从待选区选择客户！");
					$("#ids_ms2side__dx").focus();
					return ;
				}
				
				var arrs = [];
				var obj = document.forms[0].ids_ms2side__dx;
				var length  = obj.length ;
				for(var i = 0; i < length; i++){
					arrs.push(obj.options[i].value);
				}
				
				// 1,2,3, 有逗号
				$("#id_values").val(arrs) ;
				var meger_code = $("#customer_code").val();
				if("" == meger_code || null == meger_code){
					alert("合并编码不能为空!");
					("#customer_code").focus();
					return  ;
				}	
				this.form.submit();
			});
			 
			 

	}); // jquery end
	
		
		 //按客户名称过滤数据
	function filterByName(){
		var v_customer_name = $("#customer_name").val();
		
		if(v_customer_name=="" || v_customer_name==null){
			alert("请输入需要过滤的客户名称关键字!");
			return;
		}
		
		$.ajax({
			type: "POST",
			url: "R3UserCodeMerge.do?method=getCustomerListByName",
			data: {"v_customer_name": v_customer_name},
			dataType: "json",
			cache:false,
			sync: true, 
			error: function (xhr, ajaxOptions, thrownError) { alert("数据加载请求失败【" + xhr.statusText + "," + xhr.responseText + "," + xhr.status + "," + thrownError +"】！"); },
			success: function(result){
				if((i =result.length) <= 0 ){
					//$("#ids_ms2side__sx").options.removeAll();
					//ids_ms2side__sx
					//alert("000");
// 					var obj = document.forms[0].ids_ms2side__sx;
// 					for(var i = 0; i < obj.length; i++){
// 						obj.options.remove(i);  
// 						}
				}
				//无论是否有数据查询,都需要把原来的值先清除再填充
				$("#ids_ms2side__sx").find("option").remove();
				for(var i=0; i<result.length;i++ ){
					var opt = new Option( result[i].customer_name,result[i].id); 
					$("#ids_ms2side__sx").get(0).options.add(opt);
				}
			}
		   });
	}
		
		
	</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
