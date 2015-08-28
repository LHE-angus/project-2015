<?xml version="1.0" encoding="GBK"?>
    <chart palette="2" caption="${((caption)!' ')?html}" subcaption="${((unit)!' ')?html}" shownames="1" showvalues="1" decimals="2" numberPrefix="${((moneyUnit)!' ')?html}" formatNumberScale="0" useRoundEdges="1" legendBorderAlpha="0" baseFontColor="666666" baseFont="${((baseFont)!' ')?html}" BaseFontSize ="12" showBorder="1" bgSWFAlpha="0" canvasBgAlpha="0">
    <categories>
     <#list baseChartList as cur>
	    <category label="${((cur.category_label)!' ')?html}" />
     </#list>
    </categories>
    <#list mSColumn3DChartChartList as cur>
      <dataset seriesName="${((cur.series_name)!' ')?html}" showValues="1" color='${((cur.color)!' ')?html}'>
        <#list cur.baseChartList as cur_base>
	      <set value="${((cur_base.value)!' ')?html}" />
        </#list>
      </dataset>
    </#list>
    </chart>
