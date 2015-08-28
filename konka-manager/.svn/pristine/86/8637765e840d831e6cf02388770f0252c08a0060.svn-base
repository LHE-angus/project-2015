/**
 * 通过接口取买卖提物流库存数量
 * version 2011-05-31
 * author Chen,LiDe
 */
function getMmtWLStorePdNum(goods_id) {
	$.ajax({
		type: "POST",
		cache: false,
		url: "../../cs.do",
		data: "method=getMmtWLStorePdNum&goods_id=" + goods_id,
		dataType: "json",
		error: function(request, settings) {},
		success: function(storePdNum) {
			if(storePdNum != -2 && storePdNum != -1){
				$("#goods_id_" + goods_id).append("<span>" + storePdNum + "</span>");
			} else {
				$("#goods_id_" + goods_id).append("<span>-</span>");
			}
		}
	});
}

/**
 * 通过接口取买卖提物流库存数量（网点端页面）
 * version 2011-05-31
 * author Chen,LiDe
 */
function getMmtWLStorePdNum4WdShop(goods_id, controlId4Val, controlId4Display) {
	$.ajax({
		type: "POST",
		cache: false,
		url: "../../cs.do",
		data: "method=getMmtWLStorePdNum&goods_id=" + goods_id,
		dataType: "json",
		error: function(request, settings) {},
		success: function(storePdNum) {
			if(storePdNum != -2 && storePdNum != -1){
				if (controlId4Val != null) {
					$("#" + controlId4Val).val(storePdNum);
				}
				if (controlId4Display != null) {
					$("#" + controlId4Display).append(storePdNum);
				}
			} else {
				if (controlId4Val != null) {
					$("#" + controlId4Val).val("0");
				}
				if (controlId4Display != null) {
					$("#" + controlId4Display).append("<span>0</span>");
				}
			}
		}
	});
}

function getMmtWLStorePdInfoList(goods_id) {
	$.ajax({
		type: "POST",
		cache: false,
		url: "../../cs.do",
		data: "method=getMmtWLStorePdInfoList&goods_id=" + goods_id,
		dataType: "html",
		error: function(request, settings) {},
		success: function(storePdNum) {
			$("#" + pd_id).append(storePdNum);
		}
	});
}