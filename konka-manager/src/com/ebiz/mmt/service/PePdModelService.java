package com.ebiz.mmt.service;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.r3.MARA;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-21 09:45:12
 */
public interface PePdModelService {

	Long createPePdModel(PePdModel t);

	int modifyPePdModel(PePdModel t);

	int removePePdModel(PePdModel t);

	PePdModel getPePdModel(PePdModel t);

	List<PePdModel> getPePdModelList(PePdModel t);
	
	Long getPePdModelCount(PePdModel t);

	List<PePdModel> getPePdModelPaginatedList(PePdModel t);

	/**
	 * @author Gao,YongXiang
	 * @version 2011-09-22
	 */
	List<PePdModel> getPePdModulePaginatedIncludeMdNameList(PePdModel t);

	/**
	 * @author Gao,YongXiang
	 * @version 2011-09-22
	 */
	List<PePdModel> getPePdModelForB2bList(PePdModel t);

	/**
	 * @author Gao,YongXiang
	 * @version 2011-09-22
	 */
	List<PePdModel> getPePdModelForB2bWithNameList(PePdModel t);
	
	/**
	 * @author Li,Ka
	 * @version 2011-12-01
	 * 去康佳品牌的型号带大类名称
	 */
	List<PePdModel> getPePdModelWithClsNameBrandNameList(PePdModel t);
	
	/**
	 * 
	 * @author Hu,Hao
	 * @version 2013-06-25
	 * 同步R3产品型号
	 */
	Long createPePdModelForTb(List<MARA> t);
	
	/**
	 * @author Ren,zhong
	 * @version 2013-07-04
	 */
	List<PePdModel> getPePdModelWithClsNameAndParClsNameList(PePdModel t);
	
	/**
	 * @author Li,ZhiXiang
	 * @version 2013-09-23
	 */
	List<PePdModel> getPePdModelListForMdSerise(PePdModel t);
	
	/**
	 * @author Xiao,GuoJian
	 * @version 2014-03-09
	 */
	@SuppressWarnings("rawtypes")
	List<HashMap> getPePdModelListForMdSize(PePdModel t);
	
	List<HashMap> getPePdModelMapList(PePdModel t);
}