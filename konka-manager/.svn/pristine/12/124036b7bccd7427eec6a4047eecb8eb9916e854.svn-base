<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
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
	<div class="oarcont">
		<div class="oartop">
			<table width="500" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="3%"><img
						src="${ctx}/styles/admin-index/images/k_tup.jpg" alt=""
						style="vertical-align: middle;" /></td>
					<td>当前位置：${naviString}</td>
				</tr>
			</table>
		</div>
		<div>
			<h3 align="center">
				<strong class="fb">R3订单明细</strong>
			</h3>
		</div>
		<div class="rtabcont2">
			<html-el:form action="/OrderTest" enctype="multipart/form-data">
				<html-el:hidden property="r3_order_header_id" styleId="id" />
				<html-el:hidden property="method" value="save" />
				<div style="height: 20px;">
					<font color="blue" style="font-weight: bold;">订单头信息</font>
				</div>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" >
					<tr>
						<td width="8%" align="left" class="td_bord">订单单号</td>
						<td width="12%"><html-el:text property="r3_order_no" styleId="r3_order_no" ></html-el:text></td>
						<td width="8%" align="left" class="td_bord">年份</td>
						<td width="12%"><html-el:text property="r3_order_year" value="2013"></html-el:text></td>
						<td width="8%" align="left" class="td_bord">客户编码</td>
						<td width="12%"><html-el:text property="customer_code" value="Customer0002"></html-el:text></td>
						<td width="8%" align="left" class="td_bord">客户名称</td>
						<td width="12%"><html-el:text property="customer_name" value="苏宁专卖"></html-el:text></td>
					</tr>

					<tr>
						<td width="8%" align="left" class="td_bord">R/3销售单号</td>
						<td width="12%"><html-el:text property="r3_order_sell_no" ></html-el:text></td>
						<td width="8%" align="left" class="td_bord">凭证年份</td>
						<td width="12%"><html-el:text property="certify_year" value="2013"></html-el:text></td>
						<td width="8%" align="left" class="td_bord">物料类型</td>
						<td width="12%"><html-el:text property="material_type" value="成品"></html-el:text></td>
						<td width="8%" align="left" class="td_bord">周转物料类型</td>
						<td width="12%"><html-el:text property="turnover_material_type" value="成品"></html-el:text></td>
					</tr>

					<tr>
						<td width="8%" align="left" class="td_bord">申请类型</td>
						<td width="12%"><html-el:text property="apply_type" value="A"></html-el:text></td>
						<td width="8%" align="left" class="td_bord">紧急程度</td>
						<td width="12%"><html-el:text property="emergency_level" value="A"></html-el:text></td>
						<td width="8%" align="left" class="td_bord">申请日期</td>
						<td width="12%"><html-el:text property="apply_type" value="2013-06-14"></html-el:text></td>
						<td width="8%" align="left" class="td_bord">要求发货日期</td>
						<td width="12%"><html-el:text property="ship_date" value="2013-06-15"></html-el:text></td>
					</tr>

					<tr>
						<td width="8%" align="left" class="td_bord">要求发货方式</td>
						<td width="12%"><html-el:text property="delivery_type" value="航空"></html-el:text></td>
						<td width="8%" align="left" class="td_bord">建议承运商</td>
						<td width="12%"><html-el:text property="suggest_carrier" value="顺丰快递"></html-el:text></td>
						<td width="8%" align="left" class="td_bord">制单人</td>
						<td width="12%"><html-el:text property="creator" value="周浩杰"></html-el:text></td>
						<td width="8%" align="left" class="td_bord">收货人</td>
						<td width="12%"><html-el:text property="recipient"  value="徐风"></html-el:text></td>
					</tr>

					<tr>
						<td width="8%" align="left" class="td_bord">收货人电话</td>
						<td width="12%"><html-el:text property="recipient_tel" value="18664201082"></html-el:text></td>
						<td width="8%" align="left" class="td_bord">总金额</td>
						<td width="12%"><html-el:text property="total_money" value="10000"></html-el:text></td>
						<td width="8%" colspan="1" align="left" class="td_bord">收货人地址</td>
						<td width="12%"><html-el:text property="recipient_address" value="广东深圳"></html-el:text></td>
					</tr>
					
					
					<tr>
						<td width="8%" align="left" class="td_bord">备注</td>
						<td width="12%"><html-el:text property="remark" value="好东西"></html-el:text></td>
					</tr>
					
					<tr>
					
					<td colspan="5"><font color="red">以下为R/3系统要求字段------------------------------------</font></td>
					</tr>
					<tr>
						<td width="8%" align="left" class="td_bord">销售凭证类型</td>
						<td width="12%"><select name="doc_type">
								<option value="ZFOR">ZFOR</option>
								<option value="ZFGC">ZFGC</option>
								<option value="ZFRE">ZFRE</option>
								<option value="ZFRC">ZFRC</option>
							</select></td>
						<td width="8%" align="left" class="td_bord">销售组织</td>
						<td width="12%"><html-el:text property="sales_org" value="KF47" readonly="true"></html-el:text></td>
						<td width="8%" colspan="1" align="left" class="td_bord">分销渠道</td>
						<td width="12%"><html-el:text property="distr_chan" value="10" readonly="true"></html-el:text></td>
						<td width="8%" align="left" class="td_bord">产品组</td>
						<td width="12%"><html-el:text property="division" value="10" readonly="true"></html-el:text></td>
					</tr>
					<tr>
						<td width="8%" align="left" class="td_bord">售达方</td>
						<td width="12%"><html-el:text property="ag" value="F14703001" readonly="true"></html-el:text></td>
						<td width="8%" colspan="1" align="left" class="td_bord">出具发票方</td>
						<td width="12%"><html-el:text property="re" value="F14703001" readonly="true"></html-el:text></td>
						<td width="8%" colspan="1" align="left" class="td_bord">付款方</td>
						<td width="12%"><html-el:text property="rg" value="F14703001" readonly="true"></html-el:text></td>
						<td width="8%" colspan="1" align="left" class="td_bord">送达方</td>
						<td width="12%"><html-el:text property="we" value="F14703001" readonly="true"></html-el:text></td>
					</tr>

				</table>
				<div style="height: 20px;">
					<font color="blue" style="font-weight: bold;">物料信息</font>
				</div>
				<!--  物料行信息 -->
				<div style="overflow: scroll; height: 250px " >
					
				<table width="80%" style="height:30px"  border="0" cellpadding="0" cellspacing="1" class="rtable2" id="item_table">
					<!-- 首行 -->
					<tr>
						<th width="2%" nowrap="nowrap" >序号</th>
						<th width="5%" nowrap="nowrap">物料编号</th>
<!-- 						<th width="5%" nowrap="nowrap">物料名称</th> -->
						<th width="5%" nowrap="nowrap">物料描述</th>
						<th width="5%" nowrap="nowrap">审批说明</th>
						<th width="5%" nowrap="nowrap">发货说明</th>
						<th width="5%" nowrap="nowrap">单位</th>
						<th width="5%" nowrap="nowrap">申请数量</th>
						<th width="5%" nowrap="nowrap">审批数量</th>
						<th width="5%" nowrap="nowrap">单价</th>
						<th width="5%" nowrap="nowrap">金额</th>
						
						<th width="5%" nowrap="nowrap">工厂</th>
						<th width="5%" nowrap="nowrap">销售凭证项目类别</th>
						<TH WIDTH="5%" NOWRAP="NOWRAP" >SKU分子</TH>
						<TH WIDTH="5%" NOWRAP="NOWRAP" >SKU值</TH>
						<th width="5%" nowrap="nowrap">装运点</th>
						
						
						<th>
							<img src="${ctx}/images/+.gif" name="imgAddTr" id="imgAddTr" style="vertical-align:middle; cursor: pointer;margin-left: 2px;" title="再添加一个(T)" />
						</th>
					</tr>
					
					<c:if test="${not empty entityLineList}">
					<c:forEach var="cur" items="${entityLineList}" varStatus="vs">
						<tr>
							<td align="center" nowrap="nowrap" id="item_index">${vs.count}</td>
							<td align="center" nowrap="nowrap">${cur.material_code}</td>
<%-- 							<td align="center" nowrap="nowrap">${cur.material_name}</td> --%>
							<td align="center" nowrap="nowrap">${cur.material_desc}</td>
							<td align="center" nowrap="nowrap">${cur.review_notes}</td>
							<td align="center" nowrap="nowrap">${cur.ship_desc}</td>
							<td align="center" nowrap="nowrap">${cur.unit_code}</td>
							<td align="center" nowrap="nowrap">${cur.apply_amount}</td>
							<td align="center" nowrap="nowrap">${cur.review_amount}</td>
							<td align="center" nowrap="nowrap">${cur.unit_price}</td>
							<td align="center" nowrap="nowrap">${cur.total_money}</td>
							
							<td align="center" nowrap="nowrap">${cur.plant}</td>
							<td align="center" nowrap="nowrap">${cur.item_categ}</td>
							<td align="center" nowrap="nowrap" >${cur.salqtynum}</td>
							<td align="center" nowrap="nowrap" >${cur.sqlqtyden}</td>
							<td align="center" nowrap="nowrap">${cur.ship_point}</td>
							
							
							<td align="center" nowrap="nowrap">
							<img src="${ctx}/images/x.gif" name="imgDelTr" id="imgDelTr"  style="vertical-align:middle; cursor: pointer;margin-left: 2px;" title="删除"/>
							</td>
						</tr>
					</c:forEach>
					</c:if>
					<!-- 空行 -->
					<c:if test="${empty entityLineList}">
						<tr id="item_tr">
							<td align="center" nowrap="nowrap" id="item_index">1</td>
							<td align="center" nowrap="nowrap"><html-el:text property="material_code"  readonly="true" value="LED32HS05"></html-el:text></td>
<%-- 							<td align="center" nowrap="nowrap"><html-el:text property="material_name"  readonly="true" value="32inch电视机"></html-el:text></td> --%>
							<td align="center" nowrap="nowrap"><html-el:text property="material_desc"  readonly="true" value="32inch电视机"></html-el:text></td>
							<td align="center" nowrap="nowrap"><html-el:text property="review_notes"  readonly="true" value="审批通过"></html-el:text></td>
							<td align="center" nowrap="nowrap"><html-el:text property="ship_desc"  readonly="true" value="可出货"></html-el:text></td>
							<td align="center" nowrap="nowrap"><html-el:text property="unit_code"  readonly="true" value="SET"></html-el:text></td>
							<td align="center" nowrap="nowrap"><html-el:text property="apply_amount"   readonly="true" value="2"></html-el:text></td>
							<td align="center" nowrap="nowrap"><html-el:text property="review_amount"  readonly="false"  value="1"></html-el:text></td>
							<td align="center" nowrap="nowrap"><html-el:text property="unit_price"  readonly="true" value="1"></html-el:text></td>
							<td align="center" nowrap="nowrap"><html-el:text property="total_money2"  readonly="true" value="1"></html-el:text></td>
							<td align="center" nowrap="nowrap"><html-el:text property="plant"  readonly="true" value="KF47"></html-el:text></td>
							<td align="center" nowrap="nowrap"><html-el:text property="item_categ"  readonly="true" value="ZFTN"></html-el:text></td>
							<td align="center" nowrap="nowrap" ><html-el:text property="salqtynum"  readonly="true" value="1"></html-el:text></td>
							<td align="center" nowrap="nowrap" ><html-el:text property="sqlqtyden"  readonly="true" value="1"></html-el:text></td>
							<td align="center" nowrap="nowrap"><html-el:text property="ship_point"  readonly="true" value="KF47"></html-el:text></td>
							
							<td width="30"  align="center">
							<img src="${ctx}/images/x.gif" name="imgDelTr" id="imgDelTr"  style="vertical-align:middle; cursor: pointer;margin-left: 2px;" title="删除"/>
							</td>
						</tr>
					</c:if>
				</table>
					
			</div>		
			
			<div align="center">
				<label> 
				<input class="but5" type="button" value="返回" onclick="history.back();" />
				</label>
				<span></span>
				<input class="but4" type="submit" name="Submit4" value="提交" id="btn_submit"/>
			</div>
			
			</html-el:form>
		</div>
		<div class="clear"></div>
	</div>
	
<script type="text/javascript" src="../commons/scripts/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		var order_no = new Date().getTime();
		
		$("#r3_order_no").val(order_no)  ;// $("#r3_order_no").attr("value",order_no);
		
		$("#btn_submit").click(function(){
				this.form.submit();
		});
		
		$("#imgAddTr").click(function(){
			var clone_tr = $("#item_tr").clone(true);
			clone_tr.appendTo($("#item_table")).show();
			//更改序列号
			changeIndex();
		});
		
		//
		$("img[id='imgDelTr']").each(function(){
	        $(this).click(function (){
            	$(this).parent().parent().remove();
	            changeIndex();
	        });
	    });
		
	});
	
	
	//更改序列号
	function changeIndex(){
		var n = 1;
		$("#item_index","#item_table").each(function(){
		    $(this).text(n++);
		});
	}
	
	
</script>
</body>
</html>
