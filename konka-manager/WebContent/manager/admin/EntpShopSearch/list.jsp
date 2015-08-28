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
<link href="${ctx}/styles/EntpShopGSite/map.css" rel="stylesheet" type="text/css" />
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
                        <!-- <option value="">-请选择省/直辖市/自治区-</option> -->
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
                  <!-- 网点类别暂不用 
                  <tr>
                    <td valign="middle" class="title_item">
                                                            网点类别：
                    </td>
                    <td height="28" valign="middle" ><span class="note"></span>
                      <label for="shop_type1"><input type="checkbox" name="shop_types" class="shop_types" id="shop_type1" value="1" checked="checked" />R3用户</label>
                      <label for="shop_type2"><input type="checkbox" name="shop_types" class="shop_types" id="shop_type2" value="2" checked="checked" />经销网点</label>
                      <label for="shop_type3"><input type="checkbox" name="shop_types" class="shop_types" id="shop_type3" value="3" checked="checked" />其它网点</label>
                    </td>
                  </tr>
                  -->             
                  <tr>
                    <td width="15%" valign="middle" nowrap="nowrap" class="title_item">网点名称：</td>
                    <td width="85%" valign="middle" nowrap="nowrap">
                     <html-el:text property="shop_name_like" styleId="shop_name_like" maxlength="40" size="40" />
                     </td>
                  </tr>
                  <tr>
                    <td valign="middle" class="title_item">产品类别：</td>
                    <td nowrap="nowrap" height="28" valign="middle" ><html-el:select property="cls_id" styleId="cls_id" style="width:200px;">
                        <c:forEach var="cur" items="${basePdClassList}">
                          <html-el:option value="${cur.cls_id}" styleId="${cur.cls_name}-${cur.map.is_use}" style="color:${cur.map.is_use == 0 ? '#ccc' : ''};">${fn:escapeXml(cur.tree_name)}</html-el:option>
                        </c:forEach>
                      </html-el:select>
                      <input type="button" id="add_busi_type" style="margin-left:5px;cursor:pointer;" value="添加为条件"/>
                      <span class="note">注：自动添加“经营类别”后，系统将自动带出供选择的“经营品牌”</span></td>
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
                    <td valign="middle" class="title_item">
                      <span style="float:right;" >
                      	<label for="more"><input type="checkbox" id="more" name="more" value="1" />&nbsp;更多条件&nbsp;&nbsp;</label>
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
                            <html-el:select property="pd_type" styleId="pd_type" style="width:120px;">
                              <html-el:option value="">-请选择品类-</html-el:option>
                              <c:forEach var="cur" items="${basePdTypeList}" varStatus="vs">
                                <html-el:option value="${cur.pd_type}">${cur.pd_name}</html-el:option>
                              </c:forEach>
                            </html-el:select>，经营额
                            <html-el:text property="busi_total1" styleId="busi_total1" style="width:60px;"/>
                                                                           万元以上；或经营占比
                            <html-el:text property="busi_proportion1" style="width:60px;" styleId="busi_proportion1" />
                            <span class="note">（用小数表示，如：0.55）</span> 以上；<br/><span id="pd_type_msg" style="color:#f00;display:none;"> * 请输入品类的经营额或经营占比！</span></td>
                        </tr>
                        <tr>
                          <td>品牌
                            <html-el:hidden property="brand_id" styleId="brand_id" />
                            <html-el:text property="brand_name" style="width:110px;cursor:pointer;" styleId="brand_name" readonly="true" onclick="openChild(1);" />
                                                                         ，经营额
                            <html-el:text property="busi_total2" styleId="busi_total2" style="width:60px;"/>
                                                                           万元以上；或经营占比
                            <html-el:text property="busi_proportion2" style="width:60px;" styleId="busi_proportion2" />
                            <span class="note">（用小数表示，如：0.55）</span> 以上；<br/><span id="brand_msg" style="color:#f00;display:none;"> * 请输入品牌的经营额或经营占比！</span></td>
                        </tr>
                      </table>
                      <span id="more_msg" style="color:#f00;display:none;"> * 请选择品类或品牌！</span></td>
                  </tr>
                  <tr>
                    <td align="left">&nbsp;</td>
                    <td align="left">
                     <span style="float:right;margin-right:20px;">
                      <c:if test="${empty af.map.page_type || af.map.page_type eq 0}">
                        <input type="radio"  name="page_type" id="page_type_0" value="0" checked="checked" onclick="changePage(0);"/>列表
                        <input type="radio"  name="page_type" id="page_type_1" value="1" onclick="changePage(1);"/>地图
                      </c:if>
                       <c:if test="${af.map.page_type eq 1}">
                        <input type="radio"  name="page_type" id="page_type_0" value="0" onclick="changePage(0);"/>列表
                        <input type="radio"  name="page_type" id="page_type_1" value="1" checked="checked" onclick="changePage(1);"/>地图
                      </c:if>
                      </span>
                    </td>
                  </tr>                
                  <tr>
                    <td align="left">&nbsp;</td>
                    <td align="left">
                      <span style="color:#f00;float:left;">★ 说明：不输入任何查询条件，系统默认不进行查询操作！</span> 
                      <span style="float:right;margin-right:20px;">
                          <html-el:button property="search" styleId="search_btn" styleClass="but1" value="搜索" />
                      </span></td>
                  </tr>
                </table>
    </html-el:form>
  </div>

  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <c:if test="${empty af.map.page_type || af.map.page_type eq 0}">
    <div class="rtabcont1" id="page_list">
      <!-- 网点列表 -->
      <%@ include file="shop_list.jsp" %>
    </div>
    <div class="rtabcont1" id="page_map" style="margin:0px;padding:0px;display:none;">
      <!-- 网点地图 -->
      <%@ include file="shop_map.jsp" %>
    </div>
  </c:if>
  <c:if test="${af.map.page_type eq 1}">
    <div class="rtabcont1" id="page_list" style="display:none;">
      <!-- 网点列表 -->
      <%@ include file="shop_list.jsp" %>
    </div>
    <div class="rtabcont1" id="page_map" style="margin:0px;padding:0px;">
      <!-- 网点地图 -->
      <%@ include file="shop_map.jsp" %>
    </div>
  </c:if>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.pager.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<c:if test="${af.map.page_type eq 1}">
<%@include file="__gmap.jsp" %>
<%@ include file="__ga_web.jsp" %>
</c:if>
<c:if test="${af.map.page_type eq 1}">
<%@ include file="shop_map_js.jsp" %>
<script type="text/javascript" src="${ctx}/manager/scripts/charts.js"></script>
</c:if>
<%@ include file="shop_list_js.jsp" %>

<script type="text/javascript">//<![CDATA[
var page_flg = 0, page_init = 0;
<c:if test="${af.map.page_type eq 1}">
page_flg = 1;
</c:if>
function changePage(flg){
	page_flg = flg;	
}

function setMapLayout($map) {
	$(".listDiv").height($(window).height() - $("#shop_disp_div").offset().top - 14 + 300);
	$(".sbox").height($("#shop_disp_div").height());
	$(".pro_list").height($("#shop_disp_div").height() - 40);
	$("#map").height($(window).height() - $map.offset().top - 13 + 300);
}

$(document).ready(function(){
    // 区域
	$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${af.map.province}"});
	$("#city"    ).attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${af.map.city}"});
	$("#country" ).attr({"subElement": "town","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${af.map.country}"});
	$("#town"    ).attr({"defaultText": "-请选择乡/镇-", "defaultValue": "", "selectedValue": "${af.map.town}"});

	$("#province").cs_ext("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false,"p_index_join","${af.map.p_index_join}");
	$("#province").change();

	$("#shop_name_like").keydown(function(e){ if (e.keyCode == 13) $("#search_btn").click(); }).textInputWithVal("请输入商铺名称/商铺产品名称/商铺简介搜索！");

    var shop_name_search = "${af.map.shop_name_like}";
    if(shop_name_search != "")
    {
       $("#shop_name_like").val(shop_name_search);
    }   
	
	// 地图显示
<c:if test="${af.map.page_type eq 1}">
	var $map = $("#map").css({"border-right":"1px solid #ccc", "border-top":"1px solid #ccc"});
    setMapLayout($map);	
    $(window).resize(function() {
	   setMapLayout($map);
    });
    var map = new GMap2($map[0]);
	// 下载地图
	loadGMap(map);
	// current page defined.
	var sub_val = $.trim($("#shop_name_like").val());
	resetGMarkersToMap(map, $.extend({}, { shop_name_like : sub_val == $("#shop_name_like").attr("emptyValue") ? '' : sub_val }));

	$("#province, #city, #country").change(function() {
		if(page_flg == 1){
			if (this.id == 'province') $("#city, #country").val("");		
			var sub_val = $.trim($("#shop_name_like").val());
			setMapCenterByPIndex(map, $(this).val());
		}
	});
</c:if>

	$("label").css("cursor", "pointer");
	$("input[id^='root_entp_name_']").css("color", "#555");
	
	$("#busi_total1" ).attr("focus",setOnlyNum);
	$("#busi_proportion1" ).attr("focus",setOnlyNum);
	$("#busi_total2" ).attr("focus",setOnlyNum);
	$("#busi_proportion2" ).attr("focus",setOnlyNum);

	$("#more").click(function(){
		if($(this).attr('checked') == true || $(this).attr('checked') == 'checked'){
			$("#more_search").show();
		} else {
			$("#more_search").hide();
		}
	});
	
	if($.trim("${more}").length > 0){
		$("#more").attr('checked',true);
		$("#more_search").show();
	}

	/*
	//初始化网点性质checkBox
	var shop_types = "${shop_types_str}";
	if($.trim(shop_types).length > 0){
		var shop_type_array = shop_types.split(",");
		for(var i = 0; i < shop_type_array.length; i++){
			$("#shop_type" + shop_type_array[i]).attr('checked',true);
		}
	}
	*/
	//根据年份动态加载月份
	if($.trim("${af.map.year}").length > 0 && $.trim("${af.map.month}").length > 0){
		$("#year").val('${af.map.year}');
		setMonthByYear();
		$("#month").val('${af.map.month}');
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
				html = html + "<li style='width:120px;float:left;'><input type='checkbox' name='brand_ids' id='brand_ids_" + brand_id + "' value='" +  brand_id + "' /><label for='brand_ids_'"+ brand_id+">" + brand_name + "</label></li>";
			}else{
				html = html + "<li style='width:120px;float:left;'><input type='checkbox' name='brand_ids' id='brand_ids_" + brand_id + "' value='" +  brand_id + "' checked='checked' /><label for='brand_ids'"+ brand_id+">" + brand_name + "</label></li>";
			}
		}
		if(brandList.length > 80){
			$("#busi_brand_ul").css({"height":"200px","overflow-y":"auto"});
		}else{
			$("#busi_brand_ul").css("height","");
		}
		$("#busi_brand_ul").html(html);
	}
	
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

	// 查询事件处理 start
	$("#search_btn").click(function(){

		var province = $("#province").val();
		var city = $("#city").val();
		var country = $("#country").val();
		var town = $("#town").val();

		var year = $("#year").val();
		var month = $("#month").val();
		var pd_type = $("#pd_type").val();
		var brand_id = $("#brand_id").val();

		/*
		var shop_types_check = "";
		$(".shop_types").each(function(){
			if($(this).attr("checked") == true){
				shop_types_check = $(this).val();
				return;
			}
		});
		*/
		var more_condition = "";
		if($("#more").attr('checked') == true){
			more_condition = 1;
		}

		var sub_val = $.trim($("#shop_name_like").val());
		var shop_name = sub_val == $("#shop_name_like").attr("emptyValue") ? '' : sub_val;
		if($.trim(province).length == 0 && $.trim(city).length == 0 
				&& $.trim(country).length == 0 && $.trim(town).length == 0 
				/*&& $.trim(shop_types_check).length == 0*/  
				&& $.trim(more_condition).length == 0 && $.trim(cls_id_select).length == 0
				&& shop_name.length == 0){
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
            $("#shop_name_like").val(shop_name);	
            this.form.submit();
		}
	});
	// 查询事件处理end
	
	$(".rec_entpshop").click(function(){
		var p = $(this).attr("id").split(",");
		if (p.length == 2) {
			if (/^\s*$/.test(p[0]) || /^\s*$/.test(p[1])) { alert("很抱歉，该店铺还没有指定地理位置！"); return false; }
			map.panTo(new GLatLng(parseFloat(p[0]), parseFloat(p[1])));
		}
	}).hover(function() {
		$(this).css("background-color", "#eee");
	}, function() {
		$(this).css("background-color", "");
	});
});

//以下为自定义的jQuery插件
$.fn.extend({
	textInputWithVal : function(val){
		var $this = this;
		return $this.attr("emptyValue", val).blur(function() {
			if ($.trim($this.val()).length == 0) { $this.val(val); }
		}).focus(function() {
			if ($.trim($this.val()) == val) { $this.val(""); }
		}).val(val);
	},
	json : function(){
		var obj = {};
		var ss = $(this).serialize().split("&");
		for (var i = 0; i < ss.length; i++) {
			obj[ss[i].split("=")[0]] = ss[i].split("=")[1];
		}
		return obj;
	}
});
$.extend({
	isBlank : function(s) { return (s === undefined) || (s === null) || /^\s*$/g.test(s.replace("null", "")); },
	isNotBlank : function(s) { return !$.isBlank(s); }
});

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>