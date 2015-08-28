<style type="text/css">
.order-link a {font-size:12px;color:#000;text-decoration:underline;}
.order-link a:visited {font-size:12px;color:#000;text-decoration:underline;}
.order-link a:hover {font-size:12px;color:#FFB90F;text-decoration:underline;}
</style>
<div style="width:100%">
	<div>经总部审批备案，本次新增城市专卖店样机额度<span style="font-weight:700;font-size:16px;color:#F00;font-style:italic;">${yj_ed?string.currency}</span>万元&nbsp;，具体明细如下:</div>
	<div>
		<table align="center" width="80%" border="1" align="center" cellpadding="3" cellspacing="0">
			<tr>
          	<td align="center" nowrap="nowrap"><strong>分公司</strong></td>
          	<td align="center" nowrap="nowrap"><strong>专卖店编号</strong></td>
          	<td align="center" nowrap="nowrap"><strong>专卖店店长</strong></td>
          	<td align="center" nowrap="nowrap"><strong>联系电话</strong></td>
          	<td align="center" nowrap="nowrap"><strong>专卖店地址</strong></td>
          	<td align="center" nowrap="nowrap"><strong>经营性质</strong></td>
          	<td align="center" nowrap="nowrap"><strong>样机额度（万元）</strong></td>
        	</tr>
			<tr>
				<td align="left">${((dept_name)!" ")?html}</td>
				<td align="left">${((zmd_sn)!" ")?html}</td>
				<td align="left">${((ku_name)!" ")?html}</td>
				<td align="left">${((ku_tel)!" ")?html}</td>
				<td align="left">${((p_name)!" ")?html}</td>
				<td align="center">${((busi_name)!" ")?html}</td>
				<td align="right">${((yj_ed)!" ")?html}</td>
			</tr>
		</table>
	<div>
	<div align="right" width="60">${(date)?string("yyyy年MM月dd日")}</div>
	<div align="right" width="60">${send_name}</div>
<div>