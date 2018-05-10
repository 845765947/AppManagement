package com.smbms.pojo;

import java.util.Date;

public class App_version {
	private long id; // 主键id
	private long appId; // appId（来源于：app_info表的主键id）
	private String versionNo; // 版本号
	private String versionInfo; // 版本介绍
	private long publishStatus; // 发布状态（来源于：data_dictionary，1 不发布 2 已发布 3 预发布）
	private String downloadLink; // 下载链接
	private String versionSize; // 版本大小（单位：M）
	private long createdBy; // 创建者（来源于dev_user开发者信息表的用户id）
	private Date creationDate; // 创建时间
	private long modifyBy; // 更新者（来源于dev_user开发者信息表的用户id）
	private String modifyDate; // 最新更新时间
	private Date apkLocPath; // apk文件的服务器存储路径
	private String apkFileName; // 上传的apk文件名称

	public long getId() {	
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAppId() {
		return appId;
	}

	public void setAppId(long appId) {
		this.appId = appId;
	}

	public String getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}

	public String getVersionInfo() {
		return versionInfo;
	}

	public void setVersionInfo(String versionInfo) {
		this.versionInfo = versionInfo;
	}

	public long getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(long publishStatus) {
		this.publishStatus = publishStatus;
	}

	public String getDownloadLink() {
		return downloadLink;
	}

	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}

	public String getVersionSize() {
		return versionSize;
	}

	public void setVersionSize(String versionSize) {
		this.versionSize = versionSize;
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

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Date getApkLocPath() {
		return apkLocPath;
	}

	public void setApkLocPath(Date apkLocPath) {
		this.apkLocPath = apkLocPath;
	}

	public String getApkFileName() {
		return apkFileName;
	}

	public void setApkFileName(String apkFileName) {
		this.apkFileName = apkFileName;
	}

}
