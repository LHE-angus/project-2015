<?xml version="1.0" encoding="GBK"?>
	<chart palette='2' shownames='1' showvalues='1' decimals='2' sNumberSuffix='%' setAdaptiveSYMin='1' showPlotBorder='1' showBorder='0' bgColor='FFFFFF' formatNumberScale='${((formatNumberScale)!' ')?html}' numberSuffix='${((numberSuffix)!' ')?html}' >
	<categories>
     <#list baseChartList as cur>
	    <category label="${((cur.category_label)!' ')?html}" />
     </#list>
    </categories>
    <#list mSColumn3DChartChartList as cur>
      <dataset><dataset seriesName="${((cur.series_name)!' ')?html}" showValues="0" color='${((cur.color)!' ')?html}'>
        <#list cur.baseChartList as cur_base>
	      <set value="${((cur_base.value)!' ')?html}" />
        </#list>
      </dataset></dataset>
    </#list>
    <#list msLineChartList as cur>
      <lineSet seriesName="${((cur.series_name)!' ')?html}" showValues="1" color='${((cur.color)!' ')?html}' lineThickness='4'>
        <#list cur.baseChartList as cur_base>
	      <set value="${((cur_base.value)!' ')?html}" />
        </#list>
      </lineSet>
    </#list>
	</chart>
