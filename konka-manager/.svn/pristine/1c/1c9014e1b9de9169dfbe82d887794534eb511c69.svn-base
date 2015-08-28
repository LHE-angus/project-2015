/*
 * 获取静态页面传递过来的参数
 * 
 * @author Hui,Gang
 * @version Build 2012-05-11
 * @useage 1.在页面引入该脚本 2.通过 _paras.参数名称 来获取具体的参数值，如_paras.id
 * 
 */
 (function(){
    window._paras = new Object();
    var query = location.search.substring(1);      // Get query string
    var pairs = query.split("&");                  // Break at ampersand
    for (var i = 0; i < pairs.length; i++) {
        var pos = pairs[i].indexOf('=');           // Look for "name=value"
        if (pos == -1) continue;                   // If not found, skip
        var argname = pairs[i].substring(0, pos);  // Extract the name
        var value = pairs[i].substring(pos + 1);   // Extract the value
        value = decodeURIComponent(value);         // Decode it, if needed
        _paras[argname] = value;                     // Store as a property
    }
})(window);