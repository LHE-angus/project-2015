package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-23 16:32:15
 */
public class KonkaMobileCustVisit extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long visit_id;
	
	private Date add_date;
	
	private Date begin_date;
	
	private Date end_date;
	
	private Integer report_type;
	
	private String feed_list;
	
	private Integer state;
	
	private String visit_desc;
	
	private String ywy_job_id;
	
	private Long dept_id;
	
	private Long subcomp_id;
	
	private Integer is_del;
	
	private Long add_user_id;
	
	private Long visit_per_count;
	
	private String report_nae;
	
	private Integer data_source;
	
    private Integer domestic_ranking;
	
	private Long consumer_sales;
	
	private Long retail_sales;
	
	private String consumer_name;
	
	private String consumer_phone;
	
	private String remark;
	
	private String imei;
	
	private String phone_model;
	
	private String file_no;

	private KonkaMobileCustVisitDetail konkaMobileCustVisitDetail;
	
	private List<KonkaMobileCustVisitType> konkaMobileCustVisitTypeList;
	
	private List<KonkaMobileCustVisitExtend> konkaMobileCustVisitExtendList;
	
	private List<KonkaPeAttachments> konkaPeAttachmentsList;
	
	private List<Attachment> attachmentsList;

	public List<Attachment> getAttachmentsList() {
		return attachmentsList;
	}

	public void setAttachmentsList(List<Attachment> attachmentsList) {
		this.attachmentsList = attachmentsList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public KonkaMobileCustVisitDetail getKonkaMobileCustVisitDetail() {
		return konkaMobileCustVisitDetail;
	}

	public void setKonkaMobileCustVisitDetail(KonkaMobileCustVisitDetail konkaMobileCustVisitDetail) {
		this.konkaMobileCustVisitDetail = konkaMobileCustVisitDetail;
	}

	public List<KonkaMobileCustVisitType> getKonkaMobileCustVisitTypeList() {
		return konkaMobileCustVisitTypeList;
	}

	public void setKonkaMobileCustVisitTypeList(List<KonkaMobileCustVisitType> konkaMobileCustVisitTypeList) {
		this.konkaMobileCustVisitTypeList = konkaMobileCustVisitTypeList;
	}

	public List<KonkaMobileCustVisitExtend> getKonkaMobileCustVisitExtendList() {
		return konkaMobileCustVisitExtendList;
	}

	public void setKonkaMobileCustVisitExtendList(List<KonkaMobileCustVisitExtend> konkaMobileCustVisitExtendList) {
		this.konkaMobileCustVisitExtendList = konkaMobileCustVisitExtendList;
	}

	public List<KonkaPeAttachments> getKonkaPeAttachmentsList() {
		return konkaPeAttachmentsList;
	}

	public void setKonkaPeAttachmentsList(List<KonkaPeAttachments> konkaPeAttachmentsList) {
		this.konkaPeAttachmentsList = konkaPeAttachmentsList;
	}

	public KonkaMobileCustVisit() {

	}

	/**
	 * @val ID
	 */
	public Long getVisit_id() {
		return visit_id;
	}
	
	/**
	 * @val ID
	 */
	public void setVisit_id(Long visit_id) {
		this.visit_id = visit_id;
	}
	
	/**
	 * @val 创建日期
	 */
	public Date getAdd_date() {
		return add_date;
	}
	
	/**
	 * @val 创建日期
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	
	/**
	 * @val 拜访开始日期
	 */
	public Date getBegin_date() {
		return begin_date;
	}
	
	/**
	 * @val 拜访开始日期
	 */
	public void setBegin_date(Date begin_date) {
		this.begin_date = begin_date;
	}
	
	/**
	 * @val 拜访结束日期
	 */
	public Date getEnd_date() {
		return end_date;
	}
	
	/**
	 * @val 拜访结束日期
	 */
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
	/**
	 * @val 1、正常客户拜访
	 * @val 2、老客户重拾
	 * @val 3、新客户开拓日志；
	 * @val 4、事务上报
	 * @val 
	 * @val 5预留
	 */
	public Integer getReport_type() {
		return report_type;
	}
	
	/**
	 * @val 1、正常客户拜访
	 * @val 2、老客户重拾
	 * @val 3、新客户开拓日志；
	 * @val 4、事务上报
	 * @val 
	 * @val 5预留
	 */
	public void setReport_type(Integer report_type) {
		this.report_type = report_type;
	}
	
	/**
	 * @val 客户反馈问题
	 */
	public String getFeed_list() {
		return feed_list;
	}
	
	/**
	 * @val 客户反馈问题
	 */
	public void setFeed_list(String feed_list) {
		this.feed_list = feed_list;
	}
	
	/**
	 * @val 拜访状态
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * @val 拜访状态
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	/**
	 * @val 拜访说明
	 */
	public String getVisit_desc() {
		return visit_desc;
	}
	
	/**
	 * @val 拜访说明
	 */
	public void setVisit_desc(String visit_desc) {
		this.visit_desc = visit_desc;
	}
	
	/**
	 * @val 所属业务员JOBID
	 */
	public String getYwy_job_id() {
		return ywy_job_id;
	}
	
	/**
	 * @val 所属业务员JOBID
	 */
	public void setYwy_job_id(String ywy_job_id) {
		this.ywy_job_id = ywy_job_id;
	}
	
	/**
	 * @val 所属部门
	 */
	public Long getDept_id() {
		return dept_id;
	}
	
	/**
	 * @val 所属部门
	 */
	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}
	
	/**
	 * @val 所属分公司
	 */
	public Long getSubcomp_id() {
		return subcomp_id;
	}
	
	/**
	 * @val 所属分公司
	 */
	public void setSubcomp_id(Long subcomp_id) {
		this.subcomp_id = subcomp_id;
	}
	
	/**
	 * @val 0表示正常状态
	 * @val 1表示删除
	 */
	public Integer getIs_del() {
		return is_del;
	}
	
	/**
	 * @val 0表示正常状态
	 * @val 1表示删除
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}
	
	public Long getAdd_user_id() {
		return add_user_id;
	}
	
	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}
	
	/**
	 * @val 拜访人数
	 */
	public Long getVisit_per_count() {
		return visit_per_count;
	}
	
	/**
	 * @val 拜访人数
	 */
	public void setVisit_per_count(Long visit_per_count) {
		this.visit_per_count = visit_per_count;
	}
	
	/**
	 * @val 上报人姓名
	 */
	public String getReport_nae() {
		return report_nae;
	}
	
	/**
	 * @val 上报人姓名
	 */
	public void setReport_nae(String report_nae) {
		this.report_nae = report_nae;
	}
	
	/**
	 * @val 数据来源
	 * @val 0表示手机端android端
	 * @val 1表示手机端ios端
	 * @val 2表示web端
	 * @val 3表示wap端
	 */
	public Integer getData_source() {
		return data_source;
	}
	
	/**
	 * @val 数据来源
	 * @val 0表示手机端android端
	 * @val 1表示手机端ios端
	 * @val 2表示web端
	 * @val 3表示wap端
	 */
	public void setData_source(Integer data_source) {
		this.data_source = data_source;
	}
	
	/**
	 * @val 国产排名
	 */
	public Integer getDomestic_ranking() {
		return domestic_ranking;
	}
	
	/**
	 * @val 国产排名
	 */
	public void setDomestic_ranking(Integer domestic_ranking) {
		this.domestic_ranking = domestic_ranking;
	}
	
	/**
	 * @val 零售量
	 */
	public Long getConsumer_sales() {
		return consumer_sales;
	}
	
	/**
	 * @val 零售量
	 */
	public void setConsumer_sales(Long consumer_sales) {
		this.consumer_sales = consumer_sales;
	}
	
	/**
	 * @val 零售额
	 */
	public Long getRetail_sales() {
		return retail_sales;
	}
	
	/**
	 * @val 零售额
	 */
	public void setRetail_sales(Long retail_sales) {
		this.retail_sales = retail_sales;
	}
	
	/**
	 * @val 客户名称
	 */
	public String getConsumer_name() {
		return consumer_name;
	}
	
	/**
	 * @val 客户名称
	 */
	public void setConsumer_name(String consumer_name) {
		this.consumer_name = consumer_name;
	}
	
	/**
	 * @val 客户电话
	 */
	public String getConsumer_phone() {
		return consumer_phone;
	}
	
	/**
	 * @val 客户电话
	 */
	public void setConsumer_phone(String consumer_phone) {
		this.consumer_phone = consumer_phone;
	}
	public String getRemark() {
		return remark;
	}
	/**
	 * @val 备注说明
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * @val 手机IMEI
	 */
	public String getImei() {
		return imei;
	}
	
	/**
	 * @val 手机IMEI
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}
	
	/**
	 * @val 手机型号
	 */
	public String getPhone_model() {
		return phone_model;
	}
	
	/**
	 * @val 手机型号
	 */
	public void setPhone_model(String phone_model) {
		this.phone_model = phone_model;
	}
	
	/**
	 * @val 单号
	 */
	public String getFile_no() {
		return file_no;
	}
	
	/**
	 * @val 单号
	 */
	public void setFile_no(String file_no) {
		this.file_no = file_no;
	}
}