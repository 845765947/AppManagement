package com.smbms.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smbms.dao.AppVersionMapper;
import com.smbms.pojo.App_version;
import com.smbms.service.AppVersionService;

@Service
public class AppVersionServiceImpl implements AppVersionService {
	@Resource
	private AppVersionMapper dao;

	@Override
	public List<App_version> selectInfoList(String appinfoid) {
		List<App_version> list = new ArrayList<App_version>();
		list = dao.selectInfoList(appinfoid);
		return list;
	}

	@Override
	public boolean addversionsave(App_version app) {
		return dao.addversionsave(app) > 0 ? true : false;
	}

	@Override
	public App_version selectVersion(String appinfoid) {
		return dao.selectVersion(appinfoid);
	}

	@Override
	public boolean deleteFile(String appinfoid, int modifyBy) {
		return dao.deleteFile(appinfoid, modifyBy) > 0 ? true : false;
	}

	@Override
	public boolean appversionmodifysave(App_version vserion) {
		return dao.appversionmodifysave(vserion) > 0 ? true : false;
	}

	@Override
	public boolean deleteAppVersion(String id) {
		return dao.deleteAppVersion(id) > 0 ? true : false;
	}
}
