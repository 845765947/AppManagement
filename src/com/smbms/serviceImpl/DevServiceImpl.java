package com.smbms.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smbms.dao.DevMapper;
import com.smbms.pojo.App_info;
import com.smbms.pojo.Data_Dictionary;
import com.smbms.pojo.Dev_user;
import com.smbms.service.DevService;

@Service
public class DevServiceImpl implements DevService {
	@Resource
	private DevMapper devdao;;

	@Override
	public Dev_user seleteUserName(String devCode, String pwd) {
		Dev_user user = null;
		user = devdao.seleteUserName(devCode);
		if (user != null) {
			if (!(user.getDevPassword().equals(pwd))) {
				user = null;
			}
		}
		return user;
	}

	@Override
	public List<App_info> selectAppinfo(String querySoftwareName,
			String queryStatus, String queryFlatformId,
			String queryCategoryLevel1, String queryCategoryLevel2,
			String queryCategoryLevel3, Integer currentPageNo,
			Integer pagesize, long id) {
		List<App_info> list = new ArrayList<App_info>();
		// 调用分页方法
		list = devdao.selectAppinfo(querySoftwareName, queryStatus,
				queryFlatformId, queryCategoryLevel1, queryCategoryLevel2,
				queryCategoryLevel3, currentPageNo, pagesize, id);
		return list;
	}

	@Override
	public List<Data_Dictionary> selectGrading(String typeCode) {
		List<Data_Dictionary> list = new ArrayList<Data_Dictionary>();
		// 根据Type类型返回相应的列表
		list = devdao.selectGrading(typeCode);
		return list;
	}

	@Override
	public int coucatAppinfo(String querySoftwareName, String queryStatus,
			String queryFlatformId, String queryCategoryLevel1,
			String queryCategoryLevel2, String queryCategoryLevel3) {
		return devdao.coucatAppinfo(querySoftwareName, queryStatus,
				queryFlatformId, queryCategoryLevel1, queryCategoryLevel2,
				queryCategoryLevel3);
	}
}
