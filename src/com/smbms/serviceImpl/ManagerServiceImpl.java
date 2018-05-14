package com.smbms.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smbms.dao.ManagerMapper;
import com.smbms.pojo.Backend_user;
import com.smbms.service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Resource
	private ManagerMapper dao;

	@Override
	public Backend_user seleteUserName(String id, String pwd) {
		Backend_user user = null;
		user = dao.seleteUserName(id);
		if (user != null) {
			if (!(pwd.equals(user.getUserPassword()))) {
				user = null;
			}
		}
		return user;
	}

	@Override
	public boolean checksave(String id, String status) {
		return dao.checksave(id, status) > 0 ? true : false;
	}

}
