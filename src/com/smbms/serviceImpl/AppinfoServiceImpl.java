package com.smbms.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smbms.dao.AppinfoMapper;
import com.smbms.pojo.App_info;
import com.smbms.pojo.App_version;
import com.smbms.service.AppinfoService;

@Service
public class AppinfoServiceImpl implements AppinfoService {

	@Resource
	private AppinfoMapper dao;

	@Override
	public App_info selectInfoCondition(String apkName, String appinfoid) {
		return dao.selectInfoCondition(apkName, appinfoid);
	}

	@Override
	public boolean insertInfo(App_info info) {
		return dao.insertInfo(info) > 0 ? true : false;
	}

	@Override
	public boolean updateAppinfo(App_info info) {
		return dao.updateAppinfo(info) > 0 ? true : false;
	}

	@Override
	public boolean updateAppVersion(String versionId, String id) {
		return dao.updateAppVersion(versionId, id) > 0 ? true : false;
	}

	@Override
	public boolean deleteAppinfo(String id) {
		return dao.deleteAppinfo(id) > 0 ? true : false;
	}

	@Override
	public boolean sale(String appId, String appstatus) {
		return dao.sale(appId, appstatus) > 0 ? true : false;
	}

}
