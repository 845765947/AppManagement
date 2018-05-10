package com.smbms.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smbms.dao.UserMapper;
import com.smbms.pojo.App_info;
import com.smbms.pojo.Data_Dictionary;
import com.smbms.pojo.Dev_user;
import com.smbms.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserMapper userdao;

	@Override
	public Dev_user seleteUserName(String devCode, String pwd) {
		Dev_user user = new Dev_user();
		user = userdao.seleteUserName(devCode);
		if (!(user.getDevPassword().equals(pwd))) {
			user = null;
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
		list = userdao.selectAppinfo(querySoftwareName, queryStatus,
				queryFlatformId, queryCategoryLevel1, queryCategoryLevel2,
				queryCategoryLevel3, currentPageNo, pagesize, id);
		return list;
	}

	@Override
	public List<Data_Dictionary> selectGrading(String typeCode) {
		List<Data_Dictionary> list = new ArrayList<Data_Dictionary>();
		// 根据Type类型返回相应的列表
		list = userdao.selectGrading(typeCode);
		return list;
	}

	@Override
	public int coucatAppinfo(String querySoftwareName, String queryStatus,
			String queryFlatformId) {
		return userdao.coucatAppinfo(querySoftwareName, queryStatus,queryFlatformId);
	}
}
