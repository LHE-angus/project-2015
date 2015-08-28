
/**
 * jQuery Pager [run any web server]
 *
 * @param url: required, ajax request url
 * @param data: nonrequired, ajax request json data, pattern: {prop1 : value1, prop2 : value2,...}
 * @param options: nonrequired, speicfic user config, if existed, data couldn't be none, more param defined as below:
 *		requestPageSizeAttr : request param property of item count per page,default value is 'pageSize'.
 *		requestPageFirstRowAttr : request param property of first row serial num per page,default value is 'firstRow'.
 *		lang : language of pager, support for zh_cn, en.
 *		currentPage : default loading page serilal num.
 *		currentPagePoint : current page point site among displayed page numbers.
 *		i18n : language defined by userself
 *		pageSize : displayed data item number per page.
 * @param callback : callback function execute after success, params as below:
 *		args0 : data, array, pattern: [{p1 : v1, p2 , v2,...},..,..].
 *		args1 : first row serial number in current page.
 *		args2 : data item number in current page.
 * @return jQuery
 *
 * EX Demo :
 *		html :  <div id="listWrap"></div>
 *				<div id="pagerControl"></div>
 *		js : 
 *				$("#pagerControl").pager("http://xxx/xxx/list.jsp");
 *			or	$("#pagerControl").pager("http://xxx/xxx/list.jsp?prop1=val1&...");
 *			or	$("#pagerControl").pager("http://xxx/xxx/list.jsp?prop1=val1&...", function(ret, firstSn) {
 *					var listContainer = $("#listWrap").empty();
 *					for(var i = 0; i < ret.length; i++) {
 *						listContainer.append(( firstSn + i) + " : " + ret[i].id).append("<br/>");
 *					}
 *				});
 *			or	$("#pagerControl").pager("http://xxx/xxx/list.jsp", {'prop1' : 'val1',...}, function(ret, firstSn, pageSize) {
 *					...
 *				});
 *			or	$("#pagerControl").pager("http://xxx/xxx/list.jsp", {'prop1' : 'val1',...}, {lang : 'en', pageSize : '20'}, function(ret, firstSn, pageSize) {
 *					...
 *				});
 *
 * @version 1.0
 * @date 2010-05-16
 * @author Xing,XiuDong
 * @author Cheng,Bing 2011.12.26 修改
 */
jQuery.fn.extend({
	pager : function(url, data, options, callback) {
		if (jQuery.isFunction(data)) {
			return this.pager(url, null, null, data);
		} else if (jQuery.isFunction(options)){
			return this.pager(url, data, null, options);
		}
		
		var $this = this.extend(jQuery.extend({
			counts : 0,	// total item number
			pageSize : 10, // displayed data item number per page.
			currentPage : 1, // current page number
			firstRow : 1,
			firstPage : 1,
			lastPage : 0,
			prevPage : 0,
			nextPage : 0,
			pagesCount : 0, // count of pages
			showPagesCount : 10,
			currentPagePoint : 1 / 3,
			requestPageSizeAttr : 'pageSize',
			requestPageFirstRowAttr : 'firstRow',
			use_defined_flg : false,
			use_defined_type : 0,
			lang : 'zh_cn',
			i18n : {
				'zh_cn' : { firstPage : '\u9996\u9875', lastPage : '\u5c3e\u9875', prevPage : '\u4e0a\u4e00\u9875', nextPage : '\u4e0b\u4e00\u9875' },
				'en' 	: { firstPage : 'FristPage', lastPage : 'LastPage', prevPage : 'PrevPage', nextPage : 'NextPage' }
			},
			cb_page:{
				'zh_cn' : { total_rec_b : '\u5171', total_rec_a : '\u6761\u8bb0\u5f55', page_per_b : '\u6bcf\u9875\u663e\u793a', page_per_a : '\u6761' ,page_total_b : '\u5171', page_total_a : '\u9875' ,page_skip_b: '\u7b2c' ,page_skip_a: '\u9875'},
				'en' 	: { total_rec_b : 'Total',total_rec_a:'', page_per_b : 'Page',page_per_a:'', page_total_b : '',page_total_a:'',page_skip_b: '' ,page_skip_a: ''}
			}
		}, options));
		
		
		$this.extend({
			loadPage : function(pn) { return $this.setCurrentPage(pn).loadData(); },
			loadData : function() {
				if (null != url) {
					var requestUrl = url + (url.indexOf("?") < 0 ? "?" : (url[length - 1] == '&' ? '' : '&'));
					requestUrl += $this.requestPageSizeAttr + "=" + $this.pageSize + "&";
					requestUrl += $this.requestPageFirstRowAttr + "=" + $this.firstRow + "&";
					
					// if (data) requestUrl += jQuery.param(data);
					// alert("request url: " + requestUrl);
					
					jQuery.ajax({
						type: "POST",
						url: requestUrl,
						data: data,
						dataType: "json",
						error: function(XMLHttpRequest, textStatus, errorThrown) { alert("Ajax text status : " + textStatus + "\nAjax error thrown : " + errorThrown); },
						success: function(json) {
							$this.setCounts(json.count).setCurrentPage($this.currentPage).drawControlPanel();
							
							// callback
							callback(json, $this.firstRow, $this.pageSize);
						}
					});
				}
				return $this;
			},
			setCounts : function(counts) {
				$this.counts = parseInt(counts) || $this.counts;
				if($this.counts <= $this.pageSize) $this.pagesCount = $this.lastPage = 1;
				else $this.pagesCount = $this.lastPage = Math.floor($this.counts / $this.pageSize) + 1;
				return $this;
			},
			setCurrentPage : function(pn) {
				$this.currentPage = parseInt(pn);
				
				//if ($this.currentPage < $this.firstPage) $this.currentPage = $this.firstPage;
				//else if ($this.currentPage > $this.lastPage) $this.currentPage = $this.lastPage;
				
				$this.prevPage = $this.currentPage - 1;
				$this.nextPage = $this.currentPage + 1;
				
				if ($this.nextPage > $this.lastPage && $this.lastPage > $this.firstPage) $this.nextPage = $this.lastPage;
				if ($this.prevPage < $this.firstPage && $this.lastPage > $this.firstPage) $this.prevPage = $this.firstPage;
				
				// alert("curPage : " + $this.currentPage + ", firstPage : " + $this.firstPage + ", lastPage : " + $this.lastPage + ", pageSize : " + $this.pageSize);
				
				$this.firstRow = $this.pageSize * ($this.currentPage - 1) + 1;
				return $this;
			},
			drawControlPanel : function() {
				$this.empty();

				var delimeter = " ";
				var d = Math.floor($this.showPagesCount * $this.currentPagePoint);
				var firstPN = $this.currentPage - d;
				firstPN = (firstPN < $this.lastPage - $this.showPagesCount) ? firstPN : ($this.lastPage - $this.showPagesCount + 1);
				firstPN = firstPN < 1 ? 1 : firstPN;

				/* 新增页数统计*/
				if($this.use_defined_flg){
					var udf = $this.cb_page[$this.lang];
					switch($this.use_defined_type){		
					    case 3:$this.append(udf.total_rec_b+"<span style=\"color:#FF0000\"> " + $this.counts + " </span>"+udf.total_rec_a).append(delimeter);
					    case 2:$this.append(udf.page_per_b+"<span style=\"color:#FF0000\"> " + $this.pageSize + " </span>"+udf.page_per_a).append(delimeter);
					    case 1:$this.append(udf.page_total_b+"<span style=\"color:#FF0000\"> " + $this.pagesCount + " </span>"+udf.page_total_a).append(delimeter);
					    case 0:$this.append(udf.page_skip_b+"<span style=\"color:#FF0000\"> " + $this.currentPage + " </span>"+udf.page_skip_a).append(delimeter);
					    default:break;
					}			
				}

				if (firstPN > 1) {
					$this.append($("<a href='javascript:void(0);'>" + $this.i18n[$this.lang].firstPage + "</a>").attr("pn", $this.firstPage).click(function() {
						$this.loadPage($(this).attr("pn"));
					})).append(delimeter);
				}
				
				if ($this.currentPage > $this.firstPage) {
					$this.append($("<a href='javascript:void(0);'>" + $this.i18n[$this.lang].prevPage + "</a>").attr("pn", $this.prevPage).click(function() {
						$this.loadPage($(this).attr("pn"));
					})).append(delimeter);
				}
				
				for (var i = firstPN; i < firstPN + $this.showPagesCount && i <= $this.lastPage; i++) {
					$this.append(i != $this.currentPage ? $("<a href='javascript:void(0);'>" + i + "</a>").attr("pn", i).click(function() {
						$this.loadPage($(this).attr("pn"));
					}) : i).append(delimeter);
				}
				
				if ($this.currentPage < $this.lastPage) {
					$this.append($("<a href='javascript:void(0);'>" + $this.i18n[$this.lang].nextPage + "</a>").attr("pn", $this.nextPage).click(function() {
						$this.loadPage($(this).attr("pn"));
					})).append(delimeter);
				}
				
				if ($this.currentPage < $this.lastPage) {
					$this.append($("<a href='javascript:void(0);'>" + $this.i18n[$this.lang].lastPage + "</a>").attr("pn", $this.lastPage).click(function() {
						$this.loadPage($(this).attr("pn"));
					})).append(delimeter);
				}
				return $this;
			}
		});

		return $this.loadPage($this.currentPage);
	}
});