/**
 * move options from a select to another select
 * @param object targetSelect as html element select
 * @param object targetSelect as html element select
 * @version build 20051018
 * @author jinqinghua@gmail.com
 */
function moveSelected(sourceSelect, targetSelect){
	var cachOptionsArray = new Array();
	var index = 0;
	for (var i = sourceSelect.options.length - 1; i >= 0; i--){
		if (sourceSelect.options[i].selected){
			cachOptionsArray[index] = new Option(sourceSelect.options[i].text, sourceSelect.options[i].value);
			sourceSelect.options[i] = null;
			index++;
		}
	}
	var exist = false;
	for (var i = cachOptionsArray.length - 1; i >= 0; i--){
		exist = false;
		for (var j = 0; j < targetSelect.options.length; j++){
			if (targetSelect.options[j].value.toString() == cachOptionsArray[i].value.toString()){
				//alert("��" + cachOptionsArray[i].text + "���Ѿ������ˣ�");
				//sourceSelect.options[sourceSelect.options.length] = cachOptionsArray[i];
				exist = true; 
				break;
			}
		}
		if (!exist){
			targetSelect.options[targetSelect.options.length] = cachOptionsArray[i];
		}
	}
}

/**
 * Select All Options
 * @param object selectElement as html element select
 * @version build 20051029
 * @author jinqinghua@gmail.com
 */
function selectAll(selectElement){
	for (var i = 0; i < selectElement.length; i++){
		selectElement.options[i].selected = true;
	}
}
/**
 * analytics 中点击搜索出现加载图片
 * @version 2010-10-09
 * @author Wu,Yang
 */
$("#submit_id").click(function(){
	$("#loading").show();
	this.form.submit();
});

function toNeedUrlWithShowLoadGIf(url, mod_id) {
	$("#loading").show();
	location.href = url + "mdas_mod_id=" + mod_id;
}
