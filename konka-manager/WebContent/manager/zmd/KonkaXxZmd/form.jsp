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
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<style type="text/css">
/**ul {
	width:100%;
}
ul li {
	width:200px;
	float:left;
	overflow:hidden;
}*/
#fact_str {
	width:100%;
}
#fact_str li {
	width:200px;
	float:left;
	overflow:hidden;
}
</style>
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
  <%@ include file="/commons/pages/messages.jsp" %>
  <div class="rtabcont2" style="position:relative;overflow:hidden;">
    <html-el:form action="/zmd/KonkaXxZmd"  styleClass="form_cust" enctype="multipart/form-data">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="store_ids" styleId="store_ids" value="" />
      <html-el:hidden property="res_ids" styleId="res_ids" value="${af.map.res_ids}" />
      <html-el:hidden property="zmd_id" value="${af.map.zmd_id}" />
      <html-el:hidden property="edit_value" value="${af.map.edit_value}" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable4">
        <tr>
          <td colspan="4" style="font-weight:bold;font-size:18px;height:40px;text-align:center;border-bottom:none;">编辑“${af.map.zmd_sn}”专卖店资料</td>
        </tr>
        <tr>
          <td colspan="4" style="font-weight:900;"><strong class="fb">基本信息</strong></td>
        </tr>
        <tr>
          <td width="20%" nowrap="nowrap" class="title_item" align="right">分公司：</td>
          <td colspan="3">${af.map.map.dept_name}</td>
        </tr>
        <tr>
          <td width="20%" nowrap="nowrap" class="title_item" align="right">R3编码：</td>
          <td width="30%"><html-el:text property="r3_id" styleId="r3_id" size="20" maxlength="20" /></td>
          <td width="20%" nowrap="nowrap" class="title_item" align="right">R3送达方编码：</td>
          <td width="30%"><html-el:text property="r3_send_num" styleId="r3_send_num" size="15" maxlength="16" /></td>
        </tr>
        <tr>
          <td width="20%" nowrap="nowrap" class="title_item" align="right">R3专卖店名称：</td>
          <td width="30%"><html-el:text property="r3_name" styleId="r3_name" size="20" maxlength="30" /></td>
          <td width="20%" nowrap="nowrap" class="title_item" align="right">专卖店编号：</td>
          <td width="30%">${af.map.zmd_sn}</td>
        </tr>
        <tr>
          <td class="title_item" align="right" nowrap="nowrap"><span style="color:#FF0000;">[必填]</span>专卖店地址：</td>
          <td valign="middle" nowrap="nowrap" colspan="3"><select name="province" id="province" class="bd">
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
          <td class="title_item" align="right" nowrap="nowrap"><span style="color:#FF0000;">[必填]</span>详细地址：</td>
          <td nowrap="nowrap"><html-el:text property="addr" styleId="addr" size="40" maxlength="130" /></td>
          <td class="title_item" align="right" nowrap="nowrap">营业面积(平方米)：</td>
          <td nowrap="nowrap"><html-el:text property="busi_area" styleId="busi_area" size="15" maxlength="4" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">租赁期：</td>
          <td nowrap="nowrap"><c:choose>
              <c:when test="${empty af.map.rent_start or empty af.map.rent_end}"></c:when>
              <c:otherwise>
                <fmt:formatDate value="${af.map.rent_start}" pattern="yyyy-MM-dd" var="rent_start" />
              </c:otherwise>
            </c:choose>
            <html-el:text property="rent_start" styleId="rent_start" value="${rent_start}"  size="10" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;"  title="点击选择日期" />
            至
            <c:choose>
              <c:when test="${empty af.map.rent_start or empty af.map.rent_end}"></c:when>
              <c:otherwise>
                <fmt:formatDate value="${af.map.rent_end}" pattern="yyyy-MM-dd" var="rent_end" />
              </c:otherwise>
            </c:choose>
            <html-el:text property="rent_end" styleId="rent_end" value="${rent_end}"  size="10" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;"  title="点击选择日期" /></td>
          <td nowrap="nowrap" class="title_item" align="right">年租金（万元）：</td>
          <td nowrap="nowrap"><html-el:text property="rent_fee" styleId="rent_fee" size="15" maxlength="5" /></td>
        </tr>
        <tr>
          <td class="title_item" align="right"><span style="color:#FF0000;">[必填]</span>经营性质：</td>
          <td><html-el:select property="busi_type" styleId="busi_type" style="width:150px;">
              <html-el:option value="">==请选择==</html-el:option>
              <c:forEach var="cur" items="${baseTypesList10000}">
                <html-el:option value="${cur.type_id}">${fn:escapeXml(cur.type_name)}</html-el:option>
              </c:forEach>
            </html-el:select></td>
          <td class="title_item" align="right"><span style="color:#FF0000;">[必填]</span>经营模式：</td>
          <td><html-el:select property="busi_mod" styleId="busi_mod" style="width:150px;">
              <html-el:option value="">==请选择==</html-el:option>
              <c:forEach var="cur" items="${baseTypesList100000}">
                <html-el:option value="${cur.type_id}">${fn:escapeXml(cur.type_name)}</html-el:option>
              </c:forEach>
            </html-el:select></td>
        </tr>
        <tr>
          <td class="title_item" align="right">预计年销售(万元)：</td>
          <td colspan="3"><html-el:text property="money_of_sell_by_year_plan" styleId="money_of_sell_by_year_plan" size="20" maxlength="5" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">实际投入装修费用（万元）：</td>
          <td nowrap="nowrap"><html-el:text property="money_of_dcrt" styleId="money_of_dcrt" size="20" maxlength="5" /></td>
          <td nowrap="nowrap" class="title_item" align="right">专卖店押金（万元）：</td>
          <td nowrap="nowrap"><html-el:text property="money_of_deposit" styleId="money_of_deposit" size="15" maxlength="5" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">专卖店负责人：</td>
          <td nowrap="nowrap"><html-el:text property="host_name" styleId="host_name" size="20" maxlength="30" /></td>
          <td nowrap="nowrap" class="title_item" align="right">专卖店负责人电话：</td>
          <td nowrap="nowrap"><html-el:text property="host_phone" styleId="host_phone" size="15" maxlength="30" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">分公司负责人：</td>
          <td nowrap="nowrap"><html-el:text property="dept_leader_name" styleId="dept_leader_name" size="20" maxlength="30" /></td>
          <td nowrap="nowrap" class="title_item" align="right">分公司负责人电话：</td>
          <td nowrap="nowrap"><html-el:text property="dept_leader_phone" styleId="dept_leader_phone" size="15" maxlength="30" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">分公司总经理：</td>
          <td nowrap="nowrap"><html-el:text property="dept_main_man" styleId="dept_main_man" size="20" maxlength="20" /></td>
          <td nowrap="nowrap"  class="title_item" align="right">分公司财务经理：</td>
          <td nowrap="nowrap"><html-el:text property="dept_fnc_man" styleId="dept_fnc_man" size="15" maxlength="20" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">申请样机额度（万元）：</td>
          <td nowrap="nowrap"><html-el:text property="money_of_dm_apply" styleId="money_of_dm_apply" size="20" maxlength="10" /></td>
          <td nowrap="nowrap" class="title_item" align="right">计划投放专卖店样机：</td>
          <td nowrap="nowrap"><html-el:textarea property="put_dm_plan" styleId="put_dm_plan" rows="2" cols="13"/></td>
        </tr>
        <tr>
          <td class="title_item" align="right">申请建店费用(万元)：</td>
          <td colspan="3"><html-el:text property="money_of_dcrt_apply" styleId="money_of_dcrt_apply" size="20" maxlength="10" /></td>
          <!-- 
          <td colspan="2"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable4">
              <tr>
                <td colspan="2" class="title_item"><span style="color: red;">计划投放专卖店资源：（规格填写清楚、长、宽、高）</span></td>
              </tr>
              <c:forEach items="${konkaXxZmdResList}" var="cur">
                <tr>
                  <td width="30%">${cur.res_name}</td>
                  <td align="left"><c:if test="${!empty af.map.res_ids}">
                      <html-el:text property="res_pro_${cur.id}" styleId="res_pro_${cur.id}" value="${cur.res_pro}" style="width:70%;" maxlength="80" />
                    </c:if>
                    <c:if test="${empty af.map.res_ids}">
                      <html-el:text property="res_pro_${cur.res_id}" styleId="res_pro_${cur.res_id}" value="${cur.res_pro}" style="width:70%;" maxlength="80" />
                    </c:if></td>
                </tr>
              </c:forEach>
            </table></td>
             -->
        </tr>
        <!-- 
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">保留库存数量：</td>
          <td nowrap="nowrap"><html-el:text property="reserverd_stock" styleId="reserverd_stock" size="20" maxlength="5" /></td>
          <td nowrap="nowrap" class="title_item" align="right">营业状态：</td>
          <td nowrap="nowrap"><html-el:select property="is_open" styleId="is_open" style="width:100px;">
              <html-el:option value="">==请选择==</html-el:option>
              <c:forEach var="cur" items="${baseTypesList30000}">
                <html-el:option value="${cur.type_id}">${fn:escapeXml(cur.type_name)}</html-el:option>
              </c:forEach>
            </html-el:select></td>
        </tr>
         -->
          <tr>
        	 <td colspan="4" style="font-weight:900;"><strong class="fb">工程预算</strong></td>
        </tr>
        <tr>
        	<td colspan="4" style="padding-left: 5%" width="90%">
	        	 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2" id="categorys_td">
	              	<tr class="tabtt1" style="height: 28px;">
	              	  <td width="" align="center" nowrap="nowrap">工程项目</td>
	              	  <td width="10%" align="center" nowrap="nowrap">品名</td>
	              	  <td width="10%" align="center" nowrap="nowrap">规格</td>
	              	  <td width="10%" align="center" nowrap="nowrap">数量</td>
	              	  <td width="10%" align="center" nowrap="nowrap">单位</td>
	              	  <td width="12%" align="center" nowrap="nowrap">单价（元）</td>
	              	  <td width="12%" align="center" nowrap="nowrap">小计（元）</td>
	              	  <td width="5%" align="center"><img src="${ctx}/images/+.gif" name="imgCategoryAddTr" id="imgCategoryAddTr" style="vertical-align:middle; cursor: pointer;" title="再添加一个" /></td>
	              	</tr>
	              	<c:forEach items="${konkaXxZmdGcysList}" var="cur">
	              	  <tr>
	              	  	<td>
	              	  		<html-el:hidden property="item_name" value="${cur.item_name}" styleId="item_name_${cur.id}" />
	              	  		${cur.item_name}
	              	  	</td>
	              	  	<td><html-el:text property="pd_name" style="width:80px;" maxlength="30" styleId="pd_name_${cur.id}" value="${cur.pd_name}" /></td>
	              	  	<td><html-el:text property="model_name" style="width:80px;" maxlength="30" styleId="model_name_${cur.id}" value="${cur.model_name}" /></td>
	              	  	<td><html-el:text property="item_num" style="width:80px;" maxlength="16" styleId="item_num_${cur.id}" value="${cur.item_num}" onchange="javascript:setOnlyDouble(this);" onblur="sum_money(this);" /></td>
	              	  	<td><html-el:text property="unit" style="width:80px;" maxlength="10" styleId="unit_${cur.id}" value="${cur.unit}" /></td>
	              	  	<td><html-el:text property="price" style="width:80px;" maxlength="12" styleId="price_${cur.id}" value="${cur.price}" onchange="javascript:setOnlyDouble(this);" onblur="sum_money(this);" /></td>
	              	  	<td><html-el:hidden property="total" style="width:80px;"  styleId="total_${cur.id}" value="${cur.total}"/>
	              	  		<html-el:text property="total_value" style="width:80px;" styleId="total_value_${cur.id}" value="${cur.total}" disabled="true"/>
	              	  	</td>
	              	  	<td align="center"><img src="${ctx}/images/x.gif" style="vertical-align:middle; cursor: pointer;margin-left: 2px;" id="imgDelTr" title="删除"/></td>
	              	  </tr>
	              	</c:forEach>
	              	<tbody id="tbodyContent" class="rtable2">
		            </tbody>
	              	<tr id="clone_div" style="display:none;">
	              		<td>
	              	  	  <html-el:text property="item_name" styleId="item_name" />
	              	  	</td>
	              	  	<td><html-el:text property="pd_name" style="width:80px;" styleId="pd_name" maxlength="30" /></td>
	              	  	<td><html-el:text property="model_name" style="width:80px;" styleId="model_name"  maxlength="30" /></td>
	              	  	<td><html-el:text property="item_num" style="width:80px;" maxlength="16" styleId="item_num" onchange="javascript:setOnlyDouble(this);" onblur="sum_money(this);" /></td>
	              	  	<td><html-el:text property="unit" style="width:80px;" styleId="unit" maxlength="10" /></td>
	              	  	<td><html-el:text property="price" style="width:80px;" maxlength="12" styleId="price" onchange="javascript:setOnlyDouble(this);" onblur="sum_money(this);" /></td>
	              	  	<td>
	              	  	<html-el:hidden property="total" style="width:80px;" styleId="total"/>
	              	  	<html-el:text property="total_value" style="width:80px;" styleId="total_value" disabled="true" /></td>
	              	  	<td align="center"><img src="${ctx}/images/x.gif" style="vertical-align:middle; cursor: pointer;margin-left: 2px;" id="imgDelTr" title="删除"/></td>
	              	</tr>
	              	<tr>
	              		<td><span><strong>总计</strong></span></td>
	              		<td colspan="7"><span style="color: red;" id="total_money"></span></td>
	              	</tr>
	            </table>
        	</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">固定资产配置：</td>
          <td nowrap="nowrap" colspan="3"><html-el:textarea property="fixed_asset" styleId="fixed_asset" rows="4" cols="60" /></td>
        </tr>
        <tr>
            <td nowrap="nowrap" class="title_item">上传附件：</td>
            <td colspan="3"><div id="divFileHidden" style="display: none;">
                <input name="file_hidden" type="file" id="file_hidden" style="width: 250px;" />
                <img src="../../images/x.gif" style="vertical-align:middle; cursor: pointer;" id="imgDelTr" title="删除"/></div>
              <div id="divFile">
                <input name="file_show" type="file" id="file_show" style="width: 250px;" />
                <img src="../../images/+.gif" style="vertical-align:middle; cursor: pointer;" id="imgAddTr" title="再添加一个" /></div></td>
          </tr>
          <c:if var="is_atta_edit" test="${not empty attachmentList}">
            <tr>
              <td class="title_item">已上传的附件：</td>
              <td colspan="3"><ol>
                  <c:forEach var="cur" items="${attachmentList}" varStatus="vs">
                    <li><a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}">${cur.file_name}</a>&nbsp;&nbsp;&nbsp;<a href="#" id="att_del_${cur.id}">
                      <img src="../../images/x.gif" style="vertical-align:middle; cursor: pointer;" title="删除"/></a></li>
                  </c:forEach>
                </ol></td>
            </tr>
          </c:if>
        <tr>
        	<td colspan="4"><span style="color:red;">提示：文件上传可以上传的格式如下：doc,docx,ppt,pptx,rar,txt,xls,xlsx,zip,exe；图片的上传，可以直接打包成rar或者zip格式上传。</span></td>
        </tr>  
        <tr class="tr_type">
          <td colspan="4" style="font-weight:900;"><strong class="fb">返佣设置</strong></td>
        </tr>
        <tr class="tr_type">
          <td  align="left" colspan="4"><p style="color: red">承包专卖店盈利设计：常规零售返佣（8%）+ 高买部分返佣（高卖部分83%） + 工程机返佣（建议按照1%—3%）+ 规模激励（年零售总额的1%）</p></td>
        </tr>
        <tr class="tr_type">
          <td nowrap="nowrap" colspan="4"><table width="99%" border="0" cellspacing="0" cellpadding="0" class="rtable4">
              <c:forEach var="cur_08" items="${baseTypesList80000}" varStatus="vs">
                <tr>
                  <td width="15%" nowrap="nowrap" class="title_item" align="right">${cur_08.type_name}：</td>
                  <td width="85%"> 返佣比例（百分比）：
                    <c:if test="${list[vs.index] ne 1}">
                      <html-el:text property="reward_ratio_${cur_08.type_id}" styleId="reward_ratio_${cur_08.type_id}" onchange="javascript:setOnlyDouble(this);" />
                    </c:if>
                    <c:if test="${list[vs.index] eq 1}">
                      <html-el:text property="reward_ratio_${cur_08.type_id}" styleId="reward_ratio_${cur_08.type_id}" readonly="true"/>
                    </c:if>
                    &nbsp;
                    <html-el:checkbox property="is_enabled_${cur_08.type_id}" styleId="is_enabled_${cur_08.type_id}" />
                    启用
                    &nbsp;
                    <html-el:checkbox property="is_locked_${cur_08.type_id}"  styleId="is_locked_${cur_08.type_id}" />
                    锁定
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <c:if test="${list[vs.index] ne 1}">
                      <input class="but4" type="button" name="set_${cur_08.type_id}" value="设置" id="send_${cur_08.type_id}" />
                    </c:if>
                    <c:if test="${list[vs.index] eq 1}">
                      <input class="but5" type="button" name="locak_${cur_08.type_id}" value="解锁" id="locak_${cur_08.type_id}" />
                    </c:if></td>
                </tr>
              </c:forEach>
            </table></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" colspan="4	"><strong class="fb">设置专卖店仓库</strong>&nbsp;&nbsp;
            <html-el:checkbox property="check_all" styleId="check_all" />
            全选/反选</td>
        </tr>
        <tr>
          <td colspan="4"><ul id="fact_str">
              <c:forEach var="cur" items="${konkaXxStdStoreList}" varStatus="vs">
                <li>
                  <html-el:checkbox property="${cur.fac_sn}_${cur.store_sn}" styleId="${cur.fac_sn}_${cur.store_sn}" styleClass="zmd_values" />
                  &nbsp;${cur.fac_sn}
                  &nbsp;${cur.store_desc} </li>
              </c:forEach>
            </ul></td>
        </tr>
        <tr>
          <td nowrap="nowrap" colspan="4" align="right"> 拟制人:${af.map.write_man} </td>
        </tr>
        <tr>
          <td nowrap="nowrap" colspan="6" height="40"  align="center"><input class="but4" type="submit" name="submit" value="提交 " id="submit" />
            <input class="but3" type="reset"  value="重填 " id="reset" />
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" id="btn_back" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<!-- ****** Main Frame End ****** -->
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var acceptUploadFileExts = "7z,doc,docx,ppt,pptx,rar,txt,xls,xlsx,zip, exe";
	$("#file_show"   ).attr("dataType", "Filter" ).attr("msg", "您上传的文件格式不受支持，请上传正确格式的文本、音频、视频。").attr("require", "false").attr("accept", acceptUploadFileExts);
	$("#file_hidden" ).attr("dataType", "Filter" ).attr("msg", "您上传的文件格式不受支持，请上传正确格式的文本、音频、视频。").attr("require", "false").attr("accept", acceptUploadFileExts);
	
	$("#rent_start").datepicker();
	$("#rent_end").datepicker();

	$("#check_all").click(function(){
		if (this.checked) {
			$(".zmd_values").attr("checked", "checked");
		} else {
			$(".zmd_values").removeAttr("checked");
		}
	});

	$("#busi_mod").change(function(){
		if(this.value == 100100){
			$(".tr_type").hide();
			window.parent.resizeFrameHeight('mainFrame', 3);
		}else{
			$(".tr_type").show();
			window.parent.resizeFrameHeight('mainFrame', 3);
		}
	});

	if('${af.map.busi_mod}' == 100100){
		$(".tr_type").hide();
		window.parent.resizeFrameHeight('mainFrame', 3);
	}else{
		$(".tr_type").show();
		window.parent.resizeFrameHeight('mainFrame', 3);

	}
	
	if($("#busi_mod").val() == 100100){
		$(".tr_type").hide();
		window.parent.resizeFrameHeight('mainFrame', 3);
	}else{
		$(".tr_type").show();
		window.parent.resizeFrameHeight('mainFrame', 3);
	}
	
	if(null != '${af.map.edit_value}' && '${af.map.edit_value}' != "" && '${af.map.is_show}' != "2"){
		$("#addr").attr("disabled",true);
		//$("#busi_mod").attr("disabled",true);
		$("#arc_state").attr("disabled",true);
		$("#host_name").attr("disabled",true);
		$("#host_phone").attr("disabled",true);
		$("#money_of_dm_apply").attr("disabled",true);
		$("#money_of_dcrt_apply").attr("disabled",true);
	}
	
	var role_is_dept =  '${role_is_dept}';
	if(role_is_dept == 300){
		$("#check_all").attr("disabled","true");
		//$(".zmd_values").attr("disabled","true");
		}
	if(role_is_dept == 200){
		$("input[id^=locak_]").attr("disabled","true");
		$("input[id^=send_]").attr("disabled","true");
		}
	
	$("input[id^=send_]").click(function(){
	 	var	but= confirm("是否保存");
	 	
	 	if(but){ 	
		$("#tip_msg").remove();
		var $this = $(this);
		var type_id = this.id.replace("send_", "");
		var reward_ratio = $("#reward_ratio_" + type_id).val();

		if ($.trim(reward_ratio).length == 0) {
			$(this).after("<span id=\"tip_msg\" style=\"color:#F00;margin-left:5px;\">返佣比例不能为空！</span>");
			return false;
		}

		if($.trim(reward_ratio)>100){
			$(this).after("<span id=\"tip_msg\" style=\"color:#F00;margin-left:5px;\">返佣比例不能超过100%！</span>");
			return false;
		}
		var param = {
			type_id : type_id,
			reward_ratio : reward_ratio,
			is_enabled : $("#is_enabled_" + type_id)[0].checked ? 1 : 0,
			is_locked : $("#is_locked_" + type_id)[0].checked ? 1 : 0,
			zmd_id : "${af.map.zmd_id}"
		};
		
		$.get("${ctx}/manager/zmd/KonkaXxZmdRewardSet.do?method=ajaxSaveRewardType", param, function(ret_flag) {
			$("#tip_msg").remove();
			if ("1" == ret_flag) {
				$this.after("<span id=\"tip_msg\" style=\"color:green;margin-left:5px;\">恭喜您，保存成功！</span>");
				window.location.href="${ctx}/manager/zmd/KonkaXxZmd.do?method=edit&zmd_sn=${af.map.zmd_sn}&zmd_id=${af.map.zmd_id}";
			} else {
				$this.after("<span id=\"tip_msg\" style=\"color:#F00;margin-left:5px;\">很抱歉，服务器内部错误，请联系管理员！</span>");
			 }
		});
	 	}
	 	else{
		 	return false;
		}
	});


	$("input[id^=locak_]").click(function(){
	 	var	but= confirm("是否解锁");
	 	if(but){
		$("#tip_msg").remove();
		var $this = $(this);
		var type_id = this.id.replace("locak_", "");
		var param = {
			type_id : type_id,
			zmd_id : "${af.map.zmd_id}"
		};
		
		$.post("${ctx}/manager/zmd/KonkaXxZmdRewardSet.do?method=ajaxOpenRewardType", param, function(ret_flag) {
			$("#tip_msg").remove();
			if ("1" == ret_flag) {
				$this.after("<span id=\"tip_msg\" style=\"color:green;margin-left:5px;\">恭喜您，解锁成功！</span>");
				window.location.href="${ctx}/manager/zmd/KonkaXxZmd.do?method=edit&zmd_sn=${af.map.zmd_sn}&zmd_id=${af.map.zmd_id}";
				
			} else {
				$this.after("<span id=\"tip_msg\" style=\"color:#F00;margin-left:5px;\">很抱歉，服务器内部错误，请联系管理员！</span>");
			}
		});
	 	}
	 	else{
	 		return false;
		 	}
	});

    function checkedAll() {
    	var ids = document.getElementsById("fact_str");
    	for(i=0;i < ids.length;i++){
        	
        	}
   }
	
	$("#fixed_asset").change(function(){
		 if($.trim($("#fixed_asset").val()).length >200){
		     alert("固定资产配置最大输入长度不能超过200");
		     $(":submit").attr("disabled", "true");
		     return false ;
		}else{
			  $(":submit").removeAttr("disabled");
	    }
    });

	$("#addr").attr("datatype", "Require").attr("msg", "请输入专卖店详细地址");
	$("#r3_id").attr("dataType", "Require").attr("msg", "请填写R3编码！");
	$("#r3_name").attr("dataType", "Require").attr("msg", "请填写R3专卖店名称！");
	$("#put_dm_plan").attr("dataType", "Limit").attr("max", "400").attr("msg", "计划投放样机不能超过400个文字");
	
	  // 区域
	$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${province}","dataType":"Require","msg":"请选择省/直辖市/自治区！"});
	$("#city").attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${city}"});
	$("#country").attr({"subElement": "town","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${country}"});
	$("#town").attr({"defaultText": "-请选择乡/镇-", "defaultValue": "", "selectedValue": "${town}"});
	
	$("#province").cs_ext("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false,"p_index_join","${af.map.p_index_join}");
	$("#province").change();

	 $("#busi_area").focus(setOnlyNum);
	 $("#rent_fee").focus(setOnlyNum);
	 $("#host_phone").focus(setOnlyNum);
	 $("#money_of_dcrt_apply").focus(setOnlyNum);
	 $("#money_of_dm_apply").focus(setOnlyNum);
	 $("#money_of_sell_by_year_plan").focus(setOnlyNum);
	 $("#dept_leader_phone").focus(setOnlyNum);

	 var total_money_s = 0; //总计
		
     $("[id^=total_value_]").each(function(){
 		if($(this).val().length > 0){
 			total_money_s = total_money_s + parseFloat($(this).val());
 		}
 		$("#total_money").text(total_money_s.toFixed(2));	
 	});

 	//附件删除
 	$("a[id^='att_del_']").click(function() {
 	  	  var a = this;
 	  	   if(!confirm('确实要删除此附件？')) return;
 	  	    $.post("KonkaXxZmd.do", { method : "deleteFile", id : $(this).attr("id").replace(/att_del_/g, '')}, function(success) {
 	  	   if (success){alert("恭喜您，删除附件成功!");$(a).parent().remove();} else alert(" 很抱歉，删除附件出错!"); 
 	  	  });
 	});	
 	
 	//附件新增
 	 $("#imgAddTr").click(function (){
         $("#divFileHidden").clone().find("#file_hidden").attr("name", "file_" + new Date().getTime()).end().appendTo($("#divFile")).show();
         resizeFrameHeight();
         $("img[id='imgDelTr']").each(function(){
             $(this).click(function (){
                 $(this).parent().remove();

                 resizeFrameHeight();
             });
         });
     });

   	
$(".form_cust").submit(function(){
	 var s = $("#rent_start").val();
     var e = $("#rent_end").val();

     if(($.trim(e).length == 0 && $.trim(s).length > 0) || ($.trim(s).length == 0 && $.trim(e).length > 0)){
			alert("租凭期必须包含开始和结束时间，请重新填写！！");
			return false;
		}
     if($.trim(s).length > 0 && $.trim(e).length > 0){
			if (s > e) {
				alert("租赁期开始时间必须小于结束时间！");
				return false;
			}
    }
     
	var store_ids = "";
	$(".zmd_values").each(function (){
		if(this.checked)
			store_ids = store_ids + this.id + "#";
	});
	
	$("#store_ids").val(store_ids.substring(0, store_ids.length -1));
		var isSubmit = Validator.Validate(this, 3);

		if (isSubmit) {
			var zmd_ids = [];
			$(".zmd_values:checked").each(function (){
				zmd_ids[zmd_ids.length] = this.id.replace("zmd_id_", "");
			});
			
			
			if(store_ids.length == 0){
				alert("请选择仓位！");
				return false;
			} 
			$(":submit").attr("disabled", "true");
			$(":button").attr("disabled", "true");
			$(":reset").attr("disabled", "true");
		}
		return isSubmit;
	});

	//工程预算
	//start
	$("#imgCategoryAddTr").click(function (){   
		
	    var code = new Date().getTime();   //后缀
	    var div = $('#clone_div').clone(true);
	   
	    //加上验证
	    div.find("#item_name").attr("dataType", "Require").attr("msg", "请填写工程项目");
	    div.find("#price").attr("dataType", "Currency").attr("msg", "请正确填写金额");
	    div.find("#item_num").attr("dataType", "Currency").attr("msg", "请正确填写数量");
	
	    //给控件id加上标识  
	    div.find('#item_name').attr("id","item_name_"+code); //工程项目
	    div.find('#pd_name').attr("id","pd_name_"+code); //品名
	    div.find('#model_name').attr("id","model_name_"+code); //规格
	    div.find('#item_num').attr("id","item_num_"+code); //数量
	    div.find('#unit').attr("id","unit_"+code); //单位
	    div.find('#price').attr("id","price_"+code);//单价
	    div.find('#total').attr("id","total_"+code);//小计
	    div.find('#total_value').attr("id","total_value_"+code);
	    div.find('#imgDelTr').attr("onclick","sum_money();");
	    
	   div.attr('id','clone_div__'+code);
	   div.appendTo($("#tbodyContent")).show();
	   resizeFrameHeight();
	});

	//删除一个类别
	$("img[id='imgDelTr']").each(function(){
	    $(this).click(function (){
	        $(this).parent().parent().remove();
	        //sum_money(1);
			var total_money = 0; //总计
				
	        $("[id^=total_value_]").each(function(){
	    		if($(this).val().length > 0){
	    			total_money = total_money + parseFloat($(this).val());
	    		}
	    		$("#total_money").text(total_money.toFixed(2));	
	    	});
	        resizeFrameHeight();
	    });
	});
});

//计算总金额
function sum_money(value){
    var total_money = 0; //总计
    var date = (value.id).replace("item_num_","").replace("price_","");
    
    if($('#item_num_' + date).val().length > 0 && $('#price_' + date).val().length > 0){
		var total_price =  parseFloat($('#item_num_' + date).val() * parseFloat($('#price_' + date).val()));
		$("#total_" + date).val(total_price.toFixed(2));
		$("#total_value_" + date).val(total_price.toFixed(2));
    }

	$("[id^=total_value_]").each(function(){
		if($(this).val().length > 0){
			total_money = total_money + parseFloat($(this).val());
		}
		$("#total_money").text(total_money.toFixed(2));	
	});
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
		if(this.value.length == 0) this.value = "";
	});
	//this.text.selected;
}

function setOnlyNum(obj) {
	var v = obj.value.replace(/[^\d\.]/gi, '');
	if( v == 0 ){
		obj.value = '';
	} else {
		obj.value = v;
	}
}

function setOnlyDouble(obj) {
	var v = obj.value.replace(/[^\d+(\.\d+)?]/gi,'');
		obj.value=v;
}

function resizeFrameHeight(offset, min_height) {
	// frame id can be found in page /konka-wd/WebContent/manager/admin/Frames2/indexFrame.jsp, and search 'iframe' to get.
	$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
}
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
