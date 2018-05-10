package com.smbms.pojo;

import java.util.Date;

public class App_category {
	private long id; // 主键ID
	private String categoryCode; // 分类编码
	private String categoryName; // 分类名称
	private long parentId; // 父级节点id
	private long createdBy; // 创建者（来源于backend_user用户表的用户id）
	private Date creationTime; // 创建时间
	private long modifyBy; // 更新者（来源于backend_user用户表的用户id）
	private Date modifyDate; // 最新更新时间
 
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
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
