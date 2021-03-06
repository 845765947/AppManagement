package com.smbms.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class App_info {
	private long id; // 主键id
	private String softwareName;// 软件名称
	private String APKName; // APK名称（唯一）
	private String supportROM; // 支持ROM
	private String interfaceLanguage;// 界面语言
	private BigDecimal softwareSize; // 软件大小（单位：M）
	private Date updateDate; // 更新日期
	private long devId;// 开发者id（来源于：dev_user表的开发者id）
	private String appInfo; // 应用简介
	private long status; // 状态（来源于：data_dictionary，1 待审核 2 审核通过 3 审核不通过 4 已上架
							// 5已下架）
	private Date onSaleDate; // 上架时间
	private Date offSaleDate; // 下架时间
	private long flatformId; // 所属平台（来源于：data_dictionary，1 手机 2 平板 3 通用）
	private long downloads; // 下载量（单位：次）
	private long createdBy; // 创建者（来源于dev_user开发者信息表的用户id）
	private Date creationDate; // 创建时间
	private long modifyBy; // 更新者（来源于dev_user开发者信息表的用户id）
	private Date modifyDate;// 最新更新时间
	private long categoryLevel1; // 所属一级分类（来源于：data_dictionary）
	private long categoryLevel2; // 所属二级分类（来源于：data_dictionary）
	private long categoryLevel3; // 所属三级分类（来源于：data_dictionary）
	private String logoPicPath; // LOGO图片url路径
	private String logoLocPath; // LOGO图片的服务器存储路径
	private long versionId; // 最新的版本id

	// 新加上的属性
	private String versionNo;// 不知道有什么卵用，又不能删
	private String flatformName;// 所属平台名称
	private String statusName;// 状态名称
	private String categoryLevel1Name;// 所属一级名称（来源于：data_dictionary）
	private String categoryLevel2Name;// 所属二级名称（来源于：data_dictionary）
	private String categoryLevel3Name;// 所属三级名称（来源于：data_dictionary）
	private String devName;// 开发者名称

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}

	public long getVersionId() {
		return versionId;
	}

	public void setVersionId(long versionId) {
		this.versionId = versionId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getFlatformName() {
		return flatformName;
	}

	public void setFlatformName(String flatformName) {
		this.flatformName = flatformName;
	}

	public String getCategoryLevel1Name() {
		return categoryLevel1Name;
	}

	public void setCategoryLevel1Name(String categoryLevel1Name) {
		this.categoryLevel1Name = categoryLevel1Name;
	}

	public String getCategoryLevel2Name() {
		return categoryLevel2Name;
	}

	public void setCategoryLevel2Name(String categoryLevel2Name) {
		this.categoryLevel2Name = categoryLevel2Name;
	}

	public String getCategoryLevel3Name() {
		return categoryLevel3Name;
	}

	public void setCategoryLevel3Name(String categoryLevel3Name) {
		this.categoryLevel3Name = categoryLevel3Name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSoftwareName() {
		return softwareName;
	}

	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}

	public String getAPKName() {
		return APKName;
	}

	public void setAPKName(String aPKName) {
		APKName = aPKName;
	}

	public String getSupportROM() {
		return supportROM;
	}

	public void setSupportROM(String supportROM) {
		this.supportROM = supportROM;
	}

	public String getInterfaceLanguage() {
		return interfaceLanguage;
	}

	public void setInterfaceLanguage(String interfaceLanguage) {
		this.interfaceLanguage = interfaceLanguage;
	}

	public BigDecimal getSoftwareSize() {
		return softwareSize;
	}

	public void setSoftwareSize(BigDecimal softwareSize) {
		this.softwareSize = softwareSize;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public long getDevId() {
		return devId;
	}

	public void setDevId(long devId) {
		this.devId = devId;
	}

	public String getAppInfo() {
		return appInfo;
	}

	public void setAppInfo(String appInfo) {
		this.appInfo = appInfo;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public Date getOnSaleDate() {
		return onSaleDate;
	}

	public void setOnSaleDate(Date onSaleDate) {
		this.onSaleDate = onSaleDate;
	}

	public Date getOffSaleDate() {
		return offSaleDate;
	}

	public void setOffSaleDate(Date offSaleDate) {
		this.offSaleDate = offSaleDate;
	}

	public long getFlatformId() {
		return flatformId;
	}

	public void setFlatformId(long flatformId) {
		this.flatformId = flatformId;
	}

	public long getCategoryLevel3() {
		return categoryLevel3;
	}

	public void setCategoryLevel3(long categoryLevel3) {
		this.categoryLevel3 = categoryLevel3;
	}

	public long getDownloads() {
		return downloads;
	}

	public void setDownloads(long downloads) {
		this.downloads = downloads;
	}

	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public long getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(long modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public long getCategoryLevel1() {
		return categoryLevel1;
	}

	public void setCategoryLevel1(long categoryLevel1) {
		this.categoryLevel1 = categoryLevel1;
	}

	public long getCategoryLevel2() {
		return categoryLevel2;
	}

	public void setCategoryLevel2(long categoryLevel2) {
		this.categoryLevel2 = categoryLevel2;
	}

	public String getLogoPicPath() {
		return logoPicPath;
	}

	public void setLogoPicPath(String logoPicPath) {
		this.logoPicPath = logoPicPath;
	}

	public String getLogoLocPath() {
		return logoLocPath;
	}

	public void setLogoLocPath(String logoLocPath) {
		this.logoLocPath = logoLocPath;
	}

}
