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
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css"	rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet"	type="text/css" />
<link href="${ctx}/styles/customer/css/tab.css" rel="stylesheet"	type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css"	rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/fancybox/fancybox.css"	rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="oarcont">
  		<div class="oartop">
		    <table width="400" border="0" cellpadding="0" cellspacing="0">
		      <tr>
		        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
		        <td>当前位置：${naviString}</td>
		      </tr>
		    </table>
  		</div>
  		<div class="rtabcont2">
	    	<html-el:form action="/admin/WaitAuditCust" enctype="multipart/form-data">
				<html-el:hidden property="id" styleId="id" />
				<html-el:hidden property="mod_id" styleId="mod_id" value='${mod_id }'/>
				<html-el:hidden property="method" styleId="method" value="modify" />
				<html-el:hidden property="cust_id" styleId="cust_id" value="${af.map.cust_id }" />
				<html-el:hidden property="audit_stat" styleId="audit_stat" value="${af.map.audit_stat}" />
				<html-el:hidden property="cur_audit_user_id" styleId="cur_audit_user_id" value="${af.map.cur_audit_user_id}" />
				<html-el:hidden property="is_saled" styleId="is_saled" value="${af.map.is_saled}" />
				<html-el:hidden property="queryString" styleId="queryString" />
				<div style="float:right;">
					<label> 
					<input class="but4" type="button" name="Submit4" value="提交" id="btn_submit" style="padding-left: 30px"/>&nbsp;&nbsp;
					 <input class="but5" type="button" name="Submit5" value="返回" id='btn_back' onclick="history.back();" style="padding-left: 30px"/>
					</label>
				</div>
				<ul id="tabs">
					<li><a href="#" name="tab1">基本信息</a></li>
					<li><a href="#" name="tab2">建户材料</a></li>
				</ul>
				<div id="content">
					<div id="tab1">
					<table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td nowrap="nowrap" class="title_item" align="right">
								<strong class="fb">创建日期：</strong>
							</td>
							<td>
								<fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd" />
							</td>
							<td nowrap="nowrap" class="title_item" align="right">
								<strong class="fb">创建人：</strong>
							</td>
							<td>
								${create_man }
							</td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right">
								<strong class="fb"><font color="red">* </font>客户名称：</strong>
							</td>
							<td colspan="3">
								<html-el:text property="cust_name" size="40" maxlength="30" styleId="cust_name" styleClass="cla_dis"/>
							</td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right">
								<strong class="fb"><font color="red">* </font>所属经办：</strong>
							</td>
							<td>
								<html-el:select property="jb_id" styleId="jb_id" styleClass="cla_dis">
									<html-el:option value="">请选择...</html-el:option>
									<c:forEach var="cur" items="${jblist}">
										<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
									</c:forEach>
								</html-el:select>
							</td>
							<td nowrap="nowrap" class="title_item" align="right">
								<strong class="fb"><font color="red">* </font>客户类别：</strong>
							</td>
							<td>
								<html-el:select property="customer_type" styleId="customer_type" styleClass="cla_dis">
									<html-el:option value="">请选择...</html-el:option>
									<c:forEach var="cur" items="${konkaCategoryList}">
										<html-el:option value="${cur.c_index}">[${cur.c_comm}]${cur.c_name}</html-el:option>
									</c:forEach>
								</html-el:select>
							</td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right">
								<strong class="fb"><font color="red">* </font>所在城市：</strong>
							</td>
							<td colspan="3">
								<html-el:select property="province" styleId="province" style="width:180px;" styleClass="cla_dis">
				                  <option value="">-请选择省/直辖市/自治区-</option>
				                </html-el:select>
				                &nbsp;
				                <html-el:select property="city" styleId="city" style="width:100px;" styleClass="cla_dis">
				                  <option value="">-请选择市-</option>
				                </html-el:select>
				                &nbsp;
				                <html-el:select property="country" styleId="country" style="width:100px;" styleClass="cla_dis">
				                  <option value="">-请选择县-</option>
				                </html-el:select>
				                &nbsp;
				                <html-el:select property="town" styleId="town" style="width:100px;" styleClass="cla_dis">
				                  <option value="">-请选择乡镇-</option>
				                </html-el:select>
							</td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right">
								<strong class="fb"><font color="red">* </font>详细地址：</strong>
							</td>
							<td colspan="3">
								<html-el:text property="entp_addr" styleId="entp_addr" size="60" maxlength="40" styleClass="cla_dis"/>
							</td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right">
								<strong class="fb"><font color="red">* </font>法定代表人：</strong>
							</td>
							<td>
								<html-el:text property="host_name" styleId="host_name" size="20" maxlength="20" styleClass="cla_dis"/>
							</td>
							<td nowrap="nowrap" class="title_item" align="right">
								<strong class="fb"><font color="red">* </font>客户负责人：</strong>
							</td>
							<td>
								<html-el:text property="link_man_name" styleId="link_man_name" size="20" maxlength="20" styleClass="cla_dis"/>
							</td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right">
								<strong class="fb"><font color="red">* </font>经营项目：</strong>
							</td>
							<td>
								<html-el:text property="entp_main_pds" styleId="entp_main_pds" size="20" maxlength="20" styleClass="cla_dis"/>
							</td>
							<td nowrap="nowrap" class="title_item" align="right">
								<strong class="fb"><font color="red">* </font>联系电话：</strong>
							</td>
							<td>
								<html-el:text property="link_man_tel" styleId="link_man_tel" size="20" maxlength="20" styleClass="cla_dis"/>
							</td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right">
								<strong class="fb">门店面积（m²）：</strong>
							</td>
							<td>
								<html-el:text property="total_area" styleId="total_area" size="20" maxlength="20" styleClass="cla_dis"/>
							</td>
							<td nowrap="nowrap" class="title_item" align="right">
								<strong class="fb">年销售额（万元）：</strong>
							</td>
							<td>
								<html-el:select property="total_sale" styleId="total_sale" styleClass="cla_dis">
									<html-el:option value="">请选择...</html-el:option>
									<c:forEach var="cur" items="${entpScaleList}">
										<html-el:option value="${cur.c_index}">${cur.c_name}</html-el:option>
									</c:forEach>
								</html-el:select>
							</td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right">
								<strong class="fb">当地卖场数量：</strong>
							</td>
							<td colspan="3">
								<html-el:text property="total_malls" styleId="total_malls" size="20" maxlength="20" styleClass="cla_dis"/>
							</td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right">
								<strong class="fb">前期是否销售过康佳产品：</strong>
							</td>
							<td colspan="3">
								<html-el:radio property="is_saled" value="1" styleClass="cla_dis" styleId="yes_radio"></html-el:radio>是&nbsp;
								<html-el:radio property="is_saled" value="0" styleClass="cla_dis" styleId="no_radio"></html-el:radio>否&nbsp;&nbsp;&nbsp;&nbsp;
								<span class='yes_text' style="display: none">如选择是，请填报以下内容：</span>
							</td>
						</tr>
						<tr class='yes_text' style="display: none">
							<td nowrap="nowrap" class="title_item" align="right">
								&nbsp;
							</td>
							<td colspan="3">
								<strong class="fb">退出时间：</strong>
								<fmt:formatDate value="${af.map.out_date}" var="_out_date" pattern="yyyy-MM-dd" />
								<html-el:text property="out_date" styleClass="cla_dis" styleId="out_date" size="10" maxlength="10" value="${_out_date}" readonly="true" onclick="new Calendar(1900, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
							</td>
						</tr>
						<tr class='yes_text' style="display: none">
							<td nowrap="nowrap" class="title_item" align="right">&nbsp;
							</td>
							<td colspan="3">
								<strong class="fb">退出原因：</strong>
								<html-el:text property="out_reason" styleId="out_reason" size="20" maxlength="20" styleClass="cla_dis"/>
							</td>
						</tr>
						<tr class='yes_text' style="display: none">
							<td nowrap="nowrap" class="title_item" align="right">
								&nbsp;
							</td>
							<td colspan="3">
								<strong class="fb">是否有遗留问题：</strong>
								<html-el:text property="leave_question" styleId="leave_question" size="20" maxlength="20" styleClass="cla_dis"/>
							</td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right">
								备注说明：
							</td>
							<td colspan="3">
								<html-el:textarea property="memo" styleId="memo" rows="5" style="width:400px;" styleClass="cla_dis"></html-el:textarea>
							</td>
						</tr>
					</table>
					</div>
					<div id="tab2">
						<table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
							<c:forEach var="cur" items="${attachmentList}" varStatus="vs" end="12">
							<tr>
								<c:if test="${cur.file_desc eq 'ayyzzfyj' }"><td width="30%" class='ayyzzfyj' style="padding-left:100px"><font color="red">* </font>${vs.count}、营业执照复印件：</td></c:if>
								<c:if test="${cur.file_desc eq 'bgsswdjzfyj' }"><td width="30%" class='bgsswdjfyj' style="padding-left:100px"><font color="red">* </font>${vs.count}、国税税务登记证复印件：</td></c:if>
								<c:if test="${cur.file_desc eq 'cdsswdjzfyj' }"><td width="30%" class='cdsswdjzfyj' style="padding-left:100px"><font color="red">* </font>${vs.count}、地税税务登记证复印件：</td></c:if>
								<c:if test="${cur.file_desc eq 'dswh' }"><td width="30%" class='dswh' style="padding-left:100px"><font color="red">* </font>${vs.count}、商务函：</td></c:if>
								<c:if test="${cur.file_desc eq 'efddbrzms' }"><td width="30%" class='efddbrzms' style="padding-left:100px"><font color="red">* </font>${vs.count}、法定代表人证明书：</td></c:if>
								<c:if test="${cur.file_desc eq 'ffptkzl' }"><td width="30%" class='ffptkzl' style="padding-left:100px"><font color="red">* </font>${vs.count}、发票填开资料：</td></c:if>
								<c:if test="${cur.file_desc eq 'gshqzzm' }"><td width="30%" class='gshqzzm' style="padding-left:100px"><font color="red">* </font>${vs.count}、收货签章证明：</td></c:if>
								<c:if test="${cur.file_desc eq 'hfpqzzm' }"><td width="30%" class='hfpqzzm' style="padding-left:100px"><font color="red">* </font>${vs.count}、发票签章证明：</td></c:if>
								<c:if test="${cur.file_desc eq 'iyzylh' }"><td width="30%" class='iyzylh' style="padding-left:100px"><font color="red">* </font>${vs.count}、印章预留函：</td></c:if>
								<c:if test="${cur.file_desc eq 'jyhkhzl' }"><td width="30%" class='jyhkhzl' style="padding-left:100px"><font color="red">* </font>${vs.count}、银行开户资料：</td></c:if>
								<c:if test="${cur.file_desc eq 'kzdjybzs' }"><td width="30%" class='kzdjybzs' style="padding-left:100px"><font color="red">* </font>${vs.count}、正当交易保证书：</td></c:if>
								<c:if test="${cur.file_desc eq 'lsqfcsms' }"><td width="30%" class='lsqfcsms' style="padding-left:100px"><font color="red">* </font>${vs.count}、售前返厂声明书：</td></c:if>
								<c:if test="${cur.file_desc eq 'mkhkhsqb' }"><td width="30%" class='mkhkhsqb' style="padding-left:100px"><font color="red">* </font>${vs.count}、客户开户申请表：</td></c:if>
								<td colspan="3"><a href="${ctx}/manager/admin/Download.do?method=downloadFile1&save_name=${cur.save_name}" target="_blank">${cur.file_name}</a>&nbsp;&nbsp;&nbsp;
								<img src="../../images/x.gif" style="vertical-align:middle; cursor: pointer;" id="imgDelFile" title="删除"/></td>
							</tr>
			                </c:forEach>
			                <c:forEach begin="14" end='18' varStatus="vs" step="1">
			                	<c:if test="${vs.current eq 14 }">
				                	<c:set var="files_name" value="nfrdbwtsqzms" />
				                	<c:set var="files_name_text" value="法人代表委托授权证明书" />
			                	</c:if>
			                	<c:if test="${vs.current eq 15 }">
				                	<c:set var="files_name" value="owtzksqs" />
				                	<c:set var="files_name_text" value="委托转款授权书" />
			                	</c:if>
			                	<c:if test="${vs.current eq 16 }">
				                	<c:set var="files_name" value="pwtthzm" />
				                	<c:set var="files_name_text" value="委托提货证明" />
			                	</c:if>
			                	<c:if test="${vs.current eq 17 }">
				                	<c:set var="files_name" value="qlsthzm" />
				                	<c:set var="files_name_text" value="临时提货证明" />
			                	</c:if>
			                	<c:if test="${vs.current eq 18 }">
				                	<c:set var="files_name" value="rwlpsxys" />
				                	<c:set var="files_name_text" value="物流配送协议书" />
			                	</c:if>
								<c:forEach var="cur" items="${attachmentList}" begin="13">
									<c:if test="${cur.file_desc eq files_name }">
										<c:set var="have_file" value="1" />
										<c:set var="save_name" value="${cur.save_name}" />
										<c:set var="file_name" value="${cur.file_name}" />
									</c:if>
								</c:forEach>
								<tr>
									<c:if test="${have_file eq 1}">
										<td style="padding-left:100px" class='${files_name }'>
											${vs.current}、：${files_name_text }
										</td>
										<td colspan="3">
											<a href="${ctx}/manager/admin/Download.do?method=downloadFile1&save_name=${save_name}" target="_blank">${file_name }</a>&nbsp;&nbsp;&nbsp;
											<img src="../../images/x.gif" style="vertical-align:middle; cursor: pointer;" id="imgDelFilec" title="删除"/>
										</td>
									</c:if>
									
									<c:if test="${have_file eq 0 || empty have_file}">
										<td style="padding-left:100px" class='${files_name }'>
											${vs.current}、：${files_name_text }
										</td>
										<td colspan="3">
											<input name="${files_name }" type='file' id="${files_name }" style='width: 300px;'/>
										</td>
									</c:if>
								</tr>
								<c:set var="have_file" value="0" />
							</c:forEach>
						</table>
					</div>
				</div>
			  	<%-- <div id="tb" style="height: auto;margin-bottom: 20px;margin-top: 10px" align="center">
			  		<html-el:button property="" value="修改" styleClass="but4" styleId="btn_submit" />&nbsp;&nbsp;
			  		<html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();" />
				</div> --%>
				</html-el:form>
		  	</div>
		  	<jsp:include page="/__analytics.jsp" />
		</div>
		<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
		<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
		<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
		<script type="text/javascript">
			
		//初始化
		$(document).ready(function(){
			var mod_id = $('#mod_id').val();
			if(mod_id=='101005'){
				$("#btn_submit").attr("value", "提交");
			}
			
			$("#cust_name").attr("dataType", "Require").attr("msg", "请填写");
			$("#jb_id").attr("dataType", "Require").attr("msg", "请选择");
			$("#customer_type").attr("dataType", "Require").attr("msg", "请选择");
			$("#country").attr("dataType", "Require").attr("msg", "请选择");
			$("#customer_addr").attr("dataType", "Require").attr("msg", "请填写");
			$("#host_name").attr("dataType", "Require").attr("msg", "请填写");
			$("#link_man_name").attr("dataType", "Require").attr("msg", "请填写");
			$("#entp_main_pds").attr("dataType", "Require").attr("msg", "请填写");
			$("#host_tel").attr("dataType", "Require").attr("msg", "请填写");
			
			//所在地市联动
			$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${province}"});
			$("#city"    ).attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${city}"});
			$("#country" ).attr({"subElement": "town","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${country}"});
			$("#town" ).attr({"defaultText": "-请选择乡镇-", "defaultValue": "", "selectedValue": "${town}"});
			$("#province").cs("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false);
			
			//提交表单
			$("#btn_submit").click(function(){
				if(Validator.Validate(this.form, 3)){
					var demo = $("#memo").val();
					if(demo.length>1000){
						alert("备注说明的文字长度不得超过1000个！");
						return;
					}
		            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
					this.form.submit();
				}
			});
			
			//初始化时，若前期销售过康佳产品，则显示
			var is_saled = $("#is_saled").val();
			if(is_saled=='1'){
				$(".yes_text").show();
			}
			
			//单击“是”的事件
			$("#yes_radio").click(function(){
				$(".yes_text").show();
				window.parent.resizeFrameHeight('mainFrame', 3);
			});
			//单击“否”的事件
			$("#no_radio").click(function(){
				$("#out_date").val("");
				$("#out_reason").val("");
				$("#leave_question").val("");
				$(".yes_text").hide();
				window.parent.resizeFrameHeight('mainFrame', 3);
			});
			
			//删除必传附件时显示文件浏览控件
			$("img[id='imgDelFile']").each(function(){
	            $(this).click(function (){
	            	var beforeOneObj = $(this).parent();
	            	var beforetwoObj = beforeOneObj.prev();
	            	
	            	//文件类型名称
					var file_type = beforetwoObj.attr('class');
	            	
					beforeOneObj.html("<input name="+file_type+" type='file' id="+file_type+" style='width: 300px;'/>");
					$("#"+file_type).attr("dataType", "Filter" ).attr("msg", "附件格式必须是(bmp,jpeg,jpg)！").attr("require", "false").attr("accept", "bmp, jpeg, jpg");
	            });
	        });
			
			//删除可选传附件删除按钮时
			$("img[id='imgDelFilec']").each(function(){
	            $(this).click(function (){
	            	var beforeOneObj = $(this).parent();
	            	var beforetwoObj = beforeOneObj.prev();
	            	
	            	//文件类型名称
					var file_type = beforetwoObj.attr('class');
	            	
					beforeOneObj.html("<input name="+file_type+" type='file' id="+file_type+" style='width: 300px;'/>");
	            });
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
		});
		
		function resizeFrameHeight(offset, min_height) {
			// frame id can be found in page /konka-wd/WebContent/manager/admin/Frames2/indexFrame.jsp, and search 'iframe' to get.
			$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
		}
		</script>
	</body>
</html>
