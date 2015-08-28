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
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet"	type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet"	type="text/css" />
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css"	rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet"	type="text/css" />
<link href="${ctx}/styles/customer/css/tab.css" rel="stylesheet"	type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css"	rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/fancybox/fancybox.css"	rel="stylesheet" type="text/css" />
<style type="text/css">
.title_item {
	text-align: right;
}
.imgfloat{
float:left;
margin-left:30px;
}
</style>
</head>
<body>
	<div class="oarcont">
		<div class="oartop">
			<table width="500" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="3%"><img
						src="${ctx}/styles/admin-index/images/k_tup.jpg"
						style="vertical-align: middle;" /></td>
					<td>当前位置：${naviString}</td>
				</tr>
			</table>
		</div>
		<div class="rtabcont2">
			<html-el:form action="/manager/KonkaR3Store"
				enctype="multipart/form-data">
				<html-el:hidden property="store_id" value="${af.map.store_id}" />
				<html-el:hidden property="method" value="save" />
				<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
				<html-el:hidden property="province" styleId="province"	value="${af.map.province}" />
				<html-el:hidden property="city" styleId="city"	value="${af.map.city}" />
				<html-el:hidden property="country" styleId="country" value="${af.map.country}" />
				<html-el:hidden property="town" styleId="town" value="${af.map.town}" />
				<html-el:hidden property="queryString" styleId="queryString" />
				<html-el:hidden property="ck_id" styleId="ck_id" />
				<div style="float:right;">
					<label> 
						<input class="but4" type="button" name="Submit4" value="提交" id="send" />
					 	<input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
					</label>
				</div>
				<ul id="tabs">
					<li><a href="#" name="tab1">基本信息</a></li>
					<li><a href="#" name="tab2">终端物料</a></li>
					<li><a href="#" name="tab3">门店照片</a></li>
				</ul>
				<div id="content">
					<div id="tab1">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							class="rtable3">
							<tr>
								<th height="28" colspan="2">您正在${empty af.map.store_id ? '创建' : '编辑'}“${af.map.store_name}”门店信息</th>
							</tr>
							<tr class="oartop">
								<td colspan="4">门店信息</td>
							</tr>
							<tr>
								<td height="28" colspan="4">门店资料</td>
							</tr>
							<c:if test="${not empty af.map.add_date}">
								<tr>
									<td height="28" width="15%" nowrap="nowrap" class="title_item">创建时间：</td>
									<td><fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td height="28" width="15%" nowrap="nowrap" class="title_item">维护时间：</td>
									<td><fmt:formatDate value="${af.map.modify_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								</tr>
							</c:if>
							<c:if test="${not empty af.map.create_user_id or not empty af.map.modify_user_id}">
								<tr>
									<td height="28" width="15%" nowrap="nowrap" class="title_item">创建人：</td>
									<td>${create_man}</td>
									<td height="28" width="15%" nowrap="nowrap" class="title_item">维护人：</td>
									<td>${modify_man}</td>
								</tr>
							</c:if>
							<c:if test="${not empty af.map.store_id}">
								<tr>
									<td height="28" width="15%" nowrap="nowrap" class="title_item">门店编码：</td>
									<td colspan="3">${af.map.store_id}</td>
								</tr>
							</c:if>
							<tr>
								<td height="28" width="15%" nowrap="nowrap" class="title_item"><font
									color="red">* </font>门店名称：</td>
								<td nowrap="nowrap">${af.map.store_name }</td>
								<td height="28" width="15%" nowrap="nowrap" class="title_item"><font
									color="red">* </font>门店R3编码：</td>
								<td>${af.map.r3_sdf_sn }</td>
							</tr>
							<tr>
								<td height="28" width="15%" nowrap="nowrap" class="title_item"><font
									color="red">* </font>所在地区：</td>
								<td colspan="3"><c:if test="${not empty af.map.province}">
										<div style="color: #F00;">建议您按“${af.map.province}/${af.map.city}/${af.map.country}/${af.map.town}”选择</div>
									</c:if> <select name="_province" id="_province" class="bd">
										<option value="">-请选择省/直辖市/自治区-</option>
								</select> <select name="_city" id="_city" class="bd">
										<option value="">-请选择市-</option>
								</select> <select name="_country" id="_country" class="bd">
										<option value="">-请选择县-</option>
								</select> <select name="_town" id="_town" class="bd">
										<option value="">-请选择乡/镇-</option>
								</select></td>
							</tr>
							<tr>
								<td height="28" width="15%" nowrap="nowrap" class="title_item">街道地址：</td>
								<td colspan="3"><html-el:text property="addr"
										styleId="addr" size="60" maxlength="57" /></td>
							</tr>
							<tr>
								<td height="28" nowrap="nowrap" class="title_item">联系人：</td>
								<td><html-el:text property="link_man" styleId="link_man"
										style="width:150px;" size="30" maxlength="30" /></td>
								<td height="28" nowrap="nowrap" class="title_item">联系电话：</td>
								<td><html-el:text property="linka_tel" styleId="linka_tel"
										style="width:150px;" size="30" maxlength="30" /></td>
							</tr>
							<tr>
								<td height="28" colspan="4">分公司资料</td>
							</tr>
							<tr>
					          <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">所属分公司/经办：</td>
					          <td width="35%">
					          	${af.map.dept_name}/${af.map.jb_name}
					              </td>
					          <td width="15%" nowrap="nowrap" class="title_item" align="right">经办部门电话：</td>
					          <td width="35%">
					          	${af.map.jb_tel}
					          </td>
					        </tr>
					        <tr>
					          <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">经办经理：</td>
					          <td>
					           	${af.map.jbjl_name}
					          </td>
					          <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">业务主管：</td>
					          <td>
					         	${af.map.ywzg_name}
					          </td>
					        </tr>
					        <tr>
					          <td height="28" nowrap="nowrap" class="title_item" align="right">业务员：</td>
					          <td>
					          	${af.map.ywy_name}
					          </td>
					          <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">是否有促销员：</td>
					          <td>
					          	<c:if test="${af.map.havaman eq 0}">
					          		无
					          	</c:if>
					          	<c:if test="${af.map.havaman eq 1}">
					          		有
					          	</c:if>
					          </td>
					        </tr>
					        <tr>
					        	<td height="28" nowrap="nowrap" class="title_item" align="right">已有促销员：</td>
					        	<td colspan="3">${exCXY}</td>
					        </tr>
							<tr>
								<td height="28" colspan="4">客户资料</td>
							</tr>
							<tr>
					          <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">销售组织：</td>
					          <td colspan="3">${af.map.xszz}</td>
					        </tr>
					        <tr>
					          <td height="28" nowrap="nowrap" class="title_item" align="right">客户R3编码：</td>
					          <td>${af.map.r3_code}</td>
					          <td nowrap="nowrap" class="title_item" align="right">客户名称：</td>
					          <td>${af.map.kh_name}</td>
					        </tr>
					        <tr>
					          <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">R3售达方编码：</td>
					          <td>${af.map.r3_shsdf_sn}</td>
					          <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">出货仓送达方编码：</td>
					          <td >${af.map.mf_sn}</td>
							</tr>
							<tr>
									<td height="28" width="15%" nowrap="nowrap" align="right"
										class="title_item">出货仓名称：</td>
									<td colspan="3">${af.map.ck_name}</td>
							</tr>
							<tr>
									<td height="28" colspan="4" align="left">附件资料信息</td>
							</tr>
							<tr>
					        	<td height="28" width="15%" nowrap="nowrap" class="title_item" align="right" >已上传的附件：</td>
					          	<td colspan="3">
					          		<ol>
					              		<c:forEach var="cur" items="${attachmentList}" varStatus="vs">
					                		<li><a href="${ctx}/manager/admin/Download.do?method=downloadFile1&save_name=${cur.save_name}" target="_blank">${cur.file_name}</a>&nbsp;&nbsp;&nbsp;</li>
					              		</c:forEach>
					            	</ol>
					            </td>
					      	</tr>
							<tr>
								<td height="28" colspan="4">详细信息</td>
							</tr>
							<tr>
								<td height="28" width="15%" nowrap="nowrap" class="title_item">门店级别：</td>
								<td><html-el:select property="store_level"
										styleId="store_level">
										<html-el:option value="">-请选择-</html-el:option>
										<html-el:option value="0">A类</html-el:option>
										<html-el:option value="1">B类</html-el:option>
										<html-el:option value="2">C类</html-el:option>
									</html-el:select></td>
								<td height="28" width="15%" nowrap="nowrap" class="title_item">年销额（万元）：</td>
								<td><html-el:text property="year_sale_count"
										styleId="year_sale_count"></html-el:text></td>
							</tr>

							<tr>
								<td height="28" width="15%" nowrap="nowrap" class="title_item">总营业面积(平米)：</td>
								<td><html-el:text property="business_area"
										styleId="business_area"></html-el:text></td>
								<td height="28" width="15%" nowrap="nowrap" class="title_item">彩电营业面积(平米)：</td>
								<td><html-el:text property="tv_business_area"
										styleId="tv_business_area"></html-el:text></td>
							</tr>

							<tr>
								<td height="28" width="15%" nowrap="nowrap" class="title_item">我品位置：</td>
								<td>&nbsp;&nbsp; 第&nbsp;<html-el:select property="konka_site"
										styleId="konka_site">
										<html-el:option value="">请选择</html-el:option>
										<html-el:option value="1">1</html-el:option>
										<html-el:option value="2">2</html-el:option>
										<html-el:option value="3">3</html-el:option>
										<html-el:option value="4">4</html-el:option>
										<html-el:option value="5">5</html-el:option>
										<html-el:option value="6">6</html-el:option>
										<html-el:option value="7">7</html-el:option>
										<html-el:option value="8">8</html-el:option>
										<html-el:option value="9">9</html-el:option>
										<html-el:option value="10">10</html-el:option>
									</html-el:select>&nbsp;位&nbsp;&nbsp;</td>
									<td height="28" width="15%" nowrap="nowrap" class="title_item">是否重点：</td>
								<td>
								<html-el:radio property="is_important" value="0">是</html-el:radio>
								<html-el:radio property="is_important" value="1">否</html-el:radio>
								</td>
								
							</tr>
							
							<tr>
								<td height="28" width="15%" nowrap="nowrap" class="title_item">竞品品牌：</td>
								<td colspan="3">
							&nbsp;&nbsp;&nbsp;&nbsp;<html-el:checkbox property="competition" styleId="competition_0" value="0">TCL</html-el:checkbox>
							&nbsp;&nbsp;&nbsp;&nbsp;<html-el:checkbox property="competition" styleId="competition_1" value="1">长虹</html-el:checkbox>
						    &nbsp;&nbsp;&nbsp;&nbsp;<html-el:checkbox property="competition" styleId="competition_2" value="2">海信</html-el:checkbox>
							&nbsp;&nbsp;&nbsp;&nbsp;<html-el:checkbox property="competition" styleId="competition_3" value="3">创维</html-el:checkbox>
						    &nbsp;&nbsp;&nbsp;&nbsp;<html-el:checkbox property="competition" styleId="competition_4" value="4">乐视</html-el:checkbox>
						    &nbsp;&nbsp;&nbsp;&nbsp;<html-el:checkbox property="competition" styleId="competition_5" value="5">小米</html-el:checkbox>
								</td>
							</tr>
							<tr>
								<td height="28" width="15%" nowrap="nowrap" class="title_item">门店简介：</td>
								<td colspan="3">
							<html-el:textarea property="memo" styleId="memo" style="width:70%;" rows="2"></html-el:textarea>
							<div id="memo_note"  style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/styles/images/tishi.gif" style="vertical-align:middle;" /></div>
								</td>
							</tr>
						</table>
					</div>
					<div id="tab2">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							class="rtable3">
							<tr>
								<td colspan="4" style="font-weight: 900;" align="left"><strong
									class="fb">终端物料:</strong></td>
							</tr>
							<tr>
								<td colspan="4" width="100%">
									<table width="100%" border="0" cellspacing="0" cellpadding="0"
										class="rtable2" id="categorys_td">
										<tr class="tabtt1" style="height: 28px;">
											<td width="15%" align="center" nowrap="nowrap">门店</td>
											<td width="15%" align="center" nowrap="nowrap">品类名称</td>
											<td width="10%" align="center" nowrap="nowrap">发放年月</td>
											<td width="10%" align="center" nowrap="nowrap">数量</td>
											<td width="10%" align="center" nowrap="nowrap">金额单位（元）</td>
											<td width="10%" align="center" nowrap="nowrap">尺寸/规格</td>
											<td width="10%" nowrap="nowrap" align="center">添加人</td>
						                    <td width="10%" nowrap="nowrap" align="center">添加时间</td>
											<td width="10%" align="center" nowrap="nowrap">备注</td>
										</tr>
										<tbody id="tbodyContent" class="rtable2">
											<c:forEach items="${ksList}" var="cur">
												<tr>
													<td nowrap="nowrap" align="center"><span
														style="cursor: pointer; color: blue;" class="fblue"
														onclick="location.href='KonkaR3Store.do?method=view2&mod_id=${af.map.mod_id}&id=${cur.id}'">${cur.store_name}</span></td>
													<td nowrap="nowrap" align="center">${cur.category_name}</td>
													<td nowrap="nowrap" align="center">${cur.year}年${cur.month}月</td>
													<td nowrap="nowrap" align="center">${cur.num}</td>
													<td nowrap="nowrap" align="center">${cur.task_money}</td>
													<td nowrap="nowrap" align="center">${cur.size}</td>
													<td align="right" nowrap="nowrap">${cur.map.user_name}</td>
							                     <td align="center" nowrap="nowrap">
							                        <fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd"></fmt:formatDate>
							                     </td>
													<td nowrap="nowrap" align="center">${cur.remark}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</td>
							</tr>
						</table>
					</div>


					<div id="tab3">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							class="rtable3">
							<tr>
								<td nowrap="nowrap" class="title_item" align="right">上传附件：</td>
								<td colspan="3"><div id="divFileHidden"
										style="display: none;">
										&nbsp;&nbsp;附件标题:
										
										<html-el:select property="file_desc" >
										<html-el:option value="康佳全景">康佳全景</html-el:option>
										<html-el:option value="康佳主形象">康佳主形象</html-el:option>
										<html-el:option value="康佳左面">康佳左面</html-el:option>
										<html-el:option value="康佳右面">康佳右面</html-el:option>
										<html-el:option value="三星全景">三星全景</html-el:option>
										<html-el:option value="海信全景">海信全景</html-el:option>
										<html-el:option value="创维全景">创维全景</html-el:option>
										<html-el:option value="其他">其他</html-el:option>
										</html-el:select>&nbsp;&nbsp;&nbsp;&nbsp;
										<input name="file_hidden" type="file" id="file_hidden" class="file_show"
											style="width: 250px;" /> <img src="../../images/x.gif"
											style="vertical-align: middle; cursor: pointer;"
											id="imgDelTr" title="删除" />
									</div>
									<div id="divFile">
									&nbsp;&nbsp;附件标题:
										<html-el:select property="file_desc" >
										<html-el:option value="康佳全景">康佳全景</html-el:option>
										<html-el:option value="康佳主形象">康佳主形象</html-el:option>
										<html-el:option value="康佳左面">康佳左面</html-el:option>
										<html-el:option value="康佳右面">康佳右面</html-el:option>
										<html-el:option value="三星全景">三星全景</html-el:option>
										<html-el:option value="海信全景">海信全景</html-el:option>
										<html-el:option value="创维全景">创维全景</html-el:option>
										<html-el:option value="其他">其他</html-el:option>
										</html-el:select>&nbsp;&nbsp;&nbsp;&nbsp;
										<input name="file_show_1" type="file" id="file_show" class="file_show"
											style="width: 250px;" /> <img src="../../images/+.gif"
											style="vertical-align: middle; cursor: pointer;"
											id="imgAddTr" title="再添加一个" />
									</div></td>
							</tr>
						
								<tr>
									<td height="28" colspan="4"  align="left">已上传的照片
										<c:if test="${not empty attachmentList}">
									<span> <font color="red">(
									<c:forEach var="cur" items="${attachmentList}" varStatus="cou">   
									${cur.file_desc} 、
									</c:forEach>
									已存在，如要修改，请先删除再添加
									)</font></span>
									</c:if>
									</td>
								
								</tr>
								<tr><td colspan="4"  style="margin:20px 20px;" align="left">
									<c:if test="${not empty attachmentList}">
									<ul>
									<c:forEach var="cur" items="${attachmentList}" varStatus="cou">   
						      <li class="imgfloat">
						      <a href="${ctx}/manager/admin/Download.do?method=downloadFile1&save_name=${cur.save_name}" target="_blank">
								<img src="${ctx}/${cur.save_path}"	style="vertical-align: middle; cursor: pointer; width:300px; height:250px;"/>
								</a>
								<div style="margin-right: auto;margin-left: auto;"><span>${cur.file_desc}</span>&nbsp;&nbsp;&nbsp;<a href="#" id="att_del_${cur.id}">删除照片</a></div>
						     </li>
						     <c:if test="${cou.count % 3 eq 0}">   
						     </ul><ul>   
						     </c:if>   
						   </c:forEach> </ul>
						   	</c:if>
						   </td></tr>
						

						</table>
					</div>
				</div>
			</html-el:form>
		</div>
		<div class="clear"></div>
	</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=discuz"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script> 
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script>
<script type="text/javascript" src="${ctx}/jquery-ui/ui/ui.core.js"></script>
	<script type="text/javascript"><!--//<![CDATA[
$(document).ready(function(){
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}
	$("#store_name" ).attr("dataType" , "Require").attr("msg" , "请填写门店名称！");   //门店名称验证
	$("#_country" ).attr("dataType" , "Require").attr("msg" , "请将所在地区选择完全！");  
	$("#file_show" ).attr("dataType", "Filter" ).attr("msg", "图片的格式必须是(bmp,gif,jpeg,jpg)！").attr("require", "false").attr("accept", "bmp, gif, jpeg, jpg");
	$("#store_name").focus(setLength).attr("len", 30);

    var file_index=1;
    var file_index_ext=0;
	 $("#imgAddTr").click(function (){
		    file_index++;
	        $("#divFileHidden").clone().find("#file_hidden").attr("name", "file_show_"+file_index).end().appendTo($("#divFile")).show();
	        resizeFrameHeight();
	        $("img[id='imgDelTr']").each(function(){
	            $(this).click(function (){
	                $(this).parent().remove();
	                resizeFrameHeight();
	                file_index_ext=0;
	                $("input[class='file_show']").each(function(){
	                	$(this).attr("name","file_show_"+file_index_ext);
	                	file_index_ext++;
	                	
	            });
	            });
	        });
  });

	 $("a[id^='att_del_']").click(function() {
	  	  var a = this;
	  	   if(!confirm('确实要删除此附件？')) return;
	  	    $.post("KonkaR3Store.do", { method : "deleteFile", id : $(this).attr("id").replace(/att_del_/g, '')}, function(success) {
	  	   if (success){alert("恭喜您，删除附件成功!");$(a).parent().parent().remove();} else alert(" 很抱歉，删除附件出错!"); 
	  	  });
	   }); 
	

	// 订单备注
		$("#memo").textbox({
			maxLength: 200,
			onInput: function(event, status) {
				var img = '<img src="${ctx}/styles/images/tishi.gif" style="vertical-align:middle;" />';
				$("#memo_note").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
			}
		}).blur(function() {
			$("#memo_note").slideUp("normal");
		});
	 
	 
	//tabs切换Begin
		$("#content div[id^=tab]").hide(); // Initially hide all content
	    $("#tabs li:first").attr("id","current"); // Activate first tab
	    $("#content div[id^=tab]:first").fadeIn(); // Show first tab content
	    $('#tabs a').click(function(e) {
	        e.preventDefault();
	        if ($(this).closest("li").attr("id") == "current"){ //detection for current tab
	         	return       
	        } else{             
		        $("#content div[id^=tab]").hide(); //Hide all content
		        $("#tabs li").attr("id",""); //Reset id's
		        $(this).parent().attr("id","current"); // Activate this
		        $('#' + $(this).attr('name')).fadeIn(); // Show content for current tab
	        }
	        window.parent.resizeFrameHeight('mainFrame', 3);
	    });
		//tabs切换End
	 
	$("#send").click(function(){
		if($("#_province").val().length>0){
			$("#province").val($("#_province").find("option:selected").text());
		}

		if($("#_city").val().length>0){
			$("#city").val($("#_city").find("option:selected").text());
		}

		if($("#_country").val().length>0){
			$("#country").val($("#_country").find("option:selected").text());
		}
		
		if($("#_town").val().length>0){
			$("#town").val($("#_town").find("option:selected").text());
		}
		
		 
		var isSubmit = Validator.Validate(this.form, 2);
		if (isSubmit) {
			$(":button").attr("disabled", "true");
			$(":reset").attr("disabled", "true");
			this.form.submit();
		}
	 });
	 
	 // 区域
	$("#_province").attr({"subElement": "_city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${_province}"});
	$("#_city"    ).attr({"subElement": "_country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${_city}"});
	$("#_country" ).attr({"subElement": "_town","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${_country}"});
	$("#_town"    ).attr({"defaultText": "-请选择乡/镇-", "defaultValue": "", "selectedValue": "${_town}"});

	$("#_province").cs_ext("${ctx}/customer/manager/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false,"p_index_join","${af.map.p_index_join}");
	$("#_province").change();
	
	// 选择客户
	$("#r3_code").click(function(){
		var fgs_id = $("#dept_id").val();
		if ($.trim(fgs_id).length == 0) {
			alert("请先选择分公司.");
			return;
		}
		/*var returnValue = window.showModalDialog("KonkaR3Store.do?method=listCustomer&fgs_id=" + fgs_id +"&is_xx=0&ts=" + Math.random(), window, "dialogWidth:800px;status:no;dialogHeight:600px");
		if (!returnValue) {
			returnValue = window.returnValue;
		}
		if(returnValue){
			$("#r3_code").val(returnValue.cust_r3_code);
			$("#kh_name").val(returnValue.cust_name);
			$("#r3_shsdf_sn").val(returnValue.cust_r3_code);
		}*/
		
		$.dialog({
			title:  "客户列表",
			width:  800,
			height: 600,
	        lock:true ,
			content:"url:KonkaR3Store.do?method=listCustomer&fgs_id=" + fgs_id +"&is_xx=0&type=r3_code&ts=" + Math.random()
		});

		$("#xszz").val("");
		$("#ck_id").val("");
		$("#ck_name").val("");
		$("#mf_sn").val("");
	});
	
	//选择门店R3编码，允许选择已停用＋未停用，即全部客户 2014-09-30 Liang,Houen
	$("#r3_sdf_sn").click(function(){
		var fgs_id = $("#dept_id").val();
		if ($.trim(fgs_id).length == 0) {
			alert("请先选择分公司.");
			return;
		}
		/*var returnValue = window.showModalDialog("KonkaR3Store.do?method=listCustomer&fgs_id=" + fgs_id +"&ts=" + Math.random(), window, "dialogWidth:800px;status:no;dialogHeight:600px");
		if (!returnValue) {
			returnValue = window.returnValue;
		}
		if(returnValue){
			$("#r3_sdf_sn").val(returnValue.cust_r3_code);
		}*/
		$.dialog({
			title:  "客户列表",
			width:  800,
			height: 600,
	        lock:true ,
			content:"url:KonkaR3Store.do?method=listCustomer&fgs_id=" + fgs_id +"&type=r3_sdf_sn&ts=" + Math.random()
		});
	});


	$("#mf_sn").click(function(){
		//var fgs_id = $("#dept_id").val();
		var r3_code = $("#r3_code").val();
		var r3_code_2 = $("#r3_code_2").val();
		var r3code="";
		//if ($.trim(fgs_id).length == 0) {
		//	alert("请先选择分公司.");
		//	return;
		//}
		
		if ($.trim(r3_code).length == 0&&$.trim(r3_code_2).length == 0) {
			alert("请先选择客户R3编码.");
			return;
		}else{
			if ($.trim(r3_code).length != 0) {
				r3code=r3_code;
			}
			if ($.trim(r3_code_2).length != 0) {
				r3code=r3_code_2;
			}
		}
		//var returnValue = window.showModalDialog("KonkaR3Store.do?method=listCustomer&fgs_id=" + fgs_id +"&ts=" + Math.random(), window, "dialogWidth:800px;status:no;dialogHeight:600px");
		/*var returnValue = window.showModalDialog("KonkaR3Store.do?method=listchc&r3_code=" + r3code +"&ts=" + Math.random(), window, "dialogWidth:800px;status:no;dialogHeight:600px");
		if (!returnValue) {
			returnValue = window.returnValue;
		}
		if(returnValue){
			$("#ck_id").val(returnValue.ck_id);
			$("#ck_name").val(returnValue.ck_name);
			$("#mf_sn").val(returnValue.sdf_r3_code);
		}*/
		
		$.dialog({
			title:  "客户列表",
			width:  800,
			height: 600,
	        lock:true ,
			content:"url:KonkaR3Store.do?method=listchc&r3_code=" + r3code +"&ts=" + Math.random()
		});
		
		//$("#mf_sn").val(returnValue.cust_r3_code);
	});

	
	$("#r3_code_2").click(function(){
		var fgs_id = $("#dept_id").val();
		if ($.trim(fgs_id).length == 0) {
			alert("请先选择分公司.");
			return;
		}
		/*var returnValue = window.showModalDialog("KonkaR3Store.do?method=listCustomer&fgs_id=" + fgs_id +"&is_xx=0&ts=" + Math.random(), window, "dialogWidth:800px;status:no;dialogHeight:600px");
		if (!returnValue) {
			returnValue = window.returnValue;
		}
		if(returnValue){
			$("#r3_code").val(returnValue.cust_r3_code);
			$("#kh_name").val(returnValue.cust_name);
			$("#r3_code_2").val(returnValue.cust_r3_code);
			$("#kh_name_2").val(returnValue.cust_name);
			$("#r3_shsdf_sn_2").val(returnValue.cust_r3_code);
		}*/
		
		$.dialog({
			title:  "客户列表",
			width:  800,
			height: 600,
	        lock:true ,
			content:"url:KonkaR3Store.do?method=listCustomer&fgs_id=" + fgs_id +"&is_xx=0&type=r3_code_2&ts=" + Math.random()
		});
		
		$("#xszz").val("");
		$("#ck_id").val("");
		$("#ck_name").val("");
		$("#mf_sn").val("");
	});

	//检查合并编码是否重复
	$("#store_name").blur(function(){
	var $this = $(this);
	var v_store_name = $this.val();
	if(v_store_name == "" || v_store_name == null || v_store_name == '${af.map.store_name}') return ;
	
		$.ajax({
			type: "POST",
			url: "KonkaR3Store.do?method=validateName",
			data: {"v_store_name": v_store_name},
			dataType: "json",
			cache:false,
			error: function(){alert("数据加载请求失败！");},
			success: function(o){
				if(o.result == true){
					alert("该门店已存在或者已停用，请重新添加！");
					$("#store_name").val("");
					return;
				}
			}
		   });
	 }); //end
	 
	 
	 var competition='${af.map.competitions}';
	 if (null != competition){
		 var competitions = competition.split(",");
		 if(competitions.length>0){
			 for(var k in competitions){
				 $("#competition_"+competitions[k]).attr("checked","checked");
			 }
		 }
		 
	 } 
});

function fgs_id_jb_name(){
	$("#jb_job_id").empty();
	var fgs_id = $("#dept_id").val();
	if ($.trim(fgs_id).length == 0) {
		alert("请先选择分公司.");
		return;
	}
	$("<option value=''>-请选择经办-</option>").appendTo($("#jb_job_id"));
	var url = "${ctx}/manager/admin/CsAjax.do?method=getJybDeptListByFgsId2&fgs_dept_id="+$('#dept_id').val();
	$.getJSON(url, function(data) {
		if(data != null){
			$.each(data, function(i, item) {
				var option = $("<option></option>").val(item[1]).text(item[0]);
				option.appendTo($("#jb_job_id"));
			});
			if('${af.map.jb_job_id}' != null || '${af.map.jb_job_id}' != '' ){
				$("#jb_job_id").val('${af.map.jb_job_id}');
			}
		}
	});


	$("#jbjl_job_id").empty();
	$("<option value=''>-请选择经办经理-</option>").appendTo($("#jbjl_job_id"));
	var url = "${ctx}/manager/admin/CsAjax.do?method=getUserListByJybId&jb_id="+$('#dept_id').val();
	$.getJSON(url, function(data) {
		if(data != null){
			$.each(data, function(i, item) {
				var option = $("<option></option>").val(item[1]).text(item[0]);
				option.appendTo($("#jbjl_job_id"));
			});
			if('${af.map.jbjl_job_id}' != null || '${af.map.jbjl_job_id}' != '' ){
				$("#jbjl_job_id").val('${af.map.jbjl_job_id}');
			}
		}
	});


	$("#ywzg_job_id").empty();
	$("<option value=''>-请选择业务主管-</option>").appendTo($("#ywzg_job_id"));
	var url = "${ctx}/manager/admin/CsAjax.do?method=getUserListByJybId&jb_id="+$('#dept_id').val();
	$.getJSON(url, function(data) {
		if(data != null){
			$.each(data, function(i, item) {
				var option = $("<option></option>").val(item[1]).text(item[0]);
				option.appendTo($("#ywzg_job_id"));
			});
			if('${af.map.ywzg_job_id}' != null || '${af.map.ywzg_job_id}' != '' ){
				$("#ywzg_job_id").val('${af.map.ywzg_job_id}');
			}
		}
	});

	$("#ywy_job_id").empty();
	$("<option value=''>-请选择业务员-</option>").appendTo($("#ywy_job_id"));
	var url = "${ctx}/manager/admin/CsAjax.do?method=getYwyUserListByDeptId2&jb_id="+$('#dept_id').val();
	$.getJSON(url, function(data) {
		if(data != null){
			$.each(data, function(i, item) {
				var option = $("<option></option>").val(item[1]).text(item[0]);
				option.appendTo($("#ywy_job_id"));
			});
			if('${af.map.ywy_job_id}' != null || '${af.map.ywy_job_id}' != '' ){
				$("#ywy_job_id").val('${af.map.ywy_job_id}');
			}
		}
	});

	
	
}   

function resizeFrameHeight(offset, min_height) {
	// frame id can be found in page /konka-wd/WebContent/manager/admin/Frames2/indexFrame.jsp, and search 'iframe' to get.
	$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
}


function setLength(){
	$(this).keypress(function (){
		if(this.value.length > this.len){
			this.value = this.value.substring(0, this.len);
		}
	}).keyup(function (){
		if(this.value.length > this.len){
			this.value = this.value.substring(0, this.len);
		}
	}).blur(function (){
		if(this.value.length > this.len){
			this.value = this.value.substring(0, this.len);
		}
	});
}

function moveSelected(sourceSelect, targetSelect, isDelete){
	var cachOptionsArray = new Array();
	var index = 0;
	for (var i = sourceSelect.options.length - 1; i >= 0; i--){
		if (sourceSelect.options[i].selected){
			cachOptionsArray[index] = new Option(sourceSelect.options[i].text, sourceSelect.options[i].value);
			if(isDelete==undefined || isDelete==true){
				sourceSelect.options[i] = null;
			}
			index++;
		}
	}
	var exist = false;
	for (var i = cachOptionsArray.length - 1; i >= 0; i--){
		exist = false;
		for (var j = 0; j < targetSelect.options.length; j++){
			if (targetSelect.options[j].value.toString() == cachOptionsArray[i].value.toString()){
				exist = true; 
				break;
			}
		}
		if (!exist){
			targetSelect.options[targetSelect.options.length] = cachOptionsArray[i];
		}
	}
}
//]]>--></script>
	<jsp:include page="/__analytics.jsp" />
</body>
</html>
