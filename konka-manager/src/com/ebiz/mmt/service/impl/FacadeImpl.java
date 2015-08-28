package com.ebiz.mmt.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ebiz.mmt.service.*;
import com.ebiz.ssi.service.impl.BaseFacadeImpl;

@Component("facade")
public class FacadeImpl extends BaseFacadeImpl implements Facade {

	/** 买卖提进销存 **/
	@Resource
	JxcBrandApplyService jxcBrandApplyService;

	@Resource
	BranchAssignService branchAssignService;

	@Resource
	JxcCustomerService jxcCustomerService;

	@Resource
	JxcEntpSellService jxcEntpSellService;

	@Resource
	JxcPdService jxcPdService;

	@Resource
	JxcPdTypeService jxcPdTypeService;

	@Resource
	JxcSellBillDetailsService jxcSellBillDetailsService;

	@Resource
	JxcSellBillService jxcSellBillService;

	@Resource
	JxcStockBillDetailsService jxcStockBillDetailsService;

	@Resource
	JxcStockBillService jxcStockBillService;

	@Resource
	JxcSupplierService jxcSupplierService;

	@Resource
	JxcSzDetailsService jxcSzDetailsService;

	@Resource
	JxcUseNojdxxShopService jxcUseNojdxxShopService;

	/** 买卖提进销存相关 **/
	@Resource
	JdxxEntpSellService jdxxEntpSellService;

	@Resource
	StdEntpMainService stdEntpMainService;

	@Resource
	StdEntpUserService stdEntpUserService;

	@Resource
	StdEntpKeysKeysService stdEntpKeysKeysService;

	@Resource
	PdModelService pdModelService;

	@Resource
	StdEntpKeysOprRecService stdEntpKeysOprRecService;

	@Resource
	UserInfoService userInfoService;

	@Resource
	SysSettingService sysSettingService;

	@Resource
	MvPdTypeJoinBrandService mvPdTypeJoinBrandService;

	@Resource
	InteractWebService interactWebService;

	@Resource
	StdEntpLoginService stdEntpLoginService;

	@Resource
	KonkaMobileRetailRestService konkaMobileRetailRestService;

	@Resource
	KonkaMobileLocationService konkaMobileLocationService;

	@Resource
	JnhmPdContentsService jnhmPdContentsService;

	@Resource
	KonkaVipPdManageService konkaVipPdManageService;

	@Resource
	KonkaOrderInfoTransService konkaOrderInfoTransService;

	@Resource
	KonkaOrderInfoTransDetailsService konkaOrderInfoTransDetailsService;

	@Resource
	KonkaOrderInfoTransEnsuService konkaOrderInfoTransEnsuService;

	@Resource
	JBaseStoreR3Service jBaseStoreR3Service;

	@Override
	public JnhmPdContentsService getJnhmPdContentsService() {
		return jnhmPdContentsService;
	}

	@Resource
	JnhmSelledPdCodeService jnhmSelledPdCodeService;

	@Override
	public JnhmSelledPdCodeService getJnhmSelledPdCodeService() {
		return jnhmSelledPdCodeService;
	}

	@Resource
	JxcShopOrgSnService jxcShopOrgSnService;

	@Override
	public JxcShopOrgSnService getJxcShopOrgSnService() {
		return jxcShopOrgSnService;
	}

	/** 康佳进销存 **/
	@Resource
	KonkaJxcFhBillDetailsService konkaJxcFhBillDetailsService;

	@Resource
	KonkaJxcFhBillService konkaJxcFhBillService;

	@Resource
	KonkaJxcHhRecordService konkaJxcHhRecordService;

	@Resource
	KonkaJxcPcInfoService konkaJxcPcInfoService;

	@Resource
	KonkaJxcStockBillDetailsService konkaJxcStockBillDetailsService;

	@Resource
	KonkaJxcStockBillService konkaJxcStockBillService;

	@Resource
	KonkaJxcStoreInfoService konkaJxcStoreInfoService;

	@Resource
	KonkaJxcStoreStateService konkaJxcStoreStateService;

	@Resource
	KonkaJxcStoreUpdateRecordService konkaJxcStoreUpdateRecordService;

	@Resource
	KonkaJxcThRecordService konkaJxcThRecordService;

	@Resource
	KonkaR3SellImpInvalidDataService konkaR3SellImpInvalidDataService;

	@Resource
	KonkaDeptPdLinkService konkaDeptPdLinkService;

	@Resource
	KonkaMobileSailsReturnService konkaMobileSailsReturnService;

	@Resource
	KonkaJxcStoreQckcService konkaJxcStoreQckcService;

	/** 订单信息begin **/
	@Resource
	KonkaOrderInfoService konkaOrderInfoService;

	@Resource
	KonkaOrderInfoDetailsService konkaOrderInfoDetailsService;

	@Resource
	KonkaOrderInfoUpdateRecordService konkaOrderInfoUpdateRecordService;

	@Resource
	KonkaOrderInfoAuditService konkaOrderInfoAuditService;

	@Resource
	KonkaPePdModelLowestPriceService konkaPePdModelLowestPriceService;

	@Resource
	KonkaOrderAuditProcessService konkaOrderAuditProcessService;

	@Resource
	KonkaOrderAuditProcessNodeService konkaOrderAuditProcessNodeService;

	@Resource
	KonkaOrderInfoDetailsAuditService konkaOrderInfoDetailsAuditService;

	/** 订单信息end **/

	@Resource
	KonkaPdTypeJoinPdClassService konkaPdTypeJoinPdClassService;

	@Resource
	WebService webService;

	@Resource
	SxExtendedPermissionsService sxExtendedPermissionsService;

	@Resource
	OrganizationaService organizationaService;

	@Resource
	ModPopedomService modPopedomService;

	@Resource
	CategoryService categoryService;

	@Resource
	BasePopedomService basePopedomService;

	@Resource
	KonkaRealTimeStockService konkaRealTimeStockService;

	@Resource
	KonkaOrderMessageProcessService konkaOrderMessageProcessService;

	@Override
	public KonkaRealTimeStockService getKonkaRealTimeStockService() {
		return konkaRealTimeStockService;
	}

	public void setKonkaRealTimeStockService(KonkaRealTimeStockService konkaRealTimeStockService) {
		this.konkaRealTimeStockService = konkaRealTimeStockService;
	}

	@Resource
	OperLogService operLogService;

	@Resource
	SysModuleService sysModuleService;

	@Resource
	MmtStdEntpUserService mmtStdEntpUserService;

	@Resource
	MmtUserInfoService mmtUserInfoService;

	@Resource
	BasePropService basePropService;

	@Resource
	PeModuleService peModuleService;

	@Resource
	BasePropValItemService basePropValItemService;

	@Resource
	PdModelPropsValService pdModelPropsValService;

	@Resource
	BasePdClassService basePdClassService;

	@Resource
	BasePropCategoryService basePropCategoryService;

	@Resource
	BasePdClazzService basePdClazzService;

	@Resource
	BaseProvinceListService baseProvinceListService;

	@Resource
	BaseProvinceListFourService baseProvinceListFourService;

	// /////////////////新加的请放在下面
	@Resource
	PePdModelService pePdModelService;

	@Resource
	StdEntpProdService stdEntpProdService;

	@Resource
	StdEntpProdJoinBrandIdService stdEntpProdJoinBrandIdService;

	@Resource
	PePdSellareaService pePdSellareaService;

	@Resource
	BaseBrandInfoService baseBrandInfoService;

	@Resource
	PeProdUserService peProdUserService;

	@Resource
	PePositionService pePositionService;

	@Resource
	PeRoleInfoService PeRoleInfoService;

	@Resource
	PeRoleUserService peRoleUserService;

	@Resource
	KonkaR3MmtMatchService konkaR3MmtMatchService;

	@Resource
	KonkaR3ShopService konkaR3ShopService;

	@Resource
	PeShopCategoryService peShopCategoryService;

	@Resource
	KonkaPeArticleTypeService konkaPeArticleTypeService;

	@Resource
	KonkaDeptService konkaDeptService;

	@Resource
	KonkaSellDetailsService konkaSellDetailsService;

	@Resource
	KonkaSellService konkaSellService;

	@Resource
	KonkaStockDetailsService konkaStockDetailsService;

	@Resource
	KonkaStockService konkaStockService;

	@Resource
	KonkaPdModelService konkaPdModelService;

	@Resource
	MmtEntpShopService mmtEntpShopService;

	@Resource
	PeShopService peShopService;

	@Resource
	EntpShopService entpShopService;

	@Resource
	MvShopPdtypeAndBrandService mvShopPdtypeAndBrandService;

	@Resource
	PeShopLeaderRecService peShopLeaderRecService;

	@Resource
	BasePdTypeService basePdTypeService;

	@Resource
	MvClsidJoinBrandXxhxService mvClsidJoinBrandXxhxService;

	@Resource
	MdasShopBrandsalesService mdasShopBrandsalesService;

	@Resource
	MdasShopSalesService mdasShopSalesService;

	@Resource
	KonkaPeArticleContentService konkaPeArticleContentService;

	@Resource
	KonkaPeArticleUserMsgService konkaPeArticleUserMsgService;

	@Resource
	KonkaPeAttachmentsService konkaPeAttachmentsService;

	@Resource
	KonkaPeArticleInfoService konkaPeArticleInfoService;

	@Resource
	PeShopMsgService peShopMsgService;

	// 地理信息
	@Resource
	GeoCnService geoCnService;

	@Resource
	MdasRegionsService mdasRegionsService;

	@Resource
	MdasRegionsProvinceService mdasRegionsProvinceService;

	@Resource
	MdasBuyerInfoService mdasBuyerInfoService;

	@Resource
	MdasModPermissionService mdasModPermissionService;

	@Resource
	KonkaPePublicScopeService konkaPePublicScopeService;

	// 网点开拓
	@Resource
	KonkaShopVisitService konkaShopVisitService;

	@Resource
	KonkaShopDevelopService konkaShopDevelopService;

	// R3订单导入
	@Resource
	ChannelDataImportService channelDataImportService;

	@Resource
	ChannelDataImport2Service channelDataImport2Service;

	// R3客户回款导入
	@Resource
	KonkaR3BackmoneyService konkaR3BackmoneyService;

	// 任务管理
	@Resource
	ImportDataTypesService importDataTypesService;

	@Resource
	TaskParaService taskParaService;

	@Resource
	RetailMsBaseService retailMsBaseService;

	// 分公司协同办公功能
	@Resource
	KonkaItemPropertyService konkaItemPropertyService;

	@Resource
	KonkaItemService konkaItemService;

	@Resource
	KonkaoaCategoryService KonkaoaCategoryService;

	@Resource
	KonkaoaDocInfoService KonkaoaDocInfoService;

	@Resource
	KonkaoaDocRecipientService KonkaoaDocRecipientService;

	@Resource
	KonkaoaFilesAuditAgentUserService KonkaoaFilesAuditAgentUserService;

	@Resource
	KonkaoaFilesAuditNodeService KonkaoaFilesAuditNodeService;

	@Resource
	KonkaoaFilesAuditPopedomService KonkaoaFilesAuditPopedomService;

	@Resource
	KonkaoaFilesContentService KonkaoaFilesContentService;

	@Resource
	KonkaoaFilesPropertyService KonkaoaFilesPropertyService;

	@Resource
	KonkaoaFilesRecipientService KonkaoaFilesRecipientService;

	@Resource
	KonkaoaFilesService KonkaoaFilesService;

	@Resource
	KonkaMobileSailDataService konkaMobileSailDataService;

	@Resource
	KonkaMobileThingsUseReportService konkaMobileThingsUseReportService;

	@Resource
	KonkaExpenseClaimsService konkaExpenseClaimsService;

	@Resource
	KonkaAgentsShopRelService konkaAgentsShopRelService;

	@Resource
	KonkaBranchCategoryService konkaBranchCategoryService;

	@Resource
	KonkaPePublicClickService konkaPePublicClickService;

	@Resource
	KonkaMobileCategoryService konkaMobileCategoryService;

	@Resource
	KonkaMobileThingsBaseService konkaMobileThingsBaseService;

	@Resource
	KonkaMobileTerminalFeedbackService konkaMobileTerminalFeedbackService;

	@Resource
	KonkaMobileReportHistoryService konkaMobileReportHistoryService;

	@Resource
	KonkaMobileFightReportService konkaMobileFightReportService;

	@Resource
	KonkaMobileTerminalFbBackService konkaMobileTerminalFbBackService;

	@Resource
	KonkaMobileCollectionService konkaMobileCollectionService;

	@Resource
	KonkaMobileStocksHisService konkaMobileStocksHisService;

	@Resource
	KonkaMobileStoragesService konkaMobileStoragesService;

	@Resource
	KonkaSellReportableTmpService konkaSellReportableTmpService;

	@Resource
	KonkaSellReportdataTmpService konkaSellReportdataTmpService;

	@Resource
	KonkaPriceManagerService konkaPriceManagerService;

	@Resource
	KonkaPartershipService konkaPartershipService;

	@Resource
	KonkaMobileCustVisitService konkaMobileCustVisitService;

	@Resource
	KonkaMobileCustVisitDetailService konkaMobileCustVisitDetailService;

	@Resource
	KonkaMobileCustVisitExtendService konkaMobileCustVisitExtendService;

	@Resource
	KonkaMobileCustVisitTypeService konkaMobileCustVisitTypeService;

	@Resource
	BaseVisitTypeService baseVisitTypeService;

	@Resource
	VOrgOfCustomerService vOrgOfCustomerService;

	@Resource
	JStocksSummaryService jStocksSummaryService;

	@Override
	public KonkaMobileCustVisitService getKonkaMobileCustVisitService() {
		return konkaMobileCustVisitService;
	}

	@Resource
	KonkaYwHzTjService konkaYwHzTjService;

	@Resource
	KonkaOrderPlanService konkaOrderPlanService;

	@Override
	public KonkaMobileCustVisitDetailService getKonkaMobileCustVisitDetailService() {
		return konkaMobileCustVisitDetailService;
	}

	@Override
	public KonkaMobileCustVisitExtendService getKonkaMobileCustVisitExtendService() {
		return konkaMobileCustVisitExtendService;
	}

	@Override
	public KonkaMobileCustVisitTypeService getKonkaMobileCustVisitTypeService() {
		return konkaMobileCustVisitTypeService;
	}

	@Override
	public BaseVisitTypeService getBaseVisitTypeService() {
		return baseVisitTypeService;
	}

	@Override
	public BaseBrandInfoService getBaseBrandInfoService() {
		return baseBrandInfoService;
	}

	@Override
	public BasePdClassService getBasePdClassService() {
		return basePdClassService;
	}

	@Override
	public BasePdClazzService getBasePdClazzService() {
		return basePdClazzService;
	}

	@Override
	public BasePdTypeService getBasePdTypeService() {
		return basePdTypeService;
	}

	@Override
	public BasePopedomService getBasePopedomService() {
		return basePopedomService;
	}

	@Override
	public BasePropCategoryService getBasePropCategoryService() {
		return basePropCategoryService;
	}

	@Override
	public BasePropService getBasePropService() {
		return basePropService;
	}

	@Override
	public BasePropValItemService getBasePropValItemService() {
		return basePropValItemService;
	}

	@Override
	public BaseProvinceListFourService getBaseProvinceListFourService() {
		return baseProvinceListFourService;
	}

	@Override
	public BaseProvinceListService getBaseProvinceListService() {
		return baseProvinceListService;
	}

	@Override
	public CategoryService getCategoryService() {
		return categoryService;
	}

	@Override
	public ChannelDataImportService getChannelDataImportService() {
		return channelDataImportService;
	}

	@Override
	public EntpShopService getEntpShopService() {
		return entpShopService;
	}

	@Override
	public GeoCnService getGeoCnService() {
		return geoCnService;
	}

	@Override
	public ImportDataTypesService getImportDataTypesService() {
		return importDataTypesService;
	}

	@Override
	public InteractWebService getInteractWebService() {
		return interactWebService;
	}

	@Override
	public JdxxEntpSellService getJdxxEntpSellService() {
		return jdxxEntpSellService;
	}

	@Override
	public JxcBrandApplyService getJxcBrandApplyService() {
		return jxcBrandApplyService;
	}

	@Override
	public JxcCustomerService getJxcCustomerService() {
		return jxcCustomerService;
	}

	@Override
	public JxcEntpSellService getJxcEntpSellService() {
		return jxcEntpSellService;
	}

	@Override
	public JxcPdService getJxcPdService() {
		return jxcPdService;
	}

	@Override
	public JxcPdTypeService getJxcPdTypeService() {
		return jxcPdTypeService;
	}

	@Override
	public JxcSellBillDetailsService getJxcSellBillDetailsService() {
		return jxcSellBillDetailsService;
	}

	@Override
	public JxcSellBillService getJxcSellBillService() {
		return jxcSellBillService;
	}

	@Override
	public JxcStockBillDetailsService getJxcStockBillDetailsService() {
		return jxcStockBillDetailsService;
	}

	@Override
	public JxcStockBillService getJxcStockBillService() {
		return jxcStockBillService;
	}

	@Override
	public JxcSupplierService getJxcSupplierService() {
		return jxcSupplierService;
	}

	@Override
	public JxcSzDetailsService getJxcSzDetailsService() {
		return jxcSzDetailsService;
	}

	@Override
	public JxcUseNojdxxShopService getJxcUseNojdxxShopService() {
		return jxcUseNojdxxShopService;
	}

	@Override
	public KonkaAgentsShopRelService getKonkaAgentsShopRelService() {
		return konkaAgentsShopRelService;
	}

	@Override
	public KonkaBranchCategoryService getKonkaBranchCategoryService() {
		return konkaBranchCategoryService;
	}

	@Override
	public KonkaDeptPdLinkService getKonkaDeptPdLinkService() {
		return konkaDeptPdLinkService;
	}

	@Override
	public KonkaDeptService getKonkaDeptService() {
		return konkaDeptService;
	}

	@Override
	public MmtEntpShopService getMmtEntpShopService() {
		return mmtEntpShopService;
	}

	@Override
	public KonkaItemPropertyService getKonkaItemPropertyService() {
		return konkaItemPropertyService;
	}

	@Override
	public KonkaItemService getKonkaItemService() {
		return konkaItemService;
	}

	@Override
	public KonkaJxcFhBillDetailsService getKonkaJxcFhBillDetailsService() {
		return konkaJxcFhBillDetailsService;
	}

	@Override
	public KonkaJxcFhBillService getKonkaJxcFhBillService() {
		return konkaJxcFhBillService;
	}

	@Override
	public KonkaJxcHhRecordService getKonkaJxcHhRecordService() {
		return konkaJxcHhRecordService;
	}

	@Override
	public KonkaJxcPcInfoService getKonkaJxcPcInfoService() {
		return konkaJxcPcInfoService;
	}

	@Override
	public KonkaJxcStockBillDetailsService getKonkaJxcStockBillDetailsService() {
		return konkaJxcStockBillDetailsService;
	}

	@Override
	public KonkaJxcStockBillService getKonkaJxcStockBillService() {
		return konkaJxcStockBillService;
	}

	@Override
	public KonkaJxcStoreInfoService getKonkaJxcStoreInfoService() {
		return konkaJxcStoreInfoService;
	}

	@Override
	public KonkaJxcStoreQckcService getKonkaJxcStoreQckcService() {
		return konkaJxcStoreQckcService;
	}

	@Override
	public KonkaJxcStoreStateService getKonkaJxcStoreStateService() {
		return konkaJxcStoreStateService;
	}

	@Override
	public KonkaJxcStoreUpdateRecordService getKonkaJxcStoreUpdateRecordService() {
		return konkaJxcStoreUpdateRecordService;
	}

	@Override
	public KonkaJxcThRecordService getKonkaJxcThRecordService() {
		return konkaJxcThRecordService;
	}

	@Override
	public KonkaMobileCategoryService getKonkaMobileCategoryService() {
		return konkaMobileCategoryService;
	}

	@Override
	public KonkaMobileCollectionService getKonkaMobileCollectionService() {
		return konkaMobileCollectionService;
	}

	@Override
	public KonkaMobileFightReportService getKonkaMobileFightReportService() {
		return konkaMobileFightReportService;
	}

	@Override
	public KonkaMobileReportHistoryService getKonkaMobileReportHistoryService() {
		return konkaMobileReportHistoryService;
	}

	@Override
	public KonkaMobileRetailRestService getKonkaMobileRetailRestService() {
		return konkaMobileRetailRestService;
	}

	@Override
	public KonkaMobileSailDataService getKonkaMobileSailDataService() {
		return konkaMobileSailDataService;
	}

	@Override
	public KonkaMobileSailsReturnService getKonkaMobileSailsReturnService() {
		return konkaMobileSailsReturnService;
	}

	@Override
	public KonkaMobileStocksHisService getKonkaMobileStocksHisService() {
		return konkaMobileStocksHisService;
	}

	@Override
	public KonkaMobileStoragesService getKonkaMobileStoragesService() {
		return konkaMobileStoragesService;
	}

	@Override
	public KonkaMobileTerminalFbBackService getKonkaMobileTerminalFbBackService() {
		return konkaMobileTerminalFbBackService;
	}

	@Override
	public KonkaMobileTerminalFeedbackService getKonkaMobileTerminalFeedbackService() {
		return konkaMobileTerminalFeedbackService;
	}

	@Override
	public KonkaMobileThingsBaseService getKonkaMobileThingsBaseService() {
		return konkaMobileThingsBaseService;
	}

	@Override
	public KonkaMobileThingsUseReportService getKonkaMobileThingsUseReportService() {
		return konkaMobileThingsUseReportService;
	}

	@Override
	public KonkaoaCategoryService getKonkaoaCategoryService() {
		return KonkaoaCategoryService;
	}

	@Override
	public KonkaoaDocInfoService getKonkaoaDocInfoService() {
		return KonkaoaDocInfoService;
	}

	@Override
	public KonkaoaDocRecipientService getKonkaoaDocRecipientService() {
		return KonkaoaDocRecipientService;
	}

	@Override
	public KonkaoaFilesAuditAgentUserService getKonkaoaFilesAuditAgentUserService() {
		return KonkaoaFilesAuditAgentUserService;
	}

	@Override
	public KonkaoaFilesAuditNodeService getKonkaoaFilesAuditNodeService() {
		return KonkaoaFilesAuditNodeService;
	}

	@Override
	public KonkaoaFilesAuditPopedomService getKonkaoaFilesAuditPopedomService() {
		return KonkaoaFilesAuditPopedomService;
	}

	@Override
	public KonkaoaFilesContentService getKonkaoaFilesContentService() {
		return KonkaoaFilesContentService;
	}

	@Override
	public KonkaoaFilesPropertyService getKonkaoaFilesPropertyService() {
		return KonkaoaFilesPropertyService;
	}

	@Override
	public KonkaoaFilesRecipientService getKonkaoaFilesRecipientService() {
		return KonkaoaFilesRecipientService;
	}

	@Override
	public KonkaoaFilesService getKonkaoaFilesService() {
		return KonkaoaFilesService;
	}

	@Override
	public KonkaOrderAuditProcessNodeService getKonkaOrderAuditProcessNodeService() {
		return konkaOrderAuditProcessNodeService;
	}

	@Override
	public KonkaOrderAuditProcessService getKonkaOrderAuditProcessService() {
		return konkaOrderAuditProcessService;
	}

	@Override
	public KonkaOrderInfoAuditService getKonkaOrderInfoAuditService() {
		return konkaOrderInfoAuditService;
	}

	@Override
	public KonkaOrderInfoDetailsService getKonkaOrderInfoDetailsService() {
		return konkaOrderInfoDetailsService;
	}

	@Override
	public KonkaOrderInfoService getKonkaOrderInfoService() {
		return konkaOrderInfoService;
	}

	@Override
	public KonkaOrderInfoUpdateRecordService getKonkaOrderInfoUpdateRecordService() {
		return konkaOrderInfoUpdateRecordService;
	}

	@Override
	public KonkaOrderInfoDetailsAuditService getKonkaOrderInfoDetailsAuditService() {
		return konkaOrderInfoDetailsAuditService;
	}

	@Override
	public KonkaPdModelService getKonkaPdModelService() {
		return konkaPdModelService;
	}

	@Override
	public KonkaPdTypeJoinPdClassService getKonkaPdTypeJoinPdClassService() {
		return konkaPdTypeJoinPdClassService;
	}

	@Override
	public KonkaPeArticleContentService getKonkaPeArticleContentService() {
		return konkaPeArticleContentService;
	}

	@Override
	public KonkaPeArticleInfoService getKonkaPeArticleInfoService() {
		return konkaPeArticleInfoService;
	}

	@Override
	public KonkaPeArticleTypeService getKonkaPeArticleTypeService() {
		return konkaPeArticleTypeService;
	}

	@Override
	public KonkaPeArticleUserMsgService getKonkaPeArticleUserMsgService() {
		return konkaPeArticleUserMsgService;
	}

	@Override
	public KonkaPeAttachmentsService getKonkaPeAttachmentsService() {
		return konkaPeAttachmentsService;
	}

	@Override
	public KonkaPePdModelLowestPriceService getKonkaPePdModelLowestPriceService() {
		return konkaPePdModelLowestPriceService;
	}

	@Override
	public KonkaPePublicClickService getKonkaPePublicClickService() {
		return konkaPePublicClickService;
	}

	@Override
	public KonkaPePublicScopeService getKonkaPePublicScopeService() {
		return konkaPePublicScopeService;
	}

	@Override
	public KonkaR3BackmoneyService getKonkaR3BackmoneyService() {
		return konkaR3BackmoneyService;
	}

	@Override
	public KonkaR3MmtMatchService getKonkaR3MmtMatchService() {
		return konkaR3MmtMatchService;
	}

	@Override
	public KonkaR3SellImpInvalidDataService getKonkaR3SellImpInvalidDataService() {
		return konkaR3SellImpInvalidDataService;
	}

	@Override
	public KonkaR3ShopService getKonkaR3ShopService() {
		return konkaR3ShopService;
	}

	@Override
	public KonkaSellDetailsService getKonkaSellDetailsService() {
		return konkaSellDetailsService;
	}

	@Override
	public KonkaSellReportableTmpService getKonkaSellReportableTmpService() {
		return konkaSellReportableTmpService;
	}

	@Override
	public KonkaSellReportdataTmpService getKonkaSellReportdataTmpService() {
		return konkaSellReportdataTmpService;
	}

	@Override
	public SysSettingService getSysSettingService() {
		return sysSettingService;
	}

	public void setSysSettingService(SysSettingService sysSettingService) {
		this.sysSettingService = sysSettingService;
	}

	@Override
	public KonkaSellService getKonkaSellService() {
		return konkaSellService;
	}

	@Override
	public KonkaShopDevelopService getKonkaShopDevelopService() {
		return konkaShopDevelopService;
	}

	@Override
	public KonkaShopVisitService getKonkaShopVisitService() {
		return konkaShopVisitService;
	}

	@Override
	public KonkaStockDetailsService getKonkaStockDetailsService() {
		return konkaStockDetailsService;
	}

	@Override
	public KonkaStockService getKonkaStockService() {
		return konkaStockService;
	}

	@Override
	public MdasBuyerInfoService getMdasBuyerInfoService() {
		return mdasBuyerInfoService;
	}

	@Override
	public MdasModPermissionService getMdasModPermissionService() {
		return mdasModPermissionService;
	}

	@Override
	public MdasRegionsProvinceService getMdasRegionsProvinceService() {
		return mdasRegionsProvinceService;
	}

	@Override
	public MdasRegionsService getMdasRegionsService() {
		return mdasRegionsService;
	}

	@Override
	public MdasShopBrandsalesService getMdasShopBrandsalesService() {
		return mdasShopBrandsalesService;
	}

	@Override
	public MdasShopSalesService getMdasShopSalesService() {
		return mdasShopSalesService;
	}

	@Override
	public MmtStdEntpUserService getMmtStdEntpUserService() {
		return mmtStdEntpUserService;
	}

	@Override
	public MmtUserInfoService getMmtUserInfoService() {
		return mmtUserInfoService;
	}

	@Override
	public ModPopedomService getModPopedomService() {
		return modPopedomService;
	}

	@Override
	public MvClsidJoinBrandXxhxService getMvClsidJoinBrandXxhxService() {
		return mvClsidJoinBrandXxhxService;
	}

	@Override
	public MvPdTypeJoinBrandService getMvPdTypeJoinBrandService() {
		return mvPdTypeJoinBrandService;
	}

	@Override
	public MvShopPdtypeAndBrandService getMvShopPdtypeAndBrandService() {
		return mvShopPdtypeAndBrandService;
	}

	@Override
	public OperLogService getOperLogService() {
		return operLogService;
	}

	@Override
	public OrganizationaService getOrganizationaService() {
		return organizationaService;
	}

	@Override
	public PdModelPropsValService getPdModelPropsValService() {
		return pdModelPropsValService;
	}

	@Override
	public PdModelService getPdModelService() {
		return pdModelService;
	}

	@Override
	public PeModuleService getPeModuleService() {
		return peModuleService;
	}

	@Override
	public PePdModelService getPePdModelService() {
		return pePdModelService;
	}

	@Override
	public PePdSellareaService getPePdSellareaService() {
		return pePdSellareaService;
	}

	@Override
	public PePositionService getPePositionService() {
		return pePositionService;
	}

	@Override
	public PeProdUserService getPeProdUserService() {
		return peProdUserService;
	}

	@Override
	public PeRoleInfoService getPeRoleInfoService() {
		return PeRoleInfoService;
	}

	@Override
	public PeRoleUserService getPeRoleUserService() {
		return peRoleUserService;
	}

	@Override
	public PeShopCategoryService getPeShopCategoryService() {
		return peShopCategoryService;
	}

	@Override
	public PeShopLeaderRecService getPeShopLeaderRecService() {
		return peShopLeaderRecService;
	}

	@Override
	public PeShopMsgService getPeShopMsgService() {
		return peShopMsgService;
	}

	@Override
	public PeShopService getPeShopService() {
		return peShopService;
	}

	@Override
	public BranchAssignService getBranchAssignService() {
		return branchAssignService;
	}

	@Override
	public RetailMsBaseService getRetailMsBaseService() {
		return retailMsBaseService;
	}

	@Override
	public StdEntpKeysKeysService getStdEntpKeysKeysService() {
		return stdEntpKeysKeysService;
	}

	@Override
	public StdEntpKeysOprRecService getStdEntpKeysOprRecService() {
		return stdEntpKeysOprRecService;
	}

	@Override
	public StdEntpLoginService getStdEntpLoginService() {
		return stdEntpLoginService;
	}

	@Override
	public KonkaExpenseClaimsService getKonkaExpenseClaimsService() {
		return konkaExpenseClaimsService;
	}

	@Override
	public StdEntpMainService getStdEntpMainService() {
		return stdEntpMainService;
	}

	@Override
	public StdEntpProdJoinBrandIdService getStdEntpProdJoinBrandIdService() {
		return stdEntpProdJoinBrandIdService;
	}

	@Override
	public StdEntpProdService getStdEntpProdService() {
		return stdEntpProdService;
	}

	@Override
	public StdEntpUserService getStdEntpUserService() {
		return stdEntpUserService;
	}

	@Override
	public SxExtendedPermissionsService getSxExtendedPermissionsService() {
		return sxExtendedPermissionsService;
	}

	@Override
	public SysModuleService getSysModuleService() {
		return sysModuleService;
	}

	@Override
	public TaskParaService getTaskParaService() {
		return taskParaService;
	}

	@Override
	public UserInfoService getUserInfoService() {
		return userInfoService;
	}

	@Override
	public WebService getWebService() {
		return webService;
	}

	public void setEntpShopService(EntpShopService entpShopService) {
		this.entpShopService = entpShopService;
	}

	public void setMmtEntpShopService(MmtEntpShopService mmtEntpShopService) {
		this.mmtEntpShopService = mmtEntpShopService;
	}

	public void setKonkaMobileThingsUseReportService(KonkaMobileThingsUseReportService konkaMobileThingsUseReportService) {
		this.konkaMobileThingsUseReportService = konkaMobileThingsUseReportService;
	}

	public void setKonkaPdModelService(KonkaPdModelService konkaPdModelService) {
		this.konkaPdModelService = konkaPdModelService;
	}

	public void setKonkaSellDetailsService(KonkaSellDetailsService konkaSellDetailsService) {
		this.konkaSellDetailsService = konkaSellDetailsService;
	}

	public void setKonkaSellService(KonkaSellService konkaSellService) {
		this.konkaSellService = konkaSellService;
	}

	public void setKonkaStockDetailsService(KonkaStockDetailsService konkaStockDetailsService) {
		this.konkaStockDetailsService = konkaStockDetailsService;
	}

	public void setKonkaStockService(KonkaStockService konkaStockService) {
		this.konkaStockService = konkaStockService;
	}

	public void setMvShopPdtypeAndBrandService(MvShopPdtypeAndBrandService mvShopPdtypeAndBrandService) {
		this.mvShopPdtypeAndBrandService = mvShopPdtypeAndBrandService;
	}

	public void setPeShopLeaderRecService(PeShopLeaderRecService peShopLeaderRecService) {
		this.peShopLeaderRecService = peShopLeaderRecService;
	}

	public void setPeShopService(PeShopService peShopService) {
		this.peShopService = peShopService;
	}

	@Resource
	KonkaSxOperLogService konkaSxOperLogService;

	@Override
	public KonkaSxOperLogService getKonkaSxOperLogService() {
		return konkaSxOperLogService;
	}

	@Resource
	KonkaSellReportTmpService konkaSellReportTmpService;

	@Override
	public KonkaSellReportTmpService getKonkaSellReportTmpService() {
		return konkaSellReportTmpService;
	}

	@Resource
	KonkaSellReportTmpResService konkaSellReportTmpResService;

	@Override
	public KonkaSellReportTmpResService getKonkaSellReportTmpResService() {
		return konkaSellReportTmpResService;
	}

	@Resource
	KonkaJxcBindingMobileService konkaJxcBindingMobileService;

	@Override
	public KonkaJxcBindingMobileService getKonkaJxcBindingMobileService() {
		return konkaJxcBindingMobileService;
	}

	// 终端管理
	@Resource
	KonkaBoothsService konkaBoothsService;

	@Override
	public KonkaBoothsService getKonkaBoothsService() {
		return konkaBoothsService;
	}

	// 康佳专卖店 START... author Jiang,JiaYong , date 2012-1-9
	@Resource
	KonkaXxBaseTypeService konkaXxBaseTypeService;

	@Resource
	KonkaXxGoodsService konkaXxGoodsService;

	@Resource
	KonkaXxPdService konkaXxPdService;

	@Resource
	KonkaXxSellBillService konkaXxSellBillService;

	@Resource
	KonkaXxSellBillDetailsService konkaXxSellBillDetailsService;

	@Resource
	KonkaXxZmdDemomacService konkaXxZmdDemomacService;

	@Resource
	KonkaXxZmdRewardSetService konkaXxZmdRewardSetService;

	@Resource
	KonkaXxZmdService konkaXxZmdService;

	@Override
	public KonkaXxBaseTypeService getKonkaXxBaseTypeService() {
		return konkaXxBaseTypeService;
	}

	@Override
	public KonkaXxGoodsService getKonkaXxGoodsService() {
		return konkaXxGoodsService;
	}

	@Override
	public KonkaXxPdService getKonkaXxPdService() {
		return konkaXxPdService;
	}

	@Override
	public KonkaXxSellBillService getKonkaXxSellBillService() {
		return konkaXxSellBillService;
	}

	@Override
	public KonkaXxSellBillDetailsService getKonkaXxSellBillDetailsService() {
		return konkaXxSellBillDetailsService;
	}

	@Override
	public KonkaXxZmdDemomacService getKonkaXxZmdDemomacService() {
		return konkaXxZmdDemomacService;
	}

	@Override
	public KonkaXxZmdRewardSetService getKonkaXxZmdRewardSetService() {
		return konkaXxZmdRewardSetService;
	}

	@Override
	public KonkaXxZmdService getKonkaXxZmdService() {
		return konkaXxZmdService;
	}

	@Resource
	KonkaXxZmdUsersService konkaXxZmdUsersService;

	@Override
	public KonkaXxZmdUsersService getKonkaXxZmdUsersService() {
		return konkaXxZmdUsersService;
	}

	@Resource
	KonkaXxStdCompService konkaXxStdCompService;

	@Resource
	KonkaXxStdPdService konkaXxStdPdService;

	@Resource
	KonkaXxStdStoreService konkaXxStdStoreService;

	@Resource
	KonkaXxZmdStoreService konkaXxZmdStoreService;

	@Resource
	KonkaXxSellBillDetailsDstService konkaXxSellBillDetailsDstService;

	@Override
	public KonkaXxStdCompService getKonkaXxStdCompService() {
		return konkaXxStdCompService;
	}

	@Override
	public KonkaXxStdPdService getKonkaXxStdPdService() {
		return konkaXxStdPdService;
	}

	@Override
	public KonkaXxStdStoreService getKonkaXxStdStoreService() {
		return konkaXxStdStoreService;
	}

	@Override
	public KonkaXxZmdStoreService getKonkaXxZmdStoreService() {
		return konkaXxZmdStoreService;
	}

	@Override
	public KonkaXxSellBillDetailsDstService getKonkaXxSellBillDetailsDstService() {
		return konkaXxSellBillDetailsDstService;
	}

	// 康佳专卖店 END.

	// 业务员分布
	@Resource
	KonkaUserMobileService konkaUserMobileService;

	@Resource
	KonkaUserInvalidMobileService konkaUserInvalidMobileService;

	@Resource
	KonkaUserGpsInfoService konkaUserGpsInfoService;

	@Resource
	KonkaUserMobileSetService konkaUserMobileSetService;

	@Resource
	KonkaUserMobileSetPlanService konkaUserMobileSetPlanService;

	@Override
	public KonkaUserMobileService getKonkaUserMobileService() {
		return konkaUserMobileService;
	}

	@Override
	public KonkaUserInvalidMobileService getKonkaUserInvalidMobileService() {
		return konkaUserInvalidMobileService;
	}

	@Override
	public KonkaUserGpsInfoService getKonkaUserGpsInfoService() {
		return konkaUserGpsInfoService;
	}

	@Override
	public KonkaUserMobileSetService getKonkaUserMobileSetService() {
		return konkaUserMobileSetService;
	}

	@Override
	public KonkaUserMobileSetPlanService getKonkaUserMobileSetPlanService() {
		return konkaUserMobileSetPlanService;
	}

	// 业务员分布 END.
	// 完美终端
	@Resource
	KonkaParagonShowinfoService konkaParagonShowinfoService;

	@Override
	public KonkaParagonShowinfoService getKonkaParagonShowinfoService() {
		return konkaParagonShowinfoService;
	}

	@Resource
	KonkaParagonManinfoService konkaParagonManinfoService;

	@Override
	public KonkaParagonManinfoService getKonkaParagonManinfoService() {
		return konkaParagonManinfoService;
	}

	@Resource
	KonkaParagonEquipmentCService konkaParagonEquipmentCService;

	@Override
	public KonkaParagonEquipmentCService getKonkaParagonEquipmentCService() {
		return konkaParagonEquipmentCService;
	}

	@Resource
	KonkaParagonEquipmentJService konkaParagonEquipmentJService;

	@Override
	public KonkaParagonEquipmentJService getKonkaParagonEquipmentJService() {
		return konkaParagonEquipmentJService;
	}

	@Resource
	KonkaParagonEtcashService konkaParagonEtcashService;

	@Override
	public KonkaParagonEtcashService getKonkaParagonEtcashService() {
		return konkaParagonEtcashService;
	}

	@Resource
	KonkaParagonSalesService konkaParagonSalesService;

	@Override
	public KonkaParagonSalesService getKonkaParagonSalesService() {
		return konkaParagonSalesService;
	}

	@Resource
	KonkaParagonShowtService konkaParagonShowtService;

	@Override
	public KonkaParagonShowtService getKonkaParagonShowtService() {
		return konkaParagonShowtService;
	}

	@Resource
	KonkaParagonShowimgService konkaParagonShowimgService;

	@Override
	public KonkaParagonShowimgService getKonkaParagonShowimgService() {
		return konkaParagonShowimgService;
	}

	@Resource
	KonkaParagonShowshopDetailService konkaParagonShowshopDetailService;

	@Override
	public KonkaParagonShowshopDetailService getKonkaParagonShowshopDetailService() {
		return konkaParagonShowshopDetailService;
	}

	@Resource
	KonkaParagonShowmanreService konkaParagonShowmanreService;

	@Override
	public KonkaParagonShowmanreService getKonkaParagonShowmanreService() {
		return konkaParagonShowmanreService;
	}

	@Resource
	KonkaParagonShowversionService konkaParagonShowversionService;

	@Override
	public KonkaParagonShowversionService getKonkaParagonShowversionService() {
		return konkaParagonShowversionService;
	}

	@Resource
	KonkaParagonAttentionJService konkaParagonAttentionJService;

	@Override
	public KonkaParagonAttentionJService getKonkaParagonAttentionJService() {
		return konkaParagonAttentionJService;
	}

	@Resource
	KonkaParagonAttentionCService konkaParagonAttentionCService;

	@Override
	public KonkaParagonAttentionCService getKonkaParagonAttentionCService() {
		return konkaParagonAttentionCService;
	}

	/**
	 * @author Ren,zhong
	 * @version 2012-03-02
	 */
	@Resource
	KonkaXxZmdAccoutsService konkaXxZmdAccoutsService;

	@Resource
	KonkaXxPdHistoryService konkaXxPdHistoryService;

	@Resource
	KonkaXxZmdHdSetService konkaXxZmdHdSetService;

	@Resource
	KonkaXxZmdRewardSetHdService konkaXxZmdRewardSetHdService;

	@Resource
	KonkaXxZmdRewardSetPdService konkaXxZmdRewardSetPdService;

	@Override
	public KonkaXxZmdAccoutsService getKonkaXxZmdAccoutsService() {
		return konkaXxZmdAccoutsService;
	}

	@Override
	public KonkaXxPdHistoryService getKonkaXxPdHistoryService() {
		return konkaXxPdHistoryService;
	}

	@Override
	public KonkaXxZmdHdSetService getKonkaXxZmdHdSetService() {
		return konkaXxZmdHdSetService;
	}

	@Override
	public KonkaXxZmdRewardSetHdService getKonkaXxZmdRewardSetHdService() {
		return konkaXxZmdRewardSetHdService;
	}

	@Override
	public KonkaXxZmdRewardSetPdService getKonkaXxZmdRewardSetPdService() {
		return konkaXxZmdRewardSetPdService;
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2012-03-19
	 */
	@Resource
	KonkaXxNoticeService konkaXxNoticeService;

	@Resource
	KonkaXxNoticeContentService konkaXxNoticeContentService;

	@Override
	public KonkaXxNoticeService getKonkaXxNoticeService() {
		return konkaXxNoticeService;
	}

	@Override
	public KonkaXxNoticeContentService getKonkaXxNoticeContentService() {
		return konkaXxNoticeContentService;
	}

	/**
	 * @author Ren,zhong
	 * @version 2012-03-19
	 */
	@Resource
	KonkaXxActMessageService konkaXxActMessageService;

	@Resource
	KonkaXxMessageService konkaXxMessageService;

	@Override
	public KonkaXxActMessageService getKonkaXxActMessageService() {
		return konkaXxActMessageService;
	}

	@Override
	public KonkaXxMessageService getKonkaXxMessageService() {
		return konkaXxMessageService;
	}

	@Resource
	KonkaXxZmdQuotaService konkaXxZmdQuotaService;

	@Override
	public KonkaXxZmdQuotaService getKonkaXxZmdQuotaService() {
		return konkaXxZmdQuotaService;
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2012-03-21
	 */
	@Resource
	KonkaXxZmdDailyStmsService konkaXxZmdDailyStmsService;

	@Resource
	KonkaXxZmdDailyStmsDetailService konkaXxZmdDailyStmsDetailService;

	@Override
	public KonkaXxZmdDailyStmsService getKonkaXxZmdDailyStmsService() {
		return konkaXxZmdDailyStmsService;
	}

	@Override
	public KonkaXxZmdDailyStmsDetailService getKonkaXxZmdDailyStmsDetailService() {
		return konkaXxZmdDailyStmsDetailService;
	}

	@Resource
	TodoListService todoListService;

	@Override
	public TodoListService getTodoListService() {
		return todoListService;
	}

	@Resource
	KonkaXxRewardModifyService konkaXxRewardModifyService;

	@Override
	public KonkaXxRewardModifyService getKonkaXxRewardModifyService() {
		return konkaXxRewardModifyService;
	}

	/**
	 * @author Ren,zhong
	 * @version 2012-03-22
	 */
	@Resource
	KonkaXxZmdDailyDistService konkaXxZmdDailyDistService;

	@Resource
	KonkaXxZmdDailyDistDetailService konkaXxZmdDailyDistDetailService;

	@Override
	public KonkaXxZmdDailyDistService getKonkaXxZmdDailyDistService() {
		return konkaXxZmdDailyDistService;
	}

	@Override
	public KonkaXxZmdDailyDistDetailService getKonkaXxZmdDailyDistDetailService() {
		return konkaXxZmdDailyDistDetailService;
	}

	@Resource
	KonkaXxZmdRemitRecService konkaXxZmdRemitRecService;

	@Override
	public KonkaXxZmdRemitRecService getKonkaXxZmdRemitRecService() {
		return konkaXxZmdRemitRecService;
	}

	@Resource
	KonkaXxSystemMessageService konkaXxSystemMessageService;

	@Override
	public KonkaXxSystemMessageService getKonkaXxSystemMessageService() {
		return konkaXxSystemMessageService;
	}

	@Resource
	KonkaXxLogisticsService konkaXxLogisticsService;

	@Resource
	KonkaXxPdPriceLimitApplyService konkaXxPdPriceLimitApplyService;

	@Resource
	KonkaXxZmdSalaryService konkaXxZmdSalaryService;

	@Resource
	KonkaXxZmdCreditApplyService konkaXxZmdCreditApplyService;

	@Resource
	KonkaXxZmdAccDetailsService konkaXxZmdAccDetailsService;

	@Override
	public KonkaXxLogisticsService getKonkaXxLogisticsService() {
		return konkaXxLogisticsService;
	}

	@Override
	public KonkaXxPdPriceLimitApplyService getKonkaXxPdPriceLimitApplyService() {
		return konkaXxPdPriceLimitApplyService;
	}

	@Override
	public KonkaXxZmdSalaryService getKonkaXxZmdSalaryService() {
		return konkaXxZmdSalaryService;
	}

	@Override
	public KonkaXxZmdCreditApplyService getKonkaXxZmdCreditApplyService() {
		return konkaXxZmdCreditApplyService;
	}

	@Override
	public KonkaXxZmdAccDetailsService getKonkaXxZmdAccDetailsService() {
		return konkaXxZmdAccDetailsService;
	}

	@Resource
	KonkaXxDistAccountRecService konkaXxDistAccountRecService;

	@Override
	public KonkaXxDistAccountRecService getKonkaXxDistAccountRecService() {
		return konkaXxDistAccountRecService;
	}

	@Resource
	KonkaXxDistEmployeeService konkaXxDistEmployeeService;

	@Resource
	KonkaXxSellBillCstSatformService konkaXxSellBillCstSatformService;

	@Override
	public KonkaXxDistEmployeeService getKonkaXxDistEmployeeService() {
		return konkaXxDistEmployeeService;
	}

	@Override
	public KonkaXxSellBillCstSatformService getKonkaXxSellBillCstSatformService() {
		return konkaXxSellBillCstSatformService;
	}

	@Resource
	KonkaXxZmdPosRecService konkaXxZmdPosRecService;

	@Override
	public KonkaXxZmdPosRecService getKonkaXxZmdPosRecService() {
		return konkaXxZmdPosRecService;
	}

	@Override
	public KonkaMobileLocationService getKonkaMobileLocationService() {
		return konkaMobileLocationService;
	}

	public void setKonkaMobileLocationService(KonkaMobileLocationService konkaMobileLocationService) {
		this.konkaMobileLocationService = konkaMobileLocationService;
	}

	@Resource
	MvMmtjxcForKonkafightService mvMmtjxcForKonkafightService;

	@Override
	public MvMmtjxcForKonkafightService getMvMmtjxcForKonkafightService() {
		return mvMmtjxcForKonkafightService;
	}

	@Resource
	JxcSellBillAttachmentsService jxcSellBillAttachmentsService;

	@Override
	public JxcSellBillAttachmentsService getJxcSellBillAttachmentsService() {
		return jxcSellBillAttachmentsService;
	}

	@Resource
	JxcJnhmSellBillAuditService jxcJnhmSellBillAuditService;

	@Override
	public JxcJnhmSellBillAuditService getJxcJnhmSellBillAuditService() {
		return jxcJnhmSellBillAuditService;
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-03-13
	 */
	// 康佳专卖店
	@Resource
	KonkaXxZmdAuditUserInfoService konkaXxZmdAuditUserInfoService;

	@Override
	public KonkaXxZmdAuditUserInfoService getKonkaXxZmdAuditUserInfoService() {
		return konkaXxZmdAuditUserInfoService;
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-03-13
	 */
	@Resource
	KonkaXxZmdAuditUserHisService konkaXxZmdAuditUserHisService;

	@Override
	public KonkaXxZmdAuditUserHisService getKonkaXxZmdAuditUserHisService() {
		return konkaXxZmdAuditUserHisService;
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-03-21
	 */
	@Resource
	KonkaXxZmdResService konkaXxZmdResService;

	@Override
	public KonkaXxZmdResService getKonkaXxZmdResService() {
		return konkaXxZmdResService;
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-03-26
	 */
	@Resource
	KonkaXxZmdCreditService konkaXxZmdCreditService;

	@Override
	public KonkaXxZmdCreditService getKonkaXxZmdCreditService() {
		return konkaXxZmdCreditService;
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-04-01
	 */
	@Resource
	KonkaXxPropValItemService konkaXxPropValItemService;

	@Resource
	KonkaXxModelPropsValService konkaXxModelPropsValService;

	@Resource
	KonkaXxPdPropService konkaXxPdPropService;

	@Resource
	KonkaXxPropCategoryService konkaXxPropCategoryService;

	@Override
	public KonkaXxPropValItemService getKonkaXxPropValItemService() {
		return konkaXxPropValItemService;
	}

	@Override
	public KonkaXxModelPropsValService getKonkaXxModelPropsValService() {
		return konkaXxModelPropsValService;
	}

	@Override
	public KonkaXxPdPropService getKonkaXxPdPropService() {
		return konkaXxPdPropService;
	}

	@Override
	public KonkaXxPropCategoryService getKonkaXxPropCategoryService() {
		return konkaXxPropCategoryService;
	}

	@Resource
	KonkaMobileSpRelationService konkaMobileSpRelationService;

	@Override
	public KonkaMobileSpRelationService getKonkaMobileSpRelationService() {
		return konkaMobileSpRelationService;
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-04-19
	 */
	@Resource
	KonkaXxZmdHdApSbService konkaXxZmdHdApSbService;

	@Resource
	KonkaXxZmdSpPlanResService konkaXxZmdSpPlanResService;

	@Override
	public KonkaXxZmdHdApSbService getKonkaXxZmdHdApSbService() {
		return konkaXxZmdHdApSbService;
	}

	@Override
	public KonkaXxZmdSpPlanResService getKonkaXxZmdSpPlanResService() {
		return konkaXxZmdSpPlanResService;
	}

	/**
	 * @author Ren,zhong
	 * @date 2013-04-18
	 */
	@Resource
	KonkaMobileShopStoreService konkaMobileShopStoreService;

	@Override
	public KonkaMobileShopStoreService getKonkaMobileShopStoreService() {
		return konkaMobileShopStoreService;
	}

	@Resource
	KonkaEntpShopService konkaEntpShopService;

	@Override
	public KonkaEntpShopService getKonkaEntpShopService() {
		return konkaEntpShopService;
	}

	@Resource
	KonkaMobileAnnouncementService konkaMobileAnnouncementService;

	@Override
	public KonkaMobileAnnouncementService getKonkaMobileAnnouncementService() {
		return konkaMobileAnnouncementService;
	}

	@Resource
	KonkaMobileTestDataService konkaMobileTestDataService;

	@Resource
	KonkaMobileEquStoreService konkaMobileEquStoreService;

	@Override
	public KonkaMobileTestDataService getKonkaMobileTestDataService() {
		return konkaMobileTestDataService;
	}

	@Override
	public KonkaMobileEquStoreService getKonkaMobileEquStoreService() {
		return konkaMobileEquStoreService;
	}

	@Resource
	KonkaMobileSailDatasService konkaMobileSailDatasService;

	@Override
	public KonkaMobileSailDatasService getKonkaMobileSailDatasService() {
		return konkaMobileSailDatasService;
	}

	@Resource
	KonkaR3StoreService konkaR3StoreService;

	@Override
	public KonkaR3StoreService getKonkaR3StoreService() {
		return konkaR3StoreService;
	}

	@Resource
	KonkamobilesaledatasService konkamobilesaledatasService;

	@Override
	public KonkamobilesaledatasService getKonkamobilesaledatasService() {
		return konkamobilesaledatasService;
	}

	@Resource
	KonkaMobilePopModelService konkaMobilePopModelService;

	@Override
	public KonkaMobilePopModelService getKonkaMobilePopModelService() {
		return konkaMobilePopModelService;
	}

	@Resource
	KonkaMobilePaymentService konkaMobilePaymentService;

	@Resource
	KonkaMobileMdPercentService konkaMobileMdPercentService;

	@Resource
	KonkaMobilePaymentChisService konkaMobilePaymentChisService;

	@Override
	public KonkaMobilePaymentService getKonkaMobilePaymentService() {
		return konkaMobilePaymentService;
	}

	@Override
	public KonkaMobileMdPercentService getKonkaMobileMdPercentService() {
		return konkaMobileMdPercentService;
	}

	@Override
	public KonkaMobilePaymentChisService getKonkaMobilePaymentChisService() {
		return konkaMobilePaymentChisService;
	}

	@Resource
	KonkaMobileHandphonesService konkaMobileHandphonesService;

	@Override
	public KonkaMobileHandphonesService getKonkaMobileHandphonesService() {
		return konkaMobileHandphonesService;
	}

	@Resource
	KonkaOaModuleTypeService konkaOaModuleTypeService;

	@Resource
	KonkaOaModuleContentService konkaOaModuleContentService;

	@Override
	public KonkaOaModuleTypeService getKonkaOaModuleTypeService() {
		return konkaOaModuleTypeService;
	}

	@Override
	public KonkaOaModuleContentService getKonkaOaModuleContentService() {
		return konkaOaModuleContentService;
	}

	@Resource
	KonkaMobileImpDeptService konkaMobileImpDeptService;

	@Resource
	KonkaMobileImpHisService konkaMobileImpHisService;

	@Resource
	KonkaMobileImpStoreService konkaMobileImpStoreService;

	@Resource
	KonkaMobileImpUserService konkaMobileImpUserService;

	@Override
	public KonkaMobileImpDeptService getKonkaMobileImpDeptService() {
		return konkaMobileImpDeptService;
	}

	@Override
	public KonkaMobileImpHisService getKonkaMobileImpHisService() {
		return konkaMobileImpHisService;
	}

	@Override
	public KonkaMobileImpStoreService getKonkaMobileImpStoreService() {
		return konkaMobileImpStoreService;
	}

	@Override
	public KonkaMobileImpUserService getKonkaMobileImpUserService() {
		return konkaMobileImpUserService;
	}

	/**
	 * @author Ren,zhong
	 * @date 2013-05-31
	 */
	@Resource
	KonkaMobilePdService konkaMobilePdService;

	@Resource
	KonkaMobilePdBrandService konkaMobilePdBrandService;

	@Override
	public KonkaMobilePdService getKonkaMobilePdService() {
		return konkaMobilePdService;
	}

	@Override
	public KonkaMobilePdBrandService getKonkaMobilePdBrandService() {
		return konkaMobilePdBrandService;
	}

	/**
	 * @author Wu,ShangLong
	 * @date 2013-06-8
	 */
	@Resource
	JBillService jBillService;

	@Resource
	JBillDetailsService jBillDetailsService;

	@Resource
	JStocksService jStocksService;

	@Resource
	JStocksVerifyService jStocksVerifyService;

	@Resource
	JBaseStoreService jBaseStoreService;

	@Resource
	JBaseTypeService jBaseTypeService;

	@Resource
	JBaseGoodsService jBaseGoodsService;

	@Resource
	JBasePartnerService jBasePartnerService;

	@Resource
	JBaseGoodsInitStockService jBaseGoodsInitStockService;

	@Override
	public JBillService getJBillService() {
		return jBillService;
	}

	@Override
	public JBillDetailsService getJBillDetailsService() {
		return jBillDetailsService;
	}

	@Override
	public JStocksService getJStocksService() {
		return jStocksService;
	}

	@Override
	public JStocksVerifyService getJStocksVerifyService() {
		return jStocksVerifyService;
	}

	@Override
	public JBaseStoreService getJBaseStoreService() {
		return jBaseStoreService;
	}

	@Override
	public JBaseTypeService getJBaseTypeService() {
		return jBaseTypeService;
	}

	@Override
	public JBaseGoodsService getJBaseGoodsService() {
		return jBaseGoodsService;
	}

	@Override
	public JBasePartnerService getJBasePartnerService() {
		return jBasePartnerService;
	}

	@Resource
	JSubSellRecService jSubSellRecService;

	@Override
	public JSubSellRecService getJSubSellRecService() {
		return jSubSellRecService;
	}

	@Resource
	JStocksStackService jStocksStackService;

	@Override
	public JStocksStackService getJStocksStackService() {
		return jStocksStackService;
	}

	// 积分系统
	@Resource
	JfRuleService jfRuleService;

	@Resource
	JfScortsService jfScortsService;

	@Resource
	JfScortsDetailsService jfScortsDetailsService;

	@Resource
	JfScortsExchangeService jfScortsExchangeService;

	@Override
	public JfRuleService getJfRuleService() {
		return jfRuleService;
	}

	@Override
	public JfScortsService getJfScortsService() {
		return jfScortsService;
	}

	@Override
	public JfScortsDetailsService getJfScortsDetailsService() {
		return jfScortsDetailsService;
	}

	@Override
	public JfScortsExchangeService getJfScortsExchangeService() {
		return jfScortsExchangeService;
	}

	@Resource
	MemberInfoService memberInfoService;

	@Override
	public MemberInfoService getMemberInfoService() {
		return memberInfoService;
	}

	@Resource
	MemberChangeCardInfoService memberChangeCardInfoService;

	@Override
	public MemberChangeCardInfoService getMemberChangeCardInfoService() {
		return memberChangeCardInfoService;
	}

	@Resource
	JfScortsDetailsHisService jfScortsDetailsHisService;

	@Override
	public JfScortsDetailsHisService getJfScortsDetailsHisService() {
		return jfScortsDetailsHisService;
	}

	// GPS
	@Resource
	KonkaMobileUserGpsService konkaMobileUserGpsService;

	@Resource
	KonkaMobileUserGpsTrackService konkaMobileUserGpsTrackService;

	@Override
	public KonkaMobileUserGpsService getKonkaMobileUserGpsService() {
		return konkaMobileUserGpsService;
	}

	@Override
	public KonkaMobileUserGpsTrackService getKonkaMobileUserGpsTrackService() {
		return konkaMobileUserGpsTrackService;
	}

	/**
	 * @author Ren,zhong
	 * @date 2013-06-15
	 */
	@Resource
	KonkaCustomerPublicService konkaCustomerPublicService;

	@Override
	public KonkaCustomerPublicService getKonkaCustomerPublicService() {
		return konkaCustomerPublicService;
	}

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-6-24
	 */
	@Resource
	BaseReportService baseReportService;

	@Override
	public BaseReportService getBaseReportService() {
		return baseReportService;
	}

	@Resource
	KonkaR3OrderHeaderService konkaR3OrderHeaderService;

	@Override
	public KonkaR3OrderHeaderService getKonkaR3OrderHeaderService() {
		return konkaR3OrderHeaderService;
	}

	@Resource
	KonkaR3OrderLinesService konkaR3OrderLinesService;

	@Override
	public KonkaR3OrderLinesService getKonkaR3OrderLinesService() {
		return konkaR3OrderLinesService;
	}

	@Resource
	R3WebInterfaceService r3WebInterfaceService;

	@Override
	public R3WebInterfaceService getR3WebInterfaceService() {
		return r3WebInterfaceService;
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-6-25 R3客戶类别
	 */
	@Resource
	KonkaR3CustomerTypeService konkaR3CustomerTypeService;

	@Override
	public KonkaR3CustomerTypeService getKonkaR3CustomerTypeService() {
		return konkaR3CustomerTypeService;
	}

	@Resource
	KonkaCategoryService konkaCategoryService;

	@Override
	public KonkaCategoryService getKonkaCategoryService() {
		return konkaCategoryService;
	}

	/**
	 * @author pangang
	 * @date 2013-6-26
	 */
	@Resource
	JfGiftInfoService jfGiftInfoService;

	@Override
	public JfGiftInfoService getJfGiftInfoService() {
		return jfGiftInfoService;
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-07-04 R3客戶类别
	 */
	@Resource
	KonkaR3ShopCreditService konkaR3ShopCreditService;

	@Override
	public KonkaR3ShopCreditService getKonkaR3ShopCreditService() {
		return konkaR3ShopCreditService;
	}

	/**
	 * @author Ren,zhong
	 * @date 2013-07-03
	 * @desc 商品管理库
	 */
	@Resource
	KonkaBcompPdService konkaBcompPdService;

	@Override
	public KonkaBcompPdService getKonkaBcompPdService() {
		return konkaBcompPdService;
	}

	@Resource
	VADefailsOfConsumerService vADefailsOfConsumerService;

	@Override
	public VADefailsOfConsumerService getVADefailsOfConsumerService() {
		return vADefailsOfConsumerService;
	}

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-7-9
	 * @desc 任务系数管理
	 */
	@Resource
	KonkaPlanRatioService konkaPlanRatioService;

	@Resource
	KonkaPlanMoneyService konkaPlanMoneyService;

	@Override
	public KonkaPlanRatioService getKonkaPlanRatioService() {
		return konkaPlanRatioService;
	}

	@Override
	public KonkaPlanMoneyService getKonkaPlanMoneyService() {
		return konkaPlanMoneyService;
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-07-11
	 * @desc 群组
	 */
	@Resource
	KonkaPeArticleUserGroupService konkaPeArticleUserGroupService;

	@Resource
	KonkaPeArticleGUsersService konkaPeArticleGUsersService;

	@Override
	public KonkaPeArticleUserGroupService getKonkaPeArticleUserGroupService() {
		return konkaPeArticleUserGroupService;
	}

	@Override
	public KonkaPeArticleGUsersService getKonkaPeArticleGUsersService() {
		return konkaPeArticleGUsersService;
	}

	/**
	 * @author ZHOU
	 * @date 2013-07-12
	 * @desc 客户分类
	 */
	@Resource
	KonkaCategoryTypeService konkaCategoryTypeService;

	@Resource
	KonkaCategoryTempService konkaCategoryTempService;

	@Override
	public KonkaCategoryTypeService getKonkaCategoryTypeService() {
		return konkaCategoryTypeService;
	}

	@Override
	public KonkaCategoryTempService getKonkaCategoryTempService() {
		return konkaCategoryTempService;
	}

	@Resource
	ArticleImgService articleImgService;

	@Override
	public ArticleImgService getArticleImgService() {
		return articleImgService;
	}

	@Resource
	AttachmentService attachmentService;

	@Override
	public AttachmentService getAttachmentService() {
		return attachmentService;
	}

	/**
	 * @author Hu,Hao
	 * @date 2013-07-25
	 * @desc 工程预算
	 */
	@Resource
	KonkaXxZmdGcysService konkaXxZmdGcysService;

	@Override
	public KonkaXxZmdGcysService getKonkaXxZmdGcysService() {
		return konkaXxZmdGcysService;
	}

	/**
	 * @author ldy
	 * @date 2013-07-25
	 * @desc 现款价管理
	 */
	@Resource
	KonkaPdModelPricesService konkaPdModelPricesService;

	@Override
	public KonkaPdModelPricesService getKonkaPdModelPricesService() {
		return konkaPdModelPricesService;
	}

	/**
	 * @author ldy
	 * @date 2013-11-18
	 * @desc 客户基础数据
	 */
	@Resource
	KonkaCustomerKnvpService konkaCustomerKnvpService;

	@Override
	public KonkaCustomerKnvpService getKonkaCustomerKnvpService() {
		return konkaCustomerKnvpService;
	}

	/**
	 * @author Ren,zhong
	 * @date 2013-07-26
	 * @desc 员工待遇管理
	 */
	@Resource
	KonkaPersonWageService konkaPersonWageService;

	@Resource
	KonkaPersonPwdService konkaPersonPwdService;

	@Override
	public KonkaPersonWageService getKonkaPersonWageService() {
		return konkaPersonWageService;
	}

	@Override
	public KonkaPersonPwdService getKonkaPersonPwdService() {
		return konkaPersonPwdService;
	}

	/**
	 * @author Hu,Hao
	 * @date 2013-07-27
	 * @desc 专卖店审核流程节点
	 */
	@Resource
	KonkaXxAuditNoteService konkaXxAuditNoteService;

	@Override
	public KonkaXxAuditNoteService getKonkaXxAuditNoteService() {
		return konkaXxAuditNoteService;
	}

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-8-1
	 * @desc 查询条件保存
	 */
	@Resource
	SearchFiltersService searchFiltersService;

	@Override
	public SearchFiltersService getSearchFiltersService() {
		return searchFiltersService;
	}

	/**
	 * @author pan,gang
	 * @date 2013-8-9
	 * @Description: 产品展示端
	 */
	@Resource
	PshowOrdeAuditService pshowOrdeAuditService;

	@Override
	public PshowOrdeAuditService getPshowOrdeAuditService() {
		return pshowOrdeAuditService;
	}

	@Resource
	PshowOrdeDetailsService pshowOrdeDetailsService;

	@Override
	public PshowOrdeDetailsService getPshowOrdeDetailsService() {
		return pshowOrdeDetailsService;
	}

	@Resource
	PshowOrderService pshowOrderService;

	@Override
	public PshowOrderService getPshowOrderService() {
		return pshowOrderService;
	}

	@Resource
	KonkaBcompPdContentService konkaBcompPdContentService;

	@Override
	public KonkaBcompPdContentService getKonkaBcompPdContentService() {
		return konkaBcompPdContentService;
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-08-23
	 * @desc R3销售组织
	 */
	@Resource
	KonkaSalesDeptService konkaSalesDeptService;

	@Override
	public KonkaSalesDeptService getKonkaSalesDeptService() {
		return konkaSalesDeptService;
	}

	/**
	 * @author ldy
	 * @version 2013-11-14
	 * @desc R3销售组织100开头部分
	 */
	@Resource
	KonkaSalesDept2Service konkaSalesDept2Service;

	@Override
	public KonkaSalesDept2Service getKonkaSalesDept2Service() {
		return konkaSalesDept2Service;
	}

	@Resource
	KonkaR3ZsofService konkaR3ZsofService;

	@Override
	public KonkaR3ZsofService getKonkaR3ZsofService() {
		return konkaR3ZsofService;
	}

	@Resource
	KonkaDeptTreeService konkaDeptTreeService;

	@Override
	public KonkaDeptTreeService getKonkaDeptTreeService() {
		return konkaDeptTreeService;
	}

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-08-28
	 * @desc 客户联系人信息表
	 */
	@Resource
	KonkaR3ShopLinkService konkaR3ShopLinkService;

	@Override
	public KonkaR3ShopLinkService getKonkaR3ShopLinkService() {
		return konkaR3ShopLinkService;
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-09-02
	 * @desc 角色数据查询权限
	 */
	@Resource
	KonkaRoleDataLevelService konkaRoleDataLevelService;

	@Override
	public KonkaRoleDataLevelService getKonkaRoleDataLevelService() {
		return konkaRoleDataLevelService;
	}

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-09-08
	 * @desc 零售明细查询视图
	 */
	@Resource
	VADetailsOfSalesDataService vADetailsOfSalesDataService;

	@Override
	public VADetailsOfSalesDataService getVADetailsOfSalesDataService() {
		return vADetailsOfSalesDataService;
	}

	@Resource
	EcBaseStoreService ecBaseStoreService;

	@Resource
	EcBcompTypeService ecBcompTypeService;

	@Resource
	EcGiftContentService ecGiftContentService;

	@Resource
	EcGiftOrdeService ecGiftOrdeService;

	@Resource
	EcGiftService ecGiftService;

	@Resource
	EcGoodsPriceAreaService ecGoodsPriceAreaService;

	@Resource
	EcGoodsPriceService ecGoodsPriceService;

	@Resource
	EcInWarehouseService ecInWarehouseService;

	@Resource
	EcPdEavlService ecPdEavlService;

	@Resource
	EcQaInfoService ecQaInfoService;

	@Resource
	EcShoppingCartService ecShoppingCartService;

	@Resource
	EcStocksService ecStocksService;

	@Resource
	EcStoreAreaService ecStoreAreaService;

	@Resource
	EcUserAddrsService ecUserAddrsService;

	@Resource
	EcUserFavotritesService ecUserFavotritesService;

	@Resource
	EcUserScoreRecService ecUserScoreRecService;

	@Resource
	EcUserService ecUserService;

	@Override
	public EcBaseStoreService getEcBaseStoreService() {
		return ecBaseStoreService;
	}

	@Override
	public EcBcompTypeService getEcBcompTypeService() {
		return ecBcompTypeService;
	}

	@Override
	public EcGiftContentService getEcGiftContentService() {
		return ecGiftContentService;
	}

	@Override
	public EcGiftOrdeService getEcGiftOrdeService() {
		return ecGiftOrdeService;
	}

	@Override
	public EcGiftService getEcGiftService() {
		return ecGiftService;
	}

	@Override
	public EcGoodsPriceAreaService getEcGoodsPriceAreaService() {
		return ecGoodsPriceAreaService;
	}

	@Override
	public EcGoodsPriceService getEcGoodsPriceService() {
		return ecGoodsPriceService;
	}

	@Override
	public EcInWarehouseService getEcInWarehouseService() {
		return ecInWarehouseService;
	}

	@Override
	public EcPdEavlService getEcPdEavlService() {
		return ecPdEavlService;
	}

	@Override
	public EcQaInfoService getEcQaInfoService() {
		return ecQaInfoService;
	}

	@Override
	public EcShoppingCartService getEcShoppingCartService() {
		return ecShoppingCartService;
	}

	@Override
	public EcStocksService getEcStocksService() {
		return ecStocksService;
	}

	@Override
	public EcStoreAreaService getEcStoreAreaService() {
		return ecStoreAreaService;
	}

	@Override
	public EcUserAddrsService getEcUserAddrsService() {
		return ecUserAddrsService;
	}

	@Override
	public EcUserFavotritesService getEcUserFavotritesService() {
		return ecUserFavotritesService;
	}

	@Override
	public EcUserScoreRecService getEcUserScoreRecService() {
		return ecUserScoreRecService;
	}

	@Override
	public EcUserService getEcUserService() {
		return ecUserService;
	}

	@Resource
	EcBcompPdRebatesService ecBcompPdRebatesService;

	@Override
	public EcBcompPdRebatesService getEcBcompPdRebatesService() {
		return ecBcompPdRebatesService;
	}

	@Resource
	EcBindingPdService ecBindingPdService;

	@Resource
	EcBindingPdOrdeDetailsService ecBindingPdOrdeDetailsService;

	@Resource
	EcBcompBindingService ecBcompBindingService;

	@Resource
	EcOutWarehouseService ecOutWarehouseService;

	@Override
	public EcBindingPdService getEcBindingPdService() {
		return ecBindingPdService;
	}

	@Override
	public EcBindingPdOrdeDetailsService getEcBindingPdOrdeDetailsService() {
		return ecBindingPdOrdeDetailsService;
	}

	@Override
	public EcBcompBindingService getEcBcompBindingService() {
		return ecBcompBindingService;
	}

	@Override
	public EcOutWarehouseService getEcOutWarehouseService() {
		return ecOutWarehouseService;
	}

	@Resource
	GlobalIpGeoLibService globalIpGeoLibService;

	@Override
	public GlobalIpGeoLibService getGlobalIpGeoLibService() {
		return globalIpGeoLibService;
	}

	@Resource
	EcBaseExpressService ecBaseExpressService;

	@Override
	public EcBaseExpressService getEcBaseExpressService() {
		return ecBaseExpressService;
	}

	@Resource
	EcBasePayAccountService ecBasePayAccountService;

	@Override
	public EcBasePayAccountService getEcBasePayAccountService() {
		return ecBasePayAccountService;
	}

	@Resource
	EcBaseExpressReachDayService ecBaseExpressReachDayService;

	@Override
	public EcBaseExpressReachDayService getEcBaseExpressReachDayService() {
		return ecBaseExpressReachDayService;
	}

	@Resource
	KonkaR3DeptStoreService konkaR3DeptStoreService;

	@Override
	public KonkaR3DeptStoreService getKonkaR3DeptStoreService() {
		return konkaR3DeptStoreService;
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-09-27
	 * @desc 工程机管理--文件下发
	 */
	@Resource
	KonkaEmFileService konkaEmFileService;

	@Resource
	KonkaEmFileContentService konkaEmFileContentService;

	@Resource
	KonkaEmFileReceiveUserService konkaEmFileReceiveUserService;

	@Override
	public KonkaEmFileService getKonkaEmFileService() {
		return konkaEmFileService;
	}

	@Override
	public KonkaEmFileContentService getKonkaEmFileContentService() {
		return konkaEmFileContentService;
	}

	@Override
	public KonkaEmFileReceiveUserService getKonkaEmFileReceiveUserService() {
		return konkaEmFileReceiveUserService;
	}

	@Resource
	EcGoodsPriceModifyForToucService ecGoodsPriceModifyForToucService;

	@Override
	public EcGoodsPriceModifyForToucService getEcGoodsPriceModifyForToucService() {
		return ecGoodsPriceModifyForToucService;
	}

	@Resource
	EcGoodsStockModifyForToucService ecGoodsStockModifyForToucService;

	@Override
	public EcGoodsStockModifyForToucService getEcGoodsStockModifyForToucService() {
		return ecGoodsStockModifyForToucService;
	}

	@Resource
	KonkaProSqService konkaProSqService;

	@Override
	public KonkaProSqService getKonkaProSqService() {
		return konkaProSqService;
	}

	@Resource
	FighterInfoService fighterInfoService;

	@Override
	public FighterInfoService getFighterInfoService() {
		return fighterInfoService;
	}

	/**
	 * @version 2013-11-02
	 * @desc 邮件发送
	 */
	@Resource
	KonkaSendMailInfoService konkaSendMailInfoService;

	@Resource
	KonkaSendMailUserService konkaSendMailUserService;

	@Override
	public KonkaSendMailInfoService getKonkaSendMailInfoService() {
		return konkaSendMailInfoService;
	}

	@Override
	public KonkaSendMailUserService getKonkaSendMailUserService() {
		return konkaSendMailUserService;
	}

	@Resource
	UserChangeInfoService userChangeInfoService;

	@Override
	public UserChangeInfoService getUserChangeInfoService() {
		return userChangeInfoService;
	}

	@Resource
	KonkaActInfoService konkaActInfoService;

	@Override
	public KonkaActInfoService getKonkaActInfoService() {
		return konkaActInfoService;
	}

	@Resource
	GiftInfoService giftInfoService;

	@Override
	public GiftInfoService getGiftInfoService() {
		return giftInfoService;
	}

	/**
	 * 会员信息
	 */
	@Resource
	EcBaseCardTypeService ecBaseCardTypeService;

	@Resource
	EcBaseCardNoService ecBaseCardNoService;

	@Resource
	EcBaseCardChangeInfoService ecBaseCardChangeInfoService;

	@Resource
	EcBaseCardLevelService ecBaseCardLevelService;

	@Override
	public EcBaseCardTypeService getEcBaseCardTypeService() {
		return ecBaseCardTypeService;
	}

	@Override
	public EcBaseCardNoService getEcBaseCardNoService() {
		return ecBaseCardNoService;
	}

	@Override
	public EcBaseCardChangeInfoService getEcBaseCardChangeInfoService() {
		return ecBaseCardChangeInfoService;
	}

	@Override
	public EcBaseCardLevelService getEcBaseCardLevelService() {
		return ecBaseCardLevelService;
	}

	@Override
	public ChannelDataImport2Service getChannelDataImport2Service() {

		return channelDataImport2Service;
	}

	@Resource
	EcBcompPdUpService ecBcompPdUpService;

	@Override
	public EcBcompPdUpService getEcBcompPdUpService() {
		return ecBcompPdUpService;
	}

	@Resource
	KonkaStoreUserCInfoService konkaStoreUserCInfoService;

	@Override
	public KonkaStoreUserCInfoService getKonkaStoreUserCInfoService() {
		return konkaStoreUserCInfoService;
	}

	@Resource
	KonkaPindexAreaGdpService konkaPindexAreaGdpService;

	@Override
	public KonkaPindexAreaGdpService getKonkaPindexAreaGdpService() {
		return konkaPindexAreaGdpService;
	}

	@Resource
	KonkaR3ShopTaskService konkaR3ShopTaskService;

	@Override
	public KonkaR3ShopTaskService getKonkaR3ShopTaskService() {
		return konkaR3ShopTaskService;
	}

	@Resource
	KonkaMobileImpSailDataService konkaMobileImpSailDataService;

	@Override
	public KonkaMobileImpSailDataService getKonkaMobileImpSailDataService() {
		return konkaMobileImpSailDataService;
	}

	@Resource
	KonkaR3DeptStockInfoService konkaR3DeptStockInfoService;

	@Override
	public KonkaR3DeptStockInfoService getKonkaR3DeptStockInfoService() {
		return konkaR3DeptStockInfoService;
	}

	@Resource
	UserLoginErrLogsService userLoginErrLogsService;

	@Override
	public UserLoginErrLogsService getUserLoginErrLogsService() {
		return userLoginErrLogsService;
	}

	@Resource
	KonkaDeptJbTaskService konkaDeptJbTaskService;

	@Override
	public KonkaDeptJbTaskService getKonkaDeptJbTaskService() {
		return konkaDeptJbTaskService;
	}

	@Resource
	EcOrderExpressInfoService ecOrderExpressInfoService;

	@Resource
	EcBuyInfoService ecBuyInfoService;

	@Resource
	EcCashPriceService ecCashPriceService;

	@Override
	public EcOrderExpressInfoService getEcOrderExpressInfoService() {
		return ecOrderExpressInfoService;
	}

	@Override
	public EcBuyInfoService getEcBuyInfoService() {
		return ecBuyInfoService;
	}

	@Override
	public EcCashPriceService getEcCashPriceService() {
		return ecCashPriceService;
	}

	@Resource
	KonkaZles23ResultInfoService konkaZles23ResultInfoService;

	@Override
	public KonkaZles23ResultInfoService getKonkaZles23ResultInfoService() {
		return konkaZles23ResultInfoService;
	}

	@Resource
	KonkaBbZj98ImportService konkaBbZj98ImportService;

	@Override
	public KonkaBbZj98ImportService getKonkaBbZj98ImportService() {
		return konkaBbZj98ImportService;
	}

	@Resource
	KonkaBbItr2ImportService konkaBbItr2ImportService;

	@Override
	public KonkaBbItr2ImportService getKonkaBbItr2ImportService() {
		return konkaBbItr2ImportService;
	}

	@Resource
	KonkaPdModelTaskService konkaPdModelTaskService;

	@Override
	public KonkaPdModelTaskService getKonkaPdModelTaskService() {
		return konkaPdModelTaskService;
	}

	@Resource
	ChannelDataImport3Service channelDataImport3Service;

	@Override
	public ChannelDataImport3Service getChannelDataImport3Service() {
		return channelDataImport3Service;
	}

	@Resource
	EcVouchersService ecVouchersService;

	@Resource
	EcVouchersTypeService ecVouchersTypeService;

	@Override
	public EcVouchersService getEcVouchersService() {
		return ecVouchersService;
	}

	@Override
	public EcVouchersTypeService getEcVouchersTypeService() {
		return ecVouchersTypeService;
	}

	/**
	 * @author Liu,ZhiXiang
	 * @version 2014-01-11
	 * @desc 进销存月度结算
	 */
	@Resource
	JsTimesService jsTimesService;

	@Resource
	JsStocksService jsStocksService;

	@Resource
	JsSellsService jsSellsService;

	@Override
	public JsTimesService getJsTimesService() {
		return jsTimesService;
	}

	@Override
	public JsStocksService getJsStocksService() {
		return jsStocksService;
	}

	@Override
	public JsSellsService getJsSellsService() {
		return jsSellsService;
	}

	@Resource
	AutoRunService autoRunService;

	@Override
	public AutoRunService getAutoRunService() {
		return autoRunService;
	}

	@Resource
	EcBackPasswordService ecBackPasswordService;

	@Override
	public EcBackPasswordService getEcBackPasswordService() {
		return ecBackPasswordService;
	}

	@Resource
	EcUserLevelAuditInfoService ecUserLevelAuditInfoService;

	@Override
	public EcUserLevelAuditInfoService getEcUserLevelAuditInfoService() {
		return ecUserLevelAuditInfoService;
	}

	@Resource
	KonkaDeptR3PdService konkaDeptR3PdService;

	@Override
	public KonkaDeptR3PdService getKonkaDeptR3PdService() {
		return konkaDeptR3PdService;
	}

	/**
	 * @author Hu,Hao
	 * @version 2014-01-17
	 * @desc 单日结算
	 */

	@Resource
	KonkaDeptJsMoneyService konkaDeptJsMoneyService;

	@Override
	public KonkaDeptJsMoneyService getKonkaDeptJsMoneyService() {
		return konkaDeptJsMoneyService;
	}

	@Resource
	KonkaSpActivityManagerService konkaSpActivityManagerService;

	@Resource
	KonkaSpMdTypeService konkaSpMdTypeService;

	@Resource
	KonkaSpActivityTypeService konkaSpActivityTypeService;

	@Resource
	KonkaSpTaskService konkaSpTaskService;

	@Resource
	KonkaOrderMeetingManagerService konkaOrderMeetingManagerService;

	@Resource
	KonkaOrderMeetingCustomerService konkaOrderMeetingCustomerService;

	@Override
	public KonkaSpActivityManagerService getKonkaSpActivityManagerService() {
		return konkaSpActivityManagerService;
	}

	@Override
	public KonkaSpMdTypeService getKonkaSpMdTypeService() {
		return konkaSpMdTypeService;
	}

	@Override
	public KonkaSpActivityTypeService getKonkaSpActivityTypeService() {
		return konkaSpActivityTypeService;
	}

	@Override
	public KonkaSpTaskService getKonkaSpTaskService() {
		return konkaSpTaskService;
	}

	@Override
	public KonkaOrderMeetingManagerService getKonkaOrderMeetingManagerService() {
		return konkaOrderMeetingManagerService;
	}

	@Override
	public KonkaOrderMeetingCustomerService getKonkaOrderMeetingCustomerService() {
		return konkaOrderMeetingCustomerService;
	}

	@Resource
	KonkaSpListService konkaSpListService;

	@Override
	public KonkaSpListService getKonkaSpListService() {
		return konkaSpListService;
	}

	@Resource
	KonkaSpMdSailService konkaSpMdSailService;

	@Override
	public KonkaSpMdSailService getKonkaSpMdSailService() {
		return konkaSpMdSailService;
	}

	/**
	 * @author Hu,Hao
	 * @version 2014-02-12
	 * @desc 协同办公文件类型
	 */
	@Resource
	FileReportTypeService fileReportTypeService;

	@Override
	public FileReportTypeService getFileReportTypeService() {
		return fileReportTypeService;
	}

	/**
	 * @author Hu,Hao
	 * @version 2014-02-21
	 * @desc 合同管理
	 */

	@Resource
	KonkaContractManagerService konkaContractManagerService;

	@Resource
	KonkaContractAuditRecordService konkaContractAuditRecordService;

	@Override
	public KonkaContractManagerService getKonkaContractManagerService() {
		return konkaContractManagerService;
	}

	@Override
	public KonkaContractAuditRecordService getKonkaContractAuditRecordService() {
		return konkaContractAuditRecordService;
	}

	@Resource
	KonkaBbYjhbService konkaBbYjhbService;

	@Override
	public KonkaBbYjhbService getKonkaBbYjhbService() {
		return konkaBbYjhbService;
	}

	@Resource
	EcExtendService ecExtendService;

	@Override
	public EcExtendService getEcExtendService() {
		return ecExtendService;
	}

	@Resource
	EcMessageService ecMessageService;

	@Override
	public EcMessageService getEcMessageService() {
		return ecMessageService;
	}

	@Resource
	KonkaJxcBaseAddrService konkaJxcBaseAddrService;

	@Override
	public KonkaJxcBaseAddrService getKonkaJxcBaseAddrService() {
		return konkaJxcBaseAddrService;
	}

	@Resource
	CrmPriceHeaderService crmPriceHeaderService;

	@Resource
	CrmCustomerGroupService crmCustomerGroupService;

	@Resource
	CrmPriceLinesService crmPriceLinesService;

	@Resource
	CrmCustomerGroupDetailsService crmCustomerGroupDetailsService;

	@Override
	public CrmCustomerGroupService getCrmCustomerGroupService() {
		return crmCustomerGroupService;
	}

	@Override
	public CrmPriceHeaderService getCrmPriceHeaderService() {
		return crmPriceHeaderService;
	}

	@Override
	public CrmPriceLinesService getCrmPriceLinesService() {
		return crmPriceLinesService;
	}

	@Override
	public CrmCustomerGroupDetailsService getCrmCustomerGroupDetailsService() {
		return crmCustomerGroupDetailsService;
	}

	@Override
	public KonkaPriceManagerService getKonkaPriceManagerService() {
		return konkaPriceManagerService;
	}

	@Override
	public KonkaPartershipService getKonkaPartershipService() {
		return konkaPartershipService;
	}

	/*
	 * @ WangKunLindate 2014-4-16
	 */
	@Resource
	KonkaOrderInfoMessageSendService konkaOrderInfoMessageSendService;

	@Override
	public KonkaOrderInfoMessageSendService getKonkaOrderInfoMessageSendService() {
		return konkaOrderInfoMessageSendService;
	}

	@Resource
	SfPindexService sfPindexService;

	@Override
	public SfPindexService getSfPindexService() {
		return sfPindexService;
	}

	@Resource
	KonkaR3StoreShowService konkaR3StoreShowService;

	@Override
	public KonkaR3StoreShowService getKonkaR3StoreShowService() {
		return konkaR3StoreShowService;
	}

	@Resource
	KonkaR3StoreTaskService konkaR3StoreTaskService;

	@Override
	public KonkaR3StoreTaskService getKonkaR3StoreTaskService() {
		return konkaR3StoreTaskService;
	}

	@Resource
	EcRuleGoodsService ecRuleGoodsService;

	@Resource
	EcRuleService ecRuleService;

	@Override
	public EcRuleGoodsService getEcRuleGoodsService() {
		return ecRuleGoodsService;
	}

	@Override
	public EcRuleService getEcRuleService() {
		return ecRuleService;
	}

	@Resource
	EcSelfAreaService ecSelfAreaService;

	// 自提
	@Override
	public EcSelfAreaService getEcSelfAreaService() {
		return ecSelfAreaService;
	}

	@Resource
	EcDispatchAreaService ecDispatchAreaService;

	// 二次配送
	@Override
	public EcDispatchAreaService getEcDispatchAreaService() {
		return ecDispatchAreaService;
	}

	// 客户端首页商品管理
	@Override
	public KonkaVipPdManageService getKonkaVipPdManageService() {
		return konkaVipPdManageService;
	}

	@Override
	public KonkaOrderInfoTransService getKonkaOrderInfoTransService() {
		return konkaOrderInfoTransService;
	}

	@Override
	public KonkaOrderInfoTransDetailsService getKonkaOrderInfoTransDetailsService() {
		return konkaOrderInfoTransDetailsService;
	}

	@Override
	public KonkaOrderInfoTransEnsuService getKonkaOrderInfoTransEnsuService() {
		return konkaOrderInfoTransEnsuService;
	}

	@Resource
	ChannelDataImportReportService channelDataImportReportService;

	@Override
	public ChannelDataImportReportService getChannelDataImportReportService() {
		return channelDataImportReportService;
	}

	@Resource
	EcSpecFbCalService ecSpecFbCalService;

	@Resource
	EcSpecFbTeamListService ecSpecFbTeamListService;

	@Resource
	EcSpecFbUgdetailService ecSpecFbUgdetailService;

	@Override
	public EcSpecFbCalService getEcSpecFbCalService() {
		return ecSpecFbCalService;
	}

	@Resource
	JxcOutInDetailService jxcOutInDetailService;

	@Override
	public JxcOutInDetailService getJxcOutInDetailService() {
		return jxcOutInDetailService;
	}

	@Override
	public JBaseGoodsInitStockService getJBaseGoodsInitStockService() {
		return jBaseGoodsInitStockService;
	}

	@Override
	public EcSpecFbTeamListService getEcSpecFbTeamListService() {
		return ecSpecFbTeamListService;
	}

	@Override
	public EcSpecFbUgdetailService getEcSpecFbUgdetailService() {
		return ecSpecFbUgdetailService;
	}

	@Override
	public JBaseStoreR3Service getJBaseStoreR3Service() {
		return jBaseStoreR3Service;
	}

	@Override
	public KonkaOrderMessageProcessService getKonkaOrderMessageProcessService() {
		return konkaOrderMessageProcessService;
	}

	@Resource
	KonkaR3OrderService konkaR3OrderService;

	@Override
	public KonkaR3OrderService getKonkaR3OrderService() {
		return konkaR3OrderService;
	}

	@Resource
	PshowOrdeExchangeService pshowOrdeExchangeService;

	@Override
	public PshowOrdeExchangeService getPshowOrdeExchangeService() {
		return pshowOrdeExchangeService;
	}

	@Resource
	KonkaR3ShopDevService konkaR3ShopDevService;

	@Override
	public KonkaR3ShopDevService getKonkaR3ShopDevService() {
		return konkaR3ShopDevService;
	}

	@Resource
	KonkaMobileVisitPlanService konkaMobileVisitPlanService;

	@Resource
	KonkaMobileVisitPlanDetailService konkaMobileVisitPlanDetailService;

	@Override
	public KonkaMobileVisitPlanService getKonkaMobileVisitPlanService() {
		return konkaMobileVisitPlanService;
	}

	@Override
	public KonkaMobileVisitPlanDetailService getKonkaMobileVisitPlanDetailService() {
		return konkaMobileVisitPlanDetailService;
	}

	@Resource
	EcGiftCommService ecGiftCommService;

	@Override
	public EcGiftCommService getEcGiftCommService() {
		return ecGiftCommService;
	}

	@Resource
	EcSenderInfoService ecSenderInfoService;

	@Resource
	EcSenderOrderInfoService ecSenderOrderInfoService;

	@Override
	public EcSenderInfoService getEcSenderInfoService() {
		return ecSenderInfoService;
	}

	@Override
	public EcSenderOrderInfoService getEcSenderOrderInfoService() {
		return ecSenderOrderInfoService;
	}

	@Resource
	VOrgOfDeptService vOrgOfDeptService;

	@Override
	public VOrgOfDeptService getVOrgOfDeptService() {
		return vOrgOfDeptService;
	}

	@Resource
	EcLuckyBuyService ecLuckyBuyService;

	@Resource
	EcLuckyTimeService ecLuckyTimeService;

	@Resource
	EcLuckyMainService ecLuckyMainService;

	@Resource
	EcOrderPayService ecOrderPayService;

	@Override
	public EcLuckyBuyService getEcLuckyBuyService() {
		return ecLuckyBuyService;
	}

	@Override
	public EcLuckyTimeService getEcLuckyTimeService() {
		return ecLuckyTimeService;
	}

	@Override
	public EcLuckyMainService getEcLuckyMainService() {
		return ecLuckyMainService;
	}

	@Override
	public EcOrderPayService getEcOrderPayService() {
		return ecOrderPayService;
	}

	@Resource
	EcBaseCardApplyService ecBaseCardApplyService;

	@Override
	public EcBaseCardApplyService getEcBaseCardApplyService() {
		return ecBaseCardApplyService;
	}

	@Resource
	TaskContentService taskContentService;

	@Resource
	TaskParticipantService taskParticipantService;

	@Override
	public TaskContentService getTaskContentService() {
		return taskContentService;
	}

	@Override
	public TaskParticipantService getTaskParticipantService() {
		return taskParticipantService;
	}

	@Override
	public VOrgOfCustomerService getVOrgOfCustomerService() {
		return vOrgOfCustomerService;
	}

	@Resource
	EcRuleBingdingDetailService ecRuleBingdingDetailService;

	@Override
	public EcRuleBingdingDetailService getEcRuleBingdingDetailService() {
		return ecRuleBingdingDetailService;
	}

	@Resource
	com.ebiz.mmt.service.IosPushMessageService iosPushMessageService;

	@Override
	public com.ebiz.mmt.service.IosPushMessageService getIosPushMessageService() {
		return iosPushMessageService;
	}

	// 需求申请
	@Resource
	KonkaSysAplicationService konkaSysAplicationService;

	@Override
	public KonkaSysAplicationService getKonkaSysAplicationService() {
		return konkaSysAplicationService;
	}

	@Override
	public JStocksSummaryService getJStocksSummaryService() {
		return jStocksSummaryService;
	}

	// 业务汇总统计表（临时数据表）
	@Override
	public KonkaYwHzTjService getKonkaYwHzTjService() {
		return konkaYwHzTjService;
	}

	@Resource
	EcAuctionMainService ecAuctionMainService;

	@Resource
	EcAuctionBuyService ecAuctionBuyService;

	@Override
	public EcAuctionMainService getEcAuctionMainService() {
		return ecAuctionMainService;
	}

	@Override
	public EcAuctionBuyService getEcAuctionBuyService() {
		return ecAuctionBuyService;
	}

	// 发票上传审核
	@Resource
	KonkaMobileSailDataBillLoService konkaMobileSailDataBillLoService;

	@Override
	public KonkaMobileSailDataBillLoService getKonkaMobileSailDataBillLoService() {
		return konkaMobileSailDataBillLoService;
	}

	@Resource
	KonkaMobileSailDataBillService konkaMobileSailDataBillService;

	@Override
	public KonkaMobileSailDataBillService getKonkaMobileSailDataBillService() {
		return konkaMobileSailDataBillService;
	}

	@Resource
	KonkaMobileSailDataBillSwService konkaMobileSailDataBillSwService;

	@Override
	public KonkaMobileSailDataBillSwService getKonkaMobileSailDataBillSwService() {
		return konkaMobileSailDataBillSwService;
	}

	// 客户定位信息
	@Resource
	KonkaMobileCustVisitGpsService konkaMobileCustVisitGpsService;

	// 联系人表
	@Resource
	KonkaMobileCustVisitLinkService konkaMobileCustVisitLinkService;

	@Override
	public KonkaMobileCustVisitGpsService getKonkaMobileCustVisitGpsService() {
		return konkaMobileCustVisitGpsService;
	}

	@Override
	public KonkaMobileCustVisitLinkService getKonkaMobileCustVisitLinkService() {
		return konkaMobileCustVisitLinkService;
	}

	@Resource
	KonkaInterfaceIpService konkaInterfaceIpService;

	@Resource
	KonkaInterfaceLicensesService konkaInterfaceLicensesService;

	@Resource
	KonkaInterfaceBindsUserService konkaInterfaceBindsUserService;

	@Resource
	KonkaInterfaceAccessLogService konkaInterfaceAccessLogService;

	@Resource
	CmsCustomerService cmsCustomerService;

	@Override
	public KonkaInterfaceIpService getKonkaInterfaceIpService() {
		return konkaInterfaceIpService;
	}

	@Override
	public KonkaInterfaceLicensesService getKonkaInterfaceLicensesService() {
		return konkaInterfaceLicensesService;
	}

	@Override
	public KonkaInterfaceBindsUserService getKonkaInterfaceBindsUserService() {
		return konkaInterfaceBindsUserService;
	}

	@Override
	public KonkaInterfaceAccessLogService getKonkaInterfaceAccessLogService() {
		return konkaInterfaceAccessLogService;
	}

	@Resource
	KonkaR3ShopBrandService konkaR3ShopBrandService;

	@Override
	public KonkaR3ShopBrandService getKonkaR3ShopBrandService() {
		return konkaR3ShopBrandService;
	}

	@Override
	public CmsCustomerService getCmsCustomerService() {
		return cmsCustomerService;
	}

	// 基础类别数据表
	@Resource
	KonkaBaseTypeDataService konkaBaseTypeDataService;

	@Override
	public KonkaBaseTypeDataService getKonkaBaseTypeDataService() {
		return konkaBaseTypeDataService;
	}

	@Resource
	KonkaR3ShopNewService konkaR3ShopNewService;

	@Override
	public KonkaR3ShopNewService getKonkaR3ShopNewService() {
		return konkaR3ShopNewService;
	}

	@Resource
	KonkaCommentInfoService konkaCommentInfoService;

	@Resource
	KonkaCommentReadService konkaCommentReadService;

	@Resource
	KonkaCommentScoreService konkaCommentScoreService;

	@Override
	public KonkaCommentInfoService getKonkaCommentInfoService() {
		return konkaCommentInfoService;
	}

	@Override
	public KonkaCommentReadService getKonkaCommentReadService() {
		return konkaCommentReadService;
	}

	@Override
	public KonkaCommentScoreService getKonkaCommentScoreService() {
		return konkaCommentScoreService;
	}

	@Resource
	StatisticalDimensionAreaService statisticalDimensionAreaService;

	@Resource
	StatisticalDimensionDataService statisticalDimensionDataService;

	@Resource
	StatisticalDimensionKhfgsService statisticalDimensionKhfgsService;

	@Resource
	StatisticalDimensionSaleAreaService statisticalDimensionSaleAreaService;

	@Resource
	StatisticalDimensionTimeService statisticalDimensionTimeService;

	@Override
	public StatisticalDimensionAreaService getStatisticalDimensionAreaService() {
		return statisticalDimensionAreaService;
	}

	@Override
	public StatisticalDimensionDataService getStatisticalDimensionDataService() {
		return statisticalDimensionDataService;
	}

	@Override
	public StatisticalDimensionKhfgsService getStatisticalDimensionKhfgsService() {
		return statisticalDimensionKhfgsService;
	}

	@Override
	public StatisticalDimensionSaleAreaService getStatisticalDimensionSaleAreaService() {
		return statisticalDimensionSaleAreaService;
	}

	@Override
	public StatisticalDimensionTimeService getStatisticalDimensionTimeService() {
		return statisticalDimensionTimeService;
	}

	@Resource
	StatisticalDimensionDataMiddleService statisticalDimensionDataMiddleService;

	@Resource
	StatisticalDimensionDataMonthMiddleService statisticalDimensionDataMonthMiddleService;

	@Override
	public StatisticalDimensionDataMiddleService getStatisticalDimensionDataMiddleService() {
		return statisticalDimensionDataMiddleService;
	}

	@Override
	public StatisticalDimensionDataMonthMiddleService getStatisticalDimensionDataMonthMiddleService() {
		return statisticalDimensionDataMonthMiddleService;
	}

	@Resource
	StatisticalDimensionDataMonthService statisticalDimensionDataMonthService;

	@Resource
	StatisticalDimensionTimeMonthService statisticalDimensionTimeMonthService;

	@Override
	public StatisticalDimensionDataMonthService getStatisticalDimensionDataMonthService() {
		return statisticalDimensionDataMonthService;
	}

	@Override
	public StatisticalDimensionTimeMonthService getStatisticalDimensionTimeMonthService() {
		return statisticalDimensionTimeMonthService;
	}

	@Resource
	StatisticalDimensionTimeDayService statisticalDimensionTimeDayService;

	@Override
	public StatisticalDimensionTimeDayService getStatisticalDimensionTimeDayService() {
		return statisticalDimensionTimeDayService;
	}

	@Resource
	StatisticalDimensionMdService statisticalDimensionMdService;

	@Resource
	StatisticalDimensionRetailDataService statisticalDimensionRetailDataService;

	@Override
	public StatisticalDimensionMdService getStatisticalDimensionMdService() {
		return statisticalDimensionMdService;
	}

	@Override
	public StatisticalDimensionRetailDataService getStatisticalDimensionRetailDataService() {
		return statisticalDimensionRetailDataService;
	}

	@Resource
	StatisticalDimensionStoreService statisticalDimensionStoreService;

	@Override
	public StatisticalDimensionStoreService getStatisticalDimensionStoreService() {
		return statisticalDimensionStoreService;
	}

	@Resource
	YwtAttendanceSignTimeService ywtAttendanceSignTimeService;

	@Resource
	YwtAttendanceSignService ywtAttendanceSignService;

	@Resource
	YwtAskLeaveService ywtAskLeaveService;

	@Resource
	YwtAttendanceDayService ywtAttendanceDayService;

	@Resource
	YwtTaskService ywtTaskService;

	@Resource
	YwtTaskReceiveService ywtTaskReceiveService;

	@Override
	public YwtAttendanceSignTimeService getYwtAttendanceSignTimeService() {
		return ywtAttendanceSignTimeService;
	}

	@Override
	public YwtAttendanceSignService getYwtAttendanceSignService() {
		return ywtAttendanceSignService;
	}

	@Override
	public YwtAskLeaveService getYwtAskLeaveService() {
		return ywtAskLeaveService;
	}

	@Override
	public YwtAttendanceDayService getYwtAttendanceDayService() {
		return ywtAttendanceDayService;
	}

	@Override
	public YwtTaskService getYwtTaskService() {
		return ywtTaskService;
	}

	@Override
	public YwtTaskReceiveService getYwtTaskReceiveService() {
		return ywtTaskReceiveService;
	}

	@Resource
	KonkaSapInterfaceSettingService konkaSapInterfaceSettingService;

	@Override
	public KonkaSapInterfaceSettingService getKonkaSapInterfaceSettingService() {
		return konkaSapInterfaceSettingService;
	}

	@Resource
	WeixinBindUserService weixinBindUserService;

	@Override
	public WeixinBindUserService getWeixinBindUserService() {
		return weixinBindUserService;
	}

	@Resource
	EcVoteMainService ecVoteMainService;

	@Resource
	EcVoteOptionService ecVoteOptionService;

	@Resource
	EcVoteRecordService ecVoteRecordService;

	@Override
	public EcVoteMainService getEcVoteMainService() {
		return ecVoteMainService;
	}

	@Override
	public EcVoteOptionService getEcVoteOptionService() {
		return ecVoteOptionService;
	}

	@Override
	public EcVoteRecordService getEcVoteRecordService() {
		return ecVoteRecordService;
	}

	@Resource
	SfhkRelEppOrderService sfhkRelEppOrderService;

	@Resource
	SyncRecodeSfhkService syncRecodeSfhkService;

	@Override
	public SfhkRelEppOrderService getSfhkRelEppOrderService() {
		return sfhkRelEppOrderService;
	}

	@Override
	public SyncRecodeSfhkService getSyncRecodeSfhkService() {
		return syncRecodeSfhkService;
	}

	@Resource
	SfhkOutStorageService sfhkOutStorageService;

	@Override
	public SfhkOutStorageService getSfhkOutStorageService() {
		return sfhkOutStorageService;
	}

	@Resource
	SfhkCompanyService sfhkCompanyService;

	@Override
	public SfhkCompanyService getSfhkCompanyService() {
		return sfhkCompanyService;
	}

	@Resource
	KonkaSalesmanInfoService konkaSalesmanInfoService;

	@Override
	public KonkaSalesmanInfoService getKonkaSalesmanInfoService() {
		return konkaSalesmanInfoService;
	}

	@Resource
	KonkaYjglYjedService konkaYjglYjedService;

	@Resource
	KonkaYjglYjedAddService konkaYjglYjedAddService;

	@Resource
	KonkaYjglPlanService konkaYjglPlanService;

	@Resource
	KonkaYjglPlanFpService konkaYjglPlanFpService;

	@Override
	public KonkaYjglYjedService getKonkaYjglYjedService() {
		return konkaYjglYjedService;
	}

	@Override
	public KonkaYjglYjedAddService getKonkaYjglYjedAddService() {
		return konkaYjglYjedAddService;
	}

	@Override
	public KonkaYjglPlanService getKonkaYjglPlanService() {
		return konkaYjglPlanService;
	}

	@Override
	public KonkaYjglPlanFpService getKonkaYjglPlanFpService() {
		return konkaYjglPlanFpService;
	}

	@Override
	public KonkaOrderPlanService getKonkaOrderPlanService() {
		return konkaOrderPlanService;
	}

	@Resource
	KonkaFgsCkSnService konkaFgsCkSnService;

	@Resource
	KonkaFgsEdService konkaFgsEdService;

	@Override
	public KonkaFgsCkSnService getKonkaFgsCkSnService() {
		return konkaFgsCkSnService;
	}

	@Override
	public KonkaFgsEdService getKonkaFgsEdService() {
		return konkaFgsEdService;
	}

	@Resource
	SsoOaUserBindService ssoOaUserBindService;

	@Resource
	SsoOaUserGroupService ssoOaUserGroupService;

	@Resource
	EcGiftJfBuyService ecGiftJfBuyService;

	@Override
	public SsoOaUserBindService getSsoOaUserBindService() {
		return ssoOaUserBindService;
	}

	@Override
	public SsoOaUserGroupService getSsoOaUserGroupService() {
		return ssoOaUserGroupService;
	}

	@Override
	public EcGiftJfBuyService getEcGiftJfBuyService() {
		return ecGiftJfBuyService;
	}

	@Resource
	EcVouchCodeService ecVouchCodeService;

	@Override
	public EcVouchCodeService getEcVouchCodeService() {
		return ecVouchCodeService;
	}

	@Resource
	EcGroupBuyMainService ecGroupBuyMainService;

	@Override
	public EcGroupBuyMainService getEcGroupBuyMainService() {
		return ecGroupBuyMainService;
	}

	@Resource
	SapExecuteLogService sapExecuteLogService;

	@Override
	public SapExecuteLogService getSapExecuteLogService() {
		return sapExecuteLogService;
	}

	@Resource
	DataSync4BIService dataSync4BIService;

	@Override
	public DataSync4BIService getDataSync4BIService() {
		return dataSync4BIService;
	}

	@Resource
	StatisticalDimensionFgsLatlngService statisticalDimensionFgsLatlngService;

	@Override
	public StatisticalDimensionFgsLatlngService getStatisticalDimensionFgsLatlngService() {
		return statisticalDimensionFgsLatlngService;
	}

	// 决策分析-销售趋势
	@Resource
	JcfxReportXsqsService jcfxReportXsqsService;

	@Override
	public JcfxReportXsqsService getJcfxReportXsqsService() {
		return jcfxReportXsqsService;
	}

	@Resource
	KonkaPhotoUploadTypeService konkaPhotoUploadTypeService;

	@Override
	public KonkaPhotoUploadTypeService getKonkaPhotoUploadTypeService() {
		return konkaPhotoUploadTypeService;
	}

	@Resource
	KonkaPhotoUploadService konkaPhotoUploadService;

	@Override
	public KonkaPhotoUploadService getKonkaPhotoUploadService() {
		return konkaPhotoUploadService;
	}

	@Resource
	KonkaPhotoUploadTypeDeptService konkaPhotoUploadTypeDeptService;

	@Override
	public KonkaPhotoUploadTypeDeptService getKonkaPhotoUploadTypeDeptService() {
		return konkaPhotoUploadTypeDeptService;
	}

	@Resource
	EcBcompPdUpNewService ecBcompPdUpNewService;

	@Override
	public EcBcompPdUpNewService getEcBcompPdUpNewService() {
		return ecBcompPdUpNewService;
	}

	@Resource
	EcGroupService ecGroupService;

	@Resource
	EcCustService ecCustService;

	@Override
	public EcGroupService getEcGroupService() {
		return ecGroupService;
	}

	@Override
	public EcCustService getEcCustService() {
		return ecCustService;
	}

	@Resource
	EcGoodsIntegralService ecGoodsIntegralService;

	@Override
	public EcGoodsIntegralService getEcGoodsIntegralService() {
		return ecGoodsIntegralService;
	}

	@Resource
	EcLogoInfoService ecLogoInfoService;

	@Resource
	EcNavInfoService ecNavInfoService;

	@Override
	public EcLogoInfoService getEcLogoInfoService() {
		return ecLogoInfoService;
	}

	@Override
	public EcNavInfoService getEcNavInfoService() {
		return ecNavInfoService;
	}

	@Resource
	EcGoodsSallareaService ecGoodsSallareaService;

	@Resource
	EcSallareaTemplateService ecSallareaTemplateService;

	@Resource
	EcSallareaService ecSallareaService;

	@Override
	public EcGoodsSallareaService getEcGoodsSallareaService() {
		return ecGoodsSallareaService;
	}

	@Override
	public EcSallareaTemplateService getEcSallareaTemplateService() {
		return ecSallareaTemplateService;
	}

	@Override
	public EcSallareaService getEcSallareaService() {
		return ecSallareaService;
	}

	@Resource
	EcVouchersAuditService ecVouchersAuditService;

	@Resource
	EcVouchersApplyService ecVouchersApplyService;

	@Override
	public EcVouchersAuditService getEcVouchersAuditService() {
		return ecVouchersAuditService;
	}

	@Override
	public EcVouchersApplyService getEcVouchersApplyService() {
		return ecVouchersApplyService;
	}

	@Resource
	JcfxKczzKhService jcfxKczzKhService;

	@Resource
	JcfxKczzKhfzService jcfxKczzKhfzService;

	@Resource
	JcfxKczzKhfzLinkService jcfxKczzKhfzLinkService;

	@Override
	public JcfxKczzKhService getJcfxKczzKhService() {
		return jcfxKczzKhService;
	}

	@Override
	public JcfxKczzKhfzService getJcfxKczzKhfzService() {
		return jcfxKczzKhfzService;
	}

	@Override
	public JcfxKczzKhfzLinkService getJcfxKczzKhfzLinkService() {
		return jcfxKczzKhfzLinkService;
	}

	@Resource
	EcGoodsRebateService ecGoodsRebateService;

	@Override
	public EcGoodsRebateService getEcGoodsRebateService() {
		return ecGoodsRebateService;
	}

	// 促销预约开始
	@Resource
	KonkaSpActivityAddrService konkaSpActivityAddrService;

	@Resource
	KonkaSpActivityBookReportService konkaSpActivityBookReportService;

	@Override
	public KonkaSpActivityAddrService getKonkaSpActivityAddrService() {
		return konkaSpActivityAddrService;
	}

	@Override
	public KonkaSpActivityBookReportService getKonkaSpActivityBookReportService() {
		return konkaSpActivityBookReportService;
	}

	// 促销预约结束

	@Resource
	EcArticleInfoService ecArticleInfoService;

	@Override
	public EcArticleInfoService getEcArticleInfoService() {
		return ecArticleInfoService;
	}

	@Resource
	SysObjDataService sysObjDataService;

	@Override
	public SysObjDataService getSysObjDataService() {
		return sysObjDataService;
	}

	@Resource
	EcHomeFloorService ecHomeFloorService;

	@Resource
	EcHomeFloorDataService ecHomeFloorDataService;

	@Override
	public EcHomeFloorService getEcHomeFloorService() {
		return ecHomeFloorService;
	}

	@Override
	public EcHomeFloorDataService getEcHomeFloorDataService() {
		return ecHomeFloorDataService;
	}

	@Resource
	EcCustRelUserService ecCustRelUserService;

	@Override
	public EcCustRelUserService getEcCustRelUserService() {
		return ecCustRelUserService;
	}

	@Resource
	KonkaAuditListInfoService konkaAuditListInfoService;

	@Override
	public KonkaAuditListInfoService getKonkaAuditListInfoService() {
		return konkaAuditListInfoService;
	}

	@Resource
	KonkaR3ShopNewAttService konkaR3ShopNewAttService;

	@Override
	public KonkaR3ShopNewAttService getKonkaR3ShopNewAttService() {
		return konkaR3ShopNewAttService;
	}

	@Resource
	KonkaSoFifoService konkaSoFifoService;

	public KonkaSoFifoService getKonkaSoFifoService() {
		return konkaSoFifoService;
	}

	@Resource
	EcProductService ecProductService;

	public EcProductService getEcProductService() {
		return ecProductService;
	}

	@Resource
	AreaFightInfoService areaFightInfoService;

	public AreaFightInfoService getAreaFightInfoService() {
		return areaFightInfoService;
	}

	@Resource
	KonkaFightActivityManagerService konkaFightActivityManagerService;

	@Override
	public KonkaFightActivityManagerService getKonkaFightActivityManagerService() {
		
		return konkaFightActivityManagerService;
	}

	@Resource
	GcxmProjService gcxmProjService;

	@Resource
	GcxmProjBuyinfoService gcxmProjBuyinfoService;

	@Resource
	GcxmProjTjService gcxmProjTjService;

	@Resource
	GcxmProjOfferService gcxmProjOfferService;

	@Resource
	GcxmProjSupplyService gcxmProjSupplyService;

	@Resource
	GcxmProjCompetService gcxmProjCompetService;

	@Resource
	GcxmProjAuditService gcxmProjAuditService;

	@Resource
	GcxmProjAuditNodeService gcxmProjAuditNodeService;

	@Resource
	GcxmAuditProcessService gcxmAuditProcessService;

	@Resource
	GcxmAuditProcessNodeService gcxmAuditProcessNodeService;

	public GcxmProjService getGcxmProjService() {
		return gcxmProjService;
	}

	public GcxmProjBuyinfoService getGcxmProjBuyinfoService() {
		return gcxmProjBuyinfoService;
	}

	public GcxmProjTjService getGcxmProjTjService() {
		return gcxmProjTjService;
	}

	public GcxmProjOfferService getGcxmProjOfferService() {
		return gcxmProjOfferService;
	}

	public GcxmProjSupplyService getGcxmProjSupplyService() {
		return gcxmProjSupplyService;
	}

	public GcxmProjCompetService getGcxmProjCompetService() {
		return gcxmProjCompetService;
	}

	public GcxmProjAuditService getGcxmProjAuditService() {
		return gcxmProjAuditService;
	}

	public GcxmProjAuditNodeService getGcxmProjAuditNodeService() {
		return gcxmProjAuditNodeService;
	}

	public GcxmAuditProcessService getGcxmAuditProcessService() {
		return gcxmAuditProcessService;
	}

	public GcxmAuditProcessNodeService getGcxmAuditProcessNodeService() {
		return gcxmAuditProcessNodeService;
	}

	// 客户分仓库库存汇总
	@Resource
	JStocksStoreSummaryService jStocksStoreSummaryService;

	@Override
	public JStocksStoreSummaryService getJStocksStoreSummaryService() {
		return jStocksStoreSummaryService;
	}

	@Resource
	OaUserGroupLService oaUserGroupLService;

	@Resource
	OaUserGroupHService oaUserGroupHService;

	public OaUserGroupLService getOaUserGroupLService() {
		return oaUserGroupLService;
	}

	public OaUserGroupHService getOaUserGroupHService() {
		return oaUserGroupHService;
	}

	// 客户端推送用户id记录
	@Resource
	KonkaMobileLoginFlagService konkaMobileLoginFlagService;

	public KonkaMobileLoginFlagService getKonkaMobileLoginFlagService() {
		return konkaMobileLoginFlagService;
	}

	  //先进先出
	@Resource
	JxcFifoStocksService jxcFifoStocksService;
	
	public JxcFifoStocksService getJxcFifoStocksService() {
		return jxcFifoStocksService;
	}
  //先进先出比对
	@Resource
	JxcFifoDataCheckService jxcFifoDataCheckService;
	public JxcFifoDataCheckService getJxcFifoDataCheckService() {
		return jxcFifoDataCheckService;
	}

	@Resource
	KonkaSalesmanInfoLogService konkaSalesmanInfoLogService;

	public KonkaSalesmanInfoLogService getKonkaSalesmanInfoLogService() {
		return konkaSalesmanInfoLogService;
	}

	@Resource
	LogOfJobService logOfJobService;

	public LogOfJobService getLogOfJobService() {
		return logOfJobService;
	}

	@Resource
	MvOrgOfCustomerService mvOrgOfCustomerService;

	@Resource
	MvOrgOfCustomerAllService mvOrgOfCustomerAllService;

	@Resource
	MvOrgOfCxyService mvOrgOfCxyService;

	@Resource
	MvOrgOfCxyAllService mvOrgOfCxyAllService;

	public MvOrgOfCustomerService getMvOrgOfCustomerService() {
		return mvOrgOfCustomerService;
	}

	public MvOrgOfCustomerAllService getMvOrgOfCustomerAllService() {
		return mvOrgOfCustomerAllService;
	}

	public MvOrgOfCxyService getMvOrgOfCxyService() {
		return mvOrgOfCxyService;
	}

	public MvOrgOfCxyAllService getMvOrgOfCxyAllService() {
		return mvOrgOfCxyAllService;
	}
	
	@Resource
	ConsumerInfoService consumerInfoService;
	
	public ConsumerInfoService getConsumerInfoService() {
		return consumerInfoService;
	}

    //
    @Resource
    FromDrpOrderService fromDrpOrderService;

    @Resource
    FromDrpOrderDetailService fromDrpOrderDetailService;

    @Resource
    ToDrpOrderService toDrpOrderService;

    @Resource
    ToDrpOrderDetailService toDrpOrderDetailService;

    public FromDrpOrderService getFromDrpOrderService() {
        return fromDrpOrderService;
    }

    public FromDrpOrderDetailService getFromDrpOrderDetailService() {
        return fromDrpOrderDetailService;
    }

    public ToDrpOrderService getToDrpOrderService() {
        return toDrpOrderService;
    }

    public ToDrpOrderDetailService getToDrpOrderDetailService() {
        return toDrpOrderDetailService;
    }

    @Resource
    QdDrpSyncLogService qdDrpSyncLogService;

    public QdDrpSyncLogService getQdDrpSyncLogService() {
        return qdDrpSyncLogService;
    }
    
    @Resource
	KonkaR3TargetService konkaR3TargetService;
	
	public KonkaR3TargetService getKonkaR3TargetService() {
		return konkaR3TargetService;
	}
	
	@Resource
	EcPdEavlZanService ecPdEavlZanService;
	
	public EcPdEavlZanService getEcPdEavlZanService() {
		return ecPdEavlZanService;
	}

	@Resource
	KonkaJobsService konkaJobsService;

	public KonkaJobsService getKonkaJobsService() {
		return konkaJobsService;
	}
}