<?xml version="1.0" encoding="GBK"?>
<chart 
	exportFileName="${((exportFileName)!' ')?html}" 
	exportEnabled="1" 
	exportAction="download" 
	exportAtClient="0" 
	exportHandler="${((ctx)!' ')?html}/FusionchartExporter.do" 
	caption="${((caption)!' ')?html}" 
	subcaption="${((subcaption)!' ')?html}" 
	palette="2" 
	animation="1" 
	decimals="${((decimals)!' ')?html}"  
	numberPrefix="${((numberPrefix)!' ')?html}" 
	numberSuffix="${((numberSuffix)!' ')?html}" 
	showBorder="${((showBorder)!' ')?html}" 
	showValues="1"
	formatNumberScale="${((formatNumberScale)!' ')?html}" 
	showLegend="1"
	bgColor="F3f3f3"
	bgAlpha="70"
	canvasBgColor="FFFFFF"
	showAlternateHGridColor="1"
	alternateHGridColor="f8f8f8" 
	alternateHGridAlpha="60">
	
  	<#list baseChartList as cur>
		  <set value="${((cur.value)!' ')?html}" label="${((cur.label)!' ')?html}" isSliced="${((cur.is_sliced)!' ')?html}" />
	</#list>
	
	<styles>
		<definition>
			<style type="font" name="CaptionFont" color="666666" size="15" />
			<style type="font" name="SubCaptionFont" bold="0" />
		</definition>
		<application>
			<apply toObject="caption" styles="CaptionFont" />
			<apply toObject="SubCaption" styles="SubCaptionFont" />
		</application>
	</styles>
</chart>
