package com.smbms.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smbms.dao.UserMapper;
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
}
