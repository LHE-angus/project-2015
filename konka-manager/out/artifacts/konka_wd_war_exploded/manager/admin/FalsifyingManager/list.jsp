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
<link href="${ctx}/commons/styles/EntpShopSearch_style.css" rel="stylesheet" type="text/css" />

</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
     <html-el:form action="/admin/EntpShopSearch">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <html-el:hidden property="cls_ids_select" styleId="cls_ids_select" value="" />
      <html-el:hidden property="brand_ids_select" styleId="brand_ids_select" value="" />
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tdTab">
                  <tr>
                    <td width="15%" valign="middle" nowrap="nowrap" class="title_item">区域选择：</td>
                    <td width="85%" valign="middle" nowrap="nowrap">
                      <select name="province" id="province" class="bd">
                        <option value="">-请选择省/直辖市/自治区-</option>
                      </select>
                      <select name="city" id="city" class="bd">
                        <option value="">-请选择市-</option>
                      </select>
                      <select name="country" id="country" class="bd">
                        <option value="">-请选择县-</option>
                      </select>
                      <select name="town" id="town" class="bd">
                        <option value="">-请选择乡/镇-</option>
                      </select></td>
                  </tr>
                  <tr>
                    <td width="15%" valign="middle" nowrap="nowrap" class="title_item">网点名称：</td>
                    <td width="85%" valign="middle" nowrap="nowrap"><html-el:text property="shop_name_like" styleId="shop_name_like" maxlength="40" size="40" /></td>
                  </tr>
                  <tr>
                    <td valign="middle" class="title_item">
                       <img src="${ctx}/images/q_16.png" width="14" height="14" alt="帮助" title="“网点性质”的数据是从经销商登记信息中获取的，可以存在不完整。" /> 
                                                            网点性质：
                    </td>
                    <td height="28" valign="middle" ><span class="note"></span>
                      <label for="chain_type1"><input type="checkbox" name="chain_types" class="chain_types" id="chain_type1" value="1" />全国连锁</label>
                      <label for="chain_type2"><input type="checkbox" name="chain_types" class="chain_types" id="chain_type2" value="2" />区域型连锁</label>
                      <label for="chain_type3"><input type="checkbox" name="chain_types" class="chain_types" id="chain_type3" value="3" />商超百货系统</label>
                      <label for="chain_type11"><input type="checkbox" name="chain_types" class="chain_types" id="chain_type11" value="11" style="vertical-align:text-bottom;" />一般网点</label>
                      <label for="chain_type12"><input type="checkbox" name="chain_types" class="chain_types" id="chain_type12" value="12" />专卖店</label>
                      <label for="chain_type13"><input type="checkbox" name="chain_types" class="chain_types" id="chain_type13" value="13" />直营店</label>
                      <label for="all"><input type="checkbox" name="all" id="all"/>全选</label>
                    </td>
                  </tr>
                  <tr>
                    <td valign="middle" class="title_item">产品类别：</td>
                    <td height="28" valign="middle" ><html-el:select property="cls_id" styleId="cls_id" style="width:200px;">
                        <c:forEach var="cur" items="${basePdClassList}">
                          <html-el:option value="${cur.cls_id}" styleId="${cur.cls_name}-${cur.map.is_use}" style="color:${cur.map.is_use == 0 ? '#ccc' : ''};">${fn:escapeXml(cur.tree_name)}</html-el:option>
                        </c:forEach>
                      </html-el:select>
                      <input type="button" id="add_busi_type" style="margin-left:10px;cursor:pointer;" value=" 添加为条件"/>
                      <span class="note">注：自动添加“经营类别”后，系统将自动带出可供选择的“经营品牌”。</span></td>
                  </tr>
                  <tr>
                    <td valign="middle" class="title_item">经营类别：</td>
                    <td height="28" valign="middle" ><span id="busi_type_span"></span><span id="busi_type_default" style="color:#f00;">未选择</span></td>
                  </tr>
                  <tr>
                    <td valign="middle" class="title_item">经营品牌：</td>
                    <td height="28" valign="middle" ><span id="busi_brand_default" style="color:#f00;">未选择</span><img src="${ctx}/images/ajax-loader.gif" alt="ajax-loading" id="cls_id_loading" style="display:none;padding-left:20px;" />
                      <ul id="busi_brand_ul">
                      </ul></td>
                  </tr>
                  <tr>
                    <td valign="middle" class="title_item">经营面积：</td>
                    <td height="25" valign="middle" ><html-el:text property="busi_area_min" styleId="busi_area_min" maxlength="8" size="10" />
                      -
                      <html-el:text property="busi_area_max" styleId="busi_area_max" maxlength="8"  size="10" /></td>
                  </tr>
                  <tr>
                    <!--
                    <td valign="middle" class="title_item">顶级中标企业：</td>
                    <td height="25" valign="middle"><span style="float:left;"><span id="jdxx_pop_box">
                      <html-el:hidden property="root_entp_id_jdxx" styleId="root_entp_id_jdxx" />
                                                           家电下乡
                      <html-el:text property="root_entp_name_jdxx" style="width:250px;cursor:pointer;" styleId="root_entp_name_jdxx" readonly="true" onclick="openChild(2);" />
                      </span><span id="yjhx_pop_box"> 以旧换新
                      <html-el:hidden property="root_entp_id_yjhx" styleId="root_entp_id_yjhx" />
                      <html-el:text property="root_entp_name_yjhx" style="width:250px;cursor:pointer;" styleId="root_entp_name_yjhx" readonly="true" onclick="openChild(3);" />
                      </span></span>
                      <span style="float:right;" >
                      	<label for="more"><input type="checkbox" id="more" name="more" value="1" />&nbsp;更多条件</label>
                      </span>
                    </td>
                    -->
                    <td valign="middle" class="title_item">
                      <span style="float:right;" >
                      	<label for="more"><input type="checkbox" id="more" name="more" value="1" />&nbsp;更多条件</label>
                      </span>
                    </td>
                    <td height="25" valign="middle" class="title_item">&nbsp;</td>
                    
                  </tr>
                  <tr id="more_search" style="display:none;">
                    <td class="title_item"><html-el:select property="year" styleId="year" style="">
                        <html-el:option value="">--年份--</html-el:option>
                        <c:forEach begin="0" end="9" varStatus="vs">
                          <c:if test="${now_year ge (2009 + vs.count)}">
                            <html-el:option value="${2009 + vs.count}">${2009 + vs.count} 年</html-el:option>
                          </c:if>
                        </c:forEach>
                      </html-el:select>
                      <br />
                      <html-el:select property="month" styleId="month">
                        <html-el:option value="">--月份--</html-el:option>
                      </html-el:select><br/><span id="yearMon_msg" style="color:#f00;display:none;font-weight:normal;"> * 请输入年月！</span></td>
                    <td><table width="100%" border="0" cellpadding="0" cellspacing="0" id="x_t">
                        <tr>
                          <td> 品类
                            <html-el:select property="pd_type" styleId="pd_type" style="width:158px;">
                              <html-el:option value="">-请选择品类-</html-el:option>
                              <c:forEach var="cur" items="${basePdTypeList}" varStatus="vs">
                                <html-el:option value="${cur.pd_type}">${cur.pd_name}</html-el:option>
                              </c:forEach>
                            </html-el:select>，经营额
                            <html-el:text property="busi_total1" styleId="busi_total1" />
                                                                           万元以上；或经营占比
                            <html-el:text property="busi_proportion1" style="width:100px;" styleId="busi_proportion1" />
                            <span class="note">（用小数形式表示，如：0.55）</span> 以上；<br/><span id="pd_type_msg" style="color:#f00;display:none;"> * 请输入品类的经营额或经营占比！</span></td>
                        </tr>
                        <tr>
                          <td>品牌
                            <html-el:hidden property="brand_id" styleId="brand_id" />
                            <html-el:text property="brand_name" style="width:152px;cursor:pointer;" styleId="brand_name" readonly="true" onclick="openChild(1);" />
                                                                         ，经营额
                            <html-el:text property="busi_total2" styleId="busi_total2" />
                                                                           万元以上；或经营占比
                            <html-el:text property="busi_proportion2" style="width:100px;" styleId="busi_proportion2" />
                            <span class="note">（用小数形式表示，如：0.55）</span> 以上；<br/><span id="brand_msg" style="color:#f00;display:none;"> * 请输入品牌的经营额或经营占比！</span></td>
                        </tr>
                      </table>
                      <span id="more_msg" style="color:#f00;display:none;"> * 请选择品类或品牌！</span></td>
                  </tr>
                  <tr>
                    <td align="left">&nbsp;</td>
                    <td align="left"><span style="color:#f00;float:left;">★ 说明：不输入任何查询条件，系统默认不进行查询操作！</span> <span style="float:right;margin-right:20px;">
                      <html-el:button property="search" styleId="search_btn" styleClass="but1" value="搜索" />
                      </span></td>
                  </tr>
                </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
    <c:if test="${have_data eq '1'}">
    <html-el:form action="/admin/EntpShopSearch">
                <html-el:hidden property="method" value="add" />
                <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
                <html-el:hidden property="tree_param" value="${tree_param}" />
	            <html-el:hidden property="province" value="${af.map.province}" />
	            <html-el:hidden property="city" value="${af.map.city}" />
	            <html-el:hidden property="country" value="${af.map.country}" />
	            <html-el:hidden property="town" value="${af.map.town}" />
	            <html-el:hidden property="cls_id" value="${af.map.cls_id}" />
	            <html-el:hidden property="cls_ids_select" value="${af.map.cls_ids_select}" />
	            <html-el:hidden property="chain_types_str" value="${chain_types_str}" />
	            <html-el:hidden property="root_entp_id_jdxx" value="${af.map.root_entp_id_jdxx}" />
	            <html-el:hidden property="root_entp_name_jdxx" value="${af.map.root_entp_name_jdxx}" />
	            <html-el:hidden property="root_entp_id_yjhx" value="${af.map.root_entp_id_yjhx}" />
	            <html-el:hidden property="root_entp_name_yjhx" value="${af.map.root_entp_name_yjhx}" />
	            <html-el:hidden property="more" value="${af.map.more}" />
	            <html-el:hidden property="year" value="${af.map.year}" />
	            <html-el:hidden property="month" value="${af.map.month}" />
	            <html-el:hidden property="pd_type" value="${af.map.pd_type}" />
	            <html-el:hidden property="busi_total1" value="${af.map.busi_total1}" />
	            <html-el:hidden property="busi_proportion1" value="${af.map.busi_proportion1}" />
	            <html-el:hidden property="brand_id" value="${af.map.brand_id}" />
	            <html-el:hidden property="brand_name" value="${af.map.brand_name}" />
	            <html-el:hidden property="busi_total2" value="${af.map.busi_total2}" />
	            <html-el:hidden property="busi_proportion2" value="${af.map.busi_proportion2}" />
	            <html-el:hidden property="brand_ids_select" value="${brand_ids_select}" />
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td height="30" style="padding-left:10px;">
                  <!--<input name="button" type="button" value="批量开拓" onclick="confirmDispatch(this.form);" />
                  	<input name="button" type="button" value="全部开拓" onclick="allSubmit(this.form);" /> -->
                 </td>
                </tr>
	            </table>
                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="rtable2">
                  <tr class="tabtt1">
                    <td align="center" width="4%" nowrap="nowrap"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
                    <td nowrap="nowrap"><font class="blue">网点名称</font></td>
                    <td align="center" width="100" nowrap="nowrap" ><font class="blue">联系人</font></td>
                    <td align="center" width="100" nowrap="nowrap" ><font class="blue">联系电话</font></td>
                    <td align="center" width="160" nowrap="nowrap" ><font class="blue">操作</font></td>
                  </tr>
                  <c:forEach var="cur" items="${entityList}" varStatus="vs">
                    <tr>
                      <td align="center"><input name="pks" type="checkbox" id="pks" value="${cur.shop_id}" /></td>
                      <td align="left"><a href="<c:url value='/manager/admin/EntpShopSearch.do?method=view&shop_id=${cur.shop_id}&mod_id=${af.map.mod_id}'/>" style="text-decoration:none;"><font class="blue12px">${cur.shop_name}</font></a></td>
                      <td align="center">${fn:escapeXml(cur.link_user)}</td>
                      <td align="center">${fn:escapeXml(cur.link_phone)}</td>
                      <td align="center"><span style="cursor:pointer;" class="fblue"  onclick="doNeedMethod(null, 'EntpShopSellAnalysis.do', 'list','shop_id=${cur.shop_id}&'+$('#bottomPageForm').serialize())">经营情况</span>
                      |
                      <span style="cursor:pointer;" class="fblue"  onclick="doNeedMethod(null, 'EntpShopSearch.do', 'add','shop_id=${cur.shop_id}&'+$('#bottomPageForm').serialize())">开拓</span>
                      |
                      <span><a  class="fblue" href="<c:url value='/EntpShopMaps.do?shop_id=${cur.shop_id}'/>" style="text-decoration:none;color:#000;" target="_blank">周边</a></span></td>
                    </tr>
                  </c:forEach>
                </table></html-el:form>
                    <form id="bottomPageForm" name="bottomPageForm" method="post" action="EntpShopSearch.do">
                  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                      <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
                        <script type="text/javascript">
			            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
			            pager.addHiddenInputs("method", "list");
			            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			            pager.addHiddenInputs("tree_param", "${tree_param}");
			            pager.addHiddenInputs("province", "${af.map.province}");
			            pager.addHiddenInputs("city", "${af.map.city}");
			            pager.addHiddenInputs("country", "${af.map.country}");
			            pager.addHiddenInputs("town", "${af.map.town}");
			            pager.addHiddenInputs("cls_id", "${af.map.cls_id}");
			            pager.addHiddenInputs("cls_ids_select", "${af.map.cls_ids_select}");
			            pager.addHiddenInputs("chain_types_str", "${chain_types_str}");
			            //pager.addHiddenInputs("root_entp_id_jdxx", "${af.map.root_entp_id_jdxx}");
			            //pager.addHiddenInputs("root_entp_name_jdxx", "${af.map.root_entp_name_jdxx}");
			            //pager.addHiddenInputs("root_entp_id_yjhx", "${af.map.root_entp_id_yjhx}");
			            //pager.addHiddenInputs("root_entp_name_yjhx", "${af.map.root_entp_name_yjhx}");
			            pager.addHiddenInputs("more", "${af.map.more}");
			            pager.addHiddenInputs("year", "${af.map.year}");
			            pager.addHiddenInputs("month", "${af.map.month}");
			            pager.addHiddenInputs("pd_type", "${af.map.pd_type}");
			            pager.addHiddenInputs("busi_total1", "${af.map.busi_total1}");
			            pager.addHiddenInputs("busi_proportion1", "${af.map.busi_proportion1}");
			            pager.addHiddenInputs("brand_id", "${af.map.brand_id}");
			            pager.addHiddenInputs("brand_name", "${af.map.brand_name}");
			            pager.addHiddenInputs("busi_total2", "${af.map.busi_total2}");
			            pager.addHiddenInputs("busi_proportion2", "${af.map.busi_proportion2}");
			            pager.addHiddenInputs("brand_ids_select", "${brand_ids_select}");
			            document.write(pager.toString());
			   		</script></td>
                    </tr>
                  </table>
                </form>
              </c:if>
              <c:if test="${have_data eq '0'}">
                <div align="center" style="padding-top:40px;color:#f00;">对不起，没有查询出符合条件的记录集！</div>
              </c:if>
  </div>
  <div class="rtabcont3"></div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	// current page defined.
	$("label").css("cursor", "pointer");
	$("input[id^='root_entp_name_']").css("color", "#555");

	$("#busi_area_min" ).attr("focus",setOnlyNum);
	$("#busi_area_max" ).attr("focus",setOnlyNum);
	$("#busi_total1" ).attr("focus",setOnlyNum);
	$("#busi_proportion1" ).attr("focus",setOnlyNum);
	$("#busi_total2" ).attr("focus",setOnlyNum);
	$("#busi_proportion2" ).attr("focus",setOnlyNum);

	$("#more").click(function(){
		if($(this).attr('checked') == true){
			$("#more_search").show();
		} else {
			$("#more_search").hide();
		}
	});
	if($.trim("${more}").length > 0){
		$("#more").attr('checked',true);
		$("#more_search").show();
	}

	//初始化网点性质checkBox
	var chain_types = "${chain_types_str}";
	if($.trim(chain_types).length > 0){
		var chain_type_array = chain_types.split(",");
		for(var i = 0; i < chain_type_array.length; i++){
			$("#chain_type" + chain_type_array[i]).attr('checked',true);
		}
	}

	//全选网点性质checkBox
	$("#all").click(function(){
		$(".chain_types").attr('checked',this.checked);
	});

	//根据年份动态加载月份
	if($.trim("${af.map.year}").length > 0 && $.trim("${af.map.month}").length > 0){
		$("#year").val('${af.map.year}' + ' 年');
		setMonthByYear();
		$("#month").val('${af.map.month}' + ' 月');
	}
	$("#year").change(function(){
		setMonthByYear();
	});

	var cls_id_select = "";

	if($.trim("${bpcList_string}").length > 0){
		$("#busi_type_default").hide();
		var bpcList = "${bpcList_string}".split(";");
		for(var i = 0; i < bpcList.length; i++ ){
			var cls_name = bpcList[i].split("-")[0];
			var cls_id = bpcList[i].split("-")[1];
			cls_id_select = cls_id_select + cls_id + ",";
			$("#busi_type_span").append("<span style='padding-left:10px;'>" + cls_name + "<img src='${ctx}/images/del_btn.png' id='del_" + cls_id + "' style='cursor:pointer;vertical-align:middle;' title='删除'></img></span>");
			$("#del_" + cls_id).bind("click",function(){
				$(this).parent().remove();
				cls_id_select = cls_id_select.replace(cls_id + ",","");
				if($.trim(cls_id_select).length > 0){
					getBrandNameByClsIds(cls_id_select);
				}else{
					$("#busi_type_default").show();
					$("#busi_brand_default").show();
					$("#busi_brand_ul").children().remove();
					$("#busi_brand_ul").css("height","");
				}
			});
		}
	}

	if($.trim("${brandList_string}").length > 0){
		$("#busi_brand_default").hide();
		var brandList = "${brandList_string}".split(";");
		var html = "";
		for(var i = 0; i < brandList.length; i++){
			var brand_name = brandList[i].split("-")[0];
			var brand_id =  brandList[i].split("-")[1].replace("selected","");
			var is_selected = brandList[i].split("-")[1].indexOf("selected") < 0 ? 0 : 1;
			if(is_selected == 0){
				html = html + "<li style='width:120px;float:left;'><input type='checkbox' name='brand_ids' id='brand_ids_" + brand_id + "' value='" +  brand_id + "' /><label for='brand_ids_"+ brand_id +"'>" + brand_name + "</label></li>";
			}else{
				html = html + "<li style='width:120px;float:left;'><input type='checkbox' name='brand_ids' id='brand_ids_" + brand_id + "' value='" +  brand_id + "' checked='checked' /><label for='brand_ids_"+ brand_id +"'>" + brand_name + "</label></li>";
			}
		}
		if(brandList.length > 80){
			$("#busi_brand_ul").css({"height":"200px","overflow-y":"auto"});
		}else{
			$("#busi_brand_ul").css("height","");
		}
		$("#busi_brand_ul").html(html);
	}

	$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${af.map.province}"});
	$("#city"    ).attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${af.map.city}"});
	$("#country" ).attr({"subElement": "town","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${af.map.country}"});
	$("#town"    ).attr({"defaultText": "-请选择乡/镇-", "defaultValue": "", "selectedValue": "${af.map.town}"});

	$("#province").cs_ext("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false,"p_index_join","${af.map.p_index_join}");
	$("#province").change();
	
	$("#add_busi_type").click(function(){
		var cls_id = $("#cls_id option:selected").val();
		var cls_name = $("#cls_id option:selected").attr("id").split("-")[0];
		var is_use = $("#cls_id option:selected").attr("id").split("-")[1];
		if(cls_id == 0){
			alert("“家电分类”无法作为类别，请选择其子节点！");
			return false;
		}
		if(is_use == 0){
			alert("对不起，您没有权限添加该经营类别，请选择可添加的类别！");
			return false;
		}
		if(("," + cls_id_select).indexOf("," + cls_id + ",") < 0){
			$("#busi_type_span").append("<span style='padding-left:10px;'>" + cls_name + "<img src='${ctx}/images/del_btn.png' id='del_" + cls_id + "' style='cursor:pointer;vertical-align:middle;' title='删除'></img></span>");
			$("#del_"+ cls_id).bind("click",function(){
				$(this).parent().remove();
				cls_id_select = cls_id_select.replace(cls_id + ",","");
				if($.trim(cls_id_select).length > 0){
					getBrandNameByClsIds(cls_id_select);
				}else{
					$("#busi_type_default").show();
					$("#busi_brand_default").show();
					$("#busi_brand_ul").children().remove();
					$("#busi_brand_ul").css("height","");
				}
			});
			cls_id_select = cls_id_select + cls_id + ",";
			if($.trim(cls_id_select).length > 0){
				$("#busi_type_default").hide();
				getBrandNameByClsIds(cls_id_select);
			}
		}else{
			alert("对不起，该产品类别已经添加为经营类别条件！");
			return false;
		}		
	});

	$("#search_btn").click(function(){

		var province = $("#province").val();
		var city = $("#city").val();
		var country = $("#country").val();
		var town = $("#town").val();
		var chain_types_check = "";
		$(".chain_types").each(function(){
			if($(this).attr("checked") == true){
				chain_types_check = $(this).val();
				return;
			}
		});
		var busi_area_min = $("#busi_area_min").val();
		var busi_area_max = $("#busi_area_max").val();
		//var root_entp_id_jdxx = $("#root_entp_id_jdxx").val();
		//var root_entp_id_yjhx = $("#root_entp_id_yjhx").val();

		var year = $("#year").val();
		var month = $("#month").val();
		var pd_type = $("#pd_type").val();
		var brand_id = $("#brand_id").val();

		var more_condition = "";
		if($("#more").attr('checked') == true){
			more_condition = 1;
		}

		if($.trim(province).length == 0 && $.trim(city).length == 0 
				&& $.trim(country).length == 0 && $.trim(town).length == 0 
				&& $.trim(chain_types_check).length == 0 && $.trim(cls_id_select).length == 0 
				&& $.trim(busi_area_min).length == 0 && $.trim(busi_area_max).length == 0 
				/*&& $.trim(root_entp_id_jdxx).length == 0 && $.trim(root_entp_id_yjhx).length == 0 */
				&& $.trim(more_condition).length == 0
				&& $.trim($("#shop_name_like").val()).length == 0){
			alert("对不起，请至少选择或输入一个查询条件！");
			return false;
		}
		var flag = true;
		if($("#more").attr('checked') == true){
			if($.trim(year).length == 0 || $.trim(month).length == 0){
				$("#yearMon_msg").show();
			}else{
				$("#yearMon_msg").hide();
			}
			if($.trim(pd_type).length == 0 && $.trim(brand_id).length == 0){
				$("#more_msg" ).show();
				flag = false; 
			}else {
				$("#more_msg" ).hide();
				var busi_total1 = $("#busi_total1").val();
				var busi_proportion1 = $("#busi_proportion1").val();
				var busi_total2 = $("#busi_total2").val();
				var busi_proportion2 = $("#busi_proportion2").val();
				if($.trim(pd_type).length > 0  && $.trim(brand_id).length == 0 ){
					$("#brand_msg").hide();
					if($.trim(busi_total1).length == 0 && $.trim(busi_proportion1).length == 0){
						$("#pd_type_msg").show();
						flag = false;
					}else{
						$("#pd_type_msg").hide();
					}
				}
				if($.trim(brand_id).length > 0 && $.trim(pd_type).length == 0){
					$("#pd_type_msg").hide();
					if($.trim(busi_total2).length == 0 && $.trim(busi_proportion2).length == 0){
						$("#brand_msg").show();
						flag = false;
					}else{
						$("#brand_msg").hide();
					}
				}
				if($.trim(brand_id).length > 0 && $.trim(pd_type).length > 0){
					if($.trim(busi_total1).length == 0 && $.trim(busi_proportion1).length == 0){
						$("#pd_type_msg").show();
						flag = false;
					}else{
						$("#pd_type_msg").hide();
					}
					if($.trim(busi_total2).length == 0 && $.trim(busi_proportion2).length == 0){
						$("#brand_msg").show();
						flag = false;
					}else{
						$("#brand_msg").hide();
					}
				}
			}
		}
		$("#cls_ids_select").val(cls_id_select);
		var isSubmit = Validator.Validate(this.form, 3) && flag;
		$("#year" ).removeAttr("dataType");
		$("#month" ).removeAttr("dataType");
		if (isSubmit) {
			$(this).attr("disabled","true").attr("value","正在查询...");
			this.form.submit();
		}
	});
});

function getBrandNameByClsIds(cls_ids){    //通过cls_id查询brand_name
	$("#busi_brand_ul").hide();
	$("#busi_brand_default").hide();
	$("#cls_id_loading").show();
	$.ajax({
		type: "POST",
		url: "EntpShopSearch.do",
		data: "method=getBrandIdsByClsId&cls_ids=" + cls_ids.substring(0 , cls_ids.length - 1),
		dataType: "json",
		error: function(request, settings) {tag = false;},
		success: function(oper) {
			if ("" != oper.result){
				var strs = oper.result.split(";");
				var brand_names = "";
				var html = "";
				for(i = 0; i < strs.length - 1; i++){
					var temps = strs[i].split("-");
					html = html + "<li style='width:120px;float:left;'><input type='checkbox' name='brand_ids' id='brand_ids_" + temps[1] + "' value='" +  temps[1] + "' /><label for='brand_ids_"+ temp[1] +"'>" + temps[0] + "</label></li>";
				}
				if(strs.length > 80){
					$("#busi_brand_ul").css({"height":"200px","overflow-y":"auto"});
				}else{
					$("#busi_brand_ul").css("height","");
				}
				$("#cls_id_loading").hide();
				$("#busi_brand_ul").html(html);
				$("#busi_brand_ul").show();
			} else {
				$("#cls_id_loading").hide();
			}
		}
    });
}

function openChild(num){
	var now= new Date().getTime();
	if(num == 1){
		var returnValue = window.showModalDialog("EntpShopSearch.do?method=listBaseBrandInfo&time="+now, window, "dialogWidth:800px;status:no;dialogHeight:680px");
	    if(returnValue != null){
			var value = returnValue.split(",");
			document.getElementById("brand_name").value = value[0];
			document.getElementById("brand_id").value = value[1];
	    } 
	}else if(num == 2){
		var returnValue = window.showModalDialog("EntpShopSearch.do?method=listJdxxRootEntp&time="+now, window, "dialogWidth:800px;status:no;dialogHeight:680px");
	    if(returnValue != null){
			var value = returnValue.split(",");
			document.getElementById("root_entp_name_jdxx").value = value[0];
			document.getElementById("root_entp_id_jdxx").value = value[1];
	    } 
	}else if(num == 3){
		var returnValue = window.showModalDialog("EntpShopSearch.do?method=listYjhxRootEntp&time="+now, window, "dialogWidth:800px;status:no;dialogHeight:680px");
	    if(returnValue != null){
			var value = returnValue.split(",");
			document.getElementById("root_entp_name_yjhx").value = value[0];
			document.getElementById("root_entp_id_yjhx").value = value[1];
	    } 
	}
	
}

function setMonthByYear(){    //根据年份设置月份
	if($.trim($("#year").val()).length > 0){
		var year = parseInt($("#year").val(),10);
		var now_year = parseInt("${now_year}",10);
		var now_month = parseInt("${now_month}",10);
		var month = document.getElementById("month");  //month为下拉列表Month对象 
		month.length = 1; //保留第一个选择项
		if(year == 2010){
		    for(var i = 8; i <= 12; i++){ 
			    if(i < 10){
				    i = "0" + i;
				}
			    var option = new Option(); 
		        option.text = i + ' 月'; 
		        option.value = i; 
		        month.options.add(option);  //往下拉列表Month添加数据 
		    } 
		}
		if(year == now_year){
		    for(var i = 1; i < now_month; i++){ 
			    if(i < 10){
				    i = "0" + i;
				}
			    var option = new Option(); 
		        option.text = i + ' 月'; 
		        option.value = i; 
		        month.options.add(option);  //往下拉列表Month添加数据 
		    } 
		}
		if(year > 2010 && year < now_year){
		    for(var i = 1; i <= 12; i++){ 
			    if(i < 10){
				    i = "0" + i;
				}
			    var option = new Option(); 
		        option.text = i + ' 月'; 
		        option.value = i; 
		        month.options.add(option);  //往下拉列表Month添加数据 
		    } 
		}
	}
}

function setOnlyNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;
}

function confirmDispatch(form){
	var checkedCount = 0;
	if (!form.pks) {
		return;
	}
	if(!form.pks.length) {
		if (form.pks.checked == true) {
			checkedCount = 1;
		}
	}
	for (var i = 0; i < form.pks.length; i++) {
		if (form.pks[i].checked == true) {
			checkedCount++;
		}
	}
	if (checkedCount == 0) {
		alert("请至少选择一个网点！");
	} else {
			form.submit();
		}
}

function allSubmit(form){
	form.method.value = 'allSave';
	form.submit();
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>