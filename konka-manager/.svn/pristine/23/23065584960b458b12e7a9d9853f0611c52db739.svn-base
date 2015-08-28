package com.ebiz.mmt.web.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.ebiz.mmt.domain.ArticleImg;
import com.ebiz.mmt.domain.SysModule;

/**
 * @author Jin,QingHua
 * @version Build 2009.10
 */
public class StringHelper {

	// private static final Logger logger =
	// LoggerFactory.getLogger(StringHelper.class);

	public static String getTreeNodes(List<SysModule> sysModuleList) {
		StringBuffer sb = new StringBuffer();

		for (SysModule sysModule : sysModuleList) {
			String _mod_id = String.valueOf(sysModule.getMod_id());
			String _par_id = String.valueOf(sysModule.getPar_id());
			// /if ("0".equals(_par_id)) {
			// _par_id = "-1";
			// }
			String _text = StringUtils.replace(sysModule.getMod_name(), ":", "&#58;");
			if (StringUtils.isEmpty(_text)) {
				continue;
			}
			String _hint = _text;
			String _url = StringUtils.replace(sysModule.getMod_url(), ":", "&#58;");

			sb.append("\ntree.nodes[\"").append(_par_id).append("_").append(_mod_id).append("\"]=\"");
			sb.append("text:").append(_text).append(";");
			if (_hint.length() > 0) {
				sb.append("hint:").append(_hint).append(";");
			}
			if ((_url != null) && (_url.length() > 0)) {
				sb.append("url:").append(_url).append(";");
				sb.append("data:").append("mod_id=").append(_mod_id).append(";");
			} else {
				// sb.append("url:").append("Frames.do?method=main").append(";");
				// sb.append("target:_top;");
				// sb.append("url:javascript:nothing();method: expanding();");
				sb.append("url:javascript:nothing();");
			}

			sb.append("\";");
		}
		return sb.toString();
	}

	public static String getNaviString(List<SysModule> sysModuleList) {
		return getNaviString(sysModuleList, " &gt; ");
	}

	public static String getNaviString(List<SysModule> sysModuleList, String separator) {
		ArrayList<String> modNameList = new ArrayList<String>();
		for (SysModule sysModule : sysModuleList) {
			modNameList.add(sysModule.getMod_name());
		}
		return (StringUtils.join(modNameList, separator));
	}

	public static String getNaviStringFromSysModuleList(List<SysModule> sysModuleList, String separator) {
		ArrayList<String> arrayList = new ArrayList<String>();
		for (SysModule sysModule : sysModuleList) {
			arrayList.add(sysModule.getMod_name());
		}
		return (StringUtils.join(arrayList, separator));
	}


	public static String getPptJsonString(List<ArticleImg> newsInfoList, String imgProperty, String txtProperty,
			String lnkProperty, String linkURI) {
		return StringHelper.getPptJsonString(newsInfoList, imgProperty, txtProperty, lnkProperty, linkURI, null);
	}

	public static String getPptJsonString(List<ArticleImg> newsInfoList, String imgProperty, String txtProperty,
			String lnkProperty, String linkURI, String siteURI) {

		linkURI = StringUtils.isNotBlank(linkURI) ? linkURI : "news/view.tdb";
		siteURI = StringUtils.isNotBlank(siteURI) ? siteURI : "";

		linkURI = linkURI.indexOf("?") == -1 ? linkURI + "?id=" : linkURI + "&id=";

		StringBuffer buffer = new StringBuffer();
		for (ArticleImg o : newsInfoList) {
			String img = "";
			String txt = "";
			try {
				img = BeanUtils.getProperty(o, imgProperty);
				txt = BeanUtils.getProperty(o, txtProperty);
			} catch (Exception ex) {
			}
			buffer.append("{");
			buffer.append("img:'").append(siteURI).append(img).append("',");
			buffer.append("txt:'").append(StringUtils.replace(txt, "\'", "\\\'")).append("',");
			buffer.append("lnk:escape('").append(o.getImage_url()).append("')},");
		}
		buffer.append("{}");
		return buffer.toString();
	}

}
