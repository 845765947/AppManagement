package com.smbms.pojo;

import java.util.Date;

public class Ad_promotion {
	private long id; // 主键id
	private long appId; // appId（来源于：app_info表的主键id）
	private String adPicPath; // 广告图片存储路径
	private long adPV; // 广告点击量 
	private int carouselPosition; // 轮播位（1-n）
	private Date startTime; // 起效时间
	private Date endTime; // 失效时间
	private long createdBy; // 创建者（来源于backend_user用户表的用户id）
	private Date creationDate; // 创建时间
	private long modifyBy; // 更新者（来源于backend_user用户表的用户id）
	private Date modifyDate; // 最新更新时间

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

	public String getAdPicPath() {
		return adPicPath;
	}

	public void setAdPicPath(String adPicPath) {
		this.adPicPath = adPicPath;
	}

	public long getAdPV() {
		return adPV;
	}

	public void setAdPV(long adPV) {
		this.adPV = adPV;
	}

	public int getCarouselPosition() {
		return carouselPosition;
	}

	public void setCarouselPosition(int carouselPosition) {
		this.carouselPosition = carouselPosition;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

}
