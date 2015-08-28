<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="MSThemeCompatible" content="no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=7"/>
    <title>${naviString}</title>
    <style type="text/css">
        .tabtt1 td {
            background-color: #eff;
        }
    </style>
    <style type="text/css">
        select {
            font-family: Microsoft YAHEI;
            font-size: 12px;
        }

        input {
            font-family: Microsoft YAHEI;
            font-size: 12px;
        }

        label {
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="oarcont" id="body_oarcont">
    <div class="rtabcont1">
        <table width="100%" border="1" cellpadding="0" cellspacing="1" class="tableClass">
            <tr class="tabtt1">
                <td width="5%" align="center" nowrap="nowrap">序号</td>
                <td align="center" nowrap="nowrap">分公司</td>
                <td align="center" nowrap="nowrap">经办</td>
                <td align="center" nowrap="nowrap">客户名称</td>
                <td align="center" nowrap="nowrap">R3编码</td>
                <td align="center" nowrap="nowrap">渠道类型</td>
                <td align="center" nowrap="nowrap">拆分类型</td>
                <c:if test="${af.map.store_flag eq 1}">

                    <td align="center" nowrap="nowrap">仓库</td>
                </c:if>
                <td align="center" nowrap="nowrap">型号</td>
                <td align="center" nowrap="nowrap">最近30天</td>
                <td align="center" nowrap="nowrap">最近7天</td>
                <td align="center" nowrap="nowrap">盘点后数量</td>
                <td align="center" nowrap="nowrap">盘点后金额</td>
                <td align="center" nowrap="nowrap">最后盘点时间</td>
                <td align="center" nowrap="nowrap">盘点类型</td>
            </tr>
            <c:forEach var="cur" items="${entityList}" varStatus="vs">
                <tr>
                    <td align="center"
                        nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
                    <td align="center" nowrap="nowrap">${cur.map.fgs_name}</td>
                    <td align="center" nowrap="nowrap">${cur.map.jb_name}</td>
                    <td align="center" nowrap="nowrap">${cur.map.customer_name}</td>
                    <td align="center" nowrap="nowrap">${cur.map.r3_code}</td>
                    <td align="center" nowrap="nowrap">${cur.map.par_customer_type_name}</td>
                    <td align="center" nowrap="nowrap">${cur.map.customer_type_name}</td>
                    <c:if test="${af.map.store_flag eq 1}">
                        <td align="center" nowrap="nowrap">${cur.map.store_name}</td>
                    </c:if>
                    <td align="center" nowrap="nowrap">${cur.map.goods_name}</td>
                    <td align="center" nowrap="nowrap">0</td>
                    <td align="center" nowrap="nowrap">0</td>
                    <td align="right" nowrap="nowrap">${cur.ver_stocks}</td>
                    <td align="right" nowrap="nowrap">
                        <font color="red">
                            <fmt:formatNumber pattern="0.00" value="${cur.ver_money}"/>
                        </font></td>
                    <td align="center" nowrap="nowrap">
                        <fmt:formatDate value="${cur.opr_date}" />
                    </td>
                    <td align="center" nowrap="nowrap">
                        <c:if test="${cur.map.opr_type eq 1}"><font color="green">人工盘点</font></c:if>
                        <c:if test="${cur.map.opr_type ne 1}"><font color="orange">系统盘点</font></c:if>
                    </td>
                     </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>