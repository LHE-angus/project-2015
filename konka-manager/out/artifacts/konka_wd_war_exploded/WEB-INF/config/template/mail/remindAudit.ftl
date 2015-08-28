<style type="text/css">
.order-link a {font-size:12px;color:#000;text-decoration:underline;}
.order-link a:visited {font-size:12px;color:#000;text-decoration:underline;}
.order-link a:hover {font-size:12px;color:#FFB90F;text-decoration:underline;}
</style>
<div style="width:800px">
	<div>尊敬的&nbsp;<span style="font-size:16px;font-style:italic;" >${((user_name)!" ")?html}</span>,您好！</div>
	<div><span style="font-weight:700;font-size:16px;color:#F00;font-style:italic;">${entity.zmd_sn}</span>&nbsp;专卖店订单&nbsp;<span style="text-decoration:underline;color:#69F">${sell_bill_id}</span>已提交&nbsp;(开单时间：${entity.add_date?string("yyyy-MM-dd HH:mm:ss")})，请您尽快审核！</div>
	<div>
		<div>订单信息(总金额：<span style="font-size:14px;font-weight:700;font-family:verdana;color:#C00;">${entity.total_money?string.currency}</span>)</div>
	</div>
	<div>
		<table align="center" width="80%" border="1" align="center" cellpadding="3" cellspacing="0">
			<tr>
          	<td align="center" nowrap="nowrap"><strong>产品型号</strong></td>
          	<td align="center" nowrap="nowrap"><strong>产品品类</strong></td>
          	<td align="center" nowrap="nowrap"><strong>类型</strong></td>
          	<td align="center" nowrap="nowrap"><strong>数量</strong></td>
          	<td align="center" nowrap="nowrap"><strong>单价</strong></td>
        	</tr>
			<#list entity.konkaXxSellBillDetailList as cur>
				<tr>
					<td align="left">${((cur.md_name)!" ")?html}</td>
					<td align="center">${((cur.pd_cls_name)!" ")?html}</td>
					<td align="center">
						<#if cur.pd_type?? && typeList??>
							<#list typeList as t_cur>
								<#if t_cur.type_id == cur.pd_type>
									${((t_cur.type_name)!" ")?html}
								</#if>
							</#list>
						</#if>
					</td>
					<td align="center">${cur.counts?string.number}</td>
					<td align="right">${cur.price?string.currency}</td>
				</tr>
			</#list>        
		</table>
	<div>
	<div class="order-link" style="width:100%;word-break:break-all;">链接：<a href="${link}">${link_view}</a></div>
	<div align="right" width="60">${(date)?string("yyyy年MM月dd日")}</div>
	<div align="right" width="60">康佳新兴渠道${dept_name}分公司</div>
<div>